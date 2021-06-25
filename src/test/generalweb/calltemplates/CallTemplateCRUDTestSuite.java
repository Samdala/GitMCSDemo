package test.generalweb.calltemplates;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import test.framework.webelement.Tree;

public class CallTemplateCRUDTestSuite extends
		CallTemplatesPageObject {

	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testCallTemplateCreateEditDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create Edit Call Template: C71614 </span><br>", true);

		Reporter.log("User create and edit call template <br>", true);
		
		String callTemplateReference = "my auto"+ getCurrentDate().substring(getCurrentDate().length()-6);

		clickAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandModuleSettings();

		waitForExtJSAjaxComplete(20);

		clickCallTemplate();

		waitForExtJSAjaxComplete(20);

		clickAddTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(callTemplateReference);
		
		//TODO : By default it is active
		//clickActiv();
		
		saveCallTemplate();
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXPanelId("Call Templates"),"span", "text()",
				callTemplateReference, true, true).click();
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check reference and activ state before edit", true);
		
		Assert.assertTrue(getReference().equals(callTemplateReference),"call template reference before edit is wrong");
		
		Assert.assertEquals(getActivState(), true, "active state before edit is wrong");
		
		String callTemplateReferenceEdited = "my auto ed"+getCurrentDate().substring(getCurrentDate().length()-6);
		
		setReference(callTemplateReferenceEdited);
		
		clickActiv();
		
		saveCallTemplate();
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXPanelId("Call Templates"),"span", "text()",
				callTemplateReferenceEdited, true, true).click();

		Reporter.log("Check reference and activ state after edit", true);
		
		Assert.assertTrue(getReference().equals(callTemplateReferenceEdited),"call template reference after edit is wrong");
		
		Assert.assertEquals(getActivState(), false, "active state after edit is wrong");
		
//C15390 & C15389
		clickCopyTemplate();
		
		waitForExtJSAjaxComplete(25);
		
		setCopyReference(callTemplateReference+"copied");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXPanelId("Call Templates"),"span", "text()",
				callTemplateReference+"copied", true, true).click();
		
		waitForExtJSAjaxComplete(20);
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXPanelId("Call Templates"),"span", "text()",
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
							By.xpath("//span[contains(@class,'x-panel') and contains(text(),'Call Templates')]/../..//span[text()='"+ callTemplateReferenceEdited+"']")).isEmpty(),
					"Call template is present after delete");

		} finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);
		}
		
		
		Reporter.log("Call template was succesfully created/edited/deleted", true);
	}
	
	
	/**
	 * Testcase ID	: 	C74067,C74061,C73980
	 * Author		:	ssa
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testCallTemplateToGroupCreateCreateDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C29442 Add and delete Call Template to Template Groups Create Edit Call Template: c29442 </span><br>", true);

		Reporter.log("User create and delete call template to group <br>", true);
		
		String callTemplateReference = "2preCallTemplate";
		
		String group1 = "3preTemplGr";
		
		String group2 = "4preTemplGr";

		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCallTemplateToGroupCreateCreateDelete");
		
		clickAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		expandModuleSettings();
		
		waitForExtJSAjaxComplete(20);
		
		clickCallTemplate();
		
		Tree.checkNodeInTreeByTextValue(driver,callTemplateReference);
		
		waitForExtJSAjaxComplete(25);
		
		clickTemplateGroupsTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickAddOnTemplateGroupsTab();
		
		waitForExtJSAjaxComplete(20);
		
		expandAllNodeExtJsTree("@class","x-mcs-lookup-view");
		
		waitForExtJSAjaxComplete(20);
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-mcs-lookup-view')]//span[contains(text(),'"+group1+"')]").click();
		
		waitForExtJSAjaxComplete(20);
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-mcs-lookup-view')]//button[contains(@style,'add.png')]").click();
		
		waitForExtJSAjaxComplete(20);
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-mcs-lookup-view')]//span[contains(text(),'"+group2+"')]").click();
		
		waitForExtJSAjaxComplete(20);
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-mcs-lookup-view')]//button[contains(@style,'add.png')]").click();
		
		waitForExtJSAjaxComplete(20);
		
		clickOkXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		saveCallTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		driver.findElement(By.xpath("((//span[text()='Call Templates'])[last()]/../..)//div[contains(@class,'x-tool-plus')]")).click();
        
		Reporter.log("expand templates");
        
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//span[text()='"+group1+"']/../../..//span[text()='"+callTemplateReference+"']"),"template is added to group");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//span[text()='"+group2+"']/../../..//span[text()='"+callTemplateReference+"']"),"template is added to group");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWCALL);
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		driver.findElement(By.xpath("//div[contains(@class,'helpdesk-newcall')]//div[contains(@class,'x-tool-plus')]")).click();

		expandAllNodeExtJsTree("@class", "helpdesk-newcall");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[text()='"+group2+"']/../../../../..//div[text()='"+callTemplateReference+"']"),"template is added to group call");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[text()='"+group1+"']/../../../../..//div[text()='"+callTemplateReference+"']"),"template is added to group");
		
		navigateToMainPage();
		
		clickAdministration();
		
		expandModuleSettings();
		
		clickCallTemplate();
		
		Tree.checkNodeInTreeByTextValue(driver,callTemplateReference);
		
		waitForExtJSAjaxComplete(25);
		
		clickTemplateGroupsTab();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, group2);
		
		waitForExtJSAjaxComplete(20);
		
		clickRemoveOnTemplateGroupsTab();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, group1);
		
		clickRemoveOnTemplateGroupsTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		saveCallTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		expandAllNodeExtJsTree("@id", McsElement.getElementByXpath(driver, "(//span[text()='Call Templates'])[last()]/../..").getAttribute("id"));
				
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//span[text()='"+group1+"']/../../..//span[text()='"+callTemplateReference+"']"),"template is added to group");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//span[text()='"+group2+"']/../../..//span[text()='"+callTemplateReference+"']"),"template is added to group");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWCALL);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		expandAllNodeExtJsTree("@class", "helpdesk-newcall");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//div[text()='"+group2+"']/../../../../..//div[text()='"+callTemplateReference+"']"),"template is added to group call");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//div[text()='"+group1+"']/../../../../..//div[text()='"+callTemplateReference+"']"),"template is added to group");

		softAssert.assertAll();
		
		Reporter.log("Call template was succesfully created/deleted to group", true);
	}
	

	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testCallTemplateLocationRestriction() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test': C29398 Add the location restriction to a Call Template  </span><br>", true);
		
		String callTemplateReference = "my auto"+ getCurrentDate().substring(getCurrentDate().length()-6);

		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCallTemplateLocationRestriction");
		
		clickAdministration();
		
		expandModuleSettings();
		waitForExtJSAjaxComplete(20);
		
		clickCallTemplate();
		
		clickAddTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(callTemplateReference);
		
		clickActiv();
		
		clickRestrictionTab();
		
		clickLocationsTab();
		
		clickAddLocation();
		waitForExtJSAjaxComplete(20);
		
		setLocation("slnmEnrgArea1");
		clickAddLocation();
		waitForExtJSAjaxComplete(10);
		
		setLocation("slnmEnrgBuilding1");
		clickAddLocation();
		waitForExtJSAjaxComplete(10);
		
		setLocation("slnmEnrgSite1");
		//new location look up
		
		/*clickBuildingsTabLocationForm();
		
		waitForExtJSAjaxComplete(20);
		
		expandDetailsXwindow();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "slnmEnrgBuilding1");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//a[text()='slnmEnrgBuilding1']"),"link for location details");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(text(),'Type: Building')]"),"type for location details");
		
		Grid.checkRowInGriByTextValueAndClick(driver, "slnmEnrgBuilding2");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//a[text()='slnmEnrgBuilding2']"),"link for location details");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(text(),'Type: Building') and contains(.,'Code: slnmEnrgBuilding2')]"),"type for location details");
		
		clickSitesTabLocationForm();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "slnmEnrgSite1");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//a[text()='slnmEnrgSite1']"),"link for location detail site");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(text(),'Type: Site') and contains(.,'Code: slnmEnrgSite1')]"),"type for location detail site");
		
		clickOtherTabLocationForm();
		
		waitForExtJSAjaxComplete(20);
		
		expandAllNodeExtJsTree("@class","x-mcs-lookup-view");
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-mcs-lookup-view')]//span[contains(text(),'slnmEnrgArea1 (slnmEnrgArea)')]").click();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//a[text()='slnmEnrgArea1']"),"link for location detail site");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(text(),'Type: Area') and contains(.,'Code: slnmEnrgArea1') and contains(.,'Description: slnmEnrgArea')]"),"type for location detail area");
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-mcs-lookup-view')]//button[contains(@style,'add.png')]").click();
		
		waitForExtJSAjaxComplete(20);
		
		clickOkXwindow();*/
		
		waitForExtJSAjaxComplete(20);
		
		saveCallTemplate();

		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "slnmEnrgArea1");
		
		clickRemoveLocation();
		
		saveCallTemplate();
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, "slnmEnrgArea1"),"location can be deleted");
		
		softAssert.assertAll();	
			
		Reporter.log("Location was added and removed to template", true);
	}

	
	
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testCallTemplateGroupCreateEditDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create Edit Call Group Template: C73979; c15388 </span><br>", true);

		Reporter.log("User create-edit-delete call group template <br>", true);
		
		String callTemplateReference = "my auto gr"+ getCurrentDate().substring(getCurrentDate().length()-6);

		clickAdministration();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		expandModuleSettings();
		
		waitForExtJSAjaxComplete(20);

		clickCallTemplate();
		
		clickAddGroupTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		setReferenceGroup(callTemplateReference);
		
		saveCallTemplateGroup();
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXPanelId("Call Templates"),"span", "text()",
				callTemplateReference, true, true).click();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(getReferenceGroup().equals(callTemplateReference),"call template group reference before edit is wrong");
		
		String callTemplateReferenceEdited = "my auto gr ed"+getCurrentDate().substring(getCurrentDate().length()-6);
		
		setReferenceGroup(callTemplateReferenceEdited);
		
		saveCallTemplateGroup();
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXPanelId("Call Templates"),"span", "text()",
				callTemplateReferenceEdited, true, true).click();

		Assert.assertTrue(getReferenceGroup().equals(callTemplateReferenceEdited),"call template group reference after edit is wrong");
		waitForExtJSAjaxComplete(20);
		clickAddTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(callTemplateReference+"temp");
		
		saveCallTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXPanelId("Call Templates"),"span", "text()",
				callTemplateReference+"temp", true, true).click();
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXPanelId("Call Templates"),"span", "text()",
				callTemplateReferenceEdited, true, true).click();
		
		waitForExtJSAjaxComplete(20);
				
		clickDeleteGroupTemplate();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			Assert.assertTrue(
					driver.findElements(
							By.xpath("//span[contains(@class,'x-panel') and contains(text(),'Call Templates')]/../..//span[text()='"+ callTemplateReferenceEdited+"']")).isEmpty(),
					"Call template group is present after delete");

		} finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);
		}
		
		
		Reporter.log("Call template group was succesfully created/edited/deleted", true);
	}


}
