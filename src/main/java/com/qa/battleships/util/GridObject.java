package com.qa.battleships.util;

public class GridObject {
	
	private int[] coords;
	private boolean shipSunk;
	
	public GridObject(int[] coords, boolean shipSunk) {
		this.coords = coords;
		this.shipSunk = shipSunk;
	}
	
	public int[] getCoords() {
		return coords;
	}
	
	public boolean getSunk() {
		return shipSunk;
	}
	
	public void setSunk(boolean shipSunk) {
		this.shipSunk = shipSunk;
	}
	
	public void setCoords(int[] coords) {
		this.coords = coords;
	}
}
