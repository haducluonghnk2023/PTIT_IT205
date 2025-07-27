package com.data.session14.modal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name = "show_times")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ShowTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @Column(name = "startTime",length = 50,nullable = false)
    private Date startTime;
    @Column(name = "room",length = 50,nullable = false)
    private String room;

}
