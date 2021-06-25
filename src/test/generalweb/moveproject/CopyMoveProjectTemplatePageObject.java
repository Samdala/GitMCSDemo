package test.generalweb.moveproject;

import test.framework.AbstractMcsTestSuite;

import test.framework.webelement.McsElement;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class CopyMoveProjectTemplatePageObject extends AbstractMcsTestSuite {


	protected final String MOVEPROJECT_FORM_CLASS = "x-resizable";
	
	protected final String MOVEPROJECT_GRID_CLASS = "x-tab-panel-noborder";
	

	public void setReference(String code) {
		WebElement date = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", MOVEPROJECT_FORM_CLASS, "input", "@name",
				"reference", true, true);
		date.clear();
		date.sendKeys(code);
		Reporter.log("set reference " + code, true);
	}

	public String getReference() {
		return McsElement.getElementByXpath(driver,"//label[contains(text(), 'Reference:')]//..//div//input").getAttribute("value");
	}

	public void clickGeneralTab() {
		McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//span[text()='General']").click();
		waitForExtJSAjaxComplete(25);
	}

	

	
	public void clickAdministration() {
		McsElement.getElementByXpath(driver,"//div[contains(@class,'mcs-tb-glossy-strong')]//button[text()='Administration']").click();
		waitForExtJSAjaxComplete(25);
	}
	

	
	public void clickSubMenuItemInAdministration(String menuItem) {
		McsElement.getElementByXpath(driver,XPATH_AMINISTRATION_MODULE_SETTINGS +"//..//..//..//span[contains(text(),'" + menuItem + "')] ").click();
		waitForExtJSAjaxComplete(25);
	}
	
	public void clickCopy() {
		McsElement.getElementByXpath(driver,"//div[contains(@class,'x-small-editor')]//button[contains(@class, 'icon-template-copy')]").click();
		waitForExtJSAjaxComplete(25);
	}
	
	public void clickDelete() {
		McsElement.getElementByXpath(driver,"//div[contains(@class,'x-small-editor')]//button[contains(@class, 'icon-template-delete')]").click();
		waitForExtJSAjaxComplete(25);
	}
	
	public void setNewReferenceToCopiedMoveProject(String newReference) {
		WebElement reference = McsElement.getElementByXpath(driver,"//div[contains(@class,'x-window-dlg')]//input[contains(@class, 'ext-mb-input')]");
		reference.clear();
		reference.sendKeys(newReference);
		Reporter.log("Set reference " + newReference, true);
	}
	
	public void clickOkOnNewTemplate() {
		McsElement.getElementByXpath(driver,"//div[contains(@class,'x-window-dlg')]//button[contains(text(), 'OK')]").click();
	}

}
