package test.energy.gauges;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.webelement.McsElement;
import test.framework.webelement.Tree;

public class MeteringGaugesCopyPageObject extends AbstractMcsTestSuite {
	
	final static String GAUGE_GRID_CLASS = "x-tab-panel x-tab-panel-noborder";
	protected final String DIALOG_GAUGE_COPY = "copy-gauge";
	protected final String DIALOG_GAUGE = "slnmGaugeId";
	protected final String DIALOG_GAUGE_CHNL = "slnmGaugeChnlId";
	protected final String area = "slnmEnrgArea1 (slnmEnrgArea)";
	protected final String site = "slnmEnrgSite1";
	protected final String building = "slnmEnrgBuilding1";
	protected final String building2 = "slnmEnrgBuilding1.6";
	
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
	
	public void clickAddToSelectionButton(String dialogId) {
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@id", dialogId, "button", "@style", "add", true, true).click();
		Reporter.log("Click 'Add to Selection' button", true);
	}
	
	public void selectMultipleLocations() {
		
		//get lookup dialog id
		String dialogId = getXWindowId("Select Energy Objects");
		
		//change tab to Others
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@id", dialogId, "span", "text()", "Other",
				"@class", "x-tab", true, true).click();
		waitForExtJSAjaxComplete(20);
		
		//expand nodes and select multiple locations
		
		expandNode("div", "@id", dialogId, area);
		waitForExtJSAjaxComplete(20);
				
		expandNode("div", "@id", dialogId, site);
		waitForExtJSAjaxComplete(20);
		
		expandNode("div", "@id", dialogId, building);
		waitForExtJSAjaxComplete(20);
				
//		expandNode("div", "@id", dialogId, building);
//		waitForExtJSAjaxComplete(20);
		checkNodeInTreeByTextValue(building2);
		Reporter.log("Select "+building2+" node", true);
		waitForExtJSAjaxComplete(20);
		clickAddToSelectionButton(dialogId);
		waitForExtJSAjaxComplete(20);
		
//		expandNode("div", "@id", dialogId, floor);
//		waitForExtJSAjaxComplete(20);
//		Tree.checkNodeInTreeByTextValue(driver, floor);
//		Reporter.log("Select "+floor+" node", true);
//		waitForExtJSAjaxComplete(20);
//		clickAddToSelectionButton(dialogId);
//		waitForExtJSAjaxComplete(20);
		
//		expandNode("div", "@id", dialogId, room);
//		waitForExtJSAjaxComplete(20);
//		Tree.checkNodeInTreeByTextValue(driver, room);
//		Reporter.log("Select "+room+" node", true);
//		waitForExtJSAjaxComplete(20);
//		clickAddToSelectionButton(dialogId);
//		waitForExtJSAjaxComplete(20);
//		
//		expandNode("div", "@id", dialogId, land);
//		waitForExtJSAjaxComplete(20);
//		Tree.checkNodeInTreeByTextValue(driver, outside);
//		Reporter.log("Select "+outside+" node", true);
//		waitForExtJSAjaxComplete(20);
//		clickAddToSelectionButton(dialogId);
//		waitForExtJSAjaxComplete(20);
		
		clickOkXwindow();		
	}
	
	public void clickRefreshButton() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", GAUGE_GRID_CLASS, "button", 
				"@class", "x-tbar-loading", true, true).click();
		Reporter.log("Click Refresh button on the overview", true);
	}
	
	//FIXME: Method allows to check if row with one or some values is present in grid without specifying cells attribute
	public static boolean isRowInGridPresent(WebDriver webDriver, String textValue, String parentAttr, String parentAttrValue) {
		
		String[] splitted = null;
		
		if (textValue.contains("|")) {
			splitted = textValue.split("\\|");
		} else {
			splitted = new String[] {textValue};
		}
			
		String xpath= "//div[contains(" +parentAttr +",'"+parentAttrValue+"')]//div[contains(@class, 'x-grid3')]"+ "//*[text()='"+splitted[0]+"']";
		
		for (int i=1; i<splitted.length; i++){
			xpath = xpath + "//..//..//*[text()='"+splitted[i]+"']";
			}
		
		
		try {
			webDriver.findElement(By.xpath(xpath));
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	//FIXME: Method allows to check if row with one or some values is present in grid with specifying cells attribute
	public static boolean isRowInGridPresent(WebDriver webDriver, String textValue, String parentAttr, String parentAttrValue, String cellAtribute) {
		
		String[] splitted = null;
		
		if (textValue.contains("|")) {
			splitted = textValue.split("\\|");
		} else {
			splitted = new String[] {textValue};
		}
			
		String xpath= "//div[contains(" +parentAttr +",'"+parentAttrValue+"')]//div[contains(@class, 'x-grid3')]"+ "//"+cellAtribute+"[text()='"+splitted[0]+"']";
		
		for (int i=1; i<splitted.length; i++){
			xpath = xpath + "//..//..//"+cellAtribute+"[text()='"+splitted[i]+"']";
			}
		
		
		try {
			webDriver.findElement(By.xpath(xpath));
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static WebElement isRowInGridPresentAndClick(WebDriver webDriver, String textValue, String cellAtribute)  {
		String[] splitted = null;
		
		if (textValue.contains("|")) {
			splitted = textValue.split("\\|");
		} else {
			splitted = new String[] {textValue};
		}
		
		String xpath= "//div[contains(@class,'"+GAUGE_GRID_CLASS+"')]//div[contains(@class, 'x-grid3')]"+ "//"+cellAtribute+"[contains(text(), '"+splitted[0]+"')]";
		
		for (int i=1; i<splitted.length; i++){
			xpath = xpath + "//..//..//"+cellAtribute+"[text()='"+splitted[i]+"']";
			}
			
		WebElement webElement = webDriver.findElement(By.xpath(xpath));
		webElement.click();
		Reporter.log("Check if element "+textValue+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;

	}
	
	public String getCommissioningDate() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", DIALOG_GAUGE, "input",
				"@name", "commissioningDate", true, true).getAttribute("value");
	}
	
	public String getSerialNumber() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", DIALOG_GAUGE, "input",
				"@name", "serialNumber", true, true).getAttribute("value");
	}
	
	public String getManufacturer() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", DIALOG_GAUGE, "input",
				"@name", "manufacturer", true, true).getAttribute("value");
	}
	
	public String getDescription() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", DIALOG_GAUGE, "textarea",
				"@name", "description", true, true).getAttribute("value");
	}
	
	public String getStatus() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+DIALOG_GAUGE+"')]//input[@name='status']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public String getTimeZone() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+DIALOG_GAUGE+"')]//input[@name='timezone']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public String getLocation() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+DIALOG_GAUGE+"')]//input[@name='location']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public String getModel() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", DIALOG_GAUGE, "input",
				"@name", "model", true, true).getAttribute("value");
	}
	
	public String getAccessInstructions() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", DIALOG_GAUGE, "textarea",
				"@name", "accessDirectives", true, true).getAttribute("value");
	}
	
	public void changeTab(String tabName) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"li", "@id", tabName+"-tab", "span", "@class", "x-tab-strip-text", true, true).click();
		Reporter.log("change tab to "+tabName, true);
	}
	
	public WebElement checkRowInGriByTextValueAndClick(WebDriver webDriver, String textValue)  {
		WebElement webElement = webDriver.findElement(By.xpath("//div[contains(@class, 'slnmGaugeId')]//div[text()='"+textValue+"']"));
		webElement.click();
		Reporter.log("Check element "+textValue+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	
	public void clickButton(String buttonName, String dialogClass)
	{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
					"div", "@class", dialogClass, "button", "@class", "x-btn-text",
					"text()", buttonName, true, true).click();
			Reporter.log("Click "+buttonName+" button", true);
	}
	
	public String getReference() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", DIALOG_GAUGE_CHNL, "input",
				"@name", "reference", true, true).getAttribute("value");
	}
	
	public String getCalibrationDate() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", DIALOG_GAUGE_CHNL, "input",
				"@name", "calibrationDate", true, true).getAttribute("value");
	}
	
	public String getChannelParameter() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'slnmGaugeChnlId')]//input[@name='channelParameter']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public String getUnitOfMeasure() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'slnmGaugeChnlId')]//input[@name='unitOfMeasure']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public String getEntryMethod() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", DIALOG_GAUGE_CHNL, "input",
				"@name", "entryMethod", true, true).getAttribute("value");
	}
	
	public String getSmallestReportingInterval() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", DIALOG_GAUGE_CHNL, "input",
				"@name", "readingInterval", true, true).getAttribute("value");
	}
	
	public String getCalculationMethod() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", DIALOG_GAUGE_CHNL, "input",
				"@name", "calculationMethod", true, true).getAttribute("value");
	}
	
	public void changeTabForBuildings(String tabName){
		McsElement
				.getElementByXpath(
						driver,
						"//div[contains(@class, 'x-panel-noborder x-border-panel')]//div[@class='x-box-inner']//div[contains(@class,'x-tab-strip-wrap')]//li//span[contains(@class,'x-tab-strip-text') and text()='Gauges']")
				.click();
		Reporter.log("change tab to "+tabName, true);
	}
	
	public void checkNodeInTreeByTextValue(String nodeText) {
		WebElement webElement = driver.findElement(By
				.xpath("//div[contains(@class, 'x-resizable-pinned') and contains(@style, 'visibility: visible')]//div[contains(., '"+nodeText+"') and contains(@class,'x-tree-node-el')]"));
		webElement.click();
		waitForExtJSAjaxComplete(10);
	}

}
