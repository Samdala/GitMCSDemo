package test.energy.meters;

import java.io.IOException;
import java.lang.ref.Reference;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.energy.navigator.MeterInfrastructurePageObject;
import test.energy.navigator.NavigatorPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class MetersCRUDTestSuite extends
		MetersPageObject {


	 @Test(enabled=true)
	public void testMeterCreateEdit() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create and Edit Meters: C19682" + " </span><br>",
				true);

		Reporter.log("User creates and edits Meters"  + " <br>",
				true);
		
		//Field values for Meter creation
		
		String reference = "test auto mtr " + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto mtr " + getCurrentDate().substring(getCurrentDate().length()-6);
		String status = "Active";
		String timeZone = "(GMT+02:00) Athens, Bucharest, Istanbul";
		String commodity = "Electricity";
		String meterOperator = "My Company";
		String date = "03-09-2013";
		String model = "Model";
		String serialNumber = "13";
		String physicalLocation = "slnmEnrgBuilding1";
		String manufacturer = "Manufacturer";
		String description = "Some text in Description";
		String accessInstr = "Some text in Access Instructions";
		
		//Auto-created Meter Channel expected field values
		
		String chnlCalcMethod = "Spreading";
		String chnlMultiplier = "1";
		String chnlUOM = "kWh";
		String chnlSmallRepInt = "Day";
		String chnlEntryType = "Usage Value";
		String chnlEntryMethod = "Manual";
		String chnlNumDigits = "";
		String chnlNumDecimals = "";

		//Field values for Meter editing
		
		String referenceEdited = "test auto mtr " + getCurrentDate().substring(getCurrentDate().length()-6);
		String codeEdited = "test auto mtr " + getCurrentDate().substring(getCurrentDate().length()-6);
		String statusEdited = "Archived";
		String timeZoneEdited = "(GMT+01:00) Windhoek";
		//String meterOperatorEdited = "My Company";
		String dateEdited = "01-01-2008";
		String modelEdited = "Another Model";
		String serialNumberEdited = "27842";
		String physicalLocationEdited = "slnmEnrgBuilding2";
		String manufacturerEdited = "Another Manufacturer";
		String descriptionEdited = "Edited some text in Description";
		String accessInstrEdited = "Edited some text in Access Instructions";
		
		//Navigator
		
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite2";
		String supplyPointName = "EAN-1preSP";

		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("MeterCreateEdit");

		//Navigate to Meters Overview
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
	
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickMetersInfrastructureTab();
		
		waitForExtJSAjaxComplete(20);
		
		setEffectiveDate("01-01-2014");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//Add Meter and check if default channel is created with correct field values
		
		clickOnSupplyPoint(supplyPointName);
				
		waitForExtJSAjaxComplete(20);
		
		clickCreateMeter();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		setReference(reference);
		
		setCode(code);
		
		setStatus(status);
		
		waitForExtJSAjaxComplete(20);
		
		setTimeZone(timeZone);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodity(commodity);
		
		waitForExtJSAjaxComplete(20);
		
		setMeterOperator(meterOperator);

		waitForExtJSAjaxComplete(20);
		
		setCommissioningDate(date);
		
		setModel(model);
		
		setSerialNumber(serialNumber);
		
			setPhysicalLocation(site);
		
		setManufacturer(manufacturer);
		
		setDescription(description);
		
		setAccessInstructions(accessInstr);
		
		save(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(20);
		
		clickChannelsTab();
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//Select Channel by it's reference
		checkRowInGriByTextValueAndClick(driver, DIALOG_METER, reference);
		
		waitForExtJSAjaxComplete(20);
		
		clickButton("Edit", DIALOG_METER, "channels");
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(20);
		
		//Check Channel fields
		softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "calculationMethod"), chnlCalcMethod, "Calculation Method field value of autocreated channel is correct?");
		
		softAssert.assertEquals(getLookupValue(DIALOG_MTR_CHANNEL, "unitOfMeasure"), chnlUOM, "UOM field value of autocreated channel is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "readingInterval"), chnlSmallRepInt, "Smallest Reporting Interval field value of autocreated channel is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "entryType"), chnlEntryType, "Entry Type field value of autocreated channel is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "entryMethod"), chnlEntryMethod, "Entry Method field value of autocreated channel is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "multiplier"), chnlMultiplier, "Multiplier field value of autocreated channel is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "numberOfDials"), chnlNumDigits, "Number of Digits field value of autocreated channel is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_MTR_CHANNEL, "numberOfDecimals"), chnlNumDecimals, "Number of Decimals field value of autocreated channel is correct?");
		
		close(DIALOG_MTR_CHANNEL);
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		Thread.sleep(2000);
		
		close(DIALOG_METER);
		
		Thread.sleep(1000);
		
		waitForExtJSAjaxComplete(20);
		
		//Edit Meter and check if it's fields were saved correctly on creation
		
		expandMetering();
		
		waitForExtJSAjaxComplete(20);
		
		openMeteringEntity("Meters");

		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		//Select Meter on Overview by it's reference
		checkRowInGriByTextValueAndClick(driver, METER_GRID_CLASS, reference);
		
		waitForExtJSAjaxComplete(20);
		
		clickButton("Edit", METER_GRID_CLASS);
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		//Check Meter fields after creation
		softAssert.assertEquals(getFieldValue(DIALOG_METER, "reference"), reference, "Reference field value after creation is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_METER, "code"), code, "Code field value after creation is correct?");
		
		softAssert.assertEquals(getLookupValue(DIALOG_METER, "status"), status, "Status field value after creation is correct?");
		
		softAssert.assertEquals(getLookupValue(DIALOG_METER, "timezone"), timeZone, "Time Zone field value after creation is correct?");
		
		softAssert.assertEquals(getLookupValue(DIALOG_METER, "commodity"), commodity, "Commodity field value after creation is correct?");
		
		softAssert.assertEquals(getLookupValue(DIALOG_METER, "meterOperator"), meterOperator, "Meter Operator field value after creation is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_METER, "commissioningDate"), date, "Commisioning Date field value after creation is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_METER, "model"), model, "Model field value after creation is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_METER, "serialNumber"), serialNumber, "Serial Number field value after creation is correct?");
		
		softAssert.assertEquals(getLookupValue(DIALOG_METER, "location"), site, "Physical Location field value after creation is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_METER, "manufacturer"), manufacturer, "Manufacturer field value after creation is correct?");
		
		softAssert.assertEquals(getTextAreaValue(DIALOG_METER, "description"), description, "Description field value after creation is correct?");
		
		softAssert.assertEquals(getTextAreaValue(DIALOG_METER, "accessDirectives"), accessInstr, "Access Instructions field value after creation is correct?");
		
		//Edit Meter fields values
		
		setReference(referenceEdited);
		
		setCode(codeEdited);
		
		setStatus(statusEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setTimeZone(timeZoneEdited);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setCommissioningDate(dateEdited);
		
		setModel(modelEdited);
		
		setSerialNumber(serialNumberEdited);
		
			setPhysicalLocation(site);

			setManufacturer(manufacturerEdited);
		
		setDescription(descriptionEdited);
		
		setAccessInstructions(accessInstrEdited);
		
		saveClose(DIALOG_METER);
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		checkRowInGriByTextValueAndClick(driver, METER_GRID_CLASS, referenceEdited);
		
		waitForExtJSAjaxComplete(20);
		
		clickButton("Edit", METER_GRID_CLASS);
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		//Check if edited Meter fields were saved correctly
		softAssert.assertEquals(getFieldValue(DIALOG_METER, "reference"), reference, "Reference field value after editing is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_METER, "code"), codeEdited, "Code field value after editing is correct?");
		
		softAssert.assertEquals(getLookupValue(DIALOG_METER, "status"), statusEdited, "Status field value after editing is correct?");
		
		softAssert.assertEquals(getLookupValue(DIALOG_METER, "timezone"), timeZoneEdited, "Time Zone field value after editing is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_METER, "commissioningDate"), dateEdited, "Commisioning Date field value after editing is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_METER, "model"), modelEdited, "Model field value after editing is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_METER, "serialNumber"), serialNumberEdited, "Serial Number field value after editing is correct?");
		
			softAssert.assertEquals(getLookupValue(DIALOG_METER, "location"), site, "Physical Location field value after editing is correct?");
		
		softAssert.assertEquals(getFieldValue(DIALOG_METER, "manufacturer"), manufacturerEdited, "Manufacturer field value after editing is correct?");
		
		softAssert.assertEquals(getTextAreaValue(DIALOG_METER, "description"), descriptionEdited, "Description field value after editing is correct?");
		
		softAssert.assertEquals(getTextAreaValue(DIALOG_METER, "accessDirectives"), accessInstrEdited, "Access Instructions field value after editing is correct?");
		
		softAssert.assertAll();
		
		Reporter.log("Meter was succesfully created and edited", true);
	 }
	 
	 //Temporarily Meters can't be deleted!
	 @Test(enabled = false)
		public void testMeterDelete() throws IllegalStateException, IOException {

			
			Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Delete Meter: C19685" + " </span><br>",
					true);

			Reporter.log("User deletes Meter"  + " <br>",
					true);
			
			String reference = "test auto mtr " +getCurrentDate().substring(getCurrentDate().length()-6);
			String code = "test auto mtr " + getCurrentDate().substring(getCurrentDate().length()-6);
			String commodity = "Electricity";
			
			//Navigator
			
			String area = "slnmEnrgArea1 (slnmEnrgArea)";
			String site = "slnmEnrgSite2";
			String supplyPointName = "EAN-1preSP";
			
			SoftAssert softAssert = new SoftAssert(); 
			
			softAssert.setMethodName("MeterDelete");
		
			//Navigate to Meters Overview
			
			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(20);
			
			expandNavigator();
			
			waitForExtJSAjaxComplete(20);
			
			test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
			
			waitForExtJSAjaxComplete(20);
			
			test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
			waitForMaskDisappear();
			
			waitForExtJSAjaxComplete(20);
			
			clickMetersInfrastructureTab();
			
			waitForExtJSAjaxComplete(20);
			
			setEffectiveDate("01-01-2014");
			
			waitForExtJSAjaxComplete(25);
			
			setCommodityClass("Electricity");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			clickOnSupplyPoint(supplyPointName);
					
			waitForExtJSAjaxComplete(20);
			
			//Create Meter with only mandatory fields and try to delete it
			
			clickCreateMeter();
			
			waitForExtJSAjaxComplete(20);
			
			setCode(code);
			
			setReference(reference);
			
			setCommodity(commodity);
			
			waitForExtJSAjaxComplete(20);
			
			save(DIALOG_METER);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			close(DIALOG_METER);
			
			waitForExtJSAjaxComplete(20);
			
			expandMetering();
			
			waitForExtJSAjaxComplete(20);
			
			openMeteringEntity("Meters");

			waitForExtJSAjaxComplete(20);
			
			checkRowInGriByTextValueAndClick(driver, METER_GRID_CLASS, reference);
			
			clickDeleteButton(METER_GRID_CLASS);
					
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(Grid.isRowInGridAbsent(driver, reference), "Was Meter deleted?");
			
			softAssert.assertAll();
			
			Reporter.log("Meter was succesfully deleted", true);
			
		}
	 
	 /**
	 * Testcase ID	 : C60531
	 * Author		 : ssu
	 * */
	@Test(enabled=true)
	public void testEditMeterSystemView() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60531: Metering: Meters: User is able to edit meter from System View </span><br>", true);
		
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite2";
		String meterName = "1preMeter";
		String propertiesTab = "Properties";
		String notesTab = "Notes";
		String notesValue = "Testing Notes Textarea";
		
		String serialNo = "123";
		String commissioningDate = getFutureDate(1);
		String manufacturer = "Test Manufacturer";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testEditMeterSystemView");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickMetersInfrastructureTab();
		
		waitForExtJSAjaxComplete(5);
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		setEffectiveDate("10-02-2014");
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		MeterInfrastructurePageObject meterInfrastructurePO = new MeterInfrastructurePageObject();
		
		waitForExtJSAjaxComplete(25);
		
		meterInfrastructurePO.clickOnMeters(meterName);
		
		waitForExtJSAjaxComplete(10);
		
		clickEdidMeter();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(EDIT_METER_WINDOW_HEADER, propertiesTab);
		
		waitForExtJSAjaxComplete(20);
		
		verifyCommodity("Select a Commodity");
		
		waitForExtJSAjaxComplete(10);
		
		clickCANCELXwindow();
		
		waitForExtJSAjaxComplete(5);
		
		setCommissioningDate(commissioningDate);
		
		setSerialNumber(serialNo);
		
		setManufacturer(manufacturer);
		
		save(DIALOG_METER);
		
		waitForExtJSAjaxComplete(10);
		
		clickOnTab(EDIT_METER_WINDOW_HEADER, notesTab);
		
		waitForExtJSAjaxComplete(10);
		
		setNotes(notesValue);
		
		save(DIALOG_METER);
		
		waitForExtJSAjaxComplete(10);
		
		close(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(10);
		
		clickOnMeters(meterName);
		
		waitForExtJSAjaxComplete(10);
		
		clickEdidMeter();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnTab(EDIT_METER_WINDOW_HEADER, propertiesTab);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getFieldValue(DIALOG_METER, "commissioningDate"), commissioningDate, "Commissioning Date is correctly Displayed");
		
		softAssert.assertEquals(getFieldValue(DIALOG_METER, "serialNumber"), serialNo, "Serial Number is correctly Displayed");
		
		softAssert.assertEquals(getFieldValue(DIALOG_METER, "manufacturer"), manufacturer, "Manufacturer is correctly Displayed");
		
		clickOnTab(EDIT_METER_WINDOW_HEADER, notesTab);
		
		Thread.sleep(2000);
		
		softAssert.assertEquals(getTextAreaValue(DIALOG_METER, "notes"), notesValue, "Notes Value is correctly Displayed");
		
		softAssert.assertAll();
		
		Reporter.log("User is able to edit meter from System View is successfully verified", true);
	}
	
	/**
	 * Testcase ID	 : C60533 & C60534
	 * Author		 : ssu
	 * */
	@Test(enabled=true)
	public void testIDAndVisibilityOfField() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60533: Visibility of all added information to Meter in Overview page </span><br>", true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60534: The ID field </span><br>", true);
		
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite1";
		String commodityClass = "Electricity";
		String propertiesTab = "Properties";
		String supplyPointName = "codePrefixNonUnique";
		
		String code = "cod"+getCurrentDate().substring(getCurrentDate().length()-5);
		String meterReference = "ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String activeStatus = "Active";
		String commisioningDate = getFutureDate(0);
		String classRef = "Commodity Meter";
		String serialNo = "1";
		String manufacturer = "manufacturer";
		String description = "C60533Desc"+getCurrentDate().substring(getCurrentDate().length()-5);
		String model = "Model";
		String accessInstructions = "C60533AccessIns"+getCurrentDate().substring(getCurrentDate().length()-5);
		String timeZone = "(GMT+01:00) Brussels, Copenhagen, Madrid, Paris";
		String scope5 = "slnmEnrgBuilding5";
		String notes = "Test Notes Field";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testIDField");
		
		waitForExtJSAjaxComplete(30);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickMetersInfrastructureTab();
		
		waitForExtJSAjaxComplete(25);
		
		setEffectiveDate(getFutureDate(-15));
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		setCommodityClass(commodityClass);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickOnSupplyPoint(supplyPointName);
		
		waitForExtJSAjaxComplete(10);
		
		clickCreateMeter();
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(ADD_METER_WINDOW_HEADER, propertiesTab);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(getItemID("value").isEmpty(), "ID field value is empty");
		
		softAssert.assertTrue(getItemID("class").contains("readonly"), "ID field is Read only");
		
		setCode(code);
		
		setReference(meterReference);
		
		setCommodity(commodityClass);
		
		waitForExtJSAjaxComplete(10);
		
		setStatus(activeStatus);
		
		waitForExtJSAjaxComplete(10);
		
		setTimeZone(timeZone);
		
		waitForExtJSAjaxComplete(10);
		
		setCommissioningDate(commisioningDate);
			
		setSerialNumber(serialNo);
			
		setManufacturer(manufacturer);
			
		setDescription(description);
			
		setModel(model);
			
		setAccessInstructions(accessInstructions);
		
		NavigatorPageObject navigatorPageObject = new NavigatorPageObject();
			
		setNavigatorLocation(scope5);
			
		waitForExtJSAjaxComplete(10);
			
		changeTab("notes");
			
		waitForExtJSAjaxComplete(20);
			
		setNotes(notes);
			
		waitForExtJSAjaxComplete(10);
		
		save(DIALOG_METER);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		changeTab("properties");
		
		waitForExtJSAjaxComplete(10);
			
		String IDValue = getItemID("value");
			
		Reporter.log("ID Value is "+IDValue, true);
			
		close(DIALOG_METER);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		openMeteringEntity("Meters");

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		navigatorPageObject.clickChangeVisibleColumns();
		
		waitForExtJSAjaxComplete(10);
		
		changeVisibleColumns(CHANGE_VISIBLE_COLUMNS_HEADER, "gp-group-General-hd", "gp-group-General-bd", "gp-group-General", "Description");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Save", "x-window x-resizable-pinned");
		
		waitForExtJSAjaxComplete(25);	
		
		waitForExtJSAjaxComplete(25);	
		
		waitForExtJSAjaxComplete(25);	

		filterGridScrollIntoView("@class", METER_GRID_CLASS, description, "Description");
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log(IDValue+" is Present in Meters Grid", true);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", METER_GRID_CLASS, description);
		
		clickButton("Edit", METER_GRID_CLASS);
		
		Thread.sleep(2000);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(getItemID("value").contains(IDValue), "ID field value is filled in Properly");
		
		softAssert.assertTrue(getItemID("class").contains("readonly"), "ID field is Read only");
		
		close(DIALOG_METER);
		
		waitForExtJSAjaxComplete(10);
		
		navigatorPageObject.clickChangeVisibleColumns();
		
		waitForExtJSAjaxComplete(10);
			
		softAssert.assertTrue(navigatorPageObject.isPropertiesTabAvailable(CHANGE_VISIBLE_COLUMNS_HEADER), "Properties Tab is available in Change Visible Column Window");
			
		softAssert.assertTrue(navigatorPageObject.isColumnsTabAvailable(CHANGE_VISIBLE_COLUMNS_HEADER), "Columns Tab is available in Change Visible Column Window");
			
		waitForExtJSAjaxComplete(10);
		
		navigatorPageObject.isAuditingFieldChecked(CHANGE_VISIBLE_COLUMNS_HEADER);
			
		waitForExtJSAjaxComplete(10);
			
		softAssert.assertTrue(navigatorPageObject.isAuditingColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Created by"), "Created By Column is Checked");
			
		softAssert.assertTrue(navigatorPageObject.isAuditingColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Created on"), "Created On Column is Checked");
			
		softAssert.assertTrue(navigatorPageObject.isAuditingColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Last Modified by"), "Last Modified By Column is Checked");
			
		softAssert.assertTrue(navigatorPageObject.isAuditingColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Last Modified on"), "Last Modified On Column is Checked");
					
		waitForExtJSAjaxComplete(10);
			
		navigatorPageObject.isGeneralFieldChecked(CHANGE_VISIBLE_COLUMNS_HEADER);
			
		waitForExtJSAjaxComplete(10);
			
		softAssert.assertTrue(navigatorPageObject.isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Access Instructions"), "Created By Column is Checked");
			
		softAssert.assertTrue(navigatorPageObject.isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Class"), "Class Column is Checked");
			
		softAssert.assertTrue(navigatorPageObject.isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Code"), "Code Column is Checked");
			
		softAssert.assertTrue(navigatorPageObject.isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Commissioning Date"), "Commissioning Date Column is Checked");
			
		softAssert.assertTrue(navigatorPageObject.isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Description"), "Description Column is Checked");
			
		softAssert.assertTrue(navigatorPageObject.isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "ID"), "ID Column is Checked");
			
		softAssert.assertTrue(navigatorPageObject.isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Manufacturer"), "Manufacturer Column is Checked");
			
		softAssert.assertTrue(navigatorPageObject.isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Model"), "Model Column is Checked");
			
		softAssert.assertTrue(navigatorPageObject.isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Notes"), "Notes Column is Checked");
			
		softAssert.assertTrue(navigatorPageObject.isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Reference"), "Reference Column is Checked");
			
		softAssert.assertTrue(navigatorPageObject.isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Serial Number"), "Serial Number Column is Checked");
			
		softAssert.assertTrue(navigatorPageObject.isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Status"), "Status Column is Checked");
			
		softAssert.assertTrue(navigatorPageObject.isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Time Zone"), "Time Zone Column is Checked");
			
		waitForExtJSAjaxComplete(10);
		
		navigatorPageObject.isPhysicalLocationFieldChecked(CHANGE_VISIBLE_COLUMNS_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(navigatorPageObject.isPhysicalLocationColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Physical Location"), "Physical Location Column is Checked");
		
		waitForExtJSAjaxComplete(10);

		navigatorPageObject.clickButton("Save", getXWindowId(CHANGE_VISIBLE_COLUMNS_HEADER));
			
		waitForExtJSAjaxComplete(25);
		
		clearOverviewFilters();
		
		waitForExtJSAjaxComplete(25);
		
		//TODO Changed filter filterGridWithoutUsingMcsElement
		filterGridScrollIntoView("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", accessInstructions, "Access Instructions");
		
		waitForExtJSAjaxComplete(20);
		
		String createdByPostTxn = "";
		String createdOnPostTxn = "";
		String lastModifiedByPostTxn = "";
		String lastModifiedDatePostTxn = "";
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", METER_GRID_CLASS, accessInstructions);
		
		createdByPostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Created by");
			
		createdOnPostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Created on");
		
		lastModifiedByPostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Last Modified by");
			
		lastModifiedDatePostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Last Modified on");
					
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
			
		String serialNoPostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Serial Number");
			
		String statusPostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Status");
			
		String timeZonePostTxn = navigatorPageObject.getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Time Zone");
			
		/**********Verification ***************/
			
		softAssert.assertEquals(getFutureDate(0), formatDates(createdOnPostTxn),  "Created On field value after creation is correct");
			
		softAssert.assertEquals(createdByPostTxn, "SELENIUM AQA", "Created By field value after creation is correct");
			
		softAssert.assertEquals(getFutureDate(0), formatDates(lastModifiedDatePostTxn), "Last Modified Date field value after creation is correct");
			
		softAssert.assertEquals(lastModifiedByPostTxn, "SELENIUM AQA",  "Last Modified By field value after creation is correct");
			
		softAssert.assertEquals(IDValue, IDPostTxn, "ID field value after creation is correct");
			
		softAssert.assertEquals(code, codePostTxn, "Code field value after creation is correct");
			
		softAssert.assertEquals(meterReference, referencePostTxn, "Reference field value after creation is correct");
			
		softAssert.assertEquals(commisioningDate, commissioningDatePostTxn, "Commisioning Date field value after creation is correct");
		
		softAssert.assertEquals(accessInstructions, accessInstruction, "Access Instruction field value after creation is correct");
		
		softAssert.assertEquals(manufacturer, manufacturerPostTxn, "Manufacturer field value after creation is correct");
			
		softAssert.assertEquals(description, descriptionTxn, "Decription field value after creation is correct");
			
		softAssert.assertEquals(classRef, classPostTxn, "Class field value after creation is correct");
			
		softAssert.assertEquals(model, modelPostTxn, "Model field value after creation is correct");
			
		softAssert.assertEquals(notes, notesPostTxn, "Notes field value after creation is correct");
			
		softAssert.assertEquals(scope5, physicalLocationPostTxn, "Location field value after creation is correct");
			
		softAssert.assertEquals(serialNo, serialNoPostTxn, "Serial No field value after creation is correct");
			
		softAssert.assertEquals(activeStatus, statusPostTxn, "Status field value after creation is correct");
			
		softAssert.assertEquals(timeZone, timeZonePostTxn, "Time Zone field value after creation is correct");
			
		waitForExtJSAjaxComplete(10);
			
		softAssert.assertAll();
			
		Reporter.log("Visibility of all added information to gauge in Overview page is successfully verified", true);
		
		Reporter.log("The ID field is successfully verified" ,true);
	} 
}