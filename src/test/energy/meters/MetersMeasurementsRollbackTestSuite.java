package test.energy.meters;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.energy.gauges.GaugesMeasurementsPageObject;
import test.energy.gauges.MeteringGaugesPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class MetersMeasurementsRollbackTestSuite extends MetersPageObject{
	
	@Test(enabled=true)
	public void testMeterMeasurementRollback() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Meter - Add Rollback Measurement: ?" + " </span><br>",
				true);

		Reporter.log("User adds Rollback Measurement on Meter"  + " <br>",
				true);
		
		String meterCode = "1preMeter";
		String channelReference = "1preMeterMeasureRollback";
		String valueDigits1 = "95";
		String valueDecimals1 = "5";
		String valueDigits2 = "50";
		String valueDecimals2 = "50";
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
		String rollback = "Rollback";
		String overflow = "Overflow";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("AddMeterMeasurementRollback");
		
		//Navigate to Meters Overview
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		expandMetering();
		
		waitForExtJSAjaxComplete(25);
		
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
		
		setDateRange(DIALOG_MTR_CHNL_MEASSUREMENTS, "01-01-2010");

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
		
		clickButtonByDialogId("Yes", getXWindowId("Warning Message"));
		
		waitForExtJSAjaxComplete(20);
		
		//Check if Measurement with Rollback is added  "50.50", "-45"
		softAssert.assertTrue(verifyMeasurementGridValues(rollback, valueDigits2+"."+valueDecimals2, "-45.00", "-45.00"), "Rollback measurement is present in grid with correct Value, Meas. Type and Difference");
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(20);
		
		setDate(dateForOverflow1);
		
		waitForExtJSAjaxComplete(20);
		
		setIndexValueDigits(valueDigits3);
		
		waitForExtJSAjaxComplete(20);
		
		setIndexValueDecimals(valueDecimals3);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		clickButtonByDialogId("Yes", getXWindowId("Warning Message"));
		
		waitForExtJSAjaxComplete(20);
		
		//Check if Measurement with Overflow is added "130.25", "79.75"
		softAssert.assertTrue(verifyMeasurementGridValues(rollback, valueDigits3+"."+valueDecimals3, "-65.25", "-20.25"), "Overflow measurement is present in grid with correct Value, Meas. Type and Difference");
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(20);
		
		setDate(dateForRollback2);
		
		waitForExtJSAjaxComplete(20);
		
		setIndexValueDigits(valueDigits4);
		
		waitForExtJSAjaxComplete(20);
		
		setIndexValueDecimals(valueDecimals4);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		clickButtonByDialogId("Yes", getXWindowId("Warning Message"));
		
		waitForExtJSAjaxComplete(20);
		
		//Check if Measurement with Rollback is added "110.25", "-20"
		softAssert.assertTrue(verifyMeasurementGridValues(rollback, valueDigits4+"."+valueDecimals4, "-85.25", "-20.00"), "Rollback measurement is present in grid with correct Value, Meas. Type and Difference");
	
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(20);
		
		setDate(dateForOverflow2);
		
		waitForExtJSAjaxComplete(20);
		
		setIndexValueDigits(valueDigits5);
		
		waitForExtJSAjaxComplete(20);
		
		setIndexValueDecimals(valueDecimals5);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		clickButtonByDialogId("Yes", getXWindowId("Warning Message"));
		
		waitForExtJSAjaxComplete(20);
		
		//Check if Measurement with Overflow is added "205.5", "95.25"
		softAssert.assertTrue(verifyMeasurementGridValues(rollback, "5.50", "-90.00", "-4.75"), "Overflow measurement is present in grid with correct Value, Meas. Type and Difference");
		
		softAssert.assertAll();
}
	
	/**
	 * Testcase ID	 : C60493 & C60494
	 * Author		 : ssu
	 * */
	@Test(enabled=true)
	public void testMaxReadingRollbackWOMaxReading() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60493: Measurement: Max reading rollback without Max Reading </span><br>", true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60494: Measurement: Max reading overflow without Max Reading </span><br>", true);
		
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite1";
		String supplyPointName = "codePrefixNonUnique";
		String propertiesTab = "Properties";
		
		String code = "C60493preMeter"+getCurrentDate().substring(getCurrentDate().length()-5);
		String commodityName = "Electricity";
		String activeStatus = "Active";
		String meterReference = "RefpreMeter"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		
		//Field values for Channel creation
		String chnlReference = "C60493chlRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String chnlParameter = "Electricity Normal Usage";
		String chnlMultiplier = "1";
		String chnlUOM = "Wh";
		String chnlRepInterval = "Year";
		String chnlEntryType = "Index Value";
		String chnlEntryMethod = "Manual";
		String chnlCalDate = getFutureDate(-3);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testMaxReadingRollbackWOMaxReading");
		
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
		
		setEffectiveDate("01-05-2014");
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityClass(commodityName);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickOnSupplyPoint(supplyPointName); //supplyPointName
		
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
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		changeTab("predecessors");
		
		waitForExtJSAjaxComplete(5);
		
		changeTab("scope");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(5);
		
		changeTab("channels");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", DIALOG_METER, "channels-tab");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
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
		
		gaugesMeasurementsPO.setMonthComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Current Year");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		String indexValue = "100";
		String measurementDate = getFutureDate(-4);
		
		System.out.println("measurementDate "+measurementDate);
		
		setDate(measurementDate);
		
		//setTime(timeManipulation(4, 60));
		
		setIndexValue(indexValue);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		//softAssert.assertTrue((getInfoBarMsgOfWindow("@class", DIALOG_MEASUREMENT)).contains("This Measurement has been saved. Please enter the next one."), "This Measurement has been saved. Please enter the next one.");
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(20);
		
		String indexValue1 = "50";
		measurementDate = getFutureDate(-3);
		
		setDate(measurementDate);
		
		setIndexValue(indexValue1);

		String time = timeManipulation(3, 60);
		
		Reporter.log("Time "+time, true);
		
		setTime(time);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue((getExtMbContentOfWindowWOMcsElement("@class", "x-window-dlg"))
					.contains("The Measurement value is less than the previous value. Please confirm that you want to make a Rollback. If yes, the Rollback Measurement Type will be auto-assigned."),
				"Roll back Dialog Message");

		softAssert.assertTrue((verifyButtons("Yes")), "Yes Button is available in Dialog Message");
		
		softAssert.assertTrue((verifyButtons("No")), "No Button is available in Dialog Message");
		
		clickButton("No", "x-window-dlg");
		
		close(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(20);
		
		indexValue = indexValue +".000";
		
		checkRowInGriByTextValueAndClick(driver, DIALOG_MTR_CHNL_MEASSUREMENTS, indexValue);
		
		waitForExtJSAjaxComplete(20);

		clickButton("Edit", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(10);
		
		setDate(measurementDate);
		
		setIndexValue(indexValue1);
		
		setTime(time);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue((getExtMbContentOfWindowWOMcsElement("@class", "x-window-dlg"))
				.contains("The Measurement value is less than the previous value. Please confirm that you want to make a Rollback. If yes, the Rollback Measurement Type will be auto-assigned."),
			"Roll back Dialog Message");
		
		clickButton("Yes", "x-window-dlg");
		
		waitForExtJSAjaxComplete(20);
		
		String indexValue2 = "10";
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(10);
		
		measurementDate = getFutureDate(-2);
		
		setDate(measurementDate);
		
		setIndexValue(indexValue2);
		
		setTime(timeManipulation(5, 60));
		
		waitForExtJSAjaxComplete(25);
		
		setNotesMeasurementPage("Testing");
		
		waitForExtJSAjaxComplete(25);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(25);
		
		/*saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(25);*/
		
		softAssert.assertTrue((getExtMbContentOfWindowWOMcsElement("@class", "x-window-dlg"))
					.contains("The Measurement value is less than the previous value. Please confirm that you want to make a Rollback. If yes, the Rollback Measurement Type will be auto-assigned"),
				"Roll back Dialog Message");
		
		waitForExtJSAjaxComplete(25);

		clickButton("Yes", "x-window-dlg");
		
		waitForExtJSAjaxComplete(20);
		
		String measurementType = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Measurement Type");
		
		softAssert.assertEquals(measurementType, "Rollback", "Rollback is the Measurement Type");
		
		waitForExtJSAjaxComplete(10);
		
		indexValue2 = indexValue2 +".000";
		
		softAssert.assertTrue(verifyGreenColorCode(DIALOG_MTR_CHNL_MEASSUREMENTS, indexValue2), "Measurement Type Row is highlighted with Green");
		
		waitForExtJSAjaxComplete(5);
		
		checkRowInGriByTextValueAndClick(driver, DIALOG_MTR_CHNL_MEASSUREMENTS, indexValue2);
		
		waitForExtJSAjaxComplete(20);

		clickButton("Edit", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(10);
		
		String indexValue3 = "9";
		
		measurementDate = getFutureDate(-1);
		
		setDate(measurementDate);
		
		setIndexValue(indexValue3);
		
		setTime(timeManipulation(6, 60));
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		indexValue3 = indexValue3 + ".000";
		
		boolean status = isRowInGridPresentCustomized("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Index Value", indexValue3);
		
		if(status){
		
			softAssert.assertTrue(isRowInGridPresentCustomized("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Index Value", indexValue3), "Index value is Present");
			
			//closeUsingToolBarMeasurements(DIALOG_MEASUREMENT);
			
			waitForExtJSAjaxComplete(25);
		} else{
			closeUsingToolBarMeasurements(DIALOG_MEASUREMENT);
			
			waitForExtJSAjaxComplete(25);
			
			clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
			
			System.out.println("measurementDate "+measurementDate);
			
			setDate(measurementDate);
			
			setIndexValue(indexValue3);
			
			setTime(timeManipulation(6, 60));
			
			saveClose(DIALOG_MEASUREMENT);
			
			waitForExtJSAjaxComplete(25);
		}
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, indexValue3, "@class", DIALOG_MTR_CHNL_MEASSUREMENTS), indexValue3+" is present");
		
		measurementType = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Measurement Type");
		
		softAssert.assertEquals(measurementType, "Rollback", "Rollback is the Measurement Type");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(verifyGreenColorCode(DIALOG_MTR_CHNL_MEASSUREMENTS, indexValue3), "Measurement Type Row is highlighted with Green");
		
		waitForExtJSAjaxComplete(25);
		
		String differenceValueActual = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Difference (Wh)");
		
		float diffValueExpected = Float.parseFloat(indexValue3) - Float.parseFloat(indexValue1);
		
		String diffValueExpectedStr = diffValueExpected +"00";
		
		softAssert.assertEquals(differenceValueActual, diffValueExpectedStr, "Difference is recalculated respectively (Current Value - Previous Value)");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
				
		Reporter.log("Measurement: Max reading overflow without Max Reading", true);
		
		Reporter.log("Measurement: Max reading rollback without Max Reading is successfully verified", true);
	}
	
	/**
	 * Testcase ID	 : C60500
	 * Author		 : ssu
	 * */
	@Test(enabled=true)
	public void testDifferenceCalculationWOMaxReading() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60500: Measurement: Difference calculation without Max Reading </span><br>", true);
		
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite2";
		String supplyPointName = "EAN-1preSP";
		String propertiesTab = "Properties";
		
		String code = "C60500preMeter"+getCurrentDate().substring(getCurrentDate().length()-5);
		String commodityName = "Electricity";
		String activeStatus = "Active";
		String meterReference = "RefpreMeter"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		//Field values for Channel creation
		String chnlReference = "C60500chlRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String chnlParameter = "Electricity Normal Usage";
		String chnlUOM = "Wh";
		String chnlRepInterval = "Year";
		String chnlEntryType = "Index Value";
		String chnlEntryMethod = "Manual";
		String chnlCalDate = getFutureDate(-3);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testDifferenceCalculationWOMaxReading");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		Thread.sleep(4000);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickMetersInfrastructureTab();
		
		waitForExtJSAjaxComplete(5);
		
		setEffectiveDate("01-04-2014");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		setCommodityClass(commodityName);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickOnSupplyPoint(supplyPointName);
		
		waitForExtJSAjaxComplete(20);
		
		clickCreateMeter();
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(ADD_METER_WINDOW_HEADER, propertiesTab);
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setCode(code);
		
		setReference(meterReference);
		
		setCommodity(commodityName);
		
		waitForExtJSAjaxComplete(10);
		
		setStatus(activeStatus);
		
		waitForExtJSAjaxComplete(10);
		
		save(DIALOG_METER);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		changeTab("channels");
		
		Thread.sleep(4000);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickButton("Add", DIALOG_METER, "channels-tab");
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
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
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, chnlReference);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewMeasurementsButton();
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		MeteringGaugesPageObject meteringGaugesPO = new MeteringGaugesPageObject();
		
		String channelMeausrement = meteringGaugesPO.getChannelMeasurements(DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		softAssert.assertTrue(channelMeausrement.contains(chnlReference), "Channel Reference is prepopulated correctly");
		
		GaugesMeasurementsPageObject gaugesMeasurementsPO = new GaugesMeasurementsPageObject();
		
		gaugesMeasurementsPO.setMonthComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Current Year");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(25);
		
		String indexValue = "90";
		String measurementDate = getFutureDate(-4);
		
		System.out.println("measurementDate "+measurementDate);
		
		setDate(measurementDate);
		
		setIndexValue(indexValue);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		//softAssert.assertTrue((getInfoBarMsgOfWindow("@class", DIALOG_MEASUREMENT)).contains("This Measurement has been saved. Please enter the next one."), "This Measurement has been saved. Please enter the next one.");
		
		indexValue = indexValue+".000";
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(25);
		
		String indexValue1 = "10";
		String time = getSystemTime();
		measurementDate = getFutureDate(-3);
		
		setDate(measurementDate);

		setIndexValue(indexValue1);
		
		setTime(time);
		
		waitForExtJSAjaxComplete(25);
		
		setNotesMeasurementPage("Testing");
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(25);
		
		indexValue1 = indexValue1+".000";
		
		
			softAssert.assertTrue((getExtMbContentOfWindow("@class", "x-window-dlg"))
					.contains("The Measurement value is less than the previous value. Please confirm that you want to make a Rollback. If yes, the Rollback Measurement Type will be auto-assigned."),
			"Roll back Dialog Message");
		
		clickButton("Yes", "x-window-dlg");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		checkRowInGriByTextValueAndClick(driver, DIALOG_MTR_CHNL_MEASSUREMENTS, indexValue1);
		
		waitForExtJSAjaxComplete(20);

		clickButton("Edit", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		waitForExtJSAjaxComplete(25);
		
		String indexValue2 = "100";
		time = getSystemTime();
		
		setIndexValue(indexValue2);
		
		setTime(time);

		waitForExtJSAjaxComplete(10);
		
		setMeasurementType("Default");
		
		waitForExtJSAjaxComplete(25);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		indexValue2 = indexValue2+".000";
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, indexValue2, "@class", DIALOG_MTR_CHNL_MEASSUREMENTS), indexValue2+" is present");
		
		checkRowInGriByTextValueAndClick(driver, DIALOG_MTR_CHNL_MEASSUREMENTS, indexValue2);
		
		waitForExtJSAjaxComplete(20);
		
		String measurementType = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Measurement Type");
		
		softAssert.assertEquals(measurementType, "Default", "Default is the Measurement Type");
		
		waitForExtJSAjaxComplete(10);
		
		String differenceValueActual = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Difference (Wh)");
		
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
	@Test(enabled=true)
	public void testNegativeValueAddingForUsageValue() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60501: Measurements: Negative value can be added for Usage Value Channels (shows as Rollback) </span><br>", true);
		
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite2";
		String supplyPointName = "EAN-1preSP";
		String propertiesTab = "Properties";
		
		String code = "C60501preMeter"+getCurrentDate().substring(getCurrentDate().length()-5);
		String commodityName = "Electricity";
		String activeStatus = "Active";
		String meterReference = "RefpreMeter"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		//Field values for Channel creation
		String chnlReference = "C60501chlRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String chnlParameter = "Electricity Normal Usage";
		String chnlUOM = "Wh";
		String chnlRepInterval = "Year";
		String chnlEntryType = "Usage Value";
		String chnlEntryMethod = "Manual";
		String chnlCalDate = getFutureDate(-3);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testNegativeValueAddingForUsageValue");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		Thread.sleep(4000);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickMetersInfrastructureTab();
		
		waitForExtJSAjaxComplete(5);
		
		setEffectiveDate("01-10-2014");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		setCommodityClass(commodityName);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickOnSupplyPoint(supplyPointName); //supplyPointName
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
				
		clickCreateMeter();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnTab(ADD_METER_WINDOW_HEADER, propertiesTab);
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(25);
		
		setCode(code);
		
		setReference(meterReference);
		
		setCommodity(commodityName);
		
		waitForExtJSAjaxComplete(10);
		
		setStatus(activeStatus);
		
		waitForExtJSAjaxComplete(10);
		
		save(DIALOG_METER);
		
		Thread.sleep(5000);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		changeTab("channels");
		
		Thread.sleep(5000);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickButton("Add", DIALOG_METER, "channels-tab");

		Thread.sleep(5000);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
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
		
		Thread.sleep(4000);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, chnlReference);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewMeasurementsButton();
		
		Thread.sleep(4000);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		MeteringGaugesPageObject meteringGaugesPO = new MeteringGaugesPageObject();
		
		String channelMeausrement = meteringGaugesPO.getChannelMeasurements(DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		softAssert.assertTrue(channelMeausrement.contains(chnlReference), "Channel Reference is prepopulated correctly");
		
		GaugesMeasurementsPageObject gaugesMeasurementsPO = new GaugesMeasurementsPageObject();
		
		gaugesMeasurementsPO.setMonthComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Current Year");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(20);
		
		String indexValue = "-100";
		String measurementDate = getFutureDate(-2);
		
		setDate(measurementDate);
		
		setIndexValue(indexValue);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		indexValue = indexValue +".000";
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, indexValue, "@class", DIALOG_MTR_CHNL_MEASSUREMENTS), indexValue+" is present");
		
		String measurementType = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Measurement Type");
		
		softAssert.assertEquals(measurementType, "Rollback", "Rollback is the Measurement Type");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(verifyAnyColorCode(DIALOG_MTR_CHNL_MEASSUREMENTS, indexValue, "mcsgridrow_46A546_ x-grid3-row-first"), "Measurement Type Row is highlighted with Green");
		
		waitForExtJSAjaxComplete(5);
		
		String usageValue = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Usage Value (Wh)");
		
		softAssert.assertEquals(usageValue, indexValue, "Usage Value is displayed Properly");
		
		waitForExtJSAjaxComplete(10);
		
		String totalValue = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Total Value (Wh)");
		
		softAssert.assertEquals(totalValue, "-100.000", "Total Value is displayed Properly");
		
		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		Thread.sleep(2000);
		
		String indexValue1 = "100";
		String time = "16:00";
		measurementDate = getFutureDate(-1);
		
		setDate(measurementDate);
		
		setIndexValue(indexValue1);
		
//		/setTime(time);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(25);
		
		indexValue1 = indexValue1+".000";
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, indexValue1, "@class", DIALOG_MTR_CHNL_MEASSUREMENTS), indexValue1+" is present");
		
		measurementType = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Measurement Type");
		
		softAssert.assertEquals(measurementType, "Default", "Default is the Measurement Type");
		
		waitForExtJSAjaxComplete(10);
		
		usageValue = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Usage Value (Wh)");
		
		softAssert.assertEquals(usageValue, indexValue1, "Usage Value is displayed Properly");
		
		waitForExtJSAjaxComplete(10);
		
		totalValue = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Total Value (Wh)");
		
		softAssert.assertEquals(totalValue, "0.000", "Total Value is displayed Properly");
		
		waitForExtJSAjaxComplete(25);

		clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		
		Thread.sleep(2000);
		
		String indexValue2 = "-100";
		time = getSystemTime();
		measurementDate = getFutureDate(0);
		
		setDate(measurementDate);
		
		setIndexValue(indexValue2);
		
		//setTime(time);

		waitForExtJSAjaxComplete(10);
		
		saveClose(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		indexValue2 = indexValue2+".000";
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, indexValue, "@class", DIALOG_MTR_CHNL_MEASSUREMENTS), indexValue2+" is present");
		
		measurementType = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Measurement Type");
		
		softAssert.assertEquals(measurementType, "Rollback", "Rollback is the Measurement Type");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(verifyAnyColorCode(DIALOG_MTR_CHNL_MEASSUREMENTS, indexValue2, "mcsgridrow_46A546_ x-grid3-row-last"), "Measurement Type Row is highlighted with Green");
		
		waitForExtJSAjaxComplete(5);
		
		usageValue = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Usage Value (Wh)");
		
		softAssert.assertEquals(usageValue, indexValue2, "Usage Value is displayed Properly");
		
		waitForExtJSAjaxComplete(10);
		
		totalValue = meteringGaugesPO.getValueOfAnyColumn("@class", DIALOG_MTR_CHNL_MEASSUREMENTS, "Total Value (Wh)");
		
		softAssert.assertEquals(totalValue, "-100.000", "Total Value is displayed Properly");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		
		Reporter.log("Measurements: Negative value can be added for Usage Value Channels (shows as Rollback) is successfully verified", true);
	}
}
