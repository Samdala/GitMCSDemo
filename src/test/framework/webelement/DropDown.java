package test.framework.webelement;





import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class DropDown extends AbstractWebElement {

	/**
	 * Constructs the instance using provided WebDriver and WebElement
	 * @param webDriver
	 * @param webElement
	 */
	public DropDown(WebDriver webDriver, WebElement webElement) {
		super(webDriver, webElement);
	}
	

	/**
	 * Set ExtJS Combo value using using extjs store by id, the value present in combobox can be setted only
	 * @param webDriver WebDriver instance to use
	 * @param elementId The HTML ID of the element
	 * @comboValue value present in combobox
	 * 
	 */
	public static void setExtJsComboValue(WebDriver webDriver, String elementId, String comboValue) {	
		try{((JavascriptExecutor) webDriver)
		.executeScript("var combo = Ext.getCmp('"+elementId+"');" +
				"var rec = combo.findRecord(combo.displayField, '"+comboValue+"');combo.setValue(rec.get(combo.valueField));combo.fireEvent('select', combo, rec);");}
		catch(Exception e){setComboValueNative(webDriver, elementId, comboValue);}
	}

	/**
	 * Check if ExtJS Combo value present using using extjs store by id
	 * @param webDriver WebDriver instance to use
	 * @param elementId The HTML ID of the element
	 * @comboValue value present in combobox
	 * @return true if value is present, false otherwise 
	 * 
	 */
	public static boolean isExtJsComboValuePresent(WebDriver webDriver, String elementId, String comboValue) {	
		return !(boolean) ((JavascriptExecutor) webDriver)
		.executeScript("var combo = Ext.getCmp('"+elementId+"');" +
				"var rec = combo.findRecord(combo.displayField, '"+comboValue+"');  return rec==undefined;");
		
	}
	

	/**
	 * Set Combo value using javascript by id, any value can be setted (even not present in store of combobox)
	 * @param webDriver WebDriver instance to use
	 * @param elementId The HTML ID of the element
	 * @comboValue value present in combobox
	 * 
	 */
	public static void setComboValue(WebDriver webDriver, String elementId, String comboValue) {	
		((JavascriptExecutor) webDriver)
		.executeScript("var combo = Ext.getCmp('"+elementId+"');combo.setValue('"+comboValue+"');combo.fireEvent('blur');");
	}
		
	/**
	 * Set Combo value using natural selenium commands
	 * @param webDriver WebDriver instance to use
	 * @param elementId The HTML ID of the element
	 * @comboValue value present in combobox
	 * 
	 */
	public static void setComboValueNative(WebDriver webDriver, String elementId, String comboValue) {	
		webDriver.findElement(By.xpath("//input[@id='"+elementId+"']")).click();
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		webDriver.findElement(By.xpath("//div[contains(@class,'x-combo-list') and contains(text(),'"+comboValue+"')]")).click();
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
	}
	
	
	/**
	 * Set Combo value using natural selenium commands
	 * @param webDriver WebDriver instance to use
	 * @param elementId The HTML ID of the element
	 * @comboValue value present in combobox
	 * 
	 */
	public static void setComboValueDropDownSelector(WebDriver webDriver, String elementId, String comboValue) {
		/*//Click on Dropdown down arrow.
		webDriver.findElement(By.xpath("//input[@id='"+elementId+"']//following-sibling::img")).click();
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		
		webDriver.findElement(By.xpath("//div[contains(@class,'x-combo-list') and contains(text(),'"+comboValue+"')]")).click();
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
*/	
		//Click on Dropdown down arrow.
        new WebDriverWait(webDriver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='"+elementId+"']//following-sibling::img")));
new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='"+elementId+"']//following-sibling::img")));
webDriver.findElement(By.xpath("//input[@id='"+elementId+"']//following-sibling::img")).click();

        //try {Thread.sleep(1000);} catch (InterruptedException e) {}

		if(!comboValue.trim().isEmpty()){
        new WebDriverWait(webDriver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'x-combo-list-item') and contains(text(),'"+comboValue+"') and not(ancestor::div[contains(@style,'visibility: hidden')])]")));
new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'x-combo-list-item') and contains(text(),'"+comboValue+"') and not(ancestor::div[contains(@style,'visibility: hidden')])]")));
webDriver.findElement(By.xpath("//div[contains(@class,'x-combo-list-item') and contains(text(),'"+comboValue+"') and not(ancestor::div[contains(@style,'visibility: hidden')])]")).click();
		}else{
	
			WebElement combolist =  new WebDriverWait(webDriver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'x-combo-list') and contains(@style, 'visibility: visible')]")));
			
			List<WebElement> combolistItems = combolist.findElements(By.xpath(".//div[@class='x-combo-list-item']"));
			
			for(int i=0; i<combolistItems.size(); i++){
				
				String comboListItemText = combolistItems.get(i).getText().trim();
				
				if(comboListItemText.isEmpty()){
					
					combolistItems.get(i).click();
					
					break;
					
				}
				
			}
			 
			
		}
		}
	
	
	/**
	 * Get Combo values using natural selenium commands
	 * @param webDriver WebDriver instance to use
	 * @param elementIdOfTextFieldBesidesDropDownImage The HTML ID of the element
	 * @return list of combo values
	 */
	public static List<String> getComboValuesFromDropDownSelector(WebDriver webDriver, String elementIdOfTextFieldBesidesDropDownImage) {
	
		List<String> comboValues = new ArrayList<String>();
		//Click on Dropdown down arrow.
		webDriver.findElement(By.xpath("//input[@id='"+elementIdOfTextFieldBesidesDropDownImage+"']//following-sibling::img")).click();
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		
		List<WebElement> comboBoxItemEles = webDriver.findElements(By.xpath("//div[contains(@class,'x-combo-list') and contains(@style,'visibility: visible')]//div[contains(@class,'x-combo-list-item')]"));
		
		for(WebElement comboBoxItemEle: comboBoxItemEles){
			
			comboValues.add(comboBoxItemEle.getText().trim());
		}
		return comboValues;
	}

	/**
	 * Helper method to select Menu Item 
	 * @param webDriver WebDriver instance to use
	 * @param elementId Id Of TextField Besides DropDownImage
	 * @param menuItem to select
	 */
	public static void selectDropDownMenuItem(WebDriver webDriver, String elementId, String menuItem){
		
		String xpath ="//div[contains(@class,'x-menu-floating') and contains(@style,'visibility: visible')]//span[@class='x-menu-item-text' and text()='"+menuItem+"']/..";
		
		 new WebDriverWait(webDriver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='"+elementId+"']//following-sibling::img")));
		 new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='"+elementId+"']//following-sibling::img")));
		 webDriver.findElement(By.xpath("//button[@id='"+elementId+"']//following-sibling::img")).click();

		 new WebDriverWait(webDriver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		 new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		 webDriver.findElement(By.xpath(xpath)).click();
		 
	}
	
	
	/**
	 * Helper method to select Menu Item 
	 * @param webDriver WebDriver instance to use
	 * @param buttonElementId Id Of TextField Besides DropDownImage
	 * @param menuItem to select
	 */
	public static void selectDropDownMenuItemOfButton(WebDriver webDriver, String buttonElementId, String menuItem){
		
		String xpath ="//div[contains(@class,'x-menu-floating') and contains(@style,'visibility: visible')]//span[@class='x-menu-item-text' and text()='"+menuItem+"']/..";
		
		 new WebDriverWait(webDriver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='"+buttonElementId+"']")));
		 new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='"+buttonElementId+"']")));
		 webDriver.findElement(By.xpath("//button[@id='"+buttonElementId+"']")).click();

		 new WebDriverWait(webDriver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		 new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		 webDriver.findElement(By.xpath(xpath)).click();

	}


	/**
	 * Helper method to select Menu Item from already populated List
	 * @param webDriver WebDriver instance to use
	 * @param menuItem to select 
	 */
	public static void selectMenuItemFromAlreadyPopulatedList(WebDriver webDriver, String menuItem){

		String xpath ="//div[contains(@class,'x-menu-floating') and contains(@style,'visibility: visible')]//span[@class='x-menu-item-text' and text()='"+menuItem+"']/..";

		new WebDriverWait(webDriver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		webDriver.findElement(By.xpath(xpath)).click();
	}

	/**
	 * Helper method to select Menu Item from already populated List
	 * @param webDriver WebDriver instance to use
	 * @param menuItem to select 
	 */
	public static void selectComboListItemFromAlreadyPopulatedList(WebDriver webDriver, String value){

		String xpath ="//div[contains(@class,'x-combo-list') and contains(@style,'visibility: visible')]//div[@class='x-combo-list-item' and contains(text(),'"+value+"')]";

		new WebDriverWait(webDriver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		webDriver.findElement(By.xpath(xpath)).click();
	}
}
