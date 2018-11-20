package com.qa.battleships.util;

public class GridObject {
	
	private int[] coords = {0, 0};
	private boolean shipSunk;
	private boolean allSunk;
	
	public GridObject(int[] coords, boolean shipSunk, boolean allSunk) {
		this.coords = coords;
		this.shipSunk = shipSunk;
		this.allSunk = allSunk;
	}
	
	public GridObject(boolean shipSunk, boolean allSunk) {
		this.shipSunk = shipSunk;
		this.allSunk = allSunk;
	}
	
	public int[] getCoords() {
		return coords;
	}
	
	public boolean getSunk() {
		return shipSunk;
	}
	
	public boolean getAllSunk() {
		return allSunk;
	}
	
	public void setSunk(boolean shipSunk) {
		this.shipSunk = shipSunk;
	}
	
	public void setCoords(int[] coords) {
		this.coords = coords;
	}
}
