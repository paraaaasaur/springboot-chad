package com.herbivore.demo.myapp.service;

import com.herbivore.demo.myapp.entity.Employee;

import java.util.List;

public interface EmployeeService {

	List<Employee> findAll();

	Employee findById(int id);

	void save(Employee employee);

	void deleteById(int id);

}
