package test.generalweb.scheduler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import test.configuration.Configuration;
import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;


public class SchedulerPageObject extends AbstractMcsTestSuite {
	
	
	protected final String SCHEDULER_WIN_CLASS = "x-panel scheduler";
	
	protected final String SCHEDULER_WIN_XPATH = "//div[contains(@class,'x-panel scheduler')]";
	
	protected final String SCHEDULER_DATE_RANGE_PICKER_XPATH = "//input[@name='dateRangePicker']";
	
	protected final String SCHEDULER_DATE_ICON_XPATH = "//input[@name='dateRangePicker']/following-sibling::img";
	
	protected final String SCHEDULER_ACTIONS_XPATH= "//div[@class='x-panel-bbar']//button[text()='Actions']";
	
	protected final String SCHEDULER_OPTIONS_XPATH= "//td[contains(@class,'x-toolbar-cell')]//button[contains(@class, 'scheduler-icon-user-options')]";
	
	protected final String SCHEDULER_GOTO_XPATH = "//div[contains(@id,'datefield-') and contains(@id, 'inputWrap')]/input";
	
	protected final String SCHEDULER_RESOURCE_GRID = "//div[starts-with(@id,'scheduler') and contains(@id,'-locked-body')]";
	
	protected final String ADMINISTRATION_XPATH = "//div[contains(@class, 'admgroups')]";
	
	protected final String WORKORDER_WINDOW_DETAIL = "x-resizable";
	
	protected final String WORKORDER_WINDOW_XPATH = "//div[contains(@class,'x-resizable-pinned') and contains(@style, 'visibility: visible')]";
	
	protected final String SLA_WINDOW_HEADER = "SLA Information";
	
	protected final String SLA_GOOD_INDICATOR_ICON_XPATH = "/../..//td[contains(@class, 'sla-indicator-title') and text()='Good']//../td[contains(@class, 'column-sla-indicator-good')]";
	
	protected final String SLA_BAD_INDICATOR_ICON_XPATH = "/../..//td[contains(@class, 'sla-indicator-title') and text()='Bad']//../td[contains(@class, 'column-sla-indicator-bad')]";
	
	protected final String USER_OPTIONS_WINDOW_HEADER = "User options";
	
	protected final String SELECT_A_COLOR_WINDOW_HEADER = "Select a color";
	
	protected final String SELECT_COLUMNS_WINDOW_HEADER = "Select columns";
	
	protected final String TASK_LABEL_XPATH = "//div[contains(@class, 'x-panel-body x-border-panel')]//span[contains(text(), 'Task label')]";
	
	protected final String DATE_PICKER_XPATH = "//div[contains(@class, 'x-date-picker')]";
	
	protected final String FOOTER_BTN_CLASS_NAME = "x-plain-footer";
	
	protected final String FROM_DATE_CLASS_NAME = "x-panel x-column";
	
	protected final String TO_DATE_CLASS_NAME = " x-panel x-column";
	
	protected final String SCHEDULER_REFRESH_XPATH = "//button[contains(@class, 'x-tbar-loading')]";
	
	protected final String CUSTOM_DATE_XPATH = "//div[contains(@class, 'x-menu-floating')]//span[contains(text(), 'Custom Range')]";
	
	protected final String FROM_TO_CALENDAR_XPATH = "//div[contains(@class, 'x-column-inner')]";
	
	protected final String START_PAGE_MODULE_XPATH = "//a[contains(@onclick, 'ActivateModule') and contains(text(), 'Scheduler')]";
	
	protected final String GO_START_PAGE_XPATH = "/following-sibling::ul//a[contains(text(), 'Go')]";
	
	protected final String SCHEDULER_HEADER_XPATH = "//div[contains(@class, 'scheduler-header-text') and contains(text(), 'Scheduler')]";
	
	protected final String WORKORDERS_HEADER_XPATH = "//label[contains(text(), 'Workorders')]";
	
	protected final String CHANGE_VISIBLE_COLUMNS_WINDOW_HEADER = "Change visible columns";
	
	protected final String PAGE_TITLE_WINDOW_HEADER = "Print";
	
	protected final String PAGE_TITLE_XPATH = "//div[contains(@class, 'x-window-plain') and contains(@style, 'visibility: visible')]";
	
	protected final String PAGE_TITLE_PRINT_XPATH = PAGE_TITLE_XPATH+"//button[contains(text(), 'Print')]";
	
	protected final String ADD_ACTION_FOR_WO_HEADER = "Add Action for Work Order";
	
	protected final String EXPORT_TO_EXCEL_HEADER = "Export to Excel";
	
	protected final String EXPORT_TO_EXCEL_DOWNLOAD_XPATH = "//button[contains(text(), 'Download')]";
	
	protected final String PRINT_BTN_SLA_INFO_XPATH = "//div[contains(@realid, 'WOSLAGrid') and contains(@style, 'left')]//button[contains(@style,'/htmlbridge/images/toolprint.png')]";
	
	protected final String EXPORT_BTN_SLA_INFO_XPATH = "//div[contains(@realid, 'WOSLAGrid') and contains(@style, 'left')]//button[contains(@style,'/htmlbridge/images/toolexcel.png')]";
	
	protected final String ZOOM_LEVELS_XPATH = "//div[contains(@class, 'x-panel-body x-border-panel')]//span[contains(text(), 'Zoom levels')]";
	
	protected final String ZOOM_LEVEL_BUTTON_XPATH = ZOOM_LEVELS_XPATH+"/ancestor::div[contains(@class, 'details-general')]";
	
	protected final String ZOOM_LEVEL_WINDOW_HEADER = "Add zoom level";
	
	protected final String ADD_ACTION_WINDOW_HEADER = "Add Action";
	
	protected final String SLA_TOOL_TIP_XPATH = "//div[contains(@class, 'x-tip sla-tooltip') and contains(@style, 'visibility: visible;')]";
	
	protected final String scheduler_StartMenu_Xpath = "//div[@id='itemscontainer']//a[text()='Scheduler']";
	
	protected final String wo_StartMenu_Xpath = "//div[@id='itemscontainer']//a[text()='Work Order']";
	
	protected final String wo_Boards_Xpath = "//button[contains(@class,'icon-board') and text()='Boards']";
	
	/**
	 * Helper method to Select a Service Group in Scheduler Main page
	 * @param serviceGroup to select
	 * @param column used for selection
	 */
	public void setServiceGroup(String serviceGroup, String column){
		
		clickLookup("@class",SCHEDULER_WIN_CLASS, "serviceGroup", "Select a Service Group");
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Service Group"), serviceGroup, column);
			
		Reporter.log(serviceGroup+" Service Group was selected", true);
		
		
	}
	
	/**
	 * Helper method to select Service Organization in Scheduler Main page
	 * @param serviceOrganization to select
	 * @param column used for selection
	 */
	public void setServiceOrganization(String serviceOrganization, String column){
		
		clickLookup("@class",SCHEDULER_WIN_CLASS, "serviceOrganization", "Select a Service Organization");
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Service Organization"), serviceOrganization, column);
			
		Reporter.log(serviceOrganization+" Service Organization was selected", true);
		
		
	}
	
	/**
	 * Helper method to  Select a Site in Scheduler Main page
	 * @param site to select
	 * @param column used for selection
	 */
	public void setSite(String site, String column){
		
		clickLookup("@class",SCHEDULER_WIN_CLASS, "site", "Select a Site");
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Site"), site, column);
			
		Reporter.log(site+" Site was selected", true);


	}

	/**
	 * Helper method to select planner in Scheduler Main page
	 * @param person to select
	 * @param tab in which selection has to be done. By default its Employees tab
	 * @param column used for selection
	 * 
	 */
	public void setPlanner(String person,  String tab, String column){
		
		clickLookup("@class",SCHEDULER_WIN_CLASS, "planner", "Select a Person");
		
		if(tab.isEmpty()||tab==null){
		
			tab = "Employees";
		}
		
		this.clickTabInLookUpWindow("@id", getXWindowId("Select a Person"), tab);
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Person"), person, column);
			
		Reporter.log(person+" person was selected", true);
		
		
	}
	
	/**
	 * Helper method to click on Date Icon
	 */
	public void clickDateIconScheduler(){
		String xpath = SCHEDULER_WIN_XPATH+SCHEDULER_DATE_ICON_XPATH;
		
		//click on Date icon
		McsElement.getElementByXpath(driver, xpath).click();
		waitForExtJSAjaxComplete(5);
		
		//Click on Custom Range
		McsElement.getElementByXpath(driver, CUSTOM_DATE_XPATH).click();
		waitForExtJSAjaxComplete(5);
	}
	
	/**
	 * Helper method to set Custom Date range using calendar button
	 * @param dateRange menu to select
	 */
	public void selectCustomMonthYearUsingCalendars(String rowNum, String month, String year, String date) throws Exception{	
		
		//Click From Month Year Button
		McsElement.getElementByXpath(driver, FROM_TO_CALENDAR_XPATH+"/div["+rowNum+"]//div[contains(@class, 'x-date-picker')]//td[contains(@class, 'x-date-middle')]//button").click();
		Thread.sleep(5000);
		
		//Select Month
		WebElement month1 = driver.findElement(By.xpath(FROM_TO_CALENDAR_XPATH+"/div["+rowNum+"]//div[contains(@class, 'x-date-mp')]//td[contains(@class, 'x-date-mp-month')]//..//a[contains(text(), '"+month+"')]"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", month1);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", month1);
		}catch(Exception e){
			e.printStackTrace();
		}
		Reporter.log("Month clicked", true);
		Thread.sleep(5000);
		
		//Select Year
		WebElement year1 = driver.findElement(By.xpath(FROM_TO_CALENDAR_XPATH+"/div["+rowNum+"]//div[contains(@class, 'x-date-mp')]//td[contains(@class, 'x-date-mp-year')]//..//a[contains(text(), '"+year+"')]"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", year1);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", year1);
		}catch(Exception e){
			e.printStackTrace();
		}
		Reporter.log("Year clicked", true);
		Thread.sleep(5000);
		
		//Click OK Button
		WebElement ok = driver.findElement(By.xpath(FROM_TO_CALENDAR_XPATH+"/div["+rowNum+"]//button[contains(@class, 'x-date-mp-ok') and contains(text(), 'OK')]"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ok);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ok);
		}catch(Exception e){
			e.printStackTrace();
		}
		Reporter.log("OK clicked", true);
		Thread.sleep(5000);
		
	}
	
	/**
	 * Helper method to set Date Via Calendar Button
	 */
	public void setCustomDateViaCalendarBtn(String rowNum, String month, String year, String date) throws Exception{
		
		//Select Month Year
		selectCustomMonthYearUsingCalendars(rowNum, month, year, date);
		waitForExtJSAjaxComplete(25);
		
		//Select Date
		McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-column-inner')]/div["+rowNum+"]//div[contains(@class, 'x-date-picker')]//td[contains(@class, 'x-date-active')]//span[contains(text(), '"+date+"')]").click();
		waitForExtJSAjaxComplete(5);
	}
	
	
	/**
	 * Helper method to verify Dates are disabled in TO Calendar
	 */
	public boolean verifyDatesDiabledInToCalendar(String rowNum, int frmDat, int toDt) throws Exception {
		boolean status = false;
		
		for(int i=frmDat; i<=toDt; i++){
			status = driver.findElement(By.xpath("//div[contains(@class, 'x-column-inner')]/div["+rowNum+"]//span[contains(text(), '"+i+"')]/ancestor::td[contains(@class, 'x-date-disabled')]")).isDisplayed();
			
			if(status){
				Reporter.log("Date "+i+" is diabled in TO calendar ", true);
			} else{
				break;
			}
		}
		
		return status;
	}
	
	/**
	 * Helper method to verify From and To Calendars Exist
	 */
	public boolean verifyFromToCalendars(String rowNum) {
		return McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-column-inner')]/div["+rowNum+"]//div[contains(@class, 'x-date-picker')]//td[contains(@class, 'x-date-middle')]//button").isDisplayed();
	}
	
	/**
	 * Helper method to click Button
	 */
	public void clickButtonUsingClassName(String className, String buttonName){
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class, 'x-menu-plain') and contains(@style, 'visibility: visible')]//div[contains(@class, '"+className+"')]//button[contains(@class, 'x-btn-text') and text()='"+buttonName+"']"));
		if(ele.isDisplayed())
		ele.click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Click "+buttonName+" Button is clicked", true);
	}
	
	/**
	 * Helper method to set Date range in Date range text field
	 * @param dateRange to be entered
	 */
	public void setCustomDateRange(String dateRangeFromTo){
		
		String xpath = SCHEDULER_WIN_XPATH+SCHEDULER_DATE_RANGE_PICKER_XPATH;
		//driver.findElement(By.xpath(xpath)).sendKeys(dateRangeFromTo);
		WebElement ele = driver.findElement(By.xpath(xpath));
		ele.click();
		ele.clear();
		ele.sendKeys(dateRangeFromTo);
		ele.sendKeys(Keys.ENTER);
		Reporter.log("Custom date Range is set", true);
	}
	
	/**
	 * Helper method to get Custom date range
	 */
	public String getCustomDateRange(){
		String xpath = SCHEDULER_WIN_XPATH+SCHEDULER_DATE_RANGE_PICKER_XPATH;
		WebElement ele = driver.findElement(By.xpath(xpath));
		String value = ele.getAttribute("value");
		return value;
	}
	
	/**
	 * Helper method to select an Action from Actions drop
	 * @param action to be selected
	 */
	public void selectActions(String action){
		
		String xpath = SCHEDULER_WIN_XPATH+"//div[contains(@class,'btn-group-body')]//button[text()='"+action+"']";
		driver.findElement(By.xpath(xpath)).click();
		//String actionsButtonID= driver.findElement(By.xpath(xpath)).getAttribute("id");
		//DropDown.selectDropDownMenuItemOfButton(driver, actionsButtonID, action);
		
	}
	
	/**
	 * Helper method to select an Options from options menu
	 * @param action
	 */
	public void selectOptions(){
		
		String xpath = SCHEDULER_WIN_XPATH+SCHEDULER_OPTIONS_XPATH;
		driver.findElement(By.xpath(xpath)).click();
		waitForExtJSAjaxComplete(25);
		//DropDown.selectDropDownMenuItemOfButton(driver, actionsButtonID, action);
		Reporter.log("Options button is clicked ", true);
	}
	
	/**
	 * Helper method to set Date in Go to field.
	 * @param date to set
	 */
	public void setGoToDateUsingCalendarIcon(String date){
		
		String xpath = SCHEDULER_WIN_XPATH+SCHEDULER_GOTO_XPATH;
		
		driver.findElement(By.xpath(xpath)).click();
		
		
		
		
	}
	
	/**
	 * Helper method to select a Resource for Scheduler Tree
	 * @param serviceGrp : service group of resource
	 * @param taskforce	: task force of resource
	 * @param resource : name of resource
	 */
	public boolean selectAResource(String serviceGrp, String taskforce, String resource){
		try{
		
		String serviceGrpXpath = SCHEDULER_RESOURCE_GRID+"//div[contains(@class,'icon-service-group')]/following-sibling::span[contains(text(),'"+serviceGrp+"')]/ancestor::tr";
		
		WebElement serviceGrpRow =  driver.findElement(By.xpath(serviceGrpXpath));
		
		String serviceGrpRowClass= serviceGrpRow.getAttribute("class");
		
		//TR containing service Group will have 'grid-tree-node-expanded' in class when its in expanded mode
		if(!serviceGrpRowClass.contains("grid-tree-node-expanded")){
		
			//if service group isnt expanded, expand it by clicking on Expander icon
			serviceGrpRow.findElement(By.xpath(".//div[contains(@class,'tree-expander')]")).click();
			
		}	

		String taskForceXpath = SCHEDULER_RESOURCE_GRID+"//div[contains(@class,'icon-taskforce')]/following-sibling::span[contains(text(), '"+taskforce+"')]/ancestor::tr";
		
		WebElement taskForceRow =  driver.findElement(By.xpath(serviceGrpXpath));
		
		String taskForceRowClass= serviceGrpRow.getAttribute("class");
		
		//TR containing taskForce will have 'grid-tree-node-expanded' in class when its in expanded mode
		if(!taskForceRowClass.contains("grid-tree-node-expanded")){
		
			//if taskForce isnt expanded, expand it by clicking on Expander icon
			taskForceRow.findElement(By.xpath(".//div[contains(@class,'tree-expander')]")).click();
			
		}	

		String resrcXpath = SCHEDULER_RESOURCE_GRID+"//div[contains(@class,'icon-employee')]/following-sibling::span[contains(text(), '"+resource+"')]";
		
		//McsElement.getElementByXpath(driver, resrcXpath).click();
		WebElement ele = driver.findElement(By.xpath(resrcXpath));
			try{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
			} catch(Exception e){
				e.printStackTrace();
				return false;
			}
		
			return true;
		}
		catch(Exception E){
			return false;
		}
	}
	
	/**
	 * Helper method to click on Administration
	 */
	public void clickAdministration() {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "mcs-tb-glossy-strong", "button", "text()",
				"Administration", true, true).click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click Administration"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	/**
	 * Helper method to expand Accounts
	 */
	public void expandNotifications() {
		expandNode("div","@id",getXPanelId("Administration"),"Accounts");
		}
	
	/**
	 * Helper method to click on Accounts Options Administration
	 */
	public void clickAdministrationOptions(String parentNode, String childNode) {
		WebElement node =McsElement.getElementByXpath(driver,"//div[@id='"+getXPanelId("Administration")+"']//span[text()='"+parentNode+"']/../../..//span[text()='"+childNode+"']");
		javaScriptFocus(node);
		node.click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Clicked on Node "+childNode, true);
	}
	
	/**
	 * Helper method to select Account Groups in Administration
	 */
	public void clickOnAccountGroups(String accGrpName){
		WebElement ele = McsElement.getElementByXpath(driver, ADMINISTRATION_XPATH+"//span[contains(text(), 'Account Groups')]//../following-sibling::div//em[contains(text(), '"+accGrpName+"')]");
		ele.click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Clicked on Account Group Options", true);
	}
	
	/**
	 * Helper method to check and uncheck Scheduler Option
	 */
	public void checkScheduler(String optionName){
		WebElement ele = McsElement.getElementByXpath(driver, ADMINISTRATION_XPATH+"//form[contains(@class, 'x-panel-body x-panel-body-noheader')]//span[contains(text(), '"+optionName+"')]/ancestor::div[contains(@class, 'x-tree-node-leaf')]//input");
		String classValue = ele.getAttribute("checked");
		if(classValue.contains("true")){
			ele.click();
			waitForExtJSAjaxComplete(5);
			ele.click();
			waitForExtJSAjaxComplete(5);
		} else{
			ele.click();
			waitForExtJSAjaxComplete(5);
		}
		
	}

	/**
	 * Helper method to click save changes 
	 */
	public void clickSaveChangesAdministration()
	{
		String xpath= ADMINISTRATION_XPATH+"//button[text()='Save Changes']";
		WebElement ele = driver.findElement(By.xpath(xpath));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Changes Clicked", true);
	}
	
	/**
	 * Helper method to navigate to Administration and turn scheduler on
	 * parentNode = Accounts
	 * childNode = Groups
	 * accGrpName = automation group
	 * optionName = Scheduler
	 */
	public void navigateToAdministrationAndEnableScheduler(String parentNode, String childNode, String accGrpName, String optionName) {
		
		clickAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		expandNotifications();
		
		waitForExtJSAjaxComplete(5);
		
		clickAdministrationOptions(parentNode, childNode);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnAccountGroups(accGrpName);
		
		waitForExtJSAjaxComplete(20);
		
		checkScheduler(optionName);
		
		waitForExtJSAjaxComplete(20);
		
		clickSaveChangesAdministration();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Reporter.log("Scheduler Is Enabled in Administration", true);
	}
	
	/**
	 * Helper method to verify Workorder and call Metrics are displayed
	 */
	public boolean verifyMetricsHeaderInSLAInformation(String realID, String textName){
		
		String xpath = "//div[contains(@realid, '"+realID+"')]//span[contains(text(), '"+textName+"')]";
		
		return McsElement.getElementByXpath(driver, xpath).isDisplayed();
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
	
	/**
	 * Helper method to Get Grid Column No
	 */
	public boolean isRowInGridPresentLike(String realID, int no, String value){
		
		try {
			driver.findElement(By.xpath("//div[contains(@realid, '"+realID+"')]//tr//div[contains(@class, 'col-"+no+"') and text()='"+value+"']"));
		} catch (Exception e) {
			Reporter.log("row can not be found in grid: ",true);
			return false;
		}
		Reporter.log("Check element "+value+" present in grid and click", true);
		return true;
	}
	
	/**
	 * Helper method to verify if Column names are present in the window
	 */
	public boolean verifyColNamesInSLAInfoWindow(String realID, String[] prodArray){
		boolean status = false;
		
		for(int i=0; i<prodArray.length; i++){
			status = driver.findElement(By.xpath("//div[contains(@class, 'x-resizable-pinned') and contains(@style, 'visibility: visible')]//div[contains(@realid, '"+realID+"') and contains(@style, 'left:')]//div[contains(text(), '"+prodArray[i]+"')]")).isDisplayed();
			Reporter.log("Column "+prodArray[i]+" is present in Window", true);
		}
		return status;
	}
	
	/**
	 * Helper method to verify all the fields are Read only
	 * mcsgridrow_FF0000
	 */
	public boolean verifyAllSLAFieldsAreReadOnly(String realID, String className, String[] prodArray, String[] value) {
		int colNo = 0;
		boolean status = false;
		
		for(int i=0; i<prodArray.length; i++){
			
			colNo = getGridColumnNumberWithoutQuickFilters("@realid", realID, prodArray[i]);
			Reporter.log("Column No for "+prodArray[i]+" is "+colNo, true);
			status = McsElement.getElementByXpath(driver, "//div[contains(@realid, '"+realID+"') and contains(@style, 'left:')]//div[contains(@class, '"+className+"')]//tr//div[contains(@class, 'col-"+colNo+"') and contains(text(),'"+value[i]+"')]").isDisplayed();
			if(status){
				Reporter.log("The field value "+value[i]+" is read only in SLA Information Grid", true);
			} else{
				break;
			}
		}
		return status;
	}
	
	/**
	 * Helper method to verify Size of the grid
	 */
	public int getSchedulerGridSize() {
		List<WebElement> ele = driver.findElements(By.xpath(SCHEDULER_WIN_XPATH+"//div[contains(@class, 'x-grid3-body')]//div[contains(@class,'mcsgridrow')]"));
		
		int size = ele.size();
		
		return size;
	}
	
	/**
	 * Helper method to double click on SLA Indicator
	 */
	public void doubleClickOnSLAIndicatorIcon(String woID, String xpath){
		String newXpath = SCHEDULER_WIN_XPATH+"//div[@class='x-grid3-body']//tr//div[text()='"+woID+"']"+xpath;
		
		WebElement ele = driver.findElement(By.xpath(newXpath));
		waitForExtJSAjaxComplete(2);
		new Actions(driver).doubleClick(ele).build().perform();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Double clicked on SLA Indicator Icon", true);
	}
	
	/**
	 * Helper method to click on task box colors
	 */
	public void clickTaskBoxColors(String labelName) {
		WebElement ele = driver.findElement(By.xpath("//label[contains(text(), '"+labelName+"')]/ancestor::tr//td[last()]//..//input"));
		ele.click();
		waitForExtJSAjaxComplete(5);
		Reporter.log("Task Box Colors Window is clicked", true);
	}
	
	/**
	 * Helper method to getValue of Task box colors 
	 */
	public String getTaskBoxColors(String labelName){
		WebElement ele = driver.findElement(By.xpath("//label[contains(text(), '"+labelName+"')]/ancestor::tr//td[last()]//..//input"));
		String colorValue = ele.getAttribute("value");
		Reporter.log("Available Task Box Color For label "+labelName+" is "+colorValue, true);
		return colorValue;
	}
	
	/**
	 * Helper method to mouse over any color
	 */
	public String mouseHoverColor(String colorCode){
		String xpath = "//div[contains(@class, 'slnmClrPckr')]//div[contains(@style, '"+colorCode+"')]";
		
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.xpath(xpath));
		action.moveToElement(we).build().perform();
		
		//String colorCodeValue = we.getAttribute("data-color");
		String colorCodeValue =getColorCodeFromBottomOfPopUp();
		Reporter.log("Color code is "+colorCodeValue, true);
		
		return colorCodeValue;
	}
	
	/**
	 * Helper method to get Color code from Bottom of the pop up
	 */
	public String getColorCodeFromBottomOfPopUp(){
		String xpath = "//div[contains(@class, 'slnmClrPckr')]//td[contains(@class, 'hexcode')]//span";
		return McsElement.getElementByXpath(driver, xpath).getAttribute("textContent");
	}
	
	/**
	 * Helper method to click on Anly color
	 */
	public void clickOnAnyColor(String colorCode){
		String xpath = "//div[contains(@class, 'slnmClrPckr')]//div[contains(@style, '"+colorCode+"')]";
		McsElement.getElementByXpath(driver, xpath).click();
		Reporter.log("Color code "+colorCode+" is clicked", true);
	}
	
	/**
	 * Helper method to check colors are retained for fields that doesnt undergo any change
	 */
	public void checkColorsRetainedForTaskColors(String initialColorArr[], String labelName[]){
		
		for(int i=0; i<=initialColorArr.length-1; i++){
			String actualColor = getTaskBoxColors(labelName[i]);
			
			Assert.assertEquals(initialColorArr[i], actualColor, "Same Color is retained for "+labelName[i]+" ");
			waitForExtJSAjaxComplete(5);
		}
	}
	
	/**
	 * Helper method to click Button
	 */
	public void clickButton(String windowHeader, String buttonName){
		WebElement ele = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(windowHeader)+"')]//button[contains(@class, 'x-btn-text') and text()='"+buttonName+"']"));
		ele.click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Click "+buttonName+" Button is clicked", true);
	}
	
	/**
	 * Helper method to expand Module Settings
	 */
	public void expandModuleSettings() {
		expandNode("div","@id",getXPanelId("Administration"),"Module Settings");
		}

	/**
	 * Helper method to click on Modify Button in Task Label Scheduler
	 */
	public void clickModifyButtonTaskLabel() {
		String xpath = TASK_LABEL_XPATH+"/ancestor::div[contains(@class, 'x-panel details-general')]//button[contains(text(), 'Modify')]";
		McsElement.getElementByXpath(driver, xpath).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Clicked on Modify Button ", true);
	}
	
	/**
	 * Helper method to get verify New Purchase Order Window Title
	 */
	public String getWindowTitleCustomized(String attribute, String attributeValue){
		WebElement windowTitle = McsElement.getElementByXpath(driver, "(//div[contains("+attribute+",'" + attributeValue + "') and contains(@style, 'visibility: visible;')]//span[contains(@class, 'x-window-header-text')])[last()]");
		
		return windowTitle.getText();
	}
	
	/**
	 * Helper method to Select Columns
	 * @param attributeHD Example: gp-group-Maintenance-hd (Maintenance Header)
	 * @param attributeBD Example: group-Maintenance-bd (Maintenance Body)
	 * @param attributeName Example: gp-group-Maintenance(Maintenance)
	 * @param fieldValue Example: Compliance Level
	 */
	public void expandColumns(String xwindowTitle, String attributeHD, String attributeName){
		//Expand Properties
		String xpath ="//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//div[contains(@id, '"+attributeHD+"')]/ancestor::div[contains(@id, '"+attributeName+"')]";
		
		WebElement ele = driver.findElement(By.xpath(xpath));
		
		try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		String element = ele.getAttribute("class");
		if(element.contains("x-grid-group-collapsed")) {
			McsElement.getElementByXpath(driver, xpath+"//div[@class='x-grid-group-title']").click();
		}
		
		waitForExtJSAjaxComplete(10);
		Reporter.log("The Tree is opened", true);
	}
	
	 /**
	  * Helper method to verify All Columns are available
	  */
	public boolean verifyAllColumnNames(String fieldValue[], String attributeName){
		boolean status = false;
		
		for(int i=0; i<=fieldValue.length-1; i++){
			status = driver.findElement(By.xpath("//div[contains(@class, 'overview-properties-grid')]//div[contains(text(), '"+fieldValue[i]+"')]")).isDisplayed();
			Reporter.log("The User Can select value "+fieldValue[i]+" from "+attributeName+" ", true);
		}
		return status;
	}
	
	
	/**
	 * Helper method to check columns in Select Columns
	 */
	public String getColumnStatus(String xwindowTitle, String attributeBD, String fieldValue){
		//Check Properties
		String elementToBeCheckedClassValue = driver
				.findElement(
						By.xpath("//div[contains(@id,'"
								+ getXWindowId(xwindowTitle)
								+ "') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-grid3-body')]//div[contains(@id, '"+attributeBD+"') and contains(@class, 'group-body')]//div[contains(@class, 'x-grid3-col-1')and text()='"
								+ fieldValue
								+ "']/ancestor::div[contains(@class, 'x-grid3-row')]"))
				.getAttribute("class");
		
		return elementToBeCheckedClassValue;
	}
	
	/**
	 * Helper method to check Required columns
	 */
	public void checkColumns(String xwindowTitle, String attributeBD, String fieldValue){
		String status = "";
		status = getColumnStatus(SELECT_COLUMNS_WINDOW_HEADER, attributeBD, fieldValue);
		WebElement elementToBeChecked = driver.findElement(By.xpath("//div[contains(@id,'"+getXWindowId(xwindowTitle)+"') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-grid3-body')]//div[contains(@id, '"+attributeBD+"') and contains(@class, 'group-body')]//div[contains(@class, 'x-grid3-col-1')and text()='"+fieldValue+"']"));
		
		if(!status.contains("row-selected")){
			Reporter.log(fieldValue+" is Not checked", true);
			elementToBeChecked.click();
		}
		Reporter.log("Columns are checked ", true);
	}
	
	/**
	 * Helper method to Uncheck All Columns
	 */
	public void unCheckColumns(String xwindowTitle, String attributeBD, String colArr[]){
		String status = "";
		
		for(int i=0; i<=colArr.length-1; i++){
			status = getColumnStatus(SELECT_COLUMNS_WINDOW_HEADER, attributeBD, colArr[i]);
			WebElement elementToBeChecked = driver.findElement(By.xpath("//div[contains(@id,'"+getXWindowId(xwindowTitle)+"') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-grid3-body')]//div[contains(@id, '"+attributeBD+"') and contains(@class, 'group-body')]//div[contains(@class, 'x-grid3-col-1')and text()='"+colArr[i]+"']"));
			
			if(status.contains("row-selected")){
				Reporter.log(colArr[i]+" is already checked", true);
				elementToBeChecked.click();
			}
		}
		Reporter.log("Element is Unchecked", true);
	}
	
	/**
	 * Helper method to verify Added column is available in the Task Label Grid
	 */
	public boolean verifyColumnsInTaskLabelGrid(String xpathPart, String columnName){
		String xpath = xpathPart+"/ancestor::div[contains(@class, 'x-panel details-general')]//div[text()= '"+columnName+"']";
		return McsElement.isElementDisplayed(driver, xpath);
	}
	
	/**
	 * Helper method to get Grid size of Task labels in Administration
	 */
	public int getGridSizeTaskLabelAdministration(){
		List<WebElement> ele = driver.findElements(By.xpath("//div[contains(@class,'admsettings-modulesettings')]//span[contains(text(), 'Task label')]/ancestor::div[contains(@class, 'x-panel details-general')]//div[contains(@class, 'x-grid3-body')]//div[contains(@class,'x-grid3-row')]"));
		
		int size = ele.size();
		
		return size;
	}
	
	/**
	 * Helper method to verify Property Field is checked or not
	 * */
	public void selectAllFieldsOfAGroupInSelectCols(String windowTitle, String groupName) {
		WebElement reference = driver.findElement(By.xpath("//div[contains(@id,'"+ getXWindowId(windowTitle)+"') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-grid3-body')]//div[contains(@id, '"+groupName+"')]//div[contains(@class, 'grid-group-title')]//..//input"));
		waitForExtJSAjaxComplete(10);
		
		System.err.println(reference.isSelected());
		
		if(reference.isSelected()){
			reference.click();
			Reporter.log("General Header is Unchecked");
			waitForExtJSAjaxComplete(5);
			reference.click();
			Reporter.log("General Header is Checked");
		} else {
			reference.click();
			Reporter.log("General Header is Checked");
			waitForExtJSAjaxComplete(5);
		}
		
		Reporter.log("Field Property Header is Checked ", true);
	}
	
	/**
	 * Helper method to verify Scroll Bar is available in the Task Label Grid
	 */
	public boolean verifyScrollBarInTaskLabelGrid() {
		String xpath = "//div[contains(@class,'admsettings-modulesettings')]//span[contains(text(), 'Task label')]/ancestor::div[contains(@class, 'x-panel details-general')]//div[contains(@class, 'x-grid3-scroller')]";
		String scrollHeight = McsElement.getElementByXpath(driver, xpath).getAttribute("scrollHeight");
		System.err.println("scrollHeight is " +scrollHeight);
		String clientHeight = McsElement.getElementByXpath(driver, xpath).getAttribute("clientHeight");
		System.err.println("clientHeight is " +clientHeight);
		if(Integer.parseInt(scrollHeight) > Integer.parseInt(clientHeight)){
			return true;
		} else{
			return false;
		}
	}
	
	/**
	 * Helper method to return alignment of text in Warning dialog
	 * @return alignment of text in Warning dialogs
	 */
	public String getWarningDialogText(){
		
		WebElement element = driver.findElement(By.xpath("//div[contains(@class, 'x-window-dlg')]//span[@class='ext-mb-text']"));
		
		return element.getText();
		
	}
	
	/**
	 * Helper method to click on Refresh Button
	 */
	public void selectRefreshButton(){

		String xpath = SCHEDULER_WIN_XPATH+SCHEDULER_REFRESH_XPATH;

		List<WebElement> buttonsList = driver.findElements(By.xpath(xpath));
		//((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", buttonsList.get(1));
		//	driver.findElement(By.xpath(xpath)).click();
		try{buttonsList.get(1).click();}
		catch(Exception e){
			buttonsList.get(0).click();
		}
		waitForExtJSAjaxComplete(25);
		Reporter.log("Refresh button is clicked ", true);
	}

	/**
	 * Helper method to get Calendar Year +1
	 */
	public int getCurrentYear(String systemDate) {
	    Calendar cal = Calendar.getInstance();
	    System.out.println("year " +cal.get(Calendar.YEAR));
	   return cal.get(Calendar.YEAR);
	}
	
	/**
	 * Helper method to Zoom in the Scheduler
	 * @return
	 */
	public boolean ZoomIn(){
		/*String xpathZoomIn = SCHEDULER_WIN_XPATH+"//span[contains(@class,'icon-zoom-in')]//ancestor::a[contains(@class,'x6-btn zoomButton x6-unselectable')]"; 
		String xpathZoomOut = SCHEDULER_WIN_XPATH+"//span[contains(@class,'icon-zoom-out')]//ancestor::a[contains(@class,'x6-btn zoomButton x6-unselectable')]";
		 */
		String xpathZoomIn = SCHEDULER_WIN_XPATH+"//span[contains(@class,'icon-zoom')]//ancestor::a[contains(@class,'x6-btn zoomButton x6-unselectable') and (@title='Zoom in')]"; 
		String xpathZoomOut = SCHEDULER_WIN_XPATH+"//span[contains(@class,'icon-zoom')]//ancestor::a[contains(@class,'x6-btn zoomButton x6-unselectable') and (@title='Zoom out')]";
		
		if(!McsElement.isElementPresent(driver, xpathZoomOut)){

			clickXPath(xpathZoomIn);
		}

		return true;
	}


	/**
	 * Helper method to Zoom Out the Scheduler 
	 * @return
	 */
	public boolean ZoomOut(){
		/*String xpathZoomIn = SCHEDULER_WIN_XPATH+"//span[contains(@class,'icon-zoom-in')]//ancestor::a[contains(@class,'x6-btn zoomButton x6-unselectable')]"; 
		String xpathZoomOut = SCHEDULER_WIN_XPATH+"//span[contains(@class,'icon-zoom-out')]//ancestor::a[contains(@class,'x6-btn zoomButton x6-unselectable')]";*/
		String xpathZoomIn = SCHEDULER_WIN_XPATH+"//span[contains(@class,'icon-zoom')]//ancestor::a[contains(@class,'x6-btn zoomButton x6-unselectable') and (@title='Zoom in')]"; 
		String xpathZoomOut = SCHEDULER_WIN_XPATH+"//span[contains(@class,'icon-zoom')]//ancestor::a[contains(@class,'x6-btn zoomButton x6-unselectable') and (@title='Zoom out')]";

		if(!McsElement.isElementPresent(driver, xpathZoomIn)){

			clickXPath(xpathZoomOut);
		}

		return true;
	}


	/**
	 * Helper method to get the header context of scheduler 
	 * @return
	 */
	public String getTimeLineHeader(){
		String xpath = SCHEDULER_WIN_XPATH+"//div[@class='sch-horizontaltimeaxis-ct']//td[contains(@class,'sch-column-header schedulerview-header-bold')]/div";
		String headerText= driver.findElement(By.xpath(xpath)).getText();
		return headerText;
	}


	/**
	 * Helper method to get the formated date
	 * @param date to be formated
	 * @return
	 * @throws Exception
	 */
	public String getFormatedDate(String date) throws Exception{
		SimpleDateFormat newDateFormat = new SimpleDateFormat("dd-MM-yyyy",Locale.ENGLISH);
		Date myDate1 = newDateFormat.parse(date);
		newDateFormat.applyPattern("EEEE dd, MMMM yyyy");
		return  newDateFormat.format(myDate1);
	}

	/**
	 * Helper method to get the Zoom Button status
	 * @return
	 */
	public String getZoomButtonStatus(){
		/*String xpath = SCHEDULER_WIN_XPATH+"//a[contains(@class, 'zoomButton')]//span[@data-ref='btnIconEl']"; 
		String classOfZoomButton= driver.findElement(By.xpath(xpath)).getAttribute("class");
		return (classOfZoomButton.contains("icon-zoom-in")? "icon-zoom-in":"icon-zoom-out");*/

		String xpath = SCHEDULER_WIN_XPATH+"//a[contains(@class, 'zoomButton')]"; 
		String classOfZoomButton= driver.findElement(By.xpath(xpath)).getAttribute("title");
		return (classOfZoomButton.contains("Zoom in")? "icon-zoom-in":"icon-zoom-out");
	}


	/**
	 * Helper method to get whether the scheduler is in hour mode or week mode
	 * @param rowLevel middle/bottom
	 * @return
	 */
	public String getZoomMode(String rowLevel){
		String xpath = "//div[contains(@class,'x-panel scheduler')]//div[@class='sch-horizontaltimeaxis-ct']//table[contains(@class,'sch-header-row-"+rowLevel+"')]//td";

		List<WebElement> elements = driver.findElements(By.xpath(xpath));

		int size = elements.size();

		return (size==24)? "hours":(size==7? "week":"");
	}

	/**
	 * Helper method to expand parent node in Service Organization
	 * @param ParentnodeText : Parent Reference 
	 * @return expanded status
	 */
	public boolean expandParentNodeInTreeByTextValue(String parentnodeText){
		String expanded = "//div[contains(., '"+parentnodeText+"') and contains(@class,'x-tree-node-el')]//img[contains(@class,'x-tree-elbow-minus')]";
		String collpased = "//div[contains(., '"+parentnodeText+"') and contains(@class,'x-tree-node-el')]//img[contains(@class,'x-tree-elbow-plus')]";

		if(McsElement.isElementPresent(driver, collpased)){

			clickXPath(collpased);
			Reporter.log("Node "+parentnodeText+" is expanded", true);

		}
		else{if(McsElement.isElementPresent(driver, expanded)){
			Reporter.log("Node "+parentnodeText+" is already expanded", true);
		}
		}

		return true;
	}

	/**
	 * Helper method to expand the child nodes in service Organization
	 * @param childnodeText : child reference
	 * @return expanded status
	 */
	public boolean expandChildNodeInTreeByTextValue(String childnodeText){
		String expanded = "//div[contains(., '"+childnodeText+"') and contains(@class,'x-tree-node-el')]//img[contains(@class,'x-tree-elbow-end-minus')]";
		String collpased = "//div[contains(., '"+childnodeText+"') and contains(@class,'x-tree-node-el')]//img[contains(@class,'x-tree-elbow-end-plus')]";

		if(McsElement.isElementPresent(driver, collpased)){

			clickXPath(collpased);
			Reporter.log("Node "+childnodeText+" is expanded", true);

		}
		else{if(McsElement.isElementPresent(driver, expanded)){
			Reporter.log("Node "+childnodeText+" is already expanded", true);
		}
		}

		return true;
	}

	/**
	 * Helper method to get List of work resources linked to SO
	 * @return
	 */
	public List<String> getMembersOfTaskForce(){
		List<WebElement> webElement = driver.findElements(By.xpath("//div[contains(@class,'x-grid-with-col-lines x-panel-noborder x-grid-panel')]//div[@class='x-grid3-body']/div//td[contains(@class,'x-grid3-cell-first')]"));
		List<String> workResources = new ArrayList<String>();
		for(WebElement ele : webElement){
			waitForExtJSAjaxComplete(5);
			workResources.add(ele.getText());
		}
		return workResources;
	}

	/**
	 * Helper method to verify Size of the grid
	 */
	public int getGridSize() {
		List<WebElement> ele = driver.findElements(By.xpath("//div[contains(@class, 'x-grid3-viewport')]//div[contains(@class, 'x-grid3-body')]/div[contains(@class,'x-grid3-row')]"));
		int size = ele.size();
		return size;
	}

	/**
	 * Helper method to click on the navigation buttons
	 * @param rowNumb Example: ◄(previous/next); ◄◄(first/last) 
	 * @param silbling Example: preceding-sibling/following-sibling 
	 */
	public void clickOnNavigationsButtons(String button, String silbling){
		String buttons[] = {"first","previous","next","last"}; 
		String sibling = null;
		List<String> buttonNames = Arrays.asList(buttons); 
		int rowNumb=0;
		switch(buttonNames.indexOf(button)){

		case 0: {  sibling = "preceding-sibling"; rowNumb = 2; break;
		}

		case 1: { sibling = "preceding-sibling"; rowNumb = 1; break;
		}

		case 2: { sibling = "following-sibling"; rowNumb = 1; break;
		}

		case 3: { sibling = "following-sibling"; rowNumb = 2; break;
		}

		}



		String xpath = "//div[contains(.,'Go to:') and contains(@class,'x6-toolbar-text-default')]/"+sibling+"::a["+rowNumb+"]";
		driver.findElement(By.xpath(xpath)).click();

	}

	/**
	 * Helper method to check the Navigation Buttons
	 * @return
	 */
	public boolean isEverynavigationButtonPresent(){
		try{
			for(int rowNumb=1; rowNumb>=2;rowNumb++){

				String xpathFwrd = "//div[contains(.,'Go to:') and contains(@class,'x6-toolbar-text-default')]/following-sibling::a["+rowNumb+"]";
				String xpathBck = "//div[contains(.,'Go to:') and contains(@class,'x6-toolbar-text-default')]/preceding-sibling::a["+rowNumb+"]";

				McsElement.isElementPresent(driver,xpathFwrd);
				McsElement.isElementPresent(driver,xpathBck);
			}
			return true;
		}

		catch(Exception e){
			return false;
		}
	}

	/**
	 * Helper method to get GOTO field Date
	 * @return
	 */
	public String getGoToDate(){
		String xpath = SCHEDULER_WIN_XPATH+SCHEDULER_GOTO_XPATH;
		String date = driver.findElement(By.xpath(xpath)).getAttribute("value");
		return date;
	}


	/**
	 * Helper method to disable working hours button 
	 */
	public void disableWorkingHoursButton(){
		String enabledXpath = "//div[contains(@class,'x-panel scheduler')]//a[contains(@class,'workingHoursButton') and contains(@class,'x6-btn-pressed')]";
		String disabledXpath = "//div[contains(@class,'x-panel scheduler')]//a[contains(@class,'workingHoursButton') and not(contains(@class,'x6-btn-pressed'))]";
		if(!McsElement.isElementPresent(driver,disabledXpath)){
			clickXPath(enabledXpath);
			Reporter.log("24/7 button is dissabled",true);
		}else
		Reporter.log("24/7 button is already disabled",true);
	}

	/**
	 * Helper method to expand or collapse the Information Panel
	 * @param mode: collapse/expand
	 */
	public void clickOnInformationPanel(String mode){
		String infoModeExpand = "//div[contains(@class,'x6-tool-expand-left')]//ancestor::div[(contains(@class,'x6-region-collapsed-placeholder')) and not(contains(@class,'x6-hidden-offsets'))]"; 
		String infoModeCollapse = "//div[contains(@class,'information-panel') and not(contains(@style,'visibility: hidden'))]//div[contains(@class,'x6-tool-collapse-right')]";
		if(mode=="expand")
			driver.findElement(By.xpath(infoModeExpand)).click();
		else
			driver.findElement(By.xpath(infoModeCollapse)).click();

	}

	/**
	 * Helper method to get the Information panel mode based on class name
	 * @return whether it is in collapsed/expanded mode
	 */
	public String getInformationPanelMode(){
		String infoModeExpand = "//div[contains(@class,'x6-tool-expand-left')]//ancestor::div[(contains(@class,'x6-region-collapsed-placeholder')) and not(contains(@class,'x6-hidden-offsets'))]";
		String infoModeCollapse = "//div[contains(@class,'information-panel') and not(contains(@style,'visibility: hidden'))]//div[contains(@class,'x6-tool-collapse-right')]";

		return McsElement.isElementPresent(driver,infoModeExpand)? "collapsed" : (McsElement.isElementPresent(driver,infoModeCollapse)? "expanded":"");

	}

	/**
	 * Helper method to get Information Panel Label Values
	 * @param labelName : Example- First name:
	 * @param xpath: eg - td or td//ul/li[1]
	 * @return the label value
	 */
	public String getInfoPanelLabelValue(String labelName, String xpath){
		String labelValue = driver.findElement(By.xpath("//div[contains(@id,'mcs_scheduler_information_panel')]//td[text()='"+labelName+"']//following-sibling::"+xpath+"")).getText();
		return labelValue;
	}

	/**
	 * Helper method to click on resource in scheduler
	 * @param resourceName to be clicked : example - contact/ taskforce / service-group
	 * @param resourceValue to be clicked 
	 */
	public void clickOnResourceTreeInScheduler(String resourceName, String resourceValue){
		driver.findElement(By.xpath("//tr[contains(@class,'"+resourceName+"')]//span[contains(text(),'"+resourceValue+"')]/ancestor::div[contains(@class,'grid-cell-inner')]")).click();
	}

	/**
	 * Helper method to get field values of Service Organization 
	 * @param header : form header - example: Service Organization/Service Group
	 * @param field to get the value from 
	 * @param fieldType : input/ textarea
	 * @return field value
	 */
	public String getServOrgFieldVal(String header, String field,String fieldType){
		String xpath = "//span[contains(text(), '"+header+"') and contains(@class,'x-panel')]//..//..//div[contains(@class, 'x-form-label-left') and not (contains(@class, 'x-hide-display'))]//"+fieldType+"[contains(@name, '"+field+"')]";
		String reference = driver.findElement(By.xpath(xpath)).getAttribute("value");
		return reference;

	}

	/**
	 * Helper method to get contact row ID 
	 * @param contact to get id
	 * @return ID 
	 */
	public String getIDOfContact(String contact){
		String id = driver.findElement(By.xpath("//span[contains(text(), '"+contact+"')]/ancestor::table")).getAttribute("id");
		String lastWord = id.substring(id.lastIndexOf("-")+1);
		return lastWord;
	}

	/**
	 * Helper method to click on Scheduled Task 
	 * @param contact to which the task is scheduled 
	 * @param schedulerTask name
	 */
	public void clickOnScheduledTask(String contact,String schedulerTask){
		String schedXpath = "//table[contains(@id,'scheduler') and contains(@id,'"+getIDOfContact(contact)+"')]//div[contains(@class, 'grid-cell-inner')]//div[contains(text(),'"+schedulerTask+"')]";
	driver.findElement(By.xpath(schedXpath)).click();
	Reporter.log("Scheduled task "+schedulerTask+" is clicked",true);
	}
	
	/**
	 * Helper method to click on Day Block
	 * @param rowLevel : bottom/top 
	 */
	public void clickOnDayBlock(String rowLevel){
		String xpath = "//div[contains(@class,'x-panel scheduler')]//div[@class='sch-horizontaltimeaxis-ct']//table[contains(@class,'sch-header-row-"+rowLevel+"')]//td";
		//driver.findElement(By.xpath(xpath)).click();
		List<WebElement> ele = driver.findElements(By.xpath(xpath));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele.get(0));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele.get(0));
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Helper method to get Reference from Info Panel
	 * @return reference value
	 */
	public String getInfoPanelheaderRef(String rownumb){
		String reference = driver.findElement(By.xpath("//div[contains(@id,'container')]//div[contains(@id,'mcs_scheduler_information_panel')]//p["+rownumb+"]")).getText();
		return reference;
	}
	
	/**
	 * Navigate to home page and relogin
	 */
	public void navigateToMainPage(String NAME_FOR_RIGHT,String PASSWORD_FOR_RIGHT) {
		Timer timer = new Timer().start();
	//	forceLogout();
		logout();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		login(NAME_FOR_RIGHT, PASSWORD_FOR_RIGHT);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Login to back-end under account "+NAME_FOR_RIGHT +" END\n", true);
		Reporter.log("<br />");
		Reporter.log("Logged in to the Portal"+ " (" + timer.stop() + "ms)\n", true);
		Reporter.log("<br />");
	}
	
	/**
	 *  Helper method to switch to Iframe
	 */
	public void switchToSchedulerIframe(){
		WebElement iFrame= driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iFrame);
		Reporter.log("Switched to Iframe", true);
		waitForExtJSAjaxComplete(5);
	}
	
	/**
	 * Helper method to switch back to Parent Frame
	 */
	public void switchBackToDefaultSchedulerFrame(){
		driver.switchTo().defaultContent();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Switched to Default Content", true);
	}
	
	/**
	 * Helper method to find Scheduler and Go Texts are available & Visible in Start Page
	 */
	public boolean verifySchedulerTextsInStartPage(String xpath) {
		switchToSchedulerIframe();
		
		boolean status = McsElement.isElementPresent(driver, xpath);
		if(status){
			status = McsElement.isElementDisplayed(driver, xpath);
		}
				
		switchBackToDefaultSchedulerFrame();
		return status;
		
	}
	
	/**
	 * Helper method to verify Workorders and Scheduler Label is present in Scheduler Page
	 */
	public boolean verifyHeaderTextInScheduler(String elementXpath){
		return McsElement.getElementByXpath(driver, elementXpath).isDisplayed();
	}
	
	/**
	 * Helper method to verify height and width of Scheduler Icon
	 */
	public String getSchedulerIconSize(String xpath, String attribute) {
		switchToSchedulerIframe();
		waitForExtJSAjaxComplete(5);
		
		WebElement ele = driver.findElement(By.xpath(xpath+"/ancestor::table//td[@class='image']//..//img"));
		System.err.println("Size : "+ele.getSize());
		System.err.println("Attribute : "+attribute+":  "+ele.getAttribute(attribute));
		String value = ele.getAttribute(attribute);
		
		switchBackToDefaultSchedulerFrame();
		return value;
	}

	/**
	 * Helper method to verify Buttons in Scheduler Tab
	 */
	public boolean verifyButtonsScheduler(String elementXpath){
		return McsElement.getElementByXpath(driver, elementXpath).isDisplayed();
	}

	/**
	 * Helper method to verify Refresh Buttons in Scheduler Tab
	 */
	public boolean verifyRefreshButtonScheduler(String elementXpath){
		//return McsElement.getElementByXpath(driver, elementXpath).isDisplayed();
		List<WebElement> buttonsList = driver.findElements(By.xpath(elementXpath));
		try{return( buttonsList.get(1).isDisplayed()||buttonsList.get(0).isDisplayed());
		}
		catch(Exception e){
			return	false;
		}
	}

	/**
	 * Helper method to verify Buttons in Scheduler Tab
	 */
	public boolean verifyActionButtonsSchedulerBasedOnText(String attrName[]){
		/*String xpath = SCHEDULER_WIN_XPATH+SCHEDULER_ACTIONS_XPATH;
		driver.findElement(By.xpath(xpath)).click();*/
		boolean status = false;

		for(int i=0; i<=attrName.length-1; i++) {
			status = driver.findElement(By.xpath("//td[contains(@class,'x-toolbar-cell')]//button[text()='"+attrName[i]+"']")).isDisplayed();

			if(status){
				Reporter.log("Action Button "+attrName+" is present in scheduler window", true);
			} else{
				break;
			}
		}
		return status;
	}
	
	/**
	 * Helper method to verify Field in Page Title
	 */
	public boolean verifyPageTitleFields(String inputName){
		String elementXpath = PAGE_TITLE_XPATH+"//input[contains(@name, '"+inputName+"')]";
		return McsElement.getElementByXpath(driver, elementXpath).isDisplayed();
	}
	
	/**
	 * Helper method to uncheck WO in Scheduler Grid
	 */
	public void unCheckGridRowByText(String woID){
		String xpath = "//div[text()='"+woID+"']/ancestor::div[contains(@class, 'x-grid3-row-selected')]//tr//div[contains(@class, 'x-grid3-col-checker')]";
		McsElement.getElementByXpath(driver, xpath).click();
		Reporter.log("Row is unchecked ", true);
	}
	
	
	/**
	 * Helper method to sort SLA Indicator Evaluation and get Class NAme
	 */
	public String verifySLAIndicatorSortedInGrid(String colName) {
		String xpathEle = "//div[contains(@class, 'x-grid3-viewport')]//div[contains(@class,'quickfilters')]//div[contains(@class,'x-grid3-hd') and contains(text(), '"+colName+"')]";
		McsElement.getElementByXpath(driver, xpathEle).click();
		waitForExtJSAjaxComplete(3);
		
		String sortValue = driver.findElement(By.xpath(xpathEle+"/ancestor::td")).getAttribute("class");
		Reporter.log("sorting Value is "+sortValue);
		
		return sortValue;
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
	
	/**
	 * Helper method to verify Rows are sorted in Grid
	 */
	public boolean verifyRowsAreSortedBasedOnSLAIndicatorInfo(String attribute, String attributeValue, String columnName){
		
		String sortValue = verifySLAIndicatorSortedInGrid(columnName);
		
		//get Col No
		int colNo = getGridColumnNumberWithQuickFilters(attribute, attributeValue, columnName);
		
		waitForExtJSAjaxComplete(10);
		
		List<WebElement> listEle = driver.findElements(By.xpath("//*[@class='x-grid3']//div[contains(@class, 'x-grid3-col-"+colNo+"')]"));
		ArrayList<String> lsValues = new ArrayList<String>(); 
		
		for(int i=0;i<listEle.size(); i++){
			
			String valueText = listEle.get(i).getText().substring(0, 5).trim();
			
			lsValues.add(valueText);
		}
		
		if(sortValue.contains("sort-asc")){
			for (int i = 0; i < lsValues.toArray().length-1; i++) {
			    if (Double.valueOf(lsValues.get(i)) >  Double.valueOf(lsValues.get(i+1))){
			        return false;
			    }
			}
			Reporter.log("Rows are sorted in Ascending Order", true);
			return true;
		} else {
			for (int i = 0; i < lsValues.toArray().length-1; i++) {
			    if (Double.valueOf(lsValues.get(i)) <  Double.valueOf(lsValues.get(i+1))){
			        return false;
			    }
			}
			Reporter.log("Rows are sorted in Descending Order", true);
			return true;
		}
	}
	
	public void filterGridCustomized(String attribute, String attributeValue, String rowTextName, String columnName) {
		Timer timer = new Timer().start();
		
		
		String columnClass = driver.findElement(By.xpath("//div[contains("+attribute+",'"+ attributeValue+"')]//div[contains(@class,'quickfilters')]"
						+ "//div[contains(@class,'x-grid3-hd') and contains(text(),'"+columnName+"')][last()]")).getAttribute("class");
		
		String columnNumber = (columnClass.substring(columnClass.length() - 3)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 3) : columnClass.substring(columnClass.length() - 1);
		System.out.println(columnNumber);
		WebElement filterInput = driver.findElement(By.xpath("//div[contains("+attribute+",'"+attributeValue+"')]//"
				+ "input[contains(@id,'filter-editor-"+columnNumber+"')][last()]")); 
					
		filterInput.clear();
			
		filterInput.sendKeys(rowTextName);
		
		waitForExtJSAjaxComplete(25);
		filterInput.sendKeys(Keys.RETURN);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		
		/*driver.findElement(By.xpath("(//div[contains("+attribute+",'"+attributeValue+"')]//"
				+ "div[contains(@class,'x-grid3-body')])[last()]/div")).click(); 
*/
		waitForExtJSAjaxComplete(5);

		Reporter.log(rowTextName + " was filtered"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	/**
	 * Helper method to collapse all the Resources In Scheduler 
	 * @param resourceName to be clicked : example - contact/ taskforce / service-group
	 */
	public void collapseResourceInTreeScheduler(String resourceName){
		String xpath = "//tr[contains(@class,'"+resourceName+"')]//div[contains(@class,'x6-tree-expander')]";
		List<WebElement> collapseImg = driver.findElements(By.xpath(xpath));
		for(WebElement collapse : collapseImg){
			collapse.click();
		}
		Reporter.log(""+resourceName+" are collapsed",true);
	}
	
	/**
	 * Helper method to expand resources in scheduler
	 * @param resourceName : to be clicked : example - contact/ taskforce / service-group
	 * @param text : eg - Referencevalue
	 */
	public void expandResourcesInTreeScheduler(String resourceName, String text){
		String xpath = "//tr[contains(@class,'"+resourceName+"')]//span[contains(@class,'x6-tree-node-text') and contains(text(),'"+text+"')]//preceding-sibling::div[contains(@class,'x6-tree-expander')]";
	driver.findElement(By.xpath(xpath)).click();
	Reporter.log(""+text+" "+resourceName+" is expanded",true);
	}
	
	/**
	 * Helper method to click on (GPS) labels in Info Panel
	 * @param labelName
	 */
	public void clickInfoPanelLabelValue(String labelName,String labelText ){
		driver.findElement(By.xpath("//div[contains(@id,'mcs_scheduler_information_panel')]//td[contains(text(),'"+labelName+"')]//following-sibling::td[contains(@class,'informationPanelName')]//a[contains(text(),'"+labelText+"')]")).click();
	}
		
	/**
	 * Helper method to child window title 
	 * @param parentWindow ID
	 * @return child window title
	 */
	public String switchAndGetChildWindow(String parentWindow){
	
		Set<String> handles =  driver.getWindowHandles();
		 String newPageTitle = "Title not found";
		    for(String windowHandle  : handles)
		         {
		         if(!windowHandle.equals(parentWindow))
		            { try{
		           driver.switchTo().window(windowHandle);
		           Thread.sleep(8000);
		           newPageTitle = driver.getTitle();
		             driver.close(); //closing child window
		            } catch(Exception e1){
		             e1.printStackTrace();
		            }
		          }
		     }
		 		return newPageTitle;
	}
	
	/**
	 * Helper method to switch to window
	 * @param parentWindow ID
	 */
	public void switchToWindow(String parentWindow){
		driver.switchTo().window(parentWindow); //cntrl to parent window
	}
	
	//********* Related To Zoom Level Test ************************///
	
	/**
	 * Helper method to verify Zoom levels section is under Profile section and over Task label sections.
	 */
	public boolean verifyOrderOfSectionsInOptionProfilesSettingsWin(int rowNum, String text){
		String xpath = "//div[contains(@class, 'x-panel-body x-border-panel')]//form//div[contains(@class, 'x-panel-body-noheader x-panel-body-noborder')]/div["+rowNum+"]//span[text()='"+text+"']";
		return McsElement.getElementByXpath(driver, xpath).isDisplayed();
	}
	
	/**
	 * Helper method to verify Buttons in Zoom levels Grid
	 */
	public boolean verifyZoomLevelButtonsWin(String btnName){
		String xpath = ZOOM_LEVEL_BUTTON_XPATH+"//button[contains(text(), '"+btnName+"')]";
		boolean status = McsElement.getElementByXpath(driver, xpath).isDisplayed();
		String className = "";
		
		if(status){
			className = McsElement.getElementByXpath(driver, xpath).getAttribute("class");
		}
		
		/*switch(btnName) {
			case "Add": {
				className.contains("icon-add");
				status = true;
				break;
			}
			case "Edit": {
				className.contains("editButton");
				status = true;
				break;
			}
			case "Delete": {
				className.contains("icon-delete");
				status = true;
				break;
			}
			case "Default": {
				status = false;
				break;
			}
				
		}*/
		
		if(btnName.contains("Add")){
			status = className.contains("icon-add");
		} else if(btnName.contains("Edit")){
			status = className.contains("editButton");
		} else{ 
			status = className.contains("icon-delete");
		}
		
		return status;
	}
	
	/**
	 * Helper method to click on Zoom level buttons
	 */
	public void clickButtonInZoomLevelWin(String btnName){
		String xpath = ZOOM_LEVEL_BUTTON_XPATH+"//button[contains(text(), '"+btnName+"')]";
		McsElement.getElementByXpath(driver, xpath).click();
		Reporter.log(btnName+" is clicked in Zoom Levels", true);
	}
	
	/**
	 * Helper method to check fields are mandatory and its type
	 */
	public boolean verifyFieldsNatureInZoomLevelWin(String inputName) {
		String xpath = WORKORDER_WINDOW_XPATH+"//input[@name='"+inputName+"']";
		String className = McsElement.getElementByXpath(driver, xpath+"/ancestor::div[contains(@class, 'x-form-item')]/label").getAttribute("class");
		boolean status = false;
		
		//Verify all fields are Mandatory
		if(className.contains("x-form-item-label-mandatory")){
			status = true;
		}
		
		//Verify num Field and Dropdowns
		if(status && inputName=="value"){
			className = driver.findElement(By.xpath(xpath)).getAttribute("class");
			if(className.contains("num-field"))
			status = true;
			Reporter.log(inputName+" Field is Input and Number field", true);
		} else {
			className = driver.findElement(By.xpath(xpath+"/following-sibling::img")).getAttribute("class");
			if(className.contains("x-form-arrow-trigger"))
			status = true;
			Reporter.log(inputName+" Field is Dropdown List", true);
		}
		
		return status;
	}
	
	/**
	 * Helper method to click on DropDown Icon zoom level
	 */
	public void clickDropDownZoomLevelWin(String inputName){
		String xpath = WORKORDER_WINDOW_XPATH+"//input[@name='"+inputName+"']";
		driver.findElement(By.xpath(xpath+"/following-sibling::img")).click();
		waitForExtJSAjaxComplete(3);
		Reporter.log("DropDown is clicked", true);
	}
	
	/**
	 * Helper method to verify Uom Dropdown values
	 */
	public List<String> verifyDropDownValuesInZoomLevelWin(String inputName) {
		String xpath = WORKORDER_WINDOW_XPATH+"//input[@name='"+inputName+"']";
		
		String elementId=driver.findElement(By.xpath(xpath)).getAttribute("id");
		
		return DropDown.getComboValuesFromDropDownSelector(driver, elementId);
	}
	
	/**
	 * Helper method to verify QTIP mess of value field
	 */
	public String verifyToolTipOfMsgUOMWin(String maxValue){
		String xpath = WORKORDER_WINDOW_XPATH+"//input[@name='value']";
		
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.xpath(xpath));
		action.moveToElement(we).build().perform();
		
		String toolTipXpath="//div[contains(@class,'x-tip') and (contains(@style,'visibility: visible'))]//*[@class='x-tip-body']";
		
		return driver.findElement( By.xpath(toolTipXpath)).getText();
	}

	/**
	 * Helper method to select UOM Using NAtive Dropdown
	 */
	public void setDropDownInZoomLevelsWin(String inputName, String uomValue){
		String xpath = WORKORDER_WINDOW_XPATH+"//input[@name='"+inputName+"']";
		String actionsButtonID= driver.findElement(By.xpath(xpath)).getAttribute("id");
		DropDown.setComboValueNative(driver, actionsButtonID, uomValue);
		Reporter.log(""+inputName+" Value is set", true);
	}
	
	/**
	 * Helper method to select UOM Using setComboValueDropDownSelector
	 */
	public void setDropDownInZoomLevelsUsingDropDownImgWin(String inputName, String uomValue){
		String xpath = WORKORDER_WINDOW_XPATH+"//input[@name='"+inputName+"']";
		String actionsButtonID= driver.findElement(By.xpath(xpath)).getAttribute("id");
		DropDown.setComboValueDropDownSelector(driver, actionsButtonID, uomValue);
		Reporter.log(""+inputName+" Value is set", true);
	}
	
	/**
	 * Helper method to set value in Zoom Levels
	 */
	public void setValueOfZoomLevelWin(String value){
		String xpath = WORKORDER_WINDOW_XPATH+"//input[@name='value']";
		WebElement ele = McsElement.getElementByXpath(driver, xpath);
		ele.click();
		ele.clear();
		ele.sendKeys(value);
		Reporter.log("Value is set in Zoom levels", true);
	}
	
	/**
	 * Helper method to click on Date Icon
	 */
	public void clickCalendarIcon(){
		String xpath = SCHEDULER_WIN_XPATH+SCHEDULER_DATE_ICON_XPATH;
		
		//click on Date icon
		McsElement.getElementByXpath(driver, xpath).click();
		waitForExtJSAjaxComplete(5);
		Reporter.log("Calendar Icon is clicked", true);
	}
		
	/**
	 * Helper method to verify Date Icon options
	 */
	public List<String> verifyCalendarRangeOptions() {
		clickCalendarIcon();
		waitForExtJSAjaxComplete(3);
		
		List<WebElement> listEle  = driver.findElements(By.xpath("//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//ul[contains(@class, 'x-menu-list')]/li[contains(@class, 'x-menu-list-item') and not (contains(@class, 'x-menu-sep-li'))]"));
		ArrayList<String> lsValues = new ArrayList<String>(); 

		for(int i=0;i<listEle.size(); i++){
			String valueText = listEle.get(i).getText().trim();
			lsValues.add(valueText);
		}
		return lsValues;
	}
	
	/**
	 * Helper method to select options in Calendar
	 */
	public void selectOptionsCalendar(String optionValue){
		driver.findElement(By.xpath("//div[contains(@class, 'x-menu-floating')]//span[contains(text(), '"+optionValue+"')]")).click();
		waitForExtJSAjaxComplete(5);
		Reporter.log("Calendar Option "+optionValue+" is clicked", true);
	}
	
	/**
	 * Helper method to get any week dates
	 */
	public String getWeekDates(int no){
		Calendar c = GregorianCalendar.getInstance();

        // Set the calendar to monday of the current week
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        System.out.println("Current week = " + Calendar.DAY_OF_WEEK);

        // Print dates of the current week starting on Monday
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String startDate = "", endDate = "";

        startDate = df.format(c.getTime());
        c.add(Calendar.DATE, no);
        endDate = df.format(c.getTime());

        return endDate;
	}
	
	/**
	 * Helper method to get Current Week Date
	 */
	public String getCurrentWeek(){
		String startDate = getWeekDates(0);
		String endDate = getWeekDates(6);

        endDate = startDate+" to "+endDate;
        System.err.println("End Date = " + endDate);
        
        return endDate;
	}
	
	/**
	 * Helper method to get next week date
	 */
	public String getNextWeek() {
		
		String startDate = getWeekDates(7);
		String endDate = getWeekDates(13);
        
        endDate = startDate+" to "+endDate;
        System.err.println("End Date = "  +endDate);
        
        return endDate;
	}

	/**
	 * Helper method to verify zoom level button status
	 * @param buttonName : Edit/Delete
	 * @return status
	 */
	public boolean verifyZoomLevelButtonStatus(String buttonName){
		String xpath = ZOOM_LEVEL_BUTTON_XPATH+"//table[contains(@class,'x-item-disabled')]//button[text()='"+buttonName+"']";
		boolean status = McsElement.getElementByXpath(driver, xpath).isDisplayed();
		return status;
	}

	/**
	 * Helper method to click on values in zoom level window
	 * @param value
	 */
	public void clickValuesInZoomLevelWin(String value){
		String xpath = ZOOM_LEVEL_BUTTON_XPATH+"//div[contains(@class,'x-grid3-cell-inner') and text()='"+value+"']//ancestor::div[contains(@class,'x-grid3-row')]";
		McsElement.getElementByXpath(driver, xpath).click();
	}

	/**
	 * Helper method to get warning window message 
	 * @return text
	 */
	public String getWarningMessage(){
		String WarningMsg = McsElement.getElementByXpath(driver, ""+PAGE_TITLE_XPATH+"//div[@class='ext-mb-content']").getText();
		return WarningMsg;
	}

	/**
	 * Helper method to click buttons in dialogue window
	 * @param buttonName eg:'Yes'/'No'
	 */
	public void clickDialogueBoxButtons(String buttonName){
		McsElement.getElementByXpath(driver, " "+PAGE_TITLE_XPATH+"//td[@class='x-toolbar-cell']//button[text()='"+buttonName+"']").click();
	}

	/**
	 * Helper method to check zoom level window visibility 
	 * @param windowTitle eg: Edit zoom level
	 * @return visibility status
	 */
	public boolean getZoomLevelWinStatus(String windowTitle){
		String xpath = "//div[contains(@class,'x-window') and contains(@style,'visibility: visible')]//div[contains(.,'"+windowTitle+"') and contains(@class,'x-window-draggable')]";
		return McsElement.isElementDisplayed(driver, xpath);
	}

	/**
	 * Helper method to get field values in zoom level window
	 * @param fieldName 
	 * @return text
	 */
	public String getValueOfZoomLevelWin(String fieldName){
		String xpath = WORKORDER_WINDOW_XPATH+"//input[@name='"+fieldName+"']";
		String fieldValue = McsElement.getElementByXpath(driver, xpath).getAttribute("value");
		return fieldValue;
	}

	/**
	 * Helper method to check whether task is present or not
	 * @param contact to which the task is scheduled 
	 * @param schedulerTask name
	 * @return presence status
	 */
	public boolean isTaskPresent(String contact,String schedulerTask){
		String schedXpath = "//table[contains(@id,'scheduler') and contains(@id,'"+getIDOfContact(contact)+"')]//div[contains(@class, 'grid-cell-inner')]//div[contains(text(),'"+schedulerTask+"')]";
		return McsElement.isElementDisplayed(driver, schedXpath);
	}
	
	/**
	 * Helper method to verify Buttons is not displayed in Scheduler Tab
	 */
	public boolean verifyButtonIsNotDisplayedInActions(String actionbutton){
		/*String xpath = SCHEDULER_WIN_XPATH+SCHEDULER_ACTIONS_XPATH;
		driver.findElement(By.xpath(xpath)).click();*/
		boolean status = false;
		
		status = driver.findElement(By.xpath("//div[contains(@class,'x-panel scheduler')]//td[contains(@class,'x-toolbar-cell')]//button[contains(@class,'x-btn-text icon') and text()='"+actionbutton+"']")).isDisplayed();
		return status;
	}
	
	/**
	 * Helper method to get the Workorder Name from the Window
	 */
	public String getWONameFromDetailsWindow(){
		String xpath = "//div[contains(@class, 'x-window x-resizable-pinned')]//div[contains(@class, 'xtb-text')]";
		
		String text = McsElement.getElementByXpath(driver, xpath).getText();
		String [] chunks = text.split (" ", 5);
        String woID = chunks.length == 5 ? chunks [2] : null;
        System.out.println(woID);
		return woID;
	}
	
	/**
	 * Helper method to mouseOver Added task and get the Reference
	 */
	public String mouseHoverTaskAndVerifyWoIdReference(String id){
		String xpath = "//div[contains(@id, '"+id+"') and contains(@class, 'sch-event')]";
		
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.xpath(xpath));
		action.moveToElement(we).build().perform();
		
		String woRef = driver.findElement(By.xpath(SLA_TOOL_TIP_XPATH+"//div[contains(@class, 'x-panel-body')]/div[contains(@style, 'bold')]")).getText();
		return woRef;
	}
	
	/**
	 * Helper method to get Colour code of Task
	 */
	public String getAttributeValuesOfTask(String id, String attribute){
				
		String xpath = "//div[contains(@id, '"+id+"') and contains(@class, 'sch-event')]";
		String value = driver.findElement(By.xpath(xpath)).getAttribute(attribute);
		return value;
	}
	
	/**
	 * Helper method to get the ID value from Hour Mode
	 * Same ID is displayed for the task in Day Mode
	 */
	public String getIDFromHourMode(String contact){
		String idValueOriginal = getAttributeValuesOfTask(contact, "id");
		
		String[] split = idValueOriginal.split("-timelineview-");
		String secondSubString = split[1];
		secondSubString = secondSubString.substring(0, 8);
		return secondSubString;
	}
	
	/**
	 * Helper method to Hour Mode Values in User Options
	 * Ex:: label Name : Smallest time box visualization:
	 * no : 1 for Hour Mode and 2 For Day Mode
	 */
	public void setDurationValuesInUserOptions(String labelName, String no, String comboValue){
		String xpath = "//div[contains(@id, '"+getXWindowId(USER_OPTIONS_WINDOW_HEADER)+"')]//span[contains(text(), 'Durations')]/ancestor::div[contains(@class, 'x-panel details-general')]//div[@class='x-panel-bwrap']//label[contains(text(), '"+labelName+"')]/../following-sibling::td["+no+"]//input";
		
		String idValue = driver.findElement(By.xpath(xpath)).getAttribute("id");
		DropDown.setComboValueDropDownSelector(driver, idValue, comboValue);
	}
	
	/**
	 * Helper method to get Dimensions Of Task
	 */
	public int getDimensionsOfTask(String id){
		String xpath = "//div[contains(@id, '"+id+"') and contains(@class, 'sch-event')]";
		int width = driver.findElement(By.xpath(xpath)).getSize().getWidth();
		System.err.println("Width " +width);
		return width;
	}
	
	/**
	 * Helper method to click Modify Button in User Options window
	 */
	public void clickModifyButtonInOptions(String xwindowTitle) {
		String xpath = "//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//button[contains(text(), 'Modify')]";
		
		McsElement.getElementByXpath(driver, xpath).click();
		Reporter.log("Modify Button is clicked in User Options Window", true);
	}
	
	/**
	 * Helper method to verify Added column is available in the User Options window
	 */
	public boolean verifyColumnsInUserOptions(String xwindowTitle, String columnName){
		String xpath = "//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//div[contains(@class, 'x-panel details-general')]//div[contains(text(), '"+columnName+"')]";
		return McsElement.isElementDisplayed(driver, xpath);
	}
	
	/**
	 * Helper method to get Colour code of Task
	 */
	public String getCssValuesOfTask(String id){
		String xpath = "//div[contains(@id, '"+id+"') and contains(@class, 'sch-event')]";
		String value = driver.findElement(By.xpath(xpath)).getCssValue("background-color");
		return value;
	}
	
	/**
	 * Helper method to click on scheduler task popup 
	 */
	public void clickScheduleTask(){
		driver.findElement(By.xpath("//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible;')]//span[contains(text(), 'Schedule task')]/ancestor::a")).click();
	}

	/**
	 * Helper method to create task by drag and drop using work order
	 * @param contact to which task needs to be created 
	 * @param fromTime start time
	 * @param toTime end time
	 */
	public void createTaskByDraggingOnTimeLine(String contact,String fromTime, String toTime){

		int from = Integer.parseInt(fromTime)+1;
		int to = Integer.parseInt(toTime)+1;

		String startTimePointXpath ="//div[contains(@class,'sch-horizontaltimeaxis')]//table[contains(@class,'sch-header-row-middle')]//td["+from+"]"; 
		String toTimePointXpath ="//div[contains(@class,'sch-horizontaltimeaxis')]//table[contains(@class,'sch-header-row-middle')]//td["+to+"]";

		WebElement fromTimeCol= McsElement.getElementByXpath(driver, startTimePointXpath);

		int startPointX= fromTimeCol.getLocation().getX();
		int startPointY = fromTimeCol.getLocation().getY();

		WebElement toTimeCol=  McsElement.getElementByXpath(driver, toTimePointXpath);

		int endPointX = toTimeCol.getLocation().getX();
		int endPointY = toTimeCol.getLocation().getY();

		int heightOfRows = getGridSizeOfResources(contact);

		String timeAxis = "//div[contains(@class,'x6-docked')]//div[contains(@class,'simple-timeaxis')]";
		String timeLineHeaderContent = "//div[contains(@class,'sch-timelinepanel-splitter')]//following-sibling::div[contains(@class,'x6-grid') and contains(@id,'scheduler')]//div[contains(@class,'x6-docked') and contains(@id,'headercontainer')]";
		int timeAxisWidth= McsElement.getElementByXpath(driver, timeAxis).getSize().getHeight();
		int timeLineHeaderContenWidth= McsElement.getElementByXpath(driver, timeLineHeaderContent).getSize().getHeight();

		heightOfRows+=timeLineHeaderContenWidth-timeAxisWidth;
		
		int dragOffSetX = endPointX - startPointX;
		int dragOffSetY = endPointY - startPointY;

		dragOffSetY = (dragOffSetY>0)? dragOffSetY: 0;

		Actions action = new Actions(driver);
		action.moveToElement(fromTimeCol).moveByOffset(0,heightOfRows).clickAndHold().moveByOffset(dragOffSetX, dragOffSetY).release().perform();
		Reporter.log("The Task is dragged successfully",true);
	}

	/**
	 * Helper method to get sum of preceding siblings grid size
	 * @param contact required
	 * @return sum of size
	 */
	public int getGridSizeOfResources(String contact){
		int height = 0;
		String precedingSibXpath = "//span[contains(text(), '"+contact+"')]/ancestor::table//preceding::table[contains(@id,'treeview')]";
		List<WebElement> Elements = driver.findElements(By.xpath(precedingSibXpath));
		for(WebElement element : Elements ){
			height += element.getSize().getHeight();
		}
		WebElement element = driver.findElement(By.xpath( "//span[contains(text(), '"+contact+"')]/ancestor::table"));
		height +=element.getSize().getHeight();
		return height;
	}

	/**
	 * Helper method to mouse hover the resource
	 * @param contact to be mouse hover
	 * @param skill to be checked
	 * @return status
	 */
	public boolean mouseHoverResourceAndVerifySkill(String contact, String skill){
		String resourceXpath = "//span[contains(text(), '"+contact+"')]/ancestor::table";
		WebElement webElement = driver.findElement(By.xpath(resourceXpath));

		Actions action = new Actions(driver);
		action.moveToElement(webElement).build().perform();

		String skillPresentXpath = "//table[@class='assigned-to-info-tooltip']//td[contains(@class,'skill-present') and text()='"+skill+"']";
		String skillMissingXpath = "//table[@class='assigned-to-info-tooltip']//td[contains(@class,'skill-missing-recommended') and text()='"+skill+"']";

		if(!McsElement.isElementPresent(driver, skillPresentXpath)){
			driver.findElement(By.xpath(skillMissingXpath)).isDisplayed();
			return false;
		}
		return true;
	}

	/**
	 * Helper method to get warning message from skill matching window
	 * @return warning message
	 */
	public String getWarningMsgOfSkillMatchingWin(){
		String xpath = "//div[contains(@class,'x6-window-default-closable')]//div[contains(@class,'x6-component-default') and contains(@id,'messagebox')]";
		return driver.findElement(By.xpath(xpath)).getText();
	}

	/**
	 * Helper method to click buttons in skill matching window 
	 * @param buttonName to be clicked
	 */
	public void clickButtonsInSkillMatchingWin(String buttonName){
		String buttonXpath = "//span[contains(@class, 'x6-btn-inner') and text()='"+buttonName+"']//ancestor::a[contains(@class,'x6-btn-default-small')]";
		driver.findElement(By.xpath(buttonXpath)).click();
		Reporter.log("Button "+buttonName+" is pressed",true);
	}

	/**
	 * Helper method to collapse Navigation
	 */
	public void collapseNavigation(){
		String expandedMode  = "//div[@id='portalcontainer_navigation' and not(contains(@class,'x-panel-collapsed'))]//div[contains(@class,'x-tool-collapse-west')]";
		String collapsedMode = "//div[@id='portalcontainer_navigation-xcollapsed' and contains(@style,'visibility: visible')]//div[contains(@class,'expand-west')]";
		if(!McsElement.isElementPresent(driver, collapsedMode)){
			driver.findElement(By.xpath(expandedMode)).click();
		}
	}

	/**
	 * Helper method to click on Module in start page
	 * @param xpath module name
	 */
	public void clickModuleInStartPage(String xpath) {
		switchToSchedulerIframe();
		waitAndClick(xpath);
		switchBackToDefaultSchedulerFrame();
	}

	/**
	 * Helper method to click on save in Filter window 
	 * @param windowTitle
	 */
	public void clickSaveFilterInFilterWindow(){
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable-pinned') and contains(@style,'visibility: visible')]//button[text()='Save']")).click();
		waitForExtJSAjaxComplete(5);

		Reporter.log("filter changes are saved",true);
	}
	
	/**
	 * Helper method to verfiy WO Grid is Empty
	 */
	public boolean isGridEmpty(String text){
		String xpath = "//div[contains(@class,'x-panel scheduler')]//div[@class='x-grid3-body']/div[contains(@class, 'x-grid-empty')]";
		String textVal = McsElement.getElementByXpath(driver, xpath).getText();
		if(textVal.contains(text)){
			return true;
		} else{
			return false;
		}
	}
	
	/**
	 * Helper method to verify Scheduler Resource Grid is empty
	 */
	public boolean isResourceGridEmpty(String text){
		String xpath = "//div[starts-with(@id, 'scheduler') and contains(@id, 'locked-body')]//div[contains(@class,'x6-grid-item-container')]";
		String value = McsElement.getElementByXpath(driver, xpath).getAttribute("innerHTML");
		value = value.contains(" ")?text:(value.contains("null")?text:text);
		if(value.contains("Empty")){
			return true;
		} else{
			return false;
		}
	}
	
	/**
	 * Helper method to verify Task Grid is Empty
	 */
	public boolean isTaskGridEmpty(String text){
		String xpath = "//div[contains(@class, 'x6-grid-with-row-lines sch-timelineview')]";
		String value = McsElement.getElementByXpath(driver, xpath).getText();
		value = value.contains(" ")?text:(value.contains("null")?text:text);
		if(value.contains("Empty")){
			return true;
		} else{
			return false;
		}
	}
	
	/**
	 * Helper method to getSite From Administration
	 */
	public String getSiteFromServOrganization(String servOrg){
		String xpath="//span[contains(text(), '"+servOrg+"')]/../../ancestor::li//span[contains(text(), 'Sites')]/ancestor::li//ul[contains(@style, 'visibility: visible')]//img[contains(@class,'icon-site')]/..//a/span";
		return McsElement.getElementByXpath(driver, xpath).getText();
	}
	
	/**
	 * Helper method to compare Dates
	 */
	public boolean compareDate(String frmDt, String plannedStrtDate, String toDt, String plannedEndDt) throws Exception{
		boolean status = false;
		
		String dateString1 = frmDt;
		String dateString2 = plannedStrtDate;

	    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

	    Date date1 = format.parse(dateString1);
	    Date date2 = format.parse(dateString2);

	    if (date1.compareTo(date2) <= 0) {
	        System.out.println("dateString1 "+date1+" is an earlier date than dateString2 "+date2+"");
	        status = true;
	    } 
	    
	    String dateString3 = toDt;
		String dateString4 = plannedEndDt;
	    
	    Date date3 = format.parse(dateString3);
	    Date date4 = format.parse(dateString4);
	    
	    if(status){
	    	 if (date3.compareTo(date4) >= 0) {
	 	        System.out.println("dateString3 "+date3+" is an earlier date than dateString4 "+date4+" ");
	 	       status = true;
	 	    }
	    }
	    
	    return status;
	}
	
	/**
	 * Helper method to verify Service Group available in Resource Grid
	 */
	public boolean verifyServGrpInResourceGrid(String serviceGrp){
		String serviceGrpXpath = SCHEDULER_RESOURCE_GRID+"//div[contains(@class,'icon-service-group')]/following-sibling::span[text()='"+serviceGrp+"']/ancestor::tr";
		
		return McsElement.isElementDisplayed(driver, serviceGrpXpath);
	}
	
	/**
	 * Helper method to get hour Table Width
	 */
	public int getTotalHoursWidth(){
		String timeTableXpath = "//div[contains(@class, 'sch-horizontaltimeaxis')]//table[contains(@class, 'sch-header-row-middle')]";
		String timeTableEle = driver.findElement(By.xpath(timeTableXpath)).getAttribute("style");
		
		String [] chunks = timeTableEle.split (" ", 2);
        timeTableEle = chunks.length == 2 ? chunks [1] : null;
        timeTableEle = timeTableEle.substring(0, timeTableEle.indexOf("p"));
        int timeTableIntValue = Integer.parseInt(timeTableEle);
        System.out.println(+timeTableIntValue);
        
        return timeTableIntValue;
	}
	
	/**
	 * Helper method to get cell Width in Hour Mode
	 */
	public int getIndividualCellWidthInHourMode(){
        String hourElementsXPath = "//div[contains(@class, 'sch-horizontaltimeaxis')]//table[contains(@class, 'sch-header-row-middle')]//tr/td";
        List<WebElement> hourElems = driver.findElements(By.xpath(hourElementsXPath));
        int hourEleSize = hourElems.size();
        System.out.println(hourEleSize);
        
        int total = getTotalHoursWidth(); //1200
        hourEleSize = total / hourEleSize; // (1200/24 = 50)
        
       return hourEleSize;
	}
	
	/**
	 * Helper method to get Width of Task Starting cell
	 * Ex: right:px;left:400px;top:0px;height:21px;width:49px;background-color: #FFFF00;
	 * noOfSplits : Based on ": " parameter split the Ex into no of parts (For Eg: 5)
	 * reqdSize : Pass '4' to get the last part alone 
	 */
	public int getWidthOfTask(String contact, int noOfSplits, int reqdSize){
		String id = getIDOfContact(contact);
		waitForExtJSAjaxComplete(15);
        String taskStartXPath = "//div[contains(@id, '"+id+"') and contains(@class, 'sch-event')]";
        String taskEle = driver.findElement(By.xpath(taskStartXPath)).getAttribute("style");
        
        String [] chunksTask = taskEle.split (": ", noOfSplits);
        taskEle = chunksTask.length == noOfSplits ? chunksTask [reqdSize] : null;
        taskEle = taskEle.substring(0, taskEle.indexOf("p"));
        int taskEleWidth = Integer.parseInt(taskEle);
        return taskEleWidth;
	}
	
	/**
	 * Helper method to select date in GoToDate Using Calendar Icon
	 */
	public void selectDateInGoToDate(String frmDt, String dt15, String month, String years){
		//click Calendar Icon
		driver.findElement(By.xpath(SCHEDULER_WIN_XPATH+SCHEDULER_GOTO_XPATH)).click();
		
		//Click on Month in Center 
		driver.findElement(By.xpath("//div[contains(@class, 'datepicker-month')]//span[contains(@id, 'btnInnerEl')]")).click();
		
		//Select Month
		driver.findElement(By.xpath("//div[contains(@class, 'monthpicker')]//div[contains(@class, 'monthpicker-month')]//a[contains(text(), '"+month+"')]")).click();
		
		//Select Year
		driver.findElement(By.xpath("//div[contains(@class, 'monthpicker')]//div[contains(@class, 'monthpicker-years')]//a[contains(text(), '"+years+"')]")).click();
		
		//Click OK
		driver.findElement(By.xpath("//div[contains(@class, 'monthpicker-buttons')]//span[contains(text(), 'OK')]")).click();
		waitForExtJSAjaxComplete(3);
		
		//Select Date
		driver.findElement(By.xpath("//div[contains(@class, 'datepicker')]//td[contains(@class, 'datepicker-active')]//div[contains(text(), '"+dt15+"')]")).click();
		waitForExtJSAjaxComplete(3);
	}
	
	/**
	 * Helper method to get Values from Grid
	 * Filter record first and values next
	 */
	public List<String> getValuesFromTransactionLine(String attribute, String attributeValue, String rowTextName, String columnName, String colNameArr[]) {

		Timer timer = new Timer().start();
		waitForExtJSAjaxComplete(5);

		int columnNumber = getGridColumnNumberWithQuickFilters(attribute, attributeValue , columnName );
		waitForExtJSAjaxComplete(5);

		ArrayList<String> list1 = new ArrayList<String>();
		String eleValues = "";
		for(int i=0; i<=colNameArr.length-1; i++){

			int lineIDColumnNumber = getGridColumnNumberWithQuickFilters(attribute, attributeValue , colNameArr[i]);
			WebElement ele = McsElement.getElementByXpath(driver,"(//"+"div"+"[contains("+attribute+",'" + attributeValue + "') and not (contains(@class, 'x-hide-display'))]//"
					+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
					+ "and "+"text()"+ "='" + rowTextName + "'" + 
					"])[last()]//..//..//div[contains(@class,'x-grid3-col-"+lineIDColumnNumber+"')]");
			waitForExtJSAjaxComplete(3);

			eleValues = ele.getText();
			list1.add(eleValues);
		}
		timer.stop();
		return list1;
	}

	/**
	 * Helper method to verify 5 values are displayed as a part of Task
	 */
	public int getNoOfElementsDisplayedInTask(){
		String xpath = SLA_TOOL_TIP_XPATH+"//div[contains(@class, 'sla-tooltip-grid')]//div[contains(@class, 'x-grid3-row-first')]//td[contains(@class, 'x-grid3-cell') and not (contains(@style, 'display: none;'))]";
		List<WebElement> ele = driver.findElements(By.xpath(xpath));
		return ele.size();
	}

	/**
	 * Helper method to get Values from Task row wise
	 * rowClass =  x-grid3-row-first and x-grid3-row-last
	 * classValue = x-grid3-col-
	 * tdClass = sla-indicator-title / sla-indicator column
	 * attrValue = class / value
	 * Pass True for SLA Verification alone and false for rest all
	 */
	public String getSLAInfoFromtask(String rowClass, String classValue, boolean slaCol, String tdClass, String attrValue){
		String xpath = SLA_TOOL_TIP_XPATH+"//div[contains(@class, 'sla-tooltip-grid')]//div[contains(@class, '"+rowClass+"')]//div[contains(@class, '"+classValue+"')]";
		String value = "";

		if(slaCol){
			xpath = xpath+"//table[contains(@class, 'column-sla-indicator')]//td[contains(@class, '"+tdClass+"')]";
		}

		WebElement ele = McsElement.getElementByXpath(driver, xpath);

		if(slaCol){
			value = ele.getAttribute(attrValue);
		} else{

			value = ele.getText();
		}
		return value;
	}	

	/**
	 * Helper method to verify SLA Indicator for WO relevant metric
	 * @param contact to which task assigned
	 * @param slaType slaTypeWo/slaTypeCall
	 * @param slaIndicator good/bad
	 * @return
	 */
	public boolean verifySLAIndicatorForWORelevantMetric(String contact,String slaType,String slaIndicator){
		String slaIndicatorXpath = "//table[contains(@id,'scheduler') and contains(@id,'"+getIDOfContact(contact)+"')]//div[contains(@class, 'grid-cell-inner')]//div[@class='sla-icons-container ']//div[contains(@class,'"+slaType+"') and contains(@class,'"+slaIndicator+"')]";
		return McsElement.isElementDisplayed(driver, slaIndicatorXpath);
	}


	/**
	 * Helper method to select boards in scheduler
	 * @param boardName to be selected
	 * @param boardDisplay to show
	 */
	public void selectBoardsInScheduler(String boardName,String boardDisplay){
		String boardXpath = "//div[contains(@class,'scheduler')]//button[contains(@class,'icon-board')]";
		String reqBoardXpath = "//span[contains(@class,'x-menu-item-text') and contains(text(),'"+boardName+"')]";
		String boardDisplayXpath = "(//span[contains(@class,'x-menu-item-text') and (text()='"+boardDisplay+"')])[last()]";

		driver.findElement(By.xpath(boardXpath)).click();
		waitForExtJSAjaxComplete(3);
		mouseMove(reqBoardXpath);
		waitForExtJSAjaxComplete(3);
		driver.findElement(By.xpath(boardDisplayXpath)).click();
	}

	/**
	 * Helper method to select WO in Add Task Window
	 * @param WO to be selected
	 */
	public void setWOInAddTaskWindow(String WO){
		clickLookup("@class","mcs-dialog", "workorder", "Select a Work Order");
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Work Order"), WO, "Reference");

		Reporter.log(WO+" WO was selected", true);
	}

	/**
	 * Helper method to click on save and close button based on window title 
	 * @param windowTitle 
	 */
	public void saveAndClose(String windowTitle) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXWindowId(windowTitle), "button", "text()", "Save",
				"text()","Close", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Save and close", true);
	}

	/**
	 * Helper method to click on close button based on window title 
	 * @param windowTitle
	 */
	public void close(String windowTitle) {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@id", getXWindowId(windowTitle), "button", "text()", "Close",
				true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Close", true);
	}

	/**
	 * Helper method to click on add action in scheduler window
	 */
	public void clickAddActionInSchedulerWin(){
		String xpath = SCHEDULER_WIN_XPATH+"//td[contains(@class,'x-toolbar-cell')]//button[contains(@class,'icon-ov-add') and text()='Add Action']";
		driver.findElement(By.xpath(xpath)).click();
		Reporter.log("Add Action button is clicked ", true);
	}

	/**
	 * Helper method to click on clear filters button
	 */
	public void clickClearFilterButton() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", "x-panel-tbar", "button", "@class",
				"icon-grid-filter-clear", "text()", "Clear Filters", true, true).click();
		Reporter.log("Click Clear Filters button", true);
	}

	/**
	 * Helper method to verify action buttons in scheduler module
	 * @param attrName of parent 
	 * @param className to be verified
	 * @return
	 */
	public boolean verifyActionButtonsSchedulerBasedOnClass(String attrName, String className){
		boolean status = false;
		status = driver.findElement(By.xpath("//td[contains(@class,'x-toolbar-cell')]//button[contains(@class,'"+className+"')]")).isDisplayed();
		if(status){
			Reporter.log("Action Button "+attrName+" is present in scheduler window", true);
		} 
		return status;
	}

	/**
	 * Helper method to click on action by class name
	 * @param action name of action
	 * @param className to be clicked based on
	 */
	public void selectActionByClassName(String action,String className){
		driver.findElement(By.xpath("//td[contains(@class,'x-toolbar-cell')]//button[contains(@class,'"+className+"')]")).click();
		Reporter.log("Action  "+action+" is clicked in scheduler window", true);
	}

	/**
	 * Helper method to enable working hours button 
	 */
	public void enableWorkingHoursButton(){
		String enabledXpath = "//div[contains(@class,'x-panel scheduler')]//a[contains(@class,'workingHoursButton') and contains(@class,'x6-btn-pressed')]";
		String disabledXpath = "//div[contains(@class,'x-panel scheduler')]//a[contains(@class,'workingHoursButton') and not(contains(@class,'x6-btn-pressed'))]";
		if(!McsElement.isElementPresent(driver,enabledXpath)){
			clickXPath(disabledXpath);
			Reporter.log("24/7 button is enabled",true);
		}else
			Reporter.log("24/7 button is already enabled",true);
	}
	
	/**
	 * helper method to mouse over on scheduler time line to get the tool tip
	 * @param mouseOverOnTime
	 * @param contact
	 * @return tool tip message
	 * @throws InterruptedException 
	 */
	public String mouseHoverOnSchedulerTimeSheetToGetToolTip(String mouseOverOnTime,String contact) throws InterruptedException{
		int time = Integer.parseInt(mouseOverOnTime)+1;
		WebElement fromTimeCol= McsElement.getElementByXpath(driver,"//div[contains(@class,'sch-horizontaltimeaxis')]//table[contains(@class,'sch-header-row-middle')]//td["+time+"]");
		int heightOfRows = getGridSizeOfResources(contact);
		waitForExtJSAjaxComplete(20);

		String timeAxis = "//div[contains(@class,'x6-docked')]//div[contains(@class,'simple-timeaxis')]";
		String timeLineHeaderContent = "//div[contains(@class,'sch-timelinepanel-splitter')]//following-sibling::div[contains(@class,'x6-grid') and contains(@id,'scheduler')]//div[contains(@class,'x6-docked') and contains(@id,'headercontainer')]";
		int timeAxisWidth= McsElement.getElementByXpath(driver, timeAxis).getSize().getHeight();
		int timeLineHeaderContenWidth= McsElement.getElementByXpath(driver, timeLineHeaderContent).getSize().getHeight();
		heightOfRows+=timeLineHeaderContenWidth-timeAxisWidth;
		waitForExtJSAjaxComplete(20);
		Actions action = new Actions(driver);
		waitForExtJSAjaxComplete(20);
		action.moveToElement(fromTimeCol).moveByOffset(50,heightOfRows).click().release().perform();
		waitForExtJSAjaxComplete(20);
		action.moveToElement(fromTimeCol).moveByOffset(0,heightOfRows).build().perform();
		action.moveToElement(fromTimeCol).moveByOffset(10,heightOfRows).build().perform();

		String toolTipText = driver.findElement( By.xpath("//div[contains(@id,'scheduler_hovertip')]//div[@class='sch-hovertip-msg']")).getText();
		return toolTipText;
	}

	/**
	 * Helper method to get non working hours duration
	 * @param resourceName 
	 * @return non working hours
	 */
	public List<String> getNonWorkingHours(String resourceName){
		WebElement fromTimeCol= McsElement.getElementByXpath(driver,"//div[contains(@class,'sch-horizontaltimeaxis')]//table[contains(@class,'sch-header-row-middle')]//td[1]");
		int widthOfEachCell = fromTimeCol.getSize().getWidth();

		List<String> nonWOrkingHours=new ArrayList<String>(); 
		String timings = null;
		String nonWorkingHrsXpath = "//table[contains(@id,'timelineview-record-"+getIDOfContact(resourceName)+"')]//td[contains(@class,'timeaxiscolumn')]//div[contains(@id,'timespangroup') and contains(@class,'unavailable')]";
		List<WebElement> nonWorkingHrsEles = driver.findElements(By.xpath(nonWorkingHrsXpath));

		for(WebElement nonWorkingHrsEle : nonWorkingHrsEles){
			String cellStyle = nonWorkingHrsEle.getAttribute("style");
			int startTime = (Integer.parseInt(cellStyle.substring(cellStyle.indexOf("left:")+(new String("left:")).length(),cellStyle.indexOf("px")).trim()))/widthOfEachCell;
			int endTime = Math.round((nonWorkingHrsEle.getSize().getWidth())/widthOfEachCell)+1;
			endTime=startTime+endTime;
			timings = Integer.toString(startTime)+"-"+Integer.toString(endTime);
			nonWOrkingHours.add(timings);
		}
		return nonWOrkingHours;
	}
}
