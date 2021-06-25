package test.generalweb.financials;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class FinancialPurchaseOrdersCRUDTestSuite extends FinancialPurchaseOrdersPageObject {

	/**
	 * Testcase ID		: C101567
	 * Author			: kve
	 **/
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testPurchaseOrdersAddEditDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"Test C101567:  Add/Edit/Delete Purchase Orders </span><br>", true);


		String savedMsg = "Saved successfully";
		String productReference1 = "aqapreProdRef1";
		String documentType = "AutomationQaPoRef";
		String supplier = "aqacompName";
		String purchasingOrganization = "aqaPOrgRef";
		String deliveryWarehouse = "PrPoTransactionWh";
		String orderDate = this.getFutureDate(0);
		String person = "SELENIUM";
		String person1 = "auto test right Last";
		String errorMsg = "There must be at least one line in a Purchase Order";
		String referenceColName = "Reference";
		String suppliernameColName = "Name";
		String productReference = "aqapreProdRef";
		String refer = "002";
		String unitPriceEd = "20";
		String orderQuantityEd = "20";
		String currencyEd = "EUR";
		String descriptionEd = "aqapreProdRef1Ed" + getCurrentDate().substring(getCurrentDate().length()-5);


		SoftAssert softAssert = new SoftAssert(); 
		softAssert.setMethodName("testPurchaseOrdersAddEditDelete");

		FinancialPurchaseRequisitionsPageObject probj=new FinancialPurchaseRequisitionsPageObject();

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Financials");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_PURCHASE_ORDERS);

		waitForExtJSAjaxComplete(20);

		probj.clickAddButton();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isPurchaseOrderWindowOpen()," New Purchase Requisition form is opened");

		waitForExtJSAjaxComplete(5);

		//Add New Purchase Order

		setReference(refer, ADD_ORDER_WINDOW_HEADER);

		probj.setDocumentType(referenceColName, documentType, ADD_ORDER_WINDOW_HEADER);

		waitForExtJSAjaxComplete(15);

		setExpectedOrderDate(orderDate, ADD_ORDER_WINDOW_HEADER);

		waitForExtJSAjaxComplete(15);

		selectSupplier(supplier, suppliernameColName, ADD_ORDER_WINDOW_HEADER);

		waitForExtJSAjaxComplete(15);

		probj.setPurchasingOrganization(referenceColName, purchasingOrganization, ADD_ORDER_WINDOW_HEADER);

		waitForExtJSAjaxComplete(15);

		selectDeliveryWarehouse(deliveryWarehouse, referenceColName, ADD_ORDER_WINDOW_HEADER);

		waitForExtJSAjaxComplete(15);

		selectContactPerson( person, "Last Name", ADD_ORDER_WINDOW_HEADER);

		waitForExtJSAjaxComplete(15);

		probj.save(EDIT_ORDER_WINDOW_HEADER);

		waitForExtJSAjaxComplete(15);

		String infoErrorMsg = getInfoBarMsgOfWindow("@id",getXWindowId(ADD_ORDER_WINDOW_HEADER));

		softAssert.assertEquals(infoErrorMsg, errorMsg, "Warning message appears, New record is not saved and All required fields that are not filled in are highlighted in red");

		waitForExtJSAjaxComplete(15);

		addPRLine(ADD_ORDER_WINDOW_HEADER , productReference, referenceColName);

		waitForExtJSAjaxComplete(15);

		checkRowInGridByTextValueAndClick(productReference);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		probj.save(EDIT_ORDER_WINDOW_HEADER);

		//String orderID  = getOrderID(EDIT_ORDER_WINDOW_HEADER,"1");
		String orderID  = getLineIDFromPOLines(EDIT_PURCHASE_ORDER_WINDOW_HEADER, "1", "Line Items");

		probj.close(EDIT_ORDER_WINDOW_HEADER);

		waitForExtJSAjaxComplete(20);

		//Edit Purchase Orders

		Grid.checkRowInGriByTextValue(driver, orderID);

		waitForExtJSAjaxComplete(10);

		probj.clickEditButton();

		waitForExtJSAjaxComplete(20);

		selectDeliveryWarehouse(deliveryWarehouse, referenceColName, EDIT_ORDER_WINDOW_HEADER);

		waitForExtJSAjaxComplete(15);

		selectContactPerson( person1, "Last Name", EDIT_ORDER_WINDOW_HEADER);

		waitForExtJSAjaxComplete(15);

		addPRLine(EDIT_ORDER_WINDOW_HEADER , productReference1, referenceColName);

		waitForExtJSAjaxComplete(20);

		//Delete Line Item

		checkRowInGridByTextValueAndClick(productReference1);

		waitForExtJSAjaxComplete(20);

		probj.clickOnDeletePRLineButton(EDIT_ORDER_WINDOW_HEADER);

		waitForExtJSAjaxComplete(20);

		checkRowInGridByTextValueAndClick(productReference);

		waitForExtJSAjaxComplete(20);

		probj.clickGeneralTab(EDIT_ORDER_WINDOW_HEADER);

		waitForExtJSAjaxComplete(10);

		probj.setLineNr("2", EDIT_ORDER_WINDOW_HEADER) ;

		probj.setOrderQuantity(orderQuantityEd, EDIT_ORDER_WINDOW_HEADER);

		probj.setUnitPrice(unitPriceEd, EDIT_ORDER_WINDOW_HEADER); 

		probj.selectCurrency(currencyEd, EDIT_ORDER_WINDOW_HEADER);

		//Description tab actions

		probj.clickDescriptionTab(EDIT_ORDER_WINDOW_HEADER);

		waitForExtJSAjaxComplete(5);

		probj.setDescription(descriptionEd,EDIT_ORDER_WINDOW_HEADER) ;

		probj.save(EDIT_ORDER_WINDOW_HEADER);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		String infoBarMsg1 = getInfoBarMsgOfWindow("@id",getXWindowId(EDIT_ORDER_WINDOW_HEADER));

		softAssert.assertEquals(infoBarMsg1, savedMsg, "Purchase Order is Edited successfully");

		Reporter.log("Requsition line id is"+orderID, true);

		probj.close(EDIT_ORDER_WINDOW_HEADER);

		softAssert.assertAll();

		Reporter.log("Financial Purchase Orders was succesfully added, edited and deleted", true);
	}
}
