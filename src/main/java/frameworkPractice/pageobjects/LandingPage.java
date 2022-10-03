package frameworkPractice.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkPratice.abstractActions.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	//ユーザーメール入力フォーム
	@FindBy(id="userEmail")
	WebElement userEmail;
	//パスワード入力フォーム
	@FindBy(id="userPassword")
	WebElement passwordElem;
	//ログインボタン
	@FindBy(id="login")
	WebElement loginButton;
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public void goToPage()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}

	public ProductCatalogPage loginApplication(String email,String password) {
		
		userEmail.sendKeys(email);
		passwordElem.sendKeys(password);
		loginButton.click();
		ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
		return productCatalogPage;
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		String actualError =errorMessage.getText();
		return actualError;
	}
	
}
