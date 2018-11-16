package com.qa.battleships.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import com.qa.battleships.persistence.repository.BattleshipLogic;
import com.qa.battleships.persistence.repository.UserLogin;
import com.qa.battleships.util.GridObject;
import com.qa.battleships.util.JSONUtil;

public class ServiceLayer implements UserLoginService {
	
	@Inject
	UserLogin login;
	
	@Inject
	BattleshipLogic battleships;

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
	
	public String placeShips(String ships) {
		return battleships.placeShips(ships);
	}
	
}
