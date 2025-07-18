package com.data.session08.service;

import com.data.session08.model.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    List<Order> getAllOrders();
}
