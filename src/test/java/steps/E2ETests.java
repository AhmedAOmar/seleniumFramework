package steps;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CheckOutPage;
import pages.OrderDetailsPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;
import tests.TestBase;

public class E2ETests extends TestBase{

	SearchPage searchPage;
	ProductDetailsPage productDetails;
	ShoppingCartPage cartPage;
	CheckOutPage checkoutPage;
	OrderDetailsPage orderPage;
	String productName = "Apple MacBook Pro 13-inch";
	
	@Given("user is on Home Page")
	public void user_is_on_Home_Page() {
	  Assert.assertTrue(driver.getCurrentUrl().contains("demo.nopcommerce.com"));
	}

	@When("he searches for {string}")
	public void he_searches_for(String string) {
	  searchPage = new SearchPage(driver);
	  searchPage.ProductSearchUsingAutoSuggest(productName);
	  productDetails = new ProductDetailsPage(driver);
	  Assert.assertTrue(productDetails.productNamebreadCrumb.getText().contains(productName));
	}

	@When("choose to buy two items")
	public void choose_to_buy_two_items() {
	 cartPage = new ShoppingCartPage(driver);
	 productDetails.AddToCart();
	 driver.navigate().to("http://demo.nopcommerce.com/" + "cart");
	}

	@When("moves to checkout cart and enter personal details on checkout page and place the order")
	public void moves_to_checkout_cart_and_enter_personal_details_on_checkout_page_and_place_the_order() throws InterruptedException {
	  checkoutPage = new CheckOutPage(driver);
	  cartPage.openCheckoutPage();
	  checkoutPage.openCheckoutPageAsGuest();
	  checkoutPage.CheckoutProduct("test", "user", "testmails2@gmail.com", 
			  "Egypt", "test address", "23242", "012131341314", "Cairo", productName);
	  Assert.assertTrue(checkoutPage.productName.isDisplayed());
	  Assert.assertTrue(checkoutPage.productName.getText().contains(productName));
	  checkoutPage.confirmOrder();
	  Assert.assertTrue(checkoutPage.ThankYoulbl.isDisplayed());
	}

	@Then("he can view the order and download the invoice")
	public void he_can_view_the_order_and_download_the_invoice() throws InterruptedException {
	  orderPage = new OrderDetailsPage(driver);
	  checkoutPage.openOrderDetails();
	  Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
	  orderPage.DownloadPDFInvoice();
	  orderPage.PrintOrderDetails();
	}
}
