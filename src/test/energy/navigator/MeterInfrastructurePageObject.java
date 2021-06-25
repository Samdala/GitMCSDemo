package test.energy.navigator;

import java.lang.ref.Reference;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import test.energy.meters.MetersPageObject;
import test.framework.webelement.McsElement;

public class MeterInfrastructurePageObject extends MetersPageObject {
	
	protected final String SUPPLY_POINT_GRID_CLASS = "x-tab-panel x-tab-panel-noborder";
	
	protected final String ADD_SUPPLY_POINT_WINDOW_HEADER = "Add Supply Point";
	
	protected final String EDIT_SUPPLY_POINT_WINDOW_HEADER = "Edit Supply Point";
	
	protected final String DIALOG_SP = "slnmSPId";
	
	protected final String SELECT_A_METER_WINDOW_HEADER = "Select a Meter";
	
	protected final String ADD_METER_WINDOW_HEADER = "Add Meter";
	
	protected final String EDIT_METER_WINDOW_HEADER = "Edit Meter";
	
	protected final String ADD_PREDECESSOR_WINDOW_HEADER = "Add Predecessor";
	
	protected final String INFO_MESSAGE_WINDOW_HEADER = "Info";
	
	protected final String ADD_SCOPE_WINDOW_HEADER = "Add Objects to Scope";
	
	protected final String EDIT_SCOPE_WINDOW_HEADER = "Edit Objects in Scope";
	
	protected final String DIALOG_METER = "slnmMeterId";

	/**
	 * Helper method to click on Tabs in Lookup window
	 * @param tabText : text of tab to click
	 */
	public void clickOnTab(String xwindowTitle, String tabText){
		
		String xpath = "//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//span[contains(@class,'x-tab-strip-text') and contains(text(),'"+tabText+"')]//ancestor::a";
		
		McsElement.getElementByXpath(driver, xpath).click();
	}
	
	/**
	 * Helper method to set Date in new Supply Point Window
	 * */
	public void setDateInSupplyPointWindow(String date) {
		
		WebElement rerferenceElement= McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", 
				SUPPLY_POINT_GRID_CLASS, "input", "@id", "effective-date", true, true);
		rerferenceElement.clear();
		rerferenceElement.sendKeys(date);
		Reporter.log("Set Date", true);
	}
	
	/**
	 * Helper method to Deactivate Supply Point
	 */
	public void deactivateSupplyPoint(){
		driver.findElement(
				By.xpath("//div[contains(@class,'x-menu x-menu-floating') and not(contains(@class,'hide'))]//span[contains(text(),'Deactivate')]")).click();

		Reporter.log("Select 'Deactivate Suppy Point' menu item", true);
	}
	
	/**
	 * Helper method to verify Supply Point Exist or Not
	 */
	public boolean verifySupplyPointExist(String supplyPointName) {
		try {
			driver.findElement(By
					.xpath("//div[contains(@class, 'x-panel x-panel-noborder')]//div[contains(@id,'hierarchy')]//*[name()='svg']//*[contains(text(),'" + supplyPointName + "')]"));
			waitForExtJSAjaxComplete(20);
			return true;
		} catch (Exception e) {
			return false;
		}	
	}
	
	/**
	 * Helper method to verify Meter Exist or Not
	 */
	public boolean verifyMeterExist(String meterName) {
		try {
			driver.findElement(By
					.xpath("//div[contains(@class, 'x-panel x-panel-noborder')]//div[contains(@id,'hierarchy')]//*[name()='svg']//*[contains(text(),'" + meterName + "')]"));
			waitForExtJSAjaxComplete(20);
			return true;
		} catch (Exception e) {
			return false;
		}	
	}
	
	/**
	 * Helper method to verify Predecessor Tab exist or not
	 */
	public boolean verifyPredecessorTabExist(String xwindowTitle) {
		try {
			driver.findElement(By
					.xpath("//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//span[contains(@class,'x-tab-strip-text') and contains(text(),'Predecessors')]//ancestor::a"));
			waitForExtJSAjaxComplete(20);
			return true;
		} catch (Exception e) {
			return false;
		}	
	}
	
	/**
	 * Helper method to click on Refresh Button
	 */
	public void clickRefreshBtn(){
		WebElement rerferenceElement= McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", 
				SUPPLY_POINT_GRID_CLASS, "button", "@class", "hierarchy-refresh", true, true);
		rerferenceElement.click();
		waitForExtJSAjaxComplete(20);
	}
	
	/*****************************Edit Meters Predecessor Tab*******************************************/
	
	/**
	 * Helper method to click on Meters
	 */
	public void clickOnMeters(String meters){
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@id", "hierarchy",
				"*", "text()", meters, true, true).click();
		Reporter.log("Click on Meters - "+meters, true);
	}
	
	/**
	 * Helper method to click on Add Existing meter as Successor
	 */
	public void clickOnAddExisitingMeter(){
		driver.findElement(
				By.xpath("//div[contains(@class,'x-menu x-menu-floating') and not(contains(@class,'hide'))]//span[contains(text(),'Add existing Meter')]")).click();

		Reporter.log("Select 'Add Existing Meter as Successor' menu item", true);
	}
	
	/**
	 * Helper method to verify Select a Meter Grid is Empty
	 */
	public String verifyMeterGridIsEmpty(String xwindowTitle) {
		String xpath = McsElement.getElementByXpath(driver, "//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//div[@class='x-grid3-body']//div[contains(@class,'x-grid-empty')]").getText();
		return xpath;
	}
	
	/**
	 * Helper method to Click on Add button in Predecessors Tab
	 */
	public void clickOnAddBtnPredecessorsTab(){
		McsElement.getElementByXpath(driver, "//div[contains(@class, 'predecessors-tab')]//div[contains(@class,'x-column-inner')]//button[contains(@class, 'addButton')]").click();
		
		Reporter.log("Clicking Add Button in Predecessor Tab", true);
	}
	
	/**
	 * Helper method to Click on Add supply point in Predecessors Tab
	 */
	public void clickOnAddSupplyPointPredecessorsTab(String xwindowTitle){
		McsElement.getElementByXpath(driver, "//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//div[contains(@class,'x-toolbar-layout-ct')]//button[(@class=' x-btn-text icon-predecessor-add') and text()='Add Supply Point']").click();
		
		Reporter.log("Clicking Add Supply Point in Predecessor Tab", true);
	}
	
	/**
	 * Helper method to Click on Add Meter in Predecessors Tab
	 */
	public void clickOnAddMeterPredecessorsTab(String xwindowTitle){
		McsElement.getElementByXpath(driver, "//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//div[contains(@class,'x-toolbar-layout-ct')]//button[(@class=' x-btn-text icon-predecessor-add') and text()='Add Meter']").click();
		
		Reporter.log("Clicking Add Meter in Predecessor Tab", true);
	}
	
	/**
	 * Helper method to Click on Add Collector in Predecessors Tab
	 */
	public void clickOnAddCollectorPredecessorsTab(String xwindowTitle){
		McsElement.getElementByXpath(driver, "//div[contains(@id, '"+getXWindowId(xwindowTitle)+"')]//div[contains(@class,'x-toolbar-layout-ct')]//button[(@class=' x-btn-text icon-predecessor-add') and text()='Add Collector']").click();
		
		Reporter.log("Clicking Add Collector in Predecessor Tab", true);
	}
	
	/**
	 * Helper method to get text of Warning dialog
	 * */
	public String getWarningMsg(String xwindowTitle) {
		
		String msg = null;
		try {
			msg = driver.findElement(By.xpath("//div[contains(@class,'x-window-dlg')]//span[contains(@class, 'ext-mb-text')]")).getText();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return msg;
	}
	
	/**
	 * Helper method to verify Meter is present in Lookup
	 * */
	public boolean verifyMeter1IsPresentInLookUp(String column, String name, String windowTitle) {
		
		boolean status = setValueGridLookupWithFiltersWithValidation("@id", getXWindowId(windowTitle), name, column);
		
		return status;
	}
	
}
