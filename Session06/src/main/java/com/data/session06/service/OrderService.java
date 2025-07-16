package com.data.session06.service;

import com.data.session06.model.entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    List<Order> getAllOrders();
    List<Order> getOrdersByDate(Date date);
}
