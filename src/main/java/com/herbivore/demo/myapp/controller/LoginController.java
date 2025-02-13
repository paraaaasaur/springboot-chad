package com.herbivore.demo.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
//		return "plain-login";
		return "fancy-login";
	}

	// Implemented by Spring Boot
//	@PostMapping("/authenticateTheUser")
//	public String authenticateTheUser() {}


}
