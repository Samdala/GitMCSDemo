package test.generalweb.helpdesk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.jetty.html.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.FileUploader;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;


public class HelpDeskBackOfficePageObject extends CallDetailTabsPageObject {
	
	protected final String SUBJECT_XPATH = "//div[contains(@class,'helpdesk')]//input[@name='NEW_REFERENCE']";
	
	protected final String PRIORITY_XPATH = "//div[contains(@class,'helpdesk')]//input[@name='NEW_PRIORITY']/..//input[contains(@class,'x-form')]";
	
	protected final String BOOKCALL_XPATH = "//div[contains(@class,'helpdesk')]//button[contains(text(),'Book Call')]";
	
	protected final String NEWCALL_TAB_XPATH = "//div[contains(@class,'helpdesk')]//button[contains(text(),'New Call')]";
	
	protected final String MYCALLS_TAB_XPATH = "//div[contains(@class,'helpdesk')]//button[contains(text(),'My Calls')]";
	
	protected final String MYTEAMSCALLS_TAB_XPATH = "//div[contains(@class,'helpdesk')]//button[contains(text(),'My Team')]";
	
	protected final String BACKOFFICE_TAB_XPATH = "//div[contains(@class,'helpdesk')]//button[contains(text(),'Backoffice')]";
	
	protected final String HYPERLINK_DESCRIPTION = "//input[@name='strDescription']";
	
	protected final String HYPERLINK_REMARK = "//input[@name='strRemark']";
	
	////////////////////////////////CALL METHODS///////////////////////////
	
	
	public void clickNewCallTab(){
		clickXPath(NEWCALL_TAB_XPATH);
		Reporter.log("New call tab was clicked <br>", true);
	}	
		
	public void clickMyCallsTab(){
		clickXPath(MYCALLS_TAB_XPATH);
		Reporter.log("My calls tab was clicked <br>", true);
	}	
	

	public void clickBackofficeTab(){
		clickXPath(BACKOFFICE_TAB_XPATH);
		Reporter.log("Backoffice tab was clicked <br>", true);
	}	

	
	public void clickMyTeamsCallsTab(){
		clickXPath(MYTEAMSCALLS_TAB_XPATH);
		Reporter.log("My team calls tab was clicked <br>", true);
	}	
	
	public void clickCallTemplate(String template){
		clickXPath("//div[contains(@class,'helpdesk')]//div[contains(text(),'"+template+"')]");
		Reporter.log("Click call template <br>", true);
	}	

	public void setSubject(String template){
		clickXPath(SUBJECT_XPATH);
		McsElement.getElementByXpath(driver, SUBJECT_XPATH).clear();
		McsElement.getElementByXpath(driver, SUBJECT_XPATH).sendKeys(template);
		clickXPath(SUBJECT_XPATH);
	}
	
	public void setPriority(String priority){
		DropDown.setExtJsComboValue(driver, driver.findElement(By.xpath(PRIORITY_XPATH)).getAttribute("id"), priority);
	}
	
	public void setNature(String nature) {

		//clickLookup("@class","x-panel", "NEW_NATURE","Select a Nature");
		
		WebElement element = driver.findElement(By.xpath("//div[contains("+"@class"+",'"
				+ "x-panel" + "')]//input[contains(@name,'"
				+ "NEW_NATURE" + "')]//..//..//button"));
		waitWebElement(element);
		javaScriptFocus(element);
		javaScriptClick(element); 
		

		try {
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(By
					.xpath("//div[contains(@id,'"
				+getXWindowId("Select a Nature") + "')]"));
		}

		catch (Exception e) {
			clickXPath("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");
		} finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);
		}

		
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//span[contains(text(),'Tree')]")).click();

		waitForExtJSAjaxComplete(25);
		
		setValueTreeLookup(new String[]{nature});
		
	}
	
	
	public void setCostCenter(String customer) {

		clickLookup("@class","x-panel", "NEW_COST_CTR","Select a Cost Center");
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Cost Center"), customer, "Reference");
		
	}

	public void setGlAccount(String customer) {

		clickLookup("@class","x-panel", "NEW_GL_ACCOUNT","Select a GL Account");
		
		waitForExtJSAjaxComplete(25);
		
		driver.findElement(By.xpath("//div[contains(@id,'"+ getXWindowId("Select a GL Account") + "')]//span[text()='List']")).click();
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a GL Account"), customer, "Reference");
		
	}
	
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
	

	public void editUrl(String url, String description, String descriptionEd, String remark, String type){
		clickXPath("//*[@class='x-grid3']//div[text()='"+description+"']");
		waitForExtJSAjaxComplete(25);
		//Grid.checkRowInGriByTextValueAndClick(driver, description);
		clickXPath(EDIT_URL_XPATH);
		waitForExtJSAjaxComplete(25);
		McsElement.getElementByXpath(driver, HYPERLINK_XPATH).clear();
		McsElement.getElementByXpath(driver, HYPERLINK_XPATH).sendKeys(url);
		
		McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).clear();
		McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).sendKeys(descriptionEd);
		
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
				{clickXPath("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");}
		}

		 finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
		}
		
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters(type, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		clickXPath(ADD_URL_SAVE_XPATH);
		waitForExtJSAjaxComplete(25);
		Reporter.log("url was set <br>", true);
	}


	public void deleteUrl(String description){
		clickXPath("//*[@class='x-grid3']//div[text()='"+description+"']");
		waitForExtJSAjaxComplete(25);
		//Grid.checkRowInGriByTextValueAndClick(driver, description);
		clickXPath(DELETE_URL_XPATH);
		waitForExtJSAjaxComplete(25);
		clickOnDialogButton("Yes");
		waitForExtJSAjaxComplete(25);
		Reporter.log("url or file was deleted <br>", true);
	}

	
	public void editFile(String file, String description, String descriptionEd, String remark, String type){
		clickXPath("//*[@class='x-grid3']//div[text()='"+description+"']");
		waitForExtJSAjaxComplete(25);
		//Grid.checkRowInGriByTextValueAndClick(driver, description);
		clickXPath(EDIT_URL_XPATH);
		waitForExtJSAjaxComplete(25);
		FileUploader.uploadFileName(driver, "@class", "x-window", file);
		waitForExtJSAjaxComplete(25);
		
		McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).clear();
		McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).sendKeys(descriptionEd);
		
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
				{clickXPath("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");}
		}

		 finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
		}
		
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters(type, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		
		clickXPath(ADD_URL_SAVE_XPATH);
		waitForExtJSAjaxComplete(25);
		Reporter.log("file was uploaded <br>", true);
	}
	
	
	public void setFile(String file, String description, String remark, String type){
		clickXPath(ADD_FILE_XPATH);
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

		clickXPath("//div[contains(@class,'x-window')]//label[contains(text(),'Type')]/..//button");
		
		waitForExtJSAjaxComplete(10);
		waitForExtJSAjaxComplete(10);
		
		try {driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			if (driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size() == xwindowNumber)
				{clickXPath("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");}
		}

		 finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
		}
		
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters(type, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		
		clickXPath(ADD_URL_CREATE_XPATH);
		waitForExtJSAjaxComplete(25);
		Reporter.log("file was uploaded <br>", true);
	}

	
	
	
	public void setUrl(String url){
		clickXPath(ADD_URL_XPATH);
		waitForExtJSAjaxComplete(25);
		McsElement.getElementByXpath(driver, HYPERLINK_XPATH).clear();
		McsElement.getElementByXpath(driver, HYPERLINK_XPATH).sendKeys(url);
		clickXPath(ADD_URL_CREATE_XPATH);
		waitForExtJSAjaxComplete(25);
		Reporter.log("url was set <br>", true);
	}
	

	public void editUrl(String url, String urlEd){
		clickXPath("//*[@class='x-grid3']//div[text()='"+url+"']");
		waitForExtJSAjaxComplete(25);
	//	Grid.checkRowInGriByTextValueAndClick(driver, url);
		clickXPath(EDIT_URL_XPATH);
		waitForExtJSAjaxComplete(25);
		McsElement.getElementByXpath(driver, HYPERLINK_XPATH).clear();
		McsElement.getElementByXpath(driver, HYPERLINK_XPATH).sendKeys(urlEd);
		clickXPath(ADD_URL_SAVE_XPATH);
		waitForExtJSAjaxComplete(25);
		Reporter.log("url was set <br>", true);
	}

	
	public void editFile(String file, String fileEd){
		clickXPath("//*[@class='x-grid3']//div[text()='"+file+"']");
		waitForExtJSAjaxComplete(25);

//		Grid.checkRowInGriByTextValueAndClick(driver, file);
		clickXPath(EDIT_URL_XPATH);
		waitForExtJSAjaxComplete(25);
		FileUploader.uploadFileName(driver, "@class", "x-window", fileEd);
		waitForExtJSAjaxComplete(25);
		clickXPath(ADD_URL_SAVE_XPATH);
		waitForExtJSAjaxComplete(25);
		Reporter.log("file was uploaded <br>", true);
	}
	
	
	public void setFile(String file) throws Exception{
		clickXPath(ADD_FILE_XPATH);
		waitForExtJSAjaxComplete(25);
		FileUploader.uploadFileName(driver, "@class", "x-window", file);
		waitForExtJSAjaxComplete(25);
		clickXPath(ADD_URL_CREATE_XPATH);
		FileUploader.waitForUploadWindowDisappear(driver);
		waitForExtJSAjaxComplete(25);
		Reporter.log("file was uploaded <br>", true);
	}
	
	
	
	public void clickBookCall(){
		clickXPath(BOOKCALL_XPATH);
		Reporter.log("Book call was clicked <br>", true);
	}
	
	public void clickButtonAddCall() {
		clickXPath("//button[contains(text(),'Add')]");
		Reporter.log("Add call was clicked <br>", true);
	}
	
	public void clickButtonDeleteCall() {
		clickXPath("//button[contains(text(),'Delete')]");
		Reporter.log("Delete call was clicked <br>", true);
	}

	
	
	public void openCallDetailsDialog(String textValue) {
		WebElement cell = Grid.checkRowInGriByTextValueAndClick(driver, textValue);
		//cell.click();
		Actions action = new Actions(driver);
		action.doubleClick(cell).perform();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Double clicked on Consumption ID <br>", true);
	}
	
	public void verifyCallDetailsHeader(String CallID) {
		McsElement.getElementByXpath(driver, "//div[contains(@class, 'hdwo-details')]//div[contains(text(), '" + CallID + "')]");
		Reporter.log("Header of call detail is correct", true);
	}
	
	public void clickCancelOnCallDetailsDialog() {
		clickXPath("//div[contains(@class, ' x-window x-resizable-pinned')]//table[contains(@class, 'x-toolbar-ct')]//button[text()='Cancel']");
	}
	
	public void closeXWindow(){
		clickXPath("(//div[contains(@class,'x-tool-close')])[last()]");
		Reporter.log("close x-window <br>", true);
	}
	
	/**
	 * Method allows to filter in grid by text
	 * @param attribute - attribute (@class, @id) of the grid container
	 * @param attributeValue - corresponding value of the grid container attribute
	 * @param rowTextName - row text to be filtered by 
	 * @columnName - columnName of the row 
	 */
	public void filterGridForCall(String attribute, String attributeValue, String rowTextName, String columnName) {
		Timer timer = new Timer().start();
		
		
		String columnClass = McsElement
				.getElementByXpath(driver,"(//div[contains("+attribute+",'"+ attributeValue+"')]//div[contains(@class,'quickfilters')]"
						+ "//div[contains(@class,'x-grid3-hd') and contains(text(),'"+columnName+"')])[last()]").getAttribute("class");
//		String columnClass = McsElement
//		.getLastElementByPartAttributeValueAndParentElement(driver,
//				"div", attribute, attributeValue,
//				"div","@class", "x-grid3-hd",
//				"text()", columnName, true, true).getAttribute("class");
		
		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
	
		
		WebElement filterInput = McsElement.getElementByXpath(driver, "(//div[contains("+attribute+",'"+attributeValue+"')]//"
					+ "input[contains(@id,'filter-editor-"+columnNumber+"')])"); 
					
					
//					McsElement
//					.getElementByPartAttributeValueAndParentElement(driver,
//							"div", attribute, attributeValue, "input",
//							"@id", "filter-editor-"+columnNumber, true, true);

			filterInput.clear();
			
			filterInput.sendKeys(rowTextName);
			waitForExtJSAjaxComplete(5);
			filterInput.sendKeys(Keys.RETURN);
			
	//	 clickXPath("(//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'x-grid3-body')])[last()]/div");

//			McsElement
//			.getElementByPartAttributeValueAndParentElement(driver,
//					"div", attribute, attributeValue, "div",
//					"@class", "x-grid3-body", true, true).click();
			
			waitForExtJSAjaxComplete(5);

			Reporter.log(rowTextName + " was filtered"+ " (" + timer.stop()
					+ "ms)", true);

	}
	/**
	 * Helper method to get cost center lookup values
	 * 
	 * @return cost center lookup values
	 */
	public List<String> getCostCenterLookUpValues(){
		
		waitForExtJSAjaxComplete(25);
		
		clickLookup("@class","x-panel","NEW_COST_CTR","Select a Cost Center");
		
		waitForExtJSAjaxComplete(25);
		
		return getLookUpValues("@class","x-panel","NEW_COST_CTR","Select a Cost Center","Reference");
	}
	
	
	private List<String> getLookUpValues(String nameAttr,String attrVal,String inpName, String windowTitle, String colName ){
		
		return getValuesFromGridLookup("@id",getXWindowId(windowTitle), colName);
		
	}
	
	/**
	 * Helper method to get GL Acccount lookup values
	 * 
	 * @return GL Account lookup values
	 */
	public List<String> getGLAccLookUpValues(){

		clickLookup("@class","x-panel", "NEW_GL_ACCOUNT","Select a GL Account");
		
		waitForExtJSAjaxComplete(25);
		
		clickXPath("//div[contains(@id,'"+ getXWindowId("Select a GL Account") + "')]//span[text()='List']");
		
		waitForExtJSAjaxComplete(25);
		
		return getLookUpValues("@class","x-panel","NEW_GL_ACCOUNT","Select a GL Account","Reference");
	}
	
	public void collapseDetailsPanel(String windowTitle){
		
		clickXPath("//span[contains(@class,'x-panel-header-text') and contains(text(),'Details')]/..//div[contains(@class,'x-tool-collapse-south')]");

	}
	
	
	/**
	 * Helper method to get customer lookup values
	 * 
	 * @return customer lookup values
	 */
	public List<String> getCustomerLookUpValues(){
		
		waitForExtJSAjaxComplete(25);
		
		clickLookup("@class","x-panel","NEW_CUSTOMER","Select a Customer");
		
		waitForExtJSAjaxComplete(25);
		
		return getLookUpValues("@class","x-panel","	NEW_CUSTOMER","Select a Customer","Customer Code");
	}

	
	
	
	/**
	 * Expand Aggregate Panel
	 * @return
	 */
	public boolean expandAggregatespanel(){
		String collapsedIconXpath = "//div[contains(@class,'x-border-panel x-panel-collapsed') and  contains(@style,'visibility: visible')]//span[text()='Aggregates']/preceding::div[contains(@class,' x-tool-collapse-east')]";
		String expandIconXpath = "//div[contains(@class,'helpdesk')]//div[contains(@class, 'collapsed-east') and contains(@style,'visibility: visible')]";
		if(McsElement.isElementPresent(driver,collapsedIconXpath))
			clickXPath(expandIconXpath);
		return true;
	}
	/**
	 * Get List of Items count In change drop down
	 * @return
	 */
	public int getChangeListCount(){
		List<WebElement> ele = driver.findElements(By.xpath("//ul[contains(@class,'x-menu-list')]//li[contains(@class,'x-menu-sep-li')]/following-sibling::li")); 
		int size = ele.size();	
		return size;
	}

	/**
	 * Get List of Items count In Aggregate Panel 
	 * @return
	 */
	public int getAggregatePanelListCount(){
		List<WebElement> ele = driver.findElements(By.xpath("//div[contains(@class,'helpdesk')]//span[text()='Aggregates']/ancestor::div[contains(@class,'x-panel-reset panel-small-header')]//div[@class='x-panel-body']//div[contains(@style, 'font-weight: bold')]"));
		int size = ele.size();
		return size;
	}

	/**
	 * Click On Change drop down
	 * @param parentValue
	 * @param text
	 */

	public void clickOnChangeDropDown(String text){
		McsElement.getElementByXpath(driver,"//div[contains(@class,'helpdesk')]//button[text()='"+text+"']").click();

	}

	/**
	 * Check On Items In Change Drop Down
	 * @param itemName
	 */
	public void clickOnItemInChangeDropDown(String itemName){
		driver.findElement(By.xpath(" //div[contains(@class,'x-menu-floating') and contains(@style,'visibility: visible')] //span[text()='"+itemName+"']")).click();
	}

	/**
	 * Check whether item is present in Change Drop Down
	 * @param itemName
	 * @param parentAttrValue
	 * @param childAttr
	 * @return
	 */
	public boolean isItemPresentInChangeDropDown(String itemName){
		try{
			McsElement.getElementByXpath(driver, "//div[contains(@class,'x-menu-floating') and contains(@style,'visibility: visible')] //span[contains(@class,'x-menu-item-text') and text()='"+itemName+"']");
			Reporter.log("Item Found ",true);
			return true;
		}
		catch(Exception E){
			Reporter.log("Item not found", true);
			return false;
		}
	}

	/**
	 *  Check whether item is present in Aggregate Panel
	 * @param itemName
	 * @param parntAttrValue
	 * @return
	 */
	public boolean isItemPresentInAggregatePanel(String itemName, String parntAttrValue){
		try{
			McsElement.getElementByXpath(driver, "//div[contains(@class,'"+parntAttrValue+"')]//div[contains(@class,'x-border-panel')]//div[text()='"+itemName+"']");
			Reporter.log("Item Found ",true);
			return true;
		}
		catch(Exception E){
			Reporter.log("Item not found", true);
			return false;	
		}

	}

	/**
	 * Get Aggregate Panel Text
	 * @return
	 */
	public String getAggregatePanelText(){
		return McsElement.getElementByXpath(driver,"//span[text()='Aggregates']/ancestor::div[contains(@class,'x-panel-reset panel-small-header')]//div[@class='x-panel-body']//div").getText();
	}


	/**
	 * Method allows to filter in grid by text
	 * @param attribute - attribute (@class, @id) of the grid container
	 * @param attributeValue - corresponding value of the grid container attribute
	 * @param rowTextName - row text to be filtered by 
	 * @columnName - columnName of the row 
	 */
	public void filterGridBackoffice(String attribute, String attributeValue, String rowTextName, String columnName) {
		Timer timer = new Timer().start();
		String columnClass = McsElement
				.getElementByXpath(driver,"(//div[contains("+attribute+",'"+ attributeValue+"')]//div[contains(@class,'quickfilters')]"
						+ "//div[contains(@class,'x-grid3-hd') and contains(text(),'"+columnName+"')])[last()]").getAttribute("class");
		
		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
		

		WebElement filterInput = McsElement.getElementByXpath(driver, "(//div[contains("+attribute+",'"+attributeValue+"')]//"
					+ "input[contains(@id,'filter-editor-"+columnNumber+"')])"); 
			filterInput.clear();
			
			filterInput.sendKeys(rowTextName);
			
			filterInput.sendKeys(Keys.ENTER);
			
			waitForExtJSAjaxComplete(5);

			Reporter.log(rowTextName + " was filtered"+ " (" + timer.stop()
					+ "ms)", true);

	}


	/**
	 * Helper method to select multiple rows in Grid
	 */
	public void selectMultipleRowsFromGrid(String call1, String call2){
		WebElement ele1 = driver.findElement(By.xpath("//div[contains(@class, 'helpdesk')]//*[@class='x-grid3']//div[text()='"+call1+"']"));
		WebElement ele2 = driver.findElement(By.xpath("//div[contains(@class, 'helpdesk')]//*[@class='x-grid3']//div[text()='"+call2+"']"));
		
		 Actions builder = new Actions(driver);
	     builder.click(ele1).keyDown(Keys.CONTROL).click(ele2).keyUp(Keys.CONTROL).build().perform();
	     Reporter.log("Multiple Rows Selected from Grid", true);
	}
	
	/**
	 * To Click Button in Call Details Window
	 * @param buttonName
	 * @return
	 */
	public boolean clickButtonInCallDetailsWindow(String buttonName){

		try{
			WebElement button=driver.findElement(By.xpath("//div[contains(@class,'resizable-pinned') and contains(@style,'visibility: visible')]//button[text()='"+buttonName+"']"));
			Actions action = new Actions(driver);
			action.doubleClick(button).perform();
			waitForExtJSAjaxComplete(20);
			Reporter.log("Button "+buttonName+" is clicked",true);
			return true;}
		catch(Exception E){
			Reporter.log("Button "+buttonName+" is disabled to click", true);
			return false;
		}

	}
	
	/**
	 * Whether the button is enabled or not
	 * @param buttonName
	 * @return
	 */
	public boolean isButtonEnabled(String buttonName){
		String buttonStatus = "//div[contains(@class,'resizable-pinned') and contains(@style,'visibility: visible')]//table[contains(@class,'icon x-item-disabled')]//button[text()='"+buttonName+"']";
		
		if(!McsElement.isElementPresent(driver,buttonStatus)){
			try{
		 driver.findElement(By.xpath("//div[contains(@class,'resizable-pinned') and contains(@style,'visibility: visible')]//button[text()='"+buttonName+"']")).isEnabled();
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
	 * Gets Maintenance object Field Value
	 * @param fieldName
	 * @param numb
	 * @return
	 */
	public String getMaintenanceObjFieldValue(String fieldName,int numb,String grpNumb){
		
		try {
            WebElement element = McsElement.getElementByXpath(driver,"//div[contains(@id, 'gp-groupOrd-"+grpNumb+"-bd')]//div[contains(text(), '"+fieldName+"')]/ancestor::tr//td["+numb+"]//div");

			((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
         e.printStackTrace();
        }
		 return McsElement.getElementByXpath(driver,"//div[contains(@id, 'gp-groupOrd-0-bd')]//div[contains(text(), '"+fieldName+"')]/ancestor::tr//td["+numb+"]//div").getText();
		}
	
	/**
	 * Get Window Header In CallDetails 
	 * @return
	 */
	public String getWindowHeaderInCallDatails(){
		return driver.findElement(By.xpath("//div[contains(@class,'shadow') and contains(@style,'display: block')]/following-sibling::div[contains(@class, 'x-window x-resizable-pinned')]//span[contains(@class, 'x-window-header-text')]")).getText();
	}
	
	/**
	 * Get Access Instructions Window Text
	 * @return
	 */
	public String getAccessInstructionsWindowText(){
		
		WebElement accessInstructions  = driver.findElement(By.xpath("//div[contains(@class,'resizable-pinned') and contains(@style,'visibility: visible')]//div[contains(@class,'x-html-editor')]//textarea"));

		String instr = accessInstructions.getAttribute("value");

		waitForExtJSAjaxComplete(20);
		
		return instr;
	}
	

	/**
	 * Helper method to set customer
	 * @param customer
	 */
	public void setCustomer(String customer) {

		clickLookup("@class","x-panel", "NEW_CUSTOMER","Select a Customer");

		waitForExtJSAjaxComplete(25);

		setValueGridLookupWithFilters("@id", getXWindowId("Select a Customer"), customer, "Customer Name");

	}

	/**
	 * Helper method to get grid column number without filter
	 * @param attribute
	 * @param attributeValue
	 * @param columnName
	 * @return column number
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
	 * Helper method to get SLA field by row 
	 * @param attribute 
	 * @param attributeValue
	 * @param columnName to get value
	 * @return List of values
	 */
	public List<String> getSLAFieldValuesByRow(String attribute, String attributeValue,String columnName){
		List<String> slaValues = new ArrayList<String>();
		int colNo = getGridColumnNumberWithoutQuickFilters(attribute, attributeValue, columnName);

		String xpath = "//div[contains(@class,' x-resizable-pinned') and contains(@style,'visibility: visible')]//div[contains(@class,'x-grid3-row') and not(contains(@class,'collapsed '))]//td[contains(@class,'x-grid3-cell') and not(contains(@class,'grid-cell-disabled'))]//div[contains(@class, 'col-"+colNo+"')]";
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		for(WebElement element : elements){
			slaValues.add(element.getText());
		}
		return slaValues;
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
	 * Helper method to get value from transaction line 
	 * @param attribute
	 * @param attributeValue
	 * @param columnName
	 * @param rowNumb
	 * @return value
	 */
	public String getValuesFromTransactionLine(String attribute, String attributeValue, String columnName,String rowNumb) {

		int columnNumber = getGridColumnNumberWithoutQuickFilters(attribute, attributeValue , columnName );
		waitForExtJSAjaxComplete(5);
		String value= McsElement.getElementByXpath(driver,"//div[contains(@class,' x-resizable-pinned') and contains(@style,'visibility: visible')]//div[contains("+attribute+",'" + attributeValue + "')]//div[contains(@class,'x-grid3-row-"+rowNumb+"')]//div[contains(@class,'x-grid3-cell-inner x-grid3-col-"+columnNumber+"')][last()]").getText();
		return value;
	}

	/**
	 * Helper method to get SLA status in tool bar
	 * @param name
	 * @return sla status
	 */
	public String getToolBarSLAStatuses(String name){
		String className = driver.findElement(By.xpath("//div[contains(@class, 'hdwo-details')]//div[contains(@class, '"+name+"')]")).getAttribute("class");
		String slaIndc = className.substring(className.lastIndexOf("-") + 1);
		return slaIndc;
	}

	/**
	 * Helper method to get location lookup values
	 * @param locName : NEW_LOCATION/location
	 * @return location list
	 */
	public List<String> getLocationLookUpValues(String locName){
        // change of location look up
		//clickLookup("@class","x-panel",locName,"Select a Location");
		clickLookupNewUI("@class","x-panel",locName,"Select a Location");
		waitForExtJSAjaxComplete(25);

		//clickXPath("//div[contains(@id,'"+ getXWindowId("Select a Location") + "')]//span[text()='Sites']");

		waitForExtJSAjaxComplete(15);

		//return getLookUpValues("@class","x-panel",locName,"Select a Location","Reference");
		return getValuesFromGridLookupNewUI("@class","x6-window-closable","Reference");
	}

	/**
	 * Helper method to click lookup and get values in lookup 
	 * @param name fieldName
	 * @param windowName
	 * @param colmnName
	 * @return list of values
	 */
	public List<String> clickandGetLookUpValues(String name, String windowName, String colmnName){

		clickLookup("@class","x-panel",name,windowName);

		waitForExtJSAjaxComplete(25);

		return getLookUpValues("@class","x-panel",name,windowName,colmnName);
	}

	/**
	 * Helper method to get sites linked to client Organization 
	 * @param customer
	 * @return list of sites
	 */
	public List<String> getAllSitesOfClientOrg(String customer){
		List<String> sites = new ArrayList<String>();
		String xpath = "//span[contains(text(), '"+customer+"')]/../../following-sibling ::ul[contains(@style, 'visibility: visible')]//img[contains(@class,'icon-site')]/..//a/span";
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		for(WebElement element : elements){
			String text = element.getText().trim();
			text = text.substring(0, text.indexOf(' '));
			sites.add(text);
		}
		return sites;
	}
}
