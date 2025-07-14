package com.data.session05.service.student;

import com.data.session05.modal.entity.Student;
import com.data.session05.repo.StudentRepository;
import com.data.session05.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> findByName(String name) {
        return studentRepository.findByFullNameContainingIgnoreCase(name);
    }

    @Override
    public List<Student> findByAddress(String address) {
        return studentRepository.findByAddressContainingIgnoreCase(address);
    }

    @Override
    public List<Student> findByClassName(String className) {
        return studentRepository.findByClassName(className);
    }
}
