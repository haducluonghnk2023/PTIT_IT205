package com.data.session15.repository;

import com.data.session15.modal.entity.ProductCart;
import com.data.session15.modal.entity.User;
import com.data.session15.modal.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {
    List<ProductCart> findByUser(User user);
    Optional<ProductCart> findByUserAndProduct(User user, Product product);
    void deleteByUserAndProduct(User user, Product product);
    void deleteByUser(User user);
}
