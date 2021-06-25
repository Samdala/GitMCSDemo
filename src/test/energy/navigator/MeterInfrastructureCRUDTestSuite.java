package test.energy.navigator;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.energy.supplypoints.MeteringSuplyPointsPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class MeterInfrastructureCRUDTestSuite extends MeterInfrastructurePageObject {

	/**
	 * Testcase ID	 : C60252
	 * Author		 : ssu
	 * */
	@Test(enabled=true)
	public void testSupplyPointDeactivation() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60252: Supply Point without successors can be deactivated </span><br>", true);
		
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite2";
		String commodityClass = "Electricity";
		String tabText = "Properties";
		
		String code = "C60252"+getCurrentDate().substring(getCurrentDate().length()-5);
		String commodityName = "Electricity";
		String typeName = "preSPTref";
		String supplySystem = "High Voltage";
		String powerCapacity = "1000";
		String futureDate = getFutureDate(5);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testSupplyPointDeactivation");
		
		waitForExtJSAjaxComplete(30);
		
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
		
		waitForExtJSAjaxComplete(5);
		
		MeteringSuplyPointsPageObject meteringSuplyPointsPageObject = new MeteringSuplyPointsPageObject();
		
		setEffectiveDate("05-05-2014");
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityClass(commodityClass);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickMetersInfrastructureTab();
		
		waitForExtJSAjaxComplete(5);
		
		setEffectiveDate("05-05-2014");
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityClass(commodityClass);
		
		waitForExtJSAjaxComplete(20);
		
		meteringSuplyPointsPageObject.clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnTab(ADD_SUPPLY_POINT_WINDOW_HEADER, tabText);
		
		meteringSuplyPointsPageObject.setCode(code);
		
		meteringSuplyPointsPageObject.clickReference();
		
		waitForExtJSAjaxComplete(20);
		
		String reference = meteringSuplyPointsPageObject.getReference();
		String codePrefix = meteringSuplyPointsPageObject.getCodePrefix();
		String codeInSplyPt = meteringSuplyPointsPageObject.getCode();
		String siteInSplPt = meteringSuplyPointsPageObject.getSite();
		
		softAssert.assertEquals(reference, codePrefix+codeInSplyPt, "Code Prefix + Code is available in Reference");
		
		softAssert.assertEquals(siteInSplPt, site, "Site is Automatically populated in Supply Point");
		
		waitForExtJSAjaxComplete(20);
		
		meteringSuplyPointsPageObject.setType(typeName, ADD_SUPPLY_POINT_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(5);
		
		meteringSuplyPointsPageObject.setCommodity(commodityName);
		
		waitForExtJSAjaxComplete(5);
		
		meteringSuplyPointsPageObject.setPowerCapacity(powerCapacity);
		
		meteringSuplyPointsPageObject.setSupplySystem(DIALOG_SP, supplySystem);
		
		waitForExtJSAjaxComplete(5);
		
		meteringSuplyPointsPageObject.save();
		
		waitForExtJSAjaxComplete(25);
		
		meteringSuplyPointsPageObject.close();
		
		waitForExtJSAjaxComplete(30);
		
		clickOnSupplyPoint(reference);
		
		waitForExtJSAjaxComplete(20);
		
		deactivateSupplyPoint();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		setEffectiveDate(futureDate);
		
		waitForExtJSAjaxComplete(30);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(30);
		
		softAssert.assertFalse(verifySupplyPointExist(reference), reference+" Supply Point after deactivation is present");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("Supply Point without successors is deactivated successfully", true);
	}
	
	/**
	 * Testcase ID	 : C60260
	 * Author		 : ssu
	 * */
	@Test(enabled=true)
	public void testPredecessorsDoesntExistForSupplyPoint() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60260: Supply Point cannot have Predecessors </span><br>", true);
		
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite1";
		String commodityClass = "Electricity";
		String propertiesTab = "Properties";
		String businessPartnersTab = "Business Partners";
		String notesTab = "Notes";
		String detailsTab = "Details";
		String supplyPointName = "codePrefixNonUnique";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testPredecessorsDoesntExistForSupplyPoint");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(5);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		waitForExtJSAjaxComplete(5);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickMetersInfrastructureTab();
		
		Thread.sleep(5000);
		
		waitForExtJSAjaxComplete(25);
		
		setEffectiveDate("01-01-2014");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		setCommodityClass(commodityClass);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickOnSupplyPoint(supplyPointName);
		
		waitForExtJSAjaxComplete(10);
		
		clickEdidMeter();
		
		Thread.sleep(5000);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnTab(EDIT_SUPPLY_POINT_WINDOW_HEADER, propertiesTab);
		
		waitForExtJSAjaxComplete(5);
		
		clickOnTab(EDIT_SUPPLY_POINT_WINDOW_HEADER, detailsTab);
		
		waitForExtJSAjaxComplete(5);
		
		clickOnTab(EDIT_SUPPLY_POINT_WINDOW_HEADER, businessPartnersTab);
		
		waitForExtJSAjaxComplete(5);
		
		clickOnTab(EDIT_SUPPLY_POINT_WINDOW_HEADER, notesTab);
		
		waitForExtJSAjaxComplete(5);
		
		boolean predecessorTabStatus = verifyPredecessorTabExist(EDIT_SUPPLY_POINT_WINDOW_HEADER);
		
		softAssert.assertFalse(predecessorTabStatus, "Predecessor Table Doesnt Exist for Supply Point");
		
		softAssert.assertAll();
		
		Reporter.log("Supply Point cannot have Predecessors is successfully verified", true);
	}
	
	/**
	 * Testcase ID	 : C60280
	 * Author		 : ssu
	 * */
	@Test(enabled=true)
	public void testOnePredecessorPerMeter() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60280: Meter can only have one Predecessor </span><br>", true);
		
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite2";
		String supplyPointName = "EAN-1preSP";
		String meterName = "1preMeter";
		String predecessorTab = "Predecessors";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testOnePredecessorPerMeter");
		
		waitForExtJSAjaxComplete(30);
		
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
		
		waitForExtJSAjaxComplete(5);
		
		setEffectiveDate("01-01-2014");
		
		waitForExtJSAjaxComplete(20);
		
		clickOnSupplyPoint(supplyPointName);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnAddExisitingMeter();
		
		waitForExtJSAjaxComplete(20);
		
		boolean textMessage = verifyMeter1IsPresentInLookUp("Reference", "1preMeter", SELECT_A_METER_WINDOW_HEADER);

		softAssert.assertFalse(textMessage, "Meter 1 is not available in the Grid");
		
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnMeters(meterName);
		
		waitForExtJSAjaxComplete(20);
		
		clickEdidMeter();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnTab(EDIT_METER_WINDOW_HEADER, predecessorTab);
		
		waitForExtJSAjaxComplete(10);
		
		clickOnAddBtnPredecessorsTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickOnAddSupplyPointPredecessorsTab(ADD_PREDECESSOR_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		String warningMsg = this.getWarningMsg(INFO_MESSAGE_WINDOW_HEADER);
		  
		softAssert.assertTrue(warningMsg.contains("Meter can have only one Predecessor"), "Meter can have only one Predecessor is verified");
		
		waitForExtJSAjaxComplete(20);
		
		clickButtonOnForm("x-window-footer x-panel-btns", "OK");
		
		waitForExtJSAjaxComplete(10);
		
		clickOnAddMeterPredecessorsTab(ADD_PREDECESSOR_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		warningMsg = this.getWarningMsg(INFO_MESSAGE_WINDOW_HEADER);
		  
		softAssert.assertTrue(warningMsg.contains("Meter can have only one Predecessor"), "Meter can have only one Predecessor is verified");
		
		waitForExtJSAjaxComplete(20);
		
		clickButtonOnForm("x-window-footer x-panel-btns", "OK");
		
		waitForExtJSAjaxComplete(10);
		
		clickOnAddCollectorPredecessorsTab(ADD_PREDECESSOR_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		warningMsg = this.getWarningMsg(INFO_MESSAGE_WINDOW_HEADER);
		  
		softAssert.assertTrue(warningMsg.contains("Meter can have only one Predecessor"), "Meter can have only one Predecessor is verified");
		
		waitForExtJSAjaxComplete(20);
		
		clickButtonOnForm("x-window-footer x-panel-btns", "OK");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("Meter can have only one predecessor is successfully verified", true);
	}
	
	/**
	 * Testcase ID	 : C60295 & C60519
	 * Author		 : ssu
	 * */
	@Test(enabled=true)
	public void testScopeOfMetersWithoutSuccessors() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60295 & C60519: Scope logic restrictions: Meter without Successors can have Scope</span><br>", true);
		
		String area = "slnmMetricsArea";
		String site = "slnmMetricsSite";
		String meterName = "slnmMetricsMeter";
		String scopeTab = "Scope";
		String energyObjReference = "slnmMetricsEO1";
		String energyObjectReferenceEco = "eo code test";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testScopeOfMetersWithoutSuccessors");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
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
		
		waitForExtJSAjaxComplete(5);
		
		setEffectiveDate("01-01-2014");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickOnMeters(meterName);
		
		waitForExtJSAjaxComplete(10);
		
		clickEdidMeter();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnTab(EDIT_METER_WINDOW_HEADER, scopeTab);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		if(getEffectiveDateOnScopeTab().isEmpty()) {
			clickAddOnScopeTab();
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			setEffectiveDateOnScope(ADD_SCOPE_WINDOW_HEADER, getFutureDate(-1));
		} else {
			clickEditOnScopeTab();
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			setEffectiveDateOnScope(EDIT_SCOPE_WINDOW_HEADER, getFutureDate(-1));
		}
		
		waitForExtJSAjaxComplete(10);
		
		if(verifyItemEnergyObjectExist(energyObjReference))  {
		
			Grid.checkRowInGriByTextValueAndClick(driver, DIALOG_PROPERTIES, energyObjReference);
			
			clickButton("Delete", DIALOG_PROPERTIES);
			
			clickOnDialogButton("Yes");
			
			waitForExtJSAjaxComplete(10);
		
		} /* else if(verifyItemEnergyObjectExist(energyObjectReferenceEco)) {
			
			Grid.checkRowInGriByTextValueAndClick(driver, DIALOG_PROPERTIES_NOT_TRUNK, energyObjectReferenceEco);
			
			clickButton("Delete", DIALOG_PROPERTIES_NOT_TRUNK);
			
			clickOnDialogButton("Yes");
			
			waitForExtJSAjaxComplete(10);
		}*/
		
		clickAddEnergyObjectOnObjectToScope();
		
		Thread.sleep(20000);
		
		selectEnergyObject(energyObjReference);
		
		Thread.sleep(10000);
		
		saveClose(DIALOG_PROPERTIES);

		Thread.sleep(20000);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, energyObjReference, "@class", "slnmMeterId"), "Energy Object is Present in Scope Tab");
		
		softAssert.assertAll();
		
		Reporter.log("Meter without Successors can have Scope is successfully verified", true);
	}
	
	/**
	 * Testcase ID	 : C60282
	 * Author		 : ssu
	 * */
	@Test(enabled=true)
	public void testMeterPredecessorIsSupplyPoint() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60282: Meter can have Supply Point as Predecessor </span><br>", true);
		
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite2";
		String supplyPointName = "EAN-1preSP";
		String propertiesTab = "Properties";
		
		String code = "C60282preMeter";
		String commodityName = "Electricity";
		String expectedClassName = "Commodity Meter";
		String activeStatus = "Active";
		String meterReference = "RefpreMeter";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testMeterPredecessorIsSupplyPoint");
		
		waitForExtJSAjaxComplete(30);
		
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
		
		waitForExtJSAjaxComplete(5);
		
		setEffectiveDate("01-01-2015");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickOnSupplyPoint(supplyPointName);
		
		waitForExtJSAjaxComplete(20);
		
		clickCreateMeter();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnTab(ADD_METER_WINDOW_HEADER, propertiesTab);
		
		waitForExtJSAjaxComplete(10);
		
		setCode(code);
		
		waitForExtJSAjaxComplete(10);
		
		setReference(meterReference);
		
		waitForExtJSAjaxComplete(10);
		
		setCommodity(commodityName);
		
		waitForExtJSAjaxComplete(10);
		
		setStatus(activeStatus);
		
		waitForExtJSAjaxComplete(10);
		
		String className = getFieldValue(DIALOG_METER, "class");
		
		softAssert.assertEquals(className, expectedClassName, "Commodity Meter is defaulted as class value");
		
		waitForExtJSAjaxComplete(10);
		
		save(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		close(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(30);
		
		softAssert.assertTrue(verifyMeterExist(meterReference), meterReference+" Meter is present as successor of Supply Point");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("Meter can have Supply Point as Predecessor is successfully verified", true);
	}
	
	
}
