package com.data.session11.model.dto.req;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private double price;
    private int quantity;
}
