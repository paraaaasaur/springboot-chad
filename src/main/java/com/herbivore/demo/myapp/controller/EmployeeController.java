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
		//TODO
		model.addAttribute("blankEmp", new Employee());
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

	@GetMapping("/edit/{id}")
	public String editEmployee(
			@PathVariable int id,
			Model model
	) {
		Employee foundEmp = employeeService.findById(id);
		model.addAttribute("employee", foundEmp);
		return "employees/edit-employee-form";
	}

	/**
	 * This method is a duplicate of add version
	 * and the route should have been named "save"
	 * so that both INSERT and UPDATE fit, but it's
	 * fine because why not ( ^)o(^ )
	 **/
	@PostMapping("/edit")
	public String editEmployee(
			String uselessRoadBlockLUL,
			@Valid @ModelAttribute("employee") Employee employee,
			// BindingResult should go right after @Valid for Spring
			// to use reflection to invoke binding correctly
//			String uselessRoadBlockLUL,
			BindingResult bindingResult
	) {
		System.out.println(employee);
		System.err.println(bindingResult);

		if (bindingResult.hasErrors()) {
			return "employees/edit-employee-form";
		}

		// ‼️ JPA CRUD also fails if the validation fails in the first place
		Employee updated = employeeService.save(employee);
		System.out.println(cyan("> Updated " + updated));

		return "redirect:/employees/list";
	}

	@DeleteMapping("/delete")
	public String deleteEmployee(@RequestParam("id") int id) {
		System.out.println(cyan("> Deleting Employee id = " + id));
		employeeService.deleteById(id);

		return "redirect:/employees/list";
	}
}
