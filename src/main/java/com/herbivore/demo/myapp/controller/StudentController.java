package com.herbivore.demo.myapp.controller;

import com.herbivore.demo.myapp.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static io.github.paraaaasaur.util.Toolbox.green;

@Controller
public class StudentController {

	@GetMapping("/showStudentForm")
	public String showForm(Model model) {
		model.addAttribute("student", new Student("foo", "bar"));
		return "student-form";
	}

	@PostMapping("/processStudentForm")
	public String processStudentForm(@ModelAttribute("student") Student student) {
		System.out.printf(green("Student %s %s just registered\n"),
				student.getFirstName(), student.getLastName()
		);

		return "student-confirmation";
	}
}
