package com.qa.battleships.persistence.repository;

import static javax.transaction.Transactional.TxType.SUPPORTS;
import static javax.transaction.Transactional.TxType.REQUIRED;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;

import com.qa.battleships.persistence.domain.Users;
import com.qa.battleships.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class DBRepositoryImpl implements UserLogin {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	@Inject
	private JSONUtil util;

	public boolean checkPassword(String password, String username) {
		Users user = em.find(Users.class, username);
		return BCrypt.checkpw(password, user.getPassword());
	}
	
	public boolean checkUsername(String username) {
		if (em.find(Users.class, username) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@Transactional(REQUIRED)
	public boolean addUser(String jsonUser) {
		Users user = util.getObjectForJSON(jsonUser, Users.class);
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		em.persist(user);
		return true;
	}
	
	@Transactional(REQUIRED)
	public boolean updatePassword(String newPassword, String username) {
		Users user = em.find(Users.class, username);
		user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
		return true;
	}

	public void setManager(EntityManager manager) {
		this.em = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}
