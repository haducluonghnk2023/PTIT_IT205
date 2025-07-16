package com.data.session07.service;

import com.data.session07.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User createUser(User user);
    User updateUser(Long id,User user);
    void  deleteById(Long id);
}
