package test.generalweb.reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import test.configuration.Configuration;
import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class ReservationPageObject extends AbstractMcsTestSuite {
	
	protected final String PARAMETER_REGION_XPATH = "//input[contains(@name,'region')]/..//input[contains(@class,'x-form-field')]";
	
	protected final String PARAMETER_DATE_XPATH = "//input[contains(@name,'searchDate')]";
	
	protected final String PARAMETER_FROM_XPATH = "//input[contains(@name,'from')]";
	
	protected final String PARAMETER_FROM_RESERVATIONPANEL_XPATH = "//input[contains(@name,'from')]//..//td[contains(@class,'ux-datetime-time')]//input";
	
	protected final String PARAMETER_UNTIL_RESERVATIONPANEL_XPATH = "//input[contains(@name,'until')]//..//td[contains(@class,'ux-datetime-time')]//input";

	protected final String PARAMETER_FROMDATE_RESERVATIONPANEL_XPATH = "//input[contains(@name,'from')]//..//td[contains(@class,'ux-datetime-date')]//input";
	
	protected final String PARAMETER_UNTIL_XPATH = "//input[contains(@name,'until')]";
	
	protected final String PARAMETER_ROOMCAPACITY_XPATH = "//input[contains(@name,'roomCapacity')]";
	
	protected final String PARAMETER_SEARCH_XPATH = "//button//*[text()='Search']";
	
	protected final String LAUNCH_RESERVATION_XPATH = "//button[contains(@onclick,'launchReservation')]";
	
	protected final String CONFIRM_RESERVATION_XPATH = "//button[contains(@style,'icon-confirm-large.png')]";
	
	protected final String MYRESERVATIONSTAB_XPATH = "//a[text()='My Reservations']";
	
	protected final String NEWRESERVATIONSTAB_XPATH = "//a[text()='New Reservation']";
	
	protected final String CALENDARTAB_XPATH = "//a[text()='Calendar']";
	
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
	
	protected final String CLOSERESERVATION_XPATH = "//button[contains(text(),'Close')]";
	
	protected final String NEW_RESERVATION_DETAILS_XPATH = "//div[contains(@class,'mcsreservationedit')]";
	
	protected final String NEW_RESERVATION_PARAMETERS_FORM_XPATH = "//div[contains(@class,'mcs-form-large-labels')]";
	
	protected final String PARAMETER_RECURRENCE_START_DATE_XPATH = "//div[contains(@class,'x-resizable') and contains(@style,'visible')]//input[contains(@name,'startDate')]";
	
	protected final String COPY_RESERVATION_XPATH = "//button[ contains(@class,'x-btn-text') and text()='Copy']//ancestor::td[contains(@class,'x-toolbar-cell') and not(contains(@class,'x-hide-display'))]";
	
	protected final String RECURRENCE_WIN_XPATH = "//div[contains(@class,'reservations') and contains(@class,'x-resizable-pinned') and contains(@style,'visibility: visible')]";

	protected final String RECURRENCE_END_DATE_XPATH = "//input[contains(@name,'endDate')]";

	
	public void clickRoomTab(){
		McsElement.getElementByXpath(driver, PARAMETER_ROOM_XPATH).click();
		Reporter.log("Room tab was clicked <br>", true);
	}	
		

	public void clickToOrderAdditional(){
		McsElement.getElementByXpath(driver, CLICKTOORDER_XPATH).click();
		Reporter.log("Additional reservation was clicked <br>", true);
	}	

	
	public void clickCalenderTab(){
		McsElement.getElementByXpath(driver, CALENDARTAB_XPATH).click();
		Reporter.log("Calendar was clicked <br>", true);
	}	
	
	public void clickParkingTab() {
		WebElement ele = driver.findElement(By.xpath(PARAMETER_PARKING_XPATH));
		waitForExtJSAjaxComplete(10);
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch (Exception e) {
			e.printStackTrace();
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
		Reporter.log("Parking tab was clicked <br>", true);
	}

	public void clickVehicleTab() {

		WebElement ele = driver.findElement(By.xpath(PARAMETER_VEHICLE_XPATH));
		waitForExtJSAjaxComplete(10);
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch (Exception e) {
			e.printStackTrace();
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
		Reporter.log("Vehicle tab was clicked <br>", true);
	}

	public void clickEquipmentTab() {
		WebElement ele = driver
				.findElement(By.xpath(PARAMETER_EQUIPMENT_XPATH));
		waitForExtJSAjaxComplete(10);
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch (Exception e) {
			e.printStackTrace();
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
		Reporter.log("Equipment tab was clicked <br>", true);
	}	

	public void clickServiceTab() {
		WebElement ele = driver.findElement(By.xpath(PARAMETER_SERVICE_XPATH));
		waitForExtJSAjaxComplete(10);
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch (Exception e) {
			e.printStackTrace();
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
		Reporter.log("Service tab was clicked <br>", true);
	}	
	
	
	public void setRegion(String region,String columnName) {

		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//div[contains(@class,'x-panel')]//input[contains(@name,'region')]//..//..//button")));

		clickLookup("x-panel", "region");

		waitForExtJSAjaxComplete(25);

		setValueGridLookupWithFiltersWithScrollInToView("@class", "x-resizable", region,columnName);

		waitForExtJSAjaxComplete(25);

		/*
		  McsElement.getElementByXpath(driver, PARAMETER_REGION_XPATH).click();
		  McsElement.getElementByXpath(driver, PARAMETER_REGION_XPATH).clear();
		  McsElement.getElementByXpath(driver,
		  PARAMETER_REGION_XPATH).sendKeys(region);
		  McsElement.getElementByXpath(driver, PARAMETER_REGION_XPATH).click();
		 */
	}
	
	public void setRegion(String region) {

		setRegion(region, "Reference");

		/*	WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//div[contains(@class,'x-panel')]//input[contains(@name,'region')]//..//..//button")));

		clickLookup("x-panel", "region");

		waitForExtJSAjaxComplete(25);

		setValueGridLookupWithFilters("@class", "x-resizable", region,"Reference");

		  McsElement.getElementByXpath(driver, PARAMETER_REGION_XPATH).click();
		  McsElement.getElementByXpath(driver, PARAMETER_REGION_XPATH).clear();
		  McsElement.getElementByXpath(driver,
		  PARAMETER_REGION_XPATH).sendKeys(region);
		  McsElement.getElementByXpath(driver, PARAMETER_REGION_XPATH).click();
		 */
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
		McsElement.getElementByXpath(driver, PARAMETER_ROOMCATEGORY_XPATH).sendKeys(Keys.ENTER);
		McsElement.getElementByXpath(driver, PARAMETER_ROOMCATEGORY_XPATH).click();
	}

	public void setRoomPurpose(String purpose){
		McsElement.getElementByXpath(driver, PARAMETER_ROOMPURPOSE_XPATH).click();
		McsElement.getElementByXpath(driver, PARAMETER_ROOMPURPOSE_XPATH).clear();
		McsElement.getElementByXpath(driver, PARAMETER_ROOMPURPOSE_XPATH).sendKeys(purpose);
		McsElement.getElementByXpath(driver, PARAMETER_ROOMCATEGORY_XPATH).sendKeys(Keys.ENTER);
		McsElement.getElementByXpath(driver, PARAMETER_ROOMPURPOSE_XPATH).click();
	}
	

	public void setRoomLayout(String purpose){
		McsElement.getElementByXpath(driver, PARAMETER_ROOMLAYOUT_XPATH).click();
		McsElement.getElementByXpath(driver, PARAMETER_ROOMLAYOUT_XPATH).clear();
		McsElement.getElementByXpath(driver, PARAMETER_ROOMLAYOUT_XPATH).sendKeys(purpose);
		McsElement.getElementByXpath(driver, PARAMETER_ROOMLAYOUT_XPATH).sendKeys(Keys.ENTER);
		McsElement.getElementByXpath(driver, PARAMETER_ROOMLAYOUT_XPATH).click();
	}
	
	
	public void setVehicleCategory(String region){
		McsElement.getElementByXpath(driver, PARAMETER_VEHICLECATEGORY_XPATH).click();
		McsElement.getElementByXpath(driver, PARAMETER_VEHICLECATEGORY_XPATH).clear();
		McsElement.getElementByXpath(driver, PARAMETER_VEHICLECATEGORY_XPATH).sendKeys(region);
		McsElement.getElementByXpath(driver, PARAMETER_VEHICLECATEGORY_XPATH).click();
	}


	public WebElement checkRowInGriByTextValueAndClick(String textValue2)  {
		WebElement webElement = driver.findElement(By.xpath("//div[contains(@class,'grid3')]//tr//div[contains(@class, 'sch-event-myreservation')]//ancestor::tr//span[text()='"+textValue2+"']"));
		javaScriptFocus(webElement);
		webElement.click();
		Reporter.log("Check element "+textValue2+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	
	
	public WebElement checkRowInGriByTextValueAndClick(String textValue, String textValue2)  {
		WebElement webElement = driver.findElement(By.xpath("//*[@class='x-grid3']//div[text()='"+textValue+"']/../..//*[contains(@class,'x-grid3-col-Resource') and contains(text(),'"+textValue2+"')]"));
		javaScriptFocus(webElement);
		webElement.click();
		Reporter.log("Check element "+textValue+textValue2+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	/**
	 * XPath modified
	 * @return
	 */
	public static boolean isRowInGridPresent(WebDriver webDriver, String textValue, String parentAttrValue, String parentAttr) {
		try {
			webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			webDriver.findElement(By.xpath("//div[contains("+parentAttr+",'"+parentAttrValue+"')]//tr//span[text()='"+textValue+"']"));
			return false;
		} catch (Exception e) {
			return true;
		} finally {
			try {webDriver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
		}
		
	}
	
	public boolean isRowInGridAbsent(String textValue, String text2) {
		try {
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
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


	public void setTimeFromReservationPanel(String date){
		String id= driver.findElement(By.xpath(PARAMETER_FROM_RESERVATIONPANEL_XPATH)).getAttribute("id");
		DropDown.setComboValueDropDownSelector(driver,id,date);
		waitForExtJSAjaxComplete(20);
	}
	public void setTimeFromReservationPanel1(String date){
		WebElement from= driver.findElement(By.xpath(PARAMETER_FROM_RESERVATIONPANEL_XPATH));
		from.click();
		waitForExtJSAjaxComplete(10);
		from.clear();
		waitForExtJSAjaxComplete(10);
		from.sendKeys(date);
		waitForExtJSAjaxComplete(10);
		from.click();
		waitForExtJSAjaxComplete(10);
	
	}

	public void setTimePrepareFromReservationPanel(String date){
		String id= McsElement.getElementByXpath(driver, "//div[contains(@class,'mcsreservationedit')]//input[@name='prepare']").getAttribute("id");
		DropDown.setComboValueDropDownSelector(driver,id,date);
		waitForExtJSAjaxComplete(10);
		/*WebElement prepare = McsElement.getElementByXpath(driver, "//div[contains(@class,'mcsreservationedit')]//input[@name='prepare']");
		prepare.click();
		waitForExtJSAjaxComplete(10);
		prepare.clear();
		waitForExtJSAjaxComplete(10);
		prepare.sendKeys(date);
		waitForExtJSAjaxComplete(10);
		prepare.click();
		waitForExtJSAjaxComplete(20);*/
	}
	

	public void setTimeCleanupFromReservationPanel(String date){
		/*WebElement prepare = McsElement.getElementByXpath(driver, "//div[contains(@class,'mcsreservationedit')]//input[@name='cleanup']");
		prepare.click();
		waitForExtJSAjaxComplete(10);
		prepare.clear();
		waitForExtJSAjaxComplete(10);
		prepare.sendKeys(date);
		waitForExtJSAjaxComplete(10);
		prepare.click();*/
		String id= McsElement.getElementByXpath(driver, "//div[contains(@class,'mcsreservationedit')]//input[@name='cleanup']").getAttribute("id");
		DropDown.setComboValueDropDownSelector(driver,id,date);
		waitForExtJSAjaxComplete(10);
		
	}
	
	public void setDateFromReservationPanel(String date){
		McsElement.getElementByXpath(driver, PARAMETER_FROMDATE_RESERVATIONPANEL_XPATH).click();
		McsElement.getElementByXpath(driver, PARAMETER_FROMDATE_RESERVATIONPANEL_XPATH).clear();
		McsElement.getElementByXpath(driver, PARAMETER_FROMDATE_RESERVATIONPANEL_XPATH).sendKeys(date);
		McsElement.getElementByXpath(driver, PARAMETER_FROMDATE_RESERVATIONPANEL_XPATH).click();
		
		clickGeneralRemarks();
		
		clickGeneralRemarks();
		
		clickGeneralRemarks();
		
		
//		String id = McsElement.getElementByXpath(driver, PARAMETER_FROMDATE_RESERVATIONPANEL_XPATH).getAttribute("id");
//		((JavascriptExecutor) driver)
//		.executeScript("Ext.getCmp('"+id+"').value='"+date+"';	Ext.getCmp('"+id+"').initValue()");
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
	

	public void setServiceQuantity(String service, String quantity){
		String xpath = "//table[contains(@class,'rsrvsearchresults')]//span[contains(text(),'"+service+"')]/../../..//div[contains(@class,'quantity-input')]//input";
		WebElement ele = driver.findElement(By.xpath(xpath));
		ele.click();
		ele.clear();
		ele.sendKeys(quantity);
		ele.click();
		
	/*	McsElement.getElementByXpath(driver, "//table[contains(@class,'rsrvsearchresults')]//span[contains(text(),'"+service+"')]/../../..//div[contains(@class,'quantity-input')]//input").click();
		McsElement.getElementByXpath(driver, "//table[contains(@class,'rsrvsearchresults')]//span[contains(text(),'"+service+"')]/../../..//div[contains(@class,'quantity-input')]//input").clear();
		McsElement.getElementByXpath(driver, "//table[contains(@class,'rsrvsearchresults')]//span[contains(text(),'"+service+"')]/../../..//div[contains(@class,'quantity-input')]//input").sendKeys(quantity);
		McsElement.getElementByXpath(driver, "//table[contains(@class,'rsrvsearchresults')]//span[contains(text(),'"+service+"')]/../../..//div[contains(@class,'quantity-input')]//input").click();*/
		Reporter.log("Service quantity was set <br>", true);
	}


	public void clickSearch(){
		WebElement element=McsElement.getElementByXpath(driver, PARAMETER_SEARCH_XPATH);
				element.click();
				element.click();
		Reporter.log("Search button was clicked <br>", true);
	}
	
	public void clickLaunchReservation(String room){
		waitForExtJSAjaxComplete(15);
		
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'"+room+"')]/../../.."+LAUNCH_RESERVATION_XPATH));
		
		if ("chrome".equals(configuration.getBrowser())) {
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", element);
            } catch (Exception e) {
             e.printStackTrace();
            }
        }
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		waitForExtJSAjaxComplete(15);
		waitForExtJSAjaxComplete(15);
		Reporter.log("Launch reservation button was clicked <br>", true);
	}


	public List<String> getAvailableSorting(){
		McsElement.getElementByXpath(driver, "((//input[@name='orderRoomsBy'])[1]/..//img)[last()]").click();
		waitForExtJSAjaxComplete(3);
		List<WebElement> webElements = null;
		try{
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			webElements = driver.
				findElements(By.xpath("//div[contains(@class,'x-layer') and contains(@style,'visibility: visible')]//div[contains(@class,'x-combo-list-inner')]//div"));
		} finally {
			try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
		}
		List<String> sorting = new ArrayList<String>();
		for (WebElement el: webElements)
		{sorting.add(el.getText().trim());}
		return sorting;
	}
	

	public void setSorting(String sorting){
		DropDown.setExtJsComboValue(driver,driver.findElement(By.xpath("((//input[@name='orderRoomsBy'])[1]/..//input)[last()]")).getAttribute("id") ,sorting);
		waitForExtJSAjaxComplete(10);
		Reporter.log("sorting was set: "+sorting+ "<br>", true);
	}
	
	
	
	public void clickParticipantTab(){

		if (driver.findElement(	By.xpath("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Participants')]/../..")).getAttribute("class").contains("collapsed")) {
			driver.findElement(	By.xpath("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Participants')]")).click();}

		waitForExtJSAjaxComplete(20);
		
	}
	
	public boolean selectParticipant(String participant){
		
		try {
			driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Participants')]/../..//div[contains(text(),'"+participant+"')]")).click();
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
		}
		
	}

	public void clickOrderItemsTab(){

	String xpath=driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Order Items')]/../..")).getAttribute("class");
	System.out.println(xpath);
		if (xpath.contains("collapsed")) {
		javaScriptClick("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Order Items')]");}
		waitForExtJSAjaxComplete(20);
		
	}
	
	
	public void expandIncludedIn(String room){
		
		if (driver.findElement(	By.xpath("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Included in "+room+"')]/../..")).getAttribute("class").contains("collapsed")) {
			driver.findElement(	By.xpath("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Included in "+room+"')]/../..//img[contains(@class,'plus')]")).click();}

		waitForExtJSAjaxComplete(20);
		
	}

	public void clickGeneralTab(){
		
		if (driver.findElement(	By.xpath("//div[contains(@class,'mcsreservationedit')]//span[text()='General']/../..")).getAttribute("class").contains("collapsed")) {
			driver.findElement(	By.xpath("//div[contains(@class,'mcsreservationedit')]//span[text()='General']")).click();}
		
		waitForExtJSAjaxComplete(10);
	}
	
///////////////////////////////////RECURRENCE WINDOW METHODS////////////////////
	public void clickGeneralRecurence(){
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//button[contains(@class,'icon-recurrence')]")).click();
		waitForExtJSAjaxComplete(15);
	}
	

	public void clickRecurenceCalculateDates(){
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable') and contains(@style,'visible')]//button[contains(@class,'icon-view-day')]")).click();
		waitForExtJSAjaxComplete(15);
	}


	public void clickCheckAvailability(){
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable') and contains(@style,'visible')]//button[contains(@class,'icon-calendar-search')]")).click();
		waitForExtJSAjaxComplete(15);
	}
	
	public void clickSetRecurence(){
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable') and contains(@style,'visible')]//button[contains(@class,'icon-recurrence')]")).click();
		waitForExtJSAjaxComplete(15);
	}
	
	
	public void clickRecurenceEndAfter(){
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable') and contains(@style,'visible')]//input[@name='endMode' and @value='COUNT']")).click();
	}

	public void clickRecurenceDailyTab(){
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable') and contains(@style,'visible')]//span[contains(text(),'Daily')]")).click();
	}
	
	
	public void setRecurenceEndAfter(String after){
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable') and contains(@style,'visible')]//input[@name='endOccurrences']")).click();
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable') and contains(@style,'visible')]//input[@name='endOccurrences']")).clear();
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable') and contains(@style,'visible')]//input[@name='endOccurrences']")).sendKeys(after);
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable') and contains(@style,'visible')]//input[@name='endOccurrences']")).click();
	}
	

	public void clickRecurenceDailyEvery(){
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable') and contains(@style,'visible')]//input[@name='dailyMode' and @value='INTERVAL']")).click();
	}

	
	public void setRecurenceDailyInterval(String after){
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable') and contains(@style,'visible')]//input[@name='dailyInterval']")).clear();
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable') and contains(@style,'visible')]//input[@name='dailyInterval']")).sendKeys(after);
	}
	
	
	public void closeRecurrenceWindow(){
		McsElement.getElementByXpath(driver, "//div[contains(@class,'reservations')]//div[contains(@class,'x-tool-close')]").click();
		Reporter.log("close x-window <br>", true);
	}
	
	public void setRecurenceStartDate(String date){
		McsElement.getElementByXpath(driver, PARAMETER_RECURRENCE_START_DATE_XPATH).click();
		McsElement.getElementByXpath(driver, PARAMETER_RECURRENCE_START_DATE_XPATH).clear();
		McsElement.getElementByXpath(driver, PARAMETER_RECURRENCE_START_DATE_XPATH).sendKeys(date);
		McsElement.getElementByXpath(driver, PARAMETER_RECURRENCE_START_DATE_XPATH).click();
	}

	public List<String> getAllValuesFromCaluclatedDatesSection(String colName){

		String headerXpath = "//div[contains(@class,'x-grid-panel')]//div[@class='x-grid3-header']//div[contains(@class,'x-grid3-hd') and text()='"+colName+"']";

		WebElement columnEle = driver.findElement(By.xpath(headerXpath));

		String columnClass = columnEle.getAttribute("class");

		String colNum = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);

		List<String> values = new ArrayList<String>();


		String xpath = "//div[contains(@class,'x-grid-panel')]//div[@class='x-grid3-body']//div[contains(@class,'x-grid3-col-"+colNum+"')]"; 
		try{
			
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		List<WebElement> rowEles = driver.findElements(By.xpath(xpath));

		for(WebElement ele: rowEles ){

			values.add(ele.getText());

		}
		}
		finally{
	
		driver.manage()
		.timeouts()
		.implicitlyWait(configuration.getImplicitWait(),
				TimeUnit.SECONDS);
	
		}

		return values;

	}

	public List<String> getAllCaluclatedDatesOfRecurrence(){
		List<String> unprocessedCalcDates = getAllValuesFromCaluclatedDatesSection("Calculated");
		return processDatesOfRecurrenceReservation(unprocessedCalcDates);

	}

	public List<String> getAllActualDatesOfRecurrence(){

		List<String> unprocessedCalcDates = getAllValuesFromCaluclatedDatesSection("Actual");

		return processDatesOfRecurrenceReservation(unprocessedCalcDates);

	}

	private List<String> processDatesOfRecurrenceReservation(List<String> unprocessedCalcDates){

		List<String> processedDates = new ArrayList<String>();

		for(String dateToProcess: unprocessedCalcDates){

			processedDates.add(dateToProcess.substring(0, dateToProcess.indexOf("(")).trim());

		}

		return processedDates;

	}

/////////////////////////////////////////////////////////////////////////////////////////
	
	public void clickGeneralRemarks(){
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'General')]/../..//span[contains(text(),'Remarks')]")).click();
		waitForExtJSAjaxComplete(25);
	}
	

	public void setGeneralRemarks(String remark){
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//textarea[contains(@name,'remarks')]")).click();
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//textarea[contains(@name,'remarks')]")).clear();
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//textarea[contains(@name,'remarks')]")).sendKeys(remark);
		waitForExtJSAjaxComplete(5);
	}


	public String getGeneralRemarks(){
		return driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//textarea[contains(@name,'remarks')]")).getAttribute("value");
	}

	
	public void clickDetailOrderItem(){
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Details')]")).click();
		waitForExtJSAjaxComplete(30);
		
	}

	public void setQuantity(String quantity){
		WebElement element=driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//input[@name='serviceQuantity']"));
		element.click();
		element.clear();
		element.sendKeys(quantity);
		element.click();
	}
	
	public String getQuantity(){
		return driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//input[@name='serviceQuantity']")).getAttribute("value");
	}
	
	
	public void expandOrderItem(String orderItem){

		if (driver.findElement(	By.xpath("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'"+orderItem+"')]//span/../../..")).getAttribute("class").contains("collapsed")) {
			driver.findElement(	By.xpath("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'"+orderItem+"')]/../..//img[contains(@class,'minus')]")).click();}

		waitForExtJSAjaxComplete(25);
		
	}
	
	
	public void setEquipmentFromDetails(String equipment) {
		
		
	clickLookup("x-panel", "resource");
	
	waitForExtJSAjaxComplete(25);
	
	//driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//span[contains(text(),'Available')]")).click();
	
	setValueGridLookupWithFilters("@class", "x-resizable", equipment, "Reference");
	
}

	
	
	public void removeOrderItem(String item){
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'"+item+"')]/span")).click();
		waitForExtJSAjaxComplete(20);
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Add/Remove')]")).click();
		waitForExtJSAjaxComplete(20);
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//div[contains(@class,'add-remove')]//button[contains(text(),'Remove')]")).click();
		waitForExtJSAjaxComplete(10);
	}
	
	public boolean orderItemExist(String item){
		try {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'"+item+"')]")).click();
			return true;
		} catch(Exception e) {
			return false;
		} finally {
			try {
				driver.manage()
						.timeouts()
						.implicitlyWait(
								Configuration.getConfiguration(null)
										.getImplicitWait(), TimeUnit.SECONDS);
			} catch (Exception e) {
			}
		}

	}
	

	public void clickAddOrderItemEquipment(){
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Add/Remove')]")).click();
		waitForExtJSAjaxComplete(20);
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//div[contains(@class,'add-remove')]//button[contains(text(),'Add Equipment')]")).click();
		waitForExtJSAjaxComplete(20);
	}


	public boolean addOrderItemEquipment(String item){
		
		//driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//span[contains(text(),'Available')]")).click();
		
		return setValueGridLookupWithFiltersWithValidation("@class", "x-resizable", item, "Reference");
	}

	
	public void clickAddOrderItemService(){
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Add/Remove')]")).click();
		waitForExtJSAjaxComplete(20);
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//div[contains(@class,'add-remove')]//button[contains(text(),'Add Service')]")).click();
		waitForExtJSAjaxComplete(10);
	}
	

	public void clickAddOrderItemVehicle(){
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Add/Remove')]")).click();
		waitForExtJSAjaxComplete(20);
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//div[contains(@class,'add-remove')]//button[contains(text(),'Add Vehicle')]")).click();
		waitForExtJSAjaxComplete(10);
	}

	public void clickAddOrderItemParking(){
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Add/Remove')]")).click();
		waitForExtJSAjaxComplete(20);
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//div[contains(@class,'add-remove')]//button[contains(text(),'Add Parking')]")).click();
		waitForExtJSAjaxComplete(10);
	}
	
	public void setTotalParticipants(String number) {
	
		String xpath = NEW_RESERVATION_DETAILS_XPATH+"//label[text()='Total:']/../..//input[contains(@class,'x-form-num-field')]";

		WebElement textField = McsElement.getElementByXpath(driver, xpath);
		textField.clear();
		textField.click();
		textField.sendKeys(number);
		textField.sendKeys(Keys.ENTER);
		waitForExtJSAjaxComplete(5);
	
	}

	public String getTotalParticipants() {
		return driver.findElement(By.xpath(NEW_RESERVATION_DETAILS_XPATH+"//label[text()='Total:']/../..//input[contains(@class,'x-form-num-field')]")).getAttribute("value");
	}

	
	public void addParticipantEmployee(String employee){
		driver.findElement(By.xpath("//button[contains(@class,'icon-person-medium')]")).click();
		waitForExtJSAjaxComplete(20);
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//span[text()='Employees']")).click();
		waitForExtJSAjaxComplete(20);
		
		filterGrid("@realid", "employees", employee, "Last Name");
		
		String columnClass = McsElement
		.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@realid", "employees",
				"div","@class", "x-grid3-hd",
				"text()", "Last Name", true, true).getAttribute("class");
		
		String columnNumber = columnClass.substring(columnClass.length() - 1);
			
			McsElement
					.getElementByPartAttributeValueAndParentElement(driver,
							"div","@realid", "employees", "div",
							"@class", "x-grid3-cell-inner x-grid3-col-"+columnNumber,
							"text()", employee, true, true).click();
			
			waitForExtJSAjaxComplete(20);	
			McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//button[contains(@style,'add.png')]").click();
			waitForExtJSAjaxComplete(20);
			clickOkXwindow();
			Reporter.log(employee + " was selected", true);
	}
	

	
	public void addParticipantContact(String employee){
		driver.findElement(By.xpath("//button[contains(@class,'icon-person-medium')]")).click();
		waitForExtJSAjaxComplete(20);
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//span[text()='Contacts']")).click();
		waitForExtJSAjaxComplete(20);
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select People"), employee, "Last Name");
		
		//filterGrid("@realid", "contacts", employee, "Last Name");
		
		
		
		/*String columnClass = McsElement
		.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@realid", "contacts",
				"div","@class", "x-grid3-hd",
				"text()", "Last Name", true, true).getAttribute("class");
		
		String columnNumber = columnClass.substring(columnClass.length() - 1);
			
			McsElement
					.getElementByPartAttributeValueAndParentElement(driver,
							"div","@realid", "contacts", "div",
							"@class", "x-grid3-cell-inner x-grid3-col-"+columnNumber,
							"text()", employee, true, true).click();
			
			waitForExtJSAjaxComplete(25);	
			McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//button[contains(@style,'add.png')]").click();
			waitForExtJSAjaxComplete(25);
			clickOkXwindow();
			Reporter.log(employee + " was selected", true);*/
	}

	
	public void addOtherPerson(String first, String last){
		driver.findElement(By.xpath("//button[contains(@class,'icon-person-unknown-medium')]")).click();
		waitForExtJSAjaxComplete(20);
		javaScriptClick("//div[contains(@class,'mcsreservationedit')]//input[contains(@name,'firstName')]");
		waitForExtJSAjaxComplete(20);
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//input[contains(@name,'firstName')]")).clear();
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//input[contains(@name,'firstName')]")).sendKeys(first);
		
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//input[contains(@name,'lastName')]")).click();
		waitForExtJSAjaxComplete(20);
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//input[contains(@name,'lastName')]")).clear();
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//input[contains(@name,'lastName')]")).sendKeys(last);
		waitForExtJSAjaxComplete(20);
	}
	
	
	public void clickAnnounceCheckbox(){
		javaScriptClick("(//span[contains(text(),'Announce')]/../input[contains(@name,'checkbox')])[last()]");
	}

	
	
	public void clickRemoveParticipant(){
		waitForExtJSAjaxComplete(20);
		//clickXPath("//div[contains(@class,'mcsreservationedit')]//tbody[contains(@class,'x-btn-small')]//button[contains(text(),'Remove')]");
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//table[contains(@class, 'x-btn-noicon')]//button[contains(text(),'Remove')]")).click();
	}
	
	public void setResponsible(String customer) {
		
			if (driver.findElement(	By.xpath("//div[contains(@class,'mcsreservationedit')]//span[text()='General']/../..")).getAttribute("class").contains("collapsed")) {
				driver.findElement(	By.xpath("//div[contains(@class,'mcsreservationedit')]//span[text()='General']")).click();}

			waitForExtJSAjaxComplete(20);
			waitForExtJSAjaxComplete(20);
			
		clickLookup("@class","x-panel", "responsible","Select a Person");
		
		waitForExtJSAjaxComplete(20);
		
		setExactValueGridLookupWithFiltersWithValidation("@id", getXWindowId("Select a Person"), customer, "Last Name");
	
		
	}
	
	public void setContact(String customer) {
		
		if (driver.findElement(	By.xpath("//div[contains(@class,'mcsreservationedit')]//span[text()='General']/../..")).getAttribute("class").contains("collapsed")) {
			driver.findElement(	By.xpath("//div[contains(@class,'mcsreservationedit')]//span[text()='General']")).click();}

		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		
	clickLookup("@class","x-panel", "contact","Select a Person");
	
	waitForExtJSAjaxComplete(20);
	
	setExactValueGridLookupWithFiltersWithValidation("@id", getXWindowId("Select a Person"), customer, "Last Name");
	
}

	

	public void clickTentative(){
		waitFocusAndClick("//div[contains(@class,'mcsreservationedit')]//input[@name='tentative']");
	}


	public void clickConfidential(){
		
		WebElement element = driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//input[@name='confidential']"));
		
		if ("chrome".equals(configuration.getBrowser())) {
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", element);
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		waitForExtJSAjaxComplete(20);
	}

	
	
	public void setProject(String project) {
		
	WebElement element = driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//input[@name='project']//..//..//button"));	
	
	((JavascriptExecutor) driver).executeScript( "arguments[0].scrollIntoView(true);", element);
		
	clickLookup("mcsreservationedit", "project");
	
	waitForExtJSAjaxComplete(20);
	
	click("//div[contains(@class,'x-resizable')]//span[text()='Projects']");
	
	waitForExtJSAjaxComplete(20);
	
	setExactValueGridLookupWithFiltersWithValidation("@class", "x-resizable", project, "Project");
	
}
	

	public void setProjectPart(String projectPart) {
		
	clickLookup("mcsreservationedit", "project");
	
	waitForExtJSAjaxComplete(25);
	
	click("//div[contains(@class,'x-resizable')]//span[text()='Project Parts']");
	
	waitForExtJSAjaxComplete(25);
	
	setValueGridLookupWithFilters("@class", "x-resizable", projectPart, "Project Part");
	
}
	
	
	public String getProject() {
		
		WebElement element= driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//input[@name='project']/..//input[@type='text']"));
		
		 try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
        	System.out.println("Exception is G " +e);
        }
    
		String text=element.getAttribute("value");
		return text;
		
	}

	
	
	public String getDepartment() {
		
		WebElement element= driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//input[@name='department']/..//input[@type='text']"));
			
		 try {
             ((JavascriptExecutor) driver).executeScript(
                     "arguments[0].scrollIntoView(true);", element);
         } catch (Exception e) {
         	System.out.println("Exception is G " +e);
         }
     
		String text=element.getAttribute("value");
		return text;
	}
	
	
	public void clickConfirmReservation(){
		McsElement.getElementByXpath(driver, CONFIRM_RESERVATION_XPATH).click();
		Reporter.log("Confirm reservation was clicked <br>", true);
		waitForExtJSAjaxComplete(25);
	}

	
	public void clickMyReservationsTab(){
		McsElement.getElementByXpath(driver, MYRESERVATIONSTAB_XPATH).click();
		Reporter.log("Click on my reservations <br>", true);
	}
	
//TODO view or cancel need to be connected with resource in grid	
	public void clickViewCancel(){
//		McsElement.getElementByXpath(driver, VIEW_CANCEL_XPATH).click();
//		Reporter.log("Click view or cancel <br>", true);
	}
	
	
	/*public void setRegionMyReservation(String region){
		DropDown.setExtJsComboValue(driver, driver.findElement(By.xpath("//span[text()='Region']/../../../../..//input")).getAttribute("id"), region);
	}
	
	public void setRegionMyReservation1(String region){
		String xpath="//span[text()='Region']/../../../../..//input";
		McsElement.getElementByXpath(driver, xpath).clear();
		McsElement.getElementByXpath(driver, xpath).sendKeys(region);
		McsElement.getElementByXpath(driver, xpath).click();
	}
*/
	
	public void clickCancelReservation(){
		
		McsElement.getElementByXpath(driver, CANCELRESERVATION_XPATH).click();
		
		Reporter.log("Click cancel reservation <br>", true);
	}

	public void clickSummaryTab(){

		if (driver.findElement(	By.xpath("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Summary')]/../..")).getAttribute("class").contains("collapsed")) {
			McsElement.getElementByXpath(driver, "//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Summary')]").click();}

		waitForExtJSAjaxComplete(30);
		
	}
	
	public String getSummaryTitle() {
		return driver
				.findElement(
						By.xpath("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Summary')]")).getText();
	}
		


	public String getSummaryItemCost(String item) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Summary')]/../..//td/b[text()='"+item+"']/../..//td)[last()]")).getText();
	}

	
	
	public String getSummaryTotalCost() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Summary')]/../..//td/b[text()='Total']/../..//b)[last()]")).getText();
	}

	
	public boolean addOrderItem(String orderItem) {
		//driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//span[contains(text(),'Available')]")).click();
		
		return setValueGridLookupWithFiltersWithValidation("@class", "x-resizable", orderItem, "Reference");
	}
	
	/**
	 * Click tabs in New Reservation details window
	 * @param tabText : text of tab to click
	 */
	public void clickTabInReservationDetailsSection(String tabText){
		
		String xpath = NEW_RESERVATION_DETAILS_XPATH + "//span[contains(@class,'x-tab-strip-text') and text()='"+tabText+"']";
		
		McsElement.getElementByXpath(driver, xpath).click();
				
	}
	
	/**
	 * Helper method to get GL Account lookup values
	 * 
	 * @return GL Account lookup values
	 */
	public List<String> getGLAccountLookUpValues(){
		
		clickLookup("mcsreservationedit", "glAccount");
		
		waitForExtJSAjaxComplete(25);
		
		McsElement.getElementByXpath(driver, "//span[contains(@class,'x-tab-strip') and text()='List']").click();
		
		return getValuesFromGridLookup("@id",getXWindowId("Select a GL Account"), "Reference");
		
	}
	
	/**
	 * Helper method to get FinKey3 lookup values
	 * 
	 * @return FinKey3 lookup values
	 */
	public List<String> getFinKey3LookUpValues(){
		
		return getLookUpValues("finKey3", "Select a value","Value");
	}
	
	/**
	 * Helper method to get FinKey4 lookup values
	 * 
	 * @return FinKey4 lookup values
	 */
	public List<String> getFinKey4LookUpValues(){
		
		return getLookUpValues("finKey4", "Select a value","Value");
	}
	
	
	private List<String> getLookUpValues(String nameAttr , String windowTitle, String colName ){
		
		waitForExtJSAjaxComplete(25);
		
		clickLookup("mcsreservationedit", nameAttr);
		
		waitForExtJSAjaxComplete(25);
		
		return getValuesFromGridLookup("@id",getXWindowId(windowTitle), colName);
		
	}
	
	/**
	 * Helper method to return true/false based on reservation is done
	 * @return true/false based on whether reservation 
	 */
	public boolean isReservationDone(){
		
		String xpath = NEW_RESERVATION_DETAILS_XPATH + "//button[ contains(@class,'x-btn-text') and text()='Confirmed']";
		
		try{
			
			McsElement.getElementByXpath(driver, xpath);
			
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	
	/**
	 * Helper method to get Reservation ID
	 * @return Reservation ID
	 */
	public String getReserationID(){
		
		String xpath = NEW_RESERVATION_DETAILS_XPATH+"//div[@class='xtb-text info-label']";
		
		return McsElement.getElementByXpath(driver, xpath).getText();
	}
	
	/**
	 * Helper method to click cancel reservation using Javascript
	 */
	public void clickCancelReservationUsingJS(){
		
		WebElement cancelReservationButton = driver.findElement(By.xpath(CANCELRESERVATION_XPATH) );
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", cancelReservationButton);
		Reporter.log("Click cancel reservation <br>", true);
	}
	
	
	
	/**
	 * Helper method to select item in Order Items section
	 * @param itemType : type of Item
	 * @param itemName : name of item to select
	 */
	
	public void clickItemInOrderItemsPane(String itemType, String itemName){
		
		List<String> itemslist = new ArrayList<String>();
		
		itemslist.add("service");
		itemslist.add("parking");
		itemslist.add("vehicle");
		itemslist.add("equipment");
		itemslist.add("room");
		
		itemType = itemType.trim().toLowerCase();
		
		String iconClass = "";
		
		int index = itemslist.indexOf(itemType);
		
		switch(index){
		
		case 0:{
			iconClass ="icon-reservable-service";
			break;
		}
		case 1:{
			iconClass ="icon-reservable-parking";
			break;
		}
		case 2:{
			iconClass ="icon-reservable-vehicle";
			break;
		}
		
		case 3:{
			iconClass ="icon-reservable-equipment";
			break;
		}
		
		case 4:{
			iconClass ="icon-reservable-room";
			break;
		}
		
		
		}
		
		
		String xpath = NEW_RESERVATION_DETAILS_XPATH + "//img[contains(@class,'"+iconClass+"')]/..//span[text()='"+itemName+"']";
		
		driver.findElement(By.xpath(xpath)).click();
	}
	
	/**
	 * Helper method to get Info of an Order Item
	 * @return Info of an Order Item
	 */
	public String getInfoHTMLOfReservableItemFromOrderItemDetailsTab(){
		
		String xpath = NEW_RESERVATION_DETAILS_XPATH+"//span[@class='x-fieldset-header-text' and text()='Info']/ancestor::fieldset//div[contains(@class,'fieldset-body')]";
		
		return driver.findElement(By.xpath(xpath)).getAttribute("innerHTML");
		
	}
	
	
	/**
	 * Helper method to return tool tip of selected item in Order Items section
	 * @param itemType : type of Item
	 * @param itemName : name of item to select
	 */
	
	public String getToolTipOfOrderItemInOrderItemsPane(String itemType, String itemName){
		
		List<String> itemslist = new ArrayList<String>();
		
		itemslist.add("service");
		itemslist.add("parking");
		itemslist.add("vehicle");
		itemslist.add("equipment");
		itemslist.add("room");
		
		itemType = itemType.trim().toLowerCase();
		
		String iconClass = "";
		
		int index = itemslist.indexOf(itemType);
		
		switch(index){
		
		case 0:{
			iconClass ="icon-reservable-service";
			break;
		}
		case 1:{
			iconClass ="icon-reservable-parking";
			break;
		}
		case 2:{
			iconClass ="icon-reservable-vehicle";
			break;
		}
		
		case 3:{
			iconClass ="icon-reservable-equipment";
			break;
		}
		
		case 4:{
			iconClass ="icon-reservable-room";
			break;
		}
		
		
		}
		
		
		String xpath = NEW_RESERVATION_DETAILS_XPATH + "//img[contains(@class,'"+iconClass+"')]/..//span[text()='"+itemName+"']";
	
		String qtip = driver.findElement(By.xpath(xpath)).getAttribute("qtip");
		
		return qtip;
	}
	
	/**
	 * Helper method to return tool tip of selected item in Order Items section
	 * @param itemType : type of Item
	 * @param itemName : name of item to select
	 * @return tool tip without HTML tags
	 */
	public String getToolTipOfOrderItemInOrderItemsPaneWithOutHTMLTags(String itemType, String itemName){
		
		return removeHtmlTagsFromString(getToolTipOfOrderItemInOrderItemsPane( itemType,  itemName));
	}
	
	/**
	 * Helper method to remove HTML tags from String and return String with text in between
	 * @param textWithHTMLTags
	 * @return
	 */
	public String removeHtmlTagsFromString(String textWithHTMLTags){
		
        return textWithHTMLTags.replaceAll("\\<.*?\\>", "");
	}
	
	
	public String getPropertyFromSring(String content , String property){
		
		int startIndex = content.indexOf(property); 
		startIndex+=property.length();
		
		int endIndex = content.indexOf("\n", startIndex);
		
		if(endIndex==-1){
			
			endIndex = content.length();
		}
		
		return content.substring(startIndex, endIndex).trim();
	}
	
	/**
	 * Helper method to verify if Image tag is present
	 * @param content
	 * @return true/false based on img tag presence 
	 */
	public boolean isImageTagPresentInString(String content){
		
		return	content.contains("<img");
		
	}
	
	/**
	 * Helper method to get list of Room Layouts 
	 * @return list of Room Layouts
	 */
	public List<String> getRoomLayouts(){

		String xpath = NEW_RESERVATION_DETAILS_XPATH + "//input[@name='roomLayout']";
						
		String elementId=driver.findElement(By.xpath(xpath)).getAttribute("id");
		
		return DropDown.getComboValuesFromDropDownSelector(driver, elementId);
	}
	
	/**
	 * Helper method to get Summary Tab text 
	 * @return Summary Tab text 
	 */
	public String getSummaryTabText(){
		
		String xpath = NEW_RESERVATION_DETAILS_XPATH + "//span[contains(text(),'Summary')]/../..//div[contains(@class,'x-panel-body')]";

		return driver.findElement(	By.xpath(xpath)).getText();

	}
	
	/**
	 * Helper method to click on PrintIcon in Summary tab
	 *  */
	public void clickPrintIconInSummarySection(){
		clickXPath(NEW_RESERVATION_DETAILS_XPATH +"//span[contains(text(),'Summary')]/../..//button[contains(@class,'icon-report')]");
	}
	
	
	/**
	 * Helper method to click Add Vehicle button in Add/Remove section of Order items section in new Reservation
	 */
	public void clickAddAdditionalVehicle(){
                clickXPath(NEW_RESERVATION_DETAILS_XPATH +"//button[text()='Add Vehicle']");
	}
	
	
	/**
	 * Helper method to click Add Parking button in Add/Remove section of Order items section in new Reservation
	 */
	public void clickAddAdditionalParking(){
                clickXPath(NEW_RESERVATION_DETAILS_XPATH +"//button[text()='Add Parking']");
	}
	
	/**
	 * Helper method to click Add  Equipment button in Add/Remove section of Order items section in new Reservation
	 */
	public void clickAddAdditionalEquipment(){
                clickXPath(NEW_RESERVATION_DETAILS_XPATH +"//button[text()='Add Equipment']");
	}
	
	/**
	 * Helper method to click Add Service button in Add/Remove section of Order items section in new Reservation
	 */
	public void clickAddAdditionalService(){
                clickXPath(NEW_RESERVATION_DETAILS_XPATH +"//button[text()='Add Service']");
	}
	
	/**
	 * Helper method to add additional vehicle to Reservation
	 * @param vehicleRef
	 */
	public void addAdditionalVehicle(String vehicleRef){
		
		clickAddAdditionalVehicle();
		
		waitForExtJSAjaxComplete(20);
		
		setValueGridLookupWithFilters(vehicleRef, "Reference");
		//setValueGridLookupWithFiltersWithValidation("@class", "x-resizable", vehicleRef, "Reference");
	}
	
	/**
	 * Helper method to get date in new reservation 
	 * @return date 
	 */
	
	public String getDate()
	{
		return McsElement.getElementByXpath(driver, PARAMETER_DATE_XPATH).getAttribute("value");
	}
	
	/**
	 * Helper method to click on close button
	 */
	
    public void clickCloseReservationUsingJS(){
		
		WebElement closeReservationButton = driver.findElement(By.xpath(NEW_RESERVATION_DETAILS_XPATH+CLOSERESERVATION_XPATH) );
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", closeReservationButton);
		Reporter.log("Click close reservation <br>", true);
	}
	
    
	/**
	 * Helper method to get Dates of all days in current week in specified date
	 * format
	 * 
	 * @param dateFormat
	 *            - date format
	 * @return list of Dates of all days in current week
	 */
	public List<String> getAllDatesInWeek(String dateFormat, String date) {

		Locale lc= Locale.US;
		
		Locale.setDefault(Locale.US);
		
		TimeZone tz1 = TimeZone.getTimeZone("GMT");
		
		GregorianCalendar calendar = new GregorianCalendar(lc);

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		Date dateOfDayInWeek = null;

		try {

			dateOfDayInWeek = formatter.parse(date);

		} catch (ParseException e) {

			Reporter.log(date + " passed is not in dd-MMM-yyyy format", true);
		}

		calendar.setTime(dateOfDayInWeek);

		SimpleDateFormat format = new SimpleDateFormat(dateFormat);

		List<String> days = new ArrayList<String>();
		int delta = -calendar.get(GregorianCalendar.DAY_OF_WEEK) + 2;
		calendar.add(Calendar.DAY_OF_MONTH, delta);
		for (int i = 0; i < 7; i++) {
			days.add(format.format(calendar.getTime()));
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}

		return days;
	}

	/**
	 * Helper method to get Dates of all working days in current week in
	 * specified date format
	 * 
	 * @param dateFormat
	 *            - date format
	 * @return
	 * @return list of Dates of all days in current week
	 */
	public List<String> getWorkingDaysInWeek(String dateFormat, String date) {

		Calendar calendar = Calendar.getInstance();

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		Date dateOfDayInWeek = null;

		try {

			dateOfDayInWeek = formatter.parse(date);

		} catch (ParseException e) {

			Reporter.log(date + " passed is not in dd-MMM-yyyy format", true);
		}

		calendar.setTime(dateOfDayInWeek);

		SimpleDateFormat format = new SimpleDateFormat(dateFormat);

		List<String> workingdays = new ArrayList<String>();
		int delta = -calendar.get(GregorianCalendar.DAY_OF_WEEK) + 2;
		calendar.add(Calendar.DAY_OF_MONTH, delta);
		for (int i = 0; i < 5; i++) {
			workingdays.add(format.format(calendar.getTime()));
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}

		return workingdays;
	}

	public void setCleanUpTimeGeneral(String prepare) {

		String xpath = NEW_RESERVATION_DETAILS_XPATH+"//input[contains(@class,'x-form-field') and contains(@name,'cleanup')]";

		String id =driver.findElement(By.xpath(xpath)).getAttribute("id");
		waitForExtJSAjaxComplete(5);
		DropDown.setComboValueDropDownSelector(driver, id, prepare);
		
	}
	
	public void clickGeneralSubject(){
		driver.findElement(By.xpath(NEW_RESERVATION_DETAILS_XPATH+"//span[contains(text(),'General')]/../..//span[contains(text(),'Subject')]")).click();
		waitForExtJSAjaxComplete(10);
	}
	
	public void setGeneralSubject(String subject){
		
		String xpath = NEW_RESERVATION_DETAILS_XPATH+"//textarea[contains(@name,'subject')]";
		
		WebElement textField = McsElement.getElementByXpath(driver, xpath);
		textField.clear();
		textField.click();
		textField.sendKeys(subject);
		
		textField.sendKeys(Keys.ENTER);
	}
	
	public String getCleanUpTimeGeneral(){
		
		return McsElement.getElementByXpath(driver,NEW_RESERVATION_DETAILS_XPATH+"//input[contains(@class,'x-form-field') and contains(@name,'cleanup')]").getAttribute("value");
		
	}
	
	/**
	 * Helper method to set room purpose
	 * @param purpose
	 */
	
	public void setRoomPurposeInOrderItemsTab(String purpose){
		
		String id = McsElement.getElementByXpath(driver,NEW_RESERVATION_DETAILS_XPATH+PARAMETER_ROOMPURPOSE_XPATH).getAttribute("id");
		System.out.println("id " +id);
		waitForExtJSAjaxComplete(5);
		
		DropDown.setComboValueDropDownSelector(driver, id, purpose);
	}
	
	/**
	 * Helper method to set room Layout
	 * @param purpose
	 */

	public void setRoomLayoutInOrderItemsTab(String layout){
		
		String id = McsElement.getElementByXpath(driver,NEW_RESERVATION_DETAILS_XPATH+PARAMETER_ROOMLAYOUT_XPATH).getAttribute("id");
		
		waitForExtJSAjaxComplete(5);
		
		DropDown.setComboValueDropDownSelector(driver, id, layout);
		
		
	}
	
	/**
	 * Helper method to get value for purpose field
	 * @return value
	 */
	
	public String getRoomPurposeInOrderItemsTab(){
		
		return McsElement.getElementByXpath(driver,NEW_RESERVATION_DETAILS_XPATH+PARAMETER_ROOMPURPOSE_XPATH).getAttribute("value");
		
	}

	/**
	 * Helper method to get value for subject field
	 * @return value
	 */
	public String getGeneralSubject(){
		
		return driver.findElement(By.xpath(NEW_RESERVATION_DETAILS_XPATH+"//textarea[contains(@name,'subject')]")).getAttribute("value").trim();
	}
	
	/**
	 * Helper method to get an Item details from Summary tab
	 * @return item detail
	 */
	public String getItemDetailsFromReservationSummary(String item){
		
		String xpath = NEW_RESERVATION_DETAILS_XPATH + "//table[@class='mcsreservation-summary-table']//b[contains(text(),'"+item+"')]/..";
		
		return driver.findElement(By.xpath(xpath)).getText();
		
	}
	
	/**
	 * Helper method to set Cost Center method In financial tab
	 * @param method to select
	 */
	public void setCostCenterMethodInFinancialsTab(String method){
		
		String xpath = NEW_RESERVATION_DETAILS_XPATH + "//input[@name='costCenterMethod']";
		
		String elementId=driver.findElement(By.xpath(xpath)).getAttribute("id");
		
		DropDown.setComboValueDropDownSelector(driver, elementId, method);
		// DropDown.setComboValue(driver, elementId, method); 
		
	}
	
	/**
	 * Helper method to get Cost center value
	 */
	public String getCostCenter(){
		
		String xpath = NEW_RESERVATION_DETAILS_XPATH + "//input[@name='costCenter']//following-sibling::input";
	
		return driver.findElement(By.xpath(xpath)).getAttribute("value");
	}
	
	/**
	 * Helper method to get Cost center lookup values
	 * @return List of cost centers
	 */
	public List<String> getCostCenterLookUpvalues(){
		
		return getLookUpValues("costCenter", "Select a Cost Center","Reference");
	}
	
	/**
	 *Helper method to get prepare time in general tab 
	 * @return value
	 */
	
	public String getPrepareTimeGeneral(){
		
		return McsElement.getElementByXpath(driver,NEW_RESERVATION_DETAILS_XPATH+"//input[contains(@class,'x-form-field') and contains(@name,'prepare')]").getAttribute("value");
		
	}
	
	/**
	 * Helper method to select item in Order Items section
	 * @param itemType : type of Item
	 * @param itemName : name of item to select
	 */
	
	public boolean isItemAddedInOrderItemsPane(String itemType, String itemName){
		
		List<String> itemslist = new ArrayList<String>();
		
		itemslist.add("service");
		itemslist.add("parking");
		itemslist.add("vehicle");
		itemslist.add("equipment");
		itemslist.add("room");
		
		itemType = itemType.trim().toLowerCase();
		
		String iconClass = "";
		
		int index = itemslist.indexOf(itemType);
		
		switch(index){
		
		case 0:{
			iconClass ="icon-reservable-service";
			break;
		}
		case 1:{
			iconClass ="icon-reservable-parking";
			break;
		}
		case 2:{
			iconClass ="icon-reservable-vehicle";
			break;
		}
		
		case 3:{
			iconClass ="icon-reservable-equipment";
			break;
		}
		
		case 4:{
			iconClass ="icon-reservable-room";
			break;
		}
		
		}
		
		
		String xpath = NEW_RESERVATION_DETAILS_XPATH + "//img[contains(@class,'"+iconClass+"')]/..//span[contains(text(),'"+itemName+"')]";
	
		try{
			
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				
			McsElement.getElementByXpath(driver, xpath);
			
			return true;
		}
		catch(Exception e){
			return false;
		}
		finally{
			
			driver.manage()
			.timeouts()
			.implicitlyWait(configuration.getImplicitWait(),
					TimeUnit.SECONDS);
			
		}
	}
	
	/**
	 * Helper method to verify equipment item is displayed or not
	 * @param equipment
	 */
	
	public boolean isDisplayLaunchReservation(String equipment){
		
		waitForExtJSAjaxComplete(25);
		
		String xpath="//span[contains(text(),'"+equipment+"')]/../../.."+LAUNCH_RESERVATION_XPATH;
		
		try{
			
			McsElement.getElementByXpath(driver, xpath);
			
			return true;
		}
		catch(Exception e){
			return false;
		}
		
	}
	
	/**
	 * Helper method to click on PrintIcon in Summary tab
	 *  */
	public void clickReportIconInSummarySection(){
		
		String xpath = NEW_RESERVATION_DETAILS_XPATH +"//span[contains(text(),'Summary')]/../..//button[contains(@class,'icon-report')]";
		
		driver.findElement(By.xpath(xpath)).click();
	}

	/**
	 * Helper method to check whether Participant tab is visible in New Reservation Window
	 * @return true/false based on presence of Participant tab.
	 */
	public boolean isParticipantsTabVisible() {

		String xpath ="//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Participants')]/../..";
		return McsElement.isElementDisplayed(driver,xpath);

	}

	/**
	 * Helper method to check whether Summary cost is included in Summary section of Reservations
	 * @return true/false based on presence of Summary cost.
	 */
	public boolean isSummaryCostIncludeColumnVisbile() {

		String xpath ="//table[contains(@class,'mcsreservation-summary-table')]//th[text()='Cost (Incl) *']";
		return McsElement.isElementDisplayed(driver,xpath);

	}

	/**
	 *Helper method to get the region values for all columns in the region
	 */
	public String getRegion()
	{
		String region = McsElement.getElementByXpath(driver, "//div[contains(@class,'reservations')]//input[contains(@name,'region')]/following-sibling::input").getAttribute("value");

		return region;
	}


	/**
	 *Helper method to get the From Time of New Reservations.
	 */
	public String getFromTime(){

		return driver.findElement(By.xpath("//div[contains(@class,'x-panel reservations')]//input[contains(@name,'from')]")).getAttribute("value");

	}

	/**
	 *Helper method to get the Until Time of New Reservations.
	 */
	public String getUntilTime(){

		return driver.findElement(By.xpath("//div[contains(@class,'x-panel reservations')]//input[contains(@name,'until')]")).getAttribute("value");

	}

	/**
	 * Helper method to select the name as Room in New Reservations.
	 * @return Name of the Item.
	 */
	public List<String> findRoomsFreeForRegionGivenTime(){

		return getReservableItemsFromLaunchReservation("Rooms");
	}

	/**
	 * Helper method to select the name as Equipment in New Reservations.
	 * @return Name of the Item.
	 */
	public List<String> findEquipmentFreeForRegionGivenTime(){

		return getReservableItemsFromLaunchReservation("Equipment");
	}

	/**
	 * Helper method to select the name as Services and Catering in New Reservations.
	 * @return Name of the Item.
	 */
	public List<String> findServiceFreeForRegionGivenTime(){

		return getReservableItemsFromLaunchReservation("Services and Catering");
	}
	/**
	 * Helper method to get the Reserved Items From Launch Reservation after clicking search in New reservations.
	 * @param reseravableItem : Name of the Item to select.
	 */
	public List<String> getReservableItemsFromLaunchReservation(String reseravableItem){

		String arrayOfReservableItems[] = {"Services and Catering","Parking","Vehicles","Equipment","Rooms"};
		List<String> itemslist = Arrays.asList(arrayOfReservableItems);

		String icon = "";

		int index = itemslist.indexOf(reseravableItem.toLowerCase());

		switch(index){

		case 0:{
			icon ="icon-service.png";
			break;
		}
		case 1:{
			icon ="icon-parking.png";
			break;
		}
		case 2:{
			icon ="icon-vehicle.png";
			break;
		}

		case 3:{
			icon ="icon-equipment.png";
			break;
		}

		case 4:{
			icon ="icon-room.png";
			break;
		}

		}


		List<String> availableReservableItems = new ArrayList<String>();

		//String xpath ="//table[contains(@class,'rsrvsearchresults')]//img[contains(@src,'"+icon+"')]/following-sibling::span";

		

		try{
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			List<WebElement> reservableItemsElesList =driver.findElements(By.xpath("//table[contains(@class,'rsrvsearchresults')]//img[contains(@src,'"+icon+"')]/following-sibling::span"));
			
		for(WebElement reservableItemEle:reservableItemsElesList ){

			availableReservableItems.add(reservableItemEle.getText().trim());
		}
		}
		finally{
			
			driver.manage()
			.timeouts()
			.implicitlyWait(configuration.getImplicitWait(),
					TimeUnit.SECONDS);
			
		}
		System.out.println(availableReservableItems);

		return availableReservableItems;

	}

	/**
	 *Helper method to set the Until Time in Reservation Panel.
	 */
	public void setTimeUntilReservationPanel(String date){
		String id= McsElement.getElementByXpath(driver, PARAMETER_UNTIL_RESERVATIONPANEL_XPATH).getAttribute("id");
		DropDown.setComboValueDropDownSelector(driver,id,date);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
	}
	
	/**
	 *Helper method to set the Until Time in Reservation Panel.
	 */
	public void setTimeUntilReservationPanel1(String date){
		WebElement until= driver.findElement(By.xpath(PARAMETER_UNTIL_RESERVATIONPANEL_XPATH));
		until.click();
		waitForExtJSAjaxComplete(10);
		until.clear();
		waitForExtJSAjaxComplete(10);
		until.sendKeys(date);
		waitForExtJSAjaxComplete(10);
		until.click();
		waitForExtJSAjaxComplete(10);
	}

	/**
	  * Checking whether My Reservation Window is opened or not
	  */
	 public boolean isMyreservationWindowOpen()
	 {

	  waitForExtJSAjaxComplete(20);
	  String xpath = "//div[contains(@class,'x-panel reservations')]//a[text()='My Reservations']/ancestor::table[contains(@class,'x-btn-pressed')]";
	  return McsElement.isElementDisplayed(driver, xpath);

	 }
		 
		/**
		 * Helper method to click on Date Controllers  
		 * @param day
		 */
		public void clickOnPrevOrNextDayInNewReservationWindow(String day){

			driver.findElement(By.xpath("//div[contains(@class,'reservations x-panel-noborder')]//button[contains(@class,'x-btn-text icon-day-"+day+"')]")).click();
		}

		/**
		 * Helper method to get Previous day date
		 * @param date
		 * @return
		 * @throws ParseException
		 */
		public String getPreviousDateForRandomDate(String date) throws ParseException {
            return  getNoOfDaysFromSpecifiedDate(date,"dd-MM-yyyy",-1);
            }
		
		/**
		 * Helper method to get Next day Date 
		 * @param date
		 * @return
		 * @throws ParseException
		 */
		 public String getNextDayDateForRandomDate(String date) throws ParseException {
             
             return  getNoOfDaysFromSpecifiedDate(date,"dd-MM-yyyy",1);
       }

		/**
		 * Helper method to set Equipment Already In Room 
		 * @param EqpCategory
		 */
		public void selectAlreadyInRoom(String eqpCategory){

			String id = driver.findElement(By.xpath("//input[@name='selectEquipment']/..//input[contains(@class,'x-form-field')]")).getAttribute("id");
			DropDown.setComboValueDropDownSelector(driver,id,eqpCategory);
			waitForExtJSAjaxComplete(25);
		}

		/**
		 * Helper method to clear Equipment Already In Room selection 
		 */
		public void clearTheSelectionInAlreadyRoomField(String Equipment){
			driver.findElement(By.xpath("//div[text()='"+Equipment+"']/../..//div[@qtip='Remove this Category'] ")).click();
		Reporter.log("Selection in the in 'Already in Room is field is cleared ",true);
		}
		
		/**
		 * Helper method to get ToolTip Info In New Reservation window 
		 * @param room
		 * @return
		 */
		public String reservableToolTipContent(String room){
			mouseMove("//span[contains(text(),'"+room+"')]//ancestor::td//img[contains(@src,'icon-info-gray.png')]");
			waitForExtJSAjaxComplete(20);
			String content = McsElement.getElementByXpath(driver, "//div[contains(@class,'x-tip reservations') and contains(@style,'visibility: visible')]//div[contains(@class,'x-tip-body')]").getText();
			waitForExtJSAjaxComplete(10);
			return content;
			
		}

		/**
		 * Helper method to check whether the Includes values is present or not 
		 * @param room
		 * @param includesValue
		 * @return
		 */
		public boolean isIncludedEquipmentPresent(String room, String includesValue){
			String xpath = "//span[contains(text(),'"+room+"')]//ancestor::table[@class='rsrvsearchresults']//td[contains(text(),'"+includesValue+"')]";
			return McsElement.isElementDisplayed(driver,xpath);

		}

		/**
		 * Helper method to get RoomLayout value in Orders Tab 
		 * @return
		 */
		public String getRoomLayoutInOrderItemsTab(){

			return McsElement.getElementByXpath(driver,NEW_RESERVATION_DETAILS_XPATH+PARAMETER_ROOMLAYOUT_XPATH).getAttribute("value");

		}

		/**
		 * Helper method to check whether room Section is selected by default
		 * @return
		 */
		public boolean isRoomSectionIsSelectedByDefault(){
			String xpath = "//span[contains(text(),'Parameters')]/../..//div[contains(@class,'mcs-form-large-labels')]//li[contains(@id,'room') and contains(@class,'x-tab-strip-active')]";
			return McsElement.isElementDisplayed(driver,xpath);
		}

		/**
		 * Helper method to get list of Room Layouts 
		 * @return list of Room Layouts
		 */
		public List<String> getOptionsForFieldInNewReservation(String fieldName){

			String xpath ="//input[contains(@name,'"+fieldName+"')]/..//input[contains(@class,'x-form-field')]";
							
			String elementId=driver.findElement(By.xpath(xpath)).getAttribute("id");
			
			return DropDown.getComboValuesFromDropDownSelector(driver, elementId);
		}
		
		/**
		 * Helper method to get field value in InReservation window 
		 * @param fieldName
		 * @return
		 */
		public String getFieldValueInNewReservationWindow(String fieldName){
		return driver.findElement(By.xpath("//input[contains(@name,'"+fieldName+"')]/..//input[contains(@class,'x-form-field')]")).getAttribute("value");

		}
		
		/**
		 * Helper method to check Room layout Selections Image
		 * @return
		 */
		public boolean isImageDisplayedInRoomLayOutSection(String roomLayout){
			driver.findElement(By.xpath("//input[contains(@name,'roomLayout')]/..//img")).click();
			waitForExtJSAjaxComplete(20);
			return McsElement.isElementDisplayed(driver,"//div[contains(@class,'x-combo-list-inner')]//div[contains(text(),'"+roomLayout+"')]/..//img");
		}



	 
		// Verify tabs are present or not
		/**
		 * Helper method to verify tabs in new reservation window
		 * 
		 * @param tabName
		 *            : name of tab to select
		 */

		public boolean isReservableTabsVisible(String tabName) {

			String[] tabs = {"rooms", "parking", "vehicles", "services",
					"equipment" };

			List<String> tabsList = Arrays.asList(tabs);

			int index = tabsList.indexOf(tabName);

			String xpath = NEW_RESERVATION_PARAMETERS_FORM_XPATH;

			switch (index) {

			case 0: {

				xpath = xpath + PARAMETER_ROOM_XPATH;
				break;
			}

			case 1: {

				xpath = xpath + PARAMETER_EQUIPMENT_XPATH;
				break;
			}

			case 2: {

				xpath = xpath + PARAMETER_SERVICE_XPATH;
				break;
			}

			case 3: {
				xpath = xpath + PARAMETER_PARKING_XPATH;
				break;
			}

			case 4: {
				xpath = xpath + PARAMETER_VEHICLE_XPATH;
				break;
			}
			}

			return McsElement.isElementDisplayed(driver, xpath);

		}

		/**
		 * Helper method to verify Room tab is present or not
		 * 
		 * @return true or false
		 */
		public boolean isRoomTabVisible() {
			return isReservableTabsVisible("rooms");
		}

		/**
		 * Helper method to verify Equipment tab is present or not
		 * 
		 * @return true or false
		 */
		public boolean isEquipmentTabVisible() {
			return isReservableTabsVisible("equipment");
		}

		/**
		 * Helper method to verify Service tab is present or not
		 * 
		 * @return true or false
		 */
		public boolean isServiceTabVisible() {
			return isReservableTabsVisible("services");
		}

		/**
		 * Helper method to verify Parking tab is present or not
		 * 
		 * @return true or false
		 */
		public boolean isParkingTabVisible() {
			return isReservableTabsVisible("parking");
		}

		/**
		 * Helper method to verify vehicle tab is present or not
		 * 
		 * @return true or false
		 */
		public boolean isVehicleTabVisible() {
			return isReservableTabsVisible("vehicles");
		}

		// Verify Fields are present or not
		/**
		 * Helper method to verify Fields in new reservation window
		 * 
		 * @param tabName
		 *            : name of Fields to select
		 */
		public boolean isReservableFieldsDisplayed(String fieldName) {

			String[] tabs = { "category", "purpose", "capacity", "layout" };

			List<String> tabsList = Arrays.asList(tabs);

			int index = tabsList.indexOf(fieldName);

			String xpath = NEW_RESERVATION_PARAMETERS_FORM_XPATH;

			switch (index) {

			case 0: {

				xpath = xpath + PARAMETER_ROOMCATEGORY_XPATH;
				break;
			}

			case 1: {

				xpath = xpath + PARAMETER_ROOMPURPOSE_XPATH;
				break;
			}

			case 2: {

				xpath = xpath + PARAMETER_ROOMCAPACITY_XPATH;
				break;
			}

			case 3: {
				xpath = xpath + PARAMETER_ROOMLAYOUT_XPATH;
				break;
			}

			}

			return McsElement.isElementDisplayed(driver, xpath);

		}

		/**
		 * Helper method to verify category Field is present or not
		 * 
		 * @return true or false
		 */
		public boolean isCategoryFieldVisible() {
			return isReservableFieldsDisplayed("category");
		}

		/**
		 * Helper method to verify Purpose Field is present or not
		 * 
		 * @return true or false
		 */
		public boolean isRoomPurposeFieldVisible() {
			return isReservableFieldsDisplayed("purpose");
		}

		/**
		 * Helper method to verify NrOfPeople Field is present or not
		 * 
		 * @return true or false
		 */
		public boolean isRoomNrOfPeopleFieldVisible() {
			return isReservableFieldsDisplayed("capacity");
		}

		/**
		 * Helper method to verify Layout Field is present or not
		 * 
		 * @return true or false
		 */
		public boolean isRoomLayoutFieldVisible() {
			return isReservableFieldsDisplayed("layout");
		}

		// Verify default values are displayed or not
		/**
		 * Helper method to verify Default values in new reservation window
		 * 
		 * @param tabName
		 *            : name of values to select
		 */
		public String getValuesOfParametersInNewReservation(String fieldName) {

			String[] tabs = { "roomcategory", "roompurpose", "roomcapacity","roomlayout"};

			List<String> tabsList = Arrays.asList(tabs);

			int index = tabsList.indexOf(fieldName);

			String xpath = NEW_RESERVATION_PARAMETERS_FORM_XPATH;

			switch (index) {

			case 0: {

				xpath = xpath + PARAMETER_ROOMCATEGORY_XPATH;
				break;
			}

			case 1: {

				xpath = xpath + PARAMETER_ROOMPURPOSE_XPATH;
				break;
			}

			case 2: {

				xpath = xpath + PARAMETER_ROOMCAPACITY_XPATH;
				break;
			}

			case 3: {
				xpath = xpath + PARAMETER_ROOMLAYOUT_XPATH;
				break;
			}
			
			}
			
			return McsElement.getElementByXpath(driver,xpath).getAttribute("value");

		}

		/**
		 * Helper method to verify Room Purpose Field Value Displayed or not
		 * 
		 * @return true or false
		 */
		public String getRoomPurpose() {
			return getValuesOfParametersInNewReservation("roompurpose");
		}

		/**
		 * Helper method to verify  Room Capacity Field Value Displayed or not
		 * 
		 * @return true or false
		 */
		public String getRoomCapacity() {
			return getValuesOfParametersInNewReservation("roomcapacity");
		}

		/**
		 * Helper method to verify Room Layout Field Value Displayed or not
		 * 
		 * @return true or false
		 */
		public String getRoomLayout() {
			return getValuesOfParametersInNewReservation("roomlayout");
		}

		/**
		 * Helper method to verify Room Category Field Value Displayed or not
		 * 
		 * @return true or false
		 */
		public String getRoomCategory() {
			return getValuesOfParametersInNewReservation("roomcategory");
		}
		
		/**
		 * Helper method to verify Cancel Reservation Button is Enabled or not
		 * @return true or false
		 */
		public static boolean isCancelResevationButtonEnabled() {
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//table[contains(@class,'x-btn x-item-disabled')]//button[text()='Cancel this reservation']"));
				return false;
			} catch (Exception e) {
				return true;
			} finally {
				try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
			}
		}
		
		
		/**
		 * Wait for ExtJS Ajax request to be finished
		 * Ext.mcs.PageState.isLoading() is a custom function of mymcs
		 * 
		 * @param time
		 */
		public void waitForExtJSAjaxComplete(int time) {
			//Timer timer = new Timer().start();
			//long preWaitTime = System.currentTimeMillis();
			
			//Reporter.log("START", true);
			final String script =  "return Ext.Ajax.isLoading()||Ext.mcs.PageState.isLoading()";
			final String scriptStandard = "return Ext.Ajax.isLoading()";
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				new WebDriverWait(driver, time/2, 300)
						.until(new ExpectedCondition<Boolean>() {
							@Override
							public Boolean apply(WebDriver driver) {
								Boolean result = !(boolean) ((JavascriptExecutor) driver)
										.executeScript(script);
								return result;
							}
						});
			} catch (Exception e) {
				try {
					driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
					new WebDriverWait(driver, time/2, 300)
							.until(new ExpectedCondition<Boolean>() {
								@Override
								public Boolean apply(WebDriver driver) {
									Boolean result = !(boolean) ((JavascriptExecutor) driver)
											.executeScript(scriptStandard);
									return result;
								}
							});
				}
				catch(Exception e2){}
				
			} finally {
				driver.manage()
						.timeouts()
						.implicitlyWait(configuration.getImplicitWait(),
								TimeUnit.SECONDS);
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//long postWaitTime = System.currentTimeMillis();
			
			//System.err.println("time ellapsed: "+ ((postWaitTime-preWaitTime)/1000) );
			
			//Reporter.log("Stop"+ " (" + timer.stop() + "ms)\n", true);*/

		}
	
		/**
		 * Method expands Administration
		 */
		@Override
		public void expandAdministration() {
			Timer timer = new Timer().start();
			//waitForMaskDisappear();
			waitForExtJSAjaxComplete(25);
			expandMainMenu("Administration");
			Reporter.log("Expand Administration menu " + " (" + timer.stop()
					+ "ms)", true);
		}
		
		/**
		 * Method allows to select row from grid in lookup using filters
		 * 
		 * @param rowTextName - row text to be selected from lookup 
		 * 
		 * @columnName - columnName of the row to be selected
		 */
		public void setValueGridLookupWithFilters(String rowTextName, String columnName) {
			Timer timer = new Timer().start();
			
			String columnClass = McsElement
			.getElementByPartAttributeValueAndParentElement(driver,
					"div", "@class", "x-window x-window-noborder x-resizable-pinned",
					"div","@class", "x-grid3-hd",
					"text()", columnName, true, true).getAttribute("class");
			
			//String columnNumber = columnClass.substring(columnClass.length() - 1);
			
			String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
			
			
			filterGrid("@class", "x-window x-window-noborder x-resizable-pinned", rowTextName,  columnName);
			
				
//				McsElement
//				.getElementByPartAttributeValueAndParentElement(driver,
//						"div", "@class", "x-window x-window-noborder x-resizable-pinned", "div",
//						"@class", "x-grid3-body", true, true).click();
				
				waitForExtJSAjaxComplete(5);
				
			/*	if(configuration.getBrowser().equalsIgnoreCase("chrome")){
					
					rowTextName = rowTextName.replaceAll(" ", "&nbsp;");
				}*/
				
				String xpath ="(//"+"div"+"[contains("+"@class"+",'" + "x-window x-window-noborder x-resizable-pinned" + "')]//"
								+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
								//+ "and "+"text()"+ "='" + rowTextName + "'" + 
								+"])";//[last()]
				
				List<WebElement> searchResults = driver.findElements(By.xpath(xpath));
				
				if(searchResults.size()==0){
					
					Reporter.log("No search results found for filtering criteria ", true);
					throw new NoSuchElementException("No search results found for filtering criteria");
					
				}
				
				for(WebElement ele: searchResults){
					
					if(ele.getText().equals(rowTextName)){
						
						ele.click();
						break;
					}
				}
				
				/*McsElement.getElementByXpath(driver,"(//"+"div"+"[contains("+"@class"+",'" + "x-window x-window-noborder x-resizable-pinned" + "')]//"
						+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
						+ "and "+"text()"+ "='" + rowTextName + "'" + 
						"])[last()]").click();
				*/
//				McsElement
//						.getElementByPartAttributeValueAndParentElement(driver,
//								"div", "@class", "x-window x-window-noborder x-resizable-pinned", "div",
//								"@class", "x-grid3-cell-inner x-grid3-col-"+columnNumber,
//								"text()", rowTextName, true, true).click();
				waitForExtJSAjaxComplete(5);
				clickOkXwindow();
				Reporter.log(rowTextName + " was selected"+ " (" + timer.stop()
						+ "ms)", true);

		}
		
		
		
		/**
		 * Method allows to select row from grid in lookup using filters
		 * @param attribute - attribute (@class, @id) of the lookup window
		 * @param attributeValue - attribute of the lookup must contain this value
		 * @param rowTextName - row text to be selected from lookup 
		 * 
		 * @columnName - columnName of the row to be selected
		 */
		public void setValueGridLookupWithFilters(String attribute, String attributeValue, String rowTextName, String columnName) {
			Timer timer = new Timer().start();
			
			waitForExtJSAjaxComplete(5);
			
			waitForExtJSAjaxComplete(5);
			filterGrid(attribute, attributeValue, rowTextName, columnName);	
			
			waitForExtJSAjaxComplete(5);
			
			String columnClass = McsElement
			.getLastElementByPartAttributeValueAndParentElement(driver,
					"div", attribute, attributeValue,
					"div","@class", "x-grid3-hd",
					"text()", columnName, true, true).getAttribute("class");
			
			String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
			//String columnNumber = columnClass.substring(columnClass.length() - 1);
			
			waitForExtJSAjaxComplete(5);
			
			String xpath ="(//"+"div"+"[contains("+"@class"+",'" + "x-window x-window-noborder x-resizable-pinned" + "')]//"
					+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
					//+ "and "+"text()"+ "='" + rowTextName + "'" + 
					+"])";//[last()]

			try{
				
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

			List<WebElement> searchResults = driver.findElements(By.xpath(xpath));

			if(searchResults.size()==0){

				Reporter.log("No search results found for filtering criteria ", true);
				throw new NoSuchElementException("No search results found for filtering criteria");

			}

			for(WebElement ele: searchResults){

				if(ele.getText().equals(rowTextName)){

					ele.click();
					break;
				}
			}
			}finally{
				
				driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
			
			}
			/*McsElement.getElementByXpath(driver,"(//"+"div"+"[contains("+attribute+",'" + attributeValue + "')]//"
			+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
			+ "and "+"text()"+ "='" + rowTextName + "'" +
			"])[last()]").click();
	*/
			waitForExtJSAjaxComplete(5);
				
//				McsElement
//						.getLastElementByPartAttributeValueAndParentElement(driver,
//								"div", attribute, attributeValue, "div",
//								"@class", "x-grid3-cell-inner x-grid3-col-"+columnNumber,
//								"text()", rowTextName, true, true).click();
				
				clickOkXwindow();
				Reporter.log(rowTextName + " was selected"+ " (" + timer.stop()
						+ "ms)", true);

		}
		
		
		
		
		/**
		 * Method allows to filter in grid by text
		 * @param attribute - attribute (@class, @id) of the grid container
		 * @param attributeValue - corresponding value of the grid container attribute
		 * @param rowTextName - row text to be filtered by 
		 * @columnName - columnName of the row 
		 */
		public void filterGrid(String attribute, String attributeValue, String rowTextName, String columnName) {
			Timer timer = new Timer().start();
			
			
			String columnClass = McsElement
					.getElementByXpath(driver,"(//div[contains("+attribute+",'"+ attributeValue+"')]//div[contains(@class,'quickfilters')]"
							+ "//div[contains(@class,'x-grid3-hd') and contains(text(),'"+columnName+"')])[last()]").getAttribute("class");
//			String columnClass = McsElement
//			.getLastElementByPartAttributeValueAndParentElement(driver,
//					"div", attribute, attributeValue,
//					"div","@class", "x-grid3-hd",
//					"text()", columnName, true, true).getAttribute("class");
			
			String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
			

			WebElement filterInput = McsElement.getElementByXpath(driver, "(//div[contains("+attribute+",'"+attributeValue+"')]//"
						+ "input[contains(@id,'filter-editor-"+columnNumber+"')])[last()]"); 
			waitForExtJSAjaxComplete(5);		
						
//						McsElement
//						.getElementByPartAttributeValueAndParentElement(driver,
//								"div", attribute, attributeValue, "input",
//								"@id", "filter-editor-"+columnNumber, true, true);
				
				filterInput.clear();
				
				filterInput.sendKeys(rowTextName);
				
				filterInput.sendKeys(Keys.RETURN);
				
				waitForMaskDisappear();
				
				 McsElement.getElementByXpath(driver, "(//div[contains("+attribute+",'"+attributeValue+"')]//"
							+ "div[contains(@class,'x-grid3-body')])[last()]/div").click(); 
				
//				McsElement
//				.getElementByPartAttributeValueAndParentElement(driver,
//						"div", attribute, attributeValue, "div",
//						"@class", "x-grid3-body", true, true).click();
				
				waitForExtJSAjaxComplete(15);

				Reporter.log(rowTextName + " was filtered"+ " (" + timer.stop()
						+ "ms)", true);

		}

		
		/**
		 * Method allows to select row from grid in lookup using filters
		 * @param attribute - attribute (@class, @id) of the lookup window
		 * @param attributeValue - attribute of the lookup must CONTAINS this value
		 * @param rowTextName - row text to be selected from lookup 
		 * 
		 * @columnName - columnName of the row to be selected
		 * @return true if row was present and false if row was not present in grid
		 */
		public boolean setValueGridLookupWithFiltersWithValidation(String attribute, String attributeValue, String rowTextName, String columnName) {
			Timer timer = new Timer().start();
			
			filterGrid(attribute, attributeValue, rowTextName, columnName);
			
			String columnClass = McsElement
			.getElementByPartAttributeValueAndParentElement(driver,
					"div", attribute, attributeValue,
					"div","@class", "x-grid3-hd",
					"text()", columnName, true, true).getAttribute("class");
			
			//String columnNumber = columnClass.substring(columnClass.length() - 1);
			
			String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
				
				try {
					driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
					
					//TODO: THis code is implemented as text() matching fails for elements which contains space in its text.
					String xpath ="(//"+"div"+"[contains("+"@class"+",'" + "x-window x-window-noborder x-resizable-pinned" + "')]//"
							+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
							//+ "and "+"text()"+ "='" + rowTextName + "'" + 
							+"])";//[last()]
			
					List<WebElement> searchResults = driver.findElements(By.xpath(xpath));

					if(searchResults.size()==0){

						Reporter.log("No search results found for filtering criteria ", true);
						throw new NoSuchElementException("No search results found for filtering criteria");

					}

					for(WebElement ele: searchResults){

						if(ele.getText().trim().equals(rowTextName)){
							
							Actions actions = new Actions(driver);
							actions.moveToElement(ele).click().perform();

							//ele.click();
							break;
						}
					}
			
					/*		McsElement
						.getElementByPartAttributeValueAndParentElement(driver,
								"div", attribute, attributeValue, "div",
								"@class", "x-grid3-cell-inner x-grid3-col-"+columnNumber,
								"text()", rowTextName, false, false).click();*/
					
				waitForExtJSAjaxComplete(15);
					
				clickOkXwindow();
				Reporter.log(rowTextName + " is present in grid and selected"+ " (" + timer.stop()
						+ "ms)", true);
				return true;
				}
				catch(Exception e){
					Reporter.log(rowTextName + " is not present"+ " (" + timer.stop()
							+ "ms)", true);
					return false;
				}
				 finally {
						try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
					}
		}
		
		
		

		/**
		 * Method allows to click on lookup button on the form for some input
		 * @param  parentAttributeValue - parent attribute (@class, @id) of the form where lookup is present
		 * @param  parentAttributeValue - value of parent attribute
		 * @param  childWindowHeader - header of the child window which should appear
		 * @param  inputName - name of the input for which lookup is opened 
		 */	
		public void clickLookup(String parentAttribute, String parentAttributeValue, String inputName, String childWindowHeader){
			Timer timer = new Timer().start();

			waitFocusAndClick("//div[contains("+parentAttribute+",'"
					+ parentAttributeValue + "')]//input[contains(@name,'"
					+ inputName + "')]//..//..//button");

			try {
				driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
				driver.findElement(By
						.xpath("//div[contains(@id,'"
					+getXWindowId(childWindowHeader) + "')]"));
			}

			catch (Exception e) {
				waitAndClick("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");
			} finally {
				driver.manage()
						.timeouts()
						.implicitlyWait(configuration.getImplicitWait(),
								TimeUnit.SECONDS);
			}
			waitForExtJSAjaxComplete(5);

			Reporter.log(inputName + " lookup was clicked"+ " (" + timer.stop()
					+ "ms)", true);
			
			
		}
		
		/**
		 * Method to retrieve values of lookup from grid in lookup
		 * @param attribute - attribute (@class, @id) of the lookup window
		 * @param attributeValue - attribute of the lookup must contain this value
		 * @columnName - columnName of the values to be retrieved
		 */
		public List<String> getValuesFromGridLookup(String attribute, String attributeValue, String columnName) {
			Timer timer = new Timer().start();
			
			waitForExtJSAjaxComplete(25);
			
			ArrayList<String> lsValues = new ArrayList<String>(); 
			
			String columnClass = McsElement
			.getLastElementByPartAttributeValueAndParentElement(driver,
					"div", attribute, attributeValue,
					"div","@class", "x-grid3-hd",
					"text()", columnName, true, true).getAttribute("class");
			
			String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
			
			try{
				
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				
			List<WebElement> values = driver.findElements(By.xpath("//"+"div"+"[contains("+attribute+",'" + attributeValue + "')]//"
			+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
			+ "]"));
				
		
			
			for(int i=0;i<values.size(); i++){
				
				String valueText = values.get(i).getText().trim();
				
				lsValues.add(valueText);
				
			}
			}finally{
				
				driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
			
			}
				
			Reporter.log(columnName + "values are retrieved"+ " (" + timer.stop()+ "ms)", true);
			
			return lsValues;
		}
		
		/**
		 * Helper method to set other Cost Center In financial tab
		 * @param other to select
		 */
		public void setCostCenter(String other) {
			clickLookup("@class","mcsreservationedit", "costCenter","Select a Cost Center");
			waitForExtJSAjaxComplete(25);
			setValueGridLookupWithFilters("@id", getXWindowId("Select a Cost Center"), other, "Reference");
		}

		/**
		 * Helper method to verify Cost Center Lookup value is Enabled or not
		 * @return true or false
		 */
		public boolean isCostCenterLookUpEnabled() {
			WebElement  reset= McsElement.getElementByXpath(driver,"//div[contains(@class,'mcsreservationedit')]//input[contains(@name,'costCenter')]//..//..//button");
			return isFieldDisabled(reset);
		}

		/**
		 * Helper method to check if the passed field is disabled or not
		 **/
		public boolean isFieldDisabled(WebElement element) {
			String classAttrVal =element.getAttribute("class");
			return (classAttrVal.contains("noedit") ||classAttrVal.contains("readonly") || classAttrVal.contains("disabled") )?false:true;
		}
		/**
		 * Helper method to verify field is disabled or not
		 * @return
		 */
		public boolean isConfirmButtonDisabled()
		{
		WebElement xpath=McsElement.getElementByXpath(driver,CONFIRM_RESERVATION_XPATH);
		return isFieldDisabled(xpath);
		}
		
		/**
		 * Helper method to return true/false based on reservation is done
		 * @return true/false based on whether reservation 
		 */
		public boolean isCopyReservationDisplayed(){

			String xpath = NEW_RESERVATION_DETAILS_XPATH + "//button[ contains(@class,'x-btn-text') and text()='Copy']";

			try{

				McsElement.getElementByXpath(driver, xpath);

				return true;
			}
			catch(Exception e){
				return false;
			}
		}

		/**
		 * Helper method to click on Copy button  
		 */
		public void clickCopyReservation(){
			McsElement.getElementByXpath(driver, COPY_RESERVATION_XPATH).click();
			Reporter.log("copy reservation was clicked <br>", true);
			waitForExtJSAjaxComplete(15);
		}

		/**
		 * Helper method to set other Fin Key3 In financial tab
		 */
		public void setFinKey3(String reference) {
			clickLookup("@class","x-panel", "finKey3","Select a value");
			waitForExtJSAjaxComplete(10);
			setValueGridLookupWithFilters("@id", getXWindowId("Select a value"), reference, "Value");
			Reporter.log("fin key 3 "+ reference+" was set", true);
		}

		/**
		 * Helper method to set other Fin Key4 In financial tab
		 */
		public void setFinKey4(String reference) {
			clickLookup("@class","x-panel", "finKey4","Select a value");
			waitForExtJSAjaxComplete(10);
			setValueGridLookupWithFilters("@id", getXWindowId("Select a value"), reference, "Value");
			Reporter.log("fin key 4 "+ reference+" was set", true);
		}

		/**
		 * Helper method to select the Rooms from Copy Reservation Button.
		 */
		public List<String> findRoomsFreeForGivenRegion(){
			return getReservableItemsFromCopyReservation("Select a Room");
		}

		/**
		 * Helper method to get the Reserved Items Copy Reservation Button.
		 */
		public List<String> getReservableItemsFromCopyReservation(String windowTitle){
			List<String> availableReservableItems = new ArrayList<String>();
			availableReservableItems=getValuesFromGridLookup("@id",getXWindowId(windowTitle), "Reference");
			availableReservableItems.removeAll(Arrays.asList(""));
			System.out.println(availableReservableItems);
			return availableReservableItems;
		}

		/**
		 *Helper method to get the From Time of New Reservations.
		 */
		public String getNewReservationFromTime(){
			return driver.findElement(By.xpath("//input[contains(@name,'from')]//..//td[contains(@class,'ux-datetime-time')]//input")).getAttribute("value").trim();
		}

		/**
		 *Helper method to get the Until Time of New Reservations.
		 */
		public String getNewReservationUntilTime(){
			return driver.findElement(By.xpath("//input[contains(@name,'until')]//..//td[contains(@class,'ux-datetime-time')]//input")).getAttribute("value");
		}

		/**
		 *Helper method to get the Responsible of New Reservations.
		 */
		public String getResponsible(){
			return driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//input[@name='responsible']/..//input[@type='text']"))
					.getAttribute("value");
		}

		/**
		 *Helper method to get the Contact of New Reservations.
		 */
		public String getContact(){
			return driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//input[@name='contact']/..//input[@type='text']"))
					.getAttribute("value");
		}

		/**
		 * Helper method to get Cost center value
		 */
		public String getFinKey3(){
			String xpath = NEW_RESERVATION_DETAILS_XPATH + "//input[@name='finKey3']//following-sibling::input";
			return driver.findElement(By.xpath(xpath)).getAttribute("value");
		}

		/**
		 * Helper method to get Cost center value
		 */
		public String getFinKey4(){
			String xpath = NEW_RESERVATION_DETAILS_XPATH + "//input[@name='finKey4']//following-sibling::input";
			return driver.findElement(By.xpath(xpath)).getAttribute("value");
		}

		public void clickOnDialogButtonCustomized(String buttonText) {
			driver.findElement(By.xpath("//div[contains(@class, 'x-window-dlg')]//button[contains(@class, 'x-btn-text') and text()='" + buttonText + "']")).click();
			Reporter.log("Click on '"+buttonText+"' button on dialog", true);
		}
		
		/**
		 * Helper method to return true/false based on Order Rooms By ComboBox Visibility
		 */
		public boolean isOrderRoomsByComboBoxVisible(){

			String xpath = "//div[contains(@class,'x-form-field-wrap') and not(contains(@class,'x-hide-display'))]//input[contains(@name,'orderRoomsBy')]/..//input[last()]";

			try{

				McsElement.getElementByXpath(driver, xpath);

				return true;
			}
			catch(Exception e){
				return false;
			}
		}

		/**
		 * Helper method to get Room capacity
		 * @param room name
		 * @return capacity
		 */
		public int getRoomCapacity(String room) {

			String xpath = "//table[contains(@class,'rsrvsearchresults')]//span[contains(text(),'"+room+"')]/ancestor::tr//img[contains(@src,'unknown')]/..";

			String roomCapacity = driver.findElement(By.xpath(xpath)).getText().trim();

			roomCapacity = roomCapacity.replaceAll("\\D+","").trim();
			
			System.err.println("roomCapacity list"+roomCapacity);
			
			return (roomCapacity.isEmpty()) ?0:Integer.parseInt(roomCapacity);
		}
		
		/**
		 * Helper method to verify Rooms are sorted by capacity  
		 * @param roomcapacityList
		 * @return true
		 */
		public boolean isRoomCapactiyInSortedOrder(List<Integer> roomcapacityList){
			
			boolean isSorted = true;
			
			Integer [] capacity = roomcapacityList.toArray(new Integer[roomcapacityList.size()]);
			//int[] capacity = Ints.toArray(roomcapacityList);
			
			
			for(int i=0;i<capacity.length-1;i++){
				
				if((i<capacity.length-1)&& (capacity[i] <= capacity[i+1]) ){
					
					continue;
				}else if((i+1)==capacity.length||capacity[i+1]==0){
					
					break;
					
				}else{
					
					isSorted = false;
					
				}		
				
			}
				
			return isSorted;
			
		}

		/**
		 * Helper method to get Rooms based on Order By ComboBox Capacity and Reference
		 */
		public String getOrderByComboBox(){

			String xpath = "//div[contains(@class,'x-form-field-wrap') and not(contains(@class,'x-hide-display'))]//input[contains(@name,'orderRoomsBy')]/..//input[last()]";

			return driver.findElement(By.xpath(xpath)).getAttribute("value");
		}
		
		/**
		 * Helper method to check whether New Reservations window is opened.
		 * @return true/false based on presence of New Reservation Window.
		 */
		public boolean isNewResevationWindowVisbile() {
			String xpath ="//table[contains(@class,'x-btn-pressed')]//a[text()='New Reservation']";
			return McsElement.isElementDisplayed(driver,xpath);
		}
		/**
		 * Helper method to get Reservation ID
		 * @return Reservation ID
		 */
		public String getReservationIDInt(){
			String xpath = NEW_RESERVATION_DETAILS_XPATH+"//div[@class='xtb-text info-label']";
			String xpathres = driver.findElement(By.xpath(xpath)).getText();
			return xpathres.replace("Reservation ", "").trim();
		}

		/**
		 * Helper method to click Smaller Button in New Reservation
		 */
		public void clickSmallerButton(){
			driver.findElement(By.xpath("//div[contains(@class,'selectRoomToolbar ')]//table[contains(@class,'btn-search-capacity') and not(contains(@class,'x-item-disabled'))]//button[text()='Smaller']")).click();
			Reporter.log("Smaller Button was clicked <br>", true);
		}

		/**
		 * Helper method to click Bigger Button in New Reservation
		 */
		public void clickBiggerButton(){
			driver.findElement(By.xpath("//div[contains(@class,'selectRoomToolbar ')]//table[contains(@class,'btn-search-capacity') and not(contains(@class,'x-item-disabled'))]//button[text()='Bigger']")).click();
			Reporter.log("Bigger Button was clicked <br>", true);
		}

		/**
		 * Helper method to click All Button in New Reservation
		 */
		public void clickAllButton(){
			driver.findElement(By.xpath("//div[contains(@class,'selectRoomToolbar ')]//table[contains(@class,'btn-search-capacity') and not(contains(@class,'x-item-disabled'))]//button[text()='All']")).click();
			Reporter.log("All Button was clicked <br>", true);
		}

		/**
		 * Helper method to check whether Smaller Button is Enabled or Disabled
		 * @return true/false based on presence of Smaller Button.
		 */
		public boolean isSmallerButtonDisabled(){
			String xpath = "//div[contains(@class,'selectRoomToolbar')]//table[contains(@class,'x-item-disabled')]//button[text()='Smaller']";
			try{
				McsElement.getElementByXpath(driver, xpath);
				return true;
			}
			catch(Exception e){
				return false;
			}
		}

		/**
		 * Helper method to check whether Bigger Button is Enabled or Disabled
		 * @return true/false based on presence of Bigger Button.
		 */
		public boolean isBiggerButtonDisabled(){
			String xpath = "//div[contains(@class,'selectRoomToolbar ')]//table[contains(@class,'x-item-disabled')]//button[text()='Bigger']";
			try{
				McsElement.getElementByXpath(driver, xpath);
				return true;
			}
			catch(Exception e){
				return false;
			}
		}

		/**
		 * Helper method to check whether All Button is Enabled or Disabled
		 * @return true/false based on presence of All Button.
		 */
		public boolean isAllButtonDisabled(){
			String xpath = "//div[contains(@class,'selectRoomToolbar ')]//table[contains(@class,'x-item-disabled')]//button[text()='All']";
			try{
				McsElement.getElementByXpath(driver, xpath);
				return true;
			}
			catch(Exception e){
				return false;
			}
		}

		/**
		 * Helper method to get Next Number in Ascending Order
		 * @param 'a' gives the List of Numbers
		 * @param 'num' gives the number
		 */
		public int nextLargestNumber(Integer a[], int num){
			int nextlargeNum = num;
			for(int i=0; i<a.length;i++){
				if(a[i]<=num){
					continue;
				}
				else{
					nextlargeNum = a[i];
					break;
				}
			}
			return nextlargeNum;
		}

		/**
		 * Helper method to get Next Number in descending order
		 * @param 'a' gives the List of Numbers
		 * @param 'num' gives the number
		 */
		public int nextSmallNumber(Integer a[], int num){
			int nextSmallNum = a[0];
			for(int i=0; i<a.length;i++){
				if(a[i]<num){
					nextSmallNum = a[i];
					continue;
				}
				else if(a[i]==num){
					break;
				}
				else{
					break;
				}
			}
			return nextSmallNum;
		}

		/**
		 * XPATH based locator Method allows to wait, focus and click on a button by
		 * xpath
		 */
		public void waitFocusAndClick(String xpath) {
			WebElement element = driver.findElement(By.xpath(xpath));
			waitWebElement(element);
			waitForExtJSAjaxComplete(20);
			javaScriptFocus(element);
			try{
				   ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
				   javaScriptClick(element);
				  } catch(Exception e){
				   e.printStackTrace();
				  }
		}
		
		/**
		 * XPATH based locator Method allows click on dialog button at confirmation
		 * dialog
		 */
		public void clickOnDialogButton(String buttonText) {
		WebElement element=driver.findElement(By.xpath("//div[contains(@class, 'x-window-dlg')]//button[contains(@class, 'x-btn-text') and text()='" + buttonText + "']"));
			
			try{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
				javaScriptClick(element);
			} catch(Exception e){
				e.printStackTrace();
			}
			Reporter.log("Click on '"+buttonText+"' button on dialog", true);
		}
		/**
		 * XPATH based locator Method allows to wait an click on a button located by
		 * xpath
		 */
		public void waitAndClick(String xpath) {
	                clickXPath(xpath);
		}

        public void clickXPath(String xpath) {
               
		WebElement element=driver.findElement(By.xpath(xpath));
		element.click();
		
        }
        
        /**
         * Helper method to get Participant First Name
         */
        public String getParticipantFirstName() {
        	String xpath = "//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Participants')]/following::input[contains(@name,'firstName')]";
        	String FirstName = driver.findElement(By.xpath(xpath)).getAttribute("value").trim();
        	return FirstName;
        }

        /**
         * Helper method to get Participant Last Name
         */
        public String getParticipantLastName() {
        	String xpath = "//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Participants')]/following::input[contains(@name,'lastName')]";
        	String LastName = driver.findElement(By.xpath(xpath)).getAttribute("value").trim();
        	return LastName;
        }

        /**
         * Helper method to get Participant Company
         */
        public String getParticipantCompany() {
        	String xpath = "//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Participants')]/following::input[contains(@name,'company')]";
        	String Company = driver.findElement(By.xpath(xpath)).getAttribute("value").trim();
        	return Company;
        }

        /**
         * Helper method to get Participant Contact lookup values
         * @return Contact lookup values
         */
        public List<String> getParticipantContactValues(String Name){
        	driver.findElement(By.xpath("//button[contains(@class,'icon-person-medium')]")).click();
        	waitForExtJSAjaxComplete(15);
        	driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//span[text()='Contacts']")).click();
        	waitForExtJSAjaxComplete(10);
        	List<String> list = getValuesFromGridLookup("@id",getXWindowId("Select People"), Name);
        	list.removeAll(Arrays.asList(""));
        	clickCANCELXwindow();
        	return list;
        }

        /**
         * Helper method to get Participant Contact First Name lookup values
         * @return Contact First Name lookup values
         */
        public List<String> getParticipantContactFirstNameValues(){
        	return getParticipantContactValues("First Name");
        }

        /**
         * Helper method to get Participant Contact Last Name lookup values
         * @return Contact Last Name lookup values
         */
        public List<String> getParticipantContactLastNameValues(){
        	return getParticipantContactValues("Last Name");
        }

        /**
         * Helper method to get Participant Contact Company lookup values
         * @return Contact Company lookup values
         */
        public List<String> getParticipantContactCompanyValues(){
        	return getParticipantContactValues("Company");
        }
        
        public void clickReservationsGenSetings(){
        	
        	WebElement element=driver.findElement(By.xpath("//span[contains(text(),'Reservation')]/../../..//span[contains(text(),'General Settings')]"));
        	try{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
				javaScriptClick(element);
			} catch(Exception e){
				e.printStackTrace();
			}
    		Reporter.log("general reservation settings was clicked <br>", true);
    	}	
        
        /**
         * Helper method to return logged in User name in LastName FirstName format
         * @return User name in LastName format
         */
        public String getUserLastNameOfLoggedInUser(){

        	String lastName = getUserNameOfLoggedInUser();
        	String formatedName = lastName.split(" ")[0];
        	return formatedName;
        }
        /**
         *  Method allows to select row from grid in lookup using filters
         * @param attribute - attribute (@class, @id) of the lookup window
         * @param attributeValue - attribute of the lookup must contain this value
         * @param rowTextName - row text to be selected from lookup 
         * @param columnName - columnName of the row to be selected
         */
        public boolean setValueGridLookupWithFiltersWithScrollInToView(String attribute, String attributeValue, String rowTextName, String columnName) {
        
        	Timer timer = new Timer().start();

        	waitForExtJSAjaxComplete(25);

        	waitForExtJSAjaxComplete(25);
        	filterGrid(attribute, attributeValue, rowTextName, columnName);	

        	waitForExtJSAjaxComplete(25);

        	String elementXpath = "(//div[contains("+attribute+",'" + attributeValue + "')]//div[contains(@class,'x-grid3-hd')"
        			+ "and contains(text(),'" + columnName + "')" + "])[last()]";


        	WebElement element = driver.findElement(By.xpath(elementXpath));
        	try{
        		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
        	} catch(Exception e){
        		e.printStackTrace();
        	}
        	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
        	String columnClass = element.getAttribute("class");
        	String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
        	waitForExtJSAjaxComplete(25);
        	
        	try{
        	String []subStrings = rowTextName.split(" ");

    		String subXpathStr = "and starts-with(text(),'"+subStrings[0]+"')";

    		for(int i=1; i<subStrings.length; i++){

    			subXpathStr+="and contains(text(),'"+subStrings[i]+"')";
    		}
    		
        	McsElement.getElementByXpath(driver,"(//"+"div"+"[contains("+attribute+",'" + attributeValue + "')]//"
        			+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
        			+ "and "+"text()"+subXpathStr+
        			"])[last()]").click();

        	waitForExtJSAjaxComplete(25);

        	clickOkXwindow();
        	Reporter.log(rowTextName + " was selected"+ " (" + timer.stop()
        	+ "ms)", true);
        	return true;
		}
		catch(Exception e){
			Reporter.log(rowTextName + " is not present"+ " (" + timer.stop()
					+ "ms)", true);
			return false;
		}
		 finally {
				try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
			}
        }
    
        public void setRecurenceEndDate(String date){
        	McsElement.getElementByXpath(driver, RECURRENCE_WIN_XPATH+RECURRENCE_END_DATE_XPATH).click();
        	McsElement.getElementByXpath(driver, RECURRENCE_WIN_XPATH+RECURRENCE_END_DATE_XPATH).clear();
        	McsElement.getElementByXpath(driver, RECURRENCE_WIN_XPATH+RECURRENCE_END_DATE_XPATH).sendKeys(date);
        	McsElement.getElementByXpath(driver, RECURRENCE_WIN_XPATH+RECURRENCE_END_DATE_XPATH).click();
        }

        public void clickDayRadioButtonInMonthlyTab(){
        	driver.findElement(By.xpath(RECURRENCE_WIN_XPATH+"//input[@name='monthlyMode' and @value='DAY']")).click();
        }

        public void clickWeekRadioButtonInMonthlyTab(){
        	driver.findElement(By.xpath(RECURRENCE_WIN_XPATH+"//input[@name='monthlyMode' and @value='WEEK']")).click();
        }

        public void setDayOfMonthInMonthlyTab(String after){
        	driver.findElement(By.xpath(RECURRENCE_WIN_XPATH+"//input[@name='monthlyDay']")).clear();
        	driver.findElement(By.xpath(RECURRENCE_WIN_XPATH+"//input[@name='monthlyDay']")).sendKeys(after);
        }

        public void setMonthlyIntervalOfDayOptionInMOnthlyTab(String after){
        	driver.findElement(By.xpath(RECURRENCE_WIN_XPATH+"//input[@name='monthlyDayInterval']")).clear();
        	driver.findElement(By.xpath(RECURRENCE_WIN_XPATH+"//input[@name='monthlyDayInterval']")).sendKeys(after);
        }

        public void setMonthlyIntervalOfWeekOptionInMonthlyTab(String after){
        	driver.findElement(By.xpath(RECURRENCE_WIN_XPATH+"//input[@name='monthlyWeekInterval']")).clear();
        	driver.findElement(By.xpath(RECURRENCE_WIN_XPATH+"//input[@name='monthlyWeekInterval']")).sendKeys(after);
        }

        public void selectWeekOfMonthInMonthlyTab(String week){
        	String id= McsElement.getElementByXpath(driver, RECURRENCE_WIN_XPATH+"//input[@name='monthlyWeek']/..//input[contains(@class,'x-form-text')]").getAttribute("id");
        	//DropDown.setComboValue(driver,id,week);
        	DropDown.setComboValueDropDownSelector(driver,id,week);
        	waitForExtJSAjaxComplete(20);
        	waitForExtJSAjaxComplete(20);
        }

        public void selectWeekDayInMonhtlyTab(String week){
        	String id= McsElement.getElementByXpath(driver, RECURRENCE_WIN_XPATH+"//input[@name='monthlyWeekDay']/..//input[contains(@class,'x-form-text')]").getAttribute("id");
        	//DropDown.setComboValue(driver,id,week);
        	DropDown.setComboValueDropDownSelector(driver,id,week);
        	waitForExtJSAjaxComplete(20);
        	waitForExtJSAjaxComplete(20);
        }

        public void clickDayRadioButtonInYearlyTab(){
        	driver.findElement(By.xpath(RECURRENCE_WIN_XPATH+"//input[@name='yearlyMode' and @value='DAY']")).click();
        }

        public void clickWeekRadioButtonInYearlyTab(){
        	driver.findElement(By.xpath(RECURRENCE_WIN_XPATH+"//input[@name='yearlyMode' and @value='WEEK']")).click();
        }

        public void setYearlyIntervalInYearTab(String after){
        	driver.findElement(By.xpath(RECURRENCE_WIN_XPATH+"//input[@name='yearlyInterval']")).clear();
        	driver.findElement(By.xpath(RECURRENCE_WIN_XPATH+"//input[@name='yearlyInterval']")).sendKeys(after);
        }

        public void setDayOfMonthInDayOptionOfYearTab(String after){
        	driver.findElement(By.xpath(RECURRENCE_WIN_XPATH+"//input[@name='yearlyDay']")).clear();
        	driver.findElement(By.xpath(RECURRENCE_WIN_XPATH+"//input[@name='yearlyDay']")).sendKeys(after);
        }

        public void selectMonthInDayOptionOfYearTab(String week){
        	String id= McsElement.getElementByXpath(driver, RECURRENCE_WIN_XPATH+"//input[@name='yearlyMonth']/..//input[contains(@class,'x-form-text')]").getAttribute("id");

        	DropDown.setComboValueDropDownSelector(driver,id,week);
        	waitForExtJSAjaxComplete(20);
        	waitForExtJSAjaxComplete(20);
        }

        public void selectWeekOfMonthOfWeekOptionInYearlyTab(String week){
        	String id= McsElement.getElementByXpath(driver, RECURRENCE_WIN_XPATH+"//input[@name='yearlyWeek']/..//input[contains(@class,'x-form-text')]").getAttribute("id");
        	DropDown.setComboValueDropDownSelector(driver,id,week);
        	waitForExtJSAjaxComplete(20);
        	waitForExtJSAjaxComplete(20);
        }

        public void selectWeekDayOfWeekOptionInYearlyTab(String week){
        	String id= McsElement.getElementByXpath(driver, RECURRENCE_WIN_XPATH+"//input[@name='yearlyWeekDay']/..//input[contains(@class,'x-form-text')]").getAttribute("id");
        	//DropDown.setComboValue(driver,id,week);
        	DropDown.setComboValueDropDownSelector(driver,id,week);
        	waitForExtJSAjaxComplete(20);
        	waitForExtJSAjaxComplete(20);
        }

        public void selectMonthOfWeekOptionInYearlyTab(String week){
        	String id= McsElement.getElementByXpath(driver, RECURRENCE_WIN_XPATH+"//input[@name='yearlyWeekMonth']/..//input[contains(@class,'x-form-text')]").getAttribute("id");
           	DropDown.setComboValueDropDownSelector(driver,id,week);
        	waitForExtJSAjaxComplete(20);
        	waitForExtJSAjaxComplete(20);
        }

        public void setRecurenceWeeklyInterval(String after){
    		driver.findElement(By.xpath(RECURRENCE_WIN_XPATH+"//input[@name='weeklyInterval']")).clear();
    		driver.findElement(By.xpath(RECURRENCE_WIN_XPATH+"//input[@name='weeklyInterval']")).sendKeys(after);
    	}
       
        public boolean uncheckRecurenceWeeklyCheckBox() {

        	String arrayOfDays[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

        	for (String day : arrayOfDays) {

        		String xpath = RECURRENCE_WIN_XPATH+"//label[text()='"+day+"']/preceding-sibling::input[contains(@name,'weeklyDay') and @type='checkbox']";


        		WebElement checkBoxStatus = McsElement.getElementByXpath(driver, xpath);

        		waitForExtJSAjaxComplete(20);

        		if (checkBoxStatus.isSelected()) {
        			checkBoxStatus.click();

        			Reporter.log(day + " is unchecked", true);

        		} 

        	}

        	return McsElement.isElementPresent(driver, RECURRENCE_WIN_XPATH+"//input[@name='errorContainer']");
        }

        public void checkRecurenceWeeklyCheckBox(List<String> days) {

        	for (String day : days) {

        		String xpath = RECURRENCE_WIN_XPATH+"//label[text()='"+day+"']/preceding-sibling::input[contains(@name,'weeklyDay') and @type='checkbox']";


        		WebElement checkBoxStatus = McsElement.getElementByXpath(
        				driver, xpath);

        		waitForExtJSAjaxComplete(20);

        		if (!checkBoxStatus.isSelected()) {
        			checkBoxStatus.click();

        		}

        		Reporter.log(day +" is checked", true);

        	}

        }

       
        /**
         * Helper method to verify more button is available or not
         * @param text
         * @return
         */
        public boolean isButtonAvailable(String text)
        {
        	String xpath="//div[contains(@class,'mcsreservationedit')]//td[contains(@class,'x-toolbar-cell') and not(contains(@class,'x-hide-display'))]//button[text()='"+text+"']";
        	return McsElement.isElementPresent(driver, xpath);
        	
        }
        
        /**
         * Helper method to click more button 
         * @param text
         * @return
         */
        public void clickMoreButton()
        {
        	String xpath=NEW_RESERVATION_DETAILS_XPATH+"//button[text()='More']";
        	
        	driver.findElement(By.xpath(xpath)).click();
        }
        
        /**
    	 * Helper method to click on close button
         * @return 
    	 */
    	
        public boolean isCopyButtonDisabled(){
    		
    		WebElement button = driver.findElement(By.xpath(NEW_RESERVATION_DETAILS_XPATH+"//button[text()='Copy']//ancestor::table[contains(@class,'mcs-btn-confirm')]"));
    		String btnClass=button.getAttribute("class");
    		if(btnClass.contains("x-item-disabled"))
    		{
    			return true;
    		}else{
    			return false;
    		}
    	}
        
        
        /**
    	 * Helper method to get tool tip text for copy button reservation 
    	 * @return tool tip text
    	 */
    	public String getToolTipTextForCopyButton()

    	{
    		
    		Actions action = new Actions(driver);
    		
    		WebElement  rowXpath = driver.findElement(By.xpath(NEW_RESERVATION_DETAILS_XPATH+"//button[text()='Copy']//ancestor::table[contains(@class,'mcs-btn-confirm')]"));
    		
    		int x= 	rowXpath.getSize().getWidth();
    		
    		int y = rowXpath.getSize().getHeight();
    		
    		action.moveToElement(rowXpath).build().perform();
    		
    		waitForExtJSAjaxComplete(10);
    		
    		action.moveToElement(rowXpath,x/2,y/2).build().perform();
    		
    		action.moveToElement(rowXpath,x/6,y/2).build().perform();

    		WebDriverWait wait=new WebDriverWait(driver,30);
    		
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'x-tip') and contains(@style,'visible')]//*[contains(text(),'You are not allowed to add a Reservation.')]")));
    		String toolTipXpath="//div[contains(@class,'x-tip') and contains(@style,'visible')]//*[contains(text(),'You are not allowed to add a Reservation.')]";
    		String toolTipText=driver.findElement( By.xpath(toolTipXpath)).getText();
    		return toolTipText;
    	}
    	
    	/**
    	 * Helper method to verify Reservable tabs are visibled in 'Enter your parameters pane'.
    	 * @param text
    	 * @return
    	 */
    	public boolean isReservableTabVisibl(String tabText){
    		String xpath="//span[contains(@class,'icon-reservable-"+tabText+"')]/../../../..";
    		boolean element=driver.findElement(By.xpath(xpath)).getAttribute("style").contains("");
    		return element;
    	}

    	/**
    	 * Helper method to verify Reservable tabs are not visibled in 'Enter your parameters pane'.
    	 * @param text
    	 * @return
    	 */
    	public boolean isReservableTabsNotVisible(String tabText){
    		String xpath="//span[contains(@class,'icon-reservable-"+tabText+"')]/../../../..";
    		boolean element=driver.findElement(By.xpath(xpath)).getAttribute("style").contains("display: none");
    		return element;
    	}

    	/**
    	 * Helper method to verify Add/Remove button is Enabled in OrderItems
    	 * @param text
    	 * @return
    	 */
    	public boolean isAddAndRemoveButtonEnabled(String tabText){

    		String xpath="//div[contains(@class,'panel-add-remove')]//table[contains(@class,'button-medium') and not(contains(@class,'x-item-disabled'))]//button[text()='"+tabText+"']";
    		return McsElement.isElementPresent(driver, xpath);
    	}

    	/**
    	 * Helper method to verify Add/Remove button is disabled in OrderItems
    	 * @param text
    	 * @return
    	 */
    	public boolean isAddAndRemoveButtonDisabled(String tabText){

    		String xpath="//div[contains(@class,'panel-add-remove')]//table[contains(@class,'button-medium') and contains(@class,'x-item-disabled')]//button[text()='"+tabText+"']";
    		return McsElement.isElementPresent(driver, xpath);
    	}

    	/**
    	 * Helper method to click Add/Remove button in OrderItems
    	 */
    	public void clickAddRemoveOrderItem(){
    		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//span[contains(text(),'Add/Remove')]")).click();
    		waitForExtJSAjaxComplete(30);
    	}
    	
    	/**
		 * Helper method to hidden copy button  
		 */
		public boolean isCopyButtonDisplayed(){
			String xpath="//button[ contains(@class,'x-btn-text') and text()='Copy']//ancestor::td[contains(@class,'x-toolbar-cell') and not(contains(@class,'x-hide-display'))]";
			return McsElement.isElementPresent(driver, xpath);
		}
		
		/**
		 * Helper method to get the list of Announced participants from participants tab.
		 */
		public List<String> getListOfParticipants(){
			List<String> availableParticipants = new ArrayList<String>();
			List<WebElement> Participants =driver.findElements(By.xpath("//div[contains(@class,'mcsreservationedit')]/../..//div[contains(text(),'Select a participant for details')]/..//div[contains(@class,'x-grid3-cell-inner x-grid3-col-0')]"));
			for(WebElement ParticipantsList:Participants ){

				availableParticipants.add(ParticipantsList.getAttribute("textContent").trim());
			}
			System.out.println(availableParticipants);
			return availableParticipants;
		}
		
		/**
		 * Helper method to check visibility of check box
		 * @param checkBoxName to be verified
		 * @return status
		 */
		public boolean isReservableCheckBoxVisible(String checkBoxName){
			String checkBoxXpath = "//div[contains(@class,'mcsreservationedit')]//input[@name='"+checkBoxName+"']/..";
			checkBoxName = "confidential";
			String className = driver.findElement(By.xpath(checkBoxXpath)).getAttribute("class");
			boolean checkBoxStatus = (className.contains("x-hide-display")?false:true );
			return checkBoxStatus;
		}

		/**
		 * Helper method to get the status of lookup field
		 * @param lookupName to get the status
		 * @return status of lookup
		 */
		public boolean isLookupEnabled(String lookupName){
			WebElement  lookUpXpath = driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//input[@name='"+lookupName+"']//ancestor::div[contains(@class,'mcslookup')]"));
			String classAttrVal =lookUpXpath.getAttribute("class");
			return (classAttrVal.contains("hide-display"))?false:true;
		}

		/**
		 * Helper method to get the status of TextArea 
		 * @param fieldName to get the status
		 * @return status of TextArea
		 */
		public boolean isReservableTextAreaDisplayed(String fieldName){
			WebElement fieldXpath = driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//textarea[contains(@class,'x-form-textarea') and @name='remarks']"));
			String classAttrVal =fieldXpath.getAttribute("class");
			return (classAttrVal.contains("hide-offsets"))?false:true;
		}

		/**
		 * Helper method to get the status of label
		 * @param labelName to get the status
		 * @return status of label
		 */
		public boolean isReservableLabelDisplayed(String labelName){
			WebElement  lookUpXpath = driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//div[(contains(@class,'x-box-item') or (@class='x-form-item')) and contains(.,'"+labelName+"')]"));
			String classAttrVal =lookUpXpath.getAttribute("class");
			return (classAttrVal.contains("hide-display"))?false:true;
		}
		
		/**
		 * Helper method to delete reservation
		 */
		public void deleteReservationFromMyReservation(String date,String from,String item,boolean isReservationDone)
		{
			ReservationMyReservationsPageObject myReserv=new ReservationMyReservationsPageObject();
			if(isReservationDone=true){
				
				navigateToMainPage();
				
				waitForMaskDisappear();
				
				expandAdministration();
				
				waitForExtJSAjaxComplete(20);

				expandSubMainMenu("Reservation");
				
				waitForExtJSAjaxComplete(20);
				
				waitAndClick(XPATH_MYRESERVATIONS);
				
				waitForExtJSAjaxComplete(20);
				
				waitForExtJSAjaxComplete(20);
				
				myReserv.setTodayDateInMyReservation(date);
				
				waitForExtJSAjaxComplete(20);
				
				myReserv.clickSearchButton();
				
				waitForExtJSAjaxComplete(20);
				
				checkRowInGriByTextValueAndClick(date+" "+from,item);
				
				waitForExtJSAjaxComplete(20);
				
				clickViewCancel();
				
				waitForExtJSAjaxComplete(20);
				
				clickGeneralTab();
				
				waitForExtJSAjaxComplete(10);
				
				clickCancelReservation();
				
				waitForExtJSAjaxComplete(10);
				
				clickOnDialogButton("Yes");
				}
				
				else{
						System.out.println("Reservation is not done");
					}
		}
}
