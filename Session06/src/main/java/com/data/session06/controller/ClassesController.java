package com.data.session06.controller;

import com.data.session06.model.dto.res.DataResponse;
import com.data.session06.model.entity.Classes;
import com.data.session06.model.entity.Students;
import com.data.session06.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("classes")
public class ClassesController {
    @Autowired
    private ClassService classService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Classes>>> getAllClasses() {
        return ResponseEntity.ok(new DataResponse<>(classService.findAll(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Classes>> createClass(@RequestBody Classes classes) {
        return ResponseEntity.ok(new DataResponse<>(classService.save(classes),HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Classes>> updateClass(@PathVariable Long id, @RequestBody Classes updatedClass) {
        Classes updated = classService.update(id, updatedClass);
        if (updated != null) {
            return ResponseEntity.ok(new DataResponse<>(updated, HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> deleteClass(@PathVariable Long id) {
        Classes existing = classService.findById(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DataResponse<>("Class not found", HttpStatus.NOT_FOUND));
        }

        // Nếu lớp học còn sinh viên thì không cho xóa
        if (existing.getStudents() != null && !existing.getStudents().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DataResponse<>("Không thể xóa lớp học vì vẫn còn sinh viên!", HttpStatus.BAD_REQUEST));
        }

        classService.delete(id);
        return ResponseEntity.ok(new DataResponse<>("Đã xóa lớp học thành công", HttpStatus.OK));
    }

}
