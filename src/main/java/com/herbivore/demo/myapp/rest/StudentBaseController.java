package com.herbivore.demo.myapp.rest;

import com.herbivore.demo.myapp.entity.Student;
import com.herbivore.demo.myapp.exception.StudentErrorResponse;
import com.herbivore.demo.myapp.exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	/** Exception handler: Annotated with {@code @ExceptionHandler}
	 * and placed under the corresponding controller
	 **/
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(
			StudentNotFoundException e
	) {
		StudentErrorResponse body = new StudentErrorResponse();

		body.setStatus(HttpStatus.NOT_FOUND.value());
		body.setMessage(e.getMessage());
		body.setTimestamp(System.currentTimeMillis());

		return new ResponseEntity<>(
				body,
				HttpStatus.NOT_FOUND
		);
	}

	/** Handles Generic Exceptions that are not
	 * catched as {@code StudentNotFoundException}
	 * under the endpoint of this controller */
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(Exception e) {
		StudentErrorResponse body = new StudentErrorResponse();

		body.setStatus(HttpStatus.BAD_REQUEST.value());
		body.setMessage(e.getMessage());
		body.setTimestamp(System.currentTimeMillis());

		return new ResponseEntity<>(
				body,
				HttpStatus.BAD_REQUEST
		);
	}
}
