// =============================================================================
//  
// Description   :	Class contain different test cases for check Login functionality 	
//					
//

// =============================================================================

package test.generalweb.login;


import java.util.HashMap;






import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;





import test.framework.RetryAnalyzer;
import test.framework.webelement.McsElement;
import test.generalweb.login.data.DataProviderLoginTestSuite;

public class LoginTestSuite extends LoginPageObject {
	
	
	/**
	 * Navigate to home page and relogin
	 */
	@BeforeMethod
	public void navigateToMainPage() {
		waitForExtJSAjaxComplete(25);
		Reporter.log("Before test method START", true);
		driver.get(configuration.getApplicationUrl()+"?aqa");
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Navigate to main page\n", true);
		
		try {driver.findElement(By.xpath("//table[@id='top-account-menu']//button")).click();
		waitForExtJSAjaxComplete(25);
		driver.findElement(By.xpath("//span[text()='Logout']")).click();
		Reporter.log("Logout <br>", true);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);}
		catch(Exception e){
			Reporter.log("user was logout <br>", true);
			
		}
		
		driver.navigate().to(configuration.getApplicationUrl());
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		
	}
	


	@Test(enabled = false, dataProvider = "correctCredentialsDataProvider", dataProviderClass = DataProviderLoginTestSuite.class)
	public void checkLogginWithCorrectCredentials(
			HashMap<String, String> credentialsData) throws Exception {
		Reporter.log(
				"	<span style='font-weight:bold;color:#000033'> "
						+ "Test: User login with correct credentials:</span><br>	 username="
						+ credentialsData.get("name") + " and password="
						+ credentialsData.get("password"), true);

		login(credentialsData.get("name"), credentialsData.get("password"));

//		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
//				.contains(credentialsData.get("expectedValidationMessage")));

		logout();

	}

	@Test(enabled = true, dataProvider = "incorrectCredentialsDataProvider", dataProviderClass = DataProviderLoginTestSuite.class)
	public void checkLogginWithIncorrectCredentials(
			HashMap<String, String> credentialsData) throws Exception {
		Reporter.log(
				"<span style='font-weight:bold;color:#000033'> "
						+ "Test: User login with incorrect credentials  c25489 :</span><br>  username="
						+ credentialsData.get("name") + " and password="
						+ credentialsData.get("password"), true);

		login(credentialsData.get("name"), credentialsData.get("password"));
		
		

		Assert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(text(),'"+credentialsData.get("expectedValidationMessage")+"') and not(contains(@class,'hide'))]"));

	}

	@Test(enabled = true,retryAnalyzer=RetryAnalyzer.class)
	public void changeLogin() throws Exception {
		Reporter.log(
				"<span style='font-weight:bold;color:#000033'> "
						+ "Test: User change login  c25492 :</span><br>", true);
		
		String username = "louser";
		
		String oldPassword = "qwerty";
		
		String newPassword = "teqwerty";
		
		String newPassword2 = "tteqwerty";
		
		String blankPassword = "";

		login(username, oldPassword);
		
		waitForExtJSAjaxComplete(20);

		openChangePasswordForm();
		
		waitForExtJSAjaxComplete(20);
		
		changePassword(oldPassword, newPassword);
		
		logout();
		
		login(username, newPassword);
		
		waitForExtJSAjaxComplete(20);

		openChangePasswordForm();
		
		changePassword(newPassword, newPassword2);
		
		logout();
		
		login(username, newPassword2);
		
		openChangePasswordForm();

		changePassword(newPassword2, blankPassword);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		logout();
		
		login(username, blankPassword);

		logout();

	}
	
	/*@AfterMethod
	public void changePasswordToDefault(){
	
		String username = "louser";
		
		String oldPassword = "qwerty";
		
		String blankPassword = "";
		
		login(username, blankPassword);
		
		openChangePasswordForm();

		changePassword(blankPassword, oldPassword);
		
	}
	*/
	
}
