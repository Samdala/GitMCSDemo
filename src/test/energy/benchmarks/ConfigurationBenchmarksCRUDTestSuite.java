package test.energy.benchmarks;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class ConfigurationBenchmarksCRUDTestSuite extends
ConfigurationBenchmarksPageObject {




	 @Test(enabled=true)
	public void testAnalysisBenchmarksEdit() throws Exception, InterruptedException  {

			Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Edit Benchmarks " + " </span><br>",
					true);

			Reporter.log("User edits Benchmarks: C30020"  + " <br>",
					true);
			
			String lookupId = null;
			
			////////////Properties tab////////////
			String reference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
			String code = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
			String description = "description";
			String year = "2010";
			String energyLevel = "Primary";
			String originInternal = "Internal";
			String originExternal = "External";
			String evaluationBasisPercentiles = "Percentiles";
			String evaluationBasisAverage = "Average";
			String benchmarkClass = "Energy";
			String weatherStation = "1preWeatherStationUsed";
			
			String referenceEdited = "test auto gau ed" + getCurrentDate().substring(getCurrentDate().length()-6);
			String codeEdited = "test auto gau" + getCurrentDate().substring(getCurrentDate().length()-6);
			String descriptionEdited = "description edited";
			String yearEdited = "2014";
			String weatherStationEdited = "1preWeatherStationNotCalc";
			
			////////////Energy Object Types tab////////////
			String energyObjectTypePrimary = "Default";
			String energyObjectTypeSecondary = "Office";
			
			////////////Electricity tab////////////
			String metricNameForElectricity = "Electricity Usage per Building Volume";
			String primaryEnergyFactorForElectricity = "25";
			String cdd = "16.5";
			String lowUsageElectricity = "100";
			String mediumUsageElectricity = "200";
			String highUsageElectricity = "300";
			String numberOfEnergyObjectsElectricity = "2";
			
			String metricNameForElectricityEdited = "Electricity Usage per Building Area";
			String primaryEnergyFactorForElectricityEdited = "13";
			String cddEdited = "17.5";
			String lowUsageElectricityEdited = "10";
			String mediumUsageElectricityEdited = "20";
			String highUsageElectricityEdited = "30";
			String numberOfEnergyObjectsElectricityEdited = "10";
			
			////////////Combustibles tab////////////
			String metricNameForCombustibles = "Combustibles Usage per Building Volume";
			String primaryEnergyFactorForCombustibles = "25";
			String hdd = "20";
			String lowUsageCombustibles = "150";
			String mediumUsageCombustibles = "250";
			String highUsageCombustibles = "350";
			String numberOfEnergyObjectsCombustibles = "3";
			String primaryEnergyFactorForCombustiblesEdited = "12";
			String hddEdited = "19.5";
			String lowUsageCombustiblesEdited = "50";
			String mediumUsageCombustiblesEdited = "55";
			String highUsageCombustiblesEdited = "60";
			String numberOfEnergyObjectsCombustiblesEdited = "9";
			
			String metricNameForCombustiblesEdited = "Combustibles Usage per Building Area";
			
			/////////////Water tab////////////
			String metricNameForWater = "Water Usage per Building Area";
			String primaryEnergyFactorForWater = "2";
			String lowUsageWater = "1000";
			String mediumUsageWater = "2000";
			String highUsageWater = "3000";
			String numberOfEnergyObjectsWater = "5";
			
			String metricNameForWaterEdited = "Water Usage per Building Volume";
			String primaryEnergyFactorForWaterEdited = "5";
			String lowUsageWaterEdited = "700";
			String mediumUsageWaterEdited = "800";
			String highUsageWaterEdited = "900";
			String numberOfEnergyObjectsWaterEdited = "3";

			
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("BenchmarksEdit");

			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			waitAndClick(XPATH_ENERGY);
			
			Thread.sleep(3000);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			expandEnergyMenuItem("Configuration");	
			
			waitForExtJSAjaxComplete(25);
			
			clickOnEnergyEntity("Benchmarks");
			
			Thread.sleep(3000);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			clickAddButton(BENCHMARKS_GRID_CLASS);
			
			Thread.sleep(3000);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
		
			setCode(code);
			
			waitForExtJSAjaxComplete(3);
			
			setReference(reference);
			
			waitForExtJSAjaxComplete(3);
			
			setYear(year);
			
			waitForExtJSAjaxComplete(3);
			
			setEnergyLevel(energyLevel);
			
			waitForExtJSAjaxComplete(3);
			
			setOrigin(originInternal);
			
			waitForExtJSAjaxComplete(3);
			
			setEvaluationBasis(evaluationBasisPercentiles);
			
			waitForExtJSAjaxComplete(3);
			
			setPercentilesDependingOnRowAndColumn("1", "Percentile Value", "35");
			
			waitForExtJSAjaxComplete(3);
			
			setPercentilesDependingOnRowAndColumn("1", "Percentile Label", "Bottom");
			
			waitForExtJSAjaxComplete(3);
			
//			setPercentilesDependingOnRowAndColumn("1", "Percentile Color", "#FFF380");
			
			waitForExtJSAjaxComplete(3);
			
			setPercentilesDependingOnRowAndColumn("2", "Percentile Value", "65");
			
			waitForExtJSAjaxComplete(3);
			
			setPercentilesDependingOnRowAndColumn("2", "Percentile Label", "Middle");
			
			waitForExtJSAjaxComplete(3);
			
//			setPercentilesDependingOnRowAndColumn("2", "Percentile Color", "#FF7F50");
			
			waitForExtJSAjaxComplete(3);
			
			setPercentilesDependingOnRowAndColumn("3", "Percentile Value", "90");
			
			waitForExtJSAjaxComplete(3);
			
			setPercentilesDependingOnRowAndColumn("3", "Percentile Label", "Top");
			
			waitForExtJSAjaxComplete(3);
			
//			setPercentilesDependingOnRowAndColumn("3", "Percentile Color", "#98FF98");
			
			waitForExtJSAjaxComplete(3);
			
			setDescription(description);
			
			setWeatherStation(weatherStation);
			
			waitForExtJSAjaxComplete(3);
			
			save();
			
			waitForExtJSAjaxComplete(3);
			
			clickOnTab("Energy Object Types");
			
			waitForExtJSAjaxComplete(3);
			
			clickButtonOnDialog("Add");
			
			//if (!build14) {
				lookupId = getXWindowId("Select Energy Object Types");
			/*}
			else {
				lookupId = getXWindowId("Select Energy Object Types");
			}*/
			setValueGridLookupWithFilters("@id", lookupId, energyObjectTypePrimary, "Reference");
			
			waitForExtJSAjaxComplete(3);
			
			clickButtonOnDialog("Add");
			
			//if (!build14) {
				lookupId = getXWindowId("Select Energy Object Types");
			//}
			//else {
			//	lookupId = getXWindowId("Select a Energy Object Types");
			//}
			setValueGridLookupWithFilters("@id", lookupId, energyObjectTypeSecondary, "Reference");
			
			waitForExtJSAjaxComplete(3);
			
			clickOnTab("Energy");
			
			waitForExtJSAjaxComplete(3);
			
			setMetricOnElectricityTab(metricNameForElectricity);
			
			waitForExtJSAjaxComplete(3);
			
//			setCDD(cdd);
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setLowUsageDependingOnTab(lowUsageElectricity, "Electricity");
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setMediumUsageDependingOnTab(mediumUsageElectricity, "Electricity");
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setHighUsageDependingOnTab(highUsageElectricity, "Electricity");
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setNumberOfEnergyObjectsDependingOnTab(numberOfEnergyObjectsElectricity, "Electricity");
//			
//			waitForExtJSAjaxComplete(25);
			
			clickOnTab("Combustibles");
			
			waitForExtJSAjaxComplete(3);
			
			setMetricOnCombustiblesTab(metricNameForCombustibles);
			
			waitForExtJSAjaxComplete(3);
			
//			setHDD(hdd);
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setLowUsageDependingOnTab(lowUsageCombustibles, "Combustibles");
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setMediumUsageDependingOnTab(mediumUsageCombustibles, "Combustibles");
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setHighUsageDependingOnTab(highUsageCombustibles, "Combustibles");
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setNumberOfEnergyObjectsDependingOnTab(numberOfEnergyObjectsCombustibles, "Combustibles");
//			
//			waitForExtJSAjaxComplete(25);
			
			clickOnTab("Water");
			
			waitForExtJSAjaxComplete(3);
			
//			setLowUsageDependingOnTab(lowUsageWater, "Water");
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setMediumUsageDependingOnTab(mediumUsageWater, "Water");
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setHighUsageDependingOnTab(highUsageWater, "Water");
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setNumberOfEnergyObjectsDependingOnTab(numberOfEnergyObjectsWater, "Water");
//			
//			waitForExtJSAjaxComplete(25);
			
			setMetricOnWaterTab(metricNameForWater);
			
			waitForExtJSAjaxComplete(3);
			
			saveClose();
			
			Thread.sleep(3000);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);

			softAssert.assertTrue(Grid.isRowInGridPresent(driver, reference,"@class", "x-grid3"), "Created Benchmark is not present");
			
			Grid.checkRowInGriByTextValueAndClick(driver, reference);
			
			waitForExtJSAjaxComplete(3);
			
			clickEditButton(BENCHMARKS_GRID_CLASS);
			
			Thread.sleep(3000);
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertEquals(getReference(),reference, reference+" Reference after creation is wrong");
			
			softAssert.assertEquals(getCode(),code, code+ " Code after creation is wrong");
			
			softAssert.assertEquals(getYear(),year, year+ " Year after creation is wrong");
			
				softAssert.assertEquals(getEnergyLevel(),"Secondary", "Secondary"+ " Energy Level after creation is wrong");

				softAssert.assertEquals(getOrigin(),originInternal, originInternal+ " Origin after creation is wrong");
			
				softAssert.assertEquals(getWeatherStation(),weatherStation, weatherStation+ " Weather Station type after creation is wrong");
			
			softAssert.assertEquals(getDescription(),description, description+ " Description type after creation is wrong");
			
			softAssert.assertEquals(getEvaluationBasis(),evaluationBasisPercentiles, evaluationBasisPercentiles+ " Evaluation Basis after creation is wrong");
			
			softAssert.assertEquals(getBenchmarkClass(),benchmarkClass, benchmarkClass+ " Benchmark Class after creation is wrong");
			
			setCode(codeEdited);
			
			setReference(referenceEdited);
			
			waitForExtJSAjaxComplete(3);
			
			setYear(yearEdited);
			
			setWeatherStation(weatherStationEdited);
			
			setPercentilesDependingOnRowAndColumn("1", "Percentile Value", "10");
			
			waitForExtJSAjaxComplete(3);
			
			setPercentilesDependingOnRowAndColumn("1", "Percentile Label", "Small");
			
			waitForExtJSAjaxComplete(3);
			
//			setPercentilesDependingOnRowAndColumn("1", "Percentile Color", "#FFF380");
			
			waitForExtJSAjaxComplete(3);
			
			setPercentilesDependingOnRowAndColumn("2", "Percentile Value", "50");
			
			waitForExtJSAjaxComplete(3);
			
			setPercentilesDependingOnRowAndColumn("2", "Percentile Label", "Medium");
			
			waitForExtJSAjaxComplete(3);
			
//			setPercentilesDependingOnRowAndColumn("2", "Percentile Color", "#FF7F50");
			
			waitForExtJSAjaxComplete(3);
			
			setPercentilesDependingOnRowAndColumn("3", "Percentile Value", "75");
			
			waitForExtJSAjaxComplete(3);
			
			setPercentilesDependingOnRowAndColumn("3", "Percentile Label", "Big");
			
			waitForExtJSAjaxComplete(3);
			
//			setPercentilesDependingOnRowAndColumn("3", "Percentile Color", "#98FF98");
			
			waitForExtJSAjaxComplete(3);
			
			setDescription(descriptionEdited);
			
			waitForExtJSAjaxComplete(3);
			
			//TODO check for Energy Object Type present
			
			clickOnTab("Energy");
			
			waitForExtJSAjaxComplete(3);
			
			softAssert.assertEquals(getMetricOnElectricityTab(),metricNameForElectricity, metricNameForElectricity+ " Metric on Electricity tab after creation is wrong");
			
//			softAssert.assertEquals(getLowUsageDependingOnTab("Electricity"), lowUsageElectricity, lowUsageElectricity+ " Low Usage on Electricity tab after creation is wrong");
//			
//			softAssert.assertEquals(getMediumUsageDependingOnTab("Electricity"), mediumUsageElectricity, mediumUsageElectricity+ " Medium Usage on Electricity tab after creation is wrong");
//			
//			softAssert.assertEquals(getHighUsageDependingOnTab("Electricity"), highUsageElectricity, highUsageElectricity+ " High Usage on Electricity tab after creation is wrong");
//			
//			softAssert.assertEquals(getNumberOfEnergyObjectsDependingOnTab("Electricity"), numberOfEnergyObjectsElectricity, numberOfEnergyObjectsElectricity+ " Number Of Energy Objects on Electricity tab after creation is wrong");
			
			waitForExtJSAjaxComplete(3);
			
//			setCDD(cddEdited);
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setLowUsageDependingOnTab(lowUsageElectricityEdited, "Electricity");
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setMediumUsageDependingOnTab(mediumUsageElectricityEdited, "Electricity");
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setHighUsageDependingOnTab(highUsageElectricityEdited, "Electricity");
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setNumberOfEnergyObjectsDependingOnTab(numberOfEnergyObjectsElectricityEdited, "Electricity");
//			
//			waitForExtJSAjaxComplete(25);
			
			setMetricOnElectricityTab(metricNameForElectricityEdited);
			
			waitForExtJSAjaxComplete(3);
			
			clickOnTab("Combustibles");
			
			waitForExtJSAjaxComplete(3);
			
			softAssert.assertEquals(getMetricOnCombustiblesTab(),metricNameForCombustibles, metricNameForCombustibles+ " Metric on Combustibles tab after creation is wrong");
			
//			softAssert.assertEquals(getLowUsageDependingOnTab("Combustibles"), lowUsageCombustibles, lowUsageCombustibles+ " Low Usage on Combustibles tab after creation is wrong");
//			
//			softAssert.assertEquals(getMediumUsageDependingOnTab("Combustibles"), mediumUsageCombustibles, mediumUsageCombustibles+ " Medium Usage on Combustibles tab after creation is wrong");
//			
//			softAssert.assertEquals(getHighUsageDependingOnTab("Combustibles"), highUsageCombustibles, highUsageCombustibles+ " High Usage on Combustibles tab after creation is wrong");
//			
//			softAssert.assertEquals(getNumberOfEnergyObjectsDependingOnTab("Combustibles"), numberOfEnergyObjectsCombustibles, numberOfEnergyObjectsCombustibles+ " Number Of Energy Objects on Combustibles tab after creation is wrong");
			
			waitForExtJSAjaxComplete(3);
			

			setMetricOnCombustiblesTab(metricNameForCombustiblesEdited);
			
			waitForExtJSAjaxComplete(3);
				
			clickOnTab("Water");
			
			waitForExtJSAjaxComplete(3);
			
			softAssert.assertEquals(getMetricOnWaterTab(),metricNameForWater, metricNameForWater+ " Metric on Water tab after creation is wrong");
			
//			softAssert.assertEquals(getLowUsageDependingOnTab("Water"), lowUsageWater, lowUsageWater+ " Low Usage on Water tab after creation is wrong");
//			
//			softAssert.assertEquals(getMediumUsageDependingOnTab("Water"), mediumUsageWater, mediumUsageWater+ " Medium Usage on Water tab after creation is wrong");
//			
//			softAssert.assertEquals(getHighUsageDependingOnTab("Water"), highUsageWater, highUsageWater+ " High Usage on Water tab after creation is wrong");
//			
//			softAssert.assertEquals(getNumberOfEnergyObjectsDependingOnTab("Water"), numberOfEnergyObjectsWater, numberOfEnergyObjectsWater+ " Number Of Energy Objects on Water tab after creation is wrong");
			
			waitForExtJSAjaxComplete(3);
			
//			setLowUsageDependingOnTab(lowUsageWaterEdited, "Water");
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setMediumUsageDependingOnTab(mediumUsageWaterEdited, "Water");
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setHighUsageDependingOnTab(highUsageWaterEdited, "Water");
//			
//			waitForExtJSAjaxComplete(25);
//			
//			setNumberOfEnergyObjectsDependingOnTab(numberOfEnergyObjectsWaterEdited, "Water");
//			
//			waitForExtJSAjaxComplete(25);
			
			setMetricOnWaterTab(metricNameForWaterEdited);
			
			waitForExtJSAjaxComplete(3);
			
			saveClose();
			
			Thread.sleep(3000);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			Grid.checkRowInGriByTextValueAndClick(driver, referenceEdited);
			
			clickEditButton(BENCHMARKS_GRID_CLASS);
			
			Thread.sleep(3000);
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertEquals(getReference(),referenceEdited, referenceEdited+" Reference after edit is wrong");
			
			softAssert.assertEquals(getCode(),codeEdited, codeEdited+ " Code after edit is wrong");
			
			softAssert.assertEquals(getYear(),yearEdited, yearEdited+ " Year after edit is wrong");
			
			softAssert.assertEquals(getDescription(),descriptionEdited, descriptionEdited+ " Description after edit is wrong");
			
				softAssert.assertEquals(getEnergyLevel(),"Secondary", "Secondary"+ " Energy Level after edit is wrong");
			
			
				softAssert.assertEquals(getOrigin(),"Internal", "Internal"+ " Origin after edit is wrong");
			
				softAssert.assertEquals(getWeatherStation(),weatherStationEdited, weatherStationEdited+ " Weather Station type after edit is wrong");
			
			softAssert.assertEquals(getEvaluationBasis(),evaluationBasisPercentiles, evaluationBasisPercentiles+ " Evaluation Basis after edit is wrong");
			
			clickOnTab("Energy");
			
			waitForExtJSAjaxComplete(3);
			
			softAssert.assertEquals(getMetricOnElectricityTab(),metricNameForElectricityEdited, metricNameForElectricityEdited+ " Metric on Electricity tab after edit is wrong");
			
//			softAssert.assertEquals(getLowUsageDependingOnTab("Electricity"), lowUsageElectricityEdited, lowUsageElectricityEdited+ " Low Usage on Electricity tab after edit is wrong");
//			
//			softAssert.assertEquals(getMediumUsageDependingOnTab("Electricity"), mediumUsageElectricityEdited, mediumUsageElectricityEdited+ " Medium Usage on Electricity tab after edit is wrong");
//			
//			softAssert.assertEquals(getHighUsageDependingOnTab("Electricity"), highUsageElectricityEdited, highUsageElectricityEdited+ " High Usage on Electricity tab after edit is wrong");
//			
//			softAssert.assertEquals(getNumberOfEnergyObjectsDependingOnTab("Electricity"), numberOfEnergyObjectsElectricityEdited, numberOfEnergyObjectsElectricityEdited+ " Number Of Energy Objects on Electricity tab after edit is wrong");
			
			clickOnTab("Combustibles");
			
			waitForExtJSAjaxComplete(3);
			
			softAssert.assertEquals(getMetricOnCombustiblesTab(),metricNameForCombustiblesEdited, metricNameForCombustiblesEdited+ " Metric on Combustibles tab after edit is wrong");
			
//			softAssert.assertEquals(getLowUsageDependingOnTab("Combustibles"), lowUsageCombustiblesEdited, lowUsageCombustiblesEdited+ " Low Usage on Combustibles tab after edit is wrong");
//			
//			softAssert.assertEquals(getMediumUsageDependingOnTab("Combustibles"), mediumUsageCombustiblesEdited, mediumUsageCombustiblesEdited+ " Medium Usage on Combustibles tab after edit is wrong");
//			
//			softAssert.assertEquals(getHighUsageDependingOnTab("Combustibles"), highUsageCombustiblesEdited, highUsageCombustiblesEdited+ " High Usage on Combustibles tab after edit is wrong");
//			
//			softAssert.assertEquals(getNumberOfEnergyObjectsDependingOnTab("Combustibles"), numberOfEnergyObjectsCombustiblesEdited, numberOfEnergyObjectsCombustiblesEdited+ " Number Of Energy Objects on Combustibles tab after edit is wrong");
			
			clickOnTab("Water");
			
			waitForExtJSAjaxComplete(3);
			
			softAssert.assertEquals(getMetricOnWaterTab(),metricNameForWaterEdited, metricNameForWaterEdited+ " Metric on Water tab after edit is wrong");
			
//			softAssert.assertEquals(getLowUsageDependingOnTab("Water"), lowUsageWaterEdited, lowUsageWaterEdited+ " Low Usage on Water tab after edit is wrong");
//			
//			softAssert.assertEquals(getMediumUsageDependingOnTab("Water"), mediumUsageWaterEdited, mediumUsageWaterEdited+ " Medium Usage on Water tab after edit is wrong");
//			
//			softAssert.assertEquals(getHighUsageDependingOnTab("Water"), highUsageWaterEdited, highUsageWaterEdited+ " High Usage on Water tab after edit is wrong");
//			
//			softAssert.assertEquals(getNumberOfEnergyObjectsDependingOnTab("Water"), numberOfEnergyObjectsWaterEdited, numberOfEnergyObjectsWaterEdited+ " Number Of Energy Objects on Water tab after edit is wrong");
			
			close();
			
			Thread.sleep(3000);

			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			clickAddButton(BENCHMARKS_GRID_CLASS);
			
			Thread.sleep(3000);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
		
			setCode(code+"1");
			
			waitForExtJSAjaxComplete(3);
			
			setReference(reference+"1");
			
			waitForExtJSAjaxComplete(3);
			
			setYear(year);
			
			waitForExtJSAjaxComplete(3);
			
			setEnergyLevel(energyLevel);
			
			waitForExtJSAjaxComplete(3);
			
				setOrigin(originExternal);
			
			waitForExtJSAjaxComplete(3);
			
			setEvaluationBasis(evaluationBasisAverage);
			
			waitForExtJSAjaxComplete(3);
			
			setDescription(description);
			
			waitForExtJSAjaxComplete(3);
			
			save();
			
			Thread.sleep(3000);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			clickOnTab("Energy Object Types");
			
			waitForExtJSAjaxComplete(3);
			
			clickButtonOnDialog("Add");
			
			Thread.sleep(10000);
			lookupId = getXWindowId("Select Energy Object Types");
			setValueGridLookupWithFilters("@id", lookupId, energyObjectTypePrimary, "Reference");
			
			waitForExtJSAjaxComplete(3);
			
			clickButtonOnDialog("Add");
			
			lookupId = getXWindowId("Select Energy Object Types");
			setValueGridLookupWithFilters("@id", lookupId, energyObjectTypeSecondary, "Reference");
			
			waitForExtJSAjaxComplete(3);
			
			clickOnTab("Energy");
			
			waitForExtJSAjaxComplete(3);
			
			setMetricOnElectricityTab(metricNameForElectricity);
			
			waitForExtJSAjaxComplete(3);
			
			setPrimaryEnergyFactorDependingOnTab(primaryEnergyFactorForElectricity, "Electricity");
			
			waitForExtJSAjaxComplete(3);
			
			setCDD(cdd);
			
			waitForExtJSAjaxComplete(3);
			
			setAverageForExternalBenhcmark(ELECTRICITY_TAB_XPATH, "1", lowUsageElectricity);
			
			waitForExtJSAjaxComplete(25);
			
			setEnergyObjectsForExternalBenhcmark(ELECTRICITY_TAB_XPATH, "1", numberOfEnergyObjectsElectricity);
			
			waitForExtJSAjaxComplete(3);
			
			clickOnTab("Combustibles");
			
			waitForExtJSAjaxComplete(3);
			
			setMetricOnCombustiblesTab(metricNameForCombustibles);
			
			waitForExtJSAjaxComplete(3);
			
			setPrimaryEnergyFactorDependingOnTab(primaryEnergyFactorForCombustibles, "Combustibles");
			
			waitForExtJSAjaxComplete(3);
			
			setHDD(hdd);
			
			waitForExtJSAjaxComplete(3);
			
			/*setNumberOfEnergyObjectsForExternalBenhcmarkDependingOnTab(numberOfEnergyObjectsCombustibles, "Combustibles");
			
			waitForExtJSAjaxComplete(3);
			
			setAverageDependingOnTab(lowUsageCombustibles, "Combustibles");
			
			Thread.sleep(2000);*/
			
			setAverageForExternalBenhcmark(COMBUSTIBLES_TAB_XPATH, "1", lowUsageCombustibles);
			
			Thread.sleep(2000);
			
			setEnergyObjectsForExternalBenhcmark(COMBUSTIBLES_TAB_XPATH, "1", numberOfEnergyObjectsCombustibles);
			
			waitForExtJSAjaxComplete(3);
			
			clickOnTab("Water");
			
			waitForExtJSAjaxComplete(3);
			
			setPrimaryEnergyFactorDependingOnTab(primaryEnergyFactorForWater, "Water");
			
			waitForExtJSAjaxComplete(3);
			
		
			
			/*setNumberOfEnergyObjectsForExternalBenhcmarkDependingOnTab(numberOfEnergyObjectsWater, "Water");
			
			waitForExtJSAjaxComplete(3);*/
			
			setMetricOnWaterTab(metricNameForWater);
			
			waitForExtJSAjaxComplete(3);
			
			/*setAverageDependingOnTab(lowUsageWater, "Water");
			
			Thread.sleep(2000);*/
			
			setAverageForExternalBenhcmarkWater(WATER_TAB_XPATH, "1", lowUsageWater);
			
			waitForExtJSAjaxComplete(25);
			
			setEnergyObjectsForExternalBenhcmark(WATER_TAB_XPATH, "1", numberOfEnergyObjectsWater);
			
			waitForExtJSAjaxComplete(25);
			
			saveClose();
			
			Thread.sleep(3000);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);

			softAssert.assertTrue(Grid.isRowInGridPresent(driver, reference+"1","@class", "x-grid3"), "Created Benchmark is not present");
			
			Grid.checkRowInGriByTextValueAndClick(driver, reference+"1");
			
			waitForExtJSAjaxComplete(3);
			
			clickEditButton(BENCHMARKS_GRID_CLASS);
			
			Thread.sleep(3000);
			
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertEquals(getReference(),reference+"1", reference+"1"+" Reference after creation is wrong");
			
			softAssert.assertEquals(getCode(),code+"1", code+"1"+ " Code after creation is wrong");
			
			softAssert.assertEquals(getYear(),year, year+ " Year after creation is wrong");
			
			softAssert.assertEquals(getEnergyLevel(),energyLevel, energyLevel+ " Energy Level after creation is wrong");
			
			softAssert.assertEquals(getOrigin(),originExternal, originExternal+ " Origin after creation is wrong");
			
			softAssert.assertEquals(getDescription(),description, description+ " Description type after creation is wrong");
			
			softAssert.assertEquals(getEvaluationBasis(),evaluationBasisAverage, evaluationBasisAverage+ " Evaluation Basis after creation is wrong");
			
			softAssert.assertEquals(getBenchmarkClass(),benchmarkClass, benchmarkClass+ " Benchmark Class after creation is wrong");
			
			setCode(codeEdited+"1");
			
			setReference(referenceEdited+"1");
			
			waitForExtJSAjaxComplete(3);
			
			setYear(yearEdited);
			
			setDescription(descriptionEdited);
			
			waitForExtJSAjaxComplete(3);
			
			//TODO check for Energy Object Type present
			
			clickOnTab("Energy");
			
			waitForExtJSAjaxComplete(3);
			
			softAssert.assertEquals(getPrimaryEnergyFactorDependingOnTab("Electricity"), primaryEnergyFactorForElectricity, primaryEnergyFactorForElectricity+ " Primary Energy Factor on Electricity tab after creation is wrong");
			
			softAssert.assertEquals(getMetricOnElectricityTab(),metricNameForElectricity, metricNameForElectricity+ " Metric on Electricity tab after creation is wrong");
			
			softAssert.assertEquals(getLowUsageDependingOnTab("Electricity", "Default"), lowUsageElectricity, lowUsageElectricity+ " Low Usage on Electricity tab after creation is wrong");
			
			softAssert.assertEquals(getNumberOfEnergyObjectsForExternalBenhcmarkDependingOnTab("Electricity"), numberOfEnergyObjectsElectricity, numberOfEnergyObjectsElectricity+ " Number Of Energy Objects on Electricity tab after creation is wrong");
			
			waitForExtJSAjaxComplete(3);
			
			setCDD(cddEdited);
			
			waitForExtJSAjaxComplete(3);
			
			/*setAverageDependingOnTab(lowUsageElectricityEdited, "Electricity");
			
			waitForExtJSAjaxComplete(3);
			
			setNumberOfEnergyObjectsForExternalBenhcmarkDependingOnTab(numberOfEnergyObjectsElectricityEdited, "Electricity");
			
			waitForExtJSAjaxComplete(3); */
			setAverageForExternalBenhcmark(ELECTRICITY_TAB_XPATH, "1", lowUsageElectricityEdited);
			
			Thread.sleep(2000);
			
			setEnergyObjectsForExternalBenhcmark(ELECTRICITY_TAB_XPATH, "1", numberOfEnergyObjectsElectricityEdited);
			
			Thread.sleep(1000);
			
			setMetricOnElectricityTab(metricNameForElectricityEdited);
			
			waitForExtJSAjaxComplete(3);
			
			setPrimaryEnergyFactorDependingOnTab(primaryEnergyFactorForElectricityEdited, "Electricity");
			
			waitForExtJSAjaxComplete(3);
			
			clickOnTab("Combustibles");
			
			waitForExtJSAjaxComplete(3);
			
			softAssert.assertEquals(getPrimaryEnergyFactorDependingOnTab("Combustibles"), primaryEnergyFactorForCombustibles, primaryEnergyFactorForCombustibles+ " Primary Energy Factor on Combustibles tab after creation is wrong");
			
			softAssert.assertEquals(getMetricOnCombustiblesTab(),metricNameForCombustibles, metricNameForCombustibles+ " Metric on Combustibles tab after creation is wrong");
			
			softAssert.assertEquals(getLowUsageDependingOnTab("Combustibles", "Default"), lowUsageCombustibles, lowUsageCombustibles+ " Low Usage on Combustibles tab after creation is wrong");
			
			softAssert.assertEquals(getNumberOfEnergyObjectsForExternalBenhcmarkDependingOnTab("Combustibles"), numberOfEnergyObjectsCombustibles, numberOfEnergyObjectsCombustibles+ " Number Of Energy Objects on Combustibles tab after creation is wrong");
			
			waitForExtJSAjaxComplete(3);
			
			try {
				setHDD(hddEdited);
			} catch (Exception e) {
				waitForExtJSAjaxComplete(3);
				setHDD(hddEdited);
			}
			
			
			waitForExtJSAjaxComplete(3);
			
		/*	setAverageDependingOnTab(lowUsageCombustiblesEdited, "Combustibles");
			
			waitForExtJSAjaxComplete(3);
			
			setNumberOfEnergyObjectsForExternalBenhcmarkDependingOnTab(numberOfEnergyObjectsCombustiblesEdited, "Combustibles");
			
			waitForExtJSAjaxComplete(3); */
			setAverageForExternalBenhcmark(COMBUSTIBLES_TAB_XPATH, "1", lowUsageCombustiblesEdited);
			
			waitForExtJSAjaxComplete(3);
			
			setEnergyObjectsForExternalBenhcmark(COMBUSTIBLES_TAB_XPATH, "1", numberOfEnergyObjectsCombustiblesEdited);
			
		        waitForExtJSAjaxComplete(3);

			setMetricOnCombustiblesTab(metricNameForCombustiblesEdited);
			
			waitForExtJSAjaxComplete(3);
			
			setPrimaryEnergyFactorDependingOnTab(primaryEnergyFactorForCombustiblesEdited, "Combustibles");
			
			waitForExtJSAjaxComplete(3);
				
			clickOnTab("Water");
			
			waitForExtJSAjaxComplete(3);
			
			softAssert.assertEquals(getPrimaryEnergyFactorDependingOnTab("Water"), primaryEnergyFactorForWater, primaryEnergyFactorForWater+ " Primary Energy Factor on Water tab after creation is wrong");
			
			softAssert.assertEquals(getMetricOnWaterTab(),metricNameForWater, metricNameForWater+ " Metric on Water tab after creation is wrong");
			
			softAssert.assertEquals(getLowUsageDependingOnTab("Water", "Default"), lowUsageWater, lowUsageWater+ " Low Usage on Water tab after creation is wrong");
			
			softAssert.assertEquals(getNumberOfEnergyObjectsForExternalBenhcmarkDependingOnTab("Water"), numberOfEnergyObjectsWater, numberOfEnergyObjectsWater+ " Number Of Energy Objects on Water tab after creation is wrong");
			
			waitForExtJSAjaxComplete(3);
			
		/*	try {
				setAverageDependingOnTab(lowUsageWaterEdited, "Water");
			} catch (Exception e) {
				waitForExtJSAjaxComplete(3);
				setAverageDependingOnTab(lowUsageWaterEdited, "Water");
			} */
			
			waitForExtJSAjaxComplete(3);
			
			/*setNumberOfEnergyObjectsForExternalBenhcmarkDependingOnTab(numberOfEnergyObjectsWaterEdited, "Water");
			
			waitForExtJSAjaxComplete(3);*/
			
			setMetricOnWaterTab(metricNameForWaterEdited);
			
			waitForExtJSAjaxComplete(3);
			
			setPrimaryEnergyFactorDependingOnTab(primaryEnergyFactorForWaterEdited, "Water");
			
			waitForExtJSAjaxComplete(3);
			
			setAverageForExternalBenhcmarkWater(WATER_TAB_XPATH, "1", lowUsageWaterEdited);
			
			waitForExtJSAjaxComplete(3);
			
			setEnergyObjectsForExternalBenhcmark(WATER_TAB_XPATH, "1", numberOfEnergyObjectsWaterEdited);
			
			saveClose();
			
			Thread.sleep(3000);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			Grid.checkRowInGriByTextValueAndClick(driver, referenceEdited+"1");
			
			clickEditButton(BENCHMARKS_GRID_CLASS);
			
			Thread.sleep(3000);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertEquals(getReference(),referenceEdited+"1", referenceEdited+"1"+" Reference after edit is wrong");
			
			softAssert.assertEquals(getCode(),codeEdited+"1", codeEdited+"1"+ " Code after edit is wrong");
			
			softAssert.assertEquals(getYear(),yearEdited, yearEdited+ " Year after edit is wrong");
			
			softAssert.assertEquals(getDescription(),descriptionEdited, descriptionEdited+ " Description after edit is wrong");
			
			softAssert.assertEquals(getEnergyLevel(),"Primary", "Primary"+ " Energy Level after edit is wrong");
			
			softAssert.assertEquals(getOrigin(),originExternal, originExternal+ " Origin after edit is wrong");
			
			softAssert.assertEquals(getEvaluationBasis(),evaluationBasisAverage, evaluationBasisAverage+ " Evaluation Basis after edit is wrong");
			
			clickOnTab("Energy");
			
			waitForExtJSAjaxComplete(3);
			
			softAssert.assertEquals(getPrimaryEnergyFactorDependingOnTab("Electricity"), primaryEnergyFactorForElectricityEdited, primaryEnergyFactorForElectricityEdited+ " Primary Energy Factor on Electricity tab after edit is wrong");
			
			softAssert.assertEquals(getMetricOnElectricityTab(),metricNameForElectricityEdited, metricNameForElectricityEdited+ " Metric on Electricity tab after edit is wrong");
			
			softAssert.assertEquals(getLowUsageDependingOnTab("Electricity", "Default"), lowUsageElectricityEdited, lowUsageElectricityEdited+ " Low Usage on Electricity tab after edit is wrong");
			
			softAssert.assertEquals(getNumberOfEnergyObjectsForExternalBenhcmarkDependingOnTab("Electricity"), numberOfEnergyObjectsElectricityEdited, numberOfEnergyObjectsElectricityEdited+ " Number Of Energy Objects on Electricity tab after edit is wrong");
			
			clickOnTab("Combustibles");
			
			waitForExtJSAjaxComplete(3);
			
			softAssert.assertEquals(getPrimaryEnergyFactorDependingOnTab("Combustibles"), primaryEnergyFactorForCombustiblesEdited, primaryEnergyFactorForCombustiblesEdited+ " Primary Energy Factor on Combustibles tab after edit is wrong");
			
			softAssert.assertEquals(getMetricOnCombustiblesTab(),metricNameForCombustiblesEdited, metricNameForCombustiblesEdited+ " Metric on Combustibles tab after edit is wrong");
			
			//not edited TODO
			softAssert.assertEquals(getLowUsageDependingOnTab("Combustibles", "Default"), lowUsageCombustiblesEdited, lowUsageCombustiblesEdited+ " Low Usage on Combustibles tab after edit is wrong");
			
			softAssert.assertEquals(getNumberOfEnergyObjectsForExternalBenhcmarkDependingOnTab("Combustibles"), numberOfEnergyObjectsCombustiblesEdited, numberOfEnergyObjectsCombustiblesEdited+ " Number Of Energy Objects on Combustibles tab after edit is wrong");
			
			clickOnTab("Water");
			
			waitForExtJSAjaxComplete(3);
			
			softAssert.assertEquals(getPrimaryEnergyFactorDependingOnTab("Water"), primaryEnergyFactorForWaterEdited, primaryEnergyFactorForWaterEdited+ " Primary Energy Factor on Water tab after edit is wrong");
			
			softAssert.assertEquals(getMetricOnWaterTab(),metricNameForWaterEdited, metricNameForWaterEdited+ " Metric on Water tab after edit is wrong");
			
			softAssert.assertEquals(getLowUsageDependingOnTab("Water", "Default"), lowUsageWaterEdited, lowUsageWaterEdited+ " Low Usage on Water tab after edit is wrong");
			
			softAssert.assertEquals(getNumberOfEnergyObjectsForExternalBenhcmarkDependingOnTab("Water"), numberOfEnergyObjectsWaterEdited, numberOfEnergyObjectsWaterEdited+ " Number Of Energy Objects on Water tab after edit is wrong");
			
			
			softAssert.assertAll();

			Reporter.log("Benchmarks"  + " was successfully edited", true);

		}

	@Test(enabled = true)
	public void testBenchmarksDelete() throws Exception {

		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Benchmarks Station " + " </span><br>",
				true);

		Reporter.log("User deletes Benchmarks: C26451 "  + " <br>",
				true);
		
		String reference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String year = "2014";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("BenchmarksDelete");

		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(5);
		
		clickOnEnergyEntity("Benchmarks");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddButton(BENCHMARKS_GRID_CLASS);
		
		Thread.sleep(5000);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
	
		setCode(code);
		
		setReference(reference);
		
		setYear(year);
		
		waitForExtJSAjaxComplete(3);
		
		save();
		
		waitForExtJSAjaxComplete(25);
		
		close();
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		waitForExtJSAjaxComplete(25);
		
		clickDeleteButton(BENCHMARKS_GRID_CLASS);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, reference), reference+" reference after delete is present");
		
		softAssert.assertAll();
		
		Reporter.log("Benchmarks was successfully deleted", true);
		
	}
	
	
}
