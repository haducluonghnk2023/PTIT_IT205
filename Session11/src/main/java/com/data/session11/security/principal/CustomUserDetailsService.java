package com.data.session11.security.principal;

import com.data.session11.model.entity.Role;
import com.data.session11.model.entity.User;
import com.data.session11.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tồn tại người dùng với username: " + username));

        CustomUserDetails ud = CustomUserDetails.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .address(user.getAddress())
                .fullName(user.getFullname())
                .email(user.getEmail())
                .enabled(user.getEnable())
                .authorities(mapRoleToGrantAuthorities(user.getRoles()))
                .build();

        return ud;
    }

    private Collection<? extends GrantedAuthority> mapRoleToGrantAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }

}
