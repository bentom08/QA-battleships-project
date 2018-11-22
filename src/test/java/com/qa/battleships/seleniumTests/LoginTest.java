package com.qa.battleships.seleniumTests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginTest {
	
	WebDriver driver;
	private static final String localhost = "http://localhost:3000";

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/Admin/Downloads/chromedriver_win32/chromedriver.exe");
		
		driver = new ChromeDriver();
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	@Ignore
	public void addUserTest() {
		driver.manage().window().maximize();
		driver.get(localhost);
		
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);
		
		login.goToSignUp();
		
		SignUpPage signUp = PageFactory.initElements(driver, SignUpPage.class);
		
		signUp.addUser("user", "password");
		
		LoggedInPage loggedIn = PageFactory.initElements(driver, LoggedInPage.class);
		
		assertEquals("You are currently logged in as user", loggedIn.getLoggedInUser());
		
		loggedIn.signOut();
	}
	
	@Test
	@Ignore
	public void loginTest() {
		driver.manage().window().maximize();
		driver.get(localhost);
		
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);
		
		login.login("test", "test");
		
		LoggedInPage loggedIn = PageFactory.initElements(driver, LoggedInPage.class);
		
		assertEquals("You are currently logged in as test", loggedIn.getLoggedInUser());
		
		loggedIn.signOut();
	}
	
	@Test
	@Ignore
	public void updatePasswordTest() {
		driver.manage().window().maximize();
		driver.get(localhost);
		
		LoginPage login = PageFactory.initElements(driver, LoginPage.class);
		
		login.login("test1", "test1");
		
		LoggedInPage loggedIn = PageFactory.initElements(driver, LoggedInPage.class);
		
		loggedIn.updatePassword();
		
		UpdatePasswordPage update = PageFactory.initElements(driver, UpdatePasswordPage.class);
		
		update.updatePassword("test1", "test2");
		
		loggedIn.signOut();
		
		login.login("test1", "test2");
		
		assertEquals("You are currently logged in as test1", loggedIn.getLoggedInUser());
	}
}
