package test.energy.measuretypes;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class ConfigurationMeasureTypesCRUDTestSuite extends
		ConfigurationMeasureTypesPageObject {


	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testConfigurationMeasureTypesEdit() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Edit Measure Types:c31563, C31564 </span><br>",
				true);

		Reporter.log("User edit Measure Types: <br>",
				true);
		
		String code = "my auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "test auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		boolean defaultState = true;
		
		String codeEdited = "my auto ed" + getCurrentDate().substring(getCurrentDate().length()-5);
		String referenceEdited = "test auto ed" + getCurrentDate().substring(getCurrentDate().length()-5);
		//boolean defaultStateEdited = false;


		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(20);
		
		openConfigurationEntity("Measure Types");

		waitForExtJSAjaxComplete(20);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		setReference(reference);
		
		checkDefault();
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		
		Grid.checkRowInGriByTextValue(driver, code);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertEquals(getCode(),code, code+" code before edit is wrong");
		
		Assert.assertEquals(getReference(),reference, reference+" reference before edit is wrong");
		
		Assert.assertEquals(getDefaultState(),defaultState, defaultState + " defaultState before edit is wrong");
		
		setCode(codeEdited);
		
		setReference(referenceEdited);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValue(driver, codeEdited);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertEquals(getCode(),codeEdited, codeEdited+" code after edit is wrong");
		
		Assert.assertEquals(getReference(),referenceEdited, referenceEdited+" reference after edit is wrong");

		close();
		
		Reporter.log("Configuration MeasureTypes was succesfully edited", true);

	}

	@Test(enabled = true,retryAnalyzer=RetryAnalyzer.class)
	public void testConfigurationMeasureTypesDelete() throws IllegalStateException, IOException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete MeasureTypes: 31563, 31565</span><br>",
				true);

		Reporter.log("User delete MeasureTypes <br>", true);
		
		String code = "my auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "test auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(25);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(25);
		
		openConfigurationEntity("Measure Types");

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setCode(code);
		
		setReference(reference);
		
		saveClose();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValue(driver, code);
		
		clickDeleteButton();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, code), code+" code after delete is present");
		
		Reporter.log("Configuration MeasureTypes was succesfully deleted", true);
	}
	
	/**
	 * Test Case ID: C60976, C60975, C60977, C60973
	 * Author : SSU
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testFormValidationMeasureTypes() {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Default values: C60975" + " </span><br>",
				true);
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Uniqueness of fields: C60976" + " </span><br>",
				true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Specific field behaviour: C60977" + " </span><br>",
				true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Mandatory and Optional fields: C60973" + " </span><br>",
				true);
		
		String code = "Default";
		String reference = "Default";
		String preMeasureRef = "preMeasureType1";
		
		waitForExtJSAjaxComplete(30);		
		
		waitAndClick(XPATH_ENERGY);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testFormValidationMeasureTypes");
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(25);
		
		openConfigurationEntity("Measure Types");

		waitForExtJSAjaxComplete(25);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getFieldValue(ADD_MEASURE_TYPE_WINDOW_HEADER, "reference"), "", "No default value for reference");
		
		softAssert.assertEquals(getFieldValue(ADD_MEASURE_TYPE_WINDOW_HEADER, "code"), "", "No default value for code");
		
		softAssert.assertEquals(getCheckBoxState(ADD_MEASURE_TYPE_WINDOW_HEADER, "isDefault"), false, "Impact electricity is off");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "reference"),"Element reference has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "code"),"Element code has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(!McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "isDefault"),"Element Default has correct UI (plaint text, no asterix)");
		
		waitForExtJSAjaxComplete(10);
		
		setCode(code);
		
		setReference(reference);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
			softAssert.assertTrue((getInfoBarMsgOfWindow("@id", getXWindowId(ADD_MEASURE_TYPE_WINDOW_HEADER))).contains("The Code for the Measure Type must be unique."), "The Code for Measure Type must be unique");
		
		close();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(25);
		
		int defaultCount = verifyOnlyOneRowIsDefauted();
		
		int count = 1;
		
		softAssert.assertEquals(defaultCount, count, "Only One Row is Defaulted in the Grid");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, preMeasureRef);
		
		waitForExtJSAjaxComplete(5);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(25);
		
		boolean defaultState = getCheckBoxState(EDIT_MEASURE_TYPE_WINDOW_HEADER, "isDefault");

		if(!defaultState){
			
			checkDefault();
			
			save();
			
			waitForExtJSAjaxComplete(10);
			
			close();
			
		} else {
			
			checkDefault();
			
			save();
			
			waitForExtJSAjaxComplete(5);
			
			softAssert.assertTrue((getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_MEASURE_TYPE_WINDOW_HEADER))).contains("No Measure Type has been set as default. Please set one as default before saving."), "Atleast One Row should be Defaulted");
			
			close();
			
			clickOnDialogButton("Yes");
			
			waitForExtJSAjaxComplete(10);
		}
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertAll();
		
		Reporter.log("Form Validations in Measure Types is Successfully verified", true);
		
	}

}
