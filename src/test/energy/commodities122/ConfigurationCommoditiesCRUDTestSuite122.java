package test.energy.commodities122;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class ConfigurationCommoditiesCRUDTestSuite122 extends ConfigurationCommoditiesPageObject {
	
		@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
		public void testCommoditiesCreateEdit() throws IOException  {

			Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Add/Edit Commodities : c11662" + " </span><br>",
					true);

			Reporter.log("User adds/edits Commodities "  + " <br>",
					true);
				
			String reference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
			String code = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
			String commodityClass = "Waste";
			String defaultUOM = "kilogram";
			
			String referenceEdited = "test auto ed" + getCurrentDate().substring(getCurrentDate().length()-6);
			String codeEdited = "test auto ed" + getCurrentDate().substring(getCurrentDate().length()-6);
			String defaultUOMEdited = "gram";	
			
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("CreateEditCommodity");

			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(20);
			
			expandConfiguration();
			
			waitForExtJSAjaxComplete(20);
			
			openConfigurationEntity("Commodities");		
			
			waitForExtJSAjaxComplete(20);
			
			clickButton(COMMODITY_GRID, "Add");
			
			waitForExtJSAjaxComplete(20);
					
			setCode(code, DIALOG_COMMODITY);
			
			setReference(reference, DIALOG_COMMODITY);
						
			setClass(DIALOG_COMMODITY, commodityClass);
			
			waitForExtJSAjaxComplete(20);
			
			Reporter.log("Check if Default UOM is prefilled with "+defaultUOM, true);	
			softAssert.assertEquals(getDefaultUOM(DIALOG_COMMODITY),defaultUOM, defaultUOM+" defaultUOM is not prefilled or is prefilled with wrong UOM");
						
			waitForExtJSAjaxComplete(20);
			
			saveClose(DIALOG_COMMODITY);
			
			waitForExtJSAjaxComplete(20);
					
			Grid.checkRowInGriByTextValueAndClick(driver, code);
			//div[contains(@class,'commodity_overview')]//button[contains(@class,'x-btn-text')and contains(text(),'Edit')]
			clickButton(COMMODITY_GRID, "Edit");
			
			waitForExtJSAjaxComplete(20);
			
			Reporter.log("Check if Code/Reference/Default UOM/Class Commodity are correct after creation", true);				
			softAssert.assertEquals(getCode(DIALOG_COMMODITY),code, code+" Code after creation is wrong");
			
			softAssert.assertEquals(getReference(DIALOG_COMMODITY),reference, reference+" Reference after creation is wrong");
					
			softAssert.assertEquals(getDefaultUOM(DIALOG_COMMODITY),defaultUOM, defaultUOM+" Default UOM after creation is wrong");
			
			softAssert.assertEquals(getClass(DIALOG_COMMODITY),commodityClass, commodityClass+" Commodity Class after creation is wrong");
		
			setCode(codeEdited, DIALOG_COMMODITY);
			
			setReference(referenceEdited, DIALOG_COMMODITY);
			
			setDefaultUOM(defaultUOMEdited, DIALOG_COMMODITY);
			
			waitForExtJSAjaxComplete(20);
			
			saveClose(DIALOG_COMMODITY);
			
			Grid.checkRowInGriByTextValueAndClick(driver, codeEdited);
			
			clickButton(COMMODITY_GRID, "Edit");
			
			waitForExtJSAjaxComplete(20);
			
			Reporter.log("Check if Code/Reference/Default UOM/Commodity Class are correct after editing", true);			
			softAssert.assertEquals(getCode(DIALOG_COMMODITY),codeEdited, codeEdited+" Code after edit is wrong");
			
			softAssert.assertEquals(getReference(DIALOG_COMMODITY),referenceEdited, referenceEdited+" Reference after edit is wrong");
					
			softAssert.assertEquals(getDefaultUOM(DIALOG_COMMODITY),defaultUOMEdited, defaultUOMEdited+" Default UOM Class after edit is wrong");
			
			softAssert.assertEquals(getClass(DIALOG_COMMODITY),commodityClass, commodityClass+" Commodity Class after edit is wrong");
			
			softAssert.assertAll();
			
			Reporter.log("Commodity was succesfully created and edited", true);
		}
			
		@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
		public void testCommoditiesDelete() throws IOException  {

			Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Delete Commodity : c11663" + " </span><br>",
					true);

			Reporter.log("User delete commodity: " + " <br>",
					true);
				
			String code = "my auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
			String reference = "test auto del" + getCurrentDate().substring(getCurrentDate().length()-5);	
			String commodityClass = "Waste";
				
			waitAndClick(XPATH_ENERGY);
				
			waitForExtJSAjaxComplete(20);
				
			expandConfiguration();
				
			waitForExtJSAjaxComplete(20);
				
			openConfigurationEntity("Commodities");	
			
			waitForExtJSAjaxComplete(20);
				
			clickButton(COMMODITY_GRID, "Add");
				
			waitForExtJSAjaxComplete(20);
						
			setCode(code, DIALOG_COMMODITY);
				
			setReference(reference, DIALOG_COMMODITY);
				
			setClass(DIALOG_COMMODITY, commodityClass);
				
			waitForExtJSAjaxComplete(20);
																	
			saveClose(DIALOG_COMMODITY);
				
			waitForExtJSAjaxComplete(20);
				
			Grid.checkRowInGriByTextValueAndClick(driver, code);
				
			clickButton(COMMODITY_GRID, "Delete");
			
			clickOnDialogButton("OK");
			Reporter.log("Confirm deletion", true);
				
			waitForMaskDisappear();
				
			waitForExtJSAjaxComplete(20);
			
			Reporter.log("Check if Commodity is present in grid after deletion", true);
			Assert.assertTrue(Grid.isRowInGridAbsent(driver, code), code+" code after delete is present");
				
			Reporter.log("Commodity was succesfully deleted", true);
		}	
}

