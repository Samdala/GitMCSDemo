package test.energy.meters;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class MetersScopeValidationTestSuite extends MetersPageObject{
	
	 @Test(enabled=true)
		public void testMeterScopeValidation() throws Exception  {

			Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Scope Validation: C39612, C39613, C39609" + " </span><br>",
					true);

			Reporter.log("User creates Validation form Meter Scope"  + " <br>",
					true);
			
			
			SoftAssert softAssert = new SoftAssert(); 
			
			softAssert.setMethodName("ScopeCreateEditRemove");
			
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
			String effectiveDate = getFutureDate(0);
			
			//Navigator
			
			String area = "slnmEnrgArea1 (slnmEnrgArea)";
			String site = "slnmEnrgSite3";
			String supplyPointName = "EAN-2preSP";

			//String meterReference = reference;//"1preMeter";
			
			String energyObjectReference = "slnmEnrgBuilding10";
			
			String energyObjectReferenceEdited;
				energyObjectReferenceEdited = "slnmEnrgScopeEdit";
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
			
			setEffectiveDate("01-03-2014");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			setCommodityClass("Electricity");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			//Add Meter and check if default channel is created with correct field values
			
			clickOnSupplyPointJS(supplyPointName);
					
			waitForExtJSAjaxComplete(20);
			
			clickCreateMeter();
			
			waitForExtJSAjaxComplete(20);
			
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
			
				//setPhysicalLocation(site);
			
			setManufacturer(manufacturer);
			
			setDescription(description);
			
			setAccessInstructions(accessInstr);
			
			save(DIALOG_METER);
			
			waitForExtJSAjaxComplete(20);

			waitForExtJSAjaxComplete(25);
			
			//Removed Few Lines of Code since the functionality changed
			
			Thread.sleep(10000);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			clickScopeTab();
			
			Thread.sleep(3000);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			//ScopeTabs functions
			
			clickAddOnScopeTab();
			
			Thread.sleep(3000);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertEquals(getEffectiveDateOnScopeTab(), "", "Date is empty");
			
			/////////////////not unique
			
			clickAddEnergyObjectOnObjectToScope();
			
			Thread.sleep(3000);
			
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(20);
			
			selectEnergyObject(energyObjectReference);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(verifyItemEnergyObjectExist(energyObjectReference), "Energy Object Exist 1");
			
			waitForExtJSAjaxComplete(20);
			
				save(DIALOG_PROPERTIES);
			
			waitForExtJSAjaxComplete(20);
			
			clickAddEnergyObjectOnObjectToScope();
			
			Thread.sleep(3000);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(20);
			
			selectEnergyObject(energyObjectReference);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(verifyItemEnergyObjectExist(energyObjectReference), "Energy Object Exist 1");
			
			waitForExtJSAjaxComplete(20);
			
				save(DIALOG_PROPERTIES);
				
				Thread.sleep(3000);
				
				waitForExtJSAjaxComplete(20);
				
				waitForExtJSAjaxComplete(25);
				softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_PROPERTIES),"Message about invalid form is present");
			
			waitForExtJSAjaxComplete(20);
			
			selectEnergyObjectOnAddObjectToScope(energyObjectReference);
			
			clickDeleteOnObjectToScope();
			
			waitForExtJSAjaxComplete(20);
			
			clickOnDialogButton("Yes");
			
				close(DIALOG_PROPERTIES);
			
			waitForExtJSAjaxComplete(20);
			
			clickOnDialogButton("Yes");
			
			///////////////////////////////
			
			waitForExtJSAjaxComplete(20);
			
			clickAddOnScopeTab();
			
			Thread.sleep(3000);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			setEffectiveDateOnScope(ADD_SCOPE_WINDOW_HEADER, effectiveDate);
			
			waitForExtJSAjaxComplete(20);
			
			clickAddEnergyObjectOnObjectToScope();
			
			Thread.sleep(3000);
			
			waitForExtJSAjaxComplete(20);
			
			selectEnergyObject(energyObjectReference);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(verifyItemEnergyObjectExist(energyObjectReference), "Energy Object Exist 1");
			
			waitForExtJSAjaxComplete(20);
			
				save(DIALOG_PROPERTIES);
				waitForExtJSAjaxComplete(20);
				
				waitForExtJSAjaxComplete(25);
				close(DIALOG_PROPERTIES);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertEquals(getEffectiveDateOnScopeTab(), effectiveDate, "Date is correct. Todays date.");
			
			
			softAssert.assertAll();
			
			Reporter.log("Meter Channel was successfully created and edited", true);
	 }
	 
	 /**
	  * Test Case Id : C60514 & C60516
	  * Author : SSU
	  */
	 @Test(retryAnalyzer=RetryAnalyzer.class)
	 public void testMeterScopeCommodityEndUseFieldsVisibility() throws Exception {
		 
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Meter Scope with Commodity End-use : C60514" + " </span><br>", 
					true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Combination of Scope and Commodity End-use : C60516" + " </span><br>", 
				true);
			
		SoftAssert softAssert = new SoftAssert();
			
		softAssert.setMethodName("testMeterScopeCommodityEndUseFieldsVisibility");
		
		Boolean build122 = driver.getCurrentUrl().contains("122");
		Boolean build14 = driver.getCurrentUrl().contains("14");
		
		//Navigate to Meters Overview
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		openMeteringEntity("Meters");
		
		waitForExtJSAjaxComplete(20);
		
		String energyObjectReference = "slnmMetricsMeter";
		String energyObject122 = "2preMeter";
		
		//Open pre-configured Meter and go to Channels tab
		if(!build122){
			Grid.checkRowInGriByTextValueAndClick(driver, "@class", METER_GRID_CLASS, energyObjectReference);
		} else {
			Grid.checkRowInGriByTextValueAndClick(driver, "@class", METER_GRID_CLASS, energyObject122);
		}
		
		clickButton("Edit", METER_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		clickScopeTab();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(McsElement.isElementDisplayed(driver, EFFECTIVE_DATE_SCOPETAB_XPATH), "Effective Date Element is present in Scope Tab and is visible");
		
		softAssert.assertTrue(McsElement.isElementDisplayed(driver, ADD_SCOPETAB_XPATH), "Add Button is present in Scope Tab and is visible");
		
		softAssert.assertTrue(McsElement.isElementDisplayed(driver, EDIT_SCOPETAB_XPATH), "Edit Button is present in Scope Tab and is visible");
		
		softAssert.assertTrue(McsElement.isElementDisplayed(driver, CLOSE_SCOPETAB_XPATH), "Close Button is present in Scope Tab and is visible");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, CLOSE_FOOTER_SCOPETAB_XPATH), "Close Button is present in Footer Area of Scope Tab and is visible");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, SAVE_CLOSE_SCOPETAB_XPATH), "Save and Close Button is present in Scope Tab and is visible");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, SAVE_SCOPETAB_XPATH), "Save Button is present in Scope Tab and is visible");
		
		waitForExtJSAjaxComplete(5);
		
		if(getEffectiveDateOnScopeTab().isEmpty()) {
			clickAddOnScopeTab();
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			setEffectiveDateOnScope(ADD_SCOPE_WINDOW_HEADER, getFutureDate(-1));
		} else {
			if(build14 || build122){
				clickEditOnScopeTab();
				
				waitForExtJSAjaxComplete(20);
				
				waitForExtJSAjaxComplete(20);
				
				setEffectiveDateOnScope(EDIT_SCOPE_WINDOW_HEADER, getFutureDate(-1));
			}
		}
		
		waitForExtJSAjaxComplete(20);
		
		//TODO Trunk doesnt have ADD /EDit Buttons Active
		if(build122 || build14) {
			
		softAssert.assertTrue(McsElement.isElementPresent(driver, EFFECTIVE_DATE_OBJECTS_SCOPE_XPATH), "Effective Date Element is present in Scope Tab and is visible");
		
		if(!build122){
			softAssert.assertTrue(McsElement.isElementPresent(driver, ADD_ENERGY_OBJECT_SCOPE_XPATH), "Add Energy Object is present in Scope Tab and is visible");
		
			softAssert.assertTrue(McsElement.isElementPresent(driver, ADD_MAINTENANCE_OBJECT_SCOPE_XPATH), "Add Maintenance Object is present in Scope Tab and is visible");
		}else{
			softAssert.assertTrue(McsElement.isElementPresent(driver, ADD_LOCATION_122_XPATH), "Add Location is present in Scope Tab and is visible");
		}
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, DELETE_ENERGY_OBJECT_SCOPE_XPATH), "Delete Button is present in Scope Tab and is visible");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, OBJECTS_IN_SCOPE_HEADER), "Objects In Scope Header is present in Footer Area of Scope Tab and is visible");
		
		if(build122){
			softAssert.assertTrue(McsElement.isElementPresent(driver, COMMODITY_ENDUSE_IN_SCOPE_HEADER_122), "Commodity EndUse is present in Scope Tab and is visible");
		} else{
			softAssert.assertTrue(McsElement.isElementPresent(driver, COMMODITY_ENDUSE_IN_SCOPE_HEADER), "Commodity EndUse is present in Scope Tab and is visible");
		}
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, DISTRIBUTION_RATIO_SCOPE_HEADER), "Distribution Ratio is present in Scope Tab and is visible");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, DISTRIBUTION_PERCENTAGE_SCOPE_HEADER), "Distribution Percentage is present in Scope Tab and is visible");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, NOTES_IN_SCOPE_HEADER), "Notes is present in Scope Tab and is visible");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, SAVE_OBJECT_SCOPE_XPATH), "Save Button is present in Scope Tab and is visible");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, SAVECLOSE_OBJECT_SCOPE_XPATH), "Save And Close Button is present in Scope Tab and is visible");

		softAssert.assertTrue(McsElement.isElementPresent(driver, CLOSE_OBJECT_SCOPE_XPATH), "Close Button is present in Scope Tab and is visible");
		
		waitForExtJSAjaxComplete(20);
		
		String energyObjectMetrics = "slnmMetricsEO1";
		String energyObjectBuilding5 = "slnmEnrgBuilding5";
		
		
		
		if(verifyItemEnergyObjectExist(energyObjectMetrics))  {
		
			Grid.checkRowInGriByTextValueAndClick(driver, DIALOG_PROPERTIES_NOT_TRUNK, energyObjectMetrics);
			
			clickButton("Delete", DIALOG_PROPERTIES_NOT_TRUNK);
			
			clickOnDialogButton("Yes");
			
			waitForExtJSAjaxComplete(10);
		
		} else if(verifyItemEnergyObjectExist(energyObjectBuilding5)) {
			
			Grid.checkRowInGriByTextValueAndClick(driver, DIALOG_PROPERTIES_NOT_TRUNK, energyObjectBuilding5);
			
			clickButton("Delete", DIALOG_PROPERTIES_NOT_TRUNK);
			
			clickOnDialogButton("Yes");
			
			waitForExtJSAjaxComplete(10);
		}
		
		clickAddEnergyObjectOnObjectToScope();
		
		waitForExtJSAjaxComplete(10);
		
		if (!build122) {
		
			selectEnergyObject(energyObjectMetrics);
			
			waitForExtJSAjaxComplete(10);
			
			clickAddEnergyObjectOnObjectToScope();
			
			waitForExtJSAjaxComplete(10);
			
			selectEnergyObject(energyObjectMetrics);
			
			waitForExtJSAjaxComplete(10);
			
			saveClose(DIALOG_PROPERTIES_NOT_TRUNK);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue((getInfoBarMsgOfWindow("@class", DIALOG_PROPERTIES_NOT_TRUNK)).contains("The item already exists in this Scope Distribution."), "The item already exists in this Scope Distribution.");
			
			waitForExtJSAjaxComplete(20);
			
			Grid.checkRowInGriByTextValueAndClick(driver, DIALOG_PROPERTIES_NOT_TRUNK, energyObjectMetrics);
			
			clickButton("Delete", DIALOG_PROPERTIES_NOT_TRUNK);
			
			clickOnDialogButton("Yes");
			
			waitForExtJSAjaxComplete(10);
	
			saveClose(DIALOG_PROPERTIES_NOT_TRUNK);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, energyObjectMetrics, "@class", "slnmMeterId"), "Energy Object is Present in Scope Tab");
		
		} else if(build122){
			setValueTreeLookup(energyObjectBuilding5);
			
			waitForExtJSAjaxComplete(10);
			
			clickAddEnergyObjectOnObjectToScope();
			
			waitForExtJSAjaxComplete(10);
			
			setValueTreeLookup(energyObjectBuilding5);
			
			waitForExtJSAjaxComplete(10);
			
			saveClose(DIALOG_PROPERTIES_NOT_TRUNK);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue((getInfoBarMsgOfWindow("@class", DIALOG_PROPERTIES_NOT_TRUNK)).contains("The item already exists in this Scope Distribution."), "The item already exists in this Scope Distribution.");
			
			waitForExtJSAjaxComplete(20);
			
			Grid.checkRowInGriByTextValueAndClick(driver, DIALOG_PROPERTIES_NOT_TRUNK, energyObjectBuilding5);
			
			clickButton("Delete", DIALOG_PROPERTIES_NOT_TRUNK);
			
			clickOnDialogButton("Yes");
			
			waitForExtJSAjaxComplete(10);
			
			saveClose(DIALOG_PROPERTIES_NOT_TRUNK);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, energyObjectBuilding5, "@class", "slnmMeterId"), "Energy Object is Present in Scope Tab");
		}
		
		}
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, OBJECTS_IN_SCOPE_TAB), "Objects In Scope Header is present in Footer Area of Scope Tab and is visible");
		
		if(build122){
			softAssert.assertTrue(McsElement.isElementPresent(driver, COMMODITY_ENDUSE_IN_SCOPE_TAB_122), "Commodity EndUse is present in Scope Tab and is visible");
		} else{
			softAssert.assertTrue(McsElement.isElementPresent(driver, COMMODITY_ENDUSE_IN_SCOPE_TAB), "Commodity EndUse is present in Scope Tab and is visible");
		}
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, DISTRIBUTION_RATIO_SCOPE_TAB), "Distribution Ratio is present in Scope Tab and is visible");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, DISTRIBUTION_PERCENTAGE_SCOPE_TAB), "Distribution Percentage is present in Scope Tab and is visible");
		
		softAssert.assertAll();
		
		Reporter.log("Meter Scope with Commodity End-use is successfully verified", true);
	 }
	 
	 /**
	  * Test Case Id : C60515
	  * Author : SSU
	  */
	 @Test(retryAnalyzer=RetryAnalyzer.class)
	 public void testMeterScopeAddEditCommodityEndUse() throws Exception {
		 
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Adding/Editing of Commodity End-use : C60515" + " </span><br>", 
					true);
				
		SoftAssert softAssert = new SoftAssert();
			
		softAssert.setMethodName("testMeterScopeAddEditCommodityEndUse");
		
		Boolean build122 = driver.getCurrentUrl().contains("122");
		Boolean build14 = driver.getCurrentUrl().contains("14");
		
		String energyObjectMetrics = "slnmMetricsEO1";
		String energyObjectBuilding5 = "slnmEnrgBuilding5";
		
		//Navigate to Meters Overview
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		openMeteringEntity("Meters");
		
		waitForExtJSAjaxComplete(20);
		
		String energyObjectReference = "slnmMetricsMeter";
		String energyObject122 = "2preMeter";
		
		//Open pre-configured Meter and go to Channels tab
		if(!build122){
			Grid.checkRowInGriByTextValueAndClick(driver, "@class", METER_GRID_CLASS, energyObjectReference);
		} else {
			Grid.checkRowInGriByTextValueAndClick(driver, "@class", METER_GRID_CLASS, energyObject122);
		}
		
		clickButton("Edit", METER_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		clickScopeTab();
		
		waitForExtJSAjaxComplete(10);
		
		if(getEffectiveDateOnScopeTab().isEmpty()) {
			clickAddOnScopeTab();
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			setEffectiveDateOnScope(ADD_SCOPE_WINDOW_HEADER, getFutureDate(-1));
		} else {
			if(build14 || build122){
				clickEditOnScopeTab();
				
				waitForExtJSAjaxComplete(20);
				
				waitForExtJSAjaxComplete(20);
				
				setEffectiveDateOnScope(EDIT_SCOPE_WINDOW_HEADER, getFutureDate(-1));
			}
		}
		
		waitForExtJSAjaxComplete(20);
		
		if(verifyItemEnergyObjectExist(energyObjectMetrics))  {
			
			Grid.checkRowInGriByTextValueAndClick(driver, DIALOG_PROPERTIES_NOT_TRUNK, energyObjectMetrics);
			
			clickButton("Delete", DIALOG_PROPERTIES_NOT_TRUNK);
			
			clickOnDialogButton("Yes");
			
			waitForExtJSAjaxComplete(10);
		
		} else if(verifyItemEnergyObjectExist(energyObjectBuilding5)) {
			
			Grid.checkRowInGriByTextValueAndClick(driver, DIALOG_PROPERTIES_NOT_TRUNK, energyObjectBuilding5);
			
			clickButton("Delete", DIALOG_PROPERTIES_NOT_TRUNK);
			
			clickOnDialogButton("Yes");
			
			waitForExtJSAjaxComplete(10);
		}
		
		//TODO Trunk doesnt have ADD /EDit Buttons Active
		if(build122 || build14) {
		
		clickAddEnergyObjectOnObjectToScope();
		
		waitForExtJSAjaxComplete(10);
	
		if (!build122) {
	
			selectEnergyObject(energyObjectMetrics);
			
			waitForExtJSAjaxComplete(10);
			
			/*saveClose(DIALOG_PROPERTIES_NOT_TRUNK);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, energyObjectMetrics, "@class", "slnmMeterId"), "Energy Object is Present in Scope Tab");*/
		
		} else if(build122){
			
			setValueTreeLookup(energyObjectBuilding5);
			
			waitForExtJSAjaxComplete(20);
			
			/*waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, energyObjectBuilding5, "@class", "slnmMeterId"), "Energy Object is Present in Scope Tab");*/
		}
		
		if(!build122){
			setCommodityEndUse("@class", DIALOG_PROPERTIES_NOT_TRUNK, "Miscellaneous", "Commodity End Use");
		} else {
			setCommodityEndUse("@class", DIALOG_PROPERTIES_NOT_TRUNK, "Miscellaneous", "Commodity End-use");
		}
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Refrigeration", "@class", DIALOG_PROPERTIES_NOT_TRUNK), "Energy Object is Present in Scope Tab");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(verifyCommodityEndUseDeletion(), "User is not able to Delete Commodity End Use in Scope Tab");
		} else{
			Reporter.log("Trunk Doesnt have Add/Edit Button Active");
		}
	
		softAssert.assertAll();
		
		Reporter.log("Adding/Editing of Commodity End-use is successfully verified", true);
	 }
}

