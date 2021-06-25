package test.energy.navigator122;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class LinkWeatherStationToLocationTestSuite extends
	LinkWeatherStationToLocationPageObject {


	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void LinkWeatherStationToLocationWithoutCDDHDDChannel() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: LinkWeatherStationToLocationWithoutCDDHDDChannel: C21536" + " </span><br>",
				true);

		Reporter.log("User LinkWeatherStationToLocationWithoutCDDHDDChannel"  + " <br>",
				true);
		
		String locationType = "Building";
		String location = "slnmEnrgBuilding1";
		String weatherStation = "1preWeatherStationLink";
		String warningMessage = "selected Weather Station";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("LinkWeatherStationToLocationWithoutCDDHDDChannel");

		//Navigate to Meters Overview
	
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		setLocationValueTreeLookup(location);
		
		waitForExtJSAjaxComplete(20);
		
		String panelID = getXPanelId(locationType + " \"" +location + "\"");
		
		changeTab(panelID, "Weather");
		
		waitForExtJSAjaxComplete(20);
		
		ClickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		boolean bRes = verifyWarningDialogTextMessage(warningMessage);
		
		if (bRes) {
			try {
				clickOnDialogButton("OK");
			} catch (Exception e) {}
		}
		
		waitForExtJSAjaxComplete(50);
		
		setInheritWSFromParentObject(false);
		
		setWeatherStation(weatherStation);
		
		waitForExtJSAjaxComplete(50);
		
		bRes = verifyWarningDialogTextMessage(warningMessage);
		
		softAssert.assertEquals(true, bRes, "Warning message is correct ");
		
		if (bRes) {
			try {
				clickOnDialogButton("OK");
			} catch (Exception e) {}
		}
		
		save();
		
		waitForExtJSAjaxComplete(50);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, location,"@class", "crud-form"), "Weather station was selected.");
		
		close();
		
		waitForExtJSAjaxComplete(50);
		
		panelID = getXPanelId(locationType + " \"" +location + "\"");
		
		softAssert.assertEquals(getWeatherInformation("Inherit From Parent Object"), "No", "Is inherit No :");
		softAssert.assertEquals(getWeatherInformation("Weather Station"), weatherStation, weatherStation + " weatherstation is present ");
		softAssert.assertEquals(getWeatherInformation("Heating base temperature"), "16.5 °C (Not supported by the selected Weather Station)", "Heating base temperature is correct.");
		softAssert.assertEquals(getWeatherInformation("Cooling base temperature"), "16.5 °C (Not supported by the selected Weather Station)", "Cooling base temperature is correct.");
		softAssert.assertEquals(getWeatherInformation("HDD Reference"), "Standard (No data is available)", "Standard (No data is available)" + " HDD regerence is correct.");
		softAssert.assertEquals(getWeatherInformation("CDD Reference"), "Standard (No data is available)", "Standard (No data is available)" + " CDD regerence is correct.");
		
		ClickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		boolean bExist = verifyWarningDialogTextMessage(warningMessage);
		
		softAssert.assertEquals(true, bExist, "Warning message is correct ");
		
		if (bExist) {
			try {
				clickOnDialogButton("OK");
			} catch (Exception e) {}
		}
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, location,"@class", "crud-form"), "Weather station was selected.");
		
		close();
		
		waitForExtJSAjaxComplete(50);
		
		softAssert.assertAll();
		
		Reporter.log("Weather station was successfully linked to location.", true);
	 }
	 
	 @Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void LinkWeatherStationToLocationBuilding() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Link Weather Station To Location: C22079" + " </span><br>",
				true);

		Reporter.log("User Link Weather Station To Location"  + " <br>",
				true);
		
		String locationType = "Building";
		String location = "slnmEnrgBuilding1";
		String weatherStation = "1preWeatherStationLinkChannel";
		String warningMessage = "Selected Weather Station has no HDD or CDD channels";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("LinkWeatherStationToLocationBuilding");

		//Navigate to Meters Overview
	
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		setLocationValueTreeLookup(location);
		
		waitForExtJSAjaxComplete(20);
		
		String panelID = getXPanelId(locationType + " \"" +location + "\"");
		
		changeTab(panelID, "Weather");
		
		waitForExtJSAjaxComplete(20);
		
		ClickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		setInheritWSFromParentObject(false);
		
		waitForExtJSAjaxComplete(20);
		
		boolean bRes = verifyWarningDialogTextMessage(warningMessage);
		
		if (bRes) {
			try {
				clickOnDialogButton("OK");
			} catch (Exception e) {}
		}
		
		setWeatherStation(weatherStation);
		
		waitForExtJSAjaxComplete(50);
		
		bRes = verifyWarningDialogTextMessage(warningMessage);
		
		softAssert.assertEquals(false, bRes, "Warning message is correct ");
		
		if (bRes) {
			try {
				clickOnDialogButton("OK");
			} catch (Exception e) {}
		}
		
		save();
		
		waitForExtJSAjaxComplete(50);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, location,"@class", "crud-form"), "Weather station was selected.");
		
		close();
		
		waitForExtJSAjaxComplete(50);
		
		panelID = getXPanelId(locationType + " \"" +location + "\"");
		
		softAssert.assertEquals(getWeatherInformation("Inherit From Parent Object"), "No", "Is inherit No :");
		softAssert.assertEquals(getWeatherInformation("Weather Station"), weatherStation, weatherStation + " weatherstation is present ");
		softAssert.assertEquals(getWeatherInformation("Heating base temperature"), "16.5 °C (Not supported by the selected Weather Station)", "Heating base temperature is correct.");
		softAssert.assertEquals(getWeatherInformation("Cooling base temperature"), "16.5 °C (Not supported by the selected Weather Station)", "Cooling base temperature is correct.");
		softAssert.assertEquals(getWeatherInformation("HDD Reference"), "Standard (No data is available)", "Standard (No data is available)" + " HDD regerence is correct.");
		softAssert.assertEquals(getWeatherInformation("CDD Reference"), "Standard (No data is available)", "Standard (No data is available)" + " CDD regerence is correct.");
		
		ClickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		boolean bExist = verifyWarningDialogTextMessage(warningMessage);
		
		softAssert.assertEquals(false, bExist, "Warning message is correct ");
		
		if (bExist) {
			try {
				clickOnDialogButton("OK");
			} catch (Exception e) {}
		}
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, location,"@class", "crud-form"), "Weather station was selected.");
		
		waitForExtJSAjaxComplete(50);
		
		typeTextToCell("1", "1.0 °C");
		
		waitForExtJSAjaxComplete(50);
		
		typeTextToCell("2", "5.0 °C"); 
		
		waitForExtJSAjaxComplete(50);
		
		save();
		
		waitForExtJSAjaxComplete(50);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, location,"@class", "crud-form"), "Weather station was selected.");
		
		close();
		
		waitForExtJSAjaxComplete(50);
		
		softAssert.assertEquals(getWeatherInformation("Inherit From Parent Object"), "No", "Is inherit No :");
		softAssert.assertEquals(getWeatherInformation("Weather Station"), weatherStation, weatherStation + " weatherstation is present ");
		softAssert.assertEquals(getWeatherInformation("Heating base temperature"), "1.0 °C (No data is available)", "Heating base temperature is correct.");
		softAssert.assertEquals(getWeatherInformation("Cooling base temperature"), "5.0 °C (No data is available)", "Cooling base temperature is correct.");
		softAssert.assertEquals(getWeatherInformation("HDD Reference"), "Standard (No data is available)", "Standard (No data is available)" + " HDD regerence is correct.");
		softAssert.assertEquals(getWeatherInformation("CDD Reference"), "Standard (No data is available)", "Standard (No data is available)" + " CDD regerence is correct.");
		
		softAssert.assertAll();
		
		Reporter.log("Weather station was successfully linked to location.", true);
	 }

	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void LinkWeatherStationToLocationArea() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Link Weather Station To Location: C22077" + " </span><br>",
				true);

		Reporter.log("User Link Weather Station To Location"  + " <br>",
				true);
		
		String locationType = "Area";
		String location = "slnmEnrgArea1 (slnmEnrgArea)";
		String weatherStation = "1preWeatherStationLink";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("LinkWeatherStationToLocationArea");

		//Navigate to Meters Overview
	
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		setLocationValueTreeLookup(location);
		
		waitForExtJSAjaxComplete(20);
		
		String panelID = getXPanelId(locationType + " \"" +location + "\"");
		
		changeTab(panelID, "Weather");
		
		waitForExtJSAjaxComplete(20);
		
		ClickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		setWeatherStation(weatherStation);
		
		waitForExtJSAjaxComplete(50);
		
		save();
		
		waitForExtJSAjaxComplete(50);
		
		close();
		
		waitForExtJSAjaxComplete(50);
		
		panelID = getXPanelId(locationType + " \"" +location + "\"");
		
		softAssert.assertEquals(getWeatherInformation("Inherit From Parent Object"), "No", "Is inherit No :");
		softAssert.assertEquals(getWeatherInformation("Weather Station"), weatherStation, weatherStation + " weatherstation is present ");
		softAssert.assertEquals(getWeatherInformation("HDD Reference"), "Standard (No data is available)", "Standard (No data is available)" + " HDD regerence is correct.");
		softAssert.assertEquals(getWeatherInformation("CDD Reference"), "Standard (No data is available)", "Standard (No data is available)" + " CDD regerence is correct.");
		
		ClickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getWeatherStation(), weatherStation, "Weather station is correct ");
		
		close();
		
		waitForExtJSAjaxComplete(50);
		
		softAssert.assertAll();
		
		Reporter.log("Weather station was successfully linked to location area.", true);
	 }
	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void LinkWeatherStationToLocationSite() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Link Weather Station To Location: C22078" + " </span><br>",
				true);

		Reporter.log("User Link Weather Station To Location"  + " <br>",
				true);
		
		String locationType = "Site";
		String location = "slnmEnrgSite1";
		String weatherStation = "1preWeatherStationLink";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("LinkWeatherStationToLocationSite");
	
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		setLocationValueTreeLookup(location);
		
		waitForExtJSAjaxComplete(20);
		
		String panelID = getXPanelId(locationType + " \"" +location + "\"");
		
		changeTab(panelID, "Weather");
		
		waitForExtJSAjaxComplete(20);
		
		ClickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		setInheritWSFromParentObject(false);
		
		waitForExtJSAjaxComplete(20);
		
		setWeatherStation(weatherStation);
		
		waitForExtJSAjaxComplete(50);
		
		save();
		
		waitForExtJSAjaxComplete(50);
		
		close();
		
		waitForExtJSAjaxComplete(50);
		
		panelID = getXPanelId(locationType + " \"" +location + "\"");
		
		softAssert.assertEquals(getWeatherInformation("Inherit From Parent Object"), "No", "Is inherit No :");
		softAssert.assertEquals(getWeatherInformation("Weather Station"), weatherStation, weatherStation + " weatherstation is present ");
		softAssert.assertEquals(getWeatherInformation("HDD Reference"), "Standard (No data is available)", "Standard (No data is available)" + " HDD regerence is correct.");
		softAssert.assertEquals(getWeatherInformation("CDD Reference"), "Standard (No data is available)", "Standard (No data is available)" + " CDD regerence is correct.");
		
		ClickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getWeatherStation(), weatherStation, "Weather station is correct ");
		
		close();
		
		waitForExtJSAjaxComplete(50);
		
		softAssert.assertAll();
		
		Reporter.log("Weather station was successfully linked to location site.", true);
	 }

	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void LinkWeatherStationToLocationInherit() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Link Weather Station To Location: C21536" + " </span><br>",
				true);

		Reporter.log("User Link Weather Station To Location"  + " <br>",
				true);
		
		String locationType = "Site";
		String location = "slnmEnrgSite1";
		String weatherStation = "1preWeatherStationLink";
		
		String locationTypeBuilding = "Building";
		String locationBuilding = "slnmEnrgBuilding1";
		String warningMessage = "Selected Weather Station has no HDD or CDD channels";
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("LinkWeatherStationToLocationInherit");
	
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		setLocationValueTreeLookup(location);
		
		waitForExtJSAjaxComplete(20);
		
		String panelID = getXPanelId(locationType + " \"" +location + "\"");
		
		changeTab(panelID, "Weather");
		
		waitForExtJSAjaxComplete(20);
		
		ClickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		setInheritWSFromParentObject(false);
		
		waitForExtJSAjaxComplete(20);
		
		setWeatherStation(weatherStation);
		
		waitForExtJSAjaxComplete(50);
		
		save();
		
		waitForExtJSAjaxComplete(50);
		
		close();
		
		waitForExtJSAjaxComplete(50);
		
		panelID = getXPanelId(locationType + " \"" +location + "\"");
		
		softAssert.assertEquals(getWeatherInformation("Inherit From Parent Object"), "No", "Is inherit No :");
		softAssert.assertEquals(getWeatherInformation("Weather Station"), weatherStation, weatherStation + " weatherstation is present ");
		softAssert.assertEquals(getWeatherInformation("HDD Reference"), "Standard (No data is available)", "Standard (No data is available)" + " HDD regerence is correct.");
		softAssert.assertEquals(getWeatherInformation("CDD Reference"), "Standard (No data is available)", "Standard (No data is available)" + " CDD regerence is correct.");
		
		ClickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getWeatherStation(), weatherStation, "Weather station is correct ");
		
		close();
		
		waitForExtJSAjaxComplete(50);
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		setLocationValueTreeLookup(locationBuilding);
		
		waitForExtJSAjaxComplete(20);
		
		panelID = getXPanelId(locationTypeBuilding + " \"" +locationBuilding + "\"");
		
		changeTab(panelID, "Weather");
		
		waitForExtJSAjaxComplete(20);
		
		ClickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		boolean bRes = verifyWarningDialogTextMessage(warningMessage);
		
		if (bRes) {
			try {
				clickOnDialogButton("OK");
			} catch (Exception e) {}
		}
		
		waitForExtJSAjaxComplete(50);
		
		setInheritWSFromParentObject(true);
		
		waitForExtJSAjaxComplete(50);
		
		bRes = verifyWarningDialogTextMessage(warningMessage);
		
		softAssert.assertEquals(false, bRes, "Warning message is correct ");
		
		if (bRes) {
			try {
				clickOnDialogButton("OK");
			} catch (Exception e) {}
		}
		
		save();
		
		waitForExtJSAjaxComplete(50);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, locationBuilding,"@class", "crud-form"), "Weather station was selected.");
		
		close();
		
		waitForExtJSAjaxComplete(50);
		
		panelID = getXPanelId(locationTypeBuilding + " \"" +locationBuilding + "\"");
		
		softAssert.assertEquals(getWeatherInformation("Inherit From Parent Object"), "Yes", "Is inherit Yes :");
		softAssert.assertEquals(getWeatherInformation("Weather Station"), weatherStation, weatherStation + " weatherstation is present ");
		softAssert.assertEquals(getWeatherInformation("Heating base temperature"), "16.5 °C (Not supported by the selected Weather Station)", "Heating base temperature is correct.");
		softAssert.assertEquals(getWeatherInformation("Cooling base temperature"), "16.5 °C (Not supported by the selected Weather Station)", "Cooling base temperature is correct.");
		softAssert.assertEquals(getWeatherInformation("HDD Reference"), "Standard (No data is available)", "Standard (No data is available)" + " HDD regerence is correct.");
		softAssert.assertEquals(getWeatherInformation("CDD Reference"), "Standard (No data is available)", "Standard (No data is available)" + " CDD regerence is correct.");
		
		ClickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		boolean bExist = verifyWarningDialogTextMessage(warningMessage);
		
		softAssert.assertEquals(false, bExist, "Warning message is correct ");
		
		if (bExist) {
			try {
				clickOnDialogButton("OK");
			} catch (Exception e) {}
		}
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, locationBuilding,"@class", "crud-form"), "Weather station was selected.");
		
		close();
		
		waitForExtJSAjaxComplete(50);
		
		softAssert.assertAll();
		
		Reporter.log("Weather station was successfully linked to location with inheritance.", true);
	 }
}