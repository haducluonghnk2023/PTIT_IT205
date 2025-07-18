package com.data.session08.service;

import com.data.session08.model.entity.PaymentSlip;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

public interface PaymentSlipService {
    PaymentSlip create(PaymentSlip paymentSlip);
    PaymentSlip update(Long id, PaymentSlip paymentSlip) throws NoResourceFoundException;
    void delete(Long id) throws NoResourceFoundException;
    List<PaymentSlip> findAll();
}
