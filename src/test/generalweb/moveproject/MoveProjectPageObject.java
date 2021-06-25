package test.generalweb.moveproject;

import java.util.concurrent.TimeUnit;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.FileUploader;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

public class MoveProjectPageObject extends AbstractMcsTestSuite {


	
	protected final String MOVEPROJECT_FORM_CLASS = "x-resizable";
	
	protected final String MOVEPROJECT_WIN_XPATH = "//div[contains(@class,'x-resizable-pinned') and contains(@style,'visible')]";
	
	protected final String MOVEPROJECT_GRID_CLASS = "x-tab-panel-noborder";
	
	protected final String ADD_URL_XPATH = "//button[text()='Add URL' and contains(@style,'icon-addurl.png')]";

	protected final String HYPERLINK_XPATH = "//input[@name='strHyperlink']";
	
	protected final String ADD_URL_CREATE_XPATH = "(//div[contains(@class,'x-window')]//button[text()='Create'])[last()]";
	
	protected final String ADD_URL_SAVE_XPATH = "(//div[contains(@class,'x-window')]//button[text()='Save'])[last()]";

	protected final String ADD_FILE_XPATH = "//button[text()='Add File' and contains(@style,'icon-addfile.png')]";
	
	protected final String EDIT_URL_XPATH = "//button[text()='Edit' and contains(@style,'icon-edit.png')]";

	protected final String DELETE_URL_XPATH = "//button[text()='Delete' and contains(@style,'icon-delete.png')]";
	
	protected final String HYPERLINK_DESCRIPTION = "//input[@name='strDescription']";
	
	protected final String HYPERLINK_REMARK = "//input[@name='strRemark']";
	
	

	public void clickAddButton() {
                clickXPath("//div[contains(@class,'" + MOVEPROJECT_GRID_CLASS + "')]//button[contains(@class,'x-btn-text') and text()='Add']");
		Reporter.log("click add button", true);
	}

	public void clickEditButton() {
                clickXPath("//div[contains(@class,'" + MOVEPROJECT_GRID_CLASS + "')]//button[contains(@class,'x-btn-text') and text()='Edit']");
		Reporter.log("click edit button", true);
	}

	public void clickDeleteButton() {
		Timer timer = new Timer().start();
                clickXPath("//div[contains(@class,'" + MOVEPROJECT_GRID_CLASS + "')]//button[contains(@class,'x-btn-text') and text()='Delete']");

		Reporter.log("Delete selected move project" + " (" + timer.stop() + "ms)", true);
	}
	
	/**
	 * Helper method to verify if Template is present in Templates list
	 * @param templateName
	 * @return
	 */
	public boolean isTemplatePresent(String templateName){
		
		return McsElement.isElementPresent(driver,"//div[contains(@class,'tplLabel') and contains(text(),'"+templateName+"')]");
	}

	public void setReference(String code) {
		WebElement date = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", MOVEPROJECT_FORM_CLASS, "input", "@name",
				"reference", true, true);
		date.clear();
		date.sendKeys(code);
		Reporter.log("set reference " + code, true);
	}

	public String getReference() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", MOVEPROJECT_FORM_CLASS, "input",
				"@name", "reference", true, true).getAttribute("value");
	}


	public void setCode(String code) {
		WebElement date = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", MOVEPROJECT_FORM_CLASS, "input", "@name",
				"code", true, true);
		date.clear();
		date.sendKeys(code);
		Reporter.log("set date " + code, true);
	}

	public String getCode() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", MOVEPROJECT_FORM_CLASS, "input",
				"@name", "code", true, true).getAttribute("value");
	}

	
	public void setProjectCode(String code){
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-panel')]//input[@name='PRJ_CODE']").clear();
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-panel')]//input[@name='PRJ_CODE']").sendKeys(code);
	}
	

	public void setProjectReference(String code){
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-panel')]//input[@name='PRJ_REFERENCE']").clear();
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-panel')]//input[@name='PRJ_REFERENCE']").sendKeys(code);
	}
	
	
	public void setNature(String nature) {
		
		if (nature.equals("")){
			driver.findElement(By.xpath("(//form[contains(@class,'x-panel-body x-panel-body-noheader x-panel-body-noborder x-form')]//input[@name='nature']//..//input)[last()]"))
			.clear();
		}
		else{
		
		clickLookup("x-resizable", "nature");
		waitForExtJSAjaxComplete(5);
		setValueTreeLookup(new String[]{nature});}
	}
	
	
	public String getNature() {
		return driver
				.findElement(
						By.xpath("(//form[contains(@class,'x-panel-body x-panel-body-noheader x-panel-body-noborder x-form')]//input[@name='nature']//..//input)[last()]"))
				.getAttribute("value");
	}
	

	
	public String getLocation() {
		return driver
				.findElement(
						By.xpath("(//form[contains(@class,'x-panel-body x-panel-body-noheader x-panel-body-noborder x-form')]//input[@name='location']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	
	
	public void clickGeneralTab() {
		clickXPath("//div[contains(@class,'x-resizable')]//span[text()='General']");

		waitForExtJSAjaxComplete(5);
	}

	
	public void clickRelatedDocumentsTab() {
                clickXPath("(//div[contains(@class,'x-resizable')]//span[text()='Documents'])|(//div[contains(@class,'x-resizable')]//span[text()='Related Documents'])");
		waitForExtJSAjaxComplete(5);
	}
	
	
	public void clickCreate() {
                clickXPath("//div[contains(@class,'x-panel')]//tbody[contains(@class,'x-btn-medium x-btn-icon-medium-left')]//button[text()='Create']");
		waitForExtJSAjaxComplete(5);
	}
	
	public void save() {
		McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", MOVEPROJECT_FORM_CLASS, "button",
				"text()", "Save", true, true).click();
	}

	public void close() {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", MOVEPROJECT_FORM_CLASS, "button", "text()",
				"Close", true, true).click();
		waitForExtJSAjaxComplete(3);
		if (!McsElement.isElementAbsent(driver, "//div[contains(@class,'x-window-dlg') and not(contains(@style,'hidden'))]//button[contains(@class,'x-btn-text') and contains(text(),'Yes')]"))
		{clickOnDialogButton("Yes");}
	}
	
	public void setProjectCategory(String category){
		clickLookup("x-panel", "PRJ_CATEGORY");
		setValueGridLookupWithFilters(category, "Reference");
	}
	
	
	public void setLocation(String category){
		//clickLookup("x-panel", "PRJ_LOCATION");
		clickLookupNewUI("x-panel", "PRJ_LOCATION");
	//	driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//span[contains(text(),'Buildings')]")).click();
		
		//setValueGridLookupWithFilters(category, "Reference");
		setValueGridLookupWithFiltersNewUI(category, "Reference");
	}
	
	
public void setProjectNature(String nature) {
		
		clickLookup("x-panel", "PRJ_NATURE");
		
		waitForExtJSAjaxComplete(5);
		
		driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//span[contains(text(),'Tree')]")).click();

		
		setValueTreeLookup(new String[]{nature});
		
	}
	

////////////////////////////RELATED DOCUMENTS TAB////////////////////////////////////////////////////

public void setUrl(String url, String description, String remark, String type){
	McsElement.getElementByXpath(driver, ADD_URL_XPATH).click();
	waitForExtJSAjaxComplete(5);
	McsElement.getElementByXpath(driver, HYPERLINK_XPATH).clear();
	McsElement.getElementByXpath(driver, HYPERLINK_XPATH).sendKeys(url);
	
	McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).clear();
	McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).sendKeys(description);
	
	McsElement.getElementByXpath(driver, HYPERLINK_REMARK).clear();
	McsElement.getElementByXpath(driver, HYPERLINK_REMARK).sendKeys(remark);
	
	int xwindowNumber=0;
	
	try {driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		xwindowNumber = driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size();
	}
	finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);}

	waitFocusAndClick("//div[contains(@class,'x-window')]//label[contains(text(),'Type')]/..//button");
	
	waitForExtJSAjaxComplete(10);
	waitForExtJSAjaxComplete(10);
	
	try {driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		if (driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size() == xwindowNumber)
			{waitAndClick("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");}
	}

	 finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
	}
	
	
	waitForExtJSAjaxComplete(5);
	
	setValueGridLookupWithFilters(type, "Reference");
	
	waitForExtJSAjaxComplete(5);
	
	McsElement.getElementByXpath(driver, ADD_URL_CREATE_XPATH).click();
	waitForExtJSAjaxComplete(5);
	Reporter.log("url was set <br>", true);
}


public void editUrl(String url, String description, String descriptionEd, String remark, String type){
	Grid.checkRowInGriByTextValueAndClick(driver, description);
	McsElement.getElementByXpath(driver, EDIT_URL_XPATH).click();
	waitForExtJSAjaxComplete(25);
	McsElement.getElementByXpath(driver, HYPERLINK_XPATH).clear();
	McsElement.getElementByXpath(driver, HYPERLINK_XPATH).sendKeys(url);
	
	McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).clear();
	McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).sendKeys(descriptionEd);
	
	McsElement.getElementByXpath(driver, HYPERLINK_REMARK).clear();
	McsElement.getElementByXpath(driver, HYPERLINK_REMARK).sendKeys(remark);
	
	int xwindowNumber=0;
	
	try {driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		xwindowNumber = driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size();
	}
	finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);}

	waitFocusAndClick("//div[contains(@class,'x-window')]//label[contains(text(),'Type')]/..//button");
	
	waitForExtJSAjaxComplete(10);
	waitForExtJSAjaxComplete(10);
	
	try {driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		if (driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size() == xwindowNumber)
			{waitAndClick("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");}
	}

	 finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
	}
	
	
	waitForExtJSAjaxComplete(5);
	
	setValueGridLookupWithFilters(type, "Reference");
	
	waitForExtJSAjaxComplete(5);
	
	McsElement.getElementByXpath(driver, ADD_URL_SAVE_XPATH).click();
	waitForExtJSAjaxComplete(5);
	Reporter.log("url was set <br>", true);
}


public void deleteUrl(String description){
	Grid.checkRowInGriByTextValueAndClick(driver, description);
	McsElement.getElementByXpath(driver, DELETE_URL_XPATH).click();
	waitForExtJSAjaxComplete(5);
	clickOnDialogButton("Yes");
	waitForExtJSAjaxComplete(5);
	Reporter.log("url or file was deleted <br>", true);
}


public void editFile(String file, String description, String descriptionEd, String remark, String type){
	Grid.checkRowInGriByTextValueAndClick(driver, description);
	McsElement.getElementByXpath(driver, EDIT_URL_XPATH).click();
	waitForExtJSAjaxComplete(5);
	FileUploader.uploadFileName(driver, "@class", "x-window", file);
	waitForExtJSAjaxComplete(5);
	
	McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).clear();
	McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).sendKeys(descriptionEd);
	
	McsElement.getElementByXpath(driver, HYPERLINK_REMARK).clear();
	McsElement.getElementByXpath(driver, HYPERLINK_REMARK).sendKeys(remark);
	
	int xwindowNumber=0;
	
	try {driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		xwindowNumber = driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size();
	}
	finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);}

	waitFocusAndClick("//div[contains(@class,'x-window')]//label[contains(text(),'Type')]/..//button");
	
	waitForExtJSAjaxComplete(10);
	waitForExtJSAjaxComplete(10);
	
	try {driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		if (driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size() == xwindowNumber)
			{waitAndClick("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");}
	}

	 finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
	}
	
	
	waitForExtJSAjaxComplete(5);
	
	setValueGridLookupWithFilters(type, "Reference");
	
	waitForExtJSAjaxComplete(5);
	
	
	McsElement.getElementByXpath(driver, ADD_URL_SAVE_XPATH).click();
	waitForExtJSAjaxComplete(5);
	Reporter.log("file was uploaded <br>", true);
}


public void setFile(String file, String description, String remark, String type){
	McsElement.getElementByXpath(driver, ADD_FILE_XPATH).click();
	waitForExtJSAjaxComplete(5);
	FileUploader.uploadFileName(driver, "@class", "x-window", file);
	waitForExtJSAjaxComplete(5);
	
	
	McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).clear();
	McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).sendKeys(description);
	
	McsElement.getElementByXpath(driver, HYPERLINK_REMARK).clear();
	McsElement.getElementByXpath(driver, HYPERLINK_REMARK).sendKeys(remark);
	
	int xwindowNumber=0;
	
	try {driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		xwindowNumber = driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size();
	}
	finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);}

	waitFocusAndClick("//div[contains(@class,'x-window')]//label[contains(text(),'Type')]/..//button");
	
	waitForExtJSAjaxComplete(10);
	waitForExtJSAjaxComplete(10);
	
	try {driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		if (driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size() == xwindowNumber)
			{waitAndClick("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");}
	}

	 finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
	}
	
	
	waitForExtJSAjaxComplete(5);
	
	setValueGridLookupWithFilters(type, "Reference");
	
	waitForExtJSAjaxComplete(5);
	
	
	McsElement.getElementByXpath(driver, ADD_URL_CREATE_XPATH).click();
	waitForExtJSAjaxComplete(5);
	Reporter.log("file was uploaded <br>", true);
}



//////////////////////////////////////////////MOVE LIST-EMPLOYEES////////////////////////////////////////////////////

public void clickMoveListTab() {
	McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//span[text()='Move List']").click();
	waitForExtJSAjaxComplete(5);
}


public void clickMoveListDetails() {
	McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//span[contains(text(), 'Move List')]").click();
	waitForExtJSAjaxComplete(5);
}

public void setDueDate(String date) {
	WebElement input = McsElement.getElementByXpath(driver,"//span[contains(text(),'Move List')]/ancestor::div[3]//label[contains(text(),'Due Date')]/following-sibling::div//input");
	input.click();
	input.clear();
	input.sendKeys(date);
	waitForExtJSAjaxComplete(5);
}


public String getDueDate(){
	
	return	McsElement.getElementByXpath(driver,"(//span[contains(text(),'Move List')]/ancestor::div[3])[last()]//label[contains(text(),'Due Date')]/following-sibling::div//input").getAttribute("value");
}

public void setMoveListDescription(String date) {
	WebElement input = McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//label[contains(text(),'Description')]/following-sibling::div//textarea");
	input.click();
	input.clear();
	input.sendKeys(date);
	waitForExtJSAjaxComplete(5);
}


public String getMoveListDescription() {
	return McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//label[contains(text(),'Description')]/following-sibling::div//textarea").getAttribute("value");
}


public void setMoveListJustification(String date) {
	WebElement input = McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//label[contains(text(),'Justification')]/following-sibling::div//textarea");
	input.click();
	input.clear();
	input.sendKeys(date);
	waitForExtJSAjaxComplete(5);
}

public String getMoveListJustification() {
	return McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//label[contains(text(),'Justification')]/following-sibling::div//textarea").getAttribute("value");
}


public void clickEmployeesTab() {
	McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//span[text()='Employees' and contains(@class,'x-tab-strip-text')]").click();
	waitForExtJSAjaxComplete(5);
}

public void clickEmployeesTabInAssigneeLookup() {
	McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//li[contains(@id,'employ')]//span[text()='Employees']").click();
	waitForExtJSAjaxComplete(5);
}


public void clickContactsTabInAssigneeLookup() {
	McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//li[contains(@id,'contact')]//span[text()='Contacts']").click();
	waitForExtJSAjaxComplete(5);
}


public void clickAssetsTab() {
	McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//span[text()='Assets']").click();
	waitForExtJSAjaxComplete(3);
}

public void clickAddEmployeeAsset() {
	McsElement.getElementByXpath(driver,"(//em[contains(@class,'x-btn-split')]//button[text()='Add'])[last()]").click();
	waitForExtJSAjaxComplete(3);
}

public void clickAddDummyEmployeeAsset() {
	McsElement.getElementByXpath(driver,"(//em[contains(@class,'x-unselectable')]//button[text()='Add Dummy'])[last()]").click();
	waitForExtJSAjaxComplete(3);
}


public void setEmployee(String employee){
	String name = McsElement.getElementByXpath(driver,"(//div[contains(@class,'x-resizable-pinned') and not (contains(@class,'x-window-noborder'))]//input)[last()]").getAttribute("name");
	clickLookup("x-resizable", name);
	setValueGridLookupWithFilters(employee, "Last Name");
	McsElement.getElementByXpath(driver,"(//div[contains(@class,'x-resizable-pinned') and not (contains(@class,'x-window-noborder'))]//button[text()='OK'])[last()]").click();
}

public void setAsset(String employee){
	String name = McsElement.getElementByXpath(driver,"(//div[contains(@class,'x-resizable-pinned') and not (contains(@class,'x-window-noborder'))]//input)[last()]").getAttribute("name");
	clickLookup("x-resizable", name);
	String id =	McsElement.getElementByXpath(driver,"//div[contains(@id,'"+getXWindowId("Select an Asset")+"')]//div[contains(@class,'x-form-field')]//input").getAttribute("id");		
	DropDown.setExtJsComboValue(driver, id, "general");
	waitForExtJSAjaxComplete(5);
	setValueGridLookupWithFilters(employee, "Reference");
	McsElement.getElementByXpath(driver,"(//div[contains(@class,'x-resizable-pinned') and not (contains(@class,'x-window-noborder'))]//button[text()='OK'])[last()]").click();
}


//**************************************MOVE LIST - MOVE LIST TAB*********************************************************

/**
* Helper method to set Due date in Move List Details tab
* @param date to enter
*/
public void setDueDateInMoveListDetailsTab(String date){
	
	//WebElement input = driver.findElement(By.xpath(MOVELIST_TAB_XPATH+"//label[contains(text(),'Due Date')]/..//input"));
	
	WebElement input = driver.findElement(By.xpath("//span[text()='Move List Details']/ancestor::div[3]//label[contains(text(),'Due Date')]/following-sibling::div//input"));
	
	input.click();
	input.clear();
	input.sendKeys(date);
	waitForExtJSAjaxComplete(5);
}

//////////////////////////////////////////////DESCRIPTION TAB////////////////////////////////////////////////////

public void clickDescriptionTab() {
McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//span[text()='Description']").click();
waitForExtJSAjaxComplete(5);
}


public void setDescription(String description) {
McsElement.getElementByXpath(driver,"//div[contains(@class,'x-window')]//form/textarea").click();
McsElement.getElementByXpath(driver,"//div[contains(@class,'x-window')]//form/textarea").clear();
McsElement.getElementByXpath(driver,"//div[contains(@class,'x-window')]//form/textarea").sendKeys(description);
waitForExtJSAjaxComplete(5);
}

public String getDescription() {
return McsElement.getElementByXpath(driver,"//div[contains(@class,'x-window')]//form/textarea").getAttribute("value");
}

public void doubleClickRow(String row){
	WebElement cellElement = driver.findElement(By.xpath("//div[contains(@class,'x-grid3')]//div[contains(text(),'"+row+"')]"));
Actions action = new Actions(driver);
action.doubleClick(cellElement);
action.perform();
}

/////////////////////FINANCIAL TAB/////////////////////////////////////

public void clickFinancialTab() {
McsElement.getElementByXpath(driver,"//div[contains(@class,'x-resizable')]//span[text()='Financial']").click();
waitForExtJSAjaxComplete(5);
}


public void setCostCategory(String category) {
clickLookup("x-resizable", McsElement.getElementByXpath(driver,"//label[text()='Cost Category:']/..//input").getAttribute("name"));
McsElement.getElementByXpath(driver,"//li[contains(@id,'cost_category_list')]//span[contains(text(),'List')]").click();
setValueGridLookupWithFilters(category, "Reference");
}

public String getCostCategory() {
return McsElement.getElementByXpath(driver,"//label[text()='Cost Category:']/..//input").getAttribute("value");
}


public void clickLookupInMoveProjWindow( String inputName){
	
	String lookupButtonXpath = MOVEPROJECT_WIN_XPATH+"//input[contains(@name,'"
				+ inputName + "')]//..//..//button";
	
	
	McsElement.getElementByXpath(driver, lookupButtonXpath).click();
	
}


}
