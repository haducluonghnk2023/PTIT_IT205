package com.data.session06.controller;

import com.data.session06.model.dto.res.DataResponse;
import com.data.session06.model.entity.Book;
import com.data.session06.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public ResponseEntity<DataResponse<List<Book>>> getAllFruits() {
        return ResponseEntity.ok(new DataResponse<>(service.findAll(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Book>> addBook(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DataResponse<>(service.createBook(book), HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Book>> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updated = service.updateBook(id,book);
        if (updated != null) {
            return ResponseEntity.ok(new DataResponse<>(updated, HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Book>> deleteBook(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok(new DataResponse<>(null, HttpStatus.OK));
    }
}
