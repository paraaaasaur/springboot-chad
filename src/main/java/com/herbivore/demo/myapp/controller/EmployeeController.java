package com.herbivore.demo.myapp.controller;

import com.herbivore.demo.myapp.entity.Employee;
import com.herbivore.demo.myapp.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.herbivore.demo.myapp.util.Trivial.DACREW;

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
		List<Employee> employees = employeeService.findAllByOrderByLastNameAsc();
		model.addAttribute("employees", employees);

		return "employees/list-employees";
	}

	@GetMapping("/add")
	public String addEmployeeForm(Model model) {
		model.addAttribute("employee", DACREW.get(0));
		return "employees/add-employee-form";
	}

	@PostMapping("/add")
	public String addEmployee(@ModelAttribute Employee employee) {
		Employee saved = employeeService.save(employee);
		System.out.println(saved);
		//TODO: validation
		return "redirect:list";
	}
}
