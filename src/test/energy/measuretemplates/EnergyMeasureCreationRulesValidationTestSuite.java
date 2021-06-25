package test.energy.measuretemplates;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class EnergyMeasureCreationRulesValidationTestSuite extends EnergyMeasureCreationRulesPageObject{
	
	@Test(enabled=true)
	public void testEnergyMeasureCreationRuleValidation() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Validation Energy Measure Creation Rules : 31583, 31584, 31591, 31585, 31587, 31588, 31590" + " </span><br>", //TODO test case
				true);

		Reporter.log("User Validation Energy Measure Creation Rule"  + " <br>",
				true);
		
		Boolean build14 = driver.getCurrentUrl().contains("14");
		
		String reference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String appliesToMntcObj = "Maintenance Object";
		String appliesToMntcObjPart = "Maintenance Object Part";
		String mntcObjCat = "1preMnObCtRef";
		String mntcObjPartType = "1preObPtTpRef";
		String measureTemplateForMntcObj = "1preMeasureTmpltMO";
		String measureTemplateForMntcObjPart = "1preMeasureTmpltMOPT";
		
		String operator = "is equal to";
		String value1 = "25";
		
		String generalUDIFieldType = "General UDI Field";
		String catSpecUDIFieldType = "Category-specific UDI Field";
		String partTypeSpecificUDIFieldType = "Part Type-specific UDI Field";
		String energyRatingFieldType = "Energy Rating";
		
		
		String mntcObjUdiField = "MO_UDI_FIELD";
		String mntcObjCategorySpecificUdiField = "MO_CAT_1PRE_UDI_FIELD";
		String mntcObjPartUdiField = "MOPrt_UDI_FIELD";
		String mntcObjPartTypeSpecificUdiField = "MOPrt_1PRE_UDI_FIELD";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("CreateEditEnergyMeasureCreationRuleValidation");
		
		////////////////////Navigate to Energy Measure Creation Rules grid///////////////////////

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		if(!build14){
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(20);
		
		openConfigurationEntity("Energy Measure Templates");
		
		waitForExtJSAjaxComplete(20);
		
		clickMeasureCreationRulesTab();
		
		waitForExtJSAjaxComplete(20);
		
		//////////////////////Create Energy Measure Creation Rule//////////////////////
		
		clickAddButtonOnRulesTab();
		
		waitForExtJSAjaxComplete(20);
		
		//Verify default fields and values
		
		softAssert.assertTrue(isElementOnForm("maintenanceObjectCategory"), "Field Maintenance Object Category is displayed on form.");
		
		softAssert.assertFalse(isElementOnForm("maintenanceObjectPartType"), "Field Maintenance Object Part Type is hidden on form.");
		
		softAssert.assertTrue(checkInputDisabled("measureTemplate"), "Field Measure Template is disabled.");
		
		setAppliesTo("Maintenance Object Part");
		
		softAssert.assertFalse(isElementOnForm("maintenanceObjectCategory"), "Field Maintenance Object Category is hidden on form.");
		
		softAssert.assertTrue(isElementOnForm("maintenanceObjectPartType"), "Field Maintenance Object Part Type is displayed on form.");
		
		softAssert.assertTrue(checkInputDisabled("measureTemplate"), "Element Measure Template is disabled.");
		
		//Verify mandatory field UI
		
		setAppliesTo(appliesToMntcObj);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "reference"),"Reference has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "code"), "Code field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "objectType"), "Applies To field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "maintenanceObjectCategory"), "Maintenance Object Category field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "measureTemplate"), "Measure Template field has correct UI (Bold text, asterix)");
				
		setAppliesTo(appliesToMntcObjPart);
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "maintenanceObjectPartType"), "Maintenance Object Part Type field has correct UI (Bold text, asterix)");
		
		//Verify rule can't be added without any selected category or part
		
		clickAddButtonOnRulesDialog();
		
		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW),"Message about invalid form is present"); 
		
		//TODO Add check if form is not opened
		
		setAppliesTo(appliesToMntcObj);
		
		clickAddButtonOnRulesDialog();
		
		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW),"Message about invalid form is present"); 
		
		//TODO Add check if form is not opened
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		setReference(reference);
		
		setAppliesTo(appliesToMntcObj);
		
		waitForExtJSAjaxComplete(20);
		
		setMaintenanceObjectCategory(mntcObjCat);
		
		waitForExtJSAjaxComplete(20);
		
		setUseMeasureTemplate(measureTemplateForMntcObj);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButtonOnRulesDialog();
		
		waitForExtJSAjaxComplete(20);
		
		//Verify Field Type, Criterion Name and operators for Maintenance Object and it's category
		
		openDropdown("energy properties", "fieldType");
		
		softAssert.assertTrue(verfiyValueIsPresentInDropdown(generalUDIFieldType), "General UDI Field - is present on Field Type dropdown");
		
		softAssert.assertTrue(verfiyValueIsPresentInDropdown(catSpecUDIFieldType), "Category-specific UDI Field  is not present on Field Type dropdown");
		
		softAssert.assertFalse(verfiyValueIsPresentInDropdown(partTypeSpecificUDIFieldType), "Part Type Specific UDI Field is not present on Field Type dropdown");
		
		softAssert.assertTrue(verfiyValueIsPresentInDropdown(energyRatingFieldType), "Energy Rating is present on Field Type dropdown");
		
		closeDropdown("energy properties", "fieldType");
		
		setFieldType(generalUDIFieldType);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "field", mntcObjUdiField), mntcObjUdiField+" - is present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", mntcObjCategorySpecificUdiField), mntcObjCategorySpecificUdiField+" - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", mntcObjPartUdiField), mntcObjPartUdiField+" - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", mntcObjPartTypeSpecificUdiField), mntcObjPartTypeSpecificUdiField+" - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", "1preLabel"), "1preLabel - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", "BREEAM"), "BREEAM - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", "EU"), "EU - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", "LEED"), "LEED - is not present on Criteria Type dropdown");
		
		setCriterionName(mntcObjUdiField);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is equal to"), "is equal to - is present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is NOT equal to"), "is NOT equal to - is present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", ">"), "> - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "<"), "< - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", ">="), ">= - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "<="), "<= - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is between"), "is between - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is NOT between"), "is NOT between - is not present on Criteria Type dropdown");
		
		setFieldType(catSpecUDIFieldType);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", mntcObjUdiField), mntcObjUdiField+" - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "field", mntcObjCategorySpecificUdiField), mntcObjCategorySpecificUdiField+" - is present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", mntcObjPartUdiField), mntcObjPartUdiField+" - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", mntcObjPartTypeSpecificUdiField), mntcObjPartTypeSpecificUdiField+" - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", "1preLabel"), "1preLabel - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", "BREEAM"), "BREEAM - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", "EU"), "EU - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", "LEED"), "LEED - is not present on Criteria Type dropdown");
		
		setCriterionName(mntcObjCategorySpecificUdiField);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is equal to"), "is equal to - is present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is NOT equal to"), "is NOT equal to - is present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", ">"), "> - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "<"), "< - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", ">="), ">= - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "<="), "<= - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is between"), "is between - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is NOT between"), "is NOT between - is not present on Criteria Type dropdown");
		
		setFieldType(energyRatingFieldType);
				
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", mntcObjUdiField), mntcObjUdiField+" - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", mntcObjCategorySpecificUdiField), mntcObjCategorySpecificUdiField+" - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", mntcObjPartUdiField), mntcObjPartUdiField+" - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", mntcObjPartTypeSpecificUdiField), mntcObjPartTypeSpecificUdiField+" - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "field", "1preLabel"), "1preLabel - is present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "field", "BREEAM"), "BREEAM - is present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "field", "EU"), "EU - is present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "field", "LEED"), "LEED - is present on Criteria Type dropdown");
		
		//BREEAM
		
		setCriterionName("BREEAM");
		
		waitForExtJSAjaxComplete(20);			
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is equal to"), "test_field - is present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is NOT equal to"), "is NOT equal to - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", ">"), "> - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "<"), "< - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", ">="), ">= - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "<="), "<= - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is between"), "is between - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is NOT between"), "is NOT between - is present on Criteria Type dropdown");
				
		waitForExtJSAjaxComplete(20);
		
		//EU
		
		setCriterionName("EU");
		
		waitForExtJSAjaxComplete(20);			
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is equal to"), "test_field - is present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is NOT equal to"), "is NOT equal to - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", ">"), "> - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "<"), "< - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", ">="), ">= - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "<="), "<= - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is between"), "is between - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is NOT between"), "is NOT between - is present on Criteria Type dropdown");
				
		waitForExtJSAjaxComplete(20);
		
		//LEED
		
		setCriterionName("LEED");
		
		waitForExtJSAjaxComplete(20);			
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is equal to"), "test_field - is present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is NOT equal to"), "is NOT equal to - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", ">"), "> - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "<"), "< - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", ">="), ">= - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "<="), "<= - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is between"), "is between - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is NOT between"), "is NOT between - is present on Criteria Type dropdown");
				
		waitForExtJSAjaxComplete(20);		
		
		//1preLabel
		
		setCriterionName("1preLabel");
		
		waitForExtJSAjaxComplete(20);			
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is equal to"), "test_field - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is NOT equal to"), "is NOT equal to - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", ">"), "> - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "<"), "< - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", ">="), ">= - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "<="), "<= - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is between"), "is between - is present on Criteria Type dropdown");
				
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is NOT between"), "is NOT between - is present on Criteria Type dropdown");
				
		waitForExtJSAjaxComplete(20);
		
		clickXButtonOnRulesDialog( );
		
		//TODO Start
		
		setAppliesTo(appliesToMntcObjPart);
		
		waitForExtJSAjaxComplete(20);
		
		setMaintenanceObjectPartType(mntcObjPartType);
		
		waitForExtJSAjaxComplete(20);
		
		setUseMeasureTemplate(measureTemplateForMntcObjPart);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButtonOnRulesDialog();
		
		waitForExtJSAjaxComplete(20);
		
		//Verify Field Type, Criterion Name and operators for Maintenance Object Part and it's type
		
		openDropdown("energy properties", "fieldType");
		
		softAssert.assertTrue(verfiyValueIsPresentInDropdown(generalUDIFieldType), generalUDIFieldType+" - is present on Field Type dropdown");
		
		softAssert.assertFalse(verfiyValueIsPresentInDropdown(catSpecUDIFieldType), catSpecUDIFieldType+" - is not present on Field Type dropdown");
		
		softAssert.assertFalse(verfiyValueIsPresentInDropdown(energyRatingFieldType), energyRatingFieldType+" - is not present on Field Type dropdown");
		
		softAssert.assertTrue(verfiyValueIsPresentInDropdown(partTypeSpecificUDIFieldType), partTypeSpecificUDIFieldType+" - is present on Field Type dropdown");
		
		closeDropdown("energy properties", "fieldType");
		
		setFieldType(generalUDIFieldType);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", mntcObjUdiField), mntcObjUdiField+" - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", mntcObjCategorySpecificUdiField), mntcObjCategorySpecificUdiField+" - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "field", mntcObjPartUdiField), mntcObjPartUdiField+" - is present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", mntcObjPartTypeSpecificUdiField), mntcObjPartTypeSpecificUdiField+" - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", "1preLabel"), "1preLabel - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", "BREEAM"), "BREEAM - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", "EU"), "EU - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", "LEED"), "LEED - is not present on Criteria Type dropdown");
		
		setCriterionName(mntcObjPartUdiField);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is equal to"), "is equal to - is present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is NOT equal to"), "is NOT equal to - is present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", ">"), "> - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "<"), "< - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", ">="), ">= - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "<="), "<= - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is between"), "is between - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is NOT between"), "is NOT between - is not present on Criteria Type dropdown");
		
		setFieldType(partTypeSpecificUDIFieldType);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", mntcObjUdiField), mntcObjUdiField+" - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", mntcObjCategorySpecificUdiField), mntcObjCategorySpecificUdiField+" - is not present on Criteria Type dropdown");
		
		softAssert.assertFalse(verifyItemExistsOnCriterion( "field", mntcObjPartUdiField), mntcObjPartUdiField+" - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "field", mntcObjPartTypeSpecificUdiField), mntcObjPartTypeSpecificUdiField+" - is present on Criteria Type dropdown");
		
		softAssert.assertTrue(!verifyItemExistsOnCriterion( "field", "1preLabel"), "1preLabel - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(!verifyItemExistsOnCriterion( "field", "BREEAM"), "BREEAM - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(!verifyItemExistsOnCriterion( "field", "EU"), "EU - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(!verifyItemExistsOnCriterion( "field", "LEED"), "LEED - is not present on Criteria Type dropdown");
		
		setCriterionName(mntcObjPartTypeSpecificUdiField);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is equal to"), "is equal to - is present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is NOT equal to"), "is NOT equal to - is present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", ">"), "> - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "<"), "< - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", ">="), ">= - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "<="), "<= - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is between"), "is between - is not present on Criteria Type dropdown");
		
		softAssert.assertTrue(verifyItemExistsOnCriterion( "operator", "is NOT between"), "is NOT between - is not present on Criteria Type dropdown");
		
		//TODO Stop
		
		clickXButtonOnRulesDialog( );
		
		//mandatory field Code
		
		reopenForm();
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code + "1");
		
		setReference(reference + "1");
		
		setAppliesTo(appliesToMntcObj);
		
		waitForExtJSAjaxComplete(20);
		
		setMaintenanceObjectCategory(mntcObjCat);
		
		waitForExtJSAjaxComplete(20);
		
		setUseMeasureTemplate(measureTemplateForMntcObj);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButtonOnRulesDialog();
		
		waitForExtJSAjaxComplete(20);
		
		setFieldType(generalUDIFieldType);
		
		waitForExtJSAjaxComplete(20);
		
		setCriterionName(mntcObjUdiField);
		
		waitForExtJSAjaxComplete(20);
		
		setOperator(operator);
		
		waitForExtJSAjaxComplete(20);
		
		setValue1(value1);
		
		waitForExtJSAjaxComplete(20);
		
		clickSaveButtonOnRulesDialog(ADD_CRITERION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//////////////////////after setting criterion - some fields will be disabled//////
		
		softAssert.assertTrue(checkInputDisabled("objectType"), "Element Maintenance Object is disabled.");
		
		softAssert.assertTrue(checkInputDisabled("maintenanceObjectCategory"), "Element Maintenance Object Category is disabled.");
		
		softAssert.assertTrue(checkInputDisabled("measureTemplate"), "Element Measure Template is disabled.");
		
		//////////////////////////////////////////////////////////////////////////////////
		
		waitForExtJSAjaxComplete(20);
		
		clearField("code", "Code");
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, code+"1"),"Parameter is not created with empty mandatory fields and not displayed on Overview");
				
		softAssert.assertTrue(checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW),"Form can't be saved with empty code field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, XPATH_ADD_FORM_WINDOW, "code"),"Red border is present on empty code field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW),"Message about invalid form is present"); 
				
		reopenForm();
		
		//mandatory field reference
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code + "2");
		
		setReference(reference + "2");
		
		setAppliesTo(appliesToMntcObj);
		
		waitForExtJSAjaxComplete(20);
		
		setMaintenanceObjectCategory(mntcObjCat);
		
		waitForExtJSAjaxComplete(20);
		
		setUseMeasureTemplate(measureTemplateForMntcObj);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButtonOnRulesDialog();
		
		waitForExtJSAjaxComplete(20);
		
		setFieldType(generalUDIFieldType);
		
		waitForExtJSAjaxComplete(20);
		
		setCriterionName(mntcObjUdiField);
		
		waitForExtJSAjaxComplete(20);
		
		setOperator(operator);
		
		waitForExtJSAjaxComplete(20);
		
		setValue1(value1);
		
		waitForExtJSAjaxComplete(20);
		
		clickSaveButtonOnRulesDialog(ADD_CRITERION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		clearField("reference", "Reference");
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, reference+"2"),"Parameter is not created with empty mandatory fields and not displayed on Overview");
				
		softAssert.assertTrue(checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW),"Form can't be saved with empty reference field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, XPATH_ADD_FORM_WINDOW, "reference"),"Red border is present on empty reference field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW),"Message about invalid form is present"); 
				
		reopenForm();
		
		//mandatory field CRITERIO
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code + "3");
		
		setReference(reference + "3");
		
		setAppliesTo(appliesToMntcObj);
		
		waitForExtJSAjaxComplete(20);
		
		setMaintenanceObjectCategory(mntcObjCat);
		
		waitForExtJSAjaxComplete(20);
		
		setUseMeasureTemplate(measureTemplateForMntcObj);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, reference+"3"),"Parameter is not created with empty mandatory fields and not displayed on Overview");
				
		softAssert.assertTrue(checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW),"Form can't be saved with empty reference field"); 

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, XPATH_ADD_FORM_WINDOW),"Message about invalid form is present"); 		
		
		softAssert.assertAll();
	}else {
		Reporter.log("This feature is available only in Trunk", true);
	}

	}

}
