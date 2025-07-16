package com.data.session07.controller;

import com.data.session07.model.dto.res.DataResponse;
import com.data.session07.model.entity.User;
import com.data.session07.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<DataResponse<List<User>>> getAllFruits() {
        return ResponseEntity.ok(new DataResponse<>(userService.findAll(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<User>> addUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new DataResponse<>(savedUser, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return ResponseEntity.ok(new DataResponse<>(updatedUser, HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<?>> deleteUser(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DataResponse<>("User not found", HttpStatus.NOT_FOUND));
        }

        if (user.getOrders() != null && !user.getOrders().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DataResponse<>("Cannot delete user with existing orders", HttpStatus.BAD_REQUEST));
        }

        userService.deleteById(id);
        return ResponseEntity.ok(new DataResponse<>(null, HttpStatus.OK));
    }

}
