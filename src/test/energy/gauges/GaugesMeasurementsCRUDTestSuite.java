package test.energy.gauges;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.remote.server.handler.interactions.ClickInSession;
import org.testng.Reporter;
import org.testng.annotations.Test;

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
	@Test(enabled=true)
	public void testGaugeMeasurementCreateEdit() throws Exception  {
//TODO MYM-32961 Issue Related to this
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
		String measurementTypeEdited = "Default";
		
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
		clickMeasurementsTab();
		
		waitForExtJSAjaxComplete(20);
		
		setDateRange(DIALOG_GAUGE_CHNL, "01-01-2010");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickButton("Add", DIALOG_GAUGE_CHNL);
		
		waitForExtJSAjaxComplete(25);
		
		Thread.sleep(10000);
		
		waitForExtJSAjaxComplete(25);
		
		setTime(time);
				
		setDate(date);
		
		setMeasurementType(measurementType);
		
		waitForExtJSAjaxComplete(20);
		
		setValue(value);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
				
		checkRowInGriByTextValueAndClick(driver, date+" "+time+":00", DIALOG_GAUGE_CHNL);
		
		clickButton("Edit", DIALOG_GAUGE_CHNL);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Date/Time/Index Value/Measurement Type are correct after creation", true);		
		softAssert.assertEquals(getDate(),date, date+" Date after creation is wrong");
		
		softAssert.assertEquals(getTime(),time, time+" Time after creation is wrong");
		
		softAssert.assertEquals(getValue(),value, value+" Index Value after creation is wrong");	
		
		softAssert.assertEquals(getMeasurementType(DIALOG_MEASUREMENT), measurementType, measurementType+" Measurement Type after creation is wrong");
		
		setTime(timeEdited);
		
		setDate(dateEdited);
						
		setMeasurementType(measurementTypeEdited);
		
		waitForExtJSAjaxComplete(20);

		setValue(valueEdited);
				
		saveClose(DIALOG_MEASUREMENT);
		
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
		
		//Delete Button is available in Trunk alone
		boolean status = verifyButtonsAvailableInToolBar(CHANNEL_MEASUREMENTS_WINDOW_HEADER, "Add");
		
		softAssert.assertTrue(status, "Add button is Present in Channel Measurements ");
		
		status = verifyButtonsAvailableInToolBar(CHANNEL_MEASUREMENTS_WINDOW_HEADER, "Edit");
		
		softAssert.assertTrue(status, "Edit button is Present in Channel Measurements ");
		
		status = verifyButtonsAvailableInToolBar(CHANNEL_MEASUREMENTS_WINDOW_HEADER, "Delete");
		
		softAssert.assertTrue(status, "Delete button is Not Present in Channel Measurements ");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
			
		Reporter.log("Deleting Measurement option is not available in Gauges is successfully verified", true);
	}
	
	/**
	 * Test Case ID : C60469
	 * Author : SSU
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class, priority =1)
	public void testDateRangeBehaviorOfYears() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Date Range behavior when Reading Interval is Year: C60469" + " </span><br>",
				true);
		
		String gaugeCode = "preGaugeMeasure";
		String channelReference = "C60469ChnlRef";
		String calibrationDate = getFutureDate(0);
		String channelParameter = "Building Headcount";
		String channelUOM = "p";
		String readingInterval = "Year";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testDateRangeBehaviorOfYears");
		
		waitForExtJSAjaxComplete(30);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		expandMetering();
		
		waitForExtJSAjaxComplete(25);
		
		openMeteringEntity("Gauges");

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValue(driver, gaugeCode);
		
		clickButton("Edit", GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		changeTab("channels");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//Select pre-configured Channel and go to View Measurements
		
		MeteringGaugesChanelsPageObject meteringGaugesChnlPO = new MeteringGaugesChanelsPageObject();
		
		boolean status = Grid.isRowInGridPresent(driver, channelParameter, "@class", ADD_GAUGES_FORM_CLASS);
		
		if(status) {
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, channelParameter);
				
			clickButton("Delete", "slnmGaugeId");
			
			clickOnDialogButton("Yes");

			waitForExtJSAjaxComplete(10);
		}
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", "slnmGaugeId");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		meteringGaugesChnlPO.setReference(channelReference);
		
		meteringGaugesChnlPO.setCalibrationDate(calibrationDate);
		
		waitForExtJSAjaxComplete(25);
		
		meteringGaugesChnlPO.setChannelParameter(channelParameter);
		
		waitForExtJSAjaxComplete(25);
		
		meteringGaugesChnlPO.setUnitOfMeasure(channelUOM);
		
		waitForExtJSAjaxComplete(25);
		
		meteringGaugesChnlPO.setChnlReadingInterval(readingInterval);
		
		waitForExtJSAjaxComplete(5);
		
		saveClose("slnmGaugeChnlId");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		checkRowInGriByTextValueAndClick(driver, channelReference, ADD_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(20);

		clickButton("Edit", "slnmGaugeId");
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(25);
		
		readingInterval = meteringGaugesChnlPO.getFieldValue("slnmGaugeChnlId", "readingInterval");
		
		if(!(readingInterval.contains("Year"))){
			
			meteringGaugesChnlPO.setChnlReadingInterval("Year");
			
			meteringGaugesChnlPO.save("slnmGaugeChnlId");
			
			waitForExtJSAjaxComplete(10);
		}
		
		meteringGaugesChnlPO.close("slnmGaugeChnlId");
		
		waitForExtJSAjaxComplete(20);

		clickViewMeasurementsButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickMeasurementsTab();
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(25);
		
		GaugesMeasurementsPageObject gaugesMeasurementsPO = new GaugesMeasurementsPageObject();
		
		String initialDateRangeMeasurement = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("initialDateRangeMeasurement Year : " +initialDateRangeMeasurement);
		
		String systemDate = getFutureDate(0);
		
		int currentYear = gaugesMeasurementsPO.getCurrentYear(systemDate);
		
		System.out.println("currentYear : " +currentYear);
		
		int last3Yrs = currentYear - 3;
		
		int ending3Yrs = currentYear - 1;
		
		String dateManipulationYear = "01-01-"+last3Yrs+" to 31-12-"+ending3Yrs+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(initialDateRangeMeasurement, dateManipulationYear, "Date Range of Last 3 Years is Defaulted");
		
		List<String> dateValuesNew = gaugesMeasurementsPO.getDatePickerDropDownValues("measurementsPopup_dateRangePicker");
		
		waitForExtJSAjaxComplete(10);
				
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Current Year"), "Current Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last Year"), "Last Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last 3 Years"), "Last 3 Years is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Reset"), "Reset is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Custom Range"), "Custom Range is present in list");
		
		waitForExtJSAjaxComplete(5);
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Current Year");
		
		waitForExtJSAjaxComplete(20);
		
		// Current Year
		
		String dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("dateRangeMeasurements Current Year : " +dateRangeMeasurements);
		
		dateManipulationYear = "01-01-"+currentYear+" to 31-12-"+currentYear+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(dateRangeMeasurements, dateManipulationYear, "Date Range is Correctly displayed for the current Year");
		
		// For Last Year
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Last Year");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("dateRangeMeasurements Last Year : " +dateRangeMeasurements);
		
		int lastYr = currentYear - 1;
		
		dateManipulationYear = "01-01-"+lastYr+" to 31-12-"+lastYr+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(dateRangeMeasurements, dateManipulationYear, "Date Range is Correctly displayed for the Last Year");
		
		// For Last 3 Years
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Last 3 Years");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("dateRangeMeasurements Last 3 Years : " +dateRangeMeasurements);
		
		dateManipulationYear = "01-01-"+last3Yrs+" to 31-12-"+ending3Yrs+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(dateRangeMeasurements, dateManipulationYear, "Date Range is Correctly displayed for the Last 3 Years");
		
		//Reset
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Reset");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("dateRangeMeasurements Reset : " +dateRangeMeasurements);
		
		//softAssert.assertEquals(dateRangeMeasurements, initialDateRangeMeasurement, "Date Range Of Lastly displayed is available for the Reset option");
		
		//Custom Range
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Custom Range");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("dateRangeMeasurements Reset : " +dateRangeMeasurements);
		
		softAssert.assertTrue(dateRangeMeasurements.contentEquals("01-01-2010 to 31-12-2014"), "For Custom Range Option Random date is displayed in Date Range Measurements Option");
		
		//Click Interval Data Tab
		
		clickIntervalDataTab();
		
		waitForExtJSAjaxComplete(10);
		
		String []reportingIntervalDropDown = {"Measurements", "Year"};
		
		softAssert.assertEqualsNoOrder(gaugesMeasurementsPO.getReportingIntervalDropDownValues("timeScale").toArray(), reportingIntervalDropDown, "Reporting interval has all values");

		waitForExtJSAjaxComplete(10);
		
		gaugesMeasurementsPO.setReportingInterval("Year");
		
		waitForExtJSAjaxComplete(10);
		
		initialDateRangeMeasurement = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("initialDateRangeMeasurement Year : " +initialDateRangeMeasurement);
		
		systemDate = getFutureDate(0);
		
		int currentYearIntervalTab = gaugesMeasurementsPO.getCurrentYear(systemDate);
		
		System.out.println("currentYear : " +currentYearIntervalTab);
		
		int last3YrsIntervalTab = currentYear - (currentYear-2010);
		
		int ending3YrsIntervalTab = currentYear - (currentYear-2014);
		
		String dateManipulationYearIntervalTab = "01-01-"+last3YrsIntervalTab+" to 31-12-"+ending3YrsIntervalTab+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYearIntervalTab);
		
		softAssert.assertEquals(initialDateRangeMeasurement, dateManipulationYearIntervalTab, "Date Range of Current year is Defaulted");
		
		List<String> dateValuesNewIntervalTab = gaugesMeasurementsPO.getDatePickerDropDownValues("measurementsPopup_dateRangePickerInterval");
		
		waitForExtJSAjaxComplete(10);
				
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewIntervalTab, "Current Year"), "Current Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewIntervalTab, "Last Year"), "Last Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewIntervalTab, "Last 3 Years"), "Last 3 Years is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewIntervalTab, "Reset"), "Reset is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewIntervalTab, "Custom Range"), "Custom Range is present in list");
		
		waitForExtJSAjaxComplete(5);
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Current Year");
		
		waitForExtJSAjaxComplete(20);
		
		// Current Year
		
		String dateRangeMeasurementsIntervalTab = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements Current Year : " +dateRangeMeasurementsIntervalTab);
		
		dateManipulationYear = "01-01-"+currentYear+" to 31-12-"+currentYear+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(dateRangeMeasurementsIntervalTab, dateManipulationYear, "Date Range is Correctly displayed for the current Year");
		
		// For Last Year
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last Year");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements Last Year : " +dateRangeMeasurements);
		
		int lastYrIntervalTab = currentYear - 1;
		
		dateManipulationYear = "01-01-"+lastYrIntervalTab+" to 31-12-"+lastYrIntervalTab+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(dateRangeMeasurements, dateManipulationYear, "Date Range is Correctly displayed for the Last Year");
		
		// For Last 3 Years
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last 3 Years");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements Last 3 Years : " +dateRangeMeasurements);
		
		dateManipulationYear = "01-01-"+last3Yrs+" to 31-12-"+ending3Yrs+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(dateRangeMeasurements, dateManipulationYear, "Date Range is Correctly displayed for the Last 3 Years");
		
		//Reset
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Reset");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements Reset : " +dateRangeMeasurements);
		
		dateManipulationYear = "01-01-"+last3Yrs+" to 31-12-"+lastYr+"";
		
		softAssert.assertEquals(dateRangeMeasurements, dateManipulationYear, "Date Range Of Lastly displayed is available for the Reset option");
		
		//Custom Range
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Custom Range");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements custom range : " +dateRangeMeasurements);
		
		softAssert.assertTrue(dateRangeMeasurements.contentEquals("01-01-2010 to 31-12-2014"), "For Custom Range Option Random date is displayed in Date Range Measurements Option");
		
		// For Measurements Interval
		
		gaugesMeasurementsPO.setReportingInterval("Measurements");
		
		waitForExtJSAjaxComplete(10);
		
		initialDateRangeMeasurement = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("initialDateRangeMeasurement Year : " +initialDateRangeMeasurement);
		
		systemDate = getFutureDate(0);
		
		int currentYearMeasurements = gaugesMeasurementsPO.getCurrentYear(systemDate);
		
		System.out.println("currentYear : " +currentYearMeasurements);
		
		int last3YrsMeasurements = currentYear - (currentYear-2010);
		
		int ending3YrsMeasurements = currentYear -  (currentYear-2014);
		
		String dateManipulationYearMeasurements = "01-01-"+last3YrsMeasurements+" to 31-12-"+ending3YrsMeasurements+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYearMeasurements);
		
		softAssert.assertEquals(initialDateRangeMeasurement, dateManipulationYearMeasurements, "Date Range of Last 3 Years is Defaulted");
		
		List<String> dateValuesNewMeasurements = gaugesMeasurementsPO.getDatePickerDropDownValues("measurementsPopup_dateRangePickerInterval");
		
		waitForExtJSAjaxComplete(10);
				
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewMeasurements, "Current Year"), "Current Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewMeasurements, "Last Year"), "Last Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewMeasurements, "Last 3 Years"), "Last 3 Years is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewMeasurements, "Reset"), "Reset is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewMeasurements, "Custom Range"), "Custom Range is present in list");
		
		waitForExtJSAjaxComplete(5);
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Current Year");
		
		waitForExtJSAjaxComplete(20);
		
		// Current Year
		
		String dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements Current Year : " +dateRangeMeasurementsMeasurements);
		
		dateManipulationYear = "01-01-"+currentYear+" to 31-12-"+currentYear+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, dateManipulationYear, "Date Range is Correctly displayed for the current Year");
		
		// For Last Year
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last Year");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements Last Year : " +dateRangeMeasurements);
		
		int lastYrMeasurements = currentYear - 1;
		
		dateManipulationYear = "01-01-"+lastYrMeasurements+" to 31-12-"+lastYrMeasurements+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(dateRangeMeasurements, dateManipulationYear, "Date Range is Correctly displayed for the Last Year");
		
		// For Last 3 Years
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last 3 Years");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements Last 3 Years : " +dateRangeMeasurements);
		
		dateManipulationYear = "01-01-"+last3Yrs+" to 31-12-"+ending3Yrs+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(dateRangeMeasurements, dateManipulationYear, "Date Range is Correctly displayed for the Last 3 Years");
		
		//Reset
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Reset");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements Reset : " +dateRangeMeasurements);
		
		//softAssert.assertEquals(dateManipulationYearMeasurements, initialDateRangeMeasurement, "Date Range Of Lastly displayed is available for the Reset option");
		
		//Custom Range
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Custom Range");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements Reset : " +dateRangeMeasurements);
		
		softAssert.assertTrue(dateRangeMeasurements.contentEquals("01-01-2010 to 31-12-2014"), "For Custom Range Option Random date is displayed in Date Range Measurements Option");
		
		softAssert.assertAll();
		
		Reporter.log("Date Range behavior when Reading Interval is Year is successfully verified", true);
	}
	
	/**
	 * Test Case ID : C60470
	 * Author : SSU
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class, priority=2)
	public void testDateRangeBehaviorOfMonth() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Date Range behavior when Reading Interval is Month: C60470" + " </span><br>",
				true);
		
		String gaugeCode = "preGaugeMeasure";
		String channelReference = "C60469ChnlRef";
		String calibrationDate = getFutureDate(0);
		String channelParameter = "Building Headcount";
		String channelUOM = "p";
		String readingInterval = "Month";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testDateRangeBehaviorOfMonth");
		
		waitForExtJSAjaxComplete(30);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		waitForExtJSAjaxComplete(25);
		
		openMeteringEntity("Gauges");

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValue(driver, gaugeCode);
		
		clickButton("Edit", GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		changeTab("channels");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		MeteringGaugesChanelsPageObject meteringGaugesChnlPO = new MeteringGaugesChanelsPageObject();
		
		boolean status = Grid.isRowInGridPresent(driver, channelParameter, "@class", ADD_GAUGES_FORM_CLASS);
		
		if(status) {
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, channelParameter);
				
			clickButton("Delete", "slnmGaugeId");
			
			clickOnDialogButton("Yes");

			waitForExtJSAjaxComplete(10);
		}
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", "slnmGaugeId");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		meteringGaugesChnlPO.setReference(channelReference);
		
		meteringGaugesChnlPO.setCalibrationDate(calibrationDate);
		
		waitForExtJSAjaxComplete(25);
		
		meteringGaugesChnlPO.setChannelParameter(channelParameter);
		
		waitForExtJSAjaxComplete(25);
		
		meteringGaugesChnlPO.setUnitOfMeasure(channelUOM);
		
		waitForExtJSAjaxComplete(25);
		
		meteringGaugesChnlPO.setChnlReadingInterval(readingInterval);
		
		waitForExtJSAjaxComplete(5);
		
		saveClose("slnmGaugeChnlId");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//Select pre-configured Channel and go to View Measurements
		
		checkRowInGriByTextValueAndClick(driver, channelReference, ADD_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(20);

		clickButton("Edit", "slnmGaugeId");
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(25);
		
		readingInterval = meteringGaugesChnlPO.getFieldValue("slnmGaugeChnlId", "readingInterval");
		
		if(!(readingInterval.contains("Month"))){
			
			meteringGaugesChnlPO.setChnlReadingInterval("Month");
			
			meteringGaugesChnlPO.save("slnmGaugeChnlId");
			
			waitForExtJSAjaxComplete(10);
		}
		
		meteringGaugesChnlPO.close("slnmGaugeChnlId");
		
		waitForExtJSAjaxComplete(20);

		clickViewMeasurementsButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickMeasurementsTab();
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(25);
		
		GaugesMeasurementsPageObject gaugesMeasurementsPO = new GaugesMeasurementsPageObject();
		
		String initialDateRangeMeasurement = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		String systemDate = getFutureDate(0);
		
		// Month Date Range Behaviour
		
		String currentyearDateRange = gaugesMeasurementsPO.getYearDateRange(0);
		
		String currentMonthDateRange = gaugesMeasurementsPO.getMonthDateRange(0);
		
		softAssert.assertEquals(initialDateRangeMeasurement, currentyearDateRange, "Date Range of Current year is Defaulted");
		
		List<String> dateValuesNew = gaugesMeasurementsPO.getDatePickerDropDownValues("measurementsPopup_dateRangePicker");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Current Month"), "Current Month is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last Month"), "Last Month is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last 6 Months"), "Last 6 Months is present in list");
				
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Current Year"), "Current Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last Year"), "Last Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last 3 Years"), "Last 3 Years is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Reset"), "Reset is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Custom Range"), "Custom Range is present in list");
		
		waitForExtJSAjaxComplete(5);
		
		// Current Month
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Current Month");
		
		waitForExtJSAjaxComplete(20);
		
		String dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("dateRangeMeasurements Current Month : " +dateRangeMeasurements);
		
		
		
		softAssert.assertEquals(dateRangeMeasurements, currentMonthDateRange, "Date Range is Correctly displayed for the current Month");
		
		// Last Month
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Last Month");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		String lastMonthDateRange = gaugesMeasurementsPO.getMonthDateRange(-1);
		
		softAssert.assertEquals(dateRangeMeasurements, lastMonthDateRange, "Date Range is Correctly displayed for the Last Month");
		
		// Last 6 Months

		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Last 6 Months");

		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		String last6MonthsDateRange = gaugesMeasurementsPO.getMonthDateRange(-6);
		
		softAssert.assertEquals(dateRangeMeasurements, last6MonthsDateRange, "Date Range is Correctly displayed for the Last 6 Months");
		
		// Current Year
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Current Year");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		softAssert.assertEquals(dateRangeMeasurements, currentyearDateRange, "Date Range is Correctly displayed for the current Year");
		
		// For Last Year
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Last Year");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		String lastyearDateRange = gaugesMeasurementsPO.getYearDateRange(-1);
		
		softAssert.assertEquals(dateRangeMeasurements, lastyearDateRange, "Date Range is Correctly displayed for the Last Year");
		
		// For Last 3 Years
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Last 3 Years");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		String last3yearsDateRange = gaugesMeasurementsPO.getYearDateRange(-3);
		
		softAssert.assertEquals(dateRangeMeasurements, last3yearsDateRange, "Date Range is Correctly displayed for the Last 3 Years");
		
		//Reset
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Reset");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		softAssert.assertEquals(dateRangeMeasurements, currentyearDateRange, "Date Range Of Lastly displayed is available for the Reset option");
		
		//Custom Range
		
		gaugesMeasurementsPO.setMonthComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Custom Range");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		softAssert.assertTrue(dateRangeMeasurements.contentEquals("01-01-2015 to 31-03-2015"), "For Custom Range Option Random date is displayed in Date Range Measurements Option");
		
		//******************Click Interval Data Tab************************//
		
		clickIntervalDataTab();
		
		waitForExtJSAjaxComplete(10);
		
		String []reportingIntervalDropDown = {"Measurements", "Year", "Month"};
		
		softAssert.assertEqualsNoOrder(gaugesMeasurementsPO.getReportingIntervalDropDownValues("timeScale").toArray(), reportingIntervalDropDown, "Reporting interval has all values");

		waitForExtJSAjaxComplete(10);
		
		gaugesMeasurementsPO.setReportingInterval("Month");
		
		waitForExtJSAjaxComplete(10);
		
		List<String> dateValuesNewIntervalTab = gaugesMeasurementsPO.getDatePickerDropDownValues("measurementsPopup_dateRangePickerInterval");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Current Month"), "Current Month is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last Month"), "Last Month is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last 6 Months"), "Last 6 Months is present in list");
				
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewIntervalTab, "Current Year"), "Current Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewIntervalTab, "Last Year"), "Last Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewIntervalTab, "Last 3 Years"), "Last 3 Years is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewIntervalTab, "Reset"), "Reset is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewIntervalTab, "Custom Range"), "Custom Range is present in list");
				
		waitForExtJSAjaxComplete(20);
		
		initialDateRangeMeasurement = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(initialDateRangeMeasurement, "01-01-2015 to 31-03-2015", "Date Range is Correctly displayed for the current Month");
		
		// Current Month
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Current Month");
		
		waitForExtJSAjaxComplete(20);
		
		String dateRangeMeasurementsInterval = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(dateRangeMeasurementsInterval, currentMonthDateRange, "Date Range is Correctly displayed for the current Month");
		
		// Last Month
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last Month");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsInterval = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(dateRangeMeasurementsInterval, lastMonthDateRange , "Date Range is Correctly displayed for the Last Month");
		
		// Last 6 Months

		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last 6 Months");

		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsInterval = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(dateRangeMeasurementsInterval, last6MonthsDateRange, "Date Range is Correctly displayed for the Last 6 Months");
		
		
		// Current Year
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Current Year");
		
		waitForExtJSAjaxComplete(20);
		
		String dateRangeMeasurementsIntervalTab = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");

		softAssert.assertEquals(dateRangeMeasurementsIntervalTab, currentyearDateRange, "Date Range is Correctly displayed for the current Year");
		
		// For Last Year
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last Year");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsInterval = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");

		softAssert.assertEquals(dateRangeMeasurementsInterval, lastyearDateRange, "Date Range is Correctly displayed for the Last Year");
		
		// For Last 3 Years
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last 3 Years");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsInterval = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(dateRangeMeasurementsInterval, last3yearsDateRange, "Date Range is Correctly displayed for the Last 3 Years");
		
		//Reset
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Reset");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsInterval = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(dateRangeMeasurementsInterval, currentyearDateRange, "Date Range Of Lastly displayed is available for the Reset option");
		
		//Custom Range
		
		gaugesMeasurementsPO.setMonthComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Custom Range");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsInterval = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurementsInterval Reset : " +dateRangeMeasurementsInterval);
		
		softAssert.assertTrue(dateRangeMeasurementsInterval.contentEquals("01-01-2015 to 31-03-2015"), "For Custom Range Option Random date is displayed in Date Range Measurements Option");
		
		//********************* For Measurements Interval ****************************//
		
		gaugesMeasurementsPO.setReportingInterval("Measurements");
		
		waitForExtJSAjaxComplete(10);
		
		List<String> dateValuesNewMeasurements = gaugesMeasurementsPO.getDatePickerDropDownValues("measurementsPopup_dateRangePickerInterval");
		
		waitForExtJSAjaxComplete(10);
				
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Current Month"), "Current Month is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last Month"), "Last Month is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last 6 Months"), "Last 6 Months is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewMeasurements, "Current Year"), "Current Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewMeasurements, "Last Year"), "Last Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewMeasurements, "Last 3 Years"), "Last 3 Years is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewMeasurements, "Reset"), "Reset is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewMeasurements, "Custom Range"), "Custom Range is present in list");
		
		waitForExtJSAjaxComplete(5);
		
		initialDateRangeMeasurement = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(initialDateRangeMeasurement, "01-01-2015 to 31-03-2015", "Date Range of current year is Defaulted");
		
		// Current Month

		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Current Month");
		
		waitForExtJSAjaxComplete(20);
		
		String dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, currentMonthDateRange, "Date Range is Correctly displayed for the current Month");
		
		// Last Month
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last Month");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, lastMonthDateRange, "Date Range is Correctly displayed for the Last Month");
		
		// Last 6 Months

		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last 6 Months");

		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, last6MonthsDateRange, "Date Range is Correctly displayed for the Last 6 Months");
		
		// Current Year
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Current Year");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, currentyearDateRange, "Date Range is Correctly displayed for the current Year");
		
		// For Last Year
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last Year");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, lastyearDateRange, "Date Range is Correctly displayed for the Last Year");
		
		// For Last 3 Years
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last 3 Years");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, last3yearsDateRange, "Date Range is Correctly displayed for the Last 3 Years");
		
		//Reset
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Reset");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, currentyearDateRange, "Date Range Of Lastly displayed is available for the Reset option");
		
		//Custom Range
		
		gaugesMeasurementsPO.setMonthComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Custom Range");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurementsMeasurements Reset : " +dateRangeMeasurementsMeasurements);
		
		softAssert.assertTrue(dateRangeMeasurementsMeasurements.contentEquals("01-01-2015 to 31-03-2015"), "For Custom Range Option Random date is displayed in Date Range Measurements Option");
		
		softAssert.assertAll();
		
		Reporter.log("Date Range behavior when Reading Interval is Month is successfully verified", true);
	}
	
	/**
	 * Test Case ID : C60472
	 * Author : SSU
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class, priority=3)
	public void testDateRangeBehaviorOfHour() throws Exception {
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Date Range behavior when Reading Interval is Hour: C60472" + " </span><br>",
				true);
		
		String gaugeCode = "preGaugeMeasure";
		String channelReference = "C60469ChnlRef";
		String calibrationDate = getFutureDate(0);
		String channelParameter = "Building Headcount";
		String channelUOM = "p";
		String readingInterval = "Hour";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testDateRangeBehaviorWithDifferentReadingInterval");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		waitForExtJSAjaxComplete(25);
		
		openMeteringEntity("Gauges");

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValue(driver, gaugeCode);
		
		clickButton("Edit", GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		changeTab("channels");
		
		MeteringGaugesChanelsPageObject meteringGaugesChnlPO = new MeteringGaugesChanelsPageObject();
		
		boolean status = Grid.isRowInGridPresent(driver, channelParameter, "@class", ADD_GAUGES_FORM_CLASS);
		
		if(status) {
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, channelParameter);
				
			clickButton("Delete", "slnmGaugeId");
			
			clickOnDialogButton("Yes");

			waitForExtJSAjaxComplete(10);
		}
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", "slnmGaugeId");
		
		Thread.sleep(20000);
		
		meteringGaugesChnlPO.setReference(channelReference);
		
		meteringGaugesChnlPO.setCalibrationDate(calibrationDate);
		
		waitForExtJSAjaxComplete(25);
		
		meteringGaugesChnlPO.setChannelParameter(channelParameter);
		
		waitForExtJSAjaxComplete(25);
		
		meteringGaugesChnlPO.setUnitOfMeasure(channelUOM);
		
		waitForExtJSAjaxComplete(25);
		
		meteringGaugesChnlPO.setChnlReadingInterval(readingInterval);
		
		waitForExtJSAjaxComplete(5);
		
		saveClose("slnmGaugeChnlId");
		
		Thread.sleep(20000);
		
		waitForExtJSAjaxComplete(25);
		
		//Select pre-configured Channel and go to View Measurements
		
		checkRowInGriByTextValueAndClick(driver, channelReference, ADD_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(20);

		clickButton("Edit", "slnmGaugeId");
		
		waitForExtJSAjaxComplete(10);
		
		readingInterval = meteringGaugesChnlPO.getFieldValue("slnmGaugeChnlId", "readingInterval");
		
		if(!(readingInterval.contains("Hour"))){
			
			meteringGaugesChnlPO.setChnlReadingInterval("Hour");
			
			meteringGaugesChnlPO.save("slnmGaugeChnlId");
			
			waitForExtJSAjaxComplete(10);
		}
		
		meteringGaugesChnlPO.close("slnmGaugeChnlId");
		
		waitForExtJSAjaxComplete(20);
		
		checkRowInGriByTextValueAndClick(driver, channelReference, ADD_GAUGES_FORM_CLASS);
		
		clickViewMeasurementsButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickMeasurementsTab();
		
		waitForExtJSAjaxComplete(25);
		
		GaugesMeasurementsPageObject gaugesMeasurementsPO = new GaugesMeasurementsPageObject();
		
		String initialDateRangeMeasurement = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		
		String currentYearDateRange = gaugesMeasurementsPO.getYearDateRange(0);
		
		List<String> dateValuesNew = gaugesMeasurementsPO.getDatePickerDropDownValues("measurementsPopup_dateRangePicker");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Today"), "Today is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last 7 Days"), "Last 7 Days is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Current Month"), "Current Month is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last Month"), "Last Month is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last 6 Months"), "Last 6 Months is present in list");
				
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Current Year"), "Current Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last Year"), "Last Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last 3 Years"), "Last 3 Years is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Reset"), "Reset is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Custom Range"), "Custom Range is present in list");
		
		waitForExtJSAjaxComplete(5);
		
		String currentMonthDateRange = gaugesMeasurementsPO.getMonthDateRange(0);
		
		softAssert.assertEquals(initialDateRangeMeasurement, currentMonthDateRange, "Current Month is Default Value");
		
		// Today
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Today");
				
		waitForExtJSAjaxComplete(20);
		
		String dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		//System.out.println("dateRangeMeasurements Current Month : " +dateRangeMeasurements);
		
		softAssert.assertEquals(dateRangeMeasurements, getFutureDate(0), "Date Range is Correctly displayed for Today");
		
		//Last 7 Days
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Last 7 Days");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("dateRangeMeasurements Last 7 Days : " +dateRangeMeasurements);
		
		String last7DaysDateRange = getFutureDate(-6)+" to "+getFutureDate(0);
		
		softAssert.assertEquals(dateRangeMeasurements, last7DaysDateRange, "Date Range is Correctly displayed for Last 7 days");
		
		// Current Month
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Current Month");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("dateRangeMeasurements Current Month : " +dateRangeMeasurements);
				
		softAssert.assertEquals(dateRangeMeasurements, currentMonthDateRange, "Date Range is Correctly displayed for the current Month");
		
		// Last Month
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Last Month");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		String lastMonthDateRange = gaugesMeasurementsPO.getMonthDateRange(-1);
		
		softAssert.assertEquals(dateRangeMeasurements, lastMonthDateRange, "Date Range is Correctly displayed for the Last Month");
		
		// Last 6 Months

		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Last 6 Months");

		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		String last6MonthsDateRange = gaugesMeasurementsPO.getMonthDateRange(-6);
		softAssert.assertEquals(dateRangeMeasurements, last6MonthsDateRange, "Date Range is Correctly displayed for the Last 6 Months");
		
		// Current Year
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Current Year");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		softAssert.assertEquals(dateRangeMeasurements, currentYearDateRange, "Date Range is Correctly displayed for the current Year");
		
		// For Last Year
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Last Year");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		String lastYearDateRange = gaugesMeasurementsPO.getYearDateRange(-1);
		
		softAssert.assertEquals(dateRangeMeasurements, lastYearDateRange, "Date Range is Correctly displayed for the Last Year");
		
		// For Last 3 Years
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Last 3 Years");
		
		waitForExtJSAjaxComplete(20);
		
		String last3YearsDateRange = gaugesMeasurementsPO.getYearDateRange(-3);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		softAssert.assertEquals(dateRangeMeasurements, last3YearsDateRange, "Date Range is Correctly displayed for the Last 3 Years");
		
		//Reset
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Reset");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		softAssert.assertEquals(dateRangeMeasurements, currentMonthDateRange, "Date Range Of Lastly displayed is available for the Reset option");
		
		//Custom Range
		
		gaugesMeasurementsPO.setHourComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Custom Range");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		String currentMonth = gaugesMeasurementsPO.getCurrentMonth("0");
		
		int currentyear = gaugesMeasurementsPO.getCurrentYear(getFutureDate(0));
		
		String customDateRange ="01-"+currentMonth+"-"+currentyear +" to "+getFutureDate(0);
		softAssert.assertEquals(dateRangeMeasurements, customDateRange , "For Custom Range Option Random date is displayed in Date Range Measurements Option");
				
		//Day Range
		
		gaugesMeasurementsPO.setDateRange("measurementsPopup_dateRangePicker", "01-01-2014 to 30-11-2014");
		
		waitForExtJSAjaxComplete(20);
	
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("dateRangeMeasurements Date Range Random: " +dateRangeMeasurements);
		
		softAssert.assertTrue(dateRangeMeasurements.contentEquals("01-01-2014 to 30-11-2014"), "For Random Date Range Option Selected Random date is displayed in Date Range Measurements Option");
	
		//********************* For Measurements Interval ****************************//
		
		clickIntervalDataTab();
		
		waitForExtJSAjaxComplete(10);
		
		String []reportingIntervalDropDown = {"Measurements", "Year", "Month", "Day", "Hour"};
		
		softAssert.assertEqualsNoOrder(gaugesMeasurementsPO.getReportingIntervalDropDownValues("timeScale").toArray(), reportingIntervalDropDown, "Reporting interval has all values");

		waitForExtJSAjaxComplete(10);
		
		gaugesMeasurementsPO.setReportingInterval("Measurements");
		
		waitForExtJSAjaxComplete(10);
		
		initialDateRangeMeasurement = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(initialDateRangeMeasurement, "01-01-2014 to 30-11-2014", "Date Range of current Month is Defaulted");
		
		List<String> dateValuesNewMeasurements = gaugesMeasurementsPO.getDatePickerDropDownValues("measurementsPopup_dateRangePickerInterval");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Today"), "Today is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last 7 Days"), "Last 7 Days is present in list");
				
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Current Month"), "Current Month is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last Month"), "Last Month is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last 6 Months"), "Last 6 Months is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewMeasurements, "Current Year"), "Current Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewMeasurements, "Last Year"), "Last Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewMeasurements, "Last 3 Years"), "Last 3 Years is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewMeasurements, "Reset"), "Reset is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewMeasurements, "Custom Range"), "Custom Range is present in list");
		
		waitForExtJSAjaxComplete(5);
		
		// Today
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Today");
				
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements Current Month : " +dateRangeMeasurements);
		
		softAssert.assertEquals(dateRangeMeasurements, getFutureDate(0), "Date Range is Correctly displayed for Today");
		
		//Last 7 Days
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last 7 Days");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements Last 7 Days : " +dateRangeMeasurements);
		
		last7DaysDateRange = getFutureDate(-6)+" to "+getFutureDate(0);
		
		softAssert.assertEquals(dateRangeMeasurements, last7DaysDateRange, "Date Range is Correctly displayed for Last 7 days");
		
		
		// Current Month

		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Current Month");
		
		waitForExtJSAjaxComplete(20);
		
		String dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, currentMonthDateRange, "Date Range is Correctly displayed for the current Month");
		
		// Last Month
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last Month");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, lastMonthDateRange, "Date Range is Correctly displayed for the Last Month");
		
		// Last 6 Months

		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last 6 Months");

		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, last6MonthsDateRange, "Date Range is Correctly displayed for the Last 6 Months");
		
		// Current Year
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Current Year");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, currentYearDateRange, "Date Range is Correctly displayed for the current Year");
		
		// For Last Year
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last Year");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, lastYearDateRange, "Date Range is Correctly displayed for the Last Year");
		
		// For Last 3 Years
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last 3 Years");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, last3YearsDateRange, "Date Range is Correctly displayed for the Last 3 Years");
		
		//Reset
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Reset");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, currentMonthDateRange, "Date Range Of Lastly displayed is available for the Reset option");
		
		//Custom Range
		
		gaugesMeasurementsPO.setHourComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Custom Range");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		softAssert.assertEquals(dateRangeMeasurements, customDateRange, "For Custom Range Option Random date is displayed in Date Range Measurements Option");
				
		//Day Range
		
		gaugesMeasurementsPO.setDateRange("measurementsPopup_dateRangePickerInterval", "01-01-2014 to 30-11-2014");
		
		waitForExtJSAjaxComplete(20);
	
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements Date Range Random: " +dateRangeMeasurements);
		
		softAssert.assertTrue(dateRangeMeasurements.contentEquals("01-01-2014 to 30-11-2014"), "For Random Date Range Option Selected Random date is displayed in Date Range Measurements Option");
	
		softAssert.assertAll();
		
		Reporter.log("Date Range behavior when Reading Interval is Hour is successfully verified", true);
	}

	/**
	 * Test Case ID : C60471
	 * Author : SSU
	 */
	@Test(enabled=false, retryAnalyzer=RetryAnalyzer.class)
	public void testDateRangeBehaviorOfDay() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Date Range behavior when Reading Interval is Day: C60471" + " </span><br>",
				true);
		
		String gaugeCode = "preGaugeMeasure";
		String channelReference = "preGaugeMeasureCRUD";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testDateRangeBehaviorOfDay");
		
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

		clickButton("Edit", "slnmGaugeId");
		
		waitForExtJSAjaxComplete(10);
		
		MeteringGaugesChanelsPageObject meteringGaugesChnlPO = new MeteringGaugesChanelsPageObject();
		
		String readingInterval = meteringGaugesChnlPO.getFieldValue("slnmGaugeChnlId", "readingInterval");
		
		if(!(readingInterval.contains("Day"))){
			
			meteringGaugesChnlPO.setChnlReadingInterval("Day");
			
			meteringGaugesChnlPO.save("slnmGaugeChnlId");
			
			waitForExtJSAjaxComplete(10);
		}
		
		meteringGaugesChnlPO.close("slnmGaugeChnlId");
		
		waitForExtJSAjaxComplete(20);

		clickViewMeasurementsButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickMeasurementsTab();
		
		waitForExtJSAjaxComplete(10);
		
		GaugesMeasurementsPageObject gaugesMeasurementsPO = new GaugesMeasurementsPageObject();
		
		String initialDateRangeMeasurement = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("initialDateRangeMeasurement Month : " +initialDateRangeMeasurement);
		
		String systemDate = getFutureDate(0);
		
		System.out.println("systemDate : " +systemDate);
		
		// Month Date Range Behaviour
		
		String currentMonth = gaugesMeasurementsPO.getCurrentMonth(systemDate);
		
		System.out.println("currentMonth : " +currentMonth);
		
		int currentYear = gaugesMeasurementsPO.getCurrentYear(systemDate);
		
		System.out.println("currentYear : " +currentYear);
		
		String dateManipulationYear = "01-01-"+currentYear+" to 31-12-"+currentYear+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		List<String> dateValuesNew = gaugesMeasurementsPO.getDatePickerDropDownValues("measurementsPopup_dateRangePicker");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Today"), "Today is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last 7 Days"), "Last 7 Days is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Current Month"), "Current Month is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last Month"), "Last Month is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last 6 Months"), "Last 6 Months is present in list");
				
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Current Year"), "Current Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last Year"), "Last Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last 3 Years"), "Last 3 Years is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Reset"), "Reset is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Custom Range"), "Custom Range is present in list");
		
		waitForExtJSAjaxComplete(5);
		
		int lastDayOfThisMonth = gaugesMeasurementsPO.getlastDateofThisMonth(currentMonth, currentYear);
		
		System.out.println("lastDayOfThisMonth " +Integer.toString(lastDayOfThisMonth));
		
		String firstDayOfThisMonth =  "01-"+currentMonth+"-"+currentYear+"";
		
		System.out.println("firstDayOfThisMonth " +firstDayOfThisMonth);
		
		String currentMonthManipulation = ""+firstDayOfThisMonth+" to "+Integer.toString(lastDayOfThisMonth)+"-"+currentMonth+"-"+currentYear+""; 
		
		System.out.println("currentMonthManipulation " +currentMonthManipulation);
		
		softAssert.assertEquals(initialDateRangeMeasurement, currentMonthManipulation, "Current Month is Default Value");
		
		// Today
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Today");
				
		waitForExtJSAjaxComplete(20);
		
		String dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("dateRangeMeasurements Current Month : " +dateRangeMeasurements);
		
		softAssert.assertEquals(dateRangeMeasurements, getFutureDate(0), "Date Range is Correctly displayed for Today");
		
		//Last 7 Days
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Last 7 Days");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("dateRangeMeasurements Last 7 Days : " +dateRangeMeasurements);
		
		String Last7DaysManipulation = getFutureDate(-6)+" to "+getFutureDate(0);
		
		softAssert.assertEquals(dateRangeMeasurements, Last7DaysManipulation, "Date Range is Correctly displayed for Last 7 days");
		
		// Current Month
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Current Month");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("dateRangeMeasurements Current Month : " +dateRangeMeasurements);
		
		lastDayOfThisMonth = gaugesMeasurementsPO.getlastDateofThisMonth(currentMonth, currentYear);
		
		System.out.println("lastDayOfThisMonth " +Integer.toString(lastDayOfThisMonth));
		
		firstDayOfThisMonth =  "01-"+currentMonth+"-"+currentYear+"";
		
		System.out.println("firstDayOfThisMonth " +firstDayOfThisMonth);
		
		currentMonthManipulation = ""+firstDayOfThisMonth+" to "+Integer.toString(lastDayOfThisMonth)+"-"+currentMonth+"-"+currentYear+""; 
		
		System.out.println("currentMonthManipulation " +currentMonthManipulation);
		
		softAssert.assertEquals(dateRangeMeasurements, currentMonthManipulation, "Date Range is Correctly displayed for the current Month");
		
		// Last Month
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Last Month");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("dateRangeMeasurements Last Month : " +dateRangeMeasurements);
		
		String lastDayOfLastMonth = gaugesMeasurementsPO.getLastMonthLastDate(currentMonth, currentYear);
		
		System.out.println("lastDayOfLastMonth " +lastDayOfLastMonth);
		
		int lastMonthint = Integer.parseInt(currentMonth) -1 ;
		String lastMonth =  String.valueOf(lastMonthint<10?("0"+lastMonthint):(lastMonthint));
		
		firstDayOfThisMonth =  "01-"+lastMonth+"-"+currentYear+"";
		
		System.out.println("firstDayOfThisMonth " +firstDayOfThisMonth);
		
		currentMonthManipulation = ""+firstDayOfThisMonth+" to "+lastDayOfLastMonth+"-"+lastMonth+"-"+currentYear+""; 
		
		System.out.println("currentMonthManipulation " +currentMonthManipulation);
		
		softAssert.assertEquals(dateRangeMeasurements, currentMonthManipulation, "Date Range is Correctly displayed for the Last Month");
		
		// Last 6 Months

		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Last 6 Months");

		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("dateRangeMeasurements Last Month : " +dateRangeMeasurements);
		
		String firstDayOfLast6Months = gaugesMeasurementsPO.getLastSixMonthFirstDate();
		
		System.out.println("firstDayOfLast6Months " +firstDayOfLast6Months);
		
		String lastDayOfLast6Months = gaugesMeasurementsPO.getLastMonthLastDate(currentMonth, currentYear);
		
		System.out.println("lastDayOfLast6Months " +lastDayOfLast6Months);
		
		currentMonthManipulation = ""+firstDayOfLast6Months+" to "+lastDayOfLast6Months+"-"+lastMonth+"-"+currentYear+""; 
		
		System.out.println("currentMonthManipulation " +currentMonthManipulation);
		
		softAssert.assertEquals(dateRangeMeasurements, currentMonthManipulation, "Date Range is Correctly displayed for the Last 6 Months");
		
		// Current Year
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Current Year");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("dateRangeMeasurements Current Year : " +dateRangeMeasurements);
		
		dateManipulationYear = "01-01-"+currentYear+" to 31-12-"+currentYear+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(dateRangeMeasurements, dateManipulationYear, "Date Range is Correctly displayed for the current Year");
		
		// For Last Year
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Last Year");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("dateRangeMeasurements Last Year : " +dateRangeMeasurements);
		
		int lastYr = currentYear - 1;
		
		dateManipulationYear = "01-01-"+lastYr+" to 31-12-"+lastYr+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(dateRangeMeasurements, dateManipulationYear, "Date Range is Correctly displayed for the Last Year");
		
		// For Last 3 Years
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Last 3 Years");
		
		waitForExtJSAjaxComplete(20);
		
		int last3Yrs = currentYear - 3;
		
		int ending3Yrs = currentYear - 1;
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("dateRangeMeasurements Last 3 Years : " +dateRangeMeasurements);
		
		dateManipulationYear = "01-01-"+last3Yrs+" to 31-12-"+ending3Yrs+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(dateRangeMeasurements, dateManipulationYear, "Date Range is Correctly displayed for the Last 3 Years");
		
		//Reset
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Reset");
		
		waitForExtJSAjaxComplete(20);
		
		System.out.println("dateRangeMeasurements Reset : " +dateRangeMeasurements);
	
		softAssert.assertEquals(dateRangeMeasurements, dateManipulationYear, "Date Range Of Lastly displayed is available for the Reset option");
		
		//Custom Range
		
		gaugesMeasurementsPO.setHourComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Custom Range");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("dateRangeMeasurements Custom Range : " +dateRangeMeasurements);
		
		dateManipulationYear = "01-"+currentMonth+"-"+currentYear+" to "+getFutureDate(0); 
		
		//softAssert.assertTrue(dateRangeMeasurements.contains(dateManipulationYear), "For Custom Range Option Random date is displayed in Date Range Measurements Option");
		softAssert.assertEquals(dateRangeMeasurements, dateManipulationYear, "For Custom Range Option Random date is displayed in Date Range Measurements Option");
				
		//Day Range
		
		gaugesMeasurementsPO.setDateRange("measurementsPopup_dateRangePicker", "01-01-2014 to 30-11-2014");
		
		waitForExtJSAjaxComplete(20);
	
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePicker");
		
		System.out.println("dateRangeMeasurements Date Range Random: " +dateRangeMeasurements);
		
		softAssert.assertTrue(dateRangeMeasurements.contentEquals("01-01-2014 to 30-11-2014"), "For Random Date Range Option Selected Random date is displayed in Date Range Measurements Option");
	
		//******************Click Interval Data Tab************************//
		
		clickIntervalDataTab();
		
		waitForExtJSAjaxComplete(10);
		
		String []reportingIntervalDropDown = {"Measurements", "Year", "Month", "Day"};
		
		softAssert.assertEqualsNoOrder(gaugesMeasurementsPO.getReportingIntervalDropDownValues("timeScale").toArray(), reportingIntervalDropDown, "Reporting interval has all values");

		waitForExtJSAjaxComplete(10);
		
		gaugesMeasurementsPO.setReportingInterval("Day");
		
		waitForExtJSAjaxComplete(10);
		
		initialDateRangeMeasurement = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("initialDateRangeMeasurement Day : " +initialDateRangeMeasurement);
		
		systemDate = getFutureDate(0);
		
		int currentYearIntervalTab = gaugesMeasurementsPO.getCurrentYear(systemDate);
		
		System.out.println("currentYear : " +currentYearIntervalTab);
		
		lastDayOfThisMonth = gaugesMeasurementsPO.getlastDateofThisMonth(currentMonth, currentYear);
		
		System.out.println("lastDayOfThisMonth " +Integer.toString(lastDayOfThisMonth));
		
		firstDayOfThisMonth =  "01-"+currentMonth+"-"+currentYear+"";
		
		System.out.println("firstDayOfThisMonth " +firstDayOfThisMonth);
		
		currentMonthManipulation = ""+firstDayOfThisMonth+" to "+Integer.toString(lastDayOfThisMonth)+"-"+currentMonth+"-"+currentYear+""; 
		
		System.out.println("currentMonthManipulation " +currentMonthManipulation);
		
		softAssert.assertEquals(initialDateRangeMeasurement, currentMonthManipulation, "Date Range of current Month is defaulted");
		
		waitForExtJSAjaxComplete(5);
		
		List<String> dateValuesNewIntervalTab = gaugesMeasurementsPO.getDatePickerDropDownValues("measurementsPopup_dateRangePickerInterval");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Today"), "Today is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last 7 Days"), "Last 7 Days is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Current Month"), "Current Month is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last Month"), "Last Month is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last 6 Months"), "Last 6 Months is present in list");
				
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewIntervalTab, "Current Year"), "Current Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewIntervalTab, "Last Year"), "Last Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewIntervalTab, "Last 3 Years"), "Last 3 Years is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewIntervalTab, "Reset"), "Reset is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewIntervalTab, "Custom Range"), "Custom Range is present in list");
				
		waitForExtJSAjaxComplete(20);
		
		// Today
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Today");
				
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements Current Month : " +dateRangeMeasurements);
		
		softAssert.assertEquals(dateRangeMeasurements, getFutureDate(0), "Date Range is Correctly displayed for Today");
		
		//Last 7 Days
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last 7 Days");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements Last 7 Days : " +dateRangeMeasurements);
		
		Last7DaysManipulation = getFutureDate(-6)+" to "+getFutureDate(0);
		
		softAssert.assertEquals(dateRangeMeasurements, Last7DaysManipulation, "Date Range is Correctly displayed for Last 7 days");
		
		// Current Month
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Current Month");
		
		waitForExtJSAjaxComplete(20);
		
		String dateRangeMeasurementsInterval = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurementsInterval Current Month : " +dateRangeMeasurementsInterval);
		
		int lastDayOfThisMonthInterval = gaugesMeasurementsPO.getlastDateofThisMonth(currentMonth, currentYear);
		
		System.out.println("lastDayOfThisMonthInterval " +Integer.toString(lastDayOfThisMonthInterval));
		
		String firstDayOfThisMonthInterval =  "01-"+currentMonth+"-"+currentYear+"";
		
		System.out.println("firstDayOfThisMonthInterval " +firstDayOfThisMonthInterval);
		
		currentMonthManipulation = ""+firstDayOfThisMonthInterval+" to "+Integer.toString(lastDayOfThisMonthInterval)+"-"+currentMonth+"-"+currentYear+""; 
		
		System.out.println("currentMonthManipulation " +currentMonthManipulation);
		
		softAssert.assertEquals(dateRangeMeasurementsInterval, currentMonthManipulation, "Date Range is Correctly displayed for the current Month");
		
		// Last Month
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last Month");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsInterval = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurementsInterval Last Month : " +dateRangeMeasurementsInterval);
		
		String lastDayOfLastMonthInterval = gaugesMeasurementsPO.getLastMonthLastDate(currentMonth, currentYear);
		
		System.out.println("lastDayOfLastMonthInterval " +lastDayOfLastMonthInterval);
		
		int lastMonthintInterval = Integer.parseInt(currentMonth) -1 ;
		String lastMonthInterval =  String.valueOf(lastMonthintInterval<10?("0"+lastMonthintInterval):(lastMonthintInterval));
		
		firstDayOfThisMonthInterval =  "01-"+lastMonthInterval+"-"+currentYear+"";
		
		System.out.println("firstDayOfThisMonthInterval " +firstDayOfThisMonthInterval);
		
		currentMonthManipulation = ""+firstDayOfThisMonthInterval+" to "+lastDayOfLastMonth+"-"+lastMonthInterval+"-"+currentYear+""; 
		
		System.out.println("currentMonthManipulation " +currentMonthManipulation);
		
		softAssert.assertEquals(dateRangeMeasurementsInterval, currentMonthManipulation, "Date Range is Correctly displayed for the Last Month");
		
		// Last 6 Months

		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last 6 Months");

		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsInterval = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurementsInterval Last Month : " +dateRangeMeasurementsInterval);
		
		String firstDayOfLast6MonthsInterval = gaugesMeasurementsPO.getLastSixMonthFirstDate();
		
		System.out.println("firstDayOfLast6MonthsInterval " +firstDayOfLast6MonthsInterval);
		
		String lastDayOfLast6MonthsInterval = gaugesMeasurementsPO.getLastMonthLastDate(currentMonth, currentYear);
		
		System.out.println("lastDayOfLast6MonthsInterval " +lastDayOfLast6MonthsInterval);
		
		currentMonthManipulation = ""+firstDayOfLast6MonthsInterval+" to "+lastDayOfLast6MonthsInterval+"-"+lastMonthInterval+"-"+currentYear+""; 
		
		System.out.println("currentMonthManipulation " +currentMonthManipulation);
		
		softAssert.assertEquals(dateRangeMeasurementsInterval, currentMonthManipulation, "Date Range is Correctly displayed for the Last 6 Months");
		
		// Current Year
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Current Year");
		
		waitForExtJSAjaxComplete(20);
		
		String dateRangeMeasurementsIntervalTab = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements Current Year : " +dateRangeMeasurementsIntervalTab);
		
		dateManipulationYear = "01-01-"+currentYear+" to 31-12-"+currentYear+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(dateRangeMeasurementsIntervalTab, dateManipulationYear, "Date Range is Correctly displayed for the current Year");
		
		// For Last Year
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last Year");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsInterval = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurementsInterval Last Year : " +dateRangeMeasurementsInterval);
		
		int lastYrIntervalTab = currentYear - 1;
		
		dateManipulationYear = "01-01-"+lastYrIntervalTab+" to 31-12-"+lastYrIntervalTab+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(dateRangeMeasurementsInterval, dateManipulationYear, "Date Range is Correctly displayed for the Last Year");
		
		// For Last 3 Years
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last 3 Years");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsInterval = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurementsInterval Last 3 Years : " +dateRangeMeasurementsInterval);
		
		dateManipulationYear = "01-01-"+last3Yrs+" to 31-12-"+ending3Yrs+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(dateRangeMeasurementsInterval, dateManipulationYear, "Date Range is Correctly displayed for the Last 3 Years");
		
		//Reset
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Reset");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsInterval = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurementsInterval Reset : " +dateRangeMeasurementsInterval);
		
		dateManipulationYear = "01-"+currentMonth+"-"+currentYear+" to "+Integer.toString(lastDayOfThisMonthInterval)+"-"+currentMonth+"-"+currentYear+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(dateRangeMeasurementsInterval, dateManipulationYear, "Date Range Of Lastly displayed is available for the Reset option");
		
		//Custom Range
		
		gaugesMeasurementsPO.setHourComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Custom Range");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsInterval = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurementsInterval Reset : " +dateRangeMeasurementsInterval);
		
		dateManipulationYear = getFutureDate(-6)+" to "+getFutureDate(0); 
		
		softAssert.assertEquals(dateRangeMeasurements, dateManipulationYear, "For Custom Range Option Random date is displayed in Date Range Measurements Option");
			
		//Day Range
		
		gaugesMeasurementsPO.setDateRange("measurementsPopup_dateRangePickerInterval", "01-01-2014 to 30-11-2014");
		
		waitForExtJSAjaxComplete(20);
	
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements Date Range Random: " +dateRangeMeasurements);
		
		softAssert.assertTrue(dateRangeMeasurements.contentEquals("01-01-2014 to 30-11-2014"), "For Random Date Range Option Selected Random date is displayed in Date Range Measurements Option");

		//********************* For Measurements Interval ****************************//
		
		gaugesMeasurementsPO.setReportingInterval("Measurements");
		
		waitForExtJSAjaxComplete(10);
		
		initialDateRangeMeasurement = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("initialDateRangeMeasurement Year : " +initialDateRangeMeasurement);
		
		systemDate = getFutureDate(0);
		
		int currentYearMeasurements = gaugesMeasurementsPO.getCurrentYear(systemDate);
		
		System.out.println("currentYear : " +currentYearMeasurements);
		
		int lastmonthMeasurements = Integer.parseInt(currentMonth) - 1;
		String lastMonthMeasurements =  String.valueOf(lastmonthMeasurements<10?("0"+lastmonthMeasurements):(lastmonthMeasurements));
		
		String dateManipulationYearMeasurements = "01-01-"+currentYearMeasurements+" to 31-"+lastMonthMeasurements+"-"+currentYearMeasurements+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYearMeasurements);
		
		softAssert.assertEquals(initialDateRangeMeasurement, "01-01-2014 to 30-11-2014", "Date Range of current year is Defaulted");
		
		List<String> dateValuesNewMeasurements = gaugesMeasurementsPO.getDatePickerDropDownValues("measurementsPopup_dateRangePickerInterval");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Today"), "Today is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last 7 Days"), "Last 7 Days is present in list");
				
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Current Month"), "Current Month is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last Month"), "Last Month is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNew, "Last 6 Months"), "Last 6 Months is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewMeasurements, "Current Year"), "Current Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewMeasurements, "Last Year"), "Last Year is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewMeasurements, "Last 3 Years"), "Last 3 Years is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewMeasurements, "Reset"), "Reset is present in list");
		
		softAssert.assertTrue(gaugesMeasurementsPO.useLoop(dateValuesNewMeasurements, "Custom Range"), "Custom Range is present in list");
		
		waitForExtJSAjaxComplete(5);
		
		// Today
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Today");
				
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements Current Month : " +dateRangeMeasurements);
		
		softAssert.assertEquals(dateRangeMeasurements, getFutureDate(0), "Date Range is Correctly displayed for Today");
		
		//Last 7 Days
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last 7 Days");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements Last 7 Days : " +dateRangeMeasurements);
		
		Last7DaysManipulation = getFutureDate(-6)+" to "+getFutureDate(0);
		
		softAssert.assertEquals(dateRangeMeasurements, Last7DaysManipulation, "Date Range is Correctly displayed for Last 7 days");
		
		// Current Month

		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Current Month");
		
		waitForExtJSAjaxComplete(20);
		
		String dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurementsMeasurements Current Month : " +dateRangeMeasurementsMeasurements);
		
		int lastDayOfThisMonthMeasurements = gaugesMeasurementsPO.getlastDateofThisMonth(currentMonth, currentYear);
		
		System.out.println("lastDayOfThisMonthMeasurements " +Integer.toString(lastDayOfThisMonthMeasurements));
		
		String firstDayOfThisMonthMeasurements =  "01-"+currentMonth+"-"+currentYear+"";
		
		System.out.println("firstDayOfThisMonthMeasurements " +firstDayOfThisMonthMeasurements);
		
		currentMonthManipulation = ""+firstDayOfThisMonthMeasurements+" to "+Integer.toString(lastDayOfThisMonthMeasurements)+"-"+currentMonth+"-"+currentYear+""; 
		
		System.out.println("currentMonthManipulation " +currentMonthManipulation);
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, currentMonthManipulation, "Date Range is Correctly displayed for the current Month");
		
		// Last Month
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last Month");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurementsMeasurements Last Month : " +dateRangeMeasurementsMeasurements);
		
		String lastDayOfLastMonthMeasurements = gaugesMeasurementsPO.getLastMonthLastDate(currentMonth, currentYear);
		
		System.out.println("lastDayOfLastMonthMeasurements " +lastDayOfLastMonthMeasurements);
		
		int lastMonthintMeasurements = Integer.parseInt(currentMonth) -1 ;
		lastMonthMeasurements =  String.valueOf(lastMonthintMeasurements<10?("0"+lastMonthintMeasurements):(lastMonthintMeasurements));
		
		firstDayOfThisMonthMeasurements =  "01-"+lastMonthMeasurements+"-"+currentYear+"";
		
		System.out.println("firstDayOfThisMonthMeasurements " +firstDayOfThisMonthMeasurements);
		
		currentMonthManipulation = ""+firstDayOfThisMonthMeasurements+" to "+lastDayOfLastMonthMeasurements+"-"+lastMonthMeasurements+"-"+currentYear+""; 
		
		System.out.println("currentMonthManipulation " +currentMonthManipulation);
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, currentMonthManipulation, "Date Range is Correctly displayed for the Last Month");
		
		// Last 6 Months

		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last 6 Months");

		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurementsMeasurements Last Month : " +dateRangeMeasurementsMeasurements);
		
		String firstDayOfLast6MonthsMeasurements = gaugesMeasurementsPO.getLastSixMonthFirstDate();
		
		System.out.println("firstDayOfLast6MonthsMeasurements " +firstDayOfLast6MonthsMeasurements);
		
		String lastDayOfLast6MonthsMeasurements = gaugesMeasurementsPO.getLastMonthLastDate(currentMonth, currentYear);
		
		System.out.println("lastDayOfLast6MonthsMeasurements " +lastDayOfLast6MonthsMeasurements);
		
		currentMonthManipulation = ""+firstDayOfLast6MonthsMeasurements+" to "+lastDayOfLast6MonthsMeasurements+"-"+lastMonthMeasurements+"-"+currentYear+""; 
		
		System.out.println("currentMonthManipulation " +currentMonthManipulation);
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, currentMonthManipulation, "Date Range is Correctly displayed for the Last 6 Months");
		
		// Current Year
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Current Year");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements Current Year : " +dateRangeMeasurementsMeasurements);
		
		dateManipulationYear = "01-01-"+currentYear+" to 31-12-"+currentYear+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, dateManipulationYear, "Date Range is Correctly displayed for the current Year");
		
		// For Last Year
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last Year");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurementsMeasurements Last Year : " +dateRangeMeasurementsMeasurements);
		
		int lastYrMeasurements = currentYear - 1;
		
		dateManipulationYear = "01-01-"+lastYrMeasurements+" to 31-12-"+lastYrMeasurements+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, dateManipulationYear, "Date Range is Correctly displayed for the Last Year");
		
		// For Last 3 Years
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Last 3 Years");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurementsMeasurements Last 3 Years : " +dateRangeMeasurementsMeasurements);
		
		dateManipulationYear = "01-01-"+last3Yrs+" to 31-12-"+ending3Yrs+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, dateManipulationYear, "Date Range is Correctly displayed for the Last 3 Years");
		
		//Reset
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Reset");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurementsMeasurements Reset : " +dateRangeMeasurementsMeasurements);
		
		dateManipulationYear = "01-"+currentMonth+"-"+currentYear+" to "+Integer.toString(lastDayOfThisMonthInterval)+"-"+currentMonth+"-"+currentYear+"";
		
		System.out.println("dateManipulationYear" +dateManipulationYear);
		
		softAssert.assertEquals(dateRangeMeasurementsMeasurements, dateManipulationYear, "Date Range Of Lastly displayed is available for the Reset option");
		
		//Custom Range
		
		gaugesMeasurementsPO.setHourComboValueDropDownSelector("measurementsPopup_dateRangePickerInterval", "Custom Range");
		
		waitForExtJSAjaxComplete(20);
		
		dateRangeMeasurementsInterval = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurementsInterval Reset : " +dateRangeMeasurementsInterval);
		
		dateManipulationYear = getFutureDate(-6)+" to "+getFutureDate(0); 
		
		softAssert.assertEquals(dateRangeMeasurements, dateManipulationYear, "For Custom Range Option Random date is displayed in Date Range Measurements Option");
			
		//Day Range
		
		gaugesMeasurementsPO.setDateRange("measurementsPopup_dateRangePickerInterval", "01-01-2014 to 30-11-2014");
		
		waitForExtJSAjaxComplete(20);
	
		dateRangeMeasurements = gaugesMeasurementsPO.getDateRange("measurementsPopup_dateRangePickerInterval");
		
		System.out.println("dateRangeMeasurements Date Range Random: " +dateRangeMeasurements);
		
		softAssert.assertTrue(dateRangeMeasurements.contentEquals("01-01-2014 to 30-11-2014"), "For Random Date Range Option Selected Random date is displayed in Date Range Measurements Option");

		softAssert.assertAll();
		
		Reporter.log("Date Range behavior when Reading Interval is Day is successfully verified", true);
	}

}
