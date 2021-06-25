package test.generalweb.rightvisibility;



//import org.openqa.jetty.http.handler.SetResponseHeadersHandler;
//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.Timer;
import test.framework.webelement.Grid;
import test.generalweb.receptiondesk.AnnouncementPageObject;




public class AnnouncementNegativeCRUDTestSuite extends
		AnnouncementPageObject {
	
	protected final String NAME_FOR_RIGHT = "selenright";
	
	protected final String PASSWORD_FOR_RIGHT = "qwertyu";
	
	

	/**
	 * Navigate to home page and relogin
	 */
	@BeforeMethod
	public void navigateToMainPage() {
		try{
		Timer timer = new Timer().start();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Before test method START", true);
		driver.get(configuration.getApplicationUrl()+"?aqa");
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Navigate to main page\n", true);
		driver.findElement(By.xpath("//table[@id='top-account-menu']//button")).click();
		waitForExtJSAjaxComplete(25);
		driver.findElement(By.xpath("//span[text()='Logout']")).click();
		Reporter.log("Logout <br>", true);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		driver.navigate().to(configuration.getApplicationUrl());
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		login(NAME_FOR_RIGHT, PASSWORD_FOR_RIGHT);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Login to back-end under account "+NAME_FOR_RIGHT +" END\n", true);
		Reporter.log("<br />");
		Reporter.log("Before test method END"+ " (" + timer.stop() + "ms)\n", true);
		Reporter.log("<br />");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
		
	/**
	 * Testcase ID		: C74235
	 * Author			: Intellias
	 * */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testAnnouncementNegativeCreateEditDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create/Edit/Delete Announcement with no right: C74235</span><br>", true);

		Reporter.log("User creates/edits/deletes announcement with no right<br>", true);
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testAnnouncementNegativeCreateEditDelete");
		
		String visitTypeRef = "1preVsTpRef";
	
		String receptionRef = "1preRcRef";
//		String hostLocRef = "slnmEnrgBuilding1";
	
		String visitor = "visitorneg";
		
		String hostNameLast = "auto test right Last";
		
		
//		int random = (int)(Math.random() * 10)+18;
		
		String arrivalDateTest="27-03-2020";
		String arrivalTimeTest = "10:27";
		
		String arrivalDate="10-12-2017";
		String arrivalTime = "15:22";
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reception");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_ANNOUNCEMENT);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		setVisitType(visitTypeRef);
		
		waitForExtJSAjaxComplete(20);
		
		setArrivalReception(receptionRef);
		
		waitForExtJSAjaxComplete(20);
			
		setArrivalDate(arrivalDate);
		
		setArrivalTime(arrivalTime);
		
		waitForExtJSAjaxComplete(25);
		
		clickVisitorTab();
		
		clickAddNewVisitorButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setNewVisitorLastName(visitor);
		
		waitForExtJSAjaxComplete(20);
		
		clickCreate();
				
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPage();
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_RECEPTIONDESK);
		
		waitForExtJSAjaxComplete(20);
		
		setHostNameSearch(hostNameLast);
		
		waitForExtJSAjaxComplete(20);
		
		clickSearchButton();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, arrivalDate+" "+arrivalTime),"User with no rights can not add announcement");
		
		Grid.checkRowInGriByTextValueAndClick(driver, arrivalDateTest+" "+arrivalTimeTest);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setArrivalDate(arrivalDate);
		
		setArrivalTime(arrivalTime);
				
		clickSave();
				
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPage();
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_RECEPTIONDESK);
		
		waitForExtJSAjaxComplete(20);
		
		setHostNameSearch(hostNameLast);
		
		waitForExtJSAjaxComplete(20);
		
		clickSearchButton();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, arrivalDate+" "+arrivalTime),"User with no rights can not edit announcement");
		
		Grid.checkRowInGriByTextValueAndClick(driver, arrivalDateTest+" "+arrivalTimeTest);
		
		waitForExtJSAjaxComplete(20);
		
		clickCancel();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, arrivalDateTest+" "+arrivalTimeTest,"@class","x-grid3"),"User with no rights can not delete announcement");
		
		softAssert.assertAll();
		
		Reporter.log("User with no rights can not create-delete Announcement", true);
	}

}
