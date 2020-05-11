package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CheckOutPage;
import pages.HomePage;
import pages.OrderDetailsPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;

public class GuestCheckOutTest extends TestBase
{
	HomePage homeObject;
	String productName = "Apple MacBook Pro 13-inch";
	SearchPage searchObject;
	ProductDetailsPage detailsObject;
	ShoppingCartPage cartPage;
	CheckOutPage checkoutObject;
	OrderDetailsPage orderObject;
	
	@Test(priority=1)
	public void UserCanSearchWithAutoSuggest()
	{
	searchObject = new SearchPage(driver);
	searchObject.ProductSearchUsingAutoSuggest("MacB");
	detailsObject = new ProductDetailsPage(driver);
	Assert.assertEquals(detailsObject.productNamebreadCrumb.getText(), productName);
	}
	
	@Test(priority=2)
	public void UserCanAddProductToShoppingCart() throws InterruptedException
	{
		detailsObject = new ProductDetailsPage(driver);
		detailsObject.AddToCart();
		Thread.sleep(1000);
		driver.navigate().to("https://demo.nopcommerce.com" + "/cart");
		cartPage = new ShoppingCartPage(driver);
		Assert.assertTrue(cartPage.totalLbl.getText().contains("3,600"));
	}
	
	@Test(priority=3)
	public void UserCanCheckoutProduct() throws InterruptedException
	{
		checkoutObject = new CheckOutPage(driver);
		cartPage.openCheckoutPage();
		checkoutObject.openCheckoutPageAsGuest();
		checkoutObject.CheckoutProduct("Ahmed", "Omar", "testeamil@gmail.com",
				"Egypt", "testaddress", "123456", "0112324244", "Cairo", productName);
		Thread.sleep(1000);
		Assert.assertTrue(checkoutObject.productName.isDisplayed());
		Assert.assertTrue(checkoutObject.productName.getText().contains(productName));
		checkoutObject.confirmOrder();
		Assert.assertTrue(checkoutObject.ThankYoulbl.isDisplayed());
	}
	
	@Test(priority=4)
	public void UserCanViewOrderDetails() throws InterruptedException
	{
		orderObject = new OrderDetailsPage(driver);
		checkoutObject.openOrderDetails();
		Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
		orderObject.DownloadPDFInvoice();
		Thread.sleep(1000);
		orderObject.PrintOrderDetails();
	}

}
