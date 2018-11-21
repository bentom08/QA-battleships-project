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
	
	private String TRUE = "{\"response\":\"true\"}";
	private String FALSE = "{\"response\":\"false\"}";

	public String checkPassword(User userObj) {
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
	public String addUser(User user) {
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		em.persist(user);
		return TRUE;
	}
	
	@Transactional(REQUIRED)
	public String updatePassword(User userObj) {
		User user = em.find(User.class, userObj.getUsername());
		user.setPassword(BCrypt.hashpw(userObj.getPassword(), BCrypt.gensalt()));
		return TRUE;
	}
	
	@Transactional(REQUIRED)
	public String deleteUser(String username) {
		User userInDB = em.find(User.class, username);
		if (userInDB == null) {
			return FALSE;
		} else {
			em.remove(userInDB);
			return TRUE;
		}
	}
	
	public void setManager(EntityManager manager) {
		this.em = manager;
	}
}
