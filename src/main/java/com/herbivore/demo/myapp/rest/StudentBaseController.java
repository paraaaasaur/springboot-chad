package com.herbivore.demo.myapp.rest;

import com.herbivore.demo.myapp.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentBaseController {

	// Endpoint "/students": respond with a list of students
	@GetMapping("/students")
	public List<Student> getStudents() {

		List<Student> students = List.of(
				new Student("Harry", "Potter"),
				new Student("Tom", "Riddle"),
				new Student("Neville", "Longbottom")
		);

		return students;
	}
}
