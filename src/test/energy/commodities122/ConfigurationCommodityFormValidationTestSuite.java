package test.energy.commodities122;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.McsElement;

public class ConfigurationCommodityFormValidationTestSuite extends ConfigurationCommoditiesPageObject{
	
	 @Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testMeterCreateEdit() throws IOException  {

		 //TODO Change to proper later
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Check Meter form validation: C30188" + " </span><br>",
				true);

		Reporter.log("User tries to create Meter with different validations"  + " <br>",
				true);
		
		//Field values for Meter creation
		
		String code = "test auto mtr code" + getCurrentDate().substring(getCurrentDate().length()-6);
		String reference = "test auto mtr " + getCurrentDate().substring(getCurrentDate().length()-6);
		String cmdtClass = "Electricity";
		
		List<String> mandatoryFieldsErrorMessageKeyWordsCheck =Arrays.asList("form", "invalid");
		List<String> uniqueCodeFieldErrorMessageKeyWordsCheck =Arrays.asList("Code", "exists");
		List<String> uniqueReferenceFieldErrorMessageKeyWordsCheck =Arrays.asList("Reference", "exists");

		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("CommodityFormValidation");
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(20);
		
		openConfigurationEntity("Commodities");		
		
		waitForExtJSAjaxComplete(20);
		
		clickButton(COMMODITY_GRID, "Add");
		
		waitForExtJSAjaxComplete(20);
		
		//Check UI of mandatory fields (bold text, asterisk)
		
		Reporter.log("Check UI of mandatory fields (bold text, asterisk)", true);
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_COMMODITY, "code"), "Code field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_COMMODITY, "reference"), "Reference field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_COMMODITY, "class"), "Commodity field has correct UI (Bold text, asterix)");
		
		//Try to save Meter Form with empty Reference field
		
		Reporter.log("Try to save Commodity Form with empty Reference field", true);
		
		clearField("reference", "Reference");
		
		setCode(code, DIALOG_COMMODITY);
		
		setClass(DIALOG_COMMODITY, cmdtClass);
		
		waitForExtJSAjaxComplete(20);
		
		save(DIALOG_COMMODITY);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkFormExists(DIALOG_COMMODITY),"Form can't be saved with empty Reference field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_COMMODITY, "reference"),"Red border is present on empty Reference field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_COMMODITY, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenCommodityForm();

		//Try to save Meter Form with empty Code field
		
		Reporter.log("Try to save Commdoity Form with empty Code field", true);
		
		clearField("code", "Code");
		
		setReference(reference, DIALOG_COMMODITY);
		
		setClass(DIALOG_COMMODITY, cmdtClass);
		
		waitForExtJSAjaxComplete(20);
		
		save(DIALOG_COMMODITY);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkFormExists(DIALOG_COMMODITY),"Form can't be saved with empty Code field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_COMMODITY, "code"),"Red border is present on empty Code field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_COMMODITY, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenCommodityForm();

		//Try to save Meter Form with empty Class field
		
		Reporter.log("Try to save Meter Form with empty Class field", true);
		
		setReference(reference, DIALOG_COMMODITY);
		
		setCode(code, DIALOG_COMMODITY);
		
		waitForExtJSAjaxComplete(20);
		
		save(DIALOG_COMMODITY);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkFormExists(DIALOG_COMMODITY),"Form can't be saved with empty Class field");
		
		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_COMMODITY, "class"),"Red border is present on empty Class field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_COMMODITY, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenCommodityForm();

		//Try to save Meter Form with non-unique Code field
		
		Reporter.log("Try to save Commodity Form with non-unique Code field", true);
		
		setCode("Water", DIALOG_COMMODITY);
		
		setReference(reference+"1", DIALOG_COMMODITY);
		
		setClass(DIALOG_COMMODITY, cmdtClass);
		
		waitForExtJSAjaxComplete(20);
		
		save(DIALOG_COMMODITY);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkFormExists(DIALOG_COMMODITY),"Form can't be saved with non-unique Code field"); 
		
		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_COMMODITY, uniqueCodeFieldErrorMessageKeyWordsCheck),"Message about non-unique Code field is present"); 
		
		reopenCommodityForm();

		//Try to save Meter Form with non-unique Reference field
		
		Reporter.log("Try to save Commodity Form with non-unique Reference field", true);
		
		setCode(code+"1", DIALOG_COMMODITY);
		
		setReference("Water", DIALOG_COMMODITY);
		
		setClass(DIALOG_COMMODITY, cmdtClass);
		
		waitForExtJSAjaxComplete(20);
		
		save(DIALOG_COMMODITY);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkFormExists(DIALOG_COMMODITY),"Form can't be saved with non-unique Reference field"); 
		
		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_COMMODITY, uniqueReferenceFieldErrorMessageKeyWordsCheck),"Message about non-unique Reference field is present"); 
		
		softAssert.assertAll();
		
	 }
}