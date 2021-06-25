package test.framework.webelement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import test.configuration.Configuration;
import test.framework.Timer;

public class FileUploader extends AbstractWebElement {

	/**
	 * Constructs the instance using provided WebDriver and WebElement
	 * 
	 * @param webDriver
	 * @param webElement
	 */
	public FileUploader(WebDriver webDriver, WebElement webElement) {
		super(webDriver, webElement);
	}

	
	/**
	 * method allows to upload file
	 * 
	 * @param windowParentAttr  - attribute of the window with file upload 
	 * 
	 * @param windowParentValue - attribute value of the window with file upload
	 * 
	 * @param fileName - name of file to be uploaded 
	 */
	
	public static void uploadFileName(WebDriver webDriver,
			String windowParentAttr, String windowParentValue,
			String fileName) {
		
		String filePath = null;
		
		try {
			filePath = Configuration.getConfiguration(null).getTestDataLocation()+fileName;
		} catch (Exception e) {}

		webDriver.findElement(
				By.xpath("//div[contains(" + windowParentAttr + ",'"
		+ windowParentValue + "')]//input[@type='file']"))
				.sendKeys(filePath);
		try {Thread.sleep(500);} catch (InterruptedException e) {}
	}


	
	/**
	 * method allows to upload file
	 * 
	 * @param windowParentAttr  - attribute of the window with file upload 
	 * 
	 * @param windowParentValue - attribute value of the window with file upload
	 * 
	 * @param filePath - path of file to be uploaded
	 */

	
	
	public static void uploadFilePath(WebDriver webDriver,
			String windowParentAttr, String windowParentValue,
			String filePath) {

		webDriver.findElement(
				By.xpath("//div[contains(" + windowParentAttr + ",'"
						+ windowParentValue + "')]//input[@type='file']"))
				.sendKeys(filePath);

	}
	
	
	
	/**
	 * Method allows to wait for upload window to disappear
	 * 
 	 * @param windowParentAttr  - attribute of the window with file upload 
	 * 
	 * @param windowParentValue - attribute value of the window with file upload
	 * @throws Exception 
	 */
	public static void waitForUploadWindowDisappear(WebDriver webDriver,
			final String windowParentAttr, final String windowParentValue) throws Exception {
		Timer timer = new Timer().start();

		try {
			Thread.sleep(500);
			webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			new WebDriverWait(webDriver, 25, 250)
					.until(new ExpectedCondition<Boolean>() {
						@Override
						public Boolean apply(WebDriver webDriver) {
							try {
								List<WebElement> masks = webDriver.findElements(By
										.xpath("//div[contains(" + windowParentAttr + ",'"
												+ windowParentValue + "')]//input[@type='file']"));
								for (WebElement mask : masks) {
									if (mask.isDisplayed()) {
										return false;
									}
								}
								return true;
							} catch (StaleElementReferenceException e) {
								return false;
							}
						}
					});
		} catch (Exception e) {
		} finally {
			webDriver.manage()
					.timeouts()
					.implicitlyWait( Configuration.getConfiguration(null).getImplicitWait(),
							TimeUnit.SECONDS);

		}

		Reporter.log("Wait for upload window  to disappear" + " (" + timer.stop() + "ms)");
		System.out.println("Wait for upload window to disappear" + " (" + timer.stop()
				+ "ms)");
	}
	
	

	public static void waitForUploadWindowDisappear(WebDriver webDriver) throws Exception {
		Timer timer = new Timer().start();

		try {
			
			final String windowParentAttr = "@class";
			final String windowParentValue = "x-window";
			
			Thread.sleep(500);
			webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			new WebDriverWait(webDriver, 25, 250)
					.until(new ExpectedCondition<Boolean>() {
						@Override
						public Boolean apply(WebDriver webDriver) {
							try {
								List<WebElement> masks = webDriver.findElements(By
										.xpath("//div[contains(" + windowParentAttr + ",'"
												+ windowParentValue + "')]//input[@type='file']"));
								for (WebElement mask : masks) {
									if (mask.isDisplayed()) {
										return false;
									}
								}
								return true;
							} catch (StaleElementReferenceException e) {
								return false;
							}
						}
					});
		} catch (Exception e) {
		} finally {
			webDriver.manage()
					.timeouts()
					.implicitlyWait( Configuration.getConfiguration(null).getImplicitWait(),
							TimeUnit.SECONDS);

		}

		Reporter.log("Wait for upload window  to disappear" + " (" + timer.stop() + "ms)");
		System.out.println("Wait for upload window to disappear" + " (" + timer.stop()
				+ "ms)");
	}
	
	

}

