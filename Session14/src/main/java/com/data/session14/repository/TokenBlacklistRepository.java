package com.data.session14.repository;

import com.data.session14.modal.entity.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist,Long> {
    boolean existsByToken(String token);
}
