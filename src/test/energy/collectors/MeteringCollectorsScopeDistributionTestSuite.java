package test.energy.collectors;

import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;

import java.util.Date;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.energy.meters.MetersPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.DateTimeManipulation;

public class MeteringCollectorsScopeDistributionTestSuite extends
		MeteringCollectorsScopePageObject {

	 @Test(enabled=true)
	public void testAnalysisCollectorScopeDistribution() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: ScopeDistribution " + " </span><br>",
				true);

		Reporter.log("User Scope Distribution on Collector : C39618 "  + " <br>",
				true);
		
		DateTimeManipulation today = new DateTimeManipulation();
		String todayDate = new Date().toString();
		String preiousDate = today.add(todayDate, 5, -5, "dd-MM-yyyy");
		
		String reference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String effectiveDate = preiousDate;//"01-10-2014";//getCurrentSystemDate();
		
		String supPoint = "EAN-1preSP";

		
		String energyObjectReference = "slnmEnrgBuilding2";

		String meterReference = "test auto mtr " + getCurrentDate().substring(getCurrentDate().length()-6);
		String meterCode = "test auto mtr " + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String status = "Active";
		String timeZone = "(GMT+02:00) Athens, Bucharest, Istanbul";
		String commodity = "Electricity";
		String meterOperator = "My Company";
		String date = "03-09-2014";
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
		
		setEffectiveDate(getFutureDate(-810));
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);
		
		//Add Meter and check if default channel is created with correct field values
		
		meters.clickOnSupplyPoint(supplyPointName);
				
		waitForExtJSAjaxComplete(10);
		
		clickCreateMeter();
		
		waitForExtJSAjaxComplete(20);
		
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
		
		meters.setPhysicalLocation(physicalLocation);
		
		meters.setManufacturer(manufacturer);
		
		meters.setDescription(description);
		
		meters.setAccessInstructions(accessInstr);
		
		save(DIALOG_METER);
		
		waitForExtJSAjaxComplete(20);
		
		close(DIALOG_METER);
		
		//Navigate to Navigator and select created meter

		/*waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(10);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(10);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, "slnmEnrgArea1 (slnmEnrgArea)");
		
		waitForExtJSAjaxComplete(10);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite2");
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(10);
		
		clickSystemViewTab();

		waitForExtJSAjaxComplete(10);
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);*/
		
		//clickOnMeter(supPoint);//click on supply point
		
		waitForExtJSAjaxComplete(10);
		
		clickOnMeter(meterReference);
		
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
		
		waitForExtJSAjaxComplete(20);
		
		setEffectiveDateOnScopeTab(effectiveDate);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddEnergyObjectOnObjectToScope();
		
		
		waitForExtJSAjaxComplete(20);
		
		selectEnergyObjectByFiltering(energyObjectReference);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyItemEnergyObjectExist(energyObjectReference), "Energy Object Exist 1");
		
		waitForExtJSAjaxComplete(10);
		
		save(DIALOG_PROPERTIES);
		
		waitForExtJSAjaxComplete(10);
		
		close(DIALOG_PROPERTIES);
		
		waitForExtJSAjaxComplete(10);
		
		clickAddOnScopeTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickAddEnergyObjectOnObjectToScope();
		
		waitForExtJSAjaxComplete(10);
		
		selectEnergyObjectByFiltering("slnmEnrgBuilding1");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getRatio("1"), "1", "Ratio for first row correct");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getPercentage("1"), "50.00%", "Percentage for first row correct");
		
		softAssert.assertEquals(getPercentage("2"), "50.00%", "Percentage for second row correct");
		
		waitForExtJSAjaxComplete(10);
		
		setRatio("1", "5");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getPercentage("1"), "83.33%", "Percentage for first row correct");
		
		softAssert.assertEquals(getPercentage("2"), "16.67%", "Percentage for second row correct");
		
		waitForExtJSAjaxComplete(10);
		
		setRatio("1", "10");
		
		waitForExtJSAjaxComplete(10);
		
		setRatio("2", "20");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getPercentage("1"), "33.33%", "Percentage for first row correct");
		
		softAssert.assertEquals(getPercentage("2"), "66.67%", "Percentage for second row correct");
		
		softAssert.assertAll();
		
		Reporter.log("Collector"  + " was successfully edited", true);

	}

}
