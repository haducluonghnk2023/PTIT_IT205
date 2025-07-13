package com.data.session04.services.impl;

import com.data.session04.model.entity.CategoryBook;
import com.data.session04.repository.CategoryBookRepository;
import com.data.session04.services.CategoryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryBookServiceImpl implements CategoryBookService {
    @Autowired
    private CategoryBookRepository repository;

    public List<CategoryBook> findAll() { return repository.findAll(); }
    public CategoryBook findById(Long id) { return repository.findById(id).orElse(null); }
    public CategoryBook save(CategoryBook categoryBook) { return repository.save(categoryBook); }
    public void deleteById(Long id) { repository.deleteById(id); }
}

