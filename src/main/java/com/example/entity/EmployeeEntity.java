package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name ="employee")
public class EmployeeEntity {

	@Column(name = "employee_id")
	@Id
	private String employeeId;
	
	@Column(name = "employee_name")
	private String name ;
	
	@Column(name = "salary")
	private String salary;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "designation")
	private String designation ;
	
	@Column(name = "primary_skill")
	private String primarySkill ;

	public EmployeeEntity(String employeeId, String name, String salary, String address, String designation,
			String primarySkill) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.salary = salary;
		this.address = address;
		this.designation = designation;
		this.primarySkill = primarySkill;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPrimarySkill() {
		return primarySkill;
	}

	public void setPrimarySkill(String primarySkill) {
		this.primarySkill = primarySkill;
	}

	public EmployeeEntity() {
		super();
	}

	@Override
	public String toString() {
		return "EmployeeEntity [employeeId=" + employeeId + ", name=" + name + ", salary=" + salary + ", address="
				+ address + ", designation=" + designation + ", primarySkill=" + primarySkill + "]";
	}
	
	
	
}
