package test.energy.energyobjectstatuses;

//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class ConfigurationEnergyObjectStatusesPageObject extends AbstractMcsTestSuite {

	protected final String ADMINISTRATION_PANEL_CLASS = "x-panel-body x-panel-body-noborder";

	protected final String XPATH_ADD_FORM_WINDOW = "slnmEnergyObjectStatusId";

	protected final String XPATH_ADD_FORM_WINDOW_FOOTER = "x-panel-footer x-panel-footer-noborder x-panel-btns";
	
	protected final String PANEL_NAME = "Energy Object Statuses";

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
	
	public void setObjectClass(String ddItem){
		/*String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='class']")).getAttribute("Id");
		DropDown.setComboValue(driver, elementId, ddItem);*/
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"class", true, true).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		McsElement.getElementByPartAttributeValue(driver, "div", "@class",
				"x-combo-list-item", "text()", ddItem, true, true).click();
		Reporter.log("set class " + ddItem, true);
	}
	
	public String getObjectClass() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'" + XPATH_ADD_FORM_WINDOW + "')]//input[@name='class']//..//input)[last()]"))
				.getAttribute("value");
	}
	

	public void saveClose() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW, "button", "text()",
				"Save and Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close clicked", true);
	}

	public void close() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW, "button", "text()",
				"Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Close clicked", true);
	}
	
	public void save() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW, "button", "text()",
				"Save", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save clicked", true);
	}

}
