package test.energy.weatherstations;

//import java.util.concurrent.TimeUnit;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import test.energy.EnergyBaseTestSuite;
import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;
import test.framework.webelement.FileUploader;

public class MeteringWeatherstationChanelsActualDataPageObject extends EnergyBaseTestSuite {
	
	protected final String ADD_WEATHERSTATIONS_FORM_CLASS = "slnmWSId";

	protected final String ADD_WEATHERSTATIONS_FOOTER_CLASS = "x-window-footer x-panel-btns";
	
	protected final String WEATHERSTATIONS_GRID_CLASS = "x-tab-panel x-tab-panel-noborder";
	
	protected final String WEATHERSTATIONS_CHANELS_GRID_CLASS =  "x-panel channels-tab x-panel-noborder";
	
	protected final String ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS = "slnmWSChnlId";
	
	protected final String ACTUALDATA_WEATHERSTATIONS_FORM_CLASS = "slnmWSChnlADId";
	
	public static String channelDialogId;
	public static String weatherstationDialogId;
	public static String actualDataDialogId;
	public static String importDialogId;

	public void goToDefinition(String definition) {
		driver.get(configuration.getApplicationUrl() + "/frame.php?relay="
				+ definition);
	};

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
		clickOnDialogButton("Yes");
		
		Reporter.log("Delete selected Channel measure" + " (" + timer.stop() + "ms)", true);
	}
	
	public String getReference() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "input",
				"@name", "reference", true, true).getAttribute("value");
	}

	public void setReference(String reference) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "input", "@name",
				"reference", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(reference);
		Reporter.log("set reference " + reference, true);
	}
	
	public String getCode() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "input",
				"@name", "code", true, true).getAttribute("value");
	}
	
	public void setChannelParameter(String proposerName){
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "channelParameter");
		setValueGridLookup("@id",getXWindowId("Select a Parameter"),proposerName);
	}
	
	public String getChannelParameter() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'slnmWSChnlId')]//input[@name='channelParameter']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setBaseTemperature(String proposerName){
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "baseTemperature");
		setValueGridLookup("@id",getXWindowId("Select a Base Temperature"),proposerName);
	}
	
	public String getBaseTemperature() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'slnmWSChnlId')]//input[@name='baseTemperature']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public String getEntryMethod() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "input",
				"@name", "entryMethod", true, true).getAttribute("value");
	}
	
	public void setReadingInterval(String comboValue) {
		WebElement xPath = driver.findElement(By.xpath("//div[contains(@class, '"+ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS+" ')]//input[@name='readingInterval']"));
		
		String id = xPath.getAttribute("id");
		
		waitForExtJSAjaxComplete(10);
		
		DropDown.setExtJsComboValue(driver, id, comboValue);
		
		/*((JavascriptExecutor) driver)
		.executeScript("document.getElementById('"+id+"').value ='"+comboValue+"';");*/
		
		waitForExtJSAjaxComplete(30);
		
		Reporter.log("set Reading Interval to " + comboValue, true);
	}
	
	public String getReadingInterval() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "input",
				"@name", "readingInterval", true, true).getAttribute("value");
	}
	
	public void setUOM(String uomValue){
		clickLookup(ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "unitOfMeasure");
		setValueGridLookup("@id",getXWindowId("Select a Unit Of Measure"),uomValue);
		waitForExtJSAjaxComplete(10);
	}
	
	public String getUOMValue() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'slnmWSChnlId')]//input[@name='unitOfMeasure']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void saveClose() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close Clicked", true);
	}
	
	public void save() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_CHANEL_WEATHERSTATIONS_FORM_CLASS, "button",
				"text()", "Save", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Clicked", true);
	}

	public void closeWeatherstation() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", ADD_WEATHERSTATIONS_FORM_CLASS, "button", "text()",
				"Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Close Clicked", true);
	}
	
	public void closeActualData() {		
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS, "div", "@class",
				"x-tool x-tool-close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Close Actual Data Clicked", true);
		
	}
	
	public void closeReferenceData() {		
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", "slnmWSChnlRDId", "button", "text()",
				"Close", true, true).click();
		//waitForMaskDisappear();
	}
	
	public void saveAndCloseReferenceData() {		
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", "slnmWSChnlRDId", "button", "text()",
				"Save and Close", true, true).click();
		//waitForMaskDisappear();
	}
	
	public void changeTab(String tabName) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"li", "@id", tabName+"-tab", "span", "@class", "x-tab-strip-text", true, true).click();
		Reporter.log("change tab to "+tabName, true);
	}
	
	//TODO: Move later to grid methods (usable when grid doesn't have check boxes)
		/*public static WebElement checkRowInGriByTextValueAndClick(WebDriver webDriver, String textValue, String dialogId)  {	
				WebElement webElement = webDriver.findElement(By.xpath("//div[@id='"+dialogId+"']//*[@class='x-grid3']//div[contains(text(),'"+textValue+"')]"));
				webElement.click();
				try {Thread.sleep(500);} catch (InterruptedException e) {}
				return webElement;
	}*/
			
	//TODO: Move later to grid methods (usable when grid doesn't have check boxes)
	public WebElement checkRowInGriByTextValueAndClick(WebDriver webDriver, String textValue)  {
		WebElement webElement = driver.findElement(By.xpath("//div[contains(@class, 'slnmWSId')]"+xpathGeneratorForTextElement(textValue)));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
			webElement.click();
		}catch(Exception e){
			e.printStackTrace();
		}
		Reporter.log("Check element "+textValue+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	
	public static String xpathGeneratorForTextElement(String text){
		  
		  String subStrings[] = text.split(" ");
		  
		  String xpath = "//div[starts-with(text(),'"+subStrings[0]+"')";
		  
		  for(int i=1; i<subStrings.length; i++){

		   xpath+="and contains(text(),'"+subStrings[i]+"')";
		  }
		  
		  return xpath+"]";
		  
	}
		
	public boolean isRowInGridChanelPresent(String gridValue) {
		try {
			McsElement.getElementByXpath(driver, "//div[contains(@class, 'slnmWSId')]//div[contains(text(),'" + gridValue + "')]");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//TODO: Move to general methods later
	public void clickButton(String buttonName, String dialogId)
	{
		/*McsElement.getElementByPartAttributeValueAndParentElement(driver,
					"div", "@id", dialogId, "button", "@class", "x-btn-text",
					"text()", buttonName, true, true).click();*/
		WebElement ele = driver.findElement(By.xpath("//div[contains(@id, '"+dialogId+"')]//button[contains(@class, 'x-btn-text') and contains(text(), '"+buttonName+"')]"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		ele.click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Click "+buttonName+" button", true);
	}
	
	//TODO: Move to general methods later
	public void UploadFile(String filePath, String windowId)
	{
		FileUploader.uploadFileName(driver, "@class", "x-window", filePath);
		Reporter.log("File was successfully added.", true);
	}
	
	public void setDateRange(String reference) {
	WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS, "input", "@name",
				"wsMeasurementsPopup_dateRangePickerInterval", true, true);
			
		waitForExtJSAjaxComplete(20);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(reference);
		waitForExtJSAjaxComplete(10);
		//referenceField.sendKeys(Keys.RETURN);
		referenceField.sendKeys(Keys.ENTER);
		waitForExtJSAjaxComplete(20);
		//referenceField.sendKeys(Keys.TAB);
		/*WebElement window = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ACTUALDATA_WEATHERSTATIONS_FORM_CLASS, "div", "@class",
				"x-window-mr", true, true);
		waitForExtJSAjaxComplete(20);
		window.click();*/
		waitForExtJSAjaxComplete(20);
		Reporter.log("set Date Range " + reference, true); 
	}
	
	public static WebElement getCellElementByColumnNumber(WebDriver webDriver, String parentAttr, String parentAttrVal, String colNumber) {
		WebElement webElement = webDriver.findElement(By.xpath("//div[contains("+parentAttr+",'"+parentAttrVal+"')]//*[@class='x-grid3']//td[contains(@class,'td-" + colNumber + " ')]//div[contains(@class,'col-" + colNumber + "')]"));
		Reporter.log("Element from column  "+colNumber+" is present in grid.", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	
	public static void clickCellElementByColumnNumber(WebDriver webDriver, String parentAttr, String parentAttrVal, String colNumber) {
		WebElement webElement = webDriver.findElement(By.xpath("//div[contains("+parentAttr+",'"+parentAttrVal+"')]//*[@class='x-grid3']//td[contains(@class,'td-" + colNumber + " ')]//div[contains(@class,'col-" + colNumber + "')]"));
		webElement.click();
		Reporter.log("Element from column  "+colNumber+" is present in grid and clicked", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
}
	
	
	
	public void setReferenceData(String month, String value) {
		String id = driver.findElement(By.xpath("//div[contains(@class,'x-panel  x-grid-with-col-lines x-grid-panel')]")).getAttribute("id");
		String exe = "Ext.getCmp('"+id+"').getStore().data.items[0].data['"+month+"']="+value+"; Ext.getCmp('"+id+"').getStore().data.items[0].afterEdit()";
		
		((JavascriptExecutor) driver).executeScript(exe);
		
		
	}
	
	
	public void setReferenceData(WebDriver webDriver, String parentAttr, String parentAttrVal, String colNumber, String referenceDegree) {
		WebElement cellElement = getCellElementByColumnNumber(webDriver, parentAttr, parentAttrVal, colNumber);
		Actions action = new Actions(driver);
		action.doubleClick(cellElement);
		action.perform();
		
		waitForExtJSAjaxComplete(50);
		
		WebElement webElement = McsElement.getElementByXpath(webDriver, "(//div[contains("+parentAttr+",'"+parentAttrVal+"')]//*[@class='x-grid3']//" + "input" + "[contains(" + "@class" + ",'" + "x-form-text x-form-field x-form-num-field" +"')])[last()]");
		String elClass = webElement.getAttribute("class");
		Boolean bRes = elClass.matches("focus");
		if (bRes) {
			action.doubleClick(cellElement);
			action.perform();
			waitForExtJSAjaxComplete(50);
			Reporter.log("Set reference fail on first try. Column number " + colNumber, true);
			WebElement webElement1 = McsElement.getElementByXpath(webDriver, "(//div[contains("+parentAttr+",'"+parentAttrVal+"')]//*[@class='x-grid3']//" + "input" + "[contains(" + "@class" + ",'" + "x-form-text x-form-field x-form-num-field" +"')])[last()]");
			String elClass1 = webElement1.getAttribute("class");
			Boolean bRes1 = elClass1.matches("focus");
			if (bRes1) {
				action.doubleClick(cellElement);
				action.perform();
				waitForExtJSAjaxComplete(50);
				Reporter.log("Set reference fail on second try. Column number " + colNumber, true);
			}
		}
		webElement.clear();
		webElement.sendKeys(referenceDegree);
		Reporter.log("Set reference Degree days with - " + referenceDegree, true);
	}

	/*public static List <WebElement> getElementsList(WebDriver webDriver, String parentAttr, String parentAttrVal, String element, String childAttr, String childAttrVal) {;
		List <WebElement> webElements = webDriver.findElements(By.xpath("//div[contains("+parentAttr+",'"+parentAttrVal+"')]//*[@class='x-grid3']//" + element + "[contains(" + childAttr + ",'" + childAttrVal +"')]"));
		return webElements;
	}*/
	
	public String getReferenceData(WebDriver webDriver, String parentAttr, String parentAttrVal, String colNumber) {
		WebElement cellElement = getCellElementByColumnNumber(webDriver, parentAttr, parentAttrVal, colNumber);
		return cellElement.getAttribute("innerHTML");
	}
	
	/**
	 *  Helper method to get column number in Grid 
	 */
	public int getGridColumnNumber(String attribute, String attributeValue, String columnName){
		
		String columnClass = McsElement.getElementByXpath(driver,"//div[contains("+attribute+", '"+attributeValue+"') and not (contains(@class, 'x-hide-display'))]//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')][last()]").getAttribute("class");
		String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);
		return Integer.parseInt(columnNumber);
	}
	
	/**
	 * Helper method to set Measurement Value in Calendar Window
	 */
	public void setMeasurementValue(String attribute, String attributeValue, String rowNum, String columnName, String value){
			
		int lineIDColumnNumber = getGridColumnNumber(attribute, attributeValue , columnName );
	
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class, 'x-window energy properties') and not (contains(@class, 'x-hide-display'))]//div[contains(@class, 'x-grid3-body')]//div["+rowNum+"]//div[contains(@class,'x-grid3-cell-inner x-grid3-col-"+lineIDColumnNumber+"')]"));
		
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		
		WebElement inputField = driver.findElement(By.xpath("//div[contains(@class, 'x-window energy properties') and not (contains(@class, 'x-hide-display'))]//div[contains(@class, 'x-grid3-scroller')]//div[contains(@class, 'x-layer x-editor') and contains(@style, 'visibility: visible')]//input[contains(@class, 'x-form-num-field')]"));
		inputField.click();
		inputField.clear();
		inputField.sendKeys(value);
		inputField.sendKeys(Keys.TAB);
		
	}
	
	public void save(String attributeValue) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", attributeValue, "button",
				"text()", "Save", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Clicked", true);
	}
	
	public void saveClose(String attributeValue) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", attributeValue, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close Clicked", true);
	}
	
	public void close(String attributeValue) {
		WebElement element = driver.findElement(By.xpath("//div[contains(@class, '"+attributeValue+"')]//button[text()='Close']"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
			element.click();
		} catch(Exception e){
			e.printStackTrace();
		}
		waitForExtJSAjaxComplete(25);
		Reporter.log("Close Clicked", true);
	}
	
	/*public void setTextInField(String attribute, String attributeValue, String columnName, String value){
		int lineIDColumnNumber = getGridColumnNumber(attribute, attributeValue , columnName );
		
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class, 'x-window energy properties') and not (contains(@class, 'x-hide-display'))]//div[contains(@class, 'x-grid3-body')]//div[2]//div[contains(@class,'x-grid3-cell-inner x-grid3-col-"+lineIDColumnNumber+"')]"));
		
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		
		WebElement inputField = driver.findElement(By.xpath("//div[contains(@class, 'x-window energy properties') and not (contains(@class, 'x-hide-display'))]//div[contains(@class, 'x-grid3-scroller')]//div[contains(@class, 'x-layer x-editor') and contains(@style, 'visibility: visible')]//input[contains(@class, 'x-form-num-field')]"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", inputField);
		((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('value', "+ value +")", inputField);
		
		waitForExtJSAjaxComplete(25);
		
	}*/
	
	public boolean VerifyButtonDisabled(){
		try{
			driver.findElement(By.xpath("//div[contains(@class, 'energy properties')]//button[contains(@class, 'x-btn-text') and text()='Save']//ancestor::table[contains(@class, 'x-item-disabled')]"));
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public void selectPreviousMonth(){
		WebElement element = driver.findElement(By.xpath("//div[contains(@class, 'x-window energy properties') and not (contains(@class, 'x-hide-display'))]//div[contains(@class, 'x-box-layout-ct')]//div[contains(@class, 'x-box-inner')]//input[@name='selected-month']/ancestor::div[contains(@class, 'x-box-item')]//div[contains(@class, 'x-box-inner')]//button[contains(@class, 'btn-prev')]"));
		element.click();
		waitForExtJSAjaxComplete(10);
	}
	
	/**
	  * Helper method to select Channel From Dropdown of channel Measurements Window
	  */
	 public void setChannelMeasurements(String className, String status){
		String id = McsElement
				.getElementByXpath(
						driver,
						"//div[contains(@class, '"+className+" ')]//div[contains(@class, 'x-form-item')]//label[text()='Channel']//..//input[contains(@class, 'x-form-text x-form-field')]")
				.getAttribute("id");
					
		DropDown.setExtJsComboValue(driver, id, status);
			
		waitForExtJSAjaxComplete(25);
			
		Reporter.log("Set Status to" + status, true);
	 }
	 
	 public String getWarningDialogText(){
			
		WebElement element = driver.findElement(By.xpath("//div[contains(@class, 'x-window-dlg')]//span[@class='ext-mb-text']"));
			
		return element.getText();
			
	}
	 
	 public void clickPreviousYearButtonInChannel() {
		 McsElement.getElementByPartAttributeValueAndParentElement(driver,
				 "div", "@class", "energy", "button", "@class", "btn-prev", true, true).click();
		 Reporter.log("click Previous year button", true);
	 }
	 
	 public void setDateRangeCustomized(String formClass, String dateFrom) throws IOException {
			WebElement dateRangeField = driver.findElement(By.xpath("//div[contains(@class,'"+formClass+"')]//input[contains(@name, 'dateRangePicker')]"));
			try{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", dateRangeField);
				dateRangeField.click();
				dateRangeField.clear();
				dateRangeField.sendKeys(dateFrom+" to "+getFutureDate(0));
				waitForExtJSAjaxComplete(2);
				dateRangeField.sendKeys(Keys.RETURN);
				waitForExtJSAjaxComplete(25);
			} catch(Exception e){
				e.printStackTrace();
			}
			Reporter.log("Date Range has been set", true);
		}
}
