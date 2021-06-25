package test.energy.energyobjecttype;

//import java.util.concurrent.TimeUnit;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.McsElement;


public class ConfigurationEnergyObjectTypesPageObject extends AbstractMcsTestSuite {

	protected final String ADMINISTRATION_PANEL_CLASS = "x-panel-body x-panel-body-noborder";

	protected final String XPATH_ADD_FORM_WINDOW = "slnmEnergyObjectTypeId";

	protected final String XPATH_ADD_FORM_WINDOW_FOOTER = "x-panel-footer x-panel-footer-noborder x-panel-btns";
	
	protected final String PANEL_NAME = "Energy Object Types";

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

	/**
	 * To get the Column number of Default in Grid
	 * 
	 * @param attributeValue
	 * @param colName
	 * @return
	 */
	public int getColumnNumInGrid(String attributeValue, String colName) {
		WebElement element = driver
				.findElement(By
						.xpath("//div[contains(@realid, '"
								+ attributeValue
								+ "')]//div[contains(@class,'quickfilters')]//div[contains(@class,'x-grid3-hd') and contains(text(), '"
								+ colName + "')]"));

		String columnClass = element.getAttribute("class");

		String columnNumber = columnClass.substring(columnClass
				.lastIndexOf("-") + 1);
		return Integer.parseInt(columnNumber);
	}

	/**
	 * To get the count of Rows with Default checked
	 * 
	 * @param attributeValue
	 * @param colName
	 * @return
	 */
	public int getNoOfDefaultEnergyObjectTypes(String attributeValue,
			String colName) {

		int colNo = getColumnNumInGrid(attributeValue, colName);

		List<WebElement> values = driver
				.findElements(By
						.xpath("//div[contains(@realid, '"
								+ attributeValue
								+ "')]//div[contains(@class,'x-grid3-body')]//div[contains(@class, 'x-grid3-cell-inner x-grid3-col-"
								+ colNo + "')]/div"));

		int count = 0;

		for (int i = 0; i < values.size(); i++) {
			String valueText = values.get(i).getAttribute("class");

			if (valueText.contains("x-grid3-check-col-on")) {
				count++;
			}

		}

		waitForExtJSAjaxComplete(25);
		return count;
	}
	
	
	public String getDescription() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class, '" + XPATH_ADD_FORM_WINDOW + "')]//textarea[@name='description']//..//textarea)[last()]"))
				.getAttribute("value");
	}

	public void setDescription(String reference) {
		WebElement referenceElement = McsElement.getElementByXpath(driver, "//div[contains(@class, '" + XPATH_ADD_FORM_WINDOW + "')]//label[contains(text(),'Description')]//..//textarea");
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
	
	public void setTemperatureUOM(String tempUOM){
		clickLookup(XPATH_ADD_FORM_WINDOW, "tempUom");
		setValueGridLookup("@id",getXWindowId("Select a Unit Of Measure"),tempUOM);
	}
	
	public String getTemperatureUOM() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'" + XPATH_ADD_FORM_WINDOW + "')]//input[@name='tempUom']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setCoolingBaseTemperature(String proposerName){
		clickLookup(XPATH_ADD_FORM_WINDOW, "coolingBaseTemp");
		setValueGridLookup("@id",getXWindowId("Select a Base Temperature"),proposerName);
	}
	
	public String getCoolingBaseTemperature() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'" + XPATH_ADD_FORM_WINDOW + "')]//input[@name='coolingBaseTemp']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setHeatingBaseTemperature(String proposerName){
		clickLookup(XPATH_ADD_FORM_WINDOW, "heatingBaseTemp");
		setValueGridLookup("@id",getXWindowId("Select a Base Temperature"),proposerName);
	}
	
	public String getHeatingBaseTemperature() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'" + XPATH_ADD_FORM_WINDOW + "')]//input[@name='heatingBaseTemp']//..//input)[last()]"))
				.getAttribute("value");
	}

	public void saveClose() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW, "button", "text()",
				"Save and Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close Clicked", true);
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
	
}
