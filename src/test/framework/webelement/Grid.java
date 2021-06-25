package test.framework.webelement;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.configuration.Configuration;



public class Grid extends AbstractWebElement{
	
	/**
	 * Constructs the instance using provided WebDriver and WebElement
	 * @param webDriver
	 * @param webElement
	 */
	public Grid(WebDriver webDriver, WebElement webElement) {
		super(webDriver, webElement);
	}

	
	/**
	 * METHOD FOR GRIDS WITH CHECKBOX WHICH CAN BE CHECKED/UNCHEKED
	 * 
	 * METHOD PERFORMS SMART CHECKING: IF CHECKED THEN CHECK IS NOT PERFORMED
	 * 
	 * Find checkbox of the corresponding record in Grid by text value and check this row
	 * @param webDriver WebDriver instance to use
	 * @param textValue for search
	 * @throws NoSuchElementException
	 * @return webelement
	 */
	public static WebElement checkRowInGriByTextValueExactMatch(WebDriver webDriver, String textValue)  {
		WebElement webElement = null;
		try {
			webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			webDriver.findElement(By.xpath("(//div[@class='x-grid3']//div[text()='"+textValue+"']/../../../../..)[contains(@class,'selec')]"));
		}
		catch(Exception e)
		{		
		try {webDriver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e0) {}
		webElement = webDriver.findElement(By.xpath("//*[@class='x-grid3']//div[text()='"+textValue+"']//..//..//div[contains(@class,'check')  and not(contains(@class,'inner'))]"));
		webElement.click();}
		finally {
			try {webDriver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
		}
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	
	/**
	 * METHOD FOR GRIDS WITH CHECKBOX WHICH CAN BE CHECKED/UNCHEKED
	 * 
	 * METHOD PERFORMS SMART CHECKING: IF CHECKED THEN CHECK IS NOT PERFORMED
	 * 
	 * Find checkbox of the corresponding record in Grid by text value and check this row
	 * @param webDriver WebDriver instance to use
	 * @param textValue for search
	 * @throws NoSuchElementException
	 * @return webelement
	 */
	public static WebElement checkRowInGriByTextValue(WebDriver webDriver, String textValue)  {
		WebElement webElement = null;
		try {
			webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			webDriver.findElement(By.xpath("(//div[@class='x-grid3']"+xpathGeneratorForTextElement(textValue)+"/../../../../..)[contains(@class,'selec')]"));
		}
		catch(Exception e)
		{		
		try {webDriver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e0) {}
		webElement = webDriver.findElement(By.xpath("//*[@class='x-grid3']"+xpathGeneratorForTextElement(textValue)+"//..//..//div[contains(@class,'check')  and not(contains(@class,'inner'))]"));
		webElement.click();}
		finally {
			try {webDriver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
		}
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	
	
	public static boolean isRowInGridAbsent(WebDriver webDriver, String textValue) {
		try {
			String xpath = "//*[@class='x-grid3']"+xpathGeneratorForTextElement(textValue);
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
	 * Method allows to check if row with one or some values is present in grid
	 * example: textValue = "reference1|code2"  (values in grid to search should be separated by "|")
	 * @param webDriver WebDriver instance to use
	 * @param textValue for search
	 * @throws NoSuchElementException
	 * @return true if row is present
	 */
	public static boolean isRowInGridPresent(WebDriver webDriver, String textValue, String parentAttr, String parentAttrValue) {
		
		String[] splitted = null;
		
		if (textValue.contains("|")) {
			splitted = textValue.split("\\|");
		} else {
			splitted = new String[] {textValue};
		}
			
		String xpath= "//div[contains(" +parentAttr +",'"+parentAttrValue+"')]//tr"+ xpathGeneratorForTextElement(splitted[0]);
		//"//div[text()='"+splitted[0]+"']";
		
		for (int i=1; i<splitted.length; i++){
			xpath = xpath + "//ancestor::tr"+xpathGeneratorForTextElement(splitted[i]);
			//div[text()='"+splitted[i]+"']";
		}
		
		
		try {
			webDriver.findElement(By.xpath(xpath));
		} catch (Exception e) {
			Reporter.log("row can not be found in grid: " +xpath,true);
			return false;
		}
		
		Reporter.log("Row with text " + textValue  + " was successfully found", true);

		return true;
	}

	
	/**
	 * Method allows to check if row with one or some values is present in grid using like criterium
	 * example: textValue = "reference1|code2"  (values in grid to search should be separated by "|")
	 * @param webDriver WebDriver instance to use
	 * @param textValue for search
	 * @throws NoSuchElementException
	 * @return true if row is present
	 */
	public static boolean isRowInGridPresentLike(WebDriver webDriver, String textValue, String parentAttr, String parentAttrValue) {
		
		String[] splitted = null;
		
		if (textValue.contains("|")) {
			splitted = textValue.split("\\|");
		} else {
			splitted = new String[] {textValue};
		}
			
		String xpath= "//div[contains(" +parentAttr +",'"+parentAttrValue+"')]//tr"+ xpathGeneratorForTextElement(splitted[0]);
		
		for (int i=1; i<splitted.length; i++){
			xpath = xpath + "//ancestor::tr"+xpathGeneratorForTextElement(splitted[i]);
					//div[contains(text(),'"+splitted[i]+"')]";
		}
		
		
		try {
			webDriver.findElement(By.xpath(xpath));
		} catch (Exception e) {
			Reporter.log("row can not be found in grid: " +xpath,true);
			return false;
		}
		
		Reporter.log("Row with text like " + textValue  + " was successfully found", true);

		return true;
	}

	
	/**
	 * 
	 * Click on the record in Grid by text value (when there is no class or id of the grid)
	 *  
	 * @param webDriver WebDriver instance to use
	 * @param textValue for search
	 * @throws NoSuchElementException
	 * @return webelement
	 */
	public static WebElement checkRowInGriByTextValueAndClick(WebDriver webDriver, String textValue)  {
		String xpath = "//*[@class='x-grid3']"+ xpathGeneratorForTextElement(textValue);
		WebElement webElement = webDriver.findElement(By.xpath(xpath));
		webElement.click();
		Reporter.log("Check element "+textValue+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	

	/**
	 * 
	 * Click on the record in Grid by text value (when there is a class of the grid)
	 *  
	 * @param webDriver WebDriver instance to use
	 * @param textValue for search
	 * @throws NoSuchElementException
	 * @return webelement
	 */
	public static WebElement checkRowInGriByTextValueAndClick(WebDriver webDriver, String dialogClass, String textValue)  {	
		WebElement webElement = webDriver.findElement(By.xpath("//div[contains(@class,'"+dialogClass+"')]//*[@class='x-grid3']"+xpathGeneratorForTextElement(textValue)));
		webElement.click();
		Reporter.log("Check element "+textValue+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	
	/**
	 * Helper method to check row in grid by text value(Common functions in Grid.Java are not working)
	 * @param webDriver
	 * @param textValue
	 * @return
	 */
	public static WebElement checkRowInGriByTextValueAndClickCustomized(WebDriver webDriver, String textValue)  {
		WebElement webElement = webDriver.findElement(By.xpath("//*[@class='x-grid3']//td[contains(@class, 'x-grid3-cell-selectable') and not (contains(@style, 'none'))]"+xpathGeneratorForTextElement(textValue)));
		webElement.click();
		Reporter.log("Check element "+textValue+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}
	
	/**
	 * 
	 * Click on the record in Grid by text value (when there is a parent attribute @class or @id of the grid)
	 *  
	 * @param webDriver WebDriver instance to use
	 * @param textValue for search
	 * @throws NoSuchElementException
	 * @return webelement
	 */
	public static WebElement checkRowInGriByTextValueAndClick(WebDriver webDriver, String parentAttr, String parentAttrVal, String textValue)  {	
		String xpath = "//div[contains("+parentAttr+",'"+parentAttrVal+"')]//*[@class='x-grid3']"+xpathGeneratorForTextElement(textValue);
		WebElement webElement = webDriver.findElement(By.xpath(xpath));
		webElement.click();
		Reporter.log("Check element "+textValue+" present in grid and click", true);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
		return webElement;
	}

	
	
	
	
	public static boolean isRowInGridAbsent(WebDriver webDriver, String textValue, String parentAttrValue, String parentAttr) {
		try {
			webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			webDriver.findElement(By.xpath("//div[contains(" +parentAttr +",'"+parentAttrValue+"')]//*[@class='x-grid3']"+xpathGeneratorForTextElement(textValue)));
			return false;
		} catch (Exception e) {
			return true;
		} finally {
			try {webDriver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
		}

	}

	public static String xpathGeneratorForTextElement(String text){

		String subStrings[] = text.split(" ");

		String xpath = "//div[starts-with(text(),'"+subStrings[0]+"')";

		for(int i=1; i<subStrings.length; i++){

			xpath+="and contains(text(),'"+subStrings[i]+"')";
		}

		return xpath+"]";

	}

	/**
	 * Helper method to check whether the row is selected or not
	 * @param webDriver WebDriver instance to use
	 * @param textValue for search
	 * @return true if row is checked
	 */
	public static boolean isRowInGridChecked(WebDriver webDriver, String textValue){
		return McsElement.isElementDisplayed(webDriver , "//div[@class='x-grid3-body']//div[contains(@class,'x-grid3-row-selected') and contains(.,'"+textValue+"')]");
	}
}
