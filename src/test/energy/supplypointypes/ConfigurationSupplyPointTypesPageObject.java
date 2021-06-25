package test.energy.supplypointypes;

//import java.util.concurrent.TimeUnit;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class ConfigurationSupplyPointTypesPageObject extends AbstractMcsTestSuite {

	protected final String ADMINISTRATION_PANEL_CLASS = "x-panel-body x-panel-body-noborder";

	protected final String XPATH_ADD_FORM_WINDOW = "slnmSpTypeId";

	protected final String XPATH_ADD_FORM_WINDOW_FOOTER = "slnmSpTypeId";
	
	protected final String ADD_SUPPLY_POINT_WINDOW_HEADER = "Add Supply Point Type";
	
	protected final String EDIT_SUPPLY_POINT_WINDOW_HEADER = "Edit Supply Point Type";

	public void goToDefinition(String definition) {
		driver.get(configuration.getApplicationUrl() + "/frame.php?relay="
				+ definition);
	};

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

	public void clickAddButton(String gridId) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@realid", gridId, "button", "@class", "x-btn-text",
				"text()", "Add", true, true).click();
		Reporter.log("click add button", true);
	}

	public void clickEditButton(String gridId) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@realid", gridId, "button", "@class", "x-btn-text",
				"text()", "Edit", true, true).click();
		Reporter.log("click edit button", true);
	}

	public void clickDeleteButton(String gridId) {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@realid", gridId, "button", "@class", "x-btn-text",
				"text()", "Delete", true, true).click();
		clickOnDialogButton("OK");
		Reporter.log("Click delete button" + " (" + timer.stop() + "ms)", true);
	}

	public void setCode(String code) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"code", true, true).clear();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"code", true, true).sendKeys(code);
		Reporter.log("set code " + code, true);
	}

	public String getCode(String windowTitle, String attributeValue) {
		String code = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//input[@name='code']")).getAttribute(attributeValue);
		
		return code;
		
		/*return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "code", true, true).getAttribute("value");*/
	}

	public String getReference(String windowTitle, String attributeValue) {
		String reference = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//input[@name='reference']")).getAttribute(attributeValue);
		
		return reference;
		
		/*return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "reference", true, true).getAttribute("value");*/
	}

	public void setReference(String reference) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"reference", true, true).clear();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"reference", true, true).sendKeys(reference);
		Reporter.log("set reference " + reference, true);
	}

	public String getDescription() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "textarea",
				"@name", "description", true, true).getAttribute("value");
	}

	public void setDescription(String reference) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "textarea", "@name",
				"description", true, true).clear();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "textarea", "@name",
				"description", true, true).sendKeys(reference);
		Reporter.log("set description " + reference, true);
	}

	public void setClassValueJavascript(String className) {
		String id = McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "class", true, true).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, className);
		Reporter.log("set class " + className, true);
	}

	public void setClassValue(String className) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"class", true, true).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		McsElement.getElementByPartAttributeValue(driver, "div", "@class",
				"x-combo-list-item", "text()", className, true, true).click();
		Reporter.log("set class " + className, true);
	}

	public String getClassValue() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "class", true, true).getAttribute("value");
	}

	public void checkDefault() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"isDefault", true, true).click();
//		clickOnDialogButton("Yes");
		Reporter.log("set default yes", true);
	}

	public void uncheckDefault() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"isDefault", true, true).click();
		Reporter.log("uncheck default", true);
	}

	public boolean getDefaultState() {
		if (McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"isDefault", true, true).getAttribute("checked") != null) {
			return true;
		}
		return false;
	}

	public void saveClose() {
		waitForExtJSAjaxComplete(25);
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW_FOOTER, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save close button clicked", true);
	}
	
	public void save() {
		waitForExtJSAjaxComplete(25);
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW_FOOTER, "button",
				"text()", "Save", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save button clicked", true);
	}

	public void close() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW_FOOTER, "button", "text()",
				"Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("close button clicked", true);
	}
	
	public String getFieldValue(String xwindowTitle, String fieldName) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@id,'"+getXWindowId(xwindowTitle)+"')]//input[@name='"+fieldName+"']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public String getTextAreaValue(String xwindowTitle, String textAreaName) {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@id", getXWindowId(xwindowTitle), "textarea",
				"@name", textAreaName, true, true).getAttribute("value");
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
		
		WebElement element =  driver.findElement(By.xpath("//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'quickfilters')]//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')]"));
		
		if ("chrome".equals(configuration.getBrowser())) {
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", element);
            } catch (Exception e) {
            	System.out.println("Exception is G " +e);
            }
        }
		
		String columnClass = element.getAttribute("class");
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
		
		waitForExtJSAjaxComplete(25);
		
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
