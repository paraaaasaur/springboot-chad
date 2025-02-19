package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.entity.Instructor;
import com.herbivore.demo.myapp.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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


}
