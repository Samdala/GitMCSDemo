package test.generalweb.helpdesk;




import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.FileUploader;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.seleniumhq.jetty7.server.ResourceCache.Content;
import org.testng.Reporter;

import com.opera.core.systems.scope.protos.EcmascriptProtos.Object;

public class HelpDeskFrontOfficePageObject extends CallDetailTabsPageObject {
	
	protected final String NEWCALL_TAB_XPATH = "//div[contains(@class,'helpdesk')]//button[contains(text(),'New Call')]";
	
	protected final String MYCALLS_TAB_XPATH = "//div[contains(@class,'helpdesk')]//button[contains(text(),'My Calls')]";
	
	protected final String MYTEAMSCALLS_TAB_XPATH = "//div[contains(@class,'helpdesk')]//button[contains(text(),'My Team')]";
	
	protected final String SUBJECT_XPATH = "//div[contains(@class,'helpdesk')]//input[@name='NEW_REFERENCE']";
	
	protected final String COMMENT_XPATH = "//div[contains(@class,'helpdesk')]//textarea[@name='NEW_ADD_COMMENT']";
	
	protected final String PRIORITY_XPATH = "//div[contains(@class,'helpdesk')]//input[@name='NEW_PRIORITY']/..//input[contains(@class,'x-form')]";
	
	protected final String BOOKCALL_XPATH = "//div[contains(@class,'helpdesk')]//button[contains(text(),'Book Call')]";
	
	protected final String ADD_URL_XPATH = "//button[text()='Add URL' and contains(@style,'icon-addurl.png')]";

	protected final String HYPERLINK_XPATH = "//input[@name='strHyperlink']";
	
	protected final String HYPERLINK_DESCRIPTION = "//input[@name='strDescription']";
	
	protected final String HYPERLINK_REMARK = "//input[@name='strRemark']";

	protected final String ADD_URL_CREATE_XPATH = "//div[contains(@class,'x-window')]//button[text()='Create']";

	protected final String ADD_URL_SAVE_XPATH = "(//div[contains(@class,'x-window')]//button[text()='Save'])[last()]";
	
	protected final String ADD_FILE_XPATH = "//button[text()='Add File' and contains(@style,'icon-addfile.png')]";

	protected final String EDIT_URL_XPATH = "//button[text()='Edit' and contains(@style,'icon-edit.png')]";

	protected final String DELETE_URL_XPATH = "//button[text()='Delete' and contains(@style,'icon-delete.png')]";
	
	
	public void clickNewCallTab(){
		clickXPath(NEWCALL_TAB_XPATH);
		Reporter.log("New call tab was clicked <br>", true);
	}	
		
	public void clickMyCallsTab(){
		clickXPath(MYCALLS_TAB_XPATH);
		Reporter.log("My calls tab was clicked <br>", true);
	}	
	
	public void clickMyTeamsCallsTab(){
		clickXPath(MYTEAMSCALLS_TAB_XPATH);
		Reporter.log("My team calls tab was clicked <br>", true);
	}	
	
	public void clickCallTemplate(String template){
		clickXPath("//div[contains(@class,'helpdesk')]//div[contains(text(),'"+template+"')]");
		Reporter.log("Click call template <br>", true);
	}	

	public void setSubject(String template){
		clickXPath(SUBJECT_XPATH);
		McsElement.getElementByXpath(driver, SUBJECT_XPATH).clear();
		McsElement.getElementByXpath(driver, SUBJECT_XPATH).sendKeys(template);
		clickXPath(SUBJECT_XPATH);
	}
	
	public void setComment(String template){
		clickXPath(COMMENT_XPATH);
		McsElement.getElementByXpath(driver, COMMENT_XPATH).clear();
		McsElement.getElementByXpath(driver, COMMENT_XPATH).sendKeys(template);
		clickXPath(COMMENT_XPATH);
	}
	
	public void setPriority(String priority){
		DropDown.setExtJsComboValue(driver, driver.findElement(By.xpath(PRIORITY_XPATH)).getAttribute("id"), priority);
	}
	
	public void setNature(String nature) {

		clickLookup("@class","x-panel", "NEW_NATURE","Select a Nature");
		
		waitForExtJSAjaxComplete(25);

		driver.findElement(By.xpath("//div[contains(@class,'x-resizable')]//span[contains(text(),'Tree')]")).click();

		setValueTreeLookup(new String[]{nature});
		
	}
	
	
	public void setCostCenter(String customer) {

		clickLookup("@class","x-panel", "NEW_COST_CTR","Select a Cost Center");
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a Cost Center"), customer, "Reference");
		
	}

	public void setGlAccount(String customer) {

		clickLookup("@class","x-panel", "NEW_GL_ACCOUNT","Select a GL Account");
		
		waitForExtJSAjaxComplete(25);
		
		clickXPath("//div[contains(@id,'"+ getXWindowId("Select a GL Account") + "')]//span[text()='List']");
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters("@id", getXWindowId("Select a GL Account"), customer, "Reference");
		
	}
	
	public void setUrl(String url, String description, String remark, String type){
		clickXPath(ADD_URL_XPATH);
		waitForExtJSAjaxComplete(25);
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

		clickXPath("//div[contains(@class,'x-window')]//label[contains(text(),'Type')]/..//button");
		
		waitForExtJSAjaxComplete(10);
		waitForExtJSAjaxComplete(10);
		
		try {driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			if (driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size() == xwindowNumber)
				{clickXPath("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");}
		}

		 finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
		}
		
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters(type, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		clickXPath(ADD_URL_CREATE_XPATH);
		waitForExtJSAjaxComplete(25);
		Reporter.log("url was set <br>", true);
	}
	

	public void editUrl(String url, String description, String descriptionEd, String remark, String type){
		clickXPath("//*[@class='x-grid3']//div[text()='"+description+"']");
		waitForExtJSAjaxComplete(25);
		//Grid.checkRowInGriByTextValueAndClick(driver, description);
		clickXPath(EDIT_URL_XPATH);
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

		clickXPath("//div[contains(@class,'x-window')]//label[contains(text(),'Type')]/..//button");
		
		waitForExtJSAjaxComplete(10);
		waitForExtJSAjaxComplete(10);
		
		try {driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			if (driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size() == xwindowNumber)
				{waitAndClick("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");}
		}

		 finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
		}
		
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters(type, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		clickXPath(ADD_URL_SAVE_XPATH);
		waitForExtJSAjaxComplete(25);
		Reporter.log("url was set <br>", true);
	}


	public void deleteUrl(String description){
		WebElement element = driver.findElement(By.xpath("//*[@class='x-grid3']//div[text()='"+description+"']"));
		waitFocusAndClick(element);
		waitForExtJSAjaxComplete(20);
                waitForMaskDisappear();

//		Grid.checkRowInGriByTextValueAndClick(driver, description);
		clickXPath(DELETE_URL_XPATH);
		waitForExtJSAjaxComplete(25);
		clickOnDialogButton("Yes");
		waitForExtJSAjaxComplete(25);
		Reporter.log("url or file was deleted <br>", true);
	}

	
	public void editFile(String file, String description, String descriptionEd, String remark, String type){
		clickXPath("//*[@class='x-grid3']//div[text()='"+description+"']");
		waitForExtJSAjaxComplete(25);
//		Grid.checkRowInGriByTextValueAndClick(driver, description);
		clickXPath(EDIT_URL_XPATH);
		waitForExtJSAjaxComplete(25);
		FileUploader.uploadFileName(driver, "@class", "x-window", file);
		waitForExtJSAjaxComplete(25);
		
		McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).clear();
		McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).sendKeys(descriptionEd);
		
		McsElement.getElementByXpath(driver, HYPERLINK_REMARK).clear();
		McsElement.getElementByXpath(driver, HYPERLINK_REMARK).sendKeys(remark);
		
		int xwindowNumber=0;
		
		try {driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			xwindowNumber = driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size();
		}
		finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);}

		clickXPath("//div[contains(@class,'x-window')]//label[contains(text(),'Type')]/..//button");
		
		waitForExtJSAjaxComplete(10);
		waitForExtJSAjaxComplete(10);
		
		try {driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			if (driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size() == xwindowNumber)
				{clickXPath("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");}
		}

		 finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
		}
		
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters(type, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		
		clickXPath(ADD_URL_SAVE_XPATH);
		waitForExtJSAjaxComplete(25);
		Reporter.log("file was uploaded <br>", true);
	}
	
	
	public void setFile(String file, String description, String remark, String type){
		clickXPath(ADD_FILE_XPATH);
		waitForExtJSAjaxComplete(25);
		FileUploader.uploadFileName(driver, "@class", "x-window", file);
		waitForExtJSAjaxComplete(25);
		
		
		McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).clear();
		McsElement.getElementByXpath(driver, HYPERLINK_DESCRIPTION).sendKeys(description);
		
		McsElement.getElementByXpath(driver, HYPERLINK_REMARK).clear();
		McsElement.getElementByXpath(driver, HYPERLINK_REMARK).sendKeys(remark);
		
		int xwindowNumber=0;
		
		try {driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			xwindowNumber = driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size();
		}
		finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);}

		clickXPath("//div[contains(@class,'x-window')]//label[contains(text(),'Type')]/..//button");
		
		waitForExtJSAjaxComplete(10);
		waitForExtJSAjaxComplete(10);
		
		try {driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			if (driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size() == xwindowNumber)
				{clickXPath("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");}
		}

		 finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
		}
		
		
		waitForExtJSAjaxComplete(25);
		
		setValueGridLookupWithFilters(type, "Reference");
		
		waitForExtJSAjaxComplete(25);
		

		clickXPath(ADD_URL_CREATE_XPATH);
		waitForExtJSAjaxComplete(25);
		Reporter.log("file was uploaded <br>", true);
	}
	
	public void closeXWindow(){
		clickXPath("(//div[contains(@class,'x-tool-close')])[last()]");
		Reporter.log("close x-window <br>", true);
	}
	
	public void openCallDetailsDialog(String textValue) {
		WebElement cell = Grid.checkRowInGriByTextValueAndClick(driver, textValue);
		//cell.click();
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		//Call is opened automatically after checking the check box in a grid
		/*Actions action = new Actions(driver);
		action.doubleClick(cell).perform();*/
		waitForExtJSAjaxComplete(20);
		Reporter.log("Double clicked on Call <br>", true);
	}
	
	public void verifyCallDetailsHeader(String CallID) {
		McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-window x-resizable-pinned')]//div[contains(text(), '" + CallID + "')]");
		Reporter.log("Header of call detail is correct", true);
	}
	
	public void clickBookCall(){
		clickXPath(BOOKCALL_XPATH);
		Reporter.log("Book call was clicked <br>", true);
	}
	
	/**
	 * Helper method to verify call template is present or not
	 * @param template
	 * @return
	 */
	public boolean isCallTemplateAvailable(String template){
		
		String xpath= "//div[contains(@class,'helpdesk')]//div[contains(text(),'"+template+"')]";
		
		try{
			
			McsElement.getElementByXpath(driver, xpath);
			
			return true;
		}
		catch(Exception e){
			return false;	 
		}	
	}
	
	public void collapseDetailsPanel(){
		
		String collapseIconXpath = "//span[@class='x-panel-header-text' and text()='Details']/../div[contains(@class,'x-tool x-tool-toggle')]";
		
		String expandIconXpath = "//span[text()='Details']/../../div[contains(@class,'x-tool-expand')]/parent::div[contains(@id,'xcollapsed') and contains(@style,'visibility: visible')]";
		
	
		if(!McsElement.isElementPresent(driver, expandIconXpath)){
			
			clickXPath(collapseIconXpath);
		}
		
	}

	/**
	 * Clicks on  Help Desk General Settings
	 */
	public boolean clickHelpDeskGeneralSettings() {
		try{
			Timer timer = new Timer().start();
			WebElement callTemplate = McsElement.getElementByPartAttributeValueAndParentElement(driver,
					"div", "@id", getXPanelId("Administration"), "span", "text()",
					"General Settings", true, true);
			javaScriptFocus(callTemplate);
			callTemplate.click();
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(10);
			Reporter.log("Click General Settings"+ " (" + timer.stop()
			+ "ms)", true);
			return true;
		}
		catch(Exception E){
			return false;
		}
	}

	/**
	 *  Helper method to verify Size of the grid
	 * @param gridName
	 * @return grid size
	 */
	public int getGridSize(String gridName) {

		List<WebElement> ele = driver.findElements(By.xpath("//label[text()='"+gridName+"']/ancestor::div[contains(@class,' x-grid-with-col-lines')]//div[@class='x-grid3-body']//div[contains(@class,'x-grid3-row')]"));
		int size = ele.size();
		return size;
	}


	/**
	 * checkVisibility To All Fields In General Settings Help Desk 
	 * @param sectionName 
	 * @param rowsToReduce count of grid by
	 * @return whether all rows are checked or not
	 */
	public boolean checkVisibilityOfAll(String sectionName, int rowsToReduce){

		int rowsCount = getGridSize(sectionName);
		boolean isChecked = false;
		int count = 1;
		int rowNumb;

		if(rowsCount==0){

			Reporter.log("No rows to check in section "+sectionName, true);

			return true;}

		else{ 
			for( rowNumb=1; rowNumb<=(rowsCount-rowsToReduce); rowNumb++)
			{

				isChecked = checkVisibility(rowNumb,sectionName);
				if(isChecked)
					count++;
			}	

			if(count == rowNumb){


				Reporter.log("All rows of section "+sectionName+" are checked",true);
				return true;
			}

			else{
				Reporter.log("Couldnt check all rows of "+sectionName ,true);
				return false;}
		}

	}



	/**
	 * Set visible value to field in General Setting of HelpDesk	
	 * @param rowNumb
	 * @param gridName
	 * @return
	 */
	public boolean checkVisibility(int rowNumb,String gridName){


		WebElement checkBoxStatus =  McsElement.getElementByXpath(driver,"(//label[text()='"+gridName+"']/ancestor::div[contains(@class,' x-grid-with-col-lines')]//div[@class='x-grid3-body']/div["+rowNumb+"]//input)");
		try{
			if(!checkBoxStatus.isSelected()){

				McsElement.getElementByXpath(driver,"//label[text()='"+gridName+"']/ancestor::div[contains(@class,' x-grid-with-col-lines')]//div[@class='x-grid3-body']/div["+rowNumb+"]").click();
				waitForExtJSAjaxComplete(20);
				waitForExtJSAjaxComplete(20);
				McsElement.getElementByXpath(driver,"(//label[text()='"+gridName+"']/ancestor::div[contains(@class,' x-grid-with-col-lines')]//button[text()='Set Override'])").click();
				waitForExtJSAjaxComplete(20);
				waitForExtJSAjaxComplete(20);
				McsElement.getElementByXpath(driver,"(//label[text()='"+gridName+"']/ancestor::div[contains(@class,' x-grid-with-col-lines')]//div[@class='x-grid3-body']/div["+rowNumb+"]//input)").click();
				Reporter.log("Row "+rowNumb+" of grid "+gridName+" is checked", true);
				return true;	
			}
			else{
				Reporter.log("Row "+rowNumb+" of grid "+gridName+" is already checked",true);
				return true;}
		}
		catch(Exception E){
			Reporter.log("Unable to check status of row "+rowNumb+" of grid "+gridName,true);
			return false;
		}
	}

/**
 * UnChecks visibility of a Field
 * @param rowNumb
 * @param gridName
 * @return
 */
	public boolean unCheckVisibility(int rowNumb,String gridName){
		WebElement checkBoxStatus =  McsElement.getElementByXpath(driver,"(//label[text()='"+gridName+"']/ancestor::div[contains(@class,' x-grid-with-col-lines')]//div[@class='x-grid3-body']/div["+rowNumb+"]//td[contains(@class,'x-grid3-td-2')]//input[contains(@class,'x-form-checkbox')])");
		try{
			if(checkBoxStatus.isSelected()){

				McsElement.getElementByXpath(driver,"//label[text()='"+gridName+"']/ancestor::div[contains(@class,' x-grid-with-col-lines')]//div[@class='x-grid3-body']/div["+rowNumb+"]").click();
				waitForExtJSAjaxComplete(20);
				waitForExtJSAjaxComplete(20);
				McsElement.getElementByXpath(driver,"(//label[text()='"+gridName+"']/ancestor::div[contains(@class,' x-grid-with-col-lines')]//button[text()='Set Override'])").click();
				waitForExtJSAjaxComplete(20);
				waitForExtJSAjaxComplete(20);
				McsElement.getElementByXpath(driver,"(//label[text()='"+gridName+"']/ancestor::div[contains(@class,' x-grid-with-col-lines')]//div[@class='x-grid3-body']/div["+rowNumb+"]//td[contains(@class,'x-grid3-td-2')]//input[contains(@class,'x-form-checkbox')])").click();
				Reporter.log("Row "+rowNumb+" of grid "+gridName+" is Unchecked", true);
				return true;	
			}
			else{
				Reporter.log("Row "+rowNumb+" of grid "+gridName+" is already Unchecked",true);
				return true;}
		}
		catch(Exception E){
			Reporter.log("Unable to Uncheck status of row "+rowNumb+" of grid "+gridName,true);
			return false;
		}


	}

	/**
	 * Set web account group in General Setting of HelpDesk
	 */
	public void setWebAccountGroup(String accGroup){
		DropDown.setExtJsComboValue(driver, driver.findElement(By.xpath("//label[contains(text(),'Web Account Group')]/..//input[contains(@class,'x-form')]")).getAttribute("id"), accGroup);
	}

	/**
	 * Is Field Displayed as ReadOnly(Label)
	 * @param fieldNames
	 * @return status of field
	 */
	public boolean isFieldsDisplayedAsReadOnlyInDetailsWindow(List<String> fieldNames) {

		boolean isAllFieldsPresent = true;          
		for(String fieldName: fieldNames){

			if(fieldName.contains("Document number")){
				fieldName = "Document Number";
			}

			if(isFieldDisplayedInDetailsWindow(fieldName)){

				if(isFieldReadOnlyInDetailsWindow(fieldName)){

					Reporter.log("Field "+fieldName+" is  found in General Tab as Read Only",true);
				}
				else{

					Reporter.log("Field "+fieldName+" is found in General Tab but not as Read Only",true);
					isAllFieldsPresent = false;
					break;
				}

			}else{

				isAllFieldsPresent = false;
				Reporter.log("Field "+fieldName+" is not found in General Tab",true);
				break;
			}

		}

		return (isAllFieldsPresent)? true: false;

	}

	/**
	 * Whether field is present or not 
	 * @param fieldName
	 * @return is present or not
	 */
	public boolean isFieldDisplayedInDetailsWindow(String fieldName){
		String filed_Xpath = DETAILS_WINDOW_CLASS+ "//label[contains(@class,'x-box-item') and text()='"+ fieldName+ "']";
		try{
			McsElement.getElementByXpath(driver,filed_Xpath );
			Reporter.log("Field "+fieldName+" is found in General Tab",true);
			return true;
		}catch(Exception e){
			Reporter.log("Field "+fieldName+" is not found in General Tab",true);

			return false;
		}

	}

	/**
	 * Checks whether the field is displayed and is read only(label)
	 * @param fieldName
	 * @return
	 */
	public boolean isFieldReadOnlyInDetailsWindow(String fieldName ){

		String field_Value_Xpath = DETAILS_WINDOW_CLASS+ "//label[contains(@class,'x-box-item') and text()='"+ fieldName+ "']/following-sibling::label[contains(@class,'x-box-item')]";


		try {

			McsElement.getElementByXpath(driver, field_Value_Xpath);
			Reporter.log("Field "+fieldName+" is  found in General Tab as Read Only",true);
			return true;	} 

		catch (Exception e) {

			Reporter.log("Field "+fieldName+" is  found in General Tab but not Read Only",true);

			return false;}
	}


	/**
	 * Gets all the field names in a section
	 * @param gridName
	 * @param rowsToReduce
	 * @return List of fields 
	 */
	public List<String> getAllFieldNamesOfSectionInGeneralSettings(String gridName, int rowsToReduce ){

		List<String> fieldNamesList = new ArrayList<String>();

		int maxRowNum = getGridSize(gridName);

		for(int i =1; i<=(maxRowNum-rowsToReduce); i++){

			fieldNamesList.add(getFieldNameInGeneralSettings(gridName,i));

		}

		return fieldNamesList;

	}

	/**
	 * Get single field name of a section
	 * @param gridName
	 * @param rowNum
	 * @return field name
	 */
	public String getFieldNameInGeneralSettings(String gridName ,int rowNum)
	{
		return McsElement.getElementByXpath(driver,"(//label[text()='"+gridName+"']/ancestor::div[contains(@class,' x-grid-with-col-lines')]//div[@class='x-grid3-body']/div["+rowNum+"]//label)").getText(); 
	}

	/**
	 * To Save Changes in General Setting
	 * @return save status
	 */
	public boolean clickOnSaveChangesInGeneralSettings( ){

		String buttonStatus = "//div[contains(@class,'admsettings-generalsettings')]//button[text()='Save Changes']/ancestor::table[contains(@class,'x-btn-text-icon x-item-disabled')]";
		if(!McsElement.isElementPresent(driver,buttonStatus)){
			try{
				McsElement.getElementByXpath(driver,"//div[contains(@class,'admsettings-generalsettings')]//button[text()='Save Changes']").click();
				McsElement.getElementByXpath(driver,"//button[text()='Yes']").click();
				waitForExtJSAjaxComplete(20);
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
	
	
	/**
	 * Helper method to get List of Call Templates
	 * @return
	 */
	public  List<String> getListOfCallTemplatesInNewCallWindow(){
		List<String> templates = new ArrayList<String>();
		List<WebElement> ele = driver.findElements(By.xpath("//div[contains(@class,' helpdesk x-panel-noborder')]//div[contains(@class,'template-tree')]//div[@class='tplLabel']"));
		for(WebElement template : ele)
			templates.add(template.getText());
		return templates;
		
	}
	
	/**
	 * Helper method to click on Tree tab in Nature window
	 */
	public void clickTreeTab(){
		driver.findElement(By.xpath("	//div[contains(@class,'x-resizable-pinned') and contains(@style,'visibility: visible')]//span[text()='Tree']/ancestor::li[contains(@id,'nature_tree')]")).click();
	}
	
	/**
	 * Whether the field is displayed or not in create call from template panel 
	 * @param fieldName
	 * @return
	 */
	public boolean isFieldDisplayedInCreateCallFromTemplatePanel(String fieldName){
		String fieldXPath = "//div[contains(@class,' helpdesk-newcall')]//label[text()='"+fieldName+"']";
		try{
			McsElement.getElementByXpath(driver,fieldXPath );
			Reporter.log("Field "+fieldName+" is found in General Tab",true);
			return true;
		}catch(Exception e){
			Reporter.log("Field "+fieldName+" is not found in General Tab",true);

			return false;
		}
	}	
	
	/**
	 * Helper method to get field Values from create from template panel
	 * @param fieldName
	 * @param name 
	 * @return filed value
	 */
	public String getFieldValuesFromCreateCallFromTemplatePanel(String fieldName, String name){
		String xpath = "//div[contains(@class,'helpdesk-newcall')]//label[contains(@class,'x-form-item') and text()='"+fieldName+"']/..//input[@name='"+name+"']/following-sibling::input";
		return driver.findElement(By.xpath(xpath)).getAttribute("value");
	}

	/**
	 * Helper method to field status in create call from template panel
	 * @param fieldName
	 * @return true/false
	 */
	public boolean isFieldEditableInCreateCallFromTemplatePanel(String fieldName){
		String fieldXpath = "//div[contains(@class,'helpdesk-newcall')]//input[@name='"+fieldName+"']/parent::div[not(contains(@class,'x-item-disabled')) and contains(@class,'x-form-field-wrap')]";
		return McsElement.isElementPresent(driver, fieldXpath);
	}

	/**
	 * Helper method to set caller in caller field 
	 * @param caller to be set 
	 */
	public void setCaller(String caller){
		clickLookup("@class","x-panel", "NEW_CALLER","Select a Caller");

		waitForExtJSAjaxComplete(25);

		setValueGridLookupWithFilters("@id", getXWindowId("Select a Caller"), caller, "Name");

		Reporter.log("Employee is defined in the 'Caller' field",true);
	}
	
	/**
	 * Helper method to select Location
	 */
	public void setLocation(String reference) {

		clickLookupNewUI("@class","x-panel-body-noborder", "NEW_LOCATION","Select a Location");

		waitForExtJSAjaxComplete(25);

		setValueGridLookupWithFiltersNewUI("@class", "x6-window-default-closable", reference, "Reference");
		waitForExtJSAjaxComplete(25);
		Reporter.log("location "+ reference+" was set", true);
	}
	
}