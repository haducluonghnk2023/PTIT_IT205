package com.data.session15.security.principal;

import com.data.session15.modal.entity.Role;
import com.data.session15.modal.entity.User;
import com.data.session15.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailServices implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        return CustomUserDetail.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .email(user.getEmail())
                .fullname(user.getFullName())
                .address(user.getAddress())
                .phone(user.getPhone())
                .enable(user.isEnabled())
                .authorities(mapRoleToGrandAuthorities(user.getRoles())).
                build();
    }

    private Collection<? extends GrantedAuthority> mapRoleToGrandAuthorities(Set<Role> roles) {
        return  roles.stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }
}
