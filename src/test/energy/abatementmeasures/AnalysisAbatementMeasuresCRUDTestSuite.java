package test.energy.abatementmeasures;

import java.io.IOException;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class AnalysisAbatementMeasuresCRUDTestSuite extends
		AnalysisAbatementMeasuresPageObject {




	 @Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testAnalysisAbatementMeasuresEdit() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Edit Abatement Measure: C11097, c11099(Testrail) " + " </span><br>",
				true);

		Reporter.log("User edits abatement measures "  + " <br>",
				true);
		
		String date = "03-09-2013";
		String reference = "test auto abt" + getCurrentDate().substring(getCurrentDate().length()-6);
		String costTotal = "13";
		String subsidy = "10";
		String costSaved = "80";
		String annCommoditySaving = "10";
		String payBackPeriod = "2";
		String annualEmissAbat = "5";
		String margAbatCostIndx = "-13.4";
		String returnCapitalInvest = "615.38462";
		String status = "Default";
		String proposer = "My Company";
		String commodity = "Electricity";
		String annComoditySavingUOM = "kiloWatthour";
		String type = "Default";
		String commEndUse = "Cooking";
		String costType = "Actual";
		String currency = "EUR - European euro";
		
		
		
		String dateEdited = "05-10-2014";
		String referenceEdited = "test auto abt ed" + getCurrentDate().substring(getCurrentDate().length()-6);
		String commEndUseEdited = "Lighting";
		String costTypeEdited = "Estimated";
		String commodityEdited = "Natural gas";
		String annComoditySavingUOMEdited = "cubic meter";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("analysisAbatementMeasureEdit");
		
//		expandApplication();

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandAnalysis();
		
		openAnalysisEntity("Abatement Measures");

		waitForExtJSAjaxComplete(20);
		
		clickAddButton(ABATEMENT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setReference(reference);
		
		setCommodity(commodity);
		
		waitForExtJSAjaxComplete(25);
		
		setDate(date);
		
		setProposer(proposer);
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getAnnualCommoditySavingUom(), annComoditySavingUOM, "Annual Commodity Saving UOM was not AUTOMATICALLY set in accordance to Commodity value before edit");
		
		if (!getAnnualCommoditySavingUom().equals(annComoditySavingUOM)){setAnnualCommoditySavingUom(annComoditySavingUOM);}
		
		setTotalCost(costTotal);
		
		setSubsidy(subsidy);
		
		setAnnualCommoditySaving(annCommoditySaving);
		
		setCostSaving(costSaved);
		
		setStatus(status);
		
		setProposer(proposer);
		
		setType(type);
		
		setCommodityEndUse(commEndUse);
		
		setCostTypeExtjscript(costType);
			
		setCurrencyExtjscript(currency);
				
		softAssert.assertEquals(getPaybackPeriod(),payBackPeriod, payBackPeriod+" payBackPeriod before edit is wrong");
		
		softAssert.assertEquals(getAnnualEmissionAbatement(),annualEmissAbat, annualEmissAbat+" annualEmissAbat before edit is wrong");
		
		softAssert.assertEquals(getMarginalAbatementCost(),margAbatCostIndx, margAbatCostIndx+" margAbatCostIndx before edit is wrong");
		
		softAssert.assertEquals(getReturnCapitalInvestment(),returnCapitalInvest, returnCapitalInvest+" margAbatCostIndx before edit is wrong");
		
		saveClose();
		
		Grid.checkRowInGriByTextValue(driver, reference);
		
		clickEditButton(ABATEMENT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getReference(),reference, reference+" reference before edit is wrong");
		
		softAssert.assertEquals(getDate(),date, date+ " date before edit is wrong");
		
		softAssert.assertEquals(getProposer(),proposer, proposer+ " proposer before edit is wrong");
		
		softAssert.assertEquals(getStatus(),status, status+ " status before edit is wrong");
		
		softAssert.assertEquals(getType(),type, type+ " type before edit is wrong");
		
		softAssert.assertEquals(getCostType(),costType, costType+ " cost type before edit is wrong");
		
		softAssert.assertEquals(getCommodity(),commodity, commodity+ " commodity before edit is wrong");
		
		softAssert.assertEquals(getCommodityEndUse(),commEndUse, commEndUse+ " commodity End use before edit is wrong");
		
		softAssert.assertEquals(getCurrency(),currency, currency+ " currency before edit is wrong");
		
		softAssert.assertEquals(getAnnualCommoditySavingUom(),annComoditySavingUOM, annComoditySavingUOM+ " annCommodity Saving UOM before edit is wrong");
		
		softAssert.assertEquals(getTotalCost(),costTotal, costTotal+ " costTotal before edit is wrong");
		
		softAssert.assertEquals(getSubsidy(),subsidy, subsidy+ " subsidy before edit is wrong");
		
		softAssert.assertEquals(getCostSaving(),costSaved, costSaved+ " cost Saving before edit is wrong");

		softAssert.assertEquals(getAnnualCommoditySaving(),annCommoditySaving, annCommoditySaving+ " annual commodity saving before edit is wrong");
		
		softAssert.assertEquals(getPaybackPeriod(),payBackPeriod, payBackPeriod+" payBackPeriod before edit is wrong");
		
		softAssert.assertEquals(getAnnualEmissionAbatement(),annualEmissAbat, annualEmissAbat+" annualEmissAbat before edit is wrong");
		
		softAssert.assertEquals(getMarginalAbatementCost(),margAbatCostIndx, margAbatCostIndx+" margAbatCostIndx before edit is wrong");
		
		softAssert.assertEquals(getReturnCapitalInvestment(),returnCapitalInvest, returnCapitalInvest+" margAbatCostIndx before edit is wrong");

		setReference(referenceEdited);
		
		setCommodity(commodityEdited);
		
		waitForExtJSAjaxComplete(25);
		
		setDate(dateEdited);
		
		setCommodityEndUse(commEndUseEdited);
		
		softAssert.assertEquals(getAnnualCommoditySavingUom(), annComoditySavingUOMEdited, "Annual Commodity Saving UOM was not AUTOMATICALLY set in accordance to Commodity value after edit");
		
		if (!getAnnualCommoditySavingUom().equals(annComoditySavingUOMEdited)){setAnnualCommoditySavingUom(annComoditySavingUOMEdited);}
		
		setCommodityEndUse(commEndUseEdited);
		
		setCostTypeExtjscript(costTypeEdited);
		
		saveClose();

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, referenceEdited,"@class", "x-grid3"), "edited abatement measures is not present");
		
		Grid.checkRowInGriByTextValue(driver, referenceEdited);
		
		clickEditButton(ABATEMENT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getReference(),referenceEdited, referenceEdited+" referenceEdited is wrong");
		
		softAssert.assertEquals(getDate(),dateEdited, dateEdited+ " dateEdited is wrong");
		
		softAssert.assertEquals(getProposer(),proposer, proposer+ " proposer after edit is wrong");
		
		softAssert.assertEquals(getStatus(),status, status+ " status after edit is wrong");
		
		softAssert.assertEquals(getType(),type, type+ " type after edit is wrong");
		
		softAssert.assertEquals(getCostType(),costTypeEdited, costTypeEdited+ " costEdited type is wrong");
		
		softAssert.assertEquals(getCommodity(),commodityEdited, commodityEdited+ " commodity after edit is wrong");
		
		softAssert.assertEquals(getCommodityEndUse(),commEndUseEdited, commEndUseEdited+ " commodity End use after edit is wrong");
		
		softAssert.assertEquals(getCurrency(),currency, currency+ " currency after edit is wrong");
		
		softAssert.assertEquals(getAnnualCommoditySavingUom(),annComoditySavingUOMEdited, annComoditySavingUOMEdited+ " annCommodityEdited Saving UOM before edit is wrong");
		
		softAssert.assertEquals(getTotalCost(),costTotal, costTotal+ " costTotal after edit is wrong");
		
		softAssert.assertEquals(getSubsidy(),subsidy, subsidy+ " subsidy after edit is wrong");
		
		softAssert.assertEquals(getCostSaving(),costSaved, costSaved+ " cost Saving after edit is wrong");

		softAssert.assertEquals(getAnnualCommoditySaving(),annCommoditySaving, annCommoditySaving+ " annual commodity saving after edit is wrong");
		
		softAssert.assertEquals(getPaybackPeriod(),payBackPeriod, payBackPeriod+" payBackPeriod after edit is wrong");
		
//		softAssert.assertEquals(getAnnualEmissionAbatement(),annualEmissAbat, annualEmissAbat+" annualEmissAbat after edit is wrong");
		
//		softAssert.assertEquals(getMarginalAbatementCost(),margAbatCostIndx, margAbatCostIndx+" margAbatCostIndx after edit is wrong");
		
		softAssert.assertEquals(getReturnCapitalInvestment(),returnCapitalInvest, returnCapitalInvest+" returnCapital after edit is wrong");
		
		softAssert.assertAll();

		close();
		
		Reporter.log("Abatement Measures "  + " was succesfully edited", true);

	}

	@Test(enabled =true, retryAnalyzer=RetryAnalyzer.class)
	public void analysisAbatementMeasureDelete() throws IllegalStateException, IOException {

		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Abatement Measure: c11098(testrail) " + " </span><br>",
				true);

		Reporter.log("User deletes abatement measures "  + " <br>",
				true);
		
		String date = "03-09-2013";
		String reference = "test auto del abt" + getCurrentDate().substring(getCurrentDate().length()-6);
//		String status = "Default";
		String proposer = "My Company";
		String commodity = "Electricity";
		String annComoditySavingUOM = "British Terminal Unit";
//		String type = "Default";
//		String costType = "Estimated";
//		String currency = "EUR - European euro";
	
//		expandApplication();

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandAnalysis();
		
		openAnalysisEntity("Abatement Measures");

		waitForExtJSAjaxComplete(20);
		
		clickAddButton(ABATEMENT_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setReference(reference);
		
		setCommodity(commodity);
		
		waitForExtJSAjaxComplete(20);
		
		setDate(date);
		
//		setStatus(status);
		
		setProposer(proposer);
		
//		setType(type);
		
//		setCommodityEndUse(commEndUse);
		
//		setCostTypeExtjscript(costType);
		
//		setCurrencyExtjscript(currency);
		
		setAnnualCommoditySavingUom(annComoditySavingUOM);
		
		saveClose();
		
		Grid.checkRowInGriByTextValue(driver, reference);
		
		clickDeleteButton(ABATEMENT_GRID_CLASS);
				
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, reference), reference+" reference after delete is present");
		
		Reporter.log("Abatement measure was succesfully deleted", true);
		
	}

}
