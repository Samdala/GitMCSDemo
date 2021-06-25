package test.energy.commodityclasses;

import java.io.IOException;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.energy.metrics.MetricsPageObject;
import test.energy.navigator.EnergyAnalysisPageObject;
import test.energy.occurrence.OccurrencePageObject;
import test.energy.pricetemplates.ConfigurationPriceTemplatesPageObject;
import test.energy.tariffcalendars.TariffCalendarsPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;

public class CommodityClassesCRUDTestsuite extends CommodityClassesPageObject{
	
	/**
	 * Test Case ID: C60600
	 * Author : SSU
	 * @throws IOException
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testExceptRollUpAllValuesReadOnly() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: All the values except 'Rollup UOM' are read only: C60600" + " </span><br>",
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testExceptRollUpAllValuesReadOnly");
		
		boolean build14 = driver.getCurrentUrl().contains("14");

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(20);
		
		openConfigurationEntity("Commodity Classes");		
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyColumnsInGrid("Code"), "Code Column is Present");
		
		softAssert.assertTrue(verifyColumnsInGrid("Reference"), "Reference Column is Present");
		
		softAssert.assertTrue(verifyColumnsInGrid("UOM Class"), "UOM Class Column is Present");
		
		softAssert.assertTrue(verifyColumnsInGrid("Rollup UOM"), "Rollup UOM Column is Present");
		
		waitForExtJSAjaxComplete(5);
		
		//Verify All commodity Classes are present in Grid
		
		softAssert.assertTrue(verifyCommodityClassesInGrid("@class", "energy editable-grid x-panel-noborder", "0", "Electricity"), "Electricity Commodity Class is present in the grid");
		
		softAssert.assertTrue(verifyCommodityClassesInGrid("@class", "energy editable-grid x-panel-noborder", "0", "Fuel"), "Fuel Commodity Class is present in the grid");
		
		softAssert.assertTrue(verifyCommodityClassesInGrid("@class", "energy editable-grid x-panel-noborder", "0", "Gas"), "Gas Commodity Class is present in the grid");
		
		softAssert.assertTrue(verifyCommodityClassesInGrid("@class", "energy editable-grid x-panel-noborder", "0", "Heat"), "Heat Commodity Class is present in the grid");
		
		softAssert.assertTrue(verifyCommodityClassesInGrid("@class", "energy editable-grid x-panel-noborder", "0", "Waste"), "Waste Commodity Class is present in the grid");
		
		softAssert.assertTrue(verifyCommodityClassesInGrid("@class", "energy editable-grid x-panel-noborder", "0", "Water"), "Water Commodity Class is present in the grid");
		
		waitForExtJSAjaxComplete(5);
		
		//Verify Except Rollup UOM all Rows are Un-Editable
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "1", "x-grid3-td-0", "class").contains("x-unselectable"), "Electricity Commodity Class Code is Readonly");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "1", "x-grid3-td-1", "class").contains("x-unselectable"), "Electricity Commodity Class Reference is Readonly");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "1", "x-grid3-td-2", "class").contains("x-unselectable"), "Electricity Commodity Class UOM class is Readonly");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "2", "x-grid3-td-0", "class").contains("x-unselectable"), "Fuel Commodity Class Code is Readonly");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "2", "x-grid3-td-1", "class").contains("x-unselectable"), "Fuel Commodity Class Reference is Readonly");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "2", "x-grid3-td-2", "class").contains("x-unselectable"), "Fuel Commodity Class UOM class is Readonly");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "3", "x-grid3-td-0", "class").contains("x-unselectable"), "Gas Commodity Class Code is Readonly");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "3", "x-grid3-td-1", "class").contains("x-unselectable"), "Gas Commodity Class Reference is Readonly");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "3", "x-grid3-td-2", "class").contains("x-unselectable"), "Gas Commodity Class UOM class is Readonly");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "4", "x-grid3-td-0", "class").contains("x-unselectable"), "Heat Commodity Class Code is Readonly");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "4", "x-grid3-td-1", "class").contains("x-unselectable"), "Heat Commodity Class Reference is Readonly");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "4", "x-grid3-td-2", "class").contains("x-unselectable"), "Heat Commodity Class UOM class is Readonly");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "5", "x-grid3-td-0", "class").contains("x-unselectable"), "Waste Commodity Class Code is Readonly");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "5", "x-grid3-td-1", "class").contains("x-unselectable"), "Waste Commodity Class Reference is Readonly");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "5", "x-grid3-td-2", "class").contains("x-unselectable"), "Waste Commodity Class UOM class is Readonly");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "6", "x-grid3-td-0", "class").contains("x-unselectable"), "Water Commodity Class Code is Readonly");

		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "6", "x-grid3-td-1", "class").contains("x-unselectable"), "Water Commodity Class Reference is Readonly");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "6", "x-grid3-td-2", "class").contains("x-unselectable"), "Water Commodity Class UOM class is Readonly");
		
		waitForExtJSAjaxComplete(5);
		
		setRollupUOM("@class", "energy editable-grid x-panel-noborder", "1", "x-grid3-td-3", "calorie");
		
		clickAnyColumn("@class", "energy editable-grid x-panel-noborder", "1", "x-grid3-td-2");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "1", "x-grid3-td-3", "textContent").contains("cal"), "calorie value is set as Rollup UOM for Electricity");
		
		waitForExtJSAjaxComplete(5);
		
		setRollupUOM("@class", "energy editable-grid x-panel-noborder", "2", "x-grid3-td-3", "Joule");
		
		clickAnyColumn("@class", "energy editable-grid x-panel-noborder", "2", "x-grid3-td-2");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "2", "x-grid3-td-3", "textContent").contains("J"), "Joule value is set as Rollup UOM for Electricity");
		
		waitForExtJSAjaxComplete(5);
		
		setRollupUOM("@class", "energy editable-grid x-panel-noborder", "3", "x-grid3-td-3", "kiloJoule");
		
		clickAnyColumn("@class", "energy editable-grid x-panel-noborder", "3", "x-grid3-td-2");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "3", "x-grid3-td-3", "textContent").contains("kJ"), "KiloJoule value is set as Rollup UOM for Electricity");
		
		waitForExtJSAjaxComplete(5);
		
		setRollupUOM("@class", "energy editable-grid x-panel-noborder", "4", "x-grid3-td-3", "Watthour");
		
		clickAnyColumn("@class", "energy editable-grid x-panel-noborder", "4", "x-grid3-td-2");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "4", "x-grid3-td-3", "textContent").contains("Wh"), "Watthour value is set as Rollup UOM for Electricity");
		
		waitForExtJSAjaxComplete(5);
		
		setRollupUOM("@class", "energy editable-grid x-panel-noborder", "5", "x-grid3-td-3", "ounce");
		
		clickAnyColumn("@class", "energy editable-grid x-panel-noborder", "5", "x-grid3-td-2");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "5", "x-grid3-td-3", "textContent").contains("oz"), "Newtonmeter value is set as Rollup UOM for Electricity");
		
		waitForExtJSAjaxComplete(5);
		
		if(build14){
			
			setRollupUOM("@class", "energy editable-grid x-panel-noborder", "6", "x-grid3-td-3", "centiliter");
			
			clickAnyColumn("@class", "energy editable-grid x-panel-noborder", "6", "x-grid3-td-2");
			
			waitForExtJSAjaxComplete(5);
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "6", "x-grid3-td-3", "textContent").contains("cl"), "calorie value is set as Rollup UOM for Electricity");
			
		} else {
			
			setRollupUOM("@class", "energy editable-grid x-panel-noborder", "6", "x-grid3-td-3", "Joule");
			
			clickAnyColumn("@class", "energy editable-grid x-panel-noborder", "6", "x-grid3-td-2");
			
			waitForExtJSAjaxComplete(5);
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "6", "x-grid3-td-3", "textContent").contains("J"), "calorie value is set as Rollup UOM for Electricity");
			
			waitForExtJSAjaxComplete(5);
			
			setRollupUOM("@class", "energy editable-grid x-panel-noborder", "7", "x-grid3-td-3", "cubic yard");
			
			clickAnyColumn("@class", "energy editable-grid x-panel-noborder", "7", "x-grid3-td-2");
			
			waitForExtJSAjaxComplete(5);
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "7", "x-grid3-td-3", "textContent").contains("yd³"), "calorie value is set as Rollup UOM for Electricity");
			
			waitForExtJSAjaxComplete(5);
			
			setRollupUOM("@class", "energy editable-grid x-panel-noborder", "8", "x-grid3-td-3", "cubic yard");
			
			clickAnyColumn("@class", "energy editable-grid x-panel-noborder", "8", "x-grid3-td-2");
			
			waitForExtJSAjaxComplete(5);
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "8", "x-grid3-td-3", "textContent").contains("yd³"), "calorie value is set as Rollup UOM for Electricity");
			
			waitForExtJSAjaxComplete(5);
			
		}
			
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertAll();
		
		Reporter.log("All the values except 'Rollup UOM' are read only is successfully verified", true);
	}

	/**
	 * Test Case ID: C60602
	 * Author : SSU
	 * @throws IOException
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testColumnsChange() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: User can change the columns that are visible: C60602" + " </span><br>",
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testColumnsChange");

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(20);
		
		openConfigurationEntity("Commodity Classes");		
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyColumnsInGrid("Code"), "Code Column is Present");
		
		softAssert.assertTrue(verifyColumnsInGrid("Reference"), "Reference Column is Present");
		
		softAssert.assertTrue(verifyColumnsInGrid("UOM Class"), "UOM Class Column is Present");
		
		softAssert.assertTrue(verifyColumnsInGrid("Rollup UOM"), "Rollup UOM Column is Present");
		
		waitForExtJSAjaxComplete(5);
		
		changeColumns("@class", "energy editable-grid x-panel-noborder", "0", "Columns");
		
		waitForExtJSAjaxComplete(5);
		
		unCheckColumns("Code");
		
		waitForExtJSAjaxComplete(5);
		
		unCheckColumns("Reference");
		
		waitForExtJSAjaxComplete(5);
		
		unCheckColumns("UOM Class");
		
		waitForExtJSAjaxComplete(5);
		
		unCheckColumns("Rollup UOM");
		
		waitForExtJSAjaxComplete(5);
		
		clickAnyColumn("@class", "energy editable-grid x-panel-noborder", "5", "x-grid3-td-3");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(verifyColumnsNotPresent("@class", "energy editable-grid x-panel-noborder","Code"), "Code Column is Not Present");
		
		softAssert.assertTrue(verifyColumnsNotPresent("@class", "energy editable-grid x-panel-noborder","Reference"), "Reference Column is Not Present");
		
		softAssert.assertTrue(verifyColumnsNotPresent("@class", "energy editable-grid x-panel-noborder","UOM Class"), "UOM Class Column is Not Present");
		
		softAssert.assertFalse(verifyColumnsNotPresent("@class", "energy editable-grid x-panel-noborder","Rollup UOM"), "Rollup UOM Column is Present");
		
		waitForExtJSAjaxComplete(10);
		
		changeColumns("@class", "energy editable-grid x-panel-noborder", "3", "Columns");
		
		waitForExtJSAjaxComplete(5);
		
		checkColumns("Code");
		
		waitForExtJSAjaxComplete(5);
		
		checkColumns("Reference");
		
		waitForExtJSAjaxComplete(5);
		
		checkColumns("UOM Class");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertAll();
		
		Reporter.log("User can change the columns that are visible is successfully verified", true);
	}
	
	/**
	 * Test Case ID: C60601
	 * Author : SSU
	 * @throws IOException
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testColumnsSorting() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: All the columns can be sorted (in ascending or descending order): C60601" + " </span><br>",
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testColumnsSorting");
		
		Boolean boolean14 = driver.getCurrentUrl().contains("14");

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(20);
		
		openConfigurationEntity("Commodity Classes");		
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyColumnsInGrid("Code"), "Code Column is Present");
		
		softAssert.assertTrue(verifyColumnsInGrid("Reference"), "Reference Column is Present");
		
		softAssert.assertTrue(verifyColumnsInGrid("UOM Class"), "UOM Class Column is Present");
		
		softAssert.assertTrue(verifyColumnsInGrid("Rollup UOM"), "Rollup UOM Column is Present");
		
		waitForExtJSAjaxComplete(5);
		
		changeColumns("@class", "energy editable-grid x-panel-noborder", "0", "Sort Descending");
		
		waitForExtJSAjaxComplete(5);
		
		//Verify All commodity Classes are Descended in Grid Column Code
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "1", "x-grid3-td-0", "textContent").contains("Water"), "Water Commodity Class is present in the grid");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "2", "x-grid3-td-0", "textContent").contains("Waste"), "Waste Commodity Class is present in the grid");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "3", "x-grid3-td-0", "textContent").contains("Heat"), "Heat Commodity Class is present in the grid");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "4", "x-grid3-td-0", "textContent").contains("Gas"), "Gas Commodity Class is present in the grid");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "5", "x-grid3-td-0", "textContent").contains("Fuel"), "Fuel Commodity Class is present in the grid");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "6", "x-grid3-td-0", "textContent").contains("Electricity"), "Electricity Commodity Class is present in the grid");
		
		if(!boolean14){
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "7", "x-grid3-td-0", "textContent").contains("Cooling"), "Cooling Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "8", "x-grid3-td-0", "textContent").contains("Compressed Air"), "Compressed Air Commodity Class is present in the grid");
		}
		
		waitForExtJSAjaxComplete(5);
		
		//Verify All commodity Classes are Descended in Grid Column Reference
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "1", "x-grid3-td-1", "textContent").contains("Water"), "Water Commodity Class is present in the grid");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "2", "x-grid3-td-1", "textContent").contains("Waste"), "Waste Commodity Class is present in the grid");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "3", "x-grid3-td-1", "textContent").contains("Heat"), "Heat Commodity Class is present in the grid");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "4", "x-grid3-td-1", "textContent").contains("Gas"), "Gas Commodity Class is present in the grid");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "5", "x-grid3-td-1", "textContent").contains("Fuel"), "Fuel Commodity Class is present in the grid");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "6", "x-grid3-td-1", "textContent").contains("Electricity"), "Electricity Commodity Class is present in the grid");
		
		if(!boolean14){
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "7", "x-grid3-td-1", "textContent").contains("Cooling"), "Cooling Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "8", "x-grid3-td-1", "textContent").contains("Compressed Air"), "Compressed Air Commodity Class is present in the grid");
		}
		
		waitForExtJSAjaxComplete(5);
		
		//Verify All commodity Classes are Descended in Grid Column UOM Class
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "1", "x-grid3-td-2", "textContent").contains("Volume"), "Volume Commodity Class is present in the grid");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "2", "x-grid3-td-2", "textContent").contains("Mass"), "Mass Commodity Class is present in the grid");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "3", "x-grid3-td-2", "textContent").contains("Energy"), "Energy Commodity Class is present in the grid");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "4", "x-grid3-td-2", "textContent").contains("Energy"), "Energy Commodity Class is present in the grid");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "5", "x-grid3-td-2", "textContent").contains("Energy"), "Energy Commodity Class is present in the grid");
		
		softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "6", "x-grid3-td-2", "textContent").contains("Energy"), "Energy Commodity Class is present in the grid");
		
		if(!boolean14){
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "7", "x-grid3-td-2", "textContent").contains("Energy"), "Energy Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "8", "x-grid3-td-2", "textContent").contains("Volume"), "Volume Commodity Class is present in the grid");
		}
		
		waitForExtJSAjaxComplete(5);
		
		//Verify All commodity Classes are Descended in Grid Column UOM Class
		String []rollUpValues = {"m³", "kg", "kWh", "kWh", "kWh", "kWh"};
		
		String []rollUpValuesTrunk = {"m³", "kg", "kWh", "kWh", "kWh", "kWh", "kWh", "m³"};
		
		List<String> sortedValues;
		
		if(boolean14) {
		
			sortedValues = sortRollUPValues();

			softAssert.assertEquals(sortedValues.toArray(), rollUpValues, "RollUP Values Are Sorted Successfully");
		} else {
			sortedValues = sortRollUPValues();

			softAssert.assertEquals(sortedValues.toArray(), rollUpValuesTrunk, "RollUP Values Are Sorted Successfully");
		}
		
		waitForExtJSAjaxComplete(10);
		
		changeColumns("@class", "energy editable-grid x-panel-noborder", "0", "Sort Ascending");
		
		waitForExtJSAjaxComplete(5);
		
		//Verify All commodity Classes are Descended in Grid Column Code
		
		if(!boolean14){
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "1", "x-grid3-td-0", "textContent").contains("Compressed Air"), "Compressed Air Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "2", "x-grid3-td-0", "textContent").contains("Cooling"), "Cooling Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "3", "x-grid3-td-0", "textContent").contains("Electricity"), "Electricity Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "4", "x-grid3-td-0", "textContent").contains("Fuel"), "Fuel Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "5", "x-grid3-td-0", "textContent").contains("Gas"), "Gas Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "6", "x-grid3-td-0", "textContent").contains("Heat"), "Heat Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "7", "x-grid3-td-0", "textContent").contains("Waste"), "Waste Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "8", "x-grid3-td-0", "textContent").contains("Water"), "Water Commodity Class is present in the grid");
		
		} else{ 
		
		   softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "1", "x-grid3-td-0", "textContent").contains("Electricity"), "Electricity Commodity Class is present in the grid");
		
		   softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "2", "x-grid3-td-0", "textContent").contains("Fuel"), "Fuel Commodity Class is present in the grid");
		
		   softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "3", "x-grid3-td-0", "textContent").contains("Gas"), "Gas Commodity Class is present in the grid");
		
		   softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "4", "x-grid3-td-0", "textContent").contains("Heat"), "Heat Commodity Class is present in the grid");
		
		   softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "5", "x-grid3-td-0", "textContent").contains("Waste"), "Waste Commodity Class is present in the grid");
		
		   softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "6", "x-grid3-td-0", "textContent").contains("Water"), "Water Commodity Class is present in the grid");
		
		}
		waitForExtJSAjaxComplete(5);
		
		//Verify All commodity Classes are Descended in Grid Column Reference
		
		if(!boolean14){
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "1", "x-grid3-td-1", "textContent").contains("Compressed Air"), "Compressed Air Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "2", "x-grid3-td-1", "textContent").contains("Cooling"), "Cooling Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "3", "x-grid3-td-1", "textContent").contains("Electricity"), "Electricity Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "4", "x-grid3-td-1", "textContent").contains("Fuel"), "Fuel Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "5", "x-grid3-td-1", "textContent").contains("Gas"), "Gas Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "6", "x-grid3-td-1", "textContent").contains("Heat"), "Heat Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "7", "x-grid3-td-1", "textContent").contains("Waste"), "Waste Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "8", "x-grid3-td-1", "textContent").contains("Water"), "Water Commodity Class is present in the grid");
		
		} else{ 
		
		   softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "1", "x-grid3-td-1", "textContent").contains("Electricity"), "Electricity Commodity Class is present in the grid");
		
		   softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "2", "x-grid3-td-1", "textContent").contains("Fuel"), "Fuel Commodity Class is present in the grid");
		
		   softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "3", "x-grid3-td-1", "textContent").contains("Gas"), "Gas Commodity Class is present in the grid");
		
		   softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "4", "x-grid3-td-1", "textContent").contains("Heat"), "Heat Commodity Class is present in the grid");
		
		   softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "5", "x-grid3-td-1", "textContent").contains("Waste"), "Waste Commodity Class is present in the grid");
		
		   softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "6", "x-grid3-td-1", "textContent").contains("Water"), "Water Commodity Class is present in the grid");
		
		}
		
		waitForExtJSAjaxComplete(5);
		
		//Verify All commodity Classes are Descended in Grid Column UOM Class
		
		if(!boolean14){
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "1", "x-grid3-td-2", "textContent").contains("Volume"), "Volume Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "2", "x-grid3-td-2", "textContent").contains("Energy"), "Energy Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "3", "x-grid3-td-2", "textContent").contains("Energy"), "Energy Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "4", "x-grid3-td-2", "textContent").contains("Energy"), "Energy Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "5", "x-grid3-td-2", "textContent").contains("Energy"), "Energy Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "6", "x-grid3-td-2", "textContent").contains("Energy"), "Energy Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "7", "x-grid3-td-2", "textContent").contains("Mass"), "Mass Commodity Class is present in the grid");
			
			softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "8", "x-grid3-td-2", "textContent").contains("Volume"), "Volume Commodity Class is present in the grid");
		
		} else{ 
		
		   softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "1", "x-grid3-td-2", "textContent").contains("Energy"), "Energy Commodity Class is present in the grid");
		
		   softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "2", "x-grid3-td-2", "textContent").contains("Energy"), "Energy Commodity Class is present in the grid");
		
		   softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "3", "x-grid3-td-2", "textContent").contains("Energy"), "Energy Commodity Class is present in the grid");
		
		   softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "4", "x-grid3-td-2", "textContent").contains("Energy"), "Energy Commodity Class is present in the grid");
		
		   softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "5", "x-grid3-td-2", "textContent").contains("Mass"), "Mass Commodity Class is present in the grid");
		
		   softAssert.assertTrue(getCellText("@class", "energy editable-grid x-panel-noborder", "6", "x-grid3-td-2", "textContent").contains("Volume"), "Volume Commodity Class is present in the grid");
		
		}
		
		waitForExtJSAjaxComplete(5);
		
		//Verify All commodity Classes are Descended in Grid Column UOM Class
		
		String []rollUpValuesAsc = {"kWh", "kWh", "kWh", "kWh", "kg", "m³"};
		
		String []rollUpValuesTrunkAsc = {"m³", "kWh", "kWh", "kWh", "kWh", "kWh", "kg", "m³"};
		
		if(boolean14) {
		
			sortedValues = sortAscRollUPValues();

			softAssert.assertEquals(sortedValues.toArray(), rollUpValuesAsc, "RollUP Values Are Sorted Successfully");
		} else {
			sortedValues = sortAscRollUPValues();

			softAssert.assertEquals(sortedValues.toArray(), rollUpValuesTrunkAsc, "RollUP Values Are Sorted Successfully");
		}
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("All the columns can be sorted (in ascending or descending order) is successfully verified", true);
	}
	
	/**
	 * Test Case ID: C70406
	 * Author : SSU
	 * @throws IOException
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testOrderOfCommodityClasses() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Order of the Commodity Classes in the different places: C70406" + " </span><br>",
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testOrderOfCommodityClasses");
		
		Boolean build14 = driver.getCurrentUrl().contains("14");

		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(20);
		
		//Metrics Commodity Class Values
		
		openConfigurationEntity("Metrics");		
		
		waitForExtJSAjaxComplete(20);
		
		MetricsPageObject metricsPO = new MetricsPageObject();
		
		metricsPO.clickAddButton();
		
		waitForExtJSAjaxComplete(10);
		
		if(build14) {
		
			String []commodityClassValue14 = {"Total Energy", "Electricity", "Combustibles", "Gas", "Fuel", "Heat", "Water", "Waste"};
			
			List<String> commodityList = getEquivalencyValues("slnmMetricID", "commoditySuperClass");
	
			softAssert.assertEqualsNoOrder(commodityList.toArray(), commodityClassValue14, "Commodity Classes are shown in the following order");
			
			waitForExtJSAjaxComplete(10);
		} else {
			
			String []commodityClassValueTrunk = {"Total Energy", "Electricity", "Combustibles", "Gas", "Fuel", "Heat", "Cooling", "Water", "Compressed Air", "Waste"};
			
			List<String> commodityListTrunk = getEquivalencyValues("slnmMetricID", "commoditySuperClass");
	
			softAssert.assertEqualsNoOrder(commodityListTrunk.toArray(), commodityClassValueTrunk, "Commodity Classes are shown in the following order");
			
			waitForExtJSAjaxComplete(10);
		}
		
		close("slnmMetricID");
		
		waitForExtJSAjaxComplete(10);
		
		//Tariff Calendar Commodity Class Values
		
		openConfigurationEntity("Tariff Calendars");		
		
		waitForExtJSAjaxComplete(20);
		
		String []commodityClassValueTrunk = {"Electricity", "Fuel", "Gas",  "Heat", "Waste", "Cooling", "Compressed Air", "Water"};
		
		TariffCalendarsPageObject tariffCalendarPO = new TariffCalendarsPageObject();
		
		tariffCalendarPO.clickAddButton();
		
		waitForExtJSAjaxComplete(10);
		
		if(build14) {
		
			String []commodityClassValue14 = {"Electricity", "Water", "Gas", "Fuel", "Waste", "Heat"};
			
			List<String> commodityList = getEquivalencyValues("slnmTrfId", "commodityClassId");
	
			softAssert.assertEqualsNoOrder(commodityList.toArray(), commodityClassValue14, "Commodity Classes are shown in the following order");
			
			waitForExtJSAjaxComplete(10);
		} else {
			
			List<String> commodityListTrunk = getEquivalencyValues("slnmTrfId", "commodityClassId");
	
			softAssert.assertEqualsNoOrder(commodityListTrunk.toArray(), commodityClassValueTrunk, "Commodity Classes are shown in the following order");
			
			waitForExtJSAjaxComplete(10);
		}
		
		close("slnmTrfId");
		
		waitForExtJSAjaxComplete(10);
		
		//Energy Price Templates Only For Trunk
		
		if(!build14){
			openConfigurationEntity("Energy Price Templates");		
			
			waitForExtJSAjaxComplete(20);
			
			ConfigurationPriceTemplatesPageObject priceTemplatePO = new ConfigurationPriceTemplatesPageObject();
			
			priceTemplatePO.clickAddButton();
			
			waitForExtJSAjaxComplete(10);
			
			List<String> commodityListTrunk = getEquivalencyValues("slnmNrgPrcTplId", "commodityClass");
	
			softAssert.assertEqualsNoOrder(commodityListTrunk.toArray(), commodityClassValueTrunk, "Commodity Classes are shown in the following order");
			
			waitForExtJSAjaxComplete(10);
			
			close("slnmNrgPrcTplId");
			
			waitForExtJSAjaxComplete(10);
		}
		
		String locationType = "Energy Object";
		String locationType14 = "Energy object";
		String location = "slnmMetricsEO1";
		
		OccurrencePageObject occurencePO = new OccurrencePageObject();
		
		occurencePO.expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		occurencePO.setLocationValueTreeLookup(location);
		
		waitForExtJSAjaxComplete(25);
		
		String panelID = "";
		
		if(build14){
			getXPanelId(locationType14 + " \"" +location + "\"");
		
			System.out.println("panelID" +panelID);
		}else{
			getXPanelId(locationType + " \"" +location + "\"");
			
			System.out.println("panelID" +panelID);
		}
		
		waitForExtJSAjaxComplete(25);
		
		occurencePO.changeTab(panelID, "Energy Analysis");
		
		waitForExtJSAjaxComplete(25);
		
		EnergyAnalysisPageObject energyAnalysisPO = new EnergyAnalysisPageObject();
		
		List<String> commodityList = energyAnalysisPO.getEquivalencyValues("commodityClass");
		
		if(build14) {
			
			String []commodityClassValue14 = {"Total Energy", "Electricity", "Combustibles", "Gas", "Fuel", "Heat", "Water", "Waste"};
			
			softAssert.assertEqualsNoOrder(commodityList.toArray(), commodityClassValue14, "Commodity Classes are shown in the following order");
			
			waitForExtJSAjaxComplete(10);
		} else {
			
			softAssert.assertEqualsNoOrder(commodityList.toArray(), commodityClassValueTrunk, "Commodity Classes are shown in the following order");
			
			waitForExtJSAjaxComplete(10);
		}
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertAll();
		
		Reporter.log("Order of the Commodity Classes in the different places is successfully verified", true);
	}
}
