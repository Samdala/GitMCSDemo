
//=============================================================================
//
//Description   : 	Main class for get all configuration parameters
//

package test.configuration;

import java.io.InputStreamReader;
import java.util.Properties;
import org.testng.annotations.Optional;

public class Configuration {

	private String browser;
	private String browserVersion;
	private String osName;
	private String applicationUrl;
	private String hubUrl;
	private Integer implicitWait;
	private String driverMode;
	private String appLanguage;
	private String userName;
	private String password;
	private String testDataLocation;

	public static Configuration getConfiguration(@Optional String specificConfig)
			throws Exception {

		// Validate if configuration is defined
		if (specificConfig == null) {
			// If not - check in the command line arguments
			specificConfig = System.getProperty("specificConfig");
		}

		if (specificConfig == null) {
			// If not - check in the environment
			specificConfig = System.getenv("specificConfig");
		}

		if (specificConfig == null) {
			// If still undefined - set the default settings
			specificConfig = "defaultBrowser.properties";
		}

		// Validate if global properties file is defined
		String globalPropertiesFile = System
				.getProperty("globalPropertiesFile");

		if (globalPropertiesFile == null) {
			globalPropertiesFile = System.getenv("globalPropertiesFile");
		}

		if (globalPropertiesFile == null) {
			globalPropertiesFile = "global";
		}

		Configuration configuration = new Configuration();

		// Load the values from global configuration file
		Properties globalProperties = new Properties();
		globalProperties.load(new InputStreamReader(ClassLoader
				.getSystemResourceAsStream("test/configuration/"
						+ globalPropertiesFile + ".properties")));

		// Set values for global settings
		configuration.applicationUrl = globalProperties
				.getProperty("applicationUrl");
		configuration.hubUrl = globalProperties.getProperty("hubUrl");
		configuration.implicitWait = Integer.parseInt(globalProperties
				.getProperty("implicitWait"));
		configuration.appLanguage = globalProperties.getProperty("appLanguage");
		configuration.userName = globalProperties.getProperty("userName");
		configuration.password = globalProperties.getProperty("password");
		configuration.testDataLocation =globalProperties.getProperty("testDataLocation");
		
		// Load the values from specific configuration file
		Properties browserProperties = new Properties();
		browserProperties.load(new InputStreamReader(ClassLoader
				.getSystemResourceAsStream("test/configuration/"
						+ specificConfig)));

		// Set values for specific settings
		configuration.browser = browserProperties.getProperty("browser");
		configuration.driverMode = globalProperties.getProperty("driverMode");
		configuration.browserVersion = browserProperties
				.getProperty("browserVersion");
		configuration.osName = browserProperties.getProperty("osName");

		return configuration;
	}

	public String getBrowser() {
		return browser;
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public String getOsName() {
		return osName;
	}

	public String getApplicationUrl() {
		return applicationUrl;
	}

	public String getHubUrl() {
		return hubUrl;
	}

	public Integer getImplicitWait() {
		return implicitWait;
	}

	public String getDriverMode() {
		return driverMode;
	}

	public String getAppLanguage() {
		return appLanguage;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getTestDataLocation() {
		return testDataLocation;
	}
	
}


