package test.energy.gauges;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.energy.navigator.NavigatorPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class GaugeChannelsFormValidationTestSuite extends
	MeteringGaugesChanelsPageObject {


	 @Test(enabled=true)
	public void testGaugesChannelFormValidate() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Check Gauges Channel form validation: TRUNK(C30542,C30543, C30544, C30584 )" + " </span><br>",
				true);

		Reporter.log("User tries to create Gauges Channel with different validations"  + " <br>",
				true);
		
		String lookupId;
		String gaugesDialogId;
		String gaugeReference = "preGaugeMeasure";
		String channelParameter = "preGaugeParameter9";
		String channelRefWithMeasurement = "1preGaugeMeasureEarlier";
		String channelUOM = "square decimeter";
		Boolean build122 = driver.getCurrentUrl().contains("122");
		Boolean build14 = driver.getCurrentUrl().contains("14");
		
		List<String> mandatoryFieldsErrorMessageKeyWordsCheck =Arrays.asList("form", "invalid");
		
		//Field values for Meter creation
		
		String reference = "test auto mtr " + getCurrentDate().substring(getCurrentDate().length()-6);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("GaugesChannelFormValidation");

		//Navigate to Meters Overview
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		openAnalysisEntity("Gauges");
		
		waitForExtJSAjaxComplete(20);
		
		//Open pre-configured Meter and go to Channels tab
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", GAUGES_GRID_CLASS, gaugeReference);
		
		clickEditButton(GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		changeTab("channels");
		
		waitForExtJSAjaxComplete(20);
		
		//////////////////////////Create Channel////////////////////////
		
		gaugesDialogId = getXWindowIdByClass("slnmGaugeId");
		
		clickButton("Add", gaugesDialogId);
		
		waitForExtJSAjaxComplete(20); 
		
		////////////////////////DefaultValues////////////////////////
		
		Reporter.log("Check if Reference field is prefilled with ", true);
				
		softAssert.assertEquals(getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "reference"), gaugeReference, "Reference field is prefilled with correct value.");
		
		Reporter.log("Check if channelParameter field is prefilled with ", true);
		
		softAssert.assertEquals(getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "channelParameter"), "Select a Parameter", "channelParameter field is prefilled with correct value.");
		
		Reporter.log("Test 1 = " + getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "channelParameter"));
		
		Reporter.log("Check if unitOfMeasure field is prefilled with ", true);
		
		softAssert.assertEquals(getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure"), "Select a Unit Of Measure", "unitOfMeasure field is prefilled with correct value.");
		
		Reporter.log("Test 2 = " + getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure"));
		
		Reporter.log("Check if readingInterval field is prefilled with ", true);
		
		softAssert.assertEquals(getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "readingInterval"), "Day", "readingInterval field is prefilled with correct value.");
		
//		Reporter.log("Check if entryType field is prefilled with ", true);
//		
//		softAssert.assertEquals(getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "entryType"), "Usage Value", "entryType field is prefilled with correct value.");
		
		Reporter.log("Check if entryMethod field is prefilled with ", true);
		
		softAssert.assertEquals(getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "entryMethod"), "Manual", "entryMethod field is prefilled with correct value.");
		
		Reporter.log("Check if Class field is prefilled with ", true);
		
		//trunk Energy Gauge //122 Location Gauge Class
		String className = (!build122)? "Energy Gauge" :"Location Gauge";
		softAssert.assertEquals(getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "class"), className, "Class field is prefilled with correct value.");
		
//		Reporter.log("Check if calculationMethod field is prefilled with ", true);
//		
//		softAssert.assertEquals(getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "calculationMethod"), "Latest Value", "calculationMethod field is prefilled with correct value.");
		
		Reporter.log("Check if multiplier field is prefilled with ", true);
		
		//////////////////////////Check UI of mandatory fields (bold text, asterisk)////////////////////////
		
		Reporter.log("Check UI of mandatory fields (bold text, asterisk)", true);
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_CHANEL_GAUGES_FORM_CLASS, "reference"), "Reference field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure"), "unitOfMeasure field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_CHANEL_GAUGES_FORM_CLASS, "readingInterval"), "readingInterval field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_CHANEL_GAUGES_FORM_CLASS, "entryType"), "entryType field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_CHANEL_GAUGES_FORM_CLASS, "calculationMethod"), "calculationMethod field has correct UI (Bold text, asterix)");
		
		//Check UI of non-mandatory fields (regular text)
		
		Reporter.log("Check UI of non-mandatory fields (regular text)", true);
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_CHANEL_GAUGES_FORM_CLASS, "calibrationDate"), "calibration Date field has correct UI");
				
		setChnlEntryMethod("Automatic");
		
		///////////////////////Clearing MANDATORY FIELDS AND SAVE/////////////////////////
		
		//Try to save Meter Channel Form with empty Reference field
		
		Reporter.log("Try to save Meter Channel Form with empty Reference field", true);
		
		waitForExtJSAjaxComplete(20);
		
		setChnlEntryMethod("Automatic");
		
		clearChnlField("reference", "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		save(ADD_CHANEL_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_CHANEL_GAUGES_FORM_CLASS),"Form can't be saved with empty Reference field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_CHANEL_GAUGES_FORM_CLASS, "reference"),"Red border is present on empty Reference field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_CHANEL_GAUGES_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenGaugeChannelForm();

		//Try to save Meter Channel Form with empty channelParameter field
		
		Reporter.log("Try to save Meter Form with empty channelParameter field", true);
		
		setChnlEntryMethod("Automatic");
		
		clearChnlField("channelParameter", "Parameter");
		
		waitForExtJSAjaxComplete(20);
		
		save(ADD_CHANEL_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_CHANEL_GAUGES_FORM_CLASS),"Form can't be saved with empty channelParameter field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_CHANEL_GAUGES_FORM_CLASS, "channelParameter"),"Red border is present on empty channelParameter field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_CHANEL_GAUGES_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenGaugeChannelForm();

		//Try to save Meter Channel Form with empty unitOfMeasure field
		
		Reporter.log("Try to save Meter Form with empty unitOfMeasure field", true);
		
		setChnlEntryMethod("Automatic");
		
		clearChnlField("unitOfMeasure", "UOM");
		
		waitForExtJSAjaxComplete(20);
		
		save(ADD_CHANEL_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_CHANEL_GAUGES_FORM_CLASS),"Form can't be saved with empty unitOfMeasure field");
		
		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure"),"Red border is present on empty unitOfMeasure field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_CHANEL_GAUGES_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenGaugeChannelForm();
		
		///////////////////////////////////Field limitation,Data//////////////////////////////
		
		//Verify Parameter lookup is limited by Channel Class
		
		Reporter.log("Verify channelParameter lookup is limited by Channel Class", true);
		
		clickLookup(ADD_CHANEL_GAUGES_FORM_CLASS, "channelParameter");
		
		lookupId = getXWindowId("Select a Parameter");
		
		Reporter.log("Check if Parameter lookup is limited by Channel Class", true);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "BLDAREA", "@id", lookupId), "BLDAREA parameter is  present on parameter lookup");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "BLDVOL", "@id", lookupId), "BLDVOL parameter is  present on parameter lookup");
				
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "PREGAUGEPARAMETER1", "@id", lookupId), "PREGAUGEPARAMETER1 parameter is  present on parameter lookup");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Gauge", "@id", lookupId), "Gauge parameter class is present on parameter lookup");
		
		softAssert.assertFalse(Grid.isRowInGridPresent(driver, "Meter", "@id", lookupId), "Meter parameter class is not present on parameter lookup");
		
		softAssert.assertFalse(Grid.isRowInGridPresent(driver, "Weather", "@id", lookupId), "Weather parameter class is not present on parameter lookup");
		
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		reopenGaugeChannelForm();
		
		waitForExtJSAjaxComplete(20);
		
		//Verify UOM lookup is limited by Channel Class Meter
		
		Reporter.log("Verify UOM lookup is limited by Channel Class", true);
				
		clickLookup(ADD_CHANEL_GAUGES_FORM_CLASS, "channelParameter");
				
		lookupId = getXWindowId("Select a Parameter");
				
		Reporter.log("Check if UOM lookup is limited by Channel Class", true);
		
		//setChannelParameter(channelParameter);
		setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, channelParameter, "Name");
		
		waitForExtJSAjaxComplete(20);
		
		clickLookup(ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure");
		
		waitForExtJSAjaxComplete(20);
		
		lookupId = getXWindowId("Select a Unit Of Measure");
				
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "BTU", "Code"), "BTU UOM is not present on UOM lookup");
		
		waitForExtJSAjaxComplete(20);
		
		clickLookup(ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure");
		
		waitForExtJSAjaxComplete(20);
		
		lookupId = getXWindowId("Select a Unit Of Measure");
				
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "cal", "Code"), "Caloie UOM is not present on UOM lookup");
		
		waitForExtJSAjaxComplete(20);
		
		clickLookup(ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure");
		
		waitForExtJSAjaxComplete(20);
		
		lookupId = getXWindowId("Select a Unit Of Measure");
						
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "J", "Code"), "Joule UOM is not present on UOM lookup");
		
		clickLookup(ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
				
		softAssert.assertTrue(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "square meter", "Reference"), "square meter UOM is present on UOM lookup");
		
		clickLookup(ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertTrue(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "are", "Reference"), "are UOM is present on UOM lookup");
		
		clickLookup(ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
				
		softAssert.assertTrue(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "square millimeter", "Reference"), "square millimeter UOM is present on UOM lookup");
		
		clickLookup(ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
				
		softAssert.assertTrue(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "square meter", "Reference"), "square meter UOM is present on UOM lookup");
		
		clickLookup(ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "cl", "Code"), "cl UOM is not present on UOM lookup");
		
		clickLookup(ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "cal", "Code"), "cal UOM is not present on UOM lookup");
		
		clickLookup(ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "h", "Code"), "hour UOM is not present on UOM lookup");
		
		clickLookup(ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "in", "Code"), "inch UOM is not present on UOM lookup");
		
		clickLookup(ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "kJ", "Code"), "kilojoule UOM is not not present on UOM lookup");
		
		clickLookup(ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "km", "Code"), "km UOM is not present on UOM lookup");
		
		clickLookup(ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "mm", "Code"), "milimeter UOM is not present on UOM lookup");
				
		waitForExtJSAjaxComplete(20);
		
		reopenGaugeChannelForm();
		
		waitForExtJSAjaxComplete(20);
		
		//Verify Reading Interval is limited by Channel Class Meter
		
		softAssert.assertFalse(verifyItemExists("readingInterval", "15 minutes"), "15 minutes is not present on readingInterval dropdown");
		
		softAssert.assertTrue(verifyItemExists("readingInterval", "Hour"), "Hour is present on readingInterval dropdown");

		softAssert.assertTrue(verifyItemExists("readingInterval", "Day"), "Day is present on readingInterval dropdown");

		softAssert.assertTrue(verifyItemExists("readingInterval", "Month"), "Month is present on readingInterval dropdown");

		softAssert.assertTrue(verifyItemExists("readingInterval", "Year"), "Year is present on readingInterval dropdown");

		//softAssert.assertTrue(verifyItemExists("entryType", "Index Value"), "Index Value is present on entryType dropdown");

		softAssert.assertTrue(verifyItemExists("entryType", "Usage Value"), "Usage Value is present on entryType dropdown");

		softAssert.assertTrue(verifyItemExists("entryMethod", "Manual"), "Manual is present on entryMethod dropdown");

		if(build14 || build122){
			softAssert.assertTrue(verifyItemExists("entryMethod", "Calculated"), "Calculated is present on entryMethod dropdown");
		}

		softAssert.assertTrue(verifyItemExists("entryMethod", "Automatic"), "Automatic is present on entryMethod dropdown");
		
		softAssert.assertTrue(verifyItemExists("calculationMethod", "Sum"), "Sum is present on calculationMethod dropdown");
		
		softAssert.assertTrue(verifyItemExists("calculationMethod", "Latest Value"), "Latest Value is present on calculationMethod dropdown");

		/////////////////////verifying disabled fields///////////////////////////
		
		Reporter.log("verifying disabled fields", true);
		
		//reopenGaugeChannelForm();
		 
		 close(ADD_CHANEL_GAUGES_FORM_CLASS);
		 
		 waitForExtJSAjaxComplete(20);
		 
		// try { clickOnDialogButton("Yes"); }
		 
		 //catch (Exception e) {}
		 
		 
		 
		 boolean status = Grid.isRowInGridPresentLike(driver, channelParameter, "@class", ADD_GAUGES_FORM_CLASS);
			
			if(status) {
				
				Grid.checkRowInGriByTextValueAndClick(driver, ADD_GAUGES_FORM_CLASS, channelParameter);
					
				clickDeleteButton(ADD_GAUGES_FORM_CLASS);

				waitForExtJSAjaxComplete(10);
			}
	 
		 waitForExtJSAjaxComplete(20);
     
		 gaugesDialogId = getXWindowIdByClass(ADD_GAUGES_FORM_CLASS);
	 
		 clickButton("Add", gaugesDialogId);
     
		 waitForExtJSAjaxComplete(20); 
	 
		
		setChnlEntryMethod("Automatic");
		
		softAssert.assertTrue(checkChannelInputDisabled("code"), "Input Element code is disabled.");
		
		softAssert.assertTrue(checkChannelInputDisabled("class"), "Input Element class is disabled.");
		
		softAssert.assertTrue(checkChannelInputDisabled("entryType"), "Input Element entryType is disabled.");
		
		/////////////////////verifying disabled fields in edit form state TODO WITH Measuarment///////////////////////////
		
		Reporter.log("verifying disabled fields in edit form state", true);
		
		setReference(reference);
		
		waitForExtJSAjaxComplete(20);
		
		//setChannelParameter(channelParameter);
		clickLookup(ADD_CHANEL_GAUGES_FORM_CLASS, "channelParameter");
		lookupId = getXWindowId("Select a Parameter");
		setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, channelParameter, "Name");
		
		waitForExtJSAjaxComplete(25);
		
		setUnitOfMeasure(channelUOM);
		
		waitForExtJSAjaxComplete(25);
		
		save(ADD_CHANEL_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		close(ADD_CHANEL_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", ADD_GAUGES_FORM_CLASS, channelRefWithMeasurement);
		
		waitForExtJSAjaxComplete(20);
		
		gaugesDialogId = getXWindowIdByClass(ADD_GAUGES_FORM_CLASS);
		
		clickButton("Edit", gaugesDialogId);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkChannelInputDisabled("code"), "Input Element code is disabled.");
		
		softAssert.assertTrue(checkChannelInputDisabled("class"), "Input Element class is disabled.");
		
		softAssert.assertTrue(checkChannelInputDisabled("calculationMethod"), "Input Element calculationMethod is disabled.");
		
		softAssert.assertTrue(checkChannelInputDisabled("channelParameter"), "Input Element channelParameter is disabled.");
		
		softAssert.assertTrue(checkChannelInputDisabled("entryType"), "Input Element entryType is disabled.");
		
		softAssert.assertTrue(checkChannelInputDisabled("unitOfMeasure"), "Input Element unitOfMeasure is disabled.");
		
		softAssert.assertTrue(checkChannelInputDisabled("readingInterval"), "Input Element readingInterval is disabled.");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
	 }
	 
	 
}