package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.entity.Employee;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJPAImpl implements EmployeeDAO {

	private EntityManager entityManager;

	@Autowired
	public EmployeeDAOJPAImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	public List<Employee> findAll() {
		return entityManager
				.createQuery("FROM Employee", Employee.class)
				.getResultList();
	}

	@Override
	public Employee findById(int id) {
		return entityManager.find(Employee.class, id);
	}

	/*	@Transactional...? NOPE!! Now the job belongs to the service class! */
	@Override
	public Employee save(Employee employee) {

		// merge() does INSERT or UPDATE, depending on the...
		Employee dbEmployee = entityManager.merge(employee);

		return dbEmployee;
	}

	// @Transactional
	@Override
	public void deleteById(int id) {
		entityManager.remove(
				entityManager.find(Employee.class, id)
		);
	}

	@Override
	public void test() {}


}
