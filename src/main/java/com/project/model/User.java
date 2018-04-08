package com.project.model;

import java.math.BigDecimal;
import java.util.Date;

public class User {

	private Long id;
	private String email;
	private String name;
	private Date birthDate;
	private String address;
	private BigDecimal salary;
	private Boolean status;
	
	public User() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", birthDate=" + birthDate + ", address="
				+ address + ", salary=" + salary + ", status=" + status + "]";
	}

	public User(Long id, String email, String name, Date birthDate, String address, BigDecimal salary, Boolean status) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.birthDate = birthDate;
		this.address = address;
		this.salary = salary;
		this.status = status;
	}

}
