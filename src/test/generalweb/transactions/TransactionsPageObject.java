package test.generalweb.transactions;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class TransactionsPageObject extends AbstractMcsTestSuite {

	protected final String XPATH_CHOOSE_BUTTON = "//div[contains(@id,'portalcontainer_tabs')]//div[contains(@class,'x-form-item') and not (contains(@class,'x-hide-display'))]//button[contains(@class,'x-btn-text icon-transaction') and contains(text(),'Choose')]";	
	
	protected final String NEW_TRANSACTION_WINDOW_CLASS = "x-window stock x-resizable-pinned";
	
	protected final String XPATH_NEW_TRANSACTION_LINES_GRID_EDITOR = "//div[contains(@class, 'x-grid-panel') and not (contains(@style, 'visibility: hidden'))]";

	protected final String NEW_GOODS_ISSUE_TRANSACTION_BUTTON ="//div[contains(@class, 'x-form-item') and not (contains(@class, 'x-hide-display'))]//button[contains(@class,'x-btn-text icon-goods-issue')]";
	
	protected final String NEW_GOODS_RECEIPT_TRANSACTION_BUTTON = "//button[contains(@class,'x-btn-text icon-goods-receipt')]"; 
	
	protected final String GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER = "Goods Receipt";
	
	protected final String BLOCK_TRANSACTION_WINDOW_HEADER = "Block";
	
	protected final String UNBLOCK_TRANSACTION_WINDOW_HEADER = "Unblock";
	
	protected final String GOODS_ISSUE_TRANSACTION_WINDOW_HEADER = "Goods Issue";
	
	protected final String STOCK_SCRAPPING_TRANSACTION_WINDOW_HEADER = "Stock Scrapping";
	
	protected final String STOCK_TRANSFER_TRANSACTION_WINDOW_HEADER = "Stock Transfer";
	
	protected final String RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER = "Return to Supplier";

	protected final String RETURN_FROM_CUSTOMER_TRANSACTION_WINDOW_HEADER = "Goods Return";
	
	protected final String STOCK_CORRECTION_WINDOW_HEADER = "Stock Correction";
	
	protected final String XPATH_NEW_TRANSACTION_WINDOW ="//div[contains(@class,'x-window stock x-resizable-pinned')]";
	
	protected final String XPATH_NEW_TRANSACTION_LINES_GRID = XPATH_NEW_TRANSACTION_WINDOW+"//div[@class='x-grid3']";
	
	protected final String XPATH_NEW_TRANSACTION_LINES_GRID_BODY = XPATH_NEW_TRANSACTION_LINES_GRID+"//div[@class='x-grid3-body']";
	
	protected final String XPATH_NEW_TRANSACTION_LINES_GRID_EDITOR_FULL = "//div[contains(@class,'x-grid-editor') and not(contains(@style,'visibility: hidden'))]";
	
	protected final String XPATH_WAREHOUSE_TOOLBAR ="//div[contains(@class,'x-form-item') and not(contains(@class,'x-hide-display'))]//span[@class='x-btn-group-header-text' and text()='Warehouse']/ancestor::div[contains(@class,'button-icon-extra-spacing')]//input";
	
	//*********************TRANSACTION LINES**********************************************
	protected final String TRANSACTION_LINE_PRODUCT_REF_CLASS= "x-grid3-td-productRef";
	
	protected final String TRANSACTION_LINE_QUANTITY_CLASS= "x-grid3-td-quantity";
	
	protected final String TRANSACTION_LINE_LOCATION_CLASS = "x-grid3-td-location";
	
	protected final String TRANSACTION_LINE_PURCH_ORG_CLASS = "x-grid3-td-purchorganization";
	
	protected final String TRANSACTION_LINE_PRODUCT_CODE_CLASS = "x-grid3-td-productCode";
	
	protected final String TRANSACTION_LINE_UOM_CLASS = "x-grid3-td-uom";
	
	protected final String TRANSACTION_LINE_DEFAULT_LOCATION_CLASS="x-grid3-td-location";
	
	protected final String TRANSACTION_LINE_REMARK_CLASS = "x-grid3-td-remark";
	 
	protected final String TRANSACTION_LINE_STATUS = "x-grid3-td-status ";
	
	protected final String TRANSACTION_LINE_LOT_CLASS = "x-grid3-td-lot";
	
	protected final String TRANSACTION_LINE_DESTINATION_LOCATION = "x-grid3-td-destinationLocation";
	
	protected final String TRANSACTION_LINE_QUANTITY_ORDERED_CLASS= "x-grid3-td-quantityordered";
	
	protected final String TRANSACTION_LINE_PRODUCT_CLASS = "x-grid3-td-product";
	
	protected final String STOCK_ITEMS_WINDOW_HEADER = "in warehouse";
	
	protected final String XPATH_PAGINATION = "//div[contains(@id,'portalcontainer_tabs')]//div[contains(@class,'x-form-item') and not (contains(@class,'x-hide-display'))]//div[contains(@class, 'x-toolbar-layout-ct')]//td[@class='x-toolbar-right']//span[contains(@class,'btn-group-header')]";
	
	public void goToTransactions() {
		driver.get(configuration.getApplicationUrl()
				+ "/frame.php?relay=MCS_PORTAL_ST-ACTIVATE[TRANSACTIONS]");
	};

	
	/**
	 * Helper method to click Choose button.
	 * 
	 * */

	public void clickChooseButton() {

		McsElement.getElementByXpath(driver, XPATH_CHOOSE_BUTTON).click();
	}

	/**
	 * Helper method to click New Good Receipt transaction toolbar button
	 * */
	
	public void clickNewGoodsReceiptToolBarButton(){
		
		McsElement.getElementByXpath(driver, NEW_GOODS_RECEIPT_TRANSACTION_BUTTON).click();
		
	}
	
	/**
	 * Helper method to click Goods Issue.
	 * @param transactionType
	 * */
	public void selectTransactionType(String transactionType) {

		clickChooseButton();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Actions action = new Actions(driver);

		WebElement transType = McsElement
				.getElementByXpath(
						driver,
						"//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//span[contains(@class,'x-menu-item-text') and text()='"
								+ transactionType + "']/ancestor::a");

		action.moveToElement(transType, 5, 5).click().perform();

	}

	/**
	 * Helper method to get value of Default Warehouse.
	 * 
	 * */

	public String getDefaultWarehouseValue(String windowTitle) {
		return McsElement
				.getElementByXpath(driver,
						"//div[@id='" + getXWindowId(windowTitle)+"'] //input[@name='warehouse']/following-sibling::input[@type='text']")
				.getAttribute("value");
	}

	/**
	 * Helper method to set Reference in new Transaction Window
	 * */
	public void setReference(String reference, String windowTitle) {
		
		WebElement rerferenceElement= McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "input", "@name", "reference", true, true);
		rerferenceElement.clear();
		rerferenceElement.sendKeys(reference);
		Reporter.log("Set reference", true);
	}
	
	/**
	 * Helper method to set Remarks in new Transaction Window
	 * */
	public void setRemarks(String remarks, String windowTitle) {
		WebElement remarksEle= McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "textarea", "@name", "remarks", true, true);
		
		remarksEle.clear();
		
		remarksEle.sendKeys(remarks);
		
		Reporter.log("Set remarks", true);
	}
	
	/**
	 * Helper method to set WareHouse in new Transaction Window
	 * */
	public void setWareHouse(String column, String name, String windowTitle) {
		
		clickLookup("@id",getXWindowId(windowTitle), "warehouse","Select A Warehouse");
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select A Warehouse"), name, column);
		
		Reporter.log("Ware House was selected", true);
	}
	
	
	/**
	 * Helper method to set Destination WareHouse in new Transaction Window
	 * */
	public void setDestinationWareHouse(String column, String name, String windowTitle) {
		
		clickLookup("@id",getXWindowId(windowTitle), "destinationWarehouse","Select A Warehouse");
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select A Warehouse"), name, column);
		
		Reporter.log("Ware House was selected", true);
	}
	/**
	 * Helper method to select Supplier in new Goods Receipt transaction creation window
	 */
	 
	public void selectSupplier(String column, String name, String windowTitle){
		
		clickLookup("@id",getXWindowId(windowTitle), "company","Select a Supplier");
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Supplier"), name, column);
		
		Reporter.log("Supplier selected", true);
		
		
	}
	
	
	/**
	 * Helper method to set Expected Receipt Date in new Transaction Window
	 * */
	public void setTransactionDate(String reference, String windowTitle) {
		
		WebElement rerferenceElement= McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "input", "@name", "performedOn", true, true);
		rerferenceElement.clear();
		rerferenceElement.sendKeys(reference);
		Reporter.log("Set Expected Receipt Date", true);
	}
	
	/**
	 * Helper method to set status of Transaction
	 * */
	public void setStatus(String status, String  windowTitle) {
		
		
		String id = McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id",
				getXWindowId(windowTitle), "input", "@name", "status", true, true).getAttribute("id");
				
		DropDown.setExtJsComboValue(driver, id, status);
		
		waitForExtJSAjaxComplete(25);
		
		Reporter.log("Set Status to" + status, true);
	}
	
	
	/**
	 * Helper method to click on Add button in New transaction creation window 
	 * **/
	public void clickAddTransactionLines(){
		
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", NEW_TRANSACTION_WINDOW_CLASS, "button", "@class", "x-btn-text icon-insert-row", "text()", "Add", true, true).click();
		
		Reporter.log("Clicking Add button of Transaction Lines", true);
	}
	
	
	/**
	 * Helper method to set  Product reference for transaction line from Products nd Services
	 * @param rowNum: row number of Transaction line
	 * @param name of Product reference
	 * @param column of Lookup to use for selection
	 */
	public void setTransactionLineProductReferenceFromProductsAndServices(String rowNum, String name, String column  ){
		
		 setProductReference( rowNum, "Products And Services",  name,  column);
		
	}
	
	
	public void setProductReference(String rowNum, String tabText , String name, String column){
		  
		  this.setGridLineLookUpValueInTab(tabText,"Select a Product or Service", name, column);
		  
	 }
		 
	
	/**
	 * Helper method to set Quantity of Transaction line
	 * @param rowNum : Row number of Transaction line in Transaction lines grid
	 * @param value : Quantity to set
	 * **/
	
	
	public void setTransactionLineQuantity(String rowNum, String value){
		
		clickOnTableCell(rowNum, TRANSACTION_LINE_QUANTITY_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setGridLineTextField(value);
	}
	
	/**
	 * Helper method to set Quantity of Transaction line
	 * @param rowNum : Row number of Transaction line in Transaction lines grid
	 * @param value : Quantity to set
	 * **/
	
	
	public void setTransactionLineQuantityOrdered(String rowNum, String value){
		
		clickOnTableCell(rowNum, TRANSACTION_LINE_QUANTITY_ORDERED_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setGridLineTextField(value);
	}
	
	
	
	/**
	 * Helper method to set locations from All locations tab of Locations look up
	 * @param rowNum: row number of Transaction line
	 * @param name of Location
	 * @param column of Lookup to use for selection
	 */
	public void setTransactionLineLocationFromAllLocations(String rowNum, String name, String column  ){
		
			clickOnTableCell(rowNum, TRANSACTION_LINE_LOCATION_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			this.setGridLineLookUpValueInTab("All Locations in Warehouse","Select a Storage Location", name, column);
			
	}

	
	/**
	 * Helper method to set destination locations from All locations tab of Locations look up
	 * @param rowNum: row number of Transaction line
	 * @param name of Location
	 * @param column of Lookup to use for selection
	 */
	public void setTransactionLineDestinationLocationFromAllLocations(String rowNum, String name, String column  ){
		
			clickOnTableCell(rowNum, TRANSACTION_LINE_DESTINATION_LOCATION);
			
			waitForExtJSAjaxComplete(20);
			
			this.setGridLineLookUpValueInTab("All Locations in Warehouse","Select a Storage Location", name, column);
			
	}
	
	/**
	 * Helper method to select Purchasing Organization of Transaction line
	 * @param rowNum : Row number of Transaction line in Transaction lines grid
	 * @param name of Purchasing Org
	 * @param column of Lookup to use for selection
	 * **/
	public void setPurchasingOrgForTransactionLine(String rowNum,  String name, String column  ){
		
		clickOnTableCell(rowNum, TRANSACTION_LINE_PURCH_ORG_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		this.setGridLineLookUpValue("Select A Purchasing Organization", name, column);
		
	}
	
	
	/**
	 * Helper method to set Product reference for Text line
	 * @param rowNum  of text line
	 * @param purchasingRefVal : value to set
	 */
	public void setProductReferenceForTextLine(String rowNum,  String purchasingRefVal){
		
		waitForExtJSAjaxComplete(20);
		
		setGridLineTextField(purchasingRefVal);
		
	}
	
	/**
	 * Helper method to set Product code for Text line
	 * 
	 * @param rowNum  of text line
	 * @param prodCodeVal : value to set
	 */
	public void setProductCodeForTextLine(String rowNum,  String prodCodeVal){
		
		clickOnTableCell(rowNum, TRANSACTION_LINE_PRODUCT_CODE_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setGridLineTextField(prodCodeVal);
		
		
	}
	
	/**
	 * Helper method to set UOM value of text line
	 * @param rowNum of text line
	 * @param uom to set
	 * @param column : to be used in Lookup
	 */
	public void setUOMForTextLine(String rowNum,  String uom, String column){
		
		clickOnTableCell(rowNum, TRANSACTION_LINE_UOM_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setGridLineLookUpValue("Select a Unit Of Measure",uom, column);
	}
	
	/**
	 * Helper method to set Location value of text line
	 * @param rowNum of text line
	 * @param name of location to set
	 * @param column : to be used in Lookup
	 */
	public void setLocationForTextLine(String rowNum,  String name, String column  ){
		
			clickOnTableCell(rowNum, TRANSACTION_LINE_LOCATION_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			this.setGridLineLookUpValue("Select a Storage Location", name, column);
			
	}
	
	/**
	 * Helper method to set Location value of text line
	 * @param rowNum of text line
	 * @param remark to set
	 */
	public void setTransactionLineRemark(String rowNum, String remark){
		
		waitForExtJSAjaxComplete(20);
		
		clickOnTableCell(rowNum, TRANSACTION_LINE_REMARK_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setGridLineTextField(remark);
		
		waitForExtJSAjaxComplete(25);
		
	}
	
	/**
	 * Helper method to set Lot value of transaction line
	 * @param rowNum of text line
	 * @param remark to set
	 */
	public void setTransactionLineLot(String rowNum, String lot){
		
		clickOnTableCell(rowNum, TRANSACTION_LINE_LOT_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setGridLineTextField(lot);
		
	}
	
	//***************************Transaction Line getter methods***********************
	

	/**
	 * Helper method to get Product Code
	 * @param rowNum : Row num of the Transaction line
	 * @return value of Product Code
	 * 
	 * */
	public String getProductCodeOfTransactionLine(String rowNum){
		
		return	getCellText(rowNum,TRANSACTION_LINE_PRODUCT_CODE_CLASS);
	}
	

	/**
	  * Helper method to get reference
	  * @param rowNum : Row num of the Transaction line
	  * @param columnClass : Pass the class of column containing cell
	  * @return value of Quantity
	  */
	 public String getProductRefOfTransactionLine(String rowNum){
	  
	  return getCellText(rowNum, TRANSACTION_LINE_PRODUCT_REF_CLASS);
	  
	 }
	
	 /**
	  * Helper method to get reference
	  * @param rowNum : Row num of the Transaction line
	  * @param columnClass : Pass the class of column containing cell
	  * @return value of Quantity
	  */
	 public String getProductOfTransactionLine(String rowNum){
	  
	  return getCellText(rowNum, TRANSACTION_LINE_PRODUCT_CLASS);
	  
	 }
	
	/**
	 * Helper method to get Quantity
	 * @param rowNum : Row num of the Transaction line
	 * @param columnClass : Pass the class of column containing cell
	 * @return value of Quantity
	 */
	public String getQuantityOfTransactionLine(String rowNum, String columnClass){
		
		return	getCellText(rowNum,TRANSACTION_LINE_QUANTITY_CLASS);
		
	}

	/**
	 * Helper method to get UOM
	 * @param rowNum : Row num of the Transaction line
	 * @return value of UOM
	 * 
	 * */
	public String getUOMOfTransactionLine(String rowNum){
	
	return	getCellText(rowNum,TRANSACTION_LINE_UOM_CLASS);
	}

	/**
	 * Helper method to get Default Location
	 * @param rowNum : Row num of the Transaction line
	 * @return value of location
	 * 
	 * */
	public String getDefaultLocOfTransactionLine(String rowNum){
	
	return	getCellText(rowNum,TRANSACTION_LINE_DEFAULT_LOCATION_CLASS);
	}
	
	
	/**
	  * Helper method to get Status
	  * @param rowNum : Row num of the Transaction line
	  * @param columnClass : Pass the class of column containing cell
	  * @return value of Quantity
	  */
	 public String getStatusOfTransactionLine(String rowNum){
	  
	  return getCellText(rowNum, TRANSACTION_LINE_STATUS);
	  
	 }
	 
	 
	//****************************Window button click methods*****************************
	public void saveAndClose() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", NEW_TRANSACTION_WINDOW_CLASS, "button", "text()", "Save",
				"text()","Close", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Save and close", true);
	}

	public void saveAndClose(String windowTitle) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXWindowId(windowTitle), "button", "text()", "Save",
				"text()","Close", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Save and close", true);
	}

	public void save() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", NEW_TRANSACTION_WINDOW_CLASS, "button", "text()", "Save", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Save", true);
	}
	
	
	public void execute() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", NEW_TRANSACTION_WINDOW_CLASS, "button", "text()", "Execute", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Execute", true);
	}
	
	/**
	 * Helper method to close transaction window.
	 * 
	 * */

	public void close(String windowTitle) {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@id", getXWindowId(windowTitle), "button", "text()", "Close",
				true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Close", true);
	}
	
	
	
	/**
	 * Helper Method to verify no items listed in Product Reference 
	 */
	public String verifyNoItemsListedInProductReference() {
		//waitFocusAndClick("//div[contains(@class, 'x-grid-panel')//button[contains(@style,'details.png')]");
		String xpath = XPATH_NEW_TRANSACTION_LINES_GRID_EDITOR+"//div[contains(@class,'x-grid-editor') and not(contains(@style,'visibility: hidden'))]//input[@type='text']";
		
		WebElement textField = McsElement.getElementByXpath(driver, xpath);
		
		String inputName = textField.getAttribute("name");
		this.clickLookup(NEW_TRANSACTION_WINDOW_CLASS, inputName);
		waitForExtJSAjaxComplete(25);
		
		String id = getXWindowId("Select a Product or Service");
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", id, "span", "text()", "Products And Services", true, true).click();
		
		waitForExtJSAjaxComplete(25);
		String gridRows = "//div[@realid='prods_servs']//div[contains(@class,'x-grid3-body')]/*";
		return McsElement.getElementByXpath(driver, gridRows).getText();
		
	}
	
	//********************************Helper methods***************
	/**
	 * Helper method to click on table cell
	 * @param rowNum row number of the row
	 * @param columnClass class of table cell
	 */
	 
	public void clickOnTableCell(String rowNum, String columnClass){
		
		String xpath = XPATH_NEW_TRANSACTION_LINES_GRID_BODY+"//div["+rowNum+"]//td[contains(@class,'"+columnClass+"')]";
		
		WebElement ele = McsElement.getElementByXpath(driver, xpath);//.click();
		
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
	}
	
	/**
	 * Helper method to get Cell content
	 * @param rowNum Row num of Cell
	 * @param columnClass: Class of table cell
	 * @return value of the cell
	 */
	
	public String getCellText(String rowNum, String columnClass){
		
		String xpath = XPATH_NEW_TRANSACTION_LINES_GRID_BODY+"//div["+rowNum+"]//td[contains(@class,'"+columnClass+"')]//div";
		
		
		return McsElement.getElementByXpath(driver, xpath).getText();
	}
	
	
	/**
	 * Helper method to click on Tabs in Lookup window
	 * @param tabText : text of tab to click
	 */
	public void clickTabInLookUpWindow(String tabText){
		
		String xpath = "//div[contains(@class,'x-window-noborder x-resizable-pinned')]//span[contains(@class,'x-tab-strip-text') and contains(text(),'"+tabText+"')]//ancestor::a";
		
		McsElement.getElementByXpath(driver, xpath).click();
	}
	
	/**
	  * Helper method to fill Look up value of a Grid Element
	  * Note: This method provides users to switch between tabs in the lookup value selection
	  * 
	  */
	 public void setGridLineLookUpValueInTab( String tabText, String windowTitle ,String name, String column){
	  
	  
	  String xpath = XPATH_NEW_TRANSACTION_LINES_GRID_EDITOR+"//div[contains(@class,'x-grid-editor') and not(contains(@style,'visibility: hidden'))]//input[@type='text']";
	  
	  WebElement textField = McsElement.getElementByXpath(driver, xpath);
	  
	  String inputName = textField.getAttribute("name");
	  
	  this.clickLookup(NEW_TRANSACTION_WINDOW_CLASS, inputName);
	  
	  waitForExtJSAjaxComplete(20);
	  
	  clickTabInLookUpWindow(tabText);
	  
	  waitForExtJSAjaxComplete(20);
	   
	  setValueGridLookupWithFilters("@id", getXWindowId(windowTitle), name, column);
	  
	 }
	 
	 
	 /**
	  * Helper method to fill text field value of a Grid Element 
	  * 
	  */
	 public void setGridLineTextField(String value){

		 String xpath = XPATH_NEW_TRANSACTION_LINES_GRID_EDITOR+"//div[contains(@class,'x-grid-editor') and not(contains(@style,'visibility: hidden'))]//input[@type='text']";

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

		 String xpath = XPATH_NEW_TRANSACTION_LINES_GRID_EDITOR+"//button[contains(@style,'details.png')]";

		 waitForExtJSAjaxComplete(20);

		 McsElement.getElementByXpath(driver, xpath).click();

		 waitForExtJSAjaxComplete(20);

		 setValueGridLookupWithFilters("@id", getXWindowId(windowTitle), name, column);

	 }
	//***************************************Transaction Grid utility functions************************	
	 
	/**
	 * Helper method to get the type of Quantity icon present 
	 * @param rowEle : Quantity cell element
	 * @return down arrow for Quantity down
	 * 			up arrow for Quantity up
				no arrow for No icons
	 */
	public String getQuantityIcon(WebElement rowEle){
		
		
		String arrowClass = rowEle.getAttribute("class");

		String backgroundImg =	rowEle.getCssValue("backgroundImage");

		boolean isDownArrow = arrowClass.toLowerCase().contains("quantity-down")&& backgroundImg.toLowerCase().contains("arrow-down.png");
			
		
		boolean isUpArrow = arrowClass.toLowerCase().contains("quantity-up")&& backgroundImg.toLowerCase().contains("arrow-up.png");
		
		//Block transaction won't have any Icon. 
		return (isDownArrow)?"down arrow":(isUpArrow)?"up arrow":"no arrow";
		
	}
	
	public WebElement getQuantityOfTransactionLine(String attribute, String attributeValue, String rowTextName, String columnName) {
		
		Timer timer = new Timer().start();
		
		waitForExtJSAjaxComplete(25);
		
		filterGrid(attribute, attributeValue, rowTextName, columnName);
		
		waitForExtJSAjaxComplete(25);
		
		int columnNumber = getGridColumnNumber(attribute, attributeValue , columnName );
		
		waitForExtJSAjaxComplete(25);
		
		int lineIDColumnNumber = getGridColumnNumber(attribute, attributeValue , "Quantity" );
	
		WebElement ele = McsElement.getElementByXpath(driver,"(//"+"div"+"[contains("+attribute+",'" + attributeValue + "') and not (contains(@class, 'x-hide-display'))]//"
		+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
		+ "and "+"text()"+ "='" + rowTextName + "'" + 
		"])[last()]//..//..//div[contains(@class,'x-grid3-col-"+lineIDColumnNumber+"')]/div");
			
		timer.stop();
		return ele;
	}
	
	
	
	/**
	 * Helper method to get ID attribute of current grid
	 * */
	public String getFilterGridID(){
		
		WebElement grid = McsElement.getElementByXpath(driver, "//div[@id='portalcontainer_tabs']//div[contains(@class,'x-form-item') and not(contains(@class,'x-hide-display'))]//div[@class='x-grid3']");
		
		return grid.getAttribute("id");
		
	}
	
	/**
	 *  Helper method to get column number in Grid 
	 */
	public int getGridColumnNumber(String attribute, String attributeValue, String columnName){
		
	//	String columnClass = McsElement.getElementByXpath(driver,"(//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'quickfilters')]//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')][last()])").getAttribute("class");
		String xpath = "//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'quickfilters')]//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')][last()]";

		//String columnClass = McsElement.getElementByXpath(driver,"(//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'quickfilters')]//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')][last()])").getAttribute("class");

		WebElement ele = driver.findElement(By.xpath(xpath));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		String columnClass =	ele.getAttribute("class");
		String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);
			
		return Integer.parseInt(columnNumber);
	}
	
	// ///////////////////Goods Issue Methods ////////////////////////
	/**
	 * Helper method to click on Goods Issue button in Tool bar
	 */
	public void clickNewGoodsIssueToolBarButton() {
		McsElement
				.getElementByXpath(driver, NEW_GOODS_ISSUE_TRANSACTION_BUTTON)
				.click();
	}

	/**
	 * Helper method to select Customer in new Goods Receipt transaction
	 * creation window
	 */

	public void selectCustomer(String column, String name, String windowTitle) {
		clickLookup("@id", getXWindowId(windowTitle), "company",
				"Select a Customer");
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Customer"),
				name, column);
		Reporter.log("Customer selected", true);
	}

	/**
	 * Helper method to select Collecting Person in new Goods Issue transaction
	 * creation window
	 */

	public void selectCollectingPerson(String column, String name,
			String windowTitle) {
		clickLookup("@id", getXWindowId(windowTitle), "performedBy",
				"Select a Person");
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Person"),
				name, column);
		Reporter.log("Collecting Person selected", true);
	}

	/**
	 * Helper method to open a record in Transaction Line
	 */
	public void openTransactionLine(String attribute, String attributeValue, String rowTextName, String columnName){
		waitForExtJSAjaxComplete(25);
		
		filterGrid(attribute, attributeValue, rowTextName, columnName);
		
		waitForExtJSAjaxComplete(15);
		//WebElement ele = Grid.checkRowInGriByTextValue(driver, rowTextName); 
		WebElement ele = Grid.checkRowInGriByTextValueAndClick(driver, rowTextName);
		new Actions(driver).doubleClick(ele).perform();
		
		waitForExtJSAjaxComplete(25);
	}
	
	/**
	 * Helper method to Select warehouse from transactions page
	 * @param warehouse
	 */
	public void selectWarehouse(String warehouse) 
		 {
			
			String id = McsElement.getElementByXpath(driver, XPATH_WAREHOUSE_TOOLBAR).getAttribute("id");
					
			DropDown.setExtJsComboValue(driver, id, warehouse);
			
			waitForExtJSAjaxComplete(25);
			
			Reporter.log("Set Warehouse to" + warehouse, true);
		}
	
	/**
	 * helper method to get Quantity Ordered Of Transaction Line
	 * @param rowNum
	 * @param columnClass
	 * @return
	 */
	public String getQuantityOrderedOfTransactionLine(String rowNum, String columnClass){
		
		return	getCellText(rowNum, columnClass);
		
	}
	
	/**
	 * Helper method to get the 10 digit ID from Goods Receipt Window
	 *
	 */
	public String getRemarksOfGoodsReceiptWindow(String windowTitle) {

		String xpath = "//div[@id='" + getXWindowId(windowTitle)
				+ "']//textarea[@name='remarks']";

		String reqID = McsElement.getElementByXpath(driver, xpath).getAttribute("value");
		
		return reqID;
	}
	
	
	/**
	 * Helper method to check if the passed field is editable or not
	 * */
	private boolean isFieldEditable(WebElement element){
		
		String classAttrVal =element.getAttribute("class");
		
		return (classAttrVal.contains("readonly") || classAttrVal
				.contains("not-editable")) ? true : false;
		
	}
	
	
	/**
	 * Helper method to verify warehouse field is Editable or not
	 * */
	public boolean isWareHouseCodeOfTransactionEditable () {
		WebElement unEditableWarCode = driver.findElement(By.xpath("//div[@id='" + getXWindowId(GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER)+"']//input[@name='warehouse']/following-sibling::input[@type='text']"));
		
		return isFieldEditable(unEditableWarCode);
	}
	
	/**
	 * Helper method to verify GR reference field is Editable or not
	 * */

	public boolean isReferenceFieldOfTransactionEditable () {
		
		WebElement unEditableRef = driver.findElement(By.xpath("//div[@id='" + getXWindowId(GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER)+"'] //input[@name='reference']"));
				
		
		return isFieldEditable(unEditableRef);

	}
	
	
	/**
	 * Helper method to verify GR Status field is Editable or not
	 * */

	public boolean isStatusFieldOfTransactionEditable() {
		
		WebElement unEditableGRStatus = driver.findElement(By.xpath("//div[@id='" + getXWindowId(GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER)+"'] //input[@name='status']"));
				
		
		return isFieldEditable(unEditableGRStatus);

	}
	
	
	/**
	 * Helper method to verify GR date field is Editable or not
	 * */

	public boolean isExpectedReceiptDateOfTransactionEditable() {
		
		WebElement unEditableGRDate = driver.findElement(By.xpath("//div[@id='" + getXWindowId(GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER)+"'] //input[@name='performedOn']"));
				
	
		return isFieldEditable(unEditableGRDate);

	}
	
	
	/**
	 * Helper method to verify GR Suplier field is Editable or not
	 * */

	public boolean isSupplierFiledOfTransactionEditable() {
		
		WebElement unEditableGRSupplier = driver.findElement(By.xpath("//div[@id='" + getXWindowId(GOODS_RECEIPT_TRANSACTION_WINDOW_HEADER)+"'] //input[@name='company']/following-sibling::input[@type='text']"));
				
	
		return isFieldEditable(unEditableGRSupplier);

	}
	
	/**
	  * Helper method to get Pagination Details
	  */
	 public int getPaginationDetails() {
	  String xpath = McsElement.getElementByXpath(driver, XPATH_PAGINATION).getText();
	  String pageNumber = xpath.substring(xpath.lastIndexOf("of") + 1).trim();
	  
	  return Integer.parseInt(pageNumber); 
	 }
	 
	/**
	 * Helper method to check row in grid by text value(Common functions in Grid.Java are not working)
	 * @param webDriver
	 * @param textValue
	 * @return
	 */
	public static WebElement checkRowInGriByTextValueAndClickCustomized(WebDriver webDriver, String textValue)  {
		WebElement webElement = webDriver.findElement(By.xpath("//*[@class='x-grid3']//td[contains(@class, 'x-grid3-cell-selectable') and not (contains(@style, 'none'))]//div[text()='"+textValue+"']"));
		webElement.click();
		Reporter.log("Check element "+textValue+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	
	
	/**
	 * Helper method to get the product row number in warehouse
	 * @param prodName
	 * @return
	 */
	public String getProductRowNumberInWareHouse(String prodName){
		return  McsElement.getElementByXpath(driver,"//div[contains(@class,'stock-items-grid')]//div[@class='x-grid3-body']//div[contains(@class,'x-grid3-col-reference') and contains(text(),'"+prodName+"')]/../..//div[contains(@class,'x-grid3-col-numberer')]").getText();
	}
	/**
	 * Helper Method to set value in Status Dropdown
	 * @param windowTitle
	 * @param comboValue
	 * @param attribute
	 * @param attributeValue
	 */
	public void setStatusValue(String windowTitle, String comboValue, String attribute, String attributeValue) {	
	
		WebElement xPath = driver.findElement(By.xpath("//div[@id='"+getXWindowId(windowTitle)+"']//label[contains(@class,'x-form-item-label') and text()='Status']/..//input"));
		
		String id = xPath.getAttribute("id");
		
		waitForExtJSAjaxComplete(10);
		
		DropDown.setExtJsComboValue(driver, id, comboValue);
		
		/*((JavascriptExecutor) driver)
		.executeScript("document.getElementById('"+id+"').value ='"+comboValue+"';");*/
		
		waitForExtJSAjaxComplete(30);
		
		Reporter.log("set Status Value to " + comboValue, true);
	}
	
	/**
	 * Helper method to get Status
	 *
	 */
	public String getStatusOfGoodsReturnWindow(String windowTitle) {

		String xpath = "//div[@id='" + getXWindowId(windowTitle)
				+ "']//input[@name='status']";

		String status = McsElement.getElementByXpath(driver, xpath).getAttribute("value");
		
		return status;
	}
	/**
	 * Find checkbox of the corresponding record in Grid by text value and double click
	 * @param textValue for search
	 * @return webelement
	 */
	public List<String> getStatusOfAllTxnLines(String textValue)  {
		Actions action=new Actions(driver);
		List<String> statusTransactionLine = new ArrayList<String>();
		List<WebElement> elements =driver.findElements(By.xpath("//div[@id='"+getFilterGridID()+"']//*[@class='x-grid3-body']"+ xpathGeneratorForTextElement(textValue)));
		for(WebElement ele:elements){
		action.doubleClick(ele).build().perform();
		Reporter.log("Check element "+textValue+" present in grid and click", true);
		waitForExtJSAjaxComplete(20);
		String status=getStatusOfGoodsReturnWindow(textValue);
		statusTransactionLine.add(status.trim());
		close(textValue);
		}
		return statusTransactionLine;
	}
	
	/**
	 * Helper method to get size of all txn line
	 * @param clsName
	 * @return size
	 */
	public int getSizeofTxnLinesBasedOnClass(String clsName)  {
		
		List<WebElement> elements =driver.findElements(By.xpath("//div[@id='"+getFilterGridID()+"']//*[@class='x-grid3-body']"+ xpathGeneratorForTextElement(clsName)));
		int size=elements.size();
		return size;
	
	}
	
}
	

