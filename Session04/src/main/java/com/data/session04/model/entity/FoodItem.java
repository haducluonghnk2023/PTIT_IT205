package com.data.session04.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "food_item")
public class FoodItem {
    @Id
    private Long id;

    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private double price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expirationDate;
}
