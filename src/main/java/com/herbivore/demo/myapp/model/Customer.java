package com.herbivore.demo.myapp.model;

import jakarta.validation.constraints.*;

public class Customer {

	private String firstName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required!")
	@Pattern(regexp = "\\p{Alpha}+", message = "alphabetical!")
	private String lastName;

	@Min(value = 0, message = "must >= 0")
	@Max(value = 10, message = "must <= 10")
	private int freePasses;

	public Customer() {}

	public Customer(String firstName, String lastName, int freePasses) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.freePasses = freePasses;
	}

	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}

	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}

	public int getFreePasses() {return freePasses;}
	public void setFreePasses(int freePasses) {this.freePasses = freePasses;}

	@Override
	public String toString() {
		return "Customer{" +
			   "firstName='" + firstName + '\'' +
			   ", lastName='" + lastName + '\'' +
			   ", freePasses=" + freePasses +
			   '}';
	}
}
