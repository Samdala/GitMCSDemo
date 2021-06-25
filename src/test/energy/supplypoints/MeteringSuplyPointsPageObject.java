package test.energy.supplypoints;

//import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import test.energy.EnergyBaseTestSuite;
import test.framework.AbstractMcsTestSuite;
import test.framework.SoftAssert;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class MeteringSuplyPointsPageObject extends EnergyBaseTestSuite {

	protected final String DIALOG_SP = "slnmSPId";
	protected final String SUPPLY_POINT_GRID_CLASS = "x-tab-panel x-tab-panel-noborder";
	static Integer codeIncrement = 0;
	protected final String ENERGY_PROPERTIES = "energy properties";
	
	protected final String EDIT_ENERGY_PRICE_WINDOW_HEADER = "Edit Energy Price";

	protected final String ADD_ENERGY_PRICE_WINDOW_HEADER = "Add Energy Price";
	
	protected final String ADD_SUPPLY_POINT_WINDOW_HEADER = "Add Supply Point";
	
	protected final String EDIT_SUPPLY_POINT_WINDOW_HEADER = "Edit Supply Point";
	
	protected final String PRICES_GRID_BUSINESS_PARTNERS_TAB = "mcs_energy_business_partner_prices";
	
	protected final String XPATH_COMMODITY_SPECIFIC_PROPERTIES = "//div[contains(@class,'slnmSPId ')]//span[contains(@class, 'x-fieldset-header-text') and text()='Commodity-specific Properties']";
	
	protected final String XPATH_TARIFF = "//div[contains(@class, 'slnmTrfId')]";
	
	protected final String XPATH_CODEPREFIX ="//div[contains(@class,' slnmSPId')]//input[contains(@name,'codePrefix')]";
	
	protected final String XPATH_EFFECTIVEDATE = "//div[contains(@class,'energy properties')]//input[contains(@name,'effectiveDate')]";
	
	protected final String COMMODITY_GRID = "x-panel x-panel-noborder x-grid-panel";
	
	protected final String DIALOG_COMMODITY = "slnmCmdtId";
	
	protected final String DIALOG_CMDT_SOURCE = "slnmCommSrcId";
	
	protected final String DIALOG_CMDT_EQUIVALENCY = "slnmCommSrcEqId";
	
	protected final String XPATH_INFOBAR ="//div[contains(@class,'energy properties')]//div[contains(@class,'infobar')]"; 
	
	protected final String XPATH_CODE ="//div[contains(@class,'slnmSPId')]//input[contains(@name,'code')]"; 
	
	String msgValue = "Do you want to refrain from using the default Prices defined by the administrator?";
	
	
	public MeteringSuplyPointsPageObject(){
		this.FORM_CLASS="slnmSPId";
		this.OVERVIEW_GRID_CLASS="x-tab-panel x-tab-panel-noborder";
		this.softAssert = new SoftAssert();
	}
	
	public void expandMetering() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu", 
				"span", "text()", "Metering", true, true).click();
		Reporter.log("Expand metering", true);
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
	
	public void clickSystemViewTab(){
		
		waitForMaskDisappear();
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-tab-panel-noborder",
				"span", "text()", "Meter Infrastructure", true, true).click();
		Reporter.log("Click on Meter Infrastructure tab", true);
	}

	public void clickAddButton(String gridClass) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Add", true, true).click();
		Reporter.log("Click Add button on Overview", true);
	}
	
	public void clickAddSupplyPointButton(String gridClass) {
		/*McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "addSupplyPoint",
				"text()", "Add Supply Point", true, true).click();*/
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class, 'x-tab-panel x-tab-panel-noborder')]//button[contains(@class, 'addSupplyPoint') and contains(text(), 'Add Supply Point')]"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			ele.click();
		}catch(Exception e){
			e.printStackTrace();
		}
		waitForExtJSAjaxComplete(25);
		Reporter.log("Click Add button on Overview", true);
	}

	public void clickEditButton(String gridClass) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Edit", true, true).click();
		Reporter.log("Click Edit button on Overview", true);
	}

	public void clickDeleteButton(String gridClass) {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Delete", true, true).click();
		clickOnDialogButton("Yes");
		clickOnDialogButton("OK");
		
		Reporter.log("Delete selected Supply Point" + " (" + timer.stop() + "ms)", true);
	}

	public void setCode(String code) {
		WebElement codeField = McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"code", true, true);
		codeField.click();
		codeField.clear();
		codeField.sendKeys(code);
		Reporter.log("Set Code " + code, true);
	}
	
	public String getCode() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"code", true, true).getAttribute("value");
	}
	
	public void setPressure(String pressure) {
		WebElement pressureField = McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"pressureValue", true, true);
		pressureField.click();
		pressureField.clear();
		pressureField.sendKeys(pressure);
		Reporter.log("Set Pressure " + pressure, true);
	}
	
	public String getPressure() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"pressureValue", true, true).getAttribute("value");
	}
	
	public void setCodePrefix(String codePrefix) {
		WebElement codePrefixField = McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"codePrefix", true, true);
		codePrefixField.click();
		codePrefixField.clear();
		codePrefixField.sendKeys(codePrefix);
		Reporter.log("Set Code Prefix " + codePrefix, true);
	}
	
	public String getCodePrefix() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"codePrefix", true, true).getAttribute("value");
	}	
	
	public String getReference() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"reference", true, true).getAttribute("value");
	}
	
	public void setDescription(String description) {
		WebElement descriptionField = McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "textarea", "@name",
				"description", true, true);
		descriptionField.click();
		descriptionField.clear();
		descriptionField.sendKeys(description);
		Reporter.log("Set Description " + description, true);
	}
	
	public String getDescription() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "textarea", "@name",
				"description", true, true).getAttribute("value");
	}
	
	public void setPowerCapacity(String powerCapacity) {
		WebElement powerCapacityFields = McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"powerCapacity", true, true);
		powerCapacityFields.click();
		powerCapacityFields.clear();
		powerCapacityFields.sendKeys(powerCapacity);
		Reporter.log("Set Power Capacity " + powerCapacity, true);
	}
	
	public String getPowerCapacity() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"powerCapacity", true, true).getAttribute("value");
	}
	
	public void setCapacity(String capacity) {
		WebElement capacityFields = McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"capacity", true, true);
		capacityFields.click();
		capacityFields.clear();
		capacityFields.sendKeys(capacity);
		Reporter.log("Set Capacity " + capacity, true);
	}
	
	public String getCapacity() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"capacity", true, true).getAttribute("value");
	}
	
	public void setSupplySystem(String dialogId, String comboValue) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+dialogId+"')]//input[@name='supplySystem']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, comboValue);
		Reporter.log("Set Supply System - "+comboValue, true);
	}
	
	public String getSupplySystem() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"supplySystem", true, true).getAttribute("value");
	}
	
	public void setAddress1(String address1) {
		WebElement address1Field = McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"address1", true, true);
		address1Field.click();
		address1Field.clear();
		address1Field.sendKeys(address1);
		Reporter.log("Set Address 1 " + address1, true);
	}
	
	public String getAddress1() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"address1", true, true).getAttribute("value");
	}
	
	public void setAddress2(String address1) {
		WebElement address2Field = McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"address2", true, true);
		address2Field.click();
		address2Field.clear();
		address2Field.sendKeys(address1);
		Reporter.log("Set Address 2 " + address1, true);
	}
	
	public String getAddress2() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"address2", true, true).getAttribute("value");
	}
	
	public void setZipCode(String zipcode) {
		WebElement zipcodeField = McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"zipCode", true, true);
		zipcodeField.click();
		zipcodeField.clear();
		zipcodeField.sendKeys(zipcode);
		Reporter.log("Set Zip Code " + zipcode, true);
	}
	
	public String getZipCode() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"zipCode", true, true).getAttribute("value");
	}
	
	public void setCity(String city) {
		WebElement cityField = McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"city", true, true);
		cityField.click();
		cityField.clear();
		cityField.sendKeys(city);
		Reporter.log("Set City " + city, true);
	}
	
	public String getCity() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"city", true, true).getAttribute("value");
	}	

	public void clickReference() {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "input", "@name",
				"reference", true, true);
		referenceField.click();
		Reporter.log("Click on Reference field");
	}

	public void setCountry(String countryName){
		clickLookup(DIALOG_SP, "country");
		setValueGridLookup(countryName);
//		waitForExtJSAjaxComplete(5);
	}
	
	public String getCountry() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+DIALOG_SP+"')]//input[@name='country']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setSite(String siteName){
		clickLookup(DIALOG_SP, "site");
		setValueGridLookup(siteName);
		Reporter.log("Set Site - "+siteName, true);
	}
	
	public String getSite() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+DIALOG_SP+"')]//input[@name='site']//..//input)[last()]"))
				.getAttribute("value");
	}
		
	public void setType(String typeName, String windowTitle){
		clickLookup("@id",getXWindowId(windowTitle), "supplyPointType", "Select a Supply Point Type");
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Supply Point Type"), typeName, "Reference");
		Reporter.log("Set Supply Point Type - "+typeName, true);
	}
	
	public String getType() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+DIALOG_SP+"')]//input[@name='supplyPointType']//..//input)[last()]"))
				.getAttribute("value");
	}

	public void setCommodity(String commodityName){
		clickLookup(DIALOG_SP, "commodity");
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Commodity"), commodityName, "Reference"); 
		//setValueGridLookup(commodityName);
		Reporter.log("Set Commodity - "+commodityName, true);
	}
	
	public void clickLookup(String formClass, String inputName){
		Timer timer = new Timer().start();
		int xwindowNumber=0;
		
		try {driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			xwindowNumber = driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size();
		}
		finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);}

		waitFocusAndClick("//div[contains(@class,'"
				+ formClass + "')]//input[contains(@name,'"
				+ inputName + "')]//..//..//button");
		
	/*	WebElement lookupBtn = driver.findElement(By.xpath("//div[contains(@class,'"
				+ formClass + "')]//input[contains(@name,'"
				+ inputName + "')]//..//..//button"));
		
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", lookupBtn);*/
		
		
		waitForExtJSAjaxComplete(5);
		waitForExtJSAjaxComplete(5);
		
		try {driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			if (driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size() == xwindowNumber)
				{waitAndClick("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");}
		}

		 finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
		}

		Reporter.log(inputName + " lookup was clicked"+ " (" + timer.stop()
				+ "ms)", true);
		
	}
	
	public String getCommodity() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+DIALOG_SP+"')]//input[@name='commodity']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setPressureUOM(String pressureUOM){
		clickLookup(DIALOG_SP, "pressureUom");
		setValueGridLookupWithFilters(pressureUOM, "Code");
		Reporter.log("Set Pressure UOM - "+pressureUOM, true);
	}
	
	public String getPressureUOM() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+DIALOG_SP+"')]//input[@name='pressureUom']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setDNO(String dnoName){
		clickLookup(DIALOG_SP, "dno");
		setValueGridLookup(dnoName);
		Reporter.log("Set DNO - "+dnoName, true);
	}
	
	public String getDNO() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+DIALOG_SP+"')]//input[@name='dno']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void saveClose() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Save & Close", true);
	}
	
	public void save() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "button",
				"text()", "Save", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Save", true);
	}

	public void close() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", DIALOG_SP, "button", "text()",
				"Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Close", true);
	}
	
	public WebElement checkRowInGriByTextValueAndClick(WebDriver webDriver, String textValue)  {
		WebElement webElement = webDriver.findElement(By.xpath("//*[@class='x-grid3']//div[text()='"+textValue+"']"));
		waitWebElement(webElement);
//		javaScriptFocus(webElement);
//		javaScriptClick(webElement);
		webElement.click();
		Reporter.log("Check element "+textValue+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	
	public void setCommodityClass(String commodityClass) {
		String elementId = driver.findElement(By
				.xpath("//input[@name='commodityClass']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, commodityClass);
		Reporter.log("Set Commodity Class - "+commodityClass, true);
	}
	
	public void setEffectiveDateMeterInfrastructure(String date) {
		WebElement dateField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", SUPPLY_POINT_GRID_CLASS, 
				"input", "@name", "effective-date", true, true);
		dateField.click();
		dateField.clear();
		dateField.sendKeys(date);
		Reporter.log("Set EffectiveDate - "+date, true);
	}
	
	public void clickConfirmOnDialog() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div",
				"@class", "x-window-dlg", "button", "text()",
				"Yes", true, true).click();
		waitForExtJSAjaxComplete(100);
		Reporter.log("Click Yes on dialog.", true);
	}
	
	public boolean checkDefaultField(String attribute, String attributeValue, String columnName, String fieldValue){ 
		
		filterGrid(attribute, attributeValue, fieldValue, columnName); 
		Reporter.log("Filter '"+columnName+"' column by '"+fieldValue+"' value", true);
		
		return McsElement.isElementPresent(driver, "//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'check-col-on')]");
				
	} 
	
	public boolean checkMandatoryFieldSave(){
		return driver.findElement(
					By.xpath("//div[contains(@class,'"+DIALOG_SP+"')]//span[contains(@class,'window-header')]")).getText().contains("Add");
		
	}
	
	public boolean reopenSupplyPointForm(){
		
		 if (!checkMandatoryFieldSave(DIALOG_SP)) {
			 
			 close();
			 
			 waitForExtJSAjaxComplete(20);
		     
		     setCommodityClass("Electricity");
		     
		     waitForExtJSAjaxComplete(20);
		     
		     waitForExtJSAjaxComplete(20);
		     
		     clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		     
		     waitForExtJSAjaxComplete(20);

		     return false;
		 }
		 else {
			 
			 close();
			 
			 waitForExtJSAjaxComplete(20);
			 
			 clickOnDialogButton("Yes");
			 
			 waitForExtJSAjaxComplete(20);
		     
		     setCommodityClass("Electricity");
		     
		     waitForExtJSAjaxComplete(20);
		     
		     waitForExtJSAjaxComplete(20);
		     
		     clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		     
		     waitForExtJSAjaxComplete(20);
		     
			 return true;
		 }
	}
	
	public void clickOnSupplyPoint(String supplyPointCode) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@id", "hierarchy",
				"*", "text()", supplyPointCode, true, true).click();
		Reporter.log("Click on Supply Point - "+supplyPointCode, true);
	}
	
	public boolean checkMandatoryFieldSave(String dialog){
		return driver.findElement(
					By.xpath("//div[contains(@class,'"+dialog+"')]//span[contains(@class,'window-header')]")).getText().contains("Add");
		
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
	
	public String incrementCode(String code) {
		 codeIncrement+=1;
		 return code+Integer.toString(codeIncrement);
	 }
	
	public void clickBusinessPartnersTab() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"ul", "@class", "x-tab-strip", "span", "text()", "Business Partners", true, true).click();
		Reporter.log("Click on Business Partners tab", true);
	}
	
	public void clickAddButtonBusinessPartners() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmSPId", "button", "@class", "x-btn-text",
				"text()", "Add", true, true).click();
		Reporter.log("Click Add button on BusinessPartners", true);
	}
	
	public void clickEditButtonBusinessPartners() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmSPId", "button", "@class", "x-btn-text",
				"text()", "Edit", true, true).click();
		Reporter.log("Click Add button on BusinessPartners", true);
	}
	
	public void clickDeleteButtonBusinessPartners() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmSPId", "button", "@class", "x-btn-text",
				"text()", "Delete", true, true).click();
		Reporter.log("Click Add button on BusinessPartners", true);
	}
	
	
	public void setEffectiveDate(String date) {
		WebElement dateField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_PROPERTIES, 
				"input", "@name", "effectiveDate", true, true);
		dateField.click();
		dateField.clear();
		dateField.sendKeys(date);
		dateField.sendKeys(Keys.ENTER);
		Reporter.log("Set EffectiveDate - "+date, true);
	}
	
	public void setSupplier(String supplier) {
		clickLookup(ENERGY_PROPERTIES, "supplier");
		setValueGridLookup(supplier);
		Reporter.log("Set source - "+supplier, true);
	}
	
	public void setCommoditySource(String source) {
		clickLookup(ENERGY_PROPERTIES, "source");
		setValueGridLookup(source);
		Reporter.log("Set source - "+source, true);
	}
	
	public void setDNOOnBusinessPartners(String dnoName) {
		clickLookup(ENERGY_PROPERTIES, "dno");
		setValueGridLookup(dnoName);
		Reporter.log("Set DNO - "+dnoName, true);
	}
	
	public void setTariffCalendarOnBusinessPartners(String tariffCalendar) {
		clickLookup(ENERGY_PROPERTIES, "tariffCalendar");
		setValueGridLookup(tariffCalendar);
		Reporter.log("Set TariffCalendar - "+tariffCalendar, true);
	}
	
	public void setPriceTemplateOnBusinessPartners(String priceTemplate) {
		clickLookup(ENERGY_PROPERTIES, "priceTemplate");
		setValueGridLookup(priceTemplate);
		Reporter.log("Set priceTemplate - "+priceTemplate, true);
	}
	
	
	public void saveBusinessPartners() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_PROPERTIES, "button",
				"text()", "Save", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("saveBusinessPartners", true);
	}

	public void closeBusinessPartners() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", ENERGY_PROPERTIES, "button", "text()",
				"Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("closeBusinessPartners", true);
	}
	
	public String getFieldValueBusinessPartners(String fieldName, String attributeName) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+ENERGY_PROPERTIES+"')]//input[@name='"+fieldName+"']//..//input)[last()]"))
				.getAttribute(attributeName);
	}
	
	public WebElement checkRowInGriByTextValueAndClickCustomized(String windowClass, String textValue)  {
		WebElement webElement = driver.findElement(By.xpath("//div[contains(@class,'"+windowClass+"')]//*[@class='x-grid3']"+xpathGeneratorForTextElement(textValue)));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
			webElement.click();
		}catch(Exception e){
			e.printStackTrace();
		}
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
	
	public void clickAddButtonBPPricesGrid() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@realid", PRICES_GRID_BUSINESS_PARTNERS_TAB, "button", "@class", "x-btn-text",
				"text()", "Add", true, true).click();
		Reporter.log("Click Add button on Prices tab BusinessPartners", true);
	}
	
	public void clickEditButtonBPPricesGrid() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@realid", PRICES_GRID_BUSINESS_PARTNERS_TAB, "button", "@class", "x-btn-text",
				"text()", "Edit", true, true).click();
		Reporter.log("Click Edit button on Prices tab BusinessPartners", true);
	}
	
	/**
	 *Helper method to get Cell Text Class  
	 */
	public String getCellText(String windowTitle, String rowNum, String colName) {
		
		String colNo = getGridColumnNumber("@id", getXWindowId(windowTitle), colName);

		String xpath = "";
		String classValue = "";
		
		if(colName.contains("Unit Price")){
			clickOnTableCell("energy properties", rowNum, colNo);
			
			classValue =  driver.findElement(By.xpath("//div[contains(@class,'energy properties')]//div[@class='x-grid3']//div[contains(@class, 'x-grid-editor')]//input")).getAttribute("class");
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertFalse(classValue.contains("readonly"), "Unit Price Field is Editable");
		} else {
		
			if(colName.contains("Tax Code 1") || colName.contains("Tax Code 2")) {
				xpath = "//div[@id='"+getXWindowId(windowTitle)+"']//div[@class='x-grid3-body']//div["+rowNum+"]//div[contains(@class, 'x-grid3-col-ext-"+colNo+"')]/ancestor::td";
				System.out.println(xpath);
				classValue = McsElement.getElementByXpath(driver, xpath).getAttribute("class");
			} else {
				xpath = "//div[@id='"+getXWindowId(windowTitle)+"']//div[@class='x-grid3-body']//div["+rowNum+"]//div[contains(@class, 'x-grid3-col-"+colNo+"')]/ancestor::td";
				classValue = McsElement.getElementByXpath(driver, xpath).getAttribute("class");
			}
		}
		
		
		return classValue;
	}
	
	/**
	 *  Helper method to get column number in Grid 
	 */
	public String getGridColumnNumber(String attribute, String attributeValue, String columnName){
		
		String columnClass = McsElement.getElementByXpath(driver,"(//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'x-grid3')]//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')])").getAttribute("class");
		String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);
		return columnNumber;
	}
	
	public void clickOnTableCell(String windowTitle, String rowNum, String colNo){
		
		String xpath = "//div[contains(@class, '"+windowTitle+"')]//div[@class='x-grid3-body']//div["+rowNum+"]//div[contains(@class, 'x-grid3-col-"+colNo+"')]";
		WebElement ele = driver.findElement(By.xpath(xpath));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
	}
	
	/**
	 * Helper method to set Unit Price in Grid
	 */
	public void setUnitPriceInGrid(String windowHeader, String rowNum, String colName, String unitPrice){
		
		String colNo = getGridColumnNumber("@id", getXWindowId(windowHeader), colName);
		
		clickOnTableCell("energy properties", rowNum, colNo);
		
		waitForExtJSAjaxComplete(20);
		
		String xpath = "//div[@id='"+getXWindowId(windowHeader)+"']//div[contains(@class,'x-grid-editor') and not(contains(@style,'visibility: hidden'))]//input[@type='text']";

		WebElement textField = McsElement.getElementByXpath(driver, xpath);
		
		textField.click();
		
		textField.clear();

		textField.sendKeys(unitPrice);

		textField.sendKeys(Keys.ENTER);
	}
	
	public static WebElement checkRowInGriByTextValueAndClick(WebDriver webDriver, String attribute, String dialogClass, String textValue)  {	
		WebElement webElement = webDriver.findElement(By.xpath("//div[contains("+attribute+",'"+dialogClass+"')]//*[@class='x-grid3']//div[text()='"+textValue+"']"));
		webElement.click();
		Reporter.log("Check element "+textValue+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	
	public void saveClose(String attributeValue) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", attributeValue, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Save & Close", true);
	}
	
	public void setDateEnergyPrice(String date) {
		WebElement dateField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_PROPERTIES, 
				"input", "@name", "dateStart", true, true);
		dateField.click();
		dateField.clear();
		dateField.sendKeys(date);
		Reporter.log("Set EffectiveDate - "+date, true);
	}
	
	public void setTimeEnergyPrice(String time) {
		
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+ENERGY_PROPERTIES+"')]//input[@name='timeStart']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, time);
		Reporter.log("Set Time - "+time, true);
	}
	
	/**
	 * Helper method to verify if all elements are present in Page
	 */
	public boolean verifyElementsInSP(String attribute, String name){
		try{
			driver.findElement(By.xpath("//div[contains(@class,'"+attribute+"')]//label/..//input[@name='"+name+"']" + "|"
					+ "//div[contains(@class,'"+attribute+"')]//label/..//textarea[@name='"+name+"']"));
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
		
	/**
	 * Helper method to get Values from Supply System Drop down
	 */
	public List<String> getSupplySystemValues(String attributeValue){
		WebElement supplySystemDropdown = driver.findElement(By.xpath("//div[contains(@class,'"+attributeValue+"')]//input[@name='supplySystem']"));
		
		supplySystemDropdown.click();
		
		List<WebElement> values = driver.findElements(By.xpath("//div[contains(@class, 'x-combo-list') and (contains(@style, 'visibility: visible'))]//div[contains(@class,'x-combo-list-item')]"));
					
		ArrayList<String> lsValues = new ArrayList<String>(); 
				
		for(int i=0; i<values.size(); i++){
					
		String valueText = values.get(i).getText().trim();
					
		lsValues.add(valueText);
		}
		
		return lsValues;
	}
	
	public String getPowerCapacityText() {
		return McsElement.getElementByXpath(driver, "//div[contains(@class,'slnmSPId ')]//label[contains(@class, 'x-form-item-label') and (contains(text(), 'Power Capacity'))]").getAttribute("textContent");
	}
	
	/**
	 * Helper method to verify if all elements are present in Page
	 */
	public boolean verifySupplySystemInSP(String attribute, String name){
		try{
			driver.findElement(By.xpath("//div[contains(@class,'"+attribute+"')]//label/..//input[@name='"+name+"']//ancestor::div[contains(@class, 'x-form-field-trigger-wrap x-hide-display')]"));
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Helper method to verify QTIP text 
	 * @return
	 */
	public String verifyInvalidClassName(String capacity){
		waitForExtJSAjaxComplete(25);
		//String xpath = "//div[contains(@class, 'slnmSPId')]//input[@id='capacity']";
		System.out.println("Inside Ganesha");
		String qtipG = driver.findElement(By.xpath("//div[contains(@class, 'slnmSPId')]//input[@id='capacity']")).getAttribute("class");
		
	/*	Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
		
		waitForExtJSAjaxComplete(25);
		String dfjs = element.getAttribute("qclass");
		System.out.println(dfjs);
		waitForExtJSAjaxComplete(25);
		String qtipG = element.getAttribute("qtip");*/
		System.out.println(qtipG);
		return qtipG;
	}
	
	public boolean verifyTariffIsLimitedByDnoCommodity(String value) {
		try{
			if(value.contains("Electricity")){
				driver.findElement(By.xpath("//div[contains(@class, 'x-window x-window-noborder x-resizable-pinned') and not (contains(@style, 'visibility: hidden'))]//div[@class='x-grid3-body']//div[contains(text(), '"+value+"')]"));
				return true;
			}else{
				driver.findElement(By.xpath("//div[contains(@class, 'x-window x-window-noborder x-resizable-pinned') and not (contains(@style, 'visibility: hidden'))]//div[@class='x-grid3-body']//div[contains(@class, 'x-grid3-col-1') and contains(text(), '"+value+"')]"));
				return true;
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Helper method to pass the value for RenewableEnergy 
	 * @return
	 */
	public void setRenewableEnergy(String renewableEnergy)
	{
		WebElement renewableEnergyField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_PROPERTIES, 
				"input", "@name", "renewableEnergy", true, true);
		renewableEnergyField.click();
		renewableEnergyField.clear();
		renewableEnergyField.sendKeys(renewableEnergy);
		Reporter.log("Set RenewableEnergy - "+renewableEnergy, true);
	}
	
	/**
	 * Helper method to pass the value for ContractReference 
	 * @return
	 */
	public void setContractReference(String contractReference)
	{
		WebElement contractReferenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_PROPERTIES, 
				"input", "@name", "contractReference", true, true);
		contractReferenceField.click();
		contractReferenceField.clear();
		contractReferenceField.sendKeys(contractReference);
		Reporter.log("Set ContractReference - "+contractReference, true);
		
	}
	
	/**
	 * Helper method to pass the value for Notes 
	 * @return
	 */
	public void setNotes(String notes)
	{
		WebElement notesField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_PROPERTIES, 
				"textarea", "@name", "notes", true, true);
		notesField.click();
		notesField.clear();
		notesField.sendKeys(notes);
		Reporter.log("Set Notes - "+notes, true);
	}
	
	/**
	 * Helper method to get  the data from  Notes Field 
	 * @return
	 */
	public String getNotesFromBusinessPartnersForm(String fieldName) {
		return driver
				.findElement(
						By.xpath("//div[contains(@class,'"+ENERGY_PROPERTIES+"')]//textarea[@name='"+fieldName+"']"))
				.getAttribute("value");
	}
	
	/**
	 * Helper method to Accept the Dialog Message
	 */
	public boolean verifyDialogMsg() {
		boolean status = false;
		try{
			status = driver.findElement(By.xpath("//div[contains(@class, 'ext-mb-content')]//span[text()='"+msgValue+"']")).isDisplayed();
			return status;
		} catch (Exception e){
			e.printStackTrace();
		}
		return status;
	}
}
