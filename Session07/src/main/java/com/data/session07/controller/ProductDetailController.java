package com.data.session07.controller;
import com.data.session07.model.dto.res.DataResponse;
import com.data.session07.model.entity.Product;
import com.data.session07.model.entity.ProductDetail;
import com.data.session07.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product_details")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping
    public ResponseEntity<DataResponse<List<ProductDetail>>> getAllFruits() {
        return ResponseEntity.ok(new DataResponse<>(productDetailService.findAll(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<ProductDetail>> addProductDetail(@RequestBody ProductDetail pd) {
        return ResponseEntity.ok(new DataResponse<>(productDetailService.createProductDetail(pd), HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<ProductDetail>> updateProductDetail(@PathVariable Long id, @RequestBody ProductDetail pd) {
        ProductDetail pdt = productDetailService.updateProductDetail(id, pd);
        if (pdt != null) {
            return ResponseEntity.ok(new DataResponse<>(pdt, HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<?>> deleteProductDetail(@PathVariable Long id) {
        ProductDetail detail = productDetailService.findById(id);
        if (detail == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DataResponse<>("ProductDetail not found", HttpStatus.NOT_FOUND));
        }

        if (detail.getOrderDetails() != null && !detail.getOrderDetails().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DataResponse<>("Cannot delete product detail that exists in order detail", HttpStatus.BAD_REQUEST));
        }

        productDetailService.delete(id);
        return ResponseEntity.ok(new DataResponse<>(null, HttpStatus.OK));
    }

}
