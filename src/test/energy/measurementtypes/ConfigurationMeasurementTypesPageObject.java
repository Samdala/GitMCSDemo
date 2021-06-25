package test.energy.measurementtypes;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.energy.EnergyBaseTestSuite;
import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.McsElement;

public class ConfigurationMeasurementTypesPageObject extends EnergyBaseTestSuite {

	protected final String ADMINISTRATION_PANEL_CLASS = "x-panel-body x-panel-body-noborder";

	protected final String XPATH_ADD_FORM_WINDOW = "slnmMeasurementTypeId";

	protected final String XPATH_ADD_FORM_WINDOW_FOOTER = "slnmMeasurementTypeId";
	
	protected final String ADD_MEASUREMENT_TYPE_WINDOW_HEADER = "Add a Measurement Type";
	
	protected final String ADD_MEASUREMENT_TYPE_WINDOW_HEADER_TRUNK = "Add Measurement Type";
	
	protected final String EDIT_MEASUREMENT_TYPE_WINDOW_HEADER = "Edit the Measurement Type";
	
	protected final String XPATH_GRID_PANEL = "x-panel-noborder x-grid-panel";

	public void goToDefinition(String definition) {
		driver.get(configuration.getApplicationUrl() + "/frame.php?relay="
				+ definition);
	};


	/**
	 * Expand configuration module
	 */
	public void expandConfiguration() {
		try {
			McsElement.getElementByPartAttributeValueAndParentElement(driver,
					"div", "@class", "slnmNrgMenu",
					"span", "text()", "Configuration", true, true).click();
			Reporter.log("Expand configuration", true);
		} catch (Exception e) {
			Reporter.log("ERROR: Can't Expand configuration", true);
			throw e;
		}
	}

	/**
	 * Select in Configuration some entity
	 * 
	 * @param entity
	 *            - text of entity name
	 */
	public void openConfigurationEntity(String entity) {
		try {
			McsElement.getElementByPartAttributeValueAndParentElement(driver,
					"div", "@class", "slnmNrgMenu",
					"button", "text()", entity, true, true).click();
			Reporter.log("open configuration " + entity, true);
		} catch (Exception e) {
			Reporter.log("ERROR: Can't open configuration " + entity, true);
			throw e;
		}
	}	

	/**
	 * Click on Measurement Types create button
	 * 
	 * @param gridId - "measurement_types_grid"
	 */
	public void clickAddButton() {
		/*try {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_GRID_PANEL, "button", "@class", "x-btn-text",
				"text()", "Add", true, true).click();
		Reporter.log("click add button", true);}
		catch(Exception e){
			Reporter.log("ERROR: Can't click Add button for measurement types", true);
			throw e;
		}*/
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class, '"+XPATH_GRID_PANEL+"')]//button[contains(@class, 'x-btn-text') and contains(text(), 'Add')]"));
		ele.click();
		Reporter.log("Add button clicked in MEasurement Types", true);
	}

	/**
	 * Click on Measurement Types edit button
	 * 
	 * @param gridId - "measurement_types_grid"
	 */
	public void clickEditButton() {
		try {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_GRID_PANEL, "button", "@class", "x-btn-text",
				"text()", "Edit", true, true).click();
		Reporter.log("click edit button", true);}
		catch(Exception e){
			Reporter.log("ERROR: Can't click Edit button for measurement types", true);
			throw e;
		}
	}

	
	/**
	 * Click on Measurement Types delete button
	 * 
	 * @param gridId - "measurement_types_grid"
	 */
	public void clickDeleteButton() {
		try {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_GRID_PANEL, "button", "@class", "x-btn-text",
				"text()", "Delete", true, true).click();
		clickOnDialogButton("OK");
		Reporter.log("Click delete button" + " (" + timer.stop() + "ms)", true);}
		catch(Exception e){
			Reporter.log("ERROR: Can't click Delete button for measurement types", true);
			throw e;	
		}
	}

	
	/**
	 * Set measurement type code
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		try{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"code", true, true).clear();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"code", true, true).sendKeys(code);
		Reporter.log("set code " + code, true);}
		catch(Exception e){
			Reporter.log("ERROR: Can't set code " + code, true);
		throw e;
		}
	}

	/**
	 * Get measurement type code field value
	 * 
	 * @return measurement type code
	 */
	public String getCode() {
		try{
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "code", true, true).getAttribute("value");}
		catch(Exception e){
			Reporter.log("ERROR: Can't get code ", true);
			throw e;
		}
	}

	/**
	 * Get measurement type reference field value
	 * 
	 * @return measurement type reference
	 */
	public String getReference() {
		try {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "reference", true, true).getAttribute("value");}
		catch(Exception e){
			Reporter.log("ERROR: Can't get refrerence ", true);
			throw e;
		}
	}

	/**
	 * Set reference
	 * 
	 * @param reference 
	 */
	public void setReference(String reference) {
		try {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"reference", true, true).clear();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"reference", true, true).sendKeys(reference);
		Reporter.log("set reference " + reference, true);}
		catch(Exception e){
			Reporter.log("ERROR: Can't set reference "+ reference, true);
			throw e;
		}
	}


	/**
	 * Click on default checkbox
	 */
	public void checkDefault() {
		try{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"isDefault", true, true).click();
//		clickOnDialogButton("Yes");
		Reporter.log("set default yes", true);}
		catch (Exception e){
			Reporter.log("ERROR: Can't click on Default checkbox ", true);
			throw e;
		}
	}

	/**
	 * Get default checkbox state
	 * 
	 * @return true if checked, false if uncheked
	 */
	public boolean getDefaultState() {
		try {
		if (McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"isDefault", true, true).getAttribute("checked") != null) {
			return true;
		}
		return false;}
		catch(Exception e){
			Reporter.log("ERROR: Can't get state of Default checkbox ", true);
			throw e;
		}
	}

	/**
	 * Save And Close Measurement Type window form
	 */
	public void saveClose() {
		try {
		waitForExtJSAjaxComplete(25);
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW_FOOTER, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		waitForExtJSAjaxComplete(25);}
		catch(Exception e) {
			Reporter.log("ERROR: Can't save and close type window form ", true);
			throw e;
		}
	}
	
	/**
	 * Save Measurement Type window form
	 */
	public void save() {
		try {
		waitForExtJSAjaxComplete(25);
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW_FOOTER, "button",
				"text()", "Save", true, true).click();
		waitForExtJSAjaxComplete(25);}
		catch(Exception e) {
			Reporter.log("ERROR: Can't save type window form ", true);
			throw e;
		}
	}

	/**
	 * Close Measurement Type window form
	 */
	public void close() {
		try{
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW_FOOTER, "button", "text()",
				"Close", true, true).click();
		waitForExtJSAjaxComplete(25);
	}
		catch(Exception e) {
			Reporter.log("ERROR: Can't close type window form ", true);
			throw e;
		}
		
	}
	
	public String getFieldValue(String xwindowTitle, String fieldName) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@id,'"+getXWindowId(xwindowTitle)+"')]//input[@name='"+fieldName+"']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public boolean getCheckBoxState(String xwindowTitle, String name) {
		  if (McsElement.getElementByPartAttributeValueAndParentElement(driver,
		    "div", "@id", getXWindowId(xwindowTitle), "input", "@name",
		    name, true, true).getAttribute("checked") != null) {
		   return true;
		  }
		  return false;
	}
	
	/**
	 *  Helper method to get column number in Grid 
	 */
	public int getGridColumnNumber(String attribute, String attributeValue, String columnName){
		
		String columnClass = McsElement.getElementByXpath(driver,"(//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'quickfilters')]//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')])").getAttribute("class");
		String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);
		return Integer.parseInt(columnNumber);
	}
	
	/**
	 * Helper method to set value for Default in Grid
	 */
	public int verifyOnlyOneRowIsDefauted(String columnName) {
		
		int colNo = getGridColumnNumber("@class", "energy x-panel-noborder", columnName);
		
		List<WebElement> values = driver.findElements(By.xpath("//div[contains(@class, 'energy x-panel-noborder') and not (contains(@class, 'x-hide-display'))]//div[@class='x-grid3-body']//div[contains(@class, 'x-grid3-col-"+colNo+"')]/div"));
		
		int count= 0;
		
		for(int i=0; i<values.size(); i++){
		String valueText = values.get(i).getAttribute("class");

			if (valueText.contains("x-grid3-check-col-on")) {
				count++;
			}
		
		}
		
		waitForExtJSAjaxComplete(25);
		return count;
	}
	
	/**
	 * Helper method to get Built In status
	 */
	public boolean getBuiltInStatus(String rowTextName, String colName, String lineIdColName){
		Timer timer = new Timer().start();
		
		waitForExtJSAjaxComplete(25);
		
		filterGrid("@class", "energy x-panel-noborder", rowTextName, colName);
		
		waitForExtJSAjaxComplete(25);
		
		boolean status = false;
	
		int lineIDColumnNumber = getGridColumnNumber("@class", "energy x-panel-noborder", lineIdColName);
		
		WebElement element = driver.findElement(By.xpath("//div[contains(@class, 'energy x-panel-noborder') and not (contains(@class, 'x-hide-display'))]//div[@class='x-grid3-body']//div[contains(@class, 'x-grid3-col-"+lineIDColumnNumber+"')]/div"));
		
		String valueText = element.getAttribute("class");

			if (valueText.contains("x-grid3-check-col-on")) {
				status = true;
			}
		
		timer.stop();
		
		filterGrid("@class", "energy x-panel-noborder", "", colName);
		
		return status;
	}
}
