package com.data.session14.service.impl;

import com.data.session14.modal.entity.RefreshToken;
import com.data.session14.modal.entity.User;
import com.data.session14.repository.RefreshTokenRepository;
import com.data.session14.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Autowired
    private RefreshTokenRepository refreshTokenRepo;
    @Override
    public RefreshToken createRefreshToken(User user, String ipAddress) {
        RefreshToken token = RefreshToken.builder()
                .user(user)
                .addressIp(ipAddress)
                .token(UUID.randomUUID().toString())
                .expiryDate(LocalDateTime.now().plusDays(7)) // 7 ngày
                .build();
        return refreshTokenRepo.save(token);
    }

    @Override
    public RefreshToken verifyRefreshToken(String token) {
        return refreshTokenRepo.findByToken(token)
                .filter(rt -> rt.getExpiryDate().isAfter(LocalDateTime.now()))
                .orElseThrow(() -> new RuntimeException("Refresh token không hợp lệ hoặc đã hết hạn"));
    }

    @Override
    public void revokeRefreshToken(User user) {
        refreshTokenRepo.deleteByUser(user);
    }
}
