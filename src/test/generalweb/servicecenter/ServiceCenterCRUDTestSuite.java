package test.generalweb.servicecenter;

import java.sql.Ref;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.generalweb.calltemplates.CallTemplatesPageObject;
import test.generalweb.helpdesk.HelpDeskFrontOfficePageObject;
import test.generalweb.service.ClientOrganizationsPageObject;

public class ServiceCenterCRUDTestSuite extends ServiceCenterPageObject{

	/**
	 * Test Case ID: C61812
	 * Author : SSU
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testServiceCenterWithoutLicense() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: User has access to the Service Center module with HD licences off : C61812" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testServiceCenterAccessWithoutLicense");

		String user1 = "OnlySCLic";
		String user2= "AqaWebEmplDntDelete";
		String password= "qwerty";

		//Log into Portal using User 1 who have only Service Center License 
		navigateToMainPage(user1,password); 
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		waitAndClick(XPATH_SERVICE_CENTER);

		this.waitForMaskDisappear();

		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(isDispatcherTabPresent(), "Dispatcher Tab is displayed");
		
		waitForExtJSAjaxComplete(5);
		
		clickOnServiceTabs("hdtabpanel__findCallsTab", "Find Calls");
		
		waitForExtJSAjaxComplete(5);
		
		clickOnServiceTabs("hdtabpanel__specificationsTab", "Service Specifications");
		
		waitForExtJSAjaxComplete(5);
		
		clickOnServiceTabs("hdtabpanel__ext-comp", "Calls");
		
		waitForExtJSAjaxComplete(5);
		
		clickOnCallDetailTabs("newCallTab", "Registration");
		
		waitForExtJSAjaxComplete(5);
		
		Reporter.log("Service Center Tabs are all Visible ", true);
		
		//Log into Portal using User 2 who doesnt have Service Center Licence 
		navigateToMainPage(user2,password); 
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_SERVICE_CENTER);
		
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(getWarningDialogTextMsg().contains("You do not have the License to use the 'Service Center' module."), "You do not have the License to use the 'Service Center' module. is displayed");
	
		softAssert.assertAll();
		
		Reporter.log("Service Center is opened only for Users who have License is successfully verified", true);
	}
	
	/**
	 * Test Case ID: C61807
	 * Author : SSU
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testLookUpsInDispatcherForm() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Lookups in Dispatcher Form interactions : C61807" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testLookUpsInDispatcherForm");
		
		String windowClass = "servicecenter";
		//String customer = "2preCompName";
		//String caller = "testemployee testemployee";
		String customer = "1preAqaComp";
		String caller = "1preAqaEmp 1preAqaEmp";
		String location = "slnmEnrgSite1";
		String clientOrganization = "DEFAULT ( DEFAULT )";
		
		//Navigate to Administration and get the Location Linked to Customer
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ADMINISTRATION);	
			
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		collapseNavigation();
		
		ClientOrganizationsPageObject coPageObj = new ClientOrganizationsPageObject();
		
		coPageObj.expandMasterData();
		
		waitForExtJSAjaxComplete(20);
		
		coPageObj.clickClientOrganizations();
		
		waitForExtJSAjaxComplete(20);
		
		coPageObj.selectClientOrganization(clientOrganization);
		
		waitForExtJSAjaxComplete(20);
		
		coPageObj.expandCustomerTreeLookUp(customer);
		
		waitForExtJSAjaxComplete(20);
		
		coPageObj.selectSite(location);
		
		waitForExtJSAjaxComplete(20);
		
		coPageObj.clickButton("Modify site");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		String siteLocation = coPageObj.getSiteDetails("code");
		
		waitForExtJSAjaxComplete(20);
		
		closeUsingToolBar("//div[contains(@id, '"+getXWindowId(EDIT_SITE_WINDOW_HEADER)+"')]");
		
		waitForExtJSAjaxComplete(20);
		
		coPageObj.closeModule("Administration");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigation();
					
		//Navigate to Service center
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_SERVICE_CENTER);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//Set Customer and Caller, Location is prefilled with values
		setCustomer(customer);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getCaller(), caller, "Caller is Automatically Set");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertEquals(getLocation(), siteLocation, "Location is Automatically Set");
		
		waitForExtJSAjaxComplete(5);
		
		clearDispatcherFields("servicecenter", "customer");
		waitForExtJSAjaxComplete(15);
		
		clearDispatcherFields("servicecenter", "caller");
		waitForExtJSAjaxComplete(15);
		
		clearDispatcherFields("servicecenter", "location");
		waitForExtJSAjaxComplete(15);
		
		//Set Caller and Customer, Location is prefilled with values
		setCaller(caller);
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getCustomer(), customer, "Customer is Automatically Set");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertEquals(getLocation(), siteLocation, "Location is Automatically Set");
		
		waitForExtJSAjaxComplete(5);
		
		clearDispatcherFields("servicecenter", "customer");
		
		waitForExtJSAjaxComplete(5);
		
		clearDispatcherFields("servicecenter", "caller");
		
		waitForExtJSAjaxComplete(5);
		
		clearDispatcherFields("servicecenter", "location");
		
		waitForExtJSAjaxComplete(5);
		
		//Enter Location and Verify Location is Found in Search Results
		enterAndVerifyLocationSearchResult("servicecenter", "location", location);
		
		waitForExtJSAjaxComplete(25);
		
		//Set Location and Customer,Caller is prefilled with values
		setLocation("Sites", location);
		
		waitForExtJSAjaxComplete(25);
		
		//TODO Fails
//		softAssert.assertEquals(getCustomer(windowClass), customer, "Customer is Automatically Set");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("Lookups in Dispatcher Form interactions is successfully verified", true);
	}

	/**
	 * Test Case ID: C61817
	 * Author : SSU
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testBookACallInServiceCenterCallDetailsForm() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Booking a Call in Call Details Form : C61817" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testBookACallInServiceCenterCallDetailsForm");
		
		String reference =  "test auto sub" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority =  "1prePrior";
		String smsTempName = "1aqaSMSTemplate";
		String emailTempName = "1aqaEmailTemplate";
		String language = "English";
		String colName="E-mail Address";
		String emailAddress="ssu@mcs.fm";
		
		waitForExtJSAjaxComplete(25);
		
		enableSMSTemplate(smsTempName);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_SERVICE_CENTER);	
			
		waitForExtJSAjaxComplete(25);

		collapseNavigation();

		waitForExtJSAjaxComplete(25);

		collapseDispatcher("Dispatcher");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnCallDetailTabs("newCallTab", "Registration");
		
		waitForExtJSAjaxComplete(25);
		
		//Actions Tab is Disabled
		softAssert.assertTrue(CallDetailsTabStatuses("addActionTab").contains("x-item-disabled"), "Actions Tab is Disabled");
		
		HelpDeskFrontOfficePageObject hdFrntOfcPo = new HelpDeskFrontOfficePageObject();
		
		//Book a Call
		hdFrntOfcPo.clickCallTemplate("Other Subject");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		hdFrntOfcPo.setSubject(reference);
		
		hdFrntOfcPo.setPriority(priority);
		
		setNatureCallDetailsTab("@class","details-general", "NEW_NATURE", "Default Nature");
		
		waitForExtJSAjaxComplete(20);
		
		clickBookCall();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertFalse(CallDetailsTabStatuses("addActionTab").contains("x-item-disabled"), "Actions Tab is Active");
		
		waitForExtJSAjaxComplete(20);
		
		clickOnCallDetailTabs("addActionTab", "Add Action");
		
		waitForExtJSAjaxComplete(25);
		
		clickPossibleActions("Finish Immediately");
		
		waitForExtJSAjaxComplete(25);
		
		checkSendNotification();
		
		waitForExtJSAjaxComplete(5);
		
		clickAddActionForCall();
		
		waitForExtJSAjaxComplete(25);
		
		clickOnCallDetailTabs("callDetailsTab", "Details");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getToolBarStatuses("column-status-finished"), "Finished", "Action is added to the Call");
		
		waitForExtJSAjaxComplete(20);
		
		clickOnTab("Tracking");
		
		waitForExtJSAjaxComplete(20);
		
		clickOnTab("History");
		
		waitForExtJSAjaxComplete(20);
		
		clickHistoryDetails();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(verifyHistoryDetails(), "Finished", "Finished is the Last Status");
		
		waitForExtJSAjaxComplete(25);
		
		//get The Call ID 
		String callID = getCallIDFromHistoryDetailsHeader();
		
		waitForExtJSAjaxComplete(25);
		
		closeUsingToolBarJS(ACTION_HISTORY_FOR_CALL_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertFalse(CallDetailsTabStatuses("sendNotificationTab").contains("x-item-disabled"), "Send Notification Tab is Active");
		
		waitForExtJSAjaxComplete(20);
		
		clickOnCallDetailTabs("addActionTab", "Add Action");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(isFieldReadOnlyInActionsWindow("div", "actionResultStatus"), "Result Status is Disabled");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isFieldReadOnlyInActionsWindow("textarea", "actionNotes"), "Notes is Disabled");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isDateAndTimeFieldDisabled("ux-datetime-date"), "Date Field is disabled");
		
		softAssert.assertTrue(isDateAndTimeFieldDisabled("ux-datetime-time"), "Time Field is disabled");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(verifyCallIdInAddActionTab().contains("Call "+callID+" - "+reference+""), "Call ID is displayed in the Action Tab");
		
		waitForExtJSAjaxComplete(5);
		
		clickOnCallDetailTabs("sendNotificationTab", "Send Notification");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		selectSMSTemplateInNotification(smsTempName);
		
		waitForExtJSAjaxComplete(25);
		
		selectLanguageForSMSTemp("Swedish");
		
		waitForExtJSAjaxComplete(25);
		
		selectEmailTemplateInNotification(emailTempName);
		
		waitForExtJSAjaxComplete(25);
		
		selectLanguageForEmailTemp(language);
		
		waitForExtJSAjaxComplete(25);
		
		setEmailAddressInNotification("1",colName,emailAddress);

		waitForExtJSAjaxComplete(20);

		clickCheckBoxForRecipients("1","Assign To");

		waitForExtJSAjaxComplete(20);

		clickCheckBoxForRecipients("1","To");

		waitForExtJSAjaxComplete(20);

		clickCheckBoxForRecipients("1","Cc");

		waitForExtJSAjaxComplete(20);

		clickSendButton();
		
		softAssert.assertTrue(verifyQtipMess().contains("Assigned successfully"), "Tip message about successful Sent is displayed");

		waitForExtJSAjaxComplete(25);
		
		expandNavigation();

		waitForExtJSAjaxComplete(25);

		//expandDispatcher("Dispatcher");
		
		//waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickOnCallDetailTabs("addActionTab", "Add Action");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(verifyCallIdInAddActionTab().contains("Call "+callID+" - "+reference+""), "Call ID is displayed in the Action Tab");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(verifyAssignedToLabelText().toLowerCase().contains("assigned to aqa servicecenter"));
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertAll();
		
		Reporter.log("Booking a Call in Call Details Form is successfully verified", true);
	}
	

	public void enableSMSTemplate(String smsTempName){
		
		CallTemplatesPageObject calltemplatesPageObject = new CallTemplatesPageObject();
		
		//navigate to home page
		driver.navigate().to(configuration.getApplicationUrl());
		
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
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
	} 

}


