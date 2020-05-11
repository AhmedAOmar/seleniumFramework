package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.UserRegistrationPage;

public class MyAccountTest extends TestBase {


	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	MyAccountPage myAccountObject;
	String oldPassword = "1234567";
	String newPassword = "12345678";
	String firstName = "Ahmed";
	String lastName = "Omar";
	String email = "test76232@gamil.com";


	@Test(priority=1)
	public void UserCanRegisterSuccessfully()

	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegisteration(firstName, lastName, email, oldPassword);
		Assert.assertTrue(registerObject.successMEssage.getText().contains("Your registration completed"));
	}

	@Test(priority=2)
	public void RegisteredUserCanChangePassword()
	{
		myAccountObject = new MyAccountPage(driver);
		registerObject.openMyAccountPage();
		myAccountObject.openChangePasswordPage();
		myAccountObject.ChangePassword(oldPassword, newPassword);
		Assert.assertTrue(myAccountObject.resultbl.getText().contains("Password was changed"));
	}

	@Test(priority=3)
	public void RegisteredUserCanLogout()
	{
		registerObject.userLogout();
	}
	@Test(priority=4)
	public void RegisteredUserCanLogin()
	{
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.UserLogin(email, newPassword);
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
	}

}
