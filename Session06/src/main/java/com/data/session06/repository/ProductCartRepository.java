package com.data.session06.repository;

import com.data.session06.model.entity.ProductCart;
import com.data.session06.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart,Long> {
    List<ProductCart> findByUser(User user);
}
