package com.herbivore.demo.myapp.entity;

import jakarta.persistence.*;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "instructor")
public class Instructor {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	// related entity
	@OneToOne(cascade = {PERSIST, MERGE, DETACH, REFRESH, REMOVE})
		// When you persist an Instructor, you also persist an InstructorDetail
		// When you remove an Instructor, you also remove an InstructorDetail
		// ...and so on
		// By default not vice versa
	@JoinColumn(name = "instructor_detail_id")
	private InstructorDetail instructorDetail;

	public Instructor() {}

	public Instructor(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}

	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}

	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}

	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}

	public InstructorDetail getInstructorDetail() {return instructorDetail;}
	public void setInstructorDetail(InstructorDetail instructorDetail) {this.instructorDetail = instructorDetail;}

	@Override
	public String toString() {
		return "Instructor{" +
			   "id=" + id +
			   ", firstName='" + firstName + '\'' +
			   ", lastName='" + lastName + '\'' +
			   ", email='" + email + '\'' +
			   ", instructorDetail=" + instructorDetail +
			   '}';
	}
}
