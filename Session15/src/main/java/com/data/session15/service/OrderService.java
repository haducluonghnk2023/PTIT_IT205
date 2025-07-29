package com.data.session15.service;


import com.data.session15.modal.entity.Order;

import java.util.List;

public interface OrderService {
    void updateOrderStatus(Long id, String status);
    List<Order> getAllOrders();
    List<Order> getOrdersByStatus(String status);
    Order getOrderDetailById(Long id);
}
