package test.energy.supplypoints;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class SupplyPointsBusinessPartnersCRUDTest extends
	MeteringSuplyPointsPageObject {
	
	 @Test(enabled=true)
		public void testSupplyPointBusinessPartnerCreateEdit() throws IOException  {

			Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Create and Edit Supply Point Business Partners: C39677, C30022" + " </span><br>",
					true);

			Reporter.log("User creates and edits Supply Point Business Partners:  "  + " <br>",
					true);
			
			
			String formId = null;
			
			String supplyPointReferece = "codeNonUnique";
			
			String effectiveDate = "01-01-2014";
			String commoditySource = "1preBPcmdtSource";
			String supplier = "My Company";
			String renewableEnergy = "25";
			String DNO = "My Company";
			String contractReference = "Contract Reference Name";
			String tariffCalendar = "1preBPTrfClndr";
			String priceTemplate = "1preBPPrcTmplt";
			String notes = "Some notes";
			
			String effectiveDateEdited = "05-05-2014";
			String commoditySourceEdited = "2preBPcmdtSource";
			/*String supplierEdited = "2preCompName";
			String renewableEnergyEdited = "10";
			String contractReferenceEdited = "Contract Reference Name Edited";*/
			
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("meteringSupplyPointEdit");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);

			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			expandMetering();
			
			waitForExtJSAjaxComplete(25);
			
			openMeteringEntity("Supply Points");
			
			waitForExtJSAjaxComplete(5);
			
			waitForExtJSAjaxComplete(25);
			
			Grid.checkRowInGriByTextValueAndClick(driver, supplyPointReferece);
			
			clickButtonOnOverview("Edit");	
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			clickBusinessPartnersTab();
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			clickButtonOnForm(DIALOG_SP, "Add");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			formId = getXWindowId("Add Business");
			
			setFieldValueByFormId(formId, "effectiveDate", effectiveDate);
			
			setLookupValueByFormId(formId, "source", commoditySource);
			
			setLookupValueByFormId(formId, "supplier", supplier);
			
			setLookupValueByFormId(formId, "dno", DNO);
			
			setFieldValueByFormId(formId, "renewableEnergy", renewableEnergy);
			
			setLookupValueByFormId(formId, "tariffCalendar", tariffCalendar);
			
			setFieldValueByFormId(formId, "contractReference", contractReference);
			
			setFieldValueByFormId(formId, "notes", notes);
			
			setLookupValueByFormId(formId, "priceTemplate", priceTemplate);
			
			saveAndCloseByFormId(formId);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			Grid.checkRowInGriByTextValueAndClick(driver, DIALOG_SP, commoditySource);
			
			clickButtonOnForm(DIALOG_SP, "Edit");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			formId = getXWindowId("Edit Business");
			
			verifyFieldSavedOnFormByFormId(formId, "effectiveDate", effectiveDate);
			
			verifyFieldSavedOnFormByFormId(formId, "source", commoditySource);
			
			verifyFieldSavedOnFormByFormId(formId, "supplier", supplier);
			
			verifyFieldSavedOnFormByFormId(formId, "dno", DNO);
			
			verifyFieldSavedOnFormByFormId(formId, "renewableEnergy", renewableEnergy);
			
			verifyFieldSavedOnFormByFormId(formId, "tariffCalendar", tariffCalendar);
			
			verifyFieldSavedOnFormByFormId(formId, "contractReference", contractReference);
			
			verifyFieldSavedOnFormByFormId(formId, "notes", notes);
			
			verifyFieldSavedOnFormByFormId(formId, "priceTemplate", priceTemplate);
			
			setFieldValueByFormId(formId, "effectiveDate", effectiveDateEdited);
			
			/*setLookupValueByFormId(formId, "source", commoditySourceEdited);
			
			setLookupValueByFormId(formId, "supplier", supplierEdited);
			
			setFieldValueByFormId(formId, "renewableEnergy", renewableEnergyEdited);
			
			setFieldValueByFormId(formId, "contractReference", contractReferenceEdited);*/
			
			saveAndCloseByFormId(formId);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			Grid.checkRowInGriByTextValueAndClick(driver, DIALOG_SP, effectiveDateEdited);
			
			clickButtonOnForm(DIALOG_SP, "Edit");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			formId = getXWindowId("Edit Business");
			
			verifyFieldSavedOnFormByFormId(formId, "effectiveDate", effectiveDateEdited);
			
			//verifyFieldSavedOnFormByFormId(formId, "source", commoditySourceEdited);
			
			//verifyFieldSavedOnFormByFormId(formId, "supplier", supplierEdited);
			
			verifyFieldSavedOnFormByFormId(formId, "dno", DNO);
			
			//verifyFieldSavedOnFormByFormId(formId, "renewableEnergy", renewableEnergyEdited);
			
			verifyFieldSavedOnFormByFormId(formId, "tariffCalendar", tariffCalendar);
			
//			/verifyFieldSavedOnFormByFormId(formId, "contractReference", contractReferenceEdited);
			
			verifyFieldSavedOnFormByFormId(formId, "notes", notes);
			
			verifyFieldSavedOnFormByFormId(formId, "priceTemplate", priceTemplate);
			
			softAssert.assertAll();
			
	 }

}
