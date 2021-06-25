package test.generalweb.helpdesk;


import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;



public class HelpDeskNavigationTestSuite extends
	HelpDeskNavigationPageObject {

	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testHelpDeskNavigation() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: testHelpDeskNavigation : C74039, C74040</span><br>", true);

		Reporter.log("User Navigation to HelpDesk URL's <br>", true);
		
		String callTemplateId = "|0000000003";
		
		String callTemplateIdEmpty = "";
		
		String callTemplateIdInvalid = "|0000090003";
		
		String callTemplateIdRestricted = "|0000000023";
		
		String callTemplateUrl = "index.php?mode=DESKTOP&relay=MCS_PORTAL_HD-NEW_FROM_TEMPLATE_ID";
		
		String callTemplateRef = "1preCallTemplateLabel";
		
		String callTemplateRestricted = "1preCallTemplateRestricted";
		
		String infoText = "Please choose a call template from the list.";
		
		String buttonText = "New Call";
		
		String warningText = "Unable to load template.";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testHelpDeskNavigation");
		
		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWCALL);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		navigateToPage(callTemplateUrl+callTemplateId);
		
		Thread.sleep(20000);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getCallTemplateNameFromPageHeader(), "Create Call From Template-" + callTemplateRef,"Navigate to cal template");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyButtonPressedPage(buttonText), "Button " + buttonText + " is pressed.");
		
		//softAssert.assertTrue(isColapsedCallTemplatePanel(), "Panel Call template is collapsed.");
		
		waitForExtJSAjaxComplete(20);
		
		
		
		navigateToPage(callTemplateUrl+callTemplateIdEmpty);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isInfoTextPresent(infoText),"Please choose call template message is present");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyButtonPressedPage(buttonText), "Button " + buttonText + " is pressed.");
		
		//softAssert.assertTrue(!isColapsedCallTemplatePanel(), "Panel Call template is expanded.");
		
		waitForExtJSAjaxComplete(20);
		
		
		
		navigateToPage(callTemplateUrl+callTemplateIdInvalid);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isWarningTextDisplayed(warningText), "Warning message is displayed.");
		
		waitForExtJSAjaxComplete(20);
		
		
		
		navigateToPage("index.php?mode=DESKTOP&relay=MCS_PORTAL_HD-NEW_FROM_TEMPLATE_ID");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(!isCallTeamplatePresent(callTemplateRestricted), "Restricted call template is hidden. ");
		
		navigateToPage(callTemplateUrl+callTemplateIdRestricted);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("OK");
		
		softAssert.assertAll();
		
		Reporter.log("User navigation through URL's succesfully.", true);
	}
	
	
	
	/**
	 * TestCase ID : C61283
	 *  Author : MMA
	 **/
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testBackOfficeAccessingACallviaLink() throws IOException {
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C61283 : Accessing a Call via Link </span><br>", true);

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testBackOfficeAccessingACallviaLink");

		HelpDeskFrontOfficePageObject obj = new HelpDeskFrontOfficePageObject();

		String callUrl = "frame.php?relay=MCS_PORTAL_HD-ACTIVATE[VIEW_CALLS|";
		String callUrlLowerCase = "frame.php?relay=MCS_PORTAL_HD-ACTIVATE[view_calls|";

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_MYCALLS);

		waitForExtJSAjaxComplete(20);

		obj.collapseDetailsPanel();

		waitForExtJSAjaxComplete(20);

		selectFirstRowInGrid();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		String callID = getCallId();
		
		softAssert.assertTrue(isCallDetailsWindowOpen(callID), "Call Window is opened");

		String ID = getCallId();

		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);

		navigateToPage(callUrl + ID + "]");
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(ID, getCallId(),"Verified");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isCallDetailsWindowOpen(callID),"Call Window is opened with a link");

		waitForExtJSAjaxComplete(20);
		
		navigateToPage(callUrlLowerCase + ID + "]");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(isCallDetailsWindowOpen(callID), "Call Window is not opened with lower case Link");

		softAssert.assertAll();

		Reporter.log("Call is opened with direct link", true);

	}
}
