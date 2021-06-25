package test.energy.gauges;

//import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.openqa.selenium.WebDriver;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class MeteringGaugesChanelsPageObject extends AbstractMcsTestSuite {


	protected final String ADD_GAUGES_FORM_CLASS = "slnmGaugeId";
	
	protected final String ADD_CHANEL_GAUGES_FORM_CLASS = "slnmGaugeChnlId";

	protected final String ADD_GAUGES_FOOTER_CLASS = "x-window-footer x-panel-btns";
	
	protected final String GAUGES_GRID_CLASS = "x-tab-panel x-tab-panel-noborder";
	
	protected final String GAUGES_CHANELS_GRID_CLASS =  "x-panel channels-tab x-panel-noborder";
	
	protected final String 	EDIT_GAUGE_CHANNEL_WINDOW_HEADER = "Edit Gauge Channel";
	
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
		
		Reporter.log("Delete selected Channel measure" + " (" + timer.stop() + "ms)", true);
	}
	
	/**
	 * Helper method to click on Delete Button in Any of the Navigator Options
	 * But will not delete the record. (For Deletion Use clickDeleteBtn)
	 */
	public void verifyDeleteFunctionality(String dialogId, String buttonName) {
		/*McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Delete", true, true).click();*/
		
		WebElement element = driver.findElement(By.xpath("//div[contains(@id, '"+dialogId+"')]//button[contains(@class, 'x-btn-text') and text()='"+buttonName+"']"));
        if ("chrome".equals(configuration.getBrowser())) {
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", element);
            } catch (Exception e) {
            	System.out.println("Exception is G " +e);
            }
        }

        element.click();
		Reporter.log("Click on Delete button and cancel Deletion", true);
	}
	
	public String getReference() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_CHANEL_GAUGES_FORM_CLASS, "input",
				"@name", "reference", true, true).getAttribute("value");
	}

	public void setReference(String reference) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_CHANEL_GAUGES_FORM_CLASS, "input", "@name",
				"reference", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(reference);
		Reporter.log("set reference " + reference, true);
	}
	
	public String getClassGaugeChannel() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_CHANEL_GAUGES_FORM_CLASS, "input",
				"@name", "class", true, true).getAttribute("value");
	}
	
	public String getCode() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_CHANEL_GAUGES_FORM_CLASS, "input",
				"@name", "code", true, true).getAttribute("value");
	}

	public void setCode(String code) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_CHANEL_GAUGES_FORM_CLASS, "input", "@name",
				"code", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(code);
		Reporter.log("set code " + code, true);
	}

	public void setCalibrationDate(String code) {
		WebElement date = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_CHANEL_GAUGES_FORM_CLASS, "input", "@name",
				"calibrationDate", true, true);
		date.clear();
		date.sendKeys(code);
		Reporter.log("set date " + code, true);
	}

	public String getCalibrationDate() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_CHANEL_GAUGES_FORM_CLASS, "input",
				"@name", "calibrationDate", true, true).getAttribute("value");
	}
	
	public void setChannelParameter(String proposerName){
		clickLookup(ADD_CHANEL_GAUGES_FORM_CLASS, "channelParameter");
		//setValueGridLookup("@id",getXWindowId("Select a Parameter"),proposerName);
		//setValueGridLookup(proposerName);
		String lookupId = getXWindowId("Select a Parameter");
		setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, proposerName, "Name");
		waitForExtJSAjaxComplete(20);
		/*McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@id", lookupId, "button",
				"text()", "OK", true, true).click();
		*/
		//clickOkXwindow();
		Reporter.log("Set Parameter - "+proposerName, true);
	}
	
	public String getChannelParameter() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'slnmGaugeChnlId')]//input[@name='channelParameter']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setUnitOfMeasure(String proposerName){
		clickLookup(ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure");
		//setValueGridLookup("@id",getXWindowId("Select a Unit Of Measure"),proposerName);
		//setValueGridLookup(proposerName);
		String lookupId = getXWindowId("Select a Unit Of Measure");
		setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, proposerName, "Code");
		//clickOkXwindow();
		//waitForExtJSAjaxComplete(20);
		//McsElement.getElementByPartAttributeValueAndParentElement(driver,
		//		"div", "@id", lookupId, "button",
		//		"text()", "OK", true, true).click();
		Reporter.log("Set UOM - "+proposerName, true);
	}
	
	public String getUnitOfMeasure() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'slnmGaugeChnlId')]//input[@name='unitOfMeasure']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void saveClose() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_CHANEL_GAUGES_FORM_CLASS, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close clicked", true);
	}
	
	public void save() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_CHANEL_GAUGES_FORM_CLASS, "button",
				"text()", "Save", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save clicked", true);
	}

	public void close() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", ADD_CHANEL_GAUGES_FORM_CLASS, "button", "text()",
				"Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Close clicked", true);
	}
	
	
	
	public void changeTab(String tabName) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"li", "@id", tabName+"-tab", "span", "@class", "x-tab-strip-text", true, true).click();
		Reporter.log("change tab to "+tabName, true);
	}
	
	//TODO: Move later to grid methods (usable when grid doesn't have checkboxes)
	public static WebElement checkRowInGriByTextValueAndClick(WebDriver webDriver, String textValue, String dialogId)  {	
			WebElement webElement = webDriver.findElement(By.xpath("//div[@id='"+dialogId+"']//*[@class='x-grid3']//div[text()='"+textValue+"']"));
			webElement.click();
			try {Thread.sleep(500);} catch (InterruptedException e) {}
			return webElement;
	}
		
	//TODO: Move later to grid methods (usable when grid doesn't have checkboxes)
	public WebElement checkRowInGriByTextValueAndClick(WebDriver webDriver, String textValue)  {
		WebElement webElement = webDriver.findElement(By.xpath("//div[contains(@class, 'slnmGaugeId')]"+xpathGeneratorForTextElement(textValue)));
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
			McsElement.getElementByXpath(driver, "//div[contains(@class, 'slnmGaugeId')]//div[text()='"+gridValue+"']");
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
	
	public String getFieldValue(String dialogClass, String fieldName) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+dialogClass+"')]//input[@name='"+fieldName+"']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public String getTextAreaValue(String dialogClass, String textAreaName) {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", dialogClass, "textarea",
				"@name", textAreaName, true, true).getAttribute("value");
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
	
	//Set combobox value using only mouse click
		public void setChnlEntryType(String chnlEntryType) {
			WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
					"div", "@class", ADD_CHANEL_GAUGES_FORM_CLASS, "input", "@name",
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
					.xpath("//div[contains(@class,'"+ADD_CHANEL_GAUGES_FORM_CLASS+"')]//input[@name='entryMethod']")).getAttribute("Id");
			DropDown.setExtJsComboValue(driver, elementId, chnlEntryMethod);
			Reporter.log("Set Entry Method - "+chnlEntryMethod, true);
		}
		
		/**
		 * Helper method to set Reading Interval
		 * @param chnlEntryMethod
		 */
		public void setChnlReadingInterval(String readingInterval) {
			String elementId = driver.findElement(By
					.xpath("//div[contains(@class,'"+ADD_CHANEL_GAUGES_FORM_CLASS+"')]//input[@name='readingInterval']")).getAttribute("Id");
			DropDown.setExtJsComboValue(driver, elementId, readingInterval);
			Reporter.log("Set Reading Interval - "+readingInterval, true);
		}
		
		/**
		 * Helper method to set Calculation Method
		 * @param calculationMethod
		 */
		public void setChnlCalculationMethod(String calculationMethod) {
			String elementId = driver.findElement(By
					.xpath("//div[contains(@class,'"+ADD_CHANEL_GAUGES_FORM_CLASS+"')]//input[@name='calculationMethod']")).getAttribute("Id");
			DropDown.setExtJsComboValue(driver, elementId, calculationMethod);
			Reporter.log("Set Reading Interval - "+calculationMethod, true);
		}
		
		public void setChnlUOM(String chnlUOM){
			clickLookup(ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure");
			setValueGridLookup(chnlUOM);
			Reporter.log("Set UOM - "+chnlUOM, true);
		}
		
		 //Method clears fields value
		 public void clearField(String fieldNameTag, String fieldName) {
			WebElement field =  driver.findElement
					(By.xpath("(//div[contains(@class, '"+ADD_GAUGES_FORM_CLASS+"')]//input[contains(@name, '"+fieldNameTag+"')]//..//input)[last()]"));
			field.click();
			field.clear();
			Reporter.log("Clear "+fieldName+" field", true);
		 }
		 
		 public void clearChnlField(String fieldNameTag, String fieldName) {
			WebElement field =  driver.findElement
				(By.xpath("(//div[contains(@class, '"+ADD_CHANEL_GAUGES_FORM_CLASS+"')]//input[contains(@name, '"+fieldNameTag+"')]//..//input)[last()]"));
			field.click();
			field.clear();
			Reporter.log("Clear "+fieldName+" field", true);
		}
		 
		 public boolean checkMandatoryFieldSave(String dialog){
				return driver.findElement(
							By.xpath("//div[contains(@class,'"+dialog+"')]//span[contains(@class,'window-header')]")).getText().contains("Add");
				
		}
		 
		//Reopen Meter Channel Form being either saved or not
		@SuppressWarnings("finally")
		public boolean reopenGaugeChannelForm(){
					
						 if (!checkMandatoryFieldSave(ADD_CHANEL_GAUGES_FORM_CLASS)) {
							 
							 gaugesDialogId = getXWindowIdByClass(ADD_GAUGES_FORM_CLASS);
							 
							 clickButton("Add", gaugesDialogId);
						     
						     waitForExtJSAjaxComplete(20);

						     return false;
						 }
						 else {
							 
							 close(ADD_CHANEL_GAUGES_FORM_CLASS);
							 
							 waitForExtJSAjaxComplete(20);
							 
							 try { clickOnDialogButton("Yes"); }
							 
							 //catch (Exception e) {}
							 
							 finally {
							 
								 waitForExtJSAjaxComplete(20);
						     
								 gaugesDialogId = getXWindowIdByClass(ADD_GAUGES_FORM_CLASS);
							 
								 clickButton("Add", gaugesDialogId);
						     
								 waitForExtJSAjaxComplete(20); 
						     
								 return true; 
							 }
						 }
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
			McsElement.getElementByAttributeValueAndParentElement(driver, "div",
					"@class", dialogClass, "button", "text()",
					"Close", true, true).click();
			Reporter.log("Click Close button", true);
		}
	
		public void clickCancelXwindow() {
			McsElement.getElementByPartAttributeValueAndParentElement(driver,
					"div", "@class", "x-window-footer x-window-footer-noborder x-panel-btns", "button",
					"text()", "Cancel", true, true).click();
			waitForElementDisappear("//div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]");
			Reporter.log("Click Cancel", true);
		}
		
		public boolean verifyItemExists(String inputName, String ddItem) {
			String elementId = driver.findElement(By
					.xpath("//div[contains(@class,'"+ADD_CHANEL_GAUGES_FORM_CLASS+"')]//input[@name='" + inputName + "']")).getAttribute("Id");
			return DropDown.isExtJsComboValuePresent(driver, elementId, ddItem);	
		}
		
		public boolean checkChannelInputDisabled(String elementName){	
			try {
				driver.findElement(By.xpath("//div[contains(@class, '"+ADD_CHANEL_GAUGES_FORM_CLASS+"')]//input[@name='" + elementName + "']//..//input[contains(@class, 'readonly')]"));
				return true;
			} catch (Exception e) {
				return false;
			}
			
		}
		
		/**
		 * Helper method to get Values from Entry Method  Drop down
		 */
		public List<String> getEntryMethodDropDownValues(String attribute, String attributeValue){
			WebElement statusDropDown = driver.findElement(By.xpath("//div[contains("+attribute+",'" + attributeValue + "')]//input[@name='entryMethod']"));
			
			statusDropDown.click();
			
			List<WebElement> values = driver.findElements(By.xpath("//div[contains(@class, 'x-combo-list') and (contains(@style, 'visibility: visible'))]//div[contains(@class,'x-combo-list-item')]"));
						
			ArrayList<String> lsValues = new ArrayList<String>(); 
					
			for(int i=0; i<values.size(); i++){
						
			String valueText = values.get(i).getText().trim();
						
			lsValues.add(valueText);
			}
			
			return lsValues;
		}
		
}
