package com.employee_data.employee.repository;

import com.employee_data.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Custom query method to find an employee by email
    Optional<Employee> findByEmail(String email);
}