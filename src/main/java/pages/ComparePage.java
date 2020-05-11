package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ComparePage extends PageBase

{

	public ComparePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="a.clear-list")
	private WebElement clerListBtn;

	@FindBy(css="div.no-data")
	public WebElement noDataLbl;

	@FindBy(css="table.compare-products-table")
	private WebElement compareTable;

	@FindBy(tagName="tr")
	public List<WebElement>allRows;

	@FindBy(tagName="td")
	private List<WebElement>allCol;
	
	@FindBy(linkText="Apple MacBook Pro 13-inch")
	public WebElement firstProductName;

	@FindBy(linkText="Asus N551JK-XO076H Laptop")
	public WebElement secondProductName;
	
	public void clearCompareList()
	{
		clickButton(clerListBtn);
	}

	public void CompareProducts()
	{
		// Get all Rows
		System.out.println(allRows.size());

		// Get data from each Row

		for(WebElement row : allRows)
		{
			System.out.println(row.getText() + "\t");
			for(WebElement col : allCol) 
			{
				System.out.println(col.getText() + "\t");
			}
		}
	}

}
