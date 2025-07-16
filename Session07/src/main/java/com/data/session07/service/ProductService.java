package com.data.session07.service;

import com.data.session07.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteById(Long id);
}
