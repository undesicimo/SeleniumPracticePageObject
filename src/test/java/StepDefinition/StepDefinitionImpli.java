package StepDefinition;

import java.io.IOException;

import org.testng.Assert;

import frameworkPractice.pageobjects.CartPage;
import frameworkPractice.pageobjects.CheckoutPage;
import frameworkPractice.pageobjects.ConformationPage;
import frameworkPractice.pageobjects.LandingPage;
import frameworkPractice.pageobjects.ProductCatalogPage;
import frameworkPractice.testComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpli extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalogPage productCatalogPage;
    public CartPage cartPage;
    public CheckoutPage checkOutPage;
    public ConformationPage conformationpage;

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException
    {
        landingPage = launchBrowser();
     }
     @Given("^Logged in with email (.+) and password (.+)$")
     public void Logged_in_with_email_password(String email, String password)
     {
        productCatalogPage = landingPage.loginApplication(email,password);
     }
     @When ("^I add product (.+) to Cart$")
     public void I_add_product_toCart(String product) throws InterruptedException
     {
        productCatalogPage.getItems();
    	productCatalogPage.getProductName(product);  
        productCatalogPage.addToCart(product);
        cartPage =productCatalogPage.goToCart();
     }
     @When ("^Checkout product (.+) and sumbit the order$")
     public void checkout_product_submit_order(String product)
     {
        Boolean match = cartPage.matchCheck(product);
    	Assert.assertTrue(match);
    	String countryName= "Japan";
    	checkOutPage =cartPage.checkOut();
    	checkOutPage.selectCountry(countryName);
    	conformationpage =checkOutPage.placeOrder();
     }
     @Then ("{string} message is displayed on the ConformationPage")
     public void message_is_displayed_conformationpage(String string)
     {
        String conformationMessage =conformationpage.getConformationMessage();
    	Assert.assertTrue(conformationMessage.trim().equalsIgnoreCase(string));
     }


}
