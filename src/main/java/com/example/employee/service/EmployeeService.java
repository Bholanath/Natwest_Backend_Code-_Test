package com.example.employee.service;

import java.util.List;

import com.example.employee.model.Employee;

public interface EmployeeService {
    Employee getEmployee(String employeeNumber);

	List<Employee> getAllEmployees();

	Employee updateEmployee(Employee employee);

	Employee searchEmployee(Employee employee);
}
