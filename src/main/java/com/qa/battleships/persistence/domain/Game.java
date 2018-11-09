package com.qa.battleships.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "games")
public class Game {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gameID;
	
	@ManyToOne(optional = false)
	@JoinColumn(referencedColumnName="username")
	private User user;
	
	@Column(columnDefinition = "TINYINT")
	private byte difficulty;
	
	private Long time;
	
	@Column(name = "number_of_hits", columnDefinition = "SMALLINT")
	private short numberOfHits;
	
	@Column(name = "number_of_misses", columnDefinition = "SMALLINT")
	private short numberOfMisses;
	
	@Column(name = "ai_hits", columnDefinition = "SMALLINT")
	private short aiHits;
	
	@Column(name = "ai_misses", columnDefinition = "SMALLINT")
	private short aiMisses;
	
	@Column(name = "board_size", columnDefinition = "TINYINT")
	private byte boardSize;
	
	@Column(name = "game_won")
	private boolean gameWon;
	
	public Game() {
		
	}

	public Game(User user, byte difficulty, Long time, short numberOfHits, short numberOfMisses, byte boardSize, boolean gameWon, short aiHits, short aiMisses) {
		this.user = user;
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

	public User getUsername() {
		return user;
	}

	public void setUsername(User user) {
		this.user = user;
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
