package com.data.session15.service.impl;

import com.data.session15.modal.entity.Order;
import com.data.session15.modal.entity.OrderStatus;
import com.data.session15.repository.OrderRepository;
import com.data.session15.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public void updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy đơn hàng có id = " + id));
        order.setStatus(OrderStatus.valueOf(status.toUpperCase())); // Enum check
        orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(OrderStatus.valueOf(status.toUpperCase()));
    }

    @Override
    public Order getOrderDetailById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy đơn hàng có id = " + id));
    }
}
