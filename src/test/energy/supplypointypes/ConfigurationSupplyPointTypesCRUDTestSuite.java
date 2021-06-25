package test.energy.supplypointypes;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class ConfigurationSupplyPointTypesCRUDTestSuite extends
		ConfigurationSupplyPointTypesPageObject {


	@DataProvider
	public Object[][] configuration() {
		return new Object[][] {
		{"Supply Point Types", "supply_point_types_grid"}
		};
	}


	 @Test(enabled=true,dataProvider = "configuration")
	public void testConfigurationTypeStatusEdit(String entity, String realId) throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Edit Energy Configuration " + entity + " </span><br>",
				true);

		Reporter.log("User edit configuration type or status:c10586, c11605(testrail) " + entity + " <br>",
				true);
		
		String code = "my auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "test auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		boolean defaultState = true;
//		String classValue = "Active";
		String description = "description";
		
		String codeEdited = "my auto ed" + getCurrentDate().substring(getCurrentDate().length()-5);
		String referenceEdited = "test auto ed" + getCurrentDate().substring(getCurrentDate().length()-5);
		boolean defaultStateEdited = true;
		String classValue = "Archived";
		String descriptionEdited = "descriptionEdited";
		
//		expandApplication();

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(25);
		
		openConfigurationEntity(entity);

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddButton(realId);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		setCode(code);
		
		setReference(reference);
		
		checkDefault();
		
		if(entity.equals("Supply Point Types"))
		{setDescription(description);}
		
		if(entity.equals("Metering Object Statuses"))
		{setClassValueJavascript(classValue);
			//setClassValue(classValue);
			}
		
		saveClose();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValue(driver, code);
		
		clickEditButton(realId);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		Assert.assertEquals(getCode(EDIT_SUPPLY_POINT_WINDOW_HEADER, "value"),code, code+" code before edit is wrong");
		
		Assert.assertEquals(getReference(EDIT_SUPPLY_POINT_WINDOW_HEADER, "value"),reference, reference+" reference before edit is wrong");
		
		Assert.assertEquals(getDefaultState(),defaultState, defaultState + " defaultState before edit is wrong");
		
		if(entity.equals("Supply Point Types"))
		{Assert.assertEquals(getDescription(),description, description+" description before edit is wrong");}

		if(entity.equals("Metering Object Statuses"))
		{Assert.assertEquals(getClassValue(),classValue, classValue+" classValue before edit is wrong");}
		
		setCode(codeEdited);
		
		setReference(referenceEdited);
//we can not uncheck default if there is no default entity		
//		uncheckDefault();
		
		if(entity.equals("Supply Point Types"))
		{setDescription(descriptionEdited);}

//Class can not be changed after creation?		
//		if(entity.equals("Metering Object Statuses"))
//		{setClassValue(classValue);}
		
		saveClose();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		Grid.checkRowInGriByTextValue(driver, codeEdited);
		
		clickEditButton(realId);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		Assert.assertEquals(getCode(EDIT_SUPPLY_POINT_WINDOW_HEADER, "value"),codeEdited, codeEdited+" code after edit is wrong");
		
		Assert.assertEquals(getReference(EDIT_SUPPLY_POINT_WINDOW_HEADER, "value"),referenceEdited, referenceEdited+" reference after edit is wrong");
		
		Assert.assertEquals(getDefaultState(),defaultStateEdited, defaultStateEdited + " defaultState after edit is wrong");
		
		if(entity.equals("Supply Point Types"))
		{Assert.assertEquals(getDescription(),descriptionEdited, descriptionEdited+" description after edit is wrong");}

		if(entity.equals("Metering Object Statuses"))
		{Assert.assertEquals(getClassValue(),classValue, classValue+" classValue after edit is wrong");}

		close();
		
		Reporter.log("Configuration " + entity + " was succesfully edited", true);

	}

	@Test(enabled = true, dataProvider = "configuration")
	public void testOverviewDefinitionDelete(String entity, String realId) throws IllegalStateException, IOException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Energy Configuration " + entity + " </span><br>",
				true);

		Reporter.log("User delete configuration type or status:c10587(testrail) " + entity + " <br>",
				true);
		
		String code = "my auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "test auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
//		String classValue = "Active";
		
		
//		expandApplication();

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(25);
		
		openConfigurationEntity(entity);

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddButton(realId);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setCode(code);
		
		setReference(reference);

//default class id active		
//		if(entity.equals("Metering Object Statuses"))
//		{setClassValue(classValue);}
		
		saveClose();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValue(driver, code);
		
		clickDeleteButton(realId);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, code), code+" code after delete is present");
		
		Reporter.log("Configuration " + entity + " was succesfully deleted", true);
		
	}
	
	/**
	 * Test Case ID: C60945, C60947, C60948
	 * Author : SSU
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testFormValidationSupplyPointTypes() {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:  Mandatory and Optional fields: C60945" + " </span><br>",
				true);
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Default Values: C60947" + " </span><br>",
				true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Uniqueness of fields: C60948" + " </span><br>",
				true);

		String code = "preSPTcode";
		String reference = "preSPTref";
		String defaultRef = "Default";
		String default122Ref = "DEFAULT";
		String unofficialRef = "Unofficial";
		String officialRef = "Official";
		String realId = "supply_point_types_grid";
		String defaultColName = "Default";
		String builtInColName = "Built-in";
		
		waitForExtJSAjaxComplete(30);		
		
		waitAndClick(XPATH_ENERGY);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testFormValidationSupplyPointTypes");
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(25);
		
		openConfigurationEntity("Supply Point Types");

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddButton(realId);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getFieldValue(ADD_SUPPLY_POINT_WINDOW_HEADER, "reference"), "", "No default value for reference");
		
		softAssert.assertEquals(getFieldValue(ADD_SUPPLY_POINT_WINDOW_HEADER, "code"), "", "No default value for code");
		
		softAssert.assertEquals(getTextAreaValue(ADD_SUPPLY_POINT_WINDOW_HEADER, "description"), "", "No default value for description");
		
		softAssert.assertEquals(getCheckBoxState(ADD_SUPPLY_POINT_WINDOW_HEADER, "isDefault"), false, "Impact electricity is off");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "reference"),"Element reference has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "code"),"Element code has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(!McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "description"),"Element Default has correct UI (plaint text, no asterix)");
		
		softAssert.assertTrue(!McsElement.isFieldMandatory(driver, XPATH_ADD_FORM_WINDOW, "isDefault"),"Element Default has correct UI (plaint text, no asterix)");
		
		waitForExtJSAjaxComplete(10);
		
		setCode(code);
		
		setReference(reference);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue((getInfoBarMsgOfWindow("@id", getXWindowId(ADD_SUPPLY_POINT_WINDOW_HEADER))).contains("The Supply Point Type with this Code already exists."), "The Supply Point Type with this Code already exists.");
		
		close();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(25);
		
		int defaultCount = verifyOnlyOneRowIsDefauted(defaultColName);
		
		int count = 1;
		
		softAssert.assertEquals(defaultCount, count, "Only One Row is Defaulted in the Grid");
		
		waitForExtJSAjaxComplete(10);
		
		int builtInCount = verifyOnlyOneRowIsDefauted(builtInColName);
		
		int counts = 3;
		
		softAssert.assertEquals(builtInCount, counts, "3 Built In Rows are Defaulted in the Grid");
		
		waitForExtJSAjaxComplete(10);
					
		boolean builtInStatus = getBuiltInStatus("Default", "Reference", builtInColName);
		
		softAssert.assertTrue(builtInStatus, "Built In Field is checked For Default");
		
		builtInStatus = getBuiltInStatus("Official", "Reference", builtInColName);
		
		softAssert.assertTrue(builtInStatus, "Built In Field is checked For Official");
		
		builtInStatus = getBuiltInStatus("Unofficial", "Reference", builtInColName);
		
		softAssert.assertTrue(builtInStatus, "Built In Field is checked For Unofficial");
	
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValue(driver, defaultRef);
		
		clickEditButton(realId);
		
		waitForExtJSAjaxComplete(25);
		
		//Verify Defaulted Values & Fields UnEditable
		
		String defaultCode = getCode(EDIT_SUPPLY_POINT_WINDOW_HEADER, "value");
		
		softAssert.assertEquals(defaultCode, "DEFAULT", "Default is automatically populated in Code");
		
		waitForExtJSAjaxComplete(5);
		
		String defaultClass = getCode(EDIT_SUPPLY_POINT_WINDOW_HEADER, "class");
		
		softAssert.assertTrue(defaultClass.contains("x-item-disabled"), "Default Code is UnEditable");
		
		waitForExtJSAjaxComplete(5);
		
		defaultRef = getReference(EDIT_SUPPLY_POINT_WINDOW_HEADER, "value");
		
		softAssert.assertEquals(defaultRef, "Default", "Default is automatically populated in Reference");
		
		waitForExtJSAjaxComplete(5);
		
		defaultClass = getReference(EDIT_SUPPLY_POINT_WINDOW_HEADER, "class");
		
		softAssert.assertTrue(defaultClass.contains("x-item-disabled"), "Default Reference is UnEditable");
		
		waitForExtJSAjaxComplete(5);
		
		close();
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, unofficialRef);
		
		clickEditButton(realId);
		
		waitForExtJSAjaxComplete(5);
		
		String unOfficialCode = getCode(EDIT_SUPPLY_POINT_WINDOW_HEADER, "value");
		
		softAssert.assertEquals(unOfficialCode, "UNOFFICIAL", "Unofficial is automatically populated in Code");
		
		waitForExtJSAjaxComplete(5);
		
		String unofficialClass = getCode(EDIT_SUPPLY_POINT_WINDOW_HEADER, "class");
		
		softAssert.assertTrue(unofficialClass.contains("x-item-disabled"), "Unofficial Code is UnEditable");
		
		waitForExtJSAjaxComplete(5);
		
		unofficialRef = getReference(EDIT_SUPPLY_POINT_WINDOW_HEADER, "value");
		
		softAssert.assertEquals(unofficialRef, "Unofficial", "Unofficial is automatically populated in Reference");
		
		waitForExtJSAjaxComplete(5);
		
		unofficialClass = getReference(EDIT_SUPPLY_POINT_WINDOW_HEADER, "class");
		
		softAssert.assertTrue(unofficialClass.contains("x-item-disabled"), "Unofficial Reference is UnEditable");
		
		waitForExtJSAjaxComplete(25);
		
		close();
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, officialRef);
		
		clickEditButton(realId);
		
		waitForExtJSAjaxComplete(5);
		
		String officialCode = getCode(EDIT_SUPPLY_POINT_WINDOW_HEADER, "value");
		
		softAssert.assertEquals(officialCode, "OFFICIAL", "Official is automatically populated in Code");
		
		waitForExtJSAjaxComplete(5);
		
		String OfficialClass = getCode(EDIT_SUPPLY_POINT_WINDOW_HEADER, "class");
		
		softAssert.assertTrue(OfficialClass.contains("x-item-disabled"), "Unofficial Code is UnEditable");
		
		waitForExtJSAjaxComplete(5);
		
		officialRef = getReference(EDIT_SUPPLY_POINT_WINDOW_HEADER, "value");
		
		softAssert.assertEquals(officialRef, "Official", "Official is automatically populated in Reference");
		
		waitForExtJSAjaxComplete(5);
		
		OfficialClass = getReference(EDIT_SUPPLY_POINT_WINDOW_HEADER, "class");
		
		softAssert.assertTrue(OfficialClass.contains("x-item-disabled"), "Unofficial Reference is UnEditable");
		
		waitForExtJSAjaxComplete(25);
		
		close();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValue(driver, default122Ref);
		
		waitForExtJSAjaxComplete(25);
		
		clickEditButton(realId);
		
		waitForExtJSAjaxComplete(10);
		
		boolean defaultState = getCheckBoxState(EDIT_SUPPLY_POINT_WINDOW_HEADER, "isDefault");

		if(!defaultState) {
			
			checkDefault();
			
			save();
			
			waitForExtJSAjaxComplete(10);
			
		} else {
			
			checkDefault();
			
			save();
			
			waitForExtJSAjaxComplete(5);
			
			softAssert.assertTrue((getInfoBarMsgOfWindow("@id", getXWindowId(EDIT_SUPPLY_POINT_WINDOW_HEADER))).contains("No Supply Point Type has been set as default. Please set one as default before saving."), "Atleast One Row should be Defaulted");
			
			close();
			
			clickOnDialogButton("Yes");
			
			waitForExtJSAjaxComplete(10);
			
		}
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("Form Validations in Supply Point Types is Successfully verified", true);
		
	}

}
