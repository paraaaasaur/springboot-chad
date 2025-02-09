package com.herbivore.demo.myapp.controller;

import com.herbivore.demo.myapp.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static io.github.paraaaasaur.util.Toolbox.green;

@Controller
public class StudentController {

	@Value("${countries}")
	private List<String> countries;

	@Value("${languages}")
	private Set<String> languages;

	@GetMapping("/showStudentForm")
	public String showForm(Model model) {
		model.addAttribute("student", new Student("foo", "bar"));
		Set<String> sortedCountries = new TreeSet<>(this.countries);
		model.addAttribute("countries", sortedCountries);
		model.addAttribute("languages", languages);
		return "student-form";
	}

	@PostMapping("/processStudentForm")
	public String processStudentForm(@ModelAttribute("student") Student student) {
		System.out.printf(green("Student %s %s (%s)(%s) just registered\n"),
				student.getFirstName(),
				student.getLastName(),
				student.getCountry(),
				student.getFavoriteLanguage()
		);

		return "student-confirmation";
	}
}
