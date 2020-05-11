package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends PageBase {

	public SearchPage(WebDriver driver) {
		super(driver);
}
	
	@FindBy(id="small-searchterms")
	WebElement searchTextBox;
	
	@FindBy(css="input.button-1.search-box-button")
	WebElement searchBtn;
	
	@FindBy(id="ui-id-1")
	java.util.List<WebElement> ProductList;
	
	@FindBy(linkText="Apple MacBook Pro 13-inch")
	WebElement productTitle;
	
	public void ProductSearch(String productName)
	{
		setTextElementText(searchTextBox, productName);
		clickButton(searchBtn);
	}
	
	public void OpenProductDetailsPage()
	{
		clickButton(productTitle);
	}
	
	public void ProductSearchUsingAutoSuggest(String searchTxt)
	{
		setTextElementText(searchTextBox, searchTxt);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ProductList.get(0).click();
		
	}

	
}
