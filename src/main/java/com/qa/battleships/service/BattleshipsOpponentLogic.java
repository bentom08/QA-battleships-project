package com.qa.battleships.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BattleshipsOpponentLogic {

	private Random r = new Random();
	private int[] previousHit = {-1, -1};
	private int direction;
	
	public int[] AITurn(Grid grid, int difficulty) {
		if (difficulty == 1) {
			return randomDifficulty(grid);
		} else if (difficulty == 2) {
			return mediumDifficulty(grid);
		} else if (difficulty == 3) {
			return hardDifficulty(grid);
		}
		return null;
	}
	
	private int[] randomDifficulty(Grid grid) {
		return getRandomSquare(grid);
	}
	
	private int[] mediumDifficulty(Grid grid) {
		List<int[]> hitSquares = getUnsunkShipSquares(grid);
		int[] coords = new int[2];
		
		if (hitSquares.size() == 0) {
			coords = getRandomSquare(grid);
			previousHit[0] = -1;
		} else {
			coords = getAdjacentSquare(grid, hitSquares, coords);
		}
				
		return coords;
	}
	
	private int[] hardDifficulty(Grid grid) {
		List<int[]> hitSquares = getUnsunkShipSquares(grid);
		int[] coords = new int[2];
		
		if (hitSquares.size() == 0) {
			coords = getRandomSquare(grid);
			previousHit[0] = -1;
		} else if (previousHit[0] != -1) {
			switch (direction) {
				case 0:
					coords[0] = previousHit[0] + 1;
					coords[1] = previousHit[1];
					break;
				case 1:
					coords[0] = previousHit[0] - 1;
					coords[1] = previousHit[1];
					break;
				case 2:
					coords[0] = previousHit[0];
					coords[1] = previousHit[1] + 1;
					break;
				case 3:
					coords[0] = previousHit[0];
					coords[1] = previousHit[1] - 1;
					break;
			}
			
			boolean reverse = false;
			boolean reverseNow;
			
			try {
				reverse = !grid.getGrid()[coords[0]][coords[1]].getHasShip();
				reverseNow = grid.getGrid()[coords[0]][coords[1]].getIsHit();
			} catch (ArrayIndexOutOfBoundsException e) {
				reverseNow = true;
			}
			
			int[] nextHit = {previousHit[0], previousHit[1]};
			previousHit[0] = coords[0];
			previousHit[1] = coords[1];
			
			if (reverse || reverseNow) {
				
				try {
					while (grid.getGrid()[nextHit[0]][nextHit[1]].getIsHit() && grid.getGrid()[nextHit[0]][nextHit[1]].getHasShip()) {
						switch (direction) {
							case 0:
								nextHit[0] -= 1;
								break;
							case 1:
								nextHit[0] += 1;
								break;
							case 2:
								nextHit[1] -= 1;
								break;
							case 3:
								nextHit[1] += 1;
								break;
						}
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					previousHit[0] = -1;
					return getAdjacentSquare(grid, hitSquares, coords);
				}
				
				if (grid.getGrid()[nextHit[0]][nextHit[1]].getIsHit()) {
					previousHit[0] = -1;
					return coords;
				}
				
				if (reverseNow) {
					coords[0] = nextHit[0];
					coords[1] = nextHit[1];
					previousHit[0] = coords[0];
					previousHit[1] = coords[1];
				}
				
				switch (direction) {
					case 0:
						if (!reverseNow) {
							previousHit[0] = nextHit[0] + 1;
						}
						direction = 1;
						break;
					case 1:
						direction = 0;
						if (!reverseNow) {
							previousHit[0] = nextHit[0] - 1;
						}
						break;
					case 2:
						direction = 3;
						if (!reverseNow) {
							previousHit[1] = nextHit[1] + 1;
						}
						break;
					case 3:
						direction = 2;
						if (!reverseNow) {
							previousHit[1] = nextHit[1] - 1;
						}
						break;
				}
			}
		} else {
			coords = getAdjacentSquare(grid, hitSquares, coords);
		}
				
		return coords;
	}
	
	private int[] getAdjacentSquare(Grid grid, List<int[]> hitSquares, int[] coords) {
		int i = 0;
		
		while (true) {
			direction = r.nextInt(4);
		
			switch (direction) {
				case 0:
					coords[0] = hitSquares.get(i)[0] + 1;
					coords[1] = hitSquares.get(i)[1];
					break;
				case 1:
					coords[0] = hitSquares.get(i)[0] - 1;
					coords[1] = hitSquares.get(i)[1];
					break;
				case 2:
					coords[0] = hitSquares.get(i)[0];
					coords[1] = hitSquares.get(i)[1] + 1;
					break;
				case 3:
					coords[0] = hitSquares.get(i)[0];
					coords[1] = hitSquares.get(i)[1] - 1;
					break;
			}
		
			if (coords[0] >= 0 && coords[1] >= 0 && coords[0] < grid.getGrid().length && coords[1] < grid.getGrid()[0].length && !grid.getGrid()[coords[0]][coords[1]].getIsHit()) {
				break;
			}
			
			List<Boolean> isHit = new ArrayList<>();
			
			try {isHit.add(grid.getGrid()[hitSquares.get(i)[0] + 1][hitSquares.get(i)[1]].getIsHit());} catch (ArrayIndexOutOfBoundsException e){}
			try {isHit.add(grid.getGrid()[hitSquares.get(i)[0] - 1][hitSquares.get(i)[1]].getIsHit());} catch (ArrayIndexOutOfBoundsException e){}
			try {isHit.add(grid.getGrid()[hitSquares.get(i)[0]][hitSquares.get(i)[1] + 1].getIsHit());} catch (ArrayIndexOutOfBoundsException e){}
			try {isHit.add(grid.getGrid()[hitSquares.get(i)[0]][hitSquares.get(i)[1] - 1].getIsHit());} catch (ArrayIndexOutOfBoundsException e){}
			
			if (isHit.stream().filter(k -> k == false).collect(Collectors.toList()).size() == 0) {
				i++;
			}
		}
		if (grid.getGrid()[coords[0]][coords[1]].getHasShip()) {
			previousHit[0] = coords[0];
			previousHit[1] = coords[1];
		}
		
		return coords;
	}
	
	private int[] getRandomSquare(Grid grid) {
		r = new Random();
		int[] coords = {r.nextInt(grid.getGrid().length), r.nextInt(grid.getGrid()[0].length)};
		
		while (grid.getGrid()[coords[0]][coords[1]].getIsHit()) {
			coords[0] = r.nextInt(6);
			coords[1] = r.nextInt(6);
		}
		return coords;
	}
	
	private List<int[]> getUnsunkShipSquares(Grid grid) {
		List<int[]> hitSquares = new ArrayList<>();
		
		for (int i = 0; i < grid.getGrid().length; i++) {
			for (int j = 0; j < grid.getGrid()[0].length; j++) {
				if (grid.getGrid()[i][j].getIsHit() && grid.getGrid()[i][j].getHasShip() && !grid.getGrid()[i][j].getShip().getSunk()) {
					hitSquares.add(new int[2]);
					hitSquares.get(hitSquares.size() - 1)[0] = i;
					hitSquares.get(hitSquares.size() - 1)[1] = j;
				}
			}
		}
		
		return hitSquares;
	}
}