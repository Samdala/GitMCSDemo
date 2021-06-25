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




public class ReservationEquipmentCRUDTestSuite extends
		ReservationPageObject {
	
	/**
	 * Testcase ID	:	C74426,C74439,C74440,C74441,C74429
	 * Author		:	Intellias
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testEquipmentCreateDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C74426: Book an Equipment <br>"
				+ "C74439: Add/Remove additional Equipment to Equipment Reservation <br>"
				+ "C74440: Add/Remove additional Vehicle to Equipment Reservation <br>"
				+ "C74441: Add/Remove additional Parking to Equipment Reservation <br>"
				+ "C74429: Department is auto populated if select Responsible field, while reserving an Equipment </span><br>", true);


		Reporter.log("User reserves equipment <br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "11-01-20"+random;
		String from = "02:00";
		String fromEd = "02:15";
		String dateEd = "12-01-20"+random;
		
		
		String fromWrong = "03:00";
		
		String until = "02:30";
		String numberPeople = "2";
		String responsible = getUserLastNameOfLoggedInUser();
		String equipment = "1preRsGnEqRef";
		String equipment2 = "2preRsEqRef";
		String category = "1preGnEqRef";
		
		String parking = "1prePrkEqRef";
		
//		String service = "1preSrvRef";
		
		String vehicle = "1preRsVhRef";
		
		String employee = getUserLastNameOfLoggedInUser();
		String contact = "1preContactLast";
		String company = "My Company";
		String first = "otherFrist";
		String last = "otherLast";
		String department = "AQA_DEPARTMENT";
		
		
		String wrongDepartment = "2preDepartName"; 
		String department2 = "3preDepartName";
		
		boolean isReservationDone=false;
		String project = "1preProjectRef";

		String projectPart = "1prePrPartRef";
		
		String equipment3 = "3preEqRef";

		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testParkingCreateDelete");
		
		ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();

		try{
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		clickEquipmentTab();
		
		setRegion(region);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(5);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'orderroomsbywrapper')]/div[contains(@class,'hide')]"),"sorting is not visible");
		
		softAssert.assertFalse(driver.findElements(By.xpath("//span[contains(text(),'"+equipment+"')]")).isEmpty(),"parking "+equipment+ "is present after search");
		
		softAssert.assertFalse(driver.findElements(By.xpath("//span[contains(text(),'"+equipment2+"')]")).isEmpty(),"parking "+equipment2+ "is present after search");
		
		waitForExtJSAjaxComplete(20);

		setEquipmentCategory(category);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//span[contains(text(),'"+equipment2+"')]"),"parking "+equipment2+ "is not present after search");
		
		clickLaunchReservation(equipment);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
	
		waitForExtJSAjaxComplete(20);
		
//		c18722	
		setProject(project);
			
		waitForExtJSAjaxComplete(20);
			
		softAssert.assertEquals(getProject(), project,"project was selected");
			
		setProjectPart(projectPart);
			
		waitForExtJSAjaxComplete(20);
			
		softAssert.assertEquals(getProject(), projectPart,"project part was selected");

		
// c18718
		softAssert.assertEquals(getDepartment(),department,"default department was autoselected");


		
// c18719		
		clickLookup("@class","mcsreservationedit", "department","Select a Department");
					
		waitForExtJSAjaxComplete(20);
						
		softAssert.assertFalse(setValueGridLookupWithFiltersWithValidation("@id", getXWindowId("Select a Department"), wrongDepartment, "Name"),"department from another company is not present");
		
		waitForExtJSAjaxComplete(20);
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Department"), department2, "Name");
				
		waitForExtJSAjaxComplete(20);
						
		clickOrderItemsTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickDetailOrderItem();
		
// c18716
		clickLookup("@class","x-panel", "resource","Select Equipment");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(setValueGridLookupWithFiltersWithValidation("@id", getXWindowId("Select Equipment"), equipment3, "Reference"),"equipment from another region is not present");
		waitForExtJSAjaxComplete(20);
		setExactValueGridLookupWithFiltersWithValidation("@id", getXWindowId("Select Equipment"), equipment, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		//c18728
		clickOrderItemsTab();
				
		clickAddOrderItemEquipment();
				
		addOrderItem(equipment2);
				
		softAssert.assertTrue(orderItemExist(equipment2), "order Item Equipment Exist ");
				
		removeOrderItem(equipment2);
				
		softAssert.assertFalse(orderItemExist(equipment2), "order Item Equipment not Exist ");
				
				
		//c18729
		clickOrderItemsTab();
						
		clickAddOrderItemVehicle();
						
		addOrderItem(vehicle);
						
		softAssert.assertTrue(orderItemExist(vehicle), "order Item Vehicle Exist ");
						
		removeOrderItem(vehicle);
						
		softAssert.assertFalse(orderItemExist(vehicle), "order Item Vehicle not Exist ");
				
				
		//c18730
		clickOrderItemsTab();
								
		clickAddOrderItemParking();
								
		addOrderItem(parking);
								
		softAssert.assertTrue(orderItemExist(parking), "order Item Parking Exist ");
								
		removeOrderItem(parking);
								
		softAssert.assertFalse(orderItemExist(parking), "order Item Parking not Exist ");
		
		waitForExtJSAjaxComplete(20);
		
		clickParticipantTab();
		
		setTotalParticipants(numberPeople);
		
		addParticipantEmployee(employee);
		
		addParticipantContact(contact);
		
		addOtherPerson(first, last);
		
		softAssert.assertEquals(getTotalParticipants(),"3","number of participants automatically increased");
		
		waitForExtJSAjaxComplete(20);
		
		clickRemoveParticipant();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getTotalParticipants(),"3","number of participants does not decrease when removed");
		
		Grid.checkRowInGriByTextValueAndClick(driver, company);
		
		waitForExtJSAjaxComplete(20);
		
		clickRemoveParticipant();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getTotalParticipants(),"3","number of participants does not decrease when removed");
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(10);
		
		setEquipmentCategory("");
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(equipment2);
		
		waitForExtJSAjaxComplete(20);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(vehicle);
		
		waitForExtJSAjaxComplete(20);
		
		clickParkingTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(parking);
		
		waitForExtJSAjaxComplete(20);
		
		
//c18717
		
		clickGeneralTab();
		
		setTimeFromReservationPanel(fromWrong);
		
		clickConfirmReservation();
				
		waitForExtJSAjaxComplete(20);
				
		waitForExtJSAjaxComplete(20);
				
		clickOnDialogButton("OK");
				
		waitForExtJSAjaxComplete(20);
				
		setTimeFromReservationPanel(from);
		
		clickConfirmReservation();
		
		isReservationDone=true;
		
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPage();
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		waitForExtJSAjaxComplete(20);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(20);
		
		myReserv.setRegionMyReservation(region);
		
		waitForExtJSAjaxComplete(20);
		
		myReserv.setTodayDateInMyReservation(date);
		
		waitForExtJSAjaxComplete(20);
		
		myReserv.setFutureDateMyReservation(date);
		
		waitForExtJSAjaxComplete(20);
		
		myReserv.clickSearchButton();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date+" "+from+"|"+parking,"@class", "grid3"),"parking is reserved with equipment");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date+" "+from+"|"+vehicle,"@class", "grid3"),"vehicle is reserved with equipment");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date+" "+from+"|"+equipment2,"@class", "grid3"),"equipment is reserved with equipment");
				
		checkRowInGriByTextValueAndClick(date+" "+from,equipment);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		clickOrderItemsTab();
		
		clickDetailOrderItem();
		
//c18733
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
		
		setDateFromReservationPanel(dateEd);
		
		waitForExtJSAjaxComplete(20);
		
		setTimeFromReservationPanel(fromEd);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		myReserv.setRegionMyReservation(region);
		
		waitForExtJSAjaxComplete(20);
		
		myReserv.setTodayDateInMyReservation(dateEd);
		
		waitForExtJSAjaxComplete(20);
		
		myReserv.setFutureDateMyReservation(dateEd);
		
		waitForExtJSAjaxComplete(20);
		
		myReserv.clickSearchButton();
		
		waitForExtJSAjaxComplete(20);
		
		checkRowInGriByTextValueAndClick(dateEd+" "+fromEd,equipment);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickCancelReservation();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
	}
		finally{
			if(isReservationDone=true)
			{
		
		navigateToMainPage();
		
		expandAdministration();
		waitForExtJSAjaxComplete(5);

		expandSubMainMenu("Reservation");
		waitForExtJSAjaxComplete(5);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(20);
		
		myReserv.setRegionMyReservation(region);
		
		waitForExtJSAjaxComplete(20);
		
		myReserv.setTodayDateInMyReservation(date);
		
		waitForExtJSAjaxComplete(20);
		
		myReserv.setFutureDateMyReservation(date);
		
		waitForExtJSAjaxComplete(20);
		
		myReserv.clickSearchButton();
		
		waitForExtJSAjaxComplete(30);
		
		softAssert.assertTrue(isRowInGridAbsent(equipment, date+" "+from),"Equipment can not be canceled");
			}
			else{
				System.out.println("Reservation is not done");
			}
		}
		
		softAssert.assertAll();
		
		Reporter.log("Equipment was succesfully reserved and canceled", true);
	}

	/**
	 * Testcase ID	:	c18542
	 * Author		:	Intellias
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testEquipmentReserveOnly() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Equipment reserving only</span>: c18542<br>", true);

		Reporter.log("User reserves equipment only<br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "11-01-20"+random;
		String from = "07:00";
		
		String until = "07:30";
		String responsible = getUserLastNameOfLoggedInUser();
		String equipment = "1preRsGnEqRef";
		String category = "1preGnEqRef";
		boolean isReservationDone=false;
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testEquipmentReserveOnly");

		try{
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickEquipmentTab();
		
		setRegion(region);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
		
		waitForExtJSAjaxComplete(5);
		
		String reservDate= getDate();
				
		waitForExtJSAjaxComplete(20);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(5);
		
		setEquipmentCategory(category);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(equipment);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
	
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		isReservationDone=true;
	}
	finally{
		if(isReservationDone=true){
		
		navigateToMainPage();
		
		waitForExtJSAjaxComplete(20);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		ReservationMyReservationsPageObject myReserv=new ReservationMyReservationsPageObject();
		
		myReserv.setTodayDateInMyReservation(date);
		
		myReserv.setFutureDateMyReservation(date);
		
		myReserv.clickSearchButton();
	
		waitForExtJSAjaxComplete(20);
		
		checkRowInGriByTextValueAndClick(date+" "+from,equipment);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickCancelReservation();
		
		clickOnDialogButton("Yes");
		}
		else{
			System.out.println("Reservation is not done");
		}
	}
		softAssert.assertAll();
		
		Reporter.log("Equipment only was succesfully reserved", true);
	}
	
	/**
	 * Testcase ID	:	C74451,C74452,C117540
	 * Author		:	Intellias
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testEquipmentTentativeReserve() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C74451: Tentative Equipment Reservation also generate cost items <br>"
				+ "C74452: More than one Tentative Equipment Reservation can not be made for the same time period"
				+ "C117540: Non-Room Reservations may only contain Reservation Lines of same Type (MYM-26574)  </span><br>", true);

		Reporter.log("User reserves tentative equipment<br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "11-01-20"+random;
		String from = "11:00";
		
		String until = "11:30";
		String responsible =getUserLastNameOfLoggedInUser();
		String equipment = "preEqDefRef";
		boolean isReservationDone=false;
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testEquipmentTentative");

		try{
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(20);
		
		setRegion(region);
		
		waitForExtJSAjaxComplete(20);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
				
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(equipment);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
	
		waitForExtJSAjaxComplete(20);
		
		clickTentative();
		
		waitForExtJSAjaxComplete(20);
		
		clickOrderItemsTab();
		
		waitForExtJSAjaxComplete(20);

		//C117540: Non-Room Reservations may only contain Reservation Lines of same Type (MYM-26574) 
		clickAddRemoveOrderItem();

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
		
		clickSummaryTab();
		
		softAssert.assertTrue(getSummaryTotalCost().contains("2.02 EUR"),"tentative equipment generates costs");
		
		softAssert.assertTrue(getSummaryTitle().contains("2.02 EUR"),"tentative equipment generates costs summary");
		
		waitForExtJSAjaxComplete(20);
			
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
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(20);
		
		setRegion(region);
		
		waitForExtJSAjaxComplete(20);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
			
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//span[contains(text(),'"+equipment+"')]"),"tentative equipment "+equipment+ "is not present after search");		
		
	}finally{
		if(isReservationDone=true){
			
		navigateToMainPage();
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		ReservationMyReservationsPageObject myReserv=new ReservationMyReservationsPageObject();
		
		myReserv.setTodayDateInMyReservation(date);
		
		waitForExtJSAjaxComplete(20);
		
		myReserv.setTodayDateInMyReservation(date);
		
		myReserv.clickSearchButton();
		
		waitForExtJSAjaxComplete(20);
		
		checkRowInGriByTextValueAndClick(date+" "+from,equipment);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickCancelReservation();
		
		clickOnDialogButton("Yes");
		}
		else{
			System.out.println("Reservation is not done");
		}
	}
		
		softAssert.assertAll();
		
		Reporter.log("Equipment tentative was succesfully reserved", true);
	}
	
	/**
	 * Testcase ID	:	C74448
	 * Author		:	Intellias
	 * @throws Exception
	 */
	
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testRecurrenceEquipment() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C74448:  Book an Equipment with Recurrence for available slots </span><br>", true);

		Reporter.log("User reserves equipment recurrence<br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		

		String dateRec = "10-01-20"+random;
		String dateRec2 = "12-01-20"+random;
		
		String date = "11-01-20"+random;
		String from = "10:00";
		String until = "10:30";
		String responsible = getUserLastNameOfLoggedInUser();
		String service = "1preSrvRef";
		String vehicle = "1preRsVhRef";
		String parking = "1prePrkEqRef";
		String equipment  = "1preRsGnEqRef";
		String equipment2  = "2preRsEqRef";
		
		boolean isReservationDone=false;
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testRecurrenceEquipment");

		try{
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickEquipmentTab();
		
		setRegion(region);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
		
		waitForExtJSAjaxComplete(5);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(10);
				
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(equipment);
		
		waitForExtJSAjaxComplete(35);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//clickEquipmentTab();
		
		setRegion(region);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setDate(dateRec);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
		
		waitForExtJSAjaxComplete(5);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(equipment);
		
		waitForExtJSAjaxComplete(20);
		
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
		
		clickSetRecurence();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(equipment2);
		
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
		
		clickLaunchReservation(parking);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();		
		
		isReservationDone=true;
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-window-dlg')]//span[contains(text(),'Unable to save')]"), "unable to save occurence");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
    	clickGeneralTab();
    	
    	waitForExtJSAjaxComplete(20);
		
		clickGeneralRecurence();
		
		//TO DO: Overlaped dates are removed from the recurence window
		//softAssert.assertTrue(McsElement.isElementAbsent(driver, "//div[contains(@class,'x-window')]//div[contains(@class,'x-grid3')]//div[contains(text(),'"+date+"')]"), "overlaped date is absent");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-window')]//div[contains(@class,'x-grid3')]//div[contains(text(),'"+dateRec+"')]"), "reccurence date is present");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-window')]//div[contains(@class,'x-grid3')]//div[contains(text(),'"+dateRec2+"')]"), "reccurence date is present");
		
		closeRecurrenceWindow();
	}
		finally{
			if(isReservationDone=true)
			{
		List<String> equipmentItems=Arrays.asList(date,dateRec,dateRec2);
		
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
		int i=0;
		
		for(String equipmentItem:equipmentItems)
		{
		
		myReserv.setTodayDateInMyReservation(equipmentItem);
		  
		waitForExtJSAjaxComplete(10);
		  
		myReserv.clickSearchButton();
		  
		waitForExtJSAjaxComplete(20);
		if(i==0){
			
			checkRowInGriByTextValueAndClick(equipmentItem+" "+from,equipment);
		}else{
		
		checkRowInGriByTextValueAndClick(equipmentItem+" "+from,equipment2);
		}
		
		waitForExtJSAjaxComplete(20);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickCancelReservation();
		
		waitForExtJSAjaxComplete(10);
		
		clickOnDialogButton("Yes");
		
		}
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		myReserv.setRegionMyReservation(region);
		  
		waitForExtJSAjaxComplete(20);
		  
		myReserv.setFutureDateMyReservation(dateRec);
		  
		waitForExtJSAjaxComplete(20);
		  
		myReserv.clickSearchButton();
		
		waitForExtJSAjaxComplete(20);
		
		checkRowInGriByTextValueAndClick(dateRec+" "+from,equipment);
		
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
		  
		myReserv.setFutureDateMyReservation(dateRec2);
		  
		myReserv.clickSearchButton();
		
		waitForExtJSAjaxComplete(20);
		
		checkRowInGriByTextValueAndClick(dateRec2+" "+from,equipment);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickCancelReservation();
		
		clickOnDialogButton("Yes");
			}
			else{
				System.out.println("Reservation is not done");
			}
		
		softAssert.assertAll();
		
		Reporter.log("Recurence equipment was succesfully reserved", true);
		}
		
	}

	/**
	 * Testcase ID	:	C74435
	 * Author		:	Intellias
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationEquipmentContactPerson() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C74435: Contact & Other Participant Details are shown in Equipment Reservation and Bell symbol displaying   </span><br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "11-01-20"+random;
		String from = "11:00";
		String until = "11:30";
		
		
		String equipment  = "1preRsGnEqRef";
		
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReservationContactEquipment");

		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(10);
		
		setRegion(region);
		
		waitForExtJSAjaxComplete(10);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
				
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(equipment);
		
		waitForExtJSAjaxComplete(20);
		
		clickParticipantTab();
		
		waitForExtJSAjaxComplete(10);
		
		addParticipantContact("1preContactLast");
		
		waitForExtJSAjaxComplete(20);
				
		McsElement.getElementByXpath(driver, "//img[contains(@src,'announce')]").click();
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//img[contains(@src,'announce')]"),"bell near participant is present");
		
		waitForExtJSAjaxComplete(15);
		
		clickAnnounceCheckbox();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//img[contains(@src,'announce')]"),"no bell near participant is present");
		
		addOtherPerson("firsnt", "lasnt");
		
		waitForExtJSAjaxComplete(10);
		
		selectParticipant("firsnt");
		
		softAssert.assertAll();
		
		Reporter.log("Service contact person was added", true);
	}
	
	
	/**
	 * Testcase ID	:	C74449,C74445
	 * Author		:	vpcc
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testConfidentialEquipmentBookingAndFinVals() throws Exception{
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C74449:Confidential Equipment Reservation"
				+"C74445:In Equipment Reservation, lookup values for GL Account, Cost Center and all other Finkeys must be limited to one Fiscal Entity </span><br>", true);

		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = this.getFutureDate(random);
		String from = "07:00";
		String until = "07:30";
		String responsible = getUserLastNameOfLoggedInUser();
		String equipment = "1preRsGnEqRef";
		String category = "1preGnEqRef";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testConfidentialEquipmentBookingAndFinVals");

		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(10);
		
		setRegion(region);
		
		waitForExtJSAjaxComplete(15);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(5);
				
		setEquipmentCategory(category);
		
		waitForExtJSAjaxComplete(15);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(equipment);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfidential();
		
		waitForExtJSAjaxComplete(10);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		
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
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isReservationDone(), "Confidential Reservation is confirmed");
		
		String reservationID = getReserationID();
		softAssert.assertFalse(reservationID.isEmpty(),"Confidential Reservation is confirmed and Reservation ID "+ reservationID+"is generated.");
		
		waitForExtJSAjaxComplete(5);
		
		clickCancelReservationUsingJS();
		
		waitForExtJSAjaxComplete(30);
		
		clickOnDialogButton("Yes");
		
		softAssert.assertAll();
		
		}
		
	
	/**
	 * Testcase ID	:	C74446
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testVerifyAlertMsgForPrepareCleanupTimeInEquipmentReservation() throws Exception{
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C74446: Alert message for Maximum Prepare/Cleanup time is displayed in Equipment Reservation </span><br>", true);

		
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
		String equipment1="1aqaEqRef";
		String equipment2="2aqaEqRef";
		String category = "1preRmCtDescr";
		
		String warningMsg="With the Preparation time included, the Reservation does not fit in one day. Please decrease the Preparation Time or increase the From time.";
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testVerifyAlertMsgForPrepareCleanupTimeInEquipmentReservation");
		
	
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		clickRoomTab();
		
		setRegion(region);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
        
		waitForExtJSAjaxComplete(5);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(equipment2);
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getPrepareTimeGeneral(), prepareTime,"prepare time is displayed in general");
		
		softAssert.assertEquals(getCleanUpTimeGeneral(), cleanupTime,"clean up time is displayed in general");
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		
		clickOrderItemsTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickAddOrderItemEquipment();
		
		waitForExtJSAjaxComplete(20);
		
		addOrderItemEquipment(equipment1);
		
		waitForExtJSAjaxComplete(20);
		
		Boolean status=isItemAddedInOrderItemsPane("equipment",equipment2);
		
		softAssert.assertTrue(status,"Item is added");
		
		status=isItemAddedInOrderItemsPane("equipment",equipment1);
		
		softAssert.assertTrue(status,"Item is added");
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getPrepareTimeGeneral(), updatePrepareTime,"prepare time is displayed in general");
		
		softAssert.assertEquals(getCleanUpTimeGeneral(), updateCleanUpTime,"clean up time is displayed in general");
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		String text=getWarningDialogTextMsg();
		
		softAssert.assertTrue(text.contains(warningMsg),"Error message is displayed for maximun prepare time specified to the added equipment");
	
		waitForExtJSAjaxComplete(20);
		
		this.clickOnDialogButton("OK");
		
		softAssert.assertAll();
		
		Reporter.log("Alert message is displayed Maximum Prepare/Cleanup time in Room Reservation<br>", true); 

	}
	
	
	/**
	 * Testcase ID	:	C74453
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testUnavailableEqpReservationCanNotBeBooked() throws Exception{
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C74453:Reservable Equipment for the specified Unavailable Periods can not be booked </span><br>", true);

		
		String region = "1preRgRef";
		//int random = (int)(Math.random() * 10)+18;
		String date = "09-07-2035";
		String from = "01:30";
		String until = "02:00";
		String equipment="EqpUnavailblePeriodRef";
		String regions = "All Regions";
		String unAvailableReservationTooTipMsg="The Resource is not available at that moment.";
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testUnavailableEqpReservationCanNotBeBooked");
		ReservationsCalendarNewPageObject calendarPageObj=new ReservationsCalendarNewPageObject();
		
	
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(30);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		clickRoomTab();
		
		setRegion(region);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
        
		waitForExtJSAjaxComplete(5);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		Boolean status=isDisplayLaunchReservation(equipment);
		
		softAssert.assertFalse(status, "equipment item is not displayed");
	
		waitAndClick(XPATH_CALENDAR);
		
		waitForExtJSAjaxComplete(20);
		
		clickCalenderTab();
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		calendarPageObj.clickEquipmentTabInCalendarTab();
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		calendarPageObj.setRegionInCalendarTab(regions);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		calendarPageObj.clickOnCalendarViews("View Week");
		
		waitForExtJSAjaxComplete(30);
		
		calendarPageObj.clickOnCalendarViews("View 24/7");
		
		waitForExtJSAjaxComplete(20);
	
		waitForMaskDisappear();
		
		calendarPageObj.setDateViaDatePicker(date);
		
		waitForMaskDisappear();
		
		calendarPageObj.filterItemByName(equipment);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,calendarPageObj.CALENDAR_RESOURCE_WIN_XPATH+"//div[text()='"+equipment+"']"), "Reservable equipment is displayed");
		
		String text=calendarPageObj.getToolTipTextOfUnAvailableReservation(equipment);
		
		softAssert.assertTrue(text.contains(unAvailableReservationTooTipMsg),"Tool tip message is displayed for unavailable reservation");
		
		softAssert.assertAll();
		
		Reporter.log("Reservable Equipment can not be booked for the specified Unavailable Periods <br>", true); 

	}
	
	 /**
	 * Testcase ID	:	C74454
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testMinimalTimePeriodEqpReservCanNotBeBooked() throws Exception{
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C74454:Reservable Equipment cannot be booked before the specified Minimal time ahead </span><br>", true);

		
		String region = "1preRgRef";
		
		//int random = (int)(Math.random() * 10)+18;
		
		String date = "09-07-2035";
		String from = "00:30";
		String until = "02:00";
		String equipment="EqpUnavailblePeriodRef";
		String regions = "All Regions";
		String unAvailableReservationTooTipMsg="The Resource is not available at that moment";
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testMinimalTimePeriodEqpReservCanNotBeBooked");
		ReservationsCalendarPageObject calendarPageObj=new ReservationsCalendarPageObject();
	
		
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
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		Boolean status=isDisplayLaunchReservation(equipment);
		
		softAssert.assertFalse(status, "equipment item is not displayed");	
	
		softAssert.assertAll();
		
		Reporter.log("Reservable Equipment can not be booked for the specified Minimal time ahead <br>", true); 
		
	}
	
	/**
	  * Testcase ID : C74442 
	  * Author  : ssa
	  */
	 @Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	 public void testEquipmentReservSummeryAndPrintTabs(){
	  
	  Reporter.log("<span style='font-weight:bold;color:#000033'> "

		 +"test:C74442:C119472: Summary and Printing are not available, if required field is not filled out for Equipment Reservation", true);

	  
	  String region = "1preRgRef";
	  
	  int random = (int)(Math.random() * 10)+18;
	  
	  String date = this.getFutureDate(random);
	  
	  String from = "01:00";
	  String until = "01:30";
	  
	  String equipment = "1preRsGnEqRef";
	  
	  String summaryTabText= "A summary is not available for the currently entered data\n"
			  +"\n"+
			  "Please fill in the Reservation Responsible.\n"+
			  "\n"+"Please make sure you have filled out all mandatory fields.";
	 
	  String  printIconWarningMsg ="Printing requires all changes to be saved first";
	  
	  SoftAssert softAssert = new SoftAssert();
	  
	  softAssert.setMethodName("testEquipmentSummeryAndPrintTabs");

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
	  
	  clickEquipmentTab();
	  
	  waitForExtJSAjaxComplete(5);

	  clickSearch();
	  
	  waitForExtJSAjaxComplete(20);
	  
	  clickLaunchReservation(equipment);
	
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

	 /**Testcase ID	:	C122849 
	  * Author		:	kve
	  */
	 @Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	 public void testVerifyCostCenterInEquipmentBooking(){

		 Reporter.log("<span style='font-weight:bold;color:#000033'> "
				 + "Test: C122849 :Verify that Cost Center value is correctly updated in Equipment Reservations when changing the value from Cost Center Combo box </span><br>", true);


		 String region = "1preRgRef";
		 String responsible = "SELENIUM";
		 int random = (int)(Math.random() * 10)+18;

		 String date = this.getFutureDate(random);

		 String from = "13:00";
		 String until = "14:00";
		 String equipment = "1preRsGnEqRef";
		 String project="1preProjectRef";
		 String costCenterMethod1=  "From Responsible";
		 String costCenterMethod2="From Department";
		 String costCenterMethod3="From Project";
		 String costCenterMethod4="Other";
		 String costCenter = "myMCS Default Cost Center";

		 SoftAssert softAssert = new SoftAssert();

		 softAssert.setMethodName("testVerifyCostCenterInEquipmentBooking");

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

		 clickEquipmentTab();

		 setTimeUntil(until);

		 waitForExtJSAjaxComplete(5);

		 clickEquipmentTab();

		 waitForExtJSAjaxComplete(5);

		 clickSearch();

		 waitForExtJSAjaxComplete(20);

		 clickLaunchReservation(equipment);

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
	  * Testcase ID: C118063,C115779
	  * Author     : MMA
	  * @throws IOException
	  */
	 @Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	 public void testRoomBookingPreparationTimeAndCleanupNotConsidered() throws IOException {

		 Reporter.log("<span style='font-weight:bold;color:#000033'> "
				 +"C118063: Preparation and Cleanup time should not be considered while calculating Total Cost in Equipment reservation"
				 + "C115779: In Equipment Reservation, cost for Reservable Equipment and additional cost for added Equipment, Vehicles & Parking are displayed in Summary", true);

		 SoftAssert softAssert = new SoftAssert();
		 softAssert.setMethodName("testRoomBookingPreparationTimeAndCleanupNotConsidered");

		 int random = (int)(Math.random() * 10)+24;
		 String region = "1preRgRef";
		 String date = this.getFutureDate(random);
		 String from = "18:00";
		 String until = "19:00";
		 String equipResv = "preEqDefRef";
		 String equipResv1 = "2preRsEqRef";
		 String vehicles = "aqavehicleref";
		 String parking = "prePrkDefRef";
		 String prepTime = "00:30";
		 String cleanupTime = "00:30";
		 String responsible=getUserLastNameOfLoggedInUser();

		 expandAdministration();
		 waitForExtJSAjaxComplete(20);

		 expandSubMainMenu("Reservation");
		 waitForExtJSAjaxComplete(20);

		 waitAndClick(XPATH_NEWRESERVATIONS);
		 waitForExtJSAjaxComplete(20);
		 waitForExtJSAjaxComplete(20);

		 //C118063
		 setRegion(region);
		 setDate(date);
		 setTimeFrom(from);
		 setTimeUntil(until);
		 waitForExtJSAjaxComplete(20);

		 clickEquipmentTab();
		 waitForExtJSAjaxComplete(5);

		 clickSearch();
		 waitForExtJSAjaxComplete(20);
		 waitForExtJSAjaxComplete(20);

		 clickLaunchReservation(equipResv);
		 waitForExtJSAjaxComplete(10);

		 setTimePrepareFromReservationPanel(prepTime);
		 waitForExtJSAjaxComplete(10);

		 setTimeCleanupFromReservationPanel(cleanupTime);
		 waitForExtJSAjaxComplete(10);

		 setResponsible(responsible);
		 waitForExtJSAjaxComplete(10);

		 clickSummaryTab();
		 waitForExtJSAjaxComplete(20);

		 String getSummaryText = getItemDetailsFromReservationSummary(equipResv);
		 System.err.println(getSummaryText);
		 softAssert.assertTrue( getSummaryText.contains("1 h x 4.00 EUR"),"Total cost is calculated without considering Prepare/Cleanup time");
		 System.err.println(getSummaryTitle());		
		 softAssert.assertTrue(getSummaryTitle().contains("4.04 EUR"),"summary title is ok before adding additional Equipment, Vehicles & Parking");

		 //C115779
		 clickLaunchReservation(equipResv1);
		 waitForExtJSAjaxComplete(10);

		 clickSummaryTab();
		 waitForExtJSAjaxComplete(20);

		 getSummaryText = getItemDetailsFromReservationSummary(equipResv1);
		 softAssert.assertTrue( getSummaryText.contains("0.04 d x 6.00 EUR"),"Total cost is calculated without considering Prepare/Cleanup time");

		 clickVehicleTab();
		 waitForExtJSAjaxComplete(20);

		 clickSearch();
		 waitForExtJSAjaxComplete(20);
		 waitForExtJSAjaxComplete(20);

		 clickLaunchReservation(vehicles);
		 waitForExtJSAjaxComplete(10);

		 clickSummaryTab();
		 waitForExtJSAjaxComplete(20);

		 getSummaryText = getItemDetailsFromReservationSummary(vehicles);
		 softAssert.assertTrue( getSummaryText.contains("1 h x 4.00 EUR"),"Total cost is calculated without considering Prepare/Cleanup time");

		 clickParkingTab();
		 waitForExtJSAjaxComplete(20);

		 clickSearch();
		 waitForExtJSAjaxComplete(20);
		 waitForExtJSAjaxComplete(20);

		 clickLaunchReservation(parking);
		 waitForExtJSAjaxComplete(10);

		 clickSummaryTab();
		 waitForExtJSAjaxComplete(20);

		 getSummaryText = getItemDetailsFromReservationSummary(parking);
		 softAssert.assertTrue( getSummaryText.contains("1 h x 4.00 EUR"),"Total cost is calculated without considering Prepare/Cleanup time");
		 softAssert.assertTrue(getSummaryTitle().contains("12.36 EUR"),"Cost for Reservable Equipment and additional cost for Equipment, Vehicles and Parking are displayed.");

		 Reporter.log("Preparation and Cleanup time should not be considered while calculating Total Cost in Equipment reservation", true);
		 softAssert.assertAll();
	 }

	 /**
	  * Testcase ID : C111811,C111812,C111814,C111815
	  * Author      : MMA
	  * @throws Exception
	  */
	 @Test(enabled=true, retryAnalyzer = RetryAnalyzer.class)
	 public void testaccountWithoutRightsToViewConfidentialRights() throws Exception{
		 Reporter.log("<span style='font-weight:bold;color:#000033'> "
				 +"C111811: Account without right to view confidential reservation, cannot see Responsible Person name for Equipment Reservation"
				 + "C111812: Account without right to view confidential reservation, cannot see Responsible Person name for Parking Reservation"
				 +"C111815: Account without right to view confidential reservation, cannot see Responsible Person name for vehicle Reservation"
				 + "C111814: Account without right to view confidential reservation, cannot see Responsible Person name for Service Reservation", true);

		 SoftAssert softAssert = new SoftAssert();
		 softAssert.setMethodName("testaccountWithoutRightsToViewConfidentialRights");

		 int random = (int)(Math.random() * 10)+24;
		 String region = "1preRgRef";
		 String date = this.getFutureDate(random);
		 String from = "18:00";
		 String until = "19:00";
		 String room = "1PreRoomRef";
		 String equipResv = "preEqDefRef";
		 String equipResv1 = "2preRsEqRef";
		 String vehicles = "aqavehicleref";
		 String parking = "prePrkDefRef";
		 String service = "1preSrvRef";
		 boolean isReservationDone = false;
		 String user = "aqa_selenium";
		 String password = "qwerty";
		 String calendarViewBytwentyFourBySeven="View 24/7";

		 String responsible=getUserLastNameOfLoggedInUser();

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

		 clickRoomTab();
		 waitForExtJSAjaxComplete(5);

		 clickSearch();
		 waitForExtJSAjaxComplete(20);
		 waitForExtJSAjaxComplete(20);

		 clickLaunchReservation(room);
		 waitForExtJSAjaxComplete(10);

		 clickEquipmentTab();
		 waitForExtJSAjaxComplete(5);

		 clickSearch();
		 waitForExtJSAjaxComplete(20);
		 waitForExtJSAjaxComplete(20);

		 clickLaunchReservation(equipResv);
		 waitForExtJSAjaxComplete(10);

		 clickLaunchReservation(equipResv1);
		 waitForExtJSAjaxComplete(10);

		 clickVehicleTab();
		 waitForExtJSAjaxComplete(20);

		 clickSearch();
		 waitForExtJSAjaxComplete(20);
		 waitForExtJSAjaxComplete(20);

		 clickLaunchReservation(vehicles);
		 waitForExtJSAjaxComplete(10);

		 clickParkingTab();
		 waitForExtJSAjaxComplete(20);

		 clickSearch();
		 waitForExtJSAjaxComplete(20);
		 waitForExtJSAjaxComplete(20);

		 clickLaunchReservation(parking);
		 waitForExtJSAjaxComplete(10);

		 clickServiceTab();
		 waitForExtJSAjaxComplete(20);

		 clickSearch();
		 waitForExtJSAjaxComplete(20);
		 waitForExtJSAjaxComplete(20);

		 clickLaunchReservation(service);
		 waitForExtJSAjaxComplete(10);

		 clickConfidential();
		 waitForExtJSAjaxComplete(20);

		 setResponsible(responsible);
		 waitForExtJSAjaxComplete(10);

		 try{
			 clickConfirmReservation();
			 waitForExtJSAjaxComplete(20);
			 waitForExtJSAjaxComplete(20);

			 softAssert.assertTrue(isReservationDone(), "Reservation is saved (Confirmed)");
			 String reservID1=getReserationID();

			 String reservIDWithoutString1=reservID1.replace("Reservation", "").trim();
			 isReservationDone = true;

			 navigateToMainPage(user,password) ;
			 waitForExtJSAjaxComplete(20);

			 //Navigate to Calendar tab
			 waitAndClick(XPATH_CALENDAR);
			 waitForExtJSAjaxComplete(20);
			 waitForMaskDisappear();

			 clickCalenderTab();
			 waitForExtJSAjaxComplete(20);
			 waitForMaskDisappear();

			 ReservationsCalendarNewPageObject resCalenderObj = new ReservationsCalendarNewPageObject();

			 //Equipment
			 resCalenderObj.clickEquipmentTabInCalendarTab();
			 waitForMaskDisappear();

			 resCalenderObj.setRegionInCalendarTab(region);
			 waitForExtJSAjaxComplete(20);
			 waitForMaskDisappear();

			 resCalenderObj.setDateViaDatePicker(date);
			 waitForExtJSAjaxComplete(20);
			 waitForMaskDisappear();

			 resCalenderObj.clickOnCalendarViews(calendarViewBytwentyFourBySeven);
			 waitForExtJSAjaxComplete(20);
			 waitForMaskDisappear();	

			 resCalenderObj.filterItemByName(equipResv);
			 waitForExtJSAjaxComplete(20);
			 waitForExtJSAjaxComplete(20);

			 String tooltipText = resCalenderObj.mouseOverOnReservationInCalender(equipResv,reservIDWithoutString1);
			 softAssert.assertFalse(tooltipText.contains(responsible)||!(tooltipText.contains("Reservation")));
		 }

		 //cancel Reservation
		 finally{

			 navigateToMainPage(configuration.getUserName(),configuration.getPassword()) ;
			 waitForExtJSAjaxComplete(20);

			 if(isReservationDone){

				 waitForExtJSAjaxComplete(20);

				 expandAdministration();
				 waitForExtJSAjaxComplete(20);

				 expandSubMainMenu("Reservation");
				 waitForExtJSAjaxComplete(20);

				 waitAndClick(XPATH_MYRESERVATIONS);
				 waitForExtJSAjaxComplete(20);
				 waitForExtJSAjaxComplete(20);

				 ReservationMyReservationsPageObject myReserv=new ReservationMyReservationsPageObject();
				 myReserv.setRegionMyReservation(region);
				 waitForExtJSAjaxComplete(20);

				 myReserv.setTodayDateInMyReservation(date);
				 waitForExtJSAjaxComplete(20);

				 myReserv.clickSearchButton();
				 waitForExtJSAjaxComplete(20);
				 waitForExtJSAjaxComplete(20);

				 checkRowInGriByTextValueAndClick(date+" "+from,equipResv);
				 waitForExtJSAjaxComplete(20);

				 clickViewCancel();
				 waitForExtJSAjaxComplete(20);

				 clickGeneralTab();
				 waitForExtJSAjaxComplete(20);

				 clickCancelReservation();
				 clickOnDialogButton("Yes");
				 waitForExtJSAjaxComplete(20);

				 softAssert.assertTrue(Grid.isRowInGridAbsent(driver,date+" "+from+"|"+equipResv,"@class", "grid3"),"Room reservation is cancelled");
			 }
		 }
		 Reporter.log("Account without right to view confidential reservation, cannot see Responsible Person name for Equipment Reservation", true);
		 softAssert.assertAll();
	 }

	 /**
	  * Testcase ID : C118426
	  * Author      : MMA
	  */
	 @Test(enabled=true, retryAnalyzer=RetryAnalyzer.class ) 
	 public void testEquipChangeFromOrderItemsTab() throws Exception{
		 Reporter.log("<span style='font-weight:bold;color:#000033'> "		
				 + "C118426: Reservable Equipment pertain to same Region can only be changed from Order Items pane", true);

		 SoftAssert softAssert = new SoftAssert();
		 softAssert.setMethodName("testEquipChangeFromOrderItemsTab");

		 int random = (int)(Math.random() * 10)+24;
		 String date = this.getFutureDate(random);
		 String region = "1preRgRef";
		 String from = "13:00";
		 String until = "14:00";
		 String equip1ofReg1pre = "2preRsEqRef";
		 String equip2ofReg1pre = "2aqaEqRef";
		 String equip1ofReg2pre = "3preEqRef";

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

		 clickEquipmentTab();
		 waitForExtJSAjaxComplete(5);

		 clickSearch();
		 waitForExtJSAjaxComplete(20);
		 waitForExtJSAjaxComplete(20);

		 clickLaunchReservation(equip1ofReg1pre);
		 waitForExtJSAjaxComplete(10);

		 clickOrderItemsTab();
		 waitForExtJSAjaxComplete(10);

		 clickDetailOrderItem();
		 waitForExtJSAjaxComplete(10);

		 clickLookup("@class","x-panel", "resource","Select Equipment");
		 waitForExtJSAjaxComplete(20);

		 softAssert.assertFalse(setValueGridLookupWithFiltersWithScrollInToView("@id", getXWindowId("Select Equipment"), equip1ofReg2pre, "Reference"),"equipment from another region is not present,Reservable Equipment pertained to same Region are only displayed in Select a Equipment window");
		 softAssert.assertTrue(setValueGridLookupWithFiltersWithScrollInToView("@id", getXWindowId("Select Equipment"), equip2ofReg1pre, "Reference"),"The Reservable Equipment is selected, equipment of same region is selected");
		 waitForExtJSAjaxComplete(20);

		 softAssert.assertFalse(orderItemExist(equip1ofReg1pre),"Initially selected equipment(orderitem doesnot exist)");
		 softAssert.assertTrue(orderItemExist(equip2ofReg1pre),"The Reservable Equipment is selected");

		 Reporter.log("Reservable Equipment pertain to same Region can only be changed from Order Items pane", true);
		 softAssert.assertAll();
	 }
	 
	 /**
	  * Testcase ID : C118559
	  * Author      : kve
	  */
	 @Test(enabled=true, retryAnalyzer=RetryAnalyzer.class ) 
	 public void testResourceForNonRoomReservation() throws Exception{
		 Reporter.log("<span style='font-weight:bold;color:#000033'> "		
				 + "C118559:  Resource that cannot be Reserved without a Room is not added to the Non-Room Reservation (MYM-26574) ", true);

		 SoftAssert softAssert = new SoftAssert();
		 softAssert.setMethodName("testResourceForNonRoomReservation");

		 int random = (int)(Math.random() * 10)+24;
		 String date = this.getFutureDate(random);
		 String region = "1preRgRef";
		 String from = "11:00";
		 String until = "12:00";
		 String equipment = "aqaEqRef";
		 String room = "2preRmRef";
		 String responsible=getUserLastNameOfLoggedInUser();
		 boolean isReservationDone = false;

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

		 clickEquipmentTab();

		 waitForExtJSAjaxComplete(5);

		 clickSearch();

		 waitForExtJSAjaxComplete(20);

		 waitForExtJSAjaxComplete(20);

		 boolean status=isDisplayLaunchReservation(equipment);

		 waitForExtJSAjaxComplete(20);

		 softAssert.assertTrue(status,"Equipment "+equipment+" is not displayed in the Search results");

		 waitForExtJSAjaxComplete(20);

		 clickRoomTab();

		 waitForExtJSAjaxComplete(5);

		 clickSearch();

		 waitForExtJSAjaxComplete(20);

		 clickLaunchReservation(room);

		 waitForExtJSAjaxComplete(20);

		 setResponsible(responsible);

		 waitForExtJSAjaxComplete(20);

		 waitForExtJSAjaxComplete(10);

		 clickOrderItemsTab();

		 clickAddOrderItemEquipment();

		 addOrderItem(equipment);

		 softAssert.assertTrue(orderItemExist(equipment), "order Item Equipment Exist ");

		 clickConfirmReservation();

		 waitForExtJSAjaxComplete(20);

		 if(isReservationDone = true){

			 waitForExtJSAjaxComplete(20);

			 expandAdministration();

			 waitForExtJSAjaxComplete(20);

			 expandSubMainMenu("Reservation");

			 waitForExtJSAjaxComplete(20);

			 waitAndClick(XPATH_MYRESERVATIONS);

			 waitForExtJSAjaxComplete(20);

			 waitForExtJSAjaxComplete(20);

			 ReservationMyReservationsPageObject myReserv=new ReservationMyReservationsPageObject();

			 myReserv.setRegionMyReservation(region);

			 waitForExtJSAjaxComplete(20);

			 myReserv.setTodayDateInMyReservation(date);
			 
			 waitForExtJSAjaxComplete(20);
			 
			 collapseNavigation();
			 
			 waitForExtJSAjaxComplete(20);

			 myReserv.clickSearchButton();

			 waitForExtJSAjaxComplete(20);

			 checkRowInGriByTextValueAndClick(date+" "+from,room);

			 waitForExtJSAjaxComplete(20);

			 clickViewCancel();

			 waitForExtJSAjaxComplete(20);

			 clickGeneralTab();

			 waitForExtJSAjaxComplete(20);

			 clickCancelReservation();

			 clickOnDialogButton("Yes");

			 waitForExtJSAjaxComplete(20);

		 }

		 softAssert.assertAll();

		 Reporter.log("Resource that cannot be Reserved without a Room is not added to the Non-Room Reservation was verified successfully", true);

	 }


}