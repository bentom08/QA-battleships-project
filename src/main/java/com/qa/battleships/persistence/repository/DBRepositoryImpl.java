package com.qa.battleships.persistence.repository;

import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static javax.transaction.Transactional.TxType.REQUIRED;

import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;

import com.qa.battleships.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class DBRepositoryImpl implements UserLogin {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	@Inject
	private JSONUtil util;

	public boolean checkPassword(String password, String username) {
		return false;
	}
	
	public boolean checkUsername(String username) {
		return false;
	}
	
	public String addUser(String jsonUser) {
		return null;
	}
	
	public String updatePassword(String newPassword, String username) {
		return null;
	}

	public void setManager(EntityManager manager) {
		this.em = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}
