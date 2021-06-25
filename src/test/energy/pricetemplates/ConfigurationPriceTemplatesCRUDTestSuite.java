package test.energy.pricetemplates;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.webelement.Grid;

public class ConfigurationPriceTemplatesCRUDTestSuite extends
		ConfigurationPriceTemplatesPageObject {


	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testConfigurationPriceTemplatesEdit() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Edit Energy PriceTemplates :42634</span><br>",
				true);

		Reporter.log("User edit configuration PriceTemplates: <br>",
				true);
		
		String reference = "test auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
		String country = "Angola";
		String currency = "EUR";
		String status = "Active";
		String region = reference;
		String commodityClass = "Electricity";
		String commodityUOM = "kWh";
		String taxCode1 = "MCS000";
		String taxCode2 = "EXP-MCS001";
		
		String referenceEdited = "test auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
		String countryEdited = "Antarctica";
		String currencyEdited = "USD";
		String statusEdited = "Archived";
		String regionEdited = referenceEdited;
		String commodityClassEdited = "Water";
		String commodityUOMEdited = "cc";
		//String taxCode1commodityUOMEdited = "MCS000";
		//String taxCode2commodityUOMEdited = "MCS000";
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		openConfigurationEntity("Energy Price Templates");

		waitForExtJSAjaxComplete(20);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(reference);
		
		waitForExtJSAjaxComplete(20);
		
		setCountry(country);
		
		waitForExtJSAjaxComplete(20);
		
		setCurrency(currency);
		
		waitForExtJSAjaxComplete(20);
		
		setStatus(status);
		
		waitForExtJSAjaxComplete(20);
		
		setRegion(region);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityClass(commodityClass);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityUOM(commodityUOM);
		
		waitForExtJSAjaxComplete(20);
		
		setTaxCode1(taxCode1);
		
		waitForExtJSAjaxComplete(20);
		
		setTaxCode2(taxCode2);		
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		waitForExtJSAjaxComplete(20);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertEquals(getReference(),reference, reference+" reference before edit");
		
		Assert.assertEquals(getFieldValue("country"),country, country+" country before edit");
		
		Assert.assertEquals(getFieldValue("currency"),currency, currency+" currency before edit");
		
		Assert.assertEquals(getFieldValue("status"),status, status+" status before edit");
		
		Assert.assertEquals(getRegion(),region, region+" region before edit");
		
		Assert.assertEquals(getFieldValue("commodityClass"),commodityClass, commodityClass+" commodityClass before edit");
		
		Assert.assertEquals(getFieldValue("commodityUom"),commodityUOM, commodityUOM+" commodityUOM before edit");
		
		Assert.assertEquals(getFieldValue("taxCode1"),taxCode1, taxCode1+" taxCode1 before edit");
		
		Assert.assertEquals(getFieldValue("taxCode2"),taxCode2, taxCode2+" taxCode2 before edit");
		
		waitForExtJSAjaxComplete(20);
		
		setReference(referenceEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setCountry(countryEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setCurrency(currencyEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setStatus(statusEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setRegion(regionEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityClass(commodityClassEdited);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityUOM(commodityUOMEdited);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
	
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, referenceEdited);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(20);	
		
		Assert.assertEquals(getReference(),referenceEdited, referenceEdited+" reference after edit is wrong");
		
		Assert.assertEquals(getFieldValue("country"),countryEdited, countryEdited+" country after edit is wrong");
		
		Assert.assertEquals(getFieldValue("currency"),currencyEdited, currencyEdited+" currency after edit is wrong");
		
		Assert.assertEquals(getFieldValue("status"),statusEdited, statusEdited+" status after edit is wrong");
		
		Assert.assertEquals(getRegion(),regionEdited, regionEdited+" region after edit is wrong");
		
		Assert.assertEquals(getFieldValue("commodityClass"),commodityClassEdited, commodityClassEdited+" commodityClass after edit is wrong");
		
		Assert.assertEquals(getFieldValue("commodityUom"),commodityUOMEdited, commodityUOMEdited+" commodityUOM after edit is wrong");

		Reporter.log("Configuration Price Templates was succesfully edited", true);

	}

	@Test(enabled = true)
	public void testConfigurationPriceTemplatesDelete() throws IllegalStateException, IOException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Price Templates: 42636 </span><br>",
				true);

		Reporter.log("User delete Price Templates <br>", true);
		
		String reference = "test auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
		String country = "Angola";
		String currency = "EUR";
		String status = "Active";
		String region = reference;
		String commodityClass = "Electricity";
		String commodityUOM = "Wh";
		String taxCode1 = "MCS000";
		String taxCode2 = "EXP-MCS001";
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		openConfigurationEntity("Energy Price Templates");

		waitForExtJSAjaxComplete(20);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		setReference(reference);
		
		waitForExtJSAjaxComplete(20);
		
		setCountry(country);
		
		waitForExtJSAjaxComplete(20);
		
		setCurrency(currency);
		
		waitForExtJSAjaxComplete(20);
		
		setStatus(status);
		
		waitForExtJSAjaxComplete(20);
		
		setRegion(region);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityClass(commodityClass);
		
		waitForExtJSAjaxComplete(20);
		
		setCommodityUOM(commodityUOM);
		
		waitForExtJSAjaxComplete(20);
		
		setTaxCode1(taxCode1);
		
		waitForExtJSAjaxComplete(20);
		
		setTaxCode2(taxCode2);		
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		clickDeleteButton();
		
		clickOnDialogButton("OK");
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, reference), reference+" reference after delete is present");
		
		Reporter.log("Configuration Price Templates was succesfully deleted", true);
		
	}

}
