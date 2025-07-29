package com.data.session15.controller;

import com.data.session15.modal.dto.res.ApiResponse;
import com.data.session15.modal.entity.Product;
import com.data.session15.service.OrderDetailService;
import com.data.session15.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/products")
public class PublicProductController {
    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(new ApiResponse<>("Thành công", productService.getAllProducts(), true, HttpStatus.OK));
    }

    @GetMapping("/sold")
    public ResponseEntity<?> getSoldProducts() {
        return ResponseEntity.ok(new ApiResponse<>("Thành công", orderDetailService.getSoldProducts(), true, HttpStatus.OK));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
}
