package test.energy.energyobjectstatuses;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.webelement.Grid;

public class ConfigurationEnergyObjectStatusesCRUDTestSuite extends
		ConfigurationEnergyObjectStatusesPageObject {


	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testConfigurationEnergyObjectTypeEdit() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Edit Energy Configuration:c29429, c29431 </span><br>",
				true);

		Reporter.log("User edit configuration type or status: <br>",
				true);
		
		String code = "my auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "test auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		boolean defaultState = true;
		String objectClass = "Active";
		
		String codeEdited = "my auto ed" + getCurrentDate().substring(getCurrentDate().length()-5);
		String referenceEdited = "test auto ed" + getCurrentDate().substring(getCurrentDate().length()-5);
		String objectClassEdited = "Archived";

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(25);
		
		openConfigurationEntity("Energy Object Statuses");

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		setCode(code);
		
		setReference(reference);
		
		//checkDefault();
		
		setObjectClass(objectClassEdited);
		
		saveClose();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValue(driver, code);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		Assert.assertEquals(getCode(),code, code+" code before edit is wrong");
		
		Assert.assertEquals(getReference(),reference, reference+" reference before edit is wrong");
		
		//Assert.assertEquals(getDefaultState(),defaultState, defaultState + " defaultState before edit is wrong");
		
		Assert.assertEquals(getObjectClass(),objectClassEdited, objectClassEdited+" objectClass before edit is wrong");
		
		setCode(codeEdited);
		
		setReference(referenceEdited);
		
		setObjectClass(objectClass);
		
		checkDefault();
		
		saveClose();

		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValue(driver, codeEdited);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		Assert.assertEquals(getCode(),codeEdited, codeEdited+" code after edit is wrong");
		
		Assert.assertEquals(getReference(),referenceEdited, referenceEdited+" reference after edit is wrong");
		
		Assert.assertEquals(getObjectClass(),objectClass, objectClass+" objectClass after edit is wrong");
		
		Assert.assertEquals(getDefaultState(),defaultState, defaultState + " defaultState before edit is wrong");

		close();
		
		Reporter.log("Configuration Energy object type was succesfully edited", true);

	}

	@Test(enabled = true,retryAnalyzer=RetryAnalyzer.class)
	public void testConfigurationEnergyObjectTypeDelete() throws IllegalStateException, IOException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Energy object type: c29429, 29430 </span><br>",
				true);

		Reporter.log("User delete Energy object type <br>", true);
		
		String code = "my auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "test auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
		String objectClass = "Active";
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		openConfigurationEntity("Energy Object Statuses");

		waitForExtJSAjaxComplete(25);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		setCode(code);
		
		setReference(reference);
		
		waitForExtJSAjaxComplete(20);
		
		setObjectClass(objectClass);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValue(driver, code);
		
		clickDeleteButton();
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, code), code+" code after delete is present");
		
		Reporter.log("Configuration Energy object type was succesfully deleted", true);
		
	}

}
