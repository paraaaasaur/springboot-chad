package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.entity.Course;
import com.herbivore.demo.myapp.entity.Instructor;
import com.herbivore.demo.myapp.entity.InstructorDetail;

import java.util.Set;

public interface AppDAO {

	void saveInstructor(Instructor instructor);

	Instructor findInstructorById(int id);

	Instructor findInstructorById(int id, boolean initializeCoures);

	void updateInstructor(Instructor instructor);

	void deleteInstructorById(int id);

//	void saveInstructorDetail(InstructorDetail instructorDetail);

	InstructorDetail findInstructorDetailById(int id);

	void updateInstructorDetail(InstructorDetail instructorDetail);

	void deleteInstructorDetailById(int id);

	Set<Course> findCoursesById(int id);

	Instructor findInstructorByIdJoinFetch(int id);

	Course findCourseById(int courseId);

	void updateCourse(Course course);

	void deleteCourseById(int id);

	void demoSetNullInconsistency();

	void saveCourse(Course course);

	Course findCourseAndReviewsById(int id);
}
