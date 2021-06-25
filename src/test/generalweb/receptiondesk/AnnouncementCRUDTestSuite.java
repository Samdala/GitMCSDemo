package test.generalweb.receptiondesk;



//import org.openqa.jetty.http.handler.SetResponseHeadersHandler;
//import org.openqa.selenium.By;
//import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;




public class AnnouncementCRUDTestSuite extends
		AnnouncementPageObject {

	/**
	 * Testcase ID		: C74205,C74204
	 * Author			: Intellias
	 * */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testAnnouncementCreateEditDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create/Edit/Delete Announcement</span>: C74205; C74204<br>", true);

		Reporter.log("User creates/edits/deletes announcement <br>", true);
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testAnnouncementCreateEditDelete");
		
		String visitTypeRef = "1preVsTpRef";
		String hostNameLast = "SELENIUM";
		String receptionRef = "1preRcRef";
		String hostLocRef = "slnmEnrgBuilding1";
		String hostLocRefEd = "slnmEnrgBuilding2";
		String visitor = "visitor";
		String visitorEdited = "visitorEd";
		
		int random = (int)(Math.random() * 10)+18;
		
		String arrivalDate="10-12-2019";
		String arrivalTime = "15:22";
		
		String arrivalDateEdited="11-10-20"+random;
		String arrivalTimeEdited = "13:"+random;
		
		
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
		
		setHostName(hostNameLast);
		
		waitForExtJSAjaxComplete(20);
		
		setHostLocation(hostLocRef);
		
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
		
		Grid.checkRowInGriByTextValueAndClick(driver, arrivalDate+" "+arrivalTime);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(visitTypeRef, getVisitType(),"Visit type check:");
		
		softAssert.assertEquals(hostLocRef, getHostLocation(),"Host Location check:");
		
		softAssert.assertEquals(receptionRef, getArrivalReception(),"Reception Arrival Check check:");
		
		softAssert.assertTrue(getHostName().contains(hostNameLast),"Host Name check:");
		
		softAssert.assertEquals(arrivalDate, getArrivalDate(),"Arrival Date check:");
		
		softAssert.assertEquals(arrivalTime, getArrivalTime(),"Arrival Time check:");
		
		setVisitType(visitTypeRef);
		
		waitForExtJSAjaxComplete(20);
		
		setArrivalReception(receptionRef);
		
		waitForExtJSAjaxComplete(20);
		
		setHostName(hostNameLast);
		
		waitForExtJSAjaxComplete(20);
		
		setHostLocation(hostLocRefEd);
		
		waitForExtJSAjaxComplete(20);
		
		setArrivalDate(arrivalDateEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setArrivalTime(arrivalTimeEdited);
		
		waitForExtJSAjaxComplete(25);
		
		clickVisitorTab();
		
		clickAddNewVisitorButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setNewVisitorLastName(visitorEdited);
		
		waitForExtJSAjaxComplete(20);
		
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
		
		Grid.checkRowInGriByTextValueAndClick(driver, arrivalDateEdited+" "+arrivalTimeEdited);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickCancel();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
	    waitForExtJSAjaxComplete(20);
	    
	    waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, arrivalDateEdited+" "+arrivalTimeEdited),"Announcement was deleted");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("Announcement was succesfully created and deleted", true);
	}

}
