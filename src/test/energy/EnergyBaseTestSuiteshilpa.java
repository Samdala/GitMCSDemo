package test.energy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.ImplicitlyWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.SoftAssert;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public abstract class EnergyBaseTestSuiteshilpa extends AbstractMcsTestSuite{
	
	protected SoftAssert softAssert;
	
	public String FORM_CLASS;
	public String OVERVIEW_GRID_CLASS;
//	public List<String> xpathList;
	public List<String> xpathList = new ArrayList<String>();
	public WebElement currentWebElement;
	
	WebElement getForm() {
		return driver.findElement(
				By.xpath("//div[contains(@class,'"+FORM_CLASS+"')]"));
	}
	
//	public void getFormXpath() {
//		xpath.add("//div[contains(@class,'"+FORM_CLASS+"')]");
//	}
	
//	public void getOverviewGridXpath() {
//		xpath.add("//div[contains(@class,'"+OVERVIEW_GRID_CLASS+"')]");
//	}
	
//	public void clickButton(String buttonName) {
//		for(String elementXpath : xpath) {
//			currentWebElement = currentWebElement.findElement(By.xpath(elementXpath));
//		}
//		
//		currentWebElement.click();
//		xpath.clear();
//		Reporter.log("Click "+buttonName+" button", true);
//		waitForExtJSAjaxComplete(25);
//	}
	
	WebElement getOverviewGrid() {
		return driver.findElement(
				By.xpath("//div[contains(@class,'"+OVERVIEW_GRID_CLASS+"')]"));
	}
	
	public void clickButtonOnForm(String buttonName) {
		getForm().findElement(
				By.xpath(".//button[text() = '"+buttonName+"']"))
				.click();
		Reporter.log("Click "+buttonName+" button", true);
		waitForExtJSAjaxComplete(25);
	}
	
	public void clickButtonOnForm(String formClass, String buttonName) {
		driver.findElement(
				By.xpath("//div[contains(@class, '"+formClass+"')]//button[text() = '"+buttonName+"']"))
				.click();
		Reporter.log("Click "+buttonName+" button", true);
		waitForExtJSAjaxComplete(25);
	}
	
	public void clickButtonOnFormByFormId(String formId, String buttonName) {
		driver.findElement(
				By.xpath("div[@id = '"+formId+"']//button[text() = '"+buttonName+"']"))
				.click();
		Reporter.log("Click "+buttonName+" button", true);
		waitForExtJSAjaxComplete(25);
	}
	
	public void clickButtonOnOverview(String buttonName) {
		getOverviewGrid().findElement(
				By.xpath("//button[text() = '"+buttonName+"']"))
				.click();
		Reporter.log("Click "+buttonName+" button", true);
		waitForExtJSAjaxComplete(25);
	}
	
	public void clickButtonOnDialog(String buttonText) {
		WebElement button = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@class", "x-window-dlg",
						"button", "@class", "x-btn-text", "text()", buttonText, true, true);
		button.click();
		Reporter.log("Click on '"+buttonText+"' button", true);
		waitForExtJSAjaxComplete(25);
	}
	
	public void setFieldValue(String fieldName, String fieldValue) {	
		WebElement field = getForm().findElement(
				By.xpath("(//input[@name='"+fieldName+"']/../input)[last()]"
						+ " | "
						+ "//textarea[@name='"+fieldName+"']"));
		field.clear();
		field.sendKeys(fieldValue);
		Reporter.log("Set <"+fieldName+"> field - <"+fieldValue+"> value", true);
	}
	
	public void setFieldValueByFormId(String formId, String fieldName, String fieldValue) {	
		WebElement field = driver.findElement(
				By.xpath("(//div[@id='"+formId+"']//input[@name='"+fieldName+"']/../input)[last()]"
						+ " | "
						+ "//div[@id='"+formId+"']//textarea[@name='"+fieldName+"']"));
		field.clear();
		field.sendKeys(fieldValue);
		Reporter.log("Set <"+fieldName+"> field - <"+fieldValue+"> value", true);
	}
	
	public String getFieldValue(String fieldName) {
		return getForm().findElement(
							By.xpath("(//input[@name='"+fieldName+"']/../input)[last()]"
								+ " | "
								+ "//textarea[@name='"+fieldName+"']"))
							.getAttribute("value");
	}
	
	public String getFieldValueByFormId(String formId, String fieldName) {
		return driver.findElement(
							By.xpath("(//div[@id='"+formId+"']//input[@name='"+fieldName+"']/../input)[last()]"
								+ " | "
								+ "//div[@id='"+formId+"']//textarea[@name='"+fieldName+"']"))
							.getAttribute("value");
	}
	
	public void clearField(String fieldName) {
		WebElement field =  getForm().findElement(
				By.xpath("(//input[@name='"+fieldName+"']/../input)[last()]"
						+ " | "
						+ "//textarea[@name='"+fieldName+"']"));
		field.click();
		field.clear();
		Reporter.log("Clear "+fieldName+" field", true);
	}
	
	public void setLookupValue(String lookupName, String lookupValue){
		getForm().findElement(
				By.xpath("//input[contains(@name,'"	+ lookupName + "')]//..//..//button")).click();
		waitForExtJSAjaxComplete(25);
		setValueGridLookup(lookupValue);
		waitForExtJSAjaxComplete(25);
	}
	
	public void setLookupValueByFormId(String formId, String lookupName, String lookupValue){
		getForm().findElement(
				By.xpath("//div[@id='"+formId+"']//input[contains(@name,'"	+ lookupName + "')]//..//..//button")).click();
		waitForExtJSAjaxComplete(25);
		setValueGridLookup(lookupValue);
		waitForExtJSAjaxComplete(25);
	}
	
	public void setDropdownValue(String dropdownName, String dropdownValue) {
		String elementId = getForm().findElement(
				By.xpath("//input[@name='"+dropdownName+"']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, dropdownValue);
		Reporter.log("Set "+dropdownName+" - "+dropdownValue, true);
	}
	
	public void verifyFieldSaved(String fieldName, String fieldValue) {
		softAssert.assertEquals(getFieldValue(fieldName), fieldValue, fieldName+" field value was saved successfully");
	}
	
	public void verifyFieldSavedOnFormByFormId(String formId, String fieldName, String fieldValue) {
		softAssert.assertEquals(getFieldValueByFormId(formId, fieldName), fieldValue, fieldName+" field value was saved successfully");
	}
	
	public void saveAndClose() {
		getForm().findElement(
				By.xpath("//button[text() = 'Save and Close']"))
				.click();
		Reporter.log("Click <Save and Close> button", true);
		waitForExtJSAjaxComplete(25);
	}
	
	public void saveAndCloseByFormId(String formId) {
		getForm().findElement(
				By.xpath("//div[@id='"+formId+"']//button[contains(text(), 'Save') and contains(text(), 'Close')]"))
				.click();
		Reporter.log("Click <Save and Close> button", true);
		waitForExtJSAjaxComplete(25);
	}
	
	public void save2() {
		getForm().findElement(
				By.xpath("//button[text() = 'Save']"))
				.click();
		Reporter.log("Click <Save> button", true);
		waitForExtJSAjaxComplete(25);
	}

	public void close2() {
		getForm().findElement(
				By.xpath("//button[text() = 'Close']"))
				.click();
		Reporter.log("Click <Close> button", true);
		waitForExtJSAjaxComplete(25);
	}
	
	public void superClick(String elementToClick, String elementToWait) throws InterruptedException {
		Integer loopIterator=0;
		
		WebDriverWait wait = new WebDriverWait(driver, 1);
		while (!McsElement.isElementDisplayed(driver, elementToWait) && loopIterator<=5) {
			driver.findElement(By.xpath(elementToClick)).click();
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementToWait)));
			} catch (Exception e) {
				loopIterator += 1;
			}
		}
	}
	
	public WebElement findElement() {
		WebElement webElement = driver.findElement(By.xpath("//html"));
		
		for(String xpath : xpathList) {
			webElement = webElement.findElement(By.xpath("."+xpath));
		}
//		xpathList.clear();
		return webElement;
	}
	
	public void expandEnergyMenuItem(String menuItemName) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"span", "text()", menuItemName, true, true).click();
		Reporter.log("Expand "+menuItemName, true);
	}

	public void clickOnEnergyEntity(String entityName) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"button", "text()", entityName, true, true).click();
		Reporter.log("Open entity " + entityName, true);
	}
	
	public void expandNavigator(String menuItemName) {
		if (McsElement.getElementByXpath(driver, "//div[contains(@class, 'nrg-menu-navigator')]").getAttribute("class").contains("collapsed")) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu", 
				"span", "text()", "Navigator", true, true).click();
		Reporter.log("Expand Navigator", true);
		}
	}
	
	public void openDropdown(String formXpathClass, String inputName) {
		driver.findElement(By
				.xpath("(//div[contains(@class,'" + formXpathClass + "')]//input[@name='" + inputName + "'])[last()]//..//img")).click();
	}
	
	public void closeDropdown(String formXpathClass, String inputName) {
		if (driver.findElement(By
				.xpath("(//div[contains(@class,'" + formXpathClass + "')]//input[@name='" + inputName + "'])[last()]")).getAttribute("class").contains("x-form-focus")) {
			driver.findElement(By
					.xpath("(//div[contains(@class,'" + formXpathClass + "')]//input[@name='" + inputName + "'])[last()]//..//img")).click();
		}
	}
	
	public boolean verfiyValueIsPresentInDropdown(String ddItem) {
//		WebElement dropdownList = driver.findElement(By
//				.xpath("//div[contains(@class,'x-layer x-combo-list') and contains(@style, 'visibility: visible')]"));
		try {
			driver.findElement(By
					.xpath("//div[contains(@class,'x-layer x-combo-list') and contains(@style, 'visibility: visible')]"
								+ "//div[contains(@class, 'x-combo-list-item') and contains(text(), '"+ddItem+"')]"));
//							+ "| "
//								+ "//div[contains(@class,'x-layer x-combo-list') and contains(@style, 'visibility: visible')]"
//									+ "//div[@class='x-combo-list-item' and text()='"+ddItem+" ']"));
//			dropdownList.findElement(By
//					.xpath(".//div[@class='x-combo-list-item' and text()='"+ddItem+"'] | .//div[@class='x-combo-list-item' and text()='"+ddItem+" ']"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean verifyMessageDialogContainsText(String messageText) {
		try {
			driver.findElement(By
					.xpath("//div[contains(@class,'x-window-dlg') and contains(@style, 'visibility: visible')]"
								+ "//span[contains(text(), '"+messageText+"')]"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void setMaximumDateRange(String formClass) {
		//Open Date Range drop down menu
		driver.findElement(By.xpath("//div[contains(@class,'"+formClass+"')]//input[contains(@name, 'dateRangePicker')]//following-sibling::img")).click();
		//Click on Dates Before drop down menu item
		driver.findElement(By.xpath("//div[contains(@class,'x-menu-floating') and contains(@style, 'visibility: visible')]//span[contains(text(), 'Dates Before')]")).click();
		//Click on todays (selected) date
		driver.findElement(By.xpath("//div[contains(@class,'x-date-menu') and contains(@style, 'visibility: visible')]//td[contains(@class, 'x-date-today')]//span")).click();
		Reporter.log("Set Date Range - Dates Before today", true);
		
	}
	
	public void setDateRange (String formClass, String dateFrom) throws IOException {
		WebElement dateRangeField = driver.findElement(By.xpath("//div[contains(@class,'"+formClass+"')]//input[contains(@name, 'dateRangePicker')]"));
		dateRangeField.click();
		dateRangeField.clear();
		dateRangeField.sendKeys(dateFrom+" to "+getFutureDate(0));
		waitForExtJSAjaxComplete(2);
		dateRangeField.sendKeys(Keys.RETURN);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Date Range has been set", true);
	}
	
	
}
