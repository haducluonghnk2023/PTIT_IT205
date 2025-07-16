package com.data.session07.service;

import com.data.session07.model.entity.ProductDetail;

import java.util.List;

public interface ProductDetailService {
    List<ProductDetail> findAll();
    ProductDetail findById(Long id);
    ProductDetail createProductDetail(ProductDetail productDetail);
    ProductDetail updateProductDetail(Long id, ProductDetail productDetail);
    void delete(Long id);
}
