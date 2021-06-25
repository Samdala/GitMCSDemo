package test.energy.meters;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.energy.gauges.MeteringGaugesPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class MetersChannelsCRUDTestSuite extends MetersPageObject{
	
	 @Test(enabled=true)
		public void testMeterChannelCreateEdit() throws IOException  {

			Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create and Edit Meter Channels: C19904" + " </span><br>",
				true);

		Reporter.log("User creates and edits Meter Channel"  + " <br>",
				true);
		
		//Field values for Channel creation
		String chnlReference = "test auto chnl " + getCurrentDate().substring(getCurrentDate().length()-6);
		String chnlMultiplier = "1";
		String chnlUOM = "J";
		String chnlRepInterval = "Hour";
		String chnlEntryType = "Index Value";
		String chnlEntryMethod = "Automatic";
		String chnlNumDigits = "5";
		//String chnlNumDecimals = "3";
		String chnlCalDate = "01-11-2009";
		
		//Field values for Channel editing
		String chnlReferenceEdited = "test auto chnl ed" + getCurrentDate().substring(getCurrentDate().length()-6);
		String chnlMultiplierEdited = "3";
		String chnlUOMEdited = "Nm";
		String chnlRepIntervalEdited = "Day";
		String chnlEntryTypeEdited = "Usage Value";
		String chnlEntryMethodEdited = "Manual";
		String chnlCalDateEdited = "20-11-2012";	
		
		SoftAssert softAssert = new SoftAssert(); 
		
		softAssert.setMethodName("ChannelCreateEdit");

		//Navigate to Meters Overview
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		expandMetering();
		
		waitForExtJSAjaxComplete(25);
		
		openMeteringEntity("Meters");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		//Open pre-configured Meter and go to Channels tab
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", METER_GRID_CLASS, "1preMeter");
		
		clickButton("Edit", METER_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickChannelsTab();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//Create Channel
		
		clickButton("Add", DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		setChnlReference(chnlReference);
		
		setChnlMultiplier(chnlMultiplier);
		
		setChnlNumDigits(chnlNumDigits);
		
		setChnlCalDate(chnlCalDate);
		
		//setChnlNumDecimals(chnlNumDecimals);
		
		setChnlRepInterval(chnlRepInterval);
		
		waitForExtJSAjaxComplete(20);
		
		setChnlEntryType(chnlEntryType);
		
		waitForExtJSAjaxComplete(20);
		
		setChnlEntryMethod(chnlEntryMethod);
		
		waitForExtJSAjaxComplete(20);
		
		setChnlUOM(chnlUOM);

		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MTR_CHANNEL);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		//Open created Channel and edit it; check if all fields were save correctly during creation
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, chnlReference);
		
		waitForExtJSAjaxComplete(20);
		
		clickButton("Edit", DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "reference"), chnlReference, "Reference field value after creation is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "multiplier"), chnlMultiplier, "Multiplier field value after creation is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "numberOfDials"), chnlNumDigits, "Number of Digits field value after creation is correct?");
		
		//softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "numberOfDecimals"), chnlNumDecimals, "Number of Decimals field value after editing is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "calibrationDate"), chnlCalDate, "Calibration Date field value after creation is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "readingInterval"), chnlRepInterval, "Smallest Reporting Interval field value after creation is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "entryType"), chnlEntryType, "Entry Type field value after creation is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "entryMethod"), chnlEntryMethod, "Entry Method field value after creation is correct?");
		
		softAssert.assertEquals(getLookupValue(DIALOG_MTR_CHANNEL, "unitOfMeasure"), chnlUOM, "UOM field value after editing is correct?");
		
		setChnlReference(chnlReferenceEdited);
		
		setChnlMultiplier(chnlMultiplierEdited);
				
		setChnlCalDate(chnlCalDateEdited);
					
		setChnlRepInterval(chnlRepIntervalEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setChnlEntryType(chnlEntryTypeEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setChnlEntryMethod(chnlEntryMethodEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setChnlUOM(chnlUOMEdited);

		waitForExtJSAjaxComplete(20);
		
		
		//Check if Number of Digits and Number of Decimals fields are disabled if Entry Type is Usage
		softAssert.assertTrue(checkNumDigitsDisabled(), "Number of Digits field is read-only?");
		
		softAssert.assertTrue(checkNumDecimalsDisabled(), "Number of Decimals field is read-only?");
		
		saveClose(DIALOG_MTR_CHANNEL);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		//check if all fields were save correctly during editing
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, chnlReferenceEdited);
		
		waitForExtJSAjaxComplete(20);
		
		clickButton("Edit", DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "reference"), chnlReferenceEdited, "Reference field value after editing is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "multiplier"), chnlMultiplierEdited, "Multiplier field value after editing is correct?");
		
		//Check if Number of Digits and Number of Decimals fields are disabled if Entry Type is Usage
		softAssert.assertTrue(checkNumDigitsDisabled(), "Number of Digits is read-only?");
		
		softAssert.assertTrue(checkNumDecimalsDisabled(), "Number of Digits is read-only?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "calibrationDate"), chnlCalDateEdited, "Calibration Date field value after editing is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "readingInterval"), chnlRepIntervalEdited, "Smallest Reporting Interval field value after editing is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "entryType"), chnlEntryTypeEdited, "Entry Type field value after editing is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "entryMethod"), chnlEntryMethodEdited, "Entry Method field value after editing is correct?");
		
		softAssert.assertEquals(getLookupValue(DIALOG_MTR_CHANNEL, "unitOfMeasure"), chnlUOMEdited, "UOM field value after editing is correct?");
		
		softAssert.assertAll();
		
		Reporter.log("Meter Channel was successfully created and edited", true);
 }
 
 @Test(enabled=true)
	public void testMeterChannelDelete() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Meter Channels: C19905" + " </span><br>",
				true);

		Reporter.log("User deletes Meter Channel"  + " <br>",
				true);
		
		String chnlReference = "test auto chnl del" + getCurrentDate().substring(getCurrentDate().length()-6);
		String parameter = "Electricity Normal Usage";
		
		SoftAssert softAssert = new SoftAssert(); 
		
		softAssert.setMethodName("ChannelDelete");
		
		//Navigate to Meters Overview
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		expandMetering();
		
		openMeteringEntity("Meters");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		//Open pre-configured Meter and go to Channels tab
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", METER_GRID_CLASS, "3preMeter");
		
		waitForExtJSAjaxComplete(20);
		
		clickButton("Edit", METER_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickChannelsTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		//Create Channel
		
		clickButton("Add", DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		setChnlReference(chnlReference);
		
		setChnlParameter(parameter);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose(DIALOG_MTR_CHANNEL);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		//Delete created Channel
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", DIALOG_METER, chnlReference);
		
		waitForExtJSAjaxComplete(20);
		
		clickDeleteButton(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(Grid.isRowInGridPresent(driver, chnlReference, "@class", DIALOG_METER), "Is Channel present in grid after deletion?");
		
		softAssert.assertAll();
		
		Reporter.log("Meter Channel was successfully deleted", true);
			
	 }
 
}
