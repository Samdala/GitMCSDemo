package test.energy.abatementmeasuretypes;

//import java.util.concurrent.TimeUnit;

import org.testng.Reporter;

import test.energy.EnergyBaseTestSuite;
import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class ConfigurationAbatementMeasureTypesPageObject extends EnergyBaseTestSuite {

	protected final String ADMINISTRATION_PANEL_CLASS = "x-panel-body x-panel-body-noborder";

	protected final String XPATH_ADD_FORM_WINDOW = "x-window energy crud-form x-resizable-pinned";

	protected final String XPATH_ADD_FORM_WINDOW_FOOTER = "x-panel-footer x-panel-footer-noborder x-panel-btns";

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
		Reporter.log("Open configuration " + entity, true);
	}

	public void clickAddButton(String gridId) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@realid", gridId, "button", "@class", "x-btn-text",
				"text()", "Add", true, true).click();
		Reporter.log("click add button", true);
	}

	public void clickEditButton(String gridId) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@realid", gridId, "button", "@class", "x-btn-text",
				"text()", "Edit", true, true).click();
		Reporter.log("click edit button", true);
	}

	public void clickDeleteButton(String gridId) {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@realid", gridId, "button", "@class", "x-btn-text",
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
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "class", true, true).getAttribute("value");
	}

	public void checkDefault() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"isDefault", true, true).click();
		clickOnDialogButton("Yes");
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
		waitForExtJSAjaxComplete(25);
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW_FOOTER, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		waitForMaskDisappear();
	}

	public void close() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW_FOOTER, "button", "text()",
				"Close", true, true).click();
		waitForMaskDisappear();
	}
}
