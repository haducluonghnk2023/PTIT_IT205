package com.data.session14.controller;

import com.data.session14.modal.dto.req.OtpRequest;
import com.data.session14.modal.dto.req.UserLogin;
import com.data.session14.modal.dto.req.UserRegister;
import com.data.session14.modal.dto.res.APIResponse;
import com.data.session14.modal.dto.res.JWTResponse;
import com.data.session14.modal.entity.RefreshToken;
import com.data.session14.modal.entity.TokenBlacklist;
import com.data.session14.modal.entity.User;
import com.data.session14.repository.TokenBlacklistRepository;
import com.data.session14.repository.UserRepository;
import com.data.session14.sercurity.jwt.JWTProvider;
import com.data.session14.service.EmailService;
import com.data.session14.service.RefreshTokenService;
import com.data.session14.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private TokenBlacklistRepository blacklistRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    // === Đăng ký tài khoản ===
    @PostMapping("/register")
    public ResponseEntity<APIResponse<User>> registerUser(@RequestBody  UserRegister request) {
        User created = userService.registerUser(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new APIResponse<>("Đăng ký thành công", created, true, HttpStatus.CREATED));
    }

    // === Đăng nhập - Gửi OTP tới email ===
    @PostMapping("/login")
    public ResponseEntity<APIResponse<String>> login(@RequestBody  UserLogin request) {
        User user = userService.verifyPassword(request.getUsername(), request.getPassword());
        String otp = userService.generateOtp();
        user.setOtp(otp);
        userService.save(user);
        emailService.sendOtpEmail(user.getEmail(), otp);

        return ResponseEntity.ok(new APIResponse<>("OTP đã được gửi tới email", null, true, HttpStatus.OK));
    }

    // === Xác minh OTP và cấp JWT token ===
    @PostMapping("/verify-otp")
    public ResponseEntity<APIResponse<JWTResponse>> verifyOtp(@RequestBody OtpRequest request) {
        JWTResponse response = (JWTResponse) userService.verifyOtpAndLogin(request);
        return ResponseEntity.ok(new APIResponse<>("Xác thực OTP thành công", response, true, HttpStatus.OK));
    }

    // === Cấp lại access token qua refresh token ===
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshAccessToken(@RequestHeader("Refresh-Token") String refreshToken) {
        RefreshToken token = refreshTokenService.verifyRefreshToken(refreshToken);
        String accessToken = jwtProvider.generateToken(token.getUser().getUsername());
        return ResponseEntity.ok(Map.of("accessToken", accessToken));
    }

    // === Đăng xuất: Thu hồi access + refresh token ===
    @PostMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<APIResponse<String>> logout(@AuthenticationPrincipal UserDetails userDetails,
                                                      @RequestHeader("Authorization") String accessTokenRaw) {
        String accessToken = accessTokenRaw.replace("Bearer ", "");
        User user = userRepository.findByUsername(userDetails.getUsername());

        // Thu hồi refresh token
        refreshTokenService.revokeRefreshToken(user);

        // Đưa access token vào blacklist
        TokenBlacklist blacklist = new TokenBlacklist(null, accessToken, LocalDateTime.now());
        blacklistRepo.save(blacklist);

        return ResponseEntity.ok(new APIResponse<>("Đăng xuất thành công", null, true, HttpStatus.OK));
    }
}
