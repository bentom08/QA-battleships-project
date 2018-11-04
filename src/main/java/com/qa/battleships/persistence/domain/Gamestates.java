package com.qa.battleships.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Gamestates {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gamestateID;

	@ManyToOne
	@JoinColumn(name = "username", table = "Users")
	@Column(columnDefinition = "VARCHAR(40)")
	private String username;
	
	@Column(name = "game_state", columnDefinition = "VARCHAR(3000)")
	private String gamestate;
	
	public Gamestates() {
		
	}
	
	public Gamestates(String username, String gamestate) {
		this.username = username;
		this.gamestate = gamestate;
	}

	public Long getGamestateID() {
		return gamestateID;
	}

	public void setGamestateID(Long gamestateID) {
		this.gamestateID = gamestateID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGamestate() {
		return gamestate;
	}

	public void setGamestate(String gamestate) {
		this.gamestate = gamestate;
	}
}
