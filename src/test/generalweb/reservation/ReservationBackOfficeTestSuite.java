package test.generalweb.reservation;

import java.util.Arrays;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;

public class ReservationBackOfficeTestSuite extends ReservationBackOfficePageObject{
	
	/* Testcase ID	:	C118627
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationDetailsWindowTabs() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C118627: Reservation Details window is launched on confirmed Reservation (MYM-26027))"
				+ "</span><br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "09-03-20"+random;
		String from = "06:00";
		String until = "07:00";
		String responsible = getUserLastNameOfLoggedInUser();
		String room = "2preRmRef";
		String password="qwerty";
		String calendarViewByWeek = "View Week";
		String calendarViewBytwentyFourBySeven = "View 24/7";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReservationDetailsWindowTabs");
	
			
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
		
		String reservDate=getDate();
		
		waitForExtJSAjaxComplete(20);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(room);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
	
		clickConfirmReservation();
		
		String reservID = getReserationID();

		String reservIDWithoutString = reservID.replace("Reservation", "").trim();
		
		softAssert.assertTrue(isReservationDone(), "Reservation is confirmed");
		
		boolean isReservationDone=true;
		List<String> items = Arrays.asList("New Reservation","My Reservation","Calendar");
		
		if(isReservationDone)
		{
			try{
			for (String item : items)
			{
				if(item=="My Reservation")
				{
					waitAndClick(XPATH_MYRESERVATIONS);
					
					waitForExtJSAjaxComplete(20);
					
					ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();
					  
					waitForExtJSAjaxComplete(20);
					
					myReserv.setRegionMyReservation(region);
					  
					waitForExtJSAjaxComplete(15);
					  
					myReserv.setFutureDateMyReservation(reservDate);
					  
					waitForExtJSAjaxComplete(10);
					  
					myReserv.clickSearchButton();
					  
					waitForExtJSAjaxComplete(20);
					
					checkRowInGriByTextValueAndClick(reservDate+" "+from,room);
					
					waitForExtJSAjaxComplete(20);
				
					clickViewCancel();
					
					waitForExtJSAjaxComplete(20);
				}else if(item=="Calendar")
				{
					waitForExtJSAjaxComplete(20);
					
					waitAndClick(XPATH_CALENDAR);

					waitForMaskDisappear();
					
					waitForExtJSAjaxComplete(20);

					clickCalenderTab();

					waitForMaskDisappear();

					waitForExtJSAjaxComplete(20);

					clickOnCalendarViews(calendarViewByWeek);

					waitForMaskDisappear();

					waitForExtJSAjaxComplete(20);

					clickOnCalendarViews(calendarViewBytwentyFourBySeven);

					waitForExtJSAjaxComplete(20);

					setDateViaDatePicker(date);

					waitForMaskDisappear();

					waitForExtJSAjaxComplete(20);

					setRegionInCalendarTab(region);
					
					waitForExtJSAjaxComplete(20);
					
					filterItemByName(room);
					
					waitForExtJSAjaxComplete(20);
					
					clickReservationInCalendar(room, reservIDWithoutString);
				
				}

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isButtonAvailable("More"), "More button is available");
		
		clickMoreButton();
		
		waitForExtJSAjaxComplete(20);
		
		resizeTheReservationDetailsWindow();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isTabPresent("General"), "Tab General is available");
		softAssert.assertTrue(isTabPresent("Recurrence"), "Tab Recurrence is available");
		softAssert.assertTrue(isTabPresent("Order Items"), "Tab Order Items is available");
		softAssert.assertTrue(isTabPresent("Conference Details"), "Tab Conference Details is available");
		softAssert.assertTrue(isTabPresent("Participants"), "Tab Participants is available");
		softAssert.assertTrue(isTabPresent("Cost Items"), "Tab Cost Items is available");
		softAssert.assertTrue(isTabPresent("History Reporting"), "Tab History Reporting is available");
		
		clickOnButton("General");
		
		waitForExtJSAjaxComplete(20);
		
		String postDate=getDateInReservationDetailsWindow();
		
		softAssert.assertEquals(postDate,date,"Reservation details are displayed");
		
		String preSubject=getGeneralSubjectInReservationDetails();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isButtonDisabled("Cancel"), "Button is disabled");
		
		setGeneralsubjectInReservationDetails("Rservation test");
	
		softAssert.assertFalse(isButtonDisabled("Cancel"), "Button is enabled");
		
		clickCancelButtonInReservationDetails();
		
		waitForExtJSAjaxComplete(20);
		
		String postSubject=getGeneralSubjectInReservationDetails();
		
		softAssert.assertEquals(preSubject,postSubject, "Subject is not saved after cancelled changes");
		
		clickCloseReservationDetailswindow();
		
		waitForExtJSAjaxComplete(20);
		
		clickCloseReservationUsingJS();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Close");
		
		waitForExtJSAjaxComplete(20);
			}
			}finally{
		//Cancel reservation
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();
		  
		waitForExtJSAjaxComplete(20);
		
		myReserv.setRegionMyReservation(region);
		  
		waitForExtJSAjaxComplete(15);
		  
		myReserv.setFutureDateMyReservation(reservDate);
		  
		waitForExtJSAjaxComplete(10);
		  
		myReserv.clickSearchButton();
		  
		waitForExtJSAjaxComplete(20);
		
		checkRowInGriByTextValueAndClick(reservDate+" "+from,room);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickCancelReservation();
		
		clickOnDialogButton("Yes");
		}
	}
		else{
			System.out.println("Reservation not booked");
		}
		
	softAssert.assertAll();
	
	Reporter.log("Reservation Details window is launched on confirmed Reservation verified (MYM-26027))", true);
		
	
	}
	

	/**Testcase ID	:	C117861
	 * author		:	kve
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testErrorMsgForEditOwnReservRight() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C117861:  Appropriate message is displayed on no right to edit own Reservations right (MYM-35462) "
				+ "</span><br>", true);


		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date1 = "09-03-20"+random;

		String date = "28-05-2018";
		String from = "13:00";
		String until = "14:00";
		String from1 = "15:00";
		String until1 = "16:00";
		String user="userEditAccess";
		String password="qwerty";
		String responsible =  getUserLastNameOfLoggedInUser();
		String room = "2preRmRef";
		String errorMsg = " - Responsible: You are not allowed to add a Reservation. Please contact your system administrator to grant you this right.";
		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testErrorMsgForEditOwnReservRight");


		navigateToMainPage(user,password);

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(10);

		//service: "preDerSrvRef", room: "2preRmRef", Responsible: LoggedInUser
		waitAndClick(XPATH_MYRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();

		waitForExtJSAjaxComplete(20);

		myReserv.setRegionMyReservation(region);

		waitForExtJSAjaxComplete(15);

		myReserv.setTodayDateInMyReservation(date);

		waitForExtJSAjaxComplete(10);

		myReserv.clickSearchButton();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		checkRowInGriByTextValueAndClick(date+" "+from,room);

		waitForExtJSAjaxComplete(20);

		clickViewCancel();

		waitForExtJSAjaxComplete(20);

		waitForMaskDisappear();

		softAssert.assertTrue(isButtonAvailable("More"), "More button is available");

		clickMoreButton();

		waitForExtJSAjaxComplete(20);

		clickOnButton("Order Items");

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isFieldDisabled("Room:"), "Room field is Disabled");
		softAssert.assertTrue(isFieldDisabled("Layout:"), "Layout field is Disabled");
		softAssert.assertTrue(isFieldDisabled("Purpose:"), "Purpose field is Disabled");
		softAssert.assertTrue(isFieldDisabled("From:"), "From field is Disabled");
		softAssert.assertTrue(isFieldDisabled("Until:"), "Until field is Disabled");

		clickOnButton("Cost Items");

		waitForMaskDisappear();

		softAssert.assertTrue(isButtonDisabled("Add"), "Room field is Disabled");
		softAssert.assertTrue(isButtonDisabled("Edit"), "Layout field is Disabled");
		softAssert.assertTrue(isButtonDisabled("Delete"), "Purpose field is Disabled");

		waitForExtJSAjaxComplete(20);

		waitForMaskDisappear();

		clickCloseReservationDetailswindow();

		waitForExtJSAjaxComplete(20);

		clickCloseReservationUsingJS();

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("Close");

		waitForExtJSAjaxComplete(20);

		//service: "preDerSrvRef", room: "2preRmRef", Responsible: SELENIUM AQA
		waitAndClick(XPATH_MYRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		myReserv.setRegionMyReservation(region);

		waitForExtJSAjaxComplete(15);

		myReserv.setTodayDateInMyReservation(date);

		waitForExtJSAjaxComplete(10);

		myReserv.clickSearchButton();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		checkRowInGriByTextValueAndClick(date+" "+from1,room);

		waitForExtJSAjaxComplete(20);

		clickViewCancel();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isButtonAvailable("More"), "More button is available");

		clickMoreButton();

		waitForExtJSAjaxComplete(20);

		clickOnButton("Order Items");

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isFieldDisabled("Room:"), "Room field is Disabled");
		softAssert.assertTrue(isFieldDisabled("Layout:"), "Layout field is Disabled");
		softAssert.assertTrue(isFieldDisabled("Purpose:"), "Purpose field is Disabled");
		softAssert.assertTrue(isFieldDisabled("From:"), "From field is Disabled");
		softAssert.assertTrue(isFieldDisabled("Until:"), "Until field is Disabled");

		clickOnButton("Cost Items");

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isButtonDisabled("Add"), "Room field is Disabled");
		softAssert.assertTrue(isButtonDisabled("Edit"), "Layout field is Disabled");
		softAssert.assertTrue(isButtonDisabled("Delete"), "Purpose field is Disabled");

		waitForExtJSAjaxComplete(20);

		waitForMaskDisappear();

		clickCloseReservationDetailswindow();

		waitForExtJSAjaxComplete(20);

		clickCloseReservationUsingJS();

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("Close");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setRegion(region);

		setDate(date1);

		setTimeFrom(from);

		setTimeUntil(until);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickRoomTab();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(room);

		waitForExtJSAjaxComplete(20);
		
		//waitForMaskDisappear();

		//setResponsible(responsible);

		waitForExtJSAjaxComplete(20);

		clickConfirmReservation();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(getWarningDialogTextMsg().contains(errorMsg),"error message is displayed.");

		clickOnDialogButton("OK");

		waitForExtJSAjaxComplete(20);

		clickCloseReservationUsingJS();

		waitForExtJSAjaxComplete(10);

		clickOnDialogButton("Close");

		waitForExtJSAjaxComplete(20);

		softAssert.assertAll();

		Reporter.log("Successfully Appropriate message is displayed on no right to edit own Reservations right (MYM-35462) ", true);

	}
	
	/* Testcase ID	:	C118621
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationBackOfficeLicense() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C118621: Reservation Details button is not displayed with no BackOffice License (MYM-26027)"
				+ "</span><br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "9-03-20"+random;
		String from = "06:00";
		String until = "06:30";
		String responsible = getUserLastNameOfLoggedInUser();
		String room = "1preRmRef";
		String password="qwerty";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReservationBackOfficeLicense");
		
		 List<String> userNames=Arrays.asList("aqa_reservations","aqa_Defaultathen");
	
		for(String user:userNames)
		{
			
		navigateToMainPage(user,password);
		
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
				
		waitForExtJSAjaxComplete(20);
		
		String reservDate=getDate();
		
		waitForExtJSAjaxComplete(20);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(room);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
	
		clickConfirmReservation();
		
		boolean isReservationDone=true;
		
		if(isReservationDone)
		{
		softAssert.assertTrue(isReservationDone(), "Reservation is confirmed");

		waitForExtJSAjaxComplete(20);
		if(user=="aqa_reservations")
		{
		softAssert.assertTrue(isButtonAvailable("More"), "More button is available");
		}
		else{
			softAssert.assertFalse(isButtonAvailable("More"), "More button is not available");
		}
		
		clickCloseReservationUsingJS();
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();
		  
		waitForExtJSAjaxComplete(20);
		
		myReserv.setRegionMyReservation(region);
		  
		waitForExtJSAjaxComplete(15);
		  
		myReserv.setFutureDateMyReservation(reservDate);
		  
		waitForExtJSAjaxComplete(10);
		  
		myReserv.clickSearchButton();
		  
		waitForExtJSAjaxComplete(20);
		
		checkRowInGriByTextValueAndClick(reservDate+" "+from,room);
		
		waitForExtJSAjaxComplete(20);
		
		if(user=="aqa_reservations")
		{
		softAssert.assertTrue(isButtonAvailable("More"), "More button is available");
		}
		else{
			softAssert.assertFalse(isButtonAvailable("More"), "More button is not available");
		}
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickCancelReservation();
		
		clickOnDialogButton("Yes");
		}
		else{
			System.out.println("Reservation not confirmed");
		}
		}
		
		softAssert.assertAll();
		
		Reporter.log("Reservation back office license is verified", true);
	}
		
}



