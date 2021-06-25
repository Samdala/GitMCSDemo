package test.generalweb.activity;

//import java.util.concurrent.TimeUnit;
import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class ActivityPageObject extends AbstractMcsTestSuite {

	protected final String XPATH_OVERVIEW_BUTTON_GENERAL = "//span[contains(@class,'x-menu-item-text') and contains(text(),'General')]";

	protected final String XPATH_OVERVIEW_BUTTON_GENERAL_ALL_WORKORDERS = "//span[contains(@class,'x-menu-item-text') and contains(text(),'Out')]";

	protected final String XPATH_OVERVIEW_BUTTON = "//button[contains(@class,'x-btn-text icon-ov-overview')]";

	protected final String ACTIVITY_WINDOW_CLASS = "x-window-mc";

	protected final String ACTIVITY_WINDOW_FOOTER_CLASS = "x-toolbar x-small-editor x-toolbar-layout-ct";

	protected final String XWINDOW_CLASS = "x-resizable-pinned";

	protected final String XWINDOW_FOOTER_CLASS = "x-window-footer x-window-footer-noborder x-panel-btns";
	
	protected final String DETAILS_GENERAL_FORM_CLASS = "x-panel-body x-panel-body-noheader x-panel-body-noborder x-form";
	
	protected final String XPATH_XWINDOW = "//div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]";

	public void goToActivity() {
		driver.get(configuration.getApplicationUrl()
				+ "/frame.php?relay=TIME_REG");
	};

	public void clickAddButton() {
		McsElement.getElementByPartAttributeValue(driver, "button", "@class",
				"x-btn-text icon-ov-add", "text()", "Add", true, true).click();
		Reporter.log("Add button was clicked", true);
	}


	public void clickEditButton() {
		McsElement.getElementByPartAttributeValue(driver, "button", "@class",
				"x-btn-text icon-ov-edit", "text()", "Edit", true, true).click();
		Reporter.log("Edit button was clicked", true);
	}

	
	
	public void clickDeleteButton() {
		McsElement.getElementByPartAttributeValue(driver, "button", "@class",
				"x-btn-text icon-ov-remove", "text()", "Delete", true, true).click();
		Reporter.log("Delete button was clicked", true);
	}

	
	
	public void setDate(String reference) {
		Timer timer = new Timer().start();
		WebElement date = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@class", ACTIVITY_WINDOW_CLASS, "input", "@id", "date", true, true);
		date.clear();
		date.sendKeys(reference);
		Reporter.log("date was set" + " (" + timer.stop()
				+ "ms)", true);
	}

	public void setStartTime(String reference) {
		Timer timer = new Timer().start();
		WebElement code = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@class", ACTIVITY_WINDOW_CLASS, "input", "@name",
						"timeStart", true, true);
		code.clear();
		code.sendKeys(reference);
		Reporter.log("start time was set" + " (" + timer.stop()
				+ "ms)", true);
	}

	public void setStopTime(String reference) {
		Timer timer = new Timer().start();
		WebElement code = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@class", ACTIVITY_WINDOW_CLASS, "input", "@name",
						"timeStop", true, true);
		code.clear();
		code.sendKeys(reference);
		Reporter.log("stop time was set"+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void setActivityStatus(String reference) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ACTIVITY_WINDOW_CLASS, "input", "@name",
				"statusCode", true, true).click();
		McsElement.getElementByPartAttributeValue(driver, "div", "@class",
				"x-combo-list", "text()", reference, true, true).click();
		Reporter.log("activity status "+ reference+" was set", true);
	}

	public void setActivityType(String reference) {
		clickLookup(ACTIVITY_WINDOW_CLASS, "type");
		setValueGridLookupWithFilters("@class", XWINDOW_CLASS, reference, "Reference");
		waitForExtJSAjaxComplete(25);
	}
	

	public String getActivityType() {
		return McsElement.getElementByXpath(driver, "(//div[contains(@class,'" + ACTIVITY_WINDOW_CLASS  + "')]//input[@name='type']//..//input)[last()]").getAttribute("value");	
		
	}

	public void setWorkForce(String lastName) {
		clickLookup(ACTIVITY_WINDOW_CLASS, "workforce");
		setValueGridLookupWithFilters("@class", XWINDOW_CLASS, lastName, "Last Name");
		waitForExtJSAjaxComplete(25);
	}

	public String getWorkForce() {
		return McsElement.getElementByXpath(driver, "(//div[contains(@class,'" + ACTIVITY_WINDOW_CLASS  + "')]//input[@name='workforce']//..//input)[last()]").getAttribute("value");	
		
	}
	
	public void setRelatedType(String reference) {
		String id = McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ACTIVITY_WINDOW_CLASS, "input",
				"@name", "relatedType", true, true).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, reference);
		Reporter.log("related type was selected", true);
	}

	public void setCall(String reference) {

		clickLookup(ACTIVITY_WINDOW_CLASS, "relCall");
		waitForExtJSAjaxComplete(20);

		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XWINDOW_CLASS, "input", "@class",
				"x-trigger-noedit", true, true).click();
		
		waitForExtJSAjaxComplete(20);
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@style", "visible", "div", "text()", "allCalls", true, true)
				.click();
		waitForExtJSAjaxComplete(20);
		setValueGridLookupWithFilters("@class", XWINDOW_CLASS, reference, "Reference");
	}

	public void setDescription(String reference) {
		WebElement code = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@class", ACTIVITY_WINDOW_CLASS, "textarea", "@name",
						"description", true, true);
		code.clear();
		code.sendKeys(reference);
		Reporter.log("description was set "+ reference, true);
	}

	public void saveAndClose() {
		 McsElement
			.getElementByPartAttributeValueAndParentElement(driver, "div",
					"@class", ACTIVITY_WINDOW_CLASS, "button", "text()",
					"Save", "text()", "Close", true, true).click();
		 Reporter.log("save and close was clicked", true);
	}

	public void cancel() {
		 McsElement
			.getElementByPartAttributeValueAndParentElement(driver, "div",
					"@class", ACTIVITY_WINDOW_CLASS, "button", "text()",
					"Cancel", true, true).click();
		 Reporter.log("cancel was clicked", true);
	}
	
	public void clickFinancialTab() {
		 McsElement.getElementByXpath(driver, "//span[contains(@class,'x-tab-strip-text') and contains(text(),'Financial Details')]").click();
		 Reporter.log("financial tab was clicked", true);
	}
	
	public void clickOverrideProduct() {
		 McsElement.getElementByXpath(driver, "//input[@name='productOverride']").click();
		 Reporter.log("checkbox override was clicked", true);
	}

	public void setProductService(String reference) {
		if (!reference.equals("")){
		clickLookup("x-window", "productId");
		McsElement.getElementByXpath(driver, "//span[contains(@class,'x-tab-strip-text') and contains(text(),'Products And Services')]").click();
		setValueGridLookupWithFilters(reference, "Reference");}
		else{
			McsElement.getElementByXpath(driver, "(//input[@name='productId']/..//input)[last()]").click();
			waitForExtJSAjaxComplete(20);
			
			McsElement.getElementByXpath(driver, "(//input[@name='productId']/..//input)[last()]").clear();
			waitForExtJSAjaxComplete(20);
			waitForExtJSAjaxComplete(20);
			McsElement.getElementByXpath(driver, "(//input[@name='productId']/..//input)[last()]").sendKeys("   ");
			waitForExtJSAjaxComplete(20);
			waitForExtJSAjaxComplete(20);
			McsElement.getElementByXpath(driver, "(//input[@name='productId']/..//input)[last()]").sendKeys("  ");
			
			McsElement.getElementByXpath(driver, "(//input[@name='productId']/..//input)[last()]").clear();
			McsElement.getElementByXpath(driver, "(//input[@name='productId']/..//input)[last()]").click();
		}
		 Reporter.log("product was set", true);
	}

	public String getUnit() {
		 return McsElement.getElementByXpath(driver, "//input[@name='quantityUnit']").getAttribute("value");
	}
	
	/**
	 * Helper method to check all the rows in a grid 
	 */
	public void gridChecker(){
		String xpath = "(//div[contains(@class,'x-grid3-hd-checker')])[last()]";
		WebElement ele = driver.findElement(By.xpath(xpath));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		ele.click();
	}
}
