package com.qa.battleships.persistence.repository;

public interface UserLogin {

	String updatePassword(String jsonUser);
	
	String addUser(String jsonUser);
	
	String checkUsername(String username);
	
	String checkPassword(String jsonUser);
	
	String deleteUser(String username);
}
