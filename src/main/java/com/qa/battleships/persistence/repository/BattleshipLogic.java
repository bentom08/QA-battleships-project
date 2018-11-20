package com.qa.battleships.persistence.repository;

public interface BattleshipLogic {

	String placeShips(String ships);
	
	String placeAIShips(String shipLengths);
	
	String AITurn(String difficulty);

	String playerTurn(String coords);
}
