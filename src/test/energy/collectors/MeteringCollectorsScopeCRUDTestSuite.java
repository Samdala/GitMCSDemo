package test.energy.collectors;

import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;

import java.util.Date;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.energy.meters.MetersPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.DateTimeManipulation;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class MeteringCollectorsScopeCRUDTestSuite extends
		MeteringCollectorsScopePageObject {

	 @Test(enabled=true)
	public void testAnalysisCollectorScopeCreateEditClose() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: ScopeCreateEditClose Collector " + " </span><br>",
				true);

		Reporter.log("User ScopeCreateEditClose Collector : C19917 "  + " <br>",
				true);
		
		DateTimeManipulation today = new DateTimeManipulation();
		String todayDate = new Date().toString();
		String preiousDate = getFutureDate(-5);
		
		String reference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String effectiveDate = preiousDate;//"01-10-2014";//getCurrentSystemDate();
		
		String supPoint = "1preSP";

		
		String energyObjectReference = "slnmEnrgBuilding1";
		
		String energyObjectReferenceEdited= "slnmEnrgBuilding1.2";

		String meterReference = "test auto mtr " + getCurrentDate().substring(getCurrentDate().length()-6);
		String meterCode = "test auto mtr " + getCurrentDate().substring(getCurrentDate().length()-6);
		
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
		
		String meterRef = meterReference;
		//Navigator
		
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite1";
		String supplyPointName = "codePrefixNonUnique";

		//String meterReference = reference;//"1preMeter";
		
		SoftAssert softAssert = new SoftAssert();
		
		MetersPageObject meters = new MetersPageObject();
		
		softAssert.setMethodName("ScopeCreateEditClose");
	
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		meters.clickMetersInfrastructureTab();
		
		waitForExtJSAjaxComplete(20);
		
		setEffectiveDate("01-01-2014");
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		//Add Meter and check if default channel is created with correct field values
		
		meters.clickOnSupplyPoint(supplyPointName);
				
		waitForExtJSAjaxComplete(10);
		
		clickCreateMeter();
		
		waitForExtJSAjaxComplete(10);
		
		meters.setReference(meterReference);
		
		meters.setCode(meterCode);
		
		meters.setStatus(status);
		
		waitForExtJSAjaxComplete(10);
		
		meters.setTimeZone(timeZone);
		
		waitForExtJSAjaxComplete(10);
		
		meters.setCommodity(commodity);
		
		waitForExtJSAjaxComplete(10);
		
		meters.setMeterOperator(meterOperator);

		waitForExtJSAjaxComplete(10);
		
		meters.setCommissioningDate(date);
		
		meters.setModel(model);
		
		meters.setSerialNumber(serialNumber);
		
		meters.setPhysicalLocationNodeLevel(site);
		
		meters.setManufacturer(manufacturer);
		
		meters.setDescription(description);
		
		meters.setAccessInstructions(accessInstr);
		
		save(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		close(DIALOG_METER);
		
		//Navigate to Navigator and select created meter

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(10);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, "slnmEnrgArea1 (slnmEnrgArea)");
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(10);
		
		clickSystemViewTab();

		waitForExtJSAjaxComplete(10);
		
		setEffectiveDate("01-01-2014");
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);
		
		//clickOnMeter(supPoint);//click on supply point
		
		waitForExtJSAjaxComplete(10);
		
		clickOnMeter(meterRef);
		
		waitForExtJSAjaxComplete(10);
		
		clickCreateCollector();		
		
		waitForExtJSAjaxComplete(10);
	
		setCode(code);
		
		setReference(reference);
		
		save();
		
		waitForExtJSAjaxComplete(25);
		
		close();
		
		waitForExtJSAjaxComplete(10);
		
		clickOnMeter(reference); // clickCollector
		
		waitForExtJSAjaxComplete(10); 
		
		clickEdidCollector();		

		waitForExtJSAjaxComplete(10);
		
		clickScopeTab();
		
		waitForExtJSAjaxComplete(10);
		
		//ScopeTabs functions
		
		//add new scope with effectiveDate = current - 5 days
		clickAddOnScopeTab();
		
		waitForExtJSAjaxComplete(10);
		
		setEffectiveDateOnScopeTab(effectiveDate);
		
		waitForExtJSAjaxComplete(10);
		
		clickAddEnergyObjectOnObjectToScope();
		
		waitForExtJSAjaxComplete(10);
		
		selectEnergyObject(energyObjectReference);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(verifyItemEnergyObjectExist(energyObjectReference), "Energy Object Exist 1");
		
		waitForExtJSAjaxComplete(10);
		
			save(DIALOG_PROPERTIES);
		
		waitForExtJSAjaxComplete(10);
		
			close(DIALOG_PROPERTIES);
		
		waitForExtJSAjaxComplete(10);
		
		clickEditOnScopeTab();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(verifyItemEnergyObjectExist(energyObjectReference), "EnergyObjectExist " + energyObjectReference);
		
		waitForExtJSAjaxComplete(10);
		
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
		
		softAssert.assertFalse(verifyItemEnergyObjectNotExist(energyObjectReference), "EnergyObject is not existing 4" + energyObjectReference);
		
		waitForExtJSAjaxComplete(20);
		
			save(DIALOG_PROPERTIES);
		
		waitForExtJSAjaxComplete(20);
		
			close(DIALOG_PROPERTIES);
		waitForExtJSAjaxComplete(20);
		
		clickEditOnScopeTab();
		
		softAssert.assertFalse(verifyItemEnergyObjectNotExist(energyObjectReference), "EnergyObject is not existing." + energyObjectReference);			
		
			close(DIALOG_PROPERTIES);
		
		waitForExtJSAjaxComplete(20);
		
		clickCloseOnScopeTab();
		
		waitForExtJSAjaxComplete(20);
		
		///close scope in three days to today
		String threeDaysToCurrent = getFutureDate(-3);
		
		setDateOnCloseScope(threeDaysToCurrent);
		
		waitForExtJSAjaxComplete(20);
		
		clickOkOnCloseScope();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkButtonEditDisabledOnScopeTab(), "Edit button is disabled.");
				
		softAssert.assertTrue(checkButtonCloseDisabledOnScopeTab(), "Close button is disabled.");
		
		
		//add and close scope with todays date
		
		waitForExtJSAjaxComplete(20);
		
		///two days to today
		String twoDaysToCurrent = getFutureDate(-2);
		
		clickAddOnScopeTab();
		
		waitForExtJSAjaxComplete(20);
		
		setEffectiveDateOnScopeTab(twoDaysToCurrent);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddEnergyObjectOnObjectToScope();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		selectEnergyObject(energyObjectReferenceEdited);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyItemEnergyObjectExist(energyObjectReferenceEdited), "EnergyObjectEdited Exist " + energyObjectReferenceEdited);
		
		softAssert.assertFalse(verifyItemEnergyObjectNotExist(energyObjectReference), "EnergyObject is not existing 4" + energyObjectReference);
		
		waitForExtJSAjaxComplete(20);
		
			save(DIALOG_PROPERTIES);

			waitForExtJSAjaxComplete(20);
		
			close(DIALOG_PROPERTIES);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditOnScopeTab();
		
		softAssert.assertFalse(verifyItemEnergyObjectNotExist(energyObjectReference), "EnergyObject is not existing." + energyObjectReference);			
		
			close(DIALOG_PROPERTIES);
		
		waitForExtJSAjaxComplete(20);
		
		clickCloseOnScopeTab();
		
		waitForExtJSAjaxComplete(20);
		
		///close scope today
		
		setDateOnCloseScope(getCurrentSystemDate());
		
		String dayAheadAfterToday = getFutureDate(1);		
		
		//validate user not possible to close scope in future
		softAssert.assertTrue(validateDayIsDisabledForClosingScope(dayAheadAfterToday), "Date is disabled.");
		////////
		
		waitForExtJSAjaxComplete(20);
		
		clickOkOnCloseScope();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkButtonEditDisabledOnScopeTab(), "Edit button is disabled.");
				
		softAssert.assertTrue(checkButtonCloseDisabledOnScopeTab(), "Close button is disabled.");
		
		close(ADD_COLLECTORS_FORM_CLASS);
		
		//user not able to add scope to meter with collector.
		
		clickOnMeter(meterReference); // clickMeter
		
		waitForExtJSAjaxComplete(20); 
		
		clickEdidCollector();		

		waitForExtJSAjaxComplete(20);
		
		clickScopeTab();
		
		waitForExtJSAjaxComplete(20);
		
		//ScopeTabs functions
		
		//add new scope with effectiveDate = current - 5 days
		clickAddOnScopeTabOnMeters();
		
		waitForExtJSAjaxComplete(20);
		
		setEffectiveDateOnScopeTab(effectiveDate);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddEnergyObjectOnObjectToScope();
		
		waitForExtJSAjaxComplete(20);
		
		selectEnergyObject(energyObjectReference);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyItemEnergyObjectExist(energyObjectReference), "Energy Object Exist 1");
		
		waitForExtJSAjaxComplete(20);
		
			save(DIALOG_PROPERTIES);
			waitForExtJSAjaxComplete(20);
			softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_PROPERTIES),"Message about invalid form is present");
		
		waitForExtJSAjaxComplete(20);	
		
		softAssert.assertAll();
		
		Reporter.log("Collector"  + " was successfully edited", true);

	}

	 /**
	 * Test Case ID: C60547
	 * Author : SSU
	 */
	 @Test(enabled=true)
	 public void testCollectorScopeCannotBeAddedClosedAtSameDate() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Collector Scope cannot be added/closed at same date: C60547" + " </span><br>",
				true);
		
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite2";
		String supplyPointName = "EAN-1preSP";
		String meterRef = "C60547MeterRef" +getCurrentDate().substring(getCurrentDate().length()-6);
		String meterCod = "C60547MeterCod" +getCurrentDate().substring(getCurrentDate().length()-6);
		String reference = "C60547Ref" + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "C60547Cod" + getCurrentDate().substring(getCurrentDate().length()-6);
		String status = "Active";
		
		SoftAssert softAssert = new SoftAssert();
		
		MetersPageObject meters = new MetersPageObject();
		
		softAssert.setMethodName("testCollectorScopeCannotBeAddedClosedAtSameDate");
	
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		meters.clickMetersInfrastructureTab();
		
		waitForExtJSAjaxComplete(20);
		
		meters.setEffectiveDate("30-01-2014");	
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		
		meters.setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		
		MetersPageObject mpo = new MetersPageObject();
		
		mpo.clickOnSupplyPoint(supplyPointName);
		
		waitForExtJSAjaxComplete(20);
		
		mpo.clickCreateMeter();
		
		waitForExtJSAjaxComplete(20);
		
		mpo.setReference(meterRef);
		
		mpo.setCode(meterCod);
		
		mpo.setStatus(status);
		
		waitForExtJSAjaxComplete(20);
		
		mpo.save(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		mpo.close(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnMeter(meterRef);
		
		waitForExtJSAjaxComplete(10);
		
		clickCreateCollector();		
		
		waitForExtJSAjaxComplete(10);
	
		setCode(code);
		
		setReference(reference);
		
		save(ADD_COLLECTORS_FORM_CLASS);
		
		Thread.sleep(20000);
		
		close(ADD_COLLECTORS_FORM_CLASS);
		
		Thread.sleep(10000);
		
		waitForExtJSAjaxComplete(10);
		
		clickOnMeter(reference); // clickCollector
		
		waitForExtJSAjaxComplete(10); 
		
		clickEdidCollector();		

		waitForExtJSAjaxComplete(10);
		
		clickScopeTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickAddOnScopeTab();
			
		waitForExtJSAjaxComplete(20);
			
		waitForExtJSAjaxComplete(20);
			
		setEffectiveDateOnScope(ADD_SCOPE_WINDOW_HEADER, getFutureDate(0));
		
		waitForExtJSAjaxComplete(20);
		
		String energyObjectNonUnique = "slnmNonUnique";
		
		if(verifyItemEnergyObjectExist(energyObjectNonUnique))  {
		
			Grid.checkRowInGriByTextValueAndClick(driver, DIALOG_PROPERTIES_NOT_TRUNK, energyObjectNonUnique);
			
			clickButton("Delete", DIALOG_PROPERTIES_NOT_TRUNK);
			
			clickOnDialogButton("Yes");
			
			waitForExtJSAjaxComplete(10);
		
		}
		
		clickAddEnergyObjectOnObjectToScope();
		
		waitForExtJSAjaxComplete(10);

		selectEnergyObject(energyObjectNonUnique);
			
		waitForExtJSAjaxComplete(10);
			
			saveClose("energy properties");
			
			waitForExtJSAjaxComplete(20);
			
			
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, energyObjectNonUnique, "@class", "slnmCollectors"), "Energy Object is Present in Scope Tab");
		
		waitForExtJSAjaxComplete(10);
		
		//Add Scope Again and Select same Date as Previous scope
		
		clickAddOnScopeTab();
		
		waitForExtJSAjaxComplete(20);
			
		waitForExtJSAjaxComplete(20);
			
		setEffectiveDateOnScope(ADD_SCOPE_WINDOW_HEADER, getFutureDate(0));
		
		waitForExtJSAjaxComplete(20);
		
		clickAddEnergyObjectOnObjectToScope();
		
		waitForExtJSAjaxComplete(10);
		
		selectEnergyObject(energyObjectNonUnique);
		
		waitForExtJSAjaxComplete(10);
		
		String warningMessage = qtipText(ADD_SCOPE_WINDOW_HEADER);
		
		softAssert.assertTrue(warningMessage.contains("You cannot add a Scope Distribution with the same or earlier date as that of the last Distribution."), "Warning Message is displayed");
		
		waitForExtJSAjaxComplete(5);
		
			close("energy properties");
			
			waitForExtJSAjaxComplete(20);
			
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		// Close the scope
		
		clickCloseOnScopeTab();
		
		waitForExtJSAjaxComplete(20);
		
		setDateOnCloseScope(getFutureDate(0));
		
		clickOkOnCloseScope();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(verifyScopeClosedMsg(), "Scope is successfully closed");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		// Add Scope Again
		clickAddOnScopeTab();
		
		waitForExtJSAjaxComplete(20);
			
		waitForExtJSAjaxComplete(20);
			
		setEffectiveDateOnScope(ADD_SCOPE_WINDOW_HEADER, getFutureDate(0));
		
		waitForExtJSAjaxComplete(20);
		
		clickAddEnergyObjectOnObjectToScope();
		
		waitForExtJSAjaxComplete(10);
		
		selectEnergyObject(energyObjectNonUnique);
		
		waitForExtJSAjaxComplete(10);
		
			saveClose("energy properties");
			
			waitForExtJSAjaxComplete(20);
			
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(getInfoBarMsgOfWindow("@id", getXWindowId(ADD_SCOPE_WINDOW_HEADER)).contains("The form is invalid. Hover over the fields in red to see the errors."), "New Scope cannot be added when the last one was closed.");
		
		softAssert.assertAll();
		
		Reporter.log("Collector Scope cannot be added/closed at same date is successfully verified", true);
	 }
}
