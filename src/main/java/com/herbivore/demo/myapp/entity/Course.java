package com.herbivore.demo.myapp.entity;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
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

	@OneToMany(
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL
	)
	@JoinColumn(name = "course_id")
	private Collection<Review> reviews = new HashSet<>();

	protected Course() {}

	public Course(String title) {
		this.title = title;
	}

	public void associate(Review... reviewArr) {
		if (reviewArr == null || reviewArr.length == 0)
			return;
		if (reviews == null)
			reviews = new HashSet<>();
		Arrays.stream(reviewArr)
				.filter(Objects::nonNull)
				.forEach(reviews::add);
	}

	public void dissociateReviews(Review... reviewArr) {
		if (reviewArr == null || reviewArr.length == 0)
			return;
		Arrays.stream(reviewArr)
				.filter(Objects::nonNull)
				.forEach(reviews::remove);
	}

	public int getId() {return id;}
	protected void setId(int id) {this.id = id;}

	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}

	public Instructor getInstructor() {return instructor;}
	public void setInstructor(Instructor instructor) {this.instructor = instructor;}

	public Collection<Review> getReviews() {return reviews;}
	public void setReviews(Collection<Review> reviews) {this.reviews = reviews;}

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
