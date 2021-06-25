package test.energy.weatherstations;

//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.openqa.selenium.WebDriver;













import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class MeteringWeatherstationChanelsPageObject extends AbstractMcsTestSuite {
	
	protected final String ADD_WEATHERSTATIONS_FORM_CLASS = "slnmWSId";

	protected final String ADD_WEATHERSTATIONS_FOOTER_CLASS = "x-window-footer x-panel-btns";
	
	protected final String WEATHERSTATIONS_GRID_CLASS = "x-tab-panel x-tab-panel-noborder";
	
	protected final String WEATHERSTATIONS_CHANELS_GRID_CLASS =  "x-panel channels-tab x-panel-noborder";
	
	protected final String ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS = "slnmWSChnlId";
	
	public static String channelDialogId;
	public static String gaugesDialogId;

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
		
		Reporter.log("Delete selected Channel measure" + " (" + timer.stop() + "ms)", true);
	}
	
	public String getReference() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "input",
				"@name", "reference", true, true).getAttribute("value");
	}

	public void setReference(String reference) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "input", "@name",
				"reference", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(reference);
		Reporter.log("Set Reference - " + reference, true);
	}
	
	public String getCode() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "input",
				"@name", "code", true, true).getAttribute("value");
	}
	
	public void setChannelParameter(String proposerName){
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "channelParameter");
		setValueGridLookup("@id",getXWindowId("Select a Parameter"),proposerName);
	}
	
	public String getChannelParameter() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'slnmWSChnlId')]//input[@name='channelParameter']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public String getUOM() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'slnmWSChnlId')]//input[@name='unitOfMeasure']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setUnitOfMeasure(String proposerName){
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure");
		//setValueGridLookup("@id",getXWindowId("Select a Unit Of Measure"),proposerName);
		//setValueGridLookup(proposerName);
		String lookupId = getXWindowId("Select a Unit Of Measure");
		setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, proposerName, "Reference");
		//clickOkXwindow();
		//waitForExtJSAjaxComplete(20);
		//McsElement.getElementByPartAttributeValueAndParentElement(driver,
		//		"div", "@id", lookupId, "button",
		//		"text()", "OK", true, true).click();
		Reporter.log("Set UOM - "+proposerName, true);
	}
	
	public void setBaseTemperature(String proposerName){
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "baseTemperature");
		setValueGridLookup("@id",getXWindowId("Select a Base Temperature"),proposerName);
	}
	
	public String getBaseTemperature() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'slnmWSChnlId')]//input[@name='baseTemperature']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public String getEntryMethod() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "input",
				"@name", "entryMethod", true, true).getAttribute("value");
	}
	
	public void setChnlEntryMethod(String chnlEntryMethod) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS+"')]//input[@name='entryMethod']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, chnlEntryMethod);
		Reporter.log("Set Entry Method - "+chnlEntryMethod, true);
	}
	
	public String getReadingInterval() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "input",
				"@name", "readingInterval", true, true).getAttribute("value");
	}
	
	public void saveClose() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close Clicked", true);
	}
	
	public void saveWS() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_WEATHERSTATIONS_FORM_CLASS, "button",
				"text()", "Save", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close Clicked", true);
	}
	
	public void closeWS() {
		McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", ADD_WEATHERSTATIONS_FORM_CLASS, "button",
				"text()", "Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close Clicked", true);
	}
	
	
	public void save() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "button",
				"text()", "Save", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close Clicked", true);
	}

	public void close() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "button", "text()",
				"Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close Clicked", true);
	}
	
	public void changeTab(String tabName) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"li", "@id", tabName+"-tab", "span", "@class", "x-tab-strip-text", true, true).click();
		Reporter.log("Change tab to - "+tabName, true);
	}
	
	//TODO: Move later to grid methods (usable when grid doesn't have checkboxes)
	public static WebElement checkRowInGriByTextValueAndClick(WebDriver webDriver, String textValue, String dialogId)  {	
			WebElement webElement = webDriver.findElement(By.xpath("//div[@id='"+dialogId+"']//*[@class='x-grid3']//div[contains(text(),'"+textValue+"')]"));
			webElement.click();
			try {Thread.sleep(500);} catch (InterruptedException e) {}
			return webElement;
	}
		
	//TODO: Move later to grid methods (usable when grid doesn't have checkboxes)
	public WebElement checkRowInGriByTextValueAndClick(WebDriver webDriver, String textValue)  {
		WebElement webElement = webDriver.findElement(By.xpath("//div[contains(@class, 'slnmWSId')]"+xpathGeneratorForTextElement(textValue)));
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
	
	public boolean isRowInGridChanelPresent(String gridValue) {
		try {
			McsElement.getElementByXpath(driver, "//div[contains(@class, 'slnmWSId')]//div[contains(text(),'" + gridValue + "')]");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//TODO: Move to general methods later
	public void clickButton(String buttonName, String dialogId)
	{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
					"div", "@id", dialogId, "button", "@class", "x-btn-text",
					"text()", buttonName, true, true).click();
			Reporter.log("Click "+buttonName+" button", true);
	}
	
//////////////WeatherStationFormValidation/////////////////
	
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
	@SuppressWarnings("finally")
	public boolean reopenWeatherStationForm(){
		
			 if (!checkMandatoryFieldSave(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS)) {
				 
				 close(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS);
				 
				 waitForExtJSAjaxComplete(20);
				 
				 clickAddButton(ADD_WEATHERSTATIONS_FORM_CLASS);
					
				 waitForExtJSAjaxComplete(20);

			     return false;
			 }
			 else {
				 
				 close();
				 
				 waitForExtJSAjaxComplete(20);
				 
				 try { clickOnDialogButton("Yes"); }
				 
				 //catch (Exception e) {}
				 
				 finally {
				 
				 waitForExtJSAjaxComplete(20);
			     
				 clickAddButton(ADD_WEATHERSTATIONS_FORM_CLASS);
					
				 waitForExtJSAjaxComplete(20);
				 
				 return true;
				 
				 }
				 
				 
			 }
		}
	 
	 public boolean verifyItemExists(String inputName, String ddItem) {
			String elementId = driver.findElement(By
					.xpath("//div[contains(@class,'"+ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS+"')]//input[@name='" + inputName + "']")).getAttribute("Id");
			return DropDown.isExtJsComboValuePresent(driver, elementId, ddItem);	
	}
	 
	 public boolean checkInputDisabled(String elementName){	
			try {
				driver.findElement(By.xpath("//div[contains(@class, '"+ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS+"')]//input[@name='" + elementName + "']//..//input[contains(@class, 'readonly')]"));
				return true;
			} catch (Exception e) {
				return false;
			}
			
	}
		
	public boolean checkChannelInputDisabled(String elementName){	
		try {
			driver.findElement(By.xpath("//div[contains(@class, '"+ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS+"')]//input[@name='" + elementName + "']//..//input[contains(@class, 'readonly')]"));
			return true;
		} catch (Exception e) {
			return false;
		}
			
	}
	
	 public void clearChnlField(String fieldNameTag, String fieldName) {
			WebElement field =  driver.findElement
				(By.xpath("(//div[contains(@class, '"+ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS+"')]//input[contains(@name, '"+fieldNameTag+"')]//..//input)[last()]"));
			field.click();
			field.clear();
			Reporter.log("Clear "+fieldName+" field", true);
	}
	 
	public void setWSCode (String code) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_WEATHERSTATIONS_FORM_CLASS, "input", "@name",
				"code", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(code);
		Reporter.log("Set Code - " + code, true);
	}
	
	public void setWSReference (String reference) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_WEATHERSTATIONS_FORM_CLASS, "input", "@name",
				"reference", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(reference);
		Reporter.log("Set Reference - " + reference, true);
	}
	
	public void setEntryMethod (String entryMethod) {
		String id = McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_WEATHERSTATIONS_FORM_CLASS, "input",
				"@name", "entryMethod", true, true).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, entryMethod);
		Reporter.log("Set Degree Days Calculation Method - " + entryMethod, true);
	}
	
}
