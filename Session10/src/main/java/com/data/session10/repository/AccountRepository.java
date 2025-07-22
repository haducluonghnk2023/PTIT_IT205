package com.data.session10.repository;

import com.data.session10.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByCitizenIdentificationCard(String citizenIdentificationCard);
}
