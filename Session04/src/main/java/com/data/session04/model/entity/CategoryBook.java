package com.data.session04.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "category_book")
public class CategoryBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cateBookId;

    private String cateBookName;

    @OneToMany(mappedBy = "categoryBook", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Books> books;

}
