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
@Table(name = "gamestates")
public class Gamestate {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gamestateID;

	@ManyToOne(optional = false)
	@JoinColumn(referencedColumnName="username")
	private User user;
	
	@Column(name = "game_state", columnDefinition = "VARCHAR(3000)")
	private String gamestate;
	
	public Gamestate() {
		
	}
	
	public Gamestate(User user, String gamestate) {
		this.user = user;
		this.gamestate = gamestate;
	}

	public Long getGamestateID() {
		return gamestateID;
	}

	public void setGamestateID(Long gamestateID) {
		this.gamestateID = gamestateID;
	}

	public User getUser() {
		return user;
	}

	public void setUsername(User user) {
		this.user = user;
	}

	public String getGamestate() {
		return gamestate;
	}

	public void setGamestate(String gamestate) {
		this.gamestate = gamestate;
	}
}
