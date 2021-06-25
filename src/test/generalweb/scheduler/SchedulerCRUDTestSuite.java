/**
 * Class File to hold tests related to Scheduler module
 * @author vpcc
 */
package test.generalweb.scheduler;



import org.apache.bcel.generic.FMUL;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.Tree;
import test.generalweb.rightvisibility.AdministrationPageObject;
import test.generalweb.workorders.WorkOrderDetailTabsPageObject;
import test.generalweb.workorders.WorkOrderPageObject;

public class SchedulerCRUDTestSuite extends SchedulerPageObject {
	
	/**
	 * Test Case ID: C82434 & C82553 & C82552
	 * Author : SSU
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testEditWorkOrderInSchedulerGrid() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: User is able to Edit Work Order : C82434" + " </span><br>", 
				true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Edit Work Order right : C82552" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testEditWorkOrderInSchedulerGrid");

		String woRef = "SchedulerRelated";
		String woRefEdited = "SchedulerRelated "+getCurrentDate().substring(getCurrentDate().length()-5);
		String password= "qwerty";
		String user2 = "aqa_gauges";
		String serviceOrgREf = "AutoTestServOrgRef";

		waitForExtJSAjaxComplete(25);

		collapseNavigation();
		waitForExtJSAjaxComplete(5);

		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath);
		//clickXPath(XPATH_SCHEDULER);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

	//	setServiceOrganization(serviceOrgREf, "Reference");
		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		
		//waitForExtJSAjaxComplete(25);
		
		setCustomDateRange("01-07-2016 to 31-08-2016");
		//waitForExtJSAjaxComplete(20);
		Thread.sleep(15000);
		
		WorkOrderPageObject woObj = new WorkOrderPageObject();
		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", "SchedulerRelated", "Reference");
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, woRef, "@class", "x-panel scheduler"), "WorkOrder is available in the grid");
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", woRef);
		
		waitForExtJSAjaxComplete(5);
		
		/*Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", woRef);
		
		waitForExtJSAjaxComplete(5);*/
		
		selectActions("Edit");
		
		waitForExtJSAjaxComplete(25);
		
		WorkOrderPageObject woPobj = new WorkOrderPageObject();
		
		softAssert.assertTrue(woPobj.getWONameFromDetailsWindow(woRef), "WO Edit Details window is opened");
		
		waitForExtJSAjaxComplete(25);
		
		woPobj.clickGeneralTab();
		
		waitForExtJSAjaxComplete(25);
		
		//Edit the WO Reference
		Reporter.log("WO Edit Reference "+woRefEdited, true);
		
		String windowID = woPobj.getWindowIdByClass("x-window x-resizable-pinned");
		
		waitForExtJSAjaxComplete(25);
		
		woPobj.setReferenceGeneralTab(windowID, woRefEdited);
		
		waitForExtJSAjaxComplete(25);
		
		woPobj.saveWorkOrder(windowID, "Save Work Order");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(woPobj.getReference(), woRefEdited, "Changes are saved Successfully");
		
		waitForExtJSAjaxComplete(25);
		
		woPobj.closeUsingToolBar(WORKORDER_WINDOW_XPATH);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, woRefEdited, "@class", "x-panel scheduler"), "WorkOrder Reference Edited is available in the grid");
		
		waitForExtJSAjaxComplete(25);
		
		Reporter.log("User is able to Edit Work Order in Scheduler Grid is successfully Verified", true);
		
		/************** C82533**********/
			
		Reporter.log("C82553 Test Begins", true);

		//softAssert.setMethodName("C82533AddActionRights");

		waitForExtJSAjaxComplete(25);

		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", woRef);

		waitForExtJSAjaxComplete(5);

		//selectActions("Add Action");
		clickAddActionInSchedulerWin();

		waitForExtJSAjaxComplete(25);

		woPobj.clickPossibleActions("Action1");

		waitForExtJSAjaxComplete(3);

		woPobj.setActionNote("Note Added");

		woPobj.closeAction(ADD_ACTION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(3);

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(3);

		//Log into Portal using User "aqa_Gauges" who doesn't have rights to Add Action to WO C82553
		navigateToMainPage(user2,password); 

		waitForExtJSAjaxComplete(25);

		collapseNavigation();
		waitForExtJSAjaxComplete(5);

		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath);
		//clickXPath(XPATH_SCHEDULER);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		
		/*setServiceOrganization(serviceOrgREf, "Reference");
		
		waitForExtJSAjaxComplete(25);*/
		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		
		setCustomDateRange("01-07-2016 to 31-08-2016");
		Thread.sleep(15000);
		waitForExtJSAjaxComplete(20);
		
		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", woRef, "Reference");
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", woRef);
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertFalse(verifyButtonIsNotDisplayedInActions("Add Action"), "Add Action is not displayed for the user "+user2+" who doesnt have license");	
		
		waitForExtJSAjaxComplete(2);
		
		Reporter.log("Apply Action to Work Order right is successfully Verified", true);
		
		/*******      C82552 *****************/
		
		Reporter.log("C82552 Test Begins", true);
		
		//softAssert.setMethodName("C82552EditWORights");
		
		waitForExtJSAjaxComplete(25);
		
		selectRefreshButton();
		
		waitForExtJSAjaxComplete(20);
		
		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", woRef, "Reference");
		waitForExtJSAjaxComplete(15);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", woRef);
		
		waitForExtJSAjaxComplete(5);
		
		selectActions("Edit");
		
		waitForExtJSAjaxComplete(25);
		
		woPobj.clickGeneralTab();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(woPobj.getDueDate(WORKORDER_WINDOW_DETAIL, "class").contains("x-form-readonly"), "Due Date is Read only and not editable");
		
		softAssert.assertTrue(woPobj.getDueTime(WORKORDER_WINDOW_DETAIL, "class").contains("x-form-readonly"), "Due Time is Read only and not editable");
		
		woPobj.clickDescriptionTab();
		
		waitForExtJSAjaxComplete(5);
		
		woPobj.clickDescription2Tab(WORKORDER_WINDOW_DETAIL);
		
		softAssert.assertTrue(woPobj.getDescriptionOrNotes(WORKORDER_WINDOW_DETAIL, "class").contains("x-form-readonly"),"description is Read only and not editable");
		
		waitForExtJSAjaxComplete(5);
		
		woPobj.clickNotesTab(WORKORDER_WINDOW_DETAIL);
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(woPobj.getDescriptionOrNotes(WORKORDER_WINDOW_DETAIL, "class").contains("x-form-readonly"),"Notes is Read only and not editable");

		softAssert.assertAll();
		
		Reporter.log("User Who doesnt have proper rights cannot Edit Work Order in Scheduler Grid is successfully Verified", true);
	}

	/**
	 * Test Case ID: C82756
	 * Author : SSU
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testSLAMetricsForWOLinkedToCall() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: The planner can view the SLA Metrics for Workorders, that are linked to a Call : C82756" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testSLAMetricsForWOLinkedToCall");
		
		String woRef = "SchedulerSLA";
		String serviceGrpRef = "AutoTestServGrp1Ref";		
		String taskforceRef = "AutoTestTaskFrc1Ref";
		String woGoodSLAID = "39";
		String dateRangeFromTo = "01-07-2016 to 31-08-2016";
		String serviceOrgREf = "AutoTestServOrgRef";
		AdministrationPageObject objad = new AdministrationPageObject();
		
		clickAdministration();
		waitForExtJSAjaxComplete(5);

		expandMasterData();
		waitForExtJSAjaxComplete(5);

		clickAdministrationOptions("Master Data","Service Organizations");
		waitForExtJSAjaxComplete(15);

		expandParentNodeInTreeByTextValue(serviceOrgREf);
		waitForExtJSAjaxComplete(5);

		expandParentNodeInTreeByTextValue("Sites");
		waitForExtJSAjaxComplete(5);

		String siteAdministration = getSiteFromServOrganization(serviceOrgREf);
		siteAdministration = siteAdministration.substring(0, siteAdministration.indexOf(" "));
				
		waitForExtJSAjaxComplete(5);

		expandParentNodeInTreeByTextValue(serviceGrpRef);
		waitForExtJSAjaxComplete(10);

		expandChildNodeInTreeByTextValue("Task Forces");
		waitForExtJSAjaxComplete(5);

		expandChildNodeInTreeByTextValue(taskforceRef);
		waitForExtJSAjaxComplete(10);

		Tree.checkNodeInTreeByTextValue(driver,taskforceRef);
		waitForExtJSAjaxComplete(5);

		List<String> workResources = getMembersOfTaskForce();
		waitForExtJSAjaxComplete(5);

		closeModule("Administration");
		waitForExtJSAjaxComplete(5);

		WorkOrderPageObject woObj = new WorkOrderPageObject();

		waitAndClick(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(15);
		waitForExtJSAjaxComplete(15);

		//All the WO which are displayed in the work order overview are displayed in scheduler board irrespective with WO status and linked site, so no need to apply filter
/*		woObj.clickFilterResults();
		waitForExtJSAjaxComplete(10);

		woObj.expandMaintenanceAndSelectComplianceValueInFilterResults("x-window x-resizable-pinned", "Add restriction", "General Properties","Status Class");
		waitForExtJSAjaxComplete(10);

		setValueGridLookupWithFilters("Finished","Name");
		waitForExtJSAjaxComplete(5);

		woObj. selectComplianceRestriction("x-window x-resizable-pinned","Status Class =");
		waitForExtJSAjaxComplete(5);

		woObj.clickAnyButtonInFilterResults("x-window x-resizable-pinned","NOT");
		waitForExtJSAjaxComplete(5);

		clickSaveFilterInFilterWindow();
		waitForExtJSAjaxComplete(10);

		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport",siteAdministration, "Site");
		waitForExtJSAjaxComplete(25);*/

		int woCount = getGridSize();
		closeModule("Work Order");
		waitForExtJSAjaxComplete(5);

		//TODO : Scheduler loading Issue
		collapseNavigation();
		waitForExtJSAjaxComplete(10);
		
		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath);
		//clickXPath(XPATH_SCHEDULER);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		
		/*setServiceOrganization(serviceOrgREf, "Reference");
		waitForExtJSAjaxComplete(10);*/
		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		
		setCustomDateRange("01-07-2016 to 31-08-2016");
		Thread.sleep(15000);
		//waitForExtJSAjaxComplete(25);
		
		/*woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport",siteAdministration, "Site");
		waitForExtJSAjaxComplete(25);*/
		
		softAssert.assertEquals(getSchedulerGridSize(),woCount, "Two WO that meets the Searching criteria are displayed");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, woRef, "@class", "x-panel scheduler"), "WorkOrder is available in the grid");
		
		waitForExtJSAjaxComplete(25);
		
		//Verify Warning Message
		
		selectActions("View SLA Information");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(getWarningDialogTextMsg().contains("You must select at least one Work Order to perform this action on"), "You must select at least one Work Order to perform this action on is displayed");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(25);
		
		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", woRef, "Reference");
		waitForExtJSAjaxComplete(5);
		
		//Select the WO and click SLA Information
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", woRef);
		
		selectActions("View SLA Information");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(verifyMetricsHeaderInSLAInformation("WOSLAGrid", "Workorder Metrics"), "Workorder Metrics is Displayed in SLA Information Window");
		
		softAssert.assertTrue(verifyMetricsHeaderInSLAInformation("HDSLAGrid", "Call Metrics"), "Call Metrics is Displayed in SLA Information Window");
		
		//WO Metric Grid Verification
		String colNameArr[] = {"SLA Object Code", "SLA Object Reference", "Metric Name", "Chrono", "Metric Value", "Lower Target", "Upper Target", "Lower Target Variance", "Upper Target Variance"};
		
		softAssert.assertTrue(verifyColNamesInSLAInfoWindow("WOSLAGrid", colNameArr), "Detailed SLA information is displayed in the WO Metrics grid");
		
		waitForExtJSAjaxComplete(25);
		
		String woRow1ValueArr[] = {"SLAWO", "SLAWoRef", "Metric1", "Stopped", "0.00", "24.00", "48.00", "-24.00", "-48.00"};
		
		softAssert.assertTrue(verifyAllSLAFieldsAreReadOnly("WOSLAGrid", "mcsgridrow_FF0000", colNameArr, woRow1ValueArr), "All the fields in WO Metrics Grid is Read only");
		
		waitForExtJSAjaxComplete(25);
		
		String woRow2ValueArr[] = {"SLAWO", "SLAWoRef", "Metric2", "Stopped", "0.00", "24.00", "48.00", "-24.00", "-48.00"};
		
		softAssert.assertTrue(verifyAllSLAFieldsAreReadOnly("WOSLAGrid", "mcsgridrow_00CC00", colNameArr, woRow2ValueArr), "All the fields in WO Metrics Grid is Read only");
		
		waitForExtJSAjaxComplete(25);
		
		//Call Metric Grid Verification
		softAssert.assertTrue(verifyColNamesInSLAInfoWindow("HDSLAGrid", colNameArr), "Detailed SLA information is displayed in the Call Metrics grid");
		
		waitForExtJSAjaxComplete(25);
		
		String callRow1ValueArr[] = {"SLACall", "SLACallRef", "Metric1", "Stopped", "0.08", "24.00", "48.00", "-23.92", "-47.92"};
		
		softAssert.assertTrue(verifyAllSLAFieldsAreReadOnly("HDSLAGrid", "mcsgridrow_FF0000", colNameArr, callRow1ValueArr), "All the fields in Call Metrics Grid is Read only");
		
		waitForExtJSAjaxComplete(25);
		
		String callRow2ValueArr[] = {"SLACall", "SLACallRef", "Metric2", "Stopped", "0.08", "24.00", "48.00", "-23.92", "-47.92"};
		
		softAssert.assertTrue(verifyAllSLAFieldsAreReadOnly("HDSLAGrid", "mcsgridrow_00CC00", colNameArr, callRow2ValueArr), "All the fields in Call Metrics Grid is Read only");
		
		waitForExtJSAjaxComplete(25);
		
		closeUsingToolBarJS(SLA_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
				
		driver.navigate().refresh();
		waitForExtJSAjaxComplete(25);

		collapseNavigation();
		waitForExtJSAjaxComplete(10);
		
		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath);
		waitForExtJSAjaxComplete(15);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(5);
				
		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		
		setCustomDateRange("01-07-2016 to 31-08-2016");
		Thread.sleep(15000);
		
		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", woGoodSLAID, "Work Order ID");
		waitForExtJSAjaxComplete(5);
		
		//Double click on an SLA Indicator icon near the Call
		doubleClickOnSLAIndicatorIcon(woGoodSLAID, SLA_GOOD_INDICATOR_ICON_XPATH);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(verifyMetricsHeaderInSLAInformation("WOSLAGrid", "Workorder Metrics"), "Workorder Metrics is Displayed in SLA Information Window");
		
		softAssert.assertTrue(verifyMetricsHeaderInSLAInformation("HDSLAGrid", "Call Metrics"), "Call Metrics is Displayed in SLA Information Window");
		
		//WO Metric Grid Verification
		softAssert.assertTrue(verifyColNamesInSLAInfoWindow("WOSLAGrid", colNameArr), "Detailed SLA information is displayed in the WO Metrics grid");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(verifyAllSLAFieldsAreReadOnly("WOSLAGrid", "mcsgridrow_FF0000", colNameArr, woRow1ValueArr), "All the fields in WO Metrics Grid is Read only");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(verifyAllSLAFieldsAreReadOnly("WOSLAGrid", "mcsgridrow_00CC00", colNameArr, woRow2ValueArr), "All the fields in WO Metrics Grid is Read only");
		
		waitForExtJSAjaxComplete(25);
		
		//Call Metric Grid Verification
		softAssert.assertTrue(verifyColNamesInSLAInfoWindow("HDSLAGrid", colNameArr), "Detailed SLA information is displayed in the Call Metrics grid");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(verifyAllSLAFieldsAreReadOnly("HDSLAGrid", "mcsgridrow_FF0000", colNameArr, callRow1ValueArr), "All the fields in Call Metrics Grid is Read only");
		
		waitForExtJSAjaxComplete(25);
		
 		softAssert.assertTrue(verifyAllSLAFieldsAreReadOnly("HDSLAGrid", "mcsgridrow_00CC00", colNameArr, callRow2ValueArr), "All the fields in Call Metrics Grid is Read only");
		
		waitForExtJSAjaxComplete(25);
		
		closeUsingToolBarJS(SLA_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertAll();
		
		Reporter.log("The planner can view the SLA Metrics for Workorders, that are linked to a Call is successfully verified", true);
	}
	
	/**
	 * Test Case ID: C82450
	 * Author : SSU
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testCustomDateRange() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Custom date range : C82450" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCustomDateRange");
		
		waitForExtJSAjaxComplete(25);
		
		String month = "Jul";
		String year = "2016";
		String fromDay = "15";
		String dateRangeFromTo = "01-07-2016 to 31-08-2016";
		String toDay = "16";
		String serviceOrgREf = "AutoTestServOrgRef";

		
		waitForExtJSAjaxComplete(25);
		
		collapseNavigation();
		waitForExtJSAjaxComplete(10);
		
		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		
		/*setServiceOrganization(serviceOrgREf, "Reference");
		waitForExtJSAjaxComplete(10);*/
		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		
		setCustomDateRange(dateRangeFromTo);
		Thread.sleep(15000);
		//waitForExtJSAjaxComplete(5);
		
		clickDateIconScheduler();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(verifyFromToCalendars("1"), "From calendar is available");
		
		softAssert.assertTrue(verifyFromToCalendars("2"), "To calendar is available");
		
		waitForExtJSAjaxComplete(25);

		clickButtonUsingClassName(FOOTER_BTN_CLASS_NAME, "Cancel");

		waitForExtJSAjaxComplete(5);
		
		selectRefreshButton();
		
		waitForExtJSAjaxComplete(25);

		clickDateIconScheduler();

		waitForExtJSAjaxComplete(25);

		//Select From date
		setCustomDateViaCalendarBtn("1", month, year, fromDay);

		waitForExtJSAjaxComplete(25);

		//Select TO date
		setCustomDateViaCalendarBtn("2", month, year, toDay);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(verifyDatesDiabledInToCalendar("2", 1, 14), "Dates are disabled in TO Calendar");
		
		waitForExtJSAjaxComplete(25);
		
		//TODO check once
		clickButtonUsingClassName(FOOTER_BTN_CLASS_NAME, "Cancel");
		
		waitForExtJSAjaxComplete(5);
		
		selectRefreshButton();
		
		waitForExtJSAjaxComplete(25);
		
		clickDateIconScheduler();
		
		waitForExtJSAjaxComplete(25);
		
		//Select TO date
		setCustomDateViaCalendarBtn("2", month, year, toDay);
		
		waitForExtJSAjaxComplete(25);
		
		clickButtonUsingClassName(FOOTER_BTN_CLASS_NAME, "Cancel");
		
		waitForExtJSAjaxComplete(5);
		
		selectRefreshButton();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getCustomDateRange(), dateRangeFromTo, "The Date range is not changed");
		
		waitForExtJSAjaxComplete(25);
		
		clickDateIconScheduler();
		
		waitForExtJSAjaxComplete(5);
		
		//Select TO date
		setCustomDateViaCalendarBtn("2", month, year, toDay);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(verifyDatesDiabledInToCalendar("1", 17, 30), "Dates are disabled in FROM Calendar");
		
		waitForExtJSAjaxComplete(25);
		
		clickButtonUsingClassName(FOOTER_BTN_CLASS_NAME, "Cancel");
		
		waitForExtJSAjaxComplete(5);
		
		selectRefreshButton();
		
		waitForExtJSAjaxComplete(25);
		
		//Set Date in Calendar and verify the same
		clickDateIconScheduler();
		
		waitForExtJSAjaxComplete(5);
		
		//Select From date
		setCustomDateViaCalendarBtn("1", month, year, fromDay);
		
		waitForExtJSAjaxComplete(25);
		
		//Select TO date
		setCustomDateViaCalendarBtn("2", "Aug", year, "31");
		
		waitForExtJSAjaxComplete(25);
		
		clickButtonUsingClassName(FOOTER_BTN_CLASS_NAME, "Apply");
		
		waitForExtJSAjaxComplete(5);
		
		dateRangeFromTo = "15-07-2016 to 31-08-2016";
		
		softAssert.assertEquals(getCustomDateRange(), dateRangeFromTo, "The Date range "+dateRangeFromTo+" is displayed");
		
		waitForExtJSAjaxComplete(25);
		
		//Try to select any date in future (after today) 
		clickDateIconScheduler();
		
		waitForExtJSAjaxComplete(5);
		
		year = String.valueOf(getCurrentYear(getFutureDate(0))+1);
		System.err.println(year);
		
		//Select From date
		setCustomDateViaCalendarBtn("1", month, year, fromDay);
		
		waitForExtJSAjaxComplete(25);
		
		//Select TO date
		setCustomDateViaCalendarBtn("2", "Aug", year, "31");
		
		waitForExtJSAjaxComplete(25);
		
		clickButtonUsingClassName(FOOTER_BTN_CLASS_NAME, "Apply");
		
		waitForExtJSAjaxComplete(5);
		
		dateRangeFromTo = "15-07-"+year+" to 31-08-"+year+"";
		
		softAssert.assertEquals(getCustomDateRange(), dateRangeFromTo, "The Date range "+dateRangeFromTo+" is displayed");
		
		waitForExtJSAjaxComplete(25);
		
		//Try to select any date in future (after today) 
		clickDateIconScheduler();
		
		waitForExtJSAjaxComplete(5);
		
		year = String.valueOf(getCurrentYear(getFutureDate(0))-1);
		System.err.println(year);
		
		//Select From date
		setCustomDateViaCalendarBtn("1", month, year, fromDay);
		
		waitForExtJSAjaxComplete(25);
		
		//Select TO date
		setCustomDateViaCalendarBtn("2", month, year, fromDay);
		
		waitForExtJSAjaxComplete(25);
		
		clickButtonUsingClassName(FOOTER_BTN_CLASS_NAME, "Apply");
		
		waitForExtJSAjaxComplete(5);
		
		dateRangeFromTo = "15-07-"+year+"";
		
		softAssert.assertEquals(getCustomDateRange(), dateRangeFromTo, "Only 1 Date range "+dateRangeFromTo+" is available in date range field");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertAll();
		
		Reporter.log("Custom date range is successfully verified", true);
	}
	
	/**
	 * Test Case Id : C89559, C83194, C83188
	 * Author : MMA
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testToSwitchToThePreviousOrNextDayOfDateRange() throws Exception{

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:Per hour: '<'/'>' switch to the previous/next day of date range, when date range duration and Tree structure > 1 day: C89559, C83194, C83188" + " </span><br>", 
				true);

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testToSwitchToThePreviousOrNextDayOfDateRange");

		String serviceOrgREf = "AutoTestServOrg";
		String serviceGrpRef = "AutoTestServGrp1Ref";
		String taskforceRef = "AutoTestTaskFrc1Ref";
		String fromDate = "01-08-2016";	
		String dateRangeFromTo = fromDate+" to 31-08-2016";
		String fromDateNext = "02-08-2016"; 
		String fromDateNext2 = "03-08-2016";

		waitForExtJSAjaxComplete(25);
		collapseNavigation();
		WorkOrderPageObject woObj = new WorkOrderPageObject();
		waitForExtJSAjaxComplete(5);

		//C83194
		//TODO : Scheduler loading Issue
		clickModuleInStartPage(wo_StartMenu_Xpath);
	//	waitAndClick(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(15);
		waitForExtJSAjaxComplete(15);

		//All the WO which are displayed in the work order overview are displayed in scheduler board irrespective with WO status and linked site, so no need to apply filter
		/*woObj.clickFilterResults();
		waitForExtJSAjaxComplete(20);

		woObj.expandMaintenanceAndSelectComplianceValueInFilterResults("x-window x-resizable-pinned", "Add restriction", "General Properties","Status Class");
		waitForExtJSAjaxComplete(5);

		setValueGridLookupWithFilters("Finished","Name");
		waitForExtJSAjaxComplete(5);

		woObj. selectComplianceRestriction("x-window x-resizable-pinned","Status Class =");
		waitForExtJSAjaxComplete(5);

		woObj.clickAnyButtonInFilterResults("x-window x-resizable-pinned","NOT");
		waitForExtJSAjaxComplete(5);

		clickSaveFilterInFilterWindow();
		waitForExtJSAjaxComplete(10);

		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", "1aqaPreParSites", "Site");
		waitForExtJSAjaxComplete(25);*/
	
		int size = getGridSize();
		waitForExtJSAjaxComplete(5);
		
		closeModule("Work Order");
		waitForExtJSAjaxComplete(10);
		
		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath);
		//clickXPath(XPATH_SCHEDULER);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(getZoomButtonStatus().contains("icon-zoom-out"),"The Calendar is in per hour view by default");
		waitForExtJSAjaxComplete(5);

		//setServiceOrganization(serviceOrgREf, "Reference");
		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		
		setCustomDateRange(dateRangeFromTo);
		Thread.sleep(15000);
		//waitForExtJSAjaxComplete(15);

		
		/*woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", "1aqaPreParSites", "Site");
		waitForExtJSAjaxComplete(25);
	*/
		softAssert.assertEquals(size, getSchedulerGridSize(),"On the overview all the WO that correspond to the set filters are displayed");
		waitForExtJSAjaxComplete(5);

		clickAdministration();
		waitForExtJSAjaxComplete(5);

		expandMasterData();

		clickAdministrationOptions("Master Data","Service Organizations");

		expandParentNodeInTreeByTextValue(serviceOrgREf);
		waitForExtJSAjaxComplete(5);

		expandParentNodeInTreeByTextValue(serviceGrpRef);
		waitForExtJSAjaxComplete(5);

		expandChildNodeInTreeByTextValue("Task Forces");
		waitForExtJSAjaxComplete(5);

		expandChildNodeInTreeByTextValue(taskforceRef);
		waitForExtJSAjaxComplete(10);

		Tree.checkNodeInTreeByTextValue(driver,taskforceRef);

		List<String> workResources = getMembersOfTaskForce();
		waitForExtJSAjaxComplete(5);
		closeModule("Administration");
		
		//Navigate to Scheduler
		//TODO : Scheduler loading Issue
		//clickXPath(XPATH_SCHEDULER);
		waitForExtJSAjaxComplete(15);

		setCustomDateRange(dateRangeFromTo);
		Thread.sleep(15000);
		waitForExtJSAjaxComplete(15);

		for(String workRes : workResources) {
			//String formatedworkRes = (workRes.split(" ")[1]+" "+workRes.split(" ")[0]).trim();
			softAssert.assertTrue(selectAResource( serviceGrpRef,  taskforceRef,  workRes),"Resources displays the hierarchy of Resource  "+workRes+"  for the selected SO1");
		}

		waitForExtJSAjaxComplete(5);

		//TODO C83194 Material Resource needs to be verified in the future.

		//C83188
		disableWorkingHoursButton();
		waitForExtJSAjaxComplete(15);

		ZoomIn();
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(getZoomButtonStatus().contains("icon-zoom-out"), "Zoom button changes from plus to minus");
		waitForExtJSAjaxComplete(5);

		ZoomOut();
		waitForExtJSAjaxComplete(25);
		Thread.sleep(1500);
		softAssert.assertTrue(getZoomButtonStatus().contains("icon-zoom-in"), "Zoom button changes from minus to plus");

		ZoomIn();
		waitForExtJSAjaxComplete(10);

		softAssert.assertEquals(getTimeLineHeader(), getFormatedDate(fromDate),"Top header contains day in "+"day name, day number, month name, year" +"format");		
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getZoomMode("middle"), "hours","Zoom mode is switched to 'per hour'");		

		ZoomOut();
		waitForExtJSAjaxComplete(10);

		String fromDateFormated = getFormatedWeekNumb(fromDate);
		waitForExtJSAjaxComplete(25);

		fromDate = "ISO Week "+ getWeekNo(fromDate)+ ", " + fromDateFormated;

		softAssert.assertEquals(getTimeLineHeader(), fromDate,"Top header contains day name in "+" Week ,week number, month name, year" +"format");		
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getZoomMode("bottom"), "week","Zoom mode is switched to 'per day'");		
		waitForExtJSAjaxComplete(5);

		ZoomIn();
		closeModule("Scheduler");
		waitForExtJSAjaxComplete(25);

		clickModuleInStartPage(scheduler_StartMenu_Xpath);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		//C89559
		fromDate = "01-08-2016";

		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		
		setCustomDateRange(dateRangeFromTo);
		Thread.sleep(15000);
		//waitForExtJSAjaxComplete(25);

		softAssert.assertEquals(getGoToDate(), fromDate,"The first day of the date range is shown in GOTO");
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getTimeLineHeader(), getFormatedDate(fromDate),"The first day of the date range is displayed on the calendar");		
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(getZoomButtonStatus().contains("icon-zoom-out"),"The Calendar is in per hour view by default");

		clickOnNavigationsButtons("next","following-sibling");
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getGoToDate(), fromDateNext,"The next day of the date range is shown in GOTO");
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getTimeLineHeader(), getFormatedDate(fromDateNext),"The next day of the date range is shown in header");		
		softAssert.assertTrue(getZoomButtonStatus().contains("icon-zoom-out"),"The Calendar is in per hour view by default");

		clickOnNavigationsButtons("next","following-sibling");
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getGoToDate(), fromDateNext2,"The next day of the date range is shown in GOTO");
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getTimeLineHeader(), getFormatedDate(fromDateNext2),"The next day of the date range is shown in header");		
		softAssert.assertTrue(getZoomButtonStatus().contains("icon-zoom-out"),"The Calendar is in per hour view by default");

		clickOnNavigationsButtons("previous","preceding-sibling");
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getGoToDate(), fromDateNext,"The next day of the date range is shown in GOTO");
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getTimeLineHeader(), getFormatedDate(fromDateNext),"The previous day of the date range is shown in header");		
		softAssert.assertTrue(getZoomButtonStatus().contains("icon-zoom-out"),"The Calendar is in per hour view by default");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(isEverynavigationButtonPresent(),"Under the Calendar there are 4 navigation buttons:<<, <, >, >>");

		softAssert.assertAll();

		Reporter.log("Per hour: '<'/'>' switch to the previous/next day of date range, when date range duration > 1 day and Tree structure ", true);
	}

	/**
	 * Test Case Id : C90302 
	 * Author : MMA
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testInformationPanel() throws Exception{

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:At the right side of the Planning sheet, an Information panel can be opened or collapsed: C90302" + " </span><br>", 
				true);

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testInformationPanel");

		String serviceOrgREf = "AutoTestServOrgRef";
		String serviceGrpRef = "AutoTestServGrpRef";
		String taskforceRef = "AutoTestTaskFrcRef";
		String sche_GrpRef = "AutoTestServGrpRef";
		String sche_TaskFrcRef = "AutoTestTaskFrcRef ";
		String sche_Contact = "Sandhya Sundaramurthy";


		waitForExtJSAjaxComplete(25);

		clickAdministration();

		waitForExtJSAjaxComplete(5);

		expandMasterData();

		clickAdministrationOptions("Master Data","Service Organizations");

		expandParentNodeInTreeByTextValue(serviceOrgREf);
		waitForExtJSAjaxComplete(5);

		expandChildNodeInTreeByTextValue(serviceGrpRef);
		waitForExtJSAjaxComplete(5);

		Tree.checkNodeInTreeByTextValue(driver,serviceGrpRef);
		waitForExtJSAjaxComplete(5);

		String admin_SGrpCod = getServOrgFieldVal(serviceGrpRef,"code","input");

		expandChildNodeInTreeByTextValue("Task Forces");
		waitForExtJSAjaxComplete(5);

		Tree.checkNodeInTreeByTextValue(driver,taskforceRef);
		waitForExtJSAjaxComplete(5);

		String admin_TskFsCod = getServOrgFieldVal(taskforceRef,"code","input");
		waitForExtJSAjaxComplete(5);

		closeModule("Administration");
		waitForExtJSAjaxComplete(5);
		
		collapseNavigation();
		waitForExtJSAjaxComplete(5);

		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		//setServiceOrganization(serviceOrgREf, "Reference");
		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		
		//Hardcoding july month because if we set today's date previous week days are getting hided in automation
		String fromDate = "04-07-2016";
		String dateRangeFromTo = fromDate+" to 31-07-2016";

		setCustomDateRange(dateRangeFromTo);
		Thread.sleep(15000);
		//waitForExtJSAjaxComplete(15);
		
		collapseResourceInTreeScheduler("servicegroup");
		waitForExtJSAjaxComplete(10);
		expandResourcesInTreeScheduler("servicegroup",sche_GrpRef);
		waitForExtJSAjaxComplete(25);
		
		//Service Group
		clickOnResourceTreeInScheduler("servicegroup",sche_GrpRef);
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getInformationPanelMode(),"collapsed", "At the right side of the Planning sheet, an Information panel is collapsed");

		clickOnInformationPanel("expand");
		waitForExtJSAjaxComplete(15);

		softAssert.assertEquals(getInformationPanelMode(),"expanded", "The Info panel is opened.");

		String labelNameCd = "Code:";
		String label_FstName = "First name:";
		String label_LstName = "Last name:";

		softAssert.assertEquals(getInfoPanelLabelValue(labelNameCd,"td"), admin_SGrpCod,"relevant information for the last clicked object (Service group) is shown");

		clickOnInformationPanel("collapse");
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getInformationPanelMode(),"collapsed", "Information panel is collapsed");
		waitForExtJSAjaxComplete(10);
		
		clickOnInformationPanel("expand");
		waitForExtJSAjaxComplete(10);

		softAssert.assertEquals(getInformationPanelMode(),"expanded", "The Info panel is opened.");
		softAssert.assertEquals(getInfoPanelLabelValue(labelNameCd,"td"), admin_SGrpCod,"relevant information for the last clicked object (Service group) is shown");

		//Task Force
		clickOnResourceTreeInScheduler("taskforce",sche_TaskFrcRef);
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getInfoPanelLabelValue(labelNameCd,"td"), admin_TskFsCod,"relevant information for the last clicked object (Task Force) is shown");

		clickOnInformationPanel("collapse");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(getInformationPanelMode().equals("collapsed"), "Information panel is collapsed");

		clickOnInformationPanel("expand");
		waitForExtJSAjaxComplete(15);

		softAssert.assertTrue(getInformationPanelMode().equals("expanded"), "The Info panel is opened.");
		softAssert.assertEquals(getInfoPanelLabelValue(labelNameCd,"td"), admin_TskFsCod,"relevant information for the last clicked object (Task Force) is shown");
		waitForExtJSAjaxComplete(15);
		
		//Work Resource 
		clickOnResourceTreeInScheduler("resource-type",sche_Contact);
		waitForExtJSAjaxComplete(5);
		
		//No first_name and last_name format is found 
/*		softAssert.assertEquals(getInfoPanelLabelValue(label_FstName,"td"),"Sandhya","relevant information for the last clicked object (Work Resource) is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(label_LstName,"td"),"Sundaramurthy","relevant information for the last clicked object (Work Resource) is shown");
*/
		//String formatedworkRes = (sche_Contact.split(" ")[1]+" "+sche_Contact.split(" ")[0]).trim();

		softAssert.assertEquals(getInfoPanelheaderRef("1"),sche_Contact,"relevant information for the last clicked object (Work Resource) is shown");
		
		clickOnInformationPanel("collapse");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(getInformationPanelMode().equals("collapsed"), "Information panel is collapsed");

		clickOnInformationPanel("expand");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(getInformationPanelMode().equals("expanded"), "The Info panel is opened.");
		//No first_name and last_name format is found 
		/*softAssert.assertEquals(getInfoPanelLabelValue(label_FstName,"td"),"Sandhya","relevant information for the last clicked object (Work Resource) is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(label_LstName,"td"),"Sundaramurthy","relevant information for the last clicked object (Work Resource) is shown");
		*/
		softAssert.assertEquals(getInfoPanelheaderRef("1"),sche_Contact,"relevant information for the last clicked object (Work Resource) is shown");
		waitForExtJSAjaxComplete(5);

		//TODO : C90302 Material Resource needs to be verified in the future.
		//Task 
		String schedulerTask = "38 SchedulerRelated";

		WorkOrderPageObject woObj = new WorkOrderPageObject();
		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", "SchedulerRelated", "Reference");
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", "SchedulerRelated");
		waitForExtJSAjaxComplete(5);

		selectActions("Edit");
		waitForExtJSAjaxComplete(5);

		WorkOrderPageObject woPobj = new WorkOrderPageObject();
		waitForExtJSAjaxComplete(25);

		woPobj.clickGeneralTab();
		waitForExtJSAjaxComplete(25);

		String woNature = woPobj.getNature();
		String woLocation = woPobj.getLocation();

		WorkOrderDetailTabsPageObject woDTPobj = new WorkOrderDetailTabsPageObject();
		woDTPobj.closeXWindow();
		waitForExtJSAjaxComplete(15);
		
		clickOnInformationPanel("collapse");
		waitForExtJSAjaxComplete(10);
		
		clickOnScheduledTask(sche_Contact, schedulerTask);

		clickOnInformationPanel("expand");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(getInformationPanelMode().equals("expanded"), "The Info panel is opened.");
		softAssert.assertEquals(getInfoPanelLabelValue("Nature:","td"),woNature,"relevant information for the last clicked object (Task) is shown");
		softAssert.assertEquals(getInfoPanelLabelValue("Site:","td").replace(", IN",""),woLocation,"relevant information for the last clicked object (Task) is shown");

		clickOnInformationPanel("collapse");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(getInformationPanelMode().equals("collapsed"), "Information panel is collapsed");

		clickOnInformationPanel("expand");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(getInformationPanelMode().equals("expanded"), "The Info panel is opened.");
		softAssert.assertEquals(getInfoPanelLabelValue("Nature:","td"),woNature,"relevant information for the last clicked object (Task) is shown");
		softAssert.assertEquals(getInfoPanelLabelValue("Site:","td").replace(", IN",""),woLocation,"relevant information for the last clicked object (Task) is shown");

		softAssert.assertAll();

		Reporter.log("At the right side of the Planning sheet, an Information panel can be opened or collapsed", true);
	}

	/**
	 * Test Case Id : C83198, C90307
	 * Author : MMA
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testToVerifyServGrpContentInInfoPanel() throws Exception{

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:User can switch between the 2 zoom modes by clicking the day block: C83198, C90307" + " </span><br>", 
				true);

		SoftAssert softAssert = new SoftAssert(); 

		softAssert.setMethodName("testToVerifyServGrpContentInInfoPanel");

		String fromDate = "04-07-2016";
		String dateRangeFromTo = fromDate+" to 31-07-2016";
		String serviceOrgREf = "AutoTestServOrgRef";
		String serviceGrpRef = "AutoTestServGrpRef";
		String taskforceRef = "AutoTestTaskFrcRef";
		String serviceGrpRef1 = "AutoTestServGrp1Ref";
		String taskforceRef1 = "AutoTestTaskFrc1Ref";
		String site = "1aqaPreParSites";

		waitForExtJSAjaxComplete(25);

		clickAdministration();

		waitForExtJSAjaxComplete(5);

		expandMasterData();

		clickAdministrationOptions("Master Data","Service Organizations");

		expandParentNodeInTreeByTextValue(serviceOrgREf);
		waitForExtJSAjaxComplete(5);

		//Service Group
		Tree.checkNodeInTreeByTextValue(driver,serviceGrpRef);
		waitForExtJSAjaxComplete(5);

		String admin_SGrpCod = getServOrgFieldVal(serviceGrpRef,"code","input");

		String admin_SGrpDes = getServOrgFieldVal(serviceGrpRef,"description","textarea");
		waitForExtJSAjaxComplete(5);

		expandChildNodeInTreeByTextValue(serviceGrpRef);
		waitForExtJSAjaxComplete(5);

		expandChildNodeInTreeByTextValue("Task Forces");
		waitForExtJSAjaxComplete(5);

		expandChildNodeInTreeByTextValue(taskforceRef);
		waitForExtJSAjaxComplete(25);

		Tree.checkNodeInTreeByTextValue(driver,taskforceRef);

		List<String> workResources = getMembersOfTaskForce();
		waitForExtJSAjaxComplete(5);

		int size = workResources.size();

		taskforceRef = "AutoTestTaskFrcRef ["+size+"]";

		//Service Group1
		Tree.checkNodeInTreeByTextValue(driver,serviceGrpRef1);
		waitForExtJSAjaxComplete(10);

		String admin_SGrpCod1 = getServOrgFieldVal(serviceGrpRef1,"code","input");

		String admin_SGrpDes1 = getServOrgFieldVal(serviceGrpRef1,"description","textarea");
		waitForExtJSAjaxComplete(5);

		expandParentNodeInTreeByTextValue(serviceGrpRef1);
		waitForExtJSAjaxComplete(5);

		expandChildNodeInTreeByTextValue("Task Forces");
		waitForExtJSAjaxComplete(5);

		expandChildNodeInTreeByTextValue(taskforceRef1);
		waitForExtJSAjaxComplete(25);

		Tree.checkNodeInTreeByTextValue(driver,taskforceRef1);

		workResources = getMembersOfTaskForce();
		waitForExtJSAjaxComplete(5);

		size = workResources.size();

		taskforceRef1 = "AutoTestTaskFrc1Ref ["+size+"]";

		closeModule("Administration");
		waitForExtJSAjaxComplete(5);
		
		//Navigate to Administration and Enable Scheduler
		/*navigateToAdministrationAndEnableScheduler("Accounts", "Groups", "automation group", "Scheduler");
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);*/

		//First time it is not opening so closing and opening again
		//TODO : Scheduler loading Issue
		collapseNavigation();
		waitForExtJSAjaxComplete(5);
		//clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);

		//waitForMaskDisappear();

		//closeModule("Scheduler");
		//waitForExtJSAjaxComplete(25);

		//Navigate to Scheduler
		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);

		waitForMaskDisappear();
		waitForExtJSAjaxComplete(25);

		//setServiceOrganization(serviceOrgREf, "Reference");
		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		
		//C83198
		setCustomDateRange(dateRangeFromTo);
		waitForExtJSAjaxComplete(15);

		disableWorkingHoursButton();
		waitForExtJSAjaxComplete(5);

		ZoomIn();
		waitForExtJSAjaxComplete(15);
		waitForMaskDisappear();

		softAssert.assertTrue(getZoomButtonStatus().contains("icon-zoom-out"),"The Calendar is in per hour view");
		clickOnDayBlock("top");
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(getZoomButtonStatus().contains("icon-zoom-in"),"Zoom button changes from minus to plus");
		waitForExtJSAjaxComplete(15);
		softAssert.assertEquals(getZoomMode("bottom"), "week","Zoom mode is switched to 'per day'");		
		waitForExtJSAjaxComplete(5);

		String fromDateFormated = getFormatedWeekNumb(fromDate);
		fromDate = "ISO Week "+ getWeekNo(fromDate)+ ", " + fromDateFormated;
		softAssert.assertEquals(getTimeLineHeader(), fromDate,"Top header contains day name in "+" Week ,week number, month name, year" +"format");		
		waitForExtJSAjaxComplete(5);
		Thread.sleep(1000);
		
		enableWorkingHoursButton();
		waitForExtJSAjaxComplete(25);
		
		clickOnDayBlock("bottom");
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(getZoomButtonStatus().contains("icon-zoom-out"),"Zoom button changes from plus to minus");
		waitForExtJSAjaxComplete(15);
		
		enableWorkingHoursButton();
		waitForExtJSAjaxComplete(15);
		
		softAssert.assertEquals(getZoomMode("middle"), "hours","Zoom mode is switched to 'per hour'");		
		waitForExtJSAjaxComplete(5);

		fromDate = "04-07-2016";
		softAssert.assertEquals(getTimeLineHeader(), getFormatedDate(fromDate),"Top header contains day in "+"day name, day number, month name, year" +"format");		
		waitForExtJSAjaxComplete(15);

		//C90307
		//Service Group verification
		clickOnResourceTreeInScheduler("servicegroup",serviceGrpRef);
		waitForExtJSAjaxComplete(5);

		clickOnInformationPanel("expand");
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getInformationPanelMode(),"expanded", "The Info panel is opened.");

		String labelNameCd = "Code:";
		String labelNameDesp = "Description:";
		String labelNameTF = "Task forces:";
		String labelNameSite = "Sites:";

		softAssert.assertEquals(getInfoPanelLabelValue(labelNameCd,"td"), admin_SGrpCod,"relevant information for the last clicked object (Service group) code is shown");

		softAssert.assertEquals(getInfoPanelLabelValue(labelNameDesp,"td"), admin_SGrpDes,"relevant information for the last clicked object (Service group) Description is shown");

		softAssert.assertEquals(getInfoPanelLabelValue(labelNameTF,"td"),taskforceRef ,"relevant information for the last clicked object (Service group) task force is shown");

		softAssert.assertEquals(getInfoPanelLabelValue(labelNameSite,"td"),site ,"relevant information for the last clicked object (Service group) site is shown");

		softAssert.assertEquals(getInfoPanelheaderRef("1"),serviceGrpRef ,"relevant information for the last clicked object (Service group) referece is shown");

		waitForExtJSAjaxComplete(5);

		//Service Group1 verification
		clickOnResourceTreeInScheduler("servicegroup",serviceGrpRef1);
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getInfoPanelLabelValue(labelNameCd,"td"), admin_SGrpCod1,"relevant information for the last clicked object (Service group) code is shown");

		softAssert.assertEquals(getInfoPanelLabelValue(labelNameDesp,"td"), admin_SGrpDes1,"relevant information for the last clicked object (Service group) Description is shown");

		softAssert.assertEquals(getInfoPanelLabelValue(labelNameTF,"td"),taskforceRef1 ,"relevant information for the last clicked object (Service group) task force is shown");

		softAssert.assertEquals(getInfoPanelLabelValue(labelNameSite,"td"),site ,"relevant information for the last clicked object (Service group) site is shown");

		softAssert.assertEquals(getInfoPanelheaderRef("1"),serviceGrpRef1 ,"relevant information for the last clicked object (Service group) referece is shown");
		waitForExtJSAjaxComplete(5);

		softAssert.assertAll();
		Reporter.log("User can switch between the 2 zoom modes by clicking the day block and content verification for the Service group in Info Panel", true);

	}
	
	/**
	 * Test Case ID: C82452
	 * Author : SSU
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testSchedulerModuleWithoutLicense() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Access to the 'Scheduler' module : C82452" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testSchedulerModuleWithoutLicense");
		
		String user2= "AqaWebEmplDntDelete";
		String password= "qwerty";
		
		waitForExtJSAjaxComplete(25);
		
		//Navigate to Administration and Enable Scheduler
		/*navigateToAdministrationAndEnableScheduler("Accounts", "Groups", "automation group", "Scheduler");
		
		waitForExtJSAjaxComplete(25);
		
		closeModule("myMCS Administration");*/
		
		//waitForExtJSAjaxComplete(5);
		
		//TODO : Scheduler loading Issue
		collapseNavigation();
		waitForExtJSAjaxComplete(5);
		//clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//waitAndClick(XPATH_SCHEDULER);
		
		//waitForExtJSAjaxComplete(25);
		
		//closeModule("Scheduler");
		
		//waitForExtJSAjaxComplete(5);
		
		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//waitAndClick(XPATH_SCHEDULER);
		waitForExtJSAjaxComplete(15);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(5);
		
		//TODO: No WO and Scheduler label present in Scheduler window. So, verifying boards button 
		/*softAssert.assertTrue(verifyHeaderTextInScheduler(SCHEDULER_WIN_XPATH+WORKORDERS_HEADER_XPATH), "Workorders Label is present in Scheduler window");
		
		softAssert.assertTrue(verifyHeaderTextInScheduler(SCHEDULER_WIN_XPATH+SCHEDULER_HEADER_XPATH), "Scheduler Label is present in Scheduler window");
		*/
		
		softAssert.assertTrue(verifyHeaderTextInScheduler(SCHEDULER_WIN_XPATH+wo_Boards_Xpath), "Boards button is present in Scheduler window");
		
		driver.navigate().refresh();
		
		waitForExtJSAjaxComplete(25);
		
		//Verify Scheduler Module is present in start Page
		softAssert.assertTrue(verifySchedulerTextsInStartPage(START_PAGE_MODULE_XPATH), "Scheduler Module is present in Start Page");
		
		//Verify Scheduler GO Text is present in start Page
		softAssert.assertTrue(verifySchedulerTextsInStartPage(START_PAGE_MODULE_XPATH+GO_START_PAGE_XPATH), "Scheduler GO Text is present in Start Page");
		
		//Verify Scheduler Icon Width and Height
		softAssert.assertTrue(getSchedulerIconSize(START_PAGE_MODULE_XPATH, "height").contains("48"), "Scheduler Icon Height is 48px.");
		waitForExtJSAjaxComplete(25);
		softAssert.assertTrue(getSchedulerIconSize(START_PAGE_MODULE_XPATH, "width").contains("48"), "Scheduler Icon width is 48px.");
		
		waitForExtJSAjaxComplete(25);
		
		//Log into Portal using User 2 who doesn't have Scheduler License
		navigateToMainPage(user2,password); 
		
		waitForExtJSAjaxComplete(25);
		
		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//waitAndClick(XPATH_SCHEDULER);
		
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(getWarningDialogTextMsg().contains("You do not have the License to use the 'Scheduler' module."), "You do not have the License to use the 'Scheduler' module. is displayed");
		
		clickOnDialogButton("OK");
		
		softAssert.assertAll();
		
		Reporter.log("Scheduler is opened only for Users who have License is successfully verified", true);
	}

	/**
	 * Test Case ID: C82433
	 * Author : SSU
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testAvailableCommandsSchedulerModule() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Available Commands : C82433" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testAvailableCommandsSchedulerModule");
		
		String woRef = "SchedulerSLA";
		String serviceOrgREf = "AutoTestServOrgRef";
		
		waitForExtJSAjaxComplete(20);

	 	//TODO : Scheduler loading Issue
		collapseNavigation();
		waitForExtJSAjaxComplete(5);
		/*clickModuleInStartPage(scheduler_StartMenu_Xpath);
		//clickXPath(XPATH_SCHEDULER);
		
		waitForExtJSAjaxComplete(20);
		
		closeModule("Scheduler");
		
		waitForExtJSAjaxComplete(5);
*/		
		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);
		
		waitForExtJSAjaxComplete(25);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(25);
		
		/*setServiceOrganization(serviceOrgREf, "Reference");
		
		waitForExtJSAjaxComplete(5);*/
		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();

		//TODO: Action button is not available in scheduler window
		//	softAssert.assertTrue(verifyButtonsScheduler(SCHEDULER_WIN_XPATH+SCHEDULER_ACTIONS_XPATH), "Actions button is present in Scheduler Tab");

		softAssert.assertTrue(verifyButtonsScheduler(SCHEDULER_WIN_XPATH+SCHEDULER_OPTIONS_XPATH), "Options button is present in Scheduler Tab");

		softAssert.assertTrue(verifyRefreshButtonScheduler(SCHEDULER_WIN_XPATH+SCHEDULER_REFRESH_XPATH), "Refresh button is present in Scheduler Tab");

		String actionsArr[] = {"Edit", "Add Action", "View SLA Information", "Update SLA Info"};

		softAssert.assertTrue(verifyActionButtonsSchedulerBasedOnText(actionsArr), "All Action buttons are available");

		String actionsArr1[] = {"Change visible columns", "Print rows", "Export to Excel"};
		for(String action : actionsArr1){
			int i = 0;
			String className[] ={"icon-grid-columns","icon-grid-print","icon-grid-export-excel"}; 
			softAssert.assertTrue(verifyActionButtonsSchedulerBasedOnClass(action,className[i]), ""+action+" Action buttons are available");
			i++;
		}
		
		waitForExtJSAjaxComplete(5);

		selectRefreshButton();

		waitForExtJSAjaxComplete(5);
		
		selectActions("Edit");
		
		softAssert.assertTrue(getWarningDialogText().contains("Please select an item"), "The error that at least one item should be selected is displayed for Edit");
		
		clickOnDialogButton("OK");
		
		//Add Action
		//selectActions("Add Action");
		clickAddActionInSchedulerWin();
		
		softAssert.assertTrue(getWarningDialogText().contains("Please select an item"), "The error that at least one item should be selected is displayed for Add Action");
		
		clickOnDialogButton("OK");
		
		//Show SLA information
		selectActions("View SLA Information");
		
		waitForExtJSAjaxComplete(3);
		
		softAssert.assertTrue(getWarningDialogText().contains("You must select at least one Work Order to perform this action on"), "The error that at least one item should be selected is displayed for View SLA Information");
		
		clickOnDialogButton("OK");
		
		//Update SLA
		selectActions("Update SLA Info");
		
		waitForExtJSAjaxComplete(3);
		
		softAssert.assertTrue(getWarningDialogText().contains("You must select at least one Work Order to perform this action on"), "The error that at least one item should be selected is displayed for Update SLA Info");
		
		clickOnDialogButton("OK");
		
		//click on Refresh Button
		selectRefreshButton();
		
		waitForMaskDisappear();
		
		Reporter.log("Grid is Refreshed", true);
		
		//Change Visible Columns
		selectActionByClassName("Change visible columns...","icon-grid-columns");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(getWindowTitleCustomized("@class", "x-resizable-pinned").contains(CHANGE_VISIBLE_COLUMNS_WINDOW_HEADER), "Change Visible columns window is opened");
		
		closeUsingToolBarJS(CHANGE_VISIBLE_COLUMNS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		//click Print Rows
		selectActionByClassName("Print rows...","icon-grid-print");
		
		softAssert.assertTrue(getWindowTitleCustomized("@class", "x-window x-window-plain").contains(PAGE_TITLE_WINDOW_HEADER), "Page Title window is opened");
		
		softAssert.assertTrue(verifyPageTitleFields("pagetitle"), "Page Title Field is Available");
		
		softAssert.assertTrue(verifyPageTitleFields("colors"), "Row Colors Field is Available");
		
		softAssert.assertTrue(verifyButtonsScheduler(PAGE_TITLE_PRINT_XPATH), "Print Button is Available");
		
		closeUsingToolBarJS(PAGE_TITLE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		//Export to Excel
		selectActionByClassName("Export to Excel...","icon-grid-export-excel");
		
		softAssert.assertTrue(getWindowTitleCustomized("@class", "x-window x-window-plain").contains(EXPORT_TO_EXCEL_HEADER), "Export To Excel window is opened");
		
		softAssert.assertTrue(verifyPageTitleFields("colors"), "Include Row Colors Field is Available");
		
		softAssert.assertTrue(verifyButtonsScheduler(PAGE_TITLE_XPATH+EXPORT_TO_EXCEL_DOWNLOAD_XPATH), "Download Button is Available");
		
		closeUsingToolBarJS(EXPORT_TO_EXCEL_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		//Click On options and verify Options window is opened
		selectOptions();
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(getWindowTitleCustomized("@class", "x-resizable-pinned").contains(USER_OPTIONS_WINDOW_HEADER), "User Options window is opened");
		
		closeUsingToolBarJS(USER_OPTIONS_WINDOW_HEADER);
		
		WorkOrderPageObject woObj = new WorkOrderPageObject();
		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", woRef, "Reference");
		waitForExtJSAjaxComplete(3);
		
		//Check any Workorder and click on the 'Edit' command
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", woRef);
		
		selectActions("Edit");
		
		waitForExtJSAjaxComplete(5);
		
		WorkOrderPageObject woPobj = new WorkOrderPageObject();
		
		softAssert.assertTrue(woPobj.getWONameFromDetailsWindow(woRef), "WO Edit Details window is opened");
		
		waitForExtJSAjaxComplete(5);
		
		woPobj.closeUsingToolBar(WORKORDER_WINDOW_XPATH);
		
		waitForExtJSAjaxComplete(5);	
		
		//Check any Workorder and click on the 'Add Action' command
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", woRef);
		
		//selectActions("Add Action");
		clickAddActionInSchedulerWin();
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(getWindowTitleCustomized("@class", "x-form-label-top x-resizable-pinned").contains(ADD_ACTION_FOR_WO_HEADER), "Add Action for WO window is opened");
		
		woPobj.closeUsingToolBar(WORKORDER_WINDOW_XPATH);
		
		waitForExtJSAjaxComplete(5);	
		
		//Check any Workorder and click on the 'Show SLA' command Covered in another test case C82725
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", woRef);
		
		selectActions("View SLA Information");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(verifyMetricsHeaderInSLAInformation("WOSLAGrid", "Workorder Metrics"), "Workorder Metrics is Displayed in SLA Information Window");
		
		softAssert.assertTrue(verifyMetricsHeaderInSLAInformation("HDSLAGrid", "Call Metrics"), "Call Metrics is Displayed in SLA Information Window");
		
		closeUsingToolBarJS(SLA_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);	
		
		//Check any Workorder and click on the 'Update SLA' command
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", woRef);
		
		selectActions("Update SLA Info");
		
		waitForExtJSAjaxComplete(5);
		
		Reporter.log("SLA Information is updated", true);
		
		closeUsingToolBarJS(SLA_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		clickClearFilterButton();
		waitForExtJSAjaxComplete(5);
		
		selectRefreshButton();
		
		waitForExtJSAjaxComplete(5);
		
		String colName = "SLA Indicator Info";
		
		//Filter WO Grid with WO 2 Reference
		filterGridCustomized("@class", "x-grid3-viewport", "left to target", colName);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getSchedulerGridSize(), 2, "Two WO that meets the Searching criteria are displayed");
		
		waitForExtJSAjaxComplete(5);
		
		//sort Work Orders by 'SLA Indicator Info'
		verifyRowsAreSortedBasedOnSLAIndicatorInfo("@class", "x-grid3-viewport", colName);
		
		waitForExtJSAjaxComplete(5);
		
		//Uncheck WO and click on the 'Update SLA' command
		//setServiceOrganization("Service Roles Org", "Reference");
		driver.navigate().refresh();
		waitForExtJSAjaxComplete(15);

		collapseNavigation();
		waitForExtJSAjaxComplete(5);
		
		clickModuleInStartPage(scheduler_StartMenu_Xpath);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		
		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		
		selectActions("Update SLA Info");

		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(getWarningDialogText().contains("You must select at least one Work Order to perform this action on"), "The error that at least one item should be selected is displayed for Update SLA Info");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(5);
		
		/*setServiceOrganization(serviceOrgREf, "Reference");
		
		waitForExtJSAjaxComplete(5);
		*/
		softAssert.assertAll();
		
		Reporter.log("Available Commands In Overview Toolbar of Scheduler is successfully verified", true);
	}

	/**
	 * Test Case ID: C82725
	 * Author : SSU
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testSLAMetricsForWONotLinkedToCall() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: The planner can view the SLA Metrics for Workorders, that are not linked to a Call : C82725" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testSLAMetricsForWONotLinkedToCall");
		
		String woGoodSla = "SchedulerSLA";
		String woBadSla = "WoSLAInfoBad";
		String serviceOrgREf = "AutoTestServOrgRef";
		String woGoodSLAID = "39";
		String woBadSLAID = "42";
		
		waitForExtJSAjaxComplete(25);
		
		//Navigate to Administration and Enable Scheduler
		/*navigateToAdministrationAndEnableScheduler("Accounts", "Groups", "automation group", "Scheduler");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		*/
		//First time it is not opening so closing and opening again
		//TODO : Scheduler loading Issue
		collapseNavigation();
		waitForExtJSAjaxComplete(5);
		
		WorkOrderPageObject woObj = new WorkOrderPageObject();
		waitForExtJSAjaxComplete(5);

		//C83194
		//TODO : Scheduler loading Issue
		clickModuleInStartPage(wo_StartMenu_Xpath);
	//	waitAndClick(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(15);
		waitForExtJSAjaxComplete(15);

		//All the WO which are displayed in the work order overview are displayed in scheduler board irrespective with WO status and linked site, so no need to apply filter
		/*woObj.clickFilterResults();
		waitForExtJSAjaxComplete(20);

		woObj.expandMaintenanceAndSelectComplianceValueInFilterResults("x-window x-resizable-pinned", "Add restriction", "General Properties","Status Class");
		waitForExtJSAjaxComplete(5);

		setValueGridLookupWithFilters("Finished","Name");
		waitForExtJSAjaxComplete(5);

		woObj. selectComplianceRestriction("x-window x-resizable-pinned","Status Class =");
		waitForExtJSAjaxComplete(5);

		woObj.clickAnyButtonInFilterResults("x-window x-resizable-pinned","NOT");
		waitForExtJSAjaxComplete(5);

		clickSaveFilterInFilterWindow();
		waitForExtJSAjaxComplete(10);

		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", "1aqaPreParSites", "Site");
		waitForExtJSAjaxComplete(25);*/
	
		int size = getGridSize();
		waitForExtJSAjaxComplete(5);
		
		closeModule("Work Order");
		waitForExtJSAjaxComplete(10);
		
		clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);
		
		waitForMaskDisappear();
		
		closeModule("Scheduler");
		
		waitForExtJSAjaxComplete(25);
		
		//Navigate to Scheduler
	 	//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath);
		//clickXPath(XPATH_SCHEDULER);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(25);
		
		/*setServiceOrganization(serviceOrgREf, "Reference");
		
		waitForExtJSAjaxComplete(25);*/
		
		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		
		setCustomDateRange("01-07-2016 to 31-08-2016");
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		
		/*woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", "1aqaPreParSites", "Site");
		waitForExtJSAjaxComplete(25);
	*/
		softAssert.assertEquals(getSchedulerGridSize(),size, "Two WO that meets the Searching criteria are displayed");
		waitForExtJSAjaxComplete(25);
		
		//Show SLA information
		selectActions("View SLA Information");
		
		waitForExtJSAjaxComplete(3);
		
		softAssert.assertTrue(getWarningDialogText().contains("You must select at least one Work Order to perform this action on"), "The error that at least one item should be selected is displayed for View SLA Information");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(5);
		
		//GOOD SLA
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, woGoodSla, "@class", "x-panel scheduler"), "WorkOrder Good SLA is available in the grid");
		
		waitForExtJSAjaxComplete(2);
		
		//Double click on an SLA Indicator GOOD icon near the Call
		doubleClickOnSLAIndicatorIcon(woGoodSLAID, SLA_GOOD_INDICATOR_ICON_XPATH);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(getWindowTitleCustomized("@class", "x-resizable-pinned").contains(SLA_WINDOW_HEADER), "SLA Information window is opened");
		
		softAssert.assertTrue(verifyMetricsHeaderInSLAInformation("WOSLAGrid", "Workorder Metrics"), "Workorder Metrics is Displayed in SLA Information Window");
		
		softAssert.assertTrue(verifyButtonsScheduler(PRINT_BTN_SLA_INFO_XPATH), "Print button is present in SLA Info Window");
		
		softAssert.assertTrue(verifyButtonsScheduler(EXPORT_BTN_SLA_INFO_XPATH), "Export To Excel button is present in SLA Info Window");
		
		waitForExtJSAjaxComplete(5);
		
		//WO Metric Grid Verification
		String colNameArr[] = {"SLA Object Code", "SLA Object Reference", "Metric Name", "Chrono", "Metric Value", "Lower Target", "Upper Target", "Lower Target Variance", "Upper Target Variance"};
		
		softAssert.assertTrue(verifyColNamesInSLAInfoWindow("WOSLAGrid", colNameArr), "Detailed SLA information is displayed in the WO Metrics grid");
		
		waitForExtJSAjaxComplete(25);
		
		String woRow1ValueArr[] = {"SLAWO", "SLAWoRef", "Metric1", "Stopped", "0.00", "24.00", "48.00", "-24.00", "-48.00"};
		
		softAssert.assertTrue(verifyAllSLAFieldsAreReadOnly("WOSLAGrid", "mcsgridrow_FF0000", colNameArr, woRow1ValueArr), "All the fields in WO Metrics Grid is Read only");
		
		waitForExtJSAjaxComplete(25);
		
		String woRow2ValueArr[] = {"SLAWO", "SLAWoRef", "Metric2", "Stopped", "0.00", "24.00", "48.00", "-24.00", "-48.00"};
		
		softAssert.assertTrue(verifyAllSLAFieldsAreReadOnly("WOSLAGrid", "mcsgridrow_00CC00", colNameArr, woRow2ValueArr), "All the fields in WO Metrics Grid is Read only");
		
		waitForExtJSAjaxComplete(25);
		
		closeUsingToolBarJS(SLA_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		try{
			closeUsingToolBarJS("Details");
			waitForExtJSAjaxComplete(25);
		}
		catch(Exception e){
			waitForExtJSAjaxComplete(5);
		}
		//Select the Good SLA WO and click SLA Information
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", woGoodSla);
		waitForExtJSAjaxComplete(25);
		
		selectActions("View SLA Information");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(getWindowTitleCustomized("@class", "x-resizable-pinned").contains(SLA_WINDOW_HEADER), "SLA Information window is opened");
		
		softAssert.assertTrue(verifyMetricsHeaderInSLAInformation("WOSLAGrid", "Workorder Metrics"), "Workorder Metrics is Displayed in SLA Information Window");
		
		softAssert.assertTrue(verifyButtonsScheduler(PRINT_BTN_SLA_INFO_XPATH), "Print button is present in SLA Info Window");
		
		softAssert.assertTrue(verifyButtonsScheduler(EXPORT_BTN_SLA_INFO_XPATH), "Export To Excel button is present in SLA Info Window");
		
		waitForExtJSAjaxComplete(5);
		
		//WO Metric Grid Verification
		softAssert.assertTrue(verifyColNamesInSLAInfoWindow("WOSLAGrid", colNameArr), "Detailed SLA information is displayed in the WO Metrics grid");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(verifyAllSLAFieldsAreReadOnly("WOSLAGrid", "mcsgridrow_FF0000", colNameArr, woRow1ValueArr), "All the fields in WO Metrics Grid is Read only");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(verifyAllSLAFieldsAreReadOnly("WOSLAGrid", "mcsgridrow_00CC00", colNameArr, woRow2ValueArr), "All the fields in WO Metrics Grid is Read only");
		
		waitForExtJSAjaxComplete(25);
		
		closeUsingToolBarJS(SLA_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		//Bad SLA Related to WO Verification
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, woBadSla, "@class", "x-panel scheduler"), "WorkOrder Bad SLA is available in the grid");
		
		waitForExtJSAjaxComplete(2);
		
		//Double click on an SLA Indicator Bad icon near the Call
		doubleClickOnSLAIndicatorIcon(woBadSLAID, SLA_BAD_INDICATOR_ICON_XPATH);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(getWindowTitleCustomized("@class", "x-resizable-pinned").contains(SLA_WINDOW_HEADER), "SLA Information window is opened");
		
		softAssert.assertTrue(verifyMetricsHeaderInSLAInformation("WOSLAGrid", "Workorder Metrics"), "Workorder Metrics is Displayed in SLA Information Window");
		
		softAssert.assertTrue(verifyButtonsScheduler(PRINT_BTN_SLA_INFO_XPATH), "Print button is present in SLA Info Window");
		
		softAssert.assertTrue(verifyButtonsScheduler(EXPORT_BTN_SLA_INFO_XPATH), "Export To Excel button is present in SLA Info Window");
		
		waitForExtJSAjaxComplete(5);
		
		//WO Metric Grid Verification
		String colNameArrBad[] = {"SLA Object Code", "SLA Object Reference", "Metric Name", "Chrono", "Metric Value", "Lower Target", "Upper Target", "Lower Target Variance", "Upper Target Variance"};
		
		softAssert.assertTrue(verifyColNamesInSLAInfoWindow("WOSLAGrid", colNameArrBad), "Detailed SLA information is displayed in the WO Metrics grid");
		
		waitForExtJSAjaxComplete(25);
		
		String woRow1ValueArrBad[] = {"WoSLAInfoBad", "WoSLAInfoBadRef", "Metrics1", "Stopped", "0.01", "24.00", "48.00", "-23.99", "-47.99"};
		
		softAssert.assertTrue(verifyAllSLAFieldsAreReadOnly("WOSLAGrid", "mcsgridrow_FF0000", colNameArrBad, woRow1ValueArrBad), "All the fields in WO Metrics Grid is Read only");
		
		waitForExtJSAjaxComplete(25);
		
		closeUsingToolBarJS(SLA_WINDOW_HEADER);

		waitForExtJSAjaxComplete(25);

		try{
			closeUsingToolBarJS("Details");
			waitForExtJSAjaxComplete(25);
		}
		catch(Exception e){
			waitForExtJSAjaxComplete(5);
		}

		//Select the BAD SLA WO and click SLA Information
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", woBadSla);

		selectActions("View SLA Information");

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(getWindowTitleCustomized("@class", "x-resizable-pinned").contains(SLA_WINDOW_HEADER), "SLA Information window is opened");

		softAssert.assertTrue(verifyMetricsHeaderInSLAInformation("WOSLAGrid", "Workorder Metrics"), "Workorder Metrics is Displayed in SLA Information Window");

		softAssert.assertTrue(verifyButtonsScheduler(PRINT_BTN_SLA_INFO_XPATH), "Print button is present in SLA Info Window");

		softAssert.assertTrue(verifyButtonsScheduler(EXPORT_BTN_SLA_INFO_XPATH), "Export To Excel button is present in SLA Info Window");

		waitForExtJSAjaxComplete(5);
		
		//WO Metric Grid Verification
		softAssert.assertTrue(verifyColNamesInSLAInfoWindow("WOSLAGrid", colNameArrBad), "Detailed SLA information is displayed in the WO Metrics grid");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(verifyAllSLAFieldsAreReadOnly("WOSLAGrid", "mcsgridrow_FF0000", colNameArrBad, woRow1ValueArrBad), "All the fields in WO Metrics Grid is Read only");
		
		waitForExtJSAjaxComplete(5);
		
		closeUsingToolBarJS(SLA_WINDOW_HEADER);
						
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertAll();
		
		Reporter.log("The planner can view the SLA Metrics for Workorders, that are NOT linked to a Call is successfully verified", true);
	}

	/**
	 * Test Case Id: C90313
	 * Author: MMA 
	 */

	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testToVerifyWorkResourceContentInInfoPanel() throws Exception {
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:Information panel visualization and content for the Work Resource: C90313" + " </span><br>", 
				true);

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testToVerifyWorkResourceContentInInfoPanel");

		String fromDate = "04-07-2016";
		String dateRangeFromTo = fromDate+" to 31-07-2016";
		String sche_Contact = "AQA SELENIUM";
		String sche_Contact1 = "AqaWebEmplDntDelete AqaWebEmplDntDelete";
		String labelNameCod = "Code:";
		String labelFirstName = "First name:";
		String labelLastName = "Last name:";
		String labelCompy = "Company:";
		String labelTel = "Tel:";
		String labelMbl = "Mobile:";
		String labelEmail = "E-mail:";
		String labelSkill = "Skills:";
		String labelGPS = "GPS position:";
		String serviceOrgREf = "AutoTestServOrgRef";

		waitForExtJSAjaxComplete(25);

		//Navigate to Administration and Enable Scheduler
		/*navigateToAdministrationAndEnableScheduler("Accounts", "Groups", "automation group", "Scheduler");
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);*/

		//First time it is not opening so closing and opening again
		//TODO : Scheduler loading Issue
		collapseNavigation();
		waitForExtJSAjaxComplete(5);
		/*clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);

		waitForMaskDisappear();

		closeModule("Scheduler");
		waitForExtJSAjaxComplete(25);*/

		//Navigate to Scheduler
		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);

		waitForMaskDisappear();
		waitForExtJSAjaxComplete(25);
		
		/*setServiceOrganization(serviceOrgREf, "Reference");
		waitForExtJSAjaxComplete(10);*/
		
		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		
		setCustomDateRange(dateRangeFromTo);
		waitForExtJSAjaxComplete(15);
		waitForMaskDisappear();
		
		disableWorkingHoursButton();
		waitForExtJSAjaxComplete(15);

		collapseResourceInTreeScheduler("servicegroup");
		waitForExtJSAjaxComplete(5);

		expandResourcesInTreeScheduler("servicegroup","AutoTestServGrp1Ref");

		//Work Resource 
		clickOnResourceTreeInScheduler("resource",sche_Contact);
		waitForExtJSAjaxComplete(5);
		String code = "1234";
		String firstName = "AQA";
		String lastName = "Scheduler";
		String company = "My Company";
		String tel = "9642713900";
		String mobile = "4899546";
		
		String eMail = "aqascheduler@mcs.fm";
		String skills = "Engineering";
		String skills1 = "Electronics";
		//TODO:In case in future if Longitude & latitude values are populated automatically we can change the code.
		String GPS = "13°N,10°E";

		clickOnInformationPanel("expand");
		waitForExtJSAjaxComplete(5);
		softAssert.assertTrue(getInformationPanelMode().equals("expanded"), "The Info panel is opened.");

		softAssert.assertEquals(getInfoPanelLabelValue(labelNameCod,"td"),code ,"relevant information for the last clicked object (Service group) site is shown");
		//TODO: there is no first name and last name format
		/*softAssert.assertEquals(getInfoPanelLabelValue(labelFirstName,"td"),firstName ,"relevant information for the last clicked object (Service group) site is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelLastName,"td"),lastName ,"relevant information for the last clicked object (Service group) site is shown");
		*/
		softAssert.assertEquals(getInfoPanelLabelValue(labelCompy,"td"),company ,"relevant information for the last clicked object (Service group) site is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelTel,"td"),tel ,"relevant information for the last clicked object (Service group) site is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelMbl,"td"),mobile ,"relevant information for the last clicked object (Service group) site is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelEmail,"td"),eMail ,"relevant information for the last clicked object (Service group) site is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelSkill,"td//ul/li[1]"),skills ,"relevant information for the last clicked object (Service group) site is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelSkill,"td//ul/li[2]"),skills1 ,"relevant information for the last clicked object (Service group) site is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelGPS,"td"),GPS ,"relevant information for the last clicked object (Service group) site is shown");

		//Work Resource1 
		clickOnResourceTreeInScheduler("resource",sche_Contact1);
		waitForExtJSAjaxComplete(5);

		String code1 = "12345";
		String firstName1 = "AqaWebEmplDntDelete";
		String lastName1 = "AqaWebEmplDntDelete";
		String company1 = "My Company";
		String tel1 = "65479221";
		String mobile1 = "8745294317";
		String eMail1 = "aqawebemp@mcs.fm";
		String GPS1 = "17°N,78°E";

		softAssert.assertTrue(getInformationPanelMode().equals("expanded"), "The Info panel is refreshed.");

		softAssert.assertEquals(getInfoPanelLabelValue(labelNameCod,"td"),code1 ,"relevant information for the last clicked object (Service group) site is shown");
		//TODO: there is no first name and last name format
		/*softAssert.assertEquals(getInfoPanelLabelValue(labelFirstName,"td"),firstName1 ,"relevant information for the last clicked object (Service group) site is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelLastName,"td"),lastName1 ,"relevant information for the last clicked object (Service group) site is shown");
		*/
		softAssert.assertEquals(getInfoPanelLabelValue(labelCompy,"td"),company1 ,"relevant information for the last clicked object (Service group) site is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelTel,"td"),tel1 ,"relevant information for the last clicked object (Service group) site is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelMbl,"td"),mobile1 ,"relevant information for the last clicked object (Service group) site is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelEmail,"td"),eMail1 ,"relevant information for the last clicked object (Service group) site is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelSkill,"td//ul/li"),skills ,"relevant information for the last clicked object (Service group) site is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelGPS,"td"),GPS1 ,"relevant information for the last clicked object (Service group) site is shown");

		String parentWindow =getCurrentWindowHandle();
		clickInfoPanelLabelValue(labelGPS,GPS1);
		waitForExtJSAjaxComplete(25);

		softAssert.assertEquals(switchAndGetChildWindow(parentWindow), "Google Maps","URL to google maps");
		waitForExtJSAjaxComplete(5);

		switchToWindow(parentWindow);

		softAssert.assertAll();
		Reporter.log("Information panel visualization and content for the Work Resource", true);

	}

	/**
	 * Test Case Id: C90315
	 * Author: MMA 
	 */

	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testToVerifyTaskContentInInfoPanel() throws Exception {
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:Information panel visualization and content for the Task: C90315" + " </span><br>", 
				true);

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testToVerifyTaskContentInInfoPanel");

		String fromDate = "04-08-2016";
		String dateRangeFromTo = fromDate+" to 31-08-2016";
		String sche_Contact = "Aqa_scheduler_new Scheduler";
		String sche_Contact1 = "AqaWebEmplDntDelete AqaWebEmplDntDelete";
		String labelSrtTime = "Start:";
		String labelEndTime = "End:";
		String labelDuration = "Duration:";
		String labelActTyp = "Activity type:";
		String labelID = "Work order:";
		String labelNature = "Nature:";
		String labelCustomer = "Customer:";
		String labelSite = "Site:";
		String labelPrj = "Project:";
		String labelCall = "Call:";
		String labelPrntWO = "Parent Work order:";
		String serviceOrgREf = "AutoTestServOrgRef";

		waitForExtJSAjaxComplete(25);

		//Navigate to Administration and Enable Scheduler
		/*navigateToAdministrationAndEnableScheduler("Accounts", "Groups", "automation group", "Scheduler");
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);*/

		//First time it is not opening so closing and opening again
		//TODO : Scheduler loading Issue
		collapseNavigation();
		waitForExtJSAjaxComplete(5);
		clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);

		// no loading issue, for the first time it self module is loading
		/*waitForMaskDisappear();

		closeModule("Scheduler");
		waitForExtJSAjaxComplete(25);

		//Navigate to Scheduler
		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);
		 */
		
		waitForExtJSAjaxComplete(25);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(25);

		/*setServiceOrganization(serviceOrgREf, "Reference");
		waitForExtJSAjaxComplete(10);
		*/
		
		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		
		setCustomDateRange(dateRangeFromTo);
		waitForExtJSAjaxComplete(15);
		waitForMaskDisappear();
		
		disableWorkingHoursButton();
		waitForExtJSAjaxComplete(10);

		collapseResourceInTreeScheduler("servicegroup");
		waitForExtJSAjaxComplete(5);

		expandResourcesInTreeScheduler("servicegroup","AutoTestServGrp1Ref");
		waitForExtJSAjaxComplete(5);

		//Task Force 1
		String sche_TaskFrcRef1 = "38 Task1WORelatedToPrj";
		WorkOrderPageObject woObj = new WorkOrderPageObject();
		
		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport","38", "Work Order ID");
		waitForExtJSAjaxComplete(10);

		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", "38");
		waitForExtJSAjaxComplete(5);

		selectActions("Edit");
		waitForExtJSAjaxComplete(25);

		WorkOrderPageObject woPobj = new WorkOrderPageObject();
		WorkOrderDetailTabsPageObject woDTPobj = new WorkOrderDetailTabsPageObject();

		woPobj.clickGeneralTab();
		waitForExtJSAjaxComplete(15);

		String woNature = woPobj.getNature();
		String woLocation = woPobj.getLocation();
		String woCustomer = woDTPobj.getCustomer(WORKORDER_WINDOW_DETAIL);
		String woProj = woDTPobj.getProject(WORKORDER_WINDOW_DETAIL);

		woDTPobj.closeXWindow();
		waitForExtJSAjaxComplete(5);

		clickOnScheduledTask(sche_Contact,sche_TaskFrcRef1);
		waitForExtJSAjaxComplete(5);

		clickOnInformationPanel("expand");
		waitForExtJSAjaxComplete(10);

		softAssert.assertEquals(getInformationPanelMode(),"expanded", "The Info panel is opened.");

		String taskRef = "Task1WORelatedToPrj";
		String startTime = "04-08-2016" +"\n"+ "07:00";
		String endTime = "04-08-2016" +"\n"+ "07:50";
		String duration = "0.83 man-hours";
		String actTyp = "Default";
		String id = "38";
		String status = "In Preparation";

		softAssert.assertEquals(getInfoPanelheaderRef("1"),taskRef ,"relevant information for the last clicked object (Task1) referece is shown");
		softAssert.assertEquals(getInfoPanelheaderRef("2"),status ,"relevant information for the last clicked object (Task1) status is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelSrtTime,"td"),startTime ,"relevant information for the last clicked object (Task1) starttime is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelEndTime,"td"),endTime ,"relevant information for the last clicked object (Task1) endtime is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelDuration,"td"),duration ,"relevant information for the last clicked object (Task1) duration is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelActTyp,"td"),actTyp ,"relevant information for the last clicked object (Task1) activitytype is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelID,"td"),id ,"relevant information for the last clicked object (Task1) WOID is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelNature,"td"),woNature ,"relevant information for the last clicked object (Task1) WONature is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelCustomer,"td"),woCustomer ,"relevant information for the last clicked object (Task1) WOCustomer is shown");
		softAssert.assertTrue(getInfoPanelLabelValue(labelSite,"td").contains(woLocation) ,"relevant information for the last clicked object (Task1) WOSite is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelPrj,"td"),woProj ,"relevant information for the last clicked object (Task1) LinkedProject is shown");

		String parentWindow =getCurrentWindowHandle();
		clickInfoPanelLabelValue(labelSite,"1aqaPreParSites");
		waitForExtJSAjaxComplete(15);

		String pageTitle = switchAndGetChildWindow(parentWindow).toString();
		switchToWindow(parentWindow);
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(pageTitle.contains("Google Maps"),"URL correctly redirects user to the set position on Google maps");
		waitForExtJSAjaxComplete(5);

		switchToWindow(parentWindow);
		waitForExtJSAjaxComplete(10);

		//Task Force 2
		String sche_TaskFrcRef2 = "Task2";

		clickOnScheduledTask(sche_Contact1,sche_TaskFrcRef2);
		waitForExtJSAjaxComplete(5);

		startTime = "04-08-2016" +"\n"+ "06:30";
		endTime = "04-08-2016" +"\n"+ "07:00";
		duration = "0.5 man-hours";
		actTyp="Default";

		softAssert.assertEquals(getInfoPanelheaderRef("1"),sche_TaskFrcRef2 ,"relevant information for the last clicked object (Task2) referece is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelSrtTime,"td"),startTime ,"relevant information for the last clicked object (Task2) startTime is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelEndTime,"td"),endTime ,"relevant information for the last clicked object (Task2) endTime is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelDuration,"td"),duration ,"relevant information for the last clicked object (Task2) duration is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelActTyp,"td"),actTyp ,"relevant information for the last clicked object (Task2) activitytype is shown");
		softAssert.assertEquals(getInformationPanelMode(),"expanded", "The Info panel is refreshed.");
		waitForExtJSAjaxComplete(5);

		//Task Force 3
		String sche_TaskFrcRef3 = "39 Task3WORelatedToCall";
		clickOnInformationPanel("collapse");
		waitForExtJSAjaxComplete(5);

		clickOnNavigationsButtons("next","following-sibling");
		waitForExtJSAjaxComplete(15);

		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport","39", "Work Order ID");
		waitForExtJSAjaxComplete(3);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", "39");
		waitForExtJSAjaxComplete(5);

		selectActions("Edit");
		waitForExtJSAjaxComplete(5);

		woPobj.clickGeneralTab();
		waitForExtJSAjaxComplete(15);

		woNature = woPobj.getNature();
		woLocation = woPobj.getLocation();
		woCustomer = woDTPobj.getCustomer(WORKORDER_WINDOW_DETAIL);
		String woCall = woDTPobj.getCall(WORKORDER_WINDOW_DETAIL);

		int woCallLngt = woCall.length();
		woCall = woCall.substring(4,woCallLngt).trim();

		woDTPobj.closeXWindow();
		waitForExtJSAjaxComplete(5);

		clickOnScheduledTask(sche_Contact1,sche_TaskFrcRef3);
		waitForExtJSAjaxComplete(5);

		taskRef = "Task3WORelatedToCall";
		status = "In Progress";
		startTime = "05-08-2016" +"\n"+ "08:00";
		endTime = "05-08-2016" +"\n"+ "09:00";
		duration = "1 man-hours";
		actTyp="Default";
		id = "39";
		clickOnInformationPanel("expand");
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getInfoPanelheaderRef("1"),taskRef ,"relevant information for the last clicked object (Task3) referece is shown");
		softAssert.assertEquals(getInfoPanelheaderRef("2"),status ,"relevant information for the last clicked object (Task3) status is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelSrtTime,"td"),startTime ,"relevant information for the last clicked object (Task3) startTime is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelEndTime,"td"),endTime ,"relevant information for the last clicked object (Task3) endTime is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelDuration,"td"),duration ,"relevant information for the last clicked object (Task3) duration is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelActTyp,"td"),actTyp ,"relevant information for the last clicked object (Task3) activitytype is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelID,"td"),id ,"relevant information for the last clicked object (Task3) WOID is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelNature,"td"),woNature ,"relevant information for the last clicked object (Task3) woNature is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelCustomer,"td"),woCustomer ,"relevant information for the last clicked object (Task3) woCustomer is shown");
		softAssert.assertTrue(getInfoPanelLabelValue(labelSite,"td").contains(woLocation) ,"relevant information for the last clicked object (Task3) woLocation is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelCall,"td"),woCall ,"relevant information for the last clicked object (Task3) CallID linked to WO is shown");

		//Task Force4
		String sche_TaskFrcRef4 = "33 Task4WORelatedToWO";
		clickOnInformationPanel("collapse");

		waitForExtJSAjaxComplete(5);

		clickOnNavigationsButtons("next","following-sibling");
		waitForExtJSAjaxComplete(15);

		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport","33", "Work Order ID");
		waitForExtJSAjaxComplete(3);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", "33");
		waitForExtJSAjaxComplete(5);

		selectActions("Edit");
		waitForExtJSAjaxComplete(5);


		woPobj.clickGeneralTab();
		waitForExtJSAjaxComplete(15);

		woNature = woPobj.getNature();
		woLocation = woPobj.getLocation();
		woCustomer = woDTPobj.getCustomer(WORKORDER_WINDOW_DETAIL);
		String parentWO = woDTPobj.getWorkorder(WORKORDER_WINDOW_DETAIL);

		int parentWOLngt = parentWO.length();
		parentWO = parentWO.substring(3,parentWOLngt).trim();
		
		woDTPobj.closeXWindow();
		waitForExtJSAjaxComplete(5);

		clickOnScheduledTask(sche_Contact1,sche_TaskFrcRef4);
		waitForExtJSAjaxComplete(5);

		taskRef = "Task4WORelatedToWO";
		status = "In Preparation";
		startTime = "06-08-2016" +"\n"+ "08:00";
		endTime = "06-08-2016" +"\n"+ "09:00";
		duration = "1 man-hours";
		actTyp="Default";
		id = "33";

		clickOnInformationPanel("expand");
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getInfoPanelheaderRef("1"),taskRef ,"relevant information for the last clicked object (Task4) referece is shown");
		softAssert.assertEquals(getInfoPanelheaderRef("2"),status ,"relevant information for the last clicked object (Task4) status is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelSrtTime,"td"),startTime ,"relevant information for the last clicked object (Task4) startTime is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelEndTime,"td"),endTime ,"relevant information for the last clicked object (Task4) endTime is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelDuration,"td"),duration ,"relevant information for the last clicked object (Task4) duration is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelActTyp,"td"),actTyp ,"relevant information for the last clicked object (Task4) activitytype is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelID,"td"),id ,"relevant information for the last clicked object (Task4) WOID is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelNature,"td"),woNature ,"relevant information for the last clicked object (Task4) woNature is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelCustomer,"td"),woCustomer ,"relevant information for the last clicked object (Task4) woCustomer is shown");
		softAssert.assertTrue(getInfoPanelLabelValue(labelSite,"td").contains(woLocation) ,"relevant information for the last clicked object (Task4) site is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelPrntWO,"td"),parentWO ,"relevant information for the last clicked object (Task4) parent WO ID  is shown");


		//Task Force 5
		String sche_TaskFrcRef5 = "42 Task5LinkedToWO";


		clickOnInformationPanel("collapse");
		waitForExtJSAjaxComplete(5);

		clickOnNavigationsButtons("previous","preceding-sibling");
		waitForExtJSAjaxComplete(15);

		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport","42", "Work Order ID");
		waitForExtJSAjaxComplete(3);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", "42");
		waitForExtJSAjaxComplete(5);

		selectActions("Edit");
		waitForExtJSAjaxComplete(5);


		woPobj.clickGeneralTab();
		waitForExtJSAjaxComplete(15);

		woNature = woPobj.getNature();
		woLocation = woPobj.getLocation();
		woCustomer = woDTPobj.getCustomer(WORKORDER_WINDOW_DETAIL);

		woDTPobj.closeXWindow();
		waitForExtJSAjaxComplete(5);

		clickOnScheduledTask(sche_Contact,sche_TaskFrcRef5);
		waitForExtJSAjaxComplete(5);

		taskRef = "Task5LinkedToWO";
		status = "In Progress";
		startTime = "05-08-2016" +"\n"+ "07:00";
		endTime = "05-08-2016" +"\n"+ "08:00";
		duration = "1 man-hours";
		actTyp="Default";
		id = "42";

		clickOnInformationPanel("expand");
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getInfoPanelheaderRef("1"),taskRef ,"relevant information for the last clicked object (Task5) referece is shown");
		softAssert.assertEquals(getInfoPanelheaderRef("2"),status ,"relevant information for the last clicked object (Task5) status is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelSrtTime,"td"),startTime ,"relevant information for the last clicked object (Task5) startTime is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelEndTime,"td"),endTime ,"relevant information for the last clicked object (Task5) endTime is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelDuration,"td"),duration ,"relevant information for the last clicked object (Task5) duration is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelActTyp,"td"),actTyp ,"relevant information for the last clicked object (Task5) activitytype is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelID,"td"),id ,"relevant information for the last clicked object (Task5) WOID is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelNature,"td"),woNature ,"relevant information for the last clicked object (Task5) woNature is shown");
		softAssert.assertEquals(getInfoPanelLabelValue(labelCustomer,"td"),woCustomer ,"relevant information for the last clicked object (Task5) woCustomer is shown");
		softAssert.assertTrue(getInfoPanelLabelValue(labelSite,"td").contains(woLocation) ,"relevant information for the last clicked object (Task5) site is shown");

		softAssert.assertAll();
		Reporter.log("Information panel visualization and content for the Task", true);
	}	

	/**
	 * Test Case ID: C92785 & C82430
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testUserAddsZoomLevelAndPredefinedCustomDateRanges() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: User is able to Add a Zoom Level : C92785" + " </span><br>", 
				true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Predefined date ranges : C82430" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testUserAddsZoomLevelAndPredefinedCustomDateRanges");
		
		waitForExtJSAjaxComplete(25);
		
		//TODO: Zoom levels test case in not applicable for trunk, since Zoom levels section not available
/*		clickAdministration();
		
		waitForExtJSAjaxComplete(25);
		
		expandModuleSettings();
		
		waitForExtJSAjaxComplete(25);
		
		clickAdministrationOptions("Scheduler", "Option Profiles Settings");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(verifyOrderOfSectionsInOptionProfilesSettingsWin(1, "Profile"), "Profile is the Available First");
		
		//TODO: Zoom levels test case in not applicable for trunk, since Zoom levels section not available
		//softAssert.assertTrue(verifyOrderOfSectionsInOptionProfilesSettingsWin(2, "Zoom levels"), "Zoom levels is the Available In between Profile and Task Label");
		
		softAssert.assertTrue(verifyOrderOfSectionsInOptionProfilesSettingsWin(3, "Task label"), "Task Label is the Available After Zoom levels");
		
		softAssert.assertTrue(verifyZoomLevelButtonsWin("Add"), "Add Button is displayed in Zoom levels with Proper Icon");
		
		softAssert.assertTrue(verifyZoomLevelButtonsWin("Edit"), "Edit Button is displayed in Zoom levels with Proper Icon");
		
		softAssert.assertTrue(verifyZoomLevelButtonsWin("Delete"), "Delete Button is displayed in Zoom levels with Proper Icon");
		
		waitForExtJSAjaxComplete(5);
		
		//clickButtonInZoomLevelWin("Add");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(verifyFieldsNatureInZoomLevelWin("value"), "Value Field in Zoo level is verified");
		
		softAssert.assertTrue(verifyFieldsNatureInZoomLevelWin("uom"), "UOM Field in Zoo level is verified");
		
		softAssert.assertTrue(verifyFieldsNatureInZoomLevelWin("duration"), "Duration Field in Zoo level is verified");
		
		softAssert.assertTrue(verifyFieldsNatureInZoomLevelWin("minTaskVizualization"), "Minimum Task Block Visualisation Field in Zoo level is verified");
		
		softAssert.assertTrue(verifyFieldsNatureInZoomLevelWin("durationGranularity"), "Duration Granularity Field in Zoo level is verified");
		
		waitForExtJSAjaxComplete(5);
		
		String uomArr[] = {"Hour", "Day", "Week", "Month"};
		
		List<String> uomReferencesList = verifyDropDownValuesInZoomLevelWin("uom");
		
		softAssert.assertEqualsNoOrder(uomReferencesList.toArray(), uomArr , "UOM List has all 4 values");
		
		waitForExtJSAjaxComplete(5);
		
		clickDropDownZoomLevelWin("uom");
		
		waitForExtJSAjaxComplete(3);
		
		String maxValue = "13";
		
		//Verify Tooltip for Hour
		setValueOfZoomLevelWin(maxValue);
		
		setDropDownInZoomLevelsWin("uom", "Hour");
		
		softAssert.assertTrue(verifyToolTipOfMsgUOMWin(maxValue).contains("The maximum value for this field is 12"), "The maximum value for this field is 12 is verified");
		
		waitForExtJSAjaxComplete(5);
		
		//Verify Tooltip for Day
		setDropDownInZoomLevelsWin("uom", "Day");
		
		setValueOfZoomLevelWin(maxValue);
		
		softAssert.assertTrue(verifyToolTipOfMsgUOMWin(maxValue).contains("The maximum value for this field is 3"), "The maximum value for this field is 3 is verified");
		
		waitForExtJSAjaxComplete(5);
		
		//Verify Tooltip for Week
		setDropDownInZoomLevelsWin("uom", "Week");
		
		setValueOfZoomLevelWin(maxValue);
		
		softAssert.assertTrue(verifyToolTipOfMsgUOMWin(maxValue).contains("The maximum value for this field is 4"), "The maximum value for this field is 4 is verified");
		
		waitForExtJSAjaxComplete(5);
		
		//Verify Tooltip for Month
		setDropDownInZoomLevelsWin("uom", "Month");
		
		setValueOfZoomLevelWin(maxValue);
		
		softAssert.assertTrue(verifyToolTipOfMsgUOMWin(maxValue).contains("The maximum value for this field is 1"), "The maximum value for this field is 1 is verified");
		
		waitForExtJSAjaxComplete(5);
		
		//Select zoom level 1 day (UOM - day, Value - 1) -> Save.
		setValueOfZoomLevelWin("1");
		
		setDropDownInZoomLevelsWin("uom", "Day");
		
		clickButton(ZOOM_LEVEL_WINDOW_HEADER, "Save and Close");
		
		waitForExtJSAjaxComplete(3);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(ZOOM_LEVEL_WINDOW_HEADER)), "There are errors in this dialog box. Please correct them and retry.", "There are errors in this dialog box. Please correct them and retry.");
		
		waitForExtJSAjaxComplete(3);
		
		//Select zoom level 1 week (UOM - week, Value - 1) -> Save.
		setValueOfZoomLevelWin("1");
		
		setDropDownInZoomLevelsWin("uom", "Week");
		
		clickButton(ZOOM_LEVEL_WINDOW_HEADER, "Save and Close");
		
		waitForExtJSAjaxComplete(3);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(ZOOM_LEVEL_WINDOW_HEADER)), "There are errors in this dialog box. Please correct them and retry.", "There are errors in this dialog box. Please correct them and retry.");
		
		waitForExtJSAjaxComplete(3);
		
		//Default Duration value is verified
		String durationArr[] = {"00:05", "00:10", "00:15", "00:20", "00:30", "01:00", "02:00", "04:00", "08:00"};
		
		List<String> actualDurationArr = verifyDropDownValuesInZoomLevelWin("duration");
		
		softAssert.assertEqualsNoOrder(actualDurationArr.toArray(), durationArr , "Duration List has all 9 values");
		
		waitForExtJSAjaxComplete(5);
		
		clickDropDownZoomLevelWin("duration");
		
		waitForExtJSAjaxComplete(5);
		
		//Minimal Task block visualization:*
		actualDurationArr = verifyDropDownValuesInZoomLevelWin("minTaskVizualization");
		
		softAssert.assertEqualsNoOrder(actualDurationArr.toArray(), durationArr , "Task Visualization List has all 9 values");
		
		waitForExtJSAjaxComplete(5);
		
		clickDropDownZoomLevelWin("minTaskVizualization");
		
		waitForExtJSAjaxComplete(5);
		
		//Duration granularity:* dropdown list
		actualDurationArr = verifyDropDownValuesInZoomLevelWin("durationGranularity");
		
		softAssert.assertEqualsNoOrder(actualDurationArr.toArray(), durationArr , "Duration Granularity List has all 9 values");
		
		waitForExtJSAjaxComplete(5);
		
		clickDropDownZoomLevelWin("durationGranularity");
		
		waitForExtJSAjaxComplete(5);
		
		clickButton(ZOOM_LEVEL_WINDOW_HEADER, "Close");
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(3);
		
		clickButtonInZoomLevelWin("Add");
		
		waitForExtJSAjaxComplete(5);
		
		//Fill in all mandatory fields
		setValueOfZoomLevelWin("11");
		
		setDropDownInZoomLevelsWin("uom", "Hour");
		
		setDropDownInZoomLevelsUsingDropDownImgWin("duration", "01:00");
		
		setDropDownInZoomLevelsUsingDropDownImgWin("minTaskVizualization", "00:15");
		
		setDropDownInZoomLevelsUsingDropDownImgWin("durationGranularity", "00:15");
		
		clickButton(ZOOM_LEVEL_WINDOW_HEADER, "Save and Close");
		
		waitForExtJSAjaxComplete(3);
		
		softAssert.assertTrue(verifyColumnsInTaskLabelGrid(ZOOM_LEVELS_XPATH, "Hour"), "Hour Column is available in the Zoom Levels Grid");
		
		Reporter.log("New Zoom level is added successfully.", true);
		
		Reporter.log("User is able to Add a Zoom Level is successfully verified", true);
	
		waitForExtJSAjaxComplete(5);
		
		closeModule("myMCS Administration");
		
		waitForExtJSAjaxComplete(10);
		*/
		// Predefined date ranges : C82430 starts*******//////
		softAssert.setMethodName("testPredefinedDateRanges");
		
		String userName = "aqa_selenium";
		String password = "qwerty";
		navigateToMainPage(userName,password); 
		waitForExtJSAjaxComplete(25);
		
		String serviceOrgREf = "AutoTestServOrgRef";
		String today = "Today";
		String nxtWrkDay = "Next Working Day";
		String currWeek = "Current Week";
		String nxtWeek = "Next Week";
		String nxt7Days = "Next 7 Days";
		String nxt14Days = "Next 14 Days";
		String custmRange = "Custom Range";
		
		waitForExtJSAjaxComplete(25);
		
		//First time it is not opening so closing and opening again
		//TODO : Scheduler loading Issue
		collapseNavigation();
		/*clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);
		
		waitForMaskDisappear();
		
		closeModule("Scheduler");
		
		waitForExtJSAjaxComplete(25);
		*/
		//Navigate to Scheduler
		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(25);
		
		/*setServiceOrganization(serviceOrgREf, "Reference");
		
		waitForExtJSAjaxComplete(25);*/
		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(15);

		try{
			clickOnInformationPanel("collapse");
			waitForExtJSAjaxComplete(10);
		}
		catch(Exception e){
			waitForExtJSAjaxComplete(10);
		}
		//Verify the default value for the 'Date range' field
		String defaultDate = getCustomDateRange();
		System.err.println("Default Date Range "+defaultDate);
		
		String expectedDefaultDate = getFutureDate(0)+" to "+getFutureDate(6);
		System.err.println(expectedDefaultDate);
		
		softAssert.assertEquals(defaultDate, expectedDefaultDate, "Default value is 'Next 7 days': current day and next 6 days");
		
		waitForExtJSAjaxComplete(3);	
		
		clickCalendarIcon();
		
		waitForExtJSAjaxComplete(25);
		
		String calendarArr[] = {"Today", "Next Working Day", "Current Week", "Next Week", "Next 7 Days", "Next 14 Days", "Custom Range"};
		
		List<String> calendarOptions = verifyCalendarRangeOptions();
		
		softAssert.assertEqualsNoOrder(calendarOptions.toArray(), calendarArr , "Calendar Option has all 7 values");
		
		waitForExtJSAjaxComplete(5);
 	
		selectRefreshButton();
		
		waitForExtJSAjaxComplete(5);
		
		//Select 'Today'
		clickCalendarIcon();
		
		selectOptionsCalendar(today);
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertEquals(getCustomDateRange(), getFutureDate(0), "The Date range is today");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		//Select  'Next working day'
		clickCalendarIcon();
		
		selectOptionsCalendar(nxtWrkDay);
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertEquals(getCustomDateRange(), getFutureDate(1), "The Date range is Next Working Day");
		waitForMaskDisappear();
		//Select  Current week
		clickCalendarIcon();
		
		selectOptionsCalendar(currWeek);
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertEquals(getCustomDateRange(), getCurrentWeek(), "The Date range is Current Week");
		waitForMaskDisappear();
		//select 'Next week'
		clickCalendarIcon();
		
		selectOptionsCalendar(nxtWeek);
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertEquals(getCustomDateRange(), getNextWeek(), "The Date range is Next Week");
		waitForMaskDisappear();
		//select 'Next 7 days'
		clickCalendarIcon();
		
		selectOptionsCalendar(nxt7Days);
		
		waitForExtJSAjaxComplete(5);
		
		String expctdNxt7Days = getFutureDate(0)+" to "+getFutureDate(6);
		
		softAssert.assertEquals(getCustomDateRange(), expctdNxt7Days, "The Date range is Next 7 days");
		waitForMaskDisappear();
		//select 'Next 14 days'
		clickCalendarIcon();
		
		
		selectOptionsCalendar(nxt14Days);
		
		waitForExtJSAjaxComplete(5);
		
		String expctdNxt14Days = getFutureDate(0)+" to "+getFutureDate(13);
		
		softAssert.assertEquals(getCustomDateRange(), expctdNxt14Days, "The Date range is Next 7 days");
		
		navigateToMainPage(configuration.getUserName(),configuration.getPassword()); 
		waitForExtJSAjaxComplete(15);

		softAssert.assertAll();
		
		Reporter.log("Predefined date ranges is successfully verified in scheduler", true);
	}
	
	/**
	 * Test Case ID: C92787, C92786& C92224
	 * Author : MMA
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testUserEditAndDeleteZoomLevel() throws Exception {
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:User is able to Edit and Delete a Zoom level: C92787, C92786 & C92224" + " </span><br>", 
				true);

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testUserEditAndDeleteZoomLevel");

		String initialValue = "1";
		String initialUOM = "Hour";
		String initialDuration = "01:00";
		String initialTskBlkVis = "00:30";
		String initailgranularity = "00:15";
		String editValue = "1.6";
		String editUOM = "Day";
		String editDuration = "01:00";
		String editTskBlkVis = "00:15";
		String editgranularity = "00:15";
		String workRes1 = "AQA SELENIUM";
		String workRes2 = "AqaWebEmplDntDelete AqaWebEmplDntDelete";
		String workRes3 = "Aqa_scheduler_new Scheduler";
		String schedulerTask = "Task4WORelatedToWO";
		String fromDate = "06-08-2016";
		String dateRangeFromTo = fromDate+" to 31-08-2016";
		String woID = "33";
		String serviceOrgREf = "AutoTestServOrgRef";

		waitForExtJSAjaxComplete(25);
		//TODO: Zoom levels test case in not applicable for trunk, since Zoom levels section not available

		/*		clickAdministration();

		waitForExtJSAjaxComplete(25);

		expandModuleSettings();

		waitForExtJSAjaxComplete(25);

		clickAdministrationOptions("Scheduler", "Option Profiles Settings");

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(verifyZoomLevelButtonStatus("Edit"),"Edit button is in disabled state	and not clickable");
		softAssert.assertTrue(verifyZoomLevelButtonStatus("Delete"),"Edit button is in disabled state	and not clickable");
		clickValuesInZoomLevelWin(initialValue);

		//C92786
		clickButtonInZoomLevelWin("Edit");
		waitForExtJSAjaxComplete(3);
		softAssert.assertTrue(getZoomLevelWinStatus("Edit zoom level"),"Zoom level window is opened");

		//Edit all mandatory fields
		setValueOfZoomLevelWin(editValue);

		setDropDownInZoomLevelsWin("uom",editUOM);

		setDropDownInZoomLevelsUsingDropDownImgWin("duration",editDuration);

		setDropDownInZoomLevelsUsingDropDownImgWin("minTaskVizualization",editTskBlkVis);

		setDropDownInZoomLevelsUsingDropDownImgWin("durationGranularity",editgranularity);

		waitForExtJSAjaxComplete(3);

		clickButton("Edit zoom level", "Close");

		waitForExtJSAjaxComplete(3);

		softAssert.assertEquals(getWarningMessage(),"All unsaved changes will be lost! Are you sure you want to continue?","warning message appears.");

		waitForExtJSAjaxComplete(3);

		clickDialogueBoxButtons("Yes");

		waitForExtJSAjaxComplete(3);

		softAssert.assertFalse(getZoomLevelWinStatus("Edit zoom level"),"Zoom level window is closed");

		waitForExtJSAjaxComplete(3);

		clickValuesInZoomLevelWin(initialValue);

		clickButtonInZoomLevelWin("Edit");
		waitForExtJSAjaxComplete(3);

		softAssert.assertEquals(getValueOfZoomLevelWin("value"),initialValue,"changes are not saved");
		softAssert.assertEquals(getValueOfZoomLevelWin("uom"),initialUOM,"changes are not saved");
		softAssert.assertEquals(getValueOfZoomLevelWin("duration"),initialDuration,"changes are not saved");
		softAssert.assertEquals(getValueOfZoomLevelWin("minTaskVizualization"),initialTskBlkVis,"changes are not saved");
		softAssert.assertEquals(getValueOfZoomLevelWin("durationGranularity"),initailgranularity,"changes are not saved");

		//Edit all mandatory fields
		setValueOfZoomLevelWin(editValue);

		setDropDownInZoomLevelsWin("uom",editUOM);

		setDropDownInZoomLevelsUsingDropDownImgWin("duration",editDuration);

		setDropDownInZoomLevelsUsingDropDownImgWin("minTaskVizualization",editTskBlkVis);

		setDropDownInZoomLevelsUsingDropDownImgWin("durationGranularity",editgranularity);

		clickButton("Edit zoom level", "Save and Close");

		waitForExtJSAjaxComplete(3);

		softAssert.assertFalse(getZoomLevelWinStatus("Edit zoom level"),"Zoom level window is closed and changes are saved.");

		clickValuesInZoomLevelWin(editValue);

		clickButtonInZoomLevelWin("Edit");
		waitForExtJSAjaxComplete(3);

		softAssert.assertEquals(getValueOfZoomLevelWin("value"),editValue,"Changes are correctly saved");
		softAssert.assertEquals(getValueOfZoomLevelWin("uom"),editUOM,"Changes are correctly saved");
		softAssert.assertEquals(getValueOfZoomLevelWin("duration"),editDuration,"Changes are correctly saved");
		softAssert.assertEquals(getValueOfZoomLevelWin("minTaskVizualization"),editTskBlkVis,"Changes are correctly saved");
		softAssert.assertEquals(getValueOfZoomLevelWin("durationGranularity"),editgranularity,"Changes are correctly saved");

		clickButton("Edit zoom level", "Close");
		waitForExtJSAjaxComplete(3);

		//C92787
		clickValuesInZoomLevelWin(editValue);
		clickButtonInZoomLevelWin("Delete");
		waitForExtJSAjaxComplete(3);

		softAssert.assertEquals(getWarningMessage(),"Do you want to delete Zoom Level?","warning message appears.");
		clickDialogueBoxButtons("No");
		softAssert.assertFalse(getZoomLevelWinStatus("Delete zoom level"),"Zoom level window is closed.");
		waitForExtJSAjaxComplete(3);

		softAssert.assertTrue(verifyColumnsInTaskLabelGrid(ZOOM_LEVELS_XPATH,editValue),"Zoom level_1 is in the grid.");
		clickValuesInZoomLevelWin(editValue);
		clickButtonInZoomLevelWin("Delete");
		waitForExtJSAjaxComplete(3);

		clickDialogueBoxButtons("Yes");
		waitForExtJSAjaxComplete(3);

		softAssert.assertFalse(verifyColumnsInTaskLabelGrid(ZOOM_LEVELS_XPATH,editValue),"Zoom level_1 is deleted from the grid");

		closeModule("myMCS Administration");
		waitForExtJSAjaxComplete(5);
		*/
		//C92224
		//Navigate to Administration and Enable Scheduler
		/*navigateToAdministrationAndEnableScheduler("Accounts", "Groups", "automation group", "Scheduler");
		waitForExtJSAjaxComplete(25);*/
		waitForExtJSAjaxComplete(15);

		//First time it is not opening so closing and opening again
		//TODO : Scheduler loading Issue
		collapseNavigation();
		waitForExtJSAjaxComplete(5);
		/*clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);

		waitForMaskDisappear();

		closeModule("Scheduler");
		waitForExtJSAjaxComplete(25);*/

		//Navigate to Scheduler
		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);

		waitForMaskDisappear();
		waitForExtJSAjaxComplete(25);


		/*setServiceOrganization(serviceOrgREf, "Reference");
		waitForExtJSAjaxComplete(10);
*/
		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		
		setCustomDateRange(dateRangeFromTo);
		waitForExtJSAjaxComplete(15);

		disableWorkingHoursButton();
		waitForExtJSAjaxComplete(5);

		//Step3 is verified as a part of C83194(testToSwitchToThePreviousOrNextDayOfDateRange)
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, woID, "@class", "x-panel scheduler"), "WorkOrder is available in the grid");

		collapseResourceInTreeScheduler("servicegroup");
		waitForExtJSAjaxComplete(5);

		expandResourcesInTreeScheduler("servicegroup","AutoTestServGrp1Ref");

		softAssert.assertTrue( isTaskPresent(workRes1,schedulerTask),"The schedulerTask is shown in "+workRes1+" member");
		softAssert.assertTrue( isTaskPresent(workRes2,schedulerTask),"The schedulerTask is shown in "+workRes2+" member");
		softAssert.assertTrue( isTaskPresent(workRes3,schedulerTask),"The schedulerTask is shown in "+workRes3+" member");

		softAssert.assertAll();

		Reporter.log("User is able to Edit and Delete a Zoom level", true);		
	}

	/**
	 * Test Case ID : C94997
	 * Author : MMA
	 */
	@Test(enabled=true)
	public void testRecommendedSkillMatchingValidation() throws Exception {
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:Recommended skill matching validation: C94997" + " </span><br>", 
				true);

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testRecommendedSkillMatchingValidation");

		String fromDate = "25-07-2016";
		String dateRangeFromTo = fromDate+" to 31-07-2016";
		String woRef = "SchedulerRelated";
		String contact = "AqaWebEmplDntDelete";
		String skill = "Engineering";
		String contact1 = "Aqa_scheduler_new";
		String serviceOrgREf = "AutoTestServOrgRef";

		waitForExtJSAjaxComplete(25);
		
		//First time it is not opening so closing and opening again
		//TODO : Scheduler loading Issue
		collapseNavigation();
		waitForExtJSAjaxComplete(5);

		/*clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);

		waitForMaskDisappear();

		closeModule("Scheduler");
		waitForExtJSAjaxComplete(25);*/

		//Navigate to Scheduler
		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);
		waitForExtJSAjaxComplete(15);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(15);

		/*setServiceOrganization(serviceOrgREf, "Reference");
		waitForExtJSAjaxComplete(10);
		*/
		
		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(10);
		
		try{
			clickOnInformationPanel("collapse");
			waitForExtJSAjaxComplete(10);
		}
		catch(Exception e){
			waitForExtJSAjaxComplete(10);
		}

		setCustomDateRange(dateRangeFromTo);
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(10);
		
		WorkOrderPageObject woObj = new WorkOrderPageObject();
		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", woRef, "Reference");
		waitForExtJSAjaxComplete(10);

		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", woRef);
		//clickScheduleTask();
		waitForExtJSAjaxComplete(10);

		//TODO : unable to verify appropriate icon i.e related class name is verified 
		softAssert.assertTrue(mouseHoverResourceAndVerifySkill(contact,skill),"Recommended Skill "+skill+" is present");
		waitForExtJSAjaxComplete(3);
		softAssert.assertFalse(mouseHoverResourceAndVerifySkill(contact1,skill),"Recommended Skill "+skill+" is missing");
		waitForExtJSAjaxComplete(3);

		createTaskByDraggingOnTimeLine(contact,"8","9");
		waitForExtJSAjaxComplete(15);
		waitForExtJSAjaxComplete(15);
		
		setWOInAddTaskWindow(woRef);
		waitForExtJSAjaxComplete(5);
		
		saveAndClose("Add Task");
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(isTaskPresent(contact,"SchedulerRelated"),"Task is Created successfully");
		waitForExtJSAjaxComplete(3);

		closeModule("Scheduler");
		waitForExtJSAjaxComplete(3);

		//Navigate to Scheduler
		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);
		waitForExtJSAjaxComplete(5);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(15);
		
		/*setServiceOrganization(serviceOrgREf, "Reference");
		waitForExtJSAjaxComplete(10);
		*/
		setCustomDateRange(dateRangeFromTo);
		waitForExtJSAjaxComplete(15);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(15);
		
		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", woRef, "Reference");
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", woRef);
		//clickScheduleTask();
		waitForExtJSAjaxComplete(5);

		createTaskByDraggingOnTimeLine(contact1,"1","2");
		waitForExtJSAjaxComplete(15);
		waitForExtJSAjaxComplete(15);

		setWOInAddTaskWindow(woRef);
		waitForExtJSAjaxComplete(5);

		saveAndClose("Add Task");
		waitForExtJSAjaxComplete(15);

		softAssert.assertTrue(getWarningMsgOfSkillMatchingWin().contains("This Resource does not possess all required Skills for the execution of the Workorder. Do you want to continue anyway?"),"Warning message appears");
		waitForExtJSAjaxComplete(3);

		clickButtonsInSkillMatchingWin("Yes");
		waitForExtJSAjaxComplete(25);
		waitForMaskDisappear();
		
		softAssert.assertTrue(isTaskPresent(contact1,"SchedulerRelated"),"Task is Created");
		waitForExtJSAjaxComplete(3);

		closeModule("Scheduler");
		waitForExtJSAjaxComplete(3);

		//Navigate to Scheduler
		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);
		waitForExtJSAjaxComplete(5);

		/*setServiceOrganization(serviceOrgREf, "Reference");
		waitForExtJSAjaxComplete(10);*/
		
		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		
		setCustomDateRange(dateRangeFromTo);
		waitForExtJSAjaxComplete(5);

		clickOnNavigationsButtons("next","following-sibling");
		waitForExtJSAjaxComplete(3);

		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", woRef, "Reference");
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", woRef);
		//clickScheduleTask();
		waitForExtJSAjaxComplete(5);

		createTaskByDraggingOnTimeLine(contact1,"1","2");
		waitForExtJSAjaxComplete(15);
		waitForExtJSAjaxComplete(15);
		
		setWOInAddTaskWindow(woRef);
		waitForExtJSAjaxComplete(5);
		
		saveAndClose("Add Task");
		waitForExtJSAjaxComplete(15);
		
		clickButtonsInSkillMatchingWin("No");
		waitForExtJSAjaxComplete(3);

		softAssert.assertFalse(isTaskPresent(contact1,"SchedulerRelated"),"Task is not Created");

		softAssert.assertAll();

		Reporter.log("Recommended skill matching verified", true);
	}	

	/**
	 * Test Case ID: C82423
	 * Author : SSU
	 */
	@Test(enabled=false, retryAnalyzer=RetryAnalyzer.class)
	public void testUserCanChangeServiceOrganizationFilter() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: User can choose the 'Service organization' filter criteria : C82423" + " </span><br>", 
				true);
			
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testUserCanChangeServiceOrganizationFilter");
		
		String woRef = "TestPlanningSection";
		String wo2Ref = "AQAWoType";
		String wo3Ref = "SchedulerRelated";
		
		String servOrg1 = "SO1Ref";
		String servOrg2 = "SO2WithoutSiteRef";
		String serviceOrgREf = "AutoTestServOrgRef";
		
		String serviceGrp = "AutoTestServGrp1Ref";
		String serviceGrpNotAvl = "AutoTestServGrpRef";
		String taskForce = "AutoTestTaskFrc1Ref";
		
		String resource1 = "AQA SELENIUM";
		String resource2 = "AqaWebEmplDntDelete";
		String resource3 = "Aqa_scheduler_new Scheduler";
		
		waitForExtJSAjaxComplete(25);
		
		clickAdministration();

		waitForExtJSAjaxComplete(5);

		expandMasterData();

		clickAdministrationOptions("Master Data","Service Organizations");

		expandParentNodeInTreeByTextValue(servOrg1);
		waitForExtJSAjaxComplete(5);
		
		expandChildNodeInTreeByTextValue("Sites");
		waitForExtJSAjaxComplete(5);
		
		String siteAdministration = getSiteFromServOrganization(servOrg1);
		siteAdministration = siteAdministration.substring(0, siteAdministration.indexOf(" "));
		waitForExtJSAjaxComplete(3);
		
		closeModule("Administration");
		waitForExtJSAjaxComplete(3);

		WorkOrderPageObject woObj = new WorkOrderPageObject();

		waitAndClick(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(15);
		waitForExtJSAjaxComplete(15);

		woObj.clickFilterResults();
		waitForExtJSAjaxComplete(10);

		//Finished
		woObj.expandMaintenanceAndSelectComplianceValueInFilterResults("x-window x-resizable-pinned", "Add restriction", "General Properties","Status Class");
		waitForExtJSAjaxComplete(10);

		setValueGridLookupWithFilters("Finished","Name");
		waitForExtJSAjaxComplete(5);

		woObj. selectComplianceRestriction("x-window x-resizable-pinned","Status Class = Finished");
		waitForExtJSAjaxComplete(5);

		woObj.clickAnyButtonInFilterResults("x-window x-resizable-pinned","NOT");
		waitForExtJSAjaxComplete(5);

		//Archived
		woObj.expandMaintenanceAndSelectComplianceValueInFilterResults("x-window x-resizable-pinned", "Add restriction", "General Properties","Status Class");
		waitForExtJSAjaxComplete(10);

		setValueGridLookupWithFilters("Archived","Name");
		waitForExtJSAjaxComplete(5);

		woObj. selectComplianceRestriction("x-window x-resizable-pinned","Status Class = Archived");
		waitForExtJSAjaxComplete(5);

		woObj.clickAnyButtonInFilterResults("x-window x-resizable-pinned","NOT");
		waitForExtJSAjaxComplete(5);
		clickSaveFilterInFilterWindow();
		waitForExtJSAjaxComplete(10);

		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport",siteAdministration, "Site");
		waitForExtJSAjaxComplete(25);

		int woCount = getGridSize();
		
		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport","1aqaPreParSites", "Site");
		waitForExtJSAjaxComplete(25);
		int woCount1 = getGridSize();
		
		closeModule("Work Order");
		waitForExtJSAjaxComplete(5);
		
		//First time it is not opening so closing and opening again
		collapseNavigation();
		waitForExtJSAjaxComplete(5);
		//TODO : Scheduler loading Issue
		/*clickModuleInStartPage(scheduler_StartMenu_Xpath);
		//clickXPath(XPATH_SCHEDULER);

		waitForMaskDisappear();

		closeModule("Scheduler");

		waitForExtJSAjaxComplete(25);*/

		//Navigate to Scheduler
		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath);
		//clickXPath(XPATH_SCHEDULER);

		waitForMaskDisappear();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);
		
		//Do not choose any filter value
		//TODO: This functionality is not present in portal trunk
		//softAssert.assertTrue(isGridEmpty("No items available"), "No items available is displayed");
		
		softAssert.assertTrue(isResourceGridEmpty("Empty"), "Resource Grid is Empty");
		
		softAssert.assertTrue(isTaskGridEmpty("Empty"), "Task Grid is also empty");
		
		waitForExtJSAjaxComplete(3);
		
		//select SO1		
		/*setServiceOrganization(servOrg1, "Reference");
		
		waitForExtJSAjaxComplete(10);*/
		
		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		
		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", siteAdministration, "Site");
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getSchedulerGridSize(), woCount, "Two WO that meets the SO1 Searching criteria is displayed");
		
		waitForExtJSAjaxComplete(5);
		
		//Work Orders, with correct combination of the service organization and 'Planned Start Date' and 'Planned End Date' 
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", woRef);
		
		waitForExtJSAjaxComplete(5);
		
		selectActions("Edit");
		
		waitForExtJSAjaxComplete(5);
		
		WorkOrderPageObject woPobj = new WorkOrderPageObject();
		
		woPobj.clickGeneralTab();
		
		waitForExtJSAjaxComplete(5);
		
		String siteRef = woPobj.getLocation();
		
		softAssert.assertTrue(siteAdministration.contains(siteRef), "Service Organization (SO1) with linked Site is correclty displayed in WO ");
		
		waitForExtJSAjaxComplete(3);
		
		String windowID = woPobj.getWindowIdByClass("x-window x-resizable-pinned");
		
		woPobj.clickOnTab(windowID, "Tracking");
		
		waitForExtJSAjaxComplete(5);
		
		woPobj.clickOnTab(windowID, "Planning");
		
		waitForExtJSAjaxComplete(5);
		
		String plannedStrtDt = woPobj.getPlannedStartDate(WORKORDER_WINDOW_DETAIL);
		String plannedEndDt = woPobj.getPlannedEndDate(WORKORDER_WINDOW_DETAIL);
		
		String frmDt = "01-02-2015";
		String toDt = "31-12-2015";
		
		softAssert.assertTrue(compareDate(frmDt, plannedStrtDt, toDt, plannedEndDt), "Start-End Dates period is in range of the selected Date Range");
		waitForExtJSAjaxComplete(3);
		
		woPobj.closeUsingToolBar(WORKORDER_WINDOW_XPATH);
		waitForExtJSAjaxComplete(3);
		
		//Work Order linked to the selected organization and without 'Planned Start Date' and 'Planned End Date' are shown
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", wo2Ref);
		
		waitForExtJSAjaxComplete(5);
		
		selectActions("Edit");
		
		waitForExtJSAjaxComplete(5);
		
		woPobj.clickGeneralTab();
		
		waitForExtJSAjaxComplete(5);
		
		siteRef = woPobj.getLocation();
		
		softAssert.assertTrue(siteAdministration.contains(siteRef), "Service Organization (SO1) with linked Site is correclty displayed in WO ");
		
		woPobj.clickOnTab(windowID, "Tracking");
		
		waitForExtJSAjaxComplete(5);
		
		woPobj.clickOnTab(windowID, "Planning");
		
		waitForExtJSAjaxComplete(5);
		
		plannedStrtDt = woPobj.getPlannedStartDate(WORKORDER_WINDOW_DETAIL);
		plannedEndDt = woPobj.getPlannedEndDate(WORKORDER_WINDOW_DETAIL);
		String expctdDate = "dd-mm-yyyy";
		
		softAssert.assertEquals(plannedEndDt, expctdDate, "Planned End Date is not filled");
		softAssert.assertEquals(plannedStrtDt, expctdDate, "Planned Start Date is not filled");
		waitForExtJSAjaxComplete(3);
		
		woPobj.closeUsingToolBar(WORKORDER_WINDOW_XPATH);
		waitForExtJSAjaxComplete(3);

		//WO3 is not visible in the grid
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, wo3Ref, "@class", "x-panel scheduler"), "WorkOrder 3 is NOT available in the grid");
		
		softAssert.assertTrue(isResourceGridEmpty("Empty"), "Resource Grid is Empty");
		
		softAssert.assertTrue(isTaskGridEmpty("Empty"), "Task Grid is also empty");
		
		waitForExtJSAjaxComplete(3);
		
		//select SO3 -> Select Date Range from : '10/01/2015 to 10/30/2015'
		/*setServiceOrganization(serviceOrgREf, "Reference");
		waitForExtJSAjaxComplete(5);
		
		setServiceGroup(serviceGrp, "Reference");
		waitForExtJSAjaxComplete(5);*/
		
		selectBoardsInScheduler("serviceGrpNotAvlRef","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
				
		String dateRangeFromTo = "01-08-2016 to 30-9-2016";
		
		setCustomDateRange(dateRangeFromTo);
		
		waitForExtJSAjaxComplete(5);
		waitForExtJSAjaxComplete(3);
		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport","1aqaPreParSites", "Site");
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getSchedulerGridSize(),woCount1, "WO that meets the SO3 AutoTestServOrg Searching criteria is displayed");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(selectAResource(serviceGrp, taskForce, resource1), "SG1 with Resource1 is displayed");
		
		softAssert.assertTrue(selectAResource(serviceGrp, taskForce, resource2), "SG1 with Resource2 is displayed");
		
		softAssert.assertTrue(selectAResource(serviceGrp, taskForce, resource3), "SG1 with Resource3 is displayed");

		waitForExtJSAjaxComplete(2);

		softAssert.assertFalse(selectAResource(serviceGrpNotAvl, taskForce, resource1), "SG2 is not displayed");

		driver.navigate().refresh();
		waitForExtJSAjaxComplete(10);

		collapseNavigation();
		waitForExtJSAjaxComplete(5);

		clickModuleInStartPage(scheduler_StartMenu_Xpath);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();

		dateRangeFromTo = "01-10-2015 to 31-10-2015";

		setCustomDateRange(dateRangeFromTo);


		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(10);

		int totalHrsWidth = getTotalHoursWidth(); // 1200
		int cellwidth = getIndividualCellWidthInHourMode(); //50
		int taskWidth = getWidthOfTask(resource2, 5 , 4)+1; //800

		//Total Width is 1200, Each cell is 50wdth so 8th cell calculations would be 1200 - (50*8) = 800
		cellwidth = totalHrsWidth - (cellwidth*8); //1200 - 400 = 800

		softAssert.assertEquals(taskWidth , cellwidth ,"one task assigned to member is displayed 10/01/2015 08:00");
		waitForExtJSAjaxComplete(5);

		frmDt = "01-10-2015";
		String dt15 = "15-10-2015";
		String dt30 = "30-10-2015";
		String taskRef = "Task1";

		//1 days to 30 inclusively are blocked.. Verifying Randomly 2 dates (15 and 30) whether task is present
		dateRangeFromTo = "15-10-2015 to 30-10-2015";
		
		setCustomDateRange(dateRangeFromTo);
		
		waitForExtJSAjaxComplete(10);
		
		String contactID = getIDOfContact(resource2);
		
		softAssert.assertTrue(mouseHoverTaskAndVerifyWoIdReference(contactID).contains(taskRef), "Task Reference is available in the grid for "+dt15);
		
		totalHrsWidth = getTotalHoursWidth();
		taskWidth = getWidthOfTask(resource2, 5 , 4)+1;
		
		softAssert.assertEquals(taskWidth , totalHrsWidth ,"15-10-2015 is blocked for Entire Day");
		waitForExtJSAjaxComplete(5);
		
		dateRangeFromTo = "30-10-2015";
		
		setCustomDateRange(dateRangeFromTo);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(mouseHoverTaskAndVerifyWoIdReference(contactID).contains(taskRef), "Task Reference is available in the grid for "+dt30);
		
		totalHrsWidth = getTotalHoursWidth();
		taskWidth = getWidthOfTask(resource2, 5 ,4)+1;
		
		softAssert.assertEquals(taskWidth , totalHrsWidth ,"30-10-2015 is blocked for Entire Day");
		waitForExtJSAjaxComplete(3);
		
		//Service organization' lookup > select SO2
		//TODO: this functionality is not applicable for trunk
		/*setServiceOrganization(servOrg2, "Reference");
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isGridEmpty("No items available"), "No items available is displayed");
		
		softAssert.assertTrue(isResourceGridEmpty("Empty"), "Resource Grid is Empty");
		
		softAssert.assertTrue(isTaskGridEmpty("Empty"), "Task Grid is also empty");
		
		waitForExtJSAjaxComplete(3);
		*/
		softAssert.assertAll();
		
		Reporter.log("User can choose the 'Service organization' filter criteria is successfully verified", true);
	}

	/**
	 * Test Case ID : C89862
	 * Author : MMA
	 */
	@Test(enabled = true)
	public void testSLAInfoVerificationLinkedToWO() throws Exception{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:When task is linked only to a WO with SLA information - WO SLA metric is shown on the tooltip: C89862" + " </span><br>", 
				true);

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testSLAInfoVerificationLinkedToWO");

		String serviceOrgREf = "AutoTestServOrgRef";
		String serviceGrpRef = "AutoTestServGrp1Ref";		
		String taskforceRef = "AutoTestTaskFrc1Ref";
		String dateRangeFromTo = "05-08-2016 to 31-08-2016";
		String taskContact = "AqaWebEmplDntDelete";
		String schedulerTask = "Task3WORelatedToCall";
		String slaType= "slaTypeWo";
		String slaIndicator = "column-sla-indicator-good";
		String[] colNameArr = {"Work Order ID","SLA Indicator Evaluation","SLA Indicator Metric","SLA Indicator Info"};
		String woRef = "SchedulerSLA";
		String taskRef = " Task3WORelatedToCall";

		clickAdministration();
		waitForExtJSAjaxComplete(5);	

		expandMasterData();
		waitForExtJSAjaxComplete(5);

		clickAdministrationOptions("Master Data","Service Organizations");
		waitForExtJSAjaxComplete(15);

		expandParentNodeInTreeByTextValue(serviceOrgREf);
		waitForExtJSAjaxComplete(5);

		expandParentNodeInTreeByTextValue("Sites");
		waitForExtJSAjaxComplete(5);

		String siteAdministration = getSiteFromServOrganization(serviceOrgREf);
		siteAdministration = siteAdministration.substring(0, siteAdministration.indexOf(" "));
				
		waitForExtJSAjaxComplete(5);

		expandParentNodeInTreeByTextValue(serviceGrpRef);
		waitForExtJSAjaxComplete(10);

		expandChildNodeInTreeByTextValue("Task Forces");
		waitForExtJSAjaxComplete(5);

		expandChildNodeInTreeByTextValue(taskforceRef);
		waitForExtJSAjaxComplete(10);

		Tree.checkNodeInTreeByTextValue(driver,taskforceRef);
		waitForExtJSAjaxComplete(5);

		List<String> workResources = getMembersOfTaskForce();
		waitForExtJSAjaxComplete(5);

		closeModule("Administration");
		waitForExtJSAjaxComplete(5);

		WorkOrderPageObject woObj = new WorkOrderPageObject();

		waitAndClick(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(15);
		waitForExtJSAjaxComplete(15);

		//All the WO which are displayed in the work order overview are displayed in scheduler board irrespective with WO status and linked site, so no need to apply filter
		/*		woObj.clickFilterResults();
		waitForExtJSAjaxComplete(10);

		//Finished
		woObj.expandMaintenanceAndSelectComplianceValueInFilterResults("x-window x-resizable-pinned", "Add restriction", "General Properties","Status Class");
		waitForExtJSAjaxComplete(10);

		setValueGridLookupWithFilters("Finished","Name");
		waitForExtJSAjaxComplete(5);

		woObj. selectComplianceRestriction("x-window x-resizable-pinned","Status Class = Finished");
		waitForExtJSAjaxComplete(5);

		woObj.clickAnyButtonInFilterResults("x-window x-resizable-pinned","NOT");
		waitForExtJSAjaxComplete(5);

		//Archived
		woObj.expandMaintenanceAndSelectComplianceValueInFilterResults("x-window x-resizable-pinned", "Add restriction", "General Properties","Status Class");
		waitForExtJSAjaxComplete(10);

		setValueGridLookupWithFilters("Archived","Name");
		waitForExtJSAjaxComplete(5);

		woObj. selectComplianceRestriction("x-window x-resizable-pinned","Status Class = Archived");
		waitForExtJSAjaxComplete(5);

		woObj.clickAnyButtonInFilterResults("x-window x-resizable-pinned","NOT");
		waitForExtJSAjaxComplete(5);
		clickSaveFilterInFilterWindow();
		waitForExtJSAjaxComplete(10);

		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport",siteAdministration, "Site");
		waitForExtJSAjaxComplete(25);*/

		int woCount = getGridSize();
		closeModule("Work Order");
		waitForExtJSAjaxComplete(5);

		//First time it is not opening so closing and opening again
		//TODO : Scheduler loading Issue
		collapseNavigation();
		waitForExtJSAjaxComplete(5);
		/*clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);
		waitForMaskDisappear();

		closeModule("Scheduler");
		waitForExtJSAjaxComplete(5);*/

		//Navigate to Scheduler
		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(15);

		/*setServiceOrganization(serviceOrgREf, "Reference");
		waitForExtJSAjaxComplete(5);
*/
		
		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		
		setCustomDateRange(dateRangeFromTo);
		waitForExtJSAjaxComplete(20);

	/*	woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", "1aqaPreParSites", "Site");
		waitForExtJSAjaxComplete(25);*/
		
		softAssert.assertEquals(woCount, getSchedulerGridSize(),"On the WO overview all the WOs (including WO1), that meets the selected filters, are displayed");
		waitForExtJSAjaxComplete(3);

		for(String workRes : workResources) {

			softAssert.assertTrue(selectAResource( serviceGrpRef,  taskforceRef, workRes),"Resources grid shows the resources that are linked to the Service groups of the selected Service organization (including the SG1)");
			waitForExtJSAjaxComplete(5);
		}
		waitForExtJSAjaxComplete(15);
		
		//start time and end time verification 
		int cellWidth = getIndividualCellWidthInHourMode();
		int startTimeTaskWidth = getWidthOfTask(taskContact,2 ,1 );

		//Total Width is 1200, Each cell is 50wdth., upto 8th cell calculations would be (50*8) = 400
		int taskStartTimeSetWidth = cellWidth*8; //50*8 = 400
		int taskEndTimeSetWidth = cellWidth*9; //50*9 = 450

		softAssert.assertEquals(startTimeTaskWidth, taskStartTimeSetWidth,"tast start time = task start time set for task");

		int taskWidth =  getWidthOfTask(taskContact,5 ,4 );
		int endTimeTaskWidth = startTimeTaskWidth + taskWidth + 1;

		softAssert.assertEquals(endTimeTaskWidth, taskEndTimeSetWidth,"tast end time = task end time set for task");

		selectAResource( serviceGrpRef,  taskforceRef,taskContact);
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(isTaskPresent(taskContact,schedulerTask),"The Task T1 for the resource is correctly displayed on the calendar");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(verifySLAIndicatorForWORelevantMetric(taskContact,slaType,slaIndicator),"The SLA indicator for the WO relevant metric is also shown on the task");
		waitForExtJSAjaxComplete(10);

		//Filter WO Grid with WO 2 Reference
		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", woRef, "Reference");
		waitForExtJSAjaxComplete(3);

		List<String> values = getValuesFromTransactionLine("@class","x-grid3-viewport",woRef,"Reference",colNameArr);
		String contactID = getIDOfContact(taskContact);
		String woIdRef = values.get(0) + taskRef;
		String LabelRef = mouseHoverTaskAndVerifyWoIdReference(contactID);
		
		softAssert.assertTrue(LabelRef.contains(woIdRef), "WO ID and Task Reference is shown on the Task Label");

		String slaIndicatorIcon = getSLAInfoFromtask("x-grid3-row-first"," x-grid3-col-3",true,"sla-indicator column","class");
		slaIndicatorIcon = slaIndicatorIcon.substring(slaIndicatorIcon.lastIndexOf("-")+1, slaIndicatorIcon.length());

		softAssert.assertEquals(getSLAInfoFromtask("x-grid3-row-first"," x-grid3-col-0",false,"",""),"Work Order","Work Order is shown on task tooltip");
		softAssert.assertTrue(values.contains(getSLAInfoFromtask("x-grid3-row-first"," x-grid3-col-1",false,"","")),"Work Order ID is shown on task tooltip"); 
		softAssert.assertTrue(values.contains(getSLAInfoFromtask("x-grid3-row-first"," x-grid3-col-2",false,"","")),"Metric indicator is shown on task tooltip"); 
		softAssert.assertTrue(values.contains(getSLAInfoFromtask("x-grid3-row-first"," x-grid3-col-5",false,"","")),"Time left to/over target is shown on task tooltip");  
		softAssert.assertTrue(values.contains(getSLAInfoFromtask("x-grid3-row-first"," x-grid3-col-3",true,"sla-indicator-title","textContent")),"Label of the relevant metric is shown on task tooltip" ); 
		softAssert.assertTrue(values.get(1).toLowerCase().contains(slaIndicatorIcon), "Label(smiley test) of the relevant metric is shown on task tooltip"); 

		softAssert.assertAll();

		Reporter.log("When task is linked only to a WO with SLA information - WO SLA metric is shown on the tooltip", true);
	}
	
	/**
	 * Testcase : C117574
	 * Author   : MMA
	 * @throws Exception
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testNonWOrkingHoursDisplayBasedOnTimeTable() throws Exception{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C117574 : Non working hours are displayed for one Resource depending on Time Table" + " </span><br>", 
				true);

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testNonWOrkingHoursDisplayBasedOnTimeTable");

		String dateRangeFromTo = "14-06-2018 to 14-07-2018";
		List<String> noWOrkingHours = Arrays.asList("0-9","14-15","18-24");
		String resourceName = "NoWOMultipleEditRights";
		String toolTip = "Non-working time";

		collapseNavigation();
		waitForExtJSAjaxComplete(5);

		clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(15);

		selectBoardsInScheduler("serviceGrpNotAvlRef","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();

		setCustomDateRange(dateRangeFromTo);
		waitForExtJSAjaxComplete(20);
		waitForMaskDisappear();

		softAssert.assertEquals(getNonWorkingHours(resourceName),noWOrkingHours,"Non Working Hours are shown as grey lines in following hours");
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isTaskPresent(resourceName,"Task1"),"In the Calendar First task is shown");
		softAssert.assertTrue(isTaskPresent(resourceName,"Task2"),"In the Calendar Second task is shown");
		softAssert.assertTrue(isTaskPresent(resourceName,"Task3"),"In the Calendar Third task is shown");
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(mouseHoverOnSchedulerTimeSheetToGetToolTip("6",resourceName), toolTip,"Pop up 'Non - Working hours [time]' appears");

		softAssert.assertAll();
		Reporter.log("Non working hours are displayed for one Resource depending on Time Table", true);	
	}
}
