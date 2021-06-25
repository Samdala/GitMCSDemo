package test.generalweb.receptiondesk;

//import java.util.concurrent.TimeUnit;

import test.framework.AbstractMcsTestSuite;
//import test.framework.Timer;
//import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class AnnouncementPageObject extends AbstractMcsTestSuite {

	protected final String ANNOUNCEMENT_WINDOW_CLASS = "mcsreceptiondesk";

	protected final String ADD_BUTTON_CSS = "button[class*='x-btn-text icon-ov-add']";

	protected final String REFERENCE_INPUT_CSS = "input[name='reference']";

	protected final String PRODUCT_CODE_INPUT_CSS = "input[name='productCode']";

	protected final String DEFAULT_EXPENCE_INPUT_CSS = "input[name='defaultExpenseAmount']";

	protected final String DEFAULT_REVENUE_INPUT_CSS = "input[name='defaultRevenueAmount']";
	
	protected final String XPATH_XWINDOW = "//div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]";
	
	protected final String XWINDOW_FOOTER_CLASS = "x-window-footer x-window-footer-noborder x-panel-btns";
	
	protected final String DETAILS_GENERAL_FORM_CLASS = "x-panel-body x-panel-body-noheader x-panel-body-noborder x-form";
	
	protected final String XWINDOW_CLASS = "x-window x-window-noborder x-resizable-pinned";
	
	protected final String SEARCH_CLASS = "//button[contains(@style,'icon-search-large.png')]";
	
	
	protected void expandSubMainMenu(final String menuName) {
		if (driver
				.findElement(
						By.xpath("//*[@id='" + PORTAL_NAVIGATION_TREE_ID
								+ "']//*[text()='" + menuName
								+ "']/../..")).getAttribute("class")
				.contains("collapsed")) {
			driver.findElement(
					By.xpath("//*[@id='" + PORTAL_NAVIGATION_TREE_ID
							+ "']//*[text()='" + menuName + "']/../..//img[contains(@class,'plus')]"))
					.click();

			new WebDriverWait(driver, 20, 250)
					.until(new ExpectedCondition<Boolean>() {
						@Override
						public Boolean apply(WebDriver driver) {
							try {

								if (driver
										.findElement(
												By.xpath("//*[@id='"
														+ PORTAL_NAVIGATION_TREE_ID
														+ "']//*[text()='"
														+ menuName
														+ "']/../.."))
										.getAttribute("class")
										.contains("collapsed"))

								{
									return false;
								}

								return true;
							} catch (Exception e) {
								return false;
							}
						}
					});

		}
	}


	public void clickSearchButton() {
		McsElement.getElementByXpath(driver, SEARCH_CLASS).click();
		Reporter.log("search button was clicked", true);
	}
	
	public void clickAddButton() {
		McsElement.getElementByPartAttributeValue(driver, "button", "text()", "Add Announcement", true, true).click();
		Reporter.log("Add announcement button was clicked", true);
	}

	public void clickEditButton() {
		McsElement.getElementByPartAttributeValue(driver, "button", "text()", "Edit", true, true).click();
		Reporter.log("Edit button was clicked", true);
	}

	public void clickCancel() {
		McsElement.getElementByPartAttributeValue(driver, "button", "text()", "Cancel", true, true).click();
		Reporter.log("Cancel button was clicked", true);
	}
	
	
	public void clickGeneralTab(){
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", ANNOUNCEMENT_WINDOW_CLASS, "span", "text()", "General", true, true).click();
		Reporter.log("General tab on announcement was clicked", true);
	}
	
	public void clickVisitorTab(){
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", ANNOUNCEMENT_WINDOW_CLASS, "span", "text()", "Visitors", true, true).click();
		Reporter.log("Visitors tab on announcement was clicked", true);
	}
	
	public void clickAddNewVisitorButton() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", ANNOUNCEMENT_WINDOW_CLASS, "button", "text()", "Add New Visitor", true, true).click();
		Reporter.log("Add announcement button was clicked", true);
	}
	
	public void setVisitType(String visitReference) {
		
		clickLookup("@class",ANNOUNCEMENT_WINDOW_CLASS, "idVisitType","Select a Visit Type");
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Visit Type"), visitReference, "Reference");
		
	}
	
	public String getVisitType() {
		
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'" + ANNOUNCEMENT_WINDOW_CLASS  + "')]//input[@name='idVisitType']//..//input)[last()]"))
				.getAttribute("value");	
	}
	

	public void setArrivalReception(String receptionReference) {
		
		clickLookup("@class",ANNOUNCEMENT_WINDOW_CLASS, "idReceptionArr","Select a Reception");
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Reception"), receptionReference, "Reference");
		
	}
	

	public String getArrivalReception() {
		
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'" + ANNOUNCEMENT_WINDOW_CLASS  + "')]//input[@name='idReceptionArr']//..//input)[last()]"))
				.getAttribute("value");	
	}

	
	
	public void setHostName(String receptionReference) {
		
		clickLookup("@class",ANNOUNCEMENT_WINDOW_CLASS, "idHost","Select a Person");
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Person"), receptionReference, "Last Name");
		
	}

	
	public String getHostName() {
		
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'" + ANNOUNCEMENT_WINDOW_CLASS  + "')]//input[@name='idHost']//..//input)[last()]"))
				.getAttribute("value");	
	}
	
	public void setHostNameSearch(String receptionReference) {
		
		clickLookup("@class",ANNOUNCEMENT_WINDOW_CLASS, "idNameHost","Select a Person");
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Person"), receptionReference, "Last Name");
		
	}

	
	public String getHostNameSearch() {
		
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'" + ANNOUNCEMENT_WINDOW_CLASS  + "')]//input[@name='idNameHost']//..//input)[last()]"))
				.getAttribute("value");	
	}

	
	public void setArrivalDate(String reference) {
		WebElement date = McsElement.getElementByXpath(driver, "//div[contains(@class,'"+ ANNOUNCEMENT_WINDOW_CLASS+"')]//*[contains(@class,'ux-datetime-date')]//input");
		date.click();
		date.clear();
		date.sendKeys(reference);
		Reporter.log("Date was set", true);
	}
	

	public String getArrivalDate() {
		return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+ ANNOUNCEMENT_WINDOW_CLASS+"')]//*[contains(@class,'ux-datetime-date')]//input").getAttribute("value");
	}


	public void setArrivalTime(String reference) {
		WebElement date = McsElement.getElementByXpath(driver, "//div[contains(@class,'"+ ANNOUNCEMENT_WINDOW_CLASS+"')]//*[contains(@class,'ux-datetime-time')]//input");
		date.click();
		date.clear();
		date.sendKeys(reference);
		Reporter.log("Time was set", true);
	}


	public String getArrivalTime() {
		return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+ ANNOUNCEMENT_WINDOW_CLASS+"')]//*[contains(@class,'ux-datetime-time')]//input").getAttribute("value");
	}
	
	
	public void setHostLocation(String receptionReference) {
		
		clickLookupNewUI("@class",ANNOUNCEMENT_WINDOW_CLASS, "idLocation","Select a Location");
		
		waitForExtJSAjaxComplete(25);
		
		//McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXWindowId("Select a Location"), "span", "text()", "Buildings", true, true).click();
		
		setValueGridLookupWithFiltersNewUI("@class", "x6-window-default-closable", receptionReference, "Reference");
		
	}


	public String getHostLocation() {
		
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'" + ANNOUNCEMENT_WINDOW_CLASS  + "')]//input[@name='idLocation']//..//input)[last()]"))
				.getAttribute("value");	
	}

	
	
	public void setNewVisitorLastName(String reference) {
		WebElement date = McsElement.getElementByXpath(driver, "//div[contains(@class,'"+ ANNOUNCEMENT_WINDOW_CLASS+"') and contains(@class,'x-window')]//div[contains(@class,'x-grid3-viewport')]//input");
		date.click();
		date.clear();
		date.sendKeys(reference);
		McsElement.getElementByXpath(driver, "//div[contains(@class,'"+ ANNOUNCEMENT_WINDOW_CLASS+"') and contains(@class,'x-window') ]").click();
		Reporter.log("New Visitor was set", true);
	}
	
	
	public void clickCreate(){
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", ANNOUNCEMENT_WINDOW_CLASS, "button", "text()", "Create", true, true).click();
		Reporter.log("Announcement was created", true);
	}
	

	public void clickSave(){
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", ANNOUNCEMENT_WINDOW_CLASS, "button", "text()", "Save", true, true).click();
		Reporter.log("Announcement was edited", true);
	}

	
	public void clickCancelOnAnnouncement(){
		McsElement.getElementByXpath(driver, "//div[contains(@class,'"+ ANNOUNCEMENT_WINDOW_CLASS+"') and contains(@class,'x-window')]//button[contains(text(),'Cancel')]").click();
	}
	
	
}
