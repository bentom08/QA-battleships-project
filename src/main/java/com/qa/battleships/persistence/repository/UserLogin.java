package com.qa.battleships.persistence.repository;

public interface UserLogin {

	boolean updatePassword(String newPassword, String username);
	
	boolean addUser(String jsonUser);
	
	boolean checkUsername(String username);
	
	boolean checkPassword(String password, String username);
}
