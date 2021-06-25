package test.generalweb.transactions.newfeatures;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.MenuSelector;
import test.generalweb.transactions.TransactionsPageObject;
import test.generalweb.warehouse.AddWarehousePageObject;

public class TransactionsNewFeaturesTestSuite extends TransactionsPageObject{
	
	
	
	/**
	 * Testcase ID	 : C90821
	 * Author		 : vpcc
	 * */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void verifyWarningMsgForZeroQuantityInUnBlockTransaction() throws Exception{
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C90821:While Unblocking the Stocks a message should be displayed if the Quantity is not filled in From <br> </span>", true);
	

		String remark1 = "UnBlockRemark1"+getCurrentDate().substring(getCurrentDate().length()-5);
		String wareHouseLoc = "HYD";
		String warehouseReference = "UnBlockTransactionWH";
	    String stockItemRef1 = "2preConsRef";
		String lookUpWindowCol = "Reference";
		String expectedWarningMsg ="The Quantity must be filled in"  ;
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("verifyWarningMsgForZeroQuantityInUnBlockTransaction");
		
		expandAdministration();
		  
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		  
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
		  
		setTransactionLineLocationFromAllLocations("1",  wareHouseLoc, lookUpWindowCol);
		  
		waitForExtJSAjaxComplete(20);
		  
		setTransactionLineRemark("1", remark1);
		  
		waitForExtJSAjaxComplete(20);
		  
		execute();
		  
		waitForExtJSAjaxComplete(10);
		
		String warningMsg = this.getWarningDialogTextMsg();
		  
		softAssert.assertTrue(warningMsg.contains(expectedWarningMsg), "A message is displayed if the Quantity is not filled  while Unblocking");
		  
		waitForExtJSAjaxComplete(20);
		  
		this.clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(10);
		
		this.close(UNBLOCK_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		this.clickOnDialogButton("Yes");

		Reporter.log("A message is displayed if the Quantity is not filled  while Unblocking <br>",true);
	}
	
	
	/**
	 * Testcase ID	 : C90817
	 * Author		 : vpcc
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void verifyWarningMsgForZeroQuantityInBlockTransaction() throws Exception{
		
		String remark1 = "BlockRemark1"+getCurrentDate().substring(getCurrentDate().length()-5);
		String wareHouseLoc = "HYD";
		String warehouseReference = "BlockTransactionWH";
		String stockItemRef1 = "2preConsRef";
		String lookUpWindowCol = "Reference";
		String expectedWarningMsg ="The Quantity must be filled in"  ;
		SoftAssert softAssert = new SoftAssert();
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "+
	 	"C90817: While Blocking the Stocks a message should be displayed if the Quantity is not filled in From <br></span>", true); 
		
		softAssert.setMethodName("verifyWarningMsgForZeroQuantityInBlockTransaction");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
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
		
		setTransactionLineLocationFromAllLocations("1",  wareHouseLoc, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		setTransactionLineRemark("1", remark1);
		
		waitForExtJSAjaxComplete(20);
		
		execute();
		
		waitForExtJSAjaxComplete(10);
		
		String warningMsg = this.getWarningDialogTextMsg();
		
		softAssert.assertTrue(warningMsg.contains(expectedWarningMsg), "A message is displayed if the Quantity is not filled  while blocking");
		  
		waitForExtJSAjaxComplete(20);
		  
		this.clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(10);
		
		this.close(BLOCK_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		this.clickOnDialogButton("Yes");

		Reporter.log("A message is displayed if the Quantity is not filled  while blocking <br>",true);
		
	
	}
	
	
	/**
	 * TestCase ID :C90802
	 * Author : ssa
	 */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testLocationDefaultsInGRTxnLine () throws Exception{
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C90802:Verify system populates Default Storage Location in the Location field automatically on the Goods Receipt form </span><br>",true);	
		
		String transactionReference = "C44776Ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String warehouseReference = "GRDefaultLocWH";
		String stockItemRef = "2preConsRef";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		String defaultLocColName = "Default Storage Location";
		String lookUpWindowCol = "Reference";
		
		
		SoftAssert softAssert = new SoftAssert();
		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
	
		softAssert.setMethodName("testLocationDefaultsInGRTxnLine");
		
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
		
		String DefaultLocValue = wareHousePageObject.getStockItemsCellValue(getProductRowNumberInWareHouse(stockItemRef),defaultLocColName);
	
		Reporter.log(DefaultLocValue+" is available default location for product"+stockItemRef, true);
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		waitForExtJSAjaxComplete(20);
		
		this.selectTransactionType("Goods Receipt");
		
		waitForExtJSAjaxComplete(20);
		
		setWareHouse("Reference", warehouseReference, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		setReference( transactionReference, GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER);
		
		//***************************Stock items transaction************************
		clickAddTransactionLines();

		MenuSelector.selectMenuItemNative(driver, "Stock");

		waitForExtJSAjaxComplete(20);

		setTransactionLineProductReferenceFromProductsAndServices("1", stockItemRef, lookUpWindowCol);

		waitForExtJSAjaxComplete(20);
		
		String populatedDefaultLocValue = getDefaultLocOfTransactionLine("1");
		
		softAssert.assertEquals(populatedDefaultLocValue,DefaultLocValue,"System populates default storage location in the Location field automatically");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("System populates Default Storage Location in the Location field automatically on the Goods Receipt form ",  true);
		
	
	}
		
	
}

