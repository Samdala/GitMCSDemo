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

public class WSChannelsFormValidationTestSuite extends
	MeteringWeatherstationChanelsPageObject {


	 @Test(enabled=true)
	public void testWeatherstationChannelFormValidate() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Check Weatherstation Channel form validation: Trunk(31570, 31571, 31582), 122(35474,35473, 35475)" + " </span><br>",
				true);

		Reporter.log("User tries to create Weatherstation Channel with different validations"  + " <br>",
				true);
		
		String lookupId;
		String waetherstationDialogId;
		String weatherstationReference = "1preWeatherStation";
		String channelParameter = "Precipitation";
		
		List<String> mandatoryFieldsErrorMessageKeyWordsCheck =Arrays.asList("form", "invalid");
		//String channelUOM = "millimeter";
		//Boolean build122 = driver.getCurrentUrl().contains("122");
		
		//Field values for Meter creation
		
		//String reference = "test auto mtr " + getCurrentDate().substring(getCurrentDate().length()-6);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("WeatherstationChannelFormValidation");

		//Navigate to Meters Overview
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		openAnalysisEntity("Weather Stations");
		
		waitForExtJSAjaxComplete(20);
		
		//Open pre-configured Meter and go to Channels tab
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", WEATHERSTATIONS_GRID_CLASS, weatherstationReference);
		
		clickEditButton(WEATHERSTATIONS_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		changeTab("channels");
		
		waitForExtJSAjaxComplete(20);
		
		//////////////////////////Create Channel////////////////////////
		
		waetherstationDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
		
		clickButton("Add", waetherstationDialogId);
		
		waitForExtJSAjaxComplete(20); 
		
		////////////////////////DefaultValues////////////////////////
		
		Reporter.log("Check if Reference field is prefilled with ", true);
				
		softAssert.assertEquals(getFieldValue(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "reference"), "", "Reference field is prefilled with correct value.");
		
		Reporter.log("Check if channelParameter field is prefilled with ", true);
		
		softAssert.assertTrue(getFieldValue(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "channelParameter").toLowerCase().contains("select")||getFieldValue(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "channelParameter").equals("") , "channelParameter field is prefilled with correct value.");
		
		Reporter.log("Test 1 = " + getFieldValue(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "channelParameter"));
		
		Reporter.log("Check if unitOfMeasure field is prefilled with ", true);
		
		softAssert.assertEquals(getFieldValue(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure"), "", "unitOfMeasure field is prefilled with correct value.");
		
		//Reporter.log("Test 2 = " + getFieldValue(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure"));
		
		Reporter.log("Check if readingInterval field is prefilled with ", true);
		
		softAssert.assertEquals(getFieldValue(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "readingInterval"), "Hour", "readingInterval field is prefilled with correct value.");
		
		Reporter.log("Check if entryMethod field is prefilled with ", true);
		
		softAssert.assertEquals(getFieldValue(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "entryMethod"), "", "entryMethod field is prefilled with correct value.");
		
		Reporter.log("Check if multiplier field is prefilled with ", true);
		
		//////////////////////////Check UI of mandatory fields (bold text, asterisk)////////////////////////
		
		Reporter.log("Check UI of mandatory fields (bold text, asterisk)", true);
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "reference"), "Reference field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure"), "unitOfMeasure field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "readingInterval"), "readingInterval field has correct UI (Bold text, asterix)");
		
		///////////////////////Clearing MANDATORY FIELDS AND SAVE/////////////////////////
		
		//Try to save Meter Channel Form with empty Reference field
		
		Reporter.log("Try to save Meter Channel Form with empty Reference field", true);
		
		waitForExtJSAjaxComplete(20);
		
		clearChnlField("reference", "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS),"Form can't be saved with empty Reference field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "reference"),"Red border is present on empty Reference field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenWeatherStationForm();

		//Try to save Meter Channel Form with empty channelParameter field
		
		Reporter.log("Try to save Meter Form with empty channelParameter field", true);
		
		setChnlEntryMethod("Automatic");
		
		clearChnlField("channelParameter", "Parameter");
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS),"Form can't be saved with empty channelParameter field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "channelParameter"),"Red border is present on empty channelParameter field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenWeatherStationForm();

		//Try to save Meter Channel Form with empty unitOfMeasure field
		
		Reporter.log("Try to save Meter Form with empty unitOfMeasure field", true);
		
		setChannelParameter("CDD");
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if entryMethod field is updated with correct value", true);
		
		softAssert.assertEquals(getFieldValue(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "entryMethod"), "Calculated", "entryMethod field is prefilled with correct value.");
		
		waitForExtJSAjaxComplete(20);
		
		//clearChnlField("unitOfMeasure", "UOM");
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS),"Form can't be saved with empty unitOfMeasure field");
		
		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "baseTemperature"),"Red border is present on empty baseTemperature field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenWeatherStationForm();
		
		///////////////////////////////////Field limitation,Data//////////////////////////////
		
		//Verify Parameter lookup is limited by Channel Class
		
		Reporter.log("Verify channelParameter lookup is limited by Channel Class", true);
		
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "channelParameter");
		
		lookupId = getXWindowId("Select a Parameter");
		
		Reporter.log("Check if Parameter lookup is limited by Channel Class", true);
		
		softAssert.assertFalse(Grid.isRowInGridPresent(driver, "BLDAREA", "@id", lookupId), "BLDAREA parameter is not  present on parameter lookup");
		
		softAssert.assertFalse(Grid.isRowInGridPresent(driver, "BLDVOL", "@id", lookupId), "BLDVOL parameter is not  present on parameter lookup");
				
		softAssert.assertFalse(Grid.isRowInGridPresent(driver, "PREGAUGEPARAMETER1", "@id", lookupId), "PREGAUGEPARAMETER1 parameter is not  present on parameter lookup");
		
		softAssert.assertFalse(Grid.isRowInGridPresent(driver, "Gauge", "@id", lookupId), "Gauge parameter class is not present on parameter lookup");
		
		softAssert.assertFalse(Grid.isRowInGridPresent(driver, "Meter", "@id", lookupId), "Meter parameter class is not present on parameter lookup");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Weather", "@id", lookupId), "Weather parameter class is present on parameter lookup");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Cooling Degree-days", "@id", lookupId), "Cooling Degree-days parameter is present on parameter lookup");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Solar Hours", "@id", lookupId), "Solar Hours parameter is present on parameter lookup");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Wind Speed", "@id", lookupId), "Wind Speed parameter is present on parameter lookup");
		
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		//Verify UOM lookup is limited by Channel Class Meter
		
		Reporter.log("Verify UOM lookup is limited by Channel Class", true);
		
		setChannelParameter(channelParameter);
				
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure");
				
		lookupId = getXWindowId("Select a Unit Of Measure");
				
		Reporter.log("Check if UOM lookup is limited by Channel Class", true);
		
		//setChannelParameter(channelParameter);
		
		waitForExtJSAjaxComplete(20);
				
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "BTU", "Code"), "BTU UOM is not present on UOM lookup");
		
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
				
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "cal", "Code"), "Caloie UOM is not present on UOM lookup");
		
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
						
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "J", "Code"), "Joule UOM is not present on UOM lookup");
		
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
				
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "square meter", "Reference"), "square meter UOM is not present on UOM lookup");
		
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "are", "Reference"), "are UOM is not present on UOM lookup");
		
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
				
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "square millimeter", "Reference"), "square millimeter UOM is not present on UOM lookup");
		
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
				
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "square meter", "Reference"), "square meter UOM is not present on UOM lookup");
		
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "cl", "Code"), "cl UOM is not present on UOM lookup");
		
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "cal", "Code"), "cal UOM is not present on UOM lookup");
		
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "h", "Code"), "hour UOM is not present on UOM lookup");
		
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertTrue(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "in", "Code"), "inch UOM is present on UOM lookup");
		
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "kJ", "Code"), "kilojoule UOM is not not present on UOM lookup");
		
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertTrue(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "km", "Code"), "km UOM is present on UOM lookup");
		
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertTrue(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "mm", "Code"), "milimeter UOM is present on UOM lookup");
				
		//clickCancelXwindow();
				
		waitForExtJSAjaxComplete(20);
		
		//Verify Reading Interval is limited by Channel Class Meter
		
		softAssert.assertFalse(verifyItemExists("readingInterval", "15 minutes"), "15 minutes is not present on readingInterval dropdown");
		
		softAssert.assertTrue(verifyItemExists("readingInterval", "Hour"), "Hour is present on readingInterval dropdown");

		softAssert.assertTrue(verifyItemExists("readingInterval", "Day"), "Day is present on readingInterval dropdown");

		softAssert.assertTrue(verifyItemExists("readingInterval", "Month"), "Month is present on readingInterval dropdown");

		softAssert.assertFalse(verifyItemExists("readingInterval", "Year"), "Year is not present on readingInterval dropdown");

		//softAssert.assertTrue(verifyItemExists("entryType", "Index Value"), "Index Value is present on entryType dropdown");

		//softAssert.assertTrue(verifyItemExists("entryType", "Usage Value"), "Usage Value is present on entryType dropdown");

		//softAssert.assertTrue(verifyItemExists("entryMethod", "Manual"), "Manual is present on entryMethod dropdown");

		//softAssert.assertTrue(verifyItemExists("entryMethod", "Calculated"), "Calculated is present on entryMethod dropdown");

		//softAssert.assertTrue(verifyItemExists("entryMethod", "Automatic"), "Automatic is present on entryMethod dropdown");
		
		//softAssert.assertFalse(verifyItemExists("calculationMethod", "Spreading"), "Spreading is not present on calculationMethod dropdown");
		
		//softAssert.assertTrue(verifyItemExists("calculationMethod", "Sum"), "Sum is present on calculationMethod dropdown");
		
		//softAssert.assertTrue(verifyItemExists("calculationMethod", "Latest Value"), "Latest Value is present on calculationMethod dropdown");

		/////////////////////verifying disabled fields///////////////////////////
		
		Reporter.log("verifying disabled fields", true);
		
		reopenWeatherStationForm();
		
		//setChnlEntryMethod("Automatic");
		
		softAssert.assertTrue(checkChannelInputDisabled("code"), "Input Element code is disabled.");
		
		softAssert.assertFalse(checkChannelInputDisabled("channelParameter"), "Input Element channelParameter is enabled.");
		
		softAssert.assertFalse(checkChannelInputDisabled("reference"), "Input Element reference is enabled.");
		
		softAssert.assertFalse(checkChannelInputDisabled("readingInterval"), "Input Element readingInterval is enabled.");
		
		softAssert.assertTrue(checkChannelInputDisabled("entryMethod"), "Input Element entryMethod is disabled.");
		
		softAssert.assertTrue(checkChannelInputDisabled("unitOfMeasure"), "Input Element UOM is disabled.");
		
		setChannelParameter(channelParameter);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(checkChannelInputDisabled("unitOfMeasure"), "Input Element UOM is enabled.");
		
		close();
		
		softAssert.assertAll();
		
	 }
}