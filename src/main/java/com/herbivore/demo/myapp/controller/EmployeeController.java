package com.herbivore.demo.myapp.controller;

import com.herbivore.demo.myapp.entity.Employee;
import com.herbivore.demo.myapp.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model model) {

		// get the employees from db
		List<Employee> dbEmployees = employeeService.findAll();

		// add to the spring model
		model.addAttribute("employees", dbEmployees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {

		// create model attribute to bind form data
		Employee employee = new Employee();

		model.addAttribute("employee", employee);

		return "employees/employee-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int id,
									Model model) {
		// get the employee from the service
		Employee employee = employeeService.findById(id);

		// set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);

		// send over to our form
		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.save(employee);

		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int id) {
		employeeService.deleteById(id);

		return "redirect:/employees/list";
	}
}









