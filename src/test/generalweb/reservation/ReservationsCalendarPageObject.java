/**
 *  ReservationsCalendarPageObject is container for Calendar tab related tests
 *	author: vpcc
*/
package test.generalweb.reservation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import test.configuration.Configuration;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;


public class ReservationsCalendarPageObject extends ReservationPageObject {

	
	protected final String CALENDAR_CLASS_XPATH = "//div[contains(@class,'rsrvcalendar')]";
	
	protected final String CALENDAR_GRID_XPATH = "//div[contains(@class,'sch-schedulerpanel')]";
	
	protected final String CALENDAR_ROOM_TAB_XPATH = "//div[contains(@class,'rsrvcalendar')]//span[contains(@class,'icon-reservable-room') and text()='Rooms']";
	
	protected final String CALENDAR_PARKING_TAB_XPATH = "//div[contains(@class,'rsrvcalendar')]//span[contains(@class,'icon-reservable-parking') and text()='Parking']";
	
	protected final String CALENDAR_VEHICLE_TAB_XPATH = "//div[contains(@class,'rsrvcalendar')]//span[contains(@class,'icon-reservable-vehicle') and text()='Vehicles']";
	
	protected final String CALENDAR_EQUIPMENT_TAB_XPATH = "//div[contains(@class,'rsrvcalendar')]//span[contains(@class,'icon-reservable-equipment') and text()='Equipment']";
	
	protected final String CALENDAR_REGION_XPATH = "//div[contains(@class,'rsrvcalendar')]//div[contains(@class,'x-tab-panel-body-top')]/div[contains(@class,'x-panel x-panel-noborder') and not(contains(@class,'x-hide-display'))]//div[ @class='xtb-text' and text()='Region:']/../..//input[contains(@class,'x-form-field')]";

	protected final String CALENDAR_RESERVATIONS_POPUP= "//div[contains(@class,'x-window reservations x-resizable-pinned')]";
	
	protected final String CALENDAR_ROOMS_GRID = "//div[@realid='rsrv_front_cal_room']";
	
	protected final String CALENDAR_PARKING_GRID = "//div[@realid='rsrv_front_cal_parking']";
	
	protected final String CALENDAR_EQUIPMENT_GRID = "//div[@realid='rsrv_front_cal_equipment']";
	
	protected final String CALENDAR_VEHICLE_GRID = "//div[@realid='rsrv_front_cal_vehicle']";

	
	//TO DO: This code is not working for new changes in Calendar tab
/*	
	*//**
	 * Helper method to click on Rooms tab in Calendar 
	 *//*
	public void clickRoomsTabInCalendarTab(){
		McsElement.getElementByXpath(driver, CALENDAR_ROOM_TAB_XPATH).click();
		Reporter.log("Rooms tab in Calendar page was clicked <br>", true);
	}	
	
	*//**
	 * Helper method to click on Parking tab in Calendar 
	 *//*
	public void clickParkingTabInCalendarTab(){
		McsElement.getElementByXpath(driver, CALENDAR_PARKING_TAB_XPATH).click();
		Reporter.log("Parking tab in Calendar page was clicked <br>", true);
	}
	
	*//**
	 * Helper method to click on Vehicles tab in Calendar 
	 *//*
	public void clickVehiclesTabInCalendarTab(){
		McsElement.getElementByXpath(driver, CALENDAR_VEHICLE_TAB_XPATH).click();
		Reporter.log("Vehicles tab in Calendar page was clicked <br>", true);
	}
	
	*//**
	 * Helper method to click on Equipment tab in Calendar 
	 *//*
	public void clickEquipmentTabInCalendarTab(){
		McsElement.getElementByXpath(driver, CALENDAR_EQUIPMENT_TAB_XPATH).click();
		Reporter.log("Equipment tab in Calendar page was clicked <br>", true);
	}
	
	*//**
	 * Helper method to Region in Calendar Tab
	 *//*
	public void setRegionInCalendarTab(String region){
		
		  WebDriverWait wait = new WebDriverWait(driver, 20);
		  
		  wait.until(ExpectedConditions.elementToBeClickable(By
		    .xpath(CALENDAR_CLASS_XPATH+"//div[contains(@class,'x-panel-noborder')and not(contains(@class,'x-hide-display'))]//div[contains(@class,'xtb-text') and text()='Region:']//..//..//button")));

		  clickLookupButtonBasedOnLabel("Region:");

		  waitForExtJSAjaxComplete(30);

		  setValueGridLookupWithFiltersWithScrollInToView("@class", "x-resizable", region,"Reference");
		  
		String id = McsElement.getElementByXpath(driver, CALENDAR_REGION_XPATH).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, region);
		
		Reporter.log(region+ " is set as region", true);
	}
	
		 
	
	public void setDateInCalendarTab(String inpValue)
	{
		String xpath= CALENDAR_CLASS_XPATH+"//div[contains(@class,'x-panel x-panel-noborder') and not(contains(@class,'x-hide-display'))]//tr[contains(@class,'x-toolbar-right-row')]//..//input[contains(@type,'text')]";
		
		WebElement textField = McsElement.getElementByXpath(driver, xpath);

		textField.clear();
		textField.sendKeys(inpValue);
		textField.sendKeys(Keys.RETURN);
		waitForExtJSAjaxComplete(5);
		clickOnRefreshButtonInCalendarTab();
	}
	
	public void clickOnRefreshButtonInCalendarTab()
	{
		Actions action=new Actions(driver);
		String xpath= CALENDAR_CLASS_XPATH+"//div[contains(@class,'x-panel x-panel-noborder') and not(contains(@class,'x-hide-display'))]//tr[contains(@class,'x-toolbar-right-row')]//..//button[contains(@class,'x-tbar-loading')]";
		WebElement element=McsElement.getElementByXpath(driver, xpath);
		action.doubleClick(element).perform();
	}
	
	public void clickOnCalendarViews(String value){
		
		WebElement element=driver.findElement(By.xpath(CALENDAR_CLASS_XPATH+"//div[contains(@class,'x-panel x-panel-noborder') and not(contains(@class,'x-hide-display'))]//tr[contains(@class,'x-toolbar-right-row')]//..//button[contains(@type,'button') and text()='"+value+"']"));
	
		try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
        	e.printStackTrace();
        }
	((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	waitForExtJSAjaxComplete(20);
	
	}*/
	
	/*/**
	 * Helper method to get Date range of Grid in Calendar tab
	 * @return list of Date range of Grid in Calendar tab
	 
	public List<String> getCalendarGridDateRangesFromHeader(){
		String xpath = CALENDAR_CLASS_XPATH+"//div[contains(@class,'x-grid3-header-quickfilters')]//tr[contains(@class,'x-grid3-header')]";
			List<String> dateRanges = new ArrayList<String>();
		
		try{
			
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		List<WebElement> gridRows = driver.findElements(By.xpath(xpath));
		
		List<WebElement> dateRangeEles = gridRows.get(0).findElements(By.xpath(".//td[contains(@class,'sch-timeheader')]"));
		
		for(WebElement dateRange: dateRangeEles){
			
			dateRanges.add(dateRange.getText().trim());
		}
		
		}finally{
			
			driver.manage()
			.timeouts()
			.implicitlyWait(configuration.getImplicitWait(),
					TimeUnit.SECONDS);
			
		}
		
		return dateRanges;
		
	}
	
	*//**
	 * Helper method to get time range of Grid in Calendar tab
	 * @return list of time range of Grid in Calendar tab
	 *//*
	public List<String> getCalendarGridTimeRangeFromHeader(){
		
		String xpath = CALENDAR_CLASS_XPATH+"//div[contains(@class,'x-grid3-header-quickfilters')]//tr[contains(@class,'x-grid3-header')]";
		
		List<String> timeRanges = new ArrayList<String>();
		
		try{
			
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			
		List<WebElement> gridRows = driver.findElements(By.xpath(xpath));
		
		List<WebElement> timeRangeEles = gridRows.get(1).findElements(By.xpath(".//td[contains(@class,'sch-timeheader')]"));
		
		try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", timeRangeEles.get(timeRangeEles.size()-1));
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		for(WebElement timeRange: timeRangeEles){
			
			timeRanges.add(timeRange.getText().trim());
			
		}
		}
		finally{
			
			driver.manage()
								.timeouts()
								.implicitlyWait(configuration.getImplicitWait(),
										TimeUnit.SECONDS);
		}
		return timeRanges;
	}
	*//**
	 * Helper method to click on reservation in calendar tab
	 * @param property
	 *//*

	public void clickReservationInCalendar(String property)
	{
	String xpath=CALENDAR_GRID_XPATH+"//*[@class='x-grid3-body']//div[text()='"+property+"']//ancestor::table[@class='x-grid3-row-table']//td[contains(@class, 'sch-timetd')]//div[contains(@class, 'myreservation')]";
	
	McsElement.getElementByXpath(driver, xpath).click();
	}
	
	public boolean isReservationCancelled(String property)
	{
		String xpath=CALENDAR_GRID_XPATH+"//*[@class='x-grid3-body']//span[contains(@style, 'text-decoration') and text()='"+property+"']//ancestor::table[@class='x-grid3-row-table']//td[contains(@class, 'sch-timetd')]//div[contains(@class, 'myreservation')]";
		
		try{
			
		McsElement.getElementByXpath(driver, xpath);
		
		return true;
	}
	catch(Exception e){
		return false;
	}
	}
	
	*//**
	 * Helper method to filter by name
	 *//*
	public void filterItemByName(String attribute, String attributeValue, String rowTextName, String columnName)
	{
		
		filterGrid(attribute, attributeValue, rowTextName, columnName);
	}
	
	*//**
	 * Helper method to get tool tip text of unavailable reservation 
	 * @return tool tip text
	 *//*
	public String getToolTipTextOfUnAvailableReservation(String value)

	{
		ReservationsCalendarNewPageObject calendarPageObj=new ReservationsCalendarNewPageObject();
		Actions action = new Actions(driver);
		WebElement xpath =driver.findElement(By.xpath(calendarPageObj.CALENDAR_RESOURCE_WIN_XPATH+"//div[text()='"+value+"']//ancestor::div[contains(@id,'rsrv_scheduler-1238-body')]//div[contains(@class,'sch-resourcezone sch-timespangroup')]"));
		
		action.moveToElement(xpath).build().perform();
		
		waitForExtJSAjaxComplete(5);
		
		String toolTipXpath="//div[contains(@class,'x-tip sch-tip')]//*[@class='rsrvcalendar-eventTip']";
		
		return driver.findElement( By.xpath(toolTipXpath)).getText();
	}
	
	/**
	 * Helper method to select the name as Rooms in Calendar.
	 * @return Name of the Item and time.
	 
	public List<String> findRoomsFreeForGivenTime(String time){

		return getReservableItemsAvailableForTimeFromCalendar("Rooms", time);
	}

	*//**
	 * Helper method to select the name as Equipment in Calendar.
	 * @return Name of the Item and time.
	 *//*
	public List<String> findEquipmentFreeForGivenTime(String time){

		return getReservableItemsAvailableForTimeFromCalendar("Equipment", time);
	}*/

	 /**
	 * Helper method to get the Reserved Items From calendar Tab.
	 * @param reseravableItem : Name of the Item to select.
	 * @param time : Time given for Item.
	 *//*

	public List<String> getReservableItemsAvailableForTimeFromCalendar(String reseravableItem, String time){

		String reservableItems[] = {"rooms","parking","equipment","vehicles"};

		List<String> reservableItemsList = Arrays.asList(reservableItems);

		List<String> availableReservableItems = new ArrayList<String>();

		int i = reservableItemsList.indexOf(reseravableItem.toLowerCase());

		String xpath = "";
		switch(i){

		case 0: {
			xpath = CALENDAR_ROOMS_GRID;
			break;
		}

		case 1: {
			xpath = CALENDAR_PARKING_GRID;
			break;
		}

		case 2: {
			xpath = CALENDAR_EQUIPMENT_GRID;
			break;
		}

		case 3: {
			xpath = CALENDAR_VEHICLE_GRID;
			break;
		}
		default:{

			Reporter.log("Invalid input: "+reseravableItem, true);
			throw new IllegalArgumentException("Invalid input: "+reseravableItem);

		} }

		String columnClass = driver.findElement(By.xpath(xpath+"//td[contains(@class,'sch-timeheader')]/div[text()='"+time+"']/..")).getAttribute("class");


		String columnNameContainingString = "x-grid3-td-"; //x-grid3-td-16
		int startIndex = columnClass.indexOf(columnNameContainingString)+columnNameContainingString.length();
		int endIndex = startIndex+2;
		String columnNumber = columnClass.substring(startIndex, endIndex).trim();
		


	try{
			
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
 
		List<WebElement> eleList = driver.findElements(By.xpath(xpath+"//td[contains(@class,'x-grid3-td-"+columnNumber+"  sch-timetd')]/div"));

		for(WebElement ele: eleList){
			
			boolean isAvaialable = true;
			
			//Check if there is already a booking made for the specified time
			try{

				ele.findElement(By.xpath("./div"));

				isAvaialable = false;

			}		
			catch(Exception e){}
			//Check if reservable element is unavailable
			
			try{
				
				ele.findElement(By.xpath("./ancestor::div[contains(@class,'x-grid3-row') and contains(@class,'calendar-row-readonly')]"));
				isAvaialable = false;
			}catch(Exception e){}
			
			
			WebElement reservableItemEle = ele.findElement(By.xpath("./ancestor::tr/td[contains(@class,'x-grid3-td-0')]//div"));
			
			if(isAvaialable){

				availableReservableItems.add(reservableItemEle.getText());

			}else{
				Reporter.log(reservableItemEle.getText()+ " is booked for time period" +time);
			}

		}

		}finally{
			
			driver.manage()
			.timeouts()
			.implicitlyWait(configuration.getImplicitWait(),
					TimeUnit.SECONDS);
			
		}
		System.out.println(availableReservableItems);
		return availableReservableItems;

	}

	*//**
	 * Helper method to filter the reservables based on category 
	 * @param category
	 *//*
	public void filterByCategory(String category ){

		String columnClass = McsElement.getElementByXpath(driver,"(//div[contains(@class,'x-grid3-hd') and contains(text(),'Category')])[last()]").getAttribute("class");

		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);

		String id = McsElement.getElementByXpath(driver,CALENDAR_ROOMS_GRID+"//tr[@class='quick-filter-row']//div[contains(@id,'filter-"+columnNumber+"')]//input").getAttribute("id");

		waitForExtJSAjaxComplete(5);

		DropDown.setComboValueDropDownSelector(driver, id, category);

		waitForExtJSAjaxComplete(20);
	}

	*//**
	 * Helper method to get region in CalenderTab
	 * @return
	 *//*
	public String getRegionInCalendarTab(){
		return driver.findElement(By.xpath("//div[contains(@class,'x-panel-noborder')and not(contains(@class,'x-hide-display'))]//div[contains(@class,'xtb-text') and text()='Region:']//ancestor::tr[@class='x-toolbar-left-row']//input")).getAttribute("value");
	}

	*//**
	 * Helper method to verify Grid value in calendar is Present or not
	 * @return true or false
	 *//*
	public static boolean isRowInGridByTextValueVisibleInCalendar(String textValue)  {
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//*[@class='x-grid3']//span[text()='"+textValue+"']"));
			return false;
		} catch (Exception e) {
			return true;
		} finally {
			try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
		}
	}
	
	public void clickRefreshButtonInCalendarTab(){

		McsElement.getElementByXpath(driver, CALENDAR_CLASS_XPATH+"//div[contains(@class,'x-panel-tbar-noheader')]//button[contains(@class,'x-tbar-loading')]").click();
	}*/
	
	/**
	 * Get text of reservation item window  text in Calendar tab
	 * @return reservation item details  window text in Calendar tab
	 *//*
	public String getReservationsPopUpContent(){
		
		String content = McsElement.getElementByXpath(driver, CALENDAR_RESERVATIONS_POPUP+"//div[@class='x-window-body']").getText();
		
		return content;//getWarningDialogTextMsg();
		
	}*/
	
	public String getPropertyFromSring(String content , String property){
		
		int startIndex = content.indexOf(property); 
		
		startIndex+=property.length();
		
		int endIndex = content.indexOf("\n", startIndex);
	
		if(endIndex==-1){
			
			endIndex = content.length();
		}
		
		String popUpContent= content.substring(startIndex, endIndex).trim();
		
		System.out.println(popUpContent);
		
		return popUpContent;
	}
	/**
	 * Helper method to get category from calendar tab
	 * @return
	 *//*
	public Set<String> getCategoryFromCalendarTab(){
		List<String> CategoryList = new ArrayList<String>();

		String columnClass = McsElement.getElementByXpath(driver,"(//div[contains(@class,'x-grid3-hd') and contains(text(),'Category')])[last()]").getAttribute("class");

		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);

		List<WebElement> result = driver.findElements(By.xpath("//div[contains(@class,'rsrvcalendar')]//div[contains(@class,'x-grid3-scroller')]//div//td[contains(@class,'x-grid3-td-"+columnNumber+"')]//div"));
	
		for (WebElement Category : result){
			CategoryList.add(Category.getText());
		}
		Set<String> set = new HashSet<String>(CategoryList);
		System.out.println(set);
		return set;
	}*/

	/**
	 * Helper method to click on Look Up button when input doesn't have @name
	 * attribute
	 *//*

	public void clickLookupButtonBasedOnLabel(String fieldLabel) {
		McsElement.getElementByXpath(driver, CALENDAR_REGION_XPATH + "//button").click();
	}*/

	public void clickCalendarGridRowByName(String rowValue) {

		String xpath = CALENDAR_GRID_XPATH + "//*[@class='x-grid3']//div[text()='" + rowValue + "']";

		McsElement.getElementByXpath(driver, xpath).click();

	}

	public void clickOKInReservationsPopupWindow() {

		McsElement.getElementByXpath(driver, CALENDAR_RESERVATIONS_POPUP + "//button[text()='OK']").click();
	}
}
