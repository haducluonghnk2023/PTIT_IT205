package com.data.session15.service.impl;

import com.data.session15.modal.dto.req.CategoryDTO;
import com.data.session15.modal.entity.Category;
import com.data.session15.repository.CategoryRepository;
import com.data.session15.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category createCategory(CategoryDTO category) {
        Category c = Category.builder()
                .categoryName(category.getCategoryName())
                .status(category.isStatus())
                .build();
        return categoryRepository.save(c);
    }

    @Override
    public Category updateCategory(Long id, CategoryDTO category) {
        Category c = categoryRepository.findById(id).orElse(null);
        if (c != null) {
            c.setCategoryName(category.getCategoryName());
            c.setStatus(category.isStatus());
            return categoryRepository.save(c);
        }
        return null;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
