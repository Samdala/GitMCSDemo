package test.generalweb.reservation;


	import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

	import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

	import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.DropDown;
	//import test.framework.webelement.DropDown;
	import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import test.framework.webelement.Tree;
import test.generalweb.helpdesk.HelpDeskFrontOfficePageObject;
import test.generalweb.rightvisibility.AdministrationPageObject;




		public class ReservationsGeneralSettingsAdminTestSuite  extends
			ReservationPageObject {

		/**
		  * Testcase ID : C61542 & C61528
		  * Author  : ssa
		 * @throws ParseException 
		  */
		 @Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
		 public void testGeneralSettingForRoomReservation() throws ParseException{

		 Reporter.log("<span style='font-weight:bold;color:#000033'> "

		 +"test:C61528:  Make changes in Settings of General settings for Reservations and verify the same in application "
		 +"C61542:Make changes in Search section of General settings for Reservations and verify the same in New Reservation", true);

		
		
		 String region = "1preRgRef";
		 String date = "13-04-2020";
		 String calendarViewByWeek="View Week";
		 String calendarViewBytwentyFourBySeven="View 24/7";
		 String defaultValueForMrng="8:00";
		 String defaultValueForEvng="17:00";
		 String mrngFieldText="";
		 String evngFieldText="";
		 String mesgText="No resource types enabled in this dossier";
		

		 SoftAssert softAssert = new SoftAssert();

		 softAssert.setMethodName("testGeneralSettingForRoomReservation");

		 ReservationsGeneralSettingsAdministrationPageObject obj=new ReservationsGeneralSettingsAdministrationPageObject();

		 HelpDeskFrontOfficePageObject objhd=new HelpDeskFrontOfficePageObject();

		 AdministrationPageObject objad=new  AdministrationPageObject();

		 List<String> fieldNames = Arrays.asList("Allow booking Rooms","Allow booking Equipment","Allow booking Services","Allow booking Parking Spaces","Allow booking Vehicles");
		 
		 List<String> mandatoryFieldNames=Arrays.asList("Room Category","Room Purpose","Nr. of People","Room Layout");
		 
		 List<String> visibleFieldNames=Arrays.asList("Room Category","Room Purpose","Nr. of People","Room Layout");
		 
		 try{

			 obj.clickAdministration();

			 waitForExtJSAjaxComplete(20);

			 obj.expandModuleSettings();
			 
			 waitForExtJSAjaxComplete(20);

			 objad.expandReservations();

			 objad.clickReservationsGenSetings();

			 waitForExtJSAjaxComplete(20);
			 
			//Toggle 'ON' Default Value/Setting Check box for Show Rooms tab, Show Equipment tab, Show Services tab, Show Parking Spaces tab and Show Vehicles tab
			 obj.checkDefaultValueCheckBoxForFields(fieldNames);
			 
		    /*waitForExtJSAjaxComplete(20);
			 
			 //TO DO: Fields are removed
			mrngFieldText=obj.getDefaultValuesFormrngField("Morning");
			 
			 System.out.println(mrngFieldText);
			 
			 waitForExtJSAjaxComplete(20);
			  
			 evngFieldText=obj.getDefaultValuesForEvngField("Evening");
			 
			 waitForExtJSAjaxComplete(20);
			 
			 System.out.println(evngFieldText);
	
			 obj.selectDefaultValuesForSettingsTable("Morning",defaultValueForMrng);
			 
			 waitForExtJSAjaxComplete(20);
			 
			obj.selectDefaultValuesForSettingsTable("Evening",defaultValueForEvng);
			 
			 waitForExtJSAjaxComplete(20);
			 */
			//Toggle 'ON' visible field Check box for Show Rooms tab, Room Category,Room Purpose,Nr. of People,Room Layout
			 obj.checkVisibleColumnCheckBox(visibleFieldNames,"Search Fields");
			 
			 waitForExtJSAjaxComplete(20);
			 
			 //Toggle 'ON' mandatory field Check box for Show Rooms tab, Room Category,Room Purpose,Nr. of People,Room Layout
			 obj.checkMandatoryFieldsCheckBox(mandatoryFieldNames);
			 
			 waitForExtJSAjaxComplete(20);
			 
			 objhd.clickOnSaveChangesInGeneralSettings();
			 
			 waitForExtJSAjaxComplete(20);

			 driver.navigate().refresh();
			 
			 waitForExtJSAjaxComplete(20);

			 expandAdministration();

			 waitForExtJSAjaxComplete(20);

			 expandSubMainMenu("Reservation");

			 waitForExtJSAjaxComplete(20);

			 waitAndClick(XPATH_NEWRESERVATIONS);

			 waitForExtJSAjaxComplete(20);
			 
			 waitForMaskDisappear();

			 waitForExtJSAjaxComplete(20);
			 
			 //Verify all tabs are displayed in new reservation window
		
			 softAssert.assertTrue(isEquipmentTabVisible() ,"Equipment tab is present");
			 
			 softAssert.assertTrue(isRoomTabVisible() ,"Room tab is present");

			 softAssert.assertTrue(isServiceTabVisible() ,"Service tab is present");
			 
			 softAssert.assertTrue(isParkingTabVisible() ,"Parking tab is present");

			 softAssert.assertTrue(isVehicleTabVisible() ,"Vehicle tab is present");
			 
			 softAssert.assertTrue(isCategoryFieldVisible() ,"Room category tab is present");
			 
			 softAssert.assertTrue(isRoomPurposeFieldVisible() ,"Room purpose tab is present");
			 
			 softAssert.assertTrue(isRoomNrOfPeopleFieldVisible() ,"Room capacity tab is present");
			 
			 softAssert.assertTrue(isRoomLayoutFieldVisible() ,"Room layout tab is present");
			 
			 waitForExtJSAjaxComplete(20);
			 
			 // Verify Nr. of People, Room Category, Room Purpose and Room Layout fields are mandatory
			 softAssert.assertTrue(McsElement.isFieldMandatory(driver, "x-form-item", "roomCategory"), "roomCategory field has correct UI (Bold text, asterix)");
				
			 softAssert.assertTrue(McsElement.isFieldMandatory(driver, "x-form-item", "roomPurpose"), "Room Purpose field has correct UI (Bold text, asterix)");
			 
			 softAssert.assertTrue(McsElement.isFieldMandatory(driver, "x-form-item", "roomCapacity"), "room Capacity field has correct UI (Bold text, asterix)");
			 
			 softAssert.assertTrue(McsElement.isFieldMandatory(driver, "x-form-item", "roomLayout"), "room Layout field has correct UI (Bold text, asterix)");
			 
			 waitForExtJSAjaxComplete(20);
			 
			 expandAdministration();

			 waitForExtJSAjaxComplete(20);

			 expandSubMainMenu("Reservation");

			 waitForExtJSAjaxComplete(20);
			 
			 waitAndClick(XPATH_CALENDAR);
				
			 waitForExtJSAjaxComplete(20);
			
			 waitForExtJSAjaxComplete(20);
			
			 clickCalenderTab();
			
			 waitForMaskDisappear();
			 
			 waitForExtJSAjaxComplete(20);
			 
			 ReservationsCalendarNewPageObject calObj=new ReservationsCalendarNewPageObject();
			 
			 calObj.setRegionInCalendarTab(region);
			 
			 waitForMaskDisappear();
			 
			 waitForExtJSAjaxComplete(20);
			 
			 calObj.setDateViaDatePicker(date);
			 
			 waitForMaskDisappear();
			 
			 waitForExtJSAjaxComplete(20);
			 //Verify Default time range is displayed in Calendar View
			List<String> actualHoursInDay = calObj.getCalendarGridTimeRangeFromHeader("true","false");
			
			System.err.println(actualHoursInDay);
				
			String ExpWrkingHours[] = new String[]{"Mon 13", "Tue 14", "Wed 15", "Thu 16", "Fri 17"};
			boolean b178Format= Arrays.equals(ExpWrkingHours,actualHoursInDay.toArray());
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
					     
			b817Format = Arrays.equals(ExpWrkingHoursInDayofTwentyFourBySeven,actualHoursInDay.toArray());
			}

			softAssert.assertTrue((b178Format||b817Format),"Calendar is displayed for the Reservable vehicle pertain to the selected region from 00:00 to 19:00 hrs as per the date specified");		
			
			
			 waitForExtJSAjaxComplete(20);
			 
			 driver.navigate().refresh();

			 waitForExtJSAjaxComplete(20);

			 waitForExtJSAjaxComplete(20);

			 obj.clickAdministration();

			 waitForExtJSAjaxComplete(20);

			 obj.expandModuleSettings();

			 objad.expandReservations();

			 objad.clickReservationsGenSetings();

			 waitForExtJSAjaxComplete(20);

			 //TO DO: this steps are covered in testRightForReservation1 c14658
			//Toggle 'OFF' Default Value/Setting Check box for Show Rooms tab, Show Equipment tab, Show Services tab, Show Parking Spaces tab and Show Vehicles tab
			// obj.uncheckDefaultValueCheckBoxForFields(fieldNames);

			 waitForExtJSAjaxComplete(20);
			 
			//Toggle 'OFF' visible field Check box for Show Rooms tab, Room Category,Room Purpose,Nr. of People,Room Layout 
			 obj.uncheckVisibleColumnCheckBox(visibleFieldNames,"Search Fields");
			 
			 waitForExtJSAjaxComplete(20);
			 
			 //Toggle 'OFF' mandatory field Check box for Show Rooms tab, Room Category,Room Purpose,Nr. of People,Room Layout
			 obj.uncheckMandatoryFieldsCheckBox(mandatoryFieldNames);
			 
			 waitForExtJSAjaxComplete(20);
			 
			 objhd.clickOnSaveChangesInGeneralSettings();
			 
			 waitForExtJSAjaxComplete(20);

			 waitForExtJSAjaxComplete(20);
			 
			 driver.navigate().refresh();

			 waitForExtJSAjaxComplete(20);

			 expandAdministration();

			 waitForExtJSAjaxComplete(20);

			 expandSubMainMenu("Reservation");

			 waitForExtJSAjaxComplete(20);

			 waitAndClick(XPATH_NEWRESERVATIONS);

			 waitForExtJSAjaxComplete(20);
			 
			//TO DO: this steps are covered in testRightForReservation1
			/*verifyWarningDialogTextMessage(mesgText);
			 
			 softAssert.assertTrue( verifyWarningDialogTextMessage(mesgText) ,"Alert message is displayed");
			 
			 clickOnDialogButtonCustomized("OK");

			 waitForExtJSAjaxComplete(20);
			 
			 //Verify all tabs are not displayed in new reservation window
			 softAssert.assertFalse(isRoomTabVisible() ,"Room tab is not present");

			 softAssert.assertFalse(isEquipmentTabVisible() ,"Equipment tab is not present");

			 softAssert.assertFalse(isServiceTabVisible() ,"Service tab is not present");
			 
			 softAssert.assertFalse(isParkingTabVisible() ,"Parking tab is not present");

			 softAssert.assertFalse(isVehicleTabVisible() ,"Vehicle tab is not present");
			 */
			 softAssert.assertFalse(isCategoryFieldVisible() ,"Room category tab is not present");
			 
			 softAssert.assertFalse(isRoomPurposeFieldVisible() ,"Room purpose tab is not present");
			 
			 softAssert.assertFalse(isRoomNrOfPeopleFieldVisible() ,"Room capacity tab is not present");
			 
			 softAssert.assertFalse(isRoomLayoutFieldVisible() ,"Room layout tab is not present");
			 
			 waitForExtJSAjaxComplete(20);
			 
			 //Verify Nr. of People, Room Category, Room Purpose and Room Layout fields are mandatory
			 softAssert.assertFalse(McsElement.isFieldMandatory(driver, "x-form-item", "roomCategory"), "roomCategory field has not (Bold text, asterix)");
				
			 softAssert.assertFalse(McsElement.isFieldMandatory(driver, "x-form-item", "roomPurpose"), "room Purpose field has not (Bold text, asterix)");
			 
			 softAssert.assertFalse(McsElement.isFieldMandatory(driver, "x-form-item", "roomCapacity"), "room Capacity field has not (Bold text, asterix)");
			 
			 softAssert.assertFalse(McsElement.isFieldMandatory(driver, "x-form-item", "roomLayout"), "room Layout field has not (Bold text, asterix)");
			
			 waitForExtJSAjaxComplete(20);

			 waitForExtJSAjaxComplete(20);
		 	}
		 	
		 	finally{

			 this.navigateToMainPage();

			 waitForExtJSAjaxComplete(20);

			 waitForExtJSAjaxComplete(20);

			 obj.clickAdministration();

			 waitForExtJSAjaxComplete(20);

			 obj.expandModuleSettings();
			 
			 waitForExtJSAjaxComplete(20);

			 objad.expandReservations();
			 
			 waitForExtJSAjaxComplete(20);

			 objad.clickReservationsGenSetings();

			 waitForExtJSAjaxComplete(20);

			 waitForExtJSAjaxComplete(20);

			 obj.checkDefaultValueCheckBoxForFields(fieldNames);

			 waitForExtJSAjaxComplete(20);
			 
			 obj.checkVisibleColumnCheckBox(visibleFieldNames,"Search Fields");
			 
			 waitForExtJSAjaxComplete(20);
			 
			 obj.uncheckMandatoryFieldsCheckBox(mandatoryFieldNames);
			 
			 waitForExtJSAjaxComplete(20);
			 
			 //obj.selectDefaultValuesForSettingsTable("Morning",mrngFieldText);
			 
			 waitForExtJSAjaxComplete(20);
			 
			 //obj.selectDefaultValuesForSettingsTable("Evening",evngFieldText);
			 
			 waitForExtJSAjaxComplete(20);

			 objhd.clickOnSaveChangesInGeneralSettings();

			 waitForExtJSAjaxComplete(20);

		 }
		
		 softAssert.assertAll();

		 Reporter.log("successfully verified visibility of tabs and default values are displayed in New Reservations window <br>", true);
	 }	
		 
		 /**
		  * Testcase ID : C61542 & C61528
		  * Author  : ssa
		  */
		 @Test(enabled = false, retryAnalyzer=RetryAnalyzer.class)
		 public void testGeneralSettingForRoomReservation1(){

		 Reporter.log("<span style='font-weight:bold;color:#000033'> "

		 +"test:C61528:  Make changes in Settings of General settings for Reservations and verify the same in application "
		 +"C61542:Make changes in Search section of General settings for Reservations and verify the same in New Reservation", true);

		

		 SoftAssert softAssert = new SoftAssert();

		 softAssert.setMethodName("testGeneralSettingForRoomReservation");

		 ReservationsGeneralSettingsAdministrationPageObject obj=new ReservationsGeneralSettingsAdministrationPageObject();

		 HelpDeskFrontOfficePageObject objhd=new HelpDeskFrontOfficePageObject();

		 AdministrationPageObject objad=new  AdministrationPageObject();

		 
		 try{

			 obj.clickAdministration();

			 waitForExtJSAjaxComplete(20);

			 obj.expandModuleSettings();
			 
			 waitForExtJSAjaxComplete(20);

			 objad.expandReservations();

			 objad.clickReservationsGenSetings();

			 waitForExtJSAjaxComplete(20);
			 
			 obj.selectDefaultValuesForFromAndUntilSearchTable("Until","02:00");
			 
			 waitForExtJSAjaxComplete(20);
			 
			 obj.selectDefaultValuesForFromAndUntilSearchTable("From","01:00");

			 waitForExtJSAjaxComplete(20);
			 
			 obj.setDefaultValuesForCapacityFields("Nr. of People","2");
			 
			 waitForExtJSAjaxComplete(20); 
			 
			 obj.selectDefaultValuesForSearchTable("Room Layout","2preRmCmRef");
			 
			 waitForExtJSAjaxComplete(20);
			 
			 obj.selectDefaultValuesForSearchTable("Room Purpose","2preRmRsPrRef");
			 
			 waitForExtJSAjaxComplete(20);
			 
			 obj.selectDefaultValuesForSearchTable("Room Category","2preRmCtDescr");
			 
			 waitForExtJSAjaxComplete(20);
			 
			 obj.selectDefaultValuesForSearchTable("Region","2preRgRef");
			 
			 waitForExtJSAjaxComplete(20);
			 
			 obj.clickOnSaveChangesInGeneralSettings();
			 
			 waitForExtJSAjaxComplete(20);

			 driver.navigate().refresh();
			 
			 waitForExtJSAjaxComplete(20);

			 expandAdministration();

			 waitForExtJSAjaxComplete(20);

			 expandSubMainMenu("Reservation");

			 waitForExtJSAjaxComplete(20);

			 waitAndClick(XPATH_NEWRESERVATIONS);

			 waitForExtJSAjaxComplete(20);
			 
			 this.waitForMaskDisappear();
			 
			 waitForExtJSAjaxComplete(20);
			 
			 //Verify General setting default values are displayed in new reservation window
			 String roomCategory=getRoomCategory();
			 
			 softAssert.assertEquals(roomCategory,"2preRmCtDescr","Room Category default value in Admin is displayed in new reservation window");
			 
			 String roomPurpose=getRoomPurpose();
			 
			 softAssert.assertEquals(roomPurpose,"2preRmRsPrRef","Room Purpose default value in Admin is displayed in new reservation window");
			 
			 String roomLayout=getRoomLayout();
			 
			 softAssert.assertEquals(roomLayout,"2preRmCmRef","Room Layout default value in Admin is displayed in new reservation window");
			 
			 String roomCapacity=getRoomCapacity();
			 
			 softAssert.assertEquals("2", roomCapacity,"Until time default value in Admin is displayed in new reservation window");
			 
			 waitForExtJSAjaxComplete(20);
			 
			 String fromTime=getFromTime();
			 
			 softAssert.assertEquals(fromTime,"01:00","From time default value in Admin is displayed in new reservation window");
			 
			 waitForExtJSAjaxComplete(20);
			 
			 String untilTime=getUntilTime();
			 
			 softAssert.assertEquals(untilTime,"02:00","Until time default value in Admin is displayed in new reservation window");
			 
			 waitForExtJSAjaxComplete(20);
			
		 	}
		 	
		 	finally{

			 this.navigateToMainPage();

			 waitForExtJSAjaxComplete(20);

			 waitForExtJSAjaxComplete(20);

			 obj.clickAdministration();

			 waitForExtJSAjaxComplete(20);

			 obj.expandModuleSettings();
			 
			 waitForExtJSAjaxComplete(20);

			 objad.expandReservations();
			 
			 waitForExtJSAjaxComplete(20);

			 objad.clickReservationsGenSetings();

			 waitForExtJSAjaxComplete(20);
			
			 obj.selectDefaultValuesForSearchTable("Room Layout","");
			 
			 waitForExtJSAjaxComplete(20);
			 
			 obj.selectDefaultValuesForSearchTable("Room Purpose","");
			 
			 waitForExtJSAjaxComplete(20);
			 
			 obj.selectDefaultValuesForSearchTable("Room Category","");
			 
			 waitForExtJSAjaxComplete(20);
			 
			 obj.setDefaultValuesForCapacityFields("Nr. of People","0");
			 
			 waitForExtJSAjaxComplete(20);
			 
			 obj.selectDefaultValuesForFromAndUntilSearchTable("Until","02:00");
			 
			 waitForExtJSAjaxComplete(20);
			 
			 obj.selectDefaultValuesForFromAndUntilSearchTable("From","01:00");
			 
			 waitForExtJSAjaxComplete(20);
			 
			 obj.selectDefaultValuesForSearchTable("Region","");
			 
			 waitForExtJSAjaxComplete(30);

			 objhd.clickOnSaveChangesInGeneralSettings();

			 waitForExtJSAjaxComplete(20);

		 }
		
		 softAssert.assertAll();

		 Reporter.log("successfully verified visibility of tabs and default values are displayed in New Reservations window <br>", true);
	 }	  
		 
	 /**
	  * Testcase ID : C61547
	  * Author  : kve
	  */
	 @Test(enabled = false, retryAnalyzer=RetryAnalyzer.class)
	 public void testGeneralSettingForParkingReserv(){

		 Reporter.log("<span style='font-weight:bold;color:#000033'> "

		 +"test: C61547:  Make changes in Settings of General settings for Reservations and verify the same in Parking reservation ", true);

		 String from = "14:00";
		 String until = "15:00";
		 String region = "1preRgRef";
		 String room = "1preRmRef";
		 String responsible = "SELENIUM";

		 int random = (int)(Math.random() * 10)+18;
		 String date = "13-09-20"+random;
		 SoftAssert softAssert = new SoftAssert();

		 softAssert.setMethodName("testGeneralSettingForParkingReserv");

		 ReservationsGeneralSettingsAdministrationPageObject obj=new ReservationsGeneralSettingsAdministrationPageObject();

		 AdministrationPageObject objad=new  AdministrationPageObject();

		 HelpDeskFrontOfficePageObject objhd=new HelpDeskFrontOfficePageObject();

		 List<String> fieldNames = Arrays.asList("Show Participants","Show Costs including Tax in Summary");

		 try{

			 obj.clickAdministration();

			 waitForExtJSAjaxComplete(20);

			 obj.expandModuleSettings();
			 
			 waitForExtJSAjaxComplete(20);

			 objad.expandReservations();
			 
			 waitForExtJSAjaxComplete(20);

			 objad.clickReservationsGenSetings();

			 waitForExtJSAjaxComplete(20);
			 
			 obj.checkDefaultValueCheckBoxForFields(fieldNames);
			 
			 waitForExtJSAjaxComplete(20);

			 objhd.clickOnSaveChangesInGeneralSettings();

			 waitForExtJSAjaxComplete(20);

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

			 setDate(date);

			 setTimeFrom(from);

			 setTimeUntil(until);

			 waitForExtJSAjaxComplete(20);

			 clickSearch();

			 waitForExtJSAjaxComplete(20);

			 clickLaunchReservation(room);

			 softAssert.assertTrue(isParticipantsTabVisible() ,"participant tab is present");

			 waitForExtJSAjaxComplete(20);

			 setResponsible(responsible);

			 waitForExtJSAjaxComplete(20);

			 clickSummaryTab();

			 softAssert.assertTrue(isSummaryCostIncludeColumnVisbile(),"A summary is available for total cost Incl");

			 waitForExtJSAjaxComplete(20);

			 driver.navigate().refresh();

			 waitForExtJSAjaxComplete(20);

			 waitForExtJSAjaxComplete(20);

			 obj.clickAdministration();

			 waitForExtJSAjaxComplete(20);

			 obj.expandModuleSettings();

			 objad.expandReservations();

			 objad.clickReservationsGenSetings();

			 waitForExtJSAjaxComplete(20);

			 waitForExtJSAjaxComplete(20);

			 waitForExtJSAjaxComplete(20);

			 obj.uncheckDefaultValueCheckBoxForFields(fieldNames);

			 objhd.clickOnSaveChangesInGeneralSettings();

			 waitForExtJSAjaxComplete(20);

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

			 setDate(date);

			 setTimeFrom(from);

			 setTimeUntil(until);

			 waitForExtJSAjaxComplete(20);

			 clickSearch();

			 waitForExtJSAjaxComplete(20);

			 clickLaunchReservation(room);

			 waitForExtJSAjaxComplete(20);

			 softAssert.assertFalse(isParticipantsTabVisible(),"participant tab is not present");

			 waitForExtJSAjaxComplete(20);

			 setResponsible(responsible);

			 waitForExtJSAjaxComplete(20);

			 clickSummaryTab();

			 softAssert.assertFalse(isSummaryCostIncludeColumnVisbile(),"A summary is not available for total cost Incl");

			 waitForExtJSAjaxComplete(20);

		 }
		 finally{

			 driver.navigate().refresh();

			 waitForExtJSAjaxComplete(20);

			 waitForExtJSAjaxComplete(20);
			
			 obj.clickAdministration();

			 waitForExtJSAjaxComplete(20);

			 obj.expandModuleSettings();
			 
			 waitForExtJSAjaxComplete(10);

			 objad.expandReservations();
			 
			 waitForExtJSAjaxComplete(10);

			 objad.clickReservationsGenSetings();

			 this.waitForMaskDisappear();

			 waitForExtJSAjaxComplete(20);

			 obj.checkDefaultValueCheckBoxForFields(fieldNames);

			 objhd.clickOnSaveChangesInGeneralSettings();

		 }

		 softAssert.assertAll();

		 Reporter.log("successfully verified visibility of Participant tab and Cost Incl in New Reservations window <br>", true);
	 }
	 
	 
	 
 /**
	 * Testcase ID	:	C92695,C92696,C92697,C92698
	 * Author		:	KVE
	 */	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test(enabled = false, retryAnalyzer=RetryAnalyzer.class)
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
			
			waitForExtJSAjaxComplete(20);

			objgs.checkMandatoryFieldsCheckBox(mandatoryFieldNames);
			
			waitForExtJSAjaxComplete(20);

			objgs.uncheckVisibleColumnCheckBox(visibleFieldNames,"Search Fields");

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

			objgs.checkVisibleColumnCheckBox(visibleFieldNames,"Search Fields");

			waitForExtJSAjaxComplete(20);

			objgs.uncheckMandatoryFieldsCheckBox(mandatoryFieldNames);

			waitForExtJSAjaxComplete(20);

			objhd.clickOnSaveChangesInGeneralSettings();

			waitForExtJSAjaxComplete(20);

		}

		softAssert.assertAll();

		Reporter.log("Searching the Reservations based on Room capacity was done successfully", true);

	}

	/**
	 * TestCase : C111656
	 * Author   : MMA
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationDetailsFieldsVisibility() throws Exception{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C111656: Administration Settings should not affect Reservation Details Fields Visibility (MYM-26027) </span><br>", true);

		String region = "1preRgRef";
		int random = (int) (Math.random() * 10) + 18;
		String futureDate = this.getFutureDate(random);
		String date = futureDate;
		String room = "1PreRoomRef";
		String from = "12:00"; 
		String until = "13:00";
		String responsible = getUserLastNameOfLoggedInUser();
		boolean isReservation = false;
		String reservIDWithoutString = "";
		List<String> reservationFields = Arrays.asList("Confidential","Tentative","Prepare Time","Cleanup Time","Contact","Project","Subject","Remarks","Cost Center","GL Account","FIN_KEY_3");
		List<String> participantsAnnouncementFields= Arrays.asList("Phone","Location","License Plate");
		List<String> searchFields = Arrays.asList("Nr. of People","Room Category","Room Purpose","Room Layout");
		List<String> reservationBehaviorSettings = Arrays.asList("Allow booking Parking Spaces","Allow booking Vehicles","Allow booking Equipment","Allow booking Services");
		String calendarViewBytwentyFourBySeven="View 24/7";
		String calendarViewByWeek = "View Week";

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testReservationDetailsFieldsVisibility");

		ReservationsGeneralSettingsAdministrationPageObject obj=new ReservationsGeneralSettingsAdministrationPageObject();
		HelpDeskFrontOfficePageObject objhd=new HelpDeskFrontOfficePageObject();
		AdministrationPageObject objad=new  AdministrationPageObject();
		ReservationsCalendarNewPageObject objClndr = new ReservationsCalendarNewPageObject();

		try{
			//Administration - UnCheck Search Fields and Reservation Behavior Settings Fields
			waitForExtJSAjaxComplete(25);
			obj.clickAdministration();
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);

			obj.expandModuleSettings();
			waitForExtJSAjaxComplete(20);

			objad.expandReservations();
			objad.clickReservationsGenSetings();
			waitForExtJSAjaxComplete(20);

			objad.setWebAccountGroup("All groups");
			waitForExtJSAjaxComplete(20);

			obj.uncheckVisibleColumnCheckBox(searchFields,"Search Fields");
			waitForExtJSAjaxComplete(20);

			obj.uncheckDefaultValueCheckBoxForFields(reservationBehaviorSettings);
			waitForExtJSAjaxComplete(20);

			objhd.clickOnSaveChangesInGeneralSettings();
			waitForExtJSAjaxComplete(20);


			//New Reservation - Search Fields verification and Reservation Behavior Settings Fields
			expandAdministration();
			waitForExtJSAjaxComplete(20);

			expandSubMainMenu("Reservation");
			waitForExtJSAjaxComplete(20);

			waitAndClick(XPATH_NEWRESERVATIONS);
			waitForExtJSAjaxComplete(20);
			waitForExtJSAjaxComplete(20);

			softAssert.assertFalse(isEquipmentTabVisible() ,"Equipment tab is not present");
			softAssert.assertFalse(isVehicleTabVisible() ,"vehicle tab is not present");
			softAssert.assertFalse(isServiceTabVisible() ,"Service tab is not present");
			softAssert.assertFalse(isParkingTabVisible() ,"Parking tab is not present");

			softAssert.assertFalse(isCategoryFieldVisible(),"Room Category label is not displayed");
			softAssert.assertFalse(isRoomPurposeFieldVisible(),"Room Purpose label is not displayed");
			softAssert.assertFalse(isRoomNrOfPeopleFieldVisible(),"Nr. of People label is not displayed");
			softAssert.assertFalse(isRoomLayoutFieldVisible(),"Room Layout label is not displayed");

			closeModule("Reservation");
			waitForExtJSAjaxComplete(25);

			//Administration - Check Search Fields and Reservation Behavior Settings Fields
			obj.clickAdministration();
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);

			objad.clickReservationsGenSetings();
			waitForExtJSAjaxComplete(20);

			objad.setWebAccountGroup("All groups");
			waitForExtJSAjaxComplete(20);

			obj.checkVisibleColumnCheckBox(searchFields,"Search Fields");
			waitForExtJSAjaxComplete(20);

			obj.checkDefaultValueCheckBoxForFields(reservationBehaviorSettings);
			waitForExtJSAjaxComplete(20);

			//Administration - UnCheck Reservation Fields
			obj.uncheckVisibleColumnCheckBox(reservationFields,"Reservation Fields");
			waitForExtJSAjaxComplete(20);

			objhd.clickOnSaveChangesInGeneralSettings();
			waitForExtJSAjaxComplete(20);

			//New Reservation
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
			waitForExtJSAjaxComplete(20);

			clickLaunchReservation(room);
			waitForExtJSAjaxComplete(20);
			waitForExtJSAjaxComplete(20);

			setResponsible(responsible);
			waitForExtJSAjaxComplete(20);
			waitForExtJSAjaxComplete(20);

			clickConfirmReservation();
			waitForExtJSAjaxComplete(20);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(20);

			softAssert.assertTrue(isReservationDone(), "Reservation is confirmed");
			isReservation= true;
			String newResVID1 = getReserationID();
			waitForExtJSAjaxComplete(20);

			reservIDWithoutString = newResVID1.replace("Reservation", "").trim();

			driver.navigate().refresh();
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(20);
			expandAdministration();
			waitForExtJSAjaxComplete(20);

			expandSubMainMenu("Reservation");
			waitForExtJSAjaxComplete(20);

			//Calendar Page - Reservation Fields verification
			waitAndClick(XPATH_CALENDAR);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(20);
			waitForExtJSAjaxComplete(20);

			objClndr.clickRoomsTabInCalendarTab();
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(20);

			objClndr.clickOnCalendarViews(calendarViewByWeek);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(20);

			objClndr.clickOnCalendarViews(calendarViewBytwentyFourBySeven);
			waitForExtJSAjaxComplete(20);

			objClndr.setDateViaDatePicker(date);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);

			objClndr.setRegionInCalendarTab(region);
			waitForExtJSAjaxComplete(20);
			waitForExtJSAjaxComplete(20);

			objClndr.filterItemByName(room);
			waitForExtJSAjaxComplete(20);

			objClndr.clickReservationInCalendar(room, reservIDWithoutString);
			waitForExtJSAjaxComplete(20);

			softAssert.assertFalse(isReservableCheckBoxVisible("confidential"),"Confidential check box is not visible");
			softAssert.assertFalse(isReservableCheckBoxVisible("tentative"),"Tentative check box is not visible");
			softAssert.assertFalse(isLookupEnabled("contact"),"Contact lookup is not visible");
			softAssert.assertFalse(isLookupEnabled("project"),"Project lookup is not visible");
			softAssert.assertFalse(isReservableTextAreaDisplayed("remarks"),"remarks textarea is not visible");
			softAssert.assertFalse(isReservableTextAreaDisplayed("subject"),"subject textarea is not visible");

			clickTabInReservationDetailsSection("Financial");
			waitForExtJSAjaxComplete(20);

			softAssert.assertFalse(isLookupEnabled("costCenter"),"Cost Center lookup is not visible");
			softAssert.assertFalse(isLookupEnabled("glAccount"),"GL Account lookup is not visible");
			softAssert.assertFalse(isLookupEnabled("finKey3"),"FIN_KEY_3 lookup is not visible");
			softAssert.assertTrue(isLookupEnabled("finKey4"),"FIN_KEY_4 lookup is not visible");


			//Administration - check Reservation Fields
			obj.clickAdministration();
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);

			objad.clickReservationsGenSetings();
			waitForExtJSAjaxComplete(20);

			objad.setWebAccountGroup("All groups");
			waitForExtJSAjaxComplete(20);

			obj.checkVisibleColumnCheckBox(reservationFields,"Reservation Fields");
			waitForExtJSAjaxComplete(20);

			//Administration - UnCheck Participant Announcement Fields
			obj.uncheckVisibleColumnCheckBox(participantsAnnouncementFields,"Participant Announcement Fields");
			waitForExtJSAjaxComplete(20);

			objhd.clickOnSaveChangesInGeneralSettings();
			waitForExtJSAjaxComplete(20);

			//calendar - Participant Announcement Fields verification
			waitAndClick(XPATH_CALENDAR);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(20);

			clickCloseReservationUsingJS();
			waitForExtJSAjaxComplete(20);
			waitForExtJSAjaxComplete(20);

			objClndr.filterItemByName(room);
			waitForExtJSAjaxComplete(20);

			objClndr.clickReservationInCalendar(room, reservIDWithoutString);
			waitForExtJSAjaxComplete(20);

			clickParticipantTab();
			addOtherPerson("other","person");

			softAssert.assertFalse(isReservableLabelDisplayed("Phone"),"Phone label is not visible");
			softAssert.assertFalse(isReservableLabelDisplayed("License Plate"),"License Plate label is not visible");

			//Administration - Check Participant Announcement Fields
			obj.clickAdministration();
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);

			objad.clickReservationsGenSetings();
			waitForExtJSAjaxComplete(20);

			objad.setWebAccountGroup("All groups");
			waitForExtJSAjaxComplete(20);

			obj.checkVisibleColumnCheckBox(participantsAnnouncementFields,"Participant Announcement Fields");
			waitForExtJSAjaxComplete(20);

			objhd.clickOnSaveChangesInGeneralSettings();
			waitForExtJSAjaxComplete(20);
		}

		finally{

			//Administration - check all section fields
			navigateToMainPage();
			waitForExtJSAjaxComplete(25);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(25);

			obj.clickAdministration();
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);

			obj.expandModuleSettings();
			waitForExtJSAjaxComplete(20);

			objad.expandReservations();
			objad.clickReservationsGenSetings();
			waitForExtJSAjaxComplete(20);

			objad.setWebAccountGroup("All groups");
			waitForExtJSAjaxComplete(20);

			obj.checkVisibleColumnCheckBox(searchFields,"Search Fields");
			waitForExtJSAjaxComplete(20);

			obj.checkDefaultValueCheckBoxForFields(reservationBehaviorSettings);
			waitForExtJSAjaxComplete(20);

			obj.checkVisibleColumnCheckBox(reservationFields,"Reservation Fields");
			waitForExtJSAjaxComplete(20);

			obj.checkVisibleColumnCheckBox(participantsAnnouncementFields,"Participant Announcement Fields");
			waitForExtJSAjaxComplete(20);

			objhd.clickOnSaveChangesInGeneralSettings();
			waitForExtJSAjaxComplete(20);

			closeModule("Administration");
			waitForExtJSAjaxComplete(20);

			//cancel reservation
			if (isReservation = true) {
				waitForExtJSAjaxComplete(20);
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
				waitForMaskDisappear();
				waitForExtJSAjaxComplete(25);

				checkRowInGriByTextValueAndClick(date+" "+from,room);
				waitForExtJSAjaxComplete(25);

				clickViewCancel();
				waitForExtJSAjaxComplete(25);

				clickGeneralTab();
				waitForExtJSAjaxComplete(25);

				clickCancelReservation();
				clickOnDialogButton("Yes");
				waitForExtJSAjaxComplete(25);

				softAssert.assertTrue(Grid.isRowInGridAbsent(driver,date+" "+from+"|"+room,"@class", "grid3"),"Room reservation is cancelled");
			} 
			else {
				Reporter.log("Reservation not done");
			}
		}
		softAssert.assertAll();
		Reporter.log("Administration Settings should not affect Reservation Details Fields Visibility (MYM-26027)", true);
	}
}


		