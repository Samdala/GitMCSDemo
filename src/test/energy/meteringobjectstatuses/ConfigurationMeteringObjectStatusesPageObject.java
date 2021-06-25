package test.energy.meteringobjectstatuses;

//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class ConfigurationMeteringObjectStatusesPageObject extends AbstractMcsTestSuite {

	protected final String ADMINISTRATION_PANEL_CLASS = "x-panel-body x-panel-body-noborder";

	protected final String XPATH_ADD_FORM_WINDOW = "slnmMeterStatusId";

	protected final String XPATH_ADD_FORM_WINDOW_FOOTER = "slnmMeterStatusId";

	public void goToDefinition(String definition) {
		driver.get(configuration.getApplicationUrl() + "/frame.php?relay="
				+ definition);
	};

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

	public void clickAddButton(String gridId) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridId, "button", "@class", "x-btn-text",
				"text()", "Add", true, true).click();
		Reporter.log("click add button", true);
	}

	public void clickEditButton(String gridId) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridId, "button", "@class", "x-btn-text",
				"text()", "Edit", true, true).click();
		Reporter.log("click edit button", true);
	}

	public void clickDeleteButton(String gridId) {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridId, "button", "@class", "x-btn-text",
				"text()", "Delete", true, true).click();
		clickOnDialogButton("OK");
		Reporter.log("Click delete button" + " (" + timer.stop() + "ms)", true);
	}

	public void setCode(String code) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"code", true, true).clear();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"code", true, true).sendKeys(code);
		Reporter.log("set code " + code, true);
	}

	public String getCode() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "code", true, true).getAttribute("value");
	}

	public String getReference() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "reference", true, true).getAttribute("value");
	}

	public void setReference(String reference) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"reference", true, true).clear();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"reference", true, true).sendKeys(reference);
		Reporter.log("set reference " + reference, true);
	}

	public String getDescription() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "textarea",
				"@name", "description", true, true).getAttribute("value");
	}

	public void setDescription(String reference) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "textarea", "@name",
				"description", true, true).clear();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "textarea", "@name",
				"description", true, true).sendKeys(reference);
		Reporter.log("set description " + reference, true);
	}

	public void setClassValueTrunk(String className) {
		clickLookup(XPATH_ADD_FORM_WINDOW, "class");
		String lookupId = getXWindowId("Select a Metering Object Status Class");
		setValueGridLookupWithFilters("@id", lookupId, className, "Reference");
		Reporter.log("set class " + className, true);
	}
	
	public void setClassValueJavascript(String className) {
		String id = McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "class", true, true).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, className);
		Reporter.log("set class " + className, true);
	}

	public void setClassValue(String className) {
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
				"x-combo-list-item", "text()", className, true, true).click();
		Reporter.log("set class " + className, true);
	}

	public String getClassValue() {
		 /*McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "class", true, true).getAttribute("value");*/
		
		String XPATH_NON_TRUNK = "//div[contains(@class, 'slnmMeterStatusId')]//input[@name='class']";
		WebElement element = driver.findElement(By.xpath(XPATH_NON_TRUNK));
		String value = element.getAttribute("value");
		return value;
	}
	
	public String getClassValueTrunk() {
		String XPATH_TRUNK = "//div[contains(@class, 'slnmMeterStatusId')]//input[@name='class']//following-sibling::input";
		
		WebElement element = driver.findElement(By.xpath(XPATH_TRUNK));
		String value = element.getAttribute("value");
		return value;
	}

	public void checkDefault() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"isDefault", true, true).click();
//		clickOnDialogButton("Yes");
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
				"isDefault", false, false).getAttribute("checked") != null) {
			return true;
		}
		return false;
	}

	public void saveClose() {
		waitForExtJSAjaxComplete(25);
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW_FOOTER, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close Clicked", true);
	}

	public void close() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW_FOOTER, "button", "text()",
				"Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Close Clicked", true);
	}
}
