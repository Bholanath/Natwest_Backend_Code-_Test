package com.example.employee.model;

import lombok.Value;

@Value
public class Employee {
    String userId;
    String firstName;
    String lastName;
    
	public String getUserId() {
		return userId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
    
    
}
