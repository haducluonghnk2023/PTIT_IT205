package com.data.session08.service.customer;

import com.data.session08.model.entity.Customer;
import com.data.session08.repo.CustomerRepository;
import com.data.session08.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        customer.setStatus(true);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setNumberOfPayments(0);
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Long id, Customer customer) throws NoResourceFoundException {
        Customer existing = customerRepository.findById(id)
                .orElseThrow(() -> new NoResourceFoundException(HttpMethod.PUT,"Customer not found with id: " + id));

        existing.setFullName(customer.getFullName());
        existing.setPhone(customer.getPhone());
        existing.setEmail(customer.getEmail());
        existing.setNumberOfPayments(customer.getNumberOfPayments());

        return customerRepository.save(existing);
    }


    @Override
    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Customer not found with id: " + id));
        customer.setStatus(false);
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllActive() {
        return customerRepository.findByStatusTrue();
    }
}
