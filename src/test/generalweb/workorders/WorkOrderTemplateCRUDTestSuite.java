package test.generalweb.workorders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.DropDown;
import test.generalweb.calltemplates.CallTemplatesPageObject;
import test.generalweb.helpdesk.HelpDeskFrontOfficePageObject;
import test.generalweb.inspections.InspectionAdministrationPageObject;
import test.generalweb.inspections.InspectionPageObject;
import test.generalweb.inspections.InspectionTemplatesCRUDTestSuite;
import test.generalweb.rightvisibility.AdministrationPageObject;
import test.generalweb.scheduler.SchedulerPageObject;

public class WorkOrderTemplateCRUDTestSuite extends WorkOrderPageObject 
 {
	/**
	 * Testcase ID :C92070,C92071,C92072,C92073,C92074,C92075,C92076,C92077,C92078
	 * Author : ssa
	 * @throws IOException
	 */
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class)
	public void testVerifyRelatedObjectTypeList() throws IOException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C92071:Change the workorder type for already created template"
				+"C92072:Related object type drop-down is available in workorder templates pane"
				+"C92073:None is the default value of the related object type drop-down"
				+"C92070/C119366:Select a workorder type in the template"
				+"C92074:Create a workorder template with related object as none"
				+"C92075:Create a workorder template with related object as Call"
				+"C92076:Create a workorder template with related object as Workorder"
				+"C92077:Create a workorder template with related object as project"
				+"C92078:Create a workorder template with related object as maintenance object",true);
			

		String reference = "WO"+ getCurrentDate().substring(getCurrentDate().length() - 6);
		
		AdministrationPageObject objad = new AdministrationPageObject();

		
		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testVerifyRelatedObjectTypeList");
	
		clickAdministration();
	
		waitForExtJSAjaxComplete(20);
	
		objad.expandModuleSettings();

		waitForExtJSAjaxComplete(20);
	
		objad.expandWorkorders();
	
		waitForExtJSAjaxComplete(20);
	
		objad.clickWorkorderTemplates();
	
		waitForExtJSAjaxComplete(20);
		
		//C92019
		softAssert.assertTrue(isDisabledDeleteButton(DELETE_BUTTON_CLASS),"Delete Workorder template button is disabled, if not selected any workorder temp");
	
		clickWOTemplateCreateButton();
		
		waitForExtJSAjaxComplete(20);
		
		//C92070
		List<String> expectedWOTypeList=Arrays.asList("1preWrkTpNm","Aqa_WO_Type_Name");
		
		List<String> actualWOTypeList=getWOTypeLookUpValues();
		
		softAssert.assertTrue(actualWOTypeList.containsAll(expectedWOTypeList),"List of values for workorder types is displayed");

		clickCANCELInLookupWindow();
		
		waitForExtJSAjaxComplete(10);
		
		//C92073
		String text=getRelatedObjectType();
		
		softAssert.assertTrue(text.contains("None"),"none is the default value of the related object type");
		
		//C92072
		List<String> expectedROTypeList=Arrays.asList("None","Call","Workorder","Project","Maintenance Object");
		
		List<String> actualROTypeList=getDropDownValuesForRelativeObjectType();
		
		softAssert.assertTrue(actualROTypeList.containsAll(expectedROTypeList),"List of values for relative types is displayed");
		
		//C92074,C92075,C92076,C92077,C92078
		for(String objectType:expectedROTypeList)
		{
		clickWOTemplateCreateButton();
		
		waitForExtJSAjaxComplete(10);
			
		setReferenceWOTemplate(reference);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(10);
		
		setWorkOrderTypeInWOTemplate("1preWrkTpNm");
		
		waitForExtJSAjaxComplete(20);
		
		selectRelatedObjectType(objectType);
		
		waitForExtJSAjaxComplete(10);
		
		clickSaveChangesButton();
		
		waitForExtJSAjaxComplete(10);
		
		boolean status=isWOTemplateCreated(reference);
		
		softAssert.assertTrue(status,"Workorder template is created");
		
		selectWOTemplate(reference);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(getRelatedObjectType().contains(objectType),"Workorder template is created with related object "+objectType+"");
		
		//C92071	
		setWorkOrderTypeInWOTemplate("Aqa_WO_Type_Name");		
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(getWorkOrderType().contains("Aqa_WO_Type_Name"),"Changed the workorder type for already created template.");
		
		//C92135
		clickWOTemplateDeleteButton();
		
		waitForExtJSAjaxComplete(10);
		
		clickOnDialogButton("No");
		
		status=isWOTemplateCreated(reference);
		
		softAssert.assertTrue(status,"Workorder template is not deleted");
		
		clickWOTemplateDeleteButton();
		
		waitForExtJSAjaxComplete(10);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		status=isWOTemplateCreated(reference);
		
		softAssert.assertFalse(status,"Workorder template is deleted with related object "+objectType+"");
		}
		
		softAssert.assertAll();
		
		Reporter.log(" Workorder template is created with related object none,call,workorder,Project,Maintenance Object<br>", true);
		}
	
	/**
	 * Testcase ID :C92147
	 *  Author : ssa
	 * @throws IOException
	 */
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class)
	public void testRestrictedWOTempInTempGroup() throws IOException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "

				+ "C92147:Restricted workorder templates in a template groups are not visible for the accounts that are not added in a group.",
				true);

		String reference = "WO" + getCurrentDate().substring(getCurrentDate().length() - 6);

		AdministrationPageObject objad = new AdministrationPageObject();

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testRestrictedWOTempInTempGroup");

		clickAdministration();

		waitForExtJSAjaxComplete(10);

		objad.expandModuleSettings();

		waitForExtJSAjaxComplete(10);

		objad.expandWorkorders();

		waitForExtJSAjaxComplete(20);

		objad.clickWorkorderTemplates();

		waitForExtJSAjaxComplete(10);

		this.selectWOTemplate("AqaTemplateGroup");

		waitForExtJSAjaxComplete(10);

		clickWOTemplateCreateButton();

		waitForExtJSAjaxComplete(10);

		setReferenceWOTemplate(reference);

		waitForMaskDisappear();

		setWorkOrderTypeInWOTemplate("1preWrkTpNm");

		waitForExtJSAjaxComplete(20);

		selectRelatedObjectType("None");

		waitForExtJSAjaxComplete(10);

		clickSecurityTab();

		waitForExtJSAjaxComplete(10);

		clickAddGroupButton("auto group");

		waitForExtJSAjaxComplete(10);

		clickSaveChangesButton();

		waitForExtJSAjaxComplete(10);

		this.clickGeneralTab();

		String tempName = getReferenceFromWOTemplate();

		waitForExtJSAjaxComplete(10);

		driver.navigate().refresh();

		waitForExtJSAjaxComplete(10);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		clickXPath(XPATH_WORKORDERS);

		waitForExtJSAjaxComplete(20);

		setDetailsTabCollapsedMode();

		waitForExtJSAjaxComplete(20);

		clickAddButton();

		waitForExtJSAjaxComplete(20);

		InspectionPageObject insPage = new InspectionPageObject();

		insPage.expandAllTemplatedGroups();

		List<String> tempList = getAllAvailableTemplates();

		softAssert.assertFalse(tempList.contains(tempName),"Template is not display in the list, because account is not in the restricted group.");

		softAssert.assertAll();

		Reporter.log("Restricted workorder templates in a template groups are not visible for the accounts that are not added in a group<br>",
				true);

	}

	/**
	 * Testcase ID : C116978
	 * Author : kve
	 * @throws IOException
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testMultipleAccsAndGrps() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C116978:  Multiple accounts and groups can be added to the restricted list </span><br>", true);

		String workOrderTemplateGroup = "AqaTemplateGroup";

		String workOrderTemplate = "Aqa_WO_Type";

		String accId1 = "MYADMIN";

		String accId2 = "FMADMIN";

		String accGroup1 = "auto group";

		String accGroup2 = "right web group";


		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testMultipleAccsAndGrps");

		CallTemplatesPageObject obj = new CallTemplatesPageObject();

		clickAdministration();

		waitForExtJSAjaxComplete(5);

		expandAdministrationOptions("Module Settings");

		waitForExtJSAjaxComplete(5);

		clickAdministrationOptions("Workorders", "Work Order Templates");

		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(verifyWOTemplatePane(), "Opens Templates pane to the right of administration menu.");

		waitForExtJSAjaxComplete(5);

		expandWOTemplateGroup(workOrderTemplateGroup);

		selectWOTemplate(workOrderTemplate);

		waitForExtJSAjaxComplete(15);

		clickTab("Security");

		obj.setAccount(accId1);

		obj.setAccount(accId2);

		clickSaveChangesButton();

		List<String> expectedAccs = Arrays.asList(accId1, accId2);

		List<String> actualAccs = getAccountGroupsList();

		softAssert.assertTrue(actualAccs.containsAll(expectedAccs),"Multiple accounts are added.");

		selectAccountName(accId1);

		waitForExtJSAjaxComplete(5);

		obj.clickOnRemove();

		waitForExtJSAjaxComplete(5);

		clickSaveChangesButton();

		waitForExtJSAjaxComplete(10);

		selectAccountName(accId2);

		waitForExtJSAjaxComplete(10);

		obj.clickOnRemove();

		waitForExtJSAjaxComplete(5);

		clickSaveChangesButton();

		List<String> actualAccsde = getAccountGroupsList();

		softAssert.assertFalse(actualAccsde.containsAll(expectedAccs),"Multiple accounts are Deleted");

		waitForExtJSAjaxComplete(5);

		clickAddGroupButton(accGroup1);

		waitForExtJSAjaxComplete(5);

		clickAddGroupButton(accGroup2);

		clickSaveChangesButton();

		waitForExtJSAjaxComplete(5);

		List<String> expectedAccGroups = Arrays.asList(accGroup1, accGroup2);

		List<String> actualAccGroups = getAccountGroupsList();

		softAssert.assertTrue(actualAccGroups.containsAll(expectedAccGroups),"Multiple groups are added.");

		selectAccGroup(accGroup1);

		waitForExtJSAjaxComplete(5);

		obj.clickOnRemove();

		waitForExtJSAjaxComplete(5);

		selectAccGroup(accGroup2);

		waitForExtJSAjaxComplete(5);

		obj.clickOnRemove();

		waitForExtJSAjaxComplete(5);

		clickSaveChangesButton();

		waitForExtJSAjaxComplete(5);

		List<String> actualAccGroupsde = getAccountGroupsList();

		softAssert.assertFalse(actualAccGroupsde.containsAll(expectedAccGroups),"Multiple groups are deleted.");

		softAssert.assertAll();

		Reporter.log("Successfully added Multiple accounts and groups to the restricted list<br>", true);

	}

	/**
	 * Test Case Id: C113809,C113807,C114017,C117834,C124411,C118377,C118378,C115017,C118810,C120377,C124299,C124298,C113889,C118405
	 * Author : MMA
	 * @throws Exception
	 */
	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testAddEditDeleteTemplateAndTemplateGroup() throws Exception{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create/Modify a template group of first level in the hierarchy : C113809"
				+ "Create/Modify a child template group for existing template group : C113807"
				+ "Delete a template group : C114017"
				+ "Delete a template group, which has some child groups/templates in it. : C114018"
				+ "Open a template group through deep link path : C117834 "
				+ "Workorder type lookup field is mandatory to create a workorder template : C124411"
				+ "Restricted check-box is empty(unchecked) by default : C118377,C118378"
				+ "Related object type drop-down is available in workorder templates pane : C118405"
				+ "Edit related object field of a saved WO template : C115017"
				+ "Restrict workorder template to some particular accounts : C118810"
				+ "User cannot access the deep link for the restricted Group/Template : C120377"
				+ "Working area is filled with 'workorder template' pane on user selection. : C124299"
				+ "Working area is filled with 'template group pane' on user selection : C124298"
				+ "Copy workorder template : C113889"+" </span><br>", 
				true);

		String reference = "TempRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String referenceEdited = "TempRefEdit"+getCurrentDate().substring(getCurrentDate().length()-5);
		String grpReference = "TempGrpRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String grpReferenceLevel = "TempGrpRefLevel1"+getCurrentDate().substring(getCurrentDate().length()-5);
		String grpReferenceEdited = "TempGrpRefEdit"+getCurrentDate().substring(getCurrentDate().length()-5);
		String woType = "Aqa_WO_Type_Name";
		String relatedObjType = "None";
		String woGroup = "AqaTemplateGroup";
		String username = configuration.getUserName();
		String password = configuration.getPassword();
		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testAddEditDeleteTemplateAndTemplateGroup");

		String tempGroup = "/frame.php?relay=MCS_PORTAL_WO-NEW_TEMPLATE_GROUP_ID|0000000007";

		waitForExtJSAjaxComplete(20);
		clickAdministration();
		waitForExtJSAjaxComplete(5);

		expandAdministrationOptions("Module Settings");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(isWOTemplateUnderWONodeInAdministration(), "Workorder Templates node is listed under workorders.");

		clickAdministrationOptions("Workorders", "Work Order Templates");

		softAssert.assertTrue(verifyWOTemplatePane(), "Opens Templates pane to the right of administration menu.");
		softAssert.assertTrue(getMsgInWOTemplateDetailsBeforeSelectingTemplate().contains("Please select a Template or Template Group"), "Workorder Templates/Group needs to be selected warning is displayed");
		waitForExtJSAjaxComplete(5);

		//C91998
		clickButtonWOTemplateAdministration("icon-templategroup-add");
		waitForExtJSAjaxComplete(25);

		setReferenceWOTemplate(grpReferenceLevel);
		clickButtonWOTemplateDetailsAdministration("icon-save", "Save Changes");
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(isTemplateCreatedAtFirstLevelInHierarchy(grpReferenceLevel),"Template Group of first level in the hierarchy is displayed in the templates pane.");
		waitForExtJSAjaxComplete(10);

		closeModule("Administration");
		waitForExtJSAjaxComplete(5);

		clickAdministration();
		waitForExtJSAjaxComplete(5);

		expandAdministrationOptions("Module Settings");
		waitForExtJSAjaxComplete(5);

		clickAdministrationOptions("Workorders", "Work Order Templates");
		waitForExtJSAjaxComplete(25);

		clickButtonWOTemplateAdministration("icon-template-add");
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(isTabPresentInWODetailsWin("General"), "Template is opened where user can provide properties of new template.");

		//C92072: Related object type drop-down values verification
		List<String> expectedObjectValues=Arrays.asList("None","Call","Workorder","Project","Maintenance Object");

		softAssert.assertEquals(DropDown.getComboValuesFromDropDownSelector(driver,getFieldIdBasedOnLabel("Related Object Type")),expectedObjectValues," following option are available in the drop-down: Call, Workorder, Project, Maintenance Object,None");

		setReferenceWOTemplate(reference);
		waitForExtJSAjaxComplete(3);

		selectRelatedObjectType(relatedObjType);
		//C92069: saving template without WO Type 
		clickButtonWOTemplateDetailsAdministration("icon-save", "Save Changes");
		waitForExtJSAjaxComplete(15);

		softAssert.assertTrue(getWarningDialogTextMsg().contains("Template workorder type cannot be empty"),"Template is not saved, indicates that workorder type is also a mandatory field");
		clickOnDialogButton("OK");
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(15);

		setWOTypeWOTemplate(woType);
		
		waitForExtJSAjaxComplete(15);

		clickButtonWOTemplateDetailsAdministration("icon-save", "Save Changes");
		waitForExtJSAjaxComplete(15);

		softAssert.assertTrue(isTemplateCreatedAtFirstLevelInHierarchy(reference),"Template of first level in the hierarchy is displayed in the templates pane.");
		waitForExtJSAjaxComplete(10);
		setReferenceWOTemplate(referenceEdited);

		clickButtonWOTemplateDetailsAdministration("icon-save", "Save Changes");
		waitForExtJSAjaxComplete(25);

		selectWOTemplate(referenceEdited);
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getReferenceFromWOTemplate(), referenceEdited, "Reference field value is changed.");
		waitForExtJSAjaxComplete(20);

		SchedulerPageObject schPgObj = new SchedulerPageObject();
		
		schPgObj.collapseNavigation();
		
		waitForExtJSAjaxComplete(15);
		
		collapseAdministration();
		waitForExtJSAjaxComplete(15);
		
		//C92079: related object field verification
		softAssert.assertTrue(isTabPresentInWODetailsWin("General"), "Selected workorder template properties are opened in working area");
		softAssert.assertFalse(isFieldEditable("Related Object Type")," Related Object Type value cannot be changed");

		//C92022: WO Template Pane tabs verification
		softAssert.assertTrue(isTabPresentInWODetailsWin("General"),"template form with General tab is opened.");
		softAssert.assertTrue(isTabPresentInWODetailsWin("Fields"),"template form with Fields tab is opened.");
		softAssert.assertTrue(isTabPresentInWODetailsWin("Field Labels"),"template form with Field Labels tab is opened.");
		softAssert.assertTrue(isTabPresentInWODetailsWin("Security"),"template form with security tab is opened.");
		softAssert.assertTrue(isTabPresentInWODetailsWin("Template Groups"),"template form with Template Groups tab is opened.");
		softAssert.assertTrue(isTabPresentInWODetailsWin("Bill of Materials"),"template form with Bill of Materials tab is opened.");
		softAssert.assertTrue(isTabPresentInWODetailsWin("Checklists"),"template form with Checklists tab is opened.");

		//Restricting the template with logged in user
		clickSecurityTab();
		waitForExtJSAjaxComplete(10);

		clickAddAccountButton();
		waitForExtJSAjaxComplete(10);

		softAssert.assertEquals(getWindowTitle("@class","x-resizable-pinned"),"Select an Account", "Accounts look up is opened.");
		setValueGridLookupWithFilters("@id", getXWindowId("Select an Account"),username, "ID");
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(getCheckBoxFieldStatus("Restricted"),"Restricted check box is checked");
		softAssert.assertTrue(isRowPresentInSecurityTab(getUserNameOfLoggedInUser(),"icon-account"),"Account is added in security tab");

		clickButtonWOTemplateDetailsAdministration("icon-save", "Save Changes");
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		clickGeneralTab();
		waitForExtJSAjaxComplete(15);

		String tempDeepLink = getDeepLinkFromWOTemplate();
		System.out.println(tempDeepLink);
		waitForExtJSAjaxComplete(15);

		/*closeModule("Administration");
		waitForExtJSAjaxComplete(5);*/

		driver.navigate().refresh();
		waitForExtJSAjaxComplete(15);

		clickAdministration();
		waitForExtJSAjaxComplete(15);

		expandAdministrationOptions("Module Settings");
		waitForExtJSAjaxComplete(15);

		clickAdministrationOptions("Workorders", "Work Order Templates");
		selectWOTemplate(woGroup);
		waitForExtJSAjaxComplete(15);

		//C92046: Restricted check box verification
		softAssert.assertTrue(isTabPresentInWODetailsWin("Security"), "New template group form with security tab is opened.");
		clickSecurityTab();
		waitForExtJSAjaxComplete(10);
		softAssert.assertFalse(getCheckBoxFieldStatus("Restricted"),"Restricted check-box is displayed and is unchecked by default");

		//C92136: Restricting WO Template to some account
		softAssert.assertTrue(isButtonPresent("x-btn-text-icon","Add Account"),"Security tab is displayed with 'Add Account' button");
		softAssert.assertTrue(isButtonPresent("x-btn-text-icon","Add Group"),"Security tab is displayed with 'Add Group' button");
		softAssert.assertTrue(isButtonPresent(" x-btn-text-icon x-item-disabled","Remove"),"Security tab is displayed with 'Remove'(disabled) button");

		clickAddAccountButton();
		waitForExtJSAjaxComplete(10);

		softAssert.assertEquals(getWindowTitle("@class","x-resizable-pinned"),"Select an Account", "Accounts look up is opened.");
		setValueGridLookupWithFilters("@id", getXWindowId("Select an Account"),username, "ID");
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(getCheckBoxFieldStatus("Restricted"),"Restricted check box is checked");
		softAssert.assertTrue(isRowPresentInSecurityTab(getUserNameOfLoggedInUser(),"icon-account"),"Account is added in security tab");

		clickButtonWOTemplateAdministration("icon-templategroup-add");
		waitForExtJSAjaxComplete(10);

		//C92021: 'template group pane' verification 
		softAssert.assertTrue(isTabPresentInWODetailsWin("General"), "New template group form with general tab is opened.");
		softAssert.assertTrue(isTabPresentInWODetailsWin("Security"), "New template group form with security tab is opened.");

		setReferenceWOTemplate(grpReference);
		clickButtonWOTemplateDetailsAdministration("icon-save", "Save Changes");
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		//C91999
		softAssert.assertTrue(isTemplateCreatedAtSecondLevelInHierarchy(woGroup,grpReference),"New child template group is created under selected template group in the templates pane");
		
		waitForExtJSAjaxComplete(15);
		
		setReferenceWOTemplate(grpReferenceEdited);
		
		waitForExtJSAjaxComplete(15);
		
		clickButtonWOTemplateDetailsAdministration("icon-save", "Save Changes");
		
		waitForExtJSAjaxComplete(15);
		
		try{
			waitForMaskDisappear();
		}
		catch(Exception e){

		}
		waitForExtJSAjaxComplete(25);
		
		selectWOTemplate(grpReferenceEdited);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getReferenceFromWOTemplate(), grpReferenceEdited, "Reference field value is changed.");
		
		waitForExtJSAjaxComplete(15);

		//C92010: delete a template group
		//C114018:Delete a template group, which has some child groups/templates in it.
		clickButtonWOTemplateAdministration("icon-templategroup-delete");
		waitForExtJSAjaxComplete(10);

		clickOnDialogButton("No");
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		expandWOTemplateGroup(woGroup);
		waitForExtJSAjaxComplete(10);
		softAssert.assertTrue(isTemplateCreatedAtSecondLevelInHierarchy(woGroup,grpReferenceEdited),"No changes, Selected template group will not be deleted.");
		waitForExtJSAjaxComplete(10);
		
		clickButtonWOTemplateAdministration("icon-templategroup-delete");
		waitForExtJSAjaxComplete(10);

		clickOnDialogButton("Yes");
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		expandWOTemplateGroup(woGroup);
		waitForExtJSAjaxComplete(10);
		softAssert.assertFalse(isTemplateCreatedAtSecondLevelInHierarchy(woGroup,grpReferenceEdited),"Selected template group is deleted");
		waitForExtJSAjaxComplete(10);

		//C92134
		selectWOTemplate("1preMoCtWrkTmpRef");
		clickButtonWOTemplateAdministration("icon-template-copy");
		waitForExtJSAjaxComplete(10);

		softAssert.assertEquals(getWindowTitle("@class","x-window-dlg"),"Copy Template","A popup with header 'Copy Template' appeared");
		setCopyTemplateReferance(reference+"Copy");
		waitForExtJSAjaxComplete(10);
		clickOnDialogButton("OK");
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(isTemplateCreatedAtSecondLevelInHierarchy(woGroup,reference+"Copy"),"New workorder template is created under the selected template group in the templates tree");
		softAssert.assertTrue(isTemplateCreatedAtSecondLevelInHierarchy(woGroup,"1preMoCtWrkTmpRef"),"Original WO template also exist under the template group");

		waitForExtJSAjaxComplete(5);

		logout();

		//C92088: open template group through deep link
		navigateToPage(tempGroup);
		waitForExtJSAjaxComplete(5);
		login(username, password);
		waitForExtJSAjaxComplete(25);
		try{
			waitForMaskDisappear();
		}
		catch(Exception E){
			
		}
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(isTempOrTempGrpSelected("grpLabel",woGroup),"User is logged in and page is redirected to specific template group.");
		waitForExtJSAjaxComplete(15);

		//C92091: open restricted template through deep link 
		System.err.println(tempDeepLink);
		logout();
		tempDeepLink= tempDeepLink.replace("https://dev172php56.mcs.be/AQA","").replace("%7C","|").trim();
		System.out.println(tempDeepLink);
		navigateToPage(tempDeepLink);
		waitForExtJSAjaxComplete(5);
		login("mma", password);
		waitForExtJSAjaxComplete(25);
		softAssert.assertTrue(getWarningDialogTextMsg().contains("Unable to load template"),"User will not be redirected to the particular group/template.");
		clickOnDialogButton("OK");
		waitForExtJSAjaxComplete(5);

		expandAllWOTemplates();
		waitForExtJSAjaxComplete(5);

		HelpDeskFrontOfficePageObject HDPgObj = new HelpDeskFrontOfficePageObject();
		softAssert.assertFalse(HDPgObj.isCallTemplateAvailable(referenceEdited),"Restricted WO Template is not visible");
		waitForExtJSAjaxComplete(5);

		softAssert.assertAll();

		Reporter.log("Add, Edit and delete Workorder template and template group", true);
	}
	
	/**
	 * Testcase ID :C110697,C110848,C116254,C114398
	 * Author : srd
	 * @throws IOException
	 */
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class)
	public void testAddWorkorderTemplateToTemplateGroup() throws IOException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C110848:Add Workorder Template To TemplateGroup"
				+"C110697:A popup appears when copy workorder template button is clicked."
				+"C116254:Link a Workorder template to more than one group"
				+"C114398:Deep Link field value is automatically generated in workorder template"
				,true);


		String reference = "WO"+ getCurrentDate().substring(getCurrentDate().length() - 6);
		
		String url =driver.getCurrentUrl().replace("?aqa","").trim();
		
		String webLink 	 =url+"frame.php?relay=MCS_PORTAL_WO-NEW_FROM_TEMPLATE_ID";

		AdministrationPageObject objad = new AdministrationPageObject();

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testAddWorkorderTemplateToTemplateGroup");

		clickAdministration();

		waitForExtJSAjaxComplete(20);

		objad.expandModuleSettings();

		waitForExtJSAjaxComplete(20);

		objad.expandWorkorders();

		waitForExtJSAjaxComplete(20);

		objad.clickWorkorderTemplates();

		waitForExtJSAjaxComplete(20);
		
		//C110848 

		selectWorkorderTemplateGroup("AqaTemplateGroup");

		waitForExtJSAjaxComplete(10);

		clickWOTemplateCreateButton();

		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(getReferenceFromWOTemplate().isEmpty(),"Open a template in right working area, where user can provide properties of new template");

		waitForMaskDisappear();
		setReferenceWOTemplate(reference);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(10);

		setWorkOrderTypeInWOTemplate("Aqa_WO_Type_Name");

		waitForExtJSAjaxComplete(10);

		clickSaveChangesButton();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isWOTemplateUnderWOGroup("AqaTemplateGroup",reference),"A new template is added under the selected group in the templates tree.");
		
		//C110697

		selectWOTemplate(reference);

		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(getDeepLinkFromWOTemplate().contains(webLink),"Deep link field is filled with a web link.");

		waitForExtJSAjaxComplete(10);

		clickWOTemplateCopyButton();

		waitForExtJSAjaxComplete(10);

		softAssert.assertEquals(getWindowTitle("@class","x-window-dlg"),"Copy Template","A popup with header Copy Template is appeared.");

		clickOnDialogButton("Cancel");

		waitForExtJSAjaxComplete(10);
		
		//C116254

		SchedulerPageObject schPgObj = new SchedulerPageObject();
		schPgObj.collapseNavigation();
		waitForExtJSAjaxComplete(15);
		
		collapseAdministration();
		waitForExtJSAjaxComplete(15);
		
		clickTemplateGroupstab();

		waitForExtJSAjaxComplete(10);

		clickAddTemplateGroups();

		waitForExtJSAjaxComplete(10);

		selectTemplateGroupFromLookup("DoNotEditGroup1");

		waitForExtJSAjaxComplete(10);

		clickSaveChangesTemplateGroupstab();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isWOTemplateUnderWOGroup("DoNotEditGroup1",reference),"workorder template will be as child to both groups in templates tree.");
		softAssert.assertTrue(isWOTemplateUnderWOGroup("AqaTemplateGroup",reference),"workorder template will be as child to both groups in templates tree.");

		waitForExtJSAjaxComplete(20);

		selectWOTemplate(reference);

		clickWOTemplateDeleteButton();

		waitForExtJSAjaxComplete(10);

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(isWOTemplateUnderWOGroup("AqaTemplateGroup",reference),"Template is Removed from AqaTemplateGroup ");

		waitForExtJSAjaxComplete(10);

		softAssert.assertFalse(isWOTemplateUnderWOGroup("DoNotEditGroup1",reference),"Template is Removed from DoNotEditGroup1");

		waitForExtJSAjaxComplete(10);

		softAssert.assertAll();

		Reporter.log(" Added WorkorderTemplate comes under the particular TemplateGroup<br>", true);

	}

	/**
	 * Testcase ID :C110759,C113967,C120098
	 * Author : kve
	 * @throws IOException
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testAddDeleteBOMLine() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"Test C110759: Add a BOM line in workorder template </span><br>"
				+"Test C113967: Delete a BOM line in workorder template </span><br>"
				+"Test C120098: UI verification of bill of materials tab </span><br> ", true);

		String workOrderTemplateGroup = "AqaTemplateGroup";

		String workOrderTemplate = "Aqa_WO_Type";

		String productRef = "1preProdRef";


		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testAddDeleteBOMLine");

		clickAdministration();

		waitForExtJSAjaxComplete(5);

		expandAdministrationOptions("Module Settings");

		waitForExtJSAjaxComplete(5);

		clickAdministrationOptions("Workorders", "Work Order Templates");

		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(verifyWOTemplatePane(), "Opens Templates pane to the right of administration menu.");

		expandWOTemplateGroup(workOrderTemplateGroup);

		selectWOTemplate(workOrderTemplate);

		waitForExtJSAjaxComplete(15);
		
		collapseNavigation();
		
		waitForExtJSAjaxComplete(15);

		clickTab("Bill of Materials");

		softAssert.assertTrue(isColumnPresentInBOMGridOfWOTemplate("Is Stock Item"), "Icon column is available in the BOM's grid");

		softAssert.assertTrue(isColumnPresentInBOMGridOfWOTemplate("Product Designation"), "Product Designation column is available in the BOM's grid");

		softAssert.assertTrue(isColumnPresentInBOMGridOfWOTemplate("Product Code"), "Product Code column is available in the BOM's grid");

		softAssert.assertTrue(isColumnPresentInBOMGridOfWOTemplate("Product Reference"), "Product Reference column is available in the BOM's grid");

		softAssert.assertTrue(isColumnPresentInBOMGridOfWOTemplate("Product UOM"), "Product UOM column is available in the BOM's grid");

		softAssert.assertTrue(isColumnPresentInBOMGridOfWOTemplate("Needed Quantity"), "Needed Quantity column is available in the BOM's grid");

		softAssert.assertTrue(isColumnPresentInBOMGridOfWOTemplate("Status"), "Status column is available in the BOM's grid");

		softAssert.assertTrue(isColumnPresentInBOMGridOfWOTemplate("Warehouse"), "Warehouse column is available in the BOM's grid");

		softAssert.assertTrue(isColumnPresentInBOMGridOfWOTemplate("Remark"), "Remark column is available in the BOM's grid");

		waitForExtJSAjaxComplete(15);

		addABOMLineInTemplate(productRef);

		waitForExtJSAjaxComplete(20);

		clickSaveChangesButton();

		waitForExtJSAjaxComplete(5);

		String neededQty = getNeededQuantityOfBOMLine("1");

		softAssert.assertEquals(neededQty, "1.00", "BOM line is added to the tab and by default needed quantity will be 1");

		editNeededQuantityInBOMLine("1","2");

		waitForExtJSAjaxComplete(5);

		clickSaveChangesButton();

		waitForExtJSAjaxComplete(5);

		selectBOMLineInGrid("1");

		waitForExtJSAjaxComplete(5);

		clickRemoveButtonInWorkOrderTemplates();

		waitForExtJSAjaxComplete(10);

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(10);

		clickSaveChangesButton();

		waitForExtJSAjaxComplete(5);

		softAssert.assertFalse(isSelectedBomLineAvailableInGrid("1", "2.00"),"product can be deleted");

		softAssert.assertAll();

		Reporter.log("BOM line in workorder template was Added and Deleted Successfully<br>", true);
	}
	
	/**
	 * Testcase ID :C113806
	 * Author : ssa
	 * @throws IOException
	 */
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class)
	public void testAddTemplateGroupToWorkorderTemplate() throws IOException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"Create/Modify a child template group by selecting workorder template of a parent template group.",true);


		String reference = "WO"+ getCurrentDate().substring(getCurrentDate().length() - 6);
		String grpReference = "WOGrp"+ getCurrentDate().substring(getCurrentDate().length() - 6);
		String grpReferenceEdit = "WOGrpEdit"+ getCurrentDate().substring(getCurrentDate().length() - 6);
		
		
		AdministrationPageObject objad = new AdministrationPageObject();

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testAddWorkorderTemplateToTemplateGroup");

		clickAdministration();

		waitForExtJSAjaxComplete(20);

		objad.expandModuleSettings();

		waitForExtJSAjaxComplete(20);

		objad.expandWorkorders();

		waitForExtJSAjaxComplete(20);

		objad.clickWorkorderTemplates();

		waitForExtJSAjaxComplete(20);
		
		//C110848 

		selectWorkorderTemplateGroup("AqaTemplateGroup");

		waitForExtJSAjaxComplete(10);

		clickWOTemplateCreateButton();

		waitForExtJSAjaxComplete(10);

		//softAssert.assertTrue(getReferenceFromWOTemplate().isEmpty(),"Open a template in right working area, where user can provide properties of new template");

		waitForMaskDisappear();
		setReferenceWOTemplate(reference);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(10);

		setWorkOrderTypeInWOTemplate("Aqa_WO_Type_Name");

		waitForExtJSAjaxComplete(10);

		clickSaveChangesButton();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isWOTemplateUnderWOGroup("AqaTemplateGroup",reference),"A new template is added under the selected group in the templates tree.");
		
	
		selectWOTemplate(reference);

		waitForExtJSAjaxComplete(10);
		
		clickButtonWOTemplateAdministration("icon-templategroup-add");
		
		waitForExtJSAjaxComplete(25);

		setReferenceWOTemplateGrp(grpReference);

		waitForExtJSAjaxComplete(20);
		
		clickSaveChangesButtonForTempGrp();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isAddedWOGrpChildOfWOTemplateGrp("AqaTemplateGroup",grpReference),"A new Grp is added as aparent to the selected template in templates tree.");

		selectWorkorderTemplateGroup(grpReference);
		
		waitForExtJSAjaxComplete(25);
		
		setReferenceWOTemplateGrp(grpReferenceEdit);
		
		waitForExtJSAjaxComplete(20);
		
		clickSaveChangesButtonForTempGrp();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getReferenceFromWOTemplateGrp(), grpReferenceEdit, "Reference field value is changed.");
		List<String> values=Arrays.asList(grpReferenceEdit,reference);
		for(String value:values)
		{
		selectWorkorderTemplateGroup(value);
		
		waitForExtJSAjaxComplete(20);
		
		if(value==grpReferenceEdit)
		{
		clickWOGrpDeleteButton();
		}
		else{
			clickWOTemplateDeleteButton();
		}
		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(isAddedWOGrpChildOfWOTemplateGrp("AqaTemplateGroup",value),""+value+" is Removed from AqaTemplateGroup ");

		}
		//waitForExtJSAjaxComplete(10);

		//softAssert.assertFalse(isWOTemplateUnderWOGroup("AqaTemplateGroup",reference),"Template is Removed from DoNotEditGroup1");

		
		waitForExtJSAjaxComplete(10);

		softAssert.assertAll();

		Reporter.log("Create/Modify a child template group by selecting workorder template of a parent template group is verified<br>", true);

	}
	
	/**
	 * Testcase Id: C118810,C118650,C124419
	 * Author     : MMA    
	 * @throws IOException
	 */
	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testRestrictWOTemplateGroup() throws IOException{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"Test C118810: Restrict workorder template to some particular accounts. </span><br>"
				+"Test C118650: Restriction list is automatically cleared, if user uncheck the restricted check box. </span><br>"
				+"Test C124419: Workorder templates in a restricted template groups are not visible for the accounts that are not added in security tab.</span><br> ", true);

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testRestrictWOTemplateGroup");

		String woGroup = "AqaTemplateGroup";
		String username = "mma";
		String tempReference = "TempRef"+getCurrentDate().substring(getCurrentDate().length()-4);
		String woType = "Aqa_WO_Type_Name";
		String relatedObjType = "None";

		clickAdministration();
		waitForExtJSAjaxComplete(5);

		expandAdministrationOptions("Module Settings");
		waitForExtJSAjaxComplete(10);

		clickAdministrationOptions("Workorders", "Work Order Templates");
		waitForExtJSAjaxComplete(10);


		//C118650: Restriction list is automatically cleared, if user uncheck the restricted check box.
		softAssert.assertTrue(verifyWOTemplatePane(), "Opens Templates pane to the right of administration menu.");
		softAssert.assertTrue(getMsgInWOTemplateDetailsBeforeSelectingTemplate().contains("Please select a Template or Template Group"), "Workorder Templates/Group needs to be selected warning is displayed");
		waitForExtJSAjaxComplete(5);

		selectWOTemplate(woGroup);
		waitForExtJSAjaxComplete(25);

		clickSecurityTab();
		waitForExtJSAjaxComplete(10);

		clickAddAccountButton();
		waitForExtJSAjaxComplete(10);

		softAssert.assertEquals(getWindowTitle("@class","x-resizable-pinned"),"Select an Account", "Accounts look up is opened.");
		setValueGridLookupWithFilters("@id", getXWindowId("Select an Account"),username, "ID");
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(getCheckBoxFieldStatus("Restricted"),"Restricted check-box is checked automatically.");
		softAssert.assertTrue(isRowPresentInSecurityTab("Mandlem Manasa","icon-account"),"Account is added in security tab");

		uncheckRestrictedCheckBox();
		waitForExtJSAjaxComplete(10);

		softAssert.assertFalse(getCheckBoxFieldStatus("Restricted"),"Restricted check box is unchecked, verified");
		softAssert.assertFalse(isRowPresentInSecurityTab(getUserNameOfLoggedInUser(),"icon-account"),"Restriction list is automatically cleared");

		//C118810: Restrict workorder template to some particular accounts.
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

		clickButtonWOTemplateDetailsAdministration("icon-save", "Save Changes");
		waitForExtJSAjaxComplete(15);

		clickSecurityTab();
		waitForExtJSAjaxComplete(10);
		softAssert.assertFalse(getCheckBoxFieldStatus("Restricted"),"Restricted check-box is displayed and is unchecked by default");
		softAssert.assertTrue(isButtonPresent("x-btn-text-icon","Add Account"),"Security tab is displayed with 'Add Account' button");
		softAssert.assertTrue(isButtonPresent("x-btn-text-icon","Add Group"),"Security tab is displayed with 'Add Group' button");
		softAssert.assertTrue(isButtonPresent(" x-btn-text-icon x-item-disabled","Remove"),"Security tab is displayed with 'Remove'(disabled) button");

		clickAddAccountButton();
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getWindowTitle("@class","x-resizable-pinned"),"Select an Account", "Accounts look up is opened.");
		setValueGridLookupWithFilters("@id", getXWindowId("Select an Account"),username, "ID");
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(getCheckBoxFieldStatus("Restricted"),"Restricted check box is checked");
		softAssert.assertTrue(isRowPresentInSecurityTab("Mandlem Manasa","icon-account"),"Account is added in security tab");

		//C124419: Workorder templates in a restricted template groups are not visible for the accounts that are not added in security tab
		clickButtonWOTemplateDetailsAdministration("icon-save", "Save Changes");
		waitForExtJSAjaxComplete(15);

		closeModule("Administration");
		waitForExtJSAjaxComplete(15);

		waitAndClick(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();
		waitForExtJSAjaxComplete(20);

		clickAddButton();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		InspectionPageObject insPage = new InspectionPageObject();

		insPage.expandAllTemplatedGroups();
		waitForExtJSAjaxComplete(25);
		
		List<String> tempList = getAllAvailableTemplates();
		System.err.println(tempList);
		softAssert.assertFalse(tempList.contains(tempReference),"Template is not display in the list, because your account is not in the restricted list.");

		softAssert.assertAll();
		Reporter.log("Restriction of work order template group",true);
	}

	/**
	 * Testcase ID :C117836,C114394,C124414
	 *  Author : kve
	 * @throws IOException
	 */
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class)
	public void testWOTempThrDeepLink() throws IOException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "

				+ "C117836: Open a workorder template through deep link path."
				+ "C114394: Deep Link field is a read only field and only visible for already existing workorder template."
				+ "C124414: Workorder Template node is disabled, if the user doesn't have the appropriate right.", true);

		String workOrderTemplateGroup = "AqaTemplateGroup";
		String wotemplate = "1preMoCtWrkTmpRef";
		String username = configuration.getUserName();
		String password = configuration.getPassword();

		String WOtemp = "/frame.php?relay=MCS_PORTAL_WO-NEW_FROM_TEMPLATE_ID%7C0000000205";


		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testWOTempThrDeepLink");

		//C114394: Deep Link field is a read only field and only visible for already existing workorder template.

		clickAdministration();

		waitForExtJSAjaxComplete(5);

		expandAdministrationOptions("Module Settings");

		waitForExtJSAjaxComplete(5);

		clickAdministrationOptions("Workorders", "Work Order Templates");

		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(verifyWOTemplatePane(), "Opens Templates pane to the right of administration menu.");

		waitForExtJSAjaxComplete(5);

		expandWOTemplateGroup(workOrderTemplateGroup);

		selectWOTemplate(wotemplate);

		waitForExtJSAjaxComplete(15);

		clickTab("General");

		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(isTabPresentInWODetailsWin("General"), "New template group form with general tab is opened.");

		softAssert.assertTrue(isDeeplinkFieldReadOnly(), "Deeplink field Value cannot be changed, because it is a read only field.");

		waitForExtJSAjaxComplete(5);

		logout();

		waitForExtJSAjaxComplete(15);

		//C124414:  Workorder Template node is disabled, if the user doesn't have the appropriate right. 

		driver.navigate().refresh();

		login("NoRightForAddModWOTemp", "qwerty");

		waitForExtJSAjaxComplete(15);

		clickAdministration();

		waitForExtJSAjaxComplete(5);

		expandAdministrationOptions("Module Settings");

		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(isWOTempNodeDisabled(),"Workorder templates cannot be selected (disabled).");

		waitForExtJSAjaxComplete(15);

		logout();

		waitForExtJSAjaxComplete(15);

		//C117836: Open a workorder template through deep link path.
		navigateToPage(WOtemp);

		waitForExtJSAjaxComplete(5);

		login(username, password);

		waitForExtJSAjaxComplete(25);
		try{
			waitForMaskDisappear();
		}
		catch(Exception E){

		}
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(isTempOrTempGrpSelected("tplLabel",wotemplate),"User will be logged in and page redirects to specific workorder template.");

		waitForExtJSAjaxComplete(15);

		softAssert.assertAll();

		Reporter.log("Successfully Opened a workorder template through deep link path.<br>", true);

	}
	
	/**
	 * Test Case : C119948,C124313,C115326,C114288,C110701,C113450,C113890
	 * Author    : MMA
	 * @throws IOException
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testLinkTempToMultipleGroups()throws IOException{
		Reporter.log("C119948 : Templates tree displayed in templates pane."
				+ "C124313: Work Order templates node is available in administration pane."
				+ "C115326: Filter template group or workorder template."
				+ "C114288: Delete template group button is disabled, if not selected any template group"
				+ "C110701: A template can belong to multiple template groups."
				+ "C113450: Clear the filter field."
				+ "C113890: Copy Workorder template button is disabled, if not selected any workorder template.");

		SoftAssert softAssert = new SoftAssert(); 
		softAssert.setMethodName("testLinkTempToMultipleGroups");

		String tempReference = "TempRefLinkToMultipleGrps"+getCurrentDate().substring(getCurrentDate().length()-4);
		String woType = "Aqa_WO_Type_Name";
		String relatedObjType = "None";
		List<String> woGroups = new ArrayList<String>(Arrays.asList("AqaTemplateGroup","DoNotEditGroup1"));

		//C119948,C124313
		waitForExtJSAjaxComplete(20);
		clickAdministration();
		waitForExtJSAjaxComplete(5);

		expandAdministrationOptions("Module Settings");
		waitForExtJSAjaxComplete(10);

		InspectionTemplatesCRUDTestSuite inspTempObj = new InspectionTemplatesCRUDTestSuite();

		softAssert.assertTrue(inspTempObj.isNodePresentUnderModuleSettings("Workorders"),"Module settings tree is expanded.");

		softAssert.assertTrue(isWOTemplateUnderWONodeInAdministration(), "Workorder Templates node is listed under workorders of module settings");

		clickAdministrationOptions("Workorders", "Work Order Templates");

		softAssert.assertTrue(verifyWOTemplatePane(), "Templates tree is displayed to the right of administration menu");

		//C114288
		softAssert.assertTrue(isDisabledDeleteButton(DELETEGROUP_BUTTON_CLASS),"Delete templategroup button is disabled/deactivated., if not selected any workorder temp");

		//C113890
		softAssert.assertTrue(isDisabledDeleteButton(COPY_BUTTON_CLASS),"Copy workorder template button is disabled/deactivated., if not selected any workorder temp");

		List<String> availableGrpAndTempBeforeFilter = getAllFilteredTempAndTempGrp();

		//C115326,C110701
		HashSet<String> expectedWOTempList = new HashSet<String>();
		expandWOTemplateGroup(woGroups.get(0));
		waitForExtJSAjaxComplete(5);

		expectedWOTempList.addAll(getWOUnderTemplateGroup(woGroups.get(0)));
		expandWOTemplateGroup(woGroups.get(1));
		waitForExtJSAjaxComplete(5);

		expectedWOTempList.addAll(getWOUnderTemplateGroup(woGroups.get(1)));
		selectWOTemplate(woGroups.get(0));
		waitForExtJSAjaxComplete(25);

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

		SchedulerPageObject schPgObj = new SchedulerPageObject();
		schPgObj.collapseNavigation();
		waitForExtJSAjaxComplete(15);

		collapseAdministration();
		waitForExtJSAjaxComplete(15);

		clickTemplateGroupstab();
		waitForExtJSAjaxComplete(10);

		clickAddTemplateGroups();
		waitForExtJSAjaxComplete(10);

		selectTemplateGroupFromLookup(woGroups.get(1));
		waitForExtJSAjaxComplete(10);

		clickButtonWOTemplateDetailsAdministration("icon-save", "Save Changes");
		waitForExtJSAjaxComplete(15);

		softAssert.assertTrue(isWOTemplateUnderWOGroup(woGroups.get(1),tempReference),"workorder template will be as child to both groups in templates tree.");
		softAssert.assertTrue(isWOTemplateUnderWOGroup(woGroups.get(1),tempReference),"workorder template will be as child to both groups in templates tree.");

		//adding template Groups to which newly added template is linked and newly added template to expected list
		expectedWOTempList.add(tempReference);
		expectedWOTempList.add(woGroups.get(0));
		expectedWOTempList.add(woGroups.get(1));

		filterWOTempByRef(tempReference);
		waitForExtJSAjaxComplete(15);

		HashSet<String> actualWOTempList = new HashSet<String>();
		actualWOTempList.addAll(getAllFilteredTempAndTempGrp());

		softAssert.assertEqualsNoOrder(actualWOTempList.toArray(),expectedWOTempList.toArray(),"If searching for workorder template that is under some template group, then whole group is display");

		filterWOTempByRef(woGroups.get(0));
		waitForExtJSAjaxComplete(15);	
		
		List<String> filteredList = getAllFilteredTempAndTempGrp();
		woGroups.remove("DoNotEditGroup1");
		softAssert.assertEquals(woGroups,filteredList,"All template groups/Workorder templates with reference are display below.");
		waitForExtJSAjaxComplete(15);

		//C113450
		clearWOTempFilter();
		waitForExtJSAjaxComplete(15);

		List<String> availableGrpAndTempAfterClearingFilter = getAllFilteredTempAndTempGrp();

		softAssert.assertEquals(availableGrpAndTempAfterClearingFilter, availableGrpAndTempBeforeFilter,"Clears the filter field and shows the full template tree again.");

		softAssert.assertAll();

		Reporter.log("A template can belong to multiple template groups", true);
	}
 }


 