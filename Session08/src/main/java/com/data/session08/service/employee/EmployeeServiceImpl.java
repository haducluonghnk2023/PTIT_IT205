package com.data.session08.service.employee;

import com.data.session08.model.entity.Employee;
import com.data.session08.repo.EmployeeRepository;
import com.data.session08.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Long id, Employee employee) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existing.setFullname(employee.getFullname());
        existing.setPhone(employee.getPhone());
        existing.setAddress(employee.getAddress());
        existing.setPosition(employee.getPosition());
        existing.setSalary(employee.getSalary());

        return employeeRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }
}
