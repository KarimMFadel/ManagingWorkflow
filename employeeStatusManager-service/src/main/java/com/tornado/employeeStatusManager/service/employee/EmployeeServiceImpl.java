package com.tornado.employeeStatusManager.service.employee;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tornado.employeeStatusManager.dto.EmployeeEvents;
import com.tornado.employeeStatusManager.dto.EmployeeStates;
import com.tornado.employeeStatusManager.dto.employee.EmployeeDTO;
import com.tornado.employeeStatusManager.general.config.workflow.EmployeeWorkFlowManager;
import com.tornado.employeeStatusManager.model.Employee;
import com.tornado.employeeStatusManager.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Map<Long, StateMachine<EmployeeStates, EmployeeEvents>> stateMachineMap = new HashMap<Long, StateMachine<EmployeeStates, EmployeeEvents>>();

	@Autowired EmployeeRepository employeeRepository;

	@Autowired EmployeeWorkFlowManager employeeWorkFlowManager;

	private static final ModelMapper modelMapper = new ModelMapper();

	@Override
	public void addEmployee(EmployeeDTO employeeDTO) {
		Employee employee = employeeRepository.save(convertToObject(employeeDTO));
		StateMachine<EmployeeStates, EmployeeEvents> stateMachine = employeeWorkFlowManager.newEmployee(employee);
		stateMachineMap.put(employee.getId(), stateMachine);
	}

	@Override
	public void updateEmployeeStatus(Long id, EmployeeEvents employeeEvents) {
		EmployeeDTO employeeDTO = getEmployeeDto(id);
		employeeWorkFlowManager.fire(stateMachineMap.get(employeeDTO.getId()), employeeEvents);
	}

	@Override
	public EmployeeDTO getEmployeeDto(Long id) {
		Employee employee = getEmployee(id);
		return convertToDto(employee);
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		return employeeRepository.findAll().stream().map(employee -> convertToDto(employee))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void updateDateBasedOnStatus(Long id, EmployeeStates employeeStates) {
		Employee employee = getEmployee(id);
		switch (employeeStates) {
		case ACTIVE:
			employee.setActivateDate(new Date());
			break;
		case CANCELLED:
			employee.setCancelDate(new Date());
			break;
		case APPROVED:
			employee.setApprovalDate(new Date());
			break;
		default:
			break;
		}
	}

	private Employee getEmployee(Long id) {
		Employee employee = employeeRepository.getOne(id);
		if (null == employee)
			throw new RuntimeException("Coudn't find employee with id: " + id);
		return employee;
	}

	private EmployeeDTO convertToDto(Employee employee) {
		return modelMapper.map(employee, EmployeeDTO.class);
	}

	private Employee convertToObject(EmployeeDTO employeeDTO) {
		return new Employee(employeeDTO.getName(), employeeDTO.getContractInformation(), employeeDTO.getAge());
	}

}
