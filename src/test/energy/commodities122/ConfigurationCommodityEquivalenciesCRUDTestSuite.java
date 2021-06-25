package test.energy.commodities122;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class ConfigurationCommodityEquivalenciesCRUDTestSuite extends ConfigurationCommoditiesPageObject{
	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testCommoditiesEquivalenciesCreateEdit() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Add/Edit Commodity Equivalencies : c19880 " + " </span><br>",
				true);

		Reporter.log("User adds/edits Commodity Equivalencies "  + " <br>",
				true);
		
		//String reference = "preCMDTref";
		String lookupId;
		
		String code = "preCMDTcode";
		String sourceName = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String date = "01-01-2010";
		String value = "1.56";
		String calEqUOM = "calorie";
		String calBaseUOM = "cubic centimeter (cc)"; //change later
		String emEqUOM = "kilogram";
		String emBaseUOM = "cubic inch";
		String note = "A short note";
		
		String dateEdited = "28-11-2012";
		String valueEdited = "0.25";
		String calEqUOMEdited = "kiloWatthour";
		String calBaseUOMEdited = "centiliter";
		String emEqUOMEdited = "ounce";
		String emBaseUOMEdited = "centiliter";
		String noteEdited = "Another edited short note";
		
		String dateSecond = "01-01-2013";

		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("CreateEditCommodityEquivalencies");

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(20);
		
		openConfigurationEntity("Commodities");		
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, code);
		
		clickButton(COMMODITY_GRID, "Edit");
		
		waitForExtJSAjaxComplete(20);
		
		clickEquivalenciesTab();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//Calorific Value
		
		//Add Source
		
		clickButton(DIALOG_COMMODITY, "Add");
		
		waitForExtJSAjaxComplete(20);
		
		setSourceName(sourceName, DIALOG_CMDT_SOURCE);
		
		saveClose(DIALOG_CMDT_SOURCE);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//Add Calorific Value
		
		setEquivalencyInCombo(DIALOG_COMMODITY, "Calorific Value");

		waitForExtJSAjaxComplete(20);
		
		clickButtonOnFieldSet("Add", "Equivalency Values");
		
		waitForExtJSAjaxComplete(20);
				
		//Lookup limitation check START
		openEquivalencyLookup();
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		Reporter.log("Check if Equivalency UOM lookup is limited by equivalency type", true);
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "kilogram", "Reference"), "Equivalency UOM lookup is not limited, kilogram UOM is present in lookup");//Mass
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "hectare", "Reference"), "Equivalency UOM lookup is not limited, hectare UOM is present in lookup");//Area
				
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "kilometer", "Reference"), "Equivalency UOM lookup is not limited, kilometer UOM is present in lookup");//Length
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "piece", "Reference"), "Equivalency UOM lookup is not limited, piece UOM is present in lookup");//Quantity
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "minute", "Reference"), "Equivalency UOM lookup is not limited, minute UOM is present in lookup");//Time
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "centiliter", "Reference"), "Equivalency UOM lookup is not limited, centiliter UOM is present in lookup");//Volume
		
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		//Lookup limitation check END
		
		//Lookup limitation check START
		openCommodityBaseLookup();

		lookupId = getXWindowId("Select a Unit Of Measure");

		Reporter.log("Check if Commodity Base UOM lookup is limited by Commodity UOM Class", true);
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "kilogram", "Reference"), "Equivalency UOM lookup is not limited, kilogram UOM is present in lookup");//Mass

		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "hectare", "Reference"), "Equivalency UOM lookup is not limited, hectare UOM is present in lookup");//Area

		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "Newtonmeter", "Reference"), "Equivalency UOM lookup is not limited, Newtonmeter UOM is present in lookup");//Energy

		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "kilometer", "Reference"), "Equivalency UOM lookup is not limited, kilometer UOM is present in lookup");//Length

		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "piece", "Reference"), "Equivalency UOM lookup is not limited, piece UOM is present in lookup");//Quantity

		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "minute", "Reference"), "Equivalency UOM lookup is not limited, minute UOM is present in lookup");//Time

		clickCancelXwindow();

		waitForExtJSAjaxComplete(20);
		//Lookup limitation check END
		
		setDate(date, DIALOG_CMDT_EQUIVALENCY);
		
		setValue(value, DIALOG_CMDT_EQUIVALENCY);
		
		setEquivalencyUOM(calEqUOM, DIALOG_CMDT_EQUIVALENCY);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityBaseUOM(calBaseUOM, DIALOG_CMDT_EQUIVALENCY);
		
		waitForExtJSAjaxComplete(20);
		
		setNote(note, DIALOG_CMDT_EQUIVALENCY);
		
		saveClose(DIALOG_CMDT_EQUIVALENCY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//Check added Calorific Value and edit
		
		setEquivalencyInCombo(DIALOG_COMMODITY, "Calorific Value");
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Calorific Value with Date - "+date+" is present in grid", true);
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date, "@class", DIALOG_COMMODITY), "Can't find Calorific Value in grid with "+date+" Date");
		
		waitForExtJSAjaxComplete(20);
		
		clickButtonOnFieldSet("Edit", "Equivalency Values");
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Date, Value, Notes, Equivalence UOM, Commdoity Base UOM are correct after creation", true);
		softAssert.assertEquals(getDate(DIALOG_CMDT_EQUIVALENCY), date, date+ " Date after creation is wrong");
		
		softAssert.assertEquals(getValue(DIALOG_CMDT_EQUIVALENCY), value, value+ " Value after creation is wrong");
		
		softAssert.assertEquals(getEquivalencyUOM(DIALOG_CMDT_EQUIVALENCY), calEqUOM, calEqUOM+ " Equivalency UOM after creation is wrong");
		
		softAssert.assertEquals(getCommodityBaseUOM(DIALOG_CMDT_EQUIVALENCY), calBaseUOM, calBaseUOM+ " Commodity Base UOM after creation is wrong");
		
		softAssert.assertEquals(getNote(DIALOG_CMDT_EQUIVALENCY), note, note+ " Note after creation is wrong");
		
		setDate(dateEdited, DIALOG_CMDT_EQUIVALENCY);
		
		setValue(valueEdited, DIALOG_CMDT_EQUIVALENCY);
		
		setEquivalencyUOM(calEqUOMEdited, DIALOG_CMDT_EQUIVALENCY);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityBaseUOM(calBaseUOMEdited, DIALOG_CMDT_EQUIVALENCY);
		
		waitForExtJSAjaxComplete(20);
		
		setNote(noteEdited, DIALOG_CMDT_EQUIVALENCY);
		
		saveClose(DIALOG_CMDT_EQUIVALENCY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//Check edited Calorific Value
		
		setEquivalencyInCombo(DIALOG_COMMODITY, "Calorific Value");
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Calorific Value with Date - "+dateEdited+" is present in grid", true);
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, dateEdited, "@class", DIALOG_COMMODITY), "Can't find Calorific Value in grid with "+dateEdited+" Date after editing");
			
		clickButtonOnFieldSet("Edit", "Equivalency Values");
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Date, Value, Notes, Equivalence UOM, Commdoity Base UOM are correct after editing", true);
		softAssert.assertEquals(getDate(DIALOG_CMDT_EQUIVALENCY), dateEdited, dateEdited+ " Date after editing is wrong");
		
		softAssert.assertEquals(getValue(DIALOG_CMDT_EQUIVALENCY), valueEdited, valueEdited+ " Value after editing is wrong");
		
		softAssert.assertEquals(getEquivalencyUOM(DIALOG_CMDT_EQUIVALENCY), calEqUOMEdited, calEqUOMEdited+ " Equivalency UOM after editing is wrong ");
		
		softAssert.assertEquals(getCommodityBaseUOM(DIALOG_CMDT_EQUIVALENCY), calBaseUOMEdited, calBaseUOMEdited+ " Commodity Base UOM after editing is wrong");
		
		softAssert.assertEquals(getNote(DIALOG_CMDT_EQUIVALENCY), noteEdited, noteEdited+ " Note after editing is wrong");
		
		close(DIALOG_CMDT_EQUIVALENCY);
		
		//Emissions CO2
		
		//Add Emissions CO2
		
		waitForExtJSAjaxComplete(20);
		
		setEquivalencyInCombo(DIALOG_COMMODITY, "Emissions CO2");

		waitForExtJSAjaxComplete(20);
				
		clickButtonOnFieldSet("Add", "Equivalency Values");
		
		waitForExtJSAjaxComplete(20);
		
		//Lookup limitation check START
		openEquivalencyLookup();
		
		lookupId = getXWindowId("Select a Unit Of Measure");
		
		Reporter.log("Check if Equivalency UOM lookup is limited by equivalency type", true);
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "hectare", "Reference"), "Equivalency UOM lookup is not limited, hectare UOM is present in lookup");//Area
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "Newtonmeter", "Reference"), "Equivalency UOM lookup is not limited, Newtonmeter UOM is present in lookup");//Energy
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "kilometer", "Reference"), "Equivalency UOM lookup is not limited, kilometer UOM is present in lookup");//Length
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "piece", "Reference"), "Equivalency UOM lookup is not limited, piece UOM is present in lookup");//Quantity
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "minute", "Reference"), "Equivalency UOM lookup is not limited, minute UOM is present in lookup");//Time
		
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "centiliter", "Reference"), "Equivalency UOM lookup is not limited, centiliter UOM is present in lookup");//Volume
		
		clickCancelXwindow();
		
		waitForExtJSAjaxComplete(20);
		//Lookup limitation check END
		
		//Lookup limitation check START
		openCommodityBaseLookup();

		lookupId = getXWindowId("Select a Unit Of Measure");

		Reporter.log("Check if Commodity Base UOM lookup is limited by Commodity UOM Class", true);
		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "kilogram", "Reference"), "Equivalency UOM lookup is not limited, kilogram UOM is present in lookup");//Mass

		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "hectare", "Reference"), "Equivalency UOM lookup is not limited, hectare UOM is present in lookup");//Area

		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "Newtonmeter", "Reference"), "Equivalency UOM lookup is not limited, Newtonmeter UOM is present in lookup");//Energy

		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "kilometer", "Reference"), "Equivalency UOM lookup is not limited, kilometer UOM is present in lookup");//Length

		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "piece", "Reference"), "Equivalency UOM lookup is not limited, piece UOM is present in lookup");//Quantity

		softAssert.assertFalse(filterGridAndSearchForValue("@id", lookupId, "minute", "Reference"), "Equivalency UOM lookup is not limited, minute UOM is present in lookup");//Time

		clickCancelXwindow();

		waitForExtJSAjaxComplete(20);
		//Lookup limitation check END
		
		setDate(date, DIALOG_CMDT_EQUIVALENCY);
		
		setValue(value, DIALOG_CMDT_EQUIVALENCY);
		
		setEquivalencyUOM(emEqUOM, DIALOG_CMDT_EQUIVALENCY);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityBaseUOM(emBaseUOM, DIALOG_CMDT_EQUIVALENCY);
		
		waitForExtJSAjaxComplete(20);
		
		setNote(note, DIALOG_CMDT_EQUIVALENCY);
		
		saveClose(DIALOG_CMDT_EQUIVALENCY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//Check added Emissions CO2 and edit
		
		setEquivalencyInCombo(DIALOG_COMMODITY, "Emissions CO2");
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Emissions CO2 with Date - "+date+" is present in grid", true);
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date, "@class", DIALOG_COMMODITY), "Can't find Emissions CO2 in grid with "+date+" Date");
		
		waitForExtJSAjaxComplete(20);
		
		clickButtonOnFieldSet("Edit", "Equivalency Values");
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Date, Value, Notes, Equivalence UOM, Commdoity Base UOM are correct after creation", true);
		softAssert.assertEquals(getDate(DIALOG_CMDT_EQUIVALENCY), date, date+ " Date after creation is wrong");
		
		softAssert.assertEquals(getValue(DIALOG_CMDT_EQUIVALENCY), value, value+ " Value after creation is wrong");
		
		softAssert.assertEquals(getEquivalencyUOM(DIALOG_CMDT_EQUIVALENCY), emEqUOM, emEqUOM+ " Equivalency UOM after creation is wrong");
		
		softAssert.assertEquals(getCommodityBaseUOM(DIALOG_CMDT_EQUIVALENCY), emBaseUOM, emBaseUOM+ " Commodity Base UOM after creation is wrong");
		
		softAssert.assertEquals(getNote(DIALOG_CMDT_EQUIVALENCY), note, note+ " Note after creation is wrong");
		
		setDate(dateEdited, DIALOG_CMDT_EQUIVALENCY);
		
		setValue(valueEdited, DIALOG_CMDT_EQUIVALENCY);
		
		setEquivalencyUOM(emEqUOMEdited, DIALOG_CMDT_EQUIVALENCY);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityBaseUOM(emBaseUOMEdited, DIALOG_CMDT_EQUIVALENCY);
		
		waitForExtJSAjaxComplete(20);
		
		setNote(noteEdited, DIALOG_CMDT_EQUIVALENCY);
		
		saveClose(DIALOG_CMDT_EQUIVALENCY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//Check edited Emissions CO2
		
		setEquivalencyInCombo(DIALOG_COMMODITY, "Emissions CO2");
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Emissions CO2 with Date - "+date+" is present in grid", true);
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, dateEdited, "@class", DIALOG_COMMODITY), "Can't find Emissions CO2 in grid with "+dateEdited+" Date after editing");
				
		clickButtonOnFieldSet("Edit", "Equivalency Values");
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Date, Value, Notes, Equivalence UOM, Commdoity Base UOM are correct after editing", true);
		softAssert.assertEquals(getDate(DIALOG_CMDT_EQUIVALENCY), dateEdited, dateEdited+ " Date after editing is wrong");
		
		softAssert.assertEquals(getValue(DIALOG_CMDT_EQUIVALENCY), valueEdited, valueEdited+ " Value after editing is wrong");
		
		softAssert.assertEquals(getEquivalencyUOM(DIALOG_CMDT_EQUIVALENCY), emEqUOMEdited, emEqUOMEdited+ " Equivalency UOM after editing is wrong");
		
		softAssert.assertEquals(getCommodityBaseUOM(DIALOG_CMDT_EQUIVALENCY), emBaseUOMEdited, emBaseUOMEdited+ " Commodity Base UOM after editing is wrong");
		
		softAssert.assertEquals(getNote(DIALOG_CMDT_EQUIVALENCY), noteEdited, noteEdited+ " Note after editing is wrong");
		
		close(DIALOG_CMDT_EQUIVALENCY);
		
		//Primary Energy
		
		//Add Primary Energy
		
		setEquivalencyInCombo(DIALOG_COMMODITY, "Primary Energy");

		waitForExtJSAjaxComplete(20);
		
		clickButtonOnFieldSet("Add", "Equivalency Values");
		
		waitForExtJSAjaxComplete(20);
		
		setDate(date, DIALOG_CMDT_EQUIVALENCY);
		
		setValue(value, DIALOG_CMDT_EQUIVALENCY);
		
		setNote(note, DIALOG_CMDT_EQUIVALENCY);
		
		saveClose(DIALOG_CMDT_EQUIVALENCY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//Check added Primary Energy and edit
		
		setEquivalencyInCombo(DIALOG_COMMODITY, "Primary Energy");
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Primary Energy with Date - "+date+" is present in grid", true);
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, date, "@class", DIALOG_COMMODITY), "Can't find Primary Energy in grid with "+date+" Date");
		
		waitForExtJSAjaxComplete(20);
		
		clickButtonOnFieldSet("Edit", "Equivalency Values");
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Date, Value, Notes are correct after creation", true);
		softAssert.assertEquals(getDate(DIALOG_CMDT_EQUIVALENCY), date, date+ " Date after creation is wrong");
		
		softAssert.assertEquals(getValue(DIALOG_CMDT_EQUIVALENCY), value, value+ " Value after creation is wrong");

		softAssert.assertEquals(getNote(DIALOG_CMDT_EQUIVALENCY), note, note+ " Note after creation is wrong");
		
		setDate(dateEdited, DIALOG_CMDT_EQUIVALENCY);
		
		setValue(valueEdited, DIALOG_CMDT_EQUIVALENCY);

		setNote(noteEdited, DIALOG_CMDT_EQUIVALENCY);
		
		saveClose(DIALOG_CMDT_EQUIVALENCY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		//Check edited Primary Energy
		
		setEquivalencyInCombo(DIALOG_COMMODITY, "Primary Energy");
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Primary Energy with Date - "+date+" is present in grid", true);
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, dateEdited, "@class", DIALOG_COMMODITY), "Can't find Primary Energy in grid with "+dateEdited+" Date after editing");
				
		clickButtonOnFieldSet("Edit", "Equivalency Values");
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Date, Value, Notes are correct after creation", true);
		softAssert.assertEquals(getDate(DIALOG_CMDT_EQUIVALENCY), dateEdited, dateEdited+ " Date after editing is wrong");
		
		softAssert.assertEquals(getValue(DIALOG_CMDT_EQUIVALENCY), valueEdited, valueEdited+ " Value after editing is wrong");
			
		softAssert.assertEquals(getNote(DIALOG_CMDT_EQUIVALENCY), noteEdited, noteEdited+ " Note after editing is wrong");
		
		close(DIALOG_CMDT_EQUIVALENCY);
		
		//Add second Calorific Value and check read-only fields
		
		setEquivalencyInCombo(DIALOG_COMMODITY, "Calorific Value");

		waitForExtJSAjaxComplete(20);
		
		clickButtonOnFieldSet("Add", "Equivalency Values");
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Equivalence UOM and Commodity Base UOM are read-only", true);		
		softAssert.assertTrue(checkEqUOMFieldIsReadOnly(), "Equivalency UOM is not read-only");
		
		softAssert.assertTrue(checkBaseUOMFieldIsReadOnly(), "Commodity Base UOM is not read-only");
		
		setDate(dateSecond, DIALOG_CMDT_EQUIVALENCY);
		
		setValue(value, DIALOG_CMDT_EQUIVALENCY);
								
		saveClose(DIALOG_CMDT_EQUIVALENCY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setEquivalencyInCombo(DIALOG_COMMODITY, "Calorific Value");
		
		waitForExtJSAjaxComplete(20);
		
		clickButtonOnFieldSet("Edit", "Equivalency Values");
		
		Reporter.log("Check if Equivalence UOM and Commodity Base UOM are read-only", true);	
		softAssert.assertTrue(checkEqUOMFieldIsReadOnly(), "Equivalency UOM is not read-only");
		
		softAssert.assertTrue(checkBaseUOMFieldIsReadOnly(), "Commodity Base UOM is not read-only");
		
		close(DIALOG_CMDT_EQUIVALENCY);
		
		waitForExtJSAjaxComplete(20);
		
		//Add second Emissions CO2 and check read-only fields
		
		setEquivalencyInCombo(DIALOG_COMMODITY, "Emissions CO2");

		waitForExtJSAjaxComplete(20);
		
		clickButtonOnFieldSet("Add", "Equivalency Values");
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if Equivalence UOM and Commodity Base UOM are read-only", true);	
		softAssert.assertTrue(checkEqUOMFieldIsReadOnly(), "Equivalency UOM is not read-only");
		
		softAssert.assertTrue(checkBaseUOMFieldIsReadOnly(), "Commodity Base UOM is not read-only");
		
		setDate(dateSecond, DIALOG_CMDT_EQUIVALENCY);
		
		setValue(value, DIALOG_CMDT_EQUIVALENCY);
						
		saveClose(DIALOG_CMDT_EQUIVALENCY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setEquivalencyInCombo(DIALOG_COMMODITY, "Emissions CO2");
		
		waitForExtJSAjaxComplete(20);
		
		clickButtonOnFieldSet("Edit", "Equivalency Values");
		
		Reporter.log("Check if Equivalence UOM and Commodity Base UOM are read-only", true);	
		softAssert.assertTrue(checkEqUOMFieldIsReadOnly(), "Equivalency UOM is not read-only");
		
		softAssert.assertTrue(checkBaseUOMFieldIsReadOnly(), "Commodity Base UOM is not read-only");
		
		close(DIALOG_CMDT_EQUIVALENCY);
		
		waitForExtJSAjaxComplete(20);
		
		//Add second Primary Energy
		
		setEquivalencyInCombo(DIALOG_COMMODITY, "Primary Energy");

		waitForExtJSAjaxComplete(20);
				
		clickButtonOnFieldSet("Add", "Equivalency Values");
				
		waitForExtJSAjaxComplete(20);
				
		setDate(dateSecond, DIALOG_CMDT_EQUIVALENCY);
				
		setValue(value, DIALOG_CMDT_EQUIVALENCY);
				
		//setEquivalencyUOM(calEqUOM, DIALOG_CMDT_EQUIVALENCY);
						
		//setCommodityBaseUOM(calBaseUOM, DIALOG_CMDT_EQUIVALENCY);
								
		saveClose(DIALOG_CMDT_EQUIVALENCY);
				
		waitForExtJSAjaxComplete(20);
				
		waitForExtJSAjaxComplete(20);
		
		//Check current Equivalencies
		
		setEquivalencyInCombo(DIALOG_COMMODITY, "Current Equivalency Values");
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if last records for each equivalency are displayed in grid", true);	
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Calorific Value|"+dateSecond+"|"+value, "@class", DIALOG_COMMODITY), "Calorific Value "+dateSecond+" "+value+" - record is not present in Current Equivalencies grid");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Emissions CO2|"+dateSecond+"|"+value, "@class", DIALOG_COMMODITY), "Emissions CO2 "+dateSecond+" "+value+" - record is not present in Current Equivalencies grid");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Primary Energy|"+dateSecond+"|"+value, "@class", DIALOG_COMMODITY), "Primary Energy "+dateSecond+" "+value+" - record is not present in Current Equivalencies grid");
		
		//Check all Equivalencies
		
		setEquivalencyInCombo(DIALOG_COMMODITY, "All Equivalency Values");
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if all records for each equivalency are displayed in grid", true);	
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Calorific Value|"+dateSecond+"|"+value, "@class", DIALOG_COMMODITY), "Calorific Value "+dateSecond+" "+value+" - record is not present in All Equivalencies grid");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver,"Emissions CO2|"+dateSecond+"|"+value, "@class", DIALOG_COMMODITY), "Emissions CO2 "+dateSecond+" "+value+" - record is not present in All Equivalencies grid");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver,"Primary Energy|"+dateSecond+"|"+value, "@class", DIALOG_COMMODITY), "Primary Energy "+dateSecond+" "+value+" - record is not present in All Equivalencies grid");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver,"Calorific Value|"+dateEdited+"|"+valueEdited, "@class", DIALOG_COMMODITY), "Calorific Value "+dateSecond+" "+value+" - record is not present in All Equivalencies grid");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver,"Emissions CO2|"+dateEdited+"|"+valueEdited, "@class", DIALOG_COMMODITY), "Emissions CO2 "+dateSecond+" "+value+" - record is not present in All Equivalencies grid");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver,"Primary Energy|"+dateEdited+"|"+valueEdited, "@class", DIALOG_COMMODITY), "Primary Energy "+dateSecond+" "+value+" - record is not present in All Equivalencies grid");
				
		softAssert.assertAll();

	
	}


}
