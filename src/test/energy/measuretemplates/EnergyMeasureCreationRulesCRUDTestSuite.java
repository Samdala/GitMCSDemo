package test.energy.measuretemplates;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class EnergyMeasureCreationRulesCRUDTestSuite extends EnergyMeasureCreationRulesPageObject{
	
	@Test(enabled=true)
	public void testEnergyMeasureCreationRuleCreateEdit() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Add/Edit Energy Measure Creation Rules : ????" + " </span><br>", //TODO test case
				true);

		Reporter.log("User adds/edits Energy Measure Creation Rule"  + " <br>",
				true);
		
		Boolean build122 = driver.getCurrentUrl().contains("122");
		Boolean build14 = driver.getCurrentUrl().contains("14");
		
		String reference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String appliesTo = "Maintenance Object";
		String maintenanceObjCat = "1preMnObCtRef";
		String measureTemplate = "1preMeasureTmpltMO";
		
		String referenceEdited = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String codeEdited = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String fieldType = "General UDI Field";
		String criterionName = "MO_UDI_FIELD";
		String operator = "is equal to";
		String value1 = "25";
		String value2 = "50";
		
		String fieldTypeEdited = "Energy Rating";
		String criterionNameEdited = "1preLabel";
		String operatorEdited = "is between";
		String value1Edited = "15";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("CreateEditEnergyMeasureCreationRule");
		
		////////////////////Navigate to Energy Measure Creation Rules grid///////////////////////
		if(!build122 && !build14){

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(20);
		
		openConfigurationEntity("Energy Measure Templates");		
		
		waitForExtJSAjaxComplete(20);
		
		clickMeasureCreationRulesTab();
		
		waitForExtJSAjaxComplete(20);
		
		//////////////////////Create Energy Measure Creation Rule//////////////////////
		
		clickAddButtonOnRulesTab();
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		setReference(reference);
		
		setAppliesTo(appliesTo);
		
		waitForExtJSAjaxComplete(20);
		
		setMaintenanceObjectCategory(maintenanceObjCat);
		
		waitForExtJSAjaxComplete(20);
		
		setUseMeasureTemplate(measureTemplate);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButtonOnRulesDialog();
		
		waitForExtJSAjaxComplete(20);
		
		//String formId = getXPanelId("Add Criterion");
		
		setFieldType(fieldType);
		
		waitForExtJSAjaxComplete(20);
		
		setCriterionName(criterionName);
		
		waitForExtJSAjaxComplete(20);
		
		setOperator(operator);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setValue1(value1);
		
		waitForExtJSAjaxComplete(20);
		
		clickSaveButtonOnRulesDialog(ADD_CRITERION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, code);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButtonOnRulesTab();
		
		waitForExtJSAjaxComplete(20);
		
		///////////////////////SoftAsserts//////////////////////////////
		
		softAssert.assertEquals(getCode(), code, code+" - Code after creation is correct");

		softAssert.assertEquals(getReference(), reference, reference+" - Reference after creation is correct");

		softAssert.assertEquals(getAppliesTo("value"), appliesTo, appliesTo+" - Applies To after creation is correct");

		softAssert.assertEquals(getMaintenanceObjectCategory("value"), maintenanceObjCat, maintenanceObjCat+" - Maintenance Object Category after creation is correct");
		
//		String a = getUseMeasureTemplate();
		softAssert.assertEquals(getUseMeasureTemplate(), measureTemplate, measureTemplate+" - Use Measure Template after creation is correct");
		
		Grid.checkRowInGriByTextValueAndClick(driver, criterionName);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButtonOnRulesDialog();
		
		waitForExtJSAjaxComplete(20);
		
		//String formId2 = getXPanelId("Edit Criterion");
		
		softAssert.assertEquals(getFieldType(), fieldType, fieldType+" - Field Type after creation is correct");
		
		softAssert.assertEquals(getCriterionName(), criterionName, criterionName+" - Criterion Name after creation is correct");
		
		softAssert.assertEquals(getOperator(), operator, operator+" - Operator after creation is correct");
		
		softAssert.assertEquals(getValue1(), value1, value1+" - Value 1 after creation is correct");
		
		clickXButtonOnRulesDialog();
		
		waitForExtJSAjaxComplete(20);
		
		//////////////////////Edit Energy Measure Creation Rule//////////////////////
		
		setCode(codeEdited);
		
		setReference(referenceEdited);
		
		Grid.checkRowInGriByTextValueAndClick(driver, criterionName);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButtonOnRulesDialog();
		
		waitForExtJSAjaxComplete(20);
		
		//String formId3 = getXPanelId("Edit Criterion");
		
		setFieldType(fieldTypeEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setCriterionName(criterionNameEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setOperator(operatorEdited);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setValue1(value1Edited);
		
		setValue2(value2);
		
		waitForExtJSAjaxComplete(20);
		
		clickSaveButtonOnRulesDialog(EDIT_CRITERION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, code);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButtonOnRulesTab();
		
		waitForExtJSAjaxComplete(20);
		
		///////////////////////SoftAsserts//////////////////////////////
		
		softAssert.assertEquals(getCode(), code, code+" - Code after editing is correct");

		softAssert.assertEquals(getReference(), reference, reference+" - Reference after editing is correct");

		Grid.checkRowInGriByTextValueAndClick(driver, criterionNameEdited);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButtonOnRulesDialog();
		
		waitForExtJSAjaxComplete(20);
		
		//String formId4 = getXPanelId("Edit Criterion");
		
		softAssert.assertEquals(getFieldType(), fieldTypeEdited, fieldTypeEdited+" - Field Type after editing is correct");
		
		softAssert.assertEquals(getCriterionName(), criterionNameEdited, criterionNameEdited+" - Criterion Name after editing is correct");
		
		softAssert.assertEquals(getOperator(), operatorEdited, operatorEdited+" - Operator after editing is correct");
		
//		String b = getValue1(formId4);
		softAssert.assertEquals(getValue1(), value1Edited, value1Edited+" - Value 1 after editing is correct");
		
		softAssert.assertEquals(getValue2(), value2, value2+" - Value 2 after editing is correct");
		
		clickXButtonOnRulesDialog();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		} else {
			Reporter.log("This feature is not available in 12.2 & 14.0", true);
		}

	}
	
	@Test(enabled=true)
	public void testEnergyMeasureCreationRuleDelete() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Energy Measure Creation Rules : ????" + " </span><br>", //TODO test case
				true);

		Reporter.log("User deletes Energy Measure Creation Rule"  + " <br>",
				true);
		
		Boolean build122 = driver.getCurrentUrl().contains("122");
		Boolean build14 = driver.getCurrentUrl().contains("14");
		
		String reference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String appliesTo = "Maintenance Object Part";
		String maintenanceObjPrtType = "1preObPtTpRef";
		String measureTemplate  = "1preMeasureTmpltMOPT";
		String fieldType = "General UDI Field";
		String criterionName = "MOPrt_UDI_FIELD";
		String operator = "is equal to";
		String value1 = "25";
		
		////////////////////Navigate to Energy Measure Creation Rules grid///////////////////////
		
		if(!build122 && !build14){
			
		SoftAssert softAssert = new SoftAssert();
			
		softAssert.setMethodName("testEnergyMeasureCreationRuleDelete");

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(20);
		
		openConfigurationEntity("Energy Measure Templates");		
		
		waitForExtJSAjaxComplete(20);
		
		clickMeasureCreationRulesTab();
		
		waitForExtJSAjaxComplete(20);
		
		//////////////////////Create Energy Measure Creation Rule//////////////////////
		
		clickAddButtonOnRulesTab();
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		setReference(reference);
		
		setAppliesTo(appliesTo);
		
		waitForExtJSAjaxComplete(20);
		
		setMaintenanceObjectPartType(maintenanceObjPrtType);
		
		waitForExtJSAjaxComplete(20);
		
		setUseMeasureTemplate(measureTemplate);
		
		waitForExtJSAjaxComplete(20);
	
		clickAddButtonOnRulesDialog();
		
		waitForExtJSAjaxComplete(20);
		
		//String formId = getXPanelId("Add Criterion");
		
		setFieldType(fieldType);
		
		waitForExtJSAjaxComplete(20);
		
		setCriterionName(criterionName);
		
		waitForExtJSAjaxComplete(20);
		
		setOperator(operator);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setValue1(value1);
		
		waitForExtJSAjaxComplete(20);
		
		clickSaveButtonOnRulesDialog(ADD_CRITERION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, code);
		
		waitForExtJSAjaxComplete(20);
		
		clickDeleteButtonOnRulesTab();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, code), code+" code after delete is present");
		
		softAssert.assertAll();
		
		} else {
			Reporter.log("This feature is not available in 122 & 14", true);
		}
		
	}

	/**
	 * Test Case Id : C60730
	 * Author : SSU
	 * @throws Exception
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testCreateEditMeasureCreationRule() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create/Edit Energy Measure Creation Rules : C60730" + " </span><br>", 
				true);

		Reporter.log("User adds/edits Energy Measure Creation Rule"  + " <br>",
				true);
		
		String reference = "60730Ref" + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "60730Cod" + getCurrentDate().substring(getCurrentDate().length()-6);
		String appliesTo = "Maintenance Object";
		String maintenanceObjCat = "1preMnObCtRef";
		String measureTemplate = "1preMeasureTmpltMO";
		
		String referenceEdited = "EditRef" + getCurrentDate().substring(getCurrentDate().length()-6);
		String codeEdited = "EditCod" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String fieldType = "General UDI Field";
		String criterionName = "MO_UDI_FIELD";
		String operator = "is equal to";
		String value1 = "25";
				
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCreateEditMeasureCreationRule");
		
		////////////////////Navigate to Energy Measure Creation Rules grid///////////////////////
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(20);
		
		openConfigurationEntity("Energy Measure Templates");		
		
		waitForExtJSAjaxComplete(20);
		
		clickMeasureCreationRulesTab();
		
		waitForExtJSAjaxComplete(20);
		
		//////////////////////Create Energy Measure Creation Rule//////////////////////
		
		clickAddButtonOnRulesTab();
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		setReference(reference);
		
		setAppliesTo(appliesTo);
		
		waitForExtJSAjaxComplete(20);
		
		setMaintenanceObjectCategory(maintenanceObjCat);
		
		waitForExtJSAjaxComplete(20);
		
		setUseMeasureTemplate(measureTemplate);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue((getInfoBarMsgOfWindow("@id", getXWindowId(ADD_MEASURE_CREATION_WINDOW_HEADER))).contains("The Measure Rule must contain at least one Criterion."), "Measure Creation Rule must contain at least one Criterion!!");
		
		waitForExtJSAjaxComplete(10);
						
		setCode("");
				
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue((getInfoBarMsgOfWindow("@id", getXWindowId(ADD_MEASURE_CREATION_WINDOW_HEADER))).contains("The form is invalid. Hover over the fields in red to see the errors."), "The form is invalid!!");
		
		waitForExtJSAjaxComplete(10);
		
		setCode(codeEdited);
		
		clickAddButtonOnRulesDialog();
		
		waitForExtJSAjaxComplete(20);
		
		setFieldType(fieldType);
		
		waitForExtJSAjaxComplete(20);
		
		setCriterionName(criterionName);
		
		waitForExtJSAjaxComplete(20);
		
		setOperator(operator);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setValue1(value1);
		
		waitForExtJSAjaxComplete(20);
		
		clickSaveButtonOnRulesDialog(ADD_CRITERION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, codeEdited);
		
		waitForExtJSAjaxComplete(10);
		
		clickEditButtonOnRulesTab();
		
		waitForExtJSAjaxComplete(10);
		
		String appliesToFieldUnEditable = getAppliesTo("class");
		
		softAssert.assertTrue(appliesToFieldUnEditable.contains("x-form-readonly x-trigger-noedit"), "Applies To Field is UnEditable");
		
		waitForExtJSAjaxComplete(10);
		
		String maintenanceObjUnEditable = getAppliesTo("class");
		
		softAssert.assertTrue(maintenanceObjUnEditable.contains("x-form-readonly x-trigger-noedit"), "Maintenance Object Field is UnEditable");
		
		waitForExtJSAjaxComplete(10);
		
		setCode("");
		
		setReference("");
		
		waitForExtJSAjaxComplete(10);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue((getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_MEASURE_CREATION_WINDOW_HEADER))).contains("The form is invalid. Hover over the fields in red to see the errors."), "The form is invalid!!");
		
		waitForExtJSAjaxComplete(10);
		
		setCode(codeEdited);
		
		setReference(referenceEdited);
		
		save();
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, criterionName);
		
		waitForExtJSAjaxComplete(10);
		
		clickDeleteButtonOnRulesDialog();
		
		waitForExtJSAjaxComplete(10);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue((getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_MEASURE_CREATION_WINDOW_HEADER)).contains("The Measure Rule must contain at least one Criterion.")), "Measure Creation Rule must contain at least one Criterion!!");
		
		waitForExtJSAjaxComplete(10);

		softAssert.assertAll();
		
		Reporter.log("Create/Edit Energy Measure Creation Rules is successfully verified", true);
		
	}
	
	/**
	 * Test Case Id : C60731
	 * Author : SSU
	 * @throws Exception
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testCreateEditDeleteCriterion() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Energy measure template rules: Creation/Editing/Deleting of criterion : C39800" + " </span><br>", 
				true);

		Reporter.log("User adds/edits Energy Measure Criterion"  + " <br>",
				true);
		
		String reference = "60731Ref" + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "60731Cod" + getCurrentDate().substring(getCurrentDate().length()-6);
		String appliesTo = "Maintenance Object";
		String maintenanceObjCat = "1preMnObCtRef";
		String measureTemplate = "1preMeasureTmpltMO";
				
		String fieldType = "General UDI Field";
		String fieldType1 = "Category-specific UDI Field";
		String fieldType2 = "Energy Rating";
		String criterionName = "MO_UDI_FIELD";
		String criterionName1 = "MO_CAT_1PRE_UDI_FIELD";
		String criterionName2 = "EU";
		String operator = "is equal to";
		String value1 = "25";
		String value2 = "50";
		
		Boolean build122 = driver.getCurrentUrl().contains("122");
		Boolean build14 = driver.getCurrentUrl().contains("14");
				
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCreateEditDeleteCriterion");
		
		////////////////////Navigate to Energy Measure Creation Rules grid///////////////////////
		if(!build14 && !build122){

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(20);
		
		openConfigurationEntity("Energy Measure Templates");		
		
		waitForExtJSAjaxComplete(20);
		
		clickMeasureCreationRulesTab();
		
		waitForExtJSAjaxComplete(20);
		
		//////////////////////Create Energy Measure Creation Rule//////////////////////
		
		clickAddButtonOnRulesTab();
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		setReference(reference);
		
		setAppliesTo(appliesTo);
		
		waitForExtJSAjaxComplete(20);
		
		setMaintenanceObjectCategory(maintenanceObjCat);
		
		waitForExtJSAjaxComplete(20);
		
		setUseMeasureTemplate(measureTemplate);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButtonOnRulesDialog();
		
		waitForExtJSAjaxComplete(20);
		
		setFieldType(fieldType);
		
		waitForExtJSAjaxComplete(20);
		
		setCriterionName(criterionName);
		
		waitForExtJSAjaxComplete(20);
		
		setOperator(operator);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setValue1(value1);
		
		waitForExtJSAjaxComplete(20);
		
		clickSaveButtonOnRulesDialog(ADD_CRITERION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButtonOnRulesDialog();
		
		waitForExtJSAjaxComplete(20);
		
		setFieldType(fieldType1);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isSaveBtnDisabled(ADD_CRITERION_WINDOW_HEADER), "User can not save criterion without values for 'Criterion Operands'/Criterion operator/Criterion name");
		
		waitForExtJSAjaxComplete(10);
		
		setCriterionName(criterionName1);
		
		waitForExtJSAjaxComplete(20);
		
		setOperator(operator);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setValue1(value2);
		
		waitForExtJSAjaxComplete(10);
		
		clickSaveButtonOnRulesDialog(ADD_CRITERION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, criterionName);
		
		waitForExtJSAjaxComplete(10);
		
		clickEditButtonOnRulesDialog();
		
		waitForExtJSAjaxComplete(10);
		
		setFieldType(fieldType2);
		
		waitForExtJSAjaxComplete(10);
		
		setCriterionName(criterionName2);
		
		waitForExtJSAjaxComplete(20);
		
		setOperator(operator);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setValue1(value2);
		
		waitForExtJSAjaxComplete(10);
		
		clickSaveButtonOnRulesDialog(EDIT_CRITERION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, criterionName1);
		
		waitForExtJSAjaxComplete(10);
		
		clickEditButtonOnRulesDialog();
		
		waitForExtJSAjaxComplete(10);
		
		setValue1("");
		
		clickSaveButtonOnRulesDialog(EDIT_CRITERION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue((getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_CRITERION_WINDOW_HEADER))).contains("The form is invalid. Hover over the fields in red to see the errors."), "The form is invalid!!");
		
		waitForExtJSAjaxComplete(10);
		
		setValue1(value2);
		
		waitForExtJSAjaxComplete(10);
		
		closeUsingWindowTitle(EDIT_CRITERION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, criterionName2);
		
		waitForExtJSAjaxComplete(10);
		
		clickDeleteButtonOnRulesDialog();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, criterionName2), "Criterion Name '"+criterionName2+"' is not present ");
		
		waitForExtJSAjaxComplete(10);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("Energy measure template rules: Creation/Editing/Deleting of criterion is successfully verified", true);
		
		} else {
			Reporter.log("This feature is not available in 122 & 14", true);
		}
	}
}
