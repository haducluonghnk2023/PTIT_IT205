package com.data.session04.services;

import com.data.session04.model.entity.CategoryBook;

import java.util.List;

public interface CategoryBookService {
    List<CategoryBook> findAll();
    CategoryBook findById(Long id);
    CategoryBook save(CategoryBook categoryBook);
    void deleteById(Long id);
}

