package com.data.session06.service;

import com.data.session06.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(Long id);
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long id,Employee employee);
    void deleteEmployee(Long id);
}
