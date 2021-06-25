package test.energy.occurrence;

import java.io.IOException;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class OccurrenceCRUDTestSuite extends
		OccurrencePageObject {


	@Test(enabled=true)
	public void testOccurrenceEdit() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Edit Energy Occurrence :44094 </span><br>",
				true);

		Reporter.log("User edit configuration Occurrence: 39521, 37737 <br>",
				true);
		
		String locationType = "Energy Object";
		String location = "slnmMetricsEO1";
		String area = "slnmMetricsArea";
		String site = "slnmMetricsSite";
		String reference = "test auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		//String occurrenceTypeClass = "Normal Occurrence";
		String type = "1preOccurrenceTypeNormal";
		String startDate = "13-08-2014";
		String startTime = "01:00";
		String endDate = "14-08-2014";
		String endTime = "02:00";
		Boolean bElectricity = true;
		Boolean bCombustibles = true;
		Boolean bWater = true;
		String description = reference;
		
		String referenceEdited = "test auto ed" + getCurrentDate().substring(getCurrentDate().length()-5);
		String endDateEdited = "15-08-2014";
		String endTimeEdited = "04:00";
		Boolean bElectricityEdited = false;
		Boolean bCombustiblesEdited = false;
		Boolean bWaterEdited = false;
		String descriptionEdited = referenceEdited;
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testOccurrenceEdit");
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, site);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, location);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		String panelID = getXPanelId(locationType + " \"" +location + "\"");
			
		System.out.println("panelID" +panelID);
		
		waitForExtJSAjaxComplete(25);
		
		changeTab(panelID, "Actions");
		
		waitForExtJSAjaxComplete(25);
		
		changeTabOccurences(panelID, "Occurrences"); 
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(reference);
		
		waitForExtJSAjaxComplete(20);
		
		setOccurrenceType(type);
		
		waitForExtJSAjaxComplete(20);
		
		setStartDate(startDate);
		
		waitForExtJSAjaxComplete(20);
		
		setStartTime(startTime);
		
		waitForExtJSAjaxComplete(20);
		
		setEndDate(endDate);
		
		waitForExtJSAjaxComplete(20);
		
		setEndTime(endTime);
		
		waitForExtJSAjaxComplete(20);
		
		setImpactElectricity(bElectricity);
		
		setImpactCombustibles(bCombustibles);
		
		setImpactWater(bWater);
		
		setDescription(description);		
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getReference(),reference, reference+" reference before edit");
		
		softAssert.assertEquals(getOccurrenceType(),type, type + " occurrenceType before edit");
		
		softAssert.assertEquals(getStartDate(),startDate, startDate + " startDate before edit");
		
		softAssert.assertEquals(getStartTime(),startTime, startTime + " startTime before edit");
		
		softAssert.assertEquals(getEndDate(),endDate, endDate + " endDate before edit");
		
		softAssert.assertEquals(getEndTime(),endTime, endTime + " endTime before edit");
		
		softAssert.assertEquals(getImpactState("electricity") ,true, " Impact electricity before edit");
		
		softAssert.assertEquals(getImpactState("combustibles") ,true, " Impact combustibles before edit");
		
		softAssert.assertEquals(getImpactState("water") ,true, " Impact water before edit");
		
		softAssert.assertEquals(getDescription(),description, description + " description before edit");
		
		setReference(referenceEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setEndDate(endDateEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setEndTime(endTimeEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setImpactElectricity(bElectricityEdited);
		
		setImpactCombustibles(bCombustiblesEdited);
		
		setImpactWater(bWaterEdited);
		
		setDescription(descriptionEdited);		
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, referenceEdited);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getReference(),referenceEdited, referenceEdited+" reference after edit is wrong");
		
		softAssert.assertEquals(getOccurrenceType(),type, type + " occurrenceType after edit is wrong");
		
		softAssert.assertEquals(getStartDate(),startDate, startDate + " startDate after edit is wrong");
		
		softAssert.assertEquals(getStartTime(),startTime, startTime + " startTime after edit is wrong");
		
		softAssert.assertEquals(getEndDate(),endDateEdited, endDateEdited + " endDate after edit is wrong");
		
		softAssert.assertEquals(getEndTime(),endTimeEdited, endTimeEdited + " endTime after edit is wrong");
		
		softAssert.assertEquals(getImpactState("electricity") ,false, " Impact electricity after edit is wrong");
		
		softAssert.assertEquals(getImpactState("combustibles") ,false, " Impact combustibles after edit is wrong");
		
		softAssert.assertEquals(getImpactState("water") ,false, " Impact water after edit is wrong");
		
		softAssert.assertEquals(getDescription(),descriptionEdited, descriptionEdited + " description after edit is wrong");

		softAssert.assertAll();
		
		Reporter.log("Configuration Occurrence was succesfully edited", true);

	}

	@Test(enabled = true)
	public void testOccurrenceDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Occurrence: C39522, C37745 </span><br>",
				true);

		Reporter.log("User delete Occurrence Type <br>", true);
		
		String locationType = "Energy Object";
		String location = "slnmMetricsEO1";
		String area = "slnmMetricsArea";
		String site = "slnmMetricsSite";
		String reference = "test auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		//String occurrenceTypeClass = "Normal Occurrence";
		String type = "1preOccurrenceTypeNormal";
		String startDate = "13-08-2014";
		String startTime = "01:00";
		String endDate = "14-08-2014";
		String endTime = "02:00";
		Boolean bElectricity = true;
		Boolean bCombustibles = true;
		Boolean bWater = true;
		String description = reference;
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testOccurrenceDelete");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		Thread.sleep(5000);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, site);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, location);
		
		Thread.sleep(5000);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		String panelID = getXPanelId(locationType + " \"" +location + "\"");
			
		System.out.println("panelID" +panelID);
		
		changeTabCustomized(panelID, "Actions");
		
		waitForExtJSAjaxComplete(20);
		
		changeTabOccurences(panelID, "Occurrences");

		Thread.sleep(2000);
		
		clickAddButton();
		
		Thread.sleep(2000);
		
		setReference(reference);
		
		waitForExtJSAjaxComplete(20);
		
		setOccurrenceType(type);
		
		waitForExtJSAjaxComplete(20);
		
		setStartDate(startDate);
		
		waitForExtJSAjaxComplete(20);
		
		setStartTime(startTime);
		
		waitForExtJSAjaxComplete(20);
		
		setEndDate(endDate);
		
		waitForExtJSAjaxComplete(20);
		
		setEndTime(endTime);
		
		waitForExtJSAjaxComplete(20);
		
		setImpactElectricity(bElectricity);
		
		setImpactCombustibles(bCombustibles);
		
		setImpactWater(bWater);
		
		setDescription(description);		
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		Thread.sleep(2000);
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		clickDeleteButton();
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, reference), reference+" reference after delete is present");
		
		Reporter.log("Configuration Occurrence was succesfully deleted", true);
		
	}

	/**
	 * Test Case ID : C70413
	 * Author : SSU
	 * @throws IOException
	 */
	@Test(enabled=false,retryAnalyzer=RetryAnalyzer.class)
	public void testNormalOccupancyNotShownInOccurrenceGrid() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: 'Normal Occupancy' is not shown on the Occurrence grid :C70413 </span><br>",
				true);
		
		String locationType = "Energy object";
		String locationTypeTrunk = "Energy Object";
		String location = "slnmNonUnique";
		String reference = "C70413Ref" + getCurrentDate().substring(getCurrentDate().length()-6);
		//String occurrenceTypeClass = "Normal Occurrence";
		String type = "1preOccurrenceTypeNormal";
		String startDate = getFutureDate(0);
		String startTime = "01:00";
		String endDate = getFutureDate(1);
		String endTime = "02:00";
		Boolean bElectricity = true;
		Boolean bCombustibles = true;
		Boolean bWater = true;
		String description = reference;
		
		Boolean build122 = driver.getCurrentUrl().contains("122");
		Boolean build14 = driver.getCurrentUrl().contains("14");
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testNormalOccupancyNotShownInOccurrenceGrid");
		
		if(!build122){
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		setLocationValueTreeLookup(location);
		
		waitForExtJSAjaxComplete(25);
		
		String panelID = "";
		
		if(!build14){
			getXPanelId(locationTypeTrunk + " \"" +location + "\"");
		
			System.out.println("panelID" +panelID);
		} else{
			getXPanelId(locationType + " \"" +location + "\"");
			
			System.out.println("panelID" +panelID);
		}
		
		waitForExtJSAjaxComplete(25);
		
		changeTab(panelID, "Actions");
		
		waitForExtJSAjaxComplete(25);
		
		changeTabOccurences(panelID, "Occurrences"); 
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(reference);
		
		waitForExtJSAjaxComplete(20);
		
		setOccurrenceType(type);
		
		waitForExtJSAjaxComplete(20);
		
		setStartDate(startDate);
		
		waitForExtJSAjaxComplete(20);
		
		setStartTime(startTime);
		
		waitForExtJSAjaxComplete(20);
		
		setEndDate(endDate);
		
		waitForExtJSAjaxComplete(20);
		
		setEndTime(endTime);
		
		waitForExtJSAjaxComplete(20);
		
		setImpactElectricity(bElectricity);
		
		setImpactCombustibles(bCombustibles);
		
		setImpactWater(bWater);
		
		setDescription(description);		
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		changeTab(panelID, "Occupancy Hours");
		
		waitForExtJSAjaxComplete(25);
		
		if(build14){
			softAssert.assertTrue(verifyOccupancyHoursNotfilled("occupancy_hours_grid_labels", "1", convertDateFormat(0, Calendar.MONDAY), "1").contains("(Not occupied)"), "The saved occurrence is not displayed on the Occupancy hours grid");
		
			softAssert.assertTrue(verifyOccupancyHoursNotfilled("occupancy_hours_grid_labels", "2", convertDateFormat(1, Calendar.TUESDAY), "2").contains("(Not occupied)"), "The saved occurrence is not displayed on the Occupancy hours grid");
		} else{
			softAssert.assertTrue(verifyOccupancyHoursNotfilled("occupancy_hours_grid_labels", "1", convertDateFormat(0, Calendar.MONDAY), "1").contains("(Not Occupied)"), "The saved occurrence is not displayed on the Occupancy hours grid");
			
			softAssert.assertTrue(verifyOccupancyHoursNotfilled("occupancy_hours_grid_labels", "2", convertDateFormat(1, Calendar.TUESDAY), "2").contains("(Not Occupied)"), "The saved occurrence is not displayed on the Occupancy hours grid");
		}
			
		softAssert.assertAll();
		
		Reporter.log("Normal Occupancy is not shown on the Occurrence grid is successfully displayed", true);
		
		}
	}
}
