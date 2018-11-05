package com.qa.battleships.persistence.repository;

public interface UserLogin {

	String updatePassword(String newPassword, String username);
	
	String addUser(String jsonUser);
	
	boolean checkUsername(String username);
	
	boolean checkPassword(String password, String username);
}
