package test.energy.measuretemplates;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class EnergyMeasureTemplatesCRUDTestSuite extends EnergyMeasureTemplatePageObject{
	
	/**
	 * Test Case ID: C39947, C39948
	 * Author : Ukraine Team
	 * @throws IOException
	 */
	@Test(enabled=true)
	public void testEnergyMeasureTemplateCreateEdit() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Add/Edit Energy Measure Templates : C39947, C39948" + " </span><br>", 
				true);

		Reporter.log("User adds/edits Energy Measure Template"  + " <br>",
				true);
			
		String reference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String measureType = "preMeasureType1";
		String appliesToEO = "Energy Object";
		String appliesToMO = "Maintenance Object";
		String appliesToMOP = "Maintenance Object Part";
		String maintenanceObjectCategory = "1preMnObCtRef";
		String maintenanceObjectPartType = "1preObPtTpRef";
		String description = "Some description";
		String expectedLifetime = "20";
		String costOfCapital = "25";
		String investmentValue = "10000";
		String currency = "USD";
		String parameterType = "Gauge parameter";
		String parameterTypeTrunk = "Gauge Parameter";
		String parameter = "preGaugeParameter1";
		
		String referenceEdited = "test auto ed" + getCurrentDate().substring(getCurrentDate().length()-6);
		String codeEdited = "test auto ed" + getCurrentDate().substring(getCurrentDate().length()-6);
		String measureTypeEdited ="preMeasureType2";
		String descriptionEdited = "Some description edited";
		String expectedLifetimeEdited = "50";
		String costOfCapitalEdited = "75";
		String investmentValueEdited = "2000";
		String currencyEdited = "EUR";
		String parameterTypeEdited = "UDI Field";
		String fieldUDI = "MOPrt_UDI_FIELD";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("CreateEditEnergyMeasureTemplate");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		////////////////////Navigate to Energy Measure Templates grid///////////////////////

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(25);
		
		openConfigurationEntity("Energy Measure Templates");		
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//////////////////////Create Energy Measure Template//////////////////////
		
		clickAddButton(XPATH_GRID_CLASS);
		
		Thread.sleep(5000);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setCode(code);
		
		setReference(reference);
		
		setMeasureType(measureType);
		
		waitForExtJSAjaxComplete(20);
		
		setAppliesTo(appliesToEO);
		
		waitForExtJSAjaxComplete(20);
		
		checkImpactOnCombustibles();
		
		waitForExtJSAjaxComplete(20);
		
		checkImpactOnWater();
		
		waitForExtJSAjaxComplete(20);
		
		checkImpactOnElectricity();
		
		waitForExtJSAjaxComplete(20);
		
		setDescription(description);
		
		clickInputParametersTab();

		waitForExtJSAjaxComplete(20);
		
		setExpectedLifetime(expectedLifetime);
		
		setCostOfCapital(costOfCapital);
		
		selectUseFormulaRadio();
		
		waitForExtJSAjaxComplete(20);
		
		setFormulaInvestmentValue(investmentValue);
		
		setFormulaCurrency(currency);
		
		waitForExtJSAjaxComplete(20);
		
		setParameterType(parameterTypeTrunk);
		
		waitForExtJSAjaxComplete(20);
		
		setParameter(parameter);
		
		saveClose();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, code);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButton(XPATH_GRID_CLASS);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		///////////////////////SoftAsserts//////////////////////////////
		
		softAssert.assertEquals(getCode(), code, code+" - Code after creation is correct");
		
		softAssert.assertEquals(getReference(), reference, reference+" - Reference after creation is correct");
		
		softAssert.assertEquals(getMeasureType(), measureType, measureType+" - Measure Type after creation is correct");
		
		softAssert.assertEquals(getAppliesTo(), appliesToEO, appliesToEO+" - Applies To after creation is correct");
		
		softAssert.assertEquals(getDescription(), description, description+" - Description after creation is correct");
		
		softAssert.assertEquals(getRadioOrCheckboxState("impactOn_electricity"), true, "Impact On Electricity is checked after creation");
		
		softAssert.assertEquals(getRadioOrCheckboxState("impactOn_combustibles"), true, "Impact On Combustibles is checked after creation");
		
		softAssert.assertEquals(getRadioOrCheckboxState("impactOn_water"), true, "Impact On Water is checked after creation");
		
		clickInputParametersTab();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getExpectedLifetime(), expectedLifetime, expectedLifetime+" - Expected Lifetime after creation is correct");
		
		softAssert.assertEquals(getCostOfCapital(), costOfCapital, costOfCapital+" - Cost Of Capital after creation is correct");
		
		softAssert.assertEquals(getRadioOrCheckboxState("inputParameters_useFormula"), true, "Use a Formula radiobuton is selected after creation");
		
		softAssert.assertEquals(getFormulaInvestmentValue(), investmentValue, investmentValue+" - Formula Investment Value after creation is correct");
		
		softAssert.assertEquals(getFormulaCurrency(), currency, currency+" - Formula Currency after creation is correct");
		
		softAssert.assertEquals(getParameterType(), parameterTypeTrunk, parameterTypeTrunk+" - Parameter Type after creation is correct");
		
		softAssert.assertEquals(getParameter(), parameter, parameter+" - Parameter after creation is correct");
		
		/////////////////////Edit Energy Measure Template///////////////////////
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
				
		setReference(referenceEdited);
		
		setCode(codeEdited);
		
		setDescription(descriptionEdited);
		
		setMeasureType(measureTypeEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setAppliesTo(appliesToMO);
		
		waitForExtJSAjaxComplete(20);
		
		setMaintenanceObjectCategory(maintenanceObjectCategory);
		
		waitForExtJSAjaxComplete(20);
		
		checkImpactOnCombustibles();
		
		waitForExtJSAjaxComplete(20);
		
		checkImpactOnElectricity();
		
		waitForExtJSAjaxComplete(20);
		
		checkImpactOnWater();
		
		waitForExtJSAjaxComplete(20);
		
		clickInputParametersTab();

		waitForExtJSAjaxComplete(20);
		
		setExpectedLifetime(expectedLifetimeEdited);
		
		setCostOfCapital(costOfCapitalEdited);
		
		selectFixedPriceRadio();
		
		waitForExtJSAjaxComplete(20);
		
		setInvestmentFixedValue(investmentValueEdited);
		
		setFixedValueCurrency(currencyEdited);
		
		saveClose();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, codeEdited);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButton(XPATH_GRID_CLASS);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		///////////////////////SoftAsserts//////////////////////////////
		
		softAssert.assertEquals(getCode(), codeEdited, codeEdited+" - Code after editing is correct");

		softAssert.assertEquals(getReference(), referenceEdited, referenceEdited+" - Reference after editing is correct");

		softAssert.assertEquals(getMeasureType(), measureTypeEdited, measureTypeEdited+" - Measure Type after editing is correct");

		softAssert.assertEquals(getAppliesTo(), appliesToMO, appliesToMO+" - Applies To after editing is correct");

		softAssert.assertEquals(getDescription(), descriptionEdited, descriptionEdited+" - Description after editing is correct");

		softAssert.assertEquals(getRadioOrCheckboxState("impactOn_electricity"), false, "Impact On Electricity is unchecked after editing");
		
		softAssert.assertEquals(getRadioOrCheckboxState("impactOn_combustibles"), false, "Impact On Combustibles is unchecked after editing");
		
		softAssert.assertEquals(getRadioOrCheckboxState("impactOn_water"), false, "Impact On Water is unchecked after editing");

		clickInputParametersTab();

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getExpectedLifetime(), expectedLifetimeEdited, expectedLifetimeEdited+" - Expected Lifetime after editing is correct");

		softAssert.assertEquals(getCostOfCapital(), costOfCapitalEdited, costOfCapitalEdited+" - Cost Of Capital after editing is correct");

		softAssert.assertEquals(getRadioOrCheckboxState("inputParameters_isFixedPrice"), true, "Fixed Price radiobuton is selected after editing");
		
		softAssert.assertEquals(getInvestmentFixedValue(), investmentValueEdited, investmentValueEdited+" - Fixed Investment Value after editing is correct");

		softAssert.assertEquals(getFixedValueCurrency(), currencyEdited, currencyEdited+" - Fixed Currency after editing is correct");
		
		/////////////////////Edit Energy Measure Template///////////////////////
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);

		setAppliesTo(appliesToMOP);
		
		waitForExtJSAjaxComplete(20);
		
		setMaintenanceObjectPartType(maintenanceObjectPartType);
		
		waitForExtJSAjaxComplete(20);
		
		checkImpactOnCombustibles();
		
		waitForExtJSAjaxComplete(20);
		
		checkImpactOnElectricity();
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, codeEdited);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButton(XPATH_GRID_CLASS);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickInputParametersTab();
		
		waitForExtJSAjaxComplete(20);
		
		selectUseFormulaRadio();
		
		waitForExtJSAjaxComplete(20);
		
		setFormulaInvestmentValue(investmentValueEdited);
		
		//setParameterType(parameterTypeEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setParameter(fieldUDI);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, codeEdited);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButton(XPATH_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getAppliesTo(), appliesToMOP, appliesToMOP+" - Applies To after editing is correct");
		
		softAssert.assertEquals(getMaintenanceObjectPartType(), maintenanceObjectPartType, maintenanceObjectPartType+" - Maintenance Object Part Type after editing is correct");

		softAssert.assertEquals(getRadioOrCheckboxState("impactOn_electricity"), true, "Impact On Electricity is checked after editing");
		
		softAssert.assertEquals(getRadioOrCheckboxState("impactOn_combustibles"), true, "Impact On Combustibles is checked after editing");
		
		softAssert.assertEquals(getRadioOrCheckboxState("impactOn_water"), false, "Impact On Water is unchecked after editing");

		clickInputParametersTab();

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getRadioOrCheckboxState("inputParameters_useFormula"), true, "Use a Formula radiobuton is selected after editing");
		
		softAssert.assertEquals(getFormulaInvestmentValue(), investmentValueEdited, investmentValueEdited+" - Formula Investment Value after editing is correct");
		
		softAssert.assertEquals(getParameterType(), parameterTypeEdited, parameterTypeEdited+" - Parameter Type after editing is correct");
		
		softAssert.assertEquals(getParameter(), fieldUDI, fieldUDI+" - UDI field after editing is correct");
		
		softAssert.assertAll();
		
	}
	
	/**
	 * Test Case ID: C39949
	 * Author : Ukraine Team
	 * @throws IOException
	 */
	@Test(enabled=true)
	public void testEnergyMeasureTemplateDelete() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Energy Measure Templates : C39949" + " </span><br>", 
				true);

		Reporter.log("User deletes Energy Measure Template"  + " <br>",
				true);
			
		String reference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String measureType = "preMeasureType1";
		String appliesToEO = "Energy Object";
		String description = "Some description";
		String expectedLifetime = "20";
		String costOfCapital = "25";
		String investmentValue = "10000";
		String currency = "USD";
		String parameterType = "Gauge parameter";
		String parameterTypeTrunk = "Gauge Parameter";
		String parameter = "preGaugeParameter1";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("DeleteEnergyMeasureTemplate");
		
		////////////////////Navigate to Energy Measure Templates grid///////////////////////
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		openConfigurationEntity("Energy Measure Templates");		
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		//////////////////////Create Energy Measure Template//////////////////////
		
		clickAddButton(XPATH_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setCode(code);
		
		setReference(reference);
		
		setMeasureType(measureType);
		
		waitForExtJSAjaxComplete(20);
		
		setAppliesTo(appliesToEO);
		
		waitForExtJSAjaxComplete(20);
		
		checkImpactOnCombustibles();
		
		waitForExtJSAjaxComplete(20);
		
		checkImpactOnWater();
		
		waitForExtJSAjaxComplete(20);
		
		checkImpactOnElectricity();
		
		waitForExtJSAjaxComplete(20);
		
		setDescription(description);
		
		clickInputParametersTab();

		waitForExtJSAjaxComplete(20);
		
		setExpectedLifetime(expectedLifetime);
		
		setCostOfCapital(costOfCapital);
		
		selectUseFormulaRadio();
		
		waitForExtJSAjaxComplete(20);
		
		setFormulaInvestmentValue(investmentValue);
		
		setFormulaCurrency(currency);
		
		waitForExtJSAjaxComplete(20);
		
		setParameterType(parameterTypeTrunk);
		
		waitForExtJSAjaxComplete(20);
		
		setParameter(parameter);
		
		saveClose();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, code);
		
		waitForExtJSAjaxComplete(20);
		
		clickDeleteButton(XPATH_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, code), code+" code after delete is present");
		
		softAssert.assertAll();
		
		Reporter.log("Delete Energy Measure Templates is successfully verified", true);
	}

	/**
	 * Test Case ID: C60724 
	 * Author : SSU
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testEnergyMeasureTemplatesCreateEdit() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Energy measure templates: Creating/Editing of the Template : C60724" + " </span><br>", 
				true);

		Reporter.log("User adds/edits Energy Measure Template"  + " <br>",
				true);
			
		String reference = "C60724Ref" + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "C60724Cod" + getCurrentDate().substring(getCurrentDate().length()-6);
		String measureType = "preMeasureType1";
		String appliesToEO = "Energy Object";
		String description = "Some description";
		String expectedLifetime = "20";
		String costOfCapital = "25";
		String investmentValue = "10000";
		String currency = "USD";
		String parameterType = "Gauge parameter";
		String parameterTypeTrunk = "Gauge Parameter";
		String parameter = "preGaugeParameter1";
		
		String codeEdited = "C60724edit" + getCurrentDate().substring(getCurrentDate().length()-6);
		String measureTemplateEdit ="1preMeasureTmpltMO";
		String descriptionEdited = "New description";
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testEnergyMeasureTemplatesCreateEdit");
		
		////////////////////Navigate to Energy Measure Templates grid///////////////////////
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(20);
		
		openConfigurationEntity("Energy Measure Templates");		
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		//////////////////////Create Energy Measure Template//////////////////////
		
		clickAddButton(XPATH_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setCode(code);
		
		setReference(reference);
		
		setMeasureType(measureType);
		
		waitForExtJSAjaxComplete(20);
		
		setAppliesTo(appliesToEO);
		
		waitForExtJSAjaxComplete(20);
		
		checkImpactOnCombustibles();
		
		waitForExtJSAjaxComplete(20);
		
		checkImpactOnWater();
		
		waitForExtJSAjaxComplete(20);
		
		checkImpactOnElectricity();
		
		waitForExtJSAjaxComplete(20);
		
		setDescription(description);
		
		clickInputParametersTab();

		waitForExtJSAjaxComplete(20);
		
		setExpectedLifetime(expectedLifetime);
		
		setCostOfCapital(costOfCapital);
		
		selectUseFormulaRadio();
		
		waitForExtJSAjaxComplete(20);
		
		setFormulaInvestmentValue(investmentValue);
		
		setFormulaCurrency(currency);
		
		waitForExtJSAjaxComplete(20);
		
		setParameterType(parameterTypeTrunk);
		
		waitForExtJSAjaxComplete(20);
		
		setParameter(parameter);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, code);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButton(XPATH_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		setCode("");
		
		setDescription(descriptionEdited);
		
		save();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue((getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_ENERGY_MEASURE_TEMPLATE_WINDOW_HEADER))).contains("The form is invalid. Hover over the fields in red to see the errors."), "The form is invalid!!");
		
		waitForExtJSAjaxComplete(10);
		
		setCode(codeEdited);
		
		saveClose();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		waitForExtJSAjaxComplete(10);
		
		clickDeleteButton(XPATH_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, measureTemplateEdit);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButton(XPATH_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setCode("");
		
		setReference("");
		
		setDescription(descriptionEdited);
		
		save();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue((getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_ENERGY_MEASURE_TEMPLATE_WINDOW_HEADER))).contains("The form is invalid. Hover over the fields in red to see the errors."), "The form is invalid!!");
		
		waitForExtJSAjaxComplete(10);
		
		setCode(measureTemplateEdit);
		
		setReference(measureTemplateEdit);
		
		saveClose();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertAll();
		
		Reporter.log("Energy measure templates is succesfully Created and Edited", true);
	}
}
