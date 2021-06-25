package test.energy.occurrencetypes;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.webelement.Grid;

public class ConfigurationOccurrenceTypesCRUDTestSuite extends
		ConfigurationOccurrenceTypesPageObject {


	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testConfigurationOccurrenceTypeEdit() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Edit Energy OccurrenceType :44094, C44192</span><br>",
				true);

		Reporter.log("User edit configuration OccurrenceType: <br>",
				true);
		
		String code = "my auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "test auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		String occurrenceTypeClass = "Normal Occurrence";
		
		String codeEdited = "my auto ed" + getCurrentDate().substring(getCurrentDate().length()-5);
		String referenceEdited = "test auto ed" + getCurrentDate().substring(getCurrentDate().length()-5);
		//String occurrenceTypeClassEdited = "Occupancy Hours";

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		openConfigurationEntity("Occurrence Types");

		waitForExtJSAjaxComplete(20);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		setReference(reference);
		
		setOccurrenceTypeClass(occurrenceTypeClass);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, code);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertEquals(getCode(),code, code+" code before edit");
		
		Assert.assertEquals(getReference(),reference, reference+" reference before edit");
		
		Assert.assertEquals(getOccurrenceTypeClass(),occurrenceTypeClass, occurrenceTypeClass + " occurrenceTypeClass before edit");
		
		setCode(codeEdited);
		
		setReference(referenceEdited);
		
		//setOccurrenceTypeClass(occurrenceTypeClassEdited);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, codeEdited);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertEquals(getCode(),codeEdited, codeEdited+" code after edit is wrong");
		
		Assert.assertEquals(getReference(),referenceEdited, referenceEdited+" reference after edit is wrong");

		//Assert.assertEquals(getOccurrenceTypeClass(),occurrenceTypeClassEdited, occurrenceTypeClassEdited + " occurrenceTypeClass after edit");

		Reporter.log("Configuration Occurrence type was succesfully edited", true);

	}

	@Test(enabled = true)
	public void testConfigurationOccurrenceTypeDelete() throws IllegalStateException, IOException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Occurrence Type: 44095, C44193 </span><br>",
				true);

		Reporter.log("User delete Occurrence Type <br>", true);
		
		String code = "my auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "test auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
		String occurrenceTypeClass = "Normal Occurrence";
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		openConfigurationEntity("Occurrence Types");

		waitForExtJSAjaxComplete(20);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		setReference(reference);
		
		setOccurrenceTypeClass(occurrenceTypeClass);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		Grid.checkRowInGriByTextValueAndClick(driver, code);
		
		clickDeleteButton();
		
		clickOnDialogButton("OK");
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, code), code+" code after delete is present");
		
		Reporter.log("Configuration Occurrence Type was succesfully deleted", true);
		
	}

}
