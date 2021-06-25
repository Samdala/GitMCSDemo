package test.energy.gauges122;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.energy.gauges.GaugesMeasurementsPageObject;
import test.energy.gauges.MeteringGaugesChanelsPageObject;
import test.energy.navigator.NavigatorPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class GaugesMeasurementFormValidationTestSuite extends MeteringGaugesPageObject{

	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testMeterMeasurementFormValidation() throws IOException  {

		//TODO Description
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: ?????" + " </span><br>",
				true);

		Reporter.log("???? "  + " <br>",
				true);
	
		String gaugeCode = "preGaugeMeasure";
		String channelReference = "1preGaugeMeasureVal";
		String channelReferenceEarlier = "1preGaugeMeasureEarlier";
		
		List<String> mandatoryFieldsErrorMessageKeyWordsCheck =Arrays.asList("form", "invalid");
		List<String> measurementFieldInFutureErrorMessageKeyWordsCheck =Arrays.asList("Measurement", "future");
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("GaugeMeasurementFormValidation");
		
		//////////////////////////Navigate to Meters Overview////////////////////////
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		openMeteringEntity("Gauges");

		waitForExtJSAjaxComplete(20);
		
		//Open pre-configured Gauge and go to Channels tab
		
		Grid.checkRowInGriByTextValue(driver, gaugeCode);
		
		//Fixed
		clickButton("Edit", GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		changeTab("channels");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		/////////////////////////Select pre-configured Channel and go to View Measurements////////////////////////
		
		checkRowInGriByTextValueAndClick(driver, channelReference, ADD_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		clickViewMeasurementsButton();
		
		waitForExtJSAjaxComplete(20);
		
		////////////////////////Click on Measurements tab to be sure it is active - sometimes click is not performed? - try to reopen window and perform another click////////////////////////
		try {clickMeasurementsTab();}
		catch(Exception e){
			
			driver.get(configuration.getApplicationUrl()+"?aqa");
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);

			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(20);
			
			expandMetering();
			
			openMeteringEntity("Gauges");

			waitForExtJSAjaxComplete(20);
			
			Grid.checkRowInGriByTextValue(driver, gaugeCode);
			Reporter.log("Select Gauge with code - " +gaugeCode, true);
			
			clickButton("Edit", GAUGES_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			changeTab("channels");
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			checkRowInGriByTextValueAndClick(driver, channelReference, ADD_GAUGES_FORM_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);

			clickViewMeasurementsButton();
			
			waitForExtJSAjaxComplete(20);
			
			clickMeasurementsTab();
			
		}
		
		waitForExtJSAjaxComplete(20);
		
		//////////////////////////Open Add Measurement Form////////////////////////
		
		clickButton("Add", DIALOG_GAUGE_CHNL);
		
		waitForExtJSAjaxComplete(20);
		
		//////////////////////////Check if Reader field is prefilled with User logged in////////////////////////
		
		Reporter.log("Check if Reader field is prefilled with User logged in", true);
		
		softAssert.assertEquals(getFieldValue(DIALOG_MEASUREMENT, "reader"), "SELENIUM AQA", "Reader field is prefilled with User logged in");
		
		//////////////////////////Check if Measurement Type field is prefilled with Type marked as Default//////////////////////////
		
		Reporter.log("Check if Measurement Type field is prefilled with Type marked as Default", true);
				
		String fieldValue = getFieldValue(DIALOG_MEASUREMENT, "measurementType");
				
		clickLookup(DIALOG_MEASUREMENT, "measurementType");
				
		String lookupId = getXWindowId("Select a Measurement Type");
			
		softAssert.assertTrue(checkDefaultField("@id", lookupId, "Reference", fieldValue), "Measurement Type field is prefilled with Type marked as Default");
				
		clickCancelXwindow();
		
		//////////////////////////Check UI of fields////////////////////////
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_MEASUREMENT, "date"), "Date field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_MEASUREMENT, "time"), "Time field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_MEASUREMENT, "value"), "Value field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_MEASUREMENT, "reader"), "Reader field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_MEASUREMENT, "measurementType"), "Measurement Type field has correct UI (Bold text, asterix)");
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_MEASUREMENT, "notes"), "Notes field has correct UI (common text)");
		
		//////////////////////////Try to save Gauge Measurement Form with empty Index Value field////////////////////////
		
		Reporter.log("Try to save Gauge Measurement Form with empty Index Value field", true);
		
		setDate("01-01-2010");
		
		setTime("12:00");
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with empty Index Value field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "value"),"Red border is present on empty Index Value field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		setTime("12:00");
		
		waitForExtJSAjaxComplete(20);
		
		reopenMeterMeasurementForm();
		
		//////////////////////////Try to save Gauge Measurement Form with empty Reader field////////////////////////
		
		Reporter.log("Try to save Gauge Measurement Form with empty Reader field", true);
		
		setDate("02-01-2010");
		
		setTime("12:00");
		
		setValue("1");
		
		clearFieldMeas("reader", "Reader");
		
		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with empty Reader field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "reader"),"Red border is present on empty Reader field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		setTime("12:00");
		
		waitForExtJSAjaxComplete(20);
		
		reopenMeterMeasurementForm();
		
		//////////////////////////Try to save Gauge Measurement Form with empty Measurement Type field////////////////////////
		
		Reporter.log("Try to save Gauge Measurement Form with empty Measurement Type field", true);
		
		setDate("03-01-2010");
		
		setTime("12:00");
		
		setValue("2");
		
		clearFieldMeas("measurementType", "Measurement Type");
		
		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with empty Measurement Type field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "measurementType"),"Red border is present on empty Measurement Type field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		setTime("12:00");
		
		waitForExtJSAjaxComplete(20);
		
		reopenMeterMeasurementForm();
		
		//////////////////////////Try to save Gauge Measurement Form with empty Date field////////////////////////
		
		Reporter.log("Try to save Gauge Measurement Form with empty Date field", true);
		
		setTime("12:00");
		
		setValue("3");
		
		clearFieldMeas("date", "Date");
		
		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with empty Date field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "date"),"Red border is present on empty Date field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		setTime("12:00");
		
		waitForExtJSAjaxComplete(20);
		
		reopenMeterMeasurementForm();
		
		//////////////////////////Try to save Gauge Measurement Form with empty Time field////////////////////////
		
		Reporter.log("Try to save Gauge Measurement Form with empty Time field", true);
		
		setDate("04-01-2010");
		
		setValue("4");
		
		clearFieldMeas("time", "Time");
		
		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with empty Time field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "time"),"Red border is present on empty Time field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		setDate("04-01-2010");
		
		waitForExtJSAjaxComplete(20);
		
		reopenMeterMeasurementForm();
		
		//////////////////////////Try to save Gauge Measurement Form with negative value in Index Value field////////////////////////
		
//		Reporter.log("Try to save Gauge Measurement Form with negative value in Index Value field", true);
//		
//		setDate("05-01-2010");
//		
//		setTime("12:00");
//		
//		setValue("-5");
//		
//		waitForExtJSAjaxComplete(20);
//		
//		saveClose(DIALOG_MEASUREMENT);
//		
//		waitForExtJSAjaxComplete(20);
//		
//		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with negative value in Index Value field"); 
//
//		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "time"),"Red border is present on Index Value field");
//
//		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT),"Message about invalid form is present"); 
//		
//		reopenMeterMeasurementForm();
		
		//////////////////////////Try to save Gauge Measurement Form with bigger than max value in Index Value field////////////////////////
		
		Reporter.log("Try to save Gauge Measurement Form with bigger than max value in Index Value field", true);
		
		setDate("06-01-2010");
		
		setTime("12:00");
		
		setValue("999999999999.999");
		
		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with bigger than max value in Index Value field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "value"),"Red border is present on Index Value field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		setTime("12:00");
		
		waitForExtJSAjaxComplete(20);
		
		reopenMeterMeasurementForm();
		
		//Save form without Notes
		
		setDate("07-01-2010");

		setTime("12:00");
		
		setValue("6");
		
		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MEASUREMENT);
					
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "07-01-2010 12:00:00", "@class" ,DIALOG_GAUGE_CHNL),"Measurement is created with empty Notes field (optional) and displayed on Channel Measurements");
		
		reopenMeterMeasurementForm();
		
		//////////////////////////Try to save Gauge Measurement Form with Date that is in future////////////////////////
		
		Reporter.log("Try to save Gauge Measurement Form with Date that is in future", true);
		
		setDate("01-01-2025");
		
		setTime("12:00");
		
		setValue("7");
		
		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with Date that is in future"); 

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, measurementFieldInFutureErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		setTime("12:00");
		
		waitForExtJSAjaxComplete(20);
		
		reopenMeterMeasurementForm();
		
		//////////////////////////Try to save Gauge Measurement Form with Time that is in future////////////////////////
		
		Reporter.log("Try to save Gauge Measurement Form with Time that is in future", true);
		
		setTime("23:59");
		
		setValue("8");
		
		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with Time that is in future"); 

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, measurementFieldInFutureErrorMessageKeyWordsCheck),"Message about invalid form is present");
		
		setTime("23:59");
		
		waitForExtJSAjaxComplete(20);
		
		reopenMeterMeasurementForm();
		
		//////////////////////////Try to save Gauge Measurement Form with Date that is earlier than the Date of previous Measurement////////////////////////////
		
		driver.get(configuration.getApplicationUrl()+"?aqa");
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		openMeteringEntity("Gauges");

		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, gaugeCode);
		Reporter.log("Select Gauge with code - " +gaugeCode, true);
		
		clickButton("Edit", GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		changeTab("channels");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		checkRowInGriByTextValueAndClick(driver, channelReferenceEarlier, ADD_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		clickViewMeasurementsButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickMeasurementsTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickButton("Add", DIALOG_GAUGE_CHNL);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Try to save Gauge Measurement Form with Date that is earlier than the Date of previous Measurement", true);
		
		setDate("01-01-2012");
		
		setTime("12:00");
		
		setValue("5");
		
		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with Date that is in future"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "date"),"Red border is present on Date field"); //TODO is it true?

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		setTime("12:00");
		
		waitForExtJSAjaxComplete(20);
		
		reopenMeterMeasurementForm();
		
		//////////////////////////Try to save Gauge Measurement Form with Time that is earlier than the Time of previous Measurement////////////////////////////
		
		Reporter.log("Try to save Gauge Measurement Form with Time that is earlier than the Time of previous Measurement", true);
		
		setDate("05-01-2012");
		
		setTime("11:00");
		
		setValue("6");
		
		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with Time that is in future"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "time"),"Red border is present on Time field"); //TODO is it true?

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		softAssert.assertAll();
		
	 }
	
	/**
	 * Test Case ID: C60467
	 * Author : SSU
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testLimitationInChannelMeasurementsIndexField() throws Exception {
			
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Limitation for max allowed digits and decimals for Channels Measurements (9999999999.999)" + " </span><br>", 
				true);
				
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testLimitationInChannelMeasurementsIndexField");
		
		String scope6 = "slnmEnrgBuilding7";
		Boolean build14 = driver.getCurrentUrl().contains("14");
		
		waitForExtJSAjaxComplete(30);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		NavigatorPageObject navigatorPO = new NavigatorPageObject();
		
		navigatorPO.expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		navigatorPO.setLocationValueTreeLookup(scope6);
			
		waitForExtJSAjaxComplete(20);
		
		String panelID = "";
		String locationType = "Building";
		String code = "C60467cod"+getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "C60467ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String statusActive = "Active";
		
		waitForExtJSAjaxComplete(20);
		
		if(!build14) {
		
			panelID = getXPanelId(locationType + " \"" +scope6 + "\"");
			
			waitForExtJSAjaxComplete(20);
		
			navigatorPO.changeTab(panelID, "Gauges");
		
			waitForExtJSAjaxComplete(20);
		
		} else {
			navigatorPO.changeTab("Gauges");
			
			waitForExtJSAjaxComplete(20);
		}
		
		waitForExtJSAjaxComplete(20);
		
		boolean status = Grid.isRowInGridPresentLike(driver, "Location Gauge", "@class", GAUGES_GRID_CLASS);
		
		if(status) {
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, "Location Gauge");
				
			navigatorPO.clickDeleteBtn();

			waitForExtJSAjaxComplete(10);
		}
			
		waitForExtJSAjaxComplete(10);
			
		navigatorPO.clickAddBtn();
			
		waitForExtJSAjaxComplete(10);
		
		setCode(code);
		
		setReference(reference);
		
		setStatus(statusActive);
		
		waitForExtJSAjaxComplete(10);
		
		save();
		
		waitForExtJSAjaxComplete(10);
		
		close();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, reference, "@class", GAUGES_GRID_CLASS), "Gauge is successfully added and correctly displayed");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, reference);
						
		navigatorPO.clickEditBtn();
				
		waitForExtJSAjaxComplete(20);
				
		waitForExtJSAjaxComplete(20);
				
		changeTab("channels");
				
		waitForExtJSAjaxComplete(20);
				
		Grid.checkRowInGriByTextValueAndClick(driver, ADD_GAUGES_FORM_CLASS, reference);
		
		waitForExtJSAjaxComplete(10);
				
		clickButton("Edit", "slnmGaugeId");
		
		waitForExtJSAjaxComplete(10);
		
		MeteringGaugesChanelsPageObject meteringGaugeChnlPO = new MeteringGaugesChanelsPageObject();
		
		String codeGauges = meteringGaugeChnlPO.getCode();
		
		String refGauges = meteringGaugeChnlPO.getReference();
		
		meteringGaugeChnlPO.close();
		
		waitForExtJSAjaxComplete(10);

		clickViewMeasurementsButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickMeasurementsTab();
		
		waitForExtJSAjaxComplete(20);
		
		String channelDropDownValue = refGauges +" ("+codeGauges+")";
		
		System.out.println(channelDropDownValue);
		
		setChannelMeasurements("slnmGaugeChnlMeasId", channelDropDownValue);
		
		waitForExtJSAjaxComplete(20);
		
		//softAssert.assertTrue(verifyGridIsEmpty().contains("No items available"), "No items available is present in the Grid");
		
		clickButton("Add", DIALOG_GAUGE_CHNL);
		
		String indexValue = "9999999999.999";
		String measurementDate = getFutureDate(-1);
		
		System.out.println("measurementDate "+measurementDate);
		
		setDate(measurementDate);
		
		setValue(indexValue);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, indexValue, "@class", DIALOG_GAUGE_CHNL), "'"+indexValue+"' Row is available in Channel Grid");
		
		clickButton("Add", DIALOG_GAUGE_CHNL);
		
		indexValue = "99999999999.999";
		measurementDate = getFutureDate(-1);
		
		setDate(measurementDate);
		
		setValue(indexValue);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue((getInfoBarMsgOfWindow("@class", DIALOG_MEASUREMENT)).contains("The form is invalid. Hover over the fields in red to see the errors."), "The form is invalid. Hover over the fields in red to see the errors.");
		
		closeUsingToolBarForDiaogMeasurement(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", DIALOG_GAUGE_CHNL);
		
		indexValue = "-999999999999.999";
		measurementDate = getFutureDate(-1);
		
		setDate(measurementDate);
		
		setValue(indexValue);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue((getInfoBarMsgOfWindow("@class", DIALOG_MEASUREMENT)).contains("The form is invalid. Hover over the fields in red to see the errors."), "The form is invalid. Hover over the fields in red to see the errors.");
		
		closeUsingToolBarForDiaogMeasurement(DIALOG_MEASUREMENT);
		
		softAssert.assertAll();
		
		Reporter.log("Limitation for max allowed digits and decimals for Channels Measurements (9999999999.999) is succsessfully verified", true);
	}
	
	
}



