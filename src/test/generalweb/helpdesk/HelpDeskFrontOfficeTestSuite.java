package test.generalweb.helpdesk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.framework.webelement.McsElement;
import test.generalweb.calltemplates.CallTemplatesPageObject;
import test.generalweb.purchaserequisitions.*;



public class HelpDeskFrontOfficeTestSuite extends
		HelpDeskFrontOfficePageObject {

	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testBookNewCall() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Book new call: C74001; C74009 </span><br>", true);

		Reporter.log("User book new call <br>", true);
		
		
		String subject =  "test auto sub" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority =  "1prePrior";
		String url = "http://test.com";
		String urlEd = "http://tested.com";
		String file = "test.csv";
		String comment =  "test auto comment" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String urlDescription =  "url descr" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String urlDescriptionEd =  "url descrEd" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String urlRemark =  "url remark" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String urlRemarkEd =  "url remarkEd" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String fileDescription =  "file descr" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String fileDescriptionEd =  "file descrEd" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String fileRemark =  "file remark" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String fileRemarkEd =  "file remarkEd" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String type = "labelen";
		
		String fileEd = "te2.csv";
		
		String typeEd = "1prelaben";
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testBookNewCall");

		waitForExtJSAjaxComplete(20);
		
		HelpDeskAccessTestSuit objHDFO = new HelpDeskAccessTestSuit();
		
		objHDFO.navigateToMainPage("AQA_SELENIUM","qwerty");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWCALL);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickCallTemplate("Other Subject");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setSubject(subject);
		
		setPriority(priority);
		
		setNature("Default Nature");
		
		waitForExtJSAjaxComplete(20);
		
		setCostCenter("myMCS Default Cost Center");
		
		waitForExtJSAjaxComplete(20);
		
		setGlAccount("myMCS Default GL Account");
		
		waitForExtJSAjaxComplete(20);
		
		setComment(comment);
		
		//////////////
		setUrl(url, urlDescription, urlRemark, type);
		
		setFile(file, fileDescription, fileRemark, type);
		
		waitForExtJSAjaxComplete(20);
		
		editUrl(urlEd, urlDescription, urlDescriptionEd, urlRemarkEd, type);
		
		editFile(file, fileDescription, fileDescriptionEd, fileRemarkEd, type);
		
		waitForExtJSAjaxComplete(20);
		
		deleteUrl(urlDescriptionEd); ///removed Ed
		
		deleteUrl(fileDescriptionEd);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, urlDescriptionEd),"url can be deleted");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, urlDescription),"url can be deleted");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, fileDescriptionEd),"file can be deleted");
		
		///////////////
		clickBookCall();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(!driver.findElements(By.xpath("//button[contains(text(),'Show Call')]")).isEmpty(),"Show Call button is present");
		
		///////////////
		
		clickMyCallsTab();
		
		waitForExtJSAjaxComplete(20);
		
//c15738
		Grid.checkRowInGriByTextValue(driver, "2preCallRef");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'x-panel hdwo-details')]//div[contains(text(), '2preCallRef')]"),"low detail panel");
		
		Grid.checkRowInGriByTextValue(driver, "1preCallSub");
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		verifySelectedCallsInTab("5", "2preCallRef");

		verifySelectedCallsInTab("1", "1preCallSub");
		
		waitForExtJSAjaxComplete(20);
		
		HelpDeskBackOfficePageObject hdFO = new HelpDeskBackOfficePageObject();
		hdFO.openCallDetailsDialog("1preCallSub");
		
		waitForExtJSAjaxComplete(20);
		
		verifyCallDetailsHeader("1preCallSub");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class, 'x-window x-resizable-pinned')]//span[contains(text(), 'Description')]"),"xwindow description");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class, 'x-window x-resizable-pinned')]//span[contains(text(), 'General')]"),"xwindow general");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class, 'x-window x-resizable-pinned')]//span[contains(text(), 'Maintenance Objects')]"),"xwindow maintenance objects");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class, 'x-window x-resizable-pinned')]//span[contains(text(), 'Tracking')]"),"xwindow tracking");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class, 'x-window x-resizable-pinned')]//span[contains(text(), 'Attachments')]"),"xwindow attachments");
		
		McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-window x-resizable-pinned')]//span[contains(text(), 'Description')]").click();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-window x-resizable-pinned')]//div[contains(@class,'x-tab-panel-bwrap')]//textarea").getAttribute("value"),"add comm","decsription is present");
		
		McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-window x-resizable-pinned')]//span[contains(text(), 'General')]").click();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-window x-resizable-pinned')]//label[contains(text(),'Caller Name')]/..//label[contains(text(),'SELENIUM AQA')]").getText(),"SELENIUM AQA","caller name is present");
		
		//softAssert.assertEquals(McsElement.getElementByXpath(driver, "(//div[contains(@class, 'x-window x-resizable-pinned')]//input[@name='caller']/../input)[last()]").getAttribute("value"),"SELENIUM AQA","caller name is present");		
		
		closeXWindow();
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, subject, "@class", "helpdesk"),"Call is present in my calls");
		
		Grid.checkRowInGriByTextValueAndClick(driver, subject);
		
		waitForExtJSAjaxComplete(20);
		
		clickDescriptionTab();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getDescription(),comment,"decsription is present");
		
		
		try {driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
			//subject = subject;
			setDescription(subject);
			softAssert.assertTrue(true,"description is editable");
		}
		catch(InvalidElementStateException e){
			Reporter.log("InvalidElementStateException: " + e.getMessage(), true);
			softAssert.assertTrue(true,"description is not editable");
		}
		finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
		}

		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertEquals(getDescription(),comment,"decsription is not edited");

		// c15433, c15435

		//clickDocumentsTab();
		//Documents tab has been renamed to Attachments
		clickAttachmentsTab();

		waitForExtJSAjaxComplete(20);

		setUrl(url, urlDescription, urlRemark, type);

		setFile(file, fileDescription, fileRemark, type);

		editUrl(urlEd, urlDescription, urlDescriptionEd, urlRemarkEd, typeEd);

		editFile(fileEd, fileDescription, fileDescriptionEd, fileRemarkEd, typeEd);
		
		try {
			
			waitForExtJSAjaxComplete(25);
			
			McsElement.getElementByXpath(driver, "//button[contains(text(), 'OK')]").click();
			
			waitForExtJSAjaxComplete(25);
			
			McsElement.getElementByXpath(driver, ADD_URL_SAVE_XPATH).click();
			
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, file+"|"+fileDescriptionEd+"|"+typeEd+"|"+fileRemarkEd, "@class","x-grid3"),"file can be edited");
			
			waitForExtJSAjaxComplete(25);
			
			Reporter.log("Fail on edit file.", true);
			
		} catch(Exception e) {
			
			softAssert.assertTrue(Grid.isRowInGridPresent(driver, fileEd+"|"+fileDescriptionEd+"|"+typeEd+"|"+fileRemarkEd, "@class","x-grid3"),"file can be edited");
		}
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, urlDescriptionEd+"|"+typeEd+"|"+urlRemarkEd, "@class","x-grid3"),"url can be edited");
		
		//softAssert.assertTrue(Grid.isRowInGridPresent(driver, fileEd+"|"+fileDescriptionEd+"|"+typeEd+"|"+fileRemarkEd, "@class","x-grid3"),"file can be edited");
		
		softAssert.assertTrue(McsElement.isElementPresent(driver, "//div[contains(@class,'grid3')]//a[contains(text(),'"+urlEd+"')]"),"url edited");
		
		deleteUrl(urlDescriptionEd);
		
		deleteUrl(fileDescriptionEd);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, urlDescriptionEd),"url can be deleted");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, urlDescription),"url can be deleted");
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, fileDescriptionEd),"file can be deleted");
		
		clickMyTeamsCallsTab();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, subject, "@class", "helpdesk"),"Call is present in my calls");
		
		Grid.checkRowInGriByTextValueAndClick(driver, subject);
		
		waitForExtJSAjaxComplete(20);
		
		clickDescriptionTab();
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertEquals(getDescription(),comment,"decsription is present");
		

		try {driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
			//subject = subject;	
			setDescription(subject);
			softAssert.assertTrue(true,"description is editable");
		}
		catch(InvalidElementStateException e){
			Reporter.log("InvalidElementStateException: " + e.getMessage(), true);
			softAssert.assertTrue(true,"description is not editable");
		}
		finally {driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
		}
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertEquals(getDescription(),comment,"decsription is not edited");
	
		softAssert.assertAll();
		
		Reporter.log("Book call operation was succesfull", true);
	}

	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testNatureAlias() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: check nature aliases: C74011 </span><br>", true);

		Reporter.log("Test: check nature aliases: C74011 <br>", true);
		
		
		String subject =  "test auto sub nat" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority =  "1prePrior";
		
		String invisibleNature = "3preNatureRefInv";
		
		String visibleNatureParent = "Default Nature";

		String visibleNatureChild = "1preNatureRef";
		
		String visibleNatureParentAlias = "4preNatureRefAlias";
		
		String visibleNatureChildAlias = "aliasforweb";
		
		String visibleNatureParentAlias2 = "2aliasforwebparent";
		
		String visibleNatureChildAlias2 = "2aliasforwebchild";
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testNatureAlias");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWCALL);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickCallTemplate("Other Subject");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setSubject(subject);
		
		setPriority(priority);
		
		clickLookup("@class","x-panel", "NEW_NATURE","Select a Nature");
		
		waitForExtJSAjaxComplete(25);
		
		clickTreeTab();
		
		softAssert.assertTrue(McsElement.isElementAbsent(driver, "//div[contains(@class,'x-resizable-pinned')]//span[text()='"+invisibleNature+"']"),"invisible nature is not shown");
		
		expandNode("div","@class","x-resizable-pinned", visibleNatureParent);
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-resizable-pinned')]//span[text()='"+visibleNatureChild+"']").click();
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-resizable-pinned')]//button[contains(text(),'OK')]").click();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(McsElement.getElementByXpath(driver, "(//input[@name='NEW_NATURE']/..//input)[last()]").getAttribute("value"),visibleNatureChild,"visible nature is shown");		
		
		clickLookup("@class","x-panel", "NEW_NATURE","Select a Nature");
		
		waitForExtJSAjaxComplete(25);
		
		expandNode("div","@class","x-resizable-pinned", visibleNatureParentAlias);
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-resizable-pinned')]//span[text()='"+visibleNatureChildAlias+"']").click();
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-resizable-pinned')]//button[contains(text(),'OK')]").click();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(McsElement.getElementByXpath(driver, "(//input[@name='NEW_NATURE']/..//input)[last()]").getAttribute("value"),visibleNatureChildAlias,"visible nature alias is shown");
		
		
		clickLookup("@class","x-panel", "NEW_NATURE","Select a Nature");
		
		waitForExtJSAjaxComplete(25);
		
		expandNode("div","@class","x-resizable-pinned", visibleNatureParentAlias2);
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-resizable-pinned')]//span[text()='"+visibleNatureChildAlias2+"']").click();
		
		McsElement.getElementByXpath(driver, "//div[contains(@class,'x-resizable-pinned')]//button[contains(text(),'OK')]").click();
		
		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(McsElement.getElementByXpath(driver, "(//input[@name='NEW_NATURE']/..//input)[last()]").getAttribute("value"),visibleNatureChildAlias2,"visible nature alias 2 is shown");
		
		setSubject(subject);
		
		setPriority(priority);
		
		waitForExtJSAjaxComplete(25);
		
		clickBookCall();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(!driver.findElements(By.xpath("//button[contains(text(),'Show Call')]")).isEmpty(),"Show Call button is present");
		
		clickMyCallsTab();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridPresent(driver, visibleNatureChildAlias2+"|"+subject, "@class", "helpdesk"),"Call is present in my calls");
		
		
		softAssert.assertAll();
		
		Reporter.log("nature alias was succesfully checked", true);
	}
	
	

	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testCallAction() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: create/edit call actions back office: C74011; C74020; C74037 </span><br>", true);

		Reporter.log("User create/edit call actions back office <br>", true);
		

		String callRef = "2preCallRef";
		
		String possibleAction = "Finish";
		
		String possibleAction2 = "Open";
		
		String actionNote = "action note" + getCurrentDate().substring(getCurrentDate().length()-6);
		
		String assigned = "SELENIUM";
		
		String contact = "1preContactLast";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testCallAction");
		
		waitForExtJSAjaxComplete(20);

		HelpDeskAccessTestSuit objHDFO = new HelpDeskAccessTestSuit();
		
		objHDFO.navigateToMainPage("AQA_SELENIUM","qwerty");
				
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_MYCALLS);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		collapseDetailsPanel();

		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, callRef);
		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickTrackingTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickTrackingHistoryTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickEditLastAction();
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("OK");
		
		clickAddAction();
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions(possibleAction);
		
		setActionNote(actionNote);
		
		setActionAssigned(assigned);
		
		waitForExtJSAjaxComplete(20);
		
		setActionContact(contact);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(getActionType().contains(possibleAction),"action type was selected");
		
		saveCloseAction();
		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isActionPresent(actionNote),"action is added");
		
		clickEditLastAction();
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions(possibleAction2);
		
		setActionNote(actionNote+"ed");
		
		softAssert.assertTrue(getActionType().contains(possibleAction),"action type is disabled after edit");
		
		saveCloseAction();
		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(isActionPresent(actionNote+"ed"),"action note is edited");
				
		softAssert.assertAll();
		
		Reporter.log("Action was succesfully edited and deleted", true);

	}

	/**
	 * Testcase ID	: 	C29395,C74059,C74063,C74064,C74065,C74061
	 * 				:   C15396,C73980
	 * Author		:	ssa
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testCallTemplatesAndGroups() throws Exception{
		
				
		 
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:C29403:Remove template group </span><br>"
				+ "Test:C29395:Copy a Call template </span><br>"			
				+ "Test:C29399:Delete Call template </span><br>"			
				+ "Test:C29404:Create an inactive Call template </span><br>" 			
				+ "Test:C29405:Create an active Call template </span><br>"
				+ "Test:C29401:Add Template groups </span><br>", true);

		Reporter.log("User create and delete Template groups <br>", true);
		
		String callTemplateActiveReference = "my auto act"+ getCurrentDate().substring(getCurrentDate().length()-6);
		String callTemplateInctiveReference = "my auto inact"+ getCurrentDate().substring(getCurrentDate().length()-6);
		String callTemplateInctiveReference1 = "AUTOinact1"+ getCurrentDate().substring(getCurrentDate().length()-6);
		String callTemplateGroupReference = "my auto gr"+ getCurrentDate().substring(getCurrentDate().length()-6);
		String callCopyTemplateReference = "Auto Copy"+ getCurrentDate().substring(getCurrentDate().length()-6);
		SoftAssert softAssert = new SoftAssert();
		
		CallTemplatesPageObject calltemplatesPageObject = new CallTemplatesPageObject();
		
		calltemplatesPageObject.clickAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.expandModuleSettings();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.clickCallTemplate();
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.addTemplateGroup(callTemplateGroupReference);
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.clickDeleteGroupTemplate();
		
		waitForExtJSAjaxComplete(20);
				
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			softAssert.assertTrue(
					driver.findElements(
							By.xpath("//span[contains(@class,'x-panel') and contains(text(),'Templates')]/../..//span[text()='"+ callTemplateGroupReference+"']")).isEmpty(),
					"Call template group is deleted");

		} finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);
		}
		
		waitForExtJSAjaxComplete(20);
		
		calltemplatesPageObject.addActiveTemplate(callTemplateActiveReference);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(calltemplatesPageObject.getReference().equals(callTemplateActiveReference),"call template reference before edit is wrong");
		
		softAssert.assertEquals(calltemplatesPageObject.getActivState(), true, "active state before edit is wrong");
		
		
		calltemplatesPageObject.clickDeleteTemplate();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			softAssert.assertTrue(
					driver.findElements(
							By.xpath("//span[contains(@class,'x-panel') and contains(text(),'Templates')]/../..//span[text()='"+ callTemplateActiveReference+"']")).isEmpty(),
					"Active Call template is deleted after delete operation");

		} finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);
		}
		
		
		calltemplatesPageObject.addInactiveTemplate(callTemplateInctiveReference);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(calltemplatesPageObject.getReference().equals(callTemplateInctiveReference),"Inactive template reference is added");
		
		calltemplatesPageObject.clickDeleteTemplate();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			softAssert.assertTrue(
					driver.findElements(
							By.xpath("//span[contains(@class,'x-panel') and contains(text(),'Templates')]/../..//span[text()='"+ callTemplateActiveReference+"']")).isEmpty(),
					"Inactive Call template is deleted after delete operation");

		} finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);
		}
		
		calltemplatesPageObject.addInactiveTemplate(callTemplateInctiveReference1);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(calltemplatesPageObject.getReference().equals(callTemplateInctiveReference1),"Inactive template reference for copy is added");
		
		calltemplatesPageObject.clickCopyTemplate();
		
		calltemplatesPageObject.setCopyReference(callCopyTemplateReference);
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(calltemplatesPageObject.getReference().equals(callCopyTemplateReference),"Template is copied with specified reference");
		
		calltemplatesPageObject.clickDeleteTemplate();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			softAssert.assertTrue(
					driver.findElements(
							By.xpath("//span[contains(@class,'x-panel') and contains(text(),'Templates')]/../..//span[text()='"+ callTemplateActiveReference+"']")).isEmpty(),
					"Copied Call template is deleted after delete operation");

		} finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);
		}
		
		
		McsElement.getElementByPartAttributeValueAndParentElement(driver, "div", "@id", getXPanelId("Call Templates"),"span", "text()",
				callTemplateInctiveReference1, true, true).click();
		
		
		
		calltemplatesPageObject.clickDeleteTemplate();
		
		clickOnDialogButton("Yes");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			softAssert.assertTrue(
					driver.findElements(
							By.xpath("//span[contains(@class,'x-panel') and contains(text(),'Templates')]/../..//span[text()='"+ callTemplateInctiveReference1+"']")).isEmpty(),
					"Template used for Copying is deleted after delete operation");

		} finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(configuration.getImplicitWait(),
							TimeUnit.SECONDS);
		}
		
		softAssert.assertAll();
		
		Reporter.log("Add and delete templete group, Active and inactive call templetes, Copy a call templete are verified successfully", true);
	}
	
	
	/**
	 * Testcase ID	: 	C15485
	 * Author		:	ssa
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testErrorMsgWhileSendingNotification() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C15485:An Error is displayed while user is sending a notification with no recipients</span><br>", true);
		
		
		String subject =  "test auto sub" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority =  "1prePrior";
		String nature="Default Nature";
		String warngMsg="Recipients are not selected";
		String emailTempName="1aqaEmailTemplate";
		String smsTempName="1aqaSMSTemplate";
		
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testErrorMsgWhileSendingNotification");
		
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWCALL);
		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickCallTemplate("Other Subject");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setSubject(subject);
		
		setPriority(priority);
		
		setNature(nature);

		waitForExtJSAjaxComplete(20);

		clickBookCall();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(!driver.findElements(By.xpath("//button[contains(text(),'Show Call')]")).isEmpty(),"Show Call button is present");

		waitForExtJSAjaxComplete(20);

		clickMyCallsTab();
		waitForMaskDisappear();

		HelpDeskBackOfficePageObject backOffice=new HelpDeskBackOfficePageObject();

		waitForExtJSAjaxComplete(20);

		backOffice.filterGridForCall("@realid", "mogrid", subject, "Reference");
		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);
		
		collapseDetailsPanel();
		waitForExtJSAjaxComplete(5);

		openCallDetailsDialog(subject);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddActionInDetails();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions("Open");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		dragAndDropActionWindows(ADD_ACTION_WINDOW_HEADER,DETAILS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		checkSendNotification();
		
		waitForExtJSAjaxComplete(20);
		
		clickAddActionForCall();
		
		waitForExtJSAjaxComplete(20);
		
		clickMaximizeButtonForSendNotificationWindow();
		
		waitForExtJSAjaxComplete(20);
		
		selectEmailTemplateInNotification(emailTempName);
		
		waitForExtJSAjaxComplete(20);
		
		selectSMSTemplateInNotification(smsTempName);
		
		waitForExtJSAjaxComplete(20);
		
		selectLanguageForEmailTemp("English");
		
		waitForExtJSAjaxComplete(20);
		
		clickSendButton();
		
		waitForExtJSAjaxComplete(20);
		
		boolean status=verifyWarningDialogTextMessage(warngMsg);
		
		softAssert.assertTrue(status,"Error message is displayed");
		
		waitForExtJSAjaxComplete(20);
	
		clickOnDialogButton("OK");
		
		softAssert.assertAll();
		
		Reporter.log("Error message is displayed successfully while user is sending a notification with no recipients ", true);
		
	}
	
	/**
	 * Testcase ID	: 	C74041
	 * Author		:	ssa
	 */
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testSelectedOptionsInNotificationScreen() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "C15473:Last selected options are remembered on Notification Screen</span><br>", true);
		
		
		String subject =  "test auto sub" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority =  "1prePrior";
		String nature="Default Nature";
		String warngMsg="Recipients are not selected";
		String emailTempName="1aqaEmailTemplate";
		String smsTempName="1aqaSMSTemplate";
		String colName="E-mail Address";
		String emailAddress="ssa@mcs.fm";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testSelectedOptionsInNotificationScreen");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWCALL);
		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickCallTemplate("Other Subject");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setSubject(subject);
		
		setPriority(priority);
		
		setNature(nature);
		
		waitForExtJSAjaxComplete(20);
		
		clickBookCall();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(!driver.findElements(By.xpath("//button[contains(text(),'Show Call')]")).isEmpty(),"Show Call button is present");
		
		waitForExtJSAjaxComplete(20);
		
		clickMyCallsTab();
		waitForMaskDisappear();

		HelpDeskBackOfficePageObject backOffice=new HelpDeskBackOfficePageObject();
		
		waitForExtJSAjaxComplete(20);
		
		backOffice.filterGridForCall("@realid", "mogrid", subject, "Reference");
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(20);
		
		HelpDeskBackOfficePageObject objHDBO = new HelpDeskBackOfficePageObject();
		objHDBO.openCallDetailsDialog(subject);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddActionInDetails();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions("Open");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		dragAndDropActionWindows(ADD_ACTION_WINDOW_HEADER,DETAILS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		checkSendNotification();
		
		waitForExtJSAjaxComplete(20);
		
		clickAddActionForCall();
		
		waitForExtJSAjaxComplete(20);
		
		clickMaximizeButtonForSendNotificationWindow();
		
		waitForExtJSAjaxComplete(20);
		
		selectEmailTemplateInNotification(emailTempName);
		
		waitForExtJSAjaxComplete(20);
		
		selectSMSTemplateInNotification(smsTempName);
		
		waitForExtJSAjaxComplete(20);
		
		setEmailAddressInNotification("1",colName,emailAddress);
		
		waitForExtJSAjaxComplete(20);
		
		clickCheckBoxForRecipients("1","Assign To");
		
		waitForExtJSAjaxComplete(20);
		
		selectLanguageForEmailTemp("English");
		
		waitForExtJSAjaxComplete(20);
		
		selectLanguageForSMSTemp("English");
		
		waitForExtJSAjaxComplete(20);
		
			clickCheckBoxForRecipients("1","To");
		
		waitForExtJSAjaxComplete(20);
		
			clickCheckBoxForRecipients("1","Cc");
		
		waitForExtJSAjaxComplete(20);
		
		clickSendButton();
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions("Finish");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddActionForCall();
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickMaximizeButtonForSendNotificationWindow();
		
		waitForExtJSAjaxComplete(20);
		
		selectSMSTemplateInNotification(smsTempName);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("SMS messages has been sent to respective recepients and SMS notification Template is chosen.", true);
		
	}
	
	/**
	 * Testcase ID	: 	C74032, C74036,C74035
	 * Author		:	ssa
	 */
	
	@Test(enabled=true, retryAnalyzer=RetryAnalyzer.class)
	public void testMultipleActions() throws Exception {
		
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Add Action and edit last action to Call, Action History of Calls: </span><br>", true);

		String subject =  "test auto sub" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority =  "1prePrior";
		String nature="Default Nature";
		String possibleAction2= "Finish";
		String possibleAction = "Open";
		String actionNote = "action note" + getCurrentDate().substring(getCurrentDate().length()-6);
		String assigned ="SELENIUM";
		String contact = "1preContactLast";
		String contact1="Roles";
		String toolTipMsgForCall="There are no changes made to save.";
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testMultipleActions");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_NEWCALL);
		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		clickCallTemplate("Other Subject");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
		
		setSubject(subject);
		
		setPriority(priority);
		
		setNature(nature);
		
		waitForExtJSAjaxComplete(20);
		
		clickBookCall();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(!driver.findElements(By.xpath("//button[contains(text(),'Show Call')]")).isEmpty(),"Show Call button is present");
		
		waitForExtJSAjaxComplete(20);
		
		clickMyCallsTab();
		waitForMaskDisappear();

        HelpDeskBackOfficePageObject backOffice=new HelpDeskBackOfficePageObject();
		
		waitForExtJSAjaxComplete(20);
		
		backOffice.filterGridForCall("@realid", "mogrid", subject, "Reference");
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(20);
		
		HelpDeskBackOfficePageObject objHDFO = new HelpDeskBackOfficePageObject();
		objHDFO.openCallDetailsDialog(subject);
		
		waitForExtJSAjaxComplete(20);
		
		clickTrackingTab(DETAILS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		clickTrackingHistoryTab();
		
		waitForExtJSAjaxComplete(20);
		
		clickAddAction();
		
		waitForExtJSAjaxComplete(20);
		
		dragAndDropActionWindows(ADD_ACTION_WINDOW_HEADER, DETAILS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddActionForCall();
		
		waitForExtJSAjaxComplete(20);
		
		String text=getToolTipTextForActionButtons(ADD_ACTION_WINDOW_HEADER,"Add Action");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(text.contains(toolTipMsgForCall),"Tool tip message is displayed for add action button");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddActionAndCloseForCall(ADD_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		text=getToolTipTextForActionButtons(ADD_ACTION_WINDOW_HEADER,"Add Action and Close");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(text.contains(toolTipMsgForCall),"Tool tip message is displayed for add action and close button");
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions(possibleAction);
		
		setActionNote(actionNote);
		
		setActionAssigned(assigned);
		
		waitForExtJSAjaxComplete(20);
		
		setActionContact(contact);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddActionAndCloseForCall(ADD_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		boolean status=isActionWindowClosed(ADD_ACTION_WINDOW_HEADER);
		
		softAssert.assertFalse(status,"Add action window is closed after clicking on AddActionandclose button");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddAction();
		
		waitForExtJSAjaxComplete(20);
		
		dragAndDropActionWindows(ADD_ACTION_WINDOW_HEADER, DETAILS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		clickPossibleActions(possibleAction2);
		
		waitForExtJSAjaxComplete(20);
		
		setActionNote(actionNote);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddActionForCall();
		
		waitForExtJSAjaxComplete(20);
		
		status=isActionWindowClosed(ADD_ACTION_WINDOW_HEADER);
		
		softAssert.assertTrue(status,"Add action window is not closed after clicking on AddAction button");
		
		waitForExtJSAjaxComplete(20);
		
		clickCloseForCall(ADD_ACTION_WINDOW_HEADER);
		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);
		
		clickEditLastAction();
		
		waitForExtJSAjaxComplete(20);
		
		dragAndDropActionWindows(EDIT_ACTION_WINDOW_HEADER, DETAILS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		text=getActionAssigned(EDIT_ACTION_WINDOW_HEADER);
		
		softAssert.assertTrue(text.contains(assigned), "Assigned field of last action value is populated");
		
		waitForExtJSAjaxComplete(20);
		
		text=getActionContact(EDIT_ACTION_WINDOW_HEADER);
		
		softAssert.assertTrue(text.contains(contact),"Contact field of last action value is populated");
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);
	
		clickCloseForCall(EDIT_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(25);
		
		status=isActionWindowClosed(EDIT_ACTION_WINDOW_HEADER);
		
		softAssert.assertFalse(status,"edit action window is closed after clicking on close button");
		
		waitForExtJSAjaxComplete(20);
		
		clickEditLastAction();
		
		waitForExtJSAjaxComplete(25);
		
		dragAndDropActionWindows(EDIT_ACTION_WINDOW_HEADER, DETAILS_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		setActionContact(contact1);
		
		waitForExtJSAjaxComplete(20);
		
		this.clickSaveAction(EDIT_ACTION_WINDOW_HEADER);
		
		waitForExtJSAjaxComplete(20);
		
		status=isActionWindowClosed(EDIT_ACTION_WINDOW_HEADER);
		
		softAssert.assertTrue(status,"edit action window is not closed after clicking on save action button");
		
		waitForExtJSAjaxComplete(20);
		
		text=getActionContact(EDIT_ACTION_WINDOW_HEADER);
		
		softAssert.assertTrue(text.contains(contact1),"changes are saved after clicking on save action button");
		
		waitForExtJSAjaxComplete(20);
		
		this.clickSaveActionClose(EDIT_ACTION_WINDOW_HEADER);
		waitForExtJSAjaxComplete(10);
		waitForExtJSAjaxComplete(20);
		
		status=isActionWindowClosed(EDIT_ACTION_WINDOW_HEADER);
		
		softAssert.assertFalse(status,"edit action window is closed after clicking on save action close button");
		
		waitForExtJSAjaxComplete(20);
		
		clickHistoryDetails();
		
		waitForExtJSAjaxComplete(20);
		
		text=getCallHistory(ACTION_HISTORY_WINDOW_HEADER,CALL_FIRST_STATUS_CLASS,"1");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(text, "In Progress","Action history for call first status is displayed");
		
		text=getCallHistory(ACTION_HISTORY_WINDOW_HEADER,CALL_LAST_STATUS_CLASS,"1");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(text,"Finished","Action history for call last status is displayed");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertAll();
		
		Reporter.log("Multiple Actions was succesfully verified.", true);
	}
	
	/**
	 * TestCase ID: C74010
	 * Author : MMA
	 */

	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testFieldsAvailabilityAtGeneralTabInMyCalls() throws Exception{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:C61269: Fields Availability at General Tab in My Calls and My TeamCalls</span><br>", true );

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testFieldsAvailabilityAtGeneralTabInMyCalls");

		String gridName1 = "Call Details";
		String gridName2 = "UDI Fields";
		int rowsToReduce_CallDetails = 9;
		int rowsToReduce_UDIFields = 0;
		List<String> callDetails_FieldNames = new ArrayList<String>(); 

		CallTemplatesPageObject calltemplatesPageObject = new CallTemplatesPageObject();

		calltemplatesPageObject.clickAdministration();

		waitForExtJSAjaxComplete(20);

		calltemplatesPageObject.expandModuleSettings();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(clickHelpDeskGeneralSettings(), "General settings Window is Displayed");

		waitForExtJSAjaxComplete(20);

		setWebAccountGroup("automation group");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);
		
		//Check visibility for all General Tab fields 
		softAssert.assertTrue(checkVisibilityOfAll(gridName1,rowsToReduce_CallDetails), "Several Fields of "+ gridName1+" are checked" );

		callDetails_FieldNames.addAll(getAllFieldNamesOfSectionInGeneralSettings(gridName1,rowsToReduce_CallDetails));

		softAssert.assertTrue(checkVisibilityOfAll(gridName2,rowsToReduce_UDIFields), "Several Fields of "+ gridName2+" are checked");

		softAssert.assertTrue(	clickOnSaveChangesInGeneralSettings( ), "Changes are saved Successfully");

		callDetails_FieldNames.addAll(getAllFieldNamesOfSectionInGeneralSettings(gridName2,rowsToReduce_UDIFields));

		callDetails_FieldNames.remove("Additional comment");
		
		callDetails_FieldNames.remove("Attached Documents");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_MYCALLS);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		collapseDetailsPanel();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		driver.findElement(By.xpath("//div[contains(@class,'x-panel helpdesk x-panel-noborder')]//div[contains(@class, 'x-grid3-body')]//div[contains(@class,'row-first')]")).click();

		waitForMaskDisappear();

		clickGeneralTab("Details");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isFieldsDisplayedAsReadOnlyInDetailsWindow(callDetails_FieldNames), "Visibility checked fields are only present and they are read only in My calls "); 

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		closeXWindow();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickMyTeamsCallsTab();

		waitForExtJSAjaxComplete(20);

		collapseDetailsPanel();

		waitForExtJSAjaxComplete(20);

		driver.findElement(By.xpath("//div[contains(@class,'x-panel helpdesk x-panel-noborder')]//div[contains(@class, 'x-grid3-body')]//div[contains(@class,'row-first')]")).click();

		waitForMaskDisappear();

		clickGeneralTab("Details");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(isFieldsDisplayedAsReadOnlyInDetailsWindow(callDetails_FieldNames), "Visibility checked fields are only present and they are read only in My calls "); 

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		closeXWindow();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		calltemplatesPageObject.clickAdministration();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		calltemplatesPageObject.expandModuleSettings();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(clickHelpDeskGeneralSettings(), "General settings Window is Displayed");

		waitForExtJSAjaxComplete(20);

		int rowNumbToUncheck = 2;

		//Uncheck visibility for some general tab fields  
		softAssert.assertTrue(unCheckVisibility( rowNumbToUncheck,gridName1), "Row 2 of section "+gridName1+" is Unchecked");

		String callDetails_UncheckedField = getFieldNameInGeneralSettings(gridName1 ,rowNumbToUncheck);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(unCheckVisibility( rowNumbToUncheck,gridName2), "Row 2 of section "+gridName2+" is Unchecked");

		String UDI_UncheckedField = getFieldNameInGeneralSettings(gridName2 ,rowNumbToUncheck);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(	clickOnSaveChangesInGeneralSettings( ), "Changes are saved Successfully");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_MYCALLS);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		collapseDetailsPanel();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		driver.findElement(By.xpath("//div[contains(@class,'x-panel helpdesk x-panel-noborder')]//div[contains(@class, 'x-grid3-body')]//div[contains(@class,'row-first')]")).click();

		waitForMaskDisappear();

		clickGeneralTab("Details");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(isFieldDisplayedInDetailsWindow(callDetails_UncheckedField), "Visibility Unchecked fields is not present in  section "+gridName1); 

		softAssert.assertFalse(isFieldDisplayedInDetailsWindow(UDI_UncheckedField), "Visibility Unchecked fields is not present in  section "+gridName2); 

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		closeXWindow();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickMyTeamsCallsTab();

		waitForExtJSAjaxComplete(20);

		collapseDetailsPanel();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		driver.findElement(By.xpath("//div[contains(@class,'x-panel helpdesk x-panel-noborder')]//div[contains(@class, 'x-grid3-body')]//div[contains(@class,'row-first')]")).click();

		waitForMaskDisappear();

		clickGeneralTab("Details");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(isFieldDisplayedInDetailsWindow(callDetails_UncheckedField), "Visibility Unchecked fields is not present in  section "+gridName1); 

		softAssert.assertFalse(isFieldDisplayedInDetailsWindow(UDI_UncheckedField), "Visibility Unchecked fields is not present in  section "+gridName2); 

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		closeXWindow();

		waitForExtJSAjaxComplete(20);

		softAssert.assertAll();

		Reporter.log("Only visibility checked Fields are  Availability at General Tab in My Calls and My TeamCalls ",true);


	}

	/**
	 * TestCase ID: C74022
	 * Author : MMA	
	 */
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void testAddingMultipleAttachments() throws Exception{
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:C61281: Adding Multiple Attachments to a Call</span><br>", true );

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testAddingMultipleAttachments");

		String subject = "Attachments"+ getCurrentDate().substring(getCurrentDate().length()-6);
		String priority = "1prePrior";
		String url = "https://test.com";
		String file = "te2.csv";
		String urlDescription = "url descr" + getCurrentDate().substring(getCurrentDate().length()-6);
		String fileDescription = "File descr" + getCurrentDate().substring(getCurrentDate().length()-6);
		String urlRemark =  "url remark" + getCurrentDate().substring(getCurrentDate().length()-6);
		String fileRemark =  "file remark" + getCurrentDate().substring(getCurrentDate().length()-6);
		String type = "labelen";

		CallTemplatesPageObject calltemplatesPageObject = new CallTemplatesPageObject();

		calltemplatesPageObject.clickAdministration();

		waitForExtJSAjaxComplete(20);

		calltemplatesPageObject.expandModuleSettings();

		waitForExtJSAjaxComplete(20);

		// Template field visibility  
		calltemplatesPageObject.clickCallTemplate();

		waitForExtJSAjaxComplete(20);

		calltemplatesPageObject.selectCallTemplate("aqaPreCallTemplate");	

		waitForExtJSAjaxComplete(20);

		calltemplatesPageObject.clickOnTab("Fields");

		waitForExtJSAjaxComplete(20);

		calltemplatesPageObject.checkCallTemplatesFieldsVisibility("Related Documents");

		waitForExtJSAjaxComplete(20);

		//Creating a new call 
		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWCALL);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickCallTemplate("aqaPreCallTemplate");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setSubject(subject);

		setPriority(priority);

		setNature("Default Nature");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setFile(file, fileDescription, fileRemark, type);

		waitForExtJSAjaxComplete(20);

		setUrl(url, urlDescription, urlRemark, type);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickBookCall();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(!driver.findElements(By.xpath("//button[contains(text(),'Show Call')]")).isEmpty(),"Show Call button is present. The call is booked");

		click("//button[contains(text(),'Show Call')]");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		// The call is opened in both "Details Panel" and "Details Window". So closing "Details Window"  
		waitForExtJSAjaxComplete(20);
		
		closeXWindow();

		waitForExtJSAjaxComplete(20);

		//clickDocumentsTab();
		//Documents tab has been renamed to Attachments
		clickAttachmentsTab();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, urlDescription+"|"+type+"|"+urlRemark, "@class","x-grid3"),"url can be edited");

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, file+"|"+fileDescription+"|"+type+"|"+fileRemark, "@class","x-grid3"),"file can be edited");

		waitForExtJSAjaxComplete(20);

		calltemplatesPageObject.clickAdministration();

		waitForExtJSAjaxComplete(20);

		calltemplatesPageObject.expandModuleSettings();

		waitForExtJSAjaxComplete(20);

		calltemplatesPageObject.clickCallTemplate();

		waitForExtJSAjaxComplete(20);

		calltemplatesPageObject.selectCallTemplate("aqaPreCallTemplate");	

		waitForExtJSAjaxComplete(20);

		calltemplatesPageObject.clickOnTab("Fields");

		waitForExtJSAjaxComplete(20);

		calltemplatesPageObject.unCheckCallTemplatesFieldsVisibility("Related Documents");

		waitForExtJSAjaxComplete(20);

		// Checking field presence     
		expandSubMainMenu("HelpDesk");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWCALL);

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickCallTemplate("aqaPreCallTemplate");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		softAssert.assertFalse(isFieldDisplayedInCreateCallFromTemplatePanel("Related Documents")," Related Documents Field is not present");

		softAssert.assertAll();

		Reporter.log(" Multiple attachments can be added to a call",true);
	}

	/**
	 * Test Case ID : C92613
	 * Author : MMA
	 * @throws Exception
	 */
	@Test(enabled = true, retryAnalyzer=RetryAnalyzer.class)
	public void testBookACallWithBookCallsForEveryoneRight() throws Exception {
		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C92613: User can Book a Call for any available person with 'Book Calls For Everyone' right (MYM-29932) </span><br>", true );

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testBookACallWithBookCallsForEveryoneRight");	

		String subject = "subjectBO"+ getCurrentDate().substring(getCurrentDate().length()-6);
		String priority = "1prePrior";
		String fieldName = "Caller:";
		String fieldName1 = "Customer:";
		String customer = "My Company";
		String caller = "Mandlem Manasa";
		String contCaller = "Contact HDClient";
		String contCustomer = "HDClntOrgCus1";
		String user1 = "HDNoBookOwnCallRights";
		String customer1 = "2preCompName";
		String user2 = "HDNoFrontOfficeRights";
		String customer2 = "My Company";
		String defaultUser = "HELPDESK AQA";
		String defaultCustomer = "MCS";
		
		waitForExtJSAjaxComplete(10);
		
		expandAdministration();
		waitForExtJSAjaxComplete(5);

		expandSubMainMenu("HelpDesk");
		waitForExtJSAjaxComplete(5);

		//Creating a new call  with logged in user 
		waitAndClick(XPATH_NEWCALL);
		waitForExtJSAjaxComplete(15);
		waitForExtJSAjaxComplete(15);

		clickCallTemplate("TemplateA");
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getFieldValuesFromCreateCallFromTemplatePanel(fieldName,"NEW_CALLER"),getUserNameOfLoggedInUser(),"Current User is defined in the 'Caller' field");
		softAssert.assertEquals(getFieldValuesFromCreateCallFromTemplatePanel(fieldName1,"NEW_CUSTOMER"),customer,"Current User's Customer is defined in the 'Customer' field");
		softAssert.assertTrue(isFieldEditableInCreateCallFromTemplatePanel("NEW_CALLER"),"Caller field is editable");
		softAssert.assertTrue(isFieldEditableInCreateCallFromTemplatePanel("NEW_CUSTOMER"),"Customer  field is editable");

		setSubject(subject);
		setPriority(priority);
		setNature("Default Nature");
		waitForExtJSAjaxComplete(5);
		
		clickBookCall();
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(!driver.findElements(By.xpath("//button[contains(text(),'Show Call')]")).isEmpty(),"Show Call button is present. The call is booked");

		clickMyCallsTab();
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, subject, "@class", "helpdesk"),"My Calls OV is displayed with a Booked Call");
		waitForExtJSAjaxComplete(3);
		
		//Creating a new call  with Employee 
		clickNewCallTab();
		waitForExtJSAjaxComplete(20);
		subject = "subjectBOLic"+ getCurrentDate().substring(getCurrentDate().length()-4);
		clickCallTemplate("TemplateA");
		waitForExtJSAjaxComplete(20);

		setCaller(caller);
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getFieldValuesFromCreateCallFromTemplatePanel(fieldName,"NEW_CALLER"),caller,"Employee is defined in the 'Caller' field");
		softAssert.assertEquals(getFieldValuesFromCreateCallFromTemplatePanel(fieldName1,"NEW_CUSTOMER"),customer,"Employee's Customer is defined in the 'Customer' field");
		softAssert.assertTrue(isFieldEditableInCreateCallFromTemplatePanel("NEW_CALLER"),"Caller field is editable");
		softAssert.assertTrue(isFieldEditableInCreateCallFromTemplatePanel("NEW_CUSTOMER"),"Customer  field is editable");

		setSubject(subject);
		setPriority(priority);
		setNature("Default Nature");
		waitForExtJSAjaxComplete(5);
		
		clickBookCall();
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(!driver.findElements(By.xpath("//button[contains(text(),'Show Call')]")).isEmpty(),"Show Call button is present. The call is booked");
		clickXPath(XPATH_BACKOFFICE);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(10);

		collapseDetailsPanel();
		waitForExtJSAjaxComplete(3);

		HelpDeskBackOfficePageObject backOffice=new HelpDeskBackOfficePageObject();
		backOffice.filterGridForCall("@realid", "mogrid", subject, "Reference");
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(5);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver,subject, "@class", "helpdesk"),"The Calls Overview is displayed");
		openCallDetailsDialog(subject);
		waitForExtJSAjaxComplete(10);

		clickGeneralTab(DETAILS_WINDOW_HEADER);
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getFieldValuesInGeneralTab("Operator:","operator",DETAILS_WINDOW_HEADER),getUserNameOfLoggedInUserFirstNameLastNameFormat(),"Logged User is displayed as Operator in selected Call");
		waitForExtJSAjaxComplete(10);
		
		closeUsingToolBar(DETAILS_WINDOW_CLASS);
		waitForExtJSAjaxComplete(10);

		//Creating a new call  with Contact  
		clickNewCallTab();
		waitForExtJSAjaxComplete(20);

		clickCallTemplate("TemplateA");
		waitForExtJSAjaxComplete(20);
		
		subject = "subjectBOL"+ getCurrentDate().substring(getCurrentDate().length()-6);
		setCaller(contCaller);
		waitForExtJSAjaxComplete(5);

		softAssert.assertEquals(getFieldValuesFromCreateCallFromTemplatePanel(fieldName,"NEW_CALLER"),contCaller,"Contact is defined in the 'Caller' field");
		softAssert.assertEquals(getFieldValuesFromCreateCallFromTemplatePanel(fieldName1,"NEW_CUSTOMER"),contCustomer,"Contact's Customer is defined in the 'Customer' field");
		softAssert.assertTrue(isFieldEditableInCreateCallFromTemplatePanel("NEW_CALLER"),"Caller field is editable");
		softAssert.assertTrue(isFieldEditableInCreateCallFromTemplatePanel("NEW_CUSTOMER"),"Customer  field is editable");

		setSubject(subject);
		setPriority(priority);
		setNature("Default Nature");
		waitForExtJSAjaxComplete(5);

		clickBookCall();
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(!driver.findElements(By.xpath("//button[contains(text(),'Show Call')]")).isEmpty(),"Show Call button is present. The call is booked");
		
		//Creating a call by Uncheck the 'Book Own Calls' right
		HelpDeskAccessTestSuit objHDFO = new HelpDeskAccessTestSuit();
		objHDFO.navigateToMainPage(user1,"qwerty");
		expandAdministration();
		waitForExtJSAjaxComplete(5);

		expandSubMainMenu("HelpDesk");
		waitForExtJSAjaxComplete(5);

		waitAndClick(XPATH_NEWCALL);
		waitForExtJSAjaxComplete(15);
		waitForExtJSAjaxComplete(15);

		clickCallTemplate("TemplateA");
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getFieldValuesFromCreateCallFromTemplatePanel(fieldName,"NEW_CALLER"),getUserNameOfLoggedInUser(),"Current User is defined in the 'Caller' field");
		softAssert.assertEquals(getFieldValuesFromCreateCallFromTemplatePanel(fieldName1,"NEW_CUSTOMER"),customer1,"Employee's Customer is defined in the 'Customer' field");
		softAssert.assertTrue(isFieldEditableInCreateCallFromTemplatePanel("NEW_CALLER"),"Caller field is editable");
		softAssert.assertTrue(isFieldEditableInCreateCallFromTemplatePanel("NEW_CUSTOMER"),"Customer  field is editable");
		waitForExtJSAjaxComplete(3);
		
		clickCallTemplate("TemplateC");
		waitForExtJSAjaxComplete(20);
		
		subject = "Test"+ getCurrentDate().substring(getCurrentDate().length()-6);
		softAssert.assertEquals(getFieldValuesFromCreateCallFromTemplatePanel(fieldName,"NEW_CALLER"),defaultUser,"Default User is defined in the 'Caller' field");
		softAssert.assertEquals(getFieldValuesFromCreateCallFromTemplatePanel(fieldName1,"NEW_CUSTOMER"),defaultCustomer,"Default User's Customer is defined in the 'Customer' field");
		softAssert.assertTrue(isFieldEditableInCreateCallFromTemplatePanel("NEW_CALLER"),"Caller field is editable");
		softAssert.assertTrue(isFieldEditableInCreateCallFromTemplatePanel("NEW_CUSTOMER"),"Customer  field is editable");
		waitForExtJSAjaxComplete(3);
		
		String currentUser = getUserNameOfLoggedInUser();
		setCaller(currentUser);
		waitForExtJSAjaxComplete(5);
		
		HelpDeskBackOfficeTestSuite objHD = new HelpDeskBackOfficeTestSuite();
		objHD.setCustomer(customer1);
		waitForExtJSAjaxComplete(5);
		
		setSubject(subject);
		setPriority(priority);
		setNature("Default Nature");
		waitForExtJSAjaxComplete(5);

		clickBookCall();
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(!driver.findElements(By.xpath("//button[contains(text(),'Show Call')]")).isEmpty(),"Show Call button is present. The call is booked");

		clickMyCallsTab();
		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver, subject, "@class", "helpdesk"),"My Calls OV is displayed with a Booked Call");
		waitForExtJSAjaxComplete(3);
		
		clickXPath(XPATH_BACKOFFICE);
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(20);

		collapseDetailsPanel();
		waitForExtJSAjaxComplete(10);

		backOffice.filterGridForCall("@realid", "mogrid", subject, "Reference");
		waitForMaskDisappear();
		waitForExtJSAjaxComplete(10);

		softAssert.assertTrue(Grid.isRowInGridPresent(driver,subject, "@class", "helpdesk"),"The Calls Overview is displayed");
		openCallDetailsDialog(subject);
		waitForExtJSAjaxComplete(10);

		clickGeneralTab(DETAILS_WINDOW_HEADER);
		waitForExtJSAjaxComplete(20);

		softAssert.assertEquals(getFieldValuesInGeneralTab("Operator:","operator",DETAILS_WINDOW_HEADER),getUserNameOfLoggedInUserFirstNameLastNameFormat(),"Logged User is displayed as Operator in selected Call");
		waitForExtJSAjaxComplete(3);
		
		//Creating a call by  Checking the 'Book Own Calls' right and Uncheck the Helpdesk FrontOffice License 
		objHDFO.navigateToMainPage(user2,"qwerty");
		expandAdministration();
		waitForExtJSAjaxComplete(5);

		expandSubMainMenu("HelpDesk");
		waitForExtJSAjaxComplete(5);

		waitAndClick(XPATH_NEWCALL);
		waitForExtJSAjaxComplete(15);
		waitForExtJSAjaxComplete(15);

		clickCallTemplate("TemplateA");
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getFieldValuesFromCreateCallFromTemplatePanel(fieldName,"NEW_CALLER"),getUserNameOfLoggedInUser(),"Current User is defined in the 'Caller' field");
		softAssert.assertEquals(getFieldValuesFromCreateCallFromTemplatePanel(fieldName1,"NEW_CUSTOMER"),customer2,"Current User's Customer is defined in the 'Customer' field");
		softAssert.assertTrue(isFieldEditableInCreateCallFromTemplatePanel("NEW_CALLER"),"Caller field is editable");
		softAssert.assertTrue(isFieldEditableInCreateCallFromTemplatePanel("NEW_CUSTOMER"),"Customer  field is editable");

		softAssert.assertAll();

		Reporter.log("User can Book a Call for any available person with 'Book Calls For Everyone' right (MYM-29932)",true);
	}
	
	/**
	 * Test Case ID: C119646
	 * Author :SRD
	 */
	@Test(enabled=true,retryAnalyzer=RetryAnalyzer.class)
	public void testWorkforceEmployeAutoAssignedtoWO() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:Workforce Employee can be auto assigned to WO C119646" + " </span><br>",
				true);

		String subject 	  =  "testAutoAssigned" + getCurrentDate().substring(getCurrentDate().length()-6);
		String priority   =  "1prePrior";
		String nature	  =  "Default Nature";
		String location	  =	 "1aqaPreParSites";
		String assignedTo =  "SELENIUM";

		SoftAssert softAssert = new SoftAssert();

		softAssert.setMethodName("testWorkforceEmployeAutoAssignedtoWO");

		expandAdministration();

		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("HelpDesk");

		waitForExtJSAjaxComplete(20);

		waitAndClick(XPATH_NEWCALL);
		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		clickCallTemplate("Other Subject");

		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		setSubject(subject);

		setPriority(priority);

		setNature(nature);

		setLocation(location);

		waitForExtJSAjaxComplete(20);

		clickBookCall();

		waitForExtJSAjaxComplete(20);

		softAssert.assertTrue(!driver.findElements(By.xpath("//button[contains(text(),'Show Call')]")).isEmpty(),"Show Call button is present");

		waitForExtJSAjaxComplete(20);
		
		HelpDeskBackOfficePageObject backOffice=new HelpDeskBackOfficePageObject();
		
		backOffice.clickBackofficeTab();

		waitForMaskDisappear();

		waitForExtJSAjaxComplete(25);

		setDetailsTabCollapsedMode();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(20);

		backOffice.filterGridForCall("@realid", "mogrid", subject,"Reference");
		waitForMaskDisappear();

		Grid.checkRowInGriByTextValue(driver, subject);
		waitForExtJSAjaxComplete(10);

		clickWorkOrdersTab();

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		softAssert.assertTrue(isWOAssignedToAssignee(subject,assignedTo)," Action is performed successfully. The Employee is set as Planner");

		waitForExtJSAjaxComplete(20);

		softAssert.assertAll();

		Reporter.log(" Workforce Employee can be auto assigned to WO is successfully verified",true);

	}

}
