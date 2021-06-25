package test.energy.energyobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class EnergyObjectsPageObject extends AbstractMcsTestSuite {
	
	protected final String DIALOG_ENERGY_OBJECT = "slnmEnergyObjectsId";
	protected final String ENERGY_OBJECT_GRID_CLASS = "x-panel-tbar";
	String site = "slnmEnrgSite2";
	
	public void expandNavigator() {
		if (McsElement.getElementByXpath(driver, "//div[contains(@class, 'slnmNrgMenu')]"
				+ "//span[contains(text(), 'Navigator')]//..//..").getAttribute("class").contains("collapsed")) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu", 
				"span", "text()", "Navigator", true, true).click();
		Reporter.log("Expand Navigation", true);
		}
	}
	
	public void clickAddEnergyObjectButton(String gridClass) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Add Energy Object", true, true).click();
		Reporter.log("Click Add Energy Object button on right panel", true);
	}
	
	public void clickEditEnergyObjectButton(String gridClass) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Edit Energy Object", true, true).click();
		Reporter.log("Click Edit Energy Object button on right panel", true);
	}
	
	/**
	 * To Click Delete Or Edit Button In The Panel
	 * @param pannelClass
	 * @param text
	 */
	public void clickOverViewButtonsInEnergyObjectsOfFacilities(String pannelClass, String text) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", pannelClass, "button", "@class", "x-btn-text",
				"text()", text, true, true).click();
		Reporter.log("Click Edit Or Delete Button In The panel", true);
	}
	

	/**
	 * Expand Facilities Tab
	 */
	public void expandFacilities() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"span", "text()", "Facilities", true, true).click();
		Reporter.log("Expand Facilities", true);
	}
	
	/**
	 * Click on Energy Objects Tab in Facilities
	 * @param entity
	 */
	public void openFacilityEntity(String entity) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"button", "text()", entity, true, true).click();
		Reporter.log("open FacilityEntity " + entity, true);
	}
	
	public void clickDeleteEnergyObjectButton(String gridClass) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Delete Energy Object", true, true).click();
		Reporter.log("Click Delete Energy Object button on right panel", true);
	}
	
	public void setCode(String code) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_ENERGY_OBJECT, "input", "@name",
				"code", true, true);
		field.click();
		field.clear();
		field.sendKeys(code);
		Reporter.log("Set Code - " + code, true);
	}
	
	public void setReference(String reference) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_ENERGY_OBJECT, "input", "@name",
				"reference", true, true);
		field.click();
		field.clear();
		field.sendKeys(reference);
		Reporter.log("Set Reference - " + reference, true);
	}
	
	public void setDescription(String description) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_ENERGY_OBJECT, "textarea", "@name",
				"description", true, true);
		field.click();
		field.clear();
		field.sendKeys(description);
		Reporter.log("Set Description - " + description, true);
	}
	
	public void setParentObject(String parentObject){
		clickLookup(DIALOG_ENERGY_OBJECT, "parentObject");
		setValueGridLookupWithFilters(parentObject, "Code");
		Reporter.log("Set Parent Object - "+parentObject, true);
	}
	
	public void setType(String type){
		clickLookup(DIALOG_ENERGY_OBJECT, "type");
		setValueGridLookup(type);
		try {
			clickOnDialogButton("Yes");
		} catch (Exception e) {}
		Reporter.log("Set Type - "+type, true);
	}
	
	public static WebElement checkNodeInTreeByTextValue(WebDriver webDriver,
			String nodeText, String id) {
		WebElement webElement = webDriver.findElement(By
				.xpath("//div[@id='"+id+"']//div[contains(., '" + nodeText
						+ "') and contains(@class,'x-tree-node-el')]"));
		webElement.click();
		return webElement;
	}
	
	public void setPhysicalLocation(String site, String physicalLocation) {
		clickLookupNewUI("@class", DIALOG_ENERGY_OBJECT, "physicalLocation", "Select a Location");
		
		setValueGridLookupWithFiltersNewUI("@class", "x6-window-default-closable", physicalLocation, "Reference");
		waitForExtJSAjaxComplete(5);
		Reporter.log("Set Physical Location - "+physicalLocation, true);
		waitForExtJSAjaxComplete(20);
	}
	
	public void setStatus(String statusName){
		clickLookup(DIALOG_ENERGY_OBJECT, "status");
		setValueGridLookup(statusName);
		Reporter.log("Set Status - "+statusName, true);
	}
	
	public void setClientOrganization(String clientOrganization){
		clickLookup(DIALOG_ENERGY_OBJECT, "clientOrganization");
		setValueGridLookup(clientOrganization);
		Reporter.log("Set Client Organization - "+clientOrganization, true);
	}
	
	public void saveClose(String dialogClass) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", dialogClass, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		Reporter.log("Save and Close", true);
	}
	
	public void save(String dialogClass) {
		McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", dialogClass, "button",
				"text()", "Save", true, true).click();
		Reporter.log("Click Save button", true);
	}
	
	public void close(String dialogClass) {
		driver
		.findElement(
				By.xpath("(//div[contains(@class,'"+dialogClass+"')]//button[text()='Close'])[last()]")).click();
		Reporter.log("Click Close button", true);
	}
	
	public String getFieldValue(String fieldName) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+DIALOG_ENERGY_OBJECT+"')]//input[@name='"+fieldName+"']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public String getTextAreaValue(String textAreaName) {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", DIALOG_ENERGY_OBJECT, "textarea",
				"@name", textAreaName, true, true).getAttribute("value");
	}
	
	/**
	 * Helper method to get Type of Energy Object from Energy Objects window
	 * @param fieldName
	 * @return
	 */
	public String getTypeOfEnergyObject() {
		
		return McsElement
				.getElementByXpath(driver,
						"//div[contains(@class,'"+DIALOG_ENERGY_OBJECT+"')]//input[@name='type']/following-sibling::input[@type='text']")
				.getAttribute("value");
	}
	
	//////////////////////////////////////EnergyObjectsFormValidation///////////////////////////////
	
	public boolean verifyMeterFormIsOpen() {
		try {
			driver.findElement(
					By.xpath("//div[contains(@class,'"+DIALOG_ENERGY_OBJECT+"')]"));
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
					By.xpath("//div[contains(@class,'"+dialog+"')]//span[contains(@class,'window-header')]")).getText().contains("Add Energy Object");
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	//Reopen Energy Object Form being either saved or not
	public boolean reopenEOForm(){
		
			 if (!checkMandatoryFieldSave(DIALOG_ENERGY_OBJECT)) {
				 
				 close(DIALOG_ENERGY_OBJECT);
				 
				 waitForExtJSAjaxComplete(20);
			     
			     waitForExtJSAjaxComplete(20);
			     
			     test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
			     waitForMaskDisappear();
			     
			     waitForExtJSAjaxComplete(20);
			     
			     waitForExtJSAjaxComplete(20);
			     
			     clickAddEnergyObjectButton(ENERGY_OBJECT_GRID_CLASS);
			     
			     waitForExtJSAjaxComplete(20);

			     return false;
			 }
			 else {
				 
				 close(DIALOG_ENERGY_OBJECT);
				 
				 waitForExtJSAjaxComplete(20);
				 
				 clickOnDialogButton("Yes");
				 
				 waitForExtJSAjaxComplete(20);
			     
			     waitForExtJSAjaxComplete(20);
			     
			     test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
			     waitForMaskDisappear();
			     
			     waitForExtJSAjaxComplete(20);
			     
			     waitForExtJSAjaxComplete(20);
			     
			     clickAddEnergyObjectButton(ENERGY_OBJECT_GRID_CLASS);
			     
			     waitForExtJSAjaxComplete(20);
			     
				 return true;
			 }
	 }
	
	 
	 //Method clears fields value
	 public void clearField(String fieldNameTag, String fieldName) {
		WebElement field =  driver.findElement
				(By.xpath("(//div[contains(@class, '"+DIALOG_ENERGY_OBJECT+"')]//input[contains(@name, '"+fieldNameTag+"')]//..//input)[last()]"));
		field.click();
		field.clear();
		Reporter.log("Clear "+fieldName+" field", true);
	 }
	 
	public void clickCancelXwindow() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-window-footer x-window-footer-noborder x-panel-btns", "button",
				"text()", "Cancel", true, true).click();
		waitForElementDisappear("//div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]");
		Reporter.log("Click Cancel", true);
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
	
	//Check if WebElement has "disabled" class
	public boolean checkWebElementDisabled(String field){	
		try {
			driver.findElement(By.xpath("//div[contains(@class, '"+DIALOG_ENERGY_OBJECT+"')]//input[@name='"+field+"']//..//input[contains(@class, 'readonly')]"));
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
}
