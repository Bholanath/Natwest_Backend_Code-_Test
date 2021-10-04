package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService service;
    
    List<Employee> empList = null;
    
    @Before
    public void setUp() {
    	 empList = new ArrayList<Employee>();
    	 String employeeNumber = "001004";
         Employee employee1 = new Employee(employeeNumber, "Rahul", "kumar");
         employeeNumber = "001005";
         Employee employee2 = new Employee(employeeNumber, "Akash", "chopra");
         employeeNumber = "001006";
         Employee employee3 = new Employee(employeeNumber, "Sachin", "kumar");
         empList.add(employee1);
         empList.add(employee2);
         empList.add(employee3);
    }

    @Test
    public void shouldGetEmployeeForTheGivenEmployeeId() {
        String employeeNumber = "001003";
        Employee employee = new Employee(employeeNumber, "firstname", "lastname");
        when(service.getEmployee(employeeNumber)).thenReturn(employee);
        ResponseEntity<Employee> employeeResponseEntity = this.employeeController.getEmployee(employeeNumber);
        assertEquals(HttpStatus.OK, employeeResponseEntity.getStatusCode());
        assertEquals(employee, employeeResponseEntity.getBody());
    }

    @Test
    public void shouldGetEmployeeNotFoundException() {
        String employeeNumber = "001001";
        when(service.getEmployee(employeeNumber)).thenReturn(null);
        ResponseEntity<Employee> employeeResponseEntity = this.employeeController.getEmployee(employeeNumber);
        assertEquals(HttpStatus.NOT_FOUND, employeeResponseEntity.getStatusCode());
    }
    
    @Test
    public void shouldGetAllEmployee() {
    	when(service.getAllEmployees()).thenReturn(empList);
    	ResponseEntity<List<Employee>> empListResponseEntity = this.employeeController.getEmployees();
    	 assertEquals(HttpStatus.OK, empListResponseEntity.getStatusCode());
         assertEquals(3, empListResponseEntity.getBody().size());
    	
    }
    @Test
    public void shouldGetNoEmployees() {
    	when(service.getAllEmployees()).thenReturn(new ArrayList<Employee>());
    	ResponseEntity<List<Employee>> empListResponseEntity = this.employeeController.getEmployees();
    	 assertEquals(HttpStatus.NOT_FOUND, empListResponseEntity.getStatusCode());
    }
    
    @Test
    public void updateEmployee() {
    	String employeeNumber = "001003";
    	Employee employee1 = new Employee(employeeNumber, "Rahul", "kumar");
    	when(service.updateEmployee(employee1)).thenReturn(employee1);
    	ResponseEntity<Employee> empResEntity = this.employeeController.updateEmployee(employee1);
    	assertEquals(HttpStatus.OK, empResEntity.getStatusCode());
    	assertEquals("Rahul", empResEntity.getBody().getFirstName());
    	assertEquals("kumar", empResEntity.getBody().getLastName());
    }
    
    @Test
    public void searchEmployeeByFirstAndLastName() {
    	
    	String employeeNumber = "001005";
    	Employee employee2 = new Employee(employeeNumber, "Akash", "chopra");
    	when(service.searchEmployee(employee2)).thenReturn(employee2);
    	ResponseEntity<Employee> empResEntity = this.employeeController.searchEmployee(employee2);
    	assertEquals(HttpStatus.OK, empResEntity.getStatusCode());
    	assertEquals("Akash", empResEntity.getBody().getFirstName());
    	assertEquals("chopra", empResEntity.getBody().getLastName());
    	
    }
    
    @Test
    public void searchEmployeeByFirstOrLastName() {
    	
    	String employeeNumber = "001006";
    	Employee employee3 = new Employee(employeeNumber, "Akash", null);
    	when(service.searchEmployee(employee3)).thenReturn(employee3);
    	ResponseEntity<Employee> empResEntity = this.employeeController.searchEmployee(employee3);
    	assertEquals(HttpStatus.OK, empResEntity.getStatusCode());
    	assertEquals("Akash", empResEntity.getBody().getFirstName());
    	assertEquals(employeeNumber, empResEntity.getBody().getUserId());
    	
    	String employeeNumber7 = "001007";
    	Employee employee4 = new Employee(employeeNumber7, null, "Das");
    	when(service.searchEmployee(employee4)).thenReturn(employee4);
    	ResponseEntity<Employee> empResEntity2 = this.employeeController.searchEmployee(employee4);
    	assertEquals(HttpStatus.OK, empResEntity2.getStatusCode());
    	assertEquals(employeeNumber7, empResEntity.getBody().getUserId());
    	
    	
    }
}
