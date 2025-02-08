package com.herbivore.demo.myapp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

	// Show initial HTML form
	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form";
	}

	// Process the HTML form
	@GetMapping("/processForm")
	public String processForm() {
		return "helloworld";
	}

	/**
	 * <ol>
	 *	 <li>Read form data</li>
	 *	 <li>Add data to the model</li>
	 * </ol>
	 * key: Make use of {@code HttpServletRequest} and {@code Model}
	 **/
	@GetMapping("/processFormV2")
	public String processFormV2(
			HttpServletRequest request,
			Model model
	) {
		String name = request.getParameter("studentName");
		String message = "Greetings, " + name.toUpperCase() + "!";

		model.addAttribute("message", message);

		return "helloworld";
	}

	/** Use {@code @RequestParam} instead of
	 * {@code HttpServletRequest}in this example
	 **/
	@GetMapping("/processFormV3")
	public String processFormV3(
			@RequestParam("studentName") String name,
			Model model
	) {
		String message = "Hail, " + name.toUpperCase() + "!";

		model.addAttribute("message", message);

		return "helloworld";
	}
}
