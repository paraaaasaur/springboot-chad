package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.entity.Instructor;
import com.herbivore.demo.myapp.entity.InstructorDetail;

public interface AppDAO {

	void saveInstructor(Instructor instructor);

	Instructor findInstructorById(int id);

	void updateInstructor(Instructor instructor);

	void deleteInstructorById(int id);

//	void saveInstructorDetail(InstructorDetail instructorDetail);

	InstructorDetail findInstructorDetailById(int id);

	void updateInstructorDetail(InstructorDetail instructorDetail);

	void deleteInstructorDetailById(int id);
}
