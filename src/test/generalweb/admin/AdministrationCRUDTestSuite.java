package test.generalweb.admin;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;

public class AdministrationCRUDTestSuite extends AdministrationPageObject {
	
	/**
	 * Testcase ID: C89456
	 * Author : ssa
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testMyAccountPageDetails() throws Exception{

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C623001 Verify Place holder image visible by default(when no image is uploaded) along with the lines can be something along these lines (do not use this icon, it is for reference purposes only!)</span><br>", true);

		String user1=configuration.getUserName();
		String password=configuration.getPassword();
		String user="AQA_NoRightsForStockInfo";
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testMyAccountPageDetails");
		
		expandmyMCSAccount();

		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYACCOUNT);
		
		waitForExtJSAjaxComplete(20);
		
		//C623001
		softAssert.assertTrue(isDefaultImgDisplayed(), "Default image is displayed before uploading any image");
		
		//C623003: Need to verified after button enables
		//softAssert.assertTrue(isChangeButtonDisplayed("Change"), "Change button is displayed");
		
		softAssert.assertTrue(isButtonDisplayed("Remove"), "Remove button is displayed");
		
		//C623009
		softAssert.assertFalse(isButtonDisabled("Export"), "Export button is enabled when user have rights");
		try{
		navigateToMainPage(user, password);
		
		waitForExtJSAjaxComplete(20);
		
		expandmyMCSAccount();

		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYACCOUNT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isButtonDisabled("Export"), "Export button is disabled when user does not have rights");
		
		}
		
		finally{
		navigateToMainPage(user1, password);
		
		}
	}

}
