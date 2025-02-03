package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

	List<Employee> findAll();

	Employee findById(int id);

	Employee save(Employee employee);

	void deleteById(int id);

	void test();
}
