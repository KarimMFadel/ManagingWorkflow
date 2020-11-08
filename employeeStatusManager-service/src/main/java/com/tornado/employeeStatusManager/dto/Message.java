package com.tornado.employeeStatusManager.dto;

public class Message {

	Long id;
	EmployeeStates employeeStates;

	public Message() {
		super();
	}

	public Message(Long id, EmployeeStates employeeStates) {
		super();
		this.id = id;
		this.employeeStates = employeeStates;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EmployeeStates getEmployeeStates() {
		return employeeStates;
	}

	public void setEmployeeStates(EmployeeStates employeeStates) {
		this.employeeStates = employeeStates;
	}

}
