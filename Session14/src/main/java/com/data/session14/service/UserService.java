package com.data.session14.service;

import com.data.session14.modal.dto.req.OtpRequest;
import com.data.session14.modal.dto.req.UserLogin;
import com.data.session14.modal.dto.req.UserRegister;
import com.data.session14.modal.dto.res.JWTResponse;
import com.data.session14.modal.entity.User;

public interface UserService {
    User registerUser(UserRegister ur);
    JWTResponse loginUser(UserLogin ul);
    User verifyPassword(String username, String password);
    String generateOtp();
    Object verifyOtpAndLogin(OtpRequest request);
    void logoutAllSessions();
    void save(User user);
}
