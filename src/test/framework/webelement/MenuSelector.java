package test.framework.webelement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Helper Class to select a Menu item
 * @author vpcc
 * */
public class MenuSelector extends AbstractWebElement {

	/**
	 * Constructs the instance using provided WebDriver and WebElement
	 * @param webDriver
	 * @param webElement
	 */
	public MenuSelector(WebDriver webDriver, WebElement webElement) {
		super(webDriver, webElement);
	}

	
	/**
	 * Select Menu using natural selenium commands
	 * @param webDriver WebDriver instance to use
	 * @param elementId The HTML ID of the element
	 * @param menuItemText  text of Menu item
	 * 
	 */
	public static void selectMenuItemNative(WebDriver webDriver, String menuItemText) {	
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
		
		String menuXpath = "//div[contains(@class,'x-menu-floating') and not(contains(@class,'x-hide-offsets'))]//span[contains(@class,'x-menu-item-text') and text()='"
		+ menuItemText + "']/ancestor::a";
		
		webDriver.findElement(By.xpath(menuXpath)).click();
		
		try {Thread.sleep(1000);} catch (InterruptedException e) {}
	}
	
	
}
