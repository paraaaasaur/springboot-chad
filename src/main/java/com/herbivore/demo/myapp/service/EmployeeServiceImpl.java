package com.herbivore.demo.myapp.service;

import com.herbivore.demo.myapp.dao.EmployeeDAO;
import com.herbivore.demo.myapp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO;

	public EmployeeServiceImpl() {}

	@Override
	public List<Employee> findAll() {
		return employeeDAO.findAll();
	}

	@Override
	public Employee findById(int id) {
		return employeeDAO.findById(id);
	}

	@Transactional
	@Override
	public Employee save(Employee employee) {
		return employeeDAO.save(employee);
	}

	@Transactional
	@Override
	public void deleteById(int id) {
		employeeDAO.deleteById(id);
	}


	@Override
	public void test() {}

	@Autowired
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

}
