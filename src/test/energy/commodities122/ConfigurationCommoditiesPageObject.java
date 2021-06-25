package test.energy.commodities122;

import java.util.concurrent.TimeUnit;

import test.configuration.Configuration;
import test.framework.AbstractMcsTestSuite;
//import test.framework.Timer;




import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class ConfigurationCommoditiesPageObject extends AbstractMcsTestSuite{
	
	public final static String COMMODITY_GRID = "x-panel x-panel-noborder x-grid-panel";
	public final static String DIALOG_COMMODITY = "slnmCmdtId";
	public final static String DIALOG_CMDT_SOURCE = "slnmCommSrcId";
	public final static String DIALOG_CMDT_EQUIVALENCY = "slnmCommSrcEqId";
		
	public void expandConfiguration() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"span", "text()", "Configuration", true, true).click();
		Reporter.log("Expand configuration", true);
	}
	
	public void openConfigurationEntity(String entity) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"button", "text()", entity, true, true).click();
		Reporter.log("Open configuration " + entity, true);
	}
	//WTF
	public void clickButton(String dialogClass, String buttonName) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", dialogClass, "button", "@class", "x-btn-text",
				"text()", buttonName, true, true).click();
		Reporter.log("Click "+buttonName+" button", true);
	}
	
	public void close(String dialogClass) {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", dialogClass, "button", "text()",
				"Close", true, true).click();
		Reporter.log("Click Close button", true);
		waitForMaskDisappear();
	}
	
	public void setCode(String code, String dialogId) {
		WebElement codeField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", dialogId, 
				"input", "@name", "code", true, true);
		codeField.click();
		codeField.clear();
		codeField.sendKeys(code);
		Reporter.log("Set Code - "+code, true);
	}
	
	public String getCode(String dialogId) {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", dialogId, "input", "@name",
				"code", true, true).getAttribute("value");
	}
	
	public void setReference(String reference, String dialogId) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", dialogId, 
				"input", "@name", "reference", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(reference);
		Reporter.log("Set Reference - "+reference, true);
	}
	
	public String getReference(String dialogId) {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", dialogId, "input", "@name",
				"reference", true, true).getAttribute("value");
	}
	
	public void setClass(String dialogId, String comboValue) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+dialogId+"')]//input[@name='class']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, comboValue);
		Reporter.log("Set Class - "+comboValue, true);
	}
	
	public String getClass(String dialogId) {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", dialogId, "input", "@name",
				"class", true, true).getAttribute("value");
	}

	public void setDefaultUOM(String defaultUOM, String dialogClass){
		clickLookup(dialogClass, "uomDefault");
		setValueGridLookup(defaultUOM);
		Reporter.log("Set Default UOM - "+defaultUOM, true);
	}
	
	public String getDefaultUOM(String dialogId) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+dialogId+"')]//input[@name='uomDefault']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void saveClose(String dialogId) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", dialogId, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		waitForMaskDisappear();
		Reporter.log("Save and Close", true);
	}
	
	// Commodity Source Test Suite
	
	public void clickEquivalenciesTab()
	{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"ul", "@class", "x-tab-strip", "span", "text()", "Equivalencies", true, true).click();
		Reporter.log("Click on Equivalencies tab", true);
	}

	public void setSourceName(String source, String dialogId) {
		WebElement sourceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", dialogId, 
				"input", "@name", "name", true, true);
		sourceField.click();
		sourceField.clear();
		sourceField.sendKeys(source);
		Reporter.log("Set Source Name - "+source, true);
	}
	
	public String getSourceName(String dialogId) {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", dialogId, "input", "@name",
				"name", true, true).getAttribute("value");
	}
	
	public void setDescription(String description, String dialogId) {
		WebElement descriptionField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", dialogId, 
				"textarea", "@name", "description", true, true);
		descriptionField.click();
		descriptionField.clear();
		descriptionField.sendKeys(description);
		Reporter.log("Set Description - "+description, true);
	}
	
	public String getDescription(String dialogId) {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", dialogId, "textarea", "@name",
				"description", true, true).getAttribute("value");
	}

	public String getCommodity(String dialogId) {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", dialogId, "input", "@name",
				"commodity", true, true).getAttribute("value");
	}
	
	public String getSource(String dialogId) {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", dialogId, "input", "@name",
				"source", true, true).getAttribute("value");
	}
	
	public void setSourceInCombo(String dialogId, String comboValue) {
		String getComboValue = McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", dialogId, "input", "@name",
				"source", true, true).getAttribute("value");
		if (comboValue.equalsIgnoreCase(getComboValue))		
			return;
			else{
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+dialogId+"')]//input[@name='source']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, comboValue);
		Reporter.log("Select Source in combobox - "+comboValue, true);
			}
	}
	
	public boolean validateSourceInCombo(String dialogId, String comboValue) {
		
		try {
			String elementId = driver.findElement(By
					.xpath("//div[contains(@class,'"+dialogId+"')]//input[@name='source']")).getAttribute("Id");
			DropDown.setExtJsComboValue(driver, elementId, comboValue);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	public void setEquivalencyInCombo(String dialogId, String comboValue) {
		String getComboValue = McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", dialogId, "input", "@name",
				"equivalencyValues", true, true).getAttribute("value");
		if
		(comboValue.equalsIgnoreCase(getComboValue))
			return;
			else{		
			String elementId = driver.findElement(By
					.xpath("//div[contains(@class,'"+dialogId+"')]//input[@name='equivalencyValues']")).getAttribute("Id");
			DropDown.setExtJsComboValue(driver, elementId, comboValue);
			Reporter.log("Select Equivalency - "+comboValue, true);		
			}
			
	}
	
	public void clickButtonOnFieldSet(String buttonName, String fieldSet){
		
		McsElement.getElementByXpath(driver, "//div[contains(@class, '"+DIALOG_COMMODITY+"')]//fieldset/legend/span[text()='"+fieldSet+"']"
				+ "//..//..//button[text()='"+buttonName+"']").click();
		Reporter.log("Click "+buttonName+" on "+fieldSet+" fieldset", true);
	}
	
	public void setDate(String date, String dialogId) {
		WebElement dateField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", dialogId, 
				"input", "@name", "date", true, true);
		dateField.click();
		dateField.clear();
		dateField.sendKeys(date);
		Reporter.log("Set Date - "+date, true);
	}
	
	public String getDate(String dialogId) {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", dialogId, "input", "@name",
				"date", true, true).getAttribute("value");
	}
	
	public void setValue(String value, String dialogId) {
		WebElement valueField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", dialogId, 
				"input", "@name", "value", true, true);
		valueField.click();
		valueField.clear();
		valueField.sendKeys(value);
		Reporter.log("Set Value - "+value, true);
	}
	
	public String getValue(String dialogId) {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", dialogId, "input", "@name",
				"value", true, true).getAttribute("value");
	}
	
	public void setNote(String note, String dialogId) {
		WebElement noteField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", dialogId, 
				"textarea", "@name", "notes", true, true);
		noteField.click();
		noteField.clear();
		noteField.sendKeys(note);
		Reporter.log("Set Note - "+note, true);
	}
	
	public String getNote(String dialogId) {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", dialogId, "textarea", "@name",
				"notes", true, true).getAttribute("value");
	}
	
	public void setEquivalencyUOM(String eqUOM, String dialogClass){
		clickLookup(dialogClass, "eqUom");
		setValueGridLookup(eqUOM);
		Reporter.log("Set Equivalency UOM - "+eqUOM, true);
	}
	
	public String getEquivalencyUOM(String dialogId) {	
			return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+dialogId+"')]//input[@name='eqUom']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setCommodityBaseUOM(String baseUOM, String dialogClass){
		clickLookup(dialogClass, "eqBaseUom");
		setValueGridLookup(baseUOM);
		Reporter.log("Set Commodity Base UOM - "+baseUOM, true);
	}
	
	public String getCommodityBaseUOM(String dialogId) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+dialogId+"')]//input[@name='eqBaseUom']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public boolean checkEqUOMFieldIsReadOnly(){	
		try {
			driver.findElement(By.xpath("//div[contains(@class, '"+DIALOG_CMDT_EQUIVALENCY+"')]//input[@name='eqUom']//..//input[contains(@class, 'readonly')]"));
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public boolean checkBaseUOMFieldIsReadOnly(){	
		try {
			driver.findElement(By.xpath("//div[contains(@class, '"+DIALOG_CMDT_EQUIVALENCY+"')]//input[@name='eqBaseUom']//..//input[contains(@class, 'readonly')]"));
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	//TODO: Move to Framework
	public boolean filterGridAndSearchForValue(String attribute, String attributeValue, String rowTextName, String columnName) {
		
		String columnClass = McsElement
		.getElementByPartAttributeValueAndParentElement(driver,
				"div", attribute, attributeValue,
				"div","@class", "x-grid3-hd",
				"text()", columnName, true, true).getAttribute("class");
		
		String columnNumber = columnClass.substring(columnClass.length() - 1);
		
			WebElement filterInput = McsElement
					.getElementByPartAttributeValueAndParentElement(driver,
							"div", attribute, attributeValue, "input",
							"@id", "filter-editor-"+columnNumber, true, true);
			
			filterInput.clear();
			
			filterInput.sendKeys(rowTextName);
			
			McsElement
			.getElementByPartAttributeValueAndParentElement(driver,
					"div", attribute, attributeValue, "div",
					"@class", "x-grid3-body", true, true).click();			
			waitForExtJSAjaxComplete(20);

			try {
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//div[contains("+attribute+",'"+attributeValue+"')]"
						+ "//div[contains(@class, 'x-grid3-cell-inner x-grid3-col-"+columnNumber+"') and text()='"+rowTextName+"']")).click();;
				return true;
			}
			catch(Exception e){
				return false;
			}
			 finally {
					try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);
					} catch (Exception e) {}
				}

	}
	
	public void openEquivalencyLookup() {
		clickLookup(DIALOG_CMDT_EQUIVALENCY, "eqUom");
		waitForExtJSAjaxComplete(20);
	}
	
	public void openCommodityBaseLookup() {
		clickLookup(DIALOG_CMDT_EQUIVALENCY, "eqBaseUom");
		waitForExtJSAjaxComplete(20);
	}

	public void clickCancelXwindow() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-window-footer x-window-footer-noborder x-panel-btns", "button",
				"text()", "Cancel", true, true).click();
		waitForElementDisappear("//div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]");
		Reporter.log("Click Cancel", true);
	}
	
	//////////////////////////////CommodityFormValidation////////////////////////
	
	 public void clearField(String fieldNameTag, String fieldName) {
		WebElement field =  driver.findElement
				(By.xpath("(//div[contains(@class, '"+DIALOG_COMMODITY+"')]//input[contains(@name, '"+fieldNameTag+"')]//..//input)[last()]"));
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
		
	public boolean checkFormExists(String dialog){
		try {
			driver.findElement(
					By.xpath("//div[contains(@class,'"+dialog+"')]//span[contains(@class,'window-header')]")).getText().contains("Add Commodity");
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
		
	//Reopen Meter Form being either saved or not
	public boolean reopenCommodityForm(){
		
			 if (checkFormExists(DIALOG_COMMODITY)) {
				 
				 close(DIALOG_COMMODITY);
				 
				 waitForExtJSAjaxComplete(20);
				 
				 clickOnDialogButton("Yes");
			     
			     waitForExtJSAjaxComplete(20);
			     
			     clickButton(COMMODITY_GRID, "Add");
			     
			     waitForExtJSAjaxComplete(20);
			     
				 return false;
			 }
			 else {
				 
				 waitForExtJSAjaxComplete(20);
			     
				 clickButton(COMMODITY_GRID, "Add");
			     
			     waitForExtJSAjaxComplete(20);
			     
				 return true;
			 }
	 } 

}
