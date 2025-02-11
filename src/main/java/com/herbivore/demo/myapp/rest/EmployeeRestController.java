package com.herbivore.demo.myapp.rest;

import com.herbivore.demo.myapp.entity.Employee;
import com.herbivore.demo.myapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	// No more raw DAOs in the controllers. It's service!
	private EmployeeService employeeService;

	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {

		Employee foundEmployee = employeeService.findById(employeeId);

		if (foundEmployee == null)
			throw new RuntimeException(String.format(
					"Employee (id = %s) not found",
					employeeId
			));

		return foundEmployee;
	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody final Employee employee) {
		// So that JPA thinks "Oh, merging an id = 0 entity?
		// That means a new row INSERT to me!"
		employee.setId(0);

		Employee dbEmp = employeeService.save(employee);

		return dbEmp;
	}

	// PUT /employees: Full UPDATE via JSON request body,
	// even if in effect only one field or nothing changes.
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody final Employee employee) {
		// skip the case where it becomes INSERT rather than UPDATE
		Employee dbEmp = employeeService.save(employee);

		return dbEmp;
	}

	// PATCH /employees/{employeeId}: Partial UPDATE via key-value pairs
	// (Not query string but x-www-urlencoded)
	@PatchMapping("/employees/{employeeId}")
	public Employee updateEmployeeEmail(
			@PathVariable final int employeeId,
			@RequestParam(name = "email", required = false) Optional<String> newEmail
	) {

		Employee dbEmp = employeeService.findById(employeeId);
		dbEmp.setEmail(newEmail.orElse("<NOT PROVIDED>"));
		employeeService.save(dbEmp);

		return dbEmp;
	}

	// DELETE /employees/{employeeId}
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee foundEmp = employeeService.findById(employeeId);
		if (foundEmp == null)
			throw new RuntimeException("Employee (id = %s) not found"
					.formatted(employeeId));

		employeeService.deleteById(employeeId);

		return "Deleted " + foundEmp;
	}
}
