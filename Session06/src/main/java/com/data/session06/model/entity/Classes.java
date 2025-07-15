package com.data.session06.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name = "classes")
@Entity
@Getter
@Setter
@Data
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;

    private String className;
    private String status;

    @OneToMany(mappedBy = "classes")
    @JsonManagedReference
    private List<Students> students;
}
