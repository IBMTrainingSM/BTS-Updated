package com.ibm.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;



	public List<Employee> getEmployees() {
		
		return employeeRepository.findAll();
	}
	public Optional<Employee> getEmployee(String employeeId) {
		return employeeRepository.findById(employeeId);
	}

}
