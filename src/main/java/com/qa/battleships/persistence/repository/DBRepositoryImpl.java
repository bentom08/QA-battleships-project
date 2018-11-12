package com.qa.battleships.persistence.repository;

import static javax.transaction.Transactional.TxType.SUPPORTS;
import static javax.transaction.Transactional.TxType.REQUIRED;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;

import com.qa.battleships.persistence.domain.User;
import com.qa.battleships.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class DBRepositoryImpl implements UserLogin {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	@Inject
	private JSONUtil util;
	
	private String TRUE = "{\"response\":\"true\"}";
	private String FALSE = "{\"response\":\"false\"}";

	public String checkPassword(String jsonUser) {
		User userObj = util.getObjectForJSON(jsonUser, User.class);
		User user = em.find(User.class, userObj.getUsername());
		if (BCrypt.checkpw(userObj.getPassword(), user.getPassword())) {
			return TRUE;
		} else {
			return FALSE;
		}
	}
	
	public String checkUsername(String username) {
		if (em.find(User.class, username) == null) {
			return FALSE;
		} else {
			return TRUE;
		}
	}
	
	@Transactional(REQUIRED)
	public String addUser(String jsonUser) {
		User user = util.getObjectForJSON(jsonUser, User.class);
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		em.persist(user);
		return TRUE;
	}
	
	@Transactional(REQUIRED)
	public String updatePassword(String jsonUser) {
		User userObj = util.getObjectForJSON(jsonUser, User.class);
		User user = em.find(User.class, userObj.getUsername());
		user.setPassword(BCrypt.hashpw(userObj.getPassword(), BCrypt.gensalt()));
		return TRUE;
	}
	
	@Transactional(REQUIRED)
	public String deleteUser(String username) {
		User user = em.find(User.class, username);
		if (user == null) {
			return FALSE;
		} else {
			em.remove(user);
			return TRUE;
		}
	}
	
	public void setManager(EntityManager manager) {
		this.em = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

	

}
