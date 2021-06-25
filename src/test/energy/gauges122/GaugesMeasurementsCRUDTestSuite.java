package test.energy.gauges122;

import java.io.IOException;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.energy.gauges.GaugesMeasurementsPageObject;
import test.energy.gauges.MeteringGaugesChanelsPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class GaugesMeasurementsCRUDTestSuite extends MeteringGaugesPageObject {
	
		
	/**
	 * This test method uses some preconditions to be created manually (in database) before test run:
	 * Metering gauge (code: "preGaugeMeasure") with Channels "preGaugeMeasureCRUD" should be created in database
	 * this channel "preGaugeMeasureCRUD" should not contain any measurements
	 * 
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testGaugeMeasurementCreateEdit() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Add/Edit Gauge Measurement: c11604 " + " </span><br>",
				true);

		Reporter.log("User adds/edits Gauge Measurement "  + " <br>",
				true);
	
		String gaugeCode = "preGaugeMeasure";
		String channelReference = "preGaugeMeasureCRUD";
		String value = "50";
		String valueEdited = "12";
		String date = "25-10-2011";
		String dateEdited = "03-02-2012";		
		String time = "03:55";
		String timeEdited = "18:02";
		String measurementType = "preMTref";
		String measurementTypeEdited = "DEFAULT";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("AddGaugeMeasurement");

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
		
		//Click on Measurements tab to be sure it is active - sometimes click is not performed? - try to reopen window and perform another click
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
		
		clickButton("Add", DIALOG_GAUGE_CHNL);
		
		waitForExtJSAjaxComplete(20);
		
		setTime(time);
				
		setDate(date);
		
		setMeasurementType(measurementType);
		
		waitForExtJSAjaxComplete(20);
		
		setValue(value);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
				
		checkRowInGriByTextValueAndClick(driver, date+" "+time+":00", DIALOG_GAUGE_CHNL);
		
		clickButton("Edit", DIALOG_GAUGE_CHNL);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Date/Time/Index Value/Measurement Type are correct after creation", true);		
		softAssert.assertEquals(getDate(),date, date+" Date after creation is wrong");
		
		softAssert.assertEquals(getTime(),time, time+" Time after creation is wrong");
		
		softAssert.assertEquals(getValue(),value, value+" Index Value after creation is wrong");	
		
		softAssert.assertEquals(getMeasurementType(DIALOG_MEASUREMENT),measurementType, measurementType+" Measurement Type after creation is wrong");
		
		setTime(timeEdited);
		
		setDate(dateEdited);
						
		setMeasurementType(measurementTypeEdited);
		
		waitForExtJSAjaxComplete(20);

		setValue(valueEdited);
				
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		checkRowInGriByTextValueAndClick(driver, dateEdited+" "+timeEdited+":00", DIALOG_GAUGE_CHNL);
		
		clickButton("Edit", DIALOG_GAUGE_CHNL);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Date/Time/Index Value/Measurement Type are correct after editing", true);
		softAssert.assertEquals(getDate(),dateEdited, dateEdited+" Date after edit is wrong");
		
		softAssert.assertEquals(getTime(),timeEdited, timeEdited+" Time after edit is wrong");
		
		softAssert.assertEquals(getValue(),valueEdited, valueEdited+" Index Value after edit is wrong");	
		
		softAssert.assertEquals(getMeasurementType(DIALOG_MEASUREMENT),measurementTypeEdited, measurementTypeEdited+" Measurement Type after edit is wrong");
				
		softAssert.assertAll();		
		
		Reporter.log("Gauge Measurement was succesfully created and edited", true);
					
	 }
		// TODO Auto-generated method stub
	
	/**
	 * Test Case ID : C60897
	 * Author : SSU
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testDeleteMeasurement() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Measurement: C60897" + " </span><br>",
				true);
		
		String gaugeCode = "preGaugeMeasure";
		String channelReference = "preGaugeMeasureCRUD";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testDeleteMeasurement");
		
		waitForExtJSAjaxComplete(30);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		openMeteringEntity("Gauges");

		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, gaugeCode);
		
		clickButton("Edit", GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		changeTab("channels");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//Select pre-configured Channel and go to View Measurements
		
		checkRowInGriByTextValueAndClick(driver, channelReference, ADD_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		clickViewMeasurementsButton();
		
		waitForExtJSAjaxComplete(20);
		
		boolean status = verifyButtonsAvailableInToolBar(CHANNEL_MEASUREMENTS_WINDOW_HEADER, "Add");
		
		softAssert.assertTrue(status, "Add button is Present in Channel Measurements ");
		
		status = verifyButtonsAvailableInToolBar(CHANNEL_MEASUREMENTS_WINDOW_HEADER, "Edit");
		
		softAssert.assertTrue(status, "Edit button is Present in Channel Measurements ");
		
		status = verifyButtonsAvailableInToolBar(CHANNEL_MEASUREMENTS_WINDOW_HEADER, "Delete");
		
		softAssert.assertFalse(status, "Delete button is Not Present in Channel Measurements ");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
			
		Reporter.log("Deleting Measurement option is not available in Gauges is successfully verified", true);
	}
	
}
