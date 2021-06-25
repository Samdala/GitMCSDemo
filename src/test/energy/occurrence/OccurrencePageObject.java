package test.energy.occurrence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.energy.navigator.NavigatorPageObject;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class OccurrencePageObject extends NavigatorPageObject {

	protected final String XPATH_ADD_FORM_WINDOW = "energy properties";

	protected final String XPATH_ADD_FORM_WINDOW_FOOTER = "x-panel-footer x-panel-footer-noborder x-panel-btns";
	
	protected final String PANEL_NAME = "Occurrences";


	public void expandNavigator() {
		if (McsElement.getElementByXpath(driver, "//div[contains(@class, 'slnmNrgMenu')]"
				+ "//span[contains(text(), 'Navigator')]/../..").getAttribute("class").contains("collapsed")) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu", 
				"span", "text()", "Navigator", true, true).click();
		Reporter.log("Expand Navigation", true);
		}
	}

	public void clickAddButton() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//span[text()='"+ PANEL_NAME +"']//..//..//..//..//..//..//..//..//button[text()='Add']").click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click add "+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void clickEditButton() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//span[text()='"+ PANEL_NAME +"']//..//..//..//..//..//..//..//..//button[text()='Edit']").click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click edit "+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void clickDeleteButton() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//span[text()='"+ PANEL_NAME +"']//..//..//..//..//..//..//..//..//button[text()='Delete']").click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click delete "+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void setCode(String code) {	
		WebElement reference = McsElement.getElementByXpath(driver, "//div[contains(@class, '" + XPATH_ADD_FORM_WINDOW + "')]//label[contains(text(),'Code')]//..//input");
		reference.clear();
		reference.sendKeys(code);
		Reporter.log("Set code", true);
	}

	public String getCode() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class, '" + XPATH_ADD_FORM_WINDOW + "')]//input[@name='code']//..//input)[last()]"))
				.getAttribute("value");
	}

	public String getReference() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class, '" + XPATH_ADD_FORM_WINDOW + "')]//input[@name='reference']//..//input)[last()]"))
				.getAttribute("value");
	}

	public void setReference(String reference) {
		WebElement referenceElement = McsElement.getElementByXpath(driver, "//div[contains(@class, '" + XPATH_ADD_FORM_WINDOW + "')]//label[contains(text(),'Reference')]//..//input");
		referenceElement.clear();
		referenceElement.sendKeys(reference);
		Reporter.log("Set reference", true);
	}
	
	public String getOccurrenceTypeClass() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'" + XPATH_ADD_FORM_WINDOW + "')]//input[@name='occurrenceTypeClass']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setOccurrenceTypeClass(String ddItem){
		String elementId = driver.findElement(By
				.xpath("(//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='occurrenceTypeClass'])[last()]")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, ddItem);
		Reporter.log("Set OccurrenceTypeClass - "+ddItem, true);
	}

	public void close() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW, "button", "text()",
				"Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Close Clicked", true);
	}
	
	public void save() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW, "button", "text()",
				"Save", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Clicked", true);
	}
	
	public void saveClose() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW, "button", "text()",
				"Save and Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close Clicked", true);
	}
	
	public void setOccurrenceType(String type) {
		WebElement lookupbtn = McsElement.getElementByXpath(driver, "//div[contains(@class, '" + XPATH_ADD_FORM_WINDOW + "')]//label[contains(text(),'Type')]//..//button");
		waitFocusAndClick(lookupbtn);
		setValueGridLookupWithFilters(type,"Reference");
		Reporter.log("Set OccurrenceType - "+type, true);
	}
	
	public String getOccurrenceType() {
		return McsElement.getElementByXpath(driver, "(//div[contains(@class, '" + XPATH_ADD_FORM_WINDOW + "')]//label[contains(text(),'Type')]//..//input)[last()]")
				.getAttribute("value");

	}
	
	public void setStartDate(String startDate) {
		WebElement dateField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"startDate", true, true);
		dateField.click();
		dateField.clear();
		dateField.sendKeys(startDate);
		dateField.click();
		waitForExtJSAjaxComplete(20);

		Reporter.log("setStartDate " + startDate, true); 
	}
	
	public String getStartDate() {
		return McsElement.getElementByXpath(driver, "(//div[contains(@class, '" + XPATH_ADD_FORM_WINDOW + "')]//input[contains(@name,'startDate')]//..//input)[last()]")
				.getAttribute("value");

	}
	
	public void setStartTime(String startTime) {
		WebElement dateField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"startTime", true, true);
		dateField.click();
		dateField.clear();
		dateField.sendKeys(startTime);
		dateField.click();
		waitForExtJSAjaxComplete(20);
	}
	
	public String getStartTime() {
		return McsElement.getElementByXpath(driver, "(//div[contains(@class, '" + XPATH_ADD_FORM_WINDOW + "')]//input[contains(@name,'startTime')]//..//input)[last()]")
				.getAttribute("value");

	}
	
	public void setEndTime(String endTime) {
		WebElement dateField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"endTime", true, true);
		dateField.click();
		dateField.clear();
		dateField.sendKeys(endTime);
		dateField.click();
		WebElement referenceElement = McsElement.getElementByXpath(driver, "//div[contains(@class, '" + XPATH_ADD_FORM_WINDOW + "')]//label[contains(text(),'Reference')]//..//input");
		referenceElement.click();
		waitForExtJSAjaxComplete(20);
	}
	
	public String getEndTime() {
		return McsElement.getElementByXpath(driver, "(//div[contains(@class, '" + XPATH_ADD_FORM_WINDOW + "')]//input[contains(@name,'endTime')]//..//input)[last()]")
				.getAttribute("value");

	}
	
	public void setEndDate(String endDate) {
		WebElement dateField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"endDate", true, true);
		dateField.click();
		dateField.clear();
		dateField.sendKeys(endDate);
		dateField.click();
		waitForExtJSAjaxComplete(20);
	}
	
	public String getEndDate() {
		return McsElement.getElementByXpath(driver, "(//div[contains(@class, '" + XPATH_ADD_FORM_WINDOW + "')]//input[contains(@name,'endDate')]//..//input)[last()]")
				.getAttribute("value");

	}

	public void setImpactElectricity(Boolean bImpact){
		WebElement checkbox = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"electricity", true, true);
		if ((checkbox.getAttribute("checked") != null ) == !bImpact) {
			checkbox.click();
		}
		Reporter.log("set impact electricity", true);
	}
	
	public void setImpactCombustibles(Boolean bImpact){
		WebElement checkbox = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"combustibles", true, true);
		if ((checkbox.getAttribute("checked") != null ) == !bImpact) {
			checkbox.click();
		}
		Reporter.log("set impact combustibles", true);
	}
	
	public void setImpactWater(Boolean bImpact){
		WebElement checkbox = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"water", true, true);
		if ((checkbox.getAttribute("checked") != null ) == !bImpact) {
			checkbox.click();
		}
		Reporter.log("set impact water", true);
	}
	
	public void setDescription(String description) {
		WebElement textBox = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "textarea", "@name",
				"description", true, true);
		textBox.clear();
		textBox.sendKeys(description);
		Reporter.log("Set Description " + description, true);
	}
	
	public String getDescription() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//textarea[@name='description']//..//textarea)[last()]"))
				.getAttribute("value");
	}

	public boolean getImpactState(String elementName) {
		if (McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				elementName, true, true).getAttribute("checked") != null) {
			return true;
		}
		return false;
	}
	
	public String getFieldValue(String fieldName) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='"+fieldName+"']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void changeTabOccurences(String panelId, String tabName) {
		driver.findElement(By.xpath("//div[contains(@id, '"+panelId+"')]//span[contains(@class, 'x-tab-strip-text') and text()='"+tabName+"']")).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("change tab to "+tabName, true);
	}
	
	public void changeTabCustomized(String panelId, String tabName) {
		WebElement element = driver.findElement(By.xpath("//div[contains(@id, '"+panelId+"')]//div[contains(@class, 'x-tab-panel-header-noborder')]//span[contains(@class, 'x-tab-strip-text') and text()='"+tabName+"']"));
		
		if ("chrome".equals(configuration.getBrowser())) {
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", element);
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].click();", element);
            } catch (Exception e) {
            	System.out.println("Exception is G " +e);
            }
        }
		waitForExtJSAjaxComplete(20);
		Reporter.log("change tab to "+tabName, true);
	}
	
	/**
	 * Helper method to get Grid Column No
	 */
	public String getGridColNo(String attributeValue, String colName) {
		WebElement element = driver.findElement(By.xpath("//div[contains(@id, '"+attributeValue+"')]//div[contains(@class,'x-grid3-hd') and text()= '"+colName+"']"));
		
		String colClass = element.getAttribute("class");
		
		String colNo = colClass.substring(colClass.lastIndexOf("-") +1);
		
		return colNo;
	}
	
	/**
	 * Helper method to verify if Respective date is in 1st row or not
	 * @param attributeValue occupancy_hours_grid_labels
	 * @param rowNum 1 
	 * @param dateValue 
	 * @return
	 */
	private boolean verifyDateIsAvalilableInGrid(String attributeValue, String rowNum, String dateValue) {
		try{
			driver.findElement(By.xpath("//div[contains(@id, '"+attributeValue+"')]//div[@class='x-grid3-body']//div["+rowNum+"]//div[contains(@class, 'x-grid3-cell-inner') and text()='"+dateValue+"']"));
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public String verifyOccupancyHoursNotfilled(String attributeValue, String rowNum, String dateValue, String value){
		
		boolean status = verifyDateIsAvalilableInGrid(attributeValue, rowNum, dateValue);
		System.out.println(status);
		String qtipText = "";
		
		if(status){
			String classValue = driver.findElement(By.xpath("//div[contains(@id, 'occupancy_hours_grid')]//div[contains(@class, 'x-grid3-hd-inner') and text()='"+value+"']")).getAttribute("class");
			
			String colNo = classValue.substring(classValue.lastIndexOf("-") +1);
			
			qtipText = driver.findElement(By.xpath("//div[contains(@id, 'occupancy_hours_grid')]//div[@class='x-grid3-body']//div["+rowNum+"]//div[@class='x-grid3-cell-inner x-grid3-col-"+colNo+" x-unselectable']")).getAttribute("ext:qtip");
			
		}
		
		return qtipText;
	}
	
	public String convertDateFormat(int noOfDays, int day){
			
			Calendar calendar = Calendar.getInstance();
			
			calendar.set(Calendar.DAY_OF_WEEK, day);
			
			//calendar.add(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() - calendar.get(Calendar.DAY_OF_WEEK));
			
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			
			Date date = calendar.getTime();
			
			System.out.println(dateFormat.format(date));
			
			return dateFormat.format(date);
		}
}
