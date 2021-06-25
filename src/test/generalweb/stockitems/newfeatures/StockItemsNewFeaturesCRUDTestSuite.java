package test.generalweb.stockitems.newfeatures;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.generalweb.purchaserequisitions.PurchaseRequisitionsPageObject;
import test.generalweb.stockitems.StockItemsPageObject;
import test.generalweb.warehouse.AddWarehousePageObject;

public class StockItemsNewFeaturesCRUDTestSuite extends StockItemsPageObject {

	
	/**
	 * TestCase ID : C90842
	 * Author : SSU 
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testPurchaseRequisitionCreationFromStockItemsOverview() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> C90842 :Verify Purchase Requisition (PR) creation functionality based on a selection of the Stock Item Overview</span><br>", true);
		
		waitForExtJSAjaxComplete(30);
		
		String warehouseRef = "PrPoTransactionWh";
		String referenceColName = "Reference";
		String availableStockColName = "Available Stock";
		String reservedStockColName = "Reserved Stock";
		String blockedStockColName = "Blocked Stock";
		String totalStockColName = "Total Stock";
		String productReference = "aqapreProdRef2";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseRef);

		AddWarehousePageObject addWarehousePageObject = new AddWarehousePageObject();
				
		addWarehousePageObject.clickEditButton(); 
				
		waitForExtJSAjaxComplete(10);
				
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
				
		waitForExtJSAjaxComplete(10);
				
		Grid.checkRowInGriByTextValue(driver, productReference);
				
		int availableStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),availableStockColName));
		int reservedStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),reservedStockColName));
		int totalStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),totalStockColName));
		int blockedStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),blockedStockColName));
				
		Reporter.log(productReference+" available stock quantity is "+availableStockValue, true);
		Reporter.log(productReference+" reserved stock quantity is "+reservedStockValue, true);
		Reporter.log(productReference+" blocked stock quantity is "+blockedStockValue, true);
		Reporter.log(productReference+" total stock quantity is "+totalStockValue, true);
				
		waitForExtJSAjaxComplete(10);
				
		addWarehousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Stock Items <br>", true);
		
		String requisitionDate = this.getFutureDate(0);
		String documentType = "AutomationQaPrRef";
		String requester = "SELENIUM";
		String purchasingOrganization = "aqaPOrgRef"; 
		String purchasingGrp = "aqaPOrgRef"; 
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testPurchaseRequisitionCreationFromStockItemsOverview");
		
		clickPurchaseRequisitionBtn();
		
		waitForExtJSAjaxComplete(5);
		
		String warningMsg = "Please select a Warehouse.";
		
		softAssert.assertTrue((getWarningDialogTextMsg().contains(warningMsg)), "Please Select a Warehous is Displayed");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(5);
		
		selectPRDocTypeFromDropDown(documentType);
		
		Grid.checkRowInGriByTextValue(driver, productReference);
		
		waitForExtJSAjaxComplete(5);
		
		clickPurchaseRequisitionBtn();
		
		waitForExtJSAjaxComplete(5);
		
		warningMsg = "Please select a Warehouse.";
		
		softAssert.assertTrue((getWarningDialogTextMsg().contains(warningMsg)), "Please Select a Warehous is Displayed");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(5);
		
		selectWarehouse(warehouseRef);
		
		//Reset Purchase requisition document type selection drop down
		
		selectPRDocTypeFromDropDown("");
		
		waitForExtJSAjaxComplete(10);
		
		selectPRDocTypeFromDropDown(documentType);
		
		Grid.checkRowInGriByTextValue(driver, productReference);
		
		waitForExtJSAjaxComplete(5);
		
		Reporter.log("Navigate to Purchase Requisitions <br>", true);
		
		clickPurchaseRequisitionBtn();
		
		waitForExtJSAjaxComplete(5);
		
		PurchaseRequisitionsPageObject purchaseRequisitionPO = new PurchaseRequisitionsPageObject();
		
		String documentTypeinPRform = purchaseRequisitionPO.getDocumentType(ADD_REQUISITION_WINDOW_HEADER);
		
		softAssert.assertEquals(documentTypeinPRform, documentType, "Document Type is filled in and is same as in Stock Items");
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPO.setRequisitionDate(requisitionDate, ADD_REQUISITION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPO.setRequester( "Last Name", requester, ADD_REQUISITION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, productReference, "@id", getXWindowId(ADD_REQUISITION_WINDOW_HEADER)), "Stock Items are automatically populated as PR Lines in PR Screen ");
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@id", getXWindowId(ADD_REQUISITION_WINDOW_HEADER), productReference);
		
		waitForExtJSAjaxComplete(5);
		
		Reporter.log("Selected Stock Items are automatically populated in PR Form " +productReference, true);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPO.clickTrackingTab(ADD_REQUISITION_WINDOW_HEADER); 
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPO.setPurchasingOrganization(referenceColName, purchasingOrganization, ADD_REQUISITION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPO.setPurchasingGroup(referenceColName, purchasingGrp, ADD_REQUISITION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPO.close(ADD_REQUISITION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		clickOnDialogButton("No");
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPO.save(ADD_REQUISITION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISITION_WINDOW_HEADER)), "Saved successfully");
		
		softAssert.assertAll();
		
		Reporter.log("Purchase Requisition(PR) is created Successfully based on a selection of the Stock Item from StockItems Overview <br>", true);
	}
}
