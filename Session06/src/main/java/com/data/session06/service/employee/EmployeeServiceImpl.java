package com.data.session06.service.employee;

import com.data.session06.model.entity.Employee;
import com.data.session06.repository.EmployeeRepository;
import com.data.session06.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Optional<Employee> emp = employeeRepository.findById(id);
        if(emp.isPresent()) {
            Employee e =  emp.get();
            e.setName(employee.getName());
            e.setEmail(employee.getEmail());
            e.setPosition(employee.getPosition());
            e.setSalary(employee.getSalary());
            return employeeRepository.save(e);
        }
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
