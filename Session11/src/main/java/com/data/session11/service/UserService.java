package com.data.session11.service;

import com.data.session11.model.dto.req.UserRegister;
import com.data.session11.model.entity.User;

public interface UserService {
    User registerUser(UserRegister ur);
}
