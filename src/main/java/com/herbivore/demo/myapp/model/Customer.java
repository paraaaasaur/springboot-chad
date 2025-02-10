package com.herbivore.demo.myapp.model;

import jakarta.validation.constraints.*;

public class Customer {

	private String firstName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required!")
	@Pattern(regexp = "\\p{Alpha}+", message = "alphabetical!")
	private String lastName;

	@NotNull(message = "is required")
	@Min(value = 0, message = "must >= 0")
	@Max(value = 10, message = "must <= 10")
	private Integer freePasses;

	@NotNull(message = "is required")
	@Pattern(regexp = "\\p{Alnum}{5}", message = "5 alphabetic/numbers")
	private String postalCode;

	public Customer() {
		this("foo", "bar", 3, null);
	}

	public Customer(String firstName, String lastName, Integer freePasses, String postalCode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.freePasses = freePasses;
		this.postalCode = postalCode;
	}

	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}

	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}

	public Integer getFreePasses() {return freePasses;}
	public void setFreePasses(Integer freePasses) {this.freePasses = freePasses;}

	public String getPostalCode() {return postalCode;}
	public void setPostalCode(String postalCode) {this.postalCode = postalCode;}

	@Override
	public String toString() {
		return "Customer{" +
			   "firstName='" + firstName + '\'' +
			   ", lastName='" + lastName + '\'' +
			   ", freePasses=" + freePasses +
			   ", postalCode='" + postalCode + '\'' +
			   '}';
	}
}
