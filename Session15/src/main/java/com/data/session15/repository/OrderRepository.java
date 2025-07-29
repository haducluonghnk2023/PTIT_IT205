package com.data.session15.repository;

import com.data.session15.modal.entity.Order;
import com.data.session15.modal.entity.OrderStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.status = :status WHERE o.orderId = :id")
    void updateOrderStatus(Long id, String status);
    List<Order> findByStatus(OrderStatus status);
}
