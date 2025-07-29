package com.data.testfinaljvw.service.impl;

import com.data.testfinaljvw.modal.dto.req.LoginReq;
import com.data.testfinaljvw.modal.dto.req.RegisterReq;
import com.data.testfinaljvw.modal.dto.res.JWTResponse;
import com.data.testfinaljvw.modal.entity.Customer;
import com.data.testfinaljvw.repository.CustomerRepository;
import com.data.testfinaljvw.security.jwt.JWTProvider;
import com.data.testfinaljvw.security.principal.CustomUserDetails;
import com.data.testfinaljvw.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public Customer register(RegisterReq r) {
        Customer c1 = customerRepository.findByEmail(r.getEmail());
        if(c1 != null){
            throw new RuntimeException("Email đã tồn tại");
        }
        Customer c = Customer.builder()
                .username(r.getUsername())
                .password(passwordEncoder.encode(r.getPassword()))
                .email(r.getEmail())
                .phone(r.getPhone())
                .fullName(r.getFullName())
                .isLogin(false)
                .status(true)
                .build();
        return customerRepository.save(c);
    }

    @Override
    public JWTResponse login(LoginReq l) {
        try {
            // Xác thực thông tin đăng nhập
            Authentication authen = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(l.getUsername(), l.getPassword())
            );

            // Lấy thông tin user đã xác thực
            CustomUserDetails customUserDetails = (CustomUserDetails) authen.getPrincipal();

            // Sinh JWT token
            String token = jwtProvider.generateToken(customUserDetails.getUsername());

            // Trả về JWTResponse
            return JWTResponse.builder()
                    .username(customUserDetails.getUsername())
                    .fullName(customUserDetails.getFullName())
                    .email(customUserDetails.getEmail())
                    .phone(customUserDetails.getPhone())
                    .isLogin(true)
                    .status(customUserDetails.getStatus())
                    .token(token)
                    .build();

        } catch (AuthenticationException e) {
            log.error("Sai username hoặc password: {}", e.getMessage());
            throw new RuntimeException("Username hoặc password không chính xác");
        }
    }

}
