package test.generalweb.purchaseorders;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import test.generalweb.purchaserequisitions.PurchaseRequisitionsPageObject;
import test.generalweb.transactions.TransactionsPageObject;
import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement; 

public class PurchaseOrdersPageObject extends AbstractMcsTestSuite {

	protected final String PURCHASE_REQUISITIONS_LINES_WINDOW_HEADER = "Select Purchase Requisition Lines";
	
	protected final String PRODUCT_CATALOG_LINES_WINDOW_HEADER = "Select A Purchasing Group";
	
	protected final String PURCHASE_ORDERS_WINDOW_CLASS = "x-window financials";
	
	protected final String ADD_REQUISIION_WINDOW_HEADER = "New Purchase Requisition";
	
	protected final String EDIT_REQUISIION_WINDOW_HEADER = "Purchase Requisition";
	
	protected final String ADD_PURCHASE_ORDER_WINDOW_HEADER = "New Purchase Order";
	
	protected final String EDIT_PURCHASE_ORDER_WINDOW_HEADER = "Purchase Order";
	
	protected final String PO_LINE_ORDER_AMOUNT_CLASS= "x-grid3-td-orderAmount";
	
	protected final String PO_LINE_CURRENCY_CLASS = "x-grid3-td-currency";
	
	protected final String PO_LINE_ORDER_QTY_CLASS = "x-grid3-td-orderQuantity";
	
	protected final String PO_LINE_ORDER_UNIT_PRICE_CLASS = "x-grid3-td-unitPrice ";
	
	protected final String PO_LINE_LINEID_CLASS = "x-grid3-td-id";
	
	protected final String PO_LINE_ORDER_UOM_CLASS = "x-grid3-td-orderUom ";
	
	protected final String PO_LINE_EXT_PRODUCT_CLASS = "x-grid3-td-externalProductID ";
	
	protected final String PO_LINE_PRODUCT_REFERENCE_CLASS = "x-grid3-td-productReference ";
	
	protected final String PO_LINE_PRODUCT_CODE_CLASS = "x-grid3-td-productCode";
	
	protected final String PO_LINE_LINE_NR_CLASS = "x-grid3-td-lineNr";
	
	protected final String PO_REQUISITIONS_TAB_ID_CLASS = "x-grid3-td-prId";
	
	protected final String PO_REQUISITIONS_TAB_ORDER_QTY_CLASS = "x-grid3-td-prOrderQuantity";
	
	protected final String PRODUCT_SERVICES_WINDOW_HEADER = "Select Products or Services";
	
	protected final String GOODS_RECEIPT_WINDOW_HEADER = "Goods Receipt";
	
	protected final String GR_TRANSACTION_LINE_QUANTITY_CLASS= "x-grid3-td-quantityordered";
	
	/**
	 * Helper Method to click add button
	 */
	public void clickAddButton() {
	
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class,'stock')]//div[contains(@class,'x-form-item')]/../div[contains (@class,'x-tab-panel') and not(contains(@class,'x-hide-display'))]//button[contains(@class,'icon-ov-add')]"));
		try {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", ele);
        } catch (Exception e) {
        	e.printStackTrace();
        }
	
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Click Add button", true);
	}
	
	/**
	 * Helper method to Set Reference
	 */
	public void setReference(String reference, String windowTitle) {
		WebElement ref = McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@id", getXWindowId(windowTitle), "input", "@name", "reference", true, true);
		ref.click();
		ref.clear();
		ref.sendKeys(reference);
		Reporter.log("Set Reference", true);
	}
	
	/**
	 * Helper method to Set Expected Order date
	 */
	public void setExpectedOrderDate(String expectedDate, String windowTitle) {
		WebElement rerferenceElement= McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXWindowId(windowTitle), "input", "@name", "expectedOrderDate", true, true);
		rerferenceElement.clear();
		rerferenceElement.sendKeys(expectedDate);
		Reporter.log("Set Expected Order Date", true);
	}
	
	/**
	 * Helper method to select document Type
	 */
	public void selectDocumentType(String rowTextName, String colName, String windowTitle) {
		clickLookup("@id", getXWindowId(windowTitle), "documentType",
				"Select A Purchasing Document Type");
		setValueGridLookupWithFilters("@id", getXWindowId("Select A Purchasing Document Type"),
				rowTextName, colName);
		Reporter.log("Document Type selected", true);
	}
	
	/**
	 * Helper method to Select Supplier
	 */
	public void selectSupplier(String rowTextName, String colName, String windowTitle) {
		clickLookup("@id", getXWindowId(windowTitle), "supplier",
				"Select a Supplier");
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Supplier"),
				rowTextName, colName);
		Reporter.log("Supplier selected", true);
	}
	
	/**
	 * Helper method to Select Purchasing Organization
	 */
	public void selectPurchasingOrganization(String rowTextName, String colName, String windowTitle) {
		clickLookup("@id", getXWindowId(windowTitle), "purchasingOrganization",
				"Select A Purchasing Organization");
		setValueGridLookupWithFilters("@id", getXWindowId("Select A Purchasing Organization"),
				rowTextName, colName);
		Reporter.log("Purchasing Organization selected", true);
	}
	
	/**
	 * Helper method to Select Purchasing Group
	 */
	public void selectPurchasingGroup(String rowTextName, String colName, String windowTitle) {
		clickLookup("@id", getXWindowId(windowTitle), "purchasingGroup",
				"Select A Purchasing Group");
		setValueGridLookupWithFilters("@id", getXWindowId("Select A Purchasing Group"),
				rowTextName, colName);
		Reporter.log("Purchasing Group selected", true);
	}
	
	/**
	 * Helper method to Select Delivery Warehouse
	 */
	public void selectDeliveryWarehouse(String rowTextName, String colName, String windowTitle) {
		waitForExtJSAjaxComplete(5);
		clickLookup("@id", getXWindowId(windowTitle), "deliveryWarehouse",
				"Select A Warehouse");
		setValueGridLookupWithFilters("@id", getXWindowId("Select A Warehouse"),
				rowTextName, colName);
		Reporter.log("Delivery Warehouse selected", true);
	}
	
	/**
	 * Helper method to Set Expected Delivery date
	 */
	public void setExpectedDeliveryDate(String expectedDeliveryDate, String windowTitle) {
		WebElement rerferenceElement= McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXWindowId(windowTitle),
				"input", "@name", "expectedDeliveryDate", true, true);
		rerferenceElement.clear();
		rerferenceElement.sendKeys(expectedDeliveryDate);
		Reporter.log("Set Expected Delivery Date", true);
	}
	
	/**
	 * Helper method to set Order Terms
	 */
	public void selectOrderTerms(String rowTextName, String colName, String windowTitle) {
		clickLookup("@id", getXWindowId(windowTitle), "orderTerms",
				"Select a Purchase Order Term");
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Purchase Order Term"),
				rowTextName, colName);
		Reporter.log("Order Terms selected", true);
	}
	
	/**
	 * Helper method to set Ship to Party
	 */
	public void selectShipToParty(String rowTextName, String colName, String windowTitle) {
		clickLookup("@id", getXWindowId(windowTitle), "shipToParty",
				"Select a Company");
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Company"),
				rowTextName, colName);
		Reporter.log("Ship To Party selected", true);
	}
	
	/**
	 * Helper method to set Bill to Party
	 */
	public void selectBillToParty(String rowTextName, String colName, String windowTitle) {
		clickLookup("@id", getXWindowId(windowTitle), "billToParty",
				"Select a Company");
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Company"),
				rowTextName, colName);
		Reporter.log("Bill To Party selected", true);
	}
	
	/**
	 * Helper method to Select Contact Person
	 */
	public void selectContactPerson(String contactPerson, String colName, String windowTitle) {
		clickLookup("@id", getXWindowId(windowTitle), "contactPerson",
				"Select a Person");
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Person"),
				contactPerson, colName);
		Reporter.log("Contact Person selected", true);
	}
	
	/**
	 * Helper Method to click add button in PO form
	 */
	public void clickAddPOLineButton() {
		McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-window financials')]//div[contains(@class,'x-box-inner') and not(contains(@class,'x-hide-display'))]//button[contains(text(),'Add')]").click();
		Reporter.log("Click Add button in New PO", true);
	}
	
	/**
	 * Helper Method to select Purchase Requisition Line
	 */
	public void selectPurchaseRequisitionLines(String attribute, String attributeValue, String rowTextName, String columnName, String realID) {
		setValueGridLookupWithFiltersCustomized(attribute, attributeValue, rowTextName, columnName, realID);
		Reporter.log("Purchase Requisition Line selected", true);
	}
	
	public void setValueGridLookupWithFiltersCustomized(String attribute, String attributeValue, String rowTextName, String columnName, String realID) {
		Timer timer = new Timer().start();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		filterGrid(attribute, attributeValue, rowTextName, columnName);	
		
		waitForExtJSAjaxComplete(25);
		
		//purch_req_line
		String columnClass = driver.findElement(By.xpath("//div[contains("+attribute+",'"+attributeValue+"')]//div[@realid='"+realID+"']//div[contains(@class,'x-grid3-hd')and contains(text(),'"+columnName+"')][last()]")).getAttribute("class");
		
		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
		
		waitForExtJSAjaxComplete(25);
		
		String xpath ="(//"+"div"+"[contains("+"@class"+",'" + "x-window x-window-noborder x-resizable-pinned" + "')]//"
				+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')])";

		List<WebElement> searchResults = driver.findElements(By.xpath(xpath));

		if(searchResults.size()==0){

			Reporter.log("No search results found for filtering criteria ", true);
			throw new NoSuchElementException("No search results found for filtering criteria");

		}
		
		for(WebElement ele: searchResults){

			if(ele.getText().equals(rowTextName)){

				ele.click();
				break;
			}
		}

		waitForExtJSAjaxComplete(25);
		clickOkXwindow();
		Reporter.log(rowTextName + " was selected"+ " (" + timer.stop()	+ "ms)", true);
	}
	
	/**
	 * Helper Method to select Product Catalog Line
	 */
	public void selectProductCatalogLines(String attribute, String attributeValue, String rowTextName, String columnName, String realID) {
		setValueGridLookupWithFiltersCustomized(attribute, attributeValue, rowTextName, columnName, realID);
		Reporter.log("Product Catalog Line selected", true);
	}
	
	/**
	 * Helper method to Click on General Tab
	 */
	private void clickOnTab(String tabText) {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", PURCHASE_ORDERS_WINDOW_CLASS, "span", "@class", "x-tab-strip-text", "text()", tabText, true, true).click();
		waitForExtJSAjaxComplete(5);
	}
	
	/**
	 * Helper method to click on General Tab
	 */
	public void clickGeneralTab() {
		clickOnTab("General"); 
	} 
	
	/**
	 * Helper Method to set Unit Price in General Tab
	 */
	public void setUnitPrice(String price) {
		WebElement unitPrice = McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", PURCHASE_ORDERS_WINDOW_CLASS, "input", "@name", "unitPrice", true, true);
		unitPrice.click();
		unitPrice.clear();
		unitPrice.sendKeys(price);
		Reporter.log("Set Unit Price", true);
	}
	
	/**
	 * Helper Method to set Order Qty in General Tab
	 */
	public void setOrderQty(String quantity) {
		WebElement orderQty = McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", PURCHASE_ORDERS_WINDOW_CLASS, "input", "@name", "orderQuantity", true, true);
		orderQty.click();
		orderQty.clear();
		orderQty.sendKeys(quantity);
		Reporter.log("Set Order quantity", true);
	}
	
	/**
	 * Helper Method to click Save button in PO Form
	 */
	public void save(String windowTitle) {
		McsElement.getElementByXpath(driver, "//div[@id='"+getXWindowId(windowTitle) +"']//button[contains(text(),'Save')]").click();
		Reporter.log("Click Save button", true);
	}
	
	/**
	 * Helper Method to click Close button in PO Form
	 */
	public void close(String windowTitle) {
		McsElement.getElementByXpath(driver, "//div[@id='"+getXWindowId(windowTitle) +"']//button[contains(text(),'Close')]").click();
		Reporter.log("Click Close button", true);
	}
	
	/**
	 * Helper method to get Total Amount in PO Form
	 */
	public String getTotalAmount(String windowTitle) {
		String totalAmt = McsElement.getElementByXpath(driver,"//div[@id='"+getXWindowId(windowTitle) +"']//label[contains(@class, 'x-box-item')]").getText();
		String [] splitVal = totalAmt.split(" ");
		System.err.println(splitVal[2]);

		String subStringTotAmt = splitVal[2].toString().trim();
		return subStringTotAmt;
	}
	
	/**
	 * Helper method to get Currency in PO Form
	 */
	public String getCurrency(String windowTitle) {
		String totalAmt = McsElement.getElementByXpath(driver,"//div[@id='"+getXWindowId(windowTitle) +"']//label[contains(@class, 'x-box-item')]").getText();
		String [] splitVal = totalAmt.split(" ");
		System.err.println(splitVal[3]);
		
		String subStringCcy = splitVal[3].toString().trim();
		return subStringCcy;
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
			String windowTitle, String tabText) {

		String xpath = "//div[@id='"
				+ getXWindowId(windowTitle)
				+ "']//span[contains(@class,'x-tab-strip-text') and text()= '"+ tabText +"']/ancestor::div[contains(@class,'x-panel-body-noheader')]//div[@class='x-grid3-body']//div["
				+ rowNum + "]//td[contains(@class,'" + columnClass + "')]//div";
		
		return McsElement.getElementByXpath(driver, xpath).getText();
	}
	
	/**
	 * Helper method to Get Order Amount
	 */
	public String getOrderAmount(String windowTitle, String rowNum, String tabText){
		return getCellText(rowNum, PO_LINE_ORDER_AMOUNT_CLASS, windowTitle, tabText);
	}

	/**
	 * Helper method to Get Currency
	 */
	public String getCurrency(String windowTitle, String rowNum, String tabText){
		return getCellText(rowNum, PO_LINE_CURRENCY_CLASS, windowTitle, tabText);
	}
	
	/**
	 * Helper method to Get Order Qty
	 */
	public int getOrderQty(String windowTitle, String rowNum, String tabText){
		String quantityInRequisitionsTab = getCellText(rowNum, PO_LINE_ORDER_QTY_CLASS, windowTitle, tabText);
		int intquantityInRequisitionsTab = Integer.parseInt(quantityInRequisitionsTab);
		return intquantityInRequisitionsTab;
	}
	
	/**
	 * Helper method to Get Order Qty from Requisitions Tab
	 */
	public int getOrderQtyRequisitionsTab(String windowTitle, String rowNum, String tabText){
		String quantityInRequisitionsTab = getCellText(rowNum, PO_REQUISITIONS_TAB_ORDER_QTY_CLASS, windowTitle, tabText);
		int intquantityInRequisitionsTab = Integer.parseInt(quantityInRequisitionsTab);
		return intquantityInRequisitionsTab;
	}
	
	/**
	 * Helper method to Get Unit Price
	 */
	public int getUnitPrice(String windowTitle, String rowNum, String tabText){
		String unitPriceInPOLine = getCellText(rowNum, PO_LINE_ORDER_UNIT_PRICE_CLASS, windowTitle, tabText);
		int intUnitPriceInPOLine = Integer.parseInt(unitPriceInPOLine);
		return intUnitPriceInPOLine;
	}
	
	/**
	 * Helper method to click on Requisitions Tab
	 */
	public void clickRequisitionsTab() {
		clickOnTab("Requisitions"); 
	} 
	
	/**
	 * Helper method to get Line ID from Requisitions Tab
	 */
	public String getIDFromRequisitionsTab(String windowTitle, String rowNum) {
		return getCellText(rowNum, PO_REQUISITIONS_TAB_ID_CLASS, windowTitle, "Requisitions");
	}
	
	/**
	 * Helper method to get Line ID from PO Lines 
	 */
	public String getLineIDFromPOLines(String windowTitle, String rowNum, String tabText){
		return getCellText(rowNum, PO_LINE_LINEID_CLASS, windowTitle, tabText);
	}
	
	/**
	 * Helper method to get Status of PO
	 */
	public String getStatus(String windowTitle) {
		WebElement  statusActions= McsElement.getElementByXpath(driver,"//div[contains(@id,'"+ getXWindowId(windowTitle)+"')]"+"//label[contains(@class,'x-form-item-label') and contains(text(),'Status')]/..//input[@name='status']");

		return statusActions.getAttribute("value");
	}

	/**
	 * Helper method to get Expected Order date
	 */
	public String getExpectedOrderDate(String windowTitle) {
		String expectedOrderDate = McsElement.getElementByXpath(driver, "//div[contains(@id,'"+ getXWindowId(windowTitle)+"')]//input[@name='expectedOrderDate']").getAttribute("value");
		return expectedOrderDate;
	}
	
	/**
	 * Helper Method to set value in Status Dropdown
	 * @param windowTitle
	 * @param comboValue
	 * @param attribute
	 * @param attributeValue
	 */
	public void setStatusValue(String windowTitle, String comboValue, String attribute, String attributeValue) {	
	
		WebElement xPath = driver.findElement(By.xpath("//div[@id='"+getXWindowId(windowTitle)+"']//label[@class='x-form-item-label' and text()='Status Actions']/..//input"));
		
		String id = xPath.getAttribute("id");
		
		waitForExtJSAjaxComplete(10);
		
		DropDown.setExtJsComboValue(driver, id, comboValue);
		
		/*((JavascriptExecutor) driver)
		.executeScript("document.getElementById('"+id+"').value ='"+comboValue+"';");*/
		
		waitForExtJSAjaxComplete(30);
		
		Reporter.log("set Status Value to " + comboValue, true);
	}
	
	/**
	 * Helper method to open a record in Transaction Line
	 */
	public void openTransactionLine(String attribute, String attributeValue, String rowTextName, String columnName){
		waitForExtJSAjaxComplete(25);
	
		filterGrid(attribute, attributeValue, rowTextName, columnName);
		
		waitForExtJSAjaxComplete(25);
		
		WebElement ele = Grid.checkRowInGriByTextValue(driver, rowTextName); 
		
		new Actions(driver).doubleClick(ele).perform();
		
		waitForExtJSAjaxComplete(25);
	}
	
	/**
	 * Helper method to get Values from Status Drop down
	 */
	public List<String> getStatusDropDownValues(String attribute, String attributeValue){
		WebElement statusDropDown = driver.findElement(By.xpath("//div[contains("+attribute+",'" + attributeValue + "')]//label[@class='x-form-item-label' and text()='Status Actions']/..//input"));
		
		statusDropDown.click();
		
		List<WebElement> values = driver.findElements(By.xpath("//div[contains(@class, 'x-combo-list') and (contains(@style, 'visibility: visible'))]//div[contains(@class,'x-combo-list-item')]"));
					
		ArrayList<String> lsValues = new ArrayList<String>(); 
				
		for(int i=0; i<values.size(); i++){
					
		String valueText = values.get(i).getText().trim();
					
		lsValues.add(valueText);
		}
		
		return lsValues;
	}
	
	/**
	 * Helper method to get ID attribute of current grid
	 * */
	public String getFilterGridID(){
		
		WebElement grid = McsElement.getElementByXpath(driver, "//div[@id='portalcontainer_tabs']//div[contains(@class,'stock')]//div[contains(@class,'x-form-item')]/../div[contains (@class,'x-tab-panel') and not(contains(@class,'x-hide-display'))]//div[@class='x-grid3']");
		
		String id = grid.getAttribute("id"); 
		return id;
		
	}
	
	/**
	 * Helper method to click on Document Tab
	 */
	public void clickDocumentTab() {
		clickOnTab("Document"); 
	} 
	
	/**
	 * Helper method to click on Notes Tab
	 */
	public void clickNotesTab() {
		clickOnTab("Notes"); 
	}
	
	/**
	 * Helper method to click on Description Tab
	 */
	public void clickDescriptionTab() {
		clickOnTab("Description"); 
	}
	
	/**
	 * Helper method to click on Goods Receipt Tab
	 */
	public void clickGoodsReceiptTab() {
		clickOnTab("Goods Receipt"); 
	}
	
	/**
	 * Helper Method to click delete button in PO form
	 */
	public void clickDeletePOLineButton() {
		McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-window financials')]//div[contains(@class,'x-box-inner') and not(contains(@class,'x-hide-display'))]//button[contains(text(),'Delete')]").click();
		Reporter.log("Click Delete button in New PO", true);
	}
	
	/**
	 * Helper method to check if the passed field is disabled or not
	 */
	
	public boolean isFieldDisabled(WebElement element)
	{
		String classAttrVal =element.getAttribute("class");
		
		return (classAttrVal.contains("noedit") ||classAttrVal.contains("readonly") || classAttrVal.contains("disabled") )?true:false;
	}
	
	/**
	 * Helper method to verify Related Document tab is disabled 
	 */
	
	public boolean isDisabledRelatedDocTab(String windowTitle) {
		WebElement  relatedTab= McsElement.getElementByXpath(driver,"//div[@id='"+ getXWindowId(windowTitle)+"']"+"//span[contains(@class,'x-tab-strip-text') and text()='Attachments']/ancestor::li");
		return isFieldDisabled(relatedTab);
	}
	
	/**
	 * Helper method to verify Add button is disabled or not
	 * */
		
	public boolean isDisabledAddButton(String windowTitle) {
		WebElement  addButton= McsElement.getElementByXpath(driver,"//div[contains(@class, 'x-window financials')]//div[contains(@class,'x-box-inner') and not(contains(@class,'x-hide-display'))]//button[contains(text(),'Add')]//ancestor::table");
		return isFieldDisabled(addButton);
	}
		
	/**
	 * Helper method to verify Delete button is disabled or not
	 */
	public boolean isDisabledDeleteButton(String windowTitle) {
	    WebElement  deleteButton= McsElement.getElementByXpath(driver,"//div[@id='"+ getXWindowId(windowTitle)+"']"+"//button[contains(@class,'x-btn-text') and text()='Delete']//ancestor::table");
		return isFieldDisabled(deleteButton);
	}
	
	/**
	 * Helper method to verify History tab is disabled
	 */
	public boolean isDiabledHistoryTab(String windowTitle) {
		WebElement  historyTab= McsElement.getElementByXpath(driver,"//div[@id='"+ getXWindowId(windowTitle)+"']"+"//span[contains(@class,'x-tab-strip-text') and text()='History']/ancestor::li");

		return isFieldDisabled(historyTab);
	}
	
	/**
	 * Helper method to verify Requisitions tab is disabled
	 */
	public boolean isDiabledRequisitionsTab(String windowTitle) {
		WebElement  requisitionsTab= McsElement.getElementByXpath(driver,"//div[@id='"+ getXWindowId(windowTitle)+"']"+"//span[contains(@class,'x-tab-strip-text') and text()='Requisitions']/ancestor::li");

		return isFieldDisabled(requisitionsTab);
	}
	
	/**
	 * Helper method to verify Stock tab is disabled
	 */
	public boolean isDisabledStockTab(String windowTitle) {
		WebElement  stockTab = McsElement.getElementByXpath(driver,"//div[@id='"+ getXWindowId(windowTitle)+"']"+"//span[contains(@class,'x-tab-strip-text') and text()='Stock']/ancestor::li");

		return isFieldDisabled(stockTab);
	}
	
	/**
	 * Helper method to verify goods Receipt is disabled
	 */
	public boolean isDisabledGoodsReceiptTab(String windowTitle) {
		WebElement  goodsReceiptTab = McsElement.getElementByXpath(driver,"//div[@id='"+ getXWindowId(windowTitle)+"']"+"//span[contains(@class,'x-tab-strip-text') and text()='Goods Receipt']/ancestor::li");

		return isFieldDisabled(goodsReceiptTab);
	}
	
	/**
	 * Helper method to get Cell content Class
	 * 
	 * @param rowNum
	 *            Row num of Cell
	 * @param columnClass
	 *            : Class of table cell
	 * @return value of the cell
	 */

	private String getCellTextAttribute(String rowNum, String columnClass,
			String windowTitle, String attribute) {

		String xpath = "//div[@id='"
				+ getXWindowId(windowTitle)
				+ "']//span[contains(@class,'x-tab-strip-text')]/ancestor::div[contains(@class,'x-panel-body-noheader')]//div[@class='x-grid3-body']//div["
				+ rowNum + "]//td[contains(@class,'" + columnClass + "')]//div";
		
		return McsElement.getElementByXpath(driver, xpath).getAttribute(attribute);
	}
		
	/////////////////////Verify UnEditable Fields of PO Line ////////////

	public boolean isOrderAmountUnEditable() {
		String orderAmountPOLin = getCellTextAttribute("1", PO_LINE_ORDER_AMOUNT_CLASS, EDIT_PURCHASE_ORDER_WINDOW_HEADER, "class");

		return (orderAmountPOLin.contains("readonly") || orderAmountPOLin.contains("x-unselectable")) ? true : false;
	}
	
	public boolean isCurrencyUnEditable() {
		String currencyPOLine = getCellTextAttribute("1", PO_LINE_CURRENCY_CLASS, EDIT_PURCHASE_ORDER_WINDOW_HEADER, "class");
		
		return (currencyPOLine.contains("readonly") || currencyPOLine.contains("x-unselectable")) ? true : false;
	}
	
	public boolean isUnitPriceUnEditable() {
		String unitPricePOLine = getCellTextAttribute("1", PO_LINE_ORDER_UNIT_PRICE_CLASS, EDIT_PURCHASE_ORDER_WINDOW_HEADER, "class");
		
		return (unitPricePOLine.contains("readonly") || unitPricePOLine.contains("x-unselectable")) ? true : false;
	}
	
	public boolean isOrderUOMUnEditable() {
		String orderUOMPOLine = getCellTextAttribute("1", PO_LINE_ORDER_UOM_CLASS, EDIT_PURCHASE_ORDER_WINDOW_HEADER, "class");
		
		return (orderUOMPOLine.contains("readonly") || orderUOMPOLine.contains("x-unselectable")) ? true : false;
	}
	
	public boolean isOrderQuantityUnEditable() {
		String orderQuantityPOLine = getCellTextAttribute("1", PO_LINE_ORDER_QTY_CLASS, EDIT_PURCHASE_ORDER_WINDOW_HEADER, "class");
		
		return (orderQuantityPOLine.contains("readonly") || orderQuantityPOLine.contains("x-unselectable")) ? true : false;
	}
	
	public boolean isExternalProductUnEditable() {
		String externalProductPOLine = getCellTextAttribute("1", PO_LINE_EXT_PRODUCT_CLASS, EDIT_PURCHASE_ORDER_WINDOW_HEADER, "class");
		
		return (externalProductPOLine.contains("readonly") || externalProductPOLine.contains("x-unselectable")) ? true : false;
	}
	
	public boolean isProductCodeUnEditable() {
		String productCodePOLine = getCellTextAttribute("1", PO_LINE_PRODUCT_CODE_CLASS, EDIT_PURCHASE_ORDER_WINDOW_HEADER, "class");
		
		return (productCodePOLine.contains("readonly") || productCodePOLine.contains("x-unselectable")) ? true : false;
	}
	
	public boolean isProductReferenceUnEditable() {
		String productRefPOLine = getCellTextAttribute("1", PO_LINE_PRODUCT_REFERENCE_CLASS, EDIT_PURCHASE_ORDER_WINDOW_HEADER, "class");
		
		return (productRefPOLine.contains("readonly") || productRefPOLine.contains("x-unselectable")) ? true : false;
	}
	
	public boolean isLineIDUnEditable() {
		String lineIDPOLine = getCellTextAttribute("1", PO_LINE_LINEID_CLASS, EDIT_PURCHASE_ORDER_WINDOW_HEADER, "class");
		
		return (lineIDPOLine.contains("readonly") || lineIDPOLine.contains("x-unselectable")) ? true : false;
	}
	
	public boolean isLineNRUnEditable() {
		String lineNRPOLine = getCellTextAttribute("1", PO_LINE_LINE_NR_CLASS, EDIT_PURCHASE_ORDER_WINDOW_HEADER, "class");
		
		return (lineNRPOLine.contains("readonly") || lineNRPOLine.contains("x-unselectable")) ? true : false;
	}

	// ///////////////////////////////// Goods Receipt Tab // ////////////////////////////////////////////////

	/**
	 * Helper method to get Goods Receipt ID
	 */
	public String getGoodsReceiptIDFromGoodsReceiptTab(String windowTitle) {
		String goodsReceiptID = McsElement.getElementByXpath(
				driver,
				"//div[@id='" + getXWindowId(windowTitle)
						+ "']//input[contains(@name, 'goodsReceiptID')]")
				.getAttribute("value");
		return goodsReceiptID;
	}

	/**
	 * Helper method to get Goods Receipt Remarks
	 */
	public String getRemarksFromGoodsReceiptTab(String windowTitle) {
		String goodsReceiptID = McsElement.getElementByXpath(driver,"//div[@id='" + getXWindowId(windowTitle)+ "']//textarea[contains(@name, 'goodsReceiptRemarks')]").getAttribute("value");
		return goodsReceiptID;
	}

	/**
	 * Helper Method to click show goods receipt button in PO form
	 */
	public void clickShowGoodsReceiptButton() {
		McsElement
				.getElementByXpath(
						driver,
						"//div[contains(@class, 'x-window financials')]//button[contains(@class,'icon-goods-receipt')]")
				.click();
		Reporter.log("Click show Goods Receipt button in PO Goods Receipt Button", true);
	}

	/**
	 * Helper method to get the 10 digit ID from Goods Receipt Window
	 * 
	 * @param windowTitle
	 *            of the window
	 * @return 10-digit id
	 */
	public String getTenDigitIDFromRemarksFieldGRWindow(String windowTitle) {

		TransactionsPageObject  transactionsPageObject = new TransactionsPageObject();
		
		String reqID = transactionsPageObject.getRemarksOfGoodsReceiptWindow(windowTitle);

		reqID = reqID.substring(reqID.lastIndexOf(" ") + 1, reqID.length());

		Reporter.log("Goods Receipt ID displayed in Remarks Field of GR Window is" + reqID, true);

		return reqID;
	}
	
	/**
	 * Helper method to open a record in Transaction Line
	 */
	public void openTransactionLineWOMcsElement(String attribute, String attributeValue, String rowTextName, String columnName){
		waitForExtJSAjaxComplete(25);
	
		filterGridWithoutUsingMcsElement(attribute, attributeValue, rowTextName, columnName);
		
		waitForExtJSAjaxComplete(25);
		
		WebElement ele = Grid.checkRowInGriByTextValue(driver, rowTextName); 
		
		new Actions(driver).doubleClick(ele).perform();
		
		waitForExtJSAjaxComplete(25);
	}
	
	public static WebElement checkRowInGriByTextValueAndClick(WebDriver webDriver, String dialogClass, String textValue)  {
		String xpath = "//div[contains(@class,'"+dialogClass+"')]//*[@class='x-grid3']//div[text()='"+textValue+"']";
		WebElement webElement = webDriver.findElement(By.xpath(xpath));
		webElement.click();
		Reporter.log("Check element "+textValue+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	/**
	 * Helper method to click Edit button.
	 * 
	 * */
	public void clickEditButtoninPO(String edit){
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-tab-panel-noborder') and not(contains(@class,'x-hide-display'))]/descendant::span[contains(@class,'x-tab-strip-inner')]//span[contains(text(),'Goods Receipts')]/following::div[contains(@class,'x-btn-group-notitle')]//button[contains(@class,'icon-ov-edit')]").click();
	}
}
