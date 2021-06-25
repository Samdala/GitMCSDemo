package test.generalweb.purchaseorders;

import java.util.List;

import org.apache.jasper.compiler.SmapStratum.LineInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.MenuSelector;
import test.generalweb.purchaserequisitions.PurchaseRequisitionsPageObject;
import test.generalweb.purchasingproduct.PurchasingProductPageObject;
import test.generalweb.transactions.TransactionsPageObject;
import test.generalweb.warehouse.AddWarehousePageObject;

public class PurchaseOrdersCRUDTestSuite extends PurchaseOrdersPageObject {

	/**
	 * TestCase ID : C90940
	 * Author : SSU (Sandhya)
	 */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testDuplicationOfProductErrorMessage() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> C90940 :Verify system allows to add product "
				+ "from the Product Catalog when there is a PO Line with that product already <br> </span>", true);
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_REQUISITIONS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Purchase Requisitions <br>", true);
		
		String warehouseRef = "PrPoTransactionWh";
		String referenceColName = "Reference";
		String requisitionDate = this.getFutureDate(0);
		String productReference = "aqapreProdRef2";
		String colName = "Product Reference";
		String documentType = "AutomationQaPrRef";
		String requester = "SELENIUM";
		String purchasingOrganization = "aqaPOrgRef"; //"Huntsman Belgium"
		String purchasingGrp = "aqaPOrgRef"; //"Engineering"
		String status = "Request";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testDuplicationOfProductErrorMessage");
		
		PurchaseRequisitionsPageObject purchaseRequisitionPageObject = new PurchaseRequisitionsPageObject();
		
		purchaseRequisitionPageObject.selectWareHouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.clickAddButton();
		
		waitForExtJSAjaxComplete(5);
		
		
		purchaseRequisitionPageObject.setDocumentType(referenceColName, documentType, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setRequisitionDate(requisitionDate, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setRequester( "Last Name", requester, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.clickOnAddPRLineButton(ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectProductCatalogLines("@id", getXWindowId(PRODUCT_SERVICES_WINDOW_HEADER), productReference, referenceColName, "prods_servs");
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValue(driver, productReference);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.clickTrackingTab(ADD_REQUISIION_WINDOW_HEADER); 
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setPurchasingOrganization(referenceColName, purchasingOrganization, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setPurchasingGroup(referenceColName, purchasingGrp, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.save(ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
				
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISIION_WINDOW_HEADER)), "Saved successfully");
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.setStatus(status, EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.save(EDIT_REQUISIION_WINDOW_HEADER);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISIION_WINDOW_HEADER)), "Saved successfully");
		
		String requisitionWindowID1 = getTenDigitWindowHeaderID(EDIT_REQUISIION_WINDOW_HEADER);
		System.err.println("Requisition Window ID 1 is : " + requisitionWindowID1);
		
		waitForExtJSAjaxComplete(5);
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.close(EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(30);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_ORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Purchase Orders <br>", true);
		
		//******Variable Initialization******************
		String reference = "ref"+ getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedOrderDate = this.getFutureDate(0);
		String expectedDeliveryDate = this.getFutureDate(3);
		String contactPerson = "SELENIUM";
		String supplier = "aqacompName";
		String deliveryWarehouse = "PrPoTransactionWh";
		String documentTypePO = "AutomationQaPoRef";
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(5);
		
		setReference(reference, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		setExpectedOrderDate(expectedOrderDate, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDocumentType(documentTypePO, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectSupplier(supplier, "Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingOrganization(purchasingOrganization, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingGroup(purchasingGrp, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDeliveryWarehouse(deliveryWarehouse, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		setExpectedDeliveryDate(expectedDeliveryDate, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectContactPerson(contactPerson, "Last Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		clickAddPOLineButton();
		
		MenuSelector.selectMenuItemNative(driver, "from Purchase Requisitions"); 
		
		selectPurchaseRequisitionLines("@id", getXWindowId(PURCHASE_REQUISITIONS_LINES_WINDOW_HEADER), requisitionWindowID1, "Purchase Requisition ID", "purch_req_line");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddPOLineButton();
		
		MenuSelector.selectMenuItemNative(driver, "from Product Catalog"); 
		
		selectProductCatalogLines("@id", getXWindowId(PRODUCT_SERVICES_WINDOW_HEADER), productReference, referenceColName, "prods_servs");
		
		waitForExtJSAjaxComplete(10);
		
		String warningMsg = "This Product has already been added to this Purchase Order:";
		String warningMsg1 = productReference;
		
		softAssert.assertTrue((getWarningDialogTextMsg().contains(warningMsg) && getWarningDialogTextMsg().contains(warningMsg1)), "This Product has already been added to this Purchase Order");
		
		softAssert.assertAll();
		
		Reporter.log("System  doesnot allows to add product "
				+ "from the Product Catalog when there is a PO Line with that product already existing <br>", true);
	}
	
	/**
	 * Test Case ID: C90941 
	 * Author : SSU (Sandhya)
	 */
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testPurchasingProductAvailableinPOForm() throws Exception {
		
		Reporter.log(
				"<span style='font-weight:bold;color:#000033'> C90941 : Verify created Purchasing Product is available in the PO form </span><br>",
				true);
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASINGPRODUCTS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Purchasing Product <br>", true);
		
		SoftAssert softAssert = new SoftAssert();
				
		softAssert.setMethodName("testPurchasingProductAvailableinPO");

		PurchasingProductPageObject purchasingProductPO = new PurchasingProductPageObject();
		
		purchasingProductPO.clickAddButton();

		waitForExtJSAjaxComplete(10);
		
		purchasingProductPO.clickGeneralTab();
		
		waitForExtJSAjaxComplete(10);
		
		////////////////Variables Initialization////////////////////////
		String purchasingProdRef = "C50846ref" + getCurrentDate().substring(getCurrentDate().length()-5);
		String code = "C50846cod" + getCurrentDate().substring(getCurrentDate().length()-5);
		String costCategoryRef = "Aqa_Cost_Cat_ref";
		String productCategoryRef = "4preProdCatRef";
		String revenueTaxCode = "MCS000";
		String defaultExpence = "10";
		String defaultRevenue = "11";
		String currency = "EUR";
		String unitsMeasureRef = "centimeter";
		String precision = "3";
		String productGroupsRef = "1preProdRef";
		
		purchasingProductPO.setReference(purchasingProdRef);
		
		purchasingProductPO.setFirstCostCategory(costCategoryRef);
		
		purchasingProductPO.setFirstProductCategory(productCategoryRef);
		
		purchasingProductPO.setProductCode(code);
		
		purchasingProductPO.setRevenueTaxCode(revenueTaxCode);
		
		purchasingProductPO.setDefaultExpense(defaultExpence);
		
		purchasingProductPO.setDefaultRevenue(defaultRevenue);
		
		purchasingProductPO.setCurrency(currency);
		
		purchasingProductPO.setFirstUnitMeasure(unitsMeasureRef); 
		
		purchasingProductPO.setPrecision(precision);
		
		purchasingProductPO.checkIsEnabled();
		
		purchasingProductPO.checkIsStockItem();
		
		purchasingProductPO.selectProductGroup(productGroupsRef);
		
		waitForExtJSAjaxComplete(10);
		
		//Fill Mandatory fields and save Product
		purchasingProductPO.saveProduct();
		
		waitForExtJSAjaxComplete(10);
		
		purchasingProductPO.close();
			
		Grid.checkRowInGriByTextValueAndClick(driver, purchasingProdRef);
		
		purchasingProductPO.clickEditButton();
		
		waitForExtJSAjaxComplete(10);
		
		//Click Suppliers Tab and Add a Preferred Supplier
		purchasingProductPO.clickSuppliersTab();
		
		waitForExtJSAjaxComplete(10);
		
		purchasingProductPO.clickAddButtonInSuppliersTab();
		
		waitForExtJSAjaxComplete(10);
		
		String supplier = "aqacompName";
		purchasingProductPO.addSupplier(supplier);
		
		waitForExtJSAjaxComplete(10);	
		
		purchasingProductPO.saveProduct();
		
		waitForExtJSAjaxComplete(10);
		
		purchasingProductPO.close();
		
		waitForExtJSAjaxComplete(10);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_ORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		System.err.println("Purchasing Product Ref: " +purchasingProdRef);
		
		//******Variable Initialization******************
		String reference = "ref"+ getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedOrderDate = this.getFutureDate(0);
		String productReference = purchasingProdRef;
		String referenceColName = "Reference";
		String documentType = "AutomationQaPoRef";
		String purchasingOrganization = "aqaPOrgRef";
		String purchasingGrp = "aqaPOrgRef";
		String deliveryWarehouse = "PrPoTransactionWh";
		
		Reporter.log("Navigate to Purchase Orders <br>", true);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(5);
		
		setReference(reference, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		setExpectedOrderDate(expectedOrderDate, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDocumentType(documentType, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectSupplier(supplier, "Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingOrganization(purchasingOrganization, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingGroup(purchasingGrp, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDeliveryWarehouse(deliveryWarehouse, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		clickAddPOLineButton();
		
		MenuSelector.selectMenuItemNative(driver, "from Product Catalog"); 
		
		waitForExtJSAjaxComplete(10);
		
		filterGrid("@id", getXWindowId(PRODUCT_SERVICES_WINDOW_HEADER), productReference, referenceColName);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, productReference, "@id", getXWindowId(PRODUCT_SERVICES_WINDOW_HEADER)), "Purchasing Product is available in PO");
		
		softAssert.assertAll();
		
		Reporter.log("Purchasing Product is available in PO <br>", true);
	}
	
	/**
	 * TestCase ID : C90939
	 * Author : SSU (Sandhya)
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testErrMsgSavingPOWithDiffCurrency() throws Exception {
		Reporter.log(
				"<span style='font-weight:bold;color:#000033'> C90939 :Verify system allows PO form to save when currency is not same for all PO Lines </span><br>",
				true);
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_ORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		//******Variable Initialization******************
		String reference = "ref"+ getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedOrderDate = this.getFutureDate(0);
		String productReference = "aqapreProdRef";
		String productReference1 = "aqapreProdRef1";
		String productReference2 = "aqapreProdRef2";
		String referenceColName = "Reference";
		String documentType = "AutomationQaPoRef";
		String supplier = "aqacompName";
		String purchasingOrganization = "aqaPOrgRef";
		String purchasingGrp = "aqaPOrgRef";
		String deliveryWarehouse = "PrPoTransactionWh";
		String contactPerson = "SELENIUM";
		
		Reporter.log("Navigate to Purchase Orders <br>", true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testErrMsgSavingPOWithDiffCurrency");
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(10);
		
		setReference(reference, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		setExpectedOrderDate(expectedOrderDate, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDocumentType(documentType, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectSupplier(supplier, "Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingOrganization(purchasingOrganization, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingGroup(purchasingGrp, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDeliveryWarehouse(deliveryWarehouse, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectContactPerson(contactPerson, "Last Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		//Total amount getting Populated on the PO Form
		String totalAmountPOForm = getTotalAmount(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		Reporter.log("Total Amount in New PO Form is: " +totalAmountPOForm, true);
		
		softAssert.assertTrue(totalAmountPOForm.contains("0"), "Total Amount in New PO Form is 0");
		
		clickAddPOLineButton();
		
		waitForExtJSAjaxComplete(5);
		
		MenuSelector.selectMenuItemNative(driver, "from Product Catalog"); 
		
		selectProductCatalogLines("@id", getXWindowId(PRODUCT_SERVICES_WINDOW_HEADER), productReference, referenceColName, "prods_servs");
		
		waitForExtJSAjaxComplete(10);
		
		//Order amount of the First Product Reference in PO Form
		String orderAmtInPOLine1 = getOrderAmount(ADD_PURCHASE_ORDER_WINDOW_HEADER, "1", "Line Items");
		
		//Converting Order amount String to int
		int intOrdAmtInPOLine1 = Integer.parseInt(orderAmtInPOLine1);
		
		Reporter.log("Order Amount in PO Line for the Product '"+productReference+"' is: " +intOrdAmtInPOLine1, true);
		
		//Total amount getting Populated on the PO Form
		totalAmountPOForm = getTotalAmount(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		//Converting Total amount Float to int
		Float f = Float.parseFloat(totalAmountPOForm);
		
		int intTotalAmtPOForm = f.intValue(); 
		Reporter.log("Total Amount in PO Form for the Product '"+productReference+"' is: " +totalAmountPOForm, true);
		
		softAssert.assertEquals(intOrdAmtInPOLine1, intTotalAmtPOForm, "Total Order Amount is the Order amount of the First Line");
		
		//Currency getting Populated on the PO Form
		String currencyInPOForm = getCurrency(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		Reporter.log("Currency in PO Form for the First Product '"+productReference+"' is: " +currencyInPOForm, true);
		
		//Currency of the First Product Reference in PO Form
		String currencyInPOLine1 = getCurrency(ADD_PURCHASE_ORDER_WINDOW_HEADER, "1", "Line Items");
		Reporter.log("Currency in PO Line is: " +currencyInPOLine1, true);

		softAssert.assertEquals(currencyInPOForm, currencyInPOLine1, "Currency is same as First PO Line Currency");
		
		waitForExtJSAjaxComplete(5);
		
		clickAddPOLineButton();
		 
		waitForExtJSAjaxComplete(5);
		
		MenuSelector.selectMenuItemNative(driver, "from Product Catalog"); 
		
		selectProductCatalogLines("@id", getXWindowId(PRODUCT_SERVICES_WINDOW_HEADER), productReference1, referenceColName, "prods_servs");
		
		waitForExtJSAjaxComplete(10);
		
		//Order amount of the Second Product Reference in PO Form
		String orderAmtInPOLine2 = getOrderAmount(ADD_PURCHASE_ORDER_WINDOW_HEADER, "2", "Line Items");
		
		//Converting Order amount String to int
		int intOrdAmtInPOLine2 = Integer.parseInt(orderAmtInPOLine2);
		Reporter.log("Order Amount in PO for the Product '"+productReference1+"' is: " +intOrdAmtInPOLine2, true);
		
		//Total amount getting Populated on the PO Form
		totalAmountPOForm = getTotalAmount(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		//Converting Total amount String to int
		f = Float.parseFloat(totalAmountPOForm);
		
		int intTotalAmtPOForm1 = f.intValue(); 
		Reporter.log("Total Amount in New PO for the Products '"+productReference+"' & '"+productReference1+"' is: " +totalAmountPOForm, true);
		
		softAssert.assertEquals(intTotalAmtPOForm, intTotalAmtPOForm1, "Total Order Amount is still the Order amount of the First Line");
		
		//Currency getting Populated on the PO Form
		currencyInPOForm = getCurrency(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		Reporter.log("Currency in PO Form for the Product '"+productReference+"' is: " +currencyInPOForm, true);
				
		//Currency of the First Product Reference in PO Form
		String currencyInPOLine2 = getCurrency(ADD_PURCHASE_ORDER_WINDOW_HEADER, "2", "Line Items");
		Reporter.log("Currency in PO Line is: " +currencyInPOLine2, true);

		softAssert.assertEquals(currencyInPOForm, currencyInPOLine1, "Currency is still the same as First PO Line Currency");
		
		waitForExtJSAjaxComplete(5);
		
		clickAddPOLineButton();
		
		waitForExtJSAjaxComplete(5);
		
		MenuSelector.selectMenuItemNative(driver, "from Product Catalog"); 
		
		selectProductCatalogLines("@id", getXWindowId(PRODUCT_SERVICES_WINDOW_HEADER), productReference2, referenceColName, "prods_servs");
		
		waitForExtJSAjaxComplete(10);
		
		//Order amount of the Third Product Reference in PO Form
		String orderAmtInPOLine3 = getOrderAmount(ADD_PURCHASE_ORDER_WINDOW_HEADER, "3", "Line Items");
		
		//Converting Order amount String to int
		int intOrdAmtInPOLine3 = Integer.parseInt(orderAmtInPOLine3);
		Reporter.log("Order Amount in PO for the Product '"+productReference2+"' is: " +intOrdAmtInPOLine3, true);
		
		//Total amount getting Populated on the PO Form
		totalAmountPOForm = getTotalAmount(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		//Converting Total amount String to int
		f = Float.parseFloat(totalAmountPOForm);
		
		int intTotalAmtPOForm2 = f.intValue(); 
		Reporter.log("Total Amount in New PO for the same currency Products '"+productReference+"' & '"+productReference2+"' is: " +totalAmountPOForm, true);
				
		int sameCurrencyOrdAmt = intOrdAmtInPOLine1 + intOrdAmtInPOLine3;
		
		softAssert.assertEquals(sameCurrencyOrdAmt, intTotalAmtPOForm2, "Total Order Amount is the sum of Order amount of the same currency PO Lines");
		
		//Currency getting Populated on the PO Form
		currencyInPOForm = getCurrency(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		Reporter.log("Currency in PO Form is: " +currencyInPOForm, true);
		
		//Currency of the First Product Reference in PO Form
		String currencyInPOLine3 = getCurrency(ADD_PURCHASE_ORDER_WINDOW_HEADER, "3", "Line Items");
		Reporter.log("Currency in PO Line is: " +currencyInPOLine3, true);
	
		softAssert.assertEquals(currencyInPOForm, currencyInPOLine1, "Currency is still the same as First PO Line Currency");
		
		waitForExtJSAjaxComplete(10);
		
		save(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
				
		softAssert.assertTrue((getInfoBarMsgOfWindow("@id", getXWindowId(ADD_PURCHASE_ORDER_WINDOW_HEADER))).contains("The form is invalid! Hover mouse over red marked fields to see the errors."), "The form is invalid!!");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertAll();
		
		Reporter.log("System doesn't allows PO form to save when currency is not same for all PO Lines", true);
	}
	

	/**
	 * TestCase ID : C90945 & C90943
	 * Author : SSU (Sandhya)
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testMergingPOLinesforSameProduct() throws Exception {
		Reporter.log(
				"<span style='font-weight:bold;color:#000033'> C90945 :Verify system merges PO Lines of the same product </span><br>",
				true);
		
		Reporter.log(
				"<span style='font-weight:bold;color:#000033'> C90943 :Verify all PR Lines in the PO will get status “Requested” again when the PO gets Canceled from Submitted status </span><br>",
				true);
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_REQUISITIONS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Purchase Requisitions <br>", true);
		
		String warehouseRef = "PrPoTransactionWh1";
		String referenceColName = "Reference";
		String requisitionDate = this.getFutureDate(0);
		String productReference = "aqapreProdRef1";
		String documentType = "AutomationQaPrRef";
		String requester = "SELENIUM";
		String purchasingOrganization = "aqaPOrgRef"; 
		String purchasingGrp = "aqaPOrgRef"; 
		String status = "Request";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testMergingPOLinesforSameProduct");
		
		PurchaseRequisitionsPageObject purchaseRequisitionPageObject = new PurchaseRequisitionsPageObject();
		
		purchaseRequisitionPageObject.selectWareHouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.clickAddButton();
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setDocumentType(referenceColName, documentType, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setRequisitionDate(requisitionDate, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setRequester( "Last Name", requester, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.addPRLine(ADD_REQUISIION_WINDOW_HEADER, productReference, referenceColName);
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValue(driver, productReference);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.clickTrackingTab(ADD_REQUISIION_WINDOW_HEADER); 
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setPurchasingOrganization(referenceColName, purchasingOrganization, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setPurchasingGroup(referenceColName, purchasingGrp, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.save(ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISIION_WINDOW_HEADER)), "Saved successfully");
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.setStatus(status, EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.save(EDIT_REQUISIION_WINDOW_HEADER);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISIION_WINDOW_HEADER)), "Saved successfully");
		
		String requisitionWindowID1 = getTenDigitWindowHeaderID(EDIT_REQUISIION_WINDOW_HEADER);
		System.err.println("Requisition Window ID 1 is : " + requisitionWindowID1);
		
		waitForExtJSAjaxComplete(5);
		
		String creationDate = purchaseRequisitionPageObject.getCreationDate(EDIT_REQUISIION_WINDOW_HEADER, "1");
		
		waitForExtJSAjaxComplete(25);
		
		purchaseRequisitionPageObject.close(EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.clickAddButton();
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setDocumentType(referenceColName, documentType, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setRequisitionDate(requisitionDate, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setRequester( "Last Name", requester, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.addPRLine(ADD_REQUISIION_WINDOW_HEADER, productReference, referenceColName);
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValue(driver, productReference);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.clickTrackingTab(ADD_REQUISIION_WINDOW_HEADER); 
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setPurchasingOrganization(referenceColName, purchasingOrganization, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setPurchasingGroup(referenceColName, purchasingGrp, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.save(ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
				
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISIION_WINDOW_HEADER)), "Saved successfully");
		
		waitForExtJSAjaxComplete(20);
		
		purchaseRequisitionPageObject.setStatus(status, EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.save(EDIT_REQUISIION_WINDOW_HEADER);
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISIION_WINDOW_HEADER)), "Saved successfully");
		
		String requisitionWindowID2 = getTenDigitWindowHeaderID(EDIT_REQUISIION_WINDOW_HEADER);
		System.err.println("Requisition Window ID 2 is : " +requisitionWindowID2);
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.close(EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_ORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		//******Variable Initialization******************
		String reference = "ref"+ getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedOrderDate = creationDate;
		String plannedStatus = "Planned";
		String supplier = "aqacompName";
		String contactPerson = "SELENIUM";
		String documentTypePO = "AutomationQaPoRef";
		String submittedStatus = "Submitted";
		String canceledStatus = "Canceled";
		
		Reporter.log("Navigate to Purchase Orders <br>", true);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(10);
		
		String defaultStatus = getStatus(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		softAssert.assertEquals(defaultStatus, plannedStatus, "The initial Status of Po is Planned");
		
		String defaultExpectedOrderDate = getExpectedOrderDate(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		softAssert.assertEquals(defaultExpectedOrderDate, expectedOrderDate, "Expected Order Date is automatically filled in with the Date of Creation");
		
		setExpectedOrderDate(expectedOrderDate, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDocumentType(documentTypePO, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectSupplier(supplier, "Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingOrganization(purchasingOrganization, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingGroup(purchasingGrp, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDeliveryWarehouse(warehouseRef, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectContactPerson(contactPerson, "Last Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		setReference(reference, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		clickAddPOLineButton();
		
		MenuSelector.selectMenuItemNative(driver, "from Purchase Requisitions"); 
		
		selectPurchaseRequisitionLines("@id", getXWindowId(PURCHASE_REQUISITIONS_LINES_WINDOW_HEADER), requisitionWindowID1, "Purchase Requisition ID", "purch_req_line");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddPOLineButton();
		
		MenuSelector.selectMenuItemNative(driver, "from Purchase Requisitions"); 
		
		selectPurchaseRequisitionLines("@id", getXWindowId(PURCHASE_REQUISITIONS_LINES_WINDOW_HEADER), requisitionWindowID2, "Purchase Requisition ID", "purch_req_line");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, PURCHASE_ORDERS_WINDOW_CLASS, productReference);
		
		waitForExtJSAjaxComplete(5);
		
		clickRequisitionsTab();
		
		//Verification of PO adds Requisitions line for Each PR line
		String prLinesID1 = getIDFromRequisitionsTab(ADD_PURCHASE_ORDER_WINDOW_HEADER, "1");
		Reporter.log("ID 1 in PO Requisitions Tab is "+prLinesID1, true);
		
		softAssert.assertEquals(requisitionWindowID1, prLinesID1, "PO Line is added for Purchase Requisition Line 1");
		
		String prLinesID2 = getIDFromRequisitionsTab(ADD_PURCHASE_ORDER_WINDOW_HEADER, "2");
		Reporter.log("ID 2 in PO Requisitions Tab is "+prLinesID2, true);
		
		softAssert.assertEquals(requisitionWindowID2, prLinesID2, "PO Line is added for Purchase Requisition Line 2");
		
		//*** Order amount Verification ************
		
		//Order amount of the Product Reference in PO Form Line Items Tab
		String orderAmtInPOLine = getOrderAmount(ADD_PURCHASE_ORDER_WINDOW_HEADER, "1", "Line Items");
		
		//Converting String to int of OrderAmt
		int intOrderAmtInPOLine = Integer.parseInt(orderAmtInPOLine);
		
		//Order Quantity of the Product Reference in PO Form Line Items Tab
		int intQuantityInPOLine = getOrderQty(ADD_PURCHASE_ORDER_WINDOW_HEADER, "1", "Line Items");
		
		//Unit Price of the Product Reference in PO Form Line Items Tab
		int intUnitPriceInPOLine = getUnitPrice(ADD_PURCHASE_ORDER_WINDOW_HEADER, "1", "Line Items");
		
		//Multiplying Quantity and Unit Price Line Items Tab
		int totalOrderAmt = (intQuantityInPOLine * intUnitPriceInPOLine);
		
		softAssert.assertEquals(intOrderAmtInPOLine, totalOrderAmt, "Order Amount is the total price of that product '"+productReference+"' (Quantity * Unit Price) ");

		//*** Order Quantity Verification ************
		
		//Order Quantity of the Product Reference in PO Form Requisitions Tab
		int intquantityInRequisitionsTabLine1 =  getOrderQtyRequisitionsTab(ADD_PURCHASE_ORDER_WINDOW_HEADER, "1", "Requisitions");
		
		int intquantityInRequisitionsTabLine2 = getOrderQtyRequisitionsTab(ADD_PURCHASE_ORDER_WINDOW_HEADER, "2", "Requisitions");
		
		int totQtyInRequisitionsTabLines = intquantityInRequisitionsTabLine1 + intquantityInRequisitionsTabLine2;
		
		softAssert.assertEquals(intQuantityInPOLine, totQtyInRequisitionsTabLines, "PO Line displays the sum of all quantities of underlying Purchase Requisitions.");
		
		waitForExtJSAjaxComplete(5);
		
		Reporter.log("Purchase Orders merges Purchase Requisition Lines of the same product", true);
		
		save(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		String lineIDPOLines = getLineIDFromPOLines(EDIT_PURCHASE_ORDER_WINDOW_HEADER, "1", "Line Items");
		
		Reporter.log("Line ID of the Po line is: "+lineIDPOLines, true);
		
		close(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(30);
		
		//********* C50868 Test Case Verification Starts ***********

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_REQUISITIONS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Purchase Requisitions <br>", true);
		
		purchaseRequisitionPageObject.filterPRGrid("@id", purchaseRequisitionPageObject.getFilterGridID(), requisitionWindowID1, "Requisition ID");
		
		waitForExtJSAjaxComplete(10);
		
		String orderPlannedStatus = "Order Planned";
		
		String statusEle = purchaseRequisitionPageObject.getPRLineStatus("@id", purchaseRequisitionPageObject.getFilterGridID(), requisitionWindowID1, "Requisition ID");
		
		Reporter.log("Status of the PR line '"+requisitionWindowID1+"' is: "+statusEle, true);
		
		softAssert.assertEquals(statusEle, orderPlannedStatus, "Order Planned is the status for the PR line");
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_ORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Purchase Orders <br>", true);
		
		//Filter PO Lines Grid with Line ID
		filterGridWithoutUsingMcsElement("@id", getFilterGridID(), lineIDPOLines, "Line ID");
				
		//Open the Transactions Grid Record
		openTransactionLineWOMcsElement("@id", getFilterGridID(), lineIDPOLines, "Line ID");
		
		waitForExtJSAjaxComplete(20);
		
		String []statusActions = {"Submitted", "Canceled"};
		
		List<String> statusValues = getStatusDropDownValues("@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER));

		softAssert.assertEqualsNoOrder(statusValues.toArray(), statusActions, "Submitted and Canceled are displayed as next Possible statuses");
		
		setStatusValue(EDIT_PURCHASE_ORDER_WINDOW_HEADER, submittedStatus, "@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER));
		
		waitForExtJSAjaxComplete(30);
		
		save(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER)), "Saved successfully");
		
		waitForExtJSAjaxComplete(30);
		
		String []statusActions1 = {"Planned", "Canceled", "Ordered"};
		
		List<String> statusValues1 = getStatusDropDownValues("@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER));
		
		softAssert.assertEqualsNoOrder(statusValues1.toArray(), statusActions1, "Ordered, Canceled and Planned are displayed as next Possible statuses");
		
		setStatusValue(EDIT_PURCHASE_ORDER_WINDOW_HEADER, canceledStatus, "@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER));
		
		waitForExtJSAjaxComplete(5);
		
		save(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER)), "Saved successfully");
		
		close(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(30);
		
		waitAndClick(XPATH_PURCHASE_REQUISITIONS);
		
		waitForExtJSAjaxComplete(30);
		
		purchaseRequisitionPageObject.selectWareHouse(warehouseRef);
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.clearFilters();
		
		waitForExtJSAjaxComplete(10);
		
		String requestedStatus = "Requested";
		
		String statusEle1 = purchaseRequisitionPageObject.getPRLineStatus("@id", purchaseRequisitionPageObject.getFilterGridID(), requisitionWindowID1, "Requisition ID");
		
		Reporter.log("Status of the PR line '"+requisitionWindowID1+"' is: "+statusEle1, true);
		
		softAssert.assertEquals(statusEle1, requestedStatus, "PR Lines are in Requested status when the PO gets canceled by the User");
		
		softAssert.assertAll();
		
		Reporter.log("PR lines gets Requested status when the PO gets cancelled by the User", true);
	}
	
	/**
	 * TestCase ID : C90942 & C90937
	 * Author : SSU (Sandhya)
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testPRRequestedStatusWhenPOIsCanceledFromPlanned() throws Exception {
		Reporter.log(
				"<span style='font-weight:bold;color:#000033'> C90942 & C90937:Verify all PR Lines in the PO will get status “Requested” again when the PO gets Canceled from Planned and Submitted status</span><br>",
				true);
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_REQUISITIONS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Purchase Requisitions <br>", true);
		
		String warehouseRef = "PrPoTransactionWh1";
		String referenceColName = "Reference";
		String requisitionDate = this.getFutureDate(0);
		String productReference = "aqapreProdRef1";
		String documentType = "AutomationQaPrRef";
		String requester = "SELENIUM";
		String purchasingOrganization = "aqaPOrgRef"; 
		String purchasingGrp = "aqaPOrgRef"; 
		String status = "Request";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testPRRequestedStatusWhenPOIsCanceledFromPlanned");
		
		PurchaseRequisitionsPageObject purchaseRequisitionPageObject = new PurchaseRequisitionsPageObject();
		
		purchaseRequisitionPageObject.selectWareHouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.clickAddButton();
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setDocumentType(referenceColName, documentType, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setRequisitionDate(requisitionDate, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setRequester( "Last Name", requester, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.addPRLine(ADD_REQUISIION_WINDOW_HEADER, productReference, referenceColName);
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValue(driver, productReference);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.clickTrackingTab(ADD_REQUISIION_WINDOW_HEADER); 
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setPurchasingOrganization(referenceColName, purchasingOrganization, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setPurchasingGroup(referenceColName, purchasingGrp, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.save(ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISIION_WINDOW_HEADER)), "Saved successfully");
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.setStatus(status, EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.save(EDIT_REQUISIION_WINDOW_HEADER);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISIION_WINDOW_HEADER)), "Saved successfully");
		
		String requisitionWindowID1 = getTenDigitWindowHeaderID(EDIT_REQUISIION_WINDOW_HEADER);
		System.err.println("Requisition Window ID 1 is : " + requisitionWindowID1);
		
		waitForExtJSAjaxComplete(5);
		
		String creationDate = purchaseRequisitionPageObject.getCreationDate(EDIT_REQUISIION_WINDOW_HEADER, "1");
		
		waitForExtJSAjaxComplete(25);
		
		purchaseRequisitionPageObject.close(EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(30);
		
		Reporter.log("Navigate to Purchase Orders <br>", true);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_ORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		//******Variable Initialization******************
		String reference = "ref"+ getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedOrderDate = creationDate;
		String plannedStatus = "Planned";
		String supplier = "aqacompName";
		String contactPerson = "SELENIUM";
		String documentTypePO = "AutomationQaPoRef";
		String canceledStatus = "Canceled";
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(10);
		
		String defaultStatus = getStatus(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		softAssert.assertEquals(defaultStatus, plannedStatus, "The initial Status of Po is Planned");
		
		String defaultExpectedOrderDate = getExpectedOrderDate(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		softAssert.assertEquals(defaultExpectedOrderDate, expectedOrderDate, "Expected Order Date is automatically filled in with the Date of Creation");
		
		setExpectedOrderDate(expectedOrderDate, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDocumentType(documentTypePO, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectSupplier(supplier, "Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingOrganization(purchasingOrganization, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingGroup(purchasingGrp, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDeliveryWarehouse(warehouseRef, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectContactPerson(contactPerson, "Last Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		setReference(reference, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		clickAddPOLineButton();
		
		MenuSelector.selectMenuItemNative(driver, "from Purchase Requisitions"); 
		
		selectPurchaseRequisitionLines("@id", getXWindowId(PURCHASE_REQUISITIONS_LINES_WINDOW_HEADER), requisitionWindowID1, "Purchase Requisition ID", "purch_req_line");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, PURCHASE_ORDERS_WINDOW_CLASS, productReference);
		
		waitForExtJSAjaxComplete(5);
		
		clickRequisitionsTab();
		
		//Verification of PO adds Requisitions line for Each PR line
		String prLinesID1 = getIDFromRequisitionsTab(ADD_PURCHASE_ORDER_WINDOW_HEADER, "1");
		Reporter.log("ID 1 in PO Requisitions Tab is "+prLinesID1, true);
		
		softAssert.assertEquals(requisitionWindowID1, prLinesID1, "PO Line is added for Purchase Requisition Line 1");
		
		waitForExtJSAjaxComplete(5);
		
		save(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		String lineIDPOLines = getLineIDFromPOLines(EDIT_PURCHASE_ORDER_WINDOW_HEADER, "1", "Line Items");
		
		Reporter.log("Line ID of the Po line is: "+lineIDPOLines, true);
		
		close(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(30);
		
		//***** Verification of Order Planned Status for PR lines

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_REQUISITIONS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Purchase Requisitions <br>", true);
		
		purchaseRequisitionPageObject.filterPRGrid("@id", purchaseRequisitionPageObject.getFilterGridID(), requisitionWindowID1, "Requisition ID");
		
		waitForExtJSAjaxComplete(10);
		
		String orderPlannedStatus = "Order Planned";
		
		String statusEle = purchaseRequisitionPageObject.getPRLineStatus("@id", purchaseRequisitionPageObject.getFilterGridID(), requisitionWindowID1, "Requisition ID");
		
		Reporter.log("Status of the PR line '"+requisitionWindowID1+"' is: "+statusEle, true);
		
		softAssert.assertEquals(statusEle, orderPlannedStatus, "Order Planned is the status for the PR line");
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_ORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Purchase Orders <br>", true);
		
		//Filter PO Lines Grid with Line ID
		filterGridWithoutUsingMcsElement("@id", getFilterGridID(), lineIDPOLines, "Line ID");
				
		//Open the Transactions Grid Record
		openTransactionLineWOMcsElement("@id", getFilterGridID(), lineIDPOLines, "Line ID");
		
		waitForExtJSAjaxComplete(20);
		
		String []statusActions = {"Submitted", "Canceled"};
		
		List<String> statusValues = getStatusDropDownValues("@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER));

		softAssert.assertEqualsNoOrder(statusValues.toArray(), statusActions, "Submitted and Canceled are displayed as next Possible statuses");
		
		setStatusValue(EDIT_PURCHASE_ORDER_WINDOW_HEADER, canceledStatus, "@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER));
		
		waitForExtJSAjaxComplete(30);
		
		save(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER)), "Saved successfully");
		
		close(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(30);
		
		//***** Verification of Requested Status for PR lines
		
		waitAndClick(XPATH_PURCHASE_REQUISITIONS);
		
		waitForExtJSAjaxComplete(30);
		
		purchaseRequisitionPageObject.selectWareHouse(warehouseRef);
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.clearFilters();
		
		waitForExtJSAjaxComplete(10);
		
		String requestedStatus = "Requested";
		
		String statusEle1 = purchaseRequisitionPageObject.getPRLineStatus("@id", purchaseRequisitionPageObject.getFilterGridID(), requisitionWindowID1, "Requisition ID");
		
		Reporter.log("Status of the PR line '"+requisitionWindowID1+"' is: "+statusEle1, true);
		
		softAssert.assertEquals(statusEle1, requestedStatus, "PR Lines are in Requested status when the PO gets canceled From Planned Status");
		
		softAssert.assertAll();
		
		Reporter.log("PR lines gets Requested status when the PO gets cancelled From Planned status and sumitted status", true);
	}
	
	/**
	 * Test Case ID : C90946
	 * Author : SSU (Sandhya)
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testInformatoryMessageWhenProductisAlreadyAvailableInPO() throws Exception {
		Reporter.log(
				"<span style='font-weight:bold;color:#000033'> C90946 :Informatory Message should be displayed when add any item that is already in the Purchase Order list </span><br>",
				true);
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_ORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Purchase Orders <br>", true);
		
		//******Variable Initialization******************
		String warehouseRef = "PrPoTransactionWh1";
		String productReference = "aqapreProdRef";
		String referenceColName = "Reference";
		String reference = "ref"+ getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedOrderDate = this.getFutureDate(0);
		String supplier = "aqacompName";
		String contactPerson = "SELENIUM";
		String documentTypePO = "AutomationQaPoRef";
		String purchasingOrganization = "aqaPOrgRef"; 
		String purchasingGrp = "aqaPOrgRef";
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testInformatoryMessageWhenProductisAlreadyAvailableInPO");
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(10);
		
		setReference(reference, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		setExpectedOrderDate(expectedOrderDate, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDocumentType(documentTypePO, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectSupplier(supplier, "Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingOrganization(purchasingOrganization, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingGroup(purchasingGrp, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDeliveryWarehouse(warehouseRef, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectContactPerson(contactPerson, "Last Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		clickAddPOLineButton();
		
		MenuSelector.selectMenuItemNative(driver, "from Product Catalog"); 
		
		selectProductCatalogLines("@id", getXWindowId(PRODUCT_SERVICES_WINDOW_HEADER), productReference, referenceColName, "prods_servs");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddPOLineButton();
		
		MenuSelector.selectMenuItemNative(driver, "from Product Catalog"); 
		
		selectProductCatalogLines("@id", getXWindowId(PRODUCT_SERVICES_WINDOW_HEADER), productReference, referenceColName, "prods_servs");
		
		String warningMsg = "This Product has already been added to this Purchase Order:";
		String warningMsg1 = productReference;
		
		softAssert.assertTrue((getWarningDialogTextMsg().contains(warningMsg) && getWarningDialogTextMsg().contains(warningMsg1)), "This Product has already been added to this Purchase Order");
		
		softAssert.assertAll();
		
		Reporter.log("Informatory Message is displayed when there is a PO Line with that product already existing <br>", true);
	}
	
	
	
	/**
	 * Test Case ID : C90938
	 * Author : SSU (Sandhya)
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testVerificationsOfAllPurchaseOrderFields() throws Exception {
		Reporter.log("<span style='font-weight:bold;color:#000033'> C90938 :Verify a new Purchase Order (PO) can be added in the PO Header table </span><br>", true);
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_REQUISITIONS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Purchase Requisitions <br>", true);
		
		String warehouseRef = "PrPoTransactionWh1";
		String warehouseRef1 = "PrPoTransactionWh";
		String referenceColName = "Reference";
		String requisitionDate = this.getFutureDate(0);
		String productReference2 = "aqapreProdRef";
		String productReference = "aqapreProdRef1";
		String productReference1 = "aqapreProdRef2";
		String documentType = "AutomationQaPrRef";
		String requester = "SELENIUM";
		String purchasingOrganization = "aqaPOrgRef"; 
		String purchasingGrp = "aqaPOrgRef"; 
		String status = "Request";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testVerificationsOfAllPurchaseOrderFields");
		
		PurchaseRequisitionsPageObject purchaseRequisitionPageObject = new PurchaseRequisitionsPageObject();
		
		purchaseRequisitionPageObject.selectWareHouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.clickAddButton();
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setDocumentType(referenceColName, documentType, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setRequisitionDate(requisitionDate, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setRequester( "Last Name", requester, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.addPRLine(ADD_REQUISIION_WINDOW_HEADER, productReference, referenceColName);
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValue(driver, productReference);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.clickTrackingTab(ADD_REQUISIION_WINDOW_HEADER); 
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setPurchasingOrganization(referenceColName, purchasingOrganization, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setPurchasingGroup(referenceColName, purchasingGrp, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.save(ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISIION_WINDOW_HEADER)), "Saved successfully");
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.setStatus(status, EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.save(EDIT_REQUISIION_WINDOW_HEADER);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISIION_WINDOW_HEADER)), "Saved successfully");
		
		String requisitionWindowID1 = getTenDigitWindowHeaderID(EDIT_REQUISIION_WINDOW_HEADER);
		System.err.println("Requisition Window ID 1 is : " + requisitionWindowID1);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.close(EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(30);
		
		//Adding a new Purchase Requisition
		
		purchaseRequisitionPageObject.selectWareHouse(warehouseRef1);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.clickAddButton();
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setDocumentType(referenceColName, documentType, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setRequisitionDate(requisitionDate, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setRequester( "Last Name", requester, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.addPRLine(ADD_REQUISIION_WINDOW_HEADER, productReference2, referenceColName);
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValue(driver, productReference2);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.clickTrackingTab(ADD_REQUISIION_WINDOW_HEADER); 
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setPurchasingOrganization(referenceColName, purchasingOrganization, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setPurchasingGroup(referenceColName, purchasingGrp, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.save(ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISIION_WINDOW_HEADER)), "Saved successfully");
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.setStatus(status, EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.save(EDIT_REQUISIION_WINDOW_HEADER);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISIION_WINDOW_HEADER)), "Saved successfully");
		
		String requisitionWindowID2 = getTenDigitWindowHeaderID(EDIT_REQUISIION_WINDOW_HEADER);
		System.err.println("Requisition Window ID 2 is : " + requisitionWindowID2);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.close(EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		//Creating a new Purchase Orders
		
		Reporter.log("Navigate to Purchase Orders <br>", true);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_ORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		//******Variable Initialization******************
		String reference = "ref"+ getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedOrderDate = this.getFutureDate(0);
		String supplier = "aqacompName";
		String contactPerson = "SELENIUM";
		String documentTypePO = "AutomationQaPoRef";
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(getWindowHeaderText(ADD_PURCHASE_ORDER_WINDOW_HEADER).contains("New Purchase Order"), "New Purchase Order is the text in Header");
		
		clickDocumentTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickNotesTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickDocumentTab();
		
		Reporter.log("History and Related Documents tab are Disabled in a new Purchase Order Form", true);
		
		boolean tabStatus = isDiabledHistoryTab(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		softAssert.assertTrue(tabStatus,"History tab is disabled");
		
		tabStatus = isDisabledRelatedDocTab(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		softAssert.assertTrue(tabStatus,"Related Document tab is disabled");
		
		softAssert.assertTrue(isDisabledAddButton(ADD_REQUISIION_WINDOW_HEADER),"Add button is disabled");
		
		waitForExtJSAjaxComplete(5);
		
		setReference(reference, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		setExpectedOrderDate(expectedOrderDate, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDocumentType(documentTypePO, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectSupplier(supplier, "Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingOrganization(purchasingOrganization, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertFalse(isDisabledAddButton(ADD_PURCHASE_ORDER_WINDOW_HEADER),"Add button is enabled");
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingGroup(purchasingGrp, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDeliveryWarehouse(warehouseRef, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectContactPerson(contactPerson, "Last Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		clickAddPOLineButton();
		
		MenuSelector.selectMenuItemNative(driver, "from Purchase Requisitions"); 
		
		selectPurchaseRequisitionLines("@id", getXWindowId(PURCHASE_REQUISITIONS_LINES_WINDOW_HEADER), requisitionWindowID1, "Purchase Requisition ID", "purch_req_line");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, PURCHASE_ORDERS_WINDOW_CLASS, productReference);
		
		waitForExtJSAjaxComplete(5);
		
		//Verifying PO line fields are un-editable
		
		boolean fieldStatus = isLineNRUnEditable();
		softAssert.assertTrue(fieldStatus, "Line Nr is UnEditable");
		
		fieldStatus = isLineIDUnEditable();
		softAssert.assertTrue(fieldStatus, "Line ID is UnEditable");
		
		fieldStatus = isProductCodeUnEditable();
		softAssert.assertTrue(fieldStatus, "Product Code is UnEditable");
		
		fieldStatus = isProductReferenceUnEditable();
		softAssert.assertTrue(fieldStatus, "Product Reference is UnEditable");
		
		fieldStatus = isExternalProductUnEditable();
		softAssert.assertTrue(fieldStatus, "External Product is UnEditable");
		
		fieldStatus = isOrderQuantityUnEditable();
		softAssert.assertTrue(fieldStatus, "Order Quantity is UnEditable");
		
		fieldStatus = isOrderUOMUnEditable();
		softAssert.assertTrue(fieldStatus, "Order UOM is UnEditable");
		
		fieldStatus = isUnitPriceUnEditable();
		softAssert.assertTrue(fieldStatus, "Unit Price is UnEditable");
		
		fieldStatus = isCurrencyUnEditable();
		softAssert.assertTrue(fieldStatus, "Currency is UnEditable");
		
		fieldStatus = isOrderAmountUnEditable();
		softAssert.assertTrue(fieldStatus, "Order Amount is UnEditable");
		
		waitForExtJSAjaxComplete(5);
		
		clickGeneralTab();
		
		clickDescriptionTab();
		
		waitForExtJSAjaxComplete(5);
		
		//Stock and Gooda Receipt tabs are disabled
		
		tabStatus = isDiabledRequisitionsTab(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		softAssert.assertFalse(tabStatus, "Requisitions Tab is Enabled");
		
		tabStatus = isDisabledStockTab(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		softAssert.assertTrue(tabStatus, "Stock Tab is Disabled");
		
		tabStatus = isDisabledGoodsReceiptTab(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		softAssert.assertTrue(tabStatus, "Goods Receipt Tab is Disabled");
		
		clickRequisitionsTab();
		
		//Verification of PO adds Requisitions line for Each PR line
		String prLinesID1 = getIDFromRequisitionsTab(ADD_PURCHASE_ORDER_WINDOW_HEADER, "1");
		Reporter.log("ID 1 in PO Requisitions Tab is "+prLinesID1, true);
				
		softAssert.assertEquals(requisitionWindowID1, prLinesID1, "PO Line is added for Purchase Requisition Line 1");
		
		waitForExtJSAjaxComplete(5);
		
		clickDeletePOLineButton();
		
		waitForExtJSAjaxComplete(5);
		
		clickAddPOLineButton();
		
		MenuSelector.selectMenuItemNative(driver, "from Product Catalog"); 
		
		selectProductCatalogLines("@id", getXWindowId(PRODUCT_SERVICES_WINDOW_HEADER), productReference1, referenceColName, "prods_servs");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, PURCHASE_ORDERS_WINDOW_CLASS, productReference1);
		
		waitForExtJSAjaxComplete(5);
		
		//Verifying PO line fields are un-editable
		fieldStatus = isLineNRUnEditable();
		softAssert.assertTrue(fieldStatus, "Line Nr is UnEditable");
		
		fieldStatus = isLineIDUnEditable();
		softAssert.assertTrue(fieldStatus, "Line ID is UnEditable");
		
		fieldStatus = isProductCodeUnEditable();
		softAssert.assertTrue(fieldStatus, "Product Code is UnEditable");
		
		fieldStatus = isProductReferenceUnEditable();
		softAssert.assertTrue(fieldStatus, "Product Reference is UnEditable");
		
		fieldStatus = isExternalProductUnEditable();
		softAssert.assertTrue(fieldStatus, "External Product is UnEditable");
		
		fieldStatus = isOrderQuantityUnEditable();
		softAssert.assertTrue(fieldStatus, "Order Quantity is UnEditable");
		
		fieldStatus = isOrderUOMUnEditable();
		softAssert.assertTrue(fieldStatus, "Order UOM is UnEditable");
		
		fieldStatus = isUnitPriceUnEditable();
		softAssert.assertTrue(fieldStatus, "Unit Price is UnEditable");
		
		fieldStatus = isCurrencyUnEditable();
		softAssert.assertTrue(fieldStatus, "Currency is UnEditable");
		
		fieldStatus = isOrderAmountUnEditable();
		softAssert.assertTrue(fieldStatus, "Order Amount is UnEditable");
		
		waitForExtJSAjaxComplete(5);
		
		clickGeneralTab();
		
		clickDescriptionTab();
		
		waitForExtJSAjaxComplete(5);
		
		//Requisitions, Stock and Gooda Receipt tabs are disabled
		tabStatus = isDiabledRequisitionsTab(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		softAssert.assertTrue(tabStatus, "Requisitions Tab is Disabled");
		
		tabStatus = isDisabledStockTab(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		softAssert.assertTrue(tabStatus, "Stock Tab is Disabled");
		
		tabStatus = isDisabledGoodsReceiptTab(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		softAssert.assertTrue(tabStatus, "Goods Receipt Tab is Disabled");
		
		waitForExtJSAjaxComplete(5);
		
		clickDeletePOLineButton();
		
		waitForExtJSAjaxComplete(5);
		
		clickAddPOLineButton();
		
		MenuSelector.selectMenuItemNative(driver, "from Purchase Requisitions"); 
		
		selectPurchaseRequisitionLines("@id", getXWindowId(PURCHASE_REQUISITIONS_LINES_WINDOW_HEADER), requisitionWindowID1, "Purchase Requisition ID", "purch_req_line");
		
		waitForExtJSAjaxComplete(10);
		
		clickAddPOLineButton();
		
		MenuSelector.selectMenuItemNative(driver, "from Purchase Requisitions"); 
		
		selectPurchaseRequisitionLines("@id", getXWindowId(PURCHASE_REQUISITIONS_LINES_WINDOW_HEADER), requisitionWindowID2, "Purchase Requisition ID", "purch_req_line");
		
		waitForExtJSAjaxComplete(10);
		
		checkRowInGriByTextValueAndClick(driver, PURCHASE_ORDERS_WINDOW_CLASS, productReference);
		
		waitForExtJSAjaxComplete(5);
		
		clickRequisitionsTab();
		
		//Verification of PO adding Requisitions line for Each PR line
		prLinesID1 = getIDFromRequisitionsTab(ADD_PURCHASE_ORDER_WINDOW_HEADER, "1");
		Reporter.log("ID 1 in PO Requisitions Tab is "+prLinesID1, true);
				
		softAssert.assertEquals(requisitionWindowID1, prLinesID1, "PO Line is added for Purchase Requisition Line 1");
		
		waitForExtJSAjaxComplete(5);
		
		checkRowInGriByTextValueAndClick(driver, PURCHASE_ORDERS_WINDOW_CLASS, productReference2);
		
		waitForExtJSAjaxComplete(5);
		
		clickRequisitionsTab();
		
		//Verification of PO adding Requisitions line for Each PR line
		String prLinesID2 = getIDFromRequisitionsTab(ADD_PURCHASE_ORDER_WINDOW_HEADER, "1");
		Reporter.log("ID 2 in PO Requisitions Tab is "+prLinesID2, true);
				
		softAssert.assertEquals(requisitionWindowID2, prLinesID2, "PO Line is added for Purchase Requisition Line 2");
		
		waitForExtJSAjaxComplete(5);
		
		clickAddPOLineButton();
		
		MenuSelector.selectMenuItemNative(driver, "from Product Catalog"); 
		
		selectProductCatalogLines("@id", getXWindowId(PRODUCT_SERVICES_WINDOW_HEADER), productReference1, referenceColName, "prods_servs");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, PURCHASE_ORDERS_WINDOW_CLASS, productReference1);
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValueAndClick(driver, PURCHASE_ORDERS_WINDOW_CLASS, productReference);
		
		clickDeletePOLineButton();
		
		waitForExtJSAjaxComplete(5);
		
		save(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		String lineIDPoLine1 = getLineIDFromPOLines(EDIT_PURCHASE_ORDER_WINDOW_HEADER, "1", "Line Items");
		Reporter.log(lineIDPoLine1, true);
		
		String lineIDPoLine2 = getLineIDFromPOLines(EDIT_PURCHASE_ORDER_WINDOW_HEADER, "2", "Line Items");
		Reporter.log(lineIDPoLine2, true);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(getWindowHeaderText(EDIT_PURCHASE_ORDER_WINDOW_HEADER).matches("Purchase Order [0-9]+"), "Purchase Order <number> is present in Window Header");
		
		softAssert.assertAll();
		
		Reporter.log("A new Purchase Order (PO) is be added in the PO Header table", true);
	}
	
	/**
	 * Test Case ID : C90952
	 * Author : SSU (Sandhya)
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testPRDeletionWhenPOisPlanned() throws Exception {
		Reporter.log("<span style='font-weight:bold;color:#000033'> C90952 :Verify system allows to delete a PR when a PO is in Planned status </span><br>", true);
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_REQUISITIONS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Purchase Requisitions <br>", true);
		
		String warehouseRef = "PrPoTransactionWh";
		String referenceColName = "Reference";
		String requisitionDate = this.getFutureDate(0);
		String productReference = "aqapreProdRef";
		String documentType = "AutomationQaPrRef";
		String requester = "SELENIUM";
		String purchasingOrganization = "aqaPOrgRef"; 
		String purchasingGrp = "aqaPOrgRef"; 
		String status = "Request";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testPRDeletionWhenPOisPlanned");
		
		PurchaseRequisitionsPageObject purchaseRequisitionPageObject = new PurchaseRequisitionsPageObject();
		
		purchaseRequisitionPageObject.selectWareHouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.clickAddButton();
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setDocumentType(referenceColName, documentType, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setRequisitionDate(requisitionDate, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setRequester( "Last Name", requester, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.addPRLine(ADD_REQUISIION_WINDOW_HEADER, productReference, referenceColName);
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValue(driver, productReference);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.clickTrackingTab(ADD_REQUISIION_WINDOW_HEADER); 
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setPurchasingOrganization(referenceColName, purchasingOrganization, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setPurchasingGroup(referenceColName, purchasingGrp, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.save(ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISIION_WINDOW_HEADER)), "Saved successfully");
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.setStatus(status, EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.save(EDIT_REQUISIION_WINDOW_HEADER);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISIION_WINDOW_HEADER)), "Saved successfully");
		
		String requisitionWindowID1 = getTenDigitWindowHeaderID(EDIT_REQUISIION_WINDOW_HEADER);
		System.err.println("Requisition Window ID 1 is : " + requisitionWindowID1);
		
		waitForExtJSAjaxComplete(5);
		
		String creationDate = purchaseRequisitionPageObject.getCreationDate(EDIT_REQUISIION_WINDOW_HEADER, "1");
		
		waitForExtJSAjaxComplete(25);
		
		purchaseRequisitionPageObject.close(EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(30);
		
		Reporter.log("Navigate to Purchase Orders <br>", true);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_ORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		//******Variable Initialization******************
		String reference = "ref"+ getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedOrderDate = creationDate;
		String plannedStatus = "Planned";
		String supplier = "aqacompName";
		String contactPerson = "SELENIUM";
		String documentTypePO = "AutomationQaPoRef";

		clickAddButton();
		
		waitForExtJSAjaxComplete(10);
		
		String defaultStatus = getStatus(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		softAssert.assertEquals(defaultStatus, plannedStatus, "The initial Status of Po is Planned");
		
		String defaultExpectedOrderDate = getExpectedOrderDate(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		softAssert.assertEquals(defaultExpectedOrderDate, expectedOrderDate, "Expected Order Date is automatically filled in with the Date of Creation");
		
		setExpectedOrderDate(expectedOrderDate, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDocumentType(documentTypePO, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectSupplier(supplier, "Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingOrganization(purchasingOrganization, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingGroup(purchasingGrp, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDeliveryWarehouse(warehouseRef, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectContactPerson(contactPerson, "Last Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		setReference(reference, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		clickAddPOLineButton();
		
		MenuSelector.selectMenuItemNative(driver, "from Purchase Requisitions"); 
		
		selectPurchaseRequisitionLines("@id", getXWindowId(PURCHASE_REQUISITIONS_LINES_WINDOW_HEADER), requisitionWindowID1, "Purchase Requisition ID", "purch_req_line");
		
		waitForExtJSAjaxComplete(10);
		
		save(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		close(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(30);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_REQUISITIONS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Purchase Requisitions <br>", true);
		
		purchaseRequisitionPageObject.selectWareHouse(warehouseRef);
		
		purchaseRequisitionPageObject.clearFilters();
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.filterPRGrid("@id", purchaseRequisitionPageObject.getFilterGridID(), requisitionWindowID1, "Requisition ID");
		
		waitForExtJSAjaxComplete(10);
		
		String orderPlannedStatus = "Order Planned";
		
		String statusEle = purchaseRequisitionPageObject.getPRLineStatus("@id", purchaseRequisitionPageObject.getFilterGridID(), requisitionWindowID1, "Requisition ID");
		
		Reporter.log("Status of the PR line '"+requisitionWindowID1+"' is: "+statusEle, true);
		
		softAssert.assertEquals(statusEle, orderPlannedStatus, "Order Planned is the status for the PR line");
		
		purchaseRequisitionPageObject.clickDeleteButton();
		
		waitForExtJSAjaxComplete(5);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(5);
		
		String warningMsg = "The Purchasing Requisition cannot be deleted because one or more Lines have reached the next step in the workflow";
		
		softAssert.assertTrue((getWarningDialogTextMsg().contains(warningMsg)), "The Purchasing Requisition cannot be deleted Error message is displayed");
		
		softAssert.assertAll();
		
		Reporter.log("System doesnot allows to delete a PR line when a PO is in Planned status", true);
	}
	
	/**
	 * Test Case ID : C90949
	 * Author : SSU (Sandhya)
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testGoodsReceiptTransactionFromPO() throws Exception {
		Reporter.log("<span style='font-weight:bold;color:#000033'> C90949 :Verify Goods Receipt Transaction is created When PO is in Ordered Status from PO Window </span><br>", true);
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_REQUISITIONS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Purchase Requisitions <br>", true);
		
		String warehouseRef = "PrPoTransactionWh";
		String referenceColName = "Reference";
		String requisitionDate = this.getFutureDate(0);
		String productReference = "aqapreProdRef";
		String documentType = "AutomationQaPrRef";
		String requester = "SELENIUM";
		String purchasingOrganization = "aqaPOrgRef"; 
		String purchasingGrp = "aqaPOrgRef"; 
		String status = "Request";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testGoodsReceiptTransactionFromPO");
		
		PurchaseRequisitionsPageObject purchaseRequisitionPageObject = new PurchaseRequisitionsPageObject();
		
		purchaseRequisitionPageObject.selectWareHouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.clickAddButton();
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setDocumentType(referenceColName, documentType, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setRequisitionDate(requisitionDate, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setRequester( "Last Name", requester, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.addPRLine(ADD_REQUISIION_WINDOW_HEADER, productReference, referenceColName);
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValue(driver, productReference);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.clickTrackingTab(ADD_REQUISIION_WINDOW_HEADER); 
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setPurchasingOrganization(referenceColName, purchasingOrganization, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setPurchasingGroup(referenceColName, purchasingGrp, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.save(ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISIION_WINDOW_HEADER)), "Saved successfully");
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.setStatus(status, EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.save(EDIT_REQUISIION_WINDOW_HEADER);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISIION_WINDOW_HEADER)), "Saved successfully");
		
		String requisitionWindowID1 = getTenDigitWindowHeaderID(EDIT_REQUISIION_WINDOW_HEADER);
		System.err.println("Requisition Window ID 1 is : " + requisitionWindowID1);
		
		waitForExtJSAjaxComplete(5);
		
		String creationDate = purchaseRequisitionPageObject.getCreationDate(EDIT_REQUISIION_WINDOW_HEADER, "1");
		
		waitForExtJSAjaxComplete(25);
		
		purchaseRequisitionPageObject.close(EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(30);
		
		Reporter.log("Navigate to Purchase Orders <br>", true);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_ORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		//******Variable Initialization******************
		String reference = "ref"+ getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedOrderDate = creationDate;
		String plannedStatus = "Planned";
		String supplier = "aqacompName";
		String contactPerson = "SELENIUM";
		String documentTypePO = "AutomationQaPoRef";
		String submittedStatus = "Submitted";
		String orderedStatus = "Ordered";

		clickAddButton();
		
		waitForExtJSAjaxComplete(10);
		
		String defaultStatus = getStatus(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		softAssert.assertEquals(defaultStatus, plannedStatus, "The initial Status of Po is Planned");
		
		String defaultExpectedOrderDate = getExpectedOrderDate(ADD_PURCHASE_ORDER_WINDOW_HEADER);
				
		softAssert.assertEquals(defaultExpectedOrderDate, expectedOrderDate, "Expected Order Date is automatically filled in with the Date of Creation");
		
		setExpectedOrderDate(expectedOrderDate, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDocumentType(documentTypePO, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectSupplier(supplier, "Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingOrganization(purchasingOrganization, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingGroup(purchasingGrp, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDeliveryWarehouse(warehouseRef, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectContactPerson(contactPerson, "Last Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		setReference(reference, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddPOLineButton();
		
		waitForExtJSAjaxComplete(25);
		
		MenuSelector.selectMenuItemNative(driver, "from Purchase Requisitions"); 

		selectPurchaseRequisitionLines("@id", getXWindowId(PURCHASE_REQUISITIONS_LINES_WINDOW_HEADER), requisitionWindowID1, "Purchase Requisition ID", "purch_req_line");
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(25);
		
		save(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		String []statusActions = {"Submitted", "Canceled"};
		
		List<String> statusValues = getStatusDropDownValues("@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER));

		softAssert.assertEqualsNoOrder(statusValues.toArray(), statusActions, "Submitted and Canceled are displayed as next Possible statuses");
		
		setStatusValue(EDIT_PURCHASE_ORDER_WINDOW_HEADER, submittedStatus, "@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER));
		
		save(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER)), "Saved successfully");
		
		waitForExtJSAjaxComplete(30);
		
		String []statusActions1 = {"Planned", "Canceled", "Ordered"};
		
		List<String> statusValues1 = getStatusDropDownValues("@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER));
		
		softAssert.assertEqualsNoOrder(statusValues1.toArray(), statusActions1, "Ordered, Canceled and Planned are displayed as next Possible statuses");
		
		setStatusValue(EDIT_PURCHASE_ORDER_WINDOW_HEADER, orderedStatus, "@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER));
		
		waitForExtJSAjaxComplete(5);
		
		save(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER)), "Saved successfully");
		
		waitForExtJSAjaxComplete(10);
				
		String purchaseOrdersWindowID = getTenDigitWindowHeaderID(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		System.err.println("purchase Orders Window ID is : " + purchaseOrdersWindowID);
		
		Grid.checkRowInGriByTextValueAndClick(driver, PURCHASE_ORDERS_WINDOW_CLASS, productReference);
				
		waitForExtJSAjaxComplete(5);
		
		int orderQuantityInPO = getOrderQty(EDIT_PURCHASE_ORDER_WINDOW_HEADER, "1", "Line Items");
		
		clickGoodsReceiptTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickShowGoodsReceiptButton();
		
		waitForExtJSAjaxComplete(10);
		
		TransactionsPageObject transactionsPO = new TransactionsPageObject();
		
		String warehouseinGoodsReceiptWindow = transactionsPO.getDefaultWarehouseValue(GOODS_RECEIPT_WINDOW_HEADER);
		Reporter.log("Warehouse Value in Goods Receipt window is "+warehouseinGoodsReceiptWindow, true);
		
		softAssert.assertEquals(warehouseRef, warehouseinGoodsReceiptWindow, "Warehouse is same in PO and Goods Receipt");
		
		waitForExtJSAjaxComplete(5);
		
		String poIDFromGoodsReceiptWindow = getTenDigitIDFromRemarksFieldGRWindow(GOODS_RECEIPT_WINDOW_HEADER);
		Reporter.log("Goods Receipt ID From Remarks field in Goods Receipt window is "+poIDFromGoodsReceiptWindow, true);
		
		softAssert.assertEquals(purchaseOrdersWindowID, poIDFromGoodsReceiptWindow, "Purchase Order window header id is displayed in Remarks field of GR Window");
		
		waitForExtJSAjaxComplete(5);

		Reporter.log("Order Quantity in PO window is "+orderQuantityInPO, true);
		
		String orderQuantityinGR = transactionsPO.getQuantityOrderedOfTransactionLine("1", GR_TRANSACTION_LINE_QUANTITY_CLASS);
		
		int intorderQuantityinGR = Integer.parseInt(orderQuantityinGR);
		Reporter.log("Transaction Line Order Quantity in GR window is "+intorderQuantityinGR, true);
		
		softAssert.assertEquals(orderQuantityInPO, intorderQuantityinGR, "PO Order Quantity is displayed in Quantity Ordered Field of GR Window");
		
		waitForExtJSAjaxComplete(5);
		
		transactionsPO.close(GOODS_RECEIPT_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		close(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("Goods Receipt Transaction is Verified From PO Window", true);
	}
	
	/**
	 * Test Case ID : C90950 & C90951
	 * Author : SSU (Sandhya)
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testAvailableAndTotalStockValueIncreasesWhenPOisOrdered() throws Exception {
		Reporter.log("<span style='font-weight:bold;color:#000033'> C90950 :Verify Goods Receipt Transaction When PO is in Ordered Status </span><br>", true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> C90951 :Verify PO creates Goods Receipt Transaction and Goods receipt increases Available and Total Stock value </span><br>", true);
		
		waitForExtJSAjaxComplete(30);
		
		String warehouseRef = "PrPoTransactionWh";
		String referenceColName = "Reference";
		String wareHouseLoc = "IND";
		String availableStockColName = "Available Stock";
		String reservedStockColName = "Reserved Stock";
		String blockedStockColName = "Blocked Stock";
		String totalStockColName = "Total Stock";
		String productReference = "aqapreProdRef";
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		String requisitionDate = this.getFutureDate(0);
		String documentType = "AutomationQaPrRef";
		String requester = "SELENIUM";
		String purchasingOrganization = "aqaPOrgRef"; 
		String purchasingGrp = "aqaPOrgRef"; 
		String status = "Request";
		String expectedDate = this.getFutureDate(3);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		//Navigate to Warehouse and Select the edit warehouse
		//Grid.checkRowInGriByTextValue(driver, warehouseRef);
		
		Grid.checkRowInGriByTextValueExactMatch(driver, warehouseRef);

		AddWarehousePageObject addWarehousePageObject = new AddWarehousePageObject();
				
		addWarehousePageObject.clickEditButton(); 
				
		waitForExtJSAjaxComplete(10);
				
		//Selecting Stock items Tab
		addWarehousePageObject.clickStockItemsTab();
				
		waitForExtJSAjaxComplete(10);
				
		Grid.checkRowInGriByTextValue(driver, productReference);
				
		int availableStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(addWarehousePageObject.getProductRowNumberInWareHouse(productReference),availableStockColName));
		int reservedStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(addWarehousePageObject.getProductRowNumberInWareHouse(productReference),reservedStockColName));
		int totalStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(addWarehousePageObject.getProductRowNumberInWareHouse(productReference),totalStockColName));
		int blockedStockValue = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(addWarehousePageObject.getProductRowNumberInWareHouse(productReference),blockedStockColName));
				
		Reporter.log(productReference+" available stock quantity is "+availableStockValue, true);
		Reporter.log(productReference+" reserved stock quantity is "+reservedStockValue, true);
		Reporter.log(productReference+" blocked stock quantity is "+blockedStockValue, true);
		Reporter.log(productReference+" total stock quantity is "+totalStockValue, true);
				
		waitForExtJSAjaxComplete(10);
				
		addWarehousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_REQUISITIONS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Navigate to Purchase Requisitions <br>", true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testAvailableAndTotalStockValueIncreasesWhenPOisOrdered");
		
		PurchaseRequisitionsPageObject purchaseRequisitionPageObject = new PurchaseRequisitionsPageObject();
		
		purchaseRequisitionPageObject.selectWareHouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.clickAddButton();
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setDocumentType(referenceColName, documentType, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setRequisitionDate(requisitionDate, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setRequester( "Last Name", requester, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.addPRLine(ADD_REQUISIION_WINDOW_HEADER, productReference, referenceColName);
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValue(driver, productReference);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.clickTrackingTab(ADD_REQUISIION_WINDOW_HEADER); 
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setPurchasingOrganization(referenceColName, purchasingOrganization, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setPurchasingGroup(referenceColName, purchasingGrp, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.save(ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISIION_WINDOW_HEADER)), "Saved successfully");
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.setStatus(status, EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.save(EDIT_REQUISIION_WINDOW_HEADER);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISIION_WINDOW_HEADER)), "Saved successfully");
		
		String requisitionWindowID1 = getTenDigitWindowHeaderID(EDIT_REQUISIION_WINDOW_HEADER);
		System.err.println("Requisition Window ID 1 is : " + requisitionWindowID1);
		
		waitForExtJSAjaxComplete(5);
		
		String creationDate = purchaseRequisitionPageObject.getCreationDate(EDIT_REQUISIION_WINDOW_HEADER, "1");
		
		waitForExtJSAjaxComplete(25);
		
		purchaseRequisitionPageObject.close(EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(30);
		
		Reporter.log("Navigate to Purchase Orders <br>", true);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_ORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		//******Variable Initialization******************
		String reference = "ref"+ getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedOrderDate = creationDate;
		String plannedStatus = "Planned";
		String supplier = "aqacompName";
		String contactPerson = "SELENIUM";
		String documentTypePO = "AutomationQaPoRef";
		String submittedStatus = "Submitted";
		String orderedStatus = "Ordered";
		String lineQuantity = "10";
		int intLineQuantity = Integer.parseInt(lineQuantity);

		clickAddButton();
		
		waitForExtJSAjaxComplete(10);
		
		String defaultStatus = getStatus(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		softAssert.assertEquals(defaultStatus, plannedStatus, "The initial Status of Po is Planned");
		
		String defaultExpectedOrderDate = getExpectedOrderDate(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		softAssert.assertEquals(defaultExpectedOrderDate, expectedOrderDate, "Expected Order Date is automatically filled in with the Date of Creation");
		
		setExpectedOrderDate(expectedOrderDate, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDocumentType(documentTypePO, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectSupplier(supplier, "Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingOrganization(purchasingOrganization, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingGroup(purchasingGrp, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDeliveryWarehouse(warehouseRef, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectContactPerson(contactPerson, "Last Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		setReference(reference, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		clickAddPOLineButton();
		
		MenuSelector.selectMenuItemNative(driver, "from Purchase Requisitions"); 
		
		selectPurchaseRequisitionLines("@id", getXWindowId(PURCHASE_REQUISITIONS_LINES_WINDOW_HEADER), requisitionWindowID1, "Purchase Requisition ID", "purch_req_line");
		
		waitForExtJSAjaxComplete(10);
		
		save(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER)), "Saved successfully");
		
		waitForExtJSAjaxComplete(30);
		
		String []statusActions = {"Submitted", "Canceled"};
		
		List<String> statusValues = getStatusDropDownValues("@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER));

		softAssert.assertEqualsNoOrder(statusValues.toArray(), statusActions, "Submitted and Canceled are displayed as next Possible statuses");
		
		setStatusValue(EDIT_PURCHASE_ORDER_WINDOW_HEADER, submittedStatus, "@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER));
		
		save(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER)), "Saved successfully");
		
		waitForExtJSAjaxComplete(30);
		
		String []statusActions1 = {"Planned", "Canceled", "Ordered"};
		
		List<String> statusValues1 = getStatusDropDownValues("@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER));
		
		softAssert.assertEqualsNoOrder(statusValues1.toArray(), statusActions1, "Ordered, Canceled and Planned are displayed as next Possible statuses");
		
		setStatusValue(EDIT_PURCHASE_ORDER_WINDOW_HEADER, orderedStatus, "@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER));
		
		waitForExtJSAjaxComplete(5);
		
		save(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER)), "Saved successfully");
		
		waitForExtJSAjaxComplete(10);
				
		String purchaseOrdersWindowID = getTenDigitWindowHeaderID(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		System.err.println("purchase Orders Window ID is : " + purchaseOrdersWindowID);
		
		Grid.checkRowInGriByTextValueAndClick(driver, PURCHASE_ORDERS_WINDOW_CLASS, productReference);
				
		waitForExtJSAjaxComplete(5);
		
		int orderQuantityInPO = getOrderQty(EDIT_PURCHASE_ORDER_WINDOW_HEADER, "1", "Line Items");
		
		clickGoodsReceiptTab();
		
		waitForExtJSAjaxComplete(5);
		
		String grIDFromGRTab = getGoodsReceiptIDFromGoodsReceiptTab(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		Reporter.log("Goods Receipt ID From GR Tab is "+grIDFromGRTab, true);
		
		waitForExtJSAjaxComplete(5);
		
		String remarksFromGRTab = getRemarksFromGoodsReceiptTab(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		Reporter.log("Remarks Value in GR window is "+remarksFromGRTab, true);
		
		close(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		Reporter.log("Navigate to Goods Receipt <br>", true);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_GOODS_RECEIPT);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForMaskDisappear();
		
		//Filter PO Lines Grid with Line ID
		filterGrid("@id", getFilterGridID(), grIDFromGRTab, "Header ID");
						
		//Open the Transactions Grid Record
		//openTransactionLine("@id", getFilterGridID(), grIDFromGRTab, "Header ID");
		Grid.checkRowInGriByTextValueAndClick(driver, grIDFromGRTab);
		
		waitForExtJSAjaxComplete(20);
							
		clickEditButtoninPO("Goods Receipts");	
		
		waitForExtJSAjaxComplete(20);
		
		TransactionsPageObject transactionsPO = new TransactionsPageObject();
		
		String warehouseFromGRWindow = transactionsPO.getDefaultWarehouseValue(GOODS_RECEIPT_WINDOW_HEADER);
		Reporter.log("Warehouse Value in GR window is "+warehouseFromGRWindow, true);
		
		softAssert.assertEquals(warehouseRef, warehouseFromGRWindow, "Warehouse is same in PO and Goods Receipt");
		
		String remarksFromGRWindow = transactionsPO.getRemarksOfGoodsReceiptWindow(GOODS_RECEIPT_WINDOW_HEADER);
		Reporter.log("Remarks Value in GR window is "+remarksFromGRWindow, true);
		
		softAssert.assertEquals(remarksFromGRTab, remarksFromGRWindow, "Goods Receipt Remarks is same in PO and Goods Receipt");
		
		Reporter.log("Order Quantity in PO window is "+orderQuantityInPO, true);
		
		String orderQuantityinGR = transactionsPO.getQuantityOrderedOfTransactionLine("1", GR_TRANSACTION_LINE_QUANTITY_CLASS);
		
		int intorderQuantityinGR = Integer.parseInt(orderQuantityinGR);
		Reporter.log("Transaction Line Order Quantity in GR window is "+intorderQuantityinGR, true);
		
		softAssert.assertEquals(orderQuantityInPO, intorderQuantityinGR, "PO Order Quantity is displayed in Quantity Ordered Field of GR Window");
		
		waitForExtJSAjaxComplete(5);
			
		transactionsPO.setStatus("Planned", GOODS_RECEIPT_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		transactionsPO.setTransactionDate(expectedDate,GOODS_RECEIPT_WINDOW_HEADER);
		waitForExtJSAjaxComplete(5);
		
		transactionsPO.setTransactionLineQuantity("1", lineQuantity);
		
		transactionsPO.setTransactionLineLocationFromAllLocations("1", wareHouseLoc, referenceColName);
		
		transactionsPO.save();
		
		waitForExtJSAjaxComplete(10);
		
		transactionsPO.setStatus("Executed", GOODS_RECEIPT_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		transactionsPO.save();
		
		waitForExtJSAjaxComplete(10);
		
		transactionsPO.close(GOODS_RECEIPT_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		Reporter.log("Navigate to Warehouses <br>", true);

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
		
		waitForExtJSAjaxComplete(20);
		
		int availableStockValuePostTxn1 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(addWarehousePageObject.getProductRowNumberInWareHouse(productReference),availableStockColName ));
		int reservedStockValuePostTxn1 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(addWarehousePageObject.getProductRowNumberInWareHouse(productReference),reservedStockColName));
		int totalStockValuePostTxn1 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(addWarehousePageObject.getProductRowNumberInWareHouse(productReference),totalStockColName));
		int blockStockValuePostTxn1 = Integer.parseInt(addWarehousePageObject.getStockItemsCellValue(addWarehousePageObject.getProductRowNumberInWareHouse(productReference),blockedStockColName));
		
		softAssert.assertTrue((availableStockValuePostTxn1==(availableStockValue+(intLineQuantity))), productReference+" available stock quantity is increased by "+lineQuantity+"units");
		softAssert.assertTrue((reservedStockValuePostTxn1 ==reservedStockValue), productReference+" reserved stock quantity is unchanged");
		softAssert.assertTrue((totalStockValuePostTxn1==(totalStockValue+(intLineQuantity))), productReference+" Total stock quantity is is increased by "+lineQuantity+"units");
		softAssert.assertTrue((blockStockValuePostTxn1==blockedStockValue), productReference+" Block stock quantity is unchanged");
		
		addWarehousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("PO creates Goods Receipt Transaction and Goods receipt increases Available and Total Stock value", true);
	}
	
	
	/**
	 * TestCase ID : C50877
	 * Author : ssa 
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testPRRequestedStatusWhenPOIsCanceledFromOrdered() throws Exception {
		Reporter.log(
				"<span style='font-weight:bold;color:#000033'> C50877: Verify all PR Lines in the PO will get status “Requested” again when the PO gets Canceled from Ordered status</span><br>",true);
		
		
		String warehouseRef = "PrPoTransactionWh1";
		String referenceColName = "Reference";
		String requisitionDate = this.getFutureDate(0);
		String productReference = "aqapreProdRef1";
		String documentType = "AutomationQaPrRef";
		String requester = "SELENIUM";
		String purchasingOrganization = "aqaPOrgRef"; 
		String purchasingGrp = "aqaPOrgRef"; 
		String status = "Request";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testPRRequestedStatusWhenPOIsCanceledFromPlanned");
		
		PurchaseRequisitionsPageObject purchaseRequisitionPageObject = new PurchaseRequisitionsPageObject();
		
		waitForExtJSAjaxComplete(30);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_REQUISITIONS);
		
		waitForExtJSAjaxComplete(20);
		
		purchaseRequisitionPageObject.selectWareHouse(warehouseRef);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.clickAddButton();
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setDocumentType(referenceColName, documentType, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setRequisitionDate(requisitionDate, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setRequester( "Last Name", requester, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.addPRLine(ADD_REQUISIION_WINDOW_HEADER, productReference, referenceColName);
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValue(driver, productReference);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.clickTrackingTab(ADD_REQUISIION_WINDOW_HEADER); 
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setPurchasingOrganization(referenceColName, purchasingOrganization, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.setPurchasingGroup(referenceColName, purchasingGrp, ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		purchaseRequisitionPageObject.save(ADD_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISIION_WINDOW_HEADER)), "Saved successfully");
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.setStatus(status, EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.save(EDIT_REQUISIION_WINDOW_HEADER);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_REQUISIION_WINDOW_HEADER)), "Saved successfully");
		
		String requisitionWindowID1 = getTenDigitWindowHeaderID(EDIT_REQUISIION_WINDOW_HEADER);
		
		System.err.println("Requisition Window ID 1 is : " + requisitionWindowID1);
		
		waitForExtJSAjaxComplete(5);
		
		String creationDate = purchaseRequisitionPageObject.getCreationDate(EDIT_REQUISIION_WINDOW_HEADER, "1");
		
		waitForExtJSAjaxComplete(25);
		
		purchaseRequisitionPageObject.close(EDIT_REQUISIION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(30);
		
		Reporter.log("Navigate to Purchase Orders <br>", true);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASE_ORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		//******Variable Initialization******************
		String reference = "ref"+ getCurrentDate().substring(getCurrentDate().length()-5);
		String expectedOrderDate = creationDate;
		String plannedStatus = "Planned";
		String supplier = "aqacompName";
		String contactPerson = "SELENIUM";
		String documentTypePO = "AutomationQaPoRef";
		String orderedStatus = "Ordered";
		String canceledStatus = "Canceled";
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(10);
		
		String defaultStatus = getStatus(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		softAssert.assertEquals(defaultStatus, plannedStatus, "The initial Status of Po is Planned");
		
		String defaultExpectedOrderDate = getExpectedOrderDate(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		softAssert.assertEquals(defaultExpectedOrderDate, expectedOrderDate, "Expected Order Date is automatically filled in with the Date of Creation");
		
		setExpectedOrderDate(expectedOrderDate, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDocumentType(documentTypePO, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectSupplier(supplier, "Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingOrganization(purchasingOrganization, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectPurchasingGroup(purchasingGrp, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectDeliveryWarehouse(warehouseRef, referenceColName, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		selectContactPerson(contactPerson, "Last Name", ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		setReference(reference, ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		clickAddPOLineButton();
		
		MenuSelector.selectMenuItemNative(driver, "from Purchase Requisitions"); 
		
		selectPurchaseRequisitionLines("@id", getXWindowId(PURCHASE_REQUISITIONS_LINES_WINDOW_HEADER), requisitionWindowID1, "Purchase Requisition ID", "purch_req_line");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, PURCHASE_ORDERS_WINDOW_CLASS, productReference);
		
		waitForExtJSAjaxComplete(5);
		
		clickRequisitionsTab();
		
		//Verification of PO adds Requisitions line for Each PR line
		String prLinesID1 = getIDFromRequisitionsTab(ADD_PURCHASE_ORDER_WINDOW_HEADER, "1");
		Reporter.log("ID 1 in PO Requisitions Tab is "+prLinesID1, true);
		
		softAssert.assertEquals(requisitionWindowID1, prLinesID1, "PO Line is added for Purchase Requisition Line 1");
		
		waitForExtJSAjaxComplete(5);
		
		save(ADD_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		String lineIDPOLines = getLineIDFromPOLines(EDIT_PURCHASE_ORDER_WINDOW_HEADER, "1", "Line Items");
		
		Reporter.log("Line ID of the Po line is: "+lineIDPOLines, true);
		
		setStatusValue(EDIT_PURCHASE_ORDER_WINDOW_HEADER, "Submitted", "@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER));
		
		waitForExtJSAjaxComplete(20);
		
		save(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		setStatusValue(EDIT_PURCHASE_ORDER_WINDOW_HEADER, "Ordered", "@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER));
		
		waitForExtJSAjaxComplete(30);
		
		save(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		setStatusValue(EDIT_PURCHASE_ORDER_WINDOW_HEADER, "Canceled", "@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER));
		
		waitForExtJSAjaxComplete(30);
		
		save(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_PURCHASE_ORDER_WINDOW_HEADER)), "Saved successfully");
		
		close(EDIT_PURCHASE_ORDER_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(30);
		
		//***** Verification of Requested Status for PR lines
		
		waitAndClick(XPATH_PURCHASE_REQUISITIONS);
		
		waitForExtJSAjaxComplete(30);
		
		purchaseRequisitionPageObject.selectWareHouse(warehouseRef);
		
		waitForExtJSAjaxComplete(10);
		
		purchaseRequisitionPageObject.clearFilters();
		
		waitForExtJSAjaxComplete(10);
		
		String requestedStatus = "Requested";
		
		String statusEle1 = purchaseRequisitionPageObject.getPRLineStatus("@id", purchaseRequisitionPageObject.getFilterGridID(), requisitionWindowID1, "Requisition ID");
		
		Reporter.log("Status of the PR line '"+requisitionWindowID1+"' is: "+statusEle1, true);
		
		softAssert.assertEquals(statusEle1, requestedStatus, "PR Lines are in Requested status when the PO gets canceled From Planned Status");
		
		softAssert.assertAll();
		
		Reporter.log("PR lines gets Requested status when the PO gets cancelled From Planned status and sumitted status", true);
	}
	
	
}
