package com.data.session11.service.impl;

import com.data.session11.model.dto.req.ProductRequest;
import com.data.session11.model.entity.Product;
import com.data.session11.repository.ProductRepository;
import com.data.session11.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setDescription(request.getDescription());

        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, ProductRequest request) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            ProductRequest productRequest = new ProductRequest();
            productRequest.setName(request.getName());
            productRequest.setPrice(request.getPrice());
            productRequest.setQuantity(request.getQuantity());
            productRepository.save(product.get());
            return product.get();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
