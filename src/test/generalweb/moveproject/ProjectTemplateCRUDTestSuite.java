package test.generalweb.moveproject;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.internal.seleniumemulation.SelectFrame;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class ProjectTemplateCRUDTestSuite extends
		ProjectTemplatesPageObject {

	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testProjectTemplateCreateEditDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create Edit Project Template: C74069; C74070 </span><br>", true);

		Reporter.log("User create and edit project template <br>", true);
		
		String callTemplateReference = "my auto"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String label = "label"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String description = "description"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testProjectTemplateCreateEditDelete");

		clickAdministration();
		

		waitForExtJSAjaxComplete(20);
		
		expandModuleSettings();

		waitForExtJSAjaxComplete(20);
		
		clickProjectTemplate();

		waitForExtJSAjaxComplete(20);
		
		clickAddTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(callTemplateReference);
		
		unCheckActive();
		
		clickFieldsTab();
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Project Category");
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Project Code");
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Reference");
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Location");
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Nature");
		
		waitForExtJSAjaxComplete(20);
		
		
		saveCallTemplate();
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", "admsettings-modulesettings","span", "text()",
				callTemplateReference, true, true).click();
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check reference and activ state before edit", true);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(getReference().equals(callTemplateReference),"call template reference before edit is wrong");
		
		Assert.assertEquals(getActivState(), false, "active state before edit is wrong");
		
		String callTemplateReferenceEdited = "my auto ed"+getCurrentDate().substring(getCurrentDate().length()-6);
		
		setReference(callTemplateReferenceEdited);
		
		setLabelDescription("English", label, description);
		
		clickActiv();
		
		saveCallTemplate();
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", "admsettings-modulesettings","span", "text()",
				callTemplateReferenceEdited, true, true).click();

		Reporter.log("Check reference and activ state after edit", true);
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(getReference().equals(callTemplateReferenceEdited),"call template reference after edit is wrong");
		
		Assert.assertEquals(getActivState(), true, "active state after edit is wrong");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Move Project");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWMOVE);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'move')]//div[contains(text(),'"+label+"')]/..//div[contains(text(),'"+description+"')]").click();

		waitForExtJSAjaxComplete(20);
		
		clickAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		expandModuleSettings();
		
		clickProjectTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		
		clickAddTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
//C15496	Reference should be unique and mandatory 
		
		setReference(callTemplateReferenceEdited);
		
		clickFieldsTab();
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Project Category");
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Project Code");
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Reference");
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Location");
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Nature");
		
		waitForExtJSAjaxComplete(20);
		
		saveCallTemplate();
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		setReference("");
		
		saveCallTemplate();
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", "admsettings-modulesettings","span", "text()",
				callTemplateReferenceEdited, true, true).click();

		waitForExtJSAjaxComplete(20);
		
		clickDeleteTemplate();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			Assert.assertTrue(
					driver.findElements(
							By.xpath("//span[contains(@class,'x-panel') and contains(text(),'Templates')]/../..//span[text()='"+ callTemplateReferenceEdited+"']")).isEmpty(),
					"Project template is present after delete");

		} finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);
		}
		
		softAssert.assertAll();
		
		Reporter.log("Project template was succesfully created/edited/deleted", true);
	}
		
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testProjectTemplateCreate2() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create Project Template2: C89270 </span><br>", true);

		Reporter.log("User create  project template2 <br>", true);
		
		String callTemplateReference = "my auto"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String question = "question23";
		
		String defaultQuestion = "answer23";
		
//		String label = "label"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
//		String description = "description"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testProjectTemplateCreate2");

		clickAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		expandModuleSettings();
		
		waitForExtJSAjaxComplete(20);
		
		clickProjectTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		clickAddTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(callTemplateReference);
		
		clickActiv();
		
		clickFieldsTab();
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Project Category");
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Project Code");
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Reference");
		
		waitForExtJSAjaxComplete(20);
		
//		checkVisible("Location");
		
//		waitForExtJSAjaxComplete(20);
		
		checkVisible("Nature");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddAdditionalQuestion();
		
		waitForExtJSAjaxComplete(20);
		
		setAddAdditionalQuestion(question);
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		setDefaultValue(question, defaultQuestion);
		
		saveCallTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, defaultQuestion+"|"+question, "@class", "x-panel"));
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Move Project");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWMOVE);
		
		waitForExtJSAjaxComplete(20);
		
		 waitForMaskDisappear();
		
		Thread.sleep(5000);
		
		waitForExtJSAjaxComplete(20);
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'move')]//div[contains(text(),'"+callTemplateReference+"')]").click();

		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(McsElement.getElementByXpath(driver,"//label[contains(text(),'"+question+"')]/..//input").getAttribute("value"),defaultQuestion,"default question ok");
		
		softAssert.assertAll();
		
		Reporter.log("Project template with additional question was created", true);
	}


	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testProjectTemplateFieldLabel() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Project Template: c22807 </span><br>", true);

		Reporter.log("User create and edit project template field label<br>", true);
		
		String callTemplateReference = "my auto"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String projectCategoryLabel = "prCtlabel"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String projectCodeLabel = "prCdlabel"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String projectReferenceLabel = "prReflabel"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		HashMap<String, String> fieldLanguage = new HashMap<>();
		
		fieldLanguage.put("Project Category", projectCategoryLabel);
		
		fieldLanguage.put("Project Code", projectCodeLabel);
		
		fieldLanguage.put("Reference", projectReferenceLabel);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testProjectTemplateFieldLabel");

		clickAdministration();

		waitForExtJSAjaxComplete(20);
		
		expandModuleSettings();

		waitForExtJSAjaxComplete(20);
		
		clickProjectTemplate();

		waitForExtJSAjaxComplete(20);
		
		clickAddTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(callTemplateReference);
		
		clickActiv();
		
		clickFieldsTab();
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Project Category");
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Project Code");
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Reference");
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Nature");
		
		waitForExtJSAjaxComplete(20);
		
		clickFieldLabelTab();
		
		waitForExtJSAjaxComplete(20);
		
		setEnglishFieldLabel(fieldLanguage);
		
		waitForExtJSAjaxComplete(20);
		
		saveCallTemplate();
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Move Project");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWMOVE);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'move')]//div[contains(text(),'"+callTemplateReference+"')]").click();

		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'panel')]//label[contains(text(),'"+projectCategoryLabel+"')]/..//input[@name='PRJ_CATEGORY']"),"project category label");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'panel')]//label[contains(text(),'"+projectCodeLabel+"')]/..//input[@name='PRJ_CODE']"),"project code label");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'panel')]//label[contains(text(),'"+projectReferenceLabel+"')]/..//input[@name='PRJ_REFERENCE']"),"project reference label");
		
		clickAdministration();
		
		expandModuleSettings();
		
		clickProjectTemplate();
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", "admsettings-modulesettings","span", "text()",
				callTemplateReference, true, true).click();

		waitForExtJSAjaxComplete(20);
		
		clickDeleteTemplate();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("Project template labels were succesfully created", true);
	}


	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testProjectTemplateLocation() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Project Template Location: C89275 </span><br>", true);

		Reporter.log("User restrict project template location<br>", true);
		
		String callTemplateReference = "my auto"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String location = "slnmEnrgBuilding1";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testProjectTemplateFieldLabel");

		clickAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		expandModuleSettings();
		
		waitForExtJSAjaxComplete(20);
		
		clickProjectTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		clickAddTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(callTemplateReference);
		
		clickActiv();
		
		clickFieldsTab();
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Project Category");
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Project Code");
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Reference");
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Nature");
		
		waitForExtJSAjaxComplete(20);
		
		checkVisible("Location");

		waitForExtJSAjaxComplete(20);
		
		clickResrictionsTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickAddRestriction();
				
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		//some strange not reproducible manually error at BE side with IE10
		/*try {McsElement.getElementByXpath(driver, "//div[contains(@class,'x-resizable')]//span[contains(text(),'Buildings')]").click();}
		catch(Exception e ){clickOnDialogButton("OK");
		waitForExtJSAjaxComplete(20);
		//McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-tool-close')])[last()]").click();
		McsElement.getElementByXpath(driver, "(//div[contains(@class,'x-tool-close')])").click();
		waitForExtJSAjaxComplete(20);
		clickAddRestriction();
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-resizable')]//span[contains(text(),'Buildings')]").click();
		}*/
		
		//setValueGridLookupWithFilters(location, "Reference");
		//new location look up
		setValueGridLookupWithFiltersNewUI(location, "Reference");
		waitForExtJSAjaxComplete(10);
		
		saveCallTemplate();
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Move Project");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWMOVE);
		
		waitForExtJSAjaxComplete(20);
		
		 waitForMaskDisappear();
			
			Thread.sleep(5000);
			
		waitForExtJSAjaxComplete(20);
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'move')]//div[contains(text(),'"+callTemplateReference+"')]").click();

		waitForExtJSAjaxComplete(20);
		
		//clickLookup("x-panel-body", "PRJ_LOCATION");
		//new location window change
		clickLookupNewUI("x-panel-body", "PRJ_LOCATION");
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(setValueGridLookupWithFiltersNewUIWithValidation("@class", "x6-window-default-closable", "slnmEnrgBuilding3", "Reference"),"slnmEnrgBuilding3 is not present");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(setValueGridLookupWithFiltersNewUIWithValidation("@class", "x6-window-default-closable", "slnmEnrgBuilding4", "Reference"),"slnmEnrgBuilding4 is not present");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(setValueGridLookupWithFiltersNewUIWithValidation("@class", "x6-window-default-closable", location, "Reference"),location+" is  present");
				
		softAssert.assertAll();
		
		Reporter.log("Location to template was restricted", true);
	}

	/**
	 * Testcase ID : C111745, C111746
	 * Author      : MMA    
	 * @throws IOException
	 */
	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testRestrictMoveProjectTempByGrpRightAngAccountRight() throws IOException{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C111745: Access Move Project templates that are restricted by account right </span><br>"
				+ "C111746: Access Move Project templates that are restricted by Group right ", true);

		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testRestrictMoveProjectTempByGrpRightAngAccountRight");

		String TempRef = "TempRestByGrp"+ getCurrentDate().substring(getCurrentDate().length()-6);
		String TempRef1 = "TempRestByAcc"+ getCurrentDate().substring(getCurrentDate().length()-6);
		String grpRef = "SLNM_AUTO_GROUP";
		String userID = "MMA";
		String userName = "Manasa Mandlem";

		//C111746
		clickAdministration();
		waitForExtJSAjaxComplete(20);

		expandModuleSettings();
		waitForExtJSAjaxComplete(20);

		clickProjectTemplate();
		waitForExtJSAjaxComplete(20);

		clickAddTemplate();
		waitForExtJSAjaxComplete(20);

		clickGeneralTab();
		waitForExtJSAjaxComplete(20);

		setReference(TempRef);
		clickActiv();
		clickFieldsTab();
		waitForExtJSAjaxComplete(20);

		checkVisible("Project Category");
		waitForExtJSAjaxComplete(20);

		checkVisible("Project Code");
		waitForExtJSAjaxComplete(20);

		checkVisible("Reference");
		waitForExtJSAjaxComplete(20);

		checkVisible("Nature");
		waitForExtJSAjaxComplete(20);

		checkVisible("Location");
		waitForExtJSAjaxComplete(20);

		clickSecurityTab();
		waitForExtJSAjaxComplete(20);

		clickAddGroup();
		waitForExtJSAjaxComplete(20);

		setValueGridLookupWithFilters(grpRef, "Name");
		waitForExtJSAjaxComplete(20);

		saveCallTemplate();
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(isRowInSecurityTabPresent(grpRef),"Selected group is added to the table grid");
		softAssert.assertTrue(getCheckBoxFieldStatus("Restricted"),"'Restricted' checkbox is checked");
		waitForExtJSAjaxComplete(20);

		//C111745
		clickAddTemplate();
		waitForExtJSAjaxComplete(20);

		clickGeneralTab();
		waitForExtJSAjaxComplete(20);

		setReference(TempRef1);
		clickActiv();
		clickFieldsTab();
		waitForExtJSAjaxComplete(20);

		checkVisible("Project Category");
		waitForExtJSAjaxComplete(5);

		checkVisible("Project Code");
		waitForExtJSAjaxComplete(5);

		checkVisible("Reference");
		waitForExtJSAjaxComplete(5);

		checkVisible("Nature");
		waitForExtJSAjaxComplete(5);

		checkVisible("Location");
		waitForExtJSAjaxComplete(5);

		clickSecurityTab();
		waitForExtJSAjaxComplete(5);

		clickAddAccount();
		waitForExtJSAjaxComplete(20);

		setValueGridLookupWithFilters(userID, "ID");
		waitForExtJSAjaxComplete(20);

		saveCallTemplate();
		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(isRowInSecurityTabPresent(userName),"Selected account is added to the table grid");
		softAssert.assertTrue(getCheckBoxFieldStatus("Restricted"),"'Restricted' checkbox is checked");

		closeModule("Administration");
		waitForExtJSAjaxComplete(20);

		expandAdministration();
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Move Project");
		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWMOVE);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		MoveProjectPageObject mpgObj = new MoveProjectPageObject();
		softAssert.assertFalse(mpgObj.isTemplatePresent(TempRef),"It is not possible to access restricted Move Project template for current user.");
		softAssert.assertFalse(mpgObj.isTemplatePresent(TempRef1),"It is not possible to access restricted Move Project template for current user.");

		softAssert.assertAll();
		Reporter.log("Move project template is restricted with account and account group", true);
	}
	
}
