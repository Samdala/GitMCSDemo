package test.energy.meters;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class MetersScopeAddEdditCloseTestSuite extends MetersPageObject{
	
	 @Test(enabled=true)
		public void testMeterScopeCreateEditClose() throws Exception  {

			Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Scope Add Eddit Close: C39612, C39613, C39609" + " </span><br>",
					true);

			Reporter.log("User creates and edits and close energy object from Meter Scope"  + " <br>",
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
			
			//Navigator
			
			String area = "slnmEnrgArea1 (slnmEnrgArea)";
			String site = "slnmEnrgSite2";
			String supplyPointName = "EAN-1preSP";

			//String meterReference = reference;//"1preMeter";
			
			String energyObjectReference = "slnmNonUnique";
			
			String energyObjectReferenceEdited;
				energyObjectReferenceEdited = "slnmEnrgScopeEdit";
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
			
			Thread.sleep(2000);
			
			//Add Meter and check if default channel is created with correct field values
			
			clickOnSupplyPoint(supplyPointName);
			
			waitForExtJSAjaxComplete(20);
			
			clickCreateMeter();
			
			Thread.sleep(2000);
			
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
			
				setPhysicalLocation(site);
			
			setManufacturer(manufacturer);
			
			setDescription(description);
			
			setAccessInstructions(accessInstr);
			
			save(DIALOG_METER);
			
			Thread.sleep(2000);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			String windowID = getXWindowIdByClassCustomized(DIALOG_METER);
			
			waitForExtJSAjaxComplete(20);
			
			/*closeUsingToolBar("//div[contains(@id,'"+windowID+"') and contains(@class, '"+DIALOG_METER+"')]");
			
			Thread.sleep(3000);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			Reporter.log("Closed SP ", true);
			
			waitForExtJSAjaxComplete(25);
			
			clickOnSupplyPoint(reference);              
			
			Thread.sleep(2000);
			
			waitForExtJSAjaxComplete(25); 
			
			clickEdidMeter();*/
			
			Thread.sleep(2000);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(20);
			
			clickScopeTab();
			
			Thread.sleep(2000);
			
			waitForExtJSAjaxComplete(20);
			
			//ScopeTabs functions
			
			clickAddOnScopeTab();
			
			Thread.sleep(4000);
			
			waitForExtJSAjaxComplete(20);
			
			setEffectiveDateOnScope(ADD_SCOPE_WINDOW_HEADER, getFutureDate(0));
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			clickAddEnergyObjectOnObjectToScope();
			
			waitForExtJSAjaxComplete(20);
			
			selectEnergyObject(energyObjectReference);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(verifyItemEnergyObjectExist(energyObjectReference), "Energy Object Exist 1");
			
			waitForExtJSAjaxComplete(20);
			
				save(DIALOG_PROPERTIES);
			
			waitForExtJSAjaxComplete(20);
			
				close(DIALOG_PROPERTIES);
			
			waitForExtJSAjaxComplete(20);
			
			clickEditOnScopeTab();
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(verifyItemEnergyObjectExist(energyObjectReference), "EnergyObjectExist " + energyObjectReference);
			
			waitForExtJSAjaxComplete(20);
			
			selectEnergyObjectOnScope(energyObjectReference);
			
			waitForExtJSAjaxComplete(20);
			
			clickDeleteOnObjectToScope();
			
			waitForExtJSAjaxComplete(20);
			
			clickOnDialogButton("Yes");
			
			waitForExtJSAjaxComplete(20);
			
			clickAddEnergyObjectOnObjectToScope();
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			selectEnergyObject(energyObjectReferenceEdited);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(verifyItemEnergyObjectExist(energyObjectReferenceEdited), "EnergyObjectEdited Exist " + energyObjectReferenceEdited);
			
			softAssert.assertTrue(verifyItemEnergyObjectNotExist(energyObjectReference), "EnergyObject is not existing 4" + energyObjectReference);
			
			waitForExtJSAjaxComplete(20);
			
				save(DIALOG_PROPERTIES);
			
			waitForExtJSAjaxComplete(20);
			
				close(DIALOG_PROPERTIES);
			
			waitForExtJSAjaxComplete(20);
			
			clickEditOnScopeTab();
			
			softAssert.assertTrue(verifyItemEnergyObjectNotExist(energyObjectReference), "EnergyObject is not existing." + energyObjectReference);			
			
				close(DIALOG_PROPERTIES);
			
			waitForExtJSAjaxComplete(20);
			
			clickCloseOnScopeTab();
			
			waitForExtJSAjaxComplete(20);
			
			clickOkOnCloseScope();
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(checkButtonEditDisabledOnScopeTab(), "Edit button is disabled.");
					
			softAssert.assertTrue(checkButtonCloseDisabledOnScopeTab(), "Close button is disabled.");
			
			softAssert.assertAll();
			
			Reporter.log("Meter Channel was successfully created and edited", true);
	 }
	 
	 
}
