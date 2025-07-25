package com.data.session11.service;

import com.data.session11.model.dto.req.ProductRequest;
import com.data.session11.model.entity.Product;

import java.util.List;

public interface ProductService {
    Product create(ProductRequest request);

    Product update(Long id, ProductRequest request);

    void delete(Long id);

    List<Product> getAll();

    Product getById(Long id);
}
