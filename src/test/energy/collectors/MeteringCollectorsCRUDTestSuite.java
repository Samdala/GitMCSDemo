package test.energy.collectors;

import java.io.IOException;



import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;

public class MeteringCollectorsCRUDTestSuite extends
		MeteringCollectorsPageObject {

	 @Test(enabled=true)
	public void testAnalysisCollectorEdit() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Edit Collector " + " </span><br>",
				true);

		Reporter.log("User edits Collector: C19917 "  + " <br>",
				true);
		
		String reference = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto " + getCurrentDate().substring(getCurrentDate().length()-6);

		
		String referenceEdited = "test auto collector ed " + getCurrentDate().substring(getCurrentDate().length()-6);
		String codeEdited = "test auto collector ed " + getCurrentDate().substring(getCurrentDate().length()-6);
		String meterRef = "2preMeter";
		String supPoint = "1preSP";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("CollectorsEdit");

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, "slnmEnrgArea1 (slnmEnrgArea)");
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite2");
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickSystemViewTab();

		waitForExtJSAjaxComplete(20);
		
		setEffectiveDate("01-01-2014");
		
		waitForExtJSAjaxComplete(25);
		
		setCommodityClass("Electricity");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		clickOnMeter(supPoint);//click on supply point
		
		clickAddExistingMeter(meterRef);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnMeter(meterRef);
		
		waitForExtJSAjaxComplete(20);
		
		clickCreateCollector();		
		
		waitForExtJSAjaxComplete(20);
	
		setCode(code);
		
		setReference(reference);
		
		save();
		
		waitForExtJSAjaxComplete(25);
		
		close();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnMeter(reference); // clickCollector
		
		waitForExtJSAjaxComplete(20); 
		
		clickEdidCollector();		

		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getReference(),reference, reference+" Reference before edit ");
		
		softAssert.assertEquals(getCode(),code, code+ " Code before edit ");
		
		setCode(codeEdited);
		
		setReference(referenceEdited);
		
		waitForExtJSAjaxComplete(25);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnMeter("test auto collector ed");// clickCollector
		
		waitForExtJSAjaxComplete(20);
		
		clickEdidCollector();		
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getReference(),referenceEdited, referenceEdited+" Reference after edit ");
		
		softAssert.assertEquals(getCode(),codeEdited, codeEdited+ " Code after edit ");

		close();
		
		softAssert.assertAll();
		
		Reporter.log("Collector"  + " was successfully edited", true);

	}

	@Test(enabled = false)
	public void testAnalysisCollectorDelete() throws IllegalStateException, IOException {

		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Collector " + " </span><br>",
				true);

		Reporter.log("User deletes Collector: C19918 "  + " <br>",
				true);
		
		String reference = "test auto collector del" + getCurrentDate().substring(getCurrentDate().length()-6);
		String code = "test auto collector del" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandMetering();
		
		openAnalysisEntity("Collectors");

		waitForExtJSAjaxComplete(20);
		
		clickAddButton(COLLECTORS_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		setReference(reference);	
		
		setCode(code);
		
		waitForExtJSAjaxComplete(25);
		
		save();
		
		waitForExtJSAjaxComplete(25);
		
		close();
		
		clickRowInGrid(reference);
		
		waitForExtJSAjaxComplete(25);
		
		clickDeleteButton(COLLECTORS_GRID_CLASS);
				
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(!isRowInGridChanelPresent(reference), reference+" reference after delete absend ");
		
		Reporter.log("Collector was successfully deleted", true);
		
	}

}
