package test.energy.supplypoints122;

import java.io.IOException;




import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import test.framework.webelement.Tree;

public class MeteringSuplyPointsCRUDTestSuite122 extends
		MeteringSuplyPointsPageObject {




	 @Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testMeteringSupplyPointEdit() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Edit Supply Point: c19712, c25331, c25332, c25333" + " </span><br>",
				true);

		Reporter.log("User edits supply point:  "  + " <br>",
				true);
		
		String code = "test auto sp" + getCurrentDate().substring(getCurrentDate().length()-6);
		String site = "slnmEnrgSite1";
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
				String typeEdited = "DEFAULT";
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
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
		
		waitForExtJSAjaxComplete(20);
		
		clickSystemViewTab();

		waitForExtJSAjaxComplete(20);
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		clickReference();
		
		waitForExtJSAjaxComplete(20);
		
		String reference = getReference();
		
		Reporter.log("Check if Reference is equal to Code Predix + Code", true);
		softAssert.assertEquals(reference, getCodePrefix()+getCode(), "Supply Point Reference doesn't equals Code Prefix + Code");
		
		softAssert.assertEquals("slnmEnrgSite1", getSite(), "Supply Point Site is prefilled");
		
		//setSite(site);
		
		setType(type);
		
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
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
		
		waitForExtJSAjaxComplete(20);
		
		clickSystemViewTab();

		waitForExtJSAjaxComplete(20);
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		clickReference();
		
		waitForExtJSAjaxComplete(20);
		
		reference = getReference();
		
		Reporter.log("Check if Reference is equal to Code Predix + Code", true);
		softAssert.assertEquals(reference, getCodePrefix()+getCode(), "Supply Point Reference doesn't equals Code Prefix + Code");
		
		softAssert.assertEquals("slnmEnrgSite1", getSite(), "Supply Point Site is prefilled");
		
		//setSite(site);
		
		setType(type);
		
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
		
		checkRowInGriByTextValueAndClick(driver, code);
		
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
		
		setType(typeEdited);
		
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

		checkRowInGriByTextValueAndClick(driver, code);
		
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
		
		setType(typeEdited);
		
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

		checkRowInGriByTextValueAndClick(driver, codeEdited);
		
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
	 
	 @Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testMeteringSupplyPointCapacity() throws IOException  {

			Reporter.log("<span style='font-weight:bold;color:#000033'> "
					+ "Test: Edit Supply Point Capacity: c19876" + " </span><br>",
					true);

			Reporter.log("User edits supply point capacity:  "  + " <br>",
					true);
			
			String code = "test auto sp" + getCurrentDate().substring(getCurrentDate().length()-6);
			//String site = "slnmEnrgSite1";
			String commodity = "Gas";
			String capacity = "125";
						
			String capacityEdited = "0.21";		
			
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.setMethodName("meteringSupplyPointCapacityEdit");

			waitAndClick(XPATH_ENERGY);
			
			waitForExtJSAjaxComplete(20);
			
			expandNavigator();
			
			waitForExtJSAjaxComplete(20);
			
			test.framework.webelement.Tree.expandNavigatorTreeNode(driver, "slnmEnrgArea1 (slnmEnrgArea)");
			
			waitForExtJSAjaxComplete(20);
			
			test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
			
			waitForExtJSAjaxComplete(20);
			
			clickSystemViewTab();

			waitForExtJSAjaxComplete(20);
			
			setCommodityClass("Gas");
			
			waitForExtJSAjaxComplete(20);
			
			clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			setCode(code);
			
			//setSite(site);
					
			waitForExtJSAjaxComplete(25);
			
			setCommodity(commodity);
			
			waitForExtJSAjaxComplete(25);
					
			setCapacity(capacity);
			
			waitForExtJSAjaxComplete(25);
			
			save();
			
			waitForExtJSAjaxComplete(25);
			
			close();
			
			waitForExtJSAjaxComplete(25);
			
			expandMetering();
			
			waitForExtJSAjaxComplete(20);
			
			openMeteringEntity("Supply Points");
			
			waitForExtJSAjaxComplete(20);
					
			checkRowInGriByTextValueAndClick(driver, code);
			
			clickEditButton(SUPPLY_POINT_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			Reporter.log("Check if Capacity, Pressure and Pressure UOM are correct after creation", true);
			
			softAssert.assertEquals(getCapacity(),capacity, capacity+" Capacity after creation is wrong");
			
			setCapacity(capacityEdited);
			
			waitForExtJSAjaxComplete(20);
			
			save();
			
			waitForExtJSAjaxComplete(25);
			
			close();
			
			waitForExtJSAjaxComplete(25);

			checkRowInGriByTextValueAndClick(driver, code);
			
			clickEditButton(SUPPLY_POINT_GRID_CLASS);
			
			waitForExtJSAjaxComplete(20);

			Reporter.log("Check if Capacity, Pressure, Pressure UOM are correct after editing", true);
			
			softAssert.assertEquals(getCapacity(),capacityEdited, capacityEdited+" Capacity after edit is wrong");
			
			softAssert.assertAll();

			close();
			
			Reporter.log("Supply Point with Capacity, Pressure and Pressure UOM"  + " was succesfully created and edited", true);

		}

	 //Temporarily Supply Points can't be deleted!
	@Test(enabled = false, retryAnalyzer=RetryAnalyzer.class)
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
		
		waitForExtJSAjaxComplete(20);
		
		clickSystemViewTab();

		waitForExtJSAjaxComplete(20);
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);
		
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
		
		checkRowInGriByTextValueAndClick(driver, code);
		
		clickDeleteButton(SUPPLY_POINT_GRID_CLASS);
				
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, code), code+" code after delete is present");
		
		Reporter.log("Supply Point was succesfully deleted", true);
		
	}
	
	/**
	 * Test Case ID: C60574
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testSPCommoditySpecificSection() throws IOException  {

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
		
		Tree.expandNavigatorTreeNode(driver, "slnmEnrgArea2");
		
		waitForExtJSAjaxComplete(20);
		
		Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite3");
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickSystemViewTab();

		waitForExtJSAjaxComplete(20);
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "codePrefix"), "Code Prefix field is Present in the SP Page");
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "code"), "Code field is Present in the SP Page");
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "site"), "site field is Present in the SP Page");
		
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
		
		waitForExtJSAjaxComplete(20);
		
		setCommodity("Natural gas");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "capacity"), "Capacity is Present in the SP Page");
		
		waitForExtJSAjaxComplete(10);
		
		close();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		// Fuel Commodity
		
		setCommodityClass(comFuel);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodity("Gasoline");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "capacity"), "Capacity is Present in the SP Page");
		
		waitForExtJSAjaxComplete(10);
		
		close();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		// Water Commodity
		
		setCommodityClass(comWater);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodity("Water");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "capacity"), "Capacity is Present in the SP Page");
		
		waitForExtJSAjaxComplete(10);
		
		close();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		// Heat Commodity
		
		setCommodityClass(comHeat);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodity("Firewood");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "capacity"), "Capacity is Present in the SP Page");
		
		waitForExtJSAjaxComplete(10);
		
		close();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(10);
		
		// Waste Commodity
		
		setCommodityClass(comWaste);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddSupplyPointButton(SUPPLY_POINT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodity("Notebooks");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(verifyElementsInSP(DIALOG_SP, "capacity"), "Capacity is Present in the SP Page");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("System View: Supply Points: Commodity-specific section is succesfully verified", true);
	}

}
