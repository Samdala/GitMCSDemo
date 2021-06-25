package test.generalweb.scheduler;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.generalweb.workorders.WorkOrderPageObject;

public class SchedulerUserOptionsTestSuite extends SchedulerPageObject {

	/**
	 * Test Case ID: C89597
	 * Author : SSU
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testColorsOfTaskViaUserOptions() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: The colors for tasks can be set via the User options : C89597" + " </span><br>", 
				true);

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testColorsOfTaskViaUserOptions");

		waitForExtJSAjaxComplete(25);

		collapseNavigation();
		waitForExtJSAjaxComplete(10);

		String asPlannedLabel = "As planned:";
		String overdueLabel = "Overdue:";
		String earlyLabel = "Early:";
		String suspendedLabel = "Suspended:";
		String woOutsideLabel = "Work Order outside the current Overview:";
		String notRelatedWOLabel = "Not related to a Work Order:";
		String taskForceLabel = "Task force summary:";

		String redColour = "#FF0000";
		String blueColour = "#00FFFF";
		String magentaColour = "#FF00FF";

		String selectedBlueColor = "00FFFF";
		String selectedMagentaColor = "FF00FF";

		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		selectOptions();
		waitForExtJSAjaxComplete(25);

		String overDueInitialColor = getTaskBoxColors(overdueLabel);
		String earlyInitialColor = getTaskBoxColors(earlyLabel);
		String suspendedInitialColor = getTaskBoxColors(suspendedLabel);
		String woOutsideInitialColor = getTaskBoxColors(woOutsideLabel);
		String notRelatedWoInitialColor = getTaskBoxColors(notRelatedWOLabel);
		String taskFrcInitialColor = getTaskBoxColors(taskForceLabel);


		waitForExtJSAjaxComplete(25);

		clickTaskBoxColors(asPlannedLabel);

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(getWindowTitle("@class", "slnmClrPckr").contains(SELECT_A_COLOR_WINDOW_HEADER), "Select a color window is opened");

		waitForExtJSAjaxComplete(5);

		//Mouse hover and check the color code
		softAssert.assertEquals(mouseHoverColor(redColour), redColour, "color + rgb are shown on the bottom of the pop up");

		waitForExtJSAjaxComplete(5);

		//1. Change the color of Planned Label
		clickOnAnyColor(blueColour);

		waitForExtJSAjaxComplete(25);

		String colorActual = getTaskBoxColors(asPlannedLabel);

		softAssert.assertEquals(colorActual, selectedBlueColor, "Selected color is displayed near Planned Label");

		waitForExtJSAjaxComplete(5);

		//Verify Except Planned label none of the colors are changed
		String plannedLabelRelated[] = {overdueLabel, earlyLabel, suspendedLabel, woOutsideLabel, notRelatedWOLabel, taskForceLabel};

		String initialColorPlannedArray[] = {overDueInitialColor, earlyInitialColor, suspendedInitialColor, woOutsideInitialColor, notRelatedWoInitialColor, taskFrcInitialColor};

		checkColorsRetainedForTaskColors(initialColorPlannedArray, plannedLabelRelated);

		waitForExtJSAjaxComplete(25);

		// 2. Change the color of Overdue Label
		clickTaskBoxColors(overdueLabel);

		waitForExtJSAjaxComplete(25);

		clickOnAnyColor(magentaColour);

		waitForExtJSAjaxComplete(25);

		colorActual = getTaskBoxColors(overdueLabel);

		softAssert.assertEquals(colorActual, selectedMagentaColor, "Selected Blue color is displayed near overdue Label");

		waitForExtJSAjaxComplete(5);

		//Verify Except overdue label none of the colors are changed
		String overDueLabelRelated[] = {asPlannedLabel, earlyLabel, suspendedLabel, woOutsideLabel, notRelatedWOLabel, taskForceLabel};

		String initialColorOverDueArray[] = {selectedBlueColor, earlyInitialColor, suspendedInitialColor, woOutsideInitialColor, notRelatedWoInitialColor, taskFrcInitialColor};

		checkColorsRetainedForTaskColors(initialColorOverDueArray, overDueLabelRelated);

		waitForExtJSAjaxComplete(25);

		// 3. Change the color of Early Label
		clickTaskBoxColors(earlyLabel);

		waitForExtJSAjaxComplete(25);

		clickOnAnyColor(blueColour);

		waitForExtJSAjaxComplete(25);

		colorActual = getTaskBoxColors(earlyLabel);

		softAssert.assertEquals(colorActual, selectedBlueColor, "Selected color is displayed near Early Label");

		waitForExtJSAjaxComplete(5);

		//Verify Except overdue label none of the colors are changed
		String earlyLabelRelated[] = {asPlannedLabel, overdueLabel, suspendedLabel, woOutsideLabel, notRelatedWOLabel, taskForceLabel};

		String initialColorEarlyArray[] = {selectedBlueColor, selectedMagentaColor, suspendedInitialColor, woOutsideInitialColor, notRelatedWoInitialColor, taskFrcInitialColor};

		checkColorsRetainedForTaskColors(initialColorEarlyArray, earlyLabelRelated);

		waitForExtJSAjaxComplete(25);

		// 4. Change the color of Suspended Label
		clickTaskBoxColors(suspendedLabel);

		waitForExtJSAjaxComplete(25);

		clickOnAnyColor(magentaColour);

		waitForExtJSAjaxComplete(25);

		colorActual = getTaskBoxColors(suspendedLabel);

		softAssert.assertEquals(colorActual, selectedMagentaColor, "Selected color is displayed near Suspended Label");

		waitForExtJSAjaxComplete(5);

		//Verify Except Suspended label none of the colors are changed
		String suspendedLabelRelated[] = {asPlannedLabel, overdueLabel, earlyLabel, woOutsideLabel, notRelatedWOLabel, taskForceLabel};

		String initialColorSuspendedArray[] = {selectedBlueColor, selectedMagentaColor, selectedBlueColor,  woOutsideInitialColor, notRelatedWoInitialColor, taskFrcInitialColor};

		checkColorsRetainedForTaskColors(initialColorSuspendedArray, suspendedLabelRelated);

		waitForExtJSAjaxComplete(25);

		//5. Change the color of WO Outside current overview Label
		clickTaskBoxColors(woOutsideLabel);

		waitForExtJSAjaxComplete(25);

		clickOnAnyColor(blueColour);

		waitForExtJSAjaxComplete(25);

		colorActual = getTaskBoxColors(woOutsideLabel);

		softAssert.assertEquals(colorActual, selectedBlueColor, "Selected color is displayed near WO Outside Current Overview Label");

		waitForExtJSAjaxComplete(5);

		//Verify Except Suspended label none of the colors are changed
		String woOutsideLabelRelated[] = {asPlannedLabel, overdueLabel, earlyLabel, suspendedLabel, notRelatedWOLabel, taskForceLabel};

		String initialColorWoOutsideArray[] = {selectedBlueColor, selectedMagentaColor, selectedBlueColor, selectedMagentaColor, notRelatedWoInitialColor, taskFrcInitialColor};

		checkColorsRetainedForTaskColors(initialColorWoOutsideArray, woOutsideLabelRelated);

		waitForExtJSAjaxComplete(25);

		//6. Change the color of Not Related To WO Label
		clickTaskBoxColors(notRelatedWOLabel);

		waitForExtJSAjaxComplete(25);

		clickOnAnyColor(magentaColour);

		waitForExtJSAjaxComplete(25);

		colorActual = getTaskBoxColors(notRelatedWOLabel);

		softAssert.assertEquals(colorActual, selectedMagentaColor, "Selected color is displayed near Not Related to WO Label");

		waitForExtJSAjaxComplete(5);

		//Verify Except Suspended label none of the colors are changed
		String NotRelatedToWOLabelRelated[] = {asPlannedLabel, overdueLabel, earlyLabel, suspendedLabel, woOutsideLabel, taskForceLabel};

		String initialColorNotRelatedToWOArray[] = {selectedBlueColor, selectedMagentaColor, selectedBlueColor, selectedMagentaColor, selectedBlueColor, taskFrcInitialColor};

		checkColorsRetainedForTaskColors(initialColorNotRelatedToWOArray, NotRelatedToWOLabelRelated);

		waitForExtJSAjaxComplete(25);

		//7. Change the color of Task Force Summary Label
		clickTaskBoxColors(taskForceLabel);

		waitForExtJSAjaxComplete(25);

		clickOnAnyColor(blueColour);

		waitForExtJSAjaxComplete(25);

		colorActual = getTaskBoxColors(taskForceLabel);

		softAssert.assertEquals(colorActual, selectedBlueColor, "Selected color is displayed near Task Force Summary Label");

		waitForExtJSAjaxComplete(5);

		//Verify Except Suspended label none of the colors are changed
		String taskForceLabelRelated[] = {asPlannedLabel, overdueLabel, earlyLabel, suspendedLabel, woOutsideLabel, notRelatedWOLabel};

		String initialColortaskForceArray[] = {selectedBlueColor, selectedMagentaColor, selectedBlueColor, selectedMagentaColor, selectedBlueColor, selectedMagentaColor};

		checkColorsRetainedForTaskColors(initialColortaskForceArray, taskForceLabelRelated);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		clickButton(USER_OPTIONS_WINDOW_HEADER, "Save & close");

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		//TODO: On clicking "Save & close" user options window is close on saving, so cannot verify the "Successfully saved" message
		//softAssert.assertTrue(getInfoBarMsgOfWindow("@id", getXWindowId(USER_OPTIONS_WINDOW_HEADER)).contains("Successfully saved"), "Changes are successfully saved");

		waitForExtJSAjaxComplete(25);

		softAssert.assertAll();

		Reporter.log("The colors for tasks can be set via the User options is successfully verified", true);
	}

	/**
	 * Test Case ID: C83259
	 * Author : SSU
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testSetOfVisibleColumns() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: User can select the set of visible columns : C83259" + " </span><br>", 
				true);

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testSetOfVisibleColumns");

		waitForExtJSAjaxComplete(25);

		String activityTypeTask = "Activity Type";
		String referenceTask = "Reference";

		clickAdministration();

		waitForExtJSAjaxComplete(25);

		expandModuleSettings();

		waitForExtJSAjaxComplete(25);

		clickAdministrationOptions("Scheduler", "Option Profiles Settings");

		waitForExtJSAjaxComplete(25);

		softAssert.assertFalse(verifyColumnsInTaskLabelGrid(TASK_LABEL_XPATH, activityTypeTask), "Column is not available in the Task Label Grid");

		clickModifyButtonTaskLabel();

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(getWindowTitleCustomized("@class", "x-resizable-pinned").contains(SELECT_COLUMNS_WINDOW_HEADER), "Select columns window is opened");

		waitForExtJSAjaxComplete(25);

		//Expand Calls Tree in Columns
		expandColumns(SELECT_COLUMNS_WINDOW_HEADER, "gp-group-Call-hd", "gp-group-Call");

		waitForExtJSAjaxComplete(25);

		String callArr[] = {"Call ID", "Caller", "Call SLA Indicator Evaluation"};

		verifyAllColumnNames(callArr, "Call");

		waitForExtJSAjaxComplete(25);

		//Expand Task Tree in Columns
		expandColumns(SELECT_COLUMNS_WINDOW_HEADER, "gp-group-Task-hd", "gp-group-Task");

		waitForExtJSAjaxComplete(25);

		String taskArr[] = {"Reference", "Activity Type"};

		verifyAllColumnNames(taskArr, "Task");

		waitForExtJSAjaxComplete(25);

		//Expand Work order Tree in Columns
		expandColumns(SELECT_COLUMNS_WINDOW_HEADER, "gp-group-Work Order-hd", "gp-group-Work Order");

		waitForExtJSAjaxComplete(25);

		String woArr[] = {"Work Order ID", "Work Order Reference", "Work Order Type", "Workorder Nature", "Workorder Priority", "Workorder Severity", "Workorder Site", "Workorder Location", "Workorder Customer", "Project Reference", "Workorder SLA Indicator Evaluation"};

		verifyAllColumnNames(woArr, "Work Order");

		waitForExtJSAjaxComplete(25);

		//Activity Type is checked
		checkColumns(SELECT_COLUMNS_WINDOW_HEADER, "gp-group-Task-bd", activityTypeTask);

		waitForExtJSAjaxComplete(5);

		clickButton(SELECT_COLUMNS_WINDOW_HEADER, "Save");

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(verifyColumnsInTaskLabelGrid(TASK_LABEL_XPATH, activityTypeTask), "Column is added to Task Label Grid");

		waitForExtJSAjaxComplete(25);

		clickModifyButtonTaskLabel();

		waitForExtJSAjaxComplete(25);

		//Expand Task Tree in Columns
		expandColumns(SELECT_COLUMNS_WINDOW_HEADER, "gp-group-Task-hd", "gp-group-Task");

		waitForExtJSAjaxComplete(25);

		//Uncheck 2 Columns in Task 
		unCheckColumns(SELECT_COLUMNS_WINDOW_HEADER, "gp-group-Task-bd", taskArr);

		waitForExtJSAjaxComplete(5);

		clickButton(SELECT_COLUMNS_WINDOW_HEADER, "Save");

		waitForExtJSAjaxComplete(25);

		softAssert.assertFalse(verifyColumnsInTaskLabelGrid(TASK_LABEL_XPATH, activityTypeTask), "Activity Type Column is not available in the Task Label Grid");

		waitForExtJSAjaxComplete(25);

		softAssert.assertFalse(verifyColumnsInTaskLabelGrid(TASK_LABEL_XPATH, referenceTask), "Reference Column is not available in the Task Label Grid");

		waitForExtJSAjaxComplete(25);

		clickModifyButtonTaskLabel();

		waitForExtJSAjaxComplete(25);

		//Expand Task Tree in Columns
		expandColumns(SELECT_COLUMNS_WINDOW_HEADER, "gp-group-Task-hd", "gp-group-Task");

		waitForExtJSAjaxComplete(25);

		//Expand Work order Tree in Columns
		expandColumns(SELECT_COLUMNS_WINDOW_HEADER, "gp-group-Work Order-hd", "gp-group-Work Order");

		waitForExtJSAjaxComplete(25);

		//Expand Calls Tree in Columns
		expandColumns(SELECT_COLUMNS_WINDOW_HEADER, "gp-group-Call-hd", "gp-group-Call");

		waitForExtJSAjaxComplete(25);

		unCheckColumns(SELECT_COLUMNS_WINDOW_HEADER, "gp-group-Call-bd", callArr);

		waitForExtJSAjaxComplete(5);

		unCheckColumns(SELECT_COLUMNS_WINDOW_HEADER, "gp-group-Task-bd", taskArr);

		waitForExtJSAjaxComplete(5);

		unCheckColumns(SELECT_COLUMNS_WINDOW_HEADER, "gp-group-Work Order-bd", woArr);

		waitForExtJSAjaxComplete(5);

		clickButton(SELECT_COLUMNS_WINDOW_HEADER, "Save");

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(getWarningDialogText().contains("Please select at least one column."), "The error that at least one column should be visible is displayed");

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("OK");

		waitForExtJSAjaxComplete(20);

		//Activity Type is checked
		checkColumns(SELECT_COLUMNS_WINDOW_HEADER, "gp-group-Task-bd", referenceTask);

		waitForExtJSAjaxComplete(20);

		clickButton(SELECT_COLUMNS_WINDOW_HEADER, "Save");

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(verifyColumnsInTaskLabelGrid(TASK_LABEL_XPATH, referenceTask), "Reference Column is added to Task Label Grid");

		softAssert.assertEquals(getGridSizeTaskLabelAdministration(), 1, "Only one row is available in Task label grid");

		waitForExtJSAjaxComplete(25);

		clickModifyButtonTaskLabel();

		waitForExtJSAjaxComplete(25);

		//check all the columns At header level
		selectAllFieldsOfAGroupInSelectCols(SELECT_COLUMNS_WINDOW_HEADER, "group-Call");

		selectAllFieldsOfAGroupInSelectCols(SELECT_COLUMNS_WINDOW_HEADER, "group-Task");

		selectAllFieldsOfAGroupInSelectCols(SELECT_COLUMNS_WINDOW_HEADER, "group-Work Order");

		waitForExtJSAjaxComplete(5);

		clickButton(SELECT_COLUMNS_WINDOW_HEADER, "Save");

		waitForExtJSAjaxComplete(25);

		int expectedSize = taskArr.length+callArr.length+woArr.length;

		System.err.println("Expected No co columns in Task Label Grid after enabling all columns is " +expectedSize);

		waitForExtJSAjaxComplete(5);

		int actualSize = getGridSizeTaskLabelAdministration();

		System.err.println("Actual No co columns in Task Label Grid after enabling all columns is " +actualSize);

		softAssert.assertEquals(actualSize, expectedSize, "All Columns are available in Task label grid");

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(verifyScrollBarInTaskLabelGrid(), "Scroll Bar is available in the grid");

		softAssert.assertAll();

		Reporter.log("User can select the set of visible columns is successfully verified", true);
	}

	/**
	 * Test Case ID: C90139
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testChangeOfUserOptionsAppliedOnLoggedUser() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Changes of User Options are immediatelly applied for the logged user : C90139" + " </span><br>", 
				true);

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testChangeOfUserOptionsAppliedOnLoggedUser");

		String woRef = "SchedulerRelated";
		String serviceOrgREf = "AutoTestServOrgRef";
		String woTypeTask = "Work Order Type";
		String contact = "AqaWebEmplDntDelete";

		String overdueLabel = "Overdue:";
		String magentaColour = "#FF00FF";
		String selectedMagentaColor = "FF00FF";

		int initialTaskWidth = 15;
		int initialDayModeTaskWidth = 2;

		waitForExtJSAjaxComplete(20);

		//TODO : Scheduler loading Issue
		collapseNavigation();
		/*clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);

		waitForExtJSAjaxComplete(20);

		closeModule("Scheduler");*/

		waitForExtJSAjaxComplete(5);

		//TODO : Scheduler loading Issue
		clickModuleInStartPage(scheduler_StartMenu_Xpath); 
		//clickXPath(XPATH_SCHEDULER);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		//Step 1
		selectOptions();

		waitForExtJSAjaxComplete(5);

		String overDueInitialColor = getTaskBoxColors(overdueLabel);

		waitForExtJSAjaxComplete(3);

		clickModifyButtonInOptions(USER_OPTIONS_WINDOW_HEADER);

		waitForExtJSAjaxComplete(5);

		//Expand Work order Tree in Columns
		expandColumns(SELECT_COLUMNS_WINDOW_HEADER, "gp-group-Work Order-hd", "gp-group-Work Order");

		waitForExtJSAjaxComplete(25);

		//Activity Type is checked
		checkColumns(SELECT_COLUMNS_WINDOW_HEADER, "gp-group-Work Order-bd", woTypeTask);

		waitForExtJSAjaxComplete(5);

		clickButton(SELECT_COLUMNS_WINDOW_HEADER, "Save");

		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(verifyColumnsInUserOptions(USER_OPTIONS_WINDOW_HEADER, woTypeTask), "Column is added to Task Label Grid");

		waitForExtJSAjaxComplete(5);

		closeUsingToolBarJS(USER_OPTIONS_WINDOW_HEADER);

		waitForExtJSAjaxComplete(3);

		// On WO overview set all the mandatory filters
		/*setServiceOrganization(serviceOrgREf, "Reference");

		waitForExtJSAjaxComplete(5);*/

		selectBoardsInScheduler("AQA Default","Show");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();

		/*Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE);
		waitForExtJSAjaxComplete(20);*/

		setCustomDateRange("01-08-2016 to 30-9-2016");
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();

		/*action.sendKeys(Keys.ESCAPE);
		waitForExtJSAjaxComplete(20);
		 */
		WorkOrderPageObject woObj = new WorkOrderPageObject();
		woObj.filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", woRef, "Reference");
		waitForExtJSAjaxComplete(3);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, woRef, "@class", "x-panel scheduler"), "WorkOrder is available in the grid");

		waitForExtJSAjaxComplete(25);

		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "x-panel scheduler", woRef);

		waitForExtJSAjaxComplete(5);

		selectActions("Edit");

		waitForExtJSAjaxComplete(25);

		WorkOrderPageObject woPobj = new WorkOrderPageObject();

		String woIdRef = getWONameFromDetailsWindow();

		woPobj.clickGeneralTab();

		waitForExtJSAjaxComplete(5);

		String woType = woPobj.getType();

		Reporter.log("WO Type "+woType, true);

		waitForExtJSAjaxComplete(3);

		woPobj.closeUsingToolBar(WORKORDER_WINDOW_XPATH);

		waitForExtJSAjaxComplete(5);

		String contactID = getIDOfContact(contact);

		woIdRef = woIdRef+" "+woRef;

		softAssert.assertTrue(mouseHoverTaskAndVerifyWoIdReference(contactID).contains(woIdRef), "WO ID and Reference is shown on the pop up");

		waitForExtJSAjaxComplete(3);

		String idValueHourMode = getIDFromHourMode(contactID);

		waitForExtJSAjaxComplete(2);

		//Change Task box colors
		selectOptions();

		waitForExtJSAjaxComplete(3);

		// 2. Change the color of Overdue Label
		clickTaskBoxColors(overdueLabel);

		waitForExtJSAjaxComplete(25);

		clickOnAnyColor(magentaColour);

		waitForExtJSAjaxComplete(25);

		String colorActual = getTaskBoxColors(overdueLabel);

		softAssert.assertEquals(colorActual, selectedMagentaColor, "Selected Magents color is displayed near overdue Label");

		waitForExtJSAjaxComplete(5);

		clickButton(USER_OPTIONS_WINDOW_HEADER, "Save & close");

		waitForExtJSAjaxComplete(5);

		//closeUsingToolBarJS(USER_OPTIONS_WINDOW_HEADER);

		waitForExtJSAjaxComplete(3);

		contactID = getIDOfContact(contact);

		softAssert.assertTrue(getCssValuesOfTask(contactID).contains(magentaColour), "The task uses the new color, set on the User options");

		waitForExtJSAjaxComplete(2);

		String initialScrollWidth = getAttributeValuesOfTask(contactID, "scrollWidth");

		waitForExtJSAjaxComplete(2);

		//change Smallest time box visualization (Hour mode) to 00:30
		selectOptions();

		waitForExtJSAjaxComplete(3);

		//Hour Mode
		setDurationValuesInUserOptions("Smallest time box visualization", "1", "00:30");

		waitForExtJSAjaxComplete(2);

		clickButton(USER_OPTIONS_WINDOW_HEADER, "Save & close");

		waitForExtJSAjaxComplete(5);

		//closeUsingToolBarJS(USER_OPTIONS_WINDOW_HEADER);

		waitForExtJSAjaxComplete(3);

		contactID = getIDOfContact(contact);

		String scrollWidthAfter30Mts = getAttributeValuesOfTask(contactID, "scrollWidth");

		waitForExtJSAjaxComplete(2);

		softAssert.assertNotEquals(scrollWidthAfter30Mts, initialScrollWidth, "Scroll Width of The task is increased after duration change to 30 Mts");

		//TODO
		softAssert.assertEquals(getDimensionsOfTask(contactID), initialTaskWidth, "Dimension of the task is changed to 15");

		waitForExtJSAjaxComplete(2);

		//change Smallest time box visualization (Hour mode) to 1h
		selectOptions();

		waitForExtJSAjaxComplete(3);

		//Hour Mode
		setDurationValuesInUserOptions("Smallest time box visualization", "1", "01:00");

		waitForExtJSAjaxComplete(2);

		clickButton(USER_OPTIONS_WINDOW_HEADER, "Save & close");

		waitForExtJSAjaxComplete(5);

		//closeUsingToolBarJS(USER_OPTIONS_WINDOW_HEADER);

		waitForExtJSAjaxComplete(3);

		String scrollWidthAfter1Hr = getAttributeValuesOfTask(contactID, "scrollWidth");

		waitForExtJSAjaxComplete(2);

		softAssert.assertNotEquals(scrollWidthAfter1Hr, scrollWidthAfter30Mts, "Scroll Width of The task is increased after duration change to 1Hr");

		//TODO
		softAssert.assertEquals(getDimensionsOfTask(contactID), 30, "Dimension of the task is changed to 1 hr");

		waitForExtJSAjaxComplete(2);

		//change Default task duration' (Hour mode) to 00:30
		selectOptions();

		waitForExtJSAjaxComplete(3);

		//Default task duration can not be less than Smallest time box visualization
		setDurationValuesInUserOptions("Smallest time box visualization", "1", "00:30");

		//Hour Mode
		setDurationValuesInUserOptions("Default duration", "1", "00:30");

		waitForExtJSAjaxComplete(2);

		clickButton(USER_OPTIONS_WINDOW_HEADER, "Save & close");

		waitForExtJSAjaxComplete(5);

		//closeUsingToolBarJS(USER_OPTIONS_WINDOW_HEADER);

		waitForExtJSAjaxComplete(3);

		//TODO
		softAssert.assertEquals(getDimensionsOfTask(contactID), initialTaskWidth, "Dimension of the task is changed to 30");

		waitForExtJSAjaxComplete(2);

		//change Duration granularity (Hour mode) to 00:30
		selectOptions();

		waitForExtJSAjaxComplete(3);

		//Duration granularity can not be less than Smallest time box visualization
		setDurationValuesInUserOptions("Smallest time box visualization", "1", "00:30");

		//Hour Mode
		setDurationValuesInUserOptions("Duration granularity", "1", "00:30");

		waitForExtJSAjaxComplete(2);

		clickButton(USER_OPTIONS_WINDOW_HEADER, "Save & close");

		waitForExtJSAjaxComplete(5);

		//closeUsingToolBarJS(USER_OPTIONS_WINDOW_HEADER);

		waitForExtJSAjaxComplete(3);

		//TODO
		softAssert.assertEquals(getDimensionsOfTask(contactID), initialTaskWidth, "Dimension of the task is changed to 30");

		waitForExtJSAjaxComplete(2);
		waitForMaskDisappear();
		//Zoom out to change to Day Mode
		ZoomOut();
		waitForExtJSAjaxComplete(15);

		softAssert.assertTrue(getZoomButtonStatus().contains("icon-zoom-in"), "Zoom button changes from minus to plus");

		//change Default task duration (Day mode) to 00:30
		selectOptions();

		waitForExtJSAjaxComplete(3);

		//Default task duration can not be less than Smallest time box visualization
		setDurationValuesInUserOptions("Smallest time box visualization", "2", "00:30");

		//Day Mode
		setDurationValuesInUserOptions("Default duration", "2", "00:30");

		waitForExtJSAjaxComplete(2);

		clickButton(USER_OPTIONS_WINDOW_HEADER, "Save & close");

		waitForExtJSAjaxComplete(5);

		//closeUsingToolBarJS(USER_OPTIONS_WINDOW_HEADER);

		waitForExtJSAjaxComplete(3);

		//TODO
		softAssert.assertEquals(getDimensionsOfTask(idValueHourMode), initialDayModeTaskWidth, "Dimension of the task is changed to 2");

		waitForExtJSAjaxComplete(2);

		//change Smallest time box visualization (Day mode) to 00:30
		selectOptions();

		waitForExtJSAjaxComplete(3);

		//Day Mode
		setDurationValuesInUserOptions("Smallest time box visualization", "2", "00:30");

		waitForExtJSAjaxComplete(2);

		clickButton(USER_OPTIONS_WINDOW_HEADER, "Save & close");

		waitForExtJSAjaxComplete(5);

		//closeUsingToolBarJS(USER_OPTIONS_WINDOW_HEADER);

		waitForExtJSAjaxComplete(3);

		//TODO
		softAssert.assertEquals(getDimensionsOfTask(idValueHourMode), initialDayModeTaskWidth, "Dimension of the task is changed to 2");

		waitForExtJSAjaxComplete(2);

		//change Duration granularity (Day mode) to 00:30
		selectOptions();

		waitForExtJSAjaxComplete(3);

		//Day Mode
		setDurationValuesInUserOptions("Duration granularity", "2", "00:30");

		waitForExtJSAjaxComplete(2);

		clickButton(USER_OPTIONS_WINDOW_HEADER, "Save & close");

		waitForExtJSAjaxComplete(5);

		//closeUsingToolBarJS(USER_OPTIONS_WINDOW_HEADER);

		waitForExtJSAjaxComplete(3);

		//TODO
		softAssert.assertEquals(getDimensionsOfTask(idValueHourMode), initialDayModeTaskWidth, "Dimension of the task is changed to 30");

		waitForExtJSAjaxComplete(2);

		softAssert.assertAll();

		Reporter.log("Changes of User Options are immediatelly applied for the logged user is successfully verified", true);
	}


}
