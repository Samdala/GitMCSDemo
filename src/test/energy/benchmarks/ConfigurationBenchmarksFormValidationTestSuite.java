package test.energy.benchmarks;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class ConfigurationBenchmarksFormValidationTestSuite extends
ConfigurationBenchmarksPageObject {
	
	@Test(enabled=true) //
		public void testConfigurationBenchmarksFormValidation() throws IOException  {

			Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Benchmarks Form Validation" + " </span><br>",
					true);

			Reporter.log("User tries to save Benchmark form with different validations: ????"  + " <br>",
					true);
			
			Boolean build14 = driver.getCurrentUrl().contains("14");
			
			String lookupId = null;
			String code = "test auto code";
			String reference = "test auto ref";
			String codeExternal = "test auto code ext";
			String referenceExternal= "test auto ref ext";
			String year = "198";
			String weatherStation = "1preWeatherStationUsed";	
			String energyObjectType = "Default";
			String PrimaryEnergyFactor = "1";
			//TODO create Non Unique Benchmarks and fill variables properly 
			String codeNonUnique = "nonUnqiueCode";
			String refNonUnique = "nonUnqiueRef";
			String yearNonUnique = "2014";
			String yearInFuture = "2050";
			
			String metricNameForElectricity = "Electricity Usage per Building Volume";
			String metricNameForCombustibles = "Combustibles Usage per Building Volume";
			String metricNameForWater = "Water Usage per Building Area";
			
			List<String> mandatoryFieldsErrorMessageKeyWordsCheck =Arrays.asList("fill", "mandatory");
			List<String> uniqueCodeFieldErrorMessageKeyWordsCheck =Arrays.asList("Code", "exists");
			List<String> uniqueYearReferencePairFieldsErrorMessageKeyWordsCheck =Arrays.asList("Reference", "Year", "exists");
			List<String> yearFieldsInFutureErrorMessageKeyWordsCheckTrunk =Arrays.asList("Year", "future");
			List<String> yearFieldsInFutureErrorMessageKeyWordsCheck =Arrays.asList("year", "future");
			
			////////////Properties tab////////////
			
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("BenchmarksEdit");

			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(20);
			
			expandEnergyMenuItem("Configuration");	
			
			clickOnEnergyEntity("Benchmarks");
			
			waitForExtJSAjaxComplete(20);
			
			boolean status = Grid.isRowInGridPresent(driver, reference, "@class", BENCHMARKS_GRID_CLASS);
			
			if(status){
				Grid.checkRowInGriByTextValueAndClick(driver, reference);
				
				clickDeleteButton(BENCHMARKS_GRID_CLASS);
				
				waitForExtJSAjaxComplete(10);
			} 
			
			clickAddButton(BENCHMARKS_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			////////////////////////////Check UI of mandatory fields (bold text, asterisk)/////////////////////
			
			Reporter.log("---------->Check UI of mandatory fields (bold text, asterisk)", true);
			
			softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_BENCHMARKS_FORM_CLASS, "code"), "Code field has correct UI (Bold text, asterix)");
			
			softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_BENCHMARKS_FORM_CLASS, "reference"), "Reference field has correct UI (Bold text, asterix)");
			
			softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_BENCHMARKS_FORM_CLASS, "year"), "Year field has correct UI (Bold text, asterix)");
			
			softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_BENCHMARKS_FORM_CLASS, "origin"), "Origin field has correct UI (Bold text, asterix)");
			
			softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_BENCHMARKS_FORM_CLASS, "evaluationBasis"), "evaluation Basis field has correct UI (Bold text, asterix)");
			
			/////////////////////////////Check UI of non-mandatory fields (regular text)////////////////////////
			
			Reporter.log("---------->Check UI of non-mandatory fields (regular text)", true);
			
			softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_BENCHMARKS_FORM_CLASS, "description"), "description field has correct UI");
			
			softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_BENCHMARKS_FORM_CLASS, "energyLevel"), "Energy Level field has correct UI");
			
			///////////////////////////Validate Default values for fields on Properties tab/////////////////////////
			
			Reporter.log("---------->Validate Default values for fields on Properties tab", true);
			
			softAssert.assertEquals(getOrigin(), "External");
			
			softAssert.assertEquals(getEvaluationBasis(), "Percentiles");
			
			softAssert.assertEquals(getPercentilesDependingOnRowAndColumnOnInitialForm("1", "Percentile Value"), "20");
			
			softAssert.assertEquals(getPercentilesDependingOnRowAndColumnOnInitialForm("2", "Percentile Value"), "50");
			
			softAssert.assertEquals(getPercentilesDependingOnRowAndColumnOnInitialForm("3", "Percentile Value"), "80");
			
			softAssert.assertEquals(getPercentilesDependingOnRowAndColumnOnInitialForm("1", "Percentile Label"), "Low");
			
			softAssert.assertEquals(getPercentilesDependingOnRowAndColumnOnInitialForm("2", "Percentile Label"), "Medium");
			
			softAssert.assertEquals(getPercentilesDependingOnRowAndColumnOnInitialForm("3", "Percentile Label"), "High");
			
			softAssert.assertEquals(getEnergyLevel(), "Primary");
			
			if (!build14) {
			
			softAssert.assertEquals(verifyWeatherStationFieldDisabled(), true, "Weather Station field is not present on form, because Origin = External");
			
			}
			
			////////////////////////////Try to save Meter Form with empty Year field///////////////////////
			
			Reporter.log("---------->Try to save Meter Form with empty Year field", true);
			
			setCode(incrementCode(code));
			
			setReference(incrementRef(reference));
			
			waitForExtJSAjaxComplete(20);
			
			saveClose();
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(checkFormSaved(),"Form can't be saved with empty Year field"); 

			softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_BENCHMARKS_FORM_CLASS, "year"),"Red border is present on empty Year field");

			softAssert.assertTrue(checkMessageInvalidFormCustomized(driver, ADD_BENCHMARKS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present because of empty Year field");
			
			clickOnHeading();
			
			reopenBenchmarksForm();
			
			////////////////////////////Try to save Benchmark Form with empty Code field///////////////////////
			
			Reporter.log("---------->Try to save Benchmark Form with empty Code field", true);
			
			clearField("code", "Code");
			
			setReference(incrementRef(reference));
			
			setYear(incrementYear(year));
			
			waitForExtJSAjaxComplete(20);
			
			saveClose();
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(checkFormSaved(),"Form can't be saved with empty Code field"); 

			softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_BENCHMARKS_FORM_CLASS, "code"),"Red border is present on empty Code field");

			softAssert.assertTrue(checkMessageInvalidFormCustomized(driver, ADD_BENCHMARKS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present because of empty Code field"); 
			
			clickOnHeading();
			
			reopenBenchmarksForm();
			
			////////////////////////////Try to save Benchmark Form with empty Reference field///////////////////////
			
			Reporter.log("---------->Try to save Benchmark Form with empty Reference field", true);
			
			clearField("reference", "Reference");
			
			setCode(incrementCode(code));
			
			setYear(incrementYear(year));
			
			waitForExtJSAjaxComplete(20);
			
			saveClose();
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(checkFormSaved(),"Form can't be saved with empty Reference field"); 

			softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_BENCHMARKS_FORM_CLASS, "reference"),"Red border is present on empty Reference field");

			softAssert.assertTrue(checkMessageInvalidFormCustomized(driver, ADD_BENCHMARKS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present because of empty Reference field"); 
			
			clickOnHeading();
			
			reopenBenchmarksForm();
			
			////////////////////////////Try to save Benchmark Form with empty Weather Station field///////////////////////
			
			if (!build14) {
			
			Reporter.log("---------->Try to save Benchmark Form with empty WeatherStation field", true);
			
			setOrigin("Internal");

			setReference(incrementRef(reference));

			setCode(incrementCode(code));

			setYear(incrementYear(year));

			waitForExtJSAjaxComplete(20);

			saveClose();

			waitForExtJSAjaxComplete(20);

			softAssert.assertTrue(checkFormSaved(),"Form can't be saved with empty WeatherStation field"); 

			softAssert.assertTrue(McsElement.checkInvalidRedBorderInputFieldWOName(driver, ADD_BENCHMARKS_FORM_CLASS, "Weather Station"),"Red border is present on empty Weather Station field");

			softAssert.assertTrue(checkMessageInvalidFormCustomized(driver, ADD_BENCHMARKS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present because of empty Weather Station field"); 
			
			clickOnHeading();
			
			reopenBenchmarksForm();
			
			}
			
			//////////////////////////Try to save Benchmark Form with non-unique Code field/////////////////////
			
			Reporter.log("---------->Try to save Benchmark Form with non-unique Code field", true);
			
			if (!build14) {
			
			setOrigin("Internal");
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(McsElement.checkInputDisabled(driver, "@class", ADD_BENCHMARKS_FORM_CLASS, "energyLevel"), "Energy Level field is read-only after Origin was set to Internal");
			
			}
			
			setCode(codeNonUnique);
			
			setReference(incrementRef(reference));
			
			setYear(incrementYear(year));
			
			if (!build14) {
			
			setWeatherStation(weatherStation);
			
			}
			
			waitForExtJSAjaxComplete(20);
			
			saveClose();
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(checkFormSaved(),"Form can't be saved with non-unique Code field"); 

			softAssert.assertTrue(checkMessageInvalidFormCustomized(driver, ADD_BENCHMARKS_FORM_CLASS, uniqueCodeFieldErrorMessageKeyWordsCheck),"Message about invalid form is present because of non-unique COde field"); 
			
			clickOnHeading();
			
			reopenBenchmarksForm();
			
			//////////////////////////Try to save Benchmark Form with non-unique combination of Reference-Year fields/////////////////////
			
			Reporter.log("---------->Try to save Benchmark Form with non-unique combination of Reference-Year fields", true);
			
			if (!build14) {
			
			setOrigin("Internal");
			
			}
			
			setCode(incrementCode(code));
			
			setReference(refNonUnique);
			
			setYear(yearNonUnique);
			
			if (!build14) {
			
			setWeatherStation(weatherStation);
			
			}
			
			waitForExtJSAjaxComplete(20);
			
			saveClose();
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(checkFormSaved(),"Form can't be saved with non-unique non-unique combination of Reference-Year fields"); 

			softAssert.assertTrue(checkMessageInvalidFormCustomized(driver, ADD_BENCHMARKS_FORM_CLASS, uniqueYearReferencePairFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present because of non-unique combination of Reference-Year fields"); 
			
			clickOnHeading();
			
			reopenBenchmarksForm();
			
			//TODO//////////////////////////Try to save Percentile value less than 0 (zero)//////////////////////////
			
			//TODO////////////////////////Try to save Percentile value bigger than 100///////////////////////////////
			
			////////////////////////Try to save Benchmarks with Year field value that is in future///////////////////////////////
			
			Reporter.log("---------->Try to save Benchmarks with Year field value that is in future", true);
			
			if (!build14) {
			
			setOrigin("Internal");
			
			}
			
			setCode(incrementCode(code));
			
			setReference(incrementRef(reference));
			
			if (!build14) {
			
			setWeatherStation(weatherStation);
			
			}
			
			setYear(yearInFuture);
			
			waitForExtJSAjaxComplete(20);
			
			saveClose();
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertTrue(checkFormSaved(),"Form can't be saved with Year field value that is in future"); 

//			softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_BENCHMARKS_FORM_CLASS, "year"),"Red border is present on Year field that has value in future");//TODO ask why

			if(build14){
				softAssert.assertTrue(checkMessageInvalidFormCustomized(driver, ADD_BENCHMARKS_FORM_CLASS, yearFieldsInFutureErrorMessageKeyWordsCheck),"Message about invalid form is present because of Year field value that is in future");
			} else{
				softAssert.assertTrue(checkMessageInvalidFormCustomized(driver, ADD_BENCHMARKS_FORM_CLASS, yearFieldsInFutureErrorMessageKeyWordsCheckTrunk),"Message about invalid form is present because of Year field value that is in future");
			}
			
			waitForExtJSAjaxComplete(25);
			
			clickOnHeading();
			
			reopenBenchmarksForm();
			
			//////////////////////Save the form///////////////////////
			
			Reporter.log("---------->Save the form", true);
			
			if (!build14) {
			
			setOrigin("Internal");
			
			}
			
			
			setCode(code);
			
			setReference(reference);
			
			setYear(incrementYear(year));
			
			if (!build14) {
			
			setWeatherStation(weatherStation);
			
			}
			
			save();
			
			waitForExtJSAjaxComplete(25);
			
			///////////////////////////Verify Origin and Evaluation Basis fields are read-only///////////////////////////
			Reporter.log("---------->Verify Origin and Evaluation Basis fields are read-only", true);
			
			softAssert.assertTrue(checkInputReadOnly("origin"), "Origin field is read-only after Benchmark form was saved");
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertTrue(checkInputReadOnly("evaluationBasis"), "Evaluation Basis field is read-only after Benchmark form was saved");
			
			clickOnTab("Energy");
			
			waitForExtJSAjaxComplete(25);
			
			/////////////////////////Check Status field default value on Electricity tab////////////////////
			
			Reporter.log("---------->Check Status field default value on Electricity tab", true);
			
			clickOnTab("Electricity");
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertEquals(getStatusDependingOnTab("Electricity"), "Active", "Status field is prefilled with 'Active' by default on Electricity tab");
			
			if (!build14) {
			
			softAssert.assertEquals(verifyPrimaryEnergyFactorIsNotDisplayed("Electricity"), true, "Primary Energy Factor is not displayed on Electricity tab because Origin was set to Internal");
			
			}
			
			///////////////////////Check Status field default value on Combustibles tab////////////////////
			
			Reporter.log("---------->Check Status field default value on Combustibles tab", true);
			
			clickOnTab("Combustibles");
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertEquals(getStatusDependingOnTab("Combustibles"), "Active", "Status field is prefilled with 'Active' by default on Combustibles tab");
			
			if (!build14) {
			
			softAssert.assertEquals(verifyPrimaryEnergyFactorIsNotDisplayed("Combustibles"), true, "Primary Energy Factor is not displayed on Combustibles tab because Origin was set to Internal");
			
			}
			
			/////////////////////Check Status field default value on Water tab////////////////////
			
			Reporter.log("---------->Check Status field default value on Water tab", true);
			
			clickOnTab("Water");
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertEquals(getStatusDependingOnTab("Water"), "Active", "Status field is prefilled with 'Active' by default on Water tab");

			if (!build14) {
			
			softAssert.assertEquals(verifyPrimaryEnergyFactorIsNotDisplayed("Water"), true, "Primary Energy Factor is not displayed on Water tab because Origin was set to Internal");
			
			} 
			
			////////////////////Create External Benchmark////////////////////
			
			Reporter.log("---------->Create External Benchmark", true);
			
			close();
			
			waitForExtJSAjaxComplete(20);
			
			status = Grid.isRowInGridPresent(driver, referenceExternal, "@class", BENCHMARKS_GRID_CLASS);
			
			if(status){
				Grid.checkRowInGriByTextValueAndClick(driver, referenceExternal);
				
				clickDeleteButton(BENCHMARKS_GRID_CLASS);
				
				waitForExtJSAjaxComplete(10);
			} 
			
			clickAddButton(BENCHMARKS_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			setCode(codeExternal);
			
			setReference(referenceExternal);
			
			setYear(incrementYear(year));
			
			setOrigin("External");
			
			waitForExtJSAjaxComplete(20);
			
			save();
			
			waitForExtJSAjaxComplete(20);
			
			clickOnTab("Energy Object Types");
			
			clickButtonOnDialog("Add");
			
			waitForExtJSAjaxComplete(25);
			
			//if (!build14) {
				lookupId = getXWindowId("Select Energy Object Types");
			//}
			//else {
			//	lookupId = getXWindowId("Select a Energy Object Types");
			//}
			
			setValueGridLookup("@id", lookupId, energyObjectType);
			
			waitForExtJSAjaxComplete(25);
			
			clickOnTab("Energy");
			
			waitForExtJSAjaxComplete(25);
			
			setMetricOnElectricityTab(metricNameForElectricity);
			
			waitForExtJSAjaxComplete(25);
			
			clickOnTab("Combustibles");
			
			waitForExtJSAjaxComplete(25);
			
			setMetricOnCombustiblesTab(metricNameForCombustibles);
			
			waitForExtJSAjaxComplete(25);
			
			///////////////////Try to save Benchmark Form with empty Metric field on Water tab///////////////////
			
			Reporter.log("---------->Try to save Benchmark Form with empty Metric field on Water tab", true);
			
			clickOnTab("Water");
			
			waitForExtJSAjaxComplete(25);
			
			setMetricOnWaterTab(metricNameForWater);
			
			waitForExtJSAjaxComplete(25);
			
			saveClose();
			
			waitForExtJSAjaxComplete(25);
			
			Grid.checkRowInGriByTextValueAndClick(driver, referenceExternal);
			
			//Mark
			
			waitForExtJSAjaxComplete(25);
			
			clickEditButton(BENCHMARKS_GRID_CLASS);
			
			waitForExtJSAjaxComplete(25);
			
			clickOnTab("Energy");
			
			waitForExtJSAjaxComplete(25);
			
			clickOnTab("Combustibles");
			
			waitForExtJSAjaxComplete(25);
			
			clickOnTab("Water");
			
			waitForExtJSAjaxComplete(25);
			
			clearMetricOnWaterTab();
			
			waitForExtJSAjaxComplete(25);
			
			saveClose();
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertTrue(checkFormSaved(),"Form can't be saved with empty Metric field on Water tab"); 

			softAssert.assertTrue(checkInvalidRedBorderInputFieldWOName("Water", "metric"),"Red border is present on empty Metric field");

			softAssert.assertTrue(checkMessageInvalidFormCustomized(driver, ADD_BENCHMARKS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present because of empty Metric field on Water tab");
			
			clickOnHeading();
			
			reopenBenchmarksFormAndNavigateToEnergyTab(referenceExternal);
			
			clickOnTab("Electricity");
			
			waitForExtJSAjaxComplete(25);
			
			clickOnTab("Combustibles");
			
			waitForExtJSAjaxComplete(25);
			
			clickOnTab("Water");
			
			waitForExtJSAjaxComplete(25);
			
			//////////////////Check if Primary Energy Factor field on Water tab is prefilled with correct value by default///////////////////
			
			Reporter.log("---------->Check if Primary Energy Factor field on Water tab is prefilled with correct value by default", true);
			
			softAssert.assertEquals(getPrimaryEnergyFactorDependingOnTab("Water"), "1");
			
			//////////////////Try to save Benchmark Form with empty Primary Energy Factor field on Water tab///////////////
			
			Reporter.log("---------->Try to save Benchmark Form with empty Primary Energy Factor field on Water tab", true);
			
			setMetricOnWaterTab(metricNameForWater);
			
			waitForExtJSAjaxComplete(25);
			
			clearPrimaryEnergyFactorDependingOnTab("Water");
			
			waitForExtJSAjaxComplete(25);
			
			saveClose();
			
			softAssert.assertTrue(checkFormSaved(),"Form can't be saved with empty Primary Energy Factor field on Water tab"); 

			softAssert.assertTrue(checkInvalidRedBorderInputFieldWOName("Water", "primaryEnergyFactor"),"Red border is present on empty primaryEnergyFactor field");

			softAssert.assertTrue(checkMessageInvalidFormCustomized(driver, ADD_BENCHMARKS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present becasue of empty Primary Energy Factor field on Water tab");
			
			clickOnHeading();
			
			reopenBenchmarksFormAndNavigateToEnergyTab(referenceExternal);
			
			clickOnTab("Electricity");
			
			waitForExtJSAjaxComplete(25);
			
			clickOnTab("Combustibles");
			
			waitForExtJSAjaxComplete(25);
			
			clickOnTab("Water");
			
			waitForExtJSAjaxComplete(25);
			
			setPrimaryEnergyFactorDependingOnTab(PrimaryEnergyFactor, "Water");
			
			setMetricOnWaterTab(metricNameForWater);
			
			waitForExtJSAjaxComplete(25);
			
			///////////////////Try to save Benchmark Form with empty Metric field on Combustibles tab///////////////////
			
			Reporter.log("---------->Try to save Benchmark Form with empty Metric field on Combustibles tab", true);
			
			clickOnTab("Combustibles");
		
			waitForExtJSAjaxComplete(25);
		
			clearMetricOnCombustiblesTab();
		
			waitForExtJSAjaxComplete(25);
		
			saveClose();
		
			softAssert.assertTrue(checkFormSaved(),"Form can't be saved with empty Metric field on Combustibles tab"); 

			softAssert.assertTrue(checkInvalidRedBorderInputFieldWOName("Combustibles", "metric"),"Red border is present on empty Metric field");

			softAssert.assertTrue(checkMessageInvalidFormCustomized(driver, ADD_BENCHMARKS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present because of empty Metric field on Combustibles tab");
		
			clickOnHeading();
			
			reopenBenchmarksFormAndNavigateToEnergyTab(referenceExternal);
			
			clickOnTab("Electricity");
			
			waitForExtJSAjaxComplete(25);
			
			clickOnTab("Combustibles");
			
			waitForExtJSAjaxComplete(25);
			
			//////////////////Check if Primary Energy Factor field on Combustibles tab is prefilled with correct value by default///////////////////
			
			Reporter.log("---------->Check if Primary Energy Factor field on Combustibles tab is prefilled with correct value by default", true);
			
			softAssert.assertEquals(getPrimaryEnergyFactorDependingOnTab("Combustibles"), "1");
		
			//////////////////Try to save Meter Form with empty Primary Energy Factor field on Combustibles tab///////////////
			
			Reporter.log("---------->Try to save Meter Form with empty Primary Energy Factor field on Combustibles tab", true);
		
			setMetricOnCombustiblesTab(metricNameForCombustibles);
		
			waitForExtJSAjaxComplete(25);
		
			clearPrimaryEnergyFactorDependingOnTab("Combustibles");
		
			waitForExtJSAjaxComplete(25);
		
			saveClose();
		
			softAssert.assertTrue(checkFormSaved(),"Form can't be saved with empty Primary Energy Factor field on Combustibles tab"); 

			softAssert.assertTrue(checkInvalidRedBorderInputFieldWOName("Combustibles", "primaryEnergyFactor"),"Red border is present on empty primaryEnergyFactor field");

			softAssert.assertTrue(checkMessageInvalidFormCustomized(driver, ADD_BENCHMARKS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present because of empty Primary Energy Factor field on Combustibles tab");
		
			clickOnHeading();
			
			reopenBenchmarksFormAndNavigateToEnergyTab(referenceExternal);
			
			clickOnTab("Electricity");
			
			waitForExtJSAjaxComplete(25);
			
			clickOnTab("Combustibles");
			
			waitForExtJSAjaxComplete(25);
		
			setPrimaryEnergyFactorDependingOnTab(PrimaryEnergyFactor, "Combustibles");
		
			setMetricOnCombustiblesTab(metricNameForCombustibles);
		
			waitForExtJSAjaxComplete(25);
			
			///////////////////Try to save Benchmark Form with empty Metric field on Electricity tab///////////////////
			
			Reporter.log("---------->Try to save Benchmark Form with empty Metric field on Electricity tab", true);
			
			clickOnTab("Electricity");
		
			waitForExtJSAjaxComplete(25);
		
			clearMetricOnElectricityTab();
		
			waitForExtJSAjaxComplete(25);
		
			saveClose();
		
			softAssert.assertTrue(checkFormSaved(),"Form can't be saved with empty Metric field on Electricity tab"); 

			softAssert.assertTrue(checkInvalidRedBorderInputFieldWOName("Electricity", "metric"),"Red border is present on empty Metric field");

			softAssert.assertTrue(checkMessageInvalidFormCustomized(driver, ADD_BENCHMARKS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present becase of empty Metric field on Electricity tab");
		
			clickOnHeading();
			
			reopenBenchmarksFormAndNavigateToEnergyTab(referenceExternal);
			
			//////////////////Check if Primary Energy Factor field on Electricity tab is prefilled with correct value by default///////////////////
			
			Reporter.log("---------->Check if Primary Energy Factor field on Electricity tab is prefilled with correct value by default", true);
			
			softAssert.assertEquals(getPrimaryEnergyFactorDependingOnTab("Electricity"), "1");
		
			//////////////////Try to save Meter Form with empty Primary Energy Factor field on Electricity tab///////////////
			
			Reporter.log("---------->Try to save Meter Form with empty Primary Energy Factor field on Electricity tab", true);
		
			setMetricOnElectricityTab(metricNameForElectricity);
		
			waitForExtJSAjaxComplete(25);
		
			clearPrimaryEnergyFactorDependingOnTab("Electricity");
		
			waitForExtJSAjaxComplete(25);
		
			saveClose();
		
			softAssert.assertTrue(checkFormSaved(),"Form can't be saved with empty Primary Energy Factor field on Electricity tab"); 

			softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_BENCHMARKS_FORM_CLASS, "primaryEnergyFactor"),"Red border is present on empty Primary Energy Factor field");

			softAssert.assertTrue(checkMessageInvalidFormCustomized(driver, ADD_BENCHMARKS_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present because of empty Primary Energy Factor field on Electricity tab");
			
			softAssert.assertAll();
			
	 }

}
