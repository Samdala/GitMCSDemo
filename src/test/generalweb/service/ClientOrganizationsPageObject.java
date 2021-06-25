package test.generalweb.service;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.McsElement;

public class ClientOrganizationsPageObject extends AbstractMcsTestSuite {

	protected final String ADD_Client_Organization_WINDOW_HEADER = "Customer:";
	protected final String NOTIFICATIONS_TEMPLATES_WINDOW_HEADER = "Select Notification Templates";

	protected final String XPATH_CLIENT_ORG_WINDOW = "//span[contains(text(), 'Client Organization') and contains(@class,'x-panel')]//ancestor::div[contains(@class,'admsettings-modulesettings')]";
	protected final String XPATH_ADMINISTRATION_CLASS_NAME = "admsettings-modulesettings";

	protected final String EDIT_SITE_WINDOW_HEADER = "Edit Site";
	
	/***
	 * Helper method to expand Master Data
	 */

	public void expandMasterData() {
		expandNode("div", "@id", getXPanelId("Administration"), "Master Data");
	}

	/***
	 * Helper method to click on Client Organization
	 */

	public void clickClientOrganizations() {

		Timer timer = new Timer().start();
		WebElement serviceOrg = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@id", getXPanelId("Administration"), "span", "text()",
						"Client Organizations", true, true);
		javaScriptFocus(serviceOrg);
		serviceOrg.click();
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Client Organizations" + " (" + timer.stop() + "ms)", true);
	}

	/***
	 * Helper method to select Organization
	 */

	public void selectClientOrganization(String orgName) {

		Timer timer = new Timer().start();

		McsElement.getElementByXpath(driver, XPATH_CLIENT_ORG_WINDOW + "//span[contains(text(),'" + orgName	+ "')]/..").click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("client Organizations selected" + " (" + timer.stop()+ "ms)", true);
	}

	/**
	 * Helper method to click on tab in Client Organization window
	 * 
	 */
	public void clickOnNotificationTab() {

		clickOnTab("Notifications");
	}

	/**
	 * Helper method to click on Notification tab in Client Organization window
	 * 
	 * @param tabText
	 *            text of tab to be clicked
	 */

	public void clickOnTab(String tabText) {

		McsElement.getElementByXpath(driver, XPATH_CLIENT_ORG_WINDOW + "//span[contains(text(),'" + tabText+ "') and contains(@class,'x-tab-strip-text')]").click();
	}

	/**
	 * Helper method to click on Notification tab in Client Organization window
	 * 
	 * @param column
	 *            to be used for selecting value in Lookup
	 * @param templateName
	 *            is to be add
	 * 
	 */

	public void addNotificationTemplates(String templateName, String columnName) {

		clickOnAddNotificationTemplatesButton();

		waitForExtJSAjaxComplete(15);

		selectTemplateLines("@id",getXWindowId(NOTIFICATIONS_TEMPLATES_WINDOW_HEADER),templateName, columnName);

		waitForExtJSAjaxComplete(15);

		Reporter.log("Add Notification template Line of " + templateName+ " is added ", true);

	}

	/**
	 * Helper method to click on Add button to add Notification Template in
	 * Client Organization window
	 * 
	 */

	public void clickOnAddNotificationTemplatesButton() {

		McsElement
				.getElementByXpath(driver, XPATH_CLIENT_ORG_WINDOW+ "//button[contains(text(), 'Add Notification Template')]")
				.click();

	}

	/**
	 * Helper Method to select template
	 */

	public void selectTemplateLines(String attribute, String attributeValue,String rowTextName, String columnName)
	{
		setValueGridLookupWithFilters(attribute, attributeValue, rowTextName,columnName);
		Reporter.log("Template Line is selected", true);
	}

	/**
	 * Helper Method to click remove button
	 */

	public void clickRemoveButton() {
		McsElement.getElementByXpath(driver, XPATH_CLIENT_ORG_WINDOW+ "//button[contains(text(), 'Remove')]").click();
	}

	/**
	 * Helper Method to click save button
	 */

	public void clickSaveChangesButton() {
		McsElement.getElementByXpath(driver, XPATH_CLIENT_ORG_WINDOW+ "//span[contains(text(), 'Save Changes')]/..").click();
	}

	/**
	 * Helper Method to click cancel button
	 */

	public void clickCancelButton() {
		McsElement.getElementByXpath(driver, XPATH_CLIENT_ORG_WINDOW+ "//button[contains(text(), 'Cancel')]").click();
	}
	
	/**
	 * Helper method to set the Tree value Lookup
	 */
	public void expandCustomerTreeLookUp(String customer){
		String XPATH_TREE_NODE = "//div[contains(@class,'x-panel-noborder x-tree')]//a[contains(@class,'x-tree-node-anchor')]//span[contains(text(), '"+customer+"')]/ancestor::div[contains(@class, 'x-tree-node-collapsed')]//img[contains(@class, ' x-tree-elbow-plus')]";
		
		McsElement.getElementByXpath(driver, XPATH_TREE_NODE).click();
		waitForExtJSAjaxComplete(25);
		
		Reporter.log("Customer is Clicked. <br>", true);
	}
	
	/**
	 * Helper method to select Site under customer
	 */
	public void selectSite(String site){
		String XPATH_TREE_NODE = "//div[contains(@class,'x-panel-noborder x-tree')]//span[contains(text(), '"+site+"')]/ancestor::a[contains(@class,'x-tree-node-anchor')]";
		
		McsElement.getElementByXpath(driver, XPATH_TREE_NODE).click();
		
		Reporter.log("Site is Clicked. <br>", true);
	}
	
	/**
	 * Helper method to click on Buttons in Client Organization
	 */
	public void clickButton(String value){
		McsElement.getElementByXpath(driver, "//input[@name='co_id' ]/ancestor::div[contains(@class,'x-panel-noborder x-border-panel')]//button[contains(text(), '"+value+"')]").click();
		Reporter.log("Clicked on "+value+" button ", true);
	}
	
	/**
	 * Helper method to get Site Details from CO
	 */
	public String getSiteDetails(String value){
		return McsElement.getElementByXpath(driver, "//div[contains(@id, '"+getXWindowId(EDIT_SITE_WINDOW_HEADER)+"')]//input[contains(@name, '"+value+"')]").getAttribute("value");
	}

}
