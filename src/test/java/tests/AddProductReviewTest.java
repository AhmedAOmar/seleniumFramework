package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.ProductReviewPage;
import pages.SearchPage;
import pages.UserRegistrationPage;

public class AddProductReviewTest extends TestBase
{

	/*
	 * 1- User Registration
	 * 2- Search for product
	 * 3- Add Review
	 * 4- Logout
	 */

	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	String productName = "Apple MacBook Pro 13-inch";
	SearchPage searchObject;
	ProductDetailsPage detailsObject;
	ProductReviewPage reviewObject;

	// 1- User Registration
	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccessfully()

	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegisteration("Ahmed", "Omar", "testmail3212264@gmail.com", "123456");
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
	// 3- Add Review
	@Test(priority=3)
	public void RegisteredUserCanReviewProduct()
	{
		detailsObject.openAddReviewPage();
		reviewObject = new ProductReviewPage(driver);
		reviewObject.AddProductReview("test", "testreview");
		Assert.assertTrue(reviewObject.reviewNotification.getText()
				.contains("Product review is successfully added."));
	}


	// 4- User Log Out

	@Test(priority=4)
	public void RegisteredUserCanLogout()
	{
		registerObject.userLogout();
	}

}
