package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.LoadProperties;
import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationWithDDTPropertiesFile extends TestBase

{

	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	String firstname = LoadProperties.userData.getProperty("firstname");
	String lastname = LoadProperties.userData.getProperty("lastname");
	String email = LoadProperties.userData.getProperty("email");
	String password = LoadProperties.userData.getProperty("password");

	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccessfully()

	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegisteration(firstname, lastname, email, password);
		Assert.assertTrue(registerObject.successMEssage.getText().contains("Your registration completed"));
	}

	@Test(priority=2)
	public void RegisteredUserCanLogout()
	{
		registerObject.userLogout();
	}
	@Test(priority=3)
	public void RegisteredUserCanLogin()
	{
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.UserLogin(email, password);
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
	}

}
