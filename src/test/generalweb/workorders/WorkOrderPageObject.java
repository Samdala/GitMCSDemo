package test.generalweb.workorders;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import test.configuration.Configuration;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.FileUploader;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class WorkOrderPageObject extends WorkOrderDetailTabsPageObject {

	protected final String XPATH_OVERVIEW_BUTTON = "//button[contains(@class,'x-btn-text icon-ov-overview')]";

	protected final String XPATH_OVERVIEW_BUTTON_GENERAL = "//span[contains(@class,'x-menu-item-text') and contains(text(),'General')]";

	protected final String XPATH_OVERVIEW_BUTTON_GENERAL_ALL_WORKORDERS = "//span[contains(@class,'x-menu-item-text') and contains(text(),'All')]";

	protected final String XWINDOW_CLASS = "x-window x-window-noborder x-resizable-pinned";

	protected final String XWINDOW_FOOTER_CLASS = "x-window-footer x-window-footer-noborder x-panel-btns";

	protected final String DETAILS_PANEL_CLASS = "x-panel workorders-newwo x-panel-noborder";

	protected final String DETAILS_GENERAL_FORM_CLASS = "x-panel-body x-panel-body-noborder x-form";
	
	protected final String XPATH_XWINDOW = "//div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]";
	
	protected final String DETAILS_NEW_WO_WINDOW_CLASS = "details-general";
	
	protected final String CHANGE_VISIBLE_COLUMNS_HEADER = "Change visible columns";
	
	protected final String FILTER_WINDOW_TITLE = "Filter";
	
	protected final String CHECKLIST_XPATH_IN_GRID = "//div[contains(@realid, 'SurveyGrid')]";
	
	protected final String TEST_PAGE_TITLE = "Platform to Create Organizational Testing and Certifications";
	
	protected final String ADD_ACTIVITY_WINDOW_HEADER = "Add Activity";
	
	protected final String ADMINISTRATION_WO_TEMPLATE_XPATH = "//span[contains(text(), 'Workorder Templates')]/../following-sibling::div";
	
	protected final String ADMINISTRATION_WO_TEMPLATE_GROUP = "//div[contains(@class, 'x-panel-body x-panel-body-noborder')]//div[contains(@class, 'x-panel x-border-panel') and starts-with(@style, 'width:')]";
	
	protected final String ADMINISTRATION_WO_TEMPLATE_DETAILS = "//div[contains(@class, 'x-panel x-border-panel') and starts-with(@style, 'left:')]";
	
	protected final String ADMINISTRATION_WO_REFERENCE_XPATH = "//label[contains(text(), 'Reference')]/..//div//input";
	
	protected final String COPY_WO_TEMPLATE_WINDOW = "//div[contains(@class, 'x-window-dlg') and contains(@style, 'visibility: visible')]";
	
	protected final String CHECKLIST_TEMPLATE_XPATH = "//div[contains(@class, 'quality-question-item')]";
	
	protected final String MAINTENANCE_OBJECTS_WINDOW_XPATH = "//div[contains(@class,'shadow') and contains(@style,'display: block')]/following-sibling::div[contains(@class, 'x-window x-resizable-pinned')]//span[contains(@class, 'x-window-header-text')]";
	
	protected final String MAINTANCE_OBJECT_WINDOW_TITLE = "Maintenance Object Details";
	
	protected final String ACCESS_INS_WINDOW_TITLE = "Access Instructions";
	
	protected final String FLOATING_MENU_XPATH = "//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//ul[contains(@class,'x-menu-list')]";
	
	protected final String EXPENSES_XPATH = "//div[contains(@class,'hdwo-details')]//span[contains(text(), 'Expenses')]";
	
	protected final String WO_WINDOW_XPATH = "//div[contains(@class,'resizable-pinned') and contains(@style,'visibility: visible')]";
	
	public  String EDIT_WO_WINDOW_CLASS_XPATH="//div[contains(@class,'hdwo-details')]//ancestor::div[contains(@class,'x-window x-resizable-pinned') and contains(@style,'visibility: visible')]";

	public final String DELETE_BUTTON_CLASS="icon-template-delete";

	public final String DELETEGROUP_BUTTON_CLASS="icon-templategroup-delete";

	public final String COPY_BUTTON_CLASS="icon-template-copy";

	protected final String TEMPLATES_PANEL_CLASS="//div[contains(@class, 'mcs-tree-navigation')]/following-sibling::div[contains(@class, 'x-panel-body')]";

	protected final String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
	
	protected final String XPATH_FOR_UNIQUE_ELEMENT="//div[contains(@class,'x-panel-mc') and contains(@class, 'x-hide-display')]//following-sibling::div[contains(@class,'x-panel-noborder') and not(contains(@class, 'x-hide-display'))]";
	
	protected final String ADD_WO_WINDOW_XPATH="//div[contains(@class,'x-window x-resizable-pinned') and contains(@style,'visibility: visible')]";
	
	public void clickAddButton() {
                clickXPath("//button[contains(@class, 'icon-ov-add') and text()='Add']");
	}


	public void clickDeleteButton() {
                clickXPath("//button[contains(@class, 'icon-ov-remove') and text()='Delete']");
	}

	
	public void selectWorkOrderLookUp() {
		clickXPath("(//*[contains(@class,'x-panel-body x-panel-body-noborder')]//button) [1]");
	}

	public void selectFirstWorkOrder() {
		clickXPath("(//*[contains(@class,'" + XWINDOW_CLASS + "')]//*[contains(@class,'x-grid3-cell-inner x-grid3-col-0')]) [1]");
	}

	public void selectWorkOrderFormOK() {
		clickXPath("//*[contains(@class,'" + XWINDOW_CLASS + "')]//button[contains(text(),'OK')]");
		waitForMaskDisappear();
	}

	/*public void clickOkXwindow() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XWINDOW_FOOTER_CLASS, "button",
				"text()", "OK", true, true).click();
		waitForElementDisappear(XPATH_XWINDOW);
	}
*/
	public void clickCancelXwindow() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XWINDOW_FOOTER_CLASS, "button",
				"text()", "Cancel", true, true).click();
		waitForElementDisappear(XPATH_XWINDOW);
	}

	public void setWorkOrderType(String workOrderType) {
		
		String name = driver.findElement(By.xpath("(//input[contains(@spellcheck,'false')])[1]")).getAttribute("name"); 
				
		clickLookup("details-general", name);
		
		setValueGridLookupWithFilters("@class","x-resizable", workOrderType, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		Reporter.log("Work Order type was selected", true);
	}
	
	
	public void setWorkOrderTemplate(String template) {
		
		String name = driver.findElement(By.xpath("(//input[contains(@spellcheck,'false')])[last()]")).getAttribute("name"); 
				
		clickLookup("details-general", name);
		
		setValueGridLookupWithFilters("@class","x-resizable", template, "Name");
		
		waitForExtJSAjaxComplete(25);

		Reporter.log("Work Order type was selected", true);
	}
	
	
	public void setNature(String nature) {

		clickLookup("@class", WORKORDER_WINDOW_DETAIL, "NEW_WO_NATURE","Select a Nature");
		
		waitForExtJSAjaxComplete(25);
		
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//span[contains(text(),'Tree')]")).click();

		setValueTreeLookup(new String[]{nature});
		
		Reporter.log("nature was selected", true);
		
	}
	
	
	
	public void setLocation(String reference) {
		clickLookupNewUI("@class", WORKORDER_WINDOW_DETAIL, "NEW_WO_LOCATION","Select a Location");
		
		setValueGridLookupWithFiltersNewUI("@class", "x6-window-default-closable", reference, "Reference");
		waitForExtJSAjaxComplete(25);
		Reporter.log("location "+ reference+" was set", true);
	}
	

	public String getNature() {
		return McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-panel')]//input[@name='nature']/..//input)[last()]").getAttribute("value");
	}


	public String getLocation() {
		return McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-panel')]//input[@name='location']/..//input)[last()]").getAttribute("value");
	}
	

	public String getType() {
		return McsElement.getElementByXpath(driver, "//div[contains(@class,'x-panel')]//input[@name='woTypeRef']").getAttribute("value");
	}

	
	public String getReference() {
		return McsElement.getElementByXpath(driver, "//div[contains(@class,'x-panel')]//input[@name='reference']").getAttribute("value");
	}
	
	
	public String getMaintanceObject() {
		return driver.findElement(By.xpath("(//div[contains(@class,'x-panel')]//input[@name='maintObj']/..//input)[last()]")).getAttribute("value");
	}
	
	
	public void clickRadioWrkTmpl(){
		clickXPath("//label[contains(text(),'Select a Work Order Template')]/..//input");
		waitForExtJSAjaxComplete(25);
	}
	
	
	public void clickGeneralTab(){
		clickXPath("//span[text()='General' and contains(@class,'tab')]");
		waitForExtJSAjaxComplete(25);
	}
	/**
	 * Helper method to click on Object tab
	 */
	public void clickObjectTab(){
		clickXPath(EDIT_WO_WINDOW_CLASS_XPATH+"//span[text()='Objects' and contains(@class,'tab')]");
		waitForExtJSAjaxComplete(25);
	}
	
	public void clickDescriptionTab(){
		clickXPath("//span[text()='Description' and contains(@class,'tab')]");
		waitForExtJSAjaxComplete(25);
	}

	public void setDescription(String description){
		clickXPath("//span[text()='Description' and contains(@class,'tab')]/../../../../../../../..//textarea");
		McsElement.getElementByXpath(driver, "//span[text()='Description' and contains(@class,'tab')]/../../../../../../../..//textarea").clear();
		McsElement.getElementByXpath(driver, "//span[text()='Description' and contains(@class,'tab')]/../../../../../../../..//textarea").sendKeys(description);
		clickXPath("//span[text()='Description' and contains(@class,'tab')]/../../../../../../../..//textarea");
		Reporter.log("description was set", true);
	}
	
	public void setPriority(String priority){
		//String id = driver.findElement(By.xpath("//input[@name='NEW_WO_PRIORITY']")).getAttribute("id");
		clickLookup(WORKORDER_WINDOW_DETAIL, "NEW_WO_PRIORITY");
		waitForExtJSAjaxComplete(25);
		//DropDown.setExtJsComboValue(driver,id, priority);
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Work Order Priority"), priority, "Reference");
		waitForExtJSAjaxComplete(25);
		Reporter.log("priority was set");
	}
	
	public void setSeverity(String severity){
		//String id = driver.findElement(By.xpath("//input[@name='severity']")).getAttribute("id");
		clickLookup(WORKORDER_WINDOW_DETAIL, "NEW_WO_SEVERITY");
		waitForExtJSAjaxComplete(25);
		//DropDown.setExtJsComboValue(driver,id, priority);
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Work Order Severity"), severity, "Reference");
		waitForExtJSAjaxComplete(25);
		Reporter.log("severity was set");
	}
	
	

	public void clickNextOnDetailsPanel() {
		clickXPath("//*[contains(@class,'" + DETAILS_PANEL_CLASS + "')]//button[contains(@class,'icon-ov-next')]");
	}


	public void clickPreviousOnDetailsPanel() {
		clickXPath("//*[contains(@class,'" + DETAILS_PANEL_CLASS + "')]//button[contains(@class,'icon-ov-prev')]");
	}

	
	public void setExecutionDate(String reference) {
		clickXPath("//div[contains(@class,'x-panel')]//input[contains(@name,'ppmDate')]");
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-panel')]//input[contains(@name,'ppmDate')]").clear();
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-panel')]//input[contains(@name,'ppmDate')]").sendKeys(reference);
		clickXPath("//div[contains(@class,'x-panel')]//input[contains(@name,'ppmDate')]");
	}

	
	
	public void setReference(String reference) {
		waitAndSendKeys("//form[contains(@class,'" + DETAILS_GENERAL_FORM_CLASS
				+ "')]//input[contains(@name,'NEW_WO_REFERENCE')]", reference);
	}
	
	public void setReferenceGeneralTab(String id, String reference) {
		WebElement ele = driver.findElement(By.xpath("//div[contains(@id,'"+id+"')]//input[contains(@name,'reference')]"));
		ele.click();
		ele.clear();
		ele.sendKeys(reference);
		waitForExtJSAjaxComplete(5);
		Reporter.log("Reference is set ", true);
	}


	public void setFirstLocation(String location, String tab) {

		clickXPath("//form[contains(@class,'" + DETAILS_GENERAL_FORM_CLASS + "')]//input[contains(@name,'location')]//..//..//button");

		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]"));
		}

		catch (Exception e) {
			clickXPath("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");
		}
		finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);
		}

		try {
			clickXPath("//*[contains(@class,'" + XWINDOW_CLASS + "')]//li[contains(@id,'" + tab + "')]//span");
		} catch (Exception e) {
			clickCancelXwindow();
			return;
		}
		try {
			WebElement firstNode = McsElement
					.getElementByPartAttributeValueAndParentElement(driver,
							"div", "@class", XWINDOW_CLASS, "a",
							"@class", "x-tree-node-anchor", ".", location, true, true);
			firstNode.click();
		} catch (Exception e) {
			clickCancelXwindow();
			Reporter.log("Location was not selected", true);
			return;
		}
		clickOkXwindow();
		Reporter.log("Location was selected", true);
	}

	public void setFirstCustomer(String customer) {

		setValueFromGridLookUp("customer", customer);
		
	}

	public void setFirstCostCenter(String costCenter) {

		setValueFromGridLookUp("costCenter", costCenter);
		
	}

	public void setFirstGlAccount(String glAccount) {

		setValueFromGridLookUp("glAccount", glAccount);
		
	}

	public void setFinKey1(String finKey1) {

		setValueFromGridLookUp("finKey1", finKey1);
		
	}

	public void setFinKey2(String finKey2) {
		
		setValueFromGridLookUp("finKey2", finKey2);

	}

	public void setFinKey3(String finKey3) {

		setValueFromGridLookUp("finKey3", finKey3);
		
	}

	public void setFinKey4(String finKey4) {
		
		setValueFromGridLookUp("finKey4", finKey4);

	}

	public void setFinKey5(String finKey5) {

		setValueFromGridLookUp("finKey5", finKey5);
		
	}

	public void setFinKey6(String finKey6) {

		setValueFromGridLookUp("finKey6", finKey6);
		
	}

	public void setFinKey7(String finKey7) {
		
		setValueFromGridLookUp("finKey7", finKey7);

	}

	public void setFinKey8(String finKey8) {
		
		setValueFromGridLookUp("finKey8", finKey8);

	}

	public void clickSaveOrder() {
		WebElement save = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", DETAILS_NEW_WO_WINDOW_CLASS, "button", "@class",
				"x-btn-text", "text()", "Save Workorder", true, true);
		waitFocusAndClick(save);
		try{
		waitForMaskDisappear();
		}
		catch(Exception E){
			
		}
		waitForExtJSAjaxComplete(20);
		Reporter.log("Order was saved", true);
	}
	
	
	
	public void setValueFromGridLookUp(String inputName, String rowTextName) {
		Timer timer = new Timer().start();

		clickXPath("//form[contains(@class,'" + DETAILS_GENERAL_FORM_CLASS + "')]//input[contains(@name,'" + inputName + "')]//..//..//button");

		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By
					.xpath("//div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]"));
		}

		catch (Exception e) {
			clickXPath("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");
		} finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);
		}

		try {
			WebElement firstRow = McsElement
					.getElementByPartAttributeValueAndParentElement(driver,
							"div", "@class", XWINDOW_CLASS, "div",
							"@class", "x-grid3-cell-inner x-grid3-col-0",
							"text()", rowTextName, true, true);
			firstRow.click();
			clickOkXwindow();
			Reporter.log(inputName + " " + rowTextName + " was selected"+ " (" + timer.stop()
					+ "ms)", true);
		} catch (Exception e) {
			clickCancelXwindow();
			Reporter.log(inputName + " " + rowTextName + "was not selected"+ " (" + timer.stop()
					+ "ms)", true);
		}

	}

	public void setValueFromTreeLookUp(String inputName, String rowTextName) {
		
		Timer timer = new Timer().start();

		clickXPath("//form[contains(@class,'" + DETAILS_GENERAL_FORM_CLASS + "')]//input[contains(@name,'" + inputName + "')]//..//..//button");

		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]"));
		}

		catch (Exception e) {
			waitAndClick("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");
		} finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);
		}

		try {
			WebElement firstNode = McsElement
			.getElementByPartAttributeValueAndParentElement(driver,
					"div", "@class", XWINDOW_CLASS, "a",
					"@class", "x-tree-node-anchor", ".", rowTextName, true, true);
			firstNode.click();
			clickOkXwindow();
			Reporter.log(inputName + " " + rowTextName + " was selected"+ " (" + timer.stop()
					+ "ms)", true);
		} catch (Exception e) {
			clickCancelXwindow();
			Reporter.log(inputName + " " + rowTextName + "was not selected"+ " (" + timer.stop()
					+ "ms)", true);
		}

	}
	
	public void expandSouth() {

		try {

			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

			McsElement.getElementByPartAttributeValue(driver, "div", "class",
					"x-tool-expand-south", true, true).click();

		} catch (Exception e) {

		} finally {
			try {
				driver.manage()
						.timeouts()
						.implicitlyWait(configuration.getImplicitWait(),
								TimeUnit.SECONDS);

			} catch (Exception e) {
			}
		}

	}
	
	/**
	 * Helper method to get Window id By class
	 */
	public String getWindowIdByClass(String xwindowClass) throws NoSuchElementException {

		String elementid = driver.findElement(By.xpath("(//div[contains(@class, '" + xwindowClass + "') and contains(@style, 'visibility: visible')])[last()]")).getAttribute("id");
		
		return elementid;
	}

	/**
	 * Helper method to open a record in Transaction Line
	 */
	public void openTransactionLine(String attribute, String attributeValue, String rowTextName, String columnName){
		waitForExtJSAjaxComplete(25);
		
		filterGridWithoutUsingMcsElement(attribute, attributeValue, rowTextName, columnName);
		
		waitForExtJSAjaxComplete(25);
		
		WebElement ele = Grid.checkRowInGriByTextValueAndClick(driver, rowTextName); 
		new Actions(driver).doubleClick(ele).build().perform();
		waitForExtJSAjaxComplete(25);
	}
	
	/**
	 * Helper method to click on Tabs in Lookup window
	 * @param tabText : text of tab to click
	 */
	public void clickOnTab(String windowID, String tabText){
		
		WebElement element = driver.findElement(By.xpath("//div[contains(@id, '"+windowID+"')]//span[contains(@class,'x-tab-strip-text') and contains(text(),'"+tabText+"')]//ancestor::a"));
		
		if ("chrome".equals(configuration.getBrowser())) {
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", element);
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		waitForExtJSAjaxComplete(20);
	}
	
	/**
	 * Helper Method to verify Compliance Level Field
	 */
	public boolean isComplainceFieldVisible(String windowID){
		//String value = driver.findElement(By.xpath("//div[contains(@id, '"+windowID+"')]//input[contains(@name, 'complianceLevelRef')]")).getAttribute("class");
		String xpath = "//div[contains(@id, '"+windowID+"')]//input[contains(@name, 'complianceLevelRef')]";
		return McsElement.isElementDisplayed(driver, xpath);
		/*if(value.contains("x-hide-display not-editable")){
			return true;
		}else{
			return false;
		}*/
	}
	
	/**
	 * Helper method to verify Compliance field is Read Only
	 */
	public boolean isComplianceFieldReadOnly(String windowID){
		String element = driver.findElement(By.xpath("//div[contains(@id, '"+windowID+"')]//input[contains(@name, 'complianceLevelRef')]")).getAttribute("class");
		
		if(element.contains("readonly")){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Helper method to click on Change Visible Columns
	 */
	public void clickChangeVisibleColumns(){
		McsElement.getElementByXpath(driver,
				"//div[contains(@class, 'x-tab-panel-body-noborder x-tab-panel-body-top') and not (contains(@class, 'hide-display'))]//button[contains(@class, 'icon-grid-columns')]")
		.click();
		Reporter.log("Click on Change Visible Columns button", true);
	}
	
	/**
	 * Helper method to click on Filter Results 
	 */
	public void clickFilterResults(){
		McsElement.getElementByXpath(driver,
				"//div[contains(@class, 'x-tab-panel-body-noborder x-tab-panel-body-top') and not (contains(@class, 'hide-display'))]//button[contains(@class, 'icon-grid-filter') and not (contains(text(), 'Clear Filters'))]")
		.click();
		Reporter.log("Click on Filter Results button", true);
	}
	
	
	/**
	 * Helper method to click on Any Button
	 */
	public void clickButton(String buttonName, String dialogId)
	{
		WebElement element = driver.findElement(By.xpath("//div[contains(@id, '"+dialogId+"')]//button[contains(@class, 'x-btn-text') and text()='"+buttonName+"']"));
		element.click();
		Reporter.log("Click "+buttonName+" button", true);
	}
	
	/**
	 * Helper method to click on Any Button
	 */
	public void clickButton(String attribute, String attributeID, String buttonName)
	{
		WebElement element = driver.findElement(By.xpath("//div[contains("+attribute+", '"+attributeID+"')]//button[contains(@class, 'x-btn-text') and text()='"+buttonName+"']"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		}catch(Exception e){
			e.printStackTrace();
		}
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		Reporter.log("Click "+buttonName+" button", true);
	}
	
	/**
	 * Helper method to clear Filters
	 */
	public void clearOverviewFilters(){
		McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-tab-panel-body-noborder x-tab-panel-body-top') and not (contains(@class, 'hide-display'))]//button[contains(@class, 'icon-grid-filter-clear')]").click();
		waitForExtJSAjaxComplete(10);
	}
	
	/**
	 * Helper method to set Compliance Value in Filter Results
	 * btnName = Add restriction
	 * Parent Node = Maintenance
	 * attrToBeClicked = Compliance Level
	 */
	public void expandMaintenanceAndSelectComplianceValueInFilterResults(String className, String btnName, String ParentNode, String attrToBeClicked){
		
		//Click on Add Restriction
		driver.findElement(By.xpath("//div[contains(@id, '"+getWindowIdByClass(className)+"')]//div[contains(@class, 'x-toolbar x-small-editor')]//button[contains(text(), '"+btnName+"')]")).click();

		waitForExtJSAjaxComplete(10);
		
		//Check Maintenance Expanded
		WebElement xpath = driver.findElement(By.xpath("//div[contains(@id, '"+getWindowIdByClass(className)+"')]//span[text()='"+ParentNode+"']/ancestor::div[contains(@class, 'x-tree-node-el x-unselectable')]"));
		
		try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", xpath);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		String xpathClassName = xpath.getAttribute("class");
		
		if(xpathClassName.contains("collapsed")) {
			driver.findElement(
					By.xpath("//div[contains(@id, '"
							+ getWindowIdByClass(className)
							+ "')]//span[text()='"+ParentNode+"']/ancestor::div[contains(@class, 'x-tree-node-collapsed')]//img[contains(@class, 'x-tree-ec-icon x-tree-elbow-plus')]"))
					.click();
		}
		waitForExtJSAjaxComplete(10);
		
		//Double click on Compliance Level
		WebElement element = driver.findElement(By.xpath("//div[contains(@id, '"+getWindowIdByClass(className)+"')]//ul[contains(@class, 'x-tree-node-ct')]//span[contains(text(), '"+attrToBeClicked+"')]"));
		
		new Actions(driver).doubleClick(element).perform();
		
		waitForExtJSAjaxComplete(10);
	}
	
	/**
	 * Helper method to Validate Compliance Level Field
	 */
	public void enterComplianceLevelValue(String className, String complainceValue){
		WebElement ele = McsElement.getElementByXpath(driver,
				"//div[contains(@id, '"+getXWindowId("Enter a Compliance Level")+"')]//div[contains(@class, 'x-box-item')]//div[@class='x-box-inner']//input");
		ele.click();
		ele.clear();
		ele.sendKeys(complainceValue);
		
		McsElement.getElementByXpath(driver, "//div[contains(@id, '"+getXWindowId("Enter a Compliance Level")+"')]//button[contains(text(), 'OK')]").click();
		
		waitForExtJSAjaxComplete(10);
		
		driver.findElement(By.xpath("//div[contains(@id, '"+getWindowIdByClass(className)+"')]//button[text()='Save']")).click();
		
		waitForExtJSAjaxComplete(25);
		
	}
	
	/**
	 * Helper method to Select Restriction and click on Edit Restriction
	 * value = Compliance Level =
	 */
	public void selectComplianceRestriction(String className, String value){
		driver.findElement(By.xpath("//div[contains(@id, '"+getWindowIdByClass(className)+"')]//span[contains(text(), '"+value+"')]")).click();
		waitForExtJSAjaxComplete(10);
	}
	
	/**
	 * Helper method to click on Edit Restriction in Filter Results
	 */
	public void clickAnyButtonInFilterResults(String className, String btnName){
		//Click on Edit Restriction
		driver.findElement(By.xpath("//div[contains(@id, '"+getWindowIdByClass(className)+"')]//div[contains(@class, 'x-toolbar x-small-editor')]//button[contains(text(), '"+btnName+"')]")).click();

		waitForExtJSAjaxComplete(25);
	}
	
	/**
	 * Helper method to verify Size of the grid
	 */
	public int getGridSize() {
		List<WebElement> ele = driver.findElements(By.xpath("//div[contains(@class, 'x-grid3-viewport')]//div[contains(@class, 'x-grid3-body')]//div[contains(@class,'x-grid3-row')]"));
		
		int size = ele.size();
		
		return size;
	}
	
	/**
	 *  Helper method to get column number in Grid 
	 */
	public boolean checkColumnNameInGrid(String attributeValue, String columnName){
		try{
			driver.findElement(By.xpath("//div[contains(@class,'"+ attributeValue+ "') and not (contains(@class, 'x-hide-display'))]//div[contains(@class,'x-grid3-hd') and contains(text(), '"+ columnName + "')][last()]"));
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Helper method to click on add in BOM Tab
	 */
	public void clickAddButtonOnBOM(String attributeID){
		driver.findElement(
				By.xpath("//div[contains(@id, '"+attributeID+"')]//div[contains(@class, 'bom-grid x-grid-with-col-lines')]//div[contains(@class, 'x-small-editor x-toolbar-layout-ct')]//button[contains(@class, 'x-btn-text') and text()='Add']"))
				.click();
		waitForExtJSAjaxComplete(10);
	}
	
	/**
	 * Helper method to select all Rows in BOM Tab
	 */
	public void selectHeaderCheckBoxInBOM(String attributeID){
		driver.findElement(
				By.xpath("//div[contains(@id, '"+attributeID+"')]//div[contains(@class, 'bom-grid x-grid-with-col-lines')]//div[contains(@class, 'x-grid3-header')]//div[contains(@class, 'x-grid3-hd-checker')]//div"))
				.click();
		waitForExtJSAjaxComplete(10);
	}
	
	/**
	 * Helper method to click on Stock Info Button in BOM Tab
	 */
	public void clickStockInfoButtonOnBOMTab(String attributeID, String value){
		driver.findElement(
				By.xpath("//div[contains(@id, '"+attributeID+"')]//button[contains(@class, 'x-btn-text') and text()='"+value+"']"))
				.click();
		waitForExtJSAjaxComplete(10);
	}
	
	/**
	 * Helper method to verify values in Stock Info page
	 * Product Code and Product Reference
	 */
	public boolean verifyProductDetailsSInStockInfoPage(String windowTitle, String attributeValue){
		try{
			driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//div[contains(@class, 'x-box-inner')]//div[contains(text(), '"+attributeValue+"')]"));
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Helper method to verify values in Stock Info page
	 * Needed Qty and UOM
	 */
	public boolean verifyNeededQtyUOMInStockInfoPage(String windowTitle, String rowNum, String labelName, String attributeValue){
		try{
			driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//div[contains(@class, 'x-window-body')]/div['"+rowNum+"']//label[contains(text(), '"+labelName+"')]//..//div[contains(text(), '"+attributeValue+"')]"));
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Helper method to verify Product values are on Top and Left aligned
	 */
	public String getStyleOfElementsInStockInfoPage(String windowTitle, String attributeValue){
		return driver.findElement(
						By.xpath("//div[contains(@id, '"
								+ getXWindowId(windowTitle)
								+ "')]//div[contains(@class, 'x-box-inner')]//div[contains(text(), '"
								+ attributeValue
								+ "')]/ancestor::div[contains(@class, 'x-box-item')]"))
				.getAttribute("style");
	}
	
	
	/**
	 * Helper method to get Product Details in BOM Page
	 * @return
	 */
	public String getProductDetails(String attribute, String attributeValue, String rowNum, String columnName) {
		
		waitForExtJSAjaxComplete(25);
		
		int columnNumber = getGridColumnNumberWithoutQuickFilters(attribute, attributeValue , columnName );
	
		WebElement ele = McsElement.getElementByXpath(driver,"//div[contains("+attribute+", '"+attributeValue+"') and not (contains(@class, 'x-hide-display'))]//div[contains(@class, 'x-grid-group-body')]/div["+rowNum+"]//div[contains(@class,'x-grid3-cell-inner x-grid3-col-"+columnNumber+"')]/span");
		
		String value = ele.getText();
		
		return value;
	}
	
	/**
	 * Helper method to verify if Element is present in the grid and delete it
	 */
	public void deleteProductsFromConsumption(String windowID, String[] prodArray, String columnName){
		boolean status = false;
		
		int colNo = getGridColumnNumberWithoutQuickFilters("@class", "hdwo-details", columnName);
		
		for(int i=0; i<prodArray.length; i++){
			
				boolean ele = isRowInGridPresentLike(colNo, prodArray[i]);
		
			if(ele) {
				driver.findElement(By.xpath("//div[contains(@class, 'hdwo-details')]//tr//div[contains(@class, 'col-"+colNo+"') and text()='"+prodArray[i]+"']")).click();
				
				waitForExtJSAjaxComplete(20);
				
				clickButtonTimeAndMaterialTab(windowID, "Delete");
				
				waitForExtJSAjaxComplete(20);
				
				clickOnDialogButton("Yes");
				
				waitForExtJSAjaxComplete(25);
				
				waitForExtJSAjaxComplete(20);			
			}
		}
	}
	
	/**
	 * Helper method to Get Grid Column No
	 */
	public boolean isRowInGridPresentLike(int no, String value){
		
		try {
			driver.findElement(By.xpath("//div[contains(@class, 'hdwo-details')]//tr//div[contains(@class, 'col-"+no+"') and text()='"+value+"']"));
		} catch (Exception e) {
			Reporter.log("row can not be found in grid: ",true);
			return false;
		}
		Reporter.log("Check element "+value+" present in grid and click", true);
		return true;
	}
	
	/**
	 * Helper method to Click on AddEdit/Delete Button Time And MAterial Tab
	 */
	public void clickButtonTimeAndMaterialTab(String attributeID, String buttonName){
		String xpath ="//div[contains(@id, '"+attributeID+"')]//div[contains(@class, 'button-icon-extra-spacing')]//button[contains(@class, 'x-btn-text') and text()='"+buttonName+"']";
				
		WebElement ele = driver.findElement(By.xpath(xpath));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		ele.click();
		Reporter.log(" "+buttonName+" consumption was clicked <br>", true);
		waitForExtJSAjaxComplete(10);
	}
	
	/**
	 * Helper method to select Product from lookup
	 */
	public void selectProductInConsumptionWindow(String windowTitle, String childWindowTitle, String name, String column){
		driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowIdWithoutVisibilityCheck(windowTitle)+"')]//label[text()='Product:']/following-sibling::div[contains(@class, 'x-form-element')]//input//..//..//button")).click();
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters("@id", getXWindowIdWithoutVisibilityCheck(childWindowTitle), name, column);
		 
		waitForExtJSAjaxComplete(20);
		 
		Reporter.log("Product is Selected on Consumptions Tab", true); 
	}
	
	
	/**
	 * Helper method to set Quantity in Consumption Window
	 */
	public void setQuantityInConsumptionWindow(String windowTitle, String qty){
		WebElement element = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//label[text()='Quantity:']/following-sibling::div[contains(@class, 'x-form-element')]//input"));
		element.click();
		element.clear();
		element.sendKeys(qty);
		waitForExtJSAjaxComplete(10);
	}
	
	/**
	 * Helper Method to Click Buttons in Consumption Window
	 */
	public void clickButtonConsumptionWindow(String windowTitle, String buttonName){
		driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//div[contains(@class, 'x-window-bbar')]//button[contains(@class, 'x-btn-text') and text()='"+buttonName+"']")).click();
		waitForExtJSAjaxComplete(20);
	}
	
	/**
	 * Helper method to get Cost Item ID
	 * @return
	 */
	public String getCostItemID(String attribute, String attributeValue, String rowNum, String columnName) {
		
		waitForExtJSAjaxComplete(25);
		
		int columnNumber = getGridColumnNumberWithoutQuickFilters(attribute, attributeValue , columnName );
	
		WebElement ele = McsElement.getElementByXpath(driver,"//div[contains("+attribute+", '"+attributeValue+"') and not (contains(@class, 'x-hide-display'))]//div[contains(@class, 'x-grid3-body')]/div["+rowNum+"]//div[contains(@class,'x-grid3-cell-inner x-grid3-col-"+columnNumber+"')]");
		
		String value = ele.getText();
		
		waitForExtJSAjaxComplete(20);

		return value;
	}
	
	/**
	 * Helper method to get ID attribute of current grid
	 * */
	public String getFilterGridID(){
		
		WebElement grid = McsElement.getElementByXpath(driver, "//div[@id='portalcontainer_tabs']//div[contains(@class,'x-panel financials x-panel-noborder') and not(contains(@class,'x-hide-display'))]//div[@class='x-grid3']");
		
		return grid.getAttribute("id");
		
	}
	
	
	/**
	 * Helper method to verify CostItemID Available in Grid of Financials Window
	 */
	public boolean verifyCostItemIDInFinancialsGrid(String attribute, String attributeValue, String rowTextName,String columnName){
		int colNo = getGridColumnNumberWithQuickFilters(attribute, attributeValue, columnName);
		
		waitForExtJSAjaxComplete(10);
		
		String xpath = "//*[@class='x-grid3']//div[contains(@class, 'x-grid3-col-"+colNo+"') and text()='"+rowTextName+"']";
		
		try {
			driver.findElement(By.xpath(xpath));
		} catch (Exception e) {
			Reporter.log("row can not be found in grid: " +xpath,true);
			return false;
		}
		
		Reporter.log("Row with text " + rowTextName  + " was successfully found", true);

		return true;
	}
	
	
	
	/**
	 * Helper method to reopen checklist
	 */
	public void clickReopenChecklist(){
		McsElement.getElementByXpath(driver, "//div[contains(@realid,'SurveyGrid')]//button[text()='Reopen']").click();
		Reporter.log("Reopen Checklist was clicked ", true);
		
		clickOnDialogButton("Yes");
	}
	
	
	public void filterGridWithoutUsingMcsElementModified(String attribute, String attributeValue, String rowTextName, String columnName) {
		Timer timer = new Timer().start();
		
		
		String columnClass = driver.findElement(By.xpath("//div[contains("+attribute+",'"+ attributeValue+"')]//div[contains(@class,'quickfilters')]"
						+ "//div[contains(@class,'x-grid3-hd') and contains(text(),'"+columnName+"')][last()]")).getAttribute("class");
		
		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
		
		WebElement filterInput = driver.findElement(By.xpath("//div[contains("+attribute+",'"+attributeValue+"')]//"
				+ "input[contains(@id,'filter-editor-"+columnNumber+"')][last()]")); 
					
		filterInput.clear();
			
		filterInput.sendKeys(rowTextName);
			
		driver.findElement(By.xpath("//div[contains(@id, 'wotabpanel') and not(contains(@style,'visibility: hidden'))]//div[contains(@class, 'x-grid3-viewport')]//div[contains(@class,'x-grid3-body')]/div[last()]")).click(); 

		waitForExtJSAjaxComplete(5);

		Reporter.log(rowTextName + " was filtered"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	/**
	 * Helper method to open Transaction Line from grid.
	 * Double click on the element is performed twice.
	 */
	public void openTransactionLineModified(String attribute, String attributeValue, String rowTextName, String columnName) throws Exception{
		waitForExtJSAjaxComplete(25);
		//if(("firefox".equals(configuration.getBrowser())) || "internet explorer".equals(configuration.getBrowser())){
		filterGridWithoutUsingMcsElementModifiedForChrome(attribute, attributeValue, rowTextName, columnName);
			waitForExtJSAjaxComplete(25);
		//}
			waitForExtJSAjaxComplete(25);
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class,'x-panel x-grid-panel')]//*[@class='x-grid3']//div[text()='"+rowTextName+"']"));
		
		Thread.sleep(10000);
		
		new Actions(driver).doubleClick(ele).perform();
		Reporter.log("Opened The Transaction Line from Grid" , true);
		
		new Actions(driver).doubleClick(ele).perform();
		waitForExtJSAjaxComplete(25);
	}
	
	/**
	 * This method is used only For Filtering Records in Chrome
	 * Difference is, It just filters and doesnt click on the filtered Record
	 * @param attribute
	 * @param attributeValue
	 * @param rowTextName
	 * @param columnName
	 */
	public void filterGridWithoutUsingMcsElementModifiedForChrome(String attribute, String attributeValue, String rowTextName, String columnName) {
		Timer timer = new Timer().start();
		
		
		String columnClass = driver.findElement(By.xpath("//div[contains("+attribute+",'"+ attributeValue+"')]//div[contains(@class,'quickfilters')]"
						+ "//div[contains(@class,'x-grid3-hd') and contains(text(),'"+columnName+"')][last()]")).getAttribute("class");
		
		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
				
		
		WebElement filterInput = driver.findElement(By.xpath("//div[contains("+attribute+",'"+attributeValue+"')]//"
				+ "input[contains(@id,'filter-editor-"+columnNumber+"')][last()]")); 
					
		filterInput.clear();
			
		filterInput.sendKeys(rowTextName);
			
		filterInput.sendKeys(Keys.RETURN);
		
		waitForExtJSAjaxComplete(5);

		Reporter.log(rowTextName + " was filtered"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
/*********************Planning Tab*************************/
	
	/**
	 * Helper method to Save Workorder
	 */
	public void saveWorkOrder(String windowID, String buttonName){
		WebElement element = driver.findElement(By.xpath("//div[contains(@id, '"+windowID+"')]//button[contains(@class, 'x-btn-text') and text()='"+buttonName+"']"));
		element.click();
		waitForExtJSAjaxComplete(25);
	}
	
	/**
	 * Helper method to enter Estimated Work in Planning Tab
	 */
	public void setEstimatedWork(String windowID, String estWork){
		WebElement element = driver.findElement(By.xpath("//div[contains(@id, '"+windowID+"')]//input[@name='estWork']"));
		element.click();
		element.clear();
		element.sendKeys(estWork);
	}
	
	/**
	 * Helper method to getValues from Planning Tab
	 */
	public double getValuesPlanningTab(String windowID, String fieldName){
		WebElement ele = driver.findElement(By.xpath("//div[contains(@id, '"+windowID+"')]//input[@name='"+fieldName+"']"));
		String value = ele.getAttribute("value");
		waitForExtJSAjaxComplete(25);
		
		Locale lc= Locale.US;
		Locale.setDefault(Locale.US);
		
		DecimalFormat twoDForm = new DecimalFormat("0.00");
	    DecimalFormatSymbols dfs = new DecimalFormatSymbols();
	    dfs.setDecimalSeparator('.');
	    twoDForm.setDecimalFormatSymbols(dfs);
		
	    String angleFormated = twoDForm.format(Double.parseDouble(value));
		
		return Double.parseDouble(angleFormated);
	}
	
	/**
	 * Helper method to enter Start Time In Activities Labor Tab
	 */
	public void setStartEndTime(String windowTitle, String fieldName, String value){
		WebElement element = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//input[@name='"+fieldName+"']"));
		element.click();
		element.clear();
		waitForExtJSAjaxComplete(25);
		element.sendKeys(value);
	}
	
	/**
	 * Helper method to enter Description In Activities Labor Tab
	 */
	public void setDescription(String windowTitle, String fieldName, String value){
		WebElement element = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//textarea[@name='"+fieldName+"']"));
		element.click();
		element.clear();
		element.sendKeys(value);
	}
	
	/**
	 * Helper method to set Workforce Value
	 */
	public void setWorkforce(String inputName, String childWindowHeader, String rowTextName, String columnName){
		clickLookup("@id", getXWindowId(ADD_ACTIVITY_WINDOW_HEADER), inputName, childWindowHeader);
		
		waitForExtJSAjaxComplete(20);

		setValueGridLookupWithFilters("@id", getXWindowId(childWindowHeader), rowTextName, columnName);
		
		waitForExtJSAjaxComplete(20);
		Reporter.log("Workforce value is set", true);
	}
	
	/**
	 * Helper method to verify Row is Present in the Grid
	 */
	public boolean isRowInLaborGridPresent(String gridRealID, String columnName, String value){
		int colNo = getGridColumnNumberWithoutQuickFilters("@realid", gridRealID, columnName);
		
		String xpath = "//div[contains(@realid, '"+gridRealID+"')]//div[contains(@class, 'x-grid3-body')]//div[contains(@class, 'x-grid3-col-"+colNo+"') and text()='"+value+"']";
		
		return McsElement.isElementDisplayed(driver, xpath);
	}
	
	/**
	 * Helper method to select Row is Present in the Grid
	 */
	public void checkRowInLaborTabGrid(String columnName, String value){
		int colNo = getGridColumnNumberWithoutQuickFilters("@realid", "WoLaborGrid", columnName);
		
		WebElement element = driver.findElement(By.xpath("//div[contains(@realid, 'WoLaborGrid')]//div[contains(@class, 'x-grid3-body')]//div[contains(@class, 'x-grid3-col-"+colNo+"') and text()='"+value+"']"));
		 try {
             ((JavascriptExecutor) driver).executeScript(
                     "arguments[0].scrollIntoView(true);", element);
         } catch (Exception e) {
         	e.printStackTrace();
         }
		element.click();
		waitForExtJSAjaxComplete(20);
	}
	
	/**
	 * Helper method to find %Spent in Planning Tab
	 * %spent’ = Actual Work / Estimated Work * 100 (rounded to 2 decimals);
	 */
	public double percentageSpent(double actualWork, double estimatedWrk){
		double percentSpent = (actualWork/estimatedWrk)*100;
		
		Reporter.log("percentageSpent Formatted no", true);
		return formatDecimals(percentSpent);
	}
	
	/**
	 * Helper method to find Remaining Work in Planning Tab
	 * Remaining Work = Estimated Work – Actual Work (rounded to 2 decimals);
	 */
	public double remainingWork(double actualWork, double estimatedWrk){
		double percentSpent = (estimatedWrk-actualWork);
		
		Reporter.log("remainingWork Formatted no", true);
		return formatDecimals(percentSpent);
	}
	
	/**
	 * Helper method to find Percentage Remaining in Planning Tab
	 * %remaining = Remaining Work/ Estimated Work * 100 (rounded to 2 decimals);
	 */
	public double percentageRemaining(double remainingWrk, double estimatedWrk){
		double percentSpent = (remainingWrk/estimatedWrk)*100;
		
		Reporter.log("percentage Remaining Formatted no", true);
		return formatDecimals(percentSpent);
	}
	
	/**
	 * Helper method to find Percentage Planned in Planning Tab
	 * % planned = Planned Work/ Estimated Work* 100 (rounded to 2 decimals);
	 */
	public double percentagePlanned(double plannedWrk, double estimatedWrk){
		double percentSpent = (plannedWrk/estimatedWrk)*100;
		
		Reporter.log("percentage Planned Formatted no", true);
		return formatDecimals(percentSpent);
	}
	
	/**
	 * Helper method to find Percentage Remaining in Planning Tab
	 * Unplanned work = 0 man-hours to plan if (Estimated work – Planned Work) <=0; 
	 * else Unplanned work = (Estimated Work – Planned Work) man hours to plan.
	 */
	public double unplannedWork(double plannedWrk, double estimatedWrk){
		double percentSpent = (estimatedWrk - plannedWrk);
		
		Reporter.log("unplannedWork Formatted no", true);
		return formatDecimals(percentSpent);
	}
	
	/**
	 * Helper method to format the Decimals to two digits
	 */
	public double formatDecimals(double no){
		Locale lc= Locale.US;
		Locale.setDefault(Locale.US);
		
		DecimalFormat twoDForm = new DecimalFormat("0.00");
	    DecimalFormatSymbols dfs = new DecimalFormatSymbols();
	    dfs.setDecimalSeparator('.');
	    twoDForm.setDecimalFormatSymbols(dfs);
		
	    String angleFormated = twoDForm.format(no);
		
		Reporter.log(angleFormated+"  Formatted no", true);
		return Double.parseDouble(angleFormated);
	}
	
	/**
	 * Helper method to get Column Values from WO Grid
	 * Column Name = Filtering the Grid Column Name(Filter with Reference)
	 * Col Name = Get the Respective Grid Column No(Get the Col No Of Planning Work)
	 */
	public String getValuesFromGrid(String attribute, String attributeValue, String colName) {
		
		waitForExtJSAjaxComplete(25);
		
		int columnNumber = getGridColumnNumberWithQuickFilters(attribute, attributeValue , colName);
	
		WebElement ele = McsElement
				.getElementByXpath(
						driver,
						"//div[contains("+attribute+", '"+attributeValue+"') and not (contains(@class, 'x-hide-display'))]//div[contains(@class, 'x-grid3-body')]//td[contains(@class,'x-grid3-cell x-grid3-td-"+columnNumber+"')]/div");
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String value = ele.getText();
		
		return value;
	}
	
	/**
	 * Helper method to get Decimal values from Few columns
	 */
	public double getValuesFromExecutionColumns(String attribute, String attributeValue, String colName){
		
		String value = getValuesFromGrid(attribute, attributeValue, colName);
		
		Reporter.log(value+"  Formatted no", true);
		return formatDecimals(Double.parseDouble(value));
	}
	
	

	/**
	 * Navigate to home page and relogin
	 */
	public void navigateToMainPage(String NAME_FOR_RIGHT,String PASSWORD_FOR_RIGHT) {
		Timer timer = new Timer().start();
		waitForExtJSAjaxComplete(25);
		driver.get(configuration.getApplicationUrl());
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Navigate to main page\n", true);
		driver.findElement(By.xpath("//table[@id='top-account-menu']//button")).click();
		waitForExtJSAjaxComplete(25);
		driver.findElement(By.xpath("//span[text()='Logout']")).click();
		Reporter.log("Logout <br>", true);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		driver.navigate().to(configuration.getApplicationUrl());
		waitForExtJSAjaxComplete(25);
		driver.manage().deleteAllCookies();
		waitForExtJSAjaxComplete(25);
		driver.navigate().refresh();
		waitForExtJSAjaxComplete(25);
		login(NAME_FOR_RIGHT, PASSWORD_FOR_RIGHT);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Login to back-end under account "+NAME_FOR_RIGHT +" END\n", true);
		Reporter.log("<br />");
		Reporter.log("Before test method END"+ " (" + timer.stop() + "ms)\n", true);
		Reporter.log("<br />");
	}
	
	/**
	 * Helper method to click on Add Actions Button In Window
	 */
	public void clickAddActionButtonInWindow(){
		McsElement.getElementByXpath(driver, "//div[contains(@class, 'hdwo-details')]//td[contains(@class,'x-toolbar-right')]//button[contains(text(), 'Add Action')]").click();
		Reporter.log("Actions Window is Opened", true);	
	}
	
	/**
	 * Helper method to get Status From Window
	 */
	public String getToolBarStatuses(String name){
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class, 'hdwo-details')]//div[contains(@class, '"+name+"')]"));
		String status = ele.getText();
		Reporter.log("Action Status ", true);
		return status;
	}
	
	/**
	 * Helper method to verify Details Tab is Collapsed
	 */
	public boolean setDetailsTabCollapsedMode(){
		String xpath = "//div[contains(@class, 'x-border-panel')]//div[contains(@class, 'x-tool-toggle x-tool-collapse-south')]";
		boolean status = McsElement.isElementDisplayed(driver, xpath);
		
		if(status){
			driver.findElement(By.xpath(xpath)).click();
			waitForExtJSAjaxComplete(10);
			
			//clickAddButton();
		}
		Reporter.log("Details Tab is Set to Collapsed", true);
		return status;
	}
	
	////////// Interactive Wo Related Methods	/////////////////////////////////////////////////
	
	/**
	 * Helper method to Expand/Collapse Template Group
	 */
	public void expandTemplateGroup(){
		String xpath = WO_WINDOW_XPATH+"//div[contains(@class, 'helpdesk-newcall')]//div[contains(@class, 'grpLabel') and text()='AqaTemplateGroup']/ancestor::div[contains(@class, 'x-tree-node-el')]";
		String classValue = McsElement.getElementByXpath(driver, xpath).getAttribute("class");
		if(classValue.contains("collapsed")){
			driver.findElement(By.xpath(xpath)).click();
			waitForExtJSAjaxComplete(10);
		} else{
			Reporter.log("Template Group tree is already expanded", true);
		}
		Reporter.log("Template Group is Expanded", true);
	}
	
	/**
	 * Helper method to click on Template Name
	 */
	public void selectTemplate(String tempName){
		String xpath = WO_WINDOW_XPATH+"//div[contains(@class, 'helpdesk-newcall')]//div[contains(@class, 'tplLabel') and text()='"+tempName+"']";
		McsElement.getElementByXpath(driver, xpath).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Template Name "+tempName+" is selected ");
	}
	
	
	/**
	 * Helper method to verify Child WO Template window is opened
	 */
	public boolean isChildWOTemplateWindowOpened(String tempName){
		String xpath =  WO_WINDOW_XPATH+"//div[contains(@class, 'template-tree')]//span[contains(text(), 'Work Order Templates')]/../..//div[contains(@class, 'tplLabel') and text()='"+tempName+"']";
		return McsElement.isElementDisplayed(driver, xpath);
	}
	
	
	
	
	/**
	 * Helper method to check and uncheck header checkbox
	 */
	public void uncheckHeaderRowCheckBox(){
		String xpath = "//div[contains(@class, 'x-grid3-viewport')]//div[contains(@class, 'quickfilters')]//td[contains(@class, 'x-grid3-td-checker x-grid3-cell-first')]//div[@class='x-grid3-hd-checker']/ancestor::div[contains(@class, 'x-grid3-hd-checker-on')]";
				
		boolean status = McsElement.isElementPresent(driver, xpath);
		
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class, 'x-grid3-viewport')]//div[contains(@class, 'quickfilters')]//td[contains(@class, 'x-grid3-td-checker x-grid3-cell-first')]//div[@class='x-grid3-hd-checker']"));
		
		if(!status){
			ele.click();
			Reporter.log("Checked All", true);
			ele.click();
			Reporter.log("UnChecked All", true);
		}
	}
	
	/**
	 * Helper method to set Due time in General Tab WO
	 */
	public void setDueTimeGeneralTab(String windowOrPanel, String priority){
		WebElement dueTime= McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='dateDue']/..//td[contains(@class,'ux-datetime-time')]//input");
		javaScriptFocus(dueTime);
		javaScriptClick(dueTime);
		dueTime.clear();
		dueTime.sendKeys(priority);
		dueTime.sendKeys(Keys.SHIFT);
		javaScriptClick(dueTime);
		Reporter.log("Due Time is Set", true);			
	}	
	
	/**
	 * Helper method to set Due Date in General Tab WO
	 */
	public String getMonthName(){
		 String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
			        "August", "September", "October", "November", "December" };

	    Calendar cal = Calendar.getInstance();
	    String month = monthName[cal.get(Calendar.MONTH)-1];
	    
	    month = month.substring(0, 3);

	    System.out.println("Month name: " + month);
	    return month;
	}
	
	public String getCurrentMonth(String systemDate) {
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH)+1;

		String Dates =  (String)(month<10?("0"+month):(month));
		System.out.println("Datessssssss " +Dates);
		return Dates;
	}
	
	/**
	 * Helper method to set Due Date in General Tab WO
	 */
	public void setDueDateGeneralTab(){
		
		WebElement dt= driver.findElement(By.xpath("//label[contains(text(), 'Due Date and Time:')]/following-sibling::div//td[contains(@class,'ux-datetime-date')]//input[contains(@class, 'x-form-field') and not (contains(@class, 'x-form-readonly'))]/following-sibling::img"));
		javaScriptFocus(dt);
		javaScriptClick(dt);
		
		
		String todayDate = getFutureDate(0);
		todayDate = todayDate.substring(0, 2);
		System.out.println(todayDate);
		
		int yestDate = Integer.parseInt(todayDate)-1;
		System.out.println(yestDate);
		
		if(String.valueOf(yestDate).contentEquals("0")){
			driver.findElement(By.xpath("//div[contains(@class, 'x-date-picker')]//td[contains(@class, 'x-date-middle')]//button")).click();
			waitForExtJSAjaxComplete(25);
			driver.findElement(By.xpath("//div[contains(@class, 'x-date-mp')]//td[contains(@class, 'x-date-mp-month')]//..//a[contains(text(), '"+getMonthName()+"')]")).click();
			waitForExtJSAjaxComplete(25);
			driver.findElement(By.xpath("//div[contains(@class, 'x-date-picker')]//button[contains(@class, 'x-date-mp-ok') and contains(text(), 'OK')]")).click();
			waitForExtJSAjaxComplete(25);
			driver.findElement(By.xpath("//div[contains(@class, 'x-date-picker')]//td[contains(@class, 'x-date-today')]/ancestor::div[contains(@class, 'x-date-picker')]//td[contains(@class, 'x-date-active')]//span[contains(text(), '5')]")).click();
			waitForExtJSAjaxComplete(25);
		} else{
			driver.findElement(By.xpath("//div[contains(@class, 'x-date-picker')]//td[contains(@class, 'x-date-today')]/ancestor::div[contains(@class, 'x-date-picker')]//td[contains(@class, 'x-date-active')]//span[contains(text(), '"+yestDate+"')]")).click();
			waitForExtJSAjaxComplete(25);
		}
		
		WebElement dueDate= McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window x-resizable-pinned')]//input[@name='dateDue']/..//td[contains(@class,'ux-datetime-date')]//input");
		/*javaScriptFocus(dueDate);
		javaScriptClick(dueDate);
		dueDate.clear();
		dueDate.sendKeys(priority);*/
		dueDate.sendKeys(Keys.SHIFT);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Due Date and Time  is Set", true);	
	}	
	
	/**
	 * Helper method to get the Workorder Name from the Window
	 */
	public boolean getWONameFromDetailsWindow(String woReference){
		String xpath = "//div[contains(@class, 'x-window x-resizable-pinned')]//div[contains(@class, 'xtb-text')]";
		
		WebElement ele = driver.findElement(By.xpath(xpath));
		String text = null;
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			text = ele.getText();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		waitForExtJSAjaxComplete(25);
		
		if(text.contains(woReference)){
			return true;
		}
		Reporter.log("WO Name Is displayed in Details window", true);
		return false;
	}
	
	/**
	 * Helper method to open a record in Transaction Line
	 * Customized filter grid is used
	 */
	public void openTransactionLineFirefox(String attribute, String attributeValue, String rowTextName, String columnName) throws Exception{
		waitForExtJSAjaxComplete(25);
		filterGridWithoutUsingMcsElementModified(attribute, attributeValue, rowTextName, columnName);
		waitForExtJSAjaxComplete(25);
		
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class,'x-panel x-grid-panel')]//*[@class='x-grid3']//div[text()='"+rowTextName+"']"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			ele.click();
		} catch(Exception e){
			e.printStackTrace();
		}
				//driver.findElement(By.xpath("//div[contains(@class,'x-panel x-grid-panel')]//*[@class='x-grid3']//div[text()='"+rowTextName+"']"));
		
		Thread.sleep(10000);
		
		//new Actions(driver).doubleClick(ele).perform();
		Reporter.log("Opened The Transaction Line from Grid" , true);
	}
	
	public static WebElement checkRowInGriByTextValueAndClick(WebDriver webDriver, String textValue)  {
		String xpath = "//*[@class='x-grid3']//div[text()='"+textValue+"']";
		WebElement webElement = webDriver.findElement(By.xpath(xpath));
		webElement.click();
		Reporter.log("Check element "+textValue+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	
	public void clickOverviewBtn(){
		WebElement ele = driver.findElement(By.xpath(XPATH_OVERVIEW_BUTTON));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			ele.click();
		} catch(Exception e){
			e.printStackTrace();
		}
		Reporter.log("Clicked On Overview Button ", true);
	}
	
	/**
	 * Helper method to delete WO from overview
	 */
	public void deleteWOFromOverview(String referenceMoCt[]){
		
		for(int i=0; i<=referenceMoCt.length-1; i++){
			
			boolean ele = Grid.isRowInGridPresentLike(driver, referenceMoCt[i], "@class", "x-grid3-viewport");
			
			if(ele){
			Reporter.log("Record is available in Grid", true);
			
			Grid.checkRowInGriByTextValueAndClick(driver, referenceMoCt[i]);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			Reporter.log("Element is checked Grid", true);
			
			/*Assert.assertTrue(getWONameFromDetailsWindow(referenceMoCt[i]), "WO Details window is opened");
			
			waitForExtJSAjaxComplete(25);*/
			
			closeXWindow();
			
			waitForExtJSAjaxComplete(25);
			
			Reporter.log("Record is Closed in Grid", true);
					
			clickDeleteButton();
			
			waitForExtJSAjaxComplete(25);
			
			Reporter.log("Delete Btn clicked", true);
			
			clickOnDialogButtonCustomized("Yes");
			
			waitForExtJSAjaxComplete(25);
			
			Reporter.log("Yes Clicked", true);
			
			clickOnDialogButtonCustomized("OK");
			
			Reporter.log("OK Clicked", true);
			}
		}
		
		Reporter.log("Wo are deleted from the overview", true);
	}
	
	public void clickOnDialogButtonCustomized(String buttonText) {
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class, 'x-window-dlg')]//button[contains(@class, 'x-btn-text') and text()='" + buttonText + "']"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		Reporter.log("Click on '"+buttonText+"' button on dialog", true);
	}
	
	//////////////////////Administration Related WO Template Methods//////////////////////
	/**
	 * Helper method to verify WO template node is listed under WO
	 */
	public boolean isWOTemplateUnderWONodeInAdministration(){
		String xpath = "//span[contains(text(), 'Workorders')]/../../following-sibling::ul//span[contains(text(), 'Work Order Templates')]/ancestor::a";
		return McsElement.isElementDisplayed(driver, xpath);
	}
	
	/**
	 * Helper method to verify WO Template Pane is loaded
	 */
	public boolean verifyWOTemplatePane(){
		String xpath = ADMINISTRATION_WO_TEMPLATE_XPATH+ADMINISTRATION_WO_TEMPLATE_GROUP;
		return McsElement.isElementDisplayed(driver, xpath);
	}
	
	/**
	 * Helper method to verify WO Tempate or Group Mess before selecting a template
	 */
	public String getMsgInWOTemplateDetailsBeforeSelectingTemplate(){
		String xpath = ADMINISTRATION_WO_TEMPLATE_XPATH+ADMINISTRATION_WO_TEMPLATE_DETAILS+"//div[contains(@class, 'x-panel-mc')]//div[contains(@class, 'x-panel-body')]";
		return McsElement.getElementByXpath(driver, xpath).getText();
	}
	
	/**
	 * Helper method to click on Button in WO Template Administration
	 * class = icon-template-add
	 */
	public void clickButtonWOTemplateAdministration(String className){
		String xpath = ADMINISTRATION_WO_TEMPLATE_GROUP+"//button[contains(@class, '"+className+"')]";
		McsElement.getElementByXpath(driver, xpath).click();
		Reporter.log("Clicked Button "+className+" in Wo Template Administration", true);
	}
	
	/**
	 * Helper method to click on Button in WO Template Details
	 * class = icon-template-add
	 */
	public void clickButtonWOTemplateDetailsAdministration(String className, String btnName){
		String xpath = ADMINISTRATION_WO_TEMPLATE_DETAILS;
		if(className.contains("icon-save")){
			xpath = xpath+"//button[contains(@class, '"+className+"')]/span[contains(text(), 'Save Changes')]";
		} else {
			xpath = xpath+"//button[contains(@class, '"+className+"') and contains(text(), 'Cancel')]";
		}
		
		McsElement.getElementByXpath(driver, xpath).click();
		Reporter.log("Clicked Button "+className+" in Wo Template Administration", true);
	}
	
	/**
	 * Helper method to verify Tabs are opened in WO Template
	 */
	public boolean isTabPresentInWODetailsWin(String tabName){
		String xpath = ADMINISTRATION_WO_TEMPLATE_DETAILS+"//span[contains(text(), '"+tabName+"')]";
		return McsElement.isElementDisplayed(driver, xpath);
	}
	
	/**
	 * Helper method to Enter Reference
	 */
	public void setReferenceWOTemplate(String reference){
		String xpath = ADMINISTRATION_WO_REFERENCE_XPATH;
		WebElement ele = McsElement.getElementByXpath(driver, xpath);
		ele.click();
		ele.clear();
		ele.sendKeys(reference);
		Reporter.log("Reference is entered in WO Template", true);
	}
	
	/**
	 * Helper method to getWO Reference From Template
	 */
	public String getReferenceFromWOTemplate(){
		return McsElement.getElementByXpath(driver, ADMINISTRATION_WO_REFERENCE_XPATH).getAttribute("value");
	}
	
	/**
	 * Helper method to select a Wo Type
	 */
	public void setWOTypeWOTemplate(String woType){
		String xpath = "//div[contains(@id, 'x-form-el-woType')]//input//../following-sibling::table//button";
		McsElement.getElementByXpath(driver, xpath).click();
		waitForExtJSAjaxComplete(3);
		
		setValueGridLookup("@id", getXWindowId("Select a Work Order Type"), woType);
		waitForExtJSAjaxComplete(3);
		Reporter.log("WO Type is Selected", true);
	}
	
	/**
	 * Helper method to select Related Object Type
	 */
	public void selectRelatedObjectType(String menuItem){
		DropDown.setComboValueDropDownSelector(driver, "relatedObjectType", menuItem);
		Reporter.log("Related Object Type is selected", true);
	}
	
	/**
	 * Helper method to getRelatedObjectType
	 */
	public String getRelatedObjectType(){
		String xpath ="//div[contains(@class, 'x-form-label-top')]//input[contains(@id, 'relatedObjectType')]";
		return McsElement.getElementByXpath(driver, xpath).getAttribute("value");
	}
	
	/**
	 * Helper method to select Language in the WO Template
	 */
	public void selectLanguageInWoTemplate(String language){
		String xpath = ADMINISTRATION_WO_TEMPLATE_DETAILS+"//div[contains(@class, 'x-grid-panel') and not (contains(@class, 'x-hide-display'))]//div[contains(text(), '"+language+"')]";
		McsElement.getElementByXpath(driver, xpath).click();
		Reporter.log("Language is Selected in WO Template ", true);
	}
	
	/**
	 * Helper method to verify template is created at first level in the hierarchy
	 */
	public void verifyTemplateIsCreatedAtFirstLevelInHierarchy(String tempName) throws Exception{
		String parentTmpXPath = ADMINISTRATION_WO_TEMPLATE_XPATH+ADMINISTRATION_WO_TEMPLATE_GROUP+"//div[@class='x-tree-root-node']/li[1]/div[1]//a/span";
		String parentValue = McsElement.getElementByXpath(driver, parentTmpXPath).getText();
		
		
		String childXpath = "//span[contains(text(), '"+parentValue+"')]//..//..//../following-sibling::li//span[contains(text(), '"+tempName+"')]";
		boolean status = McsElement.isElementDisplayed(driver, childXpath);
			if(status){
				Reporter.log("New Template is at first level in the hierarchy of templates tree", true);
				McsElement.getElementByXpath(driver, childXpath).click();
			}
			else{
				Reporter.log("New Template is not found in Template tree", true);
				throw new Exception();
			}
		
		Reporter.log("Clicked on Template "+tempName+" ", true);
	}
	
	/**
	 * Helper method to select the template
	 */
	public void selectWOTemplate(String tempName){
		String xpath = ADMINISTRATION_WO_TEMPLATE_GROUP+"//span[contains(text(), '"+tempName+"')]/..";
		McsElement.getElementByXpath(driver, xpath).click();
		Reporter.log("Template is selected ", true);
	}
	
	/**
	 * Helper method to expand first Template Group
	 */
	public void expandWOTemplateGroup(String tempName){
		String parentTmpXPath = ADMINISTRATION_WO_TEMPLATE_XPATH+ADMINISTRATION_WO_TEMPLATE_GROUP+"//div[@class='x-tree-root-node']/li//span[contains(text(), '"+tempName+"')]/ancestor::div[1]";
		String parentValue = McsElement.getElementByXpath(driver, parentTmpXPath).getAttribute("class");
		
		if(parentValue.contains("x-tree-node-collapsed")){
			//driver.findElement(By.xpath(parentTmpXPath+"//img[contains(@class, 'x-tree-elbow-end-plus')]")).click();
			driver.findElement(By.xpath("//span[contains(text(), 'Workorder Templates')]/../following-sibling::div//div[@class='x-tree-root-node']/li//span[contains(text(), '"+tempName+"')]/../..//img[contains(@class,'x-tree-elbow-plus')]")).click();
			waitForExtJSAjaxComplete(2);
		} else{
			Reporter.log("Template Group is already Expanded", true);
		}
	}
	
	/**
	 * Helper method to verify WO Template is created under Selected Group
	 */
	public boolean isWOTemplateUnderWOGroup(String tempGrpName, String tempName){
		expandWOTemplateGroup(tempGrpName);
		
		String xpath = "//span[contains(text(), '"+tempGrpName+"')]//..//..//..//ul//span[contains(text(), '"+tempName+"')]";
		return McsElement.isElementDisplayed(driver, xpath);
	}
	
	/**
	 * Helper method to Enter Reference in Copy WO Template
	 */
	public void enterReferenceInCopyWOTemplate(String reference){
		String xpath = COPY_WO_TEMPLATE_WINDOW+"//input[@class='ext-mb-input']";
		WebElement ele = McsElement.getElementByXpath(driver, xpath);
		ele.click();
		ele.clear();
		ele.sendKeys(reference);
		Reporter.log("Reference is Entered in Copy WO Template", true);
	}
	
	/**
	 * Helper method to click on Financial Summary Button
	 */
	public void clickCommandDropDown(String attrName, String btnName){
		driver.findElement(By.xpath("//div[contains(@id, '"+attrName+"')]//button[contains(@class, 'icon-gear')]")).click();
		waitForExtJSAjaxComplete(3);
		
		String xpath ="//div[contains(@class,'x-menu-floating') and contains(@style,'visibility: visible')]//span[@class='x-menu-item-text' and text()='"+btnName+"']/..";
		driver.findElement(By.xpath(xpath)).click();
		waitForExtJSAjaxComplete(3);
		
		Reporter.log("Clicked On Button "+btnName+" ", true);
	}
	
	/**
	 * Helper method to get the Workorder ID from the Window
	 */
	public String getWOIDFromDetailsWindow(){
		String xpath = "//div[contains(@class, 'x-window x-resizable-pinned') and contains(@style, 'visibility: visible')]//div[@class='xtb-text']";
		
		String text = McsElement.getElementByXpath(driver, xpath).getText();
		String [] chunks = text.split (" ", 5);
        String woID = chunks.length == 5 ? chunks [2] : null;
        System.out.println(woID);
		return woID;
	}

	/**
	 * Helper method to verify Column names in financial Summary Tab
	 */
	public boolean verifyFinancialSummaryColumns(String attriValue, String headerName, String colName[]){
		String xpath = "//div[contains(@id, '"+attriValue+"')]//div[contains(@class, 'x-grid-panel')]//span[contains(text(), '"+headerName+"')]";
		boolean status = McsElement.isElementDisplayed(driver, xpath);
		
		String colXPath = ""; 
		if(status && colName.length>=1){
			for(int i=0; i<=colName.length-1; i++){
				colXPath = xpath+"/../..//div[contains(@class, 'x-grid3-hd') and text()='"+colName[i]+"']";
				status = McsElement.isElementDisplayed(driver, colXPath);
			}
			Reporter.log("Financial Summary Details Column names are Displayed", true);
		}
		Reporter.log("Financial Summary Details Displayed", true);
		return status;
	}
	
	/**
	 * Helper method to click and change columns
	 * @throws Exception 
	 */
	public void changeColumns(String xwindowTitle, String labelName, String colNum, String parentOptions){
		
		String xpath = "//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//span[contains(text(), '"+labelName+"')]//..//..//div[contains(@class,'x-grid3-header')]//div[contains(@class, 'x-grid3-hd-"+colNum+"')]";
		WebElement we = driver.findElement(By.xpath(xpath));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", we);
		String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                "arguments[0].dispatchEvent(evObj);";
		((JavascriptExecutor)driver).executeScript(javaScript, we);
		waitForExtJSAjaxComplete(5);
		
		driver.findElement(By.xpath(xpath)).click();
		waitForExtJSAjaxComplete(5);
		
		String menuXPath = FLOATING_MENU_XPATH+"//span[contains(text(), '"+parentOptions+"')]/a";
		McsElement.getElementByXpath(driver, menuXPath).click();
		waitForExtJSAjaxComplete(5);
		
	}
	
	public void checkColumns(String options[]) throws Exception{
		for(int i=1; i<=options.length; i++) {
			String optionsXpath = FLOATING_MENU_XPATH+"//span[contains(text(), '"+options[i]+"')]/a/ancestor::li";
			String classValue = "";
			WebElement ele = McsElement.getElementByXpath(driver, optionsXpath);
			classValue = McsElement.getElementByXpath(driver, optionsXpath).getAttribute("class");
			
			if(classValue.contains("checked")){
				Reporter.log("Options is already Checked ", true);
			} else{
				ele.click();
				Reporter.log("Options is Checked ", true);
			}
		}
	}
	
	public void unCheckColumns(String options[]){
		for(int i=1; i<=options.length; i++) {
			String optionsXpath = FLOATING_MENU_XPATH+"//span[contains(text(), '"+options+"')]/a/ancestor::li";
			String classValue = "";
			WebElement ele = McsElement.getElementByXpath(driver, optionsXpath);
			classValue = McsElement.getElementByXpath(driver, optionsXpath).getAttribute("class");
			
			if(classValue.contains("checked")){
				ele.click();
				Reporter.log("Options is UNChecked ", true);
			}
		}
	}
	
	/**
	 * Helper method to verify If Columns are displayed or not
	 */
	public boolean isColumnDisplayed(String windowTitle, String labelName, String colName){
		String xpath = "//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//span[contains(text(), '"+labelName+"')]//..//..//div[contains(@class,'x-grid3-header')]//..//div[contains(text(), '"+colName+"')]/ancestor::td";
		String classValue = driver.findElement(By.xpath(xpath)).getAttribute("style");
		boolean status = false;
		if(classValue.contains("display: none;")){
			status = true;
			Reporter.log(colName+" is not displayed", true);
		} 
		return status;
	}
	
	/**
	 * Helper method to verify Currency Column is available and it can be disabled or hidden
	 */
	public void verifyColumnCanBeCollapsedOrExpanded(String colName, String colValue[]){
		String xpath = "//span[contains(text(), '"+colName+"')]//..//..//div[contains(@class, 'x-grid3-body')]/div";
		String classValue = driver.findElement(By.xpath(xpath)).getAttribute("class");
		
		
		if(classValue.contains("x-grid-empty")){
			
			Reporter.log("Grid is Empty", true);
			
		} else{
			List<WebElement> ele = driver.findElements(By.xpath(xpath));
			int sizeEle = ele.size();
			
			String className = "";
			boolean status = false;
			
			for(int i=0; i<sizeEle; i++){
				WebElement eleVal = driver.findElement(By.xpath(xpath+"//div[contains(text(), '"+colValue[i]+"')]"));
				status = eleVal.isDisplayed();
				
				if(status){
					className = eleVal.getAttribute("class");
					
					//Collapse and Expand
					if(className.contains("x-grid-group-collapsed")){
						Reporter.log("Column is Initially Collapsed", true);
						eleVal.click();
						Reporter.log("Column is Now Expanded", true);
					} else{
						Reporter.log("Column is Initially Expanded", true);
						eleVal.click();
						Reporter.log("Column is Now Collapsed", true);
						eleVal.click();
						Reporter.log("Column is Finally Expanded", true);
					}
				} else{
					Reporter.log("Column is not available in the grid", true);
				}
			}
		}
		Reporter.log("Column can be Either Displayed or Hidden", true);
	}
	
	/**
	 *  Helper method to get column number in Grid 
	 */
	public int getGridColumnNumberCustomizedWithoutQuickFilters(String labelName, String columnName){
		
		WebElement ele = driver.findElement(By.xpath("//span[contains(text(), '"+labelName+"')]//..//..//div[contains(@class, 'x-grid3-header')]//div[contains(text(), '"+columnName+"')]"));
		
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String columnClass = ele.getAttribute("class"); 
		String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);
			
		return Integer.parseInt(columnNumber);
	}
	
	/**
	 * Helper method to verify information of Product or Service which has been consumed
	 * className = x-grid3-row x-grid3-row-last / x-grid3-row
	 */
	public String verifyConsumedProductDetails(String currArr[], String colName, String currLabel, String colClass){
		verifyColumnCanBeCollapsedOrExpanded("Expenses", currArr);
		waitForExtJSAjaxComplete(3);
		
		int colNo = getGridColumnNumberCustomizedWithoutQuickFilters("Expenses", colName);
		
		String xpath = "//div[contains(text(), '"+currLabel+"')]/..//following-sibling::div//div[contains(@class,'"+colClass+"')]//div[contains(@class, 'x-grid3-col-"+colNo+"')]";
		return McsElement.getElementByXpath(driver, xpath).getText();
	}
	
	/**
	 * Helper method to get Window id By class
	 */
	public String getWindowIdByClassCustomized(String xwindowClass) throws NoSuchElementException {

		String elementid = driver.findElement(By.xpath("//div[contains(@class, '" + xwindowClass + "') and contains(@style, 'visibility: visible')][last()]")).getAttribute("id");
		
		return elementid;
	}
	
	/**
	 * This method is used to check row in Grid when the test is inside Span
	 * bom-grid x-grid-with-col-lines
	 * @return
	 */
	public WebElement checkRowInGriByTextValueAndClickCustomized(String attr, String attrValue, String textValue)  {
		String xpath = "//div[contains("+attr+",'"+attrValue+"')]//tr//span[starts-with(text(),'"+textValue+"')]";
		WebElement webElement = driver.findElement(By.xpath(xpath));
		if(webElement.isDisplayed()){
			webElement.click();
		}
		Reporter.log("Check element "+textValue+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	
	/**
	 * Helper method to verify Checklist Tab
	 */
	public boolean isCheckListOpenedAsNewTab(String attribute, String checklistName){
		try{
			driver.findElement(By.xpath("//div[contains(@id, '"+attribute+"')]//span[contains(text(), 'Work Orders -')]/ancestor::li/following-sibling::li//span[contains(text(), 'Checklists - "+checklistName+"')]"));
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Helper method to Enter Comment in Template
	 */
	public boolean enterComments(String comment, String labelName) throws Exception{
		String xpath = CHECKLIST_TEMPLATE_XPATH+"//label[contains(text(), '"+labelName+"')]//..//textarea";
		
		boolean status = McsElement.isElementDisplayed(driver, xpath);
		WebElement ele = driver.findElement(By.xpath(xpath));
		
		if(status){
			ele.click();
			ele.clear();
			ele.sendKeys(comment);
		} else {
			throw new ElementNotVisibleException("Enter comment Element is not visible");
		}
		Reporter.log("Comments Entered in Checklist Template", true);
		return status;
	}
	
	/**
	 * Helper method to getValue of Comments In Checklist
	 */
	public String getCommentsFromCheckList(String labelName, String rowNum){
		String xpath = CHECKLIST_TEMPLATE_XPATH+"//label[contains(text(), '"+labelName+"')]/ancestor::div[contains(@class, 'quality-question-item')]/following-sibling::div[contains(@class, 'x-panel')]/..//textarea";
		return McsElement.getElementByXpath(driver, xpath).getAttribute("value");
	}
	
	/**
	 * Helper method to add File to Parent CheckList Item
	 */
	public void clickAttachmentOptionsChecklistItem(String labelName, String buttonName){
		McsElement
				.getElementByXpath(
						driver,
						CHECKLIST_TEMPLATE_XPATH+"//label[contains(@class, 'x-form') and text()='"+labelName+"']/ancestor::div[contains(@class, 'quality-question-item commented')]//div[contains(@class, 'mcs-dialog')]//button[contains(text(), '"
								 +buttonName +"')]").click();
		waitForExtJSAjaxComplete(20);
	}
	
	/**
	 * Helper method to set File For Checklist Item
	 * XPATH Removed since There will be Parent and Child checklist items
	 */
	public void setFileForCheckListItems(String file, String description, String remark, String type){
		waitForExtJSAjaxComplete(25);
		FileUploader.uploadFileName(driver, "@class", "x-window", file);
		waitForExtJSAjaxComplete(25);
		
		
		McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).clear();
		McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).sendKeys(description);
		
		McsElement.getElementByXpath(driver, HYPERLINK_REMARK).clear();
		McsElement.getElementByXpath(driver, HYPERLINK_REMARK).sendKeys(remark);
		
		int xwindowNumber=0;
		
		try {driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		xwindowNumber = driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size();
		}
		finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);}
		
		waitFocusAndClick("//div[contains(@class,'x-window')]//label[contains(text(),'Type')]/..//button");
		
		waitForExtJSAjaxComplete(10);
		waitForExtJSAjaxComplete(10);
		
		try {driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		if (driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size() == xwindowNumber)
			{waitAndClick("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");}
		}
		
		finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
		}
		
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters(type, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		
		McsElement.getElementByXpath(driver, ADD_URL_CREATE_XPATH).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("file was uploaded <br>", true);
	}
	
	/**
	 * Helper method to verify Row is Present in the Grid
	 */
	public boolean isRowInGridPresent(String labelName, String rowNum, String fileName){
		String xpath = CHECKLIST_TEMPLATE_XPATH+"//label[text()='"+labelName+"']/ancestor::div[contains(@class, 'quality-question-item commented')]//div[contains(@class, 'x-grid3-body')]//div["+rowNum+"]//div[contains(text(), '"+fileName+"')]";
		try{
			driver.findElement(By.xpath(xpath));
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Helper method to click on CheckBox in Checklist Item
	 */
	public void checkWOChecklistItemsInGrid(String labelName){
		McsElement.getElementByXpath(driver, "//label[contains(@class, 'x-form-cb-label') and text()='"+labelName+"']/ancestor::div[contains(@class, 'quality-question-item commented')]//input[@type='checkbox']").click();
		waitForExtJSAjaxComplete(10);
	}
	
	/**
	 * Helper method to click on Submit Button Checklist Page 
	 */
	public void clickSubmitButton(){
		McsElement.getElementByXpath(driver, "//div[contains(@class, 'quality-form')]//button[text()='Submit']").click();
		waitForExtJSAjaxComplete(20);
	}
	
	/**
	 * Helper method to get Status of Checklist From Grid
	 */
	public String getFilledOutStatusOfChecklistTemplate(String attribute, String attributeValue, String columnName, String attributeClass, String tempName){
		int colNo = getGridColumnNumberWithQuickFilters(attribute, attributeValue, columnName);
		
		String xpath = "//div[contains(@class, '"+attributeClass+"')]//tr//div[contains(text(), '"+tempName+"')]/../following-sibling::td//div[contains(@class, 'x-grid3-col-"+colNo+"')]/div";
		return McsElement.getElementByXpath(driver, xpath).getAttribute("class");
	}

	/**
	 * Method allows to check if row with one or some values is present in grid
	 * example: textValue = "reference1|code2"  (values in grid to search should be separated by "|")
	 * @param webDriver WebDriver instance to use
	 * @param textValue for search
	 * @throws NoSuchElementException
	 * @return true if row is present
	 */
	public  boolean isRowInGridPresent(WebDriver webDriver, String textValue, String parentAttr, String parentAttrValue) {
		
		String[] splitted = null;
		
		if (textValue.contains("|")) {
			splitted = textValue.split("\\|");
		} else {
			splitted = new String[] {textValue};
		}
			
		String xpath= "//table[contains(" +parentAttr +",'"+parentAttrValue+"')]//tr"+ xpathGeneratorForTextElement(splitted[0]);
		//"//div[text()='"+splitted[0]+"']";
		
		for (int i=1; i<splitted.length; i++){
			xpath = xpath + "//ancestor::tr"+xpathGeneratorForTextElement(splitted[i]);
			//div[text()='"+splitted[i]+"']";
		}
		
		
		try {
			webDriver.findElement(By.xpath(xpath));
		} catch (Exception e) {
			Reporter.log("row can not be found in grid: " +xpath,true);
			return false;
		}
		
		Reporter.log("Row with text " + textValue  + " was successfully found", true);

		return true;
	}
	
	/**
	 * Helper method to click on tabs in template window
	 * @param windowTitle of the template window
	 * @param tabText text of tab to be clicked
	 */
	public void clickOnTab(String tabText){
		
		McsElement.getElementByXpath(driver , TEMPLATES_PANEL_CLASS+XPATH_FOR_UNIQUE_ELEMENT+"//span[contains(@class,'x-tab-strip-text') and contains(text(),'"+tabText+"')]").click();
	}
	
	
	/**
	 * Helper Method to Click security Tab
	 */
	public void clickSecurityTab(){
		clickOnTab("Security"); 
	}
	
	/**
	 * Helper method to click on save changes button
	 */
	
	public void clickSaveChangesButton()
	{
	String xpath=TEMPLATES_PANEL_CLASS+"//button[contains(@class,'x-btn-text icon-save')]";	
	McsElement.getElementByXpath(driver, xpath).click();
	}
	
	/**
	 * Helper method to click Add Account button
	 */
	
	public void clickAddAccountButton()
	{
		McsElement.getElementByXpath(driver,TEMPLATES_PANEL_CLASS+"//button[contains(@class,'x-btn-text icon-account') and text()='Add Account']").click();;
	}
	
	/**
	 * Helper method to click Add group button
	 */
	
	public void clickAddGroupButton(String grpName)
	{
		String xpath=TEMPLATES_PANEL_CLASS+"//button[contains(@class,'x-btn-text icon-account') and text()='Add Group']";
		McsElement.getElementByXpath(driver,xpath).click();
		setValueGridLookupWithFilters("@id", getXWindowId("Select an Account Group"), grpName, "Name");

	}
	
	
	/**
	 * Helper method to Expand/Collapse Template Group
	 */
	public void expandTemplateGroup(String templateGroup){
		WebElement element=driver.findElement(By.xpath(TEMPLATES_PANEL_CLASS +"//span[text()='"+templateGroup+"']/ancestor::div[contains(@class, 'x-tree-node-el')]"));
		String classValue = element.getAttribute("class");
		if(classValue.contains("collapsed")){
			element.findElement(By.xpath("./img[contains(@class,'x-tree-ec-icon x-tree-elbow-plus')]")).click();
			waitForExtJSAjaxComplete(10);
		} else{
			Reporter.log("Template Group tree is already expanded", true);
		}
		Reporter.log("Template Group is Expanded", true);
	}
	
	
	/**
	 * Helper method to expand WO template section
	 * @param name
	 */
	public void expandAllTemplatedGroupsInAdmin(){
		String xpath=TEMPLATES_PANEL_CLASS+"//div[contains(@class,'x-box-inner')]//preceding-sibling::div[contains(@class,'x-tool')]";
			String classValue = McsElement.getElementByXpath(driver, xpath).getAttribute("class");
			if(!classValue.contains("minus")){
				driver.findElement(By.xpath(xpath)).click();
				waitForExtJSAjaxComplete(10);
			} else{
				Reporter.log("all groups tree is already expanded", true);
			}
			Reporter.log("Templates groups are Expanded", true);
		}

	 /**
	 * Helper method to expand WO template section
	 * @param name
	 */
	public void collapseAllTemplatedGroups(){
		String xpath=TEMPLATES_PANEL_CLASS+"//div[contains(@class,'x-box-inner')]//preceding-sibling::div[contains(@class,'x-tool')]";
			String classValue = McsElement.getElementByXpath(driver, xpath).getAttribute("class");
			if(classValue.contains("minus")){
				driver.findElement(By.xpath(xpath)).click();
				waitForExtJSAjaxComplete(10);
			} else{
				Reporter.log("all groups tree is already collapsed", true);
			}
			Reporter.log("Templates groups are collapsed", true);
		}

	/**
	 * Helper method to get WO Templates to based on related object
	 *  
	 */
	public String getWORelateiveObjectType(String template)
	{
		String relatedObjectType=null;
		List<WebElement> elements =driver.findElements(By.xpath(ADMINISTRATION_WO_TEMPLATE_GROUP+"//span[text()='"+template+"']"));

		for(WebElement element:elements)
		{
			element.click();
			WorkOrderPageObject workOrders = new WorkOrderPageObject();
			relatedObjectType=workOrders.getRelatedObjectType();
		}

		return relatedObjectType;
	}
	
	/**
	 * Helper Method to Click Restrictions Tab
	 */
	public void clickRestrictionsTab(){
		clickOnTab("Restrictions"); 
	}
	/**
	 * Helper method to click maintenance object category button
	 */
	
	public void clickMaintenanceObjectCategoryButton()
	{
		String xpath=TEMPLATES_PANEL_CLASS+"//span[contains(@class,'x-tab-strip-text ') and text()='Maintenance Object Categories']";
		McsElement.getElementByXpath(driver,xpath).click();
	}

	/**
	 * Helper method to get maintenance object category 
	 */


	public String getMaintenanceObjectCategoryReference()
	{
		try{
			String columnClass = McsElement.getElementByXpath(driver,"//div[contains(@class,'x-grid-panel') and not(contains(@class,'x-hide-display'))]//div[contains(@class,'x-grid3-hd') and contains(text(),'Reference')])[last()]").getAttribute("class");
			String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
			String xpath = "//div[contains(@class,'x-grid-panel') and not(contains(@class,'x-hide-display'))]//div[contains(@class,'x-panel-body-noborder')]//div[contains(@class,'x-grid3-col-"+columnNumber+"')]"; 
			WebElement element= McsElement.getElementByXpath(driver,xpath);
			return element.getText();
		}
		catch(Exception e)
		{
			try{String ele="//div[contains(@class,'x-grid-panel') and not(contains(@class,'x-hide-display'))]//div[contains(@class,'x-panel-body-noborder')]//div[contains(@class,'x-grid-empty')]";
			return McsElement.getElementByXpath(driver,ele).getText();
			}
			catch(Exception e1){
				try{
					String xpath="//div[contains(@class,'x-grid-panel') and not(contains(@class,'x-hide-display'))]//div[contains(@class,'x-panel-body-noborder')]//div[contains(@class,'x-grid3-body')]/*";
					return McsElement.getElementByXpath(driver,xpath).getText();
				}
				catch(Exception e2)

				{
					return "No Maintenance Object Category restrictions";
				}
			}
		}	
	}
	
	/**
	 * Helper method to click on WO Template create button
	 */
	public void clickWOTemplateCreateButton()
	{
		clickXPath(TEMPLATES_PANEL_CLASS+"//button[contains(@class,'icon-template-add')]");
	}
	/**
	 * Helper method to click on WO Template Delete button
	 */
	public void clickWOTemplateDeleteButton()
	{
		clickXPath(TEMPLATES_PANEL_CLASS+"//button[contains(@class,'icon-template-delete')]");
	}
	/**
	 * Helper method to get list of values for work order type
	 */
	public List<String> getWOTypeLookUpValues()
	{
		return getLookUpValues("Select a Work Order Type:","Select a Work Order Type","Reference");
		
	}
		/**
		 * Helper method to get list of dropdown values
		 * @return list of related object type
		 */
		public List<String> getDropDownValuesForRelativeObjectType(){

			String xpath = ADMINISTRATION_WO_TEMPLATE_DETAILS + "//input[@name='relatedObjectType']";
							
			String elementId=driver.findElement(By.xpath(xpath)).getAttribute("id");
			
			return DropDown.getComboValuesFromDropDownSelector(driver, elementId);
		}
		
	/**
	 * Helper method to get list of lookup values
	 * @param fieldLabel to be clicked
	 * @param windowTitle 
	 * @param colName to get values
	 * @return retrieved values
	 */
	private List<String> getLookUpValues(String fieldLabel , String windowTitle, String colName ){

		waitForExtJSAjaxComplete(25);

		clickLookup(fieldLabel);

		waitForExtJSAjaxComplete(25);

		return getValuesFromGridLookup("@id",getXWindowId(windowTitle), colName);

	}
	
	/**
	 * Method allows to click on lookup button on the form for some input
	 * @param  formClass - class of the window where lookup is present
	 * @param inputName - name of the input for which lookup is opened 
	 */
	public void clickLookup(String fieldLabel){
		Timer timer = new Timer().start();

		String xpath = ADMINISTRATION_WO_TEMPLATE_DETAILS+"//label[contains(text(),'"+fieldLabel+"')]/..//input";
		
		String name = driver.findElement(By.xpath(xpath)).getAttribute("name");
		
		waitFocusAndClick(ADMINISTRATION_WO_TEMPLATE_DETAILS+"//input[@name='"
				+ name + "']//..//..//button");
		
		waitForExtJSAjaxComplete(5);

		Reporter.log("lookup was clicked"+ " (" + timer.stop()+ "ms)", true);
		
	}
	/**
	 * Helper method to set
	 * @param reference
	 */
	public void setWorkOrderTypeInWOTemplate(String workOrderType) {
		
		clickLookup("Select a Work Order Type:");
		waitForExtJSAjaxComplete(5);
		setValueGridLookupWithFilters("@class","x-resizable", workOrderType, "Reference");
		waitForExtJSAjaxComplete(15);
		Reporter.log("WOType "+ workOrderType+" was set", true);
	}
	/**
	 * Helper method to select the template
	 */
	public boolean isWOTemplateCreated(String tempName){
		String xpath = ADMINISTRATION_WO_TEMPLATE_GROUP+"//span[contains(text(), '"+tempName+"')]";
		return  McsElement.isElementDisplayed(driver, xpath);
		
	}

	/**
	 * Helper method to get Work Order Type
	 */
	public String getWorkOrderType(){
		String xpath ="//div[contains(@class, 'x-form-label-top')]//label[contains(text(), 'Select a Work Order Type:')]/..//input[@type='text']";
		return McsElement.getElementByXpath(driver, xpath).getAttribute("value");
	}
	
	
	/**
	 * Helper method to get work order templates
	 * @return list of templates
	 */
	
	public List<String> getAllAvailableTemplates()
	{
		
		waitForExtJSAjaxComplete(5);
		
		List<String> availableInspectionTemplates = new ArrayList<String>();
		
		List<WebElement> templateList =driver.findElements(By.xpath("//span[contains(text(), 'Work Order Templates')]/../following-sibling::div//span[contains(@class,'tplNode')]/div"));
		
		for(WebElement templateListsEle:templateList ){
			
			
			availableInspectionTemplates.add(templateListsEle.getText().trim());
		}

		return availableInspectionTemplates;
	}

	
	 /**
     * Helper method to check if the button is disabled or not
     * */
	
	public boolean isDisabledDeleteButton(String buttonClass)
	{
		WebElement  element= McsElement.getElementByXpath(driver,ADMINISTRATION_WO_TEMPLATE_GROUP+"//button[contains(@class,'"+buttonClass+"')]//ancestor::table[contains(@class,'x-btn-icon')]");

	   	 String classAttrVal =element.getAttribute("class");

	   	 return (classAttrVal.contains("noedit") ||classAttrVal.contains("readonly") || classAttrVal.contains("disabled") )?true:false;

	}
	
	/**
	 * Helper method to click Tab
	 * Product Reference
	 */
	public void clickTab(String tabName){
		McsElement.getElementByXpath(driver, "//span[contains(@class,'x-tab-strip-text') and contains(text(),'"+tabName+"')]//ancestor::a").click();
		waitForExtJSAjaxComplete(25);
	}
	/**
	 * Helper method to get List of Account and Group
	 * @return list of Accounts and Groups
	 */

	public List<String> getAccountGroupsList()
	{
		List<String> availableAccountGroups = new ArrayList<String>();
		List<WebElement> templateList =driver.findElements(By.xpath("//div[contains(@class,'x-list-body-inner')]//dl"));
		for(WebElement templateListsEle:templateList ){
			availableAccountGroups.add(templateListsEle.getText().trim());
		}
		return availableAccountGroups;
	}

	/**
	 * Helper method to select account Group
	 */
	public void selectAccGroup(String groupName)
	{
		String xpath="//em[contains(text(),'"+groupName+"')]//img[contains(@src,'icon-group')]";
		McsElement.getElementByXpath(driver, xpath).click();
	}

	/**
	 * Helper method to select accountName 
	 */
	public void selectAccountName(String accountName)
	{
		String xpath="//em[contains(text(),'"+accountName+"')]//img[contains(@src,'icon-account')]";
		McsElement.getElementByXpath(driver, xpath).click();
	}

	/**
	 * Helper method to verify whether the template is created at second level in hierarchy
	 * @param parentValue parent template
	 * @param tempName to be verified
	 * @return true if present
	 */
	public boolean isTemplateCreatedAtSecondLevelInHierarchy(String parentValue,String tempName){
		String childXpath = "//span[contains(text(), '"+parentValue+"')]/../../following-sibling::ul//span[contains(text(), '"+tempName+"')]";
		boolean status = McsElement.isElementDisplayed(driver, childXpath);
		if(status){
			McsElement.getElementByXpath(driver, childXpath).click();
			Reporter.log("New Template is at second level in the hierarchy of templates tree and Clicked on Template "+tempName+" ", true);
			return true;
		}
		else{
			Reporter.log("New Template is not found in Template tree", true);
			return false;
		}
	}

	/**
	 * verify whether the template/group is selected or not  
	 * @param attributeValue (grpLabel/templabel)
	 * @param TempName to be verified 
	 * @return true if selected
	 */
	public boolean isTempOrTempGrpSelected(String attributeValue, String TempName){
		String xpath = "//div[contains(@class,'x-tree-selected')]//div[@class='"+attributeValue+"' and contains(text(),'"+TempName+"')]";
		return  McsElement.isElementDisplayed(driver, xpath);
	}

	/**
	 * Helper method to get check box field status
	 * @param fieldName to get status
	 * @return true if checked
	 */
	public boolean getCheckBoxFieldStatus(String fieldName){
		String status =  driver.findElement(By.xpath(TEMPLATES_PANEL_CLASS+"//span[contains(text(),'"+fieldName+"')]/../preceding-sibling::input")).getAttribute("checked");
		return (status==null||status.equals(false))?false:true;
	}

	/**
	 * Helper method to get field id value
	 * @param fieldLabel to get id
	 * @return id
	 */
	public String getFieldIdBasedOnLabel(String fieldLabel){
		String xpath =TEMPLATES_PANEL_CLASS+ "//label[contains(text(),'"+fieldLabel+"')]/..//input";
		return McsElement.getElementByXpath(driver, xpath).getAttribute("id");
	}

	/**
	 * Helper method to check is filed editable or not
	 * @param labelName to be checked
	 * @return false if not editable
	 */
	public boolean isFieldEditable(String labelName){
		String xpath = TEMPLATES_PANEL_CLASS+ "//label[contains(text(),'"+labelName+"')]/..//input";
		String className = driver.findElement(By.xpath(xpath)).getAttribute("class");
		return (className.contains("readonly")||className.contains("noedit"))?false:true;
	}

	/**
	 * verify is button displayed or not 
	 * @param classValue (x-btn-text-icon) class name 
	 * @param buttonName to be verified
	 * @return true if present
	 */
	public boolean isButtonPresent(String classValue,String buttonName){
		String xpath = TEMPLATES_PANEL_CLASS+"//table[contains(@class,'"+classValue+"') and contains(.,'"+buttonName+"')]";
		return McsElement.isElementDisplayed(driver, xpath);
	}

	/**
	 * Helper method to verify is account present in grid or not
	 * @param accountName which needs to be verified
	 * @param accountType type of account to be verified (icon-account/icon-group)
	 * @return true if present
	 */
	public boolean isRowPresentInSecurityTab(String accountName,String accountType){
		String xpath = "//div[@class='x-list-body-inner']//img[contains(@src,'"+accountType+"')]/../../../..//dl[contains(.,'"+accountName+"')]";
		return McsElement.isElementDisplayed(driver, xpath);
	}

	/**
	 * Helper method to get deep link field value of WO Template
	 * @return deep link field value
	 */
	public String getDeepLinkFromWOTemplate(){
		return McsElement.getElementByXpath(driver,TEMPLATES_PANEL_CLASS+"//input[@id='templateLink']").getAttribute("value");

	}

	/**
	 * Helper method to expand WO Templates
	 */
	public void expandAllWOTemplates(){
		String xpath = "//div[contains(@class,'helpdesk-newcall')]//div[contains(@class,'x-tool-plus')]";
		if(McsElement.isElementPresent(driver, xpath)){
			driver.findElement(By.xpath(xpath)).click();
			Reporter.log("All WO Templates are expanded",true);
		}
		else{
			Reporter.log("WO Templates are already expanded",true);
		}
	}

	/**
	 * Helper method to set reference on copying template
	 * @param reference to be filled in
	 */
	public void setCopyTemplateReferance(String reference){
		waitAndSendKeys("//div[contains(@class,'x-window-dlg')]//span[contains(text(),'Enter a reference for a new Template')]/..//input[contains(@class,'ext-mb-input')]", reference);
	}

	/**
	 * Helper method to verify template is created at first level in the hierarchy
	 */
	public boolean isTemplateCreatedAtFirstLevelInHierarchy(String tempName) throws Exception{
		String parentTmpXPath = ADMINISTRATION_WO_TEMPLATE_XPATH+ADMINISTRATION_WO_TEMPLATE_GROUP+"//div[@class='x-tree-root-node']/li[1]/div[1]//a/span";
		String parentValue = McsElement.getElementByXpath(driver, parentTmpXPath).getText();


		String childXpath = "//span[contains(text(), '"+parentValue+"')]//..//..//../following-sibling::li//span[contains(text(), '"+tempName+"')]";
		boolean status = McsElement.isElementDisplayed(driver, childXpath);
		if(status){
			Reporter.log("New Template is at first level in the hierarchy of templates tree", true);
			McsElement.getElementByXpath(driver, childXpath).click();
			Reporter.log("Clicked on Template "+tempName+" ", true);
			return true;
		}
		else{
			Reporter.log("New Template is not found in Template tree", true);
			return false;
		}
	}

	/**
	 * Helper method to collapse Administration
	 */
	public void collapseAdministration(){
		String expandedMode  = "//div[contains(@class,'mcs-tree-navigation') and not(contains(@class,'x-panel-collapsed'))]//div[contains(@class,'x-tool-collapse-west')] ";
		String collapsedMode = "//div[contains(@class,'admsettings-modulesettings')]//div[contains(@class,'x-layout-collapsed-west') and contains(@style,'visibility: visible')]//div[contains(@class,'x-tool-expand-west')]";
		if(!McsElement.isElementPresent(driver, collapsedMode)){
			driver.findElement(By.xpath(expandedMode)).click();
			Reporter.log("Administration panel is collapsed",true);
		}
	}
	
	/**
	 * Helper method to select work order template group
	 */
	public void selectWorkorderTemplateGroup(String tempGrpName)
	{
		clickXPath(TEMPLATES_PANEL_CLASS+"//a//span[text()='"+tempGrpName+"']");
	}

	/**
	 * Helper method to click on WO Template Copy button
	 */
	public void clickWOTemplateCopyButton()
	{
		clickXPath(TEMPLATES_PANEL_CLASS+"//button[contains(@class,'icon-template-copy')]");
	}

	/**
	 * Helper method to click on TemplateGroup tab
	 */
	public void clickTemplateGroupstab()
	{
		clickXPath("//span[text()='Template Groups' and contains(@class,'tab')]");
		waitForExtJSAjaxComplete(25);
	}

	/**
	 * Helper method to click on Add Button in Template Group
	 */
	public void clickAddTemplateGroups()
	{
		clickXPath("//div[contains(@class,'admsettings-modulesettings')]//div[contains(@class,'x-grid3-hd') and contains(.,'Path')]/ancestor::div[contains(@class,'x-tab-panel-body-noborder')]//button[text()='Add']");
		waitForExtJSAjaxComplete(25);
	}

	/**
	 * Helper method to select Template Group from lookup
	 */

	public void selectTemplateGroupFromLookup(String tempGrpName,String...parentGrpNames)
	{

		
		//To expand if templategroup is present under someother group(s)
		if(parentGrpNames!=null){
			for(int i=0; i< parentGrpNames.length ; i++){


				String xpath = "//div[contains(@class, 'x-resizable-pinned') and contains(@style,'visibility: visible')]//span[text()='"+parentGrpNames[i]+"']/../..";

				WebElement element = driver.findElement(By.xpath(xpath));

				if(element.getAttribute("class").contains("collapsed")){

					element.findElement(By.xpath(".//img[contains(@class, 'x-tree-ec-icon')]")).click();
					

				} 

				waitForExtJSAjaxComplete(2);
			}
		}
	
		
		//To select template group
		String xpath="//div[contains(@class, 'x-resizable-pinned') and contains(@style,'visibility: visible')]//ul[contains(@class,'x-tree-node-c')]//span[text()='"+tempGrpName+"']";

		WebElement ele=driver.findElement(By.xpath(xpath));

		Actions act = new Actions(driver);
		act.doubleClick(ele).build().perform();
		waitForExtJSAjaxComplete(25);

		clickOkXwindow();
	}

	/**
	 * Helper method to click on Save Changes  Button in Template Group
	 */
	public void clickSaveChangesTemplateGroupstab()
	{
		clickXPath("//div[contains(@class,'x-tab-panel-bbar x-tab-panel-bbar-noborder')]//button[contains(@class,' icon-save')]");
		waitForExtJSAjaxComplete(25);
	}
	
	/**
	 * Helper method to verify BOM Columns
	 * Product Reference
	 */
	public boolean isColumnPresentInBOMGridOfWOTemplate(String product){
		try{
			driver.findElement(By.xpath("//div[contains(@class, 'x-panel bom-grid')]//div[contains(@class,'x-grid3-hd') and contains(text(), '"+product+"')]"));
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Helper method to click Add Button in Bill Of Materials Tab
	 */
	public void clickAddButtonInBOMInTemplate() {
		clickXPath("//span[text()='Bill of Materials']/ancestor::div[contains(@class,'x-tab-panel-header')]/following::button[contains(@class, 'icon-row-add') and text()='Add']");
	}
	/**
	 * Helper method allows to add BOM line
	 * Product Reference
	 */
	public void addABOMLineInTemplate(String productRef){
		clickAddButtonInBOMInTemplate();
		setValueGridLookupWithFiltersWithScrollInToView("@id",getXWindowId("Select a Product or Service"), productRef, "Reference");

	}

	/**
	 *  Method allows to select row from grid in lookup using filters
	 * @param attribute - attribute (@class, @id) of the lookup window
	 * @param attributeValue - attribute of the lookup must contain this value
	 * @param rowTextName - row text to be selected from lookup 
	 * @param columnName - columnName of the row to be selected
	 */
	public boolean setValueGridLookupWithFiltersWithScrollInToView(String attribute, String attributeValue, String rowTextName, String columnName) {

		Timer timer = new Timer().start();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);
		filterGrid(attribute, attributeValue, rowTextName, columnName);	

		waitForExtJSAjaxComplete(25);

		String elementXpath = "(//div[contains("+attribute+",'" + attributeValue + "')]//div[contains(@class,'x-grid3-hd')"
				+ "and contains(text(),'" + columnName + "')" + "])[last()]";


		WebElement element = driver.findElement(By.xpath(elementXpath));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch(Exception e){
			e.printStackTrace();
		}
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
		String columnClass = element.getAttribute("class");
		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
		waitForExtJSAjaxComplete(25);

		try{
			String []subStrings = rowTextName.split(" ");

			String subXpathStr = "and starts-with(text(),'"+subStrings[0]+"')";

			for(int i=1; i<subStrings.length; i++){

				subXpathStr+="and contains(text(),'"+subStrings[i]+"')";
			}

			McsElement.getElementByXpath(driver,"(//"+"div"+"[contains("+attribute+",'" + attributeValue + "')]//"
					+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
					+ "and "+"text()"+subXpathStr+
					"])[last()]").click();

			waitForExtJSAjaxComplete(25);

			clickOkXwindow();
			Reporter.log(rowTextName + " was selected"+ " (" + timer.stop()
					+ "ms)", true);
			return true;
		}
		catch(Exception e){
			Reporter.log(rowTextName + " is not present"+ " (" + timer.stop()
					+ "ms)", true);
			return false;
		}
		finally {
			try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
		}
	}

	/**
	 * Helper method to get Needed Quantity in BOM Page
	 */

	public String getNeededQuantityOfBOMLine(String rowNum){
		int colNo = getGridColumnNumberWithoutQuickFilters("@class", "x-panel bom-grid","Needed Quantity");
		return	getCellText(rowNum,"x-grid3-td-"+colNo+"");
	}

	/**
	 * Helper method to get Cell content
	 * @param rowNum Row num of Cell
	 * @param columnClass: Class of table cell
	 * @return value of the cell
	 */
	public String getCellText(String rowNum, String columnClass){
		String xpath = "//div[@id='bomGrid']//div[@class='x-grid3-body']//div["+rowNum+"]//td[contains(@class,'"+columnClass+"')]//div";
		return McsElement.getElementByXpath(driver, xpath).getText();
	}

	/**
	 * Helper method to Edit Needed Quantity
	 * @param rowNum of text line
	 * @param quantity to set
	 */
	public void editNeededQuantityInBOMLine(String rowNum, String quantity){
		int colNo = getGridColumnNumberWithoutQuickFilters("@class", "x-panel bom-grid","Needed Quantity");
		WebElement cell = McsElement.getElementByXpath(driver, "//div[@id='bomGrid']//div[@class='x-grid3-body']//div["+rowNum+"]//td[contains(@class,'x-grid3-td-"+colNo+"')]//div");
		Actions action = new Actions(driver);
		action.doubleClick(cell).perform();
		waitForExtJSAjaxComplete(20);
		setGridLineTextField(quantity);
		Reporter.log("Needed Quantity is Edited <br>", true);
	}

	/**
	 * Helper method to fill text field value of a Grid Element 
	 */
	public void setGridLineTextField(String value){
		String xpath = "//div[contains(@class,'x-grid-editor') and not(contains(@style,'visibility: hidden'))]//input[@type='text']";
		WebElement textField = McsElement.getElementByXpath(driver, xpath);
		textField.clear();
		textField.sendKeys(value);
		textField.sendKeys(Keys.ENTER);
	}

	/**
	 * Helper method to Select BOM Line
	 */
	public void selectBOMLineInGrid(String rowNum){
		int colNo = getGridColumnNumberWithoutQuickFilters("@class", "x-panel bom-grid","Product Reference");
		McsElement.getElementByXpath(driver, "//div[@id='bomGrid']//div[@class='x-grid3-body']//div["+rowNum+"]//td[contains(@class,'x-grid3-td-"+colNo+"')]//div").click();
		waitForExtJSAjaxComplete(10);

	}

	/**
	 * Helper method to click Remove Button in Bill Of Materials Tab
	 */
	public void clickRemoveButtonInWorkOrderTemplates() {
		clickXPath("//span[text()='Bill of Materials']/ancestor::div[contains(@class,'x-tab-panel-header')]/following::button[contains(@class, 'icon-row-remove') and text()='Remove']");
	}

	/**
	 * Helper method to verify BOM Columns
	 * Product Reference
	 */
	public boolean isSelectedBomLineAvailableInGrid(String rowNum, String quantity){
		try{
			int colNo = getGridColumnNumberWithoutQuickFilters("@class", "x-panel bom-grid","Needed Quantity");
			String xpath = "//div[@id='bomGrid']//div[@class='x-grid3-body']//div["+rowNum+"]//td[contains(@class,'x-grid3-td-"+colNo+"')]//div[text()="+quantity+"]";
			driver.findElement(By.xpath(xpath));
			return true;
		} catch(Exception e){

		}
		return false;
	}
	
	/**
	 * Helper method to Get filed value
	 * @param inpName
	 * @return
	 */
	public String getFieldValueInAddWOWindow(String inpName) {
		return McsElement.getElementByXpath(driver, ADD_WO_WINDOW_XPATH+"//input[@name='"+inpName+"']/following-sibling::input").getAttribute("value");
	}
	/**
	 * Helper method to get reference
	 * @param 
	 * @return
	 */
	public String getReferenceInAddWOWindow() {
		return McsElement.getElementByXpath(driver, ADD_WO_WINDOW_XPATH+"//input[@name='NEW_WO_REFERENCE']").getAttribute("value");
	}
	/**
	 * Helper method to click reset form button
	 */
	
	public void clickResetFormButtonInAddWOWindow()
	{
	clickXPath(ADD_WO_WINDOW_XPATH+"//button[text()='Reset Form']");
	}
	/**
	 * Helper method to get tool tip text of save button
	 * @return tool tip text
	 */
	public String getToolTipTextOfSaveButtonInAddWOWindow()

	{
		WebElement element=driver.findElement(By.xpath("//div[contains(@class,'details-general')]//button[text()='Save Workorder']"));
		Actions action = new Actions(driver);
		WebElement xpath =driver.findElement(By.xpath("//div[contains(@class,'x-tip info-tooltip')]//*[@class='x-tip-body']"));
		element.click();
		action.moveToElement(xpath).build().perform();
		return xpath.getText();

	}

	/**
	 * Helper method to identify whether the child WO create or not
	 * @param woRef parent WO reference
	 * @param childWORef to be verified
	 * @return true if present
	 */
	public boolean isChildWOCreatedInHierarchy(String woRef, String childWORef){
		String xpath = WO_WINDOW_XPATH+"//div[contains(@class, 'x-tree x-border-panel')]//span[contains(text(), '"+woRef+"')]/ancestor::div/following-sibling::ul//span[contains(text(), '"+childWORef+"')]/..";
		return McsElement.isElementDisplayed(driver, xpath);
	}

	/**
	 * Whether the button is enabled or not
	 * @param buttonName
	 * @return
	 */
	public boolean isButtonEnabled(String buttonName){
		String buttonStatus = WO_WINDOW_XPATH+"//table[contains(@class,'icon x-item-disabled')]//button[text()='"+buttonName+"']";

		if(!McsElement.isElementPresent(driver,buttonStatus)){
			try{
				driver.findElement(By.xpath(WO_WINDOW_XPATH+"//button[text()='"+buttonName+"']")).isEnabled();
				return true;
			}
			catch(Exception E)
			{
				Reporter.log("No button found",true);
				return false;
			}

		}
		else{
			return false;

		}
	}

	/**
	 * Helper method to get Criticality value from Objects Tab
	 */
	public String getCriticality(String reference){
		String xpath = "//table[contains(@class, 'treegrid-root-table')]//td//div[contains(text(), '"+reference+"')]/../following-sibling::td/div";

		String text = driver.findElement(By.xpath(xpath)).getText();
		System.out.println(text);
		return text;
	}

	/**
	 * Helper method to get Value from MO Details window based on Reference
	 * @param reference
	 * @param attrValue
	 * @return
	 */
	public String getMODetailsValues(String reference, String attrValue){
		String xpath = "//div[contains(@id, '"+getXWindowId(MAINTANCE_OBJECT_WINDOW_TITLE)+"')]//div[contains(text(), '"+reference+"')]/../following-sibling::td[contains(@class, 'x-grid3-td-1')]/div";

		String text = McsElement.getElementByXpath(driver, xpath).getAttribute(attrValue);
		System.out.println(text);
		return text;
	}

	/**
	 * Get Access Instructions Window Text
	 * @return AccessInstruction
	 */
	public String getAccessInstructionsWindowText(String attrValue){

		WebElement accessInstructions  = driver.findElement(By.xpath(WO_WINDOW_XPATH+"//div[contains(@class,'x-html-editor')]//textarea"));

		String instr = accessInstructions.getAttribute(attrValue);

		waitForExtJSAjaxComplete(20);

		return instr;
	}
	
	/**
	 * Helper method to Enter Text in Template
	 */
	public boolean enterText(String text, String labelName) throws Exception{
		String xpath = "//div[contains(@class, 'quality-question-item')]//label[contains(text(), '"+labelName+"')]/following-sibling::div//input";
		
		boolean status = McsElement.isElementDisplayed(driver, xpath);
		WebElement ele = driver.findElement(By.xpath(xpath));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
			ele.clear();
			ele.sendKeys(text);
		} catch(Exception e){
			e.printStackTrace();
		}

		Reporter.log("Text Entered in Checklist Template", true);
		return status;
	}

	/**
	 *  Helper method to click on CheckBox in Checklist Item
	 * @param itemClass (quality-paragraph/quality-question-item)
	 * @param labelName to be checked
	 */

	public void checkWOChecklistItemsInGrid(String itemClass, String labelName){
		McsElement.getElementByXpath(driver, "//div[contains(@class,'"+itemClass+"')]//label[contains(@class, 'x-form-cb-label') and text()='"+labelName+"']/preceding-sibling::input[@type='checkbox']").click();
		waitForExtJSAjaxComplete(10);
	}

	


	/**
	 * Helper method to Enter Reference
	 */
	public void setReferenceWOTemplateGrp(String reference){
		String xpath = XPATH_FOR_UNIQUE_ELEMENT+"//label[contains(text(), 'Reference')]/..//div//input[contains(@class,'x-form')]";
		WebElement ele = McsElement.getElementByXpath(driver, xpath);
		ele.click();
		ele.clear();
		ele.sendKeys(reference);
		Reporter.log("Reference is entered in WO Template", true);
	}
	
	/**
	 * Helper method to getWO Reference From Template
	 */
	public String getReferenceFromWOTemplateGrp(){
		return McsElement.getElementByXpath(driver,XPATH_FOR_UNIQUE_ELEMENT+ "//label[contains(text(), 'Reference')]/..//div//input[contains(@class,'x-form')]").getAttribute("value");
	}
	
	/**
	 * Helper method to click on WO Group Delete button
	 */
	public void clickWOGrpDeleteButton()
	{
		clickXPath(TEMPLATES_PANEL_CLASS+"//button[contains(@class,'icon-templategroup-delete')]");
	}
	
	
	/**
	 * Helper method to click on save changes button
	 */
	
	public void clickSaveChangesButtonForTempGrp()
	{
	String xpath=TEMPLATES_PANEL_CLASS+"//table[contains(@class,'x-btn-text-icon') and not (contains(@class,'x-item-disabled'))]//button[contains(@class,'x-btn-text icon-save')]";	
	McsElement.getElementByXpath(driver, xpath).click();
	}
	
	/**
	 * Helper method to verify child group is created under Selected parent Group
	 */
	public boolean isAddedWOGrpChildOfWOTemplateGrp(String tempGrpName, String tempName){
		return isWOTemplateUnderWOGroup(tempGrpName,tempName);

	}

	/**
	 * Helper method to unCheck the restricted check box
	 */
	public void uncheckRestrictedCheckBox(){
		WebElement ele =  driver.findElement(By.xpath(TEMPLATES_PANEL_CLASS+XPATH_FOR_UNIQUE_ELEMENT+"//span[contains(text(),'Restricted')]/../preceding-sibling::input"));
		ele.click();
		Reporter.log("Restricted check box is unchecked",true);
	}
	
	/**
	 * Helper method to verify DeepLink field is Read Only
	 */
	public boolean isDeeplinkFieldReadOnly(){
		String element = driver.findElement(By.xpath("//div[contains(@class,'x-form-label-top')  and not (contains(@class,'x-hide-display'))]//input[contains(@name, 'templateLink')]")).getAttribute("class");
		//String element = driver.findElement(By.xpath("//div[contains(@class,'x-tab-panel-bwrap')]//div[contains(@class,'x-tab-panel-body-top')]//div[contains(@class,'x-panel x-panel-noborder x-form-label-top')  and not (contains(@class,'x-hide-display'))]//input[contains(@name, 'templateLink')]")).getAttribute("class");
		if(element.contains("readonly")){
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Helper method to check if the passed field is disabled or not
	 **/
	public boolean isFieldDisabled(WebElement element) {
		String classAttrVal =element.getAttribute("class");
		return (classAttrVal.contains("noedit") ||classAttrVal.contains("readonly") || classAttrVal.contains("disabled") )?false:true;
	}
	/**
	 * Helper method to verify WO Template field is disabled or not
	 * @return
	 */
	public boolean isWOTempNodeDisabled()
	{
		String xpath = "//div[contains(@class,'x-panel mcs-tree-navigation')]//div[contains(@class,'x-tree-node-disabled')]//span[text()='Work Order Templates']";	
		WebElement ele=McsElement.getElementByXpath(driver,xpath);
		return isFieldDisabled(ele);
	}
	
	/**
	 * Helper method to get Text Message From Service Organization
	 * @return SrvOrg text 
	 */
	public String getTextMsgFrmSrvOrg(String srvOrg){

		String xpath = "//span[contains(text(),'"+srvOrg+"')]/following::div[contains(@class,'x-tab-panel-body-top')]//div[contains(@class,'infobar bg-success')]";

		return driver.findElement(	By.xpath(xpath)).getText();

	}

	/**
	 * Method allows to check if row with one or some values is present in grid
	 * example: textValue = "reference1|code2"  (values in grid to search should be separated by "|")
	 * @param webDriver WebDriver instance to use
	 * @param textValue for search
	 * @throws NoSuchElementException
	 * @return true if row is present
	 */
	public  boolean isRowInLocationGridPresent(WebDriver webDriver, String textValue) {
		filterGridNewUI("@class", "x6-window-default-closable", textValue, "Reference");
		String xpath= "//table[contains(@class,'x6-grid-item')]//tr//span[contains(text(),'"+textValue+"')]";
		try{
			WebElement ele = driver.findElement(By.xpath(xpath));
		} catch(Exception e){
			Reporter.log("row can not be found in grid: " +xpath,true);
			return false;
		}
		String xpath1 = "//div[contains(text(),'Select a Location')]/following::span[contains(@class,'x6-btn-inner-default-toolbar-small') and text()='Clear Filters']";
		WebElement ele1=McsElement.getElementByXpath(driver,xpath1);
		ele1.click();
		Reporter.log("Row with text " + textValue  + " was successfully found", true);
		return true;
	}
	

	///////////////////  Technical Settings ///////////////////////

	/**
	 * Helper method to click button in Technical Section
	 * @param fieldName in technical section
	 * @param buttonName to be clicked 
	 * @param className of parent
	 */
	public void clickOnButtonInTechnicalSection(String fieldName,String buttonName,String className){
		String buttonXpath = "//div[contains(@class,'admin-technicalsettings')]//label[text()='"+fieldName+"']//ancestor::div[contains(@class,'"+className+"')]//button[text()='"+buttonName+"']";
		driver.findElement(By.xpath(buttonXpath)).click();
	}

	/**
	 * Helper method to get field value in technical section
	 * @param fieldName to get the value
	 * @return value
	 */
	public String getFieldValueInTechnicalSection(String fieldName){
		String fieldXpath = "//div[contains(@class,'admin-technicalsettings')]//input[@name='"+fieldName+"']";
		return driver.findElement(By.xpath(fieldXpath)).getAttribute("value");
	}

	/**
	 * Helper method to set value in field
	 * @param fieldName to set the value
	 * @param fieldValue to be set
	 */
	public void setFieldValueInTechnicalSection(String fieldName,String fieldValue){
		String fieldXpath = "//div[contains(@class,'admin-technicalsettings')]//input[@name='"+fieldName+"']";
		driver.findElement(By.xpath(fieldXpath)).clear();;
		driver.findElement(By.xpath(fieldXpath)).sendKeys(fieldValue);
	}

	/**
	 * Helper method to click on save or cancel button in section section
	 * @param buttonName save/ cancel
	 */
	public void clickSaveOrCancleButtonInTechnicalSection(String buttonName){
		String buttonXpath = "//div[contains(@class,'admin-technicalsettings')]//div[contains(@class,'x-panel-bba')]//button[text()='"+buttonName+"']";
		driver.findElement(By.xpath(buttonXpath)).click();
	}

	/////////////////////////////////////////////////////////////

	/**
	 * Helper method to get tool tip message
	 * @return tool tip value
	 */
	public String getToolTipTextOfAddDocumentWindow(){
		String toolTipXpath="//div[contains(@class,'x-tip info-tooltip') and (contains(@style,'visibility: visible'))]//*[@class='x-tip-body']";
		return driver.findElement( By.xpath(toolTipXpath)).getText();
	}

	/**
	 * Helper method to click cancel button in add document window
	 */
	public void clickCancelButtonInAddDocWindow(){
		McsElement.getElementByXpath(driver, CANCEL_URL_XPATH).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Add Document is cancelled",true);
	}
	
	/**
	 * Method allows to check if field is mandatory
	 */
	public static boolean isFieldMandatory(WebDriver webDriver, String className, String name) {
		try {
			webDriver.findElement(By.xpath("//div[contains(@class,'"+className+"')]//label[contains(@class,'x-form-item-label-mandatory') and contains(text(),'"+name+"')]"));
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			try {webDriver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
		}
	}

	public String getReturnedByValueInQGR() {
		return McsElement.getElementByXpath(driver, "(//div[contains(@class,'storage-location-window')]//label[text()='Returned By']/..//input)[last()]").getAttribute("value");
	}

	/**
	 * Helper method to filter WO Template by reference
	 * @param tempRef
	 */
	public void filterWOTempByRef(String tempRef){
		String treeSearchXpath = "//div[contains(@class,'admsettings')]//input[@name='treesearch']";
		String binocularXpath  = "//div[contains(@class,'admsettings')]//button[contains(@class,'icon-binocular')]";
		WebElement treeSearch = driver.findElement(By.xpath(treeSearchXpath));
		treeSearch.click();
		treeSearch.clear();
		treeSearch.sendKeys(tempRef);
		waitForExtJSAjaxComplete(25);
		driver.findElement(By.xpath(binocularXpath)).click();
	}

	/**
	 * Helper method to clear the filter
	 */
	public void clearWOTempFilter(){
		String  eraserXpath = "//div[contains(@class,'admsettings')]//button[contains(@class,'icon-eraser')]";
		driver.findElement(By.xpath(eraserXpath)).click();
	}

	/**
	 * Helper method to get WO's under specific template group
	 * @param tempGrp to get WO's
	 * @return WO's list
	 */
	public List<String> getWOUnderTemplateGroup(String tempGrp){
		List<String> tempUnderWOGrp = new ArrayList<String>();
		String tempXpath = "//div[contains(@class,'admsettings-modulesettings')]//li[@class='x-tree-node']//div[contains(.,'"+tempGrp+"')]/..//div[contains(@class,'x-tree-node-leaf')]//a[contains(@class,'anchor')]/span";
		List<WebElement> webElements = driver.findElements(By.xpath(tempXpath));
		for(WebElement ele : webElements )
			tempUnderWOGrp.add(ele.getText().trim());

		return tempUnderWOGrp;
	}

	/**
	 * Helper method to get all the visible templates at node level
	 * @return wo's list
	 */
	public List<String> getAllTempFilteredAtNodeLevel(){
		List<String> filteredNodeLevelTemp = new ArrayList<String>();
		String nodeTempXpath = "//div[contains(@class,'x-panel-body')]//ul[contains(@class,'x-tree-root-ct x-tree-lines')]//div[contains(@class,'x-tree-root-node')]/li[contains(@class,'x-tree-node') and (not(@style) or (@style=''))]//ul[contains(@id,'ext-gen') and not(contains(@style,'display: none'))]//div[contains(@class,'x-tree-node-leaf')]//a[contains(@class,'node-anchor')]//span";
		List<WebElement> webElements = driver.findElements(By.xpath(nodeTempXpath));
		for(WebElement ele : webElements)
			filteredNodeLevelTemp.add(ele.getText());

		return filteredNodeLevelTemp;
	}

	/**
	 * Helper method to get all the visible templates at root level
	 * @return wo's list
	 */
	public List<String> getAllTempFilteredAtRootLevel(){
		List<String> filteredRootLevelTemp = new ArrayList<String>();
		String rootTempXpath = "//div[contains(@class,'x-panel-body')]//ul[contains(@class,'x-tree-root-ct x-tree-lines')]//div[contains(@class,'x-tree-root-node')]/li[contains(@class,'x-tree-node') and (not(@style) or (@style=''))]/div[contains(@class,'x-tree-node-leaf')]";
		List<WebElement> webElements = driver.findElements(By.xpath(rootTempXpath));
		for(WebElement ele : webElements)
			filteredRootLevelTemp.add(ele.getText());

		return filteredRootLevelTemp;
	}

	/**
	 * Helper method to get all available/visible template in the template panel
	 * @return wo's list
	 */
	public List<String> getAllFilteredTemp(){
		List<String> filteredTemp = new ArrayList<String>();
		filteredTemp.addAll(getAllTempFilteredAtRootLevel());
		filteredTemp.addAll(getAllTempFilteredAtNodeLevel());

		return filteredTemp;
	}

	/**
	 * Helper method to get all available/visible template groups in the template panel
	 * @return wo's group list 
	 */
	public List<String> getAllFilteredTempGrp(){
		List<String> filteredTempGrp = new ArrayList<String>();
		String filteredTempGrpXpath = "//div[contains(@class,'x-panel-body')]//ul[contains(@class,'x-tree-root-ct x-tree-lines')]//div[contains(@class,'x-tree-root-node')]/li[contains(@class,'x-tree-node') and (not(@style) or (@style=''))]/div[contains(@class,'x-tree-node-el x-unselectable')]//a[contains(@class,'node-anchor')]//span";
		List<WebElement> webElements = driver.findElements(By.xpath(filteredTempGrpXpath));
		for(WebElement ele : webElements ){
			filteredTempGrp.add(ele.getText());
		}
		return filteredTempGrp;
	}

	/**
	 * Helper method to get all available/visible templates, template groups in the template panel
	 * @return available list of wo's and wo's group
	 */
	public List<String> getAllFilteredTempAndTempGrp(){
		List<String> filteredTempOrTempGrp = new ArrayList<String>();
		filteredTempOrTempGrp.addAll(getAllFilteredTemp());
		filteredTempOrTempGrp.addAll(getAllFilteredTempGrp());
		return filteredTempOrTempGrp;
	}
	
}


