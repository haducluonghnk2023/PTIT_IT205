package com.data.testfinaljvw.controller;

import com.data.testfinaljvw.modal.dto.req.LoginReq;
import com.data.testfinaljvw.modal.dto.req.RegisterReq;
import com.data.testfinaljvw.modal.dto.res.ApiResponse;
import com.data.testfinaljvw.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody RegisterReq r) {
        return ResponseEntity.ok(new ApiResponse<>("Đăng ký thành công",customerService.register(r),true, HttpStatus.CREATED));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginReq l) {
        return ResponseEntity.ok(new ApiResponse<>("Đăng nhập thành công",customerService.login(l),true, HttpStatus.OK));
    }

}
