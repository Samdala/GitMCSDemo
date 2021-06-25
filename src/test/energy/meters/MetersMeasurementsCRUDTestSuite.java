package test.energy.meters;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class MetersMeasurementsCRUDTestSuite extends MetersPageObject{

	@Test(enabled=true)
	public void testMeterMeasurementCreateEdit() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Add/Edit Meter Measurement: C19906" + " </span><br>",
				true);

		Reporter.log("User adds/edits Meter Measurement "  + " <br>",
				true);
		
		String meterCode = "3preMeter";
		String channelReference = "3preMeter"; //"1preMeterMeasureCRUD";
		String value = "50";
		String valueEdited = "12";
		String date = "25-10-2011";
		String dateEdited = "03-02-2012";		
		String time = "03:55";
		String timeEdited = "18:02";
		String measurementType = "preMTref";
		String measurementTypeEdited = "Default";
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("AddMeterMeasurement");
		
		//Navigate to Meters Overview
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		expandMetering();
		
		openMeteringEntity("Meters");

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		//Open pre-configured Meter and go to Channels tab
		
		Grid.checkRowInGriByTextValue(driver, meterCode);
		
		clickButton("Edit", METER_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickChannelsTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//Select pre-configured Channel and go to View Measurements
		
		checkRowInGriByTextValueAndClick(driver, DIALOG_METER, channelReference);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		clickViewMeasurementsButton();
		
		waitForExtJSAjaxComplete(20);
		
		//Click on Measurements tab to be sure it is active - sometimes click is not performed? - try to reopen window and perform another click
		try {clickMeasurementsTab();}
		catch(Exception e){
			
			driver.get(configuration.getApplicationUrl()+"?aqa");
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);

			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(20);
			
			expandMetering();
			
			openMeteringEntity("Meters");

			waitForExtJSAjaxComplete(20);
			
			Grid.checkRowInGriByTextValue(driver, meterCode);
			Reporter.log("Select Meter with code - " +meterCode, true);
			
			clickButton("Edit", METER_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			clickChannelsTab();
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			checkRowInGriByTextValueAndClick(driver, DIALOG_METER, channelReference);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);

			clickViewMeasurementsButton();
			
			waitForExtJSAjaxComplete(20);
			
			clickMeasurementsTab();
			
		}
		
		waitForExtJSAjaxComplete(20);
		
		String dateRange = date+" to "+getFutureDate(0);
		
		setDateRangeChannels("measurementsPopup_dateRangePicker", dateRange);
		
		//Add Measurement
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setTime(time);
				
		setDate(date);
		
		setMeasurementType(measurementType);
		
		waitForExtJSAjaxComplete(20);
		
		setIndexValue(value);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		//Edit created Measurement and check if fields were correctly saved on creation 
				
		checkRowInGriByTextValueAndClick(driver, DIALOG_MTR_CHNL_MEASSUREMENTS, date+" "+time+":00");
		
		clickButton("Edit", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Date/Time/Index Value/Measurement Type are correct after creation", true);		
		softAssert.assertEquals(getFieldValue(DIALOG_MEASUREMENT, "date"),date, "Is Date after creation correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MEASUREMENT, "time"),time, "Is Time after creation correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MEASUREMENT, "value"),value, "Is Index Value after creation correct?");	
		
		softAssert.assertEquals(getLookupValue(DIALOG_MEASUREMENT, "measurementType"),measurementType, measurementType+" Is Measurement Type after creation correct?");
		
		setTime(timeEdited);
		
		setDate(dateEdited);
						
		setMeasurementType(measurementTypeEdited);
		
		waitForExtJSAjaxComplete(20);

		setIndexValue(valueEdited);
				
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue((getExtMbContentOfWindowWOMcsElement("@class", "x-window-dlg"))
				.contains("The Measurement value is less than the previous value. Please confirm that you want to make a Rollback. If yes, the Rollback Measurement Type will be auto-assigned."),
			"Roll back Dialog Message");
		
		clickButton("Yes", "x-window-dlg");
		
		waitForExtJSAjaxComplete(20);
		
		//Check if fields were correctly saved on editing
		
		checkRowInGriByTextValueAndClick(driver, DIALOG_MTR_CHNL_MEASSUREMENTS, dateEdited+" "+timeEdited+":00");
		
		clickButton("Edit", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Date/Time/Index Value/Measurement Type are correct after editing", true);
		softAssert.assertEquals(getFieldValue(DIALOG_MEASUREMENT, "date"), dateEdited, "Is Date after editing correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MEASUREMENT, "time"),timeEdited, "Is Time after editing correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MEASUREMENT, "value"),valueEdited, "Is Index Value after editing correct?");	
		
		softAssert.assertEquals(getLookupValue(DIALOG_MEASUREMENT, "measurementType"), "Rollback", "Is Measurement Type after editing correct?");
				
		softAssert.assertAll();		
		
		Reporter.log("Meter Measurement was succesfully created and edited", true);
					
	 }
	
	/**
	 * Test Case ID : C60869
	 * Author : SSU
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testDeleteMeasurement() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Measurement: C60869" + " </span><br>",
				true);
		
		String meterCode = "1preMeter";
		String channelReference = "1preMeterMeasureCRUD";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testDeleteMeasurement");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		expandMetering();
		
		openMeteringEntity("Meters");

		waitForExtJSAjaxComplete(20);
		
		//Open pre-configured Meter and go to Channels tab
		
		Grid.checkRowInGriByTextValue(driver, meterCode);
		
		clickButton("Edit", METER_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickChannelsTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//Select pre-configured Channel and go to View Measurements
		
		checkRowInGriByTextValueAndClick(driver, DIALOG_METER, channelReference);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		clickViewMeasurementsButton();
		
		waitForExtJSAjaxComplete(20);
		
		boolean status = verifyButtonsAvailableInToolBar(CHANNEL_MEASUREMENTS_WINDOW_HEADER, "Add");
		
		softAssert.assertTrue(status, "Add button is Present in Channel Measurements ");
		
		status = verifyButtonsAvailableInToolBar(CHANNEL_MEASUREMENTS_WINDOW_HEADER, "Edit");
		
		softAssert.assertTrue(status, "Edit button is Present in Channel Measurements ");
		
		status = verifyButtonsAvailableInToolBar(CHANNEL_MEASUREMENTS_WINDOW_HEADER, "Delete");
		
		softAssert.assertTrue(status, "Delete button is Present in Channel Measurements ");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
	
		Reporter.log("Deleting Measurement option in Meters is successfully verified", true);
	}
}



