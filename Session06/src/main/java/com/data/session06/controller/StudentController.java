package com.data.session06.controller;

import com.data.session06.model.dto.res.DataResponse;
import com.data.session06.model.entity.Students;
import com.data.session06.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    @Autowired
    private StudentService  studentService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Students>>> getAllStudents() {
        return ResponseEntity.ok(new DataResponse<>(studentService.findAll(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Students>> createStudent(@RequestBody Students student) {
        return ResponseEntity.ok(new DataResponse<>(studentService.save(student), HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Students>> updateStudent(@PathVariable("id") Long id, @RequestBody Students student) {
        return  ResponseEntity.ok(new DataResponse<>(studentService.update(id, student), HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Students>> deleteStudent(@PathVariable("id") Long id){
        studentService.delete(id);
        return ResponseEntity.ok(new DataResponse<>(studentService.findById(id), HttpStatus.OK));
    }
}
