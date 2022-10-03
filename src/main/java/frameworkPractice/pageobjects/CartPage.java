package frameworkPractice.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import frameworkPratice.abstractActions.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List <WebElement> productList;
	@FindBy(xpath="//*[text()='Checkout']")
	WebElement checkoutButton;
	
	
	public Boolean matchCheck(String productName) {
		Boolean matchProduct = productList.stream().anyMatch(product
				->product.getText().equals(productName));
		return matchProduct;
    	
	}
	public CheckoutPage checkOut()
	{
		checkoutButton.click();
		CheckoutPage checkOutPage = new CheckoutPage(driver);
		return checkOutPage;
	}
}
