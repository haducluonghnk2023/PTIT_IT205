package com.data.session06.service.product;

import com.data.session06.model.entity.Product;
import com.data.session06.repository.ProductRepository;
import com.data.session06.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public Page<Product> getAllProducts(Pageable pageable, String searchName) {
        if (searchName != null && !searchName.trim().isEmpty()) {
            return productRepository.findByNameContainingIgnoreCase(searchName, pageable);
        }
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product existing = optional.get();
            existing.setName(product.getName());
            existing.setPrice(product.getPrice());
            existing.setDescription(product.getDescription());
            existing.setStock(product.getStock());
            return productRepository.save(existing);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
