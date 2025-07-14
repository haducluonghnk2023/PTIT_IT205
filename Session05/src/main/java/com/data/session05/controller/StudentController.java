package com.data.session05.controller;

import com.data.session05.modal.dto.res.DataResponse;
import com.data.session05.modal.dto.res.StudentResDTO;
import com.data.session05.modal.entity.Student;
import com.data.session05.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public ResponseEntity<List<Student>> getAllBooks() {
        List<Student> studentList = service.findAll();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Student>> getStudentById(@PathVariable Long id) {
        return service.findById(id)
                .map(student -> ResponseEntity.ok(new DataResponse<>(student, HttpStatus.OK)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataResponse<>(null, HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Student>> insertStudent(@RequestBody Student student) {
        Student saved = service.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DataResponse<>(saved, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Student>> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return service.findById(id)
                .map(existing -> {
                    student.setStudentId(id);
                    Student updated = service.save(student);
                    return ResponseEntity.ok(new DataResponse<>(updated, HttpStatus.OK));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataResponse<>(null, HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Void>> deleteStudent(@PathVariable Long id) {
        return service.findById(id)
                .map(existing -> {
                    service.deleteById(id);
                    return ResponseEntity.ok(new DataResponse<Void>(null, HttpStatus.NO_CONTENT));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataResponse<>(null, HttpStatus.NOT_FOUND)));
    }

    @GetMapping("/search/name")
    public ResponseEntity<DataResponse<List<Student>>> getByName(@RequestParam String name) {
        List<Student> result = service.findByName(name);
        return ResponseEntity.ok(new DataResponse<>(result, HttpStatus.OK));
    }

    @GetMapping("/search/address")
    public ResponseEntity<DataResponse<List<Student>>> getByAddress(@RequestParam String address) {
        List<Student> result = service.findByAddress(address);
        return ResponseEntity.ok(new DataResponse<>(result, HttpStatus.OK));
    }

    @GetMapping("/search/class")
    public ResponseEntity<DataResponse<List<Student>>> getByClass(@RequestParam String className) {
        List<Student> result = service.findByClassName(className);
        return ResponseEntity.ok(new DataResponse<>(result, HttpStatus.OK));
    }
}
