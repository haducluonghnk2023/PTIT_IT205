package com.data.session08.advice_controller;

import com.data.session08.model.res.DataErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DataErrorResponse<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (ObjectError ob : ex.getBindingResult().getAllErrors()) {
            String fieldName = (ob instanceof FieldError) ? ((FieldError) ob).getField() : ob.getObjectName();
            String errorMessage = ob.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }

        DataErrorResponse<Map<String, String>> errorResponse = new DataErrorResponse<>(
                errors,
                HttpStatus.BAD_REQUEST,
                "Validation failed"
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<DataErrorResponse<String>> handleRuntimeException(RuntimeException ex) {
        DataErrorResponse<String> errorResponse = new DataErrorResponse<>(
                null,
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
