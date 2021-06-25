package test.generalweb.stockitems;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import test.generalweb.transactions.TransactionsPageObject;

public class StockItemsPageObject extends TransactionsPageObject{
	
	
	protected final String XPATH_PURCHASE_REQUISITION_TOOLBAR ="//div[contains(@class,'x-form-item') and not(contains(@class,'x-hide-display'))]//span[@class='x-btn-group-header-text' and text()='Purchase Requisition']/ancestor::div[contains(@class,'button-icon-extra-spacing')]//input";
	
	protected final String ADD_REQUISITION_WINDOW_HEADER = "New Purchase Requisition";
	
	protected final String EDIT_REQUISITION_WINDOW_HEADER = "Purchase Requisition";
	
	protected final String STOCK_SCRAPPING_TRANSACTION_WINDOW_HEADER = "Stock Scrapping";
	
	protected final String RETURN_TO_SUPPLIER_TRANSACTION_WINDOW_HEADER = "Return to Supplier";
	
	protected final String XPATH_STOCK_ITEMS_PAGE = "//div[@id='portalcontainer_tabs']//div[contains(@class, 'x-form-item') and not (contains(@class, 'x-hide-display'))]";
	
	//*********************Stock items Current Tab LINES**********************************************
	
	protected final String STOCK_ITEMS_LINE_LOCATION_CLASS = "curStorageLocCol";
		
	protected final String STOCK_ITEMS_LINE_AVAILABLE_RESERVED_STOCK_CLASS = "curAvailableStockCol";
		
	protected final String STOCK_ITEMS_LINE_BLOCKSTOCK_CLASS = "curBlockedStockCol";
		
	protected final String STOCK_ITEMS_LINE_TOTALSTOCK_CLASS = "curTotalStockCol";
		
	protected final String STOCK_ITEMS_WINDOW_HEADER = "warehouse";
		
	protected final String STOCK_ITEMS_PAGE_CLASS = "x-form-item";
	
	/**
	 * Helper method to Select warehouse from transactions page
	 * @param warehouse
	 */
	public void selectPRDocTypeFromDropDown(String docType) 
		 {
			
			String id = McsElement.getElementByXpath(driver, XPATH_PURCHASE_REQUISITION_TOOLBAR).getAttribute("id");
					
			DropDown.setExtJsComboValue(driver, id, docType);
			
			waitForExtJSAjaxComplete(25);
			
			Reporter.log("Set Purchase Requisition Document Type to" + docType, true);
		}
	
	/**
	 * Helper method to click on Purchase Requisition Button
	 */
	public void clickPurchaseRequisitionBtn() {
		McsElement.getElementByXpath(driver, XPATH_STOCK_ITEMS_PAGE+"//button[contains(@class,'icon-purchase-reqs')]").click();
		Reporter.log("Click Purchase Requisition Button ", true);
	}
	
		
	/**
	 * Helper method to click New Stock Correction toolbar button
	 * */
	public void clickNewStockCorrectionToolBarButton(){
		McsElement.getElementByXpath(driver, XPATH_STOCK_ITEMS_PAGE+"//button[contains(@class,'x-btn-text icon-stock-correction')]").click();
		Reporter.log("Click Stock Correction Tool Bar Button ", true);
	}
	
	/**
	 * Helper method to get cell text value
	 */
	private String getCellTextAttribute(String rowNum, String columnClass,
			String windowTitle, String attribute) {

		String xpath = "//div[@id='"
				+ getXWindowId(windowTitle)
				+ "']//div[@class='x-grid3-body']/div["
				+ rowNum + "]//td[contains(@class,'" + columnClass + "')]//div";
		
		return McsElement.getElementByXpath(driver, xpath).getAttribute(attribute);
	}
	
	/**
	 * Helper method to get Location India From Stock Items
	 */
	public String getLocationFromCurrentTabOfStockItems(String rowNum, String attributeName){
		return getCellTextAttribute(rowNum, STOCK_ITEMS_LINE_LOCATION_CLASS, STOCK_ITEMS_WINDOW_HEADER, attributeName);
	}
	
	/**
	 * Helper method to get Available and Reserved India Qty From Stock Items
	 */
	public String getAvailableAndReservedStockFromCurrentTabOfStockItems(String rowNum, String attributeName){
		return getCellTextAttribute(rowNum, STOCK_ITEMS_LINE_AVAILABLE_RESERVED_STOCK_CLASS, STOCK_ITEMS_WINDOW_HEADER, attributeName);
	}
	
	/**
	 * Helper method to get Blocked India Qty From Stock Items 
	 */
	public String getBlockedStockFromCurrentTabOfStockItems(String rowNum, String attributeName){
		return getCellTextAttribute(rowNum, STOCK_ITEMS_LINE_BLOCKSTOCK_CLASS, STOCK_ITEMS_WINDOW_HEADER, attributeName);
	}
	
	/**
	 * Helper method to get Total India Qty From Stock Items 
	 */
	public String getTotalStockFromCurrentTabOfStockItems(String rowNum, String attributeName){
		return getCellTextAttribute(rowNum, STOCK_ITEMS_LINE_TOTALSTOCK_CLASS, STOCK_ITEMS_WINDOW_HEADER, attributeName);
	}
	
	/**
	 * Helper method to Click on Tab
	 */
	private void clickOnTab(String tabText, String windowTitle) {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div", "@id", getXWindowId(windowTitle), "span", "@class", "x-tab-strip-text", "text()", tabText, true, true).click();
		waitForExtJSAjaxComplete(5);
	}
	
	/**
	 * Helper method to click on Current tab
	 */
	public void clickCurrentTab() {
		clickOnTab("Current", STOCK_ITEMS_WINDOW_HEADER);
	}
	
	/**
	 * Helper method to get Stock Items Left Panel Content
	 */
	private String getStockItemsContent(String windowTitle, String text) {
		String textContent = McsElement.getElementByXpath(driver, "//div[@id='"+ getXWindowId(windowTitle)+ "']//div[contains(@class,'current-details-panel')]//div[contains(@class,'x-panel-body-noheader')]").getAttribute("textContent");
		String str = textContent.substring(textContent.lastIndexOf(text)).trim();
		str=str.replace(' ',' ');
		return str;
	}
	
	/**
	 * Helper method to get Stock Items Left Panel Content
	 */
	public String getStockItemsLeftPanelContent(String windowTitle, String text){
		return getStockItemsContent(windowTitle, text);
	}
	
	/**
	 * Helper method to open a record in Transaction Line
	 */
	public void openTransactionLineStockItems(String attribute, String attributeValue, String rowTextName, String columnName){
		waitForExtJSAjaxComplete(25);
		
		filterGridWithoutUsingMcsElement(attribute, attributeValue, rowTextName, columnName);
		
		waitForExtJSAjaxComplete(25);
		
		WebElement ele = selectRowInGridByTextValue(rowTextName); 
		new Actions(driver).doubleClick(ele).perform();
		
		waitForExtJSAjaxComplete(25);
	}
	
	/**
	 * Helper method to check a row in Grid
	 */
	public static WebElement selectRowInGridByTextValue(String textValue)  {
		WebElement webElement = driver.findElement(By.xpath("//div[contains(@class, 'x-form-item') and not (contains(@class, 'x-hide-display'))]//*[@class='x-grid3']//div[text()='"+textValue+"']"));
		webElement.click();
		Reporter.log("Check element "+textValue+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	
	/**
	 * Helper method to verify available stock items in left panel 
	 * @param availableStockVal
	 * @return
	 */
	public boolean verifyAvailableStockFromStockItemsLeftPanel(String availableStockVal){
		String availableStock = getStockItemsLeftPanelContent(STOCK_ITEMS_WINDOW_HEADER, "Available");
		
		return verifyStockItemsBasedOnPattern(availableStock,"Available stock",availableStockVal);
	}
	
	/**
	 * Helper method to verify Reserved stock items in left panel 
	 * @param reservedStockVal
	 * @return
	 */
	public boolean verifyReservedStockFromStockItemsLeftPanel(String reservedStockVal){
		String reservedStock = getStockItemsLeftPanelContent(STOCK_ITEMS_WINDOW_HEADER, "Reserved");
		
		return verifyStockItemsBasedOnPattern(reservedStock,"Reserved stock",reservedStockVal);
	}
	
	/**
	 * Helper method to verify Blocked stock items in left panel 
	 * @param blockedStockVal
	 * @return
	 */
	public boolean verifyBlockedStockFromStockItemsLeftPanel(String blockedStockVal){
		String blockedStock = getStockItemsLeftPanelContent(STOCK_ITEMS_WINDOW_HEADER, "Blocked");
		
		return verifyStockItemsBasedOnPattern(blockedStock,"Blocked stock",blockedStockVal);
	}
	
	/**
	 * Helper method to verify Total stock items in left panel 
	 * @param totalStockVal
	 * @return
	 */
	public boolean verifyTotalStockFromStockItemsLeftPanel(String totalStockVal){
		String totalStock = getStockItemsLeftPanelContent(STOCK_ITEMS_WINDOW_HEADER, "Total");
		
		return verifyStockItemsBasedOnPattern(totalStock,"Total stock",totalStockVal);
	}

	/**
	 * Helper method to verify Regular Expression Pattern matching of Left panel content 
	 * @param availableStockVal
	 * @return
	 */
	private boolean verifyStockItemsBasedOnPattern(String leftpanelContent , String stockType, String stockVal){
		
		return leftpanelContent.matches("("+stockType+": "+stockVal+")[0-9a-zA-Z : .]*");
	}
}