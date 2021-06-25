package test.generalweb.helpdesk;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import test.generalweb.calltemplates.CallTemplatesPageObject;
import test.generalweb.scheduler.SchedulerPageObject;
import test.generalweb.service.ClientOrganizationsPageObject;
import test.generalweb.slamanagement.SlaManagementPageObject;
import test.generalweb.workorders.WorkOrderDetailCRUDTestSuite;
import test.generalweb.workorders.WorkOrderPageObject;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class HelpDeskBackOfficeTestSuite extends
		HelpDeskBackOfficePageObject {

	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class) //reviewed
	public void testCRUDConsumptionBackOffice() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: create update delete consumption back office: c15313; c15317; c15323 </span><br>", true);

		Reporter.log("User create update delete consumption back office <br>", true);
		
		
		String callRef = "1preCallSub";
				
		String callReceivedDate = "28-11-2013 14:10";
				
		String consumptionProduct1 = "1preProdRef";

		String consumptionProduct2 = "2preProdRef";

		String consumptionQuantity = "2";

		String consumptionQuantityEdited = "4";

		String consumptionSelect = "From Catalog";




		int random = (int)(Math.random() * 10)+18;

		String consumptionDate = "11-12-20"+random;

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testCRUDConsumptionBackOffice");

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");

		waitForExtJSAjaxComplete(20);

		clickXPath(XPATH_BACKOFFICE);
		waitForExtJSAjaxComplete(20);
		waitForMaskDisappear();

		collapseDetailsPanel(DETAILS_WINDOW_HEADER);

		waitForExtJSAjaxComplete(20);

		//Grid.checkRowInGriByTextValueAndClick(driver, callRef);
		Grid.checkRowInGriByTextValueAndClick(driver, callReceivedDate);
                waitForMaskDisappear();

		clickTimeMaterialTab();

		waitForExtJSAjaxComplete(20);

		clickTimeMaterialConsumptionTab();

		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);
		
		WorkOrderPageObject obj = new WorkOrderPageObject();
		
		String windowID = obj.getWindowIdByClass("x-window x-resizable-pinned");
		
	    obj.clickButtonTimeAndMaterialTab(windowID, "Add");

	//	clickAddConsumption();

		waitForExtJSAjaxComplete(20);
					
		//clickAddConsumption();

		//waitForExtJSAjaxComplete(20);

		setConsumptionSelectProduct(consumptionSelect);

		setConsumptionProduct(consumptionProduct2);

		setConsumptionQuantity(consumptionQuantity);

		setConsumptionDate(consumptionDate);
		
		waitForExtJSAjaxComplete(20);
		
		saveCloseConsumption();
                waitForMaskDisappear();

		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "hdwo-details", consumptionProduct2);

		waitForExtJSAjaxComplete(20);
		 
		obj.clickButtonTimeAndMaterialTab(windowID, "Edit");
		 
		//clickEditConsumption();

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getTotalExpence(),"20 USD","Total expense is calculated");

		softAssert.assertEquals(getTotalRevenue(),"40 USD","Total Revenue is calculated");

		setConsumptionQuantity(consumptionQuantityEdited);

		waitForExtJSAjaxComplete(20);

		setBilling("No");

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getTotalExpence(),"40 USD","Total expense is calculated");

		softAssert.assertEquals(getTotalRevenue(),"80 USD","Total Revenue is calculated");

		waitForExtJSAjaxComplete(20);

		saveConsumption();
                waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "hdwo-details", consumptionProduct2);

		waitForExtJSAjaxComplete(20);

		obj.clickButtonTimeAndMaterialTab(windowID, "Delete");
		
		//clickDeleteConsumption();
		
		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, consumptionProduct2, "@class", "hdwo-details"),"product was deleted"+consumptionProduct2);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, consumptionProduct1, "@class", "hdwo-details"),"product is present"+consumptionProduct1);

		softAssert.assertAll();

		Reporter.log("Consumption from call was succesfully edited and deleted", true);
	}

	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class) //reviewed
	public void testCRUDActivityBackOffice() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: create update delete activity back office: c15318; c15321; 15322 </span><br>", true);

		Reporter.log("User create update delete activity back office <br>", true);


		String callRef = "1preCallSub";

		String laborQuantity = "2";

		String laborQuantityEd = "4";

		String product = "3preProdRef";

		String productEd = "4preProdRef";


		int random = (int)(Math.random() * 10)+18;

		String laborActivityDate = "11-12-20"+random;

		String activityDescription = "test des" + getCurrentDate().substring(getCurrentDate().length()-6);

		String activityDescriptionEd = "test des Ed" + getCurrentDate().substring(getCurrentDate().length()-6);

		String laborActivityRegime = "1preAcRgRef";

		String activityStartTime = "10:00";

		String activityStopTime = "11:00";

		String activityNotes =  "test auto" + getCurrentDate().substring(getCurrentDate().length()-6);

		String activityNotesEd =  "test auto ed" + getCurrentDate().substring(getCurrentDate().length()-6);

		String activityStatus = "Tentative";

		String activityType = "1preAcTpRef";

		String activityWorkForce = "SELENIUM";

		String activityRelatedType = "Call";

		String activityCall = "1preCallSub";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testCRUDActivityBackOffice");

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");

		waitForExtJSAjaxComplete(20);

		clickXPath(XPATH_BACKOFFICE);
		waitForExtJSAjaxComplete(20);
		waitForMaskDisappear();

		this.filterGridForCall("@realid", "mogrid", callRef, "Reference");
		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValueAndClick(driver, callRef);
		waitForMaskDisappear();

		clickTimeMaterialTab();

		waitForExtJSAjaxComplete(20);

		clickTimeMaterialLaborTab();

		waitForExtJSAjaxComplete(20);

		clickAddLabor();

		waitForExtJSAjaxComplete(20);

		setLaborActivityDate(laborActivityDate);

		setLaborActivityStartTime(activityStartTime);

		setLaborActivityStopTime(activityStopTime);
		
		// Activity Status Field is disabled until the Activity is saved.(Expected behaviour)
		//	setLaborActivityStatus(activityStatus);

		setLaborActivityType(activityType);

		waitForExtJSAjaxComplete(25);

		setLaborActivityWorkForce(activityWorkForce);

		//waitForExtJSAjaxComplete(25);

		//setLaborActivityRelatedType(activityRelatedType);

		//waitForExtJSAjaxComplete(25);

		//setLaborActivityCall(activityCall);

		waitForExtJSAjaxComplete(25);

		setLaborDescription(activityDescription);

		clickLaborActivityFinancialTab();

		waitForExtJSAjaxComplete(20);

		setLaborActivityRegime(laborActivityRegime);

		clickOverrideProductService();

		waitForExtJSAjaxComplete(25);

		setLaborProduct(product);

		setLaborBilling("No");
		
		clickOverrideQuantity();
		
		waitForExtJSAjaxComplete(25);

		setLaborQuantity(laborQuantity);

		clickLaborActivityNotesTab();

		setLaborActivityNotes(activityNotes);

		saveAndCloseActivity();
                waitForMaskDisappear();

		Grid.checkRowInGriByTextValueAndClick(driver, "@realid", "CallLaborGrid", activityDescription);
                waitForMaskDisappear();

		clickEditLabor();

		waitForExtJSAjaxComplete(20);

		setLaborActivityDate(laborActivityDate);

		setLaborActivityStartTime(activityStartTime);

		setLaborActivityStopTime(activityStopTime);

		setLaborActivityStatus(activityStatus);

		setLaborActivityType(activityType);

		waitForExtJSAjaxComplete(25);

		setLaborActivityWorkForce(activityWorkForce);

		waitForExtJSAjaxComplete(25);

		setLaborDescription(activityDescriptionEd);

		clickLaborActivityFinancialTab();

		waitForExtJSAjaxComplete(20);

		setLaborActivityRegime(laborActivityRegime);

		clickOverrideProductService();

		waitForExtJSAjaxComplete(25);

		clickOverrideProductService();

		waitForExtJSAjaxComplete(25);

		setLaborProduct(productEd);

		setLaborBilling("Yes");
		
		clickOverrideQuantity();
		
		waitForExtJSAjaxComplete(25);
		
		clickOverrideQuantity();
		
		waitForExtJSAjaxComplete(25);

		setLaborQuantity(laborQuantityEd);

		clickLaborActivityNotesTab();

		setLaborActivityNotes(activityNotesEd);

		saveAndCloseActivity();
                waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValueAndClick(driver, "@realid", "CallLaborGrid", activityDescriptionEd);

		waitForExtJSAjaxComplete(20);

		clickDeleteLabor();

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, activityDescriptionEd));

		softAssert.assertAll();

		Reporter.log("Activity was succesfully edited and deleted", true);
	}

	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class) //reviewed
	public void testCRUDAssetBackOffice() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: create delete asset back office: c15328, c22004 </span><br>", true);

		Reporter.log("User create delete asset back office <br>", true);


		String assetRef = "1preAsCtRef1preAsRef";

		String assetRefGrid = "1preAsCtRef 1preAsRef";

		String assetRef2 = "1preAsCtRef2preAsRef";

		String assetRefGrid2 = "1preAsCtRef 2preAsRef";

		String callRef = "1preCallSub";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testCRUDActivityBackOffice");

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");

		waitForExtJSAjaxComplete(20);

		clickXPath(XPATH_BACKOFFICE);
		waitForExtJSAjaxComplete(20);
		waitForMaskDisappear();

		this.filterGridForCall("@realid", "mogrid", callRef, "Reference");
		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValueAndClick(driver, callRef);
		waitForMaskDisappear();

		clickMaintenanceObjectTab();

		waitForExtJSAjaxComplete(20);

		clickAddAsset();

		waitForExtJSAjaxComplete(20);

		setAsset(assetRef);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		clickAddAsset();

		waitForExtJSAjaxComplete(20);

		setAsset(assetRef2);

		waitForExtJSAjaxComplete(25);

		String tag = "div";

		clickXPath("//*[(contains(@class,'x-tree'))]//"+tag+"[text()='"+assetRefGrid+"']");

		waitForExtJSAjaxComplete(25);

		clickRemoveAsset();

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, assetRefGrid));

		clickXPath("//*[(contains(@class,'x-tree'))]//"+tag+"[text()='"+assetRefGrid2+"']");

		waitForExtJSAjaxComplete(25);

		clickRemoveAsset();

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, assetRefGrid2));

		softAssert.assertAll();

		Reporter.log("Asset was succesfully added and deleted", true);
	}

	@Test(enabled=true)    //reviewed
	public void testCRUDMaintenanceObjectBackOffice() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: create delete maintenance object back office: c15324, c15327; 15325 </span><br>", true);

		Reporter.log("User create delete maintenance object back office <br>", true);


		String mainObjectRef1 = "1preMnObRef";

		String mainObjectRef2 = "2preMnObRef";

		String callRef = "1preCallSub";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testCRUDMaintanceObjectBackOffice");

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");

		waitForExtJSAjaxComplete(20);

		clickXPath(XPATH_BACKOFFICE);
		waitForExtJSAjaxComplete(20);
		waitForMaskDisappear();
		
		this.filterGridForCall("@realid", "mogrid", callRef, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, callRef);
		waitForMaskDisappear();

		clickMaintenanceObjectTab();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		clickAddMaintanceObject();

		waitForExtJSAjaxComplete(20);

		setMaintenanceObject(mainObjectRef1);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		clickAddMaintanceObject();

		waitForExtJSAjaxComplete(20);

		setMaintenanceObject(mainObjectRef2);

		waitForExtJSAjaxComplete(25);

		String tag = "div";

		clickXPath("//*[(contains(@class,'x-tree'))]//"+tag+"[text()='"+mainObjectRef1+"']");

		waitForExtJSAjaxComplete(25);

		clickDetailsMaintanceObject();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

//C15325
        int num = 2;
		softAssert.assertEquals(getMaintenanceObjFieldValue("gp-groupOrd-0-bd", "Reference",num), mainObjectRef1,"reference is displayed in details");

		softAssert.assertEquals(getMaintenanceObjFieldValue("gp-groupOrd-0-bd", "Code",num), "1preMnObCod","code is displayed in details");

		softAssert.assertEquals(getMaintenanceObjFieldValue("gp-groupOrd-0-bd", "Class",num), "Technical Object","class is displayed in details");

		softAssert.assertEquals(getMaintenanceObjFieldValue("gp-groupOrd-0-bd", "Status",num), "Active","status is displayed in details");

		softAssert.assertEquals(getMaintenanceObjFieldValue("gp-groupOrd-0-bd", "Status",num),"Active","status is displayed in details");

		softAssert.assertEquals(getMaintenanceObjFieldValue("gp-groupOrd-0-bd", "Category",num), "1preMnObCtRef","category is displayed in details");

		softAssert.assertEquals(getMaintenanceObjFieldValue("gp-groupOrd-0-bd", "Location",num), "0slnmEnrgRoom1","location is displayed in details");

		softAssert.assertEquals(getMaintenanceObjFieldValue("gp-groupOrd-0-bd", "Room",num), "0slnmEnrgRoom1","room is displayed in details");

		softAssert.assertEquals(getMaintenanceObjFieldValue("gp-groupOrd-0-bd", "Floor",num), "slnmEnrgFloor1","floor is displayed in details");

		softAssert.assertEquals(getMaintenanceObjFieldValue("gp-groupOrd-0-bd", "Building",num), "slnmEnrgBuilding1","building is displayed in details");

		softAssert.assertEquals(getMaintenanceObjFieldValue("gp-groupOrd-0-bd", "Site",num), "slnmEnrgSite1","site is displayed in details");

		softAssert.assertEquals(getMaintenanceObjFieldValue("gp-groupOrd-0-bd", "Nature",num), "1preNatureRef","nature is displayed in details");

		softAssert.assertEquals(getMaintenanceObjFieldValue("gp-groupOrd-2-bd", "Parts Yearly Cost (EUR)",num), "2","cost is displayed in details");

		softAssert.assertEquals(getMaintenanceObjFieldValue("gp-groupOrd-0-bd", "Nature Path",num), "Default Nature/1preNatureRef","nature path is displayed in details");


		closeDetailsMaintanceObject();

		waitForExtJSAjaxComplete(25);

		clickXPath("//*[(contains(@class,'x-tree'))]//"+tag+"[text()='"+mainObjectRef1+"']");

		waitForExtJSAjaxComplete(25);

		clickRemoveAsset();

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, mainObjectRef1));

		clickXPath("//*[(contains(@class,'x-tree'))]//"+tag+"[text()='"+mainObjectRef2+"']");

		waitForExtJSAjaxComplete(25);

		clickRemoveAsset();

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, mainObjectRef2));

		softAssert.assertAll();

		Reporter.log("Maintenance object was succesfully added and deleted", true);
	}


	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)  //reviewed
	public void testCRUDMaintenanceObjectPartBackOffice() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: create delete maintenance object part back office: c15326 </span><br>", true);

		Reporter.log("User create delete maintenance object part back office <br>", true);


		String mainObjectRef1 = "1preMnObRef";

		String mainObjectRef2 = "2preMnObRef";

		String mainObjectPartRef1 = "1preMnObPtRef";

		String mainObjectPartRef2 = "2preMnObPtRef";

		String callRef = "1preCallSub";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testCRUDMaintanceObjectBackOffice");

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");

		waitForExtJSAjaxComplete(20);

		clickXPath(XPATH_BACKOFFICE);
		waitForExtJSAjaxComplete(20);
                waitForMaskDisappear();

                HelpDeskFrontOfficePageObject obj = new HelpDeskFrontOfficePageObject();
        		obj.collapseDetailsPanel();
        		waitForExtJSAjaxComplete(5);

		Grid.checkRowInGriByTextValueAndClick(driver, callRef);
                waitForMaskDisappear();
        		waitForExtJSAjaxComplete(25);

		clickMaintenanceObjectTab();

		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		clickAddMaintanceObjectPart();
		waitForExtJSAjaxComplete(20);
		
		setMaintenanceObjectPart(mainObjectPartRef1);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		clickAddMaintanceObjectPart();

		waitForExtJSAjaxComplete(20);

		setMaintenanceObjectPart(mainObjectPartRef2);

		waitForExtJSAjaxComplete(25);

		String tag = "div";

		clickXPath("//*[(contains(@class,'x-tree'))]//"+tag+"[text()='"+mainObjectPartRef1+"']");

		waitForExtJSAjaxComplete(25);

		clickRemoveAsset();

		clickOnDialogButton("No");

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, mainObjectPartRef1));

		clickXPath("//*[(contains(@class,'x-tree'))]//"+tag+"[text()='"+mainObjectRef1+"']");

		waitForExtJSAjaxComplete(25);

		clickRemoveAsset();

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		clickXPath("//*[(contains(@class,'x-tree'))]//"+tag+"[text()='"+mainObjectPartRef2+"']");

		waitForExtJSAjaxComplete(25);

		clickRemoveAsset();

		clickOnDialogButton("No");

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, mainObjectPartRef2));

		clickXPath("//*[(contains(@class,'x-tree'))]//"+tag+"[text()='"+mainObjectRef2+"']");

		waitForExtJSAjaxComplete(25);

		clickRemoveAsset();

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		softAssert.assertAll();

		Reporter.log("Maintenance object part was succesfully added and deleted", true);
	}


	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)  //reviewed
	public void testCRUDCallDescriptionBackOffice() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: create edit description of call from back office: c15335 </span><br>", true);

		Reporter.log("User create delete description of call from back office <br>", true);


		String callRef = "1preCallSub";

		String description = "add comm";

		String descriptionEd = "add comment edites";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testCRUDCallDescriptionBackOffice");

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");

		waitForExtJSAjaxComplete(20);

		clickXPath(XPATH_BACKOFFICE);
		waitForExtJSAjaxComplete(20);
		waitForMaskDisappear();

		this.filterGridForCall("@realid", "mogrid", callRef, "Reference");
		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValueAndClick(driver, callRef);
		waitForMaskDisappear();

		clickDescriptionTab();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		//		softAssert.assertEquals(getDescription(),description,"description before edit");

		setDescription(descriptionEd);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickSaveDescription();
                waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(25);

		Grid.checkRowInGriByTextValueAndClick(driver, callRef);

		waitForExtJSAjaxComplete(20);

		clickDescriptionTab();

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getDescription(),descriptionEd,"description after edit");

		setDescription(description);

		waitForExtJSAjaxComplete(25);

		clickSaveDescription();
                waitForMaskDisappear();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		clickDescriptionTab();

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getDescription(),description,"description again after edit");

		softAssert.assertAll();

		Reporter.log("description for call was edited succesfully", true);
	}

	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class) //reviewed
	public void testAddConsumptionIDCostItemIDToConsumption() throws Exception {
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: AddConsumptionIDCostItemIDToConsumption: C19858 , C15457, C15350 </span><br>", true);

		Reporter.log("User AddConsumptionIDCostItemIDToConsumption <br>", true);

		
		String callRef = "1preCallSub";

		String call2Ref = "2preCallRef";
		
		String call1ReceivedDate = "28-11-2013 14:10";
		String call2ReceivedDate = "16-01-2014 10:32";

		String[] callRefArr1 = {"1", "1preCallSub"};

		String[] callRefArr2 = {"5","2preCallRef"};

		String consumptionId = "0000000033";

		String headerBegining = "Edit Consumption ";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testAddConsumptionIDCostItemIDToConsumption");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		expandAdministration();

		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		expandSubMainMenu("HelpDesk");

		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		clickXPath(XPATH_BACKOFFICE);
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(25);
		
		this.filterGridForCall("@realid", "mogrid",call1ReceivedDate, "Date Received");
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, call1ReceivedDate);
		
        waitForMaskDisappear();

		clickTimeMaterialTab();

		waitForExtJSAjaxComplete(20);

		clickTimeMaterialConsumptionTab();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Consumption ID", "@class", "hdwo-details"));

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Activity ID", "@class", "hdwo-details"));

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Product Code", "@class", "hdwo-details"));

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Product Reference", "@class", "hdwo-details"));

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Quantity", "@class", "hdwo-details"));

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Date Consumed", "@class", "hdwo-details"));

	//	softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Product Group", "@class", "hdwo-details"));

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Cost Item ID", "@class", "hdwo-details"));

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Created By", "@class", "hdwo-details"));

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Created On", "@class", "hdwo-details"));

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Latest Modification By", "@class", "hdwo-details"));

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Latest Modification On", "@class", "hdwo-details"));

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "0000000033|1preProdCod|1preProdRef|1|29-01-2014|0000000033|AQA SELENIUM|29-01-2014 11:48", "@class", "hdwo-details"));
		
		waitForExtJSAjaxComplete(20);
		
		//openConsumptionDialog(consumptionId);

		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "hdwo-details", "1preProdCod");
						
		waitForExtJSAjaxComplete(20);
		
		clickEditConsumption();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(verifyConsumptionHeader( headerBegining, consumptionId), "Header is valid: ");

		clickCancelOnConsumptionDialog();
		
		waitForExtJSAjaxComplete(20);
		
		closeModule("HelpDesk");
		waitForExtJSAjaxComplete(5);

		clickXPath(XPATH_BACKOFFICE);
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		sortColumnsAscDec("@class","helpdesk","Reference","Sort Ascending");
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, call1ReceivedDate);
	
		waitForExtJSAjaxComplete(25);

		//Grid.checkRowInGriByTextValue(driver, call2Ref);
		Grid.checkRowInGriByTextValue(driver, call2ReceivedDate);

		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		verifySelectedCallsInTab(callRefArr1[0], callRefArr1[1]);

		waitForExtJSAjaxComplete(20);

		verifySelectedCallsInTab(callRefArr2[0], callRefArr2[1]);

		waitForExtJSAjaxComplete(20);

		openCallDetailsDialog(callRef);

		waitForExtJSAjaxComplete(20);

		verifyCallDetailsHeader(callRef);

		waitForExtJSAjaxComplete(20);

		//closeXWindow();

		softAssert.assertAll();

		Reporter.log("Consumption tab was succesfully tested.", true);
	}

	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class) //reviewed
	public void testBookNewCallFromBackoffice() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Book new call from Backoffice : c15406, c15386</span><br>", true);

		Reporter.log("User book new call <br>", true);


		String subject =  "test auto sub" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority =  "1prePrior";
		String url = "http://test.com";
		String urlEd = "http://tested.com";
		String file = "test.csv";
		String fileEd = "te2.csv";

		String urlDescription =  "url descr" + getCurrentDate().substring(getCurrentDate().length()-6);

		String urlDescriptionEd =  "url descrEd" + getCurrentDate().substring(getCurrentDate().length()-6);

		String urlRemark =  "url remark" + getCurrentDate().substring(getCurrentDate().length()-6);

		String urlRemarkEd =  "url remarkEd" + getCurrentDate().substring(getCurrentDate().length()-6);

		String fileDescription =  "file descr" + getCurrentDate().substring(getCurrentDate().length()-6);

		String fileDescriptionEd =  "file descrEd" + getCurrentDate().substring(getCurrentDate().length()-6);

		String fileRemark =  "file remark" + getCurrentDate().substring(getCurrentDate().length()-6);

		String fileRemarkEd =  "file remarkEd" + getCurrentDate().substring(getCurrentDate().length()-6);

		String type = "labelen";

		String typeEd = "1prelaben";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testBookNewCallBackoffice");

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");

		waitForExtJSAjaxComplete(20);

		clickXPath(XPATH_BACKOFFICE); ///
		waitForExtJSAjaxComplete(20);
                waitForMaskDisappear();

		clickButtonAddCall();
                waitForMaskDisappear();

		clickCallTemplate("Other Subject");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setSubject(subject);

		setPriority(priority);

		setNature("Default Nature");

		waitForExtJSAjaxComplete(20);

		setCostCenter("myMCS Default Cost Center");

		waitForExtJSAjaxComplete(20);

		setGlAccount("myMCS Default GL Account");

		waitForExtJSAjaxComplete(20);

		setUrl(url, urlDescription, urlRemark, type);
                waitForMaskDisappear();

		setFile(file, fileDescription, fileRemark, type);
                waitForMaskDisappear();

		editUrl(urlEd, urlDescription, urlDescriptionEd, urlRemarkEd, type);
                waitForMaskDisappear();

		editFile(file, fileDescription, fileDescriptionEd, fileRemarkEd, type);
                waitForMaskDisappear();

		deleteUrl(urlDescriptionEd);
                waitForMaskDisappear();

		deleteUrl(fileDescriptionEd);
                waitForMaskDisappear();

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, urlDescriptionEd),"url can be deleted");

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, urlDescription),"url can be deleted");

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, fileDescriptionEd),"file can be deleted");

		clickBookCall();
                waitForMaskDisappear();

		softAssert.assertTrue(!driver.findElements(By.xpath("//button[contains(text(),'Show Call')]")).isEmpty(),"Show Call button is present");

		clickMyCallsTab();
                waitForMaskDisappear();

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, subject, "@class", "helpdesk"),"Call is present in my calls");

		clickMyTeamsCallsTab();
                waitForMaskDisappear();

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, subject, "@class", "helpdesk"),"Call is present in my team calls");

		waitForExtJSAjaxComplete(20);

		clickBackofficeTab();
		waitForMaskDisappear();

		this.filterGridForCall("@realid", "mogrid", subject, "Reference");
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, subject, "@class", "helpdesk"),"Call is present in back office calls");

		waitForExtJSAjaxComplete(20);

		//	c15356, c15357
		//Grid.checkRowInGriByTextValue(driver, subject);
		Grid.checkRowInGriByTextValueAndClick(driver, subject);
		waitForMaskDisappear();

		//clickDocumentsTab();
		//Documents tab has been renamed to Attachments
		clickAttachmentsTab();

		waitForExtJSAjaxComplete(20);

		setUrl(url, urlDescription, urlRemark, type);
		waitForMaskDisappear();

		setFile(file, fileDescription, fileRemark, type);
		waitForMaskDisappear();

		editUrl(urlEd, urlDescription, urlDescriptionEd, urlRemarkEd, typeEd);
                waitForMaskDisappear();

		editFile(fileEd, fileDescription, fileDescriptionEd, fileRemarkEd, typeEd);
                waitForMaskDisappear();

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, urlDescriptionEd+"|"+typeEd+"|"+urlRemarkEd, "@class","x-grid3"),"url can be edited");

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, fileEd+"|"+fileDescriptionEd+"|"+typeEd+"|"+fileRemarkEd, "@class","x-grid3"),"file can be edited");

		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'grid3')]//a[contains(text(),'"+urlEd+"')]"),"url edited");

		deleteUrl(urlDescriptionEd);
                waitForMaskDisappear();

		deleteUrl(fileDescriptionEd);
                waitForMaskDisappear();

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, urlDescriptionEd),"url can be deleted");

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, urlDescription),"url can be deleted");

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, fileDescriptionEd),"file can be deleted");

		clickButtonDeleteCall();

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
                waitForMaskDisappear();

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, subject),"call can be deleted");

		softAssert.assertAll();

		Reporter.log("Book call operation was succesfull", true);
	}


	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)   //reviewed
	public void testCallAction() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: create/edit call actions back office: c18888; c18048; c15463 </span><br>", true);

		Reporter.log("User create/edit call actions back office <br>", true);


		String callRef = "3preActionCallSub";

		String possibleAction = "Open";

		String possibleAction2 = "Progress";

		String actionNote = "action note" + getCurrentDate().substring(getCurrentDate().length()-6);

		String assigned = "SELENIUM";

		String contact = "1preContactLast";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testCallAction");

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");

		waitForExtJSAjaxComplete(20);

		clickXPath(XPATH_BACKOFFICE);
		waitForExtJSAjaxComplete(20);
		waitForMaskDisappear();

		this.filterGridForCall("@realid", "mogrid", callRef, "Reference");
		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValueAndClick(driver, callRef);
		waitForMaskDisappear();

		clickTrackingTab();

		waitForExtJSAjaxComplete(20);

		clickTrackingHistoryTab();

		waitForExtJSAjaxComplete(20);

		clickEditLastAction();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("OK");

	//	saveCloseAction();
                waitForMaskDisappear();

	//	waitForExtJSAjaxComplete(25);
	//	waitForExtJSAjaxComplete(25);
	//	waitForExtJSAjaxComplete(25);

		clickAddAction();

		waitForExtJSAjaxComplete(20);

		clickPossibleActions(possibleAction);

		setActionNote(actionNote);

		setActionAssigned(assigned);

		waitForExtJSAjaxComplete(20);

		setActionContact(contact);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(getActionType().contains(possibleAction2),"action type was selected");

		saveCloseAction();
                waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isActionPresent(actionNote),"action is added");

		clickEditLastAction();

		waitForExtJSAjaxComplete(20);

		//clickPossibleActions(possibleAction2);

		setActionNote(actionNote+"ed");

		softAssert.assertTrue(getActionType().contains(possibleAction2),"action type is disabled after edit");

		saveCloseAction();
                waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isActionPresent(actionNote+"ed"),"action note is edited");

                ///////////////////////////////////////////////////////////////////////////////////
                // Reset to initial status
                ///////////////////////////////////////////////////////////////////////////////////
                clickAddAction();

		waitForExtJSAjaxComplete(20);

		clickPossibleActions("Back To Preparation");

		waitForExtJSAjaxComplete(20);

		saveCloseAction();
                waitForMaskDisappear();

		softAssert.assertAll();

		Reporter.log("Action was succesfully edited and deleted", true);

	}

	/**
	 * Testcase ID	: 	C15430
	 * Author		:	ssa
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)   //reviewed
	public void testErrorMsgWhileSendingNotification() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C15430:An Error is displayed while user is sending a notification with no recipients</span><br>", true);


		String subject =  "test auto sub" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority =  "1prePrior";
		String nature="Default Nature";
		String warngMsg="Recipients are not selected";
		String emailTempName="1aqaEmailTemplate";
		String smsTempName="1aqaSMSTemplate";


		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testErrorMsgWhileSendingNotification");


		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");

		waitForExtJSAjaxComplete(20);

		clickXPath(XPATH_NEWCALL);
                waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		clickCallTemplate("Other Subject");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setSubject(subject);

		setPriority(priority);

		setNature(nature);

		waitForExtJSAjaxComplete(20);

		clickBookCall();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(!driver.findElements(By.xpath("//button[contains(text(),'Show Call')]")).isEmpty(),"Show Call button is present");

		waitForExtJSAjaxComplete(20);

		clickXPath(XPATH_BACKOFFICE);
		waitForExtJSAjaxComplete(20);
                waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);
		
		collapseDetailsPanel(DETAILS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
	
		this.filterGridForCall("@realid", "mogrid", subject, "Reference");

		waitForExtJSAjaxComplete(20);

		openCallDetailsDialog(subject);

		waitForExtJSAjaxComplete(20);

		clickAddActionInDetails();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickPossibleActions("Open");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		dragAndDropActionWindows(ADD_ACTION_WINDOW_HEADER,DETAILS_WINDOW_HEADER);

		waitForExtJSAjaxComplete(20);

		checkSendNotification();

		waitForExtJSAjaxComplete(20);

		clickAddActionForCall();

		waitForExtJSAjaxComplete(20);

		clickMaximizeButtonForSendNotificationWindow();

		waitForExtJSAjaxComplete(20);

		selectEmailTemplateInNotification(emailTempName);

		waitForExtJSAjaxComplete(20);

		selectSMSTemplateInNotification(smsTempName);

		waitForExtJSAjaxComplete(20);

		selectLanguageForEmailTemp("English");
                waitForDialogMaskDisappear("//span[contains(text(),'Send Notification')]//ancestor::div[contains(@class,'x-window x-resizable-pinned')]");

		clickSendButton();

		waitForExtJSAjaxComplete(20);

		boolean status=verifyWarningDialogTextMessage(warngMsg);

		softAssert.assertTrue(status,"Error message is displayed");

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("OK");

		softAssert.assertAll();

		Reporter.log("Error message is displayed successfully while user is sending a notification with no recipients ", true);

	}

	/**
	 * Testcase ID	: 	C73986
	 * Author		:	ssa
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testSelectedOptionsInNotificationScreen() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C15418:Last selected options are remembered on Notification Screen</span><br>", true);


		String subject =  "test auto sub" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority =  "1prePrior";
		String nature="Default Nature";
		String warngMsg="Recipients are not selected";
		String emailTempName="1aqaEmailTemplate";
		String smsTempName="1aqaSMSTemplate";
		String colName="E-mail Address";
		String emailAddress="ssa@mcs.fm";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testSelectedOptionsInNotificationScreen");


		expandAdministration();
		
		waitForExtJSAjaxComplete(25);
		
		CallTemplatesPageObject calltemplatesPageObject = new CallTemplatesPageObject();
		
		calltemplatesPageObject.clickAdministration();
		
		waitForExtJSAjaxComplete(25);

		calltemplatesPageObject.expandNotifications();
		
		waitForExtJSAjaxComplete(25);
		
		calltemplatesPageObject.clickSMSTemplate();
		
		waitForExtJSAjaxComplete(20);

		calltemplatesPageObject.selectSMSTemplate(smsTempName);
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.checkActivSMSTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.clickSaveChangesForSMSTemplates();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		expandSubMainMenu("HelpDesk");

		waitForExtJSAjaxComplete(20);

		clickXPath(XPATH_NEWCALL);
                waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		clickCallTemplate("Other Subject");

		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		setSubject(subject);

		setPriority(priority);

		setNature(nature);

		waitForExtJSAjaxComplete(20);

		clickBookCall();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(!driver.findElements(By.xpath("//button[contains(text(),'Show Call')]")).isEmpty(),"Show Call button is present");

		waitForExtJSAjaxComplete(20);

		clickXPath(XPATH_BACKOFFICE);
		waitForExtJSAjaxComplete(20);
                waitForMaskDisappear();

		this.filterGridForCall("@realid", "mogrid", subject, "Reference");
		waitForExtJSAjaxComplete(25);
		
        waitForMaskDisappear();

        HelpDeskFrontOfficePageObject obj = new HelpDeskFrontOfficePageObject();
		obj.collapseDetailsPanel();
		waitForExtJSAjaxComplete(5);
		
		openCallDetailsDialog(subject);

		waitForExtJSAjaxComplete(20);

		clickAddActionInDetails();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickPossibleActions("Open");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		dragAndDropActionWindows(ADD_ACTION_WINDOW_HEADER,DETAILS_WINDOW_HEADER);

		waitForExtJSAjaxComplete(20);

		checkSendNotification();

		waitForExtJSAjaxComplete(20);

		clickAddActionForCall();

		waitForExtJSAjaxComplete(20);

		clickMaximizeButtonForSendNotificationWindow();

		waitForExtJSAjaxComplete(20);

		selectEmailTemplateInNotification(emailTempName);

		waitForExtJSAjaxComplete(20);

		selectSMSTemplateInNotification(smsTempName);

		waitForExtJSAjaxComplete(20);

		selectLanguageForEmailTemp("English");
                waitForDialogMaskDisappear("//span[contains(text(),'Send Notification')]//ancestor::div[contains(@class,'x-window x-resizable-pinned')]");

		setEmailAddressInNotification("1",colName,emailAddress);

		waitForExtJSAjaxComplete(20);

		clickCheckBoxForRecipients("1","Assign To");

		waitForExtJSAjaxComplete(20);

		selectLanguageForSMSTemp("Swedish");
                waitForDialogMaskDisappear("//span[contains(text(),'Send Notification')]//ancestor::div[contains(@class,'x-window x-resizable-pinned')]");

		waitForExtJSAjaxComplete(20);

		clickCheckBoxForRecipients("1","To");

		waitForExtJSAjaxComplete(20);

		clickCheckBoxForRecipients("1","Cc");

		waitForExtJSAjaxComplete(20);

		clickSendButton();

		waitForExtJSAjaxComplete(25);

		clickPossibleActions("Finish");

		waitForExtJSAjaxComplete(20);

		clickAddActionForCall();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickMaximizeButtonForSendNotificationWindow();

		waitForExtJSAjaxComplete(20);
		
		selectSMSTemplateInNotification(smsTempName);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("SMS messages has been sent to respective recepients and SMS notification Template is chosen.", true);
		
	}
	
	/**
	 * Testcase ID	: 	C73933, C73932, C73931, C73969
	 * Author		:	ssa
	 */
	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)  //reviewed
	public void testMultipleActions() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Add Action and edit last action to Call, Action History of Calls: </span><br>", true);

		String subject =  "test auto sub" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority =  "1prePrior";
		String nature="Default Nature";
		String possibleAction2= "Finish";
		String possibleAction = "Open";
		String actionNote = "action note" + getCurrentDate().substring(getCurrentDate().length()-6);
		String assigned ="SELENIUM";
		String contact = "1preContactLast";
		String contact1="Roles";
		String toolTipMsgForCall="There are no changes made to save.";
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testMultipleActions");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");
		
		waitForExtJSAjaxComplete(20);
		
		clickXPath(XPATH_NEWCALL);
                waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		clickCallTemplate("Other Subject");

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setSubject(subject);
		
		setPriority(priority);
		
		setNature(nature);
		
		waitForExtJSAjaxComplete(20);
		
		clickBookCall();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(!driver.findElements(By.xpath("//button[contains(text(),'Show Call')]")).isEmpty(),"Show Call button is present");
		
		waitForExtJSAjaxComplete(20);
		
		clickXPath(XPATH_BACKOFFICE);
		waitForExtJSAjaxComplete(20);
		waitForMaskDisappear();
		HelpDeskFrontOfficePageObject obj = new HelpDeskFrontOfficePageObject();

		obj.collapseDetailsPanel();
		waitForExtJSAjaxComplete(20);

		this.filterGridForCall("@realid", "mogrid", subject, "Reference");
                waitForMaskDisappear();

         openCallDetailsDialog(subject);
		
		waitForExtJSAjaxComplete(20);
                waitForElementDisappear("//div[contains(@class,'x-mask-loading')]");
                
		clickTrackingTab(DETAILS_WINDOW_HEADER);
		waitForMaskDisappear();

		clickTrackingHistoryTab();
	        waitForMaskDisappear();

		clickAddAction();
		
		waitForExtJSAjaxComplete(20);
		
		dragAndDropActionWindows(ADD_ACTION_WINDOW_HEADER, DETAILS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddActionForCall();
		
		waitForExtJSAjaxComplete(20);
		
		String text=getToolTipTextForActionButtons(ADD_ACTION_WINDOW_HEADER,"Add Action");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(text.contains(toolTipMsgForCall),"Tool tip message is displayed for add action button");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddActionAndCloseForCall(ADD_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		text=getToolTipTextForActionButtons(ADD_ACTION_WINDOW_HEADER,"Add Action and Close");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(text.contains(toolTipMsgForCall),"Tool tip message is displayed for add action and close button");
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions(possibleAction);
		
		setActionNote(actionNote);
		
		setActionAssigned(assigned);
		
		waitForExtJSAjaxComplete(20);
		
		setActionContact(contact);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddActionAndCloseForCall(ADD_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		boolean status=isActionWindowClosed(ADD_ACTION_WINDOW_HEADER);
		
		softAssert.assertFalse(status,"Add action window is closed after clicking on AddActionandclose button");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddAction();
		
		waitForExtJSAjaxComplete(20);
		
		dragAndDropActionWindows(ADD_ACTION_WINDOW_HEADER, DETAILS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions(possibleAction2);
		
		waitForExtJSAjaxComplete(20);
		
		setActionNote(actionNote);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddActionForCall();
		
		waitForExtJSAjaxComplete(20);
		
		status=isActionWindowClosed(ADD_ACTION_WINDOW_HEADER);
		
		softAssert.assertTrue(status,"Add action window is not closed after clicking on AddAction button");
		
		waitForExtJSAjaxComplete(20);
		
		clickCloseForCall(ADD_ACTION_WINDOW_HEADER);
		waitForExtJSAjaxComplete(20);
                waitForMaskDisappear();
		
		clickEditLastAction();
		
		waitForExtJSAjaxComplete(20);
		
		dragAndDropActionWindows(EDIT_ACTION_WINDOW_HEADER, DETAILS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		text=getActionAssigned(EDIT_ACTION_WINDOW_HEADER);
		
		softAssert.assertTrue(text.contains(assigned), "Assigned field of last action value is populated");
		
		waitForExtJSAjaxComplete(20);
		
		text=getActionContact(EDIT_ACTION_WINDOW_HEADER);
		
		softAssert.assertTrue(text.contains(contact),"Contact field of last action value is populated");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
	
		clickCloseForCall(EDIT_ACTION_WINDOW_HEADER);
		waitForExtJSAjaxComplete(20);
                waitForMaskDisappear();

		status=isActionWindowClosed(EDIT_ACTION_WINDOW_HEADER);
		
		softAssert.assertFalse(status,"edit action window is closed after clicking on close button");
		
		waitForExtJSAjaxComplete(20);
		
		clickEditLastAction();
		
		waitForExtJSAjaxComplete(25);
		
		dragAndDropActionWindows(EDIT_ACTION_WINDOW_HEADER, DETAILS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		setActionContact(contact1);
		
		waitForExtJSAjaxComplete(20);
		
		this.clickSaveAction(EDIT_ACTION_WINDOW_HEADER);
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		status=isActionWindowClosed(EDIT_ACTION_WINDOW_HEADER);
		
		softAssert.assertTrue(status,"edit action window is not closed after clicking on save action button");
		
		waitForExtJSAjaxComplete(20);
		
		text=getActionContact(EDIT_ACTION_WINDOW_HEADER);
		
		softAssert.assertTrue(text.contains(contact1),"changes are saved after clicking on save action button");
		
		waitForExtJSAjaxComplete(20);
		
		this.clickSaveActionClose(EDIT_ACTION_WINDOW_HEADER);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		
		status=isActionWindowClosed(EDIT_ACTION_WINDOW_HEADER);
		
		softAssert.assertFalse(status,"edit action window is closed after clicking on save action close button");
		
		waitForExtJSAjaxComplete(20);
		
		clickHistoryDetails();
		
		waitForExtJSAjaxComplete(20);
		
		text=getCallHistory(ACTION_HISTORY_WINDOW_HEADER,CALL_FIRST_STATUS_CLASS,"1");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(text, "In Progress","Action history for call first status is displayed");
		
		text=getCallHistory(ACTION_HISTORY_WINDOW_HEADER,CALL_LAST_STATUS_CLASS,"1");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(text,"Finished","Action history for call last status is displayed");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("Multiple Actions was succesfully verified.", true);
	}

	/**
	 * Testcase ID	: C15380,c15340,C73914,C73915,C73917,C15341
	 * Author		:	ssa
	 */
	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class) //reviewed
	public void testAddRecordToCallOverview() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Add a record to call overview</span><br>", true);

	
		String subject =  "test auto sub" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority =  "1prePrior";
		String nature =  "Default Nature";
		String callerComp= "Customer:";
		String compName="My Company";
		String callerName="Caller:";
		String callSubject="Subject:";
		String callPriority="Priority:";
		String callNature="Nature:";
		String callName= getUserNameOfLoggedInUser();
		String callNameFnLnFormat=this.getUserNameOfLoggedInUserFirstNameLastNameFormat();
		
		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testAddRecordToCallOverview");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");
		
		waitForExtJSAjaxComplete(20);
		
		clickXPath(XPATH_BACKOFFICE);
		waitForExtJSAjaxComplete(20);
                waitForMaskDisappear();

		clickButtonAddCall();
                waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);
		
		clickCallTemplate("Other Subject");
		
		waitForExtJSAjaxComplete(20);
		
		
		List<String> values = Arrays.asList("002", "HDClntOrgCus1", "MCS","001","slnmClntOrgCus","slnmClntOrgCus2");
		
		List<String> customerList = new ArrayList<String>();
		
		customerList.addAll(values);
		
		waitForExtJSAjaxComplete(20);
		
		List<String> customerLookUpVals= getCustomerLookUpValues();
		  
		softAssert.assertTrue(customerLookUpVals.containsAll(customerList),"Expected customer look up values are populated");
		
		clickCANCELInLookupWindow();
		
		waitForExtJSAjaxComplete(20);
		
		
		waitForExtJSAjaxComplete(20);
		
		setSubject(subject);
		
		setPriority(priority);

		setNature(nature);
		
		waitForExtJSAjaxComplete(20);
		
		List<String> costCenterList = new ArrayList<String>();
		costCenterList.add("myMCS Default Cost Center");
		  
		List<String> costCenterVals= getCostCenterLookUpValues();
		  
		softAssert.assertTrue(costCenterVals.containsAll(costCenterList),"Expected cost center are populated");
		
		waitForExtJSAjaxComplete(20);
		
		clickCANCELInLookupWindow();
		
		waitForExtJSAjaxComplete(20);
		
		List<String> glAccList = new ArrayList<String>();
		glAccList.add("Billing Rule Default");
		glAccList.add("myMCS Default GL Account");

		List<String> glAccVals= getGLAccLookUpValues();
		softAssert.assertEquals(glAccVals,glAccList, "Expected GL accounts are populated");
		
		waitForExtJSAjaxComplete(20);
		
		clickCANCELInLookupWindow();
		
		waitForExtJSAjaxComplete(20);
		
		clickBookCall();
		
		waitForExtJSAjaxComplete(20);
		
		collapseDetailsPanel(DETAILS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		//navigate to home page
		driver.navigate().to(configuration.getApplicationUrl());
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("HelpDesk");

		waitForExtJSAjaxComplete(20);
		
		clickXPath(XPATH_BACKOFFICE);
		waitForExtJSAjaxComplete(20);
                waitForMaskDisappear();

        filterGridBackoffice("@realid", "mogrid", subject, "Reference");
        waitForExtJSAjaxComplete(25);
        
        waitForMaskDisappear();

		boolean isGridRowPresent = Grid.isRowInGridPresent(driver,subject,"@realid","mogrid");
		
		softAssert.assertTrue(isGridRowPresent,"Record was added");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Thread.sleep(5000);
		
		//openCallDetailsDialog(subject);
		
		Grid.checkRowInGriByTextValueAndClick(driver,"@realid","mogrid",subject);
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab(DETAILS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		String text=getSubjectInGeneralTab(callSubject,DETAILS_WINDOW_HEADER);

		softAssert.assertEquals(text, subject, "subject is displayed");
		
		text=getPriorityInGeneralTab(callPriority,DETAILS_WINDOW_HEADER);
		
		softAssert.assertEquals(text, priority, "Call prority is displayed");
		
		text=getNatureInGeneralTab(callNature,DETAILS_WINDOW_HEADER);

		softAssert.assertEquals(text, nature, "Call nature is displayed");
		
		waitForExtJSAjaxComplete(20);
		
		text=getCallerInGeneralTab(callerName,DETAILS_WINDOW_HEADER);
		
		softAssert.assertTrue((text.contains(callName)||text.contains(callNameFnLnFormat)), "Caller name is displayed ");
		
		text=getCustomerInGeneralTab(callerComp,DETAILS_WINDOW_HEADER);
		
		softAssert.assertEquals(text, compName, "Company name is displayed ");
		
		waitForExtJSAjaxComplete(20);
		
		closeUsingToolBar(DETAILS_WINDOW_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("Record is added to the call overview successfully.", true);
		
		
	}

	/**
	 * TestCase Id: C73966
	 * Author : MMA
	 */

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testShowandHideAggregatesofCallOverview() throws Exception{

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:C61225: Show and Hide Aggregates of Call Overview </span><br>", true );

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testShowandHideAggregatesofCallOverview");

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");

		waitForExtJSAjaxComplete(20);

		clickXPath(XPATH_BACKOFFICE);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		HelpDeskFrontOfficePageObject obj = new HelpDeskFrontOfficePageObject();

		obj.collapseDetailsPanel();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "1preCallSub","@class", "helpdesk"),"Overview information is displayed.");

		expandAggregatespanel();

		waitForExtJSAjaxComplete(20);

		clickOnChangeDropDown("Change");

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isItemPresentInChangeDropDown("Sum of Aggregate (Details)"), "change menu includes Sum of aggregates field");

		softAssert.assertTrue(isItemPresentInChangeDropDown("Average of Aggregate (Details)"), "change menu includes Average of aggregates field");

		softAssert.assertTrue(isItemPresentInChangeDropDown("Minimum of Aggregate (Details)"), "change menu includes Minimum of aggregates field");

		softAssert.assertTrue(isItemPresentInChangeDropDown("Maximum of Aggregate (Details)"), "change menu includes Maximum  of aggregates field");

		softAssert.assertTrue(isItemPresentInChangeDropDown("Sum of Sum (Details)"), "change menu includes Sum of Sum  field");

		softAssert.assertTrue(isItemPresentInChangeDropDown("Average of Sum (Details)"), "change menu includes Average of Sum  field");

		softAssert.assertTrue(isItemPresentInChangeDropDown("Minimum of Sum (Details)"), "change menu includes Minimum of Sum  field");

		softAssert.assertTrue(isItemPresentInChangeDropDown("Maximum of Sum (Details)"), "change menu includes Maximum  of Sum  field");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		int changeListSize = getChangeListCount();

		clickOnItemInChangeDropDown("Show All");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(changeListSize, getAggregatePanelListCount(), "	All aggregates are shown.");

		waitForExtJSAjaxComplete(20);

		clickOnChangeDropDown("Change");

		waitForExtJSAjaxComplete(20);

		clickOnItemInChangeDropDown("Hide All");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(getAggregatePanelText().equals("You can show specific aggregate values with the 'Change' menu."), "None of aggregates is shown. All aggregates are hidden");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickOnChangeDropDown("Change");

		clickOnItemInChangeDropDown("Sum of Aggregate (Details)" );

		clickOnItemInChangeDropDown("Average of Aggregate (Details)" );

		waitForExtJSAjaxComplete(20);

		clickOnChangeDropDown("Change");

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isItemPresentInAggregatePanel("Sum of Aggregate (Details)", "helpdesk"), "Chosen fields is shown in 'Aggregates' section");

		softAssert.assertTrue(isItemPresentInAggregatePanel("Average of Aggregate (Details)", "helpdesk"), "Chosen fields is shown in 'Aggregates' section");

		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(isItemPresentInAggregatePanel("Average of Sum (Details)", "helpdesk"), "UnChecked Fields is hidden in 'Aggregates' section");

		softAssert.assertFalse(isItemPresentInAggregatePanel("Sum of Sum (Details)", "helpdesk"), "UnChecked Fields is hidden in 'Aggregates' section");

		softAssert.assertAll();

		Reporter.log("Show and Hide Aggregrates of Call Overview are successful   ",true);

	}

	/**
	 * TestCase Id: C73949
	 * Author : MMA
	 */

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testCriticalityAndAccessInstructions( ) throws Exception{

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:C61208: Criticality and Access Instruction</span><br>", true );
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testCriticalityAndAccessInstructions");

		int numb = 2;
		String callRef="1preCallSub";
		String mainObjectRef2 = "1preMnObRef";
		String mainObjectRef1 = "MMA_MObjRef" ;
		String Criticality = "Criticality1";
		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_BACKOFFICE);

		waitForExtJSAjaxComplete(20);

		waitForMaskDisappear();

		HelpDeskFrontOfficePageObject obj= new HelpDeskFrontOfficePageObject();

		obj.collapseDetailsPanel();

		Grid.checkRowInGriByTextValueAndClick(driver, callRef);

		waitForMaskDisappear();

		clickMaintenanceObjectTab();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickAddMaintanceObject();

		waitForExtJSAjaxComplete(20);

		setMaintenanceObject(mainObjectRef2 );

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridPresentLike(driver, "1preMnObRef", "@class", "hdwo-details") ,"Maintenance Object is present"+mainObjectRef2);

		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(Grid.isRowInGridPresentLike(driver, "1preMnObRef|Criticality1", "@class", "hdwo-details") ,"Maintenance Object is present"+mainObjectRef2+" but no criticality");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		driver.findElement(By.xpath("//div[contains(@class,'resizable-pinned') and contains(@style,'visibility: visible')]//div[text()='1preMnObRef']")).click();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isButtonEnabled("Details"),"Details button is enabled");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(isButtonEnabled("Access Instructions"),"Access Instructions button is novisible");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickButtonInCallDetailsWindow("Details");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'shadow') and contains(@style,'display: block')]/following-sibling::div[contains(@class, 'x-window x-resizable-pinned')]//span[contains(@class, 'x-window-header-text')]")).getText(), "Maintenance Object Details");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getMaintenanceObjFieldValue("Criticality",numb,"0")," ");

		waitForExtJSAjaxComplete(20);

		closeXWindow();

		waitForExtJSAjaxComplete(20);

		clickAddMaintanceObject();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setMaintenanceObject(mainObjectRef1 );

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridPresentLike(driver, "MMA_MObjRef|Criticality1", "@class", "hdwo-details") ,"Maintenance Object is present" +mainObjectRef1+" with criticality "+Criticality);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		driver.findElement(By.xpath("//div[contains(@class,'resizable-pinned') and contains(@style,'visibility: visible')]//div[text()='MMA_MObjRef']")).click();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isButtonEnabled("Details"),"Details button is enabled");

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isButtonEnabled("Access Instructions"),"Access Instructions button is enabled");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickButtonInCallDetailsWindow("Access Instructions");

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getWindowHeaderInCallDatails(), "Access Instructions");

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getAccessInstructionsWindowText(),"Maintenance object is regarding Lift maintenance");

		waitForExtJSAjaxComplete(20);

		closeXWindow();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickButtonInCallDetailsWindow("Details");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getWindowHeaderInCallDatails(), "Maintenance Object Details");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		softAssert.assertEquals(getMaintenanceObjFieldValue("Criticality",numb,"0"),"Criticality1");

		waitForExtJSAjaxComplete(20);

		closeXWindow();

		softAssert.assertAll();

		Reporter.log("Criticality and Access Instruction are displayed accordingly ",true);

	}

	/**
	 * Test Case ID : C73937
	 * Author : MMA
	 * @throws Exception
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testSLAUpdationInCall() throws Exception{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Update SLA info of the Call: C73937 </span><br>", true);

		String subject =  "test auto sub" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority =  "1prePrior";
		String nature="Default Nature";
		String possibleAction = "Open";
		String possibleAction1 = "Finish";
		String possibleAction2 = "Archive";
		String customer = "MCS";
		String slaRef = "HDSLARef";
		String colNameArr[] = {"Name", "Start Status", "End Status", "High Values Are"};


		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testAddActionsFromLayout");
		
		//expandAdministration();
		
		clickAdministration();

		waitForExtJSAjaxComplete(20);

		expandModuleSettings();
		
		waitForExtJSAjaxComplete(20);

		expandHelpDesk();

		waitForExtJSAjaxComplete(20);
		
		click(XPATH_SLA);
		
		waitForExtJSAjaxComplete(15);
		waitForExtJSAjaxComplete(15);

		WebElement element = Grid.checkRowInGriByTextValueAndClick(driver, slaRef);
		SlaManagementPageObject objSLA = new SlaManagementPageObject();
		objSLA.doubleClick(element);
		waitForExtJSAjaxComplete(5);

		objSLA.clickMetricsTab();
		waitForExtJSAjaxComplete(10);

		List<String> slaStartStatus = getSLAFieldValuesByRow("@id",getXWindowId("SLA Object"),colNameArr[1]);
		List<String> slaEndStatus = getSLAFieldValuesByRow("@id",getXWindowId("SLA Object"),colNameArr[2]);
		List<String> slaValues = getSLAFieldValuesByRow("@id",getXWindowId("SLA Object"),colNameArr[3]);

		closeXWindow();
		waitForExtJSAjaxComplete(3);
		
		//closeModule("SLA Management");
		//waitForExtJSAjaxComplete(3);
		closeModule("Administration");
		waitForExtJSAjaxComplete(5);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(5);

		expandSubMainMenu("HelpDesk");
		waitForExtJSAjaxComplete(10);

		clickXPath(XPATH_NEWCALL);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(20);

		clickCallTemplate("Other Subject");
		waitForExtJSAjaxComplete(10);

		setSubject(subject);
		setPriority(priority);
		setNature(nature);
		waitForExtJSAjaxComplete(5);

		setCustomer(customer);
		waitForExtJSAjaxComplete(5);

		clickBookCall();
		waitForExtJSAjaxComplete(10);

		clickXPath(XPATH_BACKOFFICE);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		waitForMaskDisappear();

		HelpDeskFrontOfficePageObject obj = new HelpDeskFrontOfficePageObject();
		obj.collapseDetailsPanel();
		waitForExtJSAjaxComplete(20);

		this.filterGridForCall("@realid", "mogrid", subject, "Reference");
		waitForExtJSAjaxComplete(25);
		
		openCallDetailsDialog(subject);
		waitForExtJSAjaxComplete(20);

		clickTrackingTab(DETAILS_WINDOW_HEADER);
		waitForExtJSAjaxComplete(10);

		clickTrackingSLATab();
		waitForExtJSAjaxComplete(20);

		//MERTIC 0 (In Preparation -> In Progress)
		List<String> slaCallStatus = getSLAFieldValuesByRow("@realid","HDSLAGrid","Chrono");

		softAssert.assertEquals(slaCallStatus.get(0), "Running"," SLA is Running according SLA Config");
		
		softAssert.assertEquals(slaCallStatus.get(1), "not started"," SLA is not started based on configurations");

		WorkOrderPageObject objWO = new WorkOrderPageObject();
		String callStartStatus = objWO.getToolBarStatuses("column-status");
		softAssert.assertEquals(callStartStatus,slaStartStatus.get(0),"call Status is according mertic_0 i.e INITIAL(start_status is In Preparation )");

		clickTrackingHistoryTab();
		waitForExtJSAjaxComplete(10);

		clickAddAction();
		waitForExtJSAjaxComplete(10);

		clickPossibleActions(possibleAction);
		waitForExtJSAjaxComplete(3);

		softAssert.assertEquals(getResultingStatusInAddActionWin(),slaEndStatus.get(0),"Resulting status field value changes according to SLA metric_0 i.e INITIAL(end_status is In Progress).");
		waitForExtJSAjaxComplete(5);

		saveCloseAction();
		waitForExtJSAjaxComplete(10);

		clickTrackingSLATab();
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getValuesFromTransactionLine("@realid","HDSLAGrid","Chrono","first"),"Stopped","Metric_0 i.e INITIAL is updated in call(ended) acc to SLA config");

		String callSLAIndc = getToolBarSLAStatuses("column-sla-indicator");
		//TODO: SLA indicator is in reverse 
		softAssert.assertEquals(callSLAIndc, slaValues.get(1).toLowerCase(),"");

		//Step1 (Finished -> Archived)
		clickTrackingHistoryTab();
		waitForExtJSAjaxComplete(20);

		clickAddAction();
		waitForExtJSAjaxComplete(10);

		clickPossibleActions(possibleAction1);
		waitForExtJSAjaxComplete(10);

		saveCloseAction();
		waitForExtJSAjaxComplete(20);

		clickTrackingSLATab();
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getValuesFromTransactionLine("@realid","HDSLAGrid","Chrono","last"),"Running","Metric_1 i.e Step1 is updated in call(started) acc to config");

		callStartStatus = objWO.getToolBarStatuses("column-status");

		softAssert.assertEquals(callStartStatus,slaStartStatus.get(1),"call Status is according mertic_1 i.e Step1(start_status is Finished )");

		clickTrackingHistoryTab();
		waitForExtJSAjaxComplete(20);

		clickAddAction();
		waitForExtJSAjaxComplete(10);

		clickPossibleActions(possibleAction2);
		waitForExtJSAjaxComplete(10);

		softAssert.assertEquals(getResultingStatusInAddActionWin(),slaEndStatus.get(1),"Resulting status field value changes according to SLA metric_1 i.e step1 (end_status is Archived).");
		waitForExtJSAjaxComplete(5);

		saveCloseAction();
		waitForExtJSAjaxComplete(20);

		clickTrackingSLATab();
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getValuesFromTransactionLine("@realid","HDSLAGrid","Chrono","last"),"Stopped","Metric_1 i.e step1 is updated in call(ended) acc to config");

		callSLAIndc = getToolBarSLAStatuses("column-sla-indicator");
		//TODO : SLA indicator is in reverse 
		softAssert.assertEquals(callSLAIndc, slaValues.get(0).toLowerCase(),"");

		softAssert.assertAll();

		Reporter.log("Update SLA info of the Call", true);
	}

	/**
	 * Test Case ID : C73985, C73984
	 * Author : MMA
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testClientOrganizationRestriction() throws Exception{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Client Organization restrictions for the Helpdesk and on booking new call: C73985, C73984 </span><br>", true);

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testClientOrganizationRestriction");
		waitForExtJSAjaxComplete(10);

		String subject =  "test auto sub" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority =  "1prePrior";
		String nature="Default Nature";
		String user2 = "HD_ClientAcc";
		String[] empFirstName = {"HDClnt"};
		String[] contactFirstName = {"HDClient"};
		String password = "qwerty";
		String clintOrg = "HDClientOrg";
		String[] clintOrgCust = {"HDClntOrgCus1"};
		String[] linkedProj = {"HDClntAccPrj"};
		String[] deprtName = {"HDDept"};
        String area = "HDArea";
        
		waitForExtJSAjaxComplete(10);
		HelpDeskAccessTestSuit objHDFO = new HelpDeskAccessTestSuit();
		objHDFO.navigateToMainPage(user2,password); 
		waitForExtJSAjaxComplete(10);

		//C73985 (New Call)
		waitAndClick(XPATH_ADMINISTRATION);	
		waitForExtJSAjaxComplete(20);

		expandMasterData();
		waitForExtJSAjaxComplete(20);

		ClientOrganizationsPageObject objCO = new ClientOrganizationsPageObject();
		objCO.clickClientOrganizations();
		waitForExtJSAjaxComplete(20);

		SchedulerPageObject objSch = new SchedulerPageObject();
		objSch.expandParentNodeInTreeByTextValue(clintOrg);
		waitForExtJSAjaxComplete(5);

		objSch.expandChildNodeInTreeByTextValue(clintOrgCust[0]);
		waitForExtJSAjaxComplete(5);

		List<String> adminLocations = new ArrayList();
				
		waitForExtJSAjaxComplete(5);
		
		//TODO: Buldings, sites, floors are removed in the Location List
		//adminLocations.add(area);
		adminLocations.addAll( getAllSitesOfClientOrg(clintOrgCust[0]));

		closeModule("Administration");
		waitForExtJSAjaxComplete(5);

		expandAdministration();
		waitForExtJSAjaxComplete(10);

		expandSubMainMenu("HelpDesk");
		waitForExtJSAjaxComplete(10);

		clickXPath(XPATH_NEWCALL);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(20);

		clickCallTemplate("ClientAccTemp");
		waitForExtJSAjaxComplete(10);

		setSubject(subject);
		setPriority(priority);
		setNature(nature);
		waitForExtJSAjaxComplete(10);

		//Location Lookup restriction
		List<String> locationVals=getLocationLookUpValues("NEW_LOCATION");
		waitForExtJSAjaxComplete(5);

		//clickCANCELInLookupWindow();
		clickButtonInLookupNewUI("Cancel");
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(locationVals, adminLocations,"The Location lookup is restricted via Client Organization");

		//Project Lookup Restriction
		List<String> projectVals = clickandGetLookUpValues("NEW_PROJECT","Select a Project","Project");
		waitForExtJSAjaxComplete(5);

		clickCANCELInLookupWindow();
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(projectVals.toArray(),linkedProj,"Projects Lookup contains Only Projects that belong to the Client Organization");

		//Companies Lookup UDI field
		List<String> compnyVals = clickandGetLookUpValues("udiValues.U_COMPANIES_UDI","Select a Company","Reference");
		waitForExtJSAjaxComplete(5);

		clickCANCELInLookupWindow();
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(compnyVals.toArray(), clintOrgCust,"UDI fields of type 'Companies lookup' contains Only Companies that have a 'Customer' role and belong to the Client Organization");

		//Contacts Lookup UDI field
		List<String> contactsVals = clickandGetLookUpValues("udiValues.U_CONTACTS_UDI","Select a Person","First Name");
		waitForExtJSAjaxComplete(5);

		clickCANCELInLookupWindow();
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(contactsVals.toArray(), contactFirstName,"UDI fields of type 'Contacts lookup' contains Only Contacts that belong to Companies linked to a Client Organization");

		//Employees Lookup UDI field
		List<String> EmpVals = clickandGetLookUpValues("udiValues.U_EMPLOYEES_UDI","Select a Person","First Name");
		waitForExtJSAjaxComplete(5);

		clickCANCELInLookupWindow();
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(EmpVals.toArray(), empFirstName,"UDI fields of type 'Employees lookup' contains Only Employees that belong to Companies linked to a Client Organization");

		//Departments Lookup UDI field
		List<String> DeprtVals = clickandGetLookUpValues("udiValues.U_DEPARTMENTS_UDI","Select a Department","Code");
		waitForExtJSAjaxComplete(5);

		clickCANCELInLookupWindow();
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(DeprtVals.toArray(), deprtName,"UDI fields of type 'Departments lookup' contains Only Departments that belong to Companies linked to a Client Organization");

		clickBookCall();
		waitForExtJSAjaxComplete(10);

		//C73984 (Back Office)
		clickXPath(XPATH_BACKOFFICE);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(20);

		HelpDeskFrontOfficePageObject obj = new HelpDeskFrontOfficePageObject();
		obj.collapseDetailsPanel();
		waitForExtJSAjaxComplete(10);

		this.filterGridForCall("@realid", "mogrid", subject, "Reference");
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver,subject, "@class", "helpdesk"),"The Calls Overview is displayed");

		openCallDetailsDialog(subject);
		waitForExtJSAjaxComplete(10);

		clickGeneralTab(DETAILS_WINDOW_HEADER);
		waitForExtJSAjaxComplete(20);

		//Location lookup restriction(BO) 
		locationVals=getLocationLookUpValues("location");
		waitForExtJSAjaxComplete(5);

		//clickCANCELInLookupWindow();
		clickButtonInLookupNewUI("Cancel");
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(locationVals, adminLocations,"The Location lookup is restricted via Client Organization");


		//Project Lookup Restriction(BO)
		projectVals = clickandGetLookUpValues("project","Select a Project","Project");
		waitForExtJSAjaxComplete(5);

		clickCANCELInLookupWindow();
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(projectVals.toArray(), linkedProj,"Projects Lookup contains Only Projects that belong to the Client Organization");

		//Companies Lookup UDI field(BO)
		compnyVals = clickandGetLookUpValues("udiValues.U_0000000015","Select a Company","Reference");
		waitForExtJSAjaxComplete(5);

		clickCANCELInLookupWindow();
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(compnyVals.toArray(), clintOrgCust,"UDI fields of type 'Companies lookup' contains Only Companies that have a 'Customer' role and belong to the Client Organization");

		//Contacts Lookup UDI field(BO)
		contactsVals = clickandGetLookUpValues("udiValues.U_0000000016","Select a Person","First Name");
		waitForExtJSAjaxComplete(5);

		clickCANCELInLookupWindow();
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(contactsVals.toArray(), contactFirstName,"UDI fields of type 'Contacts lookup' contains Only Contacts that belong to Companies linked to a Client Organization");

		//Employees Lookup UDI field(BO)
		EmpVals = clickandGetLookUpValues("udiValues.U_0000000018","Select a Person","First Name");
		waitForExtJSAjaxComplete(5);

		clickCANCELInLookupWindow();
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(EmpVals.toArray(), empFirstName,"UDI fields of type 'Employees lookup' contains Only Employees that belong to Companies linked to a Client Organization");

		//Departments Lookup UDI field(BO)
		DeprtVals = clickandGetLookUpValues("udiValues.U_0000000017","Select a Department","Code");
		waitForExtJSAjaxComplete(5);

		clickCANCELInLookupWindow();
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(DeprtVals.toArray(), deprtName,"UDI fields of type 'Departments lookup' contains Only Departments that belong to Companies linked to a Client Organization");

		softAssert.assertAll();

		Reporter.log("Client Organization restrictions for the Helpdesk and on booking new call", true);
	}

	/**
	 * Test Case ID : C73970
	 * Author : MMA
	 * @throws Exception
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)  
	public void testAddActionsFromLayout() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Add Action form layout:C73970 </span><br>", true);

		String subject =  "test auto sub" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority =  "1prePrior";
		String nature="Default Nature";
		String resultingStatus = "In Progress";
		String possibleAction = "Open";
		String assigned ="SELENIUM";
		String contact = "1preContactLast";
		String actionTime = "00:30";
		String actionDate = "05-09-2016";
		String[] inpreparationPossibleActions = {"Finish Immediately","Open"};
		String[] buttons = {"Add Action", "Add Action and Close", "Close"};
		String[] lookups = {"Assigned To", "Contact "};
		String warningMsg = "The action date must be later than the previous action";
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testAddActionsFromLayout");
		waitForExtJSAjaxComplete(10);

		expandAdministration();
		waitForExtJSAjaxComplete(10);

		expandSubMainMenu("HelpDesk");
		waitForExtJSAjaxComplete(10);

		clickXPath(XPATH_NEWCALL);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(20);

		clickCallTemplate("Other Subject");
		waitForExtJSAjaxComplete(10);

		setSubject(subject);
		setPriority(priority);
		setNature(nature);
		waitForExtJSAjaxComplete(10);

		clickBookCall();
		waitForExtJSAjaxComplete(10);

		clickXPath(XPATH_BACKOFFICE);
		waitForExtJSAjaxComplete(20);
		waitForMaskDisappear();

		HelpDeskFrontOfficePageObject obj = new HelpDeskFrontOfficePageObject();
		obj.collapseDetailsPanel();
		waitForExtJSAjaxComplete(10);

		this.filterGridForCall("@realid", "mogrid", subject, "Reference");
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(10);

		openCallDetailsDialog(subject);
		waitForExtJSAjaxComplete(20);

		clickTrackingTab(DETAILS_WINDOW_HEADER);
		waitForExtJSAjaxComplete(10);

		clickTrackingHistoryTab();
		waitForExtJSAjaxComplete(20);

		clickAddAction();
		waitForExtJSAjaxComplete(10);

		dragAndDropActionWindows(ADD_ACTION_WINDOW_HEADER, DETAILS_WINDOW_HEADER);
		waitForExtJSAjaxComplete(10);

		softAssert.assertEquals(getAllPossibleActions().toArray(),inpreparationPossibleActions, "Possible Actions list is displayed");
		softAssert.assertTrue(isFieldDisabledInAddActionWin("actionResultStatus"),"Resulting status is displayed and read-only");
		waitForExtJSAjaxComplete(3);

		softAssert.assertTrue(verifyDateandTimeFieldsInAddActionWindow("ux-datetime-date"),"Date field is present");
		softAssert.assertTrue(verifyDateandTimeFieldsInAddActionWindow("ux-datetime-time"),"Time field is present");
		waitForExtJSAjaxComplete(3);

		softAssert.assertTrue(isSendNotificationCheckBoxPresent(),"Send Notification checkbox is present");
		waitForExtJSAjaxComplete(3);

		softAssert.assertTrue(verifyButtonsInAddActionWindow(buttons[0]),buttons[0]+" is present in add action wndow");
		softAssert.assertTrue(verifyButtonsInAddActionWindow(buttons[1]),buttons[1]+" is present in add action wndow");
		softAssert.assertTrue(verifyButtonsInAddActionWindow(buttons[2]),buttons[2]+" is present in add action wndow");
		waitForExtJSAjaxComplete(3);

		softAssert.assertTrue(verifyAddActionWindowLabelLookup(lookups[0]).contains("lookup"),lookups[0]+"Lookup field is present");
		softAssert.assertTrue(verifyAddActionWindowLabelLookup(lookups[1]).contains("lookup"),lookups[1]+"Lookup field is present");
		waitForExtJSAjaxComplete(3);

		clickPossibleActions(possibleAction);
		waitForExtJSAjaxComplete(10);

		softAssert.assertEquals(getResultingStatusInAddActionWin(),resultingStatus,"Resulting status field value changes according to selected Action.");
		waitForExtJSAjaxComplete(5);

		setActionTime( actionTime);
		waitForExtJSAjaxComplete(5);
		setActionDate(actionDate);
		waitForExtJSAjaxComplete(5);

		clickAddActionForCall();
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(getAddActionWinWarningMsg().contains(warningMsg) ,"Date and Time cannot be earlier than previous Action's.");
		clickWarningWinButton();
		waitForExtJSAjaxComplete(5);

		setActionAssigned(assigned);
		waitForExtJSAjaxComplete(5);

		setActionContact(contact);
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(getActionAssigned(ADD_ACTION_WINDOW_HEADER).contains(assigned),"Appropriate lookups are opened and persons can be successfully added to the fields.");
		waitForExtJSAjaxComplete(5);
		softAssert.assertTrue(getActionContact(ADD_ACTION_WINDOW_HEADER).contains(contact),"Appropriate lookups are opened and persons can be successfully added to the fields.");

		softAssert.assertAll();

		Reporter.log("Add Action form layout", true);
	}
	
	/**
	 * Testcase ID : C15333,C15334
	 * Author      : MMA
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testEditDeleteWorkOrderFromCall() throws Exception{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Edit a Workorder from a Call :C15334 </span><br>"
				+ "Test: Delete a Workorder from a Call :C15333 </span><br>", true);

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testEditDeleteWorkOrderFromCall");

		String subjectCall = "callWOLink";
		String subjectWO = "woLinkedToCall";
		String woEditRef = subjectWO + "Edit"; 
		String warngMsg = "Are you sure you wish to delete this Work Order";

		waitForExtJSAjaxComplete(10);
		expandAdministration();
		waitForExtJSAjaxComplete(10);

		expandSubMainMenu("HelpDesk");
		waitForExtJSAjaxComplete(10);

		clickXPath(XPATH_BACKOFFICE);
		waitForExtJSAjaxComplete(20);
		waitForMaskDisappear();

		HelpDeskFrontOfficePageObject obj = new HelpDeskFrontOfficePageObject();
		obj.collapseDetailsPanel();
		waitForExtJSAjaxComplete(10);

		this.filterGridForCall("@realid", "mogrid", subjectCall, "Reference");
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(10);

		openCallDetailsDialog(subjectCall);
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(getWindowTitle("@class","x-resizable").equals("Details"),"Call details gets open");
		waitForExtJSAjaxComplete(5);

		clickWorkOrdersTab();
		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValueAndClick(driver, subjectWO);
		waitForMaskDisappear();

		WorkOrderDetailCRUDTestSuite objWO = new WorkOrderDetailCRUDTestSuite();
		objWO.clickButton("@realid","mogrid","Edit");
		waitForExtJSAjaxComplete(5);

		clickTabInWOWinLinkedToCall(subjectWO,"General");
		waitForExtJSAjaxComplete(15);

		String windowID = objWO.getWindowIdByClass("x-window x-resizable-pinned");
		waitForExtJSAjaxComplete(5);

		objWO.setReferenceGeneralTab(windowID,woEditRef);
		waitForExtJSAjaxComplete(5);

		objWO.clickSaveWorkorder("x-resizable");
		waitForExtJSAjaxComplete(25);

		closeXWindow();
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver,woEditRef,"@class","hdwo-details"),"Edited WO is displayed in WorkOrders Tab");
		waitForExtJSAjaxComplete(5);

		objWO.clickButton("@realid","mogrid","Delete");
		waitForExtJSAjaxComplete(15);

		boolean status=verifyWarningDialogTextMessage(warngMsg);
		softAssert.assertTrue(status,"Error message is displayed.Workorder can be deleted.");
		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("Yes");
		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(Grid.isRowInGridPresent(driver,woEditRef,"@class","hdwo-details"),"WO is Deleted");

		softAssert.assertAll();
		Reporter.log("Edit & Delete Workorder from a Call", true);

	}
}