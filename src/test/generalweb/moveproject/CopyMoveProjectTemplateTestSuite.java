package test.generalweb.moveproject;





import org.testng.Reporter;
import org.testng.annotations.Test;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.McsElement;




public class CopyMoveProjectTemplateTestSuite extends
		CopyMoveProjectTemplatePageObject {

	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testCopyMoveProjectTemplateCreateDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: CopyMoveProjectTemplate: C91916, C89261</span><br>", true);

		Reporter.log("User CopyMoveProjectTemplate <br>", true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCopyMoveProjectTemplate");
		
		String testTemplate = "1preMovePrTempl";
		
		String newReference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		
		clickAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandMainMenuInAministration("Module Settings");
		
		waitForExtJSAjaxComplete(20);
		
		clickSubMenuItemInAdministration("Move Project Templates");
		
		waitForExtJSAjaxComplete(20);
		
		McsElement.getElementByXpath(driver,"//div[contains(@class,'x-tree-root-node')]//span[contains(text(),'"+testTemplate+"')]").click();
		
		waitForExtJSAjaxComplete(20);
		
		clickCopy();
		
		waitForExtJSAjaxComplete(20);
		
		setNewReferenceToCopiedMoveProject(newReference);
		
		waitForExtJSAjaxComplete(20);
		
		clickOkOnNewTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getReference(), newReference, "Reference on created item is correct.");
		
		McsElement.getElementByXpath(driver,"//div[contains(@class,'x-tree-root-node')]//span[contains(text(),'"+newReference+"')]").click();
		
		waitForExtJSAjaxComplete(20);
		
		clickDelete();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isElementAbsent("//div[contains(@class,'x-tree-root-node')]//span[contains(text(),'"+newReference+"')]"), "Item is still exist.");
		
		softAssert.assertAll();
		
		Reporter.log("Move project template was succesfully copied", true);
	}

	
	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testCopyMoveResrtictedProjectTemplate() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: CopyMoveRestrictedProjectTemplate: C89262</span><br>", true);

		Reporter.log("User CopyMoveRestrictedProjectTemplate <br>", true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCopyMoveRestrictedProjectTemplate");
		
		String testTemplateOther = "1preMovePrTemplRestrOther";
		
		String testTemplateCurrent = "1preMovePrTemplRestrCurrrent";
		
		String newReferenceRestr = "test auto restr" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		clickAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandMainMenuInAministration("Module Settings");
		
		waitForExtJSAjaxComplete(20);
		
		clickSubMenuItemInAdministration("Move Project Templates");
		
		waitForExtJSAjaxComplete(20);
		
		McsElement.getElementByXpath(driver,"//div[contains(@class,'x-tree-root-node')]//span[contains(text(),'"+testTemplateCurrent+"')]").click();
		
		waitForExtJSAjaxComplete(20);
		
		clickCopy();
		
		waitForExtJSAjaxComplete(20);
		
		setNewReferenceToCopiedMoveProject(newReferenceRestr);
		
		waitForExtJSAjaxComplete(20);
		
		clickOkOnNewTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getReference(), newReferenceRestr, "Reference on created item is correct.");
		
		McsElement.getElementByXpath(driver,"//div[contains(@class,'x-tree-root-node')]//span[contains(text(),'"+newReferenceRestr+"')]").click();
		
		waitForExtJSAjaxComplete(20);
		
		clickDelete();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isElementAbsent("//div[contains(@class,'x-tree-root-node')]//span[contains(text(),'"+newReferenceRestr+"')]"), "Item is still exist.");

		McsElement.getElementByXpath(driver,"//div[contains(@class,'x-tree-root-node')]//span[contains(text(),'"+testTemplateOther+"')]").click();
		
		waitForExtJSAjaxComplete(20);
		
		clickCopy();
		
		waitForExtJSAjaxComplete(20);
		
		setNewReferenceToCopiedMoveProject(newReferenceRestr+"not");
		
		waitForExtJSAjaxComplete(20);
		
		clickOkOnNewTemplate();
		
		waitForExtJSAjaxComplete(20);
		
//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not allowed')]"), "warning about restricted is ok");
		
//		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("Restricted move project template was succesfully copied", true);
	}

	
	
}
