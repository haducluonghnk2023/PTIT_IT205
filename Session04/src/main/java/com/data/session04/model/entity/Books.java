package com.data.session04.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String bookName;
    private String author;
    private String publisher;
    private Integer yearPublish;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "cate_book_id")
    private CategoryBook categoryBook;

}
