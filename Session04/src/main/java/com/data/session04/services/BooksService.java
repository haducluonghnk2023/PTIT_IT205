package com.data.session04.services;

import com.data.session04.model.entity.Books;

import java.util.List;

public interface BooksService {
    List<Books> findAll();
    Books findById(Long id);
    Books save(Books book);
    void deleteById(Long id);
    List<Books> findByCategoryBookId(Long cateBookId);
}
