package com.data.session15.service;

import com.data.session15.modal.dto.req.LoginRequest;
import com.data.session15.modal.dto.req.RegisterRequest;
import com.data.session15.modal.dto.res.JWTResponse;
import com.data.session15.modal.entity.Role;
import com.data.session15.modal.entity.User;

import java.util.List;

public interface UserService {
    User registerUser(RegisterRequest rr);
    JWTResponse loginUser(LoginRequest lr);
    List<User> getAllUsers();
    void toggleUserStatus(Long userId, boolean enabled);
    List<Role> getAllRoles();
    List<User> searchUsersByName(String name);
    User findByUsername(String username);
}
