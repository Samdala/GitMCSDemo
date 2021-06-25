package test.generalweb.inspections;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.DropDown;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import test.generalweb.reservation.ReservationPageObject;
import test.generalweb.rightvisibility.AdministrationPageObject;
import test.generalweb.scheduler.SchedulerPageObject;
import test.generalweb.workorders.WorkOrderPageObject;

public class InspectionCRUDTestSuite  extends InspectionPageObject 
{
	/**
	 * Testcase ID	:	C94810,C97445,C97446,C97442,C97450
	 * Author		:	ssa
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testCreateInspectionDetails()  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C97445:reate WO for Inspection Point when Locations are listed on the �Restriction/Locations� tab"
				+"C97446:Create WO for Maintenance Object when Related Object Type = Maintenance Object"
				+"C97442:Create WO for Inspection Point when no accounts or groups are specified on Security tab"
				+"C97450:Create WO for Maintenance Object when Locations are listed on the “Restrictions/Locations” tab"
				+ "C94810: Verify Create Inspection details from create Inspection wizard</span><br>", true);


		Reporter.log(" Verify Create Inspection details from create Inspection wizard<br>",true);

		String expectedInspClass = "Damage Assessment";

		String expectedInspProcess = "Direct WO";

		String locationValue = "1aqaPreParSites";

		String columnName = "Reference";

		String templateName = "C97445";
		
		String tempGroupName1="DoNotEditGroup1";
		
		String tempGroupName2="DoNotEditGroup2";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testCreateInspectionDetails");

		AdministrationPageObject objad = new AdministrationPageObject();

		InspectionAdministrationPageObject Inspobj = new InspectionAdministrationPageObject();
		

		clickAdministration();

		waitForExtJSAjaxComplete(20);

		objad.expandModuleSettings();

		waitForExtJSAjaxComplete(20);

		objad.expandInspection();

		objad.clickInspectionTemplates();

		waitForExtJSAjaxComplete(20);

		Inspobj.openInspectionTemplateDetails(templateName);

		waitForExtJSAjaxComplete(20);

		List<String> expectedInspectionPoints = Inspobj.getInspectionPointsOfInspectionTemplate();

		closeUsingToolBarJS("Inspection Template");

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Inspection");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWINSPECTION);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		String actualInspClass = getInspectionClass();

		softAssert.assertEquals(actualInspClass, expectedInspClass,"Inspection class Damage assessment is already prefilled");

		String actualInspProcess = getInspectionProcess();

		softAssert.assertEquals(actualInspProcess, expectedInspProcess,"Inspection process Direct WO is already prefilled");

		setLocationOfInspection(locationValue, columnName);

		waitForExtJSAjaxComplete(20);

		setTemplateOfInspection(templateName, columnName);

		waitForExtJSAjaxComplete(20);

		clickCreateButton();
		
		waitForExtJSAjaxComplete(10);

        resizeTheInspectionWindow();
		waitForExtJSAjaxComplete(10);

		String text = getInspectionID();

		softAssert.assertTrue(text.matches("[0-9]+"),"Inspection <ID> is present in edit Inspection Window Header");

		List<String> actualInspectionPoints = getUninspectedInspectionPoints();

		softAssert.assertTrue(actualInspectionPoints.containsAll(expectedInspectionPoints),"inspection points are displayed which are available in inspection template");
		
		
		//C97445,C97446
		setInspectionStatus("Ready for Inspection");
		
		waitForExtJSAjaxComplete(10);
		
		this.clickSaveButton();
		
		waitForExtJSAjaxComplete(10);
		
		setInspectionStatus("In Progress");
		
		waitForExtJSAjaxComplete(10);
		
		this.clickSaveButton();
		
		waitForExtJSAjaxComplete(10);
	
		selectInspectionPoint("1aqaPreParRooms1");
		
		waitForExtJSAjaxComplete(10);
		
		clickAddButtonInInspPoints();
		
		waitForExtJSAjaxComplete(10);
		
		List<String> expectedWOTemplates=Arrays.asList("WO1","WO2","WO3","WO7");
		
		List<String> actualWOTemplates= getWOTemplatesInInspection(tempGroupName1);
		
		softAssert.assertTrue(actualWOTemplates.containsAll(expectedWOTemplates),"WO Templates are displayed which are restricted to same locations");
		
		List<String> expNotAvailableWOTemp=Arrays.asList("WO4","WO5");
		
		List<String> actNotAvailableWOTemp= getWOTemplatesInInspection(tempGroupName1);
		
		softAssert.assertFalse(actNotAvailableWOTemp.containsAll(expNotAvailableWOTemp),"WO Templates are not displayed which are restricted to other locations");
		
		closeUsingToolBar(WO_TEMPLATE_WINDOW_XPATH);
		
		waitForExtJSAjaxComplete(10);
		
		selectMaintenanceObject("AQA_MO");
		
		waitForExtJSAjaxComplete(10);
		
		clickAddButtonInInspPoints();
		
		waitForExtJSAjaxComplete(10);

		List<String> expectedWOTemplatesForMO=Arrays.asList("MO_WO1","MO_WO2","MO_WO5");

		List<String> actualWOTemplatesForMO= getWOTemplatesInInspection(tempGroupName2);

		softAssert.assertTrue(actualWOTemplatesForMO.containsAll(expectedWOTemplatesForMO),"WO Templates are displayed which are restricted to same locations");

		List<String> expNotAvailableWOTemplatesForMO=Arrays.asList("MO_WO3","MO_WO4");

		List<String> actNotAvailableWOTemplatesForMO= getWOTemplatesInInspection(tempGroupName2);

		softAssert.assertFalse(actNotAvailableWOTemplatesForMO.containsAll(expNotAvailableWOTemplatesForMO),"WO Templates are not displayed which are restricted to other locations");

		softAssert.assertAll();

		Reporter.log("Verified Create Inspection details from create Inspection wizard <br>", true); 

	}


	/**
	 * Teastcase ID  : C101855,C101871,C101872,C101873,C101874,C101866
	 * Author        : mma
	 * @throws Exception
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testVerifyAddEditAndDeleteInspection() throws Exception{

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Verify Add,Edit and Delete Inspection: C101855,"
				+ "Verify the default view of inspection form wizard : C101871,"
				+ "Verify the validations of 'Inspection Class' of create inspection Wizard : C101872,"
				+ "Verify the validations of 'Inspection Process' of create inspection wizard form : C101873,"
				+ "Verify the Validations of 'Location' field of create inspection wizard form : C101874,"
				+ "Verify Default view of 'Inspection Points' Tab  : C101866 </span><br>", true);

		String inspectionClass = "Damage Assessment";
		String inspectionProcess = "Direct WO";
		String site = "1aqaPreParSites";
		String template = "C97445";
		String winHeader = "Create Inspection";
		String warningMsg = "Are you sure you want to delete Inspection";
		String inactiveSite = "aqaPreInactiveSiteRef";
		List<String> InspWizardFields = Arrays.asList("Inspection Class","Inspection Process","Inspection Location","Inspection Template");


		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testVerifyAddEditAndDeleteInspection");

		//C101874
		clickAdministration();
		waitForExtJSAjaxComplete(10);

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
		clickOnTreeNode(inactiveSite);
		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse((getStatusClassOfSite().trim()).contains("Active"),"Inactive Site exist in application");

		InspectionAdministrationPageObject InspAdObj = new InspectionAdministrationPageObject();
		InspAdObj.expandModuleSettings();
		waitForExtJSAjaxComplete(10);

		InspAdObj.clickInspectionTemplate();
		waitForExtJSAjaxComplete(25);

		InspAdObj.openInspectionTemplateDetails(template);
		waitForExtJSAjaxComplete(20);

		List<String> expectedInspectionPoints = InspAdObj.getInspectionPointsOfInspectionTemplate();

		List<String> formatedInspPoint = new ArrayList<String>();
		for(String inspPoint : expectedInspectionPoints){

			formatedInspPoint.add("Room: "+inspPoint+" (To do)");

		}

		closeUsingToolBarJS("Inspection Template");
		waitForExtJSAjaxComplete(20);
		setShowOnPortalValue("@class","x-grid3-viewport","Show on Portal","Yes");
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class","x-grid3-viewport",site,"Location");
		waitForExtJSAjaxComplete(20);

		//List of inspection template linked to filtered site
		List<String> expectedInspTemp = getValuesFromGridLookup("@realid","mogrid","Reference");
		waitForExtJSAjaxComplete(5);

		closeModule("Administration");

		expandAdministration();
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Inspection");
		waitForExtJSAjaxComplete(20);

		clickXPath(XPATH_INSPECTION);
		waitForExtJSAjaxComplete(20);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(20);

		//Create Inspection
		//C101872, C101871, C101873
		clickAddButton();
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getInspectionWindowHeader("panel"),winHeader,"Create inspection wizard is opened(Title is shown as 'Create Inspection')");
		softAssert.assertTrue(isFieldMandatory("Inspection Class"),"Inspection field is marked with mandatory marking");
		softAssert.assertTrue(isFielddisabled("Inspection Class","div"),"Field is Read only , user is not allowed to select the value of Inspection class ");
		softAssert.assertEquals(InspWizardFields,getInspectionWizardFields(),"elements Inspection form are displayed in specified format");
		softAssert.assertTrue(isButtonPresent("Create"),"'Create button is also present in Inspection Wizard'");
		waitForExtJSAjaxComplete(5);

		softAssert.assertFalse(isButtonEnabledInInspection("text()","Create"),"'Create' Button is in disabled state");
		softAssert.assertEquals(getInspectionClass(),inspectionClass,"Inspection class Damage Assessment is selected by default(prefilled with the value 'Damage Assessment')");
		softAssert.assertEquals(getInspectionProcess(),inspectionProcess,"Inspection process Damage Assessment is selected by default(prefilled with the value 'Direct WO')");
		softAssert.assertTrue(isFielddisabled("Inspection Process","div"),"Field is Read only , user is not allowed to select the value of Inspection Process ");
		waitForExtJSAjaxComplete(10);

		clickLookup("Inspection Location:");

		softAssert.assertFalse(setValueGridLookupWithFiltersNewUIWithValidation("@class", "x6-window-default-closable",inactiveSite,"Reference"),""+inactiveSite+" inactive site is not present");
		closeXWindow();

		setLocationOfInspection(site, "Reference");
		waitForExtJSAjaxComplete(10);

		softAssert.assertEquals(getInspectionLocation(),site,"Inspection Location is selected");

		//List of Inspection templates pertain to above selected location
		List<String> actualInspTemp = getListOfInspectionTemplates();
		waitForExtJSAjaxComplete(5);

		clickCANCELXwindow();

		softAssert.assertEqualsNoOrder(actualInspTemp.toArray(), expectedInspTemp.toArray(),"Templates pertain to above selected location are only display");
		setTemplateOfInspection(template,"Reference");
		softAssert.assertEquals(getInspectionTemplate(),template,"Inspection Template pertaining to selected location selected");
		clickCreateButton();
		waitForExtJSAjaxComplete(10);
		
		resizeTheInspectionWindow();
		waitForExtJSAjaxComplete(10);
		//Save Inspection
		softAssert.assertTrue(getInspectionID().matches("[0-9]+"),"Inspection saved into database");
		softAssert.assertEquals(getInspectionWindowHeader("panel"),"","Create inspection wizard is closed");
		softAssert.assertEquals(getInspectionWindowHeader("window"),"Inspection - "+getInspectionID(),"Title of form is to show Inspection-[Inspection Name]");
		softAssert.assertEquals(getInspectionStatus(),"Draft"," Default status is shown as Draft");
		setInspectionReference(template+(getCurrentDate().substring(getCurrentDate().length()-6))+"C94813Ed");
		softAssert.assertEquals(getInspectionLocation(),site,"Inspection Location is selected from creation page");
		softAssert.assertEquals(getInspectionClass(),inspectionClass,"Inspection class Damage Assessment is selected by default");
		softAssert.assertEquals(getInspectionProcess(),inspectionProcess,"Inspection class Damage Assessment is selected by default");
		waitForExtJSAjaxComplete(10);

		softAssert.assertEquals(getInspectorOfInspection(),getUserNameOfLoggedInUser(),"Inspection is the logged in user");
		waitForExtJSAjaxComplete(10);

		String  userName= getUserNameOfLoggedInUser().split(" ")[0];
		setReviewerOfInspection("Last Name",userName);	
		setPlannedDateOfInspection(getCurrentSystemDate());
		clickSaveButton();
		waitForExtJSAjaxComplete(10);
	
		softAssert.assertEquals(getInspectionWindowHeader("window"),"Inspection - "+getInspectionID(),"Inspection details is to save into database and form remains open");
		String inspID = getInspectionID();
		waitForExtJSAjaxComplete(20);

		//Save and close Inspection 
		String editRef = template+( getCurrentDate().substring(getCurrentDate().length()-6))+"C94813Edit";
		setInspectionReference(editRef);
		softAssert.assertEquals(getInspectionLocation(),site,"Inspection Location is selected from creation page");
		softAssert.assertEquals(getInspectionClass(),inspectionClass,"Inspection class Damage Assessment is selected by default");
		softAssert.assertEquals(getInspectionProcess(),inspectionProcess,"Inspection class Damage Assessment is selected by default");
		softAssert.assertEquals(getInspectorOfInspection(),getUserNameOfLoggedInUser(),"Inspection is the logged in user");
		setReviewerOfInspection("Last Name",userName);
		setPlannedDateOfInspection(getCurrentSystemDate());
		clickSaveAndCloseButton();
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getInspectionWindowHeader("window"),"","Inspection details saved into data base and form is to closed");
		waitForExtJSAjaxComplete(20);

		driver.navigate().refresh();
		expandAdministration();
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Inspection");
		waitForExtJSAjaxComplete(20);

		clickXPath(XPATH_INSPECTION);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		//Edit Inspection
		filterGridWithoutUsingMcsElement("@class","x-grid3-viewport",editRef,"Reference");
		waitForExtJSAjaxComplete(10);

		Grid.checkRowInGriByTextValueAndClick(driver,"x-tab-panel x-border-panel" ,inspID);
		waitForExtJSAjaxComplete(10);

		clickEditButton();
		waitForExtJSAjaxComplete(10);

		resizeTheInspectionWindow();
		waitForExtJSAjaxComplete(10);

		List<String> actualInspPoints = getInspectionPointsAvailableForInspection();

		//C101866
		softAssert.assertEqualsNoOrder(formatedInspPoint.toArray(), actualInspPoints.toArray(),"Inspection point is show in 'Room:Board Room(To Do)' format");
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isButtonDisplayed("control-right"),"'Next' button is desplayed");
		softAssert.assertTrue(isButtonDisplayed("arrow-repeat"),"'Undo' button is desplayed");
		softAssert.assertFalse(isButtonEnabledInInspection("@style","control-right"),"'Next' button is disabled by default");
		softAssert.assertFalse(isButtonEnabledInInspection("@style","arrow-repeat"),"'Undo' button is disabled by default");

		setInspectionReference(template);
		waitForExtJSAjaxComplete(10);

		//Close Inspection with out saving changes
		softAssert.assertEquals(getInspectionLocation(),site,"Inspection Location is selected from creation page");
		softAssert.assertEquals(getInspectionClass(),inspectionClass,"Inspection class Damage Assessment is selected by default");
		softAssert.assertEquals(getInspectionProcess(),inspectionProcess,"Inspection class Damage Assessment is selected by default");
		softAssert.assertEquals(getInspectorOfInspection(),getUserNameOfLoggedInUser(),"Inspection is the logged in user");
		setReviewerOfInspection("Last Name",userName);
		setPlannedDateOfInspection(getCurrentSystemDate());
		clickCloseButton();
		waitForExtJSAjaxComplete(20);

		//Delete Inspection
		softAssert.assertEquals(getInspectionWindowHeader("window"),"","Inspection form is to closed with out saving details into data base.");
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, editRef+"|"+inspID ,"@class","x-grid3"),"Inspection form is to closed with out saving details into data base.");

		Grid.checkRowInGriByTextValueAndClick(driver,"x-tab-panel x-border-panel" ,inspID);
		waitForExtJSAjaxComplete(10);

		clickDeleteButton();
		waitForExtJSAjaxComplete(10);

		getWarningDialogTextMsg();
		softAssert.assertTrue(getWarningDialogTextMsg().contains(warningMsg),"A warning message is to show 'Are you sure want to delete Inspection' .");

		clickButtonOnWarningWin("Yes");
		waitForExtJSAjaxComplete(25);

		clickButtonOnWarningWin("OK");
		waitForExtJSAjaxComplete(10);

		softAssert.assertFalse(Grid.isRowInGridPresent(driver,editRef+"|"+inspID ,"@class","x-grid3"),"on confirming Inspection is to deleted");

		softAssert.assertAll();

		Reporter.log("Verify Add,Edit and Delete Inspection", true);
	}
	
	/**
	 * Test Case ID: C94814,C95038,C109600
	 * Author : SRD
	 */		

	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testVerifyTheProcessAndWorkflowOfInspection() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Verify the work flow of Inspection : C94814"+ " </span><br>" 
				+ "Test: Verify Process the Inspection Points : C95038"+ " </span><br>"
				+ "Test: Verfiy that 'Add' button in 'Wo grid' is disabled when inspection is not in 'In progress' and 'In Review' status : C109600"+ " </span><br>", 
				true);

		String inspectionReference ="C94814C95038"+ getCurrentDate().substring(getCurrentDate().length()-6);
		String inspectionTemplateLoaction="1aqaPreParSites";
		String inspectionTemplate="C94814C95038";
		String inspectionPoints[] =new String[]{"01aqaPreParRooms1","01aqaPreParRooms2","01aqaPreParRooms3","01aqaPreParRooms4","01aqaPreParRooms5"};
		String inspector=getUserLastNameOfLoggedInUser();

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testVerifyTheProcessAndWorkflowOfInspection");

		//C94814 verifying Draft ,Ready for inspection ,InProgress status,paused status

		createInspection(inspectionTemplateLoaction,inspectionTemplate,inspectionReference);

		waitForExtJSAjaxComplete(100);

		softAssert.assertEquals(getInspectionStatus(),"Draft","Inspection is in Draft status");
		
		softAssert.assertFalse(isButtonEnabledInInspection("text()","Add")," Add button in 'WO grid' is disabled");

		waitForExtJSAjaxComplete(20);

		List<String> expectedDraft=Arrays.asList("Draft","Ready for Inspection","Cancelled");

		softAssert.assertEquals(DropDown.getComboValuesFromDropDownSelector(driver,getStatusFieldIdOfInspection()),expectedDraft,"Values of Draft status are verified");

		waitForExtJSAjaxComplete(20);

		clickSaveButton();

		waitForExtJSAjaxComplete(20);

		setInspectionStatus("Ready for Inspection");

		waitForExtJSAjaxComplete(20);

		clickSaveButton();
		waitForExtJSAjaxComplete(20);
		waitForMaskDisappear();
		
		softAssert.assertEquals(getInspectionStatus(),"Ready for Inspection","Inspection is in Ready for Inspection status");

		softAssert.assertFalse(isButtonEnabledInInspection("text()","Add")," Add button in 'WO grid' is disabled");

		List<String> expectedRfInspection=Arrays.asList("Ready for Inspection","In Progress","Draft");

		softAssert.assertEquals(DropDown.getComboValuesFromDropDownSelector(driver,getStatusFieldIdOfInspection()),expectedRfInspection,"Values of Ready for Inspection status are verified");

		clickSaveButton();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setInspectionStatus("In Progress");

		waitForExtJSAjaxComplete(20);

		clickSaveButton();

		waitForExtJSAjaxComplete(30);

		List<String> expectedInProgress=Arrays.asList("In Progress","Cancelled","Paused","Ready for Inspection","Submitted");

		softAssert.assertEquals(DropDown.getComboValuesFromDropDownSelector(driver,getStatusFieldIdOfInspection()),expectedInProgress,"Values of In Progress status are verified");

		waitForExtJSAjaxComplete(20);

		clickSaveButton();

		waitForExtJSAjaxComplete(20);

		setInspectionStatus("Paused");

		waitForExtJSAjaxComplete(20);

		clickSaveButton();

		try{

			this.waitForMaskDisappear();

		}catch(Exception e){


		}
		
		softAssert.assertEquals(getInspectionStatus(),"Paused","Inspection is in In Paused status");

		softAssert.assertFalse(isButtonEnabledInInspection("text()","Add")," Add button in 'WO grid' is disabled");

		List<String> expectedPaused=Arrays.asList("Paused","Cancelled","In Progress");

		softAssert.assertEquals(DropDown.getComboValuesFromDropDownSelector(driver,getStatusFieldIdOfInspection()),expectedPaused,"Values of Paused status are verified");

		waitForExtJSAjaxComplete(20);

		clickSaveButton();

		waitForExtJSAjaxComplete(20);

		setInspectionStatus("In Progress");

		waitForExtJSAjaxComplete(20);

		clickSaveButton();
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		//C95038 Verifying the  Process of Inspection Points

		for(String inspPoint:inspectionPoints)
		{

			selectInspectionPoint(inspPoint);

			waitForExtJSAjaxComplete(20);

			moveToInspected();

			waitForExtJSAjaxComplete(20);

			softAssert.assertEquals(isInspected(inspPoint),true,inspPoint+"is Inspected");


		}

		waitForExtJSAjaxComplete(10);

		clickSaveButton();

		waitForExtJSAjaxComplete(40);

		//C94814 verifying Submitted  status

		setInspectionStatus("Submitted");

		waitForExtJSAjaxComplete(20);

		clickSaveButton();

		waitForExtJSAjaxComplete(20);

		/*clickLookup("Reviewer");

		waitForExtJSAjaxComplete(20);

		setValueGridLookup(inspector);*/
		String  userName= getUserNameOfLoggedInUser().split(" ")[0];
		setReviewerOfInspection("Last Name",userName);

		waitForExtJSAjaxComplete(20);

		clickSaveButton();

		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getInspectionStatus(),"Submitted","Inspection is in In Submitted status");

		softAssert.assertFalse(isButtonEnabledInInspection("text()","Add")," Add button in 'WO grid' is disabled");

		List<String> expectedSubmitted=Arrays.asList("Submitted","Draft","Ready for Inspection","Archived","In Review");

		softAssert.assertEquals(DropDown.getComboValuesFromDropDownSelector(driver,getStatusFieldIdOfInspection()),expectedSubmitted,"Values of Submitted status are verified");

		waitForExtJSAjaxComplete(20);

		clickSaveButton();

		waitForExtJSAjaxComplete(40);

		setInspectionStatus("In Review");

		waitForExtJSAjaxComplete(20);

		clickSaveButton();

		waitForExtJSAjaxComplete(40);
		
		softAssert.assertEquals(getInspectionStatus(),"In Review","Inspection is in In Review status");

		softAssert.assertTrue(isButtonEnabledInInspection("text()","Add")," Add button in 'WO grid' is Enabled");

		List<String> expectedInReview=Arrays.asList("In Review","Reviewed");

		softAssert.assertEquals(DropDown.getComboValuesFromDropDownSelector(driver,getStatusFieldIdOfInspection()),expectedInReview,"Values of In Review status are verified");
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		
		for(String inspPoint:inspectionPoints)
		{

			selectInspectionPoint(inspPoint);

			waitForExtJSAjaxComplete(10);

			moveToReviewed();

			waitForExtJSAjaxComplete(10);

		}

		//C94814 verifying Reviewed  status

		setInspectionStatus("Reviewed");

		waitForExtJSAjaxComplete(20);

		clickSaveButton();

		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getInspectionStatus(),"Reviewed","Inspection is in InReviewed status");

		softAssert.assertFalse(isButtonEnabledInInspection("text()","Add")," Add button in 'WO grid' is disabled");

		softAssert.assertFalse(isDropDownEnabled("Status"),"This is end status.No Actions possible");

		waitForExtJSAjaxComplete(20);

		closeUsingToolBar(INSPECTION_WIN_XPATH);

		waitForExtJSAjaxComplete(20);

		//C94814 verifying Cancelled  status

		createInspection(inspectionTemplateLoaction,inspectionTemplate,inspectionReference);

		waitForExtJSAjaxComplete(20);

		setInspectionStatus("Ready for Inspection");

		waitForExtJSAjaxComplete(20);

		clickSaveButton();

		waitForExtJSAjaxComplete(20);

		setInspectionStatus("In Progress");

		waitForExtJSAjaxComplete(20);

		clickSaveButton();

		waitForExtJSAjaxComplete(20);

		setInspectionStatus("Cancelled");

		waitForExtJSAjaxComplete(20);

		clickSaveButton();

		try{

			this.waitForMaskDisappear();

		}catch(Exception e){


		} 
		
		softAssert.assertEquals(getInspectionStatus(),"Cancelled","Inspection is in Cancelled status");

		softAssert.assertFalse(isButtonEnabledInInspection("text()","Add")," Add button in 'WO grid' is disabled"); 

		softAssert.assertFalse(isDropDownEnabled("Status"),"This is end status.No Actions possible");

		waitForExtJSAjaxComplete(20);

		closeUsingToolBar(INSPECTION_WIN_XPATH);

		waitForExtJSAjaxComplete(25);

		//C94814 verifying Archived  status

		createInspection(inspectionTemplateLoaction,inspectionTemplate,inspectionReference);

		waitForExtJSAjaxComplete(20);

		setInspectionStatus("Ready for Inspection");

		waitForExtJSAjaxComplete(20);

		clickSaveButton();

		waitForExtJSAjaxComplete(20);

		setInspectionStatus("In Progress");

		waitForExtJSAjaxComplete(20);

		clickSaveButton();

		waitForExtJSAjaxComplete(20);

		for(String inspPoint:inspectionPoints)
		{

			selectInspectionPoint(inspPoint);

			waitForExtJSAjaxComplete(20);

			moveToInspected();

			waitForExtJSAjaxComplete(20);

			softAssert.assertEquals(isInspected(inspPoint),true,inspPoint+"is Inspected");


		}

		waitForExtJSAjaxComplete(20);

		setInspectionStatus("Submitted");

		waitForExtJSAjaxComplete(20);

		clickSaveButton();

		waitForExtJSAjaxComplete(20);

		setInspectionStatus("Archived");

		waitForExtJSAjaxComplete(20);

		clickSaveButton();

		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getInspectionStatus(),"Archived","Inspection is in Archived status");

		softAssert.assertFalse(isButtonEnabledInInspection("text()","Add")," Add button in 'WO grid' is disabled");

		softAssert.assertFalse(isDropDownEnabled("Status"),"This is end status.No Actions possible");
		waitForExtJSAjaxComplete(20);

		//C94823: MMA 
		String inspID = getInspectionID();
		clickCloseButton();

		waitForExtJSAjaxComplete(20);

		filterGridWithoutUsingMcsElement("@class","x-grid3-viewport",inspID,"ID");
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, inspID+"|"+"Archived" ,"@class","x-grid3"),"Inspection whose status is 'Archived' is present in database");
		InspectionAdministrationPageObject inspObj = new InspectionAdministrationPageObject();
		inspObj.openInspectionTemplateDetails(inspID);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isFielddisabled("Reference","input"),"Reference is read only when Inspection is in Archived status");
		softAssert.assertTrue(isFielddisabled("Inspection Class","div"),"Inspection Class is read only when Inspection is in Archived status");
		softAssert.assertTrue(isFielddisabled("Inspection Process","div"),"Inspection Process is read only when Inspection is in Archived status");
		softAssert.assertTrue(isFielddisabled("Reviewer","div"),"Reviewer is read only when Inspection is in Archived status");
		softAssert.assertTrue(isFielddisabled("Date Planned","div"),"Date Planned is read only when Inspection is in Archived status");
		softAssert.assertTrue(isFielddisabled("Date Started","div"),"Date Started is read only when Inspection is in Archived status");
		softAssert.assertTrue(isFielddisabled("Date Finished","div"),"Date Finished is read only when Inspection is in Archived status");
		softAssert.assertTrue(isFielddisabled("Inspection Location","div"),"Inspection Location is read only when Inspection is in Archived status");
		softAssert.assertTrue(isFielddisabled("Inspector","div"),"Inspector is read only when Inspection is in Archived status");
		softAssert.assertTrue(isFielddisabled("Status","div"),"Status is read only when Inspection is in Archived status");
		softAssert.assertEquals(getInspectionID(),inspID,"Inspection details form opened");

		softAssert.assertAll();	

		Reporter.log(" The work flow of Inspection and Process of the Inspection Points are verified successfully", true);


	}


	/**
	 * Testcase Id : C101860,C101861,C101862,C101863,C109598
	 * Author      : MMA
	 * @throws Exception
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testVerifyPossibleActionofInspection() throws Exception{

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Verify possible actions when Inspection Status is 'Paused', 'Submitted', 'In Review', 'Reviewed'': C94818, C94819, C94820, C94821, C109598 </span><br>", true);

		String inspectionReference ="PossibleAction"+ getCurrentDate().substring(getCurrentDate().length()-6);
		String inspectionTemplateLoaction="1aqaPreParSites";
		String inspectionTemplate="C94814C95038";
		String tempGroupName1="DoNotEditGroup1";
		String workOrderTemplateNormal="WO1";
		int random = (int)(Math.random() * 10)+18;
		String reference="Possible"+random;
		String priority = "Default Priority";
		String severity = "Default Severity";
		String nature = "Default Nature";

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testVerifyPossibleActionofInspection");

		createInspection(inspectionTemplateLoaction,inspectionTemplate,inspectionReference);
		waitForExtJSAjaxComplete(10);

		//C94818
		setInspectionStatus("Ready for Inspection");
		clickSaveButton();
		waitForExtJSAjaxComplete(20);

		setInspectionStatus("In Progress");
		clickSaveButton();
		waitForExtJSAjaxComplete(20);

		String inspID = getInspectionID();
		setInspectionStatus("Paused");
		clickSaveAndCloseButton();
		waitForExtJSAjaxComplete(20);

		driver.navigate().refresh();
		expandAdministration();
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Inspection");
		waitForExtJSAjaxComplete(20);

		clickXPath(XPATH_INSPECTION);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		filterGridWithoutUsingMcsElement("@class","x-grid3-viewport",inspectionReference,"Reference");
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, inspID+"|"+"Paused" ,"@class","x-grid3"),"Inspection whose status is 'Paused' is present in database");
		Grid.checkRowInGriByTextValueAndClick(driver,"x-tab-panel x-border-panel" ,inspID);
		clickDeleteButton();
		waitForExtJSAjaxComplete(25);

		clickButtonOnWarningWin("Yes");
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(getWarningDialogTextMsg().contains("Inspection can not be deleted when it is Paused status"),"Not allowed to delete Inspection");
		clickButtonOnWarningWin("OK");
		waitForExtJSAjaxComplete(10);

		clickEditButton();
		waitForExtJSAjaxComplete(10);
		
		resizeTheInspectionWindow();
		waitForExtJSAjaxComplete(10);

		softAssert.assertEquals(getInspectionID(),inspID,"Inspection details form opened whose status is Paused");
		waitForExtJSAjaxComplete(25);

		softAssert.assertEquals(getInspectorOfInspection(),getUserNameOfLoggedInUser(),"Inspection is the logged in user");
		softAssert.assertTrue(isFielddisabled("Reference","input"),"Reference is read only when Inspection is in paused status");
		softAssert.assertTrue(isFielddisabled("Inspection Class","div"),"Inspection Class is read only when Inspection is in paused status");
		softAssert.assertTrue(isFielddisabled("Inspection Process","div"),"Inspection Process is read only when Inspection is in paused status");
		softAssert.assertTrue(isFielddisabled("Reviewer","div"),"Reviewer is read only when Inspection is in paused status");
		softAssert.assertTrue(isFielddisabled("Date Planned","div"),"Date Planned is read only when Inspection is in paused status");
		softAssert.assertTrue(isFielddisabled("Date Started","div"),"Date Started is read only when Inspection is in paused status");
		softAssert.assertTrue(isFielddisabled("Date Finished","div"),"Date Finished is read only when Inspection is in paused status");
		softAssert.assertTrue(isFielddisabled("Inspection Location","div"),"Inspection Location is read only when Inspection is in paused status");
		softAssert.assertFalse(isFielddisabled("Inspector","div"),"All the fields except 'Inspector' are Read only.");
		setInspectionStatus("In Progress");
		waitForExtJSAjaxComplete(5);

		clickSaveButton();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(10);

		//C109598
		softAssert.assertEquals(getInspectionStatus(),"In Progress","Changes saved successfully, status is changed to 'In Progress'");
		selectInspectionPoint("1aqaPreParRooms1");
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isButtonEnabledInInspection("text()","Add"),"'Add' button is available in 'WO grid'when Inspection is in 'In Progress' status");

		clickAddButtonInInspPoints();
		waitForExtJSAjaxComplete(10);

		expandTemplateGroup(tempGroupName1);
		waitForExtJSAjaxComplete(20);

		WorkOrderPageObject woObj = new WorkOrderPageObject();
		woObj.selectTemplate(workOrderTemplateNormal);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(20);

		woObj.setReference(reference);
		waitForExtJSAjaxComplete(5);

		woObj.setPriority(priority);
		woObj.setSeverity(severity);
		woObj.setNature(nature);
		waitForExtJSAjaxComplete(5);

		woObj.clickSaveOrder();
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, reference,"@class","x-grid3"),"Changes saved successfully and WorkOrder is added");
		List<String> inspectionPoints = getUninspectedInspectionPoints();

		//C94819
		for(String inspPoint:inspectionPoints)
		{

			selectInspectionPoint(inspPoint);
			waitForExtJSAjaxComplete(20);

			moveToInspected();
			waitForExtJSAjaxComplete(20);

			softAssert.assertEquals(isInspected(inspPoint),true,inspPoint+"is Inspected");

		}

		waitForExtJSAjaxComplete(20);

		setInspectionStatus("Submitted");
		waitForExtJSAjaxComplete(20);

		clickSaveAndCloseButton();
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, inspID+"|"+"Submitted" ,"@class","x-grid3"),"Inspection whose status is 'Submitted' is present in database");
		InspectionAdministrationPageObject inspObj = new InspectionAdministrationPageObject();
		inspObj.openInspectionTemplateDetails(inspID);
		waitForExtJSAjaxComplete(20);
				
		softAssert.assertEquals(getInspectionID(),inspID,"Inspection details form opened");

		softAssert.assertTrue(isFielddisabled("Reference","input"),"Reference is read only when Inspection is in Submitted status");
		softAssert.assertTrue(isFielddisabled("Inspection Class","div"),"Inspection Class is read only when Inspection is in Submitted status");
		softAssert.assertTrue(isFielddisabled("Inspection Process","div"),"Inspection Process is read only when Inspection is in Submitted status");
		softAssert.assertTrue(isFielddisabled("Inspector","div"),"Inspector is read only when Inspection is in Submitted status");
		softAssert.assertTrue(isFielddisabled("Date Planned","div"),"Date Planned is read only when Inspection is in Submitted status");
		softAssert.assertTrue(isFielddisabled("Date Started","div"),"Date Started is read only when Inspection is in Submitted status");
		softAssert.assertTrue(isFielddisabled("Date Finished","div"),"Date Finished is read only when Inspection is in Submitted status");
		softAssert.assertTrue(isFielddisabled("Inspection Location","div"),"Inspection Location is read only when Inspection is in Submitted status");
		softAssert.assertFalse(isFielddisabled("Reviewer","div"),"All the fields except 'Reviewer' are Read only when Inspection is in Submitted status.");


		//C94820
		String  userName= getUserNameOfLoggedInUser().split(" ")[0];
		setReviewerOfInspection("Last Name",userName);	
		clickSaveButton();
		waitForExtJSAjaxComplete(20);

		setInspectionStatus("In Review");
		waitForExtJSAjaxComplete(20);

		clickSaveAndCloseButton();
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, inspID+"|"+"In Review" ,"@class","x-grid3"),"Inspection whose status is 'In Review' is present in database");
		inspObj.openInspectionTemplateDetails(inspID);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getInspectionID(),inspID,"Inspection details form opened");

		//C94821
		for(String inspPoint:inspectionPoints)
		{

			selectInspectionPoint(inspPoint);
			waitForExtJSAjaxComplete(10);

			moveToReviewed();
			waitForExtJSAjaxComplete(10);

			softAssert.assertEquals(isInspectionPointReviewed(inspPoint),true,inspPoint+"is Reviewed");
		}

		setInspectionStatus("Reviewed");
		waitForExtJSAjaxComplete(5);

		clickSaveAndCloseButton();
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, inspID+"|"+"Reviewed" ,"@class","x-grid3"),"Inspection whose status is 'Reviewed' is present in database");
		inspObj.openInspectionTemplateDetails(inspID);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getInspectionID(),inspID,"Inspection details form opened");

		softAssert.assertTrue(isFielddisabled("Reference","input"),"Reference is read only when Inspection is in Reviewed status");
		softAssert.assertTrue(isFielddisabled("Inspection Class","div"),"Inspection Class is read only when Inspection is in Reviewed status");
		softAssert.assertTrue(isFielddisabled("Inspection Process","div"),"Inspection Process is read only when Inspection is in Reviewed status");
		softAssert.assertTrue(isFielddisabled("Inspector","div"),"Inspector is read only when Inspection is in Reviewed status");
		softAssert.assertTrue(isFielddisabled("Date Planned","div"),"Date Planned is read only when Inspection is in Reviewed status");
		softAssert.assertTrue(isFielddisabled("Date Started","div"),"Date Started is read only when Inspection is in Reviewed status");
		softAssert.assertTrue(isFielddisabled("Date Finished","div"),"Date Finished is read only when Inspection is in Reviewed status");
		softAssert.assertTrue(isFielddisabled("Inspection Location","div"),"Inspection Location is read only when Inspection is in Reviewed status");
		softAssert.assertTrue(isFielddisabled("Reviewer","div"),"Reviewer is read only when Inspection is in Reviewed status");
		softAssert.assertTrue(isFielddisabled("Status","div"),"All the fields including 'Status' are Read only when Inspection is in Reviewed status.");

		softAssert.assertAll();
		Reporter.log("Verify possible actions when Inspection Status is 'Paused', 'Submitted', 'In Review', 'Reviewed", true);
	}
	
	/**
	 * Testcase ID	:	C94815,C94822
	 * Author		:	ssa
	 * @throws IOException 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testActionsWhenStatusDraftAndCancelled() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C94822: Verify possible actions when Inspection Status is cancelled"
				+ "C94815:Verify possible actions when Inspection Status is Draft</span><br>", true);


		String inspectionReference ="C97445"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String messageValue="Are you sure you want to delete Inspection";
		
		String inspector=getUserLastNameOfLoggedInUser();

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testActionsWhenStatusDraftAndCancelled");


		this.createInspection("1aqaPreParSites", "C97445", inspectionReference);

		waitForExtJSAjaxComplete(10);
		
		String inspectionId = getInspectionID();

		softAssert.assertTrue(inspectionId.matches("[0-9]+"),"Inspection <ID> is present in edit Inspection Window Header");
		
		clickSaveButton();
		
		waitForExtJSAjaxComplete(15);
		
		clickCloseButton();
		
		driver.navigate().refresh();
		
		waitForExtJSAjaxComplete(15);
		
		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Inspection");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_INSPECTION);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class","x-grid3-viewport",inspectionReference,"Reference");
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver,  inspectionId);
		
		clickEditButton();
		waitForExtJSAjaxComplete(10);
		
		resizeTheInspectionWindow();
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getInspectionStatus(),"Draft","Inspection is opened which is in satus Draft");
		
		List<String> filedNames=Arrays.asList("Inspection Class","Inspection Process");
		
		for(String filedName:filedNames)
		{
		//read only fields in edit inspection window
		softAssert.assertTrue(isFieldDisabled(filedName),""+filedName+" field is read only");
		}
		
		waitForExtJSAjaxComplete(20);
		
		setInspectorOfInspection("Last Name", inspector);
		
		waitForExtJSAjaxComplete(10);
		
		setReviewerOfInspection("Last Name",inspector);
		
		waitForExtJSAjaxComplete(10);
		
		//Change the status of inspection
		setInspectionStatus("Ready for Inspection");
		
		waitForExtJSAjaxComplete(10);
		
		clickCloseButton();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, inspectionId, "@realid", "mogrid"),"Inspection is displayed in the inspection point");	
		
		Grid.checkRowInGriByTextValueAndClick(driver,  inspectionId);
		
		waitForExtJSAjaxComplete(10);
		
		clickDeleteButton();
		
		softAssert.assertTrue(getWarningDialogTextMsg().contains(messageValue),"error message is displayed for deleting inspection which is status inprogress");
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertFalse(Grid.isRowInGridPresent(driver, inspectionId, "@realid", "mogrid"),"Inspection is not displayed in the inspection point");

		driver.navigate().refresh();
		waitForExtJSAjaxComplete(15);

		//C94822:
		this.createInspection("1aqaPreParSites", "C97445", inspectionReference);

		waitForExtJSAjaxComplete(10);
		
		String inspectionId1 = getInspectionID();

		softAssert.assertTrue(inspectionId1.matches("[0-9]+"),"Inspection <ID> is present in edit Inspection Window Header");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setInspectionStatus("Cancelled");
		
		waitForExtJSAjaxComplete(15);
		
		clickSaveButton();
		
		waitForExtJSAjaxComplete(15);
		
		clickCloseButton();
		
		driver.navigate().refresh();
		
		waitForExtJSAjaxComplete(15);
		
		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Inspection");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_INSPECTION);
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class","x-grid3-viewport",inspectionReference,"Reference");
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver,  inspectionId1);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getInspectionStatus(),"Cancelled","Inspection is opned which is in cancelled status");
		
		softAssert.assertAll();	
		
		Reporter.log("Possible actions when Inspection Status is Draft and cancelled verified successfully", true);
		
		}
	
	/**
	 * Testcase ID	:	C94816,C94817
	 * Author		:	ssa
	 * @throws IOException 
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testActionsWhenStatusInProgressAndReadyForInspection() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C94816:Verify possible actions when Inspection Status is Ready for Inspection"
				+ "C94817:Verify possible actions when Inspection Status is In Progress</span><br>", true);
		
		String inspector=getUserLastNameOfLoggedInUser();
		
		String inspectionReference ="C97445"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String messageValue="Are you sure you want to delete Inspection";
		
		String messageValue1="Inspection can not be deleted when it is In Progress status.";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testActionsWhenStatusInProgressAndReadyForInspection");

		
		//C94817:
		this.createInspection("1aqaPreParSites", "C97445", inspectionReference);
	
		waitForExtJSAjaxComplete(10);
		
		String inspectionId1= getInspectionID();
		
		setInspectionStatus("Ready for Inspection");
		
		waitForExtJSAjaxComplete(15);
		
		clickSaveButton();
		
		waitForExtJSAjaxComplete(15);
		
		setInspectionStatus("In Progress");
		
		waitForExtJSAjaxComplete(15);
		
		clickSaveButton();
		
		waitForExtJSAjaxComplete(15);
		
		clickCloseButton();
		
		driver.navigate().refresh();
		
		waitForExtJSAjaxComplete(15);
		
		expandAdministration();
	
		waitForExtJSAjaxComplete(20);
	
		expandSubMainMenu("Inspection");
	
		waitForExtJSAjaxComplete(20);
	
		waitAndClick(XPATH_INSPECTION);
		
		waitForExtJSAjaxComplete(10);
		
		filterGridWithoutUsingMcsElement("@class","x-grid3-viewport",inspectionReference,"Reference");
		waitForExtJSAjaxComplete(10);

		Grid.checkRowInGriByTextValueAndClick(driver,  inspectionId1);
		
		clickEditButton();
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getInspectionStatus(),"In Progress","Inspection is opened which is in In Progress status");
		
		setInspectorOfInspection("Last Name", inspector);
		
		waitForExtJSAjaxComplete(10);
		
		setInspectionStatus("Paused");

		waitForExtJSAjaxComplete(10);

		resizeTheInspectionWindow();
		waitForExtJSAjaxComplete(10);

		clickSaveButton();
		
		waitForExtJSAjaxComplete(15);
		
		setInspectionStatus("Cancelled");
		
		waitForExtJSAjaxComplete(15);
		
		clickSaveButton();
		
		waitForExtJSAjaxComplete(15);
		
		softAssert.assertEquals(getInspectionStatus(),"Cancelled","Inspection is opened which is in Cancelled status");
		
		clickCloseButton();
		
		
		waitForExtJSAjaxComplete(15);
		
		//C94816:
		this.createInspection("1aqaPreParSites", "C97445", inspectionReference);
		
		String inspectionId2 = getInspectionID();
		
		waitForExtJSAjaxComplete(15);
		
		setInspectionStatus("Ready for Inspection");
		
		waitForExtJSAjaxComplete(15);
		
		clickSaveButton();
		
		waitForExtJSAjaxComplete(15);
		
		List<String> filedNames=Arrays.asList("Inspection Class","Inspection Process","Date Started","Date Finished");
		
		for(String filedName:filedNames)
		{
		//read only fields in edit inspection window
		softAssert.assertTrue(isFieldDisabled(filedName),""+filedName+" field is read only");
		}
		
		softAssert.assertFalse(this.isDropDownEnabled("Inspection Location:"),"inspection location field is read only");
		
		List<String> inspectionPoints=Arrays.asList("01aqaPreParRooms1","01aqaPreParRooms2","01aqaPreParRooms3");


		for(String inspPoint:inspectionPoints)
				{

					selectInspectionPoint(inspPoint);

					waitForExtJSAjaxComplete(10);
					
					softAssert.assertTrue(isInspectionPointStatusTODO(inspPoint),"inspection point is in TODO status");

				}

		waitForExtJSAjaxComplete(10);
		
		driver.navigate().refresh();
		
		waitForExtJSAjaxComplete(15);
		
		expandAdministration();
	
		waitForExtJSAjaxComplete(20);
	
		expandSubMainMenu("Inspection");
	
		waitForExtJSAjaxComplete(20);
	
		waitAndClick(XPATH_INSPECTION);
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElement("@class","x-grid3-viewport",inspectionReference,"Reference");
		waitForExtJSAjaxComplete(10);
		
		InspectionAdministrationPageObject Inspobj = new InspectionAdministrationPageObject();
		Inspobj.openInspectionTemplateDetails(inspectionId2);
		waitForExtJSAjaxComplete(10);
		selectInspectionPoint("01aqaPreParRooms3");
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(isWorkOrderGridEmpty(),"Work order is not added to the inspection points");
		
		resizeTheInspectionWindow();
		waitForExtJSAjaxComplete(10);
		
		clickCloseButton();
		waitForExtJSAjaxComplete(10);
		filterGridWithoutUsingMcsElement("@class","x-grid3-viewport",inspectionReference,"Reference");
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver,  inspectionId2);
		
		waitForExtJSAjaxComplete(10);
		
		clickDeleteButton();
		
		softAssert.assertTrue(getWarningDialogTextMsg().contains(messageValue),"error message is displayed for deleting inspection which is status inprogress");
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(Grid.isRowInGridPresent(driver, inspectionId2, "@realid", "mogrid"),"Inspection is deleted");
		
		Reporter.log("inspection is deleted when all the inspection Points status is To Do and No work orders Linked to it <br>", true);		
		
		this.createInspection("1aqaPreParSites", "C97445", inspectionReference);
		
		waitForExtJSAjaxComplete(10);
		
		String inspectionId3 = getInspectionID();
		
		waitForExtJSAjaxComplete(10);
		
		setInspectionStatus("Ready for Inspection");
		
		waitForExtJSAjaxComplete(10);
		
		clickSaveButton();
		
		waitForExtJSAjaxComplete(10);
		
		setInspectionStatus("In Progress");
		
		clickSaveButton();
		waitForExtJSAjaxComplete(10);
		
		try {
			waitForMaskDisappear();
		}
		catch(Exception E){
			
		}
		selectInspectionPoint("1aqaPreParRooms1");

		waitForExtJSAjaxComplete(10);
		
		selectMaintenanceObject("AQA_MO");
		waitForExtJSAjaxComplete(20);
		clickAddButtonInInspPoints();

		waitForExtJSAjaxComplete(10);
		
		WorkOrderPageObject wrkPageObject=new WorkOrderPageObject();
		
	
		String severity = "1preSeverityRef";
		String priority = "1prePriorityRef";
		
		String reference = "ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		String nature = "Default Nature";
		
		expandTemplateGroup("DoNotEditGroup2");
		
		waitForExtJSAjaxComplete(20);
		
		wrkPageObject.selectTemplate("MO_WO1");
		
		wrkPageObject.setReference(reference);
		
		waitForExtJSAjaxComplete(20);
		
		wrkPageObject.setPriority(priority);
		
		waitForExtJSAjaxComplete(20);
	
		wrkPageObject.setSeverity(severity);
		
		waitForExtJSAjaxComplete(20);
		
		wrkPageObject.setNature(nature);
	
		waitForExtJSAjaxComplete(5);
		
		wrkPageObject.clickSaveOrder();
		
		waitForExtJSAjaxComplete(20);

		this.clickSaveButton();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, reference, "@realid", "mogrid"),"Work order is Added to the inspection point");	

		clickCloseButton();
		waitForExtJSAjaxComplete(10);

//		wrkPageObject.setDetailsTabCollapsedMode();
//		waitForExtJSAjaxComplete(10);
		
		filterGridWithoutUsingMcsElement("@class","x-grid3-viewport",inspectionId3,"ID");
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver,  inspectionId3);
		waitForExtJSAjaxComplete(10);
		
		clickEditButton();
		waitForExtJSAjaxComplete(20);
		
		maximizeInspectionWindow();
		waitForExtJSAjaxComplete(20);
		
		clickToggleButtononWOPicturetab();	
		waitForExtJSAjaxComplete(5);
		
		Inspobj.openInspectionTemplateDetails(reference);
		
		waitForExtJSAjaxComplete(10);
		
		wrkPageObject.clickObjectTab();
		
		waitForExtJSAjaxComplete(15);
		
		softAssert.assertTrue(wrkPageObject.isRowInGridPresent(driver,"AQA_MO","@class","x-treegrid-root-table"),"Maintenance Object is added");
		
		closeUsingToolBar(wrkPageObject.EDIT_WO_WINDOW_CLASS_XPATH);
		
		waitForExtJSAjaxComplete(15);
		
		moveToInspected();
		
		waitForExtJSAjaxComplete(15);
		
		clickSaveButton();
		waitForExtJSAjaxComplete(15);
		
		try{
			waitForMaskDisappear();
		}
		catch(Exception E){
			
		}
		softAssert.assertTrue(isInspected("1aqaPreParRooms1"),"inspection point are moved to inspected");
		
		this.clickCloseButton();
		
		waitForExtJSAjaxComplete(15);
		
		driver.navigate().refresh();
		
		waitForExtJSAjaxComplete(15);
		
		expandAdministration();
	
		waitForExtJSAjaxComplete(20);
	
		expandSubMainMenu("Inspection");
	
		waitForExtJSAjaxComplete(20);
	
		waitAndClick(XPATH_INSPECTION);
		
		waitForExtJSAjaxComplete(10);
		
		filterGridWithoutUsingMcsElement("@class","x-grid3-viewport",inspectionReference,"Reference");
		waitForExtJSAjaxComplete(10);	
		Grid.checkRowInGriByTextValueAndClick(driver,  inspectionId3);
		waitForExtJSAjaxComplete(10);
		
		clickDeleteButton();
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(getWarningDialogTextMsg().contains(messageValue),"error message is displayed for deleting inspection which is status inprogress and Work orders are linked to it");
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(getWarningDialogTextMsg().contains(messageValue1),"error message is displayed for deleting inspection which is status inprogress and work orders are linked to it");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, inspectionId3, "@realid", "mogrid"),"Inspection is not deleted which is linked with work orders");
		
		softAssert.assertAll();	
		Reporter.log("Verified inspection details when it is status ready for inspection and In Progress <br>", true);
	}
	
	/**
	 * Test Case ID: C124251
	 * Author : SRD
	 * @throws Exception
	 */		

	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testVerifyValidationsOfEditInspectionform() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Verfiy Validations Of Edit Inspection form: C124251"+ " </span><br>" ,true);

		String inspectionClass = "Damage Assessment";
		String inspectionProcess = "Direct WO";
		String site = "1aqaPreParSites";
		String template = "C94814C95038";
		String inspector=getUserLastNameOfLoggedInUser();
		String warningMsg="Please provide valid input for Reference field";
		int referenceLength =80;
		String fieldLengthValidationReference=generateRandomString(referenceLength+1);

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testVerifyValidationsOfEditInspectionform");

		expandAdministration();
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Inspection");
		waitForExtJSAjaxComplete(20);

		clickXPath(XPATH_NEWINSPECTION);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setLocationOfInspection(site, "Reference");

		waitForExtJSAjaxComplete(20);

		setTemplateOfInspection(template,"Reference");

		waitForExtJSAjaxComplete(20);

		clickCreateButton();

		waitForExtJSAjaxComplete(10);

		try{

			this.waitForMaskDisappear();

		}catch(Exception e){


		}


		//validating Reference 
		
		resizeTheInspectionWindow();
		waitForExtJSAjaxComplete(10);
		
		clearFieldValueBasedOnLabel("Reference");

		waitForExtJSAjaxComplete(10);

		clickSaveButton();

		softAssert.assertTrue(getWarningDialogTextMsg().contains(warningMsg),"System is to prompt the user saying that Reference must not be Empty");

		clickOnDialogButton("OK");

		setInspectionReference(fieldLengthValidationReference);

		waitForExtJSAjaxComplete(10);

		softAssert.assertEquals(referenceLength,getInspectionReference().length(),"Reference field accepts upto 80 characters only");

		waitForExtJSAjaxComplete(10);

		//validating Location,InspectionClass,InspectionProcess

		softAssert.assertEquals(getInspectionLocation(),site,"Inspection location is to take from Inspection wizard(i.e field is prefilled)");

		softAssert.assertEquals(getInspectionClass(),inspectionClass,"Inspection Class is to take from Inspection wizard (i.e. Field is prefilled)and is read only on edit inspection form");

		softAssert.assertEquals(getInspectionProcess(),inspectionProcess,"Prefilled with the value Direct Work Orders");

		//validating InspectorOfInspection,ReviewerOfInspection

		softAssert.assertEquals(getInspectorOfInspection(),getUserNameOfLoggedInUser(),"Inspector field prefilled with value of Inspection Created person");

		setInspectorOfInspection("Last Name","NoInspTempRights");

		waitForExtJSAjaxComplete(10);

		softAssert.assertFalse(getInspectorOfInspection().contains(inspector),"Employee/Contact is selected");

		setReviewerOfInspection("Last Name",inspector);

		waitForExtJSAjaxComplete(10);

		softAssert.assertEquals(getReviewerOfInspection(),getUserNameOfLoggedInUserFirstNameLastNameFormat(),"Employee/Contact is selected");

		//validating Date Fields

		softAssert.assertTrue(getPlannedDateOfInspection().contains(getCurrentSystemDate()),"Default value is to show the Current date");

		setPlannedDateOfInspection(getFutureDate(1));

		waitForExtJSAjaxComplete(10);

		softAssert.assertEquals(getPlannedDateOfInspection(),getFutureDate(1),"Date is selected");

		softAssert.assertTrue(isFielddisabled("Date Finished","div"),"Field is diabled and empty by default");

		waitForExtJSAjaxComplete(10);

		softAssert.assertAll();

		Reporter.log("Verified Validations Of Edit Inspectionform <br>", true); 

	}
	
	/**
	 * Testcase ID :C97443 
	 * Author : ssa
	 * @throws IOException
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class)
	public void testCreateWOWhenAccountAndGroupSpecified() throws IOException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
						+ "C97443:Create WO for Inspection Point when account and group are specified on Security tab",true);

		String tempGroupName1 = "DoNotEditGroup1";
		String tempName = "WO6";
		String userID = "AQA_GroupIsAdded";
		String password = "qwerty";

		AdministrationPageObject objad = new AdministrationPageObject();

		WorkOrderPageObject workOrders = new WorkOrderPageObject();
		
		InspectionAdministrationPageObject Inspobj = new InspectionAdministrationPageObject();

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testCreateWOWhenAccountAndGroupSpecified");

		String inspectionReference = "C97443"+ getCurrentDate().substring(getCurrentDate().length() - 6);

		Inspobj.navigateToMainPage(userID, password);

		waitForExtJSAjaxComplete(10);

		createInspection("1aqaPreParSites", "C97445", inspectionReference);

		setInspectionStatus("Ready for Inspection");

		waitForExtJSAjaxComplete(10);

		clickSaveButton();

		waitForExtJSAjaxComplete(10);

		setInspectionStatus("In Progress");

		waitForExtJSAjaxComplete(10);

		this.clickSaveButton();

		waitForExtJSAjaxComplete(10);

		selectInspectionPoint("1aqaPreParRooms1");

		waitForExtJSAjaxComplete(10);

		clickAddButtonInInspPoints();

		waitForExtJSAjaxComplete(10);

		List<String> expectedWOTemplates = Arrays.asList("WO1", "WO2", "WO3");

		List<String> actualWOTemplates = getWOTemplatesInInspection(tempGroupName1);

		softAssert.assertTrue(actualWOTemplates.containsAll(expectedWOTemplates),"WO Templates are displayed which are restricted to account");

		List<String> expNotAvailableWOTemp = Arrays.asList("WO4", "WO5", "WO6");

		List<String> actNotAvailableWOTemp = getWOTemplatesInInspection(tempGroupName1);

		softAssert.assertFalse(actNotAvailableWOTemp.containsAll(expNotAvailableWOTemp),"WO Templates are not displayed which are restricted to account");

		closeUsingToolBar(WO_TEMPLATE_WINDOW_XPATH);

		driver.navigate().refresh();

		waitForExtJSAjaxComplete(20);

		clickAdministration();

		waitForExtJSAjaxComplete(20);

		objad.expandModuleSettings();

		waitForExtJSAjaxComplete(20);

		objad.expandWorkorders();

		waitForExtJSAjaxComplete(20);

		objad.clickWorkorderTemplates();

		waitForExtJSAjaxComplete(20);

		workOrders.expandTemplateGroup(tempGroupName1);

		waitForExtJSAjaxComplete(20);

		workOrders.selectWOTemplate(tempName);

		waitForExtJSAjaxComplete(20);

		workOrders.clickSecurityTab();

		waitForExtJSAjaxComplete(20);

		workOrders.clickAddGroupButton("auto group");

		waitForExtJSAjaxComplete(20);

		workOrders.clickSaveChangesButton();

		waitForExtJSAjaxComplete(20);

		driver.navigate().refresh();

		createInspection("1aqaPreParSites", "C97445", inspectionReference);

		setInspectionStatus("Ready for Inspection");

		waitForExtJSAjaxComplete(10);

		clickSaveButton();

		waitForExtJSAjaxComplete(10);

		setInspectionStatus("In Progress");

		waitForExtJSAjaxComplete(10);

		clickSaveButton();

		waitForExtJSAjaxComplete(10);

		selectInspectionPoint("1aqaPreParRooms1");

		waitForExtJSAjaxComplete(10);

		clickAddButtonInInspPoints();

		waitForExtJSAjaxComplete(10);

		List<String> postExpectedWOTemplates = Arrays.asList("WO1", "WO2","WO3", "WO6");

		List<String> postActualWOTemplates = getWOTemplatesInInspection(tempGroupName1);

		softAssert.assertTrue(postActualWOTemplates.containsAll(postExpectedWOTemplates),"WO Templates are displayed which are restricted to Group");

		List<String> postExpNotAvailableWOTemp = Arrays.asList("WO4", "WO5");

		List<String> postActNotAvailableWOTemp = getWOTemplatesInInspection(tempGroupName1);

		softAssert.assertFalse(postActNotAvailableWOTemp.containsAll(postExpNotAvailableWOTemp),"WO Templates are not displayed which are restricted to Group");

		softAssert.assertAll();

		Reporter.log("Verified WO for Inspection Point when account and group are specified on Security tab <br>",true);

	}
	
	/**
	 * Test Case ID: C122308,C109601,C120819,C120844,C120841
	 * Author : SRD
	 * @throws Exception
	 */		

	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testVerifyNextUndoandAddbuttonsfunctionalitywhileinspectionPointsProcessing() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Verfiy that 'Add' button in 'Wo grid' is disabled when inspector is not current user: C122308"+ " </span><br>"
				+ "Test: Verfiy that 'Add' button in 'Wo grid' is enabled when inspection is in 'In progress' status and inspector is current user:C122309"+ " </span><br>"
				+ "Test: Verify Next and Undo buttons functionality while inspection Points Processing : C120819"+ " </span><br>"
				+ "Test: Verify preloading Rooms under Inspection Point by default and navigation to Rooms : C120844"+ " </span><br>"
				+ "Test: Verify preloading active maintenance objects under Inspection Point by default and navigation to Maintenance objects : C120841"+ " </span><br>",
				true);

		String inspectionReference ="C109599"+ getCurrentDate().substring(getCurrentDate().length()-6);
		String inspector=getUserLastNameOfLoggedInUser();

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testVerifyNextUndoandAddbuttonsfunctionalitywhileinspectionPointsProcessing");


		this.createInspection("1aqaPreParSites", "C94814C95038",inspectionReference);

		waitForExtJSAjaxComplete(10);

		String inspectionId1= getInspectionID();

		setInspectionStatus("Ready for Inspection");

		waitForExtJSAjaxComplete(15);

		clickSaveButton();

		waitForExtJSAjaxComplete(15);

		setInspectionStatus("In Progress");

		waitForExtJSAjaxComplete(15);

		clickSaveButton();

		waitForExtJSAjaxComplete(25);

		clickCloseButton();

		driver.navigate().refresh();

		waitForExtJSAjaxComplete(15);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Inspection");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_INSPECTION);

		waitForExtJSAjaxComplete(10);
		
		filterGridWithoutUsingMcsElement("@class","x-grid3-viewport",inspectionReference,"Reference");
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver,  inspectionId1);

		clickEditButton();

		waitForExtJSAjaxComplete(20);

		try{

			this.waitForMaskDisappear();

		}catch(Exception e){


		}

		//C109601 Verify that 'Add' button in 'Wo grid' is enabled when inspection is in 'In progress' status and inspector is current user

		resizeTheInspectionWindow();
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(getInspectionStatus(),"In Progress","Inspection is opened which is in In Progress status");

		softAssert.assertTrue(isButtonEnabledInInspection("text()","Add")," Add button in 'WO grid' is Enabled");

		setInspectorOfInspection("Last Name","NoInspTempRights");

		waitForExtJSAjaxComplete(10);

		clickSaveButton();

		try{

			this.waitForMaskDisappear();

		}catch(Exception e){


		}
		waitForExtJSAjaxComplete(10);
		//C109599 Verify that 'Add' button in 'Wo grid' is disabled when inspector is not current user

		softAssert.assertFalse(isButtonEnabledInInspection("text()","Add")," Add button in 'WO grid' is disabled inspector of the inspection is not current user");

		setInspectorOfInspection("Last Name", inspector);

		waitForExtJSAjaxComplete(10);

		clickSaveButton();

		waitForExtJSAjaxComplete(20);

		//C95037 Verify Next and Undo buttons functionality while inspection Points Processing

		selectInspectionPoint("01aqaPreParRooms1");
		waitForExtJSAjaxComplete(20);
		
		maximizeInspectionWindow();
		waitForExtJSAjaxComplete(5);
		
		clickToggleButtononWOPicturetab();	
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isWOGridContainsWOOfRoom("01aqaPreParRooms1"),"Room selected and work orders pertain to that room displayed on right side of Inspection Points Pane");
		restoreInspectionWindow();
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isButtonEnabledInInspection("@style","control-right")," Next button is Enabled");

		softAssert.assertTrue(isButtonEnabledInInspection("@style","arrow-repeat")," Previous button is Enabled");

		softAssert.assertEquals(getToolTipOfButtonByMouseHover("@style","control-right"),"Move to Inspected","Mouse over text is displayed correctly for the Next Button.");

		softAssert.assertEquals(getToolTipOfButtonByMouseHover("@style","arrow-repeat"),"Move to Skipped","Mouse over text is displayed correctly for the Previous Button.");

		moveToInspected();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isInspectionPointStatusINSPECTED("01aqaPreParRooms1"),"Inspection Point status moved to next status (From To do --->Inspected)");

		waitForExtJSAjaxComplete(10);

		clickUndoButton();

		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isInspectionPointStatusTODO("01aqaPreParRooms1"),"Inspection Point status moved to previous status i.e.To Do");

		try{

			this.waitForMaskDisappear();

		}catch(Exception e){


		}

		//C95035

		selectInspectionPoint("01aqaPreParRooms1");

		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isWOGridopen(),"Work order grid opens on the right side of Inspection Points pane");

		clickToggleButtononWOPicturetab();

		softAssert.assertTrue(isWOGridContainsWOOfFinishedAndArchivedStatus(),"Work orders(other than finished and Archived) and pertain to selected location(Inspection Point Location) are to be displayed");

		softAssert.assertTrue(getToolTipOfInspectionPointByMouseHover("01aqaPreParRooms1").contains("Inspected by:"+" "+inspector),"Inspection details like Inspected By is to be displayed.");

		softAssert.assertTrue(getToolTipOfInspectionPointByMouseHover("01aqaPreParRooms1").contains("Reviewed by:"),"Inspection details like Reviewed By is to be displayed.");

		
		waitForExtJSAjaxComplete(10);

		//C95036

		selectInspectionPoint("01aqaPreParRooms1");

		waitForExtJSAjaxComplete(10);

		List<String> expectedMOs=Arrays.asList("AQA_MO1","AQA_MO");

		List<String> actualMOs= getMOofInspectionPoint("01aqaPreParRooms1");

		List<String> MOListCopy = new ArrayList<String>(actualMOs);

		Collections.sort(MOListCopy);

		softAssert.assertEquals(actualMOs,MOListCopy,"All maintenance objects are to be displayed in sorting order");

		softAssert.assertTrue(actualMOs.containsAll(expectedMOs),"Active Maintenance objects pertain to selected room are to be displayed as sub node of Rooms");

		waitForExtJSAjaxComplete(20);

		selectMaintenanceObject("AQA_MO");

		waitForExtJSAjaxComplete(20);

		clickToggleButtononWOPicturetab();
		waitForExtJSAjaxComplete(5);
		
		maximizeInspectionWindow();
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(isWOGridContainsWOOfFinishedAndArchivedStatus(),"Work orders(other than finished and Archived) and pertain to selected location(Inspection Point Location) are to be displayed");

		softAssert.assertAll();

		Reporter.log("Verified NextUndoandAddbuttonsfunctionalitywhileinspectionPointsProcessing <br>", true); 


	}	
	
	/**
	 * Testcase ID	:	C101837
	 * Author		:	kve
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testVerifyDeepLinks()  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C101837: Verify myMCS Inspection Module and deep links shows on start page of webportal </span><br>", true);


		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testVerifyDeepLinks");

		String inspectionUrl = "/index.php?mode=DESKTOP&frame.php?relay=MCS_PORTAL_INSP";

		String createInspectionUrl = "/index.php?mode=DESKTOP&relay=MCS_PORTAL_INSP-ACTIVATE[INSPECTIONS_CREATE]"; 

		String oVerViewInspectionUrl= "/index.php?mode=DESKTOP&relay=MCS_PORTAL_INSP-ACTIVATE[INSPECTIONS_OV]";

		navigateToPage(inspectionUrl);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isInspectionModuleAvalaible(),"Inspection module showing on start page of web portal based on user license");

		softAssert.assertTrue(isInspectionModuleImageAvalaible(),"Image of Inspection module is visible at start page of web portal");

		navigateToPage(createInspectionUrl);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isNewInspectionsWindowOpen(),"User navigates to new Inspection form of overview.");

		navigateToPage(oVerViewInspectionUrl);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isInspectionsWindowOpen(),"User navigates to inspections overview Page");

		softAssert.assertAll();

		Reporter.log("<span> Verify myMCS Inspection Module and deep links shows on start page of webportal </span><br>",true);
	}
	
	/**
	 * Testcase ID :C109602,C109603,C109604,C109605,C109620,C109621 
	 * Author : ssa
	 * @throws IOException
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class)
	public void testVerifyWOTempWhenLocationIsInvisible() throws IOException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C109602:Verify that Wo templates which has related object type as 'None' are displayed when user tries to create WO for an inspection point"+ " </span><br>"
				+"C109603:Verfify the WO templates displayed when user tries to create WO for a maintenance object"+ " </span><br>"
				+"C109604:Verify that user is unable to create a Workorder using a WO template for which 'Location' field is invisble"+ " </span><br>"
				+"C109605:Verify that user is unable to create a Workorder for MO using a WO template for which 'Maintenance object' field is invisble"+ " </span><br>"
				+"C109620:Verify that WO templates that are restricted to other 'MO category' is not displayed in the list when user tries to create Workorder for a MO"+ " </span><br>"
				+"C109621:Verify that WO templates that are restricted to same 'MO category' is displayed in the list when user tries to create Workorder for a MO",true);

		String tempGroupName1 = "DoNotEditGroup1";
		String tempGroupName2="DoNotEditGroup2";
		String sectionName1="Specific for Maintenance Object";
		String sectionName2="General";
		String errorMessage="You can not create a workorder related to an Inspection from this workorder template, because the template specifies that the Location field must be invisible.\n"+
				"Please select another Template or cancel and retry after the Location field's 'Visible' flag is checked on the workorder template.";
		String errorMessage1="You can not create a workorder related to an Inspection from this workorder template, because the template specifies that the Maintenance Object field must be invisible.\n"+
				"Please select another Template or cancel and retry after the Maintenance Object field's 'Visible' flag is checked on the workorder template.";


		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testVerifyWOTempWhenLocationIsInvisible");

		String inspectionReference = "C97443"+ getCurrentDate().substring(getCurrentDate().length() - 6);

	
		createInspection("1aqaPreParSites", "C97445", inspectionReference);

		setInspectionStatus("Ready for Inspection");

		waitForExtJSAjaxComplete(10);

		clickSaveButton();

		waitForExtJSAjaxComplete(10);

		setInspectionStatus("In Progress");

		waitForExtJSAjaxComplete(10);

		this.clickSaveButton();

		waitForExtJSAjaxComplete(20);

		selectInspectionPoint("1aqaPreParRooms1");

		waitForExtJSAjaxComplete(20);

		clickAddButtonInInspPoints();

		waitForExtJSAjaxComplete(10);
		
		List<String> actualWOTemplates= getWOTemplatesInInspection(tempGroupName1);
		
		expandTemplateGroup(tempGroupName1);
		
		waitForExtJSAjaxComplete(10);
		
		//C109604
		selectWOTemplateInInspection("WO8");
		
		waitForExtJSAjaxComplete(10);
		
		String actualWOMessage=getWarningDialogTextMsg();

		softAssert.assertTrue(actualWOMessage.contains(errorMessage),"Error message is displayed when location is invisible"); 

		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(10);
		
		closeUsingToolBar(WO_TEMPLATE_WINDOW_XPATH);
		
		waitForExtJSAjaxComplete(20);
		
		selectMaintenanceObject("1aqaPreParRooms1","AQA_MO");
		
		waitForExtJSAjaxComplete(20);

		clickAddButtonInInspPoints();

		waitForExtJSAjaxComplete(10);
		
		expandTemplateGroup(tempGroupName2);
		
		waitForExtJSAjaxComplete(10);
	
		//C109605
		selectWOTemplateInInspection("MO_WO6");
		
		waitForExtJSAjaxComplete(10);
		
		String actualMOMessage=getWarningDialogTextMsg();

		softAssert.assertTrue(actualMOMessage.contains(errorMessage1),"Error message is displayed when Maintenance Object is invisible");
		
		waitForExtJSAjaxComplete(10);
		
		this.clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		//C109603:Verification of two sections
		boolean status=isWOTemplateSectionIsAvailable(sectionName1);
		
		softAssert.assertTrue(status,"Specific for Maintenance Object section is displayed");
		
		waitForExtJSAjaxComplete(20);
		
		List<String> actualWOTemplatesForMO= getWOTemplatesInInspection(tempGroupName2);
		
		waitForExtJSAjaxComplete(10);
		
		boolean status1=isWOTemplateSectionIsAvailable(sectionName2);
		
		softAssert.assertTrue(status1,"General section is displayed");
		
		expandWOTemplateSection(sectionName2);
		
		waitForExtJSAjaxComplete(10);
		
		collapseAllTemplatedGroups();

		List<String> actWOTemplates= getWOTemplatesInInspection(tempGroupName1);

		waitForExtJSAjaxComplete(10);
		
		closeUsingToolBar(WO_TEMPLATE_WINDOW_XPATH);
		
		AdministrationPageObject objad = new AdministrationPageObject();

		InspectionAdministrationPageObject Inspobj = new InspectionAdministrationPageObject();
		
		WorkOrderPageObject workOrders = new WorkOrderPageObject();
		
		driver.navigate().refresh();

		waitForExtJSAjaxComplete(20);

		clickAdministration();

		waitForExtJSAjaxComplete(20);

		objad.expandModuleSettings();

		waitForExtJSAjaxComplete(20);

		objad.expandWorkorders();

		waitForExtJSAjaxComplete(20);

		objad.clickWorkorderTemplates();

		waitForExtJSAjaxComplete(20);

		workOrders.expandAllTemplatedGroupsInAdmin();

		waitForExtJSAjaxComplete(20);
		
		//C109602
		for(String templateName: actualWOTemplates){

			workOrders.selectWOTemplate(templateName);
			waitForExtJSAjaxComplete(10);
			String text=workOrders.getWORelateiveObjectType(templateName);
			softAssert.assertTrue(text.equals("None"),"Templates "+templateName+" which has related object type as 'None' are only displayed");

		}

		//C109603
		waitForExtJSAjaxComplete(20);

		for(String templateName: actualWOTemplatesForMO){

			workOrders.selectWOTemplate(templateName);
			waitForExtJSAjaxComplete(5);
			String text=workOrders.getWORelateiveObjectType(templateName);
			softAssert.assertTrue(text.equals("Maintenance Object"),"Templates "+templateName+" which has related object type as 'Maintenance Object' are only displayed");

		}

		waitForExtJSAjaxComplete(20);

		//C109620,C109621
		for(String templateName: actualWOTemplatesForMO){

			workOrders.selectWOTemplate(templateName);
			waitForExtJSAjaxComplete(15);
			workOrders.clickRestrictionsTab();
			workOrders.clickMaintenanceObjectCategoryButton();
			waitForExtJSAjaxComplete(10);
			String text=workOrders.getMaintenanceObjectCategoryReference();

			if(text.contains("No Maintenance Object Category"))
			{
				softAssert.assertTrue(text.contains("No Maintenance Object Category"),"Template which has related object type as 'MO' and same category is displayed");
			}
			else{
				softAssert.assertTrue(text.contains("1preMnObCtRef"),"Template which has related object type as 'MO' and same category is displayed");
			}
		}

		workOrders.clickGeneralTab();

		waitForExtJSAjaxComplete(20);

		//C109603
		for(String tempName: actWOTemplates){

			workOrders.selectWOTemplate(tempName);

			waitForExtJSAjaxComplete(20);
			waitForExtJSAjaxComplete(20);
			String text=workOrders.getWORelateiveObjectType(tempName);	
			softAssert.assertTrue(text.equals("None"),"Templates which has related object type as 'None' are only displayed");

		}

		softAssert.assertAll();

		Reporter.log("Verified that user unable to create WO when location and maitenance object is invisible, <br>", true); 

	}
}

