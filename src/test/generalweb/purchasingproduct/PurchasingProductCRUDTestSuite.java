package test.generalweb.purchasingproduct;



//import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.generalweb.transactions.TransactionsPageObject;
import test.generalweb.warehouse.AddWarehousePageObject;




public class PurchasingProductCRUDTestSuite extends
		PurchasingProductPageObject {

	/**
	 * Testcase ID		: c20595
	 * Author			: Intellias
	 * */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testPurchasingProductCreateDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create Purchasing Product: c20595</span><br>", true);

		Reporter.log("User creates purchasing product <br>", true);
		
		
		String defaultExpence = "10";
		String defaultRevenue = "11";
		String currency = "EUR";
		String formula = "Round";
		String precision = "3";
		String unitsMeasureRef = "cubic inch";
		String manufacturerPart = "123";
		String defaultLocation = "slnmEnrgBuilding2"; 
		String reference = "reference" + getCurrentDate().substring(getCurrentDate().length()-5);
		String code = "code" + getCurrentDate().substring(getCurrentDate().length()-5);
		String form = "Bulk";
		String revenueTaxCode = "MCS000";
		String extMatNumbRef = "1preExtMatRef";
		String costCategoryRef = "1preCostCtRef";
		String productDesignation = "Purchasing";
		String productCategoryRef = "1preProdCatRef";
		String productGroupsRef = "1preProdRef";

		
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASINGPRODUCTS);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton();

		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		setReference(reference);
		
		setFirstCostCategory(costCategoryRef);
		
		setFirstProductCategory(productCategoryRef);
		
		setProductDesignation(productDesignation);
		
		setProductCode(code);
		
		setForm(form);
		
		setRevenueTaxCode(revenueTaxCode);
		
		setExtMatNumber(extMatNumbRef);
		
		setDefaultLocation(defaultLocation);
		
		setManufPartNr(manufacturerPart);
		
		setDefaultExpense(defaultExpence);
		
		setDefaultRevenue(defaultRevenue);
		
		setCurrency(currency);
		
		setFirstUnitMeasure(unitsMeasureRef); 
		
		setFormula(formula);
		
		setPrecision(precision);
		
		selectProductGroup(productGroupsRef);
		
		saveProduct();
		
		close();
		
		waitForExtJSAjaxComplete(25);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", reference, "Reference");
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		clickDeleteButton();
		
		waitForExtJSAjaxComplete(25);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnDialogButton("OK");
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, "Purchasing product is present in grid after deletion"));
		
		Reporter.log("Product was succesfully created and deleted", true);
	}
	
	/**
	 * Testcase ID		: C90735 
	 * Author			: vpcc
	 * */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testPurchasingProductAddAndDeleteWareHouseInStockTab() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Adding/removing warehouses to/from purchasing product : C90735</span><br>", true);

		Reporter.log("User creates Warehouse <br>", true);

		//Add ware house
		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("User creates warehouse <br>", true);
		
		clickAddButton();
				
		waitForExtJSAjaxComplete(20);
		
		//****************Ware house variables Initialization **
		String warehouseReference = "C35972WHRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String warehouseCode = "C35972Warehouse"+getCurrentDate().substring(getCurrentDate().length()-5);
		String timeZone = "(GMT+01:00) Amsterdam, Berlin, Bern, Rome, Stockholm, Vienna";
		String type= "Staffed";
		wareHousePageObject.addWareHouse(warehouseReference,warehouseCode,type,timeZone);

		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", warehouseReference, "Reference");
		
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
		
		Reporter.log("Warehouse was succesfully created <br>", true);
		
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASINGPRODUCTS);
		
		waitForExtJSAjaxComplete(20);
		
		//****************Stock variables Initialization **
		String reference ="6preProdRef";// "reference" + getCurrentDate().substring(getCurrentDate().length()-5);
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testPurchasingProductAddAndDeleteWareHouseInStockTab");
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickStockTab();
		
		//**********Add warehouse in Stock tab***********
		addWareHouse(warehouseReference);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isWareHouseAddedInStockTab(warehouseReference), warehouseReference+" WareHouse is added in Stock Tab");
		
		//**********Delete Added warehouse from Stock tab***********
 		selectAddedWareHouse(warehouseReference);
		
		clickDeleteButtonInStockTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isWareHouseDeletedInStockTab(warehouseReference), warehouseReference+" WareHouse isn't present in Stock Tab");
		
		close();
		
		waitForExtJSAjaxComplete(25);
		
		clickOnDialogButton("Yes");
		
		//delete the added ware house
		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_WAREHOUSES);

		waitForExtJSAjaxComplete(20);

		Reporter.log("Deleting the added Warehouse.", true);

		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		wareHousePageObject.clickDeleteButton();

		waitForExtJSAjaxComplete(25);
		
		clickOnDialogButton("OK");
		
		Reporter.log("Deleted the added Warehouse.", true);
	
		softAssert.assertAll();
		
		Reporter.log("Warehouse was succesfully added and deleted from Purchasing Products", true);
	}
    
	/**
	 * Testcase ID : C90732 
	 * Author : ssa
	 * */

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testAddDescToPurchasingProduct() throws Exception {

		Reporter.log(
				"<span style='font-weight:bold;color:#000033'> "
						+ "Test: Adding Description of Purchasing Product.: C90732</span><br>",true);

		Reporter.log("User adding Description <br>", true);

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_PURCHASINGPRODUCTS);

		waitForExtJSAjaxComplete(20);

		// Variables Initialization
		String productRef = "2preConsRef";
		String descReference = "Entering text into the text area";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testAddDescToPurchasingProduct");

		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValueAndClick(driver, productRef);

		waitForExtJSAjaxComplete(10);

		// Click Edit button
		clickEditButton();

		waitForExtJSAjaxComplete(10);

		waitForExtJSAjaxComplete(20);
		
		// Click on Description Tab
		clickDescriptionTab();

		waitForExtJSAjaxComplete(20);

		// Add Description and Save it
		addDescription(descReference);

		waitForExtJSAjaxComplete(10);

		saveProduct();

		softAssert.assertEquals(getDescription(), descReference,"Description is added");

		waitForExtJSAjaxComplete(10);

		// Clear the Description
		clearDescription();

		waitForExtJSAjaxComplete(10);

		softAssert.assertEquals(getDescription(), "", "Description is cleared");

		waitForExtJSAjaxComplete(20);

		saveProduct();

		close();

		Reporter.log("Description was succesfully added and cleared", true);

	}

	/**
	 * Testcase ID		: C90734 
	 * Author			: ssu
	 * */
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testPurchasingProductAddRemoveSuppliers() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Adding/Removing suppliers to/from Purchasing Product: C90734 </span><br>", true);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASINGPRODUCTS);
		
		waitForExtJSAjaxComplete(20);
	
		clickAddButton();

		waitForExtJSAjaxComplete(10);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(10);
		
		////////////////Variables Initialization////////////////////////
		String reference = "C35971ref" + getCurrentDate().substring(getCurrentDate().length()-5);
		String code = "C35971cod" + getCurrentDate().substring(getCurrentDate().length()-5);
		String costCategoryRef = "1preCostCtRef";
		String productCategoryRef = "1preProdCatRef";
		String revenueTaxCode = "MCS000";
		String defaultExpence = "10";
		String defaultRevenue = "11";
		String currency = "EUR";
		String unitsMeasureRef = "cubic inch";
		String precision = "3";
		String productGroupsRef = "1preProdRef";
		
		setReference(reference);
		
		setFirstCostCategory(costCategoryRef);
		
		setFirstProductCategory(productCategoryRef);
		
		setProductCode(code);
		
		setRevenueTaxCode(revenueTaxCode);
		
		setDefaultExpense(defaultExpence);
		
		setDefaultRevenue(defaultRevenue);
		
		setCurrency(currency);
		
		setFirstUnitMeasure(unitsMeasureRef); 
		
		setPrecision(precision);
		
		selectProductGroup(productGroupsRef);
		
		waitForExtJSAjaxComplete(10);
		
		//Fill Mandatory fields and save Product
		saveProduct();
		
		waitForExtJSAjaxComplete(10);
		
		close();
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", reference, "Reference");
		
		waitForExtJSAjaxComplete(20);
			
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(10);
		
		//Click Suppliers Tab and Add a Preferred Supplier
		clickSuppliersTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickAddButtonInSuppliersTab();
		
		waitForExtJSAjaxComplete(10);
		
		String supplier1 = "My Company";
		addSupplier(supplier1);
		
		waitForExtJSAjaxComplete(10);
		
		//Click on Supplier and layout will be opened in the bottom section of the page 
		openSuppliersInfoLayOut(supplier1);
		
		waitForExtJSAjaxComplete(10);
		
		//Click Financial Keys tab to add CostCenter and GL Account
		clickFinancialKeysTabInSuppliers();
		
		String costCenterRef = "myMCS Default Cost Center";
		setFirstCostCenter(costCenterRef);
		
		waitForExtJSAjaxComplete(10);
		
		String costCategoryRef1 = "1preCostCtRef";
		setFirstCostCategoryInSuppliersTab(costCategoryRef1);
				
		waitForExtJSAjaxComplete(10);
		
		String glAccount = "myMCS Default GL Account";
		setFirstGLAccount(glAccount);
		
		waitForExtJSAjaxComplete(10);
		
		//Save Supplier1 
		saveProduct();
		
		waitForExtJSAjaxComplete(20);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testPurchasingProductAddRemoveSuppliers");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, supplier1+"|" +costCenterRef+ "|" +glAccount+ "|" +costCategoryRef1, "@class","mcsfinancialspurchasingproductdetails"),"Supplier, CostCategory, CostCenter and GL Account are added");
				
		waitForExtJSAjaxComplete(20);
		
		clickAddButtonInSuppliersTab();
		
		waitForExtJSAjaxComplete(10);
		
		//Add one More Supplier and make this as Preferred Supplier
		String supplier2 = "2preCompName";
		addSupplier(supplier2);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", supplier2, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, supplier2);
		
		clickGeneralTabInSuppliers();
		
		waitForExtJSAjaxComplete(10);
		
		//Check IsPreferred Supplier
		checkIsPreferredSupplier();
		
		waitForExtJSAjaxComplete(10);
		
		//Select Supplier1 which is not a Preferred Supplier and Remove
		Grid.checkRowInGriByTextValueAndClick(driver, supplier1);
		
		waitForExtJSAjaxComplete(10);
		
		clickRemove();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, supplier1, "@class","mcsfinancialspurchasingproductdetails"),"Supplier1 after Remove is present");
		
		//Save Supplier2 
		saveProduct();
				
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, supplier2, "@class","mcsfinancialspurchasingproductdetails"),"Supplier2 is present");
		
		close();
		
		waitForExtJSAjaxComplete(10);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", reference, "Reference");
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		clickDeleteButton();
		
		waitForExtJSAjaxComplete(25);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, "Purchasing product is present in grid after deletion"));
		
		softAssert.assertAll();
				
		Reporter.log("Suppliers are successfully added and removed from Purchasing Product", true);
	}
    
	
	/**
	 * Testcase ID		: C90733 
	 * Author			: vpcc
	 * */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testPurchasingProductAddAndDeletePicture() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Adding/Changing Picture of Purchasing Product  : C90733</span><br>", true);

		Reporter.log("User creates Warehouse <br>", true);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASINGPRODUCTS);
		
		waitForExtJSAjaxComplete(20);
		
		//****************Stock variables Initialization **
		String reference ="6preProdRef";
		String productPic = "mcslogo.png";
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testPurchasingProductAddAndDeletePicture");
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickPictureTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickRemoveButtonInPictureTab();
		
		String existingPicDetails = getProductPictureDetails();
		
		waitForExtJSAjaxComplete(20);
		
		setProductPicture(productPic);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		String updatedPicDetails = getProductPictureDetails();
		
		softAssert.assertNotSame(existingPicDetails, updatedPicDetails, "Product picture is uploaded");
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
			
		saveProduct();
		
		waitForExtJSAjaxComplete(20);
		
		String picDetailsPostSaving = getProductPictureDetails();
		
		softAssert.assertFalse(picDetailsPostSaving.isEmpty() , "Picture is uploaded");
		
		softAssert.assertNotSame(picDetailsPostSaving, updatedPicDetails, "Product picture is saved");
		
		clickRemoveButtonInPictureTab();
		
		waitForExtJSAjaxComplete(20);
		
		saveProduct();
		
		waitForExtJSAjaxComplete(20);
		
		close();
		
		softAssert.assertAll();
		
		Reporter.log("Picture is added to Purchasing product and removed.", true);


	}
	
	/**
	 * Testcase ID		: C90736 
	 * Author			: ssu
	 * */

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testPurchasingProductAddRemoveDocumentsAndURL() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "+ 
				"Test: Adding/Removing Documents and URL to/from Purchasing Product.: C90736</span><br>",true);

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_PURCHASINGPRODUCTS);

		waitForExtJSAjaxComplete(20);

		//ProductReference Variable Initialization
		String productRef = "2preConsRef";
						
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//Select Already existing Purchasing Product
		Grid.checkRowInGriByTextValueAndClick(driver, productRef);

		waitForExtJSAjaxComplete(5);

		//Click Edit button
		clickEditButton();

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(10);
			
		clickRelatedDocumentsTab();
				
		waitForExtJSAjaxComplete(10);
		
		//Variables
		String url = "http://test.com";
		String file = "test.csv";
		String urlRemark =  "C35973UrlRem" + getCurrentDate().substring(getCurrentDate().length()-6);
		String fileDescription =  "C35973FilDes" + getCurrentDate().substring(getCurrentDate().length()-6);
		String urlDescription =  "C35973UrlDesc" + getCurrentDate().substring(getCurrentDate().length()-6);
		String fileRemark =  "C35973FilRem" + getCurrentDate().substring(getCurrentDate().length()-6);
		String type = "labelen";
			
		waitForExtJSAjaxComplete(10);
		//Url to be added
		setUrl(url, urlDescription, urlRemark, type);
					
		deleteUrl(urlDescription);
			
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(10);
		//File to be uploaded
		setFile(file, fileDescription, fileRemark, type);
							
		waitForExtJSAjaxComplete(10);
						
		deleteUrl(fileDescription); 
						
		waitForExtJSAjaxComplete(10);
		
		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testPurchasingProductAddRemoveDocumentsAndURL");
				
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, urlDescription),"url after deletion is present");
			
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, fileDescription),"file after deletion is present");
			
		close();
		
		waitForExtJSAjaxComplete(5);
			
		softAssert.assertAll();
					
		Reporter.log("Documents and URL are successfully added and removed from Purchasing Product", true);
	}
	
	/**
	 * Testcase ID		: C90730 and C90729
	 * Author			: ssu
	 * 
	 * 1. Purchasing Product is created with Stock item and isenabled field checked
	 * 2. A New Warehouse is created and purchasing product is added as a stock item
	 * 3. Stock Field is Uneditable in Purchasing Product after linked with a warehouse is verified
	 * */
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testStockFieldUnEditableInPurchasingProduct() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Is Stock field must be uneditable for purchasing product after it has been linked with at least one warehouse as stock item: C90730 </span><br>", true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create a Purchasing Product: C90729 </span><br>", true);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASINGPRODUCTS);
		
		Reporter.log("Add a Purchasing Product with IsStockITem and IsEnabled Checkboxes checked <br>", true);	
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton();

		waitForExtJSAjaxComplete(10);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(10);
		
		////////////////Variables Initialization////////////////////////
		String reference = "C35967ref" + getCurrentDate().substring(getCurrentDate().length()-5);
		String code = "C35967cod" + getCurrentDate().substring(getCurrentDate().length()-5);
		String costCategoryRef = "1preCostCtRef";
		String productCategoryRef = "1preProdCatRef";
		String revenueTaxCode = "MCS000";
		String defaultExpence = "10";
		String defaultRevenue = "11";
		String currency = "EUR";
		String unitsMeasureRef = "cubic inch";
		String precision = "3";
		String productGroupsRef = "1preProdRef";
		
		setReference(reference);
		
		setFirstCostCategory(costCategoryRef);
		
		setFirstProductCategory(productCategoryRef);
		
		setProductCode(code);
		
		setRevenueTaxCode(revenueTaxCode);
		
		setDefaultExpense(defaultExpence);
		
		setDefaultRevenue(defaultRevenue);
		
		setCurrency(currency);
		
		setFirstUnitMeasure(unitsMeasureRef); 
		
		setPrecision(precision);
		
		selectProductGroup(productGroupsRef);
		
		waitForExtJSAjaxComplete(10);
		
		//Check IsEnabled and IsStockItem
		checkIsEnabled();
		
		checkIsStockItem();
		
		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testStockFieldIsUneditableInPurchasingProduct");
		
		softAssert.assertEquals(verifyEditabilityOfIsEnabled(), true, "IsEnabled field is checked before Edit");
		
		softAssert.assertEquals(verifyEditabilityOfIsStockItem(), true, "IsStockItem field is checked before Edit");
		
		waitForExtJSAjaxComplete(5);
		
		//Fill Mandatory fields and save Product
		saveProduct();
		
		waitForExtJSAjaxComplete(10);
		
		close();
		
		waitForExtJSAjaxComplete(10);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", reference, "Reference");
		
		waitForExtJSAjaxComplete(15);
				
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		Reporter.log("Purchasing Product is succesfully created and is enabled for Transactions <br>", true);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(10);
		
		//Verify IsEnabled and IsStockItem checked
		softAssert.assertEquals(verifyEditabilityOfIsEnabled(), true, "IsEnabled field is Editable in Edit Window");
		
		softAssert.assertEquals(verifyEditabilityOfIsStockItem(), true, "IsStockItem field is Editable in Edit Window");
		
		waitForExtJSAjaxComplete(5);
			
		close();
		
		waitForExtJSAjaxComplete(10);
		
		//Navigate to Warehouse Page
		AddWarehousePageObject addwarehousePageObject = new AddWarehousePageObject();
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Add Stock Item to Warehouse <br>", true);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton(); 
		
		waitForExtJSAjaxComplete(10);

		//****************Variables Initialization 
		String warehouseReference = "C35967WHRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String warehouseCode = "C35967WHCod"+getCurrentDate().substring(getCurrentDate().length()-5);
				
		addwarehousePageObject.addWareHouse(warehouseReference, warehouseCode);
		
		waitForExtJSAjaxComplete(25);

		Reporter.log("Warehouse was succesfully created <br>", true);
				
		Grid.checkRowInGriByTextValue(driver, warehouseReference);
		
		waitForExtJSAjaxComplete(5);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(10);
		
		addwarehousePageObject.clickStockItemsTab();
		
		waitForExtJSAjaxComplete(10);
		
		addwarehousePageObject.clickAddStockItems(); 
		
		addwarehousePageObject.setStockItem(reference);
		
		waitForExtJSAjaxComplete(5);
		
		addwarehousePageObject.save();
		
		waitForExtJSAjaxComplete(5);
				
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, reference, "@class", STOCK_ITEM_GRID), reference+"stock item is added");
		
		addwarehousePageObject.close(WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		driver.navigate().refresh();

		waitForExtJSAjaxComplete(20);
		
		//Navigate to Purchasing Product and Check IsStock Field Uneditable
		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_PURCHASINGPRODUCTS);

		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel-body-noheader", reference, "Reference");
			
		//Select Already existing Purchasing Product
		Grid.checkRowInGriByTextValueAndClick(driver, reference);

		waitForExtJSAjaxComplete(5);

		//Click Edit button
		clickEditButton();

		waitForExtJSAjaxComplete(10);
		
		boolean bStatus = verifyEditabilityOfIsStockItem();
		softAssert.assertFalse(bStatus, "Stock Item Checkbox is not editable");
		
		close();
		
		waitForExtJSAjaxComplete(10);
				
		Reporter.log("Stock Field is Uneditable in Purchasing Product after it is linked to a Warehouse as a stock item", true);
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		/**
		* Testcase ID		: C35968 
		* Author			: ssu
		* 
		* 1. Purchasing Product is already created with Stock item Uneditable
		* 2. To an Existing Warehouse, purchasing product is added as a stock item and For that Purchasing product IsEnabled box is Unchecked
		* 3. Navigating to Transaction line Page and adding a new transaction line to verify Stock item is Unavailable for any transactions
		* */
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
		+ "Test: The stock item which is not enabled must not be available for any transactions: C35968 </span><br>", true);
				
		waitForExtJSAjaxComplete(10);
		
		softAssert.setMethodName("testStockFieldIsUnAvailableForAnyTransactions");
		
		//Select Already existing Purchasing Product
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		waitForExtJSAjaxComplete(5);
		
		//Click Edit button
		clickEditButton();
		
		waitForExtJSAjaxComplete(10);
		
		//Check IsEnabled
		checkIsEnabled();
		
		waitForExtJSAjaxComplete(5);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		//Save Product After Editing
		saveProduct();
		
		waitForExtJSAjaxComplete(10);
		
		//Verify IsEnabled unchecked
		softAssert.assertEquals(getIsEnabledState(), false, "IsEnabled field is Unchecked in Edit Window");
		
		waitForExtJSAjaxComplete(5);	
		
		close();
		
		waitForExtJSAjaxComplete(10);
		waitForMaskDisappear();
		//Navigate to TransactionsPage and add a Goods Issue Transaction
		TransactionsPageObject transactionsPageObject = new TransactionsPageObject();
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		String transactionType = "Goods Issue";
		String lookupWindowCol = "Reference";
		
		//Click on Choose button and select Transaction type
		transactionsPageObject.selectTransactionType(transactionType);
		
		waitForExtJSAjaxComplete(20);
		
		//Set Warehouse value
		transactionsPageObject.setWareHouse(lookupWindowCol, warehouseReference, transactionType);
		
		waitForExtJSAjaxComplete(10);
		
		//Add a new Stock Tansaction line
		transactionsPageObject.clickAddTransactionLines();
		
		waitForExtJSAjaxComplete(10);
		
		//Verifying Stock item is not available for any transaction
		String text = transactionsPageObject.verifyNoItemsListedInProductReference();
		
		softAssert.assertTrue(text.contains("No items available"),"Stock Item is not available for any Transaction");
		
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(10);
		
		transactionsPageObject.close(transactionType);
		
		waitForExtJSAjaxComplete(10);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("The stock item which is not enabled is not be available for any transactions", true);
	}
	
	
	/**
	 * Testcase ID : C90762 
	 * Author : ssa
	 * */

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testDisabledProductIsDisplayedInWarehouse() throws Exception {

		Reporter.log(
				"<span style='font-weight:bold;color:#000033'> "
						+ "C90762: If product is not enabled, still displayed in warehouse if wareshouse is added in Product</span><br>",true);

		// Variables Initialization
		String productRef = "aqapreProdRef4";
		String warehouseReference = "Central Warehouse";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";

		SoftAssert softAssert = new SoftAssert();
		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();

		softAssert.setMethodName("testUnEnabledProductIsDisplayedInWarehouse");

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_WAREHOUSES);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		filterGrid("@realid","mogrid", warehouseReference, "Reference");
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, warehouseReference);

		waitForExtJSAjaxComplete(20);

		wareHousePageObject.clickEditButton();

		waitForExtJSAjaxComplete(20);

		wareHousePageObject.clickStockItemsTab();

		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(Grid.isRowInGridPresent(driver, productRef,"@class", "stock-items-grid"), "stock item is not available");

		waitForExtJSAjaxComplete(20);

		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_PURCHASINGPRODUCTS);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, productRef);

		waitForExtJSAjaxComplete(20);

		clickEditButton();
			
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		clickStockTab();

		waitForExtJSAjaxComplete(20);

		addWareHouse(warehouseReference);

		waitForExtJSAjaxComplete(20);
		
		saveProduct();

		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();

		waitForExtJSAjaxComplete(20);

		unCheckIsEnabled();

		waitForExtJSAjaxComplete(20);

		saveProduct();

		close();

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

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, productRef,"@class", "stock-items-grid"), "stock item is added");

		softAssert.assertAll();

		Reporter.log("The stock item which is not enabled is still displayed in warehouse if wareshouse is added in Product, ",true);
	}

	}
