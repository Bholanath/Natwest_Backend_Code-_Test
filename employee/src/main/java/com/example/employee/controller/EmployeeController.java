package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/1.0", produces = APPLICATION_JSON_VALUE)
@Slf4j
public class EmployeeController {
	
	@Autowired
    private  EmployeeService employeeService;

    @GetMapping("/{employeeNumber}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("employeeNumber") String employeeNumber) {
        Employee employee = employeeService.getEmployee(employeeNumber);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees() {
    	
    	List<Employee> empList = employeeService.getAllEmployees();
    	
    	if (empList != null && empList.size()>0) {
            return new ResponseEntity<>(empList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping(path = "/update", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
    	Employee updatedEmploye = employeeService.updateEmployee(employee);
		return ResponseEntity.ok(updatedEmploye);
    }
    
    @PutMapping(path = "/search", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> searchEmployee(@RequestBody Employee employee){
    	
    	Employee emp = employeeService.searchEmployee(employee);
    	
    	if (emp != null) {
            return new ResponseEntity<>(emp, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
}
