package com.qa.battleships.persistence.repository;

import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.qa.battleships.service.BattleshipsOpponentLogic;
import com.qa.battleships.service.Grid;
import com.qa.battleships.service.Ship;
import com.qa.battleships.util.GridObject;
import com.qa.battleships.util.JSONUtil;

@ApplicationScoped
public class BattleshipLogicRepo implements BattleshipLogic {
	
	@Inject
	private JSONUtil util;
	
	@Inject
	private BattleshipsOpponentLogic logic;
	
	@Inject
	private Grid playerBoard;

	@Inject
	private Grid opponentBoard;
	
	private String TRUE = "{\"response\":\"true\"}";
	
	public String placeShips(String ships) {	
		int[][] grid = util.getObjectForJSON(ships, int[][].class);

		int noShips = Arrays.stream(grid).flatMapToInt(x -> Arrays.stream(x)).max().getAsInt();
		Ship[] shipArray = new Ship[noShips];
		
		for (int i = 1; i <= noShips; i++) {
			final int j = i;
			int shipLength = (int)Arrays.stream(grid).flatMapToInt(x -> Arrays.stream(x)).filter(x -> x == j).count();
			shipArray[i - 1] = new Ship(shipLength);
		}
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] != 0) {
					playerBoard.getGrid()[i][j].setHasShip(true);
					playerBoard.getGrid()[i][j].placeShip(shipArray[grid[i][j] - 1]);
				}
			}
		}
		
		return TRUE;
	}
	
	public String placeAIShips(String shipLengths) {	
		int[] shipLengthsArray = util.getObjectForJSON(shipLengths, int[].class);
		
		for(int i = 0; i < shipLengthsArray.length; i++) {
			opponentBoard.AIPlacement(new Ship(shipLengthsArray[i]));
		}
		
		int[][] grid = new int[10][10];
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (opponentBoard.getGrid()[i][j].getHasShip()) {
					grid[i][j] = 1;
				} else {
					grid[i][j] = 0;
				}
			}
		}
		
		return util.getJSONForObject(grid);
	}
	
	public String AITurn(String difficulty) {
		int diffInt = (int)util.getObjectForJSON(difficulty, Integer.class);
		
		int[] coords = logic.AITurn(playerBoard, diffInt);
		boolean hasSunk;
		
		if (playerBoard.getGrid()[coords[0]][coords[1]].getHasShip()) {
			hasSunk = playerBoard.getGrid()[coords[0]][coords[1]].getShip().getSunk();
		} else {
			hasSunk = false;
		}
		
		boolean allSunk = playerBoard.allSunk();
		
		return util.getJSONForObject(new GridObject(coords, hasSunk, allSunk));
	}
	
	public String playerTurn(String coords) {
		int[] intCoords = util.getObjectForJSON(coords, int[].class);
		
		opponentBoard.getGrid()[intCoords[0]][intCoords[1]].setIsHit(true);

		boolean sunk = opponentBoard.getGrid()[intCoords[0]][intCoords[1]].getShip().getSunk();
		boolean allSunk = opponentBoard.allSunk();
				
		return util.getJSONForObject(new GridObject(sunk, allSunk));
	}
}
