package com.ibm.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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

	@PostMapping("/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	String createEmployee(@RequestBody @Valid Employee employee, BindingResult bindingResult) {
		validateModel(bindingResult);
		System.out.println(employee);
		return employeeService.createEmployee(employee);
	}

	private void validateModel(Errors bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("Something went wrong. Please retry");
		}
	}

	@PutMapping("/employee/{id}")
	void updateEmployee(@RequestBody @Valid Employee employee, BindingResult bindingResult,
			@PathVariable("id") String employeeId) {
		validateModel(bindingResult);
		employee.setId(employeeId);
		employeeService.updateEmployee(employee);
	}
}
