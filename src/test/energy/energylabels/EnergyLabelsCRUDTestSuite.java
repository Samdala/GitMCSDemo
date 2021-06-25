package test.energy.energylabels;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class EnergyLabelsCRUDTestSuite extends 
		EnergyLabelsPageObject {
	
	@DataProvider
	public Object[][] configuration() {
		return new Object[][] {
		{"Energy Labels", "x-panel x-panel-noborder x-grid-panel"}
		};
	}


	 @Test(enabled=true,dataProvider = "configuration", retryAnalyzer=RetryAnalyzer.class)
	public void testConfigurationENergyLabelsAddEdit(String entity, String realId) throws IOException, InterruptedException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Add/Edit Energy Configuration - Energy Labels: C22067</span><br>",
				true);

		Reporter.log("User add/edit Energy Labels <br>",
				true);
		
		String code = "my auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "test auto" + getCurrentDate().substring(getCurrentDate().length()-5);
		String color1 = "#DF0024"; //red
		String color2 = "#009337"; //green
		String rating1 = "High Rating";
		String rating2 = "Low Rating";
		String note = "My note";
		
		
		String codeEdited = "my auto ed" + getCurrentDate().substring(getCurrentDate().length()-5); codeEdited = codeEdited.replace(".", "1");
		String referenceEdited = "test auto ed" + getCurrentDate().substring(getCurrentDate().length()-5);
		String color1Edited = "#0469B3"; //blue
		String color2Edited = "#F8F400"; //yellow
		String rating1Edited = "Acceptable";
		String rating2Edited = "Failed";
		String noteEdited = "My note edited";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("configurationEnergyLabels");

		waitAndClick(XPATH_ENERGY);

		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		openConfigurationEntity(entity);

		waitForExtJSAjaxComplete(20);
		
		clickAddButton(realId);
		
		waitForExtJSAjaxComplete(20);
		
		setCode(code);
		
		setReference(reference);
		
		setNote(note);
		
		clickAddRatingButton();
		
		waitForExtJSAjaxComplete(20);
		
		setRating(rating1, "1");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddColor("1");
		
		waitForExtJSAjaxComplete(20);
		
		pickColorOnColorPicker(color1);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddRatingButton();
		
		waitForExtJSAjaxComplete(20);
		
		setRating(rating2, "2");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddColor("2");
		
		waitForExtJSAjaxComplete(20);
		
		pickColorOnColorPicker(color2);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		Grid.checkRowInGriByTextValueAndClick(driver, code);
		
		clickEditButton(realId);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getCode(), code, "Is Code correct after creation?");
		
		softAssert.assertEquals(getReference(), reference, "Is Reference correct after creation?");
		
		softAssert.assertEquals(getNote(), note, "Is Note correct after creation?");
		
		softAssert.assertEquals(getRating("1"), rating1, "Is Rating 1 correct after creation?");
		
		softAssert.assertEquals(getRating("2"), rating2, "Is Rating 2 correct after creation?");
		
		softAssert.assertEquals(getColor("1"), color1, "Is Color 1 correct after editing?");
		
		softAssert.assertEquals(getColor("2"), color2, "Is Color 2 correct after editing?");
		
		setCode(codeEdited);
		
		setReference(referenceEdited);
		
		setNote(noteEdited);
		
		setRating(rating1Edited, "1");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddColor("1");
		
		waitForExtJSAjaxComplete(20);
		
		pickColorOnColorPicker(color1Edited);
		
		waitForExtJSAjaxComplete(20);
		
		setRating(rating2Edited, "2");
		
		waitForExtJSAjaxComplete(20);

		clickAddColor("2");
		
		waitForExtJSAjaxComplete(20);
		
		pickColorOnColorPicker(color2Edited);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();

		Grid.checkRowInGriByTextValueAndClick(driver, codeEdited);
		
		clickEditButton(realId);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getCode(), codeEdited, "Is Code correct after editing?");
		
		softAssert.assertEquals(getReference(), referenceEdited, "Is Reference correct after editing?");
		
		softAssert.assertEquals(getNote(), noteEdited, "Is Note correct after editing?");
		
		softAssert.assertEquals(getRating("1"), rating1Edited, "Is Rating 1 correct after editing?");
		
		softAssert.assertEquals(getRating("2"), rating2Edited, "Is Rating 2 correct after editing?");
		
		softAssert.assertEquals(getColor("1"), color1Edited, "Is Color 1 correct after editing?");
		
		softAssert.assertEquals(getColor("2"), color2Edited, "Is Color 2 correct after editing?");
		
		close();
		
		softAssert.assertAll();
		
		Reporter.log("Energy Label was succesfully created and edited", true);

	}

	@Test(enabled = true, dataProvider = "configuration", retryAnalyzer=RetryAnalyzer.class)
	public void testEnergyLabelDelete(String entity, String realId) throws IllegalStateException, IOException, InterruptedException {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Energy Configuration - Energy Label: C22068</span><br>",
				true);

		Reporter.log("User deletes configuration - Energy Lable <br>",
				true);
		
		String code = "my auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
		String reference = "test auto del" + getCurrentDate().substring(getCurrentDate().length()-5);
		String color1 = "#DF0024"; //red
		String color2 = "#009337"; //green
		String rating1 = "High Rating";
		String rating2 = "Low Rating";
		String note = "My note";

		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandConfiguration();
		
		openConfigurationEntity(entity);

		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		clickAddButton(realId);
		
		waitForExtJSAjaxComplete(20);
		
		waitForExtJSAjaxComplete(25);
		
		setCode(code);
		
		setReference(reference);
		
		setNote(note);
		
		clickAddRatingButton();
		
		waitForExtJSAjaxComplete(20);
		
		setRating(rating1, "1");
		
		waitForExtJSAjaxComplete(20);
		
		clickAddColor("1");
		
		waitForExtJSAjaxComplete(20);
		
		pickColorOnColorPicker(color1);
		
		waitForExtJSAjaxComplete(20);
		
		clickAddRatingButton();
		
		waitForExtJSAjaxComplete(20);
		
		setRating(rating2, "2");
		
		waitForExtJSAjaxComplete(20);

		clickAddColor("2");
		
		waitForExtJSAjaxComplete(20);
		
		pickColorOnColorPicker(color2);
		
		waitForExtJSAjaxComplete(20);
		
		saveClose();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		Grid.checkRowInGriByTextValueAndClick(driver, code);
		
		clickDeleteButton(realId);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(20);
		
		Assert.assertTrue(Grid.isRowInGridAbsent(driver, code), code+" - Code after delete is present");
		
		Reporter.log("Configuration " + entity + " was succesfully deleted", true);
		
	}

}



