package test.energy.measuretypes;

//import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class ConfigurationMeasureTypesPageObject extends AbstractMcsTestSuite {

	protected final String ADMINISTRATION_PANEL_CLASS = "x-panel-body x-panel-body-noborder";

	protected final String XPATH_ADD_FORM_WINDOW = "energy properties";

	protected final String XPATH_ADD_FORM_WINDOW_FOOTER = "x-panel-footer x-panel-footer-noborder x-panel-btns";
	
	protected final String PANEL_NAME = "Measure Types";
	
	protected final String ADD_MEASURE_TYPE_WINDOW_HEADER = "Add Measure Type";
	
	protected final String EDIT_MEASURE_TYPE_WINDOW_HEADER = "Edit Measure Type";

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
		Reporter.log("open configuration " + entity, true);
	}

	public void clickAddButton() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//span[text()='"+ PANEL_NAME +"']//..//..//button[text()='Add']").click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click add "+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void clickEditButton() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//span[text()='"+ PANEL_NAME +"']//..//..//button[text()='Edit']").click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click edit "+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void clickDeleteButton() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//span[text()='"+ PANEL_NAME +"']//..//..//button[text()='Delete']").click();
		waitForExtJSAjaxComplete(10);
		clickOnDialogButton("OK");
		Reporter.log("Click delete "+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void setCode(String code) {	
		WebElement reference = McsElement.getElementByXpath(driver, "//div[contains(@class, '" + XPATH_ADD_FORM_WINDOW + "')]//label[contains(text(),'Code')]//..//input");
		reference.clear();
		reference.sendKeys(code);
		Reporter.log("Set code", true);
	}

	public String getCode() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class, '" + XPATH_ADD_FORM_WINDOW + "')]//input[@name='code']//..//input)[last()]"))
				.getAttribute("value");
	}

	public String getReference() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class, '" + XPATH_ADD_FORM_WINDOW + "')]//input[@name='reference']//..//input)[last()]"))
				.getAttribute("value");
	}

	public void setReference(String reference) {
		WebElement referenceElement = McsElement.getElementByXpath(driver, "//div[contains(@class, '" + XPATH_ADD_FORM_WINDOW + "')]//label[contains(text(),'Reference')]//..//input");
		referenceElement.clear();
		referenceElement.sendKeys(reference);
		Reporter.log("Set reference", true);
	}

	public void checkDefault() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"isDefault", true, true).click();
		//clickOnDialogButton("Yes");
		Reporter.log("set default yes", true);
	}

	public void uncheckDefault() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"isDefault", true, true).click();
		Reporter.log("uncheck default", true);
	}

	public boolean getDefaultState() {
		if (McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"isDefault", true, true).getAttribute("checked") != null) {
			return true;
		}
		return false;
	}
	
	public void saveClose() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW, "button", "text()",
				"Save and Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save close button clicked", true);
	}

	public void close() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW, "button", "text()",
				"Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("close button clicked", true);
	}
	
	public void save() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW, "button", "text()",
				"Save", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save button clicked", true);
	}

	public String getFieldValue(String xwindowTitle, String fieldName) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@id,'"+getXWindowId(xwindowTitle)+"')]//input[@name='"+fieldName+"']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public boolean getCheckBoxState(String xwindowTitle, String name) {
		  if (McsElement.getElementByPartAttributeValueAndParentElement(driver,
		    "div", "@id", getXWindowId(xwindowTitle), "input", "@name",
		    name, true, true).getAttribute("checked") != null) {
		   return true;
		  }
		  return false;
	}
	
	/**
	 * Helper method to set value for Default in Grid
	 */
	public int verifyOnlyOneRowIsDefauted() {
		List<WebElement> values = driver.findElements(By.xpath("//div[contains(@class, 'energy x-panel-noborder') and not (contains(@class, 'x-hide-display'))]//div[@class='x-grid3-body']//td[contains(@class, 'x-grid3-check-col-td')]//div/div"));
		
		int count= 0;
		
		for(int i=0; i<values.size(); i++){
		String valueText = values.get(i).getAttribute("class");
		
			if (valueText.contains("x-grid3-check-col-on")) {
				count++;
			}
		
		}
		
		waitForExtJSAjaxComplete(25);
		return count;
	}
}
