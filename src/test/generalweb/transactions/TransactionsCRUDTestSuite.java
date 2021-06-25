package test.generalweb.transactions;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.MenuSelector;
import test.generalweb.stockitems.StockItemsPageObject;
import test.generalweb.warehouse.AddWarehousePageObject;

public class TransactionsCRUDTestSuite extends TransactionsPageObject{
	
	/**
	 * Testcase ID	 : C90800,C90801,C35976
	 * Author		 : vpcc
	 * */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	
	public void testCreateGoodsReceiptTransaction() throws Exception {
	
		String transactionReference = "C35974Ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedDate = this.getFutureDate(3);
		String supplierName = "My Company";
		String wareHouseLoc = "HYD";
		String warehouseReference = "GRTransactionWH";
		String stockItemRef = "2preConsRef";
		String stockItemCode = "2preConsCod";
		String stockItemUOM = "pc";
		String nonStockItemRef = "7preProdRef";
		String nonStockItemProdCode = "7preProdCod";
		String nonStockItemUOM = "in³";
		String nonStockItemUOM122 = "\"³";
		String textLineQuantity = "10";
		String lineQuantity = "10";
		String textLineUOM = "piece";
		String purchasingOrg = "Default";
		String lookUpWindowCol = "Reference";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		String availableStockColName = "Available Stock";
		String totalStockColName="Total Stock";
		String remark = "C35974Rem"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		SoftAssert softAssert = new SoftAssert();
		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90800 :Create Goods Receipt Transaction <br>"
				+"C90801:Title: Goods Receipt transaction increases the available and total stock <br>"+
				"C35976:Verifying arrow symbol for goods receipt transactions <br> </span>", true);
		
		
		
		softAssert.setMethodName("testCreateGoodsReceiptTransaction");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");

		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValue = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),availableStockColName ));
		int totalStockValue = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),totalStockColName));
		
		Reporter.log(availableStockValue+" is available stock quantity", true);
		
		Reporter.log(totalStockValue+" is total stock quantity", true);
		
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		waitForExtJSAjaxComplete(20);
		
		selectTransactionType("Goods Receipt");
		
		waitForExtJSAjaxComplete(20);
		
		setWareHouse("Reference", warehouseReference, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		setReference( transactionReference, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		setStatus("Planned",GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER );
		
		setTransactionDate(expectedDate, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectSupplier("Name", supplierName, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		//***************************Non Stock items transaction************************
		clickAddTransactionLines();
		
		MenuSelector.selectMenuItemNative(driver, "Non-Stock");
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineProductReferenceFromProductsAndServices("1", nonStockItemRef,lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		String nonStocLineProdCode = this.getProductCodeOfTransactionLine("1");
		
		String nonStockUOM = this.getUOMOfTransactionLine("1");

		softAssert.assertTrue(nonStocLineProdCode.equals(nonStockItemProdCode), "Product code is prepopulated for Stock item line");
		
		//12.2 UOM code is displayed differently 
		boolean isNonStockItemUOM = (nonStockUOM.equals(nonStockItemUOM)||nonStockUOM.equals(nonStockItemUOM122));
		
		softAssert.assertTrue(isNonStockItemUOM, "UOM is prepopulated for Stock item line");
		
		String nonStockLineStatus = getStatusOfTransactionLine("1");
		
		softAssert.assertTrue(nonStockLineStatus.equals("Planned"), "Non-Stock line with Planned status is added");
	
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineQuantity("1",  lineQuantity);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineLocationFromAllLocations("1",  wareHouseLoc, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);

		setPurchasingOrgForTransactionLine("1",  purchasingOrg, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Non-Stock line is added", true);
		
		//***************************Stock items transaction************************
		clickAddTransactionLines();

		MenuSelector.selectMenuItemNative(driver, "Stock");

		waitForExtJSAjaxComplete(20);

		setTransactionLineProductReferenceFromProductsAndServices("2", stockItemRef, lookUpWindowCol);

		waitForExtJSAjaxComplete(20);

		String stocLineProdCode = getProductCodeOfTransactionLine("2");

		String stockUOM = getUOMOfTransactionLine("2");

		softAssert.assertTrue(stocLineProdCode.equals(stockItemCode), "Product code is prepopulated for Stock item line");

		softAssert.assertTrue(stockUOM.equals(stockItemUOM), "UOM is prepopulated for Stock item line");
		
		String stockLineStatus = getStatusOfTransactionLine("2");
		
		softAssert.assertTrue(stockLineStatus.equals("Planned"), "Stock line with Planned status is added");

		waitForExtJSAjaxComplete(20);

		setTransactionLineQuantity("2",  lineQuantity);

		waitForExtJSAjaxComplete(20);

		setTransactionLineLocationFromAllLocations("2",  wareHouseLoc, lookUpWindowCol);

		waitForExtJSAjaxComplete(20);

		setPurchasingOrgForTransactionLine("2",  purchasingOrg, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
	
		setTransactionLineRemark("2",  remark);
		
		Reporter.log("Stock line is added", true);
		
		//***************************Text transaction *************************
		clickAddTransactionLines();
		
		MenuSelector.selectMenuItemNative(driver, "Text");
		
		waitForExtJSAjaxComplete(25);
		
		setProductReferenceForTextLine("3",  stockItemRef);
		
		waitForExtJSAjaxComplete(20);
		
		setProductCodeForTextLine("3",  stockItemCode);
		
		waitForExtJSAjaxComplete(20);
		
		String textLineStatus = getStatusOfTransactionLine("3");
		
		softAssert.assertTrue(textLineStatus.equals("Planned"), "Text line with Planned status is added");
		
		setTransactionLineQuantity("3",  textLineQuantity);
		
		waitForExtJSAjaxComplete(20);
		
		setUOMForTextLine("3", textLineUOM , lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		setLocationForTextLine("3",  wareHouseLoc, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		setPurchasingOrgForTransactionLine("3",  purchasingOrg, lookUpWindowCol);
		
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		setStatus("Executed",GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER );
		
		waitForExtJSAjaxComplete(20);
		
		saveAndClose(GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		filterGrid("@id", getFilterGridID(), remark, "Remark");
		
		WebElement quantityEle = getQuantityOfTransactionLine("@id", getFilterGridID(),remark, "Remark");
		
		String quantityIcon = this.getQuantityIcon(quantityEle);
		
		softAssert.assertTrue(quantityIcon.equals("up arrow"),"Quantity icon for Transaction is Up Arrow");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValuePostTxn = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),availableStockColName ));
		int totalStockValuePostTxn = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),totalStockColName));

		softAssert.assertTrue((availableStockValue+Integer.parseInt(lineQuantity))==availableStockValuePostTxn , "Available Stock has increased by "+lineQuantity+" units");
		softAssert.assertTrue((totalStockValue+Integer.parseInt(lineQuantity))==totalStockValuePostTxn , "Stock has increased by "+lineQuantity+" units");
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		softAssert.assertAll();
		
		Reporter.log("Transaction with Stock, Non-Stock and Text lines is created ",  true);
		Reporter.log("Available and total stock are increased by"+lineQuantity+" units.",  true);
		
		
	}

	/**
	 * Testcase ID	 : C90793,C90795,C90799,C90779
	 * Author		 : SSU
	 * */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testCreateGoodsIssueTransaction() throws Exception {
	
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create Goods Issue Transaction: C90793 </span><br>", true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Goods issue transaction decreases the available and total stock: C90795 </span><br>", true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Verifying arrow symbol for goods issue transactions: C90799 </span><br>", true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: In Overview, open Goods issue from New Transaction Tool bar : C90779 </span><br>", true);
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Note down the Available and Total Stock in Warehouse <br>", true);
		
		//Variable Initialization
		String warehouseReference = "GoodsIssueWH";
		String availableStock = "Available Stock";
		String totalStock = "Total Stock";
		String prodReference = "4preConsRef";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValueAndClickCustomized(driver, warehouseReference);

		AddWarehousePageObject addWarehousePageObject = new AddWarehousePageObject();
		
		addWarehousePageObject.clickEditButton(); 
		
		waitForExtJSAjaxComplete(10);
		
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, prodReference);
		
		int availableStockValue = Integer.parseInt(addWarehousePageObject.getStockItemRowValue(availableStock));
		
		int totalStockValue = Integer.parseInt(addWarehousePageObject.getStockItemRowValue(totalStock));
		
		waitForExtJSAjaxComplete(10);
		
		addWarehousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Create a New Goods Issue Transaction <br>", true);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testCreateGoodsIssueTransaction");
		
		//Variable Initialization
		String transactionReference = "C35977Ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedDate = this.getFutureDate(3);
		String customerName = "My Company";
		String collectingPerson = "Vorobets";
		String wareHouseLoc = "IND";
		String stockItemRef = "4preConsRef";
		String stockItemCode = "4preConsCod";
		String lineQuantity = "1";
		String textLineUOM = "pc";
		String lookUpWindowCol = "Reference";
		String remarkTransactionLine1 = "rem"+getCurrentDate().substring(getCurrentDate().length()-5);
		String remarkTransactionLine2 = "rem2"+getCurrentDate().substring(getCurrentDate().length()-5);
		String lotTransactionLine = "lot";
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		waitForExtJSAjaxComplete(20);
			
		clickNewGoodsIssueToolBarButton();
		
		waitForExtJSAjaxComplete(10);
		
		setWareHouse("Reference", warehouseReference, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		setReference( transactionReference, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionDate(expectedDate, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		selectCustomer("Customer Name", customerName, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		selectCollectingPerson("Last Name", collectingPerson, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		//***************************Stock items transaction Line 1************************
		clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineProductReferenceFromProductsAndServices("1", stockItemRef, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(10);
		
		String stockLineProdCode1 = this.getProductCodeOfTransactionLine("1");
		
		String stockUOM1 = this.getUOMOfTransactionLine("1");
		
		softAssert.assertEquals(stockLineProdCode1, stockItemCode, "Product Code is displayed in Transaction Line1");

		softAssert.assertEquals(stockUOM1, textLineUOM, "UOM is displayed in Transaction Line1");
	
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("1", lineQuantity);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineLocationFromAllLocations("1", wareHouseLoc, lookUpWindowCol);
		
		setTransactionLineRemark("1", remarkTransactionLine1);
		
		setTransactionLineLot("1", lotTransactionLine);
		
		waitForExtJSAjaxComplete(10);

		Reporter.log("Stock line 1 is added", true);
		
		//***************************Stock items transaction Line 2************************
		clickAddTransactionLines();

		waitForExtJSAjaxComplete(20);

		setTransactionLineProductReferenceFromProductsAndServices("2", stockItemRef, lookUpWindowCol);

		waitForExtJSAjaxComplete(10);

		String stockLineProdCode2 = getProductCodeOfTransactionLine("2");

		String stockUOM2 = getUOMOfTransactionLine("2");

		softAssert.assertEquals(stockLineProdCode2, stockItemCode, "Product Code is displayed in Transaction Line2");

		softAssert.assertEquals(stockUOM2, textLineUOM, "UOM is displayed in Transaction Line2");

		waitForExtJSAjaxComplete(5);

		setTransactionLineQuantity("2", lineQuantity);
		
		waitForExtJSAjaxComplete(5);

		setTransactionLineLocationFromAllLocations("2", wareHouseLoc, lookUpWindowCol);
		
		setTransactionLineRemark("2", remarkTransactionLine2);
		
		setTransactionLineLot("2", lotTransactionLine);

		waitForExtJSAjaxComplete(10);
		
		Reporter.log("Stock line 2 is added", true);
		
		//*****************Verifying status in Transaction Line**********************
		
		String statusTransactionLine1 = getStatusOfTransactionLine("1");
		String statusTransactionLine2 = getStatusOfTransactionLine("2");
		String statusPlannedAndExecuted = "Reserved";
		String statusRequested = "Requested";
		
		setStatus("Requested", GOODS_ISSUE_TRANSACTION_WINDOW_HEADER );
		
		statusTransactionLine1 = getStatusOfTransactionLine("1");
		
		statusTransactionLine2 = getStatusOfTransactionLine("2");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertEquals(statusTransactionLine1, statusRequested, "Requested Status is Displayed in Transaction Line");
		
		softAssert.assertEquals(statusTransactionLine2, statusRequested, "Requested Status is Displayed in Transaction Line");
		
		setStatus("Planned", GOODS_ISSUE_TRANSACTION_WINDOW_HEADER );
		
		statusTransactionLine1 = getStatusOfTransactionLine("1");
		
		statusTransactionLine2 = getStatusOfTransactionLine("2");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertEquals(statusTransactionLine1, statusPlannedAndExecuted, "Reserved Status is Displayed in Transaction Line");
		
		softAssert.assertEquals(statusTransactionLine2, statusPlannedAndExecuted, "Reserved Status is Displayed in Transaction Line");
		
		waitForExtJSAjaxComplete(5);
				
		setStatus("Executed", GOODS_ISSUE_TRANSACTION_WINDOW_HEADER );
		
		statusTransactionLine1 = getStatusOfTransactionLine("1");
		
		statusTransactionLine2 = getStatusOfTransactionLine("2");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(statusTransactionLine1, statusPlannedAndExecuted, "Reserved Status is Displayed in Transaction Line");
		
		softAssert.assertEquals(statusTransactionLine2, statusPlannedAndExecuted, "Reserved Status is Displayed in Transaction Line");
		
		saveAndClose(GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		Reporter.log("Goods Issue Transaction is Succesfully Created with 2 Stock item Transaction Lines", true);
		
		waitForExtJSAjaxComplete(10);

		//Filter Transactions Grid with Remark
		filterGrid("@id", getFilterGridID(), remarkTransactionLine1, "Remark");
		  
		WebElement quantityEle = getQuantityOfTransactionLine("@realid", "mogrid", remarkTransactionLine1, "Remark");
		  
		String quantityIcon = this.getQuantityIcon(quantityEle);
		
		//Verify the Quantity Icon is down arrow 
		softAssert.assertTrue(quantityIcon.equals("down arrow"),"Quantity icon for Transaction is down Arrow");
		
		waitForExtJSAjaxComplete(10);
		
		//Filter Transactions Grid with Remark
		filterGrid("@id", getFilterGridID(), remarkTransactionLine2, "Remark");
		
		WebElement quantityEle2 = getQuantityOfTransactionLine("@realid", "mogrid", remarkTransactionLine2, "Remark");
		  
		String quantityIcon2 = this.getQuantityIcon(quantityEle2);
		
		//Verify the Quantity Icon is down arrow 
		softAssert.assertTrue(quantityIcon2.equals("down arrow"),"Quantity icon for Transaction is down Arrow");
		
		Reporter.log("Goods issue transaction Quantity Icon is Down Arrow", true);
		
		waitForExtJSAjaxComplete(10);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(10);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(10);
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
		
		addWarehousePageObject.clickEditButton(); 
		
		waitForExtJSAjaxComplete(10);
		
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, prodReference);
		
		waitForExtJSAjaxComplete(5);
		
		int availableStockAfterGoodsIssue = Integer.parseInt(addWarehousePageObject.getStockItemRowValue(availableStock));
		
		int totalStockAfterGoodsIssue = Integer.parseInt(addWarehousePageObject.getStockItemRowValue(totalStock));
		
		//Verify Stock items are reduced after Goods issue Transaction
		softAssert.assertNotEquals(availableStockAfterGoodsIssue, availableStockValue, "Available Stock quantity is Reduced");
		
		softAssert.assertNotEquals(totalStockAfterGoodsIssue, totalStockValue, "Total Stock is Reduced");
		
		softAssert.assertAll();
		
		Reporter.log("Goods issue transaction decreases the available and total stock", true);
	}
	
	
	/**
	 * Testcase ID	 : C90819, C90820
	 * Author		 : SSA
	 * */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void createUnBlockTransaction() throws Exception{
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90819:Create Unblock Transaction </span><br>"
				+"C90820: message should be displayed if the Quantity Exceeds the Blocked quantity in warehouse, while unblock <br> </span>", true);
	

		Reporter.log("Create Unblock Transaction <br>", true);
	
		String remark1 = "UnBlockRemark1"+getCurrentDate().substring(getCurrentDate().length()-5);
		String remark2 = "UnBlockRemark2"+getCurrentDate().substring(getCurrentDate().length()-5);;;
		String wareHouseLoc = "HYD";//+getCurrentDate().substring(getCurrentDate().length()-5);
		String warehouseReference = "UnBlockTransactionWH";
	    String stockItemRef1 = "2preConsRef";
		String stockItemRef2 = "6preProdRef";
		String lineQuantity = "10";
		String lookUpWindowCol = "Reference";
	    String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		String availableStockColName = "Available Stock";
		String totalStockColName="Total Stock";
		String blockedStockColName="Blocked Stock";
		int intLineQuantity = Integer.parseInt(lineQuantity);
		  
		
		SoftAssert softAssert = new SoftAssert();
	    AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
		
		softAssert.setMethodName("createUnBlockTransaction");
		
		expandAdministration();
		  
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		  
		waitForExtJSAjaxComplete(20);
		  
	    waitAndClick(XPATH_WAREHOUSES);
		  
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");
		
		waitForExtJSAjaxComplete(20);
		  
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		wareHousePageObject.clickEditButton();
		  
		waitForExtJSAjaxComplete(20);
		  
		wareHousePageObject.clickStockItemsTab();
		  
		waitForExtJSAjaxComplete(20);
		  
		int availableStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),availableStockColName ));
		int totalStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),totalStockColName));
		int blockStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),blockedStockColName));
		  
		  
		int availableStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),availableStockColName ));
		int totalStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),totalStockColName));
		int blockStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),blockedStockColName));
		  
		Reporter.log(stockItemRef1+" available stock quantity is "+availableStockValue1, true);
		Reporter.log(stockItemRef1+" total stock quantity is "+totalStockValue1, true);
		Reporter.log(stockItemRef1+" blocked stock quantity is "+blockStockValue1, true);
		  
		Reporter.log(stockItemRef2+" available stock quantity is "+availableStockValue2, true);
		Reporter.log(stockItemRef2+" total stock quantity is "+totalStockValue2, true);
		Reporter.log(stockItemRef2+" blocked stock quantity is "+blockStockValue2, true);
		  
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		  
		waitForExtJSAjaxComplete(20);
		  
		waitAndClick(XPATH_TRANSACTIONS);
		  
		waitForExtJSAjaxComplete(20);
		  
		this.selectTransactionType("Unblock");
		  
		waitForExtJSAjaxComplete(20);
		  
		this.setWareHouse(lookUpWindowCol, warehouseReference, UNBLOCK_TRANSACTION_WINDOW_HEADER);
		  
		waitForExtJSAjaxComplete(20);
		  
		//**************************Line 1******************************************************
		  
		this.clickAddTransactionLines();
		  
		waitForExtJSAjaxComplete(20);
		  
		setTransactionLineProductReferenceFromProductsAndServices("1", stockItemRef1,lookUpWindowCol);
		  
		waitForExtJSAjaxComplete(20);
		  
		setTransactionLineQuantity("1", ""+(blockStockValue1+1));
		  
		waitForExtJSAjaxComplete(20);
		  
		setTransactionLineLocationFromAllLocations("1",  wareHouseLoc, lookUpWindowCol);
		  
		waitForExtJSAjaxComplete(20);
		  
		setTransactionLineRemark("1", remark1);
		  
		waitForExtJSAjaxComplete(20);
		  
		//**************************Line 2******************************************************
		  
		this.clickAddTransactionLines();
		  
		waitForExtJSAjaxComplete(20);
		  
		setTransactionLineProductReferenceFromProductsAndServices("2", stockItemRef2,lookUpWindowCol);
		  
		waitForExtJSAjaxComplete(20);
		  
		setTransactionLineQuantity("2", ""+(blockStockValue2+1));
		  
		waitForExtJSAjaxComplete(20);
		  
		setTransactionLineLocationFromAllLocations("2",  wareHouseLoc, lookUpWindowCol);
		  
		waitForExtJSAjaxComplete(20);
		  
		setTransactionLineRemark("2", remark2);
		  
		waitForExtJSAjaxComplete(20);
		  
		execute();
		  
		String warningMsg = this.getWarningDialogTextMsg();
		  
		softAssert.assertTrue(warningMsg.contains("Line 1: The Quantity cannot be higher than the Blocked Stock."), "User can't enter Unblock quantity more than Blocked stock");
		softAssert.assertTrue(warningMsg.contains("Line 2: The Quantity cannot be higher than the Blocked Stock."), "User can't enter Unblock quantity more than Blocked stock");
		  
		waitForExtJSAjaxComplete(20);
		  
		this.clickOnDialogButton("OK");
		  
		setTransactionLineQuantity("1", lineQuantity);
	
		waitForExtJSAjaxComplete(20);
		  
		setTransactionLineQuantity("2", lineQuantity);
		  
		waitForExtJSAjaxComplete(20);
		  
		setTransactionLineRemark("1", remark1);
		  
		waitForExtJSAjaxComplete(20);
		  
		execute();
		  
		waitForExtJSAjaxComplete(20);
		  
		waitAndClick(XPATH_WAREHOUSES);
		  
	    waitForExtJSAjaxComplete(20);
		  
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		wareHousePageObject.clickEditButton();
		  
		waitForExtJSAjaxComplete(20);
		  
		wareHousePageObject.clickStockItemsTab();
		  
		waitForExtJSAjaxComplete(20);
		  
		int availableStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),availableStockColName ));
		int totalStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),totalStockColName));
		int blockStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),blockedStockColName));
		  
		int availableStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),availableStockColName ));
		int totalStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),totalStockColName));
		int blockStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),blockedStockColName));
		  
		//*****************************Line 1 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn1==(availableStockValue1+intLineQuantity)), stockItemRef1+" available stock quantity is increased by "+lineQuantity+"units");
		softAssert.assertTrue((blockStockValuePostTxn1 ==(blockStockValue1-intLineQuantity)), stockItemRef1+" blocked stock quantity is decreased by "+lineQuantity+"units");
		softAssert.assertTrue((totalStockValuePostTxn1==totalStockValue1), stockItemRef1+" Total stock quantity is unchanged");
		  
		//*****************************Line 2 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn2==(availableStockValue2+intLineQuantity)), stockItemRef2+" available stock quantity is increased by "+lineQuantity+"units");
		softAssert.assertTrue((blockStockValuePostTxn2 ==(blockStockValue2-intLineQuantity)), stockItemRef2+" blocked stock quantity is decreased by "+lineQuantity+"units");
		softAssert.assertTrue((totalStockValuePostTxn2==totalStockValue2), stockItemRef2+" Total stock quantity is unchanged");
		  
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		  
		softAssert.assertAll();
		
		Reporter.log("A message is displayed if the Quantity Exceeds the Blocked quantity in warehouse, while Unblocking <br>"
				+ "Created a UnBlock Transaction. UnBlock Transaction successfully Unblocked by X units of blocked stock <br>"
				+ "by increasing available stock and decreasing block stock by X Units. Total Stock is unaffected by UnBlock transaction <br>",true);
	}
	
	
	/**
	 * Testcase ID	 : C90815,C90816
	 * Author		 : vpcc
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void createBlockTransaction() throws Exception{
		
		String remark1 = "BlockRemark1"+getCurrentDate().substring(getCurrentDate().length()-5);
		String remark2 = "BlockRemark2"+getCurrentDate().substring(getCurrentDate().length()-5);
		String wareHouseLoc = "HYD";
		String warehouseReference = "BlockTransactionWH";
		String stockItemRef1 = "2preConsRef";
		String stockItemRef2 = "6preProdRef";
		String lineQuantity = "10";
		String lookUpWindowCol = "Reference";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		String availableStockColName = "Available Stock";
		String totalStockColName="Total Stock";
		String blockedStockColName="Blocked Stock";
		int intLineQuantity = Integer.parseInt(lineQuantity);
		
		SoftAssert softAssert = new SoftAssert();
		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90815:Create Block Transaction  <br>"+
	 	"C90816: A message should be displayed if the Quantity Exceeds the available quantity in warehouse, while blocking <br></span>", true); 
		
		softAssert.setMethodName("createBlockTransaction");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),availableStockColName ));
		int totalStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),totalStockColName));
		int blockStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),blockedStockColName));
		
		
		int availableStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),availableStockColName ));
		int totalStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),totalStockColName));
		int blockStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),blockedStockColName));
		
		Reporter.log(stockItemRef1+" available stock quantity is "+availableStockValue1, true);
		Reporter.log(stockItemRef1+" total stock quantity is "+totalStockValue1, true);
		Reporter.log(stockItemRef1+" blocked stock quantity is "+blockStockValue1, true);
		
		Reporter.log(stockItemRef2+" available stock quantity is "+availableStockValue2, true);
		Reporter.log(stockItemRef2+" total stock quantity is "+totalStockValue2, true);
		Reporter.log(stockItemRef2+" blocked stock quantity is "+blockStockValue2, true);
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		waitForExtJSAjaxComplete(20);
		
		this.selectTransactionType("Block");
		
		waitForExtJSAjaxComplete(20);
		
		this.setWareHouse(lookUpWindowCol, warehouseReference, BLOCK_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		//**************************Line 1******************************************************
		
		this.clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineProductReferenceFromProductsAndServices("1", stockItemRef1,lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineQuantity("1", ""+(availableStockValue1+1));
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineLocationFromAllLocations("1",  wareHouseLoc, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineRemark("1", remark1);
		
		waitForExtJSAjaxComplete(20);
		
		
		//**************************Line 2******************************************************
		
		this.clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineProductReferenceFromProductsAndServices("2", stockItemRef2,lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineQuantity("2", ""+(availableStockValue2+1));
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineLocationFromAllLocations("2",  wareHouseLoc, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineRemark("2", remark2);
		
		waitForExtJSAjaxComplete(20);
		
		execute();
		
		String warningMsg = this.getWarningDialogTextMsg();
		
		softAssert.assertTrue(warningMsg.contains("Line 1: The Quantity cannot be higher than the Available Stock."), "User can't enter block quantity more than available stock");
		softAssert.assertTrue(warningMsg.contains("Line 2: The Quantity cannot be higher than the Available Stock."), "User can't enter block quantity more than available stock");
		
		this.clickOnDialogButton("OK");
		
		setTransactionLineQuantity("1", lineQuantity);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineQuantity("2", lineQuantity);
		
		waitForExtJSAjaxComplete(20);
		
		execute();
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),availableStockColName ));
		int totalStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),totalStockColName));
		int blockStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),blockedStockColName));
		
		int availableStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),availableStockColName ));
		int totalStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),totalStockColName));
		int blockStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),blockedStockColName));
		
		//*****************************Line 1 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn1==(availableStockValue1-intLineQuantity)), stockItemRef1+" available stock quantity is decreased by "+lineQuantity+"units");
		softAssert.assertTrue((blockStockValuePostTxn1 ==(blockStockValue1+intLineQuantity)), stockItemRef1+" blocked stock quantity is increased by "+lineQuantity+"units");
		softAssert.assertTrue((totalStockValuePostTxn1==totalStockValue1), stockItemRef1+" Total stock quantity is unchanged");
		
		//*****************************Line 2 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn2==(availableStockValue2-intLineQuantity)), stockItemRef2+" available stock quantity is decreased by "+lineQuantity+"units");
		softAssert.assertTrue((blockStockValuePostTxn2 ==(blockStockValue2+intLineQuantity)), stockItemRef2+" blocked stock quantity is increased by "+lineQuantity+"units");
		softAssert.assertTrue((totalStockValuePostTxn2==totalStockValue2), stockItemRef2+" Total stock quantity is unchanged");
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		softAssert.assertAll();
		
		Reporter.log("A message is displayed if the Block Quantity Exceeds the available quantity in warehouse, while blocking <br>"+
				"Created a Block Transaction. Block Transaction successfully blocked X units of Available stock <br>"+ 
				"by increasing block stock by X Units. Total Stock is unaffected by Block transaction <br>", true); 
	
	}	
		
	
	

	/**
	 * Testcase ID	 : C90832,C90833,C90834
	 * Author		 : vpcc
	 */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	
	public void testCreateStockScrappingTransaction() throws Exception {
		
		String transactionReference = "C35984Ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedDate = this.getFutureDate(0);
		String supplierName = "My Company";
		String wareHouseLoc1 = "HYD";
		String wareHouseLoc2 = "VJA";
		String warehouseReference = "SCTransactionWH";
		String stockItemUOM = "pc";
		String purchasingOrg = "Default";
		String collectingPerson="SELENIUM";
		String stockItemRef1 = "2preConsRef";
		String stockItemRef2 = "6preProdRef";
		String lineQuantity = "10";
		String lookUpWindowCol = "Reference";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		int intLineQuantity = Integer.parseInt(lineQuantity);
		
		String availableStockColName = "Available Stock";
		String totalStockColName="Total Stock";
		String blockedStockColName="Blocked Stock";
		String reservedStock="Reserved Stock";
		String remark1 = "C35984Remark1"+getCurrentDate().substring(getCurrentDate().length()-5);
		String remark2 = "C35984Remark2"+getCurrentDate().substring(getCurrentDate().length()-5);
		SoftAssert softAssert = new SoftAssert();
		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90832: Create Stock Scrapping Transaction  <br>"
				+"C90833 :A message should be displayed when Quantity Exceeds the blocked stock, while stock scrapping <br>"+
				"C90834 :Verifying arrow symbol for stock scrapping transactions <br> </span>", true);
		
		softAssert.setMethodName("testCreateStockScrappingTransaction");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),availableStockColName ));
		int totalStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),totalStockColName));
		int blockStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),blockedStockColName));
		int reservedStockValue1  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),reservedStock));
		
		int availableStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),availableStockColName ));
		int totalStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),totalStockColName));
		int blockStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),blockedStockColName));
		int reservedStockValue2  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),reservedStock));
		
		
		Reporter.log(stockItemRef1+" available stock quantity is "+availableStockValue1, true);
		Reporter.log(stockItemRef1+" total stock quantity is "+totalStockValue1, true);
		Reporter.log(stockItemRef1+" blocked stock quantity is "+blockStockValue1, true);
		Reporter.log(stockItemRef1+" reserved stock quantity is "+reservedStockValue1, true);
		
		Reporter.log(stockItemRef2+" available stock quantity is "+availableStockValue2, true);
		Reporter.log(stockItemRef2+" total stock quantity is "+totalStockValue2, true);
		Reporter.log(stockItemRef2+" blocked stock quantity is "+blockStockValue2, true);
		Reporter.log(stockItemRef2+" reserved stock quantity is "+reservedStockValue2, true);
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		waitForExtJSAjaxComplete(20);
		
		this.selectTransactionType("Stock Scrapping");
		
		waitForExtJSAjaxComplete(20);
		
		this.setWareHouse(lookUpWindowCol, warehouseReference, STOCK_SCRAPPING_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		setReference( transactionReference, STOCK_SCRAPPING_TRANSACTION_WINDOW_HEADER);
		
		setTransactionDate(expectedDate, STOCK_SCRAPPING_TRANSACTION_WINDOW_HEADER);
		
		selectCollectingPerson("Last Name", collectingPerson, STOCK_SCRAPPING_TRANSACTION_WINDOW_HEADER);
		//**************************Line 1******************************************************
		
		this.clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineProductReferenceFromProductsAndServices("1", stockItemRef1,lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineQuantity("1", ""+(blockStockValue1+1));
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineLocationFromAllLocations("1",  wareHouseLoc1, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineRemark("1", remark1);
		
		waitForExtJSAjaxComplete(5);
		
		
		//**************************Line 2******************************************************
		
		this.clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineProductReferenceFromProductsAndServices("2", stockItemRef2,lookUpWindowCol);
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineQuantity("2", ""+(blockStockValue2+1));
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineLocationFromAllLocations("2",  wareHouseLoc2, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineRemark("2", remark2);
		
		waitForExtJSAjaxComplete(2);
		
		save();
		
		String warningMsg = this.getWarningDialogTextMsg();
		
		//TODO: Line number's aren't populated in warning message. Logged bug: 
		//Change the message accordingly once issue WO: 29532 is fixed
		softAssert.assertTrue(warningMsg.contains("There is not enough blocked stock."), "User can't enter quantity more than block stock");
		//softAssert.assertTrue(warningMsg.contains("Line 1: The Quantity cannot be higher than the Available Stock."), "User can't enter block quantity more than available stock");
		//softAssert.assertTrue(warningMsg.contains("Line 2: The Quantity cannot be higher than the Available Stock."), "User can't enter block quantity more than available stock");
		
		this.clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineRemark("1", remark1);
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineQuantity("1", lineQuantity);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineRemark("2", remark2);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("2", lineQuantity);
		
		waitForExtJSAjaxComplete(2);
		
		saveAndClose(STOCK_SCRAPPING_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@id", getFilterGridID(), remark1, "Remark");
		
		WebElement quantityEle = getQuantityOfTransactionLine("@id", getFilterGridID(),remark1, "Remark");
		
		String quantityIcon = this.getQuantityIcon(quantityEle);
		
		softAssert.assertTrue(quantityIcon.equals("down arrow"),"Quantity icon for Transaction is Up Arrow");
		
		waitForExtJSAjaxComplete(10);
		
		filterGrid("@id", getFilterGridID(), remark2, "Remark");
		
		quantityEle = getQuantityOfTransactionLine("@id", getFilterGridID(),remark2, "Remark");
		
		quantityIcon = this.getQuantityIcon(quantityEle);
		
		softAssert.assertTrue(quantityIcon.equals("down arrow"),"Quantity icon for Transaction is Up Arrow");
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		waitForExtJSAjaxComplete(10);
		
		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		
		int availableStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),availableStockColName ));
		int totalStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),totalStockColName));
		int blockStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),blockedStockColName));
		int reservedStockPostTxn1  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),reservedStock));
		
		int availableStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),availableStockColName ));
		int totalStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),totalStockColName));
		int blockStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),blockedStockColName));
		int reservedStockPostTxn2  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),reservedStock));
		
		//*****************************Line 1 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn1==availableStockValue1), stockItemRef1+" available stock quantity is unchanged by this transaction");
		softAssert.assertTrue((reservedStockPostTxn1==reservedStockValue1), stockItemRef1+" reserved stock quantity is unchanged by this transaction");
		softAssert.assertTrue((blockStockValuePostTxn1 ==(blockStockValue1-intLineQuantity)), stockItemRef1+" blocked stock quantity is decreased by "+lineQuantity+"units");
		softAssert.assertTrue((totalStockValuePostTxn1==(totalStockValue1-intLineQuantity)), stockItemRef1+" Total stock quantity is decreased by "+lineQuantity+"units");
		
		//*****************************Line 2 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn2==availableStockValue2), stockItemRef2+" available stock quantity is unchanged by this transaction");
		softAssert.assertTrue((reservedStockPostTxn2==reservedStockValue2), stockItemRef2+" reserved stock quantity is unchanged by this transaction");
		softAssert.assertTrue((blockStockValuePostTxn2 ==(blockStockValue2-intLineQuantity)), stockItemRef2+" blocked stock quantity is decreased by "+lineQuantity+"units");
		softAssert.assertTrue((totalStockValuePostTxn2==(totalStockValue2-intLineQuantity)), stockItemRef2+" Total stock quantity is decreased by "+lineQuantity+"units");
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		softAssert.assertAll();
		
		Reporter.log("A message is displayed if the Line Quantity Exceeds the blocked quantity in warehouse, while scrapping <br>"+
				"Created a Stock scrapping Transaction. Transaction successfully scrapped X units of Blocked stock <br>"+ 
				"by decreasing blocked and total stock by X Units. Available and Reserved Stock is unaffected by transaction <br>", true); 
		
		
	}
	
	/**
	 * Testcase ID	 : C90806,C90807,C90808
	 * Author		 : ssu
	 * */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testCreateReturnFromCustomerTransaction() throws Exception {
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create Return From Customer Transaction: C90806 </span><br>"
				+ "Test:  Return From Customer transaction increases the blocked and total stock: C90807 </span><br>"
				+ "Test: Verifying arrow symbol for return from customer transactions: C90808 </span><br>", true);
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Note down the Blocked and Total Stock in Warehouse <br>", true);
		
		//Variable Initialization
		String warehouseReference = "ReturnFromCustomerWH";
		String blockedStockColName = "Blocked Stock";
		String totalStockColName = "Total Stock";
		String availableStockColName = "Available Stock";
		String reservedStockColName = "Reserved Stock";
		String prodReference = "6preProdRef";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		AddWarehousePageObject addWarehousePageObject = new AddWarehousePageObject();
		
		addWarehousePageObject.clickEditButton(); 
		
		waitForExtJSAjaxComplete(10);
		
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(10);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", prodReference, "Reference");

		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, prodReference);
		
		int blockedStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),blockedStockColName));
		int totalStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),totalStockColName));
		int availableStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),availableStockColName));
		int reservedStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),reservedStockColName));
		
		Reporter.log(prodReference+" available stock quantity is "+availableStockValue, true);
		Reporter.log(prodReference+" reserved stock quantity is "+reservedStockValue, true);
		Reporter.log(prodReference+" blocked stock quantity is "+blockedStockValue, true);
		Reporter.log(prodReference+" total stock quantity is "+totalStockValue, true);
		
		waitForExtJSAjaxComplete(10);
		
		addWarehousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Create a Return From Customer Transaction <br>", true);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testCreateReturnFromCustomerTransaction");
		
		//Variable Initialization
		String transactionReference = "C35998Ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String returnDate = this.getFutureDate(3);
		String customerName = "My Company";
		String returnedBy = "SELENIUM";
		String wareHouseLoc = "IND";
		String stockItemRef = "6preProdRef";
		String stockItemCode = "6preProdCod";
		String stockItemUOM = "in³";
		String stockItemUOM122 = "\"³";
		String lineQuantity = "1";
		String lookUpWindowCol = "Reference";
		String remarkTransactionLine1 = "rem"+getCurrentDate().substring(getCurrentDate().length()-5);
		String remarkTransactionLine2 = "rem2"+getCurrentDate().substring(getCurrentDate().length()-5);
		String lotTransactionLine = "lot";
		int intLineQuantity = Integer.parseInt(lineQuantity);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		waitForExtJSAjaxComplete(20);
			
		selectTransactionType("Goods Return");
		
		waitForExtJSAjaxComplete(10);
		
		setWareHouse("Reference", warehouseReference, RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER);
		
		setReference( transactionReference, RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER);
		
		setTransactionDate(returnDate, RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER);
		
		selectCustomer("Customer Name", customerName, RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER);
		
		selectCollectingPerson("Last Name", returnedBy, RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER);
		
		//***************************Stock items transaction Line 1************************
		clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineProductReferenceFromProductsAndServices("1", stockItemRef, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(10);
		
		String stockLineProdCode1 = this.getProductCodeOfTransactionLine("1");
		
		String stockUOM1 = this.getUOMOfTransactionLine("1");
		
		softAssert.assertEquals(stockLineProdCode1, stockItemCode, "Product Code is displayed in Transaction Line1");

		//12.2 UOM code is displayed differently 
		boolean isStockItemUOM = (stockUOM1.equals(stockItemUOM)||stockUOM1.equals(stockItemUOM122));
				
		softAssert.assertTrue(isStockItemUOM, "UOM is prepopulated for Stock item line");
	
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("1", lineQuantity);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineLocationFromAllLocations("1", wareHouseLoc, lookUpWindowCol);
		
		setTransactionLineRemark("1", remarkTransactionLine1);
		
		setTransactionLineLot("1", lotTransactionLine);
		
		waitForExtJSAjaxComplete(10);

		Reporter.log("Stock line 1 is added", true);
		
		//***************************Stock items transaction Line 2************************
		clickAddTransactionLines();

		waitForExtJSAjaxComplete(20);

		setTransactionLineProductReferenceFromProductsAndServices("2", stockItemRef, lookUpWindowCol);

		waitForExtJSAjaxComplete(10);

		String stockLineProdCode2 = getProductCodeOfTransactionLine("2");

		String stockUOM2 = getUOMOfTransactionLine("2");

		softAssert.assertEquals(stockLineProdCode2, stockItemCode, "Product Code is displayed in Transaction Line2");

		//12.2 UOM code is displayed differently 
		boolean isStockItemUOM1 = (stockUOM2.equals(stockItemUOM)||stockUOM2.equals(stockItemUOM122));
						
		softAssert.assertTrue(isStockItemUOM1, "UOM is prepopulated for Stock item line");

		waitForExtJSAjaxComplete(5);

		setTransactionLineQuantity("2", lineQuantity);
		
		waitForExtJSAjaxComplete(5);

		setTransactionLineLocationFromAllLocations("2", wareHouseLoc, lookUpWindowCol);
		
		setTransactionLineRemark("2", remarkTransactionLine2);
		
		setTransactionLineLot("2", lotTransactionLine);

		waitForExtJSAjaxComplete(10);
		
		Reporter.log("Stock line 2 is added", true);
		
		//*****************Verifying status in Transaction Line**********************
		
		String statusTransactionLine1 = getStatusOfTransactionLine("1");
		String statusTransactionLine2 = getStatusOfTransactionLine("2");
		String statusPlanned = "Planned";
		String statusExecuted = "Executed";
				
		softAssert.assertEquals(statusTransactionLine1, statusPlanned, "Planned Status is Displayed in Transaction Line");
		
		softAssert.assertEquals(statusTransactionLine2, statusPlanned, "Planned Status is Displayed in Transaction Line");
		
		waitForExtJSAjaxComplete(5);
				
		setStatus("Executed", RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER );
		
		waitForExtJSAjaxComplete(5);
		
		save();
		
		waitForExtJSAjaxComplete(10);
		
		statusTransactionLine1 = getStatusOfTransactionLine("1");
		
		statusTransactionLine2 = getStatusOfTransactionLine("2");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(statusTransactionLine1, statusExecuted, "Executed Status is Displayed in Transaction Line");
		
		softAssert.assertEquals(statusTransactionLine2, statusExecuted, "Executed Status is Displayed in Transaction Line");
		
		close(RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		Reporter.log("Return From Customer Transaction is Succesfully Created with 2 Stock item Transaction Lines", true);
		
		expandSubMainMenu("Stock");
		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_TRANSACTIONS);
		waitForExtJSAjaxComplete(20);
					
		//Filter Transactions Grid with Remark
		filterGrid("@id", getFilterGridID(), remarkTransactionLine1, "Remark");
		  
		WebElement quantityEle = getQuantityOfTransactionLine("@realid", "mogrid", remarkTransactionLine1, "Remark");
		  
		String quantityIcon = this.getQuantityIcon(quantityEle);
		
		//Verify the Quantity Icon is up arrow 
		softAssert.assertTrue(quantityIcon.equals("up arrow"),"Quantity icon for Transaction is up Arrow");
		
		waitForExtJSAjaxComplete(10);
		
		//Filter Transactions Grid with Remark
		filterGrid("@id", getFilterGridID(), remarkTransactionLine2, "Remark");
		
		WebElement quantityEle2 = getQuantityOfTransactionLine("@realid", "mogrid", remarkTransactionLine2, "Remark");
		  
		String quantityIcon2 = this.getQuantityIcon(quantityEle2);
		
		//Verify the Quantity Icon is up arrow 
		softAssert.assertTrue(quantityIcon2.equals("up arrow"),"Quantity icon for Transaction is up Arrow");
		
		Reporter.log("Return From Customer transaction Quantity Icon is up Arrow", true);
		
		driver.navigate().refresh();
		waitForExtJSAjaxComplete(20);
		
		expandAdministration();
		waitForExtJSAjaxComplete(20);
				
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(5);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(10);
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
		
		addWarehousePageObject.clickEditButton(); 
		
		waitForExtJSAjaxComplete(10);
		
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(10);
		
		int blockStockValuePostTxn1 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),blockedStockColName));
		int totalStockValuePostTxn1 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),totalStockColName));
		int availableStockValuePostTxn1 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),availableStockColName));
		int reservedStockValuePostTxn1 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),reservedStockColName));
		
		softAssert.assertTrue((blockStockValuePostTxn1==(blockedStockValue+(intLineQuantity*2))), stockItemRef+" blocked stock quantity is increased by "+lineQuantity+"units");
		softAssert.assertTrue((totalStockValuePostTxn1==(totalStockValue+(intLineQuantity*2))), stockItemRef+" Total stock quantity is increased by "+lineQuantity+"units");
		softAssert.assertTrue((availableStockValuePostTxn1==availableStockValue), stockItemRef+ " Available stock quantity is unchanged");
		softAssert.assertTrue((reservedStockValuePostTxn1==reservedStockValue), stockItemRef+ " Reserved stock quantity is unchanged");
		
		softAssert.assertAll();
		
		Reporter.log("Return From Customer Increases the blocked and total stock", true);
	}
	
	
	
	/**
	 * Testcase ID	 : C90953,C90954
	 * Author		 : vpcc
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testcreateStockTransferTransaction() throws Exception{
		
		String remark1 = "STTxnRemark1"+getCurrentDate().substring(getCurrentDate().length()-5);
		String remark2 = "STTxnRemark2"+getCurrentDate().substring(getCurrentDate().length()-5);
		String wareHouseLoc = "HYD";
		String warehouseReference1 = "STTransactionWH";
		String warehouseReference2 = "STTransactionWH1";
		String stockItemRef1 = "2preConsRef";
		String stockItemRef2 = "6preProdRef";
		String lineQuantity = "10";
		String lookUpWindowCol = "Reference";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		String availableStockColName = "Available Stock";
		String totalStockColName="Total Stock";
		String blockedStockColName="Blocked Stock";
		String reservedStock="Reserved Stock";
		
		int intLineQuantity = Integer.parseInt(lineQuantity);
		String stockItemUOM1 = "pc";
		String stockItemCode1 = "2preConsCod";
		String stockItemUOM2 = "in³";
		String stockItem2UOM122 = "\"³";
		String stockItemProdCode2="6preProdCod";
		
		
		String wareHouseLoc1 = "HYD";
		String wareHouseLoc2 = "VJA";
		
		SoftAssert softAssert = new SoftAssert();
		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90953:Create Stock Transfer Transaction  <br>"+
	 	"C90954:A message should be displayed when Quantity Exceeds the available stock in source warehouse, while stock transfering <br></span>", true); 
		
		softAssert.setMethodName("testcreateStockTransferTransaction");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference1, "Reference");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueExactMatch(driver, warehouseReference1);

		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("2preConsRef"),availableStockColName ));
		int totalStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("2preConsRef"),totalStockColName));
		int blockStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("2preConsRef"),blockedStockColName));
		int reservedStockValue1  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("2preConsRef"),reservedStock));
		
		int availableStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("6preProdRef"),availableStockColName ));
		int totalStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("6preProdRef"),totalStockColName));
		int blockStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("6preProdRef"),blockedStockColName));
		int reservedStockValue2  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("6preProdRef"),reservedStock));
		
		Reporter.log(stockItemRef1+" available stock quantity is "+availableStockValue1+"in "+warehouseReference1+"warehouse", true);
		Reporter.log(stockItemRef1+" total stock quantity is "+totalStockValue1+"in "+warehouseReference1+"warehouse", true);
		Reporter.log(stockItemRef1+" blocked stock quantity is "+blockStockValue1+"in "+warehouseReference1+"warehouse", true);
		Reporter.log(stockItemRef1+" Reserved stock quantity is "+reservedStockValue1+"in "+warehouseReference1+"warehouse", true);
		
		Reporter.log(stockItemRef2+" available stock quantity is "+availableStockValue2+"in "+warehouseReference1+"warehouse", true);
		Reporter.log(stockItemRef2+" total stock quantity is "+totalStockValue2+"in "+warehouseReference1+"warehouse", true);
		Reporter.log(stockItemRef2+" blocked stock quantity is "+blockStockValue2+"in "+warehouseReference1+"warehouse", true);
		Reporter.log(stockItemRef2+" Reserved stock quantity is "+reservedStockValue2+"in "+warehouseReference1+"warehouse", true);
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		//Uncheck previous selected rows in Grid
		wareHousePageObject.clearFilterGridSelection();
		
		filterGrid("@realid", "mogrid", warehouseReference2, "Reference");
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference2);

		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValue1InWH2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("2preConsRef"),availableStockColName ));
		int totalStockValue1InWH2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("2preConsRef"),totalStockColName));
		int blockStockValue1InWH2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("2preConsRef"),blockedStockColName));
		int reservedStockValue1InWH2  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("2preConsRef"),reservedStock));
		
		int availableStockValue2InWH2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("6preProdRef"),availableStockColName ));
		int totalStockValue2InWH2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("6preProdRef"),totalStockColName));
		int blockStockValue2InWH2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("6preProdRef"),blockedStockColName));
		int reservedStockValue2InWH2  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("6preProdRef"),reservedStock));
		
		Reporter.log(stockItemRef1+" available stock quantity is "+availableStockValue1InWH2+"in "+warehouseReference2+"warehouse", true);
		Reporter.log(stockItemRef1+" total stock quantity is "+totalStockValue1InWH2+"in "+warehouseReference2+"warehouse", true);
		Reporter.log(stockItemRef1+" blocked stock quantity is "+blockStockValue1InWH2+"in "+warehouseReference2+"warehouse", true);
		Reporter.log(stockItemRef1+" Reserved stock quantity is "+reservedStockValue1InWH2+"in "+warehouseReference2+"warehouse", true);
		
		Reporter.log(stockItemRef2+" available stock quantity is "+availableStockValue2InWH2+"in "+warehouseReference2+"warehouse", true);
		Reporter.log(stockItemRef2+" total stock quantity is "+totalStockValue2InWH2+"in "+warehouseReference2+"warehouse", true);
		Reporter.log(stockItemRef2+" blocked stock quantity is "+blockStockValue2InWH2+"in "+warehouseReference2+"warehouse", true);
		Reporter.log(stockItemRef2+" Reserved stock quantity is "+reservedStockValue2InWH2+"in "+warehouseReference2+"warehouse", true);
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		waitForExtJSAjaxComplete(20);
		
		this.selectTransactionType("Stock Transfer");
		
		waitForExtJSAjaxComplete(20);
		
		this.setWareHouse(lookUpWindowCol, warehouseReference1, STOCK_TRANSFER_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		this.setDestinationWareHouse(lookUpWindowCol, warehouseReference2, STOCK_TRANSFER_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		//**************************Line 1******************************************************
		
		this.clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineProductReferenceFromProductsAndServices("1", stockItemRef1,lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineQuantity("1", ""+(availableStockValue1+1));
		
		waitForExtJSAjaxComplete(20);
		
		String stockUOM1 = this.getUOMOfTransactionLine("1");
		
		softAssert.assertEquals(stockUOM1, stockItemUOM1, "UOM is displayed in Transaction Line1");
		
		setTransactionLineLocationFromAllLocations("1",  wareHouseLoc1, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineDestinationLocationFromAllLocations("1",  wareHouseLoc1, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineRemark("1", remark1);
		
		waitForExtJSAjaxComplete(20);
		
		
		//**************************Line 2******************************************************
		
		this.clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineProductReferenceFromProductsAndServices("2", stockItemRef2,lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		String stockLineUOM2 = this.getUOMOfTransactionLine("2");

		//12.2 UOM code is displayed differently 
		boolean isStockItemUOM2 = (stockLineUOM2.equals(stockItemUOM2)||stockLineUOM2.equals(stockItemProdCode2));
		
		softAssert.assertTrue(isStockItemUOM2, "UOM is prepopulated for second transaction line");
		setTransactionLineQuantity("2", ""+(availableStockValue2+1));
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineLocationFromAllLocations("2",  wareHouseLoc2, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineDestinationLocationFromAllLocations("2",  wareHouseLoc2, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineRemark("2", remark2);
		
		waitForExtJSAjaxComplete(20);
		
		execute();
		
		waitForExtJSAjaxComplete(20);
		
		String warningMsg = this.getWarningDialogTextMsg();
		
		softAssert.assertTrue(warningMsg.contains("Line 1: The Quantity cannot be higher than the Available Stock."), "User can't enter block quantity more than available stock");
		waitForExtJSAjaxComplete(20);
		softAssert.assertTrue(warningMsg.contains("Line 2: The Quantity cannot be higher than the Available Stock."), "User can't enter block quantity more than available stock");
		waitForExtJSAjaxComplete(20);
		this.clickOnDialogButton("OK");
		
		setTransactionLineQuantity("1", lineQuantity);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineQuantity("2", lineQuantity);
		
		waitForExtJSAjaxComplete(20);
		
		execute();
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference2);

		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(10);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(10);
		
		int availableStockValuePostTxn1WH2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("2preConsRef"),availableStockColName ));
		int totalStockValuePostTxn1WH2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("2preConsRef"),totalStockColName));
		int blockStockValuePostTxn1WH2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("2preConsRef"),blockedStockColName));
		int reservedStockPostTxn1WH2  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("2preConsRef"),reservedStock));
		
		int availableStockValuePostTxn2WH2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("6preProdRef"),availableStockColName ));
		int totalStockValuePostTxn2WH2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("6preProdRef"),totalStockColName));
		int blockStockValuePostTxn2WH2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("6preProdRef"),blockedStockColName));
		int reservedStockPostTxn2WH2  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("6preProdRef"),reservedStock));
		
		Reporter.log(stockItemRef1+" available stock quantity is "+availableStockValuePostTxn1WH2+"in "+warehouseReference2+"warehouse", true);
		Reporter.log(stockItemRef1+" total stock quantity is "+totalStockValuePostTxn1WH2+"in "+warehouseReference2+"warehouse", true);
		Reporter.log(stockItemRef1+" blocked stock quantity is "+blockStockValuePostTxn1WH2+"in "+warehouseReference2+"warehouse", true);
		Reporter.log(stockItemRef1+" Reserved stock quantity is "+reservedStockPostTxn1WH2+"in "+warehouseReference2+"warehouse", true);
		
		Reporter.log(stockItemRef2+" available stock quantity is "+availableStockValuePostTxn2WH2+"in "+warehouseReference2+"warehouse", true);
		Reporter.log(stockItemRef2+" total stock quantity is "+totalStockValuePostTxn2WH2+"in "+warehouseReference2+"warehouse", true);
		Reporter.log(stockItemRef2+" blocked stock quantity is "+blockStockValuePostTxn2WH2+"in "+warehouseReference2+"warehouse", true);
		Reporter.log(stockItemRef2+" Reserved stock quantity is "+reservedStockPostTxn2WH2+"in "+warehouseReference2+"warehouse", true);
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		//Uncheck previous selected rows in Grid
		wareHousePageObject.clearFilterGridSelection();

		driver.navigate().refresh();
		waitForExtJSAjaxComplete(20);
		
		expandAdministration();
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_WAREHOUSES);
		waitForExtJSAjaxComplete(20);
		
		filterGrid("@realid", "mogrid", warehouseReference1, "Reference");
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueExactMatch(driver, warehouseReference1);

		waitForExtJSAjaxComplete(10);
		
		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("2preConsRef"),availableStockColName ));
		int totalStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("2preConsRef"),totalStockColName));
		int blockStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("2preConsRef"),blockedStockColName));
		int reservedStockPostTxn1  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("2preConsRef"),reservedStock));
		
		int availableStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("6preProdRef"),availableStockColName ));
		int totalStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("6preProdRef"),totalStockColName));
		int blockStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("6preProdRef"),blockedStockColName));
		int reservedStockPostTxn2  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse("6preProdRef"),reservedStock));
		//*****************************Line 1 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn1==(availableStockValue1-intLineQuantity)), stockItemRef1+" available stock quantity is decreased by "+lineQuantity+"units in "+warehouseReference1+"warehouse");
		softAssert.assertTrue((totalStockValuePostTxn1 ==(totalStockValue1-intLineQuantity)), stockItemRef1+" total stock quantity decreased by "+lineQuantity+"units in "+warehouseReference1+"warehouse");
		softAssert.assertTrue((blockStockValuePostTxn1==blockStockValue1), stockItemRef1+" Blocked stock quantity is unchanged in "+warehouseReference1+"warehouse");
		softAssert.assertTrue((reservedStockPostTxn1==reservedStockValue1), stockItemRef1+" reserved stock quantity is unchanged by this transaction  in "+warehouseReference1+"warehouse");
		//*****************************Line 2 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn2==(availableStockValue2-intLineQuantity)), stockItemRef2+" available stock quantity is decreased by "+lineQuantity+"units  in "+warehouseReference1+"warehouse");
		softAssert.assertTrue((totalStockValuePostTxn2 ==(totalStockValue2-intLineQuantity)), stockItemRef2+" total stock quantity decreased by "+lineQuantity+"units  in "+warehouseReference1+"warehouse");
		softAssert.assertTrue((blockStockValuePostTxn2 ==blockStockValue2), stockItemRef2+" Blocked stock quantity is unchanged  in "+warehouseReference1+"warehouse");
		softAssert.assertTrue((reservedStockPostTxn2==reservedStockValue2), stockItemRef2+" reserved stock quantity is unchanged by this transaction  in "+warehouseReference1+"warehouse");
	
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		

		//*****************************Line 1 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn1WH2==(availableStockValue1InWH2+intLineQuantity)), stockItemRef1+" available stock quantity is decreased by "+lineQuantity+"units in "+warehouseReference1+"warehouse");
		softAssert.assertTrue((totalStockValuePostTxn1WH2 ==(totalStockValue1InWH2+intLineQuantity)), stockItemRef1+" total stock quantity decreased by "+lineQuantity+"units in "+warehouseReference1+"warehouse");
		softAssert.assertTrue((blockStockValuePostTxn1WH2==blockStockValue1InWH2), stockItemRef1+" Blocked stock quantity is unchanged in "+warehouseReference1+"warehouse");
		softAssert.assertTrue((reservedStockPostTxn1WH2==reservedStockValue1InWH2), stockItemRef1+" reserved stock quantity is unchanged by this transaction  in "+warehouseReference1+"warehouse");
		
		//*****************************Line 2 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn2WH2==(availableStockValue2InWH2+intLineQuantity)), stockItemRef2+" available stock quantity is decreased by "+lineQuantity+"units  in "+warehouseReference1+"warehouse");
		softAssert.assertTrue((totalStockValuePostTxn2WH2 ==(totalStockValue2InWH2+intLineQuantity)), stockItemRef2+" total stock quantity decreased by "+lineQuantity+"units  in "+warehouseReference1+"warehouse");
		softAssert.assertTrue((blockStockValuePostTxn2WH2 ==blockStockValue2InWH2), stockItemRef2+" Blocked stock quantity is unchanged  in "+warehouseReference1+"warehouse");
		softAssert.assertTrue((reservedStockPostTxn2WH2==reservedStockValue2InWH2), stockItemRef2+" reserved stock quantity is unchanged by this transaction  in "+warehouseReference1+"warehouse");
		
		softAssert.assertAll();
		
		Reporter.log("A message is displayed when Quantity Exceeds the available stock in source warehouse, while stock transfering <br>"+
				"Created Stock Transfer Transaction. Stock transfer Transaction successfully transfered X units of Available stock from "+warehouseReference1+"to "+warehouseReference2+"<br>"+ 
				"thereby decreasing Available and Total stock by X Units in "+warehouseReference1+" and increasing Available and Total stock by same amount in"+ warehouseReference2+"<br>"
				+"Reserved and Blocked Stock is unaffected by this transaction in both Warehouses <br>", true); 
	
	}
	
	
	/**
	 * Testcase ID	 : C90810,C90811,C90812,C90813
	 * Author		 : vpcc
	 */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	
	public void testCreateReturnToSupplierTransaction() throws Exception {
		
		String transactionReference = "C35984Ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedDate = this.getFutureDate(0);
		String supplierName = "My Company";
		String wareHouseLoc = "HYD";
		String warehouseReference = "RTSTransactionWH";
		String stockItemUOM1 = "pc";
		String collectingPerson="SELENIUM";
		String stockItemRef1 = "2preConsRef";
		String stockItemRef2 = "6preProdRef";
		String lineQuantity = "10";
		String lookUpWindowCol = "Reference";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		int intLineQuantity = Integer.parseInt(lineQuantity);
		String stockItemCode1 = "2preConsCod";
		String stockItemProdCode2 = "6preProdCod";
		String stockItemUOM2 = "in³";
		String stockItemUOM122 = "\"³";
		
		String availableStockColName = "Available Stock";
		String totalStockColName="Total Stock";
		String blockedStockColName="Blocked Stock";
		String reservedStock="Reserved Stock";
		
		String remark1 = "C35998Remark1"+getCurrentDate().substring(getCurrentDate().length()-5);
		String remark2 = "C35998Remark2"+getCurrentDate().substring(getCurrentDate().length()-5);
		SoftAssert softAssert = new SoftAssert();
		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
		

		
				
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90810:Create Return To Supplier Transaction  <br>"
				+"C90811:A Message should be displayed if the Quantity Exceeds the blocked quantity in warehouse, while returning to supplier.<br>"
				+"C90812:1. An area of responsibility is selected from overview tab. 2. Stock item is linked to atleast one warehouse and some quantity is blocked.  <br>"		
				+"C90813:Verifying arrow symbol for return to supplier transactions <br> </span>", true);
		
		softAssert.setMethodName("testCreateReturnToSupplierTransaction");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),availableStockColName ));
		int totalStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),totalStockColName));
		int blockStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),blockedStockColName));
		int reservedStockValue1  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),reservedStock));
		
		int availableStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),availableStockColName ));
		int totalStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),totalStockColName));
		int blockStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),blockedStockColName));
		int reservedStockValue2  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),reservedStock));
		
		
		Reporter.log(stockItemRef1+" available stock quantity is "+availableStockValue1, true);
		Reporter.log(stockItemRef1+" total stock quantity is "+totalStockValue1, true);
		Reporter.log(stockItemRef1+" blocked stock quantity is "+blockStockValue1, true);
		Reporter.log(stockItemRef1+" reserved stock quantity is "+reservedStockValue1, true);
		
		Reporter.log(stockItemRef2+" available stock quantity is "+availableStockValue2, true);
		Reporter.log(stockItemRef2+" total stock quantity is "+totalStockValue2, true);
		Reporter.log(stockItemRef2+" blocked stock quantity is "+blockStockValue2, true);
		Reporter.log(stockItemRef2+" reserved stock quantity is "+reservedStockValue2, true);
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		waitForExtJSAjaxComplete(20);
		
		selectTransactionType("Return to Supplier");
		
		waitForExtJSAjaxComplete(20);
		
		setWareHouse(lookUpWindowCol, warehouseReference, RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		setReference( transactionReference, RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER);
		
		setTransactionDate(expectedDate, RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER);
		
		selectSupplier("Name", supplierName, RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER);
		
		selectCollectingPerson("Last Name", collectingPerson, RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER);
		//**************************Line 1******************************************************
		
		clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineProductReferenceFromProductsAndServices("1", stockItemRef1,lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		String line1ProdCode = this.getProductCodeOfTransactionLine("1");
		
		String line1StockUOM = this.getUOMOfTransactionLine("1");

		softAssert.assertTrue(line1ProdCode.equals(stockItemCode1), "Product code is prepopulated for Stock item line");
		
		softAssert.assertTrue(line1StockUOM.equals(stockItemUOM1), "UOM is prepopulated for Stock item line");
		
		setTransactionLineQuantity("1", ""+(blockStockValue1+1));
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineLocationFromAllLocations("1",  wareHouseLoc, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineRemark("1", remark1);
		
		waitForExtJSAjaxComplete(5);
		
		
		//**************************Line 2******************************************************
		
		this.clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineProductReferenceFromProductsAndServices("2", stockItemRef2,lookUpWindowCol);
		
		waitForExtJSAjaxComplete(10);
		
		String line2ProdCode = this.getProductCodeOfTransactionLine("2");
		
		String line2StockUOM = this.getUOMOfTransactionLine("2");

		softAssert.assertTrue(line2ProdCode.equals(stockItemProdCode2), "Product code is prepopulated for Stock item line");
		
		//12.2 UOM code is displayed differently 
		boolean isStockItemUOMPopulated = (line2StockUOM.equals(stockItemUOM2)||line2StockUOM.equals(stockItemUOM122));
				
		softAssert.assertTrue(isStockItemUOMPopulated, "UOM is prepopulated for Stock item line");
				
		softAssert.assertTrue(line1StockUOM.equals(stockItemUOM1), "UOM is prepopulated for Stock item line");
		
		setTransactionLineQuantity("2", ""+(blockStockValue2+1));
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineLocationFromAllLocations("2",  wareHouseLoc, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineRemark("2", remark2);
		
		waitForExtJSAjaxComplete(2);
		
		save();
		
		String warningMsg = this.getWarningDialogTextMsg();
		
		//TODO: Line number's aren't populated in warning message. Logged bug: 
		//Change the message accordingly once issue WO: 29532 is fixed
		softAssert.assertTrue(warningMsg.contains("There is not enough blocked stock."), "User can't enter quantity more than block stock");
		//softAssert.assertTrue(warningMsg.contains("Line 1: The Quantity cannot be higher than the Available Stock."), "User can't enter block quantity more than available stock");
		//softAssert.assertTrue(warningMsg.contains("Line 2: The Quantity cannot be higher than the Available Stock."), "User can't enter block quantity more than available stock");
		
		this.clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineRemark("1", remark1);
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineQuantity("1", lineQuantity);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineRemark("2", remark2);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("2", lineQuantity);
		
		waitForExtJSAjaxComplete(2);
		
		save();
		
		setStatus("Executed", RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER );
		
		saveAndClose(RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		filterGrid("@id", getFilterGridID(), remark1, "Remark");
		
		WebElement quantityEle = getQuantityOfTransactionLine("@id", getFilterGridID(),remark1, "Remark");
		
		String quantityIcon = this.getQuantityIcon(quantityEle);
		
		softAssert.assertTrue(quantityIcon.equals("down arrow"),"Quantity icon for Transaction is down Arrow");
		
		waitForExtJSAjaxComplete(10);
		
		filterGrid("@id", getFilterGridID(), remark2, "Remark");
		
		quantityEle = getQuantityOfTransactionLine("@id", getFilterGridID(),remark2, "Remark");
		
		quantityIcon = this.getQuantityIcon(quantityEle);
		
		softAssert.assertTrue(quantityIcon.equals("down arrow"),"Quantity icon for Transaction is down Arrow");
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		waitForExtJSAjaxComplete(10);
		
		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		
		int availableStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),availableStockColName ));
		int totalStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),totalStockColName));
		int blockStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),blockedStockColName));
		int reservedStockPostTxn1  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),reservedStock));
		
		int availableStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),availableStockColName ));
		int totalStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),totalStockColName));
		int blockStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),blockedStockColName));
		int reservedStockPostTxn2  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),reservedStock));
		
		//*****************************Line 1 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn1==availableStockValue1), stockItemRef1+" available stock quantity is unchanged by this transaction");
		softAssert.assertTrue((reservedStockPostTxn1==reservedStockValue1), stockItemRef1+" reserved stock quantity is unchanged by this transaction");
		softAssert.assertTrue((blockStockValuePostTxn1 ==(blockStockValue1-intLineQuantity)), stockItemRef1+" blocked stock quantity is decreased by "+lineQuantity+"units");
		softAssert.assertTrue((totalStockValuePostTxn1==(totalStockValue1-intLineQuantity)), stockItemRef1+" Total stock quantity is decreased by "+lineQuantity+"units");
		
		//*****************************Line 2 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn2==availableStockValue2), stockItemRef2+" available stock quantity is unchanged by this transaction");
		softAssert.assertTrue((reservedStockPostTxn2==reservedStockValue2), stockItemRef2+" reserved stock quantity is unchanged by this transaction");
		softAssert.assertTrue((blockStockValuePostTxn2 ==(blockStockValue2-intLineQuantity)), stockItemRef2+" blocked stock quantity is decreased by "+lineQuantity+"units");
		softAssert.assertTrue((totalStockValuePostTxn2==(totalStockValue2-intLineQuantity)), stockItemRef2+" Total stock quantity is decreased by "+lineQuantity+"units");
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		softAssert.assertAll();
		
		Reporter.log("A message is displayed if the Line Quantity Exceeds the blocked quantity in warehouse, while returning to supplier <br>"+
				"Created a Return to Supplier Transaction. Transaction successfully returns X units of Blocked stock <br>"+ 
				"by decreasing blocked and total stock by X Units. Available and Reserved Stock is unaffected by transaction <br>", true); 
		
		
	}
	
	/**
	 * Testcase ID	 : C90797, C90798, C90794
	 * Author		 : ssu
	 * */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testGoodsIssueIncreasesReservedStock() throws Exception{
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90797:Planned Goods Issue transaction increases the reserved stock and decreases the available stock </span><br>"
				+"C90798: Cancelled Goods Issue transaction increases the available stock and decreases the reserved stock <br> </span>"
				+"C90794: A Message should be displayed if the Quantity Exceeds the Available Quantity in Warehouse, while issuing goods <br> </span>", true);
		
		waitForExtJSAjaxComplete(30);

		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Note down the Available, Reserved, blocked and Total Stock in Warehouse <br>", true);
		
		//Variable Initialization
		String warehouseReference = "GITransactionWH";
		String availableStockColName = "Available Stock";
		String reservedStockColName = "Reserved Stock";
		String blockedStockColName = "Blocked Stock";
		String totalStockColName = "Total Stock";
		String prodReference = "2preConsRef";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		sortColumnsAscDec("@class", "x-panel-body-noheader", "Reference", "Sort Ascending");
		
		waitForExtJSAjaxComplete(10);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");

		waitForExtJSAjaxComplete(10);
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		AddWarehousePageObject addWarehousePageObject = new AddWarehousePageObject();
		
		addWarehousePageObject.clickEditButton(); 
		
		waitForExtJSAjaxComplete(10);
		
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, prodReference);
		
		int availableStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),availableStockColName));
		int reservedStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),reservedStockColName));
		int totalStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),totalStockColName));
		int blockedStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),blockedStockColName));
		
		Reporter.log(prodReference+" available stock quantity is "+availableStockValue, true);
		Reporter.log(prodReference+" reserved stock quantity is "+reservedStockValue, true);
		Reporter.log(prodReference+" blocked stock quantity is "+blockedStockValue, true);
		Reporter.log(prodReference+" total stock quantity is "+totalStockValue, true);
		
		waitForExtJSAjaxComplete(10);
		
		addWarehousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testGoodsIssueIncreasesReservedStockTransaction");
		
		//Variable Initialization
		String transactionReference = "C35981Ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedDate = this.getFutureDate(3);
		String customerName = "My Company";
		String collectingPerson = "SELENIUM";
		String wareHouseLoc = "IND";
		String stockItemRef = "2preConsRef";
		String stockItemCode = "2preConsCod";
		String stockItemRef2 = "6preProdRef";
		String stockItemCode2 = "6preProdCod";
		String lineQuantity = "10";
		String valueGreaterThanAvlQty = "2500";
		String stockItemUOM = "pc";
		String stockItemUOM2 = "in³";
		String stockItemUOM122 = "\"³";
		String lookUpWindowCol = "Reference";
		String remarkTransactionLine1 = "rem"+getCurrentDate().substring(getCurrentDate().length()-5);
		String remarkTransactionLine2 = "rem2"+getCurrentDate().substring(getCurrentDate().length()-5);
		String lotTransactionLine = "lot";
		int intLineQuantity = Integer.parseInt(lineQuantity);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		waitForExtJSAjaxComplete(20);
			
		clickNewGoodsIssueToolBarButton();
		
		waitForExtJSAjaxComplete(10);
		
		setWareHouse("Reference", warehouseReference, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		setReference( transactionReference, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		setTransactionDate(expectedDate, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		selectCustomer("Customer Name", customerName, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		selectCollectingPerson("Last Name", collectingPerson, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		//***************************Stock items transaction Line 1************************
		clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineProductReferenceFromProductsAndServices("1", stockItemRef, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(10);
		
		String stockLineProdCode1 = this.getProductCodeOfTransactionLine("1");
		
		String stockUOM1 = this.getUOMOfTransactionLine("1");
		
		softAssert.assertEquals(stockLineProdCode1, stockItemCode, "Product Code is displayed in Transaction Line1");

		//12.2 UOM code is displayed differently 
		boolean isStockItemUOM1 = (stockUOM1.equals(stockItemUOM)||stockUOM1.equals(stockItemUOM122));
										
		softAssert.assertTrue(isStockItemUOM1, "UOM is prepopulated for Stock item line");

		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("1", valueGreaterThanAvlQty);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineLocationFromAllLocations("1", wareHouseLoc, lookUpWindowCol);
		
		setTransactionLineRemark("1", remarkTransactionLine1);
		
		setTransactionLineLot("1", lotTransactionLine);
		
		waitForExtJSAjaxComplete(10);
		
		Reporter.log("Stock line 1 is added", true);
		
		//***************************Stock items transaction Line 2************************
		clickAddTransactionLines();

		waitForExtJSAjaxComplete(20);

		setTransactionLineProductReferenceFromProductsAndServices("2", stockItemRef2, lookUpWindowCol);

		waitForExtJSAjaxComplete(10);

		String stockLineProdCode2 = getProductCodeOfTransactionLine("2");

		String stockUOM2 = getUOMOfTransactionLine("2");

		softAssert.assertEquals(stockLineProdCode2, stockItemCode2, "Product Code is displayed in Transaction Line2");

		//12.2 UOM code is displayed differently 
		boolean isStockItemUOM2 = (stockUOM2.equals(stockItemUOM2)||stockUOM2.equals(stockItemUOM122));
										
		softAssert.assertTrue(isStockItemUOM2, "UOM is prepopulated for Stock item line");

		waitForExtJSAjaxComplete(5);

		setTransactionLineQuantity("2", valueGreaterThanAvlQty);
		
		waitForExtJSAjaxComplete(5);

		setTransactionLineLocationFromAllLocations("2", wareHouseLoc, lookUpWindowCol);
		
		setTransactionLineRemark("2", remarkTransactionLine2);
		
		setTransactionLineLot("2", lotTransactionLine);

		waitForExtJSAjaxComplete(10);
		
		Reporter.log("Stock line 2 is added", true);
		
		//*****************Verifying status in Transaction Line**********************
		
		setStatusValue(GOODS_ISSUE_TRANSACTION_WINDOW_HEADER, "Executed", "@id", getXWindowId(GOODS_ISSUE_TRANSACTION_WINDOW_HEADER));
		
		waitForExtJSAjaxComplete(10);
		
		save();
		
		waitForExtJSAjaxComplete(10);
		
		String warningMsg = this.getWarningDialogTextMsg();
		
		softAssert.assertTrue(warningMsg.contains("The Quantity cannot be higher than the Available Stock"), "User can't enter quantity more than available stock");
		softAssert.assertTrue(warningMsg.contains("The Quantity cannot be higher than the Available Stock"), "User can't enter quantity more than available stock");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineQuantity("1", lineQuantity);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineQuantity("2", lineQuantity);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(10);
		
		close(GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
		
		addWarehousePageObject.clickEditButton(); 
		
		waitForExtJSAjaxComplete(10);
		
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValuePostTxn1 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),availableStockColName ));
		int reservedStockValuePostTxn1 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),reservedStockColName));
		int totalStockValuePostTxn1 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),totalStockColName));
		int blockStockValuePostTxn1 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),blockedStockColName));
				
		//*****************************Line 1 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn1==(availableStockValue-(intLineQuantity))), stockItemRef+" available stock quantity is decreased by "+lineQuantity+"units");
		softAssert.assertTrue((reservedStockValuePostTxn1 ==(reservedStockValue+(intLineQuantity))), stockItemRef+" reserved stock quantity is increased by "+lineQuantity+"units");
		softAssert.assertTrue((totalStockValuePostTxn1==totalStockValue), stockItemRef+" Total stock quantity is unchanged");
		softAssert.assertTrue((blockStockValuePostTxn1==blockedStockValue), stockItemRef+" Block stock quantity is unchanged");
		
		int availableStockValuePostTxn2 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),availableStockColName ));
		int totalStockValuePostTxn2 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),totalStockColName));
		int blockStockValuePostTxn2 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),blockedStockColName));
		int reservedStockValuePostTxn2 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),reservedStockColName));
		
		//*****************************Line 2 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn1==(availableStockValue-(intLineQuantity))), stockItemRef2+" available stock quantity is decreased by "+lineQuantity+"units");
		softAssert.assertTrue((reservedStockValuePostTxn1 ==(reservedStockValue+(intLineQuantity))), stockItemRef2+" reserved stock quantity is increased by "+lineQuantity+"units");
		softAssert.assertTrue((totalStockValuePostTxn2==totalStockValue), stockItemRef2+" Total stock quantity is unchanged");
		softAssert.assertTrue((blockStockValuePostTxn2==blockedStockValue), stockItemRef2+" Block stock quantity is unchanged");
		
		waitForExtJSAjaxComplete(10);
		
		addWarehousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		waitAndClick(XPATH_TRANSACTIONS);
			
		waitForExtJSAjaxComplete(20);
				
		//Filter Transactions Grid with Remark
		filterGrid("@id", getFilterGridID(), remarkTransactionLine1, "Remark");
		
		//Open the Transactions Grid Record
		openTransactionLine("@realid", "mogrid", remarkTransactionLine1, "Remark");
		
		waitForExtJSAjaxComplete(10);
		
		String statusCancelled = "Not In Stock";
		
		setStatus("Canceled", GOODS_ISSUE_TRANSACTION_WINDOW_HEADER );
		
		String statusTransactionLine1 = getStatusOfTransactionLine("1");
		
		String statusTransactionLine2 = getStatusOfTransactionLine("2");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertEquals(statusTransactionLine1, statusCancelled, "Not in Stock Status is Displayed in Transaction Line 1");
		
		softAssert.assertEquals(statusTransactionLine2, statusCancelled, "Not in Stock Status is Displayed in Transaction Line 2");
		
		save();
		
		waitForExtJSAjaxComplete(10);
		
		close(GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(10);
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
		
		addWarehousePageObject.clickEditButton(); 
		
		waitForExtJSAjaxComplete(10);
		
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(10);
				
		availableStockValuePostTxn1 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),availableStockColName ));
		reservedStockValuePostTxn1 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),reservedStockColName));
		totalStockValuePostTxn1 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),totalStockColName));
		blockStockValuePostTxn1 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),blockedStockColName));
				
		//*****************************Line 1 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn1==availableStockValue), stockItemRef+" available stock quantity is unchanged");
		softAssert.assertTrue((reservedStockValuePostTxn1 ==reservedStockValue), stockItemRef+" reserved stock quantity is unchanged");
		softAssert.assertTrue((totalStockValuePostTxn1==totalStockValue), stockItemRef+" Total stock quantity is unchanged");
		softAssert.assertTrue((blockStockValuePostTxn1==blockedStockValue), stockItemRef+" Block stock quantity is unchanged");
		
		availableStockValuePostTxn2 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),availableStockColName ));
		totalStockValuePostTxn2 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),totalStockColName));
		blockStockValuePostTxn2 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),blockedStockColName));
		reservedStockValuePostTxn2 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),reservedStockColName));
		
		//*****************************Line 2 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn2==availableStockValue), stockItemRef2+" available stock quantity is unchanged");
		softAssert.assertTrue((reservedStockValuePostTxn2 ==reservedStockValue), stockItemRef2+" reserved stock quantity is unchanged");
		softAssert.assertTrue((totalStockValuePostTxn2==totalStockValue), stockItemRef2+" Total stock quantity is unchanged");
		softAssert.assertTrue((blockStockValuePostTxn2==blockedStockValue), stockItemRef2+" Block stock quantity is unchanged");
		
		addWarehousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C35981:Planned Goods Issue transaction increases the reserved stock and decreases the available stock </span><br>"
				+"C35982: Cancelled Goods Issue transaction increases the available stock and decreases the reserved stock <br> </span>"
				+"C35978: A Message is displayed if the Quantity Exceeds the Available Quantity in Warehouse, while issuing goods <br> </span>", true);
	}

	/**
	 * Testcase ID	 : C35987,C35988,C90873
	 * Author		 : vpcc
	 */
	 
	 	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testCreateIncrementalStockCorrectionTransaction() throws Exception{
		
		String remark1 = "C35987Rmark1"+getCurrentDate().substring(getCurrentDate().length()-5);
		String remark2 = "C35987Rmark2"+getCurrentDate().substring(getCurrentDate().length()-5);
		String wareHouseLoc = "HYD";
		String warehouseReference = "StockCorrTransWH";
		String stockItemRef1 = "2preConsRef";
		String stockItemRef2 = "6preProdRef";
		String lineQuantity = "10";
		String lookUpWindowCol = "Reference";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		String availableStockColName = "Available Stock";
		String totalStockColName="Total Stock";
		String blockedStockColName="Blocked Stock";
		String reservedStockColName = "Reserved Stock";
		
		int intLineQuantity = Integer.parseInt(lineQuantity);
		
		SoftAssert softAssert = new SoftAssert();
		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90823:Create Stock Correction Transaction <br>"+
	 	"C90824:Verifying arrow symbol for stock correction transactions <br></span>", true); 
		
		softAssert.setMethodName("testCreateIncrementalStockCorrectionTransaction");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),availableStockColName ));
		int totalStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),totalStockColName));
		int blockStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),blockedStockColName));
		int reservedStockValue1  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),reservedStockColName));
		
		int availableStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),availableStockColName ));
		int totalStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),totalStockColName));
		int blockStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),blockedStockColName));
		int reservedStockValue2  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),reservedStockColName));
		
		Reporter.log(stockItemRef1+" available stock quantity is "+availableStockValue1, true);
		Reporter.log(stockItemRef1+" total stock quantity is "+totalStockValue1, true);
		Reporter.log(stockItemRef1+" blocked stock quantity is "+blockStockValue1, true);
		
		Reporter.log(stockItemRef2+" available stock quantity is "+availableStockValue2, true);
		Reporter.log(stockItemRef2+" total stock quantity is "+totalStockValue2, true);
		Reporter.log(stockItemRef2+" blocked stock quantity is "+blockStockValue2, true);
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		waitForExtJSAjaxComplete(20);
		
		selectTransactionType("Stock Correction");
		
		waitForExtJSAjaxComplete(20);
		
		setWareHouse(lookUpWindowCol, warehouseReference, STOCK_CORRECTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		//**************************Line 1******************************************************
		
		clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineProductReferenceFromProductsAndServices("1", stockItemRef1,lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineQuantity("1", ""+(totalStockValue1+intLineQuantity));
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineLocationFromAllLocations("1",  wareHouseLoc, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineRemark("1", remark1);
		
		setTransactionLineRemark("1", remark1);
		
		waitForExtJSAjaxComplete(20);
		
		
		//**************************Line 2******************************************************
		
		this.clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineProductReferenceFromProductsAndServices("2", stockItemRef2,lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineQuantity("2", ""+(totalStockValue2+intLineQuantity));
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineLocationFromAllLocations("2",  wareHouseLoc, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineRemark("2", remark2);
		
		setTransactionLineRemark("2", remark2);
		
		waitForExtJSAjaxComplete(20);
		
		execute();
		
		waitForExtJSAjaxComplete(20);
		
		filterGrid("@id", getFilterGridID(), remark1, "Remark");
		
		WebElement quantityEle = getQuantityOfTransactionLine("@id", getFilterGridID(),remark1, "Remark");
		
		String quantityIcon = this.getQuantityIcon(quantityEle);
		
		softAssert.assertTrue(quantityIcon.equals("up arrow"),"Quantity icon for Transaction is up Arrow");
		
		waitForExtJSAjaxComplete(10);
		
		filterGrid("@id", getFilterGridID(), remark2, "Remark");
		
		quantityEle = getQuantityOfTransactionLine("@id", getFilterGridID(),remark2, "Remark");
		
		quantityIcon = this.getQuantityIcon(quantityEle);
		
		softAssert.assertTrue(quantityIcon.equals("up arrow"),"Quantity icon for Transaction is up Arrow");

		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),availableStockColName ));
		int totalStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),totalStockColName));
		int blockStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),blockedStockColName));
		int reservedStockPostTxn1  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),reservedStockColName));

		int availableStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),availableStockColName ));
		int totalStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),totalStockColName));
		int blockStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),blockedStockColName));
		int reservedStockPostTxn2  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),reservedStockColName));
		
		//*****************************Line 1 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn1==(availableStockValue1+intLineQuantity)), stockItemRef1+" available stock quantity is increased by "+lineQuantity+"units");
		softAssert.assertTrue(( totalStockValuePostTxn1==(totalStockValue1+intLineQuantity)), stockItemRef1+" total stock quantity is increased by "+lineQuantity+"units");
		softAssert.assertTrue((blockStockValuePostTxn1==blockStockValue1), stockItemRef1+" blocked stock quantity is unchanged");
		softAssert.assertTrue((reservedStockPostTxn1==reservedStockValue1), stockItemRef1+" reserved stock quantity is unchanged by this transaction");
		
		//*****************************Line 2 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn2==(availableStockValue2+intLineQuantity)), stockItemRef2+" available stock quantity is increased by "+lineQuantity+"units");
		softAssert.assertTrue(( totalStockValuePostTxn2 ==(totalStockValue2+intLineQuantity)), stockItemRef2+" total stock quantity is increased by "+lineQuantity+"units");
		softAssert.assertTrue((blockStockValuePostTxn2==blockStockValue2), stockItemRef2+" blocked stock quantity is unchanged");
		softAssert.assertTrue((reservedStockPostTxn2==reservedStockValue2), stockItemRef2+" reserved stock quantity is unchanged by this transaction");
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		softAssert.assertAll();
		
		Reporter.log("A message is displayed if the Block Quantity Exceeds the available quantity in warehouse, while blocking <br>"+
				"Created a Block Transaction. Block Transaction successfully blocked X units of Available stock <br>"+ 
				"by increasing block stock by X Units. Total Stock is unaffected by Block transaction <br>", true); 
	}
	
	/**
	 * Testcase ID	 : C90823,C90824
	 * Author		 : vpcc
	 */
	 
	 	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testCreateDecrementalStockCorrectionTransaction() throws Exception{
		
		String remark1 = "C35987Rmark1"+getCurrentDate().substring(getCurrentDate().length()-5);
		String remark2 = "C35987Rmark2"+getCurrentDate().substring(getCurrentDate().length()-5);
		String wareHouseLoc = "HYD";
		String warehouseReference = "StockCorrTransWH";
		String stockItemRef1 = "2preConsRef";
		String stockItemRef2 = "6preProdRef";
		String lineQuantity = "10";
		String lookUpWindowCol = "Reference";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		String availableStockColName = "Available Stock";
		String totalStockColName="Total Stock";
		String blockedStockColName="Blocked Stock";
		String reservedStockColName = "Reserved Stock";
		
		int intLineQuantity = Integer.parseInt(lineQuantity);
		
		SoftAssert softAssert = new SoftAssert();
		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90823:Create Stock Correction Transaction <br>"+
	 	"C90824:Verifying arrow symbol for stock correction transactions <br></span>", true); 
		
		softAssert.setMethodName("testCreateDecrementalStockCorrectionTransaction");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),availableStockColName ));
		int totalStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),totalStockColName));
		int blockStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),blockedStockColName));
		int reservedStockValue1  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),reservedStockColName));
		
		int availableStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),availableStockColName ));
		int totalStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),totalStockColName));
		int blockStockValue2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),blockedStockColName));
		int reservedStockValue2  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),reservedStockColName));
		
		Reporter.log(stockItemRef1+" available stock quantity is "+availableStockValue1, true);
		Reporter.log(stockItemRef1+" total stock quantity is "+totalStockValue1, true);
		Reporter.log(stockItemRef1+" blocked stock quantity is "+blockStockValue1, true);
		Reporter.log(stockItemRef1+" reserved stock quantity is "+reservedStockValue1, true);
		
		Reporter.log(stockItemRef2+" available stock quantity is "+availableStockValue2, true);
		Reporter.log(stockItemRef2+" total stock quantity is "+totalStockValue2, true);
		Reporter.log(stockItemRef2+" blocked stock quantity is "+blockStockValue2, true);
		Reporter.log(stockItemRef2+" reserved stock quantity is "+reservedStockValue2, true);
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		waitForExtJSAjaxComplete(20);
		
		this.selectTransactionType("Stock Correction");
		
		waitForExtJSAjaxComplete(20);
		
		this.setWareHouse(lookUpWindowCol, warehouseReference, STOCK_CORRECTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		//**************************Line 1******************************************************
		
		clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineProductReferenceFromProductsAndServices("1", stockItemRef1,lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineQuantity("1", ""+(totalStockValue1-intLineQuantity));
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineLocationFromAllLocations("1",  wareHouseLoc, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineRemark("1", remark1);
		
		setTransactionLineRemark("1", remark1);
		
		waitForExtJSAjaxComplete(25);
		
		
		//**************************Line 2******************************************************
		
		this.clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineProductReferenceFromProductsAndServices("2", stockItemRef2,lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineQuantity("2", ""+(totalStockValue2-intLineQuantity));
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineLocationFromAllLocations("2",  wareHouseLoc, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineRemark("2", remark2);
		
		setTransactionLineRemark("2", remark2);
		
		waitForExtJSAjaxComplete(20);
		
		execute();
		
		waitForExtJSAjaxComplete(20);
		
		filterGrid("@id", getFilterGridID(), remark1, "Remark");
		
		WebElement quantityEle = getQuantityOfTransactionLine("@id", getFilterGridID(),remark1, "Remark");
		
		String quantityIcon = this.getQuantityIcon(quantityEle);
		
		softAssert.assertTrue(quantityIcon.equals("down arrow"),"Quantity icon for Transaction is down Arrow");
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(10);
		
		filterGrid("@id", getFilterGridID(), remark2, "Remark");
		
		quantityEle = getQuantityOfTransactionLine("@id", getFilterGridID(),remark2, "Remark");
		
		quantityIcon = this.getQuantityIcon(quantityEle);
		
		softAssert.assertTrue(quantityIcon.equals("down arrow"),"Quantity icon for Transaction is down Arrow");

		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),availableStockColName ));
		int totalStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),totalStockColName));
		int blockStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),blockedStockColName));
		int reservedStockPostTxn1  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),reservedStockColName));

		int availableStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),availableStockColName ));
		int totalStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),totalStockColName));
		int blockStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),blockedStockColName));
		int reservedStockPostTxn2  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef2),reservedStockColName));
		
		Reporter.log(stockItemRef1+" available stock quantity post transaction is "+availableStockValuePostTxn1, true);
		Reporter.log(stockItemRef1+" total stock quantity  post transaction is "+totalStockValuePostTxn1, true);
		Reporter.log(stockItemRef1+" blocked stock quantity  post transaction is "+blockStockValuePostTxn1, true);
		Reporter.log(stockItemRef1+" reserved stock quantity  post transaction is "+reservedStockPostTxn1, true);
		
		Reporter.log(stockItemRef2+" available stock quantity  post transaction is "+availableStockValuePostTxn2, true);
		Reporter.log(stockItemRef2+" total stock quantity  post transaction is "+totalStockValuePostTxn2, true);
		Reporter.log(stockItemRef2+" blocked stock quantity  post transaction is "+blockStockValuePostTxn2, true);
		Reporter.log(stockItemRef2+" reserved stock quantity  post transaction is "+reservedStockPostTxn2, true);
		
		//*****************************Line 1 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn1==(availableStockValue1-intLineQuantity)), stockItemRef1+" available stock quantity is increased by "+lineQuantity+"units");
		softAssert.assertTrue(( totalStockValuePostTxn1==(totalStockValue1-intLineQuantity)), stockItemRef1+" total stock quantity is increased by "+lineQuantity+"units");
		softAssert.assertTrue((blockStockValuePostTxn1==blockStockValue1), stockItemRef1+" blocked stock quantity is unchanged");
		softAssert.assertTrue((reservedStockPostTxn1==reservedStockValue1), stockItemRef1+" reserved stock quantity is unchanged by this transaction");
		
		//*****************************Line 2 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn2==(availableStockValue2-intLineQuantity)), stockItemRef2+" available stock quantity is increased by "+lineQuantity+"units");
		softAssert.assertTrue(( totalStockValuePostTxn2 ==(totalStockValue2-intLineQuantity)), stockItemRef2+" total stock quantity is increased by "+lineQuantity+"units");
		softAssert.assertTrue((blockStockValuePostTxn2==blockStockValue2), stockItemRef2+" blocked stock quantity is unchanged");
		softAssert.assertTrue((reservedStockPostTxn2==reservedStockValue2), stockItemRef2+" reserved stock quantity is unchanged by this transaction");
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		softAssert.assertAll();

		Reporter.log("A message is displayed if the Block Quantity Exceeds the available quantity in warehouse, while blocking <br>"+
				"Created a Block Transaction. Block Transaction successfully blocked X units of Available stock <br>"+ 
				"by increasing block stock by X Units. Total Stock is unaffected by Block transaction <br>", true); 
	}
	
	/**
	 * Testcase ID	 : C90835
	 * Author		 : ssu
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testStockScrappingMultipleLocationsInSameWarehouse() throws Exception{

		Reporter.log("<span style='font-weight:bold;color:#000033'> C90835:Stock scrapping Transaction for multiple locations in same Warehouse</span><br>", true);
		
		waitForExtJSAjaxComplete(30);
		
		String warehouseRef = "StockItemsBlockedWH";
		String availableStockColName = "Available Stock";
		String reservedStockColName = "Reserved Stock";
		String blockedStockColName = "Blocked Stock";
		String totalStockColName = "Total Stock";
		String productReference = "aqapreProdRef3";
		String productCode = "aqapreProdCod3";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseRef, "Reference");
		
		waitForExtJSAjaxComplete(10);
		
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
		
		waitForExtJSAjaxComplete(20);
		
		String attributeName = "textContent";

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Stock Items <br>", true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testStockScrappingMultipleLocationsInSameWarehouse");
		
		selectWarehouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		//Levels Verification in Left Panel of Stock Items
		StockItemsPageObject stockItemsPO = new StockItemsPageObject();
		
		stockItemsPO.openTransactionLineStockItems("@id", getFilterGridID(), productCode, "Product Code");
		
		waitForExtJSAjaxComplete(10);
		
		stockItemsPO.clickCurrentTab();
		
		//************************Stock Items Line 1 Verifications (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationIndStockItemsPreTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("1", attributeName);
		
		String avlReservedQtyIndStockItemsPreTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("1", attributeName);
		
		String blockedQtyIndStockItemsPreTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("1", attributeName);
		
		String totalQtyIndStockItemsPreTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("1", attributeName);
		
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 Verifications (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationHydStockItemsPreTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("2", attributeName);
		
		String avlReservedQtyHydStockItemsPreTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("2", attributeName);
		
		String blockedQtyHydStockItemsPreTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("2", attributeName);
		
		String totalQtyHydStockItemsPreTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("2", attributeName);
		
		waitForExtJSAjaxComplete(5);
		
		close(STOCK_ITEMS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		String transactionType = "Stock Scrapping";
		String transactionref = "C52630Ref"+ getCurrentDate().substring(getCurrentDate().length()-5);
		String executedBy = "SELENIUM";
		String scrappingDate = this.getFutureDate(0);
		String refereneColName = "Reference";
		String lineQty = "1";
		String locHyd = "HYD";
		String locInd = "IND";
		int intLineQuantity = Integer.parseInt(lineQty);
		String uom = "cc";
		
		waitForExtJSAjaxComplete(20);
		
		selectTransactionType(transactionType);
		
		waitForExtJSAjaxComplete(5);
		
		setWareHouse("Reference", warehouseRef, STOCK_SCRAPPING_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		setReference(transactionref, STOCK_SCRAPPING_TRANSACTION_WINDOW_HEADER);
		
		selectCollectingPerson("Last Name", executedBy, STOCK_SCRAPPING_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);

		setTransactionDate(scrappingDate, STOCK_SCRAPPING_TRANSACTION_WINDOW_HEADER);
		
		clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineProductReferenceFromProductsAndServices("1", productReference, refereneColName);
		
		waitForExtJSAjaxComplete(20);

		String stocLineProdCode = getProductCodeOfTransactionLine("1");

		String stockUOM = getUOMOfTransactionLine("1");

		softAssert.assertTrue(stocLineProdCode.equals(productCode), "Product code is prepopulated for Stock item line");

		softAssert.assertTrue(stockUOM.equals(uom), "UOM is prepopulated for Stock item line");
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("1", lineQty);
		
		setTransactionLineLocationFromAllLocations("1", locInd, refereneColName);
		
		setTransactionLineRemark("1", "India");
		
		waitForExtJSAjaxComplete(5);
		
		clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineProductReferenceFromProductsAndServices("2", productReference, refereneColName);
		
		waitForExtJSAjaxComplete(20);

		stocLineProdCode = getProductCodeOfTransactionLine("2");

		stockUOM = getUOMOfTransactionLine("2");

		softAssert.assertTrue(stocLineProdCode.equals(productCode), "Product code is prepopulated for Stock item line");

		softAssert.assertTrue(stockUOM.equals(uom), "UOM is prepopulated for Stock item line");
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("2", lineQty);
		
		setTransactionLineLocationFromAllLocations("2", locHyd, refereneColName);
		
		setTransactionLineRemark("2", "Hyderabad");
		
		waitForExtJSAjaxComplete(5);
		
		saveAndClose(STOCK_SCRAPPING_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseRef);

		addWarehousePageObject.clickEditButton(); 
				
		waitForExtJSAjaxComplete(10);
				
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
				
		waitForExtJSAjaxComplete(10);
		
		String strAvailableStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),availableStockColName);
		String strReservedStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),reservedStockColName);
		String strBlockedStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),blockedStockColName);
		String strTotalStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),totalStockColName);
				
		int availableStockValuePostTxn1 = Integer.parseInt(strAvailableStockValuePostTxn1);
		int totalStockValuePostTxn1 = Integer.parseInt(strTotalStockValuePostTxn1);
		int blockStockValuePostTxn1 = Integer.parseInt(strBlockedStockValuePostTxn1);
		int reservedValuePostTxn1 = Integer.parseInt(strReservedStockValuePostTxn1);
		  
		//*****************************Line 1 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn1==availableStockValue), productReference+" available stock quantity is unchanged by this transaction");
		softAssert.assertTrue((blockStockValuePostTxn1 ==(blockedStockValue-(intLineQuantity*2))), productReference+" blocked stock quantity is decreased by "+lineQty+"units");
		softAssert.assertTrue((totalStockValuePostTxn1==(totalStockValue-(intLineQuantity*2))), productReference+" Total stock quantity is decreased by "+lineQty+"units");
		softAssert.assertTrue((reservedValuePostTxn1==reservedStockValue), productReference+"Reserved stock quantity is unchanged");
					
		addWarehousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Stock Items <br>", true);
		
		selectWarehouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		//Levels Verification in Left Panel of Stock Items
		stockItemsPO.openTransactionLineStockItems("@id", getFilterGridID(), productCode, "Product Code");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(stockItemsPO.verifyAvailableStockFromStockItemsLeftPanel(strAvailableStockValuePostTxn1), "Available stock: <number> is present in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyReservedStockFromStockItemsLeftPanel(strReservedStockValuePostTxn1), "Reserved stock: <number> is available in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyBlockedStockFromStockItemsLeftPanel(strBlockedStockValuePostTxn1), "Blocked stock: <number> is available in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyTotalStockFromStockItemsLeftPanel(strTotalStockValuePostTxn1), "Total stock: <number> is available in left panel");
		
		waitForExtJSAjaxComplete(5);
		
		stockItemsPO.clickCurrentTab();
		
		//************************Stock Items Line 1 get Cell Text Values (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationIndPostTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("1", attributeName);
		
		String avlReservedQtyIndPostTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("1", attributeName);
		
		String blockedQtyIndPostTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("1", attributeName);
		int intBlockedQtyIndPostTxn = Integer.parseInt(blockedQtyIndPostTxn);
		
		String totalQtyIndPostTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("1", attributeName);
		int intTotalQtyIndPostTxn = Integer.parseInt(totalQtyIndPostTxn);
		
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 get Cell Text Values (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationHydPostTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("2", attributeName);
		
		String avlReservedQtyHydPostTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("2", attributeName);
		
		String blockedQtyHydPostTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("2", attributeName);
		int intBlockedQtyHydPostTxn = Integer.parseInt(blockedQtyHydPostTxn);
		
		String totalQtyHydPostTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("2", attributeName);
		int intTotalQtyHydPostTxn = Integer.parseInt(totalQtyHydPostTxn);

		//*********** India Location Blocked and Total Qty in Stock Items Window
		int intBlockedQtyIndStockItemsPreTxn = Integer.parseInt(blockedQtyIndStockItemsPreTxn);
		int intBlockedQtyIndStockItems = intBlockedQtyIndStockItemsPreTxn - intLineQuantity;
			
		int intTotalQtyIndStockItemsPreTxn = Integer.parseInt(totalQtyIndStockItemsPreTxn);
		int intTotalQtyIndStockItems = intTotalQtyIndStockItemsPreTxn - intLineQuantity;
		
		//********* Hyderabad Location Blocked and Total Qty in Stock Items Window
		int intBlockedQtyHydStockItemsPreTxn = Integer.parseInt(blockedQtyHydStockItemsPreTxn);
		int intBlockedQtyHydStockItems = intBlockedQtyHydStockItemsPreTxn - intLineQuantity;
					
		int intTotalQtyHydStockItemsPreTxn = Integer.parseInt(totalQtyHydStockItemsPreTxn);
		int intTotalQtyHydStockItems = intTotalQtyHydStockItemsPreTxn - intLineQuantity;
		
		//************************Stock Items Line 1 Verifications (Location, Avl Qty, Blocked Qty and Total Qty)
		softAssert.assertEquals(locationIndStockItemsPreTxn, locationIndPostTxn, "Location India is present in Stock Items Window");
		
		softAssert.assertEquals(avlReservedQtyIndStockItemsPreTxn, avlReservedQtyIndPostTxn, "Location India's Available+Reserved quantity is unchanged in Stock Items Window");
		
		softAssert.assertEquals(intBlockedQtyIndStockItems, intBlockedQtyIndPostTxn, "Location India's Block quantity is decreased by "+lineQty+"units");
		
		softAssert.assertEquals(intTotalQtyIndStockItems, intTotalQtyIndPostTxn,  "Location India's Total quantity is decreased by "+lineQty+"units");
		
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 Verifications (Location, Avl Qty, Blocked Qty and Total Qty)
		softAssert.assertEquals(locationHydStockItemsPreTxn, locationHydPostTxn, "Location Hyderabad is present in Stock Items Window");

		softAssert.assertEquals(avlReservedQtyHydStockItemsPreTxn, avlReservedQtyHydPostTxn, "Location Hyderabad's Available+Reserved quantity is unchanged in Stock Items Window");
		
		softAssert.assertEquals(intBlockedQtyHydStockItems,  intBlockedQtyHydPostTxn, "Location Hyderabad's Block quantity is decreased by "+lineQty+"units");
		
		softAssert.assertEquals(intTotalQtyHydStockItems, intTotalQtyHydPostTxn, "Location Hyderabad's Total quantity is decreased by "+lineQty+"units");
		
		waitForExtJSAjaxComplete(5);
		
		close(STOCK_ITEMS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
	
		softAssert.assertAll();
		
		Reporter.log("Stock scrapping Transaction for multiple locations in same Warehouse is successfully verified <br>", true);
	}
	
	/**
	 * Testcase ID	 : C90955
	 * Author		 : ssu
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testStockTranserInSameWarehouseDifferentLocations() throws Exception{

		Reporter.log("<span style='font-weight:bold;color:#000033'> C90955: Stock transfer within same warehouse different locations</span><br>", true);
		
		waitForExtJSAjaxComplete(30);
		
		String warehouseRef = "StockItemsBlockedWH";
		String availableStockColName = "Available Stock";
		String reservedStockColName = "Reserved Stock";
		String blockedStockColName = "Blocked Stock";
		String totalStockColName = "Total Stock";
		String productReference = "aqapreProdRef3";
		String productCode = "aqapreProdCod3";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseRef, "Reference");
		
		waitForExtJSAjaxComplete(10);
		
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
		
		waitForExtJSAjaxComplete(20);
		
		String attributeName = "textContent";

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Stock Items <br>", true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testStockTranserInSameWarehouseDifferentLocations");
		
		selectWarehouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		//Levels Verification in Left Panel of Stock Items
		StockItemsPageObject stockItemsPO = new StockItemsPageObject();
		
		stockItemsPO.openTransactionLineStockItems("@id", getFilterGridID(), productCode, "Product Code");
		
		waitForExtJSAjaxComplete(10);
		
		stockItemsPO.clickCurrentTab();
		
		//************************Stock Items Line 1 get Cell Text Pre Txn (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationIndStockItemsPreTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("1", attributeName);
		
		String avlReservedQtyIndStockItemsPreTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("1", attributeName);
		
		String blockedQtyIndStockItemsPreTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("1", attributeName);
		
		String totalQtyIndStockItemsPreTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("1", attributeName);
		
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 get Cell Text Pre Txn (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationHydStockItemsPreTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("2", attributeName);
		
		String avlReservedQtyHydStockItemsPreTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("2", attributeName);
		
		String blockedQtyHydStockItemsPreTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("2", attributeName);
		
		String totalQtyHydStockItemsPreTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("2", attributeName);
		
		waitForExtJSAjaxComplete(5);
		
		close(STOCK_ITEMS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		String transactionType = "Stock Transfer";
		String refereneColName = "Reference";
		String lineQty = "5";
		String locHyd = "HYD";
		String locInd = "IND";
		int intLineQuantity = Integer.parseInt(lineQty);
		String uom = "cc";
		
		waitForExtJSAjaxComplete(20);
		
		selectTransactionType(transactionType);
		
		waitForExtJSAjaxComplete(5);
		
		setWareHouse("Reference", warehouseRef, STOCK_TRANSFER_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		setDestinationWareHouse("Reference", warehouseRef, STOCK_TRANSFER_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineProductReferenceFromProductsAndServices("1", productReference, refereneColName);
		
		waitForExtJSAjaxComplete(20);

		String stockUOM = getUOMOfTransactionLine("1");

		softAssert.assertTrue(stockUOM.equals(uom), "UOM is prepopulated for Stock item line");
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("1", lineQty);
		
		setTransactionLineLocationFromAllLocations("1", locInd, refereneColName);
		
		setTransactionLineDestinationLocationFromAllLocations("1", locHyd, refereneColName);
		
		setTransactionLineRemark("1", "stock transfer");
		
		waitForExtJSAjaxComplete(5);
				
		execute();
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseRef);

		addWarehousePageObject.clickEditButton(); 
				
		waitForExtJSAjaxComplete(10);
				
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
				
		waitForExtJSAjaxComplete(10);
		
		String strAvailableStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),availableStockColName);
		String strReservedStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),reservedStockColName);
		String strBlockedStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),blockedStockColName);
		String strTotalStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),totalStockColName);
		
		int availableStockValuePostTxn1 = Integer.parseInt(strAvailableStockValuePostTxn1);
		int totalStockValuePostTxn1 = Integer.parseInt(strTotalStockValuePostTxn1);
		int blockStockValuePostTxn1 = Integer.parseInt(strBlockedStockValuePostTxn1);
		int reservedValuePostTxn1 = Integer.parseInt(strReservedStockValuePostTxn1);
		  
		//*****************************Line 1 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn1==availableStockValue), productReference+" available stock quantity is unchanged by this transaction");
		softAssert.assertTrue((blockStockValuePostTxn1 ==blockedStockValue), productReference+" blocked stock quantity is unchanged by this transaction");
		softAssert.assertTrue((totalStockValuePostTxn1==totalStockValue), productReference+" Total stock quantity is unchanged by this transaction");
		softAssert.assertTrue((reservedValuePostTxn1==reservedStockValue), productReference+"Reserved stock quantity is unchanged");
					
		addWarehousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Stock Items <br>", true);
		
		selectWarehouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		//Levels Verification in Left Panel of Stock Items
		stockItemsPO.openTransactionLineStockItems("@id", getFilterGridID(), productCode, "Product Code");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(stockItemsPO.verifyAvailableStockFromStockItemsLeftPanel(strAvailableStockValuePostTxn1), "Available stock: <number> is present in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyReservedStockFromStockItemsLeftPanel(strReservedStockValuePostTxn1), "Reserved stock: <number> is available in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyBlockedStockFromStockItemsLeftPanel(strBlockedStockValuePostTxn1), "Blocked stock: <number> is available in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyTotalStockFromStockItemsLeftPanel(strTotalStockValuePostTxn1), "Total stock: <number> is available in left panel");
				
		waitForExtJSAjaxComplete(5);
		
		stockItemsPO.clickCurrentTab();
		
		//************************Stock Items Line 1 get Cell Text Value (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationIndPostTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("1", attributeName);
		
		String avlReservedQtyIndPostTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("1", attributeName);
		int intAvlReservedQtyIndPostTxn = Integer.parseInt(avlReservedQtyIndPostTxn);
		
		String blockedQtyIndPostTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("1", attributeName);
				
		String totalQtyIndPostTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("1", attributeName);
		int intTotalQtyIndPostTxn = Integer.parseInt(totalQtyIndPostTxn);
				
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 get Cell Text Value (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationHydPostTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("2", attributeName);
		
		String avlReservedQtyHydPostTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("2", attributeName);
		int intAvlReservedQtyHydPostTxn = Integer.parseInt(avlReservedQtyHydPostTxn);
		
		String blockedQtyHydPostTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("2", attributeName);
		
		String totalQtyHydPostTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("2", attributeName);
		int intTotalQtyHydPostTxn = Integer.parseInt(totalQtyHydPostTxn);

		//********* India Location Blocked and Total Qty in Stock Items Window
		int intAvlReservedQtyIndStockItemsPreTxn = Integer.parseInt(avlReservedQtyIndStockItemsPreTxn);
		int intAvlReservedQtyIndStockItems = intAvlReservedQtyIndStockItemsPreTxn - intLineQuantity;
			
		int intTotalQtyIndStockItemsPreTxn = Integer.parseInt(totalQtyIndStockItemsPreTxn);
		int intTotalQtyIndStockItems = intTotalQtyIndStockItemsPreTxn - intLineQuantity;
		
		// *********Hyderabad Location Blocked and Total Qty in Stock Items Window
		int intAvlReservedQtyHydStockItemsPreTxn = Integer.parseInt(avlReservedQtyHydStockItemsPreTxn);
		int intAvlReservedQtyHydStockItems = intAvlReservedQtyHydStockItemsPreTxn + intLineQuantity;
					
		int intTotalQtyHydStockItemsPreTxn = Integer.parseInt(totalQtyHydStockItemsPreTxn);
		int intTotalQtyHydStockItems = intTotalQtyHydStockItemsPreTxn + intLineQuantity;
		
		//************************Stock Items Line 1 Verifications (Location, Avl Qty, Blocked Qty and Total Qty)
		softAssert.assertEquals(locationIndStockItemsPreTxn, locationIndPostTxn, "Location India is present in Stock Items Window");
		
		softAssert.assertEquals(intAvlReservedQtyIndStockItems, intAvlReservedQtyIndPostTxn, "Location India's Available+Reserved is decreased by "+lineQty+"units");
		
		softAssert.assertEquals(blockedQtyIndStockItemsPreTxn, blockedQtyIndPostTxn, "Location India's Block quantity is unchanged in Stock Items Window");
		
		softAssert.assertEquals(intTotalQtyIndStockItems, intTotalQtyIndPostTxn, "Location India's Total quantity is decreased by "+lineQty+"units");
		
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 Verifications (Location, Avl Qty, Blocked Qty and Total Qty)
		softAssert.assertEquals(locationHydStockItemsPreTxn, locationHydPostTxn, "Location Hyderabad is present in Stock Items Window");

		softAssert.assertEquals(intAvlReservedQtyHydStockItems, intAvlReservedQtyHydPostTxn,  "Location Hyderabad's Available+Reserved quantity is increased by "+lineQty+"units");
		
		softAssert.assertEquals(blockedQtyHydStockItemsPreTxn, blockedQtyHydPostTxn, "Location Hyderabad's Block quantity is unchanged in Stock Items Window" );
		
		softAssert.assertEquals(intTotalQtyHydStockItems, intTotalQtyHydPostTxn,  "Location Hyderabad's Total quantity is increased by "+lineQty+"units");
		
		waitForExtJSAjaxComplete(5);
		
		close(STOCK_ITEMS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
	
		softAssert.assertAll();
		
		Reporter.log("Stock Transfer Transaction for multiple locations in same Warehouse is successfully verified <br>", true);
	}
	
	/**
	 * Testcase ID	 : C90831
	 * Author		 : ssu
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testStockCorrectionInSameWarehouseDifferentLocations() throws Exception{

		Reporter.log("<span style='font-weight:bold;color:#000033'> C90831: Stock correction Transaction for multiple locations in same Warehouse</span><br>", true);
		
		waitForExtJSAjaxComplete(30);
		
		String warehouseRef = "StockCorrectionWH";
		String availableStockColName = "Available Stock";
		String reservedStockColName = "Reserved Stock";
		String blockedStockColName = "Blocked Stock";
		String totalStockColName = "Total Stock";
		String productReference = "6preProdRef";
		String productCode = "6preProdCod";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseRef, "Reference");
		
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
		
		waitForExtJSAjaxComplete(20);
		
		String attributeName = "textContent";

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Stock Items <br>", true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testStockCorrectionInSameWarehouseDifferentLocations");
		
		selectWarehouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		//Levels Verification in Left Panel of Stock Items
		StockItemsPageObject stockItemsPO = new StockItemsPageObject();
		
		stockItemsPO.openTransactionLineStockItems("@id", getFilterGridID(), productCode, "Product Code");
		
		waitForExtJSAjaxComplete(10);
		
		stockItemsPO.clickCurrentTab();
		
		//************************Stock Items Line 1 get Cell Text Pre Txn (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationVjaStockItemsPreTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("1", attributeName);
		
		String avlReservedQtyVjaStockItemsPreTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("1", attributeName);

		String blockedQtyVjaStockItemsPreTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("1", attributeName);
		
		String totalQtyVjaStockItemsPreTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("1", attributeName);

		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 get Cell Text Pre Txn (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationHydStockItemsPreTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("2", attributeName);
		
		String avlReservedQtyHydStockItemsPreTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("2", attributeName);

		String blockedQtyHydStockItemsPreTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("2", attributeName);
		
		String totalQtyHydStockItemsPreTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("2", attributeName);

		waitForExtJSAjaxComplete(5);
		
		close(STOCK_ITEMS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		String transactionType = "Stock Correction";
		String refereneColName = "Reference";
		String lineQty = "5";
		String locHyd = "HYD";
		String locVja = "VJA";
		int intLineQuantity = Integer.parseInt(lineQty);
		
		waitForExtJSAjaxComplete(20);
		
		selectTransactionType(transactionType);
		
		waitForExtJSAjaxComplete(5);
		
		setWareHouse("Reference", warehouseRef, STOCK_CORRECTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineProductReferenceFromProductsAndServices("1", productReference, refereneColName);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("1", ""+(totalStockValue+intLineQuantity));
		
		setTransactionLineLocationFromAllLocations("1", locVja, refereneColName);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineRemark("1", "stock correction 1");
		
		waitForExtJSAjaxComplete(5);
		
		clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineProductReferenceFromProductsAndServices("2", productReference, refereneColName);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("2", ""+(totalStockValue+intLineQuantity));
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineLocationFromAllLocations("2", locHyd, refereneColName);
		
		setTransactionLineRemark("2", "stock correction 2");
		
		waitForExtJSAjaxComplete(5);
				
		execute();
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseRef);

		addWarehousePageObject.clickEditButton(); 
				
		waitForExtJSAjaxComplete(10);
				
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
				
		waitForExtJSAjaxComplete(10);
		
		String strAvailableStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),availableStockColName);
		String strReservedStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),reservedStockColName);
		String strBlockedStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),blockedStockColName);
		String strTotalStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),totalStockColName);
		
		int availableStockValuePostTxn1 = Integer.parseInt(strAvailableStockValuePostTxn1);
		int totalStockValuePostTxn1 = Integer.parseInt(strTotalStockValuePostTxn1);
		int blockStockValuePostTxn1 = Integer.parseInt(strBlockedStockValuePostTxn1);
		int reservedValuePostTxn1 = Integer.parseInt(strReservedStockValuePostTxn1);
		
		//*****************************Line 1 Assertions***********************
		softAssert.assertTrue((reservedValuePostTxn1==reservedStockValue), productReference+"Reserved stock quantity is unchanged");
		softAssert.assertTrue((blockStockValuePostTxn1 ==blockedStockValue), productReference+" blocked stock quantity is unchanged by this transaction");
		softAssert.assertTrue((totalStockValuePostTxn1==((totalStockValue*2)+(intLineQuantity*2))), productReference+" Total stock quantity is increased by "+lineQty+" X2 units");
		softAssert.assertTrue((availableStockValuePostTxn1==(totalStockValuePostTxn1-(blockedStockValue-reservedStockValue))), productReference+" available stock quantity is increased by "+lineQty+"units");
					
		addWarehousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Stock Items <br>", true);
		
		selectWarehouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		//Levels Verification in Left Panel of Stock Items
		stockItemsPO.openTransactionLineStockItems("@id", getFilterGridID(), productCode, "Product Code");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(stockItemsPO.verifyAvailableStockFromStockItemsLeftPanel(strAvailableStockValuePostTxn1), "Available stock: <number> is present in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyReservedStockFromStockItemsLeftPanel(strReservedStockValuePostTxn1), "Reserved stock: <number> is available in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyBlockedStockFromStockItemsLeftPanel(strBlockedStockValuePostTxn1), "Blocked stock: <number> is available in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyTotalStockFromStockItemsLeftPanel(strTotalStockValuePostTxn1), "Total stock: <number> is available in left panel");
				
		waitForExtJSAjaxComplete(5);
		
		stockItemsPO.clickCurrentTab();
		
		//************************Stock Items Line 1 get Cell Text Value (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationVjaPostTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("1", attributeName);
		
		String avlReservedQtyVjaPostTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("1", attributeName);
		int intAvlReservedQtyVjaPostTxn = Integer.parseInt(avlReservedQtyVjaPostTxn);
		
		String blockedQtyVjaPostTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("1", attributeName);
		int intBlockedQtyVjaPostTxn = Integer.parseInt(blockedQtyVjaPostTxn);
				
		String totalQtyVjaPostTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("1", attributeName);
		int intTotalQtyVjaPostTxn = Integer.parseInt(totalQtyVjaPostTxn);
				
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 get Cell Text Value (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationHydPostTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("2", attributeName);
		
		String avlReservedQtyHydPostTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("2", attributeName);
		int intAvlReservedQtyHydPostTxn = Integer.parseInt(avlReservedQtyHydPostTxn);
		
		String blockedQtyHydPostTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("2", attributeName);
		int intBlockedQtyHydPostTxn = Integer.parseInt(blockedQtyHydPostTxn);
		
		String totalQtyHydPostTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("2", attributeName);
		int intTotalQtyHydPostTxn = Integer.parseInt(totalQtyHydPostTxn);

		//********* Vja Location Available and Total Qty in Stock Items Window
		int intAvlReservedQtyVjaStockItemsPreTxn = Integer.parseInt(avlReservedQtyVjaStockItemsPreTxn);
			
		int intTotalQtyVjaStockItemsPreTxn = Integer.parseInt(totalQtyVjaStockItemsPreTxn);
		
		// *********Hyderabad Location Available and Total Qty in Stock Items Window
		int intAvlReservedQtyHydStockItemsPreTxn = Integer.parseInt(avlReservedQtyHydStockItemsPreTxn);
					
		int intTotalQtyHydStockItemsPreTxn = Integer.parseInt(totalQtyHydStockItemsPreTxn);
		
		//*** Hyderabad and Vijayawada Expected Values Manipulation
		int intAvlReservedQtyVjaStockItems = ((intAvlReservedQtyVjaStockItemsPreTxn + intAvlReservedQtyHydStockItemsPreTxn + intLineQuantity)-intBlockedQtyVjaPostTxn);
		int intAvlReservedQtyHydStockItems = ((intAvlReservedQtyHydStockItemsPreTxn + intAvlReservedQtyVjaStockItemsPreTxn + intLineQuantity)-intBlockedQtyHydPostTxn);
		
		int intTotalQtyHydStockItems = intTotalQtyHydStockItemsPreTxn + intTotalQtyVjaStockItemsPreTxn + intLineQuantity;
		int intTotalQtyVjaStockItems = intTotalQtyVjaStockItemsPreTxn + intTotalQtyHydStockItemsPreTxn + intLineQuantity;
		
		//************************Stock Items Line 1 Verifications (Location, Avl Qty, Blocked Qty and Total Qty)
		softAssert.assertEquals(locationVjaStockItemsPreTxn, locationVjaPostTxn, "Location Vja's is present in Stock Items WVjaow");
		
		softAssert.assertEquals(intAvlReservedQtyVjaStockItems, intAvlReservedQtyVjaPostTxn, "Location Vja's Available+Reserved is decreased by "+lineQty+"units");
		
		softAssert.assertEquals(blockedQtyVjaStockItemsPreTxn, blockedQtyVjaPostTxn, "Location Vja's Block quantity is unchanged in Stock Items Window");
		
		softAssert.assertEquals(intTotalQtyVjaStockItems, intTotalQtyVjaPostTxn, "Location Vja's Total quantity is decreased by "+lineQty+"units");
		
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 Verifications (Location, Avl Qty, Blocked Qty and Total Qty)
		softAssert.assertEquals(locationHydStockItemsPreTxn, locationHydPostTxn, "Location Hyderabad is present in Stock Items Window");

		softAssert.assertEquals(intAvlReservedQtyHydStockItems, intAvlReservedQtyHydPostTxn,  "Location Hyderabad's Available+Reserved quantity is increased by "+lineQty+"units");
		
		softAssert.assertEquals(blockedQtyHydStockItemsPreTxn, blockedQtyHydPostTxn, "Location Hyderabad's Block quantity is unchanged in Stock Items Window" );
		
		softAssert.assertEquals(intTotalQtyHydStockItems, intTotalQtyHydPostTxn, "Location Hyderabad's Total quantity is increased by "+lineQty+"units");
		
		waitForExtJSAjaxComplete(5);
		
		close(STOCK_ITEMS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
	
		softAssert.assertAll();
		
		Reporter.log("Stock Correction Transaction for multiple locations in same Warehouse is successfully verified <br>", true);
	}
	
	/**
	 * Testcase ID	 : C90822
	 * Author		 : ssu
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testUnBlockTransactionInSameWarehouseDifferentLocations() throws Exception{

		Reporter.log("<span style='font-weight:bold;color:#000033'> C90822: Unblock Transaction for multiple locations in same Warehouse</span><br>", true);
		
		waitForExtJSAjaxComplete(30);
		
		String warehouseRef = "UnBlockTransactionWH";
		String availableStockColName = "Available Stock";
		String reservedStockColName = "Reserved Stock";
		String blockedStockColName = "Blocked Stock";
		String totalStockColName = "Total Stock";
		String productReference = "6preProdRef";
		String productCode = "6preProdCod";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseRef, "Reference");
		
		waitForExtJSAjaxComplete(10);
		
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
		
		waitForExtJSAjaxComplete(20);
		
		String attributeName = "textContent";

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Stock Items <br>", true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testUnBlockTransactionInSameWarehouseDifferentLocations");
		
		selectWarehouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		//Levels Verification in Left Panel of Stock Items
		StockItemsPageObject stockItemsPO = new StockItemsPageObject();
		
		stockItemsPO.openTransactionLineStockItems("@id", getFilterGridID(), productCode, "Product Code");
		
		waitForExtJSAjaxComplete(10);
		
		stockItemsPO.clickCurrentTab();
		
		//************************Stock Items Line 1 get Cell Text Pre Txn (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationVjaStockItemsPreTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("1", attributeName);
		
		String avlReservedQtyVjaStockItemsPreTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("1", attributeName);
		
		String blockedQtyVjaStockItemsPreTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("1", attributeName);
		
		String totalQtyVjaStockItemsPreTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("1", attributeName);
		
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 get Cell Text Pre Txn (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationHydStockItemsPreTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("2", attributeName);
		
		String avlReservedQtyHydStockItemsPreTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("2", attributeName);
		
		String blockedQtyHydStockItemsPreTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("2", attributeName);
		
		String totalQtyHydStockItemsPreTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("2", attributeName);
		
		waitForExtJSAjaxComplete(5);
		
		close(STOCK_ITEMS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		String transactionType = "Unblock";
		String refereneColName = "Reference";
		String lineQty = "5";
		String locHyd = "HYD";
		String locVja = "VJA";
		int intLineQuantity = Integer.parseInt(lineQty);
		
		waitForExtJSAjaxComplete(20);
		
		selectTransactionType(transactionType);
		
		waitForExtJSAjaxComplete(5);
		
		setWareHouse("Reference", warehouseRef, UNBLOCK_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineProductReferenceFromProductsAndServices("1", productReference, refereneColName);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("1", lineQty);
		
		setTransactionLineLocationFromAllLocations("1", locVja, refereneColName);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineRemark("1", "unBlock 1");
		
		waitForExtJSAjaxComplete(5);
		
		clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineProductReferenceFromProductsAndServices("2", productReference, refereneColName);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("2", lineQty);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineLocationFromAllLocations("2", locHyd, refereneColName);
		
		setTransactionLineRemark("2", "unBlock 2");
		
		waitForExtJSAjaxComplete(5);
				
		execute();
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseRef);

		addWarehousePageObject.clickEditButton(); 
				
		waitForExtJSAjaxComplete(10);
				
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
				
		waitForExtJSAjaxComplete(10);
		
		String strAvailableStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),availableStockColName);
		String strReservedStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),reservedStockColName);
		String strBlockedStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),blockedStockColName);
		String strTotalStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),totalStockColName);
		
		int availableStockValuePostTxn1 = Integer.parseInt(strAvailableStockValuePostTxn1);
		int totalStockValuePostTxn1 = Integer.parseInt(strTotalStockValuePostTxn1);
		int blockStockValuePostTxn1 = Integer.parseInt(strBlockedStockValuePostTxn1);
		int reservedValuePostTxn1 = Integer.parseInt(strReservedStockValuePostTxn1);
		  
		//*****************************Line 1 Assertions***********************
		softAssert.assertTrue((reservedValuePostTxn1==reservedStockValue), productReference+"Reserved stock quantity is unchanged");
		softAssert.assertTrue((blockStockValuePostTxn1 ==(blockedStockValue-(intLineQuantity*2))), productReference+" blocked stock quantity is decreased by "+lineQty+"units");
		softAssert.assertTrue((totalStockValuePostTxn1==totalStockValue), productReference+" Total stock quantity is unchanged by this transaction");
		softAssert.assertTrue((availableStockValuePostTxn1==(availableStockValue+(intLineQuantity*2))), productReference+" available stock quantity is increased by "+lineQty+"units");
					
		addWarehousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Stock Items <br>", true);
		
		selectWarehouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		//Levels Verification in Left Panel of Stock Items
		stockItemsPO.openTransactionLineStockItems("@id", getFilterGridID(), productCode, "Product Code");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(stockItemsPO.verifyAvailableStockFromStockItemsLeftPanel(strAvailableStockValuePostTxn1), "Available stock: <number> is present in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyReservedStockFromStockItemsLeftPanel(strReservedStockValuePostTxn1), "Reserved stock: <number> is available in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyBlockedStockFromStockItemsLeftPanel(strBlockedStockValuePostTxn1), "Blocked stock: <number> is available in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyTotalStockFromStockItemsLeftPanel(strTotalStockValuePostTxn1), "Total stock: <number> is available in left panel");
				
		waitForExtJSAjaxComplete(5);
		
		stockItemsPO.clickCurrentTab();
		
		//************************Stock Items Line 1 get Cell Text Value (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationVjaPostTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("1", attributeName);
		
		String avlReservedQtyVjaPostTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("1", attributeName);
		int intAvlReservedQtyVjaPostTxn = Integer.parseInt(avlReservedQtyVjaPostTxn);
		
		String blockedQtyVjaPostTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("1", attributeName);
		int intBlockedQtyVjaPostTxn = Integer.parseInt(blockedQtyVjaPostTxn);
				
		String totalQtyVjaPostTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("1", attributeName);
		
				
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 get Cell Text Value (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationHydPostTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("2", attributeName);
		
		String avlReservedQtyHydPostTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("2", attributeName);
		int intAvlReservedQtyHydPostTxn = Integer.parseInt(avlReservedQtyHydPostTxn);
		
		String blockedQtyHydPostTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("2", attributeName);
		int intBlockedQtyHydPostTxn = Integer.parseInt(blockedQtyHydPostTxn);
		
		String totalQtyHydPostTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("2", attributeName);

		//********* Vja Location Blocked and Total Qty in Stock Items Window
		int intAvlReservedQtyVjaStockItemsPreTxn = Integer.parseInt(avlReservedQtyVjaStockItemsPreTxn);
		int intAvlReservedQtyVjaStockItems = intAvlReservedQtyVjaStockItemsPreTxn + intLineQuantity;
			
		int intBlockedQtyVjaStockItemsPreTxn = Integer.parseInt(blockedQtyVjaStockItemsPreTxn);
		int intBlockedQtyVjaStockItems = intBlockedQtyVjaStockItemsPreTxn - intLineQuantity;
		
		// *********Hyderabad Location Blocked and Total Qty in Stock Items Window
		int intAvlReservedQtyHydStockItemsPreTxn = Integer.parseInt(avlReservedQtyHydStockItemsPreTxn);
		int intAvlReservedQtyHydStockItems = intAvlReservedQtyHydStockItemsPreTxn + intLineQuantity;
					
		int intBlockedQtyHydStockItemsPreTxn = Integer.parseInt(blockedQtyHydStockItemsPreTxn);
		int intBlockedQtyHydStockItems = intBlockedQtyHydStockItemsPreTxn - intLineQuantity;
		
		//************************Stock Items Line 1 Verifications (Location, Avl Qty, Blocked Qty and Total Qty)
		softAssert.assertEquals(locationVjaStockItemsPreTxn, locationVjaPostTxn, "Location Vja's is present in Stock Items WVjaow");
		
		softAssert.assertEquals(intAvlReservedQtyVjaStockItems, intAvlReservedQtyVjaPostTxn, "Location Vja's Available is increased by "+lineQty+"units");
		
		softAssert.assertEquals(intBlockedQtyVjaStockItems, intBlockedQtyVjaPostTxn, "Location Vja's Block quantity is decreased by "+lineQty+"units");
		
		softAssert.assertEquals(totalQtyVjaStockItemsPreTxn, totalQtyVjaPostTxn, "Location Vja's Total quantity  is unchanged in Stock Items Window");
		
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 Verifications (Location, Avl Qty, Blocked Qty and Total Qty)
		softAssert.assertEquals(locationHydStockItemsPreTxn, locationHydPostTxn, "Location Hyderabad is present in Stock Items Window");

		softAssert.assertEquals(intAvlReservedQtyHydStockItems, intAvlReservedQtyHydPostTxn,  "Location Hyderabad's Available quantity is increased by "+lineQty+"units");
		
		softAssert.assertEquals(intBlockedQtyHydStockItems, intBlockedQtyHydPostTxn, "Location Hyderabad's Block quantity is decreased by "+lineQty+"units");
		
		softAssert.assertEquals(totalQtyHydStockItemsPreTxn, totalQtyHydPostTxn, "Location Hyderabad's Total quantity  is unchanged in Stock Items Window");
		
		waitForExtJSAjaxComplete(5);
		
		close(STOCK_ITEMS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
	
		softAssert.assertAll();
		
		Reporter.log("UnBlock Transaction for multiple locations in same Warehouse is successfully verified <br>", true);
	}
	
	/**
	 * Testcase ID	 : C90818
	 * Author		 : ssu
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testBlockTransactionInSameWarehouseDifferentLocations() throws Exception{

		Reporter.log("<span style='font-weight:bold;color:#000033'> C90818: Block Transaction for multiple locations in same Warehouse</span><br>", true);
		
		waitForExtJSAjaxComplete(30);
		
		String warehouseRef = "StockItemsBlockedWH";
		String availableStockColName = "Available Stock";
		String reservedStockColName = "Reserved Stock";
		String blockedStockColName = "Blocked Stock";
		String totalStockColName = "Total Stock";
		String productReference = "aqapreProdRef3";
		String productCode = "aqapreProdCod3";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseRef, "Reference");
		
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
		
		waitForExtJSAjaxComplete(20);
		
		String attributeName = "textContent";

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Stock Items <br>", true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testBlockTransactionInSameWarehouseDifferentLocations");
		
		selectWarehouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		//Levels Verification in Left Panel of Stock Items
		StockItemsPageObject stockItemsPO = new StockItemsPageObject();
		
		stockItemsPO.openTransactionLineStockItems("@id", getFilterGridID(), productCode, "Product Code");
		
		waitForExtJSAjaxComplete(10);
		
		stockItemsPO.clickCurrentTab();
		
		//************************Stock Items Line 1 get Cell Text Pre Txn (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationIndStockItemsPreTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("1", attributeName);
		
		String avlReservedQtyIndStockItemsPreTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("1", attributeName);
		
		String blockedQtyIndStockItemsPreTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("1", attributeName);
		
		String totalQtyIndStockItemsPreTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("1", attributeName);
		
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 get Cell Text Pre Txn (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationHydStockItemsPreTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("2", attributeName);
		
		String avlReservedQtyHydStockItemsPreTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("2", attributeName);
		
		String blockedQtyHydStockItemsPreTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("2", attributeName);
		
		String totalQtyHydStockItemsPreTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("2", attributeName);
		
		waitForExtJSAjaxComplete(5);
		
		close(STOCK_ITEMS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		String transactionType = "Block";
		String refereneColName = "Reference";
		String lineQty = "5";
		String locHyd = "HYD";
		String locInd = "IND";
		int intLineQuantity = Integer.parseInt(lineQty);
		
		waitForExtJSAjaxComplete(20);
		
		selectTransactionType(transactionType);
		
		waitForExtJSAjaxComplete(15);
		
		setWareHouse("Reference", warehouseRef, BLOCK_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(15);
		
		clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineProductReferenceFromProductsAndServices("1", productReference, refereneColName);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("1", lineQty);
		
		setTransactionLineLocationFromAllLocations("1", locInd, refereneColName);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineRemark("1", "Block 1");
		
		waitForExtJSAjaxComplete(5);
		
		clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineProductReferenceFromProductsAndServices("2", productReference, refereneColName);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("2", lineQty);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineLocationFromAllLocations("2", locHyd, refereneColName);
		
		setTransactionLineRemark("2", "Block 2");
		
		waitForExtJSAjaxComplete(5);
				
		execute();
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseRef);

		addWarehousePageObject.clickEditButton(); 
				
		waitForExtJSAjaxComplete(10);
				
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
				
		waitForExtJSAjaxComplete(10);
		
		String strAvailableStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),availableStockColName);
		String strReservedStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),reservedStockColName);
		String strBlockedStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),blockedStockColName);
		String strTotalStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),totalStockColName);
		
		int availableStockValuePostTxn1 = Integer.parseInt(strAvailableStockValuePostTxn1);
		int totalStockValuePostTxn1 = Integer.parseInt(strTotalStockValuePostTxn1);
		int blockStockValuePostTxn1 = Integer.parseInt(strBlockedStockValuePostTxn1);
		int reservedValuePostTxn1 = Integer.parseInt(strReservedStockValuePostTxn1);
		  
		//*****************************Line 1 Assertions***********************
		softAssert.assertTrue((reservedValuePostTxn1==reservedStockValue), productReference+" Reserved stock quantity is unchanged");
		softAssert.assertTrue((blockStockValuePostTxn1 ==(blockedStockValue+(intLineQuantity*2))), productReference+" blocked stock quantity is increased by "+lineQty+"units");
		softAssert.assertTrue((totalStockValuePostTxn1==totalStockValue), productReference+" Total stock quantity is unchanged by this transaction");
		softAssert.assertTrue((availableStockValuePostTxn1==(availableStockValue-(intLineQuantity*2))), productReference+" available stock quantity is decreased by "+lineQty+"units");
					
		addWarehousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Stock Items <br>", true);
		
		selectWarehouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		//Levels Verification in Left Panel of Stock Items
		stockItemsPO.openTransactionLineStockItems("@id", getFilterGridID(), productCode, "Product Code");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(stockItemsPO.verifyAvailableStockFromStockItemsLeftPanel(strAvailableStockValuePostTxn1), "Available stock: <number> is present in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyReservedStockFromStockItemsLeftPanel(strReservedStockValuePostTxn1), "Reserved stock: <number> is available in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyBlockedStockFromStockItemsLeftPanel(strBlockedStockValuePostTxn1), "Blocked stock: <number> is available in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyTotalStockFromStockItemsLeftPanel(strTotalStockValuePostTxn1), "Total stock: <number> is available in left panel");
				
		waitForExtJSAjaxComplete(5);
		
		stockItemsPO.clickCurrentTab();
		
		//************************Stock Items Line 1 get Cell Text Value (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationIndPostTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("1", attributeName);
		
		String avlReservedQtyIndPostTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("1", attributeName);
		int intAvlReservedQtyIndPostTxn = Integer.parseInt(avlReservedQtyIndPostTxn);
		
		String blockedQtyIndPostTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("1", attributeName);
		int intBlockedQtyIndPostTxn = Integer.parseInt(blockedQtyIndPostTxn);
				
		String totalQtyIndPostTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("1", attributeName);
		
				
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 get Cell Text Value (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationHydPostTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("2", attributeName);
		
		String avlReservedQtyHydPostTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("2", attributeName);
		int intAvlReservedQtyHydPostTxn = Integer.parseInt(avlReservedQtyHydPostTxn);
		
		String blockedQtyHydPostTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("2", attributeName);
		int intBlockedQtyHydPostTxn = Integer.parseInt(blockedQtyHydPostTxn);
		
		String totalQtyHydPostTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("2", attributeName);

		//********* Vja Location Blocked and Total Qty in Stock Items Window
		int intAvlReservedQtyIndStockItemsPreTxn = Integer.parseInt(avlReservedQtyIndStockItemsPreTxn);
		int intAvlReservedQtyIndStockItems = intAvlReservedQtyIndStockItemsPreTxn - intLineQuantity;
			
		int intBlockedQtyIndStockItemsPreTxn = Integer.parseInt(blockedQtyIndStockItemsPreTxn);
		int intBlockedQtyIndStockItems = intBlockedQtyIndStockItemsPreTxn + intLineQuantity;
		
		// *********Hyderabad Location Blocked and Total Qty in Stock Items Window
		int intAvlReservedQtyHydStockItemsPreTxn = Integer.parseInt(avlReservedQtyHydStockItemsPreTxn);
		int intAvlReservedQtyHydStockItems = intAvlReservedQtyHydStockItemsPreTxn - intLineQuantity;
					
		int intBlockedQtyHydStockItemsPreTxn = Integer.parseInt(blockedQtyHydStockItemsPreTxn);
		int intBlockedQtyHydStockItems = intBlockedQtyHydStockItemsPreTxn + intLineQuantity;
		
		//************************Stock Items Line 1 Verifications (Location, Avl Qty, Blocked Qty and Total Qty)
		softAssert.assertEquals(locationIndStockItemsPreTxn, locationIndPostTxn, "Location India's is present in Stock Items WVjaow");
		
		softAssert.assertEquals(intAvlReservedQtyIndStockItems, intAvlReservedQtyIndPostTxn, "Location India's Available is decreased by "+lineQty+"units");
		
		softAssert.assertEquals(intBlockedQtyIndStockItems, intBlockedQtyIndPostTxn, "Location India's Block quantity is increased by "+lineQty+"units");
		
		softAssert.assertEquals(totalQtyIndStockItemsPreTxn, totalQtyIndPostTxn, "Location India's Total quantity  is unchanged in Stock Items Window");
		
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 Verifications (Location, Avl Qty, Blocked Qty and Total Qty)
		softAssert.assertEquals(locationHydStockItemsPreTxn, locationHydPostTxn, "Location Hyderabad is present in Stock Items Window");

		softAssert.assertEquals(intAvlReservedQtyHydStockItems, intAvlReservedQtyHydPostTxn,  "Location Hyderabad's Available quantity is decreased by "+lineQty+"units");
		
		softAssert.assertEquals(intBlockedQtyHydStockItems, intBlockedQtyHydPostTxn, "Location Hyderabad's Block quantity is increased by "+lineQty+"units");
		
		softAssert.assertEquals(totalQtyHydStockItemsPreTxn, totalQtyHydPostTxn, "Location Hyderabad's Total quantity  is unchanged in Stock Items Window");
		
		waitForExtJSAjaxComplete(5);
		
		close(STOCK_ITEMS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
	
		softAssert.assertAll();
		
		Reporter.log("Block Transaction for multiple locations in same Warehouse is successfully verified <br>", true);
	}
	
	
	/**
	 * TestCase ID : C90763
	 * Author : ssa
	 */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testExecutedTxnAreUneditable() throws Exception{
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90763:Executed transactions are read only</span><br>",true);
		
		String transactionReference = "C40910Ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String wareHouseLoc = "HYD";
		String warehouseReference = "GRTransactionWH";
		String stockItemRef = "2preConsRef";
		String lineQuantity = "10";
		String purchasingOrg = "AQAPurchOrg";
		String lookUpWindowCol = "Reference";
		String remark= "C40910remark"+getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedDate = this.getFutureDate(3);
		String supplierName = "My Company";
		
		SoftAssert softAssert = new SoftAssert();
        softAssert.setMethodName("testExecutedTxnAreUneditable");
        
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
			
		waitForExtJSAjaxComplete(20);
			
		selectWarehouse(warehouseReference);
			
		waitForExtJSAjaxComplete(20);
			
	    selectTransactionType("Goods Receipt");
		
		waitForExtJSAjaxComplete(20);
		
        setWareHouse("Reference", warehouseReference, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		setReference( transactionReference, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		setStatus("Planned",GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER );
		
		setTransactionDate(expectedDate, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		selectSupplier("Name", supplierName, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		 
		waitForExtJSAjaxComplete(20);
		
		clickAddTransactionLines();

		MenuSelector.selectMenuItemNative(driver, "Stock");

		waitForExtJSAjaxComplete(20);

		setTransactionLineProductReferenceFromProductsAndServices("2", stockItemRef, lookUpWindowCol);
		
	    waitForExtJSAjaxComplete(20);
		
		setTransactionLineQuantity("1",  lineQuantity);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineRemark("1",  remark);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineLocationFromAllLocations("1",  wareHouseLoc, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);

		setPurchasingOrgForTransactionLine("1",  purchasingOrg, lookUpWindowCol);
		
        waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(10);
		
		setStatus("Executed",GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER );
		
		waitForExtJSAjaxComplete(20);
		
		saveAndClose(GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		//filterGrid("@realid", "mogrid", remark, "Remark");
		
		//waitForExtJSAjaxComplete(25);
		
		//waitForExtJSAjaxComplete(25);
		
		openTransactionLine("@realid", "mogrid", remark, "Remark");
		
		waitForExtJSAjaxComplete(20);
		
		//Verify all fields of warehouse is uneditable
		softAssert.assertTrue(isWareHouseCodeOfTransactionEditable(),"Unable to edit Warehouse Code");
		softAssert.assertTrue(isReferenceFieldOfTransactionEditable(),"unable to edit Goods Receipt Reference");
		softAssert.assertTrue(isStatusFieldOfTransactionEditable(),"unable to edit status in Goods Receipt");
		softAssert.assertTrue(isExpectedReceiptDateOfTransactionEditable(),"unable to edit Goods Receipt Date");
		softAssert.assertTrue(isSupplierFiledOfTransactionEditable(),"unable to edit Supplier in Goods Receipt");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("Executed transaction fields are read only<br>",  true);	
	}
	
	/**
	 * Testcase ID	 : C90764 
	 * Author		 : ssu
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testTransactionLineStatuses() throws Exception{
		
		Reporter.log(
				"<span style='font-weight:bold;color:#000033'> C90764: Transaction Line status should be displayed accordingly based on the option selected from 'Status' drop down field in goods issue form</span><br>",
				true);
				
		waitForExtJSAjaxComplete(30);	

		Reporter.log("Create a New Goods Issue Transaction <br>", true);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testTransactionLineStatuses");
		
		//Variable Initialization
		String warehouseRef = "GIStatusCheckWH";
		String reference = "C51176Ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedDate = this.getFutureDate(3);
		String customerName = "My Company";
		String collectingPerson = "SELENIUM";
		String wareHouseLoc = "IND";
		String stockItemRef = "2preConsRef";
		String stockItemCode = "2preConsCod";
		String lineQuantity = "1";
		String textLineUOM = "pc";
		String lookUpWindowCol = "Reference";
		String remarkTransactionLine1 = "rem"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		waitForExtJSAjaxComplete(20);
			
		clickNewGoodsIssueToolBarButton();
		
		waitForExtJSAjaxComplete(10);
		
		this.setWareHouse("Reference", warehouseRef, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		setReference(reference, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		setTransactionDate(expectedDate, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		selectCustomer("Customer Name", customerName, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		selectCollectingPerson("Last Name", collectingPerson, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		//***************************Stock items transaction Line 1************************
		this.clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineProductReferenceFromProductsAndServices("1", stockItemRef, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(10);
		
		String stockLineProdCode1 = this.getProductCodeOfTransactionLine("1");
		
		String stockUOM1 = this.getUOMOfTransactionLine("1");
		
		softAssert.assertEquals(stockLineProdCode1, stockItemCode, "Product Code is displayed in Transaction Line1");
	
		softAssert.assertEquals(stockUOM1, textLineUOM, "UOM is displayed in Transaction Line1");
	
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("1", lineQuantity);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineLocationFromAllLocations("1", wareHouseLoc, lookUpWindowCol);
		
		setTransactionLineRemark("1", remarkTransactionLine1);
		
		waitForExtJSAjaxComplete(10);
		
		//*****************Verifying status in Transaction Line**********************
		
		String statusTransactionLine1 = getStatusOfTransactionLine("1");
		String statusPlannedAndExecuted = "Reserved";
		String statusRequested = "Requested";
		String statusCanceled = "Not In Stock";
		
		statusTransactionLine1 = getStatusOfTransactionLine("1");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertEquals(statusTransactionLine1, statusPlannedAndExecuted, "Reserved Status is Displayed in Transaction Line");
		
		setStatus("Requested", GOODS_ISSUE_TRANSACTION_WINDOW_HEADER );
		
		statusTransactionLine1 = getStatusOfTransactionLine("1");
		
		softAssert.assertEquals(statusTransactionLine1, statusRequested, "Requested Status is Displayed in Transaction Line");
		
		waitForExtJSAjaxComplete(5);
		
		setStatus("Planned", GOODS_ISSUE_TRANSACTION_WINDOW_HEADER );
		
		waitForExtJSAjaxComplete(5);
		
		save();
		
		waitForExtJSAjaxComplete(10);
		
		setStatus("Executed", GOODS_ISSUE_TRANSACTION_WINDOW_HEADER );
		
		waitForExtJSAjaxComplete(5);
		
		statusTransactionLine1 = getStatusOfTransactionLine("1");
		
		softAssert.assertEquals(statusTransactionLine1, statusPlannedAndExecuted, "Reserved Status is Displayed in Transaction Line");
		
		waitForExtJSAjaxComplete(5);
		
		setStatus("Canceled", GOODS_ISSUE_TRANSACTION_WINDOW_HEADER );
		
		statusTransactionLine1 = getStatusOfTransactionLine("1");
		
		softAssert.assertEquals(statusTransactionLine1, statusCanceled, "Not in Stock Status is Displayed in Transaction Line");
				
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
	
		Reporter.log("Transaction Line status is displayed accordingly based on the option selected from 'Status' drop down field in goods issue form", true);
	}
	

	/**
	 * Testcase ID	 : C90829 
	 * Author		 : ssu
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testNegativeAvailableQuantityOfStockCorrection() throws Exception{
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> C90829: Stock correction makes available quantity negative</span><br>", true);
				
		waitForExtJSAjaxComplete(30);	
		
		String warehouseRef = "GoodsIssueWH";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		String availableStockColName = "Available Stock";
		String reservedStockColName = "Reserved Stock";
		String blockedStockColName = "Blocked Stock";
		String totalStockColName = "Total Stock";
		String productReference = "2preConsRef";
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(25);
		
		waitForMaskDisappear();
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseRef, "Reference");

		waitForExtJSAjaxComplete(15);
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseRef);

		AddWarehousePageObject addWarehousePageObject = new AddWarehousePageObject();
				
		addWarehousePageObject.clickEditButton(); 
				
		waitForExtJSAjaxComplete(10);
				
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
				
		waitForExtJSAjaxComplete(10);
				
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
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		waitForExtJSAjaxComplete(20);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testNegativeAvailableQuantityOfStockCorrection");
		
		String transactionType = "Stock Correction";
		String refereneColName = "Reference";
		String lineQty = "5";
		String locInd = "IND";
		int intLineQuantity = Integer.parseInt(lineQty);
		
		waitForExtJSAjaxComplete(20);
		
		selectTransactionType(transactionType);
		
		waitForExtJSAjaxComplete(5);
		
		setWareHouse("Reference", warehouseRef, STOCK_CORRECTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineProductReferenceFromProductsAndServices("1", productReference, refereneColName);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("1", ""+(reservedStockValue-intLineQuantity));
		
		setTransactionLineLocationFromAllLocations("1", locInd, refereneColName);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineRemark("1", "stock correction 1");
		
		waitForExtJSAjaxComplete(5);
		
		execute();
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseRef);

		addWarehousePageObject.clickEditButton(); 
				
		waitForExtJSAjaxComplete(10);
				
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
				
		waitForExtJSAjaxComplete(10);
		
		String strAvailableStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),availableStockColName);
		String strReservedStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),reservedStockColName);
		String strBlockedStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),blockedStockColName);
		String strTotalStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),totalStockColName);
		
		int availableStockValuePostTxn1 = Integer.parseInt(strAvailableStockValuePostTxn1);
		int totalStockValuePostTxn1 = Integer.parseInt(strTotalStockValuePostTxn1);
		int blockStockValuePostTxn1 = Integer.parseInt(strBlockedStockValuePostTxn1);
		int reservedValuePostTxn1 = Integer.parseInt(strReservedStockValuePostTxn1);
		  
		//*****************************Line 1 Assertions***********************
		softAssert.assertTrue((reservedValuePostTxn1==reservedStockValue), productReference+"Reserved stock quantity is unchanged");
		softAssert.assertTrue((blockStockValuePostTxn1 ==blockedStockValue), productReference+" blocked stock quantity is unchanged");
		softAssert.assertTrue((totalStockValuePostTxn1==(reservedStockValue-intLineQuantity)), productReference+" Total stock quantity is the new Total Of Stock Correction");
		softAssert.assertTrue((availableStockValuePostTxn1==(-intLineQuantity)), productReference+" available stock quantity is -"+lineQty+"units");
					
		addWarehousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("Stock correction makes available quantity negative is successfully verified", true);
	}
	
	/**
	 * Testcase ID	 : C90809
	 * Author		 : ssu
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testReturnFromCustomerInSameWarehouseDifferentLocations() throws Exception{

		Reporter.log("<span style='font-weight:bold;color:#000033'> C90809: Return from Customer Transaction for multiple locations in same Warehouse</span><br>", true);
		
		waitForExtJSAjaxComplete(30);
		
		String warehouseRef = "ReturnFromCustomerWH";
		String availableStockColName = "Available Stock";
		String reservedStockColName = "Reserved Stock";
		String blockedStockColName = "Blocked Stock";
		String totalStockColName = "Total Stock";
		String productReference = "4preConsRef";
		String productCode = "4preConsCod";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseRef, "Reference");

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
		
		waitForExtJSAjaxComplete(20);
		
		String attributeName = "textContent";

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Stock Items <br>", true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReturnFromCustomerInSameWarehouseDifferentLocations");
		
		selectWarehouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		//Levels Verification in Left Panel of Stock Items
		StockItemsPageObject stockItemsPO = new StockItemsPageObject();
		
		stockItemsPO.openTransactionLineStockItems("@id", getFilterGridID(), productCode, "Product Code");
		
		waitForExtJSAjaxComplete(10);
		
		stockItemsPO.clickCurrentTab();
		
		//************************Stock Items Line 1 get Cell Text Pre Txn (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationIndStockItemsPreTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("1", attributeName);
		
		String avlReservedQtyIndStockItemsPreTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("1", attributeName);
		
		String blockedQtyIndStockItemsPreTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("1", attributeName);
		
		String totalQtyIndStockItemsPreTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("1", attributeName);
		
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 get Cell Text Pre Txn (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationHydStockItemsPreTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("2", attributeName);
		
		String avlReservedQtyHydStockItemsPreTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("2", attributeName);

		String blockedQtyHydStockItemsPreTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("2", attributeName);
		
		String totalQtyHydStockItemsPreTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("2", attributeName);

		waitForExtJSAjaxComplete(5);
		
		close(STOCK_ITEMS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		String transactionType = "Goods Return";
		String refereneColName = "Reference";
		String lineQty = "1";
		String locHyd = "HYD";
		String locInd = "IND";
		int intLineQuantity = Integer.parseInt(lineQty);
		String reference = "Goods Return";
		String expectedDate = this.getFutureDate(0);
		String supplierName = "My Company";
		String collectingPerson="SELENIUM";
		
		waitForExtJSAjaxComplete(20);
		
		selectTransactionType(transactionType);
		
		waitForExtJSAjaxComplete(5);
		
		setWareHouse("Reference", warehouseRef, RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		setReference(reference, RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER);
		
		setTransactionDate(expectedDate, RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER);
		
		selectCustomer("Name", supplierName, RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER);
		
		selectCollectingPerson("Last Name", collectingPerson, RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER);
		//**************************Line 1******************************************************
		
		clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineProductReferenceFromProductsAndServices("1", productReference, refereneColName);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("1", lineQty);
		
		setTransactionLineLocationFromAllLocations("1", locInd, refereneColName);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineRemark("1", "Rfc 1");
		
		setTransactionLineRemark("1", "Rfc 1");
		
		waitForExtJSAjaxComplete(5);
		
		clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineProductReferenceFromProductsAndServices("2", productReference, refereneColName);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("2", lineQty);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineLocationFromAllLocations("2", locHyd, refereneColName);
		
		setTransactionLineRemark("2", "Rfc 2");
		
		setTransactionLineRemark("2", "Rfc 2");
		
		waitForExtJSAjaxComplete(5);
				
		save();
		
		waitForExtJSAjaxComplete(20);
		
		setStatus("Executed", RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER );
		
		saveAndClose(RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseRef);

		addWarehousePageObject.clickEditButton(); 
				
		waitForExtJSAjaxComplete(10);
				
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
				
		waitForExtJSAjaxComplete(10);
		
		String strAvailableStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),availableStockColName);
		String strReservedStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),reservedStockColName);
		String strBlockedStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),blockedStockColName);
		String strTotalStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),totalStockColName);
		
		int availableStockValuePostTxn1 = Integer.parseInt(strAvailableStockValuePostTxn1);
		int totalStockValuePostTxn1 = Integer.parseInt(strTotalStockValuePostTxn1);
		int blockStockValuePostTxn1 = Integer.parseInt(strBlockedStockValuePostTxn1);
		int reservedValuePostTxn1 = Integer.parseInt(strReservedStockValuePostTxn1);
		
		//*****************************Line 1 Assertions***********************
		softAssert.assertTrue((reservedValuePostTxn1==reservedStockValue), productReference+"Reserved stock quantity is unchanged");
		softAssert.assertTrue((blockStockValuePostTxn1 ==(blockedStockValue+(intLineQuantity*2))), productReference+" blocked stock quantity is increased by "+lineQty+"units");
		softAssert.assertTrue((totalStockValuePostTxn1==(totalStockValue+(intLineQuantity*2))), productReference+" Total stock quantity is increased by "+lineQty+"units");
		softAssert.assertTrue((availableStockValuePostTxn1==availableStockValue), productReference+" available stock quantity is unchanged");
					
		addWarehousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Stock Items <br>", true);
		
		selectWarehouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		//Levels Verification in Left Panel of Stock Items
		stockItemsPO.openTransactionLineStockItems("@id", getFilterGridID(), productCode, "Product Code");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(stockItemsPO.verifyAvailableStockFromStockItemsLeftPanel(strAvailableStockValuePostTxn1), "Available stock: <number> is present in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyReservedStockFromStockItemsLeftPanel(strReservedStockValuePostTxn1), "Reserved stock: <number> is available in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyBlockedStockFromStockItemsLeftPanel(strBlockedStockValuePostTxn1), "Blocked stock: <number> is available in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyTotalStockFromStockItemsLeftPanel(strTotalStockValuePostTxn1), "Total stock: <number> is available in left panel");
				
		waitForExtJSAjaxComplete(5);
		
		stockItemsPO.clickCurrentTab();
		
		//************************Stock Items Line 1 get Cell Text Value (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationIndPostTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("1", attributeName);
		
		String avlReservedQtyIndPostTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("1", attributeName);
		
		String blockedQtyIndPostTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("1", attributeName);
		int intBlockedQtyIndPostTxn = Integer.parseInt(blockedQtyIndPostTxn);
				
		String totalQtyIndPostTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("1", attributeName);
		int intTotalQtyIndPostTxn = Integer.parseInt(totalQtyIndPostTxn);
				
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 get Cell Text Value (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationHydPostTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("2", attributeName);
		
		String avlReservedQtyHydPostTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("2", attributeName);
		
		String blockedQtyHydPostTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("2", attributeName);
		int intBlockedQtyHydPostTxn = Integer.parseInt(blockedQtyHydPostTxn);
		
		String totalQtyHydPostTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("2", attributeName);
		int intTotalQtyHydPostTxn = Integer.parseInt(totalQtyHydPostTxn);

		//********* India Location blocked and Total Qty in Stock Items Window
		int intBlockedQtyIndStockItemsPreTxn = Integer.parseInt(blockedQtyIndStockItemsPreTxn);
			
		int intTotalQtyIndStockItemsPreTxn = Integer.parseInt(totalQtyIndStockItemsPreTxn);
		
		//*********Hyderabad Location blocked and Total Qty in Stock Items Window
		int intBlockedQtyHydStockItemsPreTxn = Integer.parseInt(blockedQtyHydStockItemsPreTxn);
					
		int intTotalQtyHydStockItemsPreTxn = Integer.parseInt(totalQtyHydStockItemsPreTxn);
		
		//*** Hyderabad and Vijayawada Expected Values Manipulation
		int intBlockedQtyIndStockItems = intBlockedQtyIndStockItemsPreTxn + (intLineQuantity);
		int intBlockedQtyHydStockItems = intBlockedQtyHydStockItemsPreTxn + (intLineQuantity);
		
		int intTotalQtyHydStockItems = intTotalQtyHydStockItemsPreTxn + (intLineQuantity);
		int intTotalQtyIndStockItems = intTotalQtyIndStockItemsPreTxn + (intLineQuantity);
		
		//************************Stock Items Line 1 Verifications (Location, Avl Qty, Blocked Qty and Total Qty)
		softAssert.assertEquals(locationIndStockItemsPreTxn, locationIndPostTxn, "Location India's is present in Stock Items WVjaow");
		
		softAssert.assertEquals(avlReservedQtyIndStockItemsPreTxn, avlReservedQtyIndPostTxn, "Location India's Available+Reserved is unchanged in Stock Items Window");
		
		softAssert.assertEquals(intBlockedQtyIndStockItems, intBlockedQtyIndPostTxn, "Location India's Block quantity is increased by "+lineQty+"units");
		
		softAssert.assertEquals(intTotalQtyIndStockItems, intTotalQtyIndPostTxn, "Location India's Total quantity is increased by "+lineQty+"units");
		
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 Verifications (Location, Avl Qty, Blocked Qty and Total Qty)
		softAssert.assertEquals(locationHydStockItemsPreTxn, locationHydPostTxn, "Location Hyderabad is present in Stock Items Window");

		softAssert.assertEquals(avlReservedQtyHydStockItemsPreTxn, avlReservedQtyHydPostTxn,  "Location Hyderabad's Available+Reserved quantity is unchanged in Stock Items Window");
		
		softAssert.assertEquals(intBlockedQtyHydStockItems, intBlockedQtyHydPostTxn, "Location Hyderabad's Block quantity is increased by "+lineQty+"units" );
		
		softAssert.assertEquals(intTotalQtyHydStockItems, intTotalQtyHydPostTxn, "Location Hyderabad's Total quantity is increased by "+lineQty+"units");
		
		waitForExtJSAjaxComplete(5);
		
		close(STOCK_ITEMS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
	
		softAssert.assertAll();
		
		Reporter.log("Return From Customer Transaction for multiple locations in same Warehouse is successfully verified <br>", true);
	}
	
	

	/**
	 * Testcase ID	 : C90828
	 * Author		 : ssa
	 */
	 
	 	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testAvailableQuantityZeroOfStockCorrection() throws Exception{
		
		String remark1 = "C52555Rmark1"+getCurrentDate().substring(getCurrentDate().length()-5);
		String remark2 = "C35987Rmark2"+getCurrentDate().substring(getCurrentDate().length()-5);
		String wareHouseLoc = "HYD";
		String warehouseReference = "SCTxnFrmStockItemsWH";
		String stockItemRef1 = "2preConsRef";
		
		String lineQuantity = "0";
		String lookUpWindowCol = "Reference";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		String availableStockColName = "Available Stock";
		String totalStockColName="Total Stock";
		String blockedStockColName="Blocked Stock";
		String reservedStockColName = "Reserved Stock";
		
		int intLineQuantity = Integer.parseInt(lineQuantity);
		
		SoftAssert softAssert = new SoftAssert();
		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90828:Stock correction makes available quantity zero <br></span>", true); 
		
		softAssert.setMethodName("testAvailableQuantityZeroOfStockCorrection");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),availableStockColName ));
		int totalStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),totalStockColName));
		int blockStockValue1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),blockedStockColName));
		int reservedStockValue1  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),reservedStockColName));
		
		Reporter.log(stockItemRef1+" available stock quantity is "+availableStockValue1, true);
		Reporter.log(stockItemRef1+" total stock quantity is "+totalStockValue1, true);
		Reporter.log(stockItemRef1+" blocked stock quantity is "+blockStockValue1, true);
		Reporter.log(stockItemRef1+" reserved stock quantity is "+reservedStockValue1, true);
		
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		waitForExtJSAjaxComplete(20);
		
		this.selectTransactionType("Stock Correction");
		
		waitForExtJSAjaxComplete(20);
		
		this.setWareHouse(lookUpWindowCol, warehouseReference, STOCK_CORRECTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		//**************************Line 1******************************************************
		
		this.clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineProductReferenceFromProductsAndServices("1", stockItemRef1,lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		//XXX : CHECKME: For available quantity 
	    lineQuantity= lineQuantity+(totalStockValue1-availableStockValue1); 
		
	    setTransactionLineQuantity("1",lineQuantity);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineLocationFromAllLocations("1",  wareHouseLoc, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineRemark("1", remark1);
		
		waitForExtJSAjaxComplete(20);
		
		execute();
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		intLineQuantity = Integer.parseInt(lineQuantity);
		waitForExtJSAjaxComplete(20);
		
		int availableStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),availableStockColName ));
		int totalStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),totalStockColName));
		int blockStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),blockedStockColName));
		int reservedStockPostTxn1  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),reservedStockColName));

		//*****************************Line 1 Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn1==0), stockItemRef1+" available stock quantity is zero");
		softAssert.assertTrue((totalStockValuePostTxn1==(intLineQuantity)), stockItemRef1+"total Stock becomes the quantity entered in transaction");
		softAssert.assertTrue((blockStockValuePostTxn1==blockStockValue1), stockItemRef1+" blocked stock quantity is unchanged");
		softAssert.assertTrue((reservedStockPostTxn1==reservedStockValue1), stockItemRef1+" reserved stock quantity is unchanged by this transaction");
		
		//*****************************Line 2 Assertions***********************
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		softAssert.assertAll();
		
		Reporter.log( "Blocked Stock is unchanged, Reserved Stock is unchanged, Actual Stock becomes zero,Total Stock becomes the quantity entered in transaction by stock correction transaction <br>", true); 
	}
		
	/**
	 * Testcase ID	 : C90814
	 * Author		 : ssu
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testReturnToSupplierInSameWarehouseDifferentLocations() throws Exception{

		Reporter.log("<span style='font-weight:bold;color:#000033'> C90814: Return to Supplier Transaction for multiple locations in same Warehouse</span><br>", true);
		
		waitForExtJSAjaxComplete(30);
		
		String warehouseRef = "RTSTransactionWH";
		String availableStockColName = "Available Stock";
		String reservedStockColName = "Reserved Stock";
		String blockedStockColName = "Blocked Stock";
		String totalStockColName = "Total Stock";
		String productReference = "6preProdRef";
		String productCode = "6preProdCod";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseRef, "Reference");
		
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
		
		waitForExtJSAjaxComplete(20);
		
		String attributeName = "textContent";

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Stock Items <br>", true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReturnToSupplierInSameWarehouseDifferentLocations");
		
		selectWarehouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		//Levels Verification in Left Panel of Stock Items
		StockItemsPageObject stockItemsPO = new StockItemsPageObject();
		
		stockItemsPO.openTransactionLineStockItems("@id", getFilterGridID(), productCode, "Product Code");
		
		waitForExtJSAjaxComplete(10);
		
		stockItemsPO.clickCurrentTab();
		
		//************************Stock Items Line 1 get Cell Text Pre Txn (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationVjaStockItemsPreTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("1", attributeName);
		
		String avlReservedQtyVjaStockItemsPreTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("1", attributeName);
		
		String blockedQtyVjaStockItemsPreTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("1", attributeName);
		
		String totalQtyVjaStockItemsPreTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("1", attributeName);
		
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 get Cell Text Pre Txn (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationHydStockItemsPreTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("2", attributeName);
		
		String avlReservedQtyHydStockItemsPreTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("2", attributeName);

		String blockedQtyHydStockItemsPreTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("2", attributeName);
		
		String totalQtyHydStockItemsPreTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("2", attributeName);

		waitForExtJSAjaxComplete(5);
		
		close(STOCK_ITEMS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		String transactionType = "Return to Supplier";
		String refereneColName = "Reference";
		String lineQty = "1";
		String locHyd = "HYD";
		String locVja = "VJA";
		int intLineQuantity = Integer.parseInt(lineQty);
		String reference = "RTS 1";
		String expectedDate = this.getFutureDate(0);
		String supplierName = "My Company";
		String collectingPerson="SELENIUM";
		
		waitForExtJSAjaxComplete(20);
		
		selectTransactionType(transactionType);
		
		waitForExtJSAjaxComplete(5);
		
		setWareHouse("Reference", warehouseRef, RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		setReference(reference, RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER);
		
		setTransactionDate(expectedDate, RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER);
		
		selectSupplier("Name", supplierName, RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER);
		
		selectCollectingPerson("Last Name", collectingPerson, RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER);
		//**************************Line 1******************************************************
		
		clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineProductReferenceFromProductsAndServices("1", productReference, refereneColName);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("1", lineQty);
		
		setTransactionLineLocationFromAllLocations("1", locVja, refereneColName);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineRemark("1", "Rts 1");
		
		waitForExtJSAjaxComplete(5);
		
		clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineProductReferenceFromProductsAndServices("2", productReference, refereneColName);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("2", lineQty);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineLocationFromAllLocations("2", locHyd, refereneColName);
		
		setTransactionLineRemark("2", "Rts 2");
		
		waitForExtJSAjaxComplete(5);
				
		save();
		
		waitForExtJSAjaxComplete(20);
		
		setStatus("Executed", RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER );
		
		saveAndClose(RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseRef);
		
		waitForExtJSAjaxComplete(10);

		addWarehousePageObject.clickEditButton(); 
				
		waitForExtJSAjaxComplete(10);
				
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
				
		waitForExtJSAjaxComplete(10);
		
		String strAvailableStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),availableStockColName);
		String strReservedStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),reservedStockColName);
		String strBlockedStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),blockedStockColName);
		String strTotalStockValuePostTxn1 = addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(productReference),totalStockColName);
		
		int availableStockValuePostTxn1 = Integer.parseInt(strAvailableStockValuePostTxn1);
		int totalStockValuePostTxn1 = Integer.parseInt(strTotalStockValuePostTxn1);
		int blockStockValuePostTxn1 = Integer.parseInt(strBlockedStockValuePostTxn1);
		int reservedValuePostTxn1 = Integer.parseInt(strReservedStockValuePostTxn1);
		
		//*****************************Line 1 Assertions***********************
		softAssert.assertTrue((reservedValuePostTxn1==reservedStockValue), productReference+"Reserved stock quantity is unchanged");
		softAssert.assertTrue((blockStockValuePostTxn1 ==(blockedStockValue-(intLineQuantity*2))), productReference+" blocked stock quantity is decreased by "+lineQty+"units");
		softAssert.assertTrue((totalStockValuePostTxn1==(totalStockValue-(intLineQuantity*2))), productReference+" Total stock quantity is decreased by "+lineQty+"units");
		softAssert.assertTrue((availableStockValuePostTxn1==availableStockValue), productReference+" available stock quantity is unchanged");
					
		addWarehousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Stock Items <br>", true);
		
		selectWarehouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		//Levels Verification in Left Panel of Stock Items
		stockItemsPO.openTransactionLineStockItems("@id", getFilterGridID(), productCode, "Product Code");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(stockItemsPO.verifyAvailableStockFromStockItemsLeftPanel(strAvailableStockValuePostTxn1), "Available stock: <number> is present in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyReservedStockFromStockItemsLeftPanel(strReservedStockValuePostTxn1), "Reserved stock: <number> is available in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyBlockedStockFromStockItemsLeftPanel(strBlockedStockValuePostTxn1), "Blocked stock: <number> is available in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyTotalStockFromStockItemsLeftPanel(strTotalStockValuePostTxn1), "Total stock: <number> is available in left panel");
				
		waitForExtJSAjaxComplete(5);
		
		stockItemsPO.clickCurrentTab();
		
		//************************Stock Items Line 1 get Cell Text Value (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationVjaPostTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("1", attributeName);
		
		String avlReservedQtyVjaPostTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("1", attributeName);
		
		String blockedQtyVjaPostTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("1", attributeName);
		int intBlockedQtyVjaPostTxn = Integer.parseInt(blockedQtyVjaPostTxn);
				
		String totalQtyVjaPostTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("1", attributeName);
		int intTotalQtyVjaPostTxn = Integer.parseInt(totalQtyVjaPostTxn);
				
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 get Cell Text Value (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationHydPostTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("2", attributeName);
		
		String avlReservedQtyHydPostTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("2", attributeName);
		
		String blockedQtyHydPostTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("2", attributeName);
		int intBlockedQtyHydPostTxn = Integer.parseInt(blockedQtyHydPostTxn);
		
		String totalQtyHydPostTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("2", attributeName);
		int intTotalQtyHydPostTxn = Integer.parseInt(totalQtyHydPostTxn);

		//********* Vja Location blocked and Total Qty in Stock Items Window
		int intBlockedQtyVjaStockItemsPreTxn = Integer.parseInt(blockedQtyVjaStockItemsPreTxn);
			
		int intTotalQtyVjaStockItemsPreTxn = Integer.parseInt(totalQtyVjaStockItemsPreTxn);
		
		//*********Hyderabad Location blocked and Total Qty in Stock Items Window
		int intBlockedQtyHydStockItemsPreTxn = Integer.parseInt(blockedQtyHydStockItemsPreTxn);
					
		int intTotalQtyHydStockItemsPreTxn = Integer.parseInt(totalQtyHydStockItemsPreTxn);
		
		//*** Hyderabad and Vijayawada Expected Values Manipulation
		int intBlockedQtyVjaStockItems = intBlockedQtyVjaStockItemsPreTxn - intLineQuantity;
		int intBlockedQtyHydStockItems = intBlockedQtyHydStockItemsPreTxn - intLineQuantity;
		
		int intTotalQtyHydStockItems = intTotalQtyHydStockItemsPreTxn - intLineQuantity;
		int intTotalQtyVjaStockItems = intTotalQtyVjaStockItemsPreTxn - intLineQuantity;
		
		//************************Stock Items Line 1 Verifications (Location, Avl Qty, Blocked Qty and Total Qty)
		softAssert.assertEquals(locationVjaPostTxn, locationVjaStockItemsPreTxn, "Location Vja's is present in Stock Items WVjaow");
		
		softAssert.assertEquals(avlReservedQtyVjaPostTxn, avlReservedQtyVjaStockItemsPreTxn, "Location Vja's Available+Reserved is unchanged in Stock Items Window");
		
		softAssert.assertEquals(intBlockedQtyVjaPostTxn, intBlockedQtyVjaStockItems, "Location Vja's Block quantity is decreased by "+lineQty+"units");
		
		softAssert.assertEquals(intTotalQtyVjaPostTxn, intTotalQtyVjaStockItems, "Location Vja's Total quantity is decreased by "+lineQty+"units");
		
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 Verifications (Location, Avl Qty, Blocked Qty and Total Qty)
		softAssert.assertEquals(locationHydPostTxn, locationHydStockItemsPreTxn, "Location Hyderabad is present in Stock Items Window");

		softAssert.assertEquals(avlReservedQtyHydPostTxn, avlReservedQtyHydStockItemsPreTxn, "Location Hyderabad's Available+Reserved quantity is unchanged in Stock Items Window" );
		
		softAssert.assertEquals(intBlockedQtyHydPostTxn, intBlockedQtyHydStockItems, "Location Hyderabad's Block quantity is decreased by "+lineQty+"units");
		
		softAssert.assertEquals(intTotalQtyHydPostTxn, intTotalQtyHydStockItems, "Location Hyderabad's Total quantity is increased by "+lineQty+"units");
		
		waitForExtJSAjaxComplete(5);
		
		close(STOCK_ITEMS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
	
		softAssert.assertAll();
		
		Reporter.log("Return To supplier Transaction for multiple locations in same Warehouse is successfully verified <br>", true);
	}

	

	/**
	 * Testcase ID	 : C90804
	 * Author		 : ssa
	 * */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	
	public void testCreateGRTxnInSameWHDifferentLocations() throws Exception {
	
		String transactionReference = "C52500Ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		String wareHouseLoc1 = "VJA";
		String wareHouseLoc2 = "HYD";
		String warehouseReference = "GRTxnFrmMultiLocWH";
		String stockItemRef = "2preConsRef";
		String stockItemCode = "2preConsCod";
		String lineQuantity = "10";
		String purchasingOrg = "Default";
		String lookUpWindowCol = "Reference";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		String blockedStockColName="Blocked Stock";
		String reservedStockColName = "Reserved Stock";
		
		int lineQty=Integer.parseInt(lineQuantity);
		String availableStockColName = "Available Stock";
		String totalStockColName="Total Stock";
		String remark = "C52500Rem"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		SoftAssert softAssert = new SoftAssert();
		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90804 Goods Receipt Transaction for multiple locations in same Warehouse <br>", true);
		
		
		
		softAssert.setMethodName("testCreateGoodsReceiptTransaction");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValuePreTxn = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),availableStockColName ));
		int totalStockValuePreTxn = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),totalStockColName));
		int blockStockValuePreTxn= Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),blockedStockColName));
		int reservedStockValuePreTxn  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),reservedStockColName));
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		String attributeName = "textContent";

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Stock Items <br>", true);
		
		selectWarehouse(warehouseReference);
		
		waitForExtJSAjaxComplete(5);
		
		//Levels Verification in Left Panel of Stock Items
		StockItemsPageObject stockItemsPO = new StockItemsPageObject();
		
		stockItemsPO.openTransactionLineStockItems("@id", getFilterGridID(), stockItemCode, "Product Code");
		
		waitForExtJSAjaxComplete(10);
		
		stockItemsPO.clickCurrentTab();
		
		//************************Stock Items Line 1 get Cell Text Pre Txn (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationVjaStockItemsPreTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("1", attributeName);
		
		String avlReservedQtyVjaStockItems = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("1", attributeName);
		
		int avlReservedQtyVjaStockItemsPreTxn = Integer.parseInt(avlReservedQtyVjaStockItems);
		
		String blockedQtyVjaStockItems= stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("1", attributeName);
		
		int blockedQtyVjaStockItemsPreTxn=Integer.parseInt(blockedQtyVjaStockItems);
		
		String totalQtyVjaStockItems = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("1", attributeName);
		
		int totalQtyVjaStockItemsPreTxn =Integer.parseInt(totalQtyVjaStockItems);
		
		
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 get Cell Text Pre Txn (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationHydStockItemsPreTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("2", attributeName);
		
		String avlReservedQtyHydStockItems = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("2", attributeName);
		
		int avlReservedQtyHydStockItemsPreTxn= Integer.parseInt(avlReservedQtyHydStockItems);
		
		String blockedQtyHydStockItems = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("2", attributeName);
		
		int blockedQtyHydStockItemsPreTxn = Integer.parseInt(blockedQtyHydStockItems);
		
		String totalQtyHydStockItems = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("2", attributeName);
		
		int totalQtyHydStockItemsPreTxn=Integer.parseInt(totalQtyHydStockItems);
		
		
		waitForExtJSAjaxComplete(5);
		
		close(STOCK_ITEMS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
        waitForExtJSAjaxComplete(20);
		
		selectTransactionType("Goods Receipt");
		
		waitForExtJSAjaxComplete(20);
		
		setWareHouse("Reference", warehouseReference, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		setReference( transactionReference, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		clickAddTransactionLines();

		MenuSelector.selectMenuItemNative(driver, "Stock");

		waitForExtJSAjaxComplete(20);

		setTransactionLineProductReferenceFromProductsAndServices("1", stockItemRef, lookUpWindowCol);

		waitForExtJSAjaxComplete(20);

		setTransactionLineQuantity("1",  lineQuantity);

		waitForExtJSAjaxComplete(20);

		setTransactionLineLocationFromAllLocations("1",  wareHouseLoc1, lookUpWindowCol);

		waitForExtJSAjaxComplete(20);

		setPurchasingOrgForTransactionLine("1",  purchasingOrg, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
	
		setTransactionLineRemark("1",  remark);
		
		Reporter.log("Stock line is added", true);
		
		clickAddTransactionLines();

		MenuSelector.selectMenuItemNative(driver, "Stock");

		waitForExtJSAjaxComplete(20);

		setTransactionLineProductReferenceFromProductsAndServices("2", stockItemRef, lookUpWindowCol);

		waitForExtJSAjaxComplete(20);

		setTransactionLineQuantity("2",  lineQuantity);

		waitForExtJSAjaxComplete(20);

		setTransactionLineLocationFromAllLocations("2",  wareHouseLoc2, lookUpWindowCol);

		waitForExtJSAjaxComplete(20);

		setPurchasingOrgForTransactionLine("2",  purchasingOrg, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
	
		setTransactionLineRemark("1",  remark);
		
		Reporter.log("Stock line2 is added", true);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		setStatus("Executed",GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER );
		
		waitForExtJSAjaxComplete(20);
		
		saveAndClose(GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
        waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		String strAvailableStockValuePostTxn = wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),availableStockColName);
		String strReservedStockValuePostTxn = wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),reservedStockColName);
		String strBlockedStockValuePostTxn = wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),blockedStockColName);
		String strTotalStockValuePostTxn= wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),totalStockColName);
		
		int availableStockValuePostTxn = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),availableStockColName ));
		int totalStockValuePostTxn = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),totalStockColName));
		int blockStockValuePostTxn = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),blockedStockColName));
		int reservedStockValuePostTxn  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),reservedStockColName));
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue((reservedStockValuePostTxn==reservedStockValuePreTxn),"Reserved stock quantity is unchanged");
		softAssert.assertTrue((blockStockValuePostTxn ==blockStockValuePreTxn), "Blocked stock is unchanged");
		softAssert.assertTrue((totalStockValuePostTxn==(totalStockValuePreTxn+(lineQty*2)))," Total stock quantity is increased by this transaction");
		softAssert.assertTrue((availableStockValuePostTxn==(availableStockValuePreTxn+(lineQty*2)))," available stock quantity is increased");
					
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Stock Items <br>", true);
		
		selectWarehouse(warehouseReference);
		
		waitForExtJSAjaxComplete(10);
		
		stockItemsPO.openTransactionLineStockItems("@id", getFilterGridID(), stockItemCode, "Product Code");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(stockItemsPO.verifyAvailableStockFromStockItemsLeftPanel(strAvailableStockValuePostTxn), "Available stock: <number> is present in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyReservedStockFromStockItemsLeftPanel(strReservedStockValuePostTxn), "Reserved stock: <number> is available in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyBlockedStockFromStockItemsLeftPanel(strBlockedStockValuePostTxn), "Blocked stock: <number> is available in left panel");
		
		softAssert.assertTrue(stockItemsPO.verifyTotalStockFromStockItemsLeftPanel(strTotalStockValuePostTxn), "Total stock: <number> is available in left panel");
				
		waitForExtJSAjaxComplete(5);
		
		stockItemsPO.clickCurrentTab();
		
		//************************Stock Items Line 1 get Cell Text Pre Txn (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationVjaStockItemsPostTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("1", attributeName);
		
		String avlReservedQtyVjaStockItemsTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("1", attributeName);
		
		int avlReservedQtyVjaStockItemsPostTxn = Integer.parseInt(avlReservedQtyVjaStockItemsTxn);
		
		String blockedQtyVjaStockItemsTxn= stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("1", attributeName);
		
		int blockedQtyVjaStockItemsPostTxn=Integer.parseInt(blockedQtyVjaStockItemsTxn);
		
		String totalQtyVjaStockItemsTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("1", attributeName);
		
		int totalQtyVjaStockItemsPostTxn =Integer.parseInt(totalQtyVjaStockItemsTxn);
		
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 get Cell Text Pre Txn (Location, Avl Qty, Blocked Qty and Total Qty)
		
		String locationHydStockItemsPostTxn = stockItemsPO.getLocationFromCurrentTabOfStockItems("2", attributeName);
		
		String avlReservedQtyHydStockItemsTxn = stockItemsPO.getAvailableAndReservedStockFromCurrentTabOfStockItems("2", attributeName);
		
		int avlReservedQtyHydStockItemsPostTxn= Integer.parseInt(avlReservedQtyHydStockItemsTxn);
		
		String blockedQtyHydStockItemsTxn = stockItemsPO.getBlockedStockFromCurrentTabOfStockItems("2", attributeName);
		
		int blockedQtyHydStockItemsPostTxn = Integer.parseInt(blockedQtyHydStockItemsTxn);
		
		String totalQtyHydStockItemsTxn = stockItemsPO.getTotalStockFromCurrentTabOfStockItems("2", attributeName);
		
		int totalQtyHydStockItemsPostTxn=Integer.parseInt(totalQtyHydStockItemsTxn);
		
		waitForExtJSAjaxComplete(5);
		
		
       //************************Stock Items Line 1 Verifications (Location, Avl Qty, Blocked Qty and Total Qty)
		softAssert.assertEquals(locationVjaStockItemsPreTxn, locationVjaStockItemsPostTxn, "Location Vja's is present in Stock Items Window");
		
		softAssert.assertEquals((avlReservedQtyVjaStockItemsPreTxn+lineQty),avlReservedQtyVjaStockItemsPostTxn, "Location Vja's Available is increased");
		
		softAssert.assertEquals(blockedQtyVjaStockItemsPreTxn, blockedQtyVjaStockItemsPostTxn, "Location Vja's Block quantity is unchanged");
		
		softAssert.assertEquals((totalQtyVjaStockItemsPreTxn+lineQty), totalQtyVjaStockItemsPostTxn, "Location Vja's Total quantity  is increased");
		
		waitForExtJSAjaxComplete(5);
		
		//***********************Stock Items Line 2 Verifications (Location, Avl Qty, Blocked Qty and Total Qty)
		softAssert.assertEquals(locationHydStockItemsPreTxn, locationHydStockItemsPostTxn, "Location Hyderabad is present in Stock Items Window");

		softAssert.assertEquals((avlReservedQtyHydStockItemsPreTxn+lineQty), avlReservedQtyHydStockItemsPostTxn,  "Location Hyderabad's Available quantity is increased");
		
		softAssert.assertEquals(blockedQtyHydStockItemsPreTxn, blockedQtyHydStockItemsPostTxn, "Location Hyderabad's Block quantity is unchanged");
		
		softAssert.assertEquals((totalQtyHydStockItemsPreTxn+lineQty), totalQtyHydStockItemsPostTxn, "Location Hyderabad's Total quantity  is increased");
		
		waitForExtJSAjaxComplete(5);
		
		close(STOCK_ITEMS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
	
		softAssert.assertAll();
		
		Reporter.log("Goods Receipt Transaction for multiple locations in same Warehouse is successfully verified <br>", true);
	
	}

	/**
	 * TestCase ID :C90805
	 * Author : ssa
	 */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testAvailableStockPossitiveValueWhenResevedStockValueMet() throws Exception{
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90805:Available Stock doesn't reach postive value untill Reserved Stock quantity is met</span><br>",true);
		
		String transactionReference = "C35974Ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedDate = this.getFutureDate(3);
		String supplierName = "My Company";
		String wareHouseLoc = "HYD";
		String warehouseReference = "TestNegativeValueWH";
		String stockItemRef = "2preConsRef";
		String lineQuantity = "1";
		String purchasingOrg = "AQAPurchOrg";
		String lookUpWindowCol = "Reference";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		String reservedStockColName="Reserved Stock";
		String blockedStockColName="Blocked Stock";
		
		String availableStockColName = "Available Stock";
		String totalStockColName="Total Stock";
		
		SoftAssert softAssert = new SoftAssert();
		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
		
        softAssert.setMethodName("testAvailableStockPossitiveValueWhenResevedStockValueMet");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
		
		waitForExtJSAjaxComplete(10);

		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValue = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),availableStockColName ));
		int totalStockValue = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),totalStockColName));
		int reservedStock=Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),reservedStockColName));
		int blockedStock=Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),blockedStockColName));
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log(availableStockValue+" is available stock quantity", true);
		
		Reporter.log(totalStockValue+" is total stock quantity", true);
		
		Reporter.log(reservedStock+" is reserved stock quantity", true);
		
		Reporter.log(blockedStock+" is blocked stock quantity", true);
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
        expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
			
		waitForExtJSAjaxComplete(10);
		
	    selectTransactionType("Goods Receipt");
		
		waitForExtJSAjaxComplete(25);
		
        setWareHouse("Reference", warehouseReference, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		setReference( transactionReference, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		setStatus("Planned",GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER );
		
		setTransactionDate(expectedDate, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		selectSupplier("Name", supplierName, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		 
		waitForExtJSAjaxComplete(20);
		
		clickAddTransactionLines();

		MenuSelector.selectMenuItemNative(driver, "Stock");

		waitForExtJSAjaxComplete(20);

		setTransactionLineProductReferenceFromProductsAndServices("2", stockItemRef, lookUpWindowCol);
		
	    waitForExtJSAjaxComplete(20);
		
		setTransactionLineQuantity("1",  lineQuantity);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineLocationFromAllLocations("1",  wareHouseLoc, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);

		setPurchasingOrgForTransactionLine("1",  purchasingOrg, lookUpWindowCol);
		
        waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		setStatus("Executed",GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER );
		
		waitForExtJSAjaxComplete(20);
		
		saveAndClose(GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),availableStockColName ));
		int totalStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),totalStockColName));
		int reservedStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),reservedStockColName));
		int blocedStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),blockedStockColName));
		
		softAssert.assertTrue((availableStockValue+Integer.parseInt(lineQuantity))==availableStockValuePostTxn1 , "Available Stock has decreased by "+lineQuantity+" units");
		softAssert.assertTrue((totalStockValue+Integer.parseInt(lineQuantity))==totalStockValuePostTxn1 , "Stock has increased by "+lineQuantity+" units");
		softAssert.assertTrue((reservedStock==reservedStockValuePostTxn1) , "Reserved Stock is Unchanged");
		softAssert.assertTrue((blockedStock==blocedStockValuePostTxn1), "Blocked Stock is Unchanged");
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		waitForExtJSAjaxComplete(30);
		
	    selectTransactionType("Goods Receipt");
		
		waitForExtJSAjaxComplete(30);
		
		setReference( transactionReference, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(30);
		
		setWareHouse("Reference", warehouseReference, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(30);
		
		setStatus("Planned",GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER );
		
		setTransactionDate(expectedDate, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		selectSupplier("Name", supplierName, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		 
		waitForExtJSAjaxComplete(30);
		
		clickAddTransactionLines();

		MenuSelector.selectMenuItemNative(driver, "Stock");

		waitForExtJSAjaxComplete(30);

		setTransactionLineProductReferenceFromProductsAndServices("2", stockItemRef, lookUpWindowCol);
		
	    waitForExtJSAjaxComplete(30);
	    
	    String availableStockValuePostTxn = Integer.toString(availableStockValuePostTxn1);
	    
		setTransactionLineQuantity("1",  availableStockValuePostTxn);
		
		waitForExtJSAjaxComplete(30);
		
		setTransactionLineLocationFromAllLocations("1",  wareHouseLoc, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(30);

		setPurchasingOrgForTransactionLine("1",  purchasingOrg, lookUpWindowCol);
		
        waitForExtJSAjaxComplete(30);
		
		save();
		
		waitForExtJSAjaxComplete(30);
		
		setStatus("Executed",GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER );
		
		waitForExtJSAjaxComplete(30);
		
		saveAndClose(GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(30);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		waitForExtJSAjaxComplete(30);
		
		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(30);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(30);
		
		int availableStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),availableStockColName ));
		int totalStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),totalStockColName));
		int reservedStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),reservedStockColName));
		int blocedStockValuePostTxn2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),blockedStockColName));
		
		softAssert.assertTrue((availableStockValuePostTxn1-availableStockValuePostTxn1)==availableStockValuePostTxn2 , "Available Stock has decreased by "+lineQuantity+" units");
		softAssert.assertTrue((totalStockValuePostTxn1-availableStockValuePostTxn1)==totalStockValuePostTxn2 , "Stock has increased by "+lineQuantity+" units");
		softAssert.assertTrue((reservedStockValuePostTxn1==reservedStockValuePostTxn2) , "Reserved Stock is Unchanged");
		softAssert.assertTrue((blocedStockValuePostTxn1==blocedStockValuePostTxn2), "Blocked Stock is Unchanged");
		
		waitForExtJSAjaxComplete(30);
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
	
		softAssert.assertAll();
		
		Reporter.log("Available Stock doesn't reach postive value untill Reserved Stock quantity is met is verified<br>"
				+"Blocked Stock is unchanged, Reserved Stock is unchanged, Actual Stock becomes zero,Total Stock becomes the quantity entered in transactions by Goods Receipt transaction ",  true);	
	}
	   
}



