package test.generalweb.reservation;




import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;




public class ReservationServiceCRUDTestSuite extends
		ReservationPageObject {
	/**
	 * Testcase ID	:	C74401,C74411
	 * Author		:	Intellias
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationServiceCreateDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74401: Book a Service <br>"
				+ "Test C74411: Add/Remove additional Service to Service Reservation </span> <br>", true);

		Reporter.log("User reserves service <br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String employee = getUserLastNameOfLoggedInUser();
		String contact = "1preContactLast";
		String company = "My Company";
		String first = "otherFrist";
		String last = "otherLast";
		
		String date = "11-02-20"+random;
		String from = "03:00";
		String fromEd = "03:15";
		String until = "03:30";
		
		String dateEd = "12-02-20"+random;
		
		String fromWrong = "04:00";
		
		String numberPeople = "2";
		String responsible = getUserLastNameOfLoggedInUser();
		String service = "1preSrvRef";
		String category = "1preSrvCtDescr";
		String service2 = "2preSrvRef";
		String department = "AQA_DEPARTMENT";
		
		String wrongDepartment = "2preDepartName"; 
		String department2 = "3preDepartName";

		String project = "1preProjectRef";

		String projectPart = "1prePrPartRef";
		
		String service3 = "5preSrvRef";
		
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReservationServiceCreateDelete");

		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//clickServiceTab();
		
		setRegion(region);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
		
		waitForExtJSAjaxComplete(10);
		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(5);
				
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'orderroomsbywrapper')]/div[contains(@class,'hide')]"),"sorting is not visible");
		
		softAssert.assertFalse(driver.findElements(By.xpath("//span[contains(text(),'"+service+"')]")).isEmpty(),"service "+service+ "is present after search");
		
		softAssert.assertFalse(driver.findElements(By.xpath("//span[contains(text(),'"+service2+"')]")).isEmpty(),"service "+service2+ "is present after search");
		
		setServiceCategory(category);
		
		setServiceName(service);
		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//span[contains(text(),'"+service2+"')]"),"service "+service2+ "is not present after search");
		
		clickLaunchReservation(service);
		
		waitForExtJSAjaxComplete(25);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(25);
		
		
//		c18696
		setProject(project);
			
		waitForExtJSAjaxComplete(25);
			
		softAssert.assertEquals(getProject(), project,"project was selected");
			
		setProjectPart(projectPart);
			
		waitForExtJSAjaxComplete(25);
			
		softAssert.assertEquals(getProject(), projectPart,"project part was selected");

		
		
		
// c18693
		softAssert.assertEquals(getDepartment(),department,"default department was autoselected");

// c18694		
		clickLookup("@class","mcsreservationedit", "department","Select a Department");
					
		waitForExtJSAjaxComplete(25);
						
		softAssert.assertFalse(setValueGridLookupWithFiltersWithValidation("@id", getXWindowId("Select a Department"), wrongDepartment, "Name"),"department from another company is not present");
				
		setExactValueGridLookupWithFiltersWithValidation("@id", getXWindowId("Select a Department"), department2, "Name");
				
		waitForExtJSAjaxComplete(25);
						
		clickOrderItemsTab();
		
		clickDetailOrderItem();
		
// c18691
		clickLookup("@class","x-panel", "resource","Select a Service");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertFalse(setValueGridLookupWithFiltersWithValidation("@id", getXWindowId("Select a Service"), service3, "Reference"),"service from another region is not present");
		
		setExactValueGridLookupWithFiltersWithValidation("@id", getXWindowId("Select a Service"), service, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		
		//c18700
		clickOrderItemsTab();
						
		clickAddOrderItemService();
						
		addOrderItem(service2);
						
		softAssert.assertTrue(orderItemExist(service2), "order Item Service Exist ");
						
		removeOrderItem(service2);
						
		softAssert.assertFalse(orderItemExist(service2), "order Item Service not Exist ");
		
		
		waitForExtJSAjaxComplete(25);
		
		clickParticipantTab();
		
		setTotalParticipants(numberPeople);
		
		addParticipantEmployee(employee);
		
		addParticipantContact(contact);
		
		addOtherPerson(first, last);
		
		softAssert.assertEquals(getTotalParticipants(),"3","number of participants automatically increased");
		
		clickRemoveParticipant();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getTotalParticipants(),"3","number of participants does not decrease when removed");
		
		Grid.checkRowInGriByTextValueAndClick(driver, company);
		
		clickRemoveParticipant();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getTotalParticipants(),"3","number of participants does not decrease when removed");

		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(25);
		
		setServiceCategory("");
		
		waitForExtJSAjaxComplete(25);
		
		setServiceName("");
		
		waitForExtJSAjaxComplete(25);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(service2);
		
		waitForExtJSAjaxComplete(25);
		
//c18692
		
		clickGeneralTab();
		
		setTimeFromReservationPanel(fromWrong);
		
		clickConfirmReservation();
				
		waitForExtJSAjaxComplete(25);
				
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

		waitAndClick(XPATH_MYRESERVATIONS);
		waitForExtJSAjaxComplete(20);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(20);
		
		  
		ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();
		    
		myReserv.setRegionMyReservation(region);
		    
		waitForExtJSAjaxComplete(25);
		    
		myReserv.setFutureDateMyReservation(date);
		    
		waitForExtJSAjaxComplete(25);
		    
		myReserv.clickSearchButton();
		    
		waitForExtJSAjaxComplete(25);
		  
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date+" "+from+"|"+service2,"@class", "grid3"),"service is reserved with service");

		checkRowInGriByTextValueAndClick(date+" "+from,service);
		
		waitForExtJSAjaxComplete(25);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(25);
		
		clickOrderItemsTab();
		
		clickDetailOrderItem();
		
//c18703
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
		
		setTimeFromReservationPanel(fromEd);
		
		waitForExtJSAjaxComplete(25);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(25);
		
		navigateToMainPage();
		
		waitForExtJSAjaxComplete(25);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_MYRESERVATIONS);
		  
		waitForExtJSAjaxComplete(20);

		waitForMaskDisappear();
		  
		myReserv.setRegionMyReservation(region);
		    
		waitForExtJSAjaxComplete(15);
		    
		myReserv.setFutureDateMyReservation(dateEd);
		    
		waitForExtJSAjaxComplete(10);
		    
		myReserv.clickSearchButton();
		    
		waitForExtJSAjaxComplete(25);
		  
		checkRowInGriByTextValueAndClick(dateEd+" "+fromEd,service);
		
		waitForExtJSAjaxComplete(25);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(25);
		
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
		
		waitAndClick(XPATH_MYRESERVATIONS);
		  
		waitForExtJSAjaxComplete(20);
		  
		waitForMaskDisappear();
		myReserv.setRegionMyReservation(region);
		    
		waitForExtJSAjaxComplete(25);
		    
		myReserv.setFutureDateMyReservation(date);
		    
		waitForExtJSAjaxComplete(25);
		    
		myReserv.clickSearchButton();
		    
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
//sometimes reservation can not be deleted or grid is not refreshed- browser cashe?	
		if(!isRowInGridAbsent(service, date+" "+from)){
			
			checkRowInGriByTextValueAndClick(date+" "+from,service);
			
			waitForExtJSAjaxComplete(25);
			
			clickViewCancel();
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(20);
			
			clickCancelReservation();
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			clickOnDialogButton("Yes");
			
			waitForExtJSAjaxComplete(20);
			
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
			    
			waitForExtJSAjaxComplete(25);
			    
			myReserv.setFutureDateMyReservation(date);
			    
			waitForExtJSAjaxComplete(25);
			    
			myReserv.clickSearchButton();
			    
			waitForExtJSAjaxComplete(25);
			
			
			softAssert.assertTrue(isRowInGridAbsent(service, date+" "+from),"Service reservation can be canceled");
			
		}
		
		softAssert.assertTrue(isRowInGridAbsent(service, date+" "+from),"Service reservation can be canceled");
		
		softAssert.assertAll();
		
		Reporter.log("Service was reserved and canceled", true);
	}

	/**
	 * Testcase ID	:	C18541
	 * Author		:	Intellias
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationServiceOnly() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Reserving service only: C18541 </span><br>", true);

		Reporter.log("User reserves service only<br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		
		String date = "12-01-20"+random;
		String from = "08:00";
		String until = "08:30";
		String responsible = getUserLastNameOfLoggedInUser();
		String service = "1preSrvRef";
		boolean isReservationDone=false;
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReservationServiceOnly");

		try{
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setRegion(region);
		
		waitForExtJSAjaxComplete(20);
		
		setDate(date);
		
		String reservDate= this.getDate();
		
		setTimeFrom(from);
		
		setTimeUntil(until);
		
		waitForExtJSAjaxComplete(10);
		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(5);
				
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(service);
		
		waitForExtJSAjaxComplete(25);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(10);
		
		clickConfirmReservation();
		
		isReservationDone=true;
		
		waitForExtJSAjaxComplete(25);
	}
		finally{
			deleteReservationFromMyReservation(date, from, service, isReservationDone);
		}
			
		softAssert.assertAll();
		
		Reporter.log("Service only was reserved", true);
	}

	/**
	 * Testcase ID	:	C74420
	 * Author		:	Intellias
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationTentativeService() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74420: Tentative Service Reservation also generate cost items </span><br>", true);

		Reporter.log("User reserves tentative service<br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		
		String date = "9-02-20"+random;
		String from = "11:00";
		String until = "11:30";
		String responsible =getUserLastNameOfLoggedInUser();
		String service = "preDerSrvRef";
		ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();
		boolean isReservationDone=false;
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReservationTentativeService");

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
		
		waitForExtJSAjaxComplete(25);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
		
		waitForExtJSAjaxComplete(10);
		
		String reservDate=getDate();
		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(10);
				
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(service);
		
		waitForExtJSAjaxComplete(25);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		
		clickTentative();
		
		waitForExtJSAjaxComplete(20);
		
		clickSummaryTab();
		
		softAssert.assertTrue(getSummaryTotalCost().contains("2.02 EUR"),"tentative service generates costs");
		
		softAssert.assertTrue(getSummaryTitle().contains("2.02 EUR"),"tentative service generates costs summary");
		
		clickConfirmReservation();
		
		isReservationDone=true;
	}
		finally{
			this.deleteReservationFromMyReservation(date, from, service, isReservationDone);
		}
		
		softAssert.assertAll();
		
		Reporter.log("Service tentative was reserved", true);
	}
	/**
	 * Testcase ID	:	C74410
	 * Author		:	Intellias
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationAdditionalCostService() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74410: In Service Reservation, cost for Reservable Service and additional cost for added Service are displayed in Summary </span><br>", true);

		Reporter.log("User reserves additional cost service<br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		
		String date = "15-05-20"+random;
		String from = "16:00";
		String until = "16:30";
		String responsible = getUserLastNameOfLoggedInUser();
		String service = "preDerSrvRef";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReservationAdditionalCostService");

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
		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(10);
				
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(service);
		
		waitForExtJSAjaxComplete(25);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(10);
		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(service);
		
		waitForExtJSAjaxComplete(25);
		
		clickSummaryTab();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(getSummaryTotalCost().contains("4.04 EUR"),"additional costs for room are ok");
		
		softAssert.assertTrue(getSummaryTitle().contains("4.04 EUR"),"summary title is ok");
		
		softAssert.assertTrue(getSummaryItemCost(service).contains("2.02 EUR"),"service cost is ok");
		
		softAssert.assertAll();
		
		Reporter.log("Additional cost service was succesfully reserved", true);
		
	}
	/**
	 * Testcase ID	:	C74417
	 * Author		:	Intellias
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testRecurrenceService() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74417: Book a Service with Recurrence for available slots </span><br>", true);

		Reporter.log("User reserves service recurrence<br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		

		String dateRec = "10-01-20"+random;
		String dateRec2 = "12-01-20"+random;
		
		String date = "11-01-20"+random;
		String from = "18:00";
		String until = "18:30";
		String responsible = getUserLastNameOfLoggedInUser();
		String service = "1preSrvRef";
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
		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(25);
				
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(service);
		
		waitForExtJSAjaxComplete(25);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(15);
		
		clickConfirmReservation();
		
		isReservationDone=true;
		
		waitForExtJSAjaxComplete(25);
		
		expandSubMainMenu("Reservation");
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//clickServiceTab();
		
		setRegion(region);
		
		setDate(dateRec);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
		
		waitForExtJSAjaxComplete(15);
		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(10);
				
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(service);
		
		waitForExtJSAjaxComplete(25);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(15);
				
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
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//div[contains(@class,'x-window')]//div[contains(@class,'x-grid3')]//div[contains(text(),'Unavailable:')]"), "unavailable period");
		
		softAssert.assertTrue(driver.findElements(By.xpath("//div[contains(@class,'x-window')]//div[contains(@class,'x-grid3')]//div[contains(text(),'Available')]")).size()==3, "3 available period");
		
		clickSetRecurence();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(service);
		
		waitForExtJSAjaxComplete(25);
		
		clickConfirmReservation();		
		
		waitForExtJSAjaxComplete(25);
		
		//TO DO: Services can be reserved, more than once 
		
/*//		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-window-dlg')]//span[contains(text(),'Unable to save')]"), "unable to save occurence");
		
//		clickOnDialogButton("OK");
		
//		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);		
		
//		clickGeneralRecurence();
		
//		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//div[contains(@class,'x-window')]//div[contains(@class,'x-grid3')]//div[contains(text(),'"+date+"')]"), "overlaped date is absent");
		
//		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-window')]//div[contains(@class,'x-grid3')]//div[contains(text(),'"+dateRec+"')]"), "reccurence date is present");
		
//		closeRecurrenceWindow();
*/		
		
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
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();
		  
		myReserv.setRegionMyReservation(region);
		  
		waitForExtJSAjaxComplete(15);
		  
		myReserv.setTodayDateInMyReservation(date);
		  
		waitForExtJSAjaxComplete(10);
		  
		myReserv.clickSearchButton();
		  
		waitForExtJSAjaxComplete(25);
		
		checkRowInGriByTextValueAndClick(date+" "+from,service);
		
		waitForExtJSAjaxComplete(25);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(25);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickCancelReservation();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(25);
		
		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(25);

		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();
		  
		myReserv.setRegionMyReservation(region);
		  
		waitForExtJSAjaxComplete(15);
		  
		myReserv.setFutureDateMyReservation(dateRec);
		  
		waitForExtJSAjaxComplete(10);
		  
		myReserv.clickSearchButton();
		
		waitForExtJSAjaxComplete(25);
		
		checkRowInGriByTextValueAndClick(dateRec+" "+from,service);
		
		waitForExtJSAjaxComplete(25);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(25);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickCancelReservation();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(25);
		
		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();
		  
		myReserv.setRegionMyReservation(region);
		  
		waitForExtJSAjaxComplete(15);
		  
		myReserv.setFutureDateMyReservation(dateRec2);
		  
		waitForExtJSAjaxComplete(10);
		  
		myReserv.clickSearchButton();
		
		waitForExtJSAjaxComplete(25);
		
		checkRowInGriByTextValueAndClick(dateRec2+" "+from,service);
		
		waitForExtJSAjaxComplete(25);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(25);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickCancelReservation();
		
		clickOnDialogButton("Yes");
		}else{
			System.out.println("Reservation is not done");
		}
	}
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertAll();
		
		Reporter.log("Additional cost service was succesfully reserved", true);
		
	}
	/**
	 * Testcase ID	:	C74408
	 * Author		:	Intellias
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationServiceContactPerson() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C74408 Contact & Other Participant Details are shown in Service Reservation and Bell symbol displaying  </span><br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "11-01-20"+random;
		String from = "11:30";
		String until = "12:00";
		
		
		String service = "1preSrvRef";
		
		
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReservationContactService");

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
		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(30);
		
		clickLaunchReservation(service);
		
		waitForExtJSAjaxComplete(25);
		
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
		
		waitForExtJSAjaxComplete(25);
		
		selectParticipant("firsnt");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertAll();
		
		Reporter.log("Service contact person was added", true);
	}

	/**
	 * Testcase ID	:	C74418,C74415
	 * Author		:	vpcc
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testConfidentialServiceBookingAndFinVals() throws Exception{
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"Test C74418: Confidential Service Reservation <br> "
				+"Test C74415 & C77076: In Service Reservation, lookup values for GL Account, Cost Center and all other Finkeys must be limited to one Fiscal Entity </span><br>", true);

		Reporter.log("User reserves service only<br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		
		String date = this.getFutureDate(random);
		String from = "08:00";
		String until = "08:30";
		String responsible = getUserLastNameOfLoggedInUser();
		String service = "1preSrvRef";
		boolean isReservationDone =false;
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testConfidentialServiceBookingAndFinVals");

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
		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(10);
				
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(service);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//no confidential check box is found when we book only service
		//clickConfidential();
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(25);
		
		clickTabInReservationDetailsSection("Financial");
		
		waitForExtJSAjaxComplete(25);
		
		List<String> finkey3List = new ArrayList<String>();
		finkey3List.add("3preValue");
		
		List<String> finkey4List = new ArrayList<String>();
		finkey4List.add("4preValue");
		
		List<String> glAccList = new ArrayList<String>();
		glAccList.add("Billing Rule Default");
		glAccList.add("myMCS Default GL Account");
	
		
		waitForExtJSAjaxComplete(25);
		
		List<String> glAccVals= getGLAccountLookUpValues();
		softAssert.assertEquals(glAccVals, glAccList , "Expected GL accounts are populated");
		clickCANCELXwindow();
		
		waitForExtJSAjaxComplete(25);
		
		List<String> finKey3Vals = getFinKey3LookUpValues();
		waitForExtJSAjaxComplete(25);
		softAssert.assertEquals(finKey3Vals, finkey3List , "Expected finkey3 List are populated");
		
		clickCANCELXwindow();
	
		
		waitForExtJSAjaxComplete(25);
		List<String> finKey4Vals = getFinKey4LookUpValues();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(finKey4Vals, finkey4List , "Expected finkey4 List are populated");
		
		clickCANCELXwindow();
		
		waitForExtJSAjaxComplete(25);
		
		clickConfirmReservation();
		
		isReservationDone=true;
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(isReservationDone(), "Confidential Reservation is confirmed");
		
		waitForExtJSAjaxComplete(25);
		
		String reservationID = getReserationID();
		softAssert.assertFalse(reservationID.isEmpty(),"Confidential Reservation is confirmed and Reservation ID "+ reservationID+"is generated.");
		
		waitForExtJSAjaxComplete(25);
		
		clickCancelReservationUsingJS();
		
		waitForExtJSAjaxComplete(25);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		  softAssert.assertAll();
		
		}
	
	/**
	  * Testcase ID : C74412/C119475 
	  * Author  : ssa
	  */
	 @Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	 public void testServiceResrvSummeryAndPrintTabs(){
	  
	  Reporter.log("<span style='font-weight:bold;color:#000033'> "

		 +"test:C74412/C119475: Summary and Printing are not available, if required field is not filled out for Service Reservation", true);

	  
	  String region = "1preRgRef";
	  
	  int random = (int)(Math.random() * 10)+18;
	  
	  String date = this.getFutureDate(random);
	  
	  String from = "01:00";
	  String until = "01:30";
	  String responsible = getUserLastNameOfLoggedInUser();
	  
	  String service = "1preSrvRef";
	  String summaryTabText= "A summary is not available for the currently entered data\n"
			  +"\n"+
			  "Please fill in the Reservation Responsible.\n"+
			  "\n"+"Please make sure you have filled out all mandatory fields.";
	 
	  
	  String  printIconWarningMsg ="Printing requires all changes to be saved first";
	  
	  SoftAssert softAssert = new SoftAssert();
	  
	  softAssert.setMethodName("testServiceResrvSummeryAndPrintTabs");

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
	    
	  waitForExtJSAjaxComplete(25);
	 	  
	  clickServiceTab();
	  
	  waitForExtJSAjaxComplete(25);

	  clickSearch();
	  
	  waitForExtJSAjaxComplete(25);
	  
	  clickLaunchReservation(service);
	  
	  waitForExtJSAjaxComplete(25);
	
	  waitForExtJSAjaxComplete(25);
	  
	  clickSummaryTab();
	  
	  waitForExtJSAjaxComplete(25);
	  
	  softAssert.assertEquals(getSummaryTabText(),summaryTabText,"'A summary is not available for the currently entered data' is display with a message to fill out required fields.");
	  
	  waitForExtJSAjaxComplete(25);
	  
	  clickPrintIconInSummarySection();
	  
	  waitForExtJSAjaxComplete(25);
	  
	  softAssert.assertTrue(verifyWarningDialogTextMessage(printIconWarningMsg),"'Printing requires all changes to be saved first' alert message is displayed.");
	  
	  this.clickOnDialogButton("OK");
	  
	  softAssert.assertAll();
	  
	  Reporter.log("Summary pane is display with a message to fill out required fields."
	  		+ "Alert message is displayed for print icon <br>", true);
	 }  
	
	 /**
	  * Testcase ID : C74409 
	  * Author  : KVE
	  */
	 @Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	 public void testDetailsOfAdditionalServiceContactOther(){

		 Reporter.log("<span style='font-weight:bold;color:#000033'> "
				 +"Test C74409: Details of additional Service and Contact & Other Participants are displayed in Service Reservation </span><br>", true);


		 String region = "1preRgRef";

		 int random = (int)(Math.random() * 10)+18;

		 String date = this.getFutureDate(random);

		 String from = "01:00";
		 String until = "01:30";
		 String service = "1preSrvRef";
		 String service1 = "aqaserviceref";
		 String serviceCode="aqaservicecode";
		 String serviceCategory="1preSrvCtDescr";
		 String serviceNotes="Service for automation";
		 String contact = "1preContactLast";
		 String first = "otherFrist";
		 String last = "otherLast";
		 String company = "My Company";



		 SoftAssert softAssert = new SoftAssert();

		 softAssert.setMethodName("testDetailsOfAdditionalServiceContactOther");

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

		 clickServiceTab();

		 waitForExtJSAjaxComplete(20);

		 clickSearch();

		 waitForExtJSAjaxComplete(20);

		 clickLaunchReservation(service);

		 waitForExtJSAjaxComplete(20);

		 this.clickOrderItemsTab();

		 clickAddOrderItemService();

		 addOrderItem(service1);

		 softAssert.assertTrue(orderItemExist(service1), "order Item Service Exist ");

		 clickItemInOrderItemsPane("service", service1);

		 waitForExtJSAjaxComplete(20);

		 String serviceserviceToolTipTextContent = getToolTipOfOrderItemInOrderItemsPane("service", service1);

		 String serviceToolTipText = removeHtmlTagsFromString(serviceserviceToolTipTextContent);

		 softAssert.assertEquals(getPropertyFromSring(serviceToolTipText,service1),"", "Reference is displayed correctly in service Tooltip");

		 softAssert.assertEquals(getPropertyFromSring(serviceToolTipText,"Category:"),serviceCategory, "category is displayed correctly  in  service  Tooltip");

		 softAssert.assertEquals(getPropertyFromSring(serviceToolTipText,"Code:"),serviceCode, "code is displayed correctly in  service Tooltip");

		 softAssert.assertEquals(serviceToolTipText.contains(serviceNotes),true, "Notes is displayed correctly  in service  Tooltip");

		 waitForExtJSAjaxComplete(20);

		 clickParticipantTab();

		 addParticipantContact(contact);

		 addOtherPerson(first, last);

		 Grid.checkRowInGriByTextValueAndClick(driver, company);

		 String lastName=getParticipantLastName();

		 String firstName=getParticipantFirstName();

		 String cmpy=getParticipantCompany();

		 waitForExtJSAjaxComplete(25);

		 List<String> lastNm=getParticipantContactLastNameValues();

		 List<String> firstNm=getParticipantContactFirstNameValues();

		 List<String> company1=getParticipantContactCompanyValues();

		 softAssert.assertTrue(lastNm.contains(lastName), "Participant Last Name is Displayed Correctly");

		 softAssert.assertTrue(firstNm.contains(firstName), "Participant First Name is Displayed Correctly");

		 softAssert.assertTrue(company1.contains(cmpy), "Participant company is Displayed Correctly");

		 softAssert.assertAll();

		 Reporter.log("Successfully Details of additional Service and Contact & Other Participants are displayed in Service Reservation <br>", true);

	 } 

	/**
	 * Testcase ID : C74413 Author : kve
	 **/
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class)
	public void testCostCenterValueInServiceReservation() {

		 Reporter.log("<span style='font-weight:bold;color:#000033'>"

			 +"Test: C74413:  Verify that Total Cost is correctly calculated and shown in summary for both Fixed and Variable Product or Service in Service reservation <br>", true);

		 String from = "13:00";
		 String until = "14:00";
		 String region = "1preRgRef";
		 String service = "preDerSrvRef"; //fixed price
		 String service1 = "1preServiceRef"; //Variable price
		 String responsible = "SELENIUM";
		 String from1 = "16:00";
		 String until1 = "17:00";
		 boolean isReservationDone=false;

		 int random = (int)(Math.random() * 10)+18;
		 String date = this.getFutureDate(random);

		 SoftAssert softAssert = new SoftAssert();

		 softAssert.setMethodName("testCostCenterValueInServiceReservation");

		 try{
		 expandAdministration();

		 waitForExtJSAjaxComplete(20);

		 expandSubMainMenu("Reservation");

		 waitForExtJSAjaxComplete(20);

		 waitAndClick(XPATH_NEWRESERVATIONS);

		 waitForExtJSAjaxComplete(20);

		 waitForExtJSAjaxComplete(20);

		 setRegion(region);

		 waitForExtJSAjaxComplete(20);

		 setDate(date);

		 setTimeFrom(from);

		 setTimeUntil(until);

		 waitForExtJSAjaxComplete(20);

		 clickServiceTab();

		 waitForExtJSAjaxComplete(10);

		 clickSearch();

		 waitForExtJSAjaxComplete(20);

		 clickLaunchReservation(service);

		 waitForExtJSAjaxComplete(20);

		 setResponsible(responsible);

		 waitForExtJSAjaxComplete(20);
		 
		 waitForExtJSAjaxComplete(20);

		 clickSummaryTab();

		 waitForExtJSAjaxComplete(20);

		 String getSummaryText = getItemDetailsFromReservationSummary(service);

		 softAssert.assertTrue( getSummaryText.contains("1 h x 4.00 EUR"),"Total cost is correctly calculated as per the linked Product or Service.");

		 softAssert.assertTrue(getSummaryTotalCost().contains("4.04 EUR"),"Total costs for service are ok");

		 driver.navigate().refresh();

		 waitForExtJSAjaxComplete(20);

		 expandAdministration();

		 waitForExtJSAjaxComplete(20);

		 expandSubMainMenu("Reservation");

		 waitForExtJSAjaxComplete(20);

		 waitAndClick(XPATH_NEWRESERVATIONS);

		 waitForExtJSAjaxComplete(20);

		 waitForExtJSAjaxComplete(20);

		 setRegion(region);

		 waitForExtJSAjaxComplete(20);

		 setDate(date);

		 setTimeFrom(from);

		 setTimeUntil(until);

		 clickServiceTab();

		 waitForExtJSAjaxComplete(10);

		 clickSearch();

		 waitForExtJSAjaxComplete(20);

		 clickLaunchReservation(service1);

		 waitForExtJSAjaxComplete(20);

		 waitForExtJSAjaxComplete(20);

		 setTimeFromReservationPanel(from);

		 waitForExtJSAjaxComplete(20);

		 setTimeUntilReservationPanel(until);

		 waitForExtJSAjaxComplete(20);

		 setResponsible(responsible);

		 waitForExtJSAjaxComplete(20);

		 clickSummaryTab();

		waitForExtJSAjaxComplete(20);

		String getSummaryText1 = getItemDetailsFromReservationSummary(service1);

		softAssert.assertTrue(getSummaryText1.contains("0.04 d x 6.00 EUR"),"Total cost is correctly calculated as per the linked Product or Service.");

		softAssert.assertTrue(getSummaryTotalCost().contains("0.24 EUR"),"Total costs for service are ok");

		clickGeneralTab();

		waitForExtJSAjaxComplete(20);

		setTimeFromReservationPanel(from1);

		waitForExtJSAjaxComplete(10);

		setTimeUntilReservationPanel(until1);
	
		waitForExtJSAjaxComplete(20);

		clickConfirmReservation();
		
		isReservationDone=true;
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);

		clickSummaryTab();
		
		waitForExtJSAjaxComplete(15);

		String getSummaryText2 = getItemDetailsFromReservationSummary(service1);

		softAssert.assertTrue(getSummaryText2.contains("0.04 d x 7.00 EUR"),"Total cost is correctly calculated as per the linked Product or Service.");

		softAssert.assertTrue(getSummaryTotalCost().contains("0.28 EUR"),"Total costs for service are ok");

	}
	finally{
		this.deleteReservationFromMyReservation(date, from1, service1, isReservationDone);
	}
		softAssert.assertAll();

		Reporter.log("Verify that Total Cost is correctly calculated and shown in summary Tab in Room reservation<br>",true);

	}

	/**Testcase ID	:	C122852 
	 * Author		:	kve
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testVerifyCostCenterInServiceBooking(){

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C122852 :Verify that Cost Center value is correctly updated in Service Reservations when changing the value from Cost Center Combo box </span><br>", true);


		String region = "1preRgRef";
		String responsible = "SELENIUM";
		int random = (int)(Math.random() * 10)+18;

		String date = this.getFutureDate(random);

		String from = "13:00";
		String until = "14:00";
		String service = "1preServiceRef";
		String project="1preProjectRef";
		String costCenterMethod1=  "From Responsible";
		String costCenterMethod2="From Department";
		String costCenterMethod3="From Project";
		String costCenterMethod4="Other";
		String costCenter = "myMCS Default Cost Center";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testVerifyCostCenterInServiceBooking");

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

		clickServiceTab();

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(service);

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

}
