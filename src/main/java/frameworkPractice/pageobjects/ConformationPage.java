package frameworkPractice.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkPratice.abstractActions.AbstractComponents;

public class ConformationPage extends AbstractComponents{
	WebDriver driver;
	public ConformationPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="label[class='ng-star-inserted']")
	WebElement orderNumber;
	@FindBy(css=".hero-primary")
	WebElement confirmationMessageEle;


	public void printOrderNumber()
	{
		String actualOrderNumber = orderNumber.getText();
		String[] splitNumber = actualOrderNumber.split(" ");
    	System.out.println(splitNumber[1]);
	}
	public String getConformationMessage()
	{
		String confirmationMessage= confirmationMessageEle.getText();
		return confirmationMessage;
	}
	
	
}


