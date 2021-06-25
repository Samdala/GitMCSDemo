package test.energy.occurrencetypes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class ConfigurationOccurrenceTypesPageObject extends AbstractMcsTestSuite {

	protected final String XPATH_ADD_FORM_WINDOW = "energy properties";

	protected final String XPATH_ADD_FORM_WINDOW_FOOTER = "x-panel-footer x-panel-footer-noborder x-panel-btns";
	
	protected final String PANEL_NAME = "Occurrence Types";

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
	
	public String getOccurrenceTypeClass() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'" + XPATH_ADD_FORM_WINDOW + "')]//input[@name='occurrenceTypeClass']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setOccurrenceTypeClass(String ddItem){
		/*McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"occurrenceTypeClass", true, true).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		McsElement.getElementByPartAttributeValue(driver, "div", "@class",
				"x-combo-list-item", "text()", ddItem, true, true).click();
		Reporter.log("set class " + ddItem, true);*/
		String elementId = driver.findElement(By
				.xpath("(//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='occurrenceTypeClass'])[last()]")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, ddItem);
		Reporter.log("Set OccurrenceTypeClass - "+ddItem, true);
	}

	public void close() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW, "button", "text()",
				"Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Close Clicked", true);
	}
	
	public void save() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW, "button", "text()",
				"Save", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Clicked", true);
	}
	
	public void saveClose() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW, "button", "text()",
				"Save and Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close Clicked", true);
	}

}
