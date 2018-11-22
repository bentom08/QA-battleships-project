package com.qa.battleships.seleniumTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	@FindBy(xpath = "//*[@id=\"username\"]")
	private WebElement username;
	
	@FindBy(xpath = "//*[@id=\"password\"]")
	private WebElement password;
	
	@FindBy(xpath = "//*[@id=\"signUp\"]")
	private WebElement signUp;
	
	@FindBy(xpath = "//*[@id=\"login\"]")
	private WebElement login;
	
	public void goToSignUp() {
		signUp.click();
	}
	
	public void login(String user, String pass) {
		username.sendKeys(user);
		password.sendKeys(pass);
		
		login.click();
	}
}
