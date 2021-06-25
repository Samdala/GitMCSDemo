package test.energy.weatherstations;

import java.io.IOException;



import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;


public class MeteringWeatherstationChanelCRUDTestSuite extends
		MeteringWeatherstationChanelsPageObject {



	 @Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testWeatherstationChanelCreateEdit() throws IOException  {

		 Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Add/Edit Channel Weatherstation Measurement " + " </span><br>",
					true);

			Reporter.log("User adds/edits Channel Weatherstation Measurement: C19911 testrail "  + " <br>",
					true);
		
			String gaugeCode = "1preWeaterStationCRUD";
			String channelReference = "CDD 5.0";
			String channelParameter = "Cooling Degree-days";
			String readingInterval = "Day";
			//String entryMethod = "Calculated";
			String baseTemperature = "5.0";
			
			
			String channelReferenceEd = "test auto ed" + getCurrentDate().substring(getCurrentDate().length()-6);;
			String baseTemperatureEd = "1.5";
			
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("AddWeatherstationChannelMeasurement");

			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			expandMetering();
			
			openAnalysisEntity("Weather Stations");

			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			Grid.checkRowInGriByTextValue(driver, gaugeCode);
			
			clickEditButton(WEATHERSTATIONS_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			changeTab("channels");
		
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			channelDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			//checkRowInGriByTextValueAndClick(driver, channelReference, gaugesDialogId);
			
			clickButton("Add", channelDialogId);
			
			waitForExtJSAjaxComplete(20);
			
			//setCode(channelCode);
			
			setChannelParameter(channelParameter);
			
			waitForExtJSAjaxComplete(25);
			
			setBaseTemperature(baseTemperature);
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertLike(getReference(),channelReference, channelReference+" reference before edit is wrong");
			
			softAssert.assertEquals(getChannelParameter(),channelParameter, channelParameter+ " channelParameteris wrong");
			
			softAssert.assertEquals(getReadingInterval(),readingInterval, readingInterval+ " Reading Interval is wrong");
			
			save();
			
			waitForExtJSAjaxComplete(25);

			waitForExtJSAjaxComplete(25);
			
			close();
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			checkRowInGriByTextValueAndClick(driver, channelReference);
			
			waitForExtJSAjaxComplete(25);
			
			channelDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			clickButton("Edit", channelDialogId);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertLike(getReference(),channelReference, channelReference+" reference before edit is wrong");
			
			softAssert.assertEquals(getChannelParameter(),channelParameter, channelParameter+ " channelParameter before edit is wrong");
			
			softAssert.assertEquals(getReadingInterval(),readingInterval, readingInterval+ " Reading Interval before edit is wrong");
			
			setBaseTemperature(baseTemperatureEd);
			
			waitForExtJSAjaxComplete(25);
			
			setReference(channelReferenceEd);
			
			save();
			
			waitForExtJSAjaxComplete(25);
			
			close();

			checkRowInGriByTextValueAndClick(driver, channelReferenceEd);
			
			channelDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			clickButton("Edit", channelDialogId);
			
			waitForExtJSAjaxComplete(20);

			softAssert.assertLike(getReference(),channelReferenceEd, channelReferenceEd+" reference before edit is wrong");
			
			softAssert.assertEquals(getChannelParameter(),channelParameter, channelParameter+ " channelParameter before edit is wrong");
			
			softAssert.assertEquals(getReadingInterval(),readingInterval, readingInterval+ " Reading Interval before edit is wrong");
			
			softAssert.assertAll();

			close();
			
			Reporter.log("Channel" + channelReferenceEd  + " was successfully edited", true);

	}
	 
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testWeatherstationChannelDelete() throws IllegalStateException, IOException {

			
			Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Delete Channel Weatherstation Measurement" + " </span><br>",
					true);

			Reporter.log("User deletes Weatherstation Channel: C19919 testrail "  + " <br>",
					true);
			
			String gaugeCode = "1preWeaterStationCRUD";
			String channelReference = "HDD 1.5";
			String channelParameter = "Heating Degree-days";
			String readingInterval = "Day";
			//String entryMethod = "Calculated";
			String baseTemperature = "1.5";
			
			
			//String channelReferenceEd = "test auto ed" + getCurrentDate().substring(getCurrentDate().length()-6);;
			//String baseTemperatureEd = "2.0";
			
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("AddWeatherstationChannelMeasurement");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);

			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			expandMetering();
			
			openAnalysisEntity("Weather Stations");

			waitForExtJSAjaxComplete(20);
			
			Grid.checkRowInGriByTextValue(driver, gaugeCode);
			
			clickEditButton(WEATHERSTATIONS_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			changeTab("channels");
		
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			channelDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			//checkRowInGriByTextValueAndClick(driver, channelReference, gaugesDialogId);
			
			clickButton("Add", channelDialogId);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			//setCode(channelCode);
			
			setChannelParameter(channelParameter);
			
			waitForExtJSAjaxComplete(25);
			
			setBaseTemperature(baseTemperature);
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertLike(getReference(),channelReference, channelReference+" reference before edit is wrong");
			
			softAssert.assertEquals(getChannelParameter(),channelParameter, channelParameter+ " channelParameteris wrong");
			
			softAssert.assertEquals(getReadingInterval(),readingInterval, readingInterval+ " Reading Interval is wrong");
			
			save();
			
			waitForExtJSAjaxComplete(25);
			
			close();
			
			waitForExtJSAjaxComplete(25);
			
			checkRowInGriByTextValueAndClick(driver, channelReference);
			
			waitForExtJSAjaxComplete(25);
			
			clickDeleteButton(WEATHERSTATIONS_CHANELS_GRID_CLASS);
					
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(20);
			
			Assert.assertTrue(Grid.isRowInGridAbsent(driver, channelReference), channelReference+" reference after delete is present");
			
			Reporter.log("Channel was successfully deleted", true);
			
		}

	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testWeatherstationChanelWithUsedParameter() throws IOException  {

		 Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: testWeatherstationChanelWithUsedParameter C39650" + " </span><br>",
					true);

			Reporter.log("User adds Channel WithUsedParameter C39650"  + " <br>",
					true);
		
			String weatherCode = "1preWeatherStationLink";
			String channelReference = "CDD 5.0";
			String channelParameter = "Mean Daily Temperature";
			
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("testWeatherstationChanelWithUsedParameter");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);

			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			expandMetering();
			
			openAnalysisEntity("Weather Stations");

			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			Grid.checkRowInGriByTextValue(driver, weatherCode);
			
			clickEditButton(WEATHERSTATIONS_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			changeTab("channels");
		
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			channelDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			clickButton("Add", channelDialogId);
			
			waitForExtJSAjaxComplete(20);
			
			setChannelParameter(channelParameter);
			
			waitForExtJSAjaxComplete(25);
			
			saveClose();
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS),"Message about invalid form is present"); 
			
			softAssert.assertAll();
			
			Reporter.log("Channel" + channelReference  + " was not created", true);

	}	
	
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void CalculationMethodCreationWithWeatherChannelsParameter() throws IOException  {

		 Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Add Channel WithUsedParameter C39650" + " </span><br>",
					true);

			Reporter.log("User adds Channel Weatherstation WithUsedParameter C39648, C39649"  + " <br>",
					true);
			
			String reference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
			String code = reference;
			String entryMethod = "Equivalent Temperature";
			
			String channelReference = "Equivalent Temperature";
			String channelParameter1 = "Heating Degree-days";
			String channelParameter2 = "Cooling Degree-days";
			String baseTemperature = "0.0 °C";
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("CalculationMethodCreationWithWeatherChannelsParameter");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);

			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			expandMetering();
			
			openAnalysisEntity("Weather Stations");

			waitForExtJSAjaxComplete(20);
			
			clickAddButton(WEATHERSTATIONS_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
		
			setWSCode(code);
			
			setWSReference(reference);
			
			waitForExtJSAjaxComplete(25);
			
			setEntryMethod(entryMethod);
			
			waitForExtJSAjaxComplete(25);
			
			saveWS();
			
			waitForExtJSAjaxComplete(25);
			
			closeWS();
			
			waitForExtJSAjaxComplete(25);
			
			Grid.checkRowInGriByTextValue(driver, reference);
			
			clickEditButton(WEATHERSTATIONS_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			changeTab("channels");
		
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			channelDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			clickButton("Add", channelDialogId);
			
			waitForExtJSAjaxComplete(20);
			
			setChannelParameter(channelParameter1);
			
			waitForExtJSAjaxComplete(25);
			
			setBaseTemperature(baseTemperature);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			save();
			
			waitForExtJSAjaxComplete(25);
			
			close();
			
			waitForExtJSAjaxComplete(25);
			
			checkRowInGriByTextValueAndClick(driver, "HDD");
			
			waitForExtJSAjaxComplete(25);
			
			clickButton("Add", channelDialogId);
			
			waitForExtJSAjaxComplete(20);
			
			setChannelParameter(channelParameter2);
			
			waitForExtJSAjaxComplete(25);
			
			setBaseTemperature(baseTemperature);
			
			waitForExtJSAjaxComplete(25);
			
			save();
			
			waitForExtJSAjaxComplete(25);
			
			close();
			
			waitForExtJSAjaxComplete(25);
			
			checkRowInGriByTextValueAndClick(driver, "CDD");
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertAll();
			
			Reporter.log("Channels" + channelReference  + " was created", true);

	}
}