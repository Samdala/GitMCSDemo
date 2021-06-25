
package test.generalweb.today;

import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.generalweb.helpdesk.HelpDeskFrontOfficePageObject;
import test.generalweb.helpdesk.HelpDeskNavigationPageObject;

public class TodayTestSuite extends TodayPageObject{

	/**
	 * Testcase ID: C89456
	 * Author : MMA
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testBookCallGadget() throws Exception{

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Book call Gadget: C89456 </span><br>", true);

		String subject =  "test auto sub" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority =  "1prePrior";

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testBookCallGadget");

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_Today);

		waitForExtJSAjaxComplete(20);

		removeGadgetFromTodayWindow("Book Call");

		openTheListOfGadgets();

		waitForExtJSAjaxComplete(20);

		expandNode("div","@class","x-window x-resizable-pinned","Calls");

		softAssert.assertTrue(isGadgetPresentListOfGadgets("Book Call"),"Book Call Gadget is displayed in the list of available gadgets");

		waitForExtJSAjaxComplete(20);

		addGadgetToTodayTab("Book Call");

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isGadgetPresentInTodaysWindow("Book Call"),"Gadget is added");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		expandCallTemplates("Book Call");

		waitForExtJSAjaxComplete(10);

		softAssert.assertFalse(areCallTemplatesCollapsed("Book Call"),"List is Expanded");

		waitForExtJSAjaxComplete(10);

		List<String> gadgetTemplates = getListOfTemplatesInGadget();

		waitForExtJSAjaxComplete(20);

		collapseCallTemplates("Book Call");

		softAssert.assertTrue(areCallTemplatesCollapsed("Book Call"),"List is collapsed");

		waitForExtJSAjaxComplete(10);

		toggleOrMaximizeGadgetWindow("Book Call", "x-tool-maximize");

		HelpDeskFrontOfficePageObject HDFrontOFficePageObject = new HelpDeskFrontOfficePageObject();

		waitForExtJSAjaxComplete(20);

		expandCallTemplates("Call Templates");

		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(HDFrontOFficePageObject.getListOfCallTemplatesInNewCallWindow().containsAll(gadgetTemplates),"List of the Call Templates is displayed");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_Today);

		waitForExtJSAjaxComplete(20);

		filterTemplateByReference("GadgetTemp1");

		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(areCallTemplatesCollapsed("Book Call"),"List is Expanded");

		softAssert.assertTrue(HDFrontOFficePageObject.isCallTemplateAvailable("GadgetTemplateGrp")," Call Template Group is filtered");

		softAssert.assertTrue(HDFrontOFficePageObject.isCallTemplateAvailable("GadgetTemp1")," Call Template is filtered");

		waitForExtJSAjaxComplete(20);

		clickCallTemplate("GadgetTemp1");

		setSubject(subject);

		setPriority(priority);

		setNature("Default Nature");

		clickBookCall();

		waitForExtJSAjaxComplete(20);

		String infobarMsg = getInfobarMessage();
		
		softAssert.assertTrue(infobarMsg.contains(subject),"Appropriate infobar message is displayed");

		waitForExtJSAjaxComplete(20);

		openTheListOfGadgets();

		waitForExtJSAjaxComplete(20);

		expandNode("div","@class","x-window x-resizable-pinned","Calls");

		waitForExtJSAjaxComplete(20);

		addGadgetToTodayTab("My Calls");

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, subject, "@id",getGadgetID("My Calls")),"Call appears in the 'My Calls' Gadget");

		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValueAndClick(driver, "@id",getGadgetID("My Calls"), subject);

		HelpDeskNavigationPageObject HDNavigation = new HelpDeskNavigationPageObject();

		softAssert.assertTrue(infobarMsg.contains(HDNavigation.getCallId()),"Appropriate infobar message is displayed with call ID");

		waitForExtJSAjaxComplete(10);

		softAssert.assertAll();

		Reporter.log("Book Call Gadget succesfully booked", true);


	}


}