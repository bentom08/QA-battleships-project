package com.qa.battleships.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GridSquare {

	boolean hasShip = false;
	boolean isHit = false;
	Ship ship = null;
	
	public boolean getHasShip() {
		return hasShip;
	}
	
	public void setHasShip(boolean hasShip) {
		this.hasShip = hasShip;
	}
	
	public boolean getIsHit() {
		return isHit;
	}
	
	public void setIsHit(boolean isHit) {
		this.isHit = isHit;
	}
	
	public void placeShip(Ship ship) {
		this.ship = ship;
	}
	
	public Ship getShip() {
		return ship;
	}
}