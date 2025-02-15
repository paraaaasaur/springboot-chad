package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.entity.Instructor;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {

	// Hibernate-specific persistence context. Supports second-level caching.
//	private Session session;
	// JPA persistence context, a more general one
	private EntityManager entityManager;

	public AppDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	@Override
	public void save(Instructor instructor) {
//		session.persist(instructor);
		entityManager.persist(instructor);
	}

	@Override
	public Instructor findInstructorById(int id) {
		return entityManager.find(Instructor.class, id);
	}
}
