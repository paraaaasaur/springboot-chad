package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// Spring Data JPA infers the wanted query by method name
	List<Employee> findAllByOrderByLastNameAsc();
}
