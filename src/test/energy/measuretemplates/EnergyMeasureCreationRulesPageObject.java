package test.energy.measuretemplates;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import test.configuration.Configuration;
import test.energy.EnergyBaseTestSuite;
import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class EnergyMeasureCreationRulesPageObject extends EnergyBaseTestSuite {
	
	protected final String XPATH_GRID_CLASS = "x-panel x-panel-noborder x-grid-panel";

	protected final String XPATH_ADD_FORM_WINDOW = "slnmEnergyMeasureCrtnRls";
	
	protected final String XPATH_CRITERION_DLG = "energy properties";
	
	protected final String EDIT_CRITERION_WINDOW_HEADER = "Edit Criterion";
	
	protected final String ADD_CRITERION_WINDOW_HEADER = "Add Criterion";
	
	protected final String ADD_MEASURE_CREATION_WINDOW_HEADER = "Add Measure Creation Rule";
	
	protected final String EDIT_MEASURE_CREATION_WINDOW_HEADER = "Edit Measure Creation Rule"; 
	
	
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
		Reporter.log("Open Configuration " + entity, true);
	}

	public void clickAddButton(String gridId) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridId, "button", "@class", "x-btn-text",
				"text()", "Add", true, true).click();
		Reporter.log("Click Add button", true);
	}
	
	public void clickAddButtonOnRulesTab() {
		driver.findElement(By
				.xpath("(//div[contains(@class, '"+XPATH_GRID_CLASS+"')]//button[contains(@class, 'x-btn-text') and text()='Add'])[last()]")).click();
		Reporter.log("Click Add button", true);
	}
	
	public void clickAddButtonOnRulesDialog() {
		driver.findElement(By
				.xpath("(//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//button[contains(@class, 'x-btn-text') and text()='Add'])[last()]")).click();
		Reporter.log("Click Add button", true);
	}

	public void clickEditButton(String gridId) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridId, "button", "@class", "x-btn-text",
				"text()", "Edit", true, true).click();
		Reporter.log("Click Edit button", true);
	}
	
	public void clickEditButtonOnRulesTab() {
		driver.findElement(By
				.xpath("(//div[contains(@class, '"+XPATH_GRID_CLASS+"')]//button[contains(@class, 'x-btn-text') and text()='Edit'])[last()]")).click();
		Reporter.log("Click Edit button", true);
	}
	
	public void clickEditButtonOnRulesDialog() {
		driver.findElement(By
				.xpath("(//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//button[contains(@class, 'x-btn-text') and text()='Edit'])[last()]")).click();
		Reporter.log("Click Edit button", true);
	}

	public void clickDeleteButton(String gridId) {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridId, "button", "@class", "x-btn-text",
				"text()", "Delete", true, true).click();
		clickOnDialogButton("OK");
		Reporter.log("Click Delete button" + " (" + timer.stop() + "ms)", true);
	}
	
	public void clickDeleteButtonOnRulesTab() {
		driver.findElement(By
				.xpath("(//div[contains(@class, '"+XPATH_GRID_CLASS+"')]//button[contains(@class, 'x-btn-text') and text()='Delete'])[last()]")).click();
		clickOnDialogButton("OK");
		Reporter.log("Click Delete button", true);
	}
	
	public void clickDeleteButtonOnRulesDialog() {
		driver.findElement(By
				.xpath("(//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//button[contains(@class, 'x-btn-text') and text()='Delete'])[last()]")).click();
		Reporter.log("Click Delete button", true);
	}
	
	public void clickSaveButtonOnRulesDialog(String windowTitle) {
		WebElement save = McsElement.getElementByXpath(driver, "(//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//button[contains(@class, 'x-btn-text') and text()='Save'])");
		javaScriptClick(save);
		waitForExtJSAjaxComplete(10);
		
		Reporter.log("Click Save button", true);
	}
	
	public void clickXButtonOnRulesDialog() {
		driver.findElement(By
				.xpath("(//div[contains(@class, '"+XPATH_CRITERION_DLG+"')]//div[contains(@class, 'x-tool-close')])[last()]")).click();
		Reporter.log("Click Close button", true);
	}

	public void setReference(String reference) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"reference", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(reference);
		referenceField.sendKeys(Keys.ENTER);
		Reporter.log("Set Reference - " + reference, true);
	}

	public String getReference() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "reference", true, true).getAttribute("value");
	}
	
	public void setCode(String code) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"code", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(code);
		referenceField.sendKeys(Keys.ENTER);
		Reporter.log("Set Code - " + code, true);
	}
	
	public String getCode() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "code", true, true).getAttribute("value");
	}
	
	public void setAppliesTo(String appliesTo) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='objectType']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, appliesTo);
		Reporter.log("Set Applies To - "+appliesTo, true);
	}
	
	public String getAppliesTo(String attributeName) {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "objectType", false, false).getAttribute(attributeName);
	}
	
	public void setMaintenanceObjectCategory(String maintenanceObjectCategory) {
		clickLookup(XPATH_ADD_FORM_WINDOW, "maintenanceObjectCategory");
		setValueGridLookup(maintenanceObjectCategory);
		Reporter.log("Set Maintenance Object Category - "+maintenanceObjectCategory, true);
	}
	
	public String getMaintenanceObjectCategory(String attributeName) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='maintenanceObjectCategory']//..//input)[last()]"))
				.getAttribute(attributeName);
	}
	
	
	public void setMaintenanceObjectPartType(String maintenanceObjectCategory) {
		clickLookup(XPATH_ADD_FORM_WINDOW, "maintenanceObjectPartType");
		setValueGridLookup(maintenanceObjectCategory);
		Reporter.log("Set Maintenance Object Part Type - "+maintenanceObjectCategory, true);
	}
	
	public String getMaintenanceObjectPartType() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='maintenanceObjectPartType']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setUseMeasureTemplate(String measureTemplate) {
		clickLookup(XPATH_ADD_FORM_WINDOW, "measureTemplate");
		setValueGridLookup(measureTemplate);
		Reporter.log("Set Use Measure Template - "+measureTemplate, true);
	}
	
	public String getUseMeasureTemplate() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='measureTemplate']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void clickMeasureCreationRulesTab()
	{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"ul", "@class", "x-tab-strip", "span", "text()", "Measure Creation Rules", true, true).click();
		Reporter.log("Click on Measure Creation Rules tab", true);
	}
	
	public void saveClose() {
		waitForExtJSAjaxComplete(25);
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close Clicked", true);
	}
	
	public void save() {
		waitForExtJSAjaxComplete(25);
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "button",
				"text()", "Save", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Clicked", true);
	}

	public void close() {
		/*McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW, "button", "text()",
				"Close", true, true).click();*/
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//button[text()='Close']"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		}catch(Exception e){
			e.printStackTrace();
		}
		waitForExtJSAjaxComplete(25);
		Reporter.log("Close Clicked", true);
	}
	
	public void closeUsingWindowTitle(String windowTitle) {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@id", getXWindowId(windowTitle), "button", "text()",
				"Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Close window title Clicked", true);
	}
	
	public void setFieldType(String fieldType) {
		String elementId = driver.findElement(By
				.xpath("(//div[contains(@class,'"+XPATH_CRITERION_DLG+"')]//input[@name='fieldType'])[last()]")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, fieldType);
		Reporter.log("Set Field Type - "+fieldType, true);
	}
	
	public String getFieldType() {
		return driver.findElement(By
				.xpath("(//div[contains(@class,'"+XPATH_CRITERION_DLG+"')]//input[@name='fieldType'])[last()]")).getAttribute("value");
	}
	
	public void setCriterionName(String criterionName) {
		String elementId = driver.findElement(By
				.xpath("(//div[contains(@class,'"+XPATH_CRITERION_DLG+"')]//input[@name='field'])[last()]")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, criterionName);
		Reporter.log("Set Criterion Name - "+criterionName, true);
	}
	
	public String getCriterionName() {
		return driver.findElement(By
				.xpath("(//div[contains(@class,'"+XPATH_CRITERION_DLG+"')]//input[@name='field'])[last()]")).getAttribute("value");
	}
	
	public void setOperator(String operator) {
		String elementId = driver.findElement(By
				.xpath("(//div[contains(@class,'"+XPATH_CRITERION_DLG+"')]//input[@name='operator'])[last()]")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, operator);
		Reporter.log("Set Criterion Operator - "+operator, true);
	}
	
	public String getOperator() {
		return driver.findElement(By
				.xpath("(//div[contains(@class,'"+XPATH_CRITERION_DLG+"')]//input[@name='operator'])[last()]")).getAttribute("value");
	}
	
	public void setValue1(String value) {
		WebElement referenceField = driver.findElement(By
				.xpath("(//div[contains(@class,'"+XPATH_CRITERION_DLG+"')]//input[@name='operand1'])[last()]"));
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(value);
		referenceField.sendKeys(Keys.ENTER);
		waitForExtJSAjaxComplete(10);
		Reporter.log("Set Criterion Value 1 - " + value, true);
	}

	public String getValue1() {
		return driver.findElement(By
				.xpath("(//div[contains(@class,'"+XPATH_CRITERION_DLG+"')]//input[@name='operand1'])[last()]")).getAttribute("value");
	}
	
	public void setValue2(String value) {
		WebElement referenceField = driver.findElement(By
				.xpath("(//div[contains(@class,'"+XPATH_CRITERION_DLG+"')]//input[@name='operand2'])[last()]"));
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(value);
		Reporter.log("Set Criterion Value 2 - " + value, true);
	}

	public String getValue2() {
		return driver.findElement(By
				.xpath("(//div[contains(@class,'"+XPATH_CRITERION_DLG+"')]//input[@name='operand2'])[last()]")).getAttribute("value");
	}
	
	public String getXPanelId(String xpanelTitle) throws NoSuchElementException {

		String elementXpath = "(//div[contains(@class, 'x-resizable-pinned')]//span[text()='"
				+ xpanelTitle + "']/../../../../..)[last()]";

		WebElement webElement = new WebDriverWait(driver, configuration.getImplicitWait())
				.until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath(elementXpath)));
		
		return webElement.getAttribute("id");
	}

	 public String getFieldValue(String fieldName) {
			return driver
					.findElement(
							By.xpath("(//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='"+fieldName+"']//..//input)[last()]"))
					.getAttribute("value");
		}
	 
	 public boolean isElementOnForm(String elementName) {
			try {
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='" + elementName + "']"));
				return true;
			} catch (Exception e) {
				return false;
			} finally {
				try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
			}
			
		}
	 
	 public boolean checkInputDisabled(String elementName) {
			
			return McsElement.checkInputDisabled(driver, "@class", XPATH_ADD_FORM_WINDOW, elementName);	
		
	}
	 
	 public boolean checkMandatoryFieldSave(String dialog){
			return driver.findElement(
						By.xpath("//div[contains(@class,'"+dialog+"')]//span[contains(@class,'window-header')]")).getText().contains("Add");
			
		}

	 public void close(String dialogClass) {
			/*McsElement.getElementByAttributeValueAndParentElement(driver, "div",
					"@class", dialogClass, "button", "text()",
					"Close", true, true).click();*/
		 WebElement ele = driver.findElement(By.xpath("//div[contains(@class, '"+dialogClass+"')]//button[text()='Close']"));
			try{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
				ele.click();
			}catch(Exception e){
				e.printStackTrace();
			}
			Reporter.log("Click Close button", true);
		}

	 
	//Reopen Weather Station Form being either saved or not
		public boolean reopenForm(){
			
			if (!checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW)) {
					 
				close(XPATH_ADD_FORM_WINDOW);
					 
				waitForExtJSAjaxComplete(20);
					 
				clickAddButtonOnRulesTab();
						
				waitForExtJSAjaxComplete(20);

				return false;
			}
			 else 
			{
					 
				close();
					 
				waitForExtJSAjaxComplete(20);
					 
				clickOnDialogButton("Yes");
					 
				//catch (Exception e) {}
					 
				waitForExtJSAjaxComplete(20);
				     
				clickAddButtonOnRulesTab();
						
				waitForExtJSAjaxComplete(20);
				
				waitForExtJSAjaxComplete(20);
				
				waitForExtJSAjaxComplete(20);
					 
				return true;
					 	 
			}
		}
		
	public boolean verifyItemExists(String inputName, String ddItem) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'" + XPATH_ADD_FORM_WINDOW + "')]//input[@name='" + inputName + "']")).getAttribute("Id");
		return DropDown.isExtJsComboValuePresent(driver, elementId, ddItem);	
	}
	
	public boolean verifyItemExistsOnCriterion(String inputName, String ddItem) {
		String elementId = driver.findElement(By
				.xpath("(//div[contains(@class,'" + XPATH_CRITERION_DLG + "')]//input[@name='" + inputName + "'])[last()]")).getAttribute("Id");
		return DropDown.isExtJsComboValuePresent(driver, elementId, ddItem);	
	}
	
	
	public boolean checkInvalidRedBorderInputField(String inputName) {
		String elementClass = driver.findElement(By.xpath("(//div[contains(@class,'"+ XPATH_CRITERION_DLG +"')]//label/..//input[@name='"+inputName+"']/..//input)[last()]"))
				.getAttribute("class");
		if (elementClass.contains("x-form-invalid")) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public void clearField(String fieldNameTag, String fieldName) {
		WebElement field =  driver.findElement
				(By.xpath("(//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//input[contains(@name, '"+fieldNameTag+"')]//..//input)[last()]"));
		field.click();
		field.clear();
		Reporter.log("Clear "+fieldName+" field", true);
	}
	
	/**
	 * Helper method to verify save button is disabled
	 */
	public boolean isSaveBtnDisabled(String xwindowTitle) {
		String element = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//button[contains(@class, 'x-btn-text') and text()='Save']/ancestor::table[contains(@class, 'x-item-disabled')]")).getAttribute("class");
		if(element.contains("disabled")){
			return true;
		}else {
		return false;
		}  
	}
}
