package com.qa.battleships.service;

import javax.inject.Inject;

import com.qa.battleships.persistence.repository.UserLogin;

public class ServiceLayer implements UserLoginService {
	
	@Inject
	UserLogin login;
	
	@Inject
	GridSquare square;

	public String addUser(String jsonUser) {
		return login.addUser(jsonUser);
	}

	public String updatePassword(String jsonUser) {
		return login.updatePassword(jsonUser);
	}

	public String checkUsername(String username) {
		return login.checkUsername(username);
	}

	public String checkPassword(String jsonUser) {
		return login.checkPassword(jsonUser);
	}

	public String deleteUser(String username) {
		return login.deleteUser(username);
	}
	
	
}
