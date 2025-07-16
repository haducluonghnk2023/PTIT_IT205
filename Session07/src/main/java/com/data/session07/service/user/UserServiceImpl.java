package com.data.session07.service.user;

import com.data.session07.model.entity.User;
import com.data.session07.repository.UserRepository;
import com.data.session07.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User us = optionalUser.get();
            us.setUserName(user.getUserName());
            us.setPassword(user.getPassword());
            us.setFullName(user.getFullName());
            us.setEmail(user.getEmail());
            us.setAddress(user.getAddress());
            us.setPhoneNumber(user.getPhoneNumber());
            return userRepository.save(us);
        }
        return null;
    }


    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
