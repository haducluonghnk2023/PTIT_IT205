package com.data.session15.repository;

import com.data.session15.modal.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findProductsByProductNameContainingIgnoreCase(String productName);
    List<Product> findByCategory_CategoryId(Long categoryId);

}
