package com.data.session14.service;

import com.data.session14.modal.entity.RefreshToken;
import com.data.session14.modal.entity.User;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(User user, String ipAddress);
    RefreshToken verifyRefreshToken(String token);
    void revokeRefreshToken(User user);
}
