package com.qa.battleships.persistence.repository;

public interface UserLogin {

	String updatePassword(String newPassword, String usernme);
	
	String addUser(String jsonUser);
	
	boolean checkUsername(String username);
	
	boolean checkPassword(String password);
}
