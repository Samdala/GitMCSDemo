package test.generalweb.reservation;




import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.AbstractMcsTestSuite;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.DropDown;
//import test.framework.webelement.DropDown;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import test.framework.webelement.Tree;
import test.generalweb.helpdesk.HelpDeskFrontOfficePageObject;
import test.generalweb.rightvisibility.AdministrationPageObject;




public class ReservationRoomCRUDTestSuite extends
		ReservationPageObject {
	/**
	 * Testcase ID	:	C74299,C74294,C74296,C74300,C74301,C74302,C74303,C74310
	 * author		:	Intellias
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationRoomCreateDelete1() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74299: Book a Room <br>"
				+ "Test C74294: The value in Total field increases when a participant is added, but does not decrease when removed <br>"
				+ "Test C74296: Recently deleted Reservable item is not displayed after the Reservation Confirmation <br>"
				+ "Test C74300: Reservable Rooms pertain to same Region can only be changed from Order Items pane <br>"
				+ "Test C74301: From time cannot be beyond the Until time in Room Reservation <br>"
				+ "Test C74302: Department is auto populated if select Responsible field while reserving a Room <br>"
				+ "Test C74303: In Room reservation a Department value can be modified for the same company only <br>"
				+ "Test C74310: Services that are not specified as allowed services to a Reservable Room can also be added, while reserving that Room </span><br>", true);

		Reporter.log("User reserves room <br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "1-01-20"+random;
		String from = "00:00";
		String fromEd = "00:15";
		String fromWrong = "01:00";
		String until = "00:30";
		String numberPeople = "2";
		String responsible = getUserLastNameOfLoggedInUser();
		String room = "1preRmRef";
		String room2 = "2preRmRef";
		String room3 = "3preRmRef";
		String category = "1preRmCtDescr";
		String purpose = "1preRmRsPrRef";
		String purpose2 = "2preRmRsPrRef";
		String layout = "1preRmCmCod";
		String service = "1preSrvRef";
		String service2 = "2preSrvRef";
		String equipment = "1preRsGnEqRef";
		String vehicle = "1preRsVhRef";
		String parking = "1prePrkEqRef";
		String employee = getUserLastNameOfLoggedInUser();
		String contact = "1preContactLast";
		String company = "My Company";
		String first = "otherFrist";
		String last = "otherLast";
		String department = "AQA_DEPARTMENT";
		String wrongDepartment = "2preDepartName"; 
		String department2 = "3preDepartName";
		String numberPeopleExceed = "200";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReservationRoomCreateDelete1");

//c18588		
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
		
		String reservDate=getDate();
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
// select sorting ordered by
//  c18550, c18552, c18553, c26811, C26812, c26796
		
		
		softAssert.assertTrue(McsElement.getElementByXpath(driver, "//input[@name='orderRoomsBy']/..//input[last()]").getAttribute("value").equals("Capacity"),"capacity sorting is ok");
		
		List<WebElement> result = driver.findElements(By.xpath("//table[@class='rsrvsearchresults']//tr//td[contains(text(),'Capacity')]")); 
		
		/*int previousInt = -1; // empty string: guaranteed to be less than or equal to any other

		for (WebElement current: result) {
		    if (Integer.parseInt(current.getText().replace("Capacity: ","").trim()) < previousInt)
		        softAssert.assertTrue(false,"order by capacity is wrong for " +current.getText());;
		    previousInt = Integer.parseInt(current.getText().replace("Capacity: ","").trim());
		}
		*/
		
		int previousInt = -1; // empty string: guaranteed to be less than or equal to any other
		for (WebElement currentroom: result) {
			
			String capacity = currentroom.getText().replace("Capacity: ","").trim();
			int intCapacity = -1; 
			
			//Code to handle when capacity is returned as ?
			try{
				intCapacity= Integer.parseInt(capacity);
			}catch(Exception e){
				
				intCapacity= Integer.MAX_VALUE;
			}
			
			
		    if (intCapacity < previousInt)
		        softAssert.assertTrue(false,"order by capacity is wrong for " +currentroom.getText());;
		    
		        previousInt = intCapacity;
		}
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "(//table[@class='rsrvsearchresults']//tr)[1]//span[contains(text(),'1preRmRef')]"),"sorting is ok");
		
		softAssert.assertFalse(driver.findElements(By.xpath("//span[contains(text(),'"+room+"')]")).isEmpty(),"Room "+room+ "is present after search");
		
		softAssert.assertFalse(driver.findElements(By.xpath("//span[contains(text(),'"+room2+"')]")).isEmpty(),"Room "+room2+ "is present after search");

		String []sortingList = {"Capacity", "Reference"};
		
		List<String> sorting = getAvailableSorting();
		
		//softAssert.assertTrue(sorting.equals(new ArrayList<>(Arrays.asList("Capacity", "Reference"))),"sorting options are ok");
		
		softAssert.assertEquals(sorting.toArray(), sortingList, "sorting options are ok");
		
		setSorting("Reference");
		
		waitForExtJSAjaxComplete(20);
		
		//tr[1] removed from below xpath
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//table[@class='rsrvsearchresults']//span[contains(text(),'1preRmRef')]"),"sorting is ok");
		
		result = driver.findElements(By.xpath("//table[@class='rsrvsearchresults']//tr//div/span")); 
		
		String previous = ""; // empty string: guaranteed to be less than or equal to any other

		List<String> rooms = new ArrayList<String>();
		for (WebElement current: result) {
			
			rooms.add(current.getText()); 
		}
		
		List<String> roomsList = new ArrayList<String>();
		roomsList.addAll(rooms);
		
		Collections.sort(roomsList, new Comparator(){@Override
			public int compare(Object arg0, Object arg1) {
			
			String str1 = (String)arg0;
			String str2 =  (String)arg1;
			str1=str1.toLowerCase();
			str2= str2.toLowerCase();
			
			return str1.compareTo(str2);
		}});
		
		softAssert.assertEquals(rooms, roomsList, "Rooms are sorted based on Reference");
		
		setSorting("Capacity");
		
		result = driver.findElements(By.xpath("//table[@class='rsrvsearchresults']//tr//td[contains(text(),'Capacity')]")); 
		
		/*previousInt = -1; // empty string: guaranteed to be less than or equal to any other

		for (WebElement current: result) {
		    if (Integer.parseInt(current.getText().replace("Capacity: ","").trim()) < previousInt)
		        softAssert.assertTrue(false,"order by capacity is wrong for " +current.getText());;
		    previousInt = Integer.parseInt(current.getText().replace("Capacity: ","").trim());
		}*/
		
		 previousInt = -1; // empty string: guaranteed to be less than or equal to any other

		 for (WebElement currentroom: result) {
				
				String capacity = currentroom.getText().replace("Capacity: ","").trim();
				int intCapacity = -1; 
				
				//Code to handle when capacity is returned as ?
				try{
					intCapacity= Integer.parseInt(capacity);
				}catch(Exception e){
					
					intCapacity= Integer.MAX_VALUE;
				}
				
				
			    if (intCapacity < previousInt)
			        softAssert.assertTrue(false,"order by capacity is wrong for " +currentroom.getText());;
			    
			        previousInt = intCapacity;
			}
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "(//table[@class='rsrvsearchresults']//tr)[1]//span[contains(text(),'1preRmRef')]"),"sorting is ok");
		
		softAssert.assertFalse(driver.findElements(By.xpath("//span[contains(text(),'"+room+"')]")).isEmpty(),"Room "+room+ "is present after search");
		
		softAssert.assertFalse(driver.findElements(By.xpath("//span[contains(text(),'"+room2+"')]")).isEmpty(),"Room "+room2+ "is present after search");
		
		//setNrPeople(numberPeople);
		
		setRoomPurpose(purpose);
		
		setRoomLayout(layout);
		
		setRoomCategory(category);

		waitForExtJSAjaxComplete(15);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//span[contains(text(),'"+room2+"')]"),"Room "+room2+ "is not present after search");
		
		clickLaunchReservation(room);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(15);
		
// c18591
		softAssert.assertEquals(getDepartment(),department,"default department was autoselected");
		
// c18592		
		clickLookup("@class","mcsreservationedit", "department","Select a Department");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(setValueGridLookupWithFiltersWithScrollInToView("@id", getXWindowId("Select a Department"), wrongDepartment, "Name"),"department from another company is not present");
		
		setValueGridLookupWithFiltersWithScrollInToView("@id", getXWindowId("Select a Department"), department2, "Name");
		
		waitForExtJSAjaxComplete(20);
		
// c18612
		clickParticipantTab();
		
		setTotalParticipants(numberPeopleExceed);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickParticipantTab();
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[@class='infobar' and contains(.,'exceeds the room')]"),"message that number of participants exceeds");
		
// c18584		
		
		setTotalParticipants(numberPeople);
		
		addParticipantEmployee(employee);
		
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
		
		clickOrderItemsTab();
		
		clickDetailOrderItem();
		
//C18606
	//Room purposes which are linked to a Reservable Room should only be populated in Purpose Combo Box 	
		softAssert.assertTrue(DropDown.isExtJsComboValuePresent(driver, McsElement.getElementByXpath(driver,"//div[contains(@class,'mcsreservationedit')]//input[@name='roomPurpose']").getAttribute("id"), purpose),"room purpose is present");
		
		softAssert.assertFalse(DropDown.isExtJsComboValuePresent(driver, McsElement.getElementByXpath(driver,"//div[contains(@class,'mcsreservationedit')]//input[@name='roomPurpose']").getAttribute("id"), purpose2),"room purpose2 is absent");
		
// c18589		
		clickLookup("@class","x-panel", "resource","Select a Room");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(setValueGridLookupWithFiltersWithScrollInToView("@id", getXWindowId("Select a Room"), room3, "Reference"),"room from another region is not present");
		
		setValueGridLookupWithFiltersWithScrollInToView("@id", getXWindowId("Select a Room"), room, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(service);
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(service2);
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(service);
		
		waitForExtJSAjaxComplete(20);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(equipment);
		
		waitForExtJSAjaxComplete(20);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(vehicle);
		
		waitForExtJSAjaxComplete(20);
		
		clickParkingTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(parking);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
//c18590
		
		setTimeFromReservationPanel(fromWrong);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		setTimeFromReservationPanel(from);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickOrderItemsTab();
		
		expandOrderItem(room);
		
// c18587		
		
		softAssert.assertTrue(driver.findElements(By.xpath("//div[contains(@class,'x-tree-node-el')]//span[contains(text(), '"+service+"')]")).size()==2,service+"is present twice");
		
		softAssert.assertTrue(driver.findElements(By.xpath("//div[contains(@class,'x-tree-node-el')]//span[contains(text(), '"+service2+"')]")).size()==1,service2+"is present");
		
		removeOrderItem(service);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(35);
		
		removeOrderItem(service2);
		
		softAssert.assertTrue(driver.findElements(By.xpath("//div[contains(@class,'x-tree-node-el')]//span[contains(text(), '"+service+"')]")).size()==1,service+"is present");
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Tree.isNodeInTreeAbsent(driver,service2),service2+"is not present");
		
		navigateToMainPage();
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		
		ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();
		  
		myReserv.setRegionMyReservation(region);
		  
		waitForExtJSAjaxComplete(20);
		  
		myReserv.setFutureDateMyReservation(reservDate);
		  
		waitForExtJSAjaxComplete(20);
		  
		myReserv.clickSearchButton();
		
		waitForExtJSAjaxComplete(20);
		
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, reservDate+" "+from+"|"+parking,"@class", "grid3"),"parking is reserved with room");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, reservDate+" "+from+"|"+vehicle,"@class", "grid3"),"vehicle is reserved with room");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, reservDate+" "+from+"|"+service,"@class", "grid3"),"service is reserved with room");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, reservDate+" "+from+"|"+equipment,"@class", "grid3"),"equipment is reserved with room");
		
		checkRowInGriByTextValueAndClick(reservDate+" "+from,room);
				
		waitForExtJSAjaxComplete(20);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		setTimeFromReservationPanel(fromEd);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		
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
		  
		waitForExtJSAjaxComplete(20);
		  
		myReserv.setFutureDateMyReservation(reservDate);
		  
		waitForExtJSAjaxComplete(20);
		  
		myReserv.clickSearchButton();
		
		waitForExtJSAjaxComplete(20);
		
		checkRowInGriByTextValueAndClick(reservDate+" "+fromEd,room);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickCancelReservation();
		
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
		  
		myReserv.setFutureDateMyReservation(reservDate);
		  
		waitForExtJSAjaxComplete(20);
		  
		myReserv.clickSearchButton();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isRowInGridAbsent(room, date+" "+from),"Room reservation can not be canceled");
		
		softAssert.assertAll();
		
		Reporter.log("Room was succesfully reserved and canceled", true);
	}

	/**
	 * Testcase ID	:	C74307,C74308,C74309,C74311,C74312,C74313,C74314
	 * author		:	Intellias
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationRoomCreateDelete2() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74307: Default Equipment and Services are added to Room reservation <br>"
				+ "Test C74308: Default Equipment and Services do not generate additional cost when booked with a Room <br>"
				+ "Test C74309: Services that are allowed for a Reservable Room can only be added, while reserving a Room <br>"
				+ "Test C74311: Add/Remove additional Equipment to Room Reservation <br>"
				+ "Test C74312: Add/Remove additional Service to Room Reservation <br>"
				+ "Test C74313: Add/Remove additional Vehicle to Room Reservation <br>"
				+ "Test C74314: Add/Remove additional Parking to Room Reservation </span><br>", true);

		Reporter.log("User reserves room <br>", true);
		
		
		String region = "1preRgRef";
		
		String defaultVehicle = "preDefVhCtRef";
		
		String defaultEquipment = "preEqDefCtRef";

		String equipmentInDepot = "2preRsEqRef";
		
		String equipmentOutDepot = "3preEqRef";
		
		String defaultParking = "prePrkDefCtRef";
		
		String defaultService = "preDerSrvRef";
		
		String responsible = getUserLastNameOfLoggedInUser();
		
		String project = "1preProjectRef";

		String projectPart = "1prePrPartRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "10-2-20"+random;
		String from = "05:00";
		String until = "05:30";
		String room = "preRmDefRef";
		String cost = "2.02 EUR";
		
		String serviceForpreRmDefRef = "3preSrvRef";
		String serviceNotForRoom = "4preSrvNoRoomRef";
		String serviceNotForpreRmDefRef = "2preSrvRef";
		String serviceForAllRoom = "1preSrvRef";
		
		String serviceOutDepot = "5preSrvRef";
		
		String serviceInDepot = "1preSrvRef";
		
		String vehicleInDepot = "1preRsVhRef";
		
		String vehicleOutDepot = "3preRsVhRef";

		String parkingInDepot = "1prePrkEqRef";
		
		String parkingOutDepot = "3prePrkResRef";
		
		SoftAssert softAssert = new SoftAssert();
		
		boolean isReservationDone=false;
		
		softAssert.setMethodName("testReservationRoomCreateDelete2");

		try{
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickRoomTab();
		
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
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(15);
		
//c18595
		setProject(project);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getProject(), project,"project was selected");
		
		setProjectPart(projectPart);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getProject(), projectPart,"project part was selected");
		
		clickOrderItemsTab();
		
		expandIncludedIn(room);
		
		softAssert.assertTrue(Tree.isNodeInTreePresent(driver, "@class", "mcsreservationedit", defaultVehicle),"default vehicle is present");
		
		softAssert.assertTrue(Tree.isNodeInTreePresent(driver, "@class", "mcsreservationedit", defaultParking),"default parking is present");
		
		softAssert.assertTrue(Tree.isNodeInTreePresent(driver, "@class", "mcsreservationedit", defaultEquipment),"default equipment is present");
		
		softAssert.assertTrue(Tree.isNodeInTreePresent(driver, "@class", "mcsreservationedit", defaultService),"default service is present");
		

//c18600
		clickAddOrderItemEquipment();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(addOrderItemEquipment(equipmentOutDepot),equipmentOutDepot+" equipment from another depot is not present");
		
		addOrderItemEquipment(equipmentInDepot);
		
		waitForExtJSAjaxComplete(20);
		
		expandOrderItem(room);
		
		removeOrderItem(equipmentInDepot);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Tree.isNodeInTreeAbsent(driver,equipmentInDepot),equipmentInDepot+"is not present after delete");
		
//c18601		
		
		expandOrderItem(room);
		
		clickAddOrderItemService();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(addOrderItemEquipment(serviceOutDepot),serviceOutDepot+" service from another depot is not present");
		
		addOrderItemEquipment(serviceInDepot);
		
		waitForExtJSAjaxComplete(20);
		
		expandOrderItem(room);
		
		removeOrderItem(serviceInDepot);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Tree.isNodeInTreeAbsent(driver,serviceInDepot),serviceInDepot+"is not present after delete");
		
//c18602		

		expandOrderItem(room);
		
		clickAddOrderItemVehicle();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(addOrderItemEquipment(vehicleOutDepot),vehicleOutDepot+" vehicle from another depot is not present");
		
		addOrderItemEquipment(vehicleInDepot);
		
		waitForExtJSAjaxComplete(20);
		
		expandOrderItem(room);
		
		removeOrderItem(vehicleInDepot);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Tree.isNodeInTreeAbsent(driver,vehicleInDepot),vehicleInDepot+"is not present after delete");
		
		
//c18603
		
		expandOrderItem(room);
		
		clickAddOrderItemParking();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(addOrderItemEquipment(parkingOutDepot),parkingOutDepot+" parking from another depot is not present");
		
		addOrderItemEquipment(parkingInDepot);
		
		waitForExtJSAjaxComplete(20);
		
		expandOrderItem(room);
		
		removeOrderItem(parkingInDepot);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Tree.isNodeInTreeAbsent(driver,parkingInDepot),parkingInDepot+"is not present after delete");
		
		clickSummaryTab();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(getSummaryTitle().contains(cost),"included service does not lead to additional cost");
		
		softAssert.assertTrue(getSummaryTotalCost().contains(cost),"included service does not lead to additional cost");
		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(driver.findElements(By.xpath("//span[contains(text(),'"+serviceForAllRoom+"')]")).isEmpty(),"service available for room"+serviceForAllRoom+ "is present after search");
		
		softAssert.assertTrue(isElementAbsent("//span[contains(text(),'"+serviceNotForRoom+"')]"),"service available for room"+serviceNotForRoom+ "is not present after search");
		
		softAssert.assertTrue(isElementAbsent("//span[contains(text(),'"+serviceNotForpreRmDefRef+"')]"),"service available for room"+serviceNotForpreRmDefRef+ "is not present after search");
		
		clickLaunchReservation(serviceForpreRmDefRef);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		isReservationDone=true;
		
		waitForMaskDisappear();
		
		clickSummaryTab();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'mcsreservationedit')]//button[text()='Confirmed']"),"confirmed button is present");
		
		softAssert.assertTrue(getSummaryTitle().contains(cost),"included service does not lead to additional cost");
		
	}
		finally{
			if(isReservationDone=true)
			{
			
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
				waitForExtJSAjaxComplete(25);
				
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

			} else {
				Reporter.log("Reservation not done");
			}
			
		}
		softAssert.assertAll();
		
		Reporter.log("Default equipment for room was checked", true);
	}

	/**
	 * Testcase ID	:	c18543, C623281
	 * author		:	Intellias
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationRoomOnly() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Reserving room only: c18543 "
				+ "C623281:  Warning message is shown and reservation saved, if no. of participants exceeds the capacity" 
				+ "</span><br>", true);

		Reporter.log("User reserves room only <br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "9-03-20"+random;
		String from = "06:00";
		String until = "06:30";
		String numberPeople = "2";
		String responsible = getUserLastNameOfLoggedInUser();
		String room = "1preRmRef";
		String category = "1preRmCtDescr";
		String purpose = "1preRmRsPrRef";
		String layout = "1preRmCmCod";
		String roomCapacity = "25";
		String warningMsg="The number of participants exceeds the room capacity";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReservationRoomOnly");

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
		
		//setNrPeople(numberPeople);
		
		//setRoomPurpose(purpose);
		
		//setRoomLayout(layout);
		
		//setRoomCategory(category);

		waitForExtJSAjaxComplete(20);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(room);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		
		//C623281
		clickParticipantTab();

		setTotalParticipants(roomCapacity);

		clickConfirmReservation();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		//Verify warning message for No.of participants exceeds room capacity
		Boolean status=verifyWarningDialogTextMessage(warningMsg);

		softAssert.assertTrue(status, "Warning Message: The number of participants exceeds the room capacity is displayed");

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("OK");

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isReservationDone(), "Reservation is confirmed");

		waitForExtJSAjaxComplete(20);
		
		//clickConfirmReservation();
		
		//waitForExtJSAjaxComplete(20);
		
		
//		C18548
		
		String reservationId = getReserationID();
		
		String id = reservationId.replaceAll("\\D+", "");
		
		navigateToPage("/index.php?mode=DESKTOP&relay=MCS_PORTAL_RS-ACTIVATE[RESERVATION|"+id+"]");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//a[contains(text(),'"+reservationId+"')]"),"button reservation id");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//b[contains(text(),'"+room+"')]"),"reserved room");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//p[contains(text(),'"+reservationId +" by AQA RESERVATIONS')]"),"reserved by SELENIUM");
		
		navigateToPage("/index.php?mode=DESKTOP&relay=MCS_PORTAL_RS-ACTIVATE[RESERVATION|"+id+"9"+"]");
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//a[contains(text(),'"+id+"9"+"')]"),"button reservation id for nonexisting reservation");
		
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
		
		softAssert.assertAll();
		
		Reporter.log("Room only was succesfully reserved", true);
	}
	/**
	 * Testcase ID	:	C74241
	 * author		:	Intellias
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testCombinedReservationRoomCreateDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74241: User can edit and cancel the combined Reservation under 'My Reservations'  </span><br>", true);

		Reporter.log("User can edit and cancel the combined Reservation under 'My Reservations': ISS UAT c26790 <br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "07-04-20"+random;
		String dateEd = "12-03-20"+random;
		String from = "00:00";
		String fromEd = "00:15";
		
		String until = "00:30";
		String numberPeople = "3";
		String responsible = getUserLastNameOfLoggedInUser();
		String room = "1preRmRef";
		
		String category = "1preRmCtDescr";
		String purpose = "1preRmRsPrRef";
		String layout = "1preRmCmCod";
		String service = "1preSrvRef";
		
		String serviceQuantity = "2";
		String serviceQuantityEd = "5";
		String equipment = "1preRsGnEqRef";
		String equipment2 = "2preRsEqRef";
		String vehicle = "1preRsVhRef";
		String parking = "1prePrkEqRef";
		String remark = "remark";
		boolean isReservationDone=false;
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCombinedReservationRoomCreateDelete");

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
				
		waitForExtJSAjaxComplete(15);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(20);
		
		//setNrPeople(numberPeople);
		
		setRoomPurpose(purpose);
		
		setRoomLayout(layout);
		
		setRoomCategory(category);

		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(room);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		setServiceQuantity(service, serviceQuantity);
		
		this.waitForMaskDisappear();
		
		clickLaunchReservation(service);
		
		waitForExtJSAjaxComplete(20);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(15);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(equipment);
		
		waitForExtJSAjaxComplete(20);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(vehicle);
		
		waitForExtJSAjaxComplete(20);
		
		clickParkingTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(parking);
		
		waitForExtJSAjaxComplete(35);
		
		clickGeneralTab();
		
		clickConfirmReservation();
		
		isReservationDone=true;
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(text(),'Reservation ')]"),"reservation is done");
		

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
		
		ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();
		  
		myReserv.setRegionMyReservation(region);
		  
		waitForExtJSAjaxComplete(20);
		  
		myReserv.setFutureDateMyReservation(date);
		  
		waitForExtJSAjaxComplete(5);
		  
		myReserv.clickSearchButton();
		  
		waitForExtJSAjaxComplete(20);
				
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date+" "+from+"|"+parking,"@class", "grid3"),"parking is reserved with room");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date+" "+from+"|"+vehicle,"@class", "grid3"),"vehicle is reserved with room");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date+" "+from+"|"+service,"@class", "grid3"),"service is reserved with room");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date+" "+from+"|"+equipment,"@class", "grid3"),"equipment is reserved with room");
		
		checkRowInGriByTextValueAndClick(date+" "+from,room);
				
		waitForExtJSAjaxComplete(20);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(20);
		
		clickOrderItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		expandOrderItem(room);
		
		waitForExtJSAjaxComplete(20);
		
		Tree.checkNodeInTreeByTextValue(driver, service);
		
		waitForExtJSAjaxComplete(20);
		
		clickDetailOrderItem();
		
		softAssert.assertEquals(serviceQuantity,getQuantity(),"quantity is correct");
		
		setQuantity(serviceQuantityEd);
		
		//clickGeneralTab();
		
		//clickOrderItemsTab();
				
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		clickOrderItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		expandOrderItem(room);
		
		waitForExtJSAjaxComplete(20);
		
		Tree.checkNodeInTreeByTextValue(driver, service);
		
		waitForExtJSAjaxComplete(20);
		
		clickDetailOrderItem();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(serviceQuantityEd,getQuantity(),"edited quantity is correct");

		Tree.checkNodeInTreeByTextValue(driver, equipment);
		
		waitForExtJSAjaxComplete(20);
		
		clickDetailOrderItem();
		
		waitForExtJSAjaxComplete(20);
		
		setEquipmentFromDetails(equipment2);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		clickOrderItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		expandOrderItem(room);
		
		softAssert.assertTrue(Tree.isNodeInTreePresent(driver, equipment2),"equipment can be edited");
		
		removeOrderItem(vehicle);
		
		softAssert.assertFalse(orderItemExist(vehicle),"vehicle can be deleted");
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		clickOrderItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		expandOrderItem(room);
		
		clickAddOrderItemVehicle();
		
		addOrderItemEquipment(vehicle);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		clickOrderItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		expandOrderItem(room);
		
		softAssert.assertTrue(orderItemExist(vehicle),"vehicle can be added");
		
		removeOrderItem(service);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(10);
		
		this.waitForMaskDisappear();
		
		clickSummaryTab();
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//table[contains(@class,'mcsreservation-summary-table')]//*[contains(text(),'"+service+"')]") ,"summary is ok");
		
		clickParticipantTab();
		
		selectParticipant(responsible);
		
		waitForExtJSAjaxComplete(20);
		
		clickRemoveParticipant();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(selectParticipant(responsible),"participant is deleted");
		
		clickOrderItemsTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickDetailOrderItem();
		
//c18608
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
		
		//clickGeneralTab();
		
		setDateFromReservationPanel(dateEd);
		
		setTimeFromReservationPanel(fromEd);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(10);
		
		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		  
		myReserv.setRegionMyReservation(region);
		  
		waitForExtJSAjaxComplete(20);
		  
		myReserv.setFutureDateMyReservation(dateEd);
		  
		waitForExtJSAjaxComplete(20);
		  
		myReserv.clickSearchButton();
		
		waitForExtJSAjaxComplete(20);
		
		checkRowInGriByTextValueAndClick(dateEd+" "+fromEd,room);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		clickGeneralRemarks();
		
		setGeneralRemarks(remark);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		clickGeneralRemarks();
		
		softAssert.assertEquals(remark,getGeneralRemarks(),"remark is ok");
		
		clickGeneralRecurence();
		
		clickRecurenceEndAfter();
		
		setRecurenceEndAfter("1");
		
		clickRecurenceDailyTab();
		
		clickRecurenceDailyEvery();
		
		setRecurenceDailyInterval("1");
		
		clickRecurenceCalculateDates();
		
		clickSetRecurence();
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//label[contains(text(),'Recurs every 1 day')]"),"recurence was set");
		
		clickCancelReservation();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPage();
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		myReserv.setRegionMyReservation(region);
		  
		waitForExtJSAjaxComplete(20);
		  
		myReserv.setFutureDateMyReservation(date);
		  
		waitForExtJSAjaxComplete(20);
		  
		myReserv.clickSearchButton();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isRowInGridAbsent(room, dateEd+" "+fromEd),"Room reservation can not be canceled");
		}
		finally{
			if(isReservationDone=true)
			{
			
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
				waitForExtJSAjaxComplete(25);
				
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

			} else {
				Reporter.log("Reservation not done");
			}
		}
		softAssert.assertAll();
		
		Reporter.log("Room was succesfully reserved and canceled", true);
	}

	/**
	 * Testcase ID	:	C74331,C74332
	 * author		:	Intellias
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationTentaiveRoom() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74331: Tentative Room Reservation also generate cost items <br>"
				+ "Test C74332:  More than one Tentative Room Reservation can not be made for the same time period </span><br>", true);

		Reporter.log("User reserves tentative room <br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "9-04-20"+random;
		String from = "08:00";
		String until = "08:30";
		String numberPeople = "2";
		String responsible = getUserLastNameOfLoggedInUser();
		String room = "1preRmRef";
		String category = "1preRmCtDescr";
		String purpose = "1preRmRsPrRef";
		String layout = "1preRmCmCod";
		boolean isReservationDone=false;
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReservationTentativeRoom");

		try{
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		setRegion(region);
		
		setDate(date);
		
		waitForExtJSAjaxComplete(20);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
		
		waitForExtJSAjaxComplete(5);
		
		String reservDate=getDate();
	
		waitForExtJSAjaxComplete(20);
		
		clickRoomTab();
		
		//setNrPeople(numberPeople);
		
		//setRoomPurpose(purpose);
		
		//setRoomLayout(layout);
		
		//setRoomCategory(category);

		waitForExtJSAjaxComplete(20);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(room);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(5);
		
		clickTentative();
		
		waitForExtJSAjaxComplete(20);
		
		clickSummaryTab();
		
		softAssert.assertTrue(getSummaryTotalCost().contains("2.02 EUR"),"tentative room generates costs");
		
		softAssert.assertTrue(getSummaryTitle().contains("2.02 EUR"),"tentative room generates costs summary");
		
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
		
		clickRoomTab();
		
		setRegion(region);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(5);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(20);
		
		//setNrPeople(numberPeople);
		
		//setRoomPurpose(purpose);
		
		//setRoomLayout(layout);
		
		//setRoomCategory(category);

		waitForExtJSAjaxComplete(15);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//span[contains(text(),'"+room+"')]"),"Room "+room+ "is not present after tentative search");
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
		
		waitForExtJSAjaxComplete(35);
		
		ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();
		  
		myReserv.setRegionMyReservation(region);
		  
		waitForExtJSAjaxComplete(20);
		  	
		myReserv.setFutureDateMyReservation(date);
		  
		waitForExtJSAjaxComplete(15);
		  
		myReserv.clickSearchButton();
		  
		waitForExtJSAjaxComplete(30);
		
		checkRowInGriByTextValueAndClick(date+" "+from,room);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickCancelReservation();
		
		clickOnDialogButton("Yes");
		}else{
			System.out.println("Reservation not confirmed");
	}
	}	
		softAssert.assertAll();
		
		Reporter.log("Tentative Room was succesfully reserved", true);
	}
	
	/**
	 * Testcase ID	:	C74325
	 * author		:	Intellias
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationRoomAdditionalCost() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74325: In Room reservation, cost for Reservable Room and additional cost for added Equipment, Services, Vehicles & Parking are displayed in Summary </span><br>", true);

		Reporter.log("User reserves room and additional costs <br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "7-05-20"+random;
		String from = "12:00";
		String until = "12:30";
		
		String responsible = getUserLastNameOfLoggedInUser();
		String room = "1preRmRef";
		
		String service = "preDerSrvRef";
		
		String equipment = "preEqDefRef";
		
		String vehicle = "preDefVhRef";
		
		String parking = "prePrkDefRef";
		
		String category = "1preRmCtDescr";
		String purpose = "1preRmRsPrRef";
		String layout = "1preRmCmCod";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReservationAdditionalRoom");

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
		
		//setRoomPurpose(purpose);
		
		//setRoomLayout(layout);
		
		//setRoomCategory(category);

		waitForExtJSAjaxComplete(20);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(room);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(15);
		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		this.waitForMaskDisappear();
		
		clickLaunchReservation(service);
		
		waitForExtJSAjaxComplete(20);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(vehicle);

		waitForExtJSAjaxComplete(20);
		
		//clickLaunchReservation(vehicle);
		
		waitForExtJSAjaxComplete(20);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(equipment);

		waitForExtJSAjaxComplete(20);
		
		clickParkingTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(parking);
		
		waitForExtJSAjaxComplete(20);
		
		clickSummaryTab();
		
		waitForExtJSAjaxComplete(15);
		
		System.out.println(getSummaryTotalCost());
		
		softAssert.assertTrue(getSummaryTotalCost().contains("10.10 EUR"),"additional costs for room are ok");
		
		System.out.println(getSummaryTitle());
		
		softAssert.assertTrue(getSummaryTitle().contains("10.10 EUR"),"summary title is ok");
		
		softAssert.assertTrue(getSummaryItemCost(room).contains("2.02 EUR"),"room cost is ok");
		
		softAssert.assertTrue(getSummaryItemCost(vehicle).contains("2.02 EUR"),"vehicle cost is ok");
		
		softAssert.assertTrue(getSummaryItemCost(parking).contains("2.02 EUR"),"parking cost is ok");

		softAssert.assertTrue(getSummaryItemCost(equipment).contains("2.02 EUR"),"equipment cost is ok");
		
		softAssert.assertTrue(getSummaryItemCost(service).contains("2.02 EUR"),"service cost is ok");

		softAssert.assertAll();
		
		Reporter.log("Additional cost Room was succesfully reserved", true);
	}

	/**
	 * Testcase ID	:	C74295,C90731
	 * author		:	Intellias
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationRoomRecurrence() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74295: Recurrence reservation with overlapped reservation dates <br>"
				+ "Test C90731: The stock item which is not enabled must not be available for any transactions </span><br>", true);

		Reporter.log("User reserves recurrence with overlapped dates <br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "11-01-20"+random;
		String dateRec = "10-01-20"+random;
		String dateRec2 = "12-01-20"+random;
		String from = "17:00";
		String until = "17:30";
		String from1 = "16:45";
		String until1 = "17:45";
		String responsible = getUserLastNameOfLoggedInUser();
		String room = "1preRmRef";
		
	
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReservationRoomRecurrence");

		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(30);
		
		setRegion(region);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(15);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(room);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(15);
		
		clickGeneralTab();
		
//C35968		
		
		setTimeCleanupFromReservationPanel("00:15");
		
		setTimePrepareFromReservationPanel("00:15");
		
		clickSummaryTab();

		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(getSummaryTitle().contains("2.02 EUR"),"prepare cleanup time does not lead to additional cost");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(getSummaryTotalCost().contains("2.02 EUR"),"prepare cleanup time does not lead to additional cost");
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
	
		clickConfirmReservation();

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		//clickRoomTab();
		
		setRegion(region);
		
		setDate(dateRec);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(5);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(room);
		
		waitForExtJSAjaxComplete(35);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(5);
		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		this.waitForMaskDisappear();
		
		clickLaunchReservation("1preSrvRef");
		
		waitForExtJSAjaxComplete(20);
		
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation("1preRsGnEqRef");
		
		waitForExtJSAjaxComplete(10);
		
		clickVehicleTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation("1preRsVhRef");
		
		waitForExtJSAjaxComplete(10);
		
		clickParkingTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation("1prePrkEqRef");
		
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
		
		//c18618
		
		clickCheckAvailability();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-window')]//div[contains(@class,'x-grid3')]//div[contains(text(),'Unavailable:')]"), "unavailable period");
		
		clickSetRecurence();
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();		
		
		waitForExtJSAjaxComplete(20);
		
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-window-dlg')]//span[contains(text(),'Unable to save')]"), "unable to save occurence");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		this.clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralRecurence();
		
		//TO DO: Overlaped dates are removed from the recurence window
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//div[contains(@class,'x-window')]//div[contains(@class,'x-grid3')]//div[contains(text(),'"+date+"')]"), "overlaped date is absent");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-window')]//div[contains(@class,'x-grid3')]//div[contains(text(),'"+dateRec+"')]"), "reccurence date is present");
		
		closeRecurrenceWindow();
		
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
		
		waitForExtJSAjaxComplete(20);
		
		checkRowInGriByTextValueAndClick(date+" "+from1,room);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickCancelReservation();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(30);
		
		//ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();
		
		myReserv.setRegionMyReservation(region);
		
		waitForExtJSAjaxComplete(15);
		
		myReserv.setTodayDateInMyReservation(dateRec);
		
		waitForExtJSAjaxComplete(10);
		
		myReserv.clickSearchButton();
		
		waitForExtJSAjaxComplete(20);
		
		checkRowInGriByTextValueAndClick(dateRec+" "+from,room);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickCancelReservation();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYRESERVATIONS);
		
		waitForExtJSAjaxComplete(20);
		
		//ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();
		
		myReserv.setRegionMyReservation(region);
		
		waitForExtJSAjaxComplete(20);
		
		myReserv.setTodayDateInMyReservation(dateRec2);
		
		waitForExtJSAjaxComplete(20);
		
		myReserv.clickSearchButton();
		
		waitForExtJSAjaxComplete(20);
		
		checkRowInGriByTextValueAndClick(dateRec2+" "+from,room);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickCancelReservation();
		
		clickOnDialogButton("Yes");
		
		softAssert.assertAll();
		
		Reporter.log("Overlapped recurence functionality was checked", true);
	}
	/**
	 * Testcase ID	:	C74321, C114744
	 * author		:	Intellias
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationRoomContactPerson() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74322: Contact & Other Participant Details are shown in Room Reservation and Bell symbol displaying"
				+ "Test C114744: Employee is added to the Reservation </span><br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "11-09-20"+random;
		String from = "11:00";
		String until = "11:30";
		
		String responsible = getUserLastNameOfLoggedInUser();
		String employee = "SELENIUM";
		String room = "1preRmRef";
		
		
		String category = "1preRmCtDescr";
		String purpose = "1preRmRsPrRef";
		String layout = "1preRmCmCod";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReservationContact");

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
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(5);
		
		
		//setRoomPurpose(purpose);
		
		//setRoomLayout(layout);
		
		//setRoomCategory(category);

		waitForExtJSAjaxComplete(10);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(room);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(10);
		
		clickParticipantTab();
		
		waitForExtJSAjaxComplete(20);
		
		addParticipantContact("1preContactLast");
		
		waitForExtJSAjaxComplete(20);
				
		McsElement.getElementByXpath(driver, "//img[contains(@src,'announce')]").click();
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//img[contains(@src,'announce')]"),"bell near participant is present");
		
		waitForExtJSAjaxComplete(20);
		
		clickAnnounceCheckbox();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//img[contains(@src,'announce')]"),"no bell near participant is present");
		
		addOtherPerson("firsnt", "lasnt");
		
		waitForExtJSAjaxComplete(20);
		
		selectParticipant("firsnt");
		
		waitForExtJSAjaxComplete(20);
		
		//RESERVATIONS AQA
		this.addParticipantEmployee(employee);

		waitForExtJSAjaxComplete(20);

		this.selectParticipant(employee);

		waitForExtJSAjaxComplete(20);

		clickAnnounceCheckbox();

		waitForExtJSAjaxComplete(20);

		clickAnnounceCheckbox();

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(McsElement.isElementPresent(driver, "//img[contains(@src,'announce')]"),"Announcement is added");
		try{
			clickConfirmReservation();

			waitForExtJSAjaxComplete(20);
		}

		finally{

			navigateToMainPage();

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


			waitForExtJSAjaxComplete(20);

			checkRowInGriByTextValueAndClick(date+" "+from,room);

			waitForExtJSAjaxComplete(20);

			clickViewCancel();

			waitForExtJSAjaxComplete(20);

			clickParticipantTab();

			waitForExtJSAjaxComplete(20);

			List<String> getallParticipantsEmp= getListOfParticipants();

			softAssert.assertTrue(getallParticipantsEmp.toString().contains("SELENIUM AQA"),"Added Employee is displayed in the Reservation ");

			softAssert.assertTrue(McsElement.isElementPresent(driver, "//img[contains(@src,'announce')]"),"Announcement is displayed for the Employee");

			waitForExtJSAjaxComplete(20);

			clickGeneralTab();

			waitForExtJSAjaxComplete(20);

			clickCancelReservation();

			clickOnDialogButton("Yes");

			waitForExtJSAjaxComplete(20);

		}
		
		softAssert.assertAll();
		
		Reporter.log("Room contact person was added", true);
	}

	/**
	 * Testcase ID	:	C74333,C120089
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testReservationConfidentialRoom() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C74333: Confidential Room Reservation </span><br>", true);

		Reporter.log("User reserves confidential room <br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = "10-07-20"+random;
		String from = "10:00";
		String until = "11:00";
		String numberPeople = "2";
		String responsible =getUserLastNameOfLoggedInUser();
		String room = "1preRmRef";
		String category = "1preRmCtDescr";
		String purpose = "1preRmRsPrRef";
		String layout = "1preRmCmCod";
		String errorMessage="You are not allowed to view this Reservation";
		
		String service = "preDerSrvRef";
		
		String equipment = "preEqDefRef";
		
		String vehicle = "preDefVhRef";
		
		String parking = "prePrkDefRef";
		boolean isReservationDone=false;
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReservationConfidentialRoom");

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
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(5);
		
		waitForMaskDisappear();
		
		clickLaunchReservation(room);
		
		waitForExtJSAjaxComplete(10);
		
		clickConfidential();
		
		waitForExtJSAjaxComplete(15);
		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(service);
		
		waitForExtJSAjaxComplete(20);
		
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
		
		clickLaunchReservation(parking);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible("SELENIUM");
		
		waitForExtJSAjaxComplete(20);
			
		clickConfirmReservation();
		
		waitForMaskDisappear();
		
		isReservationDone=true;
		
		softAssert.assertTrue(isReservationDone(), "Confidential Reservation is confirmed");
		
		String reservationID = getReserationID();
		
		String reservIDWithoutString = reservationID.replace("Reservation", "").trim();
		
		System.err.println(reservIDWithoutString);
			
		waitForExtJSAjaxComplete(20);
		
		navigateToMainPage("VPCC", "qwerty");
		
		waitForExtJSAjaxComplete(20);
		
		//C120089:Using the deep links to access the confidential reservations by ID and user does not have rights to view confidential reservations
		String ReservationUrl = "/index.php?mode=DESKTOP&relay=MCS_PORTAL_RS-ACTIVATE[RESERVATION|"+reservIDWithoutString+"]";

		navigateToPage(ReservationUrl);

		waitForMaskDisappear();
		waitForExtJSAjaxComplete(30);
		
		softAssert.assertTrue(getWarningDialogTextMsg().contains(errorMessage),"You are not allowed to view this Reservation is displayed");
		
		clickOnDialogButtonCustomized("OK");
		
		waitForExtJSAjaxComplete(30);
		}
		
		finally{
		
		if(isReservationDone==true)
		{
		navigateToMainPage();
		
		waitForExtJSAjaxComplete(30);
		
		System.err.println(responsible);
		
		if(responsible.equalsIgnoreCase("reservations")){
		
		navigateToMainPage("aqa_reservations", "qwerty");}else{
			navigateToMainPage("aqa_reservations2", "qwerty");
			}	
			
		waitForExtJSAjaxComplete(30);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
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
		  
		waitForExtJSAjaxComplete(30);
		
		checkRowInGriByTextValueAndClick(date+" "+from,room);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewCancel();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickCancelReservation();
		
		clickOnDialogButton("Yes");
		}else{
			System.out.println("Reservation is not done");
		}
		}
		softAssert.assertAll();
		
		Reporter.log("Confidential Room was succesfully reserved", true);
	}


	/**
	 * Testcase ID	:	C74304,C74305,C74315,C74316,C74318,C74320
	 * Author		:	vpcc
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testRoomReservationDetails() throws Exception{
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C74304:Notes and other details for Reservable Room should display in New Reservation Pane, while reserving a Room"
				+"C74305:Reservable Room Info is display in Details tab of Order Items pane "
				+"C74315:Notes and other details for additional Equipment should display in Room Reservation "
				+"C74316:Notes and other details for additional Services should display in New Reservation Pane, while reserving a Room "
				+"C74318:Possible Compostions which are linked to a Reservable Room should only be populated in Layout Combo Box "
				+"C74320:In Room Reservation, lookup values for GL Account, Cost Center and all other Finkeys must be limited to one Fiscal Entity "
				+ " </span><br>", true);

		Reporter.log("User reserves room and additional costs <br>", true);
		
		
		String region = "1preRgRef";
		
		int random = (int)(Math.random() * 10)+18;
		
		String date = this.getFutureDate(random);
		
		String from = "13:00";
		String until = "13:30";
		
		String responsible = getUserLastNameOfLoggedInUser();
		String room = "aqaroomref";
		String roomCode = "aqaroomcode";
		String roomNotes = "Room for automation purpose";
		String roomDefaultCapacity = "20";
		String roomCategory = "1preRmCtDescr";
		
		String service = "aqaserviceref";
		String serviceCode="aqaservicecode";
		String serviceCategory="1preSrvCtDescr";
		String serviceNotes="Service for automation";
		
		String equipment = "aqaequipmentref";
		String equipmentCode="aqaequipmentcode";
		String equipmentDepot="1preDpRef";
		String equipmentNotes="Equipment for automation";
		String equipmentCategory ="1preGnEqRef";
		
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testRoomReservationDetails");

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
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(20);

		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(room);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		
		//C18609 - Start
		clickTabInReservationDetailsSection("Financial");
		
		waitForExtJSAjaxComplete(20);
		
		List<String> finkey3List = new ArrayList<String>();
		finkey3List.add("3preValue");
		
		List<String> finkey4List = new ArrayList<String>();
		finkey4List.add("4preValue");
		
		List<String> glAccList = new ArrayList<String>();
		glAccList.add("myMCS Default GL Account");
		
		List<String> glAccVals= getGLAccountLookUpValues();
		softAssert.assertTrue(glAccVals.contains("myMCS Default GL Account") , "Expected GL accounts are populated");
		waitForExtJSAjaxComplete(20);
		clickCANCELXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		List<String> finKey3Vals = getFinKey3LookUpValues();
		waitForExtJSAjaxComplete(20);
		softAssert.assertTrue(finKey3Vals.contains("3preValue") , "Expected finkey3 List are populated");
		
		clickCANCELXwindow();
	
		
		waitForExtJSAjaxComplete(20);
		List<String> finKey4Vals = getFinKey4LookUpValues();
		waitForExtJSAjaxComplete(20);
		softAssert.assertTrue(finKey4Vals.contains("4preValue") , "Expected finkey4 List are populated");
		
		clickCANCELXwindow();
		
		waitForExtJSAjaxComplete(20);
		//C18609 - END
		
		clickOrderItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		//C18594 - Start
		
		clickItemInOrderItemsPane("room", room);
		
		waitForExtJSAjaxComplete(20);
		
		//C18593 Start
		String toolTipTextContent = getToolTipOfOrderItemInOrderItemsPane("room", room);
		
		String toolTipText = removeHtmlTagsFromString(toolTipTextContent);
		System.err.println(toolTipText);
		
		softAssert.assertEquals(getPropertyFromSring(toolTipText,room),"", "Reference is displayed correctly in Room Tooltip");
		softAssert.assertEquals(getPropertyFromSring(toolTipText,"Category:"),roomCategory, "category is displayed correctly  in  Room  Tooltip");
		softAssert.assertEquals(getPropertyFromSring(toolTipText,"Code:"),roomCode, "code is displayed correctly in  Room Tooltip");
		softAssert.assertEquals(getPropertyFromSring(toolTipText,"Default Capacity:"),roomDefaultCapacity, "default Capacity is displayed correctly in  Room  Tooltip");
		softAssert.assertEquals(toolTipText.contains(roomNotes),true, "Notes is displayed correctly  in Room  Tooltip");
		softAssert.assertTrue(isImageTagPresentInString(toolTipTextContent), "Image is displayed in Room  tooltip");
		//C18593 End
		
		String roomInfo = getInfoHTMLOfReservableItemFromOrderItemDetailsTab();
		softAssert.assertTrue(isImageTagPresentInString(roomInfo), "Image is displayed in Info section of Room");
		
		String roomInfoText = removeHtmlTagsFromString(roomInfo);
		
		softAssert.assertEquals(getPropertyFromSring(roomInfoText,room),"", "Reference is displayed correctly in Info section of room");
		softAssert.assertEquals(getPropertyFromSring(roomInfoText,"Category:"),roomCategory, "category is displayed correctly in Info section of room");
		softAssert.assertEquals(getPropertyFromSring(roomInfoText,"Code:"),roomCode, "code is displayed correctly in Info section of room");
		softAssert.assertEquals(getPropertyFromSring(roomInfoText,"Default Capacity:"),roomDefaultCapacity, "default Capacity is displayed correctly in Info section of room");
		softAssert.assertEquals(roomInfoText.contains(roomNotes),true, "Notes is displayed correctly in Info section of room");
	
		//C18594 - End
		
		waitForExtJSAjaxComplete(20);
		
		//C18607 - Start
		List<String> roomLayOuts = getRoomLayouts(); 
		System.err.println(roomLayOuts);
		List<String> possibleRoomlayOuts = new ArrayList<String>();
		possibleRoomlayOuts.add("1preRmCmCod\nCapacity: 1");
		
		softAssert.assertEquals(roomLayOuts, possibleRoomlayOuts,"Possible Compositions which are linked to the Reservable room are displayed.");
		//C18607 - End
		
		waitForExtJSAjaxComplete(20);
		
		clickServiceTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(service);
		
		waitForExtJSAjaxComplete(20);
		
		//C18605 Start
		clickItemInOrderItemsPane("service", service);
		
		waitForExtJSAjaxComplete(20);
		
		String serviceserviceToolTipTextContent = getToolTipOfOrderItemInOrderItemsPane("service", service);

		String serviceToolTipText = removeHtmlTagsFromString(serviceserviceToolTipTextContent);

		softAssert.assertEquals(getPropertyFromSring(serviceToolTipText,service),"", "Reference is displayed correctly in service Tooltip");
		
		softAssert.assertEquals(getPropertyFromSring(serviceToolTipText,"Category:"),serviceCategory, "category is displayed correctly  in  service  Tooltip");
		
		softAssert.assertEquals(getPropertyFromSring(serviceToolTipText,"Code:"),serviceCode, "code is displayed correctly in  service Tooltip");
		
		softAssert.assertEquals(serviceToolTipText.contains(serviceNotes),true, "Notes is displayed correctly  in service  Tooltip");

		//C18605 End
		
		waitForExtJSAjaxComplete(20);
		
		//
		clickEquipmentTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(equipment);

		waitForExtJSAjaxComplete(20);
		
		clickItemInOrderItemsPane("equipment", equipment);
		
		waitForExtJSAjaxComplete(20);
		//C18604 Start
		String equipmentToolTipTextContent = getToolTipOfOrderItemInOrderItemsPane("equipment", equipment);
		
		String equipmentToolTipText = removeHtmlTagsFromString(equipmentToolTipTextContent);
		
		softAssert.assertEquals(getPropertyFromSring(equipmentToolTipText,equipment),"", "Reference is displayed correctly in equipment Tooltip");

		softAssert.assertEquals(getPropertyFromSring(equipmentToolTipText,"Category:"),equipmentCategory, "category is displayed correctly  in  equipment  Tooltip");
	
		softAssert.assertEquals(getPropertyFromSring(equipmentToolTipText,"Code:"),equipmentCode, "code is displayed correctly in  equipment Tooltip");
		softAssert.assertEquals(getPropertyFromSring(equipmentToolTipText,"Depot:"),equipmentDepot, "default Capacity is displayed correctly in equipment  Tooltip");
		softAssert.assertEquals(equipmentToolTipText.contains(equipmentNotes),true, "Notes is displayed correctly  in equipment  Tooltip");
		softAssert.assertTrue(isImageTagPresentInString(equipmentToolTipTextContent), "Image is displayed in equipment  tooltip");
		//C18604 End
		
		softAssert.assertAll();
		
		Reporter.log(				
				"Notes and other details for Reservable Room are displayed in New Reservation Pane, while reserving a Room"
						+"Reservable Room Info is display in Details tab of Order Items pane "
						+"Notes and other details for additional Equipment are displayed in Room Reservation "
						+"Notes and other details for additional Services are displayed in New Reservation Pane, while reserving a Room "
						+"Possible Compostions which are linked to a Reservable Room are only populated in Layout Combo Box "
						+"In Room Reservation, lookup values for GL Account, Cost Center and all other Finkeys are limited to one Fiscal Entity "		
				, true);
		
	}
	
	/**
	  * Testcase ID : C74340 & C74327
	  * Author  : ssa
	  */
	 @Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	 public void testRoomWithNullCapacityCanBeBooked(){
	  
	  Reporter.log("<span style='font-weight:bold;color:#000033'> "
	    + "Test: C74340:Reservable Room with Capacity of Null value also can be booked </span><br>"
		 +"test:C74327: Summary and Printing are not available, if required field is not filled out for Room Reservation", true);

	  
	  String region = "1preRgRef";
	  
	  int random = (int)(Math.random() * 10)+18;
	  
	  String date = this.getFutureDate(random);
	  
	  String from = "13:00";
	  String until = "13:30";
	  
	  String room = "NullCapacityRoom";
	  String summaryTabText= "A summary is not available for the currently entered data\n"
			  +"\n"+
			  "Please fill in the Reservation Responsible.\n"+
			  "\n"+"Please make sure you have filled out all mandatory fields.";
	  
	  String  printIconWarningMsg ="Printing requires all changes to be saved first";
	  
	  SoftAssert softAssert = new SoftAssert();
	  
	  softAssert.setMethodName("testReservationAdditionalRoom");

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
	  
	  clickRoomTab();
	  
	  setTimeUntil(until);
	    
	  waitForExtJSAjaxComplete(10);
	  
	  clickRoomTab();
	  
	  waitForExtJSAjaxComplete(20);

	  clickSearch();
	  
	  waitForExtJSAjaxComplete(20);
	  
	  boolean isRoomReservable = true;
	  try{
	  clickLaunchReservation(room);
	  
	  }catch(Exception e){
	   
	    isRoomReservable = false;
	  }
	  
	  softAssert.assertTrue(isRoomReservable,"Null capacity room is reservable");
	  
	  waitForExtJSAjaxComplete(20);
	  
	  Reporter.log("User is able to reserve room with null capacity  <br>", true);
	 
	  clickSummaryTab();
	  
	  waitForExtJSAjaxComplete(20);
	  
	  softAssert.assertEquals(getSummaryTabText(),summaryTabText,"'A summary is not available for the currently entered data' is display with a message to fill out required fields.");
	  
	  waitForExtJSAjaxComplete(20);
	  
	  clickReportIconInSummarySection();
	  
	  waitForExtJSAjaxComplete(20);
	  
	  softAssert.assertTrue(verifyWarningDialogTextMessage(printIconWarningMsg),"'Printing requires all changes to be saved first' alert message is displayed.");
	  
	  this.clickOnDialogButton("OK");
	  
	  softAssert.assertAll();
	  
	  Reporter.log("Summary pane is display with a message to fill out required fields."
	  		+ "Alert message is displayed for print icon <br>", true);
	 }  
	 

 /**
	 * Testcase ID	:	C74321
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testVerifyAlertMsgForPrepareCleanupTimeInRoomReservation() throws Exception{
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C74321: Alert message for Maximum Prepare/Cleanup time is displayed in room Reservation </span><br>", true);

		
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
		String room2="2aqaRoomRef";
		
		
		String warningMsg="With the Preparation time included, the Reservation does not fit in one day. Please decrease the Preparation Time or increase the From time.\n";
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testVerifyAlertMsgForPrepareCleanupTimeInRoomReservation");
		
	
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
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(room2);
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getPrepareTimeGeneral(), prepareTime,"prepare time is displayed in general");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getCleanUpTimeGeneral(), cleanupTime,"clean up time is displayed in general");
		
		waitForExtJSAjaxComplete(20);
	
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		
		clickOrderItemsTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickAddOrderItemEquipment();
		
		waitForExtJSAjaxComplete(20);
		
		addOrderItem(equipment1);
		
		waitForExtJSAjaxComplete(20);
		
		Boolean status=isItemAddedInOrderItemsPane("room",room2);
		
		softAssert.assertTrue(status,"Item is added");
		
		waitForExtJSAjaxComplete(20);
		
		status=isItemAddedInOrderItemsPane("equipment",equipment1);
		
		softAssert.assertTrue(status,"Item is added");
		
		waitForExtJSAjaxComplete(20);
		
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
		
		Reporter.log("Alert message is displayed Maximum Prepare/Cleanup time in room Reservation<br>", true); 

	}
	
		
	/**
	 * Testcase ID	:	C18624
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testUnavailableRoomReservationCanNotBeBooked() throws Exception{
		
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C18624:Reservable room for the specified Unavailable Periods can not be booked </span><br>", true);

		String region = "1preRgRef";
		
		//int random = (int)(Math.random() * 10)+18;
		
		String date = "09-07-2035";
		String from = "01:00";
		String until = "02:00";
		String room="RoomUnavailblePeriodRef";
		String regions = "All Regions";
		String unAvailableReservationTooTipMsg="The Resource is not available at that moment";
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testUnavailableVehicleReservationCanNotBeBooked");
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
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		Boolean status=isDisplayLaunchReservation(room);
		
		softAssert.assertFalse(status, "room is not displayed");	
	
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_CALENDAR);
		
		waitForExtJSAjaxComplete(20);
		
		clickCalenderTab();
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		calendarPageObj.clickRoomsTabInCalendarTab();
		
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
		
		waitForExtJSAjaxComplete(20);
		
		calendarPageObj.filterItemByName(room);
		
		waitForMaskDisappear();
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,calendarPageObj.CALENDAR_RESOURCE_WIN_XPATH+"//div[text()='"+room+"']"), "Reservable vehicle is displayed");
		
		String text=calendarPageObj.getToolTipTextOfUnAvailableReservation(room);
		
		softAssert.assertTrue(text.contains(unAvailableReservationTooTipMsg),"Tool tip message is displayed for unavailable reservation");
		
		//C74254 :Using the deep links to access My Reservations page
		String ReservationUrl = "/frame.php?relay=MCS_PORTAL_RS-ACTIVATE[MY_RESERVATIONS]";

		navigateToPage(ReservationUrl);

		waitForExtJSAjaxComplete(30);

		softAssert.assertTrue(isMyreservationWindowOpen(),"Reservation Window is opened with a link");

		softAssert.assertAll();
		
		Reporter.log("The Reservable Room which is Out of Use is not displayed and cannot be booked."
				+"MyReservation Window is opened with deep link", true);

	}
	
		
	/**
	 * Testcase ID	:	C74328
	 * Author		:	vpcc
	 */
	//This Test case was combined with other testcases
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testVerifyCostCenterInRoomBooking(){
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C74328:Verify that Cost Center value is correctly updated in Room Reservations when changing the value from Cost Center Combo box</span><br>", true);

		
		String region = "1preRgRef";
		String responsible=getUserLastNameOfLoggedInUser();
		int random = (int)(Math.random() * 10)+18;
		
		String date = this.getFutureDate(random);
		
		String from = "13:00";
		String until = "14:00";
		String room = "1preRmRef";
		String project="1preProjectRef";
		String costCenterMethod1=  "From Responsible";
		String costCenterMethod2="From Department";
		String costCenterMethod3="From Project";
		String costCenterMethod4="Other";
		String costCenter = "myMCS Default Cost Center";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testVerifyCostCenterInRoomBooking");

		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWRESERVATIONS);
		
		waitForExtJSAjaxComplete(30);
		
		setRegion(region);
		
		setDate(date);
		
		setTimeFrom(from);
		
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(10);
		
		clickRoomTab();
		
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(10);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(5);

		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(room);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);

		waitForExtJSAjaxComplete(10);
		
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
	 * Testcase ID	:	C74324
	 * Author		:	vpcc
	 */
	//This Testcase was combined with other Testcases
	//@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testRoomBookingPreperationTimeAndCleanUpNotConsidered(){
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C74324:Preparation and Cleanup time should not be considered while calculating Total Cost in Room reservation</span><br>", true);

		
		String region = "1preRgRef";
		String responsible=getUserLastNameOfLoggedInUser();
		int random = (int)(Math.random() * 10)+18;
		
		String date = this.getFutureDate(random);
		
		String from = "13:00";
		String until = "14:00";
		String prepTime = "00:30";
		String cleanupTime = "00:30";
		String room = "1preRmRef";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testReservationAdditionalRoom");

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
		
		clickRoomTab();
		
		setTimeUntil(until);
				
		waitForExtJSAjaxComplete(20);
		
		clickRoomTab();
		
		waitForExtJSAjaxComplete(5);
		
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(room);
		
		waitForExtJSAjaxComplete(20);
		
		setTimePrepareFromReservationPanel(prepTime);
		
		waitForExtJSAjaxComplete(10);
		
		setTimeCleanupFromReservationPanel(cleanupTime);
		
		waitForExtJSAjaxComplete(10);
		
		setResponsible(responsible);

		waitForExtJSAjaxComplete(10);
		
		clickSummaryTab();
		
		waitForExtJSAjaxComplete(15);
		
		String getSummaryText = getItemDetailsFromReservationSummary(room);
		
		softAssert.assertTrue( getSummaryText.contains("1 h x 4.00 EUR"),"Preparation and Cleanup time is not considered while calculating Total Cost in Room reservation");
		
		Reporter.log("Preparation and Cleanup time is not considered while calculating Total Cost in Room reservation<br>", true);
		
		softAssert.assertAll();
		
	}
	

	/**
	 * Testcase ID	:	C74339
	 * author		:	ssa
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testOutOfUseReservRoomCannotBeBooked() throws Exception{


		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C74339:Out of Use rooms cannot be booked</span><br>", true);


		String region = "1preRgRef";

		int random = (int)(Math.random() * 10)+18;

		String roomRef = "OutOfUseRoomRef";
		String date = this.getFutureDate(random);
		String from = "00:30";
		String until = "02:00";
		String regions = "All Regions";
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testOutOfUseReservRoomCannotBeBooked");


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

		waitForExtJSAjaxComplete(10);

		clickRoomTab();

		setTimeUntil(until);

		waitForExtJSAjaxComplete(5);

		clickRoomTab();

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		boolean status=isDisplayLaunchReservation(roomRef);

		softAssert.assertFalse(status,"Reservable room is not displayed");

		softAssert.assertAll();

		Reporter.log("The Reservable Room which is Out of Use is not displayed and cannot be booked.", true);
	}



	/**
	 * Testcase ID	:	C74324
	 * Author		:	vpcc
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testRoomBookingFixedAndVariablePrice(){

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C74324:Preparation and Cleanup time should not be considered while calculating Total Cost in Room reservation</span><br>", true);


		String region = "1preRgRef";
		String responsible=getUserLastNameOfLoggedInUser();
		int random = (int)(Math.random() * 10)+18;

		String date = this.getFutureDate(random);

		String from = "13:00";
		String until = "14:00";
		String prepTime = "00:30";
		String cleanupTime = "00:30";
		String roomWithFixedPriceService = "RoomWithFPSRef";
		String roomWithVariablePriceService = "RoomWithVPSRef";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testRoomBookingFixedAndVariablePrice");

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

		setTimeUntil(until);

		waitForExtJSAjaxComplete(10);

		clickRoomTab();

		waitForExtJSAjaxComplete(5);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(roomWithFixedPriceService);

		waitForExtJSAjaxComplete(10);

		setTimePrepareFromReservationPanel(prepTime);

		waitForExtJSAjaxComplete(10);

		setTimeCleanupFromReservationPanel(cleanupTime);

		waitForExtJSAjaxComplete(10);

		setResponsible(responsible);

		waitForExtJSAjaxComplete(10);

		clickSummaryTab();

		waitForExtJSAjaxComplete(20);

		String getSummaryText = getItemDetailsFromReservationSummary(roomWithFixedPriceService);
		
		System.out.println(getSummaryText);
		
		softAssert.assertTrue( getSummaryText.contains("0.04 d x 7.00 EUR"),"Preparation and Cleanup time is not considered while calculating Total Cost in Room reservation");

		Reporter.log("Preparation and Cleanup time is not considered while calculating Total Cost in Room reservation<br>", true);

		softAssert.assertAll();

	}

	/**
	 * Testcase ID	:	C90127
	 * author		:	KVE
	 * 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testSearchReservableObjects() throws Exception{


		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"Test: C90127:Searching available Reservable Objects via Parameters Panel: Time and Date values</span><br>", true);


		String dateTwoDaysAhead = getFutureDate(2);

		String from = "14:00";
		String until = "19:00";
		String region1 = "1preRgRef";
		String region2 = "2preRgRef";
		String calendarViewBytwentyFourBySeven="View 24/7";
		String calendarViewByWeek = "View Week";


		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testSearchReservableObjects");

		ReservationsGeneralSettingsAdministrationPageObject obj=new ReservationsGeneralSettingsAdministrationPageObject();

		//Select Region 1 -> Search
		obj.clickAdministration();

		waitForExtJSAjaxComplete(20);

		obj.expandModuleSettings();

		AdministrationPageObject objad=new  AdministrationPageObject();

		objad.expandReservations();

		waitForExtJSAjaxComplete(20);

		objad.clickReservationsGenSetings();

		waitForExtJSAjaxComplete(20);

		List<String> fieldNames = Arrays.asList("From","Until");

		waitForExtJSAjaxComplete(20);

		List<String> defaultValues = obj.getDefaultValueDropDownValueForFields(fieldNames);
		
		System.err.println(defaultValues);

		waitForExtJSAjaxComplete(20);

		navigateToMainPage();

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(30);

		setRegion(region1);

		waitForExtJSAjaxComplete(20);

		String fromTime = getFromTime();

		String untilTime = getUntilTime();

		softAssert.assertEquals(getDate(), getCurrentSystemDate(),"equals with current date");

		softAssert.assertEquals(fromTime,defaultValues.get(0),"From time equals with  default From time value defined in the Administration section");

		softAssert.assertEquals(untilTime,defaultValues.get(1),"Until time equals with default Until time value defined in the Administration section");

		clickRoomTab();

		waitForExtJSAjaxComplete(10);

		clickSearch();

		waitForExtJSAjaxComplete(20);
		
		List<String> availableRoomsResults = findRoomsFreeForRegionGivenTime();
		
		System.err.println(availableRoomsResults);
		
		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_CALENDAR);
		
		waitForExtJSAjaxComplete(20);
		
		clickCalenderTab();
		
		waitForMaskDisappear();
		
		ReservationsCalendarNewPageObject cobj=new ReservationsCalendarNewPageObject();

		cobj.setRegionInCalendarTab(region1);

		waitForMaskDisappear();

		cobj.clickOnCalendarViews(calendarViewByWeek);

		waitForMaskDisappear();

		cobj.clickOnCalendarViews(calendarViewBytwentyFourBySeven);

		waitForMaskDisappear();

		List<String> roomsAvailableFromCalendarTab = cobj.findRoomsFreeForGivenTime(fromTime);
		
		System.err.println(roomsAvailableFromCalendarTab);

		softAssert.assertEqualsNoOrder(roomsAvailableFromCalendarTab.toArray(),availableRoomsResults.toArray(), "Rooms available for the specified Time and Date period are displayed.");


		//Select Region 2 -> Search

		navigateToMainPage();

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region2);

		softAssert.assertEquals(getDate(), getCurrentSystemDate(),"equals with current date");

		softAssert.assertEquals(fromTime,defaultValues.get(0),"From time equals with  default From time value defined in the Administration section");

		softAssert.assertEquals(untilTime,defaultValues.get(1),"Until time equals with  default Until time value defined in the Administration section");

		clickRoomTab();

		clickSearch();
		
		waitForExtJSAjaxComplete(20);

		List<String> availableRoomsResults1 = findRoomsFreeForRegionGivenTime();

		waitForExtJSAjaxComplete(20);

		clickCalenderTab();

		waitForExtJSAjaxComplete(20);

		cobj.setRegionInCalendarTab(region2);

		waitForExtJSAjaxComplete(20);

		List<String> roomsAvailableFromCalendarTab1 = cobj.findRoomsFreeForGivenTime(fromTime);

		softAssert.assertEqualsNoOrder(roomsAvailableFromCalendarTab1.toArray(),availableRoomsResults1.toArray(), "Rooms available for the specified Reservation Region are displayed.");


		//Specify the Date (e.g. two days ahead current) -> Search
		navigateToMainPage();

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region2);

		setDate(dateTwoDaysAhead);

		clickRoomTab();

		clickSearch();

		List<String> availableRoomsResults2 = findRoomsFreeForRegionGivenTime();

		waitForExtJSAjaxComplete(20); 

		clickCalenderTab();

		waitForExtJSAjaxComplete(10);

		cobj.setRegionInCalendarTab(region2);

		waitForExtJSAjaxComplete(10);

		cobj.setDateViaDatePicker(dateTwoDaysAhead);

		waitForExtJSAjaxComplete(10);

		List<String> roomsAvailableFromCalendarTab2 = cobj.findRoomsFreeForGivenTime(fromTime);

		softAssert.assertEqualsNoOrder(roomsAvailableFromCalendarTab2.toArray(),availableRoomsResults2.toArray(), "Rooms available for the specified Date are displayed.");


		//Specify the Time Period (e.g. from 14 00 - till 19 00) -> Search
		navigateToMainPage();

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region2);

		setTimeFrom(from);

		setTimeUntil(until);

		clickRoomTab();

		clickSearch();

		List<String> availableRoomsResults3 = findRoomsFreeForRegionGivenTime();

		waitForExtJSAjaxComplete(20);

		clickCalenderTab();

		waitForExtJSAjaxComplete(20);

		cobj.setRegionInCalendarTab(region2);

		waitForExtJSAjaxComplete(20);

		List<String> roomsAvailableFromCalendarTab3 = cobj.findRoomsFreeForGivenTime(from);

		softAssert.assertEqualsNoOrder(roomsAvailableFromCalendarTab3.toArray(),availableRoomsResults3.toArray(), "Rooms available for the specified Time Period are displayed.");


		//Select Region 1 -> Search for Equipment

		navigateToMainPage();

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region1);

		softAssert.assertEquals(getDate(), getCurrentSystemDate(),"equals with current date");

		softAssert.assertEquals(fromTime,defaultValues.get(0),"From time equals with  default From Time value defined in the Administration section");

		softAssert.assertEquals(untilTime,defaultValues.get(1),"Until time equals with  default Until Time value defined in the Administration section");

		clickEquipmentTab();

		clickSearch();

		List<String> availableEquipmentResults = findEquipmentFreeForRegionGivenTime();

		waitForExtJSAjaxComplete(20);  

		clickCalenderTab();

		waitForExtJSAjaxComplete(20);

		cobj.clickEquipmentTabInCalendarTab();

		waitForExtJSAjaxComplete(20);

		cobj.setRegionInCalendarTab(region1);

		waitForExtJSAjaxComplete(20);

		cobj.clickOnCalendarViews(calendarViewByWeek);

		waitForExtJSAjaxComplete(20);

		cobj.clickOnCalendarViews(calendarViewBytwentyFourBySeven);

		waitForExtJSAjaxComplete(20);

		List<String> EquipmentAvailableFromCalendarTab = cobj.findEquipmentFreeForGivenTime(fromTime);

		softAssert.assertEqualsNoOrder(EquipmentAvailableFromCalendarTab.toArray(),availableEquipmentResults.toArray(), "Equipment available for the specified Time and Date period are displayed.");


		//Select Region 2 -> Search for Equipment
		navigateToMainPage();

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region2);

		softAssert.assertEquals(getDate(), getCurrentSystemDate(),"equals with current date");

		softAssert.assertEquals(fromTime,defaultValues.get(0),"From time equals with  default From Time value defined in the Administration section");

		softAssert.assertEquals(untilTime,defaultValues.get(1),"Until time equals with  default Until Time value defined in the Administration section");

		clickEquipmentTab();

		clickSearch();
		
		waitForExtJSAjaxComplete(20);

		List<String> availableEquipmentResults1=findEquipmentFreeForRegionGivenTime();

		waitForExtJSAjaxComplete(20); 

		clickCalenderTab();

		waitForExtJSAjaxComplete(20);

		cobj.clickEquipmentTabInCalendarTab();

		waitForExtJSAjaxComplete(20);

		cobj.setRegionInCalendarTab(region2);

		waitForExtJSAjaxComplete(20);

		List<String> EquipmentAvailableFromCalendarTab1 = cobj.findEquipmentFreeForGivenTime(fromTime);

		softAssert.assertEqualsNoOrder(EquipmentAvailableFromCalendarTab1.toArray(),availableEquipmentResults1.toArray(), "Equipment available for the specified Reservation Region are displayed.");

		//Specify the Date (e.g. two days ahead current) -> Search for Equipment
		navigateToMainPage();

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region2);

		setDate(dateTwoDaysAhead);

		clickEquipmentTab();

		clickSearch();

		List<String> availableEquipmentResults2 = findEquipmentFreeForRegionGivenTime();

		waitForExtJSAjaxComplete(20);

		clickCalenderTab();

		waitForExtJSAjaxComplete(20);

		cobj.clickEquipmentTabInCalendarTab();

		waitForExtJSAjaxComplete(20);

		cobj.setRegionInCalendarTab(region2);

		waitForExtJSAjaxComplete(20);

		cobj.setDateViaDatePicker(dateTwoDaysAhead);

		waitForExtJSAjaxComplete(20);

		List<String> EquipmentAvailableFromCalendarTab2 = cobj.findEquipmentFreeForGivenTime(fromTime);

		softAssert.assertEqualsNoOrder(EquipmentAvailableFromCalendarTab2.toArray(),availableEquipmentResults2.toArray(), "Equipment available for the specified Date are displayed.");


		//Specify the Time Period (e.g. from 14 00 - till 19 00) -> Search for Equipment
		navigateToMainPage();

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region2);

		setTimeFrom(from);

		setTimeUntil(until);

		clickEquipmentTab();

		clickSearch();

		List<String> availableEquipmentResults3 = findEquipmentFreeForRegionGivenTime();

		waitForExtJSAjaxComplete(20);

		clickCalenderTab();

		waitForExtJSAjaxComplete(20);

		cobj.clickEquipmentTabInCalendarTab();

		waitForExtJSAjaxComplete(20);

		cobj.setRegionInCalendarTab(region2);

		waitForExtJSAjaxComplete(20);

		List<String> EquipmentAvailableFromCalendarTab3 = cobj.findEquipmentFreeForGivenTime(from);

		softAssert.assertEqualsNoOrder(EquipmentAvailableFromCalendarTab3.toArray(),availableEquipmentResults3.toArray(), "Equipment available for the specified Time Period are displayed.");

		waitForExtJSAjaxComplete(20);

		//Select Region 1 -> Search for Service

		navigateToMainPage();

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region1);

		softAssert.assertEquals(getDate(), getCurrentSystemDate(),"equals with current date");

		softAssert.assertEquals(fromTime,defaultValues.get(0),"From time equals with  default From Time value defined in the Administration section");

		softAssert.assertEquals(untilTime,defaultValues.get(1),"Until time equals with  default Until Time value defined in the Administration section");

		clickServiceTab();

		clickSearch();

		List<String> availableServiceResults = findServiceFreeForRegionGivenTime();

		List<String> servicesavailable = Arrays.asList("4preSrvNoRoomRef","preDerSrvRef","1preSrvRef","aqaserviceref","2preSrvRef");

		softAssert.assertTrue(availableServiceResults.containsAll(servicesavailable), "Services available for the specified Time and Date period are displayed.");


		//Select Region 2 -> Search for Service

		navigateToMainPage();

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region2);

		softAssert.assertEquals(getDate(), getCurrentSystemDate(),"equals with current date");

		softAssert.assertEquals(fromTime,defaultValues.get(0),"From time equals with  default From Time value defined in the Administration section");

		softAssert.assertEquals(untilTime,defaultValues.get(1),"Until time equals with  default Until Time value defined in the Administration section");

		clickServiceTab();

		clickSearch();

		List<String> availableServiceResults1 = findServiceFreeForRegionGivenTime();

		List<String> servicesavailable1 = Arrays.asList("5preSrvRef");

		softAssert.assertTrue(availableServiceResults1.containsAll(servicesavailable1), "Services available for the specified Reservation Region are displayed.");

		waitForExtJSAjaxComplete(20);

		//Specify the Date (e.g. two days ahead current) -> Search for Service
		navigateToMainPage();

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region2);

		setDate(dateTwoDaysAhead);

		clickServiceTab();

		clickSearch();

		List<String> availableServiceResults2 = findServiceFreeForRegionGivenTime();

		softAssert.assertTrue(availableServiceResults2.containsAll(servicesavailable1), "Services available for the specified Date are displayed.");

		waitForExtJSAjaxComplete(20);

		//Specify the Time Period (e.g. from 14 00 - till 19 00) -> Search for Service
		navigateToMainPage();

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		setRegion(region2);

		setTimeFrom(from);

		setTimeUntil(until);

		clickServiceTab();

		clickSearch();

		List<String> availableServiceResults3 = findServiceFreeForRegionGivenTime();

		softAssert.assertTrue(availableServiceResults3.containsAll(servicesavailable1), "Services available for the specified Time Period are displayed.");

		waitForExtJSAjaxComplete(20);

		softAssert.assertAll();

		Reporter.log("successfully verified the reservable objects for specified Date and Time <br>", true);

	}


	/**
	 * Testcase ID : C74324,C74326,C74328
	 * Author      : kve
	 **/
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testCostCenterValueInRoomReservation(){

		Reporter.log("<span style='font-weight:bold;color:#000033'>"

		 +"Test: C74324: Preparation and Cleanup time should not be considered while calculating Total Cost in Room reservation"
		 +"C74326: Verify that Total Cost is correctly calculated and shown in summary for both Fixed and Variable Product or Service in Room reservation"
		 +"C74328: Verify that Cost Center value is correctly updated in Room Reservations when changing the value from Cost Center Combo box", true);

		String from = "13:00";
		String until = "14:00";
		String region = "1preRgRef";
		String room = "1aqaRoomRef"; //fixed price
		String room1 = "1PreRoomRef"; //Variable price
		String responsible = getUserLastNameOfLoggedInUser();
		String prepTime = "00:30";
		String cleanupTime = "00:30";
		String Category ="1preRmCtDescr";
		String RoomPurpose ="1preRmRsPrRef";
		String NoOfPeople ="5";
		String RoomLayout ="1preRmCmCod";
		String AlreadyInRoom ="1preGnEqRef";
		String from1 = "20:00";
		String until1 = "21:00";
		String project = "1preProjectRef";
		String costCenterMethod1=  "From Responsible";
		String costCenterMethod2="From Department";
		String costCenterMethod3="From Project";
		String costCenterMethod4="Other";
		String costCenter = "myMCS Default Cost Center";
		String costCenter1 = "Billing Rule Default";


		int random = (int)(Math.random() * 10)+18;
		String date = this.getFutureDate(random);

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testCostCenterValueInRoomReservation");

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

		setRoomCategory(Category);

		waitForExtJSAjaxComplete(20);

		setNrPeople(NoOfPeople);

		waitForExtJSAjaxComplete(20);

		setRoomPurpose(RoomPurpose);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setRoomLayout(RoomLayout);

		waitForExtJSAjaxComplete(20);

		selectAlreadyInRoom(AlreadyInRoom);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(room);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setTimePrepareFromReservationPanel(prepTime);

		waitForExtJSAjaxComplete(20);

		setTimeCleanupFromReservationPanel(cleanupTime);

		waitForExtJSAjaxComplete(20);

		setResponsible(responsible);

		waitForMaskDisappear();

		setProject(project);

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getProject(), project,"project was selected");

		clickTabInReservationDetailsSection("Financial");
		
		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);

		setCostCenterMethodInFinancialsTab(costCenterMethod4);

		waitForExtJSAjaxComplete(20);

		setCostCenter(costCenter1);

		waitForExtJSAjaxComplete(20);

		setCostCenterMethodInFinancialsTab(costCenterMethod1);

		waitForExtJSAjaxComplete(20);

		String costCenterFromResponsible = getCostCenter();

		softAssert.assertEquals(costCenterFromResponsible, costCenter,"Cost Center lookup value is automatically filled with the Cost Center which is linked to the Responsible Person.");

		waitForExtJSAjaxComplete(20);

		setCostCenterMethodInFinancialsTab(costCenterMethod4);

		waitForExtJSAjaxComplete(20);

		setCostCenter(costCenter1);

		waitForExtJSAjaxComplete(20);

		setCostCenterMethodInFinancialsTab(costCenterMethod2);

		waitForExtJSAjaxComplete(20);

		String costCenterFromDept = getCostCenter();

		softAssert.assertEquals(costCenterFromDept, costCenter,"Cost Center lookup value is automatically filled with the Default Cost Center which is linked to the Department.");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setCostCenterMethodInFinancialsTab(costCenterMethod4);

		waitForExtJSAjaxComplete(20);

		setCostCenter(costCenter1);

		waitForExtJSAjaxComplete(20);

		setCostCenterMethodInFinancialsTab(costCenterMethod3);

		waitForExtJSAjaxComplete(20);

		String costCenterFromProject = getCostCenter();

		softAssert.assertEquals(costCenterFromProject, costCenter,"Cost Center lookup value is automatically filled with the Cost Center which is linked to the Project.");

		waitForExtJSAjaxComplete(20);

		setCostCenterMethodInFinancialsTab(costCenterMethod4);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isCostCenterLookUpEnabled(),"Cost Center lookup control is enabled.");

		waitForExtJSAjaxComplete(20);

		setCostCenter(costCenter1);

		waitForExtJSAjaxComplete(20);

		String costCenterFromOther = getCostCenter();

		softAssert.assertTrue(costCenterFromOther.contains(costCenter1),"Cost Center(s) linked to the Fiscal Entity that is assigned to the responsible person are only displayed.");

		clickSummaryTab();

		waitForExtJSAjaxComplete(20);

		String getSummaryText = getItemDetailsFromReservationSummary(room);

		softAssert.assertTrue( getSummaryText.contains("0.04 d x 6.00 EUR"),"Preparation and Cleanup time is not considered while calculating Total Cost in Room reservation");

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

		clickLaunchReservation(room1);

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
		
		String getSummaryText1 = getItemDetailsFromReservationSummary(room1);

		softAssert.assertTrue( getSummaryText1.contains("0.04 d x 6.00 EUR"),"Total cost is correctly calculated as per the linked Product or Service.");

		waitForExtJSAjaxComplete(20);

		clickGeneralTab();		

		waitForExtJSAjaxComplete(20);

		setTimeFromReservationPanel(from1);

		waitForExtJSAjaxComplete(20);

		setTimeUntilReservationPanel(until1);

		waitForExtJSAjaxComplete(20);
		
		this.clickConfirmReservation();
		
		waitForExtJSAjaxComplete(25);
		
		this.waitForMaskDisappear();

		clickSummaryTab();

		waitForExtJSAjaxComplete(20);
		
		String getSummaryText2 = getItemDetailsFromReservationSummary(room1);

		softAssert.assertTrue( getSummaryText2.contains("0.04 d x 7.00 EUR"),"Total cost is correctly calculated as per the linked Product or Service.");

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
		
		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);

		ReservationMyReservationsCRUDTestSuite myReserv = new ReservationMyReservationsCRUDTestSuite();

		myReserv.setRegionMyReservation(region);

		waitForExtJSAjaxComplete(15);

		myReserv.setFutureDateMyReservation(date);

		waitForExtJSAjaxComplete(10);

		myReserv.clickSearchButton();

		waitForExtJSAjaxComplete(20);

		checkRowInGriByTextValueAndClick(date + " " + from1, room1);

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		clickViewCancel();

		waitForExtJSAjaxComplete(20);

		clickGeneralTab();

		waitForExtJSAjaxComplete(20);

		clickCancelReservation();

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(20);

		softAssert.assertAll();

		Reporter.log("Verify that Total Cost is correctly calculated and shown in summary Tab in Room reservation<br>", true);

	}

	
	/**
	 * Testcase ID : C91059,C91017
	 * Author  : ssa
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testUnfilledMandatoryFields(){

		Reporter.log("<span style='font-weight:bold;color:#000033'> "

		
		 +"C91059:Reservation General Fields: Reservation is not saved with unfilled mandatory fields (MYM-26574)"
		 +"C91017: Reservation is not Booked with unspecified Region (MYM-26574)", true);
		
		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testUnfilledMandatoryFields");
		
		String region = "1preRgRef";
		String region1 = "All Regions";
		String responsible=getUserLastNameOfLoggedInUser();
		int random = (int)(Math.random() * 10)+18;

		String date = this.getFutureDate(random);

		String from = "01:00";
		String until = "02:00";
		
		String from1 = "00:00";
		String until1 = "00:00";
			
		String from2 = "02:00";
		String until2 = "01:00";
		
		String room = "1PreRoomRef";
		String room1 = "RoomWithNoRegionRef";
		String equipment= "EqpWithNoRegionRef";
		String service = "SrvWithNoRegionRef";
		
		String warningMsg="Unable to save";
		String warningMsg1="Reservation Line start and stop time have to fall inside Reservation start and stop time";
		String warningMsg2="Region is not specified";		
		
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
		
		clickGeneralTab();
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		
		this.setTimeFromReservationPanel(from1);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		this.setTimeUntilReservationPanel(until1);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(getWarningDialogTextMsg().contains(warningMsg),"error message is displayed for unfill from and until time");
		
		waitForExtJSAjaxComplete(20);
		
		this.clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		this.setTimeFromReservationPanel(from2);
		
		waitForExtJSAjaxComplete(20);
		
		this.setTimeUntilReservationPanel(until2);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(getWarningDialogTextMsg().contains(warningMsg1),"error message is displayed for from time is greater than untill time ");
		
		//C91017
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

		setTimeFrom(from);

		setTimeUntil(until);

		waitForExtJSAjaxComplete(20);

		clickRoomTab();

		waitForExtJSAjaxComplete(20);
		
		List<String> itemsList=Arrays.asList(room1,equipment,service);
		
		for(String items:itemsList)
		{
		if(items ==equipment)
		{
		clickEquipmentTab();
		}else if(items==service){
			clickServiceTab();
		}
		clickSearch();
		
		waitForExtJSAjaxComplete(20);
		
		clickLaunchReservation(items);
		
		waitForExtJSAjaxComplete(20);

		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		
		clickConfirmReservation();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(getWarningDialogTextMsg().contains(warningMsg2),"error message is displayed for room, equipment and service are not added to region");
		
		this.clickOnDialogButtonCustomized("OK");
		}
		
		softAssert.assertAll();

		Reporter.log("successfully verified error message in New Reservations window <br>", true);
		
	}
	
	//TO DO: Duplicated testcase
	/**
	 * Testcase ID	:	C91329
	 * author		:	ssa
	 * 
	 *//*
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
		
		String responsible = getUserLastNameOfLoggedInUser();
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
		
		clickRoomTab();
		
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
		
		setResponsible(responsible);
		
		clickConfirmReservation();
		
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

		clickLaunchReservation(room);
		
		setResponsible(responsible);
		
		waitForExtJSAjaxComplete(20);
		
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
*/
	/**
	 * Testcase ID	:	C89560
	 * Author		:	KVE
	 * @throws ParseException 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testCopyReservInNewReserv() throws ParseException{

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C89560:Copy Reservation of a meeting Room for a Conference Meeting via New ReservationÂ (MYM-24564)</span><br>", true);


		int random = (int)(Math.random() * 10)+18;
		String date = this.getFutureDate(random);

		String region = "2preRgRef";
		String region1 = "1preRgRef";
		String region2 = "All Regions";
		String responsible=getUserLastNameOfLoggedInUser();
		String from = "13:00";
		String until = "14:00";
		String room = "3preRmRef";
		String room1 = "1preRmRef";
		String reference = "2preRmRef";
		String subject= "Room reservation";
		String remark = "remark";
		String project="1preProjectRef";
		String costCenterMethod=  "From Responsible";
		String costCenter = "myMCS Default Cost Center";
		String finKey3 = "3preValue";
		String finKey4 = "4preValue";
		String department = "AQA_DEPARTMENT";
		String responsible1=this.getUserNameOfLoggedInUserFirstNameLastNameFormat();
		String room2="2preRmRef";
		String contact=this.getUserLastNameOfLoggedInUser();
		String contact1=this.getUserNameOfLoggedInUserFirstNameLastNameFormat();

		String calendarViewBytwentyFourBySeven="View 24/7";
		String calendarViewByWeek = "View Week";


		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testCopyReservInNewReserv");

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

		softAssert.assertTrue(isReservationDone(), "Confidential Reservation is confirmed");

		softAssert.assertTrue(isCopyReservationDisplayed(), "Copy button is displayed and enabled ");

		clickCopyReservation();

		softAssert.assertEquals(getWindowTitle("@class","x-resizable-pinned"),"Select a Room","Available Tab is displayed with Empty Grid");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Code"), "Code Column is Present");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Reference"), "Reference Column is Present");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Capacity"), "Capacity Column is Present");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Category"), "Category Column is Present");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Location"), "Location Column is Present");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Reservation Region"), "Reservation Region Column is Present");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Room Purpose"), "Room Purpose Column is Present");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Room Layout"), "Room Layout Column is Present");

		driver.navigate().refresh();

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWRESERVATIONS);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setRegion(region1);

		setDate(date);

		setTimeFrom(from);

		setTimeUntil(until);

		waitForExtJSAjaxComplete(20);

		clickRoomTab();

		clickSearch();

		waitForExtJSAjaxComplete(20);

		clickLaunchReservation(room1);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickGeneralSubject();

		waitForExtJSAjaxComplete(20);

		setGeneralSubject(subject);

		clickGeneralTab();

		clickGeneralRemarks();

		waitForExtJSAjaxComplete(20);

		setGeneralRemarks(remark);

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		setResponsible(responsible);

		waitForExtJSAjaxComplete(20);
		
		setContact(contact);
		
		waitForExtJSAjaxComplete(20);
		
		setProject(project);
		
		waitForExtJSAjaxComplete(10);

		clickTabInReservationDetailsSection("Financial");

		waitForExtJSAjaxComplete(20);

		//TO DO: Field is disabled
		//setCostCenterMethodInFinancialsTab(costCenterMethod);

		setFinKey3(finKey3);

		waitForExtJSAjaxComplete(20);

		setFinKey4(finKey4);
		
		waitForExtJSAjaxComplete(20);

		clickConfirmReservation();

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isReservationDone(), "Reservation is confirmed");

		softAssert.assertTrue(isCopyReservationDisplayed(), "Copy button is displayed and enabled ");

		clickCopyReservation();

		softAssert.assertEquals(getWindowTitle("@class","x-resizable-pinned"),"Select a Room","Active Tab Contains a List of Active Rooms from a selected Region");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Code"), "Code Column is Present");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Reference"), "Reference Column is Present");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Capacity"), "Capacity Column is Present");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Category"), "Category Column is Present");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Location"), "Location Column is Present");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Reservation Region"), "Reservation Region Column is Present");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Room Purpose"), "Room Purpose Column is Present");

		softAssert.assertTrue(verifyColumnsInGrid("@class", "x-window x-window-noborder", "Room Layout"), "Room Layout Column is Present");

		List<String> availableRoomsResults = findRoomsFreeForGivenRegion();
		
		System.err.println(availableRoomsResults);

		clickCANCELInLookupWindow();
		
		waitForExtJSAjaxComplete(20);
		
		clickCloseReservationUsingJS();

		ReservationsCalendarNewPageObject calendarPageObj=new ReservationsCalendarNewPageObject();
		
		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_CALENDAR);
		
		waitForExtJSAjaxComplete(20);
		
		clickCalenderTab();
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		calendarPageObj.setRegionInCalendarTab(region1);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		calendarPageObj.clickOnCalendarViews("View Week");
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		calendarPageObj.clickOnCalendarViews("View 24/7");
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		calendarPageObj.clickRoomsTabInCalendarTab();
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		List<String> roomsAvailableFromCalendarTab = calendarPageObj.findRoomsFreeForGivenTime(from);
		
		System.err.println(roomsAvailableFromCalendarTab);
		
		softAssert.assertEqualsNoOrder(roomsAvailableFromCalendarTab.toArray(),availableRoomsResults.toArray(), "All available rooms are displayed in New Reservations Screen");

		waitForExtJSAjaxComplete(20);

		navigateToMainPage();

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Reservation");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_MYRESERVATIONS); 

		waitForExtJSAjaxComplete(20);  

		waitForExtJSAjaxComplete(20);

		clickMyReservationsTab();

		waitForExtJSAjaxComplete(20);

		ReservationMyReservationsCRUDTestSuite myReserv=new ReservationMyReservationsCRUDTestSuite();

		myReserv.setRegionMyReservation(region2);

		waitForExtJSAjaxComplete(20);

		myReserv.setFutureDateMyReservation(date);

		waitForExtJSAjaxComplete(20);

		myReserv.clickSearchButton();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		checkRowInGriByTextValueAndClick(date+" "+from,room1);

		waitForExtJSAjaxComplete(20);

		clickCopyReservation();

		waitForExtJSAjaxComplete(20);

		roomsAvailableFromCalendarTab.toArray();

		setValueGridLookup(reference);

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getNewReservationFromTime(),from, "from time is copied from original Reservation");

		softAssert.assertEquals(getNewReservationUntilTime(),until, "until time is copied from original Reservation");

		softAssert.assertEquals(getGeneralSubject(),subject, "subject is copied from Reservation");

		softAssert.assertEquals(getGeneralRemarks(),remark, "remark is copied from original Reservation");

		softAssert.assertEquals(getResponsible(),responsible1, "responsible1 is copied from original Reservation");

		softAssert.assertEquals(getContact(),contact1, "contact is copied from original Reservation");

		softAssert.assertEquals(getDepartment(),department,"department is copied from original Reservation");

		softAssert.assertEquals(getProject(), project,"project is copied from original Reservation");

		String costCenterFromResponsible = getCostCenter();
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(costCenterFromResponsible, costCenter,"Cost Center is copied from original Reservation");

		softAssert.assertEquals(getFinKey3(),finKey3,"finkey 3 is copied from original Reservation");

		softAssert.assertEquals(getFinKey4(),finKey4,"finkey 4 is copied from original Reservation");

		clickOrderItemsTab();

		waitForExtJSAjaxComplete(20);

		expandOrderItem(room2);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(orderItemExist(room2), "order Item Room Exist ");

		waitForExtJSAjaxComplete(20);

		clickConfirmReservation();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isReservationDone(), "Reservation is confirmed and Reservation Data is completely copied to the New Reservation");

		waitForExtJSAjaxComplete(20);
		
		clickCloseReservationUsingJS();
		
		waitForExtJSAjaxComplete(20);

		clickMyReservationsTab();

		waitForMaskDisappear();

		myReserv.setRegionMyReservation(region2);

		waitForExtJSAjaxComplete(20);

		myReserv.setFutureDateMyReservation(date);

		waitForExtJSAjaxComplete(20);

		myReserv.clickSearchButton();

		waitForExtJSAjaxComplete(20);

		checkRowInGriByTextValueAndClick(date+" "+from,room);

		waitForExtJSAjaxComplete(20);

		clickViewCancel();

		waitForExtJSAjaxComplete(20);

		clickGeneralTab();

		clickCancelReservation();

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		checkRowInGriByTextValueAndClick(date+" "+from,room1);

		waitForExtJSAjaxComplete(20);

		clickViewCancel();

		waitForExtJSAjaxComplete(20);

		clickGeneralTab();

		clickCancelReservation();

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(20);

		checkRowInGriByTextValueAndClick(date+" "+from,room2);

		waitForExtJSAjaxComplete(20);

		clickViewCancel();

		waitForExtJSAjaxComplete(20);

		clickGeneralTab();

		waitForExtJSAjaxComplete(20);

		clickCancelReservation();

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(20);

		softAssert.assertAll();

		Reporter.log("Copy Reservation of a meeting Room in New Reservation was done successfully", true);

	}
	
	/**
	 * TestCase ID : C111717
	 * Author      : MMA
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testDeletePrepareAndCleanupTimeNoOverlappingMessageIsDisplayed() throws Exception{

		Reporter.log(
				"<span style='font-weight:bold;color:#000033'> "
						+ "C111717 : After delete Prepare/Cleanup time no overlapping messages when book new reservation in the same room </span><br>",true);
		String region = "1preRgRef";
		int random = (int) (Math.random() * 10) + 18;
		String date = this.getFutureDate(random);
		String room = "1aqaRoomRef";
		String[] from = {"18:00","17:00"}; 
		String until = "19:00";
		String until1 = "17:45";
		String prepTime="00:15";
		String cleanupTime="00:15";
		String prepTime1="00:00";
		String cleanupTime1="00:00";
		String responsible = getUserLastNameOfLoggedInUser();
		boolean isReservation = false;

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testDeletePrepareAndCleanupTimeNoOverlappingMessageIsDisplayed");
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
			setTimeFrom(from[0]);
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

			this.setTimePrepareFromReservationPanel(prepTime);
			waitForExtJSAjaxComplete(10);

			this.setTimeCleanupFromReservationPanel(cleanupTime);
			waitForExtJSAjaxComplete(10);

			clickConfirmReservation();
			waitForExtJSAjaxComplete(20);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(20);

			softAssert.assertTrue(isReservationDone(), "Confidential Reservation is confirmed");
			isReservation= true;
			this.setTimePrepareFromReservationPanel(prepTime1);
			waitForExtJSAjaxComplete(10);

			this.setTimeCleanupFromReservationPanel(cleanupTime1);
			waitForExtJSAjaxComplete(10);

			clickConfirmReservation();
			waitForExtJSAjaxComplete(20);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(20);

			softAssert.assertEquals(getCleanUpTimeGeneral(), cleanupTime1,"Changes are saved - cleanup time is changed accordingly");
			softAssert.assertEquals(getPrepareTimeGeneral(), prepTime1,"Changes are saved - prepare time is changed accordingly");
			waitForExtJSAjaxComplete(10);

			closeModule("Reservation");
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
			setTimeFrom(from[1]);
			setTimeUntil(until1);
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

			clickConfirmReservation();
			waitForExtJSAjaxComplete(20);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(20);

			softAssert.assertTrue(isReservationDone(), "Confidential Reservation is confirmed");
			waitForExtJSAjaxComplete(10);
		}
		finally{
			if (isReservation = true) {

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

				myReserv.setTodayDateInMyReservation(date);
				myReserv.clickSearchButton();
				for(String fromTime : from){

					checkRowInGriByTextValueAndClick(date+" "+fromTime,room);
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

			} else {
				System.err.println("Reservation not done");
			}
		}
	}


	/**
	 * Testcase ID : C114982
	 * Author      : MMA
	 * @throws Exception
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testEditGeneralPropertiesOfOwnReservation() throws Exception{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C114982 : Edit general properties of own reservation </span><br>",true);

		String region = "1preRgRef";
		int random = (int) (Math.random() * 10) + 18;
		String futureDate = this.getFutureDate(random);
		String date = futureDate;
		String room = "1aqaRoomRef";
		String from = "02:00"; 
		String until = "03:00";
		String responsible = getUserLastNameOfLoggedInUser();
		boolean isReservation = false;
		String calendarViewByWeek = "View Week";
		String calendarViewBytwentyFourBySeven = "View 24/7";
		String currentLogIn = configuration.getUserName();
		String changeUserID = "aqa_Defaultathen";
		String pswd = "qwerty";

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testEditGeneralPropertiesOfOwnReservation");

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

			clickConfirmReservation();
			waitForExtJSAjaxComplete(20);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(20);

			softAssert.assertTrue(isReservationDone(), "Reservation is confirmed");
			isReservation= true;
			String newResVID = getReserationID();
			waitForExtJSAjaxComplete(20);

			String[] userIDs =  {currentLogIn,changeUserID};

			for(String userID : userIDs){

				if(userID == changeUserID){
					navigateToMainPage(changeUserID,pswd) ;
					waitForExtJSAjaxComplete(20);
					waitForMaskDisappear();

					expandAdministration();
					waitForExtJSAjaxComplete(20);

					expandSubMainMenu("Reservation");
					waitForExtJSAjaxComplete(20);

					region = "1aqaRgRef";
					room = "aqaroomref";
					date = "24-04-2018";
					newResVID = "62";
				}
				waitAndClick(XPATH_MYRESERVATIONS);
				waitForExtJSAjaxComplete(20);

				ReservationMyReservationsPageObject myReserv=new ReservationMyReservationsPageObject();
				myReserv.setRegionMyReservation(region);
				waitForExtJSAjaxComplete(25);

				myReserv.setTodayDateInMyReservation(date);
				myReserv.clickSearchButton();

				checkRowInGriByTextValueAndClick(date+" "+from,room);
				waitForExtJSAjaxComplete(25);

				clickViewCancel();
				waitForExtJSAjaxComplete(25);

				clickGeneralTab();
				waitForExtJSAjaxComplete(25);
				waitForExtJSAjaxComplete(25);

				String myResvID = getReserationID();
				softAssert.assertEquals(newResVID, myResvID,"The Reservation is visible.");

				waitAndClick(XPATH_CALENDAR);
				waitForMaskDisappear();

				clickCalenderTab();
				waitForMaskDisappear();
				waitForExtJSAjaxComplete(20);

				ReservationsCalendarNewPageObject calendarPageObj=new ReservationsCalendarNewPageObject();

				calendarPageObj.clickOnCalendarViews(calendarViewByWeek);
				waitForMaskDisappear();
				waitForExtJSAjaxComplete(20);

				calendarPageObj.clickOnCalendarViews(calendarViewBytwentyFourBySeven);
				waitForMaskDisappear();
				waitForExtJSAjaxComplete(20);

				calendarPageObj.setDateViaDatePicker(date);
				waitForMaskDisappear();
				waitForExtJSAjaxComplete(20);

				calendarPageObj.setRegionInCalendarTab(region);
				waitForMaskDisappear();
				waitForExtJSAjaxComplete(20);

				calendarPageObj.clickRoomsTabInCalendarTab();
				String reservIDWithoutString = newResVID.replace("Reservation", "").trim();
				calendarPageObj.clickReservationInCalendar(room, reservIDWithoutString);

				clickGeneralTab();
				waitForExtJSAjaxComplete(20);

				clickTentative();
				waitForExtJSAjaxComplete(20);

				softAssert.assertTrue(isConfirmButtonDisabled(),"Reservation is editable");
				clickConfirmReservation();
				waitForExtJSAjaxComplete(20);
			}
		}

		finally{
			if (isReservation = true) {

				navigateToMainPage(currentLogIn,pswd) ;
				waitForExtJSAjaxComplete(25);
				waitForExtJSAjaxComplete(25);

				expandAdministration();
				waitForExtJSAjaxComplete(20);

				expandSubMainMenu("Reservation");
				waitForExtJSAjaxComplete(20);

				waitAndClick(XPATH_MYRESERVATIONS);
				waitForExtJSAjaxComplete(20);

				ReservationMyReservationsPageObject myReserv=new ReservationMyReservationsPageObject();
				region = "1preRgRef";
				room = "1aqaRoomRef";
				myReserv.setRegionMyReservation(region);
				waitForExtJSAjaxComplete(25);

				date = futureDate;
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

			} else {
				Reporter.log("Reservation not done");
			}
		}

		softAssert.assertAll();
		Reporter.log(" Edit general properties of own reservation", true);
	}
	
	
	/**
	 * TestCase ID : C113846, C113880
	 * Author      : MMA
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testCopyExistingReservationOfRoomWithEquipment()throws Exception{
		Reporter.log("<span style='font-weight:bold;color:#000033'>"
				+ "C113846 : Copy existing Reservation of Room with Equipment and Services via “Calendar” (MYM-24564)"
				+ "C113880 : Copy Reservation: Searching&Sorting list of available Rooms (MYM-24564)",true);

		String region = "2preRgRef";
		int random = (int) (Math.random() * 10) + 18;
		String futureDate = this.getFutureDate(random);
		String date = futureDate;
		String[] rooms = {"RoomWithVPSRef","roomEditRightsRef"};
		String[] roomCode = {"roomEditRightsCode","3preRmCod"};
		String[] roomRef = {"roomEditRightsRef","RoomWithVPSRef"};
		String[] roomCapacity = {"8","10"};
		String equipment = "2aqaEqRef";
		String from = "05:00"; 
		String until = "06:00";
		String responsible = getUserLastNameOfLoggedInUser();
		String contact=this.getUserLastNameOfLoggedInUser();
		String responsible1=this.getUserNameOfLoggedInUserFirstNameLastNameFormat();
		String contact1=this.getUserNameOfLoggedInUserFirstNameLastNameFormat();
		boolean isReservation = false;
		String calendarViewByWeek = "View Week";
		String calendarViewBytwentyFourBySeven = "View 24/7";
		String remark = "remark";
		String subject = "subject";
		String finKey3 = "3preValue";
		String finKey4 = "4preValue";
		String project="1preProjectRef";
		String department = "AQA_DEPARTMENT";
		String costCenter = "myMCS Default Cost Center";
		String windowTitle = "Select a Room";
		List<String> columnsList = Arrays.asList("Code","Reference","Capacity","Category","Location","Reservation Region");

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testCopyExistingReservationOfRoomWithEquipment");
		try{
			//C113846
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

			clickLaunchReservation(rooms[0]);
			waitForExtJSAjaxComplete(20);
			waitForExtJSAjaxComplete(20);

			clickEquipmentTab();
			waitForExtJSAjaxComplete(20);

			clickSearch();
			waitForExtJSAjaxComplete(20);

			clickLaunchReservation(equipment);
			waitForExtJSAjaxComplete(20);
			waitForExtJSAjaxComplete(20);

			setResponsible(responsible);
			waitForExtJSAjaxComplete(20);

			setContact(contact);
			waitForExtJSAjaxComplete(20);

			setProject(project);
			waitForExtJSAjaxComplete(10);

			clickTabInReservationDetailsSection("Financial");
			waitForExtJSAjaxComplete(20);

			setFinKey3(finKey3);
			waitForExtJSAjaxComplete(20);

			setFinKey4(finKey4);
			waitForExtJSAjaxComplete(20);

			clickGeneralTab();
			waitForExtJSAjaxComplete(10);

			clickGeneralSubject();
			setGeneralSubject(subject);

			clickGeneralRemarks();
			setGeneralRemarks(remark);

			clickConfirmReservation();
			waitForExtJSAjaxComplete(20);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(20);

			softAssert.assertTrue(isReservationDone(), "Reservation is confirmed");
			isReservation= true;
			String newResVID = getReserationID();
			waitForExtJSAjaxComplete(20);

			navigateToMainPage();
			waitForExtJSAjaxComplete(25);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(25);

			expandAdministration();
			waitForExtJSAjaxComplete(20);
			
			waitAndClick(XPATH_CALENDAR);
			waitForMaskDisappear();

			clickCalenderTab();
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(20);

			ReservationsCalendarNewPageObject calendarPageObj=new ReservationsCalendarNewPageObject();

			calendarPageObj.clickOnCalendarViews(calendarViewByWeek);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(20);

			calendarPageObj.clickOnCalendarViews(calendarViewBytwentyFourBySeven);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(20);

			calendarPageObj.setDateViaDatePicker(date);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(20);

			calendarPageObj.setRegionInCalendarTab(region);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(20);

			calendarPageObj.clickRoomsTabInCalendarTab();
			waitForExtJSAjaxComplete(20);
			String reservIDWithoutString = newResVID.replace("Reservation", "").trim();
			calendarPageObj.clickReservationInCalendar(rooms[0], reservIDWithoutString);
			String CalanderResvID = getReserationID();
			softAssert.assertEquals(newResVID, CalanderResvID,"The Reservation is visible.");
			softAssert.assertFalse(isCopyButtonDisabled(), "'Copy' button is displayed and enabled");

			clickCopyReservation();
			waitForExtJSAjaxComplete(20);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(20);
			
			
			//C113880: 
			//Column list is:	 Code, Reference,Capacity,Category,Location,Reservation Region,Room Purpose,Room Layout is already verified in 'testCopyReservInNewReserv' testcase
			for(String column : columnsList){
				sortColumnsAscDec("@class", "x-window x-window-noborder", column, "Sort Ascending");
				List<String> roomsActualList = getValuesFromGridLookup("@id",getXWindowId(windowTitle), column);
				List<String> roomsExpectedList = new ArrayList<String>();

				if(column!="Capacity"){
					for(int i=0,l=roomsActualList.size();i<l;++i)
						roomsActualList.set(i, roomsActualList.get(i).toLowerCase());


					roomsExpectedList.addAll(roomsActualList);
					waitForExtJSAjaxComplete(20);
					Collections.sort(roomsExpectedList);
					waitForExtJSAjaxComplete(20);
					softAssert.assertEquals(roomsActualList, roomsExpectedList, "Rooms are sorted based on"+column+"");
				}
				else{
					List<Integer> roomsActualListInt = new ArrayList<Integer>();
					List<Integer> roomsExpectedListInt = new ArrayList<Integer>();

					for(String roomActual : roomsActualList) 
						roomsActualListInt.add(Integer.valueOf(roomActual));

					roomsExpectedListInt.addAll(roomsActualListInt);
					waitForExtJSAjaxComplete(20);
					Collections.sort(roomsExpectedListInt);
					waitForExtJSAjaxComplete(20);
					softAssert.assertEquals(roomsActualListInt, roomsExpectedListInt, "Rooms are sorted based on"+column+"");
				}

			}


			int i = 0;
			String[] columnValue;
			for(String column : columnsList){
				if(i<=2){
					columnValue = (String[]) ((i==0) ?roomCode :(i==1)?roomRef:(i==2)?roomCapacity:"");
					String[] roomValues = columnValue;
					filterGrid("@id", getXWindowId("Select a Room"),roomValues[0],column);	
					softAssert.assertTrue(Grid.isRowInGridPresent(driver, roomValues[0],"@class", "grid3"),"Grid is filtered displaying the correct results");
					softAssert.assertFalse(Grid.isRowInGridPresent(driver, roomValues[1],"@class", "grid3"),"Grid is filtered displaying the correct results");
					i++;
					clickCANCELXwindow();
					waitForExtJSAjaxComplete(20);

					clickCopyReservation();
					waitForExtJSAjaxComplete(20);
					waitForMaskDisappear();
					waitForExtJSAjaxComplete(20);
				}
			}


			//C113846
			setValueGridLookupWithFiltersWithoutUsingMCSElement("@id", getXWindowId("Select a Room"), rooms[1], "Reference");
			waitForExtJSAjaxComplete(20);
			waitForExtJSAjaxComplete(20);

			softAssert.assertEquals(getNewReservationFromTime(),from, "from time is copied from original Reservation");
			softAssert.assertEquals(getNewReservationUntilTime(),until, "until time is copied from original Reservation");
			softAssert.assertEquals(getGeneralSubject(),subject, "subject is copied from Reservation");
			softAssert.assertEquals(getGeneralRemarks(),remark, "remark is copied from original Reservation");
			softAssert.assertEquals(getResponsible(),responsible1, "responsible1 is copied from original Reservation");
			softAssert.assertEquals(getContact(),contact1, "contact is copied from original Reservation");
			softAssert.assertEquals(getDepartment(),department,"department is copied from original Reservation");
			softAssert.assertEquals(getProject(), project,"project is copied from original Reservation");
			String costCenterFromResponsible = getCostCenter();
			waitForExtJSAjaxComplete(20);

			softAssert.assertEquals(costCenterFromResponsible, costCenter,"Cost Center is copied from original Reservation");
			softAssert.assertEquals(getFinKey3(),finKey3,"finkey 3 is copied from original Reservation");
			softAssert.assertEquals(getFinKey4(),finKey4,"finkey 4 is copied from original Reservation");

			clickOrderItemsTab();
			waitForExtJSAjaxComplete(20);

			expandOrderItem(rooms[1]);
			waitForExtJSAjaxComplete(20);

			softAssert.assertTrue(orderItemExist(rooms[1]), "order Item Room1 Exist ");
			softAssert.assertFalse(orderItemExist(equipment),"order Item equipment(2aqaEqRef) doesn't Exist ");
			softAssert.assertFalse(orderItemExist(rooms[0]),"order Item room(3preRmRef) doesn't Exist ");
			waitForExtJSAjaxComplete(20);
			waitForExtJSAjaxComplete(20);

			clickConfirmReservation();
			waitForExtJSAjaxComplete(20);

			softAssert.assertTrue(isReservationDone(), "Reservation is confirmed and Reservation Data is completely copied to the New Reservation and No additional Order Items are copied");
			waitForExtJSAjaxComplete(20);
		}

		finally{
			if(isReservation = true){
				navigateToMainPage();
				waitForExtJSAjaxComplete(25);
				waitForMaskDisappear();
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

				myReserv.setTodayDateInMyReservation(date);
				myReserv.clickSearchButton();
				waitForExtJSAjaxComplete(25);
				from = "04:30";

				for(String room : rooms){

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

			} else {
				System.err.println("Reservation not done");
			}
		}

		softAssert.assertAll();
		Reporter.log("Copy existing Reservation of Room with Equipment and Services via “Calendar” (MYM-24564)", true);
	}

}
