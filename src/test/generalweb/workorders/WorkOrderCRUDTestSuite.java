package test.generalweb.workorders;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import test.generalweb.inspections.InspectionAdministrationPageObject;
import test.generalweb.inspections.InspectionPageObject;
import test.generalweb.service.ServiceOrganizationsPageObject;


/**
 * Method with 2 parameter Window detail and input field name deals with WO Edit Screen 
 * Method with 1 paramter input field name deals with WO Add Screen
 * Ex: 1 setPriority(WORKORDER_WINDOW_DETAIL, priority); Edit Screen
 * Ex 2: setPriority(priority); Add WO Screen
 */

public class WorkOrderCRUDTestSuite extends
		WorkOrderPageObject {

	


	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testWorkOrderCreateFromType() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create Work Order from type</span><br>", true);

		Reporter.log("User creates work order from type: C75044<br>", true);
		
		
		//String workOrderType = "1preWrkTpNm";
		String workOrderTemplateNormal = "1preNrWrkTmpRef";
		String severity = "1preSeverityRef";
		String priority = "1prePriorityRef";
		
		String reference = "ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		String description = "description"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		String nature = "Default Nature";
		
		String definitionCategoryName = "preregcat";
		
		String definitionName = "prereguistered";

		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testWorkOrderCreateFromType");
		
		waitForExtJSAjaxComplete(25);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		clickXPath(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(25);

		clickOverviewBtn();
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		mouseMove("//span[contains(@class,'x-menu-item-text') and contains(text(),'"+definitionCategoryName+"')]");
		
		mouseMove("//span[contains(@class,'x-menu-item-text') and contains(text(),'"+definitionName+"')]");
	
		WebElement show = driver.findElement(By.xpath("//span[contains(@class,'x-menu-item-text') and text()='Show']"));
		
		waitWebElement(show);
	
		javaScriptClick(show);

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		

		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		/*setWorkOrderType(workOrderType);

		clickNextOnDetailsPanel();

		waitForExtJSAjaxComplete(20);
		
		clickPreviousOnDetailsPanel();
		
		waitForExtJSAjaxComplete(20);
		
		clickNextOnDetailsPanel();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();

		setReference(reference);

		setPriority("Default Priority");
		
		setSeverity("Default Severity");
		
		setNature(nature);*/
		
		expandTemplateGroup();
		
		waitForExtJSAjaxComplete(20);
		
		selectTemplate(workOrderTemplateNormal);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(reference);
		
		waitForExtJSAjaxComplete(25);
		
		setPriority(priority);
		
		waitForExtJSAjaxComplete(25);
	
		setSeverity(severity);
		
		waitForExtJSAjaxComplete(25);
		
		setNature(nature);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'"+WORKORDER_WINDOW_DETAIL+"')]//input[@name='NEW_WO_ESTIMATED_WORK']").clear();
		McsElement.getElementByXpath(driver, "//div[contains(@class,'"+WORKORDER_WINDOW_DETAIL+"')]//input[@name='NEW_WO_ESTIMATED_WORK']").sendKeys("5");
		
		waitForExtJSAjaxComplete(5);
		
		//Save Workorder button in Add WO Screen.(Interactive WO Template)
		clickSaveOrder();
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickDescriptionTab();
		
		setDescription(description);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//Edit the Created WO and click on Save button
		clickSaveWorkorder("x-panel");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(McsElement.getElementByPartAttributeValueAndParentElement(driver,	"div", "@class", "hdwo-details", "div",	"text()", "Work Order", "text()", reference,true, true).getText().matches(".*\\d+.*"),
				"Work order is created!");
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		clickOverviewBtn();
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		mouseMove("//span[contains(@class,'x-menu-item-text') and contains(text(),'"+definitionCategoryName+"')]");
		
		mouseMove("//span[contains(@class,'x-menu-item-text') and contains(text(),'"+definitionName+"')]");
	
		WebElement show2 = driver.findElement(By.xpath("//span[contains(@class,'x-menu-item-text') and text()='Show']"));
		
		waitWebElement(show2);
	
		javaScriptClick(show2);

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(
				Grid.isRowInGridPresent(driver, reference, "@realid", "mogrid"),
				"Work order is not shown!");
		
		Reporter.log("Work order was created <br>", true);

	}

	/**
	 * Test Case ID: C92119
	 * Author : SSA
	 */

	@Test(enabled=true)
	public void testWorkOrderCreateFromTemplate() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C120643:Verify Add Work order functionality from myMCS Work order module"
				+ "Test: Create Work Order from template  : C75045</span><br>", true);

		Reporter.log("User creates work order from template  : C75045  <br>", true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testWorkOrderCreateFromTemplate");
		
		String workOrderTemplateNormal = "1preNrWrkTmpRef";
		
		String workOrderTemplateMaintanceObject = "1preMoWrkTmpRef";
		
		String workOrderTemplateMaintanceObjectCategory = "1preMoCtWrkTmpRef";
		
		String nature = "Default Nature";//"1preNatureRef";
		
		String referenceNr = "1preWrkRefNr";
		
		String referenceMoCt = "1preWrkCtRef";
		
		String referenceMo = "1preWrkMoRef";
		
		String type = "1preWrkTpNm";
		
		String executionDate = "22-02-2025";
		
//		String description = "description"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		String location = "slnmEnrgBuilding2";
		
		String maintanceObject = "1preMnObRef";
		
		String severity = "1preSeverityRef";
		String priority = "1prePriorityRef";
		String errorMessage="There are invalid fields! Please correct them and submit the form again";
		

		expandAdministration();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		clickXPath(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);
		
        waitForExtJSAjaxComplete(20);
        
        waitForExtJSAjaxComplete(25);
        
        setDetailsTabCollapsedMode();
        
        waitForExtJSAjaxComplete(25);

		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		/*clickRadioWrkTmpl();
		
		waitForExtJSAjaxComplete(20);

		setWorkOrderTemplate(workOrderTemplateNormal);

		clickNextOnDetailsPanel();

		waitForExtJSAjaxComplete(20);
		
		clickPreviousOnDetailsPanel();
		
		waitForExtJSAjaxComplete(20);
		
		clickNextOnDetailsPanel();
		
		waitForExtJSAjaxComplete(20);
		
		setExecutionDate(executionDate);*/
		
		expandTemplateGroup();
		
		waitForExtJSAjaxComplete(20);
		
		selectTemplate(workOrderTemplateNormal);
		
		waitForExtJSAjaxComplete(20);
		
		this.clickSaveOrder();
		
		//C120643
		softAssert.assertTrue(getToolTipTextOfSaveButtonInAddWOWindow().contains(errorMessage),"System prompts the user with a message There are invalid fields! Please correct them and submit the form again");
		
		setReference(referenceNr);
		
		setPriority(priority);
		
		waitForExtJSAjaxComplete(25);
	
		setSeverity(severity);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setNature(nature);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getReferenceInAddWOWindow(),referenceNr,"Reference value is displayed");
		softAssert.assertEquals(getFieldValueInAddWOWindow("NEW_WO_PRIORITY"),priority,"Priority value is displayed");
		softAssert.assertEquals(getFieldValueInAddWOWindow("NEW_WO_SEVERITY"),severity,"Severity value is displayed");
		softAssert.assertEquals(getFieldValueInAddWOWindow("NEW_WO_NATURE"),nature,"Nature value is displayed");		
		
		clickResetFormButtonInAddWOWindow();
		
		this.waitForMaskDisappear();
		
		softAssert.assertEquals(getReferenceInAddWOWindow(),"","Reference value is empty");
		softAssert.assertEquals(getFieldValueInAddWOWindow("NEW_WO_PRIORITY"),"Select a Work Order Priority","Priority eference value is empty");
		softAssert.assertEquals(getFieldValueInAddWOWindow("NEW_WO_SEVERITY"),"Select a Work Order Severity","Severity value is displayed");
		softAssert.assertEquals(getFieldValueInAddWOWindow("NEW_WO_NATURE"),"Select a Nature","Nature value is displayed");		
		
		setReference(referenceNr);
		
		waitForExtJSAjaxComplete(25);
		
		setPriority(priority);
		
		waitForExtJSAjaxComplete(25);
	
		setSeverity(severity);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setNature(nature);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setLocation(location);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickSaveOrder();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		String workOrderID=getWOIDFromDetailsWindow();
		
		softAssert.assertTrue(workOrderID.matches("[0-9]+"),"Workorder ID is generated");
		
		waitForExtJSAjaxComplete(25);
		
		clickGeneralTab();
		
		softAssert.assertEquals(getNature(), nature,"nature is ok");
		
		softAssert.assertEquals(getLocation(), location,"location is ok");
		
		softAssert.assertEquals(getReference(), referenceNr,"reference is ok");
		
		softAssert.assertEquals(getType(), type,"type is ok");

		softAssert.assertTrue
		(McsElement.isElementPresent(driver,"//div[contains(@class,'xtb-text') and contains(text(),'Work Order') and contains(text(),'"+referenceNr+"')]"),"header is ok");
		
		waitForExtJSAjaxComplete(25);
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(25);
		
		navigateToMainPage();
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		click(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setDetailsTabCollapsedMode();
	        
	    waitForExtJSAjaxComplete(25);

		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		/*clickRadioWrkTmpl(); 

		setWorkOrderTemplate(workOrderTemplateMaintanceObject);

		clickNextOnDetailsPanel();

		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getMaintanceObject(), maintanceObject,"maintance object is ok");
		
		clickPreviousOnDetailsPanel();
		
		waitForExtJSAjaxComplete(20);
		
		clickNextOnDetailsPanel();
		
		waitForExtJSAjaxComplete(20);
		
		setExecutionDate(executionDate);*/
		
		expandTemplateGroup();
		
		waitForExtJSAjaxComplete(20);
		
		selectTemplate(workOrderTemplateMaintanceObject);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(referenceMo);
		
		waitForExtJSAjaxComplete(25);
		
		setPriority(priority);
		
		waitForExtJSAjaxComplete(25);
	
		setSeverity(severity);
		
		waitForExtJSAjaxComplete(25);
		
		setNature(nature);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setLocation(location);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickSaveOrder();
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		clickGeneralTab();
		
		softAssert.assertEquals(getNature(), nature,"nature is ok");
		
		softAssert.assertEquals(getLocation(), location,"location is ok");
		
		softAssert.assertEquals(getReference(), referenceMo,"reference is ok");
		
		softAssert.assertEquals(getType(), type,"type is ok");

		softAssert.assertTrue
		(McsElement.isElementPresent(driver,"//div[contains(@class,'xtb-text') and contains(text(),'Work Order') and contains(text(),'"+referenceMo+"')]"),"header is ok");
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(25);
		
		navigateToMainPage();
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		click(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setDetailsTabCollapsedMode();
	        
	    waitForExtJSAjaxComplete(25);

		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		/*clickRadioWrkTmpl(); 

		setWorkOrderTemplate(workOrderTemplateMaintanceObjectCategory);

		clickNextOnDetailsPanel();

		waitForExtJSAjaxComplete(20);
		
		clickPreviousOnDetailsPanel();
		
		waitForExtJSAjaxComplete(20);
		
		clickNextOnDetailsPanel();
		
		waitForExtJSAjaxComplete(20);
		
		setExecutionDate(executionDate);*/
		
		expandTemplateGroup();
		
		waitForExtJSAjaxComplete(20);
		
		selectTemplate(workOrderTemplateMaintanceObjectCategory);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(referenceMoCt);
		
		waitForExtJSAjaxComplete(25);
		
		setPriority(priority);
		
		waitForExtJSAjaxComplete(25);
	
		setSeverity(severity);
		
		waitForExtJSAjaxComplete(25);
		
		setNature(nature);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setLocation(location);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickSaveOrder();
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		clickGeneralTab();
		
		softAssert.assertEquals(getNature(), nature,"nature is ok");
		
		softAssert.assertEquals(getLocation(), location,"location is ok");
		
		softAssert.assertEquals(getReference(), referenceMoCt,"reference is ok");
		
		softAssert.assertEquals(getType(), type,"type is ok");

		softAssert.assertTrue
		(McsElement.isElementPresent(driver,"//div[contains(@class,'xtb-text') and contains(text(),'Work Order') and contains(text(),'"+referenceMoCt+"')]"),"header is ok");
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(5);
		
		closeModule("Work Order");
		
		waitForExtJSAjaxComplete(5);
		
		clickXPath(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);
		
        waitForExtJSAjaxComplete(20);
        
        waitForExtJSAjaxComplete(25);
        
        setDetailsTabCollapsedMode();
        
        waitForExtJSAjaxComplete(5);
		
		String woArr[] = {referenceMoCt, referenceMo, referenceNr};
		
		deleteWOFromOverview(woArr);
		
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue
		(Grid.isRowInGridAbsent(driver, referenceMo),"workorder "+referenceMo+" is deleted");
		
		softAssert.assertTrue
		(Grid.isRowInGridAbsent(driver, referenceNr),"workorder "+referenceNr+" is deleted");
		
		softAssert.assertTrue
		(Grid.isRowInGridAbsent(driver, referenceMoCt),"workorder "+referenceMoCt+" is deleted");
		
		softAssert.assertAll();
				
		Reporter.log("Work order was created <br>", true);

	}

	/**
	 * Test Case ID: C75075
	 * Author : SSU
	 * This Test is not applicable for 122
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testComplainceLevelField() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Compliance Level field in Web Workorders : C62321" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testComplainceLevelField");
		
		//WO Created using Normal Template
		String workOrder1 = "AQAWoType";
		//WO Created using Maintenance Plan Template
		String workOrder2 = "AQAWoGenerationViaMaintPlanTemp";
		//Default Compliance Value
		String complainceValue = "None";
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_WORKORDERS);
		
		Thread.sleep(20000);
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		if("chrome".equals(configuration.getBrowser())){
			
			//Filter WO Grid with WO 2 Reference
			filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", workOrder1, "Reference");
			
			Thread.sleep(5000);
			
			waitForExtJSAjaxComplete(25);
			
			//Open the WO 2
			//TODO Changed from openTransactionLineModified
			openTransactionLineModified("@class", "x-grid3-viewport", workOrder1, "Reference");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
		}
		else{
			//Filter WO Grid with WO 2 Reference
			filterGridWithoutUsingMcsElementModified("@class", "x-grid3-viewport", workOrder1, "Reference");
			
			Thread.sleep(5000);
			
			waitForExtJSAjaxComplete(25);
			//Open the WO 2
			openTransactionLineFirefox("@class", "x-grid3-viewport", workOrder1, "Reference");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
		}
		
		String windowID = getWindowIdByClass("x-window x-resizable-pinned");
		
		clickOnTab(windowID, "General");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertFalse(isComplainceFieldVisible(windowID), "Complaince Field is not available");
		
		waitForExtJSAjaxComplete(5);
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(20);
		
		//Clear Filter
		clearOverviewFilters();
				
		waitForExtJSAjaxComplete(10);
		
		//Open WO 2 which has Maintanence Plan Template
		
		if("chrome".equals(configuration.getBrowser())){
			
			//Filter WO Grid with WO 2 Reference
			filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", workOrder2, "Reference");
			
			Thread.sleep(5000);
			
			waitForExtJSAjaxComplete(25);
			
			//Open the WO 2
			//TODO Changed from openTransactionLineModified
			openTransactionLineModified("@class", "x-grid3-viewport", workOrder2, "Reference");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
		}
		else{
			//Filter WO Grid with WO 2 Reference
			filterGridWithoutUsingMcsElementModified("@class", "x-grid3-viewport", workOrder2, "Reference");
			
			Thread.sleep(5000);
			
			waitForExtJSAjaxComplete(25);
			//Open the WO 2
			openTransactionLineFirefox("@class", "x-grid3-viewport", workOrder2, "Reference");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
		}
		
		clickOnTab(windowID, "General");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(isComplainceFieldVisible(windowID), "Complaince Field is available");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isComplianceFieldReadOnly(windowID), "Compliance field is read only");
		
		waitForExtJSAjaxComplete(5);
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(20);
		
		clickChangeVisibleColumns();
		
		waitForExtJSAjaxComplete(20);
		
		//Expand Maintenance and Check Compliance Level Field
		changeVisibleColumns(CHANGE_VISIBLE_COLUMNS_HEADER, "gp-group-Maintenance-hd", "group-Maintenance-bd", "gp-group-Maintenance", "Compliance Level");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Save", getXWindowId(CHANGE_VISIBLE_COLUMNS_HEADER));
		
		waitForExtJSAjaxComplete(25);
		
		//Check Compliance Level Column in Grid
		softAssert.assertTrue(checkColumnNameInGrid("x-grid3-viewport", "Compliance Level"), "Compliance Level Column is available in the Grid");
		
		waitForExtJSAjaxComplete(20);
		
		//Clear Filter
		clearOverviewFilters();
		
		waitForExtJSAjaxComplete(10);
		
		clickFilterResults();
		
		waitForExtJSAjaxComplete(20);
		
		expandMaintenanceAndSelectComplianceValueInFilterResults("x-window x-resizable-pinned", "Add restriction", "Maintenance", "Compliance Level");
		
		waitForExtJSAjaxComplete(20);
		
		//*****Enter Special Character*****
		String invalidChar ="?!@#$%^&";
		
		enterComplianceLevelValue("x-window x-resizable-pinned", invalidChar);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, invalidChar, "@class", "x-grid3-viewport"), "WO Containing Complaince Field "+invalidChar+" is Not Available in the Grid");
		
		Reporter.log(getGridSize()+ " Filtered Compliance Value with Special Characters", true);
		
		clickFilterResults();
		
		waitForExtJSAjaxComplete(20);
		
		selectComplianceRestriction("x-window x-resizable-pinned", "Compliance Level =");
		
		waitForExtJSAjaxComplete(20);
		
		clickAnyButtonInFilterResults("x-window x-resizable-pinned", "Edit restriction");
		
		waitForExtJSAjaxComplete(20);
		
		//*****Enter 50 Characters*****
		String overFlow = "abababababababababababaabbabababababababababababab";
		
		enterComplianceLevelValue("x-window x-resizable-pinned", overFlow);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, overFlow, "@class", "x-grid3-viewport"), "WO Containing Complaince Field "+overFlow+" is Not Available in the Grid");
		
		Reporter.log(getGridSize()+ " Filtered Compliance Value with Special Characters", true);
		
		clickFilterResults();
		
		waitForExtJSAjaxComplete(20);
		
		selectComplianceRestriction("x-window x-resizable-pinned", "Compliance Level =");
		
		waitForExtJSAjaxComplete(20);
		
		clickAnyButtonInFilterResults("x-window x-resizable-pinned", "Edit restriction");
		
		waitForExtJSAjaxComplete(20);
		
		//*****Enter SQL*****
		String sqlQuery = "Select * from Employee";
		
		enterComplianceLevelValue("x-window x-resizable-pinned", sqlQuery);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, sqlQuery, "@class", "x-grid3-viewport"), "WO Containing Complaince Field "+sqlQuery+" is Not Available in the Grid");
		
		Reporter.log(getGridSize()+ " Filtered Compliance Value with Special Characters", true);
		
		clickFilterResults();
		
		waitForExtJSAjaxComplete(20);
		
		selectComplianceRestriction("x-window x-resizable-pinned", "Compliance Level =");
		
		waitForExtJSAjaxComplete(20);
		
		clickAnyButtonInFilterResults("x-window x-resizable-pinned", "Edit restriction");
		
		waitForExtJSAjaxComplete(20);
		
		//*****Enter HTML*****
		String htmlTag = "<HTML></HTML>";
		
		enterComplianceLevelValue("x-window x-resizable-pinned", htmlTag);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, htmlTag, "@class", "x-grid3-viewport"), "WO Containing Complaince Field "+htmlTag+" is Not Available in the Grid");
		
		Reporter.log(getGridSize()+ " Filtered Compliance Value with Special Characters", true);
		
		clickFilterResults();
		
		waitForExtJSAjaxComplete(20);
		
		selectComplianceRestriction("x-window x-resizable-pinned", "Compliance Level =");
		
		waitForExtJSAjaxComplete(20);
		
		clickAnyButtonInFilterResults("x-window x-resizable-pinned", "Edit restriction");
		
		waitForExtJSAjaxComplete(20);
		
		//*****Enter Text with WhiteSpaces*****
		String whiteSpace = " ABC ";
		
		enterComplianceLevelValue("x-window x-resizable-pinned", whiteSpace.trim());
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, whiteSpace, "@class", "x-grid3-viewport"), "WO Containing Complaince Field "+whiteSpace+" is Not Available in the Grid");
		
		Reporter.log(getGridSize()+ " Filtered Compliance Value with Special Characters", true);
		
		clickFilterResults();
		
		waitForExtJSAjaxComplete(20);
		
		selectComplianceRestriction("x-window x-resizable-pinned", "Compliance Level =");
		
		waitForExtJSAjaxComplete(20);
		
		clickAnyButtonInFilterResults("x-window x-resizable-pinned", "Edit restriction");
		
		waitForExtJSAjaxComplete(20);
		
		//****Enter Proper Value*****
		enterComplianceLevelValue("x-window x-resizable-pinned", complainceValue);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, workOrder2, "@class", "x-grid3-viewport"), "WO Containing Complaince Field "+complainceValue+" is filtered");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertAll();
		
		Reporter.log("Compliance Level field in Web Workorders is successfully verified", true);
	}
	
	/**
	 * Test Case ID: C75226
	 * Author : SSU
	 * This Feature is available only in Trunk and 15.0
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testStockLevelDisplayCheckOnBOM() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: It is possible to select multiple Stock items and all the info for selected Stock items will be displayed on the form : C71172" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testStockLevelDisplayCheckOnBOM");
		
		//WO Created using Normal Template
		String workOrder1 = "StockLevelspotcheckonBOM";
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
				
		waitAndClick(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(20);
		
		//Filter WO Grid with WO Reference
		filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", workOrder1, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		Thread.sleep(5000);
		
		//Open the WO 
		//TODO Changed from openTransactionLineModified
		openTransactionLineModified("@class", "x-grid3-viewport", workOrder1, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		String windowID = getWindowIdByClass("x-window x-resizable-pinned");
		
		clickOnTab(windowID, "Time & Material");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(windowID, "Bill of Materials");
		
		waitForExtJSAjaxComplete(25);
		
		String productRef1 = getProductDetails("@class", "x-window x-resizable-pinned", "1", "Reference");
		String uomProduct1 = getProductDetails("@class", "x-window x-resizable-pinned", "1", "UOM");
		String neededQtyProduct1 = getProductDetails("@class", "x-window x-resizable-pinned", "1", "Needed Qty");
		
		String productRef2 = getProductDetails("@class", "x-window x-resizable-pinned", "2", "Reference");
		String uomProduct2 = getProductDetails("@class", "x-window x-resizable-pinned", "2", "UOM");
		String neededQtyProduct2 = getProductDetails("@class", "x-window x-resizable-pinned", "2", "Needed Qty");
		
		String productRef3 = getProductDetails("@class", "x-window x-resizable-pinned", "3", "Reference");
		String uomProduct3 = getProductDetails("@class", "x-window x-resizable-pinned", "3", "UOM");
		String neededQtyProduct3 = getProductDetails("@class", "x-window x-resizable-pinned", "3", "Needed Qty");
		
		//Click on Header Checkbox so that it select all rows
		selectHeaderCheckBoxInBOM(windowID);
		
		waitForExtJSAjaxComplete(20);
		
		//Click Stock Info
		clickStockInfoButtonOnBOMTab(windowID, "Stock Info");
		
		waitForExtJSAjaxComplete(20);
		
		//Verify Details for Stock Item 1
		softAssert.assertTrue(verifyProductDetailsSInStockInfoPage("Stock Info", productRef1), "Product1 Reference is available in the Stock Info Page");
		
		softAssert.assertTrue(verifyProductDetailsSInStockInfoPage("Stock Info", "aqapreProdCod1"), "Product1 Code is available in the Stock Info Page");
		
		softAssert.assertTrue(verifyProductDetailsSInStockInfoPage("Stock Info", uomProduct1), "Product1 Needed Quantity is available in the Stock Info Page");
		
		softAssert.assertTrue(verifyProductDetailsSInStockInfoPage("Stock Info", neededQtyProduct1), "Product1 UOM is available in the Stock Info Page");
		
		waitForExtJSAjaxComplete(20);
		
		//Verify Details for Stock Item 2
		softAssert.assertTrue(verifyProductDetailsSInStockInfoPage("Stock Info", productRef2), "Product2 Reference is available in the Stock Info Page");
		
		softAssert.assertTrue(verifyProductDetailsSInStockInfoPage("Stock Info", "aqapreProdCod2"), "Product2 Code is available in the Stock Info Page");
		
		softAssert.assertTrue(verifyProductDetailsSInStockInfoPage("Stock Info", uomProduct2), "Product2 Needed Quantity is available in the Stock Info Page");
		
		softAssert.assertTrue(verifyProductDetailsSInStockInfoPage("Stock Info", neededQtyProduct2), "Product2 UOM is available in the Stock Info Page");
		
		waitForExtJSAjaxComplete(10);
		
		//Verify Details for Stock Item 2
		softAssert.assertTrue(verifyProductDetailsSInStockInfoPage("Stock Info", productRef3), "Product3 Reference is available in the Stock Info Page");
		
		softAssert.assertTrue(verifyProductDetailsSInStockInfoPage("Stock Info", "aqapreProdCod3"), "Product3 Code is available in the Stock Info Page");
		
		softAssert.assertTrue(verifyProductDetailsSInStockInfoPage("Stock Info", uomProduct3), "Product3 Needed Quantity is available in the Stock Info Page");
		
		softAssert.assertTrue(verifyProductDetailsSInStockInfoPage("Stock Info", neededQtyProduct3), "Product3 UOM is available in the Stock Info Page");
		
		waitForExtJSAjaxComplete(10);
		
		//Verification of Alignment for Product 1 Details
		Reporter.log("Product1 Reference is left aligned and present in Top Of Stock Item Page " +getStyleOfElementsInStockInfoPage("Stock Info", productRef1), true);
		
		Reporter.log("Product1 Code is left aligned and present in Top Of Stock Item Page " +getStyleOfElementsInStockInfoPage("Stock Info", "aqapreProdCod1"), true);
		
		Reporter.log("Product1 Needed Quantity is left aligned and present in Top Of Stock Item Page " +getStyleOfElementsInStockInfoPage("Stock Info", neededQtyProduct1), true);
		
		Reporter.log("Product1 UOM is left aligned and present in Top Of Stock Item Page " +getStyleOfElementsInStockInfoPage("Stock Info", uomProduct1), true);
		
		waitForExtJSAjaxComplete(5);
		
		//Verification of Alignment for Product 2 Details
		Reporter.log("Product2 Reference is left aligned and present in Top Of Stock Item Page " +getStyleOfElementsInStockInfoPage("Stock Info", productRef2), true);
		
		Reporter.log("Product2 Code is left aligned and present in Top Of Stock Item Page " +getStyleOfElementsInStockInfoPage("Stock Info", "aqapreProdCod2"), true);
		
		Reporter.log("Product2 Needed Quantity is left aligned and present in Top Of Stock Item Page " +getStyleOfElementsInStockInfoPage("Stock Info", neededQtyProduct2), true);
		
		Reporter.log("Product2 UOM is left aligned and present in Top Of Stock Item Page " +getStyleOfElementsInStockInfoPage("Stock Info", uomProduct2), true);
		
		waitForExtJSAjaxComplete(5);
		
		//Verification of Alignment for Product 3 Details
		Reporter.log("Product3 Reference is left aligned and present in Top Of Stock Item Page " +getStyleOfElementsInStockInfoPage("Stock Info", productRef3), true);
		
		Reporter.log("Product3 Code is left aligned and present in Top Of Stock Item Page " +getStyleOfElementsInStockInfoPage("Stock Info", "aqapreProdCod3"), true);
		
		Reporter.log("Product3 Needed Quantity is left aligned and present in Top Of Stock Item Page " +getStyleOfElementsInStockInfoPage("Stock Info", neededQtyProduct3), true);
		
		Reporter.log("Product3 UOM is left aligned and present in Top Of Stock Item Page " +getStyleOfElementsInStockInfoPage("Stock Info", uomProduct3), true);
		
		softAssert.assertAll();
		
		Reporter.log("It is possible to select multiple Stock items and all the info for selected Stock items is be displayed on the form", true);
	}

	
	/**
	 * Test Case ID: C75069
	 * Author : SSU
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testNegativeConsumptionsOnConsumptionForm() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Using negative Consumptions on the Add/Edit Consumptions form : C62315" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testNegativeConsumptionsOnConsumptionForm");
		
		//WO Created using Normal Template
		String workOrder1 = "AqaNegativeConsumptionWO";
		String productNegative = "aqapreProdNegRef";
		String productPositive1 = "aqapreProdRef";
		String productPositive2 = "aqapreProdRef1";
		String QtyNegativeValue = "-10";
		String QtyNegativeValue1 = "-20";
		String QtyPositiveValue1 = "20";
		String QtyPositiveValue2 = "20";
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Thread.sleep(5000);
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(25);
		
		//Filter WO Grid with WO Reference
		filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", workOrder1, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		//Open the WO 
		//TODO Changed from openTransactionLineModified
		openTransactionLineModified("@class", "x-grid3-viewport", workOrder1, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		String windowID = getWindowIdByClass("x-window x-resizable-pinned");
		
		clickOnTab(windowID, "Time & Material");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(windowID, "Consumption");
		
		waitForExtJSAjaxComplete(25);
		
		String woRealID = "WoConsumptionGrid";
		
		String prodArray[] = {productPositive1, productPositive2, productNegative};
		
		//If Products Are already available in the grid, delete it.
		deleteProductsFromConsumption(windowID, prodArray, "Product Reference");
		
		waitForExtJSAjaxComplete(20);
		
		//Trying to Add Negative Quantity to a product (that contains negative Default Revenue)  
		clickButtonTimeAndMaterialTab(windowID, "Add");
		
		waitForExtJSAjaxComplete(20);
		
		selectProductInConsumptionWindow(ADD_CONSUMPTION_WINDOW_HEADER, "Select a Product or Service", productNegative, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		setQuantityInConsumptionWindow(ADD_CONSUMPTION_WINDOW_HEADER, QtyNegativeValue);
		
		waitForExtJSAjaxComplete(10);
		
		clickButtonConsumptionWindow(ADD_CONSUMPTION_WINDOW_HEADER, "Save");
		
		waitForExtJSAjaxComplete(10);
		
		String errorMessage = "You cannot add a Consumption with negative Quantity and Unit Price.";
		
		softAssert.assertTrue(getExtMbContentOfWindow("@class", "x-window-dlg").contains(errorMessage), "Error Message Dispayed:: "+errorMessage);
		
		waitForExtJSAjaxComplete(5);
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		//Set Positive Quantity to the same product
		setQuantityInConsumptionWindow(ADD_CONSUMPTION_WINDOW_HEADER, QtyPositiveValue1);
		
		waitForExtJSAjaxComplete(10);
		
		clickButtonConsumptionWindow(ADD_CONSUMPTION_WINDOW_HEADER, "Save");
		
		waitForExtJSAjaxComplete(25);
		
		closeUsingToolBarJS(ADD_CONSUMPTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		String costItemID1 = getCostItemID("@id", getXWindowIdByClass("x-window x-resizable-pinned"), "1", "Cost Item ID");
		Reporter.log(costItemID1+ " value is", true);
		
		waitForExtJSAjaxComplete(20);
		
		//Trying to Add Positive Quantity to a product (that contains Positive Default Revenue)  
		clickButtonTimeAndMaterialTab(windowID, "Add");
		
		waitForExtJSAjaxComplete(20);
		
		selectProductInConsumptionWindow(ADD_CONSUMPTION_WINDOW_HEADER, "Select a Product or Service", productPositive1, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		setQuantityInConsumptionWindow(ADD_CONSUMPTION_WINDOW_HEADER, QtyPositiveValue2);
		
		waitForExtJSAjaxComplete(10);
		
		clickButtonConsumptionWindow(ADD_CONSUMPTION_WINDOW_HEADER, "Save");
		
		waitForExtJSAjaxComplete(25);
		
		closeUsingToolBarJS(ADD_CONSUMPTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		String costItemID2 = getCostItemID("@id", getXWindowIdByClass("x-window x-resizable-pinned"), "1", "Cost Item ID");
		Reporter.log(costItemID2+ " value is", true);
		
		waitForExtJSAjaxComplete(20);
		
		//Trying to Add Negative Quantity to a product (that contains Positive Default Revenue)  
		clickButtonTimeAndMaterialTab(windowID, "Add");
		
		waitForExtJSAjaxComplete(20);
		
		selectProductInConsumptionWindow(ADD_CONSUMPTION_WINDOW_HEADER, "Select a Product or Service", productPositive2, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		setQuantityInConsumptionWindow(ADD_CONSUMPTION_WINDOW_HEADER, QtyNegativeValue1);
		
		waitForExtJSAjaxComplete(10);
		
		clickButtonConsumptionWindow(ADD_CONSUMPTION_WINDOW_HEADER, "Save");
		
		waitForExtJSAjaxComplete(25);
		
		closeUsingToolBarJS(ADD_CONSUMPTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		String costItemID3 = getCostItemID("@id", getXWindowIdByClass("x-window x-resizable-pinned"), "3", "Cost Item ID");
		Reporter.log(costItemID3+ " value is", true);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		//Edit the Previous Record and Enter Positive Value
		Grid.checkRowInGriByTextValueAndClick(driver, "x-window x-resizable-pinned", productPositive2);
		
		waitForExtJSAjaxComplete(20);
		
		clickButtonTimeAndMaterialTab(windowID, "Edit");
		
		waitForExtJSAjaxComplete(20);
		
		setQuantityInConsumptionWindow(EDIT_CONSUMPTION_WINDOW_HEADER, QtyPositiveValue1);
		
		waitForExtJSAjaxComplete(10);
		
		clickButtonConsumptionWindow(EDIT_CONSUMPTION_WINDOW_HEADER, "Save");
		
		waitForExtJSAjaxComplete(25);
		
		//Edit the Previous Record and Enter Negative Value
		Grid.checkRowInGriByTextValueAndClick(driver, "x-window x-resizable-pinned", productPositive2);
		
		waitForExtJSAjaxComplete(20);
		
		clickButtonTimeAndMaterialTab(windowID, "Edit");
		
		waitForExtJSAjaxComplete(20);
		
		setQuantityInConsumptionWindow(EDIT_CONSUMPTION_WINDOW_HEADER, QtyNegativeValue1);
		
		waitForExtJSAjaxComplete(10);
		
		clickButtonConsumptionWindow(EDIT_CONSUMPTION_WINDOW_HEADER, "Save");
		
		waitForExtJSAjaxComplete(25);
		
		//Edit Negative Quantity to a product (that contains negative Default Revenue)
		Grid.checkRowInGriByTextValueAndClick(driver, "x-window x-resizable-pinned", productNegative);
		
		waitForExtJSAjaxComplete(20);
		
		clickButtonTimeAndMaterialTab(windowID, "Edit");
		
		waitForExtJSAjaxComplete(20);
		
		setQuantityInConsumptionWindow(EDIT_CONSUMPTION_WINDOW_HEADER, QtyNegativeValue);
		
		waitForExtJSAjaxComplete(10);
		
		clickButtonConsumptionWindow(EDIT_CONSUMPTION_WINDOW_HEADER, "Save");
		
		waitForExtJSAjaxComplete(10);
		
		errorMessage = "A Consumption cannot have both negative Quantity and Unit Price.";
		
		softAssert.assertTrue(getExtMbContentOfWindow("@class", "x-window-dlg").contains(errorMessage), "Error Message Dispayed:: "+errorMessage);
		
		waitForExtJSAjaxComplete(5);
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		setQuantityInConsumptionWindow(EDIT_CONSUMPTION_WINDOW_HEADER, QtyPositiveValue2);
		
		waitForExtJSAjaxComplete(10);
		
		clickButtonConsumptionWindow(EDIT_CONSUMPTION_WINDOW_HEADER, "Save");
		
		waitForExtJSAjaxComplete(10);
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//Navigate to Financials and Verify Cost Items Generated 
		expandSubMainMenu("Financials");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_CONSUMPTIONS);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Thread.sleep(10000);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel financials x-panel-noborder", costItemID1, "Cost Item ID");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		boolean statusNew = false;
		
		statusNew = verifyCostItemIDInFinancialsGrid("@class", "x-panel financials x-panel-noborder", costItemID1, "Cost Item ID");
		
		softAssert.assertTrue(statusNew, "Cost Item ID "+costItemID1+" is Succesfully Generated in Consumptions");
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel financials x-panel-noborder", costItemID2, "Cost Item ID");
		
		Thread.sleep(20000);
		
		statusNew = verifyCostItemIDInFinancialsGrid("@class", "x-panel financials x-panel-noborder", costItemID2, "Cost Item ID");
		
		softAssert.assertTrue(statusNew, "Cost Item ID "+costItemID2+" is Succesfully Generated in Consumptions");
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class", "x-panel financials x-panel-noborder", costItemID3, "Cost Item ID");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		statusNew = verifyCostItemIDInFinancialsGrid("@class", "x-panel financials x-panel-noborder", costItemID3, "Cost Item ID");
		
		softAssert.assertTrue(statusNew, "Cost Item ID "+costItemID3+" is Succesfully Generated in Consumptions");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("Negative Consumptions on the Add/Edit Consumptions form is successfully Verified", true);
	}

	/**
	 * Test Case ID: C75069
	 * Author : SSU
	 * This test is applicable only For 15.0 and Trunk
	 */
	@Test(enabled=true)
	public void testPlanningSectionCalculations() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Planning section : C62327" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testPlanningSectionCalculations");
		
		String workOrder = "TestPlanningSection";
		String estimatedWork = "20";
		String startTimeInput = "timeStart";
		String stopTimeInput = "timeStop";
		double estimatedWrkdbl = Double.parseDouble(estimatedWork);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(25);
		
		clickChangeVisibleColumns();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//Expand Maintenance and Check Compliance Level Field
		changeVisibleColumns(CHANGE_VISIBLE_COLUMNS_HEADER, "gp-group-Planning-hd", "group-Planning-bd", "gp-group-Planning", "Planned Work (man-hours)");
		
		waitForExtJSAjaxComplete(10);
		
		changeVisibleColumns(CHANGE_VISIBLE_COLUMNS_HEADER, "gp-group-Planning-hd", "group-Planning-bd", "gp-group-Planning", "Remaining Work (hours)");
		
		waitForExtJSAjaxComplete(10);
		
		changeVisibleColumns(CHANGE_VISIBLE_COLUMNS_HEADER, "gp-group-Planning-hd", "group-Planning-bd", "gp-group-Planning", "Actual Work (man-hours)");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Save", getXWindowId(CHANGE_VISIBLE_COLUMNS_HEADER));
		
		waitForExtJSAjaxComplete(25);
		
		//Check Planned/Remaining/Actual Work Columns in Grid
		softAssert.assertTrue(checkColumnNameInGrid("x-grid3-viewport", "Planned Work (man-hours)"), "Planned Work (man-hours) Column is available in the Grid");
		
		softAssert.assertTrue(checkColumnNameInGrid("x-grid3-viewport", "Remaining Work (hours)"), "Remaining Work (hours) Column is available in the Grid");
		
		softAssert.assertTrue(checkColumnNameInGrid("x-grid3-viewport", "Actual Work (man-hours)"), "Actual Work (man-hours) Column is available in the Grid");
		
		waitForExtJSAjaxComplete(20);
		
		//Filter WO Grid with WO Reference
		//filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", workOrder, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		//Open the WO 
		//TODO Changed from openTransactionLineModified
		openTransactionLineModified("@class", "x-grid3-viewport", workOrder, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		String windowID = getWindowIdByClass("x-window x-resizable-pinned");
		
		clickOnTab(windowID, "Tracking");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(windowID, "Planning");
		
		waitForExtJSAjaxComplete(25);
		
		double estimatedWrkActual = getValuesPlanningTab(windowID, "estWork");
		double spentPercent = getValuesPlanningTab(windowID, "spentPercent");
		double remainingPercent = getValuesPlanningTab(windowID, "remainingPercent");
		double zeroValue = 0;
		
		// If Estimated Work = 0 then %spent and % remaining fields remain empty. 
		if(estimatedWrkActual==0){
			
			softAssert.assertEquals(spentPercent, zeroValue, "Spent Percent is 0");
			
			softAssert.assertEquals(remainingPercent, 0.0, "Remaining Percent is 0");
		}
		
		waitForExtJSAjaxComplete(25);
		
		setEstimatedWork(windowID, estimatedWork);
		
		waitForExtJSAjaxComplete(25);
		
		saveWorkOrder(windowID, "Save Work Order");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		double plannedWork = getValuesPlanningTab(windowID, "plannedWork");
		double plannedWorkPercent = getValuesPlanningTab(windowID, "plannedPercent");
		double unPlannedWork = getValuesPlanningTab(windowID, "unplannedWork");
		double actualWork = getValuesPlanningTab(windowID, "actualWork");
		
		double remainingWork = getValuesPlanningTab(windowID, "remainingWork");
		remainingPercent = getValuesPlanningTab(windowID, "remainingPercent");
		
		estimatedWrkActual = getValuesPlanningTab(windowID, "estWork");
		
		softAssert.assertEquals(estimatedWrkActual, estimatedWrkdbl, "Estimated value is set as 20 Man Hours");
		
		softAssert.assertEquals(unPlannedWork, (estimatedWrkdbl-plannedWork), "UnPlanned Work is Updated with Estimated Work hours Value");
		
		softAssert.assertEquals(remainingWork, estimatedWrkActual, "Remaining Work Man Hours is Updated with Estimated Work hours Value");
		
		softAssert.assertEquals(remainingPercent, 100.0, "Remaining Percent is Updated as 100%");
		
		waitForExtJSAjaxComplete(5);
		
		//Add 2 Activities in Labor Tab
		clickOnTab(windowID, "Time & Material");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(windowID, "Labor");
		
		waitForExtJSAjaxComplete(25);
		
		clickButton("@realid", "WoLaborGrid", "Add");
		
		waitForExtJSAjaxComplete(25);
		
		setStartEndTime(ADD_ACTIVITY_WINDOW_HEADER, startTimeInput, "09:00");
		
		setStartEndTime(ADD_ACTIVITY_WINDOW_HEADER, stopTimeInput, "10:00");
		
		waitForExtJSAjaxComplete(10);
		
		setWorkforce("workforce", "Select a Person", "Sundaramurthy", "Last Name");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("@id", getXWindowId(ADD_ACTIVITY_WINDOW_HEADER), "Save and Close");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickButton("@realid", "WoLaborGrid", "Add");
		
		waitForExtJSAjaxComplete(25);
		
		setStartEndTime(ADD_ACTIVITY_WINDOW_HEADER, startTimeInput, "11:00");
		
		setStartEndTime(ADD_ACTIVITY_WINDOW_HEADER, stopTimeInput, "12:30");
		
		waitForExtJSAjaxComplete(10);
		
		setWorkforce("workforce", "Select a Person", "SELENIUM", "Last Name");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("@id", getXWindowId(ADD_ACTIVITY_WINDOW_HEADER), "Save and Close");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(isRowInLaborGridPresent("WoLaborGrid", "Start Time", "09:00"), "Activity with Start time 09:00 is available in the grid");
		
		softAssert.assertTrue(isRowInLaborGridPresent("WoLaborGrid", "Start Time", "11:00"), "Activity with Start time 11:00 is available in the grid");
		
		waitForExtJSAjaxComplete(10);
		
		//Navigate to Planning tab to verify calculations
		//Actual Work field Calculations
		clickOnTab(windowID, "Tracking");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(windowID, "Planning");
		
		waitForExtJSAjaxComplete(25);
		
		estimatedWrkActual = getValuesPlanningTab(windowID, "estWork");
		spentPercent = getValuesPlanningTab(windowID, "spentPercent");
		actualWork = getValuesPlanningTab(windowID, "actualWork");
		remainingWork = getValuesPlanningTab(windowID, "remainingWork");
		plannedWork = getValuesPlanningTab(windowID, "plannedWork");
		remainingPercent = getValuesPlanningTab(windowID, "remainingPercent");
		unPlannedWork = getValuesPlanningTab(windowID, "unplannedWork");
		plannedWorkPercent = getValuesPlanningTab(windowID, "plannedPercent");
		
		double percentSpent = percentageSpent(actualWork, estimatedWrkActual);
		
		softAssert.assertEquals(percentSpent, spentPercent, "Percent Spent is Calculated in Planning Tab");
		
		waitForExtJSAjaxComplete(20);
		
		double remainingWorkDouble = remainingWork(actualWork, estimatedWrkActual);
		
		softAssert.assertEquals(remainingWorkDouble, remainingWork, "Remaining Work is Calculated in Planning Tab");
		
		waitForExtJSAjaxComplete(20);
		
		double percentageRemaining = percentageRemaining(remainingWork, estimatedWrkActual);
		
		softAssert.assertEquals(percentageRemaining, remainingPercent, "Remaining Work is Calculated in Planning Tab");
		
		waitForExtJSAjaxComplete(20);
		
		//Tasks are already added in Database, Navigate to Planning tab to verify calculations
		//Planned Work field Calculations
		
		waitForExtJSAjaxComplete(25);
		
		double percentPlanned = percentagePlanned(plannedWork, estimatedWrkActual);
		
		softAssert.assertEquals(percentPlanned, plannedWorkPercent, "Planned Work Percent is Calculated in Planning Tab");
		
		waitForExtJSAjaxComplete(20);
		
		double unplannedWork = unplannedWork(plannedWork, estimatedWrkActual);
		
		softAssert.assertEquals(unplannedWork, unPlannedWork, "Unplanned Work is Calculated in Planning Tab");
		
		waitForExtJSAjaxComplete(20);
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//Filter The Overview
		filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", workOrder, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		//Verify Columns Updated
		softAssert.assertEquals(getValuesFromExecutionColumns("@class", "x-grid3-viewport", "Planned Work"), plannedWork, "Planned Work is Updated In Overview");
		
		softAssert.assertEquals(getValuesFromExecutionColumns("@class", "x-grid3-viewport", "Remaining Work"), remainingWork, "Remaining Work is Updated In Overview");
		
		softAssert.assertEquals(getValuesFromExecutionColumns("@class", "x-grid3-viewport", "Actual Work"), actualWork, "Actual Work is Updated In Overview");
		
		waitForExtJSAjaxComplete(25);
		
		clearOverviewFilters();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//TODO Changed from openTransactionLineModified
		openTransactionLineModified("@class", "x-grid3-viewport", workOrder, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		windowID = getWindowIdByClass("x-window x-resizable-pinned");
		
		clickOnTab(windowID, "Time & Material");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(windowID, "Labor");
		
		checkRowInLaborTabGrid("Start Time", "09:00");
		
		waitForExtJSAjaxComplete(10);
		
		clickDeleteLabor("Yes");
		
		waitForExtJSAjaxComplete(25);
		
		checkRowInLaborTabGrid("Start Time", "11:00");
		
		waitForExtJSAjaxComplete(10);
		
		clickDeleteLabor("Yes");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(windowID, "Tracking");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(windowID, "Planning");
		
		waitForExtJSAjaxComplete(25);
		
		setEstimatedWork(windowID, "0");
		
		waitForExtJSAjaxComplete(25);
		
		saveWorkOrder(windowID, "Save Work Order");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertAll();
		
		Reporter.log("Planning section Calculations are verified successfully.", true);
	}

	
	/**
	 * Test Case ID: C75029
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testChangeOfWOStatusByDifferentUser() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: User change Workorder Status created by another user : C62275" + " </span><br>", 
				true);
		
		String user1="AqaWebEmplDntDelete";
		String user2="AQA_SELENIUM";
		String password="qwerty";
		
		String possibleAction1 = "Action1";
		String possibleAction2 = "Action2";
		String possibleAction3 = "Action3";
		
		String action1Status = "aqa_open";
		String action2Status = "aqa_processed";
		String action3Status = "aqa_closed";
		
		String workOrderType = "Aqa_WO_Type_Name";
		String referenceWO1 = "WoCreatedByUser1"+getCurrentDate().substring(getCurrentDate().length()-5);
		String referenceWO2 = "WoCreatedByUser2"+getCurrentDate().substring(getCurrentDate().length()-5);
		String nature = "Default Nature";
		String priority = "Default Priority";
		String severity = "Default Severity";
		String location = "slnmEnrgBuilding1";
		
		String workOrderTemplate = "Aqa_WO_Type";
		
		String dueDate = getFutureDate(-1);

		String dueTime = getMinsEarlyOfSystemTime(-15);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testChangeOfWOStatusByDifferentUser");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//Log into Portal using User 2		
		waitAndClick(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton();
			
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Thread.sleep(5000);
		
/*		setWorkOrderType(workOrderType);
		
		waitForExtJSAjaxComplete(20);

		clickNextOnDetailsPanel();

		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();

		setReference(referenceWO2);
		
		waitForExtJSAjaxComplete(10);

		setPriority(priority);
		
		waitForExtJSAjaxComplete(10);
		
		setSeverity(severity);
		
		setNature(nature);
		
		waitForExtJSAjaxComplete(25);
		
		clickSaveOrder();*/
		
		expandTemplateGroup();
		
		waitForExtJSAjaxComplete(20);
		
		selectTemplate(workOrderTemplate);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(referenceWO2);
		
		waitForExtJSAjaxComplete(25);
		
		setPriority(priority);
		
		waitForExtJSAjaxComplete(25);
	
		setSeverity(severity);
		
		waitForExtJSAjaxComplete(25);
		
		setNature(nature);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickSaveOrder();
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//Log into Portal using User 1 
		navigateToMainPage(user1,password); //AqaWebEmplDntDelete
		
		waitForExtJSAjaxComplete(25);
		
		//Create New WO by USER1
		waitAndClick(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Thread.sleep(5000);
		
	/*	setWorkOrderType(workOrderType);
		
		waitForExtJSAjaxComplete(20);

		clickNextOnDetailsPanel();

		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();

		setReference(referenceWO1);
		
		waitForExtJSAjaxComplete(10);

		setPriority(priority);
		
		waitForExtJSAjaxComplete(10);
		
		setSeverity(severity);
		
		setNature(nature);
		
		waitForExtJSAjaxComplete(25);
		
		clickSaveOrder();
		
		waitForExtJSAjaxComplete(25);*/
		
		expandTemplateGroup();
		
		waitForExtJSAjaxComplete(20);
		
		selectTemplate(workOrderTemplate);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(referenceWO1);
		
		waitForExtJSAjaxComplete(25);
		
		setPriority(priority);
		
		waitForExtJSAjaxComplete(25);
	
		setSeverity(severity);
		
		waitForExtJSAjaxComplete(25);
		
		setNature(nature);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickSaveOrder();
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(25);
		
		uncheckHeaderRowCheckBox();
		
		waitForExtJSAjaxComplete(10);
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(20);
		
		if("chrome".equals(configuration.getBrowser())){
			
			//Filter WO Grid with WO 2 Reference
			filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", referenceWO2, "Reference");
			
			Thread.sleep(5000);
			
			waitForExtJSAjaxComplete(25);
			
			//Open the WO 2
			//TODO Changed from openTransactionLineModified
			openTransactionLineModified("@class", "x-grid3-viewport", referenceWO2, "Reference");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
		}
		else{
			//Filter WO Grid with WO 2 Reference
			filterGridWithoutUsingMcsElementModified("@class", "x-grid3-viewport", referenceWO2, "Reference");
			
			Thread.sleep(5000);
			
			waitForExtJSAjaxComplete(25);
			//Open the WO 2
			openTransactionLineFirefox("@class", "x-grid3-viewport", referenceWO2, "Reference");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
		}
		
		String windowID = getWindowIdByClass("x-window x-resizable-pinned");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(getWONameFromDetailsWindow(referenceWO2), "WO2 Details window is opened");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(windowID, "Tracking");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(windowID, "History");
		
		waitForExtJSAjaxComplete(10);
		
		clickAddAction();
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions(possibleAction1);
		
		waitForExtJSAjaxComplete(10);
		
		saveCloseAction(ADD_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getToolBarStatuses("column-status-progress"), action1Status, action1Status+" is the status of the WO 2 "+referenceWO2);

		waitForExtJSAjaxComplete(25);
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(25);
		
		clearOverviewFilters();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		//Log into Portal using User 2
		navigateToMainPage(user2,password); //AQA_SELENIUM
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(20);
		
		if("chrome".equals(configuration.getBrowser())){
		
			filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", referenceWO2, "Reference");
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			//TODO Changed from openTransactionLineModified
			openTransactionLineModified("@class", "x-grid3-viewport", referenceWO2, "Reference");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
		} else{
			filterGridWithoutUsingMcsElementModified("@class", "x-grid3-viewport", referenceWO2, "Reference");
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			openTransactionLineFirefox("@class", "x-grid3-viewport", referenceWO2, "Reference");
			
			waitForExtJSAjaxComplete(25);
			
			Thread.sleep(5000);
		}
		
		windowID = getWindowIdByClass("x-window x-resizable-pinned");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(getWONameFromDetailsWindow(referenceWO2), "WO2 Details window is opened");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(windowID, "Tracking");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(windowID, "History");
		
		waitForExtJSAjaxComplete(10);
		
		clickAddAction();
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions(possibleAction2);
		
		waitForExtJSAjaxComplete(10);
		
		saveCloseAction(ADD_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getToolBarStatuses("column-status-progress"), action2Status, action2Status+" is the status of the WO 2 "+referenceWO2);

		waitForExtJSAjaxComplete(25);
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(25);
		
		clearOverviewFilters();
		
		waitForExtJSAjaxComplete(25);
		
		uncheckHeaderRowCheckBox();
		
		waitForExtJSAjaxComplete(10);
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(20);
		
		
		if("chrome".equals(configuration.getBrowser())){
			//Filter WO Grid with WO 2 Reference
			filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", referenceWO1, "Reference");
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			//Open the WO 2
			//TODO Changed from openTransactionLineModified
			openTransactionLineModified("@class", "x-grid3-viewport", referenceWO1, "Reference");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
		} else{
		
			//Filter WO Grid with WO 2 Reference
			filterGridWithoutUsingMcsElementModified("@class", "x-grid3-viewport", referenceWO1, "Reference");
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			//Open the WO 2
			openTransactionLineFirefox("@class", "x-grid3-viewport", referenceWO1, "Reference");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
		}
		
		windowID = getWindowIdByClass("x-window x-resizable-pinned");
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(getWONameFromDetailsWindow(referenceWO1), "WO1 Details window is opened");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(windowID, "Tracking");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(windowID, "History");
		
		waitForExtJSAjaxComplete(10);
		
		clickAddAction();
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions(possibleAction1);
		
		waitForExtJSAjaxComplete(10);
		
		saveCloseAction(ADD_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getToolBarStatuses("column-status-progress"), action1Status, action1Status+" is the status of the WO 1 "+referenceWO1);

		waitForExtJSAjaxComplete(25);
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(25);
		
		//Log into Portal using AqaWebEmplDntDelete
		navigateToMainPage(user1,password);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(20);
		
		if("chrome".equals(configuration.getBrowser())){
			
			filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", referenceWO2, "Reference");
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			//TODO Changed from openTransactionLineModified
			openTransactionLineModified("@class", "x-grid3-viewport", referenceWO2, "Reference");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
		} else{
			filterGridWithoutUsingMcsElementModified("@class", "x-grid3-viewport", referenceWO2, "Reference");
			
			waitForExtJSAjaxComplete(20);
			
			openTransactionLineFirefox("@class", "x-grid3-viewport", referenceWO2, "Reference");
			
			waitForExtJSAjaxComplete(25);
			
			Thread.sleep(5000);
		}
		
		windowID = getWindowIdByClass("x-window x-resizable-pinned");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(windowID, "General");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setDueDateGeneralTab();
		
		setDueTimeGeneralTab(WORKORDER_WINDOW_DETAIL, dueTime);
		
		waitForExtJSAjaxComplete(25);
		
		/*//setLocation(WORKORDER_WINDOW_DETAIL,location);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);*/	
		
		//setDueDateGeneralTab();
		
		//setDueTimeGeneralTab(WORKORDER_WINDOW_DETAIL, dueTime);
	
		saveWorkOrder(windowID, "Save Work Order");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Thread.sleep(5000);
		
		/*if("firefox".equals(configuration.getBrowser())) {
			softAssert.assertTrue(getDueTime("hdwo-details").equals(getDueTime("x-window x-resizable-pinned")), "Due time Format remains same as entered Time format HH:mm");
		} else{
			softAssert.assertTrue(getDueTime("hdwo-details").equals("HH:mm"), "Due time Format remains same as entered Time format HH:mm");
		}*/
		
		clickOnTab(windowID, "Tracking");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(windowID, "History");
		
		waitForExtJSAjaxComplete(10);
		
		clickAddAction();
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions(possibleAction3);
		
		waitForExtJSAjaxComplete(10);
		
		saveCloseAction(ADD_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getToolBarStatuses("column-status-finished"), action3Status, action3Status+" is the status of the WO 2 "+referenceWO2);
		
		softAssert.assertEquals(getToolBarStatuses("column-duedate-overdue"), "Overdue", "Overdue is Displayed in WO 2 "+referenceWO2);

		waitForExtJSAjaxComplete(25);
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(25);
		
		//Log into Portal using Aqa_Selenium
		navigateToMainPage(user2,password);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(20);
		
		
		if("chrome".equals(configuration.getBrowser())){
			
			filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", referenceWO1, "Reference");
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			//TODO Changed from openTransactionLineModified
			openTransactionLineModified("@class", "x-grid3-viewport", referenceWO1, "Reference");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
		} else{
			filterGridWithoutUsingMcsElementModified("@class", "x-grid3-viewport", referenceWO1, "Reference");
			
			waitForExtJSAjaxComplete(20);
			
			openTransactionLineFirefox("@class", "x-grid3-viewport", referenceWO1, "Reference");
			
			waitForExtJSAjaxComplete(25);
			
			Thread.sleep(5000);
		}
		
		windowID = getWindowIdByClass("x-window x-resizable-pinned");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(windowID, "General");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setDueDateGeneralTab();
		
		setDueTimeGeneralTab(WORKORDER_WINDOW_DETAIL, dueTime);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		saveWorkOrder(windowID, "Save Work Order");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Thread.sleep(5000);
		
		clickOnTab(windowID, "Tracking");
		
		waitForExtJSAjaxComplete(25);
		
		clickOnTab(windowID, "History");
		
		waitForExtJSAjaxComplete(10);
		
		clickAddAction();
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions(possibleAction3);
		
		waitForExtJSAjaxComplete(10);
		
		saveCloseAction(ADD_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getToolBarStatuses("column-status-finished"), action3Status, action3Status+" is the status of the WO 1 "+referenceWO1);
		
		softAssert.assertEquals(getToolBarStatuses("column-duedate-overdue"), "Overdue", "Overdue is Displayed in WO 1 "+referenceWO1);

		waitForExtJSAjaxComplete(25);
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertAll();
		
		Reporter.log("User change Workorder Status created by another user is successfully verified" , true);
	}
	
	/**
	 * Test Case ID: C92012 & C92134
	 * Author : SSU
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testCreateModifyAndCopyWOTemplate() throws Exception  {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create/Modify a workorder template. C92012" + " </span><br>", 
				true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Copy workorder template. C92134" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCreateModifyAndCopyWOTemplate");
		
		waitForExtJSAjaxComplete(25);
		
		String reference = "autoTempRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String referenceEdited = "autoTempRefEdit"+getCurrentDate().substring(getCurrentDate().length()-5);
		String referenceNewTemp = "RefNewTemp"+getCurrentDate().substring(getCurrentDate().length()-5);
		String woType = "Aqa_WO_Type_Name";
		String relatedObjType = "None";
		
		clickAdministration();
		
		waitForExtJSAjaxComplete(5);
		
		expandAdministrationOptions("Module Settings");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isWOTemplateUnderWONodeInAdministration(), "Workorder Templates node is listed under workorders.");
		
		clickAdministrationOptions("Workorders", "Work Order Templates");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(verifyWOTemplatePane(), "Workorder Templates pane is opened");
		
		softAssert.assertTrue(getMsgInWOTemplateDetailsBeforeSelectingTemplate().contains("Please select a Template or Template Group"), "Workorder Templates/Group needs to be selected warning is displayed");
		
		Thread.sleep(5000);
		
		clickButtonWOTemplateAdministration("icon-template-add");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(isTabPresentInWODetailsWin("General"), "Template is opened where user can provide properties of new template.");
		
		setReferenceWOTemplate(reference);
		
		waitForMaskDisappear();
		
		setWOTypeWOTemplate(woType);
		
		waitForExtJSAjaxComplete(15);
		
		selectRelatedObjectType(relatedObjType);
		
		selectLanguageInWoTemplate("English");
		
		clickButtonWOTemplateDetailsAdministration("icon-save", "Save Changes");
		
		waitForExtJSAjaxComplete(5);
		
		verifyTemplateIsCreatedAtFirstLevelInHierarchy(reference);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setReferenceWOTemplate(referenceEdited);
		
		clickButtonWOTemplateDetailsAdministration("icon-save", "Save Changes");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		selectWOTemplate(referenceEdited);
		
		waitForExtJSAjaxComplete(3);
		
		softAssert.assertEquals(getReferenceFromWOTemplate(), referenceEdited, "Reference field value is changed.");
		
		waitForExtJSAjaxComplete(2);
		
		clickButtonWOTemplateAdministration("icon-template-delete");
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(25);
		
		Reporter.log("Copy workorder template. Test Begins", true);
		
		expandWOTemplateGroup("AqaTemplateGroup");
		
		selectWOTemplate("Aqa_WO_Type");
		
		waitForExtJSAjaxComplete(1);
		
		clickButtonWOTemplateAdministration("icon-template-copy");
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(verifyWarningDialogTextMessage("Enter a reference for a new Template"), "Enter a reference for a new Template is displayed");
		
		enterReferenceInCopyWOTemplate(referenceNewTemp);
		
		waitForExtJSAjaxComplete(25);
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isWOTemplateUnderWOGroup("AqaTemplateGroup", referenceNewTemp), "New workorder template is created under the selected template group in the templates tree.");
		
		Reporter.log("Copy workorder template is successfully verified", true);
		
		softAssert.assertAll();
		
		Reporter.log("Create/Modify a workorder template is successfully verified", true);
	}

	/**
	 * Test Case ID: C75077
	 * Author : SSU
	 */
	@Test(enabled=false, retryAnalyzer=RetryAnalyzer.class)
	public void testFinancialSummaryDetailsOfWO() throws Exception  {
		//TODO Clarification has been sent to PO 
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Show Financial Summary tab. C75077" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testFinancialSummaryDetailsOfWO");
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		String woRef = "AQAWoType";
		String productRef = "1preProdRef";
		String revenueHeader = "Revenue";
		String expensesHeader = "Expenses";
		String balanceHeader = "Balance";
		String costCatColName = "Cost Category";
		String totalColName = "Total";
		String purchasedColName = "Purchased";
		String stockColName = "Stock";
		String nonStockColName = "Non-stock";
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(25);
		
		filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", woRef, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		openTransactionLineModified("@class", "x-grid3-viewport", woRef, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		String windowID = getWindowIdByClass("x-window x-resizable-pinned");
		
		String woID = getWOIDFromDetailsWindow();
		
		//Show Financial Summary.
		clickCommandDropDown(windowID, "Show Financial Summary");
		
		waitForExtJSAjaxComplete(2);
		
		String financialWindowID = getWindowIdByClassCustomized("x-window x-resizable-pinned");
		
		String financialWindowTitle = "Financial Summary For Work Order "+woID;
		
		softAssert.assertTrue(getWindowTitle("@id", financialWindowID).contains(financialWindowTitle), "Financial Summary window is displayed.");
		
		waitForExtJSAjaxComplete(2);
		
		String revenueColArr[] = {"Cost Category", "Total", "Billed", "Not Billed", "Billing Undecided", "Billing Yes", "Billing No"};
		
		softAssert.assertTrue(verifyFinancialSummaryColumns(financialWindowID, revenueHeader, revenueColArr), "All The columns are displayed under Revenue Header in Financial Summary Window");
		
		waitForExtJSAjaxComplete(3);
		
		String expensesColArr[] = {"Cost Category", "Total", "Purchased", "Stock", "Non-stock"};
		
		softAssert.assertTrue(verifyFinancialSummaryColumns(financialWindowID, expensesHeader, expensesColArr), "All The columns are displayed under Expenses Header in Financial Summary Window");
		
		waitForExtJSAjaxComplete(3);
		
		String balencesColArr[] = {};
		
		softAssert.assertTrue(verifyFinancialSummaryColumns(financialWindowID, balanceHeader, balencesColArr), "All The columns are displayed under Balence Header in Financial Summary Window");
		
		waitForExtJSAjaxComplete(3);
		
		String curEur = "Currency: EUR";
		String curUsd = "Currency: USD";
		String colArr[] = {curEur, curUsd};
		
		changeColumns(financialWindowTitle, expensesHeader, "0", "Columns");
		
		waitForExtJSAjaxComplete(3);
		
		String unCheckColumns[] = {"Currency", "Total", "Purchased", "Stock", "Non-stock"};
		
		unCheckColumns(unCheckColumns);
		
		waitForExtJSAjaxComplete(5);
		
		String costCatCheck[] = {"Cost Category"};
		
		checkColumns(costCatCheck);
		
		waitForExtJSAjaxComplete(5);
		
		click(EXPENSES_XPATH);
		
		softAssert.assertFalse(isColumnDisplayed(financialWindowTitle, "Expenses", "Cost Category"), "Cost Category is Displayed");
		softAssert.assertTrue(isColumnDisplayed(financialWindowTitle, "Expenses", "Currency"), "Currency is not displayed");
		softAssert.assertTrue(isColumnDisplayed(financialWindowTitle, "Expenses", "Total"), "Total is not displayed");
		softAssert.assertTrue(isColumnDisplayed(financialWindowTitle, "Expenses", "Purchased"), "Purchased is not displayed");
		softAssert.assertTrue(isColumnDisplayed(financialWindowTitle, "Expenses", "Stock"), "Stock is not displayed");
		softAssert.assertTrue(isColumnDisplayed(financialWindowTitle, "Expenses", "Non-stock"), "Non-stock is not displayed");
		
		waitForExtJSAjaxComplete(2);
		
		changeColumns(financialWindowTitle, expensesHeader, "0", "Columns");
		
		waitForExtJSAjaxComplete(3);
		
		String checkColumns[] = {"Cost Category", "Currency", "Total", "Purchased", "Stock", "Non-stock"};
		
		checkColumns(checkColumns);
		
		waitForExtJSAjaxComplete(5);
		
		//Closed Financial Window
		closeUsingToolBarJS(financialWindowTitle);
		
		waitForExtJSAjaxComplete(5);
		
		//Getting the window ID Of workorders
		windowID = getWindowIdByClass("x-window x-resizable-pinned");
		
		clickOnTab(windowID, "Time & Material");
		
		waitForExtJSAjaxComplete(5);
		
		clickOnTab(windowID, "Bill of Materials");
		
		waitForExtJSAjaxComplete(5);
		
		clickStockInfoButtonOnBOMTab(windowID, "Add");
		
		waitForExtJSAjaxComplete(3);
		
		setValueGridLookupWithFilters("@id", getXWindowIdWithoutVisibilityCheck("Select Products or Services"), productRef, "Reference");
		 
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(5);
		
		clickButton("@class", "x-panel-footer", "Save");
		
		waitForExtJSAjaxComplete(3);
		
		String status = getCostItemID("@class", "bom-grid x-grid-with-col-lines", "1", "Status");
		
		softAssert.assertTrue(status.contains("Needed"), "Product1 Reference with status is needed is available in Grid");
		
		waitForExtJSAjaxComplete(2);
		
		checkRowInGriByTextValueAndClickCustomized("@class", "bom-grid x-grid-with-col-lines", productRef);
		
		clickStockInfoButtonOnBOMTab(windowID, "Consume");
		
		waitForExtJSAjaxComplete(3);
		
		status = getCostItemID("@class", "bom-grid x-grid-with-col-lines", "1", "Status");
		
		softAssert.assertTrue(status.contains("Consumed"), "Product1 Reference status is Consumed");
		
		waitForExtJSAjaxComplete(2);
		
		//Show Financial Summary.
		clickCommandDropDown(windowID, "Show Financial Summary");
		
		waitForExtJSAjaxComplete(2);
		
		String value = "0.00";
		
		softAssert.assertEquals(verifyConsumedProductDetails(colArr, costCatColName, curEur, "x-grid3-row-last"), "Aqa_Cost_Cat_ref", "Cost Category is displayed for EUR Currency");
		softAssert.assertEquals(verifyConsumedProductDetails(colArr, totalColName, curEur, "x-grid3-row-last"), value, "Total is displayed for EUR Currency");
		softAssert.assertEquals(verifyConsumedProductDetails(colArr, purchasedColName, curEur, "x-grid3-row-last"), value, "Purchased is displayed for EUR Currency");
		softAssert.assertEquals(verifyConsumedProductDetails(colArr, stockColName, curEur, "x-grid3-row-last"), value, "Stock is displayed for EUR Currency");
		softAssert.assertEquals(verifyConsumedProductDetails(colArr, nonStockColName, curEur, "x-grid3-row-last"), value, "Non Stock is displayed for EUR Currency");
		
		softAssert.assertAll();
		
		Reporter.log("Show Financial Summary tab is successfully verified", true);
	}

	/**
	 * Test Case ID: C91917 & C91918
	 * Author : SSU
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testChecklistSubmission() throws Exception  {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Submitting the Checklist on a Web Portal. C91917" + " </span><br>", 
				true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Checklist Tab on Workorder Details in Web Portal. C91918" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testChecklistSubmission");
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		String woRef = "AQACheckListWO";
		String checkListTempName = "AQAChkListTemplateRef";
		String checklistItem1 = "item1";
		String comment = "Comments Entered";
		List<String> checklistReferences = Arrays.asList("AQAChkListTemplateRef");
		
		//Variables
		String parentFile = "test.csv";
		String parentFileDesc =  "C91917ParentFilDes";
		String fileRemark =  "C91917FilRem" +getCurrentDate().substring(getCurrentDate().length()-6);
		String type = "labelen";
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(25);
		
		filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", woRef, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		openTransactionLineModified("@class", "x-grid3-viewport", woRef, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		String windowID = getWindowIdByClass("x-window x-resizable-pinned");
		
		waitXPathVisible(CHECKLISTS_OBJECT_TAB_XPATH);
		
		//Workorder detail window is displayed
		softAssert.assertTrue(getWONameFromDetailsWindow(woRef), "WO Details window is opened");
		
		waitForExtJSAjaxComplete(2);
		
		clickOnTab(windowID, "Checklists");
		
		for(String checklistReference : checklistReferences){
			boolean status = isChecklistInGridPresent(checklistReference);

			if(status) {
				selectChecklistGrid(checklistReference);
				waitForExtJSAjaxComplete(10);

				clickDeleteChecklist();
				waitForExtJSAjaxComplete(15);

				clickOnDialogButton("Yes");
				waitForExtJSAjaxComplete(25);

				softAssert.assertTrue(!isChecklistInGridPresent(checklistReference)," Checklist is still present.");
				waitForExtJSAjaxComplete(20);
			}	
		}
		
		waitForExtJSAjaxComplete(15);
		
		clickAddChecklist();

		waitForExtJSAjaxComplete(15);

		selectChecklist(checkListTempName);

		waitForExtJSAjaxComplete(15);
		
		//clickOnDialogButton("OK");

		//waitForExtJSAjaxComplete(15);
		
		//waitForExtJSAjaxComplete(5);
		
		waitXPathVisible(CHECKLIST_XPATH_IN_GRID+"//div[contains(@class, 'x-grid3-cell-inner') and text()='"+checkListTempName+"']");
		
		//Checklists Tab is opened. and Grid with assigned checklists is displayed
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, checkListTempName, "@realid", "SurveyGrid"), "Checklist Tab is opened and Checklist Template is also diplayed");
		
		waitForExtJSAjaxComplete(2);
		
		selectChecklistGrid(checkListTempName);
		
		waitForExtJSAjaxComplete(5);
		
		clickViewChecklist();
		
		waitForExtJSAjaxComplete(25);
		
		//Checklist opened as child tab inside the MyMCS Web Portal
		softAssert.assertTrue(isCheckListOpenedAsNewTab("wotabpanel", checkListTempName), "Checklist tab is Opened as child tab inside the MyMCS Web Portal");
		
		waitForExtJSAjaxComplete(2);
		
		softAssert.assertTrue(enterComments(comment, "Comment"), "Comments are displayed under the checklist items and value is enetered");
		
		waitForExtJSAjaxComplete(2);
		
		// Adding Parent File
		clickAttachmentOptionsChecklistItem(checklistItem1, "Add File");
		
		waitForExtJSAjaxComplete(25);
		
		//File to be uploaded
		setFileForCheckListItems(parentFile, parentFileDesc, fileRemark, type);

		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(isRowInGridPresent(checklistItem1, "1", parentFile), "Parent File 1 "+parentFile+"  is added to the Grid");

		waitForExtJSAjaxComplete(15);
		
		checkWOChecklistItemsInGrid(checklistItem1);
		
		waitForExtJSAjaxComplete(15);
		
		clickSubmitButton();
		
		waitForExtJSAjaxComplete(2);
		
		softAssert.assertTrue(verifyWarningDialogTextMessage("Your checklist was received. Thank you for your cooperation!"), "Success message is displayed.");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(5);
		
		closeModule("Checklists - "+checkListTempName);
		
		waitForExtJSAjaxComplete(2);
		
		clearOverviewFilters();
		
		waitForExtJSAjaxComplete(2);
		
		filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", woRef, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		openTransactionLineModified("@class", "x-grid3-viewport", woRef, "Reference");
		
		waitForExtJSAjaxComplete(25);
		
		windowID = getWindowIdByClass("x-window x-resizable-pinned");
	
		clickOnTab(windowID, "Checklists");
		
		waitForExtJSAjaxComplete(5);
		
		//Status of submitted checklist is changed to 'Filled Out'
		softAssert.assertTrue(getFilledOutStatusOfChecklistTemplate("@id", windowID, "Filled Out", "x-grid3-row", checkListTempName).contains("check-col-on"), "Status of submitted checklist is changed to Filled Out");
		
		selectChecklistGrid(checkListTempName);
		
		waitForExtJSAjaxComplete(2);
		
		clickViewChecklist();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(isCheckListOpenedAsNewTab("wotabpanel", checkListTempName), "Checklist tab is Opened");
		
		softAssert.assertTrue(isRowInGridPresent(checklistItem1, "1", parentFile), "Parent File 1 "+parentFile+"  is available in the Grid");
		
		softAssert.assertEquals(getCommentsFromCheckList(checklistItem1, "1"), comment, "Comments remain in the submitted checklist");
		
		softAssert.assertAll();
		
		Reporter.log("Submitting the Checklist on a Web Portal is successfully verified", true);
	}

	/**
	 * Test Case ID : C112461
	 * Author : MMA
	 * @throws Exception
	 */
	@Test(enabled=true)
	public void testCreateWOFromNewToClosed()throws Exception{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create a Workorder from new to closed: C112461 </span><br>", true);

		String workOrder1 = "WebWO1Ref";
		String WO1actionStatus = "aqa_closed";
		List<String> workOrders = Arrays.asList("WinWO2","WinTempRefWO3");


		String possibleAction1 = "Action1";
		String possibleAction2 = "Action2";
		String possibleAction3 = "Action3";

		String initialStatus = "aqa_new";
		String action1Status = "aqa_open";
		String action2Status = "aqa_processed";
		String action3Status = "aqa_closed";

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testCreateWOFromNewToClosed");

		expandAdministration();
		waitForExtJSAjaxComplete(5);

		click(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();
		waitForExtJSAjaxComplete(5);

		//WorkOrder1 verification
		filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", workOrder1, "Reference");

		Grid.checkRowInGriByTextValueAndClick(driver, workOrder1);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		softAssert.assertEquals(getToolBarStatuses("column-status-finished"), WO1actionStatus, "First workorder is closed and displayed with Finished status");
		waitForExtJSAjaxComplete(5);
		
		//WorkOrder2 and WorkOrder3 processing from new to closed 
		for(String workOrder : workOrders){

			closeXWindow();
			waitForExtJSAjaxComplete(10);

			filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", workOrder, "Reference");

			Grid.checkRowInGriByTextValueAndClick(driver, workOrder);
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);

			softAssert.assertEquals(getToolBarStatuses("column-status-preparation"), initialStatus, initialStatus+" is the  initial status of the WO "+workOrder);

			clickTrackingTab(WORKORDER_WINDOW_DETAIL);
			waitForExtJSAjaxComplete(10);
			clickTrackingHistoryTab();
			waitForExtJSAjaxComplete(15);

			clickAddAction();
			waitForExtJSAjaxComplete(20);

			clickPossibleActions(possibleAction1);
			waitForExtJSAjaxComplete(10);

			saveCloseAction(ADD_ACTION_WINDOW_HEADER);
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);

			softAssert.assertEquals(getToolBarStatuses("column-status-progress"), action1Status, action1Status+" is the status of the WO "+workOrder);
			waitForExtJSAjaxComplete(5);

			clickAddAction();
			waitForExtJSAjaxComplete(20);

			clickPossibleActions(possibleAction2);
			waitForExtJSAjaxComplete(10);

			saveCloseAction(ADD_ACTION_WINDOW_HEADER);
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);

			softAssert.assertEquals(getToolBarStatuses("column-status-progress"), action2Status, action2Status+" is the status of the WO "+workOrder);
			waitForExtJSAjaxComplete(5);

			clickAddAction();
			waitForExtJSAjaxComplete(20);

			clickPossibleActions(possibleAction3);
			waitForExtJSAjaxComplete(10);

			saveCloseAction(ADD_ACTION_WINDOW_HEADER);
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);

			softAssert.assertEquals(getToolBarStatuses("column-status-finished"), action3Status, action3Status+" is the status of the WO "+workOrder);
		}

		softAssert.assertAll();
		Reporter.log("Create a Workorder from new to closed successfully",true);
	}

	/**
	 * Test Case ID: C120662
	 * Author : MMA
	 */
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testCreateSubWO( ) throws Exception{

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C120662: Verify create a Sub work order </span><br>", true );
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testCreateSubWO");
		String woRef = "AQAWoType";
		String childWOTemp = "Aqa_child_WO_Temp";
		String severity = "1preSeverityRef";
		String priority = "1prePriorityRef";
		String reference = "ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String nature = "Default Nature";


		waitForExtJSAjaxComplete(25);

		//Navigate to Administration and get the WO type whose Related object type is Workorder
		clickAdministration();

		waitForExtJSAjaxComplete(5);

		expandAdministrationOptions("Module Settings");

		waitForExtJSAjaxComplete(5);

		clickAdministrationOptions("Workorders", "Work Order Templates");

		waitForExtJSAjaxComplete(5);

		expandWOTemplateGroup("AqaTemplateGroup");

		waitForExtJSAjaxComplete(2);

		selectWOTemplate(childWOTemp);

		waitForExtJSAjaxComplete(15);

		softAssert.assertTrue(getRelatedObjectType().contains("Workorder"), childWOTemp+" related object type is workorder");

		waitForExtJSAjaxComplete(3);

		closeModule("Administration");

		waitForExtJSAjaxComplete(3);

		waitAndClick(XPATH_WORKORDERS);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();

		waitForExtJSAjaxComplete(5);

		filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", woRef, "Reference");

		waitForExtJSAjaxComplete(25);

		openTransactionLineModified("@class", "x-grid3-viewport", woRef, "Reference");
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(getWONameFromDetailsWindow(woRef), "WO Details window is opened");
		clickGeneralTab();
		waitForExtJSAjaxComplete(3);

		clickButton("@class","hdwo-details","Add Workorder");
		waitForExtJSAjaxComplete(3);

		expandTemplateGroup();
		waitForExtJSAjaxComplete(3);

		softAssert.assertTrue(isChildWOTemplateWindowOpened(childWOTemp), "select template panel where templates are listed out whose related type is Work Order");
		selectTemplate(childWOTemp);
		waitForExtJSAjaxComplete(20);

		setReference(reference);
		waitForExtJSAjaxComplete(5);

		setPriority(priority);
		waitForExtJSAjaxComplete(5);

		setSeverity(severity);
		waitForExtJSAjaxComplete(5);

		setNature(nature);
		waitForExtJSAjaxComplete(5);

		clickSaveOrder();
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", "hdwo-details", "div", "text()", "Work Order", "text()", reference,true, true).getText().matches(".*\\d+.*"),"Work order is created!");
		softAssert.assertTrue(isChildWOCreatedInHierarchy(woRef, reference), "Child work order gets created. in Hierarchy Window");
		waitForExtJSAjaxComplete(3);

		closeXWindow();
		waitForExtJSAjaxComplete(5);

		softAssert.assertAll();
		Reporter.log("creation of Sub work order is successfully verified", true);
	}

	/**
	 * Test Case ID: C113804
	 * Author : MMA
	 */
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testCriticalityAndAccessInstructions( ) throws Exception{

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C113804: Criticality and Access Instruction</span><br>", true );

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testCriticalityAndAccessInstructions");

		String woRef = "AQAWoGenerationViaMaintPlanTemp";
		String moWithAccessInstructions = "MMA_MObjRef";
		String moWithOutAccessInstructions = "1preMnObRef";
		String criticality = "Criticality1";

		waitForExtJSAjaxComplete(25);

		waitAndClick(XPATH_WORKORDERS);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();
		waitForExtJSAjaxComplete(5);

		filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", woRef, "Reference");

		waitForExtJSAjaxComplete(25);

		openTransactionLineModified("@class", "x-grid3-viewport", woRef, "Reference");

		waitForExtJSAjaxComplete(25);

		String windowID = getWindowIdByClass("x-window x-resizable-pinned");

		clickOnTab(windowID, "Objects");
		waitForExtJSAjaxComplete(5);

		//select the Maintenance Object 1
		clickAddMaintanceObject();

		waitForExtJSAjaxComplete(3);

		setMaintenanceObject(moWithAccessInstructions);

		waitForExtJSAjaxComplete(3);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, moWithAccessInstructions, "@id", windowID), "Maintenance Object 1 is added to the list");

		softAssert.assertTrue(getCriticality(moWithAccessInstructions).contains(criticality), "Maintenance Object's 1 Criticality is displayed");

		selectMObjectOrMOPart(moWithAccessInstructions,"Maintenance Object");

		waitForExtJSAjaxComplete(2);

		softAssert.assertTrue(isButtonEnabled("Details"),"Details button is enabled");

		softAssert.assertTrue(isButtonEnabled("Access Instructions"),"Access Instructions button is enabled");

		clickButton("Details", windowID);

		waitForExtJSAjaxComplete(15);

		softAssert.assertEquals(driver.findElement(By.xpath(MAINTENANCE_OBJECTS_WINDOW_XPATH)).getText(), "Maintenance Object Details", "Maintenance Object Details windows is opened");

		softAssert.assertEquals(getMODetailsValues("Criticality", "textContent"), criticality, "Criticality is visible in corresponding field.");

		//Read-only Maintenance Object Details
		softAssert.assertTrue(getMODetailsValues("Criticality", "tagName").contains("DIV"), "Criticality field is not editable");

		softAssert.assertTrue(getMODetailsValues( "Reference", "tagName").contains("DIV"), "Reference field is not editable");

		softAssert.assertTrue(getMODetailsValues("Code", "tagName").contains("DIV"), "Code field is not editable");

		softAssert.assertTrue(getMODetailsValues( "Class", "tagName").contains("DIV"), "Class field is not editable");

		softAssert.assertTrue(getMODetailsValues("Status", "tagName").contains("DIV"), "Status field is not editable");

		waitForExtJSAjaxComplete(2);

		closeXWindow("@id", getXWindowId(MAINTANCE_OBJECT_WINDOW_TITLE));

		waitForExtJSAjaxComplete(2);

		clickButton("Access Instructions", windowID);

		waitForExtJSAjaxComplete(15);

		softAssert.assertEquals(driver.findElement(By.xpath(MAINTENANCE_OBJECTS_WINDOW_XPATH)).getText(), "Access Instructions", "Access Instructions windows is opened");

		softAssert.assertEquals(getAccessInstructionsWindowText("value"),"Maintenance object is regarding Lift maintenance");

		softAssert.assertTrue(getAccessInstructionsWindowText("class").contains("form-readonly"),"Read-only Access Instructions window is opened");

		waitForExtJSAjaxComplete(2);

		closeXWindow("@id", getXWindowId(ACCESS_INS_WINDOW_TITLE));

		waitForExtJSAjaxComplete(2);

		//select the Maintenance Object 2
		clickAddMaintanceObject();

		waitForExtJSAjaxComplete(3);

		setMaintenanceObject(moWithOutAccessInstructions);

		waitForExtJSAjaxComplete(3);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, moWithOutAccessInstructions, "@id", windowID), "Maintenance Object 2 is added to the list");

		softAssert.assertTrue(getCriticality(moWithOutAccessInstructions).contains(""), "Maintenance Object's 2 Criticality is Empty");

		selectMObjectOrMOPart(moWithOutAccessInstructions,"Maintenance Object");

		waitForExtJSAjaxComplete(2);

		softAssert.assertTrue(isButtonEnabled("Details"),"Details button is enabled");

		softAssert.assertFalse(isButtonEnabled("Access Instructions"),"Access Instructions button is not displayed");

		clickButton("Details", windowID);

		waitForExtJSAjaxComplete(15);

		softAssert.assertEquals(driver.findElement(By.xpath(MAINTENANCE_OBJECTS_WINDOW_XPATH)).getText(), "Maintenance Object Details", "Maintenance Object Details windows is opened");

		softAssert.assertTrue(getMODetailsValues("Criticality", "textContent").contains(""), "Maintenance Object 2 Criticality field is blank.");

		//Read-only Maintenance Object Details
		softAssert.assertTrue(getMODetailsValues("Location", "tagName").contains("DIV"), "Criticality field is not editable");

		softAssert.assertTrue(getMODetailsValues( "Reference", "tagName").contains("DIV"), "Reference field is not editable");

		softAssert.assertTrue(getMODetailsValues("Code", "tagName").contains("DIV"), "Code field is not editable");

		softAssert.assertTrue(getMODetailsValues("Class", "tagName").contains("DIV"), "Class field is not editable");

		softAssert.assertTrue(getMODetailsValues("Status", "tagName").contains("DIV"), "Status field is not editable");

		softAssert.assertAll();

		Reporter.log("Criticality and Access Instruction is successfully verified in WO", true);
	} 

	/**
	 * Test Case ID: C119148, C118829
	 * Author : kve
	 */
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testLocationLookup( ) throws Exception{

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C119148:  SO: It is possible to link only the Sites of status classes 'PLANNED' and 'ACTIVE' to Service Groups, Service organizations  </span><br>"
				+ "Test: C118829: SC: only locations with ACTIVE status are shown in lookup", true );

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testLocationLookup");

		String activesiteName = "slnmEnrgSite2";
		//String srvOrg = "SO1Ref";
		String archivedSiteName = "aqaarchived";
		String plannedSiteName = "aqaPreInactiveSite";
		String cancelSiteName = "aqacanceled";

		String textMsg= "The Sites have been successfully linked to the selected Service Organization.";

		String textMsg1= "The Site has been successfully unlinked from the selected Service Organization.";


		ServiceOrganizationsPageObject obj = new ServiceOrganizationsPageObject();

		//C119148:  SO: It is possible to link only the Sites of status classes 'PLANNED' and 'ACTIVE' to Service Groups, Service organizations
		String serviceOrganizationsReference = "my Aqa Auto"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String serviceOrganizationsDescription = "service Organizations Description";
		
		clickAdministration();
		
		expandMasterData();
		
		obj.clickServiceOrganizations();
		
		obj.clickAddServiceOrganizations();
		
		waitForExtJSAjaxComplete(20);
		
		obj.setReference(serviceOrganizationsReference);
		
		waitForExtJSAjaxComplete(20);
		
		obj.setCode(serviceOrganizationsReference);
		
		waitForExtJSAjaxComplete(20);
		
		obj.setDescription(serviceOrganizationsDescription);
		
		waitForExtJSAjaxComplete(20);
		
		obj.saveServiceOrganizations();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(obj.itemIsPresentInServiceOrganizationList(serviceOrganizationsReference), "New items is present in list");
		
		obj.selectServiceOrganizationItem(serviceOrganizationsReference);
		
		waitForExtJSAjaxComplete(20);
				
		//clickAdministration();

		//waitForExtJSAjaxComplete(20);

		//expandMasterData();

		//waitForExtJSAjaxComplete(20);

		//obj.clickServiceOrganizations();

		//obj.selectServiceOrganizationItem(srvOrg);

		obj.clickLinkSite();

		waitForExtJSAjaxComplete(20);

		obj.selectLocations(activesiteName);

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getTextMsgFrmSrvOrg(serviceOrganizationsReference),textMsg,"The Sites have been successfully linked to the selected Service Organization.");

		obj.selectServiceOrganizationItem(activesiteName);

		obj.clickUnLinkSite();

		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getTextMsgFrmSrvOrg(serviceOrganizationsReference),textMsg1,"The Site has been successfully unlinked from the selected Service Organization.");
		
		obj.selectServiceOrganizationItem(serviceOrganizationsReference);
		
		obj.clickLinkSite();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, activesiteName, "@class", "x6-tree-arrows"),"Locations with 'ACTIVE' status is present in the list.");

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, plannedSiteName, "@class", "x6-tree-arrows"),"Locations with 'PLANNED' status is present in the list.");

		softAssert.assertFalse(Grid.isRowInGridPresent(driver, archivedSiteName, "@class", "x6-tree-arrows"),"Locations with other than 'PLANNED' and 'ACTIVE' statuses are not present in the list.");

		softAssert.assertFalse(Grid.isRowInGridPresent(driver, cancelSiteName, "@class", "x6-tree-arrows"),"Locations with other than 'PLANNED' and 'ACTIVE' statuses are not present in the list.");

		waitForExtJSAjaxComplete(20);

		obj.clickCANCELXwindowSO();

		//C118829: SC: only locations with ACTIVE status are shown in lookup
		waitForExtJSAjaxComplete(20);

		expandAdministration();
		waitForExtJSAjaxComplete(5);

		click(XPATH_SERVICE_CENTER);
		waitForExtJSAjaxComplete(25);

		clickLookupNewUI("servicecenter", "location");

		softAssert.assertTrue(isRowInLocationGridPresent(driver, activesiteName),"Only locations with 'ACTIVE' status are shown in lookup");

		//clickLookupNewUI("servicecenter", "location");

		softAssert.assertFalse(Grid.isRowInGridPresent(driver, plannedSiteName, "@class", "x6-tree-arrows"),"Locations with 'PLANNED' status is not present in the lookup.");

		softAssert.assertFalse(Grid.isRowInGridPresent(driver, archivedSiteName, "@class", "x6-tree-arrows"),"Locations with 'ARCHIVED' status is not present in the lookup.");

		softAssert.assertFalse(Grid.isRowInGridPresent(driver, cancelSiteName, "@class", "x6-tree-arrows"),"Locations with 'CANCELED' status is not present in the lookup.");

		softAssert.assertAll();

		Reporter.log("Succesfully verified the Location Lookup Grid having Active and Planned status", true);

	}

	/**
	 * Testcase Id : C114433,C115702
	 * Author      : MMA
	 * @throws Exception
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testDocumentAttachmentToChecklistItems() throws Exception{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"Test C114433: Documents with allowed file extensions only, are added to the checklist items. (MYM-24556) </span><br>"
				+"Test C115702: If show on portal check box is unchecked, then template is not shown in new workorder form.</span><br> ", true);

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testDocumentAttachmentToChecklistItems");

		String tempReference = "TempRef"+getCurrentDate().substring(getCurrentDate().length()-4);
		String woType = "Aqa_WO_Type_Name";
		String relatedObjType = "None";
		String testWorkOrder = "1preWrkRef";
		String file = "testFile.txt";
		String fileDescription =  "file descr" + getCurrentDate().substring(getCurrentDate().length()-6);
		String fileRemark =  "file remark" + getCurrentDate().substring(getCurrentDate().length()-6);
		String type = "labelen";
		String checklistReference = "ConditionalRef";
		String question = "Parent";
		String username = configuration.getUserName();
		String password = "qwerty";

		try{
			//Uncheck show on portal check box
			clickAdministration();
			waitForExtJSAjaxComplete(5);

			expandAdministrationOptions("Module Settings");
			waitForExtJSAjaxComplete(10);

			clickAdministrationOptions("Workorders", "Work Order Templates");
			waitForExtJSAjaxComplete(10);

			clickButtonWOTemplateAdministration("icon-template-add");
			waitForExtJSAjaxComplete(25);
			waitForMaskDisappear(); 

			setReferenceWOTemplate(tempReference);
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(15);

			setWOTypeWOTemplate(woType);
			waitForExtJSAjaxComplete(15);

			selectRelatedObjectType(relatedObjType);
			waitForExtJSAjaxComplete(15);

			InspectionAdministrationPageObject inspAdminObj = new InspectionAdministrationPageObject();
			inspAdminObj.uncheckFieldInInspectionTemplateWindow("Show on Portal");

			clickButtonWOTemplateDetailsAdministration("icon-save", "Save Changes");
			waitForExtJSAjaxComplete(15);

			closeModule("Administration");
			waitForExtJSAjaxComplete(15);


			//Remove '.txt' file type from 'File types white-listing' if present
			clickAdministration();
			waitForExtJSAjaxComplete(5);

			expandAdministrationOptions("Portal Settings");
			waitForExtJSAjaxComplete(10);

			clickAdministrationOptions("Portal Settings", "Technical");
			waitForExtJSAjaxComplete(10);

			clickOnButtonInTechnicalSection("File types white-listing:","Reset to Default","x-box-layout");
			waitForExtJSAjaxComplete(25);

			String fieldValue = getFieldValueInTechnicalSection("files_types_whitelist");
			waitForExtJSAjaxComplete(25);

			fieldValue = (fieldValue.contains(",txt,")?fieldValue.replace(",txt,",","):(fieldValue.contains(",txt")?fieldValue.replace(",txt","").trim():fieldValue));
			waitForExtJSAjaxComplete(25);

			clickSaveOrCancleButtonInTechnicalSection("Save");
			waitForExtJSAjaxComplete(25);

			closeModule("Administration");
			waitForExtJSAjaxComplete(15);

			navigateToMainPage(username, password);
			waitForExtJSAjaxComplete(25);

			//Trying to Add the file whose file extension is not in the list
			waitAndClick(XPATH_WORKORDERS);
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);

			setDetailsTabCollapsedMode();
			waitForExtJSAjaxComplete(20);

			filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", testWorkOrder, "Reference");
			waitForExtJSAjaxComplete(25);

			checkRowInGriByTextValueAndClick(driver, testWorkOrder);
			waitForExtJSAjaxComplete(25);

			clickChecklistsObjectTab();
			waitForExtJSAjaxComplete(20);

			boolean status = isChecklistInGridPresent(checklistReference);

			if(status) {
				selectChecklistGrid(checklistReference);
				waitForExtJSAjaxComplete(10);

				clickDeleteChecklist();
				waitForExtJSAjaxComplete(15);

				clickOnDialogButton("Yes");
				waitForExtJSAjaxComplete(25);

				softAssert.assertTrue(!isChecklistInGridPresent(checklistReference)," Checklist is still present.");
				waitForExtJSAjaxComplete(20);
			}	

			clickAddChecklist();
			waitForExtJSAjaxComplete(25);

			selectChecklist(checklistReference);
			waitForExtJSAjaxComplete(10);

			selectChecklistGrid(checklistReference);
			waitForExtJSAjaxComplete(10);

			clickViewChecklist();
			waitUntilCheckListIsLoaded();
			waitForExtJSAjaxComplete(25);

			softAssert.assertTrue(isUIElementPresentInCheckList(question,"addfile.png"),"Panel with UI elements for adding a file is displayed");
			waitForExtJSAjaxComplete(15);

			clickAttachmentOptionsChecklistItem(question, "Add File");
			waitForExtJSAjaxComplete(25);

			setFileForCheckListItems(file, fileDescription, fileRemark, type);
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);

			String allowedFileType = getToolTipTextOfAddDocumentWindow();
			softAssert.assertTrue(allowedFileType.contains("File should be in") && allowedFileType.contains("format"),"Appropriate error message is displayed, "+file+" file is not added to the Grid");

			clickCancelButtonInAddDocWindow();
			waitForExtJSAjaxComplete(25);

			closeModule("Work Order");
			waitForExtJSAjaxComplete(15);


			//Verifying unchecked template, If show on portal check box is unchecked
			waitAndClick(XPATH_WORKORDERS);
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);

			clickAddButton();
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);

			InspectionPageObject insPage = new InspectionPageObject();

			insPage.expandAllTemplatedGroups();
			waitForExtJSAjaxComplete(25);

			List<String> tempList = getAllAvailableTemplates();
			softAssert.assertFalse(tempList.contains(tempReference),"Template is not display in the list, because 'Show on Portal' check box is unchecked");

			closeXWindow();
			waitForExtJSAjaxComplete(5);

			closeModule("Work Order");
			waitForExtJSAjaxComplete(15);


			//Add '.txt' file type to 'File types white-listing'
			clickAdministration();
			waitForExtJSAjaxComplete(5);

			expandAdministrationOptions("Portal Settings");
			waitForExtJSAjaxComplete(10);

			clickAdministrationOptions("Portal Settings", "Technical");
			waitForExtJSAjaxComplete(10);

			setFieldValueInTechnicalSection("files_types_whitelist","txt");
			waitForExtJSAjaxComplete(25);

			clickSaveOrCancleButtonInTechnicalSection("Save");
			waitForExtJSAjaxComplete(25);

			closeModule("Administration");
			waitForExtJSAjaxComplete(15);

			navigateToMainPage(username, password);
			waitForExtJSAjaxComplete(25);


			//Trying to Add the file whose file extension is in the list
			waitAndClick(XPATH_WORKORDERS);
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);

			setDetailsTabCollapsedMode();
			waitForExtJSAjaxComplete(20);

			filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", testWorkOrder, "Reference");
			waitForExtJSAjaxComplete(25);

			checkRowInGriByTextValueAndClick(driver, testWorkOrder);
			waitForExtJSAjaxComplete(25);

			clickChecklistsObjectTab();
			waitForExtJSAjaxComplete(20);

			selectChecklistGrid(checklistReference);
			waitForExtJSAjaxComplete(10);

			clickViewChecklist();
			waitUntilCheckListIsLoaded();
			waitForExtJSAjaxComplete(25);

			clickAttachmentOptionsChecklistItem(question, "Add File");
			waitForExtJSAjaxComplete(25);

			setFileForCheckListItems(file, fileDescription, fileRemark, type);
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);

			softAssert.assertTrue(isRowInGridPresent(question, "1",file), "childFile "+file+"  is added to the Grid");
			waitForExtJSAjaxComplete(5);	

			closeModule("Work Order");
			waitForExtJSAjaxComplete(15);
		}

		finally{
			Reporter.log("Clearing the 'File types white-listing' values",true);
			driver.navigate().refresh();
			waitForMaskDisappear();
			waitForExtJSAjaxComplete(25);

			clickAdministration();
			waitForExtJSAjaxComplete(15);
			
			expandAdministrationOptions("Portal Settings");
			waitForExtJSAjaxComplete(10);

			clickAdministrationOptions("Portal Settings", "Technical");
			waitForExtJSAjaxComplete(10);

			setFieldValueInTechnicalSection("files_types_whitelist","");
			waitForExtJSAjaxComplete(25);

			clickSaveOrCancleButtonInTechnicalSection("Save");
			waitForExtJSAjaxComplete(25);

			softAssert.assertAll();
			Reporter.log("Documents with allowed file extensions only, are added to the checklist items.",true);
		}
	}
}
