package test.generalweb.moveproject;





import java.util.Calendar;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.DropDown;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class MoveProjectCRUDTestSuite extends
		MoveProjectPageObject {

	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testMoveProjectCreateDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create/edit/delete web project: C89263</span><br>", true);

		Reporter.log("User creates purchasing product <br>", true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testMoveProjectCreateDelete");
		
		String testTemplate = "1preMovePrTempl";

		String testProjectCategory = "2prePrjCatMvRef";
		
		String nature = "Default Nature";
		
		String reference = "reference"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String referenceDuplicate = "apreProjectReference";
		
		String codeDuplicate = "apreProjectCode";
		
		String code = "code"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String testLocation = "slnmEnrgBuilding1";
		
		String testNature = "Default Nature";
		
		String testProjectCode = "testcode"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String testProjectReference = "testreference"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
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

		//expandAdministration();
		
		waitForExtJSAjaxComplete(5);

		expandSubMainMenu("Move Project");
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_MOVEPROJECTBACKOFFICE);

		waitForExtJSAjaxComplete(25);

		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(25);
		
		clickAddButton();
		
         waitForExtJSAjaxComplete(25);

		clickXPath("//div[contains(@class,'tplLabel') and contains(text(),'"+testTemplate+"')]");

		waitForExtJSAjaxComplete(5);
		
		setProjectCategory(testProjectCategory);
		
		waitForExtJSAjaxComplete(5);
		
		setProjectCode(testProjectCode);
		
		setProjectReference(testProjectReference);
		
		setLocation(testLocation);
		
		waitForExtJSAjaxComplete(5);
		
		setProjectNature(testNature);
		
		waitForExtJSAjaxComplete(5);
		
		clickCreate();
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertEquals(getNature(),testNature,"nature is prefilled");
		
		softAssert.assertEquals(getLocation(),testLocation,"location is prefilled");
		
		setReference(referenceDuplicate);
		
		setCode(code);
		
		setNature(nature);
		
		waitForExtJSAjaxComplete(5);
		
		save();
		
		waitForExtJSAjaxComplete(5);
		
			softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'bg-error')]"), "red error message is present");
		
		waitForExtJSAjaxComplete(5);
		
		setReference(reference);
		
		setCode(codeDuplicate);

		save();
		
		waitForExtJSAjaxComplete(5);
		
			softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'bg-error')]"), "red error message about code is present");

		waitForExtJSAjaxComplete(5);
		
		setCode("");
		
		save();
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, reference, "@class", "x-grid3"),"move project is not present in grid");
		
		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver,"x-resizable","code"),"red border - code");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[text()='Not all fields have a valid value']"),"not all fields value");
		
		setCode(code);
		
		setReference("");
		
		save();

//c15496; c15493		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[text()='Not all fields have a valid value']"),"not all fields value");
		
		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver,"x-resizable","reference"),"red border - reference");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, code, "@class", "x-grid3"),"move project code is not present in grid");
		
		setCode(code);
		
		setReference(reference);
		
//		C15494	
		
		setNature("");
		
		save();
		
			softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'bg-error')]"), "red error message about nature is present");

		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[text()='Not all fields have a valid value']"),"not all fields value");
		
		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver,"x-resizable","nature"),"red border - nature");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, code, "@class", "x-grid3"),"move project nature is not present in grid");

		waitForExtJSAjaxComplete(5);
		
		setNature(nature);
		
		waitForExtJSAjaxComplete(5);
		
		save();
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, reference, "@class", "x-grid3"),"move project is present in grid");
		
		close();
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, reference, "@class", "x-grid3"),"move project is present in grid");
		
		navigateToMainPage();
		
	//	expandAdministration();
		
		waitForExtJSAjaxComplete(25);

		expandSubMainMenu("Move Project");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MOVEPROJECTBACKOFFICE);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		 waitForMaskDisappear();
		 
		waitForExtJSAjaxComplete(5);
			
// C15500	
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);

		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValue(driver, referenceDuplicate);
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValue(driver, reference);
		
		waitForExtJSAjaxComplete(5);
		
		clickEditButton();
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		waitForExtJSAjaxComplete(5);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);

		clickRelatedDocumentsTab();
		
		waitForExtJSAjaxComplete(5);
		
//C30223 Add, edit and delete File to the Related Documents 		
		
		setUrl(url, urlDescription, urlRemark, type);
		
		setFile(file, fileDescription, fileRemark, type);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, type+"|"+urlDescription+"|"+urlRemark, "@class", "x-resizable-pinned"),"url can be created");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, file+"|"+type+"|"+fileDescription+"|"+fileRemark, "@class", "x-resizable-pinned"),"file can be created");
		
		editUrl(urlEd, urlDescription, urlDescriptionEd, urlRemarkEd, typeEd);
		
		editFile(fileEd, fileDescription, fileDescriptionEd, fileRemarkEd, typeEd);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, typeEd+"|"+urlDescriptionEd+"|"+urlRemarkEd, "@class", "x-resizable-pinned"),"url can be edited");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, fileEd+"|"+typeEd+"|"+fileDescriptionEd+"|"+fileRemarkEd, "@class", "x-resizable-pinned"),"file can be edited");
		
		deleteUrl(urlDescriptionEd);
		
		deleteUrl(fileDescriptionEd);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, urlDescriptionEd),"url can be deleted");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, urlDescription),"url can be deleted");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, fileDescriptionEd),"file can be deleted");
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertEquals(reference, getReference(),"reference before edit");
		
		softAssert.assertEquals(code, getCode(),"code before edit");
		
		softAssert.assertEquals(nature, getNature(),"nature before edit");
		
//c15498
		setReference(reference+"ed");
		
		setCode(code+"ed");
		
		setNature(nature);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		close();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference+"ed");
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
//c40287		
		setReference(reference+"close");
		
		close();
		
		try {clickOnDialogButton("Yes");}
		catch(Exception e){}
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton();

		waitForExtJSAjaxComplete(20);
		
		McsElement.getElementByXpath(driver,"//div[contains(@class,'tplLabel') and contains(text(),'"+testTemplate+"')]").click();
		
		waitForExtJSAjaxComplete(20);
		
		setProjectCategory(testProjectCategory);
		
		waitForExtJSAjaxComplete(20);
		
		setProjectCode(testProjectCode+"c1");
		
		setProjectReference(testProjectReference+"r1");
		
		setLocation(testLocation);
		
		waitForExtJSAjaxComplete(20);
		
		setProjectNature(testNature);
		
		waitForExtJSAjaxComplete(20);
		
		clickCreate();
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);

		close();
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton();

		waitForExtJSAjaxComplete(20);
		
		McsElement.getElementByXpath(driver,"//div[contains(@class,'tplLabel') and contains(text(),'"+testTemplate+"')]").click();
		
		waitForExtJSAjaxComplete(20);
		
		setProjectCategory(testProjectCategory);
		
		waitForExtJSAjaxComplete(20);
		
		setProjectCode(testProjectCode+"c2");
		
		setProjectReference(testProjectReference+"r2");
		
		setLocation(testLocation);
		
		waitForExtJSAjaxComplete(20);
		
		setProjectNature(testNature);
		
		waitForExtJSAjaxComplete(20);
		
		clickCreate();
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);

		close();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference+"ed");
		
		clickDeleteButton();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, reference, "@class", "x-grid3"),"move project was deleted");
		
		clickDeleteButton();
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, testProjectReference+"r1");
		
		Grid.checkRowInGriByTextValue(driver, testProjectReference+"r2");
		
//C15501		
		
		clickDeleteButton();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, testProjectReference+"r1", "@class", "x-grid3"),"move project 2 was deleted");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, testProjectReference+"r2", "@class", "x-grid3"),"move project 3 was deleted");
		
		softAssert.assertAll();
		
		Reporter.log("Move project was succesfully created and deleted", true);
	}

	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testMoveProjectTabs() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: test tabs on move web project: C74136 C74139 C89286 C90155 - 12.2</span><br>", true);

		Reporter.log("User uses tab functionality on mobe project <br>", true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testMoveProjectTabs");
		
		//String reference = "apreProjectReference";
		
		String description = "description";
		
		String category = "1preCostCtRef";
		
		String testTemplate = "1preMovePrTempl";

		String testProjectCategory = "2prePrjCatMvRef";
		
		
		String reference = "apreProjectRef"+ getCurrentDate().substring(getCurrentDate().length()-6);
		String code1 = "apreProj"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String testLocation = "slnmEnrgBuilding1";
		
		String testNature = "Default Nature";
		
		//expandAdministration();
		
		waitForExtJSAjaxComplete(5);

		expandSubMainMenu("Move Project");
		
		waitForExtJSAjaxComplete(5);
		
		waitAndClick(XPATH_MOVEPROJECTBACKOFFICE);
		
		waitForExtJSAjaxComplete(25);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(25);

		clickAddButton();

		waitForExtJSAjaxComplete(25);
		
		McsElement.getElementByXpath(driver,"//div[contains(@class,'tplLabel') and contains(text(),'"+testTemplate+"')]").click();
		
		waitForExtJSAjaxComplete(25);
		
		setProjectCategory(testProjectCategory);
		
		waitForExtJSAjaxComplete(5);
		
		setProjectCode(code1);
		
		waitForExtJSAjaxComplete(5);

		setProjectReference(reference);
		
		waitForExtJSAjaxComplete(5);

		setLocation(testLocation);
		
		waitForExtJSAjaxComplete(5);
		
		setProjectNature(testNature);
		
		waitForExtJSAjaxComplete(5);
		
		clickCreate();
		
		waitForExtJSAjaxComplete(5);
		
		save();
		
		waitForExtJSAjaxComplete(5);
		
		close();
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		waitForExtJSAjaxComplete(5);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(5);
		
		waitForExtJSAjaxComplete(5);
		
		clickMoveListTab();
		
		waitForExtJSAjaxComplete(5);
		
		waitForExtJSAjaxComplete(5);
		
//c30269		
		
		clickMoveListDetails();
		
		waitForExtJSAjaxComplete(15);
		
		softAssert.assertTrue(DropDown.isExtJsComboValuePresent(driver, driver.findElement(By.xpath("//span[contains(text(), 'Move List')]/ancestor::div[3]//label[text()='Status']/following-sibling::*//input")).getAttribute("id"), "Empty"),"empty value is present");
		
		softAssert.assertTrue(DropDown.isExtJsComboValuePresent(driver, driver.findElement(By.xpath("//span[contains(text(), 'Move List')]/ancestor::div[3]//label[text()='Status']/following-sibling::*//input")).getAttribute("id"), "Draft"),"draft value is present");
		
		//clickLookup("x-window",   driver.findElement(By.xpath("//div[contains(@class,'x-window')]//label[contains(text(),'Assigned To')]/../..//input")).getAttribute("name"));
		waitForExtJSAjaxComplete(5);
		
		clickLookupInMoveProjWindow(driver.findElement(By.xpath("//div[contains(@class,'x-window')]//label[contains(text(),'Assigned To')]/../..//input")).getAttribute("name"));
		
		waitForExtJSAjaxComplete(5);
		
		clickEmployeesTabInAssigneeLookup();
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "auto test right Last|auto test right First", "@realid", "employees"),"right employee is present");

		filterGrid("@realid","employees","MOVEPROJ","Last Name");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "MOVEPROJ|AQA", "@realid", "employees"),"selenium employee is present");
				
		clickContactsTabInAssigneeLookup();
		
		waitForExtJSAjaxComplete(5);
		
		setValueGridLookupWithFilters("@realid", "contacts", "1preContactLast", "Last Name");
		
		waitForExtJSAjaxComplete(5);
		
		setDueDateInMoveListDetailsTab("30-08-2017");
		
		setMoveListDescription(description);
		
		setMoveListJustification(description+"just");
		
		save();
		waitForExtJSAjaxComplete(10);
		waitForExtJSAjaxComplete(10);
		
		clickMoveListTab();
		waitForExtJSAjaxComplete(5);
		waitForExtJSAjaxComplete(5);
		
//c30269		
		
		clickMoveListDetails();
		
		waitForExtJSAjaxComplete(15);
		
		//softAssert.assertTrue(Grid.isRowInGridPresent(driver, "1|SELENIUM AQA", "@class", "x-resizable"),"row created found");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[text()='1']/../..//div[contains(text(),'"+"-"+Calendar.getInstance().get(Calendar.YEAR)+"')]" ),"created year ok" );
		
		softAssert.assertEquals(getDueDate(),"30-08-2017","due date was saved");
		
		softAssert.assertEquals(getMoveListDescription(),description,"description was saved");
		
		softAssert.assertEquals(getMoveListJustification(),description+"just","justification was saved");
		
		clickEmployeesTab();
		
	//	waitForExtJSAjaxComplete(5);
		
		clickAddEmployeeAsset();
		
		waitForExtJSAjaxComplete(3);
		
		setEmployee("SELENIUM");
		
		waitForExtJSAjaxComplete(3);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "SELENIUM AQA|AQA_DEPARTMENT", "@class", "x-grid3"),"employee was added");
		
		save();
		
		waitForExtJSAjaxComplete(5);
		
		clickMoveListTab();
		
		waitForExtJSAjaxComplete(5);
		
		waitForExtJSAjaxComplete(3);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "SELENIUM AQA|AQA_DEPARTMENT", "@class", "x-grid3"),"employee was added");
		
		clickAddDummyEmployeeAsset();
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "New", "@class", "x-grid3"),"employee was added by dummy");
		
		save();
		waitForExtJSAjaxComplete(20);
		
		clickMoveListTab();
		
		waitForExtJSAjaxComplete(5);
		
		waitForExtJSAjaxComplete(3);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "New|Employee not specified", "@class", "x-grid3"),"employee was added by dummy");
		
		clickAssetsTab();
		
		waitForExtJSAjaxComplete(3);
		
		clickAddEmployeeAsset();
		
		waitForExtJSAjaxComplete(3);
		
		setAsset("1preAsCtRef1preAsRef");
		
		waitForExtJSAjaxComplete(3);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "1preAsCtRef|1preAsTpRef", "@class", "x-grid3"),"asset was added");
		
		save();
		waitForExtJSAjaxComplete(25);
		
		clickMoveListTab();
		
		waitForExtJSAjaxComplete(5);
		
		waitForExtJSAjaxComplete(3);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "1preAsCtRef|1preAsTpRef", "@class", "x-grid3"),"asset was added");
		
		clickAddDummyEmployeeAsset();
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "New", "@class", "x-grid3"),"asset was added by dummy");
		
		save();
		waitForExtJSAjaxComplete(20);
		
		clickMoveListTab();
		
		waitForExtJSAjaxComplete(5);
		
		waitForExtJSAjaxComplete(3);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "New|0", "@class", "x-grid3"),"asset was added by dummy");
		
		close();
		waitForExtJSAjaxComplete(10);
		
		doubleClickRow(reference);
		waitForExtJSAjaxComplete(10);
		
		clickDescriptionTab();
		
		setDescription(description);
		
		save();
		waitForExtJSAjaxComplete(10);
		
		clickDescriptionTab();
		
		waitForExtJSAjaxComplete(3);
		
		softAssert.assertEquals(getDescription(),description,"description was set");
		
		clickFinancialTab();
		
		waitForExtJSAjaxComplete(3);
		
		setCostCategory(category);
		
		save();
		waitForExtJSAjaxComplete(10);
		
		clickFinancialTab();
		
		waitForExtJSAjaxComplete(3);
		
		softAssert.assertEquals(getCostCategory(),category,"category was set");
		
		softAssert.assertAll();
		
		Reporter.log("Employee and asset were added usind add button", true);
	}
	
	
	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testMoveProjectDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: delete move project button: c15501</span><br>", true);

		Reporter.log("User deletes move project <br>", true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testDeleteMoveProject");
		
		String testTemplate = "1preMovePrTempl";

		String testProjectCategory = "2prePrjCatMvRef";
		
		
		String reference1 = "referenc1"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String reference2 = "referenc2"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String reference3 = "referenc3"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String code1 = "code1"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String code2 = "code2"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String code3 = "code3"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String testLocation = "slnmEnrgBuilding1";
		
		String testNature = "Default Nature";
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(5);

		expandSubMainMenu("Move Project");
		
		waitForExtJSAjaxComplete(5);
		
		waitAndClick(XPATH_MOVEPROJECTBACKOFFICE);
		
		waitForExtJSAjaxComplete(5);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(5);
		
		clickAddButton();

		waitForExtJSAjaxComplete(5);
		
		McsElement.getElementByXpath(driver,"//div[contains(@class,'tplLabel') and contains(text(),'"+testTemplate+"')]").click();
		
		waitForExtJSAjaxComplete(5);
		
		setProjectCategory(testProjectCategory);
		
		waitForExtJSAjaxComplete(5);
		
		setProjectCode(code1);
		
		setProjectReference(reference1);
		
		setLocation(testLocation);
		
		waitForExtJSAjaxComplete(5);
		
		setProjectNature(testNature);
		
		waitForExtJSAjaxComplete(5);
		
		waitForExtJSAjaxComplete(15);
		
		clickCreate();
		
		waitForExtJSAjaxComplete(5);
		
		save();
		
		waitForExtJSAjaxComplete(5);
		
		close();
		
		waitForExtJSAjaxComplete(5);
		
		clickAddButton();

		waitForExtJSAjaxComplete(5);
		
		McsElement.getElementByXpath(driver,"//div[contains(@class,'tplLabel') and contains(text(),'"+testTemplate+"')]").click();
		
		waitForExtJSAjaxComplete(5);
		
		setProjectCategory(testProjectCategory);
		
		waitForExtJSAjaxComplete(5);
		
		setProjectCode(code2);
		
		setProjectReference(reference2);
		
		setLocation(testLocation);
		
		waitForExtJSAjaxComplete(5);
		
		setProjectNature(testNature);
		
		waitForExtJSAjaxComplete(5);
		
		clickCreate();
		
		waitForExtJSAjaxComplete(5);
		
		save();
		
		waitForExtJSAjaxComplete(5);
		
		close();
		
		waitForExtJSAjaxComplete(5);

		clickAddButton();

		waitForExtJSAjaxComplete(5);
		
		McsElement.getElementByXpath(driver,"//div[contains(@class,'tplLabel') and contains(text(),'"+testTemplate+"')]").click();
		
		waitForExtJSAjaxComplete(5);
		
		setProjectCategory(testProjectCategory);
		
		waitForExtJSAjaxComplete(5);
		
		setProjectCode(code3);
		
		setProjectReference(reference3);
		
		setLocation(testLocation);
		
		waitForExtJSAjaxComplete(5);
		
		setProjectNature(testNature);
		
		waitForExtJSAjaxComplete(5);
		
		clickCreate();
		
		waitForExtJSAjaxComplete(5);
		
		save();
		
		waitForExtJSAjaxComplete(5);
		
		close();
		
		waitForExtJSAjaxComplete(5);

		clickDeleteButton();
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference1);
		
		waitForExtJSAjaxComplete(5);
		
		clickDeleteButton();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(5);
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, reference1));
		
		Grid.checkRowInGriByTextValue(driver, reference2);
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValue(driver, reference3);
		
		clickDeleteButton();
		
		clickOnDialogButton("Yes");	
		
		waitForExtJSAjaxComplete(5);
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, reference2));
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, reference3));
		
		softAssert.assertAll();
		
		Reporter.log("Move project was succesfully deleted", true);
	}
	
	
}
