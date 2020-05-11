package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.EmailPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.UserRegistrationPage;

public class EmailFriendTest extends TestBase
{

	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	String productName = "Apple MacBook Pro 13-inch";
	SearchPage searchObject;
	ProductDetailsPage detailsObject;
	EmailPage emailObject;

	// 1- User Registration
	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccessfully()

	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegisteration("Ahmed", "Omar", "testmail165534@gmail.com", "123456");
		Assert.assertTrue(registerObject.successMEssage.getText().contains("Your registration completed"));
	}

	// 2- Search For Product

	@Test(priority=2)
	public void UserCanSearchWithAutoSuggest()
	{
		try {
			searchObject = new SearchPage(driver);
			searchObject.ProductSearchUsingAutoSuggest("MacB");
			detailsObject = new ProductDetailsPage(driver);
			Assert.assertEquals(detailsObject.productNamebreadCrumb.getText(), productName);

		} catch (Exception e) {
			System.out.println("Error occurred " + e.getMessage());
		}
	}
	// 3- Email To Friend
	@Test(priority=3)
	public void RegisteredUserCanSendProductToFriend()
	{
		detailsObject.openSendEmail();
		emailObject = new EmailPage(driver);
		emailObject.SendEmailToFriend("aaa@test.com", "Hello my Friend, check this product");
		Assert.assertTrue(emailObject.messageNotification.getText().contains("Your message has been sent."));
	}


	// 4- User Log Out

	@Test(priority=4)
	public void RegisteredUserCanLogout()
	{
		registerObject.userLogout();
	}
}
