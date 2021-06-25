package test.generalweb.helpdesk;

import javax.security.auth.login.Configuration;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.Timer;
import test.generalweb.calltemplates.CallTemplatesPageObject;

public class HelpDeskAccessTestSuit extends HelpDeskFrontOfficePageObject {
	
	/**
	 * Navigate to home page and relogin
	 */
	//@BeforeMethod

	public void navigateToMainPage(String NAME_FOR_RIGHT,String PASSWORD_FOR_RIGHT) {
		Timer timer = new Timer().start();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Before test method START", true);
		driver.get(configuration.getApplicationUrl());
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
		waitForExtJSAjaxComplete(25);
		driver.manage().deleteAllCookies();
		waitForExtJSAjaxComplete(25);
		driver.navigate().refresh();
		login(NAME_FOR_RIGHT, PASSWORD_FOR_RIGHT);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Login to back-end under account "+NAME_FOR_RIGHT +" END\n", true);
		Reporter.log("<br />");
		Reporter.log("Before test method END"+ " (" + timer.stop() + "ms)\n", true);
		Reporter.log("<br />");
	}
	

	/**
	 * Testcase ID	: 	C74066
	 * Author		:	ssa
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testAddAndDeleteRestrictionToCallTemp() throws Exception{
		
				
		 
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:C29345:Add and delete restriction to Call Template </span><br>", true);

		String callTemplateReference="Other Subject (Default)";
		String user1="ssa";
		String password="qwerty";
		
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testAddAndDeleteRestrictionToCallTemp");
		
		CallTemplatesPageObject calltemplatesPageObject = new CallTemplatesPageObject();
		
		calltemplatesPageObject.clickAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.expandModuleSettings();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.clickCallTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.selectCallTemplate(callTemplateReference);
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.clickSecurityTab();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.setAccount(configuration.getUserName());
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.saveCallTemplate();
		
		navigateToMainPage(user1,password);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWCALL);
		
		waitForExtJSAjaxComplete(20);
		
		boolean status=isCallTemplateAvailable(callTemplateReference);
		
		softAssert.assertFalse(status, "template is not visible");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.clickAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.expandModuleSettings();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.clickCallTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.selectCallTemplate(callTemplateReference);
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.clickSecurityTab();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.selectAccount();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.clickOnRemove();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.saveCallTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPage(user1,password);
		
		waitForExtJSAjaxComplete(20);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWCALL);
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		status=isCallTemplateAvailable(callTemplateReference);
		
		softAssert.assertTrue(status, "template is visible");
		
		softAssert.assertAll();
		
		Reporter.log("Add and delete restriction to Call Template is successfully verified", true);

	}
	
	
	/**
	 * Testcase ID	: 	C74010
	 * Author		:	ssa
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testFieldsAvailabilityFromMyCallsAndMyTeamsCalls() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C15442:Call's fields availability at General tab from My Team's Calls and My Calls </span><br>", true);
		
		
		String subject =  "test auto sub" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority =  "1prePrior";
		String nature="Default Nature";
		String callerComp= "Caller Compa"
				+ "ny";
		String compName="My Company";
		String callerName="Caller Name";
		String callerDeprt="Caller Department";
		String deprtName="AQA_DEPARTMENT";
		String callSubject="Subject";
		String callPriority="Priority";
		String callNature="Nature";
		String callDeprt="Department";
		String callCustomer="Customer";
		String fiscalEntity="Fiscal Entity";
		String fiscalEntityName="My Fiscal Entity";
		String dateAndTime="Date/Time Received";
		String user1="aqa_helpdeskaccess";
		String password="qwerty";
		
		String callName= getUserNameOfLoggedInUser();
		String recievedDate=getCurrentSystemDateFormat();
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testFeildsAvailabilityFromMyCallsAndMyTeamsCalls");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWCALL);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickCallTemplate("Other Subject");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setSubject(subject);
		
		setPriority(priority);
		
		setNature(nature);
		
		waitForExtJSAjaxComplete(20);
		
		clickBookCall();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(!driver.findElements(By.xpath("//button[contains(text(),'Show Call')]")).isEmpty(),"Show Call button is present");
		
		waitForExtJSAjaxComplete(20);
		
		clickMyCallsTab();
		waitForExtJSAjaxComplete(15);
		waitForExtJSAjaxComplete(15);
		
		collapseDetailsPanel();
		waitForExtJSAjaxComplete(3);
		HelpDeskBackOfficePageObject objHD = new HelpDeskBackOfficePageObject();
		
		objHD.openCallDetailsDialog(subject);
		
		clickGeneralTab(DETAILS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		boolean status=isFieldDisplayedAsLabel(callerName,callName);
		
		softAssert.assertTrue(status, "Caller name is displayed and it is not editable");
		
		status=isFieldDisplayedAsLabel(callerComp,compName);
		
		softAssert.assertTrue(status, "Caller company name is displayed and it is not editable");
		
		status=isFieldDisplayedAsLabel(callerDeprt,deprtName);
		
		softAssert.assertTrue(status, "Caller department name is displayed and it is not editable");
		
		status=isFieldDisplayedAsLabel(callSubject,subject);
		
		softAssert.assertTrue(status, "Caller subject is displayed and it is not editable");
		
		status=isFieldDisplayedAsLabel(callPriority,priority);
		
		softAssert.assertTrue(status, "Call prority is displayed and it is not editable");
		
		status=isFieldDisplayedAsLabel(callNature,nature);
		
		softAssert.assertTrue(status, "Call nature is displayed and it is not editable");
		
		status=isFieldDisplayedAsLabel(callDeprt,deprtName);
		
		softAssert.assertTrue(status, "Call department is displayed and it is not editable");
		
		status=isFieldDisplayedAsLabel(callCustomer,compName);
		
		softAssert.assertTrue(status, "Call customer is displayed and it is not editable");
		
		status=isFieldDisplayedAsLabel(fiscalEntity,fiscalEntityName);
		
		softAssert.assertTrue(status, "Finacial fisical entity is displayed and it is not editable");
		
		status=isFieldDisplayedAsLabel(dateAndTime,recievedDate);
				
		softAssert.assertTrue(status, "Call received date and time is displayed and it is not editable");
		
		waitForExtJSAjaxComplete(20);
		
		closeUsingToolBar(DETAILS_WINDOW_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPage(user1,password);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(10);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWCALL);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickMyTeamsCallsTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		collapseDetailsPanel();
		
		waitForExtJSAjaxComplete(20);

		openCallDetailsDialog(subject);
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab(DETAILS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		status=isFieldDisplayedAsLabel(callerName,callName);
		
		softAssert.assertTrue(status, "Caller caller name is displayed and it is not editable");
		
		status=isFieldDisplayedAsLabel(callerComp,compName);
		
		softAssert.assertTrue(status, "Caller company name is displayed and it is not editable");
		
		status=isFieldDisplayedAsLabel(callerDeprt,deprtName);
		
		softAssert.assertTrue(status, "Caller department name is displayed and it is not editable");
		
		status=isFieldDisplayedAsLabel(callSubject,subject);
		
		softAssert.assertTrue(status, "Caller subject is displayed and it is not editable");
		
		status=isFieldDisplayedAsLabel(callPriority,priority);
		
		softAssert.assertTrue(status, "Call prority is displayed and it is not editable");
		
		status=isFieldDisplayedAsLabel(callNature,nature);
		
		softAssert.assertTrue(status, "Call nature is displayed and it is not editable");
		
		status=isFieldDisplayedAsLabel(callDeprt,deprtName);
		
		softAssert.assertTrue(status, "Call department is displayed and it is not editable");
		
		status=isFieldDisplayedAsLabel(callCustomer,compName);
		
		softAssert.assertTrue(status, "Call customer is displayed and it is not editable");
		
		status=isFieldDisplayedAsLabel(fiscalEntity,fiscalEntityName);
		
		softAssert.assertTrue(status, "Finacial fisical entity is displayed and it is not editable");
		
		status=isFieldDisplayedAsLabel(dateAndTime,recievedDate);
		
		softAssert.assertTrue(status, "Call received date and time is displayed and it is not editable");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("Call's fields availability at General tab from My Team's Calls and My Calls are verified successfully", true);
		
	}
	
}
