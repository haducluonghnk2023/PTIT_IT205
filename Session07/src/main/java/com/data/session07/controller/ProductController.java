package com.data.session07.controller;
import com.data.session07.model.dto.res.DataResponse;
import com.data.session07.model.entity.Product;
import com.data.session07.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Product>>> getAllFruits() {
        return ResponseEntity.ok(new DataResponse<>(productService.findAll(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Product>> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(new DataResponse<>(productService.createProduct(product), HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Product>> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product ud = productService.updateProduct(id, product);
        if (ud != null) {
            return ResponseEntity.ok(new DataResponse<>(ud, HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<?>> deleteProduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DataResponse<>("Product not found", HttpStatus.NOT_FOUND));
        }

        if (product.getProductDetails() != null && !product.getProductDetails().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DataResponse<>("Cannot delete product with existing details", HttpStatus.BAD_REQUEST));
        }

        productService.deleteById(id);
        return ResponseEntity.ok(new DataResponse<>(null, HttpStatus.OK));
    }

}
