package test.energy.weatherstations;

//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class MeteringWeatherStationsPageObject extends AbstractMcsTestSuite {


	protected final String ADD_WEATHERSTATIONS_FORM_CLASS = "slnmWSId";

	protected final String ADD_WEATHERSTATIONS_FOOTER_CLASS = "x-window-footer x-panel-btns";
	
	protected final String WEATHERSTATIONS_GRID_CLASS = "x-tab-panel x-tab-panel-noborder";
	
	protected final String WEATHERSTATIONS_CHANELS_GRID_CLASS =  "x-panel channels-tab x-panel-noborder";
	
	protected final String ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS = "slnmWSChnlId";

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

	public void openAnalysisEntity(String entity) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"button", "text()", entity, true, true).click();
		Reporter.log("Open Metering - " + entity, true);
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
		clickOnDialogButton("OK");
		
		Reporter.log("Delete selected WEATHERSTATIONS measure" + " (" + timer.stop() + "ms)", true);
	}
	
	public String getReference() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_WEATHERSTATIONS_FORM_CLASS, "input",
				"@name", "reference", true, true).getAttribute("value");
	}

	public void setReference(String reference) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_WEATHERSTATIONS_FORM_CLASS, "input", "@name",
				"reference", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(reference);
		Reporter.log("Set Reference - " + reference, true);
	}
	
	public String getCode() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_WEATHERSTATIONS_FORM_CLASS, "input",
				"@name", "code", true, true).getAttribute("value");
	}
	
	public void setCode(String code) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_WEATHERSTATIONS_FORM_CLASS, "input", "@name",
				"code", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(code);
		Reporter.log("Set Code - " + code, true);
	}
	
	public String getDescription() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_WEATHERSTATIONS_FORM_CLASS, "textarea",
				"@name", "description", true, true).getAttribute("value");
	}

	public void setDescription(String reference) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_WEATHERSTATIONS_FORM_CLASS, "textarea", "@name",
				"description", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(reference);
		Reporter.log("Set Description - " + reference, true);
	}
	
	public void setStatus(String proposerName){
		clickLookup(ADD_WEATHERSTATIONS_FORM_CLASS, "status");
		setValueGridLookup(proposerName);
//		waitForExtJSAjaxComplete(5);
	}
	
	public String getStatus() {
		return driver
				.findElement(
						By.xpath("(//form[contains(@class,'x-panel-body x-panel-body-noheader x-panel-body-noborder x-form')]//input[@name='status']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setUOM(String itemName){
		clickLookup(ADD_WEATHERSTATIONS_FORM_CLASS, "uom");
		setValueGridLookup(itemName);
//		waitForExtJSAjaxComplete(5);
	}
	
	public String getUOM() {
		return driver
				.findElement(
						By.xpath("(//form[contains(@class,'x-panel-body x-panel-body-noheader x-panel-body-noborder x-form')]//input[@name='uom']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setEntryMethod(String className) {
		String id = McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_WEATHERSTATIONS_FORM_CLASS, "input",
				"@name", "entryMethod", true, true).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, className);
		Reporter.log("Set Degree Days Calculation Method - " + className, true);
	}
	
	public String getEntryMethod() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_WEATHERSTATIONS_FORM_CLASS, "input",
				"@name", "entryMethod", true, true).getAttribute("value");
	}
	
	public void setTimeZone(String proposerName){
		clickLookup(ADD_WEATHERSTATIONS_FORM_CLASS, "timezone");
		setValueGridLookup(proposerName);
//		waitForExtJSAjaxComplete(5);
	}
	
	public String getTimeZone() {
		return driver
				.findElement(
						By.xpath("(//form[contains(@class,'x-panel-body x-panel-body-noheader x-panel-body-noborder x-form')]//input[@name='timezone']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setSupplier(String itemName){
		clickLookup(ADD_WEATHERSTATIONS_FORM_CLASS, "supplier");
		setValueGridLookup(itemName);
//		waitForExtJSAjaxComplete(5);
	}
	
	public String getSupplier() {
		return driver
				.findElement(
						By.xpath("(//form[contains(@class,'x-panel-body x-panel-body-noheader x-panel-body-noborder x-form')]//input[@name='supplier']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void saveClose() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_WEATHERSTATIONS_FOOTER_CLASS, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close Clicked", true);
	}
	
	public void save() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_WEATHERSTATIONS_FOOTER_CLASS, "button",
				"text()", "Save", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close Clicked", true);
	}

	public void close() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", ADD_WEATHERSTATIONS_FOOTER_CLASS, "button", "text()",
				"Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close Clicked", true);
	}
	
	public void changeTab(String tabName) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"li", "@id", tabName+"-tab", "span", "@class", "x-tab-strip-text", true, true).click();
		Reporter.log("change tab to "+tabName, true);
	}
	
	public boolean isRowInGridChanelPresent(String gridValue) {
		try {
			driver.findElement(By.xpath("//div[contains(@class, 'x-panel channels-tab x-panel-noborder')]"+xpathGeneratorForTextElement(gridValue)));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static String xpathGeneratorForTextElement(String text){
		  
		  String subStrings[] = text.split(" ");
		  
		  String xpath = "//div[starts-with(text(),'"+subStrings[0]+"')";
		  
		  for(int i=1; i<subStrings.length; i++){

		   xpath+="and contains(text(),'"+subStrings[i]+"')";
		  }
		  
		  return xpath+"]";
		  
	}
	
	////////////////WeatherStationFormValidation/////////////////
	
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

	public void clickCancelXwindow() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-window-footer x-window-footer-noborder x-panel-btns", "button",
				"text()", "Cancel", true, true).click();
		//waitForElementDisappear("//div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]");
		Reporter.log("Click Cancel", true);
	}
	
	public void clearField(String fieldNameTag, String fieldName) {
		WebElement field =  driver.findElement
				(By.xpath("(//div[contains(@class, '"+ADD_WEATHERSTATIONS_FORM_CLASS+"')]//input[contains(@name, '"+fieldNameTag+"')]//..//input)[last()]"));
		field.click();
		field.clear();
		Reporter.log("Clear "+fieldName+" field", true);
	 }
	
	public void save(String dialogClass) {
		McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", dialogClass, "button",
				"text()", "Save", true, true).click();
		Reporter.log("Click Save button", true);
	}
	
	public void close(String dialogClass) {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", dialogClass, "button", "text()",
				"Close", true, true).click();
		Reporter.log("Click Close button", true);
	}
	
	public boolean checkMandatoryFieldSave(String dialog){
		return driver.findElement(
					By.xpath("//div[contains(@class,'"+dialog+"')]//span[contains(@class,'window-header')]")).getText().contains("Add");
		
	}
	
	//Reopen Weather Station Form being either saved or not
	 public boolean reopenWeatherStationForm(){
		
			 if (!checkMandatoryFieldSave(ADD_WEATHERSTATIONS_FORM_CLASS)) {
				 
				 close();
				 
				 waitForExtJSAjaxComplete(20);
				 
				 clickAddButton(WEATHERSTATIONS_GRID_CLASS);
					
				 waitForExtJSAjaxComplete(20);

			     return false;
			 }
			 else {
				 
				 close();
				 
				 waitForExtJSAjaxComplete(20);
				 
				 clickOnDialogButton("Yes");
				 
				 waitForExtJSAjaxComplete(20);
			     
				 clickAddButton(WEATHERSTATIONS_GRID_CLASS);
					
				 waitForExtJSAjaxComplete(20);
			     
				 return true;
			 }
		}
	 
	 public boolean verifyItemExists(String inputName, String ddItem) {
			String elementId = driver.findElement(By
					.xpath("//div[contains(@class,'"+ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS+"')]//input[@name='" + inputName + "']")).getAttribute("Id");
			return DropDown.isExtJsComboValuePresent(driver, elementId, ddItem);	
		}
		
	public boolean checkChannelInputDisabled(String elementName){	
		try {
			driver.findElement(By.xpath("//div[contains(@class, '"+ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS+"')]//input[@name='" + elementName + "']//..//input[contains(@class, 'readonly')]"));
			return true;
		} catch (Exception e) {
			return false;
		}
			
	}

}
