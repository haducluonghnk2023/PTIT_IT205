package com.data.session06.service;

import com.data.session06.model.entity.Classes;

import java.util.List;

public interface ClassService {
    List<Classes> findAll();
    Classes findById(Long id);
    Classes save(Classes classes);
    Classes update(Long id, Classes classes);
    void delete(Long id);
}
