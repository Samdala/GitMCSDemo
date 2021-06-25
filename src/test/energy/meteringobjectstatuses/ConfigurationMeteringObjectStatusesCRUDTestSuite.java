package test.energy.meteringobjectstatuses;

import java.io.IOException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.webelement.Grid;

public class ConfigurationMeteringObjectStatusesCRUDTestSuite extends
		ConfigurationMeteringObjectStatusesPageObject {


	@DataProvider
	public Object[][] configuration() {
		return new Object[][] {
		{"Metering Object Statuses", "meter_statuses_overview"}
		};
	}


	 @Test(enabled=true,dataProvider = "configuration")
	public void testConfigurationTypeStatusEdit(String entity, String realId) throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Edit Energy Configuration " + entity + " </span><br>",
				true);

		Reporter.log("User edit configuration type or status:c10590(testrail) " + entity + " <br>",
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
		
		Boolean build14 = driver.getCurrentUrl().contains("14");
		Boolean build122 = driver.getCurrentUrl().contains("122");
		
//		expandApplication();

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		openConfigurationEntity(entity);

		waitForExtJSAjaxComplete(20);
		
		clickAddButton("x-panel-noborder x-grid-panel");
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		setReference(reference);
		
		checkDefault();
		
		if(entity.equals("Supply Point Types"))
		{setDescription(description);}
		
		if(entity.equals("Metering Object Statuses"))
		{	
			if(build14){
				setClassValueJavascript(classValue);
			} else{
				setClassValueTrunk(classValue);
			}
		}
		
		saveClose();
		
		Grid.checkRowInGriByTextValue(driver, code);
		
		clickEditButton("x-panel-noborder x-grid-panel");
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertEquals(getCode(),code, code+" code before edit is wrong");
		
		Assert.assertEquals(getReference(),reference, reference+" reference before edit is wrong");
		
		if(driver.getCurrentUrl().contains("aqa122")){
		
		Assert.assertEquals(getDefaultState(),defaultState, defaultState + " defaultState before edit is wrong");
		}
		else {
			
			Assert.assertEquals(getDefaultState(),!defaultState, !defaultState + " defaultState before edit is wrong");
		}
		
		if(entity.equals("Supply Point Types"))
		{Assert.assertEquals(getDescription(),description, description+" description before edit is wrong");}

		if(entity.equals("Metering Object Statuses"))
		{	
			if(build14 || build122){
				Assert.assertEquals(getClassValue(),classValue, classValue+" classValue before edit is wrong");
			} else{
				Assert.assertEquals(getClassValueTrunk(),classValue, classValue+" classValue before edit is wrong");
			}
		}
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
		
		clickEditButton("x-panel-noborder x-grid-panel");
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertEquals(getCode(),codeEdited, codeEdited+" code after edit is wrong");
		
		Assert.assertEquals(getReference(),referenceEdited, referenceEdited+" reference after edit is wrong");
		
		if(driver.getCurrentUrl().contains("aqa122")){
		
		Assert.assertEquals(getDefaultState(),defaultStateEdited, defaultStateEdited + " defaultState after edit is wrong");
		}
		else {Assert.assertEquals(getDefaultState(),!defaultStateEdited, !defaultStateEdited + " defaultState after edit is wrong");}
		
		if(entity.equals("Supply Point Types"))
		{Assert.assertEquals(getDescription(),descriptionEdited, descriptionEdited+" description after edit is wrong");}

		if(entity.equals("Metering Object Statuses"))
		{
			if(build14 || build122){
				Assert.assertEquals(getClassValue(),classValue, classValue+" classValue before edit is wrong");
			} else{
				Assert.assertEquals(getClassValueTrunk(),classValue, classValue+" classValue before edit is wrong");
			}
		}

		close();
		
		Reporter.log("Configuration " + entity + " was succesfully edited", true);

	}

	@Test(enabled = true, dataProvider = "configuration")
	public void testOverviewDefinitionDelete(String entity, String realId) throws IllegalStateException, IOException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Energy Configuration " + entity + " </span><br>",
				true);

		Reporter.log("User delete configuration type or status:c10591(testrail) " + entity + " <br>",
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
		
		clickAddButton("x-panel-noborder x-grid-panel");
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		setReference(reference);

//default class id active		
//		if(entity.equals("Metering Object Statuses"))
//		{setClassValue(classValue);}
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, code);
		
		clickDeleteButton("x-panel-noborder x-grid-panel");
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, code), code+" code after delete is present");
		
		Reporter.log("Configuration " + entity + " was succesfully deleted", true);
		
	}

}
