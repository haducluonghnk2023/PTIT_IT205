package com.data.session04.repository;

import com.data.session04.model.entity.CategoryBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryBookRepository extends JpaRepository<CategoryBook, Long> {
}
