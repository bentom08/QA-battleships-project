package com.qa.battleships.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users {

	@Id
	@Column(columnDefinition = "VARCHAR(40)")
	private String username;
	
	@Column(columnDefinition = "CHAR(128)")
	private String password;
	
	@Column(columnDefinition = "CHAR(8)")
	private String salt;
	
	public Users() {
		
	}
	
	public Users(String username, String password, String salt) {
		this.password = password;
		this.username = username;
		this.salt = salt;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
