package test.generalweb.reservation;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.Timer;
import test.framework.webelement.DropDown;
//import test.framework.webelement.DropDown;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import test.framework.webelement.Tree;





public class ReservationAccessTestSuite extends
		ReservationsCalendarNewPageObject {

	
	/**
	 * Navigate to home page and relogin
	 */
	//@BeforeMethod

	public void navigateToMainPage(String NAME_FOR_RIGHT,String PASSWORD_FOR_RIGHT) {
		Timer timer = new Timer().start();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Before test method START", true);
		
		driver.get(getURLWithoutAQAParam());//"?aqa");
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		Reporter.log("Navigate to main page\n", true);
		waitForExtJSAjaxComplete(30);
		driver.findElement(By.xpath("//table[@id='top-account-menu']//button")).click();
		waitForExtJSAjaxComplete(30);
		driver.findElement(By.xpath("//span[text()='Logout']")).click();
		Reporter.log("Logout <br>", true);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		driver.navigate().to(configuration.getApplicationUrl());
		waitForExtJSAjaxComplete(20);
		driver.manage().deleteAllCookies();
		waitForExtJSAjaxComplete(30);
		driver.navigate().refresh();
		waitForExtJSAjaxComplete(30);
		login(NAME_FOR_RIGHT, PASSWORD_FOR_RIGHT);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		Reporter.log("Login to back-end under account "+NAME_FOR_RIGHT +" END\n", true);
		Reporter.log("<br />");
		Reporter.log("Before test method END"+ " (" + timer.stop() + "ms)\n", true);
		Reporter.log("<br />");
	}
	

	
	
	/**
	 * Testcase ID	:	C74400
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testDefaultAuthForReservbleParking() throws Exception{
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C74400:Default Authorization for a Reservable parking set as No Access, Read Only or Blind Read Only Access cannot be booked"
				+"</span><br>", true);

		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String parking = "DefaultAuthPrkRef";
		String date = getFutureDate(random);
		String from = "10:30";
		String until = "12:00";
		String regions = "All Regions";
		String user1="aqa_Defaultathen";
		String password="qwerty";
		String user2="aqa_Defaultathen1";
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testDefaultAuthForReservbleParking");
		
		waitForExtJSAjaxComplete(20);
		
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
				
		waitForExtJSAjaxComplete(20);
	
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(20);
		
		clickParkingTab();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		boolean status=isDisplayLaunchReservation(parking);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(status,"Reservable parking is displayed");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPage(user1,password) ;
		
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
	
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(20);
		
		clickParkingTab();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		status=isDisplayLaunchReservation(parking);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(status,"Reservable parking is not displayed");
		
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPage(user2,password) ;
		
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
		
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(20);
		
		clickParkingTab();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		status=isDisplayLaunchReservation(parking);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(status,"Reservable parking is not displayed");
		}
		finally{
			this.navigateToMainPage(configuration.getUserName(), configuration.getPassword());
		}
		
		softAssert.assertAll();
		
		Reporter.log("The parking for which Default Authorization is set as No Access, Read Only or Blind Read Only Access are not displayed and cannot be booked..", true);
	}

	/**
	 * Testcase ID	:	C74370
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testDefaultAuthForReservbleVehicle() throws Exception{
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C74370:Default Authorization for a Reservable vehicle set as No Access, Read Only or Blind Read Only Access cannot be booked"
				+"</span><br>", true);

		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String vehicle = "DefaultAuthVhRef";
		String date = this.getFutureDate(random);
		String from = "10:30";
		String until = "12:00";
		String regions = "All Regions";
		String user1="aqa_Defaultathen";
		String password="qwerty";
		String user2="aqa_Defaultathen1";
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testDefaultAuthForReservbleVehicle");
		
		waitForExtJSAjaxComplete(20);
		
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
				
		waitForExtJSAjaxComplete(20);
	
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(20);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		boolean status=isDisplayLaunchReservation(vehicle);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(status,"Reservable vehicle is displayed");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPage(user1,password) ;
		
		waitForExtJSAjaxComplete(20);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForMaskDisappear();
		
		setRegion(region);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(20);
	
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(20);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		status=isDisplayLaunchReservation(vehicle);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(status,"Reservable vehicle is not displayed");
		
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPage(user2,password) ;
		
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
		
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(20);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		status=isDisplayLaunchReservation(vehicle);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(status,"Reservable vehicle is not displayed");
		
		softAssert.assertAll();
		
		Reporter.log("The vehicle for which Default Authorization is set as No Access, Read Only or Blind Read Only Access are not displayed and cannot be booked..", true);
	}
	
	
	/**
	 * Testcase ID	:	C74456
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testDefaultAuthForReservbleEquipment() throws Exception{
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C74456:Default Authorization for a Reservable equipment set as No Access, Read Only or Blind Read Only Access cannot be booked"
				+"</span><br>", true);

		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String equipment = "DefaultAuthEqpRef";
		String date = this.getFutureDate(random);
		String from = "10:30";
		String until = "12:00";
		String regions = "All Regions";
		String user1="aqa_Defaultathen";
		String password="qwerty";
		String user2="aqa_Defaultathen1";
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testDefaultAuthForReservbleEquipment");
		
		
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
				
		waitForExtJSAjaxComplete(20);
	
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(20);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		boolean status=isDisplayLaunchReservation(equipment);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(status,"Reservable equipment is displayed");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPage(user1,password) ;
		
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
	
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(20);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		status=isDisplayLaunchReservation(equipment);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(status,"Reservable equipment is not displayed");
		
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPage(user2,password) ;
		
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
		
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(20);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		status=isDisplayLaunchReservation(equipment);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(status,"Reservable equipment is not displayed");
		
		softAssert.assertAll();
		
		Reporter.log("The equipment for which Default Authorization is set as No Access, Read Only or Blind Read Only Access are not displayed and cannot be booked..", true);
	}
	
	
	/**
	 * Testcase ID	:	C74338
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testDefaultAuthForReservbleRoom() throws Exception{
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C74338:Default Authorization for a Reservable Room set as No Access, Read Only or Blind Read Only Access cannot be booked"
				+"</span><br>", true);

		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String room = "DefaultAuthRoomRef";
		String date = this.getFutureDate(random);
		String from = "10:30";
		String until = "12:00";
		String regions = "All Regions";
		String user1="aqa_Defaultathen";
		String password="qwerty";
		String user2="aqa_Defaultathen1";
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testDefaultAuthForReservbleRoom");
		
		
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
				
		waitForExtJSAjaxComplete(20);
	
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(20);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		boolean status=isDisplayLaunchReservation(room);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(status,"Reservable room is displayed");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPage(user1,password) ;
		
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
	
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(20);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		status=isDisplayLaunchReservation(room);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(status,"Reservable room is not displayed");
		
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPage(user2,password) ;
		
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
		
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(20);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		status=isDisplayLaunchReservation(room);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(status,"Reservable room is not displayed");
		
		softAssert.assertAll();
		
		Reporter.log("The Rooms for which Default Authorization is set as No Access, Read Only or Blind Read Only Access are not displayed and cannot be booked..", true);
	}
	
	
	/**
	 * Testcase ID	:	C74425
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testDefaultAuthForReservbleService() throws Exception{
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C74425:Default Authorization for a Reservable service set as No Access, Read Only or Blind Read Only Access cannot be booked"
				+"</span><br>", true);

		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String service = "DefaultAuthServRef";
		String date = this.getFutureDate(random);
		String from = "00:30";
		String until = "02:00";
		String regions = "All Regions";
		String user1="aqa_Defaultathen";
		String password="qwerty";
		String user2="aqa_Defaultathen1";
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testDefaultAuthForReservbleService");
		
		
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
	
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(20);
		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		boolean status=isDisplayLaunchReservation(service);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(status,"Reservable service is displayed");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPage(user1,password) ;
		
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
	
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(20);
		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		status=isDisplayLaunchReservation(service);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(status,"Reservable room is not displayed");
		
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPage(user2,password) ;
		
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
		
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(20);
		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		status=isDisplayLaunchReservation(service);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(status,"Reservable service is not displayed");
		
		softAssert.assertAll();
		
		Reporter.log("The service for which Default Authorization is set as No Access, Read Only or Blind Read Only Access are not displayed and cannot be booked..", true);
	}
	
	/**
	 * Testcase ID	:	C91028
	 * author		:	KVE
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testResrvGeneralFields() throws Exception{


		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"Test: C91028:Reservation General Fields: Date & Time fields validation (MYM-26574)</span><br>", true);


		String dateBeforeTheCurrent = getFutureDate(-1);

		int random = (int)(Math.random() * 10)+18;
		String date = getFutureDate(random);

		String from = "02:00";
		String until = "03:00";
		String Resrvfrom = "12:00";
		String Resrvuntil = "10:00";
		String from1 = "10:00";
		String until1 = "10:00";
		String from2 = "00:00";
		String until2 = "09:00";
		String from3 = "18:00";
		String until3 = "23:30";
		String region = "1preRgRef";
		String room = "2preRmRef";
		String prepdefaultTime = "00:00";
		String cleanupdefaultTime = "00:00";
		String prepTime1 = "01:00";
		String cleanupTime1 = "00:00";
		String prepTime2 = "00:00";
		String cleanupTime2 = "01:00";
		String responsible = getUserLastNameOfLoggedInUser();
		String user1="aqa_reservationaccess";
		String password="mcs";


		String timeOneHourBefore  = getNumberOfHrsFromCurrentSysTime(-1);

		String timeOneHourAfter  = getNumberOfHrsFromCurrentSysTime(1);

		String timeTwoHourAfter  = getNumberOfHrsFromCurrentSysTime(2);


		String warningMsg="With the Preparation time included, the Reservation does not fit in one day. Please decrease the Preparation Time or increase the From time.";
		
		/*String warningMsg1="Unable to save\n"+
				"\n"+
				"  - "+room+"\n"+
				"Reservation Line start and stop time have to fall inside Reservation start and stop time.\n"+
				"The Reservation Until time must be later than its Starting time.\n"+
				"- The Reservation Until time must be later than its Starting time.\n";
*/
		String warningMsg1="Reservation Line start and stop time have to fall inside Reservation start and stop time.\n";

		String warningMsg2="With the Clean up time included, the Reservation does not fit in one day. Please decrease the Clean up Time or decrease the Until time.";

		String warningMsg3="You are not allowed to book a Reservation in the past. Please contact your system administrator to grant you this right";
		
		String warningMsg4="Minimal Book Time for the Reservation should be 15 min\n";
		

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testResrvGeneralFields");


		expandAdministration();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region);

		waitForExtJSAjaxComplete(20);

		setDate(date);

		waitForExtJSAjaxComplete(20);

		clickRoomTab();

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(room);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		//Specify Reservation Time (e.g. 'From' - 00:00 - Till 09:00) and Specify the 'Preparation' Time for 1 hour. 

		setTimeFromReservationPanel(from2);

		waitForExtJSAjaxComplete(20);

		setTimeUntilReservationPanel(until2);

		waitForExtJSAjaxComplete(20);

		setTimePrepareFromReservationPanel(prepTime1);

		waitForExtJSAjaxComplete(20);

		setTimeCleanupFromReservationPanel(cleanupTime1);

		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);

		waitForExtJSAjaxComplete(20);

		clickConfirmReservation();

		waitForExtJSAjaxComplete(20);

		String text5=getWarningDialogTextMsg();

		softAssert.assertTrue(text5.contains(warningMsg),"Error message is displayed for Specifying Reservation Time From 00:00, until 09:00 and Specify the 'Preparation' Time for 1 hr");

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("OK"); 

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		//Specify Reservation Time (e.g. 'From' - 18:00 - Till 23:50) and Specify the 'Cleanup' Time for 1 hour. 
		setTimeFromReservationPanel(from3);

		waitForExtJSAjaxComplete(20);

		setTimeUntilReservationPanel(until3);

		waitForExtJSAjaxComplete(20);

		setTimeCleanupFromReservationPanel(cleanupTime2);

		waitForExtJSAjaxComplete(20);

		setTimePrepareFromReservationPanel(prepTime2);

		waitForExtJSAjaxComplete(20);

		clickConfirmReservation();

		waitForExtJSAjaxComplete(20);

		String text6=getWarningDialogTextMsg();
		
		//TO DO:BUG: error message is not displayed 
		softAssert.assertTrue(text6.contains(warningMsg2),"Error message is displayed for Specifying Reservation Time From 18:00, until 23:50 and Specify the 'Cleanup' Time for 1 hr");

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("OK");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		//Erase the values for 'Prepare' and 'Cleanup' Time and Confirm Reservation using user1

		navigateToMainPage(user1,password);

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region);

		waitForExtJSAjaxComplete(20);

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

		waitForExtJSAjaxComplete(20);

		setTimePrepareFromReservationPanel(prepdefaultTime);

		waitForExtJSAjaxComplete(20);

		setTimeCleanupFromReservationPanel(cleanupdefaultTime);

		waitForExtJSAjaxComplete(20);

		setResponsible(responsible);

		waitForExtJSAjaxComplete(20);

		clickConfirmReservation();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isReservationDone(), "Reservation is confirmed");

		//Insert incorrect values into the 'Prepare' and 'Cleanup' Time fields.
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		setTimePrepareFromReservationPanel(prepdefaultTime);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setTimeCleanupFromReservationPanel(cleanupdefaultTime);

		waitForExtJSAjaxComplete(20);

		clickConfirmReservation();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isReservationDone(), "Reservation is confirmed");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		//Specify the 'Until' Time before the 'From' Time (e.g. From 12 00 - Till 10 00).

		setTimeFromReservationPanel(Resrvfrom);

		waitForExtJSAjaxComplete(20);

		setTimeUntilReservationPanel(Resrvuntil);

		waitForExtJSAjaxComplete(20);

		clickConfirmReservation();

		waitForExtJSAjaxComplete(20);

		String text1=getWarningDialogTextMsg();
		
		System.out.println(text1);
		
		System.out.println(warningMsg1);

		softAssert.assertTrue(text1.contains(warningMsg1),"Error message is displayed for Specifying the 'Until' Time before the 'From' Time");

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("OK");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		//Specify the 'From' Time 1 hr before the current time and Specify 'Until' 1 hr after the current time.
		setDateFromReservationPanel(dateBeforeTheCurrent);

		waitForExtJSAjaxComplete(20);

		setTimeFromReservationPanel1(timeOneHourBefore);

		waitForExtJSAjaxComplete(20);

		setTimeUntilReservationPanel1(timeOneHourAfter);

		waitForExtJSAjaxComplete(20);

		clickConfirmReservation();

		waitForExtJSAjaxComplete(20);

		String text2=getWarningDialogTextMsg();

		softAssert.assertTrue(text2.contains(warningMsg3),"Error message is displayed for Specifying the 'From' Time 1 hr before the current time and Specifying 'Until' Time 1 hr after the current time");

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("OK");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		//Specify the 'From' Time 1 hr after the current time, 'Until' 2 hr's after the current time, 'From' Date and Day before current.

		setTimeFromReservationPanel1(timeOneHourAfter);

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		setTimeUntilReservationPanel1(timeTwoHourAfter);

		waitForExtJSAjaxComplete(20);

		clickConfirmReservation();

		waitForExtJSAjaxComplete(20);

		String text3=getWarningDialogTextMsg();

		softAssert.assertTrue(text3.contains(warningMsg3),"Error message is displayed for Specifying the 'From' Time 1 hr after the current time,'Until' Time 2 hr's after the current time and Specify 'From' Date a Day before current");

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("OK");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		//Specify the 'Until' Time equal to the 'From' Time (e.g. From 10 00 - Till 10 00).

		setTimeFromReservationPanel(from1);

		waitForExtJSAjaxComplete(20);

		setTimeUntilReservationPanel(until1);

		waitForExtJSAjaxComplete(20);

		clickConfirmReservation();

		waitForExtJSAjaxComplete(20);

		String text4=getWarningDialogTextMsg();

		softAssert.assertTrue(text4.contains(warningMsg4),"Error message is displayed for Specifying the 'Until' Time equal to the 'From' Time(From 10:00 - Till 10:00)");

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("OK");

		waitForExtJSAjaxComplete(20);
		
		clickCloseReservationUsingJS();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Close");
		
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

		checkRowInGriByTextValueAndClick(date+" "+from,room);

		waitForExtJSAjaxComplete(20);

		clickViewCancel();

		waitForExtJSAjaxComplete(20);

		clickGeneralTab();

		waitForExtJSAjaxComplete(20);

		clickCancelReservation();

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertAll();

		Reporter.log("successfully verified the Date & Time fields validations of Reservation General Fields<br>", true);

	}
	
	/**
	 * Testcase ID	:	C90058
	 * author		:	KVE
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testCancelResrvBasedOnReservableProperties() throws Exception{


		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"Test: C90058:Cancel Reservation: depending on Reservation Properties (MYM-26590)</span><br>", true);


		int random = (int)(Math.random() * 10)+18;
		String date = getFutureDate(random);


		String from = "10:00";
		String until = "12:00";
		String region = "1preRgRef";
		String regions = "All Regions";
		String room = "1preRmRef";
		String equipment = "1preRsGnEqRef";
		String vehicle = "1PreVehicleAuthRef";
		String parking = "1PreParkingAuthRef";
		String equipment1 = "1PreEquipmentAuthRef";
		String responsible =  getUserLastNameOfLoggedInUser();
		String calendarViewByWeek="View Week";
		String calendarViewBytwentyFourBySeven="View 24/7";
		String subjectRm="Room reservation";
		String subjectEq="Equipment reservation";
		String user1="aqa_reservationaccess1";
		String password="mcs";
		String date1 = "27-06-2016";
		String from1 = "01:00";


		String warningMsg="You are not allowed to view this Reservation";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testCancelResrvBasedOnReservableProperties");
		
		ReservationMyReservationsPageObject myReserv=new ReservationMyReservationsPageObject();

		ReservationsCalendarNewPageObject obj=new ReservationsCalendarNewPageObject();

		//Calendar is displayed for specified Reserved Room
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

		waitForExtJSAjaxComplete(20);

		setTimeFrom(from);

		waitForExtJSAjaxComplete(20);

		setTimeUntil(until);

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(room);

		waitForExtJSAjaxComplete(20);

		setGeneralSubject(subjectRm);

		waitForExtJSAjaxComplete(20);

		setResponsible(responsible);

		waitForExtJSAjaxComplete(20);

		clickConfirmReservation();

		waitForExtJSAjaxComplete(20);
		
		String reservID=getReserationID();

		clickCloseReservationUsingJS();

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		//Navigate to Calendar tab
		waitAndClick(XPATH_CALENDAR);

		waitForExtJSAjaxComplete(20);

		clickCalenderTab();
		
		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);

		obj.clickRoomsTabInCalendarTab();
		
		waitForMaskDisappear();

		obj.setRegionInCalendarTab(regions);

		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(10);
		
		obj.setDateViaDatePicker(date);

		waitForMaskDisappear();

		obj.filterItemByName(room);
		
		waitForMaskDisappear();

		obj.clickOnCalendarViews(calendarViewByWeek);

		waitForExtJSAjaxComplete(20);
	
		obj.clickOnCalendarViews(calendarViewBytwentyFourBySeven);

		waitForExtJSAjaxComplete(20);

		List<String>  hoursInDay=obj.getListOfHoursInDay();
		
		System.out.println(hoursInDay);

		List<String> totalHoursInDay = obj.getCalendarGridTimeRangeFromHeader("false","true");
		
		System.out.println(totalHoursInDay);

		softAssert.assertEquals(totalHoursInDay, hoursInDay,"Calendar is displayed for the Reservable Room pertain to the selected region from 00:00 to 24:00 hrs for the specified date");

		waitForExtJSAjaxComplete(20);

		//Calendar is displayed for specified Reservable Equipment
		navigateToMainPage();

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

		waitForExtJSAjaxComplete(20);

		setTimeFrom(from);

		waitForExtJSAjaxComplete(20);

		setTimeUntil(until);
		
		waitForExtJSAjaxComplete(20);
		
		clickEquipmentTab();

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(equipment);

		waitForExtJSAjaxComplete(30);

		setGeneralSubject(subjectEq);

		waitForExtJSAjaxComplete(30);

		setResponsible(responsible);

		waitForExtJSAjaxComplete(20);

		clickConfirmReservation();

		waitForExtJSAjaxComplete(20);
		
		String reservID1=getReserationID();
		
		String reservIDWithoutString1=reservID1.replace("Reservation", "").trim();

		clickCloseReservationUsingJS();

		waitForExtJSAjaxComplete(20);
		
		driver.navigate().refresh();
	
		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);

		//Navigate to Calendar tab
		waitAndClick(XPATH_CALENDAR);

		waitForExtJSAjaxComplete(20);

		clickCalenderTab();

		waitForExtJSAjaxComplete(20);

		waitForMaskDisappear();

		obj.clickEquipmentTabInCalendarTab();

		waitForMaskDisappear();

		obj.setRegionInCalendarTab(regions);

		waitForMaskDisappear();

		obj.setDateViaDatePicker(date);

		waitForMaskDisappear();

		obj.filterItemByName(equipment);
		
		waitForMaskDisappear();

		//TO DO: No need to click on tabs
		//obj.clickOnCalendarViews(calendarViewByWeek);
		//obj.clickOnCalendarViews(calendarViewBytwentyFourBySeven);

		List<String>  hoursInDay1=obj.getListOfHoursInDay();

		List<String> totalHoursInDay1 = obj.getCalendarGridTimeRangeFromHeader("false","true");

		softAssert.assertEquals(totalHoursInDay1, hoursInDay1,"Calendar is displayed for the Reservable Equipment pertain to the selected region from 00:00 to 24:00 hrs for the specified date");

		waitForExtJSAjaxComplete(20);  

		//C90058: Cancel Reservation from My Reservations

		navigateToMainPage();

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_MYRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		myReserv.setRegionMyReservation(regions);

		waitForExtJSAjaxComplete(20);

		myReserv.setTodayDateInMyReservation(date);

		waitForExtJSAjaxComplete(20);

		myReserv.clickSearchButton();

		waitForExtJSAjaxComplete(20);

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

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver,date+" "+from+"|"+room,"@class", "grid3"),"Room reservation is cancelled");

		waitForExtJSAjaxComplete(20);  

		//C90058: Cancel Reservation from Calendar Tab

		navigateToMainPage();

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_CALENDAR);

		waitForExtJSAjaxComplete(20);

		waitForMaskDisappear();

		obj.clickEquipmentTabInCalendarTab();

		waitForMaskDisappear();

		obj.setRegionInCalendarTab(regions);

		waitForMaskDisappear();

		obj.setDateViaDatePicker(date);

		waitForMaskDisappear();

		obj.filterItemByName(equipment);

		waitForMaskDisappear();

		obj.clickReservationInCalendar(equipment,reservIDWithoutString1);

		waitForExtJSAjaxComplete(20);

		//clickGeneralTab();

		waitForExtJSAjaxComplete(20);

		clickCancelReservation();

		clickOnDialogButton("Yes");

		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		obj.clickOnRefreshPaginationButtonofReservableObjects();
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver,date+" "+from+"|"+equipment,"@class", "grid3"),"Equipment reservation is cancelled");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		//C90058: Cancel Reservation from calendar with User2(Authorization --> Blind_Read_Only)

		navigateToMainPage(user1,password);

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_CALENDAR);

		waitForMaskDisappear();

		obj.clickVehiclesTabInCalendarTab();
		
		waitForMaskDisappear();

		obj.setRegionInCalendarTab(region);

		waitForMaskDisappear();

		obj.setDateViaDatePicker(date1);

		waitForMaskDisappear();

		//obj.clickOnCalendarViews(calendarViewByWeek);

		//waitForExtJSAjaxComplete(20);

		//waitForExtJSAjaxComplete(20);

		//obj.clickOnCalendarViews(calendarViewBytwentyFourBySeven);
		
		waitForExtJSAjaxComplete(20);
		
		obj.filterItemByName(vehicle);
		
		waitForExtJSAjaxComplete(20);

		waitForMaskDisappear();

		obj.clickReservationInCalendar(vehicle,"77");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		String text=getWarningDialogTextMsg();

		softAssert.assertTrue(text.contains(warningMsg),"Alert message is displayed You are not allowed to view this reservation because authorization is set to Blind_Read_Only.");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		//C90058: Cancel Reservation from calendar with User2(Authorization --> No Access)

		navigateToMainPage();

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_CALENDAR);

		waitForExtJSAjaxComplete(20);

		waitForMaskDisappear();

		obj.clickEquipmentTabInCalendarTab();

		waitForMaskDisappear();

		obj.setRegionInCalendarTab(region);

		waitForMaskDisappear();

		obj.setDateViaDatePicker(date1);

		waitForMaskDisappear();

		//obj.clickOnCalendarViews(calendarViewByWeek);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		//obj.clickOnCalendarViews(calendarViewBytwentyFourBySeven);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		//softAssert.assertTrue(McsElement.isElementPresent(driver,obj.CALENDAR_RESOURCE_WIN_XPATH+"//div[text()='"+equipment1+"']"),"Reservation for Equipment is not Visible because authorization is set to NO_ACCESS");


		//C90058: Cancel Reservation from MyReservation with User2(Authorization --> Read_Only)

		navigateToMainPage();

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_MYRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		myReserv.setRegionMyReservation(region);

		waitForExtJSAjaxComplete(20);

		myReserv.setTodayDateInMyReservation(date1);

		waitForExtJSAjaxComplete(20);

		myReserv.clickSearchButton();

		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();

		checkRowInGriByTextValueAndClick(date1+" "+from1,parking);

		waitForExtJSAjaxComplete(20);

		clickViewCancel();

		waitForExtJSAjaxComplete(20);

		clickGeneralTab();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isCancelResevationButtonEnabled(),"Cancel this reservation button is disabled and Reservation for Parking is not canceled because authorization is set to 'Read_Only'");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		//C90058: Cancel Reservation from Calendar with User2(Authorization --> Read_Only)

		navigateToMainPage();

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_CALENDAR);

		waitForMaskDisappear();

		obj.clickParkingTabInCalendarTab();

		waitForMaskDisappear();

		obj.setRegionInCalendarTab(region);

		waitForMaskDisappear();

		obj.setDateViaDatePicker(date1);

		waitForMaskDisappear();

		//obj.clickOnCalendarViews(calendarViewByWeek);

		//waitForExtJSAjaxComplete(20);

		//waitForExtJSAjaxComplete(20);

		//obj.clickOnCalendarViews(calendarViewBytwentyFourBySeven);

		waitForExtJSAjaxComplete(20);
		
		obj.filterItemByName(parking);

		waitForMaskDisappear();

		obj.clickReservationInCalendar(parking,"74");

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isCancelResevationButtonEnabled(),"Cancel this reservation button is disabled and Reservation for Parking is not canceled because authorization is set to 'Read_Only'");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20); 

		softAssert.assertAll();

		Reporter.log("successfully canceled the Reservations depending on Reservation Properties<br>", true);
	}
	/**
	 * Testcase ID	:	C91329
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testRoomReservInFewRegions() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:C91329 Posibility to make reservation when Room in few Reservation Regions </span><br>", true);

		Reporter.log("User reserves room only <br>", true);
		
		
		String region = "1aqaRgRef";
		String region1 = "1preRgRef";
		String region2 = "2preRgRef";
		String region3 = "All Regions";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "09-03-2020";
		String from = "09:00";
		String until = "12:00";
		
		
		String from1 = "13:00";
		String until1 = "14:00";
		
		String from2 = "15:00";
		String until2 = "16:00";
		
		String from3 = "16:00";
		String until3 = "17:00";
		
		String from4 = "10:00";
		String until4 = "16:00";
		
		String responsible =  getUserLastNameOfLoggedInUser();;
		String room = "RoomWithVPSRef";
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testRoomReservInFewRegions");
		
	
		 List<String> values =Arrays.asList(from,from1,from2,from3);

		expandAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		try{

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
		
		setRegion(region);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(room);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(this.isReservationDone(),"reservation is confirmed successfully");
		
		//Set Until /From time 13:00-14:00- Select Club 8 add to the reservation -> Save Reservation
		driver.navigate().refresh();
		
		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setRegion(region1);

		setDate(date);

		setTimeFrom(from1);

		setTimeUntil(until1);

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
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(this.isReservationDone(),"reservation is confirmed successfully");
		
		clickConfirmReservation();
		
		//Set Until /From time 15:00-16:00 Select Club 8 add to the reservation
		driver.navigate().refresh();
		
		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setRegion(region2);

		setDate(date);

		setTimeFrom(from2);

		setTimeUntil(until2);

		waitForExtJSAjaxComplete(20);

		clickRoomTab();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(room);
		
		setResponsible(responsible);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(this.isReservationDone(),"reservation is confirmed successfully");
		
		//Set Until /From time 16:00-17:00 Select Club 8 add to the reservation
		driver.navigate().refresh();
		
		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setRegion(region2);

		setDate(date);

		setTimeFrom(from3);

		setTimeUntil(until3);

		waitForExtJSAjaxComplete(20);

		clickRoomTab();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(room);
		
		setResponsible(responsible);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(this.isReservationDone(),"reservation is confirmed successfully");
		
		//Set Until /From time 10:00-16:00 Select Club 8 add to the reservation
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

		setTimeFrom(from4);

		setTimeUntil(until4);

		waitForExtJSAjaxComplete(20);

		clickRoomTab();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(this.isDisplayLaunchReservation(room),"reservation is not confirmed");
		
		}finally{
				
				navigateToMainPage();

				waitForExtJSAjaxComplete(20);

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

				ReservationMyReservationsPageObject myReserv=new ReservationMyReservationsPageObject();

				myReserv.setRegionMyReservation(region3);

				waitForExtJSAjaxComplete(20);

				myReserv.setTodayDateInMyReservation(date);

				waitForExtJSAjaxComplete(20);
				
				myReserv.setFutureDateMyReservation(date);

				myReserv.clickSearchButton();

				waitForExtJSAjaxComplete(20);

				for( String fromTime: values){
					
					waitForExtJSAjaxComplete(20);

					checkRowInGriByTextValueAndClick(date+" "+fromTime,room);

					waitForExtJSAjaxComplete(20);

					clickViewCancel();

					waitForExtJSAjaxComplete(20);

					clickGeneralTab();

					waitForExtJSAjaxComplete(20);

					clickCancelReservation();

					clickOnDialogButton("Yes");
					
					waitForExtJSAjaxComplete(20);
					
					softAssert.assertTrue(Grid.isRowInGridAbsent(driver,date+" "+fromTime+"|"+room,"@class", "grid3"),"Room reservations are cancelled");

				}
		}
		softAssert.assertAll();

		Reporter.log("successfully verified Room reservation  <br>", true);	
	}
	
	/**
	 * Testcase ID	:	C91065
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testAddEditRightsCheck() throws Exception{
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C91065:Reservations Add/Edit Rights check (MYM-26574)"
				+"</span><br>", true);

		
		String region = "2preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		String date = this.getFutureDate(random);
	
		String dateInMyReserv = "04-08-2016";
		
		String from = "02:00";
		String until = "03:00";
		String regions = "All Regions";
		String user="userEditAccess";
		String password="qwerty";
		String responsible =  getUserLastNameOfLoggedInUser();
		String room="roomEditRightsRef";
		String equipment="equipmentEditRightsRef";
		String errorMsg="- Responsible: You are not allowed to add a Reservation. Please contact your system administrator to grant you this right.";
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testAddEditRightsCheck");
		
		List<String> values=Arrays.asList(room,equipment);
		
		
		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		navigateToMainPage(user,password);
		
		waitForExtJSAjaxComplete(20);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(10);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		clickRoomTab();
		
		setRegion(region);
		
		waitForExtJSAjaxComplete(20);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(20);
	
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(20);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(room);
		
		waitForExtJSAjaxComplete(20);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(equipment);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(getWarningDialogTextMsg().contains(errorMsg),"error message is displayed for room, equipment");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		clickCloseReservationUsingJS();
		
		waitForExtJSAjaxComplete(10);
		
		clickOnDialogButton("Close");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(10);

		waitForMaskDisappear();

		ReservationMyReservationsPageObject myReserv=new ReservationMyReservationsPageObject();

		myReserv.setRegionMyReservation(regions);

		waitForExtJSAjaxComplete(20);

		myReserv.setTodayDateInMyReservation(dateInMyReserv);

		waitForExtJSAjaxComplete(10);

		myReserv.clickSearchButton();

		waitForExtJSAjaxComplete(20);

		for( String items: values){
			
			waitForExtJSAjaxComplete(20);

			checkRowInGriByTextValueAndClick(dateInMyReserv+" "+from,items);

			waitForExtJSAjaxComplete(20);

			clickViewCancel();

			waitForExtJSAjaxComplete(20);

			clickGeneralTab();

			waitForExtJSAjaxComplete(20);

			softAssert.assertTrue(isConfirmButtonDisabled(),"Reservation is not editable");
			
			waitForExtJSAjaxComplete(20);
		}
		
			
			softAssert.assertAll();

			Reporter.log("Successfully verified Reservations Add/Edit Rights check<br>", true);	
	}
	
		
	/* Testcase ID	:	C114452
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testDefaultAuthForReservbleRoomInCalendarPage() throws Exception{
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C114452:Default Authorization for a Reservable Room set as No Access, Read Only or Blind Read Only Access cannot be booked"
				+"</span><br>", true);

		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String room = "DefaultAuthRoomRef";
		String date = this.getFutureDate(random);
		String from = "10:30";
		String until = "12:00";
		String regions = "All Regions";
		String user1="aqa_Defaultathen";
		String password="qwerty";
		String user2="aqa_Defaultathen1";
		String user3="ssu";
		String calendarViewByWeek = "View Week";
		String calendarViewBytwentyFourBySeven = "View 24/7";
		SoftAssert softAssert = new SoftAssert();
		String unAvailableReservationTooTipMsg="The Resource is not available due to authentication level";
		softAssert.setMethodName("testDefaultAuthForReservbleRoomInCalendarPage");
		
		
		
		//Full access for aqa_reservations covered in C74459 
		
		waitForExtJSAjaxComplete(20);
		//Read Only
		navigateToMainPage(user1,password) ;
		
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
		
		filterItemByName(room);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		boolean status=isGridEmpty("No items available");
		
		softAssert.assertFalse(status,"Reservable room is displayed for Read only access");
		
		waitForExtJSAjaxComplete(20);
		
		String text=getToolTipTextOfUnAvailableReservation(room);
		
		softAssert.assertTrue(text.contains(unAvailableReservationTooTipMsg),"Tool tip message is displayed for unavailable reservation");
		
		//No Access
		navigateToMainPage(user2,password) ;
		
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
		
		filterItemByName(room);
	
		waitForExtJSAjaxComplete(20);
		
		status=isGridEmpty("No items available");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(status,"Reservable room is not displayed for No access");
		
		//Blind Read Only
		navigateToMainPage(user3,password) ;
		
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
		
		filterItemByName(room);
		
		waitForExtJSAjaxComplete(20);
		
		status=isGridEmpty("No items available");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(status,"Reservable room is displayed for Blind read only");
		
		waitForExtJSAjaxComplete(20);
		
		text=getToolTipTextOfUnAvailableReservation(room);
		
		softAssert.assertTrue(text.contains(unAvailableReservationTooTipMsg),"Tool tip message is displayed for unavailable reservation");
			
		softAssert.assertAll();
		
		Reporter.log("The Rooms for which Default Authorization is set as No Access, Read Only or Blind Read Only Access are not displayed and cannot be booked..", true);
	}
	
	
		//Blocked by bug:MYM-52279
		/* Testcase ID	:	C113879
		 * author		:	ssa
		 * 
		 */
		@Test(enabled = false, retryAnalyzer=RetryAnalyzer.class)
		public void testReservationCopyButtonRights() throws Exception {

			Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "C113879: Copy Reservation: Rights check (MYM-24564))"
					+ "</span><br>", true);
			
			
			String region = "1preRgRef";
			
			String date = "18-04-2015";
			String from="11:00";
			String room="1PreRoomRef";
			String user="aqa_NoRightsForStockInfo";
			String password="qwerty";
			String errorMsg="You are not allowed to add a Reservation.";
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("testReservationCopyButtonRights");
			
			
			navigateToMainPage(user,password) ;
			
			waitForExtJSAjaxComplete(20);
			
			expandAdministration();
			
			waitForExtJSAjaxComplete(20);

			expandSubMainMenu("Reservation");
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
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
			
			checkRowInGriByTextValueAndClick(date+" "+from,room);
			
			waitForExtJSAjaxComplete(20);
			
			clickViewCancel();
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(isCopyButtonDisabled(), "Copy button is disabled");
			
			String text=getToolTipTextForCopyButton();
			
			softAssert.assertEquals(text,errorMsg, "Error message is displayed for Copy button rights");
			
			softAssert.assertAll();
			
			Reporter.log("Copy Reservation: Rights check (MYM-24564))", true);
			
		}
		
	}
	

