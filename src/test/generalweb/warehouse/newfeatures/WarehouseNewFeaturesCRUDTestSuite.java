package test.generalweb.warehouse.newfeatures;

import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.generalweb.warehouse.AddWarehousePageObject;


public class WarehouseNewFeaturesCRUDTestSuite extends
		AddWarehousePageObject {
	
	
	/**
	 * Testcase ID: C90714
	 * @author vpcc
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testConfirmationMsgWarehouseDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:  Confirmation Message should be displayed when trying to delete one Warehouse or Multiple Warehouses from Overview: C90714 </span><br>", true);

		Reporter.log("User creates/edits warehouse <br>", true);
		
		String warehouse1 = "Central Warehouse";
		String warehouse2 =	"StockCorrectionWH";
		String warehouse3 =	"BlockTransactionWH";
		String warehouse4 =	"UnBlockTransactionWH" ;
		String commonmsgPart1 = "Are you sure you wish to delete Warehouse:";
		String commonmsgPart2 = "Are you sure you wish to delete the following Warehouses:";
		String warningMessageForOneWarehouse = "CENTRAL : Central Warehouse"; 
		String warningMessageForMultiWarehousePart1 = "- CENTRAL : Central Warehouse";
		String warningMessageForMultiWarehousePart2 = "- StockCorrectionWH : StockCorrectionWH";
		String warningMessageForMultiWarehousePart3 = "- BlockTransactionWH : BlockTransactionWH";
		String warningMessageForMultiWarehousePart4 = "- UnBlockTransactionWH : UnBlockTransactionWH";
			
		String alignment ="center";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testConfirmationMsgWarehouseDelete");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGrid("@class","x-grid3-viewport", warehouse1,"Reference");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouse1);
		
		clickToolBarDeleteButton();
		
		waitForExtJSAjaxComplete(20);
		
		String warningMsg = getWarningDialogTextMsg();
		
		softAssert.assertTrue((getWarningDialogTextMsg().contains(commonmsgPart1)&& getWarningDialogTextMsg().contains(warningMessageForOneWarehouse)), "Warehouse can't be deleted error message is otbained");
		
		softAssert.assertEquals(getWarningDialogTextAlignment(), alignment, "Confirmation Message in center alignment is displayed");
	
		clickOnDialogButton("No");
		
		waitForExtJSAjaxComplete(10);
		
		clickClearFilterButton();
		
		waitForExtJSAjaxComplete(20);
		
		sortColumnsAscDec("@class", "x-panel-body-noheader", "Reference", "Sort Ascending");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, warehouse3);
		
		waitForExtJSAjaxComplete(10);
	
		Grid.checkRowInGriByTextValue(driver, warehouse2);
		
		waitForExtJSAjaxComplete(10);
		
		sortColumnsAscDec("@class", "x-panel-body-noheader", "Reference", "Sort Descending");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, warehouse4);
		
		waitForExtJSAjaxComplete(5);
		
		clickToolBarDeleteButton();
		
		waitForExtJSAjaxComplete(20);
		
		System.err.println(getWarningDialogTextMsg());
		
		boolean iscommonMsgPresent = getWarningDialogTextMsg().contains(commonmsgPart2)&&getWarningDialogTextMsg().contains(warningMessageForMultiWarehousePart1)&&getWarningDialogTextMsg().contains(warningMessageForMultiWarehousePart2)
				&&getWarningDialogTextMsg().contains(warningMessageForMultiWarehousePart3)&&getWarningDialogTextMsg().contains(warningMessageForMultiWarehousePart4);
	
		softAssert.assertTrue(iscommonMsgPresent, "Warehouse can't be deleted error message is otbained for multiple warehouse deletion");
		softAssert.assertEquals(getWarningDialogTextAlignment(), alignment, "Confirmation Message in center alignment is displayed");
		
		clickOnDialogButton("No");
		
		softAssert.assertAll();
		
		Reporter.log(" Confirmation Message should be displayed when trying to delete one Warehouse or Multiple Warehouses from Overview", true);
	}

	/**
	 * Testcase ID: C90713,C90712
	 * @author vpcc
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testErrMsgNoCodeAndRefInStorageLocation() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Storage location without Reference should not be add in Warehouse and Error Message should displayed: C90712 </span><br>"
				+ "Test: Storage location without Code should not be add in Warehouse and Error Message should displayed: C90713 </span><br>", true);
		
		String warehouse1 = "Central Warehouse";
		String locReference = "WHRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String locCode = "WHCode"+getCurrentDate().substring(getCurrentDate().length()-5);
		String locReference2 = "WHRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String locCode2 = "WHCode"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testErrMsgNoCodeAndRefInStorageLocation");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
				
		waitForExtJSAjaxComplete(20);
		
		filterGrid("@class","x-grid3-viewport", warehouse1,"Reference");
		
		waitForExtJSAjaxComplete(10);
	
		Grid.checkRowInGriByTextValue(driver, warehouse1);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButton(); 
		
		waitForExtJSAjaxComplete(20);
		
		clickStorageLocationsTab();
		
		waitForExtJSAjaxComplete(10);

		this.clickAddStorageLocation();
		
		waitForExtJSAjaxComplete(20);
		
		this.setStorageLocation("0", "", locReference);
		
		waitForExtJSAjaxComplete(5);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getWarningMessageForStorageLocation("1"),"Please enter a Code");
		
		softAssert.assertEquals(getStorageLocationInfobarMessage(),"Hover your mouse over the marked lines to see the errors.");
		
		this.setStorageLocation("0", locCode, locReference);
		
		waitForExtJSAjaxComplete(5);
		
		save();

		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, locReference);
		
		waitForExtJSAjaxComplete(10);
		
		this.clickDeleteStorageLocation();
		
		waitForExtJSAjaxComplete(10);

		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		this.clickAddStorageLocation();
		
		waitForExtJSAjaxComplete(20);
		
		this.setStorageLocation("0", locCode2, "");
		
		waitForExtJSAjaxComplete(5);
		
		save();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getStorageLocationInfobarMessage(),"Hover your mouse over the marked lines to see the errors.");
		
		softAssert.assertEquals(getWarningMessageForStorageLocation("1"),"Please enter a Reference");
		
		this.setStorageLocation("0", locCode2, locReference2);
		
		waitForExtJSAjaxComplete(5);
		
		save();

		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, locReference);
		
		waitForExtJSAjaxComplete(10);
		
		this.clickDeleteStorageLocation();
		
		waitForExtJSAjaxComplete(10);

		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		softAssert.assertAll();
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Storage location without Reference could not be added in Warehouse and Error Message was displayed: C51888 </span><br>"
				+ "Storage location without Code could not be add in Warehouse and Error Message was displayed: C51890 </span><br>", true);
	}

	
	/**
	 * Testcase ID: C90715
	 * @author vpcc
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testProductAndStockUOMSameClass() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C90715:Product UOM should be Default for Product and Stock UOM could be change only within the Same Class", true);
		
		String warehouse1 = "GIStatusCheckWH";
		String locReference = "WHRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String locCode = "WHCode"+getCurrentDate().substring(getCurrentDate().length()-5);
		String locReference2 = "WHRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String locCode2 = "WHCode"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testProductAndStockUOMSameClass");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
				
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForMaskDisappear();
		
		filterGrid("@class","x-grid3-viewport", warehouse1,"Reference");
		
		waitForExtJSAjaxComplete(10);
	
		Grid.checkRowInGriByTextValue(driver, warehouse1);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButton(); 
		
		waitForExtJSAjaxComplete(20);
		
		clickStockItemsTab();
		
		waitForExtJSAjaxComplete(10);

		
		String []uomsExpected = {"dozen","gross","Person","piece"};
		
		List<String> uomValues = this.getStockUOMValues("1");
		
		softAssert.assertEqualsNoOrder(uomValues.toArray(), uomsExpected , "Stock UOM can change only within the same Class");
		
		softAssert.assertAll();
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "UOM option belonging to same class is displayed in list.  </span><br>"
				+ "UOM belonging to different classes is not displayed in list. Stock UOM can change only within the same Class.</span><br>", true);
		
		
	}
	
	
}
	
	

	




