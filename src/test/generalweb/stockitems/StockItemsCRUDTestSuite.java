package test.generalweb.stockitems;


import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.generalweb.warehouse.AddWarehousePageObject;

public class StockItemsCRUDTestSuite extends StockItemsPageObject{
	
	
	/**
	 * Testcase ID	 : C90876, C90856
	 * Author		 : ssa
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void createBlockTransFromStockItemsTab() throws Exception{
		
		
		String warehouseReference = "BlockTransactionWH";
		String stockItemRef1 = "2preConsRef";
		String stockItemRef2 = "6preProdRef";
		String lineQuantity = "10";
		String remark1 = "BlockRemark1"+getCurrentDate().substring(getCurrentDate().length()-5);
		String remark2 = "BlockRemark2"+getCurrentDate().substring(getCurrentDate().length()-5);
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		String availableStockColName = "Available Stock";
		String totalStockColName="Total Stock";
		String blockedStockColName="Blocked Stock";
		int intLineQuantity = Integer.parseInt(lineQuantity);
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("createBlockTransFromStockItemsTab");
		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90876:Create Block Transaction From Stock Items Tab  <br>"+
	 	"C90856: Stock item defaults in Transaction line of Block Transaction<br></span>", true); 
		
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");		
		
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
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		selectWarehouse(warehouseReference);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, stockItemRef1);
		
		Grid.checkRowInGriByTextValue(driver, stockItemRef2);
		
		waitForExtJSAjaxComplete(20);

		selectTransactionType("Block");
		
		waitForExtJSAjaxComplete(20);
		
	    String stockLineProdRef1 = this.getProductOfTransactionLine("1");
	    
	    String stockLineProdRef2 = this.getProductOfTransactionLine("2");
	    
	    waitForExtJSAjaxComplete(20);
	    
	    softAssert.assertEquals(stockLineProdRef1, stockItemRef1, "Product reference1 is displayed in Block Transaction window");
	    softAssert.assertEquals(stockLineProdRef2, stockItemRef2, "Product reference2 is displayed in Block Transaction window");
	    
	    //**************************** Test Case C52516 is completed*************************
		
		waitForExtJSAjaxComplete(20);
		
	    setTransactionLineQuantity("1", lineQuantity);
		
	    setTransactionLineRemark("1", remark1);
		
		waitForExtJSAjaxComplete(20);
		
	    setTransactionLineQuantity("2", lineQuantity);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineRemark("2", remark2);
		
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
		
		int availableStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef1),availableStockColName));
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
		
		Reporter.log("Stock items Transaction lines are displayed defaultly in Block Transaction window<br>"+
				 "Block Transaction is successfully created from Stock items<br>", true);	

}	
	
	/**
	 * Testcase ID	 : C90877, C90859
	 * Author		 : ssa
	 */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void createUnBlockTransFromStockItemsTab() throws Exception{
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90877:Create Unblock Transaction </span><br>"
				+"C90859:Stock item defaults in Transaction line of Unblock Transaction<br>",true);
	
	
		String remark1 = "UnBlockRemark1"+getCurrentDate().substring(getCurrentDate().length()-5);
		String remark2 = "UnBlockRemark2"+getCurrentDate().substring(getCurrentDate().length()-5);;;
		String warehouseReference = "UnBlockTransactionWH";
	    String stockItemRef1 = "2preConsRef";
		String stockItemRef2 = "6preProdRef";
		String lineQuantity = "1";
	    String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		String availableStockColName = "Available Stock";
		String totalStockColName="Total Stock";
		String blockedStockColName="Blocked Stock";
		int intLineQuantity = Integer.parseInt(lineQuantity);
		String lookUpWindowCol = "Reference";
		String wareHouseLoc1 = "HYD";
		String wareHouseLoc2 = "VJA";  
		
		SoftAssert softAssert = new SoftAssert();
	    AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
		
		softAssert.setMethodName("createUnBlockTransFromStockItemsTab");
		
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
		  
        waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		selectWarehouse(warehouseReference);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, stockItemRef1);
		
		Grid.checkRowInGriByTextValue(driver, stockItemRef2);

		selectTransactionType("Unblock");
		
		waitForExtJSAjaxComplete(20);
		
	    String stockLineProdRef1 = this.getProductOfTransactionLine("1");
	    
	    String stockLineProdRef2 = this.getProductOfTransactionLine("2");
	    
	    softAssert.assertEquals(stockLineProdRef1, stockItemRef1, "Product reference1 is displayed in UnBlock Transaction window");
	    softAssert.assertEquals(stockLineProdRef2, stockItemRef2, "Product reference1 is displayed in UnBlock Transaction window");
		  
		waitForExtJSAjaxComplete(20);
		  
		setTransactionLineQuantity("1", lineQuantity);
	
		waitForExtJSAjaxComplete(20);
		
		
		setTransactionLineLocationFromAllLocations("1",  wareHouseLoc1 , lookUpWindowCol);
		
		setTransactionLineRemark("1", remark1);
		
		waitForExtJSAjaxComplete(20);
		  
		setTransactionLineQuantity("2", lineQuantity);
		  
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineLocationFromAllLocations("2",  wareHouseLoc2 , lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineRemark("2", remark2);
		  
		waitForExtJSAjaxComplete(20);
		  
		execute();
		  
		waitForExtJSAjaxComplete(20);
		  
		waitAndClick(XPATH_WAREHOUSES);
		  
	    waitForExtJSAjaxComplete(20);
		  
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
		
		waitForExtJSAjaxComplete(20);

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
		  
		Reporter.log("Stock items Transaction lines are displayed defaultly in UnBlock Transaction window <br>"
				   + "Unblock Transaction is successfully created from Stock items<br>",true);
	}
	
	/**
	 * TestCase ID : C90882 & C90871
	 * Author : SSU
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testStockScrappingCreationFromStockItemsOverview() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> C90882 :Create Stock scrapping Transaction from Stock items tab</span><br>", true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> C90871 :Stock item defaults in Transaction line of Stock scrapping Transaction</span><br>", true);
		
		waitForExtJSAjaxComplete(30);
		
		String warehouseRef = "StockItemsBlockedWH";
		String availableStockColName = "Available Stock";
		String reservedStockColName = "Reserved Stock";
		String blockedStockColName = "Blocked Stock";
		String totalStockColName = "Total Stock";
		String productReference = "aqapreProdRef3";
		String productCode = "aqapreProdCod3";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		String lookUpWindowCol = "Reference";
		String wareHouseLoc1 = "HYD";
		String wareHouseLoc2 = "IND";  
		
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
		
		waitForExtJSAjaxComplete(10);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Stock Items <br>", true);
		
		String scrappingTransactionType = "Stock Scrapping";
		String transactionref52567 = "C52567Ref"+ getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedDate = this.getFutureDate(0);
		String collectingPerson="SELENIUM";
		String remark52567 = "rem"+ getCurrentDate().substring(getCurrentDate().length()-5);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testStockScrappingCreationFromStockItemsOverview");
		
		selectWarehouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValue(driver, productReference);
		
		selectTransactionType(scrappingTransactionType);
		
		waitForExtJSAjaxComplete(5);
		
		String defaultWarehouse = getDefaultWarehouseValue(STOCK_SCRAPPING_TRANSACTION_WINDOW_HEADER);
		
		softAssert.assertEquals(defaultWarehouse, warehouseRef, "Stock Items Warehouse is Defaulted in Stock Scrapping Window");
		
		waitForExtJSAjaxComplete(5);
		
		setReference(transactionref52567, STOCK_SCRAPPING_TRANSACTION_WINDOW_HEADER);
		
		setTransactionDate(expectedDate, STOCK_SCRAPPING_TRANSACTION_WINDOW_HEADER);
		
		selectCollectingPerson("Last Name", collectingPerson, STOCK_SCRAPPING_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		String productRef = getProductRefOfTransactionLine("1");
		
		softAssert.assertEquals(productRef, productReference, "Product Reference is automatically populated in Transaction Lines of Stock Scrapping Window");
		
		String productCod = getProductCodeOfTransactionLine("1");
		
		softAssert.assertEquals(productCod, productCode, "Product Code is automatically populated in Transaction Lines of Stock Scrapping Window");
		
		Reporter.log("Stock item defaults in Transaction line of Stock scrapping Transaction <br>", true);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineLocationFromAllLocations("1",  wareHouseLoc1 , lookUpWindowCol);
		
		setTransactionLineRemark("1", remark52567);
		
		save();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		
		close(STOCK_SCRAPPING_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(25);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Transactions Page <br>", true);
		
		selectWarehouse(warehouseRef);
		
		//Filter Transactions Grid with Remark
		filterGrid("@id", getFilterGridID(), remark52567, "Remark");
		
		softAssert.assertAll();
		
		Reporter.log("Stock scrapping Transaction is successfully created from Stock items <br>", true);
	}
	
	/**
	 * TestCase ID : C90881 & C90869
	 * Author : SSU 
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testReturnToSupplierCreationFromStockItemsOverview() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> C90881 :Create Return to Supplier Transaction from Stock items tab</span><br>", true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> C90869 :Stock item defaults in Transaction line of Return to Supplier Transaction</span><br>", true);
		
		waitForExtJSAjaxComplete(30);
		
		String warehouseRef = "StockItemsBlockedWH";
		String availableStockColName = "Available Stock";
		String reservedStockColName = "Reserved Stock";
		String blockedStockColName = "Blocked Stock";
		String totalStockColName = "Total Stock";
		String productReference = "aqapreProdRef3";
		String productCode = "aqapreProdCod3";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		String lookUpWindowCol = "Reference";
		String wareHouseLoc = "HYD";
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
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
		
		waitForExtJSAjaxComplete(10);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Stock Items <br>", true);
		
		String ReturnToSupplierTransactionType = "Return to Supplier";
		String transactionref52566 = "C52566Ref"+ getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedDate = this.getFutureDate(0);
		String collectingPerson="SELENIUM";
		String remark52566 = "rem"+ getCurrentDate().substring(getCurrentDate().length()-5);
		String supplierName = "My Company";
		String executedStatus = "Executed";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReturnToSupplierCreationFromStockItemsOverview");
		
		selectWarehouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValue(driver, productReference);
		
		Reporter.log("Return to Supplier Transaction <br>", true);
		
		selectTransactionType(ReturnToSupplierTransactionType);
		
		waitForExtJSAjaxComplete(5);
		
		String defaultWarehouse = getDefaultWarehouseValue(RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER);
		
		softAssert.assertEquals(defaultWarehouse, warehouseRef, "Stock Items Warehouse is Defaulted in Return To Supplier Window");
		
		waitForExtJSAjaxComplete(5);
		
		setReference(transactionref52566, RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER);
		
		setTransactionDate(expectedDate, RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER);
		
		selectSupplier("Name", supplierName, RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER);
		
		selectCollectingPerson("Last Name", collectingPerson, RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		String productRef = getProductRefOfTransactionLine("1");
		
		softAssert.assertEquals(productRef, productReference, "Product Reference is automatically populated in Transaction Lines of Return to Supplier Window");
		
		String productCod = getProductCodeOfTransactionLine("1");
		
		softAssert.assertEquals(productCod, productCode, "Product Code is automatically populated in Transaction Lines of Return to Supplier Window");
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineRemark("1", remark52566);
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineLocationFromAllLocations("1",  wareHouseLoc , lookUpWindowCol);
		
		waitForExtJSAjaxComplete(10);
		
		setStatus(executedStatus, RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER);
		
		save();
		
		waitForExtJSAjaxComplete(10);
		
		close(RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		Reporter.log("Return to Supplier Transaction is successfully completed <br>", true);
		
		waitForExtJSAjaxComplete(10);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Transactions Page <br>", true);
		
		selectWarehouse(warehouseRef);
				
		//Filter Transactions Grid with Remark
		filterGrid("@id", getFilterGridID(), remark52566, "Remark");
		
		softAssert.assertAll();
		
		Reporter.log("Return To Supplier Transaction is successfully created from Stock items <br>", true);
	}
	
	
	/**
	 * TestCase ID : C90875 & C90858 & C90883
	 * Author : ssa
	 */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void createGoodsReceiptFromStockItemsTab() throws Exception{
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90875:Create Goods Receipt Transaction from Stock items tab </span><br>"
				+"C90858:Stock item defaults in Transaction line of Goods Receipt<br>"
				+"C90883: Verify selected warehouse is defaulted for any transactions",true);
		
		String transactionReference = "C35974Ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedDate = this.getFutureDate(3);
		String supplierName = "My Company";
		String wareHouseLoc = "HYD";
		String warehouseReference = "GRTransactionWH";
		String stockItemRef = "2preConsRef";
		String lineQuantity = "10";
		String purchasingOrg = "AQAPurchOrg";
		String lookUpWindowCol = "Reference";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		String reservedStockColName="Reserved Stock";
		String blockedStockColName="Blocked Stock";
		
		String availableStockColName = "Available Stock";
		String totalStockColName="Total Stock";
		
		SoftAssert softAssert = new SoftAssert();
		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
		
        softAssert.setMethodName("createGoodsReceiptFromStockItemsTab");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
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
		
		waitAndClick(XPATH_STOCK_ITEMS);
			
		waitForExtJSAjaxComplete(20);
			
		selectWarehouse(warehouseReference);
			
		waitForExtJSAjaxComplete(20);
			
		Grid.checkRowInGriByTextValue(driver, stockItemRef);
		
		waitForExtJSAjaxComplete(10);
		
	    selectTransactionType("Goods Receipt");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getDefaultWarehouseValue(GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER),warehouseReference,
				"warehouse value is automatically displayed in transaction which has been selected for warehouse");
        //setWareHouse("Reference", warehouseReference, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		Reporter.log("Selected warehouse is defaultly displayed for transactions <br>", true);
		
		waitForExtJSAjaxComplete(20);
		
		setReference( transactionReference, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		setStatus("Planned",GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER );
		
		setTransactionDate(expectedDate, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		selectSupplier("Name", supplierName, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		 
		waitForExtJSAjaxComplete(20);
		
		String stockLineProdRef = this.getProductRefOfTransactionLine("1");
	    
	    softAssert.assertEquals(stockLineProdRef, stockItemRef, "Product reference is displayed in Transaction lines of Goods Reciept window");
	    
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
		
		int availableStockValuePostTxn = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),availableStockColName ));
		int totalStockValuePostTxn = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),totalStockColName));
		int reservedStockValuePostTxn = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),reservedStockColName));
		int blocedStockValuePostTxn = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),blockedStockColName));
		
		softAssert.assertTrue((availableStockValue+Integer.parseInt(lineQuantity))==availableStockValuePostTxn , "Available Stock has increased by "+lineQuantity+" units");
		softAssert.assertTrue((totalStockValue+Integer.parseInt(lineQuantity))==totalStockValuePostTxn , "Stock has increased by "+lineQuantity+" units");
		softAssert.assertTrue((reservedStock==reservedStockValuePostTxn) , "Reserved Stock is Unchanged");
		softAssert.assertTrue((blockedStock==blocedStockValuePostTxn), "Blocked Stock is Unchanged");
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		softAssert.assertAll();
		
		Reporter.log("Stock item defaults in Transaction line of Goods Receipt Transaction<br>"
		         +"Goods Receipt Transaction is successfully created from Stock items<br>"
				 + "Available and total stock are increased by"+lineQuantity+" units.",  true);	
	}
	    
	
	/**
	 * TestCase ID : C90874 & C90855 & C90884
	 * Author : ssa
	 */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void createGoodsIssueFromStockItemsTab() throws Exception{
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90874:Create Goods Issue Transaction from Stock items tab </span><br>"
				+"C90855:Stock item defaults in Transaction line of Goods Issue<br>"
				+"C90884:Quantity(1) is defaultly displayed for transactions",true);	
		

		//Variable Initialization
		String warehouseReference = "GoodsIssueWH";
		String availableStockColName = "Available Stock";
		String totalStockColName = "Total Stock";
		String prodReference = "4preConsRef";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		String transactionReference = "C35977Ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedDate = this.getFutureDate(3);
		String customerName = "My Company";
		String collectingPerson = "Vorobets";
		String stockItemRef = "4preConsRef";
		String lineQuantity = "10";
		String defaultQuantity="1";
		String remarkTransactionLine1 = "rem"+getCurrentDate().substring(getCurrentDate().length()-5);
		String lotTransactionLine = "lot";
		String reservedStockColName="Reserved Stock";
		String blockedStockColName="Blocked Stock";
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("createGoodsIssueFromStockItemsTab");
		
		AddWarehousePageObject addWarehousePageObject = new AddWarehousePageObject();		
		
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");
		
		waitForExtJSAjaxComplete(20);
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		waitForExtJSAjaxComplete(10);
		
		addWarehousePageObject.clickEditButton(); 
		
		waitForExtJSAjaxComplete(10);
		
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, prodReference);
		
		//Get all stocks
		int availableStockValue = Integer.parseInt(addWarehousePageObject.getStockItemRowValue(availableStockColName));
		int totalStockValue = Integer.parseInt(addWarehousePageObject.getStockItemRowValue(totalStockColName));
		int reservedStock=Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),reservedStockColName));
		int blockedStock=Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),blockedStockColName));
		
		waitForExtJSAjaxComplete(10);
		
		addWarehousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
			
		selectWarehouse(warehouseReference);
			
		waitForExtJSAjaxComplete(20);
			
		Grid.checkRowInGriByTextValue(driver, stockItemRef);
		
		waitForExtJSAjaxComplete(20);
			
		clickNewGoodsIssueToolBarButton();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getDefaultWarehouseValue(GOODS_ISSUE_TRANSACTION_WINDOW_HEADER),warehouseReference,
				"warehouse value is automatically displayed in transaction which has been selected for warehouse");
		
		//this.setWareHouse("Reference", warehouseReference, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		setReference( transactionReference, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		setStatus("Planned", GOODS_ISSUE_TRANSACTION_WINDOW_HEADER );
		
		setTransactionDate(expectedDate, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		selectCustomer("Customer Name", customerName, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		selectCollectingPerson("Last Name", collectingPerson, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		String stockLineProdRef = this.getProductRefOfTransactionLine("1");
		
		softAssert.assertEquals(stockLineProdRef, stockItemRef, "Product Reference is displayed in Transaction Line of Goods Issue window");

		waitForExtJSAjaxComplete(5);
		
		String stockLineQuantity=this.getQuantityOfTransactionLine("1",TRANSACTION_LINE_QUANTITY_CLASS);
		
		softAssert.assertEquals(stockLineQuantity, defaultQuantity, "Default Quantity(1) is automatically displayed in Transaction Lines of Goods Issue window");
		
		Reporter.log("Quantity(1) is defaultly displayed for transactions <br>", true);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineQuantity("1", lineQuantity);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineRemark("1", remarkTransactionLine1);
		
		setTransactionLineLot("1", lotTransactionLine);
		
		waitForExtJSAjaxComplete(10);
		
		save();
		
		waitForExtJSAjaxComplete(10);
				
		setStatus("Executed", GOODS_ISSUE_TRANSACTION_WINDOW_HEADER );
		
		waitForExtJSAjaxComplete(10);
		
		saveAndClose(GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
	
		waitForExtJSAjaxComplete(10);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
		
		addWarehousePageObject.clickEditButton(); 
		
		waitForExtJSAjaxComplete(10);
		
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(10);
		
		int availableStockAfterGoodsIssue = Integer.parseInt(addWarehousePageObject.getStockItemRowValue(availableStockColName));
		int totalStockAfterGoodsIssue = Integer.parseInt(addWarehousePageObject.getStockItemRowValue(totalStockColName));
		int reservedStockAfterIssue=Integer.parseInt(addWarehousePageObject.getStockItemRowValue(reservedStockColName));
		int blockedStockAfterIssue=Integer.parseInt(addWarehousePageObject.getStockItemRowValue(blockedStockColName));
		
		waitForExtJSAjaxComplete(20);
		
		//Verify Stock items are reduced after Goods issue Transaction
		softAssert.assertTrue((availableStockValue-Integer.parseInt(lineQuantity))==availableStockAfterGoodsIssue, "Available Stock quantity is Reduced");
		softAssert.assertTrue((totalStockValue-Integer.parseInt(lineQuantity))==totalStockAfterGoodsIssue, "Total Stock is Reduced");
		softAssert.assertTrue((reservedStock==reservedStockAfterIssue) , "Reserved Stock is Unchanged");
		softAssert.assertTrue((blockedStock==blockedStockAfterIssue), "Blocked Stock is Unchanged");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("Stock item defaults in Transaction line of Goods Issue Transaction<br>"
		         +"Goods Issue Transaction is successfully created from Stock items<br>"
				 + "Goods issue transaction decreases the available and total stock",  true);	
	}
	
	/**
	 * TestCase ID : C90878 & C90862
	 * Author : ssa
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testCreateStockTransferTxnFromStockItemsTab() throws Exception{
		
		String remark1 = "STTxnRemark1"+getCurrentDate().substring(getCurrentDate().length()-5);
		String warehouseReference1 = "STTransactionWH";
		String warehouseReference2 = "STTransactionWH1";
		String stockItemRef = "6preProdRef";
		String lineQuantity = "10";
		String lookUpWindowCol = "Reference";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		String availableStockColName = "Available Stock";
		String totalStockColName="Total Stock";
		String blockedStockColName="Blocked Stock";
		String reservedStock="Reserved Stock";
		
		int intLineQuantity = Integer.parseInt(lineQuantity);
		
		String wareHouseLoc1 = "VJA";
		String wareHouseLoc2 = "HYD";
		
		SoftAssert softAssert = new SoftAssert();
		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90878:Create Stock Transfer Transaction from Stock items tab <br>"+
	 	"C90862:Stock item defaults in Transaction line of Stock Transfer Transaction <br></span>", true); 
		
		softAssert.setMethodName("testCreateStockTransferTxnFromStockItemsTab");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference1, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference1);

		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValueInWH1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),availableStockColName ));
		int totalStockValueInWH1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),totalStockColName));
		int blockStockValueInWH1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),blockedStockColName));
		int reservedStockValueInWH1  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),reservedStock));
		
		Reporter.log(stockItemRef+" available stock quantity is "+availableStockValueInWH1+"in "+warehouseReference1+"warehouse", true);
		Reporter.log(stockItemRef+" total stock quantity is "+totalStockValueInWH1+"in "+warehouseReference1+"warehouse", true);
		Reporter.log(stockItemRef+" blocked stock quantity is "+blockStockValueInWH1+"in "+warehouseReference1+"warehouse", true);
		Reporter.log(stockItemRef+" Reserved stock quantity is "+reservedStockValueInWH1+"in "+warehouseReference1+"warehouse", true);
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		//Uncheck previous selected rows in Grid
		wareHousePageObject.clearFilterGridSelection();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference2);
		
		waitForExtJSAjaxComplete(20);

		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValue1InWH2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),availableStockColName ));
		int totalStockValue1InWH2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),totalStockColName));
		int blockStockValue1InWH2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),blockedStockColName));
		int reservedStockValue1InWH2  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),reservedStock));
		
		Reporter.log(stockItemRef+" available stock quantity is "+availableStockValue1InWH2+"in "+warehouseReference2+"warehouse", true);
		Reporter.log(stockItemRef+" total stock quantity is "+totalStockValue1InWH2+"in "+warehouseReference2+"warehouse", true);
		Reporter.log(stockItemRef+" blocked stock quantity is "+blockStockValue1InWH2+"in "+warehouseReference2+"warehouse", true);
		Reporter.log(stockItemRef+" Reserved stock quantity is "+reservedStockValue1InWH2+"in "+warehouseReference2+"warehouse", true);
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitAndClick(XPATH_STOCK_ITEMS);
			
		waitForExtJSAjaxComplete(20);
				
		selectWarehouse(warehouseReference1);
				
		waitForExtJSAjaxComplete(20);
				
		Grid.checkRowInGriByTextValue(driver, stockItemRef);
			
		waitForExtJSAjaxComplete(20);
				
		selectTransactionType("Stock Transfer");
			
		waitForExtJSAjaxComplete(10);
			
	    String defaultWarehouse = getDefaultWarehouseValue(STOCK_TRANSFER_TRANSACTION_WINDOW_HEADER);
			
		softAssert.assertEquals(defaultWarehouse, warehouseReference1, "Selected Warehouse is Defaulted in Stock Transfer Window");
		
		waitForExtJSAjaxComplete(10);
		
		String stockLineProdRef = this.getProductOfTransactionLine("1");
		
		softAssert.assertEquals(stockLineProdRef, stockItemRef, "Product Reference is displayed in Transaction Line of Stock Transfer window");
			
		waitForExtJSAjaxComplete(10);
		
		this.setDestinationWareHouse(lookUpWindowCol, warehouseReference2, STOCK_TRANSFER_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineLocationFromAllLocations("1",  wareHouseLoc1, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineDestinationLocationFromAllLocations("1",  wareHouseLoc1, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineQuantity("1", lineQuantity);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineRemark("1", remark1);
		
		waitForExtJSAjaxComplete(20);
		
		execute();
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference2);
		
		waitForExtJSAjaxComplete(10);

		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(10);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(10);
		
		int availableStockValuePostTxn1WH2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),availableStockColName ));
		int totalStockValuePostTxn1WH2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),totalStockColName));
		int blockStockValuePostTxn1WH2 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),blockedStockColName));
		int reservedStockPostTxn1WH2  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),reservedStock));
		
		Reporter.log(stockItemRef+" available stock quantity is "+availableStockValuePostTxn1WH2+"in "+warehouseReference2+"warehouse", true);
		Reporter.log(stockItemRef+" total stock quantity is "+totalStockValuePostTxn1WH2+"in "+warehouseReference2+"warehouse", true);
		Reporter.log(stockItemRef+" blocked stock quantity is "+blockStockValuePostTxn1WH2+"in "+warehouseReference2+"warehouse", true);
		Reporter.log(stockItemRef+" Reserved stock quantity is "+reservedStockPostTxn1WH2+"in "+warehouseReference2+"warehouse", true);
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		//Uncheck previous selected rows in Grid
		wareHousePageObject.clearFilterGridSelection();
				
		Grid.checkRowInGriByTextValue(driver, warehouseReference1);

		waitForExtJSAjaxComplete(10);
		
		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		wareHousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),availableStockColName ));
		int totalStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),totalStockColName));
		int blockStockValuePostTxn1 = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),blockedStockColName));
		int reservedStockPostTxn1  = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),reservedStock));
		
		softAssert.assertTrue((availableStockValuePostTxn1==(availableStockValueInWH1-intLineQuantity)), stockItemRef+" available stock quantity is decreased by "+lineQuantity+"units in "+warehouseReference1+"warehouse");
		softAssert.assertTrue((totalStockValuePostTxn1 ==(totalStockValueInWH1-intLineQuantity)), stockItemRef+" total stock quantity decreased by "+lineQuantity+"units in "+warehouseReference1+"warehouse");
		softAssert.assertTrue((blockStockValuePostTxn1==blockStockValueInWH1), stockItemRef+" Blocked stock quantity is unchanged in "+warehouseReference1+"warehouse");
		softAssert.assertTrue((reservedStockPostTxn1==reservedStockValueInWH1), stockItemRef+" reserved stock quantity is unchanged by this transaction  in "+warehouseReference1+"warehouse");
	
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
	
		softAssert.assertTrue((availableStockValuePostTxn1WH2==(availableStockValue1InWH2+intLineQuantity)), stockItemRef+" available stock quantity is increased by "+lineQuantity+"units in "+warehouseReference1+"warehouse");
		softAssert.assertTrue((totalStockValuePostTxn1WH2 ==(totalStockValue1InWH2+intLineQuantity)), stockItemRef+" total stock quantity increased by "+lineQuantity+"units in "+warehouseReference1+"warehouse");
		softAssert.assertTrue((blockStockValuePostTxn1WH2==blockStockValue1InWH2), stockItemRef+" Blocked stock quantity is unchanged in "+warehouseReference1+"warehouse");
		softAssert.assertTrue((reservedStockPostTxn1WH2==reservedStockValue1InWH2), stockItemRef+" reserved stock quantity is unchanged by this transaction  in "+warehouseReference1+"warehouse");
		
		Reporter.log("Stock item defaults in Transaction line of Stock Transfer Transaction<br>"
				+ "Stock Transfer Transaction is successfully created from Stock items",true);
	
	}
	
	/**
	 * TestCase ID : C90861 & C90879
	 * Author : ssa
	 */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testCreateStockCorrectionTxnFromStockItemsTab() throws Exception{
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90861:Stock item defaults in Transaction line of Stock correction </span><br>"
				+"C90879:Create Stock correction Transaction from Stock items tab<br>",true);	
		
		
		String remark = "C52525Remark"+getCurrentDate().substring(getCurrentDate().length()-5);
		String wareHouseLoc = "HYD";
		String warehouseReference = "SCTxnFrmStockItemsWH";
		String stockItemRef = "2preConsRef";
		String lineQuantity = "10";
		String lookUpWindowCol = "Reference";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		
		String availableStockColName = "Available Stock";
		String totalStockColName="Total Stock";
		String blockedStockColName="Blocked Stock";
		String reservedStockColName = "Reserved Stock";
		
		int intLineQuantity = Integer.parseInt(lineQuantity);
		
		SoftAssert softAssert = new SoftAssert();
		AddWarehousePageObject addWarehousePageObject = new AddWarehousePageObject();
		
		
        softAssert.setMethodName("testCreateStockCorrectionTxnFromStockItemsTab");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		addWarehousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		addWarehousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),availableStockColName ));
		int totalStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),totalStockColName));
		int blockStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),blockedStockColName));
		int reservedStockValue  = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),reservedStockColName));
		
		Reporter.log(stockItemRef+" available stock quantity is "+availableStockValue, true);
		Reporter.log(stockItemRef+" total stock quantity is "+totalStockValue, true);
		Reporter.log(stockItemRef+" blocked stock quantity is "+blockStockValue, true);
		Reporter.log(stockItemRef+" blocked stock quantity is "+reservedStockValue, true);
		
		addWarehousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
        waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
			
		selectWarehouse(warehouseReference);
			
		waitForExtJSAjaxComplete(20);
			
		Grid.checkRowInGriByTextValue(driver, stockItemRef);
		
		waitForExtJSAjaxComplete(20);
			
		clickNewStockCorrectionToolBarButton();
		
		waitForExtJSAjaxComplete(10);
		
        String defaultWarehouse = getDefaultWarehouseValue(STOCK_CORRECTION_WINDOW_HEADER);
		
		softAssert.assertEquals(defaultWarehouse, warehouseReference, "Selected Warehouse is Defaulted in Stock Scrapping Window");
		
		String stockLineProdRef = this.getProductOfTransactionLine("1");
		
		softAssert.assertEquals(stockLineProdRef, stockItemRef, "Product Reference is displayed in Transaction Line of Stock Correction window");
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineQuantity("1", ""+(totalStockValue+intLineQuantity));
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineRemark("1", remark);
		
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineLocationFromAllLocations("1",  wareHouseLoc, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(10);
		
        execute();
		
		waitForExtJSAjaxComplete(10);
		
		Reporter.log("Stock Correction Transaction is successfully completed <br>", true);
		
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
		
		int availableStockAfterStockCorrection = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),availableStockColName ));
		int totalStockAfterStockCorrection = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),totalStockColName));
		int blockedStockAfterStockCorrection = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),blockedStockColName));
		int reservedStockAfterStockCorrection  = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),reservedStockColName));

		waitForExtJSAjaxComplete(20);
		
		//Verify Stock items are reduced after Goods issue Transaction
		softAssert.assertTrue((availableStockValue+Integer.parseInt(lineQuantity))==availableStockAfterStockCorrection, "Available Stock quantity is Increased");
		softAssert.assertTrue((totalStockValue+Integer.parseInt(lineQuantity))==totalStockAfterStockCorrection, "Total Stock is Increased");
		softAssert.assertTrue((reservedStockValue==reservedStockAfterStockCorrection) , "Reserved Stock is not changed");
		softAssert.assertTrue((blockStockValue==blockedStockAfterStockCorrection), "Blocked Stock is not changed");
		
		waitForExtJSAjaxComplete(10);
			
		//Filter Transactions Grid with Remark
		softAssert.assertAll();
		
		Reporter.log("Stock item defaults are displayed in Transaction line of Stock correction<br>"
				+ "Stock Correction Transaction is successfully created from Stock items <br>", true);
	}
	
	
	/**
	 * TestCase ID : C90866 & C90880
	 * Author : ssa
	 */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testCreateReturnFromCustomerTxnFromStockItemsTab() throws Exception{
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90866:Stock item defaults in Transaction line of Return from Customer Transaction </span><br>"
				+"C90880:Create Return from Customer Transaction from Stock items tab<br>",true);
		
		
		//Variable Initialization
		String warehouseReference = "ReturnFromCustomerWH";
		String blockedStockColName = "Blocked Stock";
		String totalStockColName = "Total Stock";
		String availableStockColName = "Available Stock";
		String reservedStockColName = "Reserved Stock";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		String transactionReference = "C35998Ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String returnDate = this.getFutureDate(3);
		String customerName = "My Company";
		String returnedBy = "SELENIUM";
		String stockItemRef = "6preProdRef";
		String wareHouseLoc = "IND";
		String lookUpWindowCol = "Reference";
		String lineQuantity = "10";
		String remark = "rem"+getCurrentDate().substring(getCurrentDate().length()-5);
		int intLineQuantity = Integer.parseInt(lineQuantity);
		
		
		SoftAssert softAssert = new SoftAssert();
	    AddWarehousePageObject addWarehousePageObject = new AddWarehousePageObject();
		
		softAssert.setMethodName("testCreateReturnFromCustomerTxnFromStockItemsTab");
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		addWarehousePageObject.clickEditButton(); 
		
		waitForExtJSAjaxComplete(10);
		
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, stockItemRef);
		
		int blockedStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),blockedStockColName));
		int totalStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),totalStockColName));
		int availableStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),availableStockColName));
		int reservedStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),reservedStockColName));
		
		Reporter.log(stockItemRef+" available stock quantity is "+availableStockValue, true);
		Reporter.log(stockItemRef+" reserved stock quantity is "+reservedStockValue, true);
		Reporter.log(stockItemRef+" blocked stock quantity is "+blockedStockValue, true);
		Reporter.log(stockItemRef+" total stock quantity is "+totalStockValue, true);
		
		waitForExtJSAjaxComplete(10);
		
		addWarehousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitAndClick(XPATH_STOCK_ITEMS);
			
		waitForExtJSAjaxComplete(20);
				
		selectWarehouse(warehouseReference);
				
		waitForExtJSAjaxComplete(20);
				
		Grid.checkRowInGriByTextValue(driver, stockItemRef);
			
		waitForExtJSAjaxComplete(20);
				
		selectTransactionType("Goods Return");
			
		waitForExtJSAjaxComplete(10);
			
	    String defaultWarehouse = getDefaultWarehouseValue(RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER);
			
		softAssert.assertEquals(defaultWarehouse, warehouseReference, "Selected Warehouse is Defaulted in Return From Customer Window");
			
		waitForExtJSAjaxComplete(10);
		
        setReference( transactionReference, RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER);
		
		setTransactionDate(returnDate, RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER);
		
		selectCustomer("Customer Name", customerName, RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER);
		
		selectCollectingPerson("Last Name", returnedBy, RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		String stockLineProdRef = this.getProductRefOfTransactionLine("1");
			
		softAssert.assertEquals(stockLineProdRef, stockItemRef, "Product Reference is displayed in Transaction Line of Return From Customer window");
			
		waitForExtJSAjaxComplete(10);
		
		setTransactionLineQuantity("1", lineQuantity);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineLocationFromAllLocations("1", wareHouseLoc, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(5);
		
		setTransactionLineRemark("1", remark);
		
		waitForExtJSAjaxComplete(5);
		
		save();
		
		waitForExtJSAjaxComplete(5);
		
		setStatus("Executed", RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER );
		
		waitForExtJSAjaxComplete(5);
		
		saveAndClose(RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER);
		
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
		

		int availableStockAfterStockCorrection = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),availableStockColName ));
		int totalStockAfterStockCorrection = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),totalStockColName));
		int blockedStockAfterStockCorrection = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),blockedStockColName));
		int reservedStockAfterStockCorrection  = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),reservedStockColName));
		waitForExtJSAjaxComplete(20);
		
		//Verify Stock items are reduced after Goods issue Transaction
		softAssert.assertTrue((availableStockValue==availableStockAfterStockCorrection), "Available Stock quantity is Reduced");
		softAssert.assertTrue((totalStockValue+Integer.parseInt(lineQuantity))==totalStockAfterStockCorrection, "Total Stock is Reduced");
		softAssert.assertTrue((reservedStockValue==reservedStockAfterStockCorrection) , "Reserved Stock is Unchanged");
		softAssert.assertTrue((blockedStockValue+Integer.parseInt(lineQuantity))==blockedStockAfterStockCorrection, "Blocked Stock is Unchanged");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("Stock item defaults are displayed in Transaction line of Return From Customer<br>"
				+ "Return From Customer Transaction is successfully created from Stock items <br>", true);
	}		
	
}
	