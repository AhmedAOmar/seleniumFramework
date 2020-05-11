package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishListPage extends PageBase

{

	public WishListPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css="td.product")
	public WebElement productCell;
	
	@FindBy(css="h1")
	public WebElement wishlistHeader;
	
	@FindBy(name="updatecart")
	private WebElement updateWishlistBtn;
	
	@FindBy(name="removefromcart")
	private WebElement removeFromCartCheck;
	
	@FindBy(css="div.no-data")
	public WebElement EmptyCardLbl;
	
	public void removeProductFromWishList()
	{
		clickButton(removeFromCartCheck);
		clickButton(updateWishlistBtn);

	}

}
