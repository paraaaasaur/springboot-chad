package com.herbivore.demo.myapp.service;

import com.herbivore.demo.myapp.dao.EmployeeDAO;
import com.herbivore.demo.myapp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO;

	public EmployeeServiceImpl() {}

	@Override
	public List<Employee> findAll() {
		return employeeDAO.findAll();
	}

	@Autowired
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

}
