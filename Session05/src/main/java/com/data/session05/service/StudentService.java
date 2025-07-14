package com.data.session05.service;

import com.data.session05.modal.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();
    Optional<Student> findById(Long id);
    Student save(Student student);
    void deleteById(Long id);
    List<Student> findByName(String name);
    List<Student> findByAddress(String address);
    List<Student> findByClassName(String className);
}
