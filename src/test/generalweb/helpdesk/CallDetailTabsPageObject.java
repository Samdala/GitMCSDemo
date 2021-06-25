package test.generalweb.helpdesk;




import java.util.ArrayList;
import java.util.List;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class CallDetailTabsPageObject extends AbstractMcsTestSuite {
	
	
	
	protected final String DETAILS_WINDOW_HEADER="Details";
	
	protected final String ADD_ACTION_WINDOW_HEADER="Add Action";
	
	protected final String SEND_NOTIFICATION_WINDOW_HEADER="Send Notification for Call";
	
	protected final String EDIT_ACTION_WINDOW_HEADER="Edit Action for Call";
	
	protected final String ACTION_HISTORY_WINDOW_HEADER="Action History for Call";
	
	protected final String CALL_FIRST_STATUS_CLASS="x-grid3-td-oldValue";
	
	protected final String CALL_LAST_STATUS_CLASS="x-grid3-td-newValue";
	
	protected final String ADD_URL_XPATH = "//button[text()='Add URL' and contains(@style,'icon-addurl.png')]";
	
	protected final String HYPERLINK_XPATH = "//input[@name='strHyperlink']";
	
	protected final String DETAILS_WINDOW_CLASS="//div[contains(@class,'x-window x-resizable-pinned') and (contains(@style,'visibility: visible'))]";
	
	protected final String ADD_ACTION_WINDOW_CLASS="//div[contains(@class,'x-form-label-top x-resizable-pinned') and (contains(@style,'visibility: visible'))]";
	
	protected final String SEND_NOTIFICATION_WINDOW="//div[contains(@class,'x-window x-resizable-pinned') and (contains(@style,'visibility: visible'))][last()]";
	
	protected final String XPATH_SEND_NOTIFICATION_LINES_GRID = SEND_NOTIFICATION_WINDOW+"//div[@class='x-grid3']";
	
	protected final String XPATH_SEND_NOTIFICATION_LINES_GRID_BODY = XPATH_SEND_NOTIFICATION_LINES_GRID+"//div[@class='x-grid3-body']";
	
	protected final String ADD_URL_CREATE_XPATH = "//div[contains(@class,'x-window')]//button[text()='Create']";
	
	protected final String ADD_URL_SAVE_XPATH = "//div[contains(@class,'x-window')]//button[text()='Save']";
	
	protected final String ADD_FILE_XPATH = "//button[text()='Add File' and contains(@style,'icon-addfile.png')]";
	
	protected final String EDIT_URL_XPATH = "//button[text()='Edit' and contains(@style,'icon-edit.png')]";
	
	protected final String DELETE_URL_XPATH = "//button[text()='Delete' and contains(@style,'icon-delete.png')]";
	
	protected final String MAINTANCE_OBJECT_TAB_XPATH = "//div[contains(@class,'hdwo-details')]//span[contains(text(),'Maintenance Objects')]";
	
	protected final String DESCRIPTION_TAB_XPATH = "//div[contains(@class,'hdwo-details')]//span[contains(text(),'Description')]";
	
	protected final String TIME_MATERIAL_TAB_XPATH = "//div[contains(@class,'hdwo-details')]//span[contains(text(),'Material') and contains(text(),'Time')]";
	
	protected final String TRACKING_TAB_XPATH = "//div[contains(@class,'hdwo-details')]//span[contains(text(),'Tracking')]";
	
	protected final String DOCUMENT_TAB_XPATH = "//div[contains(@class,'hdwo-details')]//span[contains(text(),'Documents')]";
	
	protected final String ATTACHMENTS_TAB_XPATH = "//div[contains(@class,'hdwo-details')]//span[contains(text(),'Attachments')]";

	protected final String TIME_MATERIAL_CONSUMPTION_TAB_XPATH ="//div[contains(@class,'hdwo-details')]//span[contains(text(),'Consumption')]"; 
	
	protected final String TRACKING_HISTORY_TAB_XPATH ="//div[contains(@class,'hdwo-details')]//span[contains(text(),'History')]"; 
	
	protected final String TIME_MATERIAL_LABOR_TAB_XPATH ="//div[contains(@class,'hdwo-details')]//span[contains(text(),'Labor')]"; 
	
	protected final String ACTIVITY_WINDOW_CLASS = "x-window";
	
	protected final String ACTIVITY_GENERAL_TAB = "//div[contains(@class,'x-window')]//span[text()='General Information']";

	protected final String ACTIVITY_FINANCIAL_TAB = "//div[contains(@class,'x-window')]//span[text()='Financial Details']";
	
	protected final String ACTIVITY_NOTES_TAB = "//div[contains(@class,'x-window')]//span[text()='Activity Notes']";
	
	protected final String ADD_CONSUMPTION_WINDOW_HEADER = "Add Consumption";

	protected final String EDIT_CONSUMPTION_WINDOW_HEADER = "Edit Consumption";

	protected final String TRACKING_SLA_TAB_XPATH ="//div[contains(@class,'hdwo-details')]//span[contains(text(),'SLA')]"; 

	protected final String PARAMETER_QUANTITY_XPATH = "//label[text()='Quantity:']/following::input[contains(@class,'x-form-num-field')]";
	
	protected final String XPATH_SLA = "//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'SLA Management')]";
	
	protected final String WORKORDERS_TAB_XPATH = DETAILS_WINDOW_CLASS+"//div[contains(@class,'hdwo-details')]//span[contains(@class,'x-tab-strip-text') and contains(text(),'Work Orders')]";
	
	public void clickTimeMaterialTab(){
		clickXPath(TIME_MATERIAL_TAB_XPATH);
		Reporter.log("Time material tab was clicked <br>", true);
	}	
		

	public void clickDocumentsTab(){
		clickXPath(DOCUMENT_TAB_XPATH);
		Reporter.log("Document tab was clicked <br>", true);
	}	
	
	public void clickAttachmentsTab(){
		clickXPath(ATTACHMENTS_TAB_XPATH);
		Reporter.log("Attachments tab was clicked <br>", true);
	}	
	
	public void clickTrackingTab(){
		clickXPath(TRACKING_TAB_XPATH);
		Reporter.log("Tracking tab was clicked <br>", true);
	}	
	
	public void clickTrackingHistoryTab(){
		clickXPath(TRACKING_HISTORY_TAB_XPATH);
		Reporter.log("Tracking history tab was clicked <br>", true);
	}	
	
	public void clickMaintenanceObjectTab(){
		clickXPath(MAINTANCE_OBJECT_TAB_XPATH);
		Reporter.log("Maintenance objects tab was clicked <br>", true);
	}	

	
	public void clickTimeMaterialConsumptionTab(){
		clickXPath(TIME_MATERIAL_CONSUMPTION_TAB_XPATH);
		Reporter.log("Time material consumption tab was clicked <br>", true);
	}	

	public void clickDescriptionTab(){
		clickXPath(DESCRIPTION_TAB_XPATH);
		Reporter.log("Description tab was clicked <br>", true);
	}	
	
	public void clickTimeMaterialLaborTab(){
		clickXPath(TIME_MATERIAL_LABOR_TAB_XPATH);
		Reporter.log("Time material labor tab was clicked <br>", true);
	}	

	
	////////////////////////////LABOR TAB METHODS////////////////////////////	
	
	
	public void clickAddLabor(){
		clickXPath("//div[@realid='CallLaborGrid']//button[text()='Add']");
		Reporter.log("add labor was clicked <br>", true);
	}

	public void clickEditLabor(){
		clickXPath("//div[@realid='CallLaborGrid']//button[text()='Edit']");
		Reporter.log("edit labor was clicked <br>", true);
	}
	
	public void clickDeleteLabor(){
		clickXPath("//div[@realid='CallLaborGrid']//button[text()='Delete']");
		Reporter.log("delete labor was clicked <br>", true);
	}

	public void setLaborActivityDate(String reference) {
		Timer timer = new Timer().start();
		WebElement date = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@class", ACTIVITY_WINDOW_CLASS, "input", "@id", "date", true, true);
		date.clear();
		date.sendKeys(reference);
		Reporter.log("labor date was set" + " (" + timer.stop()
				+ "ms)", true);
	}
	
	
	public void setLaborActivityStartTime(String reference) {
		Timer timer = new Timer().start();
		WebElement code = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@class", ACTIVITY_WINDOW_CLASS, "input", "@name",
						"timeStart", true, true);
		code.clear();
		code.sendKeys(reference);
		Reporter.log("start time was set" + " (" + timer.stop()
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
		WebElement code = McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window')]//textarea[@name='notesText']|"
				+ "//div[contains(@class,'x-window')]//textarea[@name='activityNotes']");
		code.clear();
		code.sendKeys(reference);
		Reporter.log("activity note was set"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	public void clickLaborActivityGeneralTab(){
		clickXPath(ACTIVITY_GENERAL_TAB);
		Reporter.log("Activity general tab was clicked <br>", true);
	}	
	

	public void clickLaborActivityFinancialTab(){
		clickXPath(ACTIVITY_FINANCIAL_TAB);
		Reporter.log("Activity financial tab was clicked <br>", true);
	}	

	public void clickLaborActivityNotesTab(){
		clickXPath(ACTIVITY_NOTES_TAB);
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
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ACTIVITY_WINDOW_CLASS, "input", "@name",
				"statusCode", true, true).click();
		McsElement.getElementByPartAttributeValue(driver, "div", "@class",
				"x-combo-list", "text()", reference, true, true).click();
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
	}

	public void setLaborActivityWorkForce(String lastName) {
		clickLookup(ACTIVITY_WINDOW_CLASS, "workforce");
		setValueGridLookupWithFilters("@class", "x-window", lastName, "Last Name");
		waitForExtJSAjaxComplete(25);
	}

	public void setLaborActivityRelatedType(String reference) {
		String id = McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", ACTIVITY_WINDOW_CLASS, "input",
				"@name", "relatedType", true, true).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, reference);
		Reporter.log("related type was selected", true);
	}

	public void setLaborActivityCall(String reference) {

		clickLookup(ACTIVITY_WINDOW_CLASS, "relCall");
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

	public void cancelActivity() {
		 McsElement
			.getElementByPartAttributeValueAndParentElement(driver, "div",
					"@class", ACTIVITY_WINDOW_CLASS, "button", "text()",
					"Cancel", true, true).click();
		 Reporter.log("cancel was clicked", true);
	}
	
	
	public void setLaborProduct(String product){

         	clickLookup("x-window", "productId");
         	
         	waitForExtJSAjaxComplete(25);
         	
         	clickXPath("//div[contains(@class,'x-window-noborder')]//span[text()='Products And Services']");
         	
         	setValueGridLookupWithFilters("@class", "x-window-noborder", product, "Reference");
	}

	
	////////////////////////////////CONSUMPTION TAB METHODS///////////////////////////
	
	
	
	public void clickEditConsumption(){
		String xpath = " //div[contains(@class,'helpdesk')]//div[contains(@class,'x-panel x-border-panel')]//table[contains(@class,'x-btn-text-icon') and not (contains(@class, 'x-item-disabled'))]//button[text()='Edit']";
		
		WebElement ele = driver.findElement(By.xpath(xpath));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		ele.click();
		Reporter.log("edit consumption was clicked <br>", true);
	}

	public void clickDeleteConsumption(){
		String xpath = " //div[contains(@class,'helpdesk')]//div[contains(@class,'x-panel x-border-panel')]//table[contains(@class,'x-btn-text-icon') and not (contains(@class, 'x-item-disabled'))]//button[text()='Delete']";
		
		WebElement ele = driver.findElement(By.xpath(xpath));
			try{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			} catch(Exception e){
				e.printStackTrace();
			}
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			ele.click();
        	Reporter.log("delete consumption was clicked <br>", true);
	}

	
	
	public void clickAddConsumption(){
		String xpath = " //div[contains(@class,'helpdesk')]//div[contains(@class,'x-panel x-border-panel')]//table[contains(@class,'x-btn-text-icon') and not (contains(@class, 'x-item-disabled'))]//button[text()='Add']";
		
		WebElement ele = driver.findElement(By.xpath(xpath));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		ele.click();
		//McsElement.getElementByXpath(driver,"//div[contains(@class,'x-border-panel') and contains(@style,'visibility: visible')]//div[contains(@class,'hdwo-details')]//button[text()='Add']/../..").click();
		//McsElement.getElementByXpath(driver,"//div[contains(@class,'helpdesk')]//div[contains(@class,'hdwo-details')]//button[text()='Add']/../..").click();
		Reporter.log("add consumption was clicked <br>", true);
	}

	
	public void setConsumptionSelectProduct(String comboValue){
		String id = driver.findElement(By.xpath("//div//label[contains(text(),'Select Product')]/../../..//input")).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, comboValue);
	}
	
	
	public void setConsumptionProduct(String product){
		
	clickLookup("x-resizable",driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//label[text()='Product:']/..//input")).getAttribute("name"));
	
	waitForExtJSAjaxComplete(25);
	
	clickXPath("//div[contains(@class,'x-window-noborder')]//span[text()='Products And Services']");
	
	setValueGridLookupWithFilters("@class", "x-window-noborder", product, "Reference");
	}
	
	
	public void setConsumptionQuantity(String product){
         	clickXPath("//div[contains(@class,'x-resizable')]//label[text()='Quantity:']/..//input");
         	McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//label[text()='Quantity:']/..//input").clear();
         	McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//label[text()='Quantity:']/..//input").sendKeys(product);
         	clickXPath("//div[contains(@class,'x-resizable')]//label[text()='Quantity:']/..//input");
	}

	public void setConsumptionDate(String date){
        	clickXPath("//div[contains(@class,'x-resizable')]//label[text()='Date of Consumption:']/..//input");
        	McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//label[text()='Date of Consumption:']/..//input").clear();
        	McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//label[text()='Date of Consumption:']/..//input").sendKeys(date);
        	clickXPath("//div[contains(@class,'x-resizable')]//label[text()='Date of Consumption:']/..//input");
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
	
	public void saveCloseConsumption(){
        	clickXPath("//div[contains(@class,'x-resizable')]//button[text()='Save and Close']");
        	Reporter.log("save close consumption was clicked <br>", true);
	}
	
	public void saveConsumption(){
        	clickXPath("//span[contains(text(),'Edit Consumption')]//ancestor::div[contains(@class,'x-resizable')]//button[text()='Save']");
                Reporter.log("save consumption was clicked <br>", true);
	}
	
	public void openConsumptionDialog(String id) {
		WebElement cell = McsElement.getElementByXpath(driver, "//div[@realid='CallConsumptionGrid']//div[@class='x-grid3-cell-inner x-grid3-col-0' and text()='" + id + "']//..");
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
	
	public void clickCancelOnConsumptionDialog() {
		clickXPath("//div[contains(@class, 'x-resizable-pinned')]//table[contains(@class, 'x-toolbar-ct')]//button[text()='Cancel']");
	}
	
	public void verifySelectedCallsInTab(String callID, String callReference) {
		//WebElement iFrame = McsElement.getElementByXpath(driver, "//div[contains(@class,'helpdesk')]//div[contains(@class,'x-panel x-border-panel')]//iframe");
		//driver.switchTo().frame(iFrame);
		McsElement.getElementByXpath(driver, "//tr//td[contains(text(), '" + callID + "')]//..//..//td[contains(text(), '" + callReference + "')]");
		driver.switchTo().defaultContent();
		Reporter.log("Item with id and reference: " + callID + " : " + callReference + "found. <br>", true);
	}
	
	//////////////////////////////////MAINTENANCE OBJECT TAB METHODS///////////////////////////////////////////
	

	public void clickAddAsset(){
		clickXPath("//div[contains(@class,'x-tab-panel-bwrap')]//button[text()='Add Asset']");
		Reporter.log("add asset was clicked <br>", true);
	}

	public void clickRemoveAsset(){
		clickXPath("//div[contains(@class,'x-tab-panel-bwrap')]//button[text()='Remove']");
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

		String gridId= driver.findElement(By.xpath("//span[contains(text(),'Select Assets')]//ancestor::div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]//div[contains(@realid,'mogrid')]")).getAttribute("id");

		filterGrid("@id", gridId, reference, "Reference");
                waitForDialogMaskDisappear("//span[contains(text(),'Select Assets')]//ancestor::div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]");

		String columnClass = McsElement
		.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-resizable-pinned",
				"div","@class", "x-grid3-hd",
				"text()", "Reference", true, true).getAttribute("class");

		String columnNumber = columnClass.substring(columnClass.length() - 1);

		/*	McsElement
					.getElementByPartAttributeValueAndParentElement(driver,
							"div","@class", "x-resizable-pinned", "div",
							"@class", "x-grid3-cell-inner x-grid3-col-"+columnNumber,
							"text()", reference, true, true).click();    */
		clickXPath("//span[contains(text(),'Select Assets')]//ancestor::div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]//div[contains(@class,'x-grid3-cell-inner x-grid3-col-" + columnNumber + "') and text()='" + reference + "']");


		waitForExtJSAjaxComplete(25);
		clickXPath("//span[contains(text(),'Select Assets')]//ancestor::div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]//button[contains(@style,'add.png')]");
		waitForExtJSAjaxComplete(25);
		clickOkXwindow();
		Reporter.log(reference + " was selected", true);
		
	}
	
	public void clickAddMaintanceObject(){
		clickXPath("//div[contains(@class,'x-tab-panel-bwrap')]//button[text()='Add Maint. Obj.']");
		Reporter.log("add maintance object was clicked <br>", true);
	}


	public void clickDetailsMaintanceObject(){
		clickXPath("//div[contains(@class,'x-tab-panel-bwrap')]//button[text()='Details']");
		Reporter.log("details maintance object was clicked <br>", true);
	}

	
	public String getDetailsMaintanceObject(String detail){
		return McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-window-mc')]//label[text()='Reference']/..//label)[2]").getText();		
	}
	
	public void closeDetailsMaintanceObject(){
		clickXPath("//div[contains(@class,'x-window-mc')]//button[text()='Close']");
		Reporter.log("Close details maintance object was clicked <br>", true);
	}
	
	
	public void setMaintenanceObject(String reference) {

		McsElement.getElementByPartAttributeValueAndParentElement(driver,
			"div", "@class", "x-window x-window-noborder x-resizable-pinned", "input", "@class",
			"x-trigger-noedit", true, true).click();
	
         	waitForExtJSAjaxComplete(20);
         	McsElement.getElementByPartAttributeValueAndParentElement(driver,
         			"div", "@style", "visible", "div", "text()", "allMnObjects","@class","x-combo", true, true)
         			.click();
         	waitForExtJSAjaxComplete(20);
         	
         	String gridId= driver.findElement(By.xpath("//span[contains(text(),'Select Maintenance Objects')]//ancestor::div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]//div[contains(@realid,'mogrid')]")).getAttribute("id");
         	
         	filterGrid("@id", gridId, reference, "Reference");
                waitForDialogMaskDisappear("//span[contains(text(),'Select Maintenance Objects')]//ancestor::div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]");

         	String columnClass = McsElement
         	.getElementByPartAttributeValueAndParentElement(driver,
         			"div", "@class", "x-window x-window-noborder x-resizable-pinned",
         			"div","@class", "x-grid3-hd",
         			"text()", "Reference", true, true).getAttribute("class");

         	String columnNumber = columnClass.substring(columnClass.length() - 1);
         	waitForExtJSAjaxComplete(25);
		clickXPath("//span[contains(text(),'Select Maintenance Objects')]//ancestor::div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]//div[contains(@class,'x-grid3-cell-inner x-grid3-col-" + columnNumber + "') and text()='" + reference + "']");

		waitForExtJSAjaxComplete(25);
		clickXPath("//span[contains(text(),'Select Maintenance Objects')]//ancestor::div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]//button[contains(@style,'add.png')]");
		waitForExtJSAjaxComplete(25);
		clickOkXwindow();
		Reporter.log(reference + " was selected", true);
	
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
	
        	String gridId= driver.findElement(By.xpath("//span[contains(text(),'Select Maintenance Object Parts')]//ancestor::div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]//div[contains(@realid,'mogrid')]")).getAttribute("id");
	
        	filterGrid("@id", gridId, reference, "Reference");
                waitForDialogMaskDisappear("//span[contains(text(),'Select Maintenance Objects')]//ancestor::div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]");
        	String columnClass = McsElement.getLastElementByPartAttributeValueAndParentElement(driver,
			"div", "@class", "x-resizable-pinned",
			"div","@class", "x-grid3-hd",
			"text()", "Reference", true, true).getAttribute("class");
	
        	String columnNumber = columnClass.substring(columnClass.length() - 1);

/*		waitForExtJSAjaxComplete(20);
                new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[contains(text(),'Select Maintenance Object Parts')]//ancestor::div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]//div[contains(@class, 'ext-el-mask')]")));
		waitForExtJSAjaxComplete(20);   */


		clickXPath("//span[contains(text(),'Select Maintenance Object Parts')]//ancestor::div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]//div[contains(@class,'x-grid3-cell-inner x-grid3-col-" + columnNumber + "') and text()='" + reference + "']");
		waitForExtJSAjaxComplete(25);	
		clickXPath("//span[contains(text(),'Select Maintenance Object Parts')]//ancestor::div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]//button[contains(@style,'add.png')]");
		waitForExtJSAjaxComplete(25);
		clickOkXwindow();
		Reporter.log(reference + " was selected", true);
	
	}

	
	public void clickAddMaintanceObjectPart(){
		clickXPath("//div[contains(@class,'x-tab-panel-bwrap')]//button[text()='Add Maint. Obj Part']");
		Reporter.log("add maintance object was clicked <br>", true);
	}

	/////////////////////DESCRIPTION TAB METHODS///////////////////
	
	public void setDescription(String description ){
		clickXPath("//div[contains(@class,'x-tab-panel-bwrap')]//textarea");
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-tab-panel-bwrap')]//textarea").clear();
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-tab-panel-bwrap')]//textarea").sendKeys(description);
		Reporter.log("call description was set <br>", true);
	}

	public String getDescription(){
		return McsElement.getElementByXpath(driver, "//div[contains(@class,'x-tab-panel-bwrap')]//textarea").getAttribute("value");
	}

	public void clickSaveDescription(){
		clickXPath("//button[text()='Save']");
	}
	
	
/////////////////////TRACKING HISTORY TAB METHODS///////////////////
	
	public void clickAddAction(){
                clickXPath("//div[contains(@realid,'HistoryGrid')]//button[text()='Add Action']");
	}

	public void clickEditLastAction(){
		clickXPath("//div[contains(@realid,'HistoryGrid')]//button[text()='Edit Last Action']");
	}
	
	public String getActionType(){
	return driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//div[@name='actionResultStatus']")).getText();
	}
		
	public void saveCloseAction(){
	        clickXPath("//div[contains(@class,'x-resizable')]//button[contains(text(),'Action') and contains(text(),'Close')]");
	}
	
	public void clickPossibleActions(String Action){
        	clickXPath("//div[contains(@class,'x-resizable')]//em[contains(text(),'"+Action+"')]");
        	Reporter.log("action type was clicked <br>", true);
	}

	public void setActionAssigned(String name) {
		clickLookup("x-resizable", "assignee");
		setValueGridLookupWithFilters("@class", "x-window-noborder", name, "Last Name");
		waitForExtJSAjaxComplete(25);
	}
	
	public void setActionContact(String name) {
		clickLookup("x-resizable", "contact");
		setValueGridLookupWithFilters("@class", "x-window-noborder", name, "Last Name");
		waitForExtJSAjaxComplete(25);
	}

	public void setActionNote(String note){
		clickXPath("//div[contains(@class,'x-resizable')]//textarea[@name='actionNotes']");
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-resizable')]//textarea[@name='actionNotes']").clear();
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-resizable')]//textarea[@name='actionNotes']").sendKeys(note);
		Reporter.log("action note was set <br>", true);
	}

	public boolean isActionPresent(String note){
	return McsElement.isElementPresent(driver,"//div[contains(@class,'expanded')]//div[contains(@class,'row') and contains(text(),'"+note+"')]");
	}
	

	/**
	 * Helper method to click on tabs in Details window
	 * 
	 * @param tabText
	 *  text of tab to be clicked
	 */
	public void clickOnTab(String windowTitle,String tabText) {
		clickXPath( "//div[@id='"+ getXWindowId(windowTitle)+"']//span[contains(@class,'x-tab-strip-text') and contains(text(),'"+tabText+"')]");
	}

	/**
	 * Helper method to click on general tab
	 */
	public void clickGeneralTab(String windowTitle) {
		
		clickOnTab(windowTitle,"General");
	}

	/**
	 * Helper method to verify send notification check box state
	 * 
	 * @param fieldName
	 * @param name
	 * @return true or false
	 */
	
	public boolean isFieldDisplayedAsLabel(String fieldName, String name) {
	
		String xpath = DETAILS_WINDOW_CLASS+ "//label[contains(@class,'x-box-item') and text()='"+ fieldName+ "']/following-sibling::label[contains(@class,'x-box-item') and (contains(text(),'"+ name + "'))]";
	
		try {
	
			McsElement.getElementByXpath(driver, xpath);
	
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Helper method to close Channel Measurements using Tool Close (X button)
	 */
	
	public void closeUsingToolBar(String xwindowXpath) {
                clickXPath(xwindowXpath + "//div[contains(@class, 'x-tool-close')]");
		waitForExtJSAjaxComplete(20);
	}
	
	/**
	 * Helper method to click on Add Action button
	 */
	
	public void clickAddActionInDetails() {
                clickXPath(DETAILS_WINDOW_CLASS + "//button[text()='Add Action']");
	
	}
	
	/**
	 * Helper method to click on Add Action button for a call
	 */
	
	public void clickAddActionForCall() {
	       clickXPath(ADD_ACTION_WINDOW_CLASS+ "//button[text()='Add Action']");
	
	}
	
	/**
	 * Helper method to click check of send notification field
	 */
	
	public void checkSendNotification() {
	
		WebElement element = McsElement.getElementByXpath(driver,ADD_ACTION_WINDOW_CLASS+ "//label[contains(@class,'x-form-cb-label') and text()='Send Notification']/preceding-sibling::input");
	
		if (!element.isSelected()) {
	
			element.click();
		}
	
		Reporter.log("Send notification check box is checked <br>", true);
	}
	
	/**
	 * Helper method to click on send button
	 * 
	 */
	public void clickSendButton() {
                clickXPath(SEND_NOTIFICATION_WINDOW+ "//button[contains(@class, 'x-btn-text') and text()='Send']");
	}
	
	/**
	 * Helper method to click on maximize button
	 * 
	 */
	public void clickMaximizeButtonForSendNotificationWindow() {
                clickXPath(SEND_NOTIFICATION_WINDOW+ "//div[contains(@class,'x-tool-maximize')]");
	}
	
	/**
	 * Helper method to select sms template
	 * */
	public void selectSMSTemplateInNotification(String tempName) {
	
		String id = McsElement.getElementByXpath(driver,SEND_NOTIFICATION_WINDOW+"//input[@name='smsTemplateID']//..//input[last()]").getAttribute("id");

		DropDown.setComboValueDropDownSelector(driver, id, tempName);
	
		waitForExtJSAjaxComplete(25);
	
		Reporter.log("Set sms template to" + tempName, true);
	}
	
	/**
	 * Helper method to select language template
	 * */
	public void selectLanguageForSMSTemp(String name) {
	
		String id = McsElement.getElementByXpath(driver,SEND_NOTIFICATION_WINDOW+"//input[@name='smsLanguageCode']//..//input[last()]").getAttribute("id");
	
		waitForExtJSAjaxComplete(20);
		
		DropDown.setComboValueDropDownSelector(driver, id, name);
	
		waitForExtJSAjaxComplete(25);
	
		Reporter.log("Set sms template to" + name, true);
	}
	
	/**
	 * Helper method to select language template
	 * */
	public void selectLanguageForEmailTemp(String name) {
	
		String id = McsElement.getElementByXpath(driver,SEND_NOTIFICATION_WINDOW+"//input[@name='emailLanguageCode']//..//input[last()]").getAttribute("id");
	
		DropDown.setComboValueDropDownSelector(driver, id, name);
	
		waitForExtJSAjaxComplete(25);
	
		Reporter.log("Set email template to" + name, true);
	}
	
	
	/**
	 * Helper method to select email template
	 * */
	public void selectEmailTemplateInNotification(String tempName) {
	
		String id = McsElement.getElementByXpath(driver,SEND_NOTIFICATION_WINDOW+"//input[@name='emailTemplateID']//..//input[last()]").getAttribute("id");
	
		DropDown.setComboValueDropDownSelector(driver, id, tempName);
	
		waitForExtJSAjaxComplete(25);
	
		Reporter.log("Set email template to" + tempName, true);
	}
	
	/**
	 * Helper method to check sms notification field is disabled or not
	 */
	
	public boolean isSMSTemplateFieldDisabled() {
	
		String element = McsElement.getElementByXpath(driver,SEND_NOTIFICATION_WINDOW+ "//input[@name='smsTemplateID']/ancestor::div[contains(@class,'x-form-field-wrap')]").getAttribute("class");
	
		if (element.contains("x-item-disabled")) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Helper method to list out SMS Templates
	 * @param tempName
	 * @return true
	 */
	
	public List<String> getSMSTemplatesInTheList(String tempName) {
		String id = driver.findElement(By.xpath("//input[contains(@name,'smsTemplateID')]")).getAttribute("id");
	
		return DropDown.getComboValuesFromDropDownSelector(driver, id);
	
		}
	
	/**
	 * Helper method to drag and drop add action window
	 */
	public void dragAndDropActionWindows(String sourceWindowName, String targetWindowName)
	{
	
	WebElement source=driver.findElement(By.xpath("//span[contains(@class,'x-window-header-text') and contains(text(),'"+sourceWindowName+"')]/.."));
	
	WebElement target=driver.findElement(By.xpath("//span[contains(@class,'x-window-header-text') and contains(text(),'"+targetWindowName+"')]/.."));
	
	(new Actions(driver)).dragAndDrop(source, target).perform();
	
	}
	
	/**
	 *  Helper method to get column number in Grid 
	 */
	
	public String getGridColumnNumber(String columnName){
		
		String columnClass = McsElement.getElementByXpath(driver,XPATH_SEND_NOTIFICATION_LINES_GRID+"//div[text()='"+columnName+"']//ancestor::div[contains(@class, 'x-grid3-hd-inner')][last()]").getAttribute("class");
	
		String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);
			
		return columnNumber;
	}
	
	
	/**
	 * Helper method to set email address
	 * @param rowNum 
	 * @param columnName
	 * @param value
	 */
	public void setEmailAddressInNotification(String rowNum,String columnName,String value)
	{

              	/*String columnNumber = getGridColumnNumber(columnName);
                String xpath = XPATH_SEND_NOTIFICATION_LINES_GRID_BODY+"//div["+rowNum+"]//div[contains(@class,'x-grid3-cell-inner x-grid3-col-"+columnNumber+"')]";

		clickXPath(xpath);
		waitForExtJSAjaxComplete(5);
		clickXPath(xpath);
		waitForExtJSAjaxComplete(5);
		Reporter.log("inline edit", true);
                waitXPathVisible("//input[contains(@class, 'x-form-text x-form-field')]");
                //clickXPath("//input[contains(@class, 'x-form-text x-form-field')]");
		McsElement.getElementByXpath(driver, XPATH_SEND_NOTIFICATION_LINES_GRID+"//input[contains(@class, 'x-form-text x-form-field')]").clear();
		McsElement.getElementByXpath(driver, XPATH_SEND_NOTIFICATION_LINES_GRID+"//input[contains(@class, 'x-form-text x-form-field')]").sendKeys(value);
		//clickXPath("//input[contains(@class, 'x-form-text x-form-field')]");
*/
		Actions action = new Actions(driver);	
		
		String columnNumber = getGridColumnNumber(columnName);

		WebElement element = McsElement.getElementByXpath(driver,XPATH_SEND_NOTIFICATION_LINES_GRID_BODY+"//div["+rowNum+"]//div[contains(@class,'x-grid3-cell-inner x-grid3-col-"+columnNumber+"')]");
		 
		action.doubleClick(element).build().perform();
		
		Reporter.log("Double Clicked", true);
		
		action.doubleClick(element).sendKeys(value).build().perform();
		
		waitForExtJSAjaxComplete(10);
		
		Reporter.log("set email address<br>", true);
	        Reporter.log("set email address<br>", true);
	}
	
	/**
	 * Helper method to click check box for recipients
	 * @param rowNum
	 * @param columnName
	 */
	public void clickCheckBoxForRecipients(String rowNum,String columnName)
	{
		
		
		String columnNumber = getGridColumnNumber(columnName);
		
		WebElement element = McsElement.getElementByXpath(driver, XPATH_SEND_NOTIFICATION_LINES_GRID_BODY+"//div["+rowNum+"]//div[contains(@class,'x-grid3-cell-inner x-grid3-col-ext-"+columnNumber+"')]//div");
		element.click();
	
		Reporter.log("check box is selected<br>", true);
		
	}
	
	/**
	 * Helper method to click on maximize button
	 * 
	 */
	public void clickManimizeButtonForSendNotificationWindow(String windowTitle) {
		String xpath = "//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//div[contains(@class,' x-tool-restore')]";
	
		clickXPath(xpath);
	}
	
	/**
	 * Helper method to click on general tab
	 */
	public void clickTrackingTab(String windowTitle) {
		clickOnTab(windowTitle,"Tracking");
	}
	
	/**
	 * Helper method to click on Add Action button for a call
	 */
	
	public void clickAddActionAndCloseForCall(String windowTitle) {
		
		String xpath = "//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//button[text()='Add Action and Close']";
	
		clickXPath(xpath);
	
	}
	
	/**
	 * Helper method to verify add action window is closed or not
	 */
	
	public boolean isActionWindowClosed(String windowTitle) {
		
		String xpath = "//span[contains(@class,'x-window-header-text') and contains(text(),'"+windowTitle+"')]/ancestor::div[contains(@class,'x-resizable-pinned')]";
	try{
		McsElement.getElementByXpath(driver, xpath);
		
		return true;}
	catch (Exception e) {
		
		return false;
	}
	}
	
	/**
	 * Helper method to click on close button for a call
	 */
	
	public void clickCloseForCall(String windowTitle) {
		String xpath =  "//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//button[text()='Close']";
		clickXPath(xpath);
	}
	
	/**
	 * Helper method to get action note
	 */
	public String getActionNote(String windowTitle){
		
		return McsElement.getElementByXpath(driver, "//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//textarea[@name='actionNotes']").getAttribute("value");
		
	}
	
	/**
	 * Helper method to get action assignee
	 */
	
	public String getActionAssigned(String windowTitle) {
		
		return McsElement.getElementByXpath(driver, "//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//input[@name='assignee']/following-sibling::input[@type='text']").getAttribute("value");
	}
	
	/**
	 * Helper method to get action contact
	 */
	public String getActionContact(String windowTitle) {
		return McsElement.getElementByXpath(driver, "//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//input[@name='contact']/following-sibling::input[@type='text']").getAttribute("value");		
		
	}
	
	/**
	 * Helper method to click on save action button
	 */
	public void clickSaveAction(String windowTitle){
		clickXPath("//div[contains(@id,'"+getXWindowId(windowTitle)+"')]//button[text()='Save Action']");
		Reporter.log("Save action was clicked <br>", true);
	}
	
	/**
	 * Helper method to click on save action close button
	 */
	
	public void clickSaveActionClose(String windowTitle){
		clickXPath("//div[contains(@id,'"+getXWindowId(windowTitle)+"')]//button[text()='Save Action & Close']");
		Reporter.log("Save Action Close was clicked <br>", true);
	}
	
	/**
	 * Helper method to get tool tip text for call
	 * @return tool tip text
	 */
	public String getToolTipTextForActionButtons(String windowTitle,String value)

	{
		String xpath =  "//div[contains(@id,'"+getXWindowId(windowTitle)+"')]//button[text()='"+value+"']";
		
		clickXPath(xpath);
				
		this.mouseMove(xpath);
		
		String toolTipXpath="//div[contains(@class,'x-tip info-tooltip') and (contains(@style,'visibility: visible'))]//*[@class='x-tip-body']";
		
		return driver.findElement( By.xpath(toolTipXpath)).getText();
	}
	
	/**
	 * Helper method to click history details
	 */

	public void clickHistoryDetails(){
		clickXPath("//div[contains(@realid,'HistoryGrid')]//button[text()='History Details']");
		Reporter.log("History details was clicked <br>", true);
	}
	
	
	/**
	 * Helper method to get cell text
	 * @param rowNum
	 * @param columnClass
	 * @param windowTitle 
	 * @return text
	 */
	
	public String getCellText(String rowNum, String columnClass,String windowTitle)
	{
		String xpath="//div[@id='"+getXWindowId(windowTitle)+"']//div[contains(@class,'x-grid3-col-propName') and text()='Status']/ancestor::div[contains(@class,'x-panel-body-noheader')]//div[@class='x-grid3-body']//div["+rowNum+"]//td[contains(@class,'"+columnClass+"')]//div";
		
		return McsElement.getElementByXpath(driver, xpath).getText();
	
	}
	
	/**
	 * Helper method to Get call status
	 */
	public String getCallHistory(String windowTitle, String columnClass, String rowNum){
		return getCellText(rowNum , columnClass, windowTitle);
	}
	
	/**
	 * Helper method to get subject
	 * @param fieldName
	 * @param windowTitle
	 * @return value
	 */
	
	public String getSubjectInGeneralTab(String fieldName,String windowTitle) {
	
		return driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//div[contains(@class,'details-general')]//label[contains(@class,'x-form-item-label') and text()='"+fieldName+"']/..//input")).getAttribute("value");		
			
	}
	
	/**
	 * Helper method to get priority
	 * @param fieldName
	 * @param windowTitle
	 * @return value
	 */
	
	public String getPriorityInGeneralTab(String fieldName,String windowTitle) {
		
		return driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//div[contains(@class,'details-general')]//label[contains(@class,'x-form-item-label') and text()='"+fieldName+"']/..//input")).getAttribute("value");		
			
	}
	/**
	 * Helper method to get nature
	 * @param fieldName
	 * @param windowTitle
	 * @return value
	 */
	
	public String getNatureInGeneralTab(String fieldName,String windowTitle) {
	
	return driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//div[contains(@class,'details-general')]//label[contains(@class,'x-form-item-label') and text()='"+fieldName+"']/..//input[@name='nature']/following-sibling::input")).getAttribute("value");	
	
	}
	
	/**
	 * Helper method to get customer
	 * @param fieldName
	 * @param windowTitle
	 * @return value
	 */
	
	public String getCustomerInGeneralTab(String fieldName,String windowTitle) {
		
		return driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//div[contains(@class,'details-general')]//label[contains(@class,'x-form-item-label') and text()='"+fieldName+"']/..//input[@name='customer']/following-sibling::input")).getAttribute("value");	
		
		}
	
	/**
	 * Helper method to get caller
	 * @param fieldName
	 * @param windowTitle
	 * @return value
	 */
	
	public String getCallerInGeneralTab(String fieldName,String windowTitle) {
		
		return driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//div[contains(@class,'details-general')]//label[contains(@class,'x-form-item-label') and text()='"+fieldName+"']/..//input[@name='caller']/following-sibling::input")).getAttribute("value");	
		
		}
	
	/**
	 * Gets Maintenance object Field Value
	 * @param fieldName
	 * @param numb
	 * @return
	 */
	public String getMaintenanceObjFieldValue(String sectionName,String fieldName,int numb){
		
		try {
            WebElement element = McsElement.getElementByXpath(driver,"//div[contains(@id,'"+sectionName+"')]//div[contains(text(), '"+fieldName+"')]/ancestor::tr//td["+numb+"]//div");

			((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
         e.printStackTrace();
        }
		 return McsElement.getElementByXpath(driver,"//div[contains(@id,'"+sectionName+"')]//div[contains(text(), '"+fieldName+"')]/ancestor::tr//td["+numb+"]//div").getText();
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
	
	/**
	 * Helper method to click on SLA tab
	 */
	public void clickTrackingSLATab(){
		clickXPath(TRACKING_SLA_TAB_XPATH);
		Reporter.log("Tracking history tab was clicked <br>", true);
	}


	/**
	 * Helper method to get possible actions in add action window 
	 * @return actions list
	 */
	public List<String> getAllPossibleActions(){
		List<String> possibleActions = new ArrayList<String>();
		String xpath = "//div[contains(@class,'actions-dialog')]//div[contains(@class,'x-form-item') and contains(.,'Possible Actions:')]//dl";
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		for(WebElement element : elements){
			possibleActions.add(element.getText());
		}
		return possibleActions;
	}

	/**
	 * Helper method to get add action window label status
	 * @param windowTitle  
	 * @param fieldName label name 
	 * @return label/not
	 */
	public boolean isFieldDisabledInAddActionWin(String fieldName){
		String field_Xpath = "//div[contains(@class,'actions-dialog')]//div[@name='"+fieldName+"'  and contains(@class,'disabled')]";
		return  McsElement.isElementPresent(driver, field_Xpath);

	}

	public String verifyAddActionWindowLabelLookup(String labelName){
		String field_Xpath = "//div[contains(@class,'actions-dialog')]//div[contains(.,'"+labelName+"') and contains(@class,'x-form-item')]//button[contains(@class,'x-btn-text')]";
		return driver.findElement(By.xpath(field_Xpath)).getAttribute("Style");
	}

	/**
	 * Helper method to verify send notification check box
	 * @return true/false
	 */
	public boolean isSendNotificationCheckBoxPresent(){
		String xpath = ADD_ACTION_WINDOW_CLASS+ "//label[contains(@class,'x-form-cb-label') and text()='Send Notification']/preceding-sibling::input[@type='checkbox']";
		return McsElement.isElementPresent(driver, xpath);
	}

	/**
	 * Helper method to verify buttons in add action window
	 * @param buttonName in action window
	 * @return true/false
	 */
	public boolean verifyButtonsInAddActionWindow(String buttonName){
		String buttons_Xpath = ADD_ACTION_WINDOW_CLASS+ "//button[text()='"+buttonName+"']";
		return McsElement.isElementPresent(driver, buttons_Xpath);
	}

	/**
	 * Helper method to verify presence of date and time field 
	 * @param className eg: ux-datetime-date/ ux-datetime-time
	 * @return true/false
	 */
	public boolean verifyDateandTimeFieldsInAddActionWindow(String className){
		String date_Xpath = "//div[contains(.,'Date') and contains(@class,'x-form-item')]//td[@class='"+className+"']";
		return McsElement.isElementPresent(driver, date_Xpath);
	}

	/**
	 * Helper method to get resulting status label value
	 * @param windowTitle eg: add action window
	 * @return value
	 */
	public String getResultingStatusInAddActionWin(){
		return McsElement.getElementByXpath(driver, "//div[contains(@class,'actions-dialog')]//div[@name='actionResultStatus']").getText();
	}

	/**
	 * Helper method to set action time in action window 
	 * @param windowTitle eg: add action window 
	 * @param priority : required time to set
	 */
	public void setActionTime( String priority){
		String id = McsElement.getElementByXpath(driver, "//div[contains(@class,'actions-dialog')]//td[contains(@class,'ux-datetime-time')]//input").getAttribute("id");
		DropDown.setComboValue(driver, id, priority);
	}	

	/**
	 * Helper method to set date in action window
	 * @param windowTitle eg: add action window
	 * @param priority date to set
	 */
	public void setActionDate(String priority){
		WebElement date= McsElement.getElementByXpath(driver, "//div[contains(@class,'actions-dialog')]//td[contains(@class,'ux-datetime-date')]//input");
		date.click();
		date.clear();
		date.sendKeys(priority);
		date.sendKeys(Keys.ENTER);
		clickXPath("//div[contains(@class,'actions-dialog')]//td[contains(@class,'ux-datetime-date')]//input");
	}	

	/**
	 * Helper method to get warning message of warning window 
	 * @return warning message
	 */
	public String getAddActionWinWarningMsg(){
		String Msg_Xpath = "//div[contains(@class,'x-window-dlg') and contains(@style,'visibility: visible')]//div[@class='ext-mb-content']";
		String warningMsg = driver.findElement(By.xpath(Msg_Xpath)).getText();
		return warningMsg;
	}

	/**
	 * Helper method to click button on warning window
	 */
	public void clickWarningWinButton(){
		driver.findElement(By.xpath("//div[contains(@class,'x-window-dlg') and contains(@style,'visibility: visible')]//button[contains(@class,'x-btn-text') and text()='OK']")).click();
	}

	public String getFieldValuesInGeneralTab(String fieldName, String name,String windowTitle){

		String fieldXpath = "//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//div[contains(@class,'details-general')]//label[contains(@class,'x-form-item-label') and text()='"+fieldName+"']/..//input[@name='"+name+"']/following-sibling::input";
		return driver.findElement(By.xpath(fieldXpath)).getAttribute("value");
	}
	
	 public void expandModuleSettings() {

		  waitForExtJSAjaxComplete(10);
		  expandNode("div","@id",getXPanelId("Administration"),"Module Settings");
	 }

	 public void expandHelpDesk() {
		 expandSubNode("div","@id",getXPanelId("Administration"),"HelpDesk");
	 }

	 /**
	  * Helper method to click tab in WO window, WO linked to call
	  * @param woRef WO Reference
	  * @param tabName to be clicked
	  */
	 public void clickTabInWOWinLinkedToCall(String woRef,String tabName ){
		 String xpath = "//div[starts-with(text(),'Work Order') and contains(text(),'"+woRef+"')]//ancestor::div[contains(@class,'hdwo-details')]//span[text()='"+tabName+"']";
		 driver.findElement(By.xpath(xpath)).click();
	 }

	 /**
	  * Helper method to close WO window, WO linked to call
	  * @param woRef WO Reference
	  */
	 public void closeXWindowOfWOWinLinkedToCall(String woRef){
		 String xpath = "//div[starts-with(text(),'Work Order') and contains(text(),'"+woRef+"')]//ancestor::div[contains(@class,'x-resizable-pinned')]//div[contains(@class,'x-tool-close')]";
		 driver.findElement(By.xpath(xpath)).click();
	 }
	 
	 /**
		 * Helper method to click WO tab
		 */

		public void clickWorkOrdersTab(){
			clickXPath(WORKORDERS_TAB_XPATH);
			Reporter.log("Work Orders tab was clicked <br>", true);
		}	

		/**
		 * Helper method to Whether assigned to value added to the Action or not
		 */
		public boolean isWOAssignedToAssignee(String ref,String assigneeName)
		{
			int columNum =Integer.parseInt(getGridColumnNumber("Reference"));
			int columNo =Integer.parseInt(getGridColumnNumber("Assignee"));


			String xpath=DETAILS_WINDOW_CLASS+"//div[contains(@realid,'mogrid')]//div[contains(@class,'x-grid3-row-first')]//div[contains(@class,'x-grid3-cell-inner x-grid3-col-"+columNo+"') and contains(text(),'"+assigneeName+"') and //div[contains(@class,'x-grid3-cell-inner x-grid3-col-"+columNum+"') and contains(text(),'"+ref+"')]]";

			return McsElement.getElementByXpath(driver, xpath).isDisplayed();

		}
}
