package test.energy.weatherstations;

import java.io.IOException;



import java.util.Arrays;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class WeatherStationChannelFormValidation extends
		MeteringWeatherstationChanelsPageObject {



	 @Test(enabled = false)
	public void testWeatherstationChannelFormValidation() throws IOException  {

	//TODO
		 Reporter.log("<span style='font-weight:bold;color:'> "
					+ "Test: Check Weather Station Channel form validation: " + " </span><br>",
					true);

			Reporter.log("User tries to create Weather Station Channel with different validations"  + " <br>",
					true);
		
			String weatherStationCode = "1preWeaterStationCRUD";
			String reference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
			String channelParameterSH = "Solar Hours";
			String channelParameterPRC = "Precipitation";
			String channelParameterHDD = "Heating Degree-days";
			
			List<String> mandatoryFieldsErrorMessageKeyWordsCheck =Arrays.asList("form", "invalid");

			
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("WeatherStationChannelFormValidation");

			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(20);
			
			expandMetering();
			
			openAnalysisEntity("Weather Stations");

			waitForExtJSAjaxComplete(20);
			
			Grid.checkRowInGriByTextValue(driver, weatherStationCode);
			
			waitForExtJSAjaxComplete(20);
			
			clickEditButton(WEATHERSTATIONS_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			changeTab("channels");
		
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			channelDialogId = getXWindowIdByClass(ADD_WEATHERSTATIONS_FORM_CLASS);
			
			clickButton("Add", channelDialogId);
			
			waitForExtJSAjaxComplete(20);

			//Check UI of mandatory fields (bold text, asterisk)
			
			Reporter.log("Check UI of mandatory fields (bold text, asterisk)", true);
					
			softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "channelParameter"), "Parameter field has correct UI (Bold text, asterix)");
					
			softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "reference"), "Reference field has correct UI (Bold text, asterix)");
					
			softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure"), "UOM field has correct UI (Bold text, asterix)");
					
			softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "readingInterval"), "Reading Interval field has correct UI (Bold text, asterix)");
					
			softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "entryMethod"), "Entry Method field has correct UI (Bold text, asterix)");
			
			//Check UI of non-mandatory fields (regular text)
					
			Reporter.log("Check UI of non-mandatory fields (regular text)", true);
							
			softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "code"), "Code field has correct UI");
			
			softAssert.assertTrue(checkInputDisabled("code"), "Code field is read-only");
			
			//Try to save Weather Station Channel Form with empty Reference field and check UOM/Reference fields prefill and Entry Method
			
			Reporter.log("Try to save Weather Station Channel Form with empty Reference field", true);
					
			setChannelParameter(channelParameterSH);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertLike(getReference(), "SH", "Reference field is prefilled with 'SH'");
			
			softAssert.assertEquals(getUOM(), "hour", "UOM field is prefilled with 'hour'");
			
			softAssert.assertEquals(getEntryMethod(), "Manual", "Entry Method is changed to 'Manual'");
			
			softAssert.assertFalse(checkInputDisabled("readingInterval"), "Reading Interval field is editable");
			
			softAssert.assertTrue(verifyItemExists("readingInterval", "Hour"), "Hour is present on Reading Interval dropdown");

			softAssert.assertTrue(verifyItemExists("readingInterval", "Day"), "Day is present on Reading Interval dropdown");

			softAssert.assertTrue(verifyItemExists("readingInterval", "Month"), "Month is present on Reading Interval dropdown");
			
			clearField("reference", "Reference");
			
			waitForExtJSAjaxComplete(20);
					
			save(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS);
					
			waitForExtJSAjaxComplete(20);
					
			softAssert.assertTrue(checkMandatoryFieldSave(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS),"Form can't be saved with empty Reference field"); 

			softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "reference"),"Red border is present on empty Reference field");

			softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
					
			reopenWeatherStationForm();
			
			//Try to save Weather Station Channel Form with empty Parameter field
			
			Reporter.log("Try to save Weather Station Channel Form with empty Parameter field", true);
					
			setReference(reference);
			
			waitForExtJSAjaxComplete(20);
			
			save(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS);
					
			waitForExtJSAjaxComplete(20);
					
			softAssert.assertTrue(checkMandatoryFieldSave(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS),"Form can't be saved with empty Parameter field"); 

			softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "channelParameter"),"Red border is present on empty Parameter field");

			softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
					
			reopenWeatherStationForm();
			
			//Try to save Weather Station Channel Form with empty Base Temperature field
			
			Reporter.log("Try to save Weather Station Channel Form with empty Base Temperature field", true);
					
			setReference(reference+"2");
			
			setChannelParameter(channelParameterHDD);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertEquals(getEntryMethod(), "Calculated", "Entry Method is changed to 'Calculated'");
			
			softAssert.assertTrue(checkInputDisabled("readingInterval"), "Reading Interval field is read-only");

			softAssert.assertEquals(getReadingInterval(), "Day", "Reading Interval field value is 'Day'");
			
			clearField("baseTemperature", "Base Temperature");
			
			save(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS);
					
			waitForExtJSAjaxComplete(20);
					
			softAssert.assertTrue(checkMandatoryFieldSave(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS),"Form can't be saved with empty Base Temperature field"); 

			softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "baseTemperature"),"Red border is present on empty Base Temperature field");

			softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
					
			reopenWeatherStationForm();
			
			//Try to save Weather Station Channel Form with empty UOM field
			
			Reporter.log("Try to save Weather Station Channel Form with empty UOM field", true);
					
			setReference(reference+"1");
			
			setChannelParameter(channelParameterPRC);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertEquals(getEntryMethod(), "Manual", "Entry Method is changed to 'Manual'");
			
			softAssert.assertFalse(checkInputDisabled("readingInterval"), "Reading Interval field is editable");
			
			softAssert.assertTrue(verifyItemExists("readingInterval", "Hour"), "Hour is present on Reading Interval dropdown");

			softAssert.assertTrue(verifyItemExists("readingInterval", "Day"), "Day is present on Reading Interval dropdown");

			softAssert.assertTrue(verifyItemExists("readingInterval", "Month"), "Month is present on Reading Interval dropdown");
			
			clearField("unitOfMeasure", "UOM");
			
			save(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS);
					
			waitForExtJSAjaxComplete(20);
					
			softAssert.assertTrue(checkMandatoryFieldSave(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS),"Form can't be saved with empty UOM field"); 

			softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure"),"Red border is present on empty UOM field");

			softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
					
			reopenWeatherStationForm();
			
			softAssert.assertAll();
			
			//TODO UOM lookup limitation check
			
	 }
	 
}