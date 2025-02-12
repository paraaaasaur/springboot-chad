package com.herbivore.demo.myapp.service;

import com.herbivore.demo.myapp.dao.EmployeeRepository;
import com.herbivore.demo.myapp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl() {}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> findAllByOrderByLastNameAsc() {
		return employeeRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Employee findById(int id) {
		if (true) {
			return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException(
					"Employee (id=%d) not found!".formatted(id)
			));
		}

		Optional<Employee> result = employeeRepository.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new RuntimeException("Employee (id=%d) not found!"
					.formatted(id));
		}
	}

//	@Transactional
	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

//	@Transactional
	@Override
	public void deleteById(int id) {
		employeeRepository.deleteById(id);
	}


	@Override
	public void test() {}

	@Autowired
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

}
