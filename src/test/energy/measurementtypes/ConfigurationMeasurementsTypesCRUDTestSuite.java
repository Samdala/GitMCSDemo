package test.energy.measurementtypes;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class ConfigurationMeasurementsTypesCRUDTestSuite extends
		ConfigurationMeasurementTypesPageObject {


	/**
	 * Test case checks create and edit functionality for Measurement Types
	 */
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testConfigurationTypeStatusEdit() throws IOException  {

		String entity = "Measurement Types";
		String realId = "measurement_types_grid";
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Edit Energy Configuration " + entity + " </span><br>",
				true);

		Reporter.log("User edit configuration type or status: c10588 testrail  " + entity + " <br>",
				true);
		
		String code = "my auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "test auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		boolean defaultState = true;

//		String description = "description";
		
		String codeEdited = "my auto ed" + getCurrentDate().substring(getCurrentDate().length()-5);
		String referenceEdited = "test auto ed" + getCurrentDate().substring(getCurrentDate().length()-5);
		boolean defaultStateEdited = true;
		
//		String classValue = "Archived";
//		String descriptionEdited = "descriptionEdited";
		
//		expandApplication();

		// Click Energy in Navigation
		waitAndClick(XPATH_ENERGY);
		
		// wait Energy navigation panel
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		// Expand Configuration module 
		expandConfiguration();
		
		waitForExtJSAjaxComplete(25);
		
		// Select in Configuration some entity
		openConfigurationEntity(entity);

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		// Click Add button in the grid Measurement Types
		clickAddButton();
		
		// wait window 'Add measurement type'
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		// Fill code
		setCode(code);
		
		//Fill reference
		setReference(reference);
		
		// Make this entity Default
		checkDefault();
		
		// Save and close  window 'Add measurement type'
		saveClose();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		// Check this measurement type in grid
		Grid.checkRowInGriByTextValue(driver, code);
		
		// Click edit button in Measurement Types grid
		clickEditButton();
		
		//	Wait window 'Add measurement type'
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		// Verify code 
		Assert.assertEquals(getCode(),code, code+" code before edit is wrong");
		
		// Verify reference
		Assert.assertEquals(getReference(),reference, reference+" reference before edit is wrong");
		
		// Verify default state
		Assert.assertEquals(getDefaultState(),defaultState, defaultState + " defaultState before edit is wrong");
		
		// Fill new code
		setCode(codeEdited);
		
		// Fill new reference
		setReference(referenceEdited);
		
		// Save and close  window 'Add measurement type'
		saveClose();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		// Check this measurement type in grid
		Grid.checkRowInGriByTextValue(driver, codeEdited);
		
		// Click edit button in Measurement Types grid
		clickEditButton();
		
		//	Wait window 'Add measurement type'
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		// Verify code after edit
		Assert.assertEquals(getCode(),codeEdited, codeEdited+" code after edit is wrong");
		
		// Verify reference after edit
		Assert.assertEquals(getReference(),referenceEdited, referenceEdited+" reference after edit is wrong");
		
		// Verify default state after edit
		Assert.assertEquals(getDefaultState(),defaultStateEdited, defaultStateEdited + " defaultState after edit is wrong");

		// Close  window 'Add measurement type'
		close();
		
		Reporter.log("Configuration " + entity + " was succesfully edited", true);

	}

	
	/**
	 * Test case checks delete functionality for Measurement Types
	 */
	@Test(enabled = true)
	public void testMeasurementTypesDelete() throws Exception {
		
		String entity = "Measurement Types";
		String realId = "measurement_types_grid";

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Energy Configuration " + entity + " </span><br>",
				true);

		Reporter.log("User delete configuration type or status:c10589(testrail) " + entity + " <br>",
				true);
		
		String code = "my auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "test auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
		
		String messageText = "After Measurements are added to the system, you can only create new or modify custom Measurement Types";

		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testMeasurementTypesDelete");
		
//		expandApplication();

		// Click Energy in Navigation
		waitAndClick(XPATH_ENERGY);
		
		// wait Energy navigation panel
		waitForExtJSAjaxComplete(20);

		// Expand Configuration module 
		expandConfiguration();
		
		waitForExtJSAjaxComplete(25);
		
		// Select in Configuration some entity
		openConfigurationEntity(entity);

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		// Click Add button in the grid Measurement Types
		clickAddButton();
		
		// wait window 'Add measurement type'
		Thread.sleep(5000);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		// Fill code
		setCode(code);
		
		// Fill reference
		setReference(reference);
		
		// Save and close  window 'Add measurement type'
		saveClose();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		// Check this measurement type in grid
		Grid.checkRowInGriByTextValue(driver, code);
		
		// Click delete button in Measurement Types grid
		clickDeleteButton();
		
		waitForExtJSAjaxComplete(5);
		
		/*
		 * As Per new Workflow of Delete Measurement, any MEasurement Type can be delted and no error/warning message will be displayed 
		 * https://jira.mcs.be/browse/MYM-16474
		 */
		//if (buildVersion) {
			//Verify that measurement type entity is deleted
			Assert.assertTrue(Grid.isRowInGridAbsent(driver, code), code+" code after delete is present");
			
			Reporter.log("Configuration " + entity + " was succesfully deleted", true);
		/*}
		else {
			softAssert.assertTrue(verifyMessageDialogContainsText(messageText), messageText+" - message is displayed");
			
			Assert.assertFalse(Grid.isRowInGridAbsent(driver, code), code+" Measurement Type is not deleted");
		}*/

	}

	/**
	 * Test Case ID: C60983, C60985, C60986, C60987
	 * Author : SSU
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testFormValidationMeasurementTypes() {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:  Mandatory and Optional fields: C60983" + " </span><br>",
				true);
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Default Values: C60985" + " </span><br>",
				true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Uniqueness of fields: C60986" + " </span><br>",
				true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Specific field behaviour: C60987" + " </span><br>",
				true);
		
		String code = "preMTcode";
		String reference = "preMTref";
		String preMeasureRef = "Default";
		String realId = "measurement_types_grid";
		String defaultColName = "Default";
		String builtInColName = "Built-in";

		
		waitForExtJSAjaxComplete(30);		
		
		waitAndClick(XPATH_ENERGY);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testFormValidationMeasurementTypes");
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(25);
		
		openConfigurationEntity("Measurement Types");

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
			softAssert.assertTrue(getFieldValue(ADD_MEASUREMENT_TYPE_WINDOW_HEADER_TRUNK, "reference").isEmpty(), "No default value for reference");
			
			softAssert.assertTrue(getFieldValue(ADD_MEASUREMENT_TYPE_WINDOW_HEADER_TRUNK, "code").isEmpty(), "No default value for code");
			
			softAssert.assertEquals(getCheckBoxState(ADD_MEASUREMENT_TYPE_WINDOW_HEADER_TRUNK, "isDefault"), false, "Impact electricity is off");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "reference"),"Element reference has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "code"),"Element code has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(!McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "isDefault"),"Element Default has correct UI (plaint text, no asterix)");
		
		waitForExtJSAjaxComplete(10);
		
		setCode(code);
		
		setReference(reference);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue((getInfoBarMsgOfWindow("@id", getXWindowId(ADD_MEASUREMENT_TYPE_WINDOW_HEADER_TRUNK))).contains("The Measurement Type with this Code already exists."), "The Measurement Type with this Code already exists.");
		
		close();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		int defaultCount = verifyOnlyOneRowIsDefauted(defaultColName);
		
		int count = 1;
		
		softAssert.assertEquals(defaultCount, count, "Only One Row is Defaulted in the Grid");
		
		waitForExtJSAjaxComplete(10);
		
		int builtInCount = verifyOnlyOneRowIsDefauted(builtInColName);
		
		int counts = 3;
		int countTrunk = 5;
		
		softAssert.assertEquals(builtInCount, countTrunk, "5 Built In Rows are Defaulted in the Grid");
		
		waitForExtJSAjaxComplete(10);
		
		boolean builtInStatus = getBuiltInStatus("Default", "Reference", builtInColName);
		
		softAssert.assertTrue(builtInStatus, "Built In Field is checked For Default");
		
		builtInStatus = getBuiltInStatus("Overflow", "Reference", builtInColName);
		
		softAssert.assertTrue(builtInStatus, "Built In Field is checked For OverFlow");
		
		builtInStatus = getBuiltInStatus("Rollback", "Reference", builtInColName);
		
		softAssert.assertTrue(builtInStatus, "Built In Field is checked For Rollback");
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValue(driver, preMeasureRef);
		
		waitForExtJSAjaxComplete(5);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		

		boolean defaultState = getCheckBoxState(EDIT_MEASUREMENT_TYPE_WINDOW_HEADER, "isDefault");
		
		System.out.println(defaultState);
		
		boolean defaultStatus;

		if(!defaultState){
			
			System.out.println("1");
			
			checkDefault();
			
			save();
			
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);
			
			close();
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			defaultStatus = getBuiltInStatus("Default", "Reference", defaultColName);
			
			softAssert.assertTrue(defaultStatus, "Default Field is checked For Default Row");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
		} else {
			
			System.out.println("2");
			
			checkDefault();
			
			save();
			
			waitForExtJSAjaxComplete(5);
			
			softAssert.assertTrue((getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_MEASUREMENT_TYPE_WINDOW_HEADER))).contains("No Measurement Type has been set as default. Please set one as default before saving."), "Atleast One Row should be Defaulted");
			
			close();
			
			clickOnDialogButton("Yes");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			defaultStatus = getBuiltInStatus("Default", "Reference", defaultColName);
			
			softAssert.assertTrue(defaultStatus, "Default Field is checked For Default Row");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
		}
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertAll();
		
		Reporter.log("Form Validations in Measurement Types is Successfully verified", true);
		
	}
}
