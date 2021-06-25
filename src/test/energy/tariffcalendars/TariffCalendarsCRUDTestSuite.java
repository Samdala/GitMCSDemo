package test.energy.tariffcalendars;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.energy.energyratings.EnergyRatingsPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class TariffCalendarsCRUDTestSuite extends TariffCalendarsPageObject{
	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testTariffCalendarssCreateEdit() throws IOException, InterruptedException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create and Edit Tariff Calendars: c28776" + " </span><br>",
				true);

		Reporter.log("User creates and edits Tariff Calendars"  + " <br>",
				true);
		
		String code = "my auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "test auto" + getCurrentDate().substring(getCurrentDate().length()-5);;
		String country = "Albania";
		String region = "My Region";
		String currency = "USD";
		String commodityClass = "Gas";
		String dno = "My Company";
		String description = "My Description";
		
		String patternName1 = "First Pattern";
		String patternName2 = "Second Pattern";
		String patternName3 = "Third Pattern";
		String patternName4 = "Fourth Pattern";

		
		String normalColour = "#20A931";
		String lowColour = "#F6741C";
		String totalColour = "#C047E8";
		String specialColour = "#E52424";
		String highColour = "#F7E62A";
		String notUsedColour = "#CCCCCC";
		
		String codeEdited = "my auto ed" + getCurrentDate().substring(getCurrentDate().length()-5);
		String referenceEdited = "test auto ed" + getCurrentDate().substring(getCurrentDate().length()-5);;
		String countryEdited = "Afghanistan";
		String regionEdited = "My Region Edited";
		String currencyEdited = "EUR";
		String commodityClassEdited = "Fuel";
		String dnoEdited = "2preCompName";
		String descriptionEdited = "My Description Edited";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("configurationTariffCalendars");
		
		//Navigate to Tariff Calendars grid

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		expandConfiguration();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		openConfigurationEntity("Tariff Calendars");

		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//Add new Tariff Calendar
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		//Populate text fields	
		
		setCode(code);
		
		setReference(reference);
		
		setCountry(country);
		
		waitForExtJSAjaxComplete(5);
		
		setRegion(region);
		
		//if (build14) setCurency(currency);
		
		waitForExtJSAjaxComplete(5);
		
		setCommodityClass(commodityClass);
		
		waitForExtJSAjaxComplete(5);
		
		setDNO(dno);
		
		waitForExtJSAjaxComplete(5);
		
		setDescription(description);
		
		//Check all Tariff Levels
		
		checkTariffLevel("Normal Tariff");
		
		waitForExtJSAjaxComplete(5);
		
		checkTariffLevel("Low Tariff");
		
		waitForExtJSAjaxComplete(5);
		
		checkTariffLevel("Total Tariff");
		
		waitForExtJSAjaxComplete(5);
		
		checkTariffLevel("Special Tariff");
		
		waitForExtJSAjaxComplete(5);
		
		checkTariffLevel("High Tariff");
		
		waitForExtJSAjaxComplete(5);
		
		checkTariffLevel("Not Used");
		
		waitForExtJSAjaxComplete(5);
		
		//Change tab and verify all checked Tariff Levels are active
		
		changeTab("Patterns");
		
		waitForExtJSAjaxComplete(5);
		
		Assert.assertTrue(findTariffLevelButton("Normal Tariff"), "Message");
		
		Assert.assertTrue(findTariffLevelButton("Low Tariff"), "Message");
		
		Assert.assertTrue(findTariffLevelButton("Total Tariff"), "Message");
		
		Assert.assertTrue(findTariffLevelButton("Special Tariff"), "Message");
		
		Assert.assertTrue(findTariffLevelButton("High Tariff"), "Message");
		
		Assert.assertTrue(findTariffLevelButton("Not Used"), "Message");
		
		//Add Patterns, rename them and fill with Normal and High Tariff Levels 
		
		clickAddPatternButton();
		
		waitForExtJSAjaxComplete(5);
		
		setPatternName(patternName1);
		
		waitForExtJSAjaxComplete(5);
		
		clickTariffButton("Normal Tariff");
		
		waitForExtJSAjaxComplete(5);
		
		fillPattern();
		
		waitForExtJSAjaxComplete(5);	
		
		clickAddPatternButton();
		
		waitForExtJSAjaxComplete(5);
		
		setPatternName(patternName2);
		
		waitForExtJSAjaxComplete(5);
		
		clickTariffButton("High Tariff");
		
		waitForExtJSAjaxComplete(5);
		
		fillPattern();
		
		waitForExtJSAjaxComplete(5);
		
		//Add already existing Pattern
		
		clickAddMenu();
		
		waitForExtJSAjaxComplete(5);
		
		clickAddMenuUseExisting();
		
		waitForExtJSAjaxComplete(5);
		
		selectExistingPattern(patternName3);
		
		waitForExtJSAjaxComplete(5);
		
		//Change tab and add Newly created Patterns to Composer
		
		changeTab("Composer");
		
		waitForExtJSAjaxComplete(5);
		
		setComposerPattern(patternName1);
		
		waitForExtJSAjaxComplete(5);
		
		clickAddPatternComposerButton();
		
		waitForExtJSAjaxComplete(5);
		
		setComposerPattern(patternName2);
		
		waitForExtJSAjaxComplete(5);
		
		clickComposerStartDate();
		
		waitForExtJSAjaxComplete(5);
		
		clickDatePicker();
		
		waitForExtJSAjaxComplete(5);
		
		setComposerStartDateViaCombo("15", "July");
		
		clickApplyButton();
		
		waitForExtJSAjaxComplete(5);
		
		clickAddPatternComposerButton();
		
		waitForExtJSAjaxComplete(5);
		
		setComposerPattern(patternName3);
		
		waitForExtJSAjaxComplete(5);
		
		clickComposerStartDate();
		
		waitForExtJSAjaxComplete(5);
		
		clickDatePicker();
		
		waitForExtJSAjaxComplete(5);
		
		setComposerStartDateViaCombo("10", "October");
		
		clickApplyButton();
		
		waitForExtJSAjaxComplete(5);
		
		saveClose();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//Open Newly created Tariff Calendar for editing and verification after creation
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		//Verify text fields were saved correctly
		
		softAssert.assertEquals(getCode(), code, "Compare Code after creation");
		
		softAssert.assertEquals(getReference(), reference, "Compare Reference after creation");
		
		softAssert.assertEquals(getCountry(), country, "Compare Country after creation");
		
		softAssert.assertEquals(getRegion(), region, "Compare Region after creation");
		
		//if (build14) softAssert.assertEquals(getCurrency(), currency, "Compare Currency after creation");
		
		softAssert.assertEquals(getCommodityClass(), commodityClass, "Compare Commodity Class after creation");
		
		softAssert.assertEquals(getDNO(), dno, "Compare DNO after creation");
		
		softAssert.assertEquals(getDescription(), description, "Compare Description after creation");
		
		//Verify all Tariff Levels are checked
		
		softAssert.assertTrue(verifyTariffLevelChecked("Normal Tariff"), "Verify Normal Tariff Level is checked after creation");
		
		softAssert.assertTrue(verifyTariffLevelChecked("High Tariff"), "Verify High Tariff Level is checked after creation");
		
		softAssert.assertTrue(verifyTariffLevelChecked("Low Tariff"), "Verify Low Tariff Level is checked after creation");
		
		softAssert.assertTrue(verifyTariffLevelChecked("Total Tariff"), "Verify Total Tariff Level is checked after creation");
		
		softAssert.assertTrue(verifyTariffLevelChecked("Special Tariff"), "Verify Special Tariff Level is checked after creation");
		
		softAssert.assertTrue(verifyTariffLevelChecked("Not Used"), "Verify Not Used Tariff Level is checked after creation");
		
		changeTab("Patterns");
		
		waitForExtJSAjaxComplete(5);
		
		//Verify all three Patterns are present on Patterns tab
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, patternName1, "@class", "x-grid3-body"), "Verify Pattern 1 is present after creation");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, patternName2, "@class", "x-grid3-body"), "Verify Pattern 2 is present after creation");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Copy of "+patternName3, "@class", "x-grid3-body"), "Verify Pattern 3 is present after creation");
		
		//Verify Patterns are filled correctly with proper Tariff Levels
		
		selectPattern(patternName1);
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(verifyPatternColor(normalColour, "Mon", "0"), "Verify if Pattern 1 if filled properly with Normal Tariff after creation on 'Mon 1'");
		
		softAssert.assertTrue(verifyPatternColor(normalColour, "Sun", "23"), "Verify if Pattern 1 if filled properly with Normal Tariff after creation on 'Sun 23'");
		
		selectPattern(patternName2);
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(verifyPatternColor(highColour, "Mon", "0"), "Verify if Pattern 2 if filled properly with High Tariff after creation on 'Mon 0'");
		
		softAssert.assertTrue(verifyPatternColor(highColour, "Sun", "23"), "Verify if Pattern 2 if filled properly with High Tariff after creation on 'Sun 23'");
		
		selectPattern("Copy of "+patternName3);
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(verifyPatternColor(lowColour, "Mon", "0"), "Verify if Pattern 3 if filled properly with Low Tariff after creation on 'Mon 0'");
		
		softAssert.assertTrue(verifyPatternColor(lowColour, "Sun", "7"), "Verify if Pattern 3 if filled properly with Low Tariff after creation on 'Sun 7'");
		
		softAssert.assertTrue(verifyPatternColor(totalColour, "Mon", "8"), "Verify if Pattern 3 if filled properly with Total Tariff after creation on 'Mon 8'");
		
		softAssert.assertTrue(verifyPatternColor(totalColour, "Sun", "16"), "Verify if Pattern 3 if filled properly with Total Tariff after creation on 'Sun 16'");
		
		softAssert.assertTrue(verifyPatternColor(specialColour, "Mon", "17"), "Verify if Pattern 3 if filled properly with Special Tariff after creation  on 'Mon 17'");
		
		softAssert.assertTrue(verifyPatternColor(specialColour, "Sun", "23"), "Verify if Pattern 3 if filled properly with Special Tariff after creation on 'Sun 23'");
		
		softAssert.assertTrue(verifyPatternColor(notUsedColour, "Fri", "0"), "Verify if Pattern 3 if filled properly with Not Used Tariff after creation on 'Fri 0'");
		
		softAssert.assertTrue(verifyPatternColor(notUsedColour, "Fri", "23"), "Verify if Pattern 3 if filled properly with Not Used Tariff after creation on 'Fri 23'");
		
		changeTab("Composer");
		
		waitForExtJSAjaxComplete(5);
		
		//Verify Patterns are present on Composer tab with correct Start Dates
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, patternName1+"|1 January", "@class", XPATH_ADD_FORM_WINDOW), "Verify Pattern 1 present on Composer and has proper Start Date after creation");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, patternName2+"|15 July", "@class", XPATH_ADD_FORM_WINDOW), "Verify Pattern 2 present on Composer and has proper Start Date after creation");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Copy of "+patternName3+"|10 October", "@class", XPATH_ADD_FORM_WINDOW), "Verify Pattern 3 present on Composer and has proper Start Date after creation");
		
		//Edit Tariff Calendar
		
		changeTab("Properties");
		
		//Edit text fields
		
		setCode(codeEdited);
		
		setReference(referenceEdited);
		
		setCountry(countryEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setRegion(regionEdited);
		
		//if (build14) setCurency(currencyEdited);
		
		waitForExtJSAjaxComplete(5);
		
		setCommodityClass(commodityClassEdited);
		
		waitForExtJSAjaxComplete(5);
		
		setDNO(dnoEdited);
		
		waitForExtJSAjaxComplete(5);
		
		setDescription(descriptionEdited);
		
		changeTab("Patterns");
		
		waitForExtJSAjaxComplete(5);
		
		//Edit First and Secods Pattern - fill them with another Tariff Levels
		
		selectPattern(patternName1);
		
		waitForExtJSAjaxComplete(5);
		
		clickTariffButton("High Tariff");
		
		waitForExtJSAjaxComplete(5);
		
		fillPattern();
		
		waitForExtJSAjaxComplete(5);
		
		selectPattern(patternName2);
		
		waitForExtJSAjaxComplete(5);
		
		clickTariffButton("Normal Tariff");
		
		waitForExtJSAjaxComplete(5);
		
		fillPattern();
		
		waitForExtJSAjaxComplete(5);
		
		//Delete Third Pattern 
		
		selectPattern("Copy of "+patternName3);
		
		waitForExtJSAjaxComplete(5);
		
		clickDeletePatternButton();
		
		waitForExtJSAjaxComplete(5);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(5);
		
		//Add already existing Fourth Pattern
		
		clickAddMenu();
		
		waitForExtJSAjaxComplete(5);
		
		clickAddMenuUseExisting();
		
		waitForExtJSAjaxComplete(5);
		
		selectExistingPattern(patternName4);
		
		waitForExtJSAjaxComplete(5);
		
		changeTab("Composer");
		
		//Change Patterns and their Start Dates on Composer
		
		waitForExtJSAjaxComplete(5);		
		
		setComposerPattern("Copy of "+patternName4, patternName2);
		
		waitForExtJSAjaxComplete(5);
		
		setComposerPattern(patternName2, patternName1);
		
		waitForExtJSAjaxComplete(5);
		
		clickComposerStartDate("15 July");
		
		waitForExtJSAjaxComplete(5);
		
		clickDatePicker();
		
		waitForExtJSAjaxComplete(5);
		
		setComposerStartDateViaCombo("13", "June");
		
		clickApplyButton();
		
		waitForExtJSAjaxComplete(5);
		
		clickAddPatternComposerButton();
		
		setComposerPattern("Copy of "+patternName4);
		
		waitForExtJSAjaxComplete(5);
		
		clickComposerStartDate();
		
		waitForExtJSAjaxComplete(5);
		
		clickDatePicker();
		
		waitForExtJSAjaxComplete(5);
		
		setComposerStartDateViaCombo("1", "November");
		
		clickApplyButton();
		
		waitForExtJSAjaxComplete(5);
		
		saveClose();
		
		//Verify all changes were saved after editing
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, referenceEdited);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getCode(), codeEdited, "Compare Code after editing");
		
		softAssert.assertEquals(getReference(), referenceEdited, "Compare Reference after editing");
		
		softAssert.assertEquals(getCountry(), countryEdited, "Compare Country after editing");
		
		softAssert.assertEquals(getRegion(), regionEdited, "Compare Region after editing");
		
		//if (build14) softAssert.assertEquals(getCurrency(), currencyEdited, "Compare Currency after editing");
		
		softAssert.assertEquals(getCommodityClass(), commodityClassEdited, "Compare Commodity Class after editing");
		
		softAssert.assertEquals(getDNO(), dnoEdited, "Compare DNO after editing");
		
		softAssert.assertEquals(getDescription(), descriptionEdited, "Compare Description after editing");
		
		softAssert.assertTrue(verifyTariffLevelChecked("Normal Tariff"), "Verify Normal Tariff Level is checked after editing");
		
		softAssert.assertTrue(verifyTariffLevelChecked("High Tariff"), "Verify High Tariff Level is checked after editing");
		
		softAssert.assertTrue(verifyTariffLevelChecked("Low Tariff"), "Verify Low Tariff Level is checked after editing");
		
		softAssert.assertTrue(verifyTariffLevelChecked("Total Tariff"), "Verify Total Tariff Level is checked after editing");
		
		softAssert.assertTrue(verifyTariffLevelChecked("Special Tariff"), "Verify Special Tariff Level is checked after editing");
		
		softAssert.assertTrue(verifyTariffLevelChecked("Not Used"), "Verify Not Used Tariff Level is checked after editing");
		
		changeTab("Patterns");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, patternName1, "@class", "x-grid3-body"), "Verify Pattern 1 is present after editing");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, patternName2, "@class", "x-grid3-body"), "Verify Pattern 2 is present after editing");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Copy of "+patternName4, "@class", "x-grid3-body"), "Verify Pattern 4 is present after editing");
		
		selectPattern(patternName1);
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(verifyPatternColor(highColour, "Mon", "0"), "Verify if Pattern 1 if filled properly with Normal Tariff after editing on 'Mon 0'");
		
		softAssert.assertTrue(verifyPatternColor(highColour, "Sun", "23"), "Verify if Pattern 1 if filled properly with Normal Tariff after editing on 'Sun 23'");
		
		selectPattern(patternName2);
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(verifyPatternColor(normalColour, "Mon", "0"), "Verify if Pattern 2 if filled properly with High Tariff after editing on 'Mon 0'");
		
		softAssert.assertTrue(verifyPatternColor(normalColour, "Sun", "23"), "Verify if Pattern 2 if filled properly with High Tariff after editing on 'Sun 23'");
		
		selectPattern("Copy of "+patternName4);
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(verifyPatternColor(lowColour, "Mon", "16"), "Verify if Pattern 3 if filled properly with Low Tariff after editing on 'Mon 16'");
		
		softAssert.assertTrue(verifyPatternColor(lowColour, "Sun", "23"), "Verify if Pattern 3 if filled properly with Low Tariff after editing on 'Sun 23'");
		
		softAssert.assertTrue(verifyPatternColor(totalColour, "Mon", "8"), "Verify if Pattern 3 if filled properly with Total Tariff after editing on 'Mon 8'");
		
		softAssert.assertTrue(verifyPatternColor(totalColour, "Sun", "15"), "Verify if Pattern 3 if filled properly with Total Tariff after editing on 'Sun 15'");
		
		softAssert.assertTrue(verifyPatternColor(specialColour, "Mon", "0"), "Verify if Pattern 3 if filled properly with Special Tariff after editing on 'Mon 0'");
		
		softAssert.assertTrue(verifyPatternColor(specialColour, "Sun", "7"), "Verify if Pattern 3 if filled properly with Special Tariff after editing on 'Sun 7'");
		
		softAssert.assertTrue(verifyPatternColor(notUsedColour, "Wed", "0"), "Verify if Pattern 3 if filled properly with Not Used Tariff after editing on 'Wed 0'");
		
		softAssert.assertTrue(verifyPatternColor(notUsedColour, "Wed", "23"), "Verify if Pattern 3 if filled properly with Not Used Tariff after editing on 'Wed 23'");
		
		changeTab("Composer");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, patternName2+"|1 January", "@class", XPATH_ADD_FORM_WINDOW), "Verify Pattern 2 (first) present on Composer and has proper Start Date after editing");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Copy of "+patternName4+"|13 June", "@class", XPATH_ADD_FORM_WINDOW), "Verify Pattern 4 present on Composer and has proper Start Date after editing");
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "Copy of "+patternName4+"|1 November", "@class", XPATH_ADD_FORM_WINDOW), "Verify Pattern 2 (second) present on Composer and has proper Start Date after editing");

		softAssert.assertAll();
	}
		
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
		public void testTariffCalendarssDelete() throws IOException, InterruptedException  {

			Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Delete Tariff Calendars: c28775" + " </span><br>",
					true);

			Reporter.log("User deletes Tariff Calendars"  + " <br>",
					true);
			
			String code = "my auto" + getCurrentDate().substring(getCurrentDate().length()-5);
			String reference = "test auto" + getCurrentDate().substring(getCurrentDate().length()-5);;
			String country = "Albania";
			String commodityClass = "Gas";
			String dno = "My Company";

			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("configurationTariffCalendars");
			
			//Navigate to Tariff Calendars grid
		
			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			expandConfiguration();
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			openConfigurationEntity("Tariff Calendars");

			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			//Add Tariff Calendar only with mandatory items
			
			clickAddButton();
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			setCode(code);
			
			setReference(reference);
			
			setCountry(country);
			
			waitForExtJSAjaxComplete(5);
			
			setCommodityClass(commodityClass);
			
			waitForExtJSAjaxComplete(5);
			
			setDNO(dno);
			
			waitForExtJSAjaxComplete(5);
			
			checkTariffLevel("Normal Tariff");
			
			waitForExtJSAjaxComplete(5);
			
			changeTab("Patterns");
			
			waitForExtJSAjaxComplete(5);
			
			clickAddPatternButton();
			
			waitForExtJSAjaxComplete(5);
			
			clickTariffButton("Normal Tariff");
			
			waitForExtJSAjaxComplete(5);
			
			fillPattern();
			
			waitForExtJSAjaxComplete(5);
			
			changeTab("Composer");
			
			waitForExtJSAjaxComplete(5);
			
			setComposerPattern("New Pattern");
			
			waitForExtJSAjaxComplete(5);
			
			changeTab("Composer");
			
			waitForExtJSAjaxComplete(5);
			
			saveClose();
			
			//Delete newly added Tariff calendar
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			Grid.checkRowInGriByTextValueAndClick(driver, code);
			
			waitForExtJSAjaxComplete(5);
			
			clickDeleteButton();
			
			waitForExtJSAjaxComplete(25);
			
			//Verify Tariff Calendar was deleted
			
			Assert.assertTrue(Grid.isRowInGridAbsent(driver, code, "@class", ADMINISTRATION_PANEL_CLASS), "Check if Tariff Calendar is deleted");
			
	}
}

