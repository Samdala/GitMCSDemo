package test.energy.supplypoints;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.McsElement;
import test.framework.webelement.Tree;

public class SupplyPointsFormValidationTestSuite extends
		MeteringSuplyPointsPageObject {




	 @Test(enabled=true)
	public void testMeteringSupplyPointFormValidation() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Supply Point Form Validation: ?????" + " </span><br>",
				true);

		Reporter.log("User tries to save Supply Point with different validations:  "  + " <br>",
				true);
		
		String code = "test auto sp" + getCurrentDate().substring(getCurrentDate().length()-6);
		String codePrefix = "NRG-";
		String site = "slnmEnrgSite1";
		String commodity = "Electricity";
		
		String codeNonUnique = "codeNonUnique";
		String codePrefixNonUnique = "codePrefixNonUnique";
		
		List<String> mandatoryFieldsErrorMessageKeyWordsCheck =Arrays.asList("form", "invalid");
		List<String> unqiueReferenceFieldErrorMessageKeyWordsCheck =Arrays.asList("Code", "Code Prefix", "exists");
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("meteringSupplyPointEdit");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		Tree.expandNavigatorTreeNode(driver, "slnmEnrgArea1 (slnmEnrgArea)");
		
		waitForExtJSAjaxComplete(20);
		
		Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickSystemViewTab();

		waitForExtJSAjaxComplete(20);
		
		setEffectiveDateMeterInfrastructure("05-01-2015");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite2");
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(25);
		
		Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickSystemViewTab();

		waitForExtJSAjaxComplete(20);
		
		setEffectiveDateMeterInfrastructure("05-01-2015");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		//////////////////////////Check if Type field is prefilled with Type marked as Default/////////////////////
		
		Reporter.log("Check if Type field is prefilled with Type marked as Default", true);
		
		softAssert.assertTrue(verifyLookupIsPrefilledWithDefaultValue(DIALOG_SP, "supplyPointType", "Reference", "Select a Supply Point Type"), "Type field is prefilled with Type marked as Default");
		
		////////////////////Check if Site field is prefilled with Site selected in Navigator//////////////////////
		
		Reporter.log("Check if Site field is prefilled with Site selected in Navigator", true);
		
		softAssert.assertEquals(McsElement.getFieldValue(driver, DIALOG_SP, "site"), site);
		
		//////////////////////////Check UI of mandatory fields (bold text, asterisk)////////////////
		
		Reporter.log("Check UI of mandatory fields (bold text, asterisk)", true);
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_SP, "codePrefix"), "Code Prefix field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_SP, "code"), "Code field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_SP, "reference"), "Reference field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_SP, "commodity"), "Commodity field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_SP, "flowDirection"), "Flow Direction field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_SP, "supplyPointType"), "Type field has correct UI (Bold text, asterix)");
		
		///////////////////////////Check UI of non-mandatory fields (regular text)//////////////////////////////
		
		Reporter.log("Check UI of non-mandatory fields (regular text)", true);
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_SP, "address1"), "Address 1 field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_SP, "address2"), "Address 2 field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_SP, "zipCode"), "Zip COde field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_SP, "city"), "City field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_SP, "country"), "Country field has correct UI");
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_SP, "description"), "Description field has correct UI");
		
		///////////////////////////////Check mandatory fields//////////////////////
		
		//Try to save Supply Point Form with empty Code Prefix field
		
		Reporter.log(">>>>>>>>>>>>Try to save Supply Point Form with empty Code Prefix field", true);
		
		McsElement.clearField(driver, DIALOG_SP, "codePrefix");
		
		setCode(incrementCode(code));
		
		setCommodity(commodity);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(),"Form can't be saved with empty Code Prefix field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_SP, "codePrefix"),"Red border is present on empty Code Prefix field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_SP, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenSupplyPointForm();
		
		//Try to save Supply Point Form with empty Code field
		
		Reporter.log(">>>>>>>>>>>>Try to save Supply Point Form with empty Code field", true);
		
		McsElement.clearField(driver, DIALOG_SP, "code");
		
		setCommodity(commodity);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(),"Form can't be saved with empty Code field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_SP, "code"),"Red border is present on empty Code field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_SP, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenSupplyPointForm();
		
		//Try to save Supply Point Form with empty Commodity field
		
		Reporter.log(">>>>>>>>>>>>Try to save Supply Point Form with empty Commodity field", true);
		
		McsElement.clearField(driver, DIALOG_SP, "commodity");
		
		setCode(incrementCode(code));
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(),"Form can't be saved with empty Commodity field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_SP, "commodity"),"Red border is present on empty Commodity field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_SP, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenSupplyPointForm();
		
		//Try to save Supply Point Form with empty Type field
		
		Reporter.log(">>>>>>>>>>>>Try to save Supply Point Form with empty Type field", true);
		
		McsElement.clearField(driver, DIALOG_SP, "supplyPointType");
		
		setCode(incrementCode(code));
		
		setCommodity(commodity);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(),"Form can't be saved with empty Type field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_SP, "supplyPointType"),"Red border is present on empty Type field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_SP, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenSupplyPointForm();
		
		/////////////////////////////Check uniqueness//////////////////////////////
		
		//Try to save Supply Point Form with non unique combination of Code and Code Prefix fields
		
		Reporter.log(">>>>>>>>>>>>Try to save Supply Point Form with non unique combination of Code and Code Prefix fields", true);
		
		setCode(codeNonUnique);
		
		setCodePrefix(codePrefixNonUnique);
		
		setCommodity(commodity);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(),"Form can't be saved with non unique combination of Code and Code Prefix fields"); 

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_SP, unqiueReferenceFieldErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenSupplyPointForm();
		
		///////////////////////////Check optional fields////////////////
		
		Reporter.log(">>>>>>>>>>>>Try to save Supply Point Form with with empty optional fields", true);
		
		setCodePrefix(codePrefix);
		
		setCode(incrementCode(code));
		
		setCommodity(commodity);
		
		waitForExtJSAjaxComplete(20);

		save();
		
		waitForExtJSAjaxComplete(20);

		close();
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(findMeterInHierarchy(codePrefix+code), "Supply Point was saved with empty optional fields");
		
		softAssert.assertAll();
		
	 }

	 
	 /**
	 * Test Case ID: C60575
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testSPPowerCapacityField() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: System View: Supply Points: Power Capacity field: C60575" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testSPPowerCapacityField");
		
		String comElectricity = "Electricity";
		String code = "C60575Cod"+getCurrentDate().substring(getCurrentDate().length()-5);
		String powerCap3DecValue = "1.333";
		String powerCap4DecValue = "1.3339";
		String powerCapRoundedValue = "1.334";
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		Tree.expandNavigatorTreeNode(driver, "slnmEnrgArea1 (slnmEnrgArea)");
		
		waitForExtJSAjaxComplete(20);
		
		Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickSystemViewTab();

		waitForExtJSAjaxComplete(20);
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);
		
		setEffectiveDateMeterInfrastructure("01-01-2016");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite2");
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickSystemViewTab();

		waitForExtJSAjaxComplete(20);
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);
		
		setEffectiveDateMeterInfrastructure("01-01-2016");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		setCommodity(comElectricity);
		
		waitForExtJSAjaxComplete(10);
		
		setSupplySystem(SUPPLY_POINT_GRID_CLASS, "High Voltage");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(getPowerCapacity().isEmpty(), "Power Capacity Field is Empty");
		
		setPowerCapacity(powerCap3DecValue);
		
		save();
		
		waitForExtJSAjaxComplete(5);
		
		setPowerCapacity(powerCap4DecValue);
		
		save();
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertEquals(getPowerCapacity(), powerCapRoundedValue, "The Power Capacity Fields rounded the Value if it has more than 3 decimals");
		
		softAssert.assertAll();
		
		Reporter.log("Supply Points: Power Capacity field is successfully verified");
	}
	
	
}
	 
	