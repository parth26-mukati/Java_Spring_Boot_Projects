package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.EmployeeEntity;
import com.example.repo.EmployeeRepo;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	public EmployeeService(EmployeeRepo employeeRepo) {
		super();
		this.employeeRepo = employeeRepo;
	}

	public List<EmployeeEntity> getAllEmployees(){
		List<EmployeeEntity> list = (List<EmployeeEntity>) employeeRepo.findAll();
		return list;
	}
	
	public EmployeeEntity getEmployees(String id){
		return employeeRepo.findById(id).get();
	}
	
	//1. Done
	public void updateEmployeeSalary(String id,String salary) {
		EmployeeEntity e=employeeRepo.findById(id).get();
		e.setSalary(salary);
		employeeRepo.save(e);
	}
	
	//2. Done
	public void updateEmployeeDesignation(String id,String designation) {
		EmployeeEntity e=employeeRepo.findById(id).get();
		e.setDesignation(designation);
		employeeRepo.save(e);
	}
	
	//3. Done
	public void addEmployees(EmployeeEntity e) {
		employeeRepo.save(e);
	}
	
	//4. Done
	public void deleteEmployees(String id) {
		employeeRepo.deleteById(id);
	}
	
	//5. Done
	public void updateEmployeeAddress(String id,String address) {
		EmployeeEntity e=employeeRepo.findById(id).get();
		e.setAddress(address);
		employeeRepo.save(e);
	}

}
