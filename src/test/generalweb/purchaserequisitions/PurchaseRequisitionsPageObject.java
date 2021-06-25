package test.generalweb.purchaserequisitions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.FileUploader;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class PurchaseRequisitionsPageObject extends  AbstractMcsTestSuite {

	protected final String ADD_REQUISIION_WINDOW_HEADER = "New Purchase Requisition";
	
	protected final String EDIT_REQUISIION_WINDOW_HEADER = "Purchase Requisition";
	
	protected final String ADD_REQUISIITONS_WINDOW_CLASS = "x-window x-resizable-pinned";
	
	protected final String XPATH_WAREHOUSE_TOOLBAR ="//div[contains(@class,'x-form-item') and not(contains(@class,'x-hide-display'))]//span[@class='x-btn-group-header-text' and text()='Warehouse']/ancestor::div[contains(@class,'button-icon-extra-spacing')]//input";
	
	protected final String ADD_PR_LINE_BUTTON = "//table[contains(@class,'mcs-btn-action')]//button[contains(text(),'Add')]";
	
	protected final String DELETE_PR_LINE_BUTTON = "//table[contains(@class,'mcs-btn-action')]//button[contains(text(),'Delete')]";
	
	protected final String PR_LINE_LINE_ID_CLASS= "x-grid3-td-id";
	
	protected final String PR_LINE_CREATED_ON_CLASS = "x-grid3-td-createdOn";
	
	protected final String PR_LINE_ORDER_QUANTITY_CLASS="x-grid3-td-orderQuantity";
	
	protected final String PRODUCT_SERVICES_WINDOW_HEADER = "Select Products or Services";
	
	protected final String ADD_FILE_XPATH = "//button[text()='Add File' and contains(@style,'icon-addfile.png')]";
	
	protected final String ADD_URL_XPATH = "//button[contains(text(),'Add URL')]";
	
	protected final String ADD_URL_CREATE_XPATH = "//div[contains(@class,'x-window')]//button[text()='Create']";
	
	protected final String DELETE_URL_XPATH = "//button[text()='Delete' and contains(@style,'icon-delete.png')]";
	
	protected final String HYPERLINK_XPATH = "//input[@name='strHyperlink']";
	
	protected final String HYPERLINK_DESCRIPTION = "//input[@name='strDescription']";
	
	protected final String HYPERLINK_REMARK = "//input[@name='strRemark']";
	
	protected final String ADD_URL_WINDOW_HEADER = "Add Document";
	
	protected final String ADD_DOC_WINDOW_HEADER = "Add Document";
	
	
	public void goToPurchasingRequisition() {
		driver.get(configuration.getApplicationUrl()
				+ "/frame.php?relay=MCS_PORTAL_ST-ACTIVATE[PURCHASE_REQS]");
	};

	//***************************Tool Bar button clicks**************************************
	public void clickOverview() {
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-tab-panel-noborder') and not(contains(@class,'x-hide-display'))]//button[contains(@class,'icon-ov-overview')]").click();
	}

	/**
	 * Helper method to click Add button.
	 * 
	 * */
	public void clickAddButton() {
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-tab-panel-noborder') and not(contains(@class,'x-hide-display'))]//button[contains(@class,'icon-ov-add')]").click();
	}

	/**
	 * Helper method to click Edit button.
	 * 
	 * */
	public void clickEditButton(){
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-tab-panel-noborder') and not(contains(@class,'x-hide-display'))]//button[contains(@class,'icon-ov-edit')]").click();
		
	}
	
	/**
	 * Helper method to click Delete button.
	 * 
	 * */
	public void clickDeleteButton() {
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-tab-panel-noborder') and not(contains(@class,'x-hide-display'))]//button[contains(@class,'icon-ov-remove')]").click();
	}
	
	/**
	 * Helper method to select WareHouse
	 * */
	public void selectWareHouse(String warehouse) {
		
		String id = McsElement.getElementByXpath(driver, XPATH_WAREHOUSE_TOOLBAR).getAttribute("id");
				
		DropDown.setComboValueDropDownSelector(driver, id, warehouse);
		
		waitForExtJSAjaxComplete(25);
		
		Reporter.log("Set Warehouse to" + warehouse, true);
	}
	//******************************************************************************************
	
	/**
	 * Helper method to click on tabs in Requisition window
	 * @param windowTitle of the Requistion window
	 * @param tabText text of tab to be clicked
	 */
	public void clickOnTab(String windowTitle, String tabText){
		
		McsElement.getElementByXpath(driver , "//div[@id='"+ getXWindowId(windowTitle)+"']"+ "//span[contains(@class,'x-tab-strip-text') and contains(text(),'"+tabText+"')]").click();
	}
	
	/**
	 * Helper Method to Click General Tab
	 */
	public void clickGeneralTab(String windowTitle){
		clickOnTab(windowTitle, "General"); 
	}
	
	/**
	 * Helper Method to Click Description Tab
	 */
	public void clickDescriptionTab(String windowTitle){
		clickOnTab(windowTitle, "Description"); 
	}
	
	/**
	 * Helper Method to Click Tracking Tab
	 */
	public void clickTrackingTab(String windowTitle){
		clickOnTab(windowTitle, "Tracking"); 
	}
	
	/**
	 * Helper Method to Click Financial Tab
	 */
	public void clickFinancialTab(String windowTitle){
		clickOnTab(windowTitle, "Financial"); 
	}
	
	/**
	 * Helper Method to Click Tracking Tab
	 */
	public void clickNotesTab(String windowTitle){
		clickOnTab(windowTitle, "Notes"); 
	}
	
	/**
	 * Helper Method to Click Related Documents Tab
	 */
	public void clickRelatedDocumentsTab(String windowTitle){
		clickOnTab(windowTitle, "Attachments"); 
	}
	
	/**
	 * Helper Method to Click Line Items Tab
	 */
	public void clickLineItemsTab(String windowTitle){
		clickOnTab(windowTitle, "Line Items"); 
	}
	
	
	/**
	 * Helper method to select Purchase Requisition 
	 * @param windowTitle of Purchase Requisition
	 * @param lineNum to be selected
	 */
	public void selectPRLine( String windowTitle , String lineNum){
		
		String xpath = "//div[@id='"+ getXWindowId(windowTitle)+"']//div[contains(@class,'x-grid3-hd-lineNr') and text()='General']/ancestor::div[contains(@class,'x-panel-body-noheader')]//div[@class='x-grid3-body']/div["+lineNum+"]"; 
		
		McsElement.getElementByXpath(driver ,xpath).click();
	}
	
	//**********************************Window buttons***************************
	public void save(String windowTitle) {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div","@id",getXWindowId(windowTitle), "button", "text()", "Save", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Save", true);
	}
	
	
	public void close(String windowTitle) {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@id", getXWindowId(windowTitle), "button", "text()", "Close", true, true).click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Close", true);
	}
	
	/**
	 * Helper method to click on Add button to add Purchase requisition lines in Requistion window
	 * @param windowTitle of Requisition window
	 */
	public void clickOnAddPRLineButton(String windowTitle){
		
		McsElement.getElementByXpath(driver , "//div[@id='"+ getXWindowId(windowTitle)+"']"+ ADD_PR_LINE_BUTTON).click();
		
	}
	
	/**
	 * Helper method to click on Delete button to add Purchase requisition lines in Requistion window
	 * @param windowTitle of Requisition window
	 */
	public void clickOnDeletePRLineButton(String windowTitle){
		
		McsElement.getElementByXpath(driver , "//div[@id='"+ getXWindowId(windowTitle)+"']"+ DELETE_PR_LINE_BUTTON).click();
		
	}


	
	//*****************************Document tab fields*************

	/**
	 * 
	 * @param column
	 *            to be used for selecting value in Lookup
	 * @param docType
	 *            to select
	 * @param windowTitle
	 *            :enter title of window where Lookup is present
	 */
	public void setDocumentType(String column, String docType,
			String windowTitle) {

		clickLookup("@id", getXWindowId(windowTitle), "documentType",
				"Select A Purchasing Document Type");

		setValueGridLookupWithFilters("@id",
				getXWindowId("Select A Purchasing Document Type"), docType,
				column);

		Reporter.log("Document Type was selected", true);
	}

	/**
	 * 
	 * @param column
	 *            to be used for selecting value in Lookup
	 * @param name
	 *            of requester
	 * @param windowTitle
	 *            :enter title of window where Lookup is present
	 */
	public void setRequester(String column, String name, String windowTitle) {

		clickLookup("@id", getXWindowId(windowTitle), "requester",
				"Select a Person");
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Person"),
				name, column);
		Reporter.log("Requester was selected", true);
	}

	/**
	 * Helper method to set Requisition Date
	 * 
	 * @param date
	 *            to enter
	 * @param windowTitle
	 *            of the Requisition
	 */

	public void setRequisitionDate(String date, String windowTitle) {
		WebElement code = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@id", getXWindowId(windowTitle), "input", "@name",
						"requisitionDate", true, true);
		code.clear();
		code.sendKeys(date);
		Reporter.log("Set reference", true);
	}

	// *****************************************General Tab****************************

	public void setLineNr(String lineNr, String windowTitle) {
		WebElement code = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@id", getXWindowId(windowTitle), "input", "@name",
						"lineNr", true, true);
		code.clear();
		code.sendKeys(lineNr);
		Reporter.log("Set lineNr", true);
	}

	public void setOrderQuantity(String orderQuantity, String windowTitle) {
		WebElement code = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@id", getXWindowId(windowTitle), "input", "@name",
						"orderQuantity", true, true);
		code.clear();
		code.sendKeys(orderQuantity);
		Reporter.log("Set order Quantity", true);
	}

	public void setUnitPrice(String unitPrice, String windowTitle) {
		WebElement code = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@id", getXWindowId(windowTitle), "input", "@name",
						"unitPrice", true, true);
		code.clear();
		code.sendKeys(unitPrice);
		Reporter.log("Set unit Price", true);
	}

	/**
	 * 
	 * @param column to be used for selecting value in Lookup
	 * @param name  of order UOM
	 * @param windowTitle :enter title of window where Lookup is present
	 */
	public void setOrderUOM(String column, String name, String windowTitle) {

		clickLookup("@id", getXWindowId(windowTitle), "orderUOM",
				"Select a Unit Of Measure");
		setValueGridLookupWithFilters("@id",
				getXWindowId("Select a Unit Of Measure"), name, column);
		Reporter.log("Requester was selected", true);
	}

	/**
	 * Helper method to select currency
	 * */
	public void selectCurrency(String currency, String windowTitle) {

		String id = McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@id", getXWindowId(windowTitle), "input",
				"@name", "currency", true, true).getAttribute("id");

		DropDown.setExtJsComboValue(driver, id, currency);

		waitForExtJSAjaxComplete(25);

		Reporter.log("Set currency to" + currency, true);
	}

	/**
	 * Helper method to select Requistion Warehouse
	 * 
	 * @param column  to be used for selecting value in Lookup
	 * @param name of Warehouse
	 * @param windowTitle :enter title of window where Lookup is present
	 */
	public void setRequisitionWarehouse(String column, String name,
			String windowTitle) {

		clickLookup("@id", getXWindowId(windowTitle), "warehouse",
				"Select A Warehouse");
		setValueGridLookupWithFilters("@id",
				getXWindowId("Select A Warehouse"), name, column);
		Reporter.log("Warehouse was selected", true);
	}

	// ****************************************Description Tab*********************************
	/**
	 * Helper method to set Description
	 * 
	 * @param description   to enter
	 * @param windowTitle  of the Requisition
	 */
	public void setDescription(String description, String windowTitle) {

		WebElement code = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@id", getXWindowId(windowTitle), "textarea", "@name",
						"description", true, true);

		code.clear();
		code.sendKeys(description);
		Reporter.log("Set description", true);
	}

	/**
	 * Helper method to set Description
	 *  @param justification to enter
	 * @param windowTitle of the Requisition
	 */

	public void setJustification(String justification, String windowTitle) {

		WebElement code = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@id", getXWindowId(windowTitle), "textarea", "@name",
						"justification", true, true);

		code.clear();
		code.sendKeys(justification);
		Reporter.log("Set description", true);
	}

	// ***************************************Financials Tab**********************************

	/**
	 * Helper method to set First Cost Category
	 * 
	 * @param costCategory
	 *            to set
	 * @param windowTitle
	 *            of the Requisition
	 */
	public void setCostCategory(String costCategory, String windowTitle) {

		clickLookup("@id", getXWindowId(windowTitle), "costCategory",
				"Select a Cost Category");

		waitForExtJSAjaxComplete(10);

		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@id", getXWindowId("Select a Cost Category"), "span",
				"text()", "List", true, true).click();

		waitForExtJSAjaxComplete(10);

		setValueGridLookupWithFilters("@id",
				getXWindowId("Select a Cost Category"), costCategory,
				"Reference");

	}

	// ***************************************Tracking tab*****************************************

	/**
	 * Helper method to select purchasing Organization in Requistion
	 * 
	 * @param column
	 *            to be used for selecting value in Lookup
	 * @param name
	 *            of purchasingOrganization
	 * @param windowTitle
	 *            :enter title of window where Lookup is present
	 */
	public void setPurchasingOrganization(String column, String name,
			String windowTitle) {

		clickLookup("@id", getXWindowId(windowTitle), "purchasingOrganization",
				"Select A Purchasing Organization");
		setValueGridLookupWithFilters("@id",
				getXWindowId("Select A Purchasing Organization"), name, column);
		Reporter.log("purchasing Organization was selected", true);
	}

	/**
	 * Helper method to select purchasing Group in Requisition
	 * 
	 * @param column
	 *            to be used for selecting value in Lookup
	 * @param name
	 *            of purchasingGroup
	 * @param windowTitle
	 *            :enter title of window where Lookup is present
	 */
	public void setPurchasingGroup(String column, String name,
			String windowTitle) {

		clickLookup("@id", getXWindowId(windowTitle), "purchasingGroup",
				"Select A Purchasing Group");
		setValueGridLookupWithFilters("@id",
				getXWindowId("Select A Purchasing Group"), name, column);
		Reporter.log("purchasing group was selected", true);
	}

	/**
	 * Helper method to get Cell content
	 * 
	 * @param rowNum
	 *            Row num of Cell
	 * @param columnClass
	 *            : Class of table cell
	 * @return value of the cell
	 */

	private String getCellText(String rowNum, String columnClass,
			String windowTitle) {

		String xpath = "//div[@id='"
				+ getXWindowId(windowTitle)
				+ "']//div[contains(@class,'x-grid3-hd-lineNr') and text()='General']/ancestor::div[contains(@class,'x-panel-body-noheader')]//div[@class='x-grid3-body']//div["
				+ rowNum + "]//td[contains(@class,'" + columnClass + "')]//div";

		WebElement ele = driver.findElement(By.xpath(xpath));
		
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ele.getText();
	}
	
	/**
	 * Helper method to Get Line ID
	 */
	public String getRequisitionLineID(String windowTitle, String rowNum){
		return getCellText(rowNum , PR_LINE_LINE_ID_CLASS, windowTitle);
	}

	/**
	 * Helper method to open a record in Transaction Line
	 */
	public void openTransactionLine(String lineID) {
		waitForExtJSAjaxComplete(25);

		WebElement ele = Grid.checkRowInGriByTextValue(driver, lineID);

		new Actions(driver).doubleClick(ele).perform();

		waitForExtJSAjaxComplete(25);
	}
		
	/**
	 * Helper method to Set Status in PR Page
	 * @param status
	 */
	public void setStatus(String status, String windowTitle) {
		WebElement xPath = McsElement.getElementByXpath(driver, "//div[@id='"+getXWindowId(windowTitle)+"']//label[@class='x-form-item-label' and text()='Status Actions']/..//input");
		
		String id = xPath.getAttribute("id");
		
		DropDown.setComboValueDropDownSelector(driver, id, status);
		
		waitForExtJSAjaxComplete(25);
		
		Reporter.log("Set status to" +status, true);
	}	
	
	/**
	 * Helper method to get Status of the requisition
	 * @param windowTitle
	 * @return  Status of the requisition
	 */
	public String getStatus(String windowTitle){
	
		return McsElement.getElementByXpath(driver, "//div[@id='"+getXWindowId(windowTitle)+"']//input[@name='statusText']").getAttribute("value");
		
		
	}
	
	/**
	 * Helper Method to select Product Catalog Line
	 */
	public void selectProductCatalogLines(String attribute, String attributeValue, String rowTextName, String columnName) {
		setValueGridLookupWithFilters(attribute, attributeValue, rowTextName, columnName);
		Reporter.log("Product Catalog Line selected", true);
	}
	
	/**
	 * Helper method to add a Purchase requisition line
	 * @param windowTitle of Purchase requisition
	 * @param product to be selected
	 * @param columnName to be used to Select product
	 */
	public void addPRLine(String windowTitle, String product, String columnName){
		
		clickOnAddPRLineButton(windowTitle) ;
		
		waitForExtJSAjaxComplete(15);
		
		selectProductCatalogLines("@id", getXWindowId(PRODUCT_SERVICES_WINDOW_HEADER),product,columnName);
		
		waitForExtJSAjaxComplete(15);
		
		Reporter.log("Add Purchase Requisition Line of " +product+ " is added ", true);
				
	}
	
	
	/**
	 * Helper method to get number of Requisition lines present in 
	 * @param windowTitle
	 * @return number  of Requisition lines 
	 */
	public int getNumberOfRequisitionLines(String windowTitle){
		
		String xpath = "//div[@id='"+ getXWindowId(windowTitle)+"']//div[contains(@class,'x-grid3-hd-lineNr') and text()='General']/ancestor::div[contains(@class,'x-panel-body-noheader')]//div[@class='x-grid3-body']/div[contains(@class,'x-grid3-row')]";
		
		List<WebElement> rows = driver.findElements(By.xpath(xpath)) ;
		
		return rows.size();
		
	}
	
	/**
	 * Helper method to enter notes for a Purchase Requisition 
	 * @param windowTitle of Purchase Requisition 
	 * @param notes for a Purchase Requisition
	 */
	public void setNotes(String windowTitle, CharSequence notes){
		
		String xpath = "//div[@id='"+getXWindowId(windowTitle)+"']//span[@class='x-tab-strip-text' and text()='Notes']/ancestor::div[contains(@class,'x-tab-panel x-border-panel')]//textarea";
		
		McsElement.getElementByXpath(driver, xpath).sendKeys(notes);
	}
	
	/**
	 * Helper method to check expected product is displayed or not.
	 * 
	 * */

	public List<String> getProdRefValues(String reference) {
		
	return getValuesFromGridLookup("@id", getXWindowId("Select Products or Services"), reference);
		
	}
	
	/**
	 * Helper method to verify Related Document tab is disabled or not
	 * */
	
	public boolean isDisabledRelatedDocTab(String windowTitle) {
		WebElement  relatedTab= McsElement.getElementByXpath(driver,"//div[@id='"+ getXWindowId(windowTitle)+"']"+"//span[contains(@class,'x-tab-strip-text') and text()='Related Documents']/ancestor::li");

		return isFieldDisabled(relatedTab);
	}
	
	/**
	 * Helper method to verify Add button is disabled or not
	 * */
		
		public boolean isDisabledAddButton(String windowTitle) {
			WebElement  addButton= McsElement.getElementByXpath(driver,"//div[@id='"+ getXWindowId(windowTitle)+"']"+"//button[contains(@class,'x-btn-text') and text()='Add...']//ancestor::table");

			return isFieldDisabled(addButton);

	}
		
		/**
		 * Helper method to verify Delete button is disabled or not
		 * */
		
		
		public boolean isDisabledDeleteButton(String windowTitle) {
			WebElement  deleteButton= McsElement.getElementByXpath(driver,"//div[@id='"+ getXWindowId(windowTitle)+"']"+"//button[contains(@class,'x-btn-text') and text()='Delete']//ancestor::table");

			return isFieldDisabled(deleteButton);
	}
		
		/**
		 * Helper method to verify Status button is disabled or not
		 * */
		
		
		public boolean isDisabledStatus(String windowTitle){
			WebElement  statusActions= McsElement.getElementByXpath(driver,"//div[@id='"+ getXWindowId(windowTitle)+"']"+"//label[contains(@class,'x-form-item-label') and contains(text(),'Status Actions')]/..//input/..");

			return isFieldDisabled(statusActions);
		}
		
		
		/**
		 * Helper method to verify Cost Carrier button is disabled or not
		 * */
		
		
		public boolean isDisabledCostCarrier(String windowTitle){
				WebElement costCarrier= McsElement.getElementByXpath(driver,"//div[@id='"+ getXWindowId(windowTitle)+"']"+"//input[contains(@name, 'costCarrierType')]");

				return isFieldDisabled(costCarrier);
		}
		
		/**
		 * Helper method to check if the passed field is disabled or not
		 * */
		
		public boolean isFieldDisabled(WebElement element)
		{
			String classAttrVal =element.getAttribute("class");
			
			return (classAttrVal.contains("noedit") ||classAttrVal.contains("readonly") || classAttrVal.contains("disabled") )?true:false;
			
		}
		
		/**
		 * Method allows to filter in grid by text
		 * @param attribute - attribute (@class, @id) of the grid container
		 * @param attributeValue - corresponding value of the grid container attribute
		 * @param rowTextName - row text to be filtered by 
		 * @columnName - columnName of the row 
		 */
		public void filterPRGrid(String attribute, String attributeValue, String rowTextName, String columnName) {
			Timer timer = new Timer().start();
			
			
			String columnClass = McsElement
					.getElementByXpath(driver,"(//div[contains("+attribute+",'"+ attributeValue+"')]//div[contains(@class,'quickfilters')]"
							+ "//div[contains(@class,'x-grid3-hd') and contains(text(),'"+columnName+"')])").getAttribute("class");
			
			String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
			
			
			WebElement filterInput = McsElement.getElementByXpath(driver, "(//div[contains("+attribute+",'"+attributeValue+"')]//"
					+ "input[contains(@id,'filter-editor-"+columnNumber+"')])"); 
				
			filterInput.clear();
				
			filterInput.sendKeys(rowTextName);
				
			 McsElement.getElementByXpath(driver, "(//div[contains("+attribute+",'"+attributeValue+"')]//"
						+ "div[contains(@class,'x-grid3-body')])/div").click(); 
				
			waitForExtJSAjaxComplete(5);

			Reporter.log(rowTextName + " was filtered"+ " (" + timer.stop()
					+ "ms)", true);
		}
		
		/**
		 * Helper method to get ID attribute of current grid
		 * */
		public String getFilterGridID(){
			
			WebElement grid = McsElement.getElementByXpath(driver, "//div[@id='portalcontainer_tabs']//div[contains(@class,'x-form-item')]//div[contains(@class,'x-tab-panel-noborder') and not(contains(@class,'x-hide-display'))]//div[@class='x-grid3']");
			String id = grid.getAttribute("id"); 
			return id;
			
		}
		
		/**
		 * Helper method to get PR line Status
		 */
		public String getPRLineStatus(String attribute, String attributeValue, String rowTextName, String colName){
			Timer timer = new Timer().start();
			
			waitForExtJSAjaxComplete(25);
			
			filterPRGrid(attribute, attributeValue, rowTextName, colName);
			
			waitForExtJSAjaxComplete(25);
		
			int lineIDColumnNumber = getGridColumnNumber(attribute, attributeValue , "Status" );
		
			String ele = McsElement.getElementByXpath(driver,"(//"+"div"+"[contains("+attribute+",'" + attributeValue + "') and not (contains(@class, 'x-hide-display'))]//"
			+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+lineIDColumnNumber + "')"+	
			"])").getText();
				
			timer.stop();
			return ele;
		}
		
		/**
		 *  Helper method to get column number in Grid 
		 */
		public int getGridColumnNumber(String attribute, String attributeValue, String columnName){
			
			String columnClass = "";
			String columnNumber =  "";
			WebElement ele = driver.findElement(By.xpath("(//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'quickfilters')]//div[contains(@class,'x-grid3-hd') and text()='"+columnName+"'])"));
			try{
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
				columnClass = ele.getAttribute("class");
				columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);
			} catch(Exception e){
				e.printStackTrace();
			}
			return Integer.parseInt(columnNumber);
		}
		
		/**
		 * Helper method to clear grid filters
		 */
		public void clearFilters() {
			McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-tab-panel-noborder') and not (contains(@class, 'x-hide-display'))]//button[contains(@class,'icon-grid-filter-clear') and text()='Clear Filters']").click();
			Reporter.log("Clear Filters ", true);
		}
		
		/**
		 * Helper method to Get Order Quantity
		 */
		public String getOrderQuantityOfProductLn(String windowTitle, String rowNum){
			return getCellText(rowNum ,PR_LINE_ORDER_QUANTITY_CLASS, windowTitle);
		}
		
		/**
		 * Helper method to get text of the requisition
		 * @param windowTitle
		 * @return  reference of the product
		 */
		public String getProductRefFromGeneralTab(String windowTitle){
			
			return McsElement.getElementByXpath(driver, "//div[@id='"+getXWindowId(windowTitle)+"']//input[@name='productReference']").getAttribute("value");
						
		}
			
		/**
		 * Helper method to get text of the requisition
		 * @param windowTitle
		 * @return  code of the product
		 */
		public String getProductCodeFromGeneralTab(String windowTitle){
			
			return McsElement.getElementByXpath(driver, "//div[@id='"+getXWindowId(windowTitle)+"']//input[@name='productCode']").getAttribute("value");
				
		}
		
		/**
		 * Helper method to get Status of the requisition
		 * @param windowTitle
		 * @return  Order Quantity of the product
		 */
		public String getOrderQuantityFromGeneralTab(String windowTitle){
			
			return McsElement.getElementByXpath(driver, "//div[@id='"+getXWindowId(windowTitle)+"']//input[@name='orderQuantity']").getAttribute("value");
				
		}
		
		
		/**
		 * Helper method to return true/false based on Editablity of Order quantity 
		 * @param windowTitle of Requisition window
		 * @return true/false
		 */
		public boolean isOrderQuantityDisabled(String windowTitle) {
			WebElement code = McsElement
					.getElementByPartAttributeValueAndParentElement(driver, "div",
							"@id", getXWindowId(windowTitle), "input", "@name",
							"orderQuantity", true, true);
			
			
			return isFieldDisabled(code);
		}
		
		/**
		 * Helper method to verify PR line is deleted or not
		 * @param windowTitle
		 * @param lineNum
		 * @return true
		 */
		
		public boolean isPRLineGridEmpty(String windowTitle, String lineNum)
		{
			String xpath = "//div[@id='"+ getXWindowId(windowTitle)+"']//div[contains(@class,'x-grid3-hd-lineNr') and text()='General']/ancestor::div[contains(@class,'x-panel-body-noheader')]//div[@class='x-grid3-body']/div["+lineNum+"]"; 
			
			String prGridText = McsElement.getElementByXpath(driver ,xpath).getText();
			return prGridText.equalsIgnoreCase("No lines on this document");
			
		}
		
		/**
		 * Helper method to return true/false based on Editablity of unit price
		 * @param windowTitle of Requisition window
		 * @return true/false
		 */
		public boolean isUnitPriceDisabled(String windowTitle) {
			WebElement code = McsElement
					.getElementByPartAttributeValueAndParentElement(driver, "div",
							"@id", getXWindowId(windowTitle), "input", "@name",
							"unitPrice", true, true);
			
			
			return isFieldDisabled(code);
		}
		
		/**
		 * Helper method to add a file in Related documents tab
		 * @param file path to upload
		 * @param description for file
		 * @param remark for file
		 * @param type of doc
		 */
		public void setFile(String file, String description, String remark,
				String type) {
			McsElement.getElementByXpath(driver, ADD_FILE_XPATH).click();
			waitForExtJSAjaxComplete(25);
			FileUploader.uploadFileName(driver, "@class", "x-window", file);
			waitForExtJSAjaxComplete(25);

			McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).clear();
			McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).sendKeys(
					description);

			McsElement.getElementByXpath(driver, HYPERLINK_REMARK).clear();
			McsElement.getElementByXpath(driver, HYPERLINK_REMARK).sendKeys(remark);

			int xwindowNumber = 0;

			try {
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				xwindowNumber = driver.findElements(
						By.xpath("//div[contains(@class,'x-resizable-pinned')]"))
						.size();
			} finally {
				driver.manage()
						.timeouts()
						.implicitlyWait(configuration.getImplicitWait(),
								TimeUnit.SECONDS);
			}

			waitFocusAndClick("//div[@id='"+ getXWindowId(ADD_DOC_WINDOW_HEADER)+"']//div[contains(@class,'x-window')]//label[contains(text(),'Type')]/..//button");

			waitForExtJSAjaxComplete(10);
			waitForExtJSAjaxComplete(10);

			try {
				driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
				if (driver.findElements(
						By.xpath("//div[contains(@class,'x-resizable-pinned')]"))
						.size() == xwindowNumber) {
					waitAndClick("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");
				}
			}

			finally {
				driver.manage()
						.timeouts()
						.implicitlyWait(configuration.getImplicitWait(),
								TimeUnit.SECONDS);
			}

			waitForExtJSAjaxComplete(25);

			setValueGridLookupWithFilters(type, "Reference");

			waitForExtJSAjaxComplete(25);

			McsElement.getElementByXpath(driver, ADD_URL_CREATE_XPATH).click();
			waitForExtJSAjaxComplete(35);
			Reporter.log("file was uploaded <br>", true);
		}

		/**
		 * Helper method to add URL
		 * @param url to add
		 * @param description for url
		 * @param remark for url
		 * @param type of url
		 */
		public void setUrl(String url, String description, String remark,
				String type) {
			McsElement.getElementByXpath(driver, ADD_URL_XPATH).click();
			waitForExtJSAjaxComplete(25);
			McsElement.getElementByXpath(driver, HYPERLINK_XPATH).clear();
			McsElement.getElementByXpath(driver, HYPERLINK_XPATH).sendKeys(url);

			McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).clear();
			McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).sendKeys(
					description);

			McsElement.getElementByXpath(driver, HYPERLINK_REMARK).clear();
			McsElement.getElementByXpath(driver, HYPERLINK_REMARK).sendKeys(remark);

			int xwindowNumber = 0;

			try {
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				xwindowNumber = driver.findElements(
						By.xpath("//div[contains(@class,'x-resizable-pinned')]"))
						.size();
			} finally {
				driver.manage()
						.timeouts()
						.implicitlyWait(configuration.getImplicitWait(),
								TimeUnit.SECONDS);
			}

			waitFocusAndClick("//div[@id='"+ getXWindowId(ADD_URL_WINDOW_HEADER)+"']//div[contains(@class,'x-window')]//label[contains(text(),'Type')]/..//button");

			waitForExtJSAjaxComplete(10);
			waitForExtJSAjaxComplete(10);

			try {
				driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
				if (driver.findElements(
						By.xpath("//div[contains(@class,'x-resizable-pinned')]"))
						.size() == xwindowNumber) {
					waitAndClick("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");
				}
			}

			finally {
				driver.manage()
						.timeouts()
						.implicitlyWait(configuration.getImplicitWait(),
								TimeUnit.SECONDS);
			}

			waitForExtJSAjaxComplete(25);

			setValueGridLookupWithFilters(type, "Reference");

			waitForExtJSAjaxComplete(25);

			McsElement.getElementByXpath(driver, ADD_URL_CREATE_XPATH).click();
			waitForExtJSAjaxComplete(25);
			Reporter.log("url was set <br>", true);
		}

		/**
		 * Helper method to delete the URL
		 * @param description of the URL
		 * @param windowTitle of the Requistion 
		 */
		public void deleteUrl(String windowTitle,String description) {
			WebElement element = driver.findElement(By
					.xpath("//div[@id='"+ getXWindowId(windowTitle)+"']//*[@class='x-grid3']//div[text()='" + description
							+ "']"));
			waitFocusAndClick(element);
			waitForExtJSAjaxComplete(25);
			McsElement.getElementByXpath(driver, DELETE_URL_XPATH).click();
			waitForExtJSAjaxComplete(25);
			clickOnDialogButton("Yes");
			waitForExtJSAjaxComplete(25);
			Reporter.log("url or file was deleted <br>", true);
		}
		
		/**
		 * Helper method to get DocumentType
		 */
		public String getDocumentType(String windowTitle) {
			return McsElement.getElementByXpath(driver, "//div[contains(@id, '"+getXWindowId(windowTitle)+"')]//input[@name='documentType']/following-sibling::input[@type='text']").getAttribute("value");
		}
		
		/**
		 * Helper method to Get Created Date
		 */
		public String getCreationDate(String windowTitle, String rowNum){
			String createDate = getCellText(rowNum , PR_LINE_CREATED_ON_CLASS, windowTitle);
			createDate = createDate.substring(0, 10);
			Reporter.log("Creation Date is "+createDate, true);
			return createDate;
		}

}

		

