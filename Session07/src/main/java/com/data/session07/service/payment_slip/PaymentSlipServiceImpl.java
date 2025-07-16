package com.data.session07.service.payment_slip;

import com.data.session07.model.entity.PaymentSlip;
import com.data.session07.repository.PaymentSlipRepository;
import com.data.session07.service.PaymentSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentSlipServiceImpl implements PaymentSlipService {
    @Autowired
    private PaymentSlipRepository paymentSlipRepository;

    @Override
    public List<PaymentSlip> getAllPaymentSlips() {
        return paymentSlipRepository.findAll();
    }

    @Override
    public PaymentSlip getPaymentSlipById(Long id) {
        return paymentSlipRepository.findById(id).orElse(null);
    }

    @Override
    public PaymentSlip addPaymentSlip(PaymentSlip paymentSlip) {
        return paymentSlipRepository.save(paymentSlip);
    }

    @Override
    public double sumMoneyByCreatedAtBetween(LocalDateTime start, LocalDateTime end) {
        return paymentSlipRepository.sumMoneyByCreatedAtBetween(start, end);
    }
}
