package frameworkPractice.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkPratice.abstractActions.AbstractComponents;

public class OrderPage extends AbstractComponents {
	WebDriver driver;
	public OrderPage (WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//tr/td[2]")
	List <WebElement> nameColumn;
	
	public Boolean nameColumnfind(String productName)
	{
		Boolean matchProduct =nameColumn.stream().anyMatch(product
				->product.getText().equalsIgnoreCase(productName));
		return matchProduct;
		
	}
	
	
	
	
}

	

