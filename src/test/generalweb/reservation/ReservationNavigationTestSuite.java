package test.generalweb.reservation;



import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.generalweb.helpdesk.HelpDeskFrontOfficePageObject;
import test.generalweb.rightvisibility.AdministrationPageObject;




public class ReservationNavigationTestSuite extends
	ReservationNavigationPageObject {

	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationNavigation() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: testReservationNavigation : c18544, c18546</span><br>", true);

		Reporter.log("User Navigation to Reservation URL's <br>", true);
		
		String welcomeUrl = "/index.php?mode=DESKTOP&relay=MCS_PORTAL_RS-ACTIVATE[WELCOME]";
		
		String welcomeText = "Welcome to MCS Web Reservation";
		
		String myReservationPageUrl = "/index.php?mode=DESKTOP&relay=MCS_PORTAL_RS-ACTIVATE[MY_RESERVATIONS]";
		
		//String myReservationText = "Welcome to myMCS Web Reservations";
		
		String buttonText = "My Reservations";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReservationNavigation");
		
        expandAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		navigateToPage(welcomeUrl);
		
		waitForExtJSAjaxComplete(20);
		
		verifyWelcomePage(welcomeText);
		
		waitForExtJSAjaxComplete(20);
		
		navigateToPage(myReservationPageUrl);
		
		waitForExtJSAjaxComplete(20);
		
		verifyButtonPressedPage(buttonText);
		
		softAssert.assertAll();
		
		Reporter.log("User navigation through URL's succesfully.", true);
	
	}


	/**
	 * Testcase ID : C90093, C90092
	 * Author : MMA
	 * @throws ParseException 
	 */

	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservableObjectsViaParametersPanel() throws IOException, ParseException{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+""
				+ "C90093:Searching of Reservable Objects Via Parameters Panel </span><br>",true);
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+""
				+ "C90092:Parameters panel Controls </span><br>",true);

		String regionAll="All Regions";
		String region = "1preRgRef";
		int random = (int)(Math.random() * 10)+18;
		String date = this.getFutureDate(random);
		String from = "20:00";
		String until = "20:30";
		String noOfPeople = "";
		String eqpCategory = "1preGnEqRef";
		String category = "1preRmCtDescr";
		String purpose = "1preRmRsPrRef";
		String layOut = "1preRmCmCod";
		String calendarViewBytwentyFourBySeven="View 24/7";
		String calendarViewByWeek = "View Week";


		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testReservableObjectsViaParametersPanel");
		ReservationsCalendarNewPageObject objCalendar = new ReservationsCalendarNewPageObject();
		ReservationMyReservationsPageObject objMyRes = new ReservationMyReservationsPageObject();
		waitForExtJSAjaxComplete(20);
		
		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isRoomSectionIsSelectedByDefault(), "Rooms section is selected by default");
		
       //TODO "Rooms, Parking, Vehicles, Equipment, Services tabs are displayed" this step is already covered
		
		List<String> roomLayoutExp = Arrays.asList("1preRmCmCod","2preRmCmRef");
		List<String> roomPurposeExp = Arrays.asList("1preRmRsPrRef","2preRmRsPrRef");
		List<String> alreadyInRoomExp = Arrays.asList("1preGnEqRef","preEqDefCtRef");
		List<String> cayegoryList = getOptionsForFieldInNewReservation("Category");

		softAssert.assertTrue( getOptionsForFieldInNewReservation("selectEquipment").containsAll(alreadyInRoomExp),"AlreadyInRoom menu contains list  available Equipment");
		softAssert.assertTrue( getOptionsForFieldInNewReservation("roomLayout").containsAll(roomLayoutExp),"Room Purpose menu contains list of the Room Purpose defined in MyMCS Reservations");
		softAssert.assertTrue( getOptionsForFieldInNewReservation("roomPurpose").containsAll(roomPurposeExp),"Room Purpose menu contains list of the Room Purpose defined in MyMCS Reservations");
		softAssert.assertTrue(isImageDisplayedInRoomLayOutSection("1preRmCmCod"),"Images are displayed in the for the Room Layout selections");
		waitForExtJSAjaxComplete(20);

		clickParkingTab();

		waitForExtJSAjaxComplete(10);

		List<String> prkCayegoryList = getOptionsForFieldInNewReservation("parkingCategory");
		
		waitForExtJSAjaxComplete(5);

		clickVehicleTab();

		waitForExtJSAjaxComplete(10);

		List<String> vehclCayegoryList = getOptionsForFieldInNewReservation("vehicleCategory");

		clickEquipmentTab();

		waitForExtJSAjaxComplete(10);

		List<String> eqpCayegoryList = getOptionsForFieldInNewReservation("equipmentCategory");

		clickServiceTab();

		waitForExtJSAjaxComplete(10);

		List<String> servCategoryListExp = Arrays.asList("1preSrvCtDescr","2preSrvCtDescr");

		softAssert.assertTrue(getOptionsForFieldInNewReservation("serviceCategory").containsAll(servCategoryListExp),"Category menu's contains list of the Services Categories");
		softAssert.assertEquals(getFieldValueInNewReservationWindow("serviceName"),"Use * as wildcard","Name field is displayed with a hint message");

		clickRoomTab();

		waitForExtJSAjaxComplete(20);

		setRegion(regionAll);

		setDate(date);

		setTimeFrom(from);

		setTimeUntil(until);

		setNrPeople(noOfPeople);

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(30);

		List<String> reservablesInNewResWindow = findRoomsFreeForRegionGivenTime();
		
		System.out.println(reservablesInNewResWindow);

		waitAndClick(XPATH_CALENDAR);
		
		waitForExtJSAjaxComplete(20);
		
		clickCalenderTab();
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		objCalendar.clickParkingTabInCalendarTab();
		
		waitForMaskDisappear();
		
		softAssert.assertEqualsNoOrder( prkCayegoryList.toArray(), objCalendar.getCategoryFromCalendarTab("Category","parking").toArray(),"Category menu contains list of the Parking Categories defined in MyMCS Reservations");

		objCalendar.clickVehiclesTabInCalendarTab();

		waitForMaskDisappear();

		softAssert.assertEqualsNoOrder( vehclCayegoryList.toArray(), objCalendar.getCategoryFromCalendarTab("Category","vehicle").toArray(),"Category menu contains list of the Vehicle Categories defined in MyMCS Reservations");

		objCalendar.clickEquipmentTabInCalendarTab();

		waitForMaskDisappear();

		softAssert.assertEqualsNoOrder( eqpCayegoryList.toArray(), objCalendar.getCategoryFromCalendarTab("Category","equipment").toArray(),"Category menu contains list of the Equipment Categories defined in MyMCS Reservations");

		objCalendar.clickRoomsTabInCalendarTab();

		waitForMaskDisappear();

		softAssert.assertEqualsNoOrder( cayegoryList.toArray(), objCalendar.getCategoryFromCalendarTab("Category","room").toArray(),"Category menu contains list of the Room Categories defined in MyMCS Reservations");

		objCalendar.clickOnCalendarViews(calendarViewByWeek);

		waitForExtJSAjaxComplete(20);

		objCalendar.setDateViaDatePicker(date);

		waitForExtJSAjaxComplete(20);

		objCalendar.clickOnCalendarViews(calendarViewBytwentyFourBySeven);

		waitForExtJSAjaxComplete(20);

		List<String> reservablesInCalenderTab = objCalendar.findRoomsFreeForGivenTime(from);
		

		softAssert.assertTrue(reservablesInNewResWindow.containsAll(reservablesInCalenderTab), "Rooms from All Regions available for the User are displayed");
		
		driver.navigate().refresh();
		
		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(10);

		waitAndClick(XPATH_MYRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(objMyRes.getRegionInMyReservation(), regionAll, "Same Region remain selected in My Reservation");

		waitForExtJSAjaxComplete(10);

		clickCalenderTab();

		waitForMaskDisappear();

		objCalendar.clickRoomsTabInCalendarTab();

		waitForMaskDisappear();

		softAssert.assertEquals(objCalendar.getRegionInCalendarTab(), regionAll, "Same Region remain selected in Calendar Tab");


		driver.navigate().refresh();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);
		
		setRegion(region);

		setDate(date);

		setTimeFrom(from);

		setTimeUntil(until);

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		reservablesInNewResWindow = findRoomsFreeForRegionGivenTime();

		waitForExtJSAjaxComplete(10);

		waitForExtJSAjaxComplete(20);

		clickCalenderTab();

		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);

		objCalendar.setRegionInCalendarTab(region);
		
		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);

		objCalendar.clickRoomsTabInCalendarTab();
		
		waitForExtJSAjaxComplete(20);
		
		reservablesInCalenderTab =  objCalendar.findRoomsFreeForGivenTime(from);
		
		System.out.println(reservablesInCalenderTab);

		waitForExtJSAjaxComplete(10);

		softAssert.assertEqualsNoOrder(reservablesInNewResWindow.toArray(), reservablesInCalenderTab.toArray(), "Available Rooms are displayed from" +region+ "region");

		
		driver.navigate().refresh();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region);

		setDate(date);

		setTimeFrom(from);

		setTimeUntil(until);

		waitForExtJSAjaxComplete(10);

		clickSearch();

		clickOnPrevOrNextDayInNewReservationWindow("next");

		waitForExtJSAjaxComplete(15);

		softAssert.assertEquals(getDate(),getNextDayDateForRandomDate(date),"Results are searched based on modified next day date" );

		date = getDate();

		clickOnPrevOrNextDayInNewReservationWindow("previous");

		waitForExtJSAjaxComplete(15);

		softAssert.assertEquals(getDate(),getPreviousDateForRandomDate(date),"Results are searched based on modified previous day date" );

		selectAlreadyInRoom(eqpCategory);

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		reservablesInNewResWindow = findRoomsFreeForRegionGivenTime();

		for(String room : reservablesInNewResWindow)
		{
			isIncludedEquipmentPresent( room, eqpCategory );

			softAssert.assertTrue(isIncludedEquipmentPresent( room, eqpCategory ), "category is displayed correctly");
		}

		clearTheSelectionInAlreadyRoomField("1preGnEqRef");

		waitForExtJSAjaxComplete(10);

		setRoomCategory(category);

		waitForExtJSAjaxComplete(10);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		List<String> reservablesInNewResWindowForCategory = findRoomsFreeForRegionGivenTime();
		
		System.err.println(reservablesInNewResWindowForCategory);

		waitForExtJSAjaxComplete(10);

		waitAndClick(XPATH_CALENDAR);

		waitForMaskDisappear();

		objCalendar.clickRoomsTabInCalendarTab();

		waitForExtJSAjaxComplete(10);

		objCalendar.filterByCategory(category);

		waitForExtJSAjaxComplete(20);

		reservablesInCalenderTab =  objCalendar.findRoomsFreeForGivenTime(from);
		
		System.err.println(reservablesInCalenderTab);

		waitForExtJSAjaxComplete(10);

		softAssert.assertEqualsNoOrder(reservablesInNewResWindowForCategory.toArray(), reservablesInCalenderTab.toArray(), "Rooms with specified "+ category +" Category are displayed" );

		waitForExtJSAjaxComplete(20);

		driver.navigate().refresh();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region);

		setDate(date);

		setTimeFrom(from);

		setTimeUntil(until);

		waitForExtJSAjaxComplete(5);

		setRoomPurpose(purpose);

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		reservablesInNewResWindow = findRoomsFreeForRegionGivenTime();

		for(String room : reservablesInNewResWindow)
		{
			clickLaunchReservation(room);

			waitForExtJSAjaxComplete(15);

			clickOrderItemsTab();

			clickDetailOrderItem();

			softAssert.assertEquals( getRoomPurposeInOrderItemsTab(),purpose,"Room's are searched based on purpose" );

			waitForExtJSAjaxComplete(5);

			clickCloseReservationUsingJS();

			waitForExtJSAjaxComplete(5);

			clickOnDialogButton("Close");

			waitForExtJSAjaxComplete(10);

			clickSearch();

			waitForExtJSAjaxComplete(20);
		}

		setRoomLayout(layOut);

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(10);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		reservablesInNewResWindow = findRoomsFreeForRegionGivenTime();

		for(String room : reservablesInNewResWindow)
		{
			clickLaunchReservation(room);

			waitForExtJSAjaxComplete(15);

			clickOrderItemsTab();

			clickDetailOrderItem();

			softAssert.assertEquals(getRoomLayoutInOrderItemsTab(),layOut,"Room's are searched based on layout" );

			waitForExtJSAjaxComplete(15);

			clickCloseReservationUsingJS();

			waitForExtJSAjaxComplete(5);

			clickOnDialogButton("Close");

			waitForExtJSAjaxComplete(10);

			clickSearch();

			waitForExtJSAjaxComplete(15);
		}

		softAssert.assertAll();

		Reporter.log("Searching available Reservable Objects via Parameters Panel is succesful.", true);

	}

	/**
	 * Testcase ID	:	C74260, C74266
	 * Author		:	KVE
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testOrderByCombobox(){

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C74260: Order By combobox with two options: â€œCapacityâ€� and â€œReferenceâ€� is available on Rooms Search Results page </span><br>"
				+ "Test: C74266:  Capacity of the room does not affect on sorting by reference </span><br>", true);


		int random = (int)(Math.random() * 10)+18;
		String date = this.getFutureDate(random);

		String region = "1preRgRef";
		String from = "13:00";
		String until = "14:00";
		String numberOfPeople = "10";


		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testOrderByCombobox");

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

		waitForExtJSAjaxComplete(10);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isOrderRoomsByComboBoxVisible(),"'Order By' combobox is displayed in Room");

		softAssert.assertTrue(getOrderByComboBox().equals("Capacity"),"capacity sorting is ok");

		List<String> reservablesInNewResWindow = findRoomsFreeForRegionGivenTime();

		List<Integer> roomCap  = new ArrayList<Integer>();

		for(String room: reservablesInNewResWindow){

			int roomCapacity = getRoomCapacity(room);
			
				roomCap.add( roomCapacity);	
			}

		/*List<Integer> sortRoomCap = new ArrayList<Integer>(roomCap);

		Collections.sort(sortRoomCap,  new Comparator(){
			@Override
			public int compare(Object arg0, Object  arg1) {
				Integer int1 = (Integer) arg0;
				Integer int2 = (Integer) arg1;
				return (int1<int2)? -1: (int1==int2)?0:1;
			}
		});*/
		
	
		System.out.println(roomCap);
		
		boolean status=isRoomCapactiyInSortedOrder(roomCap);

		softAssert.assertTrue(status,"Rooms are sorted based on capacity");

		setNrPeople(numberOfPeople);

		waitForExtJSAjaxComplete(10);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		setSorting("Reference");

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(getOrderByComboBox().equals("Reference"),"Reference sorting is ok");

		List<String> reservablesInNewResWindow1 = findRoomsFreeForRegionGivenTime();

		List<String> rooms = new ArrayList<String>();

		for (String current: reservablesInNewResWindow1) {

			rooms.add(current); 
		}

		List<String> roomsList = new ArrayList<String>();

		roomsList.addAll(rooms);

		Collections.sort(roomsList, new Comparator(){
			@Override
			public int compare(Object arg0, Object arg1) {
				String str1 = (String)arg0;
				String str2 =  (String)arg1;
				str1=str1.toLowerCase();
				str2= str2.toLowerCase();
				return str1.compareTo(str2);
			}});

		softAssert.assertEquals(rooms, roomsList, "Rooms are sorted based on Reference");

		clickParkingTab();

		clickSearch();

		softAssert.assertFalse(isOrderRoomsByComboBoxVisible(),"'Order By' combobox is not displayed in Parking");

		clickVehicleTab();

		clickSearch();

		softAssert.assertFalse(isOrderRoomsByComboBoxVisible(),"'Order By' combobox is not displayed in Vehicle");

		clickEquipmentTab();

		clickSearch();

		softAssert.assertFalse(isOrderRoomsByComboBoxVisible(),"'Order By' combobox is not displayed in Equipment");

		clickServiceTab();

		clickSearch();

		softAssert.assertFalse(isOrderRoomsByComboBoxVisible(),"'Order By' combobox is not displayed in Service");

		softAssert.assertAll();

		Reporter.log("Order By combobox: â€œCapacityâ€� and â€œReferenceâ€� is available on Rooms Search Results page and sorting was done successfully", true);

	}

	/**
	 * Testcase ID	:	C92695,C92696,C92697,C92698
	 * Author		:	KVE
	 *//*	
	//TO DO: Duplicated test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testSearchReservBasedOnRoomCapacity(){

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C92695: Reservations Search Room on Capacity: Nr of people is equal to Room capacity (MYM-31054) </span><br>"
				+ "Test: C92696: Reservations Search Room on Capacity: Nr of people is less than Room capacity (MYM-31054) </span><br>"
				+ "Test: C92697: Reservations Search Room on Capacity: Nr of people is bigger than Room capacity (MYM-31054) </span><br>"
				+ "Test: C92698: Reservations Search Room on Capacity: Nr of people is not set (MYM-31054) </span><br>", true);


		int random = (int)(Math.random() * 10)+18;
		String date = this.getFutureDate(random);

		String region = "1preRgRef";
		String from = "13:00";
		String until = "14:00";
		String numberOfPeople = "6";
		int noOfPeople = 6;
		int noOfPeople1 = 5;


		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testSearchReservBasedOnRoomCapacity");

		ReservationPageObject Obj = new ReservationPageObject();

		ReservationsGeneralSettingsAdministrationPageObject objgs=new ReservationsGeneralSettingsAdministrationPageObject();

		HelpDeskFrontOfficePageObject objhd=new HelpDeskFrontOfficePageObject();

		AdministrationPageObject objad=new  AdministrationPageObject();

		List<String> mandatoryFieldNames=Arrays.asList("Nr. of People");

		List<String> visibleFieldNames=Arrays.asList("Nr. of People");

		try{
			
		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation"); 

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		Obj.setRegion(region);

		Obj.setDate(date);

		Obj.setTimeFrom(from);

		Obj.setTimeUntil(until);

		Obj.clickRoomTab();

		waitForExtJSAjaxComplete(10);

		Obj.clickSearch();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Obj.isSmallerButtonDisabled(),"'Smaller' button is disabled");

		softAssert.assertTrue(Obj.isBiggerButtonDisabled(),"'Bigger' button is disabled");

		softAssert.assertFalse(Obj.isAllButtonDisabled(),"'All' button is Enabled");

		waitForExtJSAjaxComplete(20);

		List<String> reservablesInNewResWindow = Obj.findRoomsFreeForRegionGivenTime();

		List<Integer> roomCap  = new ArrayList<Integer>();

		Hashtable <String, Integer> rooms = new Hashtable <String, Integer>();
		for(String room: reservablesInNewResWindow){

			int roomCapacity = Obj.getRoomCapacity(room);

			roomCap.add( roomCapacity);

			rooms.put(room, roomCapacity);
		}

		Collections.sort(roomCap,  new Comparator(){
			@Override
			public int compare(Object arg0, Object  arg1) {
				Integer int1 = (Integer) arg0;
				Integer int2 = (Integer) arg1;
				return (int1<int2)? -1: (int1==int2)?0:1;
			}
		});

		Integer []capacityVals = new Integer [roomCap.size()] ;

		roomCap.toArray(capacityVals);

		Obj.setNrPeople(numberOfPeople);

		waitForExtJSAjaxComplete(10);

		Obj.clickSearch();

		waitForExtJSAjaxComplete(20);

		List<String> roomsListPostSearch = Obj.findRoomsFreeForRegionGivenTime();

		int nextBigNum = Obj.nextLargestNumber(capacityVals,noOfPeople);

		for(String room: roomsListPostSearch){

			int roomCapacity = rooms.get(room);

			softAssert.assertEquals(roomCapacity,nextBigNum, "Rooms with Capacity equal to 8 are displayed");

		}

		//Smaller Button

		int nextSmallNum = Obj.nextSmallNumber(capacityVals,nextBigNum);

		while(!Obj.isSmallerButtonDisabled()){

			Obj.clickSmallerButton();

			waitForExtJSAjaxComplete(20);

			List<String> roomsListPostSearch3 = Obj.findRoomsFreeForRegionGivenTime();
			for(String room: roomsListPostSearch3){

				int roomCapacity = rooms.get(room);

				softAssert.assertEquals(roomCapacity,nextSmallNum, "Rooms with next smaller Capacity will be displayed");

			}

			nextSmallNum = Obj.nextSmallNumber(capacityVals, nextSmallNum);
		}

		softAssert.assertTrue(Obj.isSmallerButtonDisabled(), "button 'Smaller' is disabled");

		//Bigger Button

		int nextBigNum1 = Obj.nextLargestNumber(capacityVals, nextSmallNum);

		while(!Obj.isBiggerButtonDisabled()){

			Obj.clickBiggerButton();

			waitForExtJSAjaxComplete(20);

			List<String> roomsListPostSearch4 = Obj.findRoomsFreeForRegionGivenTime();
			for(String room: roomsListPostSearch4){

				int roomCapacity = rooms.get(room);

				softAssert.assertEquals(roomCapacity,nextBigNum1, "Rooms with the Bigger Capacity will be displayed");

			}

			nextBigNum1 = Obj.nextLargestNumber(capacityVals, nextBigNum1);
		}

		softAssert.assertTrue(Obj.isBiggerButtonDisabled(), "button 'Bigger' is disabled");

		//All Button

		Obj.clickAllButton();

		waitForExtJSAjaxComplete(20);

		List<String> roomsListPostSearch5 = Obj.findRoomsFreeForRegionGivenTime();
		for(String room: roomsListPostSearch5){

			int roomCapacity = rooms.get(room);

			int roomCapacityAll = Obj.getRoomCapacity(room);

			softAssert.assertEquals(roomCapacity,roomCapacityAll, "Rooms with the All Capacity will be displayed");

		}

		softAssert.assertTrue(Obj.isSmallerButtonDisabled(),"'Smaller' button is disabled");

		softAssert.assertTrue(Obj.isBiggerButtonDisabled(),"'Bigger' button is disabled");

		Obj.setNrPeople(numberOfPeople);

		waitForExtJSAjaxComplete(10);

		Obj.clickSearch();

		waitForExtJSAjaxComplete(20);

		List<String> roomsListSearch = Obj.findRoomsFreeForRegionGivenTime();

		int nextNum = Obj.nextLargestNumber(capacityVals,noOfPeople);

		for(String room: roomsListSearch){

			int roomCapacity = rooms.get(room);

			softAssert.assertEquals(roomCapacity,nextNum, "Rooms with Capacity equal to 8 are displayed");

		}

		softAssert.assertFalse(Obj.isSmallerButtonDisabled(),"'Smaller' button is enabled");

		softAssert.assertFalse(Obj.isBiggerButtonDisabled(),"'Bigger' button is enabled");

			objgs.clickAdministration();

			waitForExtJSAjaxComplete(20);

			objgs.expandModuleSettings();

			waitForExtJSAjaxComplete(20);

			objad.expandReservations();

			objad.clickReservationsGenSetings();

			waitForExtJSAjaxComplete(20);

			objgs.setDefaultValuesForCapacityFields("Nr. of People","5");

			objgs.checkMandatoryFieldsCheckBox(mandatoryFieldNames);

			objgs.uncheckVisibleColumnCheckBox(visibleFieldNames);

			waitForExtJSAjaxComplete(20);

			objhd.clickOnSaveChangesInGeneralSettings();

			waitForExtJSAjaxComplete(20);

			navigateToMainPage();

			waitForExtJSAjaxComplete(20);

			expandAdministration();

			waitForExtJSAjaxComplete(20);

			expandSubMainMenu("Reservation"); 

			waitForExtJSAjaxComplete(20);

			waitAndClick(XPATH_NEWRESERVATIONS);

			waitForExtJSAjaxComplete(20);

			waitForExtJSAjaxComplete(20);

			Obj.setRegion(region);

			Obj.setDate(date);

			Obj.setTimeFrom(from);

			Obj.setTimeUntil(until);

			Obj.clickRoomTab();

			waitForExtJSAjaxComplete(10);

			Obj.clickSearch();

			waitForExtJSAjaxComplete(20);

			List<String> roomListSearch = Obj.findRoomsFreeForRegionGivenTime();

			for(String room: roomListSearch){

				int roomCapacity = rooms.get(room);

				softAssert.assertEquals(roomCapacity,noOfPeople1, "Rooms with Capacity equal to 5 are displayed");

			}
		}
		finally{

			this.navigateToMainPage();

			waitForExtJSAjaxComplete(20);

			waitForExtJSAjaxComplete(20);

			objgs.clickAdministration();

			waitForExtJSAjaxComplete(20);

			objgs.expandModuleSettings();

			waitForExtJSAjaxComplete(20);

			objad.expandReservations();

			waitForExtJSAjaxComplete(20);

			objad.clickReservationsGenSetings();

			waitForExtJSAjaxComplete(20);

			waitForExtJSAjaxComplete(20);

			objgs.setDefaultValuesForCapacityFields("Nr. of People","0");

			waitForExtJSAjaxComplete(20);

			objgs.checkVisibleColumnCheckBox(visibleFieldNames);

			waitForExtJSAjaxComplete(20);

			objgs.uncheckMandatoryFieldsCheckBox(mandatoryFieldNames);

			waitForExtJSAjaxComplete(20);

			objhd.clickOnSaveChangesInGeneralSettings();

			waitForExtJSAjaxComplete(20);

		}

		softAssert.assertAll();

		Reporter.log("Searching the Reservations based on Room capacity was done successfully", true);

	}*/
}
