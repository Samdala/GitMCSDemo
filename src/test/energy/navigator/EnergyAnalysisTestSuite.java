package test.energy.navigator;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class EnergyAnalysisTestSuite extends
		EnergyAnalysisPageObject {


	/**
	 * Edited By : SSU
	 * Rectified Softasserts for 14.
	 * @throws IOException
	 */
	@Test(enabled=true)
	public void EnergyAnalysisCalculatioCheck() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: EnergyAnalysisCalculatioCheck: 39490, 39492" + " </span><br>",
				true);

		Reporter.log("User EnergyAnalysisCalculatioCheck"  + " <br>",
				true);
		
		String locationType = "Energy Object";
		String location = "slnmMetricsEO1";
		String panelID = "";
		String area = "slnmMetricsArea";
		String site = "slnmMetricsSite";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("EnergyAnalysisCalculatioCheck");

		//Navigate to Meters Overview
	
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, site);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, location);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		panelID = getXPanelId(locationType + " \"" +location + "\"");
		
		changeTab(panelID, "Energy Analysis");
		
		waitForExtJSAjaxComplete(20);
		
		////CASE1
		
		//waitForExtJSAjaxComplete(20);
		
		//setCase ("Electricity Usage per Building Area", "Usage", "Electricity", "Space Heating", 
		//		"", "Building Area", "m²",  "Day", "28-12-2013 to 05-01-2014");
		
		setCase ("Electricity Usage per Building Area", "", "", "", 
				"", "", "m²",  "", "28-12-2013 to 05-03-2014");
		
		waitForExtJSAjaxComplete(20);
												 
		softAssert.assertTrue(isDataLabelPresent("Usage of Electricity  (kWh) per Building Area (m²)"), "Label generated correct.");
		
		clickRunAnalysis();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		changeTab(panelID, "Data");
		
		waitForExtJSAjaxComplete(20);
		
		////// Search in table row (val1|val2|val3|val4)
			softAssert.assertTrue(isDataRowValuesPresent("30-01-2014|24000|100|240"), "Row with correct data is present");
			softAssert.assertTrue(isDataRowValuesPresent("31-01-2014|24000|50|480"), "Row with correct data is present");
			softAssert.assertTrue(isDataRowValuesPresent("01-02-2014|24000|200|120"), "Row with correct data is present");
			softAssert.assertTrue(isDataRowValuesPresent("02-02-2014|24000|100|240"), "Row with correct data is present");
			softAssert.assertTrue(isDataRowValuesPresent("03-02-2014|0|100|0"), "Row with correct data is present");
		
		/////
		
		setCase ("", "", "", "", 
				"", "", "m²",  "Month", "28-12-2013 to 05-03-2014");
		
		waitForExtJSAjaxComplete(20);
		
		changeTab(panelID, "Data");
		
		waitForExtJSAjaxComplete(20);
												 
		softAssert.assertTrue(isDataLabelPresent("Usage of Electricity  (kWh) per Building Area (m²)"), "Label generated correct.");
		
		clickRunAnalysis();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		changeTab(panelID, "Data");
		
		waitForExtJSAjaxComplete(20);
		
		////// Search in table row (val1|val2|val3|val4)

			softAssert.assertTrue(isDataRowValuesPresent("01-2014|48000|75.000|640.000"), "Row with correct data is present");
			softAssert.assertTrue(isDataRowValuesPresent("02-2014|48000|103.571|463.448"), "Row with correct data is present");
		
		/////
		
		setCase ("", "", "", "", 
				"", "", "m²",  "Year", "01-01-2013 to 30-12-2015");
		
		
		//waitForExtJSAjaxComplete(20);
												 
		softAssert.assertTrue(isDataLabelPresent("Usage of Electricity  (kWh) per Building Area (m²)"), "Label generated correct.");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickRunAnalysis();
		
		waitForExtJSAjaxComplete(20);
		

		
			changeTab(panelID, "Data");
		
			waitForExtJSAjaxComplete(20);
		
		////// Search in table row (val1|val2|val3|val4)
		
			softAssert.assertTrue(isDataRowValuesPresent("2014|96000|100.237|957.73"), "Row with correct data is present");
		
		
		/*clickNormalizeCDD();
		
		clickRunAnalysis();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		////// Search in table row (val1|val2|val3|val4)
		softAssert.assertTrue(isDataRowValuesPresent("30-01-2014|24000|100|240"), "Row with correct data is present");
		softAssert.assertTrue(isDataRowValuesPresent("31-01-2014|24000|50|480"), "Row with correct data is present");
		softAssert.assertTrue(isDataRowValuesPresent("01-02-2014|24000|200|120"), "Row with correct data is present");
		softAssert.assertTrue(isDataRowValuesPresent("02-02-2014|24000|4.167|5760"), "Row with correct data is present");
		softAssert.assertTrue(isDataRowValuesPresent("03-02-2014|0|N/A|N/A"), "Row with correct data is present");
		
		clickNormalizeHDD();
		
		clickRunAnalysis();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		////// Search in table row (val1|val2|val3|val4)
		softAssert.assertTrue(isDataRowValuesPresent("30-01-2014|24000|100|240"), "Row with correct data is present");
		softAssert.assertTrue(isDataRowValuesPresent("31-01-2014|24000|50|480"), "Row with correct data is present");
		softAssert.assertTrue(isDataRowValuesPresent("01-02-2014|24000|200|120"), "Row with correct data is present");
		softAssert.assertTrue(isDataRowValuesPresent("02-02-2014|24000|4.167|5760"), "Row with correct data is present");
		softAssert.assertTrue(isDataRowValuesPresent("03-02-2014|0|N/A|N/A"), "Row with correct data is present");
			
		*/
		softAssert.assertAll();
		
		Reporter.log("Weather station was successfully linked to location.", true);
	}
}	 
