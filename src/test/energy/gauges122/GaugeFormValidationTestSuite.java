package test.energy.gauges122;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class GaugeFormValidationTestSuite extends MeteringGaugesPageObject {
	


	 @Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testMeterCreateEdit() throws IOException  {

		try {
					 
		 //TODO Change to proper later
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Check Gauge form validation: ?????" + " </span><br>",
				true);

		Reporter.log("User tries to create Gauge with different validations"  + " <br>",
				true);
		
		//Field values for Meter creation
		
		String reference = "test auto mtr " + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto mtr " + getCurrentDate().substring(getCurrentDate().length()-6);
		String codeNonUnique = "1preGaugeCopy";
		String status = "Active";
		String timeZone = "(GMT+02:00) Athens, Bucharest, Istanbul";
		String scope1 = "slnmEnrgBuilding7";
		String scope2 = "slnmEnrgBuilding8";
		String scope3 = "slnmEnrgBuilding9";
		String scope4 = "slnmEnrgBuilding10";
		String scope5 = "slnmEnrgBuilding11";
		String scope6 = "slnmEnrgBuilding12";
		
		List<String> mandatoryFieldsErrorMessageKeyWordsCheck =Arrays.asList("form", "invalid");
		List<String> uniqueCodeFieldErrorMessageKeyWordsCheck =Arrays.asList("Code", "exists");
		
		//Navigator
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("GaugesFormValidation");

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		openMeteringEntity("Gauges");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton(GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Status field is prefilled with Status marked as Default", true);
		
		String fieldValue = getFieldValue(ADD_GAUGES_FORM_CLASS, "status");
		
		clickLookup(ADD_GAUGES_FORM_CLASS, "status");
		
		String lookupId = getXWindowId("Select a Metering Object Status");
		
		softAssert.assertTrue(checkDefaultField("@id", lookupId, "Reference", fieldValue), "Status field is prefilled with Status marked as Default");
		
		clickCANCELXwindow();
		
		//Check if Time Zone field is prefilled with Time Zone marked as Default
		
		Reporter.log("Check if Time Zone field is prefilled with Time Zone marked as Default", true);
		
		softAssert.assertEquals(getFieldValue(ADD_GAUGES_FORM_CLASS, "timezone"), "(GMT+01:00) Brussels, Copenhagen, Madrid, Paris", "Time Zone field is prefilled with default time zone");
		
		//Check if Class field is prefilled with "Energy Gauge" value
		
		Reporter.log("Check if Class field is prefilled with 'Energy Gauge' value", true);
		
		softAssert.assertEquals(getObjectClass(), "Location Gauge", "Class field is prefilled with 'Energy Gauge' value");
		
		//Check UI of mandatory fields (bold text, asterisk)
		
		Reporter.log("Check UI of mandatory fields (bold text, asterisk)", true);
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "code"), "Code field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "reference"), "Reference field has correct UI (Bold text, asterix)");
		
		//TODO Change for 12.2
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "scopeLocation"), "Scope field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "timezone"), "Time Zone field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "status"), "Status field has correct UI (Bold text, asterix)");
		
		//Check UI of non-mandatory fields (regular text)
		
		Reporter.log("Check UI of non-mandatory fields (regular text)", true);
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "commissioningDate"), "Commissioning Date field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "model"), "Model field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "serialNumber"), "Serial Number field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "location"), "Physical Location field has correct UI");
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "manufacturer"), "Manufacturer field has correct UI");
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "description"), "Description field has correct UI");
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "accessDirectives"), "Access Instructions has correct UI");
		
		//Try to save Meter Form with empty Reference field
		
		Reporter.log("Try to save Gauge Form with empty Reference field", true);
		
		clearField("reference", "Reference");
		
		setCode(code);
		
		setScope(scope1);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_GAUGES_FORM_CLASS),"Form can't be saved with empty Reference field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_GAUGES_FORM_CLASS, "reference"),"Red border is present on empty Reference field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_GAUGES_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenGaugeForm();

		//Try to save Meter Form with empty Code field
		
		Reporter.log("Try to save Gauge Form with empty Code field", true);
		
		clearField("code", "Code");
		
		setReference(reference+"1");
		
		setScope(scope2);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_GAUGES_FORM_CLASS),"Form can't be saved with empty Code field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_GAUGES_FORM_CLASS, "code"),"Red border is present on empty Code field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_GAUGES_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenGaugeForm();

		//Try to save Meter Form with empty Status field
		
		Reporter.log("Try to save Gauge Form with empty Status field", true);
		
		clearField("status", "Status");
		
		setCode(code+"2");
		
		setReference(reference+"2");
		
		setScope(scope3);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_GAUGES_FORM_CLASS),"Form can't be saved with empty Status field");
		
		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_GAUGES_FORM_CLASS, "status"),"Red border is present on empty Status field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_GAUGES_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenGaugeForm();

		//Try to save Meter Form with empty Time Zone field
		
		Reporter.log("Try to save Gauge Form with empty Time Zone field", true);
		
		clearField("timezone", "Time Zone");
		
		setStatus(status);
		
		setCode(code+"3");
		
		setReference(reference+"3");
		
		setScope(scope4);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_GAUGES_FORM_CLASS),"Form can't be saved with empty Time Zone field"); 
		
		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_GAUGES_FORM_CLASS, "timezone"),"Red border is present on empty Time Zone field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_GAUGES_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenGaugeForm();

		//Try to save Meter Form with empty Scope field
		
		Reporter.log("Try to save Gauge Form with empty Scope field", true);
		
		clearField("scopeLocation", "Scope");
		
		setTimeZone(timeZone);
		
		setStatus(status);
		
		setCode(code+"4");
		
		setReference(reference+"4");
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_GAUGES_FORM_CLASS),"Form can't be saved with empty Commodity field"); 
		
		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_GAUGES_FORM_CLASS, "scopeLocation"),"Red border is present on empty Commodity field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_GAUGES_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenGaugeForm();
		
		//Try to save Meter Form with non-unique Code field
		
		Reporter.log("Try to save Gauge Form with non-unique Code field", true);
		
		setCode(codeNonUnique);
		
		setReference(reference+"5");
		
		setTimeZone(timeZone);
		
		waitForExtJSAjaxComplete(20);
		
		setScope(scope5);
		
		waitForExtJSAjaxComplete(20);
		
		setStatus(status);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_GAUGES_FORM_CLASS),"Form can't be saved with non-unique Code field"); 
		
		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_GAUGES_FORM_CLASS, uniqueCodeFieldErrorMessageKeyWordsCheck),"Message about non-unique Code field is present"); 
		
		reopenGaugeForm();

		//Try to save Meter Form form with empty optional fields
		
		Reporter.log("Try to save Gauge Form form with empty optional fields", true);
		
		setCode(code+"6");
		
		setReference(reference+"6");
		
		setTimeZone(timeZone);
		
		waitForExtJSAjaxComplete(20);
		
		setScope(scope6);
		
		waitForExtJSAjaxComplete(20);
		
		setStatus(status);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		close();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, reference+"6","@class" ,GAUGES_GRID_CLASS),"Gauge is created with empty optional fields and displayed on Gauges Overview"); 
		
		softAssert.assertAll();
		
	 }
	finally{}
	 }
	 
	 
}
