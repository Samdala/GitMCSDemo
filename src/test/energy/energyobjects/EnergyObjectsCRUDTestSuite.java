package test.energy.energyobjects;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;

public class EnergyObjectsCRUDTestSuite extends EnergyObjectsPageObject{
	
	@Test(enabled=true)
		public void testEnergyObjectCreateEdit() throws IOException  {

			Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Create and Edit Energy Objects: ????" + " </span><br>",
					true);

			Reporter.log("User creates and edits Energy Object"  + " <br>",
					true);
			
			String code = "eo code " + getCurrentDate().substring(getCurrentDate().length()-6);
			String reference = "eo ref " + getCurrentDate().substring(getCurrentDate().length()-6);
			String parentEnergyObject = "slnmEnrgBuilding2";
			String type = "Office";
			String physicalLocation = "slnmEnrgBuilding1";
			String status = "Archived";
			String clientOrganization = "slnmClientOrganization";
			String description = "Description";
			
			String codeEdited = "eo code ed " + getCurrentDate().substring(getCurrentDate().length()-6);
			String referenceEdited = "eo ref ed " + getCurrentDate().substring(getCurrentDate().length()-6);
			String typeEdited = "Default";
			String physicalLocationEdited = "slnmEnrgBuilding2";
			String statusEdited = "Active";
			String clientOrganizationEdited = "slnmClientOrganization2";
			String descriptionEdited = "Description edited";
			
			//Navigator
			
			String area = "slnmEnrgArea1 (slnmEnrgArea)";
			String site = "slnmEnrgSite1";
						
			SoftAssert softAssert = new SoftAssert(); 
			
			softAssert.setMethodName("EnergyObjectCreateEdit");
			
			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(20);
			
			expandNavigator();
			
			waitForExtJSAjaxComplete(20);
			
			test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
			
			waitForExtJSAjaxComplete(20);
			
			test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			clickAddEnergyObjectButton(ENERGY_OBJECT_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			setCode(code);
			
			setReference(reference);
			
			setType(type);
			
			setStatus(status);
			
			setParentObject(parentEnergyObject);
			
			setPhysicalLocation(site, physicalLocation);
			
			setClientOrganization(clientOrganization);
			
			setDescription(description);
			
			saveClose(DIALOG_ENERGY_OBJECT);
			
			waitForExtJSAjaxComplete(20);
			
			test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, reference);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			clickEditEnergyObjectButton(ENERGY_OBJECT_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertEquals(getFieldValue("code"), code, code + " - Code field value after creation is correct?");
			
			softAssert.assertEquals(getFieldValue("reference"), reference, reference + " - Reference field value after creation is correct?");
			
			softAssert.assertEquals(getFieldValue("parentObject"), parentEnergyObject, parentEnergyObject + " - Parent Object field value after creation is correct?");
			
			softAssert.assertEquals(getFieldValue("type"), type, type + " - Type field value after creation is correct?");
			
			softAssert.assertEquals(getFieldValue("status"), status, status + " - Status field value after creation is correct?");
			
			softAssert.assertEquals(getFieldValue("physicalLocation"), physicalLocation, physicalLocation + " - Physical Location field value after creation is correct?");
			
			softAssert.assertEquals(getFieldValue("clientOrganization"), clientOrganization, clientOrganization + " - Client Organization field value after creation is correct?");
			
			softAssert.assertEquals(getTextAreaValue("description"), description, description + " - Description field value after creation is correct?");
			
			//Check if Parent Object field is read-only during editing Energy Object
			
			Reporter.log("Check if Parent Object field is read-only during editing Energy Object", true);
							
			softAssert.assertTrue(checkWebElementDisabled("parentObject"), "Parent Object field is read-only during editing Energy Object");
			
			setCode(codeEdited);
			
			setReference(referenceEdited);
			
			setType(typeEdited);
			
			setStatus(statusEdited);
			
			setPhysicalLocation(site, physicalLocationEdited);
			
			setClientOrganization(clientOrganizationEdited);
			
			setDescription(descriptionEdited);
			
			saveClose(DIALOG_ENERGY_OBJECT);
			
			waitForExtJSAjaxComplete(20);
			
			test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, referenceEdited);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(20);
			
			clickEditEnergyObjectButton(ENERGY_OBJECT_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertEquals(getFieldValue("code"), codeEdited, codeEdited + " - Code field value after editing is correct?");
			
			softAssert.assertEquals(getFieldValue("reference"), referenceEdited, referenceEdited + " - Reference field value after editing is correct?");
			
			softAssert.assertEquals(getFieldValue("parentObject"), parentEnergyObject, parentEnergyObject + " - Parent Object field value after editing is correct?");
			
			softAssert.assertEquals(getFieldValue("type"), typeEdited, typeEdited + " - Type field value after editing is correct?");
			
			softAssert.assertEquals(getFieldValue("status"), statusEdited, statusEdited + " - Status field value after editing is correct?");
			
			softAssert.assertEquals(getFieldValue("physicalLocation"), physicalLocationEdited, physicalLocationEdited + " - Physical Location field value after editing is correct?");
			
			softAssert.assertEquals(getFieldValue("clientOrganization"), clientOrganizationEdited, clientOrganizationEdited + " - Client Organization field value after editing is correct?");
			
			softAssert.assertEquals(getTextAreaValue("description"), descriptionEdited, descriptionEdited + " - Description field value after editing is correct?");
			
			softAssert.assertAll();
			
	}
			
	@Test(enabled=true)
			public void testEnergyObjectDelete() throws IOException  {

			Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Delete Energy Objects: ????" + " </span><br>",
					true);

			Reporter.log("User deletes Energy Object"  + " <br>",
						true);
				
			String code = "eo code " + getCurrentDate().substring(getCurrentDate().length()-6);
			String reference = "eo ref " + getCurrentDate().substring(getCurrentDate().length()-6);
			String type = "Office";
			String status = "Archived";
				
			//Navigator
				
			String area = "slnmEnrgArea1 (slnmEnrgArea)";
			String site = "slnmEnrgSite1";
				
			waitAndClick(XPATH_ENERGY);
				
			waitForExtJSAjaxComplete(20);
				
			expandNavigator();
				
			waitForExtJSAjaxComplete(20);
				
			test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
				
			waitForExtJSAjaxComplete(20);
				
			test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
				
			waitForExtJSAjaxComplete(20);
				
			waitForExtJSAjaxComplete(20);
				
			clickAddEnergyObjectButton(ENERGY_OBJECT_GRID_CLASS);
				
			waitForExtJSAjaxComplete(20);
				
			setCode(code);
				
			setReference(reference);
				
			setType(type);
				
			setStatus(status);
				
			saveClose(DIALOG_ENERGY_OBJECT);
				
			waitForExtJSAjaxComplete(20);
				
			test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, reference);
				
			waitForExtJSAjaxComplete(20);
				
			waitForExtJSAjaxComplete(20);
				
			clickDeleteEnergyObjectButton(ENERGY_OBJECT_GRID_CLASS);
				
			waitForExtJSAjaxComplete(20);
			
			clickOnDialogButton("Yes");
	 }


}
