package test.energy.commodityclasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.McsElement;

public class CommodityClassesPageObject extends AbstractMcsTestSuite{

	public void expandConfiguration() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"span", "text()", "Configuration", true, true).click();
		Reporter.log("Expand configuration", true);
	}
	
	public void openConfigurationEntity(String entity) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"button", "text()", entity, true, true).click();
		Reporter.log("Open configuration " + entity, true);
	}

	public void clickButton(String dialogClass, String buttonName) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", dialogClass, "button", "@class", "x-btn-text",
				"text()", buttonName, true, true).click();
		Reporter.log("Click "+buttonName+" button", true);
	}
	
	public void close(String dialogClass) {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", dialogClass, "button", "text()",
				"Close", true, true).click();
		Reporter.log("Click Close button", true);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Close clicked", true);
	}
	
	/**
	 * Helper method to verify if Columns are present
	 */
	public boolean verifyColumnsInGrid(String colName){
		try{
			driver.findElement(By.xpath("//div[contains(@class,'energy editable-grid x-panel-noborder')]//div[contains(@class,'x-grid3-header')]//div[contains(@class,'x-grid3-hd') and contains(text(), '"+colName+"')]"));
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Helper method to get Value of Any Column from Commodity Classes grid
	 */
	public String getCellText(String attribute, String attributeValue, String rowNum, String columnClass, String attributeName){
		
		String xpath = "//div[contains("+attribute+", '"+attributeValue+"') and not (contains(@class, 'x-hide-display'))]//div[contains(@class,'x-grid3-body')]//div["+rowNum+"]//td[contains(@class,'"+columnClass+"')]//div";
		return McsElement.getElementByXpath(driver, xpath).getAttribute(attributeName);
	}
	
	/**
	 * Helper method to verify all Commodity Classes Exist in Grid
	 */
	public boolean verifyCommodityClassesInGrid(String attribute, String attributeValue, String columnNo, String columnName){
		try{
			driver.findElement(By.xpath("//div[contains("+attribute+", '"+attributeValue+"') and not (contains(@class, 'x-hide-display'))]//div[contains(@class,'x-grid3-body')]//div[contains(@class, 'x-grid3-col-"+columnNo+"') and contains(text(), '"+columnName+"')]"));
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Helper method to set Rollup UOM Value
	 */
	public void setRollupUOM(String attribute, String attributeValue, String rowNum, String columnClass, String value){
		WebElement element = driver.findElement(By.xpath("//div[contains("+attribute+", '"+attributeValue+"') and not (contains(@class, 'x-hide-display'))]//div[contains(@class,'x-grid3-body')]//div["+rowNum+"]//td[contains(@class,'"+columnClass+"')]//div"));
		element.click();
		
		waitForExtJSAjaxComplete(10);
		
		String inputName = McsElement.getElementByXpath(driver, "//div[contains(@class, 'energy editable-grid x-panel-noborder') and not (contains(@class, 'x-hide-display'))]//div[contains(@class,'x-grid-editor')]//input").getAttribute("name");
		
		clickLookup(attribute, attributeValue, inputName, "Select a Unit Of Measure");
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Unit Of Measure"), value, "Reference");
		
		waitForExtJSAjaxComplete(10);
	}
	
	/**
	 * Helper method to click on Any Column from Commodity Classes grid
	 */
	public void clickAnyColumn(String attribute, String attributeValue, String rowNum, String columnClass){
		
		String xpath = "//div[contains("+attribute+", '"+attributeValue+"') and not (contains(@class, 'x-hide-display'))]//div[contains(@class,'x-grid3-body')]//div["+rowNum+"]//td[contains(@class,'"+columnClass+"')]//div";
		McsElement.getElementByXpath(driver, xpath).click();
	}

	
	/**
	 * Helper method to click and change columns
	 */
	public void changeColumns(String attribute, String attributeValue, String colNum, String options){
		
		//Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.xpath("//div[contains("+attribute+", '"+attributeValue+"')]//div[contains(@class,'x-grid3-header')]//div[contains(@class,'x-grid3-header-inner')]//div[contains(@class, 'x-grid3-hd-"+colNum+"')]/a"));
		//action.moveToElement(we).build().perform();
		System.out.println("Ganesshaa");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", we);
		String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                "arguments[0].dispatchEvent(evObj);";

		((JavascriptExecutor)driver).executeScript(javaScript, we);
		waitForExtJSAjaxComplete(5);
		
		driver.findElement(By.xpath("//div[contains("+attribute+", '"+attributeValue+"')]//div[contains(@class,'x-grid3-header')]//div[contains(@class,'x-grid3-header-inner')]//div[contains(@class, 'x-grid3-hd-"+colNum+"')]/a")).click();
		waitForExtJSAjaxComplete(5);
		
		driver.findElement(By.xpath("//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//ul[contains(@class,'x-menu-list')]//span[contains(@class, 'x-menu-item-text') and text()='"+options+"']")).click();
		waitForExtJSAjaxComplete(5);
	}
	
	/**
	 * Helper method to uncheck Columns
	 */
	public void unCheckColumns(String colName) {
		driver.findElement(By.xpath("//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//ul[contains(@class, 'x-menu-list')]//li[contains(@class, 'x-menu-item-checked')]//span[contains(@class, 'x-menu-item-text') and text()='"+colName+"']")).click();
		waitForExtJSAjaxComplete(5);
	}
	
	/**
	 * Helper method to check Columns
	 */
	public void checkColumns(String colName) {
		driver.findElement(By.xpath("//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//ul[contains(@class, 'x-menu-list')]//li[contains(@class, 'x-menu-list-item')]//span[contains(@class, 'x-menu-item-text') and text()='"+colName+"']")).click();
		waitForExtJSAjaxComplete(5);
	}
	
	/**
	 * Helper method to verify Columns Not Present
	 */
	public boolean verifyColumnsNotPresent(String attribute, String attributeValue, String colName) {
		try{
			driver.findElement(By.xpath("//div[contains("+attribute+", '"+attributeValue+"')]//div[contains(@class,'x-grid3-header')]//div[contains(@class,'x-grid3-hd-inner') and text()='"+colName+"']/ancestor::td[contains(@style, 'display: none')]"));
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Helper method to Sort the Values
	 */
	public List<String> sortRollUPValues(){
		List<WebElement> values = driver.findElements(By.xpath("//div[contains(@class, 'energy editable-grid x-panel-noborder') and not (contains(@class, 'x-hide-display'))]//div[contains(@class,'x-grid3-body')]//td[contains(@class,'x-grid3-td-3')]//div"));
					
		ArrayList<String> lsValues = new ArrayList<String>(); 
			
		for(int i=0; i<values.size(); i++){
			
			String valueText = values.get(i).getText().trim();
			System.out.println(valueText);	
			lsValues.add(valueText);
		}
		
		Arrays.sort(lsValues.toArray());
		
		return lsValues;
	}
	
	/**
	 * Helper method to Sort the Values
	 */
	public List<String> sortAscRollUPValues(){
		List<WebElement> values = driver.findElements(By.xpath("//div[contains(@class, 'energy editable-grid x-panel-noborder') and not (contains(@class, 'x-hide-display'))]//div[contains(@class,'x-grid3-body')]//td[contains(@class,'x-grid3-td-3')]//div"));
					
		ArrayList<String> lsValues = new ArrayList<String>(); 
			
		for(int i=0; i<values.size(); i++){
			
			String valueText = values.get(i).getText().trim();
			System.out.println(valueText);	
			lsValues.add(valueText);
		}
		
		Arrays.sort(lsValues.toArray(), Collections.reverseOrder());
		
		return lsValues;
	}
	
	/**
	 * Helper method to get Values from Commodity Class Drop down
	 */
	public List<String> getEquivalencyValues(String attribute, String attributeValue){
		
		WebElement commodityClassDropDown;
		
		if(attribute!="slnmTrfId"){
			commodityClassDropDown = driver.findElement(By.xpath("//div[contains(@class,'"+attribute+"')]//input[@name='"+attributeValue+"']"));
		
			commodityClassDropDown.click();
		} else{
			commodityClassDropDown = driver.findElement(By.xpath("//div[contains(@class,'"+attribute+"')]//input[@name='"+attributeValue+"']//following-sibling::input"));
			
			commodityClassDropDown.click();
		}
		
		List<WebElement> values = null;
		
		if(attribute=="slnmTrfId" || attribute=="slnmNrgPrcTplId"){
			values = driver.findElements(By.xpath("//div[contains(@class, 'x-combo-list') and (contains(@style, 'visibility: visible'))]//div[contains(@class,'x-combo-list-item')]"));
		} else{
			values = driver.findElements(By.xpath("//div[contains(@class, 'x-combo-list') and (contains(@style, 'visibility: visible'))]//div[contains(@class,'x-combo-list-item')]/div"));
		}
					
		ArrayList<String> lsValues = new ArrayList<String>(); 
				
		for(int i=0; i<values.size(); i++){
					
		String valueText = values.get(i).getText().trim();
					
		lsValues.add(valueText);
		
		System.out.println(valueText);
		}
		
		return lsValues;
	}
	
}
