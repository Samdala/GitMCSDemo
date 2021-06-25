package test.generalweb.activity;

//import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.By;
//import org.testng.Assert;
import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;


public class ActivityCRUDTestSuite extends ActivityPageObject {

	/**
	 * Testcase ID		: C74980
	 * Author			: Intellias
	 * */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testActivityCreateDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create/delete activity: C74980</span><br>", true);

		Reporter.log("User creates activity <br>", true);
		
		
		String date = "14-08-2013";
		String startTime = "11:00";
		String stopTime = "12:00";
		String activityStatus = "Tentative";
		String activityType = "Default";
		String workForce = "SELENIUM";
		String defaultWorkForce = "SELENIUM AQA";
		String relatedType = "Call";
		String callReference = "1preCallSub";
		String description = "descr"+getCurrentDate().substring(getCurrentDate().length()-5);
		String product = "3preProdRef";
		
		
		SoftAssert softAssert = new SoftAssert();
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TIMEREGISTRATION);
		
		waitForExtJSAjaxComplete(20);
		
//		C28886		Toolbar of the Time Registration module 
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//table[contains(@class,'pressed')]//button[contains(text(),'Activities')]"),"Activity button is pressed");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//table[not (contains(@class,'pressed'))]//button[contains(text(),'Options')]"),"Options button is not pressed");

		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);

		clickAddButton(); 
				
		waitForExtJSAjaxComplete(20);

		setDate(date);

		setStartTime(startTime);

		setStopTime(stopTime);
		
//	C29835 		Default activity type
		
 // Initially activity status field is in disabled state. It is expected functionality.        
		//setActivityStatus(activityStatus);
		
		softAssert.assertEquals(getActivityType(),activityType,"default activity type is ok");

		setActivityType(activityType);
		
// C29330New activity is created for logged in user 		
		//
		softAssert.assertEquals(getWorkForce(),getUserNameOfLoggedInUserFirstNameLastNameFormat(),"default work force is ok");

		setWorkForce(workForce);

		setRelatedType(relatedType);

		setCall(callReference);
		// IE7 does not allow description?
		setDescription(description);

		saveAndClose();
		
		waitForElementDisappear("//div[contains(@class,'x-window-mc')]");
		
		waitForExtJSAjaxComplete(25);
		
		
//C29393		Activity Class is displayed correctly in the grid 
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Absence|1preAcTpRef|preSubmittedDescr", "@class", "x-grid3"),"activity type class");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Attendance|Default|"+description, "@class", "x-grid3"),"activity type class2");
		
		
//  C28941		Edit button on the activities overview toolbar 		
		
		clickEditButton();
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(25);
		
		McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-grid3-hd-checker')])[last()]").click();
		
		waitForExtJSAjaxComplete(25);
		
		clickEditButton();
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, description);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		

//c29444
		clickFinancialTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickOverrideProduct();
		
		waitForExtJSAjaxComplete(25);
		
		setProductService(product);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(getUnit().equals("h"), "measurement unit is ok");
		
		setProductService("");
		
		clickFinancialTab();
		
		waitForExtJSAjaxComplete(25);
				
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(getUnit().equals(""), "measurement unit is empty");
		
		
		cancel();
		
		waitForExtJSAjaxComplete(25);
		
		Reporter.log("User deletes activity <br>", true);
		
		//McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-grid3-hd-checker')])[last()]").click();
//		
		gridChecker();
		
		waitForExtJSAjaxComplete(25);
		
	//	McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-grid3-hd-checker')])[last()]").click();
		
//				
//		clickDeleteButton();
//		
//		waitForExtJSAjaxComplete(25);
//		
//		if (driver.getCurrentUrl().contains("122")||driver.getCurrentUrl().contains("14"))
//		
//		{clickOnDialogButton("OK");}
//		else{clickOnDialogButton("Yes");}
//		
//		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "preSubmittedDescr");
		
		waitForExtJSAjaxComplete(25);
		
		cancel();
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValue(driver, "preDescription");

		waitForExtJSAjaxComplete(25);
		
		clickDeleteButton();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(25);
		
		//McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-grid3-hd-checker')])[last()]").click();
		
		gridChecker();
		
		waitForExtJSAjaxComplete(25);
		
		gridChecker();
		
		//McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-grid3-hd-checker')])[last()]").click();
		
		waitForExtJSAjaxComplete(25);
		
		McsElement.getElementByXpath(driver, "//div[text()='"+description+"']/../..//div[contains(@class,'row-checker')]").click();
		
		waitForExtJSAjaxComplete(25);
		
		cancel();
		
		waitForExtJSAjaxComplete(25);
		
		McsElement.getElementByXpath(driver, "//div[text()='preDescrip2']/../..//div[contains(@class,'row-checker')]").click();
		
		waitForExtJSAjaxComplete(25);
		
		clickDeleteButton();
		
		clickOnDialogButton("Yes");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, description), "Activity is  deleted!");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, "preDescrip2"), "Activity is deleted!");
		
		softAssert.assertAll();
		
		Reporter.log("Activity was succesfully created and deleted", true);
	}

	/*@AfterMethod
	public void createDeletedActivities() throws IOException{
		
		String date = "14-08-2013";
		String startTime = "11";
		String stopTime = "12";
		String activityStatus = "Tentative";
		String activityType = "Default";
		String relatedType = "Call";
		String callReference = "1preCallSub";
		String description = "preDescrip2";
		
		
		if(Grid.isRowInGridAbsent(driver, "preDescrip2")){
	
			waitAndClick(XPATH_TIMEREGISTRATION);
		
		waitForExtJSAjaxComplete(20);

		clickAddButton(); 
				
		waitForExtJSAjaxComplete(20);

		setDate(date);

		setStartTime(startTime);

		setStopTime(stopTime);
		

		setActivityStatus(activityStatus);
		

		setActivityType(activityType);
		

		setRelatedType(relatedType);

		setCall(callReference);
		// IE7 does not allow description?
		setDescription(description);

		saveAndClose();
		}
	}*/
}
