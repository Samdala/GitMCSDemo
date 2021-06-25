package test.energy.measuretemplates;

import java.io.IOException;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class EnergyMeasureTemplatesValidationTestSuite extends EnergyMeasureTemplatePageObject{
	
	@Test(enabled=true)
	public void testEnergyMeasureTemplateValidation() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Energy Measure Templates Validation: 35241, 35242, 35244, 35245, 35246" + " </span><br>", //TODO test case
				true);

		Reporter.log("User Energy Measure Template Validation"  + " <br>",
				true);
			
		String reference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String measureType = "preMeasureType1";
		String appliesToEO = "Energy Object";
		String appliesToMO = "Maintenance Object";
		String appliesToMOP = "Maintenance Object Part";

		String description = "Some description";
		String expectedLifetime = "20";
		String costOfCapital = "25";
		String investmentValue = "10000";
		String currency = "USD";


		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("CreateEditEnergyMeasureTemplate");
		
		////////////////////Navigate to Energy Measure Templates grid///////////////////////
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_ENERGY);
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(20);
		
		openConfigurationEntity("Energy Measure Templates");	
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		//////////////////////Create Energy Measure Template//////////////////////
		
		clickAddButton(XPATH_GRID_CLASS);
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(25);

				//default values
		
				softAssert.assertEquals(getFieldValue("properties_reference"), "", "No default value for reference");
				softAssert.assertEquals(getFieldValue("properties_code"), "", "No default value for code");
				softAssert.assertEquals(getFieldValue("properties_class"), "Energy", "No default value for properties_class");
				softAssert.assertTrue(getFieldValue("properties_type").toLowerCase().contains("select")||getFieldValue("properties_type").equals(""), "No default value for properties_type");
				softAssert.assertEquals(getFieldValue("properties_appliesTo"), "Select Object Type", "No default value for properties_appliesTo");
				
				softAssert.assertEquals(getCheckBoxState("impactOn_electricity"), false, "Impact electricity is off");
				softAssert.assertEquals(getCheckBoxState("impactOn_combustibles"), false, "Impact combustibles is off");
				softAssert.assertEquals(getCheckBoxState("impactOn_water"), false, "Impact water is off");
				
				softAssert.assertEquals(getTextAreaFieldValue("properties_description"), "", "No default value for properties_description");
				
				//tab
				clickInputParametersTab();
				waitForExtJSAjaxComplete(20);
				
				softAssert.assertEquals(getFieldValue("inputParameters_expectedLifetime"), "", "No default value for inputParameters_expectedLifetime");
				softAssert.assertEquals(getFieldValue("inputParameters_costOfCapital"), "", "No default value for inputParameters_costOfCapital");
				softAssert.assertEquals(getFieldValue("inputParameters_investmentFixedValue"), "0", "No default value for inputParameters_investmentFixedValue");
				softAssert.assertEquals(getFieldValue("inputParameters_fixedValueCurrency"), "EUR", "No default value for inputParameters_fixedValueCurrency");
				softAssert.assertEquals(getFieldValue("inputParameters_formulaInvestmentValue"), "0", "No default value for inputParameters_formulaInvestmentValue");
				softAssert.assertEquals(getFieldValue("inputParameters_formulaCurrency"), "EUR", "No default value for inputParameters_formulaCurrency");
				softAssert.assertEquals(getFieldValue("inputParameters_parameterType"), "", "No default value for inputParameters_parameterType");
				softAssert.assertEquals(getFieldValue("inputParameters_parameter"), "", "No default value for inputParameters_parameter");
				
				softAssert.assertEquals(getCheckBoxState("inputParameters_isFixedPrice"), true, "No default value for inputParameters_isFixedPrice");
				softAssert.assertEquals(getCheckBoxState("inputParameters_useFormula"), false, "No default value for inputParameters_useFormula");
				
				//Enabled and disabled fields.
				clickGeneralTab();
				waitForExtJSAjaxComplete(20);
				
				softAssert.assertTrue(!checkInputDisabled("properties_reference"),"Element reference is not disabled.");
				softAssert.assertTrue(!checkInputDisabled("properties_code"), "Element code is not disabled.");
				softAssert.assertTrue(checkInputDisabled("properties_class"), "Element properties_class is disabled.");
				softAssert.assertTrue(!checkInputDisabled("properties_type"), "Element properties_type is not disabled.");
				softAssert.assertTrue(!checkInputDisabled("properties_appliesTo"), "Element properties_appliesTo is not disabled.");
				
				softAssert.assertTrue(!checkInputDisabled("impactOn_electricity"), "Element impactOn_electricity is not disabled.");
				softAssert.assertTrue(!checkInputDisabled("impactOn_combustibles"), "Element impactOn_combustibles is not disabled.");
				softAssert.assertTrue(!checkInputDisabled("impactOn_water"), "Element impactOn_water is not disabled.");
				softAssert.assertTrue(!checkTextareaDisabled("properties_description"), "Element properties_description is not disabled.");
				
				clickInputParametersTab();
				
				softAssert.assertTrue(!checkInputDisabled("inputParameters_expectedLifetime"), "Element inputParameters_expectedLifetime is not disabled.");
				softAssert.assertTrue(!checkInputDisabled("inputParameters_costOfCapital"), "Element inputParameters_costOfCapital is not disabled.");
				softAssert.assertTrue(!checkInputDisabled("inputParameters_investmentFixedValue"), "Element inputParameters_investmentFixedValue is not disabled.");
				softAssert.assertTrue(!checkInputDisabled("inputParameters_fixedValueCurrency"), "Element inputParameters_fixedValueCurrency is not disabled.");
				softAssert.assertTrue(checkInputDisabled("inputParameters_formulaInvestmentValue"), "Element inputParameters_formulaInvestmentValue is disabled.");
				softAssert.assertTrue(checkInputDisabled("inputParameters_formulaCurrency"), "Element inputParameters_formulaCurrency is disabled.");
				softAssert.assertTrue(checkInputDisabled("inputParameters_parameterType"), "Element inputParameters_parameterType is disabled.");
				softAssert.assertTrue(checkInputDisabled("inputParameters_parameter"), "Element inputParameters_parameter is disabled.");
				
				softAssert.assertTrue(!checkInputDisabled("inputParameters_isFixedPrice"), "Element inputParameters_isFixedPrice is not disabled.");
				softAssert.assertTrue(!checkInputDisabled("inputParameters_useFormula"), "Element inputParameters_useFormula is not disabled.");
				
				checkCheckBox("inputParameters_useFormula");
				
				softAssert.assertTrue(checkInputDisabled("inputParameters_investmentFixedValue"), "Element inputParameters_investmentFixedValue is disabled.");
				softAssert.assertTrue(checkInputDisabled("inputParameters_fixedValueCurrency"), "Element inputParameters_fixedValueCurrency is disabled.");
				softAssert.assertTrue(!checkInputDisabled("inputParameters_formulaInvestmentValue"), "Element inputParameters_formulaInvestmentValue is not disabled.");
				softAssert.assertTrue(!checkInputDisabled("inputParameters_formulaCurrency"), "Element inputParameters_formulaCurrency is not disabled.");
				softAssert.assertTrue(checkInputDisabled("inputParameters_parameterType"), "Element inputParameters_parameterType is not disabled.");
				softAssert.assertTrue(!checkInputDisabled("inputParameters_parameter"), "Element inputParameters_parameter is not disabled.");
				
				//check field visibility condition
				
				clickGeneralTab();
				
				setAppliesTo("Energy Object");
				
				softAssert.assertTrue(!isElementOnForm("properties_maintenanceObjectCategory"), "Element properties_maintenanceObjectCategory is not on form.");
				
				softAssert.assertTrue(!isElementOnForm("properties_maintenanceObjectPartType"), "Element properties_maintenanceObjectPartType is not on form.");
				
				setAppliesTo("Maintenance Object");
				
				waitForExtJSAjaxComplete(20);
				
				softAssert.assertTrue(isElementOnForm("properties_maintenanceObjectCategory"), "Element properties_maintenanceObjectCategory is on form.");
				
				softAssert.assertTrue(!isElementOnForm("properties_maintenanceObjectPartType"), "Element properties_maintenanceObjectPartType is not on form.");
				
				setAppliesTo("Maintenance Object Part");
				
				softAssert.assertTrue(!isElementOnForm("properties_maintenanceObjectCategory"), "Element properties_maintenanceObjectCategory is not on form.");
				
				softAssert.assertTrue(isElementOnForm("properties_maintenanceObjectPartType"), "Element properties_maintenanceObjectPartType is on form.");
				
				setAppliesTo("Energy Object");
				
				clickInputParametersTab();
				
				waitForExtJSAjaxComplete(20);
				
				setFormulaCurrency("USD");
				
				checkCheckBox("inputParameters_isFixedPrice");
				
				waitForExtJSAjaxComplete(20);
				
				setFixedValueCurrency("USD");
						
				//Check UI of mandatory fields (bold text, asterisk)
						
				Reporter.log("Check UI of mandatory fields (bold text, asterisk)", true);
				
				clickGeneralTab();
				
				waitForExtJSAjaxComplete(20);
				
				softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "properties_reference"),"Element reference has correct UI (Bold text, asterix)");
				
				softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "properties_code"), "Code field has correct UI (Bold text, asterix)");
				
				softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "properties_class"), "properties_class field has correct UI (Bold text, asterix)");
				
				softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "properties_type"), "properties_type field has correct UI (Bold text, asterix)");
				
				softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "properties_appliesTo"), "properties_appliesTo field has correct UI (Bold text, asterix)");
				
				softAssert.assertTrue(!McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "impactOn_electricity"), "impactOn_electricity field has correct UI no (Bold text, asterix)");
				
				softAssert.assertTrue(!McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "impactOn_combustibles"), "impactOn_combustibles field has correct UI no (Bold text, asterix)");
				
				softAssert.assertTrue(!McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "impactOn_water"), "impactOn_water field has correct UI no (Bold text, asterix)");
				
				softAssert.assertTrue(!McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "properties_description"), "properties_description field has correct UI no (Bold text, asterix)");					
				
				
				//Try to save Parameter Form with empty reference field
				
				reopenForm();
				
				Reporter.log("Try to save Energy Measure Templates Form with empty reference field", true);
				
				waitForExtJSAjaxComplete(20);
				
				setCode(code + "1");
				
				setReference(reference + "1");
				
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
				
				clickGeneralTab();
				
				clearField("properties_reference", "Reference");
				
				saveClose();
						
				waitForExtJSAjaxComplete(20);
				
				softAssert.assertTrue(Grid.isRowInGridAbsent(driver, code+"1"),"Parameter is not created with empty mandatory fields and not displayed on Overview");
						
				softAssert.assertTrue(checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW),"Form can't be saved with empty reference field"); 

				softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, XPATH_ADD_FORM_WINDOW, "properties_reference"),"Red border is present on empty reference field");

				softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW),"Message about invalid form is present"); 
						
				reopenForm();
				
				//Try to save Parameter Form with empty code field
				
				Reporter.log("Try to save Energy Measure Templates Form with empty code field", true);
				
				waitForExtJSAjaxComplete(20);
				
				setCode(code);
				
				setReference(reference + "2");
				
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
				
				clickGeneralTab();
				
				clearField("properties_code", "Code");
				
				saveClose();
						
				waitForExtJSAjaxComplete(20);
				
				softAssert.assertTrue(Grid.isRowInGridAbsent(driver, reference+"2"),"Parameter is not created with empty mandatory fields and not displayed on Overview");
						
				softAssert.assertTrue(checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW),"Form can't be saved with empty code field"); 

				softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, XPATH_ADD_FORM_WINDOW, "properties_code"),"Red border is present on empty code field");

				softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW),"Message about invalid form is present"); 
						
				reopenForm();
				
				//Try to save Parameter Form with empty code field
				
				
				Reporter.log("Try to save Energy Measure Templates Form with empty MeasureType field", true);
				
				waitForExtJSAjaxComplete(20);
				
				setCode(code + "3");
				
				setReference(reference + "3");
				
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
				
				clickGeneralTab();
				
				clearField("properties_type", "Type");
				
				saveClose();
						
				waitForExtJSAjaxComplete(20);
				
				softAssert.assertTrue(Grid.isRowInGridAbsent(driver, reference+"3"),"Parameter is not created with empty mandatory fields and not displayed on Overview");
						
				softAssert.assertTrue(checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW),"Form can't be saved with empty properties_type field"); 

				softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, XPATH_ADD_FORM_WINDOW, "properties_type"),"Red border is present on empty properties_type field");

				softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW),"Message about invalid form is present"); 
				
				//Try to save Parameter Form with empty code field
				
				reopenForm();
				
				Reporter.log("Try to save Energy Measure Templates Form with empty AppliesTo field", true);
				
				waitForExtJSAjaxComplete(20);
				
				setCode(code + "4");
				
				setReference(reference + "4");
				
				setMeasureType(measureType);
				
				waitForExtJSAjaxComplete(20);
				
				//setAppliesTo(appliesToEO);
				
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

				clickGeneralTab();
				
				//clearField("properties_appliesTo", "appliesTo");
				
				saveClose();
						
				waitForExtJSAjaxComplete(20);
				
				softAssert.assertTrue(Grid.isRowInGridAbsent(driver, reference+"4"),"Parameter is not created with empty mandatory fields and not displayed on Overview");
						
				softAssert.assertTrue(checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW),"Form can't be saved with empty appliesTo field"); 

				softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, XPATH_ADD_FORM_WINDOW, "properties_appliesTo"),"Red border is present on empty appliesTo field");

				softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW),"Message about invalid form is present"); 
				
				//Try to save Parameter Form with empty Energy object field
				
				reopenForm();
				
				Reporter.log("Try to save Energy Measure Templates Form with empty  Energy object field", true);
				
				waitForExtJSAjaxComplete(20);
				
				setCode(code + "5");
				
				setReference(reference + "5");
				
				setMeasureType(measureType);
				
				waitForExtJSAjaxComplete(20);
				
				setAppliesTo(appliesToMO);
				
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
				
				clickGeneralTab();
				
				clearField("properties_maintenanceObjectCategory", "ObjectCategory");
				
				saveClose();
						
				waitForExtJSAjaxComplete(20);
				
				softAssert.assertTrue(Grid.isRowInGridAbsent(driver, reference+"5"),"Parameter is not created with empty mandatory fields and not displayed on Overview");
						
				softAssert.assertTrue(checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW),"Form can't be saved with empty properties_maintenanceObjectCategory field"); 

				softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, XPATH_ADD_FORM_WINDOW, "properties_maintenanceObjectCategory"),"Red border is present on empty properties_maintenanceObjectCategory field");

				softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW),"Message about invalid form is present"); 
				
				//Try to save Parameter Form with empty Energy object field
				
				reopenForm();
				
				Reporter.log("Try to save Energy Measure Templates Form with empty  Energy object field", true);
				
				waitForExtJSAjaxComplete(20);
				
				setCode(code + "6");
				
				setReference(reference + "6");
				
				setMeasureType(measureType);
				
				waitForExtJSAjaxComplete(20);
				
				setAppliesTo(appliesToMOP);
				
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
				
				clickGeneralTab();
				
				clearField("properties_maintenanceObjectPartType", "ObjectCategory");
				
				saveClose();
						
				waitForExtJSAjaxComplete(20);
				
				softAssert.assertTrue(Grid.isRowInGridAbsent(driver, reference+"6"),"Parameter is not created with empty mandatory fields and not displayed on Overview");
						
				softAssert.assertTrue(checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW),"Form can't be saved with empty properties_maintenanceObjectPartType field"); 

				softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, XPATH_ADD_FORM_WINDOW, "properties_maintenanceObjectPartType"),"Red border is present on empty properties_maintenanceObjectPartType field");

				softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW),"Message about invalid form is present"); 
						
				reopenForm();
				
				//try to save non unique reference
				
				waitForExtJSAjaxComplete(20);
				
				setCode("1preMeasureTmpltMO" + "1");
				
				setReference("1preMeasureTmpltMO");
				
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
				
				saveClose();
				
				waitForExtJSAjaxComplete(20);
				
				softAssert.assertTrue(Grid.isRowInGridAbsent(driver, "1preMeasureTmpltMO" + "1"),"Template is not created with non unique fields and not displayed on Overview");
				
				softAssert.assertTrue(checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW),"Form can't be saved with non-unique Reference field"); 
				
				softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW),"Message about non-unique Reference field is present"); 
				
				reopenForm();
				
				//try to save non unique code
				
				waitForExtJSAjaxComplete(20);
				
				setCode("1preMeasureTmpltMO");
				
				setReference("1preMeasureTmpltMO" + "1");
				
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
				
				saveClose();
				
				waitForExtJSAjaxComplete(20);
				
				softAssert.assertTrue(Grid.isRowInGridAbsent(driver, "1preMeasureTmpltMO" + "1"),"Template is not created with non unique fields and not displayed on Overview");
				
				softAssert.assertTrue(checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW),"Form can't be saved with non-unique Code field"); 
				
				softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW),"Message about non-unique Code field is present"); 
				
		
		softAssert.assertAll();
		
	}
	

	/**
	 * Test Case ID: C60721
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testEnergyMeasureTemplateGridUI() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Energy measure templates: Energy measure templates grid UI: C60721" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testEnergyMeasureTemplateGridUI");

		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(25);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(20);
		
		openConfigurationEntity("Energy Measure Templates");		
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);

		clickMeasureTemplatesTab();
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(verifyUIButtons(XPATH_GRID_CLASS, "Add"), "Add button is present in UI Grid");
		
		softAssert.assertTrue(verifyUIButtons(XPATH_GRID_CLASS, "Edit"), "Edit button is present in UI Grid");
		
		softAssert.assertTrue(verifyUIButtons(XPATH_GRID_CLASS, "Delete"), "Delete button is present in UI Grid");
		
		softAssert.assertTrue(verifyUIButtons(XPATH_GRID_CLASS, "Report"), "Report button is present in UI Grid");
		
		softAssert.assertTrue(verifyUIButtons(XPATH_GRID_CLASS, "Clear Filters"), "Clear Filters button is present in UI Grid");
		
		softAssert.assertTrue(verifyUIImages("toolexcel"), "Excel Tool button is present in UI Grid");
		
		softAssert.assertTrue(verifyUIImages("toolprint"), "Excel Print button is present in UI Grid");
		
		waitForExtJSAjaxComplete(5);
		
		//Verify AppliesTo Dropdown
		String []appliesTO = {"", "Energy Object", "Maintenance Object", "Maintenance Object Part"};
		
		List<String> appliesToArray = verifyAppliesToDropdown("@class", XPATH_GRID_CLASS, "Applies to");
		
		softAssert.assertEqualsNoOrder(appliesToArray.toArray(), appliesTO, "All Values are available in applies To Dropdown");
		
		waitForExtJSAjaxComplete(10);
		
		//Verify All Column Names
		
		softAssert.assertTrue(verifyColumnNames("@class", XPATH_GRID_CLASS, "Code"), "Code Column is present in UI Grid");
		
		softAssert.assertTrue(verifyColumnNames("@class", XPATH_GRID_CLASS, "Reference"), "Reference Column is present in UI Grid");
		
		//softAssert.assertTrue(verifyColumnNames("@class", XPATH_GRID_CLASS, "Class"), "Class Column is present in UI Grid");
		
		softAssert.assertTrue(verifyColumnNames("@class", XPATH_GRID_CLASS, "Applies to"), "Applies to Column is present in UI Grid");

		softAssert.assertTrue(verifyColumnNames("@class", XPATH_GRID_CLASS, "Maintenance Object Category"), "Maintenance Object Category Column is present in UI Grid");
		
		softAssert.assertTrue(verifyColumnNames("@class", XPATH_GRID_CLASS, "Maintenance Object Part Type"), "Maintenance Object Part Type Column is present in UI Grid");
		
		softAssert.assertTrue(verifyColumnNames("@class", XPATH_GRID_CLASS, "Impact on Electricity"), "Impact on Electricity Column is present in UI Grid");
		
		softAssert.assertTrue(verifyColumnNames("@class", XPATH_GRID_CLASS, "Impact on Combustibles"), "Impact on Combustibles Column is present in UI Grid");
		
		softAssert.assertTrue(verifyColumnNames("@class", XPATH_GRID_CLASS, "Impact on Water"), "Impact on Water Column is present in UI Grid");
		
		softAssert.assertTrue(verifyColumnNames("@class", XPATH_GRID_CLASS, "Cost of Capital (%)"), "Cost of Capital (%) Column is present in UI Grid");
			
		softAssert.assertTrue(verifyColumnNames("@class", XPATH_GRID_CLASS, "Expected Lifetime (Years)"), "Expected Lifetime Column is present in UI Grid");
		
		softAssert.assertAll();
		
		Reporter.log("Energy measure templates grid UI is successfully verified", true);
	}
}
