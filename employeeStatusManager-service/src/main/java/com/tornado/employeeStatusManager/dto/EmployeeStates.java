package com.tornado.employeeStatusManager.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EmployeeStates {

	ADDED("Added"), 
	IN_CHECK("IN-Check"), 
	APPROVED("Approved"), 
	CANCELLED("Cancelled"), 
	ACTIVE("Active");

	private String description;

	private static Map<String, EmployeeStates> MAPPED_VALUES = new HashMap<String, EmployeeStates>();

	static {
		for (EmployeeStates element : EmployeeStates.values()) {
			MAPPED_VALUES.put(element.name(), element);
		}
	}

	@JsonCreator
	public static EmployeeStates fromString(String value) {
		return MAPPED_VALUES.get(value);
	}

	private EmployeeStates(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

}
