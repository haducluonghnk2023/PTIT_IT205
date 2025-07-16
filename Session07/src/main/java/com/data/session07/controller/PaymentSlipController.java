package com.data.session07.controller;

import com.data.session07.model.dto.res.DataResponse;
import com.data.session07.model.entity.PaymentSlip;
import com.data.session07.service.PaymentSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paymentslips")
public class PaymentSlipController {
    @Autowired
    private PaymentSlipService paymentSlipService;

    @GetMapping
    public ResponseEntity<DataResponse<List<PaymentSlip>>> getAllPaymentSlips() {
        return ResponseEntity.ok(new DataResponse<>(paymentSlipService.getAllPaymentSlips(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<PaymentSlip>> addPaymentSlip(@RequestBody PaymentSlip pl) {
        return ResponseEntity.ok(new DataResponse<>(paymentSlipService.addPaymentSlip(pl), HttpStatus.OK));
    }
}
