package test.generalweb.reservation;




import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.AbstractTestSuite;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;




public class ReservationVehicleCRUDTestSuite extends
		ReservationPageObject {
	/**
	 * Testcase ID	:	C74341,C74349,C74350,C74351,C74344,C74345
	 * author		:	Intellias
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationVehicleCreateDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74341: Book a Vehicle <br>"
				+ "Test C74349: Add/Remove additional Equipment to Vehicle Reservation  <br>"
				+ "Test C74350: Add/Remove additional Vehicle to Vehicle Reservation <br>"
				+ "Test C74351: Add/Remove additional Parking to Vehicle Reservation <br>"
				+ "Test C74344: Department is auto populated when we select Responsible field, while reserving a Vehicle <br>"
				+ "Test C74345: In Vehicle reservation, a Department value can be modified for the same company only </span><br>", true);

		Reporter.log("User reserves vehicle <br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		
		String employee = getUserLastNameOfLoggedInUser();
		String contact = "1preContactLast";
		String company = "My Company";
		String first = "otherFrist";
		String last = "otherLast";
		
		String date = "11-01-20"+random;
		String dateEd = "12-01-20"+random;
		String from = "04:00";
		String fromEd = "04:15";
		String fromWrong = "05:00";
		
		String until = "04:30";
		String numberPeople = "2";
		String responsible = getUserLastNameOfLoggedInUser();
		String category = "1preVhCtRef";
		String vehicle = "1preRsVhRef";
		String vehicle2 = "2preRsVhRef";
		String parking = "1prePrkEqRef";
		String equipment = "1preRsGnEqRef";
		String department = "AQA_DEPARTMENT";
		String wrongDepartment = "2preDepartName"; 
		String department2 = "3preDepartName";
		
		String vehicle3 = "3preRsVhRef";

		String project = "1preProjectRef";

		String projectPart = "1prePrPartRef";

		boolean isReservationDone=false;
		ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();
		  
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReservationVehicleCreateDelete");

		try{
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setRegion(region);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(30);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'orderroomsbywrapper')]/div[contains(@class,'hide')]"),"sorting is not visible");
		
		softAssert.assertFalse(driver.findElements(By.xpath("//span[contains(text(),'"+vehicle+"')]")).isEmpty(),"vehicle "+vehicle+ "is present after search");
		
		softAssert.assertFalse(driver.findElements(By.xpath("//span[contains(text(),'"+vehicle2+"')]")).isEmpty(),"vehicle "+vehicle2+ "is present after search");
		
	

		setVehicleCategory(category);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//span[contains(text(),'"+vehicle2+"')]"),"vehicle "+vehicle2+ "is not present after search");
		
		clickLaunchReservation(vehicle);
		
		waitForExtJSAjaxComplete(25);
		
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(25);
		
//	c18637	
		setProject(project);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getProject(), project,"project was selected");
		
		setProjectPart(projectPart);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getProject(), projectPart,"project part was selected");
		
		
// c18633
		softAssert.assertEquals(getDepartment(),department,"default department was autoselected");
		
// c18634		
		clickLookup("@class","mcsreservationedit", "department","Select a Department");
			
		waitForExtJSAjaxComplete(25);
				
		softAssert.assertFalse(setValueGridLookupWithFiltersWithValidation("@id", getXWindowId("Select a Department"), wrongDepartment, "Name"),"department from another company is not present");
		
	
		
		setExactValueGridLookupWithFiltersWithValidation("@id", getXWindowId("Select a Department"), department2, "Name");
		
		waitForExtJSAjaxComplete(25);
				
		clickOrderItemsTab();
		
		clickDetailOrderItem();
		
// c18631
		clickLookup("@class","x-panel", "resource","Select a Vehicle");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertFalse(setValueGridLookupWithFiltersWithValidation("@id", getXWindowId("Select a Vehicle"), vehicle3, "Reference"),"vehicle from another region is not present");
		
		setExactValueGridLookupWithFiltersWithValidation("@id", getXWindowId("Select a Vehicle"), vehicle, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		
		//c18638
		clickOrderItemsTab();
		
		clickAddOrderItemEquipment();
		
		addOrderItem(equipment);
		
		softAssert.assertTrue(orderItemExist(equipment), "order Item Equipment Exist ");
		
		waitForExtJSAjaxComplete(25);
		
		removeOrderItem(equipment);
		
		softAssert.assertFalse(orderItemExist(equipment), "order Item Equipment not Exist ");
		
		
		//c18639
		clickOrderItemsTab();
				
		clickAddOrderItemVehicle();
				
		addOrderItem(vehicle2);
				
		softAssert.assertTrue(orderItemExist(vehicle2), "order Item Vehicle Exist ");
				
		waitForExtJSAjaxComplete(25);
		
		removeOrderItem(vehicle2);
				
		softAssert.assertFalse(orderItemExist(vehicle2), "order Item Vehicle not Exist ");
				
		
		
		//c18640
		clickOrderItemsTab();
						
		clickAddOrderItemParking();
						
		addOrderItem(parking);
						
		softAssert.assertTrue(orderItemExist(parking), "order Item Parking Exist ");
						
		removeOrderItem(parking);
						
		softAssert.assertFalse(orderItemExist(parking), "order Item Parking not Exist ");
								
		clickParticipantTab();
		
		setTotalParticipants(numberPeople);
		
		addParticipantEmployee(employee);
		
		addParticipantContact(contact);
		
		addOtherPerson(first, last);
		
		softAssert.assertEquals(getTotalParticipants(),"3","number of participants automatically increased");
		
		waitForExtJSAjaxComplete(25);
		
		clickRemoveParticipant();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getTotalParticipants(),"3","number of participants does not decrease when removed");
		
		Grid.checkRowInGriByTextValueAndClick(driver, company);
		
		clickRemoveParticipant();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getTotalParticipants(),"3","number of participants does not decrease when removed");

		clickVehicleTab();
		
		waitForExtJSAjaxComplete(25);
		
		setVehicleCategory("");
		
		clickVehicleTab();
		
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(vehicle2);
		
		waitForExtJSAjaxComplete(25);
				
		clickParkingTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(parking);
		
		waitForExtJSAjaxComplete(25);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(equipment);
		
		waitForExtJSAjaxComplete(25);
		
		
		clickGeneralTab();
		
//c18632
				
		setTimeFromReservationPanel(fromWrong);
				
		clickConfirmReservation();
		
		isReservationDone=true;
				
		waitForExtJSAjaxComplete(25);
				
		clickOnDialogButton("OK");
				
		waitForExtJSAjaxComplete(25);
				
		setTimeFromReservationPanel(from);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(25);
		
		navigateToMainPage();
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		
		  myReserv.setRegionMyReservation(region);
		  
		  waitForExtJSAjaxComplete(20);
		  
		  myReserv.setFutureDateMyReservation(date);
		  
		  waitForExtJSAjaxComplete(20);
		  
		  myReserv.clickSearchButton();
		  
		  waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date+" "+from+"|"+parking,"@class", "grid3"),"parking is reserved with vehicle");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date+" "+from+"|"+vehicle2,"@class", "grid3"),"vehicle is reserved with vehicle");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date+" "+from+"|"+equipment,"@class", "grid3"),"equipment is reserved with vehicle");
		McsElement.getElementByXpath(driver,"//tbody[contains(@class,'x-btn-icon-large-left')]//button[contains(@class,'icon-search-large')]").click();
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		checkRowInGriByTextValueAndClick(date+" "+from,vehicle);
		
		waitForExtJSAjaxComplete(25);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(25);
		
		clickOrderItemsTab();
		
		clickDetailOrderItem();
		
//c18641
		softAssert.assertTrue(McsElement.
						getElementByXpath(driver, "//legend//span[contains(text(),'From, Until')]/../..//td[contains(@class,'ux-datetime-date')]//input[contains(@class,'x-form-readonly')]").
						getAttribute("value").contains(date),"disabled from date is ok");
				

		softAssert.assertTrue(McsElement.
						getElementByXpath(driver, "(//legend//span[contains(text(),'From, Until')]/../..//td[contains(@class,'ux-datetime-date')]//input[contains(@class,'x-form-readonly')])[last()]").
						getAttribute("value").contains(date),"disabled until date is ok");


		softAssert.assertTrue(McsElement.
						getElementByXpath(driver, "//legend//span[contains(text(),'From, Until')]/../..//td[contains(@class,'ux-datetime-time')]//input[contains(@class,'x-form-readonly')]").
						getAttribute("value").contains(from),"disabled from time is ok");

		softAssert.assertTrue(McsElement.
						getElementByXpath(driver, "(//legend//span[contains(text(),'From, Until')]/../..//td[contains(@class,'ux-datetime-time')]//input[contains(@class,'x-form-readonly')])[last()]").
						getAttribute("value").contains(until),"disabled until time is ok");

				
		javaScriptClick("//label[contains(text(),'Start and stop times can be changed on the General page')]");
				
		waitForExtJSAjaxComplete(20);
				
		softAssert.assertTrue(!McsElement.
						getElementByXpath(driver, "(//input[contains(@name,'until')]//..//td[contains(@class,'ux-datetime-time')]//input/..)").
						getAttribute("class").contains("disabled"),"not disabled until time is ok");
				
				
		setDateFromReservationPanel(dateEd);
		
		waitForExtJSAjaxComplete(20);
				
		setTimeFromReservationPanel(fromEd);
		
		waitForExtJSAjaxComplete(25);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(25);
	}
		finally{
			if(isReservationDone=true){
		
		navigateToMainPage();
		
		waitForExtJSAjaxComplete(20);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(30);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(30);
		
		waitForExtJSAjaxComplete(20);
		  
		myReserv.setRegionMyReservation(region);
		  
		waitForExtJSAjaxComplete(10);
		  
		myReserv.setFutureDateMyReservation(dateEd);
		  
		waitForExtJSAjaxComplete(10);
		  
		myReserv.clickSearchButton();
		  
		waitForExtJSAjaxComplete(25);
		
		checkRowInGriByTextValueAndClick(dateEd+" "+fromEd,vehicle);
		
		waitForExtJSAjaxComplete(25);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(30);
		
		clickGeneralTab();		
		
		waitForExtJSAjaxComplete(20);
		
		clickCancelReservation();
		
		waitForExtJSAjaxComplete(25);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(25);
		
		navigateToMainPage();
		
		waitForExtJSAjaxComplete(20);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		  
		myReserv.setRegionMyReservation(region);
		  
		waitForExtJSAjaxComplete(25);
		  
		myReserv.setFutureDateMyReservation(date);
		  
		waitForExtJSAjaxComplete(10);
		  
		myReserv.clickSearchButton();
		  
		waitForExtJSAjaxComplete(25);
	
			checkRowInGriByTextValueAndClick(date+" "+from,vehicle);
			
			waitForExtJSAjaxComplete(25);
			
			clickViewCancel();
			
			waitForExtJSAjaxComplete(25);
			
			clickCancelReservation();
			
			waitForExtJSAjaxComplete(25);
			
			clickOnDialogButton("Yes");
			
			waitForExtJSAjaxComplete(25);
		
			navigateToMainPage();
			
			waitForExtJSAjaxComplete(25);
			
			expandAdministration();
			
			waitForExtJSAjaxComplete(20);

			expandSubMainMenu("Reservation");
			
			waitForExtJSAjaxComplete(20);
			
			waitAndClick(XPATH_NEWRESERVATIONS);
			
			waitForExtJSAjaxComplete(20);
			
			waitAndClick(XPATH_MYRESERVATIONS);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			  
			myReserv.setRegionMyReservation(region);
			  
			waitForExtJSAjaxComplete(15);
			  
			myReserv.setFutureDateMyReservation(date);
			  
			waitForExtJSAjaxComplete(15);
			  
			myReserv.clickSearchButton();
			  
			waitForExtJSAjaxComplete(25);
			
			AbstractTestSuite.getScreenshot("testReservationVehicleCreateDelete");
			
			Reporter.log("after navigation to main page");
			
			softAssert.assertTrue(isRowInGridAbsent(vehicle, date+" "+from),"Vehicle reservation not be canceled");
		}else
		{
			System.out.println("Reservation is not done");
		}
		}
		softAssert.assertTrue(isRowInGridAbsent(vehicle, date+" "+from),"Vehicle reservation can be canceled");
		
		softAssert.assertAll();
		
		Reporter.log("Vehicle was reserved and canceled", true);
	}

	/**
	 * Testcase ID	:	C74363,C74364,C117540
	 * author		:	Intellias
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationTentativeVehicleCreateDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74363: Tentative Vehicle Reservation also generate cost items <br>"
				+ "Test C74364: More than one Tentative Vehicle Reservation can not be made for the same time period"
				+ "C117540: Non-Room Reservations may only contain Reservation Lines of same Type (MYM-26574)  </span><br>", true);

		Reporter.log("User reserves tentative vehicle <br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
	
		
		String date = "3-01-20"+random;

		String from = "09:00";
		
		String until = "09:30";

		String responsible = getUserLastNameOfLoggedInUser();

		String vehicle = "aqavehicleref";
		
		String vehicalCatg = "1preVhCtRef";
		
		boolean isReservationDone=false;
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testTentativeReservationVehicleCreateDelete");

		try{
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(30);
		
		setRegion(region);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
		
		waitForExtJSAjaxComplete(5);
		
		String reservDate=getDate();
		
		waitForExtJSAjaxComplete(20);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(30);
		
		clickLaunchReservation(vehicle);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickTentative();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickOrderItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickOrderItemsTab();
		
		this.waitForMaskDisappear();
		
		//C117540: Non-Room Reservations may only contain Reservation Lines of same Type (MYM-26574) 
		clickAddRemoveOrderItem();
		
		this.waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isAddAndRemoveButtonDisabled("Add Room"), "Add Room button is disabled ");

		softAssert.assertTrue(isAddAndRemoveButtonEnabled("Add Equipment"), "Add Equipment button is enabled");

		softAssert.assertTrue(isAddAndRemoveButtonDisabled("Add Service"), "Add Service button is disabled");

		softAssert.assertTrue(isAddAndRemoveButtonEnabled("Add Parking"), "Add Parking button is enabled");

		softAssert.assertTrue(isAddAndRemoveButtonEnabled("Add Vehicle"), "Add Vehicle button is enabled");

		softAssert.assertEquals(isReservableTabVisibl("parking"), isAddAndRemoveButtonEnabled("Add Parking"),"Parking button in Order item pane is enabled, so it is displayed in the 'Enter your Parameters section'");

		softAssert.assertEquals(isReservableTabVisibl("vehicle"), isAddAndRemoveButtonEnabled("Add Vehicle"),"Vehicle button in Order item pane is enabled, so it is displayed in the 'Enter your Parameters section'");

		softAssert.assertEquals(isReservableTabsNotVisible("room"), isAddAndRemoveButtonDisabled("Add Room"),"Room button in Order item pane is disabled, so it is not displayed in the 'Enter your Parameters section' ");

		softAssert.assertEquals(isReservableTabsNotVisible("service"), isAddAndRemoveButtonDisabled("Add Service"),"Service button in Order item pane is disabled, so it is not displayed  in the 'Enter your Parameters section' ");

		softAssert.assertEquals(isReservableTabVisibl("equipment"), isAddAndRemoveButtonEnabled("Add Equipment"),"Equipment button in Order item pane is enabled, so it is displayed in the 'Enter your Parameters section' ");

		waitForExtJSAjaxComplete(20);
		
		clickSummaryTab();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(getSummaryTotalCost().contains("2.02 EUR"),"tentative vehicle generates costs");
		
		softAssert.assertTrue(getSummaryTitle().contains("2.02 EUR"),"tentative vehicle generates costs summary");
		
		clickConfirmReservation();
		
		isReservationDone=true;
		
		waitForExtJSAjaxComplete(5);
	
		navigateToMainPage();
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickVehicleTab();
		
		setRegion(region);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//span[contains(text(),'"+vehicle+"')]"),"tentative vehicle "+vehicle+ "is not present after search");
		
		}
		finally{
			deleteReservationFromMyReservation(date,from,vehicle,isReservationDone);
			}
		
		
		softAssert.assertAll();
		
		Reporter.log("Tentative vehicle was succesfully reserved", true);
	}

	/**
	 * Testcase ID	:	C74357
	 * author		:	Intellias
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testAdditionalCostVehicle() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74357: In Vehicle Reservation, cost for Reservable Vehicle and additional cost for added Equipment, Vehicles & Parking are displayed in Summary </span><br>", true);

		Reporter.log("User reserves additional vehicle cost<br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "10-01-20"+random;

		String from = "14:00";
		
		String until = "14:30";

		String responsible = getUserLastNameOfLoggedInUser();

		String vehicle = "1preRsVhRef";
		
		String equipment = "preEqDefRef";
		
		String vehicle2 = "preDefVhRef";
		
		String parking = "prePrkDefRef";
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testAdditionalCostReservationVehicle");

		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		setRegion(region);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(vehicle);
		
		waitForExtJSAjaxComplete(25);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(5);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(vehicle2);
		
		waitForExtJSAjaxComplete(25);

		clickParkingTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(parking);
		
		waitForExtJSAjaxComplete(25);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(equipment);
		
		waitForExtJSAjaxComplete(25);

		clickSummaryTab();
		
		softAssert.assertTrue(getSummaryTotalCost().contains("8.08 EUR"),"additional costs for room are ok");
		
		softAssert.assertTrue(getSummaryTitle().contains("8.08 EUR"),"summary title is ok");
		
		softAssert.assertTrue(getSummaryItemCost(vehicle).contains("2.02 EUR"),"vehicle cost is ok");
		
		softAssert.assertTrue(getSummaryItemCost(vehicle2).contains("2.02 EUR"),"vehicle cost is ok");
		
		softAssert.assertTrue(getSummaryItemCost(parking).contains("2.02 EUR"),"parking cost is ok");

		softAssert.assertTrue(getSummaryItemCost(equipment).contains("2.02 EUR"),"equipment cost is ok");

		softAssert.assertAll();
		
		Reporter.log("Additional cost Vehicle was succesfully reserved", true);
	}
	/**
	 * Testcase ID	:	C74361
	 * author		:	Intellias
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testRecurrenceVehicle() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74361:  Book a Vehicle with Recurrence for available slots </span><br>", true);

		Reporter.log("User reserves service recurrence<br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		

		String dateRec = "10-01-20"+random;
		String dateRec2 = "12-01-20"+random;
		
		String date = "11-01-20"+random;
		String from = "19:00";
		String until = "19:30";
		String responsible = getUserLastNameOfLoggedInUser();
//		String service = "1preSrvRef";
		String vehicle = "1preRsVhRef";
//		String parking = "1prePrkEqRef";
		String equipment  = "1preRsGnEqRef";
		String vehicle2 = "2preRsVhRef";
		
		boolean isReservationDone=false;
		
		
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testRecurrenceService");
		try{
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setRegion(region);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
		
		waitForExtJSAjaxComplete(10);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(5);
				
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		
		clickLaunchReservation(vehicle);
		
		waitForExtJSAjaxComplete(25);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(10);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(25);
		
		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(25);
		
		//clickVehicleTab();
		
		setRegion(region);
		
		waitForExtJSAjaxComplete(20);
		
		setDate(dateRec);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
		
		waitForExtJSAjaxComplete(10);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(vehicle);
		
		waitForExtJSAjaxComplete(25);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(10);
				
		clickGeneralTab();
		
		clickGeneralRecurence();
		
		clickRecurenceEndAfter();
		
		setRecurenceEndAfter("3");
		
		clickRecurenceDailyTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickRecurenceDailyEvery();
		
		setRecurenceDailyInterval("1");
		
		clickRecurenceCalculateDates();
		
		clickCheckAvailability();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-window')]//div[contains(@class,'x-grid3')]//div[contains(text(),'Unavailable:')]"), "unavailable period");
		
		softAssert.assertTrue(driver.findElements(By.xpath("//div[contains(@class,'x-window')]//div[contains(@class,'x-grid3')]//div[contains(text(),'Available')]")).size()==2, "2 available period");
		
		clickSetRecurence();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(vehicle2);
		
		waitForExtJSAjaxComplete(25);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(equipment);
		
		waitForExtJSAjaxComplete(25);
		
		clickConfirmReservation();		
		
		isReservationDone=true;
		
		waitForExtJSAjaxComplete(25);
		
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-window-dlg')]//span[contains(text(),'Unable to save')]"), "unable to save occurence");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
    	waitForExtJSAjaxComplete(25);
		
		clickGeneralRecurence();
		
		//TO DO: Overlaped dates are removed from the recurence window
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//div[contains(@class,'x-window')]//div[contains(@class,'x-grid3')]//div[contains(text(),'"+date+"')]"), "overlaped date is absent");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-window')]//div[contains(@class,'x-grid3')]//div[contains(text(),'"+dateRec+"')]"), "reccurence date is present");
		
		closeRecurrenceWindow();
		
	}
	finally{
		if(isReservationDone=true){
		navigateToMainPage();
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();
		  
		  myReserv.setRegionMyReservation(region);
		  
		  waitForExtJSAjaxComplete(20);
		  
		  myReserv.setFutureDateMyReservation(date);
		  
		  waitForExtJSAjaxComplete(10);
		  
		  myReserv.clickSearchButton();
		  
		  waitForExtJSAjaxComplete(20);
		
		
		waitForExtJSAjaxComplete(25);
		
		checkRowInGriByTextValueAndClick(date+" "+from,vehicle);
		
		waitForExtJSAjaxComplete(25);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(25);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickCancelReservation();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		//ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();
		  
		myReserv.setRegionMyReservation(region);
		  
		waitForExtJSAjaxComplete(15);
		  
		myReserv.setFutureDateMyReservation(dateRec);
		  
		waitForExtJSAjaxComplete(10);
		  
		myReserv.clickSearchButton();
		  
		waitForExtJSAjaxComplete(25);
		
		checkRowInGriByTextValueAndClick(dateRec+" "+from,vehicle);
		
		waitForExtJSAjaxComplete(25);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(25);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickCancelReservation();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		//ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();
		  
		myReserv.setRegionMyReservation(region);
		  
		waitForExtJSAjaxComplete(15);
		  
		myReserv.setFutureDateMyReservation(dateRec2);
		  
		waitForExtJSAjaxComplete(10);
		  
		myReserv.clickSearchButton();
		  
		waitForExtJSAjaxComplete(25);
		
		checkRowInGriByTextValueAndClick(dateRec2+" "+from,vehicle);
		
		waitForExtJSAjaxComplete(25);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(25);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickCancelReservation();
		
		waitForExtJSAjaxComplete(10);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(25);
		}
		else{
			System.out.println("Reservation is not done");
		}
	}
		
		softAssert.assertAll();
		
		Reporter.log("Recurence vehicle was succesfully reserved", true);
		
	}
	
	/**
	 * Testcase ID	:	C74355
	 * author		:	Intellias
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationVehicleContactPerson() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74355: Contact & Other Participant Details are shown in Vehicle Reservation and Bell symbol displaying  </span><br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "17-01-20"+random;
		String from = "17:00";
		String until = "17:30";
		
		
		String vehicle = "1preRsVhRef";
		
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReservationContactVehicle");

		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setRegion(region);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(10);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(vehicle);
		
		waitForExtJSAjaxComplete(25);
		
		clickParticipantTab();
		
		waitForExtJSAjaxComplete(25);
		
		addParticipantContact("1preContactLast");
		
		waitForExtJSAjaxComplete(25);
				
		McsElement.getElementByXpath(driver, "//img[contains(@src,'announce')]").click();
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//img[contains(@src,'announce')]"),"bell near participant is present");
		
		clickAnnounceCheckbox();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//img[contains(@src,'announce')]"),"no bell near participant is present");
		
		addOtherPerson("firsnt", "lasnt");
		
		waitForExtJSAjaxComplete(10);
		
		selectParticipant("firsnt");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertAll();
		
		Reporter.log("Vehicle contact person was added", true);
	}

	/**
	 * Testcase ID	:	C74365
	 * author		:	Intellias
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationConfidentialVehicleCreateDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74365: Confidential Vehicle Reservation </span><br>", true);

		Reporter.log("User reserves confidential vehicle <br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
	
		
		String date = "17-09-20"+random;

		String from = "17:30";
		
		String until = "18:00";

		String responsible =getUserLastNameOfLoggedInUser();

		String vehicle = "1preRsVhRef";
		
		String equipment = "preEqDefRef";
		
		String vehicle2 = "preDefVhRef";
		
		String parking = "prePrkDefRef";

		boolean isReservationDone=false;
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testConfidentialReservationVehicleCreateDelete");
		try{
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickVehicleTab();
		
		setRegion(region);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(vehicle);
		
		waitForExtJSAjaxComplete(25);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(5);
		
		//clickConfidential();;
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(equipment);
		
		waitForExtJSAjaxComplete(25);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(vehicle2);
		
		waitForExtJSAjaxComplete(25);
		
		clickParkingTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(parking);
		
		waitForExtJSAjaxComplete(25);
		
		clickGeneralTab();
		
		clickConfirmReservation();
		
		isReservationDone=true;
		
		waitForExtJSAjaxComplete(25);
		}
		finally{
				deleteReservationFromMyReservation(date,from,vehicle,isReservationDone);
				}
		softAssert.assertAll();
		
		Reporter.log("Confidential vehicle was succesfully reserved", true);
	}

	 /**
	 * Testcase ID	:	C74354
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testVerifyAlertMsgForPrepareCleanupTimeInVehicleReservation() throws Exception{
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C74354: Alert message for Maximum Prepare/Cleanup time is displayed in Vehicle Reservation </span><br>", true);

		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		 String date = this.getFutureDate(random);
		String from = "00:30";
		String until = "02:00";
		String prepareTime="00:30";
		String cleanupTime="01:30";
		String updatePrepareTime="01:00";
		String updateCleanUpTime = "02:30";
		
		String responsible = getUserLastNameOfLoggedInUser();
		String vehicle1="1aqaVhRef";
		String vehicle2="2aqaVhRef";
		
		boolean isReservationDone=false;
		String warningMsg="With the Preparation time included, the Reservation does not fit in one day. Please decrease the Preparation Time or increase the From time.\n";
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testVerifyAlertMsgForPrepareCleanupTimeInVehicleReservation");
		
	try{
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setRegion(region);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
        
		waitForExtJSAjaxComplete(10);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(vehicle2);
		
		waitForExtJSAjaxComplete(25);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getPrepareTimeGeneral(), prepareTime,"prepare time is displayed in general");
		
		softAssert.assertEquals(getCleanUpTimeGeneral(), cleanupTime,"clean up time is displayed in general");
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(10);
		
		clickOrderItemsTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickAddOrderItemVehicle();
		
		addOrderItem(vehicle1);
		
		waitForExtJSAjaxComplete(25);
		
		Boolean status=isItemAddedInOrderItemsPane("vehicle",vehicle2);
		
		softAssert.assertTrue(status,"Item is added");
		
		status=isItemAddedInOrderItemsPane("vehicle",vehicle1);
		
		softAssert.assertTrue(status,"Item is added");
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(15);
		
		softAssert.assertEquals(getPrepareTimeGeneral(), updatePrepareTime,"prepare time is displayed in general");
		
		softAssert.assertEquals(getCleanUpTimeGeneral(), updateCleanUpTime,"clean up time is displayed in general");
		
		clickConfirmReservation();
		
		isReservationDone=true;
		
		waitForExtJSAjaxComplete(25);
		
		String text=getWarningDialogTextMsg();
		
		System.out.println(text);
		
		System.out.println(warningMsg);
		softAssert.assertTrue(text.contains(warningMsg),"Error message is displayed for maximun prepare time specified to the added equipment");
	
		waitForExtJSAjaxComplete(25);
		
		clickOnDialogButton("OK");
		
	}
	finally{
		deleteReservationFromMyReservation(date, from, vehicle1, isReservationDone);
	}
		
		softAssert.assertAll();
		
		Reporter.log("Alert message is displayed Maximum Prepare/Cleanup time in vehicle Reservation<br>", true); 

	}
	
	
	/**
	 * Testcase ID	:	C74367
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testUnavailableVehicleReservationCanNotBeBooked() throws Exception{
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C74367:Reservable vehicle for the specified Unavailable Periods can not be booked </span><br>", true);

		
		String region = "1preRgRef";
		
		//int random = (int)(Math.random() * 10)+18;
		
		String date = "09-07-2035";
		String from = "01:00";
		String until = "02:00";
		String vehicle="VehicleUnavailblePeriodRef";
		String regions = "All Regions";
		String unAvailableReservationTooTipMsg="The Resource is not available at that moment.";
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testUnavailableVehicleReservationCanNotBeBooked");
		ReservationsCalendarNewPageObject calendarPageObj=new ReservationsCalendarNewPageObject();
	
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setRegion(region);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
        
		waitForExtJSAjaxComplete(10);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		Boolean status=isDisplayLaunchReservation(vehicle);
		
		softAssert.assertFalse(status, "Vehicle item is not displayed");
	
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_CALENDAR);
		
		waitForExtJSAjaxComplete(20);
		
		clickCalenderTab();
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		calendarPageObj.clickVehiclesTabInCalendarTab();
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		calendarPageObj.setRegionInCalendarTab(regions);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		calendarPageObj.clickOnCalendarViews("View Week");
		
		waitForExtJSAjaxComplete(30);
		
		calendarPageObj.clickOnCalendarViews("View 24/7");
		
		waitForExtJSAjaxComplete(20);
		
		calendarPageObj.setDateViaDatePicker(date);
		
		waitForMaskDisappear();
		
		calendarPageObj.filterItemByName(vehicle);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
				
		softAssert.assertTrue(McsElement.isElementPresent(driver,calendarPageObj.CALENDAR_RESOURCE_WIN_XPATH+"//div[text()='"+vehicle+"']"), "Reservable vehicle is displayed");
		
		String text=calendarPageObj.getToolTipTextOfUnAvailableReservation(vehicle);
		
		softAssert.assertTrue(text.contains(unAvailableReservationTooTipMsg),"Tool tip message is displayed for unavailable reservation");
		
		softAssert.assertAll();
		
		Reporter.log("Reservable vehicle can not be booked for the specified Unavailable Periods <br>", true); 

	}

	
	
	/**
	 * Testcase ID	:	C74360/C122853
	 * Author		:	vpcc
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testVerifyCostCenterInVehicleBooking(){
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C74360/C122853:Verify that Cost Center value is correctly updated in Vehicle Reservations when changing the value from Cost Center Combo box</span><br>", true);

		
		String region = "1preRgRef";
		//String responsible=getUserLastNameOfLoggedInUser();
		String responsible = "SELENIUM";
		int random = (int)(Math.random() * 10)+18;
		
		String date = this.getFutureDate(random);
		
		String from = "13:00";
		String until = "14:00";
		String vehicle = "1preRmRef";
		String project="1preProjectRef";
		String costCenterMethod1=  "From Responsible";
		String costCenterMethod2="From Department";
		String costCenterMethod3="From Project";
		String costCenterMethod4="Other";
		String costCenter = "myMCS Default Cost Center";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testVerifyCostCenterInVehicleBooking");

		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setRegion(region);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(10);
		
		clickRoomTab();
				
		waitForExtJSAjaxComplete(5);

		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(vehicle);
		
		waitForExtJSAjaxComplete(25);
		
		setResponsible(responsible);

		waitForExtJSAjaxComplete(20);
		
		setProject(project);
		
		clickTabInReservationDetailsSection("Financial");
		
		waitForExtJSAjaxComplete(25);
		
		this.setCostCenterMethodInFinancialsTab(costCenterMethod1);
		
		waitForExtJSAjaxComplete(25);
		
		String costCenterFromResponsible = this.getCostCenter();
		
		softAssert.assertEquals(costCenterFromResponsible, costCenter,"Cost Center lookup value is automatically filled with the Cost Center which is linked to the Responsible Person.");
		
		this.setCostCenterMethodInFinancialsTab(costCenterMethod2);
		
		waitForExtJSAjaxComplete(25);
		
		String costCenterFromDept = this.getCostCenter();
		
		softAssert.assertEquals(costCenterFromDept, costCenter,"Cost Center lookup value is automatically filled with the Default Cost Center which is linked to the Department.");
		
		this.setCostCenterMethodInFinancialsTab(costCenterMethod3);
		
		waitForExtJSAjaxComplete(25);
		
		String costCenterFromProject = this.getCostCenter();
		
		softAssert.assertEquals(costCenterFromProject, costCenter,"Cost Center lookup value is automatically filled with the Cost Center which is linked to the Project.");
		
		
		this.setCostCenterMethodInFinancialsTab(costCenterMethod4);
		
		waitForExtJSAjaxComplete(25);
		
		List<String> costCentersList = new ArrayList<String>();
		costCentersList.add(costCenter);
		
		softAssert.assertEquals(costCenterFromProject, costCenter,"Cost Center(s) linked to the Fiscal Entity that is assigned to the responsible person are only displayed.");
		
		softAssert.assertAll();
		 
		Reporter.log("Cost Center values are correctly updated<br>", true);
		
		
	}
	
	/**
	  * Testcase ID : C74359 
	  * Author  : ssa
	  */
	 @Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	 public void testVehicleResrvSummeryAndPrintTabs(){
	  
	  Reporter.log("<span style='font-weight:bold;color:#000033'> "

		 +"test:C74359:C119476: Summary and Printing are not available, if required field is not filled out for Vehicle Reservation", true);

	  
	  String region = "1preRgRef";
	  
	  int random = (int)(Math.random() * 10)+18;
	  
	  String date = this.getFutureDate(random);
	  
	  String from = "01:00";
	  String until = "01:30";
	  
	  String vehicle = "1preRsVhRef";
	  String summaryTabText= "A summary is not available for the currently entered data\n"
			  +"\n"+
			  "Please fill in the Reservation Responsible.\n"+
			  "\n"+"Please make sure you have filled out all mandatory fields.";
	 
	  
	  String  printIconWarningMsg ="Printing requires all changes to be saved first";
	  
	  SoftAssert softAssert = new SoftAssert();
	  
	  softAssert.setMethodName("testVehicleResrvSummeryAndPrintTabs");

	  expandAdministration();
	  
	  waitForExtJSAjaxComplete(20);

	  expandSubMainMenu("Reservation");
	  
	  waitForExtJSAjaxComplete(20);
	  
	  waitAndClick(XPATH_NEWRESERVATIONS);
	  
	  waitForExtJSAjaxComplete(20);
	  
	  waitForExtJSAjaxComplete(20);
	  
	  setRegion(region);
	  
	  setDate(date);
	  
	  setTimeFrom(from);
	  
	  setTimeUntil(until);
	    
	  waitForExtJSAjaxComplete(10);
	  
	  setTimeUntil(until);
	    
	  waitForExtJSAjaxComplete(15);
	  
	  clickVehicleTab();
	  
	  waitForExtJSAjaxComplete(20);

	  clickSearch();
	  
	  waitForExtJSAjaxComplete(20);
	  
	  
	  clickLaunchReservation(vehicle);
	
	  waitForExtJSAjaxComplete(20);
	  
	  clickSummaryTab();
	  
	  waitForExtJSAjaxComplete(20);
	  
	  softAssert.assertEquals(getSummaryTabText(),summaryTabText,"'A summary is not available for the currently entered data' is display with a message to fill out required fields.");
	  
	  waitForExtJSAjaxComplete(20);
	  
	  clickReportIconInSummarySection();
	  
	  waitForExtJSAjaxComplete(20);
	  
	  softAssert.assertTrue(verifyWarningDialogTextMessage(printIconWarningMsg),"'Printing requires all changes to be saved first' alert message is displayed.");
	  
	  this.clickOnDialogButton("OK");
	  
	  softAssert.assertAll();

	  Reporter.log("Summary pane is display with a message to fill out required fields."
			  + "Alert message is displayed for print icon <br>", true);
	 }  

	 /**
	  * Testcase ID : C11806
	  * Author      : MMA
	  * @throws IOException
	  */
	 @Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	 public void testVehicleBookingPreparationTimeAndCleanupNotConsidered()throws IOException{
		 Reporter.log("<span style='font-weight:bold;color:#000033'> "
				 + "C11806: Preparation and Cleanup time should not be considered while calculating Total Cost in Vehicle reservation", true);

		 int random = (int)(Math.random() * 10)+24;
		 String date = this.getFutureDate(random);
		 String region = "1preRgRef";
		 String from = "18:00";
		 String until = "19:00";
		 String vehicleResv = "aqavehicleref";
		 String prepTime = "00:30";
		 String cleanupTime = "00:30";
		 String responsible=getUserLastNameOfLoggedInUser();

		 SoftAssert softAssert = new SoftAssert();
		 softAssert.setMethodName("testVehicleBookingPreparationTimeAndCleanupNotConsidered");

		 expandAdministration();
		 waitForExtJSAjaxComplete(20);

		 expandSubMainMenu("Reservation");
		 waitForExtJSAjaxComplete(20);

		 waitAndClick(XPATH_NEWRESERVATIONS);
		 waitForExtJSAjaxComplete(20);
		 waitForExtJSAjaxComplete(20);

		 setRegion(region);
		 setDate(date);
		 setTimeFrom(from);
		 setTimeUntil(until);
		 waitForExtJSAjaxComplete(20);

		 clickVehicleTab();
		 waitForExtJSAjaxComplete(5);

		 clickSearch();
		 waitForExtJSAjaxComplete(20);
		 waitForExtJSAjaxComplete(20);

		 clickLaunchReservation(vehicleResv);
		 waitForExtJSAjaxComplete(10);

		 setTimePrepareFromReservationPanel(prepTime);
		 waitForExtJSAjaxComplete(10);

		 setTimeCleanupFromReservationPanel(cleanupTime);
		 waitForExtJSAjaxComplete(10);

		 setResponsible(responsible);
		 waitForExtJSAjaxComplete(10);

		 clickSummaryTab();
		 waitForExtJSAjaxComplete(20);

		 String getSummaryText = getItemDetailsFromReservationSummary(vehicleResv);
		 softAssert.assertTrue( getSummaryText.contains("1 h x 4.00 EUR"),"Total cost is calculated without considering Prepare/Cleanup time");
		 softAssert.assertTrue(getSummaryTitle().contains("4.04 EUR"),"summary title is ok before adding additional Equipment, Vehicles & Parking");
	 }
}
