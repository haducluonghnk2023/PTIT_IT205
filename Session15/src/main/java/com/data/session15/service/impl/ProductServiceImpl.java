package com.data.session15.service.impl;

import com.data.session15.modal.dto.req.ProductDTO;
import com.data.session15.modal.entity.Category;
import com.data.session15.modal.entity.Product;
import com.data.session15.repository.ProductRepository;
import com.data.session15.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> findProductsByProductNameContainingIgnoreCase(String productName) {
        return productRepository.findProductsByProductNameContainingIgnoreCase(productName);
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategory_CategoryId(categoryId);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product product = Product.builder()
                .productName(productDTO.getProductName())
                .producer(productDTO.getProducer())
                .yearMaking(productDTO.getYearMaking())
                .expireDate(productDTO.getExpireDate())
                .quantity(productDTO.getQuantity())
                .price(productDTO.getPrice())
                .build();
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setProductName(productDTO.getProductName());
        product.setProducer(productDTO.getProducer());
        product.setYearMaking(productDTO.getYearMaking());
        product.setExpireDate(productDTO.getExpireDate());
        product.setQuantity(productDTO.getQuantity());
        product.setPrice(productDTO.getPrice());
        product.setCategory(mapCategory(productDTO.getCategoryId()));
        return productRepository.save(product);
    }

    private Category mapCategory(Long categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        return category;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
