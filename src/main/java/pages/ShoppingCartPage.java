package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends PageBase
{

	public ShoppingCartPage(WebDriver driver) {
		super(driver);

	}
	
	@FindBy(name="removefromcart")
	WebElement removeCheck;
	
	@FindBy(name="updatecart")
	WebElement updateCartBtn;
	
	@FindBy(css="input.qty-input")
	public WebElement quantityTxt;
	
	@FindBy(css="td.subtotal")
	public WebElement totalLbl;
	
	@FindBy(id="termsofservice")
	WebElement agreeCheckbox;
	
	@FindBy(id="checkout")
	WebElement checkoutBtn;
	
	public void RemoveProductFromCart()
	{
		clickButton(removeCheck);
		clickButton(updateCartBtn);
	}
	
	public void UpdateProuctQuantityInCart(String quantity) throws InterruptedException
	{
		// clear quantity textbox
		clearText(quantityTxt);
		setTextElementText(quantityTxt, quantity);
		clickButton(updateCartBtn);
	}
	
	public void openCheckoutPage()
	{
		clickButton(agreeCheckbox);
		clickButton(checkoutBtn);
	}
}
