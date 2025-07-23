package com.data.session11.service.impl;

import com.data.session11.model.dto.req.UserRegister;
import com.data.session11.model.entity.Role;
import com.data.session11.model.entity.User;
import com.data.session11.repository.RoleRepository;
import com.data.session11.repository.UserRepository;
import com.data.session11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User registerUser(UserRegister ur) {
        User user = User.builder()
                .username(ur.getUsername())
                .password(passwordEncoder.encode(ur.getPassword()))
                .fullname(ur.getFullname())
                .address(ur.getAddress())
                .email(ur.getEmail())
                .phone(ur.getPhone())
                .enable(true)
                .roles(mapRoleStringToRole(ur.getRoles()))
                .build();

        return userRepository.save(user);
    }


    private List<Role> mapRoleStringToRole(List<String> roles) {
        List<Role> roleList = new ArrayList<>();

        if (roles != null && !roles.isEmpty()) {
            for (String roleName : roles) {
                roleRepository.findFirstByRoleName(roleName)
                        .ifPresent(roleList::add);
            }
        } else {
            roleRepository.findFirstByRoleName("ROLE_USER")
                    .ifPresent(roleList::add);
        }

        return roleList;
    }

}
