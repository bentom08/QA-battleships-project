package com.qa.battleships.seleniumTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpdatePasswordPage {
	
	@FindBy(xpath = "//*[@id=\"changePassword\"]")
	private WebElement updatePassword;
	
	@FindBy(xpath = "//*[@id=\"password\"]")
	private WebElement newPassword;
	
	@FindBy(xpath = "//*[@id=\"reenter\"]")
	private WebElement reenterPass;
	
	@FindBy(xpath = "//*[@id=\"back\"]")
	private WebElement goBack;
	
	@FindBy(xpath = "//*[@id=\"oldPass\"]")
	private WebElement oldPassword;
	
	public void goBack() {
		goBack.click();
	}

	public void updatePassword(String oldPass, String newPass) {
		oldPassword.sendKeys(oldPass);
		newPassword.sendKeys(newPass);
		reenterPass.sendKeys(newPass);
		
		updatePassword.click();
	}
}
