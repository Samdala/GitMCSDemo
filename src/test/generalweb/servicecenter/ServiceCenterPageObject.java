package test.generalweb.servicecenter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class ServiceCenterPageObject extends AbstractMcsTestSuite {



	protected final String XPATH_DISPATCHER_WINDOW = "//div[contains(@class, 'servicecenter')]//div[contains(@class, 'x-form-label-top x-border-panel')]";
	
	protected final String XPATH_SERVICE_SPECIFICATIONS_MESSAGE = XPATH_DISPATCHER_WINDOW+"//label[contains(text(), 'Service Specifications')]/following-sibling::div//div[contains(@class, 'x-form-display-field x-item-disabled') and not (contains(@class, 'x-hide-display'))]";
	
	protected final String EDIT_SITE_WINDOW_HEADER = "Edit Site";
	
	protected final String ACTION_HISTORY_FOR_CALL_WINDOW_HEADER = "Action History for Call";
	
	protected final String SEND_NOTIFICATION_WINDOW = "//div[contains(@class, 'x-tab-panel x-border-panel')]//div[contains(@class,'x-panel x-panel-noborder')]";	
	
	protected final String XPATH_CALLS_TAB_CALLDETAILS_WINDOW = "//div[@id='hdtabpanel']//div[contains(@class, 'x-tab-panel-body-top')]";
	
	/**
	 * Helper method to click New call Button in Dispatcher Window
	 */
	public void clickNewcall(){
		McsElement.getElementByXpath(driver, XPATH_DISPATCHER_WINDOW+"//button[text()='New Call']").click();
		Reporter.log("New Call Button is clicked", true);
	}
	
	/**
	 * Helper method to verify Dispatcher Tab is displayed
	 */
	public boolean isDispatcherTabPresent(){
		return McsElement.isElementDisplayed(driver, "//div[contains(@class, 'servicecenter')]//span[contains(@class, 'x-panel-header-text') and text()='Dispatcher']");
	}
	
	/**
	 * Helper method to Enter description in Dispatcher Window
	 */
	public void setDescription(String value){
		WebElement description = McsElement.getElementByXpath(driver, XPATH_DISPATCHER_WINDOW+"//textarea[@name='description']");
		description.click();
		description.clear();
		description.sendKeys(value);
		Reporter.log("Description is set ", true);
	}
	
	/**
	 * Helper method to Get Description 
	 */
	public String getDescription(String windowOrPanel){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//input[@name='location']/..//input)[last()]").getAttribute("value");
	}	
	
	/**
	 * Helper method to set Location
	 * WindowPanel = 'servicecenter'
	 * tabName = Building/Sites/Other
	 */
	public void setLocation(String tabName, String reference) {
		clickLookupNewUI("servicecenter", "location");
		
		//clickXPath("//div[contains(@class,'x-resizable')]//span[text()='"+tabName+"']");
		
		setValueGridLookupWithFiltersNewUI("@class", "x6-window-closable", reference, "Reference");
		waitForExtJSAjaxComplete(25);
		Reporter.log("location "+ reference+" was set", true);
	}
	
	/**
	 * Helper method to Get Location 
	 */
	public String getLocation(){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'servicecenter')]//input[@name='location']/..//input)[last()]").getAttribute("value");
	}	
	
	/**
	 * Helper method to set Caller
	 * WindowPanel = 'servicecenter'
	 */
	public void setCaller(String name) {
		clickLookup("servicecenter", "caller");
		waitForExtJSAjaxComplete(25);
		setValueGridLookupWithFiltersWithoutUsingMCSElementxpathGenerator("@id" , getXWindowId("Select a Caller"), name, "Name");
		waitForExtJSAjaxComplete(25);
		Reporter.log("Caller "+ name+" was set", true);
	}
	
	/**
	 * Helper method to Get Caller 
	 */
	public String getCaller(){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'servicecenter')]//input[@name='caller']/..//input)[last()]").getAttribute("value");
	}	
	
	/**
	 * Helper method to set Nature
	 * @param nature
	 */
	public void setNature(String nature) {
		clickLookup("@class", "servicecenter", "nature", "Select a Nature");
		waitForExtJSAjaxComplete(25);
		setValueTreeLookup(new String[]{nature});
		Reporter.log("Nature was selected", true);
	}
	
	/**
	 * Helper method to Get Nature 
	 */
	public String getNature(){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'servicecenter')]//input[@name='nature']/..//input)[last()]").getAttribute("value");
	}	
	
	/**
	 * Helper method to set Customer
	 * WindowPanel = 'servicecenter'
	 */
	public void setCustomer(String reference) {
		clickLookup("servicecenter", "customer");
		setValueGridLookupWithFilters("@id" , getXWindowId("Select a Customer"), reference, "Customer Name");
		waitForExtJSAjaxComplete(25);
		Reporter.log("Customer "+ reference+" was set", true);
	}
	
	/**
	 * Helper method to get Customer
	 */
	public String getCustomer(){
		 return McsElement.getElementByXpath(driver, "(//div[contains(@class,'servicecenter')]//input[@name='customer']/..//input)[last()]").getAttribute("value");
	}	
	
	
	/**
	 * Helper method to verify Service Specification Display Message
	 */
	public String getServiceSpecificationMessage(){
		boolean status = false;
		status = McsElement.isElementPresent(driver, XPATH_SERVICE_SPECIFICATIONS_MESSAGE);
		if(status){
			return McsElement.getElementByXpath(driver, XPATH_SERVICE_SPECIFICATIONS_MESSAGE).getText();
		} else{
			return "ServiceSpecification Message is not Present";
		}
	}
	
	/**
	 * Helper method to get Service Specification Values
	 */
	public String getServiceSpecificationValues(int i){
		String ele = driver.findElement(By.xpath(XPATH_DISPATCHER_WINDOW+"//label[contains(text(), 'Service Specifications')]/following-sibling::div//div[contains(@class, 'spec-item-wrap')]")).getText();
		String [] serviceValues = ele.split("<br/>");
		return serviceValues[i];
	}
	
	/**
	 * Helper method to verify Call Details Tab is Collapsed
	 */
	public boolean collapseCallDetails(){
		String xpath = "//div[contains(@class, 'x-border-panel')]//div[contains(@class, 'x-tool-toggle x-tool-collapse-south')]";
		boolean status = McsElement.isElementDisplayed(driver, xpath);
		
		if(status){
			driver.findElement(By.xpath(xpath)).click();
			waitForExtJSAjaxComplete(10);
		}
		Reporter.log("Call Details Tab is Set to Collapsed", true);
		return status;
	}
	
	/**
	 * Helper method to click on top Section Tabs 
	 */
	public void clickOnServiceTabs(String liAttributeName, String tabName){
		WebElement element = driver.findElement(By.xpath("//div[@id='hdtabpanel']//li[contains(@id, '"+liAttributeName+"')]//span[contains(text(), '"+tabName+"')]"));
		element.click();
		Reporter.log("Clicked on "+tabName+"..", true);
	}
	
	/**
	 * Helper method to click on Call Detail Tabs 
	 */
	public void clickOnCallDetailTabs(String liAttributeName, String tabName){
		WebElement element = driver.findElement(By.xpath("//div[contains(@class, 'x-tab-panel x-border-panel')]//li[contains(@id, '"+liAttributeName+"')]//span[contains(text(), '"+tabName+"')]"));
		element.click();
		Reporter.log("Clicked on "+tabName+"..", true);
	}
	
	/**
	 * Helper method to clear Dispatcher Fields
	 */
	public void clearDispatcherFields(String windowOrPanel, String attrValue){
		WebElement ele = McsElement.getElementByXpath(driver, "(//div[contains(@class,'servicecenter')]//input[@name='"+attrValue+"']/..//input)[last()]");
		ele.click();
		ele.clear();
		Reporter.log("Dipatcher Field is cleared "+attrValue+":: ", true);
	}
	
	/**
	 * Helper method to Enter and Verify Search Result 
	 */
	public boolean enterAndVerifyLocationSearchResult(String windowOrPanel, String attrValue, String location){
		WebElement ele = McsElement.getElementByXpath(driver, "(//div[contains(@class,'"+windowOrPanel+"')]//input[@name='"+attrValue+"']/..//input)[last()]");
		ele.click();
		ele.clear();
		ele.sendKeys(location);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		
		return McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-combo-list') and contains(@style, 'visibility: visible;')]//div[text()= '"+location+"']").isDisplayed();
	}
	
	//****************** Find Calls Tab Related **************//
	/**
	 * Helper method to click Check Box in Find Calls Tab
	 */
	public void clickCheckBoxInFindCallsTab(String attributeName){
		WebElement element = driver.findElement(By.xpath(XPATH_CALLS_TAB_CALLDETAILS_WINDOW+"//input[@name='"+attributeName+"']"));
		String value = element.getAttribute("checked");
		
		boolean status = value.contains("true")?true:false;
		
		if(status){
			Reporter.log("Checkbox "+attributeName+" is already clicked", true);
		}else{
			element.click();
		}
		Reporter.log("Checkbox "+attributeName+" is clicked", true);
	}
	
	/**
	 * Helper method to enter CallID
	 */
	public void setCallIDInFindCallsTab(String attributeName, String value){
		WebElement ele = McsElement.getElementByXpath(driver, XPATH_CALLS_TAB_CALLDETAILS_WINDOW+"//input[@name='"+attributeName+"']");
		ele.click();
		ele.clear();
		ele.sendKeys(value);
		ele.sendKeys(Keys.RETURN);
		Reporter.log("Call ID is set and Filtered ", true);
	}
	
	/**
	 * Helper method to enter External Reference
	 */
	public void setExternalRefInFindCallsTab(String attributeName, String value){
		WebElement ele = McsElement.getElementByXpath(driver, XPATH_CALLS_TAB_CALLDETAILS_WINDOW+"//input[@name='"+attributeName+"']");
		ele.click();
		ele.clear();
		ele.sendKeys(value);
		ele.sendKeys(Keys.RETURN);
		Reporter.log("External Reference is set and Filtered ", true);
	}
	
	/**
	 * Helper method to set status Class
	 */
	public void setStatusClass(String status){
		String id = driver.findElement(By.xpath("//div[contains(@class, 'x-superboxselect-display-btns')]//div[contains(@class, 'x-superboxselect-btn-expand')]")).getAttribute("id");
		DropDown.setExtJsComboValue(driver,id, status);
		Reporter.log("Status Class is selected ", true);
	}
	
	/**
	 * Helper method to get Status Class
	 */
	public String getStatusClass(){
		return McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-superboxselect-display-btns')]//li[contains(@class, 'x-combo-list x-superboxselect-item')]").getText();
	}
	
	/**
	 * Helper method to click Change Visible Columns
	 */
	public void clickChangeVisibleColumns(){
		McsElement.getElementByXpath(driver, XPATH_CALLS_TAB_CALLDETAILS_WINDOW+"//div[@class='x-panel']//button[contains(@class, 'icon-grid-columns')]").click();
		Reporter.log("Change Visible Columns is clicked ", true);
	}
	
	/**
	 * Helper method to set From the Last Units
	 */
	public void setFromTheLastUnits(String value){
		String id = driver.findElement(By.xpath(XPATH_CALLS_TAB_CALLDETAILS_WINDOW+"//input[@name='fromTheLast_unit']")).getAttribute("id");
		DropDown.setExtJsComboValue(driver,id, value);
		Reporter.log("From The Last Unit value is selected ", true);
	}
	
	/**
	 * Helper method to From the last Quantity
	 */
	public void setFromTheLastQuantity(String value){
		WebElement ele = McsElement.getElementByXpath(driver, XPATH_CALLS_TAB_CALLDETAILS_WINDOW+"//input[@name='fromTheLast_quantity']");
		ele.click();
		ele.clear();
		ele.sendKeys(value);
		Reporter.log("From The Last Quantity is selected", true);
	}
	
	/**
	 * Helper method to verify Alert Message in Service Specification
	 */
	public String verifyMessageInServiceSpecification() {
		return McsElement.getElementByXpath(driver, XPATH_CALLS_TAB_CALLDETAILS_WINDOW+"//div[@class='x-panel']//label").getText();
	}
	
	/*******************Service specifications Tab*************/
	
	/**
	 * Helper method to get Service Description
	 */
	public String getServiceDescriptionServiceSpecificationsTab(){
		return McsElement.getElementByXpath(driver, "//div[contains(@class, 'servicecenter')]//label[contains(text(), 'Service Description')]/following-sibling::div//div[contains(@class, 'x-form-text')]").getText();
	}
	
	/**
	 * Helper method to get Dispatching Instructions
	 */
	public String getDispatchInsServiceSpecificationsTab(){
		return McsElement.getElementByXpath(driver, "//div[contains(@class, 'servicecenter')]//label[contains(text(), 'Dispatching Instructions:')]/following-sibling::div//div[contains(@class, 'x-form-text')]").getText();
	}
	
	/**
	 * Helper method to click on OK button
	 */
	public void clickOKBtnServiceSpecificationsTab(){
		McsElement.getElementByXpath(driver, "//div[contains(@class, 'servicecenter')]//div[contains(@class, 'contacts-grid')]//button[text()='Contact OK']").click();
		Reporter.log("Contact OK Button is clicked", true);
	}
	
	/**
	 * Helper method to click NOt OK button
	 */
	public void clickNotOKBtnServiceSpecificationsTab(){
		McsElement.getElementByXpath(driver, "//div[contains(@class, 'servicecenter')]//div[contains(@class, 'contacts-grid')]//button[text()='Contact not OK']").click();
		Reporter.log("Contact Not OK Button is clicked", true);
	}
	
	/**
	 * Helper method to click on Escalate button
	 */
	public void clickEscalateBtnServiceSpecificationsTab(){
		McsElement.getElementByXpath(driver, "//div[contains(@class, 'servicecenter')]//div[contains(@class, 'contacts-grid')]//button[text()='Escalate']").click();
		Reporter.log("Escalate Button is clicked", true);
	}
	
	/**
	 * Helper method to select Language
	 */
	public void setLanguage(String value){
		String id = driver.findElement(By.xpath("//div[@id='hdtabpanel']//div[contains(@class, 'x-tab-panel-body-top')]//div[@class='x-panel']//div[contains(@class, 'x-box-inner')]//input")).getAttribute("id");
		DropDown.setExtJsComboValue(driver,id, value);
		Reporter.log("Language is selected ", true);
	}
	
	/**
	 * Navigate to home page and relogin
	 */
	public void navigateToMainPage(String NAME_FOR_RIGHT,String PASSWORD_FOR_RIGHT) {
		Timer timer = new Timer().start();
		//forceLogout();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		logout();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		login(NAME_FOR_RIGHT, PASSWORD_FOR_RIGHT);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Login to back-end under account "+NAME_FOR_RIGHT +" END\n", true);
		Reporter.log("<br />");
		Reporter.log("Before test method END"+ " (" + timer.stop() + "ms)\n", true);
		Reporter.log("<br />");
	}
	
	
	/***********Calls Tab **********************************/
	/**
	 * Helper method to Set Nature
	 * @param nature
	 */
	public void setNatureCallDetailsTab(String parentAttribute, String parentAttributeValue, String inputName, String nature) {

		WebElement ele = driver.findElement(By.xpath("//div[contains("+parentAttribute+",'"
				+ parentAttributeValue + "')]//input[contains(@name,'"
				+ inputName + "')]//..//..//button"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			ele.click();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		//clickLookup("@class","details-general", "NEW_NATURE","Select a Nature");
		
		waitForExtJSAjaxComplete(25);
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//span[contains(text(),'Tree')]")).click();
		waitForExtJSAjaxComplete(15);
		setValueTreeLookup(new String[]{nature});
		Reporter.log("Nature is Set in Call Details Tab ", true);
	}
	
	/**
	 * Helper method to Book Call
	 */
	public void clickBookCall(){
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class,'helpdesk')]//button[contains(text(),'Book Call')]"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			ele.click();
		} catch(Exception e){
			e.printStackTrace();
		}
		Reporter.log("Book call was clicked <br>", true);
	}
	
	/**
	 * Helper method to verify Actions Tab is Disabled/Active
	 */
	public String CallDetailsTabStatuses(String attributeName){
		return McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-tab-panel x-border-panel')]//li[contains(@id, '"+attributeName+"')]").getAttribute("class");	
	}
	
	/**
	 * Helper method to click Possible actions
	 * @param Action
	 */
	public void clickPossibleActions(String Action){
    	clickXPath("//div[contains(@class, 'x-tab-panel x-border-panel')]//em[contains(text(),'"+Action+"')]");
    	Reporter.log("action type was clicked <br>", true);
	}
	
	/**
	 * Helper method to check send notification field
	 */
	
	public void checkSendNotification() {
		WebElement element = McsElement.getElementByXpath(driver,"//div[contains(@class, 'x-tab-panel x-border-panel')]//label[contains(@class,'x-form-cb-label') and text()='Send Notification']/preceding-sibling::input");
		if (!element.isSelected()) {
			element.click();
		}
		Reporter.log("Send notification check box is checked <br>", true);
	}
	
	/**
	 * Helper method to click on Add Action button for a call
	 */
	public void clickAddActionForCall() {
	    WebElement ele = driver.findElement(By.xpath("//div[contains(@class, 'x-tab-panel x-border-panel')]//div[@class=' x-panel x-panel-noborder']//button[text()='Add Action']"));
	    try{
	    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
	    	ele.click();
	    } catch(Exception e){
	    	e.printStackTrace();
	    }
	    Reporter.log("Add Action For Call Button is Clicked", true);
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
	 * Helper method to click on Tabs in Lookup window
	 * @param tabText : text of tab to click
	 */
	public void clickOnTab(String tabText){
		
		WebElement element = driver.findElement(By.xpath("//div[contains(@class, 'hdwo-details')]//span[contains(@class,'x-tab-strip-text') and contains(text(),'"+tabText+"')]//ancestor::a"));
		
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
	 * Helper method to click On History Details
	 */
	public void clickHistoryDetails(){
		McsElement.getElementByXpath(driver, "//div[contains(@realid, 'HistoryGrid')]//button[contains(text(), 'History Details')]").click();
		Reporter.log("History Details Tab is clicked ", true);
	}
	
	/**
	 * Helper method to get Call ID from History Details Header
	 */
	public String getCallIDFromHistoryDetailsHeader(){
		String text = McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-window x-resizable-pinned') and contains(@style, 'visibility: visible;')]//span[contains(text(), 'Action History')]").getText();
		text = text.substring(text.lastIndexOf(" "), text.length()).trim();
		Reporter.log("Call Id is "+text+" ", true);
		return text;
	}
	
	/**
	 * Helper method to verify History Details
	 */
	public String verifyHistoryDetails(){
		return McsElement.getElementByXpath(driver, "//div[contains(@id, '"+getXWindowId(ACTION_HISTORY_FOR_CALL_WINDOW_HEADER)+"')]//div[contains(@class, 'x-grid3-col-newValue')]").getText();
	}
	
	/**
	 * Checks whether the field is displayed and is read only(label)
	 * @param fieldName
	 * @return
	 */
	public boolean isFieldReadOnlyInActionsWindow(String attribute, String attributeValue ){

 		String field_Value_Xpath = "//div[contains(@class, 'actions-dialog')]//"+attribute+"[contains(@name, '"+attributeValue+"')]";
		String classValue = McsElement.getElementByXpath(driver, field_Value_Xpath).getAttribute("class");
		if(classValue.contains("x-item-disabled")){
			return true;
		} 
		
		return false;
	}
	
	/**
	 * Helper method to verify date and time field in Action tab is disabled
	 */
	public boolean isDateAndTimeFieldDisabled(String attributeValue){

 		String field_Value_Xpath = "//div[contains(@class, 'x-panel-noborder x-border-panel')]//td[contains(@class, '"+attributeValue+"')]//input";
		String classValue = McsElement.getElementByXpath(driver, field_Value_Xpath).getAttribute("disabled");
		if(classValue.contains("true")){
			return true;
		} 
		return false;
	}
	
	/**
	 * Helper method to verify Call ID displayed on Add Action tab
	 */
	public String verifyCallIdInAddActionTab(){
		String text = McsElement.getElementByXpath(driver, "//div[@id='hdtabpanel']/following-sibling::div[contains(@class, 'x-tab-panel x-border-panel')]//div[contains(@class, 'x-panel x-panel-noborder') and not (contains(@class, 'x-hide-display'))]//div[contains(@class, 'xtb-text') and (starts-with(text(), 'Call'))]").getText();
		System.out.println(text);
		return text;
	}
	
	/**
	 * Helper method to select sms template
	 * */
	public void selectSMSTemplateInNotification(String tempName) {
	
		String id = McsElement.getElementByXpath(driver,"//div[contains(@class, 'x-tab-panel x-border-panel')]//div[contains(@class,'x-panel x-panel-noborder')]//input[@name='smsTemplateID']//..//input[last()]").getAttribute("id");

		DropDown.setComboValueDropDownSelector(driver, id, tempName);
	
		waitForExtJSAjaxComplete(25);
	
		Reporter.log("Set sms template to" + tempName, true);
	}
	
	/**
	 * Helper method to select language template
	 * */
	public void selectLanguageForSMSTemp(String name) {
	
		String id = McsElement.getElementByXpath(driver,SEND_NOTIFICATION_WINDOW+"//input[@name='smsLanguageCode']//..//input[last()]").getAttribute("id");

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
	 *  Helper method to get column number in Grid 
	 */
	public String getGridColumnNumber(String columnName){
		String columnClass = McsElement.getElementByXpath(driver,SEND_NOTIFICATION_WINDOW+"//div[@class='x-grid3']//div[text()='"+columnName+"']//ancestor::div[contains(@class, 'x-grid3-hd-inner')][last()]").getAttribute("class");
		String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);
		return columnNumber;
	}
		
	/**
	 * Helper method to set email address
	 */
	public void setEmailAddressInNotification(String rowNum,String columnName,String value)
	{
		Actions action = new Actions(driver);	
		String columnNumber = getGridColumnNumber(columnName);
		WebElement element = McsElement.getElementByXpath(driver,"//div[contains(@class,'send-notification-grid')]//div[@class='x-grid3-body']//div["+rowNum+"]//div[contains(@class,'x-grid3-cell-inner x-grid3-col-"+columnNumber+"')]");
		action.doubleClick(element).sendKeys(Keys.CLEAR).build().perform();
		Reporter.log("Double Clicked", true);
		waitForExtJSAjaxComplete(5);
		WebElement element1 = McsElement.getElementByXpath(driver,"//div[contains(@class,'send-notification-grid')]//input[contains(@class,'x-form-focus')]");
		action.click(element1).sendKeys(Keys.chord(Keys.CONTROL,"a")).sendKeys(Keys.chord(Keys.CONTROL,"x")).build().perform();
		action.doubleClick(element1).sendKeys(value).build().perform();
		waitForExtJSAjaxComplete(10);
		Reporter.log("set email address<br>", true);
	}
	
	/**
	 * Helper method to click check box for recipients
	 */
	public void clickCheckBoxForRecipients(String rowNum,String columnName)
	{
		String columnNumber = getGridColumnNumber(columnName);
		WebElement element = McsElement.getElementByXpath(driver, SEND_NOTIFICATION_WINDOW+"//div[@class='x-grid3-body']//div["+rowNum+"]//div[contains(@class,'x-grid3-cell-inner x-grid3-col-ext-"+columnNumber+"')]//div");
		element.click();
		Reporter.log("check box is selected<br>", true);
	}
	
	/**
	 * Helper method to click on send button
	 */
	public void clickSendButton() {
        clickXPath(SEND_NOTIFICATION_WINDOW+ "//button[contains(@class, 'x-btn-text') and text()='Assign & Send']");
        Reporter.log("Send Button is Clicked <br>", true);
	}
	
	/**
	 * Helper method to verify Qtip Mess
	 */
	public String verifyQtipMess(){
		return McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-tip info-tooltip')]//div[contains(@class, 'x-tip-body')]").getText();
	}
	
	/**
	 * Helper method to verify Assigned To in add Action tab
	 */
	public String verifyAssignedToLabelText(){
		return McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-tab-panel x-border-panel')]//div[contains(@class,'x-panel x-panel-noborder') and not(contains(@class,'x-hide-display'))]//label[contains(text(), 'Assigned to')]").getText();
	}
	
	/**
	 * Method allows to select row from grid in lookup using filters
	 * @param attribute - attribute (@class, @id) of the lookup window
	 * @param attributeValue - attribute of the lookup must contain this value
	 * @param rowTextName - row text to be selected from lookup 
	 * 
	 * @columnName - columnName of the row to be selected
	 */
	public void setValueGridLookupWithFiltersWithoutUsingMCSElementxpathGenerator(String attribute, String attributeValue, String rowTextName, String columnName) {
		Timer timer = new Timer().start();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		filterGrid(attribute, attributeValue, rowTextName, columnName);	
		
		waitForExtJSAjaxComplete(25);
		
		String xpathExpression= "(//div[contains("+attribute+",'" + attributeValue + "')]//div[contains(@class,'x-grid3-hd')"
		+ "and contains(text(),'" + columnName + "')" + "])[last()]";
		
		String columnClass = driver.findElement(By.xpath(xpathExpression)).getAttribute("class");
		
				
		
		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
		
		waitForExtJSAjaxComplete(25);
		
		McsElement.getElementByXpath(driver,"(//"+"div"+"[contains("+attribute+",'" + attributeValue + "')]//"
		+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
		+ "and "+xpathGeneratorForTextElement(rowTextName)+")[last()]").click();

		waitForExtJSAjaxComplete(25);
			
		clickOkXwindow();
		
		Reporter.log(rowTextName + " was selected"+ " (" + timer.stop()
					+ "ms)", true);

	}
	
	/**
	  * Helper method to get xpath for text
	  * @param text
	  * @return xpath
	  */
	 public static String xpathGeneratorForTextElement(String text){
		  
		  String subStrings[] = text.split(" ");
		  
		  String xpath = "starts-with(text(),'"+subStrings[0]+"')";
		  
		  for(int i=1; i<subStrings.length; i++){

		   xpath+="and contains(text(),'"+subStrings[i]+"')";
		  }
		  
		  return xpath+"]";
		  
		 }
	 
	 /**
	  * Helper method to collapse Navigation
	  */
	 public void collapseDispatcher(String Dispatcher){
		 String collapsedMode = "//span[text()='Dispatcher']//preceding-sibling::div[contains(@class,'x-tool-collapse-west')]";
		 if(McsElement.isElementPresent(driver, collapsedMode)){
			 driver.findElement(By.xpath(collapsedMode)).click();
		 }
	 }

	 /**
	  * Helper method to collapse Navigation
	  */
	 public void expandDispatcher(String Dispatcher){
		 String expandedMode = "//div[contains(@class,'servicecenter')]//div[contains(@class,'x-layout-collapsed-west') and contains(@style,'display: block')]//div[contains(@class,'x-tool-expand-west')]";
		 if(McsElement.isElementPresent(driver, expandedMode)){
			 driver.findElement(By.xpath(expandedMode)).click();
		 }

	 }
	 
	 /**
	  * Helper method to expand Navigation
	  */
	 public void expandNavigation(){
		 String expandedMode  = "//div[@id='portalcontainer_navigation' and not(contains(@class,'x-panel-collapsed'))]//div[contains(@class,'x-tool-collapse-west')]";
		 String collapsedMode = "//div[@id='portalcontainer_navigation-xcollapsed' and contains(@style,'visibility: visible')]//div[contains(@class,'expand-west')]";
		 if(!McsElement.isElementPresent(driver, expandedMode)){
			 driver.findElement(By.xpath(collapsedMode)).click();
		 }
	 }	


}
