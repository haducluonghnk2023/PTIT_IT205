package com.data.session10.repository;

import com.data.session10.model.entity.TransactionCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Repository
public interface TransactionCreditRepository  extends JpaRepository<TransactionCredit, UUID> {
    List<TransactionCredit> findByStatusAndCreatedAtBetween(String status, LocalDateTime from, LocalDateTime to);
}
