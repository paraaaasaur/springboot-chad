package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "members") // custom resource name
//@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
