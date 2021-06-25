package test.energy.meters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.McsElement;

public class MetersFormValidationTestSuite extends
		MetersPageObject {


	 @Test(enabled=true)
	public void testMeterCreateEdit() throws IOException  {

		 //TODO Change to proper later
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Check Meter form validation: C30188" + " </span><br>",
				true);

		Reporter.log("User tries to create Meter with different validations"  + " <br>",
				true);
		
		//Field values for Meter creation
		
		String meterCode = "2preMeter";
		String reference = "test auto mtr " + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto mtr " + getCurrentDate().substring(getCurrentDate().length()-6);
		String status = "Active";
		String timeZone = "(GMT+02:00) Athens, Bucharest, Istanbul";
		String commodity = "Water";

		//Navigator
		
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite2";
		String supplyPointName = "EAN-1preSPWater";
		
		List<String> mandatoryFieldsErrorMessageKeyWordsCheck =Arrays.asList("form", "invalid");
		List<String> unqiueCodeFieldErrorMessageKeyWordsCheck =Arrays.asList("Code", "exists");
		List<String> unqiueReferenceFieldErrorMessageKeyWordsCheck =Arrays.asList("Reference", "exists");
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("MeterFormValidation");

		//Navigate to Meters Infrastructure
	
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickMetersInfrastructureTab();
		
		waitForExtJSAjaxComplete(20);
		
		setEffectiveDate("01-01-2016");
		
		waitForExtJSAjaxComplete(25);
		
		setCommodityClass("Water");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickOnSupplyPoint(supplyPointName);
				
		waitForExtJSAjaxComplete(20);
		
		clickCreateMeter();
		
		waitForExtJSAjaxComplete(20);
		
		//Check if Status field is prefilled with Status marked as Default
		
		Reporter.log("Check if Status field is prefilled with Status marked as Default", true);
		
		String fieldValue = getFieldValue(DIALOG_METER, "status");
		
		clickLookup(DIALOG_METER, "status");
		
		String lookupId = getXWindowId("Select a Metering Object Status");
		
		softAssert.assertTrue(checkDefaultField("@id", lookupId, "Reference", fieldValue), "Status field is prefilled with Status marked as Default");
		
		clickCancelXwindow();
		
		//Check if Time Zone field is prefilled with Time Zone marked as Default
		
		Reporter.log("Check if Time Zone field is prefilled with Time Zone marked as Default", true);
		
		softAssert.assertEquals(getFieldValue(DIALOG_METER, "timezone"), "(GMT+01:00) Brussels, Copenhagen, Madrid, Paris", "Time Zone field is prefilled with default time zone");
		
		//Check UI of mandatory fields (bold text, asterisk)
		
		Reporter.log("Check UI of mandatory fields (bold text, asterisk)", true);
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_METER, "code"), "Code field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_METER, "reference"), "Reference field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_METER, "commodity"), "Commodity field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_METER, "timezone"), "Time Zone field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_METER, "status"), "Status field has correct UI (Bold text, asterix)");
		
		//Check UI of non-mandatory fields (regular text)
		
		Reporter.log("Check UI of non-mandatory fields (regular text)", true);
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_METER, "meterOperator"), "Meter Operator field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_METER, "commissioningDate"), "Commissioning Date field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_METER, "model"), "Model field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_METER, "serialNumber"), "Serial Number field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_METER, "location"), "Physical Location field has correct UI");
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_METER, "manufacturer"), "Manufacturer field has correct UI");
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_METER, "description"), "Description field has correct UI");
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_METER, "accessDirectives"), "Access Instructions has correct UI");
		
		//Try to save Meter Form with empty Reference field
		
		Reporter.log("Try to save Meter Form with empty Reference field", true);
		
		clearField("reference", "Reference");
		
		setCode(code);
		
		setCommodity(commodity);
		
		waitForExtJSAjaxComplete(20);
		
		save(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_METER),"Form can't be saved with empty Reference field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_METER, "reference"),"Red border is present on empty Reference field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_METER, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenMeterForm(supplyPointName);

		//Try to save Meter Form with empty Code field
		
		Reporter.log("Try to save Meter Form with empty Code field", true);
		
		clearField("code", "Code");
		
		setReference(reference+"1");
		
		setCommodity(commodity);
		
		waitForExtJSAjaxComplete(20);
		
		save(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_METER),"Form can't be saved with empty Code field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_METER, "code"),"Red border is present on empty Code field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_METER, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenMeterForm(supplyPointName);

		//Try to save Meter Form with empty Status field
		
		Reporter.log("Try to save Meter Form with empty Status field", true);
		
		clearField("status", "Status");
		
		setCode(code+"2");
		
		setReference(reference+"2");
		
		setCommodity(commodity);
		
		waitForExtJSAjaxComplete(20);
		
		save(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_METER),"Form can't be saved with empty Status field");
		
		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_METER, "status"),"Red border is present on empty Status field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_METER, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenMeterForm(supplyPointName);

		//Try to save Meter Form with empty Time Zone field
		
		Reporter.log("Try to save Meter Form with empty Time Zone field", true);
		
		clearField("timezone", "Time Zone");
		
		setStatus(status);
		
		setCode(code+"3");
		
		setReference(reference+"3");
		
		setCommodity(commodity);
		
		waitForExtJSAjaxComplete(20);
		
		save(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_METER),"Form can't be saved with empty Time Zone field"); 
		
		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_METER, "timezone"),"Red border is present on empty Time Zone field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_METER, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenMeterForm(supplyPointName);

		//Try to save Meter Form with empty Commodity field
		
		Reporter.log("Try to save Meter Form with empty Commodity field", true);
		
		clearField("commodity", "Commodity");
		
		setTimeZone(timeZone);
		
		setStatus(status);
		
		setCode(code+"4");
		
		setReference(reference+"4");
		
		waitForExtJSAjaxComplete(20);
		
		save(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_METER),"Form can't be saved with empty Commodity field"); 
		
		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_METER, "commodity"),"Red border is present on empty Commodity field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_METER, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenMeterForm(supplyPointName);
		
		//Verify Commodity lookup is limited by Commodity Class
		
		Reporter.log("Verify Commodity lookup is limited by Commodity Class", true);
		
		clickLookup(DIALOG_METER, "commodity");
		
		lookupId = getXWindowId("Select a Commodity");
		
		Reporter.log("Check if Commodity lookup is limited by Commodity Class selected on Meters Infrastructure", true);
		
		softAssert.assertFalse(setValueGridLookupWithFiltersWithValidation("@id", lookupId, "Electricity", "Reference"), "Electricity (Electricity cmdt class) commodity is not present on Commodity lookup");
		
		softAssert.assertFalse(setValueGridLookupWithFiltersWithValidation("@id", lookupId, "Natural Gas", "Reference"), "Natural Gas (Gas cmdt class) commodity is not present on Commodity lookup");
				
		softAssert.assertFalse(setValueGridLookupWithFiltersWithValidation("@id", lookupId, "Firewood", "Reference"), "Firewood (Heat cmdt class) commodity is not present on Commodity lookup");
		
		softAssert.assertFalse(setValueGridLookupWithFiltersWithValidation("@id", lookupId, "Notebooks", "Reference"), "Notebooks (Waste cmdt class) commodity is not present on Commodity lookup");
		
		softAssert.assertFalse(setValueGridLookupWithFiltersWithValidation("@id", lookupId, "Gasoline", "Reference"), "Gasoline (Fueld cmdt class) commodity is not present on Commodity lookup");
		
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		//Try to save Meter Form with non-unique Code field
		
		Reporter.log("Try to save Meter Form with non-unique Code field", true);
		
		setCode(meterCode);
		
		setReference(reference+"5");
		
		setTimeZone(timeZone);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodity(commodity);
		
		waitForExtJSAjaxComplete(20);
		
		setStatus(status);
		
		waitForExtJSAjaxComplete(20);
		
		save(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_METER),"Form can't be saved with non-unique Code field"); 
		
		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_METER, unqiueCodeFieldErrorMessageKeyWordsCheck),"Message about non-unique Code field is present"); 
		
		reopenMeterForm(supplyPointName);

		//Try to save Meter Form form with empty optional fields
		
		Reporter.log("Try to save Meter Form form with empty optional fields", true);
		
		setCode(code+"6");
		
		setReference(reference+"6");
		
		setTimeZone(timeZone);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodity(commodity);
		
		waitForExtJSAjaxComplete(20);
		
		setStatus(status);
		
		waitForExtJSAjaxComplete(20);
		
		save(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		close(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(findMeterInHierarchy(reference+"6"),"Meter is created with empty optional fields and displayed on Meters Infrustructure"); 
		
		softAssert.assertAll();
		
	 }
}