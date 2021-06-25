package test.energy.tariffcalendars;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Reporter;

import test.energy.EnergyBaseTestSuite;
import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class TariffCalendarsPageObject extends EnergyBaseTestSuite{
	
	protected final String ADMINISTRATION_PANEL_CLASS = "x-panel x-panel-noborder";

	protected final String XPATH_ADD_FORM_WINDOW = "slnmTrfId";
	
	protected final String XPATH_START_DATE_FROM = "x-relative-date-menu";
	
	public void expandConfiguration() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"span", "text()", "Configuration", true, true).click();
		Reporter.log("Expand Configuration", true);
	}

	public void openConfigurationEntity(String entity) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"button", "text()", entity, true, true).click();
		Reporter.log("Open Configuration - " + entity, true);
	}

	public void clickAddButton(){
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADMINISTRATION_PANEL_CLASS, "button", "@class", "x-btn-text",
				"text()", "Add", true, true).click();
		Reporter.log("Click Add button", true);
	}

	public void clickEditButton(){
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@realid", "mcs_energy_tariff_calendars", "button", "@class", "x-btn-text",
				"text()", "Edit", true, true).click();
		Reporter.log("Click Edit button", true);
	}

	public void clickDeleteButton(){
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADMINISTRATION_PANEL_CLASS, "button", "@class", "x-btn-text",
				"text()", "Delete", true, true).click();
		Reporter.log("Click Delete button" + " (" + timer.stop() + "ms)", true);
		clickOnDialogButton("OK");
		Reporter.log("Click OK button", true);
		
	}
	
	public void setCode(String code) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"code", true, true).clear();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"code", true, true).sendKeys(code);
		Reporter.log("Set Code - '" + code + "'", true);
	}

	public String getCode() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "code", true, true).getAttribute("value");
	}
	
	public void setReference(String reference) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"reference", true, true).clear();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"reference", true, true).sendKeys(reference);
		Reporter.log("Set Reference - '" + reference + "'", true);
	}

	public String getReference() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "reference", true, true).getAttribute("value");
	}
	
	public void setRegion(String region) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"region", true, true).clear();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"region", true, true).sendKeys(region);
		Reporter.log("Set Region - '" + region + "'", true);
	}

	public String getRegion() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "region", true, true).getAttribute("value");
	}
	
	public void setDescription(String description) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "textarea", "@name",
				"description", true, true).clear();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "textarea", "@name",
				"description", true, true).sendKeys(description);
		Reporter.log("Set Description - '" + description + "'", true);
	}

	public String getDescription() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "textarea",
				"@name", "description", true, true).getAttribute("value");
	}
	
	public void setCurency(String currency) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='monetaryUom']//..//input[contains(@class, 'x-form-field')]"))
					.getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, currency);
		Reporter.log("Set Curency - " + currency, true);
	}
	
	public String getCurrency() {
		return driver.findElement(By
				.xpath("(//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='monetaryUom']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setCommodityClass(String commodityClass) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='commodityClassId']//..//input[contains(@class, 'x-form-field')]"))
					.getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, commodityClass);
		Reporter.log("Set Commodity Class - " + commodityClass, true);
	}
	
	public String getCommodityClass() {
		return driver.findElement(By
				.xpath("(//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='commodityClassId']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setCountry(String country){
		clickLookup(XPATH_ADD_FORM_WINDOW, "country");
		setValueGridLookup(country);
		Reporter.log("Set Country - " + country, true);
	}
	
	public String getCountry() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='country']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setDNO(String dno){
		clickLookup(XPATH_ADD_FORM_WINDOW, "dno");
		setValueGridLookup(dno);
		Reporter.log("Set DNO - " + dno, true);
	}
	
	public String getDNO() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='dno']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void checkTariffLevel(String tariff) {
		driver.findElement(
						By.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]"
								+ "//table[@class='x-grid3-row-table']//div[text()='"+tariff+"']//..//.."
								+ "//td[contains(@class, 'x-grid3-td-checker')]//div[@class='x-grid3-row-checker']"))
								.click();
		Reporter.log("Check " + tariff + " checkbox", true);
	}
	
	public boolean verifyTariffLevelChecked(String tariff) {
		String attrClass = driver.findElement(
						By.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]"
								+ "//table[@class='x-grid3-row-table']//div[text()='"+tariff+"']/../../../../..")).getAttribute("class");
		if (attrClass.contains("selected"))
			return true;
		else
			return false;								
	}
	
	public void saveClose() {
		waitForExtJSAjaxComplete(5);
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "button",
				"text()", "Save", "text()", "Close", true, true)
				.click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Click Save & Close", true);
	}
	
	public void changeTab(String tabName) {
		driver.findElement(
				By.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//span[text()='"+tabName+"']"))
					.click();
		Reporter.log("Change tab to " + tabName, true);
	}
	
	public boolean findTariffLevelButton(String tariff) {
		try {
			driver.findElement(
					By.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//button//div[text()='"+tariff+"']"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickAddPatternButton() {
		driver.findElement(
				By.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//button[text()='Add']"))
				.click();
		Reporter.log("Click Add button on Pattern tab", true);
	}
	
	public void clickDeletePatternButton() {
		driver.findElement(
				By.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//button[text()='Delete']"))
				.click();
		Reporter.log("Click Delete button on Pattern tab", true);
	}
	
	public void clickAddPatternComposerButton() {
		driver.findElement(
				By.xpath("(//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//button[text()='Add'])[last()]"))
				.click();
		Reporter.log("Click Add button on Composer tab", true);
	}
	
	public void clickDeletePatternComposerButton() {
		driver.findElement(
				By.xpath("(//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//button[text()='Delete'])[last()]"))
				.click();
		Reporter.log("Click Delete button on Composer tab", true);
	}
	
	public void clickTariffButton(String tariff) {
		driver.findElement(
				By.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//button//div[text()='"+tariff+"']"))
				.click();
		Reporter.log("Click on " + tariff + " button", true);
	}
	
	public void fillPattern() {
		String day = null;
		for (int i = 1; i <= 7; i++){
			switch (i) {
			case 1: day = "Mon";
					break;
			case 2: day = "Tue";
					break;
			case 3: day = "Wed";
					break;
			case 4: day = "Thu";
					break;
			case 5: day = "Fri";
					break;
			case 6: day = "Sat";
					break;
			case 7: day = "Sun";
					break;
			}
			for (int j = 0; j <= 23; j++){
				driver.findElement(
						By.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]"
								+ "//div[text()='"+day+"']//..//..//td[contains(@class, 'x-grid3-td-"+j+"')]//div"))
								.click();
			}
		}
		Reporter.log("Fill Pattern with selected Tariff Level", true);
	}
	
	public boolean verifyPatternColor(String color, String day, String hour) {
		String style = driver.findElement(
				By.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]"
						+ "//div[text()='"+day+"']//..//..//td[contains(@class, 'x-grid3-td-"+hour+"')]"))
						.getAttribute("style");
		if (style.contains(color) || style.contains(Color.fromString(color).asRgb()))
			return true;
		else
			return false;
	}
	
	public void setPatternName(String pattern) {
		//Click on Pattern Name field to activate it (always clicks on last field)
		WebElement webElement = driver.findElement(By.
				xpath("(//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//div[contains(@class, 'x-grid3-col-name') and contains(text(), 'New Pattern')])[last()]"));
		javaScriptFocus(webElement);
		javaScriptClick(webElement);
		waitForExtJSAjaxComplete(5);
		//Find input and send value
		WebElement patternNameField = driver.findElement(By.
				xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[contains(@class, 'tariffCalendarPatternName')]"));
		patternNameField.clear();
		patternNameField.sendKeys(pattern);
		//Click on footer to take off focus from input
		driver.findElement(By.
				xpath("(//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//td[contains(@class,'x-toolbar-left')])[last()]")).click();
		Reporter.log("Set Pattern Name - " + pattern, true);
	}
	
	public void selectPattern(String patternName) {
		driver.findElement(By.
				xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//div[contains(@class, 'x-grid3-col-name') and text()='"+patternName+"']"))
				.click();
		Reporter.log("Select Pattern  - " + patternName, true);
	}
	
	public void setComposerPattern(String pattern) throws InterruptedException {
		//Click on Composer -> Pattern field to activate combobox (always clicks on last one)
		String patternFieldXpath = "(//div[contains(@class,'x-grid3-col-patternId')])[last()]";
		String comboboxArrowXpath = "//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[contains(@class, 'tariffCalendarPatternId')]//..//img";
		
		superClick(patternFieldXpath, comboboxArrowXpath);
		waitForExtJSAjaxComplete(5);
		driver.findElement(By.xpath(comboboxArrowXpath)).click();
		//Select value from combobox
		driver.findElement(By.
				xpath("//div[contains(@class,'x-layer x-combo-list')]//div[contains(text(), '"+pattern+"')]"))
					.click();
		waitForExtJSAjaxComplete(5);
		Reporter.log("Set Pattern on Composer - " + pattern, true);
	}
	
	//This method changes 'previousPattern' to 'pattern'
	public void setComposerPattern(String pattern, String previousPattern) throws InterruptedException {
		//Click on Pattern in grid
		superClick("//div[contains(@class,'x-grid3-col-patternId') and text()='"+previousPattern+"']", "//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[contains(@class, 'tariffCalendarPatternId')]//..//img");
//		driver.findElement(
//				By.xpath("//div[contains(@class,'x-grid3-col-patternId') and text()='"+previousPattern+"']"))
//					.click();
//		waitForExtJSAjaxComplete(20);
		//Click on combobox
		driver.findElement(By.
					xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[contains(@class, 'tariffCalendarPatternId')]//..//img"))
						.click();
		waitForExtJSAjaxComplete(5);
		//Select Pattern from combobox
		driver.findElement(By.
				xpath("//div[contains(@class,'x-layer x-combo-list')]//div[contains(text(), '"+pattern+"')]"))
					.click();
		changeTab("Composer");
		Reporter.log("Set Pattern on Composer - " + pattern, true);
	}
	
	public void clickComposerStartDate() {
		WebElement webElement = driver.findElement(
				By.xpath("(//div[contains(@class,'x-grid3-col-startDate')])[last()]"));
		javaScriptFocus(webElement);
		javaScriptClick(webElement);
		Reporter.log("Click on Start Date", true);
	}
	
	public void clickComposerStartDate(String previousDate) throws InterruptedException {
		superClick("//div[contains(@class,'x-grid3-col-startDate') and text()='"+previousDate+"']", "//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[contains(@class, 'tariffCalendarStartDate')]//..//img");
//		driver.findElement(
//				By.xpath("//div[contains(@class,'x-grid3-col-startDate') and text()='"+previousDate+"']"))
//						.click();
		Reporter.log("Click on Start Date", true);
	}
	
	public void clickDatePicker() {
		driver.findElement(By
				.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[contains(@class, 'tariffCalendarStartDate')]//..//img")).click();;
		Reporter.log("Click on Date Picker", true);
	}
		
	public void setComposerStartDateViaCombo(String day, String month) {
		//Set Start Date day via combobox
		String elementIdDay = driver.findElement(
				By.xpath("(//div[contains(@class,'"+XPATH_START_DATE_FROM+"')]//input[@name='specificDay'])[last()]"))
					.getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementIdDay, day);
		Reporter.log("Set Start Date day - "+ day, true);
		//Set Start Date month via combobox
		String elementIdMonth = driver.findElement(
				By.xpath("(//div[contains(@class,'"+XPATH_START_DATE_FROM+"')]//input[@name='specificMonth'])[last()]"))
					.getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementIdMonth, month);
		Reporter.log("Set Start Date month - "+month, true);
	}
	
	public void clickApplyButton() {
		driver.findElement(By.xpath("(//div[contains(@class,'"+XPATH_START_DATE_FROM+"')]//button[text()='Apply'])[last()]"))
			.click();
		Reporter.log("Click Apply button", true);
	}
	
	public void clickAddMenu() {
		  
		  String id = driver.findElement(By.xpath("//div[contains(@class,'slnmTrfId')]//em[contains(@class,'x-btn-split')]//button[contains(text(),'Add')]/../../../../..")).getAttribute("id");
		  
		  ((JavascriptExecutor) driver)
		  .executeScript("Ext.getCmp('"+id+"').showMenu();");
		  Reporter.log("Open Add menu", true);
		  try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clickAddMenuUseExisting() {
		driver.findElement(By.xpath("//div[contains(@class, 'x-menu-floating')]//span[text()='Use Existing Pattern']"))
		.click();
		Reporter.log("Click on 'Use Existing' menu item", true);
	}
	
	public void clickAddMenuUseExistingNotTrunk() {
		driver.findElement(By.xpath("//div[contains(@class, 'x-menu-floating')]//span[text()='Use existing pattern']"))
		.click();
		Reporter.log("Click on 'Use Existing' menu item", true);
	}
	
	public void selectExistingPattern(String patternName) {
		//Enter Pattern name in filter field
		driver.findElement(By.xpath("(//div[contains(@class, 'x-window x-resizable-pinned')]//input[contains(@name, 'filter-editor-0')])[last()]"))
		.sendKeys(patternName);
		//CLick somewhere to apply filter
		driver.findElement(By.xpath("(//div[contains(@class, 'x-window x-resizable-pinned')]//input[contains(@name, 'filter-editor-1')])[last()]"))
		.click();
		waitForExtJSAjaxComplete(5);
		//Select Pattern from grid
		String []subStrings = patternName.split(" ");
		
		String subXpathStr = "starts-with(text(),'"+subStrings[0]+"')";
		
		for(int i=1; i<subStrings.length; i++){
			
			subXpathStr+="and contains(text(),'"+subStrings[i]+"')";
		}
		driver.findElement(By.xpath("//div[contains(@class, 'x-window x-resizable-pinned')]//div["+subXpathStr+"]"))
		.click();
		waitForExtJSAjaxComplete(5);
		//Click Select button
		driver.findElement(By.xpath("(//div[contains(@class, 'x-window x-resizable-pinned')]//button[text() = 'Select'])[last()]"))
		.click();
		Reporter.log("Select existing pattern - "+patternName, true);
	}
	 
	
	
	/**
	 *  Helper method to get column number in Grid 
	 */
	public int getGridColumnNumber(String attribute, String attributeValue, String columnName){
		
		String columnClass = McsElement.getElementByXpath(driver,"(//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'quickfilters')]//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')][last()])").getAttribute("class");
		String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);
			
		return Integer.parseInt(columnNumber);
	}
		
	/**
	 * Helper method to get Text Value of Grid
	 */
	public String getCellTextValue(String attribute, String attributeValue, String columnName, String rowNum) {
		
		int columnNumber = getGridColumnNumber(attribute, attributeValue , columnName );
		
		WebElement elementXpath = driver.findElement(By.xpath("//div[contains("+attribute+", '"+attributeValue+"')]//div[contains(@class, 'x-grid3-body')]//div["+rowNum+"]//div[contains(@class, 'x-grid3-cell-inner x-grid3-col-"+columnNumber+"')]"));
		String textContent = elementXpath.getAttribute("textContent");
		
		return textContent;
	}
}
