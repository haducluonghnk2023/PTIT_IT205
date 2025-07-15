package com.data.session06.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Table(name = "students")
@Entity
@Getter
@Setter
@Data
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String gender;
    private Date birthDay;
    private String address;

    @ManyToOne
    @JoinColumn(name = "class_id")
    @JsonBackReference
    private Classes classes;
}
