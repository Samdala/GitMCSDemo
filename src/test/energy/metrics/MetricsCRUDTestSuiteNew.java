package test.energy.metrics;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.webelement.Grid;

public class MetricsCRUDTestSuiteNew extends MetricsPageObject {
	
	@Test(enabled=true)
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
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		openConfigurationEntity("Metrics");

		waitForExtJSAjaxComplete(20);
		
		clickButtonOnOverview("Add");
		
		setFieldValue("reference", reference);
		
		setDropdownValue("dimension", dimension);
		
		setDropdownValue("commoditySuperClass", commodityClass);
		
		setDropdownValue("commodityEndUse", commodityEndUse);
		
		setLookupValue("nominatorUom", nominatorUom);
		
		setDropdownValue("denominator", parameter);
		
		setLookupValue("denominatorUom", denominatorUom);
		
		saveAndClose();
		
		Grid.checkRowInGriByTextValueAndClick(driver, METRIC_GRID, reference);
		
		clickButtonOnOverview("Edit");
		
		verifyFieldSaved("reference", reference);
		
		verifyFieldSaved("dimension", dimension);
		
		verifyFieldSaved("commoditySuperClass", commodityClass);
		
		verifyFieldSaved("commodityEndUse", commodityEndUse);
		
		verifyFieldSaved("nominatorUom", nominatorUom);
		
		verifyFieldSaved("denominator", parameter);
		
		verifyFieldSaved("denominatorUom", denominatorUom);
		
		setFieldValue("reference", referenceEdited);
		
		if(build14){
			setDropdownValue("dimension", dimensionEdited);
		} else{
			setDropdownValue("dimension", dimensionEditedTrunk);
		}
		
		setDropdownValue("commoditySuperClass", commodityClassEdited);
		
		setLookupValue("nominatorUom", nominatorUomEdited);
		
		setDropdownValue("denominator", parameterEdited);
		
		setLookupValue("denominatorUom", denominatorUomEdited);
		
		saveAndClose();
		
		Grid.checkRowInGriByTextValueAndClick(driver, METRIC_GRID, referenceEdited);
		
		clickEditButton();
		
		verifyFieldSaved("reference", referenceEdited);
		
		if(build14){
			verifyFieldSaved("dimension", dimensionEdited);
		} else{
			verifyFieldSaved("dimension", dimensionEditedTrunk);
		}
		
		verifyFieldSaved("commoditySuperClass", commodityClassEdited);
		
		verifyFieldSaved("nominatorUom", nominatorUomEdited);
		
		verifyFieldSaved("denominator", parameterEdited);
		
		verifyFieldSaved("denominatorUom", denominatorUomEdited);
		
		this.softAssert.assertAll();
		
	}
	
	@Test(enabled=true)
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
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		openConfigurationEntity("Metrics");

		waitForExtJSAjaxComplete(20);
		
		clickButtonOnOverview("Add");		
		
		setFieldValue("reference", reference);
		
		setDropdownValue("dimension", dimension);
		
		setDropdownValue("commoditySuperClass", commodityClass);
		
		setLookupValue("nominatorUom", nominatorUom);
		
		setDropdownValue("denominator", parameter);
		
		setLookupValue("denominatorUom", denominatorUom);
		
		saveAndClose();
		
		Grid.checkRowInGriByTextValueAndClick(driver, METRIC_GRID, reference);
		
		waitForExtJSAjaxComplete(20);
		
		clickButtonOnOverview("Edit");		
		
		verifyFieldSaved("reference", reference);
		
		verifyFieldSaved("dimension", dimension);
		
		verifyFieldSaved("commoditySuperClass", commodityClass);
		
		verifyFieldSaved("nominatorUom", nominatorUom);
		
		verifyFieldSaved("denominator", parameter);
		
		verifyFieldSaved("denominatorUom", denominatorUom);
		
		this.softAssert.assertAll();
		
	}
	
	@Test(enabled=true)
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
		
		clickButtonOnOverview("Add");
		
		setFieldValue("reference", reference);
		
		saveAndClose();
		
		Grid.checkRowInGriByTextValueAndClick(driver, METRIC_GRID, reference);
		
		clickButtonOnOverview("Delete");
		
		clickButtonOnDialog("Yes");
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, reference, METRIC_GRID, "div"), "Was Metric deleted?");
		
	}

}
