package test.energy.gauges;

import java.io.IOException;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class MeteringGaugesChanelCRUDTestSuite extends
		MeteringGaugesChanelsPageObject {



	 @Test(enabled = true)
	public void testGaugesChanelCreateEdit() throws IOException  {

		 Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Add/Edit Channel Gauge Measurement " + " </span><br>",
					true);

			Reporter.log("User adds/edits Channel Gauge Measurement: c11602 testrail "  + " <br>",
					true);
		
			String gaugeCode = "preGaugeMeasure";
//			String channelCode = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);;
			String channelReference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);;
			String channelParameter = "preGaugeParameter1";
			String channelUOM = "dm²";
			
			//String channelUOM = "square decimeter";
			String calibrationDate = "05-10-2014";
			
//			String channelCodeEd = "test auto ed " + getCurrentDate().substring(getCurrentDate().length()-6);;
			String channelReferenceEd = "test auto ed" + getCurrentDate().substring(getCurrentDate().length()-6);;
			String channelParameterEd = "preGaugeParameter2";
			//String channelUOMEd = "square centimeter";
			String channelUOMEd = "cm²";
			String calibrationDateEd = "15-10-2014";
		
			//reportingInterval ddl
			//calculationMethod ddl
			//entryType - disable 
			//entryMethod ddl
			//class- disable - setDef Location Gauge
			
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("AddGaugeMeasurement");

			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			expandMetering();
			
			waitForExtJSAjaxComplete(25);
			
			openAnalysisEntity("Gauges");

			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			Grid.checkRowInGriByTextValue(driver, gaugeCode);
			
			clickEditButton(GAUGES_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			changeTab("channels");
		
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			gaugesDialogId = getXWindowIdByClass("slnmGaugeId");
			
			//checkRowInGriByTextValueAndClick(driver, channelReference, gaugesDialogId);
			
			boolean status = Grid.isRowInGridPresent(driver, channelParameter, "@id", gaugesDialogId);
			
			if(status){
				Grid.checkRowInGriByTextValueAndClick(driver, channelParameter);
				
				clickButton("Delete", gaugesDialogId);
				
				clickOnDialogButton("Yes");
				
				waitForExtJSAjaxComplete(10);
			} 
			
			clickButton("Add", gaugesDialogId);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			//setCode(channelCode);
			
			setReference(channelReference);
			
			setCalibrationDate(calibrationDate);
			
			waitForExtJSAjaxComplete(25);
			
			setChannelParameter(channelParameter);
			
			waitForExtJSAjaxComplete(25);
			
			setUnitOfMeasure(channelUOM);
			
			waitForExtJSAjaxComplete(25);
			
			saveClose();
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			checkRowInGriByTextValueAndClick(driver, channelReference);
			
			waitForExtJSAjaxComplete(25);
			
			gaugesDialogId = getXWindowIdByClass("slnmGaugeId");
			
			clickButton("Edit", gaugesDialogId);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertEquals(getReference(),channelReference, channelReference+" reference before edit is wrong");
			
			//softAssert.assertEquals(getCode(),channelCode, channelCode+ " channelCode before edit is wrong");
			
			softAssert.assertEquals(getCalibrationDate(),calibrationDate, calibrationDate+ " calibrationDate before edit is wrong");
			
			softAssert.assertEquals(getChannelParameter(),channelParameter, channelParameter+ " channelParameter before edit is wrong");
			
			softAssert.assertEquals(getUnitOfMeasure(),channelUOM, channelUOM+ " channelUOM before edit is wrong");
			
			close();
			
			waitForExtJSAjaxComplete(20);
			
			status = Grid.isRowInGridPresent(driver, channelParameterEd, "@id", gaugesDialogId);
			
			if(status){
				Grid.checkRowInGriByTextValueAndClick(driver, channelParameterEd);
				
				clickButton("Delete", gaugesDialogId);
				
				clickOnDialogButton("Yes");
				
				waitForExtJSAjaxComplete(10);
			} 
			
			waitForExtJSAjaxComplete(25);
			
			checkRowInGriByTextValueAndClick(driver, channelReference);
			
			waitForExtJSAjaxComplete(25);
			
			gaugesDialogId = getXWindowIdByClass("slnmGaugeId");
			
			clickButton("Edit", gaugesDialogId);
			
			waitForExtJSAjaxComplete(20);
			
			//setCode(channelCodeEd);
			
			setReference(channelReferenceEd);
			
			setCalibrationDate(calibrationDateEd);
			
			waitForExtJSAjaxComplete(25);
			
			setChannelParameter(channelParameterEd);
			
			waitForExtJSAjaxComplete(25);
			
			setUnitOfMeasure(channelUOMEd);
			
			waitForExtJSAjaxComplete(25);
			
			saveClose();
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);

			checkRowInGriByTextValueAndClick(driver, channelReferenceEd);
			
			gaugesDialogId = getXWindowIdByClass("slnmGaugeId");
			
			clickButton("Edit", gaugesDialogId);
			
			waitForExtJSAjaxComplete(20);

			softAssert.assertEquals(getReference(),channelReferenceEd, channelReferenceEd+" reference before edit is wrong");
			
			//softAssert.assertEquals(getCode(),channelCodeEd, channelCodeEd+ " code before edit is wrong");
			
			softAssert.assertEquals(getCalibrationDate(),calibrationDateEd, calibrationDateEd+ " calibrationDate before edit is wrong");
			
			softAssert.assertEquals(getChannelParameter(),channelParameterEd, channelParameterEd+ " channelParameter before edit is wrong");
			
			softAssert.assertEquals(getUnitOfMeasure(),channelUOMEd, channelUOMEd+ " channelUOM before edit is wrong");
			
			softAssert.assertAll();

			close();
			
			Reporter.log("Channel" + channelReferenceEd  + " was succesfully edited", true);

		}
	 @Test(enabled = true)
	public void testChannelDelete() throws IllegalStateException, IOException {

			
			Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Delete Gauges " + " </span><br>",
					true);

			Reporter.log("User deletes Channel: c11603 testrail "  + " <br>",
					true);
			
			String gaugeCode = "preGaugeMeasure";
			//String channelCode = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);;
			String channelReference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);;
			String channelParameter = "preGaugeParameter3";
			String channelUOM = "dm²";
			String calibrationDate = "05-10-2014";
		
			//reportingInterval ddl
			//calculationMethod ddl
			//entryType - disable 
			//entryMethod ddl
			//class- disable - setDef Location Gauge
			
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("AddGaugeMeasurement");

			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			expandMetering();
			
			waitForExtJSAjaxComplete(25);
			
			openAnalysisEntity("Gauges");

			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			Grid.checkRowInGriByTextValue(driver, gaugeCode);
			
			clickEditButton(GAUGES_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(20);
			
			changeTab("channels");
		
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			gaugesDialogId = getXWindowIdByClass("slnmGaugeId");
			
			//checkRowInGriByTextValueAndClick(driver, channelReference, gaugesDialogId);
			
			clickButton("Add", gaugesDialogId);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			//setCode(channelCode);
			
			setReference(channelReference);
			
			setCalibrationDate(calibrationDate);
			
			waitForExtJSAjaxComplete(25);
			
			setChannelParameter(channelParameter);
			
			waitForExtJSAjaxComplete(25);
			
			setUnitOfMeasure(channelUOM);
			
			waitForExtJSAjaxComplete(25);
			
			saveClose();
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			checkRowInGriByTextValueAndClick(driver, channelReference);
			
			clickDeleteButton(GAUGES_CHANELS_GRID_CLASS);
					
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(20);
			
			Assert.assertTrue(Grid.isRowInGridAbsent(driver, channelReference), channelReference+" reference after delete is present");
			
			Reporter.log("Channel was succesfully deleted", true);
			
		}

}