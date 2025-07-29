package com.data.session15.service;

import com.data.session15.modal.dto.req.ProductDTO;
import com.data.session15.modal.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findProductsByProductNameContainingIgnoreCase(String productName);
    List<Product> getProductsByCategory(Long categoryId);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product createProduct(ProductDTO productDTO);
    Product updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
}
