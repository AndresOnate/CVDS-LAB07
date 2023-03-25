package com.tobias.saul.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employeeId;
	private String firstName;
	private String lastName;
	private String role;
	private Double salary;


	public Long getEmployeeId(){
		return this.employeeId;
	}

	public String getFirstName(){
		return this.firstName;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getLastName(){
		return this.lastName;
	}

	public String getRole(){
		return this.role;
	}

	public Double getSalary(){
		return this.salary;
	}

	

}
