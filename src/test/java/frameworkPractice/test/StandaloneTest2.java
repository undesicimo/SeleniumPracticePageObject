package frameworkPractice.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import frameworkPractice.pageobjects.CartPage;
import frameworkPractice.pageobjects.CheckoutPage;
import frameworkPractice.pageobjects.ConformationPage;
import frameworkPractice.pageobjects.ProductCatalogPage;
import frameworkPractice.testComponents.globalProperties;

public class StandaloneTest2 extends globalProperties{

		
		@Test
		public void submitOrder() throws IOException {
    	String productName = "IPHONE 13 PRO";    	
    	ProductCatalogPage productCatalogPage = landingPage.loginApplication("a1ngel0@gmail.com","v");
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
	
}
