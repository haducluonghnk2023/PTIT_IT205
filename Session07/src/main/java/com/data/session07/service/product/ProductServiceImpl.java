package com.data.session07.service.product;

import com.data.session07.model.entity.Product;
import com.data.session07.repository.ProductRepository;
import com.data.session07.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
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
            Product oldProduct = optional.get();
            oldProduct.setProductName(product.getProductName());
            oldProduct.setProducerName(product.getProducerName());
            return productRepository.save(oldProduct);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
