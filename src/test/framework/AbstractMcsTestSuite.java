// =============================================================================
//  
// Description   : 	Main class for all functionality, contain common methods (login, logout)
//

package test.framework;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import test.configuration.Configuration;
import test.framework.webelement.McsElement;

public class AbstractMcsTestSuite extends AbstractTestSuite {

	/* List of main menu id's or classes */

	protected final String LOGIN_INPUT_ID = "//input[@name='loginID']";
	protected final String PASSWORD_INPUT_ID = "//input[@name='password']";
	protected final String LOGIN_BUTTON_CLASS = "//*[contains(@class,'slnmLoginButton')]//button";
	protected final String LOGOUT_BUTTON_CLASS = " x-btn-text icon-logout";
	protected final String PORTAL_NAVIGATION_TREE_ID = "portalcontainer_navigationtree";
	protected final String PORTAL_TABS_ID = "portalcontainer_tabs";
	protected final String ADMINISTRATION_NAVIGATION_TREE = "admsettings-modulesettings";
	protected final String PORTAL_NAVIGATION = "portalcontainer";
	protected final String DEFAULT_CHANGE_VISIBLE_COLUMNS_HEADER = "Change visible columns";
	
	//	protected final String NAME = "AUTO";
//	protected final String NAME = "aqa_selenium";
	protected String NAME = null;
//	protected final String PASSWORD = "AUTO";
//	protected final String PASSWORD = "";
//	protected final String PASSWORD = "qwerty";
	protected String PASSWORD = null;

	
	protected final String XPATH_WORKORDERS = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'Work Order')]";
	
	protected final String XPATH_SLA = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'SLA Management')]";
	
	protected final String XPATH_ENERGY = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'Energy')]";

	protected final String XPATH_WAREHOUSES = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'Warehouses')]";


	protected final String XPATH_TIMEREGISTRATION = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'Time')]";

	protected final String XPATH_PURCHASINGPRODUCTS = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'Purchasing Products')]";


	protected final String XPATH_ANNOUNCEMENT = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'New Announcement')]";


	protected final String XPATH_RECEPTIONDESK = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[text()='Reception']";


	protected final String XPATH_NEWRESERVATIONS = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'New Reservation')]";

	protected final String XPATH_MYACCOUNT = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'My Account')]";

	protected final String XPATH_MYRESERVATIONS = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'My Reservations')]";

	protected final String XPATH_CALENDAR = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'Calendar')]";

	
	protected final String XPATH_NEWCALL = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'New Call')]";


	protected final String XPATH_MYCALLS = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'My Calls')]";
	
	
	protected final String XPATH_BACKOFFICE = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'Backoffice')]";
	
	protected final String XPATH_NEWMOVE = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'New Move')]";

	
	protected final String XPATH_NEWMOVEPROJECT = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'New Move Project')]";

	protected final String XPATH_MOVEPROJECTBACKOFFICE = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//a[contains(@href,'MOVE-ACTIVATE')]//span[contains(text(),'Backoffice')]";
	
	
	protected final String XPATH_MOVEPROJECT = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//a[contains(@href,'MOVE-ACTIVATE')]//span[contains(text(),'Move Project')]";
	
	protected final String XPATH_AMINISTRATION_MODULE_SETTINGS = "//*[contains(@class, '"
			+ ADMINISTRATION_NAVIGATION_TREE
			+ "')]//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'Module Settings')]";	
	
	protected final String XPATH_ADMINISTRATION = "//*[@id='"
			+ PORTAL_NAVIGATION
			+ "']//div[contains(@class,'x-toolbar x-toolbar-alternate')]//button[contains(text(),'Administration')]";
	
	protected final String XPATH_MasterData = "//*[contains(@class, '"
			+ ADMINISTRATION_NAVIGATION_TREE
			+ "')]//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'Master Data')]";	
	
	protected final String XPATH_Organizations = "//*[contains(@class, 'x-tree-node-ct')]//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'Organizations')]";
	
	protected final String XPATH_TRANSACTIONS= "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'Transactions')]";
	
	protected final String XPATH_STOCK_ITEMS= "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'Stock Items')]";

	protected final String XPATH_PURCHASE_ORDERS= "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'Purchase Orders')]";
	
	protected final String XPATH_PURCHASE_REQUISITIONS= "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'Purchase Requisitions')]";
	
	protected final String XPATH_GOODS_RECEIPT= "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'Goods Receipts')]";
	
	protected final String XPATH_ENERGY_QUICKLINKS = "//div[@class='outercontainer']//div[contains(@id, 'itemscontainer')]//td//a[contains(text(), 'Energy')]";
	
	protected final String XPATH_CONSUMPTIONS = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'Consumptions')]";
	
	protected final String XPATH_Today = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'Today')]";
	
	protected final String XPATH_SERVICE_CENTER = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'Service Center')]";
	
	protected final String XPATH_SCHEDULER = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'Scheduler')]";
	
	protected final String XPATH_INSPECTION = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[text()='Inspections']";
	
	protected final String XPATH_NEWINSPECTION = "//*[@id='"
			+ PORTAL_NAVIGATION_TREE_ID
			+ "']//div[contains(@class,'x-tree-node-el')]//span[contains(text(),'New Inspection')]";

	/**
	 * Login to application
	 */
	@BeforeTest(dependsOnMethods = { "setUp" })
	public void login() {
		try{
		NAME = configuration.getUserName();
		PASSWORD = configuration.getPassword();
		if ("safari".equals(configuration.getBrowser())) {
		} else {
			driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		}
		
		try{
		driver.navigate().to(configuration.getApplicationUrl());
		waitForExtJSAjaxComplete(25);
		driver.manage().deleteAllCookies();
		waitForExtJSAjaxComplete(25);
		driver.navigate().refresh();
		login(NAME, PASSWORD);
		}catch(Exception e){
			
			Reporter.log("Unable to login", true);
			Reporter.log(e.getMessage(), true);
			e.printStackTrace();
			
		}
		InetAddress ip=null;
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		}
		Reporter.log("Login to "+configuration.getApplicationUrl()+ " under account "+NAME +" on machine"+ip.toString()+" END\n", true);
		Reporter.log("<br />");
		}
		catch(Exception e){
			e.printStackTrace();	
		}
	}

	/**
	 * Navigate to home page
	 */
	@BeforeMethod
	public void navigateToMainPage() {
		
		try {Thread.sleep(3000);} catch (InterruptedException e) {}
		Timer timer = new Timer().start();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Before test method START", true);
		try{
		driver.get(configuration.getApplicationUrl()); //+"?aqa"
		}catch(Exception e){
			
			Reporter.log("Issue in navigateToMainPage(): ", true);
			
			Reporter.log(e.getMessage(), true);
			
			
		}
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Navigate to main page\n", true);
		Reporter.log("Before test method END"+ " (" + timer.stop() + "ms)\n", true);
		Reporter.log("<br />");
	}

	/**
	 * method allow to rerun webdriver if something was wrong with tests
	 */
	@AfterMethod(dependsOnMethods = { "getAutoErrorScreenshot" })//, alwaysRun = true)
	public void tearDownMethod(ITestResult result) throws Exception {
		try{
		if (!result.isSuccess()) {
                        try {                        
		   	driver.quit();
			Reporter.log("browser died",true);
			} catch (Exception e) {	} 
			Thread.sleep(5000);
			
			driver = WebDriverFactory.getWebDriver(configuration);
//			selenium = new WebDriverBackedSelenium(driver,
//					configuration.getApplicationUrl());
			Reporter.log(
					"<div style='font-weight:normal;color:red;background-color:#ff99ff'>"
							+ "FAILED</div><br>\n", true);
			Reporter.log("new browser born",true);
			login();
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

        public void clickXPath(String xpath) {
                new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		McsElement.getElementByXpath(driver,xpath).click();
        }
	/**
	 * Method allows to login
	 * 
	 * @param username
	 *            , password
	 * 
	 */
	protected void login(String username, String password) {
		Timer timer = new Timer().start();
		try{waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);
			waitForElementDisappear("//div[@id='loading-mask']");}
		catch(Exception e){}
		WebElement input = McsElement.getElementByXpath(driver, LOGIN_INPUT_ID);
		input.clear();
		
		if (username != null && !username.isEmpty()) {
			input.sendKeys(username);	 
			}
		
		
		input = McsElement.getElementByXpath(driver, PASSWORD_INPUT_ID);
		input.clear();
		
		if (password != null && !password.isEmpty()) {
			input.sendKeys(password);	
			}
		
		
		WebElement button = McsElement.getElementByXpath(driver, LOGIN_BUTTON_CLASS);
		button.click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Fill out login form and submit " + " (" + timer.stop()
				+ "ms)", true);
	}

	/**
	 * Method allows to log out (should not be used in IE8, because some unknown
	 * issue is present after logout - 1 item remaining message in browser if images are available)
	 */
	protected void logout() {
		Timer timer = new Timer().start();
		Reporter.log("Open account menu", true);
		new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'ext-el-mask')]")));
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='top-account-menu']//button")));
		driver.findElement(By.xpath("//table[@id='top-account-menu']//button")).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Click Logout", true);
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Logout']")));
		driver.findElement(By.xpath("//span[text()='Logout']")).click();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Logout from app " + " (" + timer.stop() + "ms)", true);
	}

	/**
	 * Method allows to force log out
	 */
	protected void forceLogout() {
		Timer timer = new Timer().start();
		driver.manage().deleteAllCookies();
		driver.navigate().to(configuration.getApplicationUrl());
		driver.navigate().refresh();
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		Reporter.log("Logout from app " + " (" + timer.stop() + "ms)", true);
	}

	/**
	 * Method allows to wait for WebElement to be visible
	 * 
	 * @param element
	 *            - webelement
	 */
	public void waitWebElement(WebElement element) {
		new WebDriverWait(driver, configuration.getImplicitWait())
				.until(ExpectedConditions.visibilityOf(element));

	}
	
        public void waitXPathVisible(String xpath) {
		new WebDriverWait(driver, configuration.getImplicitWait())
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

	}

	/**
	 * Navigate to certain page of the application: http://<HOST>/<PORTAL>/url&aqa
	 * for example, url=index.php?mode=DESKTOP&relay=MCS_PORTAL_RS-ACTIVATE[MY_RESERVATIONS]
	 * &aqa should be passed to enable wait for ajax requests function
	 */	
	public void navigateToPage(String url) {
		driver.navigate().to(getURLWithoutAQAParam() + url);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
	}
	


	/**
	 * Method allows to wait for WebElement to be visible and enabled to click on it
	 * 
	 * @param elementXpath of the element
	 */
	public void waitWebElementClickable(String elementXpath) {
		new WebDriverWait(driver, configuration.getImplicitWait())
				.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));

	}
	

	/**
	 * Method allows to wait while ExtJS mask disappear
	 */
	public void waitForMaskDisappear() {
		Timer timer = new Timer().start();
		waitForExtJSAjaxComplete(5);
		try{
                new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'ext-el-mask')]")));
		waitForExtJSAjaxComplete(5);
		}
		catch(Exception e){waitForExtJSAjaxComplete(25);}

		Reporter.log("Wait for mask disappear" + " (" + timer.stop() + "ms)");
		System.out.println("Wait for mask disappear" + " (" + timer.stop() + "ms)");
	//	waitForElementDisappear("//div[contains(@class, 'ext-el-mask')]");
	}

	public void waitForDialogMaskDisappear(final String dialogXPath) {
		Timer timer = new Timer().start();
		waitForExtJSAjaxComplete(5);
                new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(dialogXPath + "//div[contains(@class, 'ext-el-mask')]")));
		waitForExtJSAjaxComplete(5);
		Reporter.log("Wait for mask disappear" + " (" + timer.stop() + "ms)");
		System.out.println("Wait for mask disappear" + " (" + timer.stop() + "ms)");
          //      waitForElementDisappear(dialogXPath + "//div[contains(@class, 'ext-el-mask')]");
	}

	public void waitForDialogLoadingDisappear(final String dialogXPath) {
		Timer timer = new Timer().start();
		waitForExtJSAjaxComplete(5);
                new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(dialogXPath + "//div[contains(@class,'x-panel-body') and text()='Loading...']")));
		waitForExtJSAjaxComplete(5);
		Reporter.log("Wait for mask disappear" + " (" + timer.stop() + "ms)");
		System.out.println("Wait for mask disappear" + " (" + timer.stop() + "ms)");
	}


	public void waitForMaskDisappear(final String parentClass) {
		Timer timer = new Timer().start();


		try {

//			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			new WebDriverWait(driver, 25, 250)
					.until(new ExpectedCondition<Boolean>() {
						@Override
						public Boolean apply(WebDriver driver) {
//                                                        try {Thread.sleep(500);} catch (InterruptedException e1) {}
							try {
								WebElement mask = driver.findElement(By
										.xpath("//div[contains(@class,'" + parentClass + "')]//div[contains(@class, 'masked') and "
												+ "not(contains(@style, 'display: none'))]"));

								return mask != null;
							} catch (StaleElementReferenceException e) {
								return false;
							}
						}
					});
		}
		catch(Exception e){waitForExtJSAjaxComplete(25);}
		finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);

		}

		Reporter.log("Wait for mask disappear" + " (" + timer.stop() + "ms)");
		System.out.println("Wait for mask disappear" + " (" + timer.stop()
				+ "ms)");
	}


	/**
	 * Method expand menu (bold) if it collapsed
	 * 
	 * @param menuName
	 *            - header menu name
	 * @throws Exception
	 */
	protected void expandMainMenu(final String menuName) {
		if (driver
				.findElement(
						By.xpath("//*[@id='" + PORTAL_NAVIGATION_TREE_ID
								+ "']//*[contains(text(),'" + menuName
								+ "')]/../../..")).getAttribute("class")
				.contains("collapsed")) {
			driver.findElement(
					By.xpath("//*[@id='" + PORTAL_NAVIGATION_TREE_ID
							+ "']//*[contains(text(),'" + menuName + "')]"))
					.click();

			new WebDriverWait(driver, 20, 250)
					.until(new ExpectedCondition<Boolean>() {
						@Override
						public Boolean apply(WebDriver driver) {
							try {

								if (driver
										.findElement(
												By.xpath("//*[@id='"
														+ PORTAL_NAVIGATION_TREE_ID
														+ "']//*[contains(text(),'"
														+ menuName
														+ "')]/../../.."))
										.getAttribute("class")
										.contains("collapsed"))

								{
									return false;
								}

								return true;
							} catch (Exception e) {
								return false;
							}
						}
					});

		}
	}
	
	/**
	 * Method expand sub menu if it collapsed
	 * 
	 * @param menuName
	 *            - header menu name
	 * @throws Exception
	 */
	protected void expandSubMainMenu(final String menuName) {
		if (driver
				.findElement(
						By.xpath("//*[@id='" + PORTAL_NAVIGATION_TREE_ID
								+ "']//*[contains(text(),'" + menuName
								+ "')]/../..")).getAttribute("class")
				.contains("collapsed")) {
			driver.findElement(
					By.xpath("//*[@id='" + PORTAL_NAVIGATION_TREE_ID
							+ "']//*[contains(text(),'" + menuName + "')]/../..//img[contains(@class,'plus')]"))
					.click();

			new WebDriverWait(driver, 20, 250)
					.until(new ExpectedCondition<Boolean>() {
						@Override
						public Boolean apply(WebDriver driver) {
							try {

								if (driver
										.findElement(
												By.xpath("//*[@id='"
														+ PORTAL_NAVIGATION_TREE_ID
														+ "']//*[contains(text(),'"
														+ menuName
														+ "')]/../.."))
										.getAttribute("class")
										.contains("collapsed"))

								{
									return false;
								}

								return true;
							} catch (Exception e) {
								return false;
							}
						}
					});

		}
	}

	/**
	 * Method expand menu (bold) if it collapsed IN Aministration
	 * 
	 * @param menuName
	 *            - header menu name
	 * @throws Exception
	 */
	protected void expandMainMenuInAministration(final String menuName) {
		if (driver
				.findElement(
						By.xpath("//div[contains(@class, '" + ADMINISTRATION_NAVIGATION_TREE
								+ "')]//*[contains(text(),'" + menuName
								+ "')]/../..")).getAttribute("class")
								.contains("collapsed")) {
			driver.findElement(
						By.xpath("//div[contains(@class, '" + ADMINISTRATION_NAVIGATION_TREE
								+ "')]//*[contains(text(),'" + menuName
								+ "')]")).click();

			new WebDriverWait(driver, 20, 250)
					.until(new ExpectedCondition<Boolean>() {
						@Override
						public Boolean apply(WebDriver driver) {
							try {

								if (driver
										.findElement(
												By.xpath("//div[contains(@class, '"
														+ ADMINISTRATION_NAVIGATION_TREE
														+ "')]//*[contains(text(),'"
														+ menuName
														+ "')]/../.."))
										.getAttribute("class")
										.contains("collapsed"))

								{
									return false;
								}

								return true;
							} catch (Exception e) {
								return false;
							}
						}
					});

		}
	}
	
	/**
	 * Method expands Administration
	 */
	public void expandAdministration() {
		Timer timer = new Timer().start();
		expandMainMenu("Administration");
		Reporter.log("Expand Administration menu " + " (" + timer.stop()
				+ "ms)", true);
	}
	
	/**
	 * Method expands Administration
	 */
	public void expandmyMCSAccount() {
		Timer timer = new Timer().start();
		expandMainMenu("MCS Account");
		Reporter.log("Expand My Account menu " + " (" + timer.stop()
				+ "ms)", true);
	}

	/**
	 * Method expands Test modules
	 */
	public void expandTestModules() {
		Timer timer = new Timer().start();
		expandMainMenu("Test modules");
		Reporter.log("Expand Test modules menu " + " (" + timer.stop() + "ms)",
				true);
	}

	/**
	 * Method expands mymcs Applications
	 */
	public void expandApplication() {
		Timer timer = new Timer().start();
		expandMainMenu("myMCS Applications");
		Reporter.log("Expand Applications menu " + " (" + timer.stop() + "ms)",
				true);
	}

	/**
	 * Method expands mymcs Account
	 */
	public void expandAccount() {
		Timer timer = new Timer().start();
		expandMainMenu("myMCS Account");
		Reporter.log("Expand Account menu " + " (" + timer.stop() + "ms)", true);
	}
	
	
	/**
	 * Wait for ExtJS Ajax request to be finished
	 * Ext.mcs.PageState.isLoading() is a custom function of mymcs
	 * 
	 * @param time
	 */
	public void waitForExtJSAjaxComplete(int time) {
		final String script =  "return Ext.Ajax.isLoading()||Ext.mcs.PageState.isLoading()";
		final String scriptStandard = "return Ext.Ajax.isLoading()";
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			new WebDriverWait(driver, time/4, 300)
					.until(new ExpectedCondition<Boolean>() {
						@Override
						public Boolean apply(WebDriver driver) {
							Boolean result = !(boolean) ((JavascriptExecutor) driver)
									.executeScript(script);
							return result;
						}
					});
		} catch (Exception e) {
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				new WebDriverWait(driver, time/4, 300)
						.until(new ExpectedCondition<Boolean>() {
							@Override
							public Boolean apply(WebDriver driver) {
								Boolean result = !(boolean) ((JavascriptExecutor) driver)
										.executeScript(scriptStandard);
								return result;
							}
						});
			}
			catch(Exception e2){}
			
		} finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);
		}

		

		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			new WebDriverWait(driver, time/4, 300)
					.until(new ExpectedCondition<Boolean>() {
						@Override
						public Boolean apply(WebDriver driver) {
							Boolean result = !(boolean) ((JavascriptExecutor) driver)
									.executeScript(script);
							return result;
						}
					});
		} catch (Exception e) {
			
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				new WebDriverWait(driver, time/4, 300)
						.until(new ExpectedCondition<Boolean>() {
							@Override
							public Boolean apply(WebDriver driver) {
								Boolean result = !(boolean) ((JavascriptExecutor) driver)
										.executeScript(scriptStandard);
								return result;
							}
						});
			}
			catch(Exception e2){}

			
		} finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);
		}

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}

		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}

		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
		}

	}

	/**
	 * XPATH based locator Method allows click on dialog button at confirmation
	 * dialog
	 */
	public void clickOnDialogButton(String buttonText) {
		clickXPath("//div[contains(@class, 'x-window-dlg')]//button[contains(@class, 'x-btn-text') and text()='" + buttonText + "']");
		Reporter.log("Click on '"+buttonText+"' button on dialog", true);
	}
	
	public boolean verifyWarningDialogTextMessage(String mesageValue) {
		try {
			driver.findElement(By.xpath("//div[contains(@class, 'x-window-dlg')]//span[contains(text(), '" + mesageValue + "')]"));
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
		return false;
		}
	}
	
	
	/**
	 * Helper method to get text of Warning dialog
	 * */
	public String getWarningDialogTextMsg() {
		
		String msg = null;
		try {
			msg = driver.findElement(By.xpath("//div[contains(@class, 'x-window-dlg')]")).getText();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return msg;
	}

	/**
	 * XPATH based locator Method allows to wait an click on a button located by
	 * xpath
	 */
	public void waitAndClick(String xpath) {
                clickXPath(xpath);
	}


	/**
	 * Link text based locator Method allows to wait and click on a button
	 * located by partial link text
	 */
	public void waitAndClickLink(String linkText) {
		WebElement link = driver.findElement(By.partialLinkText(linkText));
		waitWebElement(link);
		link.click();
	}

	/**
	 * XPATH based locator Method allows to click on a button located by xpath
	 */
	public void click(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}

	/**
	 * XPATH based locator Method allows to wait and send keys to some input
	 * located by xpath
	 */
	public void waitAndSendKeys(String xpath, String text) {
		WebElement element = driver.findElement(By.xpath(xpath));
		waitWebElement(element);
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * XPATH based locator Method allows to send keys to some input located by
	 * xpath
	 */
	public void sendKeys(String xpath, String text) {
		driver.findElement(By.xpath(xpath)).sendKeys(text);
	}

	/**
	 * XPATH based locator Method allows to wait, focus and click on a button by
	 * xpath
	 */
	public void waitFocusAndClick(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		waitWebElement(element);
		waitForExtJSAjaxComplete(20);
		javaScriptFocus(element);
	/*	try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}   */
		element.click();
	}

	/**
	 * 
	 * Method allows to wait, focus and click on an element
	 */
	public void waitFocusAndClick(WebElement element) {
		waitWebElement(element);
		javaScriptFocus(element);
		element.click();
	}

	/**
	 * 
	 * Method allows to perform javascript mouse over
	 */
	public void javaScriptMouseOver(WebElement element) {
		String javaScript = "var evObj = document.createEvent('MouseEvents');"
				+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
				+ "arguments[0].dispatchEvent(evObj);";
		((JavascriptExecutor) driver).executeScript(javaScript, element);
	}

	/**
	 * 
	 * Method allows to perform javascript focus on an element
	 */
	public void javaScriptFocus(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].focus();",
				element);
	}

	/**
	 * 
	 * Method allows to perform javascript click on element
	 */
	public void javaScriptClick(WebElement element) {
		if ("safari".equals(configuration.getBrowser())) {
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"click\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			((JavascriptExecutor) driver).executeScript(javaScript, element);
			
		} else {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				element);}
	}

	/**
	 * 
	 * Method allows to perform javascript click on element by xpath
	 */
	public void javaScriptClick(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		waitWebElement(element);
		if ("safari".equals(configuration.getBrowser())) {
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"click\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			((JavascriptExecutor) driver).executeScript(javaScript, element);
			
		} else {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				element);}
	}

	/**
	 * 
	 * Method allows to perform javascript mouse over
	 */
	public void javaScriptMouseOver(String xpath) {
		String javaScript = "var evObj = document.createEvent('MouseEvents');"
				+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
				+ "arguments[0].dispatchEvent(evObj);";
		WebElement element = driver.findElement(By.xpath(xpath));
		waitWebElement(element);
		((JavascriptExecutor) driver).executeScript(javaScript, element);
	}

	/**
	 * 
	 * Method allows to perform native (webdriver) mouse move (over)
	 */
	public void mouseMove(String xpath) {
		if ("safari".equals(configuration.getBrowser()) || "9".equals(configuration.getBrowserVersion()) ) {
			javaScriptMouseOver(xpath);
		} else {
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.xpath(xpath));
			waitWebElement(we);
			action.moveToElement(we).build().perform();
		}
	}
	
	/**
	 * Method allows to wait for some element to disappear
	 * @param  xpath - xpath of the element
	 */
	public void waitForElementDisappear(final String xpath) {
		Timer timer = new Timer().start();

		try {
			try {Thread.sleep(500);} catch (InterruptedException e1) {}
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			new WebDriverWait(driver, 25, 250)
					.until(new ExpectedCondition<Boolean>() {
						@Override
						public Boolean apply(WebDriver driver) {
							try {
								List<WebElement> masks = driver.findElements(By
										.xpath(xpath));
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
		} 
		//catch (Exception e) {		} 
		finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);

		}

		Reporter.log("Wait for element to disappear" + " (" + timer.stop() + "ms)");
		System.out.println("Wait for element to disappear" + " (" + timer.stop()
				+ "ms)");
	}
	

	/**
	 * Method allows to get dynamical id of the modal window
	 * returns x-window id 
	 * 
	 * @param xwindowTitle - title of the x-window 
	 */
	public String getXWindowId(String xwindowTitle) throws NoSuchElementException {

		String elementXpath = "(//div[contains(@class, 'x-window')]//span[contains(text(),'"
				+ xwindowTitle + "')])[last()]/../../../../..";

		WebElement webElement = new WebDriverWait(driver, configuration.getImplicitWait())
				.until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath(elementXpath)));
		
		return webElement.getAttribute("id");
	}
	

	/**
	 * Method allows to get dynamical id of the x panel
	 * @return x-panel id 
	 * 
	 * @param xpanelTitle - title of the x-panel 
	 */
	public String getXPanelId(String xpanelTitle) throws NoSuchElementException {

		String elementXpath = "//div[contains(@class, 'x-panel')]//span[text()='"
				+ xpanelTitle + "'  and contains(@class,'x-panel') ]/../..";

		WebElement webElement = new WebDriverWait(driver, configuration.getImplicitWait())
				.until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath(elementXpath)));
		
		return webElement.getAttribute("id");
	}

	
	/**
	 * Method allows to get dynamical id of the modal window
	 * returns x-window id 
	 * 
	 * @param inputClassName - name of the input for which lookup is opened 
	 */
	public String getXWindowIdByClass(String xwindowClass) throws NoSuchElementException {

		String elementXpath = "//div[contains(@class, '" + xwindowClass + "')]";

		WebElement webElement = new WebDriverWait(driver, configuration.getImplicitWait())
				.until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath(elementXpath)));
		
		return webElement.getAttribute("id");
	}
	
	
	
	public boolean isElementAbsent(String xpath) {
		try {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			return driver.findElement(By.xpath(xpath)).isDisplayed();
		} catch (Exception e) {
			return true;
		} finally {
			try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
		}
		
	}
	
//////////////////////////////////////BELOW: METHODS TO WORK WITH LOOKUPS//////////////////////////////////////////////////////	

	/**
	 * Method allows to click on lookup button on the form for some input
	 * @param  formClass - class of the window where lookup is present
	 * @param inputName - name of the input for which lookup is opened 
	 */
	public void clickLookup(String formClass, String inputName){
		Timer timer = new Timer().start();
		int xwindowNumber=0;
		
		try {driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			xwindowNumber = driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size();
		}
		finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);}

		waitFocusAndClick("//div[contains(@class,'"
				+ formClass + "')]//input[contains(@name,'"
				+ inputName + "')]//..//..//button");
		
		waitForExtJSAjaxComplete(5);
		waitForExtJSAjaxComplete(5);

		try {driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			if (driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size() == xwindowNumber)
				{waitAndClick("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");}
		}

		 finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
		}

		Reporter.log(inputName + " lookup was clicked"+ " (" + timer.stop()
				+ "ms)", true);
		
	}
	


	/**
	 * Method allows to click on lookup button on the form for some input
	 * @param  parentAttributeValue - parent attribute (@class, @id) of the form where lookup is present
	 * @param  parentAttributeValue - value of parent attribute
	 * @param  childWindowHeader - header of the child window which should appear
	 * @param  inputName - name of the input for which lookup is opened 
	 */	
	public void clickLookup(String parentAttribute, String parentAttributeValue, String inputName, String childWindowHeader){
		Timer timer = new Timer().start();

		waitFocusAndClick("//div[contains("+parentAttribute+",'"
				+ parentAttributeValue + "')]//input[contains(@name,'"
				+ inputName + "')]//..//..//button");

		try {
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(By
					.xpath("//div[contains(@id,'"
				+getXWindowId(childWindowHeader) + "')]"));
		}

		catch (Exception e) {
			waitAndClick("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");
		} finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);
		}
		waitForExtJSAjaxComplete(10);

		Reporter.log(inputName + " lookup was clicked"+ " (" + timer.stop()
				+ "ms)", true);
		
		
	}


	
	/**
	 * Method allows to select row from grid in lookup using filters
	 * 
	 * @param rowTextName - row text to be selected from lookup 
	 * 
	 * @columnName - columnName of the row to be selected
	 */
	public void setValueGridLookupWithFilters(String rowTextName, String columnName) {
		Timer timer = new Timer().start();
		
		String columnClass = McsElement
		.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-window x-window-noborder x-resizable-pinned",
				"div","@class", "x-grid3-hd",
				"text()", columnName, true, true).getAttribute("class");
		
		//String columnNumber = columnClass.substring(columnClass.length() - 1);
		
		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
		
		
		filterGrid("@class", "x-window x-window-noborder x-resizable-pinned", rowTextName,  columnName);
		
			
//			McsElement
//			.getElementByPartAttributeValueAndParentElement(driver,
//					"div", "@class", "x-window x-window-noborder x-resizable-pinned", "div",
//					"@class", "x-grid3-body", true, true).click();
			
			waitForExtJSAjaxComplete(5);
			
		/*	if(configuration.getBrowser().equalsIgnoreCase("chrome")){
				
				rowTextName = rowTextName.replaceAll(" ", "&nbsp;");
			}*/
			
			String xpath ="(//"+"div"+"[contains("+"@class"+",'" + "x-window x-window-noborder x-resizable-pinned" + "')]//"
							+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
							//+ "and "+"text()"+ "='" + rowTextName + "'" + 
							+"])";//[last()]
			
			List<WebElement> searchResults = driver.findElements(By.xpath(xpath));
			
			if(searchResults.size()==0){
				
				Reporter.log("No search results found for filtering criteria ", true);
				throw new NoSuchElementException("No search results found for filtering criteria");
				
			}
			
			for(WebElement ele: searchResults){
				
				if(ele.getText().equalsIgnoreCase(rowTextName)){
					
					ele.click();
					break;
				}
			}
			
			/*McsElement.getElementByXpath(driver,"(//"+"div"+"[contains("+"@class"+",'" + "x-window x-window-noborder x-resizable-pinned" + "')]//"
					+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
					+ "and "+"text()"+ "='" + rowTextName + "'" + 
					"])[last()]").click();
			*/
//			McsElement
//					.getElementByPartAttributeValueAndParentElement(driver,
//							"div", "@class", "x-window x-window-noborder x-resizable-pinned", "div",
//							"@class", "x-grid3-cell-inner x-grid3-col-"+columnNumber,
//							"text()", rowTextName, true, true).click();
			waitForExtJSAjaxComplete(5);
			clickOkXwindow();
			Reporter.log(rowTextName + " was selected"+ " (" + timer.stop()
					+ "ms)", true);

	}
	

	/**
	 * Method allows to filter in grid by text using Selenium Webdriver xPath Expression
	 * This method will not have visibility issue while filtering
	 */
	public void filterGridWithoutUsingMcsElement(String attribute, String attributeValue, String rowTextName, String columnName) {
		Timer timer = new Timer().start();
		
		
		String columnClass = driver.findElement(By.xpath("//div[contains("+attribute+",'"+ attributeValue+"')]//div[contains(@class,'quickfilters')]"
						+ "//div[contains(@class,'x-grid3-hd') and contains(text(),'"+columnName+"')][last()]")).getAttribute("class");
		
		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
		
		WebElement filterInput = driver.findElement(By.xpath("//div[contains("+attribute+",'"+attributeValue+"')]//"
				+ "input[contains(@id,'filter-editor-"+columnNumber+"')][last()]")); 
					
		filterInput.clear();
			
		filterInput.sendKeys(rowTextName);
		
		waitForExtJSAjaxComplete(25);
			
		driver.findElement(By.xpath("(//div[contains("+attribute+",'"+attributeValue+"')]//"
				+ "div[contains(@class,'x-grid3-body')])[last()]/div")).click(); 

		waitForExtJSAjaxComplete(5);

		Reporter.log(rowTextName + " was filtered"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	/**
	 * Method allows to filter in grid by text
	 * @param attribute - attribute (@class, @id) of the grid container
	 * @param attributeValue - corresponding value of the grid container attribute
	 * @param rowTextName - row text to be filtered by 
	 * @columnName - columnName of the row 
	 */
	public void filterGrid(String attribute, String attributeValue, String rowTextName, String columnName) {
		Timer timer = new Timer().start();
		
		
		String columnClass = McsElement
				.getElementByXpath(driver,"(//div[contains("+attribute+",'"+ attributeValue+"')]//div[contains(@class,'quickfilters')]"
						+ "//div[contains(@class,'x-grid3-hd') and contains(text(),'"+columnName+"')])[last()]").getAttribute("class");
//		String columnClass = McsElement
//		.getLastElementByPartAttributeValueAndParentElement(driver,
//				"div", attribute, attributeValue,
//				"div","@class", "x-grid3-hd",
//				"text()", columnName, true, true).getAttribute("class");
		
		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
		

		WebElement filterInput = McsElement.getElementByXpath(driver, "(//div[contains("+attribute+",'"+attributeValue+"')]//"
					+ "input[contains(@id,'filter-editor-"+columnNumber+"')])[last()]"); 
		waitForExtJSAjaxComplete(20);		
					
//					McsElement
//					.getElementByPartAttributeValueAndParentElement(driver,
//							"div", attribute, attributeValue, "input",
//							"@id", "filter-editor-"+columnNumber, true, true);
			
			filterInput.clear();
			
			filterInput.sendKeys(rowTextName);
			
			 McsElement.getElementByXpath(driver, "(//div[contains("+attribute+",'"+attributeValue+"')]//"
						+ "div[contains(@class,'x-grid3-body')])[last()]/div").click(); 
			
//			McsElement
//			.getElementByPartAttributeValueAndParentElement(driver,
//					"div", attribute, attributeValue, "div",
//					"@class", "x-grid3-body", true, true).click();
			
			waitForExtJSAjaxComplete(5);

			Reporter.log(rowTextName + " was filtered"+ " (" + timer.stop()
					+ "ms)", true);

	}
	
	
	/**
	 * Method allows to select row from grid in lookup using filters
	 * @param attribute - attribute (@class, @id) of the lookup window
	 * @param attributeValue - attribute of the lookup must contain this value
	 * @param rowTextName - row text to be selected from lookup 
	 * 
	 * @columnName - columnName of the row to be selected
	 */
	public void setValueGridLookupWithFilters(String attribute, String attributeValue, String rowTextName, String columnName) {
		Timer timer = new Timer().start();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		filterGrid(attribute, attributeValue, rowTextName, columnName);	
		
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		String columnClass = McsElement
		.getLastElementByPartAttributeValueAndParentElement(driver,
				"div", attribute, attributeValue,
				"div","@class", "x-grid3-hd",
				"text()", columnName, true, true).getAttribute("class");
		
		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
		//String columnNumber = columnClass.substring(columnClass.length() - 1);
		
		waitForExtJSAjaxComplete(25);
		
		String xpath ="(//"+"div"+"[contains("+"@class"+",'" + "x-window x-window-noborder x-resizable-pinned" + "')]//"
				+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
				//+ "and "+"text()"+ "='" + rowTextName + "'" + 
				+"])";//[last()]

		List<WebElement> searchResults = driver.findElements(By.xpath(xpath));

		if(searchResults.size()==0){

			Reporter.log("No search results found for filtering criteria ", true);
			throw new NoSuchElementException("No search results found for filtering criteria");

		}

		for(WebElement ele: searchResults){

			if(ele.getText().equalsIgnoreCase(rowTextName)){

				ele.click();
				break;
			}
		}

		/*McsElement.getElementByXpath(driver,"(//"+"div"+"[contains("+attribute+",'" + attributeValue + "')]//"
		+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
		+ "and "+"text()"+ "='" + rowTextName + "'" +
		"])[last()]").click();
*/
		waitForExtJSAjaxComplete(25);
			
//			McsElement
//					.getLastElementByPartAttributeValueAndParentElement(driver,
//							"div", attribute, attributeValue, "div",
//							"@class", "x-grid3-cell-inner x-grid3-col-"+columnNumber,
//							"text()", rowTextName, true, true).click();
			
			clickOkXwindow();
			Reporter.log(rowTextName + " was selected"+ " (" + timer.stop()
					+ "ms)", true);

	}
	
	
	/**
	 * Method allows to select row from grid in lookup using filters
	 * @param attribute - attribute (@class, @id) of the lookup window
	 * @param attributeValue - attribute of the lookup must contain this value
	 * @param rowTextName - row text to be selected from lookup 
	 * 
	 * @columnName - columnName of the row to be selected
	 */
	public void setValueGridLookupWithFiltersWithoutUsingMCSElement(String attribute, String attributeValue, String rowTextName, String columnName) {
		Timer timer = new Timer().start();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		filterGrid(attribute, attributeValue, rowTextName, columnName);	
		
		waitForExtJSAjaxComplete(25);
		
		String xpathExpression= "(//div[contains("+attribute+",'" + attributeValue + "')]//div[contains(@class,'x-grid3-hd')"
		+ "and contains(text(),'" + columnName + "')" + "])[last()]";
		
		String columnClass = driver.findElement(By.xpath(xpathExpression)).getAttribute("class");
		
				
		
		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
		
		waitForExtJSAjaxComplete(25);
		
		McsElement.getElementByXpath(driver,"(//"+"div"+"[contains("+attribute+",'" + attributeValue + "')]//"
		+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
		+ "and "+"text()"+ "='" + rowTextName + "'" +
		"])[last()]").click();

		waitForExtJSAjaxComplete(25);
			
		clickOkXwindow();
		
		Reporter.log(rowTextName + " was selected"+ " (" + timer.stop()
					+ "ms)", true);

	}
	
	/**
	 * Method allows to select row from grid in lookup using filters
	 * @param attribute - attribute (@class, @id) of the lookup window
	 * @param attributeValue - attribute of the lookup must CONTAINS this value
	 * @param rowTextName - row text to be selected from lookup 
	 * 
	 * @columnName - columnName of the row to be selected
	 * @return true if row was present and false if row was not present in grid
	 */
	public boolean setValueGridLookupWithFiltersWithValidation(String attribute, String attributeValue, String rowTextName, String columnName) {
		Timer timer = new Timer().start();
		
		filterGrid(attribute, attributeValue, rowTextName, columnName);
		
		String columnClass = McsElement
		.getElementByPartAttributeValueAndParentElement(driver,
				"div", attribute, attributeValue,
				"div","@class", "x-grid3-hd",
				"text()", columnName, true, true).getAttribute("class");
		
		//String columnNumber = columnClass.substring(columnClass.length() - 1);
		
		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
			
			try {
				driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
				
				//TODO: THis code is implemented as text() matching fails for elements which contains space in its text.
				String xpath ="(//"+"div"+"[contains("+"@class"+",'" + "x-window x-window-noborder x-resizable-pinned" + "')]//"
						+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
						//+ "and "+"text()"+ "='" + rowTextName + "'" + 
						+"])";//[last()]
		
				List<WebElement> searchResults = driver.findElements(By.xpath(xpath));

				if(searchResults.size()==0){

					Reporter.log("No search results found for filtering criteria ", true);
					throw new NoSuchElementException("No search results found for filtering criteria");

				}

				for(WebElement ele: searchResults){

					if(ele.getText().trim().equals(rowTextName)){
						
						Actions actions = new Actions(driver);
						actions.moveToElement(ele).click().perform();

						//ele.click();
						break;
					}
				}
		
				/*		McsElement
					.getElementByPartAttributeValueAndParentElement(driver,
							"div", attribute, attributeValue, "div",
							"@class", "x-grid3-cell-inner x-grid3-col-"+columnNumber,
							"text()", rowTextName, false, false).click();*/
				
			waitForExtJSAjaxComplete(25);
				
			clickOkXwindow();
			Reporter.log(rowTextName + " is present in grid and selected"+ " (" + timer.stop()
					+ "ms)", true);
			return true;
			}
			catch(Exception e){
				Reporter.log(rowTextName + " is not present"+ " (" + timer.stop()
						+ "ms)", true);
				return false;
			}
			 finally {
					try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
				}
	}
	
	/**
	 * Method allows to select row from grid in lookup using filters
	 * @param attribute - attribute (@class, @id) of the lookup window
	 * @param attributeValue - attribute of the lookup must HAVE EXACT MATCH this value
	 * @param rowTextName - row text to be selected from lookup 
	 * 
	 * @columnName - columnName of the row to be selected
	 * @return true if row was present and false if row was not present in grid
	 */
	public boolean setExactValueGridLookupWithFiltersWithValidation(String attribute, String attributeValue, String rowTextName, String columnName) {
		Timer timer = new Timer().start();
		
		filterGrid(attribute, attributeValue, rowTextName, columnName);
		
		String columnClass = McsElement
		.getElementByPartAttributeValueAndParentElement(driver,
				"div", attribute, attributeValue,
				"div","@class", "x-grid3-hd",
				"text()", columnName, true, true).getAttribute("class");
		
		//String columnNumber = columnClass.substring(columnClass.length() - 1);
		
		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
			
			try {
				driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
				/*McsElement
					.getElementByAttributeValueAndParentElement(driver,
							"div", attribute, attributeValue, "div",
							"@class", "x-grid3-cell-inner x-grid3-col-"+columnNumber,
							"text()", rowTextName, false, false).click();*/
				
				//TODO: THis code is implemented as text() matching fails for elements which contains space in its text.
				String xpath ="(//"+"div"+"[contains("+"@class"+",'" + "x-window x-window-noborder x-resizable-pinned" + "')]//"
						+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
						//+ "and "+"text()"+ "='" + rowTextName + "'" + 
						+"])";//[last()]

				List<WebElement> searchResults = driver.findElements(By.xpath(xpath));
				
				int count = 0;

				if(searchResults.size()==0){

					Reporter.log("No search results found for filtering criteria ", true);
					throw new NoSuchElementException("No search results found for filtering criteria");

				}

				for(WebElement ele: searchResults){

					if(ele.getText().equals(rowTextName)){
						
						ele.click();
						count++;
						break;
					}
					
				}
				
				//If the Exact match is not found, Move the control to catch block and click cancel
				if(count==0){
					Reporter.log("No search results found for filtering criteria ", true);
					throw new NoSuchElementException("No search results found for filtering criteria");
				}

			
			//clickOkXwindow();
			McsElement.getElementByXpath(driver, "//div[contains(" + attribute + ",'" + attributeValue + "')]//button[contains(text(),'OK')]").click();
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			Reporter.log(rowTextName + " is present in grid and selected"+ " (" + timer.stop()
					+ "ms)", true);
			return true;
			}
			catch(Exception e){
				Reporter.log(rowTextName + " is not present"+ " (" + timer.stop()
						+ "ms)", true);
				//clickCANCELXwindow();
				McsElement.getElementByXpath(driver, "//div[contains(" + attribute + ",'" + attributeValue + "')]//button[contains(text(),'Cancel')]").click();
				
				waitForExtJSAjaxComplete(25);
				
				waitForExtJSAjaxComplete(25);
				
				return false;
			}
			 finally {
					try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
				}
	}
	
	/**
	 * Method allows to select row from grid in lookup (row with text should be visible in any column)
	 * 
	 * @param parentAttribute - x-window atribute  (@class, @id) 
	 *  
	 * @param parentAttributeValue - value of this attribute
	 * 
	 * @param rowTextName - row text to be selected from lookup 
	 */
	public void setValueGridLookup(String parentAttribute, String parentAttributeValue, String rowTextName) {
		Timer timer = new Timer().start();
		
		String []subStrings = rowTextName.split(" ");

		String subXpathStr = "and starts-with(text(),'"+subStrings[0]+"')";

		for(int i=1; i<subStrings.length; i++){

			subXpathStr+="and contains(text(),'"+subStrings[i]+"')";
		}
		
		String elementXpath = "//div[contains("+parentAttribute+",'"+parentAttributeValue
				+"')]//div[contains(@class,'x-grid3-cell-inner x-grid3-col-')"+subXpathStr+"]";
		
	
		WebElement firstRowWithText = 
				driver.findElement(By.xpath(((elementXpath))));
		//find row with given text in any column
		//this row should be visible (no scrolling is performed)
			/*WebElement firstRowWithText1 = McsElement
					.getElementByPartAttributeValueAndParentElement(driver,"div"
							, parentAttribute, parentAttributeValue, "div",
							"@class", "x-grid3-cell-inner x-grid3-col-",
							"text()", rowTextName, true, true);*/
			firstRowWithText.click();

			clickOkXwindow();
			Reporter.log(rowTextName + " was selected"+ " (" + timer.stop()
					+ "ms)", true);

	}


	
	/**
	 * Method allows to select row from grid in lookup (row with text should be visible in any column)
	 * 
	 * @param rowTextName - row text to be selected from lookup 
	 */
	public void setValueGridLookup(String rowTextName) {
		Timer timer = new Timer().start();
		
		//TODO: This code is implemented as text() matching fails for elements which contains space in its text.
		String []subStrings = rowTextName.split(" ");
		
		String subXpathStr = "and starts-with(text(),'"+subStrings[0]+"')";
		
		for(int i=1; i<subStrings.length; i++){
			
			subXpathStr+="and contains(text(),'"+subStrings[i]+"')";
		}
				
		//find row with given text in any column
		//this row should be visible (no scrolling is performed)
			WebElement firstRowWithText = 
					driver.findElement(By.xpath((("((//div[contains(@class, 'x-resizable-pinned')]) [last()])" +
							" //div[contains(@class,'x-grid3-cell-inner x-grid3-col-')"+ subXpathStr +"]"))));
					
			waitWebElement(firstRowWithText);
			firstRowWithText.click();
			clickOkXwindow();
			Reporter.log(rowTextName + " was selected"+ " (" + timer.stop()
					+ "ms)", true);

	}
	
	
//Do not use: Deprecated	
//	/**
//	 * Method allows to select row from grid in lookup using Extjs script
//	 * @param rowTextName - row text to be selected from lookup 
//	 * @param columnNumber  - number of column where we want to find text ("2" column for example)
//	 */
//	public void setValueGridLookupExtJs(String rowTextName, int columnNumber) {
//		Timer timer = new Timer().start();
////			waitForExtJSAjaxComplete(25);	
//			McsElement
//					.getElementByPartAttributeValueAndParentElement(driver,
//							"div", "@class", "x-window x-window-noborder x-resizable-pinned", "div",
//							"@class", "x-grid3-cell-inner x-grid3-col-",
//							"text()", "", true, true);
//			
//		String id = driver.findElement(By.xpath("//div[contains(@class,'x-panel x-panel-noborder x-grid-panel')]")).getAttribute("id");
//		
//		String script ="for (var i=0;i<Ext.getCmp('"+id+"').getStore().getCount();i++)" +
//				"{ if (Ext.getCmp('"+id+"').store.data.items[i].data['"+ columnNumber +"']=='"+rowTextName+"')" +
//						"{Ext.getCmp('"+id+"').getSelectionModel().selectRow(Ext.getCmp('"+id+"').store.indexOfId(Ext.getCmp('"+id+"').store.data.items[i].id))}}";
//			((JavascriptExecutor) driver).executeScript(script);
////			waitForExtJSAjaxComplete(25);
//			clickOkXwindow();
//			Reporter.log(rowTextName + " was selected"+ " (" + timer.stop()
//					+ "ms)", true);
//
//	}


	
	/**
	 * Method allows to click OK on lookup window
	 */	
	public void clickOkXwindow() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-window-footer x-window-footer-noborder x-panel-btns", "button",
				"text()", "OK", true, true).click();
		waitForExtJSAjaxComplete(5);
		waitForExtJSAjaxComplete(5);
	}


	/**
	 * Method allows to click CANCEL on lookup window
	 */	
	public void clickCANCELXwindow() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-window-footer", "button",
				"text()", "Cancel", true, true).click();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Click Cancel button", true);
	}

	/**
	 * Method allows to click CANCEL on lookup window
	 */	
	public void clickCANCELInLookupWindow() {
		
		String xpath = "//div[contains(@class,'x-window x-window-noborder') and contains(@style, 'visibility: visible')]//div[contains(@class,'x-window-footer')]//button[contains(text(),'Cancel')]";
		
		McsElement.getElementByXpath(driver, xpath).click();
		/*McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-window-footer", "button",
				"text()", "Cancel", true, true).click();*/
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Click Cancel button", true);
	}
	
	
	/**
	 * Method allows to expand all nodes and then to select node from tree in lookup
	 * @param rowTextName - node text to be selected from lookup 
	 */
	public void setValueTreeLookup(String rowTextName) {
		
		Timer timer = new Timer().start();
		
		waitForExtJSAjaxComplete(25);
		
		//wait for tree to load
		McsElement
		.getElementByPartAttributeValueAndParentElement(driver, "div",
				"@class",
				"x-window x-window-noborder x-resizable-pinned", "a",
				"@class", "x-tree-node-anchor", true,
				true);
		
		expandAllNodeExtJsTree("@class","x-window x-window-noborder x-resizable-pinned");
		
		//find first node with given text
		WebElement firstNodeWithTextName = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@class",
						"x-window x-window-noborder x-resizable-pinned", "a",
						"@class", "x-tree-node-anchor", ".", rowTextName, true,
						true);
		
		//if scrolling is needed
		javaScriptFocus(firstNodeWithTextName);
		
		firstNodeWithTextName.click();
		
		clickOkXwindow();
		Reporter.log(rowTextName + " was selected" + " (" + timer.stop()
				+ "ms)", true);

	}

	/**
	 * Method allows to expand all nodes and then to select node from tree in lookup
	 * @param rowTextName - node text to be selected from lookup 
	 * @param attribute - attribute (@class, @id) of the lookup window
	 * @param attributeValue - value of the lookup window attribute
	 */
	public void setValueTreeLookup(String rowTextName, String attribute, String attributeValue) {
		
		Timer timer = new Timer().start();
		
		waitForExtJSAjaxComplete(25);
		
		//wait for tree to load
		McsElement
		.getElementByPartAttributeValueAndParentElement(driver, "div",
				attribute,
				attributeValue, "a",
				"@class", "x-tree-node-anchor", true,
				true);
		
		expandAllNodeExtJsTree(attribute,attributeValue);
		
		//find first node with given text
		WebElement firstNodeWithTextName = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						attribute,
						attributeValue, "a",
						"@class", "x-tree-node-anchor", ".", rowTextName, true,
						true);
		
		//if scrolling is needed
		javaScriptFocus(firstNodeWithTextName);
		
		firstNodeWithTextName.click();
		
		clickOkXwindow();
		Reporter.log(rowTextName + " was selected" + " (" + timer.stop()
				+ "ms)", true);

	}
	
	
	/**
	 * Method expand node if it is collapsed
	 * 
	 * @param nodeText - node text
	 * @param parentElement - name of the parent element (div, span, etc)
	 * @param parentAttribute - parent attribute (@class, @id)
	 * @param parentValue - value of the parent attribute
	 * @throws Exception
	 */
	protected void expandNode(final String parentElement,
			final String parentAttribute, final String parentValue,
			final String nodeText) {
		WebElement nodeContainer = driver
				.findElement(
						By.xpath("//" + parentElement + "[contains("
								+ parentAttribute + ",'" + parentValue
								+ "')]//span[text()='" + nodeText + "']//..//.."));
		waitWebElement(nodeContainer);
		javaScriptFocus(nodeContainer);
		if (nodeContainer
				.getAttribute("class").contains("collapsed")) {
			driver.findElement(
					By.xpath("//"
							+ parentElement
							+ "[contains("
							+ parentAttribute
							+ ",'"
							+ parentValue
							+ "')]//span[text()='"
							+ nodeText
							+ "']//..//..//img[contains(@class,'-plus')]"))
					.click();
			Reporter.log("Expand "+nodeText+" node", true);

			new WebDriverWait(driver, 20, 250)
					.until(new ExpectedCondition<Boolean>() {
						@Override
						public Boolean apply(WebDriver driver) {
							try {

								if (driver
										.findElement(
												By.xpath("//" + parentElement
														+ "[contains("
														+ parentAttribute
														+ ",'" + parentValue
														+ "')]//span[text()='"
														+ nodeText
														+ "']//..//.."))
										.getAttribute("class")
										.contains("collapsed"))

								{
									return false;
								}

								return true;
							} catch (Exception e) {
								return false;
							}
						}
					});

		}
	}
	
	/**
	 * Method expand Sub node if it is collapsed
	 * 
	 * @param nodeText - node text
	 * @param parentElement - name of the parent element (div, span, etc)
	 * @param parentAttribute - parent attribute (@class, @id)
	 * @param parentValue - value of the parent attribute
	 * @throws Exception
	 */
	protected void expandSubNode(final String parentElement,
			final String parentAttribute, final String parentValue,
			final String nodeText) {
		WebElement nodeContainer = driver
				.findElement(
						By.xpath("//" + parentElement + "[contains("
								+ parentAttribute + ",'" + parentValue
								+ "')]//span[text()='" + nodeText + "']//..//.."));
		waitWebElement(nodeContainer);
		javaScriptFocus(nodeContainer);
		if (nodeContainer
				.getAttribute("class").contains("collapsed")) {
			driver.findElement(
					By.xpath("//"
							+ parentElement
							+ "[contains("
							+ parentAttribute
							+ ",'"
							+ parentValue
							+ "')]//span[text()='"
							+ nodeText
							+ "']//..//..//a[contains(@class,'x-tree-node-anchor')]"))
					.click();
			Reporter.log("Expand "+nodeText+" node", true);

			new WebDriverWait(driver, 20, 250)
					.until(new ExpectedCondition<Boolean>() {
						@Override
						public Boolean apply(WebDriver driver) {
							try {

								if (driver
										.findElement(
												By.xpath("//" + parentElement
														+ "[contains("
														+ parentAttribute
														+ ",'" + parentValue
														+ "')]//span[text()='"
														+ nodeText
														+ "']//..//.."))
										.getAttribute("class")
										.contains("collapsed"))

								{
									return false;
								}

								return true;
							} catch (Exception e) {
								return false;
							}
						}
					});

		}
	}
	
	/**
	 * Method allows to expand only needed node childs and then to select node from tree in lookup
	 * @param rowTextName - array of the node path text to be selected from lookup (name of nodes starting from root node to the needed node) 
	 */
	public void setValueTreeLookup(String[] rowTextName) {
		
		Timer timer = new Timer().start();
		
		waitForExtJSAjaxComplete(25);
		
		//additional wait for tree to load
		McsElement
		.getElementByPartAttributeValueAndParentElement(driver, "div",
				"@class",
				"x-window x-window-noborder x-resizable-pinned", "a",
				"@class", "x-tree-node-anchor", true,
				true);
		
		for(int i = 0; i < rowTextName.length-1; i++)
		{
		expandNode("div","@class","x-window x-window-noborder x-resizable-pinned", rowTextName[i]);}
		
		//find first node with given text
		WebElement firstNodeWithTextName = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@class",
						"x-window x-window-noborder x-resizable-pinned", "a",
						"@class", "x-tree-node-anchor", ".", rowTextName[rowTextName.length-1], true,
						true);
		
		//if scrolling is needed
		javaScriptFocus(firstNodeWithTextName);
		
		firstNodeWithTextName.click();
		
		clickOkXwindow();
		Reporter.log(Arrays.toString(rowTextName) + " was selected" + " (" + timer.stop()
				+ "ms)", true);

	}

	/**
	 * Method allows to expand only needed node childs and then to select node from tree in lookup
	 * @param rowTextName - array of the node path text to be selected from lookup (name of nodes starting from root node to the needed node) 
	 * @param parentAttribute - @class, @id etc
	 * @param parentValue - part of the value of the parent attribute
	 */
	public void setValueTreeLookup(String[] rowTextName, String parentAttribute, String parentValue) {
		
		Timer timer = new Timer().start();
		
		waitForExtJSAjaxComplete(25);
		
		//additional wait for tree to load
		McsElement
		.getElementByPartAttributeValueAndParentElement(driver, "div",
				parentAttribute,
				parentValue, "a",
				"@class", "x-tree-node-anchor", true,
				true);
		
		for(int i = 0; i < rowTextName.length-1; i++)
		{
		expandNode("div",parentAttribute,parentValue, rowTextName[i]);}
		
		//find first node with given text
		WebElement firstNodeWithTextName = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						parentAttribute,
						parentValue, "a",
						"@class", "x-tree-node-anchor", ".", rowTextName[rowTextName.length-1], true,
						true);
		
		//if scrolling is needed
		javaScriptFocus(firstNodeWithTextName);
		
		firstNodeWithTextName.click();
		
		clickOkXwindow();
		Reporter.log(rowTextName + " was selected" + " (" + timer.stop()
				+ "ms)", true);

	}

	
	
	/**
	 * Method allows to expand all nodes in ExtJstree defined by parent attribute
	 * @param attribute - parent attribute of the tree
	 * @param attributeValue - part of the value of the parent attribute
	 */
	public void expandAllNodeExtJsTree(String attribute, String attributeValue){
		Timer timer = new Timer().start();
		
        	String id = driver
				.findElement(
						By.xpath("//*[contains("+attribute+",'"+attributeValue+"')]//*[contains(@class,'x-panel x-panel-noborder x-tree')]"))
				.getAttribute("id");
		String script = "Ext.getCmp('"+id+"').expandAll()";
		
		((JavascriptExecutor) driver).executeScript(script);
		
		waitForExtJSAjaxComplete(25);
		
		Reporter.log("all nodes in extjstree were expanded" + " (" + timer.stop()
				+ "ms)", true);

	}
	
	
	/**
	 * Method allows to select Root node from tree using Extjs script
	 * nodes expanding is not performed, only Root node is allowed to be selected by this method
	 * 
	 * @param rowTextName
	 *            - root node text to be selected from lookup
	 */
	public void setValueTreeExtJs(String rowTextName, String parentClass) {

		Timer timer = new Timer().start();

		String id = driver
				.findElement(
						By.xpath("//*[contains(@class,'"+parentClass+"')]//*[contains(@class,'x-panel x-panel-noborder x-tree')]"))
				.getAttribute("id");
		
		//wait for grid to appear 
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class",
				"x-window x-window-noborder x-resizable-pinned", "a", "@class",
				"x-tree-node-anchor", true, true);

		String script = "for (var i=0;i<Ext.getCmp('"
				+ id
				+ "').getRootNode().childNodes.length;i++){if(Ext.getCmp('"
				+ id
				+ "').getRootNode().childNodes[i].text=='"
				+ rowTextName
				+ "')"
				+ "{Ext.getCmp('"
				+ id
				+ "').getRootNode().childNodes[i].fireEvent('click',Ext.getCmp('"
				+ id + "').getRootNode().childNodes[i])}	}";

		((JavascriptExecutor) driver).executeScript(script);

		clickOkXwindow();
		
		Reporter.log(rowTextName + " was selected" + " (" + timer.stop()
				+ "ms)", true);

	}
	
		
	/**
	 * Method allows to set value in input near which lookup is present input
	 * text should be present in lookup grid
	 * If you set up value using this method, then you should perform 
	 * another click-operation for this lookup to become set
	 * in this method we click on combo-list but may be in some cases/browsers it will not work
	 * 
	 * @param inputText
	 *            - input text to be set
	 * @param inputName
	 *            - name of the input near which lookup is present
	 */
	public void setInputLookup(String inputText, String inputName, String formClass) {
		Timer timer = new Timer().start();

		String elementXpath = "(//div[contains(@class,'"+formClass+"')]//input[@name='"
				+ inputName + "']//..//input)[last()]";

		WebElement webElement = new WebDriverWait(driver, 25)
				.until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath(elementXpath)));

		webElement.clear();

		webElement.sendKeys(inputText);
		
		waitForExtJSAjaxComplete(25);
		
		// click on combo-list item
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class",
				"x-combo-list-item", "div", "text()",
				inputText, true, true).click();
		
		waitForExtJSAjaxComplete(25);
		
		Reporter.log(inputText + " was set" + " (" + timer.stop()
				+ "ms)", true);
	}
	
	/**
	 * Method allows to verify if lookup field value is the default value for this lookup by:
	 * - Getting actual lookup field value on Add/Edit form; 
	 * - Opening that lookup
	 * - Filtering desired column by field value mentioned above
	 * - Verifying that row, displayed after filtering, is marked as Default
	 * 
	 * @param formClass
	 *            - class attribute of the Add/Edit form
	 * @param fieldName
	 *            - name attribute of the lookup field
	 * @param columnName
	 *            - name of the column to be filter
	 * @param lookupFormTitle
	 *            - title of the lookup form, used to get from ID attribute
	 * @return true if row is marked as Default and false if it doesn't    
	 */
	public boolean verifyLookupIsPrefilledWithDefaultValue(String formClass, String fieldName, String columnName, String lookupFormTitle) {
		
		String fieldValueActual;
		String lookupId;
		Boolean result;
		
		fieldValueActual = McsElement.getFieldValue(driver, formClass, fieldName);
		
		clickLookup(formClass, fieldName);
		
		lookupId = getXWindowId(lookupFormTitle);
		
		filterGrid("@id", lookupId, fieldValueActual, columnName); 
		
		Reporter.log("Filter '"+columnName+"' column by '"+fieldValueActual+"' value", true);
		
		result = McsElement.isElementPresent(driver, "//div[contains(@id,'"+lookupId+"')]//div[contains(@class,'check-col-on')]");
		
		clickCANCELXwindow();
		
		return result;
	}
	
	
	public void scrollGridColumnIntoView( String attribute, String attributeValue, String columnName){
		
		WebElement element = driver.findElement(By.xpath("(//div[contains("+attribute+",'"+ attributeValue+"')]//div[contains(@class,'quickfilters')]"
						+ "//div[contains(@class,'x-grid3-hd') and contains(text(),'"+columnName+"')])[last()]"));
		
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);");
	}
	
	
	/**
	 * Helper method to return alignment of text in Warning dialog
	 * @return alignment of text in Warning dialogs
	 */
	public String getWarningDialogTextAlignment(){
		
		WebElement element = driver.findElement(By.xpath("//div[contains(@class, 'x-window-dlg')]//span[@class='ext-mb-text']"));
		
		return element.getCssValue("text-align");
		
	}


	/**
	 * Method to retrieve values of lookup from grid in lookup
	 * @param attribute - attribute (@class, @id) of the lookup window
	 * @param attributeValue - attribute of the lookup must contain this value
	 * @columnName - columnName of the values to be retrieved
	 */
	public List<String> getValuesFromGridLookup(String attribute, String attributeValue, String columnName) {
		Timer timer = new Timer().start();
		
		waitForExtJSAjaxComplete(25);
		
		String columnClass = McsElement
		.getLastElementByPartAttributeValueAndParentElement(driver,
				"div", attribute, attributeValue,
				"div","@class", "x-grid3-hd",
				"text()", columnName, true, true).getAttribute("class");
		
		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
		
		
		List<WebElement> values = driver.findElements(By.xpath("//"+"div"+"[contains("+attribute+",'" + attributeValue + "')]//"
		+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
		+ "]"));
			
		ArrayList<String> lsValues = new ArrayList<String>(); 
		
		for(int i=0;i<values.size(); i++){
			
			String valueText = values.get(i).getText().trim();
			
			lsValues.add(valueText);
			
		}
		
			
		Reporter.log(columnName + "values are retrieved"+ " (" + timer.stop()+ "ms)", true);
		
		return lsValues;

	}
	
	/**
	 * Helper method to click on Tabs in Lookup window
	 * @param tabText : text of tab to click
	 */
	public void clickTabInLookUpWindow(String attribute ,String attrVal,String tabText){
		
		String xpath = "//div[contains("+attribute+",'"+attrVal+"')]//span[contains(@class,'x-tab-strip-text') and contains(text(),'"+tabText+"')]//ancestor::a";
		
		McsElement.getElementByXpath(driver, xpath).click();
	}

	
	/**
	 * Helper method to get Info bar message from Window
	 * @param attribute of Window where info bar is present
	 * @param attrVal: attribute value of Window where info bar is present
	 * @return Info bar message
	 */
	public String getInfoBarMsgOfWindow(String attribute, String attrVal){
		
		String xpath = "//div[contains("+attribute+",'"+attrVal+"')]//div[contains(@class,'infobar')]";
		 
		return McsElement.getElementByXpath(driver, xpath).getText();
	}
	
	/**
	  * Helper method to get the 10 digit Winodw Header ID  
	  * @param windowTitle of the window
	  * @return 10-digit requisition id
	  */
	 public String getTenDigitWindowHeaderID(String windowTitle){
	  
	  String xpath = "//div[@id='"+getXWindowId(windowTitle)+"']//span[@class='x-window-header-text']";
	    
	  String reqID = McsElement.getElementByXpath(driver, xpath).getText();
	  
	  reqID = reqID.substring(reqID.lastIndexOf(" ")+1, reqID.length()); 
	  
	  Reporter.log("Requisition ID displayed in title is"+reqID, true);
	  
	  String tenDigitReqID = caluclateTenDigitRequsitionID(reqID);
	  
	  Reporter.log("Ten digit Requisition ID is"+tenDigitReqID, true);
	  
	  return tenDigitReqID;
	 }
	 
	 /**
	  * Helper method to calculate 10 digit window header ID. In application 10 digit window header id is used
	  * But In any window header its displayed excluding leading zeros
	  * 
	  * @param nonTenDigitReqId
	  * @return Ten digit requisition ID
	  */
	 public String caluclateTenDigitRequsitionID(String nonTenDigitReqId){
	  int len = 10;
	 
	  String str = nonTenDigitReqId; 
	  while (str.length() < len) 
	  str = "0" + str;
	  return str;
	 }
	 
	 /**
	 * Helper method to get verify New Purchase Order Window Title
	 */
	public String getWindowTitle(String attribute, String attributeValue){
		WebElement windowTitle = McsElement.getElementByXpath(driver, "//div[contains("+attribute+",'" + attributeValue + "')]//span[contains(@class, 'x-window-header-text')]");
		
		return windowTitle.getText();
	}
	
	/**
	 * Helper method to get Window Header Text
	 */
	public String getWindowHeaderText(String windowTitle) {
		return McsElement.getElementByXpath(driver, "//div[@id='"+ getXWindowId(windowTitle)+"']//span[contains(@class, 'x-window-header-text')]").getText();
	}
	
	/**
	 * Helper method to get all menu items text which are currently active
	 * @return list of all menu items text
	 */
	public List<String> getAllActiveMenuItemsText(){
		
		List<String> menuText = new ArrayList<String>();
		
		List<WebElement> menus = driver.findElements(By.xpath("//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//span[@class='x-menu-item-text']"));
		
		for(WebElement menu : menus){
			
			String menuItemText = menu.getText().trim();
			
			menuText.add(menuItemText);
		}
		
		return menuText;
	}
	
	/**
	 * Helper method to get Dates of all days in current week
	 * @return list of Dates of all days in current week
	 */
	public List<String> getAllDatesInCurrentWeek(){
		
		Calendar calendar = Calendar.getInstance();
		 
		 calendar.setTime(new Date());
		 
		 SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

		    List<String> days = new ArrayList<String>();
		    int delta = -calendar.get(GregorianCalendar.DAY_OF_WEEK) + 2;
		    calendar.add(Calendar.DAY_OF_MONTH, delta );
		    for (int i = 0; i < 7; i++)
		    {
		        days.add(format.format(calendar.getTime()));
		        calendar.add(Calendar.DAY_OF_MONTH, 1);
		    }
	
		    return days;
	}
	
	/**
	 * Helper method to convert date in dd-MM-yyyy format to required format 
	 * @param date to convert in dd-MM-yyyy Example: 24-06-2015 
	 * @param dateFormat : required format
	 * @return date in required format
	 */
	public String formatDateInToRequiredFormat(String date, String dateFormat){
		
		Calendar calendar = Calendar.getInstance();

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		Date dateOfDayInWeek= null; 

		try {

			dateOfDayInWeek = formatter.parse(date);

		}catch (ParseException e) {

			Reporter.log(date+" passed is not in dd-MMM-yyyy format", true);
		}

		calendar.setTime(dateOfDayInWeek);

		SimpleDateFormat format = new SimpleDateFormat(dateFormat);

		return format.format(calendar.getTime());
			
	}
	
	/**
	 * Helper method to get the list of hours in a day as List of 24hrs in HH:00 format
	 * @return List of 24hrs in HH:00 format 
	 */
	public List<String> getListOfHoursInDay(){


		Calendar calendar = Calendar.getInstance();

		calendar.setTime(new Date());

		SimpleDateFormat format = new SimpleDateFormat("HH:00");

		List<String> hoursInDay = new ArrayList<String>();

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		for (int i = 0; i < 24; i++)
		{
			hoursInDay.add(format.format(calendar.getTime()));
			calendar.add(Calendar.HOUR_OF_DAY, 1);
		}

		return hoursInDay;
	}
	
	/**
	 *  Helper method to get the list of working hours of a day in HH:00 format
	 * @return Working hours in a day
	 */
	public List<String> getListOfWorkingHoursInDay(){

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		SimpleDateFormat format = new SimpleDateFormat("HH");

		List<String> workinghours = new ArrayList<String>();

		calendar.set(Calendar.HOUR_OF_DAY, 8);
		for (int i = 0; i < 9; i++)
		{
			workinghours.add(format.format(calendar.getTime()));
			calendar.add(Calendar.HOUR_OF_DAY, 1);
		}

		return workinghours;
	}
	
	/**
	 * Helper method to get Messaged from dialog box
	 */
	public String getExtMbContentOfWindow(String attribute, String attrVal){
		
		String xpath = "//div[contains("+attribute+",'"+attrVal+"')]//div[contains(@class,'ext-mb-content')]/span";
		 
		return McsElement.getElementByXpath(driver, xpath).getText();
	}
	
	/**
	 * Helper method to return logged in User name in LastName FirstName format
	 * @return User name in LastName FirstName format
	 */
	public String getUserNameOfLoggedInUser(){
		
		String xpath = "//div[contains(@class,'mcs-tb-glossy-strong')]//table[@id='top-account-menu']//button";
		
		return McsElement.getElementByXpath(driver, xpath).getText();
	}
	
	/**
	  * Helper method to close Channel Measurements using Tool Close (X button)
	  */
	
	 public void closeUsingToolBar(String xwindowXpath) {
	  WebElement element = driver.findElement(By.xpath(xwindowXpath+"//div[contains(@class, 'x-tool-close')]"));
	  element.click();
	  waitForExtJSAjaxComplete(20);
	  
	 }
	 
	 /**
	  * Helper method to get logged in user name in FirstName LastName format 
	  * @return user name in FirstName LastName format
	  */
	 public String getUserNameOfLoggedInUserFirstNameLastNameFormat(){
		 
		 String userName = getUserNameOfLoggedInUser();
		 
		 String formatedName = userName.split(" ")[1]+" "+userName.split(" ")[0];
		 
		 return formatedName;
		 
	 }
	 
	 /**
		 * Helper method to return logged in User name in LastName FirstName format
		 * @return User name in LastName format
		 */
		public String getUserLastNameOfLoggedInUser(){
			
			String lastName = getUserNameOfLoggedInUser();
			String formatedName = lastName.split(" ")[0];
			return formatedName;
		}
		
		 /**
		 * Helper method to return logged in User name in first name FirstName format
		 * @return User name in first name format
		 */
		public String getUserFirstNameOfLoggedInUser(){
			
			String lastName = getUserNameOfLoggedInUser();
			String formatedName = lastName.split(" ")[1];
			return formatedName;
		}
	 
	 
	 /**
	 * Helper method to close WorkOrder using Tool Close (X button) Included Visibility
	 */
	public void closeUsingToolBarJS(String windowTitle) {
		WebElement element = driver.findElement(By.xpath("//div[contains(@id, '"+getXWindowId(windowTitle)+"') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-tool-close')]"));
		
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", element);
            } catch(Exception e) {
            	e.printStackTrace();
            }
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		waitForExtJSAjaxComplete(20);
		Reporter.log("Closed the Window using Tool Close button", true);
	}
	
	/**
	 * Helper method to Change Visible Columns
	 * @param attributeHD Example: gp-group-Maintenance-hd (Maintenance Header)
	 * @param attributeBD Example: group-Maintenance-bd (Maintenance Body)
	 * @param attributeName Example: gp-group-Maintenance(Maintenance)
	 * @param fieldValue Example: Compliance Level
	 */
	public void changeVisibleColumns(String attributeHD, String attributeBD, String attributeName, String fieldValue){
		//Expand Properties
		String xwindowTitle = DEFAULT_CHANGE_VISIBLE_COLUMNS_HEADER;

		String mainWinXpath  = "//div[contains(@id, '"+getXWindowId(xwindowTitle)+"') and contains(@style, 'visibility: visible')]";

		String grpXpath = mainWinXpath+"//div[contains(@id, '-gp-group-"+attributeHD+"') and contains(@class,'x-grid-group-hd')]/..";

		WebElement ele = driver.findElement(By.xpath(grpXpath));

		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String element = ele.getAttribute("class");

		if(element.contains("x-grid-group-collapsed")) {

			McsElement.getElementByXpath(driver, grpXpath+"//div[@class='x-grid-group-title']").click();
		}

		waitForExtJSAjaxComplete(10);

		String propertyBodyXpath= mainWinXpath+"//div[contains(@class, 'x-grid3-body')]//div[contains(@id, '"+attributeBD+"-bd')]";

		String propertyGridRow = propertyBodyXpath+"//div[contains(@class, 'x-grid3-col-1')and text()='"+fieldValue+"']/ancestor::div[contains(@class, 'x-grid3-row')]";

		//Check Properties
		String propertyGridRowEleClass = driver.findElement(By.xpath(propertyGridRow)).getAttribute("class");

		if(propertyGridRowEleClass.contains("row-selected")){
			Reporter.log(fieldValue+" is already checked", true);
		} else{

			String xpath = propertyBodyXpath+ "//div[contains(@class, 'x-grid3-col-1')and text()='"+fieldValue+"']/../..//div[@class='x-grid3-row-checker']";

			driver.findElement(By.xpath(xpath)).click();
		}

		waitForExtJSAjaxComplete(5);
	}
		
	/**
	 * Helper method to Change Visible Columns
	 * @param attributeHD Example: gp-group-Maintenance-hd (Maintenance Header)
	 * @param attributeBD Example: group-Maintenance-bd (Maintenance Body)
	 * @param attributeName Example: gp-group-Maintenance(Maintenance)
	 * @param fieldValue Example: Compliance Level
	 */
	public void changeVisibleColumns(String xwindowTitle, String attributeHD, String attributeBD, String attributeName, String fieldValue){
		//Expand Properties
		String xpath ="//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//div[contains(@id, '"+attributeHD+"')]/ancestor::div[contains(@id, '"+attributeName+"')]";
		
		WebElement ele = driver.findElement(By.xpath(xpath));
		
		try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		String element = ele.getAttribute("class");
		if(element.contains("x-grid-group-collapsed")) {
			McsElement.getElementByXpath(driver, xpath+"//div[@class='x-grid-group-title']").click();
		}
		
		waitForExtJSAjaxComplete(10);
		
		//Check Properties
		WebElement elementToBeChecked = driver.findElement(By.xpath("//div[contains(@id,'"+getXWindowId(xwindowTitle)+"') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-grid3-body')]//div[contains(@id, '"+attributeBD+"') and contains(@class, 'group-body')]//div[contains(@class, 'x-grid3-col-1')and text()='"+fieldValue+"']"));
		
		String elementToBeCheckedClassValue = driver
				.findElement(
						By.xpath("//div[contains(@id,'"
								+ getXWindowId(xwindowTitle)
								+ "') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-grid3-body')]//div[contains(@id, '"+attributeBD+"') and contains(@class, 'group-body')]//div[contains(@class, 'x-grid3-col-1')and text()='"
								+ fieldValue
								+ "']/ancestor::div[contains(@class, 'x-grid3-row')]"))
				.getAttribute("class");
		
		if(elementToBeCheckedClassValue.contains("row-selected")){
			Reporter.log(fieldValue+" is already checked", true);
		} else{
			elementToBeChecked.click();
		}
		waitForExtJSAjaxComplete(5);
	}
	
	/**
	 * Helper method to get 5 mins before time of Current system time
	 * @return
	 */
	public String getMinsEarlyOfSystemTime(int no){
		 //create Calendar instance
	    Calendar now = Calendar.getInstance();
	   
	    System.out.println("Current time : " + now.get(Calendar.HOUR_OF_DAY)
	                      + ":"
	                      + now.get(Calendar.MINUTE));
	                     
	    
	    //substract minutes from current date using Calendar.add method
	    now = Calendar.getInstance();
	    now.add(Calendar.MINUTE, no);
	 
	    System.out.println("Time before "+no+" minutes : " + now.get(Calendar.HOUR_OF_DAY)
	                      + ":"
	                      + now.get(Calendar.MINUTE)
	                      );
	     Format formatter = new SimpleDateFormat("HH:mm");
	    System.out.println(formatter.format(now.getTime()));
	 
	   return formatter.format(now.getTime());
	}
	
	/**
	 * Helper method to return url without ?aqa parameter in it
	 * @return url without ?aqa parameter in it
	 */
	public String getURLWithoutAQAParam(){
		
		boolean hasAQAParamInUrl = configuration.getApplicationUrl().contains("?aqa");
		
		return (hasAQAParamInUrl)?configuration.getApplicationUrl().replace("?aqa", "") :configuration.getApplicationUrl() ;
		
	}
	
	/**
	 * Helper method to sort columns Ascending and Descending in the region lookup
	 * @param attribute : pass @attribute used to identify parent element
	 * @param attributeValue :pass @attribute value used to identify parent element
	 * @param columnName : columnName of the row
	 * @param options : pass options like sort Ascending  or sort Descending
	 */
	public void sortColumnsAscDec(String attribute, String attributeValue, String columnName, String options){
		String columnClass = McsElement
				.getElementByXpath(driver,"//div[contains("+attribute+",'"+ attributeValue+"')]//div[contains(@class,'x-grid3-header')]//div[contains(@class,'x-grid3-header-inner')]//div[text()='"+columnName+"']").getAttribute("class");

		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);

		waitForExtJSAjaxComplete(20);

		try {

			WebElement we = driver.findElement(By.xpath("//div[contains("+attribute+", '"+attributeValue+"')]//div[contains(@class,'x-grid3-header')]//div[contains(@class,'x-grid3-header-inner')]//div[contains(@class, 'x-grid3-hd-"+columnNumber+"')]/..//a[contains(@class,'x-grid3-hd-btn')]"));

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", we);

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", we);
			waitForExtJSAjaxComplete(20);

		} catch (Exception e) {
			e.printStackTrace();
		}

		waitForExtJSAjaxComplete(10);

		driver.findElement(By.xpath("//div[contains(@class, 'x-menu-floating') and contains(@style, 'visibility: visible')]//ul[contains(@class,'x-menu-list')]//span[contains(@class, 'x-menu-item-text') and text()='"+options+"']")).click();
		waitForExtJSAjaxComplete(10);
	}

	/**
	 * Helper method to verify whether required columns present in the grid
	 * @param attribute : pass @attribute used to identify parent element
	 * @param attributeValue :pass @attribute value used to identify parent element
	 * @param columnName : columnName of the row
	 */
	public boolean verifyColumnsInGrid(String attribute, String attributeValue,String colName){
		try{
			driver.findElement(By.xpath("//div[contains("+attribute+", '"+attributeValue+"')]//div[contains(@class,'x-grid3-header')]//div[contains(@class,'x-grid3-hd') and contains(text(),'"+colName+"')]"));
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}

		return false;
	}


	public boolean setValueGridLookupWithFiltersWithValidationWithScroll(String attribute, String attributeValue, String rowTextName, String columnName){
		Timer timer = new Timer().start();

		filterGrid(attribute, attributeValue, rowTextName, columnName);

		String xpath1 = "//div[contains(@class,'resizable')]//div[contains(@class,'x-grid3-hd') and contains(text(),'"+columnName+"')]";

		WebElement element = driver.findElement(By.xpath(xpath1));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch(Exception e){
			e.printStackTrace();
		}
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath1)));
		String columnClass = element.getAttribute("class");


		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);

		try {
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

			//TODO: THis code is implemented as text() matching fails for elements which contains space in its text.
			String xpath ="(//"+"div"+"[contains("+"@class"+",'" + "x-window x-window-noborder x-resizable-pinned" + "')]//"
					+"div"+"[contains("+"@class"+",'" + "x-grid3-cell-inner x-grid3-col-"+columnNumber + "')"
					//+ "and "+"text()"+ "='" + rowTextName + "'" + 
					+"])";//[last()]

			List<WebElement> searchResults = driver.findElements(By.xpath(xpath));

			if(searchResults.size()==0){

				Reporter.log("No search results found for filtering criteria ", true);
				throw new NoSuchElementException("No search results found for filtering criteria");

			}

			for(WebElement ele: searchResults){

				if(ele.getText().trim().equals(rowTextName)){

					Actions actions = new Actions(driver);
					actions.moveToElement(ele).click().perform();

					//ele.click();
					break;
				}
			}

			/*		McsElement
			.getElementByPartAttributeValueAndParentElement(driver,
					"div", attribute, attributeValue, "div",
					"@class", "x-grid3-cell-inner x-grid3-col-"+columnNumber,
					"text()", rowTextName, false, false).click();*/

			waitForExtJSAjaxComplete(25);

			clickOkXwindow();

			Reporter.log(rowTextName + " is present in grid and selected"+ " (" + timer.stop()
			+ "ms)", true);
			return true;
		}
		catch(Exception e){
			Reporter.log(rowTextName + " is not present"+ " (" + timer.stop()
			+ "ms)", true);
			return false;
		}
		finally {
			try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
		}
	}
	
	/**
	 * Helper method to get no of days from days from Specified date
	 * @param date specific date 
	 * @param format required 
	 * @param noOfDays 
	 * @return
	 * @throws ParseException
	 */
	  public String getNoOfDaysFromSpecifiedDate(String date, String format, int noOfDays) throws ParseException {
          DateFormat dateFormat = new SimpleDateFormat(format);
          Date myDate = dateFormat.parse(date);
          Calendar cal = Calendar.getInstance();
          cal.setTime(myDate);
          cal.add(Calendar.DAY_OF_YEAR, noOfDays);
          Date previousDate = cal.getTime();
          String result = dateFormat.format(previousDate);
          return result;
	  }

	  /**
	   * Helper method to close Administration Window
	   */
	  public void closeModule(String moduleWindowTitle){
		  driver.findElement(By.xpath("//div[contains(@class,'x-tab-strip-wrap')]//span[text()='"+moduleWindowTitle+"']//..//..//..//..//a[@class='x-tab-strip-close']")).click();
		  Reporter.log(""+moduleWindowTitle+" tab is closed. <br>", true);
	  }

	  /**
	   * Helper method to expand Master data
	   */
	  public void expandMasterData() {
		  expandNode("div","@id",getXPanelId("Administration"),"Master Data");
	  }

	  /**
	   * Helper method to get current window ID
	   * @return window id
	   */
	  public String getCurrentWindowHandle(){
		  return driver.getWindowHandle();
	  }
	  
	 /**
	 * Helper method to click on Administration
	 */
	public void clickAdministration() {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "mcs-tb-glossy-strong", "button", "text()",
				"Administration", true, true).click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click Administration"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	/**
	 * Helper method to click on Accounts Options Administration
	 */
	public void clickAdministrationOptions(String parentNode, String childNode) {
		WebElement node =McsElement.getElementByXpath(driver,"//div[@id='"+getXPanelId("Administration")+"']//span[text()='"+parentNode+"']/../../..//span[text()='"+childNode+"']");
		javaScriptFocus(node);
		node.click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Clicked on Node "+childNode, true);
	}
	
	/**
	 * Helper method to expand Module Settings
	 */
	public void expandAdministrationOptions(String option) {
		expandNode("div","@id",getXPanelId("Administration"), option);
	}
	
/*****************************************New Lookup code************************/
	
	/**
	 * Method to retrieve values of lookup from grid in lookup
	 * @param attribute - attribute (@class, @id) of the lookup window
	 * @param attributeValue - attribute of the lookup must contain this value
	 * @columnName - columnName of the values to be retrieved
	 */
	public List<String> getValuesFromGridLookupNewUI(String attribute, String attributeValue, String columnName) {
		Timer timer = new Timer().start();
		
		waitForExtJSAjaxComplete(25);
		
		String columnID = McsElement
		.getLastElementByPartAttributeValueAndParentElement(driver,
				"div", attribute, attributeValue,
				"div","@role", "columnheader",
				".", columnName, true, true).getAttribute("id");
		
			
		List<WebElement> values = driver.findElements(By.xpath("//"+"div"+"[contains("+attribute+",'" + attributeValue + "')]//"
		+"td"+"[contains("+"@class"+",'" + "x6-grid-cell-"+columnID + "')"
		+ "]"));
			
		ArrayList<String> lsValues = new ArrayList<String>(); 
		
		for(int i=0;i<values.size(); i++){
			
			String valueText = values.get(i).getText().trim();
			
			lsValues.add(valueText);
			
		}
		
			
		Reporter.log(columnName + "values are retrieved"+ " (" + timer.stop()+ "ms)", true);
		
		return lsValues;

	}
	
	

	
	/**
	 * Helper method to get ID of new Lookup Windows 
	 * @param xwindowTitle - title of the x-window 
	 * @return id of the x-window 
	 * @throws NoSuchElementException
	 */
	public String getNewXWindowID(String xwindowTitle) throws NoSuchElementException {
		
		String elementXpath = "(//div[contains(@class, 'x6-window-closable')]//div[contains(@id,'header-title') and contains(text(),'"
				+ xwindowTitle + "')])[last()]/../../../../..";

		WebElement webElement = new WebDriverWait(driver, configuration.getImplicitWait())
				.until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath(elementXpath)));
		
		
		return webElement.getAttribute("id");
	}


	/**
	 * Method allows to click on lookup button on the form for some input
	 * @param  formClass - class of the window where lookup is present
	 * @param inputName - name of the input for which lookup is opened 
	 */
	public void clickLookupNewUI(String formClass, String inputName){
		Timer timer = new Timer().start();
		int xwindowNumber=0;
		
		try {driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			xwindowNumber = driver.findElements(By.xpath("//div[contains(@class,'x6-window-closable')]")).size();
		}
		finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);}

		waitFocusAndClick("//div[contains(@class,'"
				+ formClass + "')]//input[contains(@name,'"
				+ inputName + "')]//..//..//button");
		
		waitForExtJSAjaxComplete(5);
		waitForExtJSAjaxComplete(5);

		try {driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			if (driver.findElements(By.xpath("//div[contains(@class,'x6-window-closable')]")).size() == xwindowNumber)
				{waitAndClick("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");}
		}

		 finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
		}

		Reporter.log(inputName + " lookup was clicked"+ " (" + timer.stop()
				+ "ms)", true);
		
	}
	



	
	/**
	 * Method allows to click on lookup button on the form for some input
	 * @param  parentAttributeValue - parent attribute (@class, @id) of the form where lookup is present
	 * @param  parentAttributeValue - value of parent attribute
	 * @param  childWindowHeader - header of the child window which should appear
	 * @param  inputName - name of the input for which lookup is opened 
	 */	
	public void clickLookupNewUI(String parentAttribute, String parentAttributeValue, String inputName, String childWindowHeader){
		Timer timer = new Timer().start();

		waitFocusAndClick("//div[contains("+parentAttribute+",'"
				+ parentAttributeValue + "')]//input[contains(@name,'"
				+ inputName + "')]//..//..//button");

		try {
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(By
					.xpath("//div[contains(@id,'"
				+getNewXWindowID(childWindowHeader) + "')]"));
		}

		catch (Exception e) {
			waitAndClick("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");
		} finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);
		}
		waitForExtJSAjaxComplete(10);

		Reporter.log(inputName + " lookup was clicked"+ " (" + timer.stop()
				+ "ms)", true);
		
		
	}
	
		
	/**
	 * Method allows to select row from grid in lookup using filters
	 * 
	 * @param rowTextName - row text to be selected from lookup 
	 * 
	 * @columnName - columnName of the row to be selected
	 */
	public void setValueGridLookupWithFiltersNewUI(String rowTextName, String columnName) {
		
		setValueGridLookupWithFiltersNewUI("@class", "x6-window-closable", rowTextName, columnName);
		
	}
	

	
	
	
	/**
	 * Method allows to filter in grid by text
	 * @param attribute - attribute (@class, @id) of the grid container
	 * @param attributeValue - corresponding value of the grid container attribute
	 * @param rowTextName - row text to be filtered by 
	 * @columnName - columnName of the row 
	 */
	public void filterGridNewUI(String attribute, String attributeValue, String rowTextName, String columnName) {
		Timer timer = new Timer().start();
		
		try{

			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			
			List<WebElement> columnHeaders = driver.findElements(By.xpath("//div[contains("+attribute+",'"+ attributeValue+"')]//div[@role='columnheader']"));
			
			List<WebElement> columnFilter = driver.findElements(By.xpath("//div[contains("+attribute+",'"+ attributeValue+"')]//input[@role='textbox']"));
			
			
			for(int i=0; i<columnHeaders.size(); i++){
				
				String colHeader = columnHeaders.get(i).getText().trim();
				
				if(colHeader.equals(columnName)){
					
					WebElement filterInput = columnFilter.get(i);
					
					filterInput.clear();
					
					filterInput.sendKeys(rowTextName);
					
					break;
					
				}
				
			}
		}finally{

			driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);

		}


		waitForExtJSAjaxComplete(20);		

		//TODO: Uncomment if necessary
		//McsElement.getElementByXpath(driver, "(//div[contains("+attribute+",'"+attributeValue+"')]//"
		//		+ "div[contains(@class,'x6-grid-body')])[last()]/div[contains(@data-componentid,'treeview')]").click(); 

		waitForExtJSAjaxComplete(5);

		Reporter.log(rowTextName + " was filtered"+ " (" + timer.stop()
				+ "ms)", true);

	}
	
	
	
	/**
	 * Method allows to select row from grid in lookup using filters
	 * @param attribute - attribute (@class, @id) of the lookup window
	 * @param attributeValue - attribute of the lookup must contain this value
	 * @param rowTextName - row text to be selected from lookup 
	 * 
	 * @columnName - columnName of the row to be selected
	 */
	public void setValueGridLookupWithFiltersNewUI(String attribute, String attributeValue, String rowTextName, String columnName) {
		Timer timer = new Timer().start();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		filterGridNewUI(attribute, attributeValue, rowTextName, columnName);	
		
		waitForExtJSAjaxComplete(25);
		
		String columnID = McsElement
		.getElementByPartAttributeValueAndParentElement(driver,
				"div", attribute, attributeValue,
				"div","@role", "columnheader",
				".", columnName, true, true).getAttribute("id");
		
				
		waitForExtJSAjaxComplete(10);
		
		String xpath ="(//"+"div"+"[contains("+"@class"+",'" + "x6-window-closable" + "')]//"
				+"td"+"[contains("+"@class"+",'" + "x6-grid-cell-"+columnID + "')"
				+"])";

		List<WebElement> searchResults = driver.findElements(By.xpath(xpath));

		if(searchResults.size()==0){

			Reporter.log("No search results found for filtering criteria ", true);
			throw new NoSuchElementException("No search results found for filtering criteria");

		}

		for(WebElement ele: searchResults){

			if(ele.getText().equals(rowTextName)){

				ele.click();
				break;
			}
		}

	

		waitForExtJSAjaxComplete(10);
			
		clickButtonInLookupNewUI("OK");
		
		Reporter.log(rowTextName + " was selected"+ " (" + timer.stop()
					+ "ms)", true);

	}
	
	
	/**
	 * Method allows to select row from grid in lookup (row with text should be visible in any column)
	 * 
	 * @param parentAttribute - x-window atribute  (@class, @id) 
	 *  
	 * @param parentAttributeValue - value of this attribute
	 * 
	 * @param rowTextName - row text to be selected from lookup 
	 */
	public void setValueGridLookupNewUI(String parentAttribute, String parentAttributeValue, String rowTextName) {
		Timer timer = new Timer().start();
		
		String []subStrings = rowTextName.split(" ");

		String subXpathStr = "and starts-with(text(),'"+subStrings[0]+"')";

		for(int i=1; i<subStrings.length; i++){

			subXpathStr+="and contains(text(),'"+subStrings[i]+"')";
		}
		
		String elementXpath = "//div[contains("+parentAttribute+",'"+parentAttributeValue
				+"')]//div[contains(@class,'x6-grid-cell-treecolumn')"+subXpathStr+"]";
		 
	
		WebElement firstRowWithText = driver.findElement(By.xpath(((elementXpath))));
	
		firstRowWithText.click();

		clickButtonInLookupNewUI("OK");
		
		Reporter.log(rowTextName + " was selected"+ " (" + timer.stop()
					+ "ms)", true);

	}


	
	/**
	 * Method allows to select row from grid in lookup (row with text should be visible in any column)
	 * 
	 * @param rowTextName - row text to be selected from lookup 
	 */
	public void setValueGridLookupNewUI(String rowTextName) {
	
		setValueGridLookupNewUI("@class", "x6-window-closable", rowTextName);
		
		
	}
	

	/**
	 * Helper method to click  buttons in lookup
	 * @param button :  OK/Cancel/Show hierarchy/Clear Filters
	 */
	public void clickButtonInLookupNewUI(String button){

		String xpath = "//div[contains(@class,'x6-window-closable')]//a[.='"+button+"']";

		McsElement.getElementByXpath(driver, xpath).click();

	}

	/**
	 * Method allows to select row from grid in lookup using filters
	 * @param attribute - attribute (@class, @id) of the lookup window
	 * @param attributeValue - attribute of the lookup must contain this value
	 * @param rowTextName - row text to be selected from lookup 
	 * @param columnName - columnName of the row to be selected
	 * @return present(true)/not present(false)
	 */
	public boolean setValueGridLookupWithFiltersNewUIWithValidation(String attribute, String attributeValue, String rowTextName, String columnName) {
		Timer timer = new Timer().start();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		filterGridNewUI(attribute, attributeValue, rowTextName, columnName);	
		waitForExtJSAjaxComplete(25);

		String columnID = McsElement
				.getElementByPartAttributeValueAndParentElement(driver,
						"div", attribute, attributeValue,
						"div","@role", "columnheader",
						".", columnName, true, true).getAttribute("id");
		waitForExtJSAjaxComplete(10);

		try{
			String xpath ="(//"+"div"+"[contains("+"@class"+",'" + "x6-window-closable" + "')]//"
					+"td"+"[contains("+"@class"+",'" + "x6-grid-cell-"+columnID + "')"
					+"])";

			List<WebElement> searchResults = driver.findElements(By.xpath(xpath));
			if(searchResults.size()==0){

				Reporter.log("No search results found for filtering criteria ", true);
				throw new NoSuchElementException("No search results found for filtering criteria");

			}

			for(WebElement ele: searchResults){

				if(ele.getText().equals(rowTextName)){

					ele.click();
					break;
				}
			}

			waitForExtJSAjaxComplete(10);

			clickButtonInLookupNewUI("OK");
			Reporter.log(rowTextName + " was selected"+ " (" + timer.stop()
			+ "ms)", true);

			Reporter.log(rowTextName + " is present in grid and selected"+ " (" + timer.stop()
			+ "ms)", true);
			return true;
		}

		catch(Exception e){
			Reporter.log(rowTextName + " is not present"+ " (" + timer.stop()
			+ "ms)", true);
			return false;
		}

		finally {
			try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
		}
	}

	/**
	 * Method allows to get dynamical id of the modal window when multiple modal dialogs are present
	 * returns x-window id 
	 * 
	 * @param xwindowTitle - title of the x-window 
	 */
	public String getWindowIdBasedOnPartialMatch(String xwindowTitle) throws NoSuchElementException {


		String winXpaths = "//div[contains(@class,'x-window x-resizable-pinned') and contains(@style,'visibility: visible')]";

		List<WebElement> openWinList = driver.findElements(By.xpath(winXpaths));

		String id = "";

		for(WebElement win: openWinList ){

			String winTitle = win.findElement(By.xpath(".//*[@class='x-window-header-text']")).getText();

			if(winTitle.contains(xwindowTitle)){

				id = win.getAttribute("id");

				break;

			}
		}

		return id;
	}

	/**
	 * Helper method to find the active module name 
	 * @return module name
	 */
	public String getActiveModuleName(){
		String activeModule = driver.findElement(By.xpath("//div[@id='portalcontainer_tabs']//li[contains(@class,' x-tab-strip-active') and contains(@id,'portalcontainer')]")).getText();
		Reporter.log(""+activeModule+" is active", true);
		return (activeModule.trim());
	}

	/**
	 * Helper method to get panel window header 
	 * @param attribute of the panel @class, @id
	 * @param attributeValue panel must contain this value 
	 * @return
	 */
	public String getXPanelWindowHeader(String attribute, String attributeValue){
		return driver.findElement(By.xpath("//div[contains("+attribute+",'"+attributeValue+"')]//span[contains(@class,'x-panel-header-text')]")).getText().trim();
	}
	
	 /**
	  * Navigate to home page and relogin
	  * @param NAME_FOR_RIGHT to log into
	  * @param PASSWORD_FOR_RIGHT password 
	  */
	 public void navigateToMainPage(String NAME_FOR_RIGHT,String PASSWORD_FOR_RIGHT) {
		 Timer timer = new Timer().start();
		 //	forceLogout();
		 logout();
		 waitForExtJSAjaxComplete(25);
		 waitForExtJSAjaxComplete(25);
		 login(NAME_FOR_RIGHT, PASSWORD_FOR_RIGHT);
		 waitForExtJSAjaxComplete(25);
		 waitForExtJSAjaxComplete(25);
		 Reporter.log("Login to back-end under account "+NAME_FOR_RIGHT +" END\n", true);
		 Reporter.log("<br />");
		 Reporter.log("Logged in to the Portal"+ " (" + timer.stop() + "ms)\n", true);
		 Reporter.log("<br />");
	 }
	 
	 /**
		 * Helper method to collapse Navigation
		 */
		public void collapseNavigation(){
			String expandedMode  = "//div[@id='portalcontainer_navigation' and not(contains(@class,'x-panel-collapsed'))]//div[contains(@class,'x-tool-collapse-west')]";
			String collapsedMode = "//div[@id='portalcontainer_navigation-xcollapsed' and contains(@style,'visibility: visible')]//div[contains(@class,'expand-west')]";
			if(!McsElement.isElementPresent(driver, collapsedMode)){
				driver.findElement(By.xpath(expandedMode)).click();
			}
		}

		 /**
		  * Helper method to get xpath for text
		  * @param text
		  * @return xpath
		  */
		 public static String xpathGeneratorForTextElement(String text){
			  
			  String subStrings[] = text.split(" ");
			  
			  String xpath = "//div[starts-with(text(),'"+subStrings[0]+"')";
			  
			  for(int i=1; i<subStrings.length; i++){

			   xpath+="and contains(text(),'"+subStrings[i]+"')";
			  }
			  
			  return xpath+"]";
			  
			 }
		 
		 /**
			 * Helper method to collapse Administration
			 */
			public void collapseAdministration(){
				String expandedMode  = "//div[contains(@class,'mcs-tree-navigation') and not(contains(@class,'x-panel-collapsed'))]//div[contains(@class,'x-tool-collapse-west')] ";
				String collapsedMode = "//div[contains(@class,'admsettings-modulesettings')]//div[contains(@class,'x-layout-collapsed-west') and contains(@style,'visibility: visible')]//div[contains(@class,'x-tool-expand-west')]";
				if(!McsElement.isElementPresent(driver, collapsedMode)){
					driver.findElement(By.xpath(expandedMode)).click();
					Reporter.log("Administration panel is collapsed",true);
				}
			}
			

}

