package test.energy.weatherstations;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.energy.gauges.GaugesMeasurementsPageObject;
import test.energy.gauges.MeteringGaugesChanelsPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class MeteringWeatherstationChanelActualDataTestSuite extends
		MeteringWeatherstationChanelsActualDataPageObject {



	@Test(enabled = true)
	public void testWeatherstationChannelCDDImportDataCheck() throws IOException  {

		 Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Weather Stations CDD channels import data " + " </span><br>",
					true);

			Reporter.log("User WeatherstationActualData Cooling days check: C19914 testrail "  + " <br>",
					true);
		
			
			String gaugeCode = "1preWeatherStationNotCalc";
			String channelReference = "CDD 2.5";
			String channelParameter = "Cooling Degree-days";
			String readingInterval = "Day";
			String baseTemperature = "2.5";
			
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("WeatherstationChannelCDDImportDataCheck");

			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(20);
			
			//expandMetering
			expandMetering();
			
			//open Weather Stations
			openAnalysisEntity("Weather Stations");

			waitForExtJSAjaxComplete(20);
			
			//select predefined weather station
			Grid.checkRowInGriByTextValue(driver, gaugeCode);
			
			//click Edit Button for weather station
			clickEditButton(WEATHERSTATIONS_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			//Select channels tab
			changeTab("channels");
		
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			//get Weather station dialog id
			weatherstationDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			boolean status = Grid.isRowInGridPresent(driver, "CDD 2.5 °C", "@class", ADD_WEATHERSTATIONS_FORM_CLASS);
			
			/*if(status) {
				
				Grid.checkRowInGriByTextValueAndClick(driver, ADD_WEATHERSTATIONS_FORM_CLASS, "CDD 2.5 °C");
					
				clickButton("Delete", weatherstationDialogId);
				
				clickOnDialogButton("Yes");

				waitForExtJSAjaxComplete(10);
			}*/
			
			if(!status){
				//Click add button for new Channel			
				clickButton("Add", weatherstationDialogId);
				
				waitForExtJSAjaxComplete(20);
				
				//set channel parameter
				setChannelParameter(channelParameter);
				
				waitForExtJSAjaxComplete(25);
				
				//set Base Temperature
				setBaseTemperature(baseTemperature);
				
				waitForExtJSAjaxComplete(25);
				
				//Verify reference
				softAssert.assertLike(getReference(),channelReference, channelReference+" reference before edit ");
				
				//Verify channel parameter
				softAssert.assertEquals(getChannelParameter(),channelParameter, channelParameter+ " channelParameter is ");
				
				//Verify channel Reading Interval
				softAssert.assertEquals(getReadingInterval(),readingInterval, readingInterval+ " Reading Interval is ");
				
				//Save and Close Channel
				saveClose();
				
				waitForExtJSAjaxComplete(25);
			}
						
			//select created channel
			checkRowInGriByTextValueAndClick(driver, channelReference);
			
			waitForExtJSAjaxComplete(25);
			
			//get channel dialog id
			channelDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			//open Actual Data dialog
			clickButton("Actual Data", channelDialogId);
			
			waitForExtJSAjaxComplete(100);
			
			//get Actual dialog id
			actualDataDialogId = getXWindowId("Actual Data");
			
			waitForExtJSAjaxComplete(25);
			
			//Click Import data button
			clickButton("Import Data", actualDataDialogId);
			
			//get import dialog id by header
			importDialogId = getXWindowId("Import CSV file");
			
			//Select uploading file
			UploadFile("test.csv", importDialogId);
			
			//perform import
			clickButton("Import", importDialogId);
			
			waitForElementDisappear("//div[contains(@id,'"
					+ importDialogId + "')]//input[@type='file']");
			
			waitForExtJSAjaxComplete(25);
			
			//Close dialog
			clickOnDialogButton("OK");
			
			waitForExtJSAjaxComplete(25);
			
			setDateRange("01-01-2011 to 31-12-2011");
						
			waitForExtJSAjaxComplete(20);
			
			//setDateRange for actual data
			/*McsElement.getElementByXpath(driver, "//input[@id='wsMeasurementsPopup_dateRangePickerInterval']/..//img").click();
			
			McsElement.getElementByXpath(driver, "//span[text()='Last 3 Years']").click();*/
			
			//setDateRange("25-11-2010 to 25-11-2013");
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "31-12-2011|7", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "30-12-2011|4", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");

			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "29-12-2011|6", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "28-12-2011|6", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "27-12-2011|8", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "26-12-2011|9", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "25-12-2011|8", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "24-12-2011|4", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "23-12-2011|9", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			waitForExtJSAjaxComplete(25);
			
			//Close actual data dialog
			closeActualData();
			
			waitForExtJSAjaxComplete(25);
			
			//select created channel
			checkRowInGriByTextValueAndClick(driver, channelReference);
			
			//click Delete button for channel
			clickDeleteButton(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			//Close weather station dialog
			closeWeatherstation();
			
			//assert
			softAssert.assertAll();
			
			Reporter.log("WeatherstationActualDataCheck was succesfully done.", true);

		}
	 
	@Test(enabled = true)
	public void testWeatherstationChannelHDDImportDataCheck() throws IOException  {

		 Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Weather Stations HDD channels import data" + " </span><br>",
					true);

			Reporter.log("User WeatherstationActualData Heating degree days check: C19915 testrail "  + " <br>",
					true);
		
			
			String gaugeCode = "1preWeatherStationNotCalc";
			String channelReference = "HDD 1.5";
			String channelParameter = "Heating Degree-days";
			String readingInterval = "Day";
			String baseTemperature = "1.5";
			
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("WeatherstationChannelHDDImportDataCheck");

			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(20);
			
			//expandMetering
			expandMetering();
			
			//open Weather Stations
			openAnalysisEntity("Weather Stations");

			waitForExtJSAjaxComplete(20);
			
			//select predefined weather station
			Grid.checkRowInGriByTextValue(driver, gaugeCode);
			
			//click Edit Button for weather station
			clickEditButton(WEATHERSTATIONS_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			//Select channels tab
			changeTab("channels");
		
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			//get Weather station dialog id
			weatherstationDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			boolean status = Grid.isRowInGridPresent(driver, "HDD 1.5 °C", "@class", ADD_WEATHERSTATIONS_FORM_CLASS);
			
			if(!status){
			
			//Click add button for new Channel			
			clickButton("Add", weatherstationDialogId);
			
			waitForExtJSAjaxComplete(20);
			
			//set channel parameter
			setChannelParameter(channelParameter);
			
			waitForExtJSAjaxComplete(25);
			
			//set Base Temperature
			setBaseTemperature(baseTemperature);
			
			waitForExtJSAjaxComplete(25);
			
			//Verify reference
			softAssert.assertLike(getReference(),channelReference, channelReference+" reference before edit ");
			
			//Verify channel parameter
			softAssert.assertEquals(getChannelParameter(),channelParameter, channelParameter+ " channelParameter is ");
			
			//Verify channel Reading Interval
			softAssert.assertEquals(getReadingInterval(),readingInterval, readingInterval+ " Reading Interval is ");
			
			//Save and Close Channel
			saveClose();
			
			waitForExtJSAjaxComplete(25);
			} 
			
			//select created channel
			checkRowInGriByTextValueAndClick(driver, channelReference);
			
			waitForExtJSAjaxComplete(25);
			
			//get channel dialog id
			channelDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			//open Actual Data dialog
			clickButton("Actual Data", channelDialogId);
			
			waitForExtJSAjaxComplete(100);
			
			//get Actual dialog id
			actualDataDialogId = getXWindowId("Actual Data");
			
			waitForExtJSAjaxComplete(25);
			
			//Click Import data button
			clickButton("Import Data", actualDataDialogId);
			
			//get import dialog id by header
			importDialogId = getXWindowId("Import CSV file");
			
			//Select uploading file
			UploadFile("test.csv", importDialogId);
			
			//perform import
			clickButton("Import", importDialogId);
			
			waitForExtJSAjaxComplete(20);
			
			waitForElementDisappear("//div[contains(@id,'"
					+ importDialogId + "')]//input[@type='file']");
			
			//Close dialog
			clickOnDialogButton("OK");
			
			//setDateRange for actual data
			waitForExtJSAjaxComplete(25);
			
			setDateRange("01-01-2011 to 30-12-2014");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "31-12-2011", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "30-12-2011", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");

			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "29-12-2011", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "28-12-2011", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "27-12-2011", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "26-12-2011", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "25-12-2011", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "24-12-2011", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "23-12-2011", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			waitForExtJSAjaxComplete(25);
			
			//Close actual data dialog
			closeActualData();
			
			waitForExtJSAjaxComplete(25);
			
			//select created channel
			checkRowInGriByTextValueAndClick(driver, channelReference);
			
			//click Delete button for channel
			clickDeleteButton(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			//Close weather station dialog
			closeWeatherstation();
			
			//assert
			softAssert.assertAll();
			
			Reporter.log("WeatherstationActualDataCheck was succesfully done.", true);

		}	 
	
	@Test(enabled = true)
	public void testWeatherstationChannelCalculationsFromMDT() throws Exception  {

		 Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Weather Stations CDD/HDD channels  calculations From MDT " + " </span><br>",
					true);

			Reporter.log("User WeatherstationActualData Cooling/Heating days check: C19916 testrail "  + " <br>",
					true);
		
			String channelReference = "Mean Daily Temperature";
			
			String gaugeCode = "1preWeatherStation";
			String channelReference1 = "CDD 5.5";
			String channelParameter1 = "Cooling Degree-days";
			String readingInterval1 = "Day";
			String baseTemperature1 = "5.5";
			
			String channelReference2 = "HDD 2.0";
			String channelParameter2 = "Heating Degree-days";
			String readingInterval2 = "Day";
			String baseTemperature2 = "2.0";
			
			
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("WeatherstationChannelCalculationsFromMDT");

			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			//expandMetering
			expandMetering();
			
			waitForExtJSAjaxComplete(25);
			
			//open Weather Stations
			openAnalysisEntity("Weather Stations");
			
			Thread.sleep(2000);

			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			//select predefined weather station
			Grid.checkRowInGriByTextValue(driver, gaugeCode);
			
			//click Edit Button for weather station
			clickEditButton(WEATHERSTATIONS_GRID_CLASS);
			
			Thread.sleep(5000);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			//Select channels tab
			changeTab("channels");
			
			Thread.sleep(5000);
		
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			//get Weather station dialog id
			weatherstationDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			checkRowInGriByTextValueAndClick(driver, channelReference);
			
			waitForExtJSAjaxComplete(25);
			
			//get channel dialog id
			channelDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			//open Actual Data dialog
			clickButton("Actual Data", channelDialogId);
			
			waitForExtJSAjaxComplete(100);
			
			//get Actual dialog id
			actualDataDialogId = getXWindowId("Actual Data");
			
			waitForExtJSAjaxComplete(25);
			
			//Click Import data button
			clickButton("Import Data", actualDataDialogId);
			
			//get import dialog id by header
			importDialogId = getXWindowId("Import CSV file");
			
			//Select uploading file
			UploadFile("test1.csv", importDialogId);
			
			//perform import
			clickButton("Import", importDialogId);
			
			waitForElementDisappear("//div[contains(@id,'"
					+ importDialogId + "')]//input[@type='file']");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			Thread.sleep(5000);
			
			//Close dialog
			clickOnDialogButton("OK");
			
			Thread.sleep(5000);
			
			waitForExtJSAjaxComplete(25);
			
			//setDateRange for actual data
			//setDateRange("01-01-2010 to 31-12-2011");
			setDateRangeCustomized(ACTUALDATA_WEATHERSTATIONS_FORM_CLASS, "01-01-2010");
			
			Thread.sleep(2000);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(20);	
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "31-12-2011 00:00|7", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "30-12-2011 00:00|4", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");

			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "29-12-2011 00:00|6", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "28-12-2011 00:00|6", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "27-12-2011 00:00|8", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "26-12-2011 00:00|9", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "25-12-2011 00:00|8", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "24-12-2011 00:00|4", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "23-12-2011 00:00|9", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			waitForExtJSAjaxComplete(25);
			
			//Close actual data dialog
			closeActualData();
			
			waitForExtJSAjaxComplete(25);
			
			boolean status = Grid.isRowInGridPresent(driver, "CDD 5.5 °C", "@class", ADD_WEATHERSTATIONS_FORM_CLASS);
			
			//IF CDD 5.5 is already added it displays differently for the date range
			
			if(!status){
			
			//Click add button for new Channel			
			clickButton("Add", weatherstationDialogId);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			//set channel parameter
			setChannelParameter(channelParameter1);
			
			waitForExtJSAjaxComplete(25);
			
			//set Base Temperature
			setBaseTemperature(baseTemperature1);
			
			waitForExtJSAjaxComplete(25);
			
			//Verify reference
			softAssert.assertLike(getReference(),channelReference1, channelReference1+" reference before edit ");
			
			//Verify channel parameter
			softAssert.assertEquals(getChannelParameter(),channelParameter1, channelParameter1+ " channelParameter is ");
			
			//Verify channel Reading Interval
			softAssert.assertEquals(getReadingInterval(),readingInterval1, readingInterval1 + " Reading Interval is ");
			
			//Save and Close Channel
			save();
			
			waitForExtJSAjaxComplete(25);
			
			close(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS);
			
			waitForExtJSAjaxComplete(25);
			
			//select created channel
			checkRowInGriByTextValueAndClick(driver, channelReference1);
			
			//open Actual Data dialog
			clickButton("Actual Data", channelDialogId);
			
			waitForExtJSAjaxComplete(100);
			
			//get Actual dialog id
			actualDataDialogId = getXWindowId("Actual Data");
			
			waitForExtJSAjaxComplete(25);
			
			//setDateRange for actual data

			setDateRange("01-01-2010 to 31-12-2011");
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "31-12-2011|0.5", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "30-12-2011|0", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");

			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "29-12-2011|0.7", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "28-12-2011|1.4", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "27-12-2011|2.8", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "26-12-2011|2.7", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "25-12-2011|1.4", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "24-12-2011|0.4", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "23-12-2011|2.7", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			} else{
			
			//select created channel
			checkRowInGriByTextValueAndClick(driver, channelReference1);
			
			//open Actual Data dialog
			clickButton("Actual Data", channelDialogId);
			
			waitForExtJSAjaxComplete(100);
			
			//get Actual dialog id
			actualDataDialogId = getXWindowId("Actual Data");
			
			waitForExtJSAjaxComplete(25);
			
			//setDateRange for actual data

			setDateRange("01-01-2010 to 31-12-2011");
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "31-12-2011|0.5", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "30-12-2011|0", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");

			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "29-12-2011|0.7", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "28-12-2011|1.4", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "27-12-2011|2.8", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "26-12-2011|2.7", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "25-12-2011|1.4", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "24-12-2011|0.4", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "23-12-2011|2.7", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			waitForExtJSAjaxComplete(25);
			}
			
			//Close actual data dialog
			closeActualData();
			
			waitForExtJSAjaxComplete(25);
		
			
			status = Grid.isRowInGridPresent(driver, "HDD 2.0", "@class", ADD_WEATHERSTATIONS_FORM_CLASS);
			
			waitForExtJSAjaxComplete(25);
			
			if(!status){
			
			//Click add button for new Channel			
			clickButton("Add", weatherstationDialogId);
			
			waitForExtJSAjaxComplete(20);
			
			//set channel parameter
			setChannelParameter(channelParameter2);
			
			waitForExtJSAjaxComplete(25);
			
			//set Base Temperature
			setBaseTemperature(baseTemperature2);
			
			waitForExtJSAjaxComplete(25);
			
			//Verify reference
			softAssert.assertLike(getReference(),channelReference2, channelReference2+" reference before edit ");
			
			//Verify channel parameter
			softAssert.assertEquals(getChannelParameter(),channelParameter2, channelParameter2+ " channelParameter is ");
			
			//Verify channel Reading Interval
			softAssert.assertEquals(getReadingInterval(),readingInterval2, readingInterval2 + " Reading Interval is ");
			
			//Save and Close Channel
			save();
			
			waitForExtJSAjaxComplete(25);
			
			close(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			//select created channel
			checkRowInGriByTextValueAndClick(driver, channelReference2);
			
			//open Actual Data dialog
			clickButton("Actual Data", channelDialogId);
			
			waitForExtJSAjaxComplete(100);
			
			//get Actual dialog id
			actualDataDialogId = getXWindowId("Actual Data");
			
			waitForExtJSAjaxComplete(25);
			
			//setDateRange for actual data
			
			setDateRange("01-01-2010 to 31-12-2011");
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "31-12-2011|0", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "30-12-2011|0", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");

			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "29-12-2011|0", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "28-12-2011|0", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "27-12-2011|0", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "26-12-2011|0", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "25-12-2011|0", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "24-12-2011|0", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "23-12-2011|0", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			} else {
			
			//select created channel
			checkRowInGriByTextValueAndClick(driver, channelReference2);
			
			//open Actual Data dialog
			clickButton("Actual Data", channelDialogId);
			
			waitForExtJSAjaxComplete(100);
			
			//get Actual dialog id
			actualDataDialogId = getXWindowId("Actual Data");
			
			waitForExtJSAjaxComplete(25);
			
			//setDateRange for actual data
			
			setDateRange("01-01-2010 to 31-12-2011");
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "31-12-2011|0", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "30-12-2011|0", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");

			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "29-12-2011|0", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "28-12-2011|0", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "27-12-2011|0", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "26-12-2011|0", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "25-12-2011|0", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "24-12-2011|0", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			softAssert.assertEquals(true, Grid.isRowInGridPresent(driver, "23-12-2011|0", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "Row was calculated ");
			
			waitForExtJSAjaxComplete(25);
			}
			
			//Close actual data dialog
			closeActualData();
			
			waitForExtJSAjaxComplete(25);
			
			
			//select created channel
			checkRowInGriByTextValueAndClick(driver, channelReference1);
			
			waitForExtJSAjaxComplete(25);
			
			//click Delete button for channel
			clickDeleteButton(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			waitForExtJSAjaxComplete(50);
			
			//verify Error Mess
			softAssert.assertTrue(getWarningDialogText().contains("You cannot delete the Channel that has Measurements."), "You cannot delete the Channel that has Measurements.");
			
			clickOnDialogButton("OK");
			
			waitForExtJSAjaxComplete(25);
			
			//select created channel
			checkRowInGriByTextValueAndClick(driver, channelReference2);
			
			waitForExtJSAjaxComplete(25);
			
			//click Delete button for channel
			clickDeleteButton(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			waitForExtJSAjaxComplete(50);
			
			softAssert.assertTrue(getWarningDialogText().contains("You cannot delete the Channel that has Measurements."), "You cannot delete the Channel that has Measurements.");
			
			clickOnDialogButton("OK");
			
			waitForExtJSAjaxComplete(25);
			
			//Close weather station dialog
			closeWeatherstation();
			
			//assert
			softAssert.assertAll();
			
			Reporter.log("testWeatherstationChannelCalculationsFromMDT was succesfully done.", true);

		}
	
	@Test(enabled =true)

	public void testWeatherStationsCDDReferenceDataCheck() throws IOException  {
		
		 Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Weather Stations CDD Reference Data " + " </span><br>",
					true);

			Reporter.log("User Weather Stations CDD Reference Data: C19913 testrail "  + " <br>",
					true);
		
			
			String gaugeCode = "1preWeatherStation";
			String channelReference = "CDD 2.5";
			String channelParameter = "Cooling Degree-days";
			String readingInterval = "Day";
			String baseTemperature = "2.5";
			
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("WeatherStationsHDDCDDReferenceDataCheck");

			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(20);
			
			//expandMetering
			expandMetering();
			
			//open Weather Stations
			openAnalysisEntity("Weather Stations");

			waitForExtJSAjaxComplete(20);
			
			//select predefined weather station
			Grid.checkRowInGriByTextValue(driver, gaugeCode);
			
			//click Edit Button for weather station
			clickEditButton(WEATHERSTATIONS_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			//Select channels tab
			changeTab("channels");
		
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			//get Weather station dialog id
			weatherstationDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			//Click add button for new Channel			
			clickButton("Add", weatherstationDialogId);
			
			waitForExtJSAjaxComplete(20);
			
			//set channel parameter
			setChannelParameter(channelParameter);
			
			waitForExtJSAjaxComplete(25);
			
			//set Base Temperature
			setBaseTemperature(baseTemperature);
			
			waitForExtJSAjaxComplete(25);
			
			//Verify reference
			softAssert.assertLike(getReference(),channelReference, channelReference+" reference before edit ");
			
			//Verify channel parameter
			softAssert.assertEquals(getChannelParameter(),channelParameter, channelParameter+ " channelParameter is ");
			
			//Verify channel Reading Interval
			softAssert.assertEquals(getReadingInterval(),readingInterval, readingInterval+ " Reading Interval is ");
			
			//Save and Close Channel
			save();
			
			waitForExtJSAjaxComplete(25);
			
			close(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS);
			
			waitForExtJSAjaxComplete(25);
			
			//select created channel
			checkRowInGriByTextValueAndClick(driver, channelReference);
			
			waitForExtJSAjaxComplete(25);
			
			//get channel dialog id
			channelDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			//open Actual Data dialog
			clickButton("Reference Data", channelDialogId);
			
			//123
			
			waitForExtJSAjaxComplete(100);
			
			//get Actual dialog id
			actualDataDialogId = getXWindowId("Reference Data");
			
			try {
			
			waitForExtJSAjaxComplete(100);
			
			setReferenceData("Jan","1");
			
			setReferenceData("Feb","2");

			setReferenceData("Mar","3");
			
			setReferenceData("Apr","4");
			
			setReferenceData("May","5");
			
			setReferenceData("Jun","6");
			
			setReferenceData("Jul","7");
			
			setReferenceData("Aug","8");
			
			setReferenceData("Sep","9");
			
			setReferenceData("Oct","10");
			
			setReferenceData("Nov","11");
			
			setReferenceData("Dec","12");
			
//			setReferenceData(driver, "@id", actualDataDialogId, "0", "1");
//			
//			waitForExtJSAjaxComplete(100);
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "1", "2");
//			
//			waitForExtJSAjaxComplete(100);
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "2", "3");
//			
//			waitForExtJSAjaxComplete(100);
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "3", "4");
//			
//			waitForExtJSAjaxComplete(100);
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "4", "5");
//			
//			waitForExtJSAjaxComplete(100);
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "5", "6");
//			
//			waitForExtJSAjaxComplete(100);
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "6", "7");
//			
//			waitForExtJSAjaxComplete(100);
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "7", "8");
//			
//			waitForExtJSAjaxComplete(100);
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "8", "9");
//			
//			waitForExtJSAjaxComplete(100);
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "9", "10");
//			
//			waitForExtJSAjaxComplete(100);
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "10", "11");
//			
//			waitForExtJSAjaxComplete(100);
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "11", "12");
//			
//			waitForExtJSAjaxComplete(100);
//			
			//just to lost focus and save last entered value
//			clickCellElementByColumnNumber(driver, "@id", actualDataDialogId, "0");
			
//			waitForExtJSAjaxComplete(100);
			
			//Close actual data dialog
			saveAndCloseReferenceData();
			
			waitForExtJSAjaxComplete(100);
			
			}
			
			catch (Exception e) {
				
				Reporter.log("Reference data failed on setting values.", true);
				closeReferenceData();
				clickOnDialogButton("Yes");
				softAssert.assertAll();
				
			}
			
			//select created channel
			checkRowInGriByTextValueAndClick(driver, channelReference);
			
			waitForExtJSAjaxComplete(100);
			
			//open Actual Data dialog
			clickButton("Reference Data", channelDialogId);
			
			waitForExtJSAjaxComplete(100);
			
			//get Actual dialog id
			actualDataDialogId = getXWindowId("Reference Data");
			
			waitForExtJSAjaxComplete(100);
			
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "0"), "1", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "1"), "2", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "2"), "3", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "3"), "4", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "4"), "5", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "5"), "6", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "6"), "7", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "7"), "8", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "8"), "9", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "9"), "10", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "10"), "11", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "11"), "12", "test");
			
			waitForExtJSAjaxComplete(100);
			
			//Close actual data dialog
			closeReferenceData();
			
			waitForExtJSAjaxComplete(100);
			
			//select created channel
			checkRowInGriByTextValueAndClick(driver, channelReference);
			
			//click Delete button for channel
			clickDeleteButton(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			//Close weather station dialog
			closeWeatherstation();
			
			//assert
			softAssert.assertAll();
			
			Reporter.log("WeatherstationActualDataCheck was succesfully done.", true);
		
		}
	
	@Test(enabled = true)
	public void testWeatherStationsHDDReferenceDataCheck() throws IOException  {

		 Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Weather Stations HDD Reference Data " + " </span><br>",
					true);

			Reporter.log("User Weather Stations HDD Reference Data: C19922 testrail "  + " <br>",
					true);
		
			
			String gaugeCode = "1preWeatherStation";
			String channelReference = "HDD 3.0";
			String channelParameter = "Heating Degree-days";
			String readingInterval = "Day";
			String baseTemperature = "3.0";
			
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("WeatherStationsHDDCDDReferenceDataCheck");

			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(20);
			
			//expandMetering
			expandMetering();
			
			//open Weather Stations
			openAnalysisEntity("Weather Stations");

			waitForExtJSAjaxComplete(20);
			
			//select predefined weather station
			Grid.checkRowInGriByTextValue(driver, gaugeCode);
			
			//click Edit Button for weather station
			clickEditButton(WEATHERSTATIONS_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			//Select channels tab
			changeTab("channels");
		
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			//get Weather station dialog id
			weatherstationDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			//Click add button for new Channel			
			clickButton("Add", weatherstationDialogId);
			
			waitForExtJSAjaxComplete(20);
			
			//set channel parameter
			setChannelParameter(channelParameter);
			
			waitForExtJSAjaxComplete(25);
			
			//set Base Temperature
			setBaseTemperature(baseTemperature);
			
			waitForExtJSAjaxComplete(25);
			
			//Verify reference
			softAssert.assertLike(getReference(),channelReference, channelReference+" reference before edit ");
			
			//Verify channel parameter
			softAssert.assertEquals(getChannelParameter(),channelParameter, channelParameter+ " channelParameter is ");
			
			//Verify channel Reading Interval
			softAssert.assertEquals(getReadingInterval(),readingInterval, readingInterval+ " Reading Interval is ");
			
			//Save and Close Channel
			save();
			
			waitForExtJSAjaxComplete(25);
			
			close(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS);
			
			waitForExtJSAjaxComplete(25);
			
			//select created channel
			checkRowInGriByTextValueAndClick(driver, channelReference);
			
			waitForExtJSAjaxComplete(25);
			
			//get channel dialog id
			channelDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			//open Actual Data dialog
			clickButton("Reference Data", channelDialogId);
			
			waitForExtJSAjaxComplete(100);
			
			//get Actual dialog id
			actualDataDialogId = getXWindowId("Reference Data");
			
			waitForExtJSAjaxComplete(25);
			
			setReferenceData("Jan","1");
			
			setReferenceData("Feb","2");

			setReferenceData("Mar","3");
			
			setReferenceData("Apr","4");
			
			setReferenceData("May","5");
			
			setReferenceData("Jun","6");
			
			setReferenceData("Jul","7");
			
			setReferenceData("Aug","8");
			
			setReferenceData("Sep","9");
			
			setReferenceData("Oct","10");
			
			setReferenceData("Nov","11");
			
			setReferenceData("Dec","12");
			
//			setReferenceData(driver, "@id", actualDataDialogId, "0", "1");
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "1", "2");
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "2", "3");
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "3", "4");
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "4", "5");
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "5", "6");
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "6", "7");
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "7", "8");
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "8", "9");
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "9", "10");
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "10", "11");
//			
//			setReferenceData(driver, "@id", actualDataDialogId, "11", "12");
//			
//			//just to lost focus and save last entered value
//			clickCellElementByColumnNumber(driver, "@id", actualDataDialogId, "0");
			
			//Close actual data dialog
			saveAndCloseReferenceData();
			
			waitForExtJSAjaxComplete(25);
			
			//select created channel
			checkRowInGriByTextValueAndClick(driver, channelReference);
			
			waitForExtJSAjaxComplete(25);
			
			//open Actual Data dialog
			clickButton("Reference Data", channelDialogId);
			
			waitForExtJSAjaxComplete(100);
			
			//get Actual dialog id
			actualDataDialogId = getXWindowId("Reference Data");
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "0"), "1", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "1"), "2", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "2"), "3", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "3"), "4", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "4"), "5", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "5"), "6", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "6"), "7", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "7"), "8", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "8"), "9", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "9"), "10", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "10"), "11", "test");
			softAssert.assertEquals(getReferenceData(driver, "@id", actualDataDialogId, "11"), "12", "test");
			
			waitForExtJSAjaxComplete(25);
			
			//Close actual data dialog
			closeReferenceData();
			
			waitForExtJSAjaxComplete(25);
			
			//select created channel
			checkRowInGriByTextValueAndClick(driver, channelReference);
			
			//click Delete button for channel
			clickDeleteButton(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			//Close weather station dialog
			closeWeatherstation();
			
			//assert
			softAssert.assertAll();
			
			Reporter.log("WeatherstationActualDataCheck was succesfully done.", true);

		}

	/**
	 * Test Case ID: C60572
	 * Author : SSU
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testLimitationManualInputChannelMeasurements() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Limitation for max allowed digits and decimals for Manual Input Daily Channels Measurements (9999.999): C60572" + " </span><br>", 
				true);
		
		String gaugeCode = "1preWeatherStationLinkChannel";
		String channelReference = "SH";
		String channelParameter = "Solar Hours";
		String readingInterval = "Day";
		String uomValue = "day";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testLimitationManualInputChannelMeasurements");

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		//expandMetering
		expandMetering();
		
		//open Weather Stations
		openAnalysisEntity("Weather Stations");

		waitForExtJSAjaxComplete(20);
		
		//select predefined weather station
		Grid.checkRowInGriByTextValue(driver, gaugeCode);
		
		//click Edit Button for weather station
		clickEditButton(WEATHERSTATIONS_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//Select channels tab
		changeTab("channels");
	
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//get Weather station dialog id
		weatherstationDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
		
		//Click add button for new Channel			
		clickButton("Add", weatherstationDialogId);
			
		waitForExtJSAjaxComplete(20);
			
		//set channel parameter
		setChannelParameter(channelParameter);
		
		waitForExtJSAjaxComplete(25);
			
		//Verify reference
		softAssert.assertLike(getReference(),channelReference, channelReference+" reference before edit ");
		
		setReadingInterval(readingInterval);
		
		waitForExtJSAjaxComplete(5);
		
		setUOM(uomValue);
		
		waitForExtJSAjaxComplete(5);
		
		//Save and Close Channel
		saveClose();
			
		waitForExtJSAjaxComplete(25);
		
		//select created channel
		checkRowInGriByTextValueAndClick(driver, channelReference);
		
		waitForExtJSAjaxComplete(25);
		
		//get channel dialog id
		channelDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
		
		//open Actual Data dialog
		clickButton("Actual Data", channelDialogId);
		
		waitForExtJSAjaxComplete(100);
		
		//get Actual dialog id
		actualDataDialogId = getXWindowId("Actual Data");
		
		waitForExtJSAjaxComplete(25);
		
		//Forming Last month Begining and End Date
		GaugesMeasurementsPageObject gaugesMeasurementsPO = new GaugesMeasurementsPageObject();
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("wsMeasurementsPopup_dateRangePickerInterval", "Last Month");
		
		waitForExtJSAjaxComplete(10);
		
		//Click Import data button
		clickButton("Manual Input", actualDataDialogId);
		
		waitForExtJSAjaxComplete(10);
		
		selectPreviousMonth();
		
		waitForExtJSAjaxComplete(10);
		
		//Set Max Measurement value in Window Calendar
		setMeasurementValue("@class", "x-window energy properties", "2", "Mon", "9999.999");
		
		waitForExtJSAjaxComplete(10);
		
		save("x-window energy properties");
		
		waitForExtJSAjaxComplete(10);
		
		//Set Negative Measurement value in Window Calendar
		setMeasurementValue("@class", "x-window energy properties",  "2",  "Wed", "-9999.999");
		
		waitForExtJSAjaxComplete(10);
		
		save("x-window energy properties");
		
		waitForExtJSAjaxComplete(10);
		
		//Try Setting Measurement value greater than Maximum value in Window Calendar
		setMeasurementValue("@class", "x-window energy properties",  "2",  "Sun", "99999");
		 
		softAssert.assertTrue(VerifyButtonDisabled(), "Measurement that is higher than 9999.999 couldnot be saved.");
				
		waitForExtJSAjaxComplete(5);
		
		close("x-window energy properties");
		
		waitForExtJSAjaxComplete(10);
	
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "9999.999", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "9999.999 Row is available in Actual Data Grid");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "-9999.999", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "-9999.999 Row is available in Actual Data Grid");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("Limitation for max allowed digits and decimals for Manual Input Daily Channels Measurements (9999.999) is successfully verified", true);
	}
	
	/**
	 * Test Case ID: C60573
	 * Author : SSU
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testLimitationManualInputMonthlyChannelMeasurements() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Limitation for max allowed digits and decimals for Manual Input Monthly Channels Measurements (9999.999): C60573" + " </span><br>", 
				true);
		
		String gaugeCode = "1preWeatherStationNotCalc";
		String channelReference = "SH";
		String channelParameter = "Solar Hours";
		String readingInterval = "Month";
		String uomValue = "hour";
		
		String reference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String uom = "°F";
		String status = "Active";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testLimitationManualInputMonthlyChannelMeasurements");

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		//expandMetering
		expandMetering();
		
		//open Weather Stations
		openAnalysisEntity("Weather Stations");

		MeteringWeatherStationsPageObject mWSPO = new MeteringWeatherStationsPageObject();
		
		waitForExtJSAjaxComplete(20);
		
		mWSPO.clickAddButton(WEATHERSTATIONS_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
	
		waitForExtJSAjaxComplete(25);
		
		mWSPO.setCode(code);
		
		mWSPO.setReference(reference);
		
		mWSPO.setUOM(uom);
		
		waitForExtJSAjaxComplete(25);
		
		mWSPO.setStatus(status);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		mWSPO.save();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//select predefined weather station
		/*Grid.checkRowInGriByTextValue(driver, gaugeCode);
		
		//click Edit Button for weather station
		clickEditButton(WEATHERSTATIONS_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);*/
		
		waitForExtJSAjaxComplete(20);
		
		//Select channels tab
		changeTab("channels");
	
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//get Weather station dialog id
		weatherstationDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
		
		//Click add button for new Channel			
		clickButton("Add", weatherstationDialogId);
			
		waitForExtJSAjaxComplete(20);
			
		//set channel parameter
		setChannelParameter(channelParameter);
		
		waitForExtJSAjaxComplete(25);
			
		//Verify reference
		softAssert.assertLike(getReference(),channelReference, channelReference+" reference before edit ");
		
		setReadingInterval(readingInterval);
		
		waitForExtJSAjaxComplete(5);
		
		setUOM(uomValue);
		
		waitForExtJSAjaxComplete(5);
		
		//Save and Close Channel
		saveClose();
			
		waitForExtJSAjaxComplete(25);
		
		//select created channel
		checkRowInGriByTextValueAndClick(driver, channelReference);
		
		waitForExtJSAjaxComplete(25);
		
		//get channel dialog id
		channelDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
		
		//open Actual Data dialog
		clickButton("Actual Data", channelDialogId);
		
		waitForExtJSAjaxComplete(100);
		
		//get Actual dialog id
		actualDataDialogId = getXWindowId("Actual Data");
		
		waitForExtJSAjaxComplete(25);
		
		//Forming Last month Begining and End Date
		GaugesMeasurementsPageObject gaugesMeasurementsPO = new GaugesMeasurementsPageObject();
		
		gaugesMeasurementsPO.setComboValueDropDownSelector("wsMeasurementsPopup_dateRangePickerInterval", "Last Year");
		
		waitForExtJSAjaxComplete(10);
		
		//Click Import data button
		clickButton("Manual Input", actualDataDialogId);
		
		waitForExtJSAjaxComplete(10);
		
		clickPreviousYearButtonInChannel();
		
		waitForExtJSAjaxComplete(10);
		
		//Set Max Measurement value in Window Calendar
		setMeasurementValue("@class", "x-window energy properties",  "1",  "Jan", "9999.999");
		
		waitForExtJSAjaxComplete(10);
		
		save("x-window energy properties");
		
		waitForExtJSAjaxComplete(10);

		//Set Negative Measurement value in Window Calendar
		setMeasurementValue("@class", "x-window energy properties",  "1", "Feb", "-9999.999");
		
		waitForExtJSAjaxComplete(10);
		
		save("x-window energy properties");
		
		waitForExtJSAjaxComplete(10);
		
		setMeasurementValue("@class", "x-window energy properties",  "1", "Mar", "99999.999");
		
		softAssert.assertTrue(VerifyButtonDisabled(), "Measurement that is higher than 9999.999 couldnot be saved.");
		
		waitForExtJSAjaxComplete(5);
		
		close("x-window energy properties");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "9999.999", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "9999.999 Row is available in Actual Data Grid");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "-9999.999", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS), "-9999.999 Row is available in Actual Data Grid");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("Limitation for max allowed digits and decimals for Manual Input Monthly Channels Measurements (9999.999) is successfully verified", true);
	}
}