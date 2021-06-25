package test.energy.commodities;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class ConfigurationCommoditySourcesCRUDTestSuite extends ConfigurationCommoditiesPageObject{
	
		@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
		public void testCommoditiesSourceCreateEditDelete() throws IOException  {

			Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Add/Edit/Delete Commodity Sources :c19910" + " </span><br>",
					true);

			Reporter.log("User adds/edits/deletes Commodity Source "  + " <br>",
					true);
			
			String reference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
			String code = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
			String commodityClass = "Waste";
			String sourceName = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
			String description = "A short description";
			String sourceNameEdited = "test auto ed" + getCurrentDate().substring(getCurrentDate().length()-6);
			String descriptionEdited = "Another short description edited";
			
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("CreateEditDeleteCommoditySource");

			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(20);
			
			expandConfiguration();
			
			waitForExtJSAjaxComplete(20);
			
			openConfigurationEntity("Commodities");		
			
			waitForExtJSAjaxComplete(20);
			
			clickButton(COMMODITY_GRID, "Add");
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
					
			setCode(code, DIALOG_COMMODITY);
			
			setReference(reference, DIALOG_COMMODITY);
						
			setClass(DIALOG_COMMODITY, commodityClass);
			
			waitForExtJSAjaxComplete(20);
			
			clickButton(DIALOG_COMMODITY, "Save");
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);

			clickEquivalenciesTab();
			
			waitForExtJSAjaxComplete(20);
			
			clickButton(DIALOG_COMMODITY, "Add");
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			setSourceName(sourceName, DIALOG_CMDT_SOURCE);
			
			setDescription(description, DIALOG_CMDT_SOURCE);
			
			Reporter.log("Check if Commodity is prefilled with "+reference, true);
			softAssert.assertEquals(getCommodity(DIALOG_CMDT_SOURCE), reference, reference+" prefilled Commodity is wrong");
			
			saveClose(DIALOG_CMDT_SOURCE);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			setSourceInCombo(DIALOG_COMMODITY, sourceName);
			
			waitForExtJSAjaxComplete(20);
			
			clickButton(DIALOG_COMMODITY, "Edit");
			
			waitForExtJSAjaxComplete(20);
			
			Reporter.log("Check if Source Name, Commodity and Description are correct after creation", true);
			softAssert.assertEquals(getSourceName(DIALOG_CMDT_SOURCE), sourceName, sourceName+" Source Name after creation is wrong");
			
			softAssert.assertEquals(getCommodity(DIALOG_CMDT_SOURCE), reference, reference+" Commodity after creation is wrong");
			
			softAssert.assertEquals(getDescription(DIALOG_CMDT_SOURCE), description, description+" Description after creation is wrong");
			
			setSourceName(sourceNameEdited, DIALOG_CMDT_SOURCE);
			
			setDescription(descriptionEdited, DIALOG_CMDT_SOURCE);
			
			saveClose(DIALOG_CMDT_SOURCE);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			setSourceInCombo(DIALOG_COMMODITY, sourceNameEdited);
			
			waitForExtJSAjaxComplete(20);
			
			clickButton(DIALOG_COMMODITY, "Edit");
			
			waitForExtJSAjaxComplete(20);
			
			Reporter.log("Check if Source Name, Commodity and Description are correct after editing", true);
			softAssert.assertEquals(getSourceName(DIALOG_CMDT_SOURCE), sourceNameEdited, sourceNameEdited+" Source Name after editing is wrong");
			
			softAssert.assertEquals(getCommodity(DIALOG_CMDT_SOURCE), reference, reference+" Commodity after editing is wrong");
			
			softAssert.assertEquals(getDescription(DIALOG_CMDT_SOURCE), descriptionEdited, descriptionEdited+" Description after editing is wrong");
			
			close(DIALOG_CMDT_SOURCE);

			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			setSourceInCombo(DIALOG_COMMODITY, sourceNameEdited);
			
			waitForExtJSAjaxComplete(20);
			
			clickButton(DIALOG_COMMODITY, "Delete");
			
			clickOnDialogButton("Yes");
			Reporter.log("Confirm deletion", true);
			
			waitForExtJSAjaxComplete(20);
			
			Reporter.log("Check if Source Name appears in combobox after deletion", true);
			softAssert.assertFalse(validateSourceInCombo(DIALOG_COMMODITY, sourceNameEdited), sourceNameEdited+" Source Name is present in combobox, Source wasn't deleted");
			
			waitForExtJSAjaxComplete(20);
			
			close(DIALOG_COMMODITY);
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			Grid.checkRowInGriByTextValueAndClick(driver, COMMODITY_GRID, reference);
			
			clickButton(COMMODITY_GRID, "Delete");
			
			clickOnDialogButton("OK");
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertAll();
							
		}
		
	
}
