package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.entity.Instructor;

public interface AppDAO {

	void save(Instructor instructor);

	Instructor findInstructorById(int id);
}
