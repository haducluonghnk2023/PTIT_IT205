package com.data.session08.repo;

import com.data.session08.model.entity.PaymentSlip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentSlipRepository extends JpaRepository<PaymentSlip, Long> {
}
