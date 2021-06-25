package test.energy.measuretemplates;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.configuration.Configuration;
import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class EnergyMeasureTemplatePageObject extends AbstractMcsTestSuite {
	
	protected final String XPATH_GRID_CLASS = "x-panel x-panel-noborder x-grid-panel";

	protected final String XPATH_ADD_FORM_WINDOW = "slnmEnergyMeasureTemplate";
	
	protected final String ADD_ENERGY_MEASURE_TEMPLATE_WINDOW_HEADER = "Add Measure Template";
	
	protected final String EDIT_ENERGY_MEASURE_TEMPLATE_WINDOW_HEADER = "Edit Measure Template";
	
	
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

	public void clickAddButton(String gridId) {
		/*McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridId, "button", "@class", "x-btn-text",
				"text()", "Add", true, true).click();*/
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class, '"+gridId+"')]//button[contains(@class, 'x-btn-text') and text()='Add']"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			ele.click();
		} catch(Exception e){
			e.printStackTrace();
		}
		Reporter.log("Click Add button", true);
	}

	public void clickEditButton(String gridId) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridId, "button", "@class", "x-btn-text",
				"text()", "Edit", true, true).click();
		Reporter.log("Click Edit button", true);
	}

	public void clickDeleteButton(String gridId) {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridId, "button", "@class", "x-btn-text",
				"text()", "Delete", true, true).click();
		clickOnDialogButton("OK");
		Reporter.log("Click Delete button" + " (" + timer.stop() + "ms)", true);
	}

	public void setReference(String reference) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"reference", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(reference);
		Reporter.log("Set Reference - " + reference, true);
	}

	public String getReference() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "reference", true, true).getAttribute("value");
	}

	public void setDescription(String description) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "textarea", "@name",
				"properties_description", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(description);
		Reporter.log("Set Description - " + description, true);
	}
	
	public String getDescription() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "textarea",
				"@name", "description", true, true).getAttribute("value");
	}

	
	public void setCode(String code) {
		/*McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"code", true, true);*/
		WebElement referenceField = driver.findElement(By.xpath("//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//input[contains(@name, 'code')]"));		
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(code);
		referenceField.sendKeys(Keys.ENTER);
		waitForExtJSAjaxComplete(10);
		Reporter.log("Set Code - " + code, true);
	}
	
	public String getCode() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "code", true, true).getAttribute("value");
	}
	
	public void setMeasureType(String measureType){
		clickLookup(XPATH_ADD_FORM_WINDOW, "properties_type");
		setValueGridLookup(measureType);
		Reporter.log("Set Measure Type - " + measureType, true);
	}
	
	public String getMeasureType() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='properties_type']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setAppliesTo(String appliesTo) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='properties_appliesTo']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, appliesTo);
		Reporter.log("Set Applies To - "+appliesTo, true);
	}
	
	public String getAppliesTo() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "properties_appliesTo", true, true).getAttribute("value");
	}
	
	public void setMaintenanceObjectCategory(String maintenanceObjectCategory) {
		clickLookup(XPATH_ADD_FORM_WINDOW, "properties_maintenanceObjectCategory");
		setValueGridLookup(maintenanceObjectCategory);
		Reporter.log("Set Maintenance Object Category - "+maintenanceObjectCategory, true);
	}
	
	public String getMaintenanceObjectCategory() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='properties_maintenanceObjectCategory']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setMaintenanceObjectPartType(String maintenanceObjectPartType) {
		clickLookup(XPATH_ADD_FORM_WINDOW, "properties_maintenanceObjectPartType");
		setValueGridLookup(maintenanceObjectPartType);
		Reporter.log("Set Maintenance Object Part Type - "+maintenanceObjectPartType, true);
	}
	
	public String getMaintenanceObjectPartType() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='properties_maintenanceObjectPartType']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setExpectedLifetime(String expectedLifetime) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"inputParameters_expectedLifetime", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(expectedLifetime);
		Reporter.log("Set Expected Lifetime - " + expectedLifetime, true);
	}
	
	public String getExpectedLifetime() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "inputParameters_expectedLifetime", true, true).getAttribute("value");
	}
	
	public void setCostOfCapital(String costOfCapital) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"inputParameters_costOfCapital", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(costOfCapital);
		Reporter.log("Set Cost Of Capital - " + costOfCapital, true);
	}
	
	public String getCostOfCapital() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "inputParameters_costOfCapital", true, true).getAttribute("value");
	}
	
	public void setInvestmentFixedValue(String investmentFixedValue) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"inputParameters_investmentFixedValue", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(investmentFixedValue);
		Reporter.log("Set Investment Fixed Value - " + investmentFixedValue, true);
	}
	
	public String getInvestmentFixedValue() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "inputParameters_investmentFixedValue", true, true).getAttribute("value");
	}
	
	public void setFormulaInvestmentValue(String formulaInvestmentValue) {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"inputParameters_formulaInvestmentValue", true, true);
		referenceField.click();
		referenceField.clear();
		referenceField.sendKeys(formulaInvestmentValue);
		Reporter.log("Set Formula Investment Value- " + formulaInvestmentValue, true);
	}
	
	public String getFormulaInvestmentValue() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "inputParameters_formulaInvestmentValue", true, true).getAttribute("value");
	}
	
	public void setFixedValueCurrency(String fixedValueCurrency) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='inputParameters_fixedValueCurrency']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, fixedValueCurrency);
		Reporter.log("Set Fixed Value Currency - "+fixedValueCurrency, true);
	}
	
	public String getFixedValueCurrency() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "inputParameters_fixedValueCurrency", true, true).getAttribute("value");
	}
	
	public void setFormulaCurrency(String formulaCurrency) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='inputParameters_formulaCurrency']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, formulaCurrency);
		Reporter.log("Set Applies To - "+formulaCurrency, true);
	}
	
	public String getFormulaCurrency() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "inputParameters_formulaCurrency", true, true).getAttribute("value");
	}
	
	public void setParameter(String parameter) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='inputParameters_parameter']")).getAttribute("id");
		System.out.println(elementId);
		DropDown.setComboValueDropDownSelector(driver, elementId, parameter);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Set Parameter - "+parameter, true);
	}
	
	public String getParameter() {
		return McsElement.getElementByAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "inputParameters_parameter", true, true).getAttribute("value");
	}
	
	public void setParameterType(String parameterType) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='inputParameters_parameterType']")).getAttribute("id");
		DropDown.setExtJsComboValue(driver, elementId, parameterType);
		Reporter.log("Set Parameter Type - "+parameterType, true);
	}
	
	public String getParameterType() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "inputParameters_parameterType", false, false).getAttribute("value");
	}
	
	public void checkImpactOnElectricity() {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"impactOn_electricity", true, true);
		referenceField.click();
		Reporter.log("Check Impact on 'Electricity' checkbox", true);
	}
	
	public void checkImpactOnCombustibles() {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"impactOn_combustibles", true, true);
		referenceField.click();
		Reporter.log("Check Impact on 'Combustibles' checkbox", true);
	}
	
	public void checkImpactOnWater() {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"impactOn_water", true, true);
		referenceField.click();
		Reporter.log("Check Impact on 'Water' checkbox", true);
	}
	
	public void selectFixedPriceRadio() {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"inputParameters_isFixedPrice", true, true);
		referenceField.click();
		Reporter.log("Select 'Fixed Price' radiobutton", true);
	}
	
	public void selectUseFormulaRadio() {
		WebElement referenceField = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"inputParameters_useFormula", true, true);
		referenceField.click();
		Reporter.log("Select 'Use A Formula' radiobutton", true);
	}
	
	public void saveClose() {
		waitForExtJSAjaxComplete(25);
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save close button is clicked", true);
	}
	
	public void save() {
		waitForExtJSAjaxComplete(25);
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "button",
				"text()", "Save", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save button is clicked", true);
	}

	public void close() {
		/*McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW, "button", "text()",
				"Close", true, true).click();*/
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//button[text()='Close']"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		waitForExtJSAjaxComplete(25);
		Reporter.log("Close button is clicked", true);
	}
	
	public void clickGeneralTab()
	{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"ul", "@class", "x-tab-strip", "span", "text()", "General", true, true).click();
		Reporter.log("Click on General tab", true);
	}
	
	public void clickInputParametersTab()
	{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"ul", "@class", "x-tab-strip", "span", "text()", "Input Parameters", true, true).click();
		Reporter.log("Click on Input Parameters tab", true);
	}
	
	 public boolean getRadioOrCheckboxState(String controlName) {
		if (McsElement.getElementByPartAttributeValueAndParentElement(driver,
		  "div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
		  controlName, true, true).getAttribute("checked") != null) {
		return true;
		}
		return false;
	 }
	
	
	 public String getFieldValue(String fieldName) {
			return driver
					.findElement(
							By.xpath("(//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='"+fieldName+"']//..//input)[last()]"))
					.getAttribute("value");
		}
	 
	 public String getTextAreaFieldValue(String fieldName) {
			return driver
					.findElement(
							By.xpath("(//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//textarea[@name='"+fieldName+"'])[last()]"))
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
			Reporter.log("Clear "+fieldName+" field", true);
		 }
		
		 public boolean verifyItemExists(String inputName, String ddItem) {
				String elementId = driver.findElement(By
						.xpath("//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='" + inputName + "']")).getAttribute("Id");
				return DropDown.isExtJsComboValuePresent(driver, elementId, ddItem);	
		}
		 
		public boolean isElementOnForm(String elementName) {
			try {
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='" + elementName + "']"));
				return true;
			} catch (Exception e) {
				return false;
			} finally {
				try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
			}
			
		}
		 
		
		public boolean checkInputDisabled(String elementName){
			
			return McsElement.checkInputDisabled(driver, "@class", XPATH_ADD_FORM_WINDOW, elementName);	
		
		}
		
		public boolean checkTextareaDisabled(String elementName){
			 driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
				try {
					driver.findElement(By.xpath("//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//textarea[@name='" + elementName + "']//..//textarea[contains(@class, 'readonly')]"));
					return true;
				} catch (Exception e) {
					return false;
				}
			 finally{
				 driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
			 }
				
		}
		
		public boolean checkMandatoryFieldSave(String dialog){
			try{
				driver.findElement(
						By.xpath("//div[contains(@class,'"+dialog+"')]//span[contains(@class,'window-header')]")).getText().contains("Add");
				return true;
			} catch(Exception e){
				e.printStackTrace();
			}
			return false;
		}
		
		//Reopen Weather Station Form being either saved or not
			
	public boolean reopenForm() throws Exception{

		if (!checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW)) {

			close(XPATH_ADD_FORM_WINDOW);

			waitForExtJSAjaxComplete(20);

			clickAddButton(XPATH_GRID_CLASS);
			
			Thread.sleep(3000);

			waitForExtJSAjaxComplete(25);

			return false;
		} else {

			close();

			waitForExtJSAjaxComplete(20);

			clickOnDialogButtonWithoutMcsElement("Yes");

			// catch (Exception e) {}

			waitForExtJSAjaxComplete(20);

			clickAddButton(XPATH_GRID_CLASS);
			
			Thread.sleep(3000);

			waitForExtJSAjaxComplete(25);

			return true;

		}
	}
			
	public void close(String dialogClass) {
		/*McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", dialogClass, "button", "text()",
				"Close", true, true).click();*/
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class, '"+dialogClass+"')]//button[text()='Close']"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		ele.click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Click Close button", true);
	}

	public void checkCheckBox(String name) {
		  McsElement.getElementByPartAttributeValueAndParentElement(driver,
		    "div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
		    name, true, true).click();
		  //clickOnDialogButton("Yes");
		  Reporter.log("set default yes", true);
	}

		 public void uncheckCheckBox(String name) {
		  McsElement.getElementByPartAttributeValueAndParentElement(driver,
		    "div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
		    name, true, true).click();
		  Reporter.log("uncheck default", true);
	}

	public boolean getCheckBoxState(String name) {
		  if (McsElement.getElementByPartAttributeValueAndParentElement(driver,
		    "div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
		    name, true, true).getAttribute("checked") != null) {
		   return true;
		  }
		  return false;
	}
	
	public void clickMeasureTemplatesTab()
	{
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"ul", "@class", "x-tab-strip", "span", "text()", "Measure Templates", true, true).click();
		Reporter.log("Click on Measure Templates tab", true);
	}
	
	public boolean verifyUIButtons(String gridId, String name) {
		  try{
			  driver.findElement(By.xpath("//div[contains(@class, '"+gridId+"')]//button[contains(@class, 'x-btn-text') and text()='"+name+"']"));
			  return true;
		  } catch(Exception e){
			  e.printStackTrace();
		  }
		  return false;
	}
	
	public boolean verifyUIImages(String atributeValue) {
		try{
			  driver.findElement(By.xpath("//div[contains(@class, 'x-panel x-panel-noborder x-grid-panel')]//button[contains(@class, 'x-btn-text') and (contains(@style, '"+atributeValue+"'))]"));
			  return true;
		  } catch(Exception e){
			  e.printStackTrace();
		  }
		  return false;
	}
	
	public List<String> verifyAppliesToDropdown(String attribute, String attributeValue, String columnName){
		int colNo = getGridColumnNumber(attribute, attributeValue, columnName);
		
		WebElement ele = driver.findElement(By.xpath("//div[contains("+attribute+",'"+attributeValue+"')]//input[contains(@id,'filter-editor-"+colNo+"')]//following-sibling::img"));
		ele.click();
		
		waitForExtJSAjaxComplete(10);
		
		List<WebElement> values = driver.findElements(By.xpath("//div[contains(@class, 'x-combo-list') and (contains(@style, 'visibility: visible'))]//div[contains(@class,'x-combo-list-item')]"));
		
		ArrayList<String> lsValues = new ArrayList<String>(); 
				
		for(int i=0; i<values.size(); i++){
					
		String valueText = values.get(i).getText().trim();
		if(valueText != null){
			lsValues.add(valueText); }
		}
		
		return lsValues;
	}
	
	/**
	 *  Helper method to get column number in Grid 
	 */
	public int getGridColumnNumber(String attribute, String attributeValue, String columnName){
		
		String columnClass = McsElement.getElementByXpath(driver,"(//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')][last()])").getAttribute("class");
		String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);
			
		return Integer.parseInt(columnNumber);
	}
	
	/**
	 * Helper method to verify Column Names in Grid
	 */
	public boolean verifyColumnNames(String attribute, String attributeValue, String columnName) {
		try{
			  driver.findElement(By.xpath("//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')]"));
			  return true;
		  } catch(Exception e){
			  e.printStackTrace();
		  }
		  return false;
	}
	
	public void clickOnDialogButtonWithoutMcsElement(String buttonText) {
		WebElement ele = driver.findElement(By.xpath(("//div[contains(@class, 'x-window-dlg')]//button[contains(@class, 'x-btn-text') and text()='" + buttonText + "']")));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			ele.click();
		} catch(Exception e){
			e.printStackTrace();
		}
		Reporter.log("Click on '"+buttonText+"' button on dialog", true);
	}
}
