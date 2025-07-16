package com.data.session06.controller;

import com.data.session06.model.dto.res.DataResponse;
import com.data.session06.model.entity.Product;
import com.data.session06.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductService  productService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Product>>> getAllProducts(
            Pageable pageable,
            @RequestParam(required = false) String search
    ) {
        Page<Product> productPage = productService.getAllProducts(pageable, search);
        List<Product> products = productPage.getContent();
        return ResponseEntity.ok(new DataResponse<>(products, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Product>> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(new DataResponse<>(productService.createProduct(product), HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Product>> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok(new DataResponse<>(productService.updateProduct(id, product), HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Product>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(new DataResponse<>(productService.getProductById(id), HttpStatus.OK));
    }
}
