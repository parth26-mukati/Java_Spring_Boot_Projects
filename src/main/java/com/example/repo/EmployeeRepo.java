package com.example.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.EmployeeEntity;

public interface EmployeeRepo extends CrudRepository<EmployeeEntity, String>{

}
