package test.energy.gauges;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.energy.meters.MetersPageObject;
import test.energy.navigator.NavigatorPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import test.framework.webelement.Tree;

public class GaugeFormValidationTestSuite extends MeteringGaugesPageObject {
	


	 @Test(enabled=true)
	public void testMeterCreateEdit() throws Exception  {

		 //TODO Change to proper later
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Check Gauge form validation: ?????" + " </span><br>",
				true);

		Reporter.log("User tries to create Gauge with different validations"  + " <br>",
				true);
		
		//Field values for Meter creation
		
		String reference = "test auto mtr " + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto mtr " + getCurrentDate().substring(getCurrentDate().length()-6);
		String codeNonUnique = "1preGaugeCopy";
		String status = "Active";
		String timeZone = "(GMT+02:00) Athens, Bucharest, Istanbul";
		String scope1 = "slnmEnrgBuilding1.2";
		String scope2 = "slnmEnrgBuilding1.3";
		String scope3 = "slnmEnrgBuilding1.4";
		String scope4 = "slnmEnrgBuilding1.5";
		String scope5 = "slnmEnrgBuilding1.6";
		String scope6 = "slnmEnrgBuilding1.1";
		
		List<String> mandatoryFieldsErrorMessageKeyWordsCheck =Arrays.asList("form", "invalid");
		List<String> unqiueCodeFieldErrorMessageKeyWordsCheck =Arrays.asList("Code", "exists");
		
		//Navigator
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("GaugesFormValidation");

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		openMeteringEntity("Gauges");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton(GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Status field is prefilled with Status marked as Default", true);
		
		String fieldValue = getFieldValue(ADD_GAUGES_FORM_CLASS, "status");
		
		clickLookup(ADD_GAUGES_FORM_CLASS, "status");
		
		String lookupId = getXWindowId("Select a Metering Object Status");
		
		softAssert.assertTrue(checkDefaultField("@id", lookupId, "Reference", fieldValue), "Status field is prefilled with Status marked as Default");
		
		clickCANCELXwindow();
		
		//Check if Time Zone field is prefilled with Time Zone marked as Default
		
		Reporter.log("Check if Time Zone field is prefilled with Time Zone marked as Default", true);
		
		softAssert.assertEquals(getFieldValue(ADD_GAUGES_FORM_CLASS, "timezone"), "(GMT+01:00) Brussels, Copenhagen, Madrid, Paris", "Time Zone field is prefilled with default time zone");
		
		//Check if Class field is prefilled with "Energy Gauge" value
		
		Reporter.log("Check if Class field is prefilled with 'Energy Gauge' value", true);
		
		softAssert.assertEquals(getObjectClass(), "Energy Gauge", "Class field is prefilled with 'Energy Gauge' value");
		
		//Check UI of mandatory fields (bold text, asterisk)
		
		Reporter.log("Check UI of mandatory fields (bold text, asterisk)", true);
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "code"), "Code field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "reference"), "Reference field has correct UI (Bold text, asterix)");
		
		//TODO Change for 12.2
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "scopeObject"), "Scope field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "timezone"), "Time Zone field has correct UI (Bold text, asterix)");
		
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "status"), "Status field has correct UI (Bold text, asterix)");
		
		//Check UI of non-mandatory fields (regular text)
		
		Reporter.log("Check UI of non-mandatory fields (regular text)", true);
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "commissioningDate"), "Commissioning Date field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "model"), "Model field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "serialNumber"), "Serial Number field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "location"), "Physical Location field has correct UI");
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "manufacturer"), "Manufacturer field has correct UI");
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "description"), "Description field has correct UI");
		
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "accessDirectives"), "Access Instructions has correct UI");
		
		//Try to save Meter Form with empty Reference field
		
		Reporter.log("Try to save Gauge Form with empty Reference field", true);
		
		clearField("reference", "Reference");
		
		setCode(code);
		
		setScope(scope1);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_GAUGES_FORM_CLASS),"Form can't be saved with empty Reference field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_GAUGES_FORM_CLASS, "reference"),"Red border is present on empty Reference field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_GAUGES_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenGaugeForm();

		//Try to save Meter Form with empty Code field
		
		Reporter.log("Try to save Gauge Form with empty Code field", true);
		
		clearField("code", "Code");
		
		setReference(reference+"1");
		
		setScope(scope2);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_GAUGES_FORM_CLASS),"Form can't be saved with empty Code field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_GAUGES_FORM_CLASS, "code"),"Red border is present on empty Code field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_GAUGES_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenGaugeForm();

		//Try to save Meter Form with empty Status field
		
		Reporter.log("Try to save Gauge Form with empty Status field", true);
		
		clearField("status", "Status");
		
		setCode(code+"2");
		
		setReference(reference+"2");
		
		setScope(scope3);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_GAUGES_FORM_CLASS),"Form can't be saved with empty Status field");
		
		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_GAUGES_FORM_CLASS, "status"),"Red border is present on empty Status field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_GAUGES_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenGaugeForm();

		//Try to save Meter Form with empty Time Zone field
		
		Reporter.log("Try to save Gauge Form with empty Time Zone field", true);
		
		clearField("timezone", "Time Zone");
		
		setStatus(status);
		
		setCode(code+"3");
		
		setReference(reference+"3");
		
		setScope(scope4);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_GAUGES_FORM_CLASS),"Form can't be saved with empty Time Zone field"); 
		
		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_GAUGES_FORM_CLASS, "timezone"),"Red border is present on empty Time Zone field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_GAUGES_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenGaugeForm();

		//Try to save Meter Form with empty Scope field
		
		Reporter.log("Try to save Gauge Form with empty Scope field", true);
		
		clearField("scopeObject", "Scope");
		
		setTimeZone(timeZone);
		
		setStatus(status);
		
		setCode(code+"4");
		
		setReference(reference+"4");
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_GAUGES_FORM_CLASS),"Form can't be saved with empty Commodity field"); 
		
		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, ADD_GAUGES_FORM_CLASS, "scopeObject"),"Red border is present on empty Commodity field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_GAUGES_FORM_CLASS, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
		
		reopenGaugeForm();
		
		//Try to save Meter Form with non-unique Code field
		
		Reporter.log("Try to save Gauge Form with non-unique Code field", true);
		
		setCode(codeNonUnique);
		
		setReference(reference+"5");
		
		setTimeZone(timeZone);
		
		waitForExtJSAjaxComplete(20);
		
		setScope(scope5);
		
		waitForExtJSAjaxComplete(20);
		
		setStatus(status);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(ADD_GAUGES_FORM_CLASS),"Form can't be saved with non-unique Code field"); 
		
		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, ADD_GAUGES_FORM_CLASS, unqiueCodeFieldErrorMessageKeyWordsCheck),"Message about non-unique Code field is present"); 
		
		reopenGaugeForm();

		//Try to save Meter Form form with empty optional fields
		
		Reporter.log("Try to save Gauge Form form with empty optional fields", true);
		
		setCode(code+"6");
		
		setReference(reference+"6");
		
		setTimeZone(timeZone);
		
		waitForExtJSAjaxComplete(20);
		
		setScope(scope6);
		
		waitForExtJSAjaxComplete(20);
		
		setStatus(status);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		close();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, reference+"6","@class" ,GAUGES_GRID_CLASS),"Gauge is created with empty optional fields and displayed on Gauges Overview"); 
		
		softAssert.assertAll();
		
	 }

	 /**
	  * Test Case ID : C60879
	  * Author : SSU
	  */
	 @Test(enabled=true)
	 public void testGaugesSpecificFieldBehaviour() throws Exception {
		 
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Properties tab: Specific Field Behaviour: C60879"
				+ " </span><br>", true);

		String reference = "C60879ref"+ getCurrentDate().substring(getCurrentDate().length() - 6);
		String code = "C60879cod"+ getCurrentDate().substring(getCurrentDate().length() - 6);
		String statusActive = "Active";
		String statusArchived = "Archive";
		String scope5 = "slnmEnrgBuilding7";
		
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite3 (site3)";
		String locationType = "Energy Object";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testGaugesSpecificFieldBehaviour");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		NavigatorPageObject navigatorPO = new NavigatorPageObject();
		
		navigatorPO.expandNavigator();
		
		waitForExtJSAjaxComplete(25);
		
		Tree.expandNavigatorTreeNode(driver, area);
		
		waitForExtJSAjaxComplete(25);
		
		Tree.expandNavigatorTreeNode(driver, site);
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Tree.checkNodeInNavigatorTreeByTextValue(driver, scope5);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		String panelID = getXPanelId(locationType + " \"" +scope5 + "\"");
		
		waitForExtJSAjaxComplete(20);
	
		navigatorPO.changeTab(panelID, "Gauges");
	
		waitForExtJSAjaxComplete(25);
		
		
		waitForExtJSAjaxComplete(25);
		
		boolean status1 = Grid.isRowInGridPresentLike(driver, "Energy Gauge", "@class", GAUGE_GRID_CLASS);
		
		if(status1) {
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGE_GRID_CLASS, "Energy Gauge");
				
			navigatorPO.clickDeleteBtn();

			waitForExtJSAjaxComplete(10);
		}	
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		expandMetering();
		
		waitForExtJSAjaxComplete(20);
		
		openMeteringEntity("Gauges");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddButton(GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Status field is prefilled with Status marked as Default", true);
		
		String scopeValue = getScope();
		
			softAssert.assertTrue(scopeValue.contains("Select an Energy Object"), "Scope field is Empty");
		
		waitForExtJSAjaxComplete(10);
		
		String physicalLocation = getLocation();
		
			softAssert.assertTrue(physicalLocation.isEmpty(), "Physical Location field is Empty");
		
		waitForExtJSAjaxComplete(10);
		
		setCode(code);
		
		setReference(reference);
		
		setStatus(statusActive);
		
		waitForExtJSAjaxComplete(10);
		
		setScope(scope5);
		
		waitForExtJSAjaxComplete(10);
		
			setLocation(scope5);
			
			waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(10);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		changeTab("channels");
		
		waitForExtJSAjaxComplete(20);

		clickViewMeasurementsButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickMeasurementsTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickButton("Add", DIALOG_GAUGE_CHNL);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(getFieldValue(DIALOG_MEASUREMENT, "reader").equals("AQA SELENIUM")||getFieldValue(DIALOG_MEASUREMENT, "reader").equals("ENERGY AQA")||getFieldValue(DIALOG_MEASUREMENT, "reader").equals("SELENIUM AQA"), "Reader field is prefilled with User logged in");
		
		waitForExtJSAjaxComplete(10);
		
		closeUsingWindowTitle(DIALOG_MEASUREMENT);
		
		waitForExtJSAjaxComplete(10);
		
		closeUsingToolBarGauges(DIALOG_GAUGE_CHNL);
		
		waitForExtJSAjaxComplete(10);
		
		changeTab("properties");
		
		waitForExtJSAjaxComplete(10);
		
		setStatus(statusArchived);
		
		waitForExtJSAjaxComplete(10);
		
		save();
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(25);
		
		changeTab("channels");
		
		waitForExtJSAjaxComplete(20);

		clickViewMeasurementsButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickMeasurementsTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickButton("Add", DIALOG_GAUGE_CHNL);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(getWarningMessage().contains("You cannot add/modify Channel Measurements for archived Metering Objects."), "It is not possible to add Measurements to Gauges when status is Archived");
		
		Reporter.log("User Cannot add Measurements to Gauges when the Status is Archived", true);
		
		waitForExtJSAjaxComplete(10);
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(10);
		
		closeUsingToolBarGauges(DIALOG_GAUGE_CHNL);
		
		waitForExtJSAjaxComplete(10);
		
		close();
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, reference);
		
		waitForExtJSAjaxComplete(10);
		
		clickDeleteButton(GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		MetersPageObject metersPO = new MetersPageObject();
		
		metersPO.expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		Tree.expandNavigatorTreeNode(driver, area);
		
		Thread.sleep(5000);
		
		Tree.expandNavigatorTreeNode(driver, site);
		waitForExtJSAjaxComplete(25);
		
		Thread.sleep(5000);
		
		Tree.checkNodeInNavigatorTreeByTextValue(driver, scope5);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
			panelID = getXPanelId(locationType + " \"" +scope5 + "\"");
		
			waitForExtJSAjaxComplete(25);
			
			navigatorPO.changeTab(panelID, "Gauges");
		
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
		
		navigatorPO.clickAddBtn();
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(getScope().contains(scope5), "Scope is PrePopulated in Navigator -> Gauges");
		
		softAssert.assertAll();
		
		Reporter.log("Properties tab: Specific Field Behaviour is successfully verified", true);
	 }
}
