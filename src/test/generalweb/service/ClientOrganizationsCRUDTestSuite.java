package test.generalweb.service;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;


public class ClientOrganizationsCRUDTestSuite extends ClientOrganizationsPageObject
		 {
	
	/**
	 * Testcase ID		: C29473
	 * Author			: ssa
	 * */
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testModifyingNotificationTemplates() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
						+ "Test: Modifying Notification Templates in the C.O.: C29473</span><br>",true);

		Reporter.log("User Modifying Notification Templates <br>", true);
		
		String clientOrg = "DEFAULT ( DEFAULT )";
		String TemplateRef = "ssa_Temp";
		String lookUpWindowCol = "Reference";
		
		
        SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testModifyingNotificationTemplates");
		
        waitAndClick(XPATH_ADMINISTRATION);	
		
		waitForExtJSAjaxComplete(20);
		
		expandMasterData();
		
		waitForExtJSAjaxComplete(20);
		
		clickClientOrganizations();
		
		waitForExtJSAjaxComplete(20);
		
		selectClientOrganization(clientOrg);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnNotificationTab();
		
		waitForExtJSAjaxComplete(20);
		
		addNotificationTemplates(TemplateRef, lookUpWindowCol);
		
		waitForExtJSAjaxComplete(20);
		
		clickSaveChangesButton();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, TemplateRef, "@class",XPATH_ADMINISTRATION_CLASS_NAME),"Notification template is added");		
  
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, XPATH_ADMINISTRATION_CLASS_NAME, TemplateRef);
		
		clickRemoveButton();
		
        waitForExtJSAjaxComplete(10);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		clickSaveChangesButton();

		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, TemplateRef,"@class",XPATH_ADMINISTRATION_CLASS_NAME),TemplateRef+ "Notification template after remove is present");
        
		softAssert.assertAll();
		
		Reporter.log("Notification templates are successfully added and removed from client organization.", true);
	
	}
	

}