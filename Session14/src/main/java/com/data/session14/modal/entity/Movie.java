package com.data.session14.modal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title",length = 100,nullable = false,unique = true)
    private String title;
    @Column(name = "description",length = 200,nullable = false)
    private String description;
    @Column(name = "duration",length = 50,nullable = false)
    private Integer duration;
    @Column(name = "releasedDate",length = 50,nullable = false)
    private Date releasedDate;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShowTime> showtimes;
}
