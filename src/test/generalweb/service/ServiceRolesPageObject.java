package test.generalweb.service;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class ServiceRolesPageObject extends AbstractMcsTestSuite {

	protected final String ADMINISTRATION_PANEL_CLASS = "x-panel-body x-panel-body-noborder";	

	protected final String XPATH_ADD_Row = "x-toolbar x-small-editor x-toolbar-layout-ct";

	protected final String PANEL_NAME = "Parameters";	
	
	protected final String XPATH_SERVICE_ROLES = "//div[contains(@class, 'x-panel')]//span[contains(text(), 'Service Roles') and contains(@class,'x-panel') ]//..//..";
	
	protected final String ADMINISTRATION_CLASS = "admsettings-modulesettings";

	protected final String GRID_EDITOR = "//div[contains(@class,'x-small-editor x-grid-editor') and not(contains(@style,'visibility: hidden'))]";
	
	
	/**
	 * Helper method to expand Master Data tree structure
	 */
	public void expandMasterData() {
		expandNode("div","@id",getXPanelId("Administration"),"Master Data");
	}
	
	/**
	 * Helper method to open Organizations and select Service Roles link	 
	 */
	public void clickServiceRolesLink() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "mcs-tree-navigation",
				"span", "text()", "Service Roles", true, true).click();
		Reporter.log("Click on Service Roles link ", true);
	}
	
	/**
	 * Helper method to click Add button
	 * @param attribute - Attribute to use 
	 * @param gridId - Grid class name 
	 */	
	public void clickAddButton(String attribute, String gridId) {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div",attribute , gridId, "button", "@class", " x-btn-text",
				"text()", "Add", true, true).click();		
		Reporter.log("Click Add button" + " (" + timer.stop() + "ms)", true);		
	}
	
	/**	 
	 * Helper method to set Reference in aqa122 portal
	 * @param reference - value for Reference field 
	 */
	public void setReference122(String reference) {		
		setGridEditorText(reference);
		Reporter.log("Set Reference " + reference, true);		
	}
	
	/**
	 * Helper method to set Code in aqa122 portal
	 * @param code - value for Code field
	 */
	public void setCode122(String code) {			
		String columnClass = McsElement	.getLastElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADMINISTRATION_CLASS,
				"div","@class", "x-grid3-hd",
				"text()", "Code", true, true).getAttribute("class");
		
		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1); 

		WebElement reqElement = McsElement.getElementByXpath(driver,"(//"+"div"+"[contains("+"@class"+",'" + ADMINISTRATION_CLASS + "')]//"
				+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')])");

		Actions obj = new Actions(driver);
		obj.doubleClick(reqElement).perform();
			
		setGridEditorText(code);
		
		Reporter.log("Set Code " + code, true);			
	}
	
	/**
	 * Helper method to set Code in aqa14 portal
	 * @param code - value for Code field
	 */
	public void setCode14(String code) {		
		setGridEditorText(code);
		Reporter.log("Set Code " + code, true);		
	}
	
	/**
	 * Helper method to set Reference in aqa14 portal
	 * @param reference - value for Reference field
	 */
	public void setReference14(String reference) {		
		String columnClass = McsElement	.getLastElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ADMINISTRATION_CLASS,
				"div","@class", "x-grid3-hd",
				"text()", "Reference", true, true).getAttribute("class");
		
		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1); 

		WebElement reqElement = McsElement.getElementByXpath(driver,"(//"+"div"+"[contains("+"@class"+",'" + ADMINISTRATION_CLASS + "')]//"
				+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')])");

		Actions obj = new Actions(driver);
		obj.doubleClick(reqElement).perform();
		
		setGridEditorText(reference);	
		
		Reporter.log("Set Reference " + reference, true);			
	}
	
	/**
	 * Helper method to click Save button
	 */
	public void saveServiceRoles() {		
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, XPATH_SERVICE_ROLES+"//button[contains(text(),'Save Changes')]").click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Save Service Roles"+ " (" + timer.stop()
				+ "ms)", true);		
	}
	
	/**
	 * Helper method to check whether success message is present or not after saving Service Role record
	 * @return Info bar message
	 */
	public boolean isSuccessbarMsgPresent(){		
		return McsElement.isElementPresent(driver, XPATH_SERVICE_ROLES+"//div[@class = 'infobar bg-success']");
	}
	
	/**
	 * Helper method to edit created Service Role record
	 * @param code - Code value of the Service Role
	 * @param colNameForFiltering - Column name to filter/obtain required record
	 * @param columnNameToEdit - Column name to edit the record
	 * @param valueToSet - Value to set
	 */	
	public void editAddedServiceRoleRecord( String code, String colNameForFiltering, String columnNameToEdit, String valueToSet ){	
		
		filterGrid("@class", ADMINISTRATION_CLASS, code, colNameForFiltering);
		
		String columnClass = McsElement	.getLastElementByPartAttributeValueAndParentElement(driver,
						"div", "@class", ADMINISTRATION_CLASS,
						"div","@class", "x-grid3-hd",
						"text()", columnNameToEdit, true, true).getAttribute("class");
				
		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1); 

		WebElement reqElement = McsElement.getElementByXpath(driver,"(//"+"div"+"[contains("+"@class"+",'" + ADMINISTRATION_CLASS + "')]//"
		      +"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')])[last()]");
		
		Actions obj = new Actions(driver);
		obj.doubleClick(reqElement).perform();
		
		waitForExtJSAjaxComplete(10);
		
		setGridEditorText(valueToSet);
		
		Reporter.log("Double click on Description element to edit", true);	
	}	
	
	/**
	 * Helper method to click Delete button for deleting selected Service Role record
	 * @param attribute - Attribute to use 
	 * @param gridId - Grid class name 
	 */
	public void clickDeleteButton(String attribute, String gridId) {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div",attribute , gridId, "button", "@class", " x-btn-text",
				"text()", "Delete", true, true).click();		
		Reporter.log("Click Delete button" + " (" + timer.stop() + "ms)", true);
		clickOnDialogButton("Yes");
	}	
	
	/**
	 * Helper method to set value in Grid edit field
	 * @param valueToSet - Value to set
	 */
	public void setGridEditorText(String valueToSet){
		WebElement editorField  = McsElement.getElementByXpath(driver, GRID_EDITOR+"//input");	
		
		editorField.clear();
		waitForExtJSAjaxComplete(10);
		
		editorField.sendKeys(valueToSet);
		
		editorField.sendKeys(Keys.ENTER);
		
		waitForExtJSAjaxComplete(20);
	}
}
