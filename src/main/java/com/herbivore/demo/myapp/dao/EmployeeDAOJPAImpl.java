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
	public void test() {}
}
