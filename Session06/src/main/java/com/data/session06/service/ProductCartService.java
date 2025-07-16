package com.data.session06.service;

import com.data.session06.model.entity.ProductCart;
import com.data.session06.model.entity.User;

import java.util.List;

public interface ProductCartService {
    List<ProductCart> getCartItemsByUser(User user);
    ProductCart addToCart(ProductCart productCart);
    ProductCart updateQuantity(Long id, Integer quantity);
    void removeFromCart(Long id);
}
