package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.WishListPage;

public class AddProductToWishListTest extends TestBase
{
	
	String productName = "Apple MacBook Pro 13-inch";
	SearchPage searchObject;
	WishListPage wishlistObject;
	ProductDetailsPage productDetails;
	

	@Test(priority=1)
	public void UserCanSearchWithAutoSuggest()
	{
			searchObject = new SearchPage(driver);
			searchObject.ProductSearchUsingAutoSuggest("MacB");
			productDetails = new ProductDetailsPage(driver);
			Assert.assertEquals(productDetails.productNamebreadCrumb.getText(), productName);	
	}
	
	@Test(priority=2)
	public void UserCanAddProductToWishList()
	{
	productDetails =new ProductDetailsPage(driver);
	productDetails.AddProductToWishList();
	driver.navigate().to("https://demo.nopcommerce.com" + "/wishlist");
	wishlistObject = new WishListPage(driver);
	Assert.assertTrue(wishlistObject.wishlistHeader.isDisplayed());
	Assert.assertTrue(wishlistObject.productCell.getText().contains(productName));
	
	}
	
	@Test(priority=3)
	public void UserCanRemoveProductFromCart()
	{
		wishlistObject = new WishListPage(driver);
		wishlistObject.removeProductFromWishList();
		Assert.assertTrue(wishlistObject.EmptyCardLbl.getText().contains("The wishlist is empty!"));
	}

}
