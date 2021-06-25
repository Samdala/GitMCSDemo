package test.generalweb.service;



import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.McsElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class ServiceSpecificationsPageObject extends AbstractMcsTestSuite {

	protected final String ADMINISTRATION_PANEL_CLASS = "x-panel-body x-panel-body-noborder";
	
	protected final String FORM_TEMPLATE_NAME = "Service Specification - All Service";

	public void goToAdministration() {
		driver.get(configuration.getApplicationUrl()
				+ "/frame.php?relay=ADM_SETTINGS");
	};

	public void expandMasterData() {
		expandNode("div","@id",getXPanelId("Administration"),"Master Data");
		}
	
	public void clickAdministration() {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "mcs-tb-glossy-strong", "button", "text()",
				"Administration", true, true).click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click Administration"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	public void clickServiceSpecifications() {
		Timer timer = new Timer().start();
		WebElement callTemplate = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@id", getXPanelId("Administration"), "span", "text()",
				"Service Specifications", true, true);
		javaScriptFocus(callTemplate);
		callTemplate.click();
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Service Specifications"+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void clickAddServiceSpecifications() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//span[contains(text(), 'Service Specifications - All')]//..//..//..//..//..//..//..//..//button[contains(text(), 'Add')]").click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click add template"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	public void clickEditServiceSpecifications() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//span[contains(text(), 'Service Specifications - All')]//..//..//..//..//..//..//..//..//button[contains(text(), 'Edit')]").click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click edit template"+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void clickDeleteServiceSpecifications() {
		McsElement.getElementByXpath(driver, "//span[contains(text(), 'Service Specifications - All')]//..//..//..//..//..//..//..//..//button[contains(text(), 'Delete')]").click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click delete template", true);
	}

	public void setReference(String referenceText) {
		WebElement reference = McsElement.getElementByXpath(driver, "//div[@id='" + getXWindowId("Service Specification") + "']//label[contains(text(),'Reference')]//..//input");
		reference.clear();
		reference.sendKeys(referenceText);
		Reporter.log("Set reference", true);
	}
	
	public String getReference() {
		String windowId = getXWindowId("Service Specification");
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@id", windowId, "input",
				"@name", "reference", true, true).getAttribute("value");
	}
	
	public void setCode(String codeText) {
		WebElement reference = McsElement.getElementByXpath(driver, "//div[@id='" + getXWindowId("Service Specification") + "']//label[contains(text(),'Code')]//..//input");
		reference.clear();
		reference.sendKeys(codeText);
		Reporter.log("Set code", true);
	}
	
	public String getCode() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@id", getXWindowId("Service Specification"), "input",
				"@name", "code", true, true).getAttribute("value");
	}
	
	public void setNature(String nature) {

		clickLookup("@id",getXWindowId("Service Specification"), "nature","Select a Nature");
		
		waitForExtJSAjaxComplete(25);

		driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//span[contains(text(),'Tree')]")).click();

		setValueTreeLookup(new String[]{nature});
		
		Reporter.log("nature was selected", true);
		
	}
	
	public String getNature() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@id,'"+getXWindowId("Service Specification")+"')]//input[@name='nature']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void saveServiceSpecifications() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//div[@id='" + getXWindowId("Service Specification") + "']//button[contains(text(),'Save')]").click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Save Service Specifications"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	public void closeServiceSpecifications() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//div[@id='" + getXWindowId("Service Specification") + "']//div[contains(@class,'x-tool-close')]").click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Close Service Specifications"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	public void clickTab(String tabName){
		McsElement.getElementByXpath(driver, "//span[text()='" + tabName + "' and contains(@class,'tab')]").click();
		waitForExtJSAjaxComplete(25);
	}
	
}
