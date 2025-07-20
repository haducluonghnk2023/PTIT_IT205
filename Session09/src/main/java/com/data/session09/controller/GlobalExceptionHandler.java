package com.data.session09.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        log.error("Đã xảy ra lỗi: {}", ex.getMessage());
        return ResponseEntity.internalServerError().body("Đã xảy ra lỗi: " + ex.getMessage());
    }
}