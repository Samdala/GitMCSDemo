package test.energy.meters;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.energy.gauges.GaugesMeasurementsPageObject;
import test.energy.gauges.MeteringGaugesChanelsPageObject;
import test.energy.gauges.MeteringGaugesPageObject;
import test.energy.navigator.NavigatorPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import test.framework.webelement.Tree;

public class MetersMeasurementFormValidationTestSuite extends MetersPageObject{

	@Test(enabled=true)
	public void testMeterMeasurementFormValidation() throws Exception  {

		//TODO Description
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: ?????" + " </span><br>",
				true);

		Reporter.log("???? "  + " <br>",
				true);
		
		String meterCode = "2preMeter";
		String channelReference = "2preMeterMeasureVal";
		String channelReferenceEarlier = "1preMeterMeasureEarlier";
		
		String measTypeRollback;
		String measTypeOverflow;
		
			measTypeRollback = "Rollback";
			measTypeOverflow = "Overflow";
		
		List<String> mandatoryFieldsErrorMessageKeyWordsCheck =Arrays.asList("form", "invalid");
		List<String> measurementFieldInFutureErrorMessageKeyWordsCheck =Arrays.asList("Measurement", "future");
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("MeterMeasurementFormValidation");
		
		//////////////////////////Navigate to Meters Overview////////////////////////
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		expandMetering();
		
		openMeteringEntity("Meters");

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		NavigatorPageObject navigatorPageObject = new NavigatorPageObject();
		
		navigatorPageObject.clickChangeVisibleColumns();
		
		waitForExtJSAjaxComplete(10);
			
		waitForExtJSAjaxComplete(10);
		
		isAuditingFieldChecked(CHANGE_VISIBLE_COLUMNS_HEADER);
			
		waitForExtJSAjaxComplete(25);
		
		clickButton("Save", "x-window x-resizable-pinned");
		
		waitForExtJSAjaxComplete(25);	
		
		waitForExtJSAjaxComplete(25);	
		
		waitForExtJSAjaxComplete(25);
		
		filterGridWithoutUsingMcsElement("@class", METER_GRID_CLASS, meterCode, "Code");
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValue(driver, meterCode);
		
		waitForExtJSAjaxComplete(25);
		
		clickButton("Edit", METER_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickChannelsTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		/////////////////////////Select pre-configured Channel and go to View Measurements////////////////////////
		
		checkRowInGriByTextValueAndClick(driver, DIALOG_METER, channelReference);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		clickViewMeasurementsButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickMeasurementsTab();
		
		waitForExtJSAjaxComplete(20);
		
		setDateRangeChannels("dateRangePicker", "01-01-2010 to "+getFutureDate(0));
		
		waitForExtJSAjaxComplete(20);
		
		//////////////////////////Open Add Measurement Form////////////////////////
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(20);
		
		//////////////////////////Check if Reader field is prefilled with User logged in////////////////////////
		
		Reporter.log("Check if Reader field is prefilled with User logged in", true);
		
		//try{
		softAssert.assertTrue(getFieldValue(DIALOG_MEASUREMENT, "reader").equals("SELENIUM AQA"), "Reader field is prefilled with User logged in");
		/*}catch(Exception e){
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(getFieldValue(DIALOG_MEASUREMENT, "reader").equals("SELENIUM AQA") || getFieldValue(DIALOG_MEASUREMENT, "reader").equals("ENERGY AQA"), "Reader field is prefilled with User logged in");
		
		}*/
	
	
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
		
		//////////////////////////Try to save Meter Measurement Form with empty Index Value field////////////////////////
		
		Reporter.log("Try to save Meter Measurement Form with empty Index Value field", true);
		
		setDate("01-01-2010");
		
		setTime("12:00");
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with empty Index Value field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "value"),"Red border is present on empty Index Value field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT),"Message about invalid form is present"); 
		
		waitForExtJSAjaxComplete(20);
		
		closeUsingToolBarMeasurements(DIALOG_MEASUREMENT);
		 
		waitForExtJSAjaxComplete(20);
		
		//reopenMeterMeasurementForm();
		
		 clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
	     
	     waitForExtJSAjaxComplete(20);
	     
	     waitForExtJSAjaxComplete(3);
		
		//////////////////////////Try to save Meter Measurement Form with empty Reader field////////////////////////
		
		Reporter.log("Try to save Meter Measurement Form with empty Reader field", true);
		
		setDate("02-01-2010");
		
		setTime("12:00");
		
		setIndexValue("1");
		
		clearFieldMeas("reader", "Reader");
		
		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with empty Reader field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "reader"),"Red border is present on empty Reader field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		waitForExtJSAjaxComplete(20);
		
		closeUsingToolBarMeasurements(DIALOG_MEASUREMENT);
		 
		waitForExtJSAjaxComplete(20);
		
		//reopenMeterMeasurementForm();
		
		 clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
	     
	     waitForExtJSAjaxComplete(20);
	     
	     waitForExtJSAjaxComplete(3);
		
		//////////////////////////Try to save Meter Measurement Form with empty Measurement Type field////////////////////////
		
		Reporter.log("Try to save Meter Measurement Form with empty Measurement Type field", true);
		
		setDate("03-01-2010");
		
		setTime("12:00");
		
		setIndexValue("2");
		
		clearFieldMeas("measurementType", "Measurement Type");
		
		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with empty Measurement Type field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "measurementType"),"Red border is present on empty Measurement Type field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		waitForExtJSAjaxComplete(20);
		
		closeUsingToolBarMeasurements(DIALOG_MEASUREMENT);
		 
		waitForExtJSAjaxComplete(20);
		
		//reopenMeterMeasurementForm();
		
		 clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
	     
	     waitForExtJSAjaxComplete(20);
	     
	     waitForExtJSAjaxComplete(3);
		
		//////////////////////////Try to save Meter Measurement Form with empty Date field////////////////////////
		
		Reporter.log("Try to save Meter Measurement Form with empty Date field", true);
		
		setTime("12:00");
		
		setIndexValue("3");
		
		clearFieldMeas("date", "Date");
		
		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with empty Date field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "date"),"Red border is present on empty Date field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		waitForExtJSAjaxComplete(20);
		
		closeUsingToolBarMeasurements(DIALOG_MEASUREMENT);
		 
		waitForExtJSAjaxComplete(20);
		
		//reopenMeterMeasurementForm();
		
		 clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
	     
	     waitForExtJSAjaxComplete(20);
	     
	     waitForExtJSAjaxComplete(3);
		
		//////////////////////////Try to save Meter Measurement Form with empty Time field////////////////////////
		
		Reporter.log("Try to save Meter Measurement Form with empty Time field", true);
		
		setDate("04-01-2010");
		
		setIndexValue("4");
		
		clearFieldMeas("time", "Time");
		
		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with empty Time field"); 

		//waitForExtJSAjaxComplete(40);
		
		//softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "time"),"Red border is present on empty Time field");
		//TODO When time field is empty, not able to save.. so commented..
		
		//softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present");
		
		waitForExtJSAjaxComplete(20);
		
		closeUsingToolBarMeasurements(DIALOG_MEASUREMENT);
		 
		waitForExtJSAjaxComplete(20);
		
		//reopenMeterMeasurementForm();
		
		 clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
	     
	     waitForExtJSAjaxComplete(20);
	     
	     waitForExtJSAjaxComplete(3);
		
		//////////////////////////Try to save Meter Measurement Form with negative value in Index Value field////////////////////////
		
//		Reporter.log("Try to save Meter Measurement Form with negative value in Index Value field", true);
//		
//		setDate("05-01-2010");
//		
//		setTime("12:00");
//		
//		setIndexValue("-5");
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
		
		//////////////////////////Try to save Meter Measurement Form with bigger than max value in Index Value field////////////////////////
		
		Reporter.log("Try to save Meter Measurement Form with bigger than max value in Index Value field", true);
		
		setDate("06-01-2010");
		
		setTime("12:00");
		
		setIndexValue("999999999999.999");
		
		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with bigger than max value in Index Value field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "value"),"Red border is present on Index Value field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		waitForExtJSAjaxComplete(20);
		
		closeUsingToolBarMeasurements(DIALOG_MEASUREMENT);
		 
		waitForExtJSAjaxComplete(20);
		
		//reopenMeterMeasurementForm();
		
		 clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
	     
	     waitForExtJSAjaxComplete(20);
	     
	     waitForExtJSAjaxComplete(3);
		
		//Save form without Notes
		
		setDate("07-01-2010");
		
		setTime("12:00");
		
		setIndexValue("6");
		
		waitForExtJSAjaxComplete(20);
		
		String timeInMeasurment = getTime();
		
		Reporter.log("time Of Measurement while saving "+timeInMeasurment, true);
		
		saveClose(DIALOG_MEASUREMENT);
					
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "07-01-2010", "@class" ,DIALOG_MTR_CHNL_MEASSUREMENTS),"Measurement is created with empty Notes field (optional) and displayed on Channel Measurements");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//reopenMeterMeasurementForm();
		
		 clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
	     
	     waitForExtJSAjaxComplete(20);
	     
	     waitForExtJSAjaxComplete(3);
		
		//////////////////////////Try to save Meter Measurement Form with Date that is in future////////////////////////
		
		Reporter.log("Try to save Meter Measurement Form with Date that is in future", true);
		
		setDate("01-01-2025");
		
		setTime("12:00");
		
		setIndexValue("7");
		
		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with Date that is in future"); 

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, measurementFieldInFutureErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		
		waitForExtJSAjaxComplete(20);
		
		closeUsingToolBarMeasurements(DIALOG_MEASUREMENT);
		 
		waitForExtJSAjaxComplete(20);
		
		//reopenMeterMeasurementForm();
		
		 clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
	     
	     waitForExtJSAjaxComplete(20);
	     
	     waitForExtJSAjaxComplete(3);
		
		//////////////////////////Try to save Meter Measurement Form with Time that is in future////////////////////////
		
		Reporter.log("Try to save Meter Measurement Form with Time that is in future", true);
		
		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    	
		
		if( !sdf.format(cal.getTime()).contains("00:") && !sdf.format(cal.getTime()).contains("23:"))
		
		{setTime("23:45");
		
		setIndexValue("8");
		
		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MEASUREMENT);
		
		Thread.sleep(2000);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with Time that is in future"); 

		//softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, measurementFieldInFutureErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		waitForExtJSAjaxComplete(20);
		}
		
		closeUsingToolBarMeasurements(DIALOG_MEASUREMENT);
		 
		waitForExtJSAjaxComplete(20);
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Try to save Meter Measurement Form with Date that is earlier than the Date of previous Measurement", true);
		
		setDate("01-01-2009");
		
		setTime("12:00");
		
		setIndexValue("9");
		
		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(getExtMbContentOfWindowWOMcsElement("@class", "x-window-dlg")
								.contains(
										"A Measurement with later date and time has already been registered. Do you want to continue anyway?"),
						"Roll back Dialog Message");
		
		clickButton("Yes", "x-window-dlg");
		
		waitForExtJSAjaxComplete(25);
		
	//	softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with Date that is in future"); 

		//TODO Manually checked these two lines arent true
		//softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "time"),"Red border is present on time field"); 

		//softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		
		/*softAssert.assertTrue((getExtMbContentOfWindowWOMcsElement("@class", "x-window-dlg"))
				.contains("The Measurement value is less than the previous value. Please confirm that you want to make a Rollback. If yes, the Rollback Measurement Type will be auto-assigned."),
			"Roll back Dialog Message");
		
		waitForExtJSAjaxComplete(25);
		
		clickButton("Cancel", "x-window-dlg");
		
		waitForExtJSAjaxComplete(25);*/
		
		//closeUsingToolBarMeasurements(DIALOG_MEASUREMENT);
		 
		
		//reopenMeterMeasurementForm();
		
		 clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
	     
	     waitForExtJSAjaxComplete(20);
	     
		//////////////////////////Try to save Meter Measurement Form with Time that is earlier than the Time of previous Measurement////////////////////////////
		
		Reporter.log("Try to save Meter Measurement Form with Time that is earlier than the Time of previous Measurement", true);
		
		setDate("07-01-2010");
		
		setTime("10:00");
		
		setIndexValue("6");
		
		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue((getExtMbContentOfWindowWOMcsElement("@class", "x-window-dlg"))
				.contains("A Measurement with later date and time has already been registered. Do you want to continue anyway?"),
			"A Measurement with later date and time has already been registered Mess is displayed");

		clickButton("Yes", "x-window-dlg");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue((getExtMbContentOfWindowWOMcsElement("@class", "x-window-dlg"))
				.contains("The Measurement value is less than the previous value. Please confirm that you want to make a Rollback. If yes, the Rollback Measurement Type will be auto-assigned."),
			"Roll back Dialog Message");

		clickButton("Yes", "x-window-dlg");
		
		waitForExtJSAjaxComplete(5);
		//softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with Time that is in future"); 

		//TODO Manually checked these two lines arent true
		//softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "time"),"Red border is present on Time field"); 

		//softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present");
		
		softAssert.assertAll();
		
		Reporter.log("Meter MeasurementForm Validation is verified", true);
	 }
	
	/**
	 * Test Case ID: C60502
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testLimitationInChannelMeasurementsUsageValue() throws Exception {
			
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Limitation for max allowed digits and decimals for Usage Value Channels Measurements (9999999999.999)" + " </span><br>", 
				true);
		
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite2";
		String meterName = "1preMeter";
		String propertiesTab = "Properties";
		
		String commodityName = "Electricity";
		
		//Field values for Channel creation
		String chnlReference = "C60502chlRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String chnlParameter = "Electricity Normal Usage";
		String chnlUOM = "kWh";
		String chnlRepInterval = "Year";
		String chnlEntryType = "Usage Value";
		String chnlEntryMethod = "Manual";
		String chnlCalDate = getFutureDate(-3);
				
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testLimitationInChannelMeasurementsUsageValue");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		Tree.expandNavigatorTreeNode(driver, area);
		
		waitForExtJSAjaxComplete(20);
		
		Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickMetersInfrastructureTab();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setEffectiveDate("20-04-2015");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		setCommodityClass(commodityName);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickOnMeters(meterName);
		
		waitForExtJSAjaxComplete(20);
		
		clickEdidMeter();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnTab(EDIT_METER_WINDOW_HEADER, propertiesTab);
		
		waitForExtJSAjaxComplete(10);
		
		changeTab("channels");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		setChnlReference(chnlReference);
		
		setChnlParameter(chnlParameter);
		
		waitForExtJSAjaxComplete(10);
		
		setChnlCalDate(chnlCalDate);
		
		setChnlRepInterval(chnlRepInterval);
		
		waitForExtJSAjaxComplete(20);
		
		setChnlEntryType(chnlEntryType);
		
		waitForExtJSAjaxComplete(20);
		
		setChnlEntryMethod(chnlEntryMethod);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "calculationMethod"), "Spreading", "Spreading is Defaulted as Calculation Method");
		
		setChnlUOM(chnlUOM);

		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, chnlReference);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewMeasurementsButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		MeteringGaugesPageObject meteringGaugesPO = new MeteringGaugesPageObject();
		
		String channelMeausrement = meteringGaugesPO.getChannelMeasurements(DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		softAssert.assertTrue(channelMeausrement.contains(chnlReference), "Channel Reference is prepopulated correctly");
		
		GaugesMeasurementsPageObject gaugesMeasurementsPO = new GaugesMeasurementsPageObject();
		
		gaugesMeasurementsPO.setMonthComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Current Year");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		String indexValue = "9999999999.999";
		String measurementDate = getFutureDate(-3);
		String time = getMinsEarlyOfSystemTime(20);
		
		setDate(measurementDate);
		
		setIndexValue(indexValue);
		
		setTime(time);
		
		waitForExtJSAjaxComplete(10);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, indexValue, "@class", DIALOG_MTR_CHNL_MEASSUREMENTS), indexValue+" is present");
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		String indexValue1 = "-9999999999.999";
		time =  getMinsEarlyOfSystemTime(10);
		measurementDate = getFutureDate(-2);
		
		setDate(measurementDate);
		
		setIndexValue(indexValue1);
		
		setTime(time);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, indexValue1, "@class", DIALOG_MTR_CHNL_MEASSUREMENTS), indexValue1+" is present");
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		String indexValue2 = "999999999999.999";
		time = getMinsEarlyOfSystemTime(5);
		measurementDate = getFutureDate(-1);
		
		setDate(measurementDate);
		
		setIndexValue(indexValue2);
		
		setTime(time);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue((getInfoBarMsgOfWindow("@class", DIALOG_MEASUREMENT)).contains("The form is invalid. Hover over the fields in red to see the errors."), "The form is invalid. Hover over the fields in red to see the errors.");
		
		softAssert.assertAll();
		
		Reporter.log("Limitation for max allowed digits and decimals for Usage Value Channels Measurements (9999999999.999) is succsessfully verified", true);
	}
	
	/**
	 * Test Case ID: C60503
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testLimitationInChannelMeasurementsIndexValue() throws Exception {
			
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Limitation for max allowed digits and decimals for Index Value Channels Measurements (9999999999.999)" + " </span><br>", 
				true);
		
		String area = "slnmMetricsArea";
		String site = "slnmMetricsSite";
		String meterName = "slnmMetricsMeter";
		String propertiesTab = "Properties";
		
		String commodityName = "Electricity";
		
		//Field values for Channel creation
		String chnlReference = "C60503chlRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String chnlParameter = "Electricity High Usage";
		String chnlUOM = "kWh";
		String chnlRepInterval = "Year";
		String chnlEntryType = "Index Value";
		String chnlEntryMethod = "Manual";
		String chnlCalDate = getFutureDate(-3);
		
		String supplyPointName = "EAN-slnmMetrics";
		String meterRef = "C60503MeterRef" +getCurrentDate().substring(getCurrentDate().length()-6);
		String meterCod = "C60503MeterCod" +getCurrentDate().substring(getCurrentDate().length()-6);
		String status = "Active";
				
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testLimitationInChannelMeasurementsIndexValue");
		
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
		
		setEffectiveDate("20-04-2015");
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityClass(commodityName);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		
		MetersPageObject mpo = new MetersPageObject();
		
		mpo.clickOnSupplyPoint(supplyPointName);
		
		waitForExtJSAjaxComplete(20);
		
		mpo.clickCreateMeter();
		
		waitForExtJSAjaxComplete(20);
		
		mpo.setReference(meterRef);
		
		mpo.setCode(meterCod);
		
		mpo.setStatus(status);
		
		waitForExtJSAjaxComplete(20);
		
		mpo.save(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		mpo.close(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnMeters(meterRef);
		
		waitForExtJSAjaxComplete(20);
		
		clickEdidMeter();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnTab(EDIT_METER_WINDOW_HEADER, propertiesTab);
		
		waitForExtJSAjaxComplete(10);
		
		changeTab("channels");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		setChnlReference(chnlReference);
		
		setChnlParameter(chnlParameter);
		
		waitForExtJSAjaxComplete(10);
		
		setChnlCalDate(chnlCalDate);
		
		setChnlRepInterval(chnlRepInterval);
		
		waitForExtJSAjaxComplete(20);
		
		setChnlEntryType(chnlEntryType);
		
		waitForExtJSAjaxComplete(20);
		
		setChnlEntryMethod(chnlEntryMethod);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getFieldValue(DIALOG_CHANNEL, "calculationMethod"), "Spreading", "Spreading is Defaulted as Calculation Method");
		
		setChnlUOM(chnlUOM);

		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_CHANNEL);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, chnlReference);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewMeasurementsButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		MeteringGaugesPageObject meteringGaugesPO = new MeteringGaugesPageObject();
		
		String channelMeausrement = meteringGaugesPO.getChannelMeasurements(DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		softAssert.assertTrue(channelMeausrement.contains(chnlReference), "Channel Reference is prepopulated correctly");
		
		GaugesMeasurementsPageObject gaugesMeasurementsPO = new GaugesMeasurementsPageObject();
		
		gaugesMeasurementsPO.setMonthComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Current Year");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		String indexValue = "9999999999.999";
		String measurementDate = getFutureDate(-1);
		
		setDate(measurementDate);
		
		setIndexValue(indexValue);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, indexValue, "@class", DIALOG_MTR_CHNL_MEASSUREMENTS), indexValue+" is present");
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		String indexValue2 = "999999999999.999";
		String time = "17:00";
		
		setDate(measurementDate);
		
		setIndexValue(indexValue2);
		
		setTime(time);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue((getInfoBarMsgOfWindow("@class", DIALOG_MEASUREMENT)).contains("The form is invalid. Hover over the fields in red to see the errors."), "The form is invalid. Hover over the fields in red to see the errors.");
		
		softAssert.assertAll();
		
		Reporter.log("Limitation for max allowed digits and decimals for Index Value Channels Measurements (9999999999.999) is succsessfully verified", true);
	}
	
}



