// ============================================================================
//  
// Description   : Main class to initialize different desired browsers
//

package test.framework;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.io.TemporaryFilesystem;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.google.common.io.Files;
import com.thoughtworks.selenium.Selenium;

import test.configuration.Configuration;

public class AbstractTestSuite {

	protected static WebDriver driver;
	protected static Selenium selenium;
	protected static Configuration configuration = new Configuration();

	/**
	 * setUp method - start desired browser before test
	 */
	@BeforeTest
	@Parameters({ "specificConfig" })
	public void setUp(@Optional String specificConfig) throws Exception {
		Thread.sleep(3000);
		try{
		configuration = Configuration.getConfiguration(specificConfig);
		}catch(Exception e){
			
			System.out.println("Invalid configuration");
		}
		try{
		driver = WebDriverFactory.getWebDriver(configuration);
		}catch(Exception e){
			e.printStackTrace();
			
			InetAddress ip=null;
			try {
				ip = InetAddress.getLocalHost();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Couldnt instantiate WebDriver object: Check your configurations on VM"+ip.toString());
		}
		Reporter.log("new browser born-new test suite",true);

//		selenium = new WebDriverBackedSelenium(driver,
//				configuration.getApplicationUrl());

	}

	/**
	 * tearDown method - kill browser after test
	 */
	@AfterTest(alwaysRun = true)
	public void tearDown() throws Exception {
		Thread.sleep(3000);
		
		try{
		
			//TemporaryFilesystem.getDefaultTmpFS().deleteTemporaryFiles();
			
			String userName = System.getProperty("user.name");

			FileUtils.forceDelete(new File("C:\\Users\\"+userName+"\\AppData\\Local\\Temp"));
		
		}
		catch(Exception e){
			
			Reporter.log("Exception while clearing temp files", true);
			
		}
	
	
		try{
		driver.quit();
		}
		catch(Exception e){
			
			System.out.println("Couldnt close browser");
		}
		Thread.sleep(3000);
		Reporter.log("browser died - test suite end",true);

	}

	/**
	 * Method allow get screenshot of current browser page
	 * 
	 * @param methodName
	 *            string
	 * @throws IOException
	 * @return file with screenshot
	 * 
	 */
	public static File getScreenshot(String methodName) throws IOException {
		File screenshot = null;
		// to prevent "browser died" if there are too many issues and screenshots
		try {Thread.sleep(3000);} catch (InterruptedException e1) {}
		try {
			screenshot = File.createTempFile(methodName + "_screenshot"
					+ getCurrentDate(), ".png", new File("test-output"));
			if (configuration.getDriverMode().equals("remote")) {
				TakesScreenshot augmentedDriver = (TakesScreenshot) new Augmenter()
						.augment(driver);
				Files.copy(augmentedDriver.getScreenshotAs(OutputType.FILE),
						screenshot);
			} else {
				File scrFile = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
				Files.copy(scrFile, screenshot);
			}
			System.out.println(screenshot.getName());
			Reporter.log(" <a href=\"../../"
					+ screenshot.getName()
					+ "\">"
					+ " <img style='border-style:solid;border-width:0.5mm;border-color:gray' width=100 "
					+ "src=\"../../" + screenshot.getName() + "\"></a><br>");
			Reporter.log(" <a href=\"../../../../../ws/test-output/"
					+ screenshot.getName()
					+ "\">"
					+ " <img style='border-style:solid;border-width:0.5mm;border-color:gray' width=100 "
					+ "src=\"../../../../../ws/test-output/" + screenshot.getName() + "\"></a><br>");
			Reporter.log(" <a href=\""
					+ screenshot.getName()
					+ "\">"
					+ " <img style='border-style:solid;border-width:0.5mm;border-color:gray' width=100 "
					+ "src=\"" + screenshot.getName() + "\"></a><br>");
			Reporter.log(" <a href=\"../"
					+ screenshot.getName()
					+ "\">"
					+ " <img style='border-style:solid;border-width:0.5mm;border-color:gray' width=100 "
					+ "src=\"../" + screenshot.getName() + "\"></a><br>");

			
		} catch (IOException e) {
			Reporter.log("Error on taking screenshot");
			Reporter.log(e.toString());
		}
		return screenshot;
	}

	/**
	 * Method get current system date
	 * 
	 * @return formatted String with present time
	 * @throws IOException
	 */
	public static String getCurrentDate() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("_yyyy.MM.dd_HH.mm.ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	/**
	 * Method get current system date dd-mm-yyyy
	 * 
	 * @return formatted String with present time
	 * @throws IOException
	 */
	public static String getCurrentSystemDate() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * Method get current system date dd/mm/yyyy
	 * 
	 * @return formatted String with present time
	 * @throws IOException
	 */
	
	public static String getCurrentSystemDateFormat() throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	
	
	
	@AfterMethod//(alwaysRun = true)
	public void getAutoErrorScreenshot(ITestResult result) throws IOException {
		try {
			if (!result.isSuccess()) {
				getScreenshot(result.getMethod().getMethodName());
			}
		} catch (Exception e) {
			
			Reporter.log("getAutoErrorScreenshot():" +e.getMessage(),true);
		}
	}
	
	/**
	 * Helper method to caluclate future date
	 * @param noOfDays in future
	 * @return current date + noOfDays Date in dd-MM-yyyy format 
	 */
	public String getFutureDate(int noOfDays){
		
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		Date date = calendar.getTime();
		return dateFormat.format(date);
	}
	
	/**
	 * Helper method to calculate future Time
	 * @param hours in future
	 * @return current Time + hours Time in HH:mm format 
	 */
	public static String getNumberOfHrsFromCurrentSysTime(int hours) throws IOException {

		Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.HOUR_OF_DAY, hours);

		DateFormat dateFormat = new SimpleDateFormat("HH:mm");

		Date date = calendar.getTime();

		return dateFormat.format(date);
	}

	/**
	 * Helper method to get the week number
	 * @param date to be formated
	 * @return the date in word format of month + year 
	 * @throws Exception
	 */
	public String getFormatedWeekNumb(String date) throws Exception{
		SimpleDateFormat newDateFormat = new SimpleDateFormat("dd-MM-yyyy",Locale.ENGLISH);
		Date myDate1 = newDateFormat.parse(date);
		newDateFormat.applyPattern("MMMM yyyy");
		return  newDateFormat.format(myDate1);
	}

	/**
	 * Helper method to get week no based on the date passed
	 * @return the week number of day in a year 
	 * @throws Exception 
	 */
	public int getWeekNo(String date) throws Exception{
		Calendar c = Calendar.getInstance(Locale.US);
		SimpleDateFormat newDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date myDate1 = newDateFormat.parse(date);
		c.setTime(myDate1);
		int dayOfWeek = c.get(Calendar.WEEK_OF_YEAR);
		return dayOfWeek-1;
	}

}
