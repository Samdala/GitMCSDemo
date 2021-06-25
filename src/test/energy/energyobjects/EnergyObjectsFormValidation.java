package test.energy.energyobjects;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.McsElement;
import test.framework.webelement.Tree;

public class EnergyObjectsFormValidation extends EnergyObjectsPageObject{
	
	@Test(enabled=true)
	public void testEnergyObjectFormValidation() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Check Energy Object form validation: ????" + " </span><br>",
				true);

		Reporter.log("User tries to create Energy Object with different validations"  + " <br>",
				true);
		
		String code = "eo code " + getCurrentDate().substring(getCurrentDate().length()-6);
		String reference = "eo ref " + getCurrentDate().substring(getCurrentDate().length()-6);
		String codeNonUnique = "slnmNonUnique";
		String referenceNonUnique = "slnmNonUnique";
		
		List<String> mandatoryFieldsErrorMessageKeyWordsCheck =Arrays.asList("form", "invalid");
		List<String> uniqueCodeFieldErrorMessageKeyWordsCheck =Arrays.asList( "Energy Object" ,"already exists");
		List<String> uniqueReferenceFieldErrorMessageKeyWordsCheck =Arrays.asList("Energy Object","already exists");
		
		//Navigator
		
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite2";
					
		SoftAssert softAssert = new SoftAssert(); 
		
		softAssert.setMethodName("EnergyObjectCreateEdit");
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, site);
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddEnergyObjectButton(ENERGY_OBJECT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		//Check if Status field is prefilled with Status marked as Default
		
		Reporter.log("Check if Status field is prefilled with Status marked as Default", true);
		
		softAssert.assertTrue(verifyLookupIsPrefilledWithDefaultValue(DIALOG_ENERGY_OBJECT, "status", "Reference", "Energy Object Status"), "Status field is prefilled with Status marked as Default");
				
		//Check if Type field is prefilled with Type marked as Default
		
		Reporter.log("Check if Type field is prefilled with Type marked as Default", true);
		
		softAssert.assertTrue(verifyLookupIsPrefilledWithDefaultValue(DIALOG_ENERGY_OBJECT, "type", "Reference", "Energy Object Type"), "Type field is prefilled with Type marked as Default");
		
		//Check if Site field is prefilled with Site selected in Navigator
		
		Reporter.log("Check if Site field is prefilled with Site selected in Navigator", true);
				
		softAssert.assertEquals(getFieldValue("site"), site, "Site field is prefilled with Site selected in Navigator - "+site);
		
		//Check if Site field is read-only
		
		Reporter.log("Check if Site field is read-only", true);
						
		softAssert.assertTrue(checkWebElementDisabled("site"), "Site field is read-only");
		
		//Check UI of mandatory fields (bold text, asterisk)
		
		Reporter.log("Check UI of mandatory fields (bold text, asterisk)", true);
				
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_ENERGY_OBJECT, "code"), "Code field has correct UI (Bold text, asterix)");
				
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_ENERGY_OBJECT, "reference"), "Reference field has correct UI (Bold text, asterix)");
				
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_ENERGY_OBJECT, "site"), "Site field has correct UI (Bold text, asterix)");
				
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_ENERGY_OBJECT, "type"), "Type field has correct UI (Bold text, asterix)");
				
		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_ENERGY_OBJECT, "status"), "Status field has correct UI (Bold text, asterix)");
				
		//Check UI of non-mandatory fields (regular text)
				
		Reporter.log("Check UI of non-mandatory fields (regular text)", true);
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_ENERGY_OBJECT, "parentObject"), "Parent Object field has correct UI");
						
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_ENERGY_OBJECT, "physicalLocation"), "Physical Location field has correct UI");
						
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_ENERGY_OBJECT, "clientOrganization"), "Client Organization field has correct UI");
				
		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_ENERGY_OBJECT, "description"), "Description field has correct UI");
		
		//Try to save Meter Form with empty Reference field
		
		Reporter.log("Try to save Meter Form with empty Reference field", true);
		
		clearField("reference", "Reference");
		
		setCode(code);
				
		waitForExtJSAjaxComplete(20);
				
		save(DIALOG_ENERGY_OBJECT);
				
		waitForExtJSAjaxComplete(20);
				
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_ENERGY_OBJECT),"Form can't be saved with empty Reference field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_ENERGY_OBJECT, "reference"),"Red border is present on empty Reference field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_ENERGY_OBJECT, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
				
		reopenEOForm();
		
		//Try to save Energy Object Form with empty Code field
		
		Reporter.log("Try to save Energy Object Form with empty Code field", true);
		
		clearField("code", "Code");
		
		setReference(reference);
				
		waitForExtJSAjaxComplete(20);
				
		save(DIALOG_ENERGY_OBJECT);
				
		waitForExtJSAjaxComplete(20);
				
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_ENERGY_OBJECT),"Form can't be saved with empty Code field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_ENERGY_OBJECT, "code"),"Red border is present on empty Code field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_ENERGY_OBJECT, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
				
		reopenEOForm();
		
		//Try to save Energy Object Form with empty Type field
		
		Reporter.log("Try to save Energy Object Form with empty Type field", true);
		
		clearField("type", "Type");
		
		setCode(code+"1");
		
		setReference(reference+"1");
				
		waitForExtJSAjaxComplete(20);
				
		save(DIALOG_ENERGY_OBJECT);
				
		waitForExtJSAjaxComplete(20);
				
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_ENERGY_OBJECT),"Form can't be saved with empty Type field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_ENERGY_OBJECT, "type"),"Red border is present on empty Type field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_ENERGY_OBJECT, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
				
		reopenEOForm();
		
		//Try to save Energy Object Form with empty Status field
		
		Reporter.log("Try to save Energy Object Form with empty Status field", true);
		
		clearField("status", "Status");
		
		setCode(code+"2");
		
		setReference(reference+"2");
				
		waitForExtJSAjaxComplete(20);
				
		save(DIALOG_ENERGY_OBJECT);
				
		waitForExtJSAjaxComplete(20);
				
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_ENERGY_OBJECT),"Form can't be saved with empty Status field"); 

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_ENERGY_OBJECT, "status"),"Red border is present on empty Status field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_ENERGY_OBJECT, mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present"); 
				
		reopenEOForm();
		
		//Try to save Energy Objects Form with non-unique Code field
		
		Reporter.log("Try to save Energy Objects Form with non-unique Code field", true);
		
		setCode(codeNonUnique);
		
		setReference(reference+"3");
				
		waitForExtJSAjaxComplete(20);
				
		save(DIALOG_ENERGY_OBJECT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_ENERGY_OBJECT),"Form can't be saved with non-unique Code field"); 
		
		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_ENERGY_OBJECT, uniqueCodeFieldErrorMessageKeyWordsCheck),"Message about non-unique Code field is present"); 
		
		reopenEOForm();
		
		//Try to save Energy Objects Form with non-unique Reference field
		
		Reporter.log("Try to save Energy Objects Form with non-unique Reference field", true);
		
		setCode(code+"3");
		
		setReference(referenceNonUnique);
				
		waitForExtJSAjaxComplete(20);
				
		save(DIALOG_ENERGY_OBJECT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_ENERGY_OBJECT),"Form can't be saved with non-unique Reference field"); 
		
		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_ENERGY_OBJECT, uniqueReferenceFieldErrorMessageKeyWordsCheck),"Message about non-unique Reference field is present"); 
		
		reopenEOForm();
		
		//Try to save Energy Objects Form form with empty optional fields
		
		Reporter.log("Try to save Energy Objects Form form with empty optional fields", true);
		
		setCode(code+"4");
		
		setReference(reference+"4");
				
		waitForExtJSAjaxComplete(20);
				
		save(DIALOG_ENERGY_OBJECT);
		
		waitForExtJSAjaxComplete(20);
		
		close(DIALOG_ENERGY_OBJECT);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Tree.isNodeInTreePresent(driver, reference+"4"),"Energy Object is created with empty optional fields and displayed in Navigation"); 
		
		softAssert.assertAll();
				
	}

}
