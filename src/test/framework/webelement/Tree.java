package test.framework.webelement;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import test.configuration.Configuration;

public class Tree extends AbstractWebElement {

	/**
	 * Constructs the instance using provided WebDriver and WebElement
	 * 
	 * @param webDriver
	 * @param webElement
	 */
	public Tree(WebDriver webDriver, WebElement webElement) {
		super(webDriver, webElement);

	}

	/**
	 * XPATH Locator Find and check the corresponding record in Tree by text
	 * value
	 * 
	 * @param webDriver
	 *            WebDriver instance to use
	 * @param textValue
	 *            for search
	 * @throws NoSuchElementException
	 * @return webelement
	 */
	public static WebElement checkNodeInNavigatorTreeByTextValue(WebDriver webDriver,
			String nodeText) {
		WebElement webElement = webDriver.findElement(By
				.xpath("//div[contains(@class, 'nrg-menu-navigator')]"
								+ "//div[contains(@class, 'x-tree-node-el')]"
								+ "//span[contains(text(), '"+nodeText+"')]"));
		webElement.click();
		Reporter.log("Select '"+nodeText+"' in Navigator", true);
		return webElement;
	}
	


	/**
	 * XPATH Locator Find and check the corresponding record in Tree by text
	 * value
	 * 
	 * @param webDriver
	 *            WebDriver instance to use
	 * @param textValue
	 *            for search
	 * @throws NoSuchElementException
	 * @return webelement
	 */
	public static WebElement clickNodeInNavigatorTreeByTextValue(WebDriver webDriver,
			String nodeText) {
		WebElement webElement = webDriver.findElement(By
				.xpath("//div[contains(@class, 'nrg-menu-navigator')]"
								+ "//div[contains(@class, 'x-tree-node-el')]"
								+ "//span[contains(text(), '"+nodeText+"')]/../../.."));
		webElement.click();
		Reporter.log("Select '"+nodeText+"' in Navigator", true);
		return webElement;
	}
	
	/**
	 * XPATH Locator Find and check the corresponding record in Tree by text
	 * value
	 * 
	 * @param webDriver
	 *            WebDriver instance to use
	 * @param textValue
	 *            for search
	 * @throws NoSuchElementException
	 * @return webelement
	 */
	public static WebElement checkNodeInTreeByTextValue(WebDriver webDriver,
			String nodeText) {
		WebElement webElement = webDriver.findElement(By
				.xpath("//div[contains(., '" + nodeText
						+ "') and contains(@class,'x-tree-node-el')]"));
		webElement.click();
		return webElement;
	}

	public static boolean isNodeInTreeAbsent(WebDriver webDriver, String nodeText) {
		try {
			webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			webDriver.findElement(By.xpath("//div[contains(@class,'x-tree-node-el')]//span[contains(text(), '"+nodeText+"')]"));
			return false;
		} catch (Exception e) {
			return true;
		} finally {
			try {
				webDriver.manage()
						.timeouts()
						.implicitlyWait(
								Configuration.getConfiguration(null)
										.getImplicitWait(), TimeUnit.SECONDS);
			} catch (Exception e) {
			}
		}

	}

	
	public static boolean isNodeInTreePresent(WebDriver webDriver, String parentAttribute, String parentValue, String nodeText) {
		try {
			webDriver.findElement(By.xpath("//div[contains("+parentAttribute+",'"+parentValue+"')]//div[contains(@class,'x-tree-node-el')]//span[contains(text(), '"+nodeText+"')]"));
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	
	public static boolean isNodeInTreePresent(WebDriver webDriver, String nodeText) {
		try {
			webDriver.findElement(By.xpath("//div[contains(@class,'x-tree-node-el')]//span[contains(text(), '"+nodeText+"')]"));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	
	/**
	 * XPATH based locator Method allows to expand Navigator tree
	 * 
	 * @param webDriver
	 *            WebDriver instance to use
	 * @param nodeText
	 *            The text of the node
	 */
	public static void expandNavigatorTreeNode(WebDriver webDriver, final String nodeText) {
		String nodeSpan = "//div[contains(@class, 'nrg-menu-navigator')]"
							+ "//div[contains(@class, 'x-tree-node-el')]"
							+ "//span[contains(text(), '"+nodeText+"')]";
		
		if (webDriver.findElement(By.xpath(nodeSpan + "//..//..")).getAttribute("class").contains("collapsed")) {
			webDriver
			.findElement(
					By.xpath(nodeSpan + "//..//..//img[contains(@class,'plus')]")).click();
			Reporter.log("Expand '"+nodeText+"' in Navigator", true);

			int time = 0;
			try {
				time = Configuration.getConfiguration(null).getImplicitWait();
			} catch (Exception e) {
			}

			new WebDriverWait(webDriver, time, 250)
				.until(new ExpectedCondition<Boolean>() {
					@Override
					public Boolean apply(WebDriver webDriver) {

						WebElement node = webDriver.findElement(By
								.xpath("//div[contains(@class, 'x-tree-root-node')]"
										+ "//div[contains(@class, 'x-tree-node-el')]"
										+ "//span[contains(text(), '"+nodeText+"')]//..//.."));

						if (node.getAttribute("class").contains("collapsed")) {
							return false;
						} else {
							return true;
						}

					}
				});
		}
	}

}
