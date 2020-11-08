package com.tornado.employeeStatusManager.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EmployeeEvents {

	ADD("Add"),
	IN_CHECK("IN-Check"),
	APPROVE("Approve"),
	CANCEL("Cancel"),
	ACTIVE("Active");
	
	private String description;
	
	private static Map<String, EmployeeEvents> MAPPED_VALUES = new HashMap<String, EmployeeEvents>();

	static {
		for (EmployeeEvents element : EmployeeEvents.values()) {
			MAPPED_VALUES.put(element.name(), element);
		}
	}

	@JsonCreator
	public static EmployeeEvents fromString(String value) {
		return MAPPED_VALUES.get(value);
	}
	
	private EmployeeEvents(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
