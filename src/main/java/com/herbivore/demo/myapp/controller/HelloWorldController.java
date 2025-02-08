package com.herbivore.demo.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
