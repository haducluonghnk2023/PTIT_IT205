package com.data.session15.controller;

import com.data.session15.modal.dto.res.ApiResponse;
import com.data.session15.modal.entity.User;
import com.data.session15.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {
    @Autowired
    private UserService userService;

    // Lấy danh sách tất cả người dùng
    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        return ResponseEntity.ok(new ApiResponse<>("Thành công", userService.getAllUsers(), true, HttpStatus.OK));
    }

    // Khóa/Mở khóa người dùng
    @PutMapping("/{userId}/status")
    public ResponseEntity<?> updateUserStatus(@PathVariable Long userId,
                                              @RequestParam boolean enabled) {
        userService.toggleUserStatus(userId, enabled);
        return ResponseEntity.ok("Cập nhật trạng thái thành công");
    }

    // Tìm kiếm người dùng theo tên
    @GetMapping("/search")
    public ResponseEntity<?> searchUsers(@RequestParam String keyword) {
        return ResponseEntity.ok(userService.searchUsersByName(keyword));
    }

    // Lấy danh sách quyền
    @GetMapping("/roles")
    public ResponseEntity<?> getAllRoles() {
        return ResponseEntity.ok(userService.getAllRoles());
    }
}
