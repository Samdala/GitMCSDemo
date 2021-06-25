package test.generalweb.helpdesk;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.generalweb.calltemplates.CallTemplatesPageObject;

public class HelpDeskCleanUpTestSuilt extends HelpDeskFrontOfficePageObject{
	
	/**
	 * Testcase ID	: 	C74044
	 * Author		:	ssa
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testIsEnabledSMSTemplateField() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C15476:SMS Notification is not enabled if approptiate option is set </span><br>", true);
		
		
		String subject =  "test auto sub" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority =  "1prePrior";
		String nature="Default Nature";

	
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testIsEnabledSMSTemplateField");
		
		try{
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		CallTemplatesPageObject calltemplatesPageObject = new CallTemplatesPageObject();
		
		calltemplatesPageObject.clickAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.expandNotifications();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.clickSMSTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.UncheckEnableSMSCalls();
		
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
		
		clickMyTeamsCallsTab();
		
		openCallDetailsDialog(subject);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddActionInDetails();
		
		waitForExtJSAjaxComplete(20);
		
		dragAndDropActionWindows(ADD_ACTION_WINDOW_HEADER,DETAILS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions("Open");
		
		waitForExtJSAjaxComplete(20);
		
		checkSendNotification();
		
		waitForExtJSAjaxComplete(20);
		
		clickAddActionForCall();
		
		waitForExtJSAjaxComplete(20);
		
		clickMaximizeButtonForSendNotificationWindow();
		
		waitForExtJSAjaxComplete(20);
		
		Boolean status=isSMSTemplateFieldDisabled();
		
		softAssert.assertTrue(status,"SMS notification field is disabled");
		
		waitForExtJSAjaxComplete(20);
		
		clickManimizeButtonForSendNotificationWindow(SEND_NOTIFICATION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);

		closeUsingToolBar(SEND_NOTIFICATION_WINDOW);

		waitForExtJSAjaxComplete(20);

		closeUsingToolBar(ADD_ACTION_WINDOW_CLASS);

		//clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(20);

		closeUsingToolBar(DETAILS_WINDOW_CLASS);

		waitForExtJSAjaxComplete(20);

		softAssert.assertAll();

		Reporter.log("SMS Notification is not enabled if approptiate option is set is verified successfully", true);
		}
		
		finally{

			CallTemplatesPageObject calltemplatesPageObject = new CallTemplatesPageObject();


			waitForExtJSAjaxComplete(20);

			calltemplatesPageObject.clickAdministration();

			waitForExtJSAjaxComplete(20);

			calltemplatesPageObject.expandNotifications();

			waitForExtJSAjaxComplete(20);

			calltemplatesPageObject.clickSMSTemplate();

			waitForExtJSAjaxComplete(20);

			calltemplatesPageObject.CheckEnableSMSCalls();

			waitForExtJSAjaxComplete(20);
		}
	}
	
	

/**
	 * Testcase ID	: 	C74043
	 * Author		:	ssa
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testInactiveSMSTemplate() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C15475:Inactive SMS Template is not displayed in available templates list </span><br>", true);
		
		
		String subject =  "test auto sub" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority =  "1prePrior";
		String nature="Default Nature";
		String smsTempName="1aqaSMSTemplate";
	
	
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testInactiveSMSTemplate");
		
		
		waitForExtJSAjaxComplete(20);
		
		try{
			
		
		CallTemplatesPageObject calltemplatesPageObject = new CallTemplatesPageObject();
		
		calltemplatesPageObject.clickAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.expandNotifications();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.clickSMSTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.selectSMSTemplate(smsTempName);
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.checkInActivSMSTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.clickSaveChangesForSMSTemplates();
		
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
		
		clickMyTeamsCallsTab();
		
		openCallDetailsDialog(subject);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddActionInDetails();
		
		waitForExtJSAjaxComplete(20);
		
		dragAndDropActionWindows(ADD_ACTION_WINDOW_HEADER,DETAILS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions("Open");
		
		waitForExtJSAjaxComplete(20);
		
		checkSendNotification();
		
		waitForExtJSAjaxComplete(20);
		
		clickAddActionForCall();
		
		waitForExtJSAjaxComplete(20);
		
		clickMaximizeButtonForSendNotificationWindow();
		
		waitForExtJSAjaxComplete(20);
		
		List<String> isValues=getSMSTemplatesInTheList(smsTempName);
		
		softAssert.assertFalse(isValues.contains(smsTempName),"SMS template is not present the SMS template list");
		
		waitForExtJSAjaxComplete(20);
		
		clickManimizeButtonForSendNotificationWindow(SEND_NOTIFICATION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		closeUsingToolBar(SEND_NOTIFICATION_WINDOW);
		
		waitForExtJSAjaxComplete(20);
		
		closeUsingToolBar(ADD_ACTION_WINDOW_CLASS);
		
		//clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		closeUsingToolBar(DETAILS_WINDOW_CLASS);
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertAll();

		Reporter.log("SMS Notification is not enabled if approptiate option is set is verified successfully", true);
		}
		
		finally{

			CallTemplatesPageObject calltemplatesPageObject = new CallTemplatesPageObject();
			
			
			waitForExtJSAjaxComplete(20);
			
			calltemplatesPageObject.clickAdministration();
			
			waitForExtJSAjaxComplete(20);
				
			calltemplatesPageObject.expandNotifications();
					
			waitForExtJSAjaxComplete(20);
			
			calltemplatesPageObject.clickSMSTemplate();
			
			waitForExtJSAjaxComplete(20);
			
			calltemplatesPageObject.CheckEnableSMSCalls();
			
			waitForExtJSAjaxComplete(20);
			
			
			calltemplatesPageObject.selectSMSTemplate(smsTempName);
			
			waitForExtJSAjaxComplete(20);
			
			calltemplatesPageObject.checkActivSMSTemplate();
			
			waitForExtJSAjaxComplete(20);
			
			calltemplatesPageObject.clickSaveChangesForSMSTemplates();
			
		
		}
	}
	
	
	/**
	 * Testcase ID	: 	C73989
	 * Author		:	ssa
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testIsEnabledSMSTemplateFieldInBackOffice() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C15421:SMS Notification is not enabled if approptiate option is set </span><br>", true);
		
		
		String subject =  "test auto sub" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority =  "1prePrior";
		String nature="Default Nature";

	
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testIsEnabledSMSTemplateFieldInBackOffice");
		try{
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		CallTemplatesPageObject calltemplatesPageObject = new CallTemplatesPageObject();
		
		calltemplatesPageObject.clickAdministration();
		
		waitForExtJSAjaxComplete(20);

		calltemplatesPageObject.expandNotifications();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.clickSMSTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.UncheckEnableSMSCalls();
		
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
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_BACKOFFICE);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		HelpDeskBackOfficePageObject backOffice=new HelpDeskBackOfficePageObject();
		
		waitForExtJSAjaxComplete(20);

		backOffice.filterGridForCall("@realid", "mogrid", subject, "Reference");

		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		HelpDeskBackOfficePageObject objHDBO = new HelpDeskBackOfficePageObject();
		objHDBO.openCallDetailsDialog(subject);

		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		clickAddActionInDetails();
		
		waitForExtJSAjaxComplete(20);
		
		dragAndDropActionWindows(ADD_ACTION_WINDOW_HEADER,DETAILS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions("Open");
		
		waitForExtJSAjaxComplete(20);
		
		checkSendNotification();
		
		waitForExtJSAjaxComplete(20);
		
		clickAddActionForCall();
		
		waitForExtJSAjaxComplete(20);
		
		clickMaximizeButtonForSendNotificationWindow();
		
		waitForExtJSAjaxComplete(20);
		
		Boolean status=isSMSTemplateFieldDisabled();
		
		softAssert.assertTrue(status,"SMS notification field is disabled");
		
		waitForExtJSAjaxComplete(20);
		
		clickManimizeButtonForSendNotificationWindow(SEND_NOTIFICATION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		closeUsingToolBar(SEND_NOTIFICATION_WINDOW);
		
		waitForExtJSAjaxComplete(20);
		
		closeUsingToolBar(ADD_ACTION_WINDOW_CLASS);
		
		//clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		closeUsingToolBar(DETAILS_WINDOW_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("SMS Notification is not enabled if approptiate option is set is verified successfully", true);
		}
		
		finally{
			CallTemplatesPageObject calltemplatesPageObject = new CallTemplatesPageObject();
			
			
			waitForExtJSAjaxComplete(20);
			
			calltemplatesPageObject.clickAdministration();
			
			waitForExtJSAjaxComplete(20);
				
			calltemplatesPageObject.expandNotifications();
					
			waitForExtJSAjaxComplete(20);
			
			calltemplatesPageObject.clickSMSTemplate();
			
			waitForExtJSAjaxComplete(20);
			
			calltemplatesPageObject.CheckEnableSMSCalls();
			
			waitForExtJSAjaxComplete(20);
		
		}
	}
	
	
	/**
	 * Testcase ID	: 	C73988
	 * Author		:	ssa
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testInactiveSMSTemplateInBackOffice() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C15420:Inactive SMS Template is not displayed in available templates list </span><br>", true);
		
		
		String subject =  "test auto sub" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority =  "1prePrior";
		String nature="Default Nature";
		String smsTempName="1aqaSMSTemplate";
	
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testInactiveSMSTemplateInBackOffice");
		
		
		waitForExtJSAjaxComplete(20);
		
		try{
		
		CallTemplatesPageObject calltemplatesPageObject = new CallTemplatesPageObject();
		
		calltemplatesPageObject.clickAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.expandNotifications();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.clickSMSTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.selectSMSTemplate(smsTempName);
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.checkInActivSMSTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.clickSaveChangesForSMSTemplates();
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("HelpDesk");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWCALL);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		waitForMaskDisappear();
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
		
		waitAndClick(XPATH_BACKOFFICE);
		
		waitForMaskDisappear();
		
		HelpDeskBackOfficePageObject backOffice=new HelpDeskBackOfficePageObject();
		
		waitForExtJSAjaxComplete(20);
		
		backOffice.filterGridForCall("@realid", "mogrid", subject, "Reference");
		
		waitForMaskDisappear();

		openCallDetailsDialog(subject);
		waitForExtJSAjaxComplete(20);

		clickAddActionInDetails();
		
		waitForExtJSAjaxComplete(20);
		
		dragAndDropActionWindows(ADD_ACTION_WINDOW_HEADER,DETAILS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions("Open");
		
		waitForExtJSAjaxComplete(20);
		
		checkSendNotification();
		
		waitForExtJSAjaxComplete(20);
		
		clickAddActionForCall();
		
		waitForExtJSAjaxComplete(20);
		
		clickMaximizeButtonForSendNotificationWindow();
		
		waitForExtJSAjaxComplete(20);
		
		List<String> isValues=getSMSTemplatesInTheList(smsTempName);
		
		softAssert.assertFalse(isValues.contains(smsTempName),"SMS template is not present the SMS template list");
		
		waitForExtJSAjaxComplete(20);
		
		clickManimizeButtonForSendNotificationWindow(SEND_NOTIFICATION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		closeUsingToolBar(SEND_NOTIFICATION_WINDOW);
		
		waitForExtJSAjaxComplete(20);
		
		closeUsingToolBar(ADD_ACTION_WINDOW_CLASS);
		
		//clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		closeUsingToolBar(DETAILS_WINDOW_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();

		Reporter.log("SMS Notification is not enabled if approptiate option is set is verified successfully", true);
		}
		
		finally{
			CallTemplatesPageObject calltemplatesPageObject = new CallTemplatesPageObject();
			
			
			waitForExtJSAjaxComplete(20);
			
			calltemplatesPageObject.clickAdministration();
			
			waitForExtJSAjaxComplete(20);
				
			calltemplatesPageObject.expandNotifications();
					
			waitForExtJSAjaxComplete(20);
			
			calltemplatesPageObject.clickSMSTemplate();
			
			waitForExtJSAjaxComplete(20);
			
			calltemplatesPageObject.CheckEnableSMSCalls();
			
			waitForExtJSAjaxComplete(20);
			
			calltemplatesPageObject.selectSMSTemplate(smsTempName);
			
			waitForExtJSAjaxComplete(20);
			
			calltemplatesPageObject.checkActivSMSTemplate();
			
			waitForExtJSAjaxComplete(20);
			
			calltemplatesPageObject.clickSaveChangesForSMSTemplates();
			
		}
	}

	
/*	@AfterMethod
	public void cleanupFunction(){
		
		String smsTempName="1aqaSMSTemplate";
		
		Boolean build122 = driver.getCurrentUrl().contains("122");
		Boolean build14 = driver.getCurrentUrl().contains("14");
	
		
		CallTemplatesPageObject calltemplatesPageObject = new CallTemplatesPageObject();
		
		//navigate to home page
		driver.navigate().to(configuration.getApplicationUrl());
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.clickAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		if(build14 || build122){
			
		calltemplatesPageObject.expandModuleSettings();
		}else
		{
		calltemplatesPageObject.expandNotifications();
		}
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.clickSMSTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.CheckEnableSMSCalls();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.selectSMSTemplate(smsTempName);
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.checkActivSMSTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.clickSaveChangesForSMSTemplates();
		
	}       */

}
