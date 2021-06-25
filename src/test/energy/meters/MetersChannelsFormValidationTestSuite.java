package test.energy.meters;

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
	public void testMeterChannelFormValidate() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Check Meter Channel form validation: TRUNK(C30538, C30539, C30578, 30541) 12.2 (C35036, C35037, C35035, C35034)" + " </span><br>",
				true);

		Reporter.log("User tries to create Meter Channel with different validations"  + " <br>",
				true);
		
		String lookupId;
		String meterReference = "1preMeter";
		String meterReferenceWithMeasure = "1preMeterMeasureEarlier";
		String prefix = "E_";
		String prefixLong = "Electricity " ;
		String normalUsage = "E_NORM_USAGE";
		String additionalUsage = "Electricity High Usage";
		String uom = "kWh";
		
		List<String> mandatoryFieldsErrorMessageKeyWordsCheck =Arrays.asList("form", "invalid");
		
		//Field values for Meter creation
		
		String reference = "test auto mtr " + getCurrentDate().substring(getCurrentDate().length()-6);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("MeterChannelFormValidation");

		//Navigate to Meters Overview
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		expandMetering();
		
		waitForExtJSAjaxComplete(25);
		
		openMeteringEntity("Meters");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		//Open pre-configured Meter and go to Channels tab
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", METER_GRID_CLASS, meterReference);
		
		clickButton("Edit", METER_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickChannelsTab();
		
		waitForExtJSAjaxComplete(20);
		
		//////////////////////////Create Channel////////////////////////
		
		clickButton("Add", DIALOG_METER, "channels-tab");
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20); 
		
		waitForExtJSAjaxComplete(25);
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
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_CHANNEL, "fileFormat"), "fileFormat field has correct UI");
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_CHANNEL, "dataSource"), "dataSource field has correct UI");
		
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
		
			softAssert.assertTrue(verifyItemExists("readingInterval", "15 Minutes"), "15 Minutes is not present on readingInterval dropdown");
		
		softAssert.assertTrue(verifyItemExists("readingInterval", "Hour"), "Hour is present on readingInterval dropdown");

		softAssert.assertTrue(verifyItemExists("readingInterval", "Day"), "Day is present on readingInterval dropdown");

		softAssert.assertTrue(verifyItemExists("readingInterval", "Month"), "Month is present on readingInterval dropdown");

		softAssert.assertTrue(verifyItemExists("readingInterval", "Year"), "Year is present on readingInterval dropdown");

		softAssert.assertTrue(verifyItemExists("entryType", "Usage Value"), "Usage Value is present on entryType dropdown");
		
		softAssert.assertTrue(verifyItemExists("entryType", "Index Value"), "Index Value is present on entryType dropdown");

		softAssert.assertTrue(verifyItemExists("entryMethod", "Manual"), "Manual is present on entryMethod dropdown");


		softAssert.assertTrue(verifyItemExists("entryMethod", "Automatic"), "Automatic is present on entryMethod dropdown");
		
		softAssert.assertTrue(verifyItemExists("calculationMethod", "Spreading"), "Spreading is present on calculationMethod dropdown");
		
		softAssert.assertFalse(verifyItemExists("calculationMethod", "Sum"), "Sum is not present on calculationMethod dropdown");
		
			softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "calculationMethod"), "Spreading", "Spreading is Defaulted as Calculation Method");
		
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
		
		softAssert.assertFalse(checkInputDisabled("entryType"), "Input Element entryType is disabled.");
		
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
	@Test(enabled=true)
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
		String chnlParameter = "Ampere";
		String chnlUOM = "A";
			
		String highUsageParameter = "Electricity High Usage";
		String highUsageUOM = "kWh";
		
		String powerFactorParameter = "Powerfactor";
		String powerFactorUOM = "-";
				
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
		
		setEffectiveDate("10-01-2015");
		
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
		
		clickButton("Add", DIALOG_METER, "channels-tab");
		
		waitForExtJSAjaxComplete(20);
		
		setChnlReference(chnlReference);
		
		setChnlParameter(chnlParameter);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "unitOfMeasure"), chnlUOM, "unitOfMeasure field is prefilled with correct value.");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "calculationMethod"), "Latest Value", "Latest Value is Defaulted as Calculation Method");
		
		waitForExtJSAjaxComplete(10);
		
		saveClose(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, chnlReference);
		
		waitForExtJSAjaxComplete(20);
		
		clickButton("Edit", DIALOG_METER, "channels-tab");
		
		waitForExtJSAjaxComplete(20);
		
		setChnlParameter(highUsageParameter);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "unitOfMeasure"), highUsageUOM, "unitOfMeasure field is prefilled with correct value.");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "calculationMethod"), "Spreading", "Spreading is Defaulted as Calculation Method");
		
		waitForExtJSAjaxComplete(10);
		
		saveClose(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, chnlReference);
		
		waitForExtJSAjaxComplete(20);
		
		clickButton("Edit", DIALOG_METER, "channels-tab");
		
		waitForExtJSAjaxComplete(20);
		
		setChnlParameter(powerFactorParameter);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "unitOfMeasure"), powerFactorUOM, "unitOfMeasure field is prefilled with correct value.");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "calculationMethod"), "Latest Value", "Latest Value is Defaulted as Calculation Method");
		
		waitForExtJSAjaxComplete(10);
		
		saveClose(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("Editing of the Channel Parameter is successfully verified", true);
	}
	
	/**
	 * Test Case ID: C60505
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testChannelsWithSameParameters() throws Exception {
			
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60505 Channels with the same Parameters" + " </span><br>", 
				true);
		
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite1";
		String supplyPointName = "codePrefixNonUnique";
		String propertiesTab = "Properties";
		
		String commodityName = "Electricity";
		String code = "C60505preMeter"+getCurrentDate().substring(getCurrentDate().length()-5);
		String codeNew = "C60505preMeter1"+getCurrentDate().substring(getCurrentDate().length()-5);
		String activeStatus = "Active";
		String meterReference = "RefpreMeter"+getCurrentDate().substring(getCurrentDate().length()-5);
		String meterReferenceNew = "RefpreMeter1"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		//Field values for Channel creation
		String chnlReference = "C60505chlRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String chnlCalDate = getFutureDate(-2);
				
		String powerFactorParameter = "Powerfactor";
		String powerFactorUOM = "-";
		
		String highUsageParameter = "Electricity High Usage";
		String highUsageUOM = "kWh";
			
		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testChannelsWithSameParameters");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
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
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
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
		
		waitForExtJSAjaxComplete(20);
		
		changeTab("channels");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", DIALOG_METER, "channels-tab");
		
		waitForExtJSAjaxComplete(20);
		
		setChnlReference(chnlReference);
		
		saveClose(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue((getInfoBarMsgOfWindow("@class", DIALOG_MTR_CHANNEL)).contains("This Channel Parameter is already available in the Meter/Gauge."), "This Channel Parameter is already available in the Meter/Gauge.");
		
		setChnlParameter(powerFactorParameter);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "unitOfMeasure"), powerFactorUOM, "unitOfMeasure field is prefilled with correct value.");
		
		saveClose(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, powerFactorParameter, "@class", DIALOG_METER), powerFactorParameter+" is present");
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, chnlReference);
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Edit", DIALOG_METER, "channels-tab");
		
		waitForExtJSAjaxComplete(20);
		
		setChnlCalDate(chnlCalDate);
		
		save(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "calibrationDate"), getFutureDate(-2), "Calibration date is saved and channel is editable");
		
		close(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, chnlReference);
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Edit", DIALOG_METER, "channels-tab");
		
		waitForExtJSAjaxComplete(20);
		
		setChnlParameter(highUsageParameter);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "unitOfMeasure"), highUsageUOM, "unitOfMeasure field is prefilled with correct value.");
		
		saveClose(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(10);
		
		close(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnSupplyPoint(supplyPointName);
		
		waitForExtJSAjaxComplete(20);
		
		clickCreateMeter();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnTab(ADD_METER_WINDOW_HEADER, propertiesTab);
		
		waitForExtJSAjaxComplete(10);
		
		setCode(codeNew);
		
		setReference(meterReferenceNew);
		
		setCommodity(commodityName);
		
		waitForExtJSAjaxComplete(10);
		
		setStatus(activeStatus);
		
		waitForExtJSAjaxComplete(10);
		
		save(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
				
		changeTab("channels");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", DIALOG_METER, "channels-tab");
		
		waitForExtJSAjaxComplete(20);
		
		setChnlParameter(powerFactorParameter);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "unitOfMeasure"), powerFactorUOM, "unitOfMeasure field is prefilled with correct value.");
		
		saveClose(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, powerFactorParameter, "@class", DIALOG_METER), powerFactorParameter+" is present");
		
		softAssert.assertAll();
		
		Reporter.log("Channels with the same Parameters is successfully verified" , true);
		
	}
	
	/**
	 * Test Case ID: C60506
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testImplicitExplicitChannelForGasCommodity() throws Exception {
			
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60506 Implicit/Explicit channels for Gas commodity class" + " </span><br>", 
				true);
		
		String area = "slnmMetricsArea";
		String site = "slnmMetricsSite";
		String supplyPointName = "EAN-GasTest";
		String propertiesTab = "Properties";
		
		String commodityName = "Natural gas";
		String code = "C60506preMeter"+getCurrentDate().substring(getCurrentDate().length()-5);
		String activeStatus = "Active";
		String meterReference = "RefpreMeter"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		String gasVolumeParameter = "Gas Normalized Volume";
		String gasVolumeUOM = "m³";
		
		String gasUsageParameter = "Gas Usage";
		String gasUsageUOM = "kWh";
			
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testImplicitExplicitChannelForGasCommodity");
		
		waitForExtJSAjaxComplete(30);
		
		waitAndClick(XPATH_ENERGY);
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
			
		waitForExtJSAjaxComplete(20);
			
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
		waitForMaskDisappear();
			
		waitForExtJSAjaxComplete(20);
		
		clickMetersInfrastructureTab();
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(5);
		
		setEffectiveDate("01-01-2014");
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityClass("Gas");
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnSupplyPoint(supplyPointName);
		
		waitForExtJSAjaxComplete(20);
		
		clickCreateMeter();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnTab(ADD_METER_WINDOW_HEADER, propertiesTab);
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(10);
		
		setCode(code);
		
		setReference(meterReference);
		
		setCommodity(commodityName);
		
		waitForExtJSAjaxComplete(10);
		
		setStatus(activeStatus);
		
		waitForExtJSAjaxComplete(10);
		
		save(DIALOG_METER);
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
				
		changeTab("channels");
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, gasUsageParameter);
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Edit", DIALOG_METER, "channels-tab");
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "channelParameter"), gasUsageParameter, "Gas Usage field is prefilled with correct value.");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "unitOfMeasure"), gasUsageUOM, "unitOfMeasure field is prefilled with correct value.");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "readingInterval"), "Day", "Day is Defaulted as Reading Interval");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "calculationMethod"), "Spreading", "Spreading is Defaulted as Caculation Method");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "entryType"), "Usage Value", "Usage Value is Defaulted as Entry Type");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "entryMethod"), "Manual", "Manual is Defaulted as Entry Method");
		
		waitForExtJSAjaxComplete(10);
		
		setChnlParameter(gasVolumeParameter);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "channelParameter"), gasVolumeParameter, "Gas Volume field is prefilled with correct value.");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "unitOfMeasure"), gasVolumeUOM, "unitOfMeasure field is prefilled with correct value.");
		
		saveClose(DIALOG_CHANNEL);
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, gasVolumeParameter, "@class", DIALOG_METER), gasVolumeParameter+" is present");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, gasUsageParameter, "@class", DIALOG_METER), gasUsageParameter+" is present");
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, gasUsageParameter);
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Edit", DIALOG_METER, "channels-tab");
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue((getInfoBarMsgOfWindow("@class", DIALOG_CHANNEL))
				.contains("Implicit Channels cannot be edited. You must create a new Channel to make it explicit."), "Implicit Channels cannot be edited. You must create a new Channel to make it explicit.");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "entryMethod"), "Calculated", "Calculated is Defaulted as Entry Method");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "reference", "class").contains("readonly"), "Reference field is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "code", "class").contains("readonly"), "Code field is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "channelParameter", "class").contains("readonly"), "Channel Parameter field is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "unitOfMeasure", "class").contains("readonly"),  "unitOfMeasure field is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "readingInterval", "class").contains("readonly"),  "Reading Interval is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "class", "class").contains("readonly"),  "Class is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "calculationMethod", "class").contains("readonly"),  "Caculation Method is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "entryType", "class").contains("readonly"),  "Entry Type is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "entryMethod", "class").contains("readonly"),  "Entry Method is read only.");
		
		waitForExtJSAjaxComplete(10);
		
		String entryMethodValue = getFieldValue(DIALOG_CHANNEL, "entryMethod", "textContent");
		
		waitForExtJSAjaxComplete(10);
		
		close(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, gasUsageParameter);
		
		waitForExtJSAjaxComplete(10);
		
		clickViewMeasurementsButton();
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(verifyMeasurementsTab(entryMethodValue), "Measurements Tab is not available");
		
		waitForExtJSAjaxComplete(25);
		
		closeUsingToolBarMeasurements(DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(25);
		
		clickButton("Add", DIALOG_METER, "channels-tab");
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(10);
		
		setChnlParameter(gasUsageParameter);
		
		waitForExtJSAjaxComplete(10);
		
		saveClose(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, "Calculated", "@class", DIALOG_METER), "Calculated is Absent");
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, gasUsageParameter);
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Edit", DIALOG_METER, "channels-tab");
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(getFieldValue(DIALOG_CHANNEL, "reference", "class").contains("readonly"), "Reference field is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "code", "class").contains("readonly"), "Code field is read only.");
		
		softAssert.assertFalse(getFieldValue(DIALOG_CHANNEL, "channelParameter", "class").contains("readonly"), "channelParameter field is read only.");
		
		softAssert.assertFalse(getFieldValue(DIALOG_CHANNEL, "unitOfMeasure", "class").contains("readonly"),  "unitOfMeasure field is read only.");
		
		softAssert.assertFalse(getFieldValue(DIALOG_CHANNEL, "readingInterval", "class").contains("readonly"),  "Reading Interval is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "class", "class").contains("readonly"),  "Class is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "calculationMethod", "class").contains("readonly"),  "Calculation Method is read only.");
		
		softAssert.assertFalse(getFieldValue(DIALOG_CHANNEL, "entryType", "class").contains("readonly"),  "Entry Type is read only.");
		
		softAssert.assertFalse(getFieldValue(DIALOG_CHANNEL, "entryMethod", "class").contains("readonly"),  "Entry Method is read only.");
		
		waitForExtJSAjaxComplete(10);
		
		entryMethodValue = getFieldValue(DIALOG_CHANNEL, "entryMethod", "textContent");
		
		waitForExtJSAjaxComplete(10);
		
		close(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(10);
		
		close(DIALOG_METER);
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickOnSupplyPoint(meterReference);
		
		waitForExtJSAjaxComplete(25);
		
		clickEdidMeter();
		
		waitForExtJSAjaxComplete(25);
		
		clickChannelsTab();
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, gasUsageParameter);
		
		waitForExtJSAjaxComplete(10);
		
		clickViewMeasurementsButton();
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(verifyMeasurementsTab(entryMethodValue), "Measurements Tab is available");
		
		clickMeasurementsTab();
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		String indexValue = "90";
		String measurementDate = getFutureDate(0);
		
		System.out.println("measurementDate "+measurementDate);
		
		setDate(measurementDate);
		
		setIndexValue(indexValue);
		
		saveClose(DIALOG_MEASUREMENT);
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(25);
		
		indexValue =indexValue+".000";
		
		boolean status = Grid.isRowInGridPresent(driver, indexValue, "@class", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		if(status){
		
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, indexValue, "@class", DIALOG_MTR_CHNL_MEASSUREMENTS), "Index value is Present");
			
			closeUsingToolBarMeasurements(DIALOG_MTR_CHNL_MEASSUREMENTS);
			
			waitForExtJSAjaxComplete(25);
		} else{
			closeUsingToolBarMeasurements(DIALOG_MTR_CHNL_MEASSUREMENTS);
			
			waitForExtJSAjaxComplete(25);
			
			clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
			
			System.out.println("measurementDate "+measurementDate);
			
			setDate(measurementDate);
			
			setIndexValue(indexValue);
			
			saveClose(DIALOG_MEASUREMENT);
			
			waitForExtJSAjaxComplete(25);
		}
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();

		Reporter.log("Implicit/Explicit channels for Gas commodity class is successfully verified", true);
	}
	
	/**
	 * Test Case ID: C60507
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testImplicitExplicitChannelForFuelCommodity() throws Exception {
			
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60507 Implicit/Explicit channels for Fuel commodity class" + " </span><br>", 
				true);
		
		String area = "slnmMetricsArea";
		String site = "slnmMetricsSite";
		String supplyPointName = "EAN-FuelTest";
		String propertiesTab = "Properties";
		
		String commodityName = "Gasoline";
		String code = "C60507preMeter"+getCurrentDate().substring(getCurrentDate().length()-5);
		String activeStatus = "Active";
		String meterReference = "RefpreMeter"+getCurrentDate().substring(getCurrentDate().length()-5);
		
				
		String fuelVolumeParameter = "Fuel Volume";
		String fuelVolumeUOM = "m³";
		
		String fuelUsageParameter = "Fuel Usage";
		String fuelUsageUOM = "kWh";
			
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testImplicitExplicitChannelForGasCommodity");
		
		waitForExtJSAjaxComplete(30);
		
		waitAndClick(XPATH_ENERGY);
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
			
		waitForExtJSAjaxComplete(20);
			
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
		waitForMaskDisappear();
			
		waitForExtJSAjaxComplete(20);
		
		clickMetersInfrastructureTab();
		Thread.sleep(2000);
		
		
		waitForExtJSAjaxComplete(5);
		
		setEffectiveDate("01-01-2014");
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityClass("Fuel");
		
		Thread.sleep(2000);
		
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
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(20);
				
		changeTab("channels");
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, fuelUsageParameter);
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Edit", DIALOG_METER, "channels-tab");
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "channelParameter"), fuelUsageParameter, "Fuel Usage field is prefilled with correct value.");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "unitOfMeasure"), fuelUsageUOM, "unitOfMeasure field is prefilled with correct value.");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "readingInterval"), "Day", "Day is Defaulted as Reading Interval");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "calculationMethod"), "Spreading", "Spreading is Defaulted as Caculation Method");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "entryType"), "Usage Value", "Usage Value is Defaulted as Entry Type");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "entryMethod"), "Manual", "Manual is Defaulted as Entry Method");
		
		waitForExtJSAjaxComplete(10);
		
		setChnlParameter(fuelVolumeParameter);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "channelParameter"), fuelVolumeParameter, "Gas Volume field is prefilled with correct value.");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "unitOfMeasure"), fuelVolumeUOM, "unitOfMeasure field is prefilled with correct value.");
		
		saveClose(DIALOG_CHANNEL);
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, fuelVolumeParameter, "@class", DIALOG_METER), fuelVolumeParameter+" is present");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, fuelUsageParameter, "@class", DIALOG_METER), fuelUsageParameter+" is present");
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, fuelUsageParameter);
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Edit", DIALOG_METER, "channels-tab");
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue((getInfoBarMsgOfWindow("@class", DIALOG_CHANNEL))
				.contains("Implicit Channels cannot be edited. You must create a new Channel to make it explicit."), "Implicit Channels cannot be edited. You must create a new Channel to make it explicit.");
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "entryMethod"), "Calculated", "Calculated is Defaulted as Entry Method");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "reference", "class").contains("readonly"), "Reference field is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "code", "class").contains("readonly"), "Fuel Usage field is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "channelParameter", "class").contains("readonly"), "Fuel Usage field is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "unitOfMeasure", "class").contains("readonly"),  "unitOfMeasure field is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "readingInterval", "class").contains("readonly"),  "Reading Interval is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "class", "class").contains("readonly"),  "Class is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "calculationMethod", "class").contains("readonly"),  "Caculation Method is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "entryType", "class").contains("readonly"),  "Entry Type is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "entryMethod", "class").contains("readonly"),  "Entry Method is read only.");
		
		waitForExtJSAjaxComplete(10);
		
		String entryMethodValue = getFieldValue(DIALOG_CHANNEL, "entryMethod", "textContent");
		
		waitForExtJSAjaxComplete(10);
		
		close(DIALOG_CHANNEL);
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, fuelUsageParameter);
		
		waitForExtJSAjaxComplete(10);
		
		clickViewMeasurementsButton();
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(verifyMeasurementsTab(entryMethodValue), "Measurements Tab is not available");
		
		closeUsingToolBarMeasurements(DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", DIALOG_METER, "channels-tab");
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(10);
		
		setChnlParameter(fuelUsageParameter);
		
		waitForExtJSAjaxComplete(10);
		
		saveClose(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, "Calculated", "@class", DIALOG_METER), "Calculated is Absent");
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, fuelUsageParameter);
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Edit", DIALOG_METER, "channels-tab");
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(getFieldValue(DIALOG_CHANNEL, "reference", "class").contains("readonly"), "Reference field is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "code", "class").contains("readonly"), "Fuel Usage field is read only.");
		
		softAssert.assertFalse(getFieldValue(DIALOG_CHANNEL, "channelParameter", "class").contains("readonly"), "Fuel Usage field is read only.");
		
		softAssert.assertFalse(getFieldValue(DIALOG_CHANNEL, "unitOfMeasure", "class").contains("readonly"),  "unitOfMeasure field is read only.");
		
		softAssert.assertFalse(getFieldValue(DIALOG_CHANNEL, "readingInterval", "class").contains("readonly"),  "Reading Interval is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "class", "class").contains("readonly"),  "Class is read only.");
		
		softAssert.assertTrue(getFieldValue(DIALOG_CHANNEL, "calculationMethod", "class").contains("readonly"),  "Caculation Method is read only.");
		
		softAssert.assertFalse(getFieldValue(DIALOG_CHANNEL, "entryType", "class").contains("readonly"),  "Entry Type is read only.");
		
		softAssert.assertFalse(getFieldValue(DIALOG_CHANNEL, "entryMethod", "class").contains("readonly"),  "Entry Method is read only.");
		
		waitForExtJSAjaxComplete(10);
		
		entryMethodValue = getFieldValue(DIALOG_CHANNEL, "entryMethod", "textContent");
		
		waitForExtJSAjaxComplete(10);
		
		close(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(10);
		
		close(DIALOG_METER);
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickOnSupplyPoint(meterReference);
		
		waitForExtJSAjaxComplete(25);
		
		clickEdidMeter();
		
		waitForExtJSAjaxComplete(25);
		
		clickChannelsTab();
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, fuelUsageParameter);
		
		waitForExtJSAjaxComplete(10);
		
		clickViewMeasurementsButton();
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(verifyMeasurementsTab(entryMethodValue), "Measurements Tab is available");
		
		clickMeasurementsTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		Thread.sleep(2000);
		
		String indexValue = "90";
		String measurementDate = getFutureDate(0);
		
		System.out.println("measurementDate "+measurementDate);
		
		setDate(measurementDate);
		
		setIndexValue(indexValue);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		indexValue = indexValue +".000";
		
		boolean status = isRowInGridPresentCustomized("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Index Value", indexValue);
		
		if(status){
		
			softAssert.assertTrue(isRowInGridPresentCustomized("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Index Value", indexValue), "Index value is Present");
			
			closeUsingToolBarMeasurements(DIALOG_MTR_CHNL_MEASSUREMENTS);
			
			waitForExtJSAjaxComplete(25);
		} else{
			closeUsingToolBarMeasurements(DIALOG_MTR_CHNL_MEASSUREMENTS);
			
			waitForExtJSAjaxComplete(25);
			
			clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
			
			System.out.println("measurementDate "+measurementDate);
			
			setDate(measurementDate);
			
			setIndexValue(indexValue);
			
			saveClose(DIALOG_MEASUREMENT);
			
			waitForExtJSAjaxComplete(25);
		}
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("Implicit/Explicit channels for Fuel commodity class is successfully verified", true);
	}
}