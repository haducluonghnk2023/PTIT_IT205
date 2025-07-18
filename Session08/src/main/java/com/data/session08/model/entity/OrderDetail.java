package com.data.session08.model.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Dish dish;

    private Integer quantity;
    private Double priceBuy;
}
