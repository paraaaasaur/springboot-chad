package com.herbivore.demo.myapp.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "title")
	private String title;

	@ManyToOne(
			fetch = FetchType.LAZY
	)
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;

	protected Course() {}

	public Course(String title) {
		this.title = title;
	}

	public void dissociate() {
		instructor.dissociateCourses(this);
	}

	public int getId() {return id;}
	protected void setId(int id) {this.id = id;}

	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}

	public Instructor getInstructor() {return instructor;}

	public void setInstructor(Instructor instructor) {this.instructor = instructor;}

	@Override
	public String toString() {
		return "Course{" +
			   "id=" + id +
			   ", title='" + title + '\'' +
			   '}';
	}

	@Override
	public boolean equals(Object o) {
		return (o instanceof Course that)
			   && this.id == that.id
			   && Objects.equals(this.title, that.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title);
	}
}
