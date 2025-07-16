package com.data.session06.service.order;

import com.data.session06.model.entity.Order;
import com.data.session06.repository.OrderRepository;
import com.data.session06.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zone).toLocalDate();

        // Tạo khoảng thời gian bắt đầu và kết thúc trong ngày
        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(LocalTime.MAX);

        return orderRepository.findByCreatedAtBetween(startOfDay, endOfDay);
    }

}
