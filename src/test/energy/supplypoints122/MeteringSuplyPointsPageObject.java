package test.energy.supplypoints122;

//import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;












import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class MeteringSuplyPointsPageObject extends AbstractMcsTestSuite {

	protected final String DIALOG_SP = "slnmSPId";
	protected final String SUPPLY_POINT_GRID_CLASS = "x-tab-panel x-tab-panel-noborder";
	
	protected final String XPATH_COMMODITY_SPECIFIC_PROPERTIES = "//div[contains(@class,'slnmSPId ')]//span[contains(@class, 'x-fieldset-header-text') and text()='Commodity-specific Properties']";
	
	protected final String XPATH_TARIFF = "//div[contains(@class, 'slnmTrfId')]";

	static Integer codeIncrement = 0;
	
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
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-tab-panel x-tab-panel-noborder",
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
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Add Supply Point", true, true).click();
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
		
	public void setType(String typeName){
		clickLookup(DIALOG_SP, "supplyPointType");
		setValueGridLookup(typeName);
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
		setValueGridLookup(commodityName);
		Reporter.log("Set Commodity - "+commodityName, true);
	}
	
	public String getCommodity() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+DIALOG_SP+"')]//input[@name='commodity']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setPressureUOM(String pressureUOM){
		clickLookup(DIALOG_SP, "pressureUom");
		setValueGridLookup(pressureUOM);
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
		waitForMaskDisappear();
		Reporter.log("Save & Close", true);
	}
	
	public void save() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_SP, "button",
				"text()", "Save", true, true).click();
		waitForMaskDisappear();
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
//		WebElement sp = McsElement.getElementByPartAttributeValueAndParentElement(driver,
//				"div", "@id", "hierarchy",
//				"*", "text()", supplyPointCode, true, true);
//		javaScriptFocus(sp);
//		javaScriptClick(sp);
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
		WebElement supplySystemDropdown = driver.findElement(By.xpath("//div[contains(@class,'"+attributeValue+"')]//input[@name='supplySystem']//following-sibling::img"));
		
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
	
	public void setEffectiveDateMeterInfrastructure(String date) {
		WebElement dateField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", SUPPLY_POINT_GRID_CLASS, 
				"input", "@name", "effective-date", true, true);
		dateField.click();
		dateField.clear();
		dateField.sendKeys(date);
		Reporter.log("Set EffectiveDate - "+date, true);
	}
}
