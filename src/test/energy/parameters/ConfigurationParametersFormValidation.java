package test.energy.parameters;

import java.io.IOException;








import java.util.Arrays;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class ConfigurationParametersFormValidation extends
	ConfigurationParametersPageObject {




	 @Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testAnalysisParameterFormValidation() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Check Parameter form validation: TRUNK(34962, 34963, 34964, 34965, 34966), 122 (23352, 23353, 23354, 23355, 28313, 28314)" + " </span><br>",
				true);

		Reporter.log("User tries to create Parameter with different validations"  + " <br>",
				true);
		
		String name = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test_auto" + getCurrentDate().substring(getCurrentDate().length()-6);
		code = code.replace(".", "1");
		String className1 = "Gauge";
		String className2 = "Weather";
		String className3 = "Meter";
		String calcMethod = "Sum";
		
		String UOMClass1 = "Area";
		String UOMClass2 = "Energy";
		String DefaultUOM1 = "centiare";
		String DefaultUOM2 = "square decimeter";
		String DefaultUOM3 = "league";
		String DefaultUOM4 = "meter";
		String DefaultUOM5 = "Joule";
		String DefaultUOM6 = "Watthour";
		String DefaultUOM7 = "kilogram";
		String DefaultUOM8 = "Volt";
		String codeNonUnique1 = "HDD";
		String NameNonUnique2 = "Building Area";
		
		String alreadyUsed1 = "preGaugeParameter11";
		String alreadyUsed2 = "preWeatherStationParameter10";
		String alreadyUsed3 = "preGaugeParameter8";
		
		List<String> mandatoryFieldsErrorMessageKeyWordsCheck =Arrays.asList("form", "invalid");
		List<String> uniqueNameFieldErrorMessageKeyWordsCheck =Arrays.asList("Name", "exists");
		List<String> uniqueCodeFieldErrorMessageKeyWordsCheck =Arrays.asList("Code", "exists");
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("configurationParameters");

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		openConfigurationEntity("Parameters");

		waitForExtJSAjaxComplete(20);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		//default values
		
		softAssert.assertEquals(getFieldValue("class"), "", "No default value for class");
		softAssert.assertEquals(getFieldValue("code"), "", "No default value for code");
		softAssert.assertLike(getFieldValue("uomClass"), "Select", "No default value for uomClass");
		softAssert.assertTrue(getFieldValue("defaultUom").contains("Select a Unit Of Measure") || getFieldValue("defaultUom").contains(""), "No default value for defaultUom");
		
		//Enabled only when Class = Gauge or Weather, disabled when class = Meter
		
		softAssert.assertTrue(!checkInputDisabled("class"), "Element class is not disabled.");
		softAssert.assertTrue(!checkInputDisabled("code"), "Element code is not disabled.");
		softAssert.assertTrue(!checkInputDisabled("name"), "Element name is not disabled.");
		softAssert.assertTrue(!checkInputDisabled("uomClass"), "Element uomClass is not disabled.");
//		softAssert.assertTrue(checkInputDisabled("defaultUom"), "Element defaultUom is not disabled.");
		
		softAssert.assertTrue(verifyItemExists("class", className1), className1 + " is present on class dropdown");
		softAssert.assertTrue(verifyItemExists("class", className2), className2 + " is present on class dropdown");
		
		softAssert.assertFalse(verifyItemExists("class", className3), className3 + " is not present on class dropdown");
		
		setClassValue(className1);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(!checkInputDisabled("uomClass"), "Element uomClass is not disabled.");
		softAssert.assertTrue(checkInputDisabled("defaultUom"), "Element defaultUom is disabled.");
		
		setClassValue(className2);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(!checkInputDisabled("uomClass"), "Element uomClass is not disabled.");
		softAssert.assertTrue(checkInputDisabled("defaultUom"), "Element defaultUom is not disabled.");
		
		/*if (build122) {
			setClassValue(className3);
		
			waitForExtJSAjaxComplete(20);
		
			softAssert.assertTrue(checkInputDisabled("uomClass"), "Element uomClass is disabled.");
			
			softAssert.assertTrue(checkInputDisabled("defaultUom"), "Element defaultUom is disabled.");
		
		}*/
		
		setClassValue(className1);
		
		setUOMClass(UOMClass1);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(!checkInputDisabled("defaultUom"), "Element defaultUom is disabled.");
				
		//Check UI of mandatory fields (bold text, asterisk)
				
		Reporter.log("Check UI of mandatory fields (bold text, asterisk)", true);
				
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "class"), "Class field has correct UI (Bold text, asterix)");
				
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "code"), "Code field has correct UI (Bold text, asterix)");
				
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "name"), "Name field has correct UI (Bold text, asterix)");
				
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "uomClass"), "uomClass field has correct UI (Bold text, asterix)");
				
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "defaultUom"), "defaultUom field has correct UI (Bold text, asterix)");
				
		//Check UI of non-mandatory fields (regular text)
				
		//Reporter.log("Check UI of non-mandatory fields (regular text)", true);
		
		//Try to save Parameter Form with empty class field
		
		reopenParameterForm();
		
		Reporter.log("Try to save Parameter Form with empty class field", true);
		
		//setClassValue(className1);
		
		setCode(code+"1");
		
		setName(name+"1");
		
		setUOMClass(UOMClass1);
		
		setDefaultUOM(DefaultUOM1);
		
		setCalculationMethod(calcMethod);
			
		waitForExtJSAjaxComplete(20);
				
		save();
				
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, name+"1"),"Parameter is not created with empty mandatory fields and not displayed on Overview");
				
		softAssert.assertTrue(checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW),"Form can't be saved with empty class field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, XPATH_ADD_FORM_WINDOW, "class"),"Red border is present on empty class field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
				
		reopenParameterForm();
		
		//Try to save Parameter Form with empty Code field
		
		Reporter.log("Try to save Parameter Form with empty Code field", true);
				
		setClassValue(className1);
		
		setCode(code+"2");
				
		setName(name+"2");
				
		setUOMClass(UOMClass1);
				
		setDefaultUOM(DefaultUOM1);
		
		setCalculationMethod(calcMethod);
				
		clearField("code", "Code");
						
		waitForExtJSAjaxComplete(20);
						
		save();
						
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, name+"2"),"Parameter is not created with empty mandatory fields and not displayed on Overview");
						
		softAssert.assertTrue(checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW),"Form can't be saved with empty class field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, XPATH_ADD_FORM_WINDOW, "code"),"Red border is present on empty Code field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
						
		reopenParameterForm();
		
		//Try to save Parameter Form with empty Name field
		
		Reporter.log("Try to save Parameter Form with empty Name field", true);
				
		setClassValue(className1);
		
		setCode(code+"3");
				
		setName(name+"3");
				
		setUOMClass(UOMClass1);
				
		setDefaultUOM(DefaultUOM1);
		
		setCalculationMethod(calcMethod);
				
		clearField("name", "Name");
						
		waitForExtJSAjaxComplete(20);
						
		save();
						
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, code+"3"),"Parameter is not created with empty mandatory fields and not displayed on Overview");
						
		softAssert.assertTrue(checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW),"Form can't be saved with empty class field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, XPATH_ADD_FORM_WINDOW, "name"),"Red border is present on empty Name field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
						
		reopenParameterForm();
		
		//Try to save Parameter Form with empty UOM field
		
		
		Reporter.log("Try to save Parameter Form with empty UOM field", true);
				
		setClassValue(className1);
		
		setCode(code+"4");
				
		setName(name+"4");
				
		setUOMClass(UOMClass1);
				
		setDefaultUOM(DefaultUOM1);
		
			setCalculationMethod(calcMethod);
				
		clearField("uomClass", "UOM Class");
						
		waitForExtJSAjaxComplete(20);
						
		save();
						
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, name+"4"),"Parameter is not created with empty mandatory fields and not displayed on Overview");
						
		softAssert.assertTrue(checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW),"Form can't be saved with empty class field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, XPATH_ADD_FORM_WINDOW, "uomClass"),"Red border is present on empty uomClass field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
						
		reopenParameterForm();
		
		//Try to save Parameter Form with empty Default UOM field
		
		Reporter.log("Try to save Parameter Form with empty Default UOM field", true);
				
		setClassValue(className1);
		
		setCode(code+"5");
				
		setName(name+"5");
				
		setUOMClass(UOMClass1);
		
			setCalculationMethod(calcMethod);
				
//		setDefaultUOM(DefaultUOM1);
				
//		clearField("defaultUom", "Default UOM");
						
		waitForExtJSAjaxComplete(20);
						
		save();
						
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, name+"5"),"Parameter is not created with empty mandatory fields and not displayed on Overview");
						
		softAssert.assertTrue(checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW),"Form can't be saved with empty class field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, XPATH_ADD_FORM_WINDOW, "defaultUom"),"Red border is present on empty Default UOM field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
						
		reopenParameterForm();
		
		//Try to save Parameter Form with non-unique Name field
		
		Reporter.log("Try to save Parameter Form with non-unique Name field", true);
		
		setClassValue(className1);
		
		setCode(code+"6");
				
		setName(NameNonUnique2);
				
		setUOMClass(UOMClass1);
				
		setDefaultUOM(DefaultUOM1);
		
		setCalculationMethod(calcMethod);
						
		waitForExtJSAjaxComplete(20);
						
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, code+"6"),"Parameter is not created with non unique fields and not displayed on Overview");
		
		softAssert.assertTrue(checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW),"Form can't be saved with non-unique Code field"); 
		
		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW, uniqueNameFieldErrorMessageKeyWordsCheck),"Message about non-unique Code field is present"); 
		
		reopenParameterForm();

		//Try to save Parameter Form with non-unique Code field
		
		Reporter.log("Try to save Parameter Form with non-unique Code field", true);
		
		setClassValue(className1);
		
		setCode(codeNonUnique1);
				
		setName(name+"6");
				
		setUOMClass(UOMClass1);
				
		setDefaultUOM(DefaultUOM1);
		
		setCalculationMethod(calcMethod);
						
		waitForExtJSAjaxComplete(20);
						
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, name+"6"),"Parameter is not created with non unique fields and not displayed on Overview");
		
		softAssert.assertTrue(checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW),"Form can't be saved with non-unique Code field"); 
		
		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW, uniqueCodeFieldErrorMessageKeyWordsCheck),"Message about non-unique Code field is present"); 
		
		reopenParameterForm();
		
		//Lookup to Units of Measure, limited by selected Unit of Measure Class 
		
		setClassValue(className1);
		
		setCode(code+"7");
				
		setName(name+"7");
				
		setUOMClass(UOMClass1);
		
		setCalculationMethod(calcMethod);
		
		clickLookup(XPATH_ADD_FORM_WINDOW, "defaultUom");
		
		String lookupId = getXWindowId("Select a Unit Of Measure");
		
		Reporter.log("Check if defaultUom lookup is limited by UOMClass", true);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, DefaultUOM1, "@id", lookupId), DefaultUOM1 + " parameter is present on lookup");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, DefaultUOM2, "@id", lookupId), DefaultUOM1 + " parameter is present on lookup");
				
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, DefaultUOM3, "@id", lookupId), DefaultUOM3 + " parameter is present on lookup");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, DefaultUOM4, "@id", lookupId), DefaultUOM4 + " parameter is present on lookup");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, DefaultUOM5, "@id", lookupId), DefaultUOM5 + " parameter is present on lookup");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, DefaultUOM6, "@id", lookupId), DefaultUOM6 + " parameter is present on lookup");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, DefaultUOM7, "@id", lookupId), DefaultUOM7 + " parameter is present on lookup");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, DefaultUOM8, "@id", lookupId), DefaultUOM8 + " parameter is present on lookup");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, DefaultUOM4, "@id", lookupId), DefaultUOM4 + " parameter is present on lookup");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, DefaultUOM4, "@id", lookupId), DefaultUOM4 + " parameter is present on lookup");	
		
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		setUOMClass(UOMClass2);
		
		clickLookup(XPATH_ADD_FORM_WINDOW, "defaultUom");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		Reporter.log("Check if defaultUom lookup is limited by UOMClass", true);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, DefaultUOM1, "@id", lookupId), DefaultUOM1 + " parameter is present on lookup");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, DefaultUOM2, "@id", lookupId), DefaultUOM1 + " parameter is present on lookup");
				
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, DefaultUOM3, "@id", lookupId), DefaultUOM3 + " parameter is present on lookup");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, DefaultUOM4, "@id", lookupId), DefaultUOM4 + " parameter is present on lookup");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, DefaultUOM5, "@id", lookupId), DefaultUOM5 + " parameter is present on lookup");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, DefaultUOM6, "@id", lookupId), DefaultUOM6 + " parameter is present on lookup");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, DefaultUOM7, "@id", lookupId), DefaultUOM7 + " parameter is present on lookup");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, DefaultUOM8, "@id", lookupId), DefaultUOM8 + " parameter is present on lookup");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, DefaultUOM4, "@id", lookupId), DefaultUOM4 + " parameter is present on lookup");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, DefaultUOM4, "@id", lookupId), DefaultUOM4 + " parameter is present on lookup");	
		
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		close();
		 
		waitForExtJSAjaxComplete(20);
			 
		clickOnDialogButton("Yes"); 		 
		
		//Verify that already used parameters are read only

		Reporter.log("Verify that already used paremeters are read only", true);
		
		//gauge
		
		filterGrid("@class", "x-grid3", alreadyUsed1, "Name");
		
		waitForExtJSAjaxComplete(25);
	
		Grid.checkRowInGriByTextValueAndClick(driver, alreadyUsed1);
		
		clickEditButton();
		
		softAssert.assertTrue(checkInputDisabled("class"), "Element class is disabled.");
		softAssert.assertTrue(!checkInputDisabled("code"), "Element code is not disabled.");
		softAssert.assertTrue(!checkInputDisabled("name"), "Element name is not disabled.");
		softAssert.assertTrue(!checkInputDisabled("uomClass"), "Element uomClass is disabled.");
		softAssert.assertTrue(!checkInputDisabled("defaultUom"), "Element defaultUom is disabled.");
		
		close();
		
		waitForExtJSAjaxComplete(20);
		
		//weather station
		
		filterGrid("@class", "x-grid3", alreadyUsed2, "Name");
		
		waitForExtJSAjaxComplete(25);

		Grid.checkRowInGriByTextValueAndClick(driver, alreadyUsed2);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(checkInputDisabled("class"), "Element class is disabled.");
		softAssert.assertTrue(!checkInputDisabled("code"), "Element code is not disabled.");
		softAssert.assertTrue(!checkInputDisabled("name"), "Element name is not disabled.");
		softAssert.assertTrue(checkInputDisabled("uomClass"), "Element uomClass is disabled.");
		softAssert.assertTrue(checkInputDisabled("defaultUom"), "Element defaultUom is disabled.");
		
		close();
		
		waitForExtJSAjaxComplete(20);
		
		//meters 
		/*if (build122) {
			
			filterGrid("@class", "x-grid3", alreadyUsed3, "Name");
			
			Grid.checkRowInGriByTextValueAndClick(driver, alreadyUsed3);
			
			waitForExtJSAjaxComplete(25);
		
			clickEditButton();
		
			softAssert.assertTrue(checkInputDisabled("class"), "Element class is disabled.");
			softAssert.assertTrue(!checkInputDisabled("code"), "Element code is not disabled.");
			softAssert.assertTrue(!checkInputDisabled("name"), "Element name is not disabled.");
			softAssert.assertTrue(checkInputDisabled("uomClass"), "Element uomClass is disabled.");
			softAssert.assertTrue(checkInputDisabled("defaultUom"), "Element defaultUom is disabled.");
		
			close();
		}*/
		
		waitForExtJSAjaxComplete(20);
	
		softAssert.assertAll();
		
	 }
}