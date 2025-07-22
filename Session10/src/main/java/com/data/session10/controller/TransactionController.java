package com.data.session10.controller;

import com.data.session10.model.entity.Transaction;
import com.data.session10.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> transfer(@RequestBody Transaction transaction) {
        Transaction result = transactionService.transferMoney(transaction);
        return ResponseEntity.status(201).body(result);
    }
}
