package com.herbivore.demo.myapp.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "idEmail", types = Employee.class)
public interface EmployeeProjection {
	// Exposes id
	int getId();

	// Exposes email
	String getEmail();
}
