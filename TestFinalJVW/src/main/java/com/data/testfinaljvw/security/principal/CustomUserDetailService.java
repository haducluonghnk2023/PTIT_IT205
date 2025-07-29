package com.data.testfinaljvw.security.principal;

import com.data.testfinaljvw.modal.entity.Customer;
import com.data.testfinaljvw.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);
        return CustomUserDetails.builder()
                .username(customer.getUsername())
                .password(customer.getPassword())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .fullName(customer.getFullName())
                .isLogin(customer.getIsLogin())
                .status(customer.getStatus())
                .build();
    }

}
