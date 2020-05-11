package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;

public class AddProductToShoppingCartTest extends TestBase
{
	SearchPage searchObject;
	ProductDetailsPage productDetails;
	ShoppingCartPage cartPage;
	String productName = "Apple MacBook Pro 13-inch";
	
	@Test(priority=1)
	public void UserCanSearchWithAutoSuggest()
	{
			searchObject = new SearchPage(driver);
			searchObject.ProductSearchUsingAutoSuggest("MacB");
			productDetails = new ProductDetailsPage(driver);
			Assert.assertEquals(productDetails.productNamebreadCrumb.getText(), productName);
	}
	
	@Test(priority=2)
	public void UserCanAddProductToShoppingCart() throws InterruptedException
	{
		productDetails = new ProductDetailsPage(driver);
		productDetails.AddToCart();
		Thread.sleep(1000);
		driver.navigate().to("https://demo.nopcommerce.com" + "/cart");
		cartPage = new ShoppingCartPage(driver);
		Assert.assertTrue(cartPage.totalLbl.getText().contains("3,600"));
	}
	
	@Test(priority=3)
	public void UserCanRemoveProductFromCart()
	{
		cartPage = new ShoppingCartPage(driver);
		cartPage.RemoveProductFromCart();
	}

}
