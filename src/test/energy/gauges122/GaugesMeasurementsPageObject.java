package test.energy.gauges122;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class GaugesMeasurementsPageObject extends AbstractMcsTestSuite {
	
	//Changed variable
	protected final String GAUGES_GRID_CLASS = "x-tab-panel x-tab-panel-noborder";
	protected final String DIALOG_GAUGE_CHNL = "slnmGaugeChnlMeasId";
	protected final String DIALOG_MEASUREMENT = "slnmMeasId";
	protected final String DIALOG_GAUGE = "slnmGaugeId";
	protected final String ADD_CHANEL_GAUGES_FORM_CLASS = "slnmGaugeChnlId";
	
	public void expandMetering() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu", 
				"span", "text()", "Metering", true, true).click();
		Reporter.log("Expand metering", true);
	}
	
	public void openMeteringEntity(String entity) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"button", "text()", entity, true, true).click();
		Reporter.log("Open metering " + entity, true);
	}
	
	public void changeTab(String tabName) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"li", "@id", tabName+"-tab", "span", "@class", "x-tab-strip-text", true, true).click();
		Reporter.log("Change tab to "+tabName, true);
	}
	
	//TODO: Move later to grid methods (usable when grid doesn't have checkboxes)
	public WebElement checkRowInGriByTextValueAndClick(WebDriver webDriver, String textValue, String dialogClass)  {
		WebElement webElement = webDriver.findElement(By.xpath("//div[contains(@class, '"+dialogClass+"')]//*[@class='x-grid3']//div[text()='"+textValue+"']"));
		waitWebElement(webElement);
		javaScriptFocus(webElement);
		javaScriptClick(webElement);
//		webElement.click();
		Reporter.log("Check element "+textValue+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	
	public void clickViewMeasurementsButton()
	{
		WebElement viewButton = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_GAUGE, "button", "@class", "x-btn-text",
				"text()", "View Measurements", true, true);
		javaScriptFocus(viewButton);
		javaScriptClick(viewButton);
		Reporter.log("Click View Measurements button", true);
	}
	
	public void clickMeasurementsTab()
	{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"ul", "@class", "x-tab-strip", "span", "text()", "Measurements", true, true).click();
		Reporter.log("Click on Measurements tab", true);
	}

	//TODO: Move to general methods later
	public void clickButton(String buttonName, String dialogClass)
	{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", dialogClass, "button", "@class", "x-btn-text",
				"text()", buttonName, true, true).click();
		Reporter.log("Click "+buttonName+" button", true);
	}
	
	public void setValue(String value)
	{
		WebElement indexValue = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MEASUREMENT, 
				"input", "@name", "value", true, true);
		indexValue.click();
		indexValue.clear();
		indexValue.sendKeys(value);
		Reporter.log("Set index value - "+value, true);
	}
	
	public void setDate(String date)
	{
		WebElement dateField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MEASUREMENT, 
				"input", "@name", "date", true, true);
		dateField.click();
		dateField.clear();
		dateField.sendKeys(date);
		Reporter.log("Set date - "+date, true);
	}
	
	public void setTime(String time)
	{
		WebElement timeField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MEASUREMENT, 
				"input", "@name", "time", true, true);
		timeField.click();
		timeField.clear();
		timeField.sendKeys(time);
		Reporter.log("Set time - "+time, true);
	}
	
	public void clickLookup(String inputName, String dialogId) {//String formClass){
		Timer timer = new Timer().start();

		waitFocusAndClick("//div[contains(@class,'"+dialogId+"')]//input[contains(@name,'"
				+ inputName + "')]//..//..//button");

		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By
					.xpath("//div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]"));
		}

		catch (Exception e) {
			waitAndClick("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");
		} finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);
		}

		Reporter.log(inputName + " lookup was clicked"+ " (" + timer.stop()
				+ "ms)", true);
		
	}
	
	public void setMeasurementType(String measurementType) {
		clickLookup("measurementType", DIALOG_MEASUREMENT);
		setValueGridLookup(measurementType);
		Reporter.log("Set Measurement Type - "+measurementType, true);
	}
	
	public String getMeasurementType(String dialogId) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+dialogId+"')]//input[@name='measurementType']//..//input)[last()]"))
				.getAttribute("value");
	}
		
	//TODO: Move to general methods later
	public void saveClose() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MEASUREMENT, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		waitForMaskDisappear();
		Reporter.log("Save and Close", true);
	}
	
	public void close() {
		McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_GAUGE, "button",
				"text()", "Close", true, true).click();
		waitForExtJSAjaxComplete(15);
		Reporter.log("Close", true);
	}

	
	
	public String getDate() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MEASUREMENT, "input", "@name",
				"date", true, true).getAttribute("value");
	}
	
	public String getTime() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MEASUREMENT, "input", "@name",
				"time", true, true).getAttribute("value");
	}
	
	public String getValue() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", DIALOG_MEASUREMENT, "input", "@name",
				"value", true, true).getAttribute("value");
	}

	
}
