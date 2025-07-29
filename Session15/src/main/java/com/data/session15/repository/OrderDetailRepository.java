package com.data.session15.repository;

import com.data.session15.modal.entity.OrderDetail;
import com.data.session15.modal.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query("SELECT DISTINCT od.product FROM OrderDetail od")
    List<Product> findSoldProducts();

}

