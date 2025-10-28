package com.employee_data.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee_data.employee.model.Employee;
import com.employee_data.employee.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return repository.findById(id);
    }

    public Employee createEmployee(Employee employee) {
        if (repository.findByEmail(employee.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists: " + employee.getEmail());
        }
        return repository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));

        if (!existingEmployee.getEmail().equals(employee.getEmail()) &&
                repository.findByEmail(employee.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists: " + employee.getEmail());
        }

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDesignation(employee.getDesignation());
        existingEmployee.setSalary(employee.getSalary());

        return repository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Employee not found with id: " + id);
        }
        repository.deleteById(id);
    }
}