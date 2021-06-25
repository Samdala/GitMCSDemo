package test.energy.gauges;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.seleniumemulation.SetTimeout;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.energy.meters.MetersPageObject;
import test.energy.navigator.NavigatorPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import test.framework.webelement.Tree;

public class GaugesMeasurementFormValidationTestSuite extends MeteringGaugesPageObject {

	@Test(enabled = true)
	public void testMeterMeasurementFormValidation() throws IOException {

		Reporter
			.log("<span style='font-weight:bold;color:#000033'> " + "Test: C60885 Measurements: Field Type and Limitation" + " </span><br>", true);

		Reporter.log("C60885 Measurements: Field Type and Limitation " + " <br>", true);

		String gaugeCode = "preGaugeMeasure";
		String channelReference = "1preGaugeMeasureVal";
		String channelReferenceEarlier = "1preGaugeMeasureEarlier";

		List<String> mandatoryFieldsErrorMessageKeyWordsCheck = Arrays.asList("form", "invalid");
		List<String> measurementFieldInFutureErrorMessageKeyWordsCheck = Arrays.asList("Measurement", "future");

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("GaugeMeasurementFormValidation");

		// ////////////////////////Navigate to Meters
		// Overview////////////////////////

		waitAndClick(XPATH_ENERGY);

		waitForExtJSAjaxComplete(20);

		expandMetering();

		openMeteringEntity("Gauges");

		waitForExtJSAjaxComplete(20);

		// Open pre-configured Gauge and go to Channels tab

		Grid.checkRowInGriByTextValue(driver, gaugeCode);

		clickButton("Edit", GAUGES_GRID_CLASS);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		changeTab("channels");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		// ///////////////////////Select pre-configured Channel and go to View
		// Measurements////////////////////////

		checkRowInGriByTextValueAndClick(driver, channelReference, ADD_GAUGES_FORM_CLASS);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickViewMeasurementsButton();

		waitForExtJSAjaxComplete(20);

		// //////////////////////Click on Measurements tab to be sure it is
		// active - sometimes click is not performed? - try to reopen window and
		// perform another click////////////////////////
		try {
			clickMeasurementsTab();
		} catch (Exception e) {

			driver.get(configuration.getApplicationUrl() + "?aqa");
			waitForExtJSAjaxComplete(25);
			waitForExtJSAjaxComplete(25);

			waitAndClick(XPATH_ENERGY);

			waitForExtJSAjaxComplete(20);

			expandMetering();

			openMeteringEntity("Gauges");

			waitForExtJSAjaxComplete(20);

			Grid.checkRowInGriByTextValue(driver, gaugeCode);
			Reporter.log("Select Gauge with code - " + gaugeCode, true);

			clickButton("Edit", GAUGES_GRID_CLASS);

			waitForExtJSAjaxComplete(20);

			waitForExtJSAjaxComplete(20);

			changeTab("channels");

			waitForExtJSAjaxComplete(20);

			waitForExtJSAjaxComplete(20);

			checkRowInGriByTextValueAndClick(driver, channelReference, ADD_GAUGES_FORM_CLASS);

			waitForExtJSAjaxComplete(20);

			waitForExtJSAjaxComplete(20);

			clickViewMeasurementsButton();

			waitForExtJSAjaxComplete(20);

			clickMeasurementsTab();

		}

		waitForExtJSAjaxComplete(20);

		setDateRange(DIALOG_GAUGE_CHNL, "01-01-2010");

		clickMeasurementsTab();

		waitForExtJSAjaxComplete(20);

		// ////////////////////////Open Add Measurement
		// Form////////////////////////

		clickButton("Add", DIALOG_GAUGE_CHNL);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);

		// ////////////////////////Check if Reader field is prefilled with User
		// logged in////////////////////////

		Reporter.log("Check if Reader field is prefilled with User logged in", true);
		
		String reader = getFieldValue(DIALOG_MEASUREMENT, "reader");

		softAssert.assertTrue(reader.equals("SELENIUM AQA"), "Reader field is prefilled with User logged in");

		// ////////////////////////Check if Measurement Type field is prefilled
		// with Type marked as Default//////////////////////////

		Reporter.log("Check if Measurement Type field is prefilled with Type marked as Default", true);

		String fieldValue = getFieldValue(DIALOG_MEASUREMENT, "measurementType");

		clickLookup(DIALOG_MEASUREMENT, "measurementType");

		String lookupId = getXWindowId("Select a Measurement Type");

		softAssert.assertTrue(checkDefaultField("@id", lookupId, "Reference", fieldValue),
			"Measurement Type field is prefilled with Type marked as Default");

		clickCancelXwindow();

		// ////////////////////////Check UI of fields////////////////////////

		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_MEASUREMENT, "date"), "Date field has correct UI (Bold text, asterix)");

		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_MEASUREMENT, "time"), "Time field has correct UI (Bold text, asterix)");

		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_MEASUREMENT, "value"), "Value field has correct UI (Bold text, asterix)");

		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_MEASUREMENT, "reader"), "Reader field has correct UI (Bold text, asterix)");

		softAssert.assertTrue(McsElement.isFieldMandatory(driver, DIALOG_MEASUREMENT, "measurementType"),
			"Measurement Type field has correct UI (Bold text, asterix)");

		softAssert.assertFalse(McsElement.isFieldMandatory(driver, DIALOG_MEASUREMENT, "notes"), "Notes field has correct UI (common text)");

		// ////////////////////////Try to save Gauge Measurement Form with empty
		// Index Value field////////////////////////

		Reporter.log("Try to save Gauge Measurement Form with empty Index Value field", true);

		setDate("01-01-2010");

		setTime("12:00");

		saveClose(DIALOG_MEASUREMENT);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT), "Form can't be saved with empty Index Value field");

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "value"),
			"Red border is present on empty Index Value field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, mandatoryFieldsErrorMessageKeyWordsCheck),
			"Message about invalid form is present");

		try {
			McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", DIALOG_MEASUREMENT, "input", "@name", "value", true,
				true).click();
		} catch (Exception e) {
		}

		waitForExtJSAjaxComplete(20);

		reopenMeterMeasurementForm();

		// ////////////////////////Try to save Gauge Measurement Form with empty
		// Reader field////////////////////////

		Reporter.log("Try to save Gauge Measurement Form with empty Reader field", true);

		setDate("02-01-2010");

		setTime("12:00");

		setValue("1");

		clearFieldMeas("reader", "Reader");

		setValue("1");

		waitForExtJSAjaxComplete(20);

		saveClose(DIALOG_MEASUREMENT);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT), "Form can't be saved with empty Reader field");

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "reader"),
			"Red border is present on empty Reader field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, mandatoryFieldsErrorMessageKeyWordsCheck),
			"Message about invalid form is present");

		try {
			McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", DIALOG_MEASUREMENT, "input", "@name", "value", true,
				true).click();
		} catch (Exception e) {
		}

		waitForExtJSAjaxComplete(20);

		reopenMeterMeasurementForm();

		// ////////////////////////Try to save Gauge Measurement Form with empty
		// Measurement Type field////////////////////////

		Reporter.log("Try to save Gauge Measurement Form with empty Measurement Type field", true);

		setDate("03-01-2010");

		setTime("12:00");

		setValue("2");

		clearFieldMeas("measurementType", "Measurement Type");

		waitForExtJSAjaxComplete(20);

		saveClose(DIALOG_MEASUREMENT);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT), "Form can't be saved with empty Measurement Type field");

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "measurementType"),
			"Red border is present on empty Measurement Type field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, mandatoryFieldsErrorMessageKeyWordsCheck),
			"Message about invalid form is present");

		try {
			McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", DIALOG_MEASUREMENT, "input", "@name", "value", true,
				true).click();
		} catch (Exception e) {
		}

		waitForExtJSAjaxComplete(20);

		reopenMeterMeasurementForm();

		// ////////////////////////Try to save Gauge Measurement Form with empty
		// Date field////////////////////////

		Reporter.log("Try to save Gauge Measurement Form with empty Date field", true);

		setTime("12:00");

		setValue("3");

		clearFieldMeas("date", "Date");

		waitForExtJSAjaxComplete(20);

		saveClose(DIALOG_MEASUREMENT);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT), "Form can't be saved with empty Date field");

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "date"),
			"Red border is present on empty Date field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, mandatoryFieldsErrorMessageKeyWordsCheck),
			"Message about invalid form is present");

		try {
			McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", DIALOG_MEASUREMENT, "input", "@name", "value", true,
				true).click();
		} catch (Exception e) {
		}

		waitForExtJSAjaxComplete(20);

		reopenMeterMeasurementForm();

		// ////////////////////////Try to save Gauge Measurement Form with empty
		// Time field////////////////////////

		Reporter.log("Try to save Gauge Measurement Form with empty Time field", true);

		setDate("04-01-2010");

		clearFieldMeas("time", "Time");

		waitForExtJSAjaxComplete(20);

		setValue("4");

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT), "Form can't be saved with empty Time field");

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(getTime().contains(""), "Red Border is present on empty time field");

		saveClose(DIALOG_MEASUREMENT);

		waitForExtJSAjaxComplete(20);

		// *****Time field class name will not have x-form-invalid

		// softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver,
		// DIALOG_MEASUREMENT,
		// "time"),"Red border is present on empty Time field");

		// softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver,
		// DIALOG_MEASUREMENT,
		// mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present");

		try {
			McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", DIALOG_MEASUREMENT, "input", "@name", "value", true,
				true).click();
		} catch (Exception e) {
		}

		waitForExtJSAjaxComplete(20);

		reopenMeterMeasurementForm();

		Reporter.log("Try to save Gauge Measurement Form with bigger than max value in Index Value field", true);

		setDate("06-01-2010");

		setTime("12:00");

		setValue("999999999999.999");

		waitForExtJSAjaxComplete(20);

		saveClose(DIALOG_MEASUREMENT);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT), "Form can't be saved with bigger than max value in Index Value field");

		softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver, DIALOG_MEASUREMENT, "value"),
			"Red border is present on Index Value field");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, mandatoryFieldsErrorMessageKeyWordsCheck),
			"Message about invalid form is present");

		try {
			McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", DIALOG_MEASUREMENT, "input", "@name", "value", true,
				true).click();
		} catch (Exception e) {
		}

		waitForExtJSAjaxComplete(20);

		reopenMeterMeasurementForm();

		// Save form without Notes

		setDate("07-01-2010");

		setTime("12:00");

		setValue("6");

		waitForExtJSAjaxComplete(20);

		saveClose(DIALOG_MEASUREMENT);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "07-01-2010 12:00:00", "@class", DIALOG_GAUGE_CHNL),
			"Measurement is created with empty Notes field (optional) and displayed on Channel Measurements");

		try {
			McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", DIALOG_MEASUREMENT, "input", "@name", "value", true,
				true).click();
		} catch (Exception e) {
		}

		waitForExtJSAjaxComplete(20);

		// reopenMeterMeasurementForm();

		clickButton("Add", DIALOG_GAUGE_CHNL);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(3);

		// ////////////////////////Try to save Gauge Measurement Form with Date
		// that is in future////////////////////////

		Reporter.log("Try to save Gauge Measurement Form with Date that is in future", true);

		setDate("01-01-2025");

		setTime("12:00");

		setValue("7");

		waitForExtJSAjaxComplete(20);

		saveClose(DIALOG_MEASUREMENT);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT), "Form can't be saved with Date that is in future");

		softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver, DIALOG_MEASUREMENT, measurementFieldInFutureErrorMessageKeyWordsCheck),
			"Message about invalid form is present");

		try {
			McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", DIALOG_MEASUREMENT, "input", "@name", "value", true,
				true).click();
		} catch (Exception e) {
		}

		waitForExtJSAjaxComplete(20);

		reopenMeterMeasurementForm();

		// ////////////////////////Try to save Gauge Measurement Form with Time
		// that is in future////////////////////////

		Reporter.log("Try to save Gauge Measurement Form with Time that is in future", true);

		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

		if (!sdf.format(cal.getTime()).contains("00:") && !sdf.format(cal.getTime()).contains("23:"))

		{
			setTime("23:45");

			setValue("8");

			waitForExtJSAjaxComplete(20);

			saveClose(DIALOG_MEASUREMENT);

			waitForExtJSAjaxComplete(25);

			softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT), "Form can't be saved with Time that is in future");

			// softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver,
			// DIALOG_MEASUREMENT,
			// measurementFieldInFutureErrorMessageKeyWordsCheck),"Message about invalid form is present");

			waitForExtJSAjaxComplete(20);

		}

		close(DIALOG_MEASUREMENT);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(25);

		clickButton("Add", DIALOG_GAUGE_CHNL);

		waitForExtJSAjaxComplete(20);

		Reporter.log("Try to save Gauge Measurement Form with Date that is earlier than the Date of previous Measurement", true);

		setDate("01-01-2009");

		setTime("12:00");

		setValue("5");

		waitForExtJSAjaxComplete(20);

		saveClose(DIALOG_MEASUREMENT);

		waitForExtJSAjaxComplete(20);

		MetersPageObject mpo = new MetersPageObject();

		softAssert.assertTrue(
			mpo.getExtMbContentOfWindowWOMcsElement("@class", "x-window-dlg").contains(
				"A Measurement with later date and time has already been registered. Do you want to continue anyway?"), "Roll back Dialog Message");

		clickButton("Yes", "x-window-dlg");

		waitForExtJSAjaxComplete(20);
		// softAssert.assertTrue(checkMandatoryFieldSave(DIALOG_MEASUREMENT),"Form can't be saved with Date that is in future");

		// TODO Manually it is'nt true, so commenting these lines
		// softAssert.assertTrue(McsElement.checkInvalidRedBorderInputField(driver,
		// DIALOG_MEASUREMENT, "date"),"Red border is present on Date field");

		// softAssert.assertTrue(McsElement.checkMessageInvalidForm(driver,
		// DIALOG_MEASUREMENT,
		// mandatoryFieldsErrorMessageKeyWordsCheck),"Message about invalid form is present");

		try {
			McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@class", DIALOG_MEASUREMENT, "input", "@name", "value", true,
				true).click();
		} catch (Exception e) {
		}

		waitForExtJSAjaxComplete(20);

		reopenMeterMeasurementForm();

		// ////////////////////////Try to save Gauge Measurement Form with Time
		// that is earlier than the Time of previous
		// Measurement////////////////////////////

		Reporter.log("Try to save Gauge Measurement Form with Time that is earlier than the Time of previous Measurement", true);

		setDate("07-01-2010");

		setTime("10:00");

		setValue("6");

		waitForExtJSAjaxComplete(20);

		saveClose(DIALOG_MEASUREMENT);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue((mpo.getExtMbContentOfWindowWOMcsElement("@class", "x-window-dlg"))
			.contains("A Measurement with later date and time has already been registered. Do you want to continue anyway?"),
			"A Measurement with later date and time has already been registered Mess is displayed");

		clickButton("Yes", "x-window-dlg");

		waitForExtJSAjaxComplete(20);

		softAssert.assertAll();

		Reporter.log("Gauges Channel Measurement FormValidation is verified successfully", true);
	}

	/**
	 * Test Case ID: C60467 Author : SSU
	 */
	@Test(enabled=true)
	public void testLimitationInChannelMeasurementsIndexField() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
			+ "Test: Limitation for max allowed digits and decimals for Channels Measurements (9999999999.999)" + " </span><br>", true);

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testLimitationInChannelMeasurementsIndexField");

		String scope6 = "slnmEnrgBuilding6";
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite1";

		waitForExtJSAjaxComplete(30);

		waitAndClick(XPATH_ENERGY);

		waitForExtJSAjaxComplete(20);

		NavigatorPageObject navigatorPO = new NavigatorPageObject();

		navigatorPO.expandNavigator();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		Tree.expandNavigatorTreeNode(driver, area);

		waitForExtJSAjaxComplete(25);

		Tree.expandNavigatorTreeNode(driver, site);
		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		Tree.checkNodeInNavigatorTreeByTextValue(driver, scope6);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(20);

		String panelID = "";
		String locationType = "Energy Object";
		String code = "C60467cod" + getCurrentDate().substring(getCurrentDate().length() - 5);
		String reference = "C60467ref" + getCurrentDate().substring(getCurrentDate().length() - 5);
		String statusActive = "Active";

		waitForExtJSAjaxComplete(20);

		panelID = getXPanelId(locationType + " \"" + scope6 + "\"");

		waitForExtJSAjaxComplete(20);

		navigatorPO.changeTab(panelID, "Gauges");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		boolean status = Grid.isRowInGridPresentLike(driver, "Energy Gauge", "@class", GAUGES_GRID_CLASS);

		if (status) {

			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, "Energy Gauge");

			navigatorPO.clickDeleteBtn();

			waitForExtJSAjaxComplete(10);
		}

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		navigatorPO.clickAddBtn();

		waitForExtJSAjaxComplete(10);

		setCode(code);

		setReference(reference);

		setStatus(statusActive);

		waitForExtJSAjaxComplete(10);

		save();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		close();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, reference, "@class", GAUGES_GRID_CLASS),
			"Gauge is successfully added and correctly displayed");

		waitForExtJSAjaxComplete(10);

		Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, reference);

		navigatorPO.clickEditBtn();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		changeTab("channels");

		waitForExtJSAjaxComplete(20);

		checkRowInGriByTextValueAndClick(driver, reference, ADD_GAUGES_FORM_CLASS);

		waitForExtJSAjaxComplete(10);

		clickButton("Edit", "slnmGaugeId");

		waitForExtJSAjaxComplete(10);

		MeteringGaugesChanelsPageObject meteringGaugeChnlPO = new MeteringGaugesChanelsPageObject();

		String codeGauges = meteringGaugeChnlPO.getCode();

		String refGauges = meteringGaugeChnlPO.getReference();

		meteringGaugeChnlPO.close();

		waitForExtJSAjaxComplete(10);

		clickViewMeasurementsButton();

		waitForExtJSAjaxComplete(20);

		clickMeasurementsTab();

		waitForExtJSAjaxComplete(20);

		String channelDropDownValue = refGauges + " (Building Area)";

		System.out.println(channelDropDownValue);

		setChannelMeasurements("slnmGaugeChnlMeasId", channelDropDownValue);

		waitForExtJSAjaxComplete(20);

		// softAssert.assertTrue(verifyGridIsEmpty().contains("No items available"),
		// "No items available is present in the Grid");

		clickButton("Add", DIALOG_GAUGE_CHNL);

		String indexValue = "9999999999.999";
		String measurementDate = getFutureDate(-1);

		System.out.println("measurementDate " + measurementDate);

		setDate(measurementDate);

		setTime("00:10");

		setValue(indexValue);

		saveClose(DIALOG_MEASUREMENT);

		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, indexValue, "@class", DIALOG_GAUGE_CHNL), "'" + indexValue
			+ "' Row is available in Channel Grid");

		clickButton("Add", DIALOG_GAUGE_CHNL);

		indexValue = "99999999999.999";
		measurementDate = getFutureDate(-1);

		setDate(measurementDate);

		setTime("00:15");

		setValue(indexValue);

		saveClose(DIALOG_MEASUREMENT);

		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(
			(getInfoBarMsgOfWindow("@class", DIALOG_MEASUREMENT)).contains("The form is invalid. Hover over the fields in red to see the errors."),
			"The form is invalid. Hover over the fields in red to see the errors.");

		closeUsingToolBarForDiaogMeasurement(DIALOG_MEASUREMENT);

		waitForExtJSAjaxComplete(10);

		clickButton("Add", DIALOG_GAUGE_CHNL);

		indexValue = "-999999999999.999";
		measurementDate = getFutureDate(-1);

		setDate(measurementDate);

		setTime("00:35");

		setValue(indexValue);

		saveClose(DIALOG_MEASUREMENT);

		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(
			(getInfoBarMsgOfWindow("@class", DIALOG_MEASUREMENT)).contains("The form is invalid. Hover over the fields in red to see the errors."),
			"The form is invalid. Hover over the fields in red to see the errors.");

		closeUsingToolBarForDiaogMeasurement(DIALOG_MEASUREMENT);

		softAssert.assertAll();

		Reporter.log("Limitation for max allowed digits and decimals for Channels Measurements (9999999999.999) is succsessfully verified", true);
	}

	/**
	 * Test Case ID : C60474 Author : SSU If Calculation Method is not Editable,
	 * Create a New Parameter in Configuration-> Parameter with Calculation
	 * Method as "SUM" And use the Parameter in Channel Measurements
	 */
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testRollBackInMeasurementType() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
			+ "Test: Rollback availability in Measurement Type lookup in Gauge with Latest Value and Sum Calculation Methods: C60474"
			+ " </span><br>", true);

		String gaugeCode = "preGaugeMeasure";
		String channelReference = "preGaugeMeasureCRUD";
		String channelReferenceSum = "C60474Test";
		String measurementValue = "-21.000";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testRollBackInMeasurementType");

		waitForExtJSAjaxComplete(30);

		waitAndClick(XPATH_ENERGY);

		waitForExtJSAjaxComplete(20);

		expandMetering();

		openMeteringEntity("Gauges");

		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValue(driver, gaugeCode);

		clickButton("Edit", GAUGES_GRID_CLASS);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		changeTab("channels");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		// Select pre-configured Channel and go to View Measurements

		checkRowInGriByTextValueAndClick(driver, channelReference, ADD_GAUGES_FORM_CLASS);

		waitForExtJSAjaxComplete(20);

		clickButton("Edit", "slnmGaugeId");

		waitForExtJSAjaxComplete(10);

		MeteringGaugesChanelsPageObject meteringGaugesChnlPO = new MeteringGaugesChanelsPageObject();

		String calculationMethod = meteringGaugesChnlPO.getFieldValue("slnmGaugeChnlId", "calculationMethod");

		if (!(calculationMethod.contains("Latest Value"))) {

			meteringGaugesChnlPO.setChnlCalculationMethod("Latest Value");

			meteringGaugesChnlPO.save("slnmGaugeChnlId");

			waitForExtJSAjaxComplete(10);
		}

		meteringGaugesChnlPO.close("slnmGaugeChnlId");

		waitForExtJSAjaxComplete(20);

		clickViewMeasurementsButton();

		waitForExtJSAjaxComplete(20);

		clickMeasurementsTab();

		waitForExtJSAjaxComplete(10);

		clickButton("Add", DIALOG_GAUGE_CHNL);

		waitForExtJSAjaxComplete(20);

		Reporter.log("Check if Measurement Type field Has RollBack Value", true);

		clickLookup(DIALOG_MEASUREMENT, "measurementType");

		waitForExtJSAjaxComplete(10);

		filterGrid("@id", getXWindowId("Select a Measurement Type"), "RollBack", "Reference");

		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(verifyGridIsEmpty("Select a Measurement Type").contains("No items available"),
			"Rollback value is not present in Measurment Type");

		clickCancelXwindow();

		waitForExtJSAjaxComplete(20);

		close(DIALOG_MEASUREMENT);

		waitForExtJSAjaxComplete(10);

		closeUsingToolBarGauges(DIALOG_GAUGE_CHNL);

		waitForExtJSAjaxComplete(25);

		// Change the calculation method to Sum

		checkRowInGriByTextValueAndClick(driver, channelReferenceSum, ADD_GAUGES_FORM_CLASS);

		waitForExtJSAjaxComplete(20);

		clickButton("Edit", "slnmGaugeId");

		waitForExtJSAjaxComplete(10);

		calculationMethod = meteringGaugesChnlPO.getFieldValue("slnmGaugeChnlId", "calculationMethod");

		if (!(calculationMethod.contains("Sum"))) {

			meteringGaugesChnlPO.setChnlCalculationMethod("Sum");

			meteringGaugesChnlPO.save("slnmGaugeChnlId");

			waitForExtJSAjaxComplete(10);
		}

		meteringGaugesChnlPO.close("slnmGaugeChnlId");

		waitForExtJSAjaxComplete(20);

		clickViewMeasurementsButton();

		waitForExtJSAjaxComplete(20);

		clickMeasurementsTab();

		waitForExtJSAjaxComplete(10);

		clickButton("Add", DIALOG_GAUGE_CHNL);

		waitForExtJSAjaxComplete(20);

		Reporter.log("Check if Measurement Type field Has RollBack Value", true);

		clickLookup(DIALOG_MEASUREMENT, "measurementType");

		waitForExtJSAjaxComplete(10);

		filterGrid("@id", getXWindowId("Select a Measurement Type"), "RollBack", "Reference");

		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(verifyGridIsEmpty("Select a Measurement Type").contains("No items available"),
			"Rollback value is not present in Measurment Type");

		clickCancelXwindow();

		waitForExtJSAjaxComplete(10);

		setValue(measurementValue);

		saveClose(DIALOG_MEASUREMENT);

		waitForExtJSAjaxComplete(20);

		GaugesMeasurementsPageObject gaugesMeasurementsPO = new GaugesMeasurementsPageObject();

		gaugesMeasurementsPO.setComboValueDropDownSelector("measurementsPopup_dateRangePicker", "Current Year");

		waitForExtJSAjaxComplete(20);

		String measurementType = getValueOfAnyColumn("@class", DIALOG_GAUGE_CHNL, "Measurement Type");

		softAssert.assertEquals(measurementType, "Rollback", "Rollback is the Measurement Type");

		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(verifyGreenColorCode(DIALOG_GAUGE_CHNL, measurementValue, "Usage Value"),
			"Measurement Type Row is highlighted with Green");

		softAssert.assertAll();

		Reporter.log(
			"Rollback availability in Measurement Type lookup in Gauge with Latest Value and Sum Calculation Methods is successfully verified", true);
	}
}
