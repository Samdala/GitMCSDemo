package test.generalweb.reservation;






import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import test.generalweb.helpdesk.HelpDeskFrontOfficePageObject;
import test.generalweb.rightvisibility.AdministrationPageObject;




public class ReservationParkingCRUDTestSuite extends
		ReservationPageObject {

	/**
	 * Testcase ID	:	C74371,C74383,C74384,C74385,C74374
	 * Author		:	Intellias
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testParkingRoomCreateDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74371: Book a Parking <br>"
				+ "Test C74383: Add/Remove additional Equipment to Parking Reservation <br>"
				+ "Test C74384: Add/Remove additional Vehicle to Parking Reservation <br>"
				+ "Test C74385: Add/Remove additional Parking to Parking Reservation <br>"
				+ "Test C74374: Department is auto populated when we select Responsible field, while reserving a Parking </span> <br>", true);

		Reporter.log("User reserves parking <br>", true);


		String region = "1preRgRef";

		int random = (int)(Math.random() * 10)+18;

		String employee = getUserLastNameOfLoggedInUser();
		String contact = "1preContactLast";
		String company = "My Company";
		String first = "otherFrist";
		String last = "otherLast";

		String date = "11-01-20"+random;
		String dateEd = "12-01-20"+random;
		String from = "02:00";
		String fromEd = "02:15";
		String until = "02:30";
		String numberPeople = "2";
		String responsible = getUserLastNameOfLoggedInUser();
		String parking = "1prePrkEqRef";
		String parking2 = "2prePrkResRef";
		//		String service = "1preSrvRef";
		String equipment = "1preRsGnEqRef";
		String vehicle = "1preRsVhRef";
		String department = "AQA_DEPARTMENT";

		String wrongDepartment = "2preDepartName"; 
		String department2 = "3preDepartName";

		String project = "1preProjectRef";

		String projectPart = "1prePrPartRef";

		String fromWrong = "03:00";

		String parking3 = "3prePrkResRef";

		boolean isReservationDone=false;

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testParkingCreateDelete");
		ReservationMyReservationsPageObject myReserv=new ReservationMyReservationsPageObject();
		String category = "1prePrkRef";
		
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

		waitForExtJSAjaxComplete(5);

		clickParkingTab();

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'orderroomsbywrapper')]/div[contains(@class,'hide')]"),"sorting is not visible");

		softAssert.assertFalse(driver.findElements(By.xpath("//span[contains(text(),'"+parking+"')]")).isEmpty(),"parking "+parking+ "is present after search");

		softAssert.assertFalse(driver.findElements(By.xpath("//span[contains(text(),'"+parking2+"')]")).isEmpty(),"parking "+parking2+ "is present after search");

		waitForExtJSAjaxComplete(5);

		setParkingCategory(category);

		clickParkingTab();

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(30);

		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//span[contains(text(),'"+parking2+"')]"),"parking "+parking2+ "is not present after search");

		clickLaunchReservation(parking);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setResponsible(responsible);

		waitForExtJSAjaxComplete(15);

		//		c18667	
		setProject(project);

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getProject(), project,"project was selected");

		setProjectPart(projectPart);

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getProject(), projectPart,"project part was selected");

		// c18663
		softAssert.assertEquals(getDepartment(),department,"default department was autoselected");


		// c18664		
		clickLookup("@class","mcsreservationedit", "department","Select a Department");

		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(setValueGridLookupWithFiltersWithValidation("@id", getXWindowId("Select a Department"), wrongDepartment, "Name"),"department from another company is not present");

		setExactValueGridLookupWithFiltersWithValidation("@id", getXWindowId("Select a Department"), department2, "Name");

		waitForExtJSAjaxComplete(20);

		clickOrderItemsTab();

		clickDetailOrderItem();

		// c18661
		clickLookup("@class","x-panel", "resource","Select a Parking Space");

		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(setValueGridLookupWithFiltersWithValidation("@id", getXWindowId("Select a Parking Space"), parking3, "Reference"),"parking from another region is not present");

		setExactValueGridLookupWithFiltersWithValidation("@id", getXWindowId("Select a Parking Space"), parking, "Reference");

		waitForExtJSAjaxComplete(20);

		//c18672
		clickOrderItemsTab();

		clickAddOrderItemEquipment();

		addOrderItem(equipment);

		softAssert.assertTrue(orderItemExist(equipment), "order Item Equipment Exist ");

		removeOrderItem(equipment);

		softAssert.assertFalse(orderItemExist(equipment), "order Item Equipment not Exist ");


		//c18673
		clickOrderItemsTab();

		clickAddOrderItemVehicle();

		addOrderItem(vehicle);

		softAssert.assertTrue(orderItemExist(vehicle), "order Item Vehicle Exist ");

		removeOrderItem(vehicle);

		softAssert.assertFalse(orderItemExist(vehicle), "order Item Vehicle not Exist ");



		//c18674
		clickOrderItemsTab();

		clickAddOrderItemParking();

		addOrderItem(parking2);

		softAssert.assertTrue(orderItemExist(parking2), "order Item Parking Exist ");

		removeOrderItem(parking2);

		softAssert.assertFalse(orderItemExist(parking2), "order Item Parking not Exist ");


		waitForExtJSAjaxComplete(20);

		clickParticipantTab();

		setTotalParticipants(numberPeople);

		addParticipantEmployee(employee);

		waitForExtJSAjaxComplete(20);

		addParticipantContact(contact);

		addOtherPerson(first, last);

		softAssert.assertEquals(getTotalParticipants(),"3","number of participants automatically increased");

		clickRemoveParticipant();

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getTotalParticipants(),"3","number of participants does not decrease when removed");

		Grid.checkRowInGriByTextValueAndClick(driver, company);

		clickRemoveParticipant();

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getTotalParticipants(),"3","number of participants does not decrease when removed");

		clickEquipmentTab();

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(equipment);

		waitForExtJSAjaxComplete(20);

		clickVehicleTab();

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(vehicle);

		waitForExtJSAjaxComplete(20);

		clickParkingTab();

		waitForExtJSAjaxComplete(5);

		setParkingCategory("");

		clickParkingTab();

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(parking2);

		waitForExtJSAjaxComplete(20);


		//c18662

		clickGeneralTab();

		setTimeFromReservationPanel(fromWrong);

		clickConfirmReservation();
		
		isReservationDone=true;

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("OK");

		waitForExtJSAjaxComplete(20);

		setTimeFromReservationPanel(from);


		clickConfirmReservation();

		waitForExtJSAjaxComplete(20);

		navigateToMainPage();

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_MYRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		myReserv.setRegionMyReservation(region);

		waitForExtJSAjaxComplete(20);

		myReserv.setFutureDateMyReservation(date);

		waitForExtJSAjaxComplete(20);

		myReserv.clickSearchButton();

		waitForExtJSAjaxComplete(30);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date+" "+from+"|"+parking2,"@class", "grid3"),"parking is reserved with parking");

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date+" "+from+"|"+vehicle,"@class", "grid3"),"vehicle is reserved with parking");

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date+" "+from+"|"+equipment,"@class", "grid3"),"equipment is reserved with parking");

		checkRowInGriByTextValueAndClick(date+" "+from,parking);

		waitForExtJSAjaxComplete(20);

		clickViewCancel();

		waitForExtJSAjaxComplete(20);

		clickOrderItemsTab();

		clickDetailOrderItem();

		//c18677
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
		
		waitForExtJSAjaxComplete(10);

		setTimeFromReservationPanel(fromEd);
		
		waitForExtJSAjaxComplete(10);

		clickConfirmReservation();
		
	}
		finally{
			
		if(isReservationDone=true){
		
		navigateToMainPage();
			
		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_MYRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		myReserv.setRegionMyReservation(region);

		waitForExtJSAjaxComplete(20);

		myReserv.setFutureDateMyReservation(dateEd);

		waitForExtJSAjaxComplete(10);

		myReserv.clickSearchButton();

		waitForExtJSAjaxComplete(20);

		checkRowInGriByTextValueAndClick(dateEd+" "+fromEd,parking);

		waitForExtJSAjaxComplete(20);

		clickViewCancel();

		waitForExtJSAjaxComplete(20);

		clickGeneralTab();

		waitForExtJSAjaxComplete(20);

		clickCancelReservation();

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(20);

		navigateToMainPage();

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_MYRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		myReserv.setRegionMyReservation(region);

		waitForExtJSAjaxComplete(20);

		myReserv.setFutureDateMyReservation(date);

		waitForExtJSAjaxComplete(10);

		myReserv.clickSearchButton();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isRowInGridAbsent(parking, date+" "+from),"parking can not be canceled");
		}else{
			System.out.println("Reservation is not done");
		}
		}
		softAssert.assertAll();

		Reporter.log("Parking was succesfully reserved and canceled", true);
	}

	/**
	 * Testcase ID	:	C74395,C74396,C117540
	 * Author		:	Intellias
	 */

	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testTentativeParkingCreateDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74395: Tentative Parking Reservation also generate cost items <br>"
				+ "Test C74396: More than one Tentative Parking Reservation can not be made for the same time period "
				+ "C117540: Non-Room Reservations may only contain Reservation Lines of same Type (MYM-26574) </span> <br>", true);

		Reporter.log("User reserves tentative parking <br>", true);


		String region = "1preRgRef";

		int random = (int)(Math.random() * 10)+18;


		String date = "01-01-20"+random;

		String from = "10:00";

		String until = "10:30";

		String responsible = getUserLastNameOfLoggedInUser();
		String parking = "prePrkDefRef";

		boolean isReservationDone=false;

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testTentativeParking");

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

		waitForExtJSAjaxComplete(20);

		setTimeFrom(from);

		setTimeUntil(until);

		waitForExtJSAjaxComplete(20);

		String reservDate=getDate();

		waitForExtJSAjaxComplete(20);

		clickParkingTab();

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();

		clickLaunchReservation(parking);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setResponsible(responsible);

		waitForExtJSAjaxComplete(20);

		clickTentative();

		waitForExtJSAjaxComplete(20);
			
		clickOrderItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickOrderItemsTab();

		this.waitForMaskDisappear();

		waitForExtJSAjaxComplete(10);

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

		softAssert.assertTrue(getSummaryTotalCost().contains("2.02 EUR"),"tentative parking generates costs");

		softAssert.assertTrue(getSummaryTitle().contains("2.02 EUR"),"tentative parking generates costs summary");

		clickConfirmReservation();
		
		isReservationDone=true;
		
		waitForExtJSAjaxComplete(20);
	
			
		navigateToMainPage();

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

		waitForExtJSAjaxComplete(5);

		clickParkingTab();

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//span[contains(text(),'"+parking+"')]"),"tentative parking "+parking+ "is not present after search");

	}
	finally{
		deleteReservationFromMyReservation(date,from,parking,isReservationDone);
	}
		
		softAssert.assertAll();

		Reporter.log("Tentative parking was succesfully reserved", true);		

	}

	/**
	 * Testcase ID	:	C74380,C115849
	 * Author		:	Intellias
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testAdditionalCostParking() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74380: In Parking Reservation, cost for Reservable Parking and additional cost for added Equipment, Vehicles & Parking are displayed in Summary </span> <br>", true);

		Reporter.log("User reserves additional parking <br>", true);


		String region = "1preRgRef";

		int random = (int)(Math.random() * 10)+18;


		String date = "03-02-20"+random;

		String from = "15:00";

		String until = "15:30";

		String responsible = getUserLastNameOfLoggedInUser();
		String parking = "prePrkDefRef";

		String vehicle = "1preRsVhRef";

		String equipment = "preEqDefRef";

		String parking2 = "1prePrkEqRef";
		boolean isReservationDone = false;


		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testAdditionalParking");

		waitForExtJSAjaxComplete(20);

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

		waitForExtJSAjaxComplete(5);

		clickParkingTab();

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(parking);

		waitForExtJSAjaxComplete(20);

		setResponsible(responsible);

		waitForExtJSAjaxComplete(20);

		clickParkingTab();

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(parking2);

		waitForExtJSAjaxComplete(20);

		clickVehicleTab();

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(vehicle);

		waitForExtJSAjaxComplete(20);

		clickEquipmentTab();

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(equipment);

		waitForExtJSAjaxComplete(20);

		clickSummaryTab();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(getSummaryTotalCost().contains("6.06 EUR"),"additional costs for room are ok");

		softAssert.assertTrue(getSummaryTitle().contains("6.06 EUR"),"summary title is ok");

		softAssert.assertTrue(getSummaryItemCost(vehicle).contains("2.02 EUR"),"vehicle cost is ok");

		softAssert.assertTrue(getSummaryItemCost(parking2).contains("-"),"parking2 cost is ok");

		softAssert.assertTrue(getSummaryItemCost(parking).contains("2.02 EUR"),"parking cost is ok");

		softAssert.assertTrue(getSummaryItemCost(equipment).contains("2.02 EUR"),"equipment cost is ok");

		try{
			clickConfirmReservation();

			waitForExtJSAjaxComplete(20);

			softAssert.assertTrue(isReservationDone(), "Reservation is saved (Confirmed)");

			isReservationDone = true;
		}

		//cancel Reservation
		finally{
			deleteReservationFromMyReservation(date, from, parking, isReservationDone);
		}

		softAssert.assertAll();

		Reporter.log("Additional cost parking was succesfully reserved", true);

	}

	/**
	 * Testcase ID	:	C74392
	 * Author		:	Intellias
	 */	
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testRecurrenceParking() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74392: Book a Parking with Recurrence for available slots </span><br>", true);

		Reporter.log("User reserves parking recurrence<br>", true);


		String region = "1preRgRef";

		int random = (int)(Math.random() * 10)+18;

		String dateRec = "10-01-20"+random;
		String dateRec2 = "12-01-20"+random;

		String date = "11-01-20"+random;
		String from = "19:45";
		String until = "20:00";
		String responsible =getUserLastNameOfLoggedInUser();
		//String service = "1preSrvRef";
		String vehicle = "1preRsVhRef";
		String parking = "2prePrkResRef";
		String equipment  = "1preRsGnEqRef";
		String parking2 = "1prePrkEqRef";
		ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();
		boolean isReservationDone=false;

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testRecurrenceParking");
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

		waitForExtJSAjaxComplete(5);

		clickParkingTab();

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(parking);

		waitForExtJSAjaxComplete(20);

		setResponsible(responsible);

		waitForExtJSAjaxComplete(20);

		clickConfirmReservation();
		
		isReservationDone=true;

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region);

		setDate(dateRec);

		setTimeFrom(from);

		setTimeUntil(until);

		waitForExtJSAjaxComplete(5);

		clickParkingTab();

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(parking);

		waitForExtJSAjaxComplete(20);

		setResponsible(responsible);

		waitForExtJSAjaxComplete(20);

		clickGeneralTab();

		clickGeneralRecurence();

		clickRecurenceEndAfter();

		setRecurenceEndAfter("3");

		clickRecurenceDailyTab();

		waitForExtJSAjaxComplete(20);

		clickRecurenceDailyEvery();

		setRecurenceDailyInterval("1");

		clickRecurenceCalculateDates();

		clickCheckAvailability();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-window')]//div[contains(@class,'x-grid3')]//div[contains(text(),'Unavailable:')]"), "unavailable period");

		softAssert.assertTrue(driver.findElements(By.xpath("//div[contains(@class,'x-window')]//div[contains(@class,'x-grid3')]//div[contains(text(),'Available')]")).size()==2, "2 available period");

		waitForExtJSAjaxComplete(10);

		clickSetRecurence();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(parking2);

		waitForExtJSAjaxComplete(20);

		clickEquipmentTab();

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(equipment);

		waitForExtJSAjaxComplete(30);

		clickVehicleTab();

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(vehicle);

		waitForExtJSAjaxComplete(20);

		clickConfirmReservation();		

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-window-dlg')]//span[contains(text(),'Unable to save')]"), "unable to save occurence");

		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(15);
		
		this.clickGeneralTab();

		waitForExtJSAjaxComplete(15);

		clickGeneralRecurence();
		
		//TO DO: Overlaped dates are removed from the recurence window
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//div[contains(@class,'x-window')]//div[contains(@class,'x-grid3')]//div[contains(text(),'"+date+"')]"), "overlaped date is absent");

		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-window')]//div[contains(@class,'x-grid3')]//div[contains(text(),'"+dateRec+"')]"), "reccurence date is present");

		closeRecurrenceWindow();
	}
		finally{
			if(isReservationDone=true)
			{
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

		myReserv.setRegionMyReservation(region);

		myReserv.setFutureDateMyReservation(date);

		waitForExtJSAjaxComplete(15);

		myReserv.clickSearchButton();

		waitForExtJSAjaxComplete(20);

		checkRowInGriByTextValueAndClick(date+" "+from,parking);

		waitForExtJSAjaxComplete(20);

		clickViewCancel();

		waitForExtJSAjaxComplete(20);

		clickGeneralTab();

		waitForExtJSAjaxComplete(10);

		clickCancelReservation();

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_MYRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		myReserv.setRegionMyReservation(region);

		waitForExtJSAjaxComplete(20);

		myReserv.setFutureDateMyReservation(dateRec);

		waitForExtJSAjaxComplete(5);

		myReserv.clickSearchButton();

		waitForExtJSAjaxComplete(20);

		checkRowInGriByTextValueAndClick(dateRec+" "+from,parking);

		waitForExtJSAjaxComplete(20);

		clickViewCancel();

		waitForExtJSAjaxComplete(20);

		clickGeneralTab();

		waitForExtJSAjaxComplete(5);

		clickCancelReservation();

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_MYRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		myReserv.setRegionMyReservation(region);

		waitForExtJSAjaxComplete(20);

		myReserv.setFutureDateMyReservation(dateRec2);

		waitForExtJSAjaxComplete(20);

		myReserv.clickSearchButton();

		waitForExtJSAjaxComplete(20);

		checkRowInGriByTextValueAndClick(dateRec2+" "+from,parking);

		waitForExtJSAjaxComplete(20);

		clickViewCancel();

		waitForExtJSAjaxComplete(20);

		clickGeneralTab();

		waitForExtJSAjaxComplete(5);

		clickCancelReservation();

		clickOnDialogButton("Yes");
			}else{
				System.out.println("Reservation is not done");
			}
		}
		
		softAssert.assertAll();

		Reporter.log("Recurrence parking was succesfully reserved", true);

	}
	/**
	 * Testcase ID	:	C74379
	 * Author		:	Intellias
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationParkingContactPerson() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C74379 Contact & Other Participant Details are shown in Parking Reservation and Bell symbol displaying  </span><br>", true);


		String region = "1preRgRef";

		int random = (int)(Math.random() * 10)+18;

		String date = "11-02-20"+random;
		String from = "18:30";
		String until = "19:00";

		String parking = "1prePrkEqRef";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testReservationContactParking");

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

		waitForExtJSAjaxComplete(5);

		clickParkingTab();

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(parking);

		waitForExtJSAjaxComplete(20);

		clickParticipantTab();

		waitForExtJSAjaxComplete(10);

		addParticipantContact("1preContactLast");

		waitForExtJSAjaxComplete(20);

		McsElement.getElementByXpath(driver, "//img[contains(@src,'announce')]").click();

		softAssert.assertTrue(McsElement.isElementPresent(driver, "//img[contains(@src,'announce')]"),"bell near participant is present");

		waitForExtJSAjaxComplete(20);

		clickAnnounceCheckbox();

		waitForExtJSAjaxComplete(15);

		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//img[contains(@src,'announce')]"),"no bell near participant is present");

		addOtherPerson("firsnt", "lasnt");

		waitForExtJSAjaxComplete(20);

		selectParticipant("firsnt");

		softAssert.assertAll();

		Reporter.log("Parking contact person was added", true);
	}
	/**
	 * Testcase ID	:	C74393,C115851 
	 * Author		:	Kve
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testConfidentialParkingCreateDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C115851: In Parking Reservation, lookup values for GL Account, Cost Center and all other Finkeys must be limited to one Fiscal Entity </span> <br>"
				+ "Test C74393: Confidential Parking Reservation </span> <br>", true);

		Reporter.log("User reserves confidential parking <br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		
		String date = "09-03-20"+random;

		String from = "16:00";

		String until = "16:30";

		String responsible = getUserLastNameOfLoggedInUser();
		String parking = "prePrkDefRef";
		
		String vehicle = "1preRsVhRef";
		
		String equipment = "preEqDefRef";
		
		String parking2 = "1prePrkEqRef";
		
		boolean isReservationDone=false;
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testConfidentialParking");

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
		
		waitForExtJSAjaxComplete(5);
		
		String reservDate=getDate();
		
		clickParkingTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(parking);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfidential();
		
		//C115851

		clickTabInReservationDetailsSection("Financial");

		waitForExtJSAjaxComplete(20);

		List<String> finkey3List = new ArrayList<String>();
		finkey3List.add("3preValue");

		List<String> finkey4List = new ArrayList<String>();
		finkey4List.add("4preValue");

		List<String> glAccList = new ArrayList<String>();
		glAccList.add("myMCS Default GL Account");

		List<String> glAccVals= getGLAccountLookUpValues();

		clickCANCELXwindow();
		softAssert.assertTrue(glAccVals.contains("myMCS Default GL Account") , "Expected GL accounts are populated");
		waitForExtJSAjaxComplete(20);

		List<String> finKey3Vals = getFinKey3LookUpValues();
		clickCANCELXwindow();
		softAssert.assertTrue(finKey3Vals.contains("3preValue") , "Expected finkey3 List are populated");

		waitForExtJSAjaxComplete(20);
		List<String> finKey4Vals = getFinKey4LookUpValues();
		clickCANCELXwindow();
		softAssert.assertTrue(finKey4Vals.contains("4preValue") , "Expected finkey4 List are populated");

		waitForExtJSAjaxComplete(20);

		//C74393
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(equipment);
		
		waitForExtJSAjaxComplete(20);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(vehicle);
		
		waitForExtJSAjaxComplete(20);
		
		clickParkingTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(parking2);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		isReservationDone=true;
	}
		finally{
			
			this.deleteReservationFromMyReservation(date, from, parking2, isReservationDone);
		}

		
		softAssert.assertAll();
		
		Reporter.log("Confidential parking was succesfully reserved", true);
	}

/**
	 * Testcase ID	:	C74390,C119033
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testVerifyAlertMsgForPrepareCleanupTimeInParkingReservation() throws Exception{


		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"Searching available Rooms with Prepare/Clreanup Time"
				+"C74390: Alert message for Maximum Prepare/Cleanup time is displayed in parking Reservation </span><br>", true);


		String region = "1preRgRef";

		int random = (int)(Math.random() * 10)+18;

		String date = this.getFutureDate(random);
		String from = "00:30";
		String until = "02:00";
		String prepareTime="00:30";
		String cleanupTime="01:30";
		String updatePrepareTime="01:00";
		String updateCleanUpTime = "02:30";
		String updatedFromTime="18:30";
		String responsible =getUserLastNameOfLoggedInUser();
		String parking1="1aqaPrkRef";
		String parking2="2aqaPrkRef";
		String room="2aqaRoomRef";
		boolean isReservationDone=false;

		String warningMsg="Unable to save\n"+"\n"+
				"  - With the Preparation time included, the Reservation does not fit in one day. Please decrease the Preparation Time or increase the From time.";
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testVerifyAlertMsgForPrepareCleanupTimeInParkingReservation");

		try{
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

		waitForExtJSAjaxComplete(5);

		clickParkingTab();

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(parking2);

		waitForExtJSAjaxComplete(20);

		clickGeneralTab();

		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getPrepareTimeGeneral(), prepareTime,"prepare time is displayed in general");

		softAssert.assertEquals(getCleanUpTimeGeneral(), cleanupTime,"clean up time is displayed in general");

		setResponsible(responsible);

		waitForExtJSAjaxComplete(20);

		clickOrderItemsTab();

		waitForExtJSAjaxComplete(20);

		clickAddOrderItemParking();

		waitForExtJSAjaxComplete(20);

		addOrderItem(parking1);

		waitForExtJSAjaxComplete(20);

		Boolean status=isItemAddedInOrderItemsPane("parking",parking2);

		softAssert.assertTrue(status,"Item is added");

		status=isItemAddedInOrderItemsPane("parking",parking1);

		softAssert.assertTrue(status,"Item is added");

		clickGeneralTab();

		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getPrepareTimeGeneral(), updatePrepareTime,"prepare time is displayed in general");

		softAssert.assertEquals(getCleanUpTimeGeneral(), updateCleanUpTime,"clean up time is displayed in general");

		clickConfirmReservation();

		waitForExtJSAjaxComplete(20);

		String text=getWarningDialogTextMsg();
		System.err.println(text);
		
		System.err.println(warningMsg);
		softAssert.assertTrue(text.contains(warningMsg),"Error message is displayed for maximun prepare time specified to the added parking");

		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		clickCloseReservationUsingJS();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Close");
		
		waitForExtJSAjaxComplete(20);
		
		//C119033
		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region);

		setDate(date);

		setTimeFrom("19:00");

		setTimeUntil("20:00");

		waitForExtJSAjaxComplete(5);

		clickRoomTab();

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(room);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		isReservationDone=true;
	
		//verify room availability on preparation time 
		driver.navigate().refresh();
		
		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region);

		setDate(date);

		setTimeFrom("18:00");

		setTimeUntil("19:00");

		waitForExtJSAjaxComplete(5);

		clickRoomTab();

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(isDisplayLaunchReservation(room),"Specified Room is not displayed in the Room's list (prepare time is from 18:30 ");
		
		driver.navigate().refresh();
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region);

		setDate(date);

		setTimeFrom("16:30");

		setTimeUntil("17:00");

		waitForExtJSAjaxComplete(5);

		clickRoomTab();

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isDisplayLaunchReservation(room),"Specified Room is displayed in the Room's list (prepare time is from 18:30 ");
	}
			finally{
				if (isReservationDone = true) {
					
					driver.navigate().refresh();
				
					waitForExtJSAjaxComplete(25);
					waitForExtJSAjaxComplete(25);

					expandAdministration();
					waitForExtJSAjaxComplete(20);

					expandSubMainMenu("Reservation");
					waitForExtJSAjaxComplete(20);

					waitAndClick(XPATH_MYRESERVATIONS);
					waitForExtJSAjaxComplete(20);

					ReservationMyReservationsPageObject myReserv=new ReservationMyReservationsPageObject();
					myReserv.setRegionMyReservation(region);
					waitForExtJSAjaxComplete(20);

					myReserv.setTodayDateInMyReservation(date);
					myReserv.clickSearchButton();
					waitForMaskDisappear();
					waitForExtJSAjaxComplete(20);

					checkRowInGriByTextValueAndClick(date+" "+updatedFromTime,room);
					waitForExtJSAjaxComplete(20);

					clickViewCancel();
					waitForExtJSAjaxComplete(20);

					clickGeneralTab();
					waitForExtJSAjaxComplete(20);

					clickCancelReservation();
					clickOnDialogButton("Yes");
					waitForExtJSAjaxComplete(20);

					softAssert.assertTrue(Grid.isRowInGridAbsent(driver,date+" "+from+"|"+room,"@class", "grid3"),"Room reservation is cancelled");

				}
			else{
			System.err.println("Reservation is not confirmed");
		}
			}
		softAssert.assertAll();

		Reporter.log("Alert message is displayed Maximum Prepare/Cleanup time in parking Reservation<br>", true); 

	}


	/**
	 * Testcase ID	:	C74397
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testUnavailableParkingReservationCanNotBeBooked() throws Exception{


		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C74397:Reservable parking for the specified Unavailable Periods can not be booked </span><br>", true);


		String region = "1preRgRef";

		//int random = (int)(Math.random() * 10)+18;

		String date = "09-07-2035";
		String from = "01:00";
		String until = "02:00";
		String parking="ParkingUnavailblePeriodRef";
		String regions = "All Regions";
		String unAvailableReservationTooTipMsg="The Resource is not available at that moment";

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testUnavailableParkingReservationCanNotBeBooked");
		ReservationsCalendarNewPageObject calendarPageObj=new ReservationsCalendarNewPageObject();

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

		waitForExtJSAjaxComplete(5);

		clickParkingTab();

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		Boolean status=isDisplayLaunchReservation(parking);

		softAssert.assertFalse(status, "Vehicle item is not displayed");

		waitAndClick(XPATH_CALENDAR);
		
		waitForExtJSAjaxComplete(20);
		
		clickCalenderTab();
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		calendarPageObj.clickParkingTabInCalendarTab();
		
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
		
		calendarPageObj.filterItemByName(parking);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,calendarPageObj.CALENDAR_RESOURCE_WIN_XPATH+"//div[text()='"+parking+"']"), "Reservable vehicle is displayed");
		
		String text=calendarPageObj.getToolTipTextOfUnAvailableReservation(parking);
		
		softAssert.assertTrue(text.contains(unAvailableReservationTooTipMsg),"Tool tip message is displayed for unavailable reservation");
		
		softAssert.assertAll();

		Reporter.log("Reservable parking can not be booked for the specified Unavailable Periods <br>", true); 

	}

	/**
	 * Testcase ID	:	C74391/C122850
	 * Author		:	vpcc
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testVerifyCostCenterInParkingBooking(){

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C74391/C122850 :Verify that Cost Center value is correctly updated in Parking Reservations when changing the value from Cost Center Combo box</span><br>", true);


		String region = "1preRgRef";
		String responsible = "SELENIUM";
		int random = (int)(Math.random() * 10)+18;

		String date = this.getFutureDate(random);

		String from = "13:00";
		String until = "14:00";
		String parking = "1prePrkEqRef";
		String project="1preProjectRef";
		String costCenterMethod1=  "From Responsible";
		String costCenterMethod2="From Department";
		String costCenterMethod3="From Project";
		String costCenterMethod4="Other";
		String costCenter = "myMCS Default Cost Center";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testVerifyCostCenterInParkingBooking");

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

		waitForExtJSAjaxComplete(5);

		clickParkingTab();

		setTimeUntil(until);

		waitForExtJSAjaxComplete(5);

		clickParkingTab();

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(parking);

		waitForExtJSAjaxComplete(20);

		setResponsible(responsible);

		waitForExtJSAjaxComplete(20);

		setProject(project);

		clickTabInReservationDetailsSection("Financial");

		waitForExtJSAjaxComplete(20);

		this.setCostCenterMethodInFinancialsTab(costCenterMethod1);

		waitForExtJSAjaxComplete(20);

		String costCenterFromResponsible = this.getCostCenter();

		softAssert.assertEquals(costCenterFromResponsible, costCenter,"Cost Center lookup value is automatically filled with the Cost Center which is linked to the Responsible Person.");

		this.setCostCenterMethodInFinancialsTab(costCenterMethod2);

		waitForExtJSAjaxComplete(20);

		String costCenterFromDept = this.getCostCenter();

		softAssert.assertEquals(costCenterFromDept, costCenter,"Cost Center lookup value is automatically filled with the Default Cost Center which is linked to the Department.");

		this.setCostCenterMethodInFinancialsTab(costCenterMethod3);

		waitForExtJSAjaxComplete(20);

		String costCenterFromProject = this.getCostCenter();

		softAssert.assertEquals(costCenterFromProject, costCenter,"Cost Center lookup value is automatically filled with the Cost Center which is linked to the Project.");


		this.setCostCenterMethodInFinancialsTab(costCenterMethod4);

		waitForExtJSAjaxComplete(20);

		List<String> costCentersList = new ArrayList<String>();
		costCentersList.add(costCenter);

		softAssert.assertEquals(costCenterFromProject, costCenter,"Cost Center(s) linked to the Fiscal Entity that is assigned to the responsible person are only displayed.");

		softAssert.assertAll();

		Reporter.log("Cost Center values are correctly updated<br>", true);


	}

	/**
	 * Testcase ID : C74386 
	 * Author  : ssa
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testParkingResrvSummeryAndPrintTabs(){

		Reporter.log("<span style='font-weight:bold;color:#000033'> "

		 +"test:C74386: Summary and Printing are not available, if required field is not filled out for parking Reservation", true);


		String region = "1preRgRef";

		int random = (int)(Math.random() * 10)+18;

		String date = this.getFutureDate(random);

		String from = "01:00";
		String until = "01:30";

		String parking = "1prePrkEqRef";
		 String summaryTabText= "A summary is not available for the currently entered data\n"
				  +"\n"+
				  "Please fill in the Reservation Responsible.\n"+
				  "\n"+"Please make sure you have filled out all mandatory fields.";
		 

		String  printIconWarningMsg ="Printing requires all changes to be saved first";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testParkingResrvSummeryAndPrintTabs");

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		//waitForExtJSAjaxComplete(20);

		setRegion(region);

		setDate(date);

		setTimeFrom(from);

		setTimeUntil(until);

		waitForExtJSAjaxComplete(5);

		clickParkingTab();

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(parking);

		waitForExtJSAjaxComplete(20);

		clickSummaryTab();

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getSummaryTabText(),summaryTabText,"'A summary is not available for the currently entered data' is display with a message to fill out required fields.");

		clickReportIconInSummarySection();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(verifyWarningDialogTextMessage(printIconWarningMsg),"'Printing requires all changes to be saved first' alert message is displayed.");

		this.clickOnDialogButton("OK");

		softAssert.assertAll();

		Reporter.log("Summary pane is display with a message to fill out required fields."
				+ "Alert message is displayed for print icon <br>", true);
	}  

	/**
	 * TestCase ID : C118064
	 * Author      : MMA
	 * @throws IOException
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testParkingBookingPreparationTimeAndCleanupNotConsidered() throws IOException{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C118064: Preparation and Cleanup time should not be considered while calculating Total Cost in Parking reservation", true);

		 int random = (int)(Math.random() * 10)+24;
		 String date = this.getFutureDate(random);
		 String region = "1preRgRef";
		 String from = "18:00";
		 String until = "19:00";
		 String prakingResv = "prePrkDefRef";
		 String prepTime = "00:30";
		 String cleanupTime = "00:30";
		 String responsible=getUserLastNameOfLoggedInUser();

		 SoftAssert softAssert = new SoftAssert();
		 softAssert.setMethodName("testParkingBookingPreparationTimeAndCleanupNotConsidered");
		 
		 expandAdministration();
		 waitForExtJSAjaxComplete(20);

		 expandSubMainMenu("Reservation");
		 waitForExtJSAjaxComplete(20);

		 waitAndClick(XPATH_NEWRESERVATIONS);
		 waitForExtJSAjaxComplete(20);
		 waitForExtJSAjaxComplete(20);

		 //C118064
		 setRegion(region);
		 setDate(date);
		 setTimeFrom(from);
		 setTimeUntil(until);
		 waitForExtJSAjaxComplete(20);

		 clickParkingTab();
		 waitForExtJSAjaxComplete(10);

		 clickSearch();
		 waitForExtJSAjaxComplete(20);
		 waitForExtJSAjaxComplete(20);

		 clickLaunchReservation(prakingResv);
		 waitForExtJSAjaxComplete(10);

		 setTimePrepareFromReservationPanel(prepTime);
		 waitForExtJSAjaxComplete(10);

		 setTimeCleanupFromReservationPanel(cleanupTime);
		 waitForExtJSAjaxComplete(10);

		 setResponsible(responsible);
		 waitForExtJSAjaxComplete(10);

		 clickSummaryTab();
		 waitForExtJSAjaxComplete(20);

		 String getSummaryText = getItemDetailsFromReservationSummary(prakingResv);
		 softAssert.assertTrue( getSummaryText.contains("1 h x 4.00 EUR"),"Total cost is calculated without considering Prepare/Cleanup time");
		 softAssert.assertTrue(getSummaryTitle().contains("4.04 EUR"),"summary title is ok before adding additional Equipment, Vehicles & Parking");
	}
}


