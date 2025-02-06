package com.herbivore.demo.myapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DemoRestController {

	// "/hello" endpoint
	@GetMapping(name = "hello")
	public String sayHello() {
		return "Hello World!";
	}
}
