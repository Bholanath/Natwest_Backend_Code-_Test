package com.example.employee.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="EMPLOYEE")
public class EmployeeEntity {
    @Id
    @Column(name="ID", unique = true, nullable = false)
    private long id;
    @Column(name="EMPLOYEE_NUMBER", unique = true, nullable = false)
    private String employeeNumber;
    @Column(name="FIRSTNAME", nullable = false)
    private String firstname;
    @Column(name="LASTNAME", nullable = false)
    private String lastname;
}
