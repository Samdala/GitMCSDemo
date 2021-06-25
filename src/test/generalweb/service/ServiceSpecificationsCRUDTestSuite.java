package test.generalweb.service;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class ServiceSpecificationsCRUDTestSuite extends
	ServiceSpecificationsPageObject {

	/**
	 * Testcase ID		: C71606
	 * Author			: Intellias
	 * */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testserviceSpecificationsCreateEdit() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C71606: Create Edit Service Specifications: 140: C29492, 122: C14677</span><br>", true);

		Reporter.log("User create and edit Service Specifications <br>", true);
		
		String serviceSpecificationsReference = "my auto"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String nature = "Default Nature";

		String serviceSpecificationsReferenceEdited = serviceSpecificationsReference + "_ed";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("Service Specifications");
		
		clickAdministration();
		
		expandMasterData();
		
		clickServiceSpecifications();
		
		clickAddServiceSpecifications();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(serviceSpecificationsReference);
		
		softAssert.assertEquals(getReference(), serviceSpecificationsReference, "Reference before edit is correct." );
		
		setCode(serviceSpecificationsReference);
		
		setNature(nature);
		
		saveServiceSpecifications();
		
		waitForExtJSAjaxComplete(20);
		
		clickTab("Dispatching Instructions");
		
		waitForExtJSAjaxComplete(20);
		
		clickTab("Validity");
		
		waitForExtJSAjaxComplete(20);
		
		clickTab("Contacts");
		
		waitForExtJSAjaxComplete(20);
		
		closeServiceSpecifications();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridPresent(driver, serviceSpecificationsReference, "@class", "x-grid3-body"), "New items is present in grid");
		
		Grid.checkRowInGriByTextValue(driver, serviceSpecificationsReference);
		
		clickEditServiceSpecifications();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getReference(), serviceSpecificationsReference, "Reference before edit is correct." );
		
		softAssert.assertEquals(getCode(), serviceSpecificationsReference, "Code before edit is correct." );
		
		softAssert.assertEquals(getNature(), nature, "Nature before edit is correct." );
		
		setReference(serviceSpecificationsReferenceEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setCode(serviceSpecificationsReferenceEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setNature(nature);
		
		waitForExtJSAjaxComplete(20);
		
		saveServiceSpecifications();
		
		waitForExtJSAjaxComplete(20);
		
		closeServiceSpecifications();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridPresent(driver, serviceSpecificationsReferenceEdited, "@class", "x-grid3-body"), "New items is present in grid");
		
		Grid.checkRowInGriByTextValue(driver, serviceSpecificationsReferenceEdited);
		
		clickEditServiceSpecifications();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getReference(), serviceSpecificationsReferenceEdited, "Reference before edit is correct." );
		
		softAssert.assertEquals(getCode(), serviceSpecificationsReferenceEdited, "Code before edit is correct." );
		
		softAssert.assertEquals(getNature(), nature, "Nature before edit is correct." );
		
		softAssert.assertAll();
		
		Reporter.log("Service Specifications was succesfully created/edited/", true);
	}
	
	/**
	 * Testcase ID		: C71606
	 * Author			: Intellias
	 * */		
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testserviceSpecificationsCreateDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C71606: Create Delete Service Specifications: 140: C29492, 122: C14677</span><br>", true);

		Reporter.log("User create--delete Service Specifications <br>", true);
		
		String serviceSpecificationsReference = "my auto"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String nature = "Default Nature";

		String serviceSpecificationsReferenceEdited = serviceSpecificationsReference + "_ed";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("Service Specifications");
		
		clickAdministration();
		
		expandMasterData();
		
		clickServiceSpecifications();
		
		clickAddServiceSpecifications();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(serviceSpecificationsReference);
		
		setCode(serviceSpecificationsReference);
		
		setNature(nature);
		
		saveServiceSpecifications();
		
		waitForExtJSAjaxComplete(20);
		
		clickTab("Dispatching Instructions");
		
		waitForExtJSAjaxComplete(20);
		
		clickTab("Validity");
		
		waitForExtJSAjaxComplete(20);
		
		clickTab("Contacts");
		
		waitForExtJSAjaxComplete(20);
		
		closeServiceSpecifications();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridPresent(driver, serviceSpecificationsReference, "@class", "x-grid3-body"), "New items is present in grid");
		
		Grid.checkRowInGriByTextValue(driver, serviceSpecificationsReference);
		
		clickDeleteServiceSpecifications();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, serviceSpecificationsReferenceEdited, "@class", "x-grid3-body"), "Items was deleted.");
		
		softAssert.assertAll();
		
		Reporter.log("Service Specifications was succesfully created/deleted/", true);
	}

}
