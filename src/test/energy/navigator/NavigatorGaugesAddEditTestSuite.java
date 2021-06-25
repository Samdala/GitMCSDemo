package test.energy.navigator;

import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

import test.energy.gauges.MeteringGaugesChanelsPageObject;
import test.energy.gauges.MeteringGaugesPageObject;
import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;

public class NavigatorGaugesAddEditTestSuite extends NavigatorPageObject {

	
	/**
	 * Test Case ID: C60425
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testMandatoryFieldsGauges() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Navigator: Mandatory fields in Add Gauge form : C60425" + " </span><br>", 
				true);
		
		String scope5 = "slnmEnrgBuilding8";
		
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite3 (site3)";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testMandatoryFieldsGauges");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		Thread.sleep(5000);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, site);
		waitForExtJSAjaxComplete(25);		
		Thread.sleep(5000);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, scope5);
		
		waitForExtJSAjaxComplete(25);
		
		Thread.sleep(20000);
		
		waitForExtJSAjaxComplete(20);
		
		String panelID = "";
		String locationType = "Energy Object";
		String code = "C60425cod"+getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "C60425ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String statusActive = "Active";
		String timeZone = "(GMT+01:00) Brussels, Copenhagen, Madrid, Paris";
		
		waitForExtJSAjaxComplete(20);
		
		panelID = getXPanelId(locationType + " \"" +scope5 + "\"");
			
		waitForExtJSAjaxComplete(20);
		
		changeTab(panelID, "Gauges");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		boolean status = Grid.isRowInGridPresentLike(driver, "Energy Gauge", "@class", GAUGES_GRID_CLASS);
		
		if(status) {
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, "Energy Gauge");
				
			clickDeleteBtn();

			waitForExtJSAjaxComplete(10);
		}
			
			softAssert.assertTrue(verifyGridIsEmpty().contains("No items available"), "No items is available in Energy Object "+scope5);
			
			waitForExtJSAjaxComplete(10);
			
			clickAddBtn();
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "code"),"Element Code has correct UI (plaint text, no asterix)");
			
			softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "reference"),"Element Reference has correct UI (plaint text, no asterix)");
			
			softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "scopeObject"),"Element Scope has correct UI (plaint text, no asterix)");
			
			softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "status"),"Element Status has correct UI (plaint text, no asterix)");
			
			softAssert.assertTrue(McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "timezone"),"Element Time Zone has correct UI (plaint text, no asterix)");
			
			waitForExtJSAjaxComplete(10);
			
			MeteringGaugesPageObject meteringGaugesPO = new MeteringGaugesPageObject();
			
			meteringGaugesPO.setCode("");
			
			meteringGaugesPO.setReference(reference);
			
			meteringGaugesPO.setStatus(statusActive);
			
			waitForExtJSAjaxComplete(10);
		
			meteringGaugesPO.save();
			
			softAssert.assertTrue((getInfoBarMsgOfWindow("@class", ADD_GAUGES_FORM_CLASS)).contains("The form is invalid. Hover over the fields in red to see the errors."), "The form is invalid. Hover over the fields in red to see the errors.");
			
			waitForExtJSAjaxComplete(10);
			
			meteringGaugesPO.setCode(code);
			
			meteringGaugesPO.setReference("");
			
			meteringGaugesPO.save();
			
			softAssert.assertTrue((getInfoBarMsgOfWindow("@class", ADD_GAUGES_FORM_CLASS)).contains("The form is invalid. Hover over the fields in red to see the errors."), "The form is invalid. Hover over the fields in red to see the errors.");
			
			meteringGaugesPO.setReference(reference);
			
			meteringGaugesPO.clearTextField(ADD_GAUGES_WINDOW_HEADER, "scopeObject");
			
			meteringGaugesPO.save();
			
			softAssert.assertTrue((getInfoBarMsgOfWindow("@class", ADD_GAUGES_FORM_CLASS)).contains("The form is invalid. Hover over the fields in red to see the errors."), "The form is invalid. Hover over the fields in red to see the errors.");
			
			waitForExtJSAjaxComplete(10);
			
			meteringGaugesPO.setScope(scope5);
			
			waitForExtJSAjaxComplete(10);
			
			meteringGaugesPO.clearTextField(ADD_GAUGES_WINDOW_HEADER, "status");
			
			meteringGaugesPO.save();
			
			softAssert.assertTrue((getInfoBarMsgOfWindow("@class", ADD_GAUGES_FORM_CLASS)).contains("The form is invalid. Hover over the fields in red to see the errors."), "The form is invalid. Hover over the fields in red to see the errors.");
			
			waitForExtJSAjaxComplete(10);
			
			meteringGaugesPO.setStatus(statusActive);
			
			waitForExtJSAjaxComplete(10);
			
			meteringGaugesPO.clearTextField(ADD_GAUGES_WINDOW_HEADER, "timezone");
			
			meteringGaugesPO.save();
			
			softAssert.assertTrue((getInfoBarMsgOfWindow("@class", ADD_GAUGES_FORM_CLASS)).contains("The form is invalid. Hover over the fields in red to see the errors."), "The form is invalid. Hover over the fields in red to see the errors.");
			
			waitForExtJSAjaxComplete(10);
			
			meteringGaugesPO.setCode("");
			
			meteringGaugesPO.setReference("");
			
			meteringGaugesPO.clearTextField(ADD_GAUGES_WINDOW_HEADER, "scopeObject");
			
			meteringGaugesPO.clearTextField(ADD_GAUGES_WINDOW_HEADER, "status");
			
			meteringGaugesPO.clearTextField(ADD_GAUGES_WINDOW_HEADER, "timezone");
			
			meteringGaugesPO.save();
			
			softAssert.assertTrue((getInfoBarMsgOfWindow("@class", ADD_GAUGES_FORM_CLASS)).contains("The form is invalid. Hover over the fields in red to see the errors."), "The form is invalid. Hover over the fields in red to see the errors.");
			
			waitForExtJSAjaxComplete(10);
			
			meteringGaugesPO.setCode(code);
			
			meteringGaugesPO.setReference(reference);
			
			meteringGaugesPO.setScope(scope5);
			
			waitForExtJSAjaxComplete(10);
			
			meteringGaugesPO.setStatus(statusActive);
			
			waitForExtJSAjaxComplete(10);
			
			meteringGaugesPO.setTimeZone(timeZone);
			
			waitForExtJSAjaxComplete(10);
			
			meteringGaugesPO.save();
			
			waitForExtJSAjaxComplete(10);
			
			meteringGaugesPO.close();
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, reference, "@class", GAUGES_GRID_CLASS), "Gauge is successfully added and correctly displayed");
			
			waitForExtJSAjaxComplete(10);
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, reference);
			
			waitForExtJSAjaxComplete(10);
			
			clickDeleteBtn();
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertAll();
			
			Reporter.log("Mandatory fields in Add Gauge form is successfully Verified", true);
	}
	
	/**
	 * Test Case ID: C60426
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testOptionalFieldsGauges() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Navigator: Optional fields in Add Gauge form : C60426" + " </span><br>", 
				true);
		
		String scope5 = "slnmEnrgBuilding8";
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite3 (site3)";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testOptionalFieldsGauges");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		Thread.sleep(20000);
				
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, site);
		waitForExtJSAjaxComplete(25);
				
		Thread.sleep(20000);
				
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, scope5);
		waitForExtJSAjaxComplete(25);
				
		Thread.sleep(20000);
		
		waitForExtJSAjaxComplete(20);
		
		String panelID = "";
		String locationType = "Energy Object";
		String code = "C60426cod"+getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "C60426ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String statusActive = "Active";
		
		String commisioningDate = getFutureDate(0);
		String classRef = "Energy Gauge";
		String serialNo = "1";
		String manufacturer = "manufacturer";
		String description = "Test Description";
		String model = "Model";
		String accessInstructions = "Access Instructions";
		String timeZone = "(GMT+01:00) Brussels, Copenhagen, Madrid, Paris";
		
		waitForExtJSAjaxComplete(20);
		
		panelID = getXPanelId(locationType + " \"" +scope5 + "\"");
		
		Thread.sleep(2000);
	
		changeTab(panelID, "Gauges");
	
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		boolean status = Grid.isRowInGridPresentLike(driver, "Energy Gauge", "@class", GAUGES_GRID_CLASS);
		
		if(status) {
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, "Energy Gauge");
				
			clickDeleteBtn();

			waitForExtJSAjaxComplete(10);
		}
			
			softAssert.assertTrue(verifyGridIsEmpty().contains("No items available"), "No items is available in Energy Object "+scope5);
			
			waitForExtJSAjaxComplete(10);
			
			clickAddBtn();
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertTrue(!McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "itemId"),"Element ID has correct UI (plaint text, no asterix)");
			
			softAssert.assertTrue(!McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "commissioningDate"),"Element Commissioning Date has correct UI (plaint text, no asterix)");
			
			softAssert.assertTrue(!McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "serialNumber"),"Element Serial No has correct UI (plaint text, no asterix)");
			
			softAssert.assertTrue(!McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "manufacturer"),"Element Manufacturer has correct UI (plaint text, no asterix)");
			
			softAssert.assertTrue(!McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "description"),"Element Description has correct UI (plaint text, no asterix)");
			
			softAssert.assertTrue(!McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "accessDirectives"),"Element Access Directives has correct UI (plaint text, no asterix)");
			
			softAssert.assertTrue(!McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "location"),"Element Physical Location has correct UI (plaint text, no asterix)");
			
			softAssert.assertTrue(!McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "model"),"Element Model has correct UI (plaint text, no asterix)");
			
			softAssert.assertTrue(!McsElement.isFieldMandatory(driver, ADD_GAUGES_FORM_CLASS, "class"),"Element Class has correct UI (plaint text, no asterix)");
			
			waitForExtJSAjaxComplete(10);
			
			MeteringGaugesPageObject meteringGaugesPO = new MeteringGaugesPageObject();
			
			meteringGaugesPO.setCode(code);
			
			meteringGaugesPO.setReference(reference);
			
			meteringGaugesPO.setStatus(statusActive);
			
			waitForExtJSAjaxComplete(10);
		
			meteringGaugesPO.save();
			
			waitForExtJSAjaxComplete(20);
			
			meteringGaugesPO.close();
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, reference, "@class", GAUGES_GRID_CLASS), "Existing gauges for current Energy Object is correctly displayed");
			
			waitForExtJSAjaxComplete(10);
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, reference);
			
			waitForExtJSAjaxComplete(10);
			
			clickEditBtn();
			
			waitForExtJSAjaxComplete(10);
			
			meteringGaugesPO.setCommissioningDate(commisioningDate);
			
			meteringGaugesPO.setSerialNumber(serialNo);
			
			meteringGaugesPO.setManufacturer(manufacturer);
			
			meteringGaugesPO.setDescription(description);
			
			meteringGaugesPO.setModel(model);
			
			meteringGaugesPO.setAccessInstructions(accessInstructions);
			
			meteringGaugesPO.setLocation(scope5);
			
			waitForExtJSAjaxComplete(10);
			
			meteringGaugesPO.save();
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertFalse(meteringGaugesPO.getItemID("value").isEmpty(), "ID field value after creation is correct");
			
			softAssert.assertEquals(meteringGaugesPO.getCode(), code, "Code field value after creation is correct");
			
			softAssert.assertEquals(meteringGaugesPO.getReference(), reference, "Reference field value after creation is correct");
			
			softAssert.assertEquals(meteringGaugesPO.getCommissioningDate(), commisioningDate, "Commisioning Date field value after creation is correct");
			
			softAssert.assertEquals(meteringGaugesPO.getSerialNumber(), serialNo, "Serial No field value after creation is correct");
			
			softAssert.assertEquals(meteringGaugesPO.getManufacturer(), manufacturer, "Manufacturer field value after creation is correct");
			
			softAssert.assertEquals(meteringGaugesPO.getDescription(), description, "Decription field value after creation is correct");
			
			softAssert.assertEquals(meteringGaugesPO.getGaugeClass(), classRef, "Class field value after creation is correct");
			
			softAssert.assertEquals(meteringGaugesPO.getScope(), scope5, "Scope field value after creation is correct");
			
			softAssert.assertEquals(meteringGaugesPO.getStatus(), statusActive, "Status field value after creation is correct");
			
			softAssert.assertEquals(meteringGaugesPO.getTimeZone(), timeZone, "Time Zone field value after creation is correct");
			
			softAssert.assertEquals(meteringGaugesPO.getModel(), model, "Model field value after creation is correct");
			
			softAssert.assertEquals(meteringGaugesPO.getLocation(), scope5, "Location field value after creation is correct");
			
			softAssert.assertEquals(meteringGaugesPO.getAccessInstructions(), accessInstructions, "Access Instruction field value after creation is correct");
			
			waitForExtJSAjaxComplete(20);
			
			meteringGaugesPO.close();
			
			waitForExtJSAjaxComplete(10);
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, reference);
			
			waitForExtJSAjaxComplete(10);
			
			clickDeleteBtn();
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertAll();
			
			Reporter.log("Optional fields in Add Gauge form is successfully Verified", true);
	}
	
	/**
	 * Test Case ID: C60429 & C60430
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testGaugesAddEdit() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Navigator: Energy Object: Gauges tab: User is able to add a gauge : C60429" + " </span><br>", 
				true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Navigator: Energy Object: Gauges tab: User can edit existing gauge : C60430" + " </span><br>", 
				true);
		
		String scope5 = "slnmEnrgBuilding8";
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite3 (site3)";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testGaugesAddEdit");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		Thread.sleep(20000);
				
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, site);
		waitForExtJSAjaxComplete(25);
				
		Thread.sleep(20000);
				
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, scope5);
		Thread.sleep(20000);
		
		waitForExtJSAjaxComplete(20);
		
		String panelID = "";
		String locationType = "Energy Object";
		String code = "C60429cod"+getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "C60429ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String statusActive = "Active";
		String scopeObjectName = "scopeObject";
		String gaugesDialogId;
		String notes = "Test Notes Field";
		
		String referenceEdited = "refEdited"+getCurrentDate().substring(getCurrentDate().length()-5);
		
		waitForExtJSAjaxComplete(20);
		
			panelID = getXPanelId(locationType + " \"" +scope5 + "\"");
			
			waitForExtJSAjaxComplete(20);
		
			changeTab(panelID, "Gauges");
		
			waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		boolean status = Grid.isRowInGridPresentLike(driver, "Energy Gauge", "@class", GAUGES_GRID_CLASS);
		
		if(status) {
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, "Energy Gauge");
				
			clickDeleteBtn();

			waitForExtJSAjaxComplete(10);
		}
			
			softAssert.assertTrue(verifyGridIsEmpty().contains("No items available"), "No items is available in Energy Object "+scope5);
			
			waitForExtJSAjaxComplete(10);
		
			softAssert.assertTrue(verifyGridIsEmpty().contains("No items available"), "No items is available in Energy Object "+scope5);
			
			waitForExtJSAjaxComplete(10);
			
			clickAddBtn();
			
			waitForExtJSAjaxComplete(10);
			
			MeteringGaugesPageObject meteringGaugesPO = new MeteringGaugesPageObject();
			
			softAssert.assertTrue(meteringGaugesPO.getScope().contains(scope5), "Scope is PrePopulated in Navigator -> Gauges");
			
			waitForExtJSAjaxComplete(10);
		
			meteringGaugesPO.setCode(code);
			
			meteringGaugesPO.setReference(reference);
			
			meteringGaugesPO.setStatus(statusActive);
			
			waitForExtJSAjaxComplete(10);
		
			meteringGaugesPO.save();
			
			waitForExtJSAjaxComplete(20);
			
			meteringGaugesPO.close();
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, reference, "@class", GAUGES_GRID_CLASS), "Existing gauges for current Energy Object is correctly displayed");
			
			waitForExtJSAjaxComplete(10);
			
			String lastModifiedDate = "";
			
			lastModifiedDate = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Last Modified on");
			
			Reporter.log("Last Modified Date Before editing is : "+lastModifiedDate, true);
			
			waitForExtJSAjaxComplete(10);
			
			clickEditBtn();
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertTrue(meteringGaugesPO.getWarningMessage().contains("Please select an item"), "Please select an item is displayed");
			
			clickOnDialogButton("OK");
			
			waitForExtJSAjaxComplete(10);
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, reference);
			
			waitForExtJSAjaxComplete(10);
			
			clickEditBtn();
			
			waitForExtJSAjaxComplete(10);
			
			String scopeObjectUnEditable = verifyUnEditable(scopeObjectName);
			
			softAssert.assertTrue(scopeObjectUnEditable.contains("x-form-readonly"), "Scope Object is UnEditable");
			
			waitForExtJSAjaxComplete(5);
			
			meteringGaugesPO.setReference(referenceEdited);
			
			waitForExtJSAjaxComplete(5);
			
			meteringGaugesPO.save();
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertEquals(meteringGaugesPO.getReference(), referenceEdited, "Reference Edited is updated");
			
			waitForExtJSAjaxComplete(20);
			
			meteringGaugesPO.changeTab("channels");
			
			waitForExtJSAjaxComplete(20);
			
			gaugesDialogId = getXWindowIdByClass("slnmGaugeId");
			
			waitForExtJSAjaxComplete(10);
			
			System.out.println("gaugesDialogId " +gaugesDialogId);
			
			clickButton("Edit", gaugesDialogId);
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertTrue(meteringGaugesPO.getWarningMessage().contains("Please select an item"), "Please select an item is displayed");
			
			clickOnDialogButton("OK");
			
			waitForExtJSAjaxComplete(10);
			
			Grid.checkRowInGriByTextValueAndClick(driver, "slnmGaugeId", reference);
			
			waitForExtJSAjaxComplete(10);
			
			clickButton("Edit", gaugesDialogId);
			
			waitForExtJSAjaxComplete(10);
			
			MeteringGaugesChanelsPageObject meteringGaugeChannelPO = new MeteringGaugesChanelsPageObject();
			
			meteringGaugeChannelPO.setReference(referenceEdited);
			
			meteringGaugeChannelPO.save(ADD_CHANEL_GAUGES_FORM_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			softAssert.assertEquals(meteringGaugeChannelPO.getReference(), referenceEdited, "Reference Edited is updated");
			
			waitForExtJSAjaxComplete(10);
			
			meteringGaugeChannelPO.close();
			
			waitForExtJSAjaxComplete(10);
			
			meteringGaugesPO.changeTab("notes");
			
			waitForExtJSAjaxComplete(20);
			
			meteringGaugesPO.setNotes(notes);
			
			waitForExtJSAjaxComplete(10);
			
			meteringGaugeChannelPO.save(ADD_GAUGES_FORM_CLASS);
			
			waitForExtJSAjaxComplete(20);
			
			meteringGaugesPO.changeTab("properties");
			
			waitForExtJSAjaxComplete(20);
			
			meteringGaugesPO.close();
			
			waitForExtJSAjaxComplete(10);
			
			clickAddBtn();
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertTrue(meteringGaugesPO.getWarningMessage().contains("There can be only one Gauge per Energy Object."), "There can be only one Gauge per Energy Object.");
			
			Reporter.log("There can be only one Gauge per Energy Object.", true);
			
			waitForExtJSAjaxComplete(10);
			
			clickOnDialogButton("OK");
			
			waitForExtJSAjaxComplete(10);
			
			String lastModifiedDateUpdated = "";
			
			lastModifiedDateUpdated = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Last Modified on");
			
			Reporter.log("Last Modified Date After editing is : "+lastModifiedDateUpdated, true);
			
			waitForExtJSAjaxComplete(10);
			
			int actualDateValue = compareDates(lastModifiedDate, lastModifiedDateUpdated);
			int expectedDateValue = -1;
			
			softAssert.assertEquals(actualDateValue, expectedDateValue, "Last Modified Date is Updated after Editing");
			
			waitForExtJSAjaxComplete(10);
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, referenceEdited);
			
			waitForExtJSAjaxComplete(10);
			
			clickDeleteBtn();
			
			waitForExtJSAjaxComplete(10);
			
		softAssert.assertAll();
		
		Reporter.log("Gauges tab: User is able to add/edit a gauge is successfully verified", true);
	}
	
	/**
	 * Test Case ID: C60434
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testGaugesDeletion() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Navigator: Energy Object: Gauges: User is able to delete existing gauges : C60434" + " </span><br>", 
				true);
		
		String scope5 = "slnmEnrgBuilding8";
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite3 (site3)";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testGaugesDeletion");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		Thread.sleep(20000);
				
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, site);
		waitForExtJSAjaxComplete(25);
				
		Thread.sleep(20000);
				
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, scope5);
		waitForMaskDisappear();
				
		Thread.sleep(20000);
		
		waitForExtJSAjaxComplete(20);
		
		String panelID = "";
		String locationType = "Energy Object";
		String code = "C60434cod"+getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "C60434ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String statusActive = "Active";
		
			panelID = getXPanelId(locationType + " \"" +scope5 + "\"");
			
			waitForExtJSAjaxComplete(20);
		
			changeTab(panelID, "Gauges");
		
			waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		boolean status = Grid.isRowInGridPresentLike(driver, "Energy Gauge", "@class", GAUGES_GRID_CLASS);
		
		if(status) {
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, "Energy Gauge");
				
			clickDeleteBtn();

			waitForExtJSAjaxComplete(10);
		}

			waitForExtJSAjaxComplete(10);
			
			clickAddBtn();
			
			waitForExtJSAjaxComplete(10);
			
			MeteringGaugesPageObject meteringGaugesPO = new MeteringGaugesPageObject();
			
			softAssert.assertTrue(meteringGaugesPO.getScope().contains(scope5), "Scope is PrePopulated in Navigator -> Gauges");
			
			waitForExtJSAjaxComplete(10);
		
			meteringGaugesPO.setCode(code);
			
			meteringGaugesPO.setReference(reference);
			
			meteringGaugesPO.setStatus(statusActive);
			
			waitForExtJSAjaxComplete(10);
		
			meteringGaugesPO.save();
			
			waitForExtJSAjaxComplete(20);
			
			meteringGaugesPO.close();
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, reference, "@class", GAUGES_GRID_CLASS), "Existing gauges for current Energy Object is correctly displayed");
			
			waitForExtJSAjaxComplete(10);
			
			verifyDeleteFunctionality();
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertTrue(meteringGaugesPO.getWarningMessage().contains("Please select an item"), "Please select an item is displayed");
			
			clickOnDialogButton("OK");
			
			waitForExtJSAjaxComplete(10);
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, reference);
			
			waitForExtJSAjaxComplete(10);	
			
			verifyDeleteFunctionality();
			
			clickOnDialogButton("No");
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, reference, "@class", GAUGES_GRID_CLASS), "Existing gauges for current Energy Object is correctly displayed");
			
			waitForExtJSAjaxComplete(10);
			
			clickDeleteBtn();
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertTrue(Grid.isRowInGridAbsent(driver, reference, "@class", GAUGES_GRID_CLASS), "Existing gauges for current Energy Object is Deleted");
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertAll();
			
			Reporter.log("Navigator: Energy Object: Gauges: User is able to delete existing gauges is successfully verified", true);
	}
	
	/**
	 * Test Case ID: C60431
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testCopyGaugesFunctionality() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Copy Gauge (To: an Energy Object) : C60431" + " </span><br>", 
				true);
		
		String scope5 = "slnmEnrgBuilding8";
		String energyObject = "slnmEnrgBuilding9";
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite3 (site3)";
		
		String panelID = "";
		String locationType = "Energy Object";
		String code = "C60431cod"+getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "C60431ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String statusActive = "Active";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCopyGaugesFunctionality");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		Thread.sleep(20000);
				
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, site);
		waitForExtJSAjaxComplete(25);
				
		Thread.sleep(20000);
				
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, energyObject);
				
		Thread.sleep(5000);
		
		waitForExtJSAjaxComplete(20);

		
			panelID = getXPanelId(locationType + " \"" +energyObject + "\"");
			
			Thread.sleep(5000);
		
			changeTab(panelID, "Gauges");
		
			waitForExtJSAjaxComplete(20);
		
		
		waitForExtJSAjaxComplete(20);
		
		boolean status = Grid.isRowInGridPresentLike(driver, "Energy Gauge", "@class", GAUGES_GRID_CLASS);
		
		if(status) {
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, "Energy Gauge");
				
			clickDeleteBtn();

			waitForExtJSAjaxComplete(10);
		}	
		
		waitForExtJSAjaxComplete(10);
		
		MeteringGaugesPageObject meteringGaugesPO = new MeteringGaugesPageObject();
					
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, scope5);
				
		Thread.sleep(5000);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
			panelID = getXPanelId(locationType + " \"" +scope5 + "\"");
			
			waitForExtJSAjaxComplete(20);
		
			changeTab(panelID, "Gauges");
		
			waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		
		status = Grid.isRowInGridPresentLike(driver, "Energy Gauge", "@class", GAUGES_GRID_CLASS);
		
		if(status) {
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, "Energy Gauge");
				
			clickDeleteBtn();

			waitForExtJSAjaxComplete(10);
		}
		
		waitForExtJSAjaxComplete(10);
		
		clickAddBtn();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(meteringGaugesPO.getScope().contains(scope5), "Scope is PrePopulated in Navigator -> Gauges");
		
		waitForExtJSAjaxComplete(10);
	
		meteringGaugesPO.setCode(code);
		
		meteringGaugesPO.setReference(reference);
		
		meteringGaugesPO.setStatus(statusActive);
		
		waitForExtJSAjaxComplete(10);
	
		meteringGaugesPO.save();
		
		waitForExtJSAjaxComplete(20);
		
		meteringGaugesPO.close();
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, reference);
		
		waitForExtJSAjaxComplete(10);	
		
		clickCopyGauge();
		
		waitForExtJSAjaxComplete(10);	
		
		selectEnergyObjects("Reference", energyObject);
		
		waitForExtJSAjaxComplete(10);	
		
		boolean colID = verifyColumnsInCopyGauge("@class", COPY_GAUGES_FORM_CLASS, "ID");
		
		softAssert.assertTrue(colID, "ID column is Present in Copy Gauge Window");
		
		boolean colReference = verifyColumnsInCopyGauge("@class", COPY_GAUGES_FORM_CLASS, "Reference");
		
		softAssert.assertTrue(colReference, "Reference column is Present in Copy Gauge Window");
		
		boolean colType = verifyColumnsInCopyGauge("@class", COPY_GAUGES_FORM_CLASS, "Type");
		
		softAssert.assertTrue(colType, "Type column is Present in Copy Gauge Window");
		
		boolean colStatus = verifyColumnsInCopyGauge("@class", COPY_GAUGES_FORM_CLASS, "Status");
		
		softAssert.assertTrue(colStatus, "Status column is Present in Copy Gauge Window");
		
		waitForExtJSAjaxComplete(10);	
		
		String statusExepcted = "Not processed";
		String statusOK = "OK";
		
		String statusValue = getValueOfCopyGuageStatusColumn("@class", COPY_GAUGES_FORM_CLASS, "Status");
		
		softAssert.assertEquals(statusValue, statusExepcted, "Not Processed is present as the status in Copy Gauges");
		
		waitForExtJSAjaxComplete(10);	
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, energyObject, "@class", COPY_GAUGES_FORM_CLASS), "Reference "+energyObject+" is present in Copy Gauge Window");
		
		clickCreate();
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(10);
		
		statusValue = getStatusValue("@class", COPY_GAUGES_FORM_CLASS, "Status");
		
		softAssert.assertEquals(statusValue, statusOK, "OK is present as the status in Copy Gauges");
		
		clickButton("Close", getXWindowIdByClass(COPY_GAUGES_FORM_CLASS));
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, reference);
		
		waitForExtJSAjaxComplete(10);
		
		clickDeleteBtn();
		
		waitForExtJSAjaxComplete(10);
		
		waitForExtJSAjaxComplete(20);
		
		/*test.framework.webelement.Tree.expandNavigatorTreeNode(driver, "slnmEnrgArea1 (slnmEnrgArea)");
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, "slnmEnrgSite1");
		waitForMaskDisappear(); */
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, energyObject);
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(10);
		
			panelID = getXPanelId(locationType + " \"" +energyObject + "\"");
			
			waitForExtJSAjaxComplete(20);
		
			changeTab(panelID, "Gauges");
		
			waitForExtJSAjaxComplete(20);
		
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, reference, "@class", GAUGES_GRID_CLASS), "Reference "+reference+" is present in Copy Gauge Window");
		
		Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, reference);
		
		waitForExtJSAjaxComplete(10);	
		
		clickEditBtn();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertNotEquals(meteringGaugesPO.getCode(), code, "Code field value after creation is Changed");
		
		softAssert.assertEquals(meteringGaugesPO.getReference(), reference, "Reference field value after creation is Same");
		
		waitForExtJSAjaxComplete(20);
		
		meteringGaugesPO.changeTab("channels");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, reference, "@class", ADD_GAUGES_FORM_CLASS), "Reference "+reference+" is present in Channels Edit Gauge Window");
		
		meteringGaugesPO.clickViewMeasurementsButton();
		
		waitForExtJSAjaxComplete(20);
		
		panelID = getXWindowIdByClass(CHANNEL_MEASUREMENTS_CLASS);
		
		changeTab(panelID, "Measurements");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(verifyGridIsEmpty().contains("No items available"), "No items is available in Channel Measurements Window");
		
		meteringGaugesPO.clickIntervalDataTab();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(50);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(verifyGridIsEmpty("meter_channel_usagedata_overview").contains("No items available"), "No items is available in Channel Measurements Interval Data Window");
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesPO.closeUsingToolBarGauges(CHANNEL_MEASUREMENTS_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesPO.close();
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, reference);
		
		waitForExtJSAjaxComplete(10);
		
		clickDeleteBtn();
		
		waitForExtJSAjaxComplete(10);

		softAssert.assertAll();
		
		Reporter.log("Copy Gauge (To: an Energy Object) is successfully verified", true);
	}
	
	/**
	 * Test Case ID: C60427
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testIDField() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: The ID field : C60427" + " </span><br>", 
				true);
		
		String scope5 = "slnmEnrgBuilding8";
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite3 (site3)";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testIDField");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		Thread.sleep(20000);
				
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, site);
		waitForExtJSAjaxComplete(25);
				
		Thread.sleep(20000);
				
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, scope5);
		waitForExtJSAjaxComplete(25);
				
		Thread.sleep(20000);
		
		waitForExtJSAjaxComplete(20);
		
		String panelID = "";
		String locationType = "Energy Object";
		String code = "C60427cod"+getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "C60427ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String statusActive = "Active";
		
			panelID = getXPanelId(locationType + " \"" +scope5 + "\"");
			
			waitForExtJSAjaxComplete(20);
		
			changeTab(panelID, "Gauges");
		
			waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		if(verifyGridIsEmpty().contains("No items available")){

			waitForExtJSAjaxComplete(10);
			
			clickAddBtn();
			
			waitForExtJSAjaxComplete(10);
			
			MeteringGaugesPageObject meteringGaugesPO = new MeteringGaugesPageObject();
			
			softAssert.assertTrue(meteringGaugesPO.getItemID("value").isEmpty(), "ID field value is empty");
			
			softAssert.assertTrue(meteringGaugesPO.getItemID("class").contains("readonly"), "ID field is Read only");
			
			waitForExtJSAjaxComplete(10);
		
			meteringGaugesPO.setCode(code);
			
			meteringGaugesPO.setReference(reference);
			
			meteringGaugesPO.setStatus(statusActive);
			
			waitForExtJSAjaxComplete(10);
		
			meteringGaugesPO.save();
			
			waitForExtJSAjaxComplete(20);
			
			meteringGaugesPO.changeTab("properties");
			
			waitForExtJSAjaxComplete(20);
			
			String IDValue = meteringGaugesPO.getItemID("value");
			
			Reporter.log("ID Value is "+IDValue, true);
			
			meteringGaugesPO.close();
			
			waitForExtJSAjaxComplete(10);
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, IDValue);
			
			Reporter.log("ID is displayed properly in Overview", true);
			
			waitForExtJSAjaxComplete(10);	
			
			clickEditBtn();
			
			waitForExtJSAjaxComplete(10);	
			
			softAssert.assertTrue(meteringGaugesPO.getItemID("value").contains(IDValue), "ID field value is filled in Properly");
			
			softAssert.assertTrue(meteringGaugesPO.getItemID("class").contains("readonly"), "ID field is Read only");
			
			meteringGaugesPO.close();
			
			waitForExtJSAjaxComplete(10);
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, IDValue);
			
			clickDeleteBtn();
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertAll();
			
			Reporter.log("The ID field in Gauges is successfully verified", true);
		}
	}
	
	/**
	 * Test Case ID: C60424
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testVisibilityOfAllGaugeFields() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Visibility of all added information to gauge in Overview page : C60424" + " </span><br>", 
				true);
		
		String scope5 = "slnmEnrgBuilding4";
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite1";
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testVisibilityOfAllGaugeFields");
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		Thread.sleep(20000);
				
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, site);
		waitForExtJSAjaxComplete(25);
				
		Thread.sleep(20000);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, scope5);
		waitForExtJSAjaxComplete(20);
				
		Thread.sleep(20000);
		
		waitForExtJSAjaxComplete(20);
		
		String panelID = "";
		String locationType = "Energy Object";
		String code = "C60424cod"+getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "C60424ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String statusActive = "Active";
		
		String commisioningDate = getFutureDate(0);
		String classRef = "Energy Gauge";
		String serialNo = "1";
		String manufacturer = "manufacturer";
		String description = "Test Description";
		String model = "Model";
		String accessInstructions = "Access Instructions";
		String timeZone = "(GMT+01:00) Brussels, Copenhagen, Madrid, Paris";
		String notes = "Test Notes Field";
		
		
			panelID = getXPanelId(locationType + " \"" +scope5 + "\"");
			
			waitForExtJSAjaxComplete(20);
		
			changeTab(panelID, "Gauges");
		
			waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		boolean status = Grid.isRowInGridPresentLike(driver, "Energy Gauge", "@class", GAUGES_GRID_CLASS);
		
		if(status) {
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, "Energy Gauge");
				
			clickDeleteBtn();

			waitForExtJSAjaxComplete(10);
		}
			
			clickAddBtn();
			
			waitForExtJSAjaxComplete(10);
			
			MeteringGaugesPageObject meteringGaugesPO = new MeteringGaugesPageObject();
			
			meteringGaugesPO.setCode(code);
			
			meteringGaugesPO.setReference(reference);
			
			meteringGaugesPO.setStatus(statusActive);
			
			waitForExtJSAjaxComplete(10);
			
			meteringGaugesPO.setTimeZone(timeZone);
			
			waitForExtJSAjaxComplete(10);
			
			meteringGaugesPO.setCommissioningDate(commisioningDate);
			
			meteringGaugesPO.setSerialNumber(serialNo);
			
			meteringGaugesPO.setManufacturer(manufacturer);
			
			meteringGaugesPO.setDescription(description);
			
			meteringGaugesPO.setModel(model);
			
			meteringGaugesPO.setAccessInstructions(accessInstructions);
			
			meteringGaugesPO.setLocation(scope5);
			
			waitForExtJSAjaxComplete(10);
			
			meteringGaugesPO.changeTab("notes");
			
			waitForExtJSAjaxComplete(20);
			
			meteringGaugesPO.setNotes(notes);
			
			waitForExtJSAjaxComplete(10);
			
			meteringGaugesPO.save();
			
			waitForExtJSAjaxComplete(20);
			
			meteringGaugesPO.changeTab("properties");
			
			waitForExtJSAjaxComplete(10);
			
			String idValue = meteringGaugesPO.getItemID("value");
			
			meteringGaugesPO.close();
			
			waitForExtJSAjaxComplete(20);
			
			String createdOn = "";
			String lastModifiedDate = "";
			
				createdOn = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Created on");
				
				lastModifiedDate = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Last Modified on");
			
			clickChangeVisibleColumns();
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertTrue(isPropertiesTabAvailable(CHANGE_VISIBLE_COLUMNS_HEADER), "Properties Tab is available in Change Visible Column Window");
			
			softAssert.assertTrue(isColumnsTabAvailable(CHANGE_VISIBLE_COLUMNS_HEADER), "Columns Tab is available in Change Visible Column Window");
			
			waitForExtJSAjaxComplete(10);
			
			isAuditingFieldChecked(CHANGE_VISIBLE_COLUMNS_HEADER);
			
			waitForExtJSAjaxComplete(10);
			
				softAssert.assertTrue(isAuditingColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Created by"), "Created By Column is Checked");
				
				softAssert.assertTrue(isAuditingColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Created on"), "Created On Column is Checked");

				softAssert.assertTrue(isAuditingColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Last Modified by"), "Last Modified By Column is Checked");
				
				softAssert.assertTrue(isAuditingColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Last Modified on"), "Last Modified On Column is Checked");
			
			waitForExtJSAjaxComplete(10);
			
			isGeneralFieldChecked(CHANGE_VISIBLE_COLUMNS_HEADER);
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertTrue(isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Access Instructions"), "Created By Column is Checked");
			
			softAssert.assertTrue(isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Class"), "Class Column is Checked");
			
			softAssert.assertTrue(isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Code"), "Code Column is Checked");
			
			softAssert.assertTrue(isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Commissioning Date"), "Commissioning Date Column is Checked");
			
			softAssert.assertTrue(isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Description"), "Description Column is Checked");
			
			softAssert.assertTrue(isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "ID"), "ID Column is Checked");
			
			softAssert.assertTrue(isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Manufacturer"), "Manufacturer Column is Checked");
			
			softAssert.assertTrue(isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Model"), "Model Column is Checked");
			
			softAssert.assertTrue(isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Notes"), "Notes Column is Checked");
			
			softAssert.assertTrue(isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Reference"), "Reference Column is Checked");
			
			softAssert.assertTrue(isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Scope"), "Scope Column is Checked");
			
			softAssert.assertTrue(isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Serial Number"), "Serial Number Column is Checked");
			
			softAssert.assertTrue(isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Status"), "Status Column is Checked");
			
			softAssert.assertTrue(isGeneralColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Time Zone"), "Time Zone Column is Checked");
			
			isPhysicalLocationFieldChecked(CHANGE_VISIBLE_COLUMNS_HEADER);
			
			waitForExtJSAjaxComplete(10);
			
			softAssert.assertTrue(isPhysicalLocationColumnsChecked(CHANGE_VISIBLE_COLUMNS_HEADER, "col-1", "Physical Location"), "Physical Location Column is Checked");
			
			waitForExtJSAjaxComplete(10);
			
			clickButton("Save", getXWindowId(CHANGE_VISIBLE_COLUMNS_HEADER));
			
			waitForExtJSAjaxComplete(10);
			
			String createdByPostTxn = "";
			String createdOnPostTxn = "";
			String lastModifiedByPostTxn = "";
			String lastModifiedDatePostTxn = "";
			
				createdByPostTxn = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Created by");
				
				createdOnPostTxn = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Created on");
				
				lastModifiedByPostTxn = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Last Modified by");
				
				lastModifiedDatePostTxn = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Last Modified on");
			
			String accessInstruction = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Access Instructions");
			
			String classPostTxn = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Class");
			
			String codePostTxn = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Code");
			
			String commissioningDatePostTxn = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Commissioning Date");
			
			String descriptionTxn = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Description");
			
			String IDPostTxn = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "ID");
			
			String manufacturerPostTxn = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Manufacturer");
			
			String modelPostTxn = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Model");
			
			String notesPostTxn = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Notes");
			
			String physicalLocationPostTxn = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Physical Location");
			
			String referencePostTxn = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Reference");
			
			String scopePostTxn = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Scope");
			
			String serialNoPostTxn = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Serial Number");
			
			String statusPostTxn = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Status");
			
			String timeZonePostTxn = getValueOfAnyColumn("@class", "x-tab-panel-body-noborder x-tab-panel-body-top", "Time Zone");
			
			/**********Verification ***************/
			
			softAssert.assertEquals(createdOn, createdOnPostTxn, "Created On field value after creation is correct");
			
			softAssert.assertEquals("SELENIUM AQA", createdByPostTxn, "Created By field value after creation is correct");
			
			softAssert.assertEquals(lastModifiedDate, lastModifiedDatePostTxn, "Last Modified Date field value after creation is correct");
			
			softAssert.assertEquals("SELENIUM AQA", lastModifiedByPostTxn, "Last Modified By field value after creation is correct");
			
			softAssert.assertEquals(createdOn, createdOnPostTxn, "Created On field value after creation is correct");
			
			softAssert.assertEquals(idValue, IDPostTxn, "ID field value after creation is correct");
			
			softAssert.assertEquals(code, codePostTxn, "Code field value after creation is correct");
			
			softAssert.assertEquals(reference, referencePostTxn, "Reference field value after creation is correct");
			
			softAssert.assertEquals(commisioningDate, commissioningDatePostTxn, "Commisioning Date field value after creation is correct");
			
			softAssert.assertEquals(accessInstructions, accessInstruction, "Access Instruction field value after creation is correct");
			
			softAssert.assertEquals(manufacturer, manufacturerPostTxn, "Manufacturer field value after creation is correct");
			
			softAssert.assertEquals(description, descriptionTxn, "Decription field value after creation is correct");
			
			softAssert.assertEquals(classRef, classPostTxn, "Class field value after creation is correct");
			
			softAssert.assertEquals(scope5, scopePostTxn, "Scope field value after creation is correct");
			
			softAssert.assertEquals(model, modelPostTxn, "Model field value after creation is correct");
			
			softAssert.assertEquals(notes, notesPostTxn, "Notes field value after creation is correct");
			
			softAssert.assertEquals(scope5, physicalLocationPostTxn, "Location field value after creation is correct");
			
			softAssert.assertEquals(serialNo, serialNoPostTxn, "Serial No field value after creation is correct");
			
			softAssert.assertEquals(statusActive, statusPostTxn, "Status field value after creation is correct");
			
			softAssert.assertEquals(timeZone, timeZonePostTxn, "Time Zone field value after creation is correct");
			
			waitForExtJSAjaxComplete(10);
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, code);
			
			waitForExtJSAjaxComplete(10);
			
			clickDeleteBtn();
			
			waitForExtJSAjaxComplete(10);
			
			waitForExtJSAjaxComplete(25);
			
			softAssert.assertAll();
			
			Reporter.log("Visibility of all added information to gauge in Overview page is successfully verified", true);
	}
	
	/**
	 * Test Case ID: C60428
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testAuditField() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Check audit fields on Edit Gauge form : C60428" + " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testAuditField");
		
		String scope = "slnmEnrgBuilding8";
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite3 (site3)";
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		Thread.sleep(20000);
				
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, site);
		waitForExtJSAjaxComplete(25);
				
		Thread.sleep(20000);
				
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, scope);
		waitForExtJSAjaxComplete(20);
				
		Thread.sleep(20000);
		
		waitForExtJSAjaxComplete(20);
		
		String panelID = "";
		String locationType = "Energy Object";
		String code = "C60428cod"+getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "C60428ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String statusActive = "Active";
		
		
			panelID = getXPanelId(locationType + " \"" +scope + "\"");
			
			waitForExtJSAjaxComplete(20);
		
			changeTab(panelID, "Gauges");
		
			waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		boolean status = Grid.isRowInGridPresentLike(driver, "Energy Gauge", "@class", GAUGES_GRID_CLASS);
		
		if(status) {
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, "Energy Gauge");
				
			clickDeleteBtn();

			waitForExtJSAjaxComplete(10);
		}
		
		clickAddBtn();
			
		waitForExtJSAjaxComplete(10);
			
		MeteringGaugesPageObject meteringGaugesPO = new MeteringGaugesPageObject();
		
		meteringGaugesPO.setCode(code);
		
		meteringGaugesPO.setReference(reference);
			
		meteringGaugesPO.setStatus(statusActive);
			
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesPO.setScope(scope);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesPO.save();
			
		waitForExtJSAjaxComplete(20);
		
		meteringGaugesPO.close();
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, reference);
		
		meteringGaugesPO.clickEditButton(GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		meteringGaugesPO.changeTab("properties");
		
		waitForExtJSAjaxComplete(10);
		
		String createdtext = meteringGaugesPO.subStringCreatedOnFromAudit();
		
		Reporter.log(createdtext+ " ::Created On Audit Text", true);
		
		String userName = meteringGaugesPO.getLoggedUserName();
		
		Reporter.log(userName+ " ::User Name", true);
		
		softAssert.assertTrue(createdtext.contains(userName));
		
		String createdOnTimeStamp = meteringGaugesPO.subStringTimeStampFromAuditFields(createdtext);
		
		Reporter.log(createdOnTimeStamp+ "Time Stamp is available in Created On Audit Text", true);
		
		waitForExtJSAjaxComplete(10);
		
		String modifiedText = meteringGaugesPO.subStringModifiedOnFromAudit();
		
		Reporter.log("Modified On Audit Text", true);
		
		softAssert.assertTrue(modifiedText.contains(userName));
		
		String modifiedOnTimeStamp = meteringGaugesPO.subStringTimeStampFromAuditFields(modifiedText);
		
		Reporter.log(modifiedOnTimeStamp+ "Time Stamp is available in Modified Audit Text", true);
		
		meteringGaugesPO.setSerialNumber("123");
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesPO.save();
		
		waitForExtJSAjaxComplete(20);
		
		meteringGaugesPO.close();
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValue(driver, reference);
		
		meteringGaugesPO.clickEditButton(GAUGES_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		meteringGaugesPO.changeTab("properties");
		
		waitForExtJSAjaxComplete(10);
		
		modifiedText = meteringGaugesPO.subStringModifiedOnFromAudit();
		
		Reporter.log("Modified On Updated Audit Text", true);
		
		softAssert.assertTrue(modifiedText.contains(userName));
		
		String modifiedOnTimeStampUpdated = meteringGaugesPO.subStringTimeStampFromAuditFields(modifiedText);
		
		Reporter.log(modifiedOnTimeStamp+ "Updated Time Stamp available in Modified Audit Text", true);
		
		softAssert.assertNotEquals(modifiedOnTimeStamp, modifiedOnTimeStampUpdated, "Date and Time For Last Modified Action is updated to Last Changes");
		
		Thread.sleep(2000);
		
		meteringGaugesPO.close("slnmGaugeId");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, reference);
		
		clickDeleteBtn();
			
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("Check audit fields on Edit Gauge form is successfully verified", true);
	}
	
	/**
	 * Test Case ID: C60447 & C60448 
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testAutomatedCreationOfChannel() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Navigator: Automated creation of a channel : C60447"
				+ " </span><br>", true);

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Navigator: User is able to add a channel : C60448"
				+ " </span><br>", true);

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testAutomatedCreationOfChannel");

		String scope6 = "slnmEnrgBuildingExtra";
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite1";
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		Thread.sleep(20000);
				
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, site);
		waitForExtJSAjaxComplete(25);
				
		Thread.sleep(20000);
				
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, scope6);
		waitForExtJSAjaxComplete(20);
				
		Thread.sleep(20000);
		
		waitForExtJSAjaxComplete(20);
		
		String panelID = "";
		String locationType = "Energy Object";

		String code = "C60447cod"+ getCurrentDate().substring(getCurrentDate().length() - 5);
		String reference = "C60447ref"+ getCurrentDate().substring(getCurrentDate().length() - 5);
		String statusActive = "Active";

		String referenceEdited = "C60477RefEdited";
		String channelParameter = "preGaugeParameter1";
		String uom = "hectare";
		String readingInterval = "Month";
		String calibrationDate = getFutureDate(0);
		
			
			panelID = getXPanelId(locationType + " \"" +scope6 + "\"");
			
			waitForExtJSAjaxComplete(20);
		
			changeTab(panelID, "Gauges");
		
			waitForExtJSAjaxComplete(20);
		
		boolean status = Grid.isRowInGridPresentLike(driver, "Energy Gauge", "@class", GAUGES_GRID_CLASS);
		
		if(status) {
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, "Energy Gauge");
				
			clickDeleteBtn();

			waitForExtJSAjaxComplete(10);
		}
		
		clickAddBtn();
			
		waitForExtJSAjaxComplete(10);

		waitForExtJSAjaxComplete(10);

		MeteringGaugesPageObject meteringGaugesPO = new MeteringGaugesPageObject();

		softAssert.assertFalse(meteringGaugesPO.verifyTabExist("channels"), "Channels Tab Doesnt Exist in Add form of Navigator -> Gauges");

		waitForExtJSAjaxComplete(10);

		meteringGaugesPO.setCode(code);

		meteringGaugesPO.setReference(reference);

		meteringGaugesPO.setStatus(statusActive);

		waitForExtJSAjaxComplete(10);
		
		meteringGaugesPO.setScope(scope6);

		waitForExtJSAjaxComplete(10);

		meteringGaugesPO.save();

		waitForExtJSAjaxComplete(20);

		meteringGaugesPO.close();

		waitForExtJSAjaxComplete(20);

		Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, reference);

		waitForExtJSAjaxComplete(10);

		clickEditBtn();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(meteringGaugesPO.verifyTabExist("channels"),	"Channels Tab Exists in Edit form of Navigator -> Gauges");

		meteringGaugesPO.changeTab("channels");

		waitForExtJSAjaxComplete(20);

		String gaugesDialogId = getXWindowIdByClass("slnmGaugeId");

		waitForExtJSAjaxComplete(10);

		System.out.println("gaugesDialogId " + gaugesDialogId);

		Grid.checkRowInGriByTextValueAndClick(driver, "slnmGaugeId", reference);

		waitForExtJSAjaxComplete(10);

		clickButton("Edit", gaugesDialogId);

		waitForExtJSAjaxComplete(10);

		String windowHeaderText = getWindowHeaderText(EDIT_GAUGE_CHANNEL_WINDOW_HEADER);

		System.out.println(windowHeaderText);

		softAssert.assertEquals(windowHeaderText, "Edit Gauge Channel \""+ reference + "\"", "Edit Gauge Channel title of gauge correctly Appears");

		waitForExtJSAjaxComplete(10);
		
		MeteringGaugesChanelsPageObject meteringGaugesChanelsPO = new MeteringGaugesChanelsPageObject();

		softAssert.assertTrue(meteringGaugesChanelsPO.checkChannelInputDisabled("code"), "Input Element code is disabled.");

		softAssert.assertFalse(meteringGaugesChanelsPO.getCode().isEmpty(), "Code is Automatically Populated in Gauges Channel Window");

		softAssert.assertEquals(meteringGaugesChanelsPO.getReference(), reference, "Reference is Automatically Populated and equals Gauges reference in Channel Window");

		softAssert.assertTrue(meteringGaugesChanelsPO.checkChannelInputDisabled("class"), "Input Element class is disabled.");

		softAssert.assertEquals(meteringGaugesChanelsPO.getClassGaugeChannel(), "Energy Gauge", "Class is Automatically Populated and equals Location Gauges in Channel Window");

		softAssert.assertEquals(meteringGaugesChanelsPO.getChannelParameter(), "Building Area", "Channel Parameter is Automatically Populated and equals Building Area in Channel Window");

		softAssert.assertEquals(meteringGaugesChanelsPO.getUnitOfMeasure(), "m²", "Unit Of Measure is Automatically Populated and equals square meter in Channel Window");

		softAssert.assertEquals(meteringGaugesChanelsPO.getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "entryMethod"), "Manual", "Channel Entry Method is Automatically Populated and equals Manual in Channel Window");

		softAssert.assertEquals(meteringGaugesChanelsPO.getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "readingInterval"), "Month", "Reading Interval is Automatically Populated and equals Month in Channel Window");

		softAssert.assertEquals(meteringGaugesChanelsPO.getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "calculationMethod"), "Latest Value", "Calculation Method is Automatically Populated and equals Latest Value in Channel Window");

		softAssert.assertTrue(meteringGaugesChanelsPO.checkChannelInputDisabled("entryType"),"Input Element entryType is disabled.");

		softAssert.assertEquals(meteringGaugesChanelsPO.getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "entryType"), "Usage Value", "Entry Type is Automatically Populated and equals Index Value in Channel Window");

		softAssert.assertFalse(meteringGaugesChanelsPO.getCalibrationDate().isEmpty(), "Calibration Date is Empty in Gauges Channel Window");

		waitForExtJSAjaxComplete(10);

		meteringGaugesChanelsPO.close(ADD_CHANEL_GAUGES_FORM_CLASS);

		waitForExtJSAjaxComplete(10);

		meteringGaugesChanelsPO.clickAddButton(ADD_GAUGES_FORM_CLASS);

		waitForExtJSAjaxComplete(10);

		meteringGaugesChanelsPO.setReference(referenceEdited);

		meteringGaugesChanelsPO.setChannelParameter(channelParameter);

		waitForExtJSAjaxComplete(10);

		meteringGaugesChanelsPO.setChnlUOM(uom);

		waitForExtJSAjaxComplete(10);

		meteringGaugesChanelsPO.setChnlReadingInterval(readingInterval);

		waitForExtJSAjaxComplete(5);

		meteringGaugesChanelsPO.setCalibrationDate(calibrationDate);

		meteringGaugesChanelsPO.save(ADD_CHANEL_GAUGES_FORM_CLASS);

		waitForExtJSAjaxComplete(10);

		meteringGaugesChanelsPO.close(ADD_CHANEL_GAUGES_FORM_CLASS);

		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, referenceEdited, "@class", ADD_GAUGES_FORM_CLASS), "'" + referenceEdited+ "' Row is available in Channel Grid");

		waitForExtJSAjaxComplete(10);

		meteringGaugesPO.close(ADD_GAUGES_FORM_CLASS);

		waitForExtJSAjaxComplete(10);

		Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, reference);

		waitForExtJSAjaxComplete(10);

		clickDeleteBtn();

		waitForExtJSAjaxComplete(10);

		softAssert.assertAll();

		Reporter.log("Automated creation of a channel is successfully verified");
}
		
	/**
	 * Test Case ID: C60451 & C60452
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testEditDeleteExistingChannel() throws Exception {
			
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Navigator: User can edit existing channel : C60451" + " </span><br>", 
				true);
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Navigator: User is able to delete existing channels : C60452 "+ " </span><br>", 
				true);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testEditDeleteExistingChannel");
		
		String scope6 = "slnmEnrgBuildingExtra";
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite1";
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		Thread.sleep(20000);
				
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, site);
		waitForExtJSAjaxComplete(25);
				
		Thread.sleep(20000);
				
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, scope6);
		waitForExtJSAjaxComplete(20);	
				
		Thread.sleep(20000);
		
		waitForExtJSAjaxComplete(20);
		
		String panelID = "";
		String locationType = "Energy Object";
		String code = "C60451cod"+getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "C60451ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String statusActive = "Active";
		
		String referenceEdited = "C60451RefEdited";
		String referenceEdited52 = "C60452RefEdited";
		String channelParameter = "preGaugeParameter1";
		String channelParameter52 = "preGaugeParameter11";
		String uom = "hectare";
		String uom52 = "Knot";
		String readingInterval = "Month";
		String calibrationDate = getFutureDate(0);
		String chnlEntryMethod = "Automatic";
		String calculationMethod = "Sum";
		
			
			panelID = getXPanelId(locationType + " \"" +scope6 + "\"");
			
			waitForExtJSAjaxComplete(20);
		
			changeTab(panelID, "Gauges");
		
			waitForExtJSAjaxComplete(20);
		
		
		boolean status = Grid.isRowInGridPresentLike(driver, "Energy Gauge", "@class", GAUGES_GRID_CLASS);
		
		if(status) {
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, "Energy Gauge");
				
			clickDeleteBtn();

			waitForExtJSAjaxComplete(10);
		}
		
		clickAddBtn();
		
		waitForExtJSAjaxComplete(10);
		
		MeteringGaugesPageObject meteringGaugesPO = new MeteringGaugesPageObject();
		
		softAssert.assertFalse(meteringGaugesPO.verifyTabExist("channels"), "Channels Tab Doesnt Exist in Add form of Navigator -> Gauges");
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesPO.setCode(code);
	
		meteringGaugesPO.setReference(reference);
		
		meteringGaugesPO.setStatus(statusActive);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesPO.setScope(scope6);
		
		waitForExtJSAjaxComplete(10);
	
		meteringGaugesPO.save();
		
		waitForExtJSAjaxComplete(20);
		
		meteringGaugesPO.close();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, reference);
		
		waitForExtJSAjaxComplete(10);
		
		clickEditBtn();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(meteringGaugesPO.verifyTabExist("channels"), "Channels Tab Exists in Edit form of Navigator -> Gauges");
		
		meteringGaugesPO.changeTab("channels");
		
		waitForExtJSAjaxComplete(20);
		
		String gaugesDialogId = getXWindowIdByClass("slnmGaugeId");
		
		waitForExtJSAjaxComplete(10);
		
		System.out.println("gaugesDialogId " +gaugesDialogId);
		
		clickButton("Edit", gaugesDialogId);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(meteringGaugesPO.getWarningMessage().contains("Please select an item"), "Please select an item is displayed");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "slnmGaugeId", reference);
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Edit", gaugesDialogId);
		
		waitForExtJSAjaxComplete(10);
		
		String windowHeaderText = getWindowHeaderText(EDIT_GAUGE_CHANNEL_WINDOW_HEADER);
		
		System.out.println(windowHeaderText);
		
		MeteringGaugesChanelsPageObject meteringGaugesChnlPO = new MeteringGaugesChanelsPageObject();
		
		meteringGaugesChnlPO.setCalibrationDate(calibrationDate);
		
		meteringGaugesChnlPO.save(ADD_CHANEL_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(meteringGaugesChnlPO.getCalibrationDate(), calibrationDate, "Calibration Date is filled correctly in Gauges Channel Window");
		
		waitForExtJSAjaxComplete(5);
		
		meteringGaugesChnlPO.setReference(referenceEdited);
		
		meteringGaugesChnlPO.setChannelParameter(channelParameter);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesChnlPO.setChnlUOM(uom);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesChnlPO.setChnlReadingInterval(readingInterval);
		
		waitForExtJSAjaxComplete(5);
		
		meteringGaugesChnlPO.setChnlEntryMethod(chnlEntryMethod);
		
		meteringGaugesChnlPO.setChnlCalculationMethod(calculationMethod);
		
		waitForExtJSAjaxComplete(5);
		
		meteringGaugesChnlPO.save(ADD_CHANEL_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesChnlPO.close(ADD_CHANEL_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "slnmGaugeId", referenceEdited);
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Edit", gaugesDialogId);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertEquals(meteringGaugesChnlPO.getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "reference"), referenceEdited, "Reference field is filled with correct value.");
		
		softAssert.assertEquals(meteringGaugesChnlPO.getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "channelParameter"), channelParameter, "Channel Parameter field is filled with correct value.");
		
		softAssert.assertEquals(meteringGaugesChnlPO.getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "unitOfMeasure"), "ha", "Unit Of Measure field is filled with correct value.");
		
		softAssert.assertEquals(meteringGaugesChnlPO.getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "readingInterval"), readingInterval, "Reading Interval field is filled with correct value.");
		
		softAssert.assertEquals(meteringGaugesChnlPO.getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "calculationMethod"), calculationMethod, "Calculation Method field is filled with correct value.");
		
		softAssert.assertEquals(meteringGaugesChnlPO.getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "entryMethod"), chnlEntryMethod, "Channel Entry Method is Automatically Populated and equals Manual in Channel Window");
		
		softAssert.assertEquals(meteringGaugesChnlPO.getFieldValue(ADD_CHANEL_GAUGES_FORM_CLASS, "entryType"), "Usage Value", "Entry Type is Automatically Populated and equals Index Value in Channel Window");
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesChnlPO.close(ADD_CHANEL_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesPO.close(ADD_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, code);
		
		waitForExtJSAjaxComplete(10);
		
		clickEditBtn();
		
		waitForExtJSAjaxComplete(20);
		
		meteringGaugesPO.changeTab("channels");
		
		waitForExtJSAjaxComplete(20);
		
		meteringGaugesChnlPO.clickAddButton(ADD_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesChnlPO.setReference(referenceEdited52);
		
		meteringGaugesChnlPO.setChannelParameter(channelParameter52);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesChnlPO.setChnlUOM(uom52);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesChnlPO.setChnlReadingInterval(readingInterval);
		
		waitForExtJSAjaxComplete(5);
		
		meteringGaugesChnlPO.save(ADD_CHANEL_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesChnlPO.close(ADD_CHANEL_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, referenceEdited52, "@class", ADD_GAUGES_FORM_CLASS), "'"+referenceEdited52+"' Row is available in Channel Grid");
		
		waitForExtJSAjaxComplete(10);
		
		gaugesDialogId = getXWindowIdByClass("slnmGaugeId");
		
		waitForExtJSAjaxComplete(5);
		
		System.out.println("ID is " +gaugesDialogId);
		
		meteringGaugesChnlPO.verifyDeleteFunctionality(gaugesDialogId, "Delete");
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(meteringGaugesPO.getWarningMessage().contains("Please select an item"), "Please select an item is displayed");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "slnmGaugeId", referenceEdited52);
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Delete", gaugesDialogId);
		
		clickOnDialogButton("No");
		
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, referenceEdited52, "@class", ADD_GAUGES_FORM_CLASS), "'"+referenceEdited52+"' Row is available in Channel Grid");
		
		waitForExtJSAjaxComplete(10);
		
		clickButton("Delete", gaugesDialogId);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(5);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, referenceEdited52, "@class", ADD_GAUGES_FORM_CLASS), "'"+referenceEdited52+"' Row is Not available in Channel Grid");
		
		meteringGaugesPO.close(ADD_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, code);
		
		waitForExtJSAjaxComplete(10);
		
		clickDeleteBtn();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("User can edit/Delete existing channel is successfully Verified", true);
}
	/**
	 * Test Case ID: C60462
	 * Author : SSU
	 */
	@Test(enabled=true)
	public void testChannelsEntryMethod() throws Exception {
			
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Navigator: Channels Entry Methods : C60462" + " </span><br>", 
				true);
				
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testChannelsEntryMethod");
		
		String scope6 = "slnmEnrgBuildingExtra";
		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite1";
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		Thread.sleep(20000);
				
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, site);
		waitForExtJSAjaxComplete(25);
				
		Thread.sleep(20000);
				
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, scope6);
		waitForMaskDisappear();
				
		Thread.sleep(20000);
		
		waitForExtJSAjaxComplete(20);
		
		String panelID = "";
		String locationType = "Energy Object";
		String code = "C60462cod"+getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "C60462ref"+getCurrentDate().substring(getCurrentDate().length()-5);
		String statusActive = "Active";
		
		String referenceEdited = "C60462RefEdited";
		String referenceEdited62 = "C60462RefEdited";
		String channelParameterEdited = "preGaugeParameter1";
		String channelParameter62 = "preGaugeParameter11";
		String uom = "hectare";
		String uom52 = "Knot";
		String readingInterval = "Month";
		String chnlAutomaticEntryMethod = "Automatic";
		String chnlManualEntryMethod = "Manual";
		
			panelID = getXPanelId(locationType + " \"" +scope6 + "\"");
			
			Thread.sleep(5000);
			
			waitForExtJSAjaxComplete(20);
		
			changeTab(panelID, "Gauges");
		
			waitForExtJSAjaxComplete(20);

			waitForExtJSAjaxComplete(20);
		
		boolean status = Grid.isRowInGridPresentLike(driver, "Energy Gauge", "@class", GAUGES_GRID_CLASS);
		
		if(status) {
			
			Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, "Energy Gauge");
				
			clickDeleteBtn();

			waitForExtJSAjaxComplete(10);
		}
			
		clickAddBtn();
		
		waitForExtJSAjaxComplete(10);
		
		MeteringGaugesPageObject meteringGaugesPO = new MeteringGaugesPageObject();
		
		softAssert.assertFalse(meteringGaugesPO.verifyTabExist("channels"), "Channels Tab Doesnt Exist in Add form of Navigator -> Gauges");
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesPO.setCode(code);
	
		meteringGaugesPO.setReference(reference);
		
		meteringGaugesPO.setStatus(statusActive);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesPO.setScope(scope6);
		
		waitForExtJSAjaxComplete(10);
	
		meteringGaugesPO.save();
		
		waitForExtJSAjaxComplete(20);
		
		meteringGaugesPO.close();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, reference);
		
		waitForExtJSAjaxComplete(10);
		
		clickEditBtn();
		
		waitForExtJSAjaxComplete(20);
		
		meteringGaugesPO.changeTab("channels");
		
		waitForExtJSAjaxComplete(20);
		
		//******** Add a New Gauge Channel with Automatic Entry Method
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, reference, "@class", ADD_GAUGES_FORM_CLASS), "'"+reference+"' Row is available in Channel Grid");
		
		MeteringGaugesChanelsPageObject meteringGaugesChnlPO = new MeteringGaugesChanelsPageObject();
		
		meteringGaugesChnlPO.clickAddButton(ADD_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesChnlPO.setReference(referenceEdited);
		
		meteringGaugesChnlPO.setChannelParameter(channelParameterEdited);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesChnlPO.setChnlUOM(uom);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesChnlPO.setChnlReadingInterval(readingInterval);
		
		waitForExtJSAjaxComplete(5);
		
		String gaugesChnlDialogId = getXWindowIdByClass("slnmGaugeChnlId");
		
		waitForExtJSAjaxComplete(10);
		
		System.out.println("gaugesChnlDialogId " +gaugesChnlDialogId);
		
		String []entryMethod = {"Manual", "Automatic"};
		
		List<String> entryValues = meteringGaugesChnlPO.getEntryMethodDropDownValues("@id", gaugesChnlDialogId);

		softAssert.assertEqualsNoOrder(entryValues.toArray(), entryMethod, "Manual, automatic are displayed as next Possible statuses");
		
		meteringGaugesChnlPO.setChnlEntryMethod(chnlManualEntryMethod);		
		
		waitForExtJSAjaxComplete(5);
		
		meteringGaugesChnlPO.save(ADD_CHANEL_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesChnlPO.close(ADD_CHANEL_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, referenceEdited, "@class", ADD_GAUGES_FORM_CLASS), "'"+referenceEdited+"' Row is available in Channel Grid");
		
		meteringGaugesChnlPO.clickAddButton(ADD_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesChnlPO.setReference(referenceEdited62);
		
		meteringGaugesChnlPO.setChannelParameter(channelParameter62);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesChnlPO.setChnlUOM(uom52);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesChnlPO.setChnlEntryMethod(chnlAutomaticEntryMethod);		
		
		waitForExtJSAjaxComplete(5);
		
		meteringGaugesChnlPO.save(ADD_CHANEL_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesChnlPO.close(ADD_CHANEL_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, referenceEdited62, "@class", ADD_GAUGES_FORM_CLASS), "'"+referenceEdited62+"' Row is available in Channel Grid");
		
		Grid.checkRowInGriByTextValueAndClick(driver, "slnmGaugeId", reference);
		
		waitForExtJSAjaxComplete(10);

		String gaugesDialogId = getXWindowIdByClass("slnmGaugeId");
		
		waitForExtJSAjaxComplete(10);
		
		System.out.println("gaugesDialogId " +gaugesDialogId);
		
		clickButton("Edit", gaugesDialogId);
		
		waitForExtJSAjaxComplete(10);
		
		String []entryMethodNew = {"Manual", "Automatic"};
		
		gaugesChnlDialogId = getXWindowIdByClass("slnmGaugeChnlId");
		
		waitForExtJSAjaxComplete(10);
		
		System.out.println("gaugesChnlDialogId " +gaugesChnlDialogId);
		
		List<String> entryValuesNew = meteringGaugesChnlPO.getEntryMethodDropDownValues("@id", gaugesChnlDialogId);

		softAssert.assertEqualsNoOrder(entryValuesNew.toArray(), entryMethodNew, "Manual, automatic are displayed as next Possible statuses");
		
		meteringGaugesChnlPO.setChnlEntryMethod(chnlAutomaticEntryMethod);		
		
		waitForExtJSAjaxComplete(5);
		
		meteringGaugesChnlPO.save(ADD_CHANEL_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesChnlPO.close(ADD_CHANEL_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		meteringGaugesPO.close(ADD_GAUGES_FORM_CLASS);
		
		waitForExtJSAjaxComplete(10);
		
		Grid.checkRowInGriByTextValueAndClick(driver, GAUGES_GRID_CLASS, code);
		
		waitForExtJSAjaxComplete(10);
		
		clickDeleteBtn();
		
		waitForExtJSAjaxComplete(10);
		
		softAssert.assertAll();
		
		Reporter.log("Navigator: Channels Entry Methods is successfully Verified", true);
	}

}
