package com.qa.battleships.service;

public interface GameDataService {

	String addGame(String username, String jsonGame);
	
	String getUserGames(String username);
	
	String getAIGames(String difficulty);
}
