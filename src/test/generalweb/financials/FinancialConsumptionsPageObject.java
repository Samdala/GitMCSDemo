package test.generalweb.financials;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.webelement.McsElement;

public class FinancialConsumptionsPageObject extends  AbstractMcsTestSuite{

	protected final String PARAMETER_QUANTITY_XPATH = "//label[text()='Quantity:']/following::input[contains(@class,'x-form-num-field')]";
	protected final String CONSUMPTION_WINDOW_HEADER = "Edit Consumption";
	protected final String CHANGE_VISIBLE_COLUMNS_HEADER = "Change visible columns";
	protected final String FILTER_WINDOW_CLASS = "x-window";

	/**
	 * Checking whether Consumption Edit Window is opened or not
	 **/
	public boolean isConsumtionsEditWindowOpen(){
		waitForExtJSAjaxComplete(20);
		String xpath = "//div[contains(@class,'x-window x-resizable-pinned')]//span[contains(text(),'Edit Consumption')]";
		return McsElement.isElementDisplayed(driver, xpath);
	}

	/**
	 * Helper Method to set the Quantity
	 */
	public void setQuantity(String quantity){
		McsElement.getElementByXpath(driver, PARAMETER_QUANTITY_XPATH).click();
		McsElement.getElementByXpath(driver, PARAMETER_QUANTITY_XPATH).clear();
		McsElement.getElementByXpath(driver, PARAMETER_QUANTITY_XPATH).sendKeys(quantity);
		McsElement.getElementByXpath(driver, PARAMETER_QUANTITY_XPATH).sendKeys(Keys.ENTER);
		McsElement.getElementByXpath(driver, PARAMETER_QUANTITY_XPATH).click();
	}		

	/**
	 * Helper method to click on Change Visible Columns
	 */
	public void clickChangeVisibleColumns(){
		McsElement.getElementByXpath(driver,
				"//div[contains(@class, 'x-tab-panel-body-noborder x-tab-panel-body-top') and not (contains(@class, 'hide-display'))]//button[contains(@class, 'icon-grid-columns')]").click();
		Reporter.log("Click on Change Visible Columns button", true);
	}

	/**
	 * Helper method to click on Filter Results 
	 */
	public void clickFilterResults(){
		McsElement.getElementByXpath(driver,
				"//div[contains(@class, 'x-tab-panel-body-noborder x-tab-panel-body-top') and not (contains(@class, 'hide-display'))]//button[contains(@class, 'icon-grid-filter') and not (contains(text(), 'Clear Filters'))]")
				.click();
		Reporter.log("Click on Filter Results button", true);
	}

	/**
	 * Helper method to set value in Filter Results
	 */
	public void setValue(String xwindowTitle, String fieldValue){
		WebElement ele = McsElement.getElementByXpath(driver,
				"//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//div[contains(@class, 'x-box-item')]//div[@class='x-box-inner']//input");
		ele.click();
		ele.clear();
		ele.sendKeys(fieldValue);
		McsElement.getElementByXpath(driver, "//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//button[contains(text(), 'OK')]").click();
		waitForExtJSAjaxComplete(10);
	}

	/**
	 * Helper method to Remove Restriction Value in Filter Results
	 **/
	public void clickRemoveRestriction() {
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-panel')]//button[text()='Remove restriction']").click();
	}

	/**
	 * Helper method to ADD Value in Filter Results
	 **/
	public void expandFilterProperties(String className, String filterProperties, String fieldValue){
		//Click on Add Restriction
		driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowIdByClass(className)+"')]//div[contains(@class, 'x-toolbar x-small-editor')]//button[contains(text(), 'Add restriction')]")).click();
		waitForExtJSAjaxComplete(10);
		//Check Maintenance Expanded
		WebElement xpath = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowIdByClass(className)+"')]//span[text()='"+filterProperties+"']/ancestor::div[contains(@class, 'x-tree-node-el x-unselectable')]"));
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", xpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String xpathClassName = xpath.getAttribute("class");
		if(xpathClassName.contains("collapsed")) {
			driver.findElement(
					By.xpath("//div[contains(@id, '"
							+ getXWindowIdByClass(className)
							+ "')]//span[text()='"+filterProperties+"']/ancestor::div[contains(@class, 'x-tree-node-collapsed')]//img[contains(@class, 'x-tree-ec-icon x-tree-elbow-plus')]"))
							.click();
		}
		waitForExtJSAjaxComplete(10);
		//Double click on Field
		WebElement element = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowIdByClass(className)+"')]//ul[contains(@class, 'x-tree-node-ct')]//span[contains(text(), '"+fieldValue+"')]"));
		new Actions(driver).doubleClick(element).perform();
		waitForExtJSAjaxComplete(10);
	}

	/**
	 * Helper method to Save
	 **/
	public void clickSave(){
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window')]//button[text()='Save']").click();
		Reporter.log("Saved Sucessfully", true);
	}

	/**
	 * Helper method to Save
	 **/
	public void saveConsumption(){
		clickXPath("//span[contains(text(),'Edit Consumption')]//ancestor::div[contains(@class,'x-resizable')]//button[text()='Save']");
		Reporter.log("Consumption was Saved Sucessfully <br>", true);
	}

	/**
	 * Helper method to get Consumption Cell Text
	 * @return
	 */
	public String getCellText(String attribute, String attributeValue, String rowNum, String columnName) {
		WebElement ele = driver.findElement(By.xpath("(//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')][last()])"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		}catch(Exception e){
			e.printStackTrace();
		}
		String columnClass = ele.getAttribute("class"); 
		String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);
		WebElement xpath = McsElement.getElementByXpath(driver,"//div[contains("+attribute+", '"+attributeValue+"') and not (contains(@class, 'x-hide-display'))]//div[contains(@class, 'x-grid3-body')]/div["+rowNum+"]//div[contains(@class,'x-grid3-cell-inner x-grid3-col-"+columnNumber+"')]");
		String value = xpath.getText();
		waitForExtJSAjaxComplete(20);
		return value;
	}

}
