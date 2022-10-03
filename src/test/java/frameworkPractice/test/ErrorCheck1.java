package frameworkPractice.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import frameworkPractice.testComponents.globalProperties;

public class ErrorCheck1 extends globalProperties{

		
		@Test
		public void submitOrder() throws IOException {
    	 
    	
    	landingPage.loginApplication("a1ngel@gmail.com","Vongola26");
    	
    	Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    	
    		
	}	
	
}
       