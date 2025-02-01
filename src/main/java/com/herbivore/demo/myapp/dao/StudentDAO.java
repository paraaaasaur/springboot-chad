package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.entity.Student;
import jakarta.persistence.Tuple;

import java.util.List;

public interface StudentDAO {

	void save(Student student);

	void save();

	Student findById(int id);

	List<Student> findAll();

	List<Tuple> findAllInTuple();

	List<Student> findByLastName(String lastName);

	void test();
}
