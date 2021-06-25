package test.generalweb.reservation;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;
import test.generalweb.calltemplates.CallTemplatesPageObject;

public class ReservationsGeneralSettingsAdministrationPageObject extends CallTemplatesPageObject {

	
	

	protected final String DEFAULT_VALUE_COLUMN_NO = "4";

	protected final String VISIBLE_VALUE_COLUMN_NO = "2";

	protected final String MANDATORY_VALUE_COLUMN_NO = "3";

	/**
	 * Helper method to check Default value column checkbox based upon field name passed.
	 * 
	 * @param fieldNames
	 *            : List of Field names for whom Default value checkbox has to be checked.
	 */
	public void checkDefaultValueCheckBox(List<String> fieldNames,
			String colNum, String tableName) {

		for (String fieldName : fieldNames) {

			String xpath = "//label[text()='"
					+ tableName
					+ "']//ancestor::div[contains(@class,'x-panel-tbar')]/..//label[text()='"
					+ fieldName
					+ "']/ancestor::tr//td[contains(@class,'x-grid3-cell x-grid3-td-"
					+ colNum + "')]//input[@type='checkbox']";

			try {
				WebElement checkBox = McsElement.getElementByXpath(driver, xpath);

				if (!checkBox.isSelected()) {
					
	
					 String checkBoxStatus = checkBox.getAttribute("checked");
					 
					 if(checkBoxStatus==null||checkBoxStatus.equals("false")){
					 
					  javaScriptClick(checkBox);
				}}


				Reporter.log("Row " + fieldName + " is checked", true);

			} catch (Exception e) {
				Reporter.log("Unable to check " + fieldName
						+ "status of row of grid ", true);
				break;
			}
		}

	}

	/**
	 * Helper method to check Default value column checkbox based upon field
	 * name passed.
	 * 
	 * @param fieldNames
	 *            : List of Field names for whom Default value checkbox has to
	 *            be checked.
	 */
	public void checkDefaultValueCheckBoxForFields(List<String> fieldNames) {

		checkDefaultValueCheckBox(fieldNames, DEFAULT_VALUE_COLUMN_NO,"Reservation Behavior Settings");

	}

	/**
	 * Helper method to check Mandatory field column checkbox based upon field
	 * name passed.
	 * 
	 * @param fieldNames
	 *            : List of Field names for whom Mandatory field value checkbox
	 *            has to be checked.
	 */
	public void checkMandatoryFieldsCheckBox(List<String> fieldNames) {

		checkDefaultValueCheckBox(fieldNames, MANDATORY_VALUE_COLUMN_NO,"Search Fields");

	}

	/**
	 * Helper method to check Visible column checkbox based upon field name
	 * passed.
	 * 
	 * @param fieldNames
	 *            : List of Field names for whom Visible field value checkbox
	 *            has to be checked.
	 */
	public void checkVisibleColumnCheckBox(List<String> fieldNames,String tableName) {

		checkDefaultValueCheckBox(fieldNames, VISIBLE_VALUE_COLUMN_NO,tableName);

	}

	/**
	 * Helper method to uncheck Default value column checkbox based upon field
	 * name passed.
	 * 
	 * @param fieldNames
	 *            : List of Field names for whom Default value checkbox has to
	 *            be unchecked.
	 */
	public void unCheckDefaultValueCheckBox(List<String> fieldNames,
			String colNum, String tableName) {

		for (String fieldName : fieldNames) {

			String xpath = "//label[text()='"
					+ tableName
					+ "']//ancestor::div[contains(@class,'x-panel-tbar')]/..//label[text()='"
					+ fieldName
					+ "']/ancestor::tr//td[contains(@class,'x-grid3-cell x-grid3-td-"
					+ colNum + "')]//input[@type='checkbox']";

			try {
				
				WebElement checkBox =driver.findElement(By.xpath(xpath));
				 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", checkBox);

				waitForExtJSAjaxComplete(10);

				if (checkBox.isSelected()) {
					
	
					 String checkBoxStatus = checkBox.getAttribute("checked");
					 
					 if(checkBoxStatus.equals("true")){
						 
							javaScriptClick(checkBox);
							
						 
				}}
				 Reporter.log("Row " + fieldName + " is unchecked", true);
						
			} catch (Exception e1) {
				Reporter.log("Unable to uncheck " + fieldName+ "status of row of grid ", true);
				break;
			}
		}

	}

	/**
	 * Helper method to uncheck Default value column checkbox based upon field
	 * name passed.
	 * 
	 * @param fieldNames
	 *            : List of Field names for whom Default value checkbox has to
	 *            be checked.
	 */
	public void uncheckDefaultValueCheckBoxForFields(List<String> fieldNames) {

		unCheckDefaultValueCheckBox(fieldNames, DEFAULT_VALUE_COLUMN_NO,"Reservation Behavior Settings");

	}

	/**
	 * Helper method to uncheck Mandatory field value column checkbox based upon
	 * field name passed.
	 * 
	 * @param fieldNames
	 *            : List of Field names for whom Mandaory field value checkbox
	 *            has to be checked.
	 */
	public void uncheckMandatoryFieldsCheckBox(List<String> fieldNames) {

		unCheckDefaultValueCheckBox(fieldNames, MANDATORY_VALUE_COLUMN_NO,"Search Fields");

	}

	/**
	 * Helper method to uncheck Visible column checkbox based upon field name
	 * passed.
	 * 
	 * @param fieldNames
	 *            : List of Field names for whom Visible field value checkbox
	 *            has to be checked.
	 */
	public void uncheckVisibleColumnCheckBox(List<String> fieldNames,String tableName) {

		unCheckDefaultValueCheckBox(fieldNames, VISIBLE_VALUE_COLUMN_NO,tableName);

	}

	/**
	 * Helper method to set Default value based upon field name, table name,value
	 * 
	 * @param fieldNames
	 *            : List of Field names for whom Default value has to be
	 *            selected .
	 */
	public void setDefaultValuesForFields(String fieldName, String colNum,
			String value, String tableName) {

		String xpath = "//label[text()='"
				+ tableName
				+ "']//ancestor::div[contains(@class,'x-panel-tbar')]/..//label[text()='"
				+ fieldName
				+ "']/ancestor::tr//td[contains(@class,'x-grid3-cell x-grid3-td-"
				+ colNum + "')]//input[@type='text']";

		try {

			WebElement element = McsElement.getElementByXpath(driver, xpath);
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			String id = element.getAttribute("id");

			waitForExtJSAjaxComplete(20);

			DropDown.setComboValueDropDownSelector(driver, id, value);

		} catch (Exception e) {
			Reporter.log("Unable to set " + fieldName+ " value of row of grid ", true);
		}
	}
	
	/**
	 * Helper method to uncheck Visible column checkbox based upon field name passed.
	 * 
	 * @param fieldNames
	 *            : List of Field names for whom Visible field value checkbox has to be checked.
	 */
	public void selectDefaultValuesForFromAndUntilSearchTable(String fieldName,String fieldValue) {

		setDefaultValuesForFields(fieldName, DEFAULT_VALUE_COLUMN_NO, fieldValue,"Search Fields");

	}



	/**
	 * Helper method to uncheck Visible column checkbox based upon field name passed.
	 * 
	 * @param fieldNames
	 *            : List of Field names for whom Visible field value checkbox has to be checked.
	 */
	public void selectDefaultValuesForSearchTable(String fieldName,String fieldValue) {

		selectDefaultValues(fieldName, DEFAULT_VALUE_COLUMN_NO, fieldValue,"Search Fields");

	}

	/**
	 * Helper method to uncheck Visible column checkbox based upon field name
	 * passed.
	 * 
	 * @param fieldNames
	 *            : List of Field names for whom Visible field value checkbox
	 *            has to be checked.
	 */
	public void selectDefaultValuesForSettingsTable(String fieldName,
			String fieldValue) {

		selectDefaultValues(fieldName, DEFAULT_VALUE_COLUMN_NO, fieldValue,"Reservation Behavior Settings");

	}

	/**
	 * Helper method for get default values for all fields
	 * 
	 * @param FieldName
	 *            : List of field names
	 * @param colNum
	 *            : Column number for get the value
	 * @param tableName
	 *            : Table name
	 */

	public String getDefaultValuesForFields(String fieldName, String colNum,
			String tableName) {
		String xpath = "//label[text()='"
				+ tableName
				+ "']//ancestor::div[contains(@class,'x-panel-tbar')]/..//label[text()='"
				+ fieldName
				+ "']/ancestor::tr//td[contains(@class,'x-grid3-cell x-grid3-td-"
				+ colNum + "')]//input[@type='text']";
		return McsElement.getElementByXpath(driver, xpath).getAttribute("value");
	}

	/**
	 * Helper method to get value for morning field
	 */
	public String getDefaultValuesFormrngField(String fieldName) {

		return getDefaultValuesForFields(fieldName, DEFAULT_VALUE_COLUMN_NO,"Search Fields");
	}

	/**
	 * Helper method to get value for evening field
	 */
	public String getDefaultValuesForEvngField(String fieldName) {

		return getDefaultValuesForFields(fieldName, DEFAULT_VALUE_COLUMN_NO,"Search Fields");
	}

	/**
	 * Helper method to set value for evening field
	 */
	public void setDefaultValuesForCapacityFields(String fieldName, String value) {

		WebElement xpath = driver
				.findElement(By
						.xpath("//label[text()='Search Fields']//ancestor::div[contains(@class,'x-panel-tbar')]/..//label[text()='"
								+ fieldName
								+ "']/ancestor::tr//td[contains(@class,'x-grid3-cell x-grid3-td-"
								+ DEFAULT_VALUE_COLUMN_NO
								+ "')]//input[@type='text']"));
		
		
		
		xpath.click();
		
		waitForExtJSAjaxComplete(2);
		
		xpath.clear();
		
		xpath.sendKeys(value);
				
		JavascriptExecutor exe= ((JavascriptExecutor)driver);
		exe.executeScript("arguments[0].value='"+value+"';", xpath);
				
	}

	/**
	 * Helper method to clear value for evening field
	 */
	public void clearDefaultValuesForCapacityFields(String fieldName) {

		WebElement xpath = driver
				.findElement(By
						.xpath("//label[text()='Search Fields']//ancestor::div[contains(@class,'x-panel-tbar')]/..//label[text()='"
								+ fieldName
								+ "']/ancestor::tr//td[contains(@class,'x-grid3-cell x-grid3-td-"
								+ DEFAULT_VALUE_COLUMN_NO
								+ "')]//input[@type='text']"));
		xpath.click();
		
		waitForExtJSAjaxComplete(2);
		
		xpath.clear();
				
	}


	/**
	 * Helper method to check Default value column check box based upon field name passed.
	 * @param fieldNames : List of Field names for whom Default value check box has to be checked.
	 */
	public List<String> getDefaultValueDropDownValueForFields(List<String> fieldNames){

		List<String> values = new ArrayList<String>();
		for(String fieldName: fieldNames){

			String xpath = "//label[text()='"+fieldName+"']/ancestor::tr//td[contains(@class,'x-grid3-cell x-grid3-td-"+DEFAULT_VALUE_COLUMN_NO+"')]//input[@type='text']"; 

			WebElement dropDownValue = McsElement.getElementByXpath(driver, xpath);
			values.add(dropDownValue.getAttribute("value").trim());
		}
		return values;
	}

	public void expandModuleSettings() {
		
		waitForExtJSAjaxComplete(10);
		expandNode("div","@id",getXPanelId("Administration"),"Module Settings");
		}
	
	/**
	 * Helper method to set Default value based upon field name, table name,value
	 * 
	 * @param fieldNames
	 *            : List of Field names for whom Default value has to be
	 *            selected .
	 */
	public void selectDefaultValues(String fieldName, String colNum,
			String value, String tableName) {

		String xpath = "//label[text()='"
				+ tableName
				+ "']//ancestor::div[contains(@class,'x-panel-tbar')]/..//label[text()='"
				+ fieldName
				+ "']/ancestor::tr//td[contains(@class,'x-grid3-cell x-grid3-td-"
				+ colNum + "')]//input[@type='text']";

		try {

			WebElement element = driver.findElement(By.xpath(xpath));
			
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			String id = element.getAttribute("id");
			System.err.println(id);

			waitForExtJSAjaxComplete(10);

			setComboValueDropDownSelectorWithScrollIntoViewCustomized(driver, id, value);

		} catch (Exception e) {
			Reporter.log("Unable to set " + fieldName+ " value of row of grid ", true);
		}
	}
	
	/**
	 * Set Combo value using natural selenium commands
	 * @param webDriver WebDriver instance to use
	 * @param elementId The HTML ID of the element
	 * @comboValue value present in combobox
	 * 
	 */
	public static void setComboValueDropDownSelectorWithScrollIntoViewCustomized(WebDriver webDriver, String elementId, String comboValue) {
		
		//Click on Dropdown down arrow.
		
        WebElement element = driver.findElement(By.xpath("//input[@id='"+elementId+"']//..//img[contains(@class,'x-form-trigger')]"));
		 
        try{
			  ((JavascriptExecutor)webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
			  ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();",element);
		  } catch(Exception e){
			  e.printStackTrace();
		  }

        try {Thread.sleep(500);} 
        catch (InterruptedException e) {}

		if(!comboValue.trim().isEmpty()){
        webDriver.findElement(By.xpath("//div[contains(@class,'x-combo-list-item') and contains(text(),'"+comboValue+"') and not(ancestor::div[contains(@style,'visibility: hidden')])]")).click();
		}else{
	
			WebElement combolist =  new WebDriverWait(webDriver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'x-combo-list') and contains(@style, 'visibility: visible')]")));
			
			List<WebElement> combolistItems = combolist.findElements(By.xpath(".//div[@class='x-combo-list-item']"));
			
			for(int i=0; i<combolistItems.size(); i++){
				
				String comboListItemText = combolistItems.get(i).getText().trim();
				
				if(comboListItemText.isEmpty()){
					
					combolistItems.get(i).click();
					
					break;
					
				}
				
			}
			
		}
		}
	
	
	/**
	 * To Save Changes in General Setting
	 * @return save status
	 */
	public boolean clickOnSaveChangesInGeneralSettings( ){

		waitForExtJSAjaxComplete(20);
		String buttonStatus = "//div[contains(@class,'admsettings-generalsettings')]//button[text()='Save Changes']/ancestor::table[contains(@class,'x-btn-text-icon x-item-disabled')]";
		if(!McsElement.isElementPresent(driver,buttonStatus)){
			try{
				WebElement element=driver.findElement(By.xpath("//div[contains(@class,'admsettings-generalsettings')]//button[text()='Save Changes']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();",element);
				WebElement xpath=driver.findElement(By.xpath("//button[text()='Yes']"));
				xpath.click();
				waitForExtJSAjaxComplete(20);
				Reporter.log(" Changes are saved ", true);
				return true;
			}

			catch(Exception E)
			{
				Reporter.log("Unable to save changes",true);
				return false;
			}
		}

		else{
			Reporter.log("No changes to save",true);
			return true;
		}
	}
}
