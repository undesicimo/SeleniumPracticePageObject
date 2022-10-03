package frameworkPractice.test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import frameworkPractice.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UnoptimizedTestScript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
    	WebDriver driver = new ChromeDriver();
    	driver.get("https://rahulshettyacademy.com/client/");
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	//対象商品
    	String productName = "IPHONE 13 PRO";
    	//ログインする
    	driver.findElement(By.id("userEmail")).sendKeys("angel@gmail.com");
    	driver.findElement(By.id("userPassword")).sendKeys("Aaaaaa26");
    	driver.findElement(By.id("login")).click();
    	LandingPage landingPage = new LandingPage(driver);
    	List <WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
    	WebElement iPhone = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
    	iPhone.findElement(By.cssSelector(".card-body button:last-of-type")).click();
    	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    	WebElement container = driver.findElement(By.id("toast-container"));
    	WebElement loadingIcon = driver.findElement(By.cssSelector(".ng-animating"));
    	wait.until(ExpectedConditions.visibilityOf(container));
    	wait.until(ExpectedConditions.invisibilityOf(loadingIcon));
    	driver.findElement(By.xpath("//*[@routerlink='/dashboard/cart']")).click();
    	List <WebElement> productList = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
    	Boolean matchProduct = productList.stream().anyMatch(product->product.getText().equals(productName));
    	Assert.assertTrue(matchProduct);
    	if(matchProduct) {
    		System.out.println("It Matches");
    	}else {
    		System.out.println("No Match");
    	}
    	driver.findElement(By.xpath("//*[text()='Checkout']")).click();
    	WebElement dropDown = driver.findElement(By.xpath("//div/input[@placeholder='Select Country']"));
    	dropDown.sendKeys("Japan");
    	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[.=' Japan']"))));
    	driver.findElement(By.xpath("//span[.=' Japan']")).click();
    	driver.findElement(By.xpath("//a[.='Place Order ']")).click();
    	String orderNumber = driver.findElement(By.cssSelector("label[class='ng-star-inserted']")).getText();
    	System.out.println(orderNumber);
    	String[] splitNumber = orderNumber.split(" ");
    	System.out.println(splitNumber[1]);
    	
    			
    	
	}	

}
