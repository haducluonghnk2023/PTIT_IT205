package com.data.session15.service.impl;

import com.data.session15.modal.dto.req.LoginRequest;
import com.data.session15.modal.dto.req.RegisterRequest;
import com.data.session15.modal.dto.res.JWTResponse;
import com.data.session15.modal.entity.Role;
import com.data.session15.modal.entity.User;
import com.data.session15.repository.RoleRepository;
import com.data.session15.repository.UserRepository;
import com.data.session15.security.jwt.JWTProvider;
import com.data.session15.security.principal.CustomUserDetail;
import com.data.session15.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public User registerUser(RegisterRequest rr) {
        if (userRepository.existsByUserName(rr.getUserName())) {
            throw new RuntimeException("Username đã tồn tại");
        }

        if (userRepository.existsByEmail(rr.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }

        User user = User.builder()
                .userName(rr.getUserName())
                .password(passwordEncoder.encode(rr.getPassword()))
                .fullName(rr.getFullName())
                .address(rr.getAddress())
                .email(rr.getEmail())
                .phone(rr.getPhone())
                .enabled(true)
                .roles(mapToRoles(rr.getRoles()))
                .build();
        return userRepository.save(user);
    }


    private Set<Role> mapToRoles(Set<String> roleNames) {
        Set<Role> roles = new HashSet<>();
        for (String roleName : roleNames) {
            Role role = roleRepository.findByRoleName(roleName.trim());
            if (role == null) {
                throw new RuntimeException("Không tồn tại role: " + roleName);
            }
            roles.add(role);
        }
        return roles;
    }


    @Override
    public JWTResponse loginUser(LoginRequest lr) {
        try {
            // Xác thực thông tin đăng nhập
            Authentication authen = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(lr.getUsername(), lr.getPassword())
            );

            // Lấy thông tin user đã xác thực
            CustomUserDetail customUserDetails = (CustomUserDetail) authen.getPrincipal();

            // Sinh JWT token
            String token = jwtProvider.generateToken(customUserDetails.getUsername());

            // Trả về JWTResponse
            return JWTResponse.builder()
                    .username(customUserDetails.getUsername())
                    .token(token)
                    .password(customUserDetails.getPassword())
                    .email(customUserDetails.getEmail())
                    .enabled(customUserDetails.isEnabled())
                    .authorities(customUserDetails.getAuthorities())
                    .fullname(customUserDetails.getFullname())
                    .address(customUserDetails.getAddress())
                    .phone(customUserDetails.getPhone())
                    .build();

        } catch (AuthenticationException e) {
            log.error("Sai username hoặc password: {}", e.getMessage());
            throw new RuntimeException("Username hoặc password không chính xác");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void toggleUserStatus(Long userId, boolean enabled) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Không tồn tại user: " + userId));
        user.setEnabled(enabled);
        userRepository.save(user);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<User> searchUsersByName(String name) {
        return userRepository.findByFullNameContainingIgnoreCase(name);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }
}
