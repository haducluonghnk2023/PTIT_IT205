package com.data.session15.service.impl;

import com.data.session15.modal.entity.Product;
import com.data.session15.modal.entity.ProductCart;
import com.data.session15.modal.entity.User;
import com.data.session15.repository.ProductCartRepository;
import com.data.session15.repository.ProductRepository;
import com.data.session15.service.ProductCartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductCartServiceImpl implements ProductCartService {

    @Autowired
    private ProductCartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductCart> getCartItems(User user) {
        return cartRepository.findByUser(user);
    }

    @Override
    public ProductCart addProductToCart(User user, Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        return cartRepository.findByUserAndProduct(user, product)
                .map(cart -> {
                    cart.setQuantity(cart.getQuantity() + quantity);
                    return cartRepository.save(cart);
                })
                .orElseGet(() -> {
                    ProductCart newCart = ProductCart.builder()
                            .user(user)
                            .product(product)
                            .quantity(quantity)
                            .build();
                    return cartRepository.save(newCart);
                });
    }

    @Override
    public ProductCart updateQuantity(User user, Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        ProductCart cart = cartRepository.findByUserAndProduct(user, product)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm trong giỏ hàng"));

        cart.setQuantity(quantity);
        return cartRepository.save(cart);
    }

    @Override
    public void removeProduct(User user, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        cartRepository.deleteByUserAndProduct(user, product);
    }

    @Override
    public void clearCart(User user) {
        cartRepository.deleteByUser(user);
    }
}
