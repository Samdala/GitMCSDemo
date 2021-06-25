package test.generalweb.calltemplates;



import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.McsElement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class CallTemplatesPageObject extends AbstractMcsTestSuite {

	protected final String ADMINISTRATION_PANEL_CLASS = "x-panel-body x-panel-body-noborder";
	
	protected final String CALL_SMS_TEMPLATE_RIGHT_PANEL="//div[contains(@class,'admsettings')]//div[contains(@class,'x-panel-noborder') and (contains(@style,'border-right-width'))]";
	
	protected final String CALL_SMS_TEMPLATE_LEFT_PANEL="//div[contains(@class,'admsettings')]//div[contains(@class,'x-panel-noborder') and (contains(@style,'border-left-width'))]";
	
	protected final String TEMPLATES_PANEL_CLASS="//div[contains(@class, 'mcs-tree-navigation')]/following-sibling::div[contains(@class, 'x-panel-body')]";
	
	protected final String FORM_TEMPLATE_NAME = "Call Template";
	
	protected final String TEMPLATE_PANEL = "Call Templates";
	
	protected final String ADMINISTRATION_WINDOW_CLASS="//div[contains(@class,'admsettings')]";

	public void goToAdministration() {
		driver.get(configuration.getApplicationUrl()
				+ "/frame.php?relay=ADM_SETTINGS");
	};

	public void expandModuleSettings() {
		expandNode("div","@id",getXPanelId("Administration"),"Module Settings");
		}
	
	
	public void expandNotifications() {
		expandNode("div","@id",getXPanelId("Administration"),"Notifications");
		}
	
	public void clickAdministration() {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "mcs-tb-glossy-strong", "button", "text()",
				"Administration", true, true).click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click Administration"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	public void clickCallTemplate() {
		Timer timer = new Timer().start();
		WebElement callTemplate = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@id", getXPanelId("Administration"), "span", "text()",
				"Call Templates", true, true);
		javaScriptFocus(callTemplate);
		callTemplate.click();
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click call template"+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void clickAddTemplate() {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXPanelId(TEMPLATE_PANEL),"button", "@class",
				"x-btn-text icon-template-add", true, true).click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click add template"+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void clickDeleteTemplate() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXPanelId(TEMPLATE_PANEL),"button", "@class",
				"x-btn-text icon-template-delete", true, true).click();
		Reporter.log("Click delete template", true);
	}
	

	public void clickCopyTemplate() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXPanelId(TEMPLATE_PANEL),"button", "@class",
				"x-btn-text icon-template-copy", true, true).click();
		Reporter.log("Click copy template", true);
	}
	

	public void setCopyReference(String referenceText) {
		WebElement reference = McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window')]//input[contains(@class,'ext-mb-input')]");
		reference.clear();
		reference.sendKeys(referenceText);
		Reporter.log("Set copy reference", true);
	}
	
	
	public void clickAddGroupTemplate() {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXPanelId(TEMPLATE_PANEL),"button", "@class",
				"icon-templategroup-add", true, true).click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click add group template"+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void clickDeleteGroupTemplate() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXPanelId(TEMPLATE_PANEL),"button", "@class",
				"icon-templategroup-delete", true, true).click();
		Reporter.log("Click delete group template", true);
	}

	
	public void clickTemplateGroupsTab(){
    String xpath =  "//span[text()='Template Groups']";
		
		WebElement ele = driver.findElement(By.xpath(xpath));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		ele.click();
		//McsElement.getElementByXpath(driver, "//span[text()='Template Groups']").click();
	}
	
	public void clickRestrictionTab(){
		McsElement.getElementByXpath(driver, "//span[text()='Restrictions']").click();
	}
	
	public void clickLocationsTab(){
		McsElement.getElementByXpath(driver, "//span[text()='Locations']").click();
	}
	
	public void clickAddLocation(){
		McsElement.getElementByXpath(driver, "(//button[contains(@class,'icon-row-add') and text()='Add'])[last()]").click();
	}
	

	public void clickRemoveLocation(){
		McsElement.getElementByXpath(driver, "(//button[contains(@class,'icon-row-remove') and text()='Remove'])[last()]").click();
	}
	
	
	public void clickBuildingsTabLocationForm(){
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window')]//span[text()='Buildings']").click();
	}


	public void clickSitesTabLocationForm(){
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window')]//span[text()='Sites']").click();
	}


	public void clickOtherTabLocationForm(){
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window')]//span[text()='Other']").click();
	}

	public void expandDetailsXwindow(){
		if (!McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-window')]//div[contains(@class,'x-tool-expand-east')]/..)").getAttribute("style").toLowerCase().contains("none"))
		{
			McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-window')]//div[contains(@class,'x-tool-expand-east')])").click();
		}
	}
	
	public void clickAddOnTemplateGroupsTab(){
		McsElement.getElementByXpath(driver, "(//button[contains(@class,'icon-row-add') and text()='Add'])[last()]").click();
	}
	

	public void clickRemoveOnTemplateGroupsTab(){
		McsElement.getElementByXpath(driver, "(//button[contains(@class,'icon-row-remove') and text()='Remove'])[last()]").click();
	}
	
	public void expandAllNodeExtJsTree(String attribute, String attributeValue){
		Timer timer = new Timer().start();
		
		String id = driver
				.findElement(
						By.xpath("//*[contains("+attribute+",'"+attributeValue+"')]//*[contains(@class,'x-panel ') and contains(@class,'x-panel-noborder x-tree')]"))
				.getAttribute("id");
		String script = "Ext.getCmp('"+id+"').expandAll()";
		
		((JavascriptExecutor) driver).executeScript(script);
		
		waitForExtJSAjaxComplete(25);
		
		Reporter.log("all nodes in extjstree were expanded" + " (" + timer.stop()
				+ "ms)", true);
		
	}
	
	public void clickActiv() {
		McsElement.getElementByXpath(driver, "//span[contains(@class,'x-panel') and contains(text(),'Templates')]/../..//input[contains(@class,'x-form-checkbox')]").click();
	}
	
	public boolean getActivState() {
		if (McsElement.getElementByXpath(driver, "//span[contains(@class,'x-panel') and contains(text(),'Templates')]/../..//label[text()='Show on Portal']//preceding-sibling::input[contains(@class,'x-form-checkbox')]").getAttribute("checked") == null) {
			return false;
		} else {
			return true;
		}
	}

	public void setReference(String referenceText) {
	WebElement reference = McsElement.getElementByXpath(driver, "//span[(@class='x-panel-header-text') and text()='Call Templates']/../..//label[contains(text(),'Reference:')]//..//input");
	
		reference.clear();
		reference.sendKeys(referenceText);
		Reporter.log("Set reference", true);
	}

	public void setReferenceGroup(String referenceText) {
		WebElement reference = McsElement.getElementByXpath(driver, "//span[text()='Template Group']/../../../../../../../../../../../..//label[contains(text(),'Reference:')]//..//input");
				
		reference.clear();
		reference.sendKeys(referenceText);
		Reporter.log("Set reference group", true);
	}

	
	
	public String getReference() {
		return McsElement.getElementByXpath(driver, "//span[(@class='x-panel-header-text') and text()='Call Templates']/../..//label[contains(text(),'Reference:')]//..//input").getAttribute("value");
		
	}
	
	public String getReferenceGroup() {
		return McsElement.getElementByXpath(driver, "//span[text()='Template Group']/../../../../../../../../../../../..//label[contains(text(),'Reference:')]//..//input").getAttribute("value");
		
	}
	
	public void saveCallTemplate() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//span[(@class='x-panel-header-text') and text()='Call Templates']/../..//span[contains(text(),'Save Changes')]").click();
		
		waitForExtJSAjaxComplete(20);
		Reporter.log("Save call template"+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void saveCallTemplateGroup() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//span[text()='Template Group']/../../../../../../../../../../../../../../..//span[contains(text(),'Save Changes')]").click();
		
		waitForExtJSAjaxComplete(20);
		Reporter.log("Save call template group"+ " (" + timer.stop()
				+ "ms)", true);
	}

	/**
	 * Helper method to add call template group
	 * @param callTemplateReference : template group reference to enter
	 */
	public void addTemplateGroup(String callTemplateReference){
		
		clickAddGroupTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		setReferenceGroup(callTemplateReference);
		
		saveCallTemplateGroup();
		
	}
	
	/**
	 * Helper method to add active call template
	 * @param callTemplateReference : template reference to enter
	 */
	public void addActiveTemplate(String callTemplateReference){
		
		clickAddTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(callTemplateReference);
		
		//By Default Checkbox is checked
		if(!getActivState()){
		clickActiv();
		}
		
		saveCallTemplate();
		
	}
	
	
	/**
	 * Helper method to add inactive call template
	 * @param callTemplateReference : template reference to enter
	 */
	public void addInactiveTemplate(String callTemplateReference){
		
		clickAddTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(callTemplateReference);
		
		//By Default Checkbox is checked
		if(getActivState()){
		clickActiv();
		}
		
		saveCallTemplate();
		
	}
	
	/**
	 * Helper method to select  call Template 
	 */
	public void selectCallTemplate(String callName)
	{
	
	WebElement xpath=McsElement.getElementByXpath(driver, TEMPLATES_PANEL_CLASS+"//span[text()='"+callName+"']");
	
	xpath.click();
	
	}
	
	////////////////// Security tab methods //////////////////////////////
	
	/**
	 * Helper method to click on tabs in template window
	 * @param windowTitle of the template window
	 * @param tabText text of tab to be clicked
	 */
	public void clickOnTab(String tabText){
		
		McsElement.getElementByXpath(driver , TEMPLATES_PANEL_CLASS+"//span[contains(@class,'x-tab-strip-text') and contains(text(),'"+tabText+"')]").click();
	}
	
	
	/**
	 * Helper Method to Click security Tab
	 */
	public void clickSecurityTab(){
		clickOnTab("Security"); 
	}
	

	/**
	 * Helper method to set Account for the call
	 * @param name
	 */
	public void setAccount(String id){
		
		
		clickAddAccountButton();
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters("@class", "x-window-noborder", id, "ID");
		
		Reporter.log("select account name "+id, true);
	}
	
	/**
	 * Helper method to click Add Account button
	 */
	
	public void clickAddAccountButton()
	{
		McsElement.getElementByXpath(driver,"//button[contains(@class,'x-btn-text icon-account') and text()='Add Account']").click();;
	}
	
	/**
	 * Helper method to select account 
	 */
	public void selectAccount()
	{
	String xpath="//img[contains(@src,'icon-account')]";
	McsElement.getElementByXpath(driver, xpath).click();
	}
	
	/**
	 * Helper method to remove account in the security tab
	 */
	public void clickOnRemove()
	{
		String xpath="//div[contains(@class,'x-tab-panel-noborder')]//div[contains(@class,'x-panel-tbar-noheader')]//button[contains(@class,'x-btn-text icon-row-remove') and text()='Remove']";
		
		McsElement.getElementByXpath(driver,xpath).click();
		
	}
	/**
	 * Helper method to click on sms template
	 */
	
	public void clickSMSTemplate() {
		Timer timer = new Timer().start();
		WebElement callTemplate =McsElement.getElementByXpath(driver,"//div[@id='"+getXPanelId("Administration")+"']//span[text()='HelpDesk']/../../..//span[text()='SMS Templates']");
				
		javaScriptFocus(callTemplate);
		callTemplate.click();
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click sms template"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	public void UncheckEnableSMSCalls()
	{
	
		WebElement element = McsElement.getElementByXpath(driver,CALL_SMS_TEMPLATE_RIGHT_PANEL+"//label[text()='Enable Call SMS']/preceding-sibling::input[@type='checkbox']");

		if(!element.isSelected())
		{
			Reporter.log("Enable SMS calls check box is unchecked <br>", true);	
		}else{
		
		element.click();}
	}
	
	/**
	 * Helper method to check enable sms templates
	 */
	
	public void CheckEnableSMSCalls()
	{
	
		WebElement element = McsElement.getElementByXpath(driver,CALL_SMS_TEMPLATE_RIGHT_PANEL+"//label[text()='Enable Call SMS']/preceding-sibling::input[@type='checkbox']");

		if(!element.isSelected())
		{
			element.click();	
		}else{
		
		Reporter.log("Enable SMS calls check box is checked <br>", true);}
	}
	
	/**
	 * Helper method to inactive sms template
	 */
	
	public void checkInActivSMSTemplate() {
		WebElement element=McsElement.getElementByXpath(driver, CALL_SMS_TEMPLATE_LEFT_PANEL+"//label[text()='Active']/preceding-sibling::input[@type='checkbox']");
	
		if(!element.isSelected())
		{
			Reporter.log("Active check box is unchecked <br>", true);
			
		}else{
		
		element.click();}
	}
	
	/**
	 * Helper method to inactive sms template
	 */
	
	public void checkActivSMSTemplate() {
		WebElement element=McsElement.getElementByXpath(driver, CALL_SMS_TEMPLATE_LEFT_PANEL+"//label[text()='Active']/preceding-sibling::input[@type='checkbox']");
	
		if(!element.isSelected())
		{
			element.click();
		}else{
			
		Reporter.log("Active check box is checked <br>", true);}
	}
	
	/**
	 * Helper method to select  SMS Template 
	 */
	public void selectSMSTemplate(String tempName)
	{
	
	String xpath= CALL_SMS_TEMPLATE_RIGHT_PANEL+"//div[contains(@class,'listitem') and text()='"+tempName+"']";
	
	McsElement.getElementByXpath(driver,xpath).click();
	}
	
	/**
	 * Helper method to click save changes for sms template
	 */
	
	public void clickSaveChangesForSMSTemplates()
	{
	
	String xpath=CALL_SMS_TEMPLATE_LEFT_PANEL+"//button[text()='Save Changes']";
	
	McsElement.getElementByXpath(driver,xpath).click();
	
	}

	/**
	 * check call template field visible
	 * @param fieldName
	 */
	public void checkCallTemplatesFieldsVisibility(String fieldName){

		String checkedvisibilityXPath = "//div[text()='"+fieldName+"']/../following-sibling::td[1][contains(@class,'x-grid3-check-col-td')]";
		String unCheckedVisibilityXPath = "//span[text()='"+fieldName+"']/../../following-sibling::td[1]//div[contains(@class,'x-grid3-check-col')]";

		if(!(McsElement.isElementPresent(driver, checkedvisibilityXPath))){
			waitForExtJSAjaxComplete(20);
			click(unCheckedVisibilityXPath);
			waitForExtJSAjaxComplete(20);
			saveCallTemplate();
			waitForExtJSAjaxComplete(20);
		}
		Reporter.log(""+fieldName+" is checked visible",true);
	}

	/**
	 * Uncheck call template field visible
	 * @param fieldName
	 */
	public void unCheckCallTemplatesFieldsVisibility(String fieldName){
		String checkedvisibilityXPath = "//div[text()='"+fieldName+"']/../following-sibling::td[1]//div[contains(@class,'x-grid3-check-col-on')]";
		String unCheckedVisibilityXPath = "//span[text()='"+fieldName+"']/../../following-sibling::td[1][contains(@class,'x-grid3-check-col-td')]";

		if(!(McsElement.isElementPresent(driver, unCheckedVisibilityXPath))){
			click(checkedvisibilityXPath);
			waitForExtJSAjaxComplete(20);
			saveCallTemplate();
			waitForExtJSAjaxComplete(20);
		}
		Reporter.log(""+fieldName+" is Unchecked visible",true);
	}
	
	/**
	 * Helper method to set location in template
	 * @param locationReference
	 */
	public void setLocation(String locationReference) {
		waitForExtJSAjaxComplete(5);
		setValueGridLookupWithFiltersNewUI("@class", "x6-window-default-closable", locationReference, "Reference");
		waitForExtJSAjaxComplete(5);
		Reporter.log("Set HostLocation - "+locationReference, true);

	}
}
