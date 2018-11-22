package com.qa.battleships.seleniumTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage {

	@FindBy(xpath = "//*[@id=\"username\"]")
	private WebElement username;
	
	@FindBy(xpath = "//*[@id=\"password\"]")
	private WebElement password;
	
	@FindBy(xpath = "//*[@id=\"signUp\"]")
	private WebElement signUp;
	
	@FindBy(xpath = "//*[@id=\"reenter\"]")
	private WebElement reenterPass;
	
	@FindBy(xpath = "//*[@id=\"back\"]")
	private WebElement goBack;
	
	public void addUser(String user, String pass) {
		username.sendKeys(user);
		password.sendKeys(pass);
		reenterPass.sendKeys(pass);
		
		signUp.click();
	}
	
	public void backToLogin() {
		goBack.click();
	}
}
