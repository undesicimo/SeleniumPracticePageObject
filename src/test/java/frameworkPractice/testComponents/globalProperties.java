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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException{
	String jsonFile = FileUtils.readFileToString(new File(filePath),
			StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonFile, new TypeReference <List<HashMap<String,String>>>(){});
		return data;
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
