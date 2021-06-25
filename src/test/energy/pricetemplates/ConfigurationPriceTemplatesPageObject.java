package test.energy.pricetemplates;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class ConfigurationPriceTemplatesPageObject extends AbstractMcsTestSuite {

	protected final String XPATH_ADD_FORM_WINDOW = "slnmNrgPrcTplId";

	protected final String XPATH_ADD_FORM_WINDOW_FOOTER = "x-panel-footer x-panel-footer-noborder x-panel-btns";
	
	protected final String PANEL_NAME = "Energy Price Templates";

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
	
	public String getRegion() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class, '" + XPATH_ADD_FORM_WINDOW + "')]//input[@name='region']//..//input)[last()]"))
				.getAttribute("value");
	}

	public void setRegion(String region) {
		WebElement regionElement = McsElement.getElementByXpath(driver, "//div[contains(@class, '" + XPATH_ADD_FORM_WINDOW + "')]//label[contains(text(),'Region')]//..//input");
		regionElement.clear();
		regionElement.sendKeys(region);
		Reporter.log("Set region", true);
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
	
	public void setCountry(String countryName) {
		clickLookup(XPATH_ADD_FORM_WINDOW, "country");
		setValueGridLookup(countryName);
		Reporter.log("Set countryName - "+countryName, true);
	}
	
	public void setCurrency(String currence) {
		String elementId = driver.findElement(By
				.xpath("//input[@name='currency']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, currence);
		Reporter.log("Set currence - "+currence, true);
	}
	
	public void setStatus(String status) {
		String elementId = driver.findElement(By
				.xpath("//input[@name='status']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, status);
		Reporter.log("Set status - "+status, true);
	}
	
	public void setCommodityClass(String commodityClass) {
		String elementId = driver.findElement(By
				.xpath("//input[@name='commodityClass']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, commodityClass);
		Reporter.log("Set commodityClass - "+commodityClass, true);
	}
	
	public void setCommodityUOM(String commodityUom) {
		clickLookup(XPATH_ADD_FORM_WINDOW, "commodityUom");
		setValueGridLookup(commodityUom);
		Reporter.log("Set commodityUom - "+commodityUom, true);
	}
	
	public void setTaxCode1(String taxCode1) {
		clickLookup(XPATH_ADD_FORM_WINDOW, "taxCode1");
		setValueGridLookup(taxCode1);
		Reporter.log("Set taxCode1 - "+taxCode1, true);
	}
	
	public void setTaxCode2(String taxCode2) {
		clickLookup(XPATH_ADD_FORM_WINDOW, "taxCode2");
		setValueGridLookup(taxCode2);
		Reporter.log("Set taxCode2 - "+taxCode2, true);
	}
	
	public String getFieldValue(String fieldName) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='"+fieldName+"']//..//input)[last()]"))
				.getAttribute("value");
	}

}
