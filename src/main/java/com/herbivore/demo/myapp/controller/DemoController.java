package com.herbivore.demo.myapp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.ZonedDateTime;
import java.util.Map;

import static io.github.paraaaasaur.util.Toolbox.yellow;

@Controller
public class DemoController {

	@GetMapping("/hello")
	public String sayHello(Model model) {

		model.addAttribute("serverTime", ZonedDateTime.now());

		// Spring Boot automatically configures to use Thymeleaf,
		// since we have it in POM.xml
		// Looks in: src/main/resources/templates/helloworld.html
		return "helloworld";
	}

	// debug purpose
	private void displayRequestHeaders(HttpHeaders httpHeaders) {
		httpHeaders.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.forEach(es -> {
					System.out.printf("\t%s: %s\n", es.getKey(), yellow(es.getValue().toString()));
				});
	}
}
