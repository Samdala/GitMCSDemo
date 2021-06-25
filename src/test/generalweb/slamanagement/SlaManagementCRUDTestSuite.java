package test.generalweb.slamanagement;

import java.io.IOException;



import java.util.Calendar;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import test.generalweb.rightvisibility.AdministrationPageObject;

public class SlaManagementCRUDTestSuite extends
		SlaManagementPageObject {
	
	
	
	 @Test(enabled=true)
	public void testValidationMandatoryFieldsSLA() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Mandatory fields check SLA : C74589 </span><br>",
				true);

		Reporter.log(" Mandatory fields check SLA "  + " <br>",
				true);
		
		//String dateFrom = "03-08-2032";
	
		//String dateUntil = "03-09-2032";
		
		int random = (int)(Math.random()*1000)+18;
		
		String dateFrom = this.getFutureDate(random);

		String dateUntil = this.getFutureDate(random+30);
		
		String classObject = "Work Order";
		
		String rule = "1";
		
		String lowTarget = "24.00";
		
		String highTarget = "48.00";
		
	
		
		String customer = "My Company";
		
		String metric = "0";
		
		String highValues = "Bad";
		
		boolean excludeSuspended = false;
		
		String startStatus = "Suspended";
		
		String endStatus = "Finished";
		
		String defaultTimeZone2 = "(GMT) Casablanca";
		
		String code = "teauto code" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String reference = "teauto reference" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String supplier = "2preCompName";
		
		String supplier2 = "My Company";
		
		String metricName = "mtrname" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String defaultTimeTable = "1preTimeTableSLARef";
		
		String defaultTimeZone = "(GMT) Monrovia, Reykjavik";
		
		String disabledSuplier = "preSuplNameDis";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCRUDSLACall");
		
		String message ="";
		
	
		//expandAdministration();
		clickAdministration();

		waitForExtJSAjaxComplete(20);

		expandModuleSettings();
		
		waitForExtJSAjaxComplete(20);

		expandHelpDesk();

		waitForExtJSAjaxComplete(20);

		click(XPATH_SLA);
		
		waitForExtJSAjaxComplete(20);
		
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		clearClassObject();
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		setReference(reference);
		
		setValidityUntil(dateUntil);
		
		setValidityFrom(dateFrom);
		
		setCustomer(customer);
		
		setDefaultTimeTable(defaultTimeTable);
		
		setDefaultTimeZone(defaultTimeZone);
		
		save();
		
		waitForExtJSAjaxComplete(20);

	//	message= McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-dlg')]").getText();

		message = getSLAObjectWindowError("SLA Object");
		//softAssert.assertTrue(message.contains("Valid Object Class"),"Can't save SLA without class. error message is ok: "+message);
		softAssert.assertTrue(message.contains("errors in this dialog box"),"Can't save SLA without class. error message is ok: "+message);


		//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save SLA') and not (contains(.,'Unexpected'))]"),"error mandatory validity from message ok");

		//clickOnDialogButton("OK");

		waitForExtJSAjaxComplete(20);
		
		setClassObject(classObject);
		
		waitForExtJSAjaxComplete(25);
		

//	C28920		Check if all enabled suppliers are available in Select a Supplier form
		
		String name = McsElement.getElementByXpath(driver,"(//div[contains(@class,'x-resizable')]//label[contains(text(),'Supplier')]/..//input)[last()]").getAttribute("name");
		
		clickLookup(SLA_FORM_CLASS, name);
		
		softAssert.assertFalse(setExactValueGridLookupWithFiltersWithValidation("@class", "x-window-noborder", disabledSuplier, "Name"),"inactive supplier is not present");
		
		clickLookup(SLA_FORM_CLASS, name);
		
		softAssert.assertTrue(setExactValueGridLookupWithFiltersWithValidation("@class", "x-window-noborder", supplier2, "Name"),"active supplier is present");

		setSupplier(supplier);
		
		clearValidityFrom();
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		// message = McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-dlg')]").getText();
		message = getSLAObjectWindowError("SLA Object");

		softAssert.assertTrue(message.contains("errors in this dialog box."),"error message is ok: "+message);
		
//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save SLA') and not (contains(.,'Unexpected'))]"),"error mandatory validity from message ok");
		
		//clickOnDialogButton("OK");
		
		setValidityFrom(dateFrom);
		
		clearValidityUntil();
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		//message = McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-dlg')]").getText();
		message = getSLAObjectWindowError("SLA Object");

		softAssert.assertTrue(message.contains("errors in this dialog box."),"error message is ok: "+message);

//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save SLA') and not (contains(.,'Unexpected'))]"),"error mandatory validity until message ok");
		
		//clickOnDialogButton("OK");
		
		clearCode();
		
		setValidityUntil(dateUntil);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		//message = McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-dlg')]").getText();
		message = getSLAObjectWindowError("SLA Object");

		softAssert.assertTrue(message.contains("errors in this dialog box."),"error message is ok: "+message);
		
//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save SLA') and not (contains(.,'Unexpected'))]"),"error mandatory code message ok");
		
		//clickOnDialogButton("OK");
		
		clearReference();
		
		setCode(code);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		//message = McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-dlg')]").getText();
		message = getSLAObjectWindowError("SLA Object");
		
		//softAssert.assertTrue(message.contains("not save SLA") && !message.contains("Unexpected"),"error message is ok: "+message);
		softAssert.assertTrue(message.contains("errors in this dialog box."),"error message is ok: "+message);
		
//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save SLA') and not (contains(.,'Unexpected'))]"),"error mandatory reference message ok");
		
	//	clickOnDialogButton("OK");
		
		setReference(reference);
		
		clearSupplier();
		
		setReference(reference);
		
		setDefaultTimeTable(defaultTimeTable);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
	//	message = McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-dlg')]").getText();
		
		message = getSLAObjectWindowError("SLA Object");

		//softAssert.assertTrue(message.contains("not save SLA") && !message.contains("Unexpected"),"error message is ok: "+message);
		softAssert.assertTrue(message.contains("errors in this dialog box."),"error message is ok: "+message);
		
//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save SLA') and not (contains(.,'Unexpected'))]"),"error mandatory supplier message ok");
		
		//clickOnDialogButton("OK");
		
		setClassObject("Call");
		
		waitForExtJSAjaxComplete(25);
		
		clearCustomer();
		
		setReference(reference);
		
		setDefaultTimeTable(defaultTimeTable);
		
		save();
		
		waitForExtJSAjaxComplete(20);

		//message = McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-dlg')]").getText();
		message = getSLAObjectWindowError("SLA Object");
		
		//softAssert.assertTrue(message.contains("not save SLA") && !message.contains("Unexpected"),"error message is ok: "+message);
		softAssert.assertTrue(message.contains("errors in this dialog box."),"error message is ok: "+message);

		
//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save SLA') and not (contains(.,'Unexpected'))]"),"error mandatory supplier message ok");
		
		//clickOnDialogButton("OK");
		
		setCustomer(customer);

		clearDefaultTimeTable();
		
		waitForExtJSAjaxComplete(3);
		
		setCustomer(customer);
		
		waitForExtJSAjaxComplete(10);
		
		save();

		waitForExtJSAjaxComplete(20);

		//message = McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-dlg')]").getText();
		message = getSLAObjectWindowError("SLA Object");

		softAssert.assertTrue(message.contains("errors in this dialog box."),"error message is ok: "+message);


		//softAssert.assertTrue(message.contains("not save SLA") && !message.contains("Unexpected"),"error message is ok: "+message);

//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save SLA') and not (contains(.,'Unexpected'))]"),"error mandatory time table message ok");
		
		//clickOnDialogButton("OK");
		
		setDefaultTimeTable(defaultTimeTable);
		
		clearDefaultTimeZone();
		
		setDefaultTimeTable(defaultTimeTable);
		
		save();
		
		waitForExtJSAjaxComplete(20);
/*
		message = McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-dlg')]").getText();
		
		softAssert.assertTrue(message.contains("not save SLA") && !message.contains("Unexpected"),"error message is ok: "+message);
		*/
		message = getSLAObjectWindowError("SLA Object");

		softAssert.assertTrue(message.contains("errors in this dialog box."),"error message is ok: "+message);

//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save SLA') and not (contains(.,'Unexpected'))]"),"error mandatory time zone message ok");
		
		//clickOnDialogButton("OK");
		
		setDefaultTimeZone(defaultTimeZone);
		
		save();
		
		waitForExtJSAjaxComplete(25);
		
		clickMetricsTab();
		
					
			setMetricNew(metric, "", startStatus, endStatus, highValues, excludeSuspended);
				
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		message = McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-dlg')]").getText();
		
		softAssert.assertTrue(message.contains("not save Metric") && message.contains("Name"),"error message is ok: "+message);

		
//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save Metric') and not (contains(.,'Unexpected'))]"),"error mandatory metric name message ok");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
			
			setMetricNew(metric, metricName, "", endStatus, highValues, excludeSuspended);
			
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		message = McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-dlg')]").getText();
		
		softAssert.assertTrue(message.contains("not save Metric") && message.contains("Start Status"),"error message is ok: "+message);

		
//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save Metric') and not (contains(.,'Unexpected'))]"),"error mandatory metric startstatus message ok");
		
		clickOnDialogButton("OK");
		
			
			setMetricNew(metric, metricName, startStatus, "", highValues, excludeSuspended);
			


		save();
		
		waitForExtJSAjaxComplete(20);
		
		message = McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-dlg')]").getText();
		
		softAssert.assertTrue(message.contains("not save Metric") && message.contains("End Status"),"error message is ok: "+message);

		
//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save Metric') and not (contains(.,'Unexpected'))]"),"error mandatory metric endstatus message ok");
		
		clickOnDialogButton("OK");
		
			
			setMetricNew(metric, metricName, startStatus, endStatus, highValues, excludeSuspended);
			

		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "METRIC"+metric);
		
		waitForExtJSAjaxComplete(20);
		
			
		setTargetRuleNative(rule, lowTarget, highTarget, "", defaultTimeZone);
		
		saveTargetRules();
		
		waitForExtJSAjaxComplete(20);
		

		message = getTargetRulesWindowErrorMsg();
		softAssert.assertTrue(message.contains("There are errors in this dialog box"),"error message is ok: "+message);

		/*message = McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-dlg')]").getText();
		
		softAssert.assertTrue(message.contains("not save the Target") && message.contains("Time Table"),"error message is ok: "+message);
*/
		
//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save the Target') and not (contains(.,'Unexpected'))]"),"error mandatory timetable target rule message ok");
		
		/*clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "METRIC"+metric);
		
		waitForExtJSAjaxComplete(20);*/
		
		waitForExtJSAjaxComplete(10);
		setTargetRuleNative(rule, lowTarget, highTarget, defaultTimeTable, "");
		waitForExtJSAjaxComplete(10);
		
		saveTargetRules();
		
		waitForExtJSAjaxComplete(10);
		message = getTargetRulesWindowErrorMsg();
		softAssert.assertTrue(message.contains("There are errors in this dialog box"),"error message is ok: "+message);

		
		/*message = McsElement.getElementByXpath(driver, "//div[contains(@class,'x-window-dlg')]").getText();
		
		softAssert.assertTrue(message.contains("not save the Target") && message.contains("Time Zone"),"error message is ok: "+message);
		
		clickOnDialogButton("OK");
*/
		
//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save the Target') and not (contains(.,'Unexpected'))]"),"error mandatory timetable target rule message ok");	
		/*
		Grid.checkRowInGriByTextValueAndClick(driver, "METRIC"+metric);
		
		waitForExtJSAjaxComplete(20);
		
			*/
		setTargetRuleNative(rule, "", highTarget, defaultTimeTable, defaultTimeZone);
			
		saveCloseTargetRules();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "METRIC"+metric);
		
			
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, "All|[none]|0.00|48.00|"+defaultTimeTable+"|"+defaultTimeZone, "@id", "MetricsTab"),"metric rule is created");
		
			//TODO: by default null target value is validated to '0'
		/*	
			setTargetRuleNew(rule, lowTarget, "", defaultTimeTable, defaultTimeZone);
			
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "METRIC"+metric);
	
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, "All|[none]|24.00|48.00|"+defaultTimeTable+"|"+defaultTimeZone, "@id", "MetricsTab"),"metric rule is created");
	
		*/
		softAssert.assertAll();
		
		Reporter.log("sla object mandatory fields were checked", true);
		
	}
	
	
	 @Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testCRUDSLA3() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#0000FF'> "
				+ "Test: Copy SLA Object: C74583 " + " </span><br>",
				true);

		Reporter.log("Copy SLA Object  "  + " <br>",
				true);
		

		int random = (int)(Math.random()*100000)+18;
		
		String dateFrom = this.getFutureDate(random);

		String dateUntil = this.getFutureDate(random+30);
		
		/*String dateFrom = "03-08-2029";
	
		String dateUntil = "03-09-2029";
		
		String dateFrom2 = "05-09-2029";
		
		String dateUntil2 = "06-09-2029";*/
		
		String dateFrom2 = this.getFutureDate(random+32);

		String dateUntil2 = this.getFutureDate(random+60);
		
		String classObject = "Call";
		
		String code = "copycode" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String reference = "copyreference" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String customer = "My Company";
		
		String customer2 = "2preCompName";
		
		String metric = "0";
		
		String metric1 = "1";
		
		String highValues = "Bad";
		
		boolean excludeSuspended = false;
		
		String highValues2 = "Good";
		
		boolean excludeSuspended2 = true;

		String startStatus2 = "Suspended";
		
		String endStatus2 = "Finished";
		
		String metricName = "mtrname" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String startStatus = "In Progress";
		
		String endStatus = "Archived";
		
		String defaultTimeTable = "1preTimeTableSLARef";
		
		String defaultTimeZone = "(GMT) Monrovia, Reykjavik";
		
		String defaultTimeZone2 = "(GMT) Casablanca";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCRUDSLA3");
		
		//expandAdministration();
		
		clickAdministration();

		waitForExtJSAjaxComplete(20);

		expandModuleSettings();
		
		waitForExtJSAjaxComplete(20);

		expandHelpDesk();

		waitForExtJSAjaxComplete(20);

		click(XPATH_SLA);
		
		waitForExtJSAjaxComplete(20);
				
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		setClassObject(classObject);
		
		setCode(code);
		
		setReference(reference);
		
		setValidityUntil(dateUntil);
		
		setValidityFrom(dateFrom);
		
		setCustomer(customer);
		
		setDefaultTimeTable(defaultTimeTable);
		
		setDefaultTimeZone(defaultTimeZone);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		clickMetricsTab();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//div[contains(@class,'x-resizable')]//div[contains(@class,'x-grid3-check-col-on ')]"),"active metric is absent");
				
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//div[contains(@class,'x-resizable')]//div[contains(text(),'Valid')]"),"rule metric is absent");
		
			
			setMetricNew(metric, metricName, startStatus, endStatus, highValues, excludeSuspended);
		
		clickAuditingTab();
		
		String xpath = "//div[contains(@class,'x-panel-body-noborder') "
				+ "and contains(text(),'Created on')"
				+ " and (contains(text(),"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+") or contains(text(),"+(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+1)+") or contains(text(),"+(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1)+") )"
						+ " and  contains(.,'Last modified on') "
						+ "and (contains(text(),"+Calendar.getInstance().get(Calendar.YEAR)+") or contains(text(),"+(Calendar.getInstance().get(Calendar.YEAR)-1)+") or contains(text(),"+(Calendar.getInstance().get(Calendar.YEAR)+1)+") ) "
								+ "and (contains(text(),"+(Calendar.getInstance().get(Calendar.MONTH)+1)+") or contains(text(),"+(Calendar.getInstance().get(Calendar.MONTH)+2)+") or contains(text(),"+(Calendar.getInstance().get(Calendar.MONTH)+0)+")   ) ]";

		
		String auditingText = McsElement.getElementByXpath(driver,xpath).getText();
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		WebElement element = Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		waitForExtJSAjaxComplete(20);
		
		doubleClick(element);
		
		waitForExtJSAjaxComplete(20);
		
		copy();
		
		softAssert.assertEquals(getClassObject(), classObject,"class object is ok");
		
//		softAssert.assertEquals(getCode(), code,"code is ok");
//		
//		softAssert.assertEquals(getReference(), reference,"reference is ok");
		
		softAssert.assertEquals(getCustomer(), customer,"customer is ok");
		
		softAssert.assertEquals(getDefaultTimeTable(), defaultTimeTable,"default time table is ok");
		
		softAssert.assertEquals(getDefaultTimeZone(), defaultTimeZone,"default time zone is ok");
		
//		softAssert.assertEquals(getValidityFrom(), dateFrom,"from date is ok");
//		
//		softAssert.assertEquals(getValidityUntil(), dateUntil,"until date is ok");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//div[contains(text(),'The SLA Object is now readonly, because the Validity Period has expired')]"),"read only ok");
		
//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//table[not (contains(@class,'disabled'))]//button[contains(text(),'Copy')]"),"copy button is present");
		
//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[not (contains(@class,'hide'))]//button[contains(text(),'Cancel')]"),"cancel button is present");
		
//		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//td[(contains(@class,'hide'))]//button[contains(text(),'Cancel')]"),"cancel button is not hiden");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[not(contains(@class,'hide'))]//button[text()='Save']"),"save button is present");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//td[(contains(@class,'hide'))]//button[text()='Save']"),"save button is not hiden");

		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[not (contains(@class,'hide'))]//button[contains(text(),'Save and Close')]"),"save close button is present");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//td[ (contains(@class,'hide'))]//button[contains(text(),'Save and Close')]"),"save close button is not hiden");
		
		setCode(code+"cp");
		
		waitForExtJSAjaxComplete(20);
		
		setReference(reference+"cp");
		
		waitForExtJSAjaxComplete(20);
	
		setCustomer(customer2);
		
		waitForExtJSAjaxComplete(20);
		
		setDefaultTimeTable(defaultTimeTable);
		
		waitForExtJSAjaxComplete(20);
		
		setValidityFrom(dateFrom2);
		
		waitForExtJSAjaxComplete(20);
		
		setValidityUntil(dateUntil2);
		
		waitForExtJSAjaxComplete(20);
		
		setDefaultTimeZone(defaultTimeZone2);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		clickMetricsTab();
		
		waitForExtJSAjaxComplete(20);
		
//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//table[not (contains(@class,'disabled'))]//button[contains(text(),'Copy')]"),"copy button is present");
//		
//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[not (contains(@class,'hide'))]//button[contains(text(),'Cancel')]"),"cancel button is present");
//		
//		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//td[(contains(@class,'hide'))]//button[contains(text(),'Cancel')]"),"cancel button is not hiden");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[not(contains(@class,'hide'))]//button[text()='Save']"),"save button is present");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//td[(contains(@class,'hide'))]//button[text()='Save']"),"save button is not hiden");

		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[not (contains(@class,'hide'))]//button[contains(text(),'Save and Close')]"),"save close button is present");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//td[ (contains(@class,'hide'))]//button[contains(text(),'Save and Close')]"),"save close button is not hiden");

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "METRIC0|"+metricName+"|"+startStatus+"|"+endStatus+"|"+highValues, "@id", "MetricsTab"),"metric 0 is ok before edit");
		
			
			setMetricNew(metric1, metricName+"cp", startStatus, endStatus, highValues, excludeSuspended);
			waitForExtJSAjaxComplete(10);
		save();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "METRIC"+metric1);
		
		waitForExtJSAjaxComplete(20);
		
			
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, "All|[none]|24.00|48.00|"+defaultTimeTable+"|"+defaultTimeZone2, "@id", "MetricsTab"),"second metric rule is created");
		
			
			setMetricNew(metric, metricName+"cp2", startStatus2, endStatus2, highValues2, excludeSuspended2);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "METRIC"+metric);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "METRIC0|"+metricName+"cp2"+"|"+startStatus2+"|"+endStatus2+"|"+highValues2, "@id", "MetricsTab"),"metric 0 is edited");
		
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, "All|[none]|24.00|48.00|"+defaultTimeTable+"|"+defaultTimeZone, "@id", "MetricsTab"),"metric rule is edited after metric edit");
		
		waitForExtJSAjaxComplete(20);
		
			
		setTargetRuleNative("1", "2", "5");
		waitForExtJSAjaxComplete(10);
		
		saveCloseTargetRules();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "METRIC"+metric);	
		
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, "All|[none]|2.00|5.00|"+defaultTimeTable+"|"+defaultTimeZone, "@id", "MetricsTab"),"metric rule is edited");
		
		
		closeXWindow();
		
		WebElement element2 = Grid.checkRowInGriByTextValueAndClick(driver, reference+"cp");
		
		waitForExtJSAjaxComplete(20);
		
		doubleClick(element2);
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		softAssert.assertEquals(getClassObject(), classObject,"class object is ok");
		
		softAssert.assertEquals(getCode(), code+"cp","code is ok after copy edit");
		
		softAssert.assertEquals(getReference(), reference+"cp","reference is ok after copy edit");
		
		softAssert.assertEquals(getCustomer(), customer2,"customer2 is ok after copy edit");
		
		softAssert.assertEquals(getDefaultTimeTable(), defaultTimeTable,"default time table2 is ok after copy edit");
		
		softAssert.assertEquals(getDefaultTimeZone(), defaultTimeZone2,"default time zone2 is ok after copy edit");
		
		softAssert.assertEquals(getValidityFrom(), dateFrom2,"from date is ok after copy edit");
		
		softAssert.assertEquals(getValidityUntil(), dateUntil2,"until date is ok after copy edit");
		
		clickAuditingTab();
		
		waitForExtJSAjaxComplete(20);
		
//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//table[not (contains(@class,'disabled'))]//button[contains(text(),'Copy')]"),"copy button is present");
//		
//		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[not (contains(@class,'hide'))]//button[contains(text(),'Cancel')]"),"cancel button is present");
//		
//		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//td[(contains(@class,'hide'))]//button[contains(text(),'Cancel')]"),"cancel button is not hiden");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[not(contains(@class,'hide'))]//button[text()='Save']"),"save button is present");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//td[(contains(@class,'hide'))]//button[text()='Save']"),"save button is not hiden");

		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[not (contains(@class,'hide'))]//button[contains(text(),'Save and Close')]"),"save close button is present");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//td[ (contains(@class,'hide'))]//button[contains(text(),'Save and Close')]"),"save close button is not hiden");
		
		String auditingTextEd = McsElement.getElementByXpath(driver,xpath).getText();
		
		softAssert.assertFalse(auditingTextEd.equals(auditingText),"auditing text changed after copy editing: "+ auditingTextEd+" VS " +auditingText);

		closeXWindow();
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference+"cp");
		
		clickDeleteButton();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("OK");
		
		softAssert.assertAll();
		
		Reporter.log("sla object was copied", true);
		
	}
	
	


	 @Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testCRUDSLACall() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#0000FF'> "
				+ "Test: Create SLA Object for Call with Metric and delete : C74585(Testrail) C74584 " + " </span><br>",
				true);

		Reporter.log("Create SLA Object for Call with Metric  "  + " <br>",
				true);
		
/*		String dateFrom = "03-08-2025";
	
		String dateUntil = "03-09-2025";
		
		String dateFrom2 = "05-08-2025";
		
		String dateUntil2 = "06-09-2025";
*/
		int random = (int)(Math.random()*10000)+18;
		
		String dateFrom = this.getFutureDate(random);
		//String dateFrom = "03-08-2026";
	
		//String dateUntil = "03-09-2026";
		String dateUntil = this.getFutureDate(random+30);
		
		String dateFrom2 = this.getFutureDate(random+2);
		
		String dateUntil2 =  this.getFutureDate(random+33);
		
		
		String classObject = "Call";
		
		String code = "autocode" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String reference = "autoreference" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String customer = "My Company";
		
		String customer2 = "2preCompName";
		
		String metric = "0";
		
		String metric1 = "1";
		
		String highValues = "Bad";
		
		boolean excludeSuspended = false;
		
		String highValues2 = "Good";
		
		boolean excludeSuspended2 = true;

		String startStatus2 = "Suspended";
		
		String endStatus2 = "Finished";
		
		String metricName = "mtrname" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String startStatus = "In Progress";
		
		String endStatus = "Archived";
		
		String defaultTimeTable = "1preTimeTableSLARef";
		
		String defaultTimeZone = "(GMT) Monrovia, Reykjavik";
		
		String defaultTimeZone2 = "(GMT) Casablanca";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCRUDSLACall");
		
		//expandAdministration();
		
		clickAdministration();

		waitForExtJSAjaxComplete(20);

		expandModuleSettings();
		
		waitForExtJSAjaxComplete(20);

		expandHelpDesk();

		waitForExtJSAjaxComplete(20);

		click(XPATH_SLA);
		
		waitForExtJSAjaxComplete(20);

		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		setClassObject(classObject);
		
		setCode(code);
		
		setReference(reference);
		
		setValidityUntil(dateUntil);
		
		setValidityFrom(dateFrom);
		
		setCustomer(customer);
		
		setDefaultTimeTable(defaultTimeTable);
		
		setDefaultTimeZone(defaultTimeZone);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		clickMetricsTab();
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//div[contains(@class,'x-resizable')]//div[contains(@class,'x-grid3-check-col-on ')]"),"active metric is absent");
				
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//div[contains(@class,'x-resizable')]//div[contains(text(),'Valid')]"),"rule metric is absent");
		
		
			
			setMetricNew(metric, metricName, startStatus, endStatus, highValues, excludeSuspended);
		
			save();
		

		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "METRIC"+metric);
		
		waitForExtJSAjaxComplete(20);
			
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, "All|[none]|24.00|48.00|"+defaultTimeTable+"|"+defaultTimeZone, "@id", "MetricsTab"),"metric rule is created");

			clickAuditingTab();
		
		waitForExtJSAjaxComplete(20);
		
		String xpath = "//div[contains(@class,'x-panel-body-noborder') "
				+ "and contains(text(),'Created on')"
				+ " and (contains(text(),"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+") or contains(text(),"+(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+1)+") or contains(text(),"+(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1)+") )"
						+ " and  contains(.,'Last modified on') "
						+ "and (contains(text(),"+Calendar.getInstance().get(Calendar.YEAR)+") or contains(text(),"+(Calendar.getInstance().get(Calendar.YEAR)-1)+") or contains(text(),"+(Calendar.getInstance().get(Calendar.YEAR)+1)+") ) "
								+ "and (contains(text(),"+(Calendar.getInstance().get(Calendar.MONTH)+1)+") or contains(text(),"+(Calendar.getInstance().get(Calendar.MONTH)+2)+") or contains(text(),"+(Calendar.getInstance().get(Calendar.MONTH)+0)+")   ) ]";

		 
		softAssert.assertTrue(McsElement.isElementPresent(driver, xpath),"text on auditing tab is ok");
		
		String auditingText = McsElement.getElementByXpath(driver,xpath).getText();
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(20);
		
//c29788 create two sla with same valid period		
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		setClassObject(classObject);
		
		setCode(code);
		
		setReference(reference+"same");
		
		setValidityUntil(dateUntil2);
		
		setValidityFrom(dateFrom2);
		
		setCustomer(customer);
		
		setDefaultTimeTable(defaultTimeTable);
		
		setDefaultTimeZone(defaultTimeZone2);
		
		saveClose();
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save SLA') and contains(.,'Code already')]"),"error unique code message ok");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code+"same");
		
//28916 - same code and reference		
		
		setReference(reference);
		
		saveClose();
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save SLA') and contains(.,'Reference already')]"),"error unique reference message ok");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		setReference(reference+"same");
		
		saveClose();
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save SLA') and contains(.,'overlaps')]"),"error overlap message ok");
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(20);
		
		WebElement element = Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		waitForExtJSAjaxComplete(20);
		
		doubleClick(element);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getClassObject(), classObject,"class object is ok");
		
		softAssert.assertEquals(getCode(), code,"code is ok");
		
		softAssert.assertEquals(getReference(), reference,"reference is ok");
		waitForExtJSAjaxComplete(20);
		softAssert.assertEquals(getCustomer(), customer,"customer is ok");
		
		softAssert.assertEquals(getDefaultTimeTable(), defaultTimeTable,"default time table is ok");
		
		softAssert.assertEquals(getDefaultTimeZone(), defaultTimeZone,"default time zone is ok");
		
		softAssert.assertEquals(getValidityFrom(), dateFrom,"from date is ok");
		
		softAssert.assertEquals(getValidityUntil(), dateUntil,"until date is ok");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//div[contains(text(),'The SLA Object is now readonly, because the Validity Period has expired')]"),"read only ok");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//table[not (contains(@class,'disabled'))]//button[contains(text(),'Copy')]"),"copy button is present");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[not (contains(@class,'hide'))]//button[contains(text(),'Cancel')]"),"cancel button is present");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//td[(contains(@class,'hide'))]//button[contains(text(),'Cancel')]"),"cancel button is not hiden");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[not(contains(@class,'hide'))]//button[text()='Save']"),"save button is present");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//td[(contains(@class,'hide'))]//button[text()='Save']"),"save button is not hiden");

		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[not (contains(@class,'hide'))]//button[contains(text(),'Save and Close')]"),"save close button is present");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//td[ (contains(@class,'hide'))]//button[contains(text(),'Save and Close')]"),"save close button is not hiden");
		
		setCode(code+"ed");
		
		waitForExtJSAjaxComplete(20);
		
		setReference(reference+"ed");
		
		waitForExtJSAjaxComplete(20);
	
		setCustomer(customer2);
		
		waitForExtJSAjaxComplete(20);
		
		setDefaultTimeTable(defaultTimeTable);
		
		waitForExtJSAjaxComplete(20);
		
		setValidityFrom(dateFrom2);
		
		waitForExtJSAjaxComplete(20);
		
		setValidityUntil(dateUntil2);
		
		waitForExtJSAjaxComplete(20);
		
		setDefaultTimeZone(defaultTimeZone2);
		
		waitForExtJSAjaxComplete(20);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getClassObject(), classObject,"class object after editing is ok");
		
		softAssert.assertEquals(getCode(), code+"ed","code is ok after editing");
		
		softAssert.assertEquals(getReference(), reference+"ed","reference is ok after editing");
		
		softAssert.assertEquals(getCustomer(), customer2,"customer is ok after editing");
		
		softAssert.assertEquals(getDefaultTimeTable(), defaultTimeTable,"default time table is ok after editing");
		
		softAssert.assertEquals(getDefaultTimeZone(), defaultTimeZone2,"default time zone is ok after editing");
		
		softAssert.assertEquals(getValidityFrom(), dateFrom2,"from date is ok after editing");
		
		softAssert.assertEquals(getValidityUntil(), dateUntil2,"until date is ok after editing");
		
		clickMetricsTab();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//table[not (contains(@class,'disabled'))]//button[contains(text(),'Copy')]"),"copy button is present");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[not (contains(@class,'hide'))]//button[contains(text(),'Cancel')]"),"cancel button is present");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//td[(contains(@class,'hide'))]//button[contains(text(),'Cancel')]"),"cancel button is not hiden");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[not(contains(@class,'hide'))]//button[text()='Save']"),"save button is present");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//td[(contains(@class,'hide'))]//button[text()='Save']"),"save button is not hiden");

		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[not (contains(@class,'hide'))]//button[contains(text(),'Save and Close')]"),"save close button is present");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//td[ (contains(@class,'hide'))]//button[contains(text(),'Save and Close')]"),"save close button is not hiden");

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "METRIC0|"+metricName+"|"+startStatus+"|"+endStatus+"|"+highValues, "@id", "MetricsTab"),"metric 0 is ok before edit");
		
			
			setMetricNew(metric1, metricName+"ed", startStatus, endStatus, highValues, excludeSuspended);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "METRIC"+metric1);
		
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "All|[none]|24.00|48.00|"+defaultTimeTable+"|"+defaultTimeZone2, "@id", "MetricsTab"),"second metric rule is created");
		
			
			setMetricNew(metric, metricName+"ed2", startStatus2, endStatus2, highValues2, excludeSuspended2);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "METRIC"+metric);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, "METRIC0|"+metricName+"ed2"+"|"+startStatus2+"|"+endStatus2+"|"+highValues2, "@id", "MetricsTab"),"metric 0 is edited");
		

			
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, "All|[none]|24.00|48.00|"+defaultTimeTable+"|"+defaultTimeZone, "@id", "MetricsTab"),"metric rule is edited after metric edit");
		

			setTargetRuleNative("1", "2", "5");
			waitForExtJSAjaxComplete(5);
		
			saveCloseTargetRules();
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "METRIC"+metric);	
		
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, "All|[none]|2.00|5.00|"+defaultTimeTable+"|"+defaultTimeZone, "@id", "MetricsTab"),"metric rule is edited");
		
		
		closeXWindow();
		
		WebElement element2 = Grid.checkRowInGriByTextValueAndClick(driver, reference+"ed");
		
		waitForExtJSAjaxComplete(20);
		
		doubleClick(element2);
		
		clickAuditingTab();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//table[not (contains(@class,'disabled'))]//button[contains(text(),'Copy')]"),"copy button is present");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[not (contains(@class,'hide'))]//button[contains(text(),'Cancel')]"),"cancel button is present");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//td[(contains(@class,'hide'))]//button[contains(text(),'Cancel')]"),"cancel button is not hiden");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[not(contains(@class,'hide'))]//button[text()='Save']"),"save button is present");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//td[(contains(@class,'hide'))]//button[text()='Save']"),"save button is not hiden");

		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[not (contains(@class,'hide'))]//button[contains(text(),'Save and Close')]"),"save close button is present");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//td[ (contains(@class,'hide'))]//button[contains(text(),'Save and Close')]"),"save close button is not hiden");
		
		String auditingTextEd = McsElement.getElementByXpath(driver,xpath).getText();
		
		softAssert.assertFalse(auditingTextEd.equals(auditingText),"auditing text changed after editing: "+ auditingTextEd+" VS " +auditingText);

		closeXWindow();
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference+"ed");
		
		clickDeleteButton();
		
		clickOnDialogButton("No");
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference+"ed");
		
		clickDeleteButton();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'deleted')]"),"confirm message ok");
		
		clickOnDialogButton("OK");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, reference+"ed"));
		
		softAssert.assertAll();
		
		Reporter.log("sla object was created and deleted", true);
		
	}

	 
	 
	 @Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testCRUDSLAWorkorder() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create SLA Object for Workorder with Metric and delete : C74586(Testrail) C74584 " + " </span><br>",
				true);

		Reporter.log("Create SLA Object for Workorder with Metric  "  + " <br>",
				true);
		
		String classObject = "Work Order";
		
		/*String dateFrom = "03-08-2024";
	
		String dateUntil = "03-09-2024";
				
		String dateFrom2 = "05-08-2024";
		
		String dateUntil2 = "06-09-2024";*/
		
		int random = (int)(Math.random()*10000)+18;
		
		String dateFrom = this.getFutureDate(random);
		//String dateFrom = "03-08-2026";
	
		//String dateUntil = "03-09-2026";
		String dateUntil = this.getFutureDate(random+30);
		
		String dateFrom2 = this.getFutureDate(random+2);
		
		String dateUntil2 =  this.getFutureDate(random+33);
		
		String defaultTimeZone2 = "(GMT) Casablanca";
		
		String code = "teauto code" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String reference = "teauto reference" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String supplier = "2preCompName";
		
		String metric = "0";
		
		String metricName = "mtrname" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String startStatus = "1preWrkOrdStsName";
		
		String endStatus = "2preWrkOrdStsName";
		
		String defaultTimeTable = "1preTimeTableSLARef";
		
		String defaultTimeZone = "(GMT) Monrovia, Reykjavik";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCRUDSLACall");
		
		
		
		//expandAdministration();
		
		clickAdministration();

		waitForExtJSAjaxComplete(20);

		expandModuleSettings();
		
		waitForExtJSAjaxComplete(20);

		expandHelpDesk();

		waitForExtJSAjaxComplete(20);

		click(XPATH_SLA);
		
		waitForExtJSAjaxComplete(20);
				
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		setClassObject(classObject);
		
		setCode(code);
		
		setReference(reference);
		
		setValidityUntil(dateUntil);
		
		setValidityFrom(dateFrom);
		
		setSupplier(supplier);
		
		setDefaultTimeTable(defaultTimeTable);
		
		setDefaultTimeZone(defaultTimeZone);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		clickMetricsTab();
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//div[contains(@class,'x-resizable')]//div[contains(@class,'x-grid3-check-col-on ')]"),"active metric is absent");
				
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//div[contains(@class,'x-resizable')]//div[contains(text(),'Valid')]"),"rule metric is absent");
		
			
			setMetricNew(metric, metricName, startStatus, endStatus, "Bad", false);
		
		
		Grid.checkRowInGriByTextValueAndClick(driver, "METRIC"+metric);
		
		waitForExtJSAjaxComplete(20);
		
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, "All|[none]|24.00|48.00|"+defaultTimeTable+"|"+defaultTimeZone, "@id", "MetricsTab"),"metric rule is created");
		
		
		clickAuditingTab();
		
		waitForExtJSAjaxComplete(20);
		
		String xpath = "//div[contains(@class,'x-panel-body-noborder') "
				+ "and contains(text(),'Created on')"
				+ " and (contains(text(),"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+") or contains(text(),"+(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+1)+") or contains(text(),"+(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1)+") )"
						+ " and  contains(.,'Last modified on') "
						+ "and (contains(text(),"+Calendar.getInstance().get(Calendar.YEAR)+") or contains(text(),"+(Calendar.getInstance().get(Calendar.YEAR)-1)+") or contains(text(),"+(Calendar.getInstance().get(Calendar.YEAR)+1)+") ) "
								+ "and (contains(text(),"+(Calendar.getInstance().get(Calendar.MONTH)+1)+") or contains(text(),"+(Calendar.getInstance().get(Calendar.MONTH)+2)+") or contains(text(),"+(Calendar.getInstance().get(Calendar.MONTH)+0)+")   ) ]";
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,xpath ),"text on auditing tab is ok: "+xpath);
		
		closeXWindow();
		
		waitForExtJSAjaxComplete(20);
		
		
//c30676 create two sla with same valid period - workorder		
		
		clickAddButton();
				
		waitForExtJSAjaxComplete(20);
				
		clickGeneralTab();
			
		setClassObject(classObject);
			
		setCode(code);
				
		setReference(reference+"same");
		
		setValidityUntil(dateUntil2);
				
		setValidityFrom(dateFrom2);
		
		setSupplier(supplier);
				
		setDefaultTimeTable(defaultTimeTable);
				
		setDefaultTimeZone(defaultTimeZone2);
				
		saveClose();
				
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save SLA') and contains(.,'Code already')]"),"error unique code message ok");
				
		clickOnDialogButton("OK");
				
		waitForExtJSAjaxComplete(20);
				
		setCode(code+"S");
				
		//28916 - same code and reference		
				
		setReference(reference);
				
		saveClose();
				
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save SLA') and contains(.,'Reference already')]"),"error unique reference message ok");
				
		clickOnDialogButton("OK");
				
		waitForExtJSAjaxComplete(20);
				
		setReference(reference+"S");
				
		saveClose();
				
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save SLA') and contains(.,'overlaps')]"),"error overlap message ok");
				
		clickOnDialogButton("OK");
				
		waitForExtJSAjaxComplete(20);
				
		closeXWindow();
				
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		clickDeleteButton();
		
		clickOnDialogButton("No");
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		clickDeleteButton();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'deleted')]"),"confirm message ok");
		
		clickOnDialogButton("OK");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, reference));
		
		softAssert.assertAll();
		
		Reporter.log("sla object was created and deleted", true);
		
	}


	 
	 @Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testCRUDSLA2() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C74596 Create SLA Metric with Low Target bigger than High Target " + " </span><br>",
				true);

		Reporter.log("C74596 Create SLA Metric with Low Target bigger than High Target   "  + " <br>",
				true);
		
		int random = (int)(Math.random()*10)+18;
				
		String dateFrom = "11-06-20"+random;
		//String dateFrom = "03-08-2026";
	
		//String dateUntil = "03-09-2026";
		String dateUntil = "11-07-20"+random;
		
		
		String classObject = "Work Order";
	
		String code = "teauto code" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String reference = "teauto reference" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String supplier = "MCS";
		
		String metric = "0";
		
		String rule = "1";
		
		String lowTarget = "2";
		
		String highTarget = "1";
		
		String metricName = "mtrname" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String startStatus = "1preWrkOrdStsName";
		
		String endStatus = "2preWrkOrdStsName";
		
		String defaultTimeTable = "1preTimeTableSLARef";
		
		String defaultTimeZone = "(GMT) Monrovia, Reykjavik";
		
		String warningMsg= "The High Target cannot be lower than the Low Target.";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCRUDSLA2");
		
		//expandAdministration();
		
		clickAdministration();

		waitForExtJSAjaxComplete(20);

		expandModuleSettings();
		
		waitForExtJSAjaxComplete(20);

		expandHelpDesk();

		waitForExtJSAjaxComplete(20);

		click(XPATH_SLA);
		
		waitForExtJSAjaxComplete(20);
				
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		setClassObject(classObject);
		
		setCode(code);
		
		setReference(reference);
		
		setValidityUntil(dateUntil);
		
		setValidityFrom(dateFrom);
		
		setSupplier(supplier);
		
		setDefaultTimeTable(defaultTimeTable);
		
		setDefaultTimeZone(defaultTimeZone);
		
		save();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickMetricsTab();
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//div[contains(@class,'x-resizable')]//div[contains(@class,'x-grid3-check-col-on ')]"),"active metric is absent");
				
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//div[contains(@class,'x-resizable')]//div[contains(text(),'Valid')]"),"rule metric is absent");
		
		setMetricNew(metric, metricName, startStatus, endStatus, "Bad", false);
		
		waitForExtJSAjaxComplete(20);
	
		save();
	
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, "METRIC"+metric);
		
		waitForExtJSAjaxComplete(20);
			
		setTargetRuleNative(rule, lowTarget, highTarget,defaultTimeTable,defaultTimeZone);
		
		waitForExtJSAjaxComplete(20);
			
		saveTargetRules();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getTargetRulesWindowErrorMsg(),warningMsg,"error message is displayed for target rule");
		
		/*softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'Could not save the Target rule')"
				+ " and contains(.,'cannot be lower')]"),"error unique code message ok");*/
				
		//clickOnDialogButton("OK");
				
	

		setTargetRuleNative("1", "1", "2");
		
		waitForExtJSAjaxComplete(20);
		
		saveCloseTargetRules();
		
		waitForExtJSAjaxComplete(20);
		
		this.saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		waitForExtJSAjaxComplete(10);
		
		clickDeleteButton();
		
		waitForExtJSAjaxComplete(5);
		
		clickOnDialogButton("No");
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		waitForExtJSAjaxComplete(10);
		
		clickDeleteButton();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("OK");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, reference));
		
		softAssert.assertAll();
		
		Reporter.log("low target < high target", true);
		
	}
	 
	 
	 @Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testCRUDSLA1() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create / delete/edit SLA Object for Until time in the past : C74588(Testrail); C74584; C74590 ; C74581" + " </span><br>",
				true);

		Reporter.log("Create SLA Object for Until time in the past  "  + " <br>",
				true);
		
		
		String dateFrom = "11-11-2010";
		
		String dateUntil = "23-12-2011";
		
		
		String dateFromWrong = "11-10-2031";
		
		String dateUntilWrong = "10-10-2031";
		
		String classObject = "Call";
		
		String code = "teauto code" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String reference = "teauto reference" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String customer = "My Company";
		
		
		String defaultTimeTable = "1preTimeTableSLARef";
		
		String defaultTimeZone = "(GMT) Monrovia, Reykjavik";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCRUDSLA1");
		
		//expandAdministration();
		
		clickAdministration();

		waitForExtJSAjaxComplete(20);

		expandModuleSettings();
		
		waitForExtJSAjaxComplete(20);

		expandHelpDesk();

		waitForExtJSAjaxComplete(20);
		
		click(XPATH_SLA);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickGeneralTab();
		
		setClassObject(classObject);
		
		setCode(code);
		
		setReference(reference);
		
		setValidityUntil(dateUntilWrong);
		
		setValidityFrom(dateFromWrong);
		
		setCustomer(customer);
		
		setDefaultTimeTable(defaultTimeTable);
		
		setDefaultTimeZone(defaultTimeZone);
		
		saveClose();
	
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'not save') and contains(.,'Until Date must be after the Valid From')]"),"date until > from");	
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		setValidityUntil(dateUntil);
		
		setValidityFrom(dateFrom);
		
		setCustomer(customer);
		
		saveClose();
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'read only') and contains(text(),'"+dateUntil+"')]"),"confirm message ok");
		
		clickOnDialogButton("Yes");
		 
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		WebElement element = Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		waitForExtJSAjaxComplete(20);
		
		doubleClick(element);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getClassObject(), classObject,"class object is ok");
		
		softAssert.assertEquals(getCode(), code,"code is ok");
		
		softAssert.assertEquals(getReference(), reference,"reference is ok");
		
		softAssert.assertEquals(getCustomer(), customer,"customer is ok");
		
		softAssert.assertEquals(getDefaultTimeTable(), defaultTimeTable,"default time table is ok");
		
		softAssert.assertEquals(getDefaultTimeZone(), defaultTimeZone,"default time zone is ok");
		
		softAssert.assertEquals(getValidityFrom(), dateFrom,"from date is ok");
		
		softAssert.assertEquals(getValidityUntil(), dateUntil,"until date is ok");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//div[contains(text(),'The SLA Object is now readonly, because the Validity Period has expired')]"),"read only ok");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//table[not (contains(@class,'disabled'))]//button[contains(text(),'Copy')]"),"copy button is present");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[contains(@class,'hide')]//button[contains(text(),'Cancel')]"),"cancel button is not present");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[contains(@class,'hide')]//button[text()='Save']"),"save button is not present");

		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[contains(@class,'hide')]//button[contains(text(),'Save and Close')]"),"save close button is not present");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//label[contains(text(),'Code')]/..//input[contains(@class,'readonly')]"),"code is readonly");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//label[contains(text(),'Reference')]/..//input[contains(@class,'readonly')]"),"reference is readonly");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//label[contains(text(),'Object Class')]/..//input[contains(@class,'readonly')]"),"object class is readonly");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//div[contains(text(),'From')]/..//input[contains(@class,'readonly')]"),"from is readonly");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"(//div[contains(@class,'x-resizable')]//div[contains(text(),'Until')]/..//input[contains(@class,'readonly')])[last()]"),"until is readonly");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"(//div[contains(@class,'x-resizable')]//label[contains(text(),'Customer')]/..//input)[last()]"),"customer is readonly");	
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"(//div[contains(@class,'x-resizable')]//label[contains(text(),'Default Time Table')]/..//input)[last()]"),"time table is readonly");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"(//div[contains(@class,'x-resizable')]//label[contains(text(),'Default Time Zone')]/..//input)[last()]"),"time zone is readonly");
		
		clickMetricsTab();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//table[not (contains(@class,'disabled'))]//button[contains(text(),'Copy')]"),"copy button is present - metrics");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//table[contains(@class,'x-btn-noicon x-item-disabled')]//button[contains(text(),'Cancel')]"),"cancel button is not present - metrics");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[contains(@class,'hide')]//button[text()='Save']"),"save button is not present - metrics");

		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[contains(@class,'hide')]//button[contains(text(),'Save and Close')]"),"save close button is not present - metrics");

		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//div[contains(@class,'x-grid3-check-col') and  (contains(@class,'disabled'))]"),"metric check boxes are disabled");
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver,"//div[contains(@class,'x-resizable')]//div[contains(@class,'x-grid3-check-col') and not (contains(@class,'disabled'))]"),"all metric check boxes are disabled");
		
		clickAuditingTab();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//table[not (contains(@class,'disabled'))]//button[contains(text(),'Copy')]"),"copy button is present");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[contains(@class,'hide')]//button[contains(text(),'Cancel')]"),"cancel button is not present - auditing");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[contains(@class,'hide')]//button[text()='Save']"),"save button is not present  - auditing");

		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-resizable')]//td[contains(@class,'hide')]//button[contains(text(),'Save and Close')]"),"save close button is not present - auditing");

		closeXWindow();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		clickDeleteButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("No");
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		clickDeleteButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver,"//div[contains(@class,'x-window-dlg')]//span[contains(text(),'deleted')]"),"confirm message ok");
		
		clickOnDialogButton("OK");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, reference));
		
		softAssert.assertAll();
		
		Reporter.log("sla object in the past was created and deleted", true);
		
	}
	 
	 
}
