package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationDDTAndDataProvider extends TestBase

{

	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;

	@DataProvider(name="testData")
	public static Object[][] userData()
	{
		return new Object [][] {
			{"Ahmed", "Omar", "testzz2@gmail.com", "123456"},{"Mohamed", "Ali", "testw2@gmail.com", "123456"}
		};
	}

	@Test(priority=1,alwaysRun=true, dataProvider="testData")
	public void UserCanRegisterSuccessfully(String fname, String lname,String email, String password) throws InterruptedException

	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegisteration(fname, lname, email, password);
		Assert.assertTrue(registerObject.successMEssage.getText().contains("Your registration completed"));
		registerObject.userLogout();
		homeObject.openLoginPage();
		loginObject = new LoginPage(driver);
		loginObject.UserLogin(email, password);
		Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
		registerObject.userLogout();
		
	}

}