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

	@GetMapping("/leaders")
	public String showLeaders() {
		return "leaders";
	}

	@GetMapping("/systems")
	public String showSystems() {
		return "systems";
	}

	@GetMapping("/access-denied-banana")
	public String showAccessDeniedPasta() {
		return "access-denied";
	}


}
