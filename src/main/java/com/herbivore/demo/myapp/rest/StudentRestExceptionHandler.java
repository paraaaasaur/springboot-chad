package com.herbivore.demo.myapp.rest;

import com.herbivore.demo.myapp.exception.StudentErrorResponse;
import com.herbivore.demo.myapp.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/** Using {@code @ControllerAdvice} is a better
 * practice to handle global Exceptions in a
 * large scale or real-time project.
 **/
@ControllerAdvice
public class StudentRestExceptionHandler {
	
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
