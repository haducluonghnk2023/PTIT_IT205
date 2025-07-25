package com.data.session11.service.impl;

import com.data.session11.model.dto.req.LoginRequest;
import com.data.session11.model.dto.req.UpdateProfileRequest;
import com.data.session11.model.dto.req.UserRegister;
import com.data.session11.model.dto.res.JWTResponse;
import com.data.session11.model.entity.Role;
import com.data.session11.model.entity.User;
import com.data.session11.repository.RoleRepository;
import com.data.session11.repository.UserRepository;
import com.data.session11.security.jwt.JWTProvider;
import com.data.session11.security.principal.CustomUserDetails;
import com.data.session11.security.principal.CustomUserDetailsService;
import com.data.session11.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public User registerUser(UserRegister ur) {
        User user = User.builder()
                .username(ur.getUsername())
                .password(passwordEncoder.encode(ur.getPassword()))
                .fullname(ur.getFullname())
                .address(ur.getAddress())
                .email(ur.getEmail())
                .phone(ur.getPhone())
                .enable(true)
                .roles(mapRoleStringToRole(ur.getRoles()))
                .build();

        return userRepository.save(user);
    }

    @Override
    public JWTResponse login(LoginRequest lr) {
        Authentication authentication = null;
        try{
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(lr.getUsername(), lr.getPassword()));
        }catch(Exception e){
            log.error("Username or password is incorrect");
        }
        CustomUserDetails userDetails =  (CustomUserDetails) authentication.getPrincipal();
        String token = jwtProvider.generateToken(userDetails.getUsername());
        return JWTResponse.builder().
                username(userDetails.getUsername()).
                fullname(userDetails.getFullName()).
                email(userDetails.getEmail()).
                phone(userDetails.getPhone()).
                enabled(userDetails.isEnabled()).
                token(token).
                build();
    }

    @Override
    public void updateProfile(String username, UpdateProfileRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        // Cập nhật thông tin từ request
        if (request.getFullName() != null) {
            user.setFullname(request.getFullName());
        }
        if (request.getAddress() != null) {
            user.setAddress(request.getAddress());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        userRepository.save(user);
    }


    private List<Role> mapRoleStringToRole(List<String> roles) {
        List<Role> roleList = new ArrayList<>();

        if (roles != null && !roles.isEmpty()) {
            for (String roleName : roles) {
                roleRepository.findFirstByRoleName(roleName)
                        .ifPresent(roleList::add);
            }
        } else {
            roleRepository.findFirstByRoleName("ROLE_USER")
                    .ifPresent(roleList::add);
        }

        return roleList;
    }

}
