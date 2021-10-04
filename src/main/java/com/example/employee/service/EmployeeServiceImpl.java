package com.example.employee.service;

import com.example.employee.error.EmployeeNotFoundException;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.repository.entity.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployee(String employeeNumber) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findByEmployeeNumber(employeeNumber);
        if (employeeEntity.isPresent()) {
            return mapEmployee(employeeEntity.get());
        }
        throw new EmployeeNotFoundException("Employee not found for employeeNumber:" + employeeNumber);
    }

    private Employee mapEmployee(EmployeeEntity employeeEntity) {
        return new Employee(employeeEntity.getEmployeeNumber(), employeeEntity.getFirstname(), employeeEntity.getLastname());
    }
    
    private EmployeeEntity getEmployeeEntity(Employee employee) {
    	EmployeeEntity entity = new EmployeeEntity();
    	entity.setFirstname(employee.getFirstName());
    	entity.setLastname(employee.getLastName());
    	entity.setEmployeeNumber(employee.getUserId());
        return entity;
    }

	@Override
	public List<Employee> getAllEmployees() {
		
		List<Employee> employeeList  = new ArrayList<Employee>();
		Iterable<EmployeeEntity> empList = employeeRepository.findAll();
		empList.forEach((emp)->employeeList.add(mapEmployee(emp)));
		return employeeList;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		
		Optional<EmployeeEntity> empRef = employeeRepository.findByEmployeeNumber(employee.getUserId());
		EmployeeEntity emp = null;
		if(empRef.isPresent()) {
			emp = empRef.get();
			emp.setFirstname(employee.getFirstName());
			emp.setLastname(employee.getLastName());
		} else {
			emp  = getEmployeeEntity(employee);
		}
		 return mapEmployee(employeeRepository.save(emp));
	}

	@Override
	public Employee searchEmployee(Employee employee) {
		
		Employee emp = null;
		Optional<EmployeeEntity> optinalEmployee = Optional.empty();
		if(employee.getFirstName()!= null && employee.getLastName()!= null) {
			optinalEmployee = employeeRepository.findEmployeeByFistNameAndLastName(employee.getFirstName(), employee.getLastName());
		} else {
			optinalEmployee = employeeRepository.findEmployeeByFistNameORLastName(employee.getFirstName(), employee.getLastName());
		}
		
		if(optinalEmployee.isPresent()) {
			emp = mapEmployee(optinalEmployee.get());
		}
		
		return emp;
	}
}
