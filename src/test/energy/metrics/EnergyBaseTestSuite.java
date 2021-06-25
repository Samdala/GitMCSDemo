package test.energy.metrics;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.SoftAssert;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public abstract class EnergyBaseTestSuite extends AbstractMcsTestSuite{
	
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
	
	public String getFieldValue(String fieldName) {
		return getForm().findElement(
							By.xpath("(//input[@name='"+fieldName+"']/../input)[last()]"
								+ " | "
								+ "//textarea[@name='"+fieldName+"']"))
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
	
	public void setDropdownValue(String dropdownName, String dropdownValue) {
		String elementId = getForm().findElement(
				By.xpath("//input[@name='"+dropdownName+"']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, dropdownValue);
		Reporter.log("Set "+dropdownName+" - "+dropdownValue, true);
	}
	
	public void verifyFieldSaved(String fieldName, String fieldValue) {
		softAssert.assertEquals(getFieldValue(fieldName), fieldValue, fieldName+" field value was saved successfully");
	}
	
	public void saveAndClose() {
		getForm().findElement(
				By.xpath("//button[text() = 'Save and Close']"))
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
	
	
}
