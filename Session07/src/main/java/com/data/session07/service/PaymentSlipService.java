package com.data.session07.service;

import com.data.session07.model.entity.PaymentSlip;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface PaymentSlipService {
    List<PaymentSlip> getAllPaymentSlips();
    PaymentSlip getPaymentSlipById(Long id);
    PaymentSlip addPaymentSlip(PaymentSlip paymentSlip);
    double sumMoneyByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
