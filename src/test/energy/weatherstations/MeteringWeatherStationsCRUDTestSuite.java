package test.energy.weatherstations;

import java.io.IOException;




import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class MeteringWeatherStationsCRUDTestSuite extends
		MeteringWeatherStationsPageObject {




	 @Test(enabled=true)
	public void testAnalysisWeatherstationEdit() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Edit Weather Station " + " </span><br>",
				true);

		Reporter.log("User edits Weatherstation: C19912"  + " <br>",
				true);
		
		String uom = "°F";
		String reference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String description = "description";
		String timeZone = "(GMT+02:00) Athens, Bucharest, Istanbul";
		String status = "Active";
		String supplier = "My Company";
		String entryMethod = "Not calculated (user input)";
		String channelReference = "Mean Daily Temperature";
		String entryMethodChannel = "Manual";
		
		//String uomEdited = "°C"; 
		String referenceEdited = "test auto gau ed" + getCurrentDate().substring(getCurrentDate().length()-6);
		String codeEdited = "test auto gau" + getCurrentDate().substring(getCurrentDate().length()-6);
		String descriptionEdited = "description";
		String timeZoneEdited = "(GMT+02:00) Athens, Bucharest, Istanbul";
		String statusEdited = "Active";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("WeatherStationsEdit");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		openAnalysisEntity("Weather Stations");
		

		waitForExtJSAjaxComplete(20);
		
		clickAddButton(WEATHERSTATIONS_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
	
		waitForExtJSAjaxComplete(25);
		
		setCode(code);
		
		setReference(reference);
		
		setUOM(uom);
		
		waitForExtJSAjaxComplete(25);
		
		setStatus(status);
		
		waitForExtJSAjaxComplete(25);
		
		setTimeZone(timeZone);
		
		waitForExtJSAjaxComplete(25);
		
		setEntryMethod(entryMethod);
		
		waitForExtJSAjaxComplete(25);
		
		setDescription(description);
		
		setStatus(status);
		
		waitForExtJSAjaxComplete(25);
		
		setSupplier(supplier);
		
		waitForExtJSAjaxComplete(25);
		
		save();
		
		waitForExtJSAjaxComplete(25);
		
		//changeTab("channels");
		Assert.assertTrue(isRowInGridChanelPresent(channelReference), "Chanel with reference: " + channelReference + " was created.");
		Assert.assertTrue(isRowInGridChanelPresent(entryMethodChannel), "Chanel with entry method: " + entryMethodChannel + " was created.");
		
		close();
		
		Grid.checkRowInGriByTextValue(driver, reference);
		
		clickEditButton(WEATHERSTATIONS_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getReference(),reference, reference+" reference before edit is wrong");
		
		softAssert.assertEquals(getCode(),code, code+ " Code before edit is wrong");
		
		softAssert.assertEquals(getUOM(),uom, uom+ " UOM before edit is wrong");
		
		softAssert.assertEquals(getStatus(),status, status+ " status before edit is wrong");
		
		softAssert.assertEquals(getEntryMethod(),entryMethod, entryMethod+ " entryMethod before edit is wrong");
		
		softAssert.assertEquals(getDescription(),description, description+ " Description type before edit is wrong");
		
		softAssert.assertEquals(getSupplier(),supplier, supplier+ " Supplier before edit is wrong");
		
		softAssert.assertEquals(getTimeZone(),timeZone, timeZone+ " TimeZone before edit is wrong");
		
		setCode(codeEdited);
		
		setReference(referenceEdited);
		
		//setUOM(uomEdited);
		
		waitForExtJSAjaxComplete(25);
		
		setStatus(statusEdited);
		
		setDescription(descriptionEdited);
		
		waitForExtJSAjaxComplete(25);
		
		//setSupplier(supplierEdited);
		
		setTimeZone(timeZoneEdited);
		
		waitForExtJSAjaxComplete(25);
		
		saveClose();

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, referenceEdited,"@class", "x-grid3"), "edited weather station is not present");
		
		clickEditButton(WEATHERSTATIONS_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getReference(),referenceEdited, referenceEdited+" reference Edited is wrong");
		
		softAssert.assertEquals(getCode(),codeEdited, codeEdited+ " Code before edit is wrong");
		
		//softAssert.assertEquals(getUOM(),uomEdited, uomEdited+ " UOM is wrong");
		
		softAssert.assertEquals(getStatus(),statusEdited, statusEdited+ " status is wrong");
		
		softAssert.assertEquals(getDescription(),descriptionEdited, descriptionEdited+ " Description type is wrong");
		
		softAssert.assertEquals(getTimeZone(),timeZoneEdited, timeZoneEdited+ " TimeZone is wrong");
		
		softAssert.assertAll();

		close();
		
		Reporter.log("Weather Station"  + " was successfully edited", true);

	}

	@Test(enabled = true)
	public void testWeatherStationDelete() throws IllegalStateException, IOException {

		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Weather Station " + " </span><br>",
				true);

		Reporter.log("User deletes Weather Station: C19920 "  + " <br>",
				true);
		
		String reference = "test auto gau del" + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto gau del" + getCurrentDate().substring(getCurrentDate().length()-6);
		String entryMethod = "Equivalent Temperature";
		String channerlReference1 = "Mean Daily Temperature";
		String channerlReference2 = "Equivalent Temperature";
		String entryMethodChannel1 = "Manual";
		String entryMethodChannel2 = "Calculated";
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		openAnalysisEntity("Weather Stations");

		waitForExtJSAjaxComplete(20);
		
		clickAddButton(WEATHERSTATIONS_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setReference(reference);	
		
		setCode(code);
		
		setEntryMethod(entryMethod);
		
		waitForExtJSAjaxComplete(25);
		
		save();
		
		waitForExtJSAjaxComplete(25);
		
		//changeTab("channels");
		Assert.assertTrue(isRowInGridChanelPresent(channerlReference1), "Chanel with reference: " + channerlReference1 + "wasn't created.");
		Assert.assertTrue(isRowInGridChanelPresent(channerlReference2), "Chanel with reference: " + channerlReference2 + "wasn't created.");
		Assert.assertTrue(isRowInGridChanelPresent(entryMethodChannel1), "Chanel with entry method: " + entryMethodChannel1 + " wasn't created.");
		Assert.assertTrue(!isRowInGridChanelPresent(entryMethodChannel2), "Chanel with entry method: " + entryMethodChannel2 + " was created.");
		
		close();
		
		Grid.checkRowInGriByTextValue(driver, reference);
		
		clickDeleteButton(WEATHERSTATIONS_GRID_CLASS);
				
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, reference), channerlReference1+" reference after delete is present");
		
		Reporter.log("Weather Station was successfully deleted", true);
		
	}
	
	@Test(enabled = true)
	public void testWeatherStationEntryMethod() throws IllegalStateException, IOException {

		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Weather Station Entry Method check " + " </span><br>",
				true);

		Reporter.log("WeatherStation Entry Method check: C19921"  + " <br>",
				true);
		
		String reference = "test auto gau del" + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto gau del" + getCurrentDate().substring(getCurrentDate().length()-6);
		String entryMethod = "Equivalent Temperature";
		String channerlReference1 = "Mean Daily Temperature";
		String channerlReference2 = "Equivalent Temperature";
		String entryMethodChannel1 = "Manual";
		String entryMethodChannel2 = "Calculated";
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		openAnalysisEntity("Weather Stations");

		waitForExtJSAjaxComplete(20);
		
		clickAddButton(WEATHERSTATIONS_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setReference(reference);	
		
		setCode(code);
		
		setEntryMethod(entryMethod);
		
		waitForExtJSAjaxComplete(25);
		
		save();
		
		waitForExtJSAjaxComplete(25);
		
		//changeTab("channels");
		Assert.assertTrue(isRowInGridChanelPresent(channerlReference1), "Chanel with reference: " + channerlReference1 + "wasn't created.");
		Assert.assertTrue(isRowInGridChanelPresent(channerlReference2), "Chanel with reference: " + channerlReference2 + "wasn't created.");
		Assert.assertTrue(isRowInGridChanelPresent(entryMethodChannel1), "Chanel with entry method: " + entryMethodChannel1 + " wasn't created.");
		Assert.assertTrue(!isRowInGridChanelPresent(entryMethodChannel2), "Chanel with entry method: " + entryMethodChannel2 + " wasn't created.");
		close();
		
		Grid.checkRowInGriByTextValue(driver, reference);
		
		clickDeleteButton(WEATHERSTATIONS_GRID_CLASS);
				
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, reference), channerlReference1+" reference after delete is present");
		
		Reporter.log("Weather Station was successfully deleted", true);
		
	}

}
