package test.energy.meters;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import test.configuration.Configuration;
import test.energy.EnergyBaseTestSuite;
import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class MetersPageObject extends EnergyBaseTestSuite {


	protected final String DIALOG_METER = "slnmMeterId";
	protected final String DIALOG_CHANNEL = "slnmMeterChnlId";
	protected final String DIALOG_MTR_CHANNEL = "slnmMeterChnlId";
	protected final String DIALOG_MTR_CHNL_MEASSUREMENTS = "slnmMeterChnlMeasId";
	protected final String METER_GRID_CLASS = "x-tab-panel x-tab-panel-noborder";
	protected final String DIALOG_MEASUREMENT = "slnmMeasId";
	protected final String DIALOG_PROPERTIES = "energy properties x-resizable-pinned";
	protected final String DIALOG_PROPERTIES_NOT_TRUNK = "energy crud-form x-resizable-pinned";
	
	protected final String ADD_MEASUREMENT_WINDOW_HEADER = "Add Measurement";
	
	protected final String CHANGE_VISIBLE_COLUMNS_HEADER = "Change visible columns";
	
	protected final String ADD_SCOPE_WINDOW_HEADER = "Add Objects to Scope";
	
	protected final String EDIT_SCOPE_WINDOW_HEADER = "Edit Objects in Scope";	
	
	protected final String CHANNEL_MEASUREMENTS_WINDOW_HEADER = "Channel Measurements";
	
	protected final String ADD_METER_WINDOW_HEADER = "Add Meter";
	
	protected final String EDIT_METER_WINDOW_HEADER = "Edit Meter";
	
	//****** Element Verification in Scope Tab**************//
	protected final String EFFECTIVE_DATE_SCOPETAB_XPATH = "//div[contains(@class, 'slnmMeterId')]//input[@name='dateStart']";
	
	protected final String ADD_SCOPETAB_XPATH = "//div[contains(@class, 'slnmMeterId')]//button[contains(@class, 'addButton')]";
	
	protected final String EDIT_SCOPETAB_XPATH = "//div[contains(@class, 'slnmMeterId')]//button[contains(@class, 'editButton')]";
	
	protected final String CLOSE_SCOPETAB_XPATH = "//div[contains(@class, 'slnmMeterId')]//button[contains(@class, 'icon-scope-end-date')]";
	
	protected final String SAVE_CLOSE_SCOPETAB_XPATH = "//div[contains(@class, 'slnmMeterId')]//button[contains(@class, 'x-btn-text') and text()='Save and Close']";
	
	protected final String SAVE_SCOPETAB_XPATH = "//div[contains(@class, 'slnmMeterId')]//button[contains(@class, 'x-btn-text') and text()='Save']";
	
	protected final String CLOSE_FOOTER_SCOPETAB_XPATH = "//div[contains(@class, 'slnmMeterId')]//button[contains(@class, 'x-btn-text') and text()='Close']";
	
	protected final String OBJECTS_IN_SCOPE_TAB = "//div[contains(@class, 'slnmMeterId')]//div[contains(@class, 'x-grid3-hd-inner') and text()='Objects in Scope']";
	
	protected final String COMMODITY_ENDUSE_IN_SCOPE_TAB_122 = "//div[contains(@class, 'slnmMeterId')]//div[contains(@class, 'x-grid3-hd-inner') and text()='Commodity End-use']";
	
	protected final String COMMODITY_ENDUSE_IN_SCOPE_TAB = "//div[contains(@class, 'slnmMeterId')]//div[contains(@class, 'x-grid3-hd-inner') and text()='Commodity End Use']";
	
	protected final String DISTRIBUTION_RATIO_SCOPE_TAB = "//div[contains(@class, 'slnmMeterId')]//div[contains(@class, 'x-grid3-hd-inner') and text()='Distribution Ratio']";
	
	protected final String DISTRIBUTION_PERCENTAGE_SCOPE_TAB = "//div[contains(@class, 'slnmMeterId')]//div[contains(@class, 'x-grid3-hd-inner') and text()='Distribution, %']";
	
	
	//******** Element Verification Edit Object Scope Tab*****///
	protected final String EFFECTIVE_DATE_OBJECTS_SCOPE_XPATH = "//div[contains(@class, 'energy crud-form')]//input[@name='dateStart']";
	
	protected final String ADD_ENERGY_OBJECT_SCOPE_XPATH = "//div[contains(@class, 'energy crud-form')]//button[contains(@class, 'icon-scope-add-energy-object')]";
	
	protected final String ADD_MAINTENANCE_OBJECT_SCOPE_XPATH = "//div[contains(@class, 'energy crud-form')]//button[contains(@class, 'icon-scope-add-maintenance')]";
	
	protected final String DELETE_ENERGY_OBJECT_SCOPE_XPATH = "//div[contains(@class, 'energy crud-form')]//button[contains(@class, 'icon-scope-remove')]";
	
	protected final String OBJECTS_IN_SCOPE_HEADER = "//div[contains(@class, 'energy crud-form')]//div[@id='scopeGridCmp']//div[contains(@class, 'x-grid3-hd-inner') and text()='Objects in Scope']";
	
	protected final String COMMODITY_ENDUSE_IN_SCOPE_HEADER_122 = "//div[contains(@class, 'energy crud-form')]//div[@id='scopeGridCmp']//div[contains(@class, 'x-grid3-hd-inner') and text()='Commodity End-use']";
	
	protected final String COMMODITY_ENDUSE_IN_SCOPE_HEADER = "//div[contains(@class, 'energy crud-form')]//div[@id='scopeGridCmp']//div[contains(@class, 'x-grid3-hd-inner') and text()='Commodity End Use']";
	
	protected final String DISTRIBUTION_RATIO_SCOPE_HEADER = "//div[contains(@class, 'energy crud-form')]//div[@id='scopeGridCmp']//div[contains(@class, 'x-grid3-hd-inner') and text()='Distribution Ratio']";
	
	protected final String DISTRIBUTION_PERCENTAGE_SCOPE_HEADER = "//div[contains(@class, 'energy crud-form')]//div[@id='scopeGridCmp']//div[contains(@class, 'x-grid3-hd-inner') and text()='Distribution, %']";
	
	protected final String NOTES_IN_SCOPE_HEADER = "//div[contains(@class, 'energy crud-form')]//textarea[@name='notes']";
	
	protected final String SAVE_OBJECT_SCOPE_XPATH = "//div[contains(@class, 'energy crud-form')]//button[contains(@class, 'x-btn-text') and text()='Save']";
	
	protected final String SAVECLOSE_OBJECT_SCOPE_XPATH = "//div[contains(@class, 'energy crud-form')]//button[contains(@class, 'x-btn-text') and text()='Save and Close']";
	
	protected final String CLOSE_OBJECT_SCOPE_XPATH = "//div[contains(@class, 'energy crud-form')]//button[contains(@class, 'x-btn-text') and text()='Close']";
	
	protected final String ADD_LOCATION_122_XPATH = "//div[contains(@class, 'energy crud-form')]//button[contains(@class, 'icon-scope-add-location')]";
	
	
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
	
	public void expandNavigator() {
		if (McsElement.getElementByXpath(driver, "//div[contains(@class, 'slnmNrgMenu')]"
				+ "//span[contains(text(), 'Navigator')]//..//..").getAttribute("class").contains("collapsed")) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu", 
				"span", "text()", "Navigator", true, true).click();
		Reporter.log("Expand Navigation", true);
		}
	}

	public void openMeteringEntity(String entity) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"button", "text()", entity, true, true).click();
		Reporter.log("Open Metering - " + entity, true);
	}
	
	public void clickMetersInfrastructureTab(){
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-tab-panel x-tab-panel-noborder",
				"span", "text()", "Meter Infrastructure", true, true).click();
		Reporter.log("Click on Meter Infrastructure tab", true);
	}
	
	public void clickOnSupplyPoint(String supplyPointCode) {
		/*McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@id", "hierarchy",
				"*", "text()", supplyPointCode, true, true).click();*/
		WebElement ele = driver.findElement(By.xpath("//div[contains(@id, 'hierarchy')]//*[contains(text(), '"+supplyPointCode+"')]"));
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			ele.click();
		}catch(Exception e){
			e.printStackTrace();
		}
		Reporter.log("Click on Supply Point - "+supplyPointCode, true);
	}
	
	public void clickOnSupplyPointJS(String supplyPointCode) {
		
		WebElement ele = driver.findElement(By.xpath("//div[contains(@id, 'hierarchy')]//*[contains(text(), '"+supplyPointCode+"')]"));
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			ele.click();
			ele.click();
		}catch(Exception e){
			e.printStackTrace();
		}
		Reporter.log("Click on Supply Point - "+supplyPointCode, true);
		
		/*String test = "";
		try {
			test = McsElement.getElementByXpath(driver, "//div[contains(@id,'hierarchy')]//*[contains(text(), '"+ supplyPointCode + "')]/../..").getAttribute("id");
		}
		catch(Exception e) {}*/
		//javaScriptClick(McsElement.getElementByPartAttributeValueAndParentElement(driver,
		//		"div", "@id", "hierarchy",
		//		"*", "text()", supplyPointCode, false, false));
		//McsElement.getElementByXpath(driver, "//div[contains(@id,'hierarchy')]//*[contains(text(), '"+ supplyPointCode + "')]").click();
		/*try {
		McsElement.getElementByXpath(driver, "//div[contains(@id,'hierarchy')]//*[contains(text(), '"+ supplyPointCode + "')]/..").click();
		}
		catch(Exception e) {}
		try {
		McsElement.getElementByXpath(driver, "//div[contains(@id,'hierarchy')]//*[contains(text(), '"+ supplyPointCode + "')]/../..").click();
		}
		catch(Exception e) {}
		try {
		McsElement.getElementByXpath(driver, "//div[contains(@id,'hierarchy')]//*[contains(text(), '"+ supplyPointCode + "')]/../../..").click();
		}
		catch(Exception e) {}
		try {
		javaScriptClick(McsElement.getElementByXpath(driver, "//div[contains(@id,'hierarchy')]//*[contains(text(), '"+ supplyPointCode + "')]/.."));
		}
		catch(Exception e) {}
		try {
		javaScriptClick(McsElement.getElementByXpath(driver, "//div[contains(@id,'hierarchy')]//*[contains(text(), '"+ supplyPointCode + "')]/../.."));
		}
		catch(Exception e) {}
		try {
		javaScriptClick(McsElement.getElementByXpath(driver, "//div[contains(@id,'hierarchy')]//*[contains(text(), '"+ supplyPointCode + "')]/../../.."));
		}
		catch(Exception e) {}
		try {
			javaScriptClick(McsElement.getElementByXpath(driver, "//div[contains(@id,'hierarchy')]//*[contains(text(), '"+ supplyPointCode + "')]"));
			}
			catch(Exception e) {}*/
	}
	
	public void clickEdidMeter() {
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class,'x-menu x-menu-floating')  and not(contains(@class,'hide'))]//span[contains(text(),'View Properties')]"));
		ele.click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Clicked on View Properties", true);
	}
	
	public void clickCreateMeter() {
		driver.findElement(
				By.xpath("//div[contains(@class,'x-menu x-menu-floating') and not(contains(@class,'hide'))]//span[contains(text(),'Create Meter')]")).click();

		Reporter.log("Select 'Create Meter as Successor' menu item", true);
	}

	public void clickAddButton(String gridClass) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Add", true, true).click();
		Reporter.log("Click Add button", true);
	}

	public void clickEditButton(String gridClass) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Edit", true, true).click();
		Reporter.log("Click Edit button", true);
	}
	
	public void clickDeleteButton(String gridClass) {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Delete", true, true).click();
		clickOnDialogButton("Yes");
		//clickOnDialogButton("OK");
		Reporter.log("Delete selected entity" + " (" + timer.stop() + "ms)", true);
	}

	public void setReference(String reference) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_METER, "input", "@name",
				"reference", true, true);
		field.click();
		field.clear();
		field.sendKeys(reference);
		Reporter.log("Set Reference - " + reference, true);
	}
	
	public void setCode(String code) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_METER, "input", "@name",
				"code", true, true);
		field.click();
		field.clear();
		field.sendKeys(code);
		Reporter.log("Set Code - " + code, true);
	}

	public void setCommissioningDate(String commissioningDate) {
		WebElement date = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_METER, "input", "@name",
				"commissioningDate", true, true);
		date.clear();
		date.sendKeys(commissioningDate);
		Reporter.log("Set Commisioning Date - " + commissioningDate, true);
	}

	public void setSerialNumber(String serialNumber) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_METER, "input", "@name",
				"serialNumber", true, true);
		field.click();
		field.clear();
		field.sendKeys(serialNumber);
		Reporter.log("Set Serial Number - " + serialNumber, true);
	}

	public void setManufacturer(String manufacturer) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_METER, "input", "@name",
				"manufacturer", true, true);
		field.click();
		field.clear();
		field.sendKeys(manufacturer);
		Reporter.log("Set Manufacturer - " + manufacturer, true);
	}

	public void setDescription(String description) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_METER, "textarea", "@name",
				"description", true, true);
		field.click();
		field.clear();
		field.sendKeys(description);
		Reporter.log("Set Description - " + description, true);
	}
	
	public void setStatus(String statusName){
		clickLookup(DIALOG_METER, "status");
		setValueGridLookup(statusName);
		Reporter.log("Set Status - "+statusName, true);
	}
	
	public void setTimeZone(String timezoneName){
		clickLookup(DIALOG_METER, "timezone");
		setValueGridLookup(timezoneName);
		Reporter.log("Set Time Zone - "+timezoneName, true);
	}
	
	public void setLocation(String locationName){
		clickLookup(DIALOG_METER, "location");
		waitForExtJSAjaxComplete(20);
		//Click Building tab to be sure it is active
		WebElement buildingsTab = McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", 
				"@id", getXWindowId("Select a Location"), "span", "text()", "Buildings", true, true);
		buildingsTab.click();
		waitForExtJSAjaxComplete(20);
		setValueGridLookup(locationName);
		Reporter.log("Set Location - "+locationName, true);
	}
	
	/**
	 * Same as setLocation but
	 * Set Location if Buildings Tab is not Available
	 * @param locationName
	 */
	public void setPhysicalLocation(String locationName){
		clickLookupNewUI("@class", DIALOG_METER, "location","Select a Location");
		
		setValueGridLookupWithFiltersNewUI("@class", "x6-window-default-closable", locationName, "Reference");
		waitForExtJSAjaxComplete(5);
		Reporter.log("Set Location - "+locationName, true);
	}
	
	/**
	 * Helper method to set value Grid Look up
	 * Xpath needs to reframed for Physical Location So new Method
	 * @param model
	 */
	public void setValueLookup(String rowTextName) {
		Timer timer = new Timer().start();
		
		//find row with given text in any column
		//this row should be visible (no scrolling is performed)
			WebElement firstRowWithText = 
					driver.findElement(By
						.xpath((("//div[contains(@class, 'x-resizable-pinned')][last()]//div[contains(@class, 'x-panel-body-noheader x-panel-body-noborder')]//div[contains(@class, 'x-tree-node-leaf')]//a/span[contains(text(),'"
								+ rowTextName + "')]"))));
					
			waitWebElement(firstRowWithText);
			firstRowWithText.click();
			clickOkXwindow();
			Reporter.log(rowTextName + " was selected"+ " (" + timer.stop()
					+ "ms)", true);
	}

	public void setModel(String model) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_METER, "input", "@name",
				"model", true, true);
		field.click();
		field.clear();
		field.sendKeys(model);
		Reporter.log("Set Model - " + model, true);
	}

	public void setAccessInstructions(String accessDirectives) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_METER, "textarea", "@name",
				"accessDirectives", true, true);
		field.click();
		field.clear();
		field.sendKeys(accessDirectives);
		Reporter.log("Set Access Instructions - " + accessDirectives, true);
	}
	
	public void setCommodity(String commodityName){
		clickLookup(DIALOG_METER, "commodity");
		setValueGridLookupWithFilters(commodityName, "Reference");
		Reporter.log("Set Commodity - "+commodityName, true);
	}
	
	public void setMeterOperator(String meterOperatorName){
		clickLookup(DIALOG_METER, "meterOperator");
		waitForExtJSAjaxComplete(20);
		setValueGridLookup(meterOperatorName);
		Reporter.log("Set Meter Operator - "+meterOperatorName, true);
	}
	
	public void saveClose(String dialogClass) {
/*		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", dialogClass, "button",
				"text()", "Save", "text()", "Close", true, true).click();*/
		driver
		.findElement(
				By.xpath("(//div[contains(@class,'"+dialogClass+"')]//button[contains(text(), 'Save') and contains(text(), 'Close')])[last()]")).click();
		Reporter.log("Save and Close clicked", true);
	}
	
	public void save(String dialogClass) {
		//McsElement.getElementByAttributeValueAndParentElement(driver,
		//		"div", "@class", dialogClass, "button",
		//		"text()", "Save", true, true).click();
		driver
		.findElement(
				By.xpath("(//div[contains(@class,'"+dialogClass+"')]//button[text()='Save'])[last()]")).click();
		Reporter.log("Click Save button", true);
	}

	public void saveNew(String dialogClass) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", dialogClass, "button",
				"text()", "Save", "text()", "New", true, true).click();
		Reporter.log("Save and New", true);
	}
	
	public void close(String dialogClass) {
//		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
//				"@class", dialogClass, "button", "text()",
//				"Close", true, true).click();
		WebElement ele = driver
		.findElement(
				By.xpath("(//div[contains(@id,'"+getXWindowIdByClassCustomized(dialogClass)+"')]//button[text()='Close'])[last()]"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		}catch(Exception e){
			e.printStackTrace();
		}
		ele.click();
		Reporter.log("Click Close button", true);
	}
	
	public WebElement checkRowInGriByTextValueAndClick(WebDriver webDriver, String dialogClass, String textValue)  {
		WebElement webElement = webDriver.findElement(By.xpath("//div[contains(@class, '"+dialogClass+"')]//*[@class='x-grid3']"+xpathGeneratorForTextElement(textValue)));
		webElement.click();
		Reporter.log("Check element "+textValue+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	
	
	public static String xpathGeneratorForTextElement(String text){
		  
		  String subStrings[] = text.split(" ");
		  
		  String xpath = "//div[starts-with(text(),'"+subStrings[0]+"')";
		  
		  for(int i=1; i<subStrings.length; i++){

		   xpath+="and contains(text(),'"+subStrings[i]+"')";
		  }
		  
		  return xpath+"]";
		  
	}
	
	public void clickButton(String buttonName, String dialogClass)
	{
		/*McsElement.getElementByPartAttributeValueAndParentElement(driver,
					"div", "@class", dialogClass, "button", "@class", "x-btn-text",
					"text()", buttonName, true, true).click();*/
		WebElement element = driver.findElement(By.xpath("//div[contains(@class, '"+dialogClass+"')]//button[contains(@class, 'x-btn-text') and text()='"+buttonName+"']"));
		
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		Reporter.log("Click "+buttonName+" button", true);
	}
	
	public void clickButton(String buttonName, String dialogClass, String tabName)
	{
		
		WebElement element = driver.findElement(By.xpath("//div[contains(@class,'"+dialogClass+"')]//div[contains(@class,'"+tabName+"') and not (contains(@class, 'x-hide-display'))]//button[contains(@class,'x-btn-text')and contains(text(),'"+buttonName+"')]"));
		
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		Reporter.log("Click "+buttonName+" button", true);
	}
	
	public void clickButtonByDialogId(String buttonName, String dialogId)
	{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
					"div", "@id", dialogId, "button", "@class", "x-btn-text",
					"text()", buttonName, true, true).click();
			Reporter.log("Click "+buttonName+" button", true);
	}
	
	public String getFieldValue(String dialogClass, String fieldName) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+dialogClass+"')]//input[@name='"+fieldName+"']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public String getTextAreaValue(String dialogClass, String textAreaName) {
		/*return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", dialogClass, "textarea",
				"@name", textAreaName, true, true).getAttribute("value");*/
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class,'"+dialogClass+"')]//textarea[contains(@name,'"+textAreaName+"')]"));
		String text = ele.getAttribute("value");
		waitForExtJSAjaxComplete(25);
		return text;
	}
	
	public String getLookupValue(String dialogClass, String lookupName) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+dialogClass+"')]//input[@name='"+lookupName+"']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	//MeterChannelsTestSuite
	
	public void clickChannelsTab()
	{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"ul", "@class", "x-tab-strip", "span", "text()", "Channels", true, true).click();
		Reporter.log("Click on Channels tab", true);
	}
	
	public void clickScopeTab()
	{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"ul", "@class", "x-tab-strip", "span", "text()", "Scope", true, true).click();
		Reporter.log("Click on Scope tab", true);
	}
	
	public void setChnlReference(String chnlReference) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MTR_CHANNEL, "input", "@name",
				"reference", true, true);
		field.click();
		field.clear();
		field.sendKeys(chnlReference);
		Reporter.log("Set Reference - " + chnlReference, true);
	}
	
	public void setChnlMultiplier(String chnlMultiplier) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MTR_CHANNEL, "input", "@name",
				"multiplier", true, true);
		field.click();
		field.clear();
		field.sendKeys(chnlMultiplier);
		Reporter.log("Set Multiplier - " + chnlMultiplier, true);
	}
	
	public void setChnlNumDigits(String chnlNumDigits) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MTR_CHANNEL, "input", "@name",
				"numberOfDials", true, true);
		field.click();
		field.clear();
		field.sendKeys(chnlNumDigits);
		Reporter.log("Set Number of Digits - " + chnlNumDigits, true);
	}
	
	public void setChnlNumDecimals(String chnlNumDecimals) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MTR_CHANNEL, "input", "@name",
				"numberOfDecimals", true, true);
		field.click();
		field.clear();
		field.sendKeys(chnlNumDecimals);
		Reporter.log("Set Number of Decimals - " + chnlNumDecimals, true);
	}
	
	public void setChnlCalDate(String chnlCalDate) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MTR_CHANNEL, "input", "@name",
				"calibrationDate", true, true);
		field.click();
		field.clear();
		field.sendKeys(chnlCalDate);
		Reporter.log("Set Calibration Date - " + chnlCalDate, true);
	}
		
	public void setChnlRepInterval(String chnlRepInterval) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+DIALOG_MTR_CHANNEL+"')]//input[@name='readingInterval']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, chnlRepInterval);
		Reporter.log("Set Smallest Reporting Interval - "+chnlRepInterval, true);
	}
	
	//Set combobox value using only mouse click
	public void setChnlEntryType(String chnlEntryType) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MTR_CHANNEL, "input", "@name",
				"entryType", true, true);
		field.click();
		
		waitForExtJSAjaxComplete(20);
		
		WebElement combo = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-combo-list", "div", "text()",
				chnlEntryType, true, true);
		combo.click();
		
		waitForExtJSAjaxComplete(20);
		Reporter.log("Set Calibration Date - " + chnlEntryType, true);
	}
		
	public void setChnlEntryMethod(String chnlEntryMethod) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+DIALOG_MTR_CHANNEL+"')]//input[@name='entryMethod']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, chnlEntryMethod);
		Reporter.log("Set Entry Method - "+chnlEntryMethod, true);
	}
	
	public void setChnlUOM(String chnlUOM){
		clickLookup(DIALOG_MTR_CHANNEL, "unitOfMeasure");
		boolean build122 = driver.getCurrentUrl().contains("122");
		if (!build122) {
			setValueGridLookupWithFilters(chnlUOM, "Code");
		} else {
			setValueGridLookup(chnlUOM);
		}
		Reporter.log("Set UOM - "+chnlUOM, true);
	}
	
	public void setChnlParameter(String parameter){
		clickLookup(DIALOG_MTR_CHANNEL, "channelParameter");
		setValueGridLookup(parameter);
		Reporter.log("Set Parameter - "+parameter, true);
	}
	
	//Check if WebElement has "disabled" class
	public boolean checkNumDigitsDisabled(){	
		try {
			driver.findElement(By.xpath("//div[contains(@class, '"+DIALOG_MTR_CHANNEL+"')]//input[@name='numberOfDials']//..//input[contains(@class, 'readonly')]"));
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	//Check if WebElement has "disabled" class
	public boolean checkNumDecimalsDisabled(){	
		try {
			driver.findElement(By.xpath("//div[contains(@class, '"+DIALOG_MTR_CHANNEL+"')]//input[@name='numberOfDecimals']//..//input[contains(@class, 'readonly')]"));
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	//MeterMeasurementsCRUDTestSuite
	
	public void clickViewMeasurementsButton()
	{
		WebElement viewButton = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_METER, "button", "@class", "x-btn-text",
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
	
	public void setIndexValue(String value)
	{
		WebElement element = driver.findElement(By.xpath("//div[contains(@class, '"+DIALOG_MEASUREMENT+"')]//input[contains(@name,'value')]"));
		
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
        	System.out.println("Exception is G " +e);
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		element.clear();
		element.sendKeys(value);
		element.sendKeys(Keys.ENTER);
		waitForExtJSAjaxComplete(20);
		Reporter.log("Set index value - "+value, true);
	}
	
	public void setIndexValueDigits(String value)
	{
		WebElement indexValue = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MEASUREMENT, 
				"input", "@name", "digitsField", true, true);
		indexValue.click();
		indexValue.clear();
		indexValue.sendKeys(value);
		Reporter.log("Set index value (digits) - "+value, true);
	}
	
	public void setIndexValueDecimals(String value)
	{
		WebElement indexValue = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MEASUREMENT, 
				"input", "@name", "decimalsField", true, true);
		indexValue.click();
		indexValue.clear();
		indexValue.sendKeys(value);
		Reporter.log("Set index value (decimals) - "+value, true);
	}
	
	public void setDate(String date)
	{
		/*
		 * McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MEASUREMENT, 
				"input", "@name", "date", true, true);
		 */
		WebElement element = driver.findElement(By.xpath("//div[contains(@class, '"+DIALOG_MEASUREMENT+"')]//input[contains(@name,'date')]"));
		
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", element);
            } catch (Exception e) {
            	System.out.println("Exception is G " +e);
            }
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		element.clear();
		element.sendKeys(date);
		element.sendKeys(Keys.ENTER);
		waitForExtJSAjaxComplete(20);
		Reporter.log("Set date - "+date, true);
	}
	
	public void setTime(String time)
	{
		/*WebElement timeField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MEASUREMENT, 
				"input", "@name", "time", true, true);*/
	WebElement element = driver.findElement(By.xpath("//div[contains(@class, '"+DIALOG_MEASUREMENT+"')]//input[contains(@name,'time')]"));
	
    try {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", element);
    } catch (Exception e) {
    	System.out.println("Exception is G " +e);
    	e.printStackTrace();
    }
	
	((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	element.clear();
	element.sendKeys(time);
	element.sendKeys(Keys.ENTER);
	waitForExtJSAjaxComplete(20);
		Reporter.log("Set time - "+time, true);
	}
	
	/**
	 * Helper method to get Time from Measurement Dialog
	 * @param time
	 */
	public String getTime()
	{
		WebElement element = driver.findElement(By.xpath("//div[contains(@class, '"+DIALOG_MEASUREMENT+"')]//input[contains(@name,'time')]"));
		return element.getAttribute("value");
	}
	
	/**
	 * Helper method to change the date format
	 */
	public String timeManipulation(int hour, int second){
		try{
			
			Locale lc= Locale.US;
			
			Locale.setDefault(Locale.US);
			
			TimeZone tz1 = TimeZone.getTimeZone("GMT");
			
			GregorianCalendar calendar = new GregorianCalendar(lc);

		    System.out.println("Current time : " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
		    calendar.add(Calendar.HOUR_OF_DAY, hour);
		    calendar.add(Calendar.SECOND, second);
       	 	System.err.println(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.SECOND));
       	 	return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)+ ":" +calendar.get(Calendar.SECOND));
		}catch(Exception ex){
			ex.printStackTrace();
			return ex.toString();
		}
	}
	
	/**
	 * Helper method to set Notes
	 */
	public void setNotesMeasurementPage(String notes){
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MEASUREMENT, "textarea", "@name",
				"notes", true, true);
		field.click();
		field.clear();
		field.sendKeys(notes);
		Reporter.log("Set notes - " + notes, true);
	}
		
	public void setMeasurementType(String measurementType) {
		clickLookup(DIALOG_MEASUREMENT, "measurementType");
		setValueGridLookup(measurementType);
		Reporter.log("Set Measurement Type - "+measurementType, true);
	}
	
	public void setCalculationMethod(String calculationMethod) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+DIALOG_MTR_CHANNEL+"')]//input[@name='calculationMethod']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, calculationMethod);
		Reporter.log("Set Entry Method - " + calculationMethod, true);
	}
	
//////////MetersFormValidationTestSuite//////////////////////
	
	public boolean verifyMeterFormIsOpen() {
		try {
			driver.findElement(
					By.xpath("//div[contains(@class,'"+DIALOG_METER+"')]"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean checkMandatoryFieldSave(String dialog){
		return driver.findElement(
					By.xpath("//div[contains(@class,'"+dialog+"')]//span[contains(@class,'window-header')]")).getText().contains("Add");
		
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
	
	//Reopen Meter Form being either saved or not
	public boolean reopenMeterForm(String supplyPointName){
		
			 if (!checkMandatoryFieldSave(DIALOG_METER)) {
				 
				 close(DIALOG_METER);
				 
				 waitForExtJSAjaxComplete(20);
			     
			     setCommodityClass("Water");
			     
			     waitForExtJSAjaxComplete(20);
			     
			     waitForExtJSAjaxComplete(20);
			     
			     clickOnSupplyPoint(supplyPointName);
			     
			     waitForExtJSAjaxComplete(20);
			     
			     clickCreateMeter();
			     
			     waitForExtJSAjaxComplete(20);

			     return false;
			 }
			 else {
				 
				 close(DIALOG_METER);
				 
				 waitForExtJSAjaxComplete(20);
				 
				 clickOnDialogButton("Yes");
				 
				 waitForExtJSAjaxComplete(20);
			     
			     setCommodityClass("Water");
			     
			     waitForExtJSAjaxComplete(20);
			     
			     waitForExtJSAjaxComplete(20);
			     
			     clickOnSupplyPoint(supplyPointName);
			     
			     waitForExtJSAjaxComplete(20);
			     
			     clickCreateMeter();
			     
			     waitForExtJSAjaxComplete(20);
			     
				 return true;
			 }
	 }
	
	
	//Reopen Meter Channel Form being either saved or not
	public boolean reopenMeterChannelForm(){
			
				 if (!checkMandatoryFieldSave(DIALOG_CHANNEL)) {
					 
					 clickButton("Add", DIALOG_METER);
				     
				     waitForExtJSAjaxComplete(20);

				     return false;
				 }
				 else {
					 
					 close(DIALOG_CHANNEL);
					 
					 waitForExtJSAjaxComplete(20);
					 
					 clickOnDialogButton("Yes");
					 
					 waitForExtJSAjaxComplete(20);
				     
					 clickButton("Add", DIALOG_METER);
				     
				     waitForExtJSAjaxComplete(20);
				     
					 return true;
				 }
	}
	 
	 //Method clears fields value
	 public void clearField(String fieldNameTag, String fieldName) {
		WebElement field =  driver.findElement
				(By.xpath("(//div[contains(@class, '"+DIALOG_METER+"')]//input[contains(@name, '"+fieldNameTag+"')]//..//input)[last()]"));
		field.click();
		field.clear();
		Reporter.log("Clear "+fieldName+" field", true);
	 }
	 
	 public void clearChnlField(String fieldNameTag, String fieldName) {
		WebElement field =  driver.findElement
			(By.xpath("(//div[contains(@class, '"+DIALOG_CHANNEL+"')]//input[contains(@name, '"+fieldNameTag+"')]//..//input)[last()]"));
		field.click();
		field.clear();
		Reporter.log("Clear "+fieldName+" field", true);
	}
	 
	 public void clearFieldMeas(String fieldNameTag, String fieldName) {
		WebElement field =  driver.findElement
				(By.xpath("(//div[contains(@class, '"+DIALOG_MEASUREMENT+"')]//input[contains(@name, '"+fieldNameTag+"')]//..//input)[last()]"));
		field.click();
		field.clear();
		Reporter.log("Clear "+fieldName+" field", true);
	 }
	 
	public void setCommodityClass(String commodityClass) {
		String elementId = driver.findElement(By
				.xpath("//input[@name='commodityClass']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, commodityClass);
		Reporter.log("Set Commodity Class - "+commodityClass, true);
	}
	
	public void setEffectiveDate(String effectiveDate) {
		WebElement field = driver.findElement(By
				.xpath("//input[@name='effective-date']"));
		field.click();
		field.clear();
		field.sendKeys(effectiveDate);
		field.sendKeys(Keys.ENTER);
		Reporter.log("Set Effective Date - "+effectiveDate, true);
	}
	
	public void clickCancelXwindow() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-window-footer x-window-footer-noborder x-panel-btns", "button",
				"text()", "Cancel", true, true).click();
		waitForElementDisappear("//div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]");
		Reporter.log("Click Cancel", true);
	}
	
	public boolean findMeterInHierarchy(String meterName) {
		try {
			McsElement.getElementByPartAttributeValueAndParentElement(driver,
					"div", "@id", "hierarchy",
					"*", "text()", meterName, true, true).click();
			return true;
		} catch (Exception e) {
			return false;
		}
			
	}
	
	public boolean checkDefaultField(String attribute, String attributeValue, String columnName, String fieldValue){ 
		
		filterGrid(attribute, attributeValue, fieldValue, columnName); 
		Reporter.log("Filter '"+columnName+"' column by '"+fieldValue+"' value", true);
		
		return McsElement.isElementPresent(driver, "//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'check-col-on')]");
				
	} 
	
	public void clickYesXwindow() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-window-footer x-window-footer-noborder x-panel-btns", "button",
				"text()", "Yes", true, true).click();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
	}
	
	////////////////////////////////////////channel form items////////////////////////////////////
	public boolean verifyItemExists(String inputName, String ddItem) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+DIALOG_MTR_CHANNEL+"')]//input[@name='" + inputName + "']")).getAttribute("Id");
		return DropDown.isExtJsComboValuePresent(driver, elementId, ddItem);	
	}
	
	public boolean checkInputDisabled(String elementName){	
		try {
			driver.findElement(By.xpath("//div[contains(@class, '"+DIALOG_MTR_CHANNEL+"')]//input[@name='" + elementName + "']//..//input[contains(@class, 'readonly')]"));
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	//////////////////////////////////////MetersMeasurementFormValidationTestSuite////////////////////////////////////
	
	public boolean reopenMeterMeasurementForm(){
		
	     waitForExtJSAjaxComplete(3);
		
		 if (!checkFormExists(DIALOG_MEASUREMENT)) {
			 
			 clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		     
		     waitForExtJSAjaxComplete(20);
		     
		     waitForExtJSAjaxComplete(3);

		     return false;
		 }
		 else {
			 
			 close(DIALOG_MEASUREMENT);
			 
			 waitForExtJSAjaxComplete(20);
			 
			 waitForExtJSAjaxComplete(20);
			 
		     waitForExtJSAjaxComplete(3);
		     
			 clickButton("Add", DIALOG_MTR_CHNL_MEASSUREMENTS);
		     
		     waitForExtJSAjaxComplete(20);
		     
		     waitForExtJSAjaxComplete(3);
		     
			 return true;
		 }
	}
	
	public void clickAddOnScopeTab() {
		driver.findElement(By
				.xpath("//div[contains(@class,'"+DIALOG_METER+"')]//div[contains(@class,'scope-tab')]//button[contains(@class, 'addButton')]")).click();
	
	}
	
	public void clickEditOnScopeTab() {
		driver.findElement(By
				.xpath("//div[contains(@class,'"+DIALOG_METER+"')]//div[contains(@class,'scope-tab')]//button[contains(@class, 'editButton')]")).click();
	
	}
	
	public void clickCloseOnScopeTab() {
		driver.findElement(By
				.xpath("//div[contains(@class,'"+DIALOG_METER+"')]//div[contains(@class,'scope-tab')]//button[contains(@class, 'icon-scope-end-date')]")).click();
	
	}
	
	public void clickAddEnergyObjectOnObjectToScope() {
		if (!driver.getCurrentUrl().contains("122")) {
			driver.findElement(By
				.xpath("(//button[contains(text(), 'Add Energy Object')])[last()]")).click();
		} else {
			driver.findElement(By
				.xpath("(//button[contains(text(), 'Add Location')])[last()]")).click();
		}
	}
	
	public void clickAddMaintenanceObjectOnObjectToScope() {
		String className;
		if (driver.getCurrentUrl().contains("122") || driver.getCurrentUrl().contains("14")) {
			className = DIALOG_PROPERTIES_NOT_TRUNK;
		}
		else {
			className = DIALOG_PROPERTIES;
		}
		 
		driver.findElement(By
				.xpath("//div[contains(@class,'"+className+"')]//button[contains(text(), 'Add Maintenance Object')]")).click();
	}
	
	public void clickDeleteOnObjectToScope() {
		String className;
		if (driver.getCurrentUrl().contains("122") || driver.getCurrentUrl().contains("14")) {
			className = DIALOG_PROPERTIES_NOT_TRUNK;
		}
		else {
			className = DIALOG_PROPERTIES;
		}
		driver.findElement(By
				.xpath("//div[contains(@class,'"+className+"')]//button[contains(text(), 'Delete')]")).click();
	
	}
	
	public void setEffectiveDateOnScope(String xwindowTitle, String effectiveDate) {
		WebElement field = driver.findElement(By
				.xpath("//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//input[@name='dateStart']"));
		field.click();
		field.clear();
		field.sendKeys(effectiveDate);
		field.sendKeys(Keys.ENTER);
		Reporter.log("Set Effective Date - "+effectiveDate, true);
	}
	
	public void selectEnergyObject(String energyObjectReference) {
		driver.findElement(By
				.xpath("//div[contains(@class,'"+"x-window-noborder x-resizable-pinned"+"')]//td[contains(@class, 'td-1')]//div[contains(text(), '" + energyObjectReference + "')]")).click();
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		driver.findElement(By
				.xpath("//div[contains(@class,'"+"x-window-noborder x-resizable-pinned"+"')]//button[contains(text(), 'OK')]")).click();
	}
	
	public void selectEnergyObjectOnScope(String energyObjectReference) {
		String className;
		if (driver.getCurrentUrl().contains("122") || driver.getCurrentUrl().contains("14")) {
			className = DIALOG_PROPERTIES_NOT_TRUNK;
		}
		else {
			className = DIALOG_PROPERTIES;
		}
		driver.findElement(By
				.xpath("//div[contains(@class,'"+className+"')]//div[contains(text(), '" + energyObjectReference + "')]")).click();
		waitForExtJSAjaxComplete(20);
	}
	
	public boolean verifyItemEnergyObjectExist(String energyObjectReference) {
		String className;
		if (driver.getCurrentUrl().contains("122") || driver.getCurrentUrl().contains("14")) {
			className = DIALOG_PROPERTIES_NOT_TRUNK;
		}
		else {
			className = DIALOG_PROPERTIES;
		}
		try {
			driver.findElement(By
					.xpath("//div[contains(@class,'"+className+"')]//div[contains(text(), '" + energyObjectReference + "')]"));
			waitForExtJSAjaxComplete(20);
			return true;
		} catch (Exception e) {
			return false;
		}	
	}
	
	public boolean verifyItemEnergyObjectNotExist(String energyObjectReference) {
		String className;
		if (driver.getCurrentUrl().contains("122") || driver.getCurrentUrl().contains("14")) {
			className = DIALOG_PROPERTIES_NOT_TRUNK;
		}
		else {
			className = DIALOG_PROPERTIES;
		}
		try {
			driver.findElement(By
					.xpath("//div[contains(@class,'"+className+"')]//div[contains(text(), '" + energyObjectReference + "')]"));
			waitForExtJSAjaxComplete(20);
			return false;
		} catch (Exception e) {
			return true;
		}	
	}
	
	public void clickOkOnCloseScope() {
		driver.findElement(By.xpath("//div[contains(@class,'x-window x-resizable-pinned')]//button[contains(text(), 'OK')]")).click();
	}
	
	public boolean checkButtonEditDisabledOnScopeTab() {
		try {
			driver.findElement(By.xpath("//div[contains(@class,'"+DIALOG_METER+"')]//div[contains(@class,'scope-tab')]//table[contains(@class,'x-item-disabled')]//button[contains(@class, 'editButton')]"));
			waitForExtJSAjaxComplete(20);
			return true;
		} catch (Exception e) {
			return false;
		}
	}	
		
	public boolean checkButtonCloseDisabledOnScopeTab() {
		try {
			driver.findElement(By.xpath("//div[contains(@class,'"+DIALOG_METER+"')]//div[contains(@class,'scope-tab')]//table[contains(@class,'x-item-disabled')]//button[contains(@class, 'icon-scope-end-date')]"));
			waitForExtJSAjaxComplete(20);
			return true;
		} catch (Exception e) {
			return false;
		}	
	}
	
	public String getEffectiveDateOnScopeTab() {
		return driver.findElement(By.xpath("//div[contains(@class,'"+DIALOG_METER+"')]//div[contains(@class,'scope-tab')]//input[contains(@name, 'dateStart')]")).getAttribute("value");
	}
	
	public void selectEnergyObjectOnAddObjectToScope(String energyObjectReference) {
		driver.findElement(By.xpath("//div[contains(@id,'scopeGridCmp')]//div[contains(text(),'" + energyObjectReference + "')]")).click();
	}
	
//	public void setDateRange(String dateRange) {
//		WebElement field = driver.findElement(By.xpath("//div[contains(@class,'"+DIALOG_MTR_CHNL_MEASSUREMENTS+"')]//input[@name='measurementsPopup_dateRangePicker']"));
//		field.click();
//		field.clear();
//		field.sendKeys(dateRange);
//		Reporter.log("Set Date Range - " + dateRange, true);
//	}
	
	public boolean verifyMeasurementGridValues(String value1, String value2, String value3, String value4) {
		try {
			driver.findElement(By.xpath("//div[contains(@class,'slnmMeterChnlMeasId')]//div[contains(text(), '"+value1+"')]//..//..//div[contains(text(), '"+value2+"')]//..//..//..//div[contains(text(), '"+value3+"')]//..//..//..//div[contains(text(), '"+value4+"')]"));
			return true;
		} catch (Exception e) {
			return false;
		}
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
	
	/**
	 *  Helper method to get column number in Grid 
	 */
	public int getGridColumnNumber(String attribute, String attributeValue, String columnName){
		
		String columnClass = McsElement.getElementByXpath(driver,"(//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')][last()])").getAttribute("class");
		String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);
			
		return Integer.parseInt(columnNumber);
	}
	
	/**
	 * Helper method to click on Commodity End use Input field
	 * @return
	 */
	public WebElement selectCommodityEndUse(String attribute, String attributeValue, String rowTextName, String columnName) {
		
		Timer timer = new Timer().start();
		
		waitForExtJSAjaxComplete(25);
		
		int columnNumber = getGridColumnNumber(attribute, attributeValue , columnName );
	
		WebElement ele = McsElement.getElementByXpath(driver,"(//div[contains("+attribute+",'" + attributeValue + "') and not (contains(@class, 'x-hide-display'))]//"
		+"div[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
		+ "and text()"+ "='" + rowTextName + "'" + "])[last()]");
		
		ele.click();
		
		waitForExtJSAjaxComplete(20);

		timer.stop();
		
		return ele;
	}
	
	/**
	 * Helper method to set Commodity End Use Look up
	 */
	public void setCommodityEndUse(String attribute, String attributeValue, String rowTextName, String columnName){
		
		selectCommodityEndUse(attribute, attributeValue, rowTextName, columnName);
		
		waitForExtJSAjaxComplete(20);
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'energy crud-form')]//div[@id='scopeGridCmp']//div[contains(@class,'x-box-inner')]//table//button[contains(@style,'details.png')]").click();
		
		waitForExtJSAjaxComplete(10);
		
		setValueGridLookup("Refrigeration");
		
		waitForExtJSAjaxComplete(10);
	}

	/**
	 * Helper method to verify Commodity End use Field value deletion
	 * @return
	 */
	public boolean verifyCommodityEndUseDeletion() {
		Boolean build122 = driver.getCurrentUrl().contains("122");
		
		WebElement element; 
		
		if(!build122){
			element = selectCommodityEndUse("@class", DIALOG_PROPERTIES_NOT_TRUNK, "Refrigeration", "Commodity End Use");
		} else {
			element = selectCommodityEndUse("@class", DIALOG_PROPERTIES_NOT_TRUNK, "Refrigeration", "Commodity End-use");
		}
		
		try {
			String status = element.getAttribute("isContentEditable");
			
			if(status.contains("false")){
				return false;
			} else {
				return true;
			}
			
		} catch(Exception e){
		
			System.out.println("Exception is " +e);
		}
		
		return true;
	}
	
	//**********************Notes Tab ******************************///
	
	/**
	 * Helper method to set Notes
	 */
	public void setNotes(String notes){
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_METER, "textarea", "@name",
				"notes", true, true);
		field.click();
		field.clear();
		field.sendKeys(notes);
		Reporter.log("Set notes - " + notes, true);
	}
	
	/**
	 * Helper method to verify Only One Commodity is Available in Commodity Lookup
	 */
	public String verifyCommodity(String xwindowTitle){
		clickLookup(DIALOG_METER, "commodity");
		
		String text = null;
		
		try{
			text = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//div[@class='x-grid3-body']/div")).getAttribute("class");
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return text;
	}
	
	/**
	 * Helper method to click on Tabs in Lookup window
	 * @param tabText : text of tab to click
	 */
	public void clickOnTab(String xwindowTitle, String tabText){
		
		String xpath = "//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//span[contains(@class,'x-tab-strip-text') and contains(text(),'"+tabText+"')]//ancestor::a";
		
		McsElement.getElementByXpath(driver, xpath).click();
	}
	
	/**
	 * Helper method to click on Meters
	 */
	public void clickOnMeters(String meters){
		/*McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@id", "hierarchy",
				"*", "text()", meters, true, true).click();*/
		WebElement element = driver.findElement(By.xpath("//div[contains(@id, 'hierarchy')]//*[contains(text(), '"+meters+"')]"));
		
		if ("chrome".equals(configuration.getBrowser())) {
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", element);
                element.click();
            } catch (Exception e) {
            	System.out.println("Exception is G " +e);
            }
        }
		
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		waitForExtJSAjaxComplete(20);
		Reporter.log("Click on Meters - "+meters, true);
	}
	
	public String getItemID(String arrtibuteValue) {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", DIALOG_METER, "input",
				"@name", "itemId", true, true).getAttribute(arrtibuteValue);
	}
	
	public void changeTab(String tabName) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"li", "@id", tabName+"-tab", "span", "@class", "x-tab-strip-text", true, true).click();
		Reporter.log("change tab to "+tabName, true);
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
	 * Helper method to set Location in Navigator Site Meter Infrastructure
	 */
	public void setNavigatorLocation(String locationName){
		clickLookupNewUI("@class", DIALOG_METER, "location","Select a Location");
		
		setValueGridLookupWithFiltersNewUI("@class", "x6-window-default-closable", locationName, "Reference");
		waitForExtJSAjaxComplete(5);
		Reporter.log("Set Location - "+locationName, true);
	}
	
	/**
	 * Helper method to verify Rollback and Cancel Buttons are Present in Dialog window
	 */
	public boolean verifyButtons(String attributeValue){
		try{
			driver.findElement(By.xpath("//div[contains(@class, 'x-window-dlg')]//button[contains(@class,'x-btn-text') and text()='"+attributeValue+"']"));
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Helper method to verify Color Code from Grid
	 */
	public boolean verifyGreenColorCode(String xwindowClass, String usageValue){
		try {
			WebElement gridText = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowIdByClass(xwindowClass)+"')]//*[@class='x-grid3']//div[contains(@class, 'x-grid3-col-3')]/..//div[text()='"+usageValue+"']/ancestor::div[contains(@class, 'mcsgridrow_46A546')]"));
			return true;
		} catch(Exception e){
			System.out.println("Exception is " +e);
		}
		
		return false;
	}
	
	public boolean getCheckBoxState(String xwindowTitle, String name) {
		 WebElement checkBoxState = driver.findElement(By.xpath("//div[contains(@id, 'ext-comp-1234')//input[@name='checkBox']"));
		 String status =	checkBoxState.getAttribute("checked");
			  if (status!= null) {
			   return true;
			  }
			  return false;
	}
	
	/**
	 * Helper method to get Filed Value passing Atrribute
	 */
	public String getFieldValue(String dialogClass, String fieldName, String attribute) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+dialogClass+"')]//input[@name='"+fieldName+"']//..//input)[last()]"))
				.getAttribute(attribute);
	}
	
	/**
	 * Helper method to verify Measurements tab is not present
	 */
	public boolean verifyMeasurementsTab(String paramter) throws NoSuchElementException {
		try{
			if(paramter.equals("Calculated")){
				driver.findElement(By.xpath("//ul[contains(@class, 'x-tab-strip')]//span[contains(text(), 'Measurements')]//ancestor::li[contains(@style, 'display: none')]"));
				return true;
			} else{
				driver.findElement(By.xpath("//ul[contains(@class, 'x-tab-strip')]//span[contains(text(), 'Measurements')]"));
				return true;
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * Helper method to close Channel Measurements using Tool Close (X button)
	 */
	public void closeUsingToolBarMeasurements(String attribute) {
		WebElement element = driver.findElement(By.xpath("//div[contains(@class, '"+attribute+"') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-tool-close')]"));
		
		if ("chrome".equals(configuration.getBrowser())) {
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", element);
                element.click();
            } catch (Exception e) {
            	System.out.println("Exception is G " +e);
            }
        }
		waitForExtJSAjaxComplete(20);
	}
	
	/**
	 * Helper method to verify Color Code from Grid
	 */
	public boolean verifyAnyColorCode(String xwindowClass, String usageValue, String colourCode){
		try {
			WebElement gridText = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowIdByClass(xwindowClass)+"')]//*[@class='x-grid3']//div[contains(@class, 'x-grid3-col-3') and text()='"+usageValue+"']/ancestor::div[contains(@class, '"+colourCode+"')]"));
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void setPhysicalLocationNodeLevel(String locationName){
		clickLookupNewUI("@class", DIALOG_METER, "location","Select a Location");
		
		setValueGridLookupWithFiltersNewUI("@class", "x6-window-default-closable", locationName, "Reference");
		waitForExtJSAjaxComplete(5);
		Reporter.log("Set Location - "+locationName, true);
	}
	
	public void setValueNodeLookup(String rowTextName) {
		Timer timer = new Timer().start();
		
			WebElement firstRowWithText = 
					driver.findElement(By
						.xpath((("//div[contains(@class, 'x-resizable-pinned')][last()]//div[contains(@class, 'x-panel-body-noheader x-panel-body-noborder')]//div[contains(@class, 'x-tree-node')]//a/span[contains(text(),'"
								+ rowTextName + "')]"))));
					
			waitWebElement(firstRowWithText);
			firstRowWithText.click();
			clickOkXwindow();
			Reporter.log(rowTextName + " was selected"+ " (" + timer.stop()
					+ "ms)", true);
	}
	
	/**
	 * Helper method to get System Time with 10 Minutes ahead
	 * @return
	 */
	public String getSystemTime() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Calendar cal = Calendar.getInstance();
		cal.setTime(cal.getTime());
		cal.add(Calendar.MINUTE, 10);
	    System.out.println(dateFormat.format(cal.getTime()));
		return dateFormat.format(cal.getTime());
	}
	
	public void filterGridScrollIntoView(String attribute, String attributeValue, String rowTextName, String columnName) {
		Timer timer = new Timer().start();
		
		WebElement ele = driver.findElement(By.xpath("//div[contains("+attribute+",'"+ attributeValue+"')]//div[contains(@class,'quickfilters')]"
						+ "//div[contains(@class,'x-grid3-hd') and contains(text(),'"+columnName+"')][last()]"));
		String columnClass = "";
		
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			columnClass = ele.getAttribute("class");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
		System.out.println(columnNumber);

		WebElement filterInput = driver.findElement(By.xpath("(//div[contains("+attribute+",'"+attributeValue+"')]//"
					+ "input[contains(@id,'filter-editor-"+columnNumber+"')])[last()]")); 
		
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", filterInput);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		waitForExtJSAjaxComplete(20);		
			
			filterInput.clear();
			
			filterInput.sendKeys(rowTextName);
			
			 McsElement.getElementByXpath(driver, "(//div[contains("+attribute+",'"+attributeValue+"')]//"
						+ "div[contains(@class,'x-grid3-body')])[last()]/div").click();
						
			waitForExtJSAjaxComplete(5);

			Reporter.log(rowTextName + " was filtered"+ " (" + timer.stop()
					+ "ms)", true);

	}
	
	/**
	 *  Helper method to get column number in Grid 
	 */
	public int getGridColumnNumberWithoutQuickFilters(String attribute, String attributeValue, String columnName){
		
		WebElement ele = driver.findElement(By.xpath("//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')][last()]"));
		String columnClass = "";
		String columnNumber = "";
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			 columnClass = ele.getAttribute("class"); 
			 columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);
		}catch(Exception e){
			e.printStackTrace();
		}
		return Integer.parseInt(columnNumber);
	}
	
	public boolean isRowInGridPresentCustomized(String attribute, String attributeValue, String columnName, String textValue) {
		
		int colNo = getGridColumnNumberWithoutQuickFilters(attribute, attributeValue, columnName);
		waitForExtJSAjaxComplete(25);
		try {
			String xpath = "//div[contains("+attribute+",'"+attributeValue+"')]//tr//div[contains(@class, 'x-grid3-col-"+colNo+"') and starts-with(text(),'"+textValue+"')]";
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			driver.findElement(By.xpath(xpath));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
		}
	}
	
	/**
	 * Helper method to clear Filters
	 */
	public void clearOverviewFilters(){
		McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-tab-panel-body-noborder x-tab-panel-body-top') and not (contains(@class, 'hide-display'))]//button[contains(@class, 'icon-grid-filter-clear')]").click();
		waitForExtJSAjaxComplete(10);
	}
	
	/**
	 * Helper method to set Date Range
	 */
	public void setDateRangeChannels(String attribute, String date)
	{
		WebElement dateField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MTR_CHNL_MEASSUREMENTS, 
				"input", "@name", attribute, true, true);
		dateField.click();
		dateField.clear();
		dateField.sendKeys(date);
		dateField.sendKeys(Keys.ENTER);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Set date Range- "+date, true);
	}
	
	/**
	 * Helper method to get Messaged from dialog box
	 */
	public String getExtMbContentOfWindowWOMcsElement(String attribute, String attrVal){
		
		String xpath = "//div[contains("+attribute+",'"+attrVal+"')]//div[contains(@class,'ext-mb-content')]/span";
		 
		String test = driver.findElement(By.xpath(xpath)).getText();
		
		System.out.println(test);
		
		return test;
	}
	
	/**
	 * Helper method to add/Subtract mins to the time which is passed as a paramter
	 */
	public String getCustomizedSystemTime(String time, int timeReqd)  throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		// replace with your start date string
		Date d = df.parse(time); 
		Calendar gc = new GregorianCalendar();
		gc.setTime(d);
		gc.add(Calendar.HOUR, timeReqd);
		Format formatter = new SimpleDateFormat("HH:mm");
		System.out.println(formatter.format( gc.getTime()));
		return formatter.format(gc.getTime());
	}
	
	public void isAuditingFieldChecked (String windowTitle) {
			
			WebElement reference = driver.findElement(By.xpath("//div[contains(@id,'"+ getXWindowId(windowTitle)+"') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-grid3-body')]//div[contains(@id, 'group-Auditing')]//div[contains(@class, 'grid-group-title')]//..//input[@type='checkbox']"));
			waitForExtJSAjaxComplete(10);
			
			System.err.println(reference.isSelected());
				
			if(reference.isSelected()){
				reference.click();
				Reporter.log("Auditing Header is Unchecked");
				waitForExtJSAjaxComplete(5);
			} 
			
			Reporter.log("Field Auditing Header is UNChecked ", true);
		}
	
	public String getXWindowIdByClassCustomized(String xwindowClass) throws NoSuchElementException {

		String elementXpath = "//div[contains(@class, '" + xwindowClass + "') and contains(@style, 'visibility: visible;')]";

		WebElement ele = driver.findElement(By.xpath(elementXpath));
		
		return ele.getAttribute("id");
	}
}