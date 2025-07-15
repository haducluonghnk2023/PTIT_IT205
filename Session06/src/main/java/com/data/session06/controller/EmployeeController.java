package com.data.session06.controller;

import com.data.session06.model.dto.res.DataResponse;
import com.data.session06.model.entity.Employee;
import com.data.session06.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Employee>>> getAllBooks() {
        return ResponseEntity.ok(new DataResponse<>(employeeService.findAll(), HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Employee>> getEmployeeId(@PathVariable Long id) {
        Employee e = employeeService.findById(id);
        if (e != null) {
            return ResponseEntity.ok(new DataResponse<>(e, HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataResponse<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Employee>> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(new DataResponse<>(employeeService.createEmployee(employee), HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Employee>> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        return ResponseEntity.ok(new DataResponse<>(employeeService.updateEmployee(id, employee), HttpStatus.OK));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DataResponse<Employee>> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok(new DataResponse<>(null, HttpStatus.OK));
    }
}
