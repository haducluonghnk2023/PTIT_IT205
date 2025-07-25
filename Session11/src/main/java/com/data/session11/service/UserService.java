package com.data.session11.service;

import com.data.session11.model.dto.req.LoginRequest;
import com.data.session11.model.dto.req.UpdateProfileRequest;
import com.data.session11.model.dto.req.UserRegister;
import com.data.session11.model.dto.res.JWTResponse;
import com.data.session11.model.entity.User;

public interface UserService {
    User registerUser(UserRegister ur);
    JWTResponse login(LoginRequest lr);
    void updateProfile(String username, UpdateProfileRequest request);
}
