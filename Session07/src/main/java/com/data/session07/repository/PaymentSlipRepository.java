package com.data.session07.repository;

import com.data.session07.model.entity.PaymentSlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface PaymentSlipRepository extends JpaRepository<PaymentSlip, Long> {
    @Query("SELECT COALESCE(SUM(p.money), 0) FROM PaymentSlip p WHERE p.createdAt BETWEEN :start AND :end")
    double sumMoneyByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
