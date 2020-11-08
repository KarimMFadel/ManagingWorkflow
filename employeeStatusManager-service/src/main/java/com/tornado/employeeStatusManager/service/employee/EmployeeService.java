package com.tornado.employeeStatusManager.service.employee;

import java.util.List;

import com.tornado.employeeStatusManager.dto.EmployeeEvents;
import com.tornado.employeeStatusManager.dto.EmployeeStates;
import com.tornado.employeeStatusManager.dto.employee.EmployeeDTO;

public interface EmployeeService {

	public void addEmployee(EmployeeDTO employeeDTO);

	public void updateEmployeeStatus(Long id, EmployeeEvents employeeEvents);
	
	public EmployeeDTO getEmployeeDto(Long id);
	
	public List<EmployeeDTO> getAllEmployees();

	public void updateDateBasedOnStatus(Long id, EmployeeStates employeeStates);

}
