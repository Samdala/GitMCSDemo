package test.energy.gauges122;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class MeteringGaugesCopyTestSuite122 extends MeteringGaugesCopyPageObject122 {
	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testCopyGauge() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Copy Gauge: c19688 " + " </span><br>",
				true);

		Reporter.log("User copies Gauge "  + " <br>",
				true);
		
		final String gaugeCode = "1preGaugeCopy";
		String serialNumber = "13";
		String model = "Model";
		String timeZone = "(GMT+02:00) Cairo";
		String status = "Active";
		String manufacturer = "Manufacturer";
		String description = "Description";
		String accessInstructions = "Access";
		String date = "01-11-2013";
		
		String channelReference = "preGaugeCopyChannel";
		String calculationMethod = "Sum";
		String parameter = "Building Volume";
		String uom = "liter";
		String entryMethod = "Automatic";
		String smallestReportingInterval = "Day";
		String channelDate = "15-07-2013";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("CopyGauge");

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		openMeteringEntity("Gauges");

		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, gaugeCode);
		Reporter.log("Select Gauge with code - " +gaugeCode, true);
		
		clickButton("Copy Gauge", GAUGE_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		selectMultipleLocations();
		
		Reporter.log("Check if all selected locations are present on Copy Gauge dialog grid", true);
		softAssert.assertTrue(isRowInGridPresent(driver, "Building|"+building1, "@class", DIALOG_GAUGE_COPY), "Building "+building1+" is not present on Copy Gauge dialog grid");
		
		softAssert.assertTrue(isRowInGridPresent(driver, "Building|"+building2, "@class", DIALOG_GAUGE_COPY), "Building "+building2+" is not present on Copy Gauge dialog grid");
		
		clickButton("Create", DIALOG_GAUGE_COPY);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if gauges where copied per each location (check 'OK' status)", true);
		softAssert.assertTrue(isRowInGridPresent(driver, "Building|"+building1+"|OK", "@class", DIALOG_GAUGE_COPY), "Copying Gauge to Building "+building1+" failed");
		
		softAssert.assertTrue(isRowInGridPresent(driver, "Building|"+building2+"|OK", "@class", DIALOG_GAUGE_COPY), "Copying Gauge to Building "+building2+" failed");
		
		clickButton("Close", DIALOG_GAUGE_COPY);;
		
		waitForExtJSAjaxComplete(20);
		
		clickRefreshButton();
		
		waitForExtJSAjaxComplete(20);
		
		isRowInGridPresentAndClick(driver, gaugeCode+"|"+building1, "div");
		
		waitForExtJSAjaxComplete(20);
		
		isRowInGridPresentAndClick(driver, gaugeCode+"|"+building2, "div");
		
		waitForExtJSAjaxComplete(20);
		
		clickButton("Edit", GAUGE_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
				
		Reporter.log("Check if Gauge fields values are copied correctly", true);
		softAssert.assertEquals(getCommissioningDate(), date, date+ " proposer after copy is wrong");
		
		softAssert.assertEquals(getStatus(),status, status+ " status after copy is wrong");
		
		softAssert.assertEquals(getSerialNumber(),serialNumber, serialNumber+ " Serial Number after copy is wrong");
		
		softAssert.assertEquals(getDescription(),description, description+ " Description after copy is wrong");
				
		softAssert.assertEquals(getModel(),model, model+ " Model after copy is wrong");
		
		softAssert.assertEquals(getTimeZone(),timeZone, timeZone+ " TimeZone after copy is wrong");
		
		softAssert.assertEquals(getManufacturer(),manufacturer, manufacturer+ " Manufacturer after copy is wrong");
		
		softAssert.assertEquals(getAccessInstructions(),accessInstructions, accessInstructions+ " Access Instructions after copy is wrong");
		
		softAssert.assertEquals(getLocation(), "Select a Location", "Location field after copy is not blank");
		
		changeTab("channels");
		
		waitForExtJSAjaxComplete(20);
		
		checkRowInGriByTextValueAndClick(driver, channelReference);
		
		waitForExtJSAjaxComplete(25);
		
		clickButton("Edit", DIALOG_GAUGE);
		
		waitForExtJSAjaxComplete(25);
		
		Reporter.log("Check if Gauge Channel fields values are copied correctly", true);
		softAssert.assertEquals(getReference(), channelReference, channelReference+ " Channel Reference after copy is wrong");
				
		softAssert.assertEquals(getCalculationMethod(), calculationMethod, calculationMethod+ " Calculation Method after copy is wrong");
		
		softAssert.assertEquals(getChannelParameter(), parameter, parameter+ " Channel Parameter after copy is wrong");
		
		softAssert.assertEquals(getUnitOfMeasure(), uom, uom+ " UOM after copy is wrong");
		
		softAssert.assertEquals(getEntryMethod(), entryMethod, entryMethod+ " Entry Method after copy is wrong");
		
		softAssert.assertEquals(getSmallestReportingInterval(), smallestReportingInterval, smallestReportingInterval+ " Smallest Reporting Interval after copy is wrong");
		
		softAssert.assertEquals(getCalibrationDate(), channelDate, channelDate+ " Calibration Date after copy is wrong");
			
		softAssert.assertAll();
				
	}

}
