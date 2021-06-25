package test.generalweb.inspections;

import java.util.HashSet;
import java.util.List;


import java.util.ArrayList;
import java.util.List;



import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class InspectionAdministrationPageObject extends AbstractMcsTestSuite {

	protected String INSPECTIONTEMPLATE_WIN_XPATH = "//div[contains(@class,'x-window x-resizable-pinned') and contains(@style,'visibility: visible')]";

	protected String INSPECTION_GRID_XPATH =INSPECTIONTEMPLATE_WIN_XPATH+"//div[@id ='inspPointGrid']";



	/**
	 * Helper method to click a tab in inspectionTemplate	
	 * @param tabName to click
	 */
	public void  clickTabinInspectionTemplate(String tabName)

	{

		driver.findElement(By.xpath(INSPECTIONTEMPLATE_WIN_XPATH+"//span[contains(@class,'x-tab-strip-text') and contains(text(),'"+tabName+"')]")).click();
		
	}



	/**
	 * Helper method to move inspection point to specified row
	 * @param inspectionPoint to move
	 * @param rowNum to which inspection point has to be moved
	 */
	public void moveInspectionPointToSpecifiedRowByDragDrop(String inspectionPoint, int rowNum){

		int sourceInspectionPointRowNum = getRowNumOfInspectionPoint( inspectionPoint);
		int destInspectionPointRowNum = rowNum;

		dragAndDropInspectionPoint(sourceInspectionPointRowNum,destInspectionPointRowNum);

	}

	/**
	 * Helper method to move a specified inspection point into place of another inspection point
	 * @param srcinspectionPoint - inspection point whose position has to change
	 * @param destInpsectionPoint to which source inspection point has to be dragged
	 */
	public void moveInspectionPointToSpecifiedRowByDragDrop(String srcinspectionPoint, String destInpsectionPoint){

		int sourceInspectionPointRowNum = getRowNumOfInspectionPoint( srcinspectionPoint);
		int destInspectionPointRowNum = getRowNumOfInspectionPoint( destInpsectionPoint);;

		dragAndDropInspectionPoint(sourceInspectionPointRowNum,destInspectionPointRowNum);


	}


	/**
	 *	Helper method to move an Inspection point to specified row number by clicking Move up/down buttons 
	 * @param inspectionPoint  inspection point whose position has to change
	 * @param rowNum to which inspection point has to be moved
	 */
	public void moveInspectionPointToSpecifiedRowByClickingMoveButtons(String inspectionPoint, int rowNum){

		int sourceInspectionPointRowNum = getRowNumOfInspectionPoint( inspectionPoint);

		moveInspectionPoint(sourceInspectionPointRowNum,rowNum);


	}

	/**
	 * Helper Method to move srcinspectionPoint point to destInpsectionPoint
	 */
	
	/**
	 *	Helper method to move an Inspection point to position of Inspection point specified  by clicking Move up/down buttons 
	 * @param inspectionPoint  inspection point whose position has to change
	 * @param destInpsectionPoint to which inspection point has to be moved
	 */

	public void moveInspectionPointToSpecifiedRowByClickingMoveButtons(String inspectionPoint, String destInpsectionPoint){

		int sourceInspectionPointRowNum = getRowNumOfInspectionPoint( inspectionPoint);
		int destInspectionPointRowNum = getRowNumOfInspectionPoint( destInpsectionPoint);

		moveInspectionPoint(sourceInspectionPointRowNum,destInspectionPointRowNum);


	}

	
	/**
	 * Helper method to drag an Inspection point at specified row number
	 * @param sourceInspectionPointRowNum - row number where inspection point is present
	 * @param destInspectionPointRowNum - row number to which inspection point has to be moved
	 */

	private void dragAndDropInspectionPoint(int sourceInspectionPointRowNum , int destInspectionPointRowNum){

		String srcxpath = INSPECTION_GRID_XPATH+"//div[contains(@class,'x-grid3-col-rowIndex') and text()='"+sourceInspectionPointRowNum+"']";

		String destxpath = INSPECTION_GRID_XPATH+"//div[contains(@class,'x-grid3-col-rowIndex') and text()='"+destInspectionPointRowNum+"']";

		WebElement sourceEle =driver.findElement(By.xpath(srcxpath));

		WebElement destinationEle =driver.findElement(By.xpath(destxpath)); 

		Actions action = new Actions(driver);
		action.dragAndDrop(sourceEle, destinationEle).build().perform();


	}

	
	/**
	 * Helper Method to get row number of the specified inspection point
	 * @param inspectionPoint
	 * @return row number of the specified inspection point
	 */

	private int getRowNumOfInspectionPoint(String inspectionPoint)
	{
		int colNum = getGridColumnNumberWithoutQuickFilters("Reference");

		String xpath = INSPECTION_GRID_XPATH+"//div[contains(@class,'x-grid3-col-"+colNum+"') and text()='"+inspectionPoint+"']/../..//div[contains(@class,'x-grid3-col-rowIndex')]"; 

		String rowNum = McsElement.getElementByXpath(driver, xpath).getText().trim();	

		return Integer.parseInt(rowNum);
	}

	
	/**
	 * Helper method to get column number in Grid
	 * @param columnName of whose column number has to be obtained
	 * @return column number
	 */
	public int getGridColumnNumberWithoutQuickFilters(String columnName){

		WebElement ele = driver.findElement(By.xpath(INSPECTION_GRID_XPATH+"//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')][last()]"));

		String columnClass = ele.getAttribute("class"); 
		String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);

		return Integer.parseInt(columnNumber);

	}

	

	/**
	 * Helper method to move Inspection Point in source row to row specified in Destination
	 * @param srcRowNum - row number where inspection point is present
	 * @param destRowNum - row number to which inspection point has to be moved
	 */
	public void moveInspectionPoint(int srcRowNum, int destRowNum){


		driver.findElement(By.xpath("//div[contains(@class,'x-grid3-col-rowIndex') and (text()='"+srcRowNum+"')]")).click();

		int diff = srcRowNum-destRowNum;

		if(diff==0){

			Reporter.log("Both source inspection is already present at required destination", true);
		}
		else if(diff>0){
			for(int i=1;i<=diff;i++)
			{
				clickMoveUp();
			}
		}else{

			for(int i=1;i<=(-diff);i++)
			{
				clickMoveDown();
			}

		}

	}


	/**
	 * Helper method to click move up icon
	 */
		
	public void clickMoveUp()

	{
		driver.findElement(By.xpath(INSPECTION_GRID_XPATH+"//button[contains(@class,'icon-move-row-up')]")).click();
	}


	/**
	 * Helper method to click move down icon
	 */

	public void clickMoveDown()
	{
		driver.findElement(By.xpath(INSPECTION_GRID_XPATH+"//button[contains(@class,'icon-move-row-down')]")).click();
	}

	//**********************************Button Clicks**************************************************************************************



	/**
	 * Helper method to click on button specified
	 * @param btnName to click
	 */
	public void clickButtonInspectionTemplate(String btnName){

		String xpath =INSPECTIONTEMPLATE_WIN_XPATH+"//button[contains(.,'"+btnName+"')]";

		McsElement.getElementByXpath(driver, xpath).click();

		Reporter.log("Clicked Button "+btnName+" in Inspection Template ", true);
	}



	/**
	 *  Helper method to open inspectionTemplate Details
	 *  @param inspectionTemplate to open
	 */

	public void openInspectionTemplateDetails(String  inspectionTemplate) {
		WebElement cell = Grid.checkRowInGriByTextValueAndClick(driver,  inspectionTemplate);
		Actions action = new Actions(driver);
		action.doubleClick(cell).perform();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Double clicked on Inspection Template ID<br>", true);
	}
	
	/**
	 *  Helper method to get inspection points
	 * @return list
	 */
	public List<String> getInspectionPointsOfInspectionTemplate()
	{
		int colNum =getGridColumnNumberWithoutQuickFilters("Reference");
		
		List<String> availableInspectionPoints = new ArrayList<String>();
		
		List<WebElement> inspectionPointsList =driver.findElements(By.xpath(INSPECTIONTEMPLATE_WIN_XPATH+"//div[contains(@class,'x-grid3-body')]//td[contains(@class,'x-grid3-td-"+colNum+"')]//div"));
			
		for(WebElement inspectionPointsEle:inspectionPointsList ){
			
			availableInspectionPoints.add(inspectionPointsEle.getText().trim());	
			
		}
		return availableInspectionPoints;

	}
	
	/**
	 * Helper method to Expand Module settings
	 */
	
	public void expandModuleSettings() {
		expandNode("div","@id",getXPanelId("Administration"),"Module Settings");
		}
	
	/**
	 * Helper method to click Inspection Template
	 */
	public void clickInspectionTemplate() {
		Timer timer = new Timer().start();
		WebElement inspectionTemplate = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@id", getXPanelId("Administration"), "span", "text()",
				"Inspection Templates", true, true);
		javaScriptFocus(inspectionTemplate);
		inspectionTemplate.click();
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(10);
		Reporter.log("Click inspection template"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	/**
	 * Helper method to click Button on InspectionTemplate overview
	 * @param btnName to click
	 */
	public void clickButton(String btnName) {
		
		String xpath="//div[contains(@class,'x-btn-group-notitle')]//button[contains(.,'"+btnName+"')]";
		
		McsElement.getElementByXpath(driver, xpath).click();

		Reporter.log("Clicked Button "+btnName+" in Inspection Template Overview ", true);
		
	}
	
	/**
	 * Helper method to pass Reference
	 * @param referenceText is for reference name
	 */
	public void setReference(String referenceText) {
		WebElement reference = McsElement.getElementByXpath(driver, INSPECTIONTEMPLATE_WIN_XPATH+"/../..//label[contains(text(),'Reference:')]//..//input");
		
			reference.clear();
			reference.sendKeys(referenceText);
			Reporter.log("Set reference", true);
		}
	
	/**
	 * Helper method to click InspectionPoint
	 * @param srcRowNum is row number
	 */
	public void clickInspectionPoint(int srcRowNum) {
		
		String xpath =INSPECTION_GRID_XPATH+"//div[contains(@class,'x-grid3-col-rowIndex') and (text()='"+srcRowNum+"')]";
		
		driver.findElement(By.xpath(xpath)).click();
		
		Reporter.log("Clicked inspectionPoint row number "+srcRowNum+" ", true);
		
	}
	
	/**
	 * Method to count number of inspectionPoints  in grid 
	 */
	public int getInspectionPointsCount() {
		
		Timer timer = new Timer().start();
		
		int colNum = getGridColumnNumberWithoutQuickFilters("Reference");
		
		waitForExtJSAjaxComplete(25);
			
		List<WebElement> values = driver.findElements(By.xpath(INSPECTION_GRID_XPATH+"//div[contains(@class,'x-grid3-col-"+colNum+"')]"));
			
	    int count =values.size();
	    
	    Reporter.log(count + "number of rows are presented in grid"+ " (" + timer.stop()+ "ms)", true);
	    
		return count;

	}
	
	/**
	 * Method to close the inspectionTemplates form 
	 */
	public void closeInspectionTemlate()
	{
		closeUsingToolBar(INSPECTIONTEMPLATE_WIN_XPATH);
	}
	
	/**
	 * Method to Add inspectionTemplate 
	 * @param Reference is inspectionTemplate Reference
	 */
	public void addInspectionTemplate(String Reference,String Location)
	{
		
		clickButton("Add");
		  
		waitForExtJSAjaxComplete(20);
		  
		setReference(Reference);
		  
		waitForExtJSAjaxComplete(20);
		  
		clickLookupNewUI("x-window x-resizable-pinned","location");
		  
	    waitForExtJSAjaxComplete(20);
		 
		setValueGridLookupWithFiltersNewUI("@class","x6-tree-arrows",Location,"Reference");
		  
		waitForExtJSAjaxComplete(20);
		  
		clickButtonInspectionTemplate("Save Changes");
		  
		waitForExtJSAjaxComplete(20);
	}
	/**
	 * Method to Add inspectionPoint  
	 * @param inspPoint is inspectionPoint name
	 */
	public void addInspectionPoint(String inspPoint)
	{
		clickButtonInspectionTemplate("Add");
		  
	    waitForExtJSAjaxComplete(20);
	   
	    setValueGridLookupWithFiltersNewUI("@class","x6-tree-arrows",inspPoint,"Reference");
	    
	    waitForExtJSAjaxComplete(20);
	}
	
	/**
	 * Method to Delete  inspectionPoints 
	 */
	
	 public void deleteAllInspectionPoints()
	 
	 {
		for(int i=getInspectionPointsCount();i>=1;i--)
				
		{

			clickInspectionPoint(i);

			waitForExtJSAjaxComplete(20);

			clickButtonInspectionTemplate("Remove");

			clickOnDialogButton("Yes");

			waitForExtJSAjaxComplete(30);

		}

	 }

	 /**
	  * Navigate to home page and relogin
	  * @param NAME_FOR_RIGHT to log into
	  * @param PASSWORD_FOR_RIGHT password 
	  */
	 public void navigateToMainPage(String NAME_FOR_RIGHT,String PASSWORD_FOR_RIGHT) {
		 Timer timer = new Timer().start();
		 //	forceLogout();
		 logout();
		 waitForExtJSAjaxComplete(25);
		 waitForExtJSAjaxComplete(25);
		 login(NAME_FOR_RIGHT, PASSWORD_FOR_RIGHT);
		 waitForExtJSAjaxComplete(25);
		 waitForExtJSAjaxComplete(25);
		 Reporter.log("Login to back-end under account "+NAME_FOR_RIGHT +" END\n", true);
		 Reporter.log("<br />");
		 Reporter.log("Logged in to the Portal"+ " (" + timer.stop() + "ms)\n", true);
		 Reporter.log("<br />");
	 }

	 /**
	  * Helper method check whether button is present or not
	  * @param attributeValue element present in page 
	  * @param buttonName to be checked 
	  * @return true if present
	  */
	 public boolean isButtonPresent(String attributeValue, String buttonName){
		 return McsElement.isElementDisplayed(driver,"//div[contains(@class,'"+attributeValue+"')]//button[contains(@class,'x-btn-text') and text()='"+buttonName+"']");
	 }

	 /**
	  * Helper method to check whether node is enabled or disabled
	  * 
	  * @param nodeName to be checked
	  * @return true if disabled
	  */
	 public boolean isNodeDisabled(String nodeName){
		 String className = driver.findElement(By.xpath("//div[contains(@class,'mcs-tree-navigation')]//span[text()='"+nodeName+"']/../parent::div")).getAttribute("class");
		 return ((className.contains("disabled"))? true : false);
	 }
	 /**
	  * Helper method to check all the rows in a grid 
	  */
	 public void gridChecker(){
		 String xpath = "(//div[contains(@class,'x-grid3-hd-checker')])[last()]";
		 driver.findElement(By.xpath(xpath)).click();
	 }

	 /**
	  * Helper method to verify whether the button is present or not 
	  * @param tabName to be verified
	  * @return true if present 
	  */
	 public boolean isTabPresent(String tabName){
		 try{
			 driver.findElement(By.xpath(INSPECTIONTEMPLATE_WIN_XPATH+"//span[contains(@class,'x-tab-strip-text') and contains(text(),'"+tabName+"')]"));
			 return true;
		 }
		 catch(Exception E){
			 return false;
		 }
	 }

	 /**
	  * Helper method to verify whether area is present in 'Select Location' lookup
	  * @return false if area is present in location lookup 
	  */
	 public boolean isAreaPresentInLocationLookup(){
		 int count = 0;
		 String xpath = "//div[@id='"+getNewXWindowID("Select Locations")+"']//div[contains(@class,'item-container')]//img[contains(@class,'x6-tree-icon-custom')]";
		 List<WebElement> elements = driver.findElements(By.xpath(xpath));

		 for(WebElement element : elements){
			 String styleValue = element.getAttribute("style");
			 count = ( styleValue.contains("SITE") || styleValue.contains("BUILDING") ||styleValue.contains("FLOOR"))? count :count++ ; 
		 }

		 return (count>=1)? true : false;
	 }

	 /**
	  * Helper method to check 'is it possible to set inspection point other than room'
	  * @param attribute - attribute (@class, @id) of the lookup window
	  * @param attributeValue - attribute of the lookup must contain this value
	  * @param rowTextName - row text to be selected from lookup
	  * @param columnName - columnName of the row to be selected
	  * @return possible(true)/not possible(false)
	  */
	 public boolean isSelectingInspectionPointOtherThanRoomPossible(String attribute, String attributeValue, String rowTextName, String columnName){
		 filterGridNewUI(attribute, attributeValue, rowTextName, columnName);	
		 waitForExtJSAjaxComplete(25);

		 String columnID = McsElement.getElementByPartAttributeValueAndParentElement(driver,"div", attribute, attributeValue,"div","@role", "columnheader",".", columnName, true, true).getAttribute("id");
		 waitForExtJSAjaxComplete(10);

		 driver.findElement(By.xpath("//div[contains(@class,'x6-window-closable')]//td[contains(@class,'x6-grid-cell-"+columnID + "')]")).click();
		 waitForExtJSAjaxComplete(10);

		 String addButton = "//div[contains(@class,'x6-window-closable')]//span[contains(@style,'add')]/../../..";
		 String addButtonClassName = driver.findElement(By.xpath(addButton)).getAttribute("class");
		 return (addButtonClassName.contains("disabled"))? false : true;
	 }

	 /**
	  * Helper method to get Field value
	  * @param fieldName to retrieve value
	  * @param attribute field type
	  * @return field value
	  */
	 public String getFieldValue(String fieldName,String attribute){
		 waitForExtJSAjaxComplete(30);
		 WebElement reference = McsElement.getElementByXpath(driver, INSPECTIONTEMPLATE_WIN_XPATH+"/../..//label[contains(text(),'"+fieldName+"')]//..//"+attribute+"[contains(@class,'x-form-field')]");
		 return reference.getAttribute("value");
	 }

	 /**
	  * Helper method to uncheck the field in InspectionTemplateWindow
	  * @param fieldName to be unchecked
	  */
	 public void uncheckFieldInInspectionTemplateWindow(String fieldName){
		 String xpath = "//label[contains(text(),'"+fieldName+"')]/..//input[contains(@class,'x-form-checkbox')] ";
		 String fieldStatus = driver.findElement(By.xpath(xpath)).getAttribute("checked");
		 if(!((fieldStatus==null)||fieldStatus.equals("false"))){
			 driver.findElement(By.xpath(xpath)).click();
			 Reporter.log(""+fieldName+" is Unchecked",true);
		 }
		 else
			 Reporter.log(""+fieldName+" is already Unchecked",true);
	 }

	 /**
	  * Helper method to set description 
	  * @param descriptionText to be set
	  */
	 public void setDescription(String descriptionText){
		 WebElement reference = McsElement.getElementByXpath(driver, INSPECTIONTEMPLATE_WIN_XPATH+"/../..//label[contains(text(),'Description:')]//..//textarea");
		 reference.clear();
		 reference.sendKeys(descriptionText);
		 Reporter.log("Set reference", true);
	 }

	 /**
	  * Helper method to set Label value in label/ Description field
	  * @param language to fill across (English/ Dutch)
	  * @param labelText value to be set
	  * @param fieldName to be filled (Label/ Description)
	  */
	 public void setLabelValue(String language, String labelText,String fieldName){
		 String xpath = INSPECTIONTEMPLATE_WIN_XPATH + "//div[text()='"+fieldName+"']";
		 String columnClass = driver.findElement(By.xpath(xpath)).getAttribute("class");
		 String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
		 WebElement label = driver.findElement(By.xpath("//div[contains(@class,'x-grid3-viewport')]//div[text()='"+language+"']/..//following-sibling::td[contains(@class,'x-grid3-cell x-grid3-td-"+columnNumber+"')]//div[contains(@class,'x-grid3-cell-inner')]"));
		 label.click();
		 Actions actions = new Actions(driver);
		 actions.sendKeys(label, labelText).sendKeys(Keys.TAB).release().perform();
	 }

	 /**
	  * Helper method to get label field value
	  * @param language (English/Dutch) 
	  * @param fieldName to get value (Label/ Description)
	  * @return label value
	  */
	 public String getLabelValue(String language,String fieldName){
		 String xpath = INSPECTIONTEMPLATE_WIN_XPATH + "//div[text()='"+fieldName+"']";
		 String columnClass = driver.findElement(By.xpath(xpath)).getAttribute("class");
		 String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);
		 String labelText = driver.findElement(By.xpath("//div[contains(@class,'x-grid3-viewport')]//div[text()='"+language+"']/..//following-sibling::td[contains(@class,'x-grid3-cell x-grid3-td-"+columnNumber+"')]/div")).getText();
		 return labelText;
	 }

	 /**
	  * Helper method to click on remove button in Inspection Template window
	  * @iconName to be clicked
	  */
	 public void clickRemoveButtonInInspTempWindow(String iconName){
		 String xpath =INSPECTIONTEMPLATE_WIN_XPATH+"//button[contains(@class,'icon-row-"+iconName+"') and text()='Remove']";
		 McsElement.getElementByXpath(driver, xpath).click();
		 Reporter.log("Button Remove is clicked in Inspection Template Window",true);
	 }

	 /**
	  * Helper method to verify is account present in grid or not
	  * @param accountName which needs to be verified
	  * @param accountType type of account to be verified (icon-account/icon-group)
	  * @return true if present
	  */
	 public boolean isRowPresentInSecurityTab(String accountName,String accountType){
		 String xpath = "//div[@class='x-list-body-inner']//img[contains(@src,'"+accountType+"')]/../../../..//dl[contains(.,'"+accountName+"')]";
		 return McsElement.isElementDisplayed(driver, xpath);
	 }

	 /**
	  * Helper method to uncheck the field in InspectionTemplateWindow
	  * @param fieldName to be unchecked
	  * @attribute type of account
	  */
	 public void checkFieldInInspectionTemplateWindow(String fieldName,String attribute){
		 String xpath = "//"+attribute+"[contains(text(),'"+fieldName+"')]/../..//input[contains(@class,'x-form-checkbox')] ";
		 String fieldStatus = driver.findElement(By.xpath(xpath)).getAttribute("checked");
		 if(fieldStatus==null||fieldStatus.equals("false")){
			 driver.findElement(By.xpath(xpath)).click();
			 Reporter.log(""+fieldName+" is checked",true);
		 }
		 else
			 Reporter.log(""+fieldName+" is already checked",true);
	 }

	 /**
	  * Helper method to select account 
	  * @param accountName to be selected
	  * @param accountType type of account(icon-account/icon-group)
	  */
	 public void selectAccount(String accountName,String accountType){
		 String xpath = "//div[@class='x-list-body-inner']//img[contains(@src,'"+accountType+"')]/../../../..//dl[contains(.,'"+accountName+"')]";
		 driver.findElement(By.xpath(xpath)).click();
	 }

	 /**
	  * Helper method to verify the icon on tree node
	  * @param panelName which contains the icon
	  * @param iconName to verify
	  * @param nodeName which contains icon
	  * @return true(if present)
	  */
	 public boolean isIconPresentOnTreeNode(String panelName,String iconName,String nodeName){
		 String xpath = "//div[contains(@id,'"+getXPanelId(panelName)+"')]//img[contains(@class,'"+iconName+"')]//following-sibling::a//span[text()='"+nodeName+"']";
		 return McsElement.isElementDisplayed(driver, xpath);
	 }

	 /**
	  * Helper method to verify node under module settings
	  * @param nodeName to be verified
	  * @return true(if present)
	  */
	 public boolean isNodePresentUnderModuleSettings(String nodeName){
		 String nodeXpath = "//div[contains(@class,'mcs-tree-section') and contains(.,'Module Settings')]/..//ul[contains(@style,'visibility: visible')]//span[text()='"+nodeName+"']";
		 return McsElement.isElementDisplayed(driver, nodeXpath);
	 }

	 /**
	  * Helper method to check whether button is enabled or not
	  * @param buttonName to check
	  * @return true(if enabled)
	  */
	 public boolean isButtonEnabled(String buttonName){
		 String buttonXpath = "//div[contains(@class,'x-btn-group-notitle')]//table[contains(@class,'x-btn') and contains(.,'"+buttonName+"')]";
		 String className = driver.findElement(By.xpath(buttonXpath)).getAttribute("class");
		 return (className.contains("disabled"))? false : true;
	 }
	 
	 /**
	  * Helper method check whether button is present or not
	  * @param buttonName to be checked 
	  * @return true if present
	  */
	 public boolean isButtonPresentOnInspectionTemplate(String buttonName){
		 String xpath =INSPECTIONTEMPLATE_WIN_XPATH+"//button[contains(.,'"+buttonName+"')]";
		 return McsElement.isElementDisplayed(driver,xpath);
	 }
	 
	 /**
	  * Helper method check  whether move button is present or not
	  * @param className to be checked 
	  * @return true if present
	  */

	 public boolean isMoveButtonPresentOnInspectionTemplate(String className){
		 String xpath =INSPECTION_GRID_XPATH+"//button[contains(@class,'"+className+"')]";
		 return McsElement.isElementDisplayed(driver,xpath);
	 }

	 /**
	  * Helper method check  whether move button is present or not
	  * @param columnName to be checked 
	  * @return true if present
	  */

	 public boolean isGridHeaderPresentOnInspectionTemplate(String columnName){
		 String xpath =INSPECTION_GRID_XPATH+"//div[contains(@class,'x-grid3-hd-inner') and contains(text(),'"+columnName+"')]";
		 return McsElement.isElementDisplayed(driver,xpath);
	 }


	 /**
	  * Helper Method to select inspection point
	  * @param inspectionPoint
	  */

	 public void selectInspectionPoint(String inspectionPoint)
	 {
		 int colNum = getGridColumnNumberWithoutQuickFilters("Reference");

		 String xpath = INSPECTION_GRID_XPATH+"//div[contains(@class,'x-grid3-col-"+colNum+"') and text()='"+inspectionPoint+"']/../..//div[contains(@class,'x-grid3-row-checker')]"; 

		 McsElement.getElementByXpath(driver, xpath).click();
	 }

	 /**
	  * Helper Method to check whether Grid is empty or not inspection template form
	  * return true if it is empty
	  * return false if it is not empty
	  */
	 public boolean isGridEmptyinInspectionTemplateForm()
	 {
		 String xpath =INSPECTION_GRID_XPATH+"//div[contains(@class,'x-grid3-body')]//div[contains(@class,'x-grid3-row-checker')]";

		 List<WebElement> searchResults = driver.findElements(By.xpath(xpath));

		 return (searchResults.size()==0)?true:false;

	 }

	 /**
	  * Helper Method to get the inspection at the given position
	  * return inspection point 
	  */

	 public String getInspectionPointinGridrow(int rowNum)
	 {
		 int colNum = getGridColumnNumberWithoutQuickFilters("Reference");

		 String xpath = INSPECTION_GRID_XPATH+"//div[contains(@class,'x-grid3-col-rowIndex') and (text()='"+rowNum+"')]/../following-sibling::td[contains(@class,'x-grid3-col')]//div[contains(@class,'x-grid3-col-"+colNum+"')]";

		 return McsElement.getElementByXpath(driver, xpath).getText();
	 }
	 
	 /**
	  * Helper Method to validate Object Type field
	  * return true if it is displaying correctly
	  * return false if it is not displaying correctly 
	  */

	 public boolean  isObjectTypeDisplayingRoom(String fieldValue)
	 {   
		 int i=0;
		 int colNum = getGridColumnNumberWithoutQuickFilters("Object Type");

		 String xpath = INSPECTION_GRID_XPATH+"//div[contains(@class,'x-grid3-col-"+colNum+"')]";
		 
		 List<WebElement> searchResults = driver.findElements(By.xpath(xpath));
		 
		 if(searchResults.size()==0){

				Reporter.log("No search results found for filtering criteria ", true);
				throw new NoSuchElementException("No search results found for filtering criteria");

		}
		 
		 for(WebElement ele: searchResults){

				if(!ele.equals(fieldValue))
				{
					i=i++;
					break;
				}

		 }
		  return (i==0)?true:false;
	 }
	 
	 
	 /**
	  * Helper Method to validate Code  field
	  * return Code value
	  */
	
      public String getCodeOfRoomBasedOnReference(String reference)
      {
    	  int colNum = getGridColumnNumberWithoutQuickFilters("Reference");
    	  
    	  String xpath = INSPECTION_GRID_XPATH+"//div[contains(@class,'x-grid3-col-"+colNum+"')and text()='"+reference+"']/../..//div[contains(@class,'x-grid3-col-code')]"; 
    	  
    	  return McsElement.getElementByXpath(driver, xpath).getText();
    	  
      }
      
	
	 /**
	  * Helper Method to validate Row# filed
	  * return true if it is displaying correctly
	  * return false if it is not displaying correctly 
	  */

	 public boolean isRowNumberFieldNumeric()
	 {   
		 int i=0;
		 String xpath = INSPECTION_GRID_XPATH+"//div[contains(@class,'x-grid3-col-rowIndex')]";
		 
		 List<WebElement> searchResults = driver.findElements(By.xpath(xpath));
		 
		 if(searchResults.size()==0){
			 
				Reporter.log("No search results found for filtering criteria ", true);
				throw new NoSuchElementException("No search results found for filtering criteria");

		}
		 for(WebElement ele: searchResults){
			 
			 if(!ele.getText().matches("-?\\d+(\\.\\d+)?"))
			 {
				 i++;
				 break;
			 }
			 	
		 }
		  
	  return (i==0)?true:false;
	 }
	 
	 /**
	  * Helper Method to validate Row# field
	  * return true if it is displaying correctly
	  * return false if it is not displaying correctly 
	  */

	 public boolean isRowNumberFieldUnique()
	 {   
		 List<Integer> al = new ArrayList<Integer>();
		 String xpath = INSPECTION_GRID_XPATH+"//div[contains(@class,'x-grid3-col-rowIndex')]";
		 
		 List<WebElement> searchResults = driver.findElements(By.xpath(xpath));
		 
		 if(searchResults.size()==0){
			 
				Reporter.log("No search results found for filtering criteria ", true);
				throw new NoSuchElementException("No search results found for filtering criteria");

		}
		 
		 for(WebElement e : searchResults){
			 al.add(Integer.parseInt(e.getText().trim()));
		 }
		 
		
		return isIntegerListUnique(al);
	 }
	 
	 /**
	  * Helper Method to validate list elements are unique are not
	  * return true list is unique
	  * return false list is not unique
	  */
	 
	 public boolean isIntegerListUnique(List<Integer> al)
	 {   
		 int i=0;
		 HashSet<Integer> hashSet = new HashSet<>();
		 for(Integer j : al) { 
		   if(!hashSet.add(j))
		   {
			   i++;
			   break;
		   }
		 }
		 
		 return (i==0)?true:false;
		 
	 } 


}



