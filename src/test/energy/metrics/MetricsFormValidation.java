package test.energy.metrics;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.McsElement;

public class MetricsFormValidation extends MetricsPageObject {
	
	@Test(enabled=true)
	public void testMetricsAddEdit() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Metrics Form Validations: ???????? </span><br>",
				true);

		Reporter.log("User tries to create Metric with different validations <br>",
				true);
		
		String lookupId = null;
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("MetricAddEdit");
		
		Boolean build14 = driver.getCurrentUrl().contains("14");
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		openConfigurationEntity("Metrics");

		waitForExtJSAjaxComplete(20);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		
		////////////////Check input UI style////////////////
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_FORM_WINDOW_XPATH, "reference"), "Reference field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_FORM_WINDOW_XPATH, "dimension"), "Dimesnion field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_FORM_WINDOW_XPATH, "commoditySuperClass"), "Commodity Class field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_FORM_WINDOW_XPATH, "commodityEndUse"), "Commodity End Use field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_FORM_WINDOW_XPATH, "nominatorUom"), "Nominator UOM field has correct UI (Bold text, asterix)");
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_FORM_WINDOW_XPATH, "denominatorUom"), "Denominator UOM field has correct UI (Bold text, asterix)");
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_FORM_WINDOW_XPATH, "denominator"), "Parameter field has correct UI (optional)");
		
		
		
		//////////////////////Verify drop-downs items presence with Usage Dimension//////////////
		setDimension("Usage");
		
		softAssert.assertTrue(verifyItemExists("dimension", "Usage"), "Usage item is present in Dimension dropdown");
		
		if(build14){
			softAssert.assertTrue(verifyItemExists("dimension", "CO2 emissions"), "CO2 emissions item is present in Dimension dropdown");
		} else{
			softAssert.assertTrue(verifyItemExists("dimension", "CO2 Emissions"), "CO2 Emissions item is present in Dimension dropdown");
		}
		
		softAssert.assertTrue(verifyItemExists("dimension", "Primary Energy"), "Primary Energy item is present in Dimension dropdown");
		
		
		
		softAssert.assertTrue(verifyItemExists("commoditySuperClass", "Total Energy"), "Total Energy item is present in Commodity Class dropdown");
		
		softAssert.assertTrue(verifyItemExists("commoditySuperClass", "Electricity"), "Electricity item is present in Commodity Class dropdown");
		
		softAssert.assertTrue(verifyItemExists("commoditySuperClass", "Combustibles"), "Combustibles item is present in Commodity Class dropdown");
		
		softAssert.assertTrue(verifyItemExists("commoditySuperClass", "Gas"), "Gas item is present in Commodity Class dropdown");
		
		softAssert.assertTrue(verifyItemExists("commoditySuperClass", "Fuel"), "Fuel item is present in Commodity Class dropdown");
		
		softAssert.assertTrue(verifyItemExists("commoditySuperClass", "Heat"), "Heat item is present in Commodity Class dropdown");
		
		softAssert.assertTrue(verifyItemExists("commoditySuperClass", "Water"), "Water item is present in Commodity Class dropdown");
		
		
		
		
		softAssert.assertTrue(verifyItemExists("commodityEndUse", "Total"), "Total item is present in Commodity End Use dropdown");
		
		softAssert.assertTrue(verifyItemExists("commodityEndUse", "Lighting"), "Lighting item is present in Commodity End Use dropdown");
		
		softAssert.assertTrue(verifyItemExists("commodityEndUse", "Space Cooling"), "Space Cooling item is present in Commodity End Use dropdown");
		
		softAssert.assertTrue(verifyItemExists("commodityEndUse", "Space Heating"), "Space Heating item is present in Commodity End Use dropdown");
		
		softAssert.assertTrue(verifyItemExists("commodityEndUse", "Ventilation"), "Ventilation item is present in Commodity End Use dropdown");
		
		softAssert.assertTrue(verifyItemExists("commodityEndUse", "Sanitary Hot Water"), "Sanitary Hot Water item is present in Commodity End Use dropdown");
		
		softAssert.assertTrue(verifyItemExists("commodityEndUse", "IT Servers"), "IT Servers item is present in Commodity End Use dropdown");
		
		softAssert.assertTrue(verifyItemExists("commodityEndUse", "Refrigeration"), "Refrigeration item is present in Commodity End Use dropdown");
		
		softAssert.assertTrue(verifyItemExists("commodityEndUse", "Cooking"), "Cooking item is present in Commodity End Use dropdown");
		
		softAssert.assertTrue(verifyItemExists("commodityEndUse", "Office Equipment"), "Office Equipment item is present in Commodity End Use dropdown");
		
		softAssert.assertTrue(verifyItemExists("commodityEndUse", "Miscellaneous"), "Miscellaneous item is present in Commodity End Use dropdown");
		
		
		
		softAssert.assertTrue(verifyItemExists("denominator", "Occupancy Hours"), "Occupancy Hours item is present in Parameter dropdown");
		
		softAssert.assertTrue(verifyItemExists("denominator", "Building Area"), "Building Area item is present in Parameter dropdown");
		
		softAssert.assertTrue(verifyItemExists("denominator", "Building Headcount"), "Building Headcount item is present in Parameter dropdown");
		
		softAssert.assertTrue(verifyItemExists("denominator", "Building Volume"), "Building Volume item is present in Parameter dropdown");
		
		softAssert.assertTrue(verifyItemExists("denominator", "preGaugeParameter1"), "preGaugeParameter1 item is present in Parameter dropdown");
		
		
		
		//////////////////////Verify drop-downs items presence with CO2 emissions Dimension//////////////
		if(build14){
			setDimension("CO2 emissions");
		} else{
			setDimension("CO2 Emissions");
		}
		
		softAssert.assertTrue(verifyItemExists("commoditySuperClass", "Total Energy"), "Total Energy item is present in Commodity Class dropdown");
		
		softAssert.assertTrue(verifyItemExists("commoditySuperClass", "Electricity"), "Electricity item is present in Commodity Class dropdown");
		
		softAssert.assertTrue(verifyItemExists("commoditySuperClass", "Combustibles"), "Combustibles item is present in Commodity Class dropdown");
		
		softAssert.assertTrue(verifyItemExists("commoditySuperClass", "Gas"), "Gas item is present in Commodity Class dropdown");
		
		softAssert.assertTrue(verifyItemExists("commoditySuperClass", "Fuel"), "Fuel item is present in Commodity Class dropdown");
		
		softAssert.assertTrue(verifyItemExists("commoditySuperClass", "Heat"), "Heat item is present in Commodity Class dropdown");
		
		softAssert.assertFalse(verifyItemExists("commoditySuperClass", "Water"), "Water item is abscent in Commodity Class dropdown when Dimension is CO2 emissions");
		
		softAssert.assertTrue(McsElement.checkInputDisabled(driver, "@class", ADD_FORM_WINDOW_XPATH, "commodityEndUse"), "Commodity End Use dropdown is disabled or read-only when Dimension is CO2 emissions");
		
		
		
		//////////////////////Verify drop-downs items presence with Primary Energy Dimension//////////////
		setDimension("Primary Energy");
		
		softAssert.assertTrue(verifyItemExists("commoditySuperClass", "Total Energy"), "Total Energy item is present in Commodity Class dropdown");
		
		softAssert.assertTrue(verifyItemExists("commoditySuperClass", "Electricity"), "Electricity item is present in Commodity Class dropdown");
		
		softAssert.assertTrue(verifyItemExists("commoditySuperClass", "Combustibles"), "Combustibles item is present in Commodity Class dropdown");
		
		softAssert.assertTrue(verifyItemExists("commoditySuperClass", "Gas"), "Gas item is present in Commodity Class dropdown");
		
		softAssert.assertTrue(verifyItemExists("commoditySuperClass", "Fuel"), "Fuel item is present in Commodity Class dropdown");
		
		softAssert.assertTrue(verifyItemExists("commoditySuperClass", "Heat"), "Heat item is present in Commodity Class dropdown");
		
		softAssert.assertTrue(verifyItemExists("commoditySuperClass", "Water"), "Water item is abscent in Commodity Class dropdown when Dimension is CO2 emissions");
		
		softAssert.assertTrue(McsElement.checkInputDisabled(driver, "@class", ADD_FORM_WINDOW_XPATH, "commodityEndUse"), "Commodity End Use dropdown is disabled or read-only when Dimension is Primary Energy");
		
		
		
		///////////////////CO2 emissions Dimension -  Nominator UOM Class - Mass///////////////////////
		
		if(build14){
			setDimension("CO2 emissions");
		} else{
			setDimension("CO2 Emissions");
		}
		
		waitForExtJSAjaxComplete(20);
		
		clickLookup(ADD_FORM_WINDOW_XPATH, "nominatorUom");
		
		waitForExtJSAjaxComplete(20);
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		Reporter.log("Check if Nominator UOM lookup is limited by Mass Commodity Class", true);
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "kWh", "Code"), "Equivalency UOM lookup is not limited, kilogram UOM is present in lookup");//Energy
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "cl", "Code"), "Equivalency UOM lookup is not limited, hectare UOM is present in lookup");//Volume
		
		softAssert.assertTrue(filterGridAndSearchForValue("@id", lookupId, "kg", "Code"), "Equivalency UOM lookup is not limited, hectare UOM is present in lookup");//Mass
				
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		
		
		///////////////Usage Dimension -  Nominator UOM Class limitation depending on Commodity Class selected
		setDimension("Usage");
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityClass("Total Energy");
		
		waitForExtJSAjaxComplete(20);
		
//		softAssert.assertTrue(isDataLabelPresent("Usage of Total Energy  (kWh)"), "Data Label is correct?");
		
		clickLookup(ADD_FORM_WINDOW_XPATH, "nominatorUom");
		
		waitForExtJSAjaxComplete(20);
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		Reporter.log("Check if Nominator UOM lookup is limited by Energy Commodity Class", true);
		
		softAssert.assertTrue(filterGridAndSearchForValue("@id", lookupId, "kWh", "Code"), "Equivalency UOM lookup is not limited, kilogram UOM is present in lookup");//Energy
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "cl", "Code"), "Equivalency UOM lookup is not limited, hectare UOM is present in lookup");//Volume
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "kg", "Code"), "Equivalency UOM lookup is not limited, hectare UOM is present in lookup");//Mass
				
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);
		
//		softAssert.assertTrue(isDataLabelPresent("Usage of Electricity  (kWh)"), "Data Label is correct?");
		
		clickLookup(ADD_FORM_WINDOW_XPATH, "nominatorUom");
		
		waitForExtJSAjaxComplete(20);
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		Reporter.log("Check if Nominator UOM lookup is limited by Energy Commodity Class", true);
		
		softAssert.assertTrue(filterGridAndSearchForValue("@id", lookupId, "kWh", "Code"), "Equivalency UOM lookup is not limited, kilogram UOM is present in lookup");//Energy
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "cl", "Code"), "Equivalency UOM lookup is not limited, hectare UOM is present in lookup");//Volume
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "kg", "Code"), "Equivalency UOM lookup is not limited, hectare UOM is present in lookup");//Mass
				
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		
		
		setCommodityClass("Combustibles");
		
		waitForExtJSAjaxComplete(20);
		
//		softAssert.assertTrue(isDataLabelPresent("Usage of Combustibles  (kWh)"), "Data Label is correct?");
		
		clickLookup(ADD_FORM_WINDOW_XPATH, "nominatorUom");
		
		waitForExtJSAjaxComplete(20);
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		Reporter.log("Check if Nominator UOM lookup is limited by Energy Commodity Class", true);
		
		softAssert.assertTrue(filterGridAndSearchForValue("@id", lookupId, "kWh", "Code"), "Equivalency UOM lookup is not limited, kilogram UOM is present in lookup");//Energy
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "cl", "Code"), "Equivalency UOM lookup is not limited, hectare UOM is present in lookup");//Volume
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "kg", "Code"), "Equivalency UOM lookup is not limited, hectare UOM is present in lookup");//Mass
				
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		
		
		setCommodityClass("Gas");
		
		waitForExtJSAjaxComplete(20);
		
//		softAssert.assertTrue(isDataLabelPresent("Usage of Gas  (kWh)"), "Data Label is correct?");
		
		clickLookup(ADD_FORM_WINDOW_XPATH, "nominatorUom");
		
		waitForExtJSAjaxComplete(20);
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		Reporter.log("Check if Nominator UOM lookup is limited by Energy Commodity Class", true);
		
		softAssert.assertTrue(filterGridAndSearchForValue("@id", lookupId, "kWh", "Code"), "Equivalency UOM lookup is not limited, kilogram UOM is present in lookup");//Energy
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "cl", "Code"), "Equivalency UOM lookup is not limited, hectare UOM is present in lookup");//Volume
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "kg", "Code"), "Equivalency UOM lookup is not limited, hectare UOM is present in lookup");//Mass
				
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		
		
		setCommodityClass("Fuel");
		
		waitForExtJSAjaxComplete(20);
		
//		softAssert.assertTrue(isDataLabelPresent("Usage of Fuel  (kWh)"), "Data Label is correct?");
		
		clickLookup(ADD_FORM_WINDOW_XPATH, "nominatorUom");
		
		waitForExtJSAjaxComplete(20);
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		Reporter.log("Check if Nominator UOM lookup is limited by Energy Commodity Class", true);
		
		softAssert.assertTrue(filterGridAndSearchForValue("@id", lookupId, "kWh", "Code"), "Equivalency UOM lookup is not limited, kilogram UOM is present in lookup");//Energy
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "cl", "Code"), "Equivalency UOM lookup is not limited, hectare UOM is present in lookup");//Volume
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "kg", "Code"), "Equivalency UOM lookup is not limited, hectare UOM is present in lookup");//Mass
				
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		
		
		setCommodityClass("Heat");
		
		waitForExtJSAjaxComplete(20);
		
//		softAssert.assertTrue(isDataLabelPresent("Usage of Heat  (kWh)"), "Data Label is correct?");
		
		clickLookup(ADD_FORM_WINDOW_XPATH, "nominatorUom");
		
		waitForExtJSAjaxComplete(20);
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		Reporter.log("Check if Nominator UOM lookup is limited by Energy Commodity Class", true);
		
		softAssert.assertTrue(filterGridAndSearchForValue("@id", lookupId, "kWh", "Code"), "Equivalency UOM lookup is not limited, kilogram UOM is present in lookup");//Energy
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "cl", "Code"), "Equivalency UOM lookup is not limited, hectare UOM is present in lookup");//Volume
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "kg", "Code"), "Equivalency UOM lookup is not limited, hectare UOM is present in lookup");//Mass
				
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		
		
		setCommodityClass("Water");
		
		waitForExtJSAjaxComplete(20);
		
//		softAssert.assertTrue(isDataLabelPresent("Usage of Water"), "Data Label is correct?");
		
		clickLookup(ADD_FORM_WINDOW_XPATH, "nominatorUom");
		
		waitForExtJSAjaxComplete(20);
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		Reporter.log("Check if Nominator UOM lookup is limited by Energy Commodity Class", true);
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "kWh", "Code"), "Equivalency UOM lookup is not limited, kilogram UOM is present in lookup");//Energy
		
		softAssert.assertTrue(filterGridAndSearchForValue("@id", lookupId, "cl", "Code"), "Equivalency UOM lookup is not limited, hectare UOM is present in lookup");//Volume
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "kg", "Code"), "Equivalency UOM lookup is not limited, hectare UOM is present in lookup");//Mass
				
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		
		
		setParameter("Building Area");
		
		waitForExtJSAjaxComplete(20);
		
//		softAssert.assertTrue(isDataLabelPresent("Usage of Water (m³) per Building Area (m²)"), "Data Label is correct?");
		
		clickLookup(ADD_FORM_WINDOW_XPATH, "denominatorUom");
		
		waitForExtJSAjaxComplete(20);
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		Reporter.log("Check if Nominator UOM lookup is limited by Energy Commodity Class", true);
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "gro", "Code"), "Building Headcount");//Building Headcount
		
		softAssert.assertTrue(filterGridAndSearchForValue("@id", lookupId, "sqft", "Code"), "Building Area");//Building Area
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "cl", "Code"), "Building Volume");//Building Volume
				
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		
		
		setParameter("Building Headcount");
		
		waitForExtJSAjaxComplete(20);
		
		clickLookup(ADD_FORM_WINDOW_XPATH, "denominatorUom");
		
		waitForExtJSAjaxComplete(20);
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		Reporter.log("Check if Nominator UOM lookup is limited by Energy Commodity Class", true);
		
		softAssert.assertTrue(filterGridAndSearchForValue("@id", lookupId, "gro", "Code"), "Building Headcount");//Building Headcount
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "sqft", "Code"), "Building Area");//Building Area
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "cl", "Code"), "Building Volume");//Building Volume
				
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		
		
		setParameter("Building Volume");
		
		waitForExtJSAjaxComplete(20);
		
		clickLookup(ADD_FORM_WINDOW_XPATH, "denominatorUom");
		
		waitForExtJSAjaxComplete(20);
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		Reporter.log("Check if Nominator UOM lookup is limited by Energy Commodity Class", true);
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "gro", "Code"), "Building Headcount");//Building Headcount
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "sqft", "Code"), "Building Area");//Building Area
		
		softAssert.assertTrue(filterGridAndSearchForValue("@id", lookupId, "cl", "Code"), "Building Volume");//Building Volume
				
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		softAssert.assertAll();
		
	}

	/**
	 * Test Case ID : C60734
	 * Author : SSU
	 * @throws Exception
	 */
	//TODO MEtricsCRUDTEStsuite too committ
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testBuiltInMetrics() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60734: Built-in metrics </span><br>", true);
		
		String electrictiyArea = "Electricity Usage per Building Area";
		String electricityVolume = "Electricity Usage per Building Volume";
		String waterArea = "Water Usage per Building Area";
		String waterVolume = "Water Usage per Building Volume";
		String combustibleArea = "Combustibles Usage per Building Area";
		String combustibleVolume = "Combustibles Usage per Building Volume";
		
		String dimensionUsage = "Usage";
		String commodityClass = "Electricity";
		String combustibleCommodity = "Combustibles";
		String waterCommodity = "Water";
		String commodityEndUse = "Total";
		String kWhUOM = "kWh";
		String areaParameter = "Building Area";
		String meterUOM = "m²";
		String volumeParameter = "Building Volume";
		String meterCubeUOM = "m³";
		
		Boolean build14 = driver.getCurrentUrl().contains("14");
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testBuiltInMetrics");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		openConfigurationEntity("Metrics");

		waitForExtJSAjaxComplete(20);
		
		//Electricity Usage per Building Area Verification
		
		filterGrid("@class", METRIC_GRID, electrictiyArea, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		openTransactionLine("@class", METRIC_GRID, electrictiyArea, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getReference("value"), electrictiyArea, electrictiyArea+"  is available in Reference");
		
		softAssert.assertEquals(getDimension("value"), dimensionUsage, dimensionUsage+"  is available in Dimension");
		
		softAssert.assertEquals(getCommodityClass("value"), commodityClass, commodityClass+"  is available in Commodity Class");
		
		softAssert.assertEquals(getCommodityEndUse("value"), commodityEndUse, commodityEndUse+"  is available in Commodity End use");
		
		softAssert.assertEquals(getNominatorUOM("value"), kWhUOM, kWhUOM+"  is available in Nominator UOM");
		
		softAssert.assertEquals(getParameter("value"), areaParameter, areaParameter+"  is available in Area Parameter");
	
		softAssert.assertEquals(getDenominatorUOM("value"), meterUOM, meterUOM+"  is available in Denominator UOM");
		
		waitForExtJSAjaxComplete(10);
		
		//Verify Editablity of Fields
		
		verifyEditablityOfFields();
		
		waitForExtJSAjaxComplete(10);
		
		close();
		
		waitForExtJSAjaxComplete(10);
		
		//Electricity Usage per Building Volume Verification
		
		filterGrid("@class", METRIC_GRID, electricityVolume, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		openTransactionLine("@class", METRIC_GRID, electricityVolume, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getReference("value"), electricityVolume, electricityVolume+"  is available in Reference");
		
		softAssert.assertEquals(getDimension("value"), dimensionUsage, dimensionUsage+"  is available in Dimension");
		
		softAssert.assertEquals(getCommodityClass("value"), commodityClass, commodityClass+"  is available in Commodity Class");
		
		softAssert.assertEquals(getCommodityEndUse("value"), commodityEndUse, commodityEndUse+"  is available in Commodity End use");
		
		softAssert.assertEquals(getNominatorUOM("value"), kWhUOM, kWhUOM+"  is available in Nominator UOM");
		
		softAssert.assertEquals(getParameter("value"), volumeParameter, volumeParameter+"  is available in Volume Parameter");
	
		softAssert.assertEquals(getDenominatorUOM("value"), meterCubeUOM, meterCubeUOM+"  is available in Denominator UOM");
		
		waitForExtJSAjaxComplete(10);
		
		//Verify Editablity of Fields
		
		verifyEditablityOfFields();
		
		waitForExtJSAjaxComplete(10);
		
		close();
		
		waitForExtJSAjaxComplete(10);
		
		//Combustibles Usage per Building Area Verification
		
		filterGrid("@class", METRIC_GRID, combustibleArea, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		openTransactionLine("@class", METRIC_GRID, combustibleArea, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getReference("value"), combustibleArea, combustibleArea+"  is available in Reference");
		
		softAssert.assertEquals(getDimension("value"), dimensionUsage, dimensionUsage+"  is available in Dimension");
		
		softAssert.assertEquals(getCommodityClass("value"), combustibleCommodity, combustibleCommodity+"  is available in Commodity Class");
		
		softAssert.assertEquals(getCommodityEndUse("value"), commodityEndUse, commodityEndUse+"  is available in Commodity End use");
		
		softAssert.assertEquals(getNominatorUOM("value"), kWhUOM, kWhUOM+"  is available in Nominator UOM");
		
		softAssert.assertEquals(getParameter("value"), areaParameter, areaParameter+"  is available in Area Parameter");
	
		softAssert.assertEquals(getDenominatorUOM("value"), meterUOM, meterUOM+"  is available in Denominator UOM");
		
		waitForExtJSAjaxComplete(10);
		
		//Verify Editablity of Fields
		
		verifyEditablityOfFields();
		
		waitForExtJSAjaxComplete(10);
		
		close();
		
		waitForExtJSAjaxComplete(10);
		
		//Combustibles Usage per Building Volume Verification
		
		filterGrid("@class", METRIC_GRID, combustibleVolume, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		openTransactionLine("@class", METRIC_GRID, combustibleVolume, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getReference("value"), combustibleVolume, combustibleVolume+"  is available in Reference");
		
		softAssert.assertEquals(getDimension("value"), dimensionUsage, dimensionUsage+"  is available in Dimension");
		
		softAssert.assertEquals(getCommodityClass("value"), combustibleCommodity, combustibleCommodity+"  is available in Commodity Class");
		
		softAssert.assertEquals(getCommodityEndUse("value"), commodityEndUse, commodityEndUse+"  is available in Commodity End use");
		
		softAssert.assertEquals(getNominatorUOM("value"), kWhUOM, kWhUOM+"  is available in Nominator UOM");
		
		softAssert.assertEquals(getParameter("value"), volumeParameter, volumeParameter+"  is available in Volume Parameter");
	
		softAssert.assertEquals(getDenominatorUOM("value"), meterCubeUOM, meterCubeUOM+"  is available in Denominator UOM");
		
		waitForExtJSAjaxComplete(10);
		
		//Verify Editablity of Fields
		
		verifyEditablityOfFields();
				
		waitForExtJSAjaxComplete(10);
		
		
		close();
		
		waitForExtJSAjaxComplete(10);
		
		//Water Usage per Building Area Verification
		
		filterGrid("@class", METRIC_GRID, waterArea, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		openTransactionLine("@class", METRIC_GRID, waterArea, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getReference("value"), waterArea, waterArea+"  is available in Reference");
		
		softAssert.assertEquals(getDimension("value"), dimensionUsage, dimensionUsage+"  is available in Dimension");
		
		softAssert.assertEquals(getCommodityClass("value"), waterCommodity, waterCommodity+"  is available in Commodity Class");
		
		softAssert.assertEquals(getCommodityEndUse("value"), commodityEndUse, commodityEndUse+"  is available in Commodity End use");
		
		softAssert.assertEquals(getNominatorUOM("value"), meterCubeUOM, meterCubeUOM+"  is available in Nominator UOM");
		
		softAssert.assertEquals(getParameter("value"), areaParameter, areaParameter+"  is available in Area Parameter");
	
		softAssert.assertEquals(getDenominatorUOM("value"), meterUOM, meterUOM+"  is available in Denominator UOM");
		
		waitForExtJSAjaxComplete(10);
		
		//Verify Editablity of Fields
		
		verifyEditablityOfFields();
				
		waitForExtJSAjaxComplete(10);
		
		close();
		
		waitForExtJSAjaxComplete(10);
		
		//Water Usage per Building Volume Verification
		
		filterGrid("@class", METRIC_GRID, waterVolume, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		openTransactionLine("@class", METRIC_GRID, waterVolume, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getReference("value"), waterVolume, waterVolume+"  is available in Reference");
		
		softAssert.assertEquals(getDimension("value"), dimensionUsage, dimensionUsage+"  is available in Dimension");
		
		softAssert.assertEquals(getCommodityClass("value"), waterCommodity, waterCommodity+"  is available in Commodity Class");
		
		softAssert.assertEquals(getCommodityEndUse("value"), commodityEndUse, commodityEndUse+"  is available in Commodity End use");
		
		softAssert.assertEquals(getNominatorUOM("value"), meterCubeUOM, meterCubeUOM+"  is available in Nominator UOM");
		
		softAssert.assertEquals(getParameter("value"), volumeParameter, volumeParameter+"  is available in Volume Parameter");
	
		softAssert.assertEquals(getDenominatorUOM("value"), meterCubeUOM, meterCubeUOM+"  is available in Denominator UOM");
		
		waitForExtJSAjaxComplete(10);
		
		//Verify Editablity of Fields
		
		verifyEditablityOfFields();
				
		waitForExtJSAjaxComplete(10);
		
		close();
		
		waitForExtJSAjaxComplete(10);
		
		filterGrid("@class", METRIC_GRID, waterVolume, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		clickDeleteButton();
		
		waitForExtJSAjaxComplete(10);
		
		if(build14){
			softAssert.assertTrue(getExtMbContentOfWindow("@class", "x-window-dlg").contains("You cannot delete built-in metrics."), "User cannot delete Built In Metrics");
		} else{
			softAssert.assertTrue(getExtMbContentOfWindow("@class", "x-window-dlg").contains("You cannot delete the built-in metrics."), "User cannot delete Built In Metrics");
		}
		
		softAssert.assertAll();
		
		Reporter.log("Built-in metrics is successfully verified", true);
	}
}
