package com.herbivore.demo.myapp.rest;

import com.herbivore.demo.myapp.entity.Student;
import com.herbivore.demo.myapp.exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentBaseController {

	private List<Student> students;

	// refactoring: populate the student list only once right after the startup
	@PostConstruct
	public void loadData() {
		this.students = List.of(
				new Student("Harry", "Potter"),
				new Student("Tom", "Riddle"),
				new Student("Neville", "Longbottom")
		);
	}

	// Endpoint "/students": respond with a list of students
	@GetMapping("/students")
	public List<Student> getStudents() {
		return students;
	}

	// Endpoint "/students/{studentId}"
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		if (studentId >= students.size() || studentId < 0)
			throw new StudentNotFoundException(
					"Student id = " + studentId +" not found ( ^)o(^ )"
			);

		return students.get(studentId);
	}
}
