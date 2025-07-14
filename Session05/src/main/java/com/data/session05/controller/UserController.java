package com.data.session05.controller;

import com.data.session05.modal.dto.res.DataResponse;
import com.data.session05.modal.entity.User;
import com.data.session05.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // GET /users
    @GetMapping
    public ResponseEntity<DataResponse<List<User>>> getAllUsers() {
        List<User> result = userService.getAllUsers();
        return ResponseEntity.ok(new DataResponse<>(result, HttpStatus.OK));
    }

    // GET /users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<User>> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(new DataResponse<>(user, HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
    }

    // POST /users
    @PostMapping
    public ResponseEntity<DataResponse<User>> createUser(@RequestBody User user) {
        User created = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DataResponse<>(created, HttpStatus.CREATED));
    }

    // PUT /users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updated = userService.updateUser(id, user);
        if (updated != null) {
            return ResponseEntity.ok(new DataResponse<>(updated, HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
    }

    // DELETE /users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new DataResponse<>("Deleted successfully", HttpStatus.OK));
    }
}
