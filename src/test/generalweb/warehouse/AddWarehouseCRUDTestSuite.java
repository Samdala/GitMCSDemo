package test.generalweb.warehouse;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import test.generalweb.purchasingproduct.PurchasingProductPageObject;
import test.generalweb.transactions.TransactionsPageObject;
import test.generalweb.workorders.WorkOrderDetailTabsPageObject;
import test.generalweb.workorders.WorkOrderPageObject;


public class AddWarehouseCRUDTestSuite extends
		AddWarehousePageObject {

	/**
	 * Testcase ID : C71570 
	 * Author : Intellias
	 * */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testWarehouseCreateEdit() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C71570: Create/Update/Delete Warehouse </span><br>", true);

		Reporter.log("User creates/edits warehouse <br>", true);
		
		String warehouseReference = "warehouseRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String warehouseCode = "warehouseCode"+getCurrentDate().substring(getCurrentDate().length()-5);
		String description = "description"+getCurrentDate().substring(getCurrentDate().length()-5);
		String adres1 = "adres1"+getCurrentDate().substring(getCurrentDate().length()-5);	
		String adres2 = "adres2"+getCurrentDate().substring(getCurrentDate().length()-5);
		String city = "city"+getCurrentDate().substring(getCurrentDate().length()-5);
		String zip = "123";
		String state = "state";
		String country = "Albania";
		String phone = "1234567";
		String timeZone = "(GMT+01:00) Amsterdam, Berlin, Bern, Rome, Stockholm, Vienna";
		String type= "Staffed";
		String warehouseReferenceEdited = "warehouseRefEd"+getCurrentDate().substring(getCurrentDate().length()-5);
		String warehouseCodeEdited = "warehouseCodeEd"+getCurrentDate().substring(getCurrentDate().length()-5);
		String descriptionEdited = "descriptionEd"+getCurrentDate().substring(getCurrentDate().length()-5);
		String adres1Edited = "adres1Ed"+getCurrentDate().substring(getCurrentDate().length()-5);	
		String adres2Edited = "adres2Ed"+getCurrentDate().substring(getCurrentDate().length()-5);
		String cityEdited = "cityEd"+getCurrentDate().substring(getCurrentDate().length()-5);
		String zipEdited = "1235";
		String stateEdited = "stateEd";
		String countryEdited = "Denmark";
		String phoneEdited = "12345678";
		String timeZoneEdited = "(GMT+01:00) Belgrade, Bratislava, Budapest, Ljubljana, Prague";
		
		String warningDefaultMessage ="There can be only one default warehouse. If you make this warehouse the default, the current default warehouse will be replaced with this warehouse. Continue anyway?"; 
		String defaultWarehouse = "Central Warehouse";	
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testWarehouseCreateEdit");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		clickAddButton(); 
				
		waitForExtJSAjaxComplete(20);
		
		setReference(warehouseReference,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setCode(warehouseCode,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setDescription(description,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setWarehouseType(type,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setAdres1(adres1,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setAdres2(adres2,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setPhone(phone,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setCity(city,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setZip(zip,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setState(state,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setCountry("Name",country,ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
				
		setTimeZone("Label",timeZone,ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		saveAndClose(ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(20);
		
		this.sortColumnsAscDec("@class", "x-panel-body-noheader", "Reference", "Sort Descending");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getCode(EDIT_WAREHOUSE_WINDOW_HEADER), warehouseCode, "Warehouse code check before edit");
		
		softAssert.assertEquals(getReference(EDIT_WAREHOUSE_WINDOW_HEADER), warehouseReference, "Warehouse reference check before edit");
		
		softAssert.assertEquals(getDefaultState(EDIT_WAREHOUSE_WINDOW_HEADER), false, "Warehouse is default check before edit");
		
		softAssert.assertEquals(getTimeZone(EDIT_WAREHOUSE_WINDOW_HEADER), timeZone, "Warehouse time zone check before edit");
		
		softAssert.assertEquals(getDescription(EDIT_WAREHOUSE_WINDOW_HEADER), description, "Warehouse time zone check before edit");
		
		softAssert.assertEquals(getAdres1(EDIT_WAREHOUSE_WINDOW_HEADER), adres1, "Warehouse adres1 check before edit");
		
		softAssert.assertEquals(getAdres2(EDIT_WAREHOUSE_WINDOW_HEADER), adres2, "Warehouse adres2 check before edit");
		
		softAssert.assertEquals(getPhone(EDIT_WAREHOUSE_WINDOW_HEADER), phone, "Warehouse phone check before edit");
		
		softAssert.assertEquals(getZip(EDIT_WAREHOUSE_WINDOW_HEADER), zip, "Warehouse zip check before edit");
		
		softAssert.assertEquals(getState(EDIT_WAREHOUSE_WINDOW_HEADER), state, "Warehouse state check before edit");
		
		softAssert.assertEquals(getCity(EDIT_WAREHOUSE_WINDOW_HEADER), city, "Warehouse city check before edit");
		
		softAssert.assertEquals(getCountry(EDIT_WAREHOUSE_WINDOW_HEADER), country, "Warehouse country check before edit");
		
		setReference(warehouseReferenceEdited,EDIT_WAREHOUSE_WINDOW_HEADER);
		
		setCode(warehouseCodeEdited,EDIT_WAREHOUSE_WINDOW_HEADER);
		
		setDescription(descriptionEdited,EDIT_WAREHOUSE_WINDOW_HEADER);
		
		setAdres1(adres1Edited,EDIT_WAREHOUSE_WINDOW_HEADER);
		
		setAdres2(adres2Edited, EDIT_WAREHOUSE_WINDOW_HEADER);
		
		setPhone(phoneEdited, EDIT_WAREHOUSE_WINDOW_HEADER);
		
		setCity(cityEdited, EDIT_WAREHOUSE_WINDOW_HEADER);
		
		setZip(zipEdited, EDIT_WAREHOUSE_WINDOW_HEADER);
		
		setState(stateEdited, EDIT_WAREHOUSE_WINDOW_HEADER);
		
		setCountry("Name",countryEdited, EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
	
//c35955: Only one warehouse can be set as default at a time 		
		
		clickDefault(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//span[contains(text(),'"+warningDefaultMessage+"')]"),"warning Default Message is wrong:"+warningDefaultMessage);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setTimeZone("Label",timeZoneEdited, EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		saveAndClose(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
//		McsElement.getElementByXpath(driver, "//div[contains(@class,'grid') and text()='"+warehouseReferenceEdited+"']/../..//div[contains(@class,'row-checker')]").click();
		
		waitForExtJSAjaxComplete(25);
		
		//this.filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", defaultWarehouse, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		this.sortColumnsAscDec("@class", "x-panel-body-noheader", "Reference", "Sort Ascending");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, defaultWarehouse);
		//McsElement.getElementByXpath(driver, "//div[contains(@class,'grid') and text()='"+defaultWarehouse+"']/../..//div[contains(@class,'row-checker')]").click();
		
		waitForExtJSAjaxComplete(25);
		
		
//C35952: A message should be displayed when trying to edit multiple warehouses 		
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'Please select one warehouse to edit')]"),"warning multiple selected Message is wrong");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		this.sortColumnsAscDec("@class", "x-panel-body-noheader", "Reference", "Sort Descending");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, warehouseReferenceEdited);
		
		waitForExtJSAjaxComplete(20);

		clickEditButton();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getCode(EDIT_WAREHOUSE_WINDOW_HEADER), warehouseCodeEdited, "Warehouse code check after edit");
		
		softAssert.assertEquals(getReference(EDIT_WAREHOUSE_WINDOW_HEADER), warehouseReferenceEdited, "Warehouse reference check after edit");
		
//	C35957		Making a warehouse Default
		
		softAssert.assertEquals(getDefaultState(EDIT_WAREHOUSE_WINDOW_HEADER), true, "Warehouse is default check after edit");
		
		softAssert.assertEquals(getTimeZone(EDIT_WAREHOUSE_WINDOW_HEADER), timeZoneEdited, "Warehouse time zone check after edit");
		
		softAssert.assertEquals(getDescription(EDIT_WAREHOUSE_WINDOW_HEADER), descriptionEdited, "Warehouse time zone check after edit");
		
		softAssert.assertEquals(getAdres1(EDIT_WAREHOUSE_WINDOW_HEADER), adres1Edited, "Warehouse adres1 check after edit");
		
		softAssert.assertEquals(getAdres2(EDIT_WAREHOUSE_WINDOW_HEADER), adres2Edited, "Warehouse adres2 check after edit");
		
		softAssert.assertEquals(getPhone(EDIT_WAREHOUSE_WINDOW_HEADER), phoneEdited, "Warehouse phone check after edit");
		
		softAssert.assertEquals(getZip(EDIT_WAREHOUSE_WINDOW_HEADER), zipEdited, "Warehouse zip check after edit");
		
		softAssert.assertEquals(getState(EDIT_WAREHOUSE_WINDOW_HEADER), stateEdited, "Warehouse state check after edit");
		
		softAssert.assertEquals(getCity(EDIT_WAREHOUSE_WINDOW_HEADER), cityEdited, "Warehouse city check after edit");
		
		softAssert.assertEquals(getCountry(EDIT_WAREHOUSE_WINDOW_HEADER), countryEdited, "Warehouse country check after edit");
		
		close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		softAssert.assertAll();
		
		Reporter.log("Warehouse was succesfully created and edited", true);
	}
	/**
	 * Testcase ID : C71570 
	 * Author : Intellias
	 * */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testWarehouseDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C71570: Create/Update/Delete Warehouse </span><br>", true);

		Reporter.log("User creates/edits warehouse <br>", true);
		String type= "Staffed";
		String warehouseReference = "warehouseRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String warehouseCode = "warehouseCode"+getCurrentDate().substring(getCurrentDate().length()-5);
		String description = "description"+getCurrentDate().substring(getCurrentDate().length()-5);
		String adres1 = "adres1"+getCurrentDate().substring(getCurrentDate().length()-5);	
		String adres2 = "adres2"+getCurrentDate().substring(getCurrentDate().length()-5);
		String city = "city"+getCurrentDate().substring(getCurrentDate().length()-5);
		String zip = "123";
		String state = "state";
		String country = "Albania";
		String phone = "1234567";
		String timeZone = "(GMT+01:00) Amsterdam, Berlin, Bern, Rome, Stockholm, Vienna";
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
				
		waitForExtJSAjaxComplete(20);
		
		clickAddButton(); 
				
		waitForExtJSAjaxComplete(20);
		
		setReference(warehouseReference, ADD_WAREHOUSE_WINDOW_HEADER);
		
		setCode(warehouseCode, ADD_WAREHOUSE_WINDOW_HEADER);
		
		setDescription(description, ADD_WAREHOUSE_WINDOW_HEADER);
		
		setWarehouseType(type,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setAdres1(adres1, ADD_WAREHOUSE_WINDOW_HEADER);
		
		setAdres2(adres2, ADD_WAREHOUSE_WINDOW_HEADER);
		
		setPhone(phone, ADD_WAREHOUSE_WINDOW_HEADER);
		
		setCity(city, ADD_WAREHOUSE_WINDOW_HEADER);
		
		setZip(zip, ADD_WAREHOUSE_WINDOW_HEADER);
		
		setState(state, ADD_WAREHOUSE_WINDOW_HEADER);
		
		setCountry("Name",country, ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		setTimeZone("Label",timeZone, ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		saveAndClose(ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");		
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
		
		clickDeleteButton();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, warehouseReference), warehouseReference+" reference after delete is present");
				
		Reporter.log("Warehouse was succesfully deleted", true);
	}
	/**
	 * Testcase ID : C90691 
	 * Author : Intellias
	 * */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void createWarehouse() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C90691: Create a warehouse </span><br>", true);

		Reporter.log("User creates warehouse <br>", true);
		
		String warehouseReference = "warehouseRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String warehouseCode = "warehouseCode"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		String storationCode = "storationCode"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		String storationReference = "storationRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		String storationNumber = "0";
		
		String stockItem = "2preConsRef";
		String type= "Staffed";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("createWarehouse");
		
		
//		String description = "description"+getCurrentDate().substring(getCurrentDate().length()-5);
//		String adres1 = "adres1"+getCurrentDate().substring(getCurrentDate().length()-5);	
//		String adres2 = "adres2"+getCurrentDate().substring(getCurrentDate().length()-5);
//		String city = "city"+getCurrentDate().substring(getCurrentDate().length()-5);
//		String zip = "123";
//		String state = "state";
//		String country = "Albania";
//		String phone = "1234567";
//		String timeZone = "(GMT+01:00) Amsterdam, Berlin, Bern, Rome, Stockholm, Vienna";
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
				
		waitForExtJSAjaxComplete(20);
		
		clickAddButton(); 
				
		waitForExtJSAjaxComplete(20);
		
		setReference(warehouseReference, ADD_WAREHOUSE_WINDOW_HEADER);
		
		setCode(warehouseCode, ADD_WAREHOUSE_WINDOW_HEADER);
		
		setWarehouseType(type,ADD_WAREHOUSE_WINDOW_HEADER);
		
//		setDescription(description, ADD_WAREHOUSE_WINDOW_HEADER);
//		
//		setAdres1(adres1, ADD_WAREHOUSE_WINDOW_HEADER);
//		
//		setAdres2(adres2, ADD_WAREHOUSE_WINDOW_HEADER);
//		
//		setPhone(phone, ADD_WAREHOUSE_WINDOW_HEADER);
//		
//		setCity(city, ADD_WAREHOUSE_WINDOW_HEADER);
//		
//		setZip(zip, ADD_WAREHOUSE_WINDOW_HEADER);
//		
//		setState(state, ADD_WAREHOUSE_WINDOW_HEADER);
//		
//		setCountry("Name",country, ADD_WAREHOUSE_WINDOW_HEADER);
//		
//		waitForExtJSAjaxComplete(25);
//		
//		setTimeZone("Label",timeZone, ADD_WAREHOUSE_WINDOW_HEADER);
//		
//		waitForExtJSAjaxComplete(25);
		
		save();
		
		waitForExtJSAjaxComplete(25);
		
		clickStorageLocationsTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickAddStorageLocation();
		
		setStorageLocation(storationNumber, storationCode, storationReference);
		
		save();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, storationCode+"|"+storationReference, "@class","storage-locations-grid"),"storage location is added");
		
		clickStockItemsTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickAddStockItems();
		
		waitForExtJSAjaxComplete(25);
		
		setStockItem(stockItem);
		
		waitForExtJSAjaxComplete(25);
		
		save();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, stockItem, "@class","stock-items-grid"),"stock item is added");
		
		clickRightsTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickUseSpecificRights();
		
		waitForExtJSAjaxComplete(25);
		
		clickAddAccounts();
		
		waitForExtJSAjaxComplete(25);
		String userName = getUserNameOfLoggedInUser();
		setAccountOrGroup(userName,"account_list");
		
		waitForExtJSAjaxComplete(25);
		
		checkAllRightsFirstRow();
		
		save();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'warehouse-rights-tab')]//div[contains(@class,'x-grid3-check-col-on')]"),"right is added");
		
		boolean isGridRowPresent = (Grid.isRowInGridPresent(driver,userName, "@class","warehouse-rights-tab"))? true: Grid.isRowInGridPresent(driver,this.getUserNameOfLoggedInUser(), "@class","warehouse-rights-tab");
		softAssert.assertTrue(isGridRowPresent,"account was added");
		
		waitForExtJSAjaxComplete(25);
		
		clickAddGroups();
		
		waitForExtJSAjaxComplete(25);
		
		setAccountOrGroup("SLNM_AUTO_GROUP","group");
		
		waitForExtJSAjaxComplete(25);
		
		checkAllRightsFirstRow();
		
		save();

		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "SLNM_AUTO_GROUP", "@class","warehouse-rights-tab"),"group was added");
		
		close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
		
		clickDeleteButton();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
				
		Reporter.log("Warehouse was succesfully created/deleted", true);
	}
	

	/**
	 * Testcase ID		: C90702 
	 * Author			: vpcc
	 * */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testWarehouseEditRefAndUOMOfStockItems() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C90702: Reference and UOM can not be modified in warehouse pan, these can be modified in purchasing product pane </span><br>", true);

		
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("User creates warehouse <br>", true);
		
		clickAddButton();
				
		waitForExtJSAjaxComplete(20);
		
		//****************Variables Initialization 
		String warehouseReference = "C40897WHRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String warehouseCode = "C40897Warehouse"+getCurrentDate().substring(getCurrentDate().length()-5);
		String description = "description"+getCurrentDate().substring(getCurrentDate().length()-5);
		String timeZone = "(GMT+01:00) Amsterdam, Berlin, Bern, Rome, Stockholm, Vienna";
		String type= "Staffed";
		
		setReference(warehouseReference,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setCode(warehouseCode,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setDescription(description,ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		setWarehouseType(type,ADD_WAREHOUSE_WINDOW_HEADER);
		
				
		setTimeZone("Label",timeZone,ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		//Fill Mandatory fields and click on Save & Close
		saveAndClose(ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
		
		Reporter.log("Warehouse was succesfully created <br>", true);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickStockItemsTab();
		
		clickAddStockItems();
		
		Reporter.log("Edit Warehouse and add Stock Items", true);
		
		String productItemRef = "6preProdRef";
		
		setStockItem(productItemRef);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testWarehouseEditRefAndUOMOfStockItems");
		
		boolean bStatus = verifyEditabilityOfRefernceField();
		softAssert.assertFalse(bStatus, "Reference Field is not editable");
		
		bStatus = verifyEditabilityOfUOMField();
		softAssert.assertFalse(bStatus, "Unit of Measure Field is not editable");
		
		saveAndClose(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		PurchasingProductPageObject purchasingProductPageObject = new PurchasingProductPageObject();
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASINGPRODUCTS);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, productItemRef);
		
		waitForExtJSAjaxComplete(20);
		
		purchasingProductPageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		//Get Reference and UOM field values before editing
		
		String preReference = purchasingProductPageObject.getReference();
		
		String preUOM = purchasingProductPageObject.getUnitOfMeasure();
		
		String unitsMeasureRef = "cubic foot";
		String reference = "reference" + getCurrentDate().substring(getCurrentDate().length()-5);
		
		//Set Reference and UOM field values
		purchasingProductPageObject.setReference(reference);
		
		waitForExtJSAjaxComplete(20);
		
		purchasingProductPageObject.setFirstUnitMeasure(unitsMeasureRef); 
		
		//Get Reference and UOM field values after editing
		String postReference = purchasingProductPageObject.getReference();
		
		String postUOM = purchasingProductPageObject.getUnitOfMeasure();
		
		//Verify Reference and UOM field values edited
		softAssert.assertNotEquals(preReference, postReference, "Reference field is Editable from Purchasing Products screen");
		softAssert.assertNotEquals(preUOM, postUOM, "Units of Measure field is Editable from Purchasing Products screen");
		
		purchasingProductPageObject.close();
		
		purchasingProductPageObject.clickOnDialogButton("Yes");
		//Code to delete the added ware house
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Delete the added Warehouse.", true);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
		
		clickDeleteButton();
		
		softAssert.assertAll();
		
		Reporter.log("Stock Items UOM and Reference values can't be updated from Stock Items tab of Warehouse. Items are editable in Product Items.", true);
	
	}
	
	/**
	 * Testcase ID		: C90701 
	 * Author			: ssu
	 * */
	
    @Test(retryAnalyzer = RetryAnalyzer.class)
	public void testWarehouseEditAddRemoveStockItems() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C90701: Adding/Removing stock items to/from warehouse </span><br>", true);
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("User can add/remove stockItems to warehouse <br>", true);		
					
		clickAddButton(); 
		
		waitForExtJSAjaxComplete(10);

		//****************Variables Initialization 
		String warehouseReference = "C40896WHRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String warehouseCode = "C40896WHCod"+getCurrentDate().substring(getCurrentDate().length()-5);
		String timeZone = "(GMT+01:00) Amsterdam, Berlin, Bern, Rome, Stockholm, Vienna";
		String type= "Staffed";
				
		setReference(warehouseReference,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setCode(warehouseCode,ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		setWarehouseType(type,ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);

		setTimeZone("Label",timeZone,ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		//Fill Mandatory fields and click on Save & Close		
		saveAndClose(ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		Reporter.log("Warehouse was succesfully created <br>", true);		

		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickStockItemsTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickAddStockItems(); 
		
		String stockItem1 = "2preConsRef";

		setStockItem(stockItem1);
		
		clickAddStockItems(); 
		
		String stockItem2 = "6preProdRef";
		
		setStockItem(stockItem2);
		
		//Selecting Stock Items and click on Save
		checkAllStockItems();
		
		waitForExtJSAjaxComplete(5);
		
		save();
		
		waitForExtJSAjaxComplete(5);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testStockManagementAddRemove");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, stockItem1, "@class","stock-items-grid"),"stock item1 is added");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, stockItem2, "@class","stock-items-grid"),"stock item2 is added");
		
		waitForExtJSAjaxComplete(10);
		
		//Selecting Stock Items and Delete
		checkAllStockItems();
		
		waitForExtJSAjaxComplete(10);
		
		clickDeleteStockItem();
		
		waitForExtJSAjaxComplete(10);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, stockItem1), stockItem1+" stock item1 after delete is present");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, stockItem2), stockItem2+" stock item2 after delete is present");
		
		waitForExtJSAjaxComplete(10);
		
		close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		//Code to delete the added ware house
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
		
		clickDeleteButton();
				
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, warehouseReference), warehouseReference+" reference after delete is present");
			
		softAssert.assertAll();
		
		Reporter.log("Stock Items are successfully added and deleted from Warehouse.", true);
	}

    /**
	 * Testcase ID		: C90700 
	 * Author			: ssa
	 * */

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testAddRemoveStorageLocations() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Add/Remove StaorageLocations: C90700 </span><br>",
				true);
	

		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("User creates warehouse <br>", true);	
		
		waitForExtJSAjaxComplete(20);
					
		clickAddButton(); 
		
		waitForExtJSAjaxComplete(10);

		//Variables Initialization 
		String warehouseReference = "C40895WHRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String warehouseCode = "C40895WHCod"+getCurrentDate().substring(getCurrentDate().length()-5);
		String timeZone = "(GMT+01:00) Amsterdam, Berlin, Bern, Rome, Stockholm, Vienna";
		String type= "Staffed";
		
		setReference(warehouseReference,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setCode(warehouseCode,ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);

		setWarehouseType(type,ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		setTimeZone("Label",timeZone,ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		//Fill Mandatory fields and click on Save & Close		
		saveAndClose(ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		Reporter.log("Warehouse was succesfully created <br>", true);		

		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		//Selecting Storage Locations Tab
		clickStorageLocationsTab();

		waitForExtJSAjaxComplete(10);
		
		//Variables Initialization for Storage Locations
		String storationCode1 = "storationCode"+ getCurrentDate().substring(getCurrentDate().length() - 5);
		String storationReference1 = "storationRef"+ getCurrentDate().substring(getCurrentDate().length() - 5);
		String storationNumber = "0";
		String storationCode2 = "sCode"+ getCurrentDate().substring(getCurrentDate().length() - 5);
		String storationReference2 = "sRef"+ getCurrentDate().substring(getCurrentDate().length() - 5);
		
		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testAddRemoveStorageLocations");

		//Adding multiple Storage Locations and  Save it
		clickAddStorageLocation();
		
		waitForExtJSAjaxComplete(10);
		
		setStorageLocation(storationNumber, storationCode1, storationReference1);

		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, storationCode1 + "|"+ storationReference1, "@class","storage-locations-grid"), "First Storage Location is added");
		
		waitForExtJSAjaxComplete(10);
		
		clickAddStorageLocation();
		
		waitForExtJSAjaxComplete(10);
		
		setStorageLocation(storationNumber, storationCode2, storationReference2);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, storationCode2 + "|"+ storationReference2, "@class","storage-locations-grid"), "Second Storage Location is added");

		save();
		
		waitForExtJSAjaxComplete(5);
		
		//Deleting the added storage locations and close it
		Grid.checkRowInGriByTextValue(driver, storationReference1);

		clickDeleteStorageLocation();

		waitForExtJSAjaxComplete(10);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, storationReference1),storationReference1+ "Location reference1 after delete is present");

		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, storationReference2);

		clickDeleteStorageLocation();

		waitForExtJSAjaxComplete(10);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, storationReference2),storationReference2+ "Location reference2 after delete is present");

		waitForExtJSAjaxComplete(10);
		
		close(EDIT_WAREHOUSE_WINDOW_HEADER);

		waitForExtJSAjaxComplete(10);
		
		Reporter.log("Delete the added Warehouse.", true);

		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
		
		waitForExtJSAjaxComplete(5);

		clickDeleteButton();

		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, warehouseReference), warehouseReference+" reference after delete is present");

		softAssert.assertAll();

		Reporter.log("Storage locations are successfully added and deleted from Warehouse.", true);
	}

	
	/**
	 * Testcase ID : C90697 
	 * Author : ssa
	 * */

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testDefaultWarehouse() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
						+ "Test: Default warehouse is automatically selected for transactions: C90697 </span><br>",true);

		// Variables Initialization
		String warehouseReference = "Central Warehouse";
		String transactionType = "Goods Issue";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testDefaultWarehouse");

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_WAREHOUSES);

		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		waitForExtJSAjaxComplete(20);

		clickEditButton();

		waitForExtJSAjaxComplete(5);

		if (!getDefaultState(EDIT_WAREHOUSE_WINDOW_HEADER)) {

			clickDefault(EDIT_WAREHOUSE_WINDOW_HEADER);

			waitForExtJSAjaxComplete(10);

			clickOnDialogButton("Yes");

			waitForExtJSAjaxComplete(10);

			this.save();

			waitForExtJSAjaxComplete(10);

		}

		close(EDIT_WAREHOUSE_WINDOW_HEADER);

		waitForExtJSAjaxComplete(20);

		TransactionsPageObject transactionsPageObject = new TransactionsPageObject();

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_TRANSACTIONS);

		waitForExtJSAjaxComplete(20);

		// Click on Choose button and select Transaction type
		transactionsPageObject.selectTransactionType(transactionType);

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(transactionsPageObject.getDefaultWarehouseValue(GOODS_ISSUE_TRANSACTION_WINDOW_HEADER),warehouseReference,
						"warehouse value is automatically displayed in transaction which has been selected as default");

		// Close Transaction window
		transactionsPageObject.close(transactionType);

		

		waitForExtJSAjaxComplete(20);

		Reporter.log("Default warehouse is automatically selected for transactions",true);

	}
	

	/**
	 * Testcase ID : C90694 
	 * Author : vpcc
	 * */
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testDeleteWareHouseWithoutRightsVerifyErrMsg() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "A message must be displayed when trying to delete a warehouse for which you donot have rights : C90694 </span><br>", true);
		
		//Variables Initialization 
		String warehouseReference = "C35953WHRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String warehouseCode = "C35953WHCod"+getCurrentDate().substring(getCurrentDate().length()-5);
		
        SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testDeleteWareHouseWithoutRights");
		
		String errMsg = "You do not have the right to Delete this Warehouse. Please contact your Administrator.".toLowerCase();
		String userAccountName ="auto test right Last auto test right First";
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton(); 
		
		waitForExtJSAjaxComplete(20);
		
		addWareHouse(warehouseReference, warehouseCode);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		waitForExtJSAjaxComplete(20);

		clickEditButton();
		
		waitForExtJSAjaxComplete(10);
		
		//Click on Rights Tab
		clickRightsTab();
		
		clickUseSpecificRights();
		
		waitForExtJSAjaxComplete(10);
		
		//Adding accounts
		clickAddAccounts();
		
		setAccountOrGroup(userAccountName,"account_list");
		
        waitForExtJSAjaxComplete(10);
        
        //click on AllRights check box and verify
		checkAllRightsFirstRow();
		
		waitForExtJSAjaxComplete(10);
		
		saveAndClose(EDIT_WAREHOUSE_WINDOW_HEADER);
			
		waitForExtJSAjaxComplete(20);
		
		//try to delete the added ware house
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
				
		clickDeleteButton();
						
		waitForExtJSAjaxComplete(25);
		
		String multiDelErrMsg = getWarningDialogTextMsg();
		
		softAssert.assertTrue(multiDelErrMsg.toLowerCase().contains(errMsg), "Warehouse can't be deleted error message is otbained");
		
		softAssert.assertAll();
		
		Reporter.log("User can't delete a warehouse for which he doesn't have rights.A message appear like: You do not have the right to Delete this Warehouse. Please contact your Administrator will be otbained", true);
			
		
		}
	
	
	/**
	 * Testcase ID : C90699 
	 * Author : ssa
	 * */
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testAddAccountsandRights() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Add accounts and rights on warehouse: C90699 </span><br>", true);
		
		//Variables Initialization 
		String warehouseReference = "C35958WHRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String warehouseCode = "C35958WHCod"+getCurrentDate().substring(getCurrentDate().length()-5);
		String type= "Staffed";
		
        SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testAddAccountsandRights");
		
        expandAdministration();
		
		waitForExtJSAjaxComplete(20);
       
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
	
		waitForExtJSAjaxComplete(20);
		
		//clickOverviewButton();
		waitForExtJSAjaxComplete(20);
		
		clickAddButton(); 
		
		waitForExtJSAjaxComplete(20);
		
		//Adding warehouse
        setReference(warehouseReference,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setCode(warehouseCode,ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		setWarehouseType(type,ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		//Fill Mandatory fields and click on Save & Close		
		saveAndClose(ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		waitForExtJSAjaxComplete(20);

		clickEditButton();
		
		waitForExtJSAjaxComplete(10);
		
		//Click on Rights Tab
		clickRightsTab();
		
		clickUseSpecificRights();
		
		waitForExtJSAjaxComplete(20);
		
		//Adding accounts
		clickAddAccounts();
		String userAccountName = getUserNameOfLoggedInUser();//"SELENIUM AQA";
		setAccountOrGroup(userAccountName,"account_list");
		
        waitForExtJSAjaxComplete(10);
        
    	softAssert.assertTrue(isUserAddedToUserRightsGrid(userAccountName),userAccountName+"user is added to warehouse");
    	
    	waitForExtJSAjaxComplete(10);
    	
        //click on AllRights check box and verify
		checkAllRightsFirstRow();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(isAllRightsBoxCheckedForFirstRow(), true, "Rights are given to selected user accounts");
		
		saveAndClose(EDIT_WAREHOUSE_WINDOW_HEADER);
			
		waitForExtJSAjaxComplete(20);
		
		//Code to delete the added ware house
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
				
		clickDeleteButton();
						
		waitForExtJSAjaxComplete(10);
				
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, warehouseReference), warehouseReference+" reference after delete is present");
				
		Reporter.log("Rights are given to selected user accounts for warehouse", true);
			
	}
	
	/**
	 * Testcase ID : C90695 
	 * Author : ssa
	 * */
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testUnEditableFieldsInWarehouse() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Fields should be uneditable when trying to modify a warehouse for which you do not have rights: C90695 </span><br>", true);
		
		//Variables Initialization 
		String warehouseReference = "c35954WHRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String warehouseCode = "c35954WHCod"+getCurrentDate().substring(getCurrentDate().length()-5);
		String description = "description"+getCurrentDate().substring(getCurrentDate().length()-5);
		String adres1 = "adres1"+getCurrentDate().substring(getCurrentDate().length()-5);	
		String adres2 = "adres2"+getCurrentDate().substring(getCurrentDate().length()-5);
		String city = "city"+getCurrentDate().substring(getCurrentDate().length()-5);
		String zip = "123";
		String state = "state";
		String country = "Albania";
		String phone = "1234567";
		String timeZone = "(GMT+01:00) Amsterdam, Berlin, Bern, Rome, Stockholm, Vienna";
		String type= "Staffed";
		
        SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testUnEditableFieldsInWarehouse");
		
		
		waitForExtJSAjaxComplete(20);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		//clickOverviewButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton(); 
		
		waitForExtJSAjaxComplete(20);
		
		//Adding warehouse
        setReference(warehouseReference,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setCode(warehouseCode,ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		setWarehouseType(type,ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
        setDescription(description,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setAdres1(adres1,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setAdres2(adres2,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setPhone(phone,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setCity(city,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setZip(zip,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setState(state,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setCountry("Name",country,ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
				
		setTimeZone("Label",timeZone,ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		//Fill Mandatory fields and click on Save & Close		
		saveAndClose(ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForMaskDisappear();
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");
		
		waitForExtJSAjaxComplete(15);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		waitForExtJSAjaxComplete(20);

		clickEditButton();
		
		waitForExtJSAjaxComplete(10);
		
		//Click on Rights Tab
		clickRightsTab();
		
		clickUseSpecificRights();
		
		waitForExtJSAjaxComplete(10);
		
		//Adding accounts
		clickAddAccounts();
		
		String userAccountName =  "auto test right Last auto test right First";
		
		setAccountOrGroup(userAccountName,"account_list");
		
        waitForExtJSAjaxComplete(10);
        
    	softAssert.assertTrue(isUserAddedToUserRightsGrid(userAccountName),userAccountName+"user is added to warehouse");
    	
    	waitForExtJSAjaxComplete(10);
    	
        //click on AllRights check box and verify
		checkAllRightsFirstRow();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(isAllRightsBoxCheckedForFirstRow(), true, "Rights are given to selected user accounts");
		
		saveAndClose(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
		
		waitForExtJSAjaxComplete(10);
		
        clickEditButton();
		
		waitForExtJSAjaxComplete(10);
		
		//Verify all fields of warehouse is uneditable
		softAssert.assertTrue(isWarehouseCodeUnEditable(),warehouseReference+"Unable to edit Warehouse Code");
		softAssert.assertTrue(isWarehouseRefUnEditable(),warehouseReference+"unable to edit Warehouse Reference");
		softAssert.assertTrue(isTimeZoneUnEditable(),timeZone+"Unable to edit timeZone ");
		softAssert.assertTrue(isAdres1UnEditable(),adres1+"unable to edit adres1");
		softAssert.assertTrue(isAdres2UnEditable(),adres2+"Unable to edit adres2");
		softAssert.assertTrue(isStateUnEditable(),state+"unable to edit state");
		softAssert.assertTrue(isCountryUnEditable(),country+"Unable to edit country");
		softAssert.assertTrue(isZipUnEditable(),zip+"unable to edit zip");
		softAssert.assertTrue(isPhoneUnEditable(),phone+"Unable to edit phone");
		softAssert.assertTrue(isDescriptionUnEditable(),description+"unable to edit description");
		softAssert.assertTrue(isCityUnEditable(),city+"Unable to edit city");
		
		waitForExtJSAjaxComplete(10);
		
		close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		softAssert.assertAll();
	
		Reporter.log("Warehouse window is opened which do not have modify rights. All fields are read only.", true);
					
	}
	
	/**
	 * Testcase ID		: C90703 
	 * Author			: ssu
	 * */
	
   @Test(retryAnalyzer = RetryAnalyzer.class)
	public void testReservedStockUnEditableInWarehousePan() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Reserved stock can not be modified in warehouse pan, it can be modified by planned/canceled goods issue transaction only: C90703 </span><br>", true);
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Reserved stock can not be modified in warehouse pan, it can be modified by planned/canceled goods issue transaction only <br>", true);		
					
		//Variable Initialization
		String warehouseReference = "GoodsIssueWH";
		String availableStockColName = "Available Stock";
		String reservedStockColName = "Reserved Stock";
		String blockedStockColName = "Blocked Stock";
		String totalStockColName = "Total Stock";
		String prodReference = "2preConsRef";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		String lineQuantity = "10";
		String GOODS_ISSUE_TRANSACTION_WINDOW_HEADER = "Goods Issue";
		String transactionReference = "Ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		int intLineQuantity = Integer.parseInt(lineQuantity);
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(10);
		
		clickStockItemsTab();
		
		waitForExtJSAjaxComplete(10);
		
		int availableStockValue = Integer.parseInt(getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),availableStockColName));
		int reservedStockValue = Integer.parseInt(getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),reservedStockColName));
		int totalStockValue = Integer.parseInt(getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),totalStockColName));
		int blockedStockValue = Integer.parseInt(getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),blockedStockColName));
		
		Reporter.log(prodReference+" available stock quantity is "+availableStockValue, true);
		Reporter.log(prodReference+" reserved stock quantity is "+reservedStockValue, true);
		Reporter.log(prodReference+" blocked stock quantity is "+blockedStockValue, true);
		Reporter.log(prodReference+" total stock quantity is "+totalStockValue, true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReservedStockUnEditableInWarehousePan");
		
		boolean bStatus = verifyEditabilityOfReservedStock("Reserved Stock");
		softAssert.assertFalse(bStatus, "Reserved Stock Field is not editable");
		
		waitForExtJSAjaxComplete(5);
		
		close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(10);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		TransactionsPageObject transactionPageObject = new TransactionsPageObject();
		
		transactionPageObject.selectWarehouse(warehouseReference);

		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, prodReference);
		
		waitForExtJSAjaxComplete(20);
		
		transactionPageObject.selectTransactionType("Goods Issue");
		
		waitForExtJSAjaxComplete(20);
		
		transactionPageObject.setStatus("Planned", GOODS_ISSUE_TRANSACTION_WINDOW_HEADER );
		
		transactionPageObject.setReference( transactionReference, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		transactionPageObject.setTransactionLineQuantity("1", lineQuantity);
		
		waitForExtJSAjaxComplete(20);
		
		transactionPageObject.saveAndClose();
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(10);

		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
				
		clickEditButton();
				
		waitForExtJSAjaxComplete(20);
				
		clickStockItemsTab();
				
		waitForExtJSAjaxComplete(10);
		
		int availableStockValuePostTxn1 = Integer.parseInt(getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),availableStockColName ));
		int reservedStockValuePostTxn1 = Integer.parseInt(getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),reservedStockColName));
		int totalStockValuePostTxn1 = Integer.parseInt(getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),totalStockColName));
		int blockStockValuePostTxn1 = Integer.parseInt(getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),blockedStockColName));
				
		//*****************************Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn1==(availableStockValue-(intLineQuantity))), prodReference+" available stock quantity is decreased by "+lineQuantity+"units");
		softAssert.assertTrue((reservedStockValuePostTxn1 ==(reservedStockValue+(intLineQuantity))), prodReference+" reserved stock quantity is increased by "+lineQuantity+"units");
		softAssert.assertTrue((totalStockValuePostTxn1==totalStockValue), prodReference+" Total stock quantity is unchanged");
		softAssert.assertTrue((blockStockValuePostTxn1==blockedStockValue), prodReference+" Block stock quantity is unchanged");
		
		close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		waitAndClick(XPATH_STOCK_ITEMS);
		
		waitForExtJSAjaxComplete(20);
		
		transactionPageObject.selectWarehouse(warehouseReference);

		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, prodReference);
		
		transactionPageObject.selectTransactionType("Goods Issue");
		
		waitForExtJSAjaxComplete(20);
		
		//TODO: Canceled status is not automatically populated, so setting planned first and canceling.
		transactionPageObject.setStatus("Planned", GOODS_ISSUE_TRANSACTION_WINDOW_HEADER );
		
		transactionPageObject.setReference( transactionReference, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		transactionPageObject.setTransactionLineQuantity("1", lineQuantity);
		
		transactionPageObject.save();
		
		waitForExtJSAjaxComplete(10);
		
		transactionPageObject.setStatus("Canceled", GOODS_ISSUE_TRANSACTION_WINDOW_HEADER );
		
		transactionPageObject.setReference( transactionReference, GOODS_ISSUE_TRANSACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		transactionPageObject.saveAndClose();
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(10);

		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
				
		clickEditButton();
				
		waitForExtJSAjaxComplete(20);
				
		clickStockItemsTab();
				
		waitForExtJSAjaxComplete(10);
		
		int availableStockValuePostTxn2 = Integer.parseInt(getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),availableStockColName ));
		int reservedStockValuePostTxn2 = Integer.parseInt(getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),reservedStockColName));
		int totalStockValuePostTxn2 = Integer.parseInt(getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),totalStockColName));
		int blockStockValuePostTxn2 = Integer.parseInt(getStockItemsCellValue(getProductRowNumberInWareHouse(prodReference),blockedStockColName));
				
		//*****************************Assertions***********************
		softAssert.assertTrue((availableStockValuePostTxn2==(availableStockValue-(intLineQuantity))), prodReference+" available stock quantity is increased by "+lineQuantity+"units");
		softAssert.assertTrue((reservedStockValuePostTxn2 ==(reservedStockValue+(intLineQuantity))), prodReference+" reserved stock quantity is decreased by "+lineQuantity+"units");
		softAssert.assertTrue((totalStockValuePostTxn2==totalStockValue), prodReference+" Total stock quantity is unchanged");
		softAssert.assertTrue((blockStockValuePostTxn2==blockedStockValue), prodReference+" Block stock quantity is unchanged");
		
		waitForExtJSAjaxComplete(10);
		
		close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("Reserved stock can not be modified in warehouse pan, it can be modified by planned/canceled goods issue transaction only <br>", true);
    }
   
   /**
	 * Testcase ID		: C90728 
	 * Author			: ssa
	 * */
	
 @Test(retryAnalyzer = RetryAnalyzer.class)
	public void testResetButtonInWarehouse() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: It is possible to delete unsaved change and reset all tabs of warehouse: C90728 </span><br>", true);
		
		String warehouseReference = "TestNegativeValueWH";
		String description= "ResetReference"; 
		String warningMsg="All unsaved changes will be lost, all tabs will be reset! Are you sure you want to continue?";
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testResetButtonInWarehouse");
		
			
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
		
		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
	
		wareHousePageObject.clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		boolean status= isResetButtonDisabled(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		softAssert.assertTrue(status, "reset is disabled");
		
		waitForExtJSAjaxComplete(20);
		
		setDescription(description,EDIT_WAREHOUSE_WINDOW_HEADER );
		
		waitForExtJSAjaxComplete(20);
		
		clickResetButton();
		
		waitForExtJSAjaxComplete(20);
		
       status= verifyWarningDialogTextMessage(warningMsg);
		
		softAssert.assertTrue(status,"warning message is displayed for Reset button");
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("No");
		
		waitForExtJSAjaxComplete(20);
		
		String text=getDescription(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		softAssert.assertEquals(text, description,"Nothing is happened and action is cancled. Change which you have been done is still there.");
		
		waitForExtJSAjaxComplete(20);
		
		clickResetButton();
		
		waitForExtJSAjaxComplete(20);
		
       status= verifyWarningDialogTextMessage(warningMsg);
		
		softAssert.assertTrue(status,"warning message is displayed for Reset button");
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		text=getDescription(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		softAssert.assertNotEquals(text, description,"Nothing is happened and action is canceled. Change which we have been done is still there.");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("Unsaved changes are deleted successfully using reset button in Warehouse<br>", true);
  
 }
 
 	/**
	 * Test : C124295,C124293
	 * Author :ssa
	 */
	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testVerifyDefaultWarehouse() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C124295:Warehouse field can contains the warehouse that is same for all products added in the Quick Goods return form<br>"
				+"C124293:Warehouse field can contains the warehouse that is marked as default",true);
		String testWorkOrder = "WOForStockInfo";
		String productReference2 = "4preConsRef";
		String productReference1 = "2preConsRef";

		String warehouseRef="StaffedGoodsReturnWH";
		
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testVerifyDefaultWarehouse");
		
		WorkOrderPageObject workOrderPO=new WorkOrderPageObject();
		
		expandAdministration();
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseRef, "Reference");
		
		waitForExtJSAjaxComplete(20);
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseRef);

		waitForExtJSAjaxComplete(15);
		
		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
		
		wareHousePageObject.clickEditButton(); 
				
		waitForExtJSAjaxComplete(10);

		waitForExtJSAjaxComplete(5);

		if (!wareHousePageObject.getDefaultState(EDIT_WAREHOUSE_WINDOW_HEADER)) {

			wareHousePageObject.clickDefault(EDIT_WAREHOUSE_WINDOW_HEADER);

			waitForExtJSAjaxComplete(10);

			clickOnDialogButton("Yes");

			waitForExtJSAjaxComplete(10);

			wareHousePageObject.save();

			waitForExtJSAjaxComplete(10);

		}

		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);

		waitForExtJSAjaxComplete(20);
		
		driver.navigate().refresh();
		
		waitForExtJSAjaxComplete(20);
		
		click(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		workOrderPO.setDetailsTabCollapsedMode();
		
		Thread.sleep(1500);
		
		waitForExtJSAjaxComplete(20);
		
		workOrderPO.filterGridWithoutUsingMcsElementModifiedForChrome("@realid", "mogrid", testWorkOrder,"Reference");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueExactMatch(driver, testWorkOrder);
		
		waitForExtJSAjaxComplete(20);
		
		workOrderPO.clickTimeMaterialTab();
		
		waitForExtJSAjaxComplete(20);
		
		workOrderPO.clickTimeMaterialBillMaterialTab();
		
		waitForExtJSAjaxComplete(20);
		
		workOrderPO.addBOMLine(productReference1);
		
		waitForExtJSAjaxComplete(20);
		
		workOrderPO.clickOnSaveInBOM();
		
		waitForExtJSAjaxComplete(10);
		
		workOrderPO.checkSelectAllcheckBoxButtonInBOM();
		
		waitForExtJSAjaxComplete(10);
		
		workOrderPO.unCheckSelectAllcheckBoxButtonInBOM();
		
		String defaultWHInBOM=workOrderPO.getWarehouseInBOM("Needed","Warehouse",productReference1);
		
		softAssert.assertEquals(defaultWHInBOM,warehouseRef,"Default warehouse is displayed");
		
		waitForExtJSAjaxComplete(20);
		
		workOrderPO.selectMutipleBOMLines("Consumed",productReference1);
		
		workOrderPO.selectMutipleBOMLines("Consumed",productReference2);
		
		waitForExtJSAjaxComplete(20);
		
		workOrderPO.clickOnQuickGoodsReturnButtonInBOM();
		
		waitForExtJSAjaxComplete(20);
		
		String defaultWHInQGR=workOrderPO.getWarehouseInQGR();
		
		softAssert.assertEquals(defaultWHInQGR,warehouseRef,"Default warehouse is displayed");
		
		softAssert.assertAll();

		Reporter.log("Verified Warehouse field can contains the warehouse that is same for all products added in the Quick Goods return form", true);
		
	}
   
   
}
	
	

	




