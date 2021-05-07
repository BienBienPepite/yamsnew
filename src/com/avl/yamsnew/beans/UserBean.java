package com.avl.yamsnew.beans;

import java.sql.Timestamp;

public class UserBean {
	
	private Long      id;
	private String    username;
	private String    password;
	private Timestamp registrationDate;
	
	public UserBean() {
		username = "";
		password = "";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Timestamp registrationDate) {
		this.registrationDate = registrationDate;
	}

}
