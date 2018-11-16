package com.qa.battleships.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Grid {

	GridSquare[][] grid;
	List<Ship> ships;
	Random r;
	
	public Grid(int x, int y) {
		grid = new GridSquare[x][y];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = new GridSquare();
			}
		}
		
		ships = new ArrayList<>();
	}
	
	public Grid(Grid copy) {
		grid = new GridSquare[copy.grid.length][copy.grid[0].length];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = new GridSquare();
			}
		}
		
		ships = new ArrayList<>();
	}
	
	public void AIPlacement(Ship ship) {
		r = new Random();
		
		int x;
		int y;
		
		boolean placed = false;
		
		while (!placed) {
			x = r.nextInt(grid.length);
			y = r.nextInt(grid[0].length);
			placed = AIDirection(ship, x, y);
			System.out.println(x + " " + y);
		}
		
		ships.add(ship);
	}
		
	private boolean AIDirection(Ship ship, int x, int y) {
		int length = ship.getShipLength();
		while (true) {
			int direction = r.nextInt(4);
			
			if (direction == 0) {
				
				if (grid[0].length < y + length) {
					continue;
				}
				
				for (int i = 0; i < length; i++) {
					if (grid[x][y + i].getHasShip()) {
						return false;
					}
				}
				
				for (int i = 0; i < length; i++) {
					grid[x][y + i].setHasShip(true);
					grid[x][y + i].placeShip(ship);
				}
				
				break;
				
			} else if (direction == 1) {

				if (y - length + 1 < 0) {
					continue;
				}
				
				for (int i = 0; i < length; i++) {
					if (grid[x][y - i].getHasShip()) {
						return false;
					}
				}
				
				for (int i = 0; i < length; i++) {
					grid[x][y - i].setHasShip(true);
					grid[x][y - i].placeShip(ship);
				}
				
				break;
				
			} else if (direction == 2) {
				
				if (x - length + 1 < 0) {
					continue;
				}
				
				for (int i = 0; i < length; i++) {
					if (grid[x - i][y].getHasShip()) {
						return false;
					}
				}
				
				for (int i = 0; i < length; i++) {
					grid[x - i][y].setHasShip(true);
					grid[x - i][y].placeShip(ship);
				}
				
				break;
				
			} else if (direction == 3) {
				
				if (grid.length < x + length) {
					continue;
				}
				
				for (int i = 0; i < length; i++) {
					if (grid[x + i][y].getHasShip()) {
						return false;
					}
				}
				
				for (int i = 0; i < length; i++) {
					grid[x + i][y].setHasShip(true);
					grid[x + i][y].placeShip(ship);
				}
				
				break;
				
			}
			
			}
			return true;
	}
	
	public void shipPlaced(Ship ship) {
		boolean x = false;
		//while ( x == false) {
			 //x = placeShip(ship);
		//}
		ships.add(ship);
		System.out.println("Ship Placed!");
	}
	
	public GridSquare[][] getGrid() {
		return grid;
	}
	
	public boolean allSunk() {
		if (ships.stream().filter(i -> !i.getSunk()).collect(Collectors.toList()).size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}