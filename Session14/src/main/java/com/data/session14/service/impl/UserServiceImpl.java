package com.data.session14.service.impl;

import com.data.session14.modal.dto.req.OtpRequest;
import com.data.session14.modal.dto.req.UserLogin;
import com.data.session14.modal.dto.req.UserRegister;
import com.data.session14.modal.dto.res.JWTResponse;
import com.data.session14.modal.entity.Role;
import com.data.session14.modal.entity.User;
import com.data.session14.repository.RoleRepository;
import com.data.session14.repository.UserRepository;
import com.data.session14.sercurity.jwt.JWTProvider;
import com.data.session14.sercurity.principal.CustomUserDetails;
import com.data.session14.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTProvider jwtProvider;

    @Override
    public User registerUser(UserRegister ur) {
        if (userRepository.existsByUsername(ur.getUsername())) {
            throw new RuntimeException("Username đã tồn tại");
        }

        // Kiểm tra email
        if (userRepository.existsByEmail(ur.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }

        User user = User.builder()
                .username(ur.getUsername())
                .password(passwordEncoder.encode(ur.getPassword()))
                .email(ur.getEmail())
                .enabled(ur.getEnabled())
                .roles(mapRoleStringToRole(ur.getRoles()))
                .build();
        return userRepository.save(user);
    }

    private List<Role> mapRoleStringToRole(List<String> roles) {
        List<Role> roleList = new ArrayList<>();
        if (roles != null &&  !roles.isEmpty()) {
            roles.forEach(role -> {
                switch (role){
                    case "ROLE_ADMIN":
                        roleList.add(roleRepository.findByName(role).orElseThrow(()-> new NoSuchElementException("khong ton tai role_admin")));
                        break;
                    case "ROLE_USER":
                        roleList.add(roleRepository.findByName(role).orElseThrow(()-> new NoSuchElementException("khong ton tai role_user")));
                        break;
                    case "ROLE_MODERATOR":
                        roleList.add(roleRepository.findByName(role).orElseThrow(()-> new NoSuchElementException("khong ton tai role_moderator")));
                        break;
                    default:
                        roleList.add(roleRepository.findByName("ROLE_USER").orElseThrow(()-> new NoSuchElementException("khong ton tai role_user")));
                }
            });
        }else {
            roleList.add(roleRepository.findByName("ROLE_USER").orElseThrow(()-> new NoSuchElementException("khong ton tai role_user")));
        }
        return roleList;
    }

    @Override
    public JWTResponse loginUser(UserLogin ul) {
        try {
            // Xác thực thông tin đăng nhập
            Authentication authen = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(ul.getUsername(), ul.getPassword())
            );

            // Lấy thông tin user đã xác thực
            CustomUserDetails customUserDetails = (CustomUserDetails) authen.getPrincipal();

            // Sinh JWT token
            String token = jwtProvider.generateToken(customUserDetails.getUsername());

            // Trả về JWTResponse
            return JWTResponse.builder()
                    .username(customUserDetails.getUsername())
                    .token(token)
                    .password(customUserDetails.getPassword()) // có thể bỏ nếu không cần trả password ra ngoài
                    .email(customUserDetails.getEmail())
                    .enabled(customUserDetails.isEnabled())
                    .build();

        } catch (AuthenticationException e) {
            log.error("Sai username hoặc password: {}", e.getMessage());
            throw new RuntimeException("Username hoặc password không chính xác");
        }
    }

    @Override
    public User verifyPassword(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Mật khẩu không đúng");
        }

        return user;
    }


    @Override
    public String generateOtp() {
        int otp = (int) (100000 + Math.random() * 900000); // OTP 6 chữ số
        return String.valueOf(otp);
    }


    @Override
    public JWTResponse verifyOtpAndLogin(OtpRequest request) {
        User user = userRepository.findByUsername(request.getUsername());

        if (!request.getOtp().equals(user.getOtp())) {
            throw new RuntimeException("OTP không chính xác");
        }

        // Xác thực thành công, cấp token
        String token = jwtProvider.generateToken(user.getUsername());

        return JWTResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .enabled(user.getEnabled())
                .token(token)
                .build();
    }


    @Override
    public void logoutAllSessions() {

    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }


}
