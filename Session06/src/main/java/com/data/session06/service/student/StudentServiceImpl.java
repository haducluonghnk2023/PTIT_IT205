package com.data.session06.service.student;

import com.data.session06.model.entity.Students;
import com.data.session06.repository.StudentRepository;
import com.data.session06.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository  studentRepository;
    @Override
    public List<Students> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Students findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Students save(Students student) {
        return studentRepository.save(student);
    }

    @Override
    public Students update(Long id, Students student) {
        Students s = findById(id);
        if (s != null) {
            s.setFullName(student.getFullName());
            s.setGender(student.getGender());
            s.setBirthDay(student.getBirthDay());
            s.setAddress(student.getAddress());
            s.setClasses(student.getClasses());
            return studentRepository.save(s);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
