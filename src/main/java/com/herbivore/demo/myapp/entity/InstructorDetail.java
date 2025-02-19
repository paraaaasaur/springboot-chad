package com.herbivore.demo.myapp.entity;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "youtube_channel")
	private String youtubeChannel;

	@Column(name = "hobby")
	private String hobby;

	@OneToOne
	// Redundant: Presumed FK name = fieldName + "_id"
//	@JoinColumn(name = "instructor_id")
	private Instructor instructor;

	public InstructorDetail() {}

	public InstructorDetail(String youtubeChannel, String hobby) {
		this.youtubeChannel = youtubeChannel;
		this.hobby = hobby;
	}

	public void dissociate() {
		instructor.dissociate();
	}

	public int getId() {return id;}
	// FIXME: Potential bug using protected setter
	protected void setId(int id) {this.id = id;}

	public String getYoutubeChannel() {return youtubeChannel;}
	public void setYoutubeChannel(String youtubeChannel) {this.youtubeChannel = youtubeChannel;}

	public String getHobby() {return hobby;}
	public void setHobby(String hobby) {this.hobby = hobby;}

	public Instructor getInstructor() {return instructor;}
	// FIXME: Potential bug
	//  - Testing for protected setter to avoid unconventional access
	protected void setInstructor(Instructor instructor) {this.instructor = instructor;}

	@Override
	public String toString() {
		return "InstructorDetail(" + instructor.hashCode() + "){" +
			   "id=" + id +
			   ", youtubeChannel='" + youtubeChannel + '\'' +
			   ", hobby='" + hobby + '\'' +
			   '}';
	}
}
