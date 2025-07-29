package com.data.session15.controller;

import com.data.session15.modal.dto.res.ApiResponse;
import com.data.session15.modal.entity.ProductCart;
import com.data.session15.modal.entity.User;
import com.data.session15.service.ProductCartService;
import com.data.session15.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user/cart")
public class UserCartController {

    @Autowired
    private ProductCartService productCartService;

    @Autowired
    private UserService userService;

    // ✅ GET /api/user/cart – Lấy danh sách giỏ hàng
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductCart>>> getUserCart(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<ProductCart> carts = productCartService.getCartItems(user);
        return ResponseEntity.ok(new ApiResponse<>("Lấy giỏ hàng thành công", carts, true, HttpStatus.OK));
    }

    // ✅ POST /api/user/cart/add – Thêm sản phẩm vào giỏ
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<ProductCart>> addToCart(
            @RequestParam Long productId,
            @RequestParam int quantity,
            Principal principal) {
        User user = userService.findByUsername(principal.getName());
        ProductCart cart = productCartService.addProductToCart(user, productId, quantity);
        return new ResponseEntity<>(new ApiResponse<>("Thêm sản phẩm vào giỏ thành công", cart, true, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    // ✅ PUT /api/user/cart/update/{productId} – Cập nhật số lượng
    @PutMapping("/update/{productId}")
    public ResponseEntity<ApiResponse<ProductCart>> updateQuantity(
            @PathVariable Long productId,
            @RequestParam int quantity,
            Principal principal) {
        User user = userService.findByUsername(principal.getName());
        ProductCart cart = productCartService.updateQuantity(user, productId, quantity);
        return ResponseEntity.ok(new ApiResponse<>("Cập nhật số lượng thành công", cart, true, HttpStatus.OK));
    }

    // ✅ DELETE /api/user/cart/remove/{productId} – Xóa sản phẩm
    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<ApiResponse<String>> removeFromCart(
            @PathVariable Long productId,
            Principal principal) {
        User user = userService.findByUsername(principal.getName());
        productCartService.removeProduct(user, productId);
        return ResponseEntity.ok(new ApiResponse<>("Xóa sản phẩm khỏi giỏ thành công", null, true, HttpStatus.OK));
    }

    // ✅ DELETE /api/user/cart/clear – Xóa toàn bộ giỏ hàng
    @DeleteMapping("/clear")
    public ResponseEntity<ApiResponse<String>> clearCart(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        productCartService.clearCart(user);
        return ResponseEntity.ok(new ApiResponse<>("Xóa toàn bộ giỏ hàng thành công", null, true, HttpStatus.OK));
    }
}
