package frameworkPractice.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import frameworkPractice.pageobjects.CartPage;
import frameworkPractice.pageobjects.CheckoutPage;
import frameworkPractice.pageobjects.ConformationPage;
import frameworkPractice.pageobjects.OrderPage;
import frameworkPractice.pageobjects.ProductCatalogPage;
import frameworkPractice.testComponents.BaseTest;

public class StandaloneTest2 extends BaseTest{
	
		
		@Test(dataProvider="getData" ,groups={"Purchase"})
		public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
    	ProductCatalogPage productCatalogPage = landingPage.loginApplication(input.get("email"),input.get("password"));
    	
    	productCatalogPage.getItems();
    	productCatalogPage.getProductName(input.get("product"));  
    	productCatalogPage.addToCart(input.get("product"));
    	Thread.sleep(5000);
    	CartPage cartPage =productCatalogPage.goToCart();
    	Boolean match = cartPage.matchCheck(input.get("product"));
    	Assert.assertTrue(match);
    	String countryName= "Japan";
    	CheckoutPage checkOutPage =cartPage.checkOut();
    	checkOutPage.selectCountry(countryName);
    	ConformationPage conformationpage =checkOutPage.placeOrder();
    	conformationpage.printOrderNumber();
    	String conformationMessage =conformationpage.getConformationMessage();
    	Assert.assertTrue(conformationMessage.trim().equalsIgnoreCase("Thankyou for the order."));
    		
	}	
		@Test(dependsOnMethods = {"submitOrder"},groups= {"Error"},dataProvider="getData" )
		public void orderValidation(HashMap<String,String> input)  throws IOException, InterruptedException
		{
			ProductCatalogPage productCatalogPage = landingPage.loginApplication(input.get("email"),input.get("password"));
			OrderPage orderPage =productCatalogPage.goToOrdersPage();
			Boolean checkOrderMatch = orderPage.nameColumnfind(input.get("product"));
			Assert.assertTrue(checkOrderMatch);
			
			
		}
		
		
		
		@DataProvider
		public Object[][] getData() throws IOException
		{
		List <HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+
				"\\src\\test\\java\\framePractice\\data\\data.json");
		return new Object [][] {{data.get(0)},{data.get(1)}};
		
		}
	
}
