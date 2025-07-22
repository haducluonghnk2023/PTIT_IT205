package com.data.session10.repository;

import com.data.session10.model.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CreditCardRepository extends JpaRepository<CreditCard, UUID> {
    Optional<CreditCard> findByAccountId(UUID accountId);
}
