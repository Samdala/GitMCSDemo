package test.energy.weatherstations;

import java.io.IOException;



import java.util.Arrays;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class WeatherStationFormValidation extends
		MeteringWeatherStationsPageObject {




	 @Test(enabled=true)
	public void testAnalysisWeatherStationFormValidation() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Check Weather Station form validation: ?????" + " </span><br>",
				true);

		Reporter.log("User tries to create Weather Station with different validations"  + " <br>",
				true);
		
		String reference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String codeNonUnique = "1preWeatherStation";
		
		List<String> mandatoryFieldsErrorMessageKeyWordsCheck =Arrays.asList("form", "invalid");
		List<String> uniqueCodeFieldErrorMessageKeyWordsCheck =Arrays.asList("Code", "exists");


		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("WeatherStationsFormValidation");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		expandMetering();
		
		openAnalysisEntity("Weather Stations");

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddButton(WEATHERSTATIONS_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		//Check if Status field is prefilled with Status marked as Default
		
		Reporter.log("Check if Status field is prefilled with Status marked as Default", true);
				
		String fieldValue = getFieldValue(ADD_WEATHERSTATIONS_FORM_CLASS, "status");
				
		clickLookup(ADD_WEATHERSTATIONS_FORM_CLASS, "status");
				
		String lookupId = getXWindowId("Select a Metering Object Status");
				
		softAssert.assertTrue(checkDefaultField("@id", lookupId, "Reference", fieldValue), "Status field is prefilled with Status marked as Default");
				
		clickCancelXwindow();
				
		//Check if Time Zone field is prefilled with Time Zone marked as Default
				
		Reporter.log("Check if Time Zone field is prefilled with Time Zone marked as Default", true);
				
		softAssert.assertEquals(getFieldValue(ADD_WEATHERSTATIONS_FORM_CLASS, "timezone"), "(GMT+01:00) Brussels, Copenhagen, Madrid, Paris", "Time Zone field is prefilled with default time zone");
		
		//Check if Degree Days Calculation Method field is prefilled with 'Mean Daily Temperature'
		
		Reporter.log("Check if Degree Days Calculation Method field is prefilled with 'Mean Daily Temperature'", true);
				
		softAssert.assertEquals(getFieldValue(ADD_WEATHERSTATIONS_FORM_CLASS, "entryMethod"), "Mean Daily Temperature", "Degree Days Calculation Method field is prefilled with 'Mean Daily Temperature'");
		
		//Check if Temperature UOM field is prefilled with Celsius
		
		Reporter.log("Check if Temperature UOM field is prefilled with Celsius", true);
				
		softAssert.assertLike(getFieldValue(ADD_WEATHERSTATIONS_FORM_CLASS, "uom"), "C", "Temperature UOM field is prefilled with Celsius");
				
		//Check UI of mandatory fields (bold text, asterisk)
				
		Reporter.log("Check UI of mandatory fields (bold text, asterisk)", true);
				
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_WEATHERSTATIONS_FORM_CLASS, "code"), "Code field has correct UI (Bold text, asterix)");
				
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_WEATHERSTATIONS_FORM_CLASS, "reference"), "Reference field has correct UI (Bold text, asterix)");
				
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_WEATHERSTATIONS_FORM_CLASS, "uom"), "Temperature UOM field has correct UI (Bold text, asterix)");
				
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_WEATHERSTATIONS_FORM_CLASS, "timezone"), "Time Zone field has correct UI (Bold text, asterix)");
				
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_WEATHERSTATIONS_FORM_CLASS, "status"), "Status field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_WEATHERSTATIONS_FORM_CLASS, "entryMethod"), "Degree Days Calculation Method field has correct UI (Bold text, asterix)");
				
		//Check UI of non-mandatory fields (regular text)
				
		Reporter.log("Check UI of non-mandatory fields (regular text)", true);
						
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_WEATHERSTATIONS_FORM_CLASS, "supplier"), "Supplier field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_WEATHERSTATIONS_FORM_CLASS, "description"), "Description field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_WEATHERSTATIONS_FORM_CLASS, "address1"), "Address 1 field has correct UI");
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_WEATHERSTATIONS_FORM_CLASS, "address2"), "Address 2 field has correct UI");
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_WEATHERSTATIONS_FORM_CLASS, "zipCode"), "Zip Code field has correct UI");
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_WEATHERSTATIONS_FORM_CLASS, "city"), "City field has correct UI");
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_WEATHERSTATIONS_FORM_CLASS, "country"), "Country field has correct UI");
		
		//Try to save Weather Station Form with empty Reference field
		
		Reporter.log("Try to save Weather Station Form with empty Reference field", true);
				
		clearField("reference", "Reference");
				
		setCode(code);
				
		waitForExtJSAjaxComplete(20);
				
		save(ADD_WEATHERSTATIONS_FORM_CLASS);
				
		waitForExtJSAjaxComplete(20);
				
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_WEATHERSTATIONS_FORM_CLASS),"Form can't be saved with empty Reference field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_WEATHERSTATIONS_FORM_CLASS, "reference"),"Red border is present on empty Reference field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_WEATHERSTATIONS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
				
		reopenWeatherStationForm();
		
		//Try to save Weather Station Form with empty Code field
		
		Reporter.log("Try to save Weather Station Form with empty Code field", true);
				
		clearField("code", "Code");
				
		setReference(reference);
				
		waitForExtJSAjaxComplete(20);
				
		save(ADD_WEATHERSTATIONS_FORM_CLASS);
				
		waitForExtJSAjaxComplete(20);
				
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_WEATHERSTATIONS_FORM_CLASS),"Form can't be saved with empty Code field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_WEATHERSTATIONS_FORM_CLASS, "code"),"Red border is present on empty Code field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_WEATHERSTATIONS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
				
		reopenWeatherStationForm();
		
		//Try to save Weather Station Form with empty Status field
		
		Reporter.log("Try to save Weather Station Form with empty Status field", true);
				
		clearField("status", "Status");
				
		setReference(reference+"1");
		
		setCode(code+"1");
				
		waitForExtJSAjaxComplete(20);
				
		save(ADD_WEATHERSTATIONS_FORM_CLASS);
				
		waitForExtJSAjaxComplete(20);
				
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_WEATHERSTATIONS_FORM_CLASS),"Form can't be saved with empty Status field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_WEATHERSTATIONS_FORM_CLASS, "status"),"Red border is present on empty Status field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_WEATHERSTATIONS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
				
		reopenWeatherStationForm();
		
		//Try to save Weather Station Form with empty Temperature UOM field
		
		Reporter.log("Try to save Weather Station Form with empty Temperature UOM field", true);
				
		clearField("uom", "Temperature UOM");
				
		setReference(reference+"2");
		
		setCode(code+"2");
				
		waitForExtJSAjaxComplete(20);
				
		save(ADD_WEATHERSTATIONS_FORM_CLASS);
				
		waitForExtJSAjaxComplete(20);
				
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_WEATHERSTATIONS_FORM_CLASS),"Form can't be saved with empty Temperature UOM field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_WEATHERSTATIONS_FORM_CLASS, "uom"),"Red border is present on empty Temperature UOM field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_WEATHERSTATIONS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
				
		reopenWeatherStationForm();
		
		//Try to save Weather Station Form with non-unique Code field
		
		Reporter.log("Try to save Weather Station Form with non-unique Code field", true);
		
		setCode(codeNonUnique);
		
		setReference(reference+"3");
		
		waitForExtJSAjaxComplete(20);
		
		save(ADD_WEATHERSTATIONS_FORM_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_WEATHERSTATIONS_FORM_CLASS),"Form can't be saved with non-unique Code field"); 
		
		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_WEATHERSTATIONS_FORM_CLASS, uniqueCodeFieldErrorMessageKeyWordsCheck),"Message about non-unique Code field is present"); 
		
		reopenWeatherStationForm();

		//Try to save Weather Station Form form with empty optional fields
		
		Reporter.log("Try to save Weather Station Form form with empty optional fields", true);
		
		setCode(code+"4");
		
		setReference(reference+"4");
		
		waitForExtJSAjaxComplete(20);
		
		save(ADD_WEATHERSTATIONS_FORM_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		close(ADD_WEATHERSTATIONS_FORM_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, reference+"4","@class" ,WEATHERSTATIONS_GRID_CLASS),"Weather Station is created with empty optional fields and displayed on Gauges Overview");
		
		softAssert.assertAll();
		
	 }
}