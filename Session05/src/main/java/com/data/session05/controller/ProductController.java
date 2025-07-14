package com.data.session05.controller;

import com.data.session05.modal.dto.res.DataResponse;
import com.data.session05.modal.entity.Product;
import com.data.session05.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET /products
    @GetMapping
    public ResponseEntity<DataResponse<List<Product>>> getAllProducts() {
        List<Product> result = productService.getAllProducts();
        return ResponseEntity.ok(new DataResponse<>(result, HttpStatus.OK));
    }

    // GET /products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Product>> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(new DataResponse<>(product, HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
    }

    // POST /products
    @PostMapping
    public ResponseEntity<DataResponse<Product>> createProduct(@RequestBody Product product) {
        Product created = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DataResponse<>(created, HttpStatus.CREATED));
    }

    // PUT /products/{id}
    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Product>> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updated = productService.updateProduct(id, product);
        if (updated != null) {
            return ResponseEntity.ok(new DataResponse<>(updated, HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
    }

    // DELETE /products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(new DataResponse<>("Deleted successfully", HttpStatus.OK));
    }
}
