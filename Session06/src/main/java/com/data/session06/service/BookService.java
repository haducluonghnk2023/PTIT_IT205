package com.data.session06.service;

import com.data.session06.model.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book findById(Long id);
    Book createBook(Book book);
    Book updateBook(Long id,Book book);
    void deleteById(Long id);
}
