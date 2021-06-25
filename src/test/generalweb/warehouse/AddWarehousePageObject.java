package test.generalweb.warehouse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.webelement.DropDown;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class AddWarehousePageObject extends AbstractMcsTestSuite {
	
	protected final String XPATH_OVERVIEW_BUTTON_GENERAL = "//span[contains(@class,'x-menu-item-text') and contains(text(),'General')]";

	protected final String XPATH_OVERVIEW_BUTTON_GENERAL_ALL_WORKORDERS = "//span[contains(@class,'x-menu-item-text') and contains(text(),'All')]";
	
	protected final String XPATH_OVERVIEW_BUTTON = "//button[contains(@class,'x-btn-text icon-ov-overview')]";
	
	protected final String ADD_WAREHOUSE_WINDOW_HEADER = "Add Warehouse";
	
	protected final String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
	
	protected final String WAREHOUSE_GENERAL_FORM_FOOTER_CLASS = "x-window-footer x-panel-btns";
	
	protected final String SELECT_SCREEN_CLASS = "x-window x-window-noborder x-resizable-pinned";
	
	protected final String WAREHOUSE_WINDOW_CLASS = "warehouse-details";
	
	protected final String SELECT_SCREEN_FOOTER_CLASS = "x-window-footer x-window-footer-noborder x-panel-btns";
	
	protected final String XPATH_WAREHOUSE_RIGHTS_WINDOW ="//div[contains(@class,'warehouse-rights-grid')]";
	
	//*****************************STORAGE LOCATION GRID********************
	protected final String WAREHOUSE_STORAGE_LOCATIONS_ERROR_CLASS = "x-grid3-col-error";
	
	protected final String WAREHOUSE_STORAGE_LOCATIONS_NUMBER_CLASS = "x-grid3-col-numberer";
	
	protected final String WAREHOUSE_STORAGE_LOCATIONS_CHECKER_CLASS = "x-grid3-col-checker";
	
	protected final String WAREHOUSE_STORAGE_LOCATIONS_CODE_CLASS = "x-grid3-col-3";
	
	protected final String WAREHOUSE_STORAGE_LOCATIONS_REF_CLASS = "x-grid3-col-4";
	
	protected final String WAREHOUSE_STORAGE_LOCATIONS_DESC_CLASS =	"x-grid3-col-storageLocDescription";
	
	//************************************************************************************
	
	protected final String XPATH_STOCK_ITEM_LINES_GRID = "//div[contains(@class,'stock-items-grid')]//div[@class='x-grid3']";
	
	protected final String XPATH_STOCK_ITEM_LINES_GRID_BODY = XPATH_STOCK_ITEM_LINES_GRID+"//div[@class='x-grid3-body']";
	
	protected final String XPATH_STOCK_ITEM_LINES_GRID_EDITOR = "//div[contains(@class,'x-grid-editor') and not(contains(@style,'visibility: hidden'))]";
	
	protected final String GOODS_ISSUE_TRANSACTION_WINDOW_HEADER = "Goods Issue";
	
	
	
	public void goToWarehouse() {
		driver.get(configuration.getApplicationUrl()
				+ "/frame.php?relay=MCS_PORTAL_ST-ACTIVATE[WAREHOUSES]");
	};

	
	public void clickAddButton() {
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", "x-panel-tbar", "button", "@class",
				"icon-ov-add", "text()", "Add", true, true).click();
		Reporter.log("Click Add button", true);
	}

	public void clickEditButton() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", "x-panel-tbar", "button", "@class",
				"icon-ov-edit", "text()", "Edit", true, true).click();
		Reporter.log("Click Edit button", true);
	}

	public void clickDeleteButton() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", "x-panel-tbar", "button", "@class",
				"icon-ov-remove", "text()", "Delete", true, true).click();
		clickOnDialogButton("Yes");
		Reporter.log("Click Delete button", true);
	}
	
	public void clickClearFilterButton() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", "x-panel-tbar", "button", "@class",
				"icon-grid-filter-clear", "text()", "Clear Filters", true, true).click();
		Reporter.log("Click Clear Filters button", true);
	}
	
	
	/**
	 *Helper method to click on Delete button 
	 *Note: This method doesn't click on Yes in confirmation message 
	 * */
	
	public void clickToolBarDeleteButton() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", "x-panel-tbar", "button", "@class",
				"icon-ov-remove", "text()", "Delete", true, true).click();
		Reporter.log("Click Delete button", true);
		
		
	}
	
	
	
	public void setCode(String reference, String windowTitle) {
		WebElement code= McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "input", "@name", "code", true, true);
		code.clear();
		code.sendKeys(reference);
		Reporter.log("Set code", true);
	}
	
	public String getCode(String windowTitle) {
		/*return McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "input", "@name", "code", true, true).getAttribute("value");*/
		String ele = driver.findElement(By.xpath("//div[contains(@class, 'stock warehouse-details') and contains(@style, 'visibility: visible')]//input[@name='code']")).getAttribute("value");
		return ele;
	}
	
	public String getReference(String windowTitle) {
		/*return McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "input", "@name", "reference", true, true).getAttribute("value");*/
		String ele = driver.findElement(By.xpath("//div[contains(@class, 'stock warehouse-details') and contains(@style, 'visibility: visible')]//input[@name='reference']")).getAttribute("value");
		return ele;
	}

	public void setReference(String reference, String windowTitle) {
		WebElement code= McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "input", "@name", "reference", true, true);
		code.clear();
		code.sendKeys(reference);
		Reporter.log("Set reference", true);
	}

	public void setDescription(String reference, String windowTitle) {
		WebElement code= McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "textarea", "@name", "description", true, true);
		code.clear();
		code.sendKeys(reference);
		Reporter.log("Set description", true);
	}
	
	public String getDescription(String windowTitle) {
		/*return McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "textarea", "@name", "description", true, true).getAttribute("value");*/
		String ele = driver.findElement(By.xpath("//div[contains(@class, 'stock warehouse-details') and contains(@style, 'visibility: visible')]//textarea[@name='description']")).getAttribute("value");
		return ele;
	}
	
	public void setWarehouseType(String type, String windowTitle){
		
		WebElement code= McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "input", "@name", "type", true, true);
		
		DropDown.setComboValueDropDownSelector(driver, code.getAttribute("id"), type);
		Reporter.log("Set Warehouse Type", true);
	}
	
	
	public void setAdres1(String reference, String windowTitle) {
		WebElement code= McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "input", "@name", "address_1", true, true);
		code.clear();
		code.sendKeys(reference);
		Reporter.log("Set adres1", true);
	}
	
	public String getAdres1(String windowTitle) {
		/*return McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "input", "@name", "address_1", true, true).getAttribute("value");*/
		String ele = driver.findElement(By.xpath("//div[contains(@class, 'stock warehouse-details') and contains(@style, 'visibility: visible')]//input[@name='address_1']")).getAttribute("value");
		return ele;
	}
	
	public void setAdres2(String reference, String windowTitle) {
		WebElement code= McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "input", "@name", "address_2", true, true);
		code.clear();
		code.sendKeys(reference);
		Reporter.log("Set adres2", true);
	}
	
	public String getAdres2(String windowTitle) {
		/*return McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "input", "@name", "address_2", true, true).getAttribute("value");*/
		String ele = driver.findElement(By.xpath("//div[contains(@class, 'stock warehouse-details') and contains(@style, 'visibility: visible')]//input[@name='address_2']")).getAttribute("value");
		return ele;
	}
	
	public void setPhone(String reference, String windowTitle) {
		WebElement code= McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "input", "@name", "phone", true, true);
		code.clear();
		code.sendKeys(reference);
		Reporter.log("Set phone", true);
	}

	public String getPhone(String windowTitle) {
/*		return McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "input", "@name", "phone", true, true).getAttribute("value");*/
		String ele = driver.findElement(By.xpath("//div[contains(@class, 'stock warehouse-details') and contains(@style, 'visibility: visible')]//input[@name='phone']")).getAttribute("value");
		return ele;
	}
	
	public void setZip(String reference, String windowTitle) {
		WebElement code= McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "input", "@name", "zip_code", true, true);
		code.clear();
		code.sendKeys(reference);
		Reporter.log("Set zip", true);
	}
	
	public String getZip(String windowTitle) {
		/*return McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "input", "@name", "zip_code", true, true).getAttribute("value");*/
		String ele = driver.findElement(By.xpath("//div[contains(@class, 'stock warehouse-details') and contains(@style, 'visibility: visible')]//input[@name='zip_code']")).getAttribute("value");
		return ele;
	}
	
	public void setCity(String reference,String windowTitle) {
		WebElement code= McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "input", "@name", "city", true, true);
		code.clear();
		code.sendKeys(reference);
		Reporter.log("Set city", true);
	}
	
	public String getCity(String windowTitle) {
		/*return McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "input", "@name", "city", true, true).getAttribute("value");*/
		String ele = driver.findElement(By.xpath("//div[contains(@class, 'stock warehouse-details') and contains(@style, 'visibility: visible')]//input[@name='city']")).getAttribute("value");
		return ele;
	}
	

	public void setState(String reference, String windowTitle) {
		WebElement code= McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "input", "@name", "state", true, true);
		code.clear();
		code.sendKeys(reference);
		Reporter.log("Set state", true);
	}
	
	public String getState(String windowTitle) {
		/*return McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "input", "@name", "state", true, true).getAttribute("value");*/
		String ele = driver.findElement(By.xpath("//div[contains(@class, 'stock warehouse-details') and contains(@style, 'visibility: visible')]//input[@name='state']")).getAttribute("value");
		return ele;
	}
	
	
	public String getCountry(String windowTitle) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@id,'"+ getXWindowId(windowTitle) +"')]//input[@name='country']//..//input)[last()]"))
				.getAttribute("value");
	}
	

	public void setCountry(String column, String name, String windowTitle) {
		
		clickLookup("@id",getXWindowId(windowTitle), "country", "Select a Country");
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Country"), name, column);
			Reporter.log("Country was selected", true);
	}

	public String getTimeZone(String windowTitle) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@id,'"+ getXWindowId(windowTitle) +"')]//input[@name='timezone']//..//input)[last()]"))
				.getAttribute("value");
	}
	

	public void setTimeZone(String column, String name, String windowTitle) {
		
		clickLookup("@id",getXWindowId(windowTitle), "timezone","Select a Time Zone");
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Time Zone"), name, column);
			Reporter.log("Time Zone was selected", true);
	}
	
	
	public void saveAndClose(String windowTitle) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXWindowId(windowTitle), "button", "text()", "Save",
				"text()","Close", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Save and close", true);
	}


	public void save() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", WAREHOUSE_WINDOW_CLASS, "button", "text()", "Save", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Save", true);
	}
	
	
	public void close(String windowTitle) {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@id", getXWindowId(windowTitle), "button", "text()", "Close", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Close", true);
	}
	
	public void clickDefault(String windowTitle) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXWindowId(windowTitle), "input", "@name",
				"is_default", true, true).click();
//		clickOnDialogButton("Yes");
		Reporter.log("Check default", true);
	}
	
	public boolean getDefaultState(String windowTitle) {
		if (McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@id", getXWindowId(windowTitle), "input", "@name",
				"is_default", true, false).getAttribute("checked") == null) {
			return false;
		} else {
			return true;
		}
	}
	
	
///////////////////////////////STORAGE LOCATION TAB//////////////////////////////////////////////
	
	public void clickStorageLocationsTab() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", WAREHOUSE_WINDOW_CLASS, "span", "text()", "Storage Locations", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Click storage location tab", true);
	}
	
	public void clickAddStorageLocation() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", "storage-locations-grid", "button", "text()", "Add", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Click Add storage location", true);
	}
	

	public void clickDeleteStorageLocation() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", "storage-locations-grid", "button", "text()", "Delete", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Click Delete storage location", true);
	}
	
	
	public void setStorageLocation(String number, String code, String reference){
			
		String id = McsElement.getElementByXpath(driver,"//div[contains(@class,'storage-locations-grid')]").getAttribute("id");
		
		
		String script= "Ext.getCmp('"+id+"').store.data.items["+number+"].data.storageLocCode='"+code+"';";
		script= script + "Ext.getCmp('"+id+"').store.data.items["+number+"].data.storageLocReference='"+reference+"';";
		
			
		script= script + "Ext.getCmp('"+id+"').store.data.items["+number+"].afterEdit();";
		
		((JavascriptExecutor) driver).executeScript(script);
		
		waitForExtJSAjaxComplete(25);
		
		Reporter.log("fill in storage location "+number, true);
	}

	/**
	 * Helper method to return the Info bar message
	 * @return Info bar message
	 */
	public String getStorageLocationInfobarMessage(){
		
		WebElement infobar = McsElement.getElementByXpath(driver, "//div[contains(@class,'storage-locations-tab')]//div[contains(@class,'infobar')]");
		
		return infobar.getText();
	}
	
	/**
	 * Helper method to get Warning message displayed for the specified row number
	 */
	public String getWarningMessageForStorageLocation(String rowNum){
		
		String toolTip = getStorageLocationsCellAttribute(rowNum,WAREHOUSE_STORAGE_LOCATIONS_ERROR_CLASS,"outerHTML" );
		
		String attrInQuotes = findAttributeValueFromHtmlString("ext:qtip" , toolTip); 
		
		attrInQuotes = attrInQuotes.replace("\"", "");
		
		Reporter.log("Tool tip obtained is"+attrInQuotes, true);
		
		return attrInQuotes; 
				
	}
	
	/**
	 * Helper method to get a Cell value from Stock Items grid
	 * */
	public String getStorageLocationsCellAttribute(String rowNum, String colClass, String attribute){

		WebElement cellElement = driver.findElement(By.xpath("//div[contains(@class,'storage-locations-grid')]//div[@class='x-grid3-body']/div["+rowNum+"]//div[contains(@class,'"+colClass+"')]"));
		
		return cellElement.getAttribute(attribute);
			
	}
	
	/**
	 * Helper method to select a storage location from Storage locations grid
	 * @param storageLocation code or reference can be passed
	 */
	public void selectStorageLocation(String storageLocation){
		
		String classAttr = driver.findElement(By.xpath("//div[contains(@class,'storage-locations-grid')]//div[@class='x-grid3-body']//div[text()='"+storageLocation+"']/ancestor::div[contains(@class,'x-grid3-row')]")).getAttribute("class");

		if(!classAttr.contains("x-grid3-row-selected")){
		
			WebElement cellElement = driver.findElement(By.xpath("//div[contains(@class,'storage-locations-grid')]//div[@class='x-grid3-body']//div[text()='"+storageLocation+"']/../../td[contains(@class,'x-grid3-td-checker')]"));
		
			cellElement.click();
		}	
	}
	
	
///////////////////////////////STOCK ITEMS TAB//////////////////////////////////////////////
		
	
	public void clickStockItemsTab() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", WAREHOUSE_WINDOW_CLASS, "span", "text()", "Stock Items", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Click stock items tab", true);
	}

	
	public void clickAddStockItems() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", "stock-items-grid", "button", "text()", "Add", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Click Add stock item", true);
	}
	

	public void clickDeleteStockItem() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", "stock-items-grid", "button", "text()", "Delete", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Click Delete stock item", true);
	}
	
	
	public void setStockItem(String reference){
		
		
		click("//div[contains(@class,'x-window-noborder')]//span[text()='Products And Services']");
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters("@class", "x-window-noborder", reference, "Reference");
		
		Reporter.log("select stock item "+reference, true);
	}
	
	/**
	 * Helper method to get a Cell value from Stock Items grid
	 * */
	public String getStockItemsCellValue(String rowNum, String colName){
		WebElement element = driver.findElement(By.xpath("//div[contains(@class,'stock-items-grid')]//div[contains(text(),'"+colName+"')]"));
		String colClass =  element.getAttribute("class");
		String columnNumber = colClass.substring(colClass.lastIndexOf("-") + 1);

		//UOM field column position changes from portal to portal. So getting column number in Run time and using it for xpath.
		String parentAttribute = "x-grid3-col-"+columnNumber;

		WebElement gridParentElement = driver.findElement(By.xpath("//div[contains(@class,'stock-items-grid')]//div[@class='x-grid3-body']/div["+rowNum+"]//div[contains(@class,'"+parentAttribute+"')]")).findElement(By.xpath(".."));
		
		String attrVal =gridParentElement.getText();
		
		return attrVal;
	}
	
	public String getProductRowNumberInWareHouse(String stockRef){
		String xpath="//div[contains(@class,'stock-items-grid')]//div[@class='x-grid3-body']//div[contains(@class,'x-grid3-col-reference') and (text()='"+stockRef+"')]/../..//div[contains(@class,'x-grid3-col-numberer')]";
		String rowNum = driver.findElement(By.xpath(xpath)).getText();
		return rowNum;
	}

	
	public List<String> getStockUOMValues(String rowNum){
		
		WebElement element = driver.findElement(By.xpath("//div[contains(@class,'stock-items-grid')]//div[contains(text(),'Stock UOM')]"));
		String colClass =  element.getAttribute("class");
		String columnNumber = colClass.substring(colClass.lastIndexOf("-") + 1);
		
		String tdClass= "x-grid3-td-"+columnNumber;
		
		clickOnTableCell(rowNum , tdClass);
		
		waitForExtJSAjaxComplete(10);
		
		clickOnLookUpIconInGrid();
		
		return getValuesFromGridLookup("@id", getXWindowId("Select a Unit Of Measure"), "Reference");
		
	} 
	
	/**
	 * Helper method to click on table cell
	 * @param rowNum row number of the row
	 * @param columnClass class of table cell
	 */
	 
	public void clickOnTableCell(String rowNum, String columnClass){
		
		String xpath = XPATH_STOCK_ITEM_LINES_GRID_BODY+"//div["+rowNum+"]//td[contains(@class,'"+columnClass+"')]";
		
		WebElement ele =McsElement.getElementByXpath(driver, xpath);
		
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		
		waitForExtJSAjaxComplete(10);
	}
	
	public void clickOnLookUpIconInGrid(){
		
		 String xpath = XPATH_STOCK_ITEM_LINES_GRID_EDITOR+"//button[contains(@style,'details.png')]";

		 waitForExtJSAjaxComplete(10);

		 WebElement ele =  McsElement.getElementByXpath(driver, xpath);
		 
		 Actions action = new Actions(driver);
		 
		 action.doubleClick(ele).perform();
		 
		 waitForExtJSAjaxComplete(10);
		 
		// ele.click();
		 
		 ((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		 
		
	}
	 
	 /**
	  * Helper method to fill text field value of a Grid Element 
	  * 
	  */
	 public void setGridLineTextField(String value){

		 String xpath = XPATH_STOCK_ITEM_LINES_GRID_EDITOR+"//input[@type='text']";

		 WebElement textField = McsElement.getElementByXpath(driver, xpath);

		 textField.sendKeys(value);

		 textField.sendKeys(Keys.ENTER);
	 }

	 /**
	  * Helper method to fill Look up value of a Grid Element 
	  * 
	  */
	 public void setGridLineLookUpValue( String windowTitle ,String name, String column){

		 clickOnLookUpIconInGrid();

		 waitForExtJSAjaxComplete(20);

		 setValueGridLookupWithFilters("@id", getXWindowId(windowTitle), name, column);

	 }
///////////////////////////////RIGHTS TAB//////////////////////////////////////////////
	
	public void clickRightsTab() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", WAREHOUSE_WINDOW_CLASS, "span", "text()", "Rights", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Click rights tab", true);
	}
	
	public void clickUseSpecificRights() {
		McsElement.getElementByXpath(driver, "//div[contains(@class,'warehouse-rights-tab')]//input[@name='useSpecificRights']").click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Click use specific rights", true);
	}

	public void clickAddAccounts() {
		McsElement.getElementByXpath(driver, "//div[contains(@class,'warehouse-rights-tab')]//button[contains(text(),'Add Account(s)')]").click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Click add accounts", true);
	}

	public void clickAddGroups() {
		McsElement.getElementByXpath(driver, "//div[contains(@class,'warehouse-rights-tab')]//button[contains(text(),'Add Group(s)')]").click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Click add groups", true);
	}

	/**
	 * Helper method to set account or group value
	 * @param name to be set
	 * @param attributeValue : realid - group/account_list
	 */
	public void setAccountOrGroup(String name,String attributeValue){
		
		setValueGridLookupWithFilters("@realid",attributeValue, name, "Name");
		
		Reporter.log("select account/group "+name, true);
	}
	
	public void checkAllRightsFirstRow() {
		
		McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-grid3-check-col') and contains(@class,'x-grid3-cc')])[1]").click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Click all rights", true);
	}

	/**
	 * Helper method to check if user is added to user grid
	 * */	
	public boolean isUserAddedToUserRightsGrid(String userName){
	
		return McsElement.isElementPresent(driver, XPATH_WAREHOUSE_RIGHTS_WINDOW+"//div[contains(@class,'x-grid3-col-accountGroup') and text()='"+userName+"']");
		
	}
	
	/**
	 * Helper method to check All Rights are given to selected user accounts
	 * */	
	
	public boolean isAllRightsBoxCheckedForFirstRow()
	{
		String element = McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-grid3-check-col') and contains(@class,'x-grid3-cc')])[1]").getAttribute("class");
		if(element.contains("x-grid3-check-col-on"))
		{
			return true;
		}else{
			return false;
		}
	}
	
	
	// ///////////////////Verify UnEditable Fields ////////////

		public boolean isWarehouseCodeUnEditable() {
			String unEditableWarCode = McsElement
					.getElementByPartAttributeValueAndParentElement(driver, "div",
							"@id", getXWindowId(EDIT_WAREHOUSE_WINDOW_HEADER), "input", "@name",
							"code", true, true).getAttribute("class");

			return (unEditableWarCode.contains("readonly") || unEditableWarCode
					.contains("not-editable")) ? true : false;
		}

		public boolean isWarehouseRefUnEditable() {
			String unEditableWareRef = McsElement
					.getElementByPartAttributeValueAndParentElement(driver, "div",
							"@id",getXWindowId(EDIT_WAREHOUSE_WINDOW_HEADER), "input", "@name",
							"reference", true, true).getAttribute("class");

			return (unEditableWareRef.contains("readonly") || unEditableWareRef
					.contains("not-editable")) ? true : false;

		}

		public boolean isTimeZoneUnEditable() {
			String unEditableTimeZone = driver.findElement(
					By.xpath("(//div[contains(@id,'" + getXWindowId(EDIT_WAREHOUSE_WINDOW_HEADER)
							+ "')]//input[@name='timezone']//..//input)[last()]"))
					.getAttribute("class");

			return (unEditableTimeZone.contains("readonly") || unEditableTimeZone
					.contains("not-editable")) ? true : false;

		}

		public boolean isDescriptionUnEditable() {
			String unEditableDescription = McsElement
					.getElementByPartAttributeValueAndParentElement(driver, "div",
							"@id", getXWindowId(EDIT_WAREHOUSE_WINDOW_HEADER), "textarea", "@name",
							"description", true, true).getAttribute("class");

			return (unEditableDescription.contains("readonly") || unEditableDescription
					.contains("not-editable")) ? true : false;
		}

		public boolean isAdres1UnEditable() {
			String unEditableAdres1 = McsElement
					.getElementByPartAttributeValueAndParentElement(driver, "div",
							"@id",getXWindowId(EDIT_WAREHOUSE_WINDOW_HEADER), "input", "@name",
							"address_1", true, true).getAttribute("class");

			return (unEditableAdres1.contains("readonly") || unEditableAdres1
					.contains("not-editable")) ? true : false;
		}

		public boolean isAdres2UnEditable() {
			String unEditableAdres2 = McsElement
					.getElementByPartAttributeValueAndParentElement(driver, "div",
							"@id",getXWindowId(EDIT_WAREHOUSE_WINDOW_HEADER), "input", "@name",
							"address_2", true, true).getAttribute("class");

			return (unEditableAdres2.contains("readonly") || unEditableAdres2
					.contains("not-editable")) ? true : false;
		}

		public boolean isPhoneUnEditable() {
			String unEditablePhone = McsElement
					.getElementByPartAttributeValueAndParentElement(driver, "div",
							"@id",getXWindowId(EDIT_WAREHOUSE_WINDOW_HEADER), "input", "@name",
							"phone", true, true).getAttribute("class");

			return (unEditablePhone.contains("readonly") || unEditablePhone
					.contains("not-editable")) ? true : false;
		}

		public boolean isZipUnEditable() {
			String unEditableZip = McsElement
					.getElementByPartAttributeValueAndParentElement(driver, "div",
							"@id",getXWindowId(EDIT_WAREHOUSE_WINDOW_HEADER), "input", "@name",
							"zip_code", true, true).getAttribute("class");
			return (unEditableZip.contains("readonly") || unEditableZip
					.contains("not-editable")) ? true : false;
		}

		public boolean isCityUnEditable() {
			String unEditableCity = McsElement
					.getElementByPartAttributeValueAndParentElement(driver, "div",
							"@id",getXWindowId(EDIT_WAREHOUSE_WINDOW_HEADER), "input", "@name",
							"city", true, true).getAttribute("class");

			return (unEditableCity.contains("readonly") || unEditableCity
					.contains("not-editable")) ? true : false;
		}

		public boolean isStateUnEditable() {
			String unEditableState = McsElement
					.getElementByPartAttributeValueAndParentElement(driver, "div",
							"@id",getXWindowId(EDIT_WAREHOUSE_WINDOW_HEADER), "input", "@name",
							"state", true, true).getAttribute("class");

			return (unEditableState.contains("readonly") || unEditableState
					.contains("not-editable")) ? true : false;
		}

		public boolean isCountryUnEditable() {
			String unEditableCountry = driver.findElement(
					By.xpath("(//div[contains(@id,'" +getXWindowId(EDIT_WAREHOUSE_WINDOW_HEADER)
							+ "')]//input[@name='country']//..//input)[last()]"))
					.getAttribute("class");
			return (unEditableCountry.contains("readonly") || unEditableCountry
					.contains("not-editable")) ? true : false;
		}
	
	
	/**
	 * Helper method to verify Reference field is Editable or not
	 * */
	public boolean verifyEditabilityOfRefernceField(){

		WebElement refField = McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", "stock-items-grid", "div", "@class", "x-grid3-col-reference", true, true).findElement(By.xpath(".."));
		
		return isFieldEditable(refField);
		
	}

	/**
	 * Helper method to verify Unit Of Measure field is Editable or not
	 * */
	public boolean verifyEditabilityOfUOMField(){

		String colClass =  McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", "stock-items-grid", "div", "text()", "Product UOM", true, true).getAttribute("class");

		//UOM field column position changes from portal to portal. So getting column number in Run time and using it for xpath.
		String parentAttribute = "x-grid3-col-"+ colClass.substring(colClass.length() - 1);
		
		WebElement uomField = McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", "stock-items-grid", "div", "@class", parentAttribute, true, true).findElement(By.xpath(".."));

		return isFieldEditable(uomField);
	}
	
	
	/**
	 * Helper method to check if the passed field is editable or not
	 * */
	private boolean isFieldEditable(WebElement element){
		
		String classAttrVal =element.getAttribute("class");
		
		System.err.println(classAttrVal);
		return (classAttrVal.contains("not-editable"))?false:true;
		
	}
	

	/**
	 * Helper method to get Add Ware House Window title
	 * */
	public String getAddWareHouseWindowTitle(){
		
		return ADD_WAREHOUSE_WINDOW_HEADER;
	}
	

	/**
	 * Helper method to select all the stock items in Warehouse edit page
	 * */
	public void checkAllStockItems() {
		McsElement
				.getElementByXpath(
						driver,
						"//div[contains(@class,'stock-items-grid')]//div[contains(@class,'hd-checker')]/div")
				.click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Select Stock items in Warehouse Edit page", true);
	}
	

	/**
	 * Helper method to add ware house with required fields
	 * @param warehouseReference - Warehouse Reference value
	 * @param warehouseCode - Warehouse code value
	 * @param timeZone - timezone to select
	 * */
	public void addWareHouse(String warehouseReference , String warehouseCode,String type, String timeZone){
		
		setReference(warehouseReference,ADD_WAREHOUSE_WINDOW_HEADER);
		
		setCode(warehouseCode,ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);
		
		setWarehouseType(type,ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(10);

		setTimeZone("Label",timeZone,ADD_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		//Fill Mandatory fields and click on Save & Close		
		saveAndClose(ADD_WAREHOUSE_WINDOW_HEADER);
		
	}
	
	/**
	 * Helper method to add ware house with minimum required fields 
	 * @param warehouseReference - Warehouse Reference value
	 * @param warehouseCode - Warehouse code value
	 * */
	public void addWareHouse(String warehouseReference, String warehouseCode){
		
		
		String timeZone = "(GMT+01:00) Amsterdam, Berlin, Bern, Rome, Stockholm, Vienna";
		String type= "Staffed";
		
		addWareHouse( warehouseReference ,  warehouseCode, type ,timeZone );
	}
	
	/**
	 * Helper method to get value from First row of the grid
	 * */
	public String getStockItemRowValue(String colName){
		WebElement element = driver.findElement(By.xpath("//div[contains(@class,'stock-items-grid')]//div[contains(text(),'"+colName+"')]"));
		
		String colClass =  element.getAttribute("class");
		String columnNumber = colClass.substring(colClass.lastIndexOf("-") + 1);
		//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
		
		//UOM field column position changes from portal to portal. So getting column number in Run time and using it for xpath.
		String parentAttribute = "x-grid3-col-"+columnNumber;
		
		WebElement gridParentElement = driver.findElement(By.xpath("//div[contains(@class,'stock-items-grid')]//div[contains(@class,'"+parentAttribute+"')]")).findElement(By.xpath(".."));
		String attrVal =gridParentElement.getText();
		return attrVal;
	}
	
	
	/**
	 * Helper method to clear selected rows in grid
	 * */
	public void clearFilterGridSelection(){
		
		WebElement gridChecker = McsElement.getElementByXpath(driver, "//div[@id='portalcontainer_tabs']//div[contains(@class,'stock')]//div[contains(@class,'x-form-item')]/following-sibling::div[not(contains(@class,'x-hide-display'))]//div[@class='x-grid3']//div[@class='x-grid3-hd-checker']/..");
		
		WebElement checkAll =  driver.findElement(By.xpath( "//div[contains(@class,'stock')]//div[contains(@class,'x-form-item')]/following-sibling::div[not(contains(@class,'x-hide-display'))]//div[@class='x-grid3']//div[@class='x-grid3-hd-checker']"));
		String className = gridChecker.getAttribute("class");

		//Check if Grid select all rows checkbox is checked.
		//If unchecked check it
		if(!className.contains("x-grid3-hd-checker-on")){
			
			
			checkAll.click();
			
			waitForExtJSAjaxComplete(10);
		}
		
		//Uncheck select all rows checkbox to clear the grid selection

		checkAll.click();
		
		waitForExtJSAjaxComplete(10);
		
	}
	
	/**
	 * Helper method to verify Reserved Stock field is Editable or not
	 * */
	public boolean verifyEditabilityOfReservedStock(String colName){

		WebElement element = driver.findElement(By.xpath("//div[contains(@class,'stock-items-grid')]//div[contains(text(),'"+colName+"')]"));
		
		String colClass =  element.getAttribute("class");
		
		String columnNumber = colClass.substring(colClass.lastIndexOf("-")+1);
		
		String parentAttribute = "x-grid3-col-"+columnNumber;
		
		WebElement uomField = McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", "stock-items-grid", "div", "@class", parentAttribute, true, true).findElement(By.xpath(".."));

		return isFieldEditable(uomField);
		
	}
	
	
	/**
	 * Helper method to extract attribute value from html st
	 * @param attr : attribute to extract from HTML 
	 * @param text : HTML text
	 * @return attribute value
	 */
	public String findAttributeValueFromHtmlString(String attr, String text){
		
		String HTML_ATTR_TAG_PATTERN = "\\s*(?i)"+attr+"\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";
		
		Pattern pattern = Pattern.compile(HTML_ATTR_TAG_PATTERN);
		
		Matcher matcher = pattern.matcher(text);
		
		matcher.find();

		//Return first match
		return matcher.group(1);
		
		
	}
	
	/**
	 * Helper methos to click on reset button
	 */
	public void clickResetButton() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", WAREHOUSE_WINDOW_CLASS, "button", "@class",
				"x-btn-text", "text()", "Reset", true, true).click();
		Reporter.log("Click reset button", true);
	}
	
	
	/**
	 * Helper method to verify Reset Button is disabled or not
	 * */
	
	public boolean isResetButtonDisabled(String windowTitle) {
		WebElement  reset= McsElement.getElementByXpath(driver,"//div[@id='"+ getXWindowId(windowTitle)+"']"+"//button[contains(@class,'x-btn-text') and text()='Reset']/ancestor::table[contains(@class,'x-btn')]");

		return isFieldDisabled(reset);
	}
	
	/**
	 * Helper method to check if the passed field is disabled or not
	 * */
	
	public boolean isFieldDisabled(WebElement element)
	{
		String classAttrVal =element.getAttribute("class");
		
		return (classAttrVal.contains("noedit") ||classAttrVal.contains("readonly") || classAttrVal.contains("disabled") )?true:false;
		
	}
} 



