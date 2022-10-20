package frameworkPractice.testComponents;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import frameworkPractice.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeWebDriver() throws IOException
	{
		Properties properties = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\GlobalProperties\\global.properties");
		properties.load(file);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : properties.getProperty("browser");
		// properties.getProperty("browser");
	
	if(browserName.contains("chrome"))
	{
	WebDriverManager.chromedriver().setup();
	ChromeOptions options = new ChromeOptions();
	if(browserName.contains("headless"))
	{
	options.addArguments("headless");
	}
	driver = new ChromeDriver(options);
	driver.manage().window().setSize(new Dimension(1440,900));
	}
	
	else if(browserName.equalsIgnoreCase("firefox"))
	{
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}
	else if(browserName.equalsIgnoreCase("edge"))
	{
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		}
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException{
	String jsonFile = FileUtils.readFileToString(new File(filePath),
			StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonFile, new TypeReference <List<HashMap<String,String>>>(){});
		return data;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File file = new File (System.getProperty("user.dir")+"//reports"+ testCaseName + ".png");
		FileUtils.copyFile(src,file);
		return System.getProperty("user.dir")+"//reports"+ testCaseName + ".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchBrowser() throws IOException
	{
		driver = initializeWebDriver();
		landingPage = new LandingPage(driver);
		landingPage.goToPage();
		return landingPage;
		
	}
	@AfterMethod(alwaysRun=true)
	public void afterOperation()
	{
		driver.close();
	}

	
}
