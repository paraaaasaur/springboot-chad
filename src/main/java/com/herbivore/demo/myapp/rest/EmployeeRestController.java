package com.herbivore.demo.myapp.rest;

import com.herbivore.demo.myapp.dao.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeDAO employeeDAO;

	@Autowired
	public EmployeeRestController(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
}
