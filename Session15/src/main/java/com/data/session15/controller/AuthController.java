package com.data.session15.controller;

import com.data.session15.modal.dto.req.LoginRequest;
import com.data.session15.modal.dto.req.RegisterRequest;
import com.data.session15.modal.dto.res.ApiResponse;
import com.data.session15.modal.entity.User;
import com.data.session15.security.jwt.JWTAuthFilter;
import com.data.session15.security.jwt.JWTProvider;
import com.data.session15.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private JWTAuthFilter jwtAuthFilter;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> registerUser(@RequestBody RegisterRequest request) {
        User created = userService.registerUser(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Đăng ký thành công", created, true, HttpStatus.CREATED));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest lr) {
        return ResponseEntity.ok(new ApiResponse<>("Đăng nhập thanh công", userService.loginUser(lr), true, HttpStatus.OK));
    }
}
