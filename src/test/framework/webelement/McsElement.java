package test.framework.webelement;



//import java.util.List;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import test.configuration.Configuration;

public class McsElement extends AbstractWebElement {

	/**
	 * Constructs the instance using provided WebDriver and WebElement
	 * @param webDriver
	 * @param webElement
	 */
	public McsElement(WebDriver webDriver, WebElement webElement) {
		super(webDriver, webElement);
	}
	
	
	/**
	 * XPATH Locator Helps to locate an element by two attribute parts of the element (@id, @class, @name, text() etc)
	 * @param element The name of the element
	 * @param webDriver WebDriver instance to use
	 * @param attributeName  The name of the attribute in the following form: @id, @class, @name, text() 
	 * @param attributeValue The part value of the attribute
	 * @param visible - means that element should be visible
	 * @param clickable - means that element should be visible and clickable (enabled) 
	 * @return webelement
	 * @throws NoSuchElementException
	 */
	public static WebElement getElementByPartAttributeValue(WebDriver webDriver, String element, String attributeName1, String attributeValue1,
			String attributeName2, String attributeValue2, boolean visible, boolean clickable) throws NoSuchElementException {
		
		int timer = 0;
		WebElement webElement;
		try {timer = Configuration.getConfiguration(null).getImplicitWait();} catch (Exception e) {}
		
		String elementXpath = "//"+element+"[contains("+attributeName1+",'" + attributeValue1 + "')"
				+ "and contains("+attributeName2+ ",'" + attributeValue2 + "')" + "]";

		if (clickable) {
			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
			return webElement;
		}
		
		if (visible) {
			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
			return webElement;
		}
		
		webElement = webDriver.findElement(By
				.xpath(elementXpath));
		
		return webElement;
	}

	
	/**
	 * XPATH Locator Helps to locate an element by part of the element attributes (@id, @class, @name etc)
	 * @param element The name of the element
	 * @param webDriver WebDriver instance to use
	 * @param attributeName  The name of the attribute in the following form: @id, @class, @name, text() 
	 * @param attributeValue The part value of the attribute
	 * @param visible - means that element should be visible
	 * @param clickable - means that element should be visible and clickable (enabled) 
	 * @return webelement
	 * @throws NoSuchElementException
	 */
	public static WebElement getElementByPartAttributeValue(WebDriver webDriver, String element, String attributeName,
			String attributeValue, boolean visible, boolean clickable) throws NoSuchElementException {
		
		int timer = 0;
		WebElement webElement;
		try {timer = Configuration.getConfiguration(null).getImplicitWait();} catch (Exception e) {}
		
		String elementXpath = "//"+element+"[contains("+attributeName+",'" + attributeValue + "')" + "]";
		
		if (clickable) {
			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
			return webElement;
		}
		
		if (visible) {
			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
			return webElement;
		}
		
		webElement = webDriver.findElement(By
				.xpath(elementXpath));
		
		return webElement;
	}
	
	
//	/**
//	 * XPATH Locator Helps to locate an element by three attribute parts of the element (@id, @class, @name, text() etc)
//	 * @param element The name of the element
//	 * @param webDriver WebDriver instance to use
//	 * @param attributeName  The name of the attribute in the following form: @id, @class, @name, text() 
//	 * @param attributeValue The part value of the attribute
// 	 * @param visible - means that element should be visible
//	 * @param clickable - means that element should be visible and clickable (enabled) 
//	 * @return webelement
//	 * @throws NoSuchElementException
//	 */
//	public static WebElement getElementByPartAttributeValue(WebDriver webDriver, String element, String attributeName1, String attributeValue1,
//			String attributeName2, String attributeValue2, String attributeName3, String attributeValue3, boolean visible, boolean clickable) throws NoSuchElementException {
//		
//		int timer = 0;
//		WebElement webElement;
//		try {timer = Configuration.getConfiguration(null).getImplicitWait();} catch (Exception e) {}
//
//		String elementXpath = "//"+element+"[contains("+attributeName1+",'" + attributeValue1 + "')"
//				+ "and contains("+attributeName2+ ",'" + attributeValue2 + "')" + 
//				"and contains("+attributeName3+ ",'" + attributeValue3 + "')" +
//				"]";
//		
//		if (clickable) {
//			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
//			return webElement;
//		}
//		
//		if (visible) {
//			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
//			return webElement;
//		}
//		
//		webElement = webDriver.findElement(By
//				.xpath(elementXpath));
//		
//		return webElement;	
//	}
	

//	/**
//	 * XPATH Locator Helps to locate an element by parent element and three parts of the attributes of the element (@id, @class, @name, text() etc)
//	 * @param element The name of the element
//	 * @param webDriver WebDriver instance to use
//	 * @param attributeName  The name of the attribute in the following form: @id, @class, @name, text() 
//	 * @param attributeValue The part value of the attribute
//	 * @param visible - means that element should be visible
//	 * @param clickable - means that element should be visible and clickable (enabled)
//	 * @return webelement
//	 * @throws NoSuchElementException
//	 */
//	public static WebElement getElementByPartAttributeValueAndParentElement(WebDriver webDriver, String parentElement, String parentAttributeName, 
//			String parentAttributeValue, String element, String attributeName1, String attributeValue1, String attributeName2, String attributeValue2,
//			String attributeName3, String attributeValue3, boolean visible, boolean clickable) throws NoSuchElementException {
//		
//		int timer = 0;
//		WebElement webElement;
//		try {timer = Configuration.getConfiguration(null).getImplicitWait();} catch (Exception e) {}
//
//		String elementXpath = "//"+parentElement+"[contains("+parentAttributeName+",'" + parentAttributeValue + "')]//"
//				+element+"[contains("+attributeName1+",'" + attributeValue1 + "')"
//				+ "and contains("+attributeName2+ ",'" + attributeValue2 + "')" + 
//				"and contains("+attributeName3+ ",'" + attributeValue3 + "')" +
//				"]"; 
//		
//		if (clickable) {
//			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
//			return webElement;
//		}
//		
//		if (visible) {
//			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
//			return webElement;
//		}
//		
//		webElement = webDriver.findElement(By
//				.xpath(elementXpath));
//		
//		return webElement;	
//	}

	/**
	 * XPATH Locator Helps to locate an element by parent element and two parts of the attributes of the element (@id, @class, @name, text() etc)
	 * @param element The name of the element
	 * @param webDriver WebDriver instance to use
	 * @param attributeName  The name of the attribute in the following form: @id, @class, @name, text() 
	 * @param attributeValue The part value of the attribute
 	 * @param visible - means that element should be visible
	 * @param clickable - means that element should be visible and clickable (enabled)
	 * @return webelement
	 * @throws NoSuchElementException
	 */
	public static WebElement getElementByPartAttributeValueAndParentElement(WebDriver webDriver, String parentElement, String parentAttributeName, 
			String parentAttributeValue, String element, String attributeName1, String attributeValue1, String attributeName2, String attributeValue2, 
			boolean visible, boolean clickable)
					throws NoSuchElementException {
		
		int timer = 0;
		WebElement webElement;
		try {timer = Configuration.getConfiguration(null).getImplicitWait();} catch (Exception e) {}

		String elementXpath = "//"+parentElement+"[contains("+parentAttributeName+",'" + parentAttributeValue + "')]//"
				+element+"[contains("+attributeName1+",'" + attributeValue1 + "')"
				+ "and contains("+attributeName2+ ",'" + attributeValue2 + "')" + 
				"]";
		
		if (clickable) {
			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
			return webElement;
		}
		
		if (visible) {
			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
			return webElement;
		}
		
		webElement = webDriver.findElement(By
				.xpath(elementXpath));
		
		return webElement;	
	}


	/**
	 * XPATH Locator Helps to locate an element by parent element and parts of the attributes of the element (@id, @class, @name, text() etc)
	 * @param element The name of the element
	 * @param webDriver WebDriver instance to use
	 * @param attributeName  The name of the attribute in the following form: @id, @class, @name, text() 
	 * @param attributeValue The part value of the attribute
	 * @param visible - means that element should be visible
	 * @param clickable - means that element should be visible and clickable (enabled)
	 * @return webelement
	 * @throws NoSuchElementException
	 */
	public static WebElement getElementByPartAttributeValueAndParentElement(WebDriver webDriver, String parentElement, String parentAttributeName, 
			String parentAttributeValue, String element, String attributeName1, String attributeValue1, boolean visible, boolean clickable)
					throws NoSuchElementException {
		
		int timer = 0;
		WebElement webElement;
		try {timer = Configuration.getConfiguration(null).getImplicitWait();} catch (Exception e) {}
		
		String elementXpath = "//"+parentElement+"[contains("+parentAttributeName+",'" + parentAttributeValue + "')]//"
				+element+"[contains("+attributeName1+",'" + attributeValue1 + "')"+ 
				"]";
		
		if (clickable) {
			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
			return webElement;
		}
		
		if (visible) {
			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
			return webElement;
		}
		
		webElement = webDriver.findElement(By
				.xpath(elementXpath));
		
		return webElement;	
	}

	/**
	 * XPATH Locator Helps to locate an element by parent element and attributes of the element (@id, @class, @name, text() etc)
	 * @param element The name of the element
	 * @param webDriver WebDriver instance to use
	 * @param attributeName  The name of the attribute in the following form: @id, @class, @name, text() 
	 * @param attributeValue The value of the attribute
 	 * @param visible - means that element should be visible
	 * @param clickable - means that element should be visible and clickable (enabled)
	 * @return webelement
	 * @throws NoSuchElementException
	 */
	public static WebElement getElementByAttributeValueAndParentElement(WebDriver webDriver, String parentElement, String parentAttributeName, 
			String parentAttributeValue, String element, String attributeName1, String attributeValue1, boolean visible, boolean clickable)
					throws NoSuchElementException {
		
		int timer = 0;
		WebElement webElement;
		try {timer = Configuration.getConfiguration(null).getImplicitWait();} catch (Exception e) {}
		
		String elementXpath = "//"+parentElement+"[contains("+parentAttributeName+",'" + parentAttributeValue + "')]//"
				+element+"["+attributeName1+"='" + attributeValue1 + "'"+ 
				"]";

		if (clickable) {
			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
			return webElement;
		}
		
		if (visible) {
			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
			return webElement;
		}
		
		webElement = webDriver.findElement(By
				.xpath(elementXpath));
		
		return webElement;	
	}
	
	
	public static WebElement getLastElementByAttributeValueAndParentElement(WebDriver webDriver, String parentElement, String parentAttributeName, 
			String parentAttributeValue, String element, String attributeName1, String attributeValue1, boolean visible, boolean clickable)
					throws NoSuchElementException {
		
		int timer = 0;
		WebElement webElement;
		try {timer = Configuration.getConfiguration(null).getImplicitWait();} catch (Exception e) {}
		
		String elementXpath = "(//"+parentElement+"[contains("+parentAttributeName+",'" + parentAttributeValue + "')]//"
				+element+"["+attributeName1+"='" + attributeValue1 + "'"+ 
				"])[last()]";

		if (clickable) {
			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
			return webElement;
		}
		
		if (visible) {
			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
			return webElement;
		}
		
		webElement = webDriver.findElement(By
				.xpath(elementXpath));
		
		return webElement;	
	}

	
	
	/**
	 * XPATH Locator Helps to locate an element by parent element and two parts of the attributes of the element (@id, @class, @name, text() etc)
	 * @param element The name of the element
	 * @param webDriver WebDriver instance to use
	 * @param attributeName  The name of the attribute in the following form: @id, @class, @name, text() 
	 * @param attributeValue The part value of the attribute
 	 * @param visible - means that element should be visible
	 * @param clickable - means that element should be visible and clickable (enabled)
	 * @return webelement
	 * @throws NoSuchElementException
	 */
	public static WebElement getElementByAttributeValueAndParentElement(WebDriver webDriver, String parentElement, String parentAttributeName, 
			String parentAttributeValue, String element, String attributeName1, String attributeValue1, String attributeName2, String attributeValue2, 
			boolean visible, boolean clickable)
					throws NoSuchElementException {
		
		int timer = 0;
		WebElement webElement;
		try {timer = Configuration.getConfiguration(null).getImplicitWait();} catch (Exception e) {}

		String elementXpath = "//"+parentElement+"[contains("+parentAttributeName+",'" + parentAttributeValue + "')]//"
				+element+"[contains("+attributeName1+",'" + attributeValue1 + "')"
				+ " and " +attributeName2+ "='" + attributeValue2 + "'" + 
				"]";
		
		if (clickable) {
			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
			return webElement;
		}
		
		if (visible) {
			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
			return webElement;
		}
		
		webElement = webDriver.findElement(By
				.xpath(elementXpath));
		
		return webElement;	
	}
	
	
//	/**
//	 * XPATH Locator Helps to locate an element by parent web element and parts of the attributes of the element (@id, @class, @name, text() etc)
//	 * @param element The name of the element
//	 * @param parentElement Parent webElement instance to use
//	 * @param attributeName  The name of the attribute in the following form: @id, @class, @name, text() 
// 	 * @param visible - means that element should be visible
//	 * @param clickable - means that element should be visible and clickable (enabled)
//	 * @param attributeValue The part value of the attribute
//	 * @return webelement
//	 * @throws NoSuchElementException
//	 */
//	public static WebElement getElementByPartAttributeValueAndParentElement(WebDriver webDriver, WebElement parentElement, 
//			String parentAttributeValue, String element, String attributeName1, String attributeValue1, boolean visible, boolean clickable)
//					throws NoSuchElementException {
//		int timer = 0;
//		WebElement webElement;
//		try {timer = Configuration.getConfiguration(null).getImplicitWait();} catch (Exception e) {}
//		
//		String elementXpath =".//"	+element+"[contains("+attributeName1+",'" + attributeValue1 + "')]";
//		
//		if (clickable) {
//			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
//			return webElement;
//		}
//		
//		if (visible) {
//			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
//			return webElement;
//		}
//		
//		webElement = webDriver.findElement(By
//				.xpath(elementXpath));
//		
//		return webElement;	
//	}
	
	/**
	 * XPATH Locator  Helps to locate an element by XPATH
	 * @param webDriver WebDriver instance to use
	 * @param elementXpath The HTML Xpath of the element
	 * @return webElement
	 * @throws NoSuchElementException
	 */
	public static WebElement getElementByXpath(WebDriver webDriver, String elementXpath) throws NoSuchElementException {	
		int timer = 0;
		try {timer = Configuration.getConfiguration(null).getImplicitWait();} catch (Exception e) {}
		WebElement webElement = webDriver.findElement(By.xpath(elementXpath));
		new WebDriverWait(webDriver, timer) 	
		.until(ExpectedConditions.visibilityOf(webElement));
		return webElement;	
	}
	

	/**
	 * XPATH Locator  Helps to reveal if element located by XPATH is not present
	 * @param webDriver WebDriver instance to use
	 * @param elementXpath The HTML Xpath of the element
	 * @return true if element is not present (otherwise false)
	 */

	public static boolean isElementAbsent(WebDriver webDriver, String xpath) {
		try {
			webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			webDriver.findElement(By.xpath(xpath));
			return false;
		} catch (Exception e) {
			return true;
		} finally {
			try {webDriver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
		}
		
	}
	
	/**
	 * XPATH Locator  Helps to reveal if element located by XPATH is present
	 * @param webDriver WebDriver instance to use
	 * @param elementXpath The HTML Xpath of the element
	 * @return true if element is present (otherwise false)
	 */

	public static boolean isElementPresent(WebDriver webDriver, String xpath) {
		try {
			webDriver.findElement(By.xpath(xpath));
			return true;
		} catch (Exception e) {
			return false;
		} 
	}
	
	/**
	 * XPATH Locator  Helps to reveal if element is located by XPATH and is displayed
	 * @param webDriver WebDriver instance to use
	 * @param elementXpath The HTML Xpath of the element
	 * @return true if element is present and displayed (otherwise false)
	 */
	
	public static boolean isElementDisplayed(WebDriver webDriver, String xpath) {
		try {
			if (webDriver.findElement(By.xpath(xpath)).isDisplayed())
				return true;
			else
				return false;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	
	/**
	 * XPATH Locator Helps to locate last element by parent element and two parts of the attributes of the element (@id, @class, @name, text() etc)
	 * @param element The name of the element
	 * @param webDriver WebDriver instance to use
	 * @param attributeName  The name of the attribute in the following form: @id, @class, @name, text() 
	 * @param attributeValue The part value of the attribute
 	 * @param visible - means that element should be visible
	 * @param clickable - means that element should be visible and clickable (enabled)
	 * @return webelement
	 * @throws NoSuchElementException
	 */
	public static WebElement getLastElementByPartAttributeValueAndParentElement(WebDriver webDriver, String parentElement, String parentAttributeName, 
			String parentAttributeValue, String element, String attributeName1, String attributeValue1, String attributeName2, String attributeValue2, 
			boolean visible, boolean clickable)
					throws NoSuchElementException {
		
		int timer = 0;
		WebElement webElement;
		try {timer = Configuration.getConfiguration(null).getImplicitWait();} catch (Exception e) {}

		String elementXpath = "(//"+parentElement+"[contains("+parentAttributeName+",'" + parentAttributeValue + "')]//"
				+element+"[contains("+attributeName1+",'" + attributeValue1 + "')"
				+ "and contains("+attributeName2+ ",'" + attributeValue2 + "')" + 
				"])[last()]";
		
		if (clickable) {
			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
			return webElement;
		}
		
		if (visible) {
			webElement = new WebDriverWait(webDriver, timer).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
			return webElement;
		}
		
		webElement = webDriver.findElement(By
				.xpath(elementXpath));
		
		return webElement;	
	}

	

	/**
	 * Method allows to check if field is mandatory
	 * @param webDriver WebDriver instance to use
	 * @param className class of the container
	 * @return webElement
	 * @throws NoSuchElementException
	 */
	public static boolean isFieldMandatory(WebDriver webDriver, String className, String name) {
		webDriver.findElement(By.xpath("//div[contains(@class,'"+className+"')]//label/..//input[@name='"+name+"']"
									+ "|"
									+ "//div[contains(@class,'"+className+"')]//label/..//textarea[@name='"+name+"']"));
		try {
			webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			webDriver.findElement(By.xpath("//div[contains(@class,'"+className+"')]//label[contains(@class,'x-form-item-label-mandatory')]/..//input[@name='"+name+"']"
										+ "|"
										+ "//div[contains(@class,'"+className+"')]//label[contains(@class,'x-form-item-label-mandatory')]/..//textarea[@name='"+name+"']"));
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			try {webDriver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
		}
		
	}	
	

	/**
	 * Method allows to check if red border near invalid input field is present
	 * @param webDriver WebDriver instance to use
	 * @param className class of the container
	 * @return webElement
	 * @throws NoSuchElementException
	 */
	public static boolean checkInvalidRedBorderInputField(WebDriver webDriver,
			String className,  String name) {
		if (webDriver.findElement(By.xpath("(//div[contains(@class,'"+className+"')]//label/..//input[@name='"+name+"']/..//input)[last()]"))
				.getAttribute("class").contains("x-form-invalid")) {
			return true;
		} else {
			return false;
		}
		
	}	
	
	/**
	 * Helper method to check if Red Border near Input field is present when Input name is Not available/Dynamic
	 * @param webDriver
	 * @param className
	 * @param name
	 * @return
	 */
	public static boolean checkInvalidRedBorderInputFieldWOName(WebDriver webDriver,
			String className, String labelName) {
		if (webDriver.findElement(By.xpath("(//div[contains(@class,'"+className+"')]//div[contains(@class, 'x-form-item')]//label[contains(text(), '"+labelName+"')]//..//input)[last()]"))
				.getAttribute("class").contains("x-form-invalid")) {
			return true;
		} else {
			return false;
		}
		
	}	
	
	/**
	 * Method allows to check if infobar error message is present on dialog
	 * @param webDriver WebDriver instance to use
	 * @param className class of the container
	 * @return true if error message is present
	 */
	public static boolean checkMessageInvalidForm(WebDriver webDriver, String className) {
			try {
				webDriver.findElement(
						By.xpath("//div[contains(@class,'"+className+"')]//div[contains(@class,'infobar bg-error')]"));
				return true;
			} catch (Exception e) {
				return false;
			}
	}
	
	public static boolean checkMessageInvalidForm(WebDriver webDriver, String className, List<String> keyWordsList) {
			String errorMessage = null;
			
			try {
				errorMessage = webDriver.findElement(
						By.xpath("//div[contains(@class,'"+className+"')]//div[contains(@class,'infobar bg-error')]")).getText();
			} catch (Exception e) {
				Reporter.log("Can't find error message bar", true);
				return false;
			}
			for(String keyWord : keyWordsList) {
				if(!errorMessage.contains(keyWord)) {
					Reporter.log(keyWord+" - key word was not found in error message text", true);
					return false;
				}
				else 
					Reporter.log(keyWord+" - key word was found in error message text", true);
			}
			return true;
	}

	 
	/**
	* Method allows to check if input element is disabled/read-only on dialog
	* @param webDriver WebDriver instance to use
	* @param className class of the element
	* @param elementName name of the element
	* @return true if error message is present
	*/
	public static boolean checkInputDisabled(WebDriver webDriver, String propertyName, String propertyValue, String elementName) {
		webDriver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
		try {
			String parent = "//div[contains(" + propertyName + ", '" + propertyValue + "')]";
			String input0 = "//input[@name='" + elementName + "']/../input[contains(@class,'x-form-readonly')]";
			String input1 = "//input[contains(@class,'x-form-readonly') and @name='" + elementName + "']";
			String input2 = "//input[contains(@class,'x-item-disabled') and @name='" + elementName + "']";
			String input3 = "//input[@name='" + elementName + "']/..)[contains(@class,'x-item-disabled')]";
			String xPath = parent+input0+"|"+parent + input1 + "|" + parent + input2 + "|" +"("+parent + input3;
			Reporter.log("Checking if element is disabled: " + elementName);
			webDriver.findElement(By.xpath(xPath));
			return true;
		} catch (Exception e) {
			return false;
		}
		finally{
			try {webDriver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
		}		
	}
	
	/**
	* Method allows to get field value based on parent Add/Edit form class (from input or textarea)
	* @param webDriver WebDriver instance to use
	* @param dialogClass class of the Add/Edit dialog
	* @param fieldName name attribute of the field
	* @return returns field value
	*/
	public static String getFieldValue(WebDriver webDriver, String dialogClass, String fieldName) {
		return webDriver
				.findElement(
						By.xpath("(//div[contains(@class,'"+dialogClass+"')]//input[@name='"+fieldName+"']/../input)[last()]"
								+ " | "
								+ "//div[contains(@class,'"+dialogClass+"')]//textarea[@name='"+fieldName+"']"))
				.getAttribute("value");
	}
	
	/**
	* Method allows to clear field value based on parent Add/Edit form class (for input or textarea)
	* @param webDriver WebDriver instance to use
	* @param dialogClass class of the Add/Edit dialog
	* @param fieldName name attribute of the field
	*/
	public static void clearField(WebDriver webDriver, String dialogClass, String fieldName) {
		WebElement field =  webDriver.findElement(
				By.xpath("(//div[contains(@class,'"+dialogClass+"')]//input[@name='"+fieldName+"']/../input)[last()]"
						+ " | "
						+ "//div[contains(@class,'"+dialogClass+"')]//textarea[@name='"+fieldName+"']"));
		field.click();
		field.clear();
		Reporter.log("Clear "+fieldName+" field", true);
	}
	
}
