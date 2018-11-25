package com.qa.battleships.service;
import javax.inject.Inject;

import com.qa.battleships.persistence.repository.GameDataRepo;
import com.qa.battleships.persistence.repository.UserLogin;
import com.qa.battleships.util.JSONUtil;
import com.qa.battleships.persistence.domain.Game;
import com.qa.battleships.persistence.domain.User;

public class ServiceLayer implements UserLoginService,
									 GameDataService {
	
	@Inject
	UserLogin login;
	
	@Inject
	GameDataRepo gameData;
	
	@Inject
	JSONUtil util;

	public String addUser(String jsonUser) {
		return login.addUser(util.getObjectForJSON(jsonUser, User.class));
	}

	public String updatePassword(String jsonUser) {
		return login.updatePassword(util.getObjectForJSON(jsonUser, User.class));
	}

	public String checkUsername(String username) {
		return login.checkUsername(username);
	}

	public String checkPassword(String jsonUser) {
		return login.checkPassword(util.getObjectForJSON(jsonUser, User.class));
	}

	public String deleteUser(String username) {
		return login.deleteUser(username);
	}

	public String addGame(String username, String jsonGame) {
		return gameData.addGame(username, util.getObjectForJSON(jsonGame, Game.class));
	}

	public String getUserGames(String username) {
		return util.getJSONForObject(gameData.getUserGames(username));
	}

	public String getAllGames() {
		return util.getJSONForObject(gameData.getAllGames());
	}
}
