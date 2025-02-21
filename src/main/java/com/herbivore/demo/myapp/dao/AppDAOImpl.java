package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.entity.Course;
import com.herbivore.demo.myapp.entity.Instructor;
import com.herbivore.demo.myapp.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Repository
public class AppDAOImpl implements AppDAO {

	private EntityManager entityManager;

	public AppDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	@Override
	public void saveInstructor(Instructor instructor) {
//		session.persist(instructor);
		entityManager.persist(instructor);
	}

	@Override
	public Instructor findInstructorById(int id) {
		return entityManager.find(Instructor.class, id);
	}

	@Transactional
	@Override
	public void updateInstructor(Instructor instructor) {
		entityManager.merge(instructor);
	}

	@Transactional
	@Override
	public void deleteInstructorById(int id) {
		Instructor found = entityManager.find(Instructor.class, id);

//		found.removeDetail();
//		found.setInstructorDetail(null);
//		found.getInstructorDetail().setInstructor(null);
		found.dissociateDetail();
//		entityManager.detach(found.getInstructorDetail());
//		entityManager.remove(found);
	}

	@Override
	public InstructorDetail findInstructorDetailById(int id) {
		return entityManager.find(InstructorDetail.class, id);
	}

	@Transactional
	@Override
	public void updateInstructorDetail(InstructorDetail instructorDetail) {
		entityManager.merge(instructorDetail);
	}

	@Transactional
	@Override
	public void deleteInstructorDetailById(int id) {
		InstructorDetail foundDetail = entityManager.find(InstructorDetail.class, id);
		foundDetail.dissociateInstructor();
		entityManager.remove(foundDetail);
	}

	@Override
	public Set<Course> findCoursesById(int id) {
		String hql = "FROM Course WHERE instructor.id = ?1";
		TypedQuery<Course> query = entityManager.createQuery(hql, Course.class)
				.setParameter(1, id);
		return new HashSet<>(query.getResultList());
	}

	@Override
	public Instructor findInstructorByIdJoinFetch(int id) {
		// Requires matching records in all tables
//		String jpql = "SELECT i FROM Instructor i " +
//					  "LEFT JOIN FETCH i.courses " +
//					  "LEFT JOIN FETCH i.instructorDetail " +
//					  "WHERE i.id = :id";

		// Allows no match (NULL) in child tables
		String jpql = "SELECT i FROM Instructor i " +
					   "LEFT JOIN FETCH i.courses " +
					   "LEFT JOIN FETCH i.instructorDetail " +
					   "WHERE i.id = :id";

		var query = entityManager.createQuery(jpql, Instructor.class)
				.setParameter("id", id);

		return query.getSingleResult();
	}
}
