package com.qa.battleships.service;

public interface UserLoginService {

	String addUser(String jsonUser);

	String updatePassword(String jsonUser);

	String checkUsername(String username);

	String checkPassword(String jsonUser);
	
	String deleteUser(String deleteUser);

	String setUsername(String username);
	
	String getUsername();
}
