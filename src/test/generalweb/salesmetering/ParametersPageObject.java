/**
 * Page Object class for Metering status page
 * @author vpcc
 *
 */
package test.generalweb.salesmetering;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.webelement.McsElement;


public class ParametersPageObject extends SalesMeteringBasePageObject {

	protected final static String METER_STATUS_WIN_CLASS = "x-resizable-pinned";
	
	protected final static String METER_STATUS_XPATH = "//div[contains(@class,'x-resizable-pinned') and contains(@style,'visibility: visible')]";

	protected final static String METER_STATUS_CODE = METER_STATUS_XPATH+"//input[@name='code']";
	
	protected final static String METER_STATUS_REFERENCE = METER_STATUS_XPATH+"//input[@name='reference']";
	
	protected final static String METER_STATUS_CLASS = "class";
	
	protected final static String METER_STATUS_ISDEFAULT = METER_STATUS_XPATH+"//input[@name='isDefault']";
	
	protected final static String METER_STATUS_SAVE_BUTTON = METER_STATUS_XPATH+"//button[text()='Save']";
	
	protected final static String METER_STATUS_SAVEANDCLOSE_BUTTON = METER_STATUS_XPATH+"//button[text()='Save and Close']";
	
	protected final static String METER_STATUS_CLOSE_BUTTON = METER_STATUS_XPATH+"//button[text()='Close']";
	
	protected final static String METER_STATUS_RESET_BUTTON = METER_STATUS_XPATH+"//button[text()='Reset']";
	
	

	/**
	 * Helper method to Set code of meter status
	 * @param code
	 */
	public void setCode(String code) {

		WebElement codeEle=McsElement.getElementByXpath(driver, METER_STATUS_CODE);
		codeEle.clear();
		codeEle.sendKeys(code);
		Reporter.log("Set code as "+code, true);
	}

	/**
	 *  Helper method to Set reference of meter status
	 * @param reference
	 */
	public void setReference(String reference) {

		WebElement codeEle=McsElement.getElementByXpath(driver, METER_STATUS_REFERENCE);
		codeEle.clear();
		codeEle.sendKeys(reference);
		Reporter.log("Set reference as "+reference, true);
	}



	/**
	 * Helper method to set Class of Meter Status
	 * @param column to be used for value selection in lookup
	 * @param className :  Class to select
	 */
	public void setClass(String column, String className) {

		clickLookup("@class",METER_STATUS_WIN_CLASS, METER_STATUS_CLASS, "Select a Metering Object Status Class");
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Metering Object Status Class"), className, column);

		Reporter.log(className+" class was selected", true);

	}


	




}
