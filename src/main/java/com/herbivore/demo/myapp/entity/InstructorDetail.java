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

	// TODO: Fill in appropriate annotation(s) here!
	// cascade type? mapping? fetch? ...
	private Instructor instructor;

	public InstructorDetail() {}

	public InstructorDetail(String youtubeChannel, String hobby) {
		this.youtubeChannel = youtubeChannel;
		this.hobby = hobby;
	}

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}

	public String getYoutubeChannel() {return youtubeChannel;}
	public void setYoutubeChannel(String youtubeChannel) {this.youtubeChannel = youtubeChannel;}

	public String getHobby() {return hobby;}
	public void setHobby(String hobby) {this.hobby = hobby;}

	// TODO: try protected setter
	public Instructor getInstructor() {return instructor;}
	public void setInstructor(Instructor instructor) {this.instructor = instructor;}

	@Override
	public String toString() {
		return "InstructorDetail{" +
			   "id=" + id +
			   ", youtubeChannel='" + youtubeChannel + '\'' +
			   ", hobby='" + hobby + '\'' +
			   '}';
	}
}
