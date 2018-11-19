package com.qa.battleships.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Ship {

	private int length;
	private int numberHit = 0;
	private boolean sunk = false;
	
	public Ship(int length) {
		this.length = length;
	}
	
	public Ship(Ship copy) {
		this.length = copy.length;
	}
	
	public int getShipLength() {
		return length;
	}
	
	public int getNumberHit() {
		return numberHit;
	}
	
	public void hit() {
		numberHit++;
		
		if (numberHit == length) {
			sunk = true;
		}
	}
	
	public boolean getSunk() {
		return sunk;
	}
} 