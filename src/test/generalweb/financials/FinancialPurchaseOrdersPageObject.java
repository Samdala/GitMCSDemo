package test.generalweb.financials;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.webelement.McsElement;
import test.generalweb.purchaseorders.PurchaseOrdersPageObject;

public class FinancialPurchaseOrdersPageObject extends PurchaseOrdersPageObject{

	protected final String ADD_ORDER_WINDOW_HEADER = "New Purchase Order";

	protected final String EDIT_ORDER_WINDOW_HEADER = "Purchase Order";

	protected final String ADD_PR_LINE_BUTTON = "//table[contains(@class,'mcs-btn-action')]//button[contains(text(),'Add')]";

	protected final String PRODUCT_SERVICES_WINDOW_HEADER = "Select Products or Services";

	protected final String PR_LINE_LINE_ID_CLASS= "x-grid3-td-id";


	/**
	 * Checking whether Purchase Order Window is opened or not
	 **/
	public boolean isPurchaseOrderWindowOpen()
	{
		waitForExtJSAjaxComplete(20);
		String xpath = "//div[contains(@class,'x-resizable-pinned')]//span[text()='New Purchase Order']";
		return McsElement.isElementDisplayed(driver, xpath);
	}

	/**
	 * Helper method to add a Purchase requisition line
	 * @param windowTitle of Purchase requisition
	 * @param product to be selected
	 * @param columnName to be used to Select product
	 */
	public void addPRLine(String windowTitle, String product, String columnName){

		clickOnAddPRLineButton(windowTitle);

		waitForExtJSAjaxComplete(15);

		clickOnProductCatalog(windowTitle);

		waitForExtJSAjaxComplete(15);

		selectProductCatalogLines("@id", getXWindowId(PRODUCT_SERVICES_WINDOW_HEADER),product,columnName);

		Reporter.log("Add Purchase Order Line of " +product+ " is added ", true);
	}

	/**
	 * Helper method to click on Add button to add Purchase requisition lines in Requisition window
	 * @param windowTitle of Requisition window
	 **/
	public void clickOnAddPRLineButton(String windowTitle){

		McsElement.getElementByXpath(driver, "//div[@id='"+ getXWindowId(windowTitle)+"']"+ ADD_PR_LINE_BUTTON).click();
	}

	/**
	 * Helper method to click on Add button to add Purchase requisition lines in Requisition window
	 * @param windowTitle of Requisition window
	 **/
	public void clickOnProductCatalog(String windowTitle){

		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window financials')]/following::span[text()='from Product Catalog']").click();

	}

	/**
	 * Helper Method to select Product Catalog Line
	 */
	public void selectProductCatalogLines(String attribute, String attributeValue, String rowTextName, String columnName) {
		setValueGridLookupWithFilters(attribute, attributeValue, rowTextName, columnName);
		Reporter.log("Product Catalog Line selected", true);
	}

	/**
	 * Helper Method to click the row present in grid
	 */
	public WebElement checkRowInGridByTextValueAndClick(String textValue)  {
		WebElement webElement = driver.findElement(By.xpath("//*[contains(@class,'x-grid3-td-productReference')]//div[text()='"+textValue+"']"));//text()='2preConsRef'
		javaScriptFocus(webElement);
		webElement.click();
		Reporter.log("Check element "+textValue+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	/**
	 * Helper method to Get Line ID
	 */
	public String getOrderID(String windowTitle, String rowNum){
		return getCellText(rowNum , PR_LINE_LINE_ID_CLASS, windowTitle);
	}

	/**
	 * Helper method to get Cell content
	 * @param rowNum Row num of Cell
	 * @param columnClass Class of table cell
	 * @return value of the cell
	 */
	private String getCellText(String rowNum, String columnClass, String windowTitle) {

		String xpath = "//div[@id='"
				+ getXWindowId(windowTitle)
				+ "']/descendant::div[contains(@class,'x-panel-body-noheader')]//div[@class='x-grid3-body']//div["
				+ rowNum + "]//td[contains(@class,'" + columnClass + "')]//div";

		WebElement ele = driver.findElement(By.xpath(xpath));

		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		}catch(Exception e){
			e.printStackTrace();
		}

		return ele.getText();
	}

}
