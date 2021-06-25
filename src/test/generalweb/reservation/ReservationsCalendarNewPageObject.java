/**
 * Rewritting Calendar Page Object to handle new UI changes
 */
package test.generalweb.reservation;

import java.awt.AWTException;
import java.awt.Robot;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.ExpectedExceptions;

import com.google.common.base.Function;

import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

/**
 * @author vpcc
 *
 */
public class ReservationsCalendarNewPageObject extends
ReservationsCalendarPageObject {

	protected final String CALENDAR_WIN_XPATH = "//div[contains(@class,'rsrvcalendar sch-timelinepanel')]";

	protected final String CALENDAR_GRID_XPATH = CALENDAR_WIN_XPATH+"//div[contains(@class,'sch-animations-enabled')]//div[@class='x6-grid-item-container']";

	protected final String CALENDAR_RESOURCE_GRID_XPATH = CALENDAR_WIN_XPATH+"//div[contains(@class,'rsrvcalendar-resource-grid')]//div[contains(@id,'locked-body')]";
	
	protected final String CALENDAR_RESOURCE_WIN_XPATH = CALENDAR_WIN_XPATH+"//div[contains(@class,'rsrvcalendar-resource-grid')]";
	
	
	protected final String CALENDAR_REGION_XPATH = CALENDAR_WIN_XPATH+"//div[@class='mcslookup']";
	
	protected final String CALENDAR_DATE_XPATH = CALENDAR_WIN_XPATH+ "//div[contains(@id,'datefield')]//input";

	protected final String CALENDAR_RESERVATIONS_POPUP= "//div[contains(@class,'x-window resource-details-window') and contains(@style,'visibility: visible')]";
	
	protected final String CALENDAR_RESERVATIONS_EVENT = "//div[@class='reservationTip']";
	
	protected final String CALENDAR_NEW_RESERVATION_BUTTON_DRAG = "//div[contains(@id,'menu') and @role='menu' and not(contains(@style,'visibility: hidden'))]//a";

	protected final String CALENDER_REGION_CLASS ="//div[contains(@class,'x-mcs-lookup-view')]";

	/**
	  * Helper method to click on Date icon
	  */
	 public void clickDateIcon()
	 {
	  WebDriverWait waitFor=new WebDriverWait(driver, 40);
	  waitFor.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'sch-schedulerpanel')]//div[contains(@id,'datefield') and contains(@data-ref,'inputWrap')]//following-sibling::div")));
	  String xpath="//div[contains(@class,'sch-schedulerpanel')]//div[contains(@id,'datefield') and contains(@data-ref,'inputWrap')]//following-sibling::div";
	  WebElement date = driver.findElement(By.xpath(xpath));
	  date.click();
	  
	 }
	
	/**
	 * Helper method to click on Rooms tab in Calendar 
	 */
	public void clickRoomsTabInCalendarTab(){

		clickTabOrButtonsInCalendarToolBarOnTop("room","@class");
	}	

	/**
	 * Helper method to click on Parking tab in Calendar 
	 */
	public void clickParkingTabInCalendarTab(){

		clickTabOrButtonsInCalendarToolBarOnTop("parking","@class");

	}

	/**
	 * Helper method to click on Vehicles tab in Calendar 
	 */
	public void clickVehiclesTabInCalendarTab(){
		clickTabOrButtonsInCalendarToolBarOnTop("vehicle","@class");
	}

	/**
	 * Helper method to click on Equipment tab in Calendar 
	 */
	public void clickEquipmentTabInCalendarTab(){

		clickTabOrButtonsInCalendarToolBarOnTop("equipment","@class");

	}

	
	/**
	 * Helper method to change Calendar Views
	 * @param value: 'View Week' or 'View 24/7'
	 */
	public void clickOnCalendarViews(String value){
		
		clickTabOrButtonsInCalendarToolBarOnTop(value,"text()");
	
	}


	/**
	 * Helper method to click on tab
	 * @param tabOrButton to be clicked
	 */
	private void clickTabOrButtonsInCalendarToolBarOnTop(String tabOrButton,String attributeName){

		String xpath = CALENDAR_WIN_XPATH+"//div[contains(@class,'x6-toolbar-default-docked-top')]//span[contains("+attributeName+",'"+tabOrButton+"')]";

		McsElement.getElementByXpath(driver, xpath).click();

		Reporter.log(tabOrButton+" tab or button in Calendar page was clicked <br>", true);

	}
	
	/**
	 * Helper method to ckick on refresh button
	 */
	public void clickOnRefreshButton()
	{
		String xpath=CALENDAR_WIN_XPATH+"//div[contains(@class,'x6-toolbar-default-docked-top')]//span[contains(@class,'x-tbar-loading')]";
		McsElement.getElementByXpath(driver, xpath).click();
	}
	
	
	


	/**
	 * Helper method to Region in Calendar Tab
	 */
	public void setRegionInCalendarTab(String region){

		WebDriverWait wait = new WebDriverWait(driver, 40);

		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath(CALENDAR_REGION_XPATH+"//button")));
		
		McsElement.getElementByXpath(driver, CALENDAR_REGION_XPATH+"//button").click();
		

		waitForExtJSAjaxComplete(30);
		
		this.setValueGridLookupWithFiltersWithScrollInToView("@class", "x-resizable", region,"Reference");

		Reporter.log(region+ " is set as region", true);
	}

	/**
	 * Helper method to get region in CalenderTab
	 * @return region name set in Calendar tab
	 */
	public String getRegionInCalendarTab(){
		
		return McsElement.getElementByXpath(driver, CALENDAR_REGION_XPATH+"//input").getAttribute("value");
	}
	
	
	//***************************************Resource grid methods**************************************************************
	
	/**
	 * Helper method to get category from calendar tab
	 * @return
	 */
	public Set<String> getCategoryFromCalendarTab(String columnName,String tabName){
	

		String columnNumber = (columnName.toLowerCase().equals("name")? "[1]": columnName.toLowerCase().equals("capacity")?  "[3]": "[2]");
		
		String xpath = "//span[contains(@class,'icon-reservable-"+tabName+"')]//ancestor::a[contains(@class,'x6-toolbar-item') and contains(@class,'x6-btn-pressed')]//ancestor::div[contains(@class,'rsrvcalendar sch-timelinepanel')]//tr[contains(@class,'x6-grid-row')]/td"+columnNumber+"//div[contains(@class,'x6-grid-cell-inner')]";
		System.out.println(xpath);
		List<WebElement> result = driver.findElements(By.xpath(xpath));
	
		List<String> CategoryList = new ArrayList<String>();
		
		for (WebElement Category : result){
			CategoryList.add(Category.getText());
			
		}
		Set<String> set = new HashSet<String>(CategoryList);

		return set;
	}
	
	/**
	 * Helper method to select a Reservable resource 
	 *@param resourceName: Resource Name to be selected
	 */
	public void clickCalendarGridRowByName(String resourceName){
		
		getGridElement("Name", resourceName).click();
		
	}
	
	/**
	 * Helper method to get WebElement of Reservable resource in grid based on column name passed. 
	 * Incase of multiple matches For example when we use Category or Capacity column values there is a possibility for multiple matches.
	 * In multiple match scenarios, this method will always return first match found 
	 * @param columnName to be used for filtering
	 * @param rowValue : resource name
	 * @return WebElement representing Reservable Resource
	 */
	private WebElement getGridElement(String columnName, String rowValue){
		
		String columnNumber = (columnName.toLowerCase().equals("name")? "[1]": columnName.toLowerCase().equals("capacity")?  "[3]": "[2]");
		
		String xpath = CALENDAR_RESOURCE_GRID_XPATH + "//tr[contains(@class,'x6-grid-row')]/td"+columnNumber+"//div[contains(.,'"+rowValue+"')]";
		
		return McsElement.getElementByXpath(driver, xpath);
		
	}
	
	
	/**
	 * Helper method to get data-record id of a reservable resource based on Resource Name
	 * @param resourceName whose data record id has to be obtained
	 * @return data-record id
	 */
	public String getDataRecordIDOfReservableResource(String resourceName){
		
		try{
		WebElement resource = getGridElement("Name", resourceName);
		waitForExtJSAjaxComplete(20);
		WebElement parentRow = resource.findElement(By.xpath("./ancestor::table[contains(@class,'x6-grid-item')]"));	
		return parentRow.getAttribute("data-recordid");
		}
		
		catch(org.openqa.selenium.StaleElementReferenceException ex){
			waitForMaskDisappear();
			WebElement resource = getGridElement("Name", resourceName);
			WebElement parentRow = resource.findElement(By.xpath("./ancestor::table[contains(@class,'x6-grid-item')]"));
			
			return parentRow.getAttribute("data-recordid");
			}
		
		
	}
	/**
	 * Helper method to get all available resources name in calander page
	 * @return
	 */
	
	public List<String> getResourceNames(){
		
			
			ArrayList<String> lsValues = new ArrayList<String>();
			String xpath=CALENDAR_RESOURCE_WIN_XPATH+"//div[contains(@id,'rsrv_scheduler-1024-locked-body')]//td[contains(@class,'x6-grid-cell-first')]//div";
			List<WebElement> values=driver.findElements(By.xpath(xpath));
			for(int i=0;i<values.size(); i++){
				
				String valueText = values.get(i).getText().trim();
				
				lsValues.add(valueText);
			}
			return lsValues;
			
		}
		
	
	/**
	 * Helper method to filter the reservable object based on Name 
	 * @param name of Reservable object
	 */
	public void filterItemByName(String name)
	{
		
		filterResource("Name", name);
	}
	
	
	/**
	 * Helper method to filter the reservable object based on Capacity 
	 * @param capacity of reservable object
	 */
	public void filterByCapacity(String capacity ){
		
		filterResource("Capacity", capacity);

	}
	
	/**
	 * Helper method to filter the reservable object based on category 
	 * @param category of reservable object
	 */
	public void filterByCategory(String category ){
		
		filterResource("Category", category);

	}
	
	/**
	 * Helper method to filter Reservable resources. 
	 * @param columnName : enter column on which filtering has to be done
	 * @param valuetoFilter : value to search for
	 */
	private void filterResource(String columnName, String valuetoFilter){
		
		String xpath = "("+CALENDAR_RESOURCE_WIN_XPATH+"//div[contains(@class,'x6-plugin-quick-filter-container')]//input[@type='text' and @aria-hidden='false'])";
		
		xpath += (columnName.toLowerCase().equals("name")? "[1]": columnName.toLowerCase().equals("capacity")?  "[3]": "[2]");
		
		WebElement quickFilterField = McsElement.getElementByXpath(driver, xpath);
		
		quickFilterField.clear();
		
		quickFilterField.sendKeys(valuetoFilter);
		
		quickFilterField.sendKeys(Keys.RETURN);
		
		waitForExtJSAjaxComplete(5);
		
	}
	
	
	
	//****************************************Calendar grid method***************************************************************
	
	/**
	 * Helper method to get Time range of Grid in Calendar tab
	 * @return list of Time range of Grid in Calendar tab
	 */
	public List<String> getCalendarGridTimeRangeFromHeader(String weekPressed,String twentyFourBySevenPressed){

		
		return getCalendarGridRangesFromHeader("time", weekPressed,twentyFourBySevenPressed);
	}
	
	
	/**
	 * Helper method to get Date range of Grid in Calendar tab
	 * @return list of Date range of Grid in Calendar tab
	 */
	public List<String> getCalendarGridDateRangesFromHeader(String weekPressed,String twentyFourBySevenPressed){
		
		return getCalendarGridRangesFromHeader("date",weekPressed,twentyFourBySevenPressed);
		
	}
	
	
	/**
	 * Helper method to get Date range of Grid in Calendar tab
	 * @return list of Date range of Grid in Calendar tab
	 */
	private List<String> getCalendarGridRangesFromHeader(String dateOrTime,String weekPressed,String twentyFourBySevenPressed){
	

		String xpath="";
		
			if((weekPressed=="true")&&(twentyFourBySevenPressed=="true"))
			{
				xpath="//div[contains(@class,'x6-toolbar-default-docked-top')]//span[contains(text(),'View Week')]//ancestor::a[contains(@class,'x6-btn-default-toolbar-small') and contains(@aria-pressed,'true')]//following-sibling::a[contains(@class,'x6-btn-default-toolbar-small') and contains(@aria-pressed,'true')]//ancestor::div[contains(@class,'rsrvcalendar sch-timelinepanel')]//div[contains(@id,'timeaxis')]//table[contains(@class,'sch-header-row')";
			}else if((weekPressed=="false")&&(twentyFourBySevenPressed=="true"))
			{
				xpath=" //div[contains(@class,'x6-toolbar-default-docked-top')]//span[contains(text(),'View Week')]//ancestor::a[contains(@class,'x6-btn-default-toolbar-small') and contains(@aria-pressed,'false')]//following-sibling::a[contains(@class,'x6-btn-default-toolbar-small') and contains(@aria-pressed,'true')]//ancestor::div[contains(@class,'rsrvcalendar sch-timelinepanel')]//div[contains(@id,'timeaxis')]//table[contains(@class,'sch-header-row')";
			}else if((weekPressed=="true")&&(twentyFourBySevenPressed=="false"))
			{
				xpath="//div[contains(@class,'x6-toolbar-default-docked-top')]//span[contains(text(),'View Week')]//ancestor::a[contains(@class,'x6-btn-default-toolbar-small') and contains(@aria-pressed,'true')]//following-sibling::a[contains(@class,'x6-btn-default-toolbar-small') and contains(@aria-pressed,'false')]//ancestor::div[contains(@class,'rsrvcalendar sch-timelinepanel')]//div[contains(@id,'timeaxis')]//table[contains(@class,'sch-header-row')";
			}else {
				xpath="//div[contains(@class,'x6-toolbar-default-docked-top')]//span[contains(text(),'View Week')]//ancestor::a[contains(@class,'x6-btn-default-toolbar-small') and contains(@aria-pressed,'false')]//following-sibling::a[contains(@class,'x6-btn-default-toolbar-small') and contains(@aria-pressed,'false')]//ancestor::div[contains(@class,'rsrvcalendar sch-timelinepanel')]//div[contains(@id,'timeaxis')]//table[contains(@class,'sch-header-row')";
			}
			
	
		if(dateOrTime.equalsIgnoreCase("date")){
		 
			xpath += "and contains(@class,'row-top')]//div[@class='sch-simple-timeheader']";
		}else{
			
			xpath += "and contains(@class,'row-middle')]//div[@class='sch-simple-timeheader']";
		}
		 
		 System.err.println(xpath);
		 
		List<String> headerValues = new ArrayList<String>();
		
		try{
			
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		
		List<WebElement> gridCols = driver.findElements(By.xpath(xpath));
		
		for(WebElement dateRange: gridCols){
			
			headerValues.add(dateRange.getText().trim());
		}
		
		}finally{
			
			driver.manage()
			.timeouts()
			.implicitlyWait(configuration.getImplicitWait(),
					TimeUnit.SECONDS);
			
		}
		
		return headerValues;
		
	}
	
	
	public List<String> getReservableItemsAvailableForTimeFromCalendar(String type ,String fromTime){
		
		List<String> availableResources = new ArrayList<String>();
		clickTabOrButtonsInCalendarToolBarOnTop(type,"@class");
		
		waitForExtJSAjaxComplete(5);
		
		 List<String> resources =  getResourceNames(); 
			
		 for(String resource : resources){
			 
			 if(isResourceAvailable(resource, fromTime)){
				 
				 availableResources.add(resource);
				 
			 }
			 
		 }
		
		return availableResources;
		
	}
	
	private boolean isResourceAvailable(String resourceName, String fromTime){

		int columnWidth = getTimeColumnWidth();
		
		String recordId = getDataRecordIDOfReservableResource(resourceName);
		
		
		
		String rowXpath = CALENDAR_GRID_XPATH+"/table[contains(@id,'timelineview-record-"+recordId+"')]//td";
		
		String rowStyle = driver.findElement(By.xpath(rowXpath)).getAttribute("style");
		
		int rowWidth = getWidthFromStyle(rowStyle);
		
		boolean isResourceAvailable = true;
		
		int timeToCheckPosition = timeToPositionConversion(fromTime, columnWidth);
		
		String xpath = rowXpath + "//div[contains(@class,'x6-grid-cell-inner')]/div";
		
		List<WebElement> eventsList = driver.findElements(By.xpath(xpath));
		
		for(WebElement event: eventsList){
			
			String style = event.getAttribute("style");
			int eventWidth = getWidthFromStyle(style);
			
			//To handle complete unavailable period
			if(rowWidth >= eventWidth||eventWidth >= (rowWidth-5)){
				
				isResourceAvailable = false;
				
				break;
				
			}
			
			
			int startPosition = getLeftFromStyle(style);
			
			if((startPosition < timeToCheckPosition)&& ((startPosition+eventWidth) > timeToCheckPosition)){
				
				isResourceAvailable = false;
				
				break;
				
			}
			
		}
		
		return isResourceAvailable;
		
	}
	
	
	
	
	private int getTimeColumnWidth(){
		
		String dateHeaderXapth=  CALENDAR_WIN_XPATH+"//div[contains(@id,'timeaxis')]//table[contains(@class,'sch-header-row') and contains(@class,'row-middle')]//td[contains(@class,'sch-column-header')]";

		String headerStyle = driver.findElement(By.xpath(dateHeaderXapth)).getAttribute("style");
		
		return getWidthFromStyle(headerStyle);
		
	} 
	
	private int timeToPositionConversion(String time, int cellWidth){
		
		String timePart[] = time.split(":");
		
		int reservableTimePosition = (Integer.parseInt(timePart[0]))* cellWidth +  (Integer.parseInt(timePart[1])*cellWidth/60);
		
		return reservableTimePosition;
		
		
	}
	
	
	/**
	 * Helper method to set Date in Date picker
	 * @param date : Enter in dd format
	 * @param month: Enter in MMM format
	 * @param year	: Enter in YYYY format
	 * 
	 */
	public void setDateViaDatePicker(String value) throws ParseException{
		
		
		clickDateIcon();
		
		waitForExtJSAjaxComplete(15);
		 
		DateFormat inputDF  = new SimpleDateFormat("dd-MM-yyyy");
        Date setDate = inputDF.parse(value);
        Calendar cal = Calendar.getInstance();
        cal.setTime(setDate);
     
        int month = cal.get(Calendar.MONTH)+1;
        int date = cal.get(Calendar.DAY_OF_MONTH);
        String day = (date<10)?"0"+date:""+date;
        int year = cal.get(Calendar.YEAR);

		
		String dateToEnter = date+"-"+month+"-"+year;
		
		SimpleDateFormat desiredFormat = new SimpleDateFormat("dd-MM-yyyy"); 
		
		Date dt = desiredFormat.parse(dateToEnter);
		 
		Format threeLettersformatter = new SimpleDateFormat("MMM",Locale.ENGLISH);
		   
		String monthName = threeLettersformatter.format(dt);
		    
		monthName = Character.toUpperCase(monthName.charAt(0)) + monthName.substring(1);
		
	    
		//******************* Make sure that date is in DD-MMM-YYYY format
		//String dateToEnter = date+"-"+month+"-"+year;
	
		//SimpleDateFormat desiredFormat = new SimpleDateFormat("dd-MMM-yyyy");   
	    
		Format formatter = new SimpleDateFormat("MMMM"); 
	    
	    String fullMonthName = formatter.format(dt);
	    
	    System.err.println(fullMonthName);
	    
	    //Get Current year
	    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	    
	    String datePickerWinXpath = "//div[contains(@id,'datepicker') and @role='region' and not(contains(@style,'display: none'))]";
		
		String headerButtonXpath = datePickerWinXpath+"//a[contains(@id,'splitbutton')]";
		
		final String monthAndYearPickerWindow = datePickerWinXpath+"//div[contains(@id,'monthpicker') and @class='x6-monthpicker-body']/..";
	    
		//Xpath to click on Previous or Next to go back and forth in Years section
		final String xpathOfNavigationButton = datePickerWinXpath+"//a[contains(@class,'"+((currentYear >year)? "yearnav-prev": "yearnav-next")+"')]" ;

		final String yearXpath = datePickerWinXpath+"//div[@class='x6-monthpicker-years']//a[text()='"+year+"']";
		
		//Replace leading 0's only
		String dateValue=Integer.toString(date);
		String tempDate =dateValue.replaceFirst("^0+(?!$)", "");
		System.out.println(tempDate);
		String dateXpath = datePickerWinXpath+"//div[@class='x6-datepicker-date' and text()='"+tempDate+"']/.." ;
		
	    //***********************************************Code****************************************
	    
		
		
		//Click on Current month button to pop out Month and Year picker window
		driver.findElement(By.xpath(headerButtonXpath)).click();
		
		//Wait for Month and date picker to be visible
		waitForExtJSAjaxComplete(30);
		

		WebDriverWait wait=new WebDriverWait(driver,30);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id,'datepicker') and @role='region' and not(contains(@style,'display: none'))]//div[@class='x6-monthpicker-months']//a[text()='"+monthName+"']")));
		
		String monthXpath = datePickerWinXpath+"//div[@class='x6-monthpicker-months']//a[text()='"+monthName+"']";
		driver.findElement(By.xpath(monthXpath)).click();
		
		
		WebElement yearElement = null;
	
		try{

			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

			FluentWait <WebDriver> waitForYear = new FluentWait<WebDriver>(driver)
					.withTimeout(180, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);

			yearElement = waitForYear.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {

					try{

						return driver.findElement(By.xpath(yearXpath));

					}catch(NoSuchElementException e){

						driver.findElement(By.xpath(xpathOfNavigationButton)).click();

					}

					return driver.findElement(By.xpath(yearXpath));
				}
			});
		}
		finally{  driver.manage() .timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);  }

		yearElement.click();
			   
		
		//Click on OK after selecting month and year
		String okButtonXpath = datePickerWinXpath+"//span[text()='OK']/ancestor::a";

		driver.findElement(By.xpath(okButtonXpath)).click();

		waitForExtJSAjaxComplete(2);

		//There can be multiple results with Same date. Get all the elements, then check the property value equals 'Month date' 

		List<WebElement> dateEles =  driver.findElements(By.xpath(dateXpath));

		
		for(WebElement dateEle: dateEles){
			
			if(dateEle.getAttribute("aria-label").equalsIgnoreCase(fullMonthName+" "+day)){

				dateEle.click();

				break;
			}
		}

		Reporter.log(dateToEnter+ " is set in Date picker", true);
			  
		
	}
	
	
	
	
	
	
	
	//****************************** Pagination toolbar of Reservable Objects **********************************************
	
	/**
	 * Helper method to click First button in Pagination toolbar for Reservable objects in calendar tab
	 */
	public void clickOnFirstPaginationButtonofReservableObjects(){


		clickOnPaginationButtonsofReservableObjects("First Page");
		
	}

	/**
	 * Helper method to click Previous button in Pagination toolbar for Reservable objects in calendar tab
	 */
	public void clickOnPreviousPaginationButtonofReservableObjects(){

		clickOnPaginationButtonsofReservableObjects("Previous Page");

	}

	/**
	 * Helper method to click Next button in Pagination toolbar for Reservable objects in calendar tab
	 */
	public void clickOnNextPaginationButtonofReservableObjects(){

		clickOnPaginationButtonsofReservableObjects("Next Page");

	}
	
	/**
	 * Helper method to click Last button in Pagination toolbar for Reservable objects in calendar tab
	 */
	public void clickOnLastPaginationButtonofReservableObjects(){

		clickOnPaginationButtonsofReservableObjects("Last Page");

	}
	
	/**
	 * Helper method to click Refresh button in Pagination toolbar for Reservable objects in calendar tab
	 */
	public void clickOnRefreshPaginationButtonofReservableObjects(){

		clickOnPaginationButtonsofReservableObjects("Refresh");

	}
	
	/**
	 * Helper method to click on Pagination buttons for Reservable Objects
	 * @param buttonDataQtip to be clicked
	 */
	
	private void clickOnPaginationButtonsofReservableObjects(String buttonDataQtip){
		
		String xpath = "//div[contains(@id,'paging')]//a[@data-qtip='"+buttonDataQtip+"']";
		
		McsElement.getElementByXpath(driver, xpath).click();
		
		Reporter.log(buttonDataQtip+" button in pagination toolbar of Reservable objects is clicked ", true);
				
		
	}
	
	
	
	
	//**************************************************Date tool bar methods  *******************************************************************************
	
	/**
	 * Helper method to set Date in Calendar page
	 * @param date: to set
	 * 
	 *//*
	public void setDateInCalendarTab(String date)
	{
		
		WebElement textField = McsElement.getElementByXpath(driver, CALENDAR_DATE_XPATH);

		textField.clear();
		textField.sendKeys(date);
		textField.sendKeys(Keys.RETURN);
		waitForExtJSAjaxComplete(5);
		//clickOnRefreshButtonInCalendarTab();
	}
	*/
	/**
	 * Helper method to set Date in Calendar page
	 * @param date: to set
	 * 
	 */
	public String getDateInCalendarTab()
	{
		
		return McsElement.getElementByXpath(driver, CALENDAR_DATE_XPATH).getAttribute("value");

	}
	
	
	/**
	 * Helper method to click on Previous week button in Date Navigation toolbar buttons 
	 */
	public void clickPreviousWeekNavigationToolBarButton(){
		
		clickDateNavigationToolBarButtons("Previous week");
		
	}
	
	
	
	/**
	 * Helper method to click on Previous day button in Date Navigation toolbar buttons 
	 */
	public void clickPreviousDayNavigationToolBarButton(){
		
		clickDateNavigationToolBarButtons("Previous day");
		
	}
	
	/**
	 * Helper method to click on Next Week button in Date Navigation toolbar buttons 
	 */
	public void clickNextWeekNavigationToolBarButton(){
		
		clickDateNavigationToolBarButtons("Next week");
		
	}
	
	
	/**
	 * Helper method to click on Next day button in Date Navigation toolbar buttons 
	 */
	public void clickNextDayNavigationToolBarButton(){
		
		clickDateNavigationToolBarButtons("Next day");
		
	}
	
	/**
	 * Helper method to click Date Navigation toolbar buttons 
	 * @param buttonTitle to click
	 */
	private void clickDateNavigationToolBarButtons(String buttonTitle){
		
		String xpath = "//div[contains(@id,'rsrv_navigation_toolbar')]//a[@title='"+buttonTitle+"']";
	}
	
	
	private int returnNextInt(String decimalVal ){
		
		double d= Double.parseDouble(decimalVal);
		
		return (int)Math.ceil(d);
		
	}
	
	/**
	 * Helper method to get width from Style attribute
	 * @param styleValue
	 * @return
	 */
	private int getWidthFromStyle(String styleValue){
		
		 Pattern re = Pattern.compile("width:(.*?)px(.*?)");
		  Matcher m = re.matcher(styleValue);
		    if(m.find()){
		      for( int groupIdx = 0; groupIdx < m.groupCount()+1; groupIdx++ ){
		        System.out.println( "[" + groupIdx + "] = " + m.group(groupIdx));
		        
		        if(groupIdx==1){
		        	
		        	return Integer.parseInt(m.group(groupIdx).trim());}
		        }
		      }
		    
		 
		
		return -1;
	}
	
	/**
	 * Helper method to get width from Style attribute
	 * @param styleValue
	 * @return
	 */
	private static int getLeftFromStyle(String styleValue){
		
		 Pattern re = Pattern.compile("left:(.*?)px(.*?)");
		  Matcher m = re.matcher(styleValue);
		    if(m.find()){
		      for( int groupIdx = 0; groupIdx < m.groupCount()+1; groupIdx++ ){
		        System.out.println( "[" + groupIdx + "] = " + m.group(groupIdx));
		        
		        if(groupIdx==1){
		        	
		        	return Integer.parseInt(m.group(groupIdx).trim());}
		        }
		      }
		    
		 
		
		return -1;
	}
	
	
	/**
	 * Helper method to click on reservation in calendar tab
	 * @param resourceName name of the item
	 * @return 
	 */

	public void clickReservationInCalendar(String resourceName,String reservID)
	{
		filterItemByName(resourceName);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(20);
		clickOnCalendarGridTimeSheet(resourceName);
		waitForExtJSAjaxComplete(10);
		Actions action = new Actions(driver);
		String actToolTipText;
		String recordId = getDataRecordIDOfReservableResource(resourceName);
		List<WebElement>  rowXpath = driver.findElements(By.xpath(CALENDAR_GRID_XPATH+"/table[contains(@id,'timelineview-record-"+recordId+"')]//td[contains(@class, 'sch-timetd')]//div[contains(@class,'x6-grid-cell-inner')]/div[contains(@class,'sch-event')]"));
		for(WebElement element:rowXpath)
		{
			
			
			int x= 	element.getSize().getWidth();
			
			int y = element.getSize().getHeight();
			
			action.moveToElement(element).build().perform();
			
			waitForExtJSAjaxComplete(10);
			try{
			action.moveToElement(element,x/6,y/2).build().perform();

			WebDriverWait wait=new WebDriverWait(driver,20);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'reservationTip')]//*[contains(@class,'rsrvcalendar-eventTip')]/div[contains(@class,'icon-tooltip-task')]")));
			String toolTipXpath="//div[contains(@class,'reservationTip')]//*[contains(@class,'rsrvcalendar-eventTip')]/div[contains(@class,'icon-tooltip-task')]";
			String toolTipText=driver.findElement( By.xpath(toolTipXpath)).getText();
			//String expReservId=reservID.replace("Reservation", "").trim();
			actToolTipText=toolTipText.replace("Reservation", "").trim();
			System.err.println(reservID+""+actToolTipText);
			}catch(Exception e)
			{
				waitForExtJSAjaxComplete(10);
				
				action.moveToElement(element).moveToElement(element,150,22).build().perform();
				
				WebDriverWait wait=new WebDriverWait(driver,20);
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'reservationTip')]//*[contains(@class,'rsrvcalendar-eventTip')]/div[contains(@class,'icon-tooltip-task')]")));
				String toolTipXpath="//div[contains(@class,'reservationTip')]//*[contains(@class,'rsrvcalendar-eventTip')]/div[contains(@class,'icon-tooltip-task')]";
				String toolTipText=driver.findElement( By.xpath(toolTipXpath)).getText();
				//String expReservId=reservID.replace("Reservation", "").trim();
				actToolTipText=toolTipText.replace("Reservation", "").trim();
				System.err.println(reservID+""+actToolTipText);
			}
			
			if(actToolTipText.equals(reservID))
			{
				element.click();
			}else{
				System.out.print("Reservation is not available");
			}
		}
	}
	
	/**
	 * Helper method to verify reservation
	 * @param resourceName name of the item
	 * @return true or false
	 */
	public boolean isReservationCancelled(String resourceName,String reservID)
	{
		String recordId = getDataRecordIDOfReservableResource(resourceName);
		List<WebElement>  rowXpath = driver.findElements(By.xpath(CALENDAR_GRID_XPATH+"/table[contains(@id,'timelineview-record-"+recordId+"')]//td[contains(@class, 'sch-timetd')]//div[contains(@class,'x6-grid-cell-inner')]/div[contains(@class,'sch-event')]"));
		for(WebElement element:rowXpath)
		{
			Actions action = new Actions(driver);
			
			int x= 	element.getSize().getWidth();
			
			int y = element.getSize().getHeight();
			
			action.moveToElement(element).build().perform();
			
			action.moveToElement(element,x/2,y/2).build().perform();
			
			waitForExtJSAjaxComplete(20);
			
			action.moveToElement(element,x/6,y/2).build().perform();
			
			WebDriverWait wait=new WebDriverWait(driver,30);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'reservationTip')]//*[contains(@class,'rsrvcalendar-eventTip')]/div[contains(@class,'icon-tooltip-task')]")));
			String toolTipXpath="//div[contains(@class,'reservationTip')]//*[contains(@class,'rsrvcalendar-eventTip')]/div[contains(@class,'icon-tooltip-task')]";
			String toolTipText=driver.findElement( By.xpath(toolTipXpath)).getText();
			//String expReservId=reservID.replace("Reservation", "").trim();
			String actToolTipText=toolTipText.replace("Reservation", "").trim();
			if(actToolTipText.equals(reservID))
			{
				return true;
			}
			
		}
		
		return false;
		
		
	}
	/**
	 * Helper method to get tool tip text of unavailable reservation 
	 * @return tool tip text
	 * @throws AWTException 
	 */
	public String getToolTipTextOfUnAvailableReservation(String resourceName) throws AWTException

	{
		clickOnCalendarGridTimeSheet(resourceName);
		
		waitForExtJSAjaxComplete(5);
		
		Actions action = new Actions(driver);
		
		String recordId = getDataRecordIDOfReservableResource(resourceName);
		
		waitForExtJSAjaxComplete(10);
		
		String  rowXpath = CALENDAR_GRID_XPATH+"//table[contains(@id,'timelineview-record-"+recordId+"')]//td[contains(@class,'sch-timetd')]";
		
		WebElement fromTimeElement  = driver.findElement(By.xpath(rowXpath));
		
		int x=fromTimeElement.getSize().getWidth();
		
		int y=fromTimeElement.getSize().getHeight();
		try{
		action.moveToElement(fromTimeElement).moveToElement(fromTimeElement, x/2, y/2).build().perform();
		
		WebDriverWait wait=new WebDriverWait(driver,30);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'rsrvcalendar-eventTip')]//following-sibling::div[contains(@class,'tooltip-description-text')]")));
		String toolTipXpath="//*[contains(@class,'rsrvcalendar-eventTip')]//following-sibling::div[contains(@class,'tooltip-description-text')]";
		String toolTipText=driver.findElement( By.xpath(toolTipXpath)).getText();
		return toolTipText;
		}catch(Exception e){
		
			
			action.moveToElement(fromTimeElement).moveToElement(fromTimeElement,150,22).build().perform();
			
			WebDriverWait wait=new WebDriverWait(driver,30);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'rsrvcalendar-eventTip')]//following-sibling::div[contains(@class,'tooltip-description-text')]")));
			String toolTipXpath="//*[contains(@class,'rsrvcalendar-eventTip')]//following-sibling::div[contains(@class,'tooltip-description-text')]";
			String toolTipText=driver.findElement( By.xpath(toolTipXpath)).getText();
			return toolTipText;
		}
	}
	
	/**
	 * Helper method to select the name as Rooms in Calendar.
	 * @return Name of the Item and time.
	 */
	public List<String> findRoomsFreeForGivenTime(String time){

		return getReservableItemsAvailableForTimeFromCalendar("room", time);
	}

	/**
	 * Helper method to select the name as Equipment in Calendar.
	 * @return Name of the Item and time.
	 */
	public List<String> findEquipmentFreeForGivenTime(String time){

		return getReservableItemsAvailableForTimeFromCalendar("equipment", time);
	}
	/**
	 * Get text of reservation reference in popup window in Calendar tab
	 */
	public String getReservationsRefFromPopUpContent(){
		
		String content = McsElement.getElementByXpath(driver, CALENDAR_RESERVATIONS_POPUP+"//span[contains(@class,'x-window-header-text')]").getText();
		
		return content;//getWarningDialogTextMsg();
		
	}
	
	/**
	 * Get text of reservation item window  text in Calendar tab
	 * @return reservation item details  window text in Calendar tab
	 */
	public String getReservationsPopUpContent(){
		
		String content = McsElement.getElementByXpath(driver, CALENDAR_RESERVATIONS_POPUP+"//div[@class='x-window-body']//div").getText();
		
		return content.trim();//getWarningDialogTextMsg();
	
	}

	/**
	 * Helper method to close popup window
	 */
	public void clickCloseInReservationsPopupWindow() {

		McsElement.getElementByXpath(driver, CALENDAR_RESERVATIONS_POPUP + "//div[contains(@class,'x-tool x-tool-close')]").click();
	}
	
	/**
	 * Helper method to get the list of hours in a day as List of 24hrs in HH:00 format
	 * @return List of 24hrs in HH:00 format 
	 */
	public List<String> getListOfHoursInDay(){


		Calendar calendar = Calendar.getInstance();

		calendar.setTime(new Date());

		SimpleDateFormat format = new SimpleDateFormat("HH");

		List<String> hoursInDay = new ArrayList<String>();

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		for (int i = 0; i < 24; i++)
		{
			hoursInDay.add(format.format(calendar.getTime()));
			calendar.add(Calendar.HOUR_OF_DAY, 1);
		}

		return hoursInDay;
	}
	
	/**
	 * Helper method to click on Look Up button when input doesn't have @name
	 * attribute
	 */

	public void clickLookupButtonBasedOnLabel(String fieldLabel) {
		McsElement.getElementByXpath(driver, CALENDAR_REGION_XPATH + "//button").click();
	}

	/**
	 * Helper method to get tooltip message of reservation in calender
	 * @param resourceName reserved item
	 * @param reservID reservation id
	 * @return tool tip message
	 */
	public String mouseOverOnReservationInCalender(String resourceName,String reservID){

		String recordId = getDataRecordIDOfReservableResource(resourceName);
		String toolTipText ="";
		List<WebElement>  rowXpath = driver.findElements(By.xpath(CALENDAR_GRID_XPATH+"/table[contains(@id,'timelineview-record-"+recordId+"')]//td[contains(@class, 'sch-timetd')]//div[contains(@class,'x6-grid-cell-inner')]/div[contains(@class,'sch-event')]"));
		for(WebElement element:rowXpath)
		{
			Actions action = new Actions(driver);

			int x= 	element.getSize().getWidth();

			int y = element.getSize().getHeight();

			action.moveToElement(element).build().perform();

			waitForExtJSAjaxComplete(10);

			action.moveToElement(element,x/2,y/2).build().perform();

			action.moveToElement(element).moveToElement(element, 100, 22).build().perform();


			WebDriverWait wait=new WebDriverWait(driver,30);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'reservationTip')]//*[contains(@class,'rsrvcalendar-eventTip')]")));
			String toolTipXpath="//div[contains(@class,'reservationTip')]//*[contains(@class,'rsrvcalendar-eventTip')]";
			toolTipText=driver.findElement( By.xpath(toolTipXpath)).getText();
			if(toolTipText.contains(reservID))
			{
				return toolTipText;
			}else{
				System.out.print("Reservation is not available");
			}
		}
		return toolTipText;
	}
	
	
	/**
	 * Helper method to drag and drop the reservation
	 * @param resourceName
	 * @param fromTime
	 * @param endTime
	 * @return
	 * @throws AWTException
	 */
	
	public boolean createNewReservationByDragAndDrop(String resourceName , String fromTime, String endTime) throws AWTException{
		
		boolean isReservationCreated = false;
		
			filterItemByName(resourceName);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(20);
			
			dragAndDrop(fromTime,  endTime,resourceName);
			waitForExtJSAjaxComplete(5);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(5);
			
			//clickNewReservationButtonObtainedAfterDragAndDrop();
		
			isReservationCreated = true;
			
			return isReservationCreated;
			
	}
	
	
	
	/**
	 * Helper method to click on New Reservation button obtained after drag and drop
	 */
	public void clickNewReservationButtonObtainedAfterDragAndDrop(){
		
		McsElement.getElementByXpath(driver, CALENDAR_NEW_RESERVATION_BUTTON_DRAG).click();
		
	}
	

	
	/**
	 * Helper method to movemouse to desired from Time and then drag and drop mouse till end time is reached.
	 * Note: This method is designed based on tool tip. So Time intervals should be multiple's of 15 and 
	 * Assumptions: Screen would be filtered and all the actions are done on 1st resource result
	 * @param fromTime
	 * @param endTime
	 */
	public void dragAndDrop(String fromTime, String endTime,String resourceName) throws AWTException{
		
		clickOnCalendarGridTimeSheet(resourceName);
		waitForExtJSAjaxComplete(15);
		moveMousetoDesiredFromTime(fromTime);
		
		//Code to click and hold mouse button after reaching from time
		Actions action = new Actions(driver);
		action.clickAndHold().build().perform();
		
		moveMousetoDesiredToTime(endTime);
		
		//Code to release mouse button after reaching end time
		action = new Actions(driver);
		action.release().perform();
		
	
	}
	
	
	private void moveMousetoDesiredFromTime(String fromTime) throws AWTException{
		
		String toolTipXpath = "//div[contains(@class,'rsrvcalendar-tip') and @aria-hidden='false']//span[contains(@class,'sch-clock-text')]";
		waitForExtJSAjaxComplete(5);
		moveMouseToDesiredTime(fromTime,  toolTipXpath);
		
	}
	
	
	private void moveMouseToDesiredTime(String time, String toolTipXpath) throws AWTException{
		
		String fromtimePart[] = time.split(":");

		String fromTimeXpath = CALENDAR_WIN_XPATH+"//div[contains(@id,'timeaxis')]//table[contains(@class,'sch-header-row') and contains(@class,'row-middle')]//div[@class='sch-simple-timeheader' and text()='"+fromtimePart[0]+"']/..";

		WebElement fromTimeElement  = driver.findElement(By.xpath(fromTimeXpath));

		String timeCellHeight = fromTimeElement.getAttribute("offsetHeight");
		//System.out.println(timeCellHeight);

		int cellHeight = Integer.parseInt(timeCellHeight);
		//System.out.println(cellHeight);

		Point coordinates = fromTimeElement.getLocation();
		Robot robot = new Robot();
		Actions actions = new Actions(driver);

		robot.mouseMove(coordinates.getX()+6,coordinates.getY()+135+cellHeight);
		
		waitForExtJSAjaxComplete(5);
		
		Actions action = new Actions(driver);
		action.moveToElement(fromTimeElement, 0, 5).moveByOffset(0,cellHeight+6).build().perform(); 

			WebElement toolTip = driver.findElement(By.xpath(toolTipXpath));

			String toolTipContent = toolTip.getText();
			System.err.println(toolTipContent);
			System.err.println(time);
			if(time.contains(toolTipContent)){
				System.out.print("Tooltip message is displayed");
				
			}else{
				System.out.print("Tooltip message is not displayed");
				actions.moveToElement(fromTimeElement, 150, 22).build().perform();

			}
		
	}
	
	
	private void moveMousetoDesiredToTime(String toTime) throws AWTException{
		
		
		String toolTipXpath = "//div[contains(@role,'tooltip') and contains(@class,'sch-dragcreate-tip')]//div[contains(@class,'sch-clockwrap')][2]//span";
		
		moveMouseToDesiredTime(toTime,  toolTipXpath);
		
	}
	
	
	/**
	 * Helper method to verify reservation legends in calendar
	 * @param resourceName reserved item
	 * @param reservID reservation id
	 * @return tool tip message
	 */
	public boolean isReservHasSpecificLegend(String resourceName, String legendName,String reserveID){

		String recordId = getDataRecordIDOfReservableResource(resourceName);
		waitForExtJSAjaxComplete(5);
		String toolTipText ="";
		List<WebElement>  rowXpath = driver.findElements(By.xpath(CALENDAR_GRID_XPATH+"/table[contains(@id,'timelineview-record-"+recordId+"')]//td[contains(@class, 'sch-timetd')]//div[contains(@class,'x6-grid-cell-inner')]//div[contains(@class,'sch-event-label')]"));
		for(WebElement element:rowXpath)
		{
			Actions action = new Actions(driver);

			int x= 	element.getSize().getWidth();

			int y = element.getSize().getHeight();

			action.moveToElement(element).build().perform();

			waitForExtJSAjaxComplete(5);

			action.moveToElement(element,x/2,y/2).build().perform();

			action.moveToElement(element,x/6,y/2).build().perform();


			WebDriverWait wait=new WebDriverWait(driver,30);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'reservationTip')]//*[contains(@class,'rsrvcalendar-eventTip')]")));
			String toolTipXpath="//div[contains(@class,'reservationTip')]//*[contains(@class,'rsrvcalendar-eventTip')]";
			toolTipText=driver.findElement( By.xpath(toolTipXpath)).getText();
			
			if(toolTipText.contains(reserveID))
			{
				if(legendName =="tentative"||legendName=="fa fa-connectdevelop"||legendName=="fa fa-refresh"||legendName=="fa fa-id-card")
				{	
				System.out.println(element);
				WebElement ele = element.findElement(By.xpath("//span[contains(@class,'"+legendName+"')]"));
				System.out.println(ele);
				return true;
				}else
				{
					WebElement ele = element.findElement(By.xpath("./following-sibling::div[contains(@class,'sch-event-cleanup')]"));
					String strValue=ele.getAttribute("style").replace("width:", "").replace("%;", "").trim();
					System.err.println(strValue);
					float i = Float.valueOf(strValue);
					System.err.println(i);
					if(i>0)
					{	
					System.out.println("prepare and clean up reservation legend is displayed");
					return true;
					}else{
						System.out.println("Myreservation legend is displayed");
						return true;
					}
				}
			}	
			else{
				System.out.print("Reservation is not available");
			}			
		}
		return false;
	}
		
	
	/**
	 * Helper method to verfiy WO Grid is Empty
	 */
	public boolean isGridEmpty(String text){
		String xpath = "//div[contains(@id,'rsrv_scheduler') and (contains(@id,'locked-body'))]//div[contains(@class, 'x6-grid-empty') and (contains(text(),'No items available'))]";
		return McsElement.isElementPresent(driver, xpath);
		
	}
	
	/**
	 * Helper method to get field value based
	 * @return value
	 */
	public String getRegionFieldvalueBasedOnLabel(){
		String xpath = CALENDER_REGION_CLASS + "//input[contains(@class,'x-form-field x-trigger-noedit')]";
		return McsElement.getElementByXpath(driver, xpath).getAttribute("value");
	}

	/**
	 * Helper method to get dropdown field values
	 * @return fieldvalues
	 */
	public List<String> getRegionMultiSelectDropDownvalues(){
		List<String> fieldValues = new ArrayList<String>();
		String xpath ="//div[contains(@class,'x-combo-list') and contains(@style,'visibility: visible')]//div[contains(@class,'x-combo-list-inner')]//div[contains(@class,'x-combo-list-item')]";
		List <WebElement> elements = driver.findElements(By.xpath(xpath));
		for(WebElement ele : elements){

			fieldValues.add(ele.getText());
		}

		return fieldValues;
	}

	/**
	 * Helper method to return true/false based on Dropdown is displayed
	 */
	public boolean isMultiSelectRegionDropdownInCopyReservationDisplayed(){

		String xpath = CALENDER_REGION_CLASS+"//input[contains(@class,'x-form-field')]/following-sibling::img";

		try{

			McsElement.isElementPresent(driver, xpath);

			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	/**
	 * Helper method to ckick on Dropdown button
	 */
	public void clickMultiSelectRegionDropdownInCopyReservation()
	{
		String xpath= CALENDER_REGION_CLASS +"//input[contains(@class,'x-form-field')]/following-sibling::img[contains(@class,'x-form-arrow-trigger')]";
		driver.findElement(By.xpath(xpath)).click();
	}

	
	/**
	 * Helper method to click conference button
	 */
	
	public void clickGeneralConference(){
		driver.findElement(By.xpath("//div[contains(@class,'mcsreservationedit')]//button[contains(@class,'x-btn-text') and contains(text(),'Conference')]")).click();
		waitForExtJSAjaxComplete(15);
	}
	
	/**
	 * Helper method to verify grid is highlight with rows 
	 */

	public boolean isGridRowsHighlightedForConference()
	{
		String xpath=CALENDAR_WIN_XPATH+"//div[contains(@class,'rsrvcalendar-resource-grid') and contains(@class,'x6-grid firstColumnHighlighting')]";
		return McsElement.isElementPresent(driver, xpath);
	}
	
	/**
	 * Helper method to click room 
	 */
	public void clickOnRoomInCalendarGrid(String room)
	{
		String xpath=CALENDAR_RESOURCE_GRID_XPATH+"//div[text()='"+room+"']";
		driver.findElement(By.xpath(xpath)).click();
	}
	
	
	/**
	 * Helper method to click on grid
	 */
	
	public void clickOnCalendarGridTimeSheet(String resourceName)
	{

		Actions action = new Actions(driver);
		
		String recordId = getDataRecordIDOfReservableResource(resourceName);
		
		waitForExtJSAjaxComplete(10);
		
		WebElement  rowXpath = driver.findElement(By.xpath(CALENDAR_GRID_XPATH+"//table[contains(@id,'timelineview-record-"+recordId+"')]//td[contains(@class,'sch-timetd')]"));
		
		int x= 	rowXpath.getSize().getWidth();
		
		int y = rowXpath.getSize().getHeight();
		
		action.moveToElement(rowXpath).build().perform();
		
		waitForExtJSAjaxComplete(10);

		action.moveToElement(rowXpath,x/2,y/2).build().perform();

		waitForExtJSAjaxComplete(10);

		rowXpath.click();


	}

	/**
	 * Helper method to find the status of conference button
	 * @return boolean value
	 */
	public boolean isConferenceButtonEnabled(){
		String conferenceButton = "//div[contains(@class,'mcsreservationedit')]//table[contains(@class,'btn-noicon') and contains(.,'Conference')]";
		String buttonClass = driver.findElement(By.xpath(conferenceButton)).getAttribute("class");
		boolean buttonStauts = (!(buttonClass.contains("disabled"))?true:false );
		return buttonStauts;
	}
}
