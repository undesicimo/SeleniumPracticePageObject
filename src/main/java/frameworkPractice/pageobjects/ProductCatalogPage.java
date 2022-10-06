package frameworkPractice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworkPratice.abstractActions.AbstractComponents;

public class ProductCatalogPage extends AbstractComponents{
	WebDriver driver;
	public ProductCatalogPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".col-lg-4")
	List <WebElement> products;
	
	By byProducts= By.cssSelector(".col-lg-4");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By container = By.id("toast-container");
	By loadingIcon = By.cssSelector(".ng-animating");
	@FindBy(xpath="//*[@routerlink='/dashboard/cart']")
	WebElement cartButton;
	
	public List<WebElement> getItems() {
		waitForElements(byProducts);
		return products;
	}
	
	public WebElement getProductName (String productName)
	{
		WebElement iPhone =	getItems().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return iPhone;
	}
	public void addToCart(String productName)
	{
		WebElement prod = getProductName(productName);
		prod.findElement(addToCart).click();
		waitForElements(container);
		
	}
	public CartPage goToCart() throws InterruptedException 
	{
		Thread.sleep(3000);
		cartButton.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	
	
}


