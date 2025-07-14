package com.data.session05.controller;

import com.data.session05.modal.entity.Product;
import com.data.session05.modal.entity.ProductList;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/productList")
public class ProductListController {

    // Mock data
    private final List<Product> products = Arrays.asList(
            new Product(1L, "Sản phẩm A","mô tả sản phẩm a",100.0),
            new Product(2L, "Sản phẩm B","mô tả sản phẩm b", 200.0)
    );

    // Tự động nhận JSON hoặc XML tùy vào Accept header
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ProductList getProducts() {
        return new ProductList(products);
    }
}