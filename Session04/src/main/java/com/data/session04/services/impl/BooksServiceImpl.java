package com.data.session04.services.impl;

import com.data.session04.model.entity.Books;
import com.data.session04.repository.BooksRepository;
import com.data.session04.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {
    @Autowired
    private BooksRepository repository;

    public List<Books> findAll() { return repository.findAll(); }
    public Books findById(Long id) { return repository.findById(id).orElse(null); }
    public Books save(Books book) { return repository.save(book); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public List<Books> findByCategoryBookId(Long cateBookId) {
        return repository.findByCategoryBook_CateBookId(cateBookId);
    }
}
