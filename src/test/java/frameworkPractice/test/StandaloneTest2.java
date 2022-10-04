package frameworkPractice.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import frameworkPractice.pageobjects.CartPage;
import frameworkPractice.pageobjects.CheckoutPage;
import frameworkPractice.pageobjects.ConformationPage;
import frameworkPractice.pageobjects.OrderPage;
import frameworkPractice.pageobjects.ProductCatalogPage;
import frameworkPractice.testComponents.globalProperties;

public class StandaloneTest2 extends globalProperties{
	
		
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
		@Test(dependsOnMethods = {"submitOrder"} )
		public void orderValidation(String productName)
		{
			ProductCatalogPage productCatalogPage = landingPage.loginApplication("angel@gmail.com","A1234567");
			OrderPage orderPage =productCatalogPage.goToOrdersPage();
			Boolean checkOrderMatch = orderPage.nameColumnfind(productName);
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
