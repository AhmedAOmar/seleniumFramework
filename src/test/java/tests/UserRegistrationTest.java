package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTest extends TestBase

{

	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;

	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccessfully()

	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegisteration("Ahmed", "Omar", "testmail187234@gmail.com", "123456");
		Assert.assertTrue(registerObject.successMEssage.getText().contains("Your registration completed"));
	}

	@Test(dependsOnMethods= {"UserCanRegisterSuccessfully"})
	public void RegisteredUserCanLogout()
	{
		registerObject.userLogout();
	}
	@Test(dependsOnMethods= {"RegisteredUserCanLogout"})
	public void RegisteredUserCanLogin()
	{
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.UserLogin("testmail1234@gmail.com", "123456");
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
	}

}
