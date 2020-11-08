package com.tornado.employeeStatusManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tornado.employeeStatusManager.dto.EmployeeEvents;
import com.tornado.employeeStatusManager.dto.employee.EmployeeDTO;
import com.tornado.employeeStatusManager.service.employee.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired EmployeeService employeeService;
	
	@PostMapping
	public void addEmployee(@RequestBody EmployeeDTO employeeDTO) {
		employeeService.addEmployee(employeeDTO);
	}
	
	@PutMapping("/status/{id}")
	public void updateEmployeeStatus(@PathVariable Long id,@RequestParam EmployeeEvents employeeEvents) {
		employeeService.updateEmployeeStatus(id, employeeEvents);
	}
	
	@GetMapping("/{id}")
	public EmployeeDTO getEmployee(Long id) {
		return employeeService.getEmployeeDto(id);
	}
	
	@GetMapping
	public List<EmployeeDTO> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
}
