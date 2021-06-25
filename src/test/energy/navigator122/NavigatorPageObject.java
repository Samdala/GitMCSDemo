package test.energy.navigator122;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.McsElement;

public class NavigatorPageObject extends AbstractMcsTestSuite {


	protected final String LOCATION_ELEMENT = "nrg-menu-navigator";
	
	protected final String ENERGY_RATINGS_GRID_CLASS = "x-tab-panel x-tab-panel-noborder";

	protected final String GAUGES_GRID_CLASS = "x-tab-panel x-tab-panel-noborder";
	
	protected final String ADD_CHANEL_GAUGES_FORM_CLASS = "slnmGaugeChnlId";
	
	protected final String ADD_GAUGES_FORM_CLASS = "slnmGaugeId";
	
	protected final String COPY_GAUGES_FORM_CLASS = "slnmGaugeCopyId";
	
	protected final String CHANNEL_MEASUREMENTS_CLASS ="slnmGaugeChnlMeasId";
	
	protected final String CHANGE_VISIBLE_COLUMNS_HEADER = "Change visible columns";
	
	protected final String ADD_GAUGES_WINDOW_HEADER = "Add Gauge";
	
	protected final String 	EDIT_GAUGE_CHANNEL_WINDOW_HEADER = "Edit Gauge Channel";
	
	protected final String CHANGE_VISIBLE_COLUMNS_CLASS = "x-window x-resizable-pinned";
	
	public void goToDefinition(String definition) {
		driver.get(configuration.getApplicationUrl() + "/frame.php?relay="
				+ definition);
	};

	public void expandNavigator() {
		WebElement panel = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"span", "text()", "Navigator", true, true);
		String elementClass = panel.getAttribute("class");
		boolean bRes = elementClass.matches("collapsed");
		if (bRes) panel.click();
		Reporter.log("Expand Navigator", true);
	}
	
	public void setLocation(String locationName){
		setValueTreeLookup(locationName);
		waitForExtJSAjaxComplete(5);
	}
	
	public void changeTab(String panelId, String tabName) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@id", panelId, "span", "text()", tabName, true, true).click();
		Reporter.log("change tab to "+tabName, true);
	}
	
	public void setLocationValueTreeLookup(String rowTextName) {
		
		Timer timer = new Timer().start();
		
		waitForExtJSAjaxComplete(25);
		
		//wait for tree to load
		McsElement
		.getElementByPartAttributeValueAndParentElement(driver, "div",
				"@class",
				"x-panel-body x-panel-body-noheader x-panel-body-noborder", "a",
				"@class", "x-tree-node-anchor", true,
				true);
		
		expandAllNodeExtJsTree("@class","x-panel-body x-panel-body-noheader x-panel-body-noborder");
		
		//find first node with given text
		WebElement firstNodeWithTextName = McsElement
				.getElementByPartAttributeValueAndParentElement(driver, "div",
						"@class",
						"x-panel-body x-panel-body-noheader x-panel-body-noborder", "a",
						"@class", "x-tree-node-anchor", ".", rowTextName, true,
						true);
		
		//if scrolling is needed
		javaScriptFocus(firstNodeWithTextName);
		
		firstNodeWithTextName.click();
		
		//clickOkXwindow();
		Reporter.log(rowTextName + " was selected" + " (" + timer.stop()
				+ "ms)", true);

	}
	
	public boolean checkWeatherInformation(String stringField, String stringValue) {
		return !driver.findElements(By.xpath("//fieldset//td//div[text() = '" + stringField + "']/../..//div[contains(text(), '" + stringValue + "')]")).isEmpty();
	}
	
	public String getWeatherInformation(String stringField) {
		return driver.findElement(By.xpath("//fieldset//td//div[text() = '" + stringField + "']/../..//div[contains(@class, 'value')]")).getText();
	}
	
	/**
	 * Helper method to click on Tabs in Navigator Pages
	 * @param tabText
	 */
	public void changeTab(String tabText){
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-tab-panel x-tab-panel-noborder",
				"span", "text()", tabText, true, true).click();
		Reporter.log("Click on tab", true);
	}
	
	/**
	 * Helper method to click on Add Button in Any of the Navigator Options
	 * Meter Infrastructure/ Gauges etc etc (@id)
	 */
	public void clickButton(String buttonName, String dialogId)
	{
		/*McsElement.getElementByPartAttributeValueAndParentElement(driver,
					"div", "@id", dialogId, "button", "@class", "x-btn-text",
					"text()", buttonName, true, true).click();
			Reporter.log("Click "+buttonName+" button", true);*/
		WebElement element = driver.findElement(By.xpath("//div[contains(@id, '"+dialogId+"')]//button[contains(@class, 'x-btn-text') and text()='"+buttonName+"']"));
		if ("chrome".equals(configuration.getBrowser())) {
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", element);
            } catch (Exception e) {
            	System.out.println("Exception is G " +e);
            }
        }
		element.click();
		Reporter.log("Click "+buttonName+" button", true);
	}
	
	/**
	 * Helper method to click on Add Button in Any of the Navigator Options
	 * Meter Infrastructure/ Gauges etc etc
	 */
	public void clickAddBtn() {
		McsElement.getElementByXpath(driver,
						"//div[contains(@class, 'x-tab-panel-body-noborder x-tab-panel-body-top') and not (contains(@class, 'hide-display'))]//button[contains(@class, 'icon-ov-add') and text()='Add']")
				.click();
		Reporter.log("Click on Add button", true);
	}
	
	/**
	 * Helper method to click on Edit Button in Any of the Navigator Options
	 * Meter Infrastructure/ Gauges etc etc
	 */
	public void clickEditBtn() {
		McsElement.getElementByXpath(driver,
						"//div[contains(@class, 'x-tab-panel-body-noborder x-tab-panel-body-top') and not (contains(@class, 'hide-display'))]//button[contains(@class, 'icon-ov-edit') and text()='Edit']")
				.click();
		Reporter.log("Click on Edit button", true);
	}
	
	/**
	 * Helper method to click on Delete Button in Any of the Navigator Options
	 * Meter Infrastructure/ Gauges etc etc
	 */
	public void clickDeleteBtn() {
		McsElement.getElementByXpath(driver,
						"//div[contains(@class, 'x-tab-panel-body-noborder x-tab-panel-body-top') and not (contains(@class, 'hide-display'))]//button[contains(@class, 'icon-ov-remove') and text()='Delete']")
				.click();
		clickOnDialogButton("Yes");
		waitForExtJSAjaxComplete(10);
		clickOnDialogButton("OK");
		Reporter.log("Click on Delete button", true);
	}
	
	/**
	 * Helper method to click on Delete Button in Any of the Navigator Options
	 * But will not delete the record. (For Deletion Use clickDeleteBtn)
	 */
	public void verifyDeleteFunctionality() {
		McsElement.getElementByXpath(driver,
						"//div[contains(@class, 'x-tab-panel-body-noborder x-tab-panel-body-top') and not (contains(@class, 'hide-display'))]//button[contains(@class, 'icon-ov-remove') and text()='Delete']")
				.click();
		Reporter.log("Click on Delete button and cancelled Deletion", true);
	}
	
	/**
	 * Helper method to verify grid is empty
	 */
	public String verifyGridIsEmpty() {
		String gridText = McsElement.getElementByXpath(driver,
				"//div[contains(@class, 'x-tab-panel-body-noborder x-tab-panel-body-top') and not (contains(@class, 'hide-display'))]//div[@class='x-grid3-body']//div[@class='x-grid-empty']").getText();
		return gridText;
	}
	
	/**
	 * Helper method to verify grid is empty with parameter 
	 */
	public String verifyGridIsEmpty(String attribute){
		WebElement gridText = driver.findElement(By.xpath("//div[contains(@class, 'x-tab-panel-body-noborder x-tab-panel-body-top') and not (contains(@class, 'hide-display'))]//div[@realid='"+attribute+"']//div[@class='x-grid3-body']//div[@class='x-grid-empty']"));
		String gridTextString = gridText.getText();
		return gridTextString;
	}
	
	/**
	 * Helper method to verify whether field is Editable or not using Input Name
	 */
	public String verifyUnEditable(String name) {
		String gridText = McsElement.getElementByXpath(driver,
				"//div[contains(@class, 'slnmGaugeId')]//input[@name='"+name+"']//..//input[last()]").getAttribute("class");
		return gridText;
	}
	
	/**
	 * Helper method to get Value of Any Column from Navigator gauges grid
	 */
	public String getValueOfAnyColumn(String attribute, String attributeValue, String columnName) {
			
		Timer timer = new Timer().start();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		String lineIDColumnNumber = getGridColumnNumber(attribute, attributeValue, columnName);

		WebElement element = driver.findElement(By.xpath("//div[contains("
				+ attribute + ",'" + attributeValue
				+ "') and not (contains(@class, 'x-hide-display'))]//"
				+ "div[@class='x-grid3-cell-inner x-grid3-col-"
				+ lineIDColumnNumber + "']"));
		String ele = element.getText();

		timer.stop();
		
		return ele;
	}
	
	/**
	 * Helper method to get Value of Status Column from Navigator gauges grid
	 */
	public String getValueOfCopyGuageStatusColumn(String attribute, String attributeValue, String columnName) {
			
		Timer timer = new Timer().start();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		String lineIDColumnNumber = getGridColumnNumber(attribute, attributeValue, columnName);

		WebElement element = driver.findElement(By.xpath("//div[contains("
				+ attribute + ",'" + attributeValue
				+ "') and not (contains(@class, 'x-hide-display'))]//"
				+ "div[contains(@class, 'x-grid3-cell-inner x-grid3-col-"
				+ lineIDColumnNumber + "')]"));
		String ele = element.getText();

		timer.stop();
		
		return ele;
	}
	
	/**
	 *  Helper method to get column number in Grid 
	 */
	public String getGridColumnNumber(String attribute, String attributeValue, String columnName){
		
		WebElement columnClassElement = driver
				.findElement(By
						.xpath("//div[contains("
								+ attribute
								+ ",'"
								+ attributeValue
								+ "') and not (contains(@class, 'x-hide-display'))]//div[contains(@class,'x-grid3-hd') and contains(text(), '"
								+ columnName + "')][last()]"));
		String columnClass = columnClassElement.getAttribute("class");
		String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);
		return columnNumber;
	}
	
	/**
	 * Helper method to compare date is equal or greater
	 */
	public int compareDates(String dateFormat, String dateForComp1, String dateForComp2){
		try{
			 
    		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        	Date date1 = sdf.parse(dateForComp1);
        	Date date2 = sdf.parse(dateForComp2);
 
        	System.out.println(sdf.format(date1));
        	System.out.println(sdf.format(date2));
        	
        	System.out.println(date1.compareTo(date2));
 
        	return date1.compareTo(date2); 
        	/*if(date1.compareTo(date2)>0){
        		System.out.println("ModifiedDate "+modifiedDate+" is after Modified Date updated"+modifiedDateUpdated);
        		return true;
        	}else if(date1.compareTo(date2)==0){
        		System.out.println("ModifiedDate "+modifiedDate+" is equal to Modified Date updated"+modifiedDateUpdated);
        		return true;
        	}else{
        		System.out.println("Modified Date updated Element is not present");
        		return false;
        	}*/
 
    	}catch(ParseException ex){
    		ex.printStackTrace();
    		return 0;
    	}
	}
	
	/**
	 * Helper method to compare date is equal or greater
	 */
	public int compareDates( String dateForComp1, String dateForComp2){
		return compareDates("dd-MM-yyyy hh:mm",  dateForComp1,  dateForComp2);
	}
	
	public void setScope(String proposerName){
		clickLookup(ADD_GAUGES_FORM_CLASS, "scopeLocation");
		setValueTreeLookup(proposerName);
		Reporter.log("Set Scope - " + proposerName, true);
//		waitForExtJSAjaxComplete(5);
	}
	
	public String getScope() {
		return driver
				.findElement(
						By.xpath("(//form[contains(@class,'x-panel-body x-panel-body-noheader x-panel-body-noborder x-form')]//input[@name='scopeLocation']//..//input)[last()]"))
				.getAttribute("value");
	}
	
	/**
	 * Helper method to click on Copy Gauge
	 */
	public void clickCopyGauge(){
		McsElement.getElementByXpath(driver,
				"//div[contains(@class, 'x-tab-panel-body-noborder x-tab-panel-body-top') and not (contains(@class, 'hide-display'))]//button[contains(@class, 'copyGauge') and text()='Copy Gauge']")
		.click();
		Reporter.log("Click on Copy Gauge button", true);
	}

	/**
	 * Helper method to select Energy Objects
	 */
	public void selectEnergyObjects(String column, String name){
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select Energy Objects"), name, column);
		
		Reporter.log("Energy Object was selected", true);
	}
	
	/**
	 * Helper method to verify Headers in Copy gauge window
	 */
	public boolean verifyColumnsInCopyGauge(String attribute, String attributeValue, String columnName){
		try{
			McsElement.getElementByXpath(driver,"(//div[contains("+attribute+",'"+attributeValue+"') and not (contains(@class, 'x-hide-display'))]//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')][last()])");
			return true;
		} catch(Exception e){
			System.out.println("Exception is: "+e);
			return false;
		}
	}
	
	/**
	 * Helper method to click on Create in Copy Gauge
	 */
	public void clickCreate(){
		McsElement.getElementByXpath(driver,
				"//div[contains(@class, "+COPY_GAUGES_FORM_CLASS+") and not (contains(@class, 'hide-display'))]//button[contains(@class, 'x-btn-text') and text()='Create']")
		.click();
		Reporter.log("Click on Create in Copy Gauge", true);
	}
	
	/**
	 * Helper method to verify Status OK in Copy Gauges
	 */
	public String getStatusValue(String attribute, String attributeValue, String columnName) {
		
		String lineIDColumnNumber = getGridColumnNumber(attribute, attributeValue, columnName);

		String ele = McsElement.getElementByXpath(driver,"(//"+"div"+"[contains("+attribute+",'"+attributeValue+"') and not (contains(@class, 'x-hide-display'))]//"
				+"div[contains(@class,'x-grid3-cell-inner x-grid3-col-"+lineIDColumnNumber + "')]/span)").getAttribute("textContent");

		return ele;
	}
	
	/**
	 * Helper method to click on Change Visible Columns
	 */
	public void clickChangeVisibleColumns(){
		McsElement.getElementByXpath(driver,
				"//div[contains(@class, 'x-tab-panel-body-noborder x-tab-panel-body-top') and not (contains(@class, 'hide-display'))]//button[contains(@class, 'icon-grid-columns')]")
		.click();
		Reporter.log("Click on Change Visible Columns button", true);
	}
	
	/**
	 * Helper method to verify Auditing Field is checked or not
	 * */
	public void isAuditingFieldChecked (String windowTitle) {
		WebElement reference = driver.findElement(By.xpath("//div[contains(@id,'"+ getWindowIdByClass(windowTitle)+"') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-grid3-body')]//div[contains(@id, 'group-Auditing')]//div[contains(@class, 'grid-group-title')]//..//input"));
		
		String value = reference.getAttribute("value");
		
		if(value.contains("on")){
			reference.click();
			Reporter.log("Auditing Header is Unchecked");
			waitForExtJSAjaxComplete(5);
			reference.click();
			Reporter.log("Auditing Header is Checked");
		} 
		
		Reporter.log("Field Auditing Header is Already Checked ", true);
	}
	
	/**
	 * Helper method to verify All columns are checked below Auditing
	 */
	public boolean isAuditingColumnsChecked(String windowTitle,String colClass, String colName ){
		try{
			driver.findElement(By.xpath("//div[contains(@id,'"+ getWindowIdByClass(windowTitle)+"') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-grid3-body')]//div[contains(@id, 'group-Auditing-bd') and contains(@class, 'group-body')]//div[contains(@class, '"+colClass+"')and text()='"+colName+"']/ancestor::div[contains(@class, 'row-selected')]"));
			return true;
		} catch(Exception e){
			System.out.println("Exception is " +e);
			return false;
		}
	}
	
	/**
	 * Helper method to verify All columns are checked below General
	 */
	public boolean isGeneralColumnsChecked(String windowTitle,String colClass, String colName ){
		try{
			driver.findElement(By.xpath("//div[contains(@id,'"+ getWindowIdByClass(windowTitle)+"') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-grid3-body')]//div[contains(@id, 'group-General-bd') and contains(@class, 'group-body')]//div[contains(@class, '"+colClass+"')and text()='"+colName+"']/ancestor::div[contains(@class, 'row-selected')]"));
			return true;
		} catch(Exception e){
			System.out.println("Exception is " +e);
			return false;
		}
	}
	
	/**
	 * Helper method to verify General Field is checked or not
	 * */
	public void isGeneralFieldChecked (String windowTitle) {
		WebElement reference = driver.findElement(By.xpath("//div[contains(@id,'"+ getWindowIdByClass(windowTitle)+"') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-grid3-body')]//div[contains(@id, 'group-General')]//div[contains(@class, 'grid-group-title')]//..//input"));
		
		String value = reference.getAttribute("value");
		
		if(value.contains("on")){
			reference.click();
			Reporter.log("General Header is Unchecked");
			waitForExtJSAjaxComplete(5);
			reference.click();
			Reporter.log("General Header is Checked");
		}
		
		Reporter.log("Field General Header is Already Checked ", true);
	}
	
	/**
	 * Helper method to verify Properties Tab in Change columns Window
	 */
	public boolean isPropertiesTabAvailable(String windowTitle) {
		try{
			driver.findElement(By.xpath("//div[contains(@id,'"+ getWindowIdByClass(windowTitle)+"') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-panel-header-noborder')]//span[contains(@class, 'header-text') and text()='Properties']"));
			return true;
		} catch(Exception e){
			System.out.println("Exception is " +e);
			return false;
		}
	}
	
	/**
	 * Helper method to verify Columns Tab in Change columns Window
	 */
	public boolean isColumnsTabAvailable(String windowTitle) {
		try{
			driver.findElement(By.xpath("//div[contains(@id,'"+ getWindowIdByClass(windowTitle)+"') and contains(@style, 'visibility: visible')]//div[contains(@class, 'x-panel-header-noborder')]//span[contains(@class, 'header-text') and text()='Columns']"));
			return true;
		} catch(Exception e){
			System.out.println("Exception is " +e);
			return false;
		}
	}
	
	public String getWindowIdByClass(String xwindowClass) throws NoSuchElementException {

		String elementXpath = "//div[contains(@class, '" + xwindowClass + "') and contains(@style, 'visibility: visible')]";

		WebElement webElement = new WebDriverWait(driver, configuration.getImplicitWait())
				.until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath(elementXpath)));
		
		return webElement.getAttribute("id");
	}
	
	/**
	 * Helper method to get User From Mcs Portal
	 */
	public String getLoggedUserName() {
		WebElement xpath = driver.findElement(By.xpath("//div[contains(@class, 'mcs-tb-glossy-strong')]//table[@class='x-toolbar-right-ct']//table[@id='top-account-menu']//..//em[contains(@class, 'x-btn-arrow')]/button"));
		
		String userName = xpath.getAttribute("textContent");
		
		System.out.println("userName " +userName);
		
		/*String split[] = userName.split(" ");
		String lastWord = split[1].toString();
		String firstWord = split[0].toString();
		
		userName = lastWord+" "+firstWord;
		
		System.out.println("userName " +userName);*/
		
		return userName;
	}
}