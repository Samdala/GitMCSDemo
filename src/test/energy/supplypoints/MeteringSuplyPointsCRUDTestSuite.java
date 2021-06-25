package test.energy.supplypoints;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Wait.WaitTimedOutException;

import test.energy.commodities.ConfigurationCommoditiesPageObject;
import test.energy.meters.MetersPageObject;
import test.energy.navigator.NavigatorPageObject;
import test.energy.tariffcalendars.TariffCalendarsPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import test.framework.webelement.Tree;

public class MeteringSuplyPointsCRUDTestSuite extends
		MeteringSuplyPointsPageObject {




	 @Test(enabled=true)
	public void testMeteringSupplyPointEdit() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Edit Supply Point: c19712, c25331, c25332, c25333" + " </span><br>",
				true);

		Reporter.log("User edits supply point:  "  + " <br>",
				true);
		
		String code = "test auto sp" + getCurrentDate().substring(getCurrentDate().length()-6);
		String site = "slnmEnrgSite3 (site3)";
		String commodity = "Electricity";
		String powerCapacity = "125";
		String supplySystemHigh = "High Voltage";
		String supplySystemMedium = "Medium Voltage";
		String type = "preSPTref";
		String description = "My Auto Description";
		String address1 = "My Auto Address1";
		String address2 = "My Auto Address2";
		String zipcode = "1234567890";
		String city = "Lviv";
		String country = "Angola";
		
				
		String codeEdited = "test auto sp ed" + getCurrentDate().substring(getCurrentDate().length()-6);
		String codePrefixEdited = "NRG-";
		String typeEdited = "Default";
		String descriptionEdited = "My Auto Description Edited";
		String powerCapacityEdited = "0.21";
		String address1Edited = "My Auto Address1 Edited";
		String address2Edited = "My Auto Address2 Edited";
		String zipcodeEdited = "0987654321";
		String cityEdited = "Kyiv";
		String countryEdited = "Albania";
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("meteringSupplyPointEdit");

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, "slnmEnrgArea1 (slnmEnrgArea)");
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.clickNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite3 (site3)");
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(20);
		
		clickSystemViewTab();

		waitForExtJSAjaxComplete(20);
		
		setEffectiveDateMeterInfrastructure("01-04-2014");
		
		waitForExtJSAjaxComplete(25);
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		test.framework.webelement.Tree.clickNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		test.framework.webelement.Tree.clickNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite3 (site3)");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		clickReference();
		
		waitForExtJSAjaxComplete(20);
		
		String reference = getReference();
		
		Reporter.log("Check if Reference is equal to Code Predix + Code", true);
		softAssert.assertEquals(reference, getCodePrefix()+getCode(), "Supply Point Reference doesn't equals Code Prefix + Code");
		
		softAssert.assertEquals("slnmEnrgSite3 (site3)", getSite(), "Supply Point Site is prefilled");
		
		//setSite(site);
		
		setType(type, ADD_SUPPLY_POINT_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		setCommodity(commodity);
		
		waitForExtJSAjaxComplete(25);
				
		setPowerCapacity(powerCapacity);
		
		setSupplySystem(DIALOG_SP, supplySystemHigh);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setDescription(description);
		
		setAddress1(address1);
		
		setAddress2(address2);
		
		setZipCode(zipcode);
		
		setCity(city);
		
		setCountry(country);
		
		waitForExtJSAjaxComplete(25);
		
		close();
		
		waitForExtJSAjaxComplete(25);
		
		clickConfirmOnDialog();
		
		waitForExtJSAjaxComplete(25);
		
		expandMetering();
		
		waitForExtJSAjaxComplete(20);
		
		openMeteringEntity("Supply Points");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.isRowInGridAbsent(driver, code);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, "slnmEnrgArea1 (slnmEnrgArea)");
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite3 (site3)");
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(20);
		
		clickSystemViewTab();

		waitForExtJSAjaxComplete(20);
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.clickNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		test.framework.webelement.Tree.clickNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite3 (site3)");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		clickReference();
		
		waitForExtJSAjaxComplete(20);
		
		reference = getReference();
		
		Reporter.log("Check if Reference is equal to Code Predix + Code", true);
		softAssert.assertEquals(reference, getCodePrefix()+getCode(), "Supply Point Reference doesn't equals Code Prefix + Code");
		
		softAssert.assertEquals("slnmEnrgSite3 (site3)", getSite(), "Supply Point Site is prefilled");
		
		//setSite(site);
		
		setType(type, ADD_SUPPLY_POINT_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		setCommodity(commodity);
		
		waitForExtJSAjaxComplete(25);
				
		setPowerCapacity(powerCapacity);
		
		setSupplySystem(DIALOG_SP, supplySystemHigh);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setDescription(description);
		
		setAddress1(address1);
		
		setAddress2(address2);
		
		setZipCode(zipcode);
		
		setCity(city);
		
		setCountry(country);
		
		waitForExtJSAjaxComplete(25);
					
		save();
				
		waitForExtJSAjaxComplete(25);
		
		close();

		waitForExtJSAjaxComplete(25);
		
		expandMetering();
		
		waitForExtJSAjaxComplete(20);
		
		openMeteringEntity("Supply Points");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, code);
		
		clickEditButton(SUPPLY_POINT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if fields are correct after creation", true);
		
		softAssert.assertEquals(getReference(),reference, reference+" reference after creation is wrong");
		
		softAssert.assertEquals(getSite(),site, site+" site after creation is wrong");
		
		softAssert.assertEquals(getType(),type, type+" type after creation is wrong");
		
		softAssert.assertEquals(getCommodity(),commodity, commodity+" commodity after creation is wrong");
		
		softAssert.assertEquals(getPowerCapacity(),powerCapacity, powerCapacity+" Power Capacity after creation is wrong");
		
		softAssert.assertEquals(getSupplySystem(),supplySystemHigh, supplySystemHigh+" Supply System after creation is wrong");
		
		softAssert.assertEquals(getDescription(),description, description+" description after creation is wrong");
		
		softAssert.assertEquals(getAddress1(),address1, address1+" address1 after creation is wrong");
		
		softAssert.assertEquals(getAddress2(),address2, address2+" address2 after creation is wrong");
		
		softAssert.assertEquals(getZipCode(),zipcode, zipcode+" zipcode after creation is wrong");

		softAssert.assertEquals(getCity(),city, city+" city after creation is wrong");
		
		softAssert.assertEquals(getCountry(),country, country+" country after creation is wrong");
		
		setCode(codeEdited);
		
		setCodePrefix(codePrefixEdited);
		
		clickReference();
		
		waitForExtJSAjaxComplete(20);
						
		Reporter.log("Check if Reference is equal to Code Predix + Code", true);
		softAssert.assertEquals(getReference(), getCodePrefix()+getCode(), "Supply Point Reference doesn't equals Code Prefix + Code");
		
		setType(typeEdited, EDIT_SUPPLY_POINT_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		setPowerCapacity(powerCapacityEdited);
		
		setSupplySystem(DIALOG_SP, supplySystemMedium);
		
		waitForExtJSAjaxComplete(25);
							
		setDescription(descriptionEdited);
		
		setAddress1(address1Edited);
		
		setAddress2(address2Edited);
		
		setZipCode(zipcodeEdited);
		
		setCity(cityEdited);
		
		setCountry(countryEdited);
			
		close();
		
		waitForExtJSAjaxComplete(25);
		
		clickConfirmOnDialog();
		
		waitForExtJSAjaxComplete(25);

		Grid.checkRowInGriByTextValueAndClick(driver, code);
		
		clickEditButton(SUPPLY_POINT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		Reporter.log("Check if fields are correct after creation", true);
		softAssert.assertEquals(getReference(),reference, reference+" reference after cancel edit is wrong");
		
		softAssert.assertEquals(getSite(),site, site+" site after cancel edit  is wrong");
		
		softAssert.assertEquals(getType(),type, type+" type after cancel edit  is wrong");
		
		softAssert.assertEquals(getCommodity(),commodity, commodity+" commodity after cancel edit  is wrong");
		
		softAssert.assertEquals(getPowerCapacity(),powerCapacity, powerCapacity+" Power Capacity after cancel edit  is wrong");
		
		softAssert.assertEquals(getSupplySystem(),supplySystemHigh, supplySystemHigh+" Supply System after cancel edit  is wrong");
		
		softAssert.assertEquals(getDescription(),description, description+" description after cancel edit  is wrong");
		
		softAssert.assertEquals(getAddress1(),address1, address1+" address1 after cancel edit  is wrong");
		
		softAssert.assertEquals(getAddress2(),address2, address2+" address2 after cancel edit  is wrong");
		
		softAssert.assertEquals(getZipCode(),zipcode, zipcode+" zipcode after cancel edit  is wrong");

		softAssert.assertEquals(getCity(),city, city+" city after cancel edit  is wrong");
		
		softAssert.assertEquals(getCountry(),country, country+" country after creation is wrong");
		
		setCode(codeEdited);
		
		setCodePrefix(codePrefixEdited);
		
		clickReference();
		
		waitForExtJSAjaxComplete(20);
						
		Reporter.log("Check if Reference is equal to Code Predix + Code", true);
		softAssert.assertEquals(getReference(), getCodePrefix()+getCode(), "Supply Point Reference doesn't equals Code Prefix + Code");
		
		setType(typeEdited, EDIT_SUPPLY_POINT_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		setPowerCapacity(powerCapacityEdited);
		
		setSupplySystem(DIALOG_SP, supplySystemMedium);
		
		waitForExtJSAjaxComplete(25);
							
		setDescription(descriptionEdited);
		
		setAddress1(address1Edited);
		
		setAddress2(address2Edited);
		
		setZipCode(zipcodeEdited);
		
		setCity(cityEdited);
		
		setCountry(countryEdited);
			
		save();
		
		waitForExtJSAjaxComplete(25);
		
		close();
		
		waitForExtJSAjaxComplete(25);

		Grid.checkRowInGriByTextValueAndClick(driver, codeEdited);
		
		clickEditButton(SUPPLY_POINT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);

		Reporter.log("Check if fields are correct after editing", true);
		softAssert.assertEquals(getReference(),codePrefixEdited+codeEdited, codePrefixEdited+codeEdited+" reference after edit is wrong");
		
		softAssert.assertEquals(getDescription(),descriptionEdited, descriptionEdited+ " description after edit is wrong");
		
		softAssert.assertEquals(getPowerCapacity(),powerCapacityEdited, powerCapacityEdited+" Power Capacity after edit is wrong");
		
		softAssert.assertEquals(getSupplySystem(),supplySystemMedium, supplySystemMedium+" Supply System after edit is wrong");
		
		softAssert.assertEquals(getAddress1(),address1Edited, address1Edited+ " address1 after edit is wrong");
		
		softAssert.assertEquals(getAddress2(),address2Edited, address2Edited+ " address2 after edit is wrong");
		
		softAssert.assertEquals(getZipCode(),zipcodeEdited, zipcodeEdited+ " zipcode after edit is wrong");
		
		softAssert.assertEquals(getCity(),cityEdited, cityEdited+ " city after edit is wrong");
		
		softAssert.assertEquals(getCountry(),countryEdited, countryEdited+ " country End use after edit is wrong");		
		
		softAssert.assertAll();

		close();
		
		Reporter.log("Supply Point "  + " was succesfully created and edited", true);

	}
	 
	@Test(enabled=true)
	public void testMeteringSupplyPointCapacityAndPressure() throws IOException  {

			Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Edit Supply Point Capacity: c19876" + " </span><br>",
					true);

			Reporter.log("User edits supply point capacity:  "  + " <br>",
					true);
			
			String code = "test auto sp" + getCurrentDate().substring(getCurrentDate().length()-6);
			//String site = "slnmEnrgSite1";
			String commodity = "Gas";
			String capacity = "125";
			String pressure = "25";
			String pressureUOM = "atm";
						
			String capacityEdited = "0.21";		
			String pressureEdited = "3";
			String pressureUOMEdited = "mb";
			
			String commodityLookUp = "Natural gas";
			
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("meteringSupplyPointCapacityAndPressureEdit");

			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(20);
			
			expandNavigator();
			
			waitForExtJSAjaxComplete(20);
			
			test.framework.webelement.Tree.expandNavigatorTreeNode(driver, "slnmEnrgArea1 (slnmEnrgArea)");
			
			waitForExtJSAjaxComplete(20);
			
			test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(20);
			
			clickSystemViewTab();

			waitForExtJSAjaxComplete(20);
			
			setEffectiveDateMeterInfrastructure("01-04-2014");
			
			waitForExtJSAjaxComplete(25);
			
			setCommodityClass("Gas");
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			test.framework.webelement.Tree.clickNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite3 (site3)");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			test.framework.webelement.Tree.clickNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
			
			waitForExtJSAjaxComplete(25);
			
			waitForExtJSAjaxComplete(25);
			
			clickSystemViewTab();

			waitForExtJSAjaxComplete(20);
			
			setEffectiveDateMeterInfrastructure("01-04-2014");
			
			waitForExtJSAjaxComplete(25);
			
			setCommodityClass("Gas");
			
			waitForExtJSAjaxComplete(20);
			
			waitForExtJSAjaxComplete(25);
			
			clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			setCode(code);
			
			//setSite(site);
					
			waitForExtJSAjaxComplete(25);
			
			setCommodity(commodityLookUp);
			
			waitForExtJSAjaxComplete(25);
					
			setCapacity(capacity);
			
			waitForExtJSAjaxComplete(25);
			
			setPressure(pressure);
			
			waitForExtJSAjaxComplete(25);
			
			setPressureUOM(pressureUOM);
			
			waitForExtJSAjaxComplete(25);
						
			save();
			
			waitForExtJSAjaxComplete(25);
			
			close();
			
			waitForExtJSAjaxComplete(25);
			
			expandMetering();
			
			waitForExtJSAjaxComplete(20);
			
			openMeteringEntity("Supply Points");
			
			waitForExtJSAjaxComplete(20);
					
			Grid.checkRowInGriByTextValueAndClick(driver, code);
			
			clickEditButton(SUPPLY_POINT_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			Reporter.log("Check if Capacity, Pressure and Pressure UOM are correct after creation", true);
			
			softAssert.assertEquals(getCapacity(),capacity, capacity+" Capacity after creation is wrong");
			
			softAssert.assertEquals(getPressure(),pressure, pressure+" Pressure after creation is wrong");
			
			softAssert.assertEquals(getPressureUOM(),pressureUOM, pressureUOM+" Pressure UOM after creation is wrong");
			
			setCapacity(capacityEdited);
			
			waitForExtJSAjaxComplete(20);
			
			setPressure(pressureEdited);
			
			waitForExtJSAjaxComplete(20);
			
			setPressureUOM(pressureUOMEdited);
			
			waitForExtJSAjaxComplete(20);
				
			save();
			
			waitForExtJSAjaxComplete(25);
			
			close();
			
			waitForExtJSAjaxComplete(25);

			Grid.checkRowInGriByTextValueAndClick(driver, code);
			
			clickEditButton(SUPPLY_POINT_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);

			Reporter.log("Check if Capacity, Pressure, Pressure UOM are correct after editing", true);
			
			softAssert.assertEquals(getCapacity(),capacityEdited, capacityEdited+" Capacity after edit is wrong");
			
			softAssert.assertEquals(getPressure(),pressureEdited, pressureEdited+" Pressure after edit is wrong");
			
			softAssert.assertEquals(getPressureUOM(),pressureUOMEdited, pressureUOMEdited+" Pressure UOM after edit is wrong");
			
			softAssert.assertAll();

			close();
			
			Reporter.log("Supply Point with Capacity, Pressure and Pressure UOM"  + " was succesfully created and edited", true);

		}

	 //Temporarily Supply Points can't be deleted!
	@Test(enabled = true)
	public void testMeteringSupplyPointDelete() throws IllegalStateException, IOException {

		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Supply Point: c19715 " + " </span><br>",
				true);

		Reporter.log("User deletes supply point:  "  + " <br>",
				true);
		
		String code = "test auto abt" + getCurrentDate().substring(getCurrentDate().length()-6);
		//String site = "slnmEnrgSite1";
		String commodity = "Electricity";
	
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, "slnmEnrgArea1 (slnmEnrgArea)");
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(20);
		
		clickSystemViewTab();

		waitForExtJSAjaxComplete(20);
		
		setEffectiveDateMeterInfrastructure("01-04-2014");
		
		waitForExtJSAjaxComplete(25);
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		test.framework.webelement.Tree.clickNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite3 (site3)");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		test.framework.webelement.Tree.clickNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		//setSite(site);
		
		setCommodity(commodity);
		
		waitForExtJSAjaxComplete(20);
				
		save();
		
		waitForExtJSAjaxComplete(25);
		
		close();
		
		waitForExtJSAjaxComplete(25);
		
		expandMetering();
		
		waitForExtJSAjaxComplete(20);
		
		openMeteringEntity("Supply Points");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, code);
		
		clickDeleteButton(SUPPLY_POINT_GRID_CLASS);
				
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, code), code+" code after delete is present");
		
		Reporter.log("Supply Point was succesfully deleted", true);
		
	}

	@Test(enabled = true)
	public void testBisnesPartnersCRUD() throws IllegalStateException, IOException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: BisnesPartnersCRUD  </span><br>",
				true);

		Reporter.log("User BisnesPartnersCRUD: C39679, C30025, C39678, C30024(testrail)  <br>",
				true);
		
		String code = "test auto sp" + getCurrentDate().substring(getCurrentDate().length()-6);
		String commodity = "Electricity";
		String powerCapacity = "125";
		String supplySystemHigh = "High Voltage";
		String type = "preSPTref";
		String description = "My Auto Description";
		String address1 = "My Auto Address1";
		String address2 = "My Auto Address2";
		String zipcode = "1234567890";
		String city = "Lviv";
		String country = "Angola";
		
		
		String effectiveDate = "01-01-2014";
		String supplier = "2preCompName";
		String dno = "My Company";
		String commoditySource = "1preBPcmdtSource";
		String tariffCalendar = "1preBPTrfClndr"; 

		String effectiveDateEdit = "05-05-2014";
		String supplierEdit = "aqacompName";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testBisnesPartnersCRUD");

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, "slnmEnrgArea1 (slnmEnrgArea)");
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite3 (site3)");
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickSystemViewTab();

		waitForExtJSAjaxComplete(20);
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);
		
		setEffectiveDateMeterInfrastructure("01-01-2014");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		test.framework.webelement.Tree.clickNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		test.framework.webelement.Tree.clickNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite3 (site3)");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		clickReference();
		
		waitForExtJSAjaxComplete(20);
		
		String reference = getReference();
		
		Reporter.log("Check if Reference is equal to Code Predix + Code", true);
		softAssert.assertEquals(reference, getCodePrefix()+getCode(), "Supply Point Reference doesn't equals Code Prefix + Code");
		
		softAssert.assertEquals(getSite(), "slnmEnrgSite3 (site3)", "Supply Point Site is prefilled");
		
		//setSite(site);
		
		setType(type, ADD_SUPPLY_POINT_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		setCommodity(commodity);
		
		waitForExtJSAjaxComplete(25);
				
		setPowerCapacity(powerCapacity);
		
		setSupplySystem(DIALOG_SP, supplySystemHigh);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setDescription(description);
		
		setAddress1(address1);
		
		setAddress2(address2);
		
		setZipCode(zipcode);
		
		setCity(city);
		
		setCountry(country);
		
		waitForExtJSAjaxComplete(25);
		
		close();
		
		waitForExtJSAjaxComplete(25);
		
		clickConfirmOnDialog();
		
		waitForExtJSAjaxComplete(25);
		
		expandMetering();
		
		waitForExtJSAjaxComplete(20);
		
		openMeteringEntity("Supply Points");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.isRowInGridAbsent(driver, code);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, "slnmEnrgArea1 (slnmEnrgArea)");
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite3 (site3)");
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickSystemViewTab();

		waitForExtJSAjaxComplete(20);
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.clickNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		test.framework.webelement.Tree.clickNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite3 (site3)");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		clickReference();
		
		waitForExtJSAjaxComplete(20);
		
		reference = getReference();
		
		Reporter.log("Check if Reference is equal to Code Predix + Code", true);
		softAssert.assertEquals(reference, getCodePrefix()+getCode(), "Supply Point Reference doesn't equals Code Prefix + Code");
		
		softAssert.assertEquals(getSite(), "slnmEnrgSite3 (site3)", "Supply Point Site is prefilled");
		
		//setSite(site);
		
		setType(type, ADD_SUPPLY_POINT_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		setCommodity(commodity);
		
		waitForExtJSAjaxComplete(25);
				
		setPowerCapacity(powerCapacity);
		
		setSupplySystem(DIALOG_SP, supplySystemHigh);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setDescription(description);
		
		setAddress1(address1);
		
		setAddress2(address2);
		
		setZipCode(zipcode);
		
		setCity(city);
		
		setCountry(country);
		
		waitForExtJSAjaxComplete(25);
					
		save();
				
		waitForExtJSAjaxComplete(25);
		
		close();

		waitForExtJSAjaxComplete(25);
		
		expandMetering();
		
		waitForExtJSAjaxComplete(20);
		
		openMeteringEntity("Supply Points");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, code);
		
		clickEditButton(SUPPLY_POINT_GRID_CLASS);
		
		clickBusinessPartnersTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButtonBusinessPartners();
		
		waitForExtJSAjaxComplete(20);
		
		setEffectiveDate(effectiveDate);
		
		waitForExtJSAjaxComplete(20);
		
		setCommoditySource(commoditySource);
		
		waitForExtJSAjaxComplete(20);
		
		setSupplier(supplier);
		
		waitForExtJSAjaxComplete(20);
		
		setDNOOnBusinessPartners(dno);
		
		waitForExtJSAjaxComplete(20);
		
		setTariffCalendarOnBusinessPartners(tariffCalendar);
		
		waitForExtJSAjaxComplete(10);
		
		saveBusinessPartners();
		
		waitForExtJSAjaxComplete(20);
		
		closeBusinessPartners();
		
		waitForExtJSAjaxComplete(20);
		
		checkRowInGriByTextValueAndClickCustomized(DIALOG_SP, supplier);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButtonBusinessPartners();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getFieldValueBusinessPartners("effectiveDate", "value"),effectiveDate, effectiveDate+" effectiveDate after creation");
		
		softAssert.assertEquals(getFieldValueBusinessPartners("source", "value"),commoditySource, commoditySource+" commoditySource after creation");
		
		softAssert.assertEquals(getFieldValueBusinessPartners("supplier", "value"),supplier, supplier+" type after creation");
		
		softAssert.assertEquals(getFieldValueBusinessPartners("dno", "value"),dno, dno+" dno(Distribution Network Operator) after creation");
		
		setEffectiveDate(effectiveDateEdit);
		
		waitForExtJSAjaxComplete(20);
		
		setSupplier(supplierEdit);
		
		waitForExtJSAjaxComplete(20);
		
		saveBusinessPartners();
		
		waitForExtJSAjaxComplete(20);
		
		closeBusinessPartners();
		
		waitForExtJSAjaxComplete(20);
		
		checkRowInGriByTextValueAndClickCustomized(DIALOG_SP, supplierEdit);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButtonBusinessPartners();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getFieldValueBusinessPartners("effectiveDate", "value"),effectiveDateEdit, effectiveDateEdit+" effectiveDate after edit");
		
		softAssert.assertEquals(getFieldValueBusinessPartners("source", "value"),commoditySource, commoditySource+" commoditySource after edit");
		
		softAssert.assertEquals(getFieldValueBusinessPartners("supplier", "value"),supplierEdit, supplierEdit+" type after edit");
		
		softAssert.assertEquals(getFieldValueBusinessPartners("dno", "value"),dno, dno+" dno(Distribution Network Operator) after edit");
		
		softAssert.assertAll();
		
		Reporter.log("Supply Point. Business partner was succesfully created and edited", true);
	}

	
	/**
	 * Test Case ID: C60823
	 * Author : SSU
	 */
	//TODO Initial Settings in Etalon Price Template etc
	@Test(enabled=true)
	public void testCustomEnergyPrice() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Define a custom energy price: C60823" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCustomEnergyPrice");
		
		String supplyPoint = "1preSP";
		String commoditySource = "Electricity_Source";
		String dnoName= "aqacompName";
		String tariffCalendar = "trfCode";
		String priceTemplate ="priceRef";
		
			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(20);
			
			expandMetering();
			
			waitForExtJSAjaxComplete(20);
			
			openMeteringEntity("Supply Points");		
			
			waitForExtJSAjaxComplete(20);
			
			checkRowInGriByTextValueAndClick(driver, "@class", SUPPLY_POINT_GRID_CLASS, supplyPoint);
			
			waitForExtJSAjaxComplete(20);
			
			clickEditButton(SUPPLY_POINT_GRID_CLASS);
			
			waitForExtJSAjaxComplete(10);
			
			clickBusinessPartnersTab();
			
			waitForExtJSAjaxComplete(25);
			
			clickAddButtonBusinessPartners();
			
			waitForExtJSAjaxComplete(10);
			
			setEffectiveDate("03-07-2015");
			
			waitForExtJSAjaxComplete(5);
			
			setCommoditySource(commoditySource);
			
			waitForExtJSAjaxComplete(10);
			
			setDNOOnBusinessPartners(dnoName);
			
			waitForExtJSAjaxComplete(10);
			
			setTariffCalendarOnBusinessPartners(tariffCalendar);
			
			waitForExtJSAjaxComplete(10);
			
			setPriceTemplateOnBusinessPartners(priceTemplate);
			
			waitForExtJSAjaxComplete(10);
			
			saveBusinessPartners();
			
			waitForExtJSAjaxComplete(10);
			
			closeBusinessPartners();
			
			waitForExtJSAjaxComplete(25);
			
			checkRowInGriByTextValueAndClick(driver, "@realid", PRICES_GRID_BUSINESS_PARTNERS_TAB, "03-07-2015 00:00:00");
			
			waitForExtJSAjaxComplete(20);
			
			clickEditButtonBPPricesGrid();
			
			waitForExtJSAjaxComplete(10);
				
			if(verifyDialogMsg()) {
				
				clickOnDialogButton("Accept");
				
				waitForExtJSAjaxComplete(10);
				
			} else{
			
				Reporter.log("Dialog Popup is not displayed", true);
			}
			
			//Row 1 Verification
			
			softAssert.assertFalse(getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "1", "Reference").contains("dirty-cell"), "Reference column is not Editable");
			
			softAssert.assertFalse(getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "1", "Dependency").contains("dirty-cell"), "Dependency column is not Editable");
			
			softAssert.assertFalse(getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "1", "Evaluation Basis").contains("dirty-cell"), "Evaluation Basis column is not Editable");
			
			getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "1", "Unit Price");
			
			softAssert.assertFalse(getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "1", "UOM").contains("dirty-cell"), "UOM column is not Editable");
			
			softAssert.assertFalse(getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "1", "Tax Code 1").contains("dirty-cell"), "Tax Code 1 column is not Editable");
			
			softAssert.assertFalse(getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "1", "Tax Code 2").contains("dirty-cell"), "Tax Code 2 column is not Editable");
			
			//Row 2 Verification
			
			softAssert.assertFalse(getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "2", "Reference").contains("dirty-cell"), "Reference column is not Editable");
			
			softAssert.assertFalse(getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "2", "Dependency").contains("dirty-cell"), "Dependency column is not Editable");
			
			softAssert.assertFalse(getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "2", "Evaluation Basis").contains("dirty-cell"), "Evaluation Basis column is not Editable");
			
			getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "2", "Unit Price");
			
			softAssert.assertFalse(getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "2", "UOM").contains("dirty-cell"), "UOM column is not Editable");
			
			softAssert.assertFalse(getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "2", "Tax Code 1").contains("dirty-cell"), "Tax Code 1 column is not Editable");
			
			softAssert.assertFalse(getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "2", "Tax Code 2").contains("dirty-cell"), "Tax Code 2 column is not Editable");
			
			//Row 3 Verification
			
			softAssert.assertFalse(getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "3", "Reference").contains("dirty-cell"), "Reference column is not Editable");
			
			softAssert.assertFalse(getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "3", "Dependency").contains("dirty-cell"), "Dependency column is not Editable");
			
			softAssert.assertFalse(getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "3", "Evaluation Basis").contains("dirty-cell"), "Evaluation Basis column is not Editable");
			
			getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "3", "Unit Price");
			
			softAssert.assertFalse(getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "3", "UOM").contains("dirty-cell"), "UOM column is not Editable");
			
			softAssert.assertFalse(getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "3", "Tax Code 1").contains("dirty-cell"), "Tax Code 1 column is not Editable");
			
			softAssert.assertFalse(getCellText(EDIT_ENERGY_PRICE_WINDOW_HEADER, "3", "Tax Code 2").contains("dirty-cell"), "Tax Code 2 column is not Editable");
			
			setUnitPriceInGrid(EDIT_ENERGY_PRICE_WINDOW_HEADER, "1", "Unit Price", "121");
			
			waitForExtJSAjaxComplete(10);
			
			setUnitPriceInGrid(EDIT_ENERGY_PRICE_WINDOW_HEADER, "2", "Unit Price", "131");
			
			waitForExtJSAjaxComplete(10);
			
			setUnitPriceInGrid(EDIT_ENERGY_PRICE_WINDOW_HEADER, "3", "Unit Price", "141");
			
			waitForExtJSAjaxComplete(10);
			
			saveClose("energy properties");
			
			waitForExtJSAjaxComplete(20);
			
			clickAddButtonBPPricesGrid();
			
			waitForExtJSAjaxComplete(10);
			
			if(verifyDialogMsg()) {
				
				clickOnDialogButton("Accept");
				
				waitForExtJSAjaxComplete(10);
				
			} else{
			
				Reporter.log("Dialog Popup is not displayed", true);
			}
			
			setDateEnergyPrice(getFutureDate(0));
			
			waitForExtJSAjaxComplete(5);
			
			setTimeEnergyPrice("03:00");
			
			waitForExtJSAjaxComplete(5);
			
			//Row 1 Verification
			
			softAssert.assertFalse(getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "1", "Reference").contains("dirty-cell"), "Reference column is not Editable");
			
			softAssert.assertFalse(getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "1", "Dependency").contains("dirty-cell"), "Dependency column is not Editable");
			
			softAssert.assertFalse(getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "1", "Evaluation Basis").contains("dirty-cell"), "Evaluation Basis column is not Editable");
			
			getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "1", "Unit Price");
			
			softAssert.assertFalse(getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "1", "UOM").contains("dirty-cell"), "UOM column is not Editable");
			
			softAssert.assertFalse(getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "1", "Tax Code 1").contains("dirty-cell"), "Tax Code 1 column is not Editable");
			
			softAssert.assertFalse(getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "1", "Tax Code 2").contains("dirty-cell"), "Tax Code 2 column is not Editable");
			
			//Row 2 Verification
			
			softAssert.assertFalse(getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "2", "Reference").contains("dirty-cell"), "Reference column is not Editable");
			
			softAssert.assertFalse(getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "2", "Dependency").contains("dirty-cell"), "Dependency column is not Editable");
			
			softAssert.assertFalse(getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "2", "Evaluation Basis").contains("dirty-cell"), "Evaluation Basis column is not Editable");
			
			getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "2", "Unit Price");
			
			softAssert.assertFalse(getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "2", "UOM").contains("dirty-cell"), "UOM column is not Editable");
			
			softAssert.assertFalse(getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "2", "Tax Code 1").contains("dirty-cell"), "Tax Code 1 column is not Editable");
			
			softAssert.assertFalse(getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "2", "Tax Code 2").contains("dirty-cell"), "Tax Code 2 column is not Editable");
			
			//Row 3 Verification
			
			softAssert.assertFalse(getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "3", "Reference").contains("dirty-cell"), "Reference column is not Editable");
			
			softAssert.assertFalse(getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "3", "Dependency").contains("dirty-cell"), "Dependency column is not Editable");
			
			softAssert.assertFalse(getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "3", "Evaluation Basis").contains("dirty-cell"), "Evaluation Basis column is not Editable");
			
			getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "3", "Unit Price");
			
			softAssert.assertFalse(getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "3", "UOM").contains("dirty-cell"), "UOM column is not Editable");
			
			softAssert.assertFalse(getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "3", "Tax Code 1").contains("dirty-cell"), "Tax Code 1 column is not Editable");
			
			softAssert.assertFalse(getCellText(ADD_ENERGY_PRICE_WINDOW_HEADER, "3", "Tax Code 2").contains("dirty-cell"), "Tax Code 2 column is not Editable");
			
			waitForExtJSAjaxComplete(10);
			
			setUnitPriceInGrid(ADD_ENERGY_PRICE_WINDOW_HEADER, "1", "Unit Price", "12");
			
			waitForExtJSAjaxComplete(10);
			
			setUnitPriceInGrid(ADD_ENERGY_PRICE_WINDOW_HEADER, "2", "Unit Price", "13");
			
			waitForExtJSAjaxComplete(10);
			
			setUnitPriceInGrid(ADD_ENERGY_PRICE_WINDOW_HEADER, "3", "Unit Price", "14");
			
			waitForExtJSAjaxComplete(10);
			
			saveClose("energy properties");
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertAll();
			
			Reporter.log("custom energy price is successfully verified in Business Partners Tab", true);
	}
	
	/**
	 * Test Case ID: C60574
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testSPCommoditySpecificSection() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: System View: Supply Points: Commodity-specific section: C60574" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testSPCommoditySpecificSection");
		
		String comElectricity = "Electricity";
		String comFuel = "Fuel";
		String comGas = "Gas";
		String comHeat = "Heat";
		String comWaste = "Waste";
		String comWater = "Water";
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		Tree.expandNavigatorTreeNode(driver, "slnmEnrgArea1 (slnmEnrgArea)");
		
		waitForExtJSAjaxComplete(20);
		
		Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(20);
		
		clickSystemViewTab();

		waitForExtJSAjaxComplete(20);
		
		setEffectiveDateMeterInfrastructure("01-06-2014");
		
		waitForExtJSAjaxComplete(25);
		
		setCommodityClass("Electricity");
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		test.framework.webelement.Tree.clickNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite3 (site3)");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		test.framework.webelement.Tree.clickNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "codePrefix"), "Code Prefix field is Present in the SP Page");
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "code"), "Code field is Present in the SP Page");
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "site"), "site field is Present in the SP Page");
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "energyObject"), "energyObject field is Present in the SP Page");
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "reference"), "Reference field is Present in the SP Page");
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "commodity"), "Commodity field is Present in the SP Page");
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "flowDirection"), "Flow Direction field is Present in the SP Page");
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "supplyPointType"), "Type field is Present in the SP Page");
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "address1"), "Address 1 is Present in the SP Page");
				
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "address2"), "Address 2 is Present in the SP Page");
				
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "zipCode"), "Zip COde is Present in the SP Page");
				
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "city"), "City is Present in the SP Page");
				
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "country"), "Country is Present in the SP Page");
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "description"), "Description is Present in the SP Page");
		
		waitForExtJSAjaxComplete(10);
		
		setCommodity(comElectricity);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(McsElement.isElementDisplayed(driver, XPATH_COMMODITY_SPECIFIC_PROPERTIES), "Commodity Specific Properties is Displayed");
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "supplySystem"), "supplySystem is Present in the SP Page");
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "powerCapacity"), "powerCapacity is Present in the SP Page");
		
		waitForExtJSAjaxComplete(5);
		
		String supplySystem[] = {"High Voltage", "Medium Voltage", "Low Voltage"};
		
		List<String> supplySystemList = getSupplySystemValues(DIALOG_SP);
		
		softAssert.assertEqualsNoOrder(supplySystemList.toArray(), supplySystem, "High, Medium and Low Voltages are present in Dropdown");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(getPowerCapacityText().contains("(kVA)"), "For Power Capacity field the UOM is kVa");
		
		close();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		//Gas Commodity
		
		setCommodityClass(comGas);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodity("Natural gas");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "capacity"), "Capacity is Present in the SP Page");
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "pressureValue"), "Pressure Field is Present in the SP Page");
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "pressureUom"), "Pressure Uom is Present in the SP Page");
		
		softAssert.assertTrue(verifySupplySystemInSP(DIALOG_SP, "supplySystem"), "supplySystem is Not Present in the SP Page");
		
		waitForExtJSAjaxComplete(10);
		
		close();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		// Fuel Commodity
		
		setCommodityClass(comFuel);
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodity("Gasoline");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "capacity"), "Capacity is Present in the SP Page");
		
		softAssert.assertTrue(verifySupplySystemInSP(DIALOG_SP, "supplySystem"), "supplySystem is Not Present in the SP Page");
		
		waitForExtJSAjaxComplete(10);
		
		close();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		// Water Commodity
		
		setCommodityClass(comWater);
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodity("Water");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "capacity"), "Capacity is Present in the SP Page");
		
		softAssert.assertTrue(verifySupplySystemInSP(DIALOG_SP, "supplySystem"), "supplySystem is Not Present in the SP Page");
		
		waitForExtJSAjaxComplete(10);
		
		close();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		// Heat Commodity
		
		setCommodityClass(comHeat);
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodity("Firewood");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "capacity"), "Capacity is Present in the SP Page");
		
		softAssert.assertTrue(verifySupplySystemInSP(DIALOG_SP, "supplySystem"), "supplySystem is Not Present in the SP Page");
		
		waitForExtJSAjaxComplete(10);
		
		close();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		// Waste Commodity
		
		setCommodityClass(comWaste);
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodity("Notebooks");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "capacity"), "Capacity is Present in the SP Page");
		
		softAssert.assertTrue(verifySupplySystemInSP(DIALOG_SP, "supplySystem"), "supplySystem is Not Present in the SP Page");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("System View: Supply Points: Commodity-specific section is succesfully verified", true);
	}
	
	
	/**
	 *  TestCase ID : C60593
	 *  Author : SRD
	 */
	@Test(enabled=true)
	public void testEffectiveDateOfBusiPrtnrShouldBeAtleast1DayAfterPrevious1() throws Exception
	{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:Add Business Partner The EffectiveDate Of The Businesspartner Should Be Atleast One Day After The PreviousOne: C60593" + " </span><br>",
				true);
		
		
		String code = "C60593Cod" + getCurrentDate().substring(getCurrentDate().length()-6);
		String commodity = "Electricity";
		String type = "Default";
		String effectiveDate ="05-05-2014";
		String effectiveDateEarlier = "04-04-2014";
		String effectiveDateAfter  = "06-06-2014";
		String dno = "My Company";
		String commoditySource = "2preBPcmdtSource";
		String tariffCalender = "1preBPTrfClndr";
		String supplier = "aqacompName"; 
		String warningMessage = "The form is invalid. Hover over the fields in red to see the errors.";
		String successMessage = "Your changes have been saved";
	
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testEffectiveDateOfBusiPrtnrShouldBeAtleast1DayAfterPrevious1");
		
		waitAndClick(XPATH_ENERGY);;
		
		waitForExtJSAjaxComplete(20);
		
		//Creation of Supply Point
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		Tree.expandNavigatorTreeNode(driver, "slnmEnrgArea1 (slnmEnrgArea)");
		
		waitForExtJSAjaxComplete(20);
		
		Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(20);
		
		clickSystemViewTab();
		
		waitForExtJSAjaxComplete(20);
		
		int random = (int)(Math.random() * 10)+18; 

		String date = this.getFutureDate(random);
		
		setEffectiveDateMeterInfrastructure(effectiveDate);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setCommodityClass(commodity);
		
		waitForExtJSAjaxComplete(25);
		
		test.framework.webelement.Tree.clickNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite3 (site3)");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		test.framework.webelement.Tree.clickNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
	    waitForExtJSAjaxComplete(50);
	    
	    Reporter.log("Add Supply Point window is opening", true);
	    
	    waitForExtJSAjaxComplete(25);
	     
	    WebDriverWait wait = new WebDriverWait(driver, 100);
	    
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_CODE)));
	    
		setCode(code);
		
		waitForExtJSAjaxComplete(25);
		
		setType(type, ADD_SUPPLY_POINT_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		setCommodity(commodity);
		
		waitForExtJSAjaxComplete(25);
				
		save();
		
		waitForExtJSAjaxComplete(25);
	
		waitForExtJSAjaxComplete(25);
		
		clickBusinessPartnersTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButtonBusinessPartners();
		
		waitForExtJSAjaxComplete(20);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_EFFECTIVEDATE)));
		   
		waitForExtJSAjaxComplete(25);
		
		//Creation of Commodity Supply Point On Business Partners Tab 
		
		setEffectiveDate(effectiveDate);
		
		waitForExtJSAjaxComplete(25);
		
		setCommoditySource(commoditySource);
		
		waitForExtJSAjaxComplete(20);
		
	    waitForExtJSAjaxComplete(20);
		
		setDNOOnBusinessPartners(dno);
		
		waitForExtJSAjaxComplete(20);
		
		setTariffCalendarOnBusinessPartners(tariffCalender);
		
		waitForExtJSAjaxComplete(20);
		
		saveBusinessPartners();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(getInfoBarMsgOfWindow("@class", ENERGY_PROPERTIES).contains(successMessage), "Commodity Supply Point is successfully added");
		
		waitForExtJSAjaxComplete(20);
			
		closeBusinessPartners();
		
		waitForExtJSAjaxComplete(20);
	
		clickAddButtonBusinessPartners();
		
		waitForExtJSAjaxComplete(20);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH_EFFECTIVEDATE)));
		   
		waitForExtJSAjaxComplete(25);
			
		
		//Validating Form With effective date earlier than date for the previous 
		
		setEffectiveDate(effectiveDateEarlier);
		
		waitForExtJSAjaxComplete(25);
		
	    waitForExtJSAjaxComplete(20);
		
		saveBusinessPartners();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(getInfoBarMsgOfWindow("@class", ENERGY_PROPERTIES).contains(warningMessage), "Commodity Supply Point is Cannot Be Saved With Effective Date Earlier Than Date For The Previous");
		
		waitForExtJSAjaxComplete(20);
		
		//Validating Form With effective date to the same day as for the previous 
		
		setEffectiveDate(effectiveDate);
		
		waitForExtJSAjaxComplete(25);
		
	    waitForExtJSAjaxComplete(20);
		
		saveBusinessPartners();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(getInfoBarMsgOfWindow("@class", ENERGY_PROPERTIES).contains(warningMessage), "Commodity Supply Point is Cannot Be Saved With Effective Date To The Same Day As For The Previous ");
		
		waitForExtJSAjaxComplete(20);
		
		//Validating Form With effective date to the day after the previous
		
		setEffectiveDate(effectiveDateAfter);
		
		waitForExtJSAjaxComplete(25);
		
	    waitForExtJSAjaxComplete(20);
		
		saveBusinessPartners();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(getInfoBarMsgOfWindow("@class", ENERGY_PROPERTIES).contains(successMessage), "Commodity Supply Point is successfully added");
		
		waitForExtJSAjaxComplete(20);
		
		closeBusinessPartners();
		
		waitForExtJSAjaxComplete(20);
		
		close();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("It is Observed That We Cannot Save The Supply Point With Same Effective Date or Date earlier Than The Previous", true);
		
		
	}	

	
}