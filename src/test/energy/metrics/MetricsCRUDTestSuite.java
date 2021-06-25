package test.energy.metrics;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class MetricsCRUDTestSuite extends MetricsPageObject {
	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testMetricsAddEdit() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Add/Edit Metrics: ???????? </span><br>",
				true);

		Reporter.log("User add and edit Metric <br>",
				true);
		
		String reference = "test auto metric " + getCurrentDate().substring(getCurrentDate().length()-6);;
		String dimension = "Usage";
		String commodityClass = "Electricity";
		String commodityEndUse = "Lighting";
		String nominatorUom = "kcal";
		String parameter = "Building Volume";
		String denominatorUom = "cl";
		
		String referenceEdited = "test auto metric ed " + getCurrentDate().substring(getCurrentDate().length()-6);;
		String dimensionEdited = "CO2 emissions";
		String dimensionEditedTrunk = "CO2 Emissions";
		String commodityClassEdited = "Gas";
		String nominatorUomEdited = "mg";
		String parameterEdited = "Building Area";
		String denominatorUomEdited = "sqmi";
		
		Boolean build14 = driver.getCurrentUrl().contains("14");
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("MetricAddEdit");
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		openConfigurationEntity("Metrics");

		waitForExtJSAjaxComplete(20);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(reference);
		
		waitForExtJSAjaxComplete(20);
		
		setDimension(dimension);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityClass(commodityClass);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityEndUse(commodityEndUse);
		
		waitForExtJSAjaxComplete(20);
		
		setNominatorUOM(nominatorUom);
		
		waitForExtJSAjaxComplete(20);
		
		setParameter(parameter);
		
		waitForExtJSAjaxComplete(20);
		
		setDenominatorUOM(denominatorUom);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, METRIC_GRID, reference);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getReference("value"), reference, "Reference field value after creation is correct?");
		
		softAssert.assertEquals(getDimension("value"), dimension, "Dimension field value after creation is correct?");
		
		softAssert.assertEquals(getCommodityClass("value"), commodityClass, "Commodity Class field value after creation is correct?");
		
		softAssert.assertEquals(getCommodityEndUse("value"), commodityEndUse, "Commodity End Use field value after creation is correct?");
		
		softAssert.assertEquals(getNominatorUOM("value"), nominatorUom, "Nominator UOM field value after creation is correct?");
		
		softAssert.assertEquals(getParameter("value"), parameter, "Parameter field value after creation is correct?");
		
		softAssert.assertEquals(getDenominatorUOM("value"), denominatorUom, "Denominator UOM field value after creation is correct?");
		
		setReference(referenceEdited);
		
		waitForExtJSAjaxComplete(20);
		
		if(build14){
			setDimension(dimensionEdited);
		} else{
			setDimension(dimensionEditedTrunk);
		}
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityClass(commodityClassEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setNominatorUOM(nominatorUomEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setParameter(parameterEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setDenominatorUOM(denominatorUomEdited);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, METRIC_GRID, referenceEdited);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getReference("value"), referenceEdited, "Reference field value after creation is correct?");
		
		if(build14){
			softAssert.assertEquals(getDimension("value"), dimensionEdited, "Dimension field value after creation is correct?");
		} else{
			softAssert.assertEquals(getDimension("value"), dimensionEditedTrunk, "Dimension field value after creation is correct?");
		}
		
		
		softAssert.assertEquals(getCommodityClass("value"), commodityClassEdited, "Commodity Class field value after creation is correct?");
		
		softAssert.assertEquals(getNominatorUOM("value"), nominatorUomEdited, "Nominator UOM field value after creation is correct?");
		
		softAssert.assertEquals(getParameter("value"), parameterEdited, "Parameter field value after creation is correct?");
		
		softAssert.assertEquals(getDenominatorUOM("value"), denominatorUomEdited, "Denominator UOM field value after creation is correct?");
		
		softAssert.assertAll();
		
	}
	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testMetricsAddWithPrimaryEnergy() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Add Metrics with Primary Energy as Dimension: ???????? </span><br>",
				true);

		Reporter.log("User adds Metric with Primary Energy as Dimension<br>",
				true);
		
		String reference = "test auto metric " + getCurrentDate().substring(getCurrentDate().length()-6);;
		String dimension = "Primary Energy";
		String commodityClass = "Electricity";
		String nominatorUom = "kcal";
		String parameter = "Building Volume";
		String denominatorUom = "cl";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("MetricAddEdit");
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		openConfigurationEntity("Metrics");

		waitForExtJSAjaxComplete(20);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(reference);
		
		waitForExtJSAjaxComplete(20);
		
		setDimension(dimension);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityClass(commodityClass);
		
		waitForExtJSAjaxComplete(20);
		
		setNominatorUOM(nominatorUom);
		
		waitForExtJSAjaxComplete(20);
		
		setParameter(parameter);
		
		waitForExtJSAjaxComplete(20);
		
		setDenominatorUOM(denominatorUom);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, METRIC_GRID, reference);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getReference("value"), reference, "Reference field value after creation is correct?");
		
		softAssert.assertEquals(getDimension("value"), dimension, "Dimension field value after creation is correct?");
		
		softAssert.assertEquals(getCommodityClass("value"), commodityClass, "Commodity Class field value after creation is correct?");
		
		softAssert.assertEquals(getNominatorUOM("value"), nominatorUom, "Nominator UOM field value after creation is correct?");
		
		softAssert.assertEquals(getParameter("value"), parameter, "Parameter field value after creation is correct?");
		
		softAssert.assertEquals(getDenominatorUOM("value"), denominatorUom, "Denominator UOM field value after creation is correct?");
		
		softAssert.assertAll();
		
	}
	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testMetricsDelete() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Metrics: ???????? </span><br>",
				true);

		Reporter.log("User deletes Metric <br>",
				true);
		
		String reference = "test auto metric " + getCurrentDate().substring(getCurrentDate().length()-6);;
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		openConfigurationEntity("Metrics");

		waitForExtJSAjaxComplete(20);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(reference);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, METRIC_GRID, reference);
		
		waitForExtJSAjaxComplete(20);
		
		clickDeleteButton();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, reference, METRIC_GRID, "div"), "Was Metric deleted?");
		
	}

}
