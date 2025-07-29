package com.data.session15.controller;

import com.data.session15.modal.dto.req.CategoryDTO;
import com.data.session15.modal.dto.res.ApiResponse;
import com.data.session15.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/categories")
@PreAuthorize("hasRole('ADMIN')")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(new ApiResponse<>("Thành công", categoryService.getAllCategories(), true, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>("Thành công", categoryService.getCategoryById(id), true, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO dto) {
        return ResponseEntity.ok(new ApiResponse<>("Thành công", categoryService.createCategory(dto), true, HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO dto) {
        return ResponseEntity.ok(new ApiResponse<>("Thành công", categoryService.updateCategory(id, dto), true, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new ApiResponse<>("Thành công", null, true, HttpStatus.OK));
    }
}
