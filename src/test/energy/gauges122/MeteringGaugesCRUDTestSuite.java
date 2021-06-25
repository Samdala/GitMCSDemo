package test.energy.gauges122;

import java.io.IOException;




import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.energy.navigator.NavigatorPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class MeteringGaugesCRUDTestSuite extends
		MeteringGaugesPageObject {




	 @Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testAnalysisGaugesEdit() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Edit Gauges " + " </span><br>",
				true);

		Reporter.log("User edits Gauges: c11601 "  + " <br>",
				true);
		
		String date = "03-09-2013";
		String reference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String serialNumber = "13";
		String description = "description";
		String scope = "slnmEnrgBuilding1";
		String model = "Model";
		String timeZone = "(GMT+02:00) Athens, Bucharest, Istanbul";
		String status = "Active";
		String manufacturer = "Manufacturer";
		String location = "slnmEnrgBuilding1";
		
		
		
		String dateEdited = "05-10-2014";
		String referenceEdited = "test auto gau ed" + getCurrentDate().substring(getCurrentDate().length()-6);
		String codeEdited = "test auto gau" + getCurrentDate().substring(getCurrentDate().length()-6);
		String serialNumberEdited = "13";
		String descriptionEdited = "description";
		String scopeEdited = "slnmEnrgBuilding1";
		String modelEdited = "Model";
		String timeZoneEdited = "(GMT+02:00) Athens, Bucharest, Istanbul";
		String statusEdited = "Active";
		String manufacturerEdited = "Manufacturer";
		String locationEdited = "slnmEnrgBuilding1";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("GaugesEdit");

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		openMeteringEntity("Gauges");
		

		waitForExtJSAjaxComplete(20);
		
		clickAddButton(GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
	
		setCode(code);
		
		setReference(reference);
		
		setCommissioningDate(date);
		
		waitForExtJSAjaxComplete(25);
		
		setSerialNumber(serialNumber);
		
		setManufacturer(manufacturer);
		
		setDescription(description);
		
		setStatus(status);
		
		waitForExtJSAjaxComplete(25);
		
		setScope(scope);
		
		waitForExtJSAjaxComplete(25);
		
		setModel(model);
		
		setTimeZone(timeZone);
		
		waitForExtJSAjaxComplete(25);
		
		setLocation(location);
		
		waitForExtJSAjaxComplete(25);
		
		save();
		
		waitForExtJSAjaxComplete(25);
		
		//changeTab("channels");
		Assert.assertTrue(isRowInGridChanelPresent(reference), "Chanel with reference: " + reference + "wasn't created.");
		
		close();
		
		Grid.checkRowInGriByTextValue(driver, reference);
		
		clickEditButton(GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getReference(),reference, reference+" reference before edit is wrong");
		
		softAssert.assertEquals(getCode(),code, code+ " date before edit is wrong");
		
		softAssert.assertEquals(getCommissioningDate(),date, date+ " proposer before edit is wrong");
		
		softAssert.assertEquals(getStatus(),status, status+ " status before edit is wrong");
		
		softAssert.assertEquals(getSerialNumber(),serialNumber, serialNumber+ " Seria lNumber before edit is wrong");
		
		softAssert.assertEquals(getDescription(),description, description+ " Description type before edit is wrong");
		
		softAssert.assertEquals(getScope(),scope, scope+ " Scope before edit is wrong");
		
		softAssert.assertEquals(getModel(),model, model+ " Model End use before edit is wrong");
		
		softAssert.assertEquals(getTimeZone(),timeZone, timeZone+ " TimeZone before edit is wrong");
		
		softAssert.assertEquals(getLocation(),location, location+ " Location Saving UOM before edit is wrong");

		
		setCode(codeEdited);
		
		setReference(referenceEdited);
		
		setCommissioningDate(dateEdited);
		
		waitForExtJSAjaxComplete(25);
		
		setSerialNumber(serialNumberEdited);
		
		setManufacturer(manufacturerEdited);
		
		setDescription(descriptionEdited);
		
		setStatus(statusEdited);
		
		waitForExtJSAjaxComplete(25);
		
		//setScope(scopeEdited);
		
		//waitForExtJSAjaxComplete(25);
		
		setModel(modelEdited);
		
		setTimeZone(timeZoneEdited);
		
		waitForExtJSAjaxComplete(25);
		
		setLocation(locationEdited);
		
		waitForExtJSAjaxComplete(25);
		
		saveClose();

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, referenceEdited,"@class", "x-grid3"), "edited gauges is not present");
		
		clickEditButton(GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getReference(),referenceEdited, referenceEdited+" referenceEdited is wrong");
		
		softAssert.assertEquals(getCode(),codeEdited, codeEdited+ " date before edit is wrong");
		
		softAssert.assertEquals(getCommissioningDate(),dateEdited, dateEdited+ " proposer is wrong");
		
		softAssert.assertEquals(getStatus(),statusEdited, statusEdited+ " status is wrong");
		
		softAssert.assertEquals(getSerialNumber(),serialNumberEdited, serialNumberEdited+ " Serial Number is wrong");
		
		softAssert.assertEquals(getDescription(),descriptionEdited, descriptionEdited+ " Description type is wrong");
		
		softAssert.assertEquals(getScope(),scopeEdited, scopeEdited+ " Scope is wrong");
		
		softAssert.assertEquals(getModel(),modelEdited, modelEdited+ " Model End use is wrong");
		
		softAssert.assertEquals(getTimeZone(),timeZoneEdited, timeZoneEdited+ " TimeZone is wrong");
		
		softAssert.assertEquals(getLocation(),locationEdited, locationEdited+ " Location Saving UOM is wrong");

		
		softAssert.assertAll();

		close();
		
		Reporter.log("Gauges "  + " was succesfully edited", true);

	}

	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testOverviewDefinitionDelete() throws IllegalStateException, IOException {

		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Gauges " + " </span><br>",
				true);

		Reporter.log("User deletes Gauges: c10225 "  + " <br>",
				true);
		
		String reference = "test auto gau del" + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto gau del" + getCurrentDate().substring(getCurrentDate().length()-6);
		String scope = "slnmEnrgBuilding5";
		String timeZone = "(GMT+02:00) Athens, Bucharest, Istanbul";
		String status = "Active";
	

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		openMeteringEntity("Gauges");

		waitForExtJSAjaxComplete(20);
		
		clickAddButton(GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setReference(reference);	
		
		setCode(code);
		
		setReference(reference);
		
		setStatus(status);
		
		waitForExtJSAjaxComplete(25);
		
		setScope(scope);
		
		waitForExtJSAjaxComplete(25);
		
		setTimeZone(timeZone);
		
		waitForExtJSAjaxComplete(25);
		
		save();
		
		close();
		
		Grid.checkRowInGriByTextValue(driver, reference);
		
		clickDeleteButton(GAUGES_GRID_CLASS);
				
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, reference), reference+" reference after delete is present");
		
		Reporter.log("Gauges was succesfully deleted", true);
		
	}
	
	/**
	 * Test Case ID: C60435
	 * Author : SSU
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testVisibilityOfAllGaugeFields() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Visibility of all added information to gauge in Overview page Metering Menu : C60435" + " </span><br>", 
				true);
		
		String scope5 = "slnmEnrgBuilding6";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testVisibilityOfAllGaugeFields");
		
		waitForExtJSAjaxComplete(30);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		openMeteringEntity("Gauges");

		waitForExtJSAjaxComplete(20);
		
		NavigatorPageObject navigatorPageObject = new NavigatorPageObject();
		
		waitForExtJSAjaxComplete(20);
		
		String code = "C60435cod"+getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "C60435ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String statusActive = "Active";
		
		String commisioningDate = getFutureDate(0);
		String classRef = "Location Gauge";
		String serialNo = "1";
		String manufacturer = "manufacturer";
		String description = "C60435Desc";
		String model = "Model";
		String accessInstructions = "C60435AccessIns";
		String timeZone = "(GMT+01:00) Brussels, Copenhagen, Madrid, Paris";
		String notes = "Test Notes Field";
		
		clickAddButton(GAUGES_GRID_CLASS);
			
		waitForExtJSAjaxComplete(10);
			
		setCode(code);
			
		setReference(reference);
			
		setStatus(statusActive);
			
		waitForExtJSAjaxComplete(10);
			
		setTimeZone(timeZone);
			
		waitForExtJSAjaxComplete(10);
	
		setScope(scope5);
		
		waitForExtJSAjaxComplete(10);
			
		setCommissioningDate(commisioningDate);
			
		setSerialNumber(serialNo);
			
		setManufacturer(manufacturer);
			
		setDescription(description);
			
		setModel(model);
			
		setAccessInstructions(accessInstructions);
			
		setLocation(scope5);
			
		waitForExtJSAjaxComplete(10);
			
		changeTab("notes");
			
		waitForExtJSAjaxComplete(20);
			
		setNotes(notes);
			
		waitForExtJSAjaxComplete(10);
			
		save();
			
		waitForExtJSAjaxComplete(20);
			
		changeTab("properties");
			
		waitForExtJSAjaxComplete(10);
			
		String idValue = getItemID("value");
			
		close();
			
		waitForExtJSAjaxComplete(20);
			
		navigatorPageObject.clickChangeVisibleColumns();
			
		waitForExtJSAjaxComplete(10);
		
		String changeColumnsWindowID = getXWindowIdByClass(CHANGE_COLUMNS_CLASS_NAME);
		System.out.println("GGGGG " +changeColumnsWindowID);
		
		waitForExtJSAjaxComplete(10);
			
		softAssert.assertTrue(isPropertiesTabAvailable(changeColumnsWindowID), "Properties Tab is available in Change Visible Column Window");
			
		softAssert.assertTrue(isColumnsTabAvailable(changeColumnsWindowID), "Columns Tab is available in Change Visible Column Window");
			
		waitForExtJSAjaxComplete(10);
		
		isAuditingFieldChecked(changeColumnsWindowID);
			
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(isAuditingColumnsChecked(changeColumnsWindowID, "col-1", "Created By"), "Created By Column is Checked");
	
		softAssert.assertTrue(isAuditingColumnsChecked(changeColumnsWindowID, "col-1", "Created On"), "Created On Column is Checked");
			
		softAssert.assertTrue(isAuditingColumnsChecked(changeColumnsWindowID, "col-1", "Last Modified By"), "Last Modified By Column is Checked");
			
		softAssert.assertTrue(isAuditingColumnsChecked(changeColumnsWindowID, "col-1", "Last Modified On"), "Last Modified On Column is Checked");
			
		waitForExtJSAjaxComplete(10);
			
		isGeneralFieldChecked(changeColumnsWindowID);
			
		waitForExtJSAjaxComplete(10);
			
		softAssert.assertTrue(isGeneralColumnsChecked(changeColumnsWindowID, "col-1", "Access Instructions"), "Created By Column is Checked");
			
		softAssert.assertTrue(isGeneralColumnsChecked(changeColumnsWindowID, "col-1", "Class"), "Class Column is Checked");
			
		softAssert.assertTrue(isGeneralColumnsChecked(changeColumnsWindowID, "col-1", "Code"), "Code Column is Checked");
			
		softAssert.assertTrue(isGeneralColumnsChecked(changeColumnsWindowID, "col-1", "Commissioning Date"), "Commissioning Date Column is Checked");
			
		softAssert.assertTrue(isGeneralColumnsChecked(changeColumnsWindowID, "col-1", "Description"), "Description Column is Checked");
			
		softAssert.assertTrue(isGeneralColumnsChecked(changeColumnsWindowID, "col-1", "ID"), "ID Column is Checked");
			
		softAssert.assertTrue(isGeneralColumnsChecked(changeColumnsWindowID, "col-1", "Manufacturer"), "Manufacturer Column is Checked");
			
		softAssert.assertTrue(isGeneralColumnsChecked(changeColumnsWindowID, "col-1", "Model"), "Model Column is Checked");
			
		softAssert.assertTrue(isGeneralColumnsChecked(changeColumnsWindowID, "col-1", "Notes"), "Notes Column is Checked");
			
		softAssert.assertTrue(isGeneralColumnsChecked(changeColumnsWindowID, "col-1", "Physical Location"), "Physical Location Column is Checked");
			
		softAssert.assertTrue(isGeneralColumnsChecked(changeColumnsWindowID, "col-1", "Reference"), "Reference Column is Checked");
			
		softAssert.assertTrue(isGeneralColumnsChecked(changeColumnsWindowID, "col-1", "Scope"), "Scope Column is Checked");
			
		softAssert.assertTrue(isGeneralColumnsChecked(changeColumnsWindowID, "col-1", "Serial Number"), "Serial Number Column is Checked");
			
		softAssert.assertTrue(isGeneralColumnsChecked(changeColumnsWindowID, "col-1", "Status"), "Status Column is Checked");
			
		softAssert.assertTrue(isGeneralColumnsChecked(changeColumnsWindowID, "col-1", "Time Zone"), "Time Zone Column is Checked");
			
		waitForExtJSAjaxComplete(10);
			
		clickButton("Save", CHANGE_COLUMNS_CLASS_NAME);
			
		waitForExtJSAjaxComplete(25);
		
		filterGridWithoutUsingMcsElement("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", accessInstructions, "Access Instructions");
		
		waitForExtJSAjaxComplete(20);
		
		String createdByPostTxn = "";
		String createdOnPostTxn = "";
		String lastModifiedByPostTxn = "";
		String lastModifiedDatePostTxn = "";
		
		createdByPostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Created By");
			
		createdOnPostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Created On");
			
		lastModifiedByPostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Last Modified By");
		
		lastModifiedDatePostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Last Modified On");
			
		String accessInstruction = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Access Instructions");
			
		String classPostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Class");
			
		String codePostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Code");
			
		String commissioningDatePostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Commissioning Date");
			
		String descriptionTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Description");
			
		String IDPostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "ID");
			
		String manufacturerPostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Manufacturer");
		
		String modelPostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Model");
			
		String notesPostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Notes");
			
		String physicalLocationPostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Physical Location");
			
		String referencePostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Reference");
			
		String scopePostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Scope");
			
		String serialNoPostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Serial Number");
			
		String statusPostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Status");
			
		String timeZonePostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Time Zone");
			
		/**********Verification ***************/
			
		softAssert.assertEquals(getFutureDate(0), formatDates(createdOnPostTxn),  "Created On field value after creation is correct");
			
		softAssert.assertEquals(createdByPostTxn, "SELENIUM AQA", "Created By field value after creation is correct");
			
		softAssert.assertEquals(getFutureDate(0), formatDates(lastModifiedDatePostTxn), "Last Modified Date field value after creation is correct");
			
		softAssert.assertEquals(lastModifiedByPostTxn, "SELENIUM AQA",  "Last Modified By field value after creation is correct");
			
		softAssert.assertEquals(idValue, IDPostTxn, "ID field value after creation is correct");
			
		softAssert.assertEquals(code, codePostTxn, "Code field value after creation is correct");
			
		softAssert.assertEquals(reference, referencePostTxn, "Reference field value after creation is correct");
			
		softAssert.assertEquals(commisioningDate, commissioningDatePostTxn, "Commisioning Date field value after creation is correct");
		
		softAssert.assertEquals(accessInstructions, accessInstruction, "Access Instruction field value after creation is correct");
		
		softAssert.assertEquals(manufacturer, manufacturerPostTxn, "Manufacturer field value after creation is correct");
			
		softAssert.assertEquals(description, descriptionTxn, "Decription field value after creation is correct");
			
		softAssert.assertEquals(classRef, classPostTxn, "Class field value after creation is correct");
			
		softAssert.assertEquals(scope5, scopePostTxn, "Scope field value after creation is correct");
			
		softAssert.assertEquals(model, modelPostTxn, "Model field value after creation is correct");
			
		softAssert.assertEquals(notes, notesPostTxn, "Notes field value after creation is correct");
			
		softAssert.assertEquals(scope5, physicalLocationPostTxn, "Location field value after creation is correct");
			
		softAssert.assertEquals(serialNo, serialNoPostTxn, "Serial No field value after creation is correct");
			
		softAssert.assertEquals(statusActive, statusPostTxn, "Status field value after creation is correct");
			
		softAssert.assertEquals(timeZone, timeZonePostTxn, "Time Zone field value after creation is correct");
			
		waitForExtJSAjaxComplete(10);
			
		Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, code);
			
		waitForExtJSAjaxComplete(10);
			
		navigatorPageObject.clickDeleteBtn();
			
		waitForExtJSAjaxComplete(10);
			
		softAssert.assertAll();
			
		Reporter.log("Visibility of all added information to gauge in Overview page is successfully verified", true);
		
	}
	
	/**
	 * Test Case ID: C60436
	 * Author : SSU
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testIDField() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: The ID field : C60436" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testIDField");
		
		NavigatorPageObject navigatorPageObject = new NavigatorPageObject();
		
		waitForExtJSAjaxComplete(30);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		waitForExtJSAjaxComplete(20);
		
		openMeteringEntity("Gauges");

		waitForExtJSAjaxComplete(20);
		
		String code = "C60436cod"+getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "C60436ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String statusActive = "Active";
		String scope= "slnmEnrgBuilding5";
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton(GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(getItemID("value").isEmpty(), "ID field value is empty");
			
		softAssert.assertTrue(getItemID("class").contains("readonly"), "ID field is Read only");
			
		waitForExtJSAjaxComplete(10);
		
		setCode(code);
		
		setReference(reference);
			
		setStatus(statusActive);
			
		waitForExtJSAjaxComplete(10);
		
		setScope(scope);
		
		waitForExtJSAjaxComplete(10);
		
		save();
			
		waitForExtJSAjaxComplete(20);
		
		changeTab("properties");
		
		waitForExtJSAjaxComplete(10);
			
		String IDValue = getItemID("value");
			
		Reporter.log("ID Value is "+IDValue, true);
			
		close();
			
		waitForExtJSAjaxComplete(10);
			
		Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, IDValue);
			
		Reporter.log("ID is displayed properly in Overview", true);
			
		waitForExtJSAjaxComplete(10);	
			
		navigatorPageObject.clickEditBtn();
			
		waitForExtJSAjaxComplete(10);	
			
		softAssert.assertTrue(getItemID("value").contains(IDValue), "ID field value is filled in Properly");
			
		softAssert.assertTrue(getItemID("class").contains("readonly"), "ID field is Read only");
			
		close();
			
		waitForExtJSAjaxComplete(10);
			
		Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, IDValue);
			
		navigatorPageObject.clickDeleteBtn();
			
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
			
		Reporter.log("The ID field in Gauges is successfully verified", true);
	}

	/**
	 * Test Case ID: C60441
	 * Author : SSU
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testAuditField() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Check audit fields on Edit Gauge form : C60441" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testIDField");
		
		NavigatorPageObject navigatorPageObject = new NavigatorPageObject();
		
		waitForExtJSAjaxComplete(30);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		waitForExtJSAjaxComplete(20);
		
		openMeteringEntity("Gauges");

		waitForExtJSAjaxComplete(20);
		
		String code = "C60441cod"+getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "C60441ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String statusActive = "Active";
		String scope = "slnmEnrgBuilding5";
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton(GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		setCode(code);
		
		setReference(reference);
			
		setStatus(statusActive);
			
		waitForExtJSAjaxComplete(10);
		
		setScope(scope);
		
		waitForExtJSAjaxComplete(10);
		
		save();
			
		waitForExtJSAjaxComplete(20);
		
		close();
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, reference);
		
		clickEditButton(GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		changeTab("properties");
		
		waitForExtJSAjaxComplete(10);
		
		String createdtext = subStringCreatedOnFromAudit();
		
		Reporter.log(createdtext+ " Created On Audit Text", true);
		
		String userName = getLoggedUserName();
		
		softAssert.assertTrue(createdtext.contains(userName));
		
		String createdOnTimeStamp = subStringTimeStampFromAuditFields(createdtext);
		
		Reporter.log(createdOnTimeStamp+ "Time Stamp is available in Created On Audit Text", true);
		
		waitForExtJSAjaxComplete(10);
		
		String modifiedText = subStringModifiedOnFromAudit();
		
		Reporter.log("Modified On Audit Text", true);
		
		softAssert.assertTrue(modifiedText.contains(userName));
		
		String modifiedOnTimeStamp = subStringTimeStampFromAuditFields(modifiedText);
		
		Reporter.log(modifiedOnTimeStamp+ "Time Stamp is available in Modified Audit Text", true);
		
		setSerialNumber("123");
		
		waitForExtJSAjaxComplete(10);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		close();
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, reference);
		
		clickEditButton(GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		changeTab("properties");
		
		waitForExtJSAjaxComplete(10);
		
		modifiedText = subStringModifiedOnFromAudit();
		
		Reporter.log("Modified On Updated Audit Text", true);
		
		softAssert.assertTrue(modifiedText.contains(userName));
		
		String modifiedOnTimeStampUpdated = subStringTimeStampFromAuditFields(modifiedText);
		
		Reporter.log(modifiedOnTimeStamp+ "Updated Time Stamp available in Modified Audit Text", true);
		
		softAssert.assertNotEquals(modifiedOnTimeStamp, modifiedOnTimeStampUpdated, "Date and Time For Last Modified Action is updated to Last Changes");
		
		close();
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, reference);
		
		navigatorPageObject.clickDeleteBtn();
			
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("Check audit fields on Edit Gauge form is successfully verified", true);
	}
}
