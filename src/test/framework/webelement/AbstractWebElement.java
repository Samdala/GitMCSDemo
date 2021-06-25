package test.framework.webelement;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractWebElement{

	/**
	 * Web Driver instance
	 */
	protected WebDriver webDriver = null;
	
	/**
	 * Web element instance
	 */
	protected WebElement webElement = null;
	
	/**
	 * Initializes the class variables with appropriate WebElement and WebDriver instances
	 * @param webDriver
	 * @param webElement
	 */
	public AbstractWebElement(WebDriver webDriver, WebElement webElement) {
		this.webDriver = webDriver;
		this.webElement = webElement;
	}
	
	public WebElement getElement() {
		return this.webElement;
	} 

	public WebDriver getDriver() {
		return this.webDriver;
	} 
}
