package test.energy.energyratings;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import test.framework.RetryAnalyzer;
import test.framework.SoftAssert;
import test.framework.webelement.Grid;

public class EnergyRatingsCRUDTestSuite extends EnergyRatingsPageObject{
	
	@Test(enabled=true)
	public void testEnergyRatingsCreateEdit() throws IOException  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Create and Edit Energy Ratings: C22429" + " </span><br>",
				true);

		Reporter.log("User creates and edits Energy Ratings"  + " <br>",
				true);
		
		//Field values for Energy Rating creation
		String label = "1preLabel";
		String rating = "High";
		String score =  "0.86";
		String certificate = "#356984";
		String inspector = "Smith";
		String ratingDate = "12-05-2013";
		String validUntil = "12-05-2015";
		String inspectionDate = "10-05-2013";
		String note = "note" + getCurrentDate().substring(getCurrentDate().length()-5);
		
		//String label = "1preLabel";
		String ratingEdited = "Low";
		String scoreEdited =  "1.25";
		String certificateEdited = "#129800";
		String inspectorEdited = "Anny";
		String ratingDateEdited = "12-12-2013";
		String validUntilEdited = "12-12-2014";
		String inspectionDateEdited = "10-12-2013";
		String noteEdited = "note ed" + getCurrentDate().substring(getCurrentDate().length()-5);

		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite1";
		String building = "slnmEnrgBuilding1";
		
		//Boolean build14 = driver.getCurrentUrl().contains("14");
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("EnergyRatingCreateEdit");

		//Navigate to Building - Energy Ratings
	
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, site);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, building);
		waitForMaskDisappear();
		
		waitForExtJSAjaxComplete(20);
		
		clickEnergyRatingsTab();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		boolean status = Grid.isRowInGridPresent(driver, "1preLabel", "@class", ENERGY_RATINGS_GRID_CLASS);
		
		if(status){
			Grid.checkRowInGriByTextValueAndClick(driver, label);
			
			clickDeleteEnergyRatingButton(ENERGY_RATINGS_GRID_CLASS);
			
			clickOnDialogButton("OK");
			
			waitForExtJSAjaxComplete(10);
		} 
				
		//Add Energy Rating

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);
		
		clickAddEnergyRatingButton();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setLabel(ENERGY_RATING_DIALOG, label);
		
		waitForExtJSAjaxComplete(20);
		
		setRating(ENERGY_RATING_DIALOG, rating);
		
		setScore(score);
		
		setCertificate(certificate);
		
		setInspector(inspector);
		
		setRatingDate(ratingDate);
		
		setValidUntil(validUntil);
		
		setInspectionDate(inspectionDate);
		
		setNote(note);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(25);
		
		//Check if all fields were saved correctly and edit Energy Rating afterwards
		
		Grid.checkRowInGriByTextValueAndClick(driver, note);
		
		clickEditEnergyRatingButton(ENERGY_RATINGS_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(25);
		
		softAssert.assertEquals(getLabel(), label, "Compare Label after creation");
		
		softAssert.assertEquals(getRating(), rating, "Compare Rating after creation");
		
		softAssert.assertEquals(getScore(), score, "Compare Score after creation");
		
		softAssert.assertEquals(getCertificate(), certificate, "Compare Certificate after creation");
		
		softAssert.assertEquals(getInspector(), inspector, "Compare Inspector after creation");
		
		softAssert.assertEquals(getRatingDate(), ratingDate, "Compare Rating Date after creation");
		
		softAssert.assertEquals(getValidUntil(), validUntil, "Compare Valid Until after creation");
		
		softAssert.assertEquals(getInspectionDate(), inspectionDate, "Compare Inspection Date after creation");
		
		softAssert.assertEquals(getNote(), note, "Compare Note after creation");
		
		//Edit Energy Rating
			
		setRating(ENERGY_RATING_DIALOG, ratingEdited);
		
		setScore(scoreEdited);
		
		setCertificate(certificateEdited);
		
		setInspector(inspectorEdited);
			
		setRatingDate(ratingDateEdited);
			
		setValidUntil(validUntilEdited);
		
		setInspectionDate(inspectionDateEdited);
		
		setNote(noteEdited);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(25);
		
		//Check if all fields were saved correctly
		
		Grid.checkRowInGriByTextValueAndClick(driver, noteEdited);
		
		clickEditEnergyRatingButton(ENERGY_RATINGS_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertEquals(getRating(), ratingEdited, "Compare Rating after editing");
		
		softAssert.assertEquals(getScore(), scoreEdited, "Compare Score after editing");
		
		softAssert.assertEquals(getCertificate(), certificateEdited, "Compare Certificate after editing");
		
		softAssert.assertEquals(getInspector(), inspectorEdited, "Compare Inspector after editing");
		
		softAssert.assertEquals(getRatingDate(), ratingDateEdited, "Compare Rating Date after editing");
			
		softAssert.assertEquals(getValidUntil(), validUntilEdited, "Compare Valid Until after editing");
		
		softAssert.assertEquals(getInspectionDate(), inspectionDateEdited, "Compare Inspection Date after editing");
		
		softAssert.assertEquals(getNote(), noteEdited, "Compare Note after editing");
		
		waitForExtJSAjaxComplete(20);
		
		close();
		
		waitForExtJSAjaxComplete(20);
		
		Grid.checkRowInGriByTextValueAndClick(driver, noteEdited);
		
		clickDeleteEnergyRatingButton(ENERGY_RATINGS_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, score), score+"Check if Energy Rating is deleted");
		
		softAssert.assertAll();
		
		Reporter.log("Energy Rating was successfully Created and Edited", true);
		
	}
	
	@Test(enabled=true)
	public void testEnergyRatingsDelete() throws Exception  {

		Reporter.log("<span style='font-weight:bold;color:#000033'> "
				+ "Test: Delete Energy Ratings: C22645" + " </span><br>",
				true);

		Reporter.log("User deletes Energy Ratings"  + " <br>",
				true);
		
		//Field values for Energy Rating creation
		String label = "LEED";
		String rating = "Platinum";
		String score =  "1.12";
		String ratingDate = "10-10-2010";
		String note = getCurrentDate().substring(getCurrentDate().length()-5);

		String area = "slnmEnrgArea1 (slnmEnrgArea)";
		String site = "slnmEnrgSite1";
		String building = "slnmEnrgBuilding1";
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.setMethodName("EnergyRatingDelete");

		//Navigate to Building - Energy Ratings
	
		waitAndClick(XPATH_ENERGY);
		
		waitForExtJSAjaxComplete(20);
		
		expandNavigator();
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, area);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.expandNavigatorTreeNode(driver, site);
		
		waitForExtJSAjaxComplete(20);
		
		test.framework.webelement.Tree.checkNodeInNavigatorTreeByTextValue(driver, building);
		Thread.sleep(3000);
		
		waitForExtJSAjaxComplete(20);
		
		clickEnergyRatingsTab();
		
		waitForExtJSAjaxComplete(20);

		waitForExtJSAjaxComplete(25);

		waitForExtJSAjaxComplete(25);
		
		//Add Energy Rating
		
		clickAddEnergyRatingButton();
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		waitForExtJSAjaxComplete(25);
		
		setLabel(ENERGY_RATING_DIALOG, label);
		
		waitForExtJSAjaxComplete(20);
		
		setRating(ENERGY_RATING_DIALOG, rating);
		
		setScore(score);
		
		setRatingDate(ratingDate);
		
		setNote(note);
		
		saveClose();
		
		waitForExtJSAjaxComplete(20);
		
		//Delete Energy Rating
		
		Grid.checkRowInGriByTextValueAndClick(driver, note);
		
		clickDeleteEnergyRatingButton(ENERGY_RATINGS_GRID_CLASS);
		
		waitForExtJSAjaxComplete(20);
		
		clickOnDialogButton("OK");
		
		waitForExtJSAjaxComplete(20);
		
		softAssert.assertTrue(Grid.isRowInGridAbsent(driver, score), score+"Check if Energy Rating is deleted");
		
		Reporter.log("Energy Rating was successfully deleted", true);		
		
	}

}
