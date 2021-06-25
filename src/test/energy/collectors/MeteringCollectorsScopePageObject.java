package test.energy.collectors;

//import java.util.concurrent.TimeUnit;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.seleniumemulation.JavascriptLibrary;
import org.testng.Reporter;





























import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class MeteringCollectorsScopePageObject extends MeteringCollectorsPageObject {

	protected final String DIALOG_METER = "slnmMeterId";
	protected final String DIALOG_PROPERTIES = "energy properties x-resizable-pinned";
	protected final String DIALOG_PROPERTIES_NOT_TRUNK = "energy crud-form x-resizable-pinned";
	
	protected final String ADD_SCOPE_WINDOW_HEADER = "Add Objects to Scope";
	
	protected final String EDIT_SCOPE_WINDOW_HEADER = "Edit Objects in Scope";	
	
	protected final String COLLECTORS_WINDOW_HEADER = "slnmCollectors";
	
	
	public void clickYesXwindow() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-window-footer x-window-footer-noborder x-panel-btns", "button",
				"text()", "Yes", true, true).click();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
	}
	
	
	public void saveClose(String dialogClass) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", dialogClass, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		Reporter.log("Save and Close", true);
	}
	
	public void save(String dialogClass) {
		driver
		.findElement(
				By.xpath("(//div[contains(@class,'"+dialogClass+"')]//button[text()='Save'])[last()]")).click();
		Reporter.log("Click Save button", true);
	}

	public void close(String dialogClass) {
		driver
		.findElement(
				By.xpath("(//div[contains(@class,'"+dialogClass+"')]//button[text()='Close'])[last()]")).click();
		Reporter.log("Click Close button", true);
	}
	
	
	public void clickAddOnScopeTab() {
		driver.findElement(By
				.xpath("//div[contains(@class,'"+ADD_COLLECTORS_FORM_CLASS+"')]//div[contains(@class,'scope-tab')]//button[contains(@class, 'addButton')]")).click();
	
	}
	
	public void clickAddOnScopeTabOnMeters() {
		driver.findElement(By
				.xpath("//div[contains(@class,'"+DIALOG_METER+"')]//div[contains(@class,'scope-tab')]//button[contains(@class, 'addButton')]")).click();
	
	}
	
	public void clickEditOnScopeTab() {
		driver.findElement(By
				.xpath("//div[contains(@class,'"+ADD_COLLECTORS_FORM_CLASS+"')]//div[contains(@class,'scope-tab')]//button[contains(@class, 'editButton')]")).click();
	
	}
	
	public void clickCloseOnScopeTab() {
		driver.findElement(By
				.xpath("//div[contains(@class,'"+ADD_COLLECTORS_FORM_CLASS+"')]//div[contains(@class,'scope-tab')]//button[contains(@class, 'icon-scope-end-date')]")).click();
	
	}
	
	public void clickAddEnergyObjectOnObjectToScope() {
		if (!driver.getCurrentUrl().contains("122")) {
			driver.findElement(By
				.xpath("//div[contains(@id,'scopeGridCmp')]//button[contains(text(), 'Add Energy Object')]")).click();
		} else {
			driver.findElement(By
				.xpath("//div[contains(@id,'scopeGridCmp')]//button[contains(text(), 'Add Location')]")).click();
		}
	}
	
	public void clickAddMaintenanceObjectOnObjectToScope() {
		String className;
		if (driver.getCurrentUrl().contains("122") || driver.getCurrentUrl().contains("14")) {
			className = DIALOG_PROPERTIES_NOT_TRUNK;
		}
		else {
			className = DIALOG_PROPERTIES;
		}
		 
		driver.findElement(By
				.xpath("//div[contains(@class,'"+className+"')]//button[contains(text(), 'Add Maintenance Object')]")).click();
	}
	
	public void clickDeleteOnObjectToScope() {
		String className;
		if (driver.getCurrentUrl().contains("122") || driver.getCurrentUrl().contains("14")) {
			className = DIALOG_PROPERTIES_NOT_TRUNK;
		}
		else {
			className = DIALOG_PROPERTIES;
		}
		driver.findElement(By
				.xpath("//div[contains(@class,'"+className+"')]//button[contains(text(), 'Delete')]")).click();
	
	}
	
	public void selectEnergyObject(String energyObjectReference) {
		driver.findElement(By
				.xpath("//div[contains(@class,'"+"x-window-noborder x-resizable-pinned"+"')]//td[contains(@class, 'td-1')]//div[contains(text(), '" + energyObjectReference + "')]")).click();
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		driver.findElement(By
				.xpath("//div[contains(@class,'"+"x-window-noborder x-resizable-pinned"+"')]//button[contains(text(), 'OK')]")).click();
	}
	
	public void selectEnergyObjectOnScope(String energyObjectReference) {
		String className;
		if (driver.getCurrentUrl().contains("122") || driver.getCurrentUrl().contains("14")) {
			className = DIALOG_PROPERTIES_NOT_TRUNK;
		}
		else {
			className = DIALOG_PROPERTIES;
		}
		driver.findElement(By
				.xpath("//div[contains(@class,'"+className+"')]//div[contains(text(), '" + energyObjectReference + "')]")).click();
		waitForExtJSAjaxComplete(20);
	}
	
	public boolean verifyItemEnergyObjectExist(String energyObjectReference) {
		String className;
		if (driver.getCurrentUrl().contains("122") || driver.getCurrentUrl().contains("14")) {
			className = DIALOG_PROPERTIES_NOT_TRUNK;
		}
		else {
			className = DIALOG_PROPERTIES;
		}
		try {
			driver.findElement(By
					.xpath("//div[contains(@class,'"+className+"')]//div[contains(text(), '" + energyObjectReference + "')]"));
			waitForExtJSAjaxComplete(20);
			return true;
		} catch (Exception e) {
			return false;
		}	
	}
	
	public boolean verifyItemEnergyObjectNotExist(String energyObjectReference) {
		String className;
		if (driver.getCurrentUrl().contains("122") || driver.getCurrentUrl().contains("14")) {
			className = DIALOG_PROPERTIES_NOT_TRUNK;
		}
		else {
			className = DIALOG_PROPERTIES;
		}
		try {
			driver.findElement(By
					.xpath("//div[contains(@class,'"+className+"')]//div[contains(text(), '" + energyObjectReference + "')]"));
			waitForExtJSAjaxComplete(20);
			return false;
		} catch (Exception e) {
			return true;
		}	
	}
	
	public void clickOkOnCloseScope() {
		driver.findElement(By.xpath("//div[contains(@class,'x-window x-resizable-pinned')]//button[contains(text(), 'OK')]")).click();
	}
	
	public void setDateOnCloseScope(String date) {
		WebElement dateField = driver.findElement(By.xpath("//div[contains(@class,'x-window x-resizable-pinned')]//input[contains(@name, 'selectedDate')]"));
		dateField.click();
		dateField.clear();
		dateField.sendKeys(date);
		waitForExtJSAjaxComplete(20);
		dateField.click();
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("set Close Date " + date, true); 
	}
	
	public boolean checkButtonEditDisabledOnScopeTab() {
		try {
			driver.findElement(By.xpath("//div[contains(@class,'"+ADD_COLLECTORS_FORM_CLASS+"')]//div[contains(@class,'scope-tab')]//table[contains(@class,'x-item-disabled')]//button[contains(@class, 'editButton')]"));
			waitForExtJSAjaxComplete(20);
			return true;
		} catch (Exception e) {
			return false;
		}
	}	
		
	public boolean checkButtonCloseDisabledOnScopeTab() {
		try {
			driver.findElement(By.xpath("//div[contains(@class,'"+ADD_COLLECTORS_FORM_CLASS+"')]//div[contains(@class,'scope-tab')]//table[contains(@class,'x-item-disabled')]//button[contains(@class, 'icon-scope-end-date')]"));
			waitForExtJSAjaxComplete(20);
			return true;
		} catch (Exception e) {
			return false;
		}	
	}
	
	public String getEffectiveDateOnScopeTab() {
		return driver.findElement(By.xpath("//div[contains(@class,'"+ADD_COLLECTORS_FORM_CLASS+"')]//div[contains(@class,'scope-tab')]//input[contains(@name, 'dateStart')]")).getAttribute("value");
	}
	
	public void setEffectiveDateOnScopeTab(String date) {
		WebElement dateField = driver.findElement(By.xpath("(//div[contains(@class,'energy')]//input[contains(@name, 'dateStart')])[last()]"));
		dateField.click();
		dateField.clear();
		dateField.sendKeys(date);
		dateField.click();
		
		waitForExtJSAjaxComplete(20);
		/*WebElement callendar = driver.findElement(By.xpath("//div[contains(@class,'x-date-menu')]"));
		callendar.sendKeys(Keys.ARROW_LEFT);
		waitForExtJSAjaxComplete(20);
		callendar.sendKeys(Keys.ARROW_LEFT);
		waitForExtJSAjaxComplete(20);
		callendar.sendKeys(Keys.ARROW_LEFT);
		waitForExtJSAjaxComplete(20);
		callendar.sendKeys(Keys.ARROW_LEFT);
		waitForExtJSAjaxComplete(20);
		//field is disabled for edit//only date picker.*/
		Reporter.log("set Close Date current date minus 4 days" + date, true); 
	}
	
	public void selectEnergyObjectOnAddObjectToScope(String energyObjectReference) {
		driver.findElement(By.xpath("//div[contains(@id,'scopeGridCmp')]//div[contains(text(),'" + energyObjectReference + "')]")).click();
	}
	
	public boolean validateDayIsDisabledForClosingScope(String date) {
		boolean bRes = false;
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable-pinned')]//input[contains(@name,'selectedDate')]/..//img")).click();
		
		waitForExtJSAjaxComplete(20);
		
		String day = date.split("-")[0]; 
		
		try {
			
			driver.findElement(By.xpath("//td[contains(@title, 'The date in this field must be before " + getCurrentSystemDate() +  "')]//span[text() = " + day + "]"));
			waitForExtJSAjaxComplete(20);
			bRes = true;
		} catch (Exception e) {
			bRes = false;
		}
		
		waitForExtJSAjaxComplete(20);
		
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable-pinned')]//input[contains(@name,'selectedDate')]")).click();
		
		return bRes;
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
	
	public void setRatio(String row, String value) {
		WebElement field = driver.findElement(By
				.xpath("//div[contains(@class,'energy properties') and not (contains(@class, 'slnmMeterId '))]//div[@class='x-grid3']//div[@class='x-grid3-body']//div["+ row + "]//td[contains(@class, 'x-grid3-td-5')]//div"));
		field.click();
		
		waitForExtJSAjaxComplete(20);
		field = driver.findElement(By
				.xpath("//div[@id='scopeGridCmp']//input[contains(@class,'x-form-num-field')]"));
		field.click();
		
		field.clear();
		field.sendKeys(value);
		
		//driver.findElement(By
		//		.xpath("//span[text()='Add Objects to Scope']")).click();
		JavascriptLibrary javascript = new JavascriptLibrary();
		javascript.callEmbeddedSelenium(driver, "triggerEvent", field, "blur");       
        
		Reporter.log("Set ratio for row " + row + " - value = " +value, true);
	}
	
	public String getRatio(String row) {
		 return driver.findElement(By
				.xpath("//div[contains(@class,'energy properties') and not (contains(@class, 'slnmMeterId '))]//div[@class='x-grid3']//div[@class='x-grid3-body']//div["+ row + "]//td[contains(@class, 'x-grid3-td-5')]//div")).getText();

	}
	
	public String getPercentage(String row) {
		 return driver.findElement(By
				.xpath("//div[contains(@class,'energy properties') and not (contains(@class, 'slnmMeterId '))]//div[@class='x-grid3']//div[@class='x-grid3-body']//div["+ row + "]//td[contains(@class, 'x-grid3-td-percentage')]//div")).getText();

	}

	/**
	 * Same as setLocation with Tree expansion
	 * Set Location if Buildings Tab is not Available
	 * @param locationName
	 */
	public void setPhysicalLocationTree(String locationName){
		clickLookup(DIALOG_METER, "location");
		waitForExtJSAjaxComplete(20);
		setValueTreeLookup(locationName);
		Reporter.log("Set Location - "+locationName, true);
	}
	
	public void selectEnergyObjectByFiltering(String energyObjectReference) {
		setValueGridLookupWithFilters(energyObjectReference, "Reference");
		waitForExtJSAjaxComplete(10);
	}
	
	public void setEffectiveDateOnScope(String xwindowTitle, String effectiveDate) {
		WebElement field = driver.findElement(By
				.xpath("//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//input[@name='dateStart']"));
		field.click();
		field.clear();
		field.sendKeys(effectiveDate);
		field.sendKeys(Keys.ENTER);
		Reporter.log("Set Effective Date - "+effectiveDate, true);
	}
	
	public void clickButton(String buttonName, String dialogClass)
	{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
					"div", "@class", dialogClass, "button", "@class", "x-btn-text",
					"text()", buttonName, true, true).click();
			Reporter.log("Click "+buttonName+" button", true);
	}
	
	public String qtipText(String xwindowTitle){
		
		//Actions builder = new Actions(driver);
		String xpath = "//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//input[@name='dateStart']";
		String qtip = driver.findElement(By.xpath(xpath)).getAttribute("qtip");
		return qtip;
	}
	
	public boolean verifyScopeClosedMsg(){
		try{
			WebElement element = driver.findElement(By.xpath("//div[contains(@class, 'slnmCollectors')]//form[contains(@class, 'x-panel-body x-panel-body-noheader')]//div[contains(text(), 'Scope Period was closed on "+getFutureDate(0)+".')]"));
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		 
		return false; 
	}
}
