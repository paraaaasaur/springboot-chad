package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.entity.Course;
import com.herbivore.demo.myapp.entity.Instructor;
import com.herbivore.demo.myapp.entity.InstructorDetail;
import com.herbivore.demo.myapp.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static io.github.paraaaasaur.util.Toolbox.blue;

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
	public Instructor findInstructorById(int id, boolean initializeCourses) {
		if (!initializeCourses)
			return findInstructorById(id);

		Instructor found = entityManager.find(Instructor.class, id);
		// Do random things to force initialization within a tx
//		Hibernate.initialize(found.getCourses());
//		found.getCourses().toString();
//		found.getCourses().isEmpty();
//		for (var course : found.getCourses()) {}
		found.getCourses().hashCode();

		return found;
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

		// No LazyInitializationException because of @Transactional,
		// i.e. within a session
		found.dissociateDetail();
		for (var course : found.getCourses()) {
			System.out.println(blue("> Dissociating %s w/ np were within session( ^)o(^ )".formatted(course)));
			course.setInstructor(null);
		}

		entityManager.remove(found);
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

	@Override
	public Course findCourseById(int courseId) {
		return entityManager.find(Course.class, courseId);
	}

	@Transactional
	@Override
	public void updateCourse(Course course) {
		entityManager.merge(course);
	}

	@Transactional
	@Override
	public void deleteCourseById(int id) {
		Course foundCourse = entityManager.find(Course.class, id);

		entityManager.remove(foundCourse);
	}

	@Transactional
	@Override
	public void demoSetNullInconsistency() {
		// Inconsistent set-null behavior across each type of association...
		// No cascade, no orphanRemoval
		Instructor i3 = entityManager.find(Instructor.class, 3);
		Course c10 = entityManager.find(Course.class, 10);
		Course c12 = entityManager.find(Course.class, 12);

		// irrelevant
		i3.getCourses().remove(c10);
		i3.setCourses(null);

		// relevant
//		c10.setInstructor(null);
//		c10.setInstructor(i3);
	}

	@Transactional
	@Override
	public void saveCourse(Course course) {
		entityManager.persist(course);
	}

	@Override
	public Course findCourseAndReviewsById(int id) {
		String jpql = "SELECT c FROM Course c " +
					  "LEFT JOIN FETCH c.reviews " +
					  "WHERE c.id = ?1";
		var query = entityManager.createQuery(jpql, Course.class)
				.setParameter(1, id);

		return query.getSingleResult();
	}

	@Override
	public Course findCourseAndStudentsById(int id) {
		String jpql = "SELECT c FROM Course c " +
					  "LEFT JOIN FETCH c.students " +
					  "WHERE c.id = :id";

		var query = entityManager.createQuery(jpql, Course.class)
				.setParameter("id", id);

		return query.getSingleResult();
	}

	@Transactional
	@Override
	public void saveStudent(Student student) {
		entityManager.persist(student);
	}

	@Override
	public Student findStudentAndCoursesById(int id) {
		String jpql = "SELECT s FROM Student s " +
					  "LEFT JOIN FETCH s.courses " +
					  "WHERE s.id = ?1";
		TypedQuery<Student> query = entityManager.createQuery(jpql, Student.class)
				.setParameter(1, id);

		return query.getSingleResult();
	}
}
