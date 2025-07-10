package com.data.session03.repository;

import com.data.session03.model.dto.EmployeeDTO;
import com.data.session03.model.entity.Employee;
import com.data.session03.model.entity.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public List<Employee> findByPhoneNumber(String phoneNumber);

    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    public List<Employee> findBySalary(double salary);

    @Query("select  new com.data.session03.model.dto.EmployeeDTO(e.id,e.name,e.salary)" + "from Employee e where e.salary > :minsalary")
    List<EmployeeDTO> findEmployeeDTOBySalaryGreaterThan(double salary);

    @Query("select e from Employee e")
    List<EmployeeInfo> findAllEmployeeInfo();

    @Query("SELECT e FROM Employee e WHERE e.salary > :minSalary")
    List<EmployeeInfo> findBySalaryGreaterThan(double minSalary);
}
