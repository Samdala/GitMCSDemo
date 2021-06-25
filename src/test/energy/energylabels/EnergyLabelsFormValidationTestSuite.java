package test.energy.energylabels;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class EnergyLabelsFormValidationTestSuite extends 
		EnergyLabelsPageObject {
	
	@DataProvider
	public Object[][] configuration() {
		return new Object[][] {
		{"Energy Labels", "x-panel x-panel-noborder x-grid-panel"}
		};
	}


	 @Test(enabled=true,dataProvider = "configuration")
	public void EnergyLabelsFormValidation(String entity, String realId) throws IOException, InterruptedException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: EnergyLabelsFormValidationTestSuite: 40009, 40010, 40011, 40012</span><br>",
				true);
		
		String code = "my auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "test auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		String note = "My note";
		String color1 = "#DF0024"; //red
		String color2 = "#009337"; //green
		String rating1 = "High Rating";
		
		List<String> mandatoryFieldsErrorMessageKeyWordsCheck =Arrays.asList("form", "invalid");
		List<String> mandatoryRatingFieldErrorMessageKeyWordsCheck =Arrays.asList("Rating", "empty");
		List<String> uniqueRatingFieldErrorMessageKeyWordsCheck =Arrays.asList("Rating", "exists");
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("configurationEnergyLabels");

		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);

		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(25);
		
		openConfigurationEntity(entity);

		waitForExtJSAjaxComplete(20);
		
		clickAddButton(realId);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//default values
		
		softAssert.assertEquals(getFieldValue("reference"), "", "No default value for reference");
		softAssert.assertEquals(getFieldValue("code"), "", "No default value for code");
		softAssert.assertEquals(getTextAreaFieldValue("notes"), "", "No default value for notes");
		
		//Enabled and disabled fields.

		softAssert.assertTrue(!checkInputDisabled("reference"),"Element reference is not disabled.");
		softAssert.assertTrue(!checkInputDisabled("code"), "Element code is not disabled.");
		softAssert.assertTrue(!checkInputDisabled("notes"), "Element notes is disabled.");

		
		//Check UI of mandatory fields (bold text, asterisk)
		
		Reporter.log("Check UI of mandatory fields (bold text, asterisk)", true);
		
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "reference"),"Element reference has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "code"), "Code field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(!McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "notes"), "notes field has correct UI (no Bold text, no asterix)");
		
		//Try to save Form with empty reference field
		
		Reporter.log("Try to save Form with empty reference field", true);
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code + "1");
		
		setReference(reference + "1");
		
		setNote(note);
		
		clearField("reference", "Reference");
		
		saveClose();
				
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, code+"1"),"Item is not created with empty mandatory fields and not displayed on Overview");
				
		softAssert.assertTrue(checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW),"Form can't be saved with empty reference field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, XPATH_ADD_FORM_WINDOW, "reference"),"Red border is present on empty reference field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
				
		reopenForm();
		
		//Try to save Form with empty code field
		
		Reporter.log("Try to save Form with empty code field", true);
				
		waitForExtJSAjaxComplete(20);
				
		setCode(code + "2");
				
		setReference(reference + "2");
				
		setNote(note);
				
		clearField("code", "Code");
				
		saveClose();
					
		waitForExtJSAjaxComplete(20);
				
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, code+"2"),"Item is not created with empty mandatory fields and not displayed on Overview");
					
		softAssert.assertTrue(checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW),"Form can't be saved with empty reference field"); 
		
		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, XPATH_ADD_FORM_WINDOW, "code"),"Red border is present on empty reference field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
						
		reopenForm();		
		
		//Try to save Form with added empty rating.
		
		Reporter.log("Try to save Form with empty code field", true);
		
		waitForExtJSAjaxComplete(20);
				
		setCode(code + "3");
				
		setReference(reference + "3");
				
		setNote(note);
		
		clickAddRatingButton();
				
		saveClose();
					
		waitForExtJSAjaxComplete(20);
				
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, code+"3"),"Item is not created with empty mandatory fields and not displayed on Overview");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW, mandatoryRatingFieldErrorMessageKeyWordsCheck),"Message about invalid form is present"); 	
		
		softAssert.assertAll();
		
		//Try to save Form with not unique rating.
		
		Reporter.log("Try to save Form with not unique field", true);
		
		waitForExtJSAjaxComplete(20);
				
		setCode(code + "4");
				
		setReference(reference + "4");
		
		setNote(note);
		
		clickAddRatingButton();
		
		waitForExtJSAjaxComplete(20);
		
		setRating(rating1, "1");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddColor("1");
		
		waitForExtJSAjaxComplete(20);
		
		pickColorOnColorPicker(color1);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddRatingButton();
		
		waitForExtJSAjaxComplete(20);
		
		setRating(rating1, "2");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddColor("2");
		
		waitForExtJSAjaxComplete(20);
		
		pickColorOnColorPicker(color2);
		
		waitForExtJSAjaxComplete(20);
				
		saveClose();
					
		waitForExtJSAjaxComplete(20);
				
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, code+"3"),"Item is not created with empty mandatory fields and not displayed on Overview");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW, uniqueRatingFieldErrorMessageKeyWordsCheck),"Message about invalid form is present"); 	
		
		softAssert.assertAll();
		
		Reporter.log("Energy Label form was succesfully checked.", true);

	}

}



