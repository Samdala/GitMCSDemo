package test.energy.collectors;

//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;








import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class MeteringCollectorsPageObject extends AbstractMcsTestSuite {


	protected final String ADD_COLLECTORS_FORM_CLASS = "slnmCollectors";

	protected final String ADD_COLLECTORS_FOOTER_CLASS = "x-window-footer x-panel-btns";
	
	protected final String COLLECTORS_GRID_CLASS = "x-panel x-panel-noborder x-grid-panel";
	
	public void clickScopeTab()
	{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"ul", "@class", "x-tab-strip", "span", "text()", "Scope", true, true).click();
		Reporter.log("Click on Scope tab", true);
	}


	public void expandMetering() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"span", "text()", "Metering", true, true).click();
		Reporter.log("Expand Metering", true);
	}

	public void openAnalysisEntity(String entity) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"button", "text()", entity, true, true).click();
		Reporter.log("open analysis " + entity, true);
	}

	public void clickAddButton(String gridClass) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Add", true, true).click();
		Reporter.log("click add button", true);
	}

	public void setCommodityClass(String commodityClass) {
		String elementId = driver.findElement(By
				.xpath("//input[@name='commodityClass']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, commodityClass);
		Reporter.log("Set Commodity Class - "+commodityClass, true);
	}
	
	public void setEffectiveDate(String effectiveDate) {
		WebElement field = driver.findElement(By
				.xpath("//input[@name='effective-date']"));
		field.click();
		field.clear();
		field.sendKeys(effectiveDate);
		field.sendKeys(Keys.ENTER);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Set Effective Date - "+effectiveDate, true);
	}
	
	public void clickEditButton(String gridClass) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Edit", true, true).click();
		Reporter.log("click edit button", true);
	}

	public void clickDeleteButton(String gridClass) {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Delete", true, true).click();
		//clickOnDialogButton("Yes");
		clickOnDialogButton("OK");
		
		Reporter.log("Delete selected WEATHERSTATIONS measure" + " (" + timer.stop() + "ms)", true);
	}
	
	public String getReference() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_COLLECTORS_FORM_CLASS, "input",
				"@name", "reference", true, true).getAttribute("value");
	}

	public void setReference(String reference) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_COLLECTORS_FORM_CLASS, "input", "@name",
				"reference", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(reference);
		Reporter.log("set reference " + reference, true);
	}
	
	public String getCode() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_COLLECTORS_FORM_CLASS, "input",
				"@name", "code", true, true).getAttribute("value");
	}
	
	public void setCode(String code) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_COLLECTORS_FORM_CLASS, "input", "@name",
				"code", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(code);
		Reporter.log("set reference " + code, true);
	}
	
	public void saveClose() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_COLLECTORS_FOOTER_CLASS, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close Clicked", true);
	}
	
	public void save() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_COLLECTORS_FOOTER_CLASS, "button",
				"text()", "Save", true, true).click();
		waitForExtJSAjaxComplete(25);
	}

	public void close() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", ADD_COLLECTORS_FOOTER_CLASS, "button", "text()",
				"Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Close Clicked", true);
	}
	
	public void changeTab(String tabName) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"li", "@id", tabName+"-tab", "span", "@class", "x-tab-strip-text", true, true).click();
		Reporter.log("change tab to "+tabName, true);
	}
	
	public boolean isRowInGridChanelPresent(String gridValue) {
		try {
			McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-panel x-panel-noborder x-grid-panel')]//div[text()='"+gridValue+"']");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickRowInGrid(String gridValue) {
		WebElement gridElement = McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-panel x-panel-noborder x-grid-panel')]//div[text()='"+gridValue+"']");
		gridElement.click();
	}
	
	public void expandNavigator() {
		if (McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-panel-body x-panel-body-noborder')]"
				+ "//span[contains(text(), 'Navigator')]//..//..").getAttribute("class").contains("collapsed")) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu", 
				"span", "text()", "Navigator", true, true).click();
		Reporter.log("Expand Navigation", true);
		}
	}
	
	public void clickSystemViewTab(){
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-tab-panel x-tab-panel-noborder",
				"span", "text()", "Meter Infrastructure", true, true).click();
		Reporter.log("Click on Meter Infrastructure tab", true);
	}

	public void clickOnMeter(String meterCode) {
		/*McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@id", "hierarchy",
				"*", "text()", meterCode, true, true).click();*/
		WebElement element = driver.findElement(By.xpath("//div[contains(@id, 'hierarchy')]//*[contains(text(), '"+meterCode+"')]"));
		
		if ("chrome".equals(configuration.getBrowser())) {
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", element);
                element.click();
            } catch (Exception e) {
            	System.out.println("Exception is G " +e);
            }
        }
		
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		waitForExtJSAjaxComplete(20);
		Reporter.log("Clicked on Meter", true);
		//driver.findElement(By.xpath("//div[contains(@id,'hierarchy')]//*[text()='" + meterCode + "']")).click();
	}
	
	public void clickCreateMeter() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-menu x-menu-floating",
				"span", "text()", "Create Meter", true, true).click();
	}
	
	public void clickCreateCollector() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-menu x-menu-floating",
				"span", "text()", "Create Collector", true, true).click();
	}
	
	public void clickEdidCollector() {
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-menu x-menu-floating')  and not(contains(@class,'hide'))]//span[contains(text(),'View Properties')]").click();
//		McsElement.getElementByPartAttributeValueAndParentElement(driver,
//				"div", "@class", "x-menu x-menu-floating",
//				"span", "text()", "View Properties", true, true).click();
	}
	
	public void clickAddExistingMeter(String meterRef) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-menu x-menu-floating",
				"span", "text()", "Add existing Meter", true, true).click();
		setValueGridLookup(meterRef);
	}

}
