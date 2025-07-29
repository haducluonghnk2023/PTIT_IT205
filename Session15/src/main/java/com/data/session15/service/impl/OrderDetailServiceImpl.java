package com.data.session15.service.impl;

import com.data.session15.modal.entity.Product;
import com.data.session15.repository.OrderDetailRepository;
import com.data.session15.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public List<Product> getSoldProducts() {
        return orderDetailRepository.findSoldProducts();
    }
}
