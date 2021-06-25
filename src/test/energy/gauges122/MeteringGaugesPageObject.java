package test.energy.gauges122;

//import java.util.concurrent.TimeUnit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class MeteringGaugesPageObject extends AbstractMcsTestSuite {


	protected final String ADD_GAUGES_FORM_CLASS = "slnmGaugeId";
	
	protected final String DIALOG_GAUGE_CHNL = "slnmGaugeChnlMeasId";
	
	protected final String DIALOG_MEASUREMENT = "slnmMeasId";

	protected final String ADD_GAUGES_FOOTER_CLASS = "x-window-footer x-panel-btns";
	
	//Changed Variable NAme
	protected final String GAUGES_GRID_CLASS = "x-tab-panel x-tab-panel-noborder";
	
	protected final String GAUGES_CHANELS_GRID_CLASS =  "x-panel channels-tab x-panel-noborder";
	
	protected final String CHANNEL_MEASUREMENTS_WINDOW_HEADER = "Channel Measurements";
	
	protected final String ADD_GAUGES_WINDOW_HEADER = "Add Gauge";
	
	protected final String SELECT_A_LOCATION_WINDOW_HEADER = "Select a Location";
	
	protected final String ADD_MEASUREMENT_WINDOW_HEADER = "Add Measurement";
	
	protected final String CHANGE_VISIBLE_COLUMNS_HEADER = "Change visible columns";
	
	protected final String CHANGE_COLUMNS_CLASS_NAME = "x-window x-resizable-pinned";

	public void goToDefinition(String definition) {
		driver.get(configuration.getApplicationUrl() + "/frame.php?relay="
				+ definition);
	};

	public void expandMetering() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"span", "text()", "Metering", true, true).click();
		Reporter.log("Expand Metering", true);
	}

	public void openMeteringEntity(String entity) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"button", "text()", entity, true, true).click();
		Reporter.log("open analysis " + entity, true);
	}

	public void clickAddButton(String gridClass) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Add", true, true).click();
		Reporter.log("click add button", true);
	}

	public void clickEditButton(String gridClass) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Edit", true, true).click();
		Reporter.log("click edit button", true);
	}

	public void clickDeleteButton(String gridClass) {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Delete", true, true).click();
		clickOnDialogButton("Yes");
		clickOnDialogButton("OK");
		
		Reporter.log("Delete selected GAUGES measure" + " (" + timer.stop() + "ms)", true);
	}
	
	public String getReference() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_GAUGES_FORM_CLASS, "input",
				"@name", "reference", true, true).getAttribute("value");
	}

	public void setReference(String reference) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_GAUGES_FORM_CLASS, "input", "@name",
				"reference", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(reference);
		Reporter.log("set reference " + reference, true);
	}
	
	public String getItemID(String arrtibuteValue) {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_GAUGES_FORM_CLASS, "input",
				"@name", "itemId", true, true).getAttribute(arrtibuteValue);
	}
	
	public String getCode() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_GAUGES_FORM_CLASS, "input",
				"@name", "code", true, true).getAttribute("value");
	}
	
	public void setCode(String code) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_GAUGES_FORM_CLASS, "input", "@name",
				"code", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(code);
		Reporter.log("set code " + code, true);
	}

	public void setCommissioningDate(String code) {
		WebElement date = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_GAUGES_FORM_CLASS, "input", "@name",
				"commissioningDate", true, true);
		date.clear();
		date.sendKeys(code);
		Reporter.log("set date " + code, true);
	}

	public String getCommissioningDate() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_GAUGES_FORM_CLASS, "input",
				"@name", "commissioningDate", true, true).getAttribute("value");
	}

	public String getSerialNumber() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_GAUGES_FORM_CLASS, "input",
				"@name", "serialNumber", true, true).getAttribute("value");
	}

	public void setSerialNumber(String reference) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_GAUGES_FORM_CLASS, "input", "@name",
				"serialNumber", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(reference);
		Reporter.log("set reference " + reference, true);
	}
	
	public String getManufacturer() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_GAUGES_FORM_CLASS, "input",
				"@name", "manufacturer", true, true).getAttribute("value");
	}
	
	public String getObjectClass() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_GAUGES_FORM_CLASS, "input",
				"@name", "class", true, true).getAttribute("value");
	}

	public void setManufacturer(String reference) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_GAUGES_FORM_CLASS, "input", "@name",
				"manufacturer", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(reference);
		Reporter.log("set reference " + reference, true);
	}
	
	public String getDescription() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_GAUGES_FORM_CLASS, "textarea",
				"@name", "description", true, true).getAttribute("value");
	}

	public void setDescription(String reference) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_GAUGES_FORM_CLASS, "textarea", "@name",
				"description", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(reference);
		Reporter.log("set reference " + reference, true);
	}
	
	public void setStatus(String proposerName){
		clickLookup(ADD_GAUGES_FORM_CLASS, "status");
		setValueGridLookup(proposerName);
//		waitForExtJSAjaxComplete(5);
	}
	
	public String getStatus() {
		return driver
				.findElement(
						By.xpath("(//form[contains(@class,'x-panel-body x-panel-body-noheader x-panel-body-noborder x-form')]//input[@name='status']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setScope(String proposerName){
		clickLookup(ADD_GAUGES_FORM_CLASS, "scopeLocation");
		setValueTreeLookup(proposerName);
//		waitForExtJSAjaxComplete(5);
	}
	
	public String getScope() {
		return driver
				.findElement(
						By.xpath("(//form[contains(@class,'x-panel-body x-panel-body-noheader x-panel-body-noborder x-form')]//input[@name='scopeLocation']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	/**
	 * Helper method to type Notes in Notes Tab
	 */
	public void setNotes(String notes){
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_GAUGES_FORM_CLASS, "textarea", "@name",
				"notes", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(notes);
		Reporter.log("set notes " + notes, true);
	}
	
	public void setTimeZone(String proposerName){
		clickLookup(ADD_GAUGES_FORM_CLASS, "timezone");
		setValueGridLookup(proposerName);
//		waitForExtJSAjaxComplete(5);
	}
	
	public String getTimeZone() {
		return driver
				.findElement(
						By.xpath("(//form[contains(@class,'x-panel-body x-panel-body-noheader x-panel-body-noborder x-form')]//input[@name='timezone']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setLocation(String proposerName){
		clickLookup(ADD_GAUGES_FORM_CLASS, "location");
		setValueTreeLookup(proposerName);
//		waitForExtJSAjaxComplete(5);
	}
	
	public String getLocation() {
		return driver
				.findElement(
						By.xpath("(//form[contains(@class,'x-panel-body x-panel-body-noheader x-panel-body-noborder x-form')]//input[@name='location']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public String getModel() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_GAUGES_FORM_CLASS, "input",
				"@name", "model", true, true).getAttribute("value");
	}

	public void setModel(String reference) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_GAUGES_FORM_CLASS, "input", "@name",
				"model", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(reference);
		Reporter.log("set reference " + reference, true);
	}
	
	public String getAccessInstructions() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_GAUGES_FORM_CLASS, "textarea",
				"@name", "accessDirectives", true, true).getAttribute("value");
	}

	public void setAccessInstructions(String reference) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_GAUGES_FORM_CLASS, "textarea", "@name",
				"accessDirectives", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(reference);
		Reporter.log("set reference " + reference, true);
	}
	
	public void saveClose() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_GAUGES_FOOTER_CLASS, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		waitForMaskDisappear();
	}
	
	public void save() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_GAUGES_FOOTER_CLASS, "button",
				"text()", "Save", true, true).click();
		waitForMaskDisappear();
	}

	public void close() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", ADD_GAUGES_FOOTER_CLASS, "button", "text()",
				"Close", true, true).click();
		waitForMaskDisappear();
	}
	
	public void changeTab(String tabName) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"li", "@id", tabName+"-tab", "span", "@class", "x-tab-strip-text", true, true).click();
		Reporter.log("change tab to "+tabName, true);
	}
	
	public boolean isRowInGridChanelPresent(String gridValue) {
		try {
			McsElement.getElementByXpath(driver, "//div[contains(@class, 'slnmGaugeId')]//div[text()='"+gridValue+"']");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//////////////////////GaugesFormValidation///////////////////////
	
	public String getFieldValue(String dialogClass, String fieldName) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+dialogClass+"')]//input[@name='"+fieldName+"']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public boolean checkDefaultField(String attribute, String attributeValue, String columnName, String fieldValue){ 
		
		filterGrid(attribute, attributeValue, fieldValue, columnName); 
		Reporter.log("Filter '"+columnName+"' column by '"+fieldValue+"' value", true);
		
		return McsElement.isElementPresent(driver, "//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'check-col-on')]");
				
	} 
	
	 public void clearField(String fieldNameTag, String fieldName) {
		WebElement field =  driver.findElement
				(By.xpath("(//div[contains(@class, '"+ADD_GAUGES_FORM_CLASS+"')]//input[contains(@name, '"+fieldNameTag+"')]//..//input)[last()]"));
		field.click();
		field.clear();
		Reporter.log("Clear "+fieldName+" field", true);
	 }
	 
	public boolean checkMandatoryFieldSave(String dialog){
		if (driver.findElement(
					By.xpath("//div[contains(@class,'"+dialog+"')]//span[contains(@class,'window-header')]")).getText().contains("Add"))
			return true;
		else {
			return false;
		}
	}
	
	//Reopen Gauge Form being either saved or not
	 public boolean reopenGaugeForm(){
		
			 if (!checkMandatoryFieldSave(ADD_GAUGES_FORM_CLASS)) {
				 
				 close();
				 
				 waitForExtJSAjaxComplete(20);
				 
				 clickAddButton(GAUGES_GRID_CLASS);
					
				 waitForExtJSAjaxComplete(20);
				     return false;
			 }
			 else {
				 
				 close();
				 
				 waitForExtJSAjaxComplete(20);
					 
				 clickOnDialogButton("Yes");
				 
				 waitForExtJSAjaxComplete(20);
			     
				 clickAddButton(GAUGES_GRID_CLASS);
					
				 waitForExtJSAjaxComplete(20);
			     
				 return true;
			 }
		}
		////////////////////////////GaugesMeasurementsCRUDTestSuite///////////////////////////
	 
	 public void clickViewMeasurementsButton()
		{
			WebElement viewButton = McsElement.getElementByPartAttributeValueAndParentElement(driver,
					"div", "@class", ADD_GAUGES_FORM_CLASS, "button", "@class", "x-btn-text",
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
	
	public void clickButton(String buttonName, String dialogClass)
	{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", dialogClass, "button", "@class", "x-btn-text",
				"text()", buttonName, true, true).click();
		Reporter.log("Click "+buttonName+" button", true);
	}
	
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
	
	public void clickCancelXwindow() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-window-footer x-window-footer-noborder x-panel-btns", "button",
				"text()", "Cancel", true, true).click();
		waitForElementDisappear("//div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]");
		Reporter.log("Click Cancel", true);
	}
	
	public void saveClose(String dialogClass) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", dialogClass, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		Reporter.log("Save and Close", true);
	}
	
	public void clearFieldMeas(String fieldNameTag, String fieldName) {
		WebElement field =  driver.findElement
				(By.xpath("(//div[contains(@class, '"+DIALOG_MEASUREMENT+"')]//input[contains(@name, '"+fieldNameTag+"')]//..//input)[last()]"));
		field.click();
		field.clear();
		Reporter.log("Clear "+fieldName+" field", true);
	 }
	
	public void close(String dialogClass) {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", dialogClass, "button", "text()",
				"Close", true, true).click();
		Reporter.log("Click Close button", true);
	}
	
	public boolean checkFormExists(String dialog){
		try {
			driver.findElement(
					By.xpath("//div[contains(@class,'"+dialog+"')]//span[contains(@class,'window-header')]")).getText().contains("Add Measurement");
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public boolean reopenMeterMeasurementForm(){
		
		 if (!checkFormExists(DIALOG_MEASUREMENT)) {
			 
			 clickButton("Add", DIALOG_GAUGE_CHNL);
		     
		     waitForExtJSAjaxComplete(20);

		     return false;
		 }
		 else {
			 
			 close(DIALOG_MEASUREMENT);
			 
			 waitForExtJSAjaxComplete(20);
			 
			 waitForExtJSAjaxComplete(20);
		     
			 clickButton("Add", DIALOG_GAUGE_CHNL);
		     
		     waitForExtJSAjaxComplete(20);
		     
			 return true;
		 }
	}
	
	/**
	 * Helper method to verify Auditing Field is checked or not
	 * */
	public void isAuditingFieldChecked (String windowTitle) {
		
		WebElement reference = driver.findElement(By.xpath("//div[contains(@id,'"+ windowTitle+"') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-grid3-body')]//div[contains(@id, 'group-Auditing')]//div[contains(@class, 'grid-group-title')]//..//input[@type='checkbox']"));
		waitForExtJSAjaxComplete(10);
		
		System.err.println(reference.isSelected());
			
		if(reference.isSelected()){
			reference.click();
			Reporter.log("Auditing Header is Unchecked");
			waitForExtJSAjaxComplete(5);
			reference.click();
			Reporter.log("Auditing Header is Checked");
		} else {
			reference.click();
			Reporter.log("Auditing Header is Unchecked");
			waitForExtJSAjaxComplete(5);
		}
		Reporter.log("Field Auditing Header is Already Checked ", true);
	}
	
	/**
	 * Helper method to verify All columns are checked below Auditing
	 */
	public boolean isAuditingColumnsChecked(String windowTitle,String colClass, String colName ){
		try{
			driver.findElement(By.xpath("//div[contains(@id,'"+ windowTitle+"') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-grid3-body')]//div[contains(@id, 'group-Auditing-bd') and contains(@class, 'group-body')]//div[contains(@class, '"+colClass+"')and text()='"+colName+"']/ancestor::div[contains(@class, 'row-selected')]"));
			return true;
		} catch(Exception e){
			System.out.println("Exception is " +e);
			return false;
		}
	}
	
	/**
	 * Helper method to verify All columns are checked below General
	 */
	public boolean isGeneralColumnsChecked(String windowTitle,String colClass, String colName ){
		try{
			driver.findElement(By.xpath("//div[contains(@id,'"+ windowTitle+"') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-grid3-body')]//div[contains(@id, 'group-General-bd') and contains(@class, 'group-body')]//div[contains(@class, '"+colClass+"')and text()='"+colName+"']/ancestor::div[contains(@class, 'row-selected')]"));
			return true;
		} catch(Exception e){
			System.out.println("Exception is " +e);
			return false;
		}
	}
	
	/**
	 * Helper method to verify General Field is checked or not
	 * */
	public void isGeneralFieldChecked (String windowTitle) {
		WebElement reference = driver.findElement(By.xpath("//div[contains(@id,'"+ windowTitle+"') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-grid3-body')]//div[contains(@id, 'group-General')]//div[contains(@class, 'grid-group-title')]//..//input"));
		waitForExtJSAjaxComplete(10);
		
		System.err.println(reference.isSelected());
		
		if(reference.isSelected()){
			reference.click();
			Reporter.log("General Header is Unchecked");
			waitForExtJSAjaxComplete(5);
			reference.click();
			Reporter.log("General Header is Checked");
		} else {
			reference.click();
			Reporter.log("General Header is Checked");
			waitForExtJSAjaxComplete(5);
		}
		
		Reporter.log("Field General Header is Already Checked ", true);
	}
	
	/**
	 * Helper method to verify Properties Tab in Change columns Window
	 */
	public boolean isPropertiesTabAvailable(String windowTitle) {
		try{
			driver.findElement(By.xpath("//div[contains(@id,'"+ windowTitle+"') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-panel-header-noborder')]//span[contains(@class, 'header-text') and text()='Properties']"));
			return true;
		} catch(Exception e){
			System.out.println("Exception is " +e);
			return false;
		}
	}
	
	/**
	 * Helper method to verify Columns Tab in Change columns Window
	 */
	public boolean isColumnsTabAvailable(String windowTitle) {
		try{
			driver.findElement(By.xpath("//div[contains(@id,'"+ windowTitle+"') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-panel-header-noborder')]//span[contains(@class, 'header-text') and text()='Columns']"));
			return true;
		} catch(Exception e){
			System.out.println("Exception is " +e);
			return false;
		}
	}
	
	/**
	 * Helper method to change the date format
	 */
	public String formatDates(String dateInGrid){
		try{
    		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        	Date date1 = sdf.parse(dateInGrid);
 
        	System.out.println(sdf.format(date1));
 
        	return sdf.format(date1); 
        	 
    	}catch(ParseException ex){
    		ex.printStackTrace();
    		return "date Format Error";
    	}
	}
	
	/**
	 * Helper method to get Audit Fields
	 */
	public String getAuditText() {
		WebElement reference = driver.findElement(By.xpath("//div[contains(@class, 'x-window-footer x-panel-btns')]//table//tr[@class='x-toolbar-left-row']//td[2]/div"));
		String text = reference.getAttribute("textContent"); 
		return text;
	}
	
	/**
	 * Helper method to substring the date from Audit Text
	 */
	public String subStringCreatedOnFromAudit(){
		String auditText = getAuditText();
		
		String split[] = auditText.split("\\n");
		System.out.println("1st line " +split[0]);
		String createdBy = split[0].toString();
		
		return createdBy;
	}
	
	/**
	 * Helper method to substring the date from Audit Text
	 */
	public String subStringModifiedOnFromAudit(){
		String auditText = getAuditText();
		
		String split[] = auditText.split("\\n");
		System.out.println("2nd line " +split[1]);
		String modifiedBy = split[1].toString();
		
		return modifiedBy;
	}
	
	/**
	 * Helper method to substring timestamp from Audit field
	 */
	public String subStringTimeStampFromAuditFields(String attribute){
		String timeStamp = StringUtils.substringBetween(attribute, "on", "by");
		System.out.println("timeStamp " +timeStamp);
		return timeStamp;
	}
	
	/**
	 * Helper method to get User From Mcs Portal
	 */
	public String getLoggedUserName() {
		WebElement xpath = driver.findElement(By.xpath("//div[contains(@class, 'mcs-tb-glossy-strong')]//table[@class='x-toolbar-right-ct']//table[@id='top-account-menu']//tr[2]//td[2]//em[contains(@class, 'x-btn-arrow')]/button"));
		
		String userName = xpath.getAttribute("textContent");
		
		return userName;
	}
	
	 public void clickIntervalDataTab()
		{
			McsElement.getElementByPartAttributeValueAndParentElement(driver,
					"ul", "@class", "x-tab-strip", "span", "text()", "Interval Data", true, true).click();
			Reporter.log("Click on Interval Data tab", true);
		}
	 
	 /**
	 * Helper method to close Channel Measurements using Tool Close (X button)
	 */
	public void closeUsingToolBarForDiaogMeasurement(String xwindowTitle) {
		WebElement element = driver.findElement(By.xpath("//div[contains(@class, '"+DIALOG_MEASUREMENT+"') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-tool-close')]"));
		
		if ("chrome".equals(configuration.getBrowser())) {
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", element);
            } catch (Exception e) {
            	System.out.println("Exception is G " +e);
            }
        }
		
		element.click();
		waitForExtJSAjaxComplete(20);
	}
	
	/**
	  * Helper method to select Channel From Dropdown of channel Measurements Window
	  */
	 public void setChannelMeasurements(String className, String status){
		String id = McsElement
				.getElementByXpath(
						driver,
						"//div[contains(@class, '"+className+" ')]//div[contains(@class, 'x-form-item')]//label[text()='Channel']//..//input[contains(@class, 'x-form-text x-form-field')]")
				.getAttribute("id");
					
		DropDown.setExtJsComboValue(driver, id, status);
			
		waitForExtJSAjaxComplete(25);
			
		Reporter.log("Set Status to" + status, true);
	 }
	 
	 /**
	  * Helper method to get Channel From Dropdown of channel Measurements Window
	  */
	 public String getChannelMeasurements(String className){
		String value = McsElement
				.getElementByXpath(
						driver,
						"//div[contains(@class, '"+className+" ')]//div[contains(@class, 'x-form-item')]//label[text()='Channel']//..//input[contains(@class, 'x-form-text x-form-field')]")
				.getAttribute("value");
					
		return value;
	 }
	 
	 /**
	 * Helper method to get Value of Any Column from Navigator gauges grid
	 */
	public String getValueOfAnyColumn(String attribute, String attributeValue, String columnName) {
			
		Timer timer = new Timer().start();

		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		String lineIDColumnNumber = getGridColumnNumber(attribute, attributeValue, columnName);

		WebElement element = driver.findElement(By.xpath("//div[contains("
				+ attribute + ",'" + attributeValue
				+ "') and not (contains(@class, 'x-hide-display'))]//"
				+ "div[@class='x-grid3-cell-inner x-grid3-col-"
				+ lineIDColumnNumber + "']"));
		String ele = element.getText();

		timer.stop();
		
		return ele;
	}
	
	/**
	 *  Helper method to get column number in Grid 
	 */
	public String getGridColumnNumber(String attribute, String attributeValue, String columnName){
		
		WebElement columnClassElement = driver
				.findElement(By
						.xpath("//div[contains("
								+ attribute
								+ ",'"
								+ attributeValue
								+ "') and not (contains(@class, 'x-hide-display'))]//div[contains(@class,'x-grid3-hd') and contains(text(), '"
								+ columnName + "')][last()]"));
		String columnClass = columnClassElement.getAttribute("class");
		String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);
		return columnNumber;
	}
	
	/**
	 * Helper method to verify grid is empty
	 */
	public String verifyGridIsEmpty(String xwindowTitle) {
		WebElement gridText = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//div[@class='x-grid3-body']//div[@class='x-grid-empty']"));
		String gridTextString = gridText.getText();
		return gridTextString;
	}
	
	/**
	 * Helper method to verify Color Code from Grid
	 */
	public boolean verifyGreenColorCode(String xwindowClass, String usageValue){
		try {
			WebElement gridText = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowIdByClass(xwindowClass)+"')]//*[@class='x-grid3']//div[contains(@class, 'x-grid3-col-2') and text()='"+usageValue+"']/ancestor::div[contains(@class, 'mcsgridrow_46A546')]"));
			return true;
		} catch(Exception e){
			System.out.println("Exception is " +e);
		}
		
		return false;
	}
	
	/**
	 * Helper method to close Channel Measurements using Tool Close (X button)
	 */
	public void closeUsingToolBar(String xwindowTitle) {
		WebElement element = driver.findElement(By.xpath("//div[contains(@class, '"+xwindowTitle+"')]//div[contains(@class, 'x-tool-close')]"));
		element.click();
		waitForExtJSAjaxComplete(20);
	}
	
	/**
	 * Helper method to verify Delete button is not available in Measurement Page	
	 */
	public boolean verifyButtonsAvailableInToolBar(String xwindowTitle, String buttonName) {
		String exception = "";
		
		try {
			WebElement element = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//button[contains(@class, 'x-btn-text') and text()='"+buttonName+"']"));
		} catch(Exception e) {
			exception = e.toString(); 
			System.err.println("NoSuch Element Exception" + exception);
			return false;
		}
		
		return true;
	}
}

