package com.herbivore.demo.myapp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

	private String codePrefix;

	@Override
	public void initialize(CourseCode courseCode) {
		codePrefix = courseCode.value();
	}

	@Override
	public boolean isValid(
			String inputCode,
			ConstraintValidatorContext context // ?
	) {
		return
//				inputCode != null &&
				inputCode.startsWith(codePrefix);
	}
}
