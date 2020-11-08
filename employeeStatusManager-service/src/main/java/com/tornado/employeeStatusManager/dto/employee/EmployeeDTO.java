package com.tornado.employeeStatusManager.dto.employee;

import java.util.Date;

public class EmployeeDTO {

	private Long id;
	private String name;
	private String contractInformation;
	private int age;
	private Date approvalDate;
	private Date activateDate;
	private Date cancelDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContractInformation() {
		return contractInformation;
	}

	public void setContractInformation(String contractInformation) {
		this.contractInformation = contractInformation;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Date getActivateDate() {
		return activateDate;
	}

	public void setActivateDate(Date activateDate) {
		this.activateDate = activateDate;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

}
