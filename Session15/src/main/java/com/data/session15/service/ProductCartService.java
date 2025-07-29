package com.data.session15.service;

import com.data.session15.modal.entity.ProductCart;
import com.data.session15.modal.entity.User;

import java.util.List;

public interface ProductCartService {
    List<ProductCart> getCartItems(User user);
    ProductCart addProductToCart(User user, Long productId, int quantity);
    ProductCart updateQuantity(User user, Long productId, int quantity);
    void removeProduct(User user, Long productId);
    void clearCart(User user);
}
