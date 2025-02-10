package com.herbivore.demo.myapp.controller;

import com.herbivore.demo.myapp.model.Customer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

	@GetMapping("/")
	public String showForm(Model model) {

		model.addAttribute("customer", new Customer());

		return "customer-form";
	}

	@PostMapping("/processForm")
	public String processForm(
			@Valid @ModelAttribute("customer") Customer customer,
			BindingResult bindingResult
	) {
		System.out.println(customer);
		System.err.println(bindingResult);

		return bindingResult.hasErrors()?
				"customer-form" :
				"customer-confirmation";
	}
}
