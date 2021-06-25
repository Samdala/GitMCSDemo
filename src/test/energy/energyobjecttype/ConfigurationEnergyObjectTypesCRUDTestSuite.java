package test.energy.energyobjecttype;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.energy.energyobjects.EnergyObjectsPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import test.energy.energyobjects.*;

public class ConfigurationEnergyObjectTypesCRUDTestSuite extends
		ConfigurationEnergyObjectTypesPageObject {


	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testConfigurationEnergyObjectTypeEdit() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Edit Energy Configuration:c29425, C29424 </span><br>",
				true);

		Reporter.log("User edit configuration type or status: <br>",
				true);
		
		String code = "my auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "test auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		boolean defaultState = true;
		String baseTemperature = "5.0 °C";
		String defaultUOM = "°C";
		
		String codeEdited = "my auto ed" + getCurrentDate().substring(getCurrentDate().length()-5);
		String referenceEdited = "test auto ed" + getCurrentDate().substring(getCurrentDate().length()-5);
		//boolean defaultStateEdited = false;
		String defaultUOMEdited = "°F";
		String baseTemperatureEdited = "33.0 °F";
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		SoftAssert softAssert = new SoftAssert();
		
		expandConfiguration();
		
		openConfigurationEntity("Energy Object Types");

		waitForExtJSAjaxComplete(20);
		
		softAssert.setMethodName("testConfigurationEnergyObjectTypeEdit");
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		setReference(reference);
		
		checkDefault();
		
		setTemperatureUOM(defaultUOM);
		
		setCoolingBaseTemperature(baseTemperature);
		
		setHeatingBaseTemperature(baseTemperature);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, code);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getCode(),code, code+" code before edit");
		
		softAssert.assertEquals(getReference(),reference, reference+" reference before edit");
		
		softAssert.assertEquals(getDefaultState(),defaultState, defaultState + " defaultState before edit");
		
		softAssert.assertEquals(getTemperatureUOM(),defaultUOM, defaultUOM + " defaultUOM before edit");
		
		setCode(codeEdited);
		
		setReference(referenceEdited);
		
		setTemperatureUOM(defaultUOMEdited);
		
		setCoolingBaseTemperature(baseTemperatureEdited);
		
		setHeatingBaseTemperature(baseTemperatureEdited);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValue(driver, codeEdited);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getCode(),codeEdited, codeEdited+" code after edit is wrong");
		
		softAssert.assertEquals(getReference(),referenceEdited, referenceEdited+" reference after edit is wrong");
		
		softAssert.assertEquals(getHeatingBaseTemperature(),baseTemperatureEdited, baseTemperatureEdited + " heatingbaseTemperatureEdited after edit");
		
		softAssert.assertEquals(getCoolingBaseTemperature(),baseTemperatureEdited, baseTemperatureEdited + " coolingbaseTemperatureEdited after edit is wrong");

		softAssert.assertEquals(getTemperatureUOM(),defaultUOMEdited, defaultUOMEdited + " defaultUOM after edit");
		
		close();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("Configuration Energy object type was succesfully edited", true);

	}

	@Test(enabled = true)
	public void testConfigurationEnergyObjectTypeDelete() throws IllegalStateException, IOException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Energy object type: c29426 </span><br>",
				true);

		Reporter.log("User delete Energy object type <br>", true);
		
		String code = "my auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "test auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
		String baseTemperature = "5.0";
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		SoftAssert softAssert = new SoftAssert();
		
		expandConfiguration();
		
		openConfigurationEntity("Energy Object Types");

		waitForExtJSAjaxComplete(20);
		
		softAssert.setMethodName("testConfigurationEnergyObjectTypeDelete");
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		setReference(reference);
		
		setCoolingBaseTemperature(baseTemperature);
		
		setHeatingBaseTemperature(baseTemperature);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, code);
		
		clickDeleteButton();
		
		clickOnDialogButton("OK");
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, code), code+" code after delete is present");
		
		softAssert.assertAll();
		
		Reporter.log("Configuration Energy object type was succesfully deleted", true);
		
	}
	
	/**
	 * Test Case ID	 : C60616, C60617, C60618.
	 * Author		 : MMA
	 * */
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testVerifyAtleastOneEnergyObjectTypeIsDefault () throws IOException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60616: Built In Default Row </span><br>",
				true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60617: Single Default Row  </span><br>",
				true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60617: User Can't Delete the Default Row </span><br>",
				true);
		
		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testVerifyAtleastOneEnergyObjectTypeIsDefault ");

		String codeExpected = "DEFAULT";
		String refExpected = "Default";
		String reqAttribute = "@class";
		String reqAttributeValue = "slnmEnergyObjectTypeId";
		int count = 1;
		boolean statusExp = false;

		waitForExtJSAjaxComplete(30);

			waitAndClick(XPATH_ENERGY);

			waitForExtJSAjaxComplete(20);

			expandConfiguration();

			openConfigurationEntity("Energy Object Types");

			waitForExtJSAjaxComplete(20);

			int  defaultRowsCount = getNoOfDefaultEnergyObjectTypes(
					"energy_object_types_grid", "Default");

			softAssert.assertEquals(defaultRowsCount, count, "1 Built In Rows are Defaulted in the Grid");

			Grid.checkRowInGriByTextValueAndClick(driver, "@realid", "energy_object_types_grid", "DEFAULT");

			waitForExtJSAjaxComplete(5);

			clickEditButton();

			waitForExtJSAjaxComplete(10);

			softAssert.assertEquals(getCode(), codeExpected, "Code field doesnt have Default Value");

			softAssert.assertEquals(getReference(), refExpected, "Reference field doesnt have Default Value");

			boolean status = getDefaultState();

			if (!status) {

				checkDefault();

				waitForExtJSAjaxComplete(5);

				save();

				waitForExtJSAjaxComplete(10);

				//Success Message Verification
				String warningMsgActual = getInfoBarMsgOfWindow(reqAttribute, reqAttributeValue);

				softAssert.assertTrue(warningMsgActual.contains("Your changes have been saved."), "Checked as default Type");

				Reporter.log("Default is Checked", true);

				waitForExtJSAjaxComplete(10);

				} else {

				Reporter.log("Default Checkbox is already checked and its value is ON", true);

				}

			//Uncheck Default 
			checkDefault();

			waitForExtJSAjaxComplete(5);

			save();

			waitForExtJSAjaxComplete(10);

			String warningMsgActual = getInfoBarMsgOfWindow(reqAttribute, reqAttributeValue);
			
			
			softAssert.assertTrue(warningMsgActual.contains("No Energy Object Type has been marked as default. Please set one as default before saving"),
							"Default Type Can't be unchecked");
			
			waitForExtJSAjaxComplete(10);
			
			close();

			waitForExtJSAjaxComplete(20);

			clickOnDialogButton("Yes");

			waitForExtJSAjaxComplete(10);
			
			Grid.checkRowInGriByTextValueAndClick(driver, "@realid", "energy_object_types_grid", "OFFICE");

			waitForExtJSAjaxComplete(5);

			clickEditButton();

			waitForExtJSAjaxComplete(10);
			
			checkDefault();

			waitForExtJSAjaxComplete(5);

			save();

			waitForExtJSAjaxComplete(10);

			//Success Message Verification
			warningMsgActual = getInfoBarMsgOfWindow(reqAttribute, reqAttributeValue);

			softAssert.assertTrue(warningMsgActual.contains("Your changes have been saved."), "Office row is made as default");
			
			close();

			waitForExtJSAjaxComplete(10);

			//Verifying Default Row is Unchecked
			Grid.checkRowInGriByTextValueAndClick(driver, "@realid", "energy_object_types_grid", "DEFAULT");

			waitForExtJSAjaxComplete(5);

			clickEditButton();

			waitForExtJSAjaxComplete(10);

			waitForExtJSAjaxComplete(10);

			softAssert.assertEquals(getDefaultState(), statusExp, "Default Row is unchecked");

			waitForExtJSAjaxComplete(10);

			close();
			
			Grid.checkRowInGriByTextValueAndClick(driver, "@realid", "energy_object_types_grid", "OFFICE");

			waitForExtJSAjaxComplete(5);

			clickDeleteButton();
			
			waitForExtJSAjaxComplete(10);
			
			clickOnDialogButton("OK");
			
			waitForExtJSAjaxComplete(10);
						
			warningMsgActual = getWarningDialogTextMsg();

			softAssert.assertTrue(warningMsgActual.contains("You cannot delete the default Energy Object Type."),"Default Row Can be Deleted");
	
			clickOnDialogButton("OK");
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertAll();
			
			Reporter.log("Built In Default Row and Single Default Row are successfully verified", true);
	}
	
	/**
	 * Test Case ID: C60619.
	 * Author      : MMA
	 */
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void  testVerifyEnergyObjectTypeInUseCantBeDeleted() throws IOException {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C60619: User Can't Delete a Row Used By Energy Object </span><br>",
				true);
		
		SoftAssert softAssert = new SoftAssert(); 
		
		softAssert.setMethodName(" testVerifyEnergyObjectTypeInUseCantBeDeleted");
		
		waitAndClick(XPATH_ENERGY);

		waitForExtJSAjaxComplete(20);
		
		boolean build122 = driver.getCurrentUrl().contains("122");
		boolean build14  = driver.getCurrentUrl().contains("14");
				
		if(!build122) {

		EnergyObjectsPageObject obj = new EnergyObjectsPageObject();
		
		obj.expandFacilities();
		
		obj.openFacilityEntity("Energy Objects");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(5);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@realid", "mogrid", "slnmEnrgScope2");

		waitForExtJSAjaxComplete(25);
		
	    waitForExtJSAjaxComplete(10);
		
		obj.clickOverViewButtonsInEnergyObjectsOfFacilities("x-panel x-panel","Edit");
		
		waitForExtJSAjaxComplete(25);
				
		softAssert.assertEquals(obj.getFieldValue("type"),"Office", "Object Type is Office");
					
		waitForExtJSAjaxComplete(10);
		 
		obj.close("slnmEnergyObjectsId");
		
		waitForExtJSAjaxComplete(10);
		
		expandConfiguration();

		openConfigurationEntity("Energy Object Types");

		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "@realid", "energy_object_types_grid", "OFFICE");
		
		clickEditButton();

		boolean status = getDefaultState();
		
		close();
				
		waitForExtJSAjaxComplete(5);
		
		clickDeleteButton();
		
		waitForExtJSAjaxComplete(10);
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(10);
							
		String warningMsgActual = getWarningDialogTextMsg();
		
		if(!status)  {
			
			if(!build14){
				
				System.err.println(warningMsgActual);
				softAssert.assertTrue(warningMsgActual.contains("There are Energy Objects of this Type. You cannot delete it"),"Office Object Type Row Can not be Deleted");
			}
			
			else{
				System.err.println(warningMsgActual);
				softAssert.assertTrue(warningMsgActual.contains("You cannot delete Energy Object Type. There are Energy Objects of this Type"),"Office Object Type Row Can not be Deleted");
			}
		}
		
		else{
			
			softAssert.assertTrue(warningMsgActual.contains("You cannot delete the default Energy Object Type."),"Default Object Type Row Can not be Deleted");
		}
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(10);
		
		}
		softAssert.assertAll();
	}

}
