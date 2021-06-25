package test.generalweb.service;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class ServiceRolesCRUDTestSuite extends 
		ServiceRolesPageObject {	

	/**
	 * Test case ID: C71611
	 * @author     : SPG
	 * 
	 */
	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)	
	public void testMasterDataOrganizationsServiceRolesAddEdit() throws IOException{		
		
		//Variables Initialization		
		String realId = "ServRoleGrid";
		String colName = "Code";
		String columnNameToEdit = "Description";
		
		Boolean buildAqa122 = driver.getCurrentUrl().contains("aqa122");		

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Add/Edit ServiceRoles: C71611</span><br>",
				true);

		Reporter.log("User add/edit ServiceRoles <br>",
				true);
		
		String reference = "Ref" + getCurrentDate().substring(getCurrentDate().length()-5); 
		String code = "Code" + getCurrentDate().substring(getCurrentDate().length()-5); code = code.replace(".", "1");		
		String desEdited = "DesModified" + getCurrentDate().substring(getCurrentDate().length()-5);
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testMasterDataOrganizationsServiceRolesAddEdit");
		
		//Click on Administration link	
		waitAndClick(XPATH_ADMINISTRATION);	
		
		waitForExtJSAjaxComplete(20);
		
		//Expand 'Master Data' tree structure in order to select Service Roles
		expandMasterData();
		
		waitForExtJSAjaxComplete(20);
		
		//Click on Service Roles link
		clickServiceRolesLink();		
		
		waitForExtJSAjaxComplete(20);
		
		//Click on Add button to create Service Role		
		clickAddButton("@realid",realId);
		
		waitForExtJSAjaxComplete(10);
		
		/**
		 * Enter data to create Service Role
		 * If portal is 122, system will focus Reference field first then it will go Code field
		 * If portal is 14, system will focus Code field first then it will go Reference field
		 * Due to this reason, created fields like setReference122, setCode122, setCode14 and setReference14
		 */		
		
		if (buildAqa122 == true) {			
			setReference122(reference);
			
			waitForExtJSAjaxComplete(5);
			
			setCode122(code);
			
			waitForExtJSAjaxComplete(5);			
		}		
		else{			
			setCode14(code);
			
			waitForExtJSAjaxComplete(5);
			
			setReference14(reference);
			
			waitForExtJSAjaxComplete(5);
		}		
		
		//Click on Save button to save added Service Role
		saveServiceRoles();
		
		waitForExtJSAjaxComplete(20);
		
		if(isSuccessbarMsgPresent())
		{Assert.assertEquals(getInfoBarMsgOfWindow("@class", ADMINISTRATION_CLASS),"The Service Role(s) have been added successfully");}
		else{		
		saveServiceRoles();	
		
		waitForExtJSAjaxComplete(10);				
			
		softAssert.assertTrue(getInfoBarMsgOfWindow("@class", ADMINISTRATION_CLASS).contains("The Service Role(s) have been added successfully"),"The Service Role(s) have been added successfully");}	
		
		waitForExtJSAjaxComplete(10);		
		
		//Edit description field in created Service Role
		editAddedServiceRoleRecord(code, colName, columnNameToEdit, desEdited);
		
		waitForExtJSAjaxComplete(20);
		
		saveServiceRoles();
		
		waitForExtJSAjaxComplete(20);
		
		try {saveServiceRoles();}
		catch(Exception e ){}
		
		waitForExtJSAjaxComplete(10);		
		
		Reporter.log("Service Role was succesfully edited", true);
		
		softAssert.assertAll();
		
		Reporter.log("User can create Service Role and edit created Service Role record", true);
	}
	
	/**
	 * Test case ID: C71611
	 * @author     : SPG
	 * 
	 */
	
	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)	
	public void testMasterDataOrganizationsServiceRolesDelete() throws IOException{		
		
		String entity = "Service Roles";
		String realId = "ServRoleGrid";
		
		Boolean buildAqa122 = driver.getCurrentUrl().contains("aqa122");	

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete ServiceRoles: C71611</span><br>",
				true);

		Reporter.log("User deletes ServiceRoles <br>",
				true);
		
		String reference = "RefDel" + getCurrentDate().substring(getCurrentDate().length()-5); 
		String code = "CodeDel" + getCurrentDate().substring(getCurrentDate().length()-5); code = code.replace(".", "1");		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("MasterDataOrganizationsServiceRoles");

		waitAndClick(XPATH_ADMINISTRATION);	
		
		waitForExtJSAjaxComplete(20);
		
		expandMasterData();	
		
		waitForExtJSAjaxComplete(20);
		
		clickServiceRolesLink();		
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton("@realid",realId);
		
		waitForExtJSAjaxComplete(10);
		
		if (buildAqa122 == true) {			
			setReference122(reference);
			
			waitForExtJSAjaxComplete(5);
			
			setCode122(code);
			
			waitForExtJSAjaxComplete(5);			
		}		
		else{				
			setCode14(code);
			
			waitForExtJSAjaxComplete(5);
			
			setReference14(reference);
			
			waitForExtJSAjaxComplete(5);
		}
		
		saveServiceRoles();
		
		waitForExtJSAjaxComplete(20);
		
		try {saveServiceRoles();}
		catch(Exception e){}
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, code);
		
		waitForExtJSAjaxComplete(10);		
		
		//Click on Delete button to delete selected Service Role
		clickDeleteButton("@realid",realId);
				
		waitForExtJSAjaxComplete(10);		
		
		softAssert.assertTrue(getInfoBarMsgOfWindow("@class", ADMINISTRATION_CLASS).contains("The Service Role has been deleted successfully."),"The Service Role has been deleted successfully.");
		
		waitForExtJSAjaxComplete(10);
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, code), code+" code after delete is present");		
		
		Reporter.log("Organizations " + entity + " was succesfully deleted", true);	
		
		softAssert.assertAll();	
		
		Reporter.log("User can create Service Role and delete created Service Role record", true);
	}
}



