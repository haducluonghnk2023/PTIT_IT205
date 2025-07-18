package com.data.session08.service.order;

import com.data.session08.model.entity.Order;
import com.data.session08.model.entity.OrderDetail;
import com.data.session08.repo.OrderRepository;
import com.data.session08.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public Order createOrder(Order order) {
        // Gán mối quan hệ ngược lại
        for (OrderDetail detail : order.getOrderDetails()) {
            detail.setOrder(order);
        }

        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
