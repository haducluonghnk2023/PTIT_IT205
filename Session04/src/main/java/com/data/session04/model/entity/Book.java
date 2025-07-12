package com.data.session04.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id",length = 45)
    private Long bookId;
    @Column(name = "title",length = 100,nullable = false,unique = true)
    private String title;
    @Column(name = "author",length = 100,nullable = false)
    private String author;
    private Date publishDate;
    private Date year;
}
