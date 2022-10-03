package frameworkPractice.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkPratice.abstractActions.AbstractComponents;

public class CheckoutPage extends AbstractComponents{
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div/input[@placeholder='Select Country']")
	WebElement dropDown;
	@FindBy(xpath="//span[@class='ng-star-inserted']")
	WebElement findJapan;
	By country = By.xpath("//span[@class='ng-star-inserted']");
	@FindBy(xpath="//a[.='Place Order ']")
	WebElement placeOrderButton;
	@FindBy(css="label[class='ng-star-inserted']")
	WebElement orderNumber;
	
	public void selectCountry(String countryName)
	{
		dropDown.sendKeys(countryName);
		waitForElements(country);
		findJapan.click();
		
	}
	public ConformationPage placeOrder()
	{
		placeOrderButton.click();
		ConformationPage conformationPage = new ConformationPage(driver);
		return conformationPage;
	}
	
	
}


