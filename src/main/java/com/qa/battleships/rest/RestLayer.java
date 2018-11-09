package com.qa.battleships.rest;

import javax.inject.Inject;

import com.qa.battleships.service.ServiceLayer;
import com.qa.battleships.service.UserLoginService;


public class RestLayer {
	
	@Inject
	UserLoginService loginService;

	public boolean addUser(String jsonUser) {
		return loginService.addUser(jsonUser);
	}

	public boolean updatePassword(String newPassword, String username) {
		return loginService.updatePassword(newPassword, username);
	}
	
	public boolean checkUsername(String username) {
		return loginService.checkUsername(username);
	}

	public boolean checkPassword(String newPassword, String username) {
		return loginService.checkPassword(newPassword, username);
	}
	
	public void setLoginService(ServiceLayer service) {
		this.loginService = service;
	}
}