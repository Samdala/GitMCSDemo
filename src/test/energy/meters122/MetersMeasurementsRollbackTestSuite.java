package test.energy.meters122;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.energy.gauges.GaugesMeasurementsPageObject;
import test.energy.gauges.MeteringGaugesPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class MetersMeasurementsRollbackTestSuite extends MetersPageObject {

	@Test(enabled=true)
	public void testMeterMeasurementRollback() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Meter - Add Rollback Measurement: ?" + " </span><br>",
				true);

		Reporter.log("User adds Rollback Measurement on Meter"  + " <br>",
				true);
		
		Boolean build122 = driver.getCurrentUrl().contains("122");
	
		String meterCode = "1preMeter";
		String channelReference = "1preMeterMeasureRollback";
		String valueDigits1 = "95";
		String valueDecimals1 = "5";
		String valueDigits2 = "50";
		String valueDecimals2 = "5";
		String valueDigits3 = "30";
		String valueDecimals3 = "25";
		String valueDigits4 = "10";
		String valueDecimals4 = "25";
		String valueDigits5 = "05";
		String valueDecimals5 = "5";
		String dateForInitialMeasurement = "19-10-2011";
		String dateForRollback1 = "20-10-2011";
		String dateForOverflow1 = "21-10-2011";
		String dateForRollback2 = "22-10-2011";
		String dateForOverflow2 = "23-10-2011";
		String rollback = (build122) ? "ROLLBACK" : "Rollback";
		String overflow = (build122) ? "OVEFLOW" : "Overflow";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("AddMeterMeasurementRollback");
		
		//Navigate to Meters Overview
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
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
		
		if (!driver.getCurrentUrl().contains("12")) setDateRange(DIALOG_MTR_CHNL_MEASSUREMENTS, "01-01-2010");

		waitForExtJSAjaxComplete(20);
		
		//Add Measurement
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
				
		waitForExtJSAjaxComplete(20);
		
		setDate(dateForInitialMeasurement);
		
		waitForExtJSAjaxComplete(20);
		
		setIndexValueDigits(valueDigits1);
		
		waitForExtJSAjaxComplete(20);
		
		setIndexValueDecimals(valueDecimals1);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		//TODO need to use LIKE instead of EQUALS
		softAssert.assertTrue(Grid.isRowInGridPresentLike(driver, valueDigits1+"."+valueDecimals1, "@class", DIALOG_MTR_CHNL_MEASSUREMENTS), "Initial measurement is present in Measurement grid with correct Value");
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(20);
		
		setDate(dateForRollback1);
		
		waitForExtJSAjaxComplete(20);
		
		setIndexValueDigits(valueDigits2);
		
		waitForExtJSAjaxComplete(20);
		
		setIndexValueDecimals(valueDecimals2);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		clickButtonByDialogId("Rollback", getXWindowId("Manage Overflow / Rollback"));
		
		waitForExtJSAjaxComplete(20);
		
		//Check if Measurement with Rollback is added
		softAssert.assertTrue(verifyMeasurementGridValues(rollback, valueDigits2+"."+valueDecimals2, "50.500", "-45.000"), "Rollback measurement is present in grid with correct Value, Meas. Type and Difference");
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(20);
		
		setDate(dateForOverflow1);
		
		waitForExtJSAjaxComplete(20);
		
		setIndexValueDigits(valueDigits3);
		
		waitForExtJSAjaxComplete(20);
		
		setIndexValueDecimals(valueDecimals3);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		clickButtonByDialogId("Overflow", getXWindowId("Manage Overflow / Rollback"));
		
		waitForExtJSAjaxComplete(20);
		
		//Check if Measurement with Overflow is added
		softAssert.assertTrue(verifyMeasurementGridValues(overflow, valueDigits3+"."+valueDecimals3, "130.250", "79.750"), "Overflow measurement is present in grid with correct Value, Meas. Type and Difference");
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(20);
		
		setDate(dateForRollback2);
		
		waitForExtJSAjaxComplete(20);
		
		setIndexValueDigits(valueDigits4);
		
		waitForExtJSAjaxComplete(20);
		
		setIndexValueDecimals(valueDecimals4);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		clickButtonByDialogId("Rollback", getXWindowId("Manage Overflow / Rollback"));
		
		waitForExtJSAjaxComplete(20);
		
		//Check if Measurement with Rollback is added
		softAssert.assertTrue(verifyMeasurementGridValues(rollback, valueDigits4+"."+valueDecimals4, "110.250", "-20.000"), "Rollback measurement is present in grid with correct Value, Meas. Type and Difference");
	
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(20);
		
		setDate(dateForOverflow2);
		
		waitForExtJSAjaxComplete(20);
		
		setIndexValueDigits(valueDigits5);
		
		waitForExtJSAjaxComplete(20);
		
		setIndexValueDecimals(valueDecimals5);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		clickButtonByDialogId("Overflow", getXWindowId("Manage Overflow / Rollback"));
		
		waitForExtJSAjaxComplete(20);
		
		//Check if Measurement with Overflow is added
		softAssert.assertTrue(verifyMeasurementGridValues(overflow, "5.500", "205.500", "95.250"), "Overflow measurement is present in grid with correct Value, Meas. Type and Difference");
		
		softAssert.assertAll();
		
	}
	
	/**
	 * Testcase ID	 : C60493 & C60494
	 * Author		 : ssu
	 * */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testMaxReadingRollbackWOMaxReading() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60493: Measurement: Max reading rollback without Max Reading </span><br>", true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60494: Measurement: Max reading overflow without Max Reading </span><br>", true);
		
		String area = "slnmEnrgArea2";
		String site = "slnmEnrgSite3";
		String supplyPointName = "EAN-1AqaInpreSP";
		String propertiesTab = "Properties";
		
		String code = "C60493preMeter"+getCurrentDate().substring(getCurrentDate().length()-5);
		String commodityName = "Electricity";
		String activeStatus = "Active";
		String meterReference = "RefpreMeter"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		//Field values for Channel creation
		String chnlReference = "C60493chlRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String chnlParameter = "Normal Usage";
		String chnlMultiplier = "1";
		String chnlUOM = "kiloWatthour";
		String chnlRepInterval = "Year";
		String chnlEntryType = "Index Value";
		String chnlEntryMethod = "Manual";
		String chnlCalDate = getFutureDate(-3);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testMaxReadingRollbackWOMaxReading");
		
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
		
		waitForExtJSAjaxComplete(10);
		
		changeTab("predecessors");
		
		waitForExtJSAjaxComplete(5);
		
		changeTab("scope");
		
		waitForExtJSAjaxComplete(5);
		
		changeTab("channels");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		setChnlReference(chnlReference);
		
		setChnlParameter(chnlParameter);
		
		waitForExtJSAjaxComplete(10);
		
		setChnlMultiplier(chnlMultiplier);
		
		setChnlCalDate(chnlCalDate);
		
		//setChnlNumDecimals(chnlNumDecimals);
		
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
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, chnlReference);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewMeasurementsButton();
		
		waitForExtJSAjaxComplete(20);
		
		MeteringGaugesPageObject meteringGaugesPO = new MeteringGaugesPageObject();
		
		String channelMeausrement = meteringGaugesPO.getChannelMeasurements(DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		softAssert.assertTrue(channelMeausrement.contains(chnlReference), "Channel Reference is prepopulated correctly");
		
		GaugesMeasurementsPageObject gaugesMeasurementsPO = new GaugesMeasurementsPageObject();
		
		gaugesMeasurementsPO.setMonthComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Last 7 Days");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		String indexValue = "100";
		String measurementDate = getFutureDate(-4);
		
		System.out.println("measurementDate "+measurementDate);
		
		setDate(measurementDate);
		
		setIndexValue(indexValue);
		
		saveNew(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue((getInfoBarMsgOfWindow("@class", DIALOG_MEASUREMENT)).contains("This Measurement has been saved. Please enter the next one."), "This Measurement has been saved. Please enter the next one.");
		
		String indexValue1 = "50";
		
		setIndexValue(indexValue1);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue((getExtMbContentOfWindow("@class", "x-window-dlg"))
				.contains("The Measurement value is less than the previous value. Please confirm that you want to make a Rollback. If yes, the Rollback Measurement Type will be auto-assigned to this value."),
					"Roll back Dialog Message");
		
		softAssert.assertTrue((verifyButtons("Rollback")), "Rollback Button is available in Dialog Message");
		
		softAssert.assertTrue((verifyButtons("Cancel")), "Cancel Button is available in Dialog Message");
		
		clickButton("Cancel", "x-window-dlg");
		
		close(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		indexValue = indexValue+".000";
		
		checkRowInGriByTextValueAndClick(driver, DIALOG_MTR_CHNL_MEASSUREMENTS, indexValue);
		
		waitForExtJSAjaxComplete(20);

		clickButton("Edit", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(10);
		
		setIndexValue(indexValue1);
		
		saveNew(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		String time = "09:30";
		String indexValue2 = "10";
		
		setIndexValue(indexValue2);
		
		setTime(time);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue((getExtMbContentOfWindow("@class", "x-window-dlg"))
				.contains("The Measurement value is less than the previous value. Please confirm that you want to make a Rollback. If yes, the Rollback Measurement Type will be auto-assigned to this value."),
					"Roll back Dialog Message");
		
		clickButton("Rollback", "x-window-dlg");
		
		waitForExtJSAjaxComplete(20);
		
		String measurementType = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Measurement Type");
		
		softAssert.assertEquals(measurementType, "ROLLBACK", "Rollback is the Measurement Type");
		
		waitForExtJSAjaxComplete(10);
		
		indexValue2 = indexValue2 +".000";
		
		softAssert.assertTrue(verifyGreenColorCode(DIALOG_MTR_CHNL_MEASSUREMENTS, indexValue2), "Measurement Type Row is highlighted with Green");
		
		waitForExtJSAjaxComplete(5);
		
		checkRowInGriByTextValueAndClick(driver, DIALOG_MTR_CHNL_MEASSUREMENTS, indexValue2);
		
		waitForExtJSAjaxComplete(20);

		clickButton("Edit", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(10);
		
		String indexValue3 = "9";
		
		setIndexValue(indexValue3);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		indexValue3 = indexValue3 +".000";
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, indexValue3, "@class", DIALOG_MTR_CHNL_MEASSUREMENTS), indexValue3+" is present");
		
		measurementType = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Measurement Type");
		
		softAssert.assertEquals(measurementType, "ROLLBACK", "Rollback is the Measurement Type");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(verifyGreenColorCode(DIALOG_MTR_CHNL_MEASSUREMENTS, indexValue3), "Measurement Type Row is highlighted with Green");
		
		waitForExtJSAjaxComplete(5);
		
		String differenceValueActual = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Difference (kWh)");
		
		float diffValueExpected = Float.parseFloat(indexValue3) - Float.parseFloat(indexValue1);
		
		String diffExpected = Float.toString(diffValueExpected)+"00";

		softAssert.assertEquals(differenceValueActual, diffExpected, "Difference is recalculated respectively (Current Value - Previous Value)");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("Measurement: Max reading overflow without Max Reading", true);
		
		Reporter.log("Measurement: Max reading rollback without Max Reading is successfully verified", true);
	}
	
	/**
	 * Testcase ID	 : C60500
	 * Author		 : ssu
	 * */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testDifferenceCalculationWOMaxReading() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60500: Measurement: Difference calculation without Max Reading </span><br>", true);
		
		String area = "slnmEnrgArea2";
		String site = "slnmEnrgSite3";
		String supplyPointName = "EAN-1AqaInpreSP";
		String propertiesTab = "Properties";
		
		String code = "C60500preMeter"+getCurrentDate().substring(getCurrentDate().length()-5);
		String commodityName = "Electricity";
		String activeStatus = "Active";
		String meterReference = "RefpreMeter"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		//Field values for Channel creation
		String chnlReference = "C60500chlRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String chnlParameter = "Normal Usage";
		String chnlUOM = "kiloWatthour";
		String chnlRepInterval = "Year";
		String chnlEntryType = "Index Value";
		String chnlEntryMethod = "Manual";
		String chnlCalDate = getFutureDate(-3);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testDifferenceCalculationWOMaxReading");
		
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
		
		setEffectiveDate("15-04-2015");
		
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
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, chnlReference);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewMeasurementsButton();
		
		waitForExtJSAjaxComplete(20);
		
		MeteringGaugesPageObject meteringGaugesPO = new MeteringGaugesPageObject();
		
		String channelMeausrement = meteringGaugesPO.getChannelMeasurements(DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		softAssert.assertTrue(channelMeausrement.contains(chnlReference), "Channel Reference is prepopulated correctly");
		
		GaugesMeasurementsPageObject gaugesMeasurementsPO = new GaugesMeasurementsPageObject();
		
		gaugesMeasurementsPO.setMonthComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Last 7 Days");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		String indexValue = "90";
		String measurementDate = getFutureDate(-4);
		
		System.out.println("measurementDate "+measurementDate);
		
		setDate(measurementDate);
		
		setIndexValue(indexValue);
		
		saveNew(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		//softAssert.assertTrue((getInfoBarMsgOfWindow("@class", DIALOG_MEASUREMENT)).contains("This Measurement has been saved. Please enter the next one."), "This Measurement has been saved. Please enter the next one.");
		
		indexValue = indexValue+".000";
		
		waitForExtJSAjaxComplete(10);
		
		String indexValue1 = "10";
		//String time = "12:30";
		
		setIndexValue(indexValue1);
		
		//setTime(time);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		indexValue1 = indexValue1+".000";
		
		softAssert.assertTrue((getExtMbContentOfWindow("@class", "x-window-dlg"))
			.contains("The Measurement value is less than the previous value. Please confirm that you want to make a Rollback. If yes, the Rollback Measurement Type will be auto-assigned to this value."),
					"Roll back Dialog Message");
		
		clickButton("Rollback", "x-window-dlg");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		checkRowInGriByTextValueAndClick(driver, DIALOG_MTR_CHNL_MEASSUREMENTS, indexValue1);
		
		waitForExtJSAjaxComplete(20);

		clickButton("Edit", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		String indexValue2 = "100";
		//time = "13:00";
		
		setIndexValue(indexValue2);
		
		//setTime(time);

		waitForExtJSAjaxComplete(10);
		
		setMeasurementType("DEFAULT");
		
		waitForExtJSAjaxComplete(25);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		indexValue2 = indexValue2+".000";
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, indexValue2, "@class", DIALOG_MTR_CHNL_MEASSUREMENTS), indexValue2+" is present");
		
		checkRowInGriByTextValueAndClick(driver, DIALOG_MTR_CHNL_MEASSUREMENTS, indexValue2);
		
		waitForExtJSAjaxComplete(20);
		
		String measurementType = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Measurement Type");
		
		softAssert.assertEquals(measurementType, "DEFAULT", "DEFAULT is the Measurement Type");
		
		waitForExtJSAjaxComplete(10);
		
		String differenceValueActual = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Difference (kWh)");
		
		System.out.println("Float.parseFloat(indexValue2) " +Float.parseFloat(indexValue2));
		
		System.out.println("Float.parseFloat(indexValue) " +Float.parseFloat(indexValue));
		
		float diffValueExpected = Float.parseFloat(indexValue2) - Float.parseFloat(indexValue);
		
		System.out.println("diffValueExpected " +diffValueExpected);
		
		String diffExpected = Float.toString(diffValueExpected)+"00";
		
		softAssert.assertEquals(differenceValueActual, diffExpected, "Difference is recalculated respectively (Current Value - Previous Value)");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("Measurement: Difference calculation without Max Reading", true);
	}

	/**
	 * Testcase ID	 : C60501
	 * Author		 : ssu
	 * */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testNegativeValueAddingForUsageValue() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60501: Measurements: Negative value can be added for Usage Value Channels (shows as Rollback) </span><br>", true);
		
		String area = "slnmEnrgArea2";
		String site = "slnmEnrgSite3";
		String supplyPointName = "EAN-1AqaInpreSP";
		String propertiesTab = "Properties";
		
		String code = "C60501preMeter"+getCurrentDate().substring(getCurrentDate().length()-5);
		String commodityName = "Electricity";
		String activeStatus = "Active";
		String meterReference = "RefpreMeter"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		//Field values for Channel creation
		String chnlReference = "C60501chlRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String chnlParameter = "Normal Usage";
		String chnlUOM = "kiloWatthour";
		String chnlRepInterval = "Year";
		String chnlEntryType = "Usage Value";
		String chnlEntryMethod = "Manual";
		String chnlCalDate = getFutureDate(-3);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testNegativeValueAddingForUsageValue");
		
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
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, chnlReference);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewMeasurementsButton();
		
		waitForExtJSAjaxComplete(20);
		
		MeteringGaugesPageObject meteringGaugesPO = new MeteringGaugesPageObject();
		
		String channelMeausrement = meteringGaugesPO.getChannelMeasurements(DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		softAssert.assertTrue(channelMeausrement.contains(chnlReference), "Channel Reference is prepopulated correctly");
		
		GaugesMeasurementsPageObject gaugesMeasurementsPO = new GaugesMeasurementsPageObject();
		
		gaugesMeasurementsPO.setMonthComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Last 7 Days");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		String indexValue = "-100";
		String measurementDate = getFutureDate(-1);
		
		setDate(measurementDate);
		
		setIndexValue(indexValue);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		indexValue = indexValue +".000";
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, indexValue, "@class", DIALOG_MTR_CHNL_MEASSUREMENTS), indexValue+" is present");
		
		String measurementType = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Measurement Type");
		
		softAssert.assertEquals(measurementType, "DEFAULT", "DEFAULT is the Measurement Type");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(verifyGreenColorCode(DIALOG_MTR_CHNL_MEASSUREMENTS, indexValue), "Measurement Type Row is highlighted with Green");
		
		waitForExtJSAjaxComplete(5);
		
		String usageValue = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Usage Value (kWh)");
		
		softAssert.assertEquals(usageValue, indexValue, "Usage Value is displayed Properly");
		
		waitForExtJSAjaxComplete(10);
		
		String totalValue = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Total Value (kWh)");
		
		softAssert.assertEquals(totalValue, indexValue, "Total Value is displayed Properly");
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		String indexValue1 = "100";
		String time = "08:00";
		
		setIndexValue(indexValue1);
		
		setTime(time);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		indexValue1 = indexValue1+".000";
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, indexValue1, "@class", DIALOG_MTR_CHNL_MEASSUREMENTS), indexValue1+" is present");
		
		measurementType = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Measurement Type");
		
		softAssert.assertEquals(measurementType, "DEFAULT", "DEFAULT is the Measurement Type");
		
		waitForExtJSAjaxComplete(10);
		
		usageValue = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Usage Value (kWh)");
		
		softAssert.assertEquals(usageValue, indexValue1, "Usage Value is displayed Properly");
		
		waitForExtJSAjaxComplete(10);
		
		totalValue = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Total Value (kWh)");
		
		softAssert.assertEquals(totalValue, "0.000", "Total Value is displayed Properly");
		
		waitForExtJSAjaxComplete(20);

		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		String indexValue2 = "-100";
		time = getSystemTime();
		
		setIndexValue(indexValue2);
		
		setTime(time);

		waitForExtJSAjaxComplete(10);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		indexValue2 = indexValue2+".000";
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, indexValue, "@class", DIALOG_MTR_CHNL_MEASSUREMENTS), indexValue2+" is present");
		
		measurementType = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Measurement Type");
		
		softAssert.assertEquals(measurementType, "DEFAULT", "DEFAULT is the Measurement Type");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(verifyGreenColorCode(DIALOG_MTR_CHNL_MEASSUREMENTS, indexValue2), "Measurement Type Row is highlighted with Green");
		
		waitForExtJSAjaxComplete(5);
		
		usageValue = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Usage Value (kWh)");
		
		softAssert.assertEquals(usageValue, indexValue2, "Usage Value is displayed Properly");
		
		waitForExtJSAjaxComplete(10);
		
		totalValue = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Total Value (kWh)");
		
		softAssert.assertEquals(totalValue, indexValue2, "Total Value is displayed Properly");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("Measurements: Negative value can be added for Usage Value Channels (shows as Rollback) is successfully verified", true);
	}
}
