package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationDTTJavaFaker extends TestBase

{

	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	Faker fakerData = new Faker();
	String firstname = fakerData.name().firstName();
	String lastname = fakerData.name().lastName();
	String email = fakerData.internet().emailAddress();
	String password = fakerData.number().digits(8).toString();

	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccessfully()

	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegisteration(firstname, lastname, email, password);
		System.out.println("The user data is: "+ firstname + " " + lastname + " " + email + " " + password);
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
		System.out.println("The user data is: " +  email + " " + password);
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
	}

}
