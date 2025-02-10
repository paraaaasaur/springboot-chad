package com.herbivore.demo.myapp.controller;

import com.herbivore.demo.myapp.model.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Locale;

@Controller
public class CustomerController {


	/**
	 * <ol>
	 * <li>{@code @InitBinder} provides data customizations,
	 * and applies to all routing methods under the same
	 * {@code @Controller}</li>
	 * <li>Paired with {@code WebDataBinder} in
	 * param in use.</li>
	 * <li>Deals with incoming data from {@code @ModelAttribute}
	 * and {@code @RequestParam}</li>
	 * </ol>
	 **/
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		var ste = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, ste);
	}

	@GetMapping("/")
	public String showForm(Model model) {

		model.addAttribute("customer", new Customer());

		return "customer-form";
	}

	@PostMapping("/processForm")
	public String processForm(
			@Valid @ModelAttribute("customer") Customer customer,
			BindingResult bindingResult,
			Locale locale
	) {
		System.out.println(customer);
		System.err.println(bindingResult);
		System.out.println(locale);

		return bindingResult.hasErrors()?
				"customer-form" :
				"customer-confirmation";
	}
}
