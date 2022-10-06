package frameworkPractice.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import frameworkPractice.testComponents.BaseTest;
import frameworkPractice.testComponents.Retry;

public class ErrorCheck1 extends BaseTest{

		
		@Test(groups= {"ErrorHandling"},retryAnalyzer = Retry.class)
		public void submitOrderError() throws IOException {
    	 
    	
    	landingPage.loginApplication("angel@gmail.com","Aaaaaa26");
    	
    	Assert.assertEquals("Incorrect email  password.", landingPage.getErrorMessage());
    	
    		
	}	
		
		
	
}
       