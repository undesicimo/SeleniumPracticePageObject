package frameworkPractice.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import frameworkPractice.testComponents.globalProperties;

public class ErrorCheck1 extends globalProperties{

		
		@Test
		public void submitOrder() throws IOException {
    	 
    	
    	landingPage.loginApplication("angel@gmail.com","Aaaaaa26");
    	
    	Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    	
    		
	}	
	
}
       