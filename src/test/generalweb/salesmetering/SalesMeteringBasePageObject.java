/**
 * This class acts as base for all Metering Page objects and contains library functions common across Metering module
 *@author vpcc
 */
package test.generalweb.salesmetering;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.framework.AbstractMcsTestSuite;
import test.framework.webelement.McsElement;


public class SalesMeteringBasePageObject extends AbstractMcsTestSuite {

	
	protected static final String SALESMETER_CLASS = "salesmeters";
	
	protected static final String SALESMETER_MENU_CLASS = "icon-btns";
	
	protected static final String SALESMETER_MENU_XPATH = "//div[contains(@class,'salesmeters')]//div[contains(@class,'icon-btns')]";
	
	protected static final String SALESMETER_SALESMETERS_MENU = SALESMETER_MENU_XPATH+"//button[text()='Sales Meters']";
	
	protected static final String SALESMETER_MEASUREMENT_TYPES_MENU = SALESMETER_MENU_XPATH+"//button[text()='Measurement Types']";
	
	protected static final String SALESMETER_PARAMETERS_MENU = SALESMETER_MENU_XPATH+"//button[text()='Parameters']";
	
	protected static final String SALESMETER_METERSTATUS_MENU = SALESMETER_MENU_XPATH+"//button[text()='Meter Statuses']";
	
	//Panel where all the controls are displayed excluding Menu options
	protected static final String SALESMETER_CONTENT_PANEL = SALESMETER_MENU_XPATH+"/following-sibling::div[contains(@class,'x-panel-noborder')]";
	
	
	/**
	 * Helper method to navigate to Sales meter page
	 */
	public void navigateToSalesMetersPage(){

		McsElement.getElementByXpath(driver, SALESMETER_SALESMETERS_MENU).click();

		waitForExtJSAjaxComplete(10);


		waitForPanelHeaderTextToChange("Sales Meters");

	}

	/**
	 * Helper method to navigate to Measurement Types page
	 */
	public void navigateToMeasurementTypesPage(){

		McsElement.getElementByXpath(driver, SALESMETER_SALESMETERS_MENU).click();

		waitForExtJSAjaxComplete(10);


		waitForPanelHeaderTextToChange("Measurement Types");

	}

	/**
	 * Helper method to navigate to Parameters page
	 */
	public void navigateToParametersPage(){

		McsElement.getElementByXpath(driver, SALESMETER_SALESMETERS_MENU).click();

		waitForExtJSAjaxComplete(10);


		waitForPanelHeaderTextToChange("Parameters");

	}

	/**
	 * Helper method to navigate to Meter Statuses page
	 */
	public void navigateToMeterStatuses(){

		McsElement.getElementByXpath(driver, SALESMETER_SALESMETERS_MENU).click();

		waitForExtJSAjaxComplete(10);


		waitForPanelHeaderTextToChange("Meter Statuses");

	}

	
	/**
	 * Helper method to wait until the Panel header text becomes same as the value passed as parameter 
	 * @param panelHeaderTextPostChange : header text
	 */
	public void waitForPanelHeaderTextToChange(String panelHeaderTextPostChange){

		final String text = panelHeaderTextPostChange;
		WebDriverWait wait = new WebDriverWait(driver, 20);

		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {

				String xpath = SALESMETER_CONTENT_PANEL+"//span[@class='x-panel-header-text' and text()='"+text+"']";

				Boolean result = McsElement.isElementPresent(driver, xpath);
				return result;
			}
		});

	}
}
