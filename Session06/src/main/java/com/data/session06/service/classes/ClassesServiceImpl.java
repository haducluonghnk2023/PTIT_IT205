package com.data.session06.service.classes;

import com.data.session06.model.entity.Classes;
import com.data.session06.repository.ClassRepository;
import com.data.session06.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassesServiceImpl implements ClassService {
    @Autowired
    private ClassRepository  classRepository;
    @Override
    public List<Classes> findAll() {
        return classRepository.findAll();
    }

    @Override
    public Classes findById(Long id) {
        return classRepository.findById(id).orElse(null);
    }

    @Override
    public Classes save(Classes classes) {
        return classRepository.save(classes);
    }

    @Override
    public Classes update(Long id, Classes classes) {
        Classes c = findById(id);
        if (c != null) {
            c.setClassName(classes.getClassName());
            c.setStatus(classes.getStatus());
            return classRepository.save(c);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        classRepository.deleteById(id);
    }
}
