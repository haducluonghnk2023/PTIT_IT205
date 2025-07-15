package com.data.session06.service;

import com.data.session06.model.entity.Students;

import java.util.List;

public interface StudentService {
    List<Students> findAll();
    Students findById(Long id);
    Students save(Students student);
    Students update(Long id, Students student);
    void delete(Long id);
}
