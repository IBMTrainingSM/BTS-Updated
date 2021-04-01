package com.ibm.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	public EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}

	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public String createEmployee(@RequestBody Employee employee) {
		Employee employeesave = employeeRepository.save(employee);
		return employeesave.getId();
	}

	public List<Employee> getEmployees() {

		return employeeRepository.findAll();
	}

	public Optional<Employee> getEmployee(String employeeId) {
		return employeeRepository.findById(employeeId);

	}

	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
}
