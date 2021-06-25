package test.energy.metrics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import test.configuration.Configuration;
import test.energy.EnergyBaseTestSuite;
import test.framework.AbstractMcsTestSuite;
import test.framework.SoftAssert;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class MetricsPageObject extends EnergyBaseTestSuite {
	
	
	
	protected final String METRIC_GRID = "x-tab-panel x-tab-panel-noborder";

	protected final String ADD_FORM_WINDOW_XPATH = "slnmMetricID";
	
	public MetricsPageObject(){
		this.FORM_CLASS="slnmMetricID";
		this.OVERVIEW_GRID_CLASS="x-tab-panel x-tab-panel-noborder";
		this.softAssert = new SoftAssert();
	}
	
	public void expandConfiguration() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"span", "text()", "Configuration", true, true).click();
		Reporter.log("Expand configuration", true);
	}

	public void openConfigurationEntity(String entity) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"button", "text()", entity, true, true).click();
		Reporter.log("open configuration " + entity, true);
	}

	public void clickAddButton() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//div[contains(@class, '"+METRIC_GRID+"')]//button[text()='Add']").click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click add "+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void clickEditButton() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//div[contains(@class, '"+METRIC_GRID+"')]//button[text()='Edit']").click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click edit "+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void clickDeleteButton() {
		McsElement.getElementByXpath(driver, "//div[contains(@class, '"+METRIC_GRID+"')]//button[text()='Delete']").click();
		waitForExtJSAjaxComplete(10);
		clickOnDialogButton("Yes");
		Reporter.log("Delete selected entity", true);
	}

	public void setReference(String reference) {	
		WebElement field = McsElement.getElementByXpath(driver, "//div[contains(@class, '"+ADD_FORM_WINDOW_XPATH+"')]//input[@name='reference']");
		field.clear();
		field.sendKeys(reference);
		Reporter.log("Set code", true);
	}

	public String getReference(String attributeValue) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class, '" + ADD_FORM_WINDOW_XPATH + "')]//input[@name='reference']//..//input)[last()]"))
				.getAttribute(attributeValue);
	}
	
	public void setDimension(String dimension) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+ADD_FORM_WINDOW_XPATH+"')]//input[@name='dimension']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, dimension);
		Reporter.log("Set Dimension - "+dimension, true);
	}
	
	public String getDimension(String attributeValue) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class, '" + ADD_FORM_WINDOW_XPATH + "')]//input[@name='dimension']//..//input)[last()]"))
				.getAttribute(attributeValue);
	}
	
	public void setCommodityClass(String commodityClass) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+ADD_FORM_WINDOW_XPATH+"')]//input[@name='commoditySuperClass']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, commodityClass);
		Reporter.log("Set Commodity Class - "+commodityClass, true);
	}
	
	public String getCommodityClass(String attributeValue) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class, '" + ADD_FORM_WINDOW_XPATH + "')]//input[@name='commoditySuperClass']//..//input)[last()]"))
				.getAttribute(attributeValue);
	}
	
	public void setCommodityEndUse(String commodityEndUse) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+ADD_FORM_WINDOW_XPATH+"')]//input[@name='commodityEndUse']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, commodityEndUse);
		Reporter.log("Set Commodity End Use - "+commodityEndUse, true);
	}
	
	public String getCommodityEndUse(String attributeValue) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class, '" + ADD_FORM_WINDOW_XPATH + "')]//input[@name='commodityEndUse']//..//input)[last()]"))
				.getAttribute(attributeValue);
	}
	
	public void setParameter(String parameter) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+ADD_FORM_WINDOW_XPATH+"')]//input[@name='denominator']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, parameter);
		Reporter.log("Set Parameter - "+parameter, true);
	}
	
	public String getParameter(String attributeValue) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class, '" + ADD_FORM_WINDOW_XPATH + "')]//input[@name='denominator']//..//input)[last()]"))
				.getAttribute(attributeValue);
	}
	
	public void setNominatorUOM(String nominatorUom){
		clickLookup(ADD_FORM_WINDOW_XPATH, "nominatorUom");
		setValueGridLookupWithFilters(nominatorUom, "Code");
		Reporter.log("Set UOM - "+nominatorUom, true);
	}
	
	public String getNominatorUOM(String attributeValue) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class, '" + ADD_FORM_WINDOW_XPATH + "')]//input[@name='nominatorUom']//..//input)[last()]"))
				.getAttribute(attributeValue);
	}
	
	public void setDenominatorUOM(String denominatorUom){
		clickLookup(ADD_FORM_WINDOW_XPATH, "denominatorUom");
		setValueGridLookupWithFilters(denominatorUom, "Code");
		Reporter.log("Set UOM - "+denominatorUom, true);
	}
	
	public String getDenominatorUOM(String attributeValue) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class, '" + ADD_FORM_WINDOW_XPATH + "')]//input[@name='denominatorUom']//..//input)[last()]"))
				.getAttribute(attributeValue);
	}
	
	public void saveClose() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADD_FORM_WINDOW_XPATH, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		Reporter.log("Save and Close", true);
	}
	
	public void save() {
		McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", ADD_FORM_WINDOW_XPATH, "button",
				"text()", "Save", true, true).click();
		Reporter.log("Click Save button", true);
	}

	public void close() {
		driver
		.findElement(
				By.xpath("(//div[contains(@class,'"+ADD_FORM_WINDOW_XPATH+"')]//button[text()='Close'])[last()]")).click();
		Reporter.log("Click Close button", true);
	}
	
	public boolean verifyItemExists(String inputName, String ddItem) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+ADD_FORM_WINDOW_XPATH+"')]//input[@name='" + inputName + "']")).getAttribute("Id");
		return DropDown.isExtJsComboValuePresent(driver, elementId, ddItem);	
	}
	
	public boolean filterGridAndSearchForValue(String attribute, String attributeValue, String rowTextName, String columnName) {
			
			String columnClass = McsElement
			.getElementByPartAttributeValueAndParentElement(driver,
					"div", attribute, attributeValue,
					"div","@class", "x-grid3-hd",
					"text()", columnName, true, true).getAttribute("class");
			
			String columnNumber = columnClass.substring(columnClass.length() - 1);
			
				WebElement filterInput = McsElement
						.getElementByPartAttributeValueAndParentElement(driver,
								"div", attribute, attributeValue, "input",
								"@id", "filter-editor-"+columnNumber, true, true);
				
				filterInput.clear();
				
				filterInput.sendKeys(rowTextName);
				
				McsElement
				.getElementByPartAttributeValueAndParentElement(driver,
						"div", attribute, attributeValue, "div",
						"@class", "x-grid3-body", true, true).click();			
				waitForExtJSAjaxComplete(20);
	
				try {
					driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
					driver.findElement(By.xpath("//div[contains("+attribute+",'"+attributeValue+"')]"
							+ "//div[contains(@class, 'x-grid3-cell-inner x-grid3-col-"+columnNumber+"') and text()='"+rowTextName+"']")).click();;
					return true;
				}
				catch(Exception e){
					return false;
				}
				 finally {
						try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);
						} catch (Exception e) {}
					}
	
	}

	public void clickCancelXwindow() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-window-footer x-window-footer-noborder x-panel-btns", "button",
				"text()", "Cancel", true, true).click();
		waitForElementDisappear("//div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]");
		Reporter.log("Click Cancel", true);
	}
	
	public boolean isDataLabelPresent(String labelMessage) {
		try {
			driver.findElement(By.xpath("//div[contains(@class,'" + ADD_FORM_WINDOW_XPATH + "')]//label[contains(text(),'" + labelMessage + "')]"));
			//Reporter.log("click edit button " + textLabel, true);
			return true;
		} catch (Exception e) {
		return false;
		}
	}
	
	/**
	 * Helper method to open a record in Transaction Line
	 */
	public void openTransactionLine(String attribute, String attributeValue, String rowTextName, String columnName){
		waitForExtJSAjaxComplete(25);
	
		filterGrid(attribute, attributeValue, rowTextName, columnName);
		
		waitForExtJSAjaxComplete(25);
		
		WebElement ele = Grid.checkRowInGriByTextValue(driver, rowTextName); 
		
		new Actions(driver).doubleClick(ele).perform();
		
		waitForExtJSAjaxComplete(25);
	}
	
	/**
	 * Helper method to verify All Fields are UnEditable
	 */
	public void verifyEditablityOfFields() {
		
		softAssert.assertTrue(getReference("class").contains("readonly"), "Reference Field is Not Editable");
		
		softAssert.assertTrue(getDimension("class").contains("readonly"), "Dimension Field is Not Editable");
		
		softAssert.assertTrue(getCommodityClass("class").contains("readonly"), "Commodity Class Field is Not Editable");
		
		softAssert.assertTrue(getCommodityEndUse("class").contains("readonly"), "Commodity End use Field is Not Editable");
		
		softAssert.assertTrue(getNominatorUOM("class").contains("readonly"), "Nominator UOM Field is Not Editable");
		
		softAssert.assertTrue(getParameter("class").contains("readonly"), "Parameter Field is Not Editable");
	
		softAssert.assertTrue(getDenominatorUOM("class").contains("readonly"), "Denominator UOM Field is Not Editable");
	}
}
