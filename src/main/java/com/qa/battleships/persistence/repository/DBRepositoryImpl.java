package com.qa.battleships.persistence.repository;

import static javax.transaction.Transactional.TxType.SUPPORTS;
import static javax.transaction.Transactional.TxType.REQUIRED;

import javax.transaction.Transactional;

@Transactional(SUPPORTS)
public class DBRepositoryImpl implements UserLogin {

	public boolean checkPassword(String password) {
		
	}
	
	public boolean checkUsername(String username) {
		
	}
	
	public String addUser(String jsonUser) {
		
	}
	
	public String updatePassword(String newPassword, String username) {
		
	}
}
