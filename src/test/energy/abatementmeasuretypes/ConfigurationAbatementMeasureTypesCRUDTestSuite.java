package test.energy.abatementmeasuretypes;

import java.io.IOException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.webelement.Grid;

public class ConfigurationAbatementMeasureTypesCRUDTestSuite extends
		ConfigurationAbatementMeasureTypesPageObject {


	@DataProvider
	public Object[][] configuration() {
		return new Object[][] {
		{"Abatement Measure Types", "abatement_measure_types_grid"}
		};
	}


	 @Test(enabled=true,dataProvider = "configuration", retryAnalyzer=RetryAnalyzer.class)
	public void testConfigurationTypeStatusEdit(String entity, String realId) throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Edit Energy Configuration:c11095 " + entity + " </span><br>",
				true);

		Reporter.log("User edit configuration type or status: " + entity + " <br>",
				true);
		
		String code = "my auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "test auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		boolean defaultState = true;
//		String classValue = "Active";
		String description = "description";
		
		String codeEdited = "my auto ed" + getCurrentDate().substring(getCurrentDate().length()-5);
		String referenceEdited = "test auto ed" + getCurrentDate().substring(getCurrentDate().length()-5);
		boolean defaultStateEdited = true;
		String classValue = "Archived";
		String descriptionEdited = "descriptionEdited";
		
//		expandApplication();

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandEnergyMenuItem("Configuration");
		
		clickOnEnergyEntity("Abatement Measure Types");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton(realId);
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		setReference(reference);
		
		checkDefault();
		
		if(entity.equals("Supply Point Types"))
		{setDescription(description);}
		
		if(entity.equals("Metering Object Statuses"))
		{setClassValueJavascript(classValue);
			//setClassValue(classValue);
			}
		
		saveClose();
		
		Grid.checkRowInGriByTextValue(driver, code);
		
		clickEditButton(realId);
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertEquals(getCode(),code, code+" code before edit is wrong");
		
		Assert.assertEquals(getReference(),reference, reference+" reference before edit is wrong");
		
		Assert.assertEquals(getDefaultState(),defaultState, defaultState + " defaultState before edit is wrong");
		
		if(entity.equals("Supply Point Types"))
		{Assert.assertEquals(getDescription(),description, description+" description before edit is wrong");}

		if(entity.equals("Metering Object Statuses"))
		{Assert.assertEquals(getClassValue(),classValue, classValue+" classValue before edit is wrong");}
		
		setCode(codeEdited);
		
		setReference(referenceEdited);
//we can not uncheck default if there is no default entity		
//		uncheckDefault();
		
		if(entity.equals("Supply Point Types"))
		{setDescription(descriptionEdited);}

//Class can not be changed after creation?		
//		if(entity.equals("Metering Object Statuses"))
//		{setClassValue(classValue);}
		
		saveClose();

		Grid.checkRowInGriByTextValue(driver, codeEdited);
		
		clickEditButton(realId);
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertEquals(getCode(),codeEdited, codeEdited+" code after edit is wrong");
		
		Assert.assertEquals(getReference(),referenceEdited, referenceEdited+" reference after edit is wrong");
		
		Assert.assertEquals(getDefaultState(),defaultStateEdited, defaultStateEdited + " defaultState after edit is wrong");
		
		if(entity.equals("Supply Point Types"))
		{Assert.assertEquals(getDescription(),descriptionEdited, descriptionEdited+" description after edit is wrong");}

		if(entity.equals("Metering Object Statuses"))
		{Assert.assertEquals(getClassValue(),classValue, classValue+" classValue after edit is wrong");}

		close();
		
		Reporter.log("Configuration " + entity + " was succesfully edited", true);

	}

	@Test(enabled = true, dataProvider = "configuration", retryAnalyzer=RetryAnalyzer.class)
	public void testOverviewDefinitionDelete(String entity, String realId) throws IllegalStateException, IOException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Energy Configuration: c11096 " + entity + " </span><br>",
				true);

		Reporter.log("User delete configuration type or status: " + entity + " <br>",
				true);
		
		String code = "my auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "test auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
//		String classValue = "Active";
		
		
//		expandApplication();

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		openConfigurationEntity(entity);

		waitForExtJSAjaxComplete(20);
		
		clickAddButton(realId);
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		setReference(reference);

//default class id active		
//		if(entity.equals("Metering Object Statuses"))
//		{setClassValue(classValue);}
		
		saveClose();
		
		Grid.checkRowInGriByTextValue(driver, code);
		
		clickDeleteButton(realId);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, code), code+" code after delete is present");
		
		Reporter.log("Configuration " + entity + " was succesfully deleted", true);
		
	}

}
