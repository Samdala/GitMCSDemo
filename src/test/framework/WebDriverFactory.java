package test.framework;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;

import test.configuration.Configuration;

public class WebDriverFactory {

	/**
	 * Get WebDriver instance
	 * 
	 * @param configuration
	 * @return
	 * @throws Exception
	 */
	public static WebDriver getWebDriver(Configuration configuration)
			throws Exception {

		WebDriver driver = null;

		// Initialize Firefox RemoteWebDriver
		if ("firefox".equals(configuration.getBrowser())
				&& "remote".equals(configuration.getDriverMode())) {

			driver = getDesiredRemoteWebBrowser(configuration);

			maximizeWindow(driver);

		}

		// Initialize Firefox local WebDriver
		if ("firefox".equals(configuration.getBrowser())
				&& "local".equals(configuration.getDriverMode())) {

			driver = new FirefoxDriver();

			// Set the default waiting time of the driver before the element is
			// loaded
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);

		

			// Get the Application start Url
			driver.get(configuration.getApplicationUrl());
			
			maximizeWindow(driver);

		}

		// Initialize IE RemoteWebDriver
		if ("internet explorer".equals(configuration.getBrowser())
				&& "remote".equals(configuration.getDriverMode())) {

			driver = getDesiredRemoteWebBrowser(configuration);

			maximizeWindow(driver);

		}

		// Initialize IE local WebDriver
		if ("internet explorer".equals(configuration.getBrowser())
				&& "local".equals(configuration.getDriverMode())) {

			// Create the InternetWebDriver instance
			DesiredCapabilities capabilities =  getDesiredCapabilities(configuration);

			 driver = new InternetExplorerDriver(capabilities);
					 
			maximizeWindow(driver);

			// Set the default waiting time of the driver before the element is
			// loaded
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);

			// Get the Application start Url
			driver.get(configuration.getApplicationUrl());

		}

		// Initialize HTML unit local WebDriver
		if ("htmlunit".equals(configuration.getBrowser())
				&& "local".equals(configuration.getDriverMode())) {

			// Create the InternetWebDriver instance
			driver = new HtmlUnitDriver(true);

		//	maximizeWindow(driver);

			// Set the default waiting time of the driver before the element is
			// loaded
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);

			// Get the Application start Url
			driver.get(configuration.getApplicationUrl());

		}

		
		
		if ("chrome".equals(configuration.getBrowser())
				&& "remote".equals(configuration.getDriverMode())) {

			driver = getDesiredRemoteWebBrowser(configuration);

			maximizeWindow(driver);

		}

		// Initialize Chrome local WebDriver
		if ("chrome".equals(configuration.getBrowser())
				&& "local".equals(configuration.getDriverMode())) {
                       
			
			
			ChromeOptions options = new ChromeOptions();
            options.addArguments("no-sandbox");
			options.addArguments("--disable-extensions");
            options.addArguments("--dns-prefetch-disable");
			
			// Create the InternetWebDriver instance
			driver = new ChromeDriver(options);

			maximizeWindow(driver);

			// Set the default waiting time of the driver before the element is
			// loaded
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);

			// Get the Application start Url
			driver.get(configuration.getApplicationUrl());

		}

		
				
		// Initialize Safari WebDriver and run local or remote

		if ("safari".equals(configuration.getBrowser())
				&& "local".equals(configuration.getDriverMode())) {

			driver = new SafariDriver();

			// Set the default waiting time of the driver before the element is
			// loaded
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);

			maximizeWindow(driver);

			// Get the Application start Url
			driver.get(configuration.getApplicationUrl());

		}

		// Initialize Firefox RemoteWebDriver
		if ("safari".equals(configuration.getBrowser())
				&& "remote".equals(configuration.getDriverMode())) {

			driver = getDesiredRemoteWebBrowser(configuration);

			maximizeWindow(driver);

		}
		
		
		if ("android".equals(configuration.getBrowser())
				&& "local".equals(configuration.getDriverMode())) {

			driver = new AndroidDriver();

			// Set the default waiting time of the driver before the element is
			// loaded
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);

		//	maximizeWindow(driver);

			// Get the Application start Url
			driver.get(configuration.getApplicationUrl());

		}
		
		if ("android".equals(configuration.getBrowser())
				&& "remote".equals(configuration.getDriverMode())) {
			
			DesiredCapabilities browser = DesiredCapabilities.internetExplorer();
		driver = new RemoteWebDriver(URI.create("http://localhost:8080/wd/hub").toURL(),browser);
			
			//maximizeWindow(driver);

		}
		

		return driver;
	}

	/**
	 * Initializes the RemoteWebDriver with desired configuration
	 * 
	 * @param configuration
	 *            The instance of Configuration
	 * @return The instance of driver
	 */
	private static WebDriver getDesiredRemoteWebBrowser(
			Configuration configuration) throws MalformedURLException {
		WebDriver driver;
		// Initialize the desired capabilities based on the passed configuration
		DesiredCapabilities capabilities = getDesiredCapabilities(configuration);

		// Set the Selenium Hub address
		URI hubAddress = URI.create(configuration.getHubUrl());

		// Create the RemoteWebDriver instance
		driver = new RemoteWebDriver(hubAddress.toURL(), capabilities);

		// Set the default waiting time of the driver before the element is
		// loaded
		driver.manage()
				.timeouts()
				.implicitlyWait(configuration.getImplicitWait(),
						TimeUnit.SECONDS);

		// Get the Application start Url
		driver.get(configuration.getApplicationUrl());
		return driver;
	}

	/**
	 * Initializes the DesiredCapabilities class, which is used for building the
	 * WebDriver
	 * 
	 * @param configuration
	 *            The instance of Configuration
	 * @return The instance of DesiredCapabilities
	 */
	private static DesiredCapabilities getDesiredCapabilities(
			Configuration configuration) {

		DesiredCapabilities capabilities = new DesiredCapabilities();

		// Set browser Javascript suport enabled to true
		capabilities.setJavascriptEnabled(true);

		if ("android".equals(configuration.getBrowser())) {

			capabilities = DesiredCapabilities.android();

		}
		
		// If desired browser Firefox set DesiredCapabilities to Firefox
		if ("firefox".equals(configuration.getBrowser())) {

			capabilities = DesiredCapabilities.firefox();

		}
		
		if ("chrome".equals(configuration.getBrowser())) {

			capabilities = DesiredCapabilities.chrome();

		}
		
		// If desired browser Safari set DesiredCapabilities to Safari
		if ("safari".equals(configuration.getBrowser())) {

			capabilities = DesiredCapabilities.safari();

		}

		// If desired browser IE set DesiredCapabilities to IE
		else if ("internet explorer".equals(configuration.getBrowser())) {
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		}

		// Set desired OS to capabilities
		Platform platformName = Platform.valueOf(configuration.getOsName());
		capabilities.setPlatform(platformName);

		// Set desired browser version to capabilities
		capabilities.setVersion(configuration.getBrowserVersion());

		return capabilities;
	}

	/**
	 * Maximize browser window
	 * 
	 * @param driver
	 */
	private static void maximizeWindow(WebDriver driver) {
		// Maximize window
		driver.manage().window().setPosition(new Point(0, 0));
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		Dimension dim = new Dimension((int) screenSize.getWidth(),
				(int) screenSize.getHeight());
		driver.manage().window().setSize(dim);

		// Another way to maximize window if first maximization does not
		// help
		driver.manage().window().maximize();

	}

}
