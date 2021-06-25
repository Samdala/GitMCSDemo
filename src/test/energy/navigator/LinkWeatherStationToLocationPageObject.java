package test.energy.navigator;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.Timer;
import test.framework.webelement.McsElement;

public class LinkWeatherStationToLocationPageObject extends NavigatorPageObject {


	protected final String EDIT_WEATHER_INFO = "energy properties";
	protected final String WARNING_DIALOG = "x-window-plain x-window-dlg";

	
	public void ClickEditButton() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "fieldset", "button", "@class", "x-btn-text",
				"text()", "Edit", true, true).click();
		Reporter.log("click edit button", true);
	}
	
	public void setWeatherStation(String proposerName){
		clickLookup(EDIT_WEATHER_INFO, "weatherStation");
		String lookupId = getXWindowId("Select a Weather Station");
		setExactValueGridLookupWithFiltersWithValidation("@id", lookupId, proposerName, "Code");
		//setValueGridLookup(proposerName);
//		waitForExtJSAjaxComplete(5);
	}
	
	public String getWeatherStation() {
		return driver
				.findElement(
						By.xpath("(//form[contains(@class,'x-panel-body x-panel-body-noheader x-panel-body-noborder x-form')]//input[@name='weatherStation']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void saveClose() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", EDIT_WEATHER_INFO, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		//waitForMaskDisappear();
	}
	
	public void save() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", EDIT_WEATHER_INFO, "button",
				"text()", "Save", true, true).click();
		//waitForMaskDisappear();
	}

	public void close() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", EDIT_WEATHER_INFO, "button", "text()",
				"Close", true, true).click();
		//waitForMaskDisappear();
	}
	
	public boolean isDataLabelPresent(String idValue, String mesageValue) {
		try {
			driver.findElement(By.xpath("//div[contains(@id, '" + idValue + "')]//div[contains(text(), '" + mesageValue + "')]"));
			return true;
		} catch (Exception e) {
		return false;
		}
	}
	
	public WebElement typeTextToCell(String column, String textType) {
		WebElement webElement = driver.findElement(By.xpath("//*[@class='x-grid3']//tbody//tr//td[contains(@class, 'td-" + column + "')]//div[contains(@class, 'col-" + column + "')]")); //TODO
		webElement.click();
		waitForExtJSAjaxComplete(50);
		WebElement inputElement = driver.findElement(By.xpath("//*[@class='x-grid3']//input"));
		Reporter.log("Check element "+column+" present in dropdown and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		inputElement.clear();
		waitForExtJSAjaxComplete(50);
		inputElement.sendKeys(textType);
		waitForExtJSAjaxComplete(50);
		WebElement webElement1 = driver.findElement(By.xpath("//*[@class='x-grid3']//tbody//tr//td[contains(@class, 'td-" + "0" + "')]//div[contains(@class, 'col-0')]")); //TODO
		webElement1.click();
		waitForExtJSAjaxComplete(50);
		
		return webElement;
	}
	
	public void setInheritWSFromParentObject(boolean bInherit){
		if(bInherit) {
			driver.findElement(By.xpath("//div[contains(@class,'x-resizable-pinned')]//input[contains(@name, 'inherit')]//..//label[text()='Yes']//..//input"))
			.click();
		} else {
			driver.findElement(By.xpath("//div[contains(@class,'x-resizable-pinned')]//input[contains(@name, 'inherit')]//..//label[text()='No']//..//input"))
			.click();
		}
	}
	
	public boolean getInheritWSFromParentObject(){
		return
			driver.findElement(By.xpath("//div[contains(@class,'x-resizable-pinned')]//input[contains(@name, 'inherit')]//..//label[text()='Yes']//..//input"))
			.isSelected();
	}
	
	/**
	 * Helper method to click on Tabs in Navigator Pages
	 * @param tabText
	 */
	public void changeTab(String tabText){
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-tab-panel x-tab-panel-noborder",
				"span", "text()", tabText, true, true).click();
		Reporter.log("Click on tab", true);
	}
	
	public boolean verifyWarningDialogTextMessageEnergy(String mesageValue) {
		boolean status = false;
		try {
			status = driver.findElement(By.xpath("//div[contains(@class, 'x-window-dlg')]//span[contains(text(), '" + mesageValue + "')]")).isDisplayed();
			return status;
		} catch (Exception e) {
			
			e.printStackTrace();
		return status;
		}
	}
}