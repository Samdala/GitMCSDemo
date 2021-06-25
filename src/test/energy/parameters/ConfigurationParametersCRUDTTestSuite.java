package test.energy.parameters;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.energy.gauges.MeteringGaugesChanelsPageObject;
import test.energy.gauges.MeteringGaugesPageObject;
import test.energy.weatherstations.MeteringWeatherstationChanelsActualDataPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.FileUploader;
import test.framework.webelement.Grid;

public class ConfigurationParametersCRUDTTestSuite extends 
		ConfigurationParametersPageObject {
	
	@DataProvider
	public Object[][] configuration() {
		return new Object[][] {
		{"Parameters", "channel_parameters_overview"}
		};
	}


	 @Test(enabled=true,dataProvider = "configuration")
	public void testConfigurationParametersAddEdit(String entity, String realId) throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Add/Edit Energy Configuration - Parameter: C22053</span><br>",
				true);

		Reporter.log("User add/edit Parameter <br>",
				true);
		
		Boolean build122 = driver.getCurrentUrl().contains("122");
		Boolean build14 = driver.getCurrentUrl().contains("14");
		
		String code = "AAUTO" + getCurrentDate().substring(getCurrentDate().length()-5); code = code.replace(".", "1");
		String name = "AAtest auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		String paramClass = "Gauge";
		String uomClass = "Area";
		String defaultUom = "hectare";
		String calculationMethod = "Latest Value";
		
		
		String codeEdited = "AMYAUTOED" + getCurrentDate().substring(getCurrentDate().length()-5); codeEdited = codeEdited.replace(".", "1");
		String nameEdited = "Atest auto ed" + getCurrentDate().substring(getCurrentDate().length()-5);
		
		String uomClassEdited = "Energy";
		String defaultUomEdited = "calorie";
		String calculationMethodEdited = "Sum";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("configurationParameters");

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		openConfigurationEntity(entity);

		waitForExtJSAjaxComplete(20);
		
		clickAddButton(realId);
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		setName(name);
		
		setParamClass(paramClass);
		
		waitForExtJSAjaxComplete(20);
		
		setUOMClass(uomClass);
		
		waitForExtJSAjaxComplete(20);
		
		setDefaultUOM(defaultUom);
		
		waitForExtJSAjaxComplete(20);
		
		if (!build122 && !build14) {
			setCalculationMethod(calculationMethod);
		}
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, code);
		
		clickEditButton(realId);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getCode(),code, "Compare Code after ceation");
		
		softAssert.assertEquals(getName(),name, "Compare Name after ceation");
		
		if (!build122 && !build14) {
			softAssert.assertEquals(getFieldValue("calculationMethod"),calculationMethod, "Compare calculationMethod after ceation");
		}
				
		setCode(codeEdited);
		
		setName(nameEdited);
		
		if (!build122 && !build14) {
			setCalculationMethod(calculationMethodEdited);
		}
		
//		setParamClass(paramClassEdited);
		
		setUOMClass(uomClassEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setDefaultUOM(defaultUomEdited);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();

		Grid.checkRowInGriByTextValueAndClick(driver, codeEdited);
		
		clickEditButton(realId);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getCode(),codeEdited, "Compare Code after editing");
		
		softAssert.assertEquals(getName(),nameEdited, "Compare Name after editing");
		
		if (!build122 && !build14) {
			softAssert.assertEquals(getFieldValue("calculationMethod"),calculationMethodEdited, "Compare calculationMethod after editing");
		}
		
		close();
		
		softAssert.assertAll();
		
		Reporter.log("Parameter was succesfully edited", true);

	}

	@Test(enabled = true, dataProvider = "configuration")
	public void testParameterDelete(String entity, String realId) throws IllegalStateException, IOException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Energy Configuration - Parameter: C22054</span><br>",
				true);

		Reporter.log("User deletes configuration - Parameter <br>",
				true);
		
		Boolean build122 = driver.getCurrentUrl().contains("122");
		Boolean build14 = driver.getCurrentUrl().contains("14");
		
		String code = "AMYAUTODEL" + getCurrentDate().substring(getCurrentDate().length()-5); code = code.replace(".", "1");
		String name = "Atest auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
		String paramClass = "Gauge";
		String uomClass = "Area";
		String defaultUom = "hectare";
		String calculationMethod = "Latest Value";

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		openConfigurationEntity(entity);

		waitForExtJSAjaxComplete(20);
		
		clickAddButton(realId);
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		setName(name);
		
		setParamClass(paramClass);
		
		waitForExtJSAjaxComplete(20);
		
		setUOMClass(uomClass);
		
		waitForExtJSAjaxComplete(20);
		
		setDefaultUOM(defaultUom);
		
		waitForExtJSAjaxComplete(20);
		
		if (!build122 && !build14) {
			setCalculationMethod(calculationMethod);
		}
		
		waitForExtJSAjaxComplete(20);

		saveClose();
		
		Grid.checkRowInGriByTextValueAndClick(driver, code);
		
		clickDeleteButton(realId);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, code), code+" code after delete is present");
		
		Reporter.log("Configuration " + entity + " was succesfully deleted", true);
		
	}

	/**
	 * Test Case ID: C60630
	 * Author : SSU
	 * @throws IOException
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class, dataProvider = "configuration")
	public void testGaugeDefaultUOMReadOnlyifChannelInUse(String entity, String realId) throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: For Gauge UOM class and Default UOM become read-only if Parameter is in use on Channel: C60630" + " </span><br>",
				true);
		
		String code = "C60630Cod" + getCurrentDate().substring(getCurrentDate().length()-5); code = code.replace(".", "1");
		String name = "C60630Ref" + getCurrentDate().substring(getCurrentDate().length()-5);
		String paramClass = "Gauge";
		String uomClass = "Time";
		String defaultUom = "second";
		String calculationMethod = "Latest Value";
		
		String channelReference = "C60630ChnlRef";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testGaugeDefaultUOMReadOnlyifChannelInUse");

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(25);
		
		openConfigurationEntity(entity);

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddButton(realId);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		setCode(code);
		
		setName(name);
		
		setParamClass(paramClass);
		
		waitForExtJSAjaxComplete(20);
		
		setUOMClass(uomClass);
		
		waitForExtJSAjaxComplete(20);
		
		setDefaultUOM(defaultUom);
		
		waitForExtJSAjaxComplete(20);
		
		setCalculationMethod(calculationMethod);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		//Filter PO Lines Grid with Line ID
		filterGrid("@realid", realId, name, "Name");
						
		waitForExtJSAjaxComplete(25);
		
		//Open the Transactions Grid Record
		openTransactionLine("@realid", realId, name, "Name");
				
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		String classValue = getClassValue("class");
		
		softAssert.assertTrue(classValue.contains("x-form-readonly x-trigger-noedit"), "Class Value is Read Only");
		
		waitForExtJSAjaxComplete(5);
		
		setUOMClass(uomClass);
		
		waitForExtJSAjaxComplete(20);
		
		setDefaultUOM(defaultUom);
		
		waitForExtJSAjaxComplete(20);
		
		close();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		waitForExtJSAjaxComplete(25);
		
		openAnalysisEntity("Gauges");

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		//Filter PO Lines Grid with Line ID TODO need change of value
		filterGridScrollIntoView("@class", GAUGE_GRID_CLASS, "slnmMetricsGauge", "Reference");
				
		waitForExtJSAjaxComplete(20);
		
		//Open the Transactions Grid Record
	//	openTransactionLine("@class", GAUGE_GRID_CLASS, "slnmMetricsGauge", "Reference");
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", GAUGE_GRID_CLASS, "slnmMetricsGauge");
						
		waitForExtJSAjaxComplete(20);
		
		MeteringGaugesPageObject meteringGaugesPO = new MeteringGaugesPageObject();
		
		meteringGaugesPO.clickEditButton(GAUGE_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		meteringGaugesPO.changeTab("notes");
		
		waitForExtJSAjaxComplete(20);
		
		meteringGaugesPO.changeTab("channels");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Add", "slnmGaugeId");
		
		waitForExtJSAjaxComplete(20);

		MeteringGaugesChanelsPageObject meteringGaugesChnlPO = new MeteringGaugesChanelsPageObject();
		
		meteringGaugesChnlPO.setReference(channelReference);
		
		waitForExtJSAjaxComplete(5);
		
		meteringGaugesChnlPO.setChannelParameter(name);
		
		waitForExtJSAjaxComplete(25);
		
		String uomValue = meteringGaugesChnlPO.getUnitOfMeasure();
		
		softAssert.assertEquals(uomValue, "s", "UOM field is prefilled with the default UOM");
		
		waitForExtJSAjaxComplete(5);
		
		saveClose("slnmGaugeChnlId");
		
		waitForExtJSAjaxComplete(25);
		
		close("slnmGaugeId");
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(20);
		
		openConfigurationEntity(entity);

		waitForExtJSAjaxComplete(20);
		
		//Filter PO Lines Grid with Line ID
		filterGrid("@realid", realId, name, "Name");
						
		//Open the Transactions Grid Record
		openTransactionLine("@realid", realId, name, "Name");
				
		waitForExtJSAjaxComplete(20);
		
		String uomClassValue = getFieldValue("uomClass", "class");
		
		softAssert.assertTrue(uomClassValue.contains("x-form-readonly x-trigger-noedit"), "uomClass Value is Read Only");
		
		String defaultUOMValue = getFieldValue("defaultUom", "class");
		
		softAssert.assertTrue(defaultUOMValue.contains("x-form-readonly x-trigger-noedit"), "defaultUOM Value is Read Only");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertAll();
		
		Reporter.log("For Gauge UOM class and Default UOM become read-only if Parameter is in use on Channel is successfully verified", true);
	}
	
	/**
	 * Test Case ID: C60632 & C60633
	 * Author : SSU
	 * @throws IOException
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class, dataProvider = "configuration")
	public void testWeatherStationDefaultUOMReadOnlyifChannelInUse(String entity, String realId) throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Weather Stations: UOM class and Default UOM should be read-only if Parameter is in use on Channel: C60632" + " </span><br>",
				true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Parameter for Meter Class cannot be created: C60633" + " </span><br>",
				true);
		
		String code = "C60632Cod" + getCurrentDate().substring(getCurrentDate().length()-5); code = code.replace(".", "1");
		String name = "C60632Ref" + getCurrentDate().substring(getCurrentDate().length()-5);
		String paramClass = "Weather";
		String uomClass = "Length";
		String defaultUom = "inch";
		String calculationMethod = "Latest Value";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testWeatherStationDefaultUOMReadOnlyifChannelInUse");

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(25);
		
		openConfigurationEntity(entity);

		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(25);
		
		clickAddButton(realId);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		setCode(code);
		
		setName(name);

		String []classValues = {"Gauge", "Weather"};
		
		List<String> classValueDropdown = getClassDropDownValues();

		softAssert.assertEqualsNoOrder(classValueDropdown.toArray(), classValues, "Gauge and Weather are displayed in Class Dropdown and Meter is not Present");
		
		waitForExtJSAjaxComplete(10);
		
		String classField = getFieldValue("class", "readOnly");
		
		softAssert.assertTrue(classField.contains("true"), "Class Field Value is Read Only and Meter Couldnt be written");
		
		waitForExtJSAjaxComplete(10);
		
		setParamClass(paramClass);
			
		waitForExtJSAjaxComplete(20);
		
		setUOMClass(uomClass);
		
		waitForExtJSAjaxComplete(20);
		
		setDefaultUOM(defaultUom);
		
		waitForExtJSAjaxComplete(20);
		
		setCalculationMethod(calculationMethod);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		//Filter PO Lines Grid with Line ID
		filterGrid("@realid", realId, name, "Name");
		
		waitForExtJSAjaxComplete(25);
						
		//Open the Transactions Grid Record
		openTransactionLine("@realid", realId, name, "Name");
				
		waitForExtJSAjaxComplete(20);
		
		String classValue = getClassValue("class");
		
		softAssert.assertTrue(classValue.contains("x-form-readonly x-trigger-noedit"), "Class Value is Read Only");
		
		waitForExtJSAjaxComplete(5);
		
		setUOMClass(uomClass);
		
		waitForExtJSAjaxComplete(20);
		
		setDefaultUOM(defaultUom);
		
		waitForExtJSAjaxComplete(20);
		
		close();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		waitForExtJSAjaxComplete(25);
		
		openAnalysisEntity("Weather Stations");

		waitForExtJSAjaxComplete(20);
		
		//Filter PO Lines Grid with Line ID 
		filterGrid("@class", GAUGE_GRID_CLASS, "1preWeatherStationMetrics", "Reference");
		
		waitForExtJSAjaxComplete(25);
								
		//Open the Transactions Grid Record
		openTransactionLine("@class", GAUGE_GRID_CLASS, "1preWeatherStationMetrics", "Reference");
						
		waitForExtJSAjaxComplete(20);
		
		MeteringWeatherstationChanelsActualDataPageObject weatherStationPO = new MeteringWeatherstationChanelsActualDataPageObject();
		
		//Select channels tab
		weatherStationPO.changeTab("channels");
	
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickButton("Add", ADD_WEATHERSTATIONS_FORM_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		//set channel parameter
		weatherStationPO.setChannelParameter(name);
		
		waitForExtJSAjaxComplete(25);
		
		weatherStationPO.setReadingInterval("Hour");
		
		//Verify reference
		softAssert.assertLike(weatherStationPO.getReference(), code, code+" reference before edit ");
		
		//Verify channel parameter
		softAssert.assertEquals(weatherStationPO.getUOMValue(), "in", uomClass+ " channelParameter is set");
		
		//Save and Close Channel
		saveClose("slnmWSChnlId");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		close(ADD_WEATHERSTATIONS_FORM_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(25);
		
		openConfigurationEntity(entity);

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		//Filter PO Lines Grid with Line ID
		filterGrid("@realid", realId, name, "Name");
		
		waitForExtJSAjaxComplete(25);
						
		//Open the Transactions Grid Record
		openTransactionLine("@realid", realId, name, "Name");
				
		waitForExtJSAjaxComplete(20);
		
		String uomClassValue = getFieldValue("uomClass", "class");
		
		softAssert.assertTrue(uomClassValue.contains("x-form-readonly x-trigger-noedit"), "uomClass Value is Read Only");
		
		String defaultUOMValue = getFieldValue("defaultUom", "class");
		
		softAssert.assertTrue(defaultUOMValue.contains("x-form-readonly x-trigger-noedit"), "defaultUOM Value is Read Only");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertAll();
		
		Reporter.log("Parameter for Meter Class cannot be created is successfully verified", true);
		
		Reporter.log("Weather Stations: UOM class and Default UOM should be read-only if Parameter is in use on Channel is successfully verified", true);
	}
}



