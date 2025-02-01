package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

	// Implement findById method
	@Override
	public Student findById(int id) {
		return entityManager.find(Student.class, id);
	}

	// Implement findAll method
	@Override
	public List<Student> findAll() {
		// create query
		String hql = "FROM Student " +
					 "ORDER BY lastName DESC";
		TypedQuery<Student> query = entityManager.createQuery(hql, Student.class);

		// return query result
		return query.getResultList();
	}

	// My extra practice
	@Override
	public List<Tuple> findAllInTuple() {
		String jpql = "SELECT s.id, s.firstName, s.lastName, s.email " +
					  "FROM Student s " +
					  "ORDER BY lastName DESC";
		TypedQuery<Tuple> query = entityManager.createQuery(jpql, Tuple.class);
		query.getResultStream();
		return query.getResultList();
	}

	// Implement findByLastName method
	@Override
	public List<Student> findByLastName(String lastName) {
		// Create query
		String jpql = "SELECT s FROM Student s " +
					  "WHERE s.lastName = :lastName";
		TypedQuery<Student> query = entityManager.createQuery(jpql, Student.class);

		// Set query params
		query.setParameter("lastName", lastName);

		// Return query result
		return query.getResultList();
	}

	@Transactional
	@Override
	public void update(Student student) {
		entityManager.merge(student);
	}

	@Override
	@Transactional
	public void delete(int id) {
		// Retrieve the student
		Student foundStudent = entityManager.find(Student.class, id);

		// Delete the student
		entityManager.remove(foundStudent);
	}

	@Override
	@Transactional
	public int deleteAll() {
		String jpql = "DELETE FROM Student s";
		Query deleteQuery = entityManager.createQuery(jpql);
		int deletedRows = deleteQuery.executeUpdate();

		return deletedRows;
	}

	public void test() {

	}

}
