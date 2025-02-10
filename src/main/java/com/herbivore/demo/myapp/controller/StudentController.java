package com.herbivore.demo.myapp.controller;

import com.herbivore.demo.myapp.model.enums.OS;
import com.herbivore.demo.myapp.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

import static io.github.paraaaasaur.util.Toolbox.green;

@Controller
public class StudentController {

	@Value("${countries}")
	private List<String> countries;

	@Value("${languages}")
	private Set<String> languages;

	private final EnumSet<OS> systems = EnumSet.allOf(OS.class);

	@GetMapping("/showStudentForm")
	public String showForm(Model model) {
		model.addAttribute("student", new Student("foo", "bar"));
		Set<String> sortedCountries = new TreeSet<>(this.countries);
		model.addAttribute("countries", sortedCountries);
		model.addAttribute("languages", languages);
		model.addAttribute("systems", systems);
		return "student-form";
	}

	@PostMapping("/processStudentForm")
	public String processStudentForm(@ModelAttribute("student") Student student) {
		System.out.printf(green("""
						Student %s %s just registered
							- Nationality: %s
							- Favorite Programming Language: %s
							- Favorite Operating Systems: %s
						"""),
				student.getFirstName(),
				student.getLastName(),
				student.getCountry(),
				student.getFavoriteLanguage(),
				student.getFavoriteSystems()
		);

		return "student-confirmation";
	}
}
