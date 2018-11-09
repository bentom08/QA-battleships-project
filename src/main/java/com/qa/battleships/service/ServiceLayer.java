package com.qa.battleships.service;

import javax.inject.Inject;

import com.qa.battleships.persistence.repository.UserLogin;

public class ServiceLayer implements UserLoginService {
	
	@Inject
	UserLogin login;

	public boolean addUser(String jsonUser) {
		return login.addUser(jsonUser);
	}

	public boolean updatePassword(String newPassword, String username) {
		return login.updatePassword(newPassword, username);
	}

	public boolean checkUsername(String username) {
		return login.checkUsername(username);
	}

	public boolean checkPassword(String password, String username) {
		return login.checkPassword(password, username);
	}
}
