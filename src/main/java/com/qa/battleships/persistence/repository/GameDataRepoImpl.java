package com.qa.battleships.persistence.repository;

import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import static javax.transaction.Transactional.TxType.REQUIRED;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.qa.battleships.persistence.domain.Game;
import com.qa.battleships.persistence.domain.User;

@Transactional(SUPPORTS)
@Default
public class GameDataRepoImpl implements GameDataRepo {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	private String TRUE = "{\"response\":\"true\"}";

	@Transactional(REQUIRED)
	public String addGame(String username, Game game) {
		User user = em.find(User.class, username);
		game.setUsername(user);
		em.persist(game);
		return TRUE;
	}

	public Collection<Game> getUserGames(String username) {
		return em.createQuery("SELECT g FROM Game g WHERE g.user.username LIKE :username").setParameter("username", username).getResultList();
	}

	public Collection<Game> getAllGames() {
		return em.createQuery("SELECT g FROM Game g").getResultList();
	}

	public void setManager(EntityManager manager) {
		this.em = manager;
	}
}
