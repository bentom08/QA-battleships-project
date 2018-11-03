package com.qa.battleships.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Games {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gameID;
	
	@ManyToOne
	@JoinColumn(name = "username", table = "Users")
	private String username;
	
	private byte difficulty;
	
	private Long time;
	
	private short numberOfHits;
	
	private short numberOfMisses;
	
	private short aiHits;
	
	private short aiMisses;
	
	private byte boardSize;
	
	private boolean gameWon;
	
	public Games() {
		
	}

	public Games(String username, byte difficulty, Long time, short numberOfHits, short numberOfMisses, byte boardSize, boolean gameWon, short aiHits, short aiMisses) {
		this.username = username;
		this.difficulty = difficulty;
		this.time = time;
		this.numberOfHits = numberOfHits;
		this.numberOfMisses = numberOfMisses;
		this.boardSize = boardSize;
		this.gameWon = gameWon;
		this.aiHits = aiHits;
		this.aiMisses = aiMisses;
	}



	public Long getGameID() {
		return gameID;
	}

	public void setGameID(Long gameID) {
		this.gameID = gameID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(byte difficulty) {
		this.difficulty = difficulty;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public short getNumberOfHits() {
		return numberOfHits;
	}

	public void setNumberOfHits(short numberOfHits) {
		this.numberOfHits = numberOfHits;
	}

	public short getNumberOfMisses() {
		return numberOfMisses;
	}

	public void setNumberOfMisses(short numberOfMisses) {
		this.numberOfMisses = numberOfMisses;
	}

	public byte getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(byte boardSize) {
		this.boardSize = boardSize;
	}

	public boolean isGameWon() {
		return gameWon;
	}

	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}

	public short getAiMisses() {
		return aiMisses;
	}

	public void setAiMisses(short aiMisses) {
		this.aiMisses = aiMisses;
	}

	public short getAiHits() {
		return aiHits;
	}

	public void setAiHits(short aiHits) {
		this.aiHits = aiHits;
	}

}
