package com.data.session11.controller;

import com.data.session11.model.dto.req.LoginRequest;
import com.data.session11.model.dto.req.UserRegister;
import com.data.session11.model.dto.res.ApiResponse;
import com.data.session11.model.entity.User;
import com.data.session11.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<ApiResponse<User>>  registerUser(@RequestBody UserRegister userRegister) {
        return ResponseEntity.ok(new ApiResponse<>(true,"register success",userService.registerUser(userRegister), HttpStatus.CREATED));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(true,"success",userService.login(request),HttpStatus.CREATED));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) throws
            ServletException {
        request.logout();
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello! Bạn đã xác thực thành công.");
    }

}
