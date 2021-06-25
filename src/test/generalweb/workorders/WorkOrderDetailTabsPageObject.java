package test.generalweb.workorders;




import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
//import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.google.common.base.Function;

import test.configuration.Configuration;
import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.FileUploader;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class WorkOrderDetailTabsPageObject extends AbstractMcsTestSuite {
	
	protected final String HYPERLINK_DESCRIPTION = "//input[@name='strDescription']";
	
	protected final String HYPERLINK_REMARK = "//input[@name='strRemark']";
			
	protected final String ADD_URL_XPATH = "//button[text()='Add URL' and contains(@style,'icon-addurl.png')]";
	
	protected final String HYPERLINK_XPATH = "//input[@name='strHyperlink']";
	
	protected final String ADD_URL_CREATE_XPATH = "//div[contains(@class,'x-window')]//button[text()='Create']";
	
	protected final String CANCEL_URL_XPATH = "//div[contains(@class,'x-window') and contains(@style,'visibility: visible')]//div[contains(@class,'x-window')]//button[text()='Cancel']";

	protected final String ADD_URL_SAVE_XPATH = "//div[contains(@class,'x-window')]//button[text()='Save']";
	
	protected final String ADD_FILE_XPATH = "//button[text()='Add File' and contains(@style,'icon-addfile.png')]";
	
	protected final String EDIT_URL_XPATH = "//button[text()='Edit' and contains(@style,'icon-edit.png')]";
	
	protected final String DELETE_URL_XPATH = "//button[text()='Delete' and contains(@style,'icon-delete.png')]";
	
	protected final String MAINTANCE_OBJECT_TAB_XPATH = "//div[contains(@class,'hdwo-details')]//span[contains(text(),'Objects')]";
	
	protected final String CHECKLISTS_OBJECT_TAB_XPATH = "//div[contains(@class,'hdwo-details')]//span[contains(text(),'Checklists')]";
	
	protected final String DESCRIPTION1_TAB_XPATH = "(//div[contains(@class,'hdwo-details')]//span[contains(text(),'Description')])[1]";
	
	protected final String DESCRIPTION2_TAB_XPATH = "(//div[contains(@class,'hdwo-details')]//span[contains(text(),'Description')])[2]";
	
	protected final String TIME_MATERIAL_TAB_XPATH = "//div[contains(@class,'hdwo-details')]//span[contains(text(),'Material') and contains(text(),'Time')]";
	
	protected final String TRACKING_TAB_XPATH = "//div[contains(@class,'hdwo-details')]//span[contains(text(),'Tracking')]";
	
	protected final String TIME_MATERIAL_CONSUMPTION_TAB_XPATH ="//div[contains(@class,'hdwo-details')]//span[contains(text(),'Consumption')]"; 
	
	protected final String TIME_MATERIAL_BILLMATERIAL_TAB_XPATH ="//div[contains(@class,'hdwo-details')]//span[contains(text(),'Bill of Materials')]";
	
	protected final String BILLING_TAB_XPATH ="//div[contains(@class,'hdwo-details')]//span[contains(text(),'Billing')]";

	protected final String TRACKING_HISTORY_TAB_XPATH ="//div[contains(@class,'hdwo-details')]//span[contains(text(),'History')]";

	protected final String TIME_MATERIAL_LABOR_TAB_XPATH ="//div[contains(@class,'hdwo-details')]//span[contains(text(),'Labor')]";
	
	protected final String ACTIVITY_WINDOW_CLASS = "x-window";
	
	protected final String ACTIVITY_GENERAL_TAB = "//div[contains(@class,'x-window')]//span[text()='General Information']";

	protected final String ACTIVITY_FINANCIAL_TAB = "//div[contains(@class,'x-window')]//span[text()='Financial Details']";
	
	protected final String ACTIVITY_NOTES_TAB = "//div[contains(@class,'x-window')]//span[text()='Activity Notes']";
	
	protected final String ATTACHMENTS_TAB_XPATH = "//div[contains(@class,'hdwo-details')]//span[contains(text(),'Attachments')]";
	
	protected final String WORKORDER_WINDOW_DETAIL = "x-resizable";
	
	protected final String WORKORDER_PANEL_DETAIL = "x-border-panel";
	
	protected final String ADD_CONSUMPTION_WINDOW_HEADER = "Add Consumption";
	
	protected final String EDIT_CONSUMPTION_WINDOW_HEADER = "Edit Consumption";
	
	protected final String ADD_ACTIVITY_WINDOW_HEADER = "Add Activity";
	
	protected final String EDIT_ACTIVITY_WINDOW_HEADER = "Edit Activity";
	
	protected final String ADD_ACTION_WINDOW_HEADER = "Add Action";
	
	protected final String EDIT_ACTION_WINDOW_HEADER = "Edit Action";
	
	protected final String ADD_DOCUMENT_WINDOW_HEADER = "Add Document";
	
	protected final String EDIT_DOCUMENT_WINDOW_HEADER = "Edit Document";
	
	protected final String OTHER_DOCUMENTS_TAB = "//div[contains(@class,'hdwo-details')]//span[contains(text(),'Other')]";
	
	protected final String PARAMETER_QUANTITY_XPATH = "//label[text()='Quantity:']/following::input[contains(@class,'x-form-num-field')]";
	
	protected final String ACTIVITY_WINDOW_XPATH="//div[@class='x-window' and contains(@style,'visibility: visible')]";

	protected final String LABOR_GRID_XPATH="//div[contains(@realid,'WoLaborGrid')]";
	
	protected final String WO_MULTIEDITWINDOW_XPATH = "//div[contains(@class,'workorderMultiEditWindow')]";
	
	protected final String WO_HIERARCHY_XPATH="//div[contains(@class,'x-tree') and contains(@style,'border-left-width')]";

	protected final String QGR_WINDOW_XPATH="//div[contains(@class,'x-window storage-location-window') and contains(@style,'visibility: visible')]";
	
	protected final String BOM_WINDOW_XPATH="//div[contains(@class,'bom-window')]";
	
	protected final String WO_DETAILSWINDOW_XPATH="//div[contains(@class,'x-resizable') and contains(@style,'visibility: visible')][last()]";
	
	public void doubleClick(WebElement element){
	Actions action = new Actions(driver);
	action.doubleClick(element);
	action.perform();
	}
	
	
	public void clickTimeMaterialTab(){
		clickXPath(TIME_MATERIAL_TAB_XPATH);
		Reporter.log("Time material tab was clicked <br>", true);
	}	
		
	public void clickTrackingTab(String windowOrPanel){
                //clickXPath("//div[contains(@class,'"+windowOrPanel+"')]" + TRACKING_TAB_XPATH);
		driver.findElement(By.xpath("//div[contains(@class,'hdwo-details')]//span[contains(text(),'Tracking')]")).click();
		Reporter.log("Tracking tab was clicked <br>", true);
	}	
	
	public void clickTrackingHistoryTab(){
		clickXPath(TRACKING_HISTORY_TAB_XPATH);
		Reporter.log("Tracking history tab was clicked <br>", true);
	}	
	
	public void clickMaintenanceObjectTab(){
		McsElement.getElementByXpath(driver, MAINTANCE_OBJECT_TAB_XPATH).click();
		Reporter.log("Maintenance objects tab was clicked <br>", true);
	}	
	
	public void clickChecklistsObjectTab(){
		clickXPath(CHECKLISTS_OBJECT_TAB_XPATH);
		Reporter.log("Checklists objects tab was clicked <br>", true);
	}

	
	public void clickTimeMaterialConsumptionTab(){
		clickXPath(TIME_MATERIAL_CONSUMPTION_TAB_XPATH);
		Reporter.log("Time material consumption tab was clicked <br>", true);
	}	

	public void clickBillingTab(String windowOrPanel){
		clickXPath("//div[contains(@class,'"+windowOrPanel+"')]" + BILLING_TAB_XPATH);
		Reporter.log("Billing tab was clicked <br>", true);
	}

	public void clickTimeMaterialBillMaterialTab(){
		clickXPath(TIME_MATERIAL_BILLMATERIAL_TAB_XPATH);
		Reporter.log("Bill material tab was clicked <br>", true);
	}	
	
	/**
	 * @param windowOrPanel - WORKORDER_WINDOW_DETAIL or WORKORDER_PANEL_DETAIL - depending on how workorder detail form is opened (double click / click)
	 * 
	 */
	public void clickDescription1Tab(String windowOrPanel){
		clickXPath("(//div[contains(@class,'"+windowOrPanel+"')]//span[contains(text(),'Description')])[1]");
		Reporter.log("Description general tab was clicked <br>", true);
	}	
	
	public void clickDescription2Tab(String windowOrPanel){
		clickXPath("//div[contains(@class, 'x-tab-panel-bwrap')]//span[contains(@class,'x-tab-strip-text') and contains(text(),'Description')]//ancestor::a");
		Reporter.log("Description nested tab was clicked <br>", true);
	}	

	public void clickGeneralTab(String windowOrPanel){
		clickXPath("//div[contains(@class,'"+windowOrPanel+"')]//span[contains(text(),'General')]");
		Reporter.log("General tab was clicked <br>", true);
	}
	

	
	public void clickTimeMaterialLaborTab(){
		clickXPath(TIME_MATERIAL_LABOR_TAB_XPATH);
		Reporter.log("Time material labor tab was clicked <br>", true);
	}	


	public void clickAttachmentsTab(){
		clickXPath(ATTACHMENTS_TAB_XPATH);
		Reporter.log("Attachments tab was clicked <br>", true);
	}	
	
		
	public void clickSaveWorkorder(String windowOrPanel){
		//McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//button[contains(text(),'Save Work Order')]").click();
		
		List<WebElement> saveWOButtons = driver.findElements(By.xpath("//div[contains(@class,'"+windowOrPanel+"')]//button[contains(text(),'Save')]"));
		
		for(WebElement saveButton: saveWOButtons ){
			
			if(saveButton.isDisplayed()&&saveButton.isEnabled()){
				
				saveButton.click();
				waitForExtJSAjaxComplete(25);
				waitForExtJSAjaxComplete(25);
				break;
			}
		}
		
		waitForExtJSAjaxComplete(20);
		Reporter.log("Save in workorder was clicked <br>", true);
	}	
	
	
	public void clickNotesTab(String windowOrPanel){
		clickXPath("//div[contains(@class,'"+windowOrPanel+"')]//span[contains(text(),'Notes')]");
		Reporter.log("notes tab was clicked <br>", true);
	}	

	
/////////////////////////DESCRIPTION TAB METHODS//////////////////////////////////	

	
	public void setDescriptionOrNotes(String windowOrPanel, String description){
                clickXPath("(//div[contains(@class,'"+windowOrPanel+"')]//textarea/../../..)[not(contains(@class,'hide'))]//textarea");
		McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//textarea/../../..)[not(contains(@class,'hide'))]//textarea").clear();
		McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//textarea/../../..)[not(contains(@class,'hide'))]//textarea").sendKeys(description);
		Reporter.log("description was set <br>", true);
	}	
	
	public String getDescriptionOrNotes(String windowOrPanel, String attribute){
		return McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//textarea/../../..)[not(contains(@class,'hide'))]//textarea").getAttribute(attribute);
	}	

	
	
/////////////////////////DESCRIPTION TAB METHODS///////////////////
	
//	public void setDescription1(String description ){
//		McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-tab-panel-bwrap')]//textarea)[1]").click();
//		McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-tab-panel-bwrap')]//textarea)[1]").clear();
//		McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-tab-panel-bwrap')]//textarea)[1]").sendKeys(description);
//		Reporter.log("call description was set <br>", true);
//	}
//
//	public String getDescription1(){
//		return McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-tab-panel-bwrap')]//textarea)[1]").getAttribute("value");
//	}
//
//	public void setNotes(String description ){
//		McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-tab-panel-bwrap')]//textarea)[2]").click();
//		McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-tab-panel-bwrap')]//textarea)[2]").clear();
//		McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-tab-panel-bwrap')]//textarea)[2]").sendKeys(description);
//		Reporter.log("call description was set <br>", true);
//	}
//
//	public String getNotes(){
//		return McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-tab-panel-bwrap')]//textarea)[2]").getAttribute("value");
//	}
	
	public void clickSaveDescription(String windowOrPanel){
		clickXPath("//div[contains(@class,'"+windowOrPanel+"')]//button[text()='Save']");
	}
	
	
	
	
	
////////////////////////////GENERAL TAB METHODS////////////////////////////
	
	public String getReference(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='reference']").getAttribute("value");
		
	}	
	
	public void setPriority(String windowOrPanel, String priority){
		String id = McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='priority']").getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, priority);
		Reporter.log("priority was set <br>", true);
	}	
	
	public String getPriority(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='priority']").getAttribute("value");
		
	}	

	public void setSeverity(String windowOrPanel, String priority){
		String id = McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='severity']").getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, priority);
		Reporter.log("severity was set <br>", true);
	}	
	
	public String getSeverity(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='severity']").getAttribute("value");
		
	}	

	public void setRemainingTimeUnit(String windowOrPanel, String priority){
		String id = McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='remainingTimeUnit']").getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, priority);
		Reporter.log("remaining time was set <br>", true);
	}	
	
	public String getRemainingTimeUnit(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='remainingTimeUnit']").getAttribute("value");
	}	

	public void setEstimatedDurationUnit(String windowOrPanel, String priority){
		if (driver.getCurrentUrl().contains("122")||driver.getCurrentUrl().contains("14")){
		String id = McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='estDurationUnit']").getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, priority);
		Reporter.log("remaining time was set <br>", true);}
	}	
	
	public String getEstinatedDurationTimeUnit(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='estDurationUnit']").getAttribute("value");
	}	
	
	public void setBudgetUnit(String windowOrPanel, String priority){
		String id = McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='estCostsUnit']").getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, priority);
		Reporter.log("remaining time was set <br>", true);
	}	
	
	public String getBudgetUnit(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='estCostsUnit']").getAttribute("value");
	}	
	
	public void setPlannedStartTime(String windowOrPanel, String priority){
		String id = McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='datePlanned']/..//td[contains(@class,'ux-datetime-time')]//input").getAttribute("id");
		DropDown.setComboValue(driver, id, priority);
	}	
	
	public String getPlannedStartTime(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='datePlanned']/..//td[contains(@class,'ux-datetime-time')]//input").getAttribute("value");
	}	

	public void setPlannedEndTime(String windowOrPanel, String priority){
		String id = McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='dateFinished']/..//td[contains(@class,'ux-datetime-time')]//input").getAttribute("id");
		DropDown.setComboValue(driver, id, priority);
	}	
	
	public String getPlannedEndTime(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='dateFinished']/..//td[contains(@class,'ux-datetime-time')]//input").getAttribute("value");
	}	
	

	public void setDueTime(String windowOrPanel, String priority){
		String id = McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='dateDue']/..//td[contains(@class,'ux-datetime-time')]//input").getAttribute("id");
		DropDown.setComboValue(driver, id, priority);
	}	
	
	public String getDueTime(String windowOrPanel, String attribute){
		 return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='dateDue']/..//td[contains(@class,'ux-datetime-time')]//input").getAttribute(attribute);
	}	


	public void setDueDate(String windowOrPanel, String priority){
		WebElement dueDate= McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='dateDue']/..//td[contains(@class,'ux-datetime-date')]//input");
		javaScriptFocus(dueDate);
		javaScriptClick(dueDate);
		dueDate.clear();
		dueDate.sendKeys(priority);
		//javaScriptClick(dueDate);
		dueDate.sendKeys(Keys.ENTER);
		clickXPath("//div[contains(@class,'"+windowOrPanel+"')]//input[@name='dateDue']/..//td[contains(@class,'ux-datetime-time')]//input");
	}	
	
	public String getDueDate(String windowOrPanel, String attribute){
		 return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='dateDue']/..//td[contains(@class,'ux-datetime-date')]//input").getAttribute(attribute);
	}	


	public void setPlannedEndDate(String windowOrPanel, String value){
		WebElement dueDate= McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='dateFinished']/..//td[contains(@class,'ux-datetime-date')]//input");
		clickXPath("//div[contains(@class,'"+windowOrPanel+"')]//input[@name='dateFinished']/..//td[contains(@class,'ux-datetime-time')]//input");
		dueDate.clear();
		dueDate.sendKeys(value);
		dueDate.sendKeys(Keys.ENTER);
		Reporter.log("Planned End date entered", true);
	}

	public String getPlannedEndDate(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='dateFinished']/..//td[contains(@class,'ux-datetime-date')]//input").getAttribute("value");
	}	


	public void setPlannedStartDate(String windowOrPanel, String value){
		WebElement dueDate= McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='datePlanned']/..//td[contains(@class,'ux-datetime-date')]//input");
		clickXPath("//div[contains(@class,'"+windowOrPanel+"')]//input[@name='datePlanned']/..//td[contains(@class,'ux-datetime-time')]//input");
		waitForExtJSAjaxComplete(1);
		dueDate.clear();
		dueDate.sendKeys(value);
		dueDate.sendKeys(Keys.ENTER);
		Reporter.log("Planned Start date entered", true);
	}

	public String getPlannedStartDate(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='datePlanned']/..//td[contains(@class,'ux-datetime-date')]//input").getAttribute("value");
	}	

	
	public void setBudget(String windowOrPanel, String priority){
                clickXPath("//div[contains(@class,'"+windowOrPanel+"')]//input[@name='estCosts']");
		McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='estCosts']").clear();
		McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='estCosts']").sendKeys(priority);
	}	
	
	public String getBudget(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='estCosts']").getAttribute("value");
	}	


	public void setEstimatedDuration(String windowOrPanel, String priority){
               clickXPath("//div[contains(@class,'"+windowOrPanel+"')]//input[@name='estWork']");
               McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='estWork']").clear();
	       McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='estWork']").sendKeys(priority);
	}
	
	public String getEstimatedDuration(String windowOrPanel){
               return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='estWork']").getAttribute("value");
	}

	public void setRemainingTime(String windowOrPanel, String priority){
                clickXPath("//div[contains(@class,'"+windowOrPanel+"')]//input[@name='remainingTime']");
		McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='remainingTime']").clear();
		McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='remainingTime']").sendKeys(priority);
	}	
	
	public String getRemainingTime(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+windowOrPanel+"')]//input[@name='remainingTime']").getAttribute("value");
	}	
	
	public void setRequestedby(String windowOrPanel, String reference) {
		clickLookup(windowOrPanel, "requestedBy");
		setValueGridLookupWithFilters("@class", "x-window-noborder x-resizable-pinned", reference, "Last Name");
		waitForExtJSAjaxComplete(25);
		Reporter.log("requested by "+ reference+" was set", true);
	}
	
	public String getRequestedby(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//input[@name='requestedBy']/..//input)[last()]").getAttribute("value");
	}	
	

	public void setNature(String windowOrPanel, String reference) {		
		clickLookup(windowOrPanel, "nature");
		waitForExtJSAjaxComplete(25);
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//span[contains(text(),'Tree')]")).click();
		waitForExtJSAjaxComplete(25);
		setValueTreeLookup(new String[]{reference});
		
		waitForExtJSAjaxComplete(25);
		Reporter.log("nature "+ reference+" was set", true);
	}
	
	public String getNature(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//input[@name='nature']/..//input)[last()]").getAttribute("value");
	}	


	public void setLocation(String windowOrPanel, String reference) {
		clickLookupNewUI("@class", WORKORDER_WINDOW_DETAIL, "location","Select a Location");
		setValueGridLookupWithFiltersNewUI("@class", "x6-window-default-closable", reference, "Reference");
		waitForExtJSAjaxComplete(25);
		Reporter.log("location "+ reference+" was set", true);
	}
	
	public String getLocation(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//input[@name='location']/..//input)[last()]").getAttribute("value");
	}	
	
	public void setCustomer(String windowOrPanel, String reference) {
		clickLookup(windowOrPanel, "customer");
		setValueGridLookupWithFilters("@class", "x-window-noborder x-resizable-pinned", reference, "Customer Name");
		waitForExtJSAjaxComplete(25);
		Reporter.log("customer "+ reference+" was set", true);
	}
	
	public String getCustomer(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//input[@name='customer']/..//input)[last()]").getAttribute("value");
	}	
	

	public void setFinKey3(String windowOrPanel, String reference) {
		clickLookup(windowOrPanel, "finKey3");
		setValueGridLookupWithFilters("@class", "x-window-noborder x-resizable-pinned", reference, "Value");
		waitForExtJSAjaxComplete(25);
		Reporter.log("fin key 3 "+ reference+" was set", true);
	}
	
	public String getFinKey3(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//input[@name='finKey3']/..//input)[last()]").getAttribute("value");
	}	


	
	public void setFinKey4(String windowOrPanel, String reference) {
		clickLookup(windowOrPanel, "finKey4");
		setValueGridLookupWithFilters("@class", "x-window-noborder x-resizable-pinned", reference, "Value");
		waitForExtJSAjaxComplete(25);
		Reporter.log("fin key 3 "+ reference+" was set", true);
	}
	
	public String getFinKey4(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//input[@name='finKey4']/..//input)[last()]").getAttribute("value");
	}	

	
	
	

	public void setProjectPart(String windowOrPanel, String reference) {
		clickLookup(windowOrPanel, "relatedProjPart");

		clickXPath("//span[contains(@class,'x-tab-strip-text') and text()='Select a Project Part']");
		setValueGridLookupWithFilters("@class", "x-window-noborder x-resizable-pinned", reference, "Reference");
		waitForExtJSAjaxComplete(25);
		Reporter.log("project part "+ reference+" was set", true);
	}
	
	public String getProjectPart(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//input[@name='relatedProjPart']/..//input)[last()]").getAttribute("value");
	}	

	public void setProject(String windowOrPanel, String reference) {
		clickLookup(windowOrPanel, "relatedProject");
		waitForDialogLoadingDisappear("//div[contains(@class, 'x-window x-window-noborder x-resizable-pinned')]");

		clickXPath("//div[contains(@class,'x-resizable')]//span[text()='Select a Project']");
		waitForDialogMaskDisappear("x-window-noborder x-resizable-pinned");
		setValueGridLookupWithFilters("@class", "x-window-noborder x-resizable-pinned", reference, "Project");
		waitForExtJSAjaxComplete(25);
		Reporter.log("project "+ reference+" was set", true);
	}
	
	public String getProject(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//input[@name='relatedProject']/..//input)[last()]").getAttribute("value");
	}	

	
	
	/**
     * Helper method to verify work order grid is empty
     * @return true or false
     */

    public boolean isLookUpGridEmpty(String message)
    {

    	List<WebElement> elements=driver.findElements(By.xpath("//span[text()='Select a Project Part']//ancestor::div[contains(@class,'x-resizable') and (contains(@style,'visibility: visible'))]//div[contains(@class,'x-grid3-scroller')]//div"));
    	System.out.println(elements.size());
    	if((elements.size()==1)||(elements.size()==2))
    	{
    	return true;
    	}else{
    		return true;
    	}

    }
    
    /**
     * Helper method to verify project parts are available or not
     * @param project
     */
    
    public boolean isNoPartsMessageDisplayed(String project)
    {
    String xpath="//div[contains(@class,'x-panel x-mcs-lookup-view') and not(contains(@class,'x-hide-display'))]//span[contains(text(),'"+project+" ("+project+") (no parts available)')]";
    System.out.println(xpath);
    return McsElement.isElementDisplayed(driver, xpath);

    }
	/**
	 * Click tab in project part lookup window
	 * @param tabName
	 */
	
	public void clickTabInProjectPartLookupWindow(String tabName)
	{
		String xpath="//span[text()='Select a Project Part']//ancestor::div[contains(@class,'x-resizable') and (contains(@style,'visibility: visible'))]//li//span[text()='"+tabName+"']";
		WebElement element=driver.findElement(By.xpath(xpath));
		element.click();
	}
	
	/**
	 * Verification of tabs in project part lookup
	 * @param tabName
	 * @return
	 */
	public boolean isTabPresentInProjectLookup(String tabName)
	{
		String xpath="//span[text()='Select a Project Part']//ancestor::div[contains(@class,'x-resizable') and (contains(@style,'visibility: visible'))]//li//span[text()='"+tabName+"']";
		return McsElement.isElementPresent(driver, xpath);
		
	}
	
	/**
	 * get all available values for project lookup
	 * @param windowOrPanel
	 * @return list
	 */
	public List<String> getAllAvailableProjects(String windowOrPanel)
	{
		
		clickLookup(windowOrPanel, "relatedProject");
		waitForDialogMaskDisappear("x-window-noborder x-resizable-pinned");
		return getValuesFromGridLookup("@id", getXWindowId("Select a Project"), "Project");
		
	}
	
	/**
	 * Enter value into project field
	 * @param project
	 * @param reference
	 */
	public void enterValueIntoProjectField(String project ,String reference) {
		String xpath=WO_DETAILSWINDOW_XPATH+"//input[contains(@name,'"+project+"')]//following-sibling::input";
		WebElement element=driver.findElement(By.xpath(xpath));
		element.click();
		element.clear();
		element.sendKeys(reference);
		
		
		Reporter.log("project "+ reference+" was entered", true);
	}
	/**
	 * get project from menu
	 * @return
	 */
	
	public String getProjectValueFromMenu(){
		
		String element="//div[contains(@class,'x-layer x-combo-list') and contains(@style,'visibility: visible')]//div[contains(@class,'x-combo-list-item')]//preceding-sibling::div";
		boolean status=McsElement.isElementDisplayed(driver,element);
		if(status==false)
		{
			return McsElement.getElementByXpath(driver, "//div[contains(@class,'x-layer x-combo-list') and contains(@style,'visibility: visible')]//div[contains(@class,'x-combo-list-inner')]/div[contains(@class,'x-item-disabled')]").getText();
		
		}
		else{
			 return McsElement.getElementByXpath(driver, element).getText();
		}
	}


	public void setCall(String windowOrPanel, String reference) {
		clickLookup(windowOrPanel, "relatedCall");

		waitForDialogLoadingDisappear("//div[contains(@class, 'x-window x-window-noborder x-resizable-pinned')]");

		clickXPath("//div[contains(@class, 'x-window x-window-noborder x-resizable-pinned')]//input[contains(@class,'x-trigger-noedit')]");
		waitForExtJSAjaxComplete(20);
		clickXPath("//div[contains(@style, 'visible')]//div[contains(text(),'allCalls')]");
		waitForExtJSAjaxComplete(20);



		setValueGridLookupWithFilters("@class", "x-window-noborder x-resizable-pinned", reference, "Reference");
		waitForExtJSAjaxComplete(25);
		Reporter.log("call "+ reference+" was set", true);
	}
	
	public String getCall(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//input[@name='relatedCall']/..//input)[last()]").getAttribute("value");
	}	

	public void setWorkorder(String windowOrPanel, String reference) {
		clickLookup(windowOrPanel, "parentWo");

		waitForDialogLoadingDisappear("//div[contains(@class, 'x-window x-window-noborder x-resizable-pinned')]");

		clickXPath("//div[contains(@class, 'x-window x-window-noborder x-resizable-pinned')]//input[contains(@class,'x-trigger-noedit')]");
		waitForExtJSAjaxComplete(20);
		clickXPath("//div[contains(@style, 'visible')]//div[contains(text(),'1preGeneral')]");
		waitForExtJSAjaxComplete(20);


		setValueGridLookupWithFilters("@class", "x-window-noborder x-resizable-pinned", reference, "Reference");
		waitForExtJSAjaxComplete(25);
		Reporter.log("workorder "+ reference+" was set", true);
	}
	
	public String getWorkorder(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//input[@name='parentWo']/..//input)[last()]").getAttribute("value");
	}	


	public void setAssigned(String windowOrPanel, String reference) {
		clickLookup(windowOrPanel, "assignedTo");
		setValueGridLookupWithFilters("@class", "x-window-noborder x-resizable-pinned", reference, "Last Name");
		waitForExtJSAjaxComplete(25);
		Reporter.log("assigned "+ reference+" was set", true);
	}
	
	public String getAssigned(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//input[@name='assignedTo']/..//input)[last()]").getAttribute("value");
	}	


	public void setTaskforce(String windowOrPanel, String reference) {
		clickLookup(windowOrPanel, "taskforce");
		setValueGridLookupWithFilters("@class", "x-window-noborder x-resizable-pinned", reference, "Reference");
		waitForExtJSAjaxComplete(25);
		Reporter.log("taskforce "+ reference+" was set", true);
	}
	
	public String getTaskforce(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//input[@name='taskforce']/..//input)[last()]").getAttribute("value");
	}	

	public void setPlanner(String windowOrPanel, String reference) {
		clickLookup(windowOrPanel, "planner");
		setValueGridLookupWithFilters("@class", "x-window-noborder x-resizable-pinned", reference, "Last Name");
		waitForExtJSAjaxComplete(25);
		Reporter.log("planner "+ reference+" was set", true);
	}
	
	public String getPlanner(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//input[@name='planner']/..//input)[last()]").getAttribute("value");
	}	


	public void setSupplier(String windowOrPanel, String reference) {
		clickLookup(windowOrPanel, "supplier");
		waitForExtJSAjaxComplete(25);
		setValueGridLookupWithFilters("@class", "x-window-noborder x-resizable-pinned", reference, "Name");
		waitForExtJSAjaxComplete(25);
		Reporter.log("supplier "+ reference+" was set", true);
	}
	
	public String getSupplier(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//input[@name='supplier']/..//input)[last()]").getAttribute("value");
	}	


	public void setDefaultActivityType(String windowOrPanel, String reference) {
		clickLookup(windowOrPanel, "activityType");
		setValueGridLookupWithFilters("@class", "x-window-noborder x-resizable-pinned", reference, "Reference");
		waitForExtJSAjaxComplete(25);
		Reporter.log("activity "+ reference+" was set", true);
	}
	
	public String getDefaultActivityType(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//input[@name='activityType']/..//input)[last()]").getAttribute("value");
	}	


	public void setCostCenter(String windowOrPanel, String reference) {
		clickLookup(windowOrPanel, "costCenter");
		setValueGridLookupWithFilters("@class", "x-window-noborder x-resizable-pinned", reference, "Reference");
		waitForExtJSAjaxComplete(25);
		Reporter.log("costcenter "+ reference+" was set", true);
	}
	
	public String getCostCenter(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//input[@name='costCenter']/..//input)[last()]").getAttribute("value");
	}	


	public void setGlAccount(String windowOrPanel, String reference) {
		clickLookup(windowOrPanel, "glAccount");
		
		clickXPath("//div[contains(@class,'x-resizable')]//span[text()='List']");

		setValueGridLookupWithFilters("@class", "x-window-noborder x-resizable-pinned", reference, "Reference");
		waitForExtJSAjaxComplete(25);
		Reporter.log("gl account "+ reference+" was set", true);
	}

	public String getGlAccount(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//input[@name='glAccount']/..//input)[last()]").getAttribute("value");
	}



////////////////////////////LABOR TAB METHODS////////////////////////////


	public void clickAddLabor(){
		clickXPath("//div[@realid='WoLaborGrid']//button[text()='Add']");
		Reporter.log("add labor was clicked <br>", true);
	}

	public void clickEditLabor(){
		clickXPath("//div[@realid='WoLaborGrid']//button[text()='Edit']");
		Reporter.log("edit labor was clicked <br>", true);
	}
	
	public void clickDeleteLabor(String parameter){
		//clickXPath("//div[@realid='WoLaborGrid']//button[text()='Delete']");
		WebElement ele = driver.findElement(By.xpath("//div[@realid='WoLaborGrid']//button[text()='Delete']"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			ele.click();
		} catch(Exception e){
			e.printStackTrace();
		}
		waitForExtJSAjaxComplete(20);
		Reporter.log("delete labor was clicked <br>", true);

		clickOnDialogButton(parameter);
		waitForExtJSAjaxComplete(20);
	}

	public void setLaborActivityDate(String reference) {
		Timer timer = new Timer().start();

		WebElement date = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@class", ACTIVITY_WINDOW_CLASS, "input", "@id", "date", true, true);
		date.click();
                date.clear();
		date.sendKeys(reference);
		Reporter.log("labor date was set" +reference+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	
	public void setLaborActivityStartTime(String reference) {
		Timer timer = new Timer().start();
		WebElement code = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@class", ACTIVITY_WINDOW_CLASS, "input", "@name",
						"timeStart", true, true);
		code.click();
                code.clear();
		code.sendKeys(reference);
		Reporter.log("start time was set" + reference +" (" + timer.stop()
				+ "ms)", true);
	}

	public void setLaborActivityStopTime(String reference) {
		Timer timer = new Timer().start();
		WebElement code = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@class", ACTIVITY_WINDOW_CLASS, "input", "@name",
						"timeStop", true, true);
		code.clear();
		code.sendKeys(reference);
		Reporter.log("stop time was set"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	public void setLaborActivityNotes(String reference) {
		Timer timer = new Timer().start();
		WebElement code = null;
		if (driver.getCurrentUrl().contains("122")||driver.getCurrentUrl().contains("14"))
		{code = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@class", ACTIVITY_WINDOW_CLASS, "textarea", "@name",
						"notesText", true, true);}
		else{code = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@class", ACTIVITY_WINDOW_CLASS, "textarea", "@name",
						"activityNotes", true, true);}
		code.clear();
		code.sendKeys(reference);
		Reporter.log("activity note was set"+ reference+" (" + timer.stop()
				+ "ms)", true);
	}
	
	public void clickLaborActivityGeneralTab(){
		McsElement.getElementByXpath(driver,  ACTIVITY_GENERAL_TAB).click();
		Reporter.log("Activity general tab was clicked <br>", true);
	}	
	

	public void clickLaborActivityFinancialTab(){
		McsElement.getElementByXpath(driver, ACTIVITY_FINANCIAL_TAB).click();
		Reporter.log("Activity financial tab was clicked <br>", true);
	}	

	public void clickLaborActivityNotesTab(){
		McsElement.getElementByXpath(driver, ACTIVITY_NOTES_TAB).click();
		Reporter.log("Activity notes tab was clicked <br>", true);
	}	

	
	public void setLaborActivityRegime(String reference) {
		String id = McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ACTIVITY_WINDOW_CLASS, "input",
				"@name", "regimeId", true, true).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, reference);
		Reporter.log("activity regime was selected", true);
		
	}
	
	public void setLaborBilling(String reference) {
		String id = McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ACTIVITY_WINDOW_CLASS, "input",
				"@name", "billing", true, true).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, reference);
		Reporter.log("activity regime was selected", true);
		
	}
	
	public void setLaborQuantity(String reference) {
		/*McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ACTIVITY_WINDOW_CLASS, "input",
				"@name", "quantity", true, true).clear();
		McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ACTIVITY_WINDOW_CLASS, "input",
				"@name", "quantity", true, true).sendKeys(reference);*/
		McsElement.getElementByXpath(driver, PARAMETER_QUANTITY_XPATH).click();
		McsElement.getElementByXpath(driver, PARAMETER_QUANTITY_XPATH).clear();
		McsElement.getElementByXpath(driver, PARAMETER_QUANTITY_XPATH).sendKeys(reference);
		Reporter.log("quantity was set", true);
		
	}
	
	public void setLaborActivityStatus(String reference) {
		//waitForDialogMaskDisappear("//span[contains(@class,'x-window-header-text') and contains(text(),'Activity')]//ancestor::div[contains(@class,'x-window')]");
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
                clickXPath("//div[contains(@class,'" + ACTIVITY_WINDOW_CLASS + "')]//input[@name='statusCode']");
                clickXPath("//div[contains(@class, 'x-combo-list-item') and contains(text(),'" + reference + "')]");
		Reporter.log("activity status "+ reference+" was set", true);
	}

	public void clickOverrideProductService(){
	        clickXPath("//div[contains(@class,'x-window')]//input[@name='productOverride']");
	        Reporter.log("override click was clicked", true);
	}
	
	public void clickOverrideQuantity(){
		clickXPath("//div[contains(@class,'x-window')]//input[@name='quantityOverride']");
		Reporter.log("override click was clicked", true);
	}

	public void setLaborActivityType(String reference) {
		clickLookup(ACTIVITY_WINDOW_CLASS, "type");
		setValueGridLookupWithFilters("@class", "x-window", reference, "Reference");
		waitForExtJSAjaxComplete(25);
		Reporter.log("activity type "+ reference+" was set", true);
	}

	public void setLaborActivityWorkForce(String lastName) {
		clickLookup(ACTIVITY_WINDOW_CLASS, "workforce");
		setValueGridLookupWithFilters("@class", "x-window", lastName, "Last Name");
		waitForExtJSAjaxComplete(25);
		Reporter.log("activity workforce "+ lastName+" was set", true);
		
	}

	public void setLaborActivityRelatedType(String reference) {
		String id = McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ACTIVITY_WINDOW_CLASS, "input",
				"@name", "relatedType", true, true).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, reference);
		waitForExtJSAjaxComplete(25);
		Reporter.log("related type was selected"+reference, true);
	}

	public void setLaborActivityCall(String reference) {

		clickLookup(ACTIVITY_WINDOW_CLASS, "relCallId");
		waitForExtJSAjaxComplete(20);

		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-resizable-pinned", "input", "@class",
				"x-trigger-noedit", true, true).click();
		
		waitForExtJSAjaxComplete(20);
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@style", "visible", "div", "text()", "allCalls", true, true)
				.click();
		waitForExtJSAjaxComplete(20);
		setValueGridLookupWithFilters("@class", "x-resizable-pinned", reference, "Reference");
	}

	public void setLaborActivityWorkorder(String reference) {

		clickLookup(ACTIVITY_WINDOW_CLASS, "relWorkorder");
		waitForExtJSAjaxComplete(20);

		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-resizable-pinned", "input", "@class",
				"x-trigger-noedit", true, true).click();
		
		waitForExtJSAjaxComplete(20);
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@style", "visible", "div", "text()", "1preGeneral", true, true)
				.click();
		waitForExtJSAjaxComplete(20);
		setValueGridLookupWithFilters("@class", "x-resizable-pinned", reference, "Reference");
		
		Reporter.log("activity workorder "+ reference+" was set", true);
	}
	
	public void setLaborDescription(String reference) {
		WebElement code = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@class", ACTIVITY_WINDOW_CLASS, "textarea", "@name",
						"description", true, true);
		code.clear();
		code.sendKeys(reference);
		Reporter.log("description was set "+ reference, true);
	}

	public void saveAndCloseActivity() {
		 McsElement
			.getElementByPartAttributeValueAndParentElement(driver, "div",
					"@class", ACTIVITY_WINDOW_CLASS, "button", "text()",
					"Save", "text()", "Close", true, true).click();
		 Reporter.log("save and close was clicked", true);
	}

	public void cancelActivity(String windowTitle) {
		 /*McsElement
			.getElementByPartAttributeValueAndParentElement(driver, "div",
					"@class", ACTIVITY_WINDOW_CLASS, "button", "text()",
					"Cancel", true, true).click();*/
		WebElement ele = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//button[contains(text(), 'Cancel')]"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
		 Reporter.log("cancel was clicked", true);
	}
	
	
	public void setLaborProduct(String product){
		
	clickLookup("x-window", "productId");
	
	waitForExtJSAjaxComplete(25);
	
	McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-noborder')]//span[text()='Products And Services']").click();
	
	setValueGridLookupWithFilters("@class", "x-window-noborder", product, "Reference");
	}

	
	////////////////////////////////CONSUMPTION TAB METHODS///////////////////////////
	
	
	
	public void clickEditConsumption(){
		//McsElement.getElementByXpath(driver, "//div[contains(@class,'hdwo-details')]//button[text()='Edit']").click();
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class,'hdwo-details')]//button[text()='Edit']"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
		Reporter.log("edit consumption was clicked <br>", true);
	}

	public void clickDeleteConsumption(){
		//McsElement.getElementByXpath(driver, "//div[contains(@class,'hdwo-details')]//button[text()='Delete']").click();
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class,'hdwo-details')]//button[text()='Delete']"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
		Reporter.log("delete consumption was clicked <br>", true);
	}

	
	
	public void clickAddConsumption(){
		//McsElement.getElementByXpath(driver, "//div[contains(@class,'hdwo-details')]//button[text()='Add']").click();
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class,'hdwo-details')]//button[text()='Add']"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
		Reporter.log("add consumption was clicked <br>", true);
	}

	public void clickShowProductDoc(){
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class,'hdwo-details')]//button[contains(@class,'x-btn-text') and text()='Show Product Doc.']"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		ele.click();
		Reporter.log("show product doc was clicked <br>", true);
	}
	

	public void closeXWindow(){
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class, 'x-window x-resizable-pinned') and contains(@style, 'visibility: visible;')]//div[contains(@class,'x-tool-close')][last()]"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		Reporter.log("close x-window <br>", true);
	}
	
	public void closeXWindow(String attribute, String attrValue){
		WebElement ele = driver.findElement(By.xpath("//div[contains("+attribute+", '"+attrValue+"') and contains(@style, 'visibility: visible;')]//div[contains(@class,'x-tool-close')][last()]"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		Reporter.log("close x-window <br>", true);
	}

	
	public void setConsumptionSelectProduct(String comboValue){
		String id = driver.findElement(By.xpath("//div//label[contains(text(),'Select Products')]/../../..//input")).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, comboValue);
	}
	
	
	public void setConsumptionProduct(String product){
		
	clickLookup("x-resizable",driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//label[text()='Product:']/..//input")).getAttribute("name"));
	
	waitForExtJSAjaxComplete(25);
	
	McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-noborder')]//span[text()='Products And Services']").click();
	
	setValueGridLookupWithFilters("@class", "x-window-noborder", product, "Reference");
	}
	
	
	public void setConsumptionQuantity(String product){
	McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//label[text()='Quantity:']/..//input").click();
	McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//label[text()='Quantity:']/..//input").clear();
	McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//label[text()='Quantity:']/..//input").sendKeys(product);
	McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//label[text()='Quantity:']/..//input").click();
	}

	public void setConsumptionDate(String date){
	McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//label[text()='Date of Consumption:']/..//input").click();	
	McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//label[text()='Date of Consumption:']/..//input").clear();
	McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//label[text()='Date of Consumption:']/..//input").sendKeys(date);
	McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//label[text()='Date of Consumption:']/..//input").click();
	}

	public String getTotalExpence(){
	return McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//label[text()='Total Expense Amt:']/..//input").getAttribute("value");
	}
	
	public String getTotalRevenue(){
	return McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//label[text()='Total Revenue Amt:']/..//input").getAttribute("value");
	}
	
	public void setBilling(String comboValue){
		String id = driver.findElement(By.xpath("//div//label[text()='Billing:']/..//input")).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, comboValue);
	}
	
	public void saveCloseConsumption(String windowTitle){
	driver.findElement(By.xpath("//div[contains(@id,'"+getXWindowId(windowTitle)+"')]//button[text()='Save and Close']")).click();
	Reporter.log("save close consumption was clicked <br>", true);
	}
	
	public void saveConsumption(String windowTitle){
	driver.findElement(By.xpath("//div[contains(@id,'"+getXWindowId(windowTitle)+"')]//button[text()='Save']")).click();
	Reporter.log("save consumption was clicked <br>", true);
	}
	
	public void openConsumptionDialog() {
		WebElement cell = McsElement.getElementByXpath(driver, "//div[@realid='WoConsumptionGrid']//div[@class='x-grid3-cell-inner x-grid3-col-0']//..");
		cell.click();
		Actions action = new Actions(driver);
		action.doubleClick(cell);
		action.perform();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Double clicked on Consumption ID <br>", true);
	}
	
	public boolean verifyConsumptionHeader(String headerBegining,String itemID) {
		String headerText = McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-window x-resizable-pinned')]//span[contains(@class, 'x-window-header-text')]").getText();
		String regEx = headerBegining + itemID;
		return headerText.matches(regEx);
	}
	
	public String getXWindowIdWithoutVisibilityCheck(String xwindowTitle) throws NoSuchElementException {

		String elementXpath = "(//div[contains(@class, 'x-window')]//span[contains(text(),'"
				+ xwindowTitle + "')])[last()]/../../../../..";

		WebElement webElement = driver.findElement(By.xpath(elementXpath));
		
		return webElement.getAttribute("id");
	}
	
	public void clickCancelOnConsumptionDialog(String windowTitle) {
		WebElement cancelButton = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowIdWithoutVisibilityCheck(windowTitle)+"')]//table[contains(@class, 'x-toolbar-ct')]//button[text()='Cancel']"));
		
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", cancelButton);
		} catch(Exception e){
			e.printStackTrace();
		}
		
		cancelButton.click();
		Reporter.log("Cancel Button is clicked", true);
	}
	
	public void verifySelectedCallsInTab(String callID, String callReference) {
		WebElement iFrame = McsElement.getElementByXpath(driver, "//div[contains(@class,'helpdesk')]//div[contains(@class,'x-tab-panel-bwrap')]//iframe");
		driver.switchTo().frame(iFrame);
		McsElement.getElementByXpath(driver, "//tr//td//b[contains(text(), '" + callID + "')]//..//..//td[contains(text(), '" + callReference + "')]");
		driver.switchTo().defaultContent();
		Reporter.log("Item with id and reference: " + callID + " : " + callReference + "found. <br>", true);
	}
	
	
	//////////////////////////////////BILL MATERIALS TAB METHODS////////////////////////////////////////////
	

	public void clickDeleteBM(){
		//McsElement.getElementByXpath(driver, "//div[contains(@class,'bom-window')]//button[text()='Delete']").click();
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class,'bom-window')]//button[text()='Delete']"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			ele.click();
		}catch(Exception e){
			e.printStackTrace();
		}
		waitForExtJSAjaxComplete(20);
		Reporter.log("delete bill materials was clicked <br>", true);
	}

	
	public void clickAddBM(){
		McsElement.getElementByXpath(driver, "//div[contains(@class,'bom-window')]//button[text()='Add']").click();
		Reporter.log("add bill materials was clicked <br>", true);
	}

	public void clickShowRelatedDoc(){
	//	McsElement.getElementByXpath(driver, "//div[contains(@class,'bom-window')]//button[contains(@class,'icon-ov-report')]").click();
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class,'bom-window')]//button[contains(@class,'icon-ov-report')]"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		}catch(Exception e){
			e.printStackTrace();
		}
		ele.click();
		Reporter.log("show related doc was clicked <br>", true);
	}

	public void selectBMProduct(String product){
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-noborder')]//span[text()='Products And Services']").click();
		
		setValueGridLookupWithFilters("@class", "x-window-noborder", product, "Reference");
		}
	
	
	public void setBMProductQuantity(String number, String product){
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'bom-window')]//span[contains(text(),'"+product+"')]/../../..//td[contains(@class,'td-9')]//div//span").click();
		
		waitForExtJSAjaxComplete(25);
		
		McsElement.getElementByXpath(driver, "(//div[contains(@class,'bom-window')]//img[contains(@class,'x-form-spinner')]/..//input)[last()]").click();
		McsElement.getElementByXpath(driver, "(//div[contains(@class,'bom-window')]//img[contains(@class,'x-form-spinner')]/..//input)[last()]").clear();
		McsElement.getElementByXpath(driver, "(//div[contains(@class,'bom-window')]//img[contains(@class,'x-form-spinner')]/..//input)[last()]").sendKeys(number);
		McsElement.getElementByXpath(driver, "(//div[contains(@class,'bom-window')]//img[contains(@class,'x-form-spinner')]/..//input)[last()]").click();
		McsElement.getElementByXpath(driver, "//div[contains(@class,'bom-window')]//span[contains(text(),'"+product+"')]").click();

		}

	public void clickSaveBM(){
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class,'bom-window')]//button[contains(text(),'Save')]"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			ele.click();
		} catch(Exception e){
			e.printStackTrace();
		}
		Reporter.log("save bill materials was clicked <br>", true);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
	}
	
	/**
	 *  Helper method to get column number in Grid 
	 */
	public int getGridColumnNumberWithoutQuickFilters(String attribute, String attributeValue, String columnName){
		
		WebElement ele = driver.findElement(By.xpath("(//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')][last()])"));
		
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String columnClass = ele.getAttribute("class"); 
		String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);
			
		return Integer.parseInt(columnNumber);
	}


	public void setBMWarehouse(String product, String warehouse){
		
		int colNo = getGridColumnNumberWithoutQuickFilters("@class", "bom-window", "Warehouse");
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'bom-window')]//span[contains(text(),'"+product+"')]/../../..//td[contains(@class,'td-"+colNo+"')]//div//span").click();
		
		waitForExtJSAjaxComplete(25);
		
		String name = McsElement.getElementByXpath(driver, "(//div[contains(@class,'bom-window')]//input[contains(@class,'x-form-invalid')])[last()]").getAttribute("name");
		
		clickLookup("bom-window", name);
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-noborder')]//span[text()='All Warehouses']").click();
		
		setValueGridLookupWithFilters("@class", "x-window-noborder", warehouse, "Reference");
		

	}
	
	public void clickReset(){
		McsElement.getElementByXpath(driver, "//div[contains(@class,'bom-window')]//button[contains(text(),'Reset')]").click();
		Reporter.log("reset bill materials was clicked <br>", true);
	}
	
	
	public void selectBMinGrid(String product){
		McsElement.getElementByXpath(driver, "//div[contains(@class,'bom-window')]//span[contains(text(),'"+product+"')]").click();
		waitForExtJSAjaxComplete(25);
	}
	
	public void setBMProductStatus(String product, String status){
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'bom-window')]//span[contains(text(),'"+product+"')]").click();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		int colNo = getGridColumnNumberWithoutQuickFilters("@class", "bom-window", "Status");
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'bom-window')]//span[contains(text(),'"+product+"')]/../../..//td[contains(@class,'td-"+colNo+"')]//div//span").click();
		
		waitForExtJSAjaxComplete(25);
		
		String id=null;
		try {id = McsElement.getElementByXpath(driver, "(//div[contains(@class,'bom-window')]//img[contains(@class,'x-form-arrow-trigger')]/..//input)[last()]").getAttribute("id");}
		catch(Exception e) {
			McsElement.getElementByXpath(driver, "//div[contains(@class,'bom-window')]//span[contains(text(),'"+product+"')]/../../..//td[contains(@class,'td-"+colNo+"')]//div//span").click();
			waitForExtJSAjaxComplete(25);
			id = McsElement.getElementByXpath(driver, "(//div[contains(@class,'bom-window')]//img[contains(@class,'x-form-arrow-trigger')]/..//input)[last()]").getAttribute("id");
		}
		
		DropDown.setExtJsComboValue(driver, id, status);

		}
	
	
	
	//////////////////////////////////MAINTENANCE OBJECT TAB METHODS///////////////////////////////////////////
	

	public String getMaintanceObjectDetail(String detailName){
		try{return McsElement.getElementByXpath(driver, "//div[contains(@class,'x-resizable')]//div[text()='"+detailName+"']/ancestor::div[contains(@class, 'x-grid3-row')]//td[2]//div").getText();}
		catch(Exception e){return "";}
	}
	
	public void clickAddAsset(){
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class,'x-tab-panel-bwrap')]//button[text()='Add Asset']"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		
	    ele.click();
		Reporter.log("add asset was clicked <br>", true);
	}

	public void clickRemoveAsset(){
		//McsElement.getElementByXpath(driver, "//div[contains(@class,'x-tab-panel-bwrap')]//button[text()='Remove']").click();
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class,'x-tab-panel-bwrap')]//button[text()='Remove']"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		
	    ele.click();
		Reporter.log("remove asset was clicked <br>", true);
	}
	
		
		
		public void setAsset(String reference) {

			McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-resizable-pinned", "input", "@class",
				"x-trigger-noedit", true, true).click();
		
		waitForExtJSAjaxComplete(20);
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@style", "visible", "div", "text()", "general","@class","x-combo", true, true)
				.click();
		waitForExtJSAjaxComplete(20);
		
		String gridId= driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//div[contains(@realid,'mogrid')]")).getAttribute("id");
		
		filterGridWithoutUsingMcsElement("@id", gridId, reference, "Reference");
		
		//String columnClass = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId("Select Assets")+"')]//div[contains(@class, 'x-grid3-hd') and text()='Reference']")).getAttribute("class");
				/* McsElement
					.getElementByPartAttributeValueAndParentElement(driver,
							"div", "@id", "x-resizable-pinned",
							"div","@class", "x-grid3-hd",
							"text()", "Reference", true, true).getAttribute("class");*/
		
			int columnNumber = getGridColumnNumberWithQuickFilters("@class", "x-window-noborder x-resizable-pinned", "Reference");
			System.out.println(columnNumber);
			
			waitForExtJSAjaxComplete(25);
			/*McsElement
					.getElementByPartAttributeValueAndParentElement(driver,
							"div","@class", "x-resizable-pinned", "div",
							"@class", "x-grid3-cell-inner x-grid3-col-"+columnNumber,
							"text()", reference, true, true).click();*/
			WebElement ele = driver.findElement(By.xpath("//div[contains(@class, 'x-resizable-pinned')]//div[contains(@class, 'x-grid3-cell-inner x-grid3-col-"+columnNumber+"') and text()='"+reference+"']"));
			
			try{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			} catch(Exception e){
				e.printStackTrace();
			}
			waitForExtJSAjaxComplete(25);
			ele.click();
			
			waitForExtJSAjaxComplete(25);	
			//McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//button[contains(@style,'add.png')]").click();
			driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//button[contains(@style,'add.png')]")).click();
			waitForExtJSAjaxComplete(25);
			WebElement OkElement = driver.findElement(By.xpath("//div[contains(@class,'x-window-footer x-window-footer-noborder x-panel-btns')]//button[contains(text(),'OK')]"));
			try{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", OkElement);
			} catch(Exception e){
				e.printStackTrace();
			}
			waitForExtJSAjaxComplete(25);
			OkElement.click();
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);
			Reporter.log(reference + " was selected", true);
		
	}

		

	
	public void clickAddMaintanceObject(){
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-tab-panel-bwrap')]//button[text()='Add Maint. Obj.']").click();
		Reporter.log("add maintance object was clicked <br>", true);
	}


	public void clickDetailsMaintanceObject(){
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-tab-panel-bwrap')]//button[text()='Details']").click();
		Reporter.log("details maintance object was clicked <br>", true);
	}

	
	public void closeDetailsMaintanceObject(){
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-mc')]//button[text()='Close']").click();
		Reporter.log("Close details maintance object was clicked <br>", true);
	}
	
	
	public void setMaintenanceObject(String reference) {

		McsElement.getElementByPartAttributeValueAndParentElement(driver,
			"div", "@class", "x-resizable-pinned", "input", "@class",
			"x-trigger-noedit", true, true).click();
	
	waitForExtJSAjaxComplete(20);
	McsElement.getElementByPartAttributeValueAndParentElement(driver,
			"div", "@style", "visible", "div", "text()", "allMnObjects","@class","x-combo", true, true)
			.click();
	waitForExtJSAjaxComplete(20);
	
	filterGrid("@class", "x-resizable-pinned", reference, "Reference");
	
	/*String columnClass = McsElement
	.getElementByPartAttributeValueAndParentElement(driver,
			"div", "@class", "x-resizable-pinned",
			"div","@class", "x-grid3-hd",
			"text()", "Reference", true, true).getAttribute("class");*/
	/*String columnClass = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId("Select Maintenance Objects")+"')]//div[contains(@class, 'x-grid3-hd') and text()='Reference']")).getAttribute("class");
	
	String columnNumber = columnClass.substring(columnClass.length() - 1);*/
	
		int columnNumber = getGridColumnNumberWithQuickFilters("@class", "x-window-noborder x-resizable-pinned", "Reference");
		System.out.println(columnNumber);
		/*McsElement
				.getElementByPartAttributeValueAndParentElement(driver,
						"div","@class", "x-resizable-pinned", "div",
						"@class", "x-grid3-cell-inner x-grid3-col-"+columnNumber,
						"text()", reference, true, true).click();*/
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class, 'x-resizable-pinned')]//div[contains(@class, 'x-grid3-cell-inner x-grid3-col-"+columnNumber+"') and text()='"+reference+"']"));
		
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		ele.click();
		
		waitForExtJSAjaxComplete(25);	
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//button[contains(@style,'add.png')]")).click();
		waitForExtJSAjaxComplete(25);	
		
		WebElement OkElement = driver.findElement(By.xpath("//div[contains(@class,'x-window-footer x-window-footer-noborder x-panel-btns')]//button[contains(text(),'OK')]"));
			try{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", OkElement);
			} catch(Exception e){
				e.printStackTrace();
			}
		waitForExtJSAjaxComplete(25);
		OkElement.click();	
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		Reporter.log(reference + " was selected", true);
	
	}
	
	/**
	 *  Helper method to get column number in Grid 
	 */
	public int getGridColumnNumberWithQuickFilters(String attribute, String attributeValue, String columnName){

		WebElement ele = driver.findElement(By.xpath("(//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'quickfilters')]//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')][last()])"));
		
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String columnClass = ele.getAttribute("class");
		String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);
			
		return Integer.parseInt(columnNumber);
	}
	
	public void setMaintenanceObjectPart(String reference) {

		McsElement.getElementByPartAttributeValueAndParentElement(driver,
			"div", "@class", "x-resizable-pinned", "input", "@class",
			"x-trigger-noedit", true, true).click();
	
	waitForExtJSAjaxComplete(20);
	McsElement.getElementByPartAttributeValueAndParentElement(driver,
			"div", "@style", "visible", "div", "text()", "allMnObPt","@class","x-combo", true, true)
			.click();
	waitForExtJSAjaxComplete(20);
		
		filterGrid("@class", "x-resizable-pinned", reference, "Reference");
	
	/*String columnClass = McsElement
	.getElementByPartAttributeValueAndParentElement(driver,
			"div", "@class", "x-resizable-pinned",
			"div","@class", "x-grid3-hd",
			"text()", "Reference", true, true).getAttribute("class");*/
		int columnNumber = getGridColumnNumberWithQuickFilters("@class", "x-window-noborder x-resizable-pinned", "Reference");
		System.out.println(columnNumber);
		
		/*McsElement
				.getElementByPartAttributeValueAndParentElement(driver,
						"div","@class", "x-resizable-pinned", "div",
						"@class", "x-grid3-cell-inner x-grid3-col-"+columnNumber,
						"text()", reference, true, true).click();*/
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class, 'x-resizable-pinned')]//div[contains(@class, 'x-grid3-cell-inner x-grid3-col-"+columnNumber+"') and text()='"+reference+"']"));
		
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		waitForExtJSAjaxComplete(25);
		ele.click();
		waitForExtJSAjaxComplete(25);
		
		
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//button[contains(@style,'add.png')]")).click();
		waitForExtJSAjaxComplete(25);
		
		
		WebElement OkElement = driver.findElement(By.xpath("//div[contains(@class,'x-window-footer x-window-footer-noborder x-panel-btns')]//button[contains(text(),'OK')]"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", OkElement);
		} catch(Exception e){
			e.printStackTrace();
		}
		waitForExtJSAjaxComplete(25);
		OkElement.click();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		Reporter.log(reference + " was selected", true);
	
	}

	
	public void clickAddMaintanceObjectPart(){
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-tab-panel-bwrap')]//button[text()='Add Maint. Obj Part']").click();
		Reporter.log("add maintance object was clicked <br>", true);
	}


	
/////////////////////TRACKING HISTORY TAB METHODS///////////////////
	
	public void clickAddAction(){
		McsElement.getElementByXpath(driver, "//div[contains(@realid,'HistoryGrid')]//button[text()='Add Action']").click();
		Reporter.log("add action was clicked <br>", true);
	}

	public void clickEditLastAction(){
		McsElement.getElementByXpath(driver, "//div[contains(@realid,'HistoryGrid')]//button[text()='Edit Last Action']").click();
		Reporter.log("edit last action was clicked <br>", true);
	}
	
	public String getActionType(){
		return driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//div[@name='actionResultStatus']")).getText();
		}
			
		public void saveCloseAction(String windowTitle){
		       clickXPath("//div[contains(@id,'"+getXWindowId(windowTitle)+"')]//button[contains(text(),'Action') and contains(text(),'Close')]");
		       Reporter.log("save close action was clicked <br>", true);
		}
		
		public void saveAction(String windowTitle){
		       clickXPath("//div[contains(@id,'"+getXWindowId(windowTitle)+"')]//button[text()='Add Action']");
		       Reporter.log("save close action was clicked <br>", true);
		}

		public void saveActionEdited(String windowTitle){
		       clickXPath("//div[contains(@id,'"+getXWindowId(windowTitle)+"')]//button[text()='Save Action']");
		       Reporter.log("save close action was clicked <br>", true);
		}

		
		public void closeAction(String windowTitle){
		        clickXPath("//div[contains(@id,'"+getXWindowId(windowTitle)+"')]//button[text()='Close']");
		        Reporter.log("close action was clicked <br>", true);
		}

		
		
		public void clickPossibleActions(String Action){
		       clickXPath("//div[contains(@class,'x-resizable')]//em[contains(text(),'"+Action+"')]");
		       Reporter.log("action type was clicked <br>", true);
		}
	
		public void setActionAssigned(String attribute, String attributeValue, String rowTextName, String columnName) {
			clickLookup("x-resizable", "assignee");
			
			waitForExtJSAjaxComplete(25);
			filterGrid(attribute, attributeValue, rowTextName, columnName);	
			
			waitForExtJSAjaxComplete(25);
			
			String columnClass = McsElement
			.getLastElementByPartAttributeValueAndParentElement(driver,
					"div", attribute, attributeValue,
					"div","@class", "x-grid3-hd",
					"text()", columnName, true, true).getAttribute("class");
			
			String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
			
			waitForExtJSAjaxComplete(25);
		
			driver.findElement(By.xpath("//div[contains("+attribute+", '"+attributeValue+"')]//div[contains(@class,'x-grid3-cell-inner x-grid3-col-"+columnNumber+"') and text()='"+rowTextName+"'][last()]")).click();

			waitForExtJSAjaxComplete(25);
				
			WebElement OkElement = driver.findElement(By.xpath("//div[contains(@class,'x-window-footer x-window-footer-noborder x-panel-btns')]//button[contains(text(),'OK')]"));
			try{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", OkElement);
			} catch(Exception e){
				e.printStackTrace();
			}
			waitForExtJSAjaxComplete(25);
			OkElement.click();
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);
			Reporter.log(rowTextName + " was selected", true);

			//setValueGridLookupWithFilters("@class", "x-window-noborder", name, "Last Name");
			
		}
		
		public boolean setActionAssignedWithValidation(String name) {
			clickLookup("x-resizable", "assignee");
			return setValueGridLookupWithFiltersWithValidation("@class", "x-window-noborder", name, "Last Name");
			//setValueGridLookupWithFilters("@class", "x-window-noborder", name, "Last Name");
			
		}
		
		public void setPlanner(String name) {
			clickLookup("x-resizable", "planner");
			setValueGridLookupWithFilters("@class", "x-window-noborder", name, "Last Name");
			waitForExtJSAjaxComplete(25);
		}

		public void setSupplier(String name) {
			clickLookup("x-resizable", "supplier");
			setValueGridLookupWithFilters("@class", "x-window-noborder", name, "Name");
			waitForExtJSAjaxComplete(25);
		}


		public void setTaskForce(String name) {
			clickLookup("x-resizable", "taskforce");
			setValueGridLookupWithFilters("@class", "x-window-noborder", name, "Reference");
			waitForExtJSAjaxComplete(25);
		}

		public void setTime(String note){
			McsElement.getElementByXpath(driver, "//td[contains(@class,'time-time')]//input").click();
			McsElement.getElementByXpath(driver, "//td[contains(@class,'time-time')]//input").clear();
			McsElement.getElementByXpath(driver, "//td[contains(@class,'time-time')]//input").sendKeys(note);
			Reporter.log("time was set <br>", true);
		}
		
		public void setActionNote(String note){
			clickXPath("//div[contains(@class,'x-resizable')]//textarea[@name='actionNotes']");
			McsElement.getElementByXpath(driver, "//div[contains(@class,'x-resizable')]//textarea[@name='actionNotes']").clear();
			McsElement.getElementByXpath(driver, "//div[contains(@class,'x-resizable')]//textarea[@name='actionNotes']").sendKeys(note);
			Reporter.log("action note was set <br>", true);
		}

		//div[contains(@class,'expanded')]//div[contains(@class,'row') and contains(text(),'"+note+"')]/../../../..//div[contains(text(),'"+action+"')]
		/**
		 * Helper method to verify Action note is available in the grid
		 * Actions are not available in the grisd only on the left corner its available
		 * @param note
		 * @return
		 */
		public boolean isActionPresent(String note){
		return McsElement.
				isElementPresent
				(driver,"//div[contains(@class,'expanded')]//div[contains(@class,'rowbody') and text()='"+note+"']");
		}
		
/////////////////////DOCUMENTS TAB METHODS///////////////////
		
		
		
		public void setUrl(String url, String description, String remark, String type){
			clickXPath(ADD_URL_XPATH);
			waitForExtJSAjaxComplete(25);
			McsElement.getElementByXpath(driver, HYPERLINK_XPATH).clear();
			McsElement.getElementByXpath(driver, HYPERLINK_XPATH).sendKeys(url);
			
			McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).clear();
			McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).sendKeys(description);
			
			McsElement.getElementByXpath(driver, HYPERLINK_REMARK).clear();
			McsElement.getElementByXpath(driver, HYPERLINK_REMARK).sendKeys(remark);
			
			int xwindowNumber=0;
			
			try {driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				xwindowNumber = driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size();
			}
			finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);}

			clickXPath("//div[contains(@class,'x-window')]//label[contains(text(),'Type')]/..//button");
			
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
			
			clickXPath(ADD_URL_CREATE_XPATH);
			waitForExtJSAjaxComplete(25);
			Reporter.log("url was set <br>", true);
		}
		

		public String getUrlDescription(){
			return McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).getAttribute("value");
		}
		
		public String getUrlType(){
			return McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window')]//label[contains(text(),'Type')]/..//input").getAttribute("value");
		}

		
		public String getUrlRemark(){
			return McsElement.getElementByXpath(driver, HYPERLINK_REMARK).getAttribute("value");
		}
		
		public void editUrl(String url, String descriptionEd, String remark, String type){
			McsElement.getElementByXpath(driver, HYPERLINK_XPATH).clear();
			McsElement.getElementByXpath(driver, HYPERLINK_XPATH).sendKeys(url);
			
			McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).clear();
			McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).sendKeys(descriptionEd);
			
			McsElement.getElementByXpath(driver, HYPERLINK_REMARK).clear();
			McsElement.getElementByXpath(driver, HYPERLINK_REMARK).sendKeys(remark);
			
			if(getTypeValue(EDIT_DOCUMENT_WINDOW_HEADER).contains("labelen")){
				Reporter.log("Labelen Value is Already Selected", true);
			} else{
			
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
			}
			
			//McsElement.getElementByXpath(driver, ADD_URL_SAVE_XPATH).click();
			driver.findElement(By.xpath("//div[contains(@id,'"+getXWindowId(EDIT_DOCUMENT_WINDOW_HEADER)+"')]//button[text()='Save']")).click();
			waitForExtJSAjaxComplete(25);
			Reporter.log("url was set <br>", true);
		}


		public void deleteUrl(String description){
			checkRowInGriByTextValueAndClick(description);
			waitForExtJSAjaxComplete(25);
			
			WebElement ele = driver.findElement(By.xpath(DELETE_URL_XPATH));
			
			try{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
				ele.click();
			} catch(Exception e){
				e.printStackTrace();
			}
			waitForExtJSAjaxComplete(25);
			Reporter.log("Delete button clicked and Waiting to click on Dialog Button", true);
			//clickOnDialogButton("Yes");
			WebElement ele1 = driver.findElement(By.xpath("//div[contains(@class, 'x-window-dlg')]//button[contains(@class, 'x-btn-text') and text()='Yes']"));
			try{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele1);
				ele1.click();
			} catch(Exception e){
				e.printStackTrace();
			}
			waitForExtJSAjaxComplete(25);
			Reporter.log("url or file was deleted <br>", true);
		}
		
		public void checkRowInGriByTextValueAndClick(String textValue)  {
			String xpath = "//*[@class='x-grid3']"+ xpathGeneratorForTextElement(textValue);
			System.out.println(xpath);
			WebElement webElement = driver.findElement(By.xpath(xpath));
			
			try{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
				webElement.click();
			} catch(Exception e){
				e.printStackTrace();
			}
			Reporter.log("Check element "+textValue+" present in grid and click", true);
		}
		
		public static String xpathGeneratorForTextElement(String text){
		  
		  String subStrings[] = text.split(" ");
		  
		  String xpath = "//div[starts-with(text(),'"+subStrings[0]+"')";
		  
		  for(int i=1; i<subStrings.length; i++){

		   xpath+="and contains(text(),'"+subStrings[i]+"')";
		  }
		  
		  return xpath+"]";
		  
		 }

		/**
		 * Helper method to get Type Field value in Add Document Window 
		 */
		public String getTypeValue(String windowTitle){
			String typeValue = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//label[contains(@class, 'label-mandatory') and text()='Type']//..//input")).getAttribute("value");
			return typeValue;
		}
		
		public void editFile(String file, String descriptionEd, String remark, String type){
			
			//TODO File is already uploaded.
			/*FileUploader.uploadFileName(driver, "@class", "x-window", file);
			waitForExtJSAjaxComplete(25);*/
			
			McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).clear();
			McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).sendKeys(descriptionEd);
			
			McsElement.getElementByXpath(driver, HYPERLINK_REMARK).clear();
			McsElement.getElementByXpath(driver, HYPERLINK_REMARK).sendKeys(remark);
			
			if(getTypeValue(EDIT_DOCUMENT_WINDOW_HEADER).contains("labelen")){
				Reporter.log("Labelen Value is Already Selected", true);
			} else{
			
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
			}
			
			//McsElement.getElementByXpath(driver, ADD_URL_SAVE_XPATH).click();
			driver.findElement(By.xpath("//div[contains(@id,'"+getXWindowId(EDIT_DOCUMENT_WINDOW_HEADER)+"')]//button[text()='Save']")).click();
			waitForExtJSAjaxComplete(25);
			Reporter.log("file was uploaded <br>", true);
		}
		
		
		public void setFile(String file, String description, String remark, String type){
			McsElement.getElementByXpath(driver, ADD_FILE_XPATH).click();
			waitForExtJSAjaxComplete(25);
			FileUploader.uploadFileName(driver, "@class", "x-window", file);
			waitForExtJSAjaxComplete(25);
			
			
			McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).clear();
			McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).sendKeys(description);
			
			McsElement.getElementByXpath(driver, HYPERLINK_REMARK).clear();
			McsElement.getElementByXpath(driver, HYPERLINK_REMARK).sendKeys(remark);
			
			if(getTypeValue(ADD_DOCUMENT_WINDOW_HEADER).contains("labelen")){
				Reporter.log("Labelen Value is Already Selected", true);
			} else{
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
			}
			
			McsElement.getElementByXpath(driver, ADD_URL_CREATE_XPATH).click();
			waitForExtJSAjaxComplete(25);
			Reporter.log("file was uploaded <br>", true);
		}
		
		
		///////////////////////////CHECKLISTS/////////////////////////////////////////
		public void selectChecklist(String checklist){
			
			McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-noborder')]//span[text()='Select a checklist template']").click();
			
			setValueGridLookupWithFilters("@class", "x-window-noborder", checklist, "Reference");
		}
		
		public void clickDeleteChecklist(){
			McsElement.getElementByXpath(driver, "//div[contains(@realid,'SurveyGrid')]//button[text()='Delete']").click();
			Reporter.log("delete clickAddChecklist was clicked <br>", true);
		}

		
		public void clickAddChecklist(){
			McsElement.getElementByXpath(driver, "//div[contains(@realid,'SurveyGrid')]//button[text()='Add']").click();
			Reporter.log("add clickAddChecklist was clicked <br>", true);
		}
		
		public void clickViewChecklist(){
			WebElement ele = driver.findElement(By.xpath("//div[contains(@realid,'SurveyGrid')]//button[text()='View / Edit']"));
			try{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
				ele.click();
			} catch(Exception e){
				e.printStackTrace();
			}
			Reporter.log("View / Edit clickAddChecklist was clicked <br>", true);
		}
		
		public void selectChecklistGrid(String chelistReference){
			
			McsElement.getElementByXpath(driver, "//div[contains(@realid,'SurveyGrid')]//div[text()='" + chelistReference + "']").click();
			
			Reporter.log("Select checklist from grid" + chelistReference + "<br>", true);
		}
		
		public void closeChecklistTab(String chelistReference){
			driver.findElement(By.xpath("//div[contains(@class,'x-tab-strip-wrap')]//span[text()='Checklists - " + chelistReference + "']//..//..//..//..//a[@class='x-tab-strip-close']")).click();
			Reporter.log("Checkilst tab was clicked. <br>", true);
		}
		
		public boolean isChecklistInGridPresent(String chelistReference){
			return McsElement.
					isElementPresent(driver, "//div[contains(@realid,'SurveyGrid')]//div[text()='" + chelistReference + "']");
			
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
		 * Helper method to verify if Element is present in the grid and delete it
		 */
		/*public void deleteProductsFromConsumptionGrid(String windowID, String[] prodArray, String columnName){
			boolean status = false;
			
			int colNo = getGridColumnNumberWithoutQuickFilters("@class", "hdwo-details", columnName);
			
			System.out.println(colNo);
			
			for(int i=0; i<prodArray.length; i++){
				
					WebElement ele = isRowInGridPresent(colNo, prodArray[i]);
					
					waitForExtJSAjaxComplete(25);
			
				if(ele.isDisplayed()) {
					ele.click();
					
					waitForExtJSAjaxComplete(20);
					
					clickButtonTimeAndMaterialConsumptionTab(windowID, "Delete");
					
					waitForExtJSAjaxComplete(20);
					
					clickOnDialogButton("Yes");
					
					waitForExtJSAjaxComplete(25);
					
					waitForExtJSAjaxComplete(25);			
				}
			}
		}
		
		/**
		 * Helper method to Get Grid Column No
		 *//*
		public WebElement isRowInGridPresent(int no, String value){
			WebElement webElement = driver.findElement(By.xpath("//div[contains(@class, 'hdwo-details')]//tr//div[contains(@class, 'col-"+no+"') and text()='"+value+"']"));
			Reporter.log("Check element "+value+" present in grid and click", true);
			try {Thread.sleep(500);} catch (InterruptedException e) {}
			return webElement;
		}
		
		/**
		 * Helper method to Click on AddEdit/Delete Button Time And MAterial Tab
		 *//*
		public void clickButtonTimeAndMaterialConsumptionTab(String attributeID, String buttonName){
			WebElement ele = driver.findElement(
					By.xpath("//div[contains(@class, '"+attributeID+"')]//button[contains(@class, 'x-btn-text') and text()='"+buttonName+"']"));
					
			waitForExtJSAjaxComplete(10);
			try{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			} catch(Exception e){
				e.printStackTrace();
			}
			ele.click();
			Reporter.log("Delet Clicked", true);
		}*/
		
		/**
		 * Helper method to verify if Element is present in the grid and delete it
		 */
		public void deleteProductsFromConsumptionGrid(String windowID, String[] prodArray, String columnName){
			boolean status = false;
			
			int colNo = getGridColumnNumberWithoutQuickFilters("@class", "hdwo-details", columnName);
			
			for(int i=0; i<prodArray.length; i++){
				
					boolean ele = isRowInGridPresent(colNo, prodArray[i]);
					
					waitForExtJSAjaxComplete(25);
			
				if(ele) {
					driver.findElement(By.xpath("//div[contains(@class, 'hdwo-details')]//tr//div[contains(@class, 'col-"+colNo+"') and contains(text(), '"+prodArray[i]+"')]")).click();
					
					waitForExtJSAjaxComplete(20);
					
					waitForExtJSAjaxComplete(25);
					
					clickButtonTimeAndMaterialConsumptionTab(windowID, "Delete");
					
					waitForExtJSAjaxComplete(20);
					
					clickOnDialogButton("Yes");
					
					waitForExtJSAjaxComplete(25);
					
					waitForExtJSAjaxComplete(25);			
				}
			}
		}
		
		/**
		 * Helper method to Get Grid Column No
		 */
		public boolean isRowInGridPresent(int no, String value){

			try {
				driver.findElement(By.xpath("//div[contains(@class, 'hdwo-details')]//tr//div[contains(@class, 'col-"+no+"') and contains(text(), '"+value+"')]"));
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
		public void clickButtonTimeAndMaterialConsumptionTab(String attributeID, String buttonName){
			WebElement ele = driver.findElement(
					By.xpath("//div[contains(@id, '"+attributeID+"')]//div[contains(@class, 'button-icon-extra-spacing')]//button[contains(@class, 'x-btn-text') and text()='"+buttonName+"']"));
					
			waitForExtJSAjaxComplete(10);
			try{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			} catch(Exception e){
				e.printStackTrace();
			}
			ele.click();
			Reporter.log("Delete Clicked", true);
		}
		
		/**
	     * Helper method to check if the button is disabled or not
	     * */
		
		public boolean isStockInfoButtonDisabledInBOMTab()
		{
			WebElement  element= McsElement.getElementByXpath(driver,"//div[contains(@class,'bom-window')]//button[text()='Stock Info']//ancestor::table[contains(@class,'x-btn-noicon')]");

		   	 String classAttrVal =element.getAttribute("class");

		   	 return (classAttrVal.contains("noedit") ||classAttrVal.contains("readonly") || classAttrVal.contains("disabled") )?true:false;

		}
		
		
		/**
	     * Helper method to check if the button is visible or not
	     * */
		
		public boolean isVisibleStockInfoButtonInBOM()
		{
			String xpath="//div[contains(@class,'bom-window')]//td[contains(@class,'x-toolbar-cell') and not (contains(@class,'x-hide-display'))]//button[text()='Stock Info']";
			 return McsElement.isElementPresent(driver, xpath);
			

		}
		
		/**
	     * Helper method to check if the column is not visible
	     * @param colName
	     * */
		
		public boolean isVisibleColumnInBOM(String colName)
		{
			String xpath="//div[contains(@class,'bom-window')]//div[contains(@class,'x-grid3-hd') and text()='"+colName+"']";
			 return McsElement.isElementDisplayed(driver,xpath);	

		}
		
		
		/**
		 * Helper method to Select BOM Line based on status (Needed,Consumed)
		 */
		public void selectBOMLine(String rowNum,String status){
			int colNo = getGridColumnNumberWithoutQuickFilters("@class", "x-panel bom-window","Reference");
			McsElement.getElementByXpath(driver, "//div[contains(@class,'bom-window')]//div[@class='x-grid-group-title']//span[text()='"+status+"']//ancestor::div[contains(@class,'x-grid-group')]//div["+rowNum+"]//td[contains(@class,'x-grid3-td-"+colNo+"')]//div").click();
			waitForExtJSAjaxComplete(10);

		}
		

		/**
		 * Helper method to verify whether tab is present in work order details window 
		 * @param attributeValue (hdwo-details/x-tab-panel-bwrap)
		 * @param tabName to be verified
		 * @return true if present
		 */
		public boolean isTabPresentInWOWindow(String attributeValue, String tabName){
			String xpath = "//div[contains(@class,'x-resizable-pinned') and contains(@style,'visibility: visible')]//div[contains(@class,'"+attributeValue+"')]//span[contains(@class,'strip-text') and contains(text(),'"+tabName+"')]";
			return McsElement.isElementPresent(driver, xpath);
		}

		/**
		 * Helper method to get panel window header
		 * @param attribute of the panel @class
		 * @param attributeValue panel must contain this value 
		 * @param header to be verified
		 * @return true if present
		 */
		public boolean isXPanelWindowHeaderPresent(String attribute, String attributeValue, String header){
			String xpath = "//div[contains("+attribute+",'"+attributeValue+"')]//span[contains(@class,'x-panel-header-text')and contains(text(),'"+header+"')]";
			return McsElement.isElementPresent(driver, xpath);
		}
		

		/**
		 * Helper method to select MObject or MOPart
		 * @param refMO reference of MO or MOpart
		 * @param object (MO/ MOPart)
		 */
		public void selectMObjectOrMOPart(String refMO,String object){
			String xpath = "//div[contains(@class,'x-treegrid-text') and (text()='"+refMO+"')]//ancestor::tr[contains(@class,'x-tree-node')]//span[text()='"+object+"']";
			driver.findElement(By.xpath(xpath)).click();
		}

		/**
		 * Helper method to verify the details of MO or MOPart
		 * @param sectionName under which the details are present 
		 * @param detail to be verified 
		 * @return true if present
		 */
		public boolean isRelatedDataPresentInMaintenanceObjectDetailsWindow(String sectionName,String detail){
			String xpath = "//div[contains(@class,'x-grid3-viewport')]//div[contains(@class,'grid-group-hd') and contains(.,'"+sectionName+"')]//following-sibling::div//div[text()='"+detail+"']";
			return McsElement.isElementDisplayed(driver, xpath);
		}

		/**
		 * Helper method to verify the WO in hierarchy panel
		 * @param woRef to be verified
		 * @return true if present
		 */
		public boolean isWOHierarchyDisplayed(String woRef){
			String xpath = "//span[text()='Hierarchy' and contains(@class,'header-text')]/../..//li//img[contains(@src,'icon_green_helmet.png')]//following-sibling::a[contains(@class,'node-anchor') and contains(.,'"+woRef+"')]";
			return McsElement.isElementPresent(driver, xpath);
		}

		/**
		 * Helper method to get Activity window field value 
		 * @param fieldName to get the value
		 * @return the field value
		 */
		public String getActivityWindowFieldValues(String fieldName){
			String xpath = "//div[@id='"+getXWindowId("Edit Activity")+"']//input[@name='"+fieldName+"']";
			return driver.findElement(By.xpath(xpath)).getAttribute("value").trim();
		}	

		/**
		 * Helper method to get Activity status field ID 
		 * @return ID
		 */
		public String getActivityStatusFieldID(){
			String xpath = "//div[@id='"+getXWindowId("Edit Activity")+"']//input[@name='statusCode']";
			return driver.findElement(By.xpath(xpath)).getAttribute("id");
		}

		/**
		 * Helper method to verify is field highlighted or not 
		 * @param fieldName to be verified
		 * @return true if field is highlighted
		 */
		public boolean isFieldHighlightedInActivityWin(String fieldName){
			String xpath = "//div[@class='x-window' and contains(@style,'visibility: visible')]//label[contains(text(),'"+fieldName+"')]/..//input[contains(@class,'x-form-field')]";
			String className = driver.findElement(By.xpath(xpath)).getAttribute("class");
			return (className.contains("x-form-invalid"))? true : false;
		}
		
		/**
		 * Helper method to get Activity id
		 * @return ID
		 */
		public String getActivityID()
		{
			String xpath=ACTIVITY_WINDOW_XPATH+"//span[contains(text(),'Edit Activity')]";
			String element= McsElement.getElementByXpath(driver, xpath).getText();
			return element.replace("Edit Activity ", " ").replace("00"," ").trim();
		}
		
		/**
		 * Helper method to select Row is Present in the Grid
		 */
		public void checkRowInLaborTabGridForActivity(String value){
			int colNo = getGridColumnNumberWithoutQuickFiltersinLabor("Activity ID");
			
			WebElement element = driver.findElement(By.xpath("//div[contains(@realid, 'WoLaborGrid')]//div[contains(@class, 'x-grid3-body')]//div[contains(@class, 'x-grid3-col-"+colNo+"') and text()='"+value+"']"));
			element.click();
			waitForExtJSAjaxComplete(20);
		}
		
		/**
		 * Helper method to get column number in Grid
		 * @param columnName of whose column number has to be obtained
		 * @return column number
		 */
		public int getGridColumnNumberWithoutQuickFiltersinLabor(String columnName){

			WebElement ele = driver.findElement(By.xpath(LABOR_GRID_XPATH+"//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')][last()]"));

			String columnClass = ele.getAttribute("class"); 
			String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);

			return Integer.parseInt(columnNumber);

		}
	

		/**
		 * Helper method to check Wheather WO Multi Edit Window opened or not
		 */
		public boolean isWOMultiEditWindowOpen() throws Exception
		{

			return McsElement.getElementByXpath(driver, WO_MULTIEDITWINDOW_XPATH).isDisplayed();	

		}

		/**Helper method to check field status 
		 * @param fieldLabel field name
		 * @param attribute div/ input
		 * @return true/false
		 */
		public boolean isDueDateEnabled(){
			String xpath = WO_MULTIEDITWINDOW_XPATH+"//input[@name='dateDue']/..//td[contains(@class,'ux-datetime-date')]//div[contains(@class,'x-form-field')]";
			String className = driver.findElement(By.xpath(xpath)).getAttribute("class");
			return (className.contains("disabled"))? false : true;
		}

		/**Helper method to check field status 
		 * @param fieldLabel field name
		 * @param attribute div/ input
		 * @return true/false
		 */
		public boolean isDueTimeEnabled(){
			String xpath = WO_MULTIEDITWINDOW_XPATH+"//input[@name='dateDue']/..//td[contains(@class,'ux-datetime-time')]//div[contains(@class,'x-form-field')]";
			String className = driver.findElement(By.xpath(xpath)).getAttribute("class");
			return (className.contains("disabled"))? false : true;
		}

		/**Helper method to Enable the field
		 * @param field name
		 */
		public void clickCustomCheckBox(String fieldLabel){
			String xpath = WO_MULTIEDITWINDOW_XPATH+"//label[contains(text(),'"+fieldLabel+"')]/../../../preceding-sibling::td//div[contains(@class,'customCheckbox')]";
			McsElement.getElementByXpath(driver, xpath).click();

		}
		
		/**
		 * Helper method to select first Action in the Possible actions
		 */
		
		public void clickFirstActionInPossibleActions()
		{
			String xpath="//div[contains(@class, 'actions-dialog')]//div[contains(@class, 'x-list-body-inner')]//dl[1]";
			
			McsElement.getElementByXpath(driver, xpath).click();

		}
		
		/**
		 * Helper method to Click Button in Actions window
		 */
		public void clickButtonInActionWindow(String btnname)
		{
			String xpath="//div[contains(@class,'x-window-footer x-panel-btns')]//button[.='"+btnname+"']";
			
			McsElement.getElementByXpath(driver, xpath).click();

		}
		
		/**
		 * Helper method to get column number in Grid
		 * @param columnName of whose column number has to be obtained
		 * @return column number
		 */
		public int getGridColumnNumberWithoutQuickFiltersinHistory(String columnName){

			WebElement ele = driver.findElement(By.xpath("//div[@realid='HistoryGridWORKORDER']//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')][last()]"));

			String columnClass = ele.getAttribute("class"); 
			String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);

			return Integer.parseInt(columnNumber);

		}
		
		/**
		 * Helper method to Wheather planner is added to the Action or not
		 */
		public boolean isPlannerAddedToAction(String plannerName,String taskForce)
		{
			int columNum =getGridColumnNumberWithoutQuickFiltersinHistory("Planner");
			int columNo =getGridColumnNumberWithoutQuickFiltersinHistory("Task Force");
			
			String xpath="//div[contains(@realid,'HistoryGridWORKORDER')]//div[contains(@class,'x-grid3-row-first')]//div[contains(@class,'x-grid3-cell-inner x-grid3-col-"+columNum+"') and contains(text(),'"+plannerName+"') and //div[contains(@class,'x-grid3-cell-inner x-grid3-col-"+columNo+"') and contains(text(),'"+taskForce+"')]]";
			
			return McsElement.getElementByXpath(driver, xpath).isDisplayed();

		}
		
		/**
		 * Helper method to check field status 
		 * @param fieldLabel field name
		 * @param attribute div/ input
		 * @return true/false
		 */
		public boolean isFieldDisabledInActivity(String fieldLabel,String attribute){
			String xpath = ACTIVITY_WINDOW_XPATH+"//label[contains(text(),'"+fieldLabel+"')]/..//"+attribute+"[contains(@class,'x-form-field')]";
			String className = driver.findElement(By.xpath(xpath)).getAttribute("class");
			return (className.contains("disabled"))? true : false;
		}

		/**
		 * Helper function to return value  field value except Activity Date in Activity Window
		 * @param fieldLabel: label of field whose value has to be retrieved
		 * @return field value
		 * This metod wont work for Activity Date
		 */
		public String getFieldValueBasedOnLabelInActivity(String fieldLabel){

			String xpath = ACTIVITY_WINDOW_XPATH+"//label[text()='"+fieldLabel+"']/..//input[contains(@class,'x-form-field')]";

			return McsElement.getElementByXpath(driver, xpath).getAttribute("value");

		}
		
		/**
		 * Helper method to click saveActivity 
		 */
		public void clickSaveActivity(){

			String xpath = ACTIVITY_WINDOW_XPATH+"//label[text()='Activity Date:']/following::button[.='Save']";

			McsElement.getElementByXpath(driver, xpath).click();

		}
		
		/**
		 * Helper method to check wheather finkey displayed or not
		 * @return true or false
		 */
		public boolean isFinkeyDisplayed(String finkeyname)
		{
			String xpath="//span[contains(.,'Financial Keys')]/../..//label[contains(.,'"+finkeyname+"')]";
			
			return McsElement.getElementByXpath(driver, xpath).isDisplayed();

		}
		
		/**
		 * Helper method to verify hierarachy
		 */
		
		public boolean isHierarchySectionDisplayedInHierarachyPanel()
		{
			String xpath=WO_HIERARCHY_XPATH+"//span[contains(@class,'x-panel-header-text') and text()='Hierarchy']";
			return McsElement.isElementDisplayed(driver, xpath);
		}
		
		/**
		 * Helper method to verify expand the Work order hierarchy
		 * @param name
		 */
		public boolean isTreeExpandedInHierarachyPanel(String name){
			WebElement element = driver.findElement(By.xpath(WO_HIERARCHY_XPATH+"//a[contains(@class,'x-tree-node-anchor')]//span[contains(text(),'"+name+"')]/../.."));
			String clsValue=element.getAttribute("class");
				if(clsValue.contains("expanded")){
					return true;
				}
			return false;
		}	
		
		
		/**
		 * Helper method to verify project 
		 * @param name
		 */
		public boolean isProjectDisplayedOnTopOfTreeInHierarachyPanel(String project){
			WebElement element = driver.findElement(By.xpath(WO_HIERARCHY_XPATH+"//a[contains(@class,'x-tree-node-anchor')]//span[contains(text(),'"+project+"')]/../.."));
			String clsValue=element.getAttribute("ext:tree-node-id");
				if(clsValue.contains("master")){
					return true;
				}
			return false;
		}	
		
		/**
		 * Helper method to verify active Work order 
		 * @param name
		 */
		public boolean isCurrentWOActiveInHierarachyPanel(String woName){
			WebElement element = driver.findElement(By.xpath(WO_HIERARCHY_XPATH+"//a[contains(@class,'x-tree-node-anchor')]//span[contains(text(),'"+woName+"')]/../.."));
			String clsValue=element.getAttribute("class");
				if(clsValue.contains("active-node")){
					return true;
				}
			return false;
		}	
		
		
		/**
		 * Helper method to click on project 
		 * @param projectName
		 */
		
		public void clickOnProjectInHierarachyPanel(String projectName)
		{
			String xpath=WO_HIERARCHY_XPATH+"//img[contains(@src,'project')]/..//a[contains(@class,'x-tree-node-anchor')]//span[contains(text(),'"+projectName+"')]";
			McsElement.getElementByXpath(driver, xpath).click();
		}

		
		/**
		 * Helper method to click on edit button
		 */
		public void clickEditButton(){
			clickXPath("//div[@id='wotabpanel']//button[contains(@class, 'icon-ov-edit') and text()='Edit']");
		}
	
		/**
		 * Helper method to verify Work order is displayed or not
		 * @param workorder
		 */
		
		public boolean isWorkOrderDisplayedInHierarachyPanel(String workorder )
		{
			String xpath=WO_HIERARCHY_XPATH+"//a[contains(@class,'x-tree-node-anchor')]//span[contains(text(),'"+workorder+"')]";
			return McsElement.isElementPresent(driver, xpath);
		}
		
		
		/**
		 * Helper method to click on project 
		 * @param woName
		 */
		
		public void clickOnWorkOrderInHierarachyPanel(String woName)
		{
			String xpath=WO_HIERARCHY_XPATH+"//a[contains(@class,'x-tree-node-anchor')]//span[contains(text(),'"+woName+"')]";
			McsElement.getElementByXpath(driver, xpath).click();
		}	

		/**
		 * Helper method to Select BOM Line based on status (Needed,Consumed)
		 */
		public void selectMutipleBOMLines(String status,String productRef){
			List<WebElement> elements= driver.findElements(By.xpath("//div[contains(@class,'bom-window')]//div[@class='x-grid-group-title']//span[text()='"+status+"']//ancestor::div[contains(@class,'x-grid-group')]//span[text()='"+productRef+"']/../..//preceding-sibling::td[contains(@class,'x-grid3-td-checker')]"));
			for(WebElement element:elements)
			{
				element.click();
				waitForExtJSAjaxComplete(10);
			}

		}
		
		/**
		 * Helper method to get quantities
		 */
		public List<Integer> getBOMLineQuantities(String status,String colName,String productRef){
			
			List<Integer> productQty = new ArrayList<Integer>();
			int colNo = getGridColumnNumberWithoutQuickFilters("@class", "x-panel bom-window",colName);
			List<WebElement> elements= driver.findElements(By.xpath("//div[contains(@class,'bom-window')]//div[@class='x-grid-group-title']//span[text()='"+status+"']//ancestor::div[contains(@class,'x-grid-group')]//span[text()='"+productRef+"']/../..//following-sibling::td[contains(@class,'x-grid3-td-"+colNo+"')]"));
			for(WebElement element:elements)
			{
				int availableStockValue = Integer.parseInt(element.getText().trim());
				productQty.add(availableStockValue);
			}
			return productQty;

		}
		
		/**
		 * Helper method to click button
		 */
		public void clickOnQuickGoodsReturnButtonInBOM()
		{
			String xpath="//div[contains(@class,'bom-window')]//button[contains(@class,'x-btn-text') and text()='Quick Goods Return']";
			driver.findElement(By.xpath(xpath)).click();
		}
		
		/**
		 * Helper method to verify QGR button is displayed next to Quick Goods Issue
		 */
		
		public boolean isQGRButtonIsAddedNextToQGI()
		{
			String xpath="//div[contains(@class,'bom-window')]//div[contains(@class,'x-panel-btns')]//td[contains(@class,'x-toolbar-left')]//td[contains(.,'Quick Goods Issue')]//following-sibling::td[contains(.,'Quick Goods Return')]";
			
			return McsElement.isElementPresent(driver, xpath);
		}
		
		/**
		 * Helper method to select Returned By in goods return window
		 */
		 
		public void selectReturnByInQGR(String name){
			
			//String xpath=QGR_WINDOW_XPATH+"//div[contains(@class,'mcslookup-invalid')]//button[contains(@class,'x-btn-text')]";
			String xpath=QGR_WINDOW_XPATH+"//label[contains(text(),'taffed')]/following::div[contains(@class,'mcslookup')]//button[contains(@class,'x-btn-text')]";
			
			driver.findElement(By.xpath(xpath)).click();
			
			setValueGridLookupWithFilters("@id", getXWindowId("Select a Person"), name,"Last Name");
			
			Reporter.log("returned by person selected", true);
			
		}
		
		/**
		 * Helper method to set Quantity of Transaction line
		 * @param value : Quantity to set
		 * **/
		
		
		public void setMultiLineQuantityInQGR(String ref,String value){
			
			List<WebElement> elements = driver.findElements(By.xpath(QGR_WINDOW_XPATH+"//div[text()='"+ref+"']/..//following-sibling::td[contains(@class,'x-grid3-td-returnedQuantity')]//div"));
			Actions action=new Actions(driver);
			for(WebElement ele:elements)
			{
				action.doubleClick(ele).build().perform();
				WebElement edit=driver.findElement(By.xpath("//div[contains(@class,'x-grid-editor') and contains(@style,'visibility: visible')]//input[contains(@class,'x-form-num-field')]"));
				edit.clear();
				edit.sendKeys(value);
				edit.sendKeys(Keys.ENTER);
			}
			
		}
		
		/**
		 * Helper method to set Quantity of Transaction line
		 * @param value : Quantity to set
		 * **/
		
		
		public void setMultiLineRemarksInQGR(String productRef,String value){
			
			List<WebElement> elements = driver.findElements(By.xpath(QGR_WINDOW_XPATH+"//div[text()='"+productRef+"']/..//following-sibling::td[contains(@class,'x-grid3-td-remark')]//div"));
			Actions action=new Actions(driver);
			for(WebElement ele:elements)
			{
				action.doubleClick(ele).build().perform();
				WebElement edit=driver.findElement(By.xpath("//div[contains(@class,'x-grid-editor') and contains(@style,'visibility: visible')]//input[contains(@class,'x-form-field')]"));
				edit.clear();
				edit.sendKeys(value);
				edit.sendKeys(Keys.ENTER);
			}
			
		}
		
		
		/**
		 * Helper method to click save button
		 */
		
		public void clickOnSaveInQGR()
		{
			String xpath=QGR_WINDOW_XPATH+"//div[contains(@class,'x-panel-btns')]//button[contains(@class,'x-btn-text') and text()='Save']";
			McsElement.getElementByXpath(driver, xpath).click();
		}
		
		/**
		 * Helper method to check field status 
		 * @param fieldLabel field name
		 * @param attribute div/ input
		 * @return true/false
		 * This method wont work for Date Related Fields
		 */
		public boolean isFieldEnabledInWOMultiWindow(String fieldLabel,String attribute){
			String xpath = WO_MULTIEDITWINDOW_XPATH+"//label[contains(text(),'"+fieldLabel+"')]/..//"+attribute+"[contains(@class,'x-form-field')]";
			String className = driver.findElement(By.xpath(xpath)).getAttribute("class");
			return (className.contains("disabled"))? false : true;
		}

		/**Helper method to check field status 
		 * @param fieldLabel field name
		 * @param attribute div/ input
		 * @return true/false
		 */
		public boolean isPlannedStartDateEnabled(){
			String xpath = WO_MULTIEDITWINDOW_XPATH+"//input[@name='datePlanned']/..//td[contains(@class,'ux-datetime-date')]//div[contains(@class,'x-form-field')]";
			String className = driver.findElement(By.xpath(xpath)).getAttribute("class");
			return (className.contains("disabled"))? false : true;
		}
		
		/**Helper method to check field status 
		 * @param fieldLabel field name
		 * @param attribute div/ input
		 * @return true/false
		 */
		public boolean isPlannedStartTimeEnabled(){
			String xpath = WO_MULTIEDITWINDOW_XPATH+"//input[@name='datePlanned']/..//td[contains(@class,'ux-datetime-time')]//div[contains(@class,'x-form-field')]";
			String className = driver.findElement(By.xpath(xpath)).getAttribute("class");
			return (className.contains("disabled"))? false : true;
		}


		/**Helper method to check field status 
		 * @param fieldLabel field name
		 * @param attribute div/ input
		 * @return true/false
		 */
		public boolean isPlannedEndDateEnabled(){
			String xpath = WO_MULTIEDITWINDOW_XPATH+"//input[@name='dateFinished']/..//td[contains(@class,'ux-datetime-date')]//div[contains(@class,'x-form-field')]";
			String className = driver.findElement(By.xpath(xpath)).getAttribute("class");
			return (className.contains("disabled"))? false : true;
		}


		/**Helper method to check field status 
		 * @param fieldLabel field name
		 * @param attribute div/ input
		 * @return true/false
		 */
		public boolean isPlannedEndTimeEnabled(){
			String xpath = WO_MULTIEDITWINDOW_XPATH+"//input[@name='dateFinished']/..//td[contains(@class,'ux-datetime-time')]//div[contains(@class,'x-form-field')]";
			String className = driver.findElement(By.xpath(xpath)).getAttribute("class");
			return (className.contains("disabled"))? false : true;
		}

		/**Helper method to check field status 
		 * @param fieldLabel field name
		 * @param attribute div/ input
		 * @return true/false
		 */
		public boolean isCustomcheckboxDisabledInWOMultiWindow(String fieldLabel){

			String xpath = WO_MULTIEDITWINDOW_XPATH+"//label[contains(text(),'"+fieldLabel+"')]/../../../preceding-sibling::td//div[contains(@class,'customCheckbox')]/..";
			String className = driver.findElement(By.xpath(xpath)).getAttribute("class");
			return (className.contains("hide-display"))? true : false;
		}

		/**
		 * Helper method to click Button in WO Multi Edit Window
		 */
		public void clickButtonInWOMultiEditWindow(String btnName){

			String xpath = WO_MULTIEDITWINDOW_XPATH+"//button[text()='"+btnName+"']";

			McsElement.getElementByXpath(driver, xpath).click();

		}


		/**
		 * Method allows to filter in grid by text
		 * @param attribute - attribute (@class, @id) of the grid container
		 * @param attributeValue - corresponding value of the grid container attribute
		 * @param rowTextName - row text to be filtered by 
		 * @columnName - columnName of the row 
		 */
		public void filterGridForWO(String attribute, String attributeValue, String rowTextName, String columnName) {
			Timer timer = new Timer().start();


			String columnClass = McsElement
					.getElementByXpath(driver,"(//div[contains("+attribute+",'"+ attributeValue+"')]//div[contains(@class,'quickfilters')]"
							+ "//div[contains(@class,'x-grid3-hd') and contains(text(),'"+columnName+"')])[last()]").getAttribute("class");

			String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);

			WebElement filterInput = McsElement.getElementByXpath(driver, "(//div[contains("+attribute+",'"+attributeValue+"')]//"
					+ "input[contains(@id,'filter-editor-"+columnNumber+"')])"); 

			filterInput.clear();

			filterInput.sendKeys(rowTextName);
			waitForExtJSAjaxComplete(5);
			filterInput.sendKeys(Keys.RETURN);
			waitForExtJSAjaxComplete(5);

			Reporter.log(rowTextName + " was filtered"+ " (" + timer.stop()
					+ "ms)", true);

		}


		/**
		 * Helper method to click Select All check Box Button
		 */
		public void clickSelectAllcheckButtonInOverview(){

			String xpath ="//div[contains(@realid,'mogrid')]//div[contains(@class,'x-grid3-hd-inner x-grid3-hd-checker')]";

			McsElement.getElementByXpath(driver, xpath).click();

		}
		
		/**Helper method to check Add Action is enabled or not
		 * @return true/false
		 */
		public boolean isAddActionButtonEnabled(){
			String xpath = WO_MULTIEDITWINDOW_XPATH+"//button[contains(text(),'Add Action')]/ancestor::table[contains(@class,'x-btn')]";
			String className = driver.findElement(By.xpath(xpath)).getAttribute("class");
			return (className.contains("disabled"))? false : true;
		}
		
		/**Helper method to get Resulting Status of an action
		 */
		public String getResultingStatusOfAction(){
			
			 return McsElement.getElementByXpath(driver, "//div[contains(@class, 'actions-dialog')]//div[contains(@name,'actionResultStatus')]").getText();
			
		}
		
		/**
		 * Helper method to check workorders status is added to the action
		 * @throws IOException 
		 */
		public boolean isWOStatusAddedToAction(String woStatus) throws IOException
		{
			int columNum =getGridColumnNumberWithoutQuickFiltersinHistory("Result Status");
			int columNo  =getGridColumnNumberWithoutQuickFiltersinHistory("Date");
			String date  =getCurrentSystemDate();
			
			String xpath="//div[contains(@realid,'HistoryGridWORKORDER')]//div[contains(@class,'x-grid3-row-first')]//div[contains(@class,'x-grid3-cell-inner x-grid3-col-"+columNum+"') and contains(text(),'"+woStatus+"') and //div[contains(@class,'x-grid3-cell-inner x-grid3-col-"+columNo+"') and contains(text(),'"+date+"')]]";
			
			return McsElement.getElementByXpath(driver, xpath).isDisplayed();

		}
		
		/**
	     * Helper method to check if the button is disabled or not
	     * */
		
		public boolean isDisabledButtonInBOMTab(String button)
		{
			WebElement  element= McsElement.getElementByXpath(driver,BOM_WINDOW_XPATH+"//button[text()='"+button+"']//ancestor::table[contains(@class,'x-btn-noicon')]");

		   	 String classAttrVal =element.getAttribute("class");

		   	 return (classAttrVal.contains("noedit") ||classAttrVal.contains("readonly") || classAttrVal.contains("disabled") )?true:false;

		}
		
		/**
		 * Helper method to click Select All check Box Button
		 */
		public void checkSelectAllcheckBoxButtonInBOM(){

			WebElement element =driver.findElement(By.xpath(BOM_WINDOW_XPATH+"//div[contains(@class,'x-grid3-header')]//td[contains(@class,'x-grid3-td-checker')]/div"));
			String cls=element.getAttribute("class");
			if(!cls.contains("x-grid3-hd-checker-on"))
			{
				WebElement ele=element.findElement(By.xpath("./div"));
				ele.click();
			}
			
			Reporter.log("check box is checked", true);

		}
		
		/**
		 * Helper method to uncheck  Select All check Box Button
		 */
		public void unCheckSelectAllcheckBoxButtonInBOM(){

			WebElement element =driver.findElement(By.xpath(BOM_WINDOW_XPATH+"//div[contains(@class,'x-grid3-header')]//td[contains(@class,'x-grid3-td-checker')]/div"));
			String cls=element.getAttribute("class");
			if(cls.contains("x-grid3-hd-checker-on"))
			{
				WebElement ele=element.findElement(By.xpath("./div"));
				ele.click();
			}
			
			Reporter.log("Check box is unchecked", true);

		}
		
		/**
		 * Helper method to select warehouse
		 */
		 
		public void selectWarehouseInQGR(String warehouse){
			
			String xpath=QGR_WINDOW_XPATH+"//label[text()='Warehouse']//..//button[contains(@type,'button')]";
			
			driver.findElement(By.xpath(xpath)).click();
			
			waitForExtJSAjaxComplete(5);
			
			setValueGridLookupWithFilters("@id", getXWindowId("Select A Warehouse"), warehouse,"Reference");
			
			Reporter.log("Warehouse is selected", true);
			
		}
		
		
		
		/**
		 * Helper method to get info bar message
		 */
		public String getInfoBarMessageInBOM()
		{
			
			WebDriverWait wait = new WebDriverWait(driver, 50);

			String message = wait.until(new ExpectedCondition<String>() {
			                    public String apply(WebDriver d) {
			                        WebElement el = d.findElement(By.xpath(BOM_WINDOW_XPATH+"//..//div[contains(@class,'infobar') and not(contains(@class,'x-hide-display'))]"));
			                        if(el.getText().length() != 0) {
			                            return el.getText();
			                        }
			                        return el.getText(); 
			                    }
			                });
		
			
			//WebElement ele=driver.findElement(By.xpath(BOM_WINDOW_XPATH+"//..//div[contains(@class,'infobar') and not(contains(@class,'x-hide-display'))]"));
			return message;
			
		}
		/**
		 * Helper method to Select BOM Line based  stock item or non stock item indicator
		 */
		public void selectNonStockIntemsInBOMLines(String status,String clsName){
			List<WebElement> elements= driver.findElements(By.xpath(BOM_WINDOW_XPATH+"//div[@class='x-grid-group-title']//span[text()='"+status+"']//ancestor::div[contains(@class,'x-grid-group')]//td[contains(@class,'"+clsName+"')]/../..//preceding-sibling::td[contains(@class,'x-grid3-td-checker')]"));
			for(WebElement element:elements)
			{
				element.click();
				waitForExtJSAjaxComplete(10);
			}

		}
		
		/**
		 * Helper method to get warehouse in BOM window
		 * @param status of the product present
		 * @param colName coloumn name of the row
		 * @param prodRef product reference to identify warehouse
		 * @return warehouse name
		 */
		public String getWarehouseInBOM(String status,String colName,String prodRef){
			int colNo = getGridColumnNumberWithoutQuickFilters("@class", "x-panel bom-window",colName);
			WebElement element=driver.findElement(By.xpath(BOM_WINDOW_XPATH+"//div[@class='x-grid-group-title']//span[text()='"+status+"']//ancestor::div[contains(@class,'x-grid-group')]//span[text()='"+prodRef+"']/../..//following-sibling::td[contains(@class,'x-grid3-td-"+colNo+"')]//div"));
			return element.getText(); 
			
		}
		
		/**
		 * Helper method to get warehouse in QGR window
		 * @return value
		 */

		public String getWarehouseInQGR(){
			WebElement element=driver.findElement(By.xpath(QGR_WINDOW_XPATH+"//label[text()='Warehouse']//..//input[contains(@type,'text')]"));
			return element.getAttribute("value"); 
			
		}
		
		/**
		 * Helper method to click Add Button in Bill Of Materials Tab
		 */
		public void clickAddButtonInBOM() {
			clickXPath(BOM_WINDOW_XPATH+"//button[contains(@class, 'x-btn-text') and text()='Add']");
		}
		/**
		 * Helper method allows to add BOM line
		 * Product Reference
		 */
		public void addBOMLine(String productRef){
			clickAddButtonInBOM();
			waitForExtJSAjaxComplete(10);
			setValueGridLookupWithFiltersWithoutUsingMCSElement("@id",getXWindowId("Select Products or Services"), productRef, "Reference");

		}
		
		/**
		 * Helper method to click Add Button in Bill Of Materials Tab
		 */
		public void clickOnSaveInBOM() {
			clickXPath(BOM_WINDOW_XPATH+"//button[contains(@class, 'x-btn-text') and text()='Save']");
		}
		

		/**
		 * Helper method to get Work order status
		 * @return the status
		 */
		
		public String getWorkOrderStatus(){
			WebElement element=driver.findElement(By.xpath(WO_DETAILSWINDOW_XPATH+"//div[contains(@class,'column-status')]"));
			return element.getText();
			
		}
		
		

		/**
		 * Helper method to identify the UI Element
		 * @param checkListItem to with the UI is present
		 * @param elementOfUI to be verified (addfile.png/icon-edit.png)
		 * @return true if UI present
		 */
		public boolean isUIElementPresentInCheckList(String checkListItem,String elementOfUI){
			String xpathOfUIEle = "//div[contains(@class,'quality-question-item') and contains(.,'"+checkListItem+"')]//button[contains(@class,'x-btn-text') and contains(@style,'"+elementOfUI+"')]";
			return	McsElement.isElementPresent(driver, xpathOfUIEle);
		}


		/**
		 * Helper method to identify the question in checklist
		 * @param qstInCheckList to be identified
		 * @return false if question hidden
		 */
		public boolean isQuestionAvailableInCheckList(String qstInCheckList){
			String className =	driver.findElement(By.xpath("//div[contains(@class,'quality-form-content')]//div[contains(@class,'quality-question-item')]//label[contains(@class,'x-form-item-label') and (text()='"+qstInCheckList+"')]/..")).getAttribute("class");
			return (className.contains("x-hide-display"))?false:true;
		}

		/**
		 * Helper method to wait till the checklist is loaded
		 * @throws InterruptedException
		 */
		public void waitUntilCheckListIsLoaded() throws InterruptedException{
			String elementXpath = "//div[contains(@class,'x-window-plain') and contains(@style,'visibility: hidden')]//div[contains(@class,'ext-mb-icon x-hidden')]";

			try{
				new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
			}
			catch(Exception e){
				Thread.sleep(8000);
			}

		}
		
		/**
		 * Helper method to click Close button
		 */

		public void clickOnCloseInQGR()
		{
			String xpath=QGR_WINDOW_XPATH+"//div[contains(@class,'x-panel-btns')]//button[contains(@class,'x-btn-text') and text()='Close']";
			McsElement.getElementByXpath(driver, xpath).click();
		}
		
		
		/**
	     * Helper method to check if the button is disabled or not
	     * */
		
		public boolean isButtonReadOnlyInQGRWindow(String colName)
		{
			

			WebElement ele = driver.findElement(By.xpath(QGR_WINDOW_XPATH+"//div[text()='"+colName+"']"));
			
			String columnClass = ele.getAttribute("class");
			String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);
			
			System.out.println(columnNumber);
				
			WebElement  element= McsElement.getElementByXpath(driver,QGR_WINDOW_XPATH+"//div[contains(@class,'x-grid3-cell-inner x-grid3-col-"+columnNumber+"')]/..");

		   	 String classAttrVal =element.getAttribute("class");

		   	 return (classAttrVal.contains("not-editable") ||classAttrVal.contains("readonly") || classAttrVal.contains("disabled") )?true:false;

		}
		
		/**
		 * 
		 */
		public void clickHistoryDetails(){
			McsElement.getElementByXpath(driver, "//div[contains(@realid,'HistoryGrid')]//button[text()='History Details']").click();
			Reporter.log("History details was clicked <br>", true);
		}
		
		public String getHistoryDetailsHeader()
		{
			String xpath="//div[contains(@class,'x-window x-resizable-pinned') and (contains(@style,'visibility: visible'))][2]//div[contains(@class,'x-window-header')]/span";
			WebElement element=driver.findElement(By.xpath(xpath));
			return element.getText();
		}
		/**Helper method to get Resulting Status of an action
		 */
		public List<String> getResultingStatusOfActionInHistoryTab(String colName){
			
			List<String> values = new ArrayList<String>();
			String headerXpath = WO_DETAILSWINDOW_XPATH+"//div[@class='x-grid3']//div[contains(@class,'x-grid3-hd') and text()='"+colName+"']";

			WebElement columnEle = driver.findElement(By.xpath(headerXpath));

			String columnClass = columnEle.getAttribute("class");

			String colNum = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
			
			List<WebElement> statuses = driver.findElements(By.xpath(WO_DETAILSWINDOW_XPATH+"//div[@class='x-grid3-body']//div[contains(@class,'x-grid3-col-"+colNum+"')]"));
			
			for(WebElement ele:statuses)
			{
				values.add(ele.getText());
			}
			return values;
			
		}

		/**
		 * Helper method to verify grid empty
		 */
		public boolean isHistoryDetailsGdridEmpty()
		{
		
		String xpath=WO_DETAILSWINDOW_XPATH+"//div[contains(@class,'x-grid3-body')]/div";
		WebElement element=driver.findElement(By.xpath(xpath));
		String value=element.getText();
		if(value.contains("No records available"))
		{
			return true;
		}else{
			return false;
		}
		}
		
		/**
		 * Helper method to get all values in result sttaus
		 * @return
		 */
		public List<String> getLastActionStatusInHistoryDetailsWindow(){
		
			List<String> values = new ArrayList<String>();
		String xpath=WO_DETAILSWINDOW_XPATH+"//div[contains(@class,'x-grid3-col-newValue')]";
		List<WebElement> elements=driver.findElements(By.xpath(xpath));
		for(WebElement ele:elements)
		{
		 values.add(ele.getText());
		}
		return values;
		}
		
		public static boolean isRowInGridPresent(WebDriver webDriver, String textValue) {
			try {
				String xpath = "//*[contains(@class,'x-treegrid')]//div[contains(@class,'x-treegrid-text') and contains(text(),'"+textValue+"')]";
				webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				webDriver.findElement(By.xpath(xpath));
				return true;
			} catch (Exception e) {
				return false;
			} finally {
				try {webDriver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
			}

		}
}
