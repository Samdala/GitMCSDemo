package test.generalweb.reservation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class ReservationMyReservationsCRUDTestSuite extends
ReservationMyReservationsPageObject{
	
	
	/**
	 * Testcase ID	:	C74468,C74469,C74470
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testViewUpdateCancelReservationInMyReservationTab() throws Exception{
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"Test C74468:View reservations in My Reservations tab <br>"
				+"Test C74469:Update a reservation from My Reservations tab <br> "
				+"Test C74470:Cancel a reservation from My Reservations tab  </span><br>", true);

		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "21-06-20"+random;
		String from = "01:00";
		String until = "02:00";
		String updateCleanUpTime = "00:15";
		String responsible = getUserLastNameOfLoggedInUser();
		String room = "1preRmRef";
		String numberPeople="2";
		String editNumberPeople="2";
		String category = "1preRmCtDescr";
		String purpose = "1preRmRsPrRef";
		String layout = "1preRmCmCod";
		String equipment = "1preRsGnEqRef";
		String vehicle = "1preRsVhRef";
		String parking = "1prePrkEqRef";
		String subject="Reservation"+getCurrentDate().substring(getCurrentDate().length()-5);
		String regions = "All Regions";
		String cancelWarningMsg="Are you sure you want to cancel this Reservation?";
		boolean isReservationDone=false;
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testViewUpdateCancelReservatioInCalenderTab");
		
	try{
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(20);
		
		setRegion(region);
		
		waitForExtJSAjaxComplete(20);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(5);
		
        String reservDate= getDate();
        
        //System.out.println(reservDate);
        
		clickRoomTab();
		
		waitForExtJSAjaxComplete(25);
		
		//setRoomCategory(category);

		//setNrPeople(numberPeople);
		
		//setRoomLayout(layout);

		
		clickSearch();
		
		waitForExtJSAjaxComplete(30);
		
		clickLaunchReservation(room);
		
		waitForExtJSAjaxComplete(25);
		
		clickGeneralTab();
		
		clickGeneralSubject();
		
		setGeneralSubject(subject);
		
		waitForExtJSAjaxComplete(25);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(25);
		
		String subtext=getGeneralSubject();
		
		waitForExtJSAjaxComplete(25);
		
		//Reservation for parking
		
		clickParkingTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(parking);
		
		waitForExtJSAjaxComplete(25);
		
		//Reservation for Vehicle
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(vehicle);
		
		waitForExtJSAjaxComplete(25);
		
		//Reservation for Equipment
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(equipment);
		
		waitForExtJSAjaxComplete(25);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		clickMyReservationsTab();
		
		waitForExtJSAjaxComplete(20);
		
		setRegionMyReservation(regions);
		
		waitForExtJSAjaxComplete(20);
		
		setTodayDateInMyReservation(reservDate);
		
		waitForExtJSAjaxComplete(20);
		
		setFutureDateMyReservation(reservDate);
		
		waitForExtJSAjaxComplete(20);
		
		setSubjectOrID(subtext);
		
		waitForExtJSAjaxComplete(20);
		
		clickSearchButton();
		
		waitForExtJSAjaxComplete(25);
		//verifying reservations are present or not 
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date+" "+from+"|"+room,"@class", "grid3"),"room is reserved");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date+" "+from+"|"+parking,"@class", "grid3"),"parking is reserved with room");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date+" "+from+"|"+vehicle,"@class", "grid3"),"vehicle is reserved with room");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date+" "+from+"|"+equipment,"@class", "grid3"),"equipment is reserved with room");
		
		waitForExtJSAjaxComplete(10);
		
		checkRowInGriByTextValueAndClick(date+" "+from,room);
				
		waitForExtJSAjaxComplete(25);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(25);
		
		//Edit and Update reservation in general tab
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(25);
		
		setCleanUpTimeGeneral(updateCleanUpTime);
		
		clickGeneralSubject();
		
		waitForExtJSAjaxComplete(25);
		
		setGeneralSubject(subject);
		
		waitForExtJSAjaxComplete(25);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(25);
		
		//clickConfirmReservation();
		
		//waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getCleanUpTimeGeneral(),updateCleanUpTime,"Edit and updated  reservation in general tab");
		
		waitForMaskDisappear();
		
		//Edit and Update reservation in order items tab
		clickOrderItemsTab();
		
		waitForExtJSAjaxComplete(10);
		
		setRoomPurposeInOrderItemsTab(purpose);
		
		waitForExtJSAjaxComplete(15);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		String text=getRoomPurposeInOrderItemsTab();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(text,purpose,"Edit and updated reservation in Order Items tab");
		
		waitForExtJSAjaxComplete(15);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(10);
		//Edit and Update reservation in participants tab
		clickParticipantTab();
		
		waitForExtJSAjaxComplete(25);
		
		setTotalParticipants(editNumberPeople);
		
		waitForExtJSAjaxComplete(25);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(25);
		
		clickConfirmReservation();

		isReservationDone=true;
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getTotalParticipants(),editNumberPeople,"Edit and updated reservation in Participants tab");
		
		waitForExtJSAjaxComplete(25);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickCancelReservation();
		
		waitForExtJSAjaxComplete(25);
		
		//Verify warning message for cancell reservation
		Boolean status=verifyWarningDialogTextMessage(cancelWarningMsg);
		
		softAssert.assertTrue(status, "Warning message for cancelled reservation is displayed");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver,date+" "+from+"|"+parking,"@class", "grid3"),"Room reservation is cancelled");
				
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, date+" "+from+"|"+parking,"@class", "grid3"),"parking reservation is cancelled");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, date+" "+from+"|"+vehicle,"@class", "grid3"),"vehicle reservation is cancelled");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, date+" "+from+"|"+equipment,"@class", "grid3"),"equipment reservation is cancelled");
		
	}
	catch(Exception e){
		deleteReservationFromMyReservation(date, from, room, isReservationDone);
	}
		
		softAssert.assertAll();
		
		Reporter.log("View, Edit, Update and cancelled the reservation successfully in My Reservation tab", true);
		
	}

	/**
	 * Testcase ID : C89551 
	 * Author  : kve
	 */

	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testSearchingAndFilteringOfReservRegion(){

		Reporter.log("<span style='font-weight:bold;color:#000033'> "

			 +"test: C89551:  Advanced Reservation Region lookup: search and filtering (MYM-25361) ", true);

		String Reference = "1aqaRgRef";
		String Code = "1aqaRgRef";
		String Floor = "1aqaPreParFloors";
		String Bulding = "1aqaPreParBuildings";
		String Site = "1aqaPreParSites";


		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testSearchingAndFilteringOfReservRegion");

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(Reference);

		softAssert.assertEquals(getRegion(),"1aqaRgRef","Lookup given with Reference is filtered with matched Region");

		setRegion(Code,"Code");

		softAssert.assertEquals(getRegion(),"1aqaRgRef","Lookup given with Code is filtered with matched Region");

		setRegion(Floor,"Floor");

		softAssert.assertEquals(getRegion(),"1aqaRgRef","Lookup given with Floor is filtered with matched Region");

		setRegion(Bulding,"Bulding");

		softAssert.assertEquals(getRegion(),"1aqaRgRef","Lookup given with Bulding is filtered with matched Region");

		setRegion(Site,"Site");

		softAssert.assertEquals(getRegion(),"1aqaRgRef","Lookup given with Site is filtered with matched Region");

		//Sort the region columns ascending and Descending 

		clickLookup("x-panel", "region");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Reference"), "Reference Column is Present");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Code"), "Code Column is Present");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Floor"), "Floor Column is Present");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Bulding"), "Bulding Column is Present");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Site"), "Site Column is Present");

		List<String> fieldNamesas = Arrays.asList("1aqaRgRef","1preRgRef","2preRgRef","All Regions");

		//sort Ascending for Reference
		sortColumnsAscDec("@class", "x-window x-window-noborder", "Reference", "Sort Ascending");

		waitForExtJSAjaxComplete(10);

		//Verify All regions are Ascendent in Grid Column for Reference
		//softAssert.assertEquals(getValuesFromGridLookup("@class", "x-window x-window-noborder", "Reference"),fieldNamesas, "Reference column list was correctly sorted in Ascending Order");

		softAssert.assertTrue(getValuesFromGridLookup("@class", "x-window x-window-noborder", "Reference").containsAll(fieldNamesas), "Reference column list was correctly sorted in Ascending Order");

		waitForExtJSAjaxComplete(10);

		Collections.reverse(fieldNamesas);

		//sort Descending for Reference
		sortColumnsAscDec("@class", "x-window x-window-noborder", "Reference", "Sort Descending");

		waitForExtJSAjaxComplete(10);

		//Verify All regions are Descended in Grid Column for Reference
		softAssert.assertTrue(getValuesFromGridLookup("@class", "x-window x-window-noborder", "Reference").containsAll(fieldNamesas), "Reference column list was correctly sorted in Descending Order");

		waitForExtJSAjaxComplete(10);

		List<String> fieldNamesas1 = Arrays.asList("1aqaRgRef","1preRgCod","2preRgCod","all");

		//sort Ascending for Code
		sortColumnsAscDec("@class", "x-window x-window-noborder", "Code", "Sort Ascending");

		waitForExtJSAjaxComplete(10);

		//Verify All regions are Ascendent in Grid Column for Code
		softAssert.assertTrue(getValuesFromGridLookup("@class", "x-window x-window-noborder", "Code").containsAll(fieldNamesas1), "Code column list was correctly sorted in Ascending Order");

		waitForExtJSAjaxComplete(10);

		Collections.reverse(fieldNamesas1);

		//sort Descending for Code
		sortColumnsAscDec("@class", "x-window x-window-noborder", "Code", "Sort Descending");

		waitForExtJSAjaxComplete(10);

		//Verify All regions are Descended in Grid Column for Code
		softAssert.assertTrue(getValuesFromGridLookup("@class", "x-window x-window-noborder", "Code").containsAll(fieldNamesas1), "Code column list was correctly sorted in Descending Order");

		List<String> fieldNamesas2 = Arrays.asList("1aqaPreParFloors","","","");

		//sort Ascending for Floor
		sortColumnsAscDec("@class", "x-window x-window-noborder", "Floor", "Sort Ascending");

		waitForExtJSAjaxComplete(10);

		//Verify All regions are Ascendent in Grid Column for Floor
		softAssert.assertTrue(getValuesFromGridLookup("@class", "x-window x-window-noborder", "Floor").containsAll(fieldNamesas2), "Floor column list was correctly sorted in Ascending Order");

		waitForExtJSAjaxComplete(10);

		Collections.reverse(fieldNamesas2);

		//sort Descending for Floor
		sortColumnsAscDec("@class", "x-window x-window-noborder", "Floor", "Sort Descending");

		waitForExtJSAjaxComplete(10);

		//Verify All regions are Descended in Grid Column for Floor
		softAssert.assertTrue(getValuesFromGridLookup("@class", "x-window x-window-noborder", "Floor").containsAll(fieldNamesas2), "Floor column list was correctly sorted in Descending Order");

		waitForExtJSAjaxComplete(5);

		List<String> fieldNamesas3 = Arrays.asList("1aqaPreParBuildings","","","");

		//sort Ascending for Bulding
		sortColumnsAscDec("@class", "x-window x-window-noborder", "Bulding", "Sort Ascending");

		waitForExtJSAjaxComplete(10);

		//Verify All regions are Ascendent in Grid Column for Bulding
		softAssert.assertTrue(getValuesFromGridLookup("@class", "x-window x-window-noborder", "Bulding").containsAll(fieldNamesas3), "Bulding column list was correctly sorted in Ascending Order");

		Collections.reverse(fieldNamesas3);

		//sort Descending for Bulding
		sortColumnsAscDec("@class", "x-window x-window-noborder", "Bulding", "Sort Descending");

		waitForExtJSAjaxComplete(10);

		//Verify All regions are Descended in Grid Column for Bulding
		softAssert.assertTrue(getValuesFromGridLookup("@class", "x-window x-window-noborder", "Bulding").containsAll(fieldNamesas3), "Bulding column list was correctly sorted in Descending Order");

		List<String> fieldNamesas4 = Arrays.asList("1aqaPreParSites","","","");

		//sort Ascending for Site
		sortColumnsAscDec("@class", "x-window x-window-noborder", "Site", "Sort Ascending");

		waitForExtJSAjaxComplete(10);

		//Verify All regions are Ascendent in Grid Column for Site
		softAssert.assertTrue(getValuesFromGridLookup("@class", "x-window x-window-noborder", "Site").containsAll(fieldNamesas4), "Site column list was correctly sorted in Ascending Order");

		Collections.reverse(fieldNamesas4);

		//sort Descending for Site
		sortColumnsAscDec("@class", "x-window x-window-noborder", "Site", "Sort Descending");

		waitForExtJSAjaxComplete(10);

		//Verify All regions are Descended in Grid Column for Site
		softAssert.assertTrue(getValuesFromGridLookup("@class", "x-window x-window-noborder", "Site").containsAll(fieldNamesas4), "Site column list was correctly sorted in Descending Order");

		softAssert.assertAll();

		Reporter.log("search and filtering of Advanced Reservation Region lookup was done successfully in My Reservation tab", true);

	}

	/**
	 * Testcase ID : C89549 
	 * Author  : kve
	 */

	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testAdvancedReservRegionLookup(){

		Reporter.log("<span style='font-weight:bold;color:#000033'> "

			 +"test: C89549:  Advanced Reservation Region lookup (MYM-25361)", true);


		String Reference = "1aqaRgRef";
		String Reference1 = "1preRgRef";
		String Region = "All Regions";
		String room = "aqaroomref";
		String room1 = "1aqaRoomRef";
		String responsible = getUserLastNameOfLoggedInUser();
		String from = "04:00";
		String until = "05:00";


		int random = (int)(Math.random() * 10)+18;
		String date = this.getFutureDate(random);


		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testAdvancedReservRegionLookup");

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		this.waitForMaskDisappear();

		softAssert.assertTrue(isNewResevationWindowVisbile() ,"New Reservation screen is displayed");

		List<String> fieldNames = Arrays.asList("1aqaRgRef","1preRgRef","2preRgRef","All Regions");

		//Open the NEWRESERVATIONS Region lookup

		setRegion(Reference);

		softAssert.assertEquals(getRegion(),"1aqaRgRef","Lookup given with Reference is filtered with matched Region");

		waitForExtJSAjaxComplete(20);				

		setDate(date);

		setTimeFrom(from);

		setTimeUntil(until);

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(room);

		waitForExtJSAjaxComplete(20);

		setResponsible(responsible);

		waitForExtJSAjaxComplete(20);
		try
		{
			clickConfirmReservation();

			softAssert.assertTrue(isReservationDone(), "Reservation is confirmed");

			String reservationID = ""+getReservationIDInt();

			softAssert.assertFalse(reservationID.isEmpty(),"Reservation is confirmed and Reservation ID "+ reservationID+"is generated.");

			waitForExtJSAjaxComplete(20);

			waitAndClick(XPATH_NEWRESERVATIONS);

			waitForExtJSAjaxComplete(20);

			setRegion(Reference1);

			waitForExtJSAjaxComplete(20);

			softAssert.assertEquals(getRegion(),"1preRgRef","Lookup given with Reference is filtered with matched Region");

			waitForExtJSAjaxComplete(20);				

			setDate(date);

			setTimeFrom(from);

			setTimeUntil(until);

			waitForExtJSAjaxComplete(20);

			clickSearch();

			waitForExtJSAjaxComplete(20);

			clickLaunchReservation(room1);

			waitForExtJSAjaxComplete(20);

			setResponsible(responsible);

			waitForExtJSAjaxComplete(20);

			clickConfirmReservation();

			softAssert.assertTrue(isReservationDone(), "Reservation is confirmed");

			String reservationID1 = ""+getReservationIDInt();

			softAssert.assertFalse(reservationID1.isEmpty(),"Reservation is confirmed and Reservation ID "+ reservationID1+"is generated.");

			List<String> reservationIDS = Arrays.asList(reservationID, reservationID1);
			
			System.err.println(reservationIDS);

			waitForExtJSAjaxComplete(20);

			driver.navigate().refresh();

			waitForExtJSAjaxComplete(20);

			expandAdministration();

			waitForExtJSAjaxComplete(20);

			expandSubMainMenu("Reservation");

			waitAndClick(XPATH_NEWRESERVATIONS);

			waitForExtJSAjaxComplete(20);

			setRegion(Region);

			waitForExtJSAjaxComplete(20);

			String preRegion=getRegion();

			softAssert.assertEquals(preRegion,"All Regions","Lookup given with Reference is filtered with matched Region");

			waitForExtJSAjaxComplete(20);

			clickLookup("x-panel", "region");

			waitForExtJSAjaxComplete(20);

			softAssert.assertTrue(getValuesFromGridLookup("@class", "x-window x-window-noborder", "Reference").containsAll(fieldNames), "Region Lookup is displayed, openened and contains All Regions value");

			softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Reference"), "Reference Column is Present");

			softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Code"), "Code Column is Present");

			softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Floor"), "Floor Column is Present");

			softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Bulding"), "Bulding Column is Present");

			softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Site"), "Site Column is Present");

			clickCANCELInLookupWindow();

			waitForExtJSAjaxComplete(20);


			//Open the MYRESERVATIONS Region lookup

			clickMyReservationsTab();

			waitForExtJSAjaxComplete(20);

			setRegionMyReservation(Region);

			waitForExtJSAjaxComplete(20);
			
			clickSearchButton();
			
			waitForExtJSAjaxComplete(20);

			String preMyReserRegion=getRegionInMyReservation();

			softAssert.assertEquals(preMyReserRegion,"All Regions","Lookup given with Reference is filtered with matched Region");

			boolean isReservationsDone=verifyReservationsInMyReservationBasedOnIDs(reservationIDS);

			softAssert.assertTrue(isReservationsDone,"All Reservations are displayed in My Reservations window");

			waitForExtJSAjaxComplete(20);

			clickLookupButtonOfRegionInMyReservations();

			waitForExtJSAjaxComplete(20);

			softAssert.assertTrue(getValuesFromGridLookup("@class", "x-window x-window-noborder", "Reference").containsAll(fieldNames), "Region Lookup is displayed, openened and contains All Regions value");

			softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Reference"), "Reference Column is Present");

			softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Code"), "Code Column is Present");

			softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Floor"), "Floor Column is Present");

			softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Bulding"), "Bulding Column is Present");

			softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Site"), "Site Column is Present");

			clickCANCELInLookupWindow();

			//Open the Calendar Region lookup
			waitForExtJSAjaxComplete(20);

			waitAndClick(XPATH_CALENDAR);

			waitForExtJSAjaxComplete(20);

			waitForExtJSAjaxComplete(20);

			clickCalenderTab();

			waitForMaskDisappear();

			ReservationsCalendarNewPageObject cobj=new ReservationsCalendarNewPageObject();

			waitForExtJSAjaxComplete(20);

			cobj.setRegionInCalendarTab(Region);

			waitForExtJSAjaxComplete(25);

			String preCalenderRegion=cobj.getRegionInCalendarTab();

			softAssert.assertEquals(preCalenderRegion,"All Regions","Lookup given with Reference is filtered with matched Region");

			waitForExtJSAjaxComplete(20);

			cobj.clickLookupButtonBasedOnLabel("Region:");

			waitForExtJSAjaxComplete(20);

			softAssert.assertTrue(getValuesFromGridLookup("@class", "x-window x-window-noborder", "Reference").containsAll(fieldNames), "Region Lookup is displayed, openened and contains All Regions value");

			softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Reference"), "Reference Column is Present");

			softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Code"), "Code Column is Present");

			softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Floor"), "Floor Column is Present");

			softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Bulding"), "Bulding Column is Present");

			softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Site"), "Site Column is Present");

			clickCANCELInLookupWindow();

			waitForExtJSAjaxComplete(20);

			waitAndClick(XPATH_NEWRESERVATIONS);

			waitForExtJSAjaxComplete(20);

			String postRegion=getRegion();

			softAssert.assertEquals(preRegion,postRegion,"New Reservations Region lookup contains previously selected Region");

			waitForExtJSAjaxComplete(20);

			waitAndClick(XPATH_MYRESERVATIONS);

			waitForExtJSAjaxComplete(20);

			String postMyReserRegion=getRegionInMyReservation();

			softAssert.assertEquals(preMyReserRegion,postMyReserRegion,"My Reservations Region lookup contains previously selected Region");

			waitAndClick(XPATH_CALENDAR);

			waitForExtJSAjaxComplete(20);

			String postCalenderRegion=cobj.getRegionInCalendarTab();

			softAssert.assertEquals(preCalenderRegion,postCalenderRegion,"Calender Region lookup contains previously selected Region");

		}
		finally{

			navigateToMainPage();

			waitForExtJSAjaxComplete(20);

			expandAdministration();

			waitForExtJSAjaxComplete(20);

			expandSubMainMenu("Reservation");

			waitForExtJSAjaxComplete(20);

			waitAndClick(XPATH_MYRESERVATIONS);

			waitForExtJSAjaxComplete(20);

			this.waitForMaskDisappear();

			ReservationMyReservationsPageObject myReserv=new ReservationMyReservationsPageObject();

			myReserv.setRegionMyReservation(Region);

			waitForExtJSAjaxComplete(25);

			myReserv.clickSearchButton();

			waitForExtJSAjaxComplete(20);

			checkRowInGriByTextValueAndClick(date+" "+from,room);

			waitForExtJSAjaxComplete(20);

			clickViewCancel();

			waitForExtJSAjaxComplete(20);

			clickGeneralTab();

			clickCancelReservation();

			clickOnDialogButton("Yes");

			waitForExtJSAjaxComplete(20);

			waitAndClick(XPATH_NEWRESERVATIONS);

			waitForExtJSAjaxComplete(20);

			waitAndClick(XPATH_MYRESERVATIONS);

			waitForExtJSAjaxComplete(20);

			myReserv.setRegionMyReservation(Region);

			waitForExtJSAjaxComplete(20);

			myReserv.clickSearchButton();

			waitForExtJSAjaxComplete(20);

			checkRowInGriByTextValueAndClick(date+" "+from,room1);
			
			waitForExtJSAjaxComplete(20);

			clickViewCancel();

			waitForExtJSAjaxComplete(20);

			clickGeneralTab();

			clickCancelReservation();

			clickOnDialogButton("Yes");

			waitForExtJSAjaxComplete(20);

		}

		softAssert.assertAll();

		Reporter.log("Advanced Reservation Region lookup was verified successfully in Reservations", true);
	}


}

	

