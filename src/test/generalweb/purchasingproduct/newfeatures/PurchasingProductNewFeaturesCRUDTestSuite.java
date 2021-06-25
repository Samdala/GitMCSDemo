package test.generalweb.purchasingproduct.newfeatures;

//import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;
import test.generalweb.purchasingproduct.PurchasingProductPageObject;
import test.generalweb.transactions.TransactionsPageObject;
import test.generalweb.warehouse.AddWarehousePageObject;


public class PurchasingProductNewFeaturesCRUDTestSuite extends
		PurchasingProductPageObject {

	
	/**
	 * Testcase ID: C90741
	 * @author vpcc
	 */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testConfirmationMsgPurchasingProdDelete() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test:  Confirmation Message should be displayed when trying to delete one Purchasing Product or Multiple Purchasing Products from Overview: C90741 </span><br>", true);

		Reporter.log("User creates/edits warehouse <br>", true);
		
		String purchasingProd1 =	"2preConsRef";
		String purchasingProd2 =	"7preProdRef";
		String purchasingProd3 =	"6preProdRef";
		String purchasingProd4 =	"4preConsRef" ;
		
		String commonmsgPart1 = "Are you sure you wish to delete Purchasing Product:";
		String commonmsgPart2 = "Are you sure you wish to delete the following Purchasing Products:";

		String alignment ="center";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("testConfirmationMsgPurchasingProdDelete");
		
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASINGPRODUCTS);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValue(driver, purchasingProd1);
		
		clickDeleteButton();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue((getWarningDialogTextMsg().contains(commonmsgPart1)&& getWarningDialogTextMsg().contains(purchasingProd1)), "Purchasing product can't be deleted error message is otbained");
		
		softAssert.assertEquals(getWarningDialogTextAlignment(), alignment, "Confirmation Message in center alignment is displayed");
	
		clickOnDialogButton("No");
		
		Grid.checkRowInGriByTextValue(driver, purchasingProd2);
		
		Grid.checkRowInGriByTextValue(driver, purchasingProd3);
		
		Grid.checkRowInGriByTextValue(driver, purchasingProd4);
		
		clickDeleteButton();
		
		waitForExtJSAjaxComplete(20);
		
		boolean iscommonMsgPresent = getWarningDialogTextMsg().contains(commonmsgPart2)&&getWarningDialogTextMsg().contains(purchasingProd1)&&getWarningDialogTextMsg().contains(purchasingProd2)
				&&getWarningDialogTextMsg().contains(purchasingProd3)&&getWarningDialogTextMsg().contains(purchasingProd4);
	
		softAssert.assertTrue(iscommonMsgPresent, "Purchasing product can't be deleted error message is otbained for multiple Purchasing products deletion");
		softAssert.assertEquals(getWarningDialogTextAlignment(), alignment, "Confirmation Message in center alignment is displayed");
		
		clickOnDialogButton("No");
		
		softAssert.assertAll();
		
		Reporter.log("Confirmation Message are displayed when trying to delete one Purchasing product or Multiple purchasing products from Overview", true);
	}

	
	/**
	 * Testcase ID		: C90738 
	 * Author			: vpcc
	 * */
	@Test(retryAnalyzer=RetryAnalyzer.class)
	public void testPurchasingProductStandardPriceIsDefaultExpenseAmount() throws Exception {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: C90738:Standard Price default value of the Supplier should be the Default Expense amount of the Purchasing product</span><br>", true);

		//****************variables Initialization **
		String reference ="4preConsRef";
		SoftAssert softAssert = new SoftAssert();
		softAssert.setMethodName("testPurchasingProductStandardPriceIsDefaultExpenseAmount");

		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		expandSubMainMenu("Stock");
		
		waitForExtJSAjaxComplete(20);
		
		waitAndClick(XPATH_PURCHASINGPRODUCTS);
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, reference);
		
		clickEditButton();
		
		waitForExtJSAjaxComplete(10);
		
		String defaultExpenseAmount = getDefaultExpenseAmount();
		
		//Click Suppliers Tab and Add a Preferred Supplier
		clickSuppliersTab();
		
		waitForExtJSAjaxComplete(10);
		
		clickAddButtonInSuppliersTab();
		
		waitForExtJSAjaxComplete(10);
		
		String supplier1 = "My Company";
		
		addSupplier(supplier1);
		
		waitForExtJSAjaxComplete(10);
		
		//Click on Supplier and layout will be opened in the bottom section of the page 
		openSuppliersInfoLayOut(supplier1);
		
		waitForExtJSAjaxComplete(10);
		
		clickGeneralTabInSuppliers();
		
		waitForExtJSAjaxComplete(10);
		
		String standardPrice = getStandardPriceFromGeneralTab();
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(defaultExpenseAmount,standardPrice,"Standard Price default value of the Supplier is same as the Default Expense amount of the Purchasing product.");
		
		close();
		
		waitForExtJSAjaxComplete(15);
		
		clickOnDialogButton("Yes");

		softAssert.assertAll();
		
		Reporter.log("Standard Price default value of the Supplier is same as the Default Expense amount of the Purchasing product.", true);
	}
    
		
}
