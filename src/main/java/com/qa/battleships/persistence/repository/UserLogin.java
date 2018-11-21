package com.qa.battleships.persistence.repository;

import com.qa.battleships.persistence.domain.User;

public interface UserLogin {

	String updatePassword(User user);
	
	String addUser(User user);
	
	String checkUsername(String username);
	
	String checkPassword(User user);
	
	String deleteUser(String username);
}
