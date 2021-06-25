package test.generalweb.service;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.generalweb.scheduler.SchedulerPageObject;
import test.generalweb.workorders.WorkOrderPageObject;

public class ServiceOrganizationsCRUDTestSuite extends
	ServiceOrganizationsPageObject {

	/**
	 * Testcase ID		: C71600
	 * Author			: Intellias
	 * */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testserviceOrganizationsCreateEdit() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C71600: Create Edit Service Organizations: 140: C29481, 122: 14689</span><br>", true);

		Reporter.log("User create and edit Service Organizations <br>", true);
		
		String serviceOrganizationsReference = "my auto"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String serviceOrganizationsDescription = "service Organizations Description";
		
		String serviceOrganizationsDescriptionEdited = "service Organizations DescriptionEd";

		String serviceOrganizationsReferenceEdited = serviceOrganizationsReference + "_ed";
		
		String siteName = "slnmEnrgSite2";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("Service Organizations");
		
		clickAdministration();
		
		expandMasterData();
		
		waitForExtJSAjaxComplete(20);
		
		clickServiceOrganizations();
		
		clickAddServiceOrganizations();
		

		SchedulerPageObject schPgObj = new SchedulerPageObject();
		schPgObj.collapseNavigation();
		waitForExtJSAjaxComplete(15);
		
		WorkOrderPageObject workPageObject=new  WorkOrderPageObject();
		workPageObject.collapseAdministration();
		waitForExtJSAjaxComplete(15);
		
		waitForExtJSAjaxComplete(20);
		
		setReference(serviceOrganizationsReference);
		
		waitForExtJSAjaxComplete(20);
		
		setCode(serviceOrganizationsReference);
		
		waitForExtJSAjaxComplete(20);
		
		setDescription(serviceOrganizationsDescription);
		
		waitForExtJSAjaxComplete(20);
		
		saveServiceOrganizations();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(itemIsPresentInServiceOrganizationList(serviceOrganizationsReference), "New items is present in list");
		
		selectServiceOrganizationItem(serviceOrganizationsReference);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getReference(), serviceOrganizationsReference, "Reference before edit is correct." );
		
		softAssert.assertEquals(getCode(), serviceOrganizationsReference, "Code before edit is correct." );
		
		softAssert.assertEquals(getDescription(), serviceOrganizationsDescription, "Description before edit is correct." );
		
		setReference(serviceOrganizationsReferenceEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setCode(serviceOrganizationsReferenceEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setDescription(serviceOrganizationsDescriptionEdited);
		
		waitForExtJSAjaxComplete(20);
		
		saveServiceOrganizations();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(itemIsPresentInServiceOrganizationList(serviceOrganizationsReferenceEdited), "Edited items is present in list");
		
		selectServiceOrganizationItem(serviceOrganizationsReferenceEdited);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getReference(), serviceOrganizationsReferenceEdited, "Reference before edit is correct." );
		
		softAssert.assertEquals(getCode(), serviceOrganizationsReferenceEdited, "Code before edit is correct." );
		
		softAssert.assertEquals(getDescription(), serviceOrganizationsDescriptionEdited, "Description before edit is correct." );
		
		waitForExtJSAjaxComplete(20);

		selectServiceOrganizationItemSite(serviceOrganizationsReferenceEdited, "Site");
		
		waitForExtJSAjaxComplete(20);

		clickLinkSite();
		
		selectLocations(siteName);
		
		clickLinkSite();
		
		softAssert.assertTrue(checkIfLocationsAbsent(siteName), "Items site is absent");
		
		clickCANCELXwindowSO();
		
		selectServiceOrganizationItemSite(serviceOrganizationsReferenceEdited, siteName);
		
		clickUnLinkSite();
		
		softAssert.assertTrue(checkIfServiceOrganizationItemSiteAbsent(serviceOrganizationsReferenceEdited, siteName), "Site was unlinked.");
		
		softAssert.assertAll();
		
		Reporter.log("Service Organizations was succesfully created/edited/", true);
	}
	
	/**
	 * Testcase ID		: C71600,C29486
	 * Author			: ssa
	 * */		
	 @Test(enabled = false, retryAnalyzer=RetryAnalyzer.class)
	public void testserviceGroupCreateDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test C71600: Create Delete Service Organizations: 140: C29481, 122: C14689</span><br>"
				+"C29486: Service Groups Add/Modify/Delete", true);

		Reporter.log("User create--delete Service OrganizationsGroups <br>", true);
		
		String serviceOrganizationsReference = "my auto"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String serviceOrganizationsDescription = "service Organizations Description";
		
		String serviceGrpReference = "service grp"+ getCurrentDate().substring(getCurrentDate().length()-6);
		
		String serviceGrpDescription = "service grp Description";
		
		String siteName = "HDSite3";
		
		//String siteName = "1aqaPreParSites";
		
		String responsible = getUserLastNameOfLoggedInUser();
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("Service Organizations");
		
		clickAdministration();
		
		waitForMaskDisappear();
		
		expandMasterData();
		
		waitForExtJSAjaxComplete(20);
		
		clickServiceOrganizations();
		
		waitForExtJSAjaxComplete(20);
		
		collapseNavigation();
		
		//create service org
		clickAddServiceOrganizations();
		
		waitForExtJSAjaxComplete(20);
		
		collapseAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(serviceOrganizationsReference);
		
		waitForExtJSAjaxComplete(20);
		
		setCode(serviceOrganizationsReference);
		
		waitForExtJSAjaxComplete(20);
		
		setDescription(serviceOrganizationsDescription);
		
		waitForExtJSAjaxComplete(20);
		
		saveServiceOrganizations();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(itemIsPresentInServiceOrganizationList(serviceOrganizationsReference), "New items is present in list");
		
		selectServiceOrganizationItem(serviceOrganizationsReference);
		
		waitForExtJSAjaxComplete(20);

		//add site
		selectServiceOrganizationItemSite(serviceOrganizationsReference, "Site");
		
		waitForExtJSAjaxComplete(20);

		clickLinkSite();
		
		waitForExtJSAjaxComplete(20);
		
		selectLocations(siteName);
		
		waitForExtJSAjaxComplete(20);
		
		selectServiceOrganizationItem(serviceOrganizationsReference);
		
		waitForExtJSAjaxComplete(20);
		
		//add service grp
		clickAddServiceGroup();
		
		waitForExtJSAjaxComplete(20);
		
		setReferenceForServiceGrp(serviceGrpReference);
		
		waitForExtJSAjaxComplete(20);
		
		setCodeForServiceGrp(serviceGrpReference);
		
		waitForExtJSAjaxComplete(20);
		
		setDescriptionForServiceGrp(serviceGrpDescription);
		
		waitForExtJSAjaxComplete(20);
		
		saveServiceGroup();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(itemIsPresentInServiceOrganizationList(serviceGrpReference), "New items is present in list");
		
		waitForExtJSAjaxComplete(20);
		
		//Select the service group
		selectServiceOrganizationItemSite(serviceOrganizationsReference, serviceGrpReference);

		//add link site
		clickLinkSite();
		
		waitForExtJSAjaxComplete(20);
		
		selectLocations(siteName);
		
		waitForExtJSAjaxComplete(20);
		
		selectServiceOrganizationItemSite(serviceGrpReference, siteName);
		
		waitForExtJSAjaxComplete(20);
		
		clickUnLinkSite();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkIfServiceOrganizationItemSiteAbsent(serviceGrpReference, siteName), "Site was unlinked.");
		
		//link contact
		clicklinkContactPerson();
		
		waitForExtJSAjaxComplete(20);
		
		setRole("Admin manager","Reference");
		
		waitForExtJSAjaxComplete(20);
		
		setContact(responsible,"Last Name");
		
		waitForExtJSAjaxComplete(20);
		
		saveContactPerson();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(itemIsPresentInServiceOrganizationList("Admin manager"), "New contact is present in the service group ");
		
		selectContactInServiceGroup(serviceGrpReference,"Admin manager");
		
		waitForExtJSAjaxComplete(25);
		//Unlink site
		clickUnlinkContactPerson();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertFalse(itemIsPresentInServiceOrganizationList("Admin manager"), "New contact is not present in the service group ");
		
		
		selectServiceOrganizationItem(serviceOrganizationsReference);
		
		waitForExtJSAjaxComplete(20);
		
		//Delete org
		clickDeleteServiceOrganizations();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(itemIsAbsentInServiceOrganizationList(serviceOrganizationsReference), "Item was deleted.");
		
		softAssert.assertAll();
		
		Reporter.log("Service groups was succesfully created/deleted/", true);
	}

}


