package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * <li>{@code @Repository} annotation is a specialized annotations
 * for DAOs. It supports component-scanning, and makes JDBC Exceptions
 * from checked to unchecked types.</li>
 **/

@Repository
public class StudentDAOImpl implements StudentDAO {
	// Define a field for EntityManager
	private EntityManager entityManager;



	// Inject EntityManager with constructor
	/** {@code @Autowired} is optional if
	 * there is only one constructor.
	 **/
	//	@Autowired
	public StudentDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	// Implement save method
	@Override
	@Transactional
	public void save(Student student) {
		entityManager.persist(student);
	}

	@Override
	@Transactional
	public void save() {
		Student foundStudent = entityManager.find(Student.class, 1);
		foundStudent.setFirstName("Gooba");
	}

	public void test() {
		
	}

}
