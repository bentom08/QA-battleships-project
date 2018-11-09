package com.qa.battleships.service;

public interface UserLoginService {

	boolean addUser(String jsonUser);

	boolean updatePassword(String newPassword, String username);

	boolean checkUsername(String username);

	boolean checkPassword(String newPassword, String username);
}
