package com.qa.battleships.seleniumTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoggedInPage {
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div")
	private WebElement loggedIn;

	@FindBy(xpath = "//*[@id=\"signOut\"]")
	private WebElement signOut;
	
	@FindBy(xpath = "//*[@id=\"changePassword\"]")
	private WebElement updatePassword;
	
	public String getLoggedInUser() {
		return loggedIn.getText();
	}
	
	public void signOut() {
		signOut.click();
	}
	
	public void updatePassword() {
		updatePassword.click();
	}
}
