package com.data.session06.service.product_cart;

import com.data.session06.model.entity.ProductCart;
import com.data.session06.model.entity.User;
import com.data.session06.repository.ProductCartRepository;
import com.data.session06.service.ProductCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCartServiceImpl implements ProductCartService {
    @Autowired
    private ProductCartRepository productCartRepository;
    @Override
    public List<ProductCart> getCartItemsByUser(User user) {
        return productCartRepository.findByUser(user);
    }

    @Override
    public ProductCart addToCart(ProductCart productCart) {
        return productCartRepository.save(productCart);
    }

    @Override
    public ProductCart updateQuantity(Long id, Integer quantity) {
        ProductCart cart = productCartRepository.findById(id).orElse(null);
        if (cart != null) {
            cart.setQuantity(quantity);
            return productCartRepository.save(cart);
        }
        return null;
    }

    @Override
    public void removeFromCart(Long id) {
        productCartRepository.deleteById(id);
    }
}
