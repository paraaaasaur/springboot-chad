package com.herbivore.demo.myapp.dao;

import com.herbivore.demo.myapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {}
