package com.data.session05.repo;

import com.data.session05.modal.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFullNameContainingIgnoreCase(String fullName);
    List<Student> findByAddressContainingIgnoreCase(String address);
    List<Student> findByClassName(String className);
}