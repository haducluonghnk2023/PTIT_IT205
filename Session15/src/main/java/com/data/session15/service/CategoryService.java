package com.data.session15.service;

import com.data.session15.modal.dto.req.CategoryDTO;
import com.data.session15.modal.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category createCategory(CategoryDTO category);
    Category updateCategory(Long id, CategoryDTO category);
    void deleteCategory(Long id);
}
