package com.data.session10.service;

import com.data.session10.model.entity.TransactionCredit;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionCreditService {
    TransactionCredit createTransaction(TransactionCredit transaction);
}
