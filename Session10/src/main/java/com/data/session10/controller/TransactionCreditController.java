package com.data.session10.controller;

import com.data.session10.model.entity.TransactionCredit;
import com.data.session10.service.transaction_credit.TransactionCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/credit-transactions")
public class TransactionCreditController {
    @Autowired
    private TransactionCreditService transactionCreditService;

    @PostMapping
    public ResponseEntity<TransactionCredit> create(@RequestBody TransactionCredit transaction) {
        TransactionCredit created = transactionCreditService.createTransaction(transaction);
        return ResponseEntity.status(201).body(created);
    }
}
