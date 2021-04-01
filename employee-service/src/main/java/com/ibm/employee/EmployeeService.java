package com.ibm.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	public String createEmployee(@RequestBody Employee employee) {
		Employee employeesave = employeeRepository.save(employee);
		return employeesave.getId();
	}

}
