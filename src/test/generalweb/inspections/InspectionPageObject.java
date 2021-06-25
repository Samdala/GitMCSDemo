package test.generalweb.inspections;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.Spring;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class InspectionPageObject extends AbstractMcsTestSuite {
	
	
	protected String INSPECTION_WIN_XPATH = "//div[contains(@class,'x-window x-resizable-pinned') and contains(@style,'visibility: visible')]";
	
	protected String INSPECTION_WIN_HEADER_TEXT = "Inspection"; 
	
	protected String INSPECTIONPOINTS_TREE_XPATH = INSPECTION_WIN_XPATH+"//div[contains(@class,'mcs-tree-navigation')]";
	
	protected String INSPECTIONS_UNDO_BUTTON = INSPECTIONPOINTS_TREE_XPATH+"//button[contains(@style,'repeat')]";
	
	protected String INSPECTIONS_MOVETO_BUTTON = INSPECTIONPOINTS_TREE_XPATH+"//button[contains(@style,'control-right')]";
	
	protected final String WO_WINDOW_XPATH = "//span[contains(text(),'Work Order')]/ancestor::div[contains(@class,'resizable-pinned') and contains(@style,'visibility: visible')]";
	
	protected final String  WO_TEMPLATE_WINDOW_XPATH ="//span[text()='Work Order Templates']/ancestor::div[contains(@class,'resizable-pinned') and contains(@style,'visibility: visible')]";
	
	//**************************************General Fields*******************************************************************************
	
	public void createInspection(String inspectionTemplateLoaction,String inspectionTemplate,String inspectionReference){
		
	    
		 expandAdministration();

		 waitForExtJSAjaxComplete(10);

		 expandSubMainMenu("Inspection");

		 waitForExtJSAjaxComplete(20);
		  
		 clickXPath(XPATH_NEWINSPECTION);
		  
		 waitForExtJSAjaxComplete(20);
		 waitForExtJSAjaxComplete(20);
		  
		 clickLookupNewUI("Inspection Location:");
		  
		 waitForExtJSAjaxComplete(20);
		  
		 setValueGridLookupWithFiltersNewUI("@class","x6-tree-arrows",inspectionTemplateLoaction,"Reference");
		  
		 waitForExtJSAjaxComplete(20);
		  
		 clickInspectionTemplateLookupNewUI("Inspection Template:");
		  
		 waitForExtJSAjaxComplete(20);
		  
		 setValueGridLookup(inspectionTemplate);
		
		 waitForExtJSAjaxComplete(20);
		  
		 clickCreateButton();
		  
		 waitForExtJSAjaxComplete(10);
		 
		 try{
			 
			 this.waitForMaskDisappear();
			 
		 }catch(Exception e){
			 
			 
		 }
			  
		 setFieldValueBasedOnLabel("Reference",inspectionReference);
		  
		 waitForExtJSAjaxComplete(20);

		 resizeTheInspectionWindow();
		 
		 waitForExtJSAjaxComplete(10);

		 clickSaveButton();

		 waitForExtJSAjaxComplete(30);
		  
	 }
		
	
	//***********************************Setter methods******************************************************************************
	
	
	/**
	 * Helper method to set Inspection Reference
	 * @ Reference of Inspection
	 */
	public void setInspectionReference(String reference ){
		
		 setFieldValueBasedOnLabel("Reference",reference);
	}
	
	/**
	 * Helper method to set Status of Inspection
	 * @ Status of Inspection
	 */
	public void setInspectionStatus(String inspectionStatus){
		
		String xpath = INSPECTION_WIN_XPATH+"//label[contains(text(),'Status')]/..//input";
		
		String id= McsElement.getElementByXpath(driver, xpath).getAttribute("id");
		
		DropDown.setComboValueNative(driver, id, inspectionStatus);
	}
	
	/**
	 * Helper method to set Inspector of Inspection
	 * @ Inspector of Inspection
	 */
	public void setInspectorOfInspection(String column,String inspector){
			
			clickLookup("@class","x-panel", "inspectionInspector","Select a Person");

			setValueGridLookupWithFilters("@id", getXWindowId("Select a Person"), inspector, column);
		}
		
	
	
	/**
	 * Helper method to set Reviewer of Inspection
	 * @ Reviewer of Inspection
	 */
	public void setReviewerOfInspection(String column,String reviewer){
		
		clickLookup("@class","x-panel", "inspectionReviewer","Select a Person");

		setValueGridLookupWithFilters("@id", getXWindowId("Select a Person"), reviewer, column);
		
		//setLOVValueBasedOnLabel("Reviewer", "Select a Person", column, reviewer);
	}
	
	
	/**
	 * Helper method to set Planned Date of Inspection
	 * @ Planned Date of Inspection
	 */
	public void setPlannedDateOfInspection(String plannedDate){
		
		 setFieldValueBasedOnLabel("Date Planned", plannedDate);
		 
		 
	}

	
	
	
	//****************************************Getter methods********************************************************************************
	/**
	 * Helper method to get Inspection Reference
	 * @return Reference of Inspection
	 */
	public String getInspectionReference(){
		
		return getFieldValueBasedOnLabel("Reference");
	}
	
	/**
	 * Helper method to get Inspection Location
	 * @return Inspection Location of Inspection
	 */
	public String getInspectionLocation(){
		
		return getFieldValueBasedOnLabel("Inspection Location");
	}
	
	/**
	 * Helper method to get Status of Inspection
	 * @return Status of Inspection
	 */
	public String getInspectionStatus(){
		
		return getFieldValueBasedOnLabel("Status");
	}
	
	/**
	 * Helper method to get Inspector of Inspection
	 * @return Inspector of Inspection
	 */
	public String getInspectorOfInspection(){
		
		return getFieldValueBasedOnLabel("Inspector");
	}
	
	/**
	 * Helper method to get Reviewer of Inspection
	 * @return Reviewer of Inspection
	 */
	public String getReviewerOfInspection(){
		
		return getFieldValueBasedOnLabel("Reviewer");
	}
	
	/**
	 * Helper method to get Inspection Class 
	 * @return Inspection Class of Inspection
	 */
	public String getInspectionClass(){
		
		return getFieldValueBasedOnLabel("Inspection Class");
	}
	
	
	/**
	 * Helper method to get Inspection Process
	 * @return Inspection Process of Inspection
	 */
	public String getInspectionProcess(){
		
		return getFieldValueBasedOnLabel("Inspection Process");
	}
	
	/**
	 * Helper method to get Planned Date of Inspection
	 * @return Planned Date of Inspection
	 */
	public String getPlannedDateOfInspection(){
		
		return getFieldValueBasedOnLabel("Date Planned");
	}

	
	
	
	/**
	 * Helper method to get Finished Date of Inspection
	 * @return Finished Date of Inspection
	 */
	public String getFinishedDateOfInspection(){
		
		return getFieldValueBasedOnLabel("Date Finished");
	}
		
	/**
	 * Helper method to get Finished Date of Inspection
	 * @return Finished Date of Inspection
	 */
	public String getStatusFieldIdOfInspection(){
		
		return getFieldIdBasedOnLabel("Status");
	}
		
	
	/**
	 * Helper method to get inspection template
	 * @return inspection template
	 */
	public String getInspectionTemplate(){
		return getFieldValueBasedOnLabel("Inspection Template");	
	}

	/**
	 * Helper method to get inspection window header
	 * @param windowType  window
	 * @return header
	 */
	public String getInspectionWindowHeader(String windowType){
		try{
			String xpath_Header = INSPECTION_WIN_XPATH+"//div[contains(@class,'x-"+windowType+"-header')]//span[contains(@class,'x-"+windowType+"-header-text')]";
			return McsElement.getElementByXpath(driver, xpath_Header).getAttribute("textContent");
		}
		catch(Exception e){

			System.out.println("window not found");
			return "";
		}

	}

	/**
	 * Helper method to get Inspection Wizard fields
	 * @return fields name
	 */
	public List<String> getInspectionWizardFields(){
		String xpath = INSPECTION_WIN_XPATH + "//label[contains(@class,'x-form-item-label')]";
		List<String> InspFormFields = new ArrayList<String>();
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		for(WebElement element : elements){
			InspFormFields.add((element.getText()).replace(":", "").trim());
		}
		return InspFormFields;
	}

	/**
	 * Helper method to get Status class of site 
	 * @return status class value
	 */
	public String getStatusClassOfSite(){
		String xpath = "//div[contains(@class,'admsettings-modulesettings')]//div[contains(@class,'mcsspaceexplorer')]//div[text()='Status Class']/../following-sibling::td";
		return driver.findElement(By.xpath(xpath)).getText();
	}

	/**
	 * Helper method to get list of lookup values
	 * @param fieldLabel to be clicked
	 * @param windowTitle 
	 * @param colName to get values
	 * @return retrieved values
	 */
	private List<String> getLookUpValues(String fieldLabel , String windowTitle, String colName ){

		waitForExtJSAjaxComplete(25);

		clickLookup(fieldLabel);

		waitForExtJSAjaxComplete(25);

		return getValuesFromGridLookup("@id",getXWindowId(windowTitle), colName);

	}

	/**
	 * Helper method to get list of Inspection Templates
	 * @return Inspection Template list
	 */
	public List<String> getListOfInspectionTemplates(){
		return getLookUpValues("Inspection Template:","Inspection Template","Reference");
	}
	
	/**
	 * Helper method to get Inspection point text value
	 * @return list of inspection points
	 */
	public List<String> getInspectionPointsAvailableForInspection()
	{
		
		List<String> availableInspectionPoints = new ArrayList<String>();
		
		List<WebElement> inspectionPointsList =driver.findElements(By.xpath(INSPECTIONPOINTS_TREE_XPATH+"//span[contains(.,'Inspection Points')]/../..//a[contains(@class,'x-tree-node-anchor')]//span[contains(text(),'To do')]"));
			
		for(WebElement inspectionPointsEle:inspectionPointsList ){
			
			
			availableInspectionPoints.add(inspectionPointsEle.getText().trim());		
			
		}

		return availableInspectionPoints;

	}
	//**********************************Button Clicks**************************************************************************************
	
	
	/**
	 * Helper method to click on Save button
	 */
	public void clickSaveButton(){
		
		clickButtonInInspectionsWin("Save");
	}
	
	/**
	 * Helper method to click on Save & Close button
	 */
	public void clickSaveAndCloseButton(){
		
		clickButtonInInspectionsWin("Save & Close");
	}
	
	/**
	 * Helper method to click on Close button
	 */
	public void clickCloseButton(){
		
		clickButtonInInspectionsWin("Close");
	}
	
	/**
	 * Helper method to click on Create button
	 */
	
	public void clickCreateButton()
	{
		clickButtonInInspectionsWin("Create");
	
	}
	
	/**
	 * Helper method to click on Add button in edit inspection window
	 */
	
	public void clickAddButtonInInspPoints()
	{
		clickButtonInInspectionsWin("Add");
	}
	
	/**
	 * Helper method to click on edit button
	 */
	public void clickEditButton(){
		 clickXPath("//button[contains(@class, 'icon-ov-edit') and text()='Edit']");
	}
	
	/**
	 * Helper method to click on delete method 
	 */
	public void clickDeleteButton(){
		 clickXPath("//button[contains(@class, 'icon-ov-remove') and text()='Delete']");
	}
	
	/**
	 * Helper method to click buttons on warning window
	 * @param button name
	 */
	public void clickButtonOnWarningWin(String button){
		driver.findElement(By.xpath("//div[contains(@class, 'x-window-dlg')]//button[contains(@class,'x-btn-text') and (text()='"+button+"')]")).click();
		
	}
	
	/**
	 * Helper method to click on add button in Inspection overveiw
	 */
	public void clickAddButton(){
		driver.findElement(By.xpath("//div[@id='inspectiontabpanel']//button[text()='Add']")).click();
	}
	
	/**
	 * Helper method to click on tree node
	 * @param nodeName to be clicked
	 */
	public void clickOnTreeNode(String nodeName){
		driver.findElement(By.xpath("//div[contains(@class,'x-tree-node-leaf') and contains(.,'"+nodeName+"')]")).click();
	}
	//***************************************Inspection Points*******************************************
	
	public void selectInspectionPoint(String inspectionPoint){
		
		String xpath = INSPECTIONPOINTS_TREE_XPATH+"//span[contains(.,'"+inspectionPoint+"')]/..";
		
		McsElement.getElementByXpath(driver, xpath).click();
		
		
	}
	
	/**
	 * Helper method to select Maintenance object
	 * @param Mantenance object
	 */
	public void selectMaintenanceObject(String maintenanceObject){
		
		String xpath = INSPECTIONPOINTS_TREE_XPATH+"//span[.='"+maintenanceObject+"']/..";
		
		McsElement.getElementByXpath(driver, xpath).click();
			
	}

	/**
	 * Helper method to click on moveToInspected
	 */
    public void moveToInspected(){
		
		String xpath = INSPECTIONPOINTS_TREE_XPATH+"//button[contains(@style,'control-right')]";
		
		McsElement.getElementByXpath(driver, xpath).click();
		
		
	}
    
    /**
	 * Helper method to click on moveToReviewed
	 */
    public void moveToReviewed(){
		
    	moveToInspected();
    		
	}
    
    /**
     * Helper method to check inspected or not 
     */
    public boolean isInspected(String inspectionPoint)
    {

    	String xpath = INSPECTIONPOINTS_TREE_XPATH+"//span[contains(.,'"+inspectionPoint+" (Inspected)')]";

    	return McsElement.isElementDisplayed(driver,xpath);
    }

    /**
     * Helper method to check whether inspection point is inspected or not
     * @param inspectionPoint
     * @return
     */
    public boolean isInspectionPointReviewed(String inspectionPoint){
    	String xpath = INSPECTIONPOINTS_TREE_XPATH+"//span[contains(.,'"+inspectionPoint+" (Reviewed)')]";

    	return McsElement.isElementDisplayed(driver,xpath);
    }

    /**
     * Helper method to check DropDown 
     */
    public boolean isDropDownEnabled(String fieldLabel)
    {

    	String xpath  = INSPECTION_WIN_XPATH+"//label[contains(text(),'"+fieldLabel+"')]/..//div[contains(@class,'x-item-disabled')]";


    	if(McsElement.isElementDisplayed(driver,xpath))
    	{
    		return false; 
    	}
    	else 
    		return true;

    }

    /**
     * Helper method to check field status 
     * @param fieldLabel field name
     * @param attribute div/ input
     * @return true/false
     */
    public boolean isFielddisabled(String fieldLabel,String attribute){
    	String xpath = INSPECTION_WIN_XPATH+"//label[contains(text(),'"+fieldLabel+"')]/..//"+attribute+"[contains(@class,'x-item')]";
    	String className = driver.findElement(By.xpath(xpath)).getAttribute("class");
    	return (className.contains("disabled"))? true : false;
    }


    /**
     * Helper method to check inspection point status is TO DO 
     */
    public boolean isInspectionPointStatusTODO(String inspectionPoint)
    {

    	String xpath = INSPECTIONPOINTS_TREE_XPATH+"//span[contains(.,'"+inspectionPoint+" (To do)')]";

    	return McsElement.isElementDisplayed(driver,xpath);
    }

    /**
     * Helper method to verify work order grid is empty
     * @return true or false
     */

    public boolean isWorkOrderGridEmpty()
    {

    	String xpath=INSPECTION_WIN_XPATH+"//div[contains(@class,'x-grid3-body')]//div[contains(@class,'x-grid-empty') and text()='No items available']";

    	return McsElement.isElementDisplayed(driver, xpath);


    }


    /**
     * Helper method to check if the field is disabled or not
     * */

    public boolean isFieldDisabled(String fieldName)
    {
    	WebElement  element= McsElement.getElementByXpath(driver,INSPECTION_WIN_XPATH+"//label[contains(text(),'"+fieldName+"')]/..//input");

    	String classAttrVal =element.getAttribute("class");

    	return (classAttrVal.contains("noedit") ||classAttrVal.contains("readonly") || classAttrVal.contains("disabled") )?true:false;

    }

    /**
     * Helper method to check the field status
     * @param fieldName to be checked
     * @return is mandatory or not
     */
    public boolean isFieldMandatory(String fieldName){

    	String xpath = INSPECTION_WIN_XPATH + "//label[contains(text(),'"+fieldName+"')]";

    	String className = driver.findElement(By.xpath(xpath)).getAttribute("class");

    	return (className.contains("mandatory"))?true:false;
    }

    /**
     * Helper method verify whether the button is present or not
     * @param buttonName to verify
     * @return displayed or not displayed
     */
  public boolean isButtonPresent(String buttonName){
	  String xpath =INSPECTION_WIN_XPATH + "//button[contains(@class,'x-btn-text') and  text()='"+buttonName+"']";
	  return McsElement.isElementDisplayed(driver, xpath);
  }

  /**
   * Helper method to verify button present or not 
   * @param attributeValue
   * @return displayed or not displayed 
   */
  public boolean isButtonDisplayed(String attributeValue){
	  String xpath = INSPECTION_WIN_XPATH + "//button[contains(@class,'x-btn-text') and contains(@style,'"+attributeValue+"')]";
	  return McsElement.isElementDisplayed(driver, xpath);
  }
    //*********************************Helper methods***********************************************************

    /**
     * Helper function to return value of Readonly fields in Create New Inspection Window or Inspection Main window
     * @param fieldLabel: label of field whose value has to be retrieved
     * @return field value
	 */
	private void setFieldValueBasedOnLabel(String fieldLabel, String value ){
		
		String xpath = INSPECTION_WIN_XPATH+"//label[contains(text(),'"+fieldLabel+"')]/..//input";
		
		WebElement input = McsElement.getElementByXpath(driver, xpath);
		
		input.click();
		input.clear();
		input.sendKeys(value);
		
	}
	
	
	/**
	 * Helper function to return value of Readonly fields in Create New Inspection Window or Inspection Main window
	 * @param fieldLabel: label of field whose value has to be retrieved
	 * @return field value
	 */
	private String getFieldValueBasedOnLabel(String fieldLabel ){
		
		String xpath = INSPECTION_WIN_XPATH+"//label[contains(text(),'"+fieldLabel+"')]/..//input[contains(@class,'x-form-field')]";
		
		return McsElement.getElementByXpath(driver, xpath).getAttribute("value");
		
	}
	
	/**
	 * Helper function to return Id
	 * @param fieldLabel: label of field whose value has to be retrieved
	 * @return field id
	 */
	private String getFieldIdBasedOnLabel(String fieldLabel ){
		
		String xpath = INSPECTION_WIN_XPATH+"//label[contains(text(),'"+fieldLabel+"')]/..//input";
		
		return McsElement.getElementByXpath(driver, xpath).getAttribute("id");
		
	}

	/**
	 * Helper method to click on buttons in Inspection Windows
	 * @param buttonText to click
	 */
	private void clickButtonInInspectionsWin(String buttonText){
		
		String xpath = INSPECTION_WIN_XPATH+"//button[text()='"+buttonText+"']";
		
		McsElement.getElementByXpath(driver, xpath).click();
	}
	
	/**
	 * Helper method to set values in Lookup in Inspection windows
	 * @param fieldLabel: label of Field
	 * @param lookupWinTitle : Title of the Lookup window which opens after clicking lookup
	 * @param column to use
	 * @param value to enter
	 */
	private void setLOVValueBasedOnLabel(String fieldLabel, String lookupWinTitle,String column, String value){
		
		clickLookup(fieldLabel);
		
		waitForExtJSAjaxComplete(10);
		
		setValueGridLookupWithFiltersNewUI(value,column);
		
		
	}

	
	/**
	 * Method allows to click on lookup button on the form for some input
	 * @param  formClass - class of the window where lookup is present
	 * @param inputName - name of the input for which lookup is opened 
	 */
	public void clickLookup(String fieldLabel){
		Timer timer = new Timer().start();
		
		int xwindowNumber=0;
		
		try {driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			xwindowNumber = driver.findElements(By.xpath("//div[contains(@class,'x-resizable-pinned')]")).size();
		}
		finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);}

		String xpath = INSPECTION_WIN_XPATH+"//label[contains(text(),'"+fieldLabel+"')]/..//input";
		
		String name = driver.findElement(By.xpath(xpath)).getAttribute("name");
		
		waitFocusAndClick(INSPECTION_WIN_XPATH+"//input[@name='"
				+ name + "']//..//..//button");
		
		waitForExtJSAjaxComplete(5);

		Reporter.log("lookup was clicked"+ " (" + timer.stop()+ "ms)", true);
		
	}
	
	/**
	 * Helper method to set location value
	 * @param location
	 * @param column
	 */
	public void setLocationOfInspection(String location,String column){
		
		setLOVValueBasedOnLabel("Inspection Location:","Select a Location",column,location);
	}
	
	/**
	 * Helper method to set inspection template 
	 * @param location
	 * @param column
	 */
	public void setTemplateOfInspection(String template,String column){
		
		clickLookup("Inspection Template:");
		
		waitForExtJSAjaxComplete(10);
		
		setValueGridLookupWithFilters("@id", getXWindowId("Inspection Template"), template, column);
	}
	
	/**
	 *  Helper method to get inspection points
	 * @return list
	 */
	public List<String> getUninspectedInspectionPoints()
	{
		
		List<String> availableInspectionPoints = new ArrayList<String>();
		
		List<WebElement> inspectionPointsList =driver.findElements(By.xpath(INSPECTIONPOINTS_TREE_XPATH+"//span[contains(.,'Inspection Points')]/../..//a[contains(@class,'x-tree-node-anchor')]//span[contains(text(),'To do')]"));
			
		for(WebElement inspectionPointsEle:inspectionPointsList ){
			
			
			availableInspectionPoints.add(inspectionPointsEle.getText().replace("Room:", " ").replace("(To do)", " ").trim());		
			
		}

		return availableInspectionPoints;

	}
	
	/**
	 * Helper method to get inspection id
	 * @return ID
	 */
	public String getInspectionID()
	{
		String xpath=INSPECTION_WIN_XPATH+"//span[contains(text(),'Inspection -')]";
		String element= McsElement.getElementByXpath(driver, xpath).getText();
		return element.replace("Inspection - ", " ").trim();
		
	}
	
	/**
	 * Helper method to get work order templates
	 * @return list of templates
	 */
	
	public List<String> getWOTemplatesInInspection(String name)
	{
		expandAllTemplatedGroups();
		
		waitForExtJSAjaxComplete(5);
		
		List<String> availableInspectionTemplates = new ArrayList<String>();
		
		List<WebElement> templateList =driver.findElements(By.xpath("//span[contains(text(), 'Work Order Templates')]/../following-sibling::div//span[contains(@class,'tplNode')]/div"));
		
		for(WebElement templateListsEle:templateList ){
			
			
			availableInspectionTemplates.add(templateListsEle.getText().trim());
		}

		return availableInspectionTemplates;
	}
	
	/**
	 * Helper method to Expand/Collapse Template Group
	 */
	public void expandTemplateGroup(String templateGroup){
		String xpath = WO_WINDOW_XPATH+"//div[contains(@class, 'helpdesk-newcall')]//div[contains(@class, 'grpLabel') and text()='"+templateGroup+"']/ancestor::div[contains(@class, 'x-tree-node-el')]";
		String classValue = McsElement.getElementByXpath(driver, xpath).getAttribute("class");
		if(classValue.contains("collapsed")){
			driver.findElement(By.xpath(xpath)).click();
			waitForExtJSAjaxComplete(10);
		} else{
			Reporter.log("Template Group tree is already expanded", true);
		}
		Reporter.log("Template Group is Expanded", true);
	}

		/**
	 * Method allows to click on lookup button on the form for some input
	 * @param  formClass - class of the window where lookup is present
	 * @param inputName - name of the input for which lookup is opened 
	 */
	public void clickLookupNewUI(String fieldLabel){
		Timer timer = new Timer().start();
		
		int xwindowNumber=0;
		
		try {driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			xwindowNumber = driver.findElements(By.xpath("//div[contains(@class,'x6-window-closable')]")).size();
		}
		finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);}

		String xpath = INSPECTION_WIN_XPATH+"//label[contains(text(),'"+fieldLabel+"')]/..//input";
		
		String name = McsElement.getElementByXpath(driver, xpath).getAttribute("name");
		
		waitFocusAndClick(INSPECTION_WIN_XPATH+"//input[@name='"
				+ name + "']//..//..//button");
		
		waitForExtJSAjaxComplete(5);
		waitForExtJSAjaxComplete(5);

		try {driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			if (driver.findElements(By.xpath("//div[contains(@class,'x6-window-closable') ]")).size() == xwindowNumber)
				{waitAndClick("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");}
		}

		 finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
		}

		Reporter.log("lookup was clicked"+ " (" + timer.stop()+ "ms)", true);
		
	}
	
	
	/**
	 * Method allows to click on lookup button on the form for some input
	 * @param  formClass - class of the window where lookup is present
	 * @param inputName - name of the input for which lookup is opened 
	 */
	public void clickInspectionTemplateLookupNewUI(String fieldLabel){
		Timer timer = new Timer().start();
		int xwindowNumber=0;
		
		try {driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			xwindowNumber = driver.findElements(By.xpath("//div[contains(@class,'x-window-body-noborder')]")).size();
		}
		finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);}

		String xpath = INSPECTION_WIN_XPATH+"//label[contains(text(),'"+fieldLabel+"')]/..//input";
		
		String name = McsElement.getElementByXpath(driver, xpath).getAttribute("name");
		
		waitFocusAndClick(INSPECTION_WIN_XPATH+"//input[@name='"
				+ name + "']//..//..//button");
		
		waitForExtJSAjaxComplete(5);
		waitForExtJSAjaxComplete(5);

		try {driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			if (driver.findElements(By.xpath("//div[contains(@class,'x-window-body-noborder')]")).size() == xwindowNumber)
				{waitAndClick("(//img[contains(@src,'library/lookup2/images/search.png')]) [last()]");}
		}

		 finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
		}

		Reporter.log("lookup was clicked"+ " (" + timer.stop()+ "ms)", true);
		
	}
	
	/**
	 * Helper method to check whether button is enabled or not 
	 * @param buttonName to click
	 * @return 
	 */
	public boolean isButtonEnabledInInspection(String attribute,String attributeValue){
		String xpath = INSPECTION_WIN_XPATH+"//button[contains("+attribute+",'"+attributeValue+"')]//ancestor::table[contains(@class,'x-btn')]";
		String className = driver.findElement(By.xpath(xpath)).getAttribute("class");
		return (className.contains("disabled"))? false : true;
	}

	/**
	 * Helper method to close window
	 */
	public void closeXWindow(){
		McsElement.getElementByXpath(driver, "(//div[contains(@class,'x6-resizable')]//div[contains(@class,'x6-tool-close')])[last()]").click();
		Reporter.log("close x-window <br>", true);
	}
	
	
	/**
	 * Helper function to clear value of fields in Create New Inspection Window or Inspection Main window
	 * @param fieldLabel: label of field whose value has to be retrieved
	 */
	public void clearFieldValueBasedOnLabel(String fieldLabel){

		String xpath = INSPECTION_WIN_XPATH+"//label[contains(text(),'"+fieldLabel+"')]/..//input";

		WebElement input = McsElement.getElementByXpath(driver, xpath);

		input.click();
		input.clear();

	}



	/**
	 * Helper method to get random string of desired length
	 */
	private static final String CHAR_LIST =
			"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	public String generateRandomString(int RANDOM_STRING_LENGTH){

		StringBuffer randStr = new StringBuffer();
		for(int i=0; i<RANDOM_STRING_LENGTH; i++){
			int number = getRandomNumberInRange(0,61);
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	/**
	 * Helper method to get random number
	 */
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	/**
	 * Helper method to mouse over on buttons
	 */
	public String getToolTipOfButtonByMouseHover(String buttonAttribute,String buttonAttributeValue){
		String xpath = INSPECTION_WIN_XPATH+"//button[contains("+buttonAttribute+",'"+buttonAttributeValue+"')]";

		WebElement we = driver.findElement(By.xpath(xpath));

		Actions actions = new Actions(driver);
		actions.clickAndHold(we).perform();
		we = driver.findElement(By.xpath(xpath));
		String ToolTipText = we.getAttribute("qtip");
		return ToolTipText;
	}

	/**
	 * Helper method to mouse over on InspectionPoint
	 */
	public String getToolTipOfInspectionPointByMouseHover(String inspectionPoint){
		String xpath = INSPECTIONPOINTS_TREE_XPATH+"//span[contains(.,'"+inspectionPoint+"')]";

		WebElement we = driver.findElement(By.xpath(xpath));

		Actions actions = new Actions(driver);
		actions.click().moveToElement(we, 5, 5).perform();

		we = driver.findElement(By.xpath(xpath));
		String toolTipText = we.getAttribute("qtip");

		return getTextWithoutDivTags(toolTipText).trim();

	}

	/**
	 * Helper method to check inspection point status is TO DO 
	 */
	public String getTextWithoutDivTags(String htmltext)
	{
		int divIndex = htmltext.indexOf("<div");
		divIndex = htmltext.indexOf(">", divIndex);

		int endDivIndex = htmltext.indexOf("</div>", divIndex);
		String content = htmltext.substring(divIndex + 1, endDivIndex);

		return content.replaceAll("<br>","");

	}



	/**
	 * Helper method to check inspection point status is TO DO 
	 */
	public boolean isInspectionPointStatusINSPECTED(String inspectionPoint)
	{

		String xpath = INSPECTIONPOINTS_TREE_XPATH+"//span[contains(.,'"+inspectionPoint+" (Inspected)')]";

		return McsElement.isElementDisplayed(driver,xpath);
	}

	/**
	 * Helper method to check WO Grid opens or not
	 * return true if it is open
	 * return false if it is not open
	 */
	public boolean isWOGridopen()
	{

		String xpath = INSPECTION_WIN_XPATH+"//div[contains(@class,'x-grid3-hd-inner x-grid3-hd') and contains(text(),'Work Order ID')]";

		return McsElement.isElementDisplayed(driver,xpath);
	}


	/**
	 * Helper method to get maintenance Objects of inspection points 
	 * @param inspecPoint is name of the inspection point
	 * @return list of MO's
	 */

	public List<String> getMOofInspectionPoint(String inspecPoint)
	{

		waitForExtJSAjaxComplete(5);

		List<String> availableMaintenanceObjects = new ArrayList<String>();

		String xpath="//span[contains(.,'"+inspecPoint+"')]/../following::ul[contains(@style,'visibility: visible')]//a[contains(@class,'x-tree-node-anchor')]//span";

		List<WebElement> maintenanceObjectsList =driver.findElements(By.xpath(xpath));

		for(WebElement moListsEle:maintenanceObjectsList ){


			availableMaintenanceObjects.add(moListsEle.getText().trim());

		}

		return availableMaintenanceObjects;



	}

	/**
	 * Method to check for the Finished And Archived Status of wo's
	 * return true if there is no such wo's
	 * return false if its have such wo's
	 */
	public boolean isWOGridContainsWOOfRoom(String inspPoint) {   
		int i=0;
		waitForExtJSAjaxComplete(25);

		String xpath =INSPECTION_WIN_XPATH+"//div[@realid='mogrid']//div[contains(@class,'x-grid3-body')]//div[contains(@class,'x-grid3-row')]";

		List<WebElement> searchResults = driver.findElements(By.xpath(xpath));

		if(searchResults.size()==0){

			Reporter.log("No search results found for filtering criteria ", true);
			throw new NoSuchElementException("No search results found for filtering criteria");

		}

		for(WebElement ele: searchResults){

			Actions act = new Actions(driver);
			act.doubleClick(ele).build().perform();

			String general = INSPECTION_WIN_XPATH+"//span[contains(@class,'x-tab-strip-inner')]//span[.='General']";
			WebElement we = driver.findElement(By.xpath(general));
			we.click();
			waitForExtJSAjaxComplete(25);
			if(!getWOFieldValueBasedOnLabel("Location").equals(inspPoint))
			{
				i=i++;
				break;
			}

			closeUsingToolBar(WO_WINDOW_XPATH);
			
			waitForExtJSAjaxComplete(25);

		}

		return (i==0)?true:false;
	}

	/**
	 * Helper function to return value of Readonly fields in Work orders window
	 * @param fieldLabel: label of field whose value has to be retrieved
	 * @return field value
	 */
	private String getWOFieldValueBasedOnLabel(String fieldLabel ){

		String xpath = INSPECTION_WIN_XPATH+"//form[contains(@class,'x-column-layout-ct')]//label[contains(text(),'"+fieldLabel+"')]/..//input[contains(@class,'x-form-field')]";

		return McsElement.getElementByXpath(driver, xpath).getAttribute("value");

	}

	/**
	 * Helper method to click on Toggle Button on WO Picture tab
	 */
	public void clickToggleButtononWOPicturetab(){

		String xpath = INSPECTION_WIN_XPATH+"//div[contains(@class,'images-view')]//div[contains(@class,' x-tool-toggle x-too')]";

		McsElement.getElementByXpath(driver, xpath).click();


	}

	/**
	 * Helper method to click on UndoButton in inspection form
	 */
	public void clickUndoButton(){

		String xpath = INSPECTIONPOINTS_TREE_XPATH+"//button[contains(@style,'arrow-repeat')]";

		McsElement.getElementByXpath(driver, xpath).click();


	}
	
	/**
	 * Method to check for the Finished And Archived Status of wo's
	 * return true if there is no such wo's
	 * return false if its have such wo's
	 */
	public boolean isWOGridContainsWOOfFinishedAndArchivedStatus() {   
		int i=0;
		waitForExtJSAjaxComplete(25);

		int colNum = getGridColumnNumberWithoutQuickFilters("Date Finished");
		waitForExtJSAjaxComplete(25);

		String xpath =INSPECTION_WIN_XPATH+"//div[@realid='mogrid']//div[@class='x-grid3-cell-inner x-grid3-col-"+colNum + "']";

		List<WebElement> searchResults = driver.findElements(By.xpath(xpath));

		if(searchResults.size()==0){

			Reporter.log("No search results found for filtering criteria ", true);
			throw new NoSuchElementException("No search results found for filtering criteria");

		}

		for(WebElement ele: searchResults){

			if(!ele.getText().isEmpty())
			{
				i=i++;
				break;
			}

		}

		

		return (i==0)?true:false;



	}
	
	/**
	 * Helper method to get column number in Grid
	 * @param columnName of whose column number has to be obtained
	 * @return column number
	 */
	public int getGridColumnNumberWithoutQuickFilters(String columnName){

		WebElement ele = driver.findElement(By.xpath(INSPECTION_WIN_XPATH+"//div[contains(@class,'x-grid3-hd') and contains(text(), '"+columnName+"')][last()]"));

		String columnClass = ele.getAttribute("class"); 
		String columnNumber = columnClass.substring(columnClass.lastIndexOf("-") + 1);

		return Integer.parseInt(columnNumber);

	}

	/**
	 *  Helper method to switch to Iframe
	 */
	public void switchToIframe(){
		WebElement iFrame= driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iFrame);
		Reporter.log("Switched to Iframe", true);
		waitForExtJSAjaxComplete(5);
	}

	/**
	 * Helper method to switch back to Parent Frame
	 */
	public void switchBackToDefaultFrame(){
		driver.switchTo().defaultContent();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Switched to Default Content", true);
	}
	/**
	 * Checking whether Inspection Module is available in start page of Web portal
	 */
	public boolean isInspectionModuleAvalaible()
	{		
		switchToIframe();
		String xpath = "//img[contains(@class,'inspections')]/following::a[text()='Inspection']";
		boolean status = McsElement.isElementPresent(driver, xpath);
		if(status){
			status = McsElement.isElementDisplayed(driver, xpath);
		}

		switchBackToDefaultFrame();
		return status;
	}	

	/**
	 * Checking whether Inspection Module image is available in start page of Web portal
	 */
	public boolean isInspectionModuleImageAvalaible()
	{
		switchToIframe();
		String xpath = "//img[contains(@class,'inspections')]";
		boolean status = McsElement.isElementPresent(driver, xpath);
		if(status){
			status = McsElement.isElementDisplayed(driver, xpath);
		}

		switchBackToDefaultFrame();
		return status;
	}

	/**
	 * Checking whether Inspection window is opened or not
	 */
	public boolean isInspectionsWindowOpen()
	{
		String xpath = "//span[contains(@class,'icon-inspections') and text()='Inspection']";
		return McsElement.isElementDisplayed(driver, xpath);
	}

	/**
	 * Checking whether New Inspection window is opened or not
	 */
	public boolean isNewInspectionsWindowOpen()
	{
		String xpath = "//span[contains(@class,'x-panel-header-text') and text()='Create Inspection']";
		return McsElement.isElementDisplayed(driver, xpath);
	}

	/**
	 * Helper method to resize inspection window
	 */
	public void resizeTheInspectionWindow(){
		String xpath = INSPECTION_WIN_XPATH+"//div[@class='x-window-bwrap']";
		int widthOfXWindow = driver.findElement(By.xpath(xpath)).getSize().getWidth();
		waitForExtJSAjaxComplete(10);

		Actions action = new Actions(driver);
		String startXWindow = INSPECTION_WIN_XPATH+"//div[contains(@class,'x-resizable-handle-west')]";
		WebElement startXWindowElement = driver.findElement(By.xpath(startXWindow));
		action.moveToElement(startXWindowElement).clickAndHold().moveByOffset(widthOfXWindow/8,0).release().perform();
		waitForExtJSAjaxComplete(10);
		String endXWindow = INSPECTION_WIN_XPATH+"//div[contains(@class,'x-resizable-handle-east')]";
		WebElement endXWindowElement = driver.findElement(By.xpath(endXWindow));
		action.moveToElement(endXWindowElement).clickAndHold().moveByOffset(-2*(widthOfXWindow/6),0).release().perform();

		Reporter.log("Inspection window is resized",true);
	}

	/**
	 * Helper method to set dropdown value in grid
	 * @param attribute - parent attribute (@class, @id)
	 * @param attributeValue - value of parent attribute
	 * @param columnName - to be filtered
	 * @param dropDownValue - value to be selected
	 */
	public void setShowOnPortalValue(String attribute, String attributeValue, String columnName,String dropDownValue){
		String columnClass = driver.findElement(By.xpath("//div[contains("+attribute+",'"+ attributeValue+"')]//div[contains(@class,'quickfilters')]"
				+ "//div[contains(@class,'x-grid3-hd') and contains(text(),'"+columnName+"')][last()]")).getAttribute("class");

		String columnNumber = (columnClass.substring(columnClass.length() - 2)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 2) : columnClass.substring(columnClass.length() - 1);

		String id= driver.findElement(By.xpath("//div[contains("+attribute+",'"+attributeValue+"')]//"
				+ "input[contains(@id,'filter-editor-"+columnNumber+"')][last()]")).getAttribute("id");

		DropDown.setComboValue(driver,id,dropDownValue);
	}

	/**
	 * Helper method to filter in grid by text value
	 * @param attribute - attribute (@class, @id) of the grid container
	 * @param attributeValue - corresponding value of the grid container attribute
	 * @param rowTextName - row text to be filtered by 
	 * @param columnName - columnName of the row 
	 */
	public void filterGridWithoutUsingMcsElement(String attribute, String attributeValue, String rowTextName, String columnName) {
		Timer timer = new Timer().start();
		
		
		String columnClass = driver.findElement(By.xpath("//div[contains("+attribute+",'"+ attributeValue+"')]//div[contains(@class,'quickfilters')]"
						+ "//div[contains(@class,'x-grid3-hd') and contains(text(),'"+columnName+"')][last()]")).getAttribute("class");
		
		String columnNumber = (columnClass.substring(columnClass.length() - 3)).matches("[0-9]+") ? columnClass.substring(columnClass.length() - 3) : columnClass.substring(columnClass.length() - 1);
		
		WebElement filterInput = driver.findElement(By.xpath("//div[contains("+attribute+",'"+attributeValue+"')]//"
				+ "input[contains(@id,'filter-editor-"+columnNumber+"')][last()]")); 
					
		filterInput.clear();
			
		filterInput.sendKeys(rowTextName);
		
		waitForExtJSAjaxComplete(25);
			
		driver.findElement(By.xpath("(//div[contains("+attribute+",'"+attributeValue+"')]//div[contains(@class,'x-grid3-body')])/div[last()]")).click(); 

		waitForExtJSAjaxComplete(5);

		Reporter.log(rowTextName + " was filtered"+ " (" + timer.stop()
				+ "ms)", true);
	}
	
	/**
	 * Helper method to click on Template Name
	 */
	public void selectWOTemplateInInspection(String tempName){
		String xpath = WO_TEMPLATE_WINDOW_XPATH+"//div[contains(@class, 'helpdesk-newcall')]//div[contains(@class, 'tplLabel') and text()='"+tempName+"']";
		McsElement.getElementByXpath(driver, xpath).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Template Name "+tempName+" is selected ");
	}
	
	/**
	 * Helper method to select Maintenance object
	 * @param Mantenance object
	 */
	public void selectMaintenanceObject(String inspectionPoint,String maintenanceObject){
		
		String xpath = INSPECTIONPOINTS_TREE_XPATH+"//span[contains(.,'"+inspectionPoint+"')]/../../..//span[.='"+maintenanceObject+"']/..";
		
		McsElement.getElementByXpath(driver, xpath).click();
			
	}

	/**
	 * Helper method to verify WO template section is present or not
	 * @param name
	 * @return true or false
	 */

	public boolean isWOTemplateSectionIsAvailable(String name)
	{
		String xpath=WO_TEMPLATE_WINDOW_XPATH+"//div[contains(@class,'x-column-inner')]//preceding-sibling::div[contains(text(),'"+name+"')]";
		
		  return McsElement.isElementDisplayed(driver,xpath);
	         
	}
	
	/**
	 * Helper method to expand WO template section
	 * @param name
	 */
	public void expandWOTemplateSection(String name){
		String xpath=WO_TEMPLATE_WINDOW_XPATH+"//div[contains(@class,'x-column-inner')]//preceding-sibling::div[contains(text(),'"+name+"')]//following-sibling::div";
		String classValue = McsElement.getElementByXpath(driver, xpath).getAttribute("class");
		if(!classValue.contains("minus")){
			driver.findElement(By.xpath(xpath)).click();
			waitForExtJSAjaxComplete(10);
		} else{
			Reporter.log("Sction tree is already expanded", true);
		}
		Reporter.log("Section is Expanded", true);
	}

	/**
	 * Helper method to expand WO template section
	 * @param name
	 */
	public void expandAllTemplatedGroups(){
		String xpath=WO_TEMPLATE_WINDOW_XPATH+"//div[contains(@class,'x-box-inner')]//preceding-sibling::div[contains(@class,'x-tool')]";
		String classValue = McsElement.getElementByXpath(driver, xpath).getAttribute("class");
		if(!classValue.contains("minus")){
			WebElement ele = driver.findElement(By.xpath(xpath));
			waitForExtJSAjaxComplete(10);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		} else{
			Reporter.log("all groups tree is already expanded", true);
		}
		Reporter.log("Templates groups are Expanded", true);
	}

	/**
	 * Helper method to cpllapse WO template section
	 * @param name
	 */
	public void collapseAllTemplatedGroups(){
		String xpath=WO_TEMPLATE_WINDOW_XPATH+"//div[contains(@class,'x-box-inner')]//preceding-sibling::div[contains(@class,'x-tool')]";
		String classValue = McsElement.getElementByXpath(driver, xpath).getAttribute("class");
		if(classValue.contains("minus")){
			driver.findElement(By.xpath(xpath)).click();
			waitForExtJSAjaxComplete(10);
		} else{
			Reporter.log("all groups tree is already collapsed", true);
		}
		Reporter.log("Templates groups are collapsed", true);
	}

	/**
	 * Helper method to maximize Inspection Window
	 */
	public void maximizeInspectionWindow(){
		String xpath = INSPECTION_WIN_XPATH +"//div[contains(@class,'x-window-draggable')]//div[contains(@class,'x-tool-maximize')]";
		driver.findElement(By.xpath(xpath)).click();
	}

	/**
	 * Helper method to minimize inspection window
	 */
	public void restoreInspectionWindow(){
		String xpath = INSPECTION_WIN_XPATH +"//div[contains(@class,'x-window-draggable')]//div[contains(@class,'x-tool-restore')]";
		driver.findElement(By.xpath(xpath)).click();
	}
}
