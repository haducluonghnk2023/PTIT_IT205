package com.data.session07.service.product_detail;

import com.data.session07.model.entity.ProductDetail;
import com.data.session07.repository.ProductDetailRepository;
import com.data.session07.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Override
    public List<ProductDetail> findAll() {
        return productDetailRepository.findAll();
    }

    @Override
    public ProductDetail findById(Long id) {
        return productDetailRepository.findById(id).orElse(null);
    }

    @Override
    public ProductDetail createProductDetail(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }

    @Override
    public ProductDetail updateProductDetail(Long id, ProductDetail productDetail) {
        Optional<ProductDetail> productDetailOptional = productDetailRepository.findById(id);
        if (productDetailOptional.isPresent()) {
            ProductDetail pd = productDetailOptional.get();
            pd.setColor(productDetail.getColor());
            pd.setSize(productDetail.getSize());
            pd.setPrice(productDetail.getPrice());
            pd.setYearMaking(productDetail.getYearMaking());
            return  productDetailRepository.save(pd);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        productDetailRepository.deleteById(id);
    }
}
