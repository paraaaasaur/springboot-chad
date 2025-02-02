package com.herbivore.demo.myapp.entity;

/**
 * Q1. This is not an {@code @Entity}. Is it fine?
 * Q2. Is no-arg or all-args constructor necessary?
 **/
public class Student {

	private String firstName;

	private String lastName;

	public Student() {}

	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/** broken getter */
	public String ggeettFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/** misspelled getter */
	public String getLaaaaaastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
