package test.generalweb.workorders;

import java.util.Arrays;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.DropDown;
import test.framework.webelement.Grid;

public class WorkOrdersAccessTestSuite extends
WorkOrderPageObject  {
	
	/**
	 * Test Case ID: C91885,C75225
	 * Author : SSA
	 * 
	 */
	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testVerifyStockInfRights() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C110693: A new fields are to be added in the BOM line"
				+ "C123543: Verify that, the users with right “View Stock Info on BOM” are only allowed to see the current Stock information </span><br>", true);
		
		
		String testWorkOrder = "WOForStockInfo";
		
		String userID="aqa_NoRightsForStockInfo";
		
		String password="qwerty";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testVerifyStockInfRights");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		click(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(25);
		
		filterGridWithoutUsingMcsElementModifiedForChrome("@realid", "mogrid", testWorkOrder,"Reference");
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, testWorkOrder);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickTimeMaterialTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickTimeMaterialBillMaterialTab();
		
		waitForExtJSAjaxComplete(25);
		
		//C91885
		softAssert.assertTrue(isVisibleColumnInBOM("Issued Qty"),"column Issued Qty is visible in BOM window.");
		softAssert.assertTrue(isVisibleColumnInBOM("Returned Qty"),"column Returned Qty is visible in BOM window.");
		softAssert.assertTrue(isVisibleColumnInBOM("Consumed Qty"),"column Consumed Qty is visible in BOM window.");
		softAssert.assertTrue(isVisibleColumnInBOM("Reserved Qty"),"column Reserved Qty is visible in BOM window.");
		
		waitForExtJSAjaxComplete(25);
		
		//C75225
		softAssert.assertTrue(isVisibleStockInfoButtonInBOM(),"The button Stock Info is visible when user has rights.");
		
		softAssert.assertTrue(isStockInfoButtonDisabledInBOMTab(),"The button Stock Info is disabled when BOM line is not selected and user has rights.");
		
		selectBOMLine("1","Consumed");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertFalse(isStockInfoButtonDisabledInBOMTab(),"The button Stock Info is enabled when BOM line is selected and user has rights.");
		
		waitForExtJSAjaxComplete(25);
		
		navigateToMainPage(userID, password);
		
		waitForExtJSAjaxComplete(20);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		click(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(25);
		
		filterGridWithoutUsingMcsElementModifiedForChrome("@realid", "mogrid", testWorkOrder,"Reference");
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, testWorkOrder);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickTimeMaterialTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickTimeMaterialBillMaterialTab();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertFalse(isVisibleStockInfoButtonInBOM(),"The button Stock Info is not visible when user has no rights.");
		
		softAssert.assertAll();
		
		Reporter.log("Verifed that, the users with right “View Stock Info on BOM” are only allowed to see the current Stock information", true);
		
	}
	
	/**
	 * Test Case ID : C111924, C116440
	 * Author: MMA
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testVerifyBookActivityCostAndHierarchyExtensionRights() throws Exception{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Activity status is changed to 'Booked' with appropriate User right: C111924 "
				+ " WO hierarchy extension is available via specified right : C116440 </span><br>", true);

		String testWorkOrder = "2preWrkRef";
		String noViewFinancialRights = "WONoFinancialRights";
		String password = "qwerty";
		String product = "3preProdRef";
		List<String> status = Arrays.asList("Completed");

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testVerifyBookActivityCostAndHierarchyExtensionRights");

		//C116440
		expandAdministration();
		waitForExtJSAjaxComplete(5);

		click(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();
		waitForExtJSAjaxComplete(5);
		filterGridWithoutUsingMcsElementModifiedForChrome("@realid", "mogrid", testWorkOrder,"Reference");
		waitForExtJSAjaxComplete(5);
		Grid.checkRowInGriByTextValueAndClick(driver, testWorkOrder);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		clickGeneralTab();
		waitForExtJSAjaxComplete(15);

		softAssert.assertTrue(isWOHierarchyDisplayed(testWorkOrder),"WO hierarchy is displayed");
		waitForExtJSAjaxComplete(5);


		//C111924
		clickTimeMaterialTab();
		waitForExtJSAjaxComplete(15);
		clickTimeMaterialLaborTab();
		waitForExtJSAjaxComplete(10);

		clickAddLabor();
		waitForExtJSAjaxComplete(20);
		saveWorkOrder(getXWindowId("Add Activity"),"Save");
		waitForExtJSAjaxComplete(20);
		setLaborActivityStatus("Submitted");
		waitForExtJSAjaxComplete(5);
		saveWorkOrder(getXWindowId("Edit Activity"),"Save");
		waitForExtJSAjaxComplete(25);

		setLaborActivityStatus("Completed");
		waitForExtJSAjaxComplete(5);
		saveWorkOrder(getXWindowId("Edit Activity"),"Save");

		setLaborActivityStatus("Booked");
		waitForExtJSAjaxComplete(5);
		saveWorkOrder(getXWindowId("Edit Activity"),"Save");
		waitForExtJSAjaxComplete(15);

		softAssert.assertTrue(getWarningDialogTextMsg().contains("Unable to save activity. Please make sure all mandatory fields are filled in"),"Warning message is displayed in the 'Financial Details' Tab");
		clickOnDialogButton("OK");
		waitForExtJSAjaxComplete(5);

		clickLaborActivityFinancialTab();
		waitForExtJSAjaxComplete(15);
		
		softAssert.assertTrue(isFieldHighlightedInActivityWin("Product or Service"),"Select the 'Product or Service' Lookup is highlighted");
		waitForExtJSAjaxComplete(5);
		
		String activityID = getActivityID();
		saveWorkOrder(getXWindowId("Edit Activity"),"Cancel");

		checkRowInLaborTabGridForActivity(activityID);
		waitForExtJSAjaxComplete(15);
		clickEditLabor();
		waitForExtJSAjaxComplete(15);

		softAssert.assertEquals(getActivityWindowFieldValues("statusCode"),"Completed","Activity is not saved");
		setLaborActivityStatus("Booked");
		waitForExtJSAjaxComplete(5);

		clickLaborActivityFinancialTab();
		setLaborProduct(product);
		waitForExtJSAjaxComplete(15);

		saveAndCloseActivity();
		waitForExtJSAjaxComplete(15);

		checkRowInLaborTabGridForActivity(activityID);
		waitForExtJSAjaxComplete(10);

		clickEditLabor();
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(getActivityWindowFieldValues("statusCode").contains("Booked"),"Activity is saved");
		waitForExtJSAjaxComplete(5);
		saveWorkOrder(getXWindowId("Edit Activity"),"Cancel");
		waitForExtJSAjaxComplete(5);

		//C116440
		logout();
		login(noViewFinancialRights, password);
		waitForExtJSAjaxComplete(15);

		expandAdministration();
		waitForExtJSAjaxComplete(5);

		click(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, testWorkOrder);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		clickGeneralTab();
		waitForExtJSAjaxComplete(25);

		softAssert.assertFalse(isWOHierarchyDisplayed(testWorkOrder),"WO hierarchy is displayed");
		waitForExtJSAjaxComplete(5);

		//C111924
		clickTimeMaterialTab();
		waitForExtJSAjaxComplete(15);
		clickTimeMaterialLaborTab();
		waitForExtJSAjaxComplete(10);

		clickAddLabor();
		waitForExtJSAjaxComplete(20);
		saveWorkOrder(getXWindowId("Add Activity"),"Save");

		setLaborActivityStatus("Submitted");
		waitForExtJSAjaxComplete(25);
		saveWorkOrder(getXWindowId("Edit Activity"),"Save");
		waitForExtJSAjaxComplete(25);

		activityID = getActivityID();

		setLaborActivityStatus("Completed");
		waitForExtJSAjaxComplete(25);

		clickLaborActivityFinancialTab();

		//check Override Product/Service
		clickOverrideProductService();

		waitForExtJSAjaxComplete(25);
		saveWorkOrder(getXWindowId("Edit Activity"),"Save");

		softAssert.assertTrue(getWarningDialogTextMsg().contains("Unable to save activity. Please make sure all mandatory fields are filled in"),"Warning message is displayed in the 'Financial Details' Tab, Activity is not saved");

		clickOnDialogButton("OK");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(isFieldHighlightedInActivityWin("Product or Service"),"Select the 'Product or Service' Lookup is highlighted");
		waitForExtJSAjaxComplete(5);
		
		setLaborProduct(product);
		saveAndCloseActivity();
		waitForExtJSAjaxComplete(15);

		checkRowInLaborTabGridForActivity(activityID);
		waitForExtJSAjaxComplete(10);

		clickEditLabor();
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(getActivityWindowFieldValues("statusCode").contains("Completed"),"Activity is saved");
		softAssert.assertEquals(DropDown.getComboValuesFromDropDownSelector(driver,getActivityStatusFieldID()),status,"The menu contains 'Completed' option only");
		softAssert.assertAll();
		Reporter.log("WO hierarchy extension is available via specified right and Activity status is changed to 'Booked' with appropriate User right are successfully verified", true);
	}
}
