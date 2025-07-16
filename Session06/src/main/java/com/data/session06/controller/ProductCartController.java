package com.data.session06.controller;

import com.data.session06.model.dto.res.DataResponse;
import com.data.session06.model.entity.ProductCart;
import com.data.session06.model.entity.User;
import com.data.session06.service.ProductCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class ProductCartController {
    @Autowired
    private ProductCartService cartService;

    @GetMapping
    public ResponseEntity<DataResponse<List<ProductCart>>> getCartByUser(@RequestParam Long userId) {
        User user = new User();
        user.setId(userId);
        List<ProductCart> cartItems = cartService.getCartItemsByUser(user);
        return ResponseEntity.ok(new DataResponse<>(cartItems, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<ProductCart>> addToCart(@RequestBody ProductCart cart) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DataResponse<>(cartService.addToCart(cart), HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<ProductCart>> updateQuantity(
            @PathVariable Long id,
            @RequestParam Integer quantity
    ) {
        ProductCart updated = cartService.updateQuantity(id, quantity);
        if (updated != null) {
            return ResponseEntity.ok(new DataResponse<>(updated, HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> deleteItem(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return ResponseEntity.ok(new DataResponse<>("Đã xóa sản phẩm khỏi giỏ", HttpStatus.OK));
    }
}
