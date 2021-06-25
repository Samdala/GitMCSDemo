package test.generalweb.inspections;

import java.io.IOException;

import org.openqa.selenium.remote.server.handler.CloseWindow;
import org.openqa.selenium.remote.server.handler.html5.SetLocationContext;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.generalweb.calltemplates.CallTemplatesPageObject;
import test.generalweb.scheduler.SchedulerPageObject;

public class InspectionTemplatesCRUDTestSuite extends InspectionAdministrationPageObject {


	/**
	 * Test Case ID: C120639,C1206275,C124254,C94787
	 * Author : SRD
	 */		

	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testVerifyAddEditDeleteInspectionPoints() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "


				+ "Test: Verify Add Edit Delete InspectionPoints : C120639" + " </span><br>"
				+ "Test: Veify the elements of Inspection Points Tab : C120627" + " </span><br>"
				+ "Test: Verify validations of inspection Points toolbar options : C124254" + " </span><br>"
				+ "Test: Verify validations of inspection Points :C94787" + " </span><br>", 
				true);

		String inspectionTemplateReference ="C94788"+ getCurrentDate().substring(getCurrentDate().length()-6);
		String inspectionTemplateLoaction ="1aqaPreParSites";
		String inspectionPoints[] =new String[]{"01aqaPreParRooms1","01aqaPreParRooms2","01aqaPreParRooms3","01aqaPreParRooms4","01aqaPreParRooms5"};

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testVerifyAddEditDeleteInspectionPoints");

		clickAdministration();

		waitForExtJSAjaxComplete(20);

		expandModuleSettings();

		waitForExtJSAjaxComplete(20);

		clickInspectionTemplate();

		waitForExtJSAjaxComplete(20);

		//Add Inspection Template

		addInspectionTemplate(inspectionTemplateReference,inspectionTemplateLoaction);
		
		//C94785 Verifying  the elements of Inspection Points Tab
		
		clickTabinInspectionTemplate("Inspection Points");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isButtonPresentOnInspectionTemplate("Add"),"Inspection Tab opens and Add Button is present");
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isButtonPresentOnInspectionTemplate("Remove"),"Remove Button is present");
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isButtonPresentOnInspectionTemplate("Cancel"),"Cancel Button is present");
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isButtonPresentOnInspectionTemplate("Save Changes"),"Save Changes Button is present");
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isMoveButtonPresentOnInspectionTemplate("icon-move-row-up"),"Move up Button is present");
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isMoveButtonPresentOnInspectionTemplate("icon-move-row-down"),"Move down Button is present");
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isGridHeaderPresentOnInspectionTemplate("#"),"Row # header is present");
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isGridHeaderPresentOnInspectionTemplate("Object Type"),"Object Type header is present");
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isGridHeaderPresentOnInspectionTemplate("Code"),"Code header is present");
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isGridHeaderPresentOnInspectionTemplate("Reference"),"Reference header is present");
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isGridHeaderPresentOnInspectionTemplate("Status Class"),"Status Class header is present");
		waitForExtJSAjaxComplete(5);
	
		//C94786 Verify validations of inspection Points toolbar options
		
		for(String inspPoint:inspectionPoints)
		{

			addInspectionPoint(inspPoint);

			waitForExtJSAjaxComplete(20);

			softAssert.assertTrue(Grid.isRowInGridPresent(driver,inspPoint,"@id","inspPointGrid"),inspPoint+"is added to the grid");

		}
		
		//C94787 Verify validations of inspection Points 
		
		softAssert.assertTrue(isRowNumberFieldNumeric(),"Row# field is numerical");
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isRowNumberFieldUnique(),"Row# field is Unique");
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isObjectTypeDisplayingRoom("Room"),"Object Type is Displaying  Room only");
		waitForExtJSAjaxComplete(5);
		
		for(String inspPoint:inspectionPoints)
		{

			softAssert.assertEquals("1aqaPreParRooms",getCodeOfRoomBasedOnReference(inspPoint),"Room Code is displayed");
			waitForExtJSAjaxComplete(5);

		}
	
	    for(int i=1;i<=getInspectionPointsCount();i++)
	    {
	    	softAssert.assertEquals(inspectionPoints[i-1],getInspectionPointinGridrow(i),"Room reference is displayed");
	    }
	
			
		selectInspectionPoint(inspectionPoints[0]);
		
		waitForExtJSAjaxComplete(10);
		
		clickButtonInspectionTemplate("Remove");

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver,"01aqaPreParRooms1","@id","inspPointGrid"),"Selected inspection Point removed");
		
        selectInspectionPoint(inspectionPoints[1]);
		
		waitForExtJSAjaxComplete(5);
		
		selectInspectionPoint(inspectionPoints[2]);
			
		waitForExtJSAjaxComplete(5);
		
		selectInspectionPoint(inspectionPoints[3]);
			
		waitForExtJSAjaxComplete(5);
		
		selectInspectionPoint(inspectionPoints[4]);
		
		waitForExtJSAjaxComplete(5);
		
		//Removing Multiple inspection Points
		
		clickButtonInspectionTemplate("Remove");
		
		softAssert.assertTrue(verifyWarningDialogTextMessage("Are you sure you want to delete selected items?"),"warning message is displayed");

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(isGridEmptyinInspectionTemplateForm(),"Selected inspection Points removed");
		

		//Adding Inspection Points

		for(String inspPoint:inspectionPoints)
		{

			addInspectionPoint(inspPoint);

			waitForExtJSAjaxComplete(20);

			softAssert.assertTrue(Grid.isRowInGridPresent(driver,inspPoint,"@id","inspPointGrid"),inspPoint+"is added to the grid");

		}

		//Modifying Inspection points

		moveInspectionPointToSpecifiedRowByClickingMoveButtons(inspectionPoints[0],4);

		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getInspectionPointinGridrow(4),inspectionPoints[0],"Selected line item moves accordingly.");
		
		waitForExtJSAjaxComplete(10);
		
		moveInspectionPointToSpecifiedRowByClickingMoveButtons(inspectionPoints[4],1);

		waitForExtJSAjaxComplete(10);
		
        softAssert.assertEquals(getInspectionPointinGridrow(1),inspectionPoints[4],"Selected line item moves accordingly.");
		
		waitForExtJSAjaxComplete(10);

		moveInspectionPointToSpecifiedRowByClickingMoveButtons(inspectionPoints[2],inspectionPoints[3]);

		waitForExtJSAjaxComplete(20);

		moveInspectionPoint(2,1);

		waitForExtJSAjaxComplete(20);

		moveInspectionPointToSpecifiedRowByDragDrop(inspectionPoints[3],4);

		waitForExtJSAjaxComplete(20);

		moveInspectionPointToSpecifiedRowByDragDrop(inspectionPoints[0],inspectionPoints[1]);

		waitForExtJSAjaxComplete(20);

		clickButtonInspectionTemplate("Save Changes");

		waitForExtJSAjaxComplete(20);
		
		//Deleting Inspection Points

		deleteAllInspectionPoints();

		waitForExtJSAjaxComplete(20);

		for(int i=0;i>=inspectionPoints.length;i++)
		{

			softAssert.assertTrue(Grid.isRowInGridAbsent(driver,inspectionPoints[i],"@id","inspPointGrid"),inspectionPoints[i]+"is Deleted from the grid");

		}

		closeInspectionTemlate();

		waitForExtJSAjaxComplete(20);

		//Deleting InspectionTemplate

		Grid.checkRowInGriByTextValueAndClick(driver,inspectionTemplateReference);

		waitForExtJSAjaxComplete(20);

		clickButton("Delete");

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(30);

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver,inspectionTemplateReference),inspectionTemplateReference+"is Deleted from the grid");

		softAssert.assertAll();	

		Reporter.log(" Add Edit Delete InspectionPoints is successfully Verified", true);


	}
	/**
	 * Testcase ID : C120656, C120682
	 * Author      : MMA
	 * @throws IOException
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testVerifyAddEditAndDeleteInspectionTempRights() throws IOException{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Verify Add/Modify/Delete Inspection Templates Right, Verify validations of edit Inspection form : C120656,C120682 " + " </span><br>", 
				true);

		String inspectionTemplateReference ="testTemp"+ getCurrentDate().substring(getCurrentDate().length()-6);
		String inspectionTemplateLoaction ="1aqaPreParSites";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testVerifyAddEditAndDeleteInspectionTempRights");

		clickAdministration();
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getActiveModuleName(),"Administration","User navigates to web Administration form");
		expandModuleSettings();
		waitForExtJSAjaxComplete(20);

		clickInspectionTemplate();
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getXPanelWindowHeader("@class","x-form-item"),"Inspection Templates","Inspection Template form opens on right side administration Pane");

		clickButton("Add");	
		softAssert.assertEquals(getWindowTitle("@class","x-resizable-pinned"),"Add Inspection Template","Create inspection Template form Opens");

		setReference(inspectionTemplateReference);
		waitForExtJSAjaxComplete(5);

		clickLookupNewUI("x-window x-resizable-pinned","location");
		waitForExtJSAjaxComplete(20);

		setValueGridLookupWithFiltersNewUI("@class","x6-tree-arrows",inspectionTemplateLoaction,"Reference");
		waitForExtJSAjaxComplete(15);

		clickButtonInspectionTemplate("Save Changes");
		waitForExtJSAjaxComplete(5);

		closeInspectionTemlate();
		waitForExtJSAjaxComplete(5);

		Grid.checkRowInGriByTextValueAndClick(driver,inspectionTemplateReference);
		softAssert.assertTrue(Grid.isRowInGridChecked(driver,inspectionTemplateReference),"Template is selected");

		clickButton("Delete");
		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("No");
		waitForExtJSAjaxComplete(30);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver,inspectionTemplateReference,"@class","x-grid3"),inspectionTemplateReference+"is not Deleted from the grid on clicking 'No'");

		clickButton("Delete");
		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("Yes");
		waitForExtJSAjaxComplete(10);

		clickOnDialogButton("OK");
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver,inspectionTemplateReference),inspectionTemplateReference+"is Deleted from the grid on clicking 'Yes'");

		//logged in with no 'delete Inspection Template Rights' Uses
		navigateToMainPage("NoDeleteInspTempRights","qwerty") ;
		waitForExtJSAjaxComplete(20);

		clickAdministration();
		waitForExtJSAjaxComplete(20);

		expandModuleSettings();
		waitForExtJSAjaxComplete(20);

		clickInspectionTemplate();
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(isButtonPresent("admsettings-modulesettings","Delete"),"Delete button is invisible");
		waitForExtJSAjaxComplete(5);

		//logged in with no 'Inspection Template Rights' User 
		navigateToMainPage("NoInspTempRights","qwerty") ;
		waitForExtJSAjaxComplete(20);

		clickAdministration();
		waitForExtJSAjaxComplete(20);

		expandModuleSettings();
		waitForExtJSAjaxComplete(20);

		clickInspectionTemplate();
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		//Inspection Template node is disabled when the uses doesn't have both 'Add/Modify Inspection Template' and 'Delete Inspection Templates' Rights
		softAssert.assertTrue(isNodeDisabled("Inspection Templates")," 'Inspection Templates' Node is to be disabled ");

		softAssert.assertAll();

		Reporter.log("Verify Add/Modify/Delete Inspection Templates Right, Verify validations of edit Inspection form ", true);
	}

	/**
	 * Testcase Id : C101808,C101819,C101820
	 * Author      : MMA  
	 * @throws IOException
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testVerifyPossibleActionsOnInspectionTemplate()throws IOException{

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Verify possible actions on default view of Inspection Template form : C101808 ,"
				+ " Verify Edit Inspection Template Details  : C101819 "
				+ "Verify 'Inspection Point' Tab and 'Security' Tab enable only on successful saving the template first Time : C101820 " + " </span><br>", 
				true);

		String inspectionTemplateReference = "C97445";
		String newInspTempRef ="testTemp"+ getCurrentDate().substring(getCurrentDate().length()-6);
		String inspectionTemplateLoaction ="1aqaPreParSites";
		String expectedInspPoints[] = {"01aqaPreParRooms1"};

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testVerifyPossibleActionsOnInspectionTemplate");

		waitForExtJSAjaxComplete(20);
		clickAdministration();
		waitForExtJSAjaxComplete(20);

		expandModuleSettings();
		waitForExtJSAjaxComplete(20);

		clickInspectionTemplate();
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getXPanelWindowHeader("@class","x-form-item"),"Inspection Templates","Inspection Template form opens on right side administration Pane");

		clickButton("Add");	
		waitForExtJSAjaxComplete(20);

		InspectionPageObject inspPObj = new InspectionPageObject();

		String windowTitle = inspPObj.getInspectionWindowHeader("window");
		softAssert.assertEquals(windowTitle,"Add Inspection Template","Add Inspection Template is to Open");

		closeUsingToolBarJS(windowTitle);
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(inspPObj.getInspectionWindowHeader("window"),"","Form is closed on clicking (x) button");

		clickButton("Edit");
		waitForExtJSAjaxComplete(20);
		softAssert.assertTrue(getWarningDialogTextMsg().contains("Please select an item"),"A warning message is to display Message saying 'Please select an Item'");

		clickOnDialogButton("OK");
		waitForExtJSAjaxComplete(10);

		Grid.checkRowInGriByTextValueAndClick(driver,inspectionTemplateReference);
		clickButton("Edit");
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		String editWinTitle = inspPObj.getInspectionWindowHeader("window");
		softAssert.assertEquals(editWinTitle,"Inspection Template [C97445]","Edit inspection Template from is Opened");

		closeUsingToolBarJS(editWinTitle);
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(inspPObj.getInspectionWindowHeader("window"),"","Edit inspection Form is closed on clicking (x) button");
		waitForExtJSAjaxComplete(5);

		gridChecker();
		waitForExtJSAjaxComplete(5);

		clickButton("Edit");
		waitForExtJSAjaxComplete(10);
		softAssert.assertTrue(getWarningDialogTextMsg().contains("Select only one template to edit"),"A warning message saying 'Cannot edit more than one Template details at a time'");

		closeUsingToolBarJS("Info");
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getWarningDialogTextMsg(),"","Pop up is closed on clicking (x) button");
		gridChecker();
		waitForExtJSAjaxComplete(5);

		clickButton("Delete");
		waitForExtJSAjaxComplete(5);
		softAssert.assertTrue(getWarningDialogTextMsg().contains("Please select an item"),"A warning message is to display Message saying 'Please select an Item'");

		closeUsingToolBarJS("Info");
		waitForExtJSAjaxComplete(5);
		softAssert.assertEquals(getWarningDialogTextMsg(),"","Pop up is closed on clicking (x) button");

		clickButton("Add");	
		waitForExtJSAjaxComplete(20);

		setReference(newInspTempRef);
		waitForExtJSAjaxComplete(5);

		clickLookupNewUI("x-window x-resizable-pinned","location");
		waitForExtJSAjaxComplete(20);

		setValueGridLookupWithFiltersNewUI("@class","x6-tree-arrows",inspectionTemplateLoaction,"Reference");
		waitForExtJSAjaxComplete(15);

		clickButtonInspectionTemplate("Cancel");
		closeUsingToolBarJS("Add Inspection Template");
		waitForExtJSAjaxComplete(5);

		softAssert.assertFalse(Grid.isRowInGridPresent(driver,newInspTempRef,"@class","x-grid3"),"No record is created on clicking cancel");

		clickButton("Add");	
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getWindowTitle("@class","x-resizable-pinned"),"Add Inspection Template","Create inspection Template form Opens");

		setReference(newInspTempRef);
		waitForExtJSAjaxComplete(5);

		clickLookupNewUI("x-window x-resizable-pinned","location");
		waitForExtJSAjaxComplete(20);

		setValueGridLookupWithFiltersNewUI("@class","x6-tree-arrows",inspectionTemplateLoaction,"Reference");
		waitForExtJSAjaxComplete(15);

		clickButtonInspectionTemplate("Save Changes");
		waitForExtJSAjaxComplete(5);

		editWinTitle = inspPObj.getInspectionWindowHeader("window");
		softAssert.assertEquals(editWinTitle,"Inspection Template ["+newInspTempRef+"]","From is Opened in edit mode on clicking savechanges");
		softAssert.assertTrue(isTabPresent("Inspection Points"),"'Inspection Points' tab is visible on saving inspection template");
		softAssert.assertTrue(isTabPresent("Security"),"'Security' tab is visible on saving inspection template");
		closeUsingToolBarJS(editWinTitle);

		waitForExtJSAjaxComplete(5);
		softAssert.assertTrue(Grid.isRowInGridPresent(driver,newInspTempRef,"@class","x-grid3"),"Grid is refreshed with newly added Template");

		Grid.checkRowInGriByTextValueAndClick(driver,newInspTempRef);
		clickButton("Edit");
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		clickTabinInspectionTemplate("Inspection Points");
		waitForExtJSAjaxComplete(5);

		clickButtonInspectionTemplate("Add");
		waitForExtJSAjaxComplete(10);

		softAssert.assertFalse(isAreaPresentInLocationLookup(),"On clicking add button List Shows buildings, floor and rooms");
		softAssert.assertFalse(setValueGridLookupWithFiltersNewUIWithValidation("@class","x6-window-default-closable","HDSite1","Reference"),"'Site' which is not pertains under Selected Template Location in general Section is not shown in 'Select locations' window");
		//TO DO: Location,Buildings, Sites are removed from the location List
		//softAssert.assertFalse(isSelectingInspectionPointOtherThanRoomPossible("@class", "x6-window-default-closable", "1aqaPreParBuildings", "Reference"),"System Not allows user to select Site, Building, Floor as inspection point");
		softAssert.assertTrue(isSelectingInspectionPointOtherThanRoomPossible("@class", "x6-window-default-closable", expectedInspPoints[0], "Reference"),"Rooms only allowed to select as inspection Point Location");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(setValueGridLookupWithFiltersNewUIWithValidation("@class","x6-window-default-closable","01aqaPreParRooms1","Reference"),"'Room' which is pertains under Selected Template Location in general Section is shown in 'Select locations' window, Room added to Inspection Point");
		waitForExtJSAjaxComplete(10);

		clickButtonInspectionTemplate("Save Changes");
		waitForExtJSAjaxComplete(15);

		softAssert.assertEquals(getInspectionPointsOfInspectionTemplate().toArray(),expectedInspPoints,"Inspection Template details updated successfully.");

		setReference(newInspTempRef+"Edit");
		waitForExtJSAjaxComplete(5);

		clickButtonInspectionTemplate("Save Changes");
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		softAssert.assertEquals(getFieldValue("Reference","input"), newInspTempRef+"Edit","Reference updated successfully");
		softAssert.assertTrue(inspPObj.isFielddisabled("Inspection Class","div"),"System not allowed to modify 'Inspection Class' ");
		softAssert.assertTrue(inspPObj.isFielddisabled("Inspection Process","div"),"System not allowed to modify 'Inspection Process' ");
		waitForExtJSAjaxComplete(5);

		uncheckFieldInInspectionTemplateWindow("Show on Portal");
		waitForExtJSAjaxComplete(5);

		clickButtonInspectionTemplate("Save Changes");
		waitForExtJSAjaxComplete(5);

		closeUsingToolBarJS(newInspTempRef+"Edit");
		waitForExtJSAjaxComplete(5);

		expandAdministration();
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Inspection");
		waitForExtJSAjaxComplete(20);

		clickXPath(XPATH_NEWINSPECTION);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		inspPObj.setLocationOfInspection(inspectionTemplateLoaction,"Reference");
		waitForExtJSAjaxComplete(5);

		inspPObj.clickLookup("Inspection Template:");
		waitForExtJSAjaxComplete(10);

		softAssert.assertFalse(setValueGridLookupWithFiltersWithValidation("@id",getXWindowId("Inspection Template"),newInspTempRef+"Edit","Reference")," "+newInspTempRef+"Edit' Template not visible while adding Inspections as 'show on portal' is unchecked");
		closeUsingToolBarJS("Inspection Template");

		closeInspectionTemlate();
		waitForExtJSAjaxComplete(5);

		clickAdministration();
		waitForExtJSAjaxComplete(20);

		expandModuleSettings();
		waitForExtJSAjaxComplete(20);

		clickInspectionTemplate();
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValueAndClick(driver,newInspTempRef+"Edit");
		clickButton("Edit");
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		clickLookupNewUI("x-window x-resizable-pinned","location");
		waitForExtJSAjaxComplete(20);

		setValueGridLookupWithFiltersNewUI("@class","x6-tree-arrows",inspectionTemplateLoaction,"Reference");
		waitForExtJSAjaxComplete(15);

		clickButtonInspectionTemplate("Save Changes");
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getInspectionPointsCount(),0,"Location changes saved successfully and inspection points gets clear from the grid");

		setDescription("Descrp");
		clickButtonInspectionTemplate("Save Changes");
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getFieldValue("Description","textarea"),"Descrp","Description is updated successfully");

		clickTabinInspectionTemplate("Label");
		waitForExtJSAjaxComplete(5);

		String labelValue = "labelTextEng";
		setLabelValue("English",labelValue,"Label");
		clickButtonInspectionTemplate("Save Changes");
		waitForExtJSAjaxComplete(15);

		clickTabinInspectionTemplate("Label");
		softAssert.assertEquals(getLabelValue("English","Label"),labelValue,"On clicking 'Save Changes' after editing label, Changes updated successfully");
		waitForExtJSAjaxComplete(15);

		clickTabinInspectionTemplate("Security");
		waitForExtJSAjaxComplete(5);

		checkFieldInInspectionTemplateWindow("Restricted","span");
		CallTemplatesPageObject calltemplatesPageObject = new CallTemplatesPageObject();
		calltemplatesPageObject.setAccount(configuration.getUserName());
		waitForExtJSAjaxComplete(15);

		clickButtonInspectionTemplate("Save Changes");
		waitForExtJSAjaxComplete(15);

		clickTabinInspectionTemplate("Security");
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isRowPresentInSecurityTab(getUserNameOfLoggedInUserFirstNameLastNameFormat(),"icon-account")," New record is added in security tab, changes are saved successfully");

		selectAccount(getUserNameOfLoggedInUserFirstNameLastNameFormat(),"icon-account");
		waitForExtJSAjaxComplete(5);
		clickRemoveButtonInInspTempWindow("remove");

		clickButtonInspectionTemplate("Save Changes");
		waitForExtJSAjaxComplete(15);

		clickTabinInspectionTemplate("Security");
		waitForExtJSAjaxComplete(5);

		softAssert.assertFalse(isRowPresentInSecurityTab(getUserNameOfLoggedInUserFirstNameLastNameFormat(),"icon-account"),"record is deleted from security tab, changes are saved successfully");

		closeUsingToolBarJS(newInspTempRef+"Edit");
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValueAndClick(driver,newInspTempRef+"Edit");
		clickButton("Delete");
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(getWarningDialogTextMsg().contains("Are you sure you wish to delete Inspection Template"),"A warning message is displayed saying 'Are you sure want to delete Template'.");
		clickOnDialogButton("Yes");
		waitForExtJSAjaxComplete(25);
		clickOnDialogButton("OK");
		waitForExtJSAjaxComplete(15);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, newInspTempRef+"Edit"),"On conforming Template is delete.");
		
		softAssert.assertAll();

		Reporter.log("Verify possible actions on default view of Inspection Template form, Edit Inspection Template Details", true);
	}

	/**
	 * Testcase Id : C101835,C101836,C101790,C101792,C101794,C101795,C101822
	 * Author      : MMA
	 * @throws IOException
	 */
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class)
	public void testVerifyAddEditDeleteInspectionTempRightsWithoutSystemIntegratorLicence()throws IOException{

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Verify Add/Modify Inspection Templates Right with the System Integrator License :C101835 "
				+ "Verify 'Delete Inspection Templates' right with system integrator Licence : C101836 "
				+ "Verify Inspections node in Administration Menu : C101790"
				+"Verify the behaviour of elements available on inspection Template form : C101792"
				+"Verify field validations of Add Inspections template form : C101794"
				+"Veify the description field validation : C101795"
				+ "Verify Copy Template functionality : C101822"+ " </span><br>", 
				true);

		String inspectionTemplateReference ="testTemp"+ getCurrentDate().substring(getCurrentDate().length()-6);
		String inspectionTemplateLoaction ="1aqaPreParSites";
		String ExistingRef = "C97445";
		int referenceLength =80;
		String inactiveSite = "aqaPreInactiveSiteRef";
		String expectedInspPoints[] = {"01aqaPreParRooms1","01aqaPreParRooms2"};

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testVerifyAddEditDeleteInspectionTempRightsWithoutSystemIntegratorLicence");
		InspectionPageObject inspObj = new InspectionPageObject();

		clickAdministration();
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getActiveModuleName(),"Administration","User navigates to web Administration form");
		expandMasterData();
		waitForExtJSAjaxComplete(5);

		clickAdministrationOptions("Master Data","Client Organizations");
		waitForExtJSAjaxComplete(10);

		SchedulerPageObject schObj = new SchedulerPageObject();
		schObj.expandParentNodeInTreeByTextValue("DEFAULT");
		waitForExtJSAjaxComplete(5);

		schObj.expandParentNodeInTreeByTextValue("MCS");
		waitForExtJSAjaxComplete(5);

		//To get the status class of Site
		inspObj.clickOnTreeNode(inactiveSite);
		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse((inspObj.getStatusClassOfSite().trim()).contains("Active"),"Inactive Site exist in application");
		waitForExtJSAjaxComplete(10);

		expandModuleSettings();
		waitForExtJSAjaxComplete(20);

		//C94779
		softAssert.assertTrue(isNodePresentUnderModuleSettings("Inspection"),"Inspection Node is displayed under module settings");
		softAssert.assertTrue(isIconPresentOnTreeNode("Administration","icon-inspection","Inspection"),"Icon is displayed for 'Inspection' node");
		softAssert.assertTrue(isIconPresentOnTreeNode("Administration","icon-inspection","Inspection Templates"),"Icon is displayed for 'Inspection Templates' node");

		clickInspectionTemplate();
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		//C94781
		softAssert.assertEquals(getXPanelWindowHeader("@class","x-form-item"),"Inspection Templates","Inspection Template form opens on right side administration Pane");
		softAssert.assertTrue(isButtonEnabled("Add"),"By Default 'Add' button is Enabled.");
		softAssert.assertTrue(isButtonEnabled("Edit"),"By Default 'Edit' button is Enabled.");
		softAssert.assertTrue(isButtonEnabled("Delete"),"By Default 'Delete' button is Enabled.");
		softAssert.assertTrue(isButtonEnabled("Copy"),"By Default 'Copy' button is Enabled.");

		clickButton("Edit");
		waitForExtJSAjaxComplete(5);
		softAssert.assertTrue(getWarningDialogTextMsg().contains("Please select an item"),"'Please select an item' alert message is displayed");
		clickOnDialogButton("OK");
		waitForExtJSAjaxComplete(5);

		clickButton("Delete");
		waitForExtJSAjaxComplete(5);
		softAssert.assertTrue(getWarningDialogTextMsg().contains("Please select an item"),"'Please select an item' alert message is displayed");
		clickOnDialogButton("OK");
		waitForExtJSAjaxComplete(5);

		clickButton("Copy");
		waitForExtJSAjaxComplete(5);
		softAssert.assertTrue(getWarningDialogTextMsg().contains("Select atleast one Inspection Template"),"'Please select an item' alert message is displayed");
		clickOnDialogButton("OK");
		waitForExtJSAjaxComplete(5);

		clickButton("Add");	
		softAssert.assertEquals(getWindowTitle("@class","x-resizable-pinned"),"Add Inspection Template","Create inspection Template form Opens");
		waitForExtJSAjaxComplete(5);

		clickTabinInspectionTemplate("Label");
		softAssert.assertEquals(inspObj.getInspectionWindowHeader("panel"),"Label","Label selected");
		softAssert.assertTrue(inspObj.isFieldMandatory("Location"),"Location field is mandatory");
		softAssert.assertEquals(getFieldValue("Location","input"),"","By Default Location field is empty");

		setReference(inspectionTemplateReference);
		waitForExtJSAjaxComplete(5);

		clickLookupNewUI("x-window x-resizable-pinned","location");
		waitForExtJSAjaxComplete(20);
		softAssert.assertFalse(setValueGridLookupWithFiltersNewUIWithValidation("@class", "x6-window-default-closable",inactiveSite,"Reference"),""+inactiveSite+" inactive site is not present");

		setValueGridLookupWithFiltersNewUI("@class","x6-tree-arrows",inspectionTemplateLoaction,"Reference");
		waitForExtJSAjaxComplete(15);

		softAssert.assertFalse(inspObj.isFieldMandatory("Description"),"Description field is not to be mandatory");
		softAssert.assertEquals(getFieldValue("Description","textarea"),"","By Default Description field is empty");

		setDescription(inspObj.generateRandomString(256));
		clickButtonInspectionTemplate("Save Changes");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(getWarningDialogTextMsg().contains("The Description can be only 255 characters long. Please shorten the Description"),"Decription field is not to allow unlimited characters");
		waitForExtJSAjaxComplete(5);
		clickOnDialogButton("OK");
		waitForExtJSAjaxComplete(5);

		setDescription(inspObj.generateRandomString(254));
		waitForExtJSAjaxComplete(15);

		clickButtonInspectionTemplate("Save Changes");
		waitForExtJSAjaxComplete(30);

		setReference(ExistingRef);
		waitForExtJSAjaxComplete(5);
		clickButtonInspectionTemplate("Save Changes");
		waitForExtJSAjaxComplete(15);

		//C94783
		softAssert.assertTrue(getWarningDialogTextMsg().contains("Template Reference must be unique"),"System allows only unique reference ");
		waitForExtJSAjaxComplete(10);
		clickOnDialogButton("OK");
		waitForExtJSAjaxComplete(5);

		setReference("");
		clickButtonInspectionTemplate("Save Changes");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(getWarningDialogTextMsg().contains("Reference cannot be empty"),"System allows only unique reference ");
		waitForExtJSAjaxComplete(5);
		clickOnDialogButton("OK");
		waitForExtJSAjaxComplete(5);

		setReference(inspObj.generateRandomString(referenceLength+1));
		softAssert.assertEquals(referenceLength,getFieldValue("Reference","input").length(),"Reference Field length is to validated");
		clickButtonInspectionTemplate("Cancel");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(inspObj.isFieldMandatory("Inspection Class"),"Inspection Class field is mandatory");
		softAssert.assertEquals("Damage Assessment",getFieldValue("Inspection Class","input"),"Inspection class Field prefilled with the Value 'Damage Assessment'");
		softAssert.assertTrue(inspObj.isFielddisabled("Inspection Class","div"),"Inspection class field is always read only");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(inspObj.isFieldMandatory("Inspection Process"),"Inspection Process field is mandatory");
		softAssert.assertEquals("Direct WO",getFieldValue("Inspection Process","input"),"Inspection Process field prefilled with the Value 'Direct work Order'");
		softAssert.assertTrue(inspObj.isFielddisabled("Inspection Process","div"),"Inspection Process field is always read only");

		//TODO: 8. Show on Portal field validations is already verified in testVerifyPossibleActionsOnInspectionTemplate

		//C94962 
		addInspectionPoint(expectedInspPoints[0]);
		waitForExtJSAjaxComplete(5);

		addInspectionPoint(expectedInspPoints[1]);
		waitForExtJSAjaxComplete(5);

		clickTabinInspectionTemplate("Label");
		waitForExtJSAjaxComplete(10);

		String labelValue = "Description";
		setLabelValue("English",labelValue,"Label");
		clickButtonInspectionTemplate("Save Changes");
		waitForExtJSAjaxComplete(15);

		clickTabinInspectionTemplate("Security");
		waitForExtJSAjaxComplete(5);

		checkFieldInInspectionTemplateWindow("Restricted","span");         
		CallTemplatesPageObject calltemplatesPageObject = new CallTemplatesPageObject();
		calltemplatesPageObject.setAccount(configuration.getUserName());
		waitForExtJSAjaxComplete(15);

		clickButtonInspectionTemplate("Save Changes");
		waitForExtJSAjaxComplete(15);

		closeInspectionTemlate();
		waitForExtJSAjaxComplete(5);

		Grid.checkRowInGriByTextValueAndClick(driver,inspectionTemplateReference);
		softAssert.assertTrue(Grid.isRowInGridChecked(driver,inspectionTemplateReference),"Template is selected");
		waitForExtJSAjaxComplete(5);

		clickButton("Copy");
		waitForExtJSAjaxComplete(15);

		softAssert.assertFalse(inspObj.isButtonEnabledInInspection("text()","Cancel"),"Cancel button is in disabled sate");
		softAssert.assertFalse(inspObj.isButtonEnabledInInspection("@class","icon-save"),"Save Changes button is in disabled sate");

		softAssert.assertEquals(getInspectionPointsOfInspectionTemplate().toArray(),expectedInspPoints,"All the added inspection points are displayed on copying the inspection template");
		waitForExtJSAjaxComplete(5);

		clickTabinInspectionTemplate("Label");
		softAssert.assertEquals(getLabelValue("English","Label"),labelValue,"Original Inspection Label value is also displayed on copying the Inspection Template");
		waitForExtJSAjaxComplete(5);

		clickTabinInspectionTemplate("Security");
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isRowPresentInSecurityTab(getUserNameOfLoggedInUserFirstNameLastNameFormat(),"icon-account"),"Restricted account in also displayed from original Inspection template");

		clickTabinInspectionTemplate("Inspection Points");
		waitForExtJSAjaxComplete(5);

		addInspectionPoint("01aqaPreParRooms3");
		waitForExtJSAjaxComplete(5);

		clickButtonInspectionTemplate("Save Changes");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(getWarningDialogTextMsg().contains("Template Reference must be unique"),"No new record is created even though changes are made in 'copied inspection template'unless reference is changed");

		clickOnDialogButton("OK");
		waitForExtJSAjaxComplete(5);

		setReference(inspectionTemplateReference+"copy");
		clickButtonInspectionTemplate("Save Changes");
		waitForExtJSAjaxComplete(5);

		closeUsingToolBarJS("Inspection Template");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver,inspectionTemplateReference,"@class","x-grid3"),inspectionTemplateReference+" original inspection template exists in system");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver,inspectionTemplateReference+"copy","@class","x-grid3"),inspectionTemplateReference+"copy"+" copied inspection template also exists in system");
		waitForExtJSAjaxComplete(5);

		//To check all the grow present in overview grid
		gridChecker();
		waitForExtJSAjaxComplete(5);

		//To uncheck all the rows present in overview grid
		gridChecker();
		waitForExtJSAjaxComplete(5);

		Grid.checkRowInGriByTextValueAndClick(driver,inspectionTemplateReference+"copy");

		clickButton("Delete");
		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("No");
		waitForExtJSAjaxComplete(30);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver,inspectionTemplateReference+"copy","@class","x-grid3"),inspectionTemplateReference+"copy"+" is not Deleted from the grid on clicking 'No'");

		clickButton("Delete");
		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("Yes");
		waitForExtJSAjaxComplete(10);

		clickOnDialogButton("OK");
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver,inspectionTemplateReference+"copy"),inspectionTemplateReference+"copy"+" is Deleted from the grid on clicking 'Yes'");

		//C94778 : logged in with no 'delete Inspection Template Rights' and with System Integrator License Uses
		navigateToMainPage("NoDeleteInspTempRightsWithSystemIntgLic","qwerty") ;
		waitForExtJSAjaxComplete(20);

		clickAdministration();
		waitForExtJSAjaxComplete(20);

		expandModuleSettings();
		waitForExtJSAjaxComplete(20);

		clickInspectionTemplate();
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(isButtonPresent("admsettings-modulesettings","Delete"),"Delete button is invisible");
		waitForExtJSAjaxComplete(5);

		//C94777 : logged in with no 'Inspection Template Rights' and with System Integrator License User 
		navigateToMainPage("NoInspTempRightsWithSysIntgLic","qwerty") ;
		waitForExtJSAjaxComplete(20);

		clickAdministration();
		waitForExtJSAjaxComplete(20);

		expandModuleSettings();
		waitForExtJSAjaxComplete(20);

		clickInspectionTemplate();
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		//Inspection Template node is disabled when the uses doesn't have both 'Add/Modify Inspection Template' and 'Delete Inspection Templates' Rights
		softAssert.assertTrue(isNodeDisabled("Inspection Templates")," 'Inspection Templates' Node is to be disabled ");

		softAssert.assertAll();

		Reporter.log("Verify Add/Modify/Delete Inspection Templates Right, Verify validations of edit Inspection form ", true);

	}
}
