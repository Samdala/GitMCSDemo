package test.generalweb.rightvisibility;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;

import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.Timer;
import test.generalweb.helpdesk.HelpDeskFrontOfficePageObject;
import test.generalweb.reservation.ReservationPageObject;
import test.generalweb.reservation.ReservationsGeneralSettingsAdministrationPageObject;




public class VisibilityReservationTestSuite extends
		AdministrationPageObject {
	
	
	/**
	 * Navigate to home page and relogin
	 */
	@BeforeMethod
	public void navigateToMainPage() {
		try{
		Timer timer = new Timer().start();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Before test method START", true);
		driver.get(configuration.getApplicationUrl()+"?aqa");
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Navigate to main page\n", true);
		driver.findElement(By.xpath("//table[@id='top-account-menu']//button")).click();
		waitForExtJSAjaxComplete(25);
		driver.findElement(By.xpath("//span[text()='Logout']")).click();
		Reporter.log("Logout <br>", true);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		driver.navigate().to(configuration.getApplicationUrl());
		waitForExtJSAjaxComplete(25);
		driver.manage().deleteAllCookies();
		waitForExtJSAjaxComplete(25);
		driver.navigate().refresh();
		login(NAME_FOR_RIGHT, PASSWORD_FOR_RIGHT);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Login to back-end under account "+NAME_FOR_RIGHT +" END\n", true);
		Reporter.log("<br />");
		Reporter.log("Before test method END"+ " (" + timer.stop() + "ms)\n", true);
		Reporter.log("<br />");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


	/**
	 * Testcase ID	:	c14658
	 * author		:	ssa
	 * 
	 */

	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testRightForReservation() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Reserving rights check</span>: c14658 <br>", true);

		Reporter.log("Reserving rights check <br>", true);
		
		

		String until = "11:00";
		String room = "1preRmRef";
		
		String region = "1preRgRef";
		
	
		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testRightForReservation");

		ReservationsGeneralSettingsAdministrationPageObject obj=new ReservationsGeneralSettingsAdministrationPageObject();
		ReservationPageObject objRes = new ReservationPageObject();
		HelpDeskFrontOfficePageObject objhd=new HelpDeskFrontOfficePageObject();
		
		try{
		clickAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		expandModuleSettings();
		
		waitForExtJSAjaxComplete(20);
		
		expandReservations();
		
		clickReservationsGenSetings();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setWebAccountGroup("web right group");
		
		waitForExtJSAjaxComplete(20);
		
		clickOnUnCheckInRow("Show Participants","Reservation Behavior Settings","4");
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		objhd.clickOnSaveChangesInGeneralSettings();
		
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPageOnly();
		
		waitForExtJSAjaxComplete(20);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		objRes.setRegion(region);
		
		setTimeUntil(until);
		
		waitForExtJSAjaxComplete(25);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(25);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickLaunchReservation(room);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(driver.findElement(By.xpath("//span[text()='Participants']/../..")).getAttribute("class").contains("hide"),"participant tab is absent");
		}
		finally{
			clickAdministration();
			
			waitForExtJSAjaxComplete(20);
			
			expandModuleSettings();
			
			waitForExtJSAjaxComplete(20);
			
			expandReservations();
			
			clickReservationsGenSetings();
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			setWebAccountGroup("web right group");
			
			clickOnDialogButton("Yes");
			
			waitForExtJSAjaxComplete(20);
			
			clickOnCheckInRow("Show Participants","Reservation Behavior Settings","4");
			
			waitForExtJSAjaxComplete(20);
			
			objhd.clickOnSaveChangesInGeneralSettings();
			
		}
		
		softAssert.assertAll();
		
		Reporter.log("Reservation rights were sucessfully checked", true);
	}
	
	/**
	 * Testcase ID	:	c14658
	 * author		:	ssa
	 * 
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testRightForReservation1() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Reserving rights check</span>: c14658 <br>", true);

		Reporter.log("Reserving rights check <br>", true);
		
	
		
		List<String> visibleFieldNames=Arrays.asList("Allow booking Rooms","Allow booking Equipment","Allow booking Parking Spaces","Allow booking Vehicles","Allow booking Services");

		
		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testRightForReservation");

		ReservationsGeneralSettingsAdministrationPageObject obj=new ReservationsGeneralSettingsAdministrationPageObject();
		
	
		try{
			
		clickAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		expandModuleSettings();
		
		waitForExtJSAjaxComplete(20);
		
		expandReservations();
		
		waitForExtJSAjaxComplete(20);
		
		clickReservationsGenSetings();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setWebAccountGroup("web right group");
		
		waitForExtJSAjaxComplete(20);
		
		clickOnUnCheckInRow("Allow booking Services","Reservation Behavior Settings","4");
		
		waitForExtJSAjaxComplete(20);
		
		 obj.clickOnSaveChangesInGeneralSettings();
		
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPageOnly();
		
		waitForExtJSAjaxComplete(20);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(driver.findElement(By.xpath(PARAMETER_SERVICE_XPATH+"/../../../..")).getAttribute("style").contains("none"),"service tab is absent");
		
		clickAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		expandModuleSettings();
		
		expandReservations();
		
		clickReservationsGenSetings();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setWebAccountGroup("web right group");
		
		waitForExtJSAjaxComplete(20);
		
		clickOnUnCheckInRow("Allow booking Vehicles","Reservation Behavior Settings","4");
		
		 obj.clickOnSaveChangesInGeneralSettings();
		
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPageOnly();
		
		waitForExtJSAjaxComplete(20);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(driver.findElement(By.xpath(PARAMETER_VEHICLE_XPATH+"/../../../..")).getAttribute("style").contains("none"),"vehicles tab is absent");
		
		clickAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		expandModuleSettings();
		
		expandReservations();
		
		clickReservationsGenSetings();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setWebAccountGroup("web right group");
		
		waitForExtJSAjaxComplete(20);
		
		clickOnUnCheckInRow("Allow booking Equipment","Reservation Behavior Settings","4");
		
		obj.clickOnSaveChangesInGeneralSettings();
		
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPageOnly();
		
		waitForExtJSAjaxComplete(20);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(driver.findElement(By.xpath(PARAMETER_EQUIPMENT_XPATH+"/../../../..")).getAttribute("style").contains("none"),"equipment tab is absent");
		
		clickAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		expandModuleSettings();
		
		expandReservations();
		
		clickReservationsGenSetings();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setWebAccountGroup("web right group");
		
		waitForExtJSAjaxComplete(20);
		
		clickOnUnCheckInRow("Allow booking Parking Spaces","Reservation Behavior Settings","4");
		
		obj.clickOnSaveChangesInGeneralSettings();
		
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPageOnly();
		
		waitForExtJSAjaxComplete(20);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(driver.findElement(By.xpath(PARAMETER_PARKING_XPATH+"/../../../..")).getAttribute("style").contains("none"),"parking tab is absent");
		
		clickAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		expandModuleSettings();
		
		expandReservations();
		
		clickReservationsGenSetings();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setWebAccountGroup("web right group");
		
		waitForExtJSAjaxComplete(20);
		
		clickOnCheckInRow("Allow booking Equipment","Reservation Behavior Settings","4");
		
		waitForExtJSAjaxComplete(20);
		
		clickOnUnCheckInRow("Allow booking Rooms","Reservation Behavior Settings","4");
		
		obj.clickOnSaveChangesInGeneralSettings();
		
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPageOnly();
		
		waitForExtJSAjaxComplete(20);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(driver.findElement(By.xpath(PARAMETER_ROOM_XPATH+"/../../../..")).getAttribute("style").contains("none"),"room tab is absent");
	}
		finally{

			this.navigateToMainPage();

			waitForExtJSAjaxComplete(20);

			waitForExtJSAjaxComplete(20);

			clickAdministration();

			waitForExtJSAjaxComplete(20);

			expandModuleSettings();

			waitForExtJSAjaxComplete(20);

			expandReservations();

			waitForExtJSAjaxComplete(20);

			clickReservationsGenSetings();

			waitForExtJSAjaxComplete(20);

			waitForExtJSAjaxComplete(20);

			setWebAccountGroup("web right group");
			
			waitForExtJSAjaxComplete(20);

			obj.checkDefaultValueCheckBoxForFields(visibleFieldNames);

			waitForExtJSAjaxComplete(20);
			
			HelpDeskFrontOfficePageObject objhd=new HelpDeskFrontOfficePageObject();
			
			objhd.clickOnSaveChangesInGeneralSettings();

			waitForExtJSAjaxComplete(20);
		}

	
		softAssert.assertAll();
				
		Reporter.log("Reservation rights were sucessfully checked", true);
	}
}
