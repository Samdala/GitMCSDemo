package test.energy.meters122;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class MetersChannelsFormValidationTestSuite extends
		MetersPageObject {


	 @Test(enabled=true)
	public void testMeterChannelFormValidate() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Check Meter Channel form validation: TRUNK(C30538, C30539, C30578, 30541) 12.2 (C35036, C35037, C35035, C35034)" + " </span><br>",
				true);

		Reporter.log("User tries to create Meter Channel with different validations"  + " <br>",
				true);
		
		String lookupId;
		String meterReference = "1preMeter";
		String meterReferenceWithMeasure = "1preMeterMeasureEarlier";
		Boolean build122 = driver.getCurrentUrl().contains("122");
		String prefix = build122? "" : "E_";
		String prefixLong = build122? "" : "Electricity " ;
		String normalUsage = build122? "NORMAL_USAGE" : "E_NORM_USAGE";
		String additionalUsage = build122? "SPECIAL_USAGE" : "AMPERE";
		String uom = (!build122)? "kWh" :"kiloWatthour";
		
		List<String> mandatoryFieldsErrorMessageKeyWordsCheck =Arrays.asList("form", "invalid");
		
		//Field values for Meter creation
		
		String reference = "test auto mtr " + getCurrentDate().substring(getCurrentDate().length()-6);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("MeterChannelFormValidation");

		//Navigate to Meters Overview
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		openMeteringEntity("Meters");
		
		waitForExtJSAjaxComplete(20);
		
		//Open pre-configured Meter and go to Channels tab
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", METER_GRID_CLASS, meterReference);
		
		clickButton("Edit", METER_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		clickChannelsTab();
		
		waitForExtJSAjaxComplete(20);
		
		//////////////////////////Create Channel////////////////////////
		
		clickButton("Add", DIALOG_METER);
		
		waitForExtJSAjaxComplete(20); 
		
		////////////////////////DefaultValues////////////////////////
		
		Reporter.log("Check if Reference field is prefilled with ", true);
				
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "reference"), meterReference, "Reference field is prefilled with correct value.");
		
		Reporter.log("Check if channelParameter field is prefilled with ", true);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "channelParameter"), prefixLong + "Usage", "channelParameter field is prefilled with correct value.");
		
		Reporter.log("Check if unitOfMeasure field is prefilled with ", true);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "unitOfMeasure"), uom, "unitOfMeasure field is prefilled with correct value.");
		
		Reporter.log("Check if readingInterval field is prefilled with ", true);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "readingInterval"), "Day", "readingInterval field is prefilled with correct value.");
		
		Reporter.log("Check if entryType field is prefilled with ", true);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "entryType"), "Index Value", "entryType field is prefilled with correct value.");
		
		Reporter.log("Check if entryMethod field is prefilled with ", true);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "entryMethod"), "Manual", "entryMethod field is prefilled with correct value.");
		
		Reporter.log("Check if Class field is prefilled with ", true);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "class"), "Commodity Meter", "Class field is prefilled with correct value.");
		
		Reporter.log("Check if calculationMethod field is prefilled with ", true);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "calculationMethod"), "Spreading", "calculationMethod field is prefilled with correct value.");
		
		Reporter.log("Check if multiplier field is prefilled with ", true);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "multiplier"), "1", "multiplier field is prefilled with correct value.");
		
		
		//////////////////////////Check UI of mandatory fields (bold text, asterisk)////////////////////////
		
		Reporter.log("Check UI of mandatory fields (bold text, asterisk)", true);
		
		//softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_CHANNEL, "input", "code"), "Code field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_CHANNEL, "reference"), "Reference field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_CHANNEL, "unitOfMeasure"), "unitOfMeasure field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_CHANNEL, "readingInterval"), "readingInterval field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_CHANNEL, "entryType"), "entryType field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_CHANNEL, "calculationMethod"), "calculationMethod field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_CHANNEL, "multiplier"), "multiplier field has correct UI (Bold text, asterix)");
		
		//Check UI of non-mandatory fields (regular text)
		
		Reporter.log("Check UI of non-mandatory fields (regular text)", true);
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_CHANNEL, "calibrationDate"), "calibration Date field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_CHANNEL, "numberOfDials"), "numberOfDials field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_CHANNEL, "numberOfDecimals"), "numberOfDecimals field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_CHANNEL, "maxReading"), "maxReading field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_CHANNEL, "maxReadingOverflows"), "maxReadingOverflows field has correct UI");
		
		setChnlEntryMethod("Automatic");
		/*
		if(!build122) {
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_CHANNEL, "fileFormat"), "fileFormat field has correct UI");
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_CHANNEL, "dataSource"), "dataSource field has correct UI");
		
		}*/
		///////////////////////Clearing MANDATORY FIELDS AND SAVE/////////////////////////
		
		//Try to save Meter Channel Form with empty Reference field
		
		Reporter.log("Try to save Meter Channel Form with empty Reference field", true);
		
		waitForExtJSAjaxComplete(20);
		
		setChnlEntryMethod("Automatic");
		
		clearChnlField("reference", "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		save(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_CHANNEL),"Form can't be saved with empty Reference field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_CHANNEL, "reference"),"Red border is present on empty Reference field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_CHANNEL, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenMeterChannelForm();

		//Try to save Meter Channel Form with empty channelParameter field
		
		Reporter.log("Try to save Meter Form with empty channelParameter field", true);
		
		setChnlEntryMethod("Automatic");
		
		clearChnlField("channelParameter", "Parameter");
		
		waitForExtJSAjaxComplete(20);
		
		save(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_CHANNEL),"Form can't be saved with empty channelParameter field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_CHANNEL, "channelParameter"),"Red border is present on empty channelParameter field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_CHANNEL, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenMeterChannelForm();

		//Try to save Meter Channel Form with empty unitOfMeasure field
		
		Reporter.log("Try to save Meter Form with empty unitOfMeasure field", true);
		
		setChnlEntryMethod("Automatic");
		
		clearChnlField("unitOfMeasure", "UOM");
		
		waitForExtJSAjaxComplete(20);
		
		save(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_CHANNEL),"Form can't be saved with empty unitOfMeasure field");
		
		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_CHANNEL, "unitOfMeasure"),"Red border is present on empty unitOfMeasure field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_CHANNEL, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenMeterChannelForm();

		//Try to save Meter Channel Form with empty multiplier field
		
		Reporter.log("Try to save Meter Form with empty multiplier field", true);
		
		setChnlEntryMethod("Automatic");
		
		clearChnlField("multiplier", "Multiplier");
		
		waitForExtJSAjaxComplete(20);
		
		save(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_CHANNEL),"Form can't be saved with empty Multiplier field"); 
		
		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_CHANNEL, "multiplier"),"Red border is present on empty Multiplier field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_CHANNEL, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenMeterChannelForm();
		
		///////////////////////////////////Field limitation,Data//////////////////////////////
		
		//Verify Parameter lookup is limited by Channel Class
		
		Reporter.log("Verify channelParameter lookup is limited by Channel Class", true);
		
		clickLookup(DIALOG_CHANNEL, "channelParameter");
		
		lookupId = getXWindowId("Select a Parameter");
		
		Reporter.log("Check if Parameter lookup is limited by Channel Class", true);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, prefix + "LOW_USAGE", "@id", lookupId), "LOW_USAGE parameter is  present on parameter lookup");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, normalUsage, "@id", lookupId), normalUsage + " parameter is  present on parameter lookup");
				
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, prefix + "SPECIAL_USAGE", "@id", lookupId), "SPECIAL_USAGE parameter is  present on parameter lookup");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, prefix + "TOTAL_USAGE", "@id", lookupId), "TOTAL_USAGE parameter is  present on parameter lookup");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, prefix + "USAGE", "@id", lookupId), "USAGE parameter is  present on parameter lookup");
		
		softAssert.assertFalse(Grid.isRowInGridPresent(driver, "Gauge", "@id", lookupId), "Gauge parameter class is not present on parameter lookup");
		
		softAssert.assertFalse(Grid.isRowInGridPresent(driver, "Weather", "@id", lookupId), "Weather parameter class is not present on parameter lookup");
		
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		//Verify UOM lookup is limited by Channel Class Meter
		
		Reporter.log("Verify UOM lookup is limited by Channel Class", true);
				
		clickLookup(DIALOG_CHANNEL, "unitOfMeasure");
				
		lookupId = getXWindowId("Select a Unit Of Measure");
				
		Reporter.log("Check if UOM lookup is limited by Channel Class", true);
				
		softAssert.assertTrue(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "BTU", "Code"), "BTU UOM is  present on UOM lookup");
		
		clickLookup(DIALOG_CHANNEL, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
				
		softAssert.assertTrue(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "cal", "Code"), "Caloie UOM is  present on UOM lookup");
		
		clickLookup(DIALOG_CHANNEL, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
						
		softAssert.assertTrue(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "J", "Code"), "Joule UOM is  present on UOM lookup");
		
		clickLookup(DIALOG_CHANNEL, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
				
		softAssert.assertTrue(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "kcal", "Code"), "kcal UOM is  present on UOM lookup");
		
		clickLookup(DIALOG_CHANNEL, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertTrue(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "Wh", "Code"), "Wh UOM is  present on UOM lookup");
		
		clickLookup(DIALOG_CHANNEL, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
				
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "m", "Code"), "meter UOM is not present on UOM lookup");
		
		clickLookup(DIALOG_CHANNEL, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
				
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "mph", "Code"), "mph UOM is  present on UOM lookup");
		
		clickLookup(DIALOG_CHANNEL, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "cl", "Code"), "cl UOM is not present on UOM lookup");
		
		clickLookup(DIALOG_CHANNEL, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertTrue(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "cal", "Code"), "cal UOM is not present on UOM lookup");
		
		clickLookup(DIALOG_CHANNEL, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "h", "Code"), "hour UOM is not present on UOM lookup");
		
		clickLookup(DIALOG_CHANNEL, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "in", "Code"), "inch UOM is not present on UOM lookup");
		
		clickLookup(DIALOG_CHANNEL, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertTrue(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "kJ", "Code"), "kilojoule UOM is not present on UOM lookup");
		
		clickLookup(DIALOG_CHANNEL, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "km", "Code"), "km UOM is not present on UOM lookup");
		
		clickLookup(DIALOG_CHANNEL, "unitOfMeasure");
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, "mm", "Code"), "milimeter UOM is  present on UOM lookup");
				
		waitForExtJSAjaxComplete(20);
		
		//Verify Reading Interval is limited by Channel Class Meter
		
		softAssert.assertTrue(verifyItemExists("readingInterval", "15 minutes"), "15 minutes is not present on readingInterval dropdown");
		
		softAssert.assertTrue(verifyItemExists("readingInterval", "Hour"), "Hour is present on readingInterval dropdown");

		softAssert.assertTrue(verifyItemExists("readingInterval", "Day"), "Day is present on readingInterval dropdown");

		softAssert.assertTrue(verifyItemExists("readingInterval", "Month"), "Month is present on readingInterval dropdown");

		softAssert.assertTrue(verifyItemExists("readingInterval", "Year"), "Year is present on readingInterval dropdown");

		softAssert.assertTrue(verifyItemExists("entryType", "Usage Value"), "Usage Value is present on entryType dropdown");
		
		softAssert.assertTrue(verifyItemExists("entryType", "Index Value"), "Index Value is present on entryType dropdown");

		softAssert.assertTrue(verifyItemExists("entryMethod", "Manual"), "Manual is present on entryMethod dropdown");

		softAssert.assertTrue(verifyItemExists("entryMethod", "Calculated"), "Calculated is present on entryMethod dropdown");

		softAssert.assertTrue(verifyItemExists("entryMethod", "Automatic"), "Automatic is present on entryMethod dropdown");
		
		softAssert.assertTrue(verifyItemExists("calculationMethod", "Spreading"), "Spreading is present on calculationMethod dropdown");
		
		softAssert.assertFalse(verifyItemExists("calculationMethod", "Sum"), "Sum is not present on calculationMethod dropdown");
		
		softAssert.assertFalse(verifyItemExists("calculationMethod", "Latest Value"), "Latest Value is not present on calculationMethod dropdown");
		
		//Verifying numbers and logic on form 
		
		Reporter.log("Multiplier, number of digits, number of decimals, max reading", true);
		
		waitForExtJSAjaxComplete(20);
		
		setChnlNumDigits("-1");
		
		waitForExtJSAjaxComplete(20);
		
		setChnlNumDecimals("-1");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "numberOfDials"), "1", "numberOfDials  can not be negative.");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "numberOfDecimals"), "1", "numberOfDecimals can not be negative.");
		
		reopenMeterChannelForm();
		
		setChnlNumDigits("2");
		
		waitForExtJSAjaxComplete(20);
		
		setChnlNumDecimals("2");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "maxReading"), "99.99", "Max reading is correct.");
		
		waitForExtJSAjaxComplete(20);
		
		//setChnlMultiplier("0");
		clearChnlField("multiplier", "Multiplier");
		
		waitForExtJSAjaxComplete(20);
		
		save(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_CHANNEL),"Form can't be saved with non-unique multiplier field"); 
		
		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_CHANNEL, "multiplier"),"Red border is present on non-unique multiplier field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_CHANNEL, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about empty multiplier field is present"); 
		
		/////////////////////verifying disabled fields///////////////////////////
		
		Reporter.log("verifying disabled fields", true);
		
		reopenMeterChannelForm();
		
		setChnlEntryMethod("Automatic");

		setChnlMultiplier("1");
		
		softAssert.assertTrue(checkInputDisabled("code"), "Input Element code is disabled.");
		
		softAssert.assertTrue(checkInputDisabled("class"), "Input Element class is disabled.");
		
		softAssert.assertTrue(checkInputDisabled("maxReading"), "Input Element maxReading is disabled.");
		
		softAssert.assertTrue(checkInputDisabled("maxReadingOverflows"), "Input Element maxReadingOverflows is disabled.");
		
		/////////////////////verifying disabled fields in edit form state  WITH Measuarment///////////////////////////
		
		Reporter.log("verifying disabled fields in edit form state", true);
		
		setChnlReference(reference);
		
		setChnlParameter(additionalUsage);
		
		waitForExtJSAjaxComplete(20);
		
		save(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(20);
		
		close(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, meterReferenceWithMeasure);
		
		waitForExtJSAjaxComplete(20);
		
		clickButton("Edit", DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkInputDisabled("code"), "Input Element code is disabled.");
		
		softAssert.assertTrue(checkInputDisabled("class"), "Input Element class is disabled.");
		
		softAssert.assertTrue(checkInputDisabled("calculationMethod"), "Input Element calculationMethod is disabled.");
		
		softAssert.assertTrue(checkInputDisabled("channelParameter"), "Input Element channelParameter is disabled.");
		
		softAssert.assertTrue(checkInputDisabled("multiplier"), "Input Element multiplier is disabled.");
		
		softAssert.assertTrue(checkInputDisabled("unitOfMeasure"), "Input Element unitOfMeasure is disabled.");
		
		softAssert.assertTrue(checkInputDisabled("numberOfDials"), "Input Element numberOfDials is disabled.");
		
		softAssert.assertTrue(checkInputDisabled("readingInterval"), "Input Element readingInterval is disabled.");
		
		softAssert.assertTrue(checkInputDisabled("numberOfDecimals"), "Input Element numberOfDecimals is disabled.");
		
		softAssert.assertTrue(checkInputDisabled("entryType"), "Input Element entryType is disabled.");
		
		softAssert.assertTrue(checkInputDisabled("maxReading"), "Input Element maxReading is disabled.");
		
		softAssert.assertTrue(checkInputDisabled("entryMethod"), "Input Element entryMethod is disabled.");
		
		softAssert.assertTrue(checkInputDisabled("maxReadingOverflows"), "Input Element maxReadingOverflows is disabled.");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
	 }
	 
	 /**
	 * Test Case ID: C60504
	 * Author : SSU
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testEditingChannelParameter() throws Exception {
			
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60504 Editing of the Channel Parameter" + " </span><br>", 
				true);
		
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite2";
		String supplyPointName = "EAN-1preSP";
		String propertiesTab = "Properties";
		
		String commodityName = "Electricity";
		String code = "C60504preMeter"+getCurrentDate().substring(getCurrentDate().length()-5);
		String activeStatus = "Active";
		String meterReference = "RefpreMeter"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		//Field values for Channel creation
		String chnlReference = "C60504chlRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String chnlParameter = "Low Usage";
		String chnlUOM = "kiloWatthour";
			
		String specialUsageParameter = "Special Usage";
		String specialUsageUOM = "kiloWatthour";
		
		String totalUsageParameter = "Total Usage";
		String totalUsageUOM = "kiloWatthour";
				
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testEditingChannelParameter");
		
		waitForExtJSAjaxComplete(30);
		
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
		
		waitForExtJSAjaxComplete(5);
		
		setEffectiveDate("10-04-2015");
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityClass(commodityName);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnSupplyPoint(supplyPointName);
		
		waitForExtJSAjaxComplete(20);
		
		clickCreateMeter();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnTab(ADD_METER_WINDOW_HEADER, propertiesTab);
		
		waitForExtJSAjaxComplete(10);
		
		setCode(code);
		
		setReference(meterReference);
		
		setCommodity(commodityName);
		
		waitForExtJSAjaxComplete(10);
		
		setStatus(activeStatus);
		
		waitForExtJSAjaxComplete(10);
		
		save(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
				
		changeTab("channels");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		setChnlReference(chnlReference);
		
		setChnlParameter(chnlParameter);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "unitOfMeasure"), chnlUOM, "unitOfMeasure field is prefilled with correct value.");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "calculationMethod"), "Spreading", "Spreading is Defaulted as Calculation Method");
		
		waitForExtJSAjaxComplete(10);
		
		saveClose(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, chnlReference);
		
		waitForExtJSAjaxComplete(20);
		
		clickButton("Edit", DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		setChnlParameter(specialUsageParameter);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "unitOfMeasure"), specialUsageUOM, "unitOfMeasure field is prefilled with correct value.");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "calculationMethod"), "Spreading", "Spreading is Defaulted as Calculation Method");
		
		waitForExtJSAjaxComplete(10);
		
		saveClose(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, chnlReference);
		
		waitForExtJSAjaxComplete(20);
		
		clickButton("Edit", DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		setChnlParameter(totalUsageParameter);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "unitOfMeasure"), totalUsageUOM, "unitOfMeasure field is prefilled with correct value.");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "calculationMethod"), "Spreading", "Spreading is Defaulted as Calculation Method");
		
		waitForExtJSAjaxComplete(10);
		
		saveClose(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("Editing of the Channel Parameter is successfully verified", true);
	}
	
	
}