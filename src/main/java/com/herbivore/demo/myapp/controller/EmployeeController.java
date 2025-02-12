package com.herbivore.demo.myapp.controller;

import com.herbivore.demo.myapp.entity.Employee;
import com.herbivore.demo.myapp.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	// @Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/list")
	@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
	public String listEmployees(Model model) {
		List<Employee> employees = employeeService.findAll();
		model.addAttribute("employees", employees);

		return "list-employees";
	}
}
