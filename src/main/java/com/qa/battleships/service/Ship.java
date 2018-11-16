package com.qa.battleships.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Ship {

	private int length;
	private int numberHit = 0;
	private String shipType;
	private boolean sunk = false;
	
	public Ship(int length, String shipType) {
		this.length = length;
		this.shipType = shipType;
	}
	
	public Ship(Ship copy) {
		this.length = copy.length;
		this.shipType = copy.shipType;
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
			System.out.println("You sunk my " + shipType);
			sunk = true;
		}
	}
	
	public boolean getSunk() {
		return sunk;
	}
} 