package com.data.session08.service;

import com.data.session08.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);
    Employee update(Long id, Employee employee);
    void delete(Long id);
    List<Employee> getAll();
}
