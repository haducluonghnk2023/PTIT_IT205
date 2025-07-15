package com.data.session06.service.book;

import com.data.session06.model.entity.Book;
import com.data.session06.repository.BookRepository;
import com.data.session06.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        Optional<Book> optionalFruit = bookRepository.findById(id);
        if (optionalFruit.isPresent()) {
            Book b = optionalFruit.get();
            b.setTitle(book.getTitle());
            b.setAuthor(book.getAuthor());
            b.setPrice(book.getPrice());
            return bookRepository.save(b);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
