package com.data.session08.controller;

import com.data.session08.model.entity.PaymentSlip;
import com.data.session08.service.PaymentSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Map;

@RestController
@RequestMapping("/paymentslips")
public class PaymentSlipController {

    @Autowired
    private PaymentSlipService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PaymentSlip paymentSlip) {
        return ResponseEntity.ok(service.create(paymentSlip));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PaymentSlip paymentSlip) throws NoResourceFoundException {
        return ResponseEntity.ok(service.update(id, paymentSlip));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NoResourceFoundException {
        service.delete(id);
        return ResponseEntity.ok(Map.of("message", "Deleted successfully"));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.findAll());
    }
}