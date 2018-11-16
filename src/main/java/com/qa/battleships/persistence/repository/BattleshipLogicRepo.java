package com.qa.battleships.persistence.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.qa.battleships.service.Ship;
import com.qa.battleships.util.GridObject;
import com.qa.battleships.util.JSONUtil;

@ApplicationScoped
public class BattleshipLogicRepo implements BattleshipLogic {
	
	@Inject
	JSONUtil util;
	
	public String placeShips(String ships) {
		int[][] grid = util.getObjectForJSON(ships, GridObject.class).getGrid();

		int noShips = Arrays.stream(grid).flatMapToInt(x -> Arrays.stream(x)).max().getAsInt();
		Ship[] shipArray = new Ship[noShips];
		
		for (int i = 1; i <= noShips; i++) {
			final int j = i;
			int shipLength = (int)Arrays.stream(grid).flatMapToInt(x -> Arrays.stream(x)).filter(x -> x == j).count();
			shipArray[i - 1] = new Ship(shipLength);
		}
		
		List<Integer> shipIDs = new ArrayList<>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				
			}
		}
		return null;
	}
}
