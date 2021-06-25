package test.energy.gauges;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class GaugesMeasurementsPageObject extends AbstractMcsTestSuite {
	
	protected final String GAUGE_GRID_CLASS = "x-tab-panel x-tab-panel-noborder";
	protected final String DIALOG_GAUGE_CHNL = "slnmGaugeChnlMeasId";
	protected final String DIALOG_MEASUREMENT = "slnmMeasId";
	protected final String DIALOG_GAUGE = "slnmGaugeId";
	protected final String ADD_CHANEL_GAUGES_FORM_CLASS = "slnmGaugeChnlId";
	
	public void expandMetering() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu", 
				"span", "text()", "Metering", true, true).click();
		Reporter.log("Expand metering", true);
	}
	
	public void openMeteringEntity(String entity) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"button", "text()", entity, true, true).click();
		Reporter.log("Open metering " + entity, true);
	}
	
	public void changeTab(String tabName) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"li", "@id", tabName+"-tab", "span", "@class", "x-tab-strip-text", true, true).click();
		Reporter.log("Change tab to "+tabName, true);
	}
	
	//TODO: Move later to grid methods (usable when grid doesn't have checkboxes)
	public WebElement checkRowInGriByTextValueAndClick(WebDriver webDriver, String textValue, String dialogClass)  {
		WebElement webElement = webDriver.findElement(By.xpath("//div[contains(@class, '"+dialogClass+"')]//*[@class='x-grid3']//div[text()='"+textValue+"']"));
		waitWebElement(webElement);
		javaScriptFocus(webElement);
		javaScriptClick(webElement);
//		webElement.click();
		Reporter.log("Check element "+textValue+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	
	public void clickViewMeasurementsButton()
	{
		WebElement viewButton = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_GAUGE, "button", "@class", "x-btn-text",
				"text()", "View Measurements", true, true);
		javaScriptFocus(viewButton);
		javaScriptClick(viewButton);
		Reporter.log("Click View Measurements button", true);
	}
	
	public void clickMeasurementsTab()
	{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"ul", "@class", "x-tab-strip", "span", "text()", "Measurements", true, true).click();
		Reporter.log("Click on Measurements tab", true);
	}

	//TODO: Move to general methods later
	public void clickButton(String buttonName, String dialogClass)
	{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", dialogClass, "button", "@class", "x-btn-text",
				"text()", buttonName, true, true).click();
		Reporter.log("Click "+buttonName+" button", true);
	}
	
	public void setValue(String value)
	{
		WebElement indexValue = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MEASUREMENT, 
				"input", "@name", "value", true, true);
		indexValue.click();
		indexValue.clear();
		indexValue.sendKeys(value);
		Reporter.log("Set index value - "+value, true);
	}
	
	public void setDate(String date)
	{
		WebElement dateField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MEASUREMENT, 
				"input", "@name", "date", true, true);
		dateField.click();
		dateField.clear();
		dateField.sendKeys(date);
		Reporter.log("Set date - "+date, true);
	}
	
	public void setTime(String time)
	{
		WebElement timeField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MEASUREMENT, 
				"input", "@name", "time", true, true);
		timeField.click();
		timeField.clear();
		timeField.sendKeys(time);
		Reporter.log("Set time - "+time, true);
	}
	
	public void clickLookup(String inputName, String dialogId) {//String formClass){
		Timer timer = new Timer().start();

		waitFocusAndClick("//div[contains(@class,'"+dialogId+"')]//input[contains(@name,'"
				+ inputName + "')]//..//..//button");

		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By
					.xpath("//div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]"));
		}

		catch (Exception e) {
			waitAndClick("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");
		} finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);
		}

		Reporter.log(inputName + " lookup was clicked"+ " (" + timer.stop()
				+ "ms)", true);
		
	}
	
	public void setMeasurementType(String measurementType) {
		clickLookup("measurementType", DIALOG_MEASUREMENT);
		setValueGridLookup(measurementType);
		Reporter.log("Set Measurement Type - "+measurementType, true);
	}
	
	public String getMeasurementType(String dialogId) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+dialogId+"')]//input[@name='measurementType']//..//input)[last()]"))
				.getAttribute("value");
	}
		
	//TODO: Move to general methods later
	public void saveClose() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MEASUREMENT, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save and Close", true);
	}
	
	public void close() {
		McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_GAUGE, "button",
				"text()", "Close", true, true).click();
		waitForExtJSAjaxComplete(15);
		Reporter.log("Close", true);
	}

	
	
	public String getDate() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MEASUREMENT, "input", "@name",
				"date", true, true).getAttribute("value");
	}
	
	public String getTime() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MEASUREMENT, "input", "@name",
				"time", true, true).getAttribute("value");
	}
	
	public String getValue() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MEASUREMENT, "input", "@name",
				"value", true, true).getAttribute("value");
	}

	/**
	 * Helper method to click on Date Icon in Channel Measurements Tab
	 */
	public void clickDateIconMeasurementsTab(String inputName){
		McsElement.getElementByXpath(driver, "//div[contains(@class, '"+DIALOG_GAUGE_CHNL+" ')]//input[@name='"+inputName+"']//following-sibling::img").click();
		waitForExtJSAjaxComplete(15);
		Reporter.log("Clicked on Date Icon Channel Measurements", true);
	}
	
	/**
	 * Helper method to get Values from Date Picker Icon
	 */
	public List<String> getDatePickerDropDownValues(String inputName){
		WebElement datePicker = driver.findElement(By.xpath("//div[contains(@class, '"+DIALOG_GAUGE_CHNL+" ')]//input[@name='"+inputName+"']//following-sibling::img"));
		
		datePicker.click();
		
		waitForExtJSAjaxComplete(5);
		
		List<WebElement> values = driver.findElements(By.xpath("//div[contains(@class, 'x-menu-floating') and (contains(@style, 'visibility: visible'))]//ul[contains(@class,'x-menu-list') and not (contains(@class, 'x-menu-sep-li'))]"));

		ArrayList<String> lsValues = new ArrayList<String>(); 

		for(int i=0; i<values.size(); i++){
					
		String valueText = values.get(i).getText().trim();
		lsValues.add(valueText);
		}
		
		
		return lsValues;
	}
	
	public boolean useLoop(List<String> arr, String targetValue) {
		for(String s: arr){
			//System.out.println("s is " +s);
			if(s.contains(targetValue)){
				return true;
			}
		}
		
		return false;
	}
	
	public void setComboValueDropDownSelector(String elementId, String comboValue) {
		driver.findElement(By.xpath("//input[@name='"+elementId+"']//following-sibling::img")).click();
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		
		driver.findElement(By.xpath("//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//li[contains(@class,'x-menu-list-item')]//span[contains(text(),'"+comboValue+"')]")).click();
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		
		if(comboValue.contentEquals("Custom Range")){
			
			driver.findElement(By.xpath("//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//ul[@class= 'x-menu-list']//form[contains(@class, 'x-box-layout-ct')]//div[contains(@class, 'x-box-inner')]//div[1]/input//following-sibling::img")).click();
			waitForExtJSAjaxComplete(5);
			driver.findElement(By.xpath("//div[contains(@class, 'x-combo-list x-menu')  and not (contains(@style, 'visibility: hidden'))]//div[contains(@class,'x-combo-list-item') and contains(text(),'2010')]")).click();
			
			waitForExtJSAjaxComplete(10);
			
			driver.findElement(By.xpath("//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//ul[@class= 'x-menu-list']//form[contains(@class, 'x-box-layout-ct')]//div[contains(@class, 'x-box-inner')]//div[2]/input//following-sibling::img")).click();
			waitForExtJSAjaxComplete(10);
			driver.findElement(By.xpath("//div[contains(@class, 'x-combo-list x-menu')  and not (contains(@style, 'visibility: hidden'))]//div[contains(@class,'x-combo-list-item') and contains(text(),'2014')]")).click();
			
			waitForExtJSAjaxComplete(5);
			
			driver.findElement(By.xpath("//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-plain-footer x-panel-btns')]//td[@class='x-toolbar-right']//button[contains(@class, 'x-btn-text') and text()='Apply']")).click();
			waitForExtJSAjaxComplete(5);
		}
		
	}
	
	/**
	 * Helper method to get Calendar Month
	 */
	public String getCurrentMonth(String systemDate) {
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH)+1;

		String Dates = String.valueOf((month<10?("0"+month):(month)));
		//String datesStr = Integer.toString(Dates);
		System.out.println("Datessssssss " +Dates);
		return Dates;
	}
	
	/**
	 * Helper method to get Calendar Year
	 */
	public int getCurrentYear(String systemDate) {
	    Calendar cal = Calendar.getInstance();
	    Date date = new Date();
	    cal.setTime(date);
	    System.out.println("year " +cal.get(Calendar.YEAR));
	    return cal.get(Calendar.YEAR);
	}
	
	/**
	 * Helper method to set get Date Range from Measurements Tab
	 */
	public String getDateRange(String attributeName) {
		
		WebElement element = driver.findElement(By.xpath("//div[contains(@class, '"+DIALOG_GAUGE_CHNL+" ')]//input[@name='"+attributeName+"']"));
		String dateValue = element.getAttribute("value");
		
		return dateValue;
	}
	
	
	public String getMonthDateRange(int noOfMonths){

		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-yyyy");
		
		Calendar c = Calendar.getInstance();

		int tempMonthsToAdd = (noOfMonths*-1>0)? -1: 0;
		
		c.add(Calendar.MONTH,tempMonthsToAdd);

		int lastDayOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		String mmYYYYFormat = dateFormatter.format(c.getTime());

		String toDate = ""+lastDayOfMonth+"-"+mmYYYYFormat;
		
		c.add(Calendar.MONTH,noOfMonths-tempMonthsToAdd);
		
		String mmYYYYFormatPre = dateFormatter.format(c.getTime());

		String fromDate = "01"+"-"+mmYYYYFormatPre;

		return fromDate+" to "+toDate;
	}

	
	public String getYearDateRange(int noOfYears){
		
		
		Calendar c = Calendar.getInstance();
		
		int tempYearsToAdd = (noOfYears*-1>0)? -1: 0;
		
		c.add(Calendar.YEAR,tempYearsToAdd);
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy");
		
		String mmYYYYFormat = dateFormatter.format(c.getTime());
		
		String toDate = "31-12"+"-"+mmYYYYFormat;
		
		
		c.add(Calendar.YEAR,noOfYears-tempYearsToAdd);
		
		String mmYYYYFormatPre = dateFormatter.format(c.getTime());
		
		String fromDate = "01-01-"+mmYYYYFormatPre;
		return fromDate+" to "+toDate;
	}
	
	
	
	/////////////// Reporting Interval Data Tab //////////////////////
	
	/**
	 * Helper method to get Values from Reporting Interval
	 */
	public List<String> getReportingIntervalDropDownValues(String inputName){
		WebElement datePicker = driver.findElement(By.xpath("//div[contains(@class, '"+DIALOG_GAUGE_CHNL+" ')]//input[@name='"+inputName+"']//following-sibling::img"));
		
		datePicker.click();
		
		waitForExtJSAjaxComplete(5);
		
		List<WebElement> values = driver.findElements(By.xpath("//div[contains(@class, 'x-combo-list') and (contains(@style, 'visibility: visible'))]//div[contains(@class,'x-combo-list-item')]"));

		ArrayList<String> lsValues = new ArrayList<String>(); 

		for(int i=0; i<values.size(); i++){
					
		String valueText = values.get(i).getText().trim();
		lsValues.add(valueText);
		}
		
		return lsValues;
	}
	
	public void setReportingInterval(String comboValue) {
		WebElement xPath = driver.findElement(By.xpath("//div[contains(@class, '"+DIALOG_GAUGE_CHNL+" ')]//input[@name='timeScale']"));
		
		String id = xPath.getAttribute("id");
		
		waitForExtJSAjaxComplete(10);
		
		DropDown.setExtJsComboValue(driver, id, comboValue);
		
		/*((JavascriptExecutor) driver)
		.executeScript("document.getElementById('"+id+"').value ='"+comboValue+"';");*/
		
		waitForExtJSAjaxComplete(30);
		
		Reporter.log("set Status Value to " + comboValue, true);
	}
	
	/**
	 * Helper method to get Last Month Last Date
	 */
	public String getLastMonthLastDate(String month, int year) {
      
	    // get a calendar object
	    GregorianCalendar calendar = new GregorianCalendar();
		    
		// convert the year and month to integers
		int yearInt = year;
		int monthInt = Integer.parseInt(month);
		    
		// adjust the month for a zero based index
		monthInt = monthInt - 2;
		    
		// set the date of the calendar to the date provided
		calendar.set(yearInt, monthInt, 1);
		    
		int dayInt = calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		    
		return Integer.toString(dayInt);
    }
	
	/**
	 * Helper method to get Last 6 Months Last Date
	 */
	public String getLastSixMonthFirstDate() {
		Date referenceDate = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(referenceDate); 
		c.add(Calendar.MONTH, -6);
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		Date date = c.getTime();
		
		String firstDate = dateFormat.format(date);
		String firstDateManipulation = firstDate.substring(0, 2);
		String dateFirst =  (String)(Integer.parseInt(firstDateManipulation)<=31?("01"):("01"));
		System.out.println("dateFirst " +dateFirst);
		
		String monthYearPart = firstDate.substring(2, 10);
		System.out.println("monthYearPart " +monthYearPart);
		
		firstDate = dateFirst+monthYearPart;
		System.out.println("firstDate " +firstDate);
		
		return firstDate;
    }
	
	/**
	 * Helper method to Manipulate 1st and last day of Last 6 months
	 */
	/*public String getLastSixMonthLastDate(String month, int year) {
		Date referenceDate = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(referenceDate); 
		c.add(Calendar.MONTH, -6);
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		Date date = c.getTime();
		
		String lastDate = dateFormat.format(date);
		String firstDateManipulation = lastDate.substring(0, 2);
		String monthManipulation = lastDate.substring(3, 5);
		String yearManipulation = lastDate.substring(6, 10);
		
		int montManipulationInt = Integer.parseInt(monthManipulation) - 1;
		
		System.out.println("firstDateManipulation " +firstDateManipulation);
		System.out.println("montManipulationInt " +montManipulationInt);
		System.out.println("yearManipulation " +yearManipulation);
		
		if(Integer.toString(montManipulationInt).contains("02")){
			if(Integer.parseInt(yearManipulation)%400 == 0 || Integer.parseInt(yearManipulation)%4 == 0) {
				firstDateManipulation = "28"; 
			} else {
				firstDateManipulation = "29";
			}
		}else if(montManipulationInt% 2 == 0){
			firstDateManipulation = "30"; 
		} else{
			firstDateManipulation = "31";
		}
		
		System.out.println("dateFirst " +firstDateManipulation);
		
		lastDate = firstDateManipulation+"-"+Integer.toString(montManipulationInt)+"-"+yearManipulation;
		System.out.println("lastDate " +lastDate);
		
		return lastDate;
	}*/
	
	
	/**
	 * Helper method to get Last Date of this month
	 */
	public int getlastDateofThisMonth(String month, int year) {
	    Calendar calendar = Calendar.getInstance();
	    if (year > -1) {
	      calendar.set(Calendar.YEAR, year);
	    }

	    int monthint = Integer.parseInt(month);
	    if (monthint > -1) {
	    	monthint--;
	      calendar.set(Calendar.MONTH, monthint);
	    }
	    
	    System.out.println("Last Date of this month " +calendar.getActualMaximum(Calendar.DATE));

	    return calendar.getActualMaximum(Calendar.DATE);
	  }
	
	/**
	 * Helper method to select Month Range for Custom Range Option
	 */
	public void setMonthComboValueDropDownSelector(String elementId, String comboValue) {
		driver.findElement(By.xpath("//input[@name='"+elementId+"']//following-sibling::img")).click();
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		
		driver.findElement(By.xpath("//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//li[contains(@class,'x-menu-list-item')]//span[contains(text(),'"+comboValue+"')]")).click();
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		
		if(comboValue.contentEquals("Custom Range")){
			
			driver.findElement(By.xpath("//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//ul[@class= 'x-menu-list']//form[contains(@class, 'x-box-layout-ct')]//div[contains(@class, 'x-box-inner')]//div[1]/input//following-sibling::img")).click();
			waitForExtJSAjaxComplete(5);
			driver.findElement(By.xpath("//div[contains(@class, 'x-combo-list x-menu')  and not (contains(@style, 'visibility: hidden'))]//div[contains(@class,'x-combo-list-item') and contains(text(),'January')]")).click();
			
			driver.findElement(By.xpath("//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//ul[@class= 'x-menu-list']//form[contains(@class, 'x-box-layout-ct')]//div[contains(@class, 'x-box-inner')]//div[2]/input//following-sibling::img")).click();
			waitForExtJSAjaxComplete(10);
			driver.findElement(By.xpath("//div[contains(@class, 'x-combo-list x-menu')  and not (contains(@style, 'visibility: hidden'))]//div[contains(@class,'x-combo-list-item') and contains(text(),'2015')]")).click();
			
			WebElement element = driver.findElement(By.xpath("//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//ul[@class= 'x-menu-list']//form[contains(@class, 'x-box-layout-ct')]//div[contains(@class, 'x-box-inner')]//div[3]/input"));
			String id = element.getAttribute("id");
			waitForExtJSAjaxComplete(5);
			DropDown.setExtJsComboValue(driver, id, "March");
			//driver.findElement(By.xpath("//div[contains(@class, 'x-combo-list x-menu')  and not (contains(@style, 'visibility: hidden'))]//div[contains(@class,'x-combo-list-item') and contains(text(),'March')]")).click();
			waitForExtJSAjaxComplete(5);
			
			driver.findElement(By.xpath("//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//ul[@class= 'x-menu-list']//form[contains(@class, 'x-box-layout-ct')]//div[contains(@class, 'x-box-inner')]//div[4]/input//following-sibling::img")).click();
			waitForExtJSAjaxComplete(10);
			driver.findElement(By.xpath("//div[contains(@class, 'x-combo-list x-menu')  and not (contains(@style, 'visibility: hidden'))]//div[contains(@class,'x-combo-list-item') and contains(text(),'2015')]")).click();
			
			driver.findElement(By.xpath("//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-plain-footer x-panel-btns')]//td[@class='x-toolbar-right']//button[contains(@class, 'x-btn-text') and text()='Apply']")).click();
			waitForExtJSAjaxComplete(5);
		}
	}
	
	/**
	 * Helper method to select Custom Date Range For Hour Reading Interval
	 */
	public void setHourComboValueDropDownSelector(String elementId, String comboValue) {
		driver.findElement(By.xpath("//input[@name='"+elementId+"']//following-sibling::img")).click();
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		
		driver.findElement(By.xpath("//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//li[contains(@class,'x-menu-list-item')]//span[contains(text(),'"+comboValue+"')]")).click();
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		
		if(comboValue.contentEquals("Custom Range")){
			
			driver.findElement(By.xpath("//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-column-inner')]/div[2]//div[contains(@class, 'x-date-picker')]//td[@class='x-date-bottom']//em//button[text()='Today']")).click();
			waitForExtJSAjaxComplete(5);

			String g =  "//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-plain-footer x-panel-btns')]//td[@class='x-toolbar-right']//button[contains(@class, 'x-btn-text') and text()='Apply']";
			WebElement element = McsElement.getElementByXpath(driver, g);
			
			if ("chrome".equals(configuration.getBrowser())) {
	            try {
	            
	                ((JavascriptExecutor) driver).executeScript(
	                        "arguments[0].scrollIntoView(true);", element);
	                
	            } catch (Exception e) {
	            	System.out.println("Exception is G " +e);
	            }
	        }
			element.click();
			waitForExtJSAjaxComplete(5);
			
		}
	}
	
	/**
	 * Helper method to set Date Range
	 */
	public void setDateRange(String attribute, String date)
	{
		WebElement dateField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_GAUGE_CHNL, 
				"input", "@name", attribute, true, true);
		dateField.click();
		dateField.clear();
		dateField.sendKeys(date);
		Reporter.log("Set date Range- "+date, true);
	}
}
