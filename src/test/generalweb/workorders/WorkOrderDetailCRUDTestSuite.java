package test.generalweb.workorders;



import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import test.generalweb.helpdesk.CallDetailTabsPageObject;
import test.generalweb.helpdesk.HelpDeskBackOfficePageObject;
import test.generalweb.transactions.TransactionsPageObject;
import test.generalweb.warehouse.AddWarehousePageObject;


/**
 * Method with 2 parameter Window detail and input field name deals with WO Edit Screen 
 * Method with 1 paramter input field name deals with WO Add Screen
 * Ex: 1 setPriority(WORKORDER_WINDOW_DETAIL, priority); Edit Screen
 * Ex 2: setPriority(priority); Add WO Screen
 */

public class WorkOrderDetailCRUDTestSuite extends
		WorkOrderPageObject {

	@Test(enabled=true)
	public void testCRUDFileUrlWorkOrder() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: add edit delete file and url to workorder</span><br>: C75033; C75034; C75035", true);

		Reporter.log("User add edit delete file and url to workorder<br>", true);
		

		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCRUDFileUrlWorkOrder");
		
		
		String testWorkOrder = "1preWrkRef";
		
		String urlDescription =  "url descr"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String urlDescriptionEd =  "url descrEd" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String urlRemark =  "url remark" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String urlRemarkEd =  "url remarkEd" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String fileDescription =  "file descr" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String fileDescriptionEd =  "file descrEd"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String fileRemark =  "file remark" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String fileRemarkEd =  "file remarkEd" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String type = "labelen";
		
		String url = "http://test.com";
		String urlEd = "http://tested.com";
		String file = "test.csv";
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		clickXPath(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(25);

		checkRowInGriByTextValueAndClick(driver, testWorkOrder);
		waitForExtJSAjaxComplete(25);
		
		//clickDocumentsTab();
		clickAttachmentsTab();
		
		waitForExtJSAjaxComplete(20);
		
		//clickXPath(OTHER_DOCUMENTS_TAB);
		
		waitForExtJSAjaxComplete(15);
		
		setUrl(url, urlDescription, urlRemark, type);
		
		waitForExtJSAjaxComplete(25);
		
		setFile(file, fileDescription, fileRemark, type);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, urlDescription);
		waitForExtJSAjaxComplete(25);

		clickXPath(EDIT_URL_XPATH);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getUrlDescription(), urlDescription,"url description is wrong");
		
		softAssert.assertEquals(getUrlRemark(), urlRemark,"url remark is wrong");
		
		softAssert.assertEquals(getUrlType(), type,"type is wrong");
		
		editUrl(urlEd, urlDescriptionEd, urlRemarkEd, type);
		waitForExtJSAjaxComplete(25);

		Grid.checkRowInGriByTextValueAndClick(driver, fileDescription);
		waitForExtJSAjaxComplete(25);

		clickXPath(EDIT_URL_XPATH);

		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getUrlDescription(), fileDescription,"file description is wrong");
		
		softAssert.assertEquals(getUrlRemark(), fileRemark,"file remark is wrong");
		
		softAssert.assertEquals(getUrlType(), type,"type is wrong");
		
		editFile(file, fileDescriptionEd, fileRemarkEd, type);
		waitForExtJSAjaxComplete(15);
		waitForExtJSAjaxComplete(25);
		deleteUrl(urlDescriptionEd);
		waitForExtJSAjaxComplete(15);
		waitForExtJSAjaxComplete(25);
		deleteUrl(fileDescriptionEd);
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, urlDescriptionEd),"url can be deleted");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, urlDescription),"url can be deleted");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, fileDescriptionEd),"file can be deleted");
		
		softAssert.assertAll();
		
		Reporter.log("Work order details were edited <br>", true);

	}

	//TODO Bug Raised for Couldnt Delete Activity Labor Tab
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testCRUDActivityWorkOrder() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: create update delete activity workorder: C75020 </span><br>", true);

		Reporter.log("User create update delete activity workorder <br>", true);
		
		String testWorkOrder = "1preWrkRef";
	
		
		String laborQuantity = "2";
		
		String laborQuantityEd = "4";
		
		String product = "3preProdRef";
		
		String productEd = "4preProdRef";
		
		
		int random = (int)(Math.random() * 10)+18;
		
		String laborActivityDate = "11-12-20"+random;
		
		String activityDescription1 = "test des" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String activityDescription = "test des1" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String activityDescriptionEd = "test des Ed" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String laborActivityRegime = "1preAcRgRef";
		
		String activityStartTime = "10:00";
		
		String activityStopTime = "11:00";
		
		String activityNotes =  "test auto" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String activityNotesEd =  "test auto ed" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String activityStatus = "Tentative";
		
		String activityType = "1preAcTpRef";	
		
		String activityWorkForce = "SELENIUM";
		
		String activityRelatedType = "Workorder";
		
//		String activityWorkOrder = "1preWrkRef";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCRUDActivityWorkOrder");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		clickXPath(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(25);
		
		checkRowInGriByTextValueAndClick(driver, testWorkOrder);
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickTimeMaterialTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickTimeMaterialLaborTab();
		
		waitForExtJSAjaxComplete(20);
		
		//add first activity
		clickAddLabor();
		
		waitForExtJSAjaxComplete(20);
		waitForDialogMaskDisappear("//span[contains(@class,'x-window-header-text') and text()='Add Activity']//ancestor::div[contains(@class,'x-window')]");

		setLaborActivityDate(laborActivityDate);
		
		setLaborActivityStartTime(activityStartTime);
		
		setLaborActivityStopTime(activityStopTime);
		
		//setLaborActivityStatus(activityStatus);
		
		setLaborActivityType(activityType);
		
		waitForExtJSAjaxComplete(25);
		
		setLaborActivityWorkForce(activityWorkForce);

		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//setLaborActivityRelatedType(activityRelatedType);
		
		//waitForExtJSAjaxComplete(25);
		
	//setLaborActivityWorkorder(activityWorkOrder);
		
		//waitForExtJSAjaxComplete(25);
		
	//	waitForExtJSAjaxComplete(25);
		
		setLaborDescription(activityDescription1);
		
		waitForExtJSAjaxComplete(25);
				
		saveAndCloseActivity();
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, activityDescription1, "@realid", "WoLaborGrid"),"activity was created");
		
		// try to add second activity and cancel
		clickAddLabor();
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(20);
		
		setLaborActivityDate(laborActivityDate);
		
		setLaborActivityStartTime(activityStartTime);
		
		setLaborActivityStopTime(activityStopTime);
		
		//setLaborActivityStatus(activityStatus);
		
		setLaborActivityType(activityType);
		
		waitForExtJSAjaxComplete(25);
		
		setLaborActivityWorkForce(activityWorkForce);
		
		waitForExtJSAjaxComplete(25);
		
		//setLaborActivityRelatedType(activityRelatedType);
		
		//waitForExtJSAjaxComplete(25);
		
//		setLaborActivityWorkorder(activityWorkOrder);
		
		waitForExtJSAjaxComplete(25);
		
		setLaborDescription(activityDescription);

		cancelActivity(ADD_ACTIVITY_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, activityDescription, "@realid", "WoLaborGrid"),"activity creation was canceled");
		
		//add second activity
		clickAddLabor();
		
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(25);

		setLaborActivityDate(laborActivityDate);
		
		setLaborActivityStartTime(activityStartTime);
		
		setLaborActivityStopTime(activityStopTime);
		
		//setLaborActivityStatus(activityStatus);
		
		setLaborActivityType(activityType);
		
		waitForExtJSAjaxComplete(25);
		
		setLaborActivityWorkForce(activityWorkForce);
		
		waitForExtJSAjaxComplete(25);
		
		//setLaborActivityRelatedType(activityRelatedType);
		
		//waitForExtJSAjaxComplete(25);
		
//		setLaborActivityWorkorder(activityWorkOrder);
		
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
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValueAndClick(driver, "@realid", "WoLaborGrid", activityDescription);
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(20);

		clickEditLabor();
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(20);

		setLaborActivityDate(laborActivityDate);
		
		setLaborActivityStartTime(activityStartTime);
		
		setLaborActivityStopTime(activityStopTime);
		
		//setLaborActivityStatus(activityStatus);
		
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
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@realid", "WoLaborGrid", activityDescriptionEd);
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(20);

		clickDeleteLabor("No");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValueAndClick(driver, "@realid", "WoLaborGrid", activityDescriptionEd);
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(20);

		clickDeleteLabor("Yes");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, activityDescription1, "@realid", "WoLaborGrid"),"another activity was not deleted");
		
		//TODO Bug Raised
		//softAssert.assertTrue(Grid.isRowInGridAbsent(driver, activityDescriptionEd),"activity was deleted");
		
		softAssert.assertAll();
		
		Reporter.log("Activity was succesfully edited and deleted", true);
	}

	//retryanalyzer is disabled because of defect
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class
			)
	public void testCRUDConsumptionWorkOrder() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: create update delete consumption workorder: C75021; C75071 </span><br>", true);

		Reporter.log("User create update delete consumption workorder: C75021; C75071 <br>", true);
		
		
		String testWorkOrder = "1preWrkRef";
				
		String consumptionProduct1 = "1preProdRef";
		
		String consumptionProduct2 = "2preProdRef";
		
		String consumptionQuantity = "2";
		
		String consumptionQuantityEdited = "4";
		
		String consumptionSelect = "From Catalog";
		
		int random = (int)(Math.random() * 10)+18;
		
		String consumptionDate3 = "11-10-20"+random;
		
		String consumptionDate = "11-12-20"+random;
		
		String consumptionDate2 = "11-11-20"+random;
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCRUDConsumptionBackOffice");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		click(XPATH_WORKORDERS);
         waitForExtJSAjaxComplete(25);
        
        waitForExtJSAjaxComplete(25);
        
        waitForExtJSAjaxComplete(25);
        
        setDetailsTabCollapsedMode();
        
        waitForExtJSAjaxComplete(25);
        
        waitForExtJSAjaxComplete(25);

		checkRowInGriByTextValueAndClick(driver, testWorkOrder);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		String windowID = getWindowIdByClass("x-window x-resizable-pinned");
		
		clickTimeMaterialTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickTimeMaterialConsumptionTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
//c19859
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Consumption ID", "@class", "hdwo-details"));
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Activity ID", "@class", "hdwo-details"));
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Product Code", "@class", "hdwo-details"));
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Product Reference", "@class", "hdwo-details"));
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Quantity", "@class", "hdwo-details"));
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Date Consumed", "@class", "hdwo-details"));
		
		//softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Product Group", "@class", "hdwo-details"));
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Cost Item ID", "@class", "hdwo-details"));
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Created By", "@class", "hdwo-details"));
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Created On", "@class", "hdwo-details"));
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Latest Modification By", "@class", "hdwo-details"));
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Latest Modification On", "@class", "hdwo-details"));
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "0000000052|1preProdRef|1|17-02-2014|1preProdCod|AQA SELENIUM", "@class", "hdwo-details"),"consumption grid check");
		
		waitForExtJSAjaxComplete(25);
		
		clickButtonTimeAndMaterialTab(windowID, "Add");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickCancelOnConsumptionDialog(ADD_CONSUMPTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		String prodArray[] = {"11-10-20", "11-12-20", "11-11-20"};
		
		//If Products Are already available in the grid, delete it.
		deleteProductsFromConsumptionGrid(windowID, prodArray, "Date Consumed");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, consumptionDate, "@class", "hdwo-details"),"product was deleted"+consumptionDate3);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, consumptionDate2, "@class", "hdwo-details"),"product is present"+consumptionProduct1);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, consumptionDate3, "@class", "hdwo-details"),"product is present"+consumptionDate2);
			
		waitForExtJSAjaxComplete(25);
		
		
		clickButtonTimeAndMaterialTab(windowID, "Add");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
				
		selectProductInConsumptionWindow(ADD_CONSUMPTION_WINDOW_HEADER, "Select a Product or Service", consumptionProduct2, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		setConsumptionQuantity(consumptionQuantity);
		
		setConsumptionDate(consumptionDate);
		
		saveCloseConsumption(ADD_CONSUMPTION_WINDOW_HEADER);
        waitForExtJSAjaxComplete(25);
               
       waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "hdwo-details", consumptionDate);

		waitForExtJSAjaxComplete(20);
		
		clickButtonTimeAndMaterialTab(windowID, "Edit");
		
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
		
		saveConsumption(EDIT_CONSUMPTION_WINDOW_HEADER);

		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Thread.sleep(10000);
		
		clickButtonTimeAndMaterialTab(windowID, "Add");
		
		waitForExtJSAjaxComplete(20);
		
		selectProductInConsumptionWindow(ADD_CONSUMPTION_WINDOW_HEADER, "Select a Product or Service", consumptionProduct2, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		setConsumptionQuantity(consumptionQuantity);
		
		setConsumptionDate(consumptionDate2);
		
		saveCloseConsumption(ADD_CONSUMPTION_WINDOW_HEADER);
                waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "hdwo-details", consumptionDate2);
		
		clickButtonTimeAndMaterialTab(windowID, "Edit");
		
		waitForExtJSAjaxComplete(20);
		
		setConsumptionQuantity("179");
		
		clickCancelOnConsumptionDialog(EDIT_CONSUMPTION_WINDOW_HEADER);
                waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, "179", "@class", "hdwo-details"),"cancel does not lead to saving");
		
		clickButtonTimeAndMaterialTab(windowID, "Add");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		selectProductInConsumptionWindow(ADD_CONSUMPTION_WINDOW_HEADER, "Select a Product or Service", consumptionProduct2, "Reference");
		
		waitForExtJSAjaxComplete(20);
		
		setConsumptionQuantity(consumptionQuantity);
		
		waitForExtJSAjaxComplete(20);
		
		setConsumptionDate(consumptionDate3);
		
		waitForExtJSAjaxComplete(20);
		
		saveCloseConsumption(ADD_CONSUMPTION_WINDOW_HEADER);
        waitForExtJSAjaxComplete(25);
       
       waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValueAndClick(driver, "@class", "hdwo-details", consumptionDate3);
		
		waitForExtJSAjaxComplete(20);
		
		clickShowProductDoc();
		
		waitForExtJSAjaxComplete(20);
		
		/*softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-resizable')]//div[contains(text(),'test.csv')]"),"doc file is present");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-resizable')]//div[contains(text(),'descriptwww')]"),"doc url description is present");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-resizable')]//div[contains(text(),'remwww')]"),"doc url remark is present");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-resizable')]//div[contains(text(),'remfile')]"),"doc file remark is present");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-resizable')]//div[contains(text(),'descriptfile')]"),"doc file description is present");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-resizable')]//a[contains(text(),'http://www.test.com')]"),"doc url is present");
		*/
		closeXWindow();
        waitForExtJSAjaxComplete(25);
       
       waitForExtJSAjaxComplete(25);
		
		softAssert.assertAll();
		
		Reporter.log("Consumption from call was succesfully edited and deleted", true);
	}
	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testWorkorderAction() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: create/edit workorder actions : C75043; C75042; C75116; C75030 </span><br>", true);

		Reporter.log("User create/edit workorder actions <br>", true);
		

//		String callRef = "3preActionCallSub";
		
		String testWorkOrder = "1preWrkRef";
		
		String possibleAction = "1preAct";
		
		String resultingStatus = "3preWrkOrdStsName";
		
		String possibleAction2 = "frompre1topre2";
		
		String resultingStatus2 = "2preWrkOrdStsName";
		
		String possibleAction3 =  "frompre2topre3";
		
		String actionNote = "action note" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String assigned = "SELENIUM";
		
		String taskForce = "1preTskFrcName";
		
		String supplier = "2preCompName";
	
		String planner = "SELENIUM";

		String workOrderType = "1preWrkTpNm";
		
		String reference = "ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		String description = "description"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		String nature = "Default Nature";
		String workOrderTemplateNormal = "1preNrWrkTmpRef";
		String severity = "1preSeverityRef";
		String priority = "1prePriorityRef";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testWorkorderAction");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		click(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(25);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		/*setWorkOrderType(workOrderType);

		clickNextOnDetailsPanel();

		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();

		setReference(reference);

		setPriority("Default Priority");
		
		setSeverity("Default Severity");
		
		setNature(nature);
		
		waitForExtJSAjaxComplete(5);
				
		clickSaveOrder();*/
		
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
		
		clickSaveOrder();
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickTrackingTab(WORKORDER_PANEL_DETAIL);
		
		waitForExtJSAjaxComplete(20);
		
		clickTrackingHistoryTab();
		
		waitForExtJSAjaxComplete(20);
		
//c16167
		clickEditLastAction();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("OK");
		
//c16107
		clickAddAction();
		
		waitForExtJSAjaxComplete(20);
		
		saveAction(ADD_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(driver.findElements(By.xpath("//*[contains(text(),'no changes')]")).isEmpty());
		
		waitForExtJSAjaxComplete(20);
		
		saveCloseAction(ADD_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(driver.findElements(By.xpath("//*[contains(text(),'no changes')]")).size()==2);
		
		closeAction(ADD_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddAction();
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions(possibleAction2);
		
		setActionNote(actionNote);
		
//c16094		
		softAssert.assertFalse(setActionAssignedWithValidation("auto test right Last"),"user not workforce can not be assigned");
		
		waitForExtJSAjaxComplete(20);
		
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		setActionAssigned("@class", "x-window-noborder",assigned, "Last Name");
		
		waitForExtJSAjaxComplete(25);
		
		setTaskForce(taskForce);
		
		waitForExtJSAjaxComplete(20);
		
		setSupplier(supplier);
		
		waitForExtJSAjaxComplete(20);

		setPlanner(planner);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(getActionType().contains(resultingStatus2),"action type was selected");
		
		saveCloseAction(ADD_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isActionPresent(actionNote),"action is added");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddAction();
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions(possibleAction);
		
		setActionNote(actionNote+"2");
		
		setActionAssigned("@class", "x-window-noborder",assigned, "Last Name");
		
		waitForExtJSAjaxComplete(20);
		
		setTaskForce(taskForce);
		
		waitForExtJSAjaxComplete(20);
		
		setSupplier(supplier);
		
		waitForExtJSAjaxComplete(20);

		setPlanner(planner);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(getActionType().contains(resultingStatus),"action type was selected");
		
		saveAction(ADD_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		closeAction(ADD_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isActionPresent(actionNote+"2"),"action is added");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
//c16106		
		clickEditLastAction();
		
		waitForExtJSAjaxComplete(20);
		
		closeAction(EDIT_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		
		clickEditLastAction();
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions(possibleAction3);
		
		setActionNote(actionNote+"ed");
		
		softAssert.assertTrue(getActionType().contains(resultingStatus),"action type is disabled after edit");
		
		setActionAssigned("@class", "x-window-noborder",assigned, "Last Name");
		
		waitForExtJSAjaxComplete(20);
		
		setTaskForce(taskForce);
		
		waitForExtJSAjaxComplete(20);
		
		setSupplier(supplier);
		
		waitForExtJSAjaxComplete(20);

		setPlanner(planner);
		
		waitForExtJSAjaxComplete(20);

		saveActionEdited(EDIT_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		closeAction(EDIT_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isActionPresent(actionNote+"ed"),"action note is edited");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		clickEditLastAction();
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions(possibleAction3);
		
		setActionNote(actionNote+"ed2");
		
		softAssert.assertTrue(getActionType().contains(resultingStatus),"action type is disabled after edit");
		
		setActionAssigned("@class", "x-window-noborder",assigned, "Last Name");
		
		waitForExtJSAjaxComplete(20);
		
		setTaskForce(taskForce);
		
		waitForExtJSAjaxComplete(20);
		
		setSupplier(supplier);
		
		waitForExtJSAjaxComplete(20);

		setPlanner(planner);
		
		waitForExtJSAjaxComplete(20);
		
		saveCloseAction(EDIT_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(isActionPresent(actionNote+"ed2"),"action note is edited");
				
		softAssert.assertAll();
		
		Reporter.log("Action was succesfully edited and deleted", true);

	}
	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testCRUDAssetMaintenanceObject() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: create delete asset+maintenance object: C75025 </span><br>", true);

		Reporter.log("User create delete asset+maintenance object <br>", true);
		
		String assetRef = "1preAsCtRef1preAsRef";
		
		String assetRefGrid = "1preAsCtRef 1preAsRef";
		
		String assetRef2 = "1preAsCtRef2preAsRef";
		
		String assetRefGrid2 = "1preAsCtRef 2preAsRef";
		
		String assetType2 = "1preAsTpRef";
		
		String assetCode2 = "2preAsCod";
		String assetCode1 = "1preAsCod";
		
		String assetLocation2 = "0slnmEnrgRoom1";
		
		String assetCategory2 = "1preAsCtDesc";
		
		String firstUsedDate2 = "2013-12-25";
		String firstUsedDate1 = "2013-12-24";
		
		String moveable2 = "1";
		
		String mainObjectRef1 = "1preMnObRef";
		
		String mainObjectCode1 = "1preMnObCod";
		
		String mainObjectStatus1 = "Active";
		
		String mainObjectCategory1 = "1preMnObCtRef";
		
		String mainObjectLocation1 = "0slnmEnrgRoom1";
		
		String mainObjectRoom1 = "0slnmEnrgRoom1";
		
		String mainObjectFloor1 = "slnmEnrgFloor1";

		String mainObjectBuilding1 = "slnmEnrgBuilding1";
		
		String mainObjectSite1 = "slnmEnrgSite1";
		
		String mainObjectNature1 = "1preNatureRef";	
		
		String mainObjectNaturePath1 = "Default Nature/1preNatureRef";
				
		String mainObjectRef2 = "2preMnObRef";
		
		String testWorkOrder = "1preWrkRef";
		
		String mainObjectPartRef1 = "1preMnObPtRef";
		
		String mainObjectPartCode1 = "1preMnObPtCod";
		
		String mainObjectPartStatus1 = "Active";

		String mainObjectPartType1 = "1preObPtTpRef";
		
		String mainObjectPartWarranty1 = "testwarranty";
		
		String mainObjectPartWarrantyDate1 = "25-12-2019";
		
		String mainObjectPartLocation1 = "0slnmEnrgRoom1";
		
		String mainObjectPartMaintanceObject1 = "1preMnObRef";
		
		String mainObjectPartNature1 = "1preNatureRef";
		
		String mainObjectPartYearlyCost1 = "2";
		
		
		String mainObjectPartRef2 = "2preMnObPtRef";

				
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCRUDAssetMaintanceObject");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		click(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(25);
		
		checkRowInGriByTextValueAndClick(driver, testWorkOrder);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		clickMaintenanceObjectTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddAsset();
				
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		setAsset(assetRef);
		
		waitForExtJSAjaxComplete(25);
		
		waitForMaskDisappear();
		
		clickAddAsset();
		
		waitForExtJSAjaxComplete(20);
		
		setAsset(assetRef2);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		String tag = "div";
		
		driver.findElement(By.xpath("//*[(contains(@class,'x-tree'))]//"+tag+"[text()='"+assetRefGrid+"']")).click();
		
		clickDetailsMaintanceObject();
		
		Thread.sleep(5000);
		
		softAssert.assertTrue(getMaintanceObjectDetail("Reference").contains(assetRef),"asset  detail reference");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Type").contains(assetType2),"asset detail type");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Code").contains(assetCode1),"asset detail code");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Location").contains(assetLocation2),"asset detail location");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Category").contains(assetCategory2),"asset detail category");
		
		softAssert.assertTrue(getMaintanceObjectDetail("First Used Date").contains(firstUsedDate1),"asset detail first used date");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Moveable").contains(moveable2),"asset detail moveable");
		
		closeDetailsMaintanceObject();
		
		waitForExtJSAjaxComplete(25);
		
		driver.findElement(By.xpath("//*[(contains(@class,'x-tree'))]//"+tag+"[text()='"+assetRefGrid2+"']")).click();
		
		waitForExtJSAjaxComplete(25);
		
		clickDetailsMaintanceObject();
		
		Thread.sleep(5000);
		
		softAssert.assertTrue(getMaintanceObjectDetail("Reference").contains(assetRef2),"asset  detail reference");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Type").contains(assetType2),"asset detail type");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Code").contains(assetCode2),"asset detail code");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Location").contains(assetLocation2),"asset detail location");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Category").contains(assetCategory2),"asset detail category");
		
		softAssert.assertTrue(getMaintanceObjectDetail("First Used Date").contains(firstUsedDate2),"asset detail first used date");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Moveable").contains(moveable2),"asset detail moveable");
		
		closeDetailsMaintanceObject();
		
		waitForExtJSAjaxComplete(25);
		
		clickAddMaintanceObject();
		
		waitForExtJSAjaxComplete(20);
		
		setMaintenanceObject(mainObjectRef1);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddMaintanceObject();
		
		waitForExtJSAjaxComplete(20);
		
		setMaintenanceObject(mainObjectRef2);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		driver.findElement(By.xpath("//*[(contains(@class,'x-tree'))]//"+tag+"[text()='"+mainObjectRef1+"']")).click();
		
		waitForExtJSAjaxComplete(25);
		
		clickDetailsMaintanceObject();
		
		Thread.sleep(5000);
		
		softAssert.assertTrue(getMaintanceObjectDetail("Reference").contains(mainObjectRef1),"maintance odject detail reference");
		
		softAssert.assertEquals(getMaintanceObjectDetail("Code"),mainObjectCode1,"maintance odject detail code");
		
		softAssert.assertEquals(getMaintanceObjectDetail("Status"),mainObjectStatus1,"maintance odject detail status");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Category").contains(mainObjectCategory1),"maintance odject detail category");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Location").contains(mainObjectLocation1),"maintance odject detail location");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Room").contains(mainObjectRoom1),"maintance odject detail room");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Floor").contains(mainObjectFloor1),"maintance odject detail floor");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Building").contains(mainObjectBuilding1),"maintance odject detail building");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Site").contains(mainObjectSite1),"maintance odject detail site");
		
		softAssert.assertEquals(getMaintanceObjectDetail("Nature"),mainObjectNature1,"maintance odject detail nature");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Nature Path").contains(mainObjectNaturePath1),"maintance odject detail nature path");
		
		closeDetailsMaintanceObject();
		
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
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		driver.findElement(By.xpath("//*[(contains(@class,'x-tree'))]//div[text()='"+mainObjectPartRef1+"']")).click();
		
		clickDetailsMaintanceObject();
		
		Thread.sleep(5000);
		
		softAssert.assertTrue(getMaintanceObjectDetail("Reference").contains(mainObjectPartRef1),"maintance object part detail reference");
		
		softAssert.assertEquals(getMaintanceObjectDetail("Code"),mainObjectPartCode1,"maintance object part detail code");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Active").contains(mainObjectPartStatus1),"maintance object part detail status");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Type").contains(mainObjectPartType1),"maintance object part detail type");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Warranty").contains(mainObjectPartWarranty1),"maintance object part detail warranty");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Warranty Date").contains(mainObjectPartWarrantyDate1),"maintance object part detail warranty date");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Location").contains(mainObjectPartLocation1),"maintance object part detail location");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Maintenance Object").contains(mainObjectPartMaintanceObject1),"maintance object part detail maintenance");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Nature").contains(mainObjectPartNature1),"maintance object part detail nature");
		
		softAssert.assertTrue(getMaintanceObjectDetail("Yearly Cost").contains(mainObjectPartYearlyCost1),"maintance object part detail yearly cost");
		
		closeDetailsMaintanceObject();
		
		waitForExtJSAjaxComplete(25);
		
		driver.findElement(By.xpath("//*[(contains(@class,'x-tree'))]//div[text()='"+assetRefGrid+"']")).click();
		
		waitForExtJSAjaxComplete(25);
		
		clickRemoveAsset();
		
		clickOnDialogButton("No");
		
		waitForExtJSAjaxComplete(25);
		
		driver.findElement(By.xpath("//*[(contains(@class,'x-tree'))]//div[text()='"+assetRefGrid+"']")).click();
		
		waitForExtJSAjaxComplete(25);
		
		clickRemoveAsset();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, assetRefGrid),"maintance object can be deleted");
		
		driver.findElement(By.xpath("//*[(contains(@class,'x-tree'))]//div[text()='"+mainObjectPartRef1+"']")).click();
		
		waitForExtJSAjaxComplete(25);
		
		clickRemoveAsset();
		
		clickOnDialogButton("No");
		
		waitForExtJSAjaxComplete(25);
		
		//driver.findElement(By.xpath("//*[(contains(@class,'x-tree'))]//div[text()='"+mainObjectPartRef1+"']")).click();
        softAssert.assertTrue(Grid.isRowInGridAbsent(driver, mainObjectPartRef1),"maintance object can be deleted");
		
		softAssert.assertTrue(isRowInGridPresent(driver, mainObjectRef1),"maintance object is not deleted. Only part is deleted.");
		
		driver.findElement(By.xpath("//*[(contains(@class,'x-tree'))]//div[text()='"+mainObjectRef1+"']")).click();
				
		waitForExtJSAjaxComplete(25);
		
		clickRemoveAsset();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, mainObjectPartRef1),"maintance object can be deleted");
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, mainObjectRef1),"maintance object can be deleted");
		
		driver.findElement(By.xpath("//*[(contains(@class,'x-tree'))]//div[text()='"+mainObjectRef2+"']")).click();
		
		waitForExtJSAjaxComplete(25);
		
		clickRemoveAsset();
		
		clickOnDialogButton("No");
		
		waitForExtJSAjaxComplete(25);
		
		driver.findElement(By.xpath("//*[(contains(@class,'x-tree'))]//div[text()='"+mainObjectRef2+"']")).click();
		
		waitForExtJSAjaxComplete(25);
		
		clickRemoveAsset();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, mainObjectRef2),"maintance object can be deleted");
		
		waitForExtJSAjaxComplete(25);
				
		softAssert.assertAll();
		
		Reporter.log("Maintenance object was succesfully added and deleted", true);
	}

	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testCRUDBillMaterials() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: create delete product bill material workorder: C75024, C75072 </span><br>", true);

		Reporter.log("User create delete product bill material workorder: C75024, C75072 <br>", true);
		
		
		String testWorkOrder = "2preWrkRef";
				
		String billProduct = "6preProdRef";
		
		String billProduct2 = "7preProdRef";
		
		String consumptionQuantity = "2";
		
		String warehouse = "Central Warehouse";
		
		String status = "Requested";
		
		String invalidWarehouseXpath = "//div[contains(@class,'bom-window')]//td[contains(@class,'invalid')]";
		
		
//		int random = (int)(Math.random() * 10)+18;
		
//		String consumptionDate3 = "11-10-20"+random;
		
//		String consumptionDate = "11-12-20"+random;
		
//		String consumptionDate2 = "11-11-20"+random;
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCRUDBillMaterials");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		click(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, testWorkOrder);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickTimeMaterialTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickTimeMaterialBillMaterialTab();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
//c27186
		
		clickAddBM();
		
		selectBMProduct(billProduct2);
		
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		
		clickSaveBM();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		selectBMinGrid(billProduct2);
		
		clickDeleteBM();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		
		clickAddBM();
		
		selectBMProduct(billProduct);
		
		waitForExtJSAjaxComplete(20);
		
		selectBMinGrid(billProduct);
		
		waitForExtJSAjaxComplete(20);
		
		setBMProductQuantity(consumptionQuantity, billProduct);
		
		clickSaveBM();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,invalidWarehouseXpath),"invalid warehouse message is present");
		
		setBMWarehouse(billProduct, warehouse);
		
		waitForExtJSAjaxComplete(20);
		
		clickReset();
		
		clickOnDialogButton("No");
		
		clickSaveBM();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		selectBMinGrid(billProduct);
		
		waitForExtJSAjaxComplete(25);
		
		setBMProductStatus(billProduct, status);
		
		waitForExtJSAjaxComplete(20);
		
		clickShowRelatedDoc();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-resizable')]//div[contains(text(),'test.csv')]"),"docs url and file");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-resizable')]//a[contains(text(),'http://testcons.org')]"),"docs url and file");
		
		closeXWindow("@id", getXWindowId("Related Documents"));
		
		waitForExtJSAjaxComplete(25);
		
		clickSaveBM();
		
		selectBMinGrid(billProduct);
		
		waitForExtJSAjaxComplete(25);
		
		clickDeleteBM();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//div[contains(@class,'bom-window')]//span[contains(text(),'"+billProduct+"')]"),"product can be deleted");
		
		softAssert.assertAll();
		
		Reporter.log("Bill materials can be succesfully edited and deleted", true);
	}

	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testCRUDGeneralDescription() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:edit general and description tab: C75027 </span><br>", true);

		Reporter.log("Test:edit general and description tab: C75027; c27239 <br>", true);
		
		String testWorkOrder = "2preWrkRef";
		
		String testRelatedWorkOrder = "1preWrkRef";
		
		String description = "description";
		
		String note = "note";
		
		String priority = "1prePriorityRef";
		
		String severity = "1preSeverityRef";
		
		String requestedBy = "SELENIUM";
		
		String dueDate = "21-03-2016";

		String dueTime = "00:30";
		

		String plannedStartDate = "21-03-2017";
		
		String plannedStartTime = "00:30";

		String plannedEndDate = "21-03-2018";
		
		String plannedEndTime = "00:30";

		String budget = "EUR";
		
		String estimation = "Hours";
		
		String nature = "2aliasforwebparent";
		
		String location = "slnmEnrgBuilding10";
		
		String customer = "2preCompName";
		
		String project = "1preProjectRef";
		
		String projectPart = "1prePrPartRef";
		
		String call = "1preCallSub";
		
		String assigned = "SELENIUM";
		
		String taskforce = "1preTskFrcName";
		
		String planner = "SELENIUM";
		
		String supplier = "2preCompName";
		
		String budgetNumber = "2";
		
		String duration = "4";
		
		String durationUnit = "Hours";
			
		String remainingUnit = "Hours";
		
		String remaining = "3";
		
		String costcenter = "myMCS Default Cost Center";
		
		String glaccount = "myMCS Default GL Account";
		
		String defaultActivity = "1preAcTpRef";

		String finKey3 = "3preValue";
		
		String finKey4 = "4preValue";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCRUDGeneralDescription");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		click(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);
                
        waitForExtJSAjaxComplete(25);
        
        setDetailsTabCollapsedMode();
        
        waitForExtJSAjaxComplete(25);

		doubleClick(driver.findElement(By.xpath("//*[@class='x-grid3']//div[text()='"+testWorkOrder+"']")));
		waitForDialogMaskDisappear("//span[contains(@class,'x-window-header-text') and text()='Details']//ancestor::div[contains(@class,'x-window x-resizable-pinned')]");

		clickDescription1Tab(WORKORDER_WINDOW_DETAIL);
		
		waitForExtJSAjaxComplete(20);
		
		clickDescription2Tab(WORKORDER_WINDOW_DETAIL);
		
		waitForExtJSAjaxComplete(20);
		
		setDescriptionOrNotes(WORKORDER_WINDOW_DETAIL,description);
		
		clickSaveDescription(WORKORDER_WINDOW_DETAIL);
		waitForDialogMaskDisappear("//span[contains(@class,'x-window-header-text') and text()='Details']//ancestor::div[contains(@class,'x-window x-resizable-pinned')]");

		clickNotesTab(WORKORDER_WINDOW_DETAIL);
		
		setDescriptionOrNotes(WORKORDER_WINDOW_DETAIL,note);
		
		clickSaveDescription(WORKORDER_WINDOW_DETAIL);
		waitForDialogMaskDisappear("//span[contains(@class,'x-window-header-text') and text()='Details']//ancestor::div[contains(@class,'x-window x-resizable-pinned')]");

		clickGeneralTab(WORKORDER_WINDOW_DETAIL);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setPriority(WORKORDER_WINDOW_DETAIL, priority);
		
		waitForExtJSAjaxComplete(20);
		
		setSeverity(WORKORDER_WINDOW_DETAIL, severity);
		
		waitForExtJSAjaxComplete(20);
		
		setRequestedby(WORKORDER_WINDOW_DETAIL, requestedBy);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setDueDate(WORKORDER_WINDOW_DETAIL, dueDate);
		
		waitForExtJSAjaxComplete(20);
		
		setDueTime(WORKORDER_WINDOW_DETAIL, dueTime);
		
		waitForExtJSAjaxComplete(20);
		
		setNature(WORKORDER_WINDOW_DETAIL, nature);
		
//		String customer  = (driver.getCurrentUrl().contains("122"))? "2preCompName":"slnmClntOrgCus";
		
		waitForExtJSAjaxComplete(20);
		
		setCustomer(WORKORDER_WINDOW_DETAIL,customer);
		
		waitForExtJSAjaxComplete(20);
		
		setLocation(WORKORDER_WINDOW_DETAIL,location);
		
		waitForExtJSAjaxComplete(20);
		
		setProject(WORKORDER_WINDOW_DETAIL, project);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(getProject(WORKORDER_WINDOW_DETAIL).contains(project),"project can be selected");
		
		setProjectPart(WORKORDER_WINDOW_DETAIL, projectPart);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(getProjectPart(WORKORDER_WINDOW_DETAIL).contains(projectPart),"project part can be selected");
		
		setCall(WORKORDER_WINDOW_DETAIL, call);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(getCall(WORKORDER_WINDOW_DETAIL).contains(call),"call can be selected");

		setWorkorder(WORKORDER_WINDOW_DETAIL, testRelatedWorkOrder);
		
		waitForExtJSAjaxComplete(20);
		
		setAssigned(WORKORDER_WINDOW_DETAIL, assigned);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setTaskforce(WORKORDER_WINDOW_DETAIL, taskforce);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setPlanner(WORKORDER_WINDOW_DETAIL, planner);
		
		waitForExtJSAjaxComplete(20);
		
		setSupplier(WORKORDER_WINDOW_DETAIL, supplier);
		
		waitForExtJSAjaxComplete(20);
		
		clickSaveWorkorder(WORKORDER_WINDOW_DETAIL);
		waitForExtJSAjaxComplete(25);

        clickTrackingTab(WORKORDER_WINDOW_DETAIL);

		setPlannedStartDate(WORKORDER_WINDOW_DETAIL, plannedStartDate);
		
		waitForExtJSAjaxComplete(20);
		
		setPlannedEndDate(WORKORDER_WINDOW_DETAIL, plannedEndDate);
		
		waitForExtJSAjaxComplete(20);
		
		setPlannedStartTime(WORKORDER_WINDOW_DETAIL, plannedStartTime);
		
		waitForExtJSAjaxComplete(20);
		
		setPlannedEndTime(WORKORDER_WINDOW_DETAIL, plannedEndTime);
		
		waitForExtJSAjaxComplete(20);
		
		setBudgetUnit(WORKORDER_WINDOW_DETAIL, budget);
		
		waitForExtJSAjaxComplete(20);
		
		setBudget(WORKORDER_WINDOW_DETAIL, budgetNumber);
		
		waitForExtJSAjaxComplete(20);
		
//		setEstimatedDuration(WORKORDER_WINDOW_DETAIL, duration);
		
		waitForExtJSAjaxComplete(20);
		
//		setEstimatedDurationUnit(WORKORDER_WINDOW_DETAIL, durationUnit);
		
		waitForExtJSAjaxComplete(20);
		
//		setRemainingTimeUnit(WORKORDER_WINDOW_DETAIL, remainingUnit);
		
		waitForExtJSAjaxComplete(20);
		
//		setRemainingTime(WORKORDER_WINDOW_DETAIL, remaining);
		
		waitForExtJSAjaxComplete(20);
		
		setDefaultActivityType(WORKORDER_WINDOW_DETAIL, defaultActivity);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
			McsElement.getElementByXpath(driver, "//div[contains(@class,'"+WORKORDER_WINDOW_DETAIL+"')]//input[@name='extraWork']").clear();
			McsElement.getElementByXpath(driver, "//div[contains(@class,'"+WORKORDER_WINDOW_DETAIL+"')]//input[@name='extraWork']").sendKeys("5");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
			
		clickSaveWorkorder(WORKORDER_WINDOW_DETAIL);
                waitForDialogMaskDisappear("//div[contains(@class,'"+WORKORDER_WINDOW_DETAIL+"')]");
 
                clickBillingTab(WORKORDER_WINDOW_DETAIL);

		softAssert.assertEquals(getFinKey3(WORKORDER_WINDOW_DETAIL),finKey3,"finkey 3 is ok");

		waitForExtJSAjaxComplete(25);

		softAssert.assertEquals(getFinKey4(WORKORDER_WINDOW_DETAIL),finKey4,"finkey 4 is ok");

		setCostCenter(WORKORDER_WINDOW_DETAIL, costcenter);
		
		waitForExtJSAjaxComplete(20);
		
		setGlAccount(WORKORDER_WINDOW_DETAIL, glaccount);
		
		waitForExtJSAjaxComplete(20);
		
		clickSaveWorkorder(WORKORDER_WINDOW_DETAIL);
		waitForExtJSAjaxComplete(25);


		navigateToMainPage();
		
		waitForExtJSAjaxComplete(25);
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		click(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		doubleClick(driver.findElement(By.xpath("//*[@class='x-grid3']//div[text()='"+testWorkOrder+"']")));
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForDialogMaskDisappear("//div[contains(@class,'"+WORKORDER_WINDOW_DETAIL+"')]");

		clickDescription1Tab(WORKORDER_WINDOW_DETAIL);
		
		waitForExtJSAjaxComplete(20);
		
		clickDescription2Tab(WORKORDER_WINDOW_DETAIL);
		
		waitForExtJSAjaxComplete(20);

		waitForDialogMaskDisappear("//div[contains(@class,'"+WORKORDER_WINDOW_DETAIL+"')]");

		softAssert.assertEquals(getDescriptionOrNotes(WORKORDER_WINDOW_DETAIL, "value"), description,"description can be edited");
		
		clickNotesTab(WORKORDER_WINDOW_DETAIL);
		
		softAssert.assertEquals(getDescriptionOrNotes(WORKORDER_WINDOW_DETAIL, "value"), note,"note can be edited");
		
		clickGeneralTab(WORKORDER_WINDOW_DETAIL);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(testWorkOrder,getReference(WORKORDER_WINDOW_DETAIL),"workorder reference is ok");
		
		softAssert.assertEquals(getPriority(WORKORDER_WINDOW_DETAIL), priority,"priority can be edited");
		
		softAssert.assertEquals(getSeverity(WORKORDER_WINDOW_DETAIL), severity,"severity can be edited");
		
		softAssert.assertLike(getRequestedby(WORKORDER_WINDOW_DETAIL), requestedBy,"requestedby can be edited");
		
		softAssert.assertEquals(getDueDate(WORKORDER_WINDOW_DETAIL, "value"), dueDate,"due date can be edited");
		
		softAssert.assertEquals(getDueTime(WORKORDER_WINDOW_DETAIL, "value"), dueTime,"due time can be edited");
		
		softAssert.assertEquals(getNature(WORKORDER_WINDOW_DETAIL), nature,"nature can be edited");
		
		softAssert.assertEquals(getLocation(WORKORDER_WINDOW_DETAIL), location,"location can be edited");
		
		softAssert.assertEquals(getNature(WORKORDER_WINDOW_DETAIL), nature,"nature can be edited");
		
		softAssert.assertEquals(getCustomer(WORKORDER_WINDOW_DETAIL), customer,"customer can be edited");
		
//		softAssert.assertEquals(getProjectPart(WORKORDER_WINDOW_DETAIL), projectPart,"project part can be edited");
//		
//		softAssert.assertEquals(getProject(WORKORDER_WINDOW_DETAIL), project,"project can be edited");
//		
//		softAssert.assertLike(getCall(WORKORDER_WINDOW_DETAIL), call,"call can be edited");
		
		softAssert.assertLike(getWorkorder(WORKORDER_WINDOW_DETAIL), testRelatedWorkOrder,"workorder can be edited");
		
		softAssert.assertLike(getAssigned(WORKORDER_WINDOW_DETAIL), assigned,"assigned can be edited");
		
		softAssert.assertEquals(getTaskforce(WORKORDER_WINDOW_DETAIL), taskforce,"taskforce can be edited");
		
		softAssert.assertLike(getPlanner(WORKORDER_WINDOW_DETAIL), planner,"planner can be edited");
		
		softAssert.assertEquals(getSupplier(WORKORDER_WINDOW_DETAIL), supplier,"supplier can be edited");
	
         clickTrackingTab(WORKORDER_WINDOW_DETAIL);

		softAssert.assertEquals(getPlannedStartDate(WORKORDER_WINDOW_DETAIL), plannedStartDate,"planned start date can be edited");
		
		softAssert.assertEquals(getPlannedEndDate(WORKORDER_WINDOW_DETAIL), plannedEndDate,"planned end date can be edited");
		
		softAssert.assertEquals(getPlannedStartTime(WORKORDER_WINDOW_DETAIL), plannedStartTime,"planned start time can be edited");
		
		softAssert.assertEquals(getPlannedEndTime(WORKORDER_WINDOW_DETAIL), plannedEndTime,"planned end time can be edited");
		
		softAssert.assertEquals(getBudget(WORKORDER_WINDOW_DETAIL), budgetNumber, "budget can be edited");
		
		softAssert.assertEquals(getBudgetUnit(WORKORDER_WINDOW_DETAIL), budget, "budget number can be edited");
		
	//	softAssert.assertEquals(getEstimatedDuration(WORKORDER_WINDOW_DETAIL), duration, "estimation can be edited");
		
//		softAssert.assertEquals(getEstinatedDurationTimeUnit(WORKORDER_WINDOW_DETAIL), estimation, "estimated duration number can be edited");
		
		
//		softAssert.assertEquals(getRemainingTime(WORKORDER_WINDOW_DETAIL), remaining, "remaining can be edited");
		
//		softAssert.assertEquals(getRemainingTimeUnit(WORKORDER_WINDOW_DETAIL), remainingUnit, "remaining time unit can be edited");
				
		softAssert.assertEquals(getDefaultActivityType(WORKORDER_WINDOW_DETAIL), defaultActivity,"default can be edited");
		
                clickBillingTab(WORKORDER_WINDOW_DETAIL);
		softAssert.assertEquals(getCostCenter(WORKORDER_WINDOW_DETAIL), costcenter,"costcenter can be edited");
		
		softAssert.assertEquals(getGlAccount(WORKORDER_WINDOW_DETAIL), glaccount,"glaccount can be edited");
		
		
		softAssert.assertAll();
		
		Reporter.log("workorder can be succesfully edited ", true);
	}

	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testCRUDChecklists() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: create delete Checklists in workorder: C27266, C91916</span><br>"
				+"Checklist Tab on Workorder Details in Web Portal:", true);

		Reporter.log("User create delete Checklists in workorder: C27266, C91916<br>", true);
		
		
		String testWorkOrder = "2preWrkRef";
		
		String checklistReference = "1chkTmpRef";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCRUDChecklists");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		click(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
        
        waitForExtJSAjaxComplete(25);
        
        setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, testWorkOrder);
		waitForExtJSAjaxComplete(25);
        
        waitForExtJSAjaxComplete(25);
        
        waitForExtJSAjaxComplete(25);
		
		clickChecklistsObjectTab();

		waitForExtJSAjaxComplete(20);
		
		//C91918:
		boolean status = isChecklistInGridPresent(checklistReference);
				
		softAssert.assertTrue(isChecklistInGridPresent(checklistReference)," Checklist is present in the grid.");
				
		
		if(status) {
			selectChecklistGrid(checklistReference);
			
			waitForExtJSAjaxComplete(25);
			
			clickDeleteChecklist();
			
			waitForExtJSAjaxComplete(5);
			
			clickOnDialogButton("Yes");
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertTrue(!isChecklistInGridPresent(checklistReference)," Checklist is still present.");

			waitForExtJSAjaxComplete(20);
			
		}		
		waitForExtJSAjaxComplete(20);
		
		clickAddChecklist();
		
		waitForExtJSAjaxComplete(25);
		
		selectChecklist(checklistReference);
		
		waitForExtJSAjaxComplete(20);
		
		selectChecklistGrid(checklistReference);
		
		waitForExtJSAjaxComplete(20);
		
		clickViewChecklist();
		
		Thread.sleep(8000);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		closeChecklistTab(checklistReference);
		
		waitForExtJSAjaxComplete(20);
		
		/*selectChecklistGrid(checklistReference);
		
		clickDeleteChecklist();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(!isChecklistInGridPresent(checklistReference)," Checklist is still present.");

		waitForExtJSAjaxComplete(20);*/
		
		softAssert.assertAll();
		
		Reporter.log("Checklist can be succesfully added and deleted", true);
	}
	
	/**
	 * Test Case Id : C117828,C120642,C114733,C111748
	 *  Author : MMA 
	 * @throws Exception
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testOrganisationOfTabsAndSubTabs() throws Exception {
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Organisation of tabs and sub-tabs for workorders screen: C117828 </span><br>"
				+ "Verify Add Work order functionality from myMCS Help Desk module : C120642"
				+ "Expense and Billing Tab are displayed with appropriate rights enabled : C114733"
				+ "Access to Web Workorders module : C111748", true);

		String testWorkOrder = "2preWrkRef";
		String callRef = "AqaNegativeConsumptionCall";
		String noViewFinancialRights = "WONoFinancialRights";
		String noWOLicense = "NoWORights";
		String password = "qwerty";
		String workOrderTemplateNormal = "1preNrWrkTmpRef";
		String reference = "woRef"+getCurrentDate().substring(getCurrentDate().length()-5);
		String severity = "1preSeverityRef";
		String priority = "1prePriorityRef";
		String nature = "Default Nature";

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testOrganisationOfTabsAndSubTabs");

		expandAdministration();
		waitForExtJSAjaxComplete(5);

		click(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();
		waitForExtJSAjaxComplete(5);

		Grid.checkRowInGriByTextValueAndClick(driver, testWorkOrder);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(getWindowTitle("@class",WORKORDER_WINDOW_DETAIL).equals("Details"),"Workorders form is opened");
		waitForExtJSAjaxComplete(5);

		//C117828
		clickGeneralTab();
		waitForExtJSAjaxComplete(5);
		softAssert.assertTrue(isXPanelWindowHeaderPresent("@class","details-general","Details - New Tab Sheet"),"General tab includes UDI groups");
		softAssert.assertFalse(isXPanelWindowHeaderPresent("@class","details-general","Financial Keys"),"General tab excludes Financial Keys group");
		softAssert.assertFalse(isXPanelWindowHeaderPresent("@class","details-general","Planning"),"General tab excludes Planning group");

		softAssert.assertTrue(isTabPresentInWOWindow("hdwo-details","Objects"),"Objects tab is present in details WO window");
		softAssert.assertTrue(isTabPresentInWOWindow("hdwo-details","Checklists"),"Checklists tab is present in details WO window");
		softAssert.assertTrue(isTabPresentInWOWindow("hdwo-details","Tracking"),"Tracking tab is present in details WO window");

		clickTrackingTab(WORKORDER_WINDOW_DETAIL);
		waitForExtJSAjaxComplete(15);
		softAssert.assertTrue(isTabPresentInWOWindow("hdwo-details","Planning"),"Planning sub tab is present in details WO window");
		softAssert.assertTrue(isXPanelWindowHeaderPresent("@class","details-general","Planning"),"Planning tab includes Planning group");
		softAssert.assertTrue(isTabPresentInWOWindow("hdwo-details","History"),"History sub tab is present in details WO window");
		softAssert.assertTrue(isTabPresentInWOWindow("hdwo-details","SLA"),"SLA sub tab is present in details WO window");

		clickTimeMaterialTab();
		waitForExtJSAjaxComplete(5);
		softAssert.assertTrue(isTabPresentInWOWindow("hdwo-details","Bill of Materials"),"Bill of Materials sub tab is present in details WO window");
		softAssert.assertTrue(isTabPresentInWOWindow("hdwo-details","Consumption"),"Consumption sub tab is present in details WO window");
		softAssert.assertTrue(isTabPresentInWOWindow("hdwo-details","Labor"),"Labor sub tab is present in details WO window");

		click(EXPENSES_XPATH);
		waitForExtJSAjaxComplete(5);
		softAssert.assertTrue(isTabPresentInWOWindow("hdwo-details","Procurement"),"Procurement sub tab is present in details WO window");
		softAssert.assertTrue(isTabPresentInWOWindow("hdwo-details","Invoices"),"Invoices sub tab is present in details WO window");

		clickBillingTab(WORKORDER_WINDOW_DETAIL);
		waitForExtJSAjaxComplete(15);
		softAssert.assertTrue(isTabPresentInWOWindow("hdwo-details","Parameters"),"Parameters sub tab is present in details WO window");
		softAssert.assertTrue(isXPanelWindowHeaderPresent("@class","details-general","Financial Keys"),"Parameters tab includes Financial Keys");

		softAssert.assertTrue(isTabPresentInWOWindow("hdwo-details","Attachments"),"Attachments tab is present in details WO window");
		softAssert.assertTrue(isTabPresentInWOWindow("hdwo-details","Pictures"),"Pictures tab is present in details WO window");

		clickDescription1Tab(WORKORDER_WINDOW_DETAIL);
		waitForExtJSAjaxComplete(5);
		softAssert.assertTrue(isTabPresentInWOWindow("x-tab-panel-bwrap","Description"),"Description tab is present under Description tab in WO window");
		softAssert.assertTrue(isTabPresentInWOWindow("x-tab-panel-bwrap","Notes"),"Notes tab is present under Description tab in WO window");

		closeUsingToolBar(WO_WINDOW_XPATH);
		waitForExtJSAjaxComplete(10);

		closeModule("Work Order");
		waitForExtJSAjaxComplete(5);

		//C120642
		expandSubMainMenu("HelpDesk");
		waitForExtJSAjaxComplete(5);

		clickXPath(XPATH_BACKOFFICE);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		HelpDeskBackOfficePageObject hdPgObj = new HelpDeskBackOfficePageObject();
		hdPgObj.collapseDetailsPanel("Details");
		waitForExtJSAjaxComplete(5);

		hdPgObj.filterGridForCall("@realid", "mogrid", callRef, "Reference");
		waitForExtJSAjaxComplete(25);

		Grid.checkRowInGriByTextValueAndClick(driver, callRef);
		waitForMaskDisappear();

		softAssert.assertTrue(getWindowTitle("@class",WORKORDER_WINDOW_DETAIL).equals("Details"),"Call details gets open");
		waitForExtJSAjaxComplete(5);

		CallDetailTabsPageObject callPgObj = new CallDetailTabsPageObject();
		callPgObj.clickOnTab("Details","Work Orders");
		waitForExtJSAjaxComplete(5);

		clickButton("@realid","mogrid","Add");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(getXPanelWindowHeader("@class","helpdesk-newcall").equals("Work Order Templates"),"User navigates to select work order template pane");

		expandTemplateGroup();
		waitForExtJSAjaxComplete(20);

		selectTemplate(workOrderTemplateNormal);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(20);

		setReference(reference);
		waitForExtJSAjaxComplete(5);

		setPriority(priority);
		waitForExtJSAjaxComplete(5);

		setSeverity(severity);

		waitForExtJSAjaxComplete(25);

		setNature(nature);
		waitForExtJSAjaxComplete(5);

		clickSaveOrder();
		waitForExtJSAjaxComplete(15);

		callPgObj.clickTabInWOWinLinkedToCall(reference,"General");
		waitForExtJSAjaxComplete(5);

		//TODO: call needs to be populated by default
		setCall(WORKORDER_WINDOW_DETAIL, callRef);
		waitForExtJSAjaxComplete(5);

		clickSaveWorkorder(WORKORDER_WINDOW_DETAIL);
		waitForExtJSAjaxComplete(25);

		callPgObj.closeXWindowOfWOWinLinkedToCall(reference);
		waitForExtJSAjaxComplete(15);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver,reference,"@class","hdwo-details"),"Created WO is desplayed in WorkOrders Tab");
		waitForExtJSAjaxComplete(5);
		logout();

		//C114733
		login(noViewFinancialRights, password);
		waitForExtJSAjaxComplete(5);

		expandAdministration();
		waitForExtJSAjaxComplete(5);

		click(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();
		waitForExtJSAjaxComplete(15);
		
		filterGridWithoutUsingMcsElementModifiedForChrome("@realid", "mogrid", testWorkOrder,"Reference");
		
		waitForExtJSAjaxComplete(15);
		
		Grid.checkRowInGriByTextValueAndClick(driver, testWorkOrder);
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(getWindowTitle("@class",WORKORDER_WINDOW_DETAIL).equals("Details"),"Workorders form is opened");
		waitForExtJSAjaxComplete(5);

		softAssert.assertFalse(isTabPresentInWOWindow("hdwo-details","Expenses"),"On unselect the 'View financial info on workorders' right for the User, Expenses is not displayed");
		softAssert.assertFalse(isTabPresentInWOWindow("hdwo-details","Billing"),"On unselect the 'View financial info on workorders' right for the User, Billing is not displayed");
		logout();

		//C111748
		login(noWOLicense, password);
		waitForExtJSAjaxComplete(15);

		expandAdministration();
		waitForExtJSAjaxComplete(5);

		click(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(getWarningDialogTextMsg().contains("You do not have the License to use the 'Work Order' module"),"Warning message is displayed.");
		softAssert.assertAll();
		Reporter.log("Organisation of tabs and sub-tabs for workorders screen,Access to Web Workorders module is successfully verified", true);
	}	
	
	/**
	 * Test Case ID : C114302,C114299
	 * Author : MMA
	 * @throws Exception
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testMaintenanceObjectUDIFields() throws Exception{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:Disaplaying Maintenance Objects Parts UDI fields in the Work Order : C114302</span><br>"
				+ "Displaying Maintenance Objects UDI fields in the Work Order : C114299", true);

		String testWorkOrder = "SchedulerRelated";
		String refMO = "AQA_MO";
		String refMOPart = "AQA_MO_PartRef";
		List<String> inputUDIsMO = Arrays.asList("MO_UDI_EditBox","MO_UDI_List","MO_UDI_Combo","MO_UDI_Radio","MO_UDI_ItemsLookup");
		List<String> inputUDIsMOValues = Arrays.asList("EditBoxInput","MOList","ComboInput","MORadio","Lookup Text");
		List<String> outputUDIsMO = Arrays.asList("MO_UDI_Output_Edit","MO_UDI_Output_Listbox","MO_UDI_Output_Combo","MO_UDI_Output_Radio","MO_UDI_Output_ItemLookup");
		String specificMOCatgInputUDI = "MO_CAT_1pre_Edit";
		String specificMOCatgInputUDIValue = "MOCatInput";
		String specificMOCatgOutputUDI = "MO_CAT_1pre_output_Edit";
		List<String> inputUDIsMOPart = Arrays.asList("MOPrt_UDI_EditBox","MOPrt_UDI_ListBox","MOPrt_UDI_ComboBox","MOPrt_UDI_RadioGrp","MOPrt_UDI_ItemLookup");
		List<String> inputUDIsMOPrtValues = Arrays.asList("Edit Text","MOPrtList","ComboText","MOPrtRadio","MOPrtLookup");
		List<String> outputUDIsMOPart = Arrays.asList("MOPrt_UDI_Output_EditBox","MOPrt_UDI_Output_ListBox","MOPrt_UDI_Output_ComboBox","MOPrt_UDI_Output_RadioGrp","MOPrt_UDI_Output_ItemLook");
		String specificMOPartCatgInputUDI = "MOPrt_Catg_EditBox";
		String specificMOPartCatgInputUDIValue = "MOCatgEditBox";
		String specificMOPartCatgOutputUDI = "MOPrt_Catg_Output_EditBox";
		int num = 2;
		int i = 0;

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testMaintenanceObjectUDIFields");

		expandAdministration();
		waitForExtJSAjaxComplete(5);

		click(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();
		waitForExtJSAjaxComplete(15);
		
		filterGridWithoutUsingMcsElementModifiedForChrome("@realid", "mogrid", testWorkOrder,"Reference");
		
		waitForExtJSAjaxComplete(15);
		
		Grid.checkRowInGriByTextValueAndClick(driver, testWorkOrder);
		waitForExtJSAjaxComplete(25);
		
		clickMaintenanceObjectTab();
		waitForExtJSAjaxComplete(15);

		//C114299
		clickAddMaintanceObject();
		waitForExtJSAjaxComplete(25);

		setMaintenanceObject(refMO);
		waitForExtJSAjaxComplete(25);

		selectMObjectOrMOPart(refMO,"Maintenance Object");
		waitForExtJSAjaxComplete(5);

		clickDetailsMaintanceObject();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		CallDetailTabsPageObject callPgObj = new CallDetailTabsPageObject();

		for(String inputUDI : inputUDIsMO)
		{
			softAssert.assertTrue(isRelatedDataPresentInMaintenanceObjectDetailsWindow("Details",inputUDI),"General UDI fields for all MOs "+inputUDI+" is present in Details section");
			softAssert.assertEquals(callPgObj.getMaintenanceObjFieldValue("gp-groupOrd-3",inputUDI,num),inputUDIsMOValues.get(i),"UDI field dafault value is displayed on checking or unchecking 'mandatory' field check box");
			i++;
		}

		for(String outputUDI : outputUDIsMO)
			softAssert.assertTrue(isRelatedDataPresentInMaintenanceObjectDetailsWindow("Details",outputUDI),"General UDI fields for all MOs "+outputUDI+" is present in Details section");

		softAssert.assertTrue(isRelatedDataPresentInMaintenanceObjectDetailsWindow("1preMnObCtRef",specificMOCatgInputUDI),"UDI "+specificMOCatgInputUDI+" fields for the specific MO Category 1preMnObCtRef is present");
		softAssert.assertEquals(callPgObj.getMaintenanceObjFieldValue("gp-groupOrd-4",specificMOCatgInputUDI,num),specificMOCatgInputUDIValue,"UDI field dafault value is displayed on checking or unchecking 'mandatory' field check box");

		softAssert.assertTrue(isRelatedDataPresentInMaintenanceObjectDetailsWindow("1preMnObCtRef",specificMOCatgOutputUDI),"UDI "+specificMOCatgOutputUDI+" fields for the specific MO Category 1preMnObCtRef is present");
		waitForExtJSAjaxComplete(5);

		closeUsingToolBarJS("Maintenance Object Details");
		waitForExtJSAjaxComplete(5);

		//C114302
		clickAddMaintanceObjectPart();
		waitForExtJSAjaxComplete(15);

		setMaintenanceObjectPart(refMOPart);

		selectMObjectOrMOPart(refMOPart,"Maintenance Object Part");
		waitForExtJSAjaxComplete(5);

		clickDetailsMaintanceObject();
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		i=0;
		for(String inputUDI : inputUDIsMOPart)
		{
			softAssert.assertTrue(isRelatedDataPresentInMaintenanceObjectDetailsWindow("Details",inputUDI),"General UDI fields for all MOParts "+inputUDI+" is present in Details section");
			softAssert.assertEquals(callPgObj.getMaintenanceObjFieldValue("gp-groupOrd-2",inputUDI,num),inputUDIsMOPrtValues.get(i),"UDI field dafault value is displayed on checking or unchecking 'mandatory' field check box");
			i++;
		}

		for(String outputUDI : outputUDIsMOPart)
			softAssert.assertTrue(isRelatedDataPresentInMaintenanceObjectDetailsWindow("Details",outputUDI),"General UDI fields for all MOsParts "+outputUDI+" is present in Details section");

		softAssert.assertTrue(isRelatedDataPresentInMaintenanceObjectDetailsWindow("1preObPtTpRef",specificMOPartCatgInputUDI),"UDI "+specificMOPartCatgInputUDI+" field for the specific MOPart Category 1preObPtTpRef is present");
		softAssert.assertEquals(callPgObj.getMaintenanceObjFieldValue("gp-groupOrd-3",specificMOPartCatgInputUDI,num),specificMOPartCatgInputUDIValue,"UDI field dafault value is displayed on checking or unchecking 'mandatory' field check box");

		softAssert.assertTrue(isRelatedDataPresentInMaintenanceObjectDetailsWindow("1preObPtTpRef",specificMOPartCatgOutputUDI),"UDI "+specificMOPartCatgOutputUDI+" field for the specific MOPart Category 1preObPtTpRef is present");
		softAssert.assertAll();
		Reporter.log("'Maintenance objects' and 'Maintenance Object Parts' UDI's are successfully verified", true);

	}
	/**
	 * Test Case ID: C114758
	 * Author :SRD
	 */
	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testEditingMultipleWORights () throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Editing Multiple WO Rights C114758" + " </span><br>",
				true);

		String woRef1  = "C75219_Inpreparation1";
		String woRef2  = "C75219_Inpreparation2";
		String errMsg  = "You do not have the right to modify Work Orders. Please contact your Administrator.";

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();

		expandAdministration();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		clickXPath(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();

		waitForExtJSAjaxComplete(25);

		filterGridForWO("@realid","mogrid","C75219_Inpreparation","Reference");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		Grid.checkRowInGriByTextValue(driver, woRef1);

		waitForExtJSAjaxComplete(10);

		closeXWindow("@class","x-resizable-pinned");

		waitForExtJSAjaxComplete(10);

		Grid.checkRowInGriByTextValue(driver, woRef2);

		waitForExtJSAjaxComplete(10);

		clickEditButton();

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(isWOMultiEditWindowOpen(),"The WO's are opened in multi-edit mode");

		waitForExtJSAjaxComplete(25);

		clickCustomCheckBox("Due Date and Time:");

		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isDueDateEnabled(),"Due Date field is enabled and editable");

		softAssert.assertTrue(isDueTimeEnabled(),"Due Time field is enabled and editable");

		closeXWindow();

		clickOnDialogButton("Yes");

		logout();

		waitForExtJSAjaxComplete(10);

		login("NoWOMultipleEditRights","qwerty");

		expandAdministration();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		clickXPath(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		filterGridForWO("@realid","mogrid","C75219_Inpreparation","Reference");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		Grid.checkRowInGriByTextValue(driver, woRef1);

		waitForExtJSAjaxComplete(10);

		closeXWindow("@class","x-resizable-pinned");

		waitForExtJSAjaxComplete(10);

		Grid.checkRowInGriByTextValue(driver, woRef2);

		waitForExtJSAjaxComplete(10);

		clickEditButton();

		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(getWarningDialogTextMsg().contains(errMsg),errMsg);

		waitForExtJSAjaxComplete(10);

		clickOnDialogButton("OK");

		waitForExtJSAjaxComplete(10);

		softAssert.assertAll();

		Reporter.log("Editing Multiple WO Rights is  successfully verified", true);

	}
	
	/**
	 * Test Case ID: C113934
	 * Author :SRD
	 */
	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testDeletedEmployeeCannotbeMemberOfTaskforce() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Deleted employee cannot be the member of Taskforce C113934" + " </span><br>",
				true);
        
		String woRef     = "C75133";
		String planner1  = "SELENIUM";
		String planner2  = "NotEnabled";
		String taskForce ="1preTskFrcName";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testDeletedEmployeeCannotbeMemberOfTaskforce");
		
        expandAdministration();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		clickXPath(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setDetailsTabCollapsedMode();
		
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);
		
        waitForExtJSAjaxComplete(25);
		
		checkRowInGriByTextValueAndClick(woRef);
		
		waitForExtJSAjaxComplete(25);
		
	    clickAddActionButtonInWindow();
	    
	    waitForExtJSAjaxComplete(10);
	    
	    clickFirstActionInPossibleActions();
	    
	    waitForExtJSAjaxComplete(10);
	    
	    setTaskforce("actions-dialog",taskForce);
	    
	    waitForExtJSAjaxComplete(10);
	    
	    setPlanner("actions-dialog",planner1);
	    
	    waitForExtJSAjaxComplete(10);
	   
	    softAssert.assertTrue(getPlanner("actions-dialog").contains(planner1),planner1+" Employee is visible in the list as planner.");
	    
	    waitForExtJSAjaxComplete(10);
	    
	    clickButtonInActionWindow("Add Action and Close");
	    
	    waitForExtJSAjaxComplete(10);
	    
	    clickTrackingTab("window");
	    
	    waitForExtJSAjaxComplete(10);
	    
	    clickTrackingHistoryTab();
	    
	    waitForExtJSAjaxComplete(10);
	    
	    softAssert.assertTrue(isPlannerAddedToAction(planner1,"1preTskFrcName")," Action is performed successfully. The Employee is set as Planner");
	    
	    waitForExtJSAjaxComplete(10);
	    
	    clickEditLastAction();
	    
	    waitForExtJSAjaxComplete(10);
	    
	    clickLookup("actions-dialog","planner");
	    
	    waitForExtJSAjaxComplete(10);
	    
	    softAssert.assertFalse(setValueGridLookupWithFiltersWithValidation("@class", "x-window-noborder x-resizable-pinned", planner2,"Last Name"), planner2+"Employee is not in the list.");   
		
		softAssert.assertAll();

		Reporter.log("Deleted Employee Cannot be The Member Of Taskforces is  successfully verified", true);
		
	}
	
	/**
	 * Test Case ID: C110852,C115240,C119642
	 * Author :SRD
	 */
	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testAddEditActivities() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Add Edit Activities. C110852" + " </span><br>"
				+ "Test: Financial Keys are displayed on a Web WO if these fields are enabled for Quick Entry in WIN Module: C115240" + " </span><br>"
				+ "Test: The employee can be assigned to WO if he is or isn't excluded from standard search list: C119642" + " </span><br>",
				true);

		String wo1Ref = "C75078";
		String wo2Ref = "C75133";
		String assigned = "SELENIUM";
		String unassignableUser = "NotEnabled";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testAddEditActivities");

		expandAdministration();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		clickXPath(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		//C75078

		checkRowInGriByTextValueAndClick(wo1Ref);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(15);

		String woID = getWOIDFromDetailsWindow();

		waitForExtJSAjaxComplete(15);

		clickTimeMaterialTab();

		waitForExtJSAjaxComplete(25);

		clickTimeMaterialLaborTab();

		waitForExtJSAjaxComplete(10);

		clickAddLabor();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isFieldDisabledInActivity("Work Order:","div"), "Add activity window is displayed. Workorder field is not editable");

		waitForExtJSAjaxComplete(10);

		softAssert.assertEquals(woID+": "+wo1Ref,getFieldValueBasedOnLabelInActivity("Work Order:"),"Workorder field is automatically filled in with the Workorder 1");

		waitForExtJSAjaxComplete(10);

		clickSaveActivity();

		waitForExtJSAjaxComplete(10);

		String activityID=getActivityID();

		waitForExtJSAjaxComplete(10);

		cancelActivity(EDIT_ACTIVITY_WINDOW_HEADER);

		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver,activityID,"@realid","WoLaborGrid"),"Activity is added to the Workorder 1");

		waitForExtJSAjaxComplete(5);

		checkRowInLaborTabGridForActivity(activityID);

		waitForExtJSAjaxComplete(10);

		clickEditLabor();

		waitForExtJSAjaxComplete(5);

		setLaborActivityWorkorder(wo2Ref);

		waitForExtJSAjaxComplete(10);

		saveAndCloseActivity();

		waitForExtJSAjaxComplete(10);

		softAssert.assertFalse(Grid.isRowInGridPresent(driver,activityID,"@realid","WoLaborGrid"),"Activity is not visible in Workorder 1.");

		waitForExtJSAjaxComplete(10);

		driver.navigate().refresh();

		waitForExtJSAjaxComplete(10);

		waitForExtJSAjaxComplete(10);

		expandAdministration();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		clickXPath(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		checkRowInGriByTextValueAndClick(wo2Ref);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(15);

		clickTimeMaterialTab();

		waitForExtJSAjaxComplete(25);

		clickTimeMaterialLaborTab();

		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver,activityID,"@realid","WoLaborGrid"),"Activity added in Wo1 is visible");

		waitForExtJSAjaxComplete(5);

		checkRowInLaborTabGridForActivity(activityID);

		clickEditLabor();

		waitForExtJSAjaxComplete(5);

		softAssert.assertFalse(isFieldDisabledInActivity("Work Order:","input"), "Edit activity window is displayed. Workorder field is Editable");

		waitForExtJSAjaxComplete(10);

		cancelActivity(EDIT_ACTIVITY_WINDOW_HEADER);

		checkRowInLaborTabGridForActivity(activityID);

		clickDeleteLabor("Yes");

		waitForExtJSAjaxComplete(15);

		driver.navigate().refresh();

		waitForExtJSAjaxComplete(10);

		waitForExtJSAjaxComplete(10);

		expandAdministration();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		clickXPath(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		checkRowInGriByTextValueAndClick(wo2Ref);

		waitForExtJSAjaxComplete(25);

		//C75133

		clickBillingTab("window");

		waitForExtJSAjaxComplete(15);
		
		softAssert.assertTrue(isFinkeyDisplayed("Fiscal Entity:"),"Fiscal Entity is Displayed");
		
		softAssert.assertTrue(isFinkeyDisplayed("Financial Status:"),"Financial Status is Displayed");

		softAssert.assertTrue(isFinkeyDisplayed("Cost Center:"),"Cost Center is Displayed");

		softAssert.assertTrue(isFinkeyDisplayed("GL Account:"),"GL Accountr is Displayed");

		softAssert.assertTrue(isFinkeyDisplayed("FIN_KEY_3:"),"FIN_KEY_3 is Displayed");

		softAssert.assertTrue(isFinkeyDisplayed("FIN_KEY_4:"),"FIN_KEY_4 is Displayed");

		//C75031

		driver.navigate().refresh();

		waitForExtJSAjaxComplete(10);

		waitForExtJSAjaxComplete(10);

		expandAdministration();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		clickXPath(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		checkRowInGriByTextValueAndClick(wo2Ref);

		waitForExtJSAjaxComplete(25);

		clickGeneralTab("window");

		waitForExtJSAjaxComplete(10);

		setAssigned(WORKORDER_WINDOW_DETAIL, assigned);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(getAssigned("window").contains(assigned),assigned+"User is Assigned to WO");
		
		waitForExtJSAjaxComplete(10);
		
		clickLookup(WORKORDER_WINDOW_DETAIL, "assignedTo");

		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(setValueGridLookupWithFiltersWithValidation("@class", "x-window-noborder x-resizable-pinned", unassignableUser,"Last Name"),unassignableUser+"User is absent in the list");
		
		softAssert.assertAll();

		Reporter.log("Add Edit Activities and FinKeys are  successfully verified", true);

	}
	
	/**
	 * Test Case ID: C116439
	 * Author :ssa
	 */

	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testWOhierarchycontainsProjectsAndProjectParts() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:WO hierarchy contains Projects&Project Parts C116439</span><br>", true);

		
		String testWorkOrder = "MultipleWOLinking";
		String testWorkOrder1 = "MultipleWOLinking1";
		String projectPart="1prePrPartRef";
		String project="1preProjectRef";
	
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testWOhierarchycontainsProjectsAndProjectParts");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(25);
		
		clickXPath(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(25);
		
		setDetailsTabCollapsedMode();
		
		Thread.sleep(1500);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElementModifiedForChrome("@realid", "mogrid", testWorkOrder,"Reference");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, testWorkOrder);
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		//hierarchy is displayed
		softAssert.assertTrue(isHierarchySectionDisplayedInHierarachyPanel(),"WO hierarchy is displayed");
		
		//Tree is expanded
		List<String> values=Arrays.asList(project,projectPart,testWorkOrder);
		
		for(String value:values)
		{
		softAssert.assertTrue(isTreeExpandedInHierarachyPanel(value),"WO hierarchy is in expanded mode and Workorders are linked to the project");
		}
		
		//Project is displayed on the Top of the Tree
		softAssert.assertTrue(isProjectDisplayedOnTopOfTreeInHierarachyPanel(project),"Project is displayed on the Top of the Tree");
		
		//Current WO is highlighted in Red
		softAssert.assertTrue(isCurrentWOActiveInHierarachyPanel(testWorkOrder),"Current WO is highlighted in active mode");
		
		clickOnProjectInHierarachyPanel(project);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isWorkOrderDisplayedInHierarachyPanel(testWorkOrder),"WO remain displayed");
		softAssert.assertTrue(isWorkOrderDisplayedInHierarachyPanel(testWorkOrder1),"WO remain displayed");
		
		clickOnWorkOrderInHierarachyPanel(testWorkOrder1);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getReference(),testWorkOrder1,"User is redirected to the WO");
		
		softAssert.assertAll();
		
		Reporter.log("Verified WO hierarchy contains Projects&Project Parts",  true);
	}	
	
	/**
	 * Test Case ID: C116979,C114777
	 * Author :ssa
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testUnStaffedWarehouseForMultBOMLines() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C116979: Multiple BOM Lines of same product"
				+"C114777: Executing a Quick Goods Return in an unstaffed Warehouse </span><br>", true);
		
		String testWorkOrder = "WOForStockInfo";
		String user=this.getUserLastNameOfLoggedInUser();
		String quantity="10";
		int  quantityQGR= Integer.parseInt(quantity);
		String warehouseRef = "UnstaffedGoodsReturn";
		String availableStockColName = "Available Stock";
		String reservedStockColName = "Reserved Stock";
		String blockedStockColName = "Blocked Stock";
		String totalStockColName = "Total Stock";
		String productReference = "2preConsRef";
		String goodsReturnClass="Goods Return";
		String blockClass="Block";
		String unblockClass="Unblock";
		String remark = "C91904"+getCurrentDate().substring(getCurrentDate().length()-5);
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testUnStaffedWarehouseForMultBOMLines");
		
		TransactionsPageObject transPageObject=new TransactionsPageObject();

		expandAdministration();
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElementModifiedForChrome("@realid", "mogrid", warehouseRef,"Reference");
		
		waitForExtJSAjaxComplete(20);
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseRef);

		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
				
		wareHousePageObject.clickEditButton(); 
				
		waitForExtJSAjaxComplete(10);
				
		//Selecting Stock items Tab
		wareHousePageObject.clickStockItemsTab();
				
		waitForExtJSAjaxComplete(10);
		
		String preProdRowNO=wareHousePageObject.getProductRowNumberInWareHouse(productReference);
				
		int preAvailableStockValue = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(preProdRowNO,availableStockColName ));
		int preReservedStockValue = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(preProdRowNO,reservedStockColName));
		int preTotalStockValue=Integer.parseInt(wareHousePageObject.getStockItemsCellValue(preProdRowNO,totalStockColName));
		int preBlockedStockValue=Integer.parseInt(wareHousePageObject.getStockItemsCellValue(preProdRowNO,blockedStockColName));
		
		Reporter.log(preAvailableStockValue+"is available stock quantity", true);
		
		Reporter.log(preTotalStockValue+"is total stock quantity", true);
		
		Reporter.log(preBlockedStockValue+"is blocked stock quantity", true);
		
		Reporter.log(preReservedStockValue+"is reserved stock quantity", true);
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		driver.navigate().refresh();
		
		waitForExtJSAjaxComplete(25);
		
		click(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		setDetailsTabCollapsedMode();
		
		Thread.sleep(1500);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElementModifiedForChrome("@realid", "mogrid", testWorkOrder,"Reference");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, testWorkOrder);
		
		waitForExtJSAjaxComplete(20);
		
		clickTimeMaterialTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickTimeMaterialBillMaterialTab();
		
		waitForExtJSAjaxComplete(20);
		
		selectMutipleBOMLines("Consumed",productReference);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		List<Integer> preAvailableConsumedQty=getBOMLineQuantities("Consumed", "Consumed Qty", productReference);
		
		List<Integer> preAvailableReturnedQty=getBOMLineQuantities("Consumed", "Returned Qty", productReference);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnQuickGoodsReturnButtonInBOM();
		
		waitForExtJSAjaxComplete(20);
		
		selectReturnByInQGR(user);
		
		waitForExtJSAjaxComplete(20);
		
		setMultiLineQuantityInQGR(productReference,quantity);
		
		waitForExtJSAjaxComplete(20);
		
		setMultiLineRemarksInQGR(productReference,remark);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnSaveInQGR();
		
		waitForExtJSAjaxComplete(20);
		
		List<Integer> postAvailableConsumedQty=getBOMLineQuantities("Consumed", "Consumed Qty", productReference);
		
		int availableProdSize=postAvailableConsumedQty.size();
		
		softAssert.assertTrue((postAvailableConsumedQty.get(0)==(-(quantityQGR-preAvailableConsumedQty.get(0)))),"Consumed Qty value is increase by x units");
		
		softAssert.assertTrue((postAvailableConsumedQty.get(1)==(-(quantityQGR-preAvailableConsumedQty.get(1)))),"Consumed Qty value is increase by x units");
		
		softAssert.assertTrue((postAvailableConsumedQty.get(2)==(-(quantityQGR-preAvailableConsumedQty.get(1)))),"Consumed Qty value is increase by x units");
		
		List<Integer> postAvailableReturnedQty=this.getBOMLineQuantities("Consumed", "Returned Qty",  productReference);
		
		softAssert.assertTrue((postAvailableReturnedQty.get(0)==(quantityQGR+preAvailableReturnedQty.get(0))),"Returned Qty value is increase by x units");
		
		softAssert.assertTrue((postAvailableReturnedQty.get(1)==(quantityQGR+preAvailableReturnedQty.get(1))),"Returned Qty value is increase by x units");
		
		softAssert.assertTrue((postAvailableReturnedQty.get(2)==(quantityQGR+preAvailableReturnedQty.get(1))),"Returned Qty value is increase by x units");
		
		
		driver.navigate().refresh();
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		transPageObject.filterGrid("@id", transPageObject.getFilterGridID(),remark, "Remark");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(transPageObject.getSizeofTxnLinesBasedOnClass(unblockClass),availableProdSize,"unblock transaction three records are displayed");
		
		waitForExtJSAjaxComplete(10);
		
		List<String> classes=Arrays.asList(goodsReturnClass);
		
		for(String transaction:classes)
		{
		
		List<String> status=transPageObject.getStatusOfAllTxnLines(transaction);
				
		softAssert.assertTrue(status.contains("Executed"), "Executed Status is Displayed in "+transaction+" Transaction record");
		}
	
		driver.navigate().refresh();
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
				
		filterGridWithoutUsingMcsElementModifiedForChrome("@realid", "mogrid", warehouseRef,"Reference");
		
		waitForExtJSAjaxComplete(20);
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseRef);
		
		waitForExtJSAjaxComplete(20);

		wareHousePageObject.clickEditButton(); 
				
		waitForExtJSAjaxComplete(10);
				
		//Selecting Stock items Tab
		wareHousePageObject.clickStockItemsTab();
				
		String postProdRowNO=wareHousePageObject.getProductRowNumberInWareHouse(productReference);
		
		int postAvailableStockValue = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(postProdRowNO,availableStockColName ));
		int postReservedStockValue = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(postProdRowNO,reservedStockColName));
		int postTotalStockValue=Integer.parseInt(wareHousePageObject.getStockItemsCellValue(postProdRowNO,totalStockColName));
		int postBlockedStockValue=Integer.parseInt(wareHousePageObject.getStockItemsCellValue(postProdRowNO,blockedStockColName));
		
		Reporter.log(postAvailableStockValue+"is available stock quantity",true);
		Reporter.log(postTotalStockValue+"is total stock quantity", true);
		Reporter.log(postBlockedStockValue+"is blocked stock quantity", true);
		Reporter.log(postReservedStockValue+"is reserved stock quantity", true);
		
		softAssert.assertTrue((postBlockedStockValue==preBlockedStockValue), productReference+" blocked stock quantity is unchanged");
		softAssert.assertTrue((postReservedStockValue==preReservedStockValue), productReference+ " Reserved stock quantity is unchanged");
		softAssert.assertTrue((postAvailableStockValue==(preAvailableStockValue+(quantityQGR*3))), productReference+ " Available stock quantity is increased by "+quantityQGR+"units");
		softAssert.assertTrue((postTotalStockValue==(preTotalStockValue+(quantityQGR*3))), productReference+" Total stock quantity is increased by "+quantityQGR+"units");
		
		softAssert.assertAll();
		
		Reporter.log("Verified Quick Goods Return in an Unstaffed Warehouse",  true);
		
	}	

	/**
	 * Test Case ID: C114778
	 * Author :ssa
	 */
	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testStaffedWarehouse() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C114778: Executing a Quick Goods Return in an staffed Warehouse </span><br>", true);
		
		String testWorkOrder = "WOForStockInfo";
		String user=this.getUserLastNameOfLoggedInUser();
		String quantity="10";
		int  quantityQGR= Integer.parseInt(quantity);
		String warehouseRef = "StaffedGoodsReturnWH";
		String availableStockColName = "Available Stock";
		String reservedStockColName = "Reserved Stock";
		String blockedStockColName = "Blocked Stock";
		String totalStockColName = "Total Stock";
		String productReference = "4preConsRef";
		String goodsReturnClass="Goods Return";
		String blockClass="Block";
		String unblockClass="Unblock";
		String remark = "C91902"+getCurrentDate().substring(getCurrentDate().length()-5);
		String EDIT_WAREHOUSE_WINDOW_HEADER = "Warehouse [";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testStaffedWarehouse");
		
		TransactionsPageObject transPageObject=new TransactionsPageObject();

		expandAdministration();
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElementModifiedForChrome("@realid", "mogrid", warehouseRef,"Reference");

		waitForExtJSAjaxComplete(20);

		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseRef);

		AddWarehousePageObject wareHousePageObject = new AddWarehousePageObject();
				
		wareHousePageObject.clickEditButton(); 
				
		waitForExtJSAjaxComplete(10);
				
		//Selecting Stock items Tab
		wareHousePageObject.clickStockItemsTab();
				
		waitForExtJSAjaxComplete(10);
		
		String preProdRowNO=wareHousePageObject.getProductRowNumberInWareHouse(productReference);
				
		int preAvailableStockValue = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(preProdRowNO,availableStockColName ));
		int preReservedStockValue = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(preProdRowNO,reservedStockColName));
		int preTotalStockValue=Integer.parseInt(wareHousePageObject.getStockItemsCellValue(preProdRowNO,totalStockColName));
		int preBlockedStockValue=Integer.parseInt(wareHousePageObject.getStockItemsCellValue(preProdRowNO,blockedStockColName));
		
		Reporter.log(preAvailableStockValue+"is available stock quantity", true);
		
		Reporter.log(preTotalStockValue+"is total stock quantity", true);
		
		Reporter.log(preBlockedStockValue+"is blocked stock quantity", true);
		
		Reporter.log(preReservedStockValue+"is reserved stock quantity", true);
		
		wareHousePageObject.close(EDIT_WAREHOUSE_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		driver.navigate().refresh();
		
		waitForExtJSAjaxComplete(25);
		
		click(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		setDetailsTabCollapsedMode();
		
		Thread.sleep(1500);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElementModifiedForChrome("@realid", "mogrid", testWorkOrder,"Reference");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, testWorkOrder);
		
		waitForExtJSAjaxComplete(20);
		
		clickTimeMaterialTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickTimeMaterialBillMaterialTab();
		
		waitForExtJSAjaxComplete(20);
		
		selectMutipleBOMLines("Consumed",productReference);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		List<Integer> preAvailableConsumedQty=getBOMLineQuantities("Consumed", "Consumed Qty",  productReference);
		
		List<Integer> preAvailableReturnedQty=getBOMLineQuantities("Consumed", "Returned Qty",  productReference);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnQuickGoodsReturnButtonInBOM();
		
		waitForExtJSAjaxComplete(20);
		
		selectReturnByInQGR(user);
		
		waitForExtJSAjaxComplete(20);
		
		setMultiLineQuantityInQGR(productReference,quantity);
		
		waitForExtJSAjaxComplete(20);
		
		setMultiLineRemarksInQGR(productReference,remark);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnSaveInQGR();
		
		waitForExtJSAjaxComplete(20);
		
		driver.navigate().refresh();
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_TRANSACTIONS);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		openTransactionLine("@realid", "mogrid", remark, "Remark");
				
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		transPageObject.setStatus("Executed", goodsReturnClass);
		
		waitForExtJSAjaxComplete(20);
		
		transPageObject.save();
		
		waitForExtJSAjaxComplete(20);
		
		transPageObject.close(goodsReturnClass);
		
		waitForExtJSAjaxComplete(20);
	
		driver.navigate().refresh();
		
		waitForExtJSAjaxComplete(20);
		
		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_WAREHOUSES);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElementModifiedForChrome("@realid", "mogrid", warehouseRef,"Reference");
		
		waitForExtJSAjaxComplete(20);
		
		//Navigate to Warehouse and Select the edit warehouse
		Grid.checkRowInGriByTextValue(driver, warehouseRef);
		
		waitForExtJSAjaxComplete(20);

		wareHousePageObject.clickEditButton(); 
				
		waitForExtJSAjaxComplete(10);
				
		//Selecting Stock items Tab
		wareHousePageObject.clickStockItemsTab();
				
		String postProdRowNO=wareHousePageObject.getProductRowNumberInWareHouse(productReference);
		
		int postAvailableStockValue = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(postProdRowNO,availableStockColName ));
		int postReservedStockValue = Integer.parseInt(wareHousePageObject.getStockItemsCellValue(postProdRowNO,reservedStockColName));
		int postTotalStockValue=Integer.parseInt(wareHousePageObject.getStockItemsCellValue(postProdRowNO,totalStockColName));
		int postBlockedStockValue=Integer.parseInt(wareHousePageObject.getStockItemsCellValue(postProdRowNO,blockedStockColName));
		
		Reporter.log(postAvailableStockValue+"is available stock quantity",true);
		Reporter.log(postTotalStockValue+"is total stock quantity", true);
		Reporter.log(postBlockedStockValue+"is blocked stock quantity", true);
		Reporter.log(postReservedStockValue+"is reserved stock quantity", true);
		
		softAssert.assertTrue((postBlockedStockValue==(preBlockedStockValue+quantityQGR)), productReference+" blocked stock quantity is increased by "+quantityQGR+"units");
		softAssert.assertTrue((postReservedStockValue==preReservedStockValue), productReference+ " Reserved stock quantity is unchanged");
		softAssert.assertTrue((postAvailableStockValue==(preAvailableStockValue)), productReference+ " Available stock quantity is unchanged");
		softAssert.assertTrue((postTotalStockValue==(preTotalStockValue+quantityQGR)), productReference+" Total stock quantity is increased by "+quantityQGR+"units");
		
		driver.navigate().refresh();
		
		waitForExtJSAjaxComplete(25);
		
		click(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		setDetailsTabCollapsedMode();
		
		Thread.sleep(1500);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElementModifiedForChrome("@realid", "mogrid", testWorkOrder,"Reference");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, testWorkOrder);
		
		waitForExtJSAjaxComplete(20);
		
		clickTimeMaterialTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickTimeMaterialBillMaterialTab();
		
		waitForExtJSAjaxComplete(20);
		
		selectMutipleBOMLines("Consumed",productReference);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		List<Integer> postAvailableConsumedQty=this.getBOMLineQuantities("Consumed", "Consumed Qty",  productReference);
		
		softAssert.assertTrue((postAvailableConsumedQty.get(0)==(-(quantityQGR-preAvailableConsumedQty.get(0)))),"Consumed Qty value is increase by x units");
				
		List<Integer> postAvailableReturnedQty=this.getBOMLineQuantities("Consumed", "Returned Qty",  productReference);
		
		softAssert.assertTrue((postAvailableReturnedQty.get(0)==(quantityQGR+preAvailableReturnedQty.get(0))),"Returned Qty value is increase by x units");
		
		softAssert.assertAll();
		
		Reporter.log("Verified Quick Goods Return in an staffed Warehouse",  true);
		
	}	
	
	/**
	 * Test Case ID: C124372
	 * Author :SRD
	 */
	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testWOMultiEditFieldsAvailabilityDependingOnTheSelectedWO() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: WO Multi Edit Fields Availability Depending On TheS elected WO C124372" + " </span><br>",
				true);

		String woRef1 				   = "1preWrkRef";
		String woRef2                  = "AqaNegativeConsumptionWO";
		String woRef3                  = "C75078";
		
		String wo1InPreparationStatus  = "C75219_Inpreparation1";
		
		String wo1InProgressStatus     = "C75219_Inprogress1";
	
		String warMsg				   = "Editing of Workorders in different Status Classes is not allowed.";
		String wo1ArchivedStatus       = "C75219_Archived1";
		String wo2ArchivedStatus       = "C75219_Archived2";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testAddEditActivities");
		
		expandAdministration();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		clickXPath(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();

		waitForExtJSAjaxComplete(25);

		Grid.checkRowInGriByTextValue(driver, woRef1);

		waitForExtJSAjaxComplete(10);
		
		closeXWindow("@class","x-resizable-pinned");
		
		waitForExtJSAjaxComplete(10);

		Grid.checkRowInGriByTextValue(driver, woRef2);

		waitForExtJSAjaxComplete(25);

		clickEditButton();

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(isWOMultiEditWindowOpen(),"The WO's are opened in multi-edit mode");

		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		clickCustomCheckBox("Cost Center:");

		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isFieldEnabledInWOMultiWindow("Cost Center:","div"),"Cost Center is Enabled");
		
		clickCustomCheckBox("GL Account:");
		
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isFieldEnabledInWOMultiWindow("GL Account:","div"),"GL Account: is Enabled");
		
		clickCustomCheckBox("FIN_KEY_3:");
		
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isFieldEnabledInWOMultiWindow("FIN_KEY_3:","div"),"FIN_KEY_3: is Enabled");
		
		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Planned Start Date:"),"Customcheck box is Disabled for Planned Start Date");

		softAssert.assertFalse(isPlannedStartDateEnabled(),"Planned Start Date Field is read-only");

		softAssert.assertFalse(isPlannedStartTimeEnabled(),"Planned Start Time Field is read-only");
		
		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Planned End Date:"),"Customcheck box is Disabled for Planned End Date");

		softAssert.assertFalse(isPlannedEndDateEnabled(),"Planned End Date Field is read-only");

		softAssert.assertFalse(isPlannedEndTimeEnabled(),"Planned End Time Field is read-only");
		
		closeXWindow();

		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(25);
		
		clickSelectAllcheckButtonInOverview();
		
		clickSelectAllcheckButtonInOverview();
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, woRef1);

		waitForExtJSAjaxComplete(10);
		
		closeXWindow("@class","x-resizable-pinned");
		
		waitForExtJSAjaxComplete(10);

		Grid.checkRowInGriByTextValue(driver, woRef3);

		waitForExtJSAjaxComplete(25);

		clickEditButton();

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(isWOMultiEditWindowOpen(),"The WO's are opened in multi-edit mode");

		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Cost Center:"),"Customcheck box is Disabled for Cost Center");

		softAssert.assertFalse(isFieldEnabledInWOMultiWindow("Cost Center:","div"),"Cost Center is read-only");
		
		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("GL Account:"),"Customcheck box is Disabled for GL Account:");

		softAssert.assertFalse(isFieldEnabledInWOMultiWindow("GL Account:","div"),"GL Account: is read-only");
		
		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("FIN_KEY_3:"),"Customcheck box is Disabled for FIN_KEY_3");

		softAssert.assertFalse(isFieldEnabledInWOMultiWindow("FIN_KEY_3:","div"),"FIN_KEY_3: is read-only");
		
		driver.navigate().refresh();

		waitForExtJSAjaxComplete(10);

		waitForExtJSAjaxComplete(10);

		expandAdministration();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		clickXPath(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();

		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(10); 
		
		filterGridForWO("@realid","mogrid","C75219","Reference");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		//verification of Archive Status
		
		Grid.checkRowInGriByTextValue(driver, wo1ArchivedStatus);

		waitForExtJSAjaxComplete(10);

		closeXWindow("@class","x-resizable-pinned");

		waitForExtJSAjaxComplete(10);

		Grid.checkRowInGriByTextValue(driver, wo2ArchivedStatus);

		waitForExtJSAjaxComplete(25);

		clickEditButton();

		softAssert.assertTrue(isWOMultiEditWindowOpen(),"The WO's are opened in multi-edit mode");

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);
		
		softAssert.assertFalse(isFieldEnabledInWOMultiWindow("Fiscal Entity:","input"),"Fiscal Entity is read-only field ");
		
		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Cost Center:"),"Customcheck box is Disabled for Cost Center");

		softAssert.assertFalse(isFieldEnabledInWOMultiWindow("Cost Center:","div"),"Cost Center is read-only");
		
		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("GL Account:"),"Customcheck box is Disabled for GL Account:");

		softAssert.assertFalse(isFieldEnabledInWOMultiWindow("GL Account:","div"),"GL Account: is read-only");
		
		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("FIN_KEY_3:"),"Customcheck box is Disabled for FIN_KEY_3");

		softAssert.assertFalse(isFieldEnabledInWOMultiWindow("FIN_KEY_3:","div"),"FIN_KEY_3: is read-only");
		
		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Priority:"),"Customcheck box is Disabled for Cost Center");
	
		softAssert.assertFalse(isFieldEnabledInWOMultiWindow("Priority:","div"),"Priority is read-only");
		
		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Severity:"),"Customcheck box is Disabled for Cost Center");

		softAssert.assertFalse(isFieldEnabledInWOMultiWindow("Severity:","div"),"Severity is read-only");
		
		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Nature:"),"Customcheck box is Disabled for Cost Center");

		softAssert.assertFalse(isFieldEnabledInWOMultiWindow("Nature:","div"),"Nature is read-only");
		
		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Location:"),"Customcheck box is Disabled for Cost Center");

		softAssert.assertFalse(isFieldEnabledInWOMultiWindow("Location:","div"),"Location is read-only");
		
		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Due Date and Time:"),"Customcheck box is Disabled for Due Date and Time");
		
		softAssert.assertFalse(isDueDateEnabled(),"Due Date field is not editable");

		softAssert.assertFalse(isDueTimeEnabled(),"Due Time field is not editable");
		
		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Task Force:"),"Customcheck box is Disabled for Task Force");
		
		softAssert.assertFalse(isFieldEnabledInWOMultiWindow("Task Force:","div"),"Task Force is not editable");
		
		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Assigned To:"),"Customcheck box is Disabled for Assigned To");
		
		softAssert.assertFalse(isFieldEnabledInWOMultiWindow("Assigned To:","div"),"Assigned To is not editable");
		
		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Planned Start"),"Customcheck box is Disabled for Planned Start date");
		
		softAssert.assertFalse(isPlannedStartDateEnabled(),"Planned Start Date Field is not editable");

		softAssert.assertFalse(isPlannedStartTimeEnabled(),"Planned Start Time Field is not editable");
		
		closeXWindow();

		clickSelectAllcheckButtonInOverview();
		
		waitForExtJSAjaxComplete(5);
		
		clickSelectAllcheckButtonInOverview();
		
		waitForExtJSAjaxComplete(5);
		
		//verification of In Progress  and In Preparation Status
		
		Grid.checkRowInGriByTextValue(driver, wo1InPreparationStatus);

		waitForExtJSAjaxComplete(10);

		closeXWindow("@class","x-resizable-pinned");

		waitForExtJSAjaxComplete(10);

		Grid.checkRowInGriByTextValue(driver, wo1InProgressStatus);

		waitForExtJSAjaxComplete(25);

		clickEditButton();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(getWarningDialogTextMsg().contains(warMsg),"Edit Selected Items button is Verified");

		waitForExtJSAjaxComplete(10);

		clickOnDialogButton("OK");
		
		softAssert.assertAll();

		Reporter.log("Editing Multiple WO is successfully verified", true);
		
	}
	
	/**
	 * Test Case ID: C114757
	 * Author :SRD
	 */
	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testEditingMultipleWOOfInpreparation() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Editing Multiple WO of Inpreparation StatusC114757" + " </span><br>",
				true);

		String wo1InPreparationStatus  = "C75219_Inpreparation1";
		String wo2InPreparationStatus  = "C75219_Inpreparation2";
		
		String severity 			   = "1preSeverityRef";
		String priority				   = "1prePriorityRef";
		String nature				   = "Default Nature";
		String location			       = "1aqaPreParSites";
		String costcenter			   = "myMCS Default Cost Center";
		String glaccount               = "myMCS Default GL Account";
		String finKey3 				   = "3preValue";
		
	
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testEditingMultipleWOOfInpreparation");
		

		expandAdministration();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		clickXPath(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();

		waitForExtJSAjaxComplete(25);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(getWarningDialogTextMsg().contains("Please select an item"),"Edit Selected Items button is Verified");

		waitForExtJSAjaxComplete(10);

		clickOnDialogButton("OK");

		waitForExtJSAjaxComplete(10);
		
		filterGridForWO("@realid","mogrid","C75219_Inpreparation","Reference");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//verification of In Preparation Status

		Grid.checkRowInGriByTextValue(driver, wo1InPreparationStatus);

		waitForExtJSAjaxComplete(10);
		
		closeXWindow("@class","x-resizable-pinned");
		
		waitForExtJSAjaxComplete(10);

		Grid.checkRowInGriByTextValue(driver, wo2InPreparationStatus);
		
		waitForExtJSAjaxComplete(25);

		clickEditButton();
		
		softAssert.assertTrue(isWOMultiEditWindowOpen(),"The WO's are opened in multi-edit mode");

		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertFalse(isFieldEnabledInWOMultiWindow("Fiscal Entity:","input"),"Fiscal Entity is read-only field ");
		
		clickCustomCheckBox("Priority:");
		
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isFieldEnabledInWOMultiWindow("Priority:","div"),"Priority is Enabled");
		
		clickCustomCheckBox("Severity:");
		
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isFieldEnabledInWOMultiWindow("Severity:","div"),"Severity is Enabled");
		
		clickCustomCheckBox("Due Date and Time:");

		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isDueDateEnabled(),"Due Date field is enabled and editable");

		softAssert.assertTrue(isDueTimeEnabled(),"Due Time field is enabled and editable");
		
		clickCustomCheckBox("Nature:");
		
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isFieldEnabledInWOMultiWindow("Nature:","div"),"Nature is editable");
		
		clickCustomCheckBox("Location:");
		
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isFieldEnabledInWOMultiWindow("Location:","div"),"Location is editable");
		
		clickCustomCheckBox("Assigned To:");
		
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isFieldEnabledInWOMultiWindow("Assigned To:","div"),"Assigned To is editable");
		
		clickCustomCheckBox("Task Force:");
		
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isFieldEnabledInWOMultiWindow("Task Force:","div"),"Task Force is editable");
		
		clickCustomCheckBox("Planned Start");
		
		softAssert.assertTrue(isPlannedStartDateEnabled(),"Planned Start Date Field is editable");

		softAssert.assertTrue(isPlannedStartTimeEnabled(),"Planned Start Time Field is editable");
		
		clickCustomCheckBox("Planned End");

		softAssert.assertTrue(isPlannedEndDateEnabled(),"Planned End Date Field is editable");

		softAssert.assertTrue(isPlannedEndTimeEnabled(),"Planned End Time Field is editable"); 
		
		clickCustomCheckBox("Cost Center:");

		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isFieldEnabledInWOMultiWindow("Cost Center:","div"),"Cost Center is Enabled");
		
		clickCustomCheckBox("GL Account:");
		
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isFieldEnabledInWOMultiWindow("GL Account:","div"),"GL Account: is Enabled");
		
		clickCustomCheckBox("FIN_KEY_3:");
		
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(isFieldEnabledInWOMultiWindow("FIN_KEY_3:","div"),"FIN_KEY_3: is Enabled");
		
		softAssert.assertEquals(priority,getPriority("workorderMultiEditWindow"),"Priority field value is prepopulated with value because all the WOs has same Priority");
		
		softAssert.assertEquals(severity,getSeverity("workorderMultiEditWindow"),"severity field value is prepopulated with value because all the WOs has same severity");
		
		softAssert.assertEquals(nature,getNature("workorderMultiEditWindow"),"nature field value is prepopulated with value because all the WOs has same nature");
		
		setPriority("workorderMultiEditWindow",priority);
		
		waitForExtJSAjaxComplete(10);
		
		setSeverity("workorderMultiEditWindow",severity);
		
		waitForExtJSAjaxComplete(10);
		
		setNature("workorderMultiEditWindow",nature);
		
		waitForExtJSAjaxComplete(10);
		
		setLocation("workorderMultiEditWindow",location);
		
		waitForExtJSAjaxComplete(10);
		
		setCostCenter("workorderMultiEditWindow",costcenter);
		
		waitForExtJSAjaxComplete(10);
		
		setGlAccount("workorderMultiEditWindow",glaccount);
		
		waitForExtJSAjaxComplete(10);
		
		setFinKey3("workorderMultiEditWindow",finKey3);
		
		waitForExtJSAjaxComplete(10);
		
		clickButtonInWOMultiEditWindow("Save & Close");
		
		waitForExtJSAjaxComplete(25); 
	
		clickSelectAllcheckButtonInOverview();
		
		waitForExtJSAjaxComplete(5);
		
		clickSelectAllcheckButtonInOverview();
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValue(driver, wo1InPreparationStatus);

		waitForExtJSAjaxComplete(10);
		
		clickGeneralTab("window");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(priority,getPriority("window")," priority value is Updated");
		
		softAssert.assertEquals(severity,getSeverity("window")," severity value is Updated");
		
		softAssert.assertEquals(nature,getNature("window")," nature value is Updated");
		
		softAssert.assertEquals(location,getLocation("window")," location value is Updated");
		
		clickBillingTab("window");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(costcenter,getCostCenter("window")," costcenter value is Updated");
		
		softAssert.assertEquals(glaccount,getGlAccount("window")," glaccount value is Updated");
		
		softAssert.assertEquals(finKey3,getFinKey3("window")," finKey3 value is Updated");
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(5);
		
		clickSelectAllcheckButtonInOverview();
		
		waitForExtJSAjaxComplete(5);
		
		clickSelectAllcheckButtonInOverview();
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValue(driver, wo2InPreparationStatus);

		waitForExtJSAjaxComplete(10);
		
		clickGeneralTab("window");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(priority,getPriority("window")," priority value is Updated");
		
		softAssert.assertEquals(severity,getSeverity("window")," severity value is Updated");
		
		softAssert.assertEquals(nature,getNature("window")," nature value is Updated");
		
		softAssert.assertEquals(location,getLocation("window")," location value is Updated");
		
		clickBillingTab("window");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(costcenter,getCostCenter("window")," costcenter value is Updated");
		
		softAssert.assertEquals(glaccount,getGlAccount("window")," glaccount value is Updated");
		
		softAssert.assertEquals(finKey3,getFinKey3("window")," finKey3 value is Updated");
		
		softAssert.assertAll();

		Reporter.log("Editing Multiple WO of Inpreparation is successfully verified", true);
	
	
	}

	/**
	 * Test Case ID: C114757
	 * Author :SRD
	 */
	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testEditingMultipleWOOfInProgress() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Editing Multiple WO of InProgress StatusC114757" + " </span><br>",
				true);

		String wo1InProgressStatus     = "C75219_Inprogress1";
		String wo2InProgressStatus     = "C75219_Inprogress2";

		String severity 			   = "1preSeverityRef";
		String priority				   = "1prePriorityRef";
		String nature				   = "Default Nature";
		String location			       = "1aqaPreParSites";
		String costcenter			   = "myMCS Default Cost Center";
		String glaccount               = "myMCS Default GL Account";
		String finKey3 				   = "3preValue";


		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testEditingMultipleWOOfInProgress");


		expandAdministration();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		clickXPath(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(10);

		filterGridForWO("@realid","mogrid","C75219_Inprogress","Reference");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		Grid.checkRowInGriByTextValue(driver, wo1InProgressStatus);

		waitForExtJSAjaxComplete(10);

		closeXWindow("@class","x-resizable-pinned");

		waitForExtJSAjaxComplete(10);

		Grid.checkRowInGriByTextValue(driver, wo2InProgressStatus);

		waitForExtJSAjaxComplete(25);

		clickEditButton();

		softAssert.assertTrue(isWOMultiEditWindowOpen(),"The WO's are opened in multi-edit mode");

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Due Date and Time:"),"Customcheck box is Disabled for Due Date and Time");

		softAssert.assertFalse(isDueDateEnabled(),"Due Date field is not editable");

		softAssert.assertFalse(isDueTimeEnabled(),"Due Time field is not editable");

		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Task Force:"),"Customcheck box is Disabled for Task Force");

		softAssert.assertFalse(isFieldEnabledInWOMultiWindow("Task Force:","div"),"Task Force is not editable");

		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Assigned To:"),"Customcheck box is Disabled for Assigned To");

		softAssert.assertFalse(isFieldEnabledInWOMultiWindow("Assigned To:","div"),"Assigned To is not editable");

		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Planned Start"),"Customcheck box is Disabled for Planned Start date");

		softAssert.assertFalse(isPlannedStartDateEnabled(),"Planned Start Date Field is not editable");

		softAssert.assertFalse(isPlannedStartTimeEnabled(),"Planned Start Time Field is not editable");

		softAssert.assertEquals(priority,getPriority("workorderMultiEditWindow"),"Priority field value is prepopulated with value because all the WOs has same Priority");

		softAssert.assertEquals(severity,getSeverity("workorderMultiEditWindow"),"severity field value is prepopulated with value because all the WOs has same severity");

		softAssert.assertEquals(nature,getNature("workorderMultiEditWindow"),"nature field value is prepopulated with value because all the WOs has same nature");

		clickCustomCheckBox("Priority:");

		setPriority("workorderMultiEditWindow",priority);

		waitForExtJSAjaxComplete(10);

		clickCustomCheckBox("Severity:");

		waitForExtJSAjaxComplete(10);

		setSeverity("workorderMultiEditWindow",severity);

		waitForExtJSAjaxComplete(10);

		clickCustomCheckBox("Nature:");

		waitForExtJSAjaxComplete(10);

		setNature("workorderMultiEditWindow",nature);

		waitForExtJSAjaxComplete(10);

		clickCustomCheckBox("Location:");

		waitForExtJSAjaxComplete(10);

		setLocation("workorderMultiEditWindow",location);

		waitForExtJSAjaxComplete(10);

		clickCustomCheckBox("Cost Center:");

		waitForExtJSAjaxComplete(10);

		setCostCenter("workorderMultiEditWindow",costcenter);

		waitForExtJSAjaxComplete(10);

		clickCustomCheckBox("GL Account:");

		waitForExtJSAjaxComplete(10);

		setGlAccount("workorderMultiEditWindow",glaccount);

		waitForExtJSAjaxComplete(10);

		clickCustomCheckBox("FIN_KEY_3:");

		waitForExtJSAjaxComplete(10);

		setFinKey3("workorderMultiEditWindow",finKey3);

		waitForExtJSAjaxComplete(10);

		clickButtonInWOMultiEditWindow("Save & Close");

		waitForExtJSAjaxComplete(25); 

		//clickSelectAllcheckButtonInOverview();

		waitForExtJSAjaxComplete(5);

		Grid.checkRowInGriByTextValue(driver, wo1InProgressStatus);

		waitForExtJSAjaxComplete(25);

		clickGeneralTab("window");

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(priority,getPriority("window")," priority value is Updated");

		softAssert.assertEquals(severity,getSeverity("window")," severity value is Updated");

		softAssert.assertEquals(nature,getNature("window")," nature value is Updated");

		softAssert.assertEquals(location,getLocation("window")," location value is Updated");

		clickBillingTab("window");

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(costcenter,getCostCenter("window")," costcenter value is Updated");

		softAssert.assertEquals(glaccount,getGlAccount("window")," glaccount value is Updated");

		softAssert.assertEquals(finKey3,getFinKey3("window")," finKey3 value is Updated");

		closeXWindow();

		waitForExtJSAjaxComplete(5);

		clickSelectAllcheckButtonInOverview();

		waitForExtJSAjaxComplete(5);

		clickSelectAllcheckButtonInOverview();

		waitForExtJSAjaxComplete(5);

		Grid.checkRowInGriByTextValue(driver, wo2InProgressStatus);

		waitForExtJSAjaxComplete(10);

		clickGeneralTab("window");

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(priority,getPriority("window")," priority value is Updated");

		softAssert.assertEquals(severity,getSeverity("window")," severity value is Updated");

		softAssert.assertEquals(nature,getNature("window")," nature value is Updated");

		softAssert.assertEquals(location,getLocation("window")," location value is Updated");

		clickBillingTab("window");

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(costcenter,getCostCenter("window")," costcenter value is Updated");

		softAssert.assertEquals(glaccount,getGlAccount("window")," glaccount value is Updated");

		softAssert.assertEquals(finKey3,getFinKey3("window")," finKey3 value is Updated");

		softAssert.assertAll();

		Reporter.log("Editing Multiple WO of InProgressStatus is successfully verified", true);


	}

	/**
	 * Test Case ID: C114757
	 * Author :SRD
	 */
	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testEditingMultipleWOOfSuspended() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Editing Multiple WO of Suspended StatusC114757" + " </span><br>",
				true);

		String wo1SuspendedStatus      = "C75219_suspended1";
		String wo2SuspendedStatus      = "C75219_suspended2";

		String severity 			   = "1preSeverityRef";
		String priority				   = "1prePriorityRef";
		String nature				   = "Default Nature";
		String location			       = "1aqaPreParSites";
		String costcenter			   = "myMCS Default Cost Center";
		String glaccount               = "myMCS Default GL Account";
		String finKey3 				   = "3preValue";


		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testEditingMultipleWOOfSuspended");


		expandAdministration();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		clickXPath(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(10);

		filterGridForWO("@realid","mogrid","C75219_Suspended","Reference");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		Grid.checkRowInGriByTextValue(driver, wo1SuspendedStatus);

		waitForExtJSAjaxComplete(10);

		closeXWindow("@class","x-resizable-pinned");

		waitForExtJSAjaxComplete(10);

		Grid.checkRowInGriByTextValue(driver, wo2SuspendedStatus);

		waitForExtJSAjaxComplete(25);

		clickEditButton();

		softAssert.assertTrue(isWOMultiEditWindowOpen(),"The WO's are opened in multi-edit mode");

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Due Date and Time:"),"Customcheck box is Disabled for Due Date and Time");

		softAssert.assertFalse(isDueDateEnabled(),"Due Date field is not editable");

		softAssert.assertFalse(isDueTimeEnabled(),"Due Time field is not editable");

		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Task Force:"),"Customcheck box is Disabled for Task Force");

		softAssert.assertFalse(isFieldEnabledInWOMultiWindow("Task Force:","div"),"Task Force is not editable");

		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Assigned To:"),"Customcheck box is Disabled for Assigned To");

		softAssert.assertFalse(isFieldEnabledInWOMultiWindow("Assigned To:","div"),"Assigned To is not editable");

		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Planned Start"),"Customcheck box is Disabled for Planned Start date");

		softAssert.assertFalse(isPlannedStartDateEnabled(),"Planned Start Date Field is not editable");

		softAssert.assertFalse(isPlannedStartTimeEnabled(),"Planned Start Time Field is not editable");

		softAssert.assertEquals(priority,getPriority("workorderMultiEditWindow"),"Priority field value is prepopulated with value because all the WOs has same Priority");

		softAssert.assertEquals(severity,getSeverity("workorderMultiEditWindow"),"severity field value is prepopulated with value because all the WOs has same severity");

		softAssert.assertEquals(nature,getNature("workorderMultiEditWindow"),"nature field value is prepopulated with value because all the WOs has same nature");

		clickCustomCheckBox("Priority:");

		setPriority("workorderMultiEditWindow",priority);

		waitForExtJSAjaxComplete(10);

		clickCustomCheckBox("Severity:");

		waitForExtJSAjaxComplete(10);

		setSeverity("workorderMultiEditWindow",severity);

		waitForExtJSAjaxComplete(10);

		clickCustomCheckBox("Nature:");

		waitForExtJSAjaxComplete(10);

		setNature("workorderMultiEditWindow",nature);

		waitForExtJSAjaxComplete(10);

		clickCustomCheckBox("Location:");

		waitForExtJSAjaxComplete(10);

		setLocation("workorderMultiEditWindow",location);

		waitForExtJSAjaxComplete(10);

		clickCustomCheckBox("Cost Center:");

		waitForExtJSAjaxComplete(10);

		setCostCenter("workorderMultiEditWindow",costcenter);

		waitForExtJSAjaxComplete(10);

		clickCustomCheckBox("GL Account:");

		waitForExtJSAjaxComplete(10);

		setGlAccount("workorderMultiEditWindow",glaccount);

		waitForExtJSAjaxComplete(10);

		clickCustomCheckBox("FIN_KEY_3:");

		waitForExtJSAjaxComplete(10);

		setFinKey3("workorderMultiEditWindow",finKey3);

		waitForExtJSAjaxComplete(10);

		clickButtonInWOMultiEditWindow("Save & Close");

		waitForExtJSAjaxComplete(25); 

		//clickSelectAllcheckButtonInOverview();

		waitForExtJSAjaxComplete(5);

		Grid.checkRowInGriByTextValue(driver,wo1SuspendedStatus);

		waitForExtJSAjaxComplete(25);

		clickGeneralTab("window");

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(priority,getPriority("window")," priority value is Updated");

		softAssert.assertEquals(severity,getSeverity("window")," severity value is Updated");

		softAssert.assertEquals(nature,getNature("window")," nature value is Updated");

		softAssert.assertEquals(location,getLocation("window")," location value is Updated");

		clickBillingTab("window");

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(costcenter,getCostCenter("window")," costcenter value is Updated");

		softAssert.assertEquals(glaccount,getGlAccount("window")," glaccount value is Updated");

		softAssert.assertEquals(finKey3,getFinKey3("window")," finKey3 value is Updated");

		closeXWindow();

		waitForExtJSAjaxComplete(5);

		clickSelectAllcheckButtonInOverview();

		waitForExtJSAjaxComplete(5);

		clickSelectAllcheckButtonInOverview();

		waitForExtJSAjaxComplete(5);

		Grid.checkRowInGriByTextValue(driver, wo2SuspendedStatus);

		waitForExtJSAjaxComplete(10);

		clickGeneralTab("window");

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(priority,getPriority("window")," priority value is Updated");

		softAssert.assertEquals(severity,getSeverity("window")," severity value is Updated");

		softAssert.assertEquals(nature,getNature("window")," nature value is Updated");

		softAssert.assertEquals(location,getLocation("window")," location value is Updated");

		clickBillingTab("window");

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(costcenter,getCostCenter("window")," costcenter value is Updated");

		softAssert.assertEquals(glaccount,getGlAccount("window")," glaccount value is Updated");

		softAssert.assertEquals(finKey3,getFinKey3("window")," finKey3 value is Updated");

		softAssert.assertAll();

		Reporter.log("Editing Multiple WO of Suspended is successfully verified", true);


	}

	/**
	 * Test Case ID: C114757
	 * Author :SRD
	 */
	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testEditingMultipleWOOfFinished() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Editing Multiple WO of Suspended StatusC114757" + " </span><br>",
				true);

		String wo1FinishedStatus       = "C75219_finished1";
		String wo2FinishedStatus       = "C75219_finished2";

		String severity 			   = "1preSeverityRef";
		String priority				   = "1prePriorityRef";
		String nature				   = "Default Nature";
		String location			       = "1aqaPreParSites";
		String costcenter			   = "myMCS Default Cost Center";
		String glaccount               = "myMCS Default GL Account";
		String finKey3 				   = "3preValue";


		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testEditingMultipleWOOfSuspended");


		expandAdministration();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		clickXPath(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(10);

		filterGridForWO("@realid","mogrid","C75219_finished","Reference");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		Grid.checkRowInGriByTextValue(driver, wo1FinishedStatus);

		waitForExtJSAjaxComplete(10);

		closeXWindow("@class","x-resizable-pinned");

		waitForExtJSAjaxComplete(10);

		Grid.checkRowInGriByTextValue(driver, wo2FinishedStatus);

		waitForExtJSAjaxComplete(25);

		clickEditButton();

		softAssert.assertTrue(isWOMultiEditWindowOpen(),"The WO's are opened in multi-edit mode");

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Due Date and Time:"),"Customcheck box is Disabled for Due Date and Time");

		softAssert.assertFalse(isDueDateEnabled(),"Due Date field is not editable");

		softAssert.assertFalse(isDueTimeEnabled(),"Due Time field is not editable");

		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Task Force:"),"Customcheck box is Disabled for Task Force");

		softAssert.assertFalse(isFieldEnabledInWOMultiWindow("Task Force:","div"),"Task Force is not editable");

		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Assigned To:"),"Customcheck box is Disabled for Assigned To");

		softAssert.assertFalse(isFieldEnabledInWOMultiWindow("Assigned To:","div"),"Assigned To is not editable");

		softAssert.assertTrue(isCustomcheckboxDisabledInWOMultiWindow("Planned Start"),"Customcheck box is Disabled for Planned Start date");

		softAssert.assertFalse(isPlannedStartDateEnabled(),"Planned Start Date Field is not editable");

		softAssert.assertFalse(isPlannedStartTimeEnabled(),"Planned Start Time Field is not editable");

		softAssert.assertEquals(priority,getPriority("workorderMultiEditWindow"),"Priority field value is prepopulated with value because all the WOs has same Priority");

		softAssert.assertEquals(severity,getSeverity("workorderMultiEditWindow"),"severity field value is prepopulated with value because all the WOs has same severity");

		softAssert.assertEquals(nature,getNature("workorderMultiEditWindow"),"nature field value is prepopulated with value because all the WOs has same nature");

		clickCustomCheckBox("Priority:");

		setPriority("workorderMultiEditWindow",priority);

		waitForExtJSAjaxComplete(10);

		clickCustomCheckBox("Severity:");

		waitForExtJSAjaxComplete(10);

		setSeverity("workorderMultiEditWindow",severity);

		waitForExtJSAjaxComplete(10);

		clickCustomCheckBox("Nature:");

		waitForExtJSAjaxComplete(10);

		setNature("workorderMultiEditWindow",nature);

		waitForExtJSAjaxComplete(10);

		clickCustomCheckBox("Location:");

		waitForExtJSAjaxComplete(10);

		setLocation("workorderMultiEditWindow",location);

		waitForExtJSAjaxComplete(10);

		clickCustomCheckBox("Cost Center:");

		waitForExtJSAjaxComplete(10);

		setCostCenter("workorderMultiEditWindow",costcenter);

		waitForExtJSAjaxComplete(10);

		clickCustomCheckBox("GL Account:");

		waitForExtJSAjaxComplete(10);

		setGlAccount("workorderMultiEditWindow",glaccount);

		waitForExtJSAjaxComplete(10);

		clickCustomCheckBox("FIN_KEY_3:");

		waitForExtJSAjaxComplete(10);

		setFinKey3("workorderMultiEditWindow",finKey3);

		waitForExtJSAjaxComplete(10);

		clickButtonInWOMultiEditWindow("Save & Close");

		waitForExtJSAjaxComplete(25); 

		//clickSelectAllcheckButtonInOverview();

		waitForExtJSAjaxComplete(5);

		Grid.checkRowInGriByTextValue(driver,wo1FinishedStatus);

		waitForExtJSAjaxComplete(25);

		clickGeneralTab("window");

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(priority,getPriority("window")," priority value is Updated");

		softAssert.assertEquals(severity,getSeverity("window")," severity value is Updated");

		softAssert.assertEquals(nature,getNature("window")," nature value is Updated");

		softAssert.assertEquals(location,getLocation("window")," location value is Updated");

		clickBillingTab("window");

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(costcenter,getCostCenter("window")," costcenter value is Updated");

		softAssert.assertEquals(glaccount,getGlAccount("window")," glaccount value is Updated");

		softAssert.assertEquals(finKey3,getFinKey3("window")," finKey3 value is Updated");

		closeXWindow();

		waitForExtJSAjaxComplete(5);

		clickSelectAllcheckButtonInOverview();

		waitForExtJSAjaxComplete(5);

		clickSelectAllcheckButtonInOverview();

		waitForExtJSAjaxComplete(5);

		Grid.checkRowInGriByTextValue(driver, wo2FinishedStatus);

		waitForExtJSAjaxComplete(10);

		clickGeneralTab("window");

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(priority,getPriority("window")," priority value is Updated");

		softAssert.assertEquals(severity,getSeverity("window")," severity value is Updated");

		softAssert.assertEquals(nature,getNature("window")," nature value is Updated");

		softAssert.assertEquals(location,getLocation("window")," location value is Updated");

		clickBillingTab("window");

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(costcenter,getCostCenter("window")," costcenter value is Updated");

		softAssert.assertEquals(glaccount,getGlAccount("window")," glaccount value is Updated");

		softAssert.assertEquals(finKey3,getFinKey3("window")," finKey3 value is Updated");

		softAssert.assertAll();

		Reporter.log("Editing Multiple WO of Finished is successfully verified", true);


	}
	
	/**
	 * Test Case ID: C111533
	 * Author :SRD
	 */
	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testAddingActioToMultipleWO() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Adding Action To Multiple WO C111533" + " </span><br>",
				true);

		String wo1InPreparationStatus  = "C75219_Inpreparation6";
		String wo2InPreparationStatus  = "C75219_Inpreparation7";
		String wo3InPreparationStatus  = "C75219_Inpreparation3";
		String wo4InPreparationStatus  = "C75219_Inpreparation4";
		String wo5InPreparationStatus  = "C75219_Inpreparation5";
		
		String warnMsgOfDiffStatus	   = "It is not possible to add an action due to the different status of the Work Orders";
		String warnMsgOfDiffTypes	   = "It is not possible to add an Action due to the different types of the Work Orders";
	
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testAddingActioToMultipleWO");
		

		expandAdministration();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		clickXPath(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();

		waitForExtJSAjaxComplete(10);
		
		filterGridForWO("@realid","mogrid","C75219_Inpreparation","Reference");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		Grid.checkRowInGriByTextValue(driver, wo1InPreparationStatus);

		waitForExtJSAjaxComplete(10);
		
		closeXWindow("@class","x-resizable-pinned");
		
		waitForExtJSAjaxComplete(10);

		Grid.checkRowInGriByTextValue(driver, wo2InPreparationStatus);
		
		waitForExtJSAjaxComplete(25);

		clickEditButton();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(isAddActionButtonEnabled(),"Add Action button is enabled");
		
		waitForExtJSAjaxComplete(10);
		
		clickButtonInWOMultiEditWindow("Add Action");
		
		waitForExtJSAjaxComplete(20);
		
		clickFirstActionInPossibleActions();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		String status =getResultingStatusOfAction();
		
		waitForExtJSAjaxComplete(20);
		
		clickButtonInActionWindow("Add Action and Close");
		
		waitForExtJSAjaxComplete(10);
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(10);
		
		clickSelectAllcheckButtonInOverview();
		
		waitForExtJSAjaxComplete(10);
		
		clickSelectAllcheckButtonInOverview();
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, wo1InPreparationStatus);

		waitForExtJSAjaxComplete(10);
		
		clickTrackingTab(WORKORDER_WINDOW_DETAIL);
		
		waitForExtJSAjaxComplete(25);
		
		clickTrackingHistoryTab();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(isWOStatusAddedToAction(status),"The Action is added to the edited WO's and appropriate History record is added to the WO");
		
		waitForExtJSAjaxComplete(10);
		
		closeXWindow("@class","x-resizable-pinned");

		waitForExtJSAjaxComplete(10);
		
		clickSelectAllcheckButtonInOverview();
		
		waitForExtJSAjaxComplete(10);
		
		clickSelectAllcheckButtonInOverview();
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, wo2InPreparationStatus);

		waitForExtJSAjaxComplete(10);
		
		clickTrackingTab(WORKORDER_WINDOW_DETAIL);
		
		waitForExtJSAjaxComplete(25);
		
		clickTrackingHistoryTab();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(isWOStatusAddedToAction(status),"The Action is added to the edited WO's and appropriate History record is added to the WO");
		
		waitForExtJSAjaxComplete(10);
		
		closeXWindow("@class","x-resizable-pinned");

		waitForExtJSAjaxComplete(10);
		
		clickSelectAllcheckButtonInOverview();
		
		waitForExtJSAjaxComplete(10);
		
		clickSelectAllcheckButtonInOverview(); 
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, wo3InPreparationStatus);

		waitForExtJSAjaxComplete(10);
		
		closeXWindow("@class","x-resizable-pinned");
		
		waitForExtJSAjaxComplete(10);

		Grid.checkRowInGriByTextValue(driver, wo5InPreparationStatus);

		waitForExtJSAjaxComplete(10);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertFalse(isAddActionButtonEnabled(),"Add Action button is Disabled");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(getInfoBarMsgOfWindow("@class","workorderMultiEditWindow").contains(warnMsgOfDiffStatus),"The appropriate message is displayed for different status of the Work Orders");
		
		waitForExtJSAjaxComplete(10);
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(10);
		
		clickSelectAllcheckButtonInOverview();
		
		waitForExtJSAjaxComplete(10);
		
		clickSelectAllcheckButtonInOverview();
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, wo3InPreparationStatus);

		waitForExtJSAjaxComplete(10);
		
		closeXWindow("@class","x-resizable-pinned");
		
		waitForExtJSAjaxComplete(10);

		Grid.checkRowInGriByTextValue(driver, wo4InPreparationStatus);

		waitForExtJSAjaxComplete(10);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
	
		softAssert.assertFalse(isAddActionButtonEnabled(),"Add Action button is Disabled");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(getInfoBarMsgOfWindow("@class","workorderMultiEditWindow").contains(warnMsgOfDiffTypes),"The appropriate message is displayed for different types of the Work Orders");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();

		Reporter.log("Adding Action To Multiple WO is successfully verified", true);
		
	}
	
	/**
	 * Test Case ID:C119599,C119602,C119603,C119600,C120706
	 * C119604,C103785,C120709,C114836,C124255,C120582
	 * Author :ssa
	 */
	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testQGRButtonBasedOnProductSelection() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C119599:The button Quick Goods Return is disabled when user selects non-Stock Items"
				+"C119602:The button Quick Goods Return is disabled when user selects stock items that are in status Requested and Needed"
				+"C119603:The button Quick Goods Return is disabled when user selects stock items that are in status Consumed and Requested </span><br>"
				+"C119600:The button Quick Goods Return is disabled when user selects stock items that are in status Consumed and Needed"
				+"C119604:The button Quick Goods Return is enabled when user selects Stock Items that are in Status consumed" 
				+"C103785:Right to View Stock Info on BOM </span><br>"
				+"C120709:Verify error message when we are adding products which are not having storage locations for selected warehouse"
				+"C114836/C114837:Error message is shown when user attempts to close the window without saving"
				+"C124255/C119609/C119607:Verify warehouse rights for Quick Goods Return"
				+"C120706:Verify error message for zero quantity on Quick Goods Return transaction"
				+"C110685:A new button Quick Goods Return is added on the BOM tab of Workorders form"
				+"C117493: Mandatory fields indication by asterik in 'QGR' form on BOM line"
				+"C119638: The default value of each quantity is Zero in BOM line"
				+"C120582:Visual indication can not be applicable when Returned Qty is lessthan or equal to Issued Qty", true);
		
		String testWorkOrder = "MultipleWOLinking";
		String user=this.getUserLastNameOfLoggedInUser();
		String quantity="2";
		int  quantityQGR= Integer.parseInt(quantity);
		String productReference = "1preProdRef";
		String productReference1 = "6preProdRef";
		String productReference2 = "4preConsRef";
		String productReference3 = "2preConsRef";
		String changeWH="BlockTransactionWH";
		String changeWH1="NoLocationsForWH";
		String warningMessage="One or more of the products selected for Quick Goods Return do not have a storage location in the selected warehous";
		String warningMessage1="The returned quantity should always be more than 0";
		String warningMessage2="You do not have the right to Add/Modify Goods Returns for this Warehouse. Please contact your Administrator.";
		String remark = "C91902"+getCurrentDate().substring(getCurrentDate().length()-5);
		String warningMessage3="Are you sure you want to cancel the Quick Goods Return(s)?";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testQGRButtonBasedOnProductSelection");
		
		click(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		setDetailsTabCollapsedMode();
		
		Thread.sleep(1500);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElementModifiedForChrome("@realid", "mogrid", testWorkOrder,"Reference");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueExactMatch(driver, testWorkOrder);
		
		waitForExtJSAjaxComplete(20);
		
		clickTimeMaterialTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickTimeMaterialBillMaterialTab();
		
		waitForExtJSAjaxComplete(20);
		
		//C119602 //C103785
		selectMutipleBOMLines("Needed",productReference);

		//C119638
		List<Integer> ReservedQty=getBOMLineQuantities("Needed", "Reserved Qty", productReference);
		List<Integer> IssuedQty=getBOMLineQuantities("Needed", "Issued Qty", productReference);
		List<Integer> ConsumedQty=getBOMLineQuantities("Needed", "Consumed Qty", productReference);
		List<Integer> ReturnedQty=getBOMLineQuantities("Needed", "Returned Qty", productReference);

		int[] defaultvalue ={0};

		softAssert.assertEquals(ReservedQty.toArray(), defaultvalue, "default value of ReservedQty field is 0");
		softAssert.assertEquals(IssuedQty.toArray(), defaultvalue, "default value of IssuedQty field is 0");
		softAssert.assertEquals(ConsumedQty.toArray(), defaultvalue, "default value of ConsumedQty field is 0");
		softAssert.assertEquals(ReturnedQty.toArray(), defaultvalue, "default value of ReturnedQty field is 0");

		softAssert.assertTrue(isDisabledButtonInBOMTab("Quick Goods Return"),"QGR button is disabled when we Select non Stock Items that are in status Needed");
		
		//C119599
		selectNonStockIntemsInBOMLines("Needed","no-stock-item");
		
		softAssert.assertTrue(isStockInfoButtonDisabledInBOMTab(),"Stock Info button is disabled when we Select non Stock Items");
		
		//C119603
		selectMutipleBOMLines("Requested",productReference1);
		
		waitForExtJSAjaxComplete(20);
		
		selectMutipleBOMLines("Consumed",productReference2);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isDisabledButtonInBOMTab("Quick Goods Return"),"QGR button is disabled when we select Stock Items that are in status consumed and Requested");
		
		checkSelectAllcheckBoxButtonInBOM();
		
		waitForExtJSAjaxComplete(10);
		
		unCheckSelectAllcheckBoxButtonInBOM();
		
		waitForExtJSAjaxComplete(20);
		
		//C119600
		selectMutipleBOMLines("Needed",productReference);
		
		waitForExtJSAjaxComplete(20);
		
		selectMutipleBOMLines("Consumed",productReference2);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isDisabledButtonInBOMTab("Quick Goods Return"),"QGR button is disabled when we select Stock Items that are in status consumed and Needed");
		
		checkSelectAllcheckBoxButtonInBOM();
		
		waitForExtJSAjaxComplete(10);
		
		unCheckSelectAllcheckBoxButtonInBOM();
		
		waitForExtJSAjaxComplete(20);
		
		//C119604
		selectMutipleBOMLines("Consumed",productReference2);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(isDisabledButtonInBOMTab("Quick Goods Return"),"QGR button is enabled when we select Stock Items that are in status consumed");
		
		//C110685
		softAssert.assertTrue(isQGRButtonIsAddedNextToQGI(),"Quick Goods Return button is added next to Quick Goods Issue button");
		
		waitForExtJSAjaxComplete(20);
		
		clickOnQuickGoodsReturnButtonInBOM();
		
		waitForExtJSAjaxComplete(20);
		
		//C117493
		Reporter.log("Check UI of mandatory fields (bold text, asterisk)", true);

		softAssert.assertTrue(isFieldMandatory(driver, "storage-location-window", "Warehouse"), "Warehouse field has correct UI (Bold text, asterix)");

		softAssert.assertTrue(isFieldMandatory(driver, "storage-location-window", "Returned By"), "Returned By field has correct UI (Bold text, asterix)");

		waitForExtJSAjaxComplete(10);
		//C120709
		selectReturnByInQGR(user);
		
		waitForExtJSAjaxComplete(20);
		
		setMultiLineRemarksInQGR(productReference2,remark);
		
		waitForExtJSAjaxComplete(20);
		
		selectWarehouseInQGR(changeWH);
		
		waitForExtJSAjaxComplete(20);
		
		setMultiLineQuantityInQGR(productReference2,quantity);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnSaveInQGR();
		
		
		String preMessage=getInfoBarMessageInBOM();
		
		System.err.println(preMessage);
		
		softAssert.assertTrue(preMessage.contains(warningMessage), "Error message is displayed for products are not available in the warehouse");
		
		clickOnQuickGoodsReturnButtonInBOM();
		
		waitForExtJSAjaxComplete(20);
		
		selectReturnByInQGR(user);
		
		waitForExtJSAjaxComplete(20);
		
		setMultiLineRemarksInQGR(productReference2,remark);
		
		waitForExtJSAjaxComplete(20);
		
		selectWarehouseInQGR(changeWH1);
		
		waitForExtJSAjaxComplete(20);
		
		setMultiLineQuantityInQGR(productReference2,quantity);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnSaveInQGR();
		
		waitForExtJSAjaxComplete(5);
		
		String postMessage=getInfoBarMessageInBOM();
		
		softAssert.assertTrue(postMessage.contains(warningMessage), "Error message is displayed for products which are not having storage locations");
		
		waitForExtJSAjaxComplete(20);
		
		//C103813
		List<Integer> availableIssuedQty=getBOMLineQuantities("Consumed", "Issued Qty", productReference2);
		
		waitForExtJSAjaxComplete(20);

		clickOnQuickGoodsReturnButtonInBOM();
		
		waitForExtJSAjaxComplete(20);
		
		//C118392:Read only fields in the Quick Goods Return form
		softAssert.assertTrue(isButtonReadOnlyInQGRWindow("Reference"), "This field is read only");
		softAssert.assertTrue(isButtonReadOnlyInQGRWindow("UOM"), "This field is read only");
		softAssert.assertTrue(isButtonReadOnlyInQGRWindow("Consumed Qty"), "This field is read only");
		softAssert.assertTrue(isButtonReadOnlyInQGRWindow("Warehouse"), "This field is read only");
		
		

		waitForExtJSAjaxComplete(20);

		selectReturnByInQGR(user);

		waitForExtJSAjaxComplete(20);

		//C114837
		setMultiLineQuantityInQGR(productReference2,quantity);

		clickOnCloseInQGR();

		waitForExtJSAjaxComplete(20);

		String message3=getWarningDialogTextMsg();

		//C120706
		softAssert.assertTrue(message3.contains(warningMessage3), "Dialogue message is displayed for unsaved changes");

		clickOnDialogButton("Yes");

		waitForExtJSAjaxComplete(20);

		clickOnQuickGoodsReturnButtonInBOM();
		
		waitForExtJSAjaxComplete(20);
		
		selectReturnByInQGR(user);
		
		waitForExtJSAjaxComplete(20);
		
		//C114836
		clickOnSaveInQGR();
		
		waitForExtJSAjaxComplete(20);
		
		String message1=getWarningDialogTextMsg();
		
		//C120706
		softAssert.assertTrue(message1.contains(warningMessage1), "Error message is displayed for mandatory fields");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		setMultiLineRemarksInQGR(productReference2,remark);
		
		waitForExtJSAjaxComplete(20);
		
		setMultiLineQuantityInQGR(productReference2,quantity);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnSaveInQGR();
		
		waitForExtJSAjaxComplete(20);
		
		//C120582
		List<Integer> postAvailableConsumedQty=this.getBOMLineQuantities("Consumed", "Consumed Qty", productReference2);
		
		softAssert.assertTrue((postAvailableConsumedQty.get(0)==(availableIssuedQty.get(0)-quantityQGR)),"Consumed Qty value is Zero");
		
		checkSelectAllcheckBoxButtonInBOM();
		
		waitForExtJSAjaxComplete(10);
		
		unCheckSelectAllcheckBoxButtonInBOM();
		
		selectMutipleBOMLines("Consumed",productReference3);
		
		//C124255
		clickOnQuickGoodsReturnButtonInBOM();
		
		waitForExtJSAjaxComplete(5);
		
		String message2=getInfoBarMessageInBOM();
		
		softAssert.assertTrue(message2.contains(warningMessage2), "Error message is displayed for no rights for warehouse");
		 
		softAssert.assertAll();

		Reporter.log("Verified Quick Goods Return button based on product selection", true);

	}

	/**
	 * Test Case Id : C111730, C114418, C114516
	 * Author       : MMA
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testAllowAttachmentsToChecklist() throws Exception{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ " C111730 : Attachments are allowed in case appropriate option is enabled in the Cheklist Template (MYM-24556)"
				+ " C114418 : Documents added to the conditional questions remain in the Checklist in case parent question is unfilled (MYM-24556)"
				+ " C114516 : Document is added to the checklist item with item unfilled (MYM-24556)",true);

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testAllowAttachmentsToChecklist");

		String testWorkOrder = "AQACheckListWO";
		List<String> checklistReferences = Arrays.asList("NoAttachmentsRef","ConditionalRef","NotApplicableRef");
		String conditionalQuestion = "Is child visible for conditional question, if wrong answer is answered to parent question";
		String childFile = "test.csv";
		String childFileDesc =  "childfileDesc";
		String fileRemark =  "FilRem" + getCurrentDate().substring(getCurrentDate().length()-6);
		String type = "labelen";

		expandAdministration();
		waitForExtJSAjaxComplete(5);

		click(XPATH_WORKORDERS);		
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();
		waitForExtJSAjaxComplete(15);

		//C111730
		filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", testWorkOrder, "Reference");
		waitForExtJSAjaxComplete(25);

		Grid.checkRowInGriByTextValueAndClick(driver, testWorkOrder);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		clickChecklistsObjectTab();
		waitForExtJSAjaxComplete(20);

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

		clickAddChecklist();
		waitForExtJSAjaxComplete(25);

		selectChecklist(checklistReferences.get(0));
		waitForExtJSAjaxComplete(20);

		selectChecklistGrid(checklistReferences.get(0));
		waitForExtJSAjaxComplete(5);

		//Open checkList
		clickViewChecklist();
		waitUntilCheckListIsLoaded();
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(isCheckListOpenedAsNewTab("wotabpanel", checklistReferences.get(0)), "Checklist tab is Opened");
		softAssert.assertFalse(isUIElementPresentInCheckList("No attachments","addfile.png"),"Panel with UI elements for adding a file is not displayed");

		closeModule("Checklists - "+checklistReferences.get(0));
		waitForExtJSAjaxComplete(5);

		closeModule("Work Order");
		waitForExtJSAjaxComplete(15);

		click(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();
		waitForExtJSAjaxComplete(15);

		filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", testWorkOrder, "Reference");
		waitForExtJSAjaxComplete(25);

		Grid.checkRowInGriByTextValueAndClick(driver, testWorkOrder);
		waitForExtJSAjaxComplete(30);
		waitForExtJSAjaxComplete(30);

		clickChecklistsObjectTab();
		waitForExtJSAjaxComplete(20);

		clickAddChecklist();
		waitForExtJSAjaxComplete(25);

		selectChecklist(checklistReferences.get(1));
		waitForExtJSAjaxComplete(10);

		selectChecklistGrid(checklistReferences.get(1));
		waitForExtJSAjaxComplete(10);

		clickViewChecklist();
		waitUntilCheckListIsLoaded();
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(isUIElementPresentInCheckList("Parent","addfile.png"),"Panel with UI elements for adding a file is displayed");

		//C114418
		enterText("5","Parent");
		softAssert.assertTrue(isQuestionAvailableInCheckList(conditionalQuestion),"Conditional question is displayed");
		waitForExtJSAjaxComplete(25);

		clickAttachmentOptionsChecklistItem(conditionalQuestion, "Add File");
		waitForExtJSAjaxComplete(25);

		setFileForCheckListItems(childFile, childFileDesc, fileRemark, type);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(isRowInGridPresent(conditionalQuestion, "1", childFile), "childFile "+childFile+"  is added to the Grid");
		waitForExtJSAjaxComplete(5);

		//Delete the Answer for Parent question
		enterText(" ","Parent");
		waitForExtJSAjaxComplete(5);

		softAssert.assertFalse(isQuestionAvailableInCheckList(conditionalQuestion),"Conditional question is hidden");

		//Fill the answer for parent question
		enterText("5","Parent");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(isQuestionAvailableInCheckList(conditionalQuestion),"Conditional question is displayed");
		waitForExtJSAjaxComplete(5);
		softAssert.assertTrue(isRowInGridPresent(conditionalQuestion, "1", childFile), "childFile "+childFile+"  is added to the Grid");
		waitForExtJSAjaxComplete(5);

		//C114516
		closeModule("Checklists - "+checklistReferences.get(1));
		waitForExtJSAjaxComplete(5);

		closeModule("Work Order");
		waitForExtJSAjaxComplete(15);

		click(XPATH_WORKORDERS);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();
		waitForExtJSAjaxComplete(15);

		filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", testWorkOrder, "Reference");
		waitForExtJSAjaxComplete(25);

		Grid.checkRowInGriByTextValueAndClick(driver, testWorkOrder);
		waitForExtJSAjaxComplete(30);
		waitForExtJSAjaxComplete(30);

		clickChecklistsObjectTab();
		waitForExtJSAjaxComplete(20);

		clickAddChecklist();
		waitForExtJSAjaxComplete(25);

		selectChecklist(checklistReferences.get(2));
		waitForExtJSAjaxComplete(10);

		selectChecklistGrid(checklistReferences.get(2));
		waitForExtJSAjaxComplete(10);

		clickViewChecklist();
		waitUntilCheckListIsLoaded();
		waitForExtJSAjaxComplete(25);

		clickAttachmentOptionsChecklistItem("item1", "Add File");
		waitForExtJSAjaxComplete(25);

		setFileForCheckListItems(childFile, childFileDesc, fileRemark, type);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(isRowInGridPresent("item1", "1", childFile), "File "+childFile+"  is added to the Grid");
		waitForExtJSAjaxComplete(5);

		//checked as Not applicable
		checkWOChecklistItemsInGrid("quality-paragraph","Not applicable");
		waitForExtJSAjaxComplete(5);

		//Uncheck Not applicable
		checkWOChecklistItemsInGrid("quality-paragraph","Not applicable");
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(isRowInGridPresent("item1", "1", childFile), "File "+childFile+"  is added to the Grid");
		waitForExtJSAjaxComplete(5);
		
		clickSubmitButton();
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isRowInGridPresent("item1", "1", childFile), "File "+childFile+"  is added to the Grid");
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertAll();
		Reporter.log("Attachments are allowed in case appropriate option is enabled in the Cheklist Template", true);
	}

	/**
	 * Test Case ID: C113796,C124374 
	 * Author :ssa
	 */

	@Test(enabled = true, retryAnalyzer = RetryAnalyzer.class)
	public void testCRUDChecklistsRestriction() throws Exception {

		Reporter.log(
				"<span style='font-weight:bold;color:#000033'> "
						+ "C124374:WO Status class change depending on filled-out checklists"
						+ "C113796:Checklist manipulations are not allowed for WO Finished/Archived Status</span><br>",
				true);

		String testWorkOrder = "TestCheckListRestriction";
		String checkListTempName = "TemplateTestRef";
		String checkListTempName1 = "AQAChkListTemplate";
		String checklistItem1 = "TestItem1";
		String text = "1";
		String checklistItem2 = "Comment:";
		String comment = "Comments Entered";
		String checklistItem3 = "Hello How Are you?:";
		String comment3 = "I am Fine";

		List<String> possibleActions = Arrays.asList("finish", "Archive");
		String errorMessage = "A Work Order cannot obtain Status of Classes Finished or Archived if it has open Checklists";
		String errorMessage1 = "Checklist manipulation are not allowed on Finished and Archived Work Orders";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testCRUDChecklistsRestriction");

		expandAdministration();

		waitForExtJSAjaxComplete(25);

		click(XPATH_WORKORDERS);

		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();

		waitForExtJSAjaxComplete(25);

		filterGridWithoutUsingMcsElementModifiedForChrome("@realid", "mogrid", testWorkOrder, "Reference");

		waitForExtJSAjaxComplete(25);

		Grid.checkRowInGriByTextValueAndClick(driver, testWorkOrder);

		waitForExtJSAjaxComplete(25);

		String preWOStatus = getWorkOrderStatus();

		waitForExtJSAjaxComplete(20);

		clickTrackingTab(WORKORDER_PANEL_DETAIL);

		waitForExtJSAjaxComplete(20);

		clickTrackingHistoryTab();

		waitForExtJSAjaxComplete(20);

		clickAddAction();

		waitForExtJSAjaxComplete(20);

		clickPossibleActions(possibleActions.get(0));

		waitForExtJSAjaxComplete(20);

		saveCloseAction(ADD_ACTION_WINDOW_HEADER);

		// Restriction message is displayed
		String preMessage = getWarningDialogTextMsg();

		softAssert.assertTrue(preMessage.contains(errorMessage), "Error message is displayed");

		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);

		closeAction(ADD_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);

		// Workorder status is not changed
		String postWOStatus = getWorkOrderStatus();

		softAssert.assertEquals(postWOStatus, preWOStatus, "Error message is displayed");

		waitForExtJSAjaxComplete(20);

	//	closeAction(ADD_ACTION_WINDOW_HEADER);

	//	waitForExtJSAjaxComplete(20);

	//	clickOnDialogButton("Yes");

	//	waitForExtJSAjaxComplete(20);

		clickChecklistsObjectTab();

		waitForExtJSAjaxComplete(20);

		selectChecklistGrid(checkListTempName);

		waitForExtJSAjaxComplete(20);

		clickViewChecklist();

		Thread.sleep(8000);

		waitForExtJSAjaxComplete(25);

		enterText(text, checklistItem1);

		waitForExtJSAjaxComplete(10);

		enterComments(comment, checklistItem2);

		waitForExtJSAjaxComplete(10);

		enterComments(comment3, checklistItem3);

		waitForExtJSAjaxComplete(10);

		clickSubmitButton();

		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("OK");

		waitForExtJSAjaxComplete(20);

		closeModule("Checklists - " + checkListTempName);

		waitForExtJSAjaxComplete(20);

		// C113796
		for (String passibleAction : possibleActions) {

			clearOverviewFilters();

			waitForExtJSAjaxComplete(20);

			filterGridWithoutUsingMcsElementModifiedForChrome("@class", "x-grid3-viewport", testWorkOrder, "Reference");

			waitForExtJSAjaxComplete(20);

			Grid.checkRowInGriByTextValueAndClick(driver, testWorkOrder);

			waitForExtJSAjaxComplete(20);

			clickTrackingTab(WORKORDER_PANEL_DETAIL);

			waitForExtJSAjaxComplete(20);

			clickTrackingHistoryTab();

			waitForExtJSAjaxComplete(20);

			String preWOStatus1 = getWorkOrderStatus();

			waitForExtJSAjaxComplete(10);

			clickAddAction();

			waitForExtJSAjaxComplete(20);

			clickPossibleActions(passibleAction);

			waitForExtJSAjaxComplete(20);

			saveCloseAction(ADD_ACTION_WINDOW_HEADER);

			waitForExtJSAjaxComplete(10);

			// Workorder status is changed
			String postWOStatus1 = getWorkOrderStatus();

			softAssert.assertNotEquals(postWOStatus1, preWOStatus1, "Status is changed to " + postWOStatus1 + " ");

			String windowID = getWindowIdByClass("x-window x-resizable-pinned");

			clickOnTab(windowID, "Checklists");

			waitForExtJSAjaxComplete(15);

			clickAddChecklist();

			waitForExtJSAjaxComplete(15);

			selectChecklist(checkListTempName1);

			waitForExtJSAjaxComplete(15);

			String preMessage1 = getWarningDialogTextMsg();

			softAssert.assertTrue(preMessage1.contains(errorMessage1), "Error message is displayed for Add button");

			clickOnDialogButton("OK");

			waitForExtJSAjaxComplete(15);

			selectChecklistGrid(checkListTempName);

			waitForExtJSAjaxComplete(15);

			clickDeleteChecklist();

			waitForExtJSAjaxComplete(10);

			clickOnDialogButton("Yes");

			waitForExtJSAjaxComplete(10);

			String preMessage2 = getWarningDialogTextMsg();

			softAssert.assertTrue(preMessage2.contains(errorMessage1), "Error message is displayed for delete button");

			clickOnDialogButton("OK");

			waitForExtJSAjaxComplete(20);

			clickReopenChecklist();

			waitForExtJSAjaxComplete(10);

			String preMessage3 = getWarningDialogTextMsg();

			softAssert.assertTrue(preMessage3.contains(errorMessage1), "Error message is displayed for Reopen button");

			clickOnDialogButton("OK");

			waitForExtJSAjaxComplete(20);

			closeUsingToolBar(WO_WINDOW_XPATH);

		}

		softAssert.assertAll();

		Reporter.log("Checklist manipulations are not allowed for WO Finished/Archived Status", true);
	}
	

	/**
	 * Test Case ID:C118290
	 * Author :ssa
	 */
	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testAndVerifyProjectAndProjectPartInteraction() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"ID:C118290:Project / Project Part Interaction",true);
		
		String testWorkOrder = "MultipleWOLinking";
		String projectA="AqaProjectA";
		String projectB="AqaProjectB";
		String projectC="AqaProjectC";
		String errMsg="No results matching your query";
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testAndVerifyProjectAndProjectPartInteraction");
		
		click(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		setDetailsTabCollapsedMode();
		
		Thread.sleep(1500);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElementModifiedForChrome("@realid", "mogrid", testWorkOrder,"Reference");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueExactMatch(driver, testWorkOrder);
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		enterValueIntoProjectField("relatedProject",projectA);
		
		waitForExtJSAjaxComplete(10);
		
		String text=getProjectValueFromMenu();
		
		softAssert.assertTrue(text.contains(projectA), "Project is displayed in the menu");
		
		waitForExtJSAjaxComplete(20);
		
		enterValueIntoProjectField("relatedProject",projectC);
		
		waitForExtJSAjaxComplete(10);
		
		text=getProjectValueFromMenu();
		
		softAssert.assertTrue(errMsg.contains(text), "Project is not displayed in the menu");
		
		enterValueIntoProjectField("relatedProjPart",projectC);
		
		waitForExtJSAjaxComplete(20);
		
		//text=getProjectValueFromMenu();
		
		//softAssert.assertTrue(text.contains(projectC), "Project is displayed in the menu");
	
		List<String> actualValues=getAllAvailableProjects(WORKORDER_WINDOW_DETAIL);
		
		List<String> expectedValues=Arrays.asList("1preProjectRef", "apreProjectReference", "AqaProjectA", "AqaProjectB", "HDClntAccPrj");
		
		softAssert.assertTrue(actualValues.containsAll(expectedValues), "All Available Projects are displayed in the list");
		
		clickCANCELInLookupWindow();
		
		waitForExtJSAjaxComplete(20);
		
		setProject(WORKORDER_WINDOW_DETAIL, projectA);
		
		waitForExtJSAjaxComplete(20);
		
		clickLookup(WORKORDER_WINDOW_DETAIL, "relatedProjPart");
		
		softAssert.assertTrue(isTabPresentInProjectLookup("Tree") ,"Tab is displayed");
		softAssert.assertTrue(isTabPresentInProjectLookup("Select a Project Part") ,"Tab is displayed");
		
		clickTabInProjectPartLookupWindow("Select a Project Part");
		
		waitForExtJSAjaxComplete(20);
		
		
		List<String> expectedAvailableParts=Arrays.asList(projectC);
		List<String> actualAvailableParts= getValuesFromGridLookup("@class", "x-mcs-lookup-view", "Reference");
		
		softAssert.assertEquals(actualAvailableParts,expectedAvailableParts, "Project Part lookup is filtered via Project selected in the Project lookup");
		
		clickCANCELInLookupWindow();
		
		waitForExtJSAjaxComplete(20);
		
		setProject(WORKORDER_WINDOW_DETAIL, projectB);
		
		waitForExtJSAjaxComplete(20);
		
		clickLookup(WORKORDER_WINDOW_DETAIL, "relatedProjPart");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isLookUpGridEmpty("No items available") ,"Empty grid is displayed");
		
		waitForExtJSAjaxComplete(20);
		
		clickTabInProjectPartLookupWindow("Tree");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isNoPartsMessageDisplayed(projectB),""+projectB+" has no parts");
		
		clickCANCELInLookupWindow();
		
		//setProject(WORKORDER_WINDOW_DETAIL, "");
		
		enterValueIntoProjectField("relatedProject","");
		
		waitForExtJSAjaxComplete(20);
		
		setProjectPart(WORKORDER_WINDOW_DETAIL, projectC);
		
		waitForExtJSAjaxComplete(20);
		
		clickLookup(WORKORDER_WINDOW_DETAIL, "relatedProject");
		
		List<String> actualAvailableProjects= getValuesFromGridLookup("@id", getXWindowId("Select a Project"), "Project");
		
		softAssert.assertTrue(actualAvailableProjects.containsAll(expectedValues), "Project lookup is not filtered via Project Part lookup");
		
		clickCANCELInLookupWindow();
		
		waitForExtJSAjaxComplete(20);
		
		getProject(WORKORDER_WINDOW_DETAIL);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getProject(WORKORDER_WINDOW_DETAIL),projectA, "Project lookup is not filtered via Project Part lookup");
		
		softAssert.assertAll();
		
		Reporter.log("Project / Project Part Interaction verified", true);
	}

	/**
	 * Test Case ID :C114742,C118413
	 * Author :kve
	 */

	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testQGRFields() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C118413: Returned By field can contains the current user by default"
				+"C114742: Editable fields in the Quick Goods Return form", true);

		String testWorkOrder = "MultipleWOLinking1";
		String user=this.getUserNameOfLoggedInUserFirstNameLastNameFormat();
		String productReference = "6preProdRef";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testQGRFields");

		click(XPATH_WORKORDERS);

		waitForExtJSAjaxComplete(20);

		setDetailsTabCollapsedMode();

		Thread.sleep(1500);

		waitForExtJSAjaxComplete(20);

		filterGridWithoutUsingMcsElementModifiedForChrome("@realid", "mogrid", testWorkOrder,"Reference");

		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValueExactMatch(driver, testWorkOrder);

		waitForExtJSAjaxComplete(20);

		clickTimeMaterialTab();

		waitForExtJSAjaxComplete(20);

		clickTimeMaterialBillMaterialTab();

		waitForExtJSAjaxComplete(20);

		selectMutipleBOMLines("Consumed",productReference);

		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(isDisabledButtonInBOMTab("Quick Goods Return"),"QGR button is enabled when we select Stock Items that are in status consumed");

		waitForExtJSAjaxComplete(20);
		//C110685
		softAssert.assertTrue(isQGRButtonIsAddedNextToQGI(),"Quick Goods Return button is added next to Quick Goods Issue button");

		waitForExtJSAjaxComplete(20);

		clickOnQuickGoodsReturnButtonInBOM();

		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getReturnedByValueInQGR(), user,"person name is to be displayed to whom the workorder is assigned to.");

		softAssert.assertFalse(isButtonReadOnlyInQGRWindow("Returned Qty"),"Returned Qty field should be Editable");

		softAssert.assertFalse(isButtonReadOnlyInQGRWindow("Storage Location"),"Storage Location field should be Editable");

		softAssert.assertFalse(isButtonReadOnlyInQGRWindow("Lot"),"Lot field should be Editable");

		softAssert.assertFalse(isButtonReadOnlyInQGRWindow("Remark"),"Remark field should be Editable");

		softAssert.assertAll();

		Reporter.log("Successfully Quick Goods Return button fields are verified", true);

	}

	
	
	/**
	 * Test Case ID :C27155
	 * Author :ssa
	 */
	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void TestActionHistoryOfWorkorders() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+"C27155:Action History of Workorders", true);
		
		String testWorkOrder = "MultipleWOLinking";
		String possibleAction="Action1";
		String user=getUserLastNameOfLoggedInUser();
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("TestActionHistoryOfWorkorders");
		
		click(XPATH_WORKORDERS);
		
		waitForExtJSAjaxComplete(20);
		
		setDetailsTabCollapsedMode();
		
		Thread.sleep(1500);
		
		waitForExtJSAjaxComplete(20);
		
		filterGridWithoutUsingMcsElementModifiedForChrome("@realid", "mogrid", testWorkOrder,"Reference");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueExactMatch(driver, testWorkOrder);
		
		waitForExtJSAjaxComplete(20);
		
		clickTrackingTab(WORKORDER_PANEL_DETAIL);
		
		waitForExtJSAjaxComplete(20);
		
		clickTrackingHistoryTab();
		
		waitForExtJSAjaxComplete(20);
		
		List<String> preResult=getResultingStatusOfActionInHistoryTab("Result Status");
		
		waitForExtJSAjaxComplete(20);
		
		clickHistoryDetails();
		
		waitForExtJSAjaxComplete(20);
		
		String hearText=getHistoryDetailsHeader();
		    
		softAssert.assertTrue(hearText.matches("Action History for Work Order [0-9]+"), "Work order action history Header is displayed");  
		
		softAssert.assertTrue(isHistoryDetailsGdridEmpty(), "Action is not available in history details");
		 
		closeUsingToolBar(WO_DETAILSWINDOW_XPATH);
		 
		waitForExtJSAjaxComplete(20);
		
		clickAddAction();
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions(possibleAction);
		
		waitForExtJSAjaxComplete(20);
		
		String postResult=getResultingStatusOfAction();
		
		waitForExtJSAjaxComplete(20);
		
		//setActionNote(actionNote);
		
		waitForExtJSAjaxComplete(20);
		
		saveCloseAction(ADD_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertNotEquals(postResult,preResult.get(0),"Action window pre result status is not appears in history grid");
		
		preResult=getResultingStatusOfActionInHistoryTab("Result Status");
		
		softAssert.assertEquals(postResult,preResult.get(0),"Action window result status after saved is appears in history grid");
		
		clickHistoryDetails();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(isHistoryDetailsGdridEmpty(), "History details grid is not empty after action is added");
		
		softAssert.assertEquals(getLastActionStatusInHistoryDetailsWindow().get(0),postResult,"Action is added in history details grid");
		 
		closeUsingToolBar(WO_DETAILSWINDOW_XPATH);
		 
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();

		Reporter.log("WorkOrder actions are verified Successfully in history details window", true);	
	
	}
}



	




	