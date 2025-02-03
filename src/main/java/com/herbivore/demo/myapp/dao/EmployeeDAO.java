package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

	List<Employee> findAll();

	void test();
}
