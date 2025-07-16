package com.data.session06.controller;

import com.data.session06.model.dto.res.DataResponse;
import com.data.session06.model.entity.User;
import com.data.session06.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<DataResponse<List<User>>> getAllUsers() {
        return  ResponseEntity.ok(new DataResponse<>(userService.getAllUsers(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<DataResponse<User>> createUser(@RequestBody User user) {
        return  ResponseEntity.ok(new DataResponse<>(userService.createUser(user), HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<User>> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return  ResponseEntity.ok(new DataResponse<>(userService.updateUser(id, user), HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<User>> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new DataResponse<>(userService.getUserById(id), HttpStatus.OK));
    }
}
