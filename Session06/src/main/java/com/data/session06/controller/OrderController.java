package com.data.session06.controller;

import com.data.session06.model.dto.res.DataResponse;
import com.data.session06.model.entity.Order;
import com.data.session06.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Order>>> getAllOrders() {
        return ResponseEntity.ok(new DataResponse<>(orderService.getAllOrders(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Order>> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(new DataResponse<>(orderService.createOrder(order), HttpStatus.OK));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<DataResponse<List<Order>>> getAllOrdersByDate(
            @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<Order> orders = orderService.getOrdersByDate(date);
        return ResponseEntity.ok(new DataResponse<>(orders, HttpStatus.OK));
    }

}
