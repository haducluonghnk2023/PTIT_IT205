package com.data.session08.service;

import com.data.session08.model.entity.Customer;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    Customer update(Long id, Customer customer) throws NoResourceFoundException;
    void delete(Long id);
    List<Customer> getAllActive();
}
