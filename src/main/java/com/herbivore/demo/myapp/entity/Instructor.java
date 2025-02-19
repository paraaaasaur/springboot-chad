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

	@OneToOne(
			cascade = {PERSIST},
//			orphanRemoval = true,
			mappedBy = "instructor"
	)
	private InstructorDetail instructorDetail;

	public Instructor() {}

	public Instructor(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	/** <h3>Use me to bind referenced & dependent tables together
	 * in a bidirectional {@link OneToOne} mapping!</h3>*/
	public void associate(InstructorDetail instructorDetail) {
		this.setInstructorDetail(instructorDetail);
		instructorDetail.setInstructor(this);
	}

	public void dissociate() {
		if (instructorDetail != null) {
			instructorDetail.setInstructor(null);
		}
		this.setInstructorDetail(null);
	}

	public int getId() {return id;}
	// FIXME: Potential bug using protected setter
	protected void setId(int id) {this.id = id;}

	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}

	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}

	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}

	public InstructorDetail getInstructorDetail() {return instructorDetail;}
	// FIXME: Potential bug
	//  - Testing for protected setter to force using convenience method
	protected void setInstructorDetail(InstructorDetail instructorDetail) {this.instructorDetail = instructorDetail;}

	@Override
	public String toString() {
		return "Instructor(" + hashCode() + "){" +
			   "id=" + id +
			   ", firstName='" + firstName + '\'' +
			   ", lastName='" + lastName + '\'' +
			   ", email='" + email + '\'' +
			   ", \n- " + instructorDetail +
			   '}';
	}
}
