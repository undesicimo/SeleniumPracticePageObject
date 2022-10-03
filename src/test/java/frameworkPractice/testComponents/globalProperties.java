package frameworkPractice.testComponents;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import frameworkPractice.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class globalProperties {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeWebDriver() throws IOException
	{
		Properties properties = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\GlobalProperties\\global.properties");
		properties.load(file);
		String browserName = properties.getProperty("browser");
	
	if(browserName.equalsIgnoreCase("chrome"))
	{
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	}
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	return driver;
	}
	@BeforeMethod
	public LandingPage launchBrowser() throws IOException
	{
		driver = initializeWebDriver();
		landingPage = new LandingPage(driver);
		landingPage.goToPage();
		return landingPage;
		
	}
	@AfterMethod
	public void afterOperation()
	{
		driver.close();
	}

	
}
