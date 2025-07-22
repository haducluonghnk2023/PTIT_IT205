package com.data.session10.controller;

import com.data.session10.model.entity.CreditCard;
import com.data.session10.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/creditcards")
@RequiredArgsConstructor
public class CreditCardController {

    private final CreditCardService creditCardService;

    @PostMapping
    public ResponseEntity<CreditCard> create(@RequestBody CreditCard creditCard) {
        return ResponseEntity.status(201).body(creditCardService.create(creditCard));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCard> updateLimit(@PathVariable UUID id, @RequestParam BigDecimal limit) {
        return ResponseEntity.ok(creditCardService.updateLimit(id, limit));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<CreditCard> updateStatus(@PathVariable UUID id, @RequestParam String status) {
        return ResponseEntity.ok(creditCardService.updateStatus(id, status));
    }
}
