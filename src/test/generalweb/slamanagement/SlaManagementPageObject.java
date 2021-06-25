package test.generalweb.slamanagement;

//import java.util.concurrent.TimeUnit;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;










import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class SlaManagementPageObject extends AbstractMcsTestSuite {


	protected final String GRID_CLASS = "slamanagement";

	protected final String SLA_FORM_CLASS = "x-window x-resizable-pinned";

	protected final String SLA_DIALOG_TITLE = "SLA Objects";
	
	protected final String SLA_METRICS_GRID_XPATH = "//div[@id='MetricsTab']//span[text()='Metrics' and @class='x-panel-header-text']/../..//div[@class='x-grid3']";
	
	protected final String SLA_GRID_EDITOR = "//div[contains(@class,'x-grid-editor') and not(contains(@style,'visibility: hidden'))]";
	
	protected final String SLA_TARGET_RULES_LOW_TRAGET = "//input[@name='lowTarget']";
	
	protected final String SLA_TARGET_RULES_HIGH_TRAGET = "//input[@name='highTarget']";
	
	protected final String XPATH_SLA = "//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'SLA Management')]";
	
	
	public void clickGeneralTab(){
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", SLA_FORM_CLASS, "span", "text()", "General", true, true).click();
		Reporter.log("click general tab", true);
	}
	

	public void clickMetricsTab(){
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", SLA_FORM_CLASS, "span", "text()", "Metrics", true, true).click();
		Reporter.log("click metrics tab", true);
	}
	

	public void clickAuditingTab(){
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", SLA_FORM_CLASS, "span", "text()", "Auditing", true, true).click();
		Reporter.log("click auditing tab", true);
	}
	
	
	public void clickAddButton() {
		//McsElement.getElementByPartAttributeValueAndParentElement(driver,	"div", "@class", GRID_CLASS, "button", "@class", "x-btn-text","text()", "Add", true, true).click();
		McsElement.getElementByXpath(driver, "//img[contains(@class,'slamanagement')]/following::button[contains(@class,'x-btn-text')and contains(text(),'Add')]").click();
		Reporter.log("click add button", true);
	}

	
	public void doubleClick(WebElement element){
	Actions action = new Actions(driver);
	action.doubleClick(element);
	action.perform();
	}
	
	
	public void clickEditButton(String sla) {
		doubleClick(driver.findElement(By.xpath("//div[contains(@class,'"+GRID_CLASS+"']//div[text()='"+sla+"']")));
		Reporter.log("click edit button (double click on record)", true);
	}

	public void clickDeleteButton() {
		Timer timer = new Timer().start();
		/*McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", GRID_CLASS, "button", "@class", "x-btn-text",
				"text()", "Delete", true, true).click();*/
		McsElement.getElementByXpath(driver, "//img[contains(@class,'slamanagement')]/following::button[contains(@class,'x-btn-text')and contains(text(),'Delete')]").click();
		Reporter.log("Click delete " + " (" + timer.stop() + "ms)", true);
	}
	
///////////////////GENERAL TAB//////////////////////////////////////////
	
	public void setClassObject(String code) {
	String id = McsElement.getElementByXpath(driver, "//div[contains(@class,'x-resizable')]//label[contains(text(),'Object Class')]/..//input").getAttribute("id");
	DropDown.setExtJsComboValue(driver, id, code);
	
	waitForExtJSAjaxComplete(25);
	Reporter.log("set class " + code, true);
	}

	
	public String getClassObject() {
		return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+SLA_FORM_CLASS+"')]//label[contains(text(),'Object Class')]/..//input").getAttribute("value");
	}
	
	
	public void clearClassObject() {
		McsElement.getElementByXpath(driver, "//div[contains(@class,'"+SLA_FORM_CLASS+"')]//label[contains(text(),'Object Class')]/..//input").clear();
	}
	
	
	public void setCode(String code) {
		WebElement element = McsElement.getElementByXpath(driver, "//div[contains(@class,'"+SLA_FORM_CLASS+"')]//label[contains(text(),'Code')]/..//input");
		element.clear();
		element.sendKeys(code);
		Reporter.log("set code " + code, true);
	}
	
	public void clearCode() {
		McsElement.getElementByXpath(driver, "//div[contains(@class,'"+SLA_FORM_CLASS+"')]//label[contains(text(),'Code')]/..//input").clear();
	}
	
	public String getCode() {
		return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+SLA_FORM_CLASS+"')]//label[contains(text(),'Code')]/..//input").getAttribute("value");
	}
	
	public void setReference(String code) {
		WebElement element = McsElement.getElementByXpath(driver, "//div[contains(@class,'"+SLA_FORM_CLASS+"')]//label[contains(text(),'Reference')]/..//input");
		element.clear();
		element.sendKeys(code);
		Reporter.log("set reference " + code, true);
	}

	public String getReference() {
		return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+SLA_FORM_CLASS+"')]//label[contains(text(),'Reference')]/..//input").getAttribute("value");
	}

	public void clearReference() {
		McsElement.getElementByXpath(driver, "//div[contains(@class,'"+SLA_FORM_CLASS+"')]//label[contains(text(),'Reference')]/..//input").clear();
	}
	
	public void setValidityFrom(String code) {
		WebElement element = McsElement.getElementByXpath(driver, "//div[contains(@class,'"+SLA_FORM_CLASS+"')]//div[contains(text(),'From')]/..//input");
		element.clear();
		element.sendKeys(code);
		Reporter.log("set validity from " + code, true);
	}

	public String getValidityFrom() {
		return McsElement.getElementByXpath(driver, "//div[contains(@class,'"+SLA_FORM_CLASS+"')]//div[contains(text(),'From')]/..//input").getAttribute("value");
	}

	public void clearValidityFrom() {
		McsElement.getElementByXpath(driver, "//div[contains(@class,'"+SLA_FORM_CLASS+"')]//div[contains(text(),'From')]/..//input").clear();
	}
	
	public void setValidityUntil(String code) {
		WebElement element = McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+SLA_FORM_CLASS+"')]//div[contains(text(),'Until')]/..//input)[last()]");
		element.clear();
		element.sendKeys(code);
		Reporter.log("set validity from " + code, true);
	}

	public String getValidityUntil() {
		return McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+SLA_FORM_CLASS+"')]//div[contains(text(),'Until')]/..//input)[last()]").getAttribute("value");
	}
	
	public void clearValidityUntil() {
		McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+SLA_FORM_CLASS+"')]//div[contains(text(),'Until')]/..//input)[last()]").clear();
	}
	
	public void setCustomer(String proposerName){
		
		String name = McsElement.getElementByXpath(driver,"(//div[contains(@class,'x-resizable')]//label[contains(text(),'Customer')]/..//input)[last()]").getAttribute("name"); 
		clickLookup(SLA_FORM_CLASS, name);
		waitForExtJSAjaxComplete(20);
		setValueGridLookupWithFilters(proposerName, "Customer Name");
	}
	

	public void clearCustomer() {
		McsElement.getElementByXpath(driver,"(//div[contains(@class,'x-resizable')]//label[contains(text(),'Customer')]/..//input)[last()]").clear();
	}

	
	public String getCustomer() {
		return McsElement.getElementByXpath(driver,"(//div[contains(@class,'x-resizable')]//label[contains(text(),'Customer')]/..//input)[last()]").getAttribute("value");
	}


	public void setSupplier(String proposerName){
		String name = McsElement.getElementByXpath(driver,"(//div[contains(@class,'x-resizable')]//label[contains(text(),'Supplier')]/..//input)[last()]").getAttribute("name"); 
		clickLookup(SLA_FORM_CLASS, name);
		setValueGridLookupWithFilters(proposerName, "Name");
	}
	
	
	public String getSupplier() {
		return McsElement.getElementByXpath(driver,"(//div[contains(@class,'x-resizable')]//label[contains(text(),'Supplier')]/..//input)[last()]").getAttribute("value");
	}

	public void clearSupplier() {
		McsElement.getElementByXpath(driver,"(//div[contains(@class,'x-resizable')]//label[contains(text(),'Supplier')]/..//input)[last()]").clear();
	}
	
	
	public void setDefaultTimeTable(String proposerName){
		String name = McsElement.getElementByXpath(driver,"(//div[contains(@class,'x-resizable')]//label[contains(text(),'Default Time Table')]/..//input)[last()]").getAttribute("name"); 
		clickLookup(SLA_FORM_CLASS, name);
		waitForExtJSAjaxComplete(20);
		setValueGridLookupWithFilters(proposerName, "Reference");
	}
	
	
	public String getDefaultTimeTable() {
		return McsElement.getElementByXpath(driver,"(//div[contains(@class,'x-resizable')]//label[contains(text(),'Default Time Table')]/..//input)[last()]").getAttribute("value");
	}
	

	public void clearDefaultTimeTable() {
		McsElement.getElementByXpath(driver,"(//div[contains(@class,'x-resizable')]//label[contains(text(),'Default Time Table')]/..//input)[last()]").clear();
	}
	
	
	public void setDefaultTimeZone(String proposerName){
		String name = McsElement.getElementByXpath(driver,"(//div[contains(@class,'x-resizable')]//label[contains(text(),'Default Time Zone')]/..//input)[last()]").getAttribute("name"); 
		clickLookup(SLA_FORM_CLASS, name);
		waitForExtJSAjaxComplete(20);
		setValueGridLookupWithFilters(proposerName, "Label");
	}
	
	
	public String getDefaultTimeZone() {
		return McsElement.getElementByXpath(driver,"(//div[contains(@class,'x-resizable')]//label[contains(text(),'Default Time Zone')]/..//input)[last()]").getAttribute("value");
	}


	public void clearDefaultTimeZone() {
		McsElement.getElementByXpath(driver,"(//div[contains(@class,'x-resizable')]//label[contains(text(),'Default Time Zone')]/..//input)[last()]").clear();
	}

	
	
//////////////////////////METRICS TAB//////////////////////////////	
	
	

	
	public void clickActive(String metric){
		McsElement.getElementByXpath(driver,
				"//div[contains(@class,'x-resizable')]//div[text()='"+metric+"']/../..//div[contains(@class,'check')]").click();
		Reporter.log("click active for "+metric, true);
	}

	/**
	 * Helper method to select a Metic
	 * @param metric
	 */
	public void selectMetricRow(String metric){
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@id", getWindowIdBasedOnPartialMatch("Target Rule"), metric);
		
	}
	
	public void setTargetRuleNew(String rule, String lowTarget, String highTarget){
		
		
		String id = McsElement.getElementByXpath(driver,"(//div[@id='MetricsTab']/div//span[(contains(text(),'Target Rules'))]/../..)").getAttribute("id");
		
		String script="Ext.getCmp('"+id+"').store.data.items["+rule+"].data.highTarget='"+highTarget+"';";
		
		script= script + "Ext.getCmp('"+id+"').store.data.items["+rule+"].data.lowTarget='"+lowTarget+"';";
		
		script= script + "Ext.getCmp('"+id+"').store.data.items["+rule+"].afterEdit();";
		
		
		((JavascriptExecutor) driver).executeScript(script);
		
		waitForExtJSAjaxComplete(25);
		
		Reporter.log("fill in rule "+rule, true);
	}
	

	
	public void setTargetRuleNew(String rule, String lowTarget, String highTarget, String timeTable, String timeZone){
		
		
		HashMap<String, String> codeToStatusMapping = new HashMap<String, String>();
		codeToStatusMapping.put("1preTimeTableSLARef","0000000002");
		
		codeToStatusMapping.put("(GMT) Casablanca","0000000032");

		codeToStatusMapping.put("(GMT) Monrovia, Reykjavik","0000000035");
		
		
		codeToStatusMapping.put("","");
		
		if ((codeToStatusMapping.get(timeTable)==null) || (codeToStatusMapping.get(timeZone)==null)){Reporter.log("this time zone or timetable was not implemented in test code: add id if you want to use it by extjs ");
		throw new NullPointerException();}
		
		
		String id = McsElement.getElementByXpath(driver,"(//div[@id='MetricsTab']/div//span[(contains(text(),'Target Rules'))]/../..)").getAttribute("id");
		
		String script="Ext.getCmp('"+id+"').store.data.items["+rule+"].data.highTarget='"+highTarget+"';";
		
		script= script + "Ext.getCmp('"+id+"').store.data.items["+rule+"].data.lowTarget='"+lowTarget+"';";
		
		script= script + "Ext.getCmp('"+id+"').store.data.items["+rule+"].data.timetable.id='"+codeToStatusMapping.get(timeTable)+"';";
		
		script= script + "Ext.getCmp('"+id+"').store.data.items["+rule+"].data.timetable.caption='"+timeTable+"';";
		
		script= script + "Ext.getCmp('"+id+"').store.data.items["+rule+"].data.timezone.id='"+codeToStatusMapping.get(timeZone)+"';";
		
		script= script + "Ext.getCmp('"+id+"').store.data.items["+rule+"].data.timezone.caption='"+timeZone+"';";
		
		script= script + "Ext.getCmp('"+id+"').store.data.items["+rule+"].afterEdit();";
		
		
		((JavascriptExecutor) driver).executeScript(script);
		
		waitForExtJSAjaxComplete(25);
		
		Reporter.log("fill in rule "+rule, true);
	}

	
	

	public void setTargetRule(String rule, String lowTarget, String highTarget){


		String id = McsElement.getElementByXpath(driver,"(//div[@id='MetricsTab']/div//span[(contains(text(),'Target Rules'))]/../..)").getAttribute("id");

		String script="Ext.getCmp('"+id+"').store.data.items["+rule+"].data.high_target='"+highTarget+"';";

		script= script + "Ext.getCmp('"+id+"').store.data.items["+rule+"].data.low_target='"+lowTarget+"';";

		script= script + "Ext.getCmp('"+id+"').store.data.items["+rule+"].afterEdit();";


		((JavascriptExecutor) driver).executeScript(script);

		waitForExtJSAjaxComplete(25);

		Reporter.log("fill in rule "+rule, true);
	}



	public void setTargetRule(String rule, String lowTarget, String highTarget, String timeTable, String timeZone){


		HashMap<String, String> codeToStatusMapping = new HashMap<String, String>();
		codeToStatusMapping.put("1preTimeTableSLARef","0000000002");

		codeToStatusMapping.put("(GMT) Casablanca","0000000032");

		codeToStatusMapping.put("(GMT) Monrovia, Reykjavik","0000000035");


		codeToStatusMapping.put("","");

		if ((codeToStatusMapping.get(timeTable)==null) || (codeToStatusMapping.get(timeZone)==null)){Reporter.log("this time zone or timetable was not implemented in test code: add id if you want to use it by extjs ");
		throw new NullPointerException();}


		String id = McsElement.getElementByXpath(driver,"(//div[@id='MetricsTab']/div//span[(contains(text(),'Target Rules'))]/../..)").getAttribute("id");

		String script="Ext.getCmp('"+id+"').store.data.items["+rule+"].data.high_target='"+highTarget+"';";

		script= script + "Ext.getCmp('"+id+"').store.data.items["+rule+"].data.low_target='"+lowTarget+"';";

		script= script + "Ext.getCmp('"+id+"').store.data.items["+rule+"].data.timetable.id='"+codeToStatusMapping.get(timeTable)+"';";

		script= script + "Ext.getCmp('"+id+"').store.data.items["+rule+"].data.timetable.caption='"+timeTable+"';";

		script= script + "Ext.getCmp('"+id+"').store.data.items["+rule+"].data.timezone.id='"+codeToStatusMapping.get(timeZone)+"';";

		script= script + "Ext.getCmp('"+id+"').store.data.items["+rule+"].data.timezone.caption='"+timeZone+"';";

		script= script + "Ext.getCmp('"+id+"').store.data.items["+rule+"].afterEdit();";


		((JavascriptExecutor) driver).executeScript(script);

		waitForExtJSAjaxComplete(25);

		Reporter.log("fill in rule "+rule, true);
	}

	
	/**
	 * Helper method to set Target Rule values
	 * @param metric for which target rule has to be created
	 * @param lowTarget 
	 * @param highTarget
	 */
	public void setTargetRuleNative( String metric, String lowTarget, String highTarget){

		//selectTargetRow(metric);
		selectTargeRow();
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		
		setTargets(  lowTarget,  highTarget);
		
	}


	public void setMetric(String metric, String name, String startStatus, String endStatus, String highValues, boolean excludeSuspended){

		HashMap<String, String> codeToStatusMapping = new HashMap<String, String>();
		codeToStatusMapping.put("Suspended","0000000102");
		codeToStatusMapping.put("Archived","0000000104");
		codeToStatusMapping.put("Finished","0000000103");
		codeToStatusMapping.put("In Preparation","0000000100");
		codeToStatusMapping.put("In Progress","0000000101");
		codeToStatusMapping.put("1preWrkOrdStsName","0000000001");
		codeToStatusMapping.put("2preWrkOrdStsName","0000000002");
		codeToStatusMapping.put("3preWrkOrdStsName","0000000003");
		codeToStatusMapping.put("Bad","B");
		codeToStatusMapping.put("Good","G");
		codeToStatusMapping.put("","");

		String id = McsElement.getElementByXpath(driver,"//div[@id='MetricsTab']/div/div/div").getAttribute("id");

		String script= "Ext.getCmp('"+id+"').store.data.items["+metric+"].data.active=true;";
		script= script + "Ext.getCmp('"+id+"').store.data.items["+metric+"].data.name='"+name+"';";
		script= script + "Ext.getCmp('"+id+"').store.data.items["+metric+"].data.startstatus.caption='"+startStatus+"';";
		script= script + "Ext.getCmp('"+id+"').store.data.items["+metric+"].data.startstatus.id='"+codeToStatusMapping.get(startStatus)+"';";
		script= script + "Ext.getCmp('"+id+"').store.data.items["+metric+"].data.endstatus.caption='"+endStatus+"';";
		script= script + "Ext.getCmp('"+id+"').store.data.items["+metric+"].data.endstatus.id='"+codeToStatusMapping.get(endStatus)+"';";
		script= script + "Ext.getCmp('"+id+"').store.data.items["+metric+"].data.highvalues='"+codeToStatusMapping.get(highValues)+"';";
		script= script + "Ext.getCmp('"+id+"').store.data.items["+metric+"].data.exclude_suspended="+excludeSuspended+";";


		script= script + "Ext.getCmp('"+id+"').store.data.items["+metric+"].afterEdit();";

		((JavascriptExecutor) driver).executeScript(script);

		waitForExtJSAjaxComplete(25);

		Reporter.log("fill in metric "+metric, true);
	}

	/**
	 * Helper method to create metrics using new columns of 15.0 and above 
	 * Note: Columns name in Ext js file are updated. 
	 * @param metric - pass int value representing the Metric -Example: Pass 0 for Metric0
	 * @param name - metric name
	 * @param startStatus - Start status for SLA
	 * @param endStatus - End status for SLA
	 * @param highValues -  Good/Bad
	 * @param excludeSuspended - True/false to exclude suspended hours
	 */
	public void setMetricNew(String metric, String name, String startStatus, String endStatus, String highValues, boolean excludeSuspended){
		
		HashMap<String, String> codeToStatusMapping = new HashMap<String, String>();
		codeToStatusMapping.put("Suspended","0000000102");
		codeToStatusMapping.put("Archived","0000000104");
		codeToStatusMapping.put("Finished","0000000103");
		codeToStatusMapping.put("In Preparation","0000000100");
		codeToStatusMapping.put("In Progress","0000000101");
		codeToStatusMapping.put("1preWrkOrdStsName","0000000001");
		codeToStatusMapping.put("2preWrkOrdStsName","0000000002");
		codeToStatusMapping.put("3preWrkOrdStsName","0000000003");
		codeToStatusMapping.put("Bad","B");
		codeToStatusMapping.put("Good","G");
		codeToStatusMapping.put("","");
		
		//String id = McsElement.getElementByXpath(driver,"//div[@id='MetricsTab']/div/div/div").getAttribute("id");
		String id = McsElement.getElementByXpath(driver,"//span[text()='Metrics' and contains(@class,'header-text')]/../..").getAttribute("id");
		
/*		['id', 'linkId', 'active', 'name', 'startStatus',
         'endStatus', 'highValues', 'excludeSuspended', 'targetRules'];
*/		
		String script= "Ext.getCmp('"+id+"').store.data.items["+metric+"].data.active=true;";
		script= script + "Ext.getCmp('"+id+"').store.data.items["+metric+"].data.name='"+name+"';";
		script= script + "Ext.getCmp('"+id+"').store.data.items["+metric+"].data.startStatus.caption='"+startStatus+"';";
		script= script + "Ext.getCmp('"+id+"').store.data.items["+metric+"].data.startStatus.id='"+codeToStatusMapping.get(startStatus)+"';";
		script= script + "Ext.getCmp('"+id+"').store.data.items["+metric+"].data.endStatus.caption='"+endStatus+"';";
		script= script + "Ext.getCmp('"+id+"').store.data.items["+metric+"].data.endStatus.id='"+codeToStatusMapping.get(endStatus)+"';";
		script= script + "Ext.getCmp('"+id+"').store.data.items["+metric+"].data.highValues='"+codeToStatusMapping.get(highValues)+"';";
		script= script + "Ext.getCmp('"+id+"').store.data.items["+metric+"].data.excludeSuspended="+excludeSuspended+";";
		
			
		script= script + "Ext.getCmp('"+id+"').store.data.items["+metric+"].afterEdit();";
		
		((JavascriptExecutor) driver).executeScript(script);
		
		waitForExtJSAjaxComplete(25);
		
		
		//McsElement.getElementByXpath(driver,"//div[@id='MetricsTab']/div/div/div").getAttribute("id");
		 McsElement.getElementByXpath(driver,"//span[text()='Metrics' and contains(@class,'header-text')]/../..").getAttribute("id");
		Reporter.log("fill in metric "+metric, true);
	}
	
	
	
	/**
	 * Helper method to create metrics using new columns of 15.0 and above 
	 * Note: Columns name in Ext js file are updated. 
	 * @param metric - pass int value representing the Metric -Example: Pass 0 for Metric0
	 * @param name - metric name
	 * @param startStatus - Start status for SLA
	 * @param endStatus - End status for SLA
	 * @param highValues -  Good/Bad
	 * @param excludeSuspended - True/false to exclude suspended hours
	 */
	public void setCallMetric(String metric, String name, String startStatus, String endStatus, String highValues, boolean excludeSuspended){
		
		clickActive(metric);
		
		waitForExtJSAjaxComplete(10);
		
		String rownNum = getMetricRowNum(metric);
		
		setMetricName(rownNum,name);
		
		waitForExtJSAjaxComplete(10);
	
		setStartStatus( rownNum,"Select a Call Status", startStatus);
		
		waitForExtJSAjaxComplete(10);
		
		setEndStatus(rownNum,"Select a Call Status", endStatus);
		
		waitForExtJSAjaxComplete(10);
		
		if(excludeSuspended){
			
			checkExcludeSuspensionTime(rownNum);
		}
		
		Reporter.log("fill in metric "+metric, true);
	}
	
	
	
	/**
	 * Helper method to create metrics using new columns of 15.0 and above 
	 * Note: Columns name in Ext js file are updated. 
	 * @param metric - pass int value representing the Metric -Example: Pass 0 for Metric0
	 * @param name - metric name
	 * @param startStatus - Start status for SLA
	 * @param endStatus - End status for SLA
	 * @param highValues -  Good/Bad
	 * @param excludeSuspended - True/false to exclude suspended hours
	 */
	public void setWOMetric(String metric, String name, String startStatus, String endStatus, String highValues, boolean excludeSuspended){
		
		clickActive(metric);
		
		waitForExtJSAjaxComplete(10);
		
		String rownNum = getMetricRowNum(metric);
		
		setMetricName(rownNum,name);
		
		waitForExtJSAjaxComplete(10);
		
		setStartStatus( rownNum,"Select a Work Order Status", startStatus);
		
		waitForExtJSAjaxComplete(10);
		
		setEndStatus(rownNum,"Select a Work Order Status", endStatus);
		
		waitForExtJSAjaxComplete(10);
		
		selectHighValuesAreValue(rownNum,highValues);
		
		waitForExtJSAjaxComplete(10);
		
		if(excludeSuspended){
			
			checkExcludeSuspensionTime(rownNum);
		}
		Reporter.log("fill in metric "+metric, true);
	}

	
	/**
	 * Helper method to set metric name 
	 * @param rowNum of metric in grid
	 * @param name to set
	 */
	public void setMetricName(String rowNum ,String name){
		
		clickOnTableCell(  rowNum, getColumnNumberInMetricsGrid("Name"));
		
		waitForExtJSAjaxComplete(10);
		
		setGridLineTextField(name);
		
		waitForExtJSAjaxComplete(10);
		
	}
	
	
	/**
	 * Helper method to set Start  status of a Metric
	 * @param rowNum of metric	
	 * @param windowTitle: title of lookup window
	 * @param status to select
	 */
	public void setStartStatus(String rowNum,String windowTitle, String status){
		
		clickOnTableCell(  rowNum, getColumnNumberInMetricsGrid("Start Status"));
		
		waitForExtJSAjaxComplete(10);
		
		setGridLineLookUpValue( windowTitle ,status, "Name");
	}
	
	/**
	 * Helper method to set End status of a Metric
	 * @param rowNum of metric	
	 * @param windowTitle: title of lookup window
	 * @param status to select
	 */
	public void setEndStatus(String rowNum, String windowTitle, String status){
		
		clickOnTableCell(  rowNum, getColumnNumberInMetricsGrid("End Status"));
		
		waitForExtJSAjaxComplete(10);
		
		setGridLineLookUpValue( windowTitle ,status, "Name");
		
	}
	
	
	/**
	 * Helper method to select value for High Values are dropdown for the metric
	 * @param rowNum of metric
	 * @param value : Good/Bad to select
	 */
	public void selectHighValuesAreValue(String rowNum, String value){
		
		clickOnTableCell(  rowNum, getColumnNumberInMetricsGrid("High Values Are"));
		
		waitForExtJSAjaxComplete(10);
		
		setGridLineSelect(value);
		
	}
	
	/**
	 * Helper method to check Exclude Suspension time for the metric
	 * @param rowNum of metric
	 */
	public void checkExcludeSuspensionTime(String rowNum){
		
		String xpath = SLA_METRICS_GRID_XPATH+"//div[@class='x-grid3-body']/div["+rowNum+"]//td[contains(@class,'x-grid3-check-col-td x-grid3-dirty-cell')]//div[contains(@class,'x-grid3-check-col')]";
		
		WebElement checkbox = driver.findElement(By.xpath(xpath));
		
		String checkBoxClass = checkbox.getAttribute("class");
		
		String classTokens[] = checkBoxClass.split(" ");
		
		if(!classTokens[0].contains("on")){
			
			String columClass = classTokens[1].replace("x-grid3-cc-", "").trim();
			
			clickOnTableCell(rowNum, columClass);
		}
		
	}
	
	public void setTargets(String lowTarget, String highTarget ){
		waitForExtJSAjaxComplete(25);
		setTargetFields(lowTarget,  highTarget,null,null);
		
	}
	
	public void setTargets(String lowTarget, String highTarget,String timetable, String timezone ){
		waitForExtJSAjaxComplete(25);
		setTargetFields(lowTarget,  highTarget, timetable,  timezone);
		
	}
	
	
	
	private void setTargetFields(String lowTarget, String highTarget,String timetable, String timezone){
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		String winID  = this.getWindowIdBasedOnPartialMatch("Target Rule");
			
			//String winHighTargetXpath = "//div[@id='"+winID+"']"+SLA_TARGET_RULES_HIGH_TRAGET;
			//String winLowTargetXpath = "//div[@id='"+winID+"']"+SLA_TARGET_RULES_LOW_TRAGET;
		String winHighTargetXpath = "//span[contains(text(),'Edit Target Rule')]/following::input[@name='highTarget']";
		String winLowTargetXpath = "//span[contains(text(),'Edit Target Rule')]/following::input[@name='lowTarget']";
		
			
			waitAndSendKeys(winHighTargetXpath, highTarget);
			
			waitAndSendKeys(winLowTargetXpath, lowTarget);
			
			if(!(timetable==null||timetable.isEmpty())){
				
			clickLookup("x-panel", "timetable");	
			
			setValueGridLookupWithFiltersWithoutUsingMCSElement("@id", getXWindowId("Select a Time Table"), timetable, "Reference");
			
			}else if(!(timetable==null) && timetable.isEmpty()){
				clearTargetRulesLookupFieldValues("timetable");
			}else{//Do nothing
				
			}
			
			
			if(!(timezone==null||timezone.isEmpty())){
			clickLookup("x-panel", "timezone");		
			setValueGridLookupWithFiltersWithValidationWithScroll("@id", getXWindowId("Select a Time Zone"), timezone, "Label");
			}else if(!(timetable==null) && timezone.isEmpty()){
				clearTargetRulesLookupFieldValues("timezone");
			}else{//Do nothing
				
			}
		}
	
	/**
	 * Helper method to select criteria for a Target rule of WO SLA metric
	 * @param priority to select
	 * @param nature to select
	 * @param site to select
	 */
	public void setWOCriteria(String priority, String nature, String site){
		
		String winID  = this.getWindowIdBasedOnPartialMatch("Target Rule");
		
		String winXpath = "//div[@id="+winID+"]";
		
		setWOPriority("@id", winID, priority, "Reference");
		
		setNature("@id", winID, nature, "Reference");
		
		setSite("@id", winID, site, "Reference");
		
		
	}
	
	
	/**
	 * Helper method to select criteria for a Target rule of Call SLA metric
	 * @param priority to select
	 * @param nature to select
	 * @param site to select
	 */
	public void setCallCriteria(String priority, String nature, String site){
		
		String winID  = this.getWindowIdBasedOnPartialMatch("Target Rule");
		
		String winXpath = "//div[@id="+winID+"]";
		
		setCallPriority("@id", winID, priority, "Reference");
		
		setNature("@id", winID, nature, "Reference");
		
		setSite("@id", winID, site, "Reference");
		
		
	}
	
	
	/**
	 * Helper method to set Priority in Criteria section of Target rule of a WO SLA metric 
	 * @param attribute of the win
	 * @param attributeValue: value of attribute
	 * @param priority to select
	 * @param columnName to be used for selection
	 */
	public void setWOPriority(String attribute, String attributeValue, String priority, String columnName){
		
		clickLookup(attribute, attributeValue, priority, "Select a Work Order Priority");
		
		this.waitForExtJSAjaxComplete(10);
		
		this.setValueGridLookupWithFilters("@id", getXWindowId("Select a Work Order Priority"), priority, columnName );
		
	}
	
	
	/**
	 * Helper method to set Priority in Criteria section of Target rule of a Call SLA metric 
	 * @param attribute of the win
	 * @param attributeValue: value of attribute
	 * @param priority to select
	 * @param columnName to be used for selection
	 */
	public void setCallPriority(String attribute, String attributeValue, String priority, String columnName){
		
		clickLookup(attribute, attributeValue, priority, "Select a Call Priority");
		
		this.waitForExtJSAjaxComplete(10);
		
		this.setValueGridLookupWithFilters("@id", getXWindowId("Select a Call Priority"), priority, columnName );
		
	}
	
	
	/**
	 * Helper method to set Nature in Criteria section of Target rule of a Call SLA metric 
	 * @param attribute of the win
	 * @param attributeValue: value of attribute
	 * @param nature to select
	 * @param columnName to be used for selection
	 */
	public void setNature(String attribute, String attributeValue, String nature, String columnName){
		
		clickLookup(attribute, attributeValue, nature, "Select a Nature");
		
		this.waitForExtJSAjaxComplete(10);
		
		this.setValueGridLookupWithFilters("@id", getXWindowId("Select a Nature"), nature, columnName );
		
	}
	
	
	/**
	 * Helper method to set site in Criteria section of Target rule of a Call SLA metric 
	 * @param attribute of the win
	 * @param attributeValue: value of attribute
	 * @param site to select
	 * @param columnName to be used for selection
	 */
	public void setSite(String attribute, String attributeValue, String site, String columnName){
		
		clickLookup(attribute, attributeValue, site, "Select a Site");
		
		this.waitForExtJSAjaxComplete(10);
		
		this.setValueGridLookupWithFilters("@id", getXWindowId("Select a Site"), site, columnName );
		
	}
	
	
	
	public void saveClose() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", SLA_FORM_CLASS, "button",
				"text()", "Save", "text()", "Close", true, true).click();
	}

	public void cancel() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", SLA_FORM_CLASS, "button", "text()",
				"Cancel", true, true).click();
		waitForExtJSAjaxComplete(25);
	}

	public void copy() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", SLA_FORM_CLASS, "button", "text()",
				"Copy", true, true).click();
		waitForExtJSAjaxComplete(25);
	}


	public void save() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", SLA_FORM_CLASS, "button", "text()",
				"Save", true, true).click();
		waitForExtJSAjaxComplete(25);
	}
	
	public void closeXWindow(){
		McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-resizable')]//div[contains(@class,'x-tool-close')])[last()]").click();
		Reporter.log("close x-window <br>", true);
	}

	
	
	//****************************Grid functions*****************************************
	
	
	/**
	 * Helper method to click on table cell
	 * @param rowNum row number of the row
	 * @param columnClass class of table cell
	 */
	 
	public void clickOnTableCell( String rowNum, String columnNum){
		
		String xpath = SLA_METRICS_GRID_XPATH+"//div[@class='x-grid3-body']/div["+rowNum+"]//td[contains(@class,'x-grid3-td-"+columnNum+"')]";
		
		WebElement ele = McsElement.getElementByXpath(driver, xpath);//.click();
		
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
	}
	
	
	 /**
	  * Helper method to fill text field value of a Grid Element 
	  * 
	  */
	 public void setGridLineTextField(String value){

		 String xpath = SLA_GRID_EDITOR+"//input[@type='text']";

		 WebElement textField = McsElement.getElementByXpath(driver, xpath);
		 
		 textField.clear();

		 textField.sendKeys(value);

		 textField.sendKeys(Keys.ENTER);
	 }

	 /**
	  * Helper method to fill Look up value of a Grid Element 
	  * 
	  */
	 public void setGridLineLookUpValue( String windowTitle ,String name, String column){

		 String xpath = SLA_GRID_EDITOR+"//img[contains(@src,'s.gif')]";

		 waitForExtJSAjaxComplete(20);

		 McsElement.getElementByXpath(driver, xpath).click();

		 waitForExtJSAjaxComplete(20);

		 setValueGridLookupWithFilters("@id", getXWindowId(windowTitle), name, column);

	 }
	 
	 /**
	  * Helper method to select value of a Select box in Grid 
	  * 
	  */
	 public void setGridLineSelect( String value){

		 String xpath = SLA_GRID_EDITOR+"//img[contains(@src,'s.gif')]";

		 waitForExtJSAjaxComplete(20);

		 McsElement.getElementByXpath(driver, xpath).click();

		 waitForExtJSAjaxComplete(20);

		 DropDown.selectComboListItemFromAlreadyPopulatedList(driver, value);

	 }
	 
	 
	 /**
	  * Helper method to return column number based on Column name in Metric's grid
	  * @param columnName of which column number has to be found
	  * @return column number of col
	  */
	 public String getColumnNumberInMetricsGrid(String columnName){
		 
		 String xpath = SLA_METRICS_GRID_XPATH+"//td[contains(@class,'x-grid3-hd')]and .='"+columnName+"'";
		 
		 WebElement columnEle = driver.findElement(By.xpath(xpath));
		 
		 String columnClass = columnEle.getAttribute("class");
		 
		 columnClass = columnClass.replace("x-grid3-hd x-grid3-cell x-grid3-td-", "");
		 
		 return columnClass;
	 }
	
	 /**
	  * Helper method to get row numeber of metric in grid
	  * @param metric 
	  * @return row number of metric
	  */
	 public String getMetricRowNum(String metric){
		
		 String rowNum = "-1"; 
		 
		for(int i=1; i<11;i++){ 
			
		String xpath = SLA_METRICS_GRID_XPATH+ "//div[@class='x-grid3-body']/div["+i+"]//td[contains(@class,'x-grid3-td-') and .='"+metric+"']";
		
			if(McsElement.isElementDisplayed(driver, xpath)){
				
				rowNum = ""+i;
				
				break;
			}
		
		}
		
		return rowNum;
	 }
	
	 /**
		 * Helper method to set Target Rule values
		 * @param metric for which target rule has to be created
		 * @param lowTarget 
		 * @param highTarget
		 * @param timeTable
		 * @param timeZone
		 */
		public void setTargetRuleNative( String metric, String lowTarget, String highTarget, String timeTable, String timeZone){

			//selectTargetRow(metric);
			
			selectTargeRow();
			
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);
			
			setTargets(lowTarget, highTarget, timeTable, timeZone);
			
		}
		
		
		/**
		 * Helper method to select a Metic
		 * @param metric
		 */
		public void selectTargetRow(String metric){
			
			checkRowInGriByTextValueAndDoubleClick(driver, "@id", this.getXWindowId("SLA Objects"), metric);
			
		}
		
		public WebElement selectTargeRow(){
			Actions action=new Actions(driver);
			String xpath = "(//span[contains(text(), 'Metrics')]/following::div[contains(@class,'x-grid3-row-first')])[last()]/..";
			WebElement webElement = driver.findElement(By.xpath(xpath));
			action.doubleClick(webElement).build().perform();
			action.doubleClick(webElement).build().perform();
			return webElement;
			
			//action.moveToElement(driver.findElement(By.xpath(""))).doubleClick().build().perform();
					
		}
		
		/**
		 * 
		 *Double Click on the record in Grid by text value (when there is a parent attribute @class or @id of the grid)
		 *  
		 * @param webDriver WebDriver instance to use
		 * @param textValue for search
		 * @throws NoSuchElementException
		 * @return webelement
		 */
		public WebElement checkRowInGriByTextValueAndDoubleClick(WebDriver webDriver, String parentAttr, String parentAttrVal, String textValue)  {
			
			Actions action=new Actions(driver);
			String xpath = "//div[contains("+parentAttr+",'"+parentAttrVal+"')]//*[@class='x-grid3']//div[text()='"+textValue+"']";
			WebElement webElement = webDriver.findElement(By.xpath(xpath));
			action.doubleClick(webElement).build().perform();
			action.doubleClick(webElement).build().perform();
			Reporter.log("Check element "+textValue+" present in grid and click", true);
			try {Thread.sleep(500);} catch (InterruptedException e) {}
			return webElement;
		}
		
		 /**
		   * Helper method to get error message from target rule window 
		   * @return error message
		   */
		  public String getTargetRulesWindowErrorMsg(){
		   String errMsg = driver.findElement(By.xpath("//div[contains(@class,'x-window-body mcs-dialog')]//ancestor::div[contains(@class,'x-resizable-pinned')]//div[@class='infobar bg-error']")).getText();
		   return errMsg;
		  }
		  
		  /**
		   * Helper method to click on save and close target rule value 
		   */
		  public void saveCloseTargetRules() {
			  String winID  = this.getWindowIdBasedOnPartialMatch("Target Rule");
		   WebElement element = driver.findElement(By.xpath("//div[@id='"+winID+"']//button[contains(text(),'Save') and contains(text(),'Close') and contains(@class,'x-btn-text')]"));
		   javaScriptFocus(element);
		   javaScriptClick(element); 
		   Reporter.log("Save and Close of Target Rules is clicked", true);
		  }

		  /**
		   * Helper method to click on save target rule
		   */
		  public void saveTargetRules() {
			  String winID  = this.getWindowIdBasedOnPartialMatch("Target Rule");
		   WebElement element = driver.findElement(By.xpath("//div[@id='"+winID+"']//button[contains(text(),'Save') and contains(@class,'x-btn-text')]"));
		   javaScriptFocus(element);
		   javaScriptClick(element); 
		   Reporter.log("Save and Close of Target Rules is clicked", true);
		  }
		  
		  
		  /**
		   * Helper method to click on save and close target rule value 
		   */
		  public void closeTargetRules() {
			  String winID  = this.getWindowIdBasedOnPartialMatch("Target Rule");
			  WebElement element = driver.findElement(By.xpath("//div[@id='"+winID+"']//button[contains(text(),'Close') and contains(@class,'x-btn-text')]"));
			  javaScriptFocus(element);
			  javaScriptClick(element); 
			  Reporter.log("Save and Close of Target Rules is clicked", true);
		  }

		  /**
		   * Helper method to clear target rule lookup values 
		   * @param fieldName lookup name 
		   */
		  public void clearTargetRulesLookupFieldValues(String fieldName){
			  String winID  = this.getWindowIdBasedOnPartialMatch("Target Rule");
			  WebElement element = driver.findElement(By.xpath("//div[@id='"+winID+"']//input[@name='"+fieldName+"']/following-sibling::input[contains(@class,'x-form-field')]"));
			  element.clear();
			  element.sendKeys(Keys.RETURN);
			  Reporter.log(""+fieldName+" lookup value is cleared", true);
		  }
		  
		  public void expandModuleSettings() {

			  waitForExtJSAjaxComplete(10);
			  expandNode("div","@id",getXPanelId("Administration"),"Module Settings");
		  }
		  
		  public void expandHelpDesk() {
			  expandSubNode("div","@id",getXPanelId("Administration"),"HelpDesk");
		  }
		  
		  /**
		   * helper method to get sla object window error
		   * @param windowName
		   * @return error message
		   */
		 public String getSLAObjectWindowError(String windowName){
			return driver.findElement(By.xpath("//div[@id='"+getXWindowId(windowName)+"']//div[contains(@class,'infobar bg-error')]")).getText();
		  }
}
