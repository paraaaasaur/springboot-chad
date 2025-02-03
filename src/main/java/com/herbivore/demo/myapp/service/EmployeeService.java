package com.herbivore.demo.myapp.service;

import com.herbivore.demo.myapp.entity.Employee;

import java.util.List;

/**
 * <h3>Service Facade Design Pattern</h3>
 * <li>Service layer is at a level higher than DAO
 * It wraps DAO(s), and can offer other business
 * logic.</li>
 * <li>Services, rather than raw DAOs, are the
 * ones to be used in the controllers.</li>
 **/
public interface EmployeeService {

	List<Employee> findAll();
}
