package com.data.session06.repository;

import com.data.session06.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
