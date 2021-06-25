package test.generalweb.financials;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class FinancialPurchaseRequisitionsCRUDTestSuite extends FinancialPurchaseRequisitionsPageObject {
	
	/**
	 * Testcase ID		: C101565
	 * Author			: kve
	 * */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testPurchaseRequsitionAddEditDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"Test: C101565: Add/Edit/Delete Purchase Requisition </span><br>", true);

		String unitsMeasureRef = "piece";
		String description = "C52423Desc" + getCurrentDate().substring(getCurrentDate().length()-5);
		String savedMsg = "Saved successfully";
		String costCategory = "1preCostCtRef";
		String reqStatus = "Requested";
		String reqStatusEd = "Needed";
		String reqStatusToSet = "Request";
		String reqStatusToSetEd = "Rework";
		String referenceColName = "Reference";
		String requisitionDate = this.getFutureDate(0);
		String productReference = "2preConsRef";
		String productReference1 = "4preConsRef";
		String documentType = "Std Purchase Req";
		String requester = "SELENIUM";
		String purchasingOrganization = "AQAPurchOrg";
		String orderQuantity = "10";
		String unitPrice = "10";
		String currency = "EUR";
		String errorMsg = "Unable to save: Please select a Purchasing Group";
		String unitPriceEd = "20";
		String orderQuantityEd = "20";
		String currencyEd = "EUR";
		String descriptionEd = "C52423DescEd" + getCurrentDate().substring(getCurrentDate().length()-5);
		String costCategoryEd = "Aqa_Cost_Cat_ref";
		String purchasingOrganizationEd = "aqaPOrgRef";


		SoftAssert softAssert = new SoftAssert(); 

		softAssert.setMethodName("testPurchaseRequsitionAddEditDelete");

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Financials");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_PURCHASE_REQUISITIONS);

		waitForExtJSAjaxComplete(20);

		clickAddButton();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isPurchaseRequisitionWindowOpen()," New Purchase Requisition form is opened");

		waitForExtJSAjaxComplete(5);

		//Add New Purchase Requisition

		setDocumentType(referenceColName, documentType, ADD_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(5);

		setRequisitionDate(requisitionDate, ADD_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(5);

		setRequester( "Last Name", requester, ADD_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(5);

		addPRLine(ADD_REQUISIION_WINDOW_HEADER , productReference, referenceColName);

		waitForExtJSAjaxComplete(25);

		save(ADD_REQUISIION_WINDOW_HEADER);

		String infoErrorMsg = getInfoBarMsgOfWindow("@id",getXWindowId(EDIT_REQUISIION_WINDOW_HEADER));

		softAssert.assertEquals(infoErrorMsg, errorMsg, "Warning message appears, New record is not saved and All required fields that are not filled in are highlighted in red");

		Grid.checkRowInGriByTextValueAndClick(driver, productReference);

		waitForExtJSAjaxComplete(25);

		selectPRLine(ADD_REQUISIION_WINDOW_HEADER , "1");

		waitForExtJSAjaxComplete(25);

		//General tab actions
		clickGeneralTab(ADD_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(10);

		setLineNr("1", ADD_REQUISIION_WINDOW_HEADER) ;

		setOrderQuantity(orderQuantity, ADD_REQUISIION_WINDOW_HEADER);

		setUnitPrice(unitPrice ,  ADD_REQUISIION_WINDOW_HEADER); 

		setOrderUOM(referenceColName, unitsMeasureRef,  ADD_REQUISIION_WINDOW_HEADER);

		selectCurrency( currency,  ADD_REQUISIION_WINDOW_HEADER);

		//Description tab actions

		clickDescriptionTab(ADD_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(5);

		setDescription( description,ADD_REQUISIION_WINDOW_HEADER) ;

		//Financial tab actions

		clickFinancialTab(ADD_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(5);

		setCostCategory(costCategory, ADD_REQUISIION_WINDOW_HEADER);

		//Tracking tab actions
		clickTrackingTab(ADD_REQUISIION_WINDOW_HEADER); 

		waitForExtJSAjaxComplete(5);

		setPurchasingOrganization(referenceColName, purchasingOrganization, ADD_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(5);

		setPurchasingGroup(referenceColName, purchasingOrganization, ADD_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(5);

		save(ADD_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		String infoBarMsg = getInfoBarMsgOfWindow("@id",getXWindowId(EDIT_REQUISIION_WINDOW_HEADER));

		softAssert.assertEquals(infoBarMsg, savedMsg, "Purchase Requisiton is Saved successfully");

		waitForExtJSAjaxComplete(20);

		setStatus(reqStatusToSet, EDIT_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(10);

		save(EDIT_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(20);

		String statusOFReq = getStatus(EDIT_REQUISIION_WINDOW_HEADER);

		softAssert.assertEquals(statusOFReq,reqStatus ," Requsition is in Requested status");

		waitForExtJSAjaxComplete(20);

		setStatus(reqStatusToSetEd, EDIT_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(10);

		save(EDIT_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(20);

		String statusOFReqEd = getStatus(EDIT_REQUISIION_WINDOW_HEADER);

		softAssert.assertEquals(statusOFReqEd,reqStatusEd ," Requsition is in Needed status");

		waitForExtJSAjaxComplete(20);

		String requistionLineID  = getRequisitionLineID(EDIT_REQUISIION_WINDOW_HEADER,"1");

		close(EDIT_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(20);

		//Edit Purchase Requisition

		Grid.checkRowInGriByTextValue(driver, requistionLineID);

		waitForExtJSAjaxComplete(10);

		clickEditButton();

		waitForExtJSAjaxComplete(10);

		addPRLine(EDIT_REQUISIION_WINDOW_HEADER , productReference1, referenceColName);

		waitForExtJSAjaxComplete(20);

		clickOnDeletePRLineButton(EDIT_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValueAndClick(driver, productReference);

		waitForExtJSAjaxComplete(20);

		clickGeneralTab(EDIT_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(10);

		setLineNr("2", EDIT_REQUISIION_WINDOW_HEADER) ;

		setOrderQuantity(orderQuantityEd, EDIT_REQUISIION_WINDOW_HEADER);

		setUnitPrice(unitPriceEd, EDIT_REQUISIION_WINDOW_HEADER); 

		selectCurrency( currencyEd, EDIT_REQUISIION_WINDOW_HEADER);

		//Description tab actions

		clickDescriptionTab(EDIT_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(5);

		setDescription(descriptionEd,EDIT_REQUISIION_WINDOW_HEADER) ;

		//Financial tab actions

		clickFinancialTab(EDIT_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(5);

		setCostCategory(costCategoryEd, EDIT_REQUISIION_WINDOW_HEADER);

		//Financial tab actions
		clickTrackingTab(EDIT_REQUISIION_WINDOW_HEADER); 

		waitForExtJSAjaxComplete(5);

		setPurchasingOrganization(referenceColName, purchasingOrganizationEd, EDIT_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(10);

		setPurchasingGroup(referenceColName, purchasingOrganizationEd, EDIT_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(25);

		save(EDIT_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		String infoBarMsg1 = getInfoBarMsgOfWindow("@id",getXWindowId(EDIT_REQUISIION_WINDOW_HEADER));

		softAssert.assertEquals(infoBarMsg1, savedMsg, "Purchase Requisiton is Edited successfully");

		waitForExtJSAjaxComplete(20);

		Reporter.log("Requsition line id is"+requistionLineID, true);

		close(EDIT_REQUISIION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(20);

		//Delete Purchase Requisition

		Grid.checkRowInGriByTextValue(driver, requistionLineID);

		waitForExtJSAjaxComplete(10);

		clickDeleteButton();

		waitForExtJSAjaxComplete(10);

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(10);

		clickOnDialogButton("OK");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		boolean isReqDeleted = Grid.isRowInGridAbsent(driver, requistionLineID,"mogrid", "@realID");

		softAssert.assertTrue(isReqDeleted, "Purchase requistion with Requested status is deleted");

		softAssert.assertAll();

		Reporter.log("Financial Purchase requistion was succesfully added, edited and deleted", true);
	}

}
