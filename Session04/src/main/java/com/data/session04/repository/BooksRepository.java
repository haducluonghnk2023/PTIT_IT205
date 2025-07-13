package com.data.session04.repository;

import com.data.session04.model.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksRepository extends JpaRepository<Books, Long> {
    List<Books> findByCategoryBook_CateBookId(Long cateBookId);
}
