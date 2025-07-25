package com.data.session07.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "seeds")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer quantity;
}
