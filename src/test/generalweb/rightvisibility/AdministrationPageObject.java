package test.generalweb.rightvisibility;

import java.util.List;
import java.util.concurrent.TimeUnit;

import test.configuration.Configuration;
import test.framework.AbstractMcsTestSuite;

import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import org.testng.Reporter;


public class AdministrationPageObject extends AbstractMcsTestSuite {
	
	
	protected final String NAME_FOR_RIGHT = "selenright";
	
	protected final String PASSWORD_FOR_RIGHT = "qwertyu";
	
	protected final String ADMINISTRATION_BUTTON_XPATH = "//button[contains(@class,'icon-gear') and contains(text(),'Administration')]";
	
	
	
	
	
	protected final String PARAMETER_REGION_XPATH = "//input[contains(@name,'region')]/..//input[contains(@class,'x-form-field')]";
	
	protected final String PARAMETER_DATE_XPATH = "//input[contains(@name,'searchDate')]";
	
	protected final String PARAMETER_FROM_XPATH = "//input[contains(@name,'from')]";
	
	protected final String PARAMETER_UNTIL_XPATH = "//input[contains(@name,'until')]";
	
	protected final String PARAMETER_ROOMCAPACITY_XPATH = "//input[contains(@name,'roomCapacity')]";
	
	protected final String PARAMETER_SEARCH_XPATH = "//button//*[text()='Search']";
	
	protected final String LAUNCH_RESERVATION_XPATH = "//button[contains(@onclick,'launchReservation')]";
	
	protected final String CONFIRM_RESERVATION_XPATH = "//button[contains(text(),'Confirm Reservation')]";
	
	protected final String MYRESERVATIONSTAB_XPATH = "//a[text()='My Reservations']";
	
	protected final String NEWRESERVATIONSTAB_XPATH = "//a[text()='New Reservation']";
	
	protected final String VIEW_CANCEL_XPATH = "//div[contains(@class,'action')and contains(text(),'View or Cancel')]";
	
	protected final String CANCELRESERVATION_XPATH = "//button[contains(text(),'Cancel this reservation')]";
	
	protected final String NEWRESERVATION_XPATH = "//a[text()='New Reservation']";
	
	protected final String MYRESERVATION_XPATH = "//a[text()='My Reservations']";
	
	protected final String PARAMETER_ROOM_XPATH = "//span[contains(@class,'icon-reservable-room')]";
	
	protected final String PARAMETER_PARKING_XPATH = "//span[contains(@class,'icon-reservable-parking')]";
	
	protected final String PARAMETER_VEHICLE_XPATH = "//span[contains(@class,'icon-reservable-vehicle')]";
	
	protected final String PARAMETER_EQUIPMENT_XPATH = "//span[contains(@class,'icon-reservable-equipment')]";
	
	protected final String PARAMETER_SERVICE_XPATH = "//span[contains(@class,'icon-reservable-service')]";
	
	protected final String PARAMETER_PARKINGCATEGORY_XPATH = "//input[contains(@name,'parkingCategory')]/..//input[contains(@class,'x-form-field')]";
	
	protected final String PARAMETER_ROOMCATEGORY_XPATH = "//input[contains(@name,'roomCategory')]/..//input[contains(@class,'x-form-field')]";
	
	protected final String PARAMETER_ROOMPURPOSE_XPATH = "//input[contains(@name,'roomPurpose')]/..//input[contains(@class,'x-form-field')]";
	
	protected final String PARAMETER_ROOMLAYOUT_XPATH = "//input[contains(@name,'roomLayout')]/..//input[contains(@class,'x-form-field')]";
	
	protected final String PARAMETER_VEHICLECATEGORY_XPATH = "//input[contains(@name,'vehicleCategory')]/..//input[contains(@class,'x-form-field')]";
	
	protected final String PARAMETER_EQUIPMENTCATEGORY_XPATH = "//input[contains(@name,'equipmentCategory')]/..//input[contains(@class,'x-form-field')]";
	
	protected final String PARAMETER_SERVICECATEGORY_XPATH = "//input[contains(@name,'serviceCategory')]/..//input[contains(@class,'x-form-field')]";
	
	protected final String PARAMETER_SERVICENAME_XPATH = "//input[contains(@name,'serviceName')]/..//input[contains(@class,'x-form-field')]";
	
	protected final String CLICKTOORDER_XPATH = "//button[@qtip='Click to order']";
	
	

	public void navigateToMainPageOnly() {
		waitForExtJSAjaxComplete(25);
		driver.get(configuration.getApplicationUrl()+"?aqa");
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Navigate to main page\n", true);
		Reporter.log("<br />");
	}
	
	public void expandModuleSettings() {
		expandNode("div","@id",getXPanelId("Administration"),"Module Settings");
		}
	
	public void expandReservations() {
		expandSubNode("div","@id",getXPanelId("Administration"),"Reservation");
		}
	
	/**
	 * Helper method to expand inspections
	 */
	public void expandInspection() {
		expandSubNode("div","@id",getXPanelId("Administration"),"Inspection");
		}
	/**
	 * Helper method to expand Workorders
	 */
	public void expandWorkorders() {
		expandSubNode("div","@id",getXPanelId("Administration"),"Workorders");
		}
	
	
	/**
	 * Helper method to click on workorder templates
	 */
	
	public void clickWorkorderTemplates(){
		McsElement.getElementByXpath(driver, "//span[contains(text(),'Workorders')]/../../..//span[contains(text(),'Work Order Templates')]").click();
		Reporter.log("general reservation settings was clicked <br>", true);
	}	
	
	/**
	 * Helper method to click Inspection templates 
	 */
	public void clickInspectionTemplates(){
		McsElement.getElementByXpath(driver, "//span[contains(text(),'Inspection')]/../../..//span[contains(text(),'Inspection Templates')]").click();
		Reporter.log("general Inspection templates was clicked <br>", true);
	}	
	
	public void clickReservationsGenSetings(){
		McsElement.getElementByXpath(driver, "//span[contains(text(),'Reservation')]/../../..//span[contains(text(),'General Settings')]").click();
		Reporter.log("general reservation settings was clicked <br>", true);
	}	
	
	
	public void setWebAccountGroup(String group){
		String id = McsElement.getElementByXpath(driver, "//label[contains(text(),'Web Account Group')]/..//input").getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, group);
		Reporter.log("web group was selected <br>", true);
	}	
	
		
	public void clickOnCheckInRow(String fieldName,String tableName,String colNum){
		
		String xpath = "//label[text()='"
				+ tableName
				+ "']//ancestor::div[contains(@class,'x-panel-tbar')]/..//label[text()='"
				+ fieldName
				+ "']/ancestor::tr//td[contains(@class,'x-grid3-cell x-grid3-td-"
				+ colNum + "')]//input[@type='checkbox']";
		try {
			WebElement checkBoxStatus = McsElement.getElementByXpath(
					driver, xpath);

			waitForExtJSAjaxComplete(20);

			if (!checkBoxStatus.isSelected()) {
				checkBoxStatus.click();

			}

			Reporter.log("Row " + fieldName + " is checked", true);

		} catch (Exception e) {
			Reporter.log("Unable to check " + fieldName
					+ "status of row of grid ", true);
		}
		Reporter.log("Click on row checkbox was done <br>", true);
	}
	
	public void clickOnUnCheckInRow(String fieldName,String tableName,String colNum){
		
		String xpath = "//label[text()='"
				+ tableName
				+ "']//ancestor::div[contains(@class,'x-panel-tbar')]/..//label[text()='"
				+ fieldName
				+ "']/ancestor::tr//td[contains(@class,'x-grid3-cell x-grid3-td-"
				+ colNum + "')]//input[@type='checkbox']";
		try {
			WebElement checkBoxStatus = McsElement.getElementByXpath(
					driver, xpath);

			waitForExtJSAjaxComplete(20);

			if (checkBoxStatus.isSelected()) {
				checkBoxStatus.click();

			}

			Reporter.log("Row " + fieldName + " is unchecked", true);

		} catch (Exception e) {
			Reporter.log("Unable to Uncheck " + fieldName
					+ "status of row of grid ", true);
		}
		Reporter.log("UnClick on row checkbox was done <br>", true);
	}	
	
	public void saveChanges(){
		McsElement.getElementByXpath(driver, "//div[contains(@class,'admsettings')]//button[contains(text(),'Save Changes')]").click();
		clickOnDialogButton("Yes");
		Reporter.log("Save changes was clicked <br>", true);
	}
	
	
	public void clickAdministration(){
		McsElement.getElementByXpath(driver, ADMINISTRATION_BUTTON_XPATH).click();
		Reporter.log("Administration was clicked <br>", true);
	}
	
	public void clickRoomTab(){
		McsElement.getElementByXpath(driver, PARAMETER_ROOM_XPATH).click();
		Reporter.log("Room tab was clicked <br>", true);
	}	
		

	public void clickToOrderAdditional(){
		McsElement.getElementByXpath(driver, CLICKTOORDER_XPATH).click();
		Reporter.log("Additional reservation was clicked <br>", true);
	}	

	
	
	public void clickParkingTab(){
		McsElement.getElementByXpath(driver, PARAMETER_PARKING_XPATH).click();
		Reporter.log("Parking tab was clicked <br>", true);
	}	
	
	public void clickVehicleTab(){
		McsElement.getElementByXpath(driver, PARAMETER_VEHICLE_XPATH).click();
		Reporter.log("Vehicle tab was clicked <br>", true);
	}	
	
	public void clickEquipmentTab(){
		McsElement.getElementByXpath(driver, PARAMETER_EQUIPMENT_XPATH).click();
		Reporter.log("Equipment tab was clicked <br>", true);
	}	

	
	public void clickServiceTab(){
		McsElement.getElementByXpath(driver, PARAMETER_SERVICE_XPATH).click();
		Reporter.log("Service tab was clicked <br>", true);
	}	
	
	
	public void setRegion(String region){
		McsElement.getElementByXpath(driver, PARAMETER_REGION_XPATH).click();
		McsElement.getElementByXpath(driver, PARAMETER_REGION_XPATH).clear();
		McsElement.getElementByXpath(driver, PARAMETER_REGION_XPATH).sendKeys(region);
		McsElement.getElementByXpath(driver, PARAMETER_REGION_XPATH).click();
	}


	public void setParkingCategory(String region){
		McsElement.getElementByXpath(driver, PARAMETER_PARKINGCATEGORY_XPATH).click();
		McsElement.getElementByXpath(driver, PARAMETER_PARKINGCATEGORY_XPATH).clear();
		McsElement.getElementByXpath(driver, PARAMETER_PARKINGCATEGORY_XPATH).sendKeys(region);
		McsElement.getElementByXpath(driver, PARAMETER_PARKINGCATEGORY_XPATH).click();
	}

	public void setRoomCategory(String room){
		McsElement.getElementByXpath(driver, PARAMETER_ROOMCATEGORY_XPATH).click();
		McsElement.getElementByXpath(driver, PARAMETER_ROOMCATEGORY_XPATH).clear();
		McsElement.getElementByXpath(driver, PARAMETER_ROOMCATEGORY_XPATH).sendKeys(room);
		McsElement.getElementByXpath(driver, PARAMETER_ROOMCATEGORY_XPATH).click();
	}

	public void setRoomPurpose(String purpose){
		McsElement.getElementByXpath(driver, PARAMETER_ROOMPURPOSE_XPATH).click();
		McsElement.getElementByXpath(driver, PARAMETER_ROOMPURPOSE_XPATH).clear();
		McsElement.getElementByXpath(driver, PARAMETER_ROOMPURPOSE_XPATH).sendKeys(purpose);
		McsElement.getElementByXpath(driver, PARAMETER_ROOMPURPOSE_XPATH).click();
	}
	

	public void setRoomLayout(String purpose){
		McsElement.getElementByXpath(driver, PARAMETER_ROOMLAYOUT_XPATH).click();
		McsElement.getElementByXpath(driver, PARAMETER_ROOMLAYOUT_XPATH).clear();
		McsElement.getElementByXpath(driver, PARAMETER_ROOMLAYOUT_XPATH).sendKeys(purpose);
		McsElement.getElementByXpath(driver, PARAMETER_ROOMLAYOUT_XPATH).click();
	}
	
	
	public void setVehicleCategory(String region){
		McsElement.getElementByXpath(driver, PARAMETER_VEHICLECATEGORY_XPATH).click();
		McsElement.getElementByXpath(driver, PARAMETER_VEHICLECATEGORY_XPATH).clear();
		McsElement.getElementByXpath(driver, PARAMETER_VEHICLECATEGORY_XPATH).sendKeys(region);
		McsElement.getElementByXpath(driver, PARAMETER_VEHICLECATEGORY_XPATH).click();
	}

	
	public WebElement checkRowInGriByTextValueAndClick(String textValue, String textValue2)  {
		WebElement webElement = driver.findElement(By.xpath("//*[@class='x-grid3']//div[text()='"+textValue+"']/../..//div[text()='"+textValue2+"']"));
		webElement.click();
		Reporter.log("Check element "+textValue+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	
	public boolean isRowInGridAbsent(String textValue, String text2) {
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//*[@class='x-grid3']//div[text()='"+textValue+"']/../..//div[text()='"+text2+"']"));
			return false;
		} catch (Exception e) {
			return true;
		} finally {
			try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
		}
		
	}
	

	public void setEquipmentCategory(String region){
		McsElement.getElementByXpath(driver, PARAMETER_EQUIPMENTCATEGORY_XPATH).click();
		McsElement.getElementByXpath(driver, PARAMETER_EQUIPMENTCATEGORY_XPATH).clear();
		McsElement.getElementByXpath(driver, PARAMETER_EQUIPMENTCATEGORY_XPATH).sendKeys(region);
		McsElement.getElementByXpath(driver, PARAMETER_EQUIPMENTCATEGORY_XPATH).click();
	}

	public void setServiceCategory(String region){
		McsElement.getElementByXpath(driver, PARAMETER_SERVICECATEGORY_XPATH).click();
		McsElement.getElementByXpath(driver, PARAMETER_SERVICECATEGORY_XPATH).clear();
		McsElement.getElementByXpath(driver, PARAMETER_SERVICECATEGORY_XPATH).sendKeys(region);
		McsElement.getElementByXpath(driver, PARAMETER_SERVICECATEGORY_XPATH).click();
	}

	public void setServiceName(String region){
		McsElement.getElementByXpath(driver, PARAMETER_SERVICENAME_XPATH).click();
		McsElement.getElementByXpath(driver, PARAMETER_SERVICENAME_XPATH).clear();
		McsElement.getElementByXpath(driver, PARAMETER_SERVICENAME_XPATH).sendKeys(region);
		McsElement.getElementByXpath(driver, PARAMETER_SERVICENAME_XPATH).click();
	}
	
	
	
	public void setDate(String date){
		McsElement.getElementByXpath(driver, PARAMETER_DATE_XPATH).click();
		McsElement.getElementByXpath(driver, PARAMETER_DATE_XPATH).clear();
		McsElement.getElementByXpath(driver, PARAMETER_DATE_XPATH).sendKeys(date);
		McsElement.getElementByXpath(driver, PARAMETER_DATE_XPATH).click();
	}
	

	public void setTimeFrom(String date){
		McsElement.getElementByXpath(driver, PARAMETER_FROM_XPATH).click();
		McsElement.getElementByXpath(driver, PARAMETER_FROM_XPATH).clear();
		McsElement.getElementByXpath(driver, PARAMETER_FROM_XPATH).sendKeys(date);
		McsElement.getElementByXpath(driver, PARAMETER_FROM_XPATH).click();
	}

	public void setTimeUntil(String date){
		McsElement.getElementByXpath(driver, PARAMETER_UNTIL_XPATH).click();
		McsElement.getElementByXpath(driver, PARAMETER_UNTIL_XPATH).clear();
		McsElement.getElementByXpath(driver, PARAMETER_UNTIL_XPATH).sendKeys(date);
		McsElement.getElementByXpath(driver, PARAMETER_UNTIL_XPATH).click();
	}
	
	public void setNrPeople(String date){
		McsElement.getElementByXpath(driver, PARAMETER_ROOMCAPACITY_XPATH).click();
		McsElement.getElementByXpath(driver, PARAMETER_ROOMCAPACITY_XPATH).clear();
		McsElement.getElementByXpath(driver, PARAMETER_ROOMCAPACITY_XPATH).sendKeys(date);
		McsElement.getElementByXpath(driver, PARAMETER_ROOMCAPACITY_XPATH).click();
	}
	

	public void clickSearch(){
		McsElement.getElementByXpath(driver, PARAMETER_SEARCH_XPATH).click();
		Reporter.log("Search button was clicked <br>", true);
	}
	
	public void clickLaunchReservation(String room){
		McsElement.getElementByXpath(driver, "//span[contains(text(),'"+room+"')]/../../.."+LAUNCH_RESERVATION_XPATH).click();
		Reporter.log("Launch reservation button was clicked <br>", true);
	}

	
	public void setResponsible(String customer) {
		
			if (driver.findElement(	By.xpath("//div[contains(@class,'mcsreservationedit')]//span[text()='General']/../..")).getAttribute("class").contains("collapsed")) {
				driver.findElement(	By.xpath("//div[contains(@class,'mcsreservationedit')]//span[text()='General']/../div")).click();}

			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);
			
		clickLookup("@class","x-panel", "responsible","Select a Person");
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Person"), customer, "Last Name");
		
	}

	
	public void clickConfirmReservation(){
		McsElement.getElementByXpath(driver, CONFIRM_RESERVATION_XPATH).click();
		Reporter.log("Confirm reservation was clicked <br>", true);
	}

	
	public void clickMyReservationsTab(){
		McsElement.getElementByXpath(driver, MYRESERVATIONSTAB_XPATH).click();
		Reporter.log("Click on my reservations <br>", true);
	}
	
	public void clickViewCancel(){
		McsElement.getElementByXpath(driver, VIEW_CANCEL_XPATH).click();
		Reporter.log("Click view or cancel <br>", true);
	}
	
	public void clickCancelReservation(){
		McsElement.getElementByXpath(driver, CANCELRESERVATION_XPATH).click();
		Reporter.log("Click cancel reservation <br>", true);
	}

	
	public void clickOnCheckInRowToSetVisibility(List<String> fieldNames){
		for(String fieldName: fieldNames){
		WebElement checkBoxStatus = McsElement.getElementByXpath(driver, "//label[contains(text(),'"+fieldName+"')]/../../../..//input");
		try{
			if(!checkBoxStatus.isSelected()){
				McsElement.getElementByXpath(driver, "//label[contains(text(),'"+fieldName+"')]/../../../..//input").click();
					
			}
			else{
				Reporter.log("Row "+fieldName+" is already checked",true);
			
		}
		}
		catch(Exception E){
			Reporter.log("Unable to check "+fieldName+"status of row of grid ",true);
						}
		}

	}
}
