package frameworkPractice.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import frameworkPractice.pageobjects.CartPage;
import frameworkPractice.pageobjects.CheckoutPage;
import frameworkPractice.pageobjects.ConformationPage;
import frameworkPractice.pageobjects.OrderPage;
import frameworkPractice.pageobjects.ProductCatalogPage;
import frameworkPractice.testComponents.globalProperties;

public class StandaloneTest2 extends globalProperties{
	String productName = "IPHONE 13 PRO";
		
		@Test
		public void submitOrder() throws IOException {
    	    	
    	ProductCatalogPage productCatalogPage = landingPage.loginApplication("angel@gmail.com","Aaaaaa26");
    	productCatalogPage.getItems();
    	productCatalogPage.getProductName(productName);  
    	productCatalogPage.addToCart(productName);
    	CartPage cartPage =productCatalogPage.goToCart();
    	Boolean match = cartPage.matchCheck(productName);
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
		public void orderValidation()
		{
			ProductCatalogPage productCatalogPage = landingPage.loginApplication("a1ngel0@gmail.com","Vongola26");
			OrderPage orderPage =productCatalogPage.goToOrdersPage();
			Boolean checkOrderMatch = orderPage.nameColumnfind(productName);
			Assert.assertTrue(checkOrderMatch);
			
			
		}
	
}
