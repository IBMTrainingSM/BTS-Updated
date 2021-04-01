package com.ibm.employee;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Employee {
	@NotBlank
	@NotNull
	private String name;
	@NotNull
	private String mail;
	private float phoneNumber;
	private TYPE type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public float getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(float phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public TYPE getType() {
		return type;
	}
	public void setType(TYPE type) {
		this.type = type;
	}
	
}
