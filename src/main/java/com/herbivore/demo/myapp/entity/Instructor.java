package com.herbivore.demo.myapp.entity;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
			cascade = {PERSIST, REMOVE},
//			orphanRemoval = true,
			mappedBy = "instructor"
	)
	private InstructorDetail instructorDetail;

	@OneToMany(
			cascade = {PERSIST, MERGE, REFRESH, DETACH},
			mappedBy = "instructor"
	)
	private Set<Course> courses = new HashSet<>();

	protected Instructor() {}

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

	public void associate(Course... courses) {
		for (var course : courses) {
			this.courses.add(course);
			course.setInstructor(this);
		}
	}

	public void dissociateDetail() {
		if (instructorDetail != null) {
			instructorDetail.setInstructor(null);
		}
		this.setInstructorDetail(null);
	}

	public void dissociateCourses(Course... courses) {
		Arrays.stream(courses)
				.filter(Objects::nonNull)
				.forEach(course -> {
					course.setInstructor(null);
					this.courses.remove(course);
				});
	}

	public void dissociateAllCourses() {
		courses.remove(null);
		courses.forEach(course -> {
			course.setInstructor(null);
			courses.remove(course);
		});
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

	public Set<Course> getCourses() {return courses;}
	protected void setCourses(Set<Course> courses) {this.courses = courses;}

	@Override
	public String toString() {
		String detailString = instructorDetail == null? "(No InstructorDetail)" : instructorDetail.toString();
		String coursesString = String.join("\n\t- ", courses.stream().map(Course::toString).toArray(String[]::new));
		return "Instructor(" + hashCode() + "){" +
			   "id=" + id +
			   ", firstName='" + firstName + '\'' +
			   ", lastName='" + lastName + '\'' +
			   ", email='" + email + '\'' +
			   ", \n- " + detailString +
			   ", \n- courses: \n\t- " + coursesString +
			   '}';
	}
}
