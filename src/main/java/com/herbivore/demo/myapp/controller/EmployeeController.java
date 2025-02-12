package com.herbivore.demo.myapp.controller;

import com.herbivore.demo.myapp.entity.Employee;
import com.herbivore.demo.myapp.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.herbivore.demo.myapp.util.Trivial.DACREW;
import static io.github.paraaaasaur.util.Toolbox.cyan;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		var ste = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, ste);
	}

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
	public String addEmployee(
			@Valid @ModelAttribute("employee") Employee employee,
			BindingResult bindingResult
	) {
		System.out.println(employee);
		System.err.println(bindingResult);

		if (bindingResult.hasErrors()) {
			return "employees/add-employee-form";
		}

		// ‼️ JPA CRUD also fails if the validation fails in the first place
		Employee saved = employeeService.save(employee);
		System.out.println(cyan("> Saved " + saved));

		return "redirect:list";
	}
}
