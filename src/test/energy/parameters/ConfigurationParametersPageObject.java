package test.energy.parameters;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class ConfigurationParametersPageObject extends AbstractMcsTestSuite {

	protected final String ADMINISTRATION_PANEL_CLASS = "x-panel-body x-panel-body-noborder";

	protected final String XPATH_ADD_FORM_WINDOW = "slnmParametersId";
	
	protected final String PANEL_NAME = "Parameters";
	
	protected final String GAUGE_GRID_CLASS = "x-tab-panel x-tab-panel-noborder";
	
	protected final String ADD_WEATHERSTATIONS_FORM_CLASS = "slnmWSId";
	
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
		Reporter.log("Open Configuration " + entity, true);
	}

	public void clickAddButton() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//span[text()='"+ PANEL_NAME +"']//..//..//button[text()='Add']").click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click add "+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void clickEditButton() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//span[text()='"+ PANEL_NAME +"']//..//..//button[text()='Edit']").click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click edit "+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void clickDeleteButton() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//span[text()='"+ PANEL_NAME +"']//..//..//button[text()='Delete']").click();
		waitForExtJSAjaxComplete(10);
		clickOnDialogButton("OK");
		Reporter.log("Click Delete button" + " (" + timer.stop() + "ms)", true);
	}
	
	public void clickAddButton(String gridId) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@realid", gridId, "button", "@class", "x-btn-text",
				"text()", "Add", true, true).click();
		Reporter.log("Click Add button", true);
	}

	public void clickEditButton(String gridId) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@realid", gridId, "button", "@class", "x-btn-text",
				"text()", "Edit", true, true).click();
		Reporter.log("Click Edit button", true);
	}

	public void clickDeleteButton(String gridId) {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@realid", gridId, "button", "@class", "x-btn-text",
				"text()", "Delete", true, true).click();
		clickOnDialogButton("OK");
		Reporter.log("Click Delete button" + " (" + timer.stop() + "ms)", true);
	}

	public void setCode(String code) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"code", true, true).clear();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"code", true, true).sendKeys(code);
		Reporter.log("Set Code " + code, true);
	}

	public String getCode() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "code", true, true).getAttribute("value");
	}

	public String getName() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "name", true, true).getAttribute("value");
	}

	public void setName(String reference) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"name", true, true).clear();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"name", true, true).sendKeys(reference);
		Reporter.log("Set Name " + reference, true);
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
		Reporter.log("Set Description " + reference, true);
	}

	public String getClassValue(String attribute) {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "class", true, true).getAttribute(attribute);
	}
	
	//trunk 14.0
	public void setClassValue(String elementValue) {
		String id =  McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "class", true, true).getAttribute("id");

		DropDown.setExtJsComboValue(driver, id, elementValue);
		Reporter.log("energy Level " + elementValue, true);
	}

	public void saveClose() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW, "button", "text()",
				"Save and Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close is clicked", true);
	}

	public void close() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW, "button", "text()",
				"Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log(" Close is clicked", true);
	}
	
	public void save() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW, "button", "text()",
				"Save", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save is clicked", true);
	}
	
	public void setParamClass(String paramClass) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='class']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, paramClass);
		Reporter.log("Set Parameter Class - "+paramClass, true);
	}
	
	public void setCalculationMethod(String paramClass) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='calculationMethod']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, paramClass);
		Reporter.log("Set calculation Method - "+paramClass, true);
	}
	
	public void setUOMClass(String uomClass){
		clickLookup(XPATH_ADD_FORM_WINDOW, "uomClass");
		setValueGridLookup(uomClass);
		Reporter.log("Set UOM Class - "+uomClass, true);
	}

	public void setDefaultUOM(String uomClass){
		clickLookup(XPATH_ADD_FORM_WINDOW, "defaultUom");
		setValueGridLookupWithFilters(uomClass,"Reference");
		Reporter.log("Set default UOM - "+uomClass, true);
	}

	public String getFieldValue(String fieldName) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='"+fieldName+"']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void clickCancelXwindow() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-window-footer x-window-footer-noborder x-panel-btns", "button",
				"text()", "Cancel", true, true).click();
		//waitForElementDisappear("//div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]");
		Reporter.log("Click Cancel", true);
	}
	
	public void clickSavelXwindow() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-window-footer x-window-footer-noborder x-panel-btns", "button",
				"text()", "Save and Close", true, true).click();
		Reporter.log("Click Cancel", true);
	}

	
	public void clearField(String fieldNameTag, String fieldName) {
		WebElement field =  driver.findElement
				(By.xpath("(//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//input[contains(@name, '"+fieldNameTag+"')]//..//input)[last()]"));
		field.click();
		field.clear();
		field.click();
		Reporter.log("Clear "+fieldName+" field", true);
	 }
	
	 public boolean verifyItemExists(String inputName, String ddItem) {
			String elementId = driver.findElement(By
					.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='" + inputName + "']")).getAttribute("Id");
			return DropDown.isExtJsComboValuePresent(driver, elementId, ddItem);	
	}
	 
	 public boolean checkInputDisabled(String elementName){
		 driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
			try {
				driver.findElement(By.xpath("//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='" + elementName + "']//..//input[contains(@class, 'readonly')]"));
				return true;
			} catch (Exception e) {
				return false;
			}
		 finally{
			 driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
		 }
			
	}
		
	/*public boolean checkChannelInputDisabled(String elementName){	
		try {
			driver.findElement(By.xpath("//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='" + elementName + "']//..//input[contains(@class, 'readonly')]"));
			return true;
		} catch (Exception e) {
			return false;
		}
			
	}*/
	
	public boolean checkMandatoryFieldSave(String dialog){
		return driver.findElement(
					By.xpath("//div[contains(@class,'"+dialog+"')]//span[contains(@class,'window-header')]")).getText().contains("Add");
		
	}
	
	//Reopen Weather Station Form being either saved or not
	@SuppressWarnings("finally")
	public boolean reopenParameterForm(){
		
		if (!checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW)) {
				 
			close(XPATH_ADD_FORM_WINDOW);
				 
			waitForExtJSAjaxComplete(20);
				 
			clickAddButton();
					
			waitForExtJSAjaxComplete(20);

			return false;
		}
		 else 
		{
				 
		 close();
			 
		 waitForExtJSAjaxComplete(20);
			 
		 try { clickOnDialogButton("Yes"); }
			 
		 //catch (Exception e) {}
			 
		 finally {
			 
			 waitForExtJSAjaxComplete(20);
		     
			 clickAddButton();
				
			 waitForExtJSAjaxComplete(20);
			 
			 return true;
				 
			}
				 	 
		}
	}
	
	public void close(String dialogClass) {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", dialogClass, "button", "text()",
				"Close", true, true).click();
		Reporter.log("Click Close button", true);
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
	
	public void clickButton(String buttonName, String dialogClass)
	{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", dialogClass, "button", "@class", "x-btn-text",
				"text()", buttonName, true, true).click();
		Reporter.log("Click "+buttonName+" button", true);
	}
	
	public void saveClose(String dialogClass) {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", dialogClass, "button", "text()",
				"Save and Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close is clicked", true);
	}
	
	public String getFieldValue(String fieldName, String attribute) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='"+fieldName+"']//..//input)[last()]"))
				.getAttribute(attribute);
	}
	
	/**
	 * Helper method to get Values from Class Drop down
	 */
	public List<String> getClassDropDownValues(){
		WebElement classDropDown = driver.findElement(By.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='class']"));
		
		classDropDown.click();
		
		List<WebElement> values = driver.findElements(By.xpath("//div[contains(@class, 'x-combo-list') and (contains(@style, 'visibility: visible'))]//div[contains(@class,'x-combo-list-item')]"));
					
		ArrayList<String> lsValues = new ArrayList<String>(); 
				
		for(int i=0; i<values.size(); i++){
					
		String valueText = values.get(i).getText().trim();
					
		lsValues.add(valueText);
		}
		
		return lsValues;
	}
	
	/**
	 * Helper method to identify Locators in a webpage
	 */
	public int testLocators(){
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class, 'slnmGaugeId')]//input[contains(@name, 'model')]"));
		String eleStr = ele.getAttribute("className");
		
		return Integer.parseInt(eleStr);
	}
	
	public void filterGridScrollIntoView(String attribute, String attributeValue, String rowTextName, String columnName) {
		Timer timer = new Timer().start();
		
		WebElement element = driver.findElement(By.xpath("//div[contains("+attribute+",'"+ attributeValue+"')]//div[contains(@class,'quickfilters')]"
						+ "//div[contains(@class,'x-grid3-hd') and contains(text(),'"+columnName+"')]"));
		
		if ("chrome".equals(configuration.getBrowser())) {
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", element);
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
		
		String columnClass = McsElement
				.getElementByXpath(driver,"(//div[contains("+attribute+",'"+ attributeValue+"')]//div[contains(@class,'quickfilters')]"
						+ "//div[contains(@class,'x-grid3-hd') and contains(text(),'"+columnName+"')])[last()]").getAttribute("class");
		
		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
		

		WebElement filterInput = McsElement.getElementByXpath(driver, "(//div[contains("+attribute+",'"+attributeValue+"')]//"
					+ "input[contains(@id,'filter-editor-"+columnNumber+"')])[last()]"); 
					
			filterInput.clear();
			
			filterInput.sendKeys(rowTextName);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			driver.findElement(By.xpath("//div[contains("+attribute+",'"+attributeValue+"')]//"
						+ "div[contains(@class,'x-grid3-body')][last()]/div")).click();
			
			/* McsElement.getElementByXpath(driver, "(//div[contains("+attribute+",'"+attributeValue+"')]//"
						+ "div[contains(@class,'x-grid3-body')])[last()]/div").click(); */
	

			waitForExtJSAjaxComplete(5);

			Reporter.log(rowTextName + " was filtered"+ " (" + timer.stop()
			+ "ms)", true);

	}
}
