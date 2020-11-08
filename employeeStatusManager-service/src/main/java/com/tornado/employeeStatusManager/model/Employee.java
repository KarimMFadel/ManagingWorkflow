package com.tornado.employeeStatusManager.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String contractInformation;
	private int age;
	private Date approvalDate;
	private Date activateDate;
	private Date cancelDate;

	public Employee() {
		super();
	}

	public Employee(String name, String contractInformation, int age) {
		super();
		this.name = name;
		this.contractInformation = contractInformation;
		this.age = age;
	}

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

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Employee))
			return false;
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [");
		if (id != null) {
			builder.append("id=").append(id).append(", ");
		}
		if (name != null) {
			builder.append("name=").append(name).append(", ");
		}
		if (contractInformation != null) {
			builder.append("contractInformation=").append(contractInformation).append(", ");
		}
		builder.append("age=").append(age).append(", ");
		if (approvalDate != null) {
			builder.append("approvalDate=").append(approvalDate).append(", ");
		}
		if (activateDate != null) {
			builder.append("activateDate=").append(activateDate).append(", ");
		}
		if (cancelDate != null) {
			builder.append("cancelDate=").append(cancelDate);
		}
		builder.append("]");
		return builder.toString();
	}

}
