/**
 * Page Object class for Measurement type page
 * @author vpcc
 *
 */
package test.generalweb.salesmetering;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.webelement.McsElement;


public class MeasurementTypePageObject extends SalesMeteringBasePageObject {

	protected final static String MEASUREMENT_TYPE_WIN_CLASS = "x-resizable-pinned";
	
	protected final static String MEASUREMENT_TYPE_XPATH = "//div[contains(@class,'x-resizable-pinned') and contains(@style,'visibility: visible')]";

	protected final static String MEASUREMENT_TYPE_CODE = MEASUREMENT_TYPE_XPATH+"//input[@name='code']";
	
	protected final static String MEASUREMENT_TYPE_REFERENCE = MEASUREMENT_TYPE_XPATH+"//input[@name='reference']";
	
	
	protected final static String MEASUREMENT_TYPE_ISDEFAULT = MEASUREMENT_TYPE_XPATH+"//input[@name='isDefault']";
	
	protected final static String MEASUREMENT_TYPE_SAVE_BUTTON = MEASUREMENT_TYPE_XPATH+"//button[text()='Save']";
	
	protected final static String MEASUREMENT_TYPE_SAVEANDCLOSE_BUTTON = MEASUREMENT_TYPE_XPATH+"//button[text()='Save and Close']";
	
	protected final static String MEASUREMENT_TYPE_CLOSE_BUTTON = MEASUREMENT_TYPE_XPATH+"//button[text()='Close']";
	
	protected final static String MEASUREMENT_TYPE_RESET_BUTTON = MEASUREMENT_TYPE_XPATH+"//button[text()='Reset']";
	
	

	/**
	 * Helper method to Set code of meter status
	 * @param code
	 */
	public void setCode(String code) {

		WebElement codeEle=McsElement.getElementByXpath(driver, MEASUREMENT_TYPE_CODE);
		codeEle.clear();
		codeEle.sendKeys(code);
		Reporter.log("Set code as "+code, true);
	}

	/**
	 *  Helper method to Set reference of meter status
	 * @param reference
	 */
	public void setReference(String reference) {

		WebElement codeEle=McsElement.getElementByXpath(driver, MEASUREMENT_TYPE_REFERENCE);
		codeEle.clear();
		codeEle.sendKeys(reference);
		Reporter.log("Set reference as "+reference, true);
	}


	/**
	 * Helper method to check Default checkbox 
	 */
	public void checkDefault() {

		WebElement isDefaultEle = McsElement.getElementByXpath(driver, MEASUREMENT_TYPE_ISDEFAULT);

		if(!isDefaultEle.isSelected()){

			isDefaultEle.click();
		}

		Reporter.log("Check default", true);
	}


	

	




}
