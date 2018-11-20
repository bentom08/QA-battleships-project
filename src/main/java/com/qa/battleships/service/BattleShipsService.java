package com.qa.battleships.service;

public interface BattleShipsService {
	
	String placeShips(String ships);
	
	String placeAIShips(String shipLengths);
	
	String AITurn(String difficulty);
	
	String playerTurn(String coords);
}
