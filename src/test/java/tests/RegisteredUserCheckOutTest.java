package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CheckOutPage;
import pages.HomePage;
import pages.LoginPage;
import pages.OrderDetailsPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;
import pages.UserRegistrationPage;

public class RegisteredUserCheckOutTest extends TestBase
{
	/*
	 * 1- Register User
	 * 2- Search for product
	 * 3- Add to Cart
	 * 4- Check Out
	 * 5- Logout
	 */
	
	HomePage homeObject;
	UserRegistrationPage registerObject;
	LoginPage loginObject;
	String productName = "Apple MacBook Pro 13-inch";
	SearchPage searchObject;
	ProductDetailsPage detailsObject;
	ShoppingCartPage cartPage;
	CheckOutPage checkoutObject;
	OrderDetailsPage orderObject;
	
	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccessfully()

	{
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegisteration("Ahmed", "Omar", "testmail9767234@gmail.com", "123456");
		Assert.assertTrue(registerObject.successMEssage.getText().contains("Your registration completed"));
	}
	
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
	
	@Test(priority=3)
	public void UserCanAddProductToShoppingCart() throws InterruptedException
	{
		detailsObject = new ProductDetailsPage(driver);
		detailsObject.AddToCart();
		Thread.sleep(1000);
		driver.navigate().to("https://demo.nopcommerce.com" + "/cart");
		cartPage = new ShoppingCartPage(driver);
		Assert.assertTrue(cartPage.totalLbl.getText().contains("3,600"));
	}
	
	// Print invoice as PDF or print Page
	
	@Test(priority=4)
	public void UserCanCheckoutProduct() throws InterruptedException
	{
		checkoutObject = new CheckOutPage(driver);
		cartPage.openCheckoutPage();
		checkoutObject.RegisteredUserCheckOutProduct("Egypt", "test address", "1231424", "01214314141", "Cairo", productName);
		Assert.assertTrue(checkoutObject.productName.isDisplayed());
		Assert.assertTrue(checkoutObject.productName.getText().contains(productName));
		checkoutObject.confirmOrder();
		Assert.assertTrue(checkoutObject.ThankYoulbl.isDisplayed());
		checkoutObject.openOrderDetails();
		Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
		orderObject = new OrderDetailsPage(driver);
		orderObject.DownloadPDFInvoice();
		orderObject.PrintOrderDetails();
	}
	
	
	@Test(priority=5)
	public void RegisteredUserCanLogout()
	{
		registerObject.userLogout();
	}
}
