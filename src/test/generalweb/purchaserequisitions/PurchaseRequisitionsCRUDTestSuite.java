package test.generalweb.purchaserequisitions;

//import org.openqa.selenium.By;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.generalweb.warehouse.AddWarehousePageObject;

public class PurchaseRequisitionsCRUDTestSuite extends PurchaseRequisitionsPageObject {
	
	/**
	 * Testcase ID		: C90909,C90912,C90913
	 * Author			: vpcc
	 * */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testPurchaseRequsitionCreateDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"Test: C90912:Alert Pop up should come when user tries to create a Requisition without selecting warehouse"
				+ "Test: Add a Purchase Requisitons: C90909 "
				+"Test : C90913: Delete a Purchase Requisition From Overview </span><br>", true);
		
		String unitsMeasureRef = "piece";
		String description = "C52423Desc" + getCurrentDate().substring(getCurrentDate().length()-5);
		String warningMsg = "Please select a Warehouse";
		String savedMsg = "Saved successfully";
		String costCategory = "1preCostCtRef";
		String reqStatus = "Requested";
		String reqStatusToSet = "Request";
		String warehouseRef = "Central Warehouse";
		String referenceColName = "Reference";
		String requisitionDate = this.getFutureDate(0);
		String productReference = "2preConsRef";
		String documentType = "Std Purchase Req";
		String requester = "SELENIUM";
		String purchasingOrganization = "AQAPurchOrg"; //"Huntsman Belgium"
		String purchasingGrp = "aqaPOrgRef"; //"Engineering"
		String orderQuantity = "10";
		String unitPrice = "10";
		String currency = "EUR";
		
		SoftAssert softAssert = new SoftAssert(); 
		softAssert.setMethodName("testPurchaseRequsitionCreateDelete");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_REQUISITIONS);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton();

		waitForExtJSAjaxComplete(20);
		
		//C52426 testcase verification
		softAssert.assertTrue(getWarningDialogTextMsg().contains(warningMsg), "User cannot create Purchase Requisitions without selecting warehouse");
		
		Reporter.log("User cannot create Purchase Requisitions without selecting warehouse <br>", true);
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(5);
		
		selectWareHouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(5);
		
		setDocumentType(referenceColName, documentType, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		setRequisitionDate(requisitionDate, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		setRequester( "Last Name", requester, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		addPRLine(ADD_REQUISIION_WINDOW_HEADER , productReference, referenceColName);
		
		waitForExtJSAjaxComplete(25);
		
		selectPRLine(ADD_REQUISIION_WINDOW_HEADER , "1");
		
		waitForExtJSAjaxComplete(25);
		
		//*****************General tab actions*****************
		clickGeneralTab(ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		setLineNr("1", ADD_REQUISIION_WINDOW_HEADER) ;
	
		setOrderQuantity(orderQuantity, ADD_REQUISIION_WINDOW_HEADER);
		
		setUnitPrice(unitPrice ,  ADD_REQUISIION_WINDOW_HEADER); 
		
		setOrderUOM(referenceColName, unitsMeasureRef,  ADD_REQUISIION_WINDOW_HEADER);
		
		selectCurrency( currency,  ADD_REQUISIION_WINDOW_HEADER);
	
		//******************Description tab actions**********************
		
		clickDescriptionTab(ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		setDescription( description,ADD_REQUISIION_WINDOW_HEADER) ;
	
		//************Financial tab actions********************
		
		clickFinancialTab(ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		setCostCategory(costCategory, ADD_REQUISIION_WINDOW_HEADER);
		
		//*******************Tracking tab actions ***********************
		clickTrackingTab(ADD_REQUISIION_WINDOW_HEADER); 
		
		waitForExtJSAjaxComplete(5);
		
		setPurchasingOrganization(referenceColName, purchasingOrganization, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		//As Reference column matches for two columns in Lookup. So filtering by 'Purchasing Organization Reference' column 
		setPurchasingGroup(referenceColName, purchasingOrganization, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		save(ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		String infoBarMsg = getInfoBarMsgOfWindow("@id",getXWindowId(EDIT_REQUISIION_WINDOW_HEADER));
		
		softAssert.assertEquals(infoBarMsg, savedMsg, "Purcahse Requisiton is Saved successfully");
		
		this.setStatus(reqStatusToSet, EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		save(EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		String statusOFReq = getStatus(EDIT_REQUISIION_WINDOW_HEADER);
		
		softAssert.assertEquals(statusOFReq,reqStatus ," Requsition is in Requested status");
		
		waitForExtJSAjaxComplete(5);
		
		String requistionLineID  = getRequisitionLineID(EDIT_REQUISIION_WINDOW_HEADER,"1");
		
		//String requistionID = getRequisitionID(EDIT_REQUISIION_WINDOW_HEADER); 
		
		Reporter.log("Requsition line id is"+requistionLineID, true);
		
		close(EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, requistionLineID);
		
		waitForExtJSAjaxComplete(10);
		
		clickDeleteButton();
		
		waitForExtJSAjaxComplete(10);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		selectWareHouse(warehouseRef);
		
		waitForExtJSAjaxComplete(20);
		
		boolean isReqDeleted = Grid.isRowInGridAbsent(driver, requistionLineID,"mogrid", "@realID");
		
		softAssert.assertTrue(isReqDeleted, "Purchase requistion with Requested status is deleted");
		
		softAssert.assertAll();
		
		Reporter.log("Purchase requistion was succesfully created and deleted", true);
	}
	
	
	/**
	 * Testcase ID		: C90932 
	 * Author			: ssa
	 * */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testWarehouseStockItemsProductsInPR()
	{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:Verify Purchasing products are displayed in Purchase Requisition window: C90932</span><br>", true);

		Reporter.log("User Verify Purchasing Product in PR <br>",true);
		
		String warehouseReference = "BlockTransactionWH";
		String documentTypeOfPR= "Std Purchase Req";
		String StockItemRefColName = "Reference";
		String StocItemRefName = "2preConsRef";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		SoftAssert softAssert = new SoftAssert();
		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
		softAssert.setMethodName("testWarehouseStockItemsProductsInPR");
		
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
		
		String AvailableProductInPR1 = wareHousePageObject.getStockItemsCellValue(wareHousePageObject.getProductRowNumberInWareHouse("6preProdRef"),StockItemRefColName);
		String AvailableProductInPR2 = wareHousePageObject.getStockItemsCellValue(wareHousePageObject.getProductRowNumberInWareHouse(StocItemRefName),StockItemRefColName); 
		
		String []productReferences = {AvailableProductInPR1,AvailableProductInPR2};
		
		Reporter.log("Available StockItems  are in warehouse ="+AvailableProductInPR1+ "," +AvailableProductInPR2, true);
		
	    wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_REQUISITIONS);
		
		waitForExtJSAjaxComplete(20);
		
		selectWareHouse(warehouseReference);
		
		clickAddButton();
		
		setDocumentType("Reference", documentTypeOfPR, ADD_REQUISIION_WINDOW_HEADER);
		
		clickOnAddPRLineButton(ADD_REQUISIION_WINDOW_HEADER);
		
		List<String> productReferencesList = getProdRefValues(StockItemRefColName);
	
		softAssert.assertEqualsNoOrder(productReferencesList.toArray(), productReferences , "Purchasing Produccts in PR are successfully displayed which is available in warehouse stock items tab");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertAll();
		
		Reporter.log("Stock items present in Warehouse are only listed in Product catalog in PR<br>", true);
		
	}
	
	/**
	 * Testcase ID		: C90916 
	 * Author			: ssa
	 * */
		
		@Test(retryAnalyzer=RetryAnalyzer.class)
		public void testVerifyDisabledFieldsInPR()
		{
			Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Check Related Documents/ Add and Delete Button are Disabled before selecting a Document Type: C90916</span><br>", true);

			Reporter.log("User is verifying disabled fields in PR<br>", true);
			
			String warehouseReference = "Central Warehouse";
			String documentTypeOfPR= "Std Purchase Req";
			
			SoftAssert softAssert = new SoftAssert();
			softAssert.setMethodName("testVerifyDisabledFieldsInPR");
			
			waitForExtJSAjaxComplete(20);
			
			expandSubMainMenu("Stock");
			
			waitForExtJSAjaxComplete(20);
			
			waitAndClick(XPATH_PURCHASE_REQUISITIONS);
			
			waitForExtJSAjaxComplete(20);
			
			selectWareHouse(warehouseReference);
			
			clickAddButton();
			
			waitForExtJSAjaxComplete(20);
			
			Reporter.log(" Related Documents/ Add and Delete Button are Disabled before selecting a Document Type", true);
			
			//Filed is enabled
			//boolean status = isDisabledRelatedDocTab(ADD_REQUISIION_WINDOW_HEADER);
			//softAssert.assertTrue(status,"Related Document tab is disabled");
			
			boolean status = isDisabledAddButton(ADD_REQUISIION_WINDOW_HEADER);
			softAssert.assertTrue(status,"Add button is disabled");
			
			status = isDisabledDeleteButton(ADD_REQUISIION_WINDOW_HEADER);
			softAssert.assertTrue(status,"Delete button is disabled");
			
			status = isDisabledStatus(ADD_REQUISIION_WINDOW_HEADER);
			softAssert.assertTrue(status,"status Actions field is disabled");
			
			status = isDisabledCostCarrier(ADD_REQUISIION_WINDOW_HEADER);
			softAssert.assertTrue(status,"Cost carrier field is disabled");
			
			waitForExtJSAjaxComplete(20);
			
			setDocumentType("Reference", documentTypeOfPR, ADD_REQUISIION_WINDOW_HEADER);
			
			Reporter.log(" Related Documents/ Add and Delete Button are Disabled after selecting a Document Type", true);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertFalse(isDisabledAddButton(ADD_REQUISIION_WINDOW_HEADER),"Add button is enabled");
			
			softAssert.assertAll();
		
			Reporter.log("Verified Related Documents/ Add and Delete Button are Disabled before selecting a Document Type", true);
	}
	
		/**
		 * Testcase ID		: C90915
		 * Author			: ssa
		 * */
		
		@Test(retryAnalyzer=RetryAnalyzer.class)
		public void testProductDetailsInPurchasingProduct() throws Exception {

			Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+"Verify the product details in the purchasing product: C90915</span><br>", true);
			
			
			String savedMsg = "Saved successfully";
			String reqStatus = "Needed";
			String warehouseRef = "Central Warehouse";
			String referenceColName = "Reference";
			String productReference = "2preConsRef";
			String productCode = "2preConsCod";
			String documentType = "Std Purchase Req";
			String purchasingOrganization = "AQAPurchOrg"; 
			String orderQuantity1 = "10";
			String orderQuantity2 = "20";
			
			
			SoftAssert softAssert = new SoftAssert(); 
			softAssert.setMethodName("testProductDetailsInPurchasingProduct");
			
			expandAdministration();
			
			waitForExtJSAjaxComplete(20);

			expandSubMainMenu("Stock");
			
			waitForExtJSAjaxComplete(20);
			
			waitAndClick(XPATH_PURCHASE_REQUISITIONS);
			
			waitForExtJSAjaxComplete(20);
			
			selectWareHouse(warehouseRef);
			
			waitForExtJSAjaxComplete(20);
			
			clickAddButton();
			
			waitForExtJSAjaxComplete(25);
			
			setDocumentType(referenceColName, documentType, ADD_REQUISIION_WINDOW_HEADER);
			
			
			//************************* Add first product*****************
			addPRLine(ADD_REQUISIION_WINDOW_HEADER , productReference, referenceColName);
			
			waitForExtJSAjaxComplete(25);
			
			selectPRLine(ADD_REQUISIION_WINDOW_HEADER , "1");
			
			waitForExtJSAjaxComplete(25);
			
			//*****************General tab actions*****************
			clickGeneralTab(ADD_REQUISIION_WINDOW_HEADER);
			
			waitForExtJSAjaxComplete(10);
		
			setOrderQuantity(orderQuantity1, ADD_REQUISIION_WINDOW_HEADER);
			
			//*******************Tracking tab actions ***********************
			clickTrackingTab(ADD_REQUISIION_WINDOW_HEADER); 
			
			waitForExtJSAjaxComplete(5);
			
			setPurchasingOrganization(referenceColName, purchasingOrganization, ADD_REQUISIION_WINDOW_HEADER);
			
			waitForExtJSAjaxComplete(5);
			
			//As Reference column matches for two columns in Lookup. So filtering by 'Purchasing Organization Reference' column 
			setPurchasingGroup(referenceColName, purchasingOrganization, ADD_REQUISIION_WINDOW_HEADER);
			
			waitForExtJSAjaxComplete(5);
			
			save(ADD_REQUISIION_WINDOW_HEADER);
			
			waitForExtJSAjaxComplete(20);
			
			//Get Order Quantity for first product
            int orderQuantityOfProductLn1 = Integer.parseInt(getOrderQuantityOfProductLn(EDIT_REQUISIION_WINDOW_HEADER,"1"));
			
			Reporter.log("The Order Quantity of first product is" +orderQuantityOfProductLn1, true);
			
			//Get Line ID for first product
			String requisitionLineID1 =getRequisitionLineID(EDIT_REQUISIION_WINDOW_HEADER,"1");
			
			String infoBarMsg = getInfoBarMsgOfWindow("@id",getXWindowId(EDIT_REQUISIION_WINDOW_HEADER));
			
			softAssert.assertEquals(infoBarMsg, savedMsg, "Purcahse Requisiton is Saved successfully");
			
			String statusOFReq = getStatus(EDIT_REQUISIION_WINDOW_HEADER);
				
			softAssert.assertEquals(statusOFReq,reqStatus ," Requsition is in Needed status");
			
			waitForExtJSAjaxComplete(10);
			
			//********************************Add second product************************
			
			addPRLine(EDIT_REQUISIION_WINDOW_HEADER , productReference, referenceColName);
			
            waitForExtJSAjaxComplete(25);
			
			selectPRLine(EDIT_REQUISIION_WINDOW_HEADER , "2");
			
			waitForExtJSAjaxComplete(25);
			
			//*****************General tab actions*****************
			clickGeneralTab(EDIT_REQUISIION_WINDOW_HEADER);
			
			waitForExtJSAjaxComplete(10);
			
			setOrderQuantity(orderQuantity2, EDIT_REQUISIION_WINDOW_HEADER);
			
			waitForExtJSAjaxComplete(10);
			
			//*******************Tracking tab actions ***********************
			clickTrackingTab(EDIT_REQUISIION_WINDOW_HEADER); 
			
			waitForExtJSAjaxComplete(5);
			
			setPurchasingOrganization(referenceColName, purchasingOrganization, EDIT_REQUISIION_WINDOW_HEADER);
			
			waitForExtJSAjaxComplete(5);
			
			//As Reference column matches for two columns in Lookup. So filtering by 'Purchasing Organization Reference' column 
			setPurchasingGroup(referenceColName, purchasingOrganization, EDIT_REQUISIION_WINDOW_HEADER);
			
			waitForExtJSAjaxComplete(5);
			
			save(EDIT_REQUISIION_WINDOW_HEADER);
			
			String requisitionLineID2 = getRequisitionLineID(EDIT_REQUISIION_WINDOW_HEADER,"2");
			
			waitForExtJSAjaxComplete(25);
			
			//Verifying details corresponding to that product for First 
			
			waitForExtJSAjaxComplete(30);
			
			Grid.checkRowInGriByTextValueAndClick(driver, ADD_REQUISIITONS_WINDOW_CLASS, requisitionLineID1);
			
			waitForExtJSAjaxComplete(30);
			
			clickGeneralTab(EDIT_REQUISIION_WINDOW_HEADER);
			
			waitForExtJSAjaxComplete(30);
			
			
			//********************************General Tab******************************
		
			String ProductRefFromGeneralTab1=getProductRefFromGeneralTab(EDIT_REQUISIION_WINDOW_HEADER);
			softAssert.assertEquals(ProductRefFromGeneralTab1,productReference,"Product reference is displayed in the General tab which is corresponding to first product line");
			
			String getProductCodeFromGeneralTab1=getProductCodeFromGeneralTab(EDIT_REQUISIION_WINDOW_HEADER);
			softAssert.assertEquals(getProductCodeFromGeneralTab1,productCode,"Product code is displayed in the General tab which is corresponding to first product line");
			
			int getOrderQuantityFromGeneralTab1 = Integer.parseInt(getOrderQuantityFromGeneralTab(EDIT_REQUISIION_WINDOW_HEADER));
			softAssert.assertEquals(getOrderQuantityFromGeneralTab1,orderQuantityOfProductLn1,"Order quantity is displayed in the General tab which is corresponding to first product line");
	
			waitForExtJSAjaxComplete(25);
			
			Grid.checkRowInGriByTextValueAndClick(driver, ADD_REQUISIITONS_WINDOW_CLASS, requisitionLineID2); 
			
			waitForExtJSAjaxComplete(20);
			
			//Verifying details corresponding to that product
			//********************************General Tab******************************
			
			String ProductRefFromGeneralTab2=getProductRefFromGeneralTab(EDIT_REQUISIION_WINDOW_HEADER);
			softAssert.assertEquals(ProductRefFromGeneralTab2,productReference,"Product reference is displayed in the General tab which is corresponding to second product line");
			
			String getProductCodeFromGeneralTab2=getProductCodeFromGeneralTab(EDIT_REQUISIION_WINDOW_HEADER);
			softAssert.assertEquals(getProductCodeFromGeneralTab2,productCode,"Product code is displayed in the General tab which is corresponding to second product line");
			
			int orderQuantityOfProductLn2 = Integer.parseInt(getOrderQuantityOfProductLn(EDIT_REQUISIION_WINDOW_HEADER,"2"));
			int orderQuantityFromGeneralTab2 = Integer.parseInt(getOrderQuantityFromGeneralTab(EDIT_REQUISIION_WINDOW_HEADER));
			softAssert.assertEquals(orderQuantityFromGeneralTab2,orderQuantityOfProductLn2,"Order quantity is displayed in the General tab which is corresponding to second product line");

			waitForExtJSAjaxComplete(20);
			
			close(EDIT_REQUISIION_WINDOW_HEADER);
			
			waitForExtJSAjaxComplete(20);
			
			Grid.checkRowInGriByTextValue(driver, requisitionLineID1);
			
			waitForExtJSAjaxComplete(10);
			
			clickDeleteButton();
			
			waitForExtJSAjaxComplete(10);
			
			clickOnDialogButton("Yes");
			
			waitForExtJSAjaxComplete(10);
			
			clickOnDialogButton("OK");
			
			waitForExtJSAjaxComplete(20);
			
			selectWareHouse(warehouseRef);
			
			waitForExtJSAjaxComplete(25);
			
			boolean isReqDeleted = Grid.isRowInGridAbsent(driver, requisitionLineID1,"mogrid", "@realID");
			
			softAssert.assertTrue(isReqDeleted, "Purchase requistion with Needed status is deleted");
			
			softAssert.assertAll();
			
			Reporter.log("Product details are displayed in General tab which are corresponding to that product only", true);
			
		}


		/**
		 * Testcase ID		: C90911,C90914,C90910
		 * Author			: vpcc
		 * */
		@Test(retryAnalyzer=RetryAnalyzer.class)
		public void testPRDelProdDocUploadAndReqStatusUnEditable() throws Exception {

			Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+"C90911:Adding/Removing Documents and URL to/from Purchase Requisitons"
					+ "Test: C90914:Cannot Edit when a Purchase Requisition is in Requested Status "
					+"Test : C90910:Delete a Purchase Requisitions </span><br>", true);

			String unitsMeasureRef = "piece";
			String description = "C52425Desc" + getCurrentDate().substring(getCurrentDate().length()-5);
			String warningMsg = "Please select a Warehouse";
			String savedMsg = "Saved successfully";
			String costCategory = "1preCostCtRef";
			String reqStatus = "Requested";
			String reqStatusToSet = "Request";
			String warehouseRef = "Central Warehouse";
			String referenceColName = "Reference";
			String requisitionDate = this.getFutureDate(0);
			String productReference = "2preConsRef";
			String documentType = "Std Purchase Req";
			String requester = "SELENIUM";
			String purchasingOrganization = "AQAPurchOrg"; //"Huntsman Belgium"
			String purchasingGrp = "aqaPOrgRef"; //"Engineering"
			String orderQuantity = "10";
			String unitPrice = "10";
			String currency = "EUR";

			//Variables
			String url = "http://test.com";
			String file = "test.csv";
			String urlRemark =  "C35973UrlRem" + getCurrentDate().substring(getCurrentDate().length()-6);
			String fileDescription =  "C35973FilDes" + getCurrentDate().substring(getCurrentDate().length()-6);
			String urlDescription =  "C35973UrlDesc" + getCurrentDate().substring(getCurrentDate().length()-6);
			String fileRemark =  "C35973FilRem" + getCurrentDate().substring(getCurrentDate().length()-6);
			String type = "labelen";


			SoftAssert softAssert = new SoftAssert(); 
			softAssert.setMethodName("testPRDelProdDocUploadAndReqStatusUnEditable");

			expandAdministration();

			waitForExtJSAjaxComplete(20);

			expandSubMainMenu("Stock");

			waitForExtJSAjaxComplete(20);

			waitAndClick(XPATH_PURCHASE_REQUISITIONS);

			waitForExtJSAjaxComplete(20);

			selectWareHouse(warehouseRef);

			waitForExtJSAjaxComplete(5);

			clickAddButton();

			waitForExtJSAjaxComplete(5);

			setDocumentType(referenceColName, documentType, ADD_REQUISIION_WINDOW_HEADER);

			waitForExtJSAjaxComplete(5);

			setRequisitionDate(requisitionDate, ADD_REQUISIION_WINDOW_HEADER);

			waitForExtJSAjaxComplete(5);

			setRequester( "Last Name", requester, ADD_REQUISIION_WINDOW_HEADER);

			waitForExtJSAjaxComplete(5);

			addPRLine(ADD_REQUISIION_WINDOW_HEADER , productReference, referenceColName);

			waitForExtJSAjaxComplete(25);

			selectPRLine(ADD_REQUISIION_WINDOW_HEADER , "1");

			waitForExtJSAjaxComplete(25);

			//*******************Tracking tab actions ***********************
			clickTrackingTab(ADD_REQUISIION_WINDOW_HEADER); 

			waitForExtJSAjaxComplete(5);

			setPurchasingOrganization(referenceColName, purchasingOrganization, ADD_REQUISIION_WINDOW_HEADER);

			waitForExtJSAjaxComplete(5);

			//As Reference column matches for two columns in Lookup. So filtering by 'Purchasing Organization Reference' column 
			setPurchasingGroup(referenceColName, purchasingOrganization, ADD_REQUISIION_WINDOW_HEADER);

			waitForExtJSAjaxComplete(5);

			save(ADD_REQUISIION_WINDOW_HEADER);

			waitForExtJSAjaxComplete(15);

			Grid.checkRowInGriByTextValueAndClick(driver, "@id",getXWindowId(EDIT_REQUISIION_WINDOW_HEADER), productReference);
			
			//selectPRLine(EDIT_REQUISIION_WINDOW_HEADER , "1");
			
			waitForExtJSAjaxComplete(15);
			
			clickOnDeletePRLineButton(EDIT_REQUISIION_WINDOW_HEADER);

			waitForExtJSAjaxComplete(15);

			int noOFReqLines = getNumberOfRequisitionLines(EDIT_REQUISIION_WINDOW_HEADER);

			//C52424: End of testcase
			//TODO: A single PR line can no longer be deleted. To handle this updating the code @vpcc
			//softAssert.assertEquals(noOFReqLines, 0, "Requisition line is deleted");
			softAssert.assertEquals(noOFReqLines, 1, "Single Requisition line can't be deleted");
			
			addPRLine(EDIT_REQUISIION_WINDOW_HEADER , productReference, referenceColName);

			//*****************General tab actions*****************
			clickGeneralTab(EDIT_REQUISIION_WINDOW_HEADER);

			waitForExtJSAjaxComplete(10);

			setLineNr("1", EDIT_REQUISIION_WINDOW_HEADER) ;

			setOrderQuantity(orderQuantity, EDIT_REQUISIION_WINDOW_HEADER);

			setUnitPrice(unitPrice ,  EDIT_REQUISIION_WINDOW_HEADER); 

			setOrderUOM(referenceColName, unitsMeasureRef,  EDIT_REQUISIION_WINDOW_HEADER);

			selectCurrency( currency,  EDIT_REQUISIION_WINDOW_HEADER);

			//******************Description tab actions**********************

			clickDescriptionTab(EDIT_REQUISIION_WINDOW_HEADER);

			waitForExtJSAjaxComplete(5);

			setDescription( description,EDIT_REQUISIION_WINDOW_HEADER) ;

			//************Financial tab actions********************

			clickFinancialTab(EDIT_REQUISIION_WINDOW_HEADER);

			waitForExtJSAjaxComplete(5);

			setCostCategory(costCategory, EDIT_REQUISIION_WINDOW_HEADER);

			//*******************Tracking tab actions ***********************
			clickTrackingTab(EDIT_REQUISIION_WINDOW_HEADER); 

			waitForExtJSAjaxComplete(5);

			setPurchasingOrganization(referenceColName, purchasingOrganization, EDIT_REQUISIION_WINDOW_HEADER);

			waitForExtJSAjaxComplete(5);

			//As Reference column matches for two columns in Lookup. So filtering by 'Purchasing Organization Reference' column 
			setPurchasingGroup(referenceColName, purchasingOrganization, EDIT_REQUISIION_WINDOW_HEADER);

			waitForExtJSAjaxComplete(5);

			save(EDIT_REQUISIION_WINDOW_HEADER);

			waitForExtJSAjaxComplete(20);

			String infoBarMsg = getInfoBarMsgOfWindow("@id",getXWindowId(EDIT_REQUISIION_WINDOW_HEADER));

			softAssert.assertEquals(infoBarMsg, savedMsg, "Purcahse Requisiton is Saved successfully");
			
			//C52425 testcase start
			clickRelatedDocumentsTab(EDIT_REQUISIION_WINDOW_HEADER);

			//Url to be added
			setUrl(url, urlDescription, urlRemark, type);

			deleteUrl( EDIT_REQUISIION_WINDOW_HEADER,urlDescription);

			waitForExtJSAjaxComplete(10);

			//File to be uploaded
			setFile(file, fileDescription, fileRemark, type);

			waitForExtJSAjaxComplete(10);

			deleteUrl(EDIT_REQUISIION_WINDOW_HEADER,fileDescription); 

			//C52425 test case end
			waitForExtJSAjaxComplete(10);

			clickLineItemsTab(EDIT_REQUISIION_WINDOW_HEADER);

			waitForExtJSAjaxComplete(10);

			setStatus(reqStatusToSet, EDIT_REQUISIION_WINDOW_HEADER);

			waitForExtJSAjaxComplete(10);

			save(EDIT_REQUISIION_WINDOW_HEADER);

			waitForExtJSAjaxComplete(20);

			String statusOFReq = getStatus(EDIT_REQUISIION_WINDOW_HEADER);

			softAssert.assertEquals(statusOFReq,reqStatus ," Requsition is in Requested status");

			waitForExtJSAjaxComplete(5);

			//selectPRLine(EDIT_REQUISIION_WINDOW_HEADER,"1");
			Grid.checkRowInGriByTextValueAndClick(driver, "@id",getXWindowId(EDIT_REQUISIION_WINDOW_HEADER), productReference);
			
			waitForExtJSAjaxComplete(5);

			clickGeneralTab(EDIT_REQUISIION_WINDOW_HEADER);

			waitForExtJSAjaxComplete(10);

			softAssert.assertTrue(isOrderQuantityDisabled(EDIT_REQUISIION_WINDOW_HEADER), "Order quantity is not editable");

			softAssert.assertTrue(isUnitPriceDisabled(EDIT_REQUISIION_WINDOW_HEADER), "Unit price is not editable");

			close(EDIT_REQUISIION_WINDOW_HEADER);

			softAssert.assertAll();

			Reporter.log("Product will be deleted only when it is in Needed Status <br>",true);
			Reporter.log("User can add/Remove Documents and URL to/from Purchase Requisitons <br>" ,true);
			Reporter.log("Purchase requistion in Requested state is not editable <br>", true);
		}
		
		
		
		/**
		 * Testcase ID		: C90936
		 * Author			: ssa
		 * */
		@Test(retryAnalyzer=RetryAnalyzer.class)
		public void testDisabledFieldsInPRAfterchangeStausToRework() throws Exception {
			
			
			Reporter.log("<span style='font-weight:bold;color:#000033'>"
					+ "C90936: It is possible to edit in Purchase requisition with Requested Status after changing to Rework Status <br>", true);
			
			
			String savedMsg = "Saved successfully";
			String reqStatus = "Requested";
			String reqStatusToSet = "Request";
			String warehouseRef = "Central Warehouse";
			String referenceColName = "Reference";
			String productReference = "2preConsRef";
			String documentType = "Std Purchase Req";
			String purchasingOrganization = "AQAPurchOrg"; 
			
			String emptyGridText="No lines on this document";	
			SoftAssert softAssert = new SoftAssert(); 
			softAssert.setMethodName("testDisabledFieldsInPRAfterchangeStausToRework");

			expandAdministration();

			waitForExtJSAjaxComplete(20);

			expandSubMainMenu("Stock");

			waitForExtJSAjaxComplete(20);

			waitAndClick(XPATH_PURCHASE_REQUISITIONS);

			waitForExtJSAjaxComplete(20);
			waitForExtJSAjaxComplete(20);
			
			selectWareHouse(warehouseRef);
			
			waitForExtJSAjaxComplete(20);
			
			clickAddButton();
			
			waitForExtJSAjaxComplete(30);
			
			setDocumentType(referenceColName, documentType, ADD_REQUISIION_WINDOW_HEADER);
			
			//************************* Add first product*****************
			addPRLine(ADD_REQUISIION_WINDOW_HEADER , productReference, referenceColName);
			
			waitForExtJSAjaxComplete(25);
			
			selectPRLine(ADD_REQUISIION_WINDOW_HEADER , "1");
			
			waitForExtJSAjaxComplete(25);
			
			//*******************Tracking tab actions ***********************
			clickTrackingTab(ADD_REQUISIION_WINDOW_HEADER); 
			
			waitForExtJSAjaxComplete(5);
			
			setPurchasingOrganization(referenceColName, purchasingOrganization, ADD_REQUISIION_WINDOW_HEADER);
			
			waitForExtJSAjaxComplete(5);
			
			//As Reference column matches for two columns in Lookup. So filtering by 'Purchasing Organization Reference' column 
			setPurchasingGroup(referenceColName, purchasingOrganization, ADD_REQUISIION_WINDOW_HEADER);
			
			waitForExtJSAjaxComplete(5);
			
			save(ADD_REQUISIION_WINDOW_HEADER);
			
			waitForExtJSAjaxComplete(20);
			
			String infoBarMsg = getInfoBarMsgOfWindow("@id",getXWindowId(EDIT_REQUISIION_WINDOW_HEADER));
			
			softAssert.assertEquals(infoBarMsg, savedMsg, "Purcahse Requisiton is Saved successfully");
			
			setStatus(reqStatusToSet, EDIT_REQUISIION_WINDOW_HEADER);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			save(EDIT_REQUISIION_WINDOW_HEADER);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			String statusOFReq = getStatus(EDIT_REQUISIION_WINDOW_HEADER);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertEquals(statusOFReq,reqStatus ," Requsition is in Requested status");
			
			waitForExtJSAjaxComplete(5);
			
			String requistionLineID  = getRequisitionLineID(EDIT_REQUISIION_WINDOW_HEADER,"1");
			
			waitForExtJSAjaxComplete(20);
			
			getStatus(EDIT_REQUISIION_WINDOW_HEADER);
			
			waitForExtJSAjaxComplete(10);
			
			selectPRLine(EDIT_REQUISIION_WINDOW_HEADER,"1");
			
			waitForExtJSAjaxComplete(10);
			
			Boolean status=isDisabledAddButton(EDIT_REQUISIION_WINDOW_HEADER);
			
			softAssert.assertTrue(status, "Add button is disabled");
			
			status=isDisabledDeleteButton(EDIT_REQUISIION_WINDOW_HEADER);
			
			softAssert.assertTrue(status, "Delete button is disabled");
			
	        waitForExtJSAjaxComplete(20);
			
			setStatus("Rework",EDIT_REQUISIION_WINDOW_HEADER);
			
			waitForExtJSAjaxComplete(25);
			
			save(EDIT_REQUISIION_WINDOW_HEADER);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			status=isDisabledAddButton(EDIT_REQUISIION_WINDOW_HEADER);
			
			softAssert.assertFalse(status, "Add button is Enabled");
			
			waitForExtJSAjaxComplete(10);
			
			selectPRLine(EDIT_REQUISIION_WINDOW_HEADER,"1");
			
			waitForExtJSAjaxComplete(20);
			
			status=isDisabledDeleteButton(EDIT_REQUISIION_WINDOW_HEADER);
			
			softAssert.assertFalse(status, "Delete button is Enabled");
			
			waitForExtJSAjaxComplete(10);
			
			selectPRLine(EDIT_REQUISIION_WINDOW_HEADER,"1");
			
			waitForExtJSAjaxComplete(10);
			
			clickOnDeletePRLineButton(EDIT_REQUISIION_WINDOW_HEADER);
			
			waitForExtJSAjaxComplete(20);
			waitForExtJSAjaxComplete(20);
			
			boolean text=isPRLineGridEmpty(EDIT_REQUISIION_WINDOW_HEADER,"1");
			
			waitForExtJSAjaxComplete(10);
			
			//TODO: A single PR line can no longer be deleted. To handle this updating the code @vpcc
			softAssert.assertFalse(text,"PR line is deleted and No lines on this document message is displayed");
			
			softAssert.assertAll();
			
			Reporter.log("Disabled fields in PR window is verified sucessfully after changing status to remork");
				
			
		}		
}




