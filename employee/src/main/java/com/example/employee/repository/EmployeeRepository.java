package com.example.employee.repository;

import com.example.employee.repository.entity.EmployeeEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity> findByEmployeeNumber(String employeeNumber);
    
    @Query("select employee from EmployeeEntity employee where employee.firstname=:firstname and employee.lastName=:lastName")
    Optional<EmployeeEntity> findEmployeeByFistNameAndLastName(String firstName,String lastName);
    
    @Query("select employee from EmployeeEntity employee where employee.firstname=:firstname or employee.lastName=:lastName")
    Optional<EmployeeEntity> findEmployeeByFistNameORLastName(String firstName,String lastName);
}
