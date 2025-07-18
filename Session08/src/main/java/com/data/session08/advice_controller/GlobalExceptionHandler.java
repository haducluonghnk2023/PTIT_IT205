package com.data.session08.advice_controller;

import jakarta.validation.ConstraintViolationException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 1. MethodArgumentNotValidException - lỗi validation @Valid trong DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    // 2. ConstraintViolationException - lỗi vi phạm ràng buộc trong @Validated
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String field = violation.getPropertyPath().toString();
            errors.put(field, violation.getMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    // 3. NoSuchElementException - không tìm thấy phần tử
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElement(NoSuchElementException ex) {
        Map<String, String> error = Map.of("error", "Không tìm thấy phần tử yêu cầu.");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // 4. DateTimeParseException - lỗi khi parse ngày giờ
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<?> handleDateTimeParseException(DateTimeParseException ex) {
        Map<String, String> error = Map.of("error", "Định dạng ngày giờ không hợp lệ.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // 5. NoResourceFoundException - lỗi tài nguyên tùy chỉnh không tồn tại
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleNoResourceFoundException(NoResourceFoundException ex) {
        Map<String, String> error = Map.of("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // 6. BadRequestException - lỗi yêu cầu không hợp lệ
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex) {
        Map<String, String> error = Map.of("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
