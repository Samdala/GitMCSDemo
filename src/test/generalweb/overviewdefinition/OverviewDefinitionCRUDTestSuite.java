package test.generalweb.overviewdefinition;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
//import test.framework.SoftAssert;
import test.framework.webelement.McsElement;

public class OverviewDefinitionCRUDTestSuite extends
		OverviewDefinitionPageObject {


	@DataProvider
	public Object[][] overviewDefinition() {
		return new Object[][] {
		{"warehouse", "MCS_PORTAL_ST-ACTIVATE[WAREHOUSES]"},
		{ "purchasing products", "MCS_PORTAL_ST-ACTIVATE[PURCHASING_PRODUCTS]" },
		{"stock items","MCS_PORTAL_ST-ACTIVATE[STOCK_ITEMS]"},
		{"transactions","MCS_PORTAL_ST-ACTIVATE[TRANSACTIONS]"},
		{"call","MCS_PORTAL_HD-ACTIVATE[BACKOFFICE]"},
		{"activity","TIME_REG"},
		{"consumptions","FINANCIALS"},
		{"site","MCS_PORTAL_SP-ACTIVATE[OV]"},
		{"land","MCS_PORTAL_SP-ACTIVATE[OV]"},
		{"outside","MCS_PORTAL_SP-ACTIVATE[OV]"},
		{"building","MCS_PORTAL_SP-ACTIVATE[OV]"},
		{"floor","MCS_PORTAL_SP-ACTIVATE[OV]"},
		{"room","MCS_PORTAL_SP-ACTIVATE[OV]"},
		{"vroom","MCS_PORTAL_SP-ACTIVATE[OV]"},
		{"workplace","MCS_PORTAL_SP-ACTIVATE[OV]"},
		{"workorder","MCS_PORTAL_WO"}         
		};
	}


	 @Test(enabled=true,dataProvider = "overviewDefinition", retryAnalyzer=RetryAnalyzer.class)
	public void testOverviewDefinitionCreateEdit(String entity, String url) throws IllegalStateException, IOException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Edit " + entity + " Overview Definition: c19889, c19890, c19891, c19892, c19893, c19894," +
						" c19895, c19896, c19897, c19898, c19899, c19900, c19901, c19902, c19903</span><br>",
				true);

		Reporter.log("User edit overview definition for " + entity + " <br>",
				true);

		String definitionCategoryName = "my auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		String definitionName = "test auto" + getCurrentDate().substring(getCurrentDate().length()-5);
               
		expandAdministration();
		
		waitForExtJSAjaxComplete(20);
		
		goToDefinition(url);

		waitForExtJSAjaxComplete(20);

		prepareAndClickOverviewButton(entity);
		waitForExtJSAjaxComplete(20);
		clickAddOverViewButton();
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		setName(definitionName);
		
		setCategory(definitionCategoryName);
		
		clickEnabled();
		
		clickPublic();
		
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		save();

		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		goToDefinition(url);

		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(20);

		prepareAndClickOverviewButton(entity);
		
		waitForExtJSAjaxComplete(20);
                
		mouseMove("//span[contains(@class,'x-menu-item-text') and contains(text(),'"+definitionCategoryName+"')]");

		mouseMove("//span[contains(@class,'x-menu-item-text') and contains(text(),'"+definitionName+"')]");
	
		WebElement edit = driver.findElement(By.xpath("//span[contains(@class,'x-menu-item-text') and contains(text(),'Edit...')]"));
		
		waitWebElement(edit);
	
		javaScriptClick(edit);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(25);

		setCategory(definitionCategoryName + "edited");

		setName(definitionName +"edited");

		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		save();

		waitForExtJSAjaxComplete(25);
		waitForExtJSAjaxComplete(20);
		
		goToDefinition(url);
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		prepareAndClickOverviewButton(entity);
		
		mouseMove("//span[contains(@class,'x-menu-item-text') and contains(text(),'"+definitionCategoryName+"edited"+  "')]");

		Assert.assertTrue(
				McsElement.getElementByPartAttributeValue(driver, "span",
						"@class", "x-menu-item-text", "text()",
						definitionName +"edited", true, true).isDisplayed(), "Definition"
						+ entity + "is not edited!");

		Reporter.log("Definition " + entity + " was succesfully edited", true);

	}

	@Test(enabled = true, dataProvider = "overviewDefinition", retryAnalyzer=RetryAnalyzer.class)
	public void testOverviewDefinitionDelete(String entity, String url) throws IllegalStateException, IOException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete " + entity + " Overview Definition</span><br>",
				true);

		Reporter.log("User deletes overview definition for " + entity + " <br>",
				true);
		
		String definitionCategoryName = "my auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		String definitionName = "test auto" + getCurrentDate().substring(getCurrentDate().length()-5);

		expandAdministration();
		
		waitForExtJSAjaxComplete(20);

		goToDefinition(url);

		waitForExtJSAjaxComplete(20);

		prepareAndClickOverviewButton(entity);
		
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(10);
		clickAddOverViewButton();

		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(10);
		
		setName(definitionName+"del");
		
		setCategory(definitionCategoryName+"del");
		
		clickEnabled();
		
		clickPublic();

		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);
		save();

		waitForExtJSAjaxComplete(20);
		waitForExtJSAjaxComplete(20);

		goToDefinition(url);
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(20);

		prepareAndClickOverviewButton(entity);
		
		waitForExtJSAjaxComplete(20);

		mouseMove("//span[contains(@class,'x-menu-item-text') and contains(text(),'"+definitionCategoryName+"del"+"')]");

		mouseMove("//span[contains(@class,'x-menu-item-text') and contains(text(),'"+definitionName+"del"+"')]");

		WebElement delete = driver.findElement(By.xpath("//span[contains(@class,'x-menu-item-text') and contains(text(),'Delete')]"));
		
		waitWebElement(delete);

		javaScriptClick(delete);

		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("Yes");
		
		waitForMaskDisappear();

		waitForExtJSAjaxComplete(20);
		
		click(XPATH_OVERVIEW_BUTTON);

		try {
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			mouseMove("//span[contains(@class,'x-menu-item-text') and contains(text(),'"+definitionCategoryName+"del"+"')]");

			Assert.assertTrue(
					driver.findElements(
							By.xpath("//span[contains(@class,'x-menu-item-text') and contains(text(),'"+definitionName+"del')]"))
							.isEmpty(), "Definition" + entity
							+ "is not deleted!");
		} catch (Exception e) {
		}
		 finally {
				driver.manage()
						.timeouts()
						.implicitlyWait(configuration.getImplicitWait(),
								TimeUnit.SECONDS);
			}

		Reporter.log("Definition " + entity + " was succesfully deleted", true);

	}

}
