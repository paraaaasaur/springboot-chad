# EXTRA: Validation 

* ‼️ JPA CRUD also fails if the validation fails in the first place, so the following doesn't work:
```java
	@PostMapping("/add")
	public String addEmployee(
			@Valid @ModelAttribute("employee") Employee employee,
			BindingResult bindingResult
	) {
	    // works when Spring validation is okay
        // throws exceptions when Spring validation fails
	    employeeService.save(employee);
		return bindingResult.hasErrors()? 
				"employees/add-employee-form" :
				"redirect:list";
	}
```