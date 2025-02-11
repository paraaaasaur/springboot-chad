package com.herbivore.demo.myapp.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {CourseCodeConstraintValidator.class})
@Target({ElementType.FIELD
//		, ElementType.METHOD // ?
})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

	String value() default "TUNA";

	String message() default "Course Code has to start with TUNA";

	// validating order
	Class<?>[] groups() default {};

	// extra message as metadata
	Class<? extends Payload>[] payload() default {};
}
