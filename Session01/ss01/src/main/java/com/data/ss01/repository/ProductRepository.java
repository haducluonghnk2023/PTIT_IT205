package com.data.ss01.repository;


import com.data.ss01.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product, Integer> {
}
