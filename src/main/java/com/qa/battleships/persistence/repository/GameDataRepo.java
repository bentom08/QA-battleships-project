package com.qa.battleships.persistence.repository;

import java.util.Collection;

import com.qa.battleships.persistence.domain.Game;

public interface GameDataRepo {

	String addGame(String username, Game game);
	
	Collection<Game> getUserGames(String username);
	
	Collection<Game> getAllGames();
}
