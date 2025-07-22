package com.data.session10.service;

import com.data.session10.model.entity.Transaction;

public interface TransactionService {
    Transaction transferMoney(Transaction transaction);
}