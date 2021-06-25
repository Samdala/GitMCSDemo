package test.generalweb.service;



import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import test.configuration.Configuration;
import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.McsElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class ServiceOrganizationsPageObject extends AbstractMcsTestSuite {

	//protected final String ADMINISTRATION_PANEL_CLASS = "x-panel-body x-panel-body-noborder";
	
	//protected final String FORM_TEMPLATE_NAME = "Service Organizations - All Service";

	public void goToAdministration() {
		driver.get(configuration.getApplicationUrl()
				+ "/frame.php?relay=ADM_SETTINGS");
	};

	public void expandMasterData() {
		expandNode("div","@id",getXPanelId("Administration"),"Master Data");
		}
	
	public void clickAdministration() {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "mcs-tb-glossy-strong", "button", "text()",
				"Administration", true, true).click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click Administration"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	public void clickServiceOrganizations() {
		Timer timer = new Timer().start();
		WebElement serviceOrg = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@id", getXPanelId("Administration"), "span", "text()",
				"Service Organizations", true, true);
		javaScriptFocus(serviceOrg);
		serviceOrg.click();
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Service Organizations"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	public void clickLinkSite() {
		Timer timer = new Timer().start();
		String xpath ="//button[contains(text(), 'Link Site')]";
		
		WebElement ele = driver.findElement(By.xpath(xpath));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		ele.click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click add template"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	public void clickUnLinkSite() {
		Timer timer = new Timer().start();
		if (configuration.getApplicationUrl().contains("122")) {
			McsElement.getElementByXpath(driver, "//button[contains(text(), 'Remove Site')]").click();
		}
		else{McsElement.getElementByXpath(driver, "//button[contains(text(), 'Unlink Site')]").click();}
		waitForExtJSAjaxComplete(10);
		clickOnDialogButton("Yes");
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click add template"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	public void selectLocations(String siteName) {
		
		//get lookup dialog id
		String dialogId = getXWindowIdSO("Select Locations");
		McsElement.getElementByXpath(driver, "//div[@id = '" + dialogId + "']//div[contains(text(), '" + siteName + "')]").click();
		Reporter.log("Select " + siteName, true);
		clickOkXwindowSO();		
	}

	public void clickAddServiceOrganizations() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//button[contains(text(), 'Add Service Organization')]").click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click add template"+ " (" + timer.stop()
				+ "ms)", true);
	}

	public void clickDeleteServiceOrganizations() {
		McsElement.getElementByXpath(driver, "//button[contains(text(), 'Delete Service Organization')]").click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click delete template", true);
	}

	public void setReference(String referenceText) {
		WebElement reference = McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-panel')]//span[contains(text(), 'Service Organization') and contains(@class,'x-panel') ]//..//..//label[contains(text(),'Reference:')]//..//input");
		reference.clear();
		reference.sendKeys(referenceText);
		Reporter.log("Set reference", true);
	}
	
	public String getReference() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class, 'x-panel')]//span[contains(text(), 'Service Organization') and contains(@class,'x-panel') ]//..//..//input[@name='reference']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setCode(String codeText) {
		WebElement reference = McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-panel')]//span[contains(text(), 'Service Organization') and contains(@class,'x-panel') ]//..//..//label[contains(text(),'Code:')]//..//input");
		reference.clear();
		reference.sendKeys(codeText);
		Reporter.log("Set code", true);
	}
	
	public String getCode() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class, 'x-panel')]//span[contains(text(), 'Service Organization') and contains(@class,'x-panel') ]//..//..//input[@name='code']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	public void setDescription(String codeText) {
		WebElement reference = McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-panel')]//span[contains(text(), 'Service Organization') and contains(@class,'x-panel') ]//..//..//label[contains(text(),'Description:')]//..//textarea");
		reference.clear();
		reference.sendKeys(codeText);
		Reporter.log("Set description", true);
	}
	
	public String getDescription() {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class, 'x-panel')]//span[contains(text(), 'Service Organization') and contains(@class,'x-panel') ]//..//..//textarea[@name='description']//..//textarea)[last()]"))
				.getAttribute("value");
	}
	
	
	public void saveServiceOrganizations() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-panel')]//span[contains(text(), 'Service Organization') and contains(@class,'x-panel') ]//..//..//span[contains(text(),'Save Changes')]").click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Save Service Organizations"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	public void cancelServiceOrganizations() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-panel')]//span[contains(text(), 'Service Organization') and contains(@class,'x-panel') ]//..//..//span[contains(text(),'Cancel')]").click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Cancel Service Organizations"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	public void selectServiceOrganizationItem(String reference){
		Timer timer = new Timer().start();

		McsElement.getElementByXpath(driver, "//ul[contains(@class,'x-tree-root-ct x-tree-lines')]//span[contains(text(),'" + reference + "')]").click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Service Organizations selected"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	public boolean itemIsPresentInServiceOrganizationList(String reference) {
		
		String xpath = "//ul[contains(@class,'x-tree-root-ct x-tree-lines')]//span[contains(text(),'" + reference + "')]";
		try {
			driver.findElement(By.xpath(xpath));
		} catch (Exception e) {
			Reporter.log("row can not be found in list: " + xpath ,true);
			return false;
		}
		
		Reporter.log("Items with reference text " + reference  + " was successfully found in ServiceOrganizationList", true);

		return true;
	}
	
	public boolean itemIsAbsentInServiceOrganizationList(String reference) {
		String xpath = "//ul[contains(@class,'x-tree-root-ct x-tree-lines')]//span[contains(text(),'" + reference + "')]";
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			driver.findElement(By.xpath(xpath));
			return false;
		} catch (Exception e) {
			return true;
		} finally {
			try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
		}
		
	}
	
	public boolean checkIfLocationsAbsent(String siteName) {
		//get lookup dialog id
		
		String dialogId = getXWindowIdSO("Select Locations");
		String xpath = "//div[@id = '" + dialogId + "']//div[contains(@class,'x-grid3-header-quickfilters')]/..//div[contains(text(), '" + siteName + "')]";
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			driver.findElement(By.xpath(xpath));
			return false;
		} catch (Exception e) {
			return true;
		} finally {
			try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
		}
		
	}
	
	public void selectServiceOrganizationItemSite(String organiztion, String  site){
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//ul[contains(@class,'x-tree-root-ct x-tree-lines')]//span[contains(text(),'" + organiztion + "')]/../../..//span[contains(text(),'" + site + "')]").click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Service Organizations selected"+ " (" + timer.stop()
		+ "ms)", true);
	}

	public boolean checkIfServiceOrganizationItemSiteAbsent(String organiztion, String  site) {
		//get lookup dialog id

		String xpath = "//ul[contains(@class,'x-tree-root-ct x-tree-lines')]//span[contains(text(),'" + organiztion + "')]/../../..//span[contains(text(),'" + site + "')]";
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			driver.findElement(By.xpath(xpath));
			return false;
		} catch (Exception e) {
			return true;
		} finally {
			try {driver.manage().timeouts().implicitlyWait(Configuration.getConfiguration(null).getImplicitWait(),TimeUnit.SECONDS);} catch (Exception e) {}
		}

	}

	/**
	 * Method allows to get dynamical id of the modal window
	 * returns x-window id 
	 * 
	 * @param xwindowTitle - title of the x-window 
	 */
	public String getXWindowIdSO(String xwindowTitle) throws NoSuchElementException {

		String elementXpath = "(//div[contains(@class, 'x6-window')]//div[contains(text(),'"
				+ xwindowTitle + "')])[last()]/../../../../..";

		WebElement webElement = new WebDriverWait(driver, configuration.getImplicitWait())
				.until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath(elementXpath)));

		return webElement.getAttribute("id");
	}

	/**
	 * Method allows to click OK on lookup window
	 */	
	public void clickOkXwindowSO() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x6-toolbar-footer", "span",
				"text()", "OK", true, true).click();
		waitForExtJSAjaxComplete(5);
		waitForExtJSAjaxComplete(5);
	}

	/**
	 * Method allows to click CANCEL on lookup window
	 */	
	public void clickCANCELXwindowSO() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x6-toolbar-footer", "span",
				"text()", "Cancel", true, true).click();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Click Cancel button", true);
	}
	
	/**
	 * Helper method to click Add service group button
	 */
	public void clickAddServiceGroup() {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-panel x-panel-noborder x-border-panel')]//button[contains(text(), 'Add Service Group')]").click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click add service group"+ " (" + timer.stop()
				+ "ms)", true);
	}
	/**
	 * Helper method to set reference
	 * @param referenceText
	 */
	public void setReferenceForServiceGrp(String referenceText) {
		WebElement reference = McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-panel')]//span[contains(text(), 'Add New Service Group') and contains(@class,'x-panel') ]//..//..//label[contains(text(),'Reference:')]//..//input");
		reference.clear();
		reference.sendKeys(referenceText);
		Reporter.log("Set reference", true);
	}
	
	
	/**
	 * Helper method to set Code
	 * @param referenceText
	 */
	public void setCodeForServiceGrp(String referenceText) {
		WebElement reference = McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-panel')]//span[contains(text(), 'Add New Service Group') and contains(@class,'x-panel') ]//..//..//label[contains(text(),'Code:')]//..//input");
		reference.clear();
		reference.sendKeys(referenceText);
		Reporter.log("Set reference", true);
	}
	
	/**
	 * Helper method to enter test into description
	 * @param codeText
	 */
	public void setDescriptionForServiceGrp(String codeText) {
		WebElement reference = McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-panel')]//span[contains(text(), 'Add New Service Group') and contains(@class,'x-panel') ]//..//..//label[contains(text(),'Description:')]//..//textarea");
		reference.clear();
		reference.sendKeys(codeText);
		Reporter.log("Set description", true);
	}

	
	/**
	 * Helper method to click 
	 */
	private void clickLinkAndUnlinkContactPerson(String text) {
		Timer timer = new Timer().start();
		String xpath ="//button[contains(text(), '"+text+"')]";
		
		WebElement ele = driver.findElement(By.xpath(xpath));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch(Exception e){
			e.printStackTrace();
		}
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		ele.click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click link and unlink sites"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	public void clicklinkContactPerson()
	{
		clickLinkAndUnlinkContactPerson("Link Contact Person");
	}
	
	public void clickUnlinkContactPerson()
	{
		clickLinkAndUnlinkContactPerson("Unlink Contact Person");
	}
	
	
	
	/**
	 * Helper method to select values
	 */
	public void setValue(String value,String name,String colName)
	{

		clickLookup("x-panel", name);

		waitForExtJSAjaxComplete(25);

		setValueGridLookupWithFilters("@class","x-resizable",value,colName);
	}
	/**
	 * Helper method to  set contact
	 */
	public void setRole(String value,String colName)
	{
		setValue(value,"roleField","Reference");
	}
	
	/**
	 * Helper method to  set contact
	 */
	public void setContact(String value,String colName)
	{
		setValue(value,"contactField",colName);
	}
	/**
	 * Helper method to select contact
	 * @param organiztion
	 * @param site
	 */
	public void selectContactInServiceGroup(String ServiceGrp, String  contact){
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//ul[contains(@class,'x-tree-root-ct x-tree-lines')]//span[contains(text(),'" + ServiceGrp + "')]/../../..//span[contains(text(),'" + contact + "')]").click();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Cotact is selected"+ " (" + timer.stop()
		+ "ms)", true);
	}
	
	
	
	/**
	 * Helper method to  save service 
	 */
	private void saveService(String headerText) {
		Timer timer = new Timer().start();
		McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-panel')]//span[contains(text(), '"+headerText+"') and contains(@class,'x-panel') ]//..//..//span[contains(text(),'Save Changes')]").click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Save Service Organizations"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	
	public void saveServiceGroup()
	{
		saveService("Add New Service Group");
	}
	
	public void saveContactPerson()
	{
		saveService("Add New Contact Person");
	}
	
}
