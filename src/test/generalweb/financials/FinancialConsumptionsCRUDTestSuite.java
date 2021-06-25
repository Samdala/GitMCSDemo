package test.generalweb.financials;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class FinancialConsumptionsCRUDTestSuite extends FinancialConsumptionsPageObject{

	/**
	 * Testcase ID		: C101558, C101577
	 * Author			: kve
	 **/
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testConsumptionsEditDeleteAndAddPropertiesToGridAndFilter() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"Test C101558: Edit/Delete Consumptions </span>"
				+"Test C101577: Consumption ID and Cost Item ID are added to the grid and the filter properties  </span> <br>", true);


		String CostCarrierType = "Call";
		String CostCarrierType1 = "Work Order";

		String warningMsg="Please select an item";

		SoftAssert softAssert = new SoftAssert(); 
		softAssert.setMethodName("testConsumptionsEditDeleteAndAddPropertiesToGridAndFilter");

		FinancialPurchaseRequisitionsPageObject probj=new FinancialPurchaseRequisitionsPageObject();

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Financials");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_CONSUMPTIONS);

		waitForExtJSAjaxComplete(20);

		clickChangeVisibleColumns();

		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(Grid.isRowInGridAbsent(driver,"Consumption ID"), "Consumption ID is displayed in the 'General Properties' category");

		softAssert.assertFalse(Grid.isRowInGridAbsent(driver,"Cost Item ID"), "Cost Item ID is displayed in the 'General Properties' category");

		changeVisibleColumns("General Properties", "General Properties", "Consumption ID", "Consumption ID");

		changeVisibleColumns("General Properties", "General Properties", "Cost Item ID", "Cost Item ID");

		waitForExtJSAjaxComplete(20);

		//save("Change visible columns");
		clickSave();

		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(Grid.isRowInGridAbsent(driver,"Consumption ID"), "Consumption ID is added to the Consumption grid");

		softAssert.assertFalse(Grid.isRowInGridAbsent(driver,"Cost Item ID"), "Cost Item ID is added to the Consumption grid");

		waitForExtJSAjaxComplete(20);

		clickFilterResults();

		waitForExtJSAjaxComplete(20);

		expandFilterProperties("x-window x-resizable-pinned", "General Properties", "Consumption ID");

		waitForExtJSAjaxComplete(20);

		String ConsumptionIdForFilters = getCellText("@id", getXWindowIdByClass("x-panel financials"), "2", "Consumption ID");

		setValue("Enter a Consumption ID", ConsumptionIdForFilters);

		waitForExtJSAjaxComplete(20);

		clickSave();

		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(Grid.isRowInGridAbsent(driver, ConsumptionIdForFilters), "Consumption record with selected Consumption ID value is displayed in the grid.");

		clickFilterResults();

		waitForExtJSAjaxComplete(20);

		clickRemoveRestriction();

		waitForExtJSAjaxComplete(20);

		clickSave();

		waitForExtJSAjaxComplete(20);

		probj.clickEditButton();

		waitForExtJSAjaxComplete(20);

		String text=getWarningDialogTextMsg();

		softAssert.assertTrue(text.contains(warningMsg),"Error message Please Select an Item is displayed.");

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("OK"); 

		waitForExtJSAjaxComplete(20);

		filterGridWithoutUsingMcsElement("@class", "x-grid3-viewport", CostCarrierType, "Cost Carrier Type");

		String call = getCellText("@id", getXWindowIdByClass("x-panel financials"), "1", "Cost Carrier Type");

		Grid.checkRowInGriByTextValueAndClick(driver, call);

		waitForExtJSAjaxComplete(20);

		probj.clickEditButton();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isConsumtionsEditWindowOpen(),"Consumptions Edit form is opened");

		waitForExtJSAjaxComplete(20);

		setQuantity("10");

		waitForExtJSAjaxComplete(20);

		saveConsumption();
		//clickSave();

		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValueAndClick(driver, call);

		waitForExtJSAjaxComplete(20);

		probj.clickDeleteButton();

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(10);

		clickOnDialogButton("OK");

		waitForExtJSAjaxComplete(20); 

		filterGridWithoutUsingMcsElement("@class", "x-grid3-viewport", CostCarrierType1, "Cost Carrier Type");

		String workorder = getCellText("@id", getXWindowIdByClass("x-panel financials"), "1", "Cost Carrier Type");

		Grid.checkRowInGriByTextValueAndClick(driver, workorder);

		waitForExtJSAjaxComplete(20);

		probj.clickEditButton();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isConsumtionsEditWindowOpen(),"Consumptions Edit form is opened");

		waitForExtJSAjaxComplete(20);

		setQuantity("10");

		waitForExtJSAjaxComplete(20);

		saveConsumption();

		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValueAndClick(driver, workorder);

		waitForExtJSAjaxComplete(20);

		probj.clickDeleteButton();

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(10);

		clickOnDialogButton("OK");

		waitForExtJSAjaxComplete(20);

		softAssert.assertAll();

		Reporter.log("Financial Consumptions was successfully edited, deleted and added 'Consumption ID' and 'Cost Item ID' to the grid and the filter properties ", true);
	}

}
