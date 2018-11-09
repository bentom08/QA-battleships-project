package com.qa.battleships.service;

public interface UserLoginService {

	String addUser(String jsonUser);

	String updatePassword(String jsonUser);

	String checkUsername(String username);

	String checkPassword(String jsonUser);
}
