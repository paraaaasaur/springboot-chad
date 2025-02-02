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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
