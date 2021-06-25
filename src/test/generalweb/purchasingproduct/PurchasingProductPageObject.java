package test.generalweb.purchasingproduct;

//import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.browserlaunchers.Sleeper;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.google.common.base.Function;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import test.framework.webelement.FileUploader;

public class PurchasingProductPageObject extends AbstractMcsTestSuite {
	
	protected final String PURCHASING_PRODUCT_WINDOW_CLASS = "mcsfinancialspurchasingproductdetails";

	protected final String XPATH_OVERVIEW_BUTTON = "//button[contains(@class,'x-btn-text icon-ov-overview')]";

	protected final String XPATH_OVERVIEW_BUTTON_GENERAL = "//span[contains(@class,'x-menu-item-text') and contains(text(),'General')]";

	protected final String OVERVIEW_BUTTON_GENERAL = "//span[contains(@class,'x-menu-item-text']";

	protected final String XPATH_OVERVIEW_BUTTON_GENERAL_ALL = "//span[contains(@class,'x-menu-item-text') and contains(text(),'All')]";

	protected final String LINK_TEXT = "All";

	protected final String ADD_BUTTON = "//button[contains(@class,'x-btn-text icon-ov-add')]";
	
	protected final String EDIT_BUTTON = "//button[contains(@class,'x-btn-text icon-ov-edit')]";

	protected final String REFERENCE_INPUT = "//input[@name='reference']";

	protected final String PRODUCT_CODE_INPUT = "//input[@name='code']";

	protected final String DEFAULT_EXPENCE_INPUT = "//input[@name='defaultExpenseAmount']";

	protected final String DEFAULT_REVENUE_INPUT = "//input[@name='defaultRevenueAmount']";

	protected final String ADD_PRODUCT_GROUP_XPATH = "//div[contains(@class,'"+ PURCHASING_PRODUCT_WINDOW_CLASS +"')]//fieldset//button[contains(text(),'Add...')]";
	
	protected final String XPATH_XWINDOW = "//div[contains(@class,'x-window x-window-noborder x-resizable-pinned')]";
	
	protected final String XWINDOW_FOOTER_CLASS = "x-window-footer x-window-footer-noborder x-panel-btns";
	
	protected final String DETAILS_GENERAL_FORM_CLASS = "x-panel-body x-panel-body-noheader x-panel-body-noborder x-form";
	
	protected final String XWINDOW_CLASS = "x-window x-window-noborder x-resizable-pinned";
	
	protected final String DESCRIPTION_TXTAREA = "//textarea[contains(@name,'description')]";
	
	protected final String COST_CATEGORY_SUPPLIERS_XPATH = "//div[contains(@class,'"+ PURCHASING_PRODUCT_WINDOW_CLASS +"')]//div[contains(@class, 'x-form-composite')]//input[contains(@name, 'costCategory')]//..//..//button";
	
	protected final String CHANGE_BUTTON_PICTURE = "//input[@type='file' and @name='file']";
	
	protected final String HYPERLINK_XPATH = "//input[@name='strHyperlink']";
	
	protected final String HYPERLINK_DESCRIPTION = "//input[@name='strDescription']";
	
	protected final String HYPERLINK_REMARK = "//input[@name='strRemark']";
	
	protected final String ADD_FILE_XPATH = "//button[text()='Add File' and contains(@style,'icon-addfile.png')]";
	
	protected final String ADD_URL_XPATH = "//div[contains(@class,'mcsfinancialspurchasingproductdetails')]//button[contains(text(),'Add URL')]";
	
	protected final String ADD_URL_CREATE_XPATH = "//div[contains(@class,'x-window')]//button[text()='Create']";
	
	protected final String DELETE_URL_XPATH = "//button[text()='Delete' and contains(@style,'icon-delete.png')]";
	
	protected final String WAREHOUSE_WINDOW_HEADER = "Warehouse [";
	
	protected final String STOCK_ITEM_GRID = "stock-items-grid";
	
	
	//PRODUCT PICTURE
	protected final String PRODUCT_PIC_CONTAINER ="//div[contains(@class,'mcsfinancialspurchasingproductdetails')]//div[contains(@class,'x-tab-panel-body-top')]/div[not(contains(@class,'x-hide-display'))]//form//div[contains(@class,'x-panel-noborder')]";
	//protected final String PRODUCT_PIC_CONTAINER ="//div[contains(@class,'mcsfinancialspurchasingproductdetails')]//div[contains(@class,'x-panel-noborder') and not(contains(@class,'x-hide-display'))]//div[contains(@class,'x-panel-body-noheader')]";

	
	public void goToPurchasingProduct() {
		driver.get(configuration.getApplicationUrl()
				+ "/frame.php?relay=MCS_PORTAL_ST-ACTIVATE[PURCHASING_PRODUCTS]");
	};

	public void clickOverview() {
		waitAndClick(XPATH_OVERVIEW_BUTTON);
	}

	public void clickOverviewGeneral() {
		waitAndClick(OVERVIEW_BUTTON_GENERAL);
	}

	public void clickAll() {
		waitAndClickLink(LINK_TEXT);
	}

	
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
	
	public void clickGeneralTab(){
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", PURCHASING_PRODUCT_WINDOW_CLASS, "span", "text()", "General", true, true).click();
	}
	
	/**
	 * Helper method to click on Stock tab
	 * */
	public void clickStockTab(){
		
		String xpath = "//div[contains(@class,"+PURCHASING_PRODUCT_WINDOW_CLASS+")]//span[text()='Stock']";
		waitForExtJSAjaxComplete(20);
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", PURCHASING_PRODUCT_WINDOW_CLASS, "span", "text()", "Stock", true, true).click();
		
	}
	
	/**
	 * Helper method to click on Picture tab
	 * */
	public void clickPictureTab(){
		
		String xpath = "//div[contains(@class,"+PURCHASING_PRODUCT_WINDOW_CLASS+")]//span[text()='Picture']";
		waitForExtJSAjaxComplete(20);
		
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", PURCHASING_PRODUCT_WINDOW_CLASS, "span", "text()", "Picture", true, true).click();
	}
	
	public void setReference(String reference) {
		waitAndSendKeys(REFERENCE_INPUT, reference);
	}

	public void setProductCode(String code) {
		waitAndSendKeys(PRODUCT_CODE_INPUT, code);
	}

	public void setDefaultExpense(String expense) {
		waitAndSendKeys(DEFAULT_EXPENCE_INPUT, expense);
	}

	public void setDefaultRevenue(String expense) {
		waitAndSendKeys(DEFAULT_REVENUE_INPUT, expense);
	}

	

	public void selectProductGroup(String name) {
		Timer timer = new Timer().start();
		
		waitFocusAndClick(ADD_PRODUCT_GROUP_XPATH);
		
		String id = getXWindowId("Select Product Groups");
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", id, "span", "text()", "List", true, true).click();
		
		waitForExtJSAjaxComplete(25);
		
		filterGrid("@id", id, name, "Reference");
		
		String columnClass = McsElement
				.getElementByPartAttributeValueAndParentElement(driver,
						"div", "@id", id,
						"div","@class", "x-grid3-hd",
						"text()", "Reference", true, true).getAttribute("class");
				
				String columnNumber = columnClass.substring(columnClass.length() - 1);
					
					McsElement
							.getElementByPartAttributeValueAndParentElement(driver,
									"div", "@id", id, "div",
									"@class", "x-grid3-cell-inner x-grid3-col-"+columnNumber,
									"text()", name, true, true).click();
		
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", id, "button", "@style", "add.png", true, true).click();
	
		waitForExtJSAjaxComplete(25);
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", id, "button", "text()", "OK", true, true).click();
		
		waitForExtJSAjaxComplete(25);
		
		Reporter.log("Product group" +name+"was selected"+ " (" + timer.stop()
				+ "ms)", true);
	}

	
	public void clickOkXwindow() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XWINDOW_FOOTER_CLASS, "button",
				"text()", "OK", true, true).click();
		waitForElementDisappear(XPATH_XWINDOW);
	}

	public void clickCancelXwindow() {
		System.out.println("inside");
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XWINDOW_FOOTER_CLASS, "button",
				"text()", "Cancel", true, true).click();
		waitForElementDisappear(XPATH_XWINDOW);
		System.out.println("after");
	}
	
	
	public void setFirstCostCategory(String customer) {
		
		clickLookup("@class",PURCHASING_PRODUCT_WINDOW_CLASS, "costCategory","Select a Cost Category");
		
		waitForExtJSAjaxComplete(25);
		
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@id", getXWindowId("Select a Cost Category"), "span", "text()", "List", true, true).click();
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Cost Category"), customer, "Reference");
	
	}

	
	public void setFirstUnitMeasure(String customer) {
		
		clickLookup("@class",PURCHASING_PRODUCT_WINDOW_CLASS, "unitOfMeasure","Select a Unit Of Measure");
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Unit Of Measure"), customer, "Reference");
		
	}

	
	public void setRevenueTaxCode(String customer) {

		clickLookup("@class",PURCHASING_PRODUCT_WINDOW_CLASS, "revenueTaxCode","Select a Revenue Tax Code");
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Revenue Tax Code"), customer, "Reference");
		
	}

	
	
	public void setFirstProductCategory(String customer) {

		clickLookup("@class",PURCHASING_PRODUCT_WINDOW_CLASS, "productCategory","Select a Product Category");
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Product Category"), customer, "Reference");
		
	}

	public void setProductDesignation(String customer) {
		
		String id = McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", PURCHASING_PRODUCT_WINDOW_CLASS, "input", "@name", "designation", true, true).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, customer);
		
		waitForExtJSAjaxComplete(25);
		
	}
	
	public void setForm(String customer) {
		
		String id = McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", PURCHASING_PRODUCT_WINDOW_CLASS, "input", "@name", "productForm", true, true).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, customer);
		waitForExtJSAjaxComplete(25);
		
	}

	
	public void setFormula(String formula){
		
		String id = McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", PURCHASING_PRODUCT_WINDOW_CLASS, "input", "@name", "calculationFormula", true, true).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, formula);
		waitForExtJSAjaxComplete(25);
		
	}
	
	public void setPrecision(String formula){
		
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", PURCHASING_PRODUCT_WINDOW_CLASS, "input", "@name", "calculationPrecision", true, true).clear();
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", PURCHASING_PRODUCT_WINDOW_CLASS, "input", "@name", "calculationPrecision", true, true).sendKeys(formula);
	
	}
	
	
	public void setExtMatNumber(String extNumber) {
		clickLookup("@class", PURCHASING_PRODUCT_WINDOW_CLASS,
				"externalMaterialNumber", "Select an External Material Number");

		waitForExtJSAjaxComplete(25);

		setValueGridLookupWithFilters("@id", getXWindowId("Select an External Material Number"), extNumber, "Reference");
	}
	
	
	public void setDefaultLocation(String extNumber) {
		/*clickLookup("@class", PURCHASING_PRODUCT_WINDOW_CLASS,
				"defaultLocation", "Select a Location");
*/
		clickLookupNewUI("@class", PURCHASING_PRODUCT_WINDOW_CLASS,
				"defaultLocation", "Select a Location");

		waitForExtJSAjaxComplete(25);

		//setValueGridLookupWithFilters("@id", getXWindowId("Select a Location"), extNumber, "Reference");
		setValueGridLookupWithFiltersNewUI("@class", "x6-window-default-closable", extNumber, "Reference");
	} 
	
	
	public void setManufPartNr(String part){
		WebElement element = McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", PURCHASING_PRODUCT_WINDOW_CLASS, "input", "@name", "manufacturerPartNr", true, true);
		element.clear();
		element.sendKeys(part);
		
	}
	
	
	public void  setCurrency(String currency){
		String id = McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", PURCHASING_PRODUCT_WINDOW_CLASS, "input", "@name", "currency", true, true).getAttribute("id");
		DropDown.setExtJsComboValue(driver, id, currency);
		waitForExtJSAjaxComplete(25);
		
	}
	

	
	public void saveProduct() {
		McsElement.getElementByXpath(driver, "//div[contains(@class, 'mcsfinancialspurchasingproductdetails') and not (contains(@style, 'visibility: hidden'))]//button[contains(text(), 'Save')]").click();
		waitForExtJSAjaxComplete(20);
	}

	public void close() {
		McsElement.getElementByXpath(driver, "//div[contains(@class, 'mcsfinancialspurchasingproductdetails') and not (contains(@style, 'visibility: hidden'))]//button[contains(text(), 'Close')]").click();
		waitForExtJSAjaxComplete(20);
	}
	
	
	public void clickDeleteButton() {
		McsElement.getElementByPartAttributeValue(driver, "button", "@class",
				"x-btn-text icon-ov-remove", "text()", "Delete", true, true).click();
		waitForExtJSAjaxComplete(20);
	}
	
	
	/**
	 * Helper method to get Unit of Measure Lookup value
	 * */
	public String getUnitOfMeasure() {
		
		return  McsElement.getElementByXpath(driver, "//input[@name='unitOfMeasure']/following-sibling::input[@type='text']").getAttribute("value");
	}
	
	/**
	 * Helper method to get value of Product Reference
	 * */
	public String getReference(){
		
		return McsElement.getElementByXpath(driver,REFERENCE_INPUT).getAttribute("value");
	}

	/**
	 * Helper method to get defaultExpenseAmount
	 * @return defaultExpenseAmount
	 */
	public String getDefaultExpenseAmount(){
		
		return McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", PURCHASING_PRODUCT_WINDOW_CLASS, "input", "@name", "defaultExpenseAmount", true, true).getAttribute("value");
		
	}
	//******************Stock Item Related********************
	
	public void addWareHouse(String reference){
		
		clickAddButtonInStockTab();
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters("@class", "x-window-noborder", reference, "Reference");
		
		Reporter.log("selected Ware house "+reference, true);
		
	}
	
	/**
	 * Helper method to click on Add button in Stock tab of Purchasing Product screen
	 * */
	public void clickAddButtonInStockTab(){
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-border-layout-ct') and not(contains(@class,'x-hide-display'))]//div[contains(@class,'x-panel-bbar')]//button[contains(text(),'Add..')]").click();
		
		//McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", "x-panel-bbar", "button", "text()", "Add...", true, true).click();
		
		Reporter.log("Clicked on Add button in Stock Tab", true);
	}
	

	/**
	 * Helper method to click on Delete button in Stock tab of Purchasing Product screen
	 * */
	public void clickDeleteButtonInStockTab(){
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-border-layout-ct') and not(contains(@class,'x-hide-display'))]//div[contains(@class,'x-panel-bbar')]//button[contains(text(),'Delete')]").click();
		//McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", "x-panel-bbar", "button", "text()", "Delete", true, true).click();
		
		Reporter.log("Clicked on Delete button in Stock Tab", true);
	}
	
	/**
	 * Helper method to select a Ware house in List of added Ware houses in Stoc tab
	 * @param warehouseRef - Reference of Added ware house
	 * */
	public void selectAddedWareHouse(String warehouseRef){
		
		//Sleeper.sleepTightInSeconds(5);
		WebElement wareHouse = driver.findElement(By.xpath( "(//div[contains(@class,'mcsfinancialspurchasingproductdetails')]//*[@class='x-grid3']//div[text()='"+warehouseRef+"'])[last()]"));
		
		//Simple webelement.click() is throwing "Element is not clickable at point" error.
		//So using Actions class to click on Element
		Actions action = new Actions(driver);
		action.moveToElement(wareHouse, 5, 5).click().perform();
		
	}
	
	/**
	 * Helper method to check if Ware house with reference is present in added Warehouse list  
	 * @param warehouseRef - Reference of Warehouse to be verified
	 * */
	public boolean isWareHouseAddedInStockTab(String warehouseRef){
		
		return McsElement.isElementPresent(driver, "(//div[contains(@class,'mcsfinancialspurchasingproductdetails')]//*[@class='x-grid3']//div[text()='"+warehouseRef+"'])[last()]");
	}
	
	
	/**
	 * Helper method to check if Ware house with reference is present in added Warehouse list  
	 * @param warehouseRef - Reference of Warehouse to be verified
	 * */
	public boolean isWareHouseDeletedInStockTab(String warehouseRef){
		
		return McsElement.isElementAbsent(driver, "(//div[contains(@class,'mcsfinancialspurchasingproductdetails')]//*[@class='x-grid3']//div[text()='"+warehouseRef+"'])[last()]");
	}
	
	// ******************Add Description Related********************

	/**
	 * Helper method to click on Description Tab of Purchasing Product
	 * */
	public void clickDescriptionTab() {
		
		String xpath = "//div[contains(@class,"+PURCHASING_PRODUCT_WINDOW_CLASS+")]//span[text()='Description']";
		
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", PURCHASING_PRODUCT_WINDOW_CLASS, "span", "text()",
				"Description", true, true).click();

	}

	/**
	 * Helper method to add Description to Purchasing Product
	 * */

	public void addDescription(String descRef) {

		waitAndSendKeys(DESCRIPTION_TXTAREA, descRef);

	}

	/**
	 * Helper method to get value of Description field
	 * */
	public String getDescription() {
		return McsElement.getElementByXpath(driver, DESCRIPTION_TXTAREA)
				.getAttribute("value");
	}

	/**
	 * Helper method to clear value of Description field
	 * */
	public void clearDescription() {

		WebElement descField = McsElement.getElementByXpath(driver,	DESCRIPTION_TXTAREA);
	
		descField.sendKeys(" ");
		
		descField.clear();
		
		descField.sendKeys(Keys.TAB);
	}
	
	///////////////////////Suppliers Tab/////////////////////////////
	
	public void clickSuppliersTab() {
		
		String xpath = "//div[contains(@class,"+PURCHASING_PRODUCT_WINDOW_CLASS+")]//span[text()='Suppliers']";
		
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	
		McsElement.getLastElementByAttributeValueAndParentElement(driver, "div",
				"@class", PURCHASING_PRODUCT_WINDOW_CLASS, "span", "text()",
				"Suppliers", true, true).click();
		
		Reporter.log("Clicked Suppliers Tab", true);
	}

	public void addSupplier(String supplier) {
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Supplier"),
				supplier, "Name");
		waitForExtJSAjaxComplete(10);
		Reporter.log("Add Supplier", true);
	}

	public void clickAddButtonInSuppliersTab() {

		McsElement
				.getElementByXpath(
						driver,
						"//div[contains(@class, 'x-grid-panel') and not(contains(@realid,'mogrid'))]//button[contains(text(),'Add...')]")
				.click();
		Reporter.log("Clicked on Add button in Suppliers Tab", true);
	}

	public void openSuppliersInfoLayOut(String supplier1) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", PURCHASING_PRODUCT_WINDOW_CLASS, "div",
				"@class", "x-grid3-col-supplier", "text()", supplier1, true,
				true).click();
		Reporter.log("Opened suppliers info layout", true);
	}

	public void clickFinancialKeysTabInSuppliers() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", PURCHASING_PRODUCT_WINDOW_CLASS, "span", "text()",
				"Financial Keys", true, true).click();
		Reporter.log("Clicked Financial Keys Tab", true);
	}

	public void clickGeneralTabInSuppliers() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "form",
				"@class", DETAILS_GENERAL_FORM_CLASS, "span", "text()",
				"General", true, true).click();
		Reporter.log("Clicked General Tab in Suppliers Tab", true);
	}

	public void setFirstCostCenter(String costCenter) {
		clickLookup("@class", PURCHASING_PRODUCT_WINDOW_CLASS, "costCenter",
				"Select a Cost Center");
		waitForExtJSAjaxComplete(25);
		setValueGridLookupWithFilters("@id",
				getXWindowId("Select a Cost Center"), costCenter, "Reference");
		Reporter.log("Set Cost Center", true);
	}
	
	public void setFirstCostCategoryInSuppliersTab(String costCategory) {
		Timer timer = new Timer().start();
				
		waitFocusAndClick(COST_CATEGORY_SUPPLIERS_XPATH);
		
		waitForExtJSAjaxComplete(25);
				
		String id = getXWindowId("Select a Cost Category");
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", id, "span", "text()", "List", true, true).click();
		
		waitForExtJSAjaxComplete(25);
		
		filterGrid("@id", id, costCategory, "Reference");
		
		String columnClass = McsElement
				.getElementByPartAttributeValueAndParentElement(driver,
						"div", "@id", id,
						"div","@class", "x-grid3-hd",
						"text()", "Reference", true, true).getAttribute("class");
				
				String columnNumber = columnClass.substring(columnClass.length() - 1);
					
					McsElement
							.getElementByPartAttributeValueAndParentElement(driver,
									"div", "@id", id, "div",
									"@class", "x-grid3-cell-inner x-grid3-col-"+columnNumber,
									"text()", costCategory, true, true).click();
		
		waitForExtJSAjaxComplete(25);
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", id, "button", "text()", "OK", true, true).click();
		
		waitForExtJSAjaxComplete(25);
		
		Reporter.log("Cost Category" +costCategory+"was selected"+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void setFirstGLAccount(String glAccount) {
		clickLookup("@class",PURCHASING_PRODUCT_WINDOW_CLASS, "glAccount","Select a GL Account");
		waitForExtJSAjaxComplete(25);
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@id", getXWindowId("Select a GL Account"), "span", "text()", "List", true, true).click();
		setValueGridLookupWithFilters("@id", getXWindowId("Select a GL Account"), glAccount, "Reference");
		Reporter.log("Set GL Account", true);
	}

	public void checkIsPreferredSupplier() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", PURCHASING_PRODUCT_WINDOW_CLASS, "input", "@name",
				"preferred", true, true).click();
		Reporter.log("Check Preferred Suppliers Tab", true);
	}

	public void clickRemove() {
		McsElement
				.getElementByXpath(
						driver,
						"//div[contains(@class, 'x-grid-panel x-border-panel') and not(contains(@realid,'mogrid'))]//button[contains(text(),'Remove')]")
				.click();
		clickOnDialogButton("Yes");
		Reporter.log("Click Remove Button", true);
	}
	
	/**
	 * Helper method to get Standard price for the supplier
	 * @return Standard price
	 */
	public String getStandardPriceFromGeneralTab(){
		
		return McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@class", PURCHASING_PRODUCT_WINDOW_CLASS, "input", "@name", "standardPrice", true, true).getAttribute("value");
		
	}
	
	//************Picture tab related*****************************
	
		/**
		 * Helper method to click on Change button in Picture tab of Purchasing Product screen
		 * */
		public void clickChangeButtonInPictureTab(){
			
			McsElement.getElementByXpath(driver, "//div[contains(@class,'x-panel-noborder') and not(contains(@class,'x-hide-display'))]//div[contains(@class,'x-panel-bbar')]//button[contains(text(),'Change...')]").click();
			
			Reporter.log("Clicked on Change button in Picture Tab", true);
		}
		

		/**
		 * Helper method to click on Remove button in Picture tab of Purchasing Product screen
		 * */
		public void clickRemoveButtonInPictureTab(){
			
			McsElement.getElementByXpath(driver, "//div[contains(@class,'x-panel-noborder') and not(contains(@class,'x-hide-display'))]//div[contains(@class,'x-panel-bbar')]//button[contains(text(),'Remove')]").click();
			
			Reporter.log("Clicked on Remove button in Picture Tab", true);
		} 
		
		/**
		 * Helper method to set Picture of Product in Product tab
		 * Note: Don't click on Change button in this function. Clicking on Change button will open File dialog
		 * FileUploader successfully does sendkeys over hidden file element. No need to click on Change button 
		 * */
		public void setProductPicture(String file){

			FileUploader.uploadFileName(driver, "@class", "x-panel-noborder", file);
			
			waitForExtJSAjaxComplete(25);
			
			Reporter.log("Setting Picture for Product in Picture Tab", true);
		}
		
		/**
		 * Helper method to get Product picture details
		 * */
		public String getProductPictureDetails(){
			
			return (hasProductPicture())?McsElement.getElementByXpath(driver, PRODUCT_PIC_CONTAINER+"//img").getAttribute("src"): "";
		}
		
		
		/**
		 * Helper method to check if Product has picture for it in Picture tab
		 * */
		public boolean hasProductPicture(){
				
		return	McsElement.isElementPresent(driver, PRODUCT_PIC_CONTAINER+"//img");
			
		}
		
	// ///////////Related Documents Tab/////////
	public void clickRelatedDocumentsTab() {
		
		waitForExtJSAjaxComplete(20);
		
		String xpath = "//div[contains(@class,"+PURCHASING_PRODUCT_WINDOW_CLASS+")]//span[text()='Attachments']";
		
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		
		waitForExtJSAjaxComplete(10);
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", PURCHASING_PRODUCT_WINDOW_CLASS, "span", "text()",
				"Attachments", true, true).click();
		Reporter.log("Click Related Documents Tab", true);
	}

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

		waitFocusAndClick("//div[contains(@class,'x-window')]//label[contains(text(),'Type')]/..//button");

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

		waitFocusAndClick("//div[contains(@class,'x-window')]//label[contains(text(),'Type')]/..//button");

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

	public void deleteUrl(String description) {
		WebElement element = driver.findElement(By
				.xpath("//*[@class='x-grid3']//div[text()='" + description
						+ "']"));
		waitFocusAndClick(element);
		waitForExtJSAjaxComplete(25);
		McsElement.getElementByXpath(driver, DELETE_URL_XPATH).click();
		waitForExtJSAjaxComplete(25);
		clickOnDialogButton("Yes");
		waitForExtJSAjaxComplete(25);
		Reporter.log("url or file was deleted <br>", true);
	}
	
	////Stock Item is UnEditable in Purchasing Product once it is Linked to a Warehouse ////
	public void checkIsEnabled() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", PURCHASING_PRODUCT_WINDOW_CLASS, "input", "@name",
				"isEnabled", true, true).click();
		Reporter.log("Check IsEnabled", true);
	}
	
	public void checkIsStockItem() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", PURCHASING_PRODUCT_WINDOW_CLASS, "input", "@name",
				"isStockItem", true, true).click();
		Reporter.log("Check IsStockItem", true);
	}
	
	public boolean verifyEditabilityOfIsStockItem(){
		boolean bStatus = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", PURCHASING_PRODUCT_WINDOW_CLASS, "input", "@name", "isStockItem", 
				true, false).isEnabled();
		return bStatus;
	}
	
	public boolean verifyEditabilityOfIsEnabled(){
		boolean bStatus = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", PURCHASING_PRODUCT_WINDOW_CLASS, "input", "@name", "isEnabled", 
				true, false).isEnabled();
		return bStatus;
	}
	
	/**
	 * Helper Method to get IsEnabled Check box State
	 */
	public boolean getIsEnabledState() {
		if (McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", PURCHASING_PRODUCT_WINDOW_CLASS, "input", "@name",
				"isEnabled", true, false).getAttribute("checked") == null) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public void unCheckIsEnabled() {

		WebElement isChecked = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@class", PURCHASING_PRODUCT_WINDOW_CLASS, "input",
						"@name", "isEnabled", true, true);

		Boolean bIsChecked = Boolean.parseBoolean(isChecked.getAttribute("checked"));
		if (bIsChecked) {

			isChecked.click();
			clickOnDialogButton("Yes");
		}

		Reporter.log("IsEnabled checkbox is unchecked", true);
	}
}
