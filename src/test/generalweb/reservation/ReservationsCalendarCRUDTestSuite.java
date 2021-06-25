/**
 * Class for Calendar tab related test
 * @author vpcc
 */
package test.generalweb.reservation;

import java.awt.AWTException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;


public class ReservationsCalendarCRUDTestSuite extends ReservationsCalendarNewPageObject {

	/**
	 * Testcase ID	:	C74461,C74462,C74463,C74464,C74401,C74411
	 * author		:	vpcc
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testViewReservationEntitiesInCalendarTab() throws Exception{
		
		String roomRef = "3preRmRef";
		String parkingRef = "3prePrkResRef";
	//	String calendarGridClass = "sch-schedulerpanel";
		String regions = "All Regions";
		String roomTelNo ="123";
		String defaultCapacity="8";
		String code = "3preRmCod";
		String category ="1preRmCtDescr";
		String notes = "test notes for 3";
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testViewReservationEntitiesInCalendarTab");
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"Test C74461:View Reservable Room details in Calendar tab <br>"
				+"Test C74462:View Reservable Equipment details in Calendar tab <br>"
				+"Test C74463:View Reservable Vehicle details in Calendar tab <br>"
				+"Test C74464:View Reservable Parking details in Calendar tab <br> "
				+ "Test: Reserving service: C74401, C74411 </span><br>", true);

		
		expandAdministration();
		
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_CALENDAR);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickCalenderTab();
		
		waitForExtJSAjaxComplete(15);
		
		waitForMaskDisappear();
		
		clickRoomsTabInCalendarTab();
		
		waitForExtJSAjaxComplete(10);
		
		setRegionInCalendarTab(regions);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickCalendarGridRowByName(roomRef);
		
		waitForExtJSAjaxComplete(20);

		String popUpConent = getReservationsPopUpContent();
		
		System.out.println(popUpConent);
		
		softAssert.assertEquals(getReservationsRefFromPopUpContent(),roomRef, "Reference is displayed correctly");
		softAssert.assertEquals(getPropertyFromSring(popUpConent,"Telephone Number:"),roomTelNo, "Telephone number is displayed correctly");
		softAssert.assertEquals(getPropertyFromSring(popUpConent,"Category:"),category, "category is displayed correctly");
		
		//TO DO: Code is removed form the popup content
		//softAssert.assertEquals(getPropertyFromSring(popUpConent,"Code:"),code, "code is displayed correctly");
		softAssert.assertEquals(getPropertyFromSring(popUpConent,"Default Capacity:"),defaultCapacity, "default Capacity is displayed correctly");
		softAssert.assertEquals(popUpConent.contains(notes),true, "Notes is displayed correctly");
		
		clickCloseInReservationsPopupWindow();
		
		waitForExtJSAjaxComplete(20);
		//********************************************
		clickParkingTabInCalendarTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		setRegionInCalendarTab(regions);
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		clickCalendarGridRowByName(parkingRef);
		
		waitForExtJSAjaxComplete(20);
		
		popUpConent = getReservationsPopUpContent();
		
		String parkingCode = "3prePrkResCod";
		String parkingCategory = "3prePrkCtRef";
		String parkingDepot = "2preDpRef";
		
		softAssert.assertEquals(getReservationsRefFromPopUpContent(),parkingRef, "Reference is displayed correctly");
		softAssert.assertEquals(getPropertyFromSring(popUpConent,"Category:"),parkingCategory, "parking Category is displayed correctly");
		//softAssert.assertEquals(getPropertyFromSring(popUpConent,"Code:"),parkingCode, "parking Code is displayed correctly");
		softAssert.assertEquals(getPropertyFromSring(popUpConent,"Depot:"),parkingDepot, "parking Depot is displayed correctly");
		
		clickCloseInReservationsPopupWindow();
		
		waitForExtJSAjaxComplete(20);
		
		//********************************************
		clickVehiclesTabInCalendarTab();
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		setRegionInCalendarTab(regions);
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		String vehicleReference = "3preRsVhRef";
		String vehicleCode= "3preRsVhCod";
		String vehicleCategory= "3preCtVhRef";
		String vehicleDepot = "2preDpRef";
		
		clickCalendarGridRowByName(vehicleReference);
		
		waitForExtJSAjaxComplete(20);
		
		popUpConent = getReservationsPopUpContent();
		
		softAssert.assertEquals(getReservationsRefFromPopUpContent(),vehicleReference, "Reference is displayed correctly");
		softAssert.assertEquals(getPropertyFromSring(popUpConent,"Category:"),vehicleCategory, "vehicle Category is displayed correctly");
		//softAssert.assertEquals(getPropertyFromSring(popUpConent,"Code:"),vehicleCode, "vehicle Code is displayed correctly");
		softAssert.assertEquals(getPropertyFromSring(popUpConent,"Depot:"),vehicleDepot, "vehicle Depot is displayed correctly");
		
		clickCloseInReservationsPopupWindow();
		
		waitForExtJSAjaxComplete(20);
		
		//**************************************************
		clickEquipmentTabInCalendarTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		setRegionInCalendarTab(regions);
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		String equipReference = "3preEqRef";
		String equipCode= "3preEqRef";
		String equipCategory= "1preGnEqRef";
		String equipDepot = "2preDpRef";
		
		clickCalendarGridRowByName(equipReference);
		
		waitForExtJSAjaxComplete(20);
		
		popUpConent = getReservationsPopUpContent();
		
		softAssert.assertEquals(getReservationsRefFromPopUpContent(),equipReference, "Reference is displayed correctly");
		softAssert.assertEquals(getPropertyFromSring(popUpConent,"Category:"),equipCategory, "equipment Category is displayed correctly");
		//softAssert.assertEquals(getPropertyFromSring(popUpConent,"Code:"),equipCode, "equipment Code is displayed correctly");
		softAssert.assertEquals(getPropertyFromSring(popUpConent,"Depot:"),equipDepot, "equipment Depot is displayed correctly");
		
		clickCloseInReservationsPopupWindow();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
	}
	
	
	/**
	 * Testcase ID	:	C74460,C74457,C74458,C74465,C74466
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testViewUpdateCancelRoomReservationInCalenderTab() throws Exception{
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"Test C74460:Select a time range and make reservation in Calendar tab for a specific Room, Equipment, Vehicle or Parking<br>"
				+"Test C74457: View reservations in Calendar tab<br>"
				+"Test C74458: View or edit a reservation from Calendar tab <br>"
				+"Test C74465: Update a reservation from Calendar tab <br> "
				+"Test:C74466: Cancel a reservation from Calendar tab  </span><br>", true);

		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "13-04-2020";
		String from = "03:00";
		String subject="Room reservation";
		String until = "04:00";
		String updateCleanUpTime = "00:25";
		String numberPeople = "2";
		String responsible = getUserLastNameOfLoggedInUser();
		String room = "2preRmRef";
		String category = "1preRmCtDescr";
		String purpose = "1preRmRsPrRef";
		String layout = "1preRmCmCod";
		String calendarViewByWeek="View Week";
		String calendarViewBytwentyFourBySeven="View 24/7";
		String regions = "All Regions";
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testViewUpdateCancelRoomReservationInCalenderTab");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setRegion(region);
		
		waitForExtJSAjaxComplete(15);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(5);
		
        String reservDate= getDate();
        
        //System.out.println(reservDate);
        
		waitForExtJSAjaxComplete(5);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(20);
		
		//setRoomCategory(category);

		//setNrPeople(numberPeople);
		
		//setRoomPurpose(purpose);
		
		//setRoomLayout(layout);

		//waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(room);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isReservationDone(), "Reservation is confirmed");
		
		String reservID=getReserationID();
		
		String reservIDWithoutString=reservID.replace("Reservation", "").trim();
		
		softAssert.assertTrue(getReserationID().matches("Reservation [0-9]+"), "Reservation <ID> is present in edit reservation Window Header");
		
		waitForExtJSAjaxComplete(20);
		
		clickCloseReservationUsingJS();
		
		waitForExtJSAjaxComplete(20);
		
		//Navigate to Calendar tab
		waitAndClick(XPATH_CALENDAR);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickCalenderTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		clickRoomsTabInCalendarTab();
		
		waitForExtJSAjaxComplete(20);
		
		setRegionInCalendarTab(regions);
		
		waitForMaskDisappear();
	
		waitForExtJSAjaxComplete(20);
		
		setDateViaDatePicker(reservDate);
		
		waitForExtJSAjaxComplete(20);
		
		//Switch on two tabs
		clickOnCalendarViews(calendarViewBytwentyFourBySeven);
		
		waitForExtJSAjaxComplete(20);
		
		//TODO: Headers are removed from the calendar page
		//List<String> actualDateRanges = getCalendarGridDateRangesFromHeader();
		
		//System.err.println(actualDateRanges);
		
		//List<String> ExpectedDateRanges = getAllDatesInWeek("EEE dd/MM",reservDate);
		
		//System.err.println(ExpectedDateRanges);
		
		//List<String> dateRanges = getCalendarGridDateRangesFromHeader();
		
		//softAssert.assertEquals(ExpectedDateRanges, actualDateRanges,"Calendar is displayed for the Reservable equipment pertain to the selected region for the week from Monday to Sunday as per the date specified");
		
		List<String> ActualTimeRanges = getCalendarGridTimeRangeFromHeader("true","true");
		
		System.err.println(ActualTimeRanges);
		
		waitForExtJSAjaxComplete(20);
		
		String ExpTimesRange[] = new String[]{"Mon 13", "Tue 14", "Wed 15", "Thu 16", "Fri 17", "Sat 18", "Sun 19"};
		boolean b240Format= Arrays.equals(ExpTimesRange,ActualTimeRanges.toArray());
		boolean b024Format = false;
		
		if(!b240Format){	
		String ExpTimesRanges[] = new String[]{"23\n"+
		                                        "00\n"+
		                                        "Mon 13", "23\n"+
		                                        "00\n"+
		                                        "Tue 14", "23\n"+
		                                        "00\n"+
		                                        "Wed 15", "23\n"+
		                                        "00\n"+
		                                        "Thu 16", "23\n"+
		                                        "00\n"+
		                                        "Fri 17", "23\n"+
		                                        "00\n"+
		                                        "Sat 18", "23\n"+
		                                        "00\n"+
		                                        "Sun 19"};
		
		b024Format = Arrays.equals(ExpTimesRanges,ActualTimeRanges.toArray());
		}
		
		
		softAssert.assertTrue((b240Format||b024Format),"Calendar is displayed for the Reservable vehicle pertain to the selected region from 00:00 to 24:00 hrs as per the date specified");
		
		//Switch off "View Week"
		clickOnCalendarViews(calendarViewByWeek);
		
		waitForExtJSAjaxComplete(20);
		
		//String dateForm=formatDateInToRequiredFormat(reservDate, "EEE dd/MM");
		
		//List<String> dateRangeViewWeekOff = getCalendarGridDateRangesFromHeader();
		
		//waitForExtJSAjaxComplete(20);
		
		//List <String> expecteddateRangeViewWeekOff = new ArrayList<String>();
		//expecteddateRangeViewWeekOff.add(dateForm);
		
		//softAssert.assertEquals(dateRangeViewWeekOff, expecteddateRangeViewWeekOff,"Calendar is displayed for the Reservable equipment pertain to the selected region for week day for the specified date");
		
		List<String>  hoursInDay=getListOfHoursInDay();
		
		System.err.println(hoursInDay);
		
		List<String> totalHoursInDay = getCalendarGridTimeRangeFromHeader("false","true");
		
		System.err.println(totalHoursInDay);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(totalHoursInDay, hoursInDay,"Calendar is displayed for the Reservable equipment pertain to the selected region from 00:00 to 24:00 hrs for the specified date");
		
		//Switch off Two Tabs
		clickOnCalendarViews(calendarViewBytwentyFourBySeven);
		
		waitForExtJSAjaxComplete(20);
		
		//String dateFormmat=formatDateInToRequiredFormat(reservDate, "EEE dd/MM");
		
		
		//List <String> expecteddateRangeViewTabsOff = new ArrayList<String>();
		//expecteddateRangeViewTabsOff.add(dateFormmat);
		//List<String> dateRangeViewTabsOff = getCalendarGridDateRangesFromHeader();
		
		//waitForExtJSAjaxComplete(20);
		
		//softAssert.assertEquals(dateRangeViewTabsOff, expecteddateRangeViewTabsOff,"Calendar is displayed for the Reservable equipment pertain to the selected region for week day for the specified date ");
		
		List<String>  expectdWorkinghoursInDay=getListOfWorkingHoursInDay();
		
		System.err.println(expectdWorkinghoursInDay);
		
		List<String> workingHoursInDay = getCalendarGridTimeRangeFromHeader("false","false");
		
		System.err.println(workingHoursInDay);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(workingHoursInDay, expectdWorkinghoursInDay,"Calendar is displayed for the Reservable equipment pertain to the selected region from 00:00 to 19:00 hrs for the specified date ");
		
		//Switch off View 24/7
		clickOnCalendarViews(calendarViewByWeek);
		
		waitForExtJSAjaxComplete(20);
		
		//List<String> dateRangeViewTwentyFourBySevenOff=getCalendarGridDateRangesFromHeader();
		
		//waitForExtJSAjaxComplete(20);
		
		//List <String> expecteddateRangeViewTwentyFourBySeven = getWorkingDaysInWeek("EEE dd/MM",reservDate);
		
		//softAssert.assertEquals(dateRangeViewTwentyFourBySevenOff, expecteddateRangeViewTwentyFourBySeven,"Calendar is displayed for the Reservable equipment pertain to the selected region from the week from Monday to Friday as per the date specified");
	
		waitForExtJSAjaxComplete(20);
		
		List<String> workingHoursInDayofTwentyFourBySeven = getCalendarGridTimeRangeFromHeader("true","false");
		
		System.out.println(workingHoursInDayofTwentyFourBySeven);
		
		waitForExtJSAjaxComplete(20);
		
		String ExpWrkingHours[] = new String[]{"Mon 13", "Tue 14", "Wed 15", "Thu 16", "Fri 17"};
		boolean b178Format= Arrays.equals(ExpWrkingHours,workingHoursInDayofTwentyFourBySeven.toArray());
		boolean b817Format = false;
		
		if(!b178Format){	
			String ExpWrkingHoursInDayofTwentyFourBySeven[]=new String[] {"17\n"+
					"08\n"+
					"Mon 13", "17\n"+
					"08\n"+
					"Tue 14","17\n"+
					"08\n"+
					"Wed 15", "17\n"+
					"08\n"+
					"Thu 16", "17\n"+
					"08\n"+
					"Fri 17"};
				     
		b817Format = Arrays.equals(ExpWrkingHoursInDayofTwentyFourBySeven,workingHoursInDayofTwentyFourBySeven.toArray());
		}

		softAssert.assertTrue((b178Format||b817Format),"Calendar is displayed for the Reservable vehicle pertain to the selected region from 00:00 to 19:00 hrs as per the date specified");		
		
		waitForExtJSAjaxComplete(20);
		
		filterItemByName(room);

		waitForExtJSAjaxComplete(20);

		//clickOnCalendarViews(calendarViewByWeek);

		waitForMaskDisappear();
		
		clickOnCalendarViews(calendarViewBytwentyFourBySeven);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(10);
				
		clickReservationInCalendar(room,reservIDWithoutString);
		
		waitForExtJSAjaxComplete(20);
		
		
		//reservDate+" "+from+"|"+room
		//softAssert.assertTrue(isRowInGridPresent(driver, room, "@class", "grid3"),"equipment is reserved with equipment");
		
		//checkRowInGriByTextValueAndClick(room);
		
		//waitForExtJSAjaxComplete(20);
		
		//clickViewCancel();
		
		// Edit and update Room reservation in calendar tab
		waitForExtJSAjaxComplete(20);
		clickOrderItemsTab();
		waitForExtJSAjaxComplete(10);
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		setCleanUpTimeGeneral(updateCleanUpTime);
		
		clickGeneralSubject();
		
		waitForExtJSAjaxComplete(15);
		
		setGeneralSubject(subject);
		
		waitForExtJSAjaxComplete(10);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getCleanUpTimeGeneral(),updateCleanUpTime,"Edit and updated room reservation in calendar tab");
		
		waitForExtJSAjaxComplete(20);

		//clickViewCancel();

		//waitForExtJSAjaxComplete(20);

		clickGeneralTab();

		waitForExtJSAjaxComplete(20);

		clickCancelReservation();

		clickOnDialogButton("Yes");
		
		//clickParkingTabInCalendarTab();
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickRoomsTabInCalendarTab();
		
		waitForMaskDisappear();
		
		filterItemByName(room);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(isReservationCancelled(room,reservIDWithoutString),"Room reservation is cancelled");
		
		softAssert.assertAll();
		
		Reporter.log("View, Edit, Update and cancelled the Room reservation successfully in calendar tab", true);
		
	}
	
	
	/**
	 * Testcase ID	:	C74460,C74457,C74458,C74465,C74466
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testViewUpdateCancelParkingReservationInCalenderTab() throws Exception{
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"Test C74460:Select a time range and make reservation in Calendar tab for a specific Room, Equipment, Vehicle or Parking<br>"
				+"Test C74457: View reservations in Calendar tab<br>"
				+"Test C74458:View or edit a reservation from Calendar tab <br>"
				+"Test C74465:Update a reservation from Calendar tab <br> "
				+"Test:C74466:Cancel a reservation from Calendar tab  </span><br>", true);

		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "13-04-2020";
		String from = "01:00";
		String subject="Parking reservation";
		String until = "02:00";
		String updateCleanUpTime = "00:15";
		String responsible = getUserLastNameOfLoggedInUser();
		String parking = "2prePrkResRef";
		String calendarViewByWeek="View Week";
		String calendarViewBytwentyFourBySeven="View 24/7";
		String regions = "All Regions";
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testViewUpdateCancelParkingReservationInCalenderTab");
		
	
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
		
        String reservDate= getDate();
        
        //System.out.println(reservDate);
        
		waitForExtJSAjaxComplete(20);
		
		clickParkingTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(parking);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		String reservID=getReserationID();
		
		String reservIDWithoutString=reservID.replace("Reservation", "").trim();
		
		softAssert.assertTrue(getReserationID().matches("Reservation [0-9]+"), "Reservation <ID> is present in edit reservation Window Header");
		
		waitForExtJSAjaxComplete(20);
		
		clickCloseReservationUsingJS();
		
		waitForExtJSAjaxComplete(20);
		//Navigate to Calendar tab
		waitAndClick(XPATH_CALENDAR);
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		clickCalenderTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		clickParkingTabInCalendarTab();
		
		waitForMaskDisappear();
		
		setRegionInCalendarTab(regions);
		
		waitForMaskDisappear();
		
		setDateViaDatePicker(reservDate);
		
		waitForExtJSAjaxComplete(20);
		
		//Switch on two tabs
		clickOnCalendarViews(calendarViewBytwentyFourBySeven);
		
		waitForExtJSAjaxComplete(20);
		
		//TODO: Headers are removed from the calendar page
		//List<String> actualDateRanges = getCalendarGridDateRangesFromHeader();
		
		//System.err.println(actualDateRanges);
		
		//List<String> ExpectedDateRanges = getAllDatesInWeek("EEE dd/MM",reservDate);
		
		//System.err.println(ExpectedDateRanges);
		
		//List<String> dateRanges = getCalendarGridDateRangesFromHeader();
		
		//softAssert.assertEquals(ExpectedDateRanges, actualDateRanges,"Calendar is displayed for the Reservable equipment pertain to the selected region for the week from Monday to Sunday as per the date specified");
		
		List<String> ActualTimeRanges = getCalendarGridTimeRangeFromHeader("true","true");
		
		waitForExtJSAjaxComplete(20);
		
		String ExpTimesRange[] = new String[]{"Mon 13", "Tue 14", "Wed 15", "Thu 16", "Fri 17", "Sat 18", "Sun 19"};
		boolean b240Format= Arrays.equals(ExpTimesRange,ActualTimeRanges.toArray());
		boolean b024Format = false;
		
		if(!b240Format){	
		String ExpTimesRanges[] = new String[]{"23\n"+
                "00\n"+
                "Mon 13", "23\n"+
                "00\n"+
                "Tue 14", "23\n"+
                "00\n"+
                "Wed 15", "23\n"+
                "00\n"+
                "Thu 16", "23\n"+
                "00\n"+
                "Fri 17", "23\n"+
                "00\n"+
                "Sat 18", "23\n"+
                "00\n"+
                "Sun 19"};
		b024Format = Arrays.equals(ExpTimesRanges,ActualTimeRanges.toArray());
		}

		softAssert.assertTrue((b240Format||b024Format),"Calendar is displayed for the Reservable vehicle pertain to the selected region from 00:00 to 24:00 hrs as per the date specified");
		
		//Switch off "View Week"
		clickOnCalendarViews(calendarViewByWeek);
		
		waitForExtJSAjaxComplete(20);
		
		//String dateForm=formatDateInToRequiredFormat(reservDate, "EEE dd/MM");
		
		//List<String> dateRangeViewWeekOff = getCalendarGridDateRangesFromHeader();
		
		//waitForExtJSAjaxComplete(20);
		
		//List <String> expecteddateRangeViewWeekOff = new ArrayList<String>();
		//expecteddateRangeViewWeekOff.add(dateForm);
		
		//softAssert.assertEquals(dateRangeViewWeekOff, expecteddateRangeViewWeekOff,"Calendar is displayed for the Reservable equipment pertain to the selected region for week day for the specified date");
		
		List<String>  hoursInDay=getListOfHoursInDay();
		
		System.err.println(hoursInDay);
		
		List<String> totalHoursInDay = getCalendarGridTimeRangeFromHeader("false","true");
		
		System.err.println(totalHoursInDay);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(totalHoursInDay, hoursInDay,"Calendar is displayed for the Reservable equipment pertain to the selected region from 00:00 to 24:00 hrs for the specified date");
		
		//Switch off Two Tabs
		clickOnCalendarViews(calendarViewBytwentyFourBySeven);
		
		waitForExtJSAjaxComplete(20);
		
		//String dateFormmat=formatDateInToRequiredFormat(reservDate, "EEE dd/MM");
		
		
		//List <String> expecteddateRangeViewTabsOff = new ArrayList<String>();
		//expecteddateRangeViewTabsOff.add(dateFormmat);
		//List<String> dateRangeViewTabsOff = getCalendarGridDateRangesFromHeader();
		
		//waitForExtJSAjaxComplete(20);
		
		//softAssert.assertEquals(dateRangeViewTabsOff, expecteddateRangeViewTabsOff,"Calendar is displayed for the Reservable equipment pertain to the selected region for week day for the specified date ");
		
		List<String>  expectdWorkinghoursInDay=getListOfWorkingHoursInDay();
		
		System.err.println(expectdWorkinghoursInDay);
		
		List<String> workingHoursInDay = getCalendarGridTimeRangeFromHeader("false","false");
		
		System.err.println(workingHoursInDay);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(workingHoursInDay, expectdWorkinghoursInDay,"Calendar is displayed for the Reservable equipment pertain to the selected region from 00:00 to 19:00 hrs for the specified date ");
		
		//Switch off View 24/7
		clickOnCalendarViews(calendarViewByWeek);
		
		waitForExtJSAjaxComplete(20);
		
		//List<String> dateRangeViewTwentyFourBySevenOff=getCalendarGridDateRangesFromHeader();
		
		//waitForExtJSAjaxComplete(20);
		
		//List <String> expecteddateRangeViewTwentyFourBySeven = getWorkingDaysInWeek("EEE dd/MM",reservDate);
		
		//softAssert.assertEquals(dateRangeViewTwentyFourBySevenOff, expecteddateRangeViewTwentyFourBySeven,"Calendar is displayed for the Reservable equipment pertain to the selected region from the week from Monday to Friday as per the date specified");
	
		waitForExtJSAjaxComplete(20);
		
		List<String> workingHoursInDayofTwentyFourBySeven = getCalendarGridTimeRangeFromHeader("true","false");
		
		System.out.println(workingHoursInDayofTwentyFourBySeven);
		
		waitForExtJSAjaxComplete(20);
		
		String ExpWrkingHours[] = new String[]{"Mon 13", "Tue 14", "Wed 15", "Thu 16", "Fri 17"};
		boolean b178Format= Arrays.equals(ExpWrkingHours,workingHoursInDayofTwentyFourBySeven.toArray());
		boolean b817Format = false;
		
		if(!b178Format){	
			String ExpWrkingHoursInDayofTwentyFourBySeven[]=new String[] {"17\n"+
					"08\n"+
					"Mon 13", "17\n"+
					"08\n"+
					"Tue 14","17\n"+
					"08\n"+
					"Wed 15", "17\n"+
					"08\n"+
					"Thu 16", "17\n"+
					"08\n"+
					"Fri 17"};
				     
		b817Format = Arrays.equals(ExpWrkingHoursInDayofTwentyFourBySeven,workingHoursInDayofTwentyFourBySeven.toArray());
		}

		softAssert.assertTrue((b178Format||b817Format),"Calendar is displayed for the Reservable vehicle pertain to the selected region from 00:00 to 19:00 hrs as per the date specified");		
		
		waitForExtJSAjaxComplete(20);
		
		filterItemByName(parking);

		waitForExtJSAjaxComplete(20);

		//clickOnCalendarViews(calendarViewByWeek);

		waitForMaskDisappear();

		clickOnCalendarViews(calendarViewBytwentyFourBySeven);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
				
		clickReservationInCalendar(parking,reservIDWithoutString);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		//softAssert.assertTrue(Grid.isRowInGridPresent(driver, reservDate+" "+from+"|"+parking,"@class", "grid3"),"equipment is reserved with equipment");
		
		//checkRowInGriByTextValueAndClick(reservDate+" "+from,parking);
		//softAssert.assertTrue(isRowInGridPresent(driver, parking, "@class", "grid3"),"equipment is reserved with equipment");
		
		//checkRowInGriByTextValueAndClick(parking);
		
		//waitForExtJSAjaxComplete(20);
		
		//clickViewCancel();
		
		//waitForExtJSAjaxComplete(20);
		
		
		//view, Edit and update parking reservation in calendar tab
		
		clickOrderItemsTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		setCleanUpTimeGeneral(updateCleanUpTime);
		
		clickGeneralSubject();
		
		waitForExtJSAjaxComplete(20);
		
		setGeneralSubject(subject);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
	
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getCleanUpTimeGeneral(),updateCleanUpTime,"Edit and updated parking reservation in calendar tab");
		
		waitForExtJSAjaxComplete(20);
		
		clickCancelReservation();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickMyReservationsTab();
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickCalenderTab();
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		setRegionInCalendarTab(regions);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickParkingTabInCalendarTab();
		
		waitForMaskDisappear();
		
		filterItemByName(parking);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(isReservationCancelled(parking,reservIDWithoutString),"parking reservation is cancelled");
		
		softAssert.assertAll();
		
		Reporter.log("View, Edit, Update and cancelled the parking reservation successfully in calendar tab", true);
		
	}
		
		
	
	/**
	 * Testcase ID	:	C74460,C74457,C74458,C74465,C74466
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testViewUpdateCancelVehiclesReservationInCalenderTab() throws Exception{
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"Test C74460:Select a time range and make reservation in Calendar tab for a specific Room, Equipment, Vehicle or Parking<br>"
				+"Test C74457: View reservations in Calendar tab<br>"
				+"Test C74458:View or edit a reservation from Calendar tab <br>"
				+"Test C74465:Update a reservation from Calendar tab <br> "
				+"Test:C74466:Cancel a reservation from Calendar tab  </span><br>", true);

		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "13-04-2020";
		String from = "01:00";
		String subject="Vehicle reservation";
		String until = "02:00";
		String updateCleanUpTime = "00:15";
		String numberPeople = "2";
		String responsible = getUserLastNameOfLoggedInUser();
		String vehicle = "2preRsVhRef";
		String calendarViewByWeek="View Week";
		String calendarViewBytwentyFourBySeven="View 24/7";
		String regions = "All Regions";
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testViewUpdateCancelVehiclesReservationInCalenderTab");
		
	
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
		
        String reservDate= getDate();
        
        //System.out.println(reservDate);
        
		waitForExtJSAjaxComplete(5);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(vehicle);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(10);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		String reservID=getReserationID();
		
		String reservIDWithoutString=reservID.replace("Reservation", "").trim();
		
		softAssert.assertTrue(getReserationID().matches("Reservation [0-9]+"), "Reservation <ID> is present in edit reservation Window Header");
		
		waitForExtJSAjaxComplete(20);
		
		clickCloseReservationUsingJS();
		
		waitForExtJSAjaxComplete(20);
		
		//Navigate to Calendar tab
		waitAndClick(XPATH_CALENDAR);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickCalenderTab();
		
		waitForMaskDisappear();
		
		clickRoomsTabInCalendarTab();
		
		waitForMaskDisappear();
		
		clickVehiclesTabInCalendarTab();
		
		waitForMaskDisappear();
		
		setRegionInCalendarTab(regions);
		
		waitForMaskDisappear();
		
		setDateViaDatePicker(reservDate);
		
		waitForMaskDisappear();
		
		//Switch on two tabs
		clickOnCalendarViews(calendarViewBytwentyFourBySeven);
		
		waitForMaskDisappear();
		
		//TODO: Headers are removed from the calendar page
		//List<String> actualDateRanges = getCalendarGridDateRangesFromHeader();
		
		//System.err.println(actualDateRanges);
		
		//List<String> ExpectedDateRanges = getAllDatesInWeek("EEE dd/MM",reservDate);
		
		//System.err.println(ExpectedDateRanges);
		
		//List<String> dateRanges = getCalendarGridDateRangesFromHeader();
		
		//softAssert.assertEquals(ExpectedDateRanges, actualDateRanges,"Calendar is displayed for the Reservable equipment pertain to the selected region for the week from Monday to Sunday as per the date specified");
		
		List<String> ActualTimeRanges = getCalendarGridTimeRangeFromHeader("true","true");
		
		waitForExtJSAjaxComplete(20);
		
		System.err.println(ActualTimeRanges);
		
		String ExpTimesRange[] = new String[]{"Mon 13", "Tue 14", "Wed 15", "Thu 16", "Fri 17", "Sat 18", "Sun 19"};
		boolean b240Format= Arrays.equals(ExpTimesRange,ActualTimeRanges.toArray());
		boolean b024Format = false;
		
		if(!b240Format){	
		String ExpTimesRanges[] = new String[]{"23\n"+
                "00\n"+
                "Mon 13", "23\n"+
                "00\n"+
                "Tue 14", "23\n"+
                "00\n"+
                "Wed 15", "23\n"+
                "00\n"+
                "Thu 16", "23\n"+
                "00\n"+
                "Fri 17", "23\n"+
                "00\n"+
                "Sat 18", "23\n"+
                "00\n"+
                "Sun 19"};
		b024Format = Arrays.equals(ExpTimesRanges,ActualTimeRanges.toArray());
		}

		softAssert.assertTrue((b240Format||b024Format),"Calendar is displayed for the Reservable vehicle pertain to the selected region from 00:00 to 24:00 hrs as per the date specified");
		
		//Switch off "View Week"
		clickOnCalendarViews(calendarViewByWeek);
		
		waitForExtJSAjaxComplete(20);
		
		//String dateForm=formatDateInToRequiredFormat(reservDate, "EEE dd/MM");
		
		//List<String> dateRangeViewWeekOff = getCalendarGridDateRangesFromHeader();
		
		//waitForExtJSAjaxComplete(20);
		
		//List <String> expecteddateRangeViewWeekOff = new ArrayList<String>();
		//expecteddateRangeViewWeekOff.add(dateForm);
		
		//softAssert.assertEquals(dateRangeViewWeekOff, expecteddateRangeViewWeekOff,"Calendar is displayed for the Reservable equipment pertain to the selected region for week day for the specified date");
		
		List<String>  hoursInDay=getListOfHoursInDay();
		
		System.err.println(hoursInDay);
		
		List<String> totalHoursInDay = getCalendarGridTimeRangeFromHeader("false","true");
		
		System.err.println(totalHoursInDay);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(totalHoursInDay, hoursInDay,"Calendar is displayed for the Reservable equipment pertain to the selected region from 00:00 to 24:00 hrs for the specified date");
		
		//Switch off Two Tabs
		clickOnCalendarViews(calendarViewBytwentyFourBySeven);
		
		waitForExtJSAjaxComplete(20);
		
		//String dateFormmat=formatDateInToRequiredFormat(reservDate, "EEE dd/MM");
		
		
		//List <String> expecteddateRangeViewTabsOff = new ArrayList<String>();
		//expecteddateRangeViewTabsOff.add(dateFormmat);
		//List<String> dateRangeViewTabsOff = getCalendarGridDateRangesFromHeader();
		
		//waitForExtJSAjaxComplete(20);
		
		//softAssert.assertEquals(dateRangeViewTabsOff, expecteddateRangeViewTabsOff,"Calendar is displayed for the Reservable equipment pertain to the selected region for week day for the specified date ");
		
		List<String>  expectdWorkinghoursInDay=getListOfWorkingHoursInDay();
		
		System.err.println(expectdWorkinghoursInDay);
		
		List<String> workingHoursInDay = getCalendarGridTimeRangeFromHeader("false","false");
		
		System.err.println(workingHoursInDay);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(workingHoursInDay, expectdWorkinghoursInDay,"Calendar is displayed for the Reservable equipment pertain to the selected region from 00:00 to 19:00 hrs for the specified date ");
		
		//Switch off View 24/7
		clickOnCalendarViews(calendarViewByWeek);
		
		waitForExtJSAjaxComplete(20);
		
		//List<String> dateRangeViewTwentyFourBySevenOff=getCalendarGridDateRangesFromHeader();
		
		//waitForExtJSAjaxComplete(20);
		
		//List <String> expecteddateRangeViewTwentyFourBySeven = getWorkingDaysInWeek("EEE dd/MM",reservDate);
		
		//softAssert.assertEquals(dateRangeViewTwentyFourBySevenOff, expecteddateRangeViewTwentyFourBySeven,"Calendar is displayed for the Reservable equipment pertain to the selected region from the week from Monday to Friday as per the date specified");
	
		waitForExtJSAjaxComplete(20);
		
		List<String> workingHoursInDayofTwentyFourBySeven = getCalendarGridTimeRangeFromHeader("true","false");
		
		System.out.println(workingHoursInDayofTwentyFourBySeven);
		
		waitForExtJSAjaxComplete(20);
		
		String ExpWrkingHours[] = new String[]{"Mon 13", "Tue 14", "Wed 15", "Thu 16", "Fri 17"};
		boolean b178Format= Arrays.equals(ExpWrkingHours,workingHoursInDayofTwentyFourBySeven.toArray());
		boolean b817Format = false;
		
		if(!b178Format){	
			String ExpWrkingHoursInDayofTwentyFourBySeven[]=new String[] {"17\n"+
					"08\n"+
					"Mon 13", "17\n"+
					"08\n"+
					"Tue 14","17\n"+
					"08\n"+
					"Wed 15", "17\n"+
					"08\n"+
					"Thu 16", "17\n"+
					"08\n"+
					"Fri 17"};
				     
		b817Format = Arrays.equals(ExpWrkingHoursInDayofTwentyFourBySeven,workingHoursInDayofTwentyFourBySeven.toArray());
		}

		softAssert.assertTrue((b178Format||b817Format),"Calendar is displayed for the Reservable vehicle pertain to the selected region from 00:00 to 19:00 hrs as per the date specified");		
		waitForExtJSAjaxComplete(20);
		
		filterItemByName(vehicle);

		waitForMaskDisappear();

		//clickOnCalendarViews(calendarViewByWeek);

		waitForExtJSAjaxComplete(20);

		clickOnCalendarViews(calendarViewBytwentyFourBySeven);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
				
		clickReservationInCalendar(vehicle,reservIDWithoutString);
		
		waitForExtJSAjaxComplete(20);
		
		//softAssert.assertTrue(Grid.isRowInGridPresent(driver, reservDate+" "+from+"|"+vehicle,"@class", "grid3"),"equipment is reserved with equipment");
		
		//checkRowInGriByTextValueAndClick(reservDate+" "+from,vehicle);*/
		//softAssert.assertTrue(isRowInGridPresent(driver, vehicle, "@class", "grid3"),"equipment is reserved with equipment");
		
		//checkRowInGriByTextValueAndClick(vehicle);
		
		//waitForExtJSAjaxComplete(20);
		
		//clickViewCancel();
		
		//waitForExtJSAjaxComplete(20);
		
		//view, Edit and update parking reservation in calendar tab
		waitForExtJSAjaxComplete(20);
		
		clickOrderItemsTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		setCleanUpTimeGeneral(updateCleanUpTime);
		
		clickGeneralSubject();
		
		waitForExtJSAjaxComplete(20);
		
		setGeneralSubject(subject);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getCleanUpTimeGeneral(),updateCleanUpTime,"Edit and updated vehicle reservation in clalender tab");
		
		waitForExtJSAjaxComplete(20);
		
		clickCancelReservation();
		
		clickOnDialogButton("Yes");

		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickMyReservationsTab();
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickCalenderTab();
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		setRegionInCalendarTab(regions);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickVehiclesTabInCalendarTab();
		
		waitForMaskDisappear();
		
		filterItemByName(vehicle);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(isReservationCancelled(vehicle,reservIDWithoutString),"vehicle reservation is cancelled");
		
		softAssert.assertAll();
		
		Reporter.log("View, Edit, Update and cancelled the Vehicle reservation successfully in calendar tab", true);
		
	}	
		

	
	/**
	 * Testcase ID	:	C74460,C74457,C74458,C74465,C74466
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testViewUpdateCancelEquipmentReservationInCalenderTab() throws Exception{
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"Test C74460:Select a time range and make reservation in Calendar tab for a specific Room, Equipment, Vehicle or Parking<br>"
				+"Test C74457: View reservations in Calendar tab<br>"
				+"Test C74458:View or edit a reservation from Calendar tab <br>"
				+"Test C74465:Update a reservation from Calendar tab <br> "
				+"Test:C74466:Cancel a reservation from Calendar tab  </span><br>", true);

		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "13-04-2020";
		String from = "01:00";
		String subject="Equipment reservation";
		String until = "02:00";
		String updateCleanUpTime = "00:25";
		String responsible = getUserLastNameOfLoggedInUser();
		String equipment = "2preRsEqRef";
		String calendarViewByWeek="View Week";
		String calendarViewBytwentyFourBySeven="View 24/7";
		String regions = "All Regions";
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testViewUpdateCancelEquipmentReservationInCalenderTab");
		
	
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
		
        String reservDate= getDate();
        
        
        
        //System.out.println(reservDate);
        
		waitForExtJSAjaxComplete(5);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(equipment);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		String reservID=getReserationID();
		
		String reservIDWithoutString=reservID.replace("Reservation", "").trim();
		
		softAssert.assertTrue(getReserationID().matches("Reservation [0-9]+"), "Reservation <ID> is present in edit reservation Window Header");
		
		waitForExtJSAjaxComplete(20);
		
		clickCloseReservationUsingJS();
		
		waitForExtJSAjaxComplete(20);
		//Navigate to Calendar tab
		waitAndClick(XPATH_CALENDAR);
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		clickCalenderTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		clickEquipmentTabInCalendarTab();
		
		waitForMaskDisappear();
		
		setRegionInCalendarTab(regions);
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		setDateViaDatePicker(reservDate);
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		//Switch on two tabs
		clickOnCalendarViews(calendarViewBytwentyFourBySeven);
		
		waitForExtJSAjaxComplete(20);
		
		//TODO: Headers are removed from the calendar page
		//List<String> actualDateRanges = getCalendarGridDateRangesFromHeader();
		
		//System.err.println(actualDateRanges);
		
		//List<String> ExpectedDateRanges = getAllDatesInWeek("EEE dd/MM",reservDate);
		
		//System.err.println(ExpectedDateRanges);
		
		//List<String> dateRanges = getCalendarGridDateRangesFromHeader();
		
		//softAssert.assertEquals(ExpectedDateRanges, actualDateRanges,"Calendar is displayed for the Reservable equipment pertain to the selected region for the week from Monday to Sunday as per the date specified");
		
		List<String> ActualTimeRanges = getCalendarGridTimeRangeFromHeader("true","true");
		
		waitForExtJSAjaxComplete(20);
		
		String ExpTimesRange[] = new String[]{"Mon 13", "Tue 14", "Wed 15", "Thu 16", "Fri 17", "Sat 18", "Sun 19"};
		boolean b240Format= Arrays.equals(ExpTimesRange,ActualTimeRanges.toArray());
		boolean b024Format = false;
		
		if(!b240Format){	
		String ExpTimesRanges[] = new String[]{"23\n"+
                "00\n"+
                "Mon 13", "23\n"+
                "00\n"+
                "Tue 14", "23\n"+
                "00\n"+
                "Wed 15", "23\n"+
                "00\n"+
                "Thu 16", "23\n"+
                "00\n"+
                "Fri 17", "23\n"+
                "00\n"+
                "Sat 18", "23\n"+
                "00\n"+
                "Sun 19"};
		b024Format = Arrays.equals(ExpTimesRanges,ActualTimeRanges.toArray());
		}

		softAssert.assertTrue((b240Format||b024Format),"Calendar is displayed for the Reservable vehicle pertain to the selected region from 00:00 to 24:00 hrs as per the date specified");
		
		//Switch off "View Week"
		clickOnCalendarViews(calendarViewByWeek);
		
		waitForExtJSAjaxComplete(20);
		
		//String dateForm=formatDateInToRequiredFormat(reservDate, "EEE dd/MM");
		
		//List<String> dateRangeViewWeekOff = getCalendarGridDateRangesFromHeader();
		
		//waitForExtJSAjaxComplete(20);
		
		//List <String> expecteddateRangeViewWeekOff = new ArrayList<String>();
		//expecteddateRangeViewWeekOff.add(dateForm);
		
		//softAssert.assertEquals(dateRangeViewWeekOff, expecteddateRangeViewWeekOff,"Calendar is displayed for the Reservable equipment pertain to the selected region for week day for the specified date");
		
		List<String>  hoursInDay=getListOfHoursInDay();
		
		System.err.println(hoursInDay);
		
		List<String> totalHoursInDay = getCalendarGridTimeRangeFromHeader("false","true");
		
		System.err.println(totalHoursInDay);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(totalHoursInDay, hoursInDay,"Calendar is displayed for the Reservable equipment pertain to the selected region from 00:00 to 24:00 hrs for the specified date");
		
		//Switch off Two Tabs
		clickOnCalendarViews(calendarViewBytwentyFourBySeven);
		
		waitForExtJSAjaxComplete(20);
		
		//String dateFormmat=formatDateInToRequiredFormat(reservDate, "EEE dd/MM");
		
		
		//List <String> expecteddateRangeViewTabsOff = new ArrayList<String>();
		//expecteddateRangeViewTabsOff.add(dateFormmat);
		//List<String> dateRangeViewTabsOff = getCalendarGridDateRangesFromHeader();
		
		//waitForExtJSAjaxComplete(20);
		
		//softAssert.assertEquals(dateRangeViewTabsOff, expecteddateRangeViewTabsOff,"Calendar is displayed for the Reservable equipment pertain to the selected region for week day for the specified date ");
		
		List<String>  expectdWorkinghoursInDay=getListOfWorkingHoursInDay();
		
		System.err.println(expectdWorkinghoursInDay);
		
		List<String> workingHoursInDay = getCalendarGridTimeRangeFromHeader("false","false");
		
		System.err.println(workingHoursInDay);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(workingHoursInDay, expectdWorkinghoursInDay,"Calendar is displayed for the Reservable equipment pertain to the selected region from 00:00 to 19:00 hrs for the specified date ");
		
		//Switch off View 24/7
		clickOnCalendarViews(calendarViewByWeek);
		
		waitForExtJSAjaxComplete(20);
		
		//List<String> dateRangeViewTwentyFourBySevenOff=getCalendarGridDateRangesFromHeader();
		
		//waitForExtJSAjaxComplete(20);
		
		//List <String> expecteddateRangeViewTwentyFourBySeven = getWorkingDaysInWeek("EEE dd/MM",reservDate);
		
		//softAssert.assertEquals(dateRangeViewTwentyFourBySevenOff, expecteddateRangeViewTwentyFourBySeven,"Calendar is displayed for the Reservable equipment pertain to the selected region from the week from Monday to Friday as per the date specified");
	
		waitForExtJSAjaxComplete(20);
		
		List<String> workingHoursInDayofTwentyFourBySeven = getCalendarGridTimeRangeFromHeader("true","false");
		
		System.out.println(workingHoursInDayofTwentyFourBySeven);
		
		waitForExtJSAjaxComplete(20);
		
		String ExpWrkingHours[] = new String[]{"Mon 13", "Tue 14", "Wed 15", "Thu 16", "Fri 17"};
		boolean b178Format= Arrays.equals(ExpWrkingHours,workingHoursInDayofTwentyFourBySeven.toArray());
		boolean b817Format = false;
		
		if(!b178Format){	
			String ExpWrkingHoursInDayofTwentyFourBySeven[]=new String[] {"17\n"+
					"08\n"+
					"Mon 13", "17\n"+
					"08\n"+
					"Tue 14","17\n"+
					"08\n"+
					"Wed 15", "17\n"+
					"08\n"+
					"Thu 16", "17\n"+
					"08\n"+
					"Fri 17"};
				     
		b817Format = Arrays.equals(ExpWrkingHoursInDayofTwentyFourBySeven,workingHoursInDayofTwentyFourBySeven.toArray());
		}

		softAssert.assertTrue((b178Format||b817Format),"Calendar is displayed for the Reservable vehicle pertain to the selected region from 00:00 to 19:00 hrs as per the date specified");		
				
		filterItemByName(equipment);

		waitForMaskDisappear();

		//clickOnCalendarViews(calendarViewByWeek);

		waitForExtJSAjaxComplete(20);

		clickOnCalendarViews(calendarViewBytwentyFourBySeven);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
				
		clickReservationInCalendar(equipment,reservIDWithoutString);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//softAssert.assertTrue(Grid.isRowInGridPresent(driver, reservDate+" "+from+"|"+equipment,"@class", "grid3"),"equipment is reserved with equipment");
		
		//checkRowInGriByTextValueAndClick(reservDate+" "+from,equipment);*/
		//softAssert.assertTrue(isRowInGridPresent(driver, equipment, "@class", "grid3"),"equipment is reserved with equipment");
		
		//checkRowInGriByTextValueAndClick(equipment);
		
		//waitForExtJSAjaxComplete(20);
		
		//clickViewCancel();
		
		//waitForExtJSAjaxComplete(20);
		
		//view, Edit and update parking reservation in calendar tab
		waitForExtJSAjaxComplete(20);
		
		clickOrderItemsTab();
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(10);
		
		setCleanUpTimeGeneral(updateCleanUpTime);
		
		clickGeneralSubject();
		
		waitForExtJSAjaxComplete(20);
		
		setGeneralSubject(subject);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getCleanUpTimeGeneral(),updateCleanUpTime,"Edit and updated equipment reservation in clalender tab");
		waitForExtJSAjaxComplete(20);
		
		clickCancelReservation();
		
		clickOnDialogButton("Yes");
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickMyReservationsTab();
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickCalenderTab();
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		setRegionInCalendarTab(regions);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickEquipmentTabInCalendarTab();
		
		waitForMaskDisappear();
		
		filterItemByName(equipment);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(isReservationCancelled(equipment,reservIDWithoutString),"eqipment reservation is cancelled");
		
		softAssert.assertAll();
		
		Reporter.log("View, Edit, Update and cancelled the equipment reservation successfully in calendar tab", true);
		
	}
	
	/**
	 * Testcase ID	:	C74459
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testOutOfUseReservableRoomInCalenderTab() throws Exception{
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C74459:Out of Use Reservable Rooms do not display in Calendar tab  </span><br>", true);

		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String roomRef = "OutOfUseRoomRef";
		String date = this.getFutureDate(random);
		String calendarViewByWeek="View Week";
		String calendarViewBytwentyFourBySeven="View 24/7";
		String regions = "All Regions";
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testOutOfUseReservableRoomInCalenderTab");
		
		expandAdministration();
	
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_CALENDAR);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickCalenderTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		clickRoomsTabInCalendarTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		setRegionInCalendarTab(regions);
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		setDateViaDatePicker(date);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnCalendarViews(calendarViewByWeek);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnCalendarViews(calendarViewBytwentyFourBySeven);
		
		waitForExtJSAjaxComplete(20);
		
		filterItemByName(roomRef);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//div[contains(@class,'sch-schedulerpanel')]//*[@class='x-grid3']//span[text()='"+roomRef+"' and contains(@style,'text-decoration: underline')]"), "Reservable room is not displayed");
		
		softAssert.assertAll();
		
		Reporter.log("Out of Use Reservable Room is not displayed in calendar tab", true);
	}
	
	/** Testcase ID : C91944
	 * Author      : kve
	 * @throws ParseException 
	 **/
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testRecurrenceForConflictingReserv() throws ParseException{

		Reporter.log("<span style='font-weight:bold;color:#000033'>"

		 +"Test C91944: Reservation Recurrence: Conflicting Reservations (MYM-26574)", true);

		String date = getFutureDate(0);
		String datebefore = getFutureDate(-1);
		String from = "14:00";
		String until = "15:00";
		String region2="All Regions";
		String region = "1aqaRgRef";
		String region1 = "1preRgRef";
		String room = "aqaroomref"; 
		String responsible = getUserLastNameOfLoggedInUser();
		String calendarViewBytwentyFourBySeven="View 24/7";
		String calendarViewByWeek = "View Week";

		String warningMsg ="Unable to save";

		boolean isReservationDone = false;

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testRecurrenceForConflictingReserv");

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

		waitForExtJSAjaxComplete(20);

		setTimeFrom(from);

		waitForExtJSAjaxComplete(20);

		setTimeUntil(until);

		waitForExtJSAjaxComplete(20);

		clickRoomTab();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(room);

		waitForExtJSAjaxComplete(20);

		setResponsible(responsible);

		waitForExtJSAjaxComplete(20);

		clickGeneralRecurence();

		setRecurenceStartDate(datebefore);

		waitForExtJSAjaxComplete(20);

		clickRecurenceEndAfter();

		waitForExtJSAjaxComplete(20);

		setRecurenceEndAfter("5");

		clickRecurenceDailyTab();

		waitForExtJSAjaxComplete(20);

		clickRecurenceDailyEvery();

		setRecurenceDailyInterval("2");

		clickRecurenceCalculateDates();

		waitForExtJSAjaxComplete(20);

		List<String> dateValues = getAllCaluclatedDatesOfRecurrence();
		
		System.err.println(dateValues);

		waitForExtJSAjaxComplete(20);

		clickCheckAvailability();

		waitForExtJSAjaxComplete(20);

		clickSetRecurence();

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		try{

			clickConfirmReservation();
			
			waitForMaskDisappear();
			
			waitForExtJSAjaxComplete(20);
			
			String reservID=getReserationID();
			
			String  reservWithoutString=reservID.replace("Reservation", "").trim();
			
			softAssert.assertTrue(isReservationDone(), "Reservation is confirmed");

			isReservationDone = true;

			waitForExtJSAjaxComplete(15);
			
			this.clickCloseReservationUsingJS();
			
			waitForExtJSAjaxComplete(20);
			
			driver.navigate().refresh();
		
			waitForExtJSAjaxComplete(20);

			expandAdministration();

			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);

			expandSubMainMenu("Reservation");

			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			waitAndClick(XPATH_CALENDAR);
			
			waitForMaskDisappear();

			waitForExtJSAjaxComplete(20);
			
			clickCalenderTab();
			
			waitForMaskDisappear();

			waitForExtJSAjaxComplete(20);
			
			clickRoomsTabInCalendarTab();

			waitForExtJSAjaxComplete(20);

			setRegionInCalendarTab(region);

			waitForExtJSAjaxComplete(20);
			
			waitForMaskDisappear();

			filterItemByName(room);
			
			waitForMaskDisappear();

			waitForExtJSAjaxComplete(20);

			clickOnCalendarViews(calendarViewByWeek);
			
			waitForMaskDisappear();

			waitForExtJSAjaxComplete(20);

			clickOnCalendarViews(calendarViewBytwentyFourBySeven);

			waitForExtJSAjaxComplete(20);

			int i=0;
			
			for( String date1: dateValues){
				
				waitForMaskDisappear();

				setDateViaDatePicker(date1);
				
				waitForExtJSAjaxComplete(10);
				
				int reservIDIntConv=Integer.parseInt(reservWithoutString);
				
				int reservIDInt=reservIDIntConv+i;
			
				String reservIDStr=String.valueOf(reservIDInt);
				
				waitForExtJSAjaxComplete(10);
				
				softAssert.assertTrue(isReservationCancelled(room,reservIDStr), "Recurrence is displayed for the given booked date");
				i++;

			}

			driver.navigate().refresh();

			waitForExtJSAjaxComplete(20);

			waitForExtJSAjaxComplete(20);

			waitAndClick(XPATH_NEWRESERVATIONS);

			waitForExtJSAjaxComplete(20);

			waitForExtJSAjaxComplete(20);

			setRegion(region1);

			waitForExtJSAjaxComplete(20);

			setDate(date);

			waitForExtJSAjaxComplete(20);

			setTimeFrom(from);

			waitForExtJSAjaxComplete(20);

			setTimeUntil(until);

			waitForExtJSAjaxComplete(20);

			clickRoomTab();

			waitForExtJSAjaxComplete(20);

			clickSearch();

			waitForExtJSAjaxComplete(20);

			clickLaunchReservation(room);

			waitForExtJSAjaxComplete(20);

			setResponsible(responsible);

			waitForExtJSAjaxComplete(10);

			clickGeneralRecurence();

			setRecurenceStartDate(datebefore);

			clickRecurenceEndAfter();

			setRecurenceEndAfter("5");

			clickRecurenceDailyTab();

			waitForExtJSAjaxComplete(20);

			clickRecurenceDailyEvery();

			setRecurenceDailyInterval("2");

			clickRecurenceCalculateDates();

			clickCheckAvailability();

			waitForExtJSAjaxComplete(20);

			clickSetRecurence();

			waitForExtJSAjaxComplete(20);

			clickConfirmReservation();		

			waitForExtJSAjaxComplete(20);

			softAssert.assertFalse(isReservationDone(), "Reservation is not confirmed");

			waitForExtJSAjaxComplete(20);

			String text=getWarningDialogTextMsg();

			softAssert.assertTrue(text.contains(warningMsg),"Alert message is displayed Unable to save, Overlaps with Reservation");

			waitForExtJSAjaxComplete(20);

			clickOnDialogButton("OK");

			waitForExtJSAjaxComplete(20);

		}

		//cancel Reservation

		finally{

			if(isReservationDone){
				
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

				myReserv.setRegionMyReservation(region2);

				waitForExtJSAjaxComplete(20);
				
				for( String date1: dateValues){

				myReserv.setTodayDateInMyReservation(date1);

				waitForExtJSAjaxComplete(20);

				myReserv.clickSearchButton();

				waitForExtJSAjaxComplete(20);

				waitForExtJSAjaxComplete(20);

				checkRowInGriByTextValueAndClick(date1+" "+from,room);

				waitForExtJSAjaxComplete(20);

				clickViewCancel();

				waitForExtJSAjaxComplete(20);

				clickGeneralTab();

				waitForExtJSAjaxComplete(20);

				clickCancelReservation();

				clickOnDialogButton("Yes");
				
				waitForExtJSAjaxComplete(20);
				
				softAssert.assertTrue(Grid.isRowInGridAbsent(driver,date1+" "+from+"|"+room,"@class", "grid3"),"Room reservation is cancelled");

				}
			}
		}

		softAssert.assertAll();

		Reporter.log("Successfully Verified the Reservation Recurrence for Conflicting Reservations in Room reservation<br>", true);

	}

	/** Testcase ID : C91330/C118662
	 * Author      : kve
	 * @throws Exception 
	 **/
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testRecurrencesBasedOnSpecifiedSettings_1() throws Exception{

		Reporter.log("<span style='font-weight:bold;color:#000033'>"

		 +"Test C91330/C118662:  Reservation Recurrence: Is calculated according to specified settings (MYM-26574) ", true);

		String date = getFutureDate(0);
		String dateBefore = getFutureDate(-1);
		String dateAfter = getFutureDate(1);
		String dateFiveMonthsAfter = getFutureDate(150);
		String date1 = getFutureDate(7);

		String from = "14:00";
		String until = "15:00";
		String from1 = "12:00";
		String until1 = "13:00";
		String region = "1aqaRgRef";
		String room = "AqaRmRecurrenceRef"; 
		String responsible = "SELENIUM";
		String calendarViewBytwentyFourBySeven="View 24/7";
		String calendarViewByWeek = "View Week";

		boolean isReservationDone = false;

		List<String> dateValues  = new ArrayList<String>();
		List<String> dateValuesAfterEdit = new ArrayList<String>();
		List<String> dateValues3 = new ArrayList<String>();



		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testRecurrencesBasedOnSpecifiedSettings_1");

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region);

		waitForExtJSAjaxComplete(20);

		setDate(date);

		waitForExtJSAjaxComplete(20);

		setTimeFrom(from);

		waitForExtJSAjaxComplete(20);

		setTimeUntil(until);

		waitForExtJSAjaxComplete(20);

		clickRoomTab();

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(room);

		waitForExtJSAjaxComplete(20);

		setResponsible(responsible);

		waitForExtJSAjaxComplete(20);

		clickGeneralRecurence();

		setRecurenceStartDate(date);

		waitForExtJSAjaxComplete(20);

		clickRecurenceEndAfter();

		waitForExtJSAjaxComplete(20);

		setRecurenceEndAfter("5");

		clickTabInLookUpWindow("@id", getXWindowId("Recurrence"),"Daily");

		waitForExtJSAjaxComplete(20);

		clickRecurenceDailyEvery();

		setRecurenceDailyInterval("1");

		clickRecurenceCalculateDates();

		waitForExtJSAjaxComplete(20);

		clickCheckAvailability();

		dateValues = getAllCaluclatedDatesOfRecurrence();

		waitForExtJSAjaxComplete(20);

		clickSetRecurence();

		waitForExtJSAjaxComplete(20);


		try{

			clickConfirmReservation();

			isReservationDone = true;

			waitForExtJSAjaxComplete(20);

			String reservationId = getReserationID();

			String reservIDWithoutString=reservationId.replace("Reservation", "").trim();

			clickCloseReservationUsingJS();

			waitForExtJSAjaxComplete(20);

			//calendar

			driver.navigate().refresh();

			waitForExtJSAjaxComplete(20);

			expandAdministration();

			waitForExtJSAjaxComplete(20);

			expandSubMainMenu("Reservation");

			waitForExtJSAjaxComplete(20);

			waitAndClick(XPATH_CALENDAR);

			waitForMaskDisappear();

			clickCalenderTab();

			waitForMaskDisappear();

			waitForExtJSAjaxComplete(20);

			setRegionInCalendarTab(region);

			waitForMaskDisappear();

			waitForExtJSAjaxComplete(20);

			clickRoomsTabInCalendarTab();

			waitForMaskDisappear();

			waitForExtJSAjaxComplete(20);

			//setDateInCalendarTab(dateAfter);
			setDateViaDatePicker(dateAfter);

			waitForMaskDisappear();

			waitForExtJSAjaxComplete(20);

			//waitForExtJSAjaxComplete(20);

			//filterItemByName("@class", "sch-schedulerpanel",room, "Name");

			clickOnCalendarViews(calendarViewByWeek);

			waitForExtJSAjaxComplete(20);

			clickOnCalendarViews(calendarViewBytwentyFourBySeven);

			waitForExtJSAjaxComplete(20);

			clickReservationInCalendar(room,reservIDWithoutString);

			waitForExtJSAjaxComplete(20);

			clickGeneralRecurence();

			waitForExtJSAjaxComplete(20);

			setRecurenceEndDate(date1);

			waitForExtJSAjaxComplete(20);

			clickRecurenceCalculateDates();

			waitForExtJSAjaxComplete(20);

			clickCheckAvailability();

			waitForExtJSAjaxComplete(20);

			dateValuesAfterEdit = getAllCaluclatedDatesOfRecurrence();

			waitForExtJSAjaxComplete(20);

			softAssert.assertNotEquals(dateValues,dateValuesAfterEdit,"Occurrence is edited and saved");

			clickSetRecurence();

			waitForExtJSAjaxComplete(20);

			clickConfirmReservation();	

			isReservationDone = true;

			waitForExtJSAjaxComplete(20);


			//Monthly-week

			driver.navigate().refresh();

			waitForExtJSAjaxComplete(25);

			expandAdministration();

			waitForExtJSAjaxComplete(20);

			expandSubMainMenu("Reservation");

			waitForExtJSAjaxComplete(20);

			waitAndClick(XPATH_NEWRESERVATIONS);

			waitForExtJSAjaxComplete(20);

			setRegion(region);

			waitForExtJSAjaxComplete(20);

			setDate(dateBefore);

			waitForExtJSAjaxComplete(20);

			setTimeFrom(from1);

			waitForExtJSAjaxComplete(20);

			setTimeUntil(until1);

			waitForExtJSAjaxComplete(20);

			clickRoomTab();

			waitForExtJSAjaxComplete(20);

			clickSearch();

			waitForExtJSAjaxComplete(20);

			clickLaunchReservation(room);

			waitForExtJSAjaxComplete(20);

			setResponsible(responsible);

			waitForExtJSAjaxComplete(20);

			clickGeneralRecurence();

			setRecurenceEndDate(dateFiveMonthsAfter);

			waitForExtJSAjaxComplete(20);

			clickRecurenceEndAfter();

			waitForExtJSAjaxComplete(20);

			setRecurenceEndAfter("5");

			clickTabInLookUpWindow("@id", getXWindowId("Recurrence"),"Monthly");

			clickWeekRadioButtonInMonthlyTab();

			waitForExtJSAjaxComplete(20);

			selectWeekOfMonthInMonthlyTab("first");

			selectWeekDayInMonhtlyTab("Sunday");

			setMonthlyIntervalOfWeekOptionInMonthlyTab("1");

			clickRecurenceCalculateDates();

			waitForExtJSAjaxComplete(20);

			clickCheckAvailability();

			dateValues3 = getAllCaluclatedDatesOfRecurrence();

			waitForExtJSAjaxComplete(20);

			clickSetRecurence();

			waitForExtJSAjaxComplete(20);

			clickConfirmReservation();

			waitForMaskDisappear();

			isReservationDone = true;

			waitForExtJSAjaxComplete(20);
		} 

		finally{

			if(isReservationDone){

				navigateToMainPage();

				waitForExtJSAjaxComplete(20);

				expandAdministration();

				waitForExtJSAjaxComplete(20);

				expandSubMainMenu("Reservation");

				waitForExtJSAjaxComplete(20);

				waitAndClick(XPATH_MYRESERVATIONS);

				waitForExtJSAjaxComplete(20);

				ReservationMyReservationsPageObject myReserv=new ReservationMyReservationsPageObject();

				myReserv.setRegionMyReservation(region);

				waitForExtJSAjaxComplete(20);

				myReserv.setTodayDateInMyReservation(dateBefore);

				waitForExtJSAjaxComplete(20);

				myReserv.clickSearchButton();

				waitForExtJSAjaxComplete(20);

				for( String dateval: dateValuesAfterEdit){

					waitForExtJSAjaxComplete(20);

					checkRowInGriByTextValueAndClick(dateval+" "+from,room);

					waitForExtJSAjaxComplete(20);

					clickViewCancel();

					waitForExtJSAjaxComplete(20);

					clickGeneralTab();

					waitForExtJSAjaxComplete(20);

					clickCancelReservation();

					clickOnDialogButton("Yes");

					waitForExtJSAjaxComplete(20);

					softAssert.assertTrue(Grid.isRowInGridAbsent(driver,dateval+" "+from+"|"+room,"@class", "grid3"),"Room reservation is cancelled");

				}


				for( String dateval3: dateValues3){

					waitForExtJSAjaxComplete(20);

					checkRowInGriByTextValueAndClick(dateval3+" "+from1,room);

					waitForExtJSAjaxComplete(20);

					clickViewCancel();

					waitForExtJSAjaxComplete(20);

					clickGeneralTab();

					waitForExtJSAjaxComplete(20);

					clickCancelReservation();

					clickOnDialogButton("Yes");

					waitForExtJSAjaxComplete(20);

					softAssert.assertTrue(Grid.isRowInGridAbsent(driver,dateval3+" "+from1+"|"+room,"@class", "grid3"),"Room reservation is cancelled");

				}

			}

		} 

		waitForExtJSAjaxComplete(20); 

		softAssert.assertAll();

		Reporter.log("Successfully calculated the Recurrence Reservation according to specified settings in Room reservation<br>", true);

	}

	/** Testcase ID : C91330/C118662
	 * Author      : kve
	 **/
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testRecurrencesBasedOnSpecifiedSettings_2(){

		Reporter.log("<span style='font-weight:bold;color:#000033'>"

		 +"Test C91330/C118662:  Reservation Recurrence: Is calculated according to specified settings (MYM-26574) ", true);

		String date = getFutureDate(0);
		String date1 = getFutureDate(-1);
		String dateThreeYearsAfter = getFutureDate(1095);
		String from = "08:00";
		String until = "09:00";
		String from1 = "11:00";
		String until1 = "12:00";
		String region = "1aqaRgRef";
		String room = "AqaRmRecurrenceRef"; 
		String responsible = "SELENIUM";


		boolean isReservationDone = false;


		List<String> dateValues1 = new ArrayList<String>();
		List<String> dateValues5 = new ArrayList<String>();


		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testRecurrencesBasedOnSpecifiedSettings_2");


		List<String> dayschecked = Arrays.asList("Wednesday","Friday");

		//Weekly
		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region);

		waitForExtJSAjaxComplete(20);

		setDate(date);

		waitForExtJSAjaxComplete(20);

		setTimeFrom(from);

		waitForExtJSAjaxComplete(20);

		setTimeUntil(until);

		clickRoomTab();

		waitForExtJSAjaxComplete(25);

		clickSearch();

		waitForExtJSAjaxComplete(25);

		clickLaunchReservation(room);

		waitForExtJSAjaxComplete(25);

		setResponsible(responsible);

		waitForExtJSAjaxComplete(25);

		clickGeneralRecurence();

		setRecurenceStartDate(date);

		waitForExtJSAjaxComplete(25);

		clickRecurenceEndAfter();

		setRecurenceEndAfter("10");

		clickTabInLookUpWindow("@id", getXWindowId("Recurrence"),"Weekly");

		setRecurenceWeeklyInterval("1");

		uncheckRecurenceWeeklyCheckBox();

		checkRecurenceWeeklyCheckBox(dayschecked);

		clickRecurenceCalculateDates();

		waitForExtJSAjaxComplete(20);

		dateValues1 = getAllCaluclatedDatesOfRecurrence();

		waitForExtJSAjaxComplete(20);

		clickCheckAvailability();

		waitForExtJSAjaxComplete(20);

		clickSetRecurence();

		waitForExtJSAjaxComplete(25);

		try{

			clickConfirmReservation();

			isReservationDone = true;

			waitForExtJSAjaxComplete(25);

			//Yearly-Week

			driver.navigate().refresh();

			waitForMaskDisappear();

			waitForExtJSAjaxComplete(20);

			expandAdministration();

			waitForExtJSAjaxComplete(20);

			expandSubMainMenu("Reservation");

			waitForExtJSAjaxComplete(20);

			waitAndClick(XPATH_NEWRESERVATIONS);

			waitForMaskDisappear();

			waitForExtJSAjaxComplete(20);

			setRegion(region);

			waitForExtJSAjaxComplete(20);

			setDate(date);

			waitForExtJSAjaxComplete(20);

			setTimeFrom(from1);

			waitForExtJSAjaxComplete(20);

			setTimeUntil(until1);

			waitForExtJSAjaxComplete(25);

			clickRoomTab();

			waitForExtJSAjaxComplete(25);

			clickSearch();

			waitForExtJSAjaxComplete(25);

			clickLaunchReservation(room);

			waitForExtJSAjaxComplete(25);

			setResponsible(responsible);

			waitForExtJSAjaxComplete(25);

			clickGeneralRecurence();

			setRecurenceStartDate(date);

			setRecurenceEndDate(dateThreeYearsAfter);

			clickTabInLookUpWindow("@id", getXWindowId("Recurrence"),"Yearly");

			setYearlyIntervalInYearTab("1");

			clickWeekRadioButtonInYearlyTab();

			waitForExtJSAjaxComplete(15);

			selectWeekOfMonthOfWeekOptionInYearlyTab("third");

			selectWeekDayOfWeekOptionInYearlyTab("Monday");

			selectMonthOfWeekOptionInYearlyTab("June");

			clickRecurenceCalculateDates();

			waitForExtJSAjaxComplete(20);

			dateValues5 = getAllCaluclatedDatesOfRecurrence();

			waitForExtJSAjaxComplete(20);

			clickCheckAvailability();

			waitForExtJSAjaxComplete(20);

			clickSetRecurence();

			waitForExtJSAjaxComplete(25);

			clickConfirmReservation();

			isReservationDone = true;

		} 

		finally{

			if(isReservationDone){

				navigateToMainPage();

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

				waitForExtJSAjaxComplete(25);

				myReserv.setTodayDateInMyReservation(date1);

				myReserv.clickSearchButton();

				for( String dateval1: dateValues1){

					waitForExtJSAjaxComplete(25);

					checkRowInGriByTextValueAndClick(dateval1+" "+from,room);

					waitForExtJSAjaxComplete(25);

					clickViewCancel();

					waitForExtJSAjaxComplete(25);

					clickGeneralTab();

					waitForExtJSAjaxComplete(25);

					clickCancelReservation();

					clickOnDialogButton("Yes");

					waitForExtJSAjaxComplete(25);

					softAssert.assertTrue(Grid.isRowInGridAbsent(driver,dateval1+" "+from+"|"+room,"@class", "grid3"),"Room reservation is cancelled");

				}

				for( String dateval5: dateValues5){

					waitForExtJSAjaxComplete(25);

					checkRowInGriByTextValueAndClick(dateval5+" "+from1,room);

					waitForExtJSAjaxComplete(25);

					clickViewCancel();

					waitForExtJSAjaxComplete(25);

					clickGeneralTab();

					waitForExtJSAjaxComplete(25);

					clickCancelReservation();

					clickOnDialogButton("Yes");

					waitForExtJSAjaxComplete(25);

					softAssert.assertTrue(Grid.isRowInGridAbsent(driver,dateval5+" "+from1+"|"+room,"@class", "grid3"),"Room reservation is cancelled");
				}
			}

		} 

		waitForExtJSAjaxComplete(25); 

		softAssert.assertAll();

		Reporter.log("Successfully calculated the Recurrence Reservation according to specified settings in Room reservation<br>", true);

	}

	/**
	 * Testcase ID : C91330/C118662
	 * Author      : kve
	 **/
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testRecurrencesBasedOnSpecifiedSettings_3(){

		Reporter.log("<span style='font-weight:bold;color:#000033'>"

		 +"Test C91330/C118662:  Reservation Recurrence: Is calculated according to specified settings (MYM-26574) ", true);

		String date = getFutureDate(0);
		String dateThreeMonthsAfter = getFutureDate(90);
		String dateTwoYearsAfter = getFutureDate(730);

		String from = "04:00";
		String until = "05:00";
		String region = "1aqaRgRef";
		String room = "AqaRmRecurrenceRef"; 
		String responsible = "SELENIUM";


		boolean isReservationDone = false;


		List<String> dateValues2 = new ArrayList<String>();
		List<String> dateValues4 = new ArrayList<String>();



		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testRecurrencesBasedOnSpecifiedSettings_3");

		//Monthly-Day
		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region);

		waitForExtJSAjaxComplete(20);

		setDate(date);

		waitForExtJSAjaxComplete(20);

		setTimeFrom(from);

		waitForExtJSAjaxComplete(20);

		setTimeUntil(until);

		waitForExtJSAjaxComplete(20);

		clickRoomTab();

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(room);

		waitForExtJSAjaxComplete(20);

		setResponsible(responsible);

		waitForExtJSAjaxComplete(20);

		clickGeneralRecurence();

		setRecurenceEndDate(dateThreeMonthsAfter);

		waitForExtJSAjaxComplete(20);

		clickRecurenceEndAfter();

		waitForExtJSAjaxComplete(20);

		setRecurenceEndAfter("2");

		clickTabInLookUpWindow("@id", getXWindowId("Recurrence"),"Monthly");

		clickDayRadioButtonInMonthlyTab();

		waitForExtJSAjaxComplete(25);

		setDayOfMonthInMonthlyTab("10");

		setMonthlyIntervalOfDayOptionInMOnthlyTab("1");

		clickRecurenceCalculateDates();

		dateValues2 = getAllCaluclatedDatesOfRecurrence();

		waitForExtJSAjaxComplete(20);

		clickCheckAvailability();

		waitForExtJSAjaxComplete(20);

		clickSetRecurence();

		waitForExtJSAjaxComplete(20);


		try{

			clickConfirmReservation();

			isReservationDone = true;

			//Yearly-Day

			driver.navigate().refresh();

			waitForExtJSAjaxComplete(25);

			expandAdministration();

			waitForExtJSAjaxComplete(20);

			expandSubMainMenu("Reservation");

			waitForExtJSAjaxComplete(20);

			waitAndClick(XPATH_NEWRESERVATIONS);

			waitForExtJSAjaxComplete(20);

			setRegion(region);

			waitForExtJSAjaxComplete(20);

			setDate(date);

			waitForExtJSAjaxComplete(20);

			setTimeFrom(from);

			waitForExtJSAjaxComplete(20);

			setTimeUntil(until);

			waitForExtJSAjaxComplete(20);

			clickRoomTab();

			waitForExtJSAjaxComplete(20);

			clickSearch();

			waitForExtJSAjaxComplete(20);

			waitForExtJSAjaxComplete(20);

			clickLaunchReservation(room);

			waitForExtJSAjaxComplete(20);

			waitForExtJSAjaxComplete(20);

			setResponsible(responsible);

			waitForExtJSAjaxComplete(20);

			waitForExtJSAjaxComplete(20);

			clickGeneralRecurence();

			setRecurenceStartDate(date);

			setRecurenceEndDate(dateTwoYearsAfter);

			clickTabInLookUpWindow("@id", getXWindowId("Recurrence"),"Yearly");

			setYearlyIntervalInYearTab("1");

			clickDayRadioButtonInYearlyTab();

			waitForExtJSAjaxComplete(20);

			selectMonthInDayOptionOfYearTab("July");

			setDayOfMonthInDayOptionOfYearTab("4");

			clickRecurenceCalculateDates();

			dateValues4 = getAllCaluclatedDatesOfRecurrence();

			waitForExtJSAjaxComplete(20);

			clickCheckAvailability();

			waitForExtJSAjaxComplete(20);

			clickSetRecurence();

			waitForExtJSAjaxComplete(20);

			clickConfirmReservation();

			isReservationDone = true;


		} 

		finally{

			if(isReservationDone){

				navigateToMainPage();

				waitForExtJSAjaxComplete(20);

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

				waitForExtJSAjaxComplete(20);

				myReserv.clickSearchButton();

				waitForExtJSAjaxComplete(20);

				for( String dateval2: dateValues2){

					waitForExtJSAjaxComplete(20);

					checkRowInGriByTextValueAndClick(dateval2+" "+from,room);

					waitForExtJSAjaxComplete(20);

					clickViewCancel();

					waitForExtJSAjaxComplete(20);

					clickGeneralTab();

					waitForExtJSAjaxComplete(20);

					clickCancelReservation();

					clickOnDialogButton("Yes");

					waitForExtJSAjaxComplete(20);

					softAssert.assertTrue(Grid.isRowInGridAbsent(driver,dateval2+" "+from+"|"+room,"@class", "grid3"),"Room reservation is cancelled");

				}


				for( String dateval4: dateValues4){

					waitForExtJSAjaxComplete(20);

					checkRowInGriByTextValueAndClick(dateval4+" "+from,room);

					waitForExtJSAjaxComplete(20);

					clickViewCancel();

					waitForExtJSAjaxComplete(20);

					clickGeneralTab();

					waitForExtJSAjaxComplete(20);

					clickCancelReservation();

					clickOnDialogButton("Yes");

					waitForExtJSAjaxComplete(20);

					softAssert.assertTrue(Grid.isRowInGridAbsent(driver,dateval4+" "+from+"|"+room,"@class", "grid3"),"Room reservation is cancelled");

				}


			}

		} 

		waitForExtJSAjaxComplete(20); 

		softAssert.assertAll();

		Reporter.log("Successfully calculated the Recurrence Reservation according to specified settings in Room reservation<br>", true);

	}



	//Blocked by bug:MYM-46928
	/**
	 * Testcase ID : C74459 
	 * author : ssa
	 */
	@Test(enabled = false, retryAnalyzer = RetryAnalyzer.class)
	public void testVehicleReservationLegendInCalenderTab() throws Exception {

		Reporter.log(
				"<span style='font-weight:bold;color:#000033'> "
						+ "C74459:Make Vehicle Reservations and verify that appropriate legend are shown in Calendar  </span><br>",true);

		String region = "1preRgRef";

		int random = (int) (Math.random() * 10) + 18;

		String vehicle = "1preRsVhRef";
		//String room = "1preRmRef";
		//String parking = "1prePrkEqRef";
		//String equipment = "1preRsGnEqRef";

		String date = this.getFutureDate(random);
		String calendarViewByWeek = "View Week";
		String calendarViewBytwentyFourBySeven = "View 24/7";
		// String regions = "All Regions";
		String from = "08:00";
		String until = "09:00";
		String prepare="00:10";
		String cleanup="00:10";
		String responsible = getUserLastNameOfLoggedInUser();

		String recurrence = "fa fa-refresh";
		String tentative = "tentative";
		String externalParticipant="fa fa-id-card";
		String externalParticipant1="fa fa-id-card";
		String reservIDWithoutString ="";
		 String first = "otherFrist";
		 String last = "otherLast";

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testVehicleReservationLegendInCalenderTab");

		boolean isReservation = false;
		List<String> allReservedIDs = new ArrayList<String>();

		
		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_CALENDAR);

		waitForMaskDisappear();

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

		waitForMaskDisappear();

		clickVehiclesTabInCalendarTab();

		waitForMaskDisappear();

		List<String> items = Arrays.asList(externalParticipant,externalParticipant1,"prepareandcleanup","myreservation",recurrence, tentative);
		for (String item : items) {

			waitForMaskDisappear();

			waitForExtJSAjaxComplete(20);

			createNewReservationByDragAndDrop(vehicle, from, until);

			waitForExtJSAjaxComplete(20);

			waitForExtJSAjaxComplete(20);

			// setRecurrence

			if (item == recurrence) {
				clickGeneralRecurence();

				clickRecurenceEndAfter();

				setRecurenceEndAfter("1");

				clickRecurenceDailyTab();

				waitForExtJSAjaxComplete(20);

				clickRecurenceDailyEvery();

				setRecurenceDailyInterval("1");

				clickRecurenceCalculateDates();

				// c18618

				clickCheckAvailability();

				waitForExtJSAjaxComplete(20);

				clickSetRecurence();

				waitForExtJSAjaxComplete(20);

				waitForExtJSAjaxComplete(20);
			} else if (item == tentative) {
				clickTentative();
			}else if(item=="prepareandcleanup")
			{
			setCleanUpTimeGeneral(prepare);
			waitForExtJSAjaxComplete(20);
			setCleanUpTimeGeneral(cleanup);
			}else if(item=="external participant")
			{
				clickParticipantTab();
				
				waitForExtJSAjaxComplete(20);
				
				addOtherPerson(first, last);
				
			}
			else if(item=="external participant1"){
				
				clickParticipantTab();
				
				waitForExtJSAjaxComplete(20);
				
				addParticipantContact("Sundaramurthy");
				
			}
			clickGeneralTab();
			
			waitForExtJSAjaxComplete(25);

			setResponsible(responsible);

			waitForExtJSAjaxComplete(20);

			waitForExtJSAjaxComplete(20);
				
			clickConfirmReservation();

			isReservation = true;

			waitForExtJSAjaxComplete(20);

			String reservID = getReserationID();

			reservIDWithoutString = reservID.replace("Reservation", "").trim();

			softAssert.assertTrue(getReserationID().matches("Reservation [0-9]+"),"Reservation <ID> is present in edit reservation Window Header");

			waitForExtJSAjaxComplete(20);

			softAssert.assertTrue(isReservHasSpecificLegend(vehicle, item, reservIDWithoutString),"+item+ Reservation legend is displayed");
		
		
			if (isReservation = true) {
				
				waitForExtJSAjaxComplete(20);

				clickReservationInCalendar(vehicle, reservIDWithoutString);

				waitForExtJSAjaxComplete(20);

				clickGeneralTab();

				clickCancelReservation();

				waitForExtJSAjaxComplete(20);

				clickOnDialogButton("Yes");
				
				waitForExtJSAjaxComplete(20);
			} else {
				System.err.println("Reservation not done");
			}
		}
		
			
		softAssert.assertAll();

		Reporter.log("Successfully verified reservation legends for equipment<br>", true);
	}


		//Blocked by bug:MYM-46928
		/**
		 * Testcase ID : C116878 
		 * author : ssa
		 */
		@Test(enabled = false, retryAnalyzer = RetryAnalyzer.class)
	public void testEquipmentReservationLegendInCalenderTab() throws Exception {
		
			Reporter.log(
					"<span style='font-weight:bold;color:#000033'> "
		+ "C116878:Make Vehicle Reservations and verify that appropriate legend are shown in Calendar  </span><br>",true);
		
		String region = "1preRgRef";
		
		int random = (int) (Math.random() * 10) + 18;
		
		
		String equipment = "1preRsGnEqRef";
		
		String date = this.getFutureDate(random);
		String calendarViewByWeek = "View Week";
		String calendarViewBytwentyFourBySeven = "View 24/7";
		// String regions = "All Regions";
		String from = "08:00";
		String until = "09:00";
		String prepare="00:10";
		String cleanup="00:10";
		String responsible = getUserLastNameOfLoggedInUser();
		
		String recurrence = "fa fa-refresh";
		String tentative = "tentative";
		String reservIDWithoutString ="";
		String first = "otherFrist";
		String last = "otherLast";
		
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testEquipmentReservationLegendInCalenderTab");
		
		boolean isReservation = false;
		List<String> allReservedIDs = new ArrayList<String>();
		
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_CALENDAR);
		
		waitForMaskDisappear();
		
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
		
		waitForMaskDisappear();
		
		clickEquipmentTabInCalendarTab();
		
		waitForMaskDisappear();
		
		int i = 0;
		
		List<String> items = Arrays.asList("tentative","fa fa-id-card","fa fa-id-card","prepareandcleanup","myreservation","fa fa-refresh");
		for (String item : items) {
		
			waitForMaskDisappear();
		
			waitForExtJSAjaxComplete(20);
		
			createNewReservationByDragAndDrop(equipment, from, until);
		
			waitForExtJSAjaxComplete(20);
		
			waitForExtJSAjaxComplete(20);
		
			// setRecurrence
		
		if (item == "fa fa-refresh") {
			clickGeneralRecurence();
		
			clickRecurenceEndAfter();
		
			setRecurenceEndAfter("1");
		
		clickRecurenceDailyTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickRecurenceDailyEvery();
		
		setRecurenceDailyInterval("1");
		
		clickRecurenceCalculateDates();
		
		// c18618
		
			clickCheckAvailability();
		
			waitForExtJSAjaxComplete(20);
		
			clickSetRecurence();
		
			waitForExtJSAjaxComplete(20);
		
			waitForExtJSAjaxComplete(20);
		} else if (item == "tentative") {
			clickTentative();
		}else if(item=="prepareandcleanup")
		{
		setCleanUpTimeGeneral(prepare);
		waitForExtJSAjaxComplete(20);
		setCleanUpTimeGeneral(cleanup);
		}else if(item=="fa fa-id-card" && i==0)
		{
			waitForExtJSAjaxComplete(20);
			
			clickParticipantTab();
			
			waitForExtJSAjaxComplete(20);
			
			addOtherPerson(first, last);
			
			waitForExtJSAjaxComplete(10);
			
			clickAnnounceCheckbox();
			
			i++;
			
		}else if(item=="fa fa-id-card" && i==1){
			
		waitForExtJSAjaxComplete(20);
		
		clickParticipantTab();
		
		waitForExtJSAjaxComplete(20);
		
		addParticipantEmployee("Sundaramurthy");
		
		McsElement.getElementByXpath(driver, "//img[contains(@src,'announce')]").click();
		
		waitForExtJSAjaxComplete(10);
		
		clickAnnounceCheckbox();
			
		}
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(25);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
			
		clickConfirmReservation();
		
		isReservation = true;
		
		waitForExtJSAjaxComplete(20);
		
		String reservID = getReserationID();
		
		reservIDWithoutString = reservID.replace("Reservation", "").trim();
		
		softAssert.assertTrue(getReserationID().matches("Reservation [0-9]+"),"Reservation <ID> is present in edit reservation Window Header");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isReservHasSpecificLegend(equipment, item, reservIDWithoutString)," "+item+" Reservation legend is displayed");
		
		
		if (isReservation = true) {
			
			waitForExtJSAjaxComplete(20);
		
			clickReservationInCalendar(equipment, reservIDWithoutString);
		
			waitForExtJSAjaxComplete(20);
		
			clickGeneralTab();
		
			clickCancelReservation();
		
			waitForExtJSAjaxComplete(20);
		
			clickOnDialogButton("Yes");
			
			waitForExtJSAjaxComplete(20);
		} else {
			System.err.println("Reservation not done");
			}
		
		
		}
		
			
		softAssert.assertAll();
		
		Reporter.log("Successfully verified reservation legends for vehicle<br>", true);
		}
	
	//Blocked by bug: MYM-46928
	/**
	 * Testcase ID : C116903 
	 * author : MMA
	 */
	@Test(enabled = false, retryAnalyzer = RetryAnalyzer.class)
	public void testParkingReservationLegendInCalenderTab() throws Exception {

		Reporter.log(
				"<span style='font-weight:bold;color:#000033'> "
						+ "C74459:Make Vehicle Reservations and verify that appropriate legend are shown in Calendar  </span><br>",true);

		String region = "1preRgRef";

		int random = (int) (Math.random() * 10) + 18;

		String parking = "1prePrkEqRef";

		String date = this.getFutureDate(random);
		String calendarViewByWeek = "View Week";
		String calendarViewBytwentyFourBySeven = "View 24/7";
		String from = "08:00";
		String until = "09:00";
		String prepare="00:10";
		String cleanup="00:10";
		String responsible = getUserLastNameOfLoggedInUser();

		String recurrence = "fa fa-refresh";
		String tentative = "tentative";
		String reservIDWithoutString ="";

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testParkingReservationLegendInCalenderTab");

		boolean isReservation = false;
		List<String> allReservedIDs = new ArrayList<String>();

		
		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_CALENDAR);

		waitForMaskDisappear();

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

		waitForMaskDisappear();

		clickParkingTabInCalendarTab();

		waitForMaskDisappear();

		List<String> items = Arrays.asList("prepareandcleanup","myreservation",recurrence, tentative);
		for (String item : items) {

			waitForMaskDisappear();

			waitForExtJSAjaxComplete(20);

			createNewReservationByDragAndDrop(parking, from, until);

			waitForExtJSAjaxComplete(20);

			waitForExtJSAjaxComplete(20);

			// setRecurrence

			if (item == recurrence) {
				clickGeneralRecurence();

				clickRecurenceEndAfter();

				setRecurenceEndAfter("1");

				clickRecurenceDailyTab();

				waitForExtJSAjaxComplete(20);

				clickRecurenceDailyEvery();

				setRecurenceDailyInterval("1");

				clickRecurenceCalculateDates();

				// c18618

				clickCheckAvailability();

				waitForExtJSAjaxComplete(20);

				clickSetRecurence();

				waitForExtJSAjaxComplete(20);

				waitForExtJSAjaxComplete(20);
			} else if (item == tentative) {
				clickTentative();
			}else if(item=="prepareandcleanup")
			{
			setCleanUpTimeGeneral(prepare);
			waitForExtJSAjaxComplete(20);
			setCleanUpTimeGeneral(cleanup);
			}

			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);
			
			setResponsible(responsible);

			waitForExtJSAjaxComplete(20);

			try{
				
			waitForExtJSAjaxComplete(20);
				
			clickConfirmReservation();

			isReservation = true;

			waitForExtJSAjaxComplete(20);

			String reservID = getReserationID();

			reservIDWithoutString = reservID.replace("Reservation", "").trim();

			softAssert.assertTrue(getReserationID().matches("Reservation [0-9]+"),"Reservation <ID> is present in edit reservation Window Header");
			waitForExtJSAjaxComplete(5);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(20);

			softAssert.assertTrue(isReservHasSpecificLegend(parking, item, reservIDWithoutString),"+item+ Reservation legend is displayed");
		}
		finally{
			if (isReservation = true) {
				
				waitForExtJSAjaxComplete(20);

				clickReservationInCalendar(parking, reservIDWithoutString);

				waitForExtJSAjaxComplete(20);

				clickGeneralTab();

				clickCancelReservation();

				waitForExtJSAjaxComplete(20);

				clickOnDialogButton("Yes");
				
				waitForExtJSAjaxComplete(20);
			} else {
				System.err.println("Reservation not done");
			}
		}
		}
			
		softAssert.assertAll();

		Reporter.log("Successfully verified reservation legends<br>", true);
	}	
	
	
	/**
	 * Testcase ID	:	C113876,C113877
	 * Author		:	ssa
	 * @throws ParseException 
	 * @throws AWTException 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testCopyReservInCalendar() throws ParseException, AWTException{

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C113876:Copy Reservation: Reservation is getting copied from Copied Reservation (MYM-24564)"
				+ "C113877: Copy Reservation: Reservation is not copied unless it is confirmed (MYM-24564)</span><br>", true);


		int random = (int)(Math.random() * 10)+18;
		String date = this.getFutureDate(random);

		String region = "2preRgRef";
		String responsible = getUserLastNameOfLoggedInUser();
		String from = "13:00";
		String until = "14:00";
		String room = "3preRmRef";
		String copyRoom = "roomEditRightsRef";
		String calendarViewBytwentyFourBySeven="View 24/7";
		String calendarViewByWeek = "View Week";
		boolean isReservation = false;


		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testCopyReservInNewReserv");

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_CALENDAR);

		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);

		clickCalenderTab();

		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);
		
		setRegionInCalendarTab(region);
		
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
		
		createNewReservationByDragAndDrop(room, from, until);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		String reservID = getReserationID();

		String reservIDWithoutString = reservID.replace("Reservation", "").trim();
		
		softAssert.assertTrue(isReservationDone(), "Reservation is confirmed");
		
		clickReservationInCalendar(room, reservIDWithoutString);
		
		waitForExtJSAjaxComplete(20);

		clickCopyReservation();
		
		waitForExtJSAjaxComplete(20);
		
		filterGrid("@class", "x-resizable", copyRoom,"Reference");;	
		
		//Reservation is Copied with appropriate parameters:C89560
		
		clickCANCELInLookupWindow();

		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isCopyButtonDisplayed(),"Copy button is displayed after reservation is not copied");

		waitAndClick(XPATH_MYRESERVATIONS); 

		waitForExtJSAjaxComplete(20);  

		waitForExtJSAjaxComplete(20);

		clickMyReservationsTab();

		waitForExtJSAjaxComplete(20);

		ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();

		myReserv.setRegionMyReservation(region);

		waitForExtJSAjaxComplete(20);

		myReserv.setFutureDateMyReservation(date);

		waitForExtJSAjaxComplete(20);

		myReserv.clickSearchButton();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver,date+" "+from+"|"+copyRoom,"@class", "grid3"),"Room reservation is not found which is not confirmed");
		
		//Copy reservation in calendar page:C113876
		waitAndClick(XPATH_CALENDAR);

		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);

		clickCalenderTab();
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickCopyReservation();
		
		waitForExtJSAjaxComplete(20);
		
		setValueGridLookup(copyRoom);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(isCopyButtonDisplayed(),"Copy button is hidden after reservation is copied");
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		isReservation=true;
		
		waitForExtJSAjaxComplete(20);
		
		List<String> items = Arrays.asList(room,copyRoom);
		
		if(isReservation=true)
		{
		
		for (String item : items) {
			
			clickMyReservationsTab();

			waitForMaskDisappear();

			myReserv.setRegionMyReservation(region);

			waitForExtJSAjaxComplete(20);

			myReserv.setFutureDateMyReservation(date);

			waitForExtJSAjaxComplete(20);

			myReserv.clickSearchButton();

			waitForExtJSAjaxComplete(20);

			checkRowInGriByTextValueAndClick(date+" "+from,item);

			waitForExtJSAjaxComplete(20);

			clickViewCancel();

			waitForExtJSAjaxComplete(20);

			clickGeneralTab();

			clickCancelReservation();

			waitForExtJSAjaxComplete(20);

			clickOnDialogButton("Yes");

			waitForExtJSAjaxComplete(20);

			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(Grid.isRowInGridAbsent(driver,date+" "+from+"|"+item,"@class", "grid3"),"Room reservation is not found which is cancelled");
		}
		}
		else{
			System.out.println("Reservation is not done");
		}
	
		softAssert.assertAll();

		Reporter.log("Successfully verified copy reservation in calendar reservation<br>", true);
			
	}
		

	/**
	 * Testcase ID	:	C113872
	 * Author		:	KVE
	 * @throws ParseException 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testCopyRoomReservationForConferenceMeeting() throws ParseException{

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C113872:  Copy Reservation of a meeting Room for a Conference Meeting via “Calendar” (MYM-24564)</span><br>", true);


		int random = (int)(Math.random() * 10)+18;
		String date = this.getFutureDate(random);

		String region = "2preRgRef";
		String responsible=getUserLastNameOfLoggedInUser();
		String from = "13:00";
		String until = "14:00";
		String room = "3preRmRef";
		String room1 = "roomEditRightsRef";
		String contact=this.getUserLastNameOfLoggedInUser();
		String calendarViewByWeek = "View Week";
		String calendarViewBytwentyFourBySeven = "View 24/7";
		boolean isReservationDone = false;

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testCopyReservationFromCalendar");

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

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(room);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setResponsible(responsible);

		waitForExtJSAjaxComplete(20);

		setContact(contact);

		waitForExtJSAjaxComplete(20);

		clickConfirmReservation();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		String reservID=getReserationID();

		String  reservWithoutString=reservID.replace("Reservation", "").trim();

		softAssert.assertTrue(isReservationDone(), "Reservation is confirmed");

		isReservationDone = true;

		waitForExtJSAjaxComplete(15);

		this.clickCloseReservationUsingJS();

		waitForExtJSAjaxComplete(15);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_CALENDAR);

		waitForMaskDisappear();

		clickCalenderTab();

		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);

		clickOnCalendarViews(calendarViewByWeek);

		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);

		clickOnCalendarViews(calendarViewByWeek);

		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);

		clickOnCalendarViews(calendarViewBytwentyFourBySeven);
		
		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);

		setDateViaDatePicker(date);

		waitForMaskDisappear();

		if (isReservationDone = true) {

			waitForExtJSAjaxComplete(20);

			clickReservationInCalendar(room, reservWithoutString);
		} 

		softAssert.assertTrue(isCopyReservationDisplayed(), "Copy button is displayed and enabled ");

		clickCopyReservation();

		List<String> expectedObjectValues=Arrays.asList("1aqaRgRef","1preRgRef","2preRgRef");

		softAssert.assertTrue(isMultiSelectRegionDropdownInCopyReservationDisplayed(), "Multi-select drop down with Reservation Regions is displayed");

		softAssert.assertEquals(getRegionFieldvalueBasedOnLabel(), region, "Default value of Region is displayed in the drop down with Reservation Regions");

		clickMultiSelectRegionDropdownInCopyReservation();

		softAssert.assertEquals(getRegionMultiSelectDropDownvalues(),expectedObjectValues,"1preRgRef option are available in the drop-down: 1preRgRef,1aqaRgRef,2preRgRef");

		waitForExtJSAjaxComplete(20);

		clickMultiSelectRegionDropdownInCopyReservation();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setValueGridLookupWithFiltersWithoutUsingMCSElement("@id", getXWindowId("Select a Room"), room1, "Reference");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickConfirmReservation();
		
		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);

		String reservID1=getReserationID();

		String  reservWithoutString1=reservID1.replace("Reservation", "").trim();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		if (isReservationDone = true) {

			waitForExtJSAjaxComplete(20);

			clickReservationInCalendar(room, reservWithoutString);

			waitForExtJSAjaxComplete(20);

			clickGeneralTab();

			clickCancelReservation();

			waitForExtJSAjaxComplete(20);

			clickOnDialogButton("Yes");

			waitForExtJSAjaxComplete(20);
		} else {
			System.err.println("Reservation not done");
		}

		this.waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);

		if (isReservationDone = true) {

			waitForExtJSAjaxComplete(20);

			clickReservationInCalendar(room1, reservWithoutString1);

			waitForExtJSAjaxComplete(20);

			clickGeneralTab();

			clickCancelReservation();

			waitForExtJSAjaxComplete(20);

			clickOnDialogButton("Yes");

			waitForExtJSAjaxComplete(20);
		} else {
			System.err.println("Reservation not done");
		}


		waitForMaskDisappear();

		softAssert.assertAll();

		Reporter.log("Copy Reservation of a meeting Room in New Reservation was done successfully", true);

	}
	
	
	/**
	 * Testcase ID	:	C112021, C112018
	 * Author		:	ssa(Shilpa)
	 * @throws ParseException 
	 * @throws AWTException 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testConferenceReservationInCalendar() throws ParseException, AWTException{

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C112021: Booking a Conference using a Calendar (MYM-28832)</span><br>"
				+ "C112018: Booking a Conference and Reverting changes using a Calendar (MYM-28832)", true);


		int random = (int)(Math.random() * 10)+18;
		String date = this.getFutureDate(random);

		String region1 = "1preRgRef";
		String region2 = "2preRgRef";
		String region3=	"All Regions";
		String responsible = getUserLastNameOfLoggedInUser();
		String from = "07:00";
		String until = "08:00";
		String roomW = "1preRmRef";
		String roomQ = "2preRmRef";
		String roomV="1PreRoomRef";
		String roomZ="3preRmRef";
		String roomX="roomEditRightsRef";
		String calendarViewBytwentyFourBySeven="View 24/7";
		String calendarViewByWeek = "View Week";
		String reservIDWithoutString1="";
		String reservIDWithoutString2="";
		String expectedErrorMsg="Overlaps with Reservation";
		boolean isReservation = false;
		String warningMsg = "Close this Reservation without saving?";


		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testConferenceReservationInCalendar");

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

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

		setRegionInCalendarTab(region1);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		//Creates new reservations with roomW
		createNewReservationByDragAndDrop(roomW, from, until);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		try{
			
		clickConfirmReservation();
		
		isReservation=true;
		
		waitForExtJSAjaxComplete(20);
		
		//Reservation is booked
		softAssert.assertTrue(isReservationDone(), "Reservation is confirmed");

		clickGeneralConference();

		waitForExtJSAjaxComplete(20);

		//C112018
		from = "11:00";
		until = "12:00";
		setTimeFromReservationPanel(from);
		waitForExtJSAjaxComplete(20);

		setTimeUntilReservationPanel(until);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(isConferenceButtonEnabled(),"Conference button is inactive");
		clickConfirmReservation();
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isReservationDone(), "Reservation is confirmed");


		clickGeneralConference();
		waitForExtJSAjaxComplete(20);	


		//After 'Conference' button is toggled Rooms are highlighted and available ones can be added to Conference
		softAssert.assertTrue(isGridRowsHighlightedForConference(), "After 'Conference' button is toggled Rooms are highlighted and available ones can be added to Conference");

		filterItemByName(roomV);

		waitForExtJSAjaxComplete(20);

		clickOnRoomInCalendarGrid(roomV);

		waitForExtJSAjaxComplete(20);

		//Reservation Conference is not created yet
		softAssert.assertFalse(isReservationDone(), "Reservation is not confirmed");
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		String reservID1 = getReserationID();

		reservIDWithoutString1 = reservID1.replace("Reservation", "").trim();
		
		System.err.println(reservIDWithoutString1);
		
		softAssert.assertTrue(isReservationDone(), "Reservation is confirmed");
		
		//Conference is created with roomW and roomV
		softAssert.assertTrue(isReservHasSpecificLegend(roomV, "fa fa-connectdevelop", reservIDWithoutString1)," "+roomV+" Reservation legend is displayed");
		
		clickCloseReservationUsingJS();
		
		waitForExtJSAjaxComplete(20);
		
		createNewReservationByDragAndDrop(roomQ, from, until);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralConference();
		
		filterItemByName(roomZ);
		
		waitForExtJSAjaxComplete(20);
		
		//Room is not available which is not under same region
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//div[contains(@class,'sch-schedulerpanel')]//*[@class='x-grid3']//span[text()='"+roomZ+"' and contains(@style,'text-decoration: underline')]"), "Reservable room is not displayed");
		
		filterItemByName(roomW);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnRoomInCalendarGrid(roomW);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		//Error message is displayed for already booked roomW
		String actualErrorMsg=getWarningDialogTextMsg();
		
		System.err.println(actualErrorMsg);
		
		softAssert.assertTrue(actualErrorMsg.contains(expectedErrorMsg), "Reservation is not confirmed ");
		
		clickOnDialogButtonCustomized("OK");
		
		waitForExtJSAjaxComplete(20);
		
		setRegionInCalendarTab(region2);
		
		waitForExtJSAjaxComplete(20);
		
		filterItemByName(roomZ);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnRoomInCalendarGrid(roomZ);
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(isReservationDone(), "Reservation is not confirmed");

		//C112018
		waitAndClick(XPATH_NEWRESERVATIONS);
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(getWarningDialogTextMsg().contains(warningMsg),"Appropriate error message is displayed ''Close this Reservation without saving?''");
		clickOnDialogButton("Cancel"); 
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		clickConfirmReservation();

		waitForExtJSAjaxComplete(20);

		String reservID2 = getReserationID();
		
		reservIDWithoutString2 = reservID2.replace("Reservation", "").trim();
		
		System.err.println(reservIDWithoutString2);
		
		softAssert.assertTrue(isReservationDone(), "Reservation is confirmed");
		
		//Conference is created with roomQ and roomZ for different regions
		softAssert.assertTrue(isReservHasSpecificLegend(roomZ, "fa fa-connectdevelop", reservIDWithoutString2)," "+roomZ+" Reservation legend is displayed");
		
		}finally{
			
			//Canceling booked reservations
			driver.navigate().refresh();
			
			waitForMaskDisappear();
			
			waitAndClick(XPATH_CALENDAR);

			waitForMaskDisappear();
			
			waitForExtJSAjaxComplete(20);
			
			List<String> items=Arrays.asList(roomV,roomZ);
				if (isReservation = true) {
					int i=0;
					for(String item:items)
					{
					
						
					waitForMaskDisappear();
					
					if(i==1){
						
					setRegionInCalendarTab(region2);
						
					waitForExtJSAjaxComplete(20);
					
					filterItemByName(roomZ);
					
					waitForExtJSAjaxComplete(20);
					
					System.err.println(reservIDWithoutString2);
							
					clickReservationInCalendar(item, reservIDWithoutString2);
					}else{
					
					setRegionInCalendarTab(region1);
						
					waitForExtJSAjaxComplete(20);
					
					filterItemByName(item);
					
					waitForMaskDisappear();
					waitForExtJSAjaxComplete(20);
					
					System.err.println(reservIDWithoutString1);
						
					clickReservationInCalendar(item, reservIDWithoutString1);
					}
					waitForExtJSAjaxComplete(20);

					clickGeneralTab();
					
					waitForExtJSAjaxComplete(20);

					clickCancelReservation();

					waitForExtJSAjaxComplete(20);

					clickOnDialogButton("Yes");

					waitForExtJSAjaxComplete(20);
					i++;
					}
				} else {
					System.err.println("Reservation not done");
				}

		}



		softAssert.assertAll();

		Reporter.log("Booking a Conference using a Calendar is verified sucessfully", true); 


	}

	/**
	 * Test case : C113365
	 * Author    : MMA
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testconfirmationMassageOnDiscardingChanges() throws Exception{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C113365: Confirmation message about discard changes if switch to another reservation", true);

		String region = "1preRgRef";
		int random = (int) (Math.random() * 10) + 18;
		String futureDate = this.getFutureDate(random);
		String date = futureDate;
		String room1 = "1PreRoomRef";
		String room2 = "2preRmRef";
		String from = "05:00"; 
		String until = "06:00";
		String calendarViewBytwentyFourBySeven="View 24/7";
		String calendarViewByWeek = "View Week";
		String responsible = getUserLastNameOfLoggedInUser();
		boolean isReservation = false;
		List<String> reservIDWithoutString = new ArrayList<String>();
		String warningMsg = "Close this Reservation without saving?";


		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testconfirmationMassageOnDiscardingChanges");
		try{
			expandAdministration();
			waitForExtJSAjaxComplete(20);

			expandSubMainMenu("Reservation");
			waitForExtJSAjaxComplete(20);

			int i = 0;
			do{
				waitAndClick(XPATH_NEWRESERVATIONS);
				waitForExtJSAjaxComplete(20);
				waitForExtJSAjaxComplete(20);

				if(i==1) { from = "09:00"; until = "10:00";}

				setRegion(region);
				setDate(date);
				setTimeFrom(from);
				setTimeUntil(until);
				waitForExtJSAjaxComplete(20);

				clickRoomTab();
				waitForExtJSAjaxComplete(20);

				clickSearch();
				waitForExtJSAjaxComplete(20);

				clickLaunchReservation(room1);
				waitForExtJSAjaxComplete(20);
				waitForExtJSAjaxComplete(20);

				setResponsible(responsible);
				waitForExtJSAjaxComplete(20);

				clickConfirmReservation();
				waitForExtJSAjaxComplete(20);
				waitForMaskDisappear();
				waitForExtJSAjaxComplete(20);

				softAssert.assertTrue(isReservationDone(), "Reservation is confirmed");
				isReservation= true;
				String newResVID1 = getReserationID();
				waitForExtJSAjaxComplete(20);

				reservIDWithoutString.add(newResVID1.replace("Reservation", "").trim());
				closeModule("Reservation");
				waitForExtJSAjaxComplete(10);
				i++;
			}while(i<=1);

			//NewReservation
			waitAndClick(XPATH_NEWRESERVATIONS);
			waitForExtJSAjaxComplete(20);
			waitForExtJSAjaxComplete(20);

			setRegion(region);
			setDate(date);
			setTimeFrom(from);
			setTimeUntil(until);
			waitForExtJSAjaxComplete(20);

			clickRoomTab();
			waitForExtJSAjaxComplete(20);

			clickSearch();
			waitForExtJSAjaxComplete(20);

			clickLaunchReservation(room2);
			waitForExtJSAjaxComplete(20);
			waitForExtJSAjaxComplete(20);

			//calendar - Alert message appears
			waitAndClick(XPATH_CALENDAR);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(20);

			clickRoomTab();
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
			waitForExtJSAjaxComplete(20);

			filterItemByName(room1);
			waitForExtJSAjaxComplete(20);

			clickReservationInCalendar(room1, reservIDWithoutString.get(0));
			waitForExtJSAjaxComplete(20);

			String clndrReservID = getReserationID();
			softAssert.assertEquals(clndrReservID.replace("Reservation", "").trim(), reservIDWithoutString.get(0),"Reservation's panel reloads and selected reservation is shown");
			waitForExtJSAjaxComplete(20);

			//Reservation's panel doesn't reload
			clickGeneralSubject();
			waitForExtJSAjaxComplete(20);

			setGeneralSubject("EditResvervation");
			waitForExtJSAjaxComplete(20);

			clickReservationInCalendar(room1, reservIDWithoutString.get(1));
			waitForExtJSAjaxComplete(20);
			softAssert.assertTrue(getWarningDialogTextMsg().contains(warningMsg),"Appropriate error message is displayed ''Close this Reservation without saving?''");

			clickOnDialogButton("Cancel"); 
			waitForExtJSAjaxComplete(20);
			waitForExtJSAjaxComplete(20);

			clickReservationInCalendar(room1, reservIDWithoutString.get(0));
			waitForExtJSAjaxComplete(20);

			softAssert.assertTrue(getGeneralSubject().contains("EditResvervation"),"Reservation's panel doesn't reload");
		}
		finally{
			if(isReservation = true){
				//Canceling booked reservations
				driver.navigate().refresh();
				waitForMaskDisappear();

				expandAdministration();
				waitForExtJSAjaxComplete(20);

				expandSubMainMenu("Reservation");
				waitForExtJSAjaxComplete(20);

				waitAndClick(XPATH_MYRESERVATIONS);
				waitForExtJSAjaxComplete(20);

				ReservationMyReservationsPageObject myReserv=new ReservationMyReservationsPageObject();
				myReserv.setRegionMyReservation(region);
				waitForExtJSAjaxComplete(25);

				myReserv.setTodayDateInMyReservation(date);
				myReserv.clickSearchButton();
				waitForExtJSAjaxComplete(25);

				List<String> fromTime = Arrays.asList("05:00","09:00");

				for(String from1 : fromTime){
					
					waitForExtJSAjaxComplete(25);
					checkRowInGriByTextValueAndClick(date+" "+from1,room1);
					waitForExtJSAjaxComplete(25);

					clickViewCancel();
					waitForExtJSAjaxComplete(25);

					clickGeneralTab();
					waitForExtJSAjaxComplete(25);

					clickCancelReservation();
					clickOnDialogButton("Yes");
					waitForExtJSAjaxComplete(25);

					softAssert.assertTrue(Grid.isRowInGridAbsent(driver,date+" "+from+"|"+room1,"@class", "grid3"),"Room reservation is cancelled");
				}

			} else {
				System.err.println("Reservation not done");
			}
		}

			softAssert.assertAll();
			Reporter.log("Confirmation message about discard changes if switch to another reservation", true); 
		}
	}


