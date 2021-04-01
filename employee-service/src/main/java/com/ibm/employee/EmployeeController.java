package com.ibm.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;





@RestController
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@GetMapping("/employee")
	List<Employee> getemployees() {
		return employeeService.getEmployees();
	}
	
	@GetMapping("/employee/{id}")
	Optional<Employee> getEmployee(@PathVariable("id") String employeeId) {
		return employeeService.getEmployee(employeeId);
	}
	
}
