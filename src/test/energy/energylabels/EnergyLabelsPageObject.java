package test.energy.energylabels;

import java.util.concurrent.TimeUnit;

import org.testng.Reporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import test.energy.EnergyBaseTestSuite;
import org.openqa.selenium.JavascriptExecutor;
import test.framework.AbstractMcsTestSuite;
import test.framework.Timer;
import test.framework.webelement.McsElement;

public class EnergyLabelsPageObject extends EnergyBaseTestSuite {

	protected final String ADMINISTRATION_PANEL_CLASS = "x-panel-body x-panel-body-noborder";

	protected final String XPATH_ADD_FORM_WINDOW = "slnmRatLblId";
	
	protected final String PANEL_NAME = "Energy Labels";
	
	public void expandConfiguration() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"span", "text()", "Configuration", true, true).click();
		Reporter.log("Expand configuration", true);
	}

	public void openConfigurationEntity(String entity) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu",
				"button", "text()", entity, true, true).click();
		Reporter.log("Open Configuration " + entity, true);
	}

	public void clickAddButton(String gridId) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridId, "button", "@class", "x-btn-text",
				"text()", "Add", true, true).click();
		Reporter.log("Click Add button", true);
	}

	public void clickEditButton(String gridId) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridId, "button", "@class", "x-btn-text",
				"text()", "Edit", true, true).click();
		Reporter.log("Click Edit button", true);
	}

	public void clickDeleteButton(String gridId) {
		Timer timer = new Timer().start();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridId, "button", "@class", "x-btn-text",
				"text()", "Delete", true, true).click();
		clickOnDialogButton("OK");
		Reporter.log("Click Delete button" + " (" + timer.stop() + "ms)", true);
	}
	
	public void clickAddRatingButton() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "button", "@class", "x-btn-text",
				"text()", "Add", true, true).click();
		Reporter.log("Click Add Rating button", true);
	}

	public void setCode(String code) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"code", true, true).clear();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"code", true, true).sendKeys(code);
		Reporter.log("Set Code - '" + code + "'", true);
	}

	public String getCode() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "code", true, true).getAttribute("value");
	}

	public String getReference() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "input",
				"@name", "reference", true, true).getAttribute("value");
	}

	public void setReference(String reference) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"reference", true, true).clear();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "input", "@name",
				"reference", true, true).sendKeys(reference);
		Reporter.log("Set Reference - '" + reference + "'", true);
	}

	public String getNote() {
		return McsElement.getElementByPartAttributeValueAndParentElement(
				driver, "div", "@class", XPATH_ADD_FORM_WINDOW, "textarea",
				"@name", "notes", true, true).getAttribute("value");
	}

	public void setNote(String note) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "textarea", "@name",
				"notes", true, true).clear();
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "textarea", "@name",
				"notes", true, true).sendKeys(note);
		Reporter.log("Set Note - '" + note + "'", true);
	}

	public void saveClose() {
		waitForExtJSAjaxComplete(25);
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", XPATH_ADD_FORM_WINDOW, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		Reporter.log("Click Save & Close", true);
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save Close clicked", true);
	}

	public void close() {
		/*McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", XPATH_ADD_FORM_WINDOW, "button", "text()",
				"Close", true, true).click();*/
		WebElement ele = driver.findElement(By.xpath("//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//button[text()='Close']"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		}catch(Exception e){
			e.printStackTrace();
		}
		waitForExtJSAjaxComplete(25);
		Reporter.log("Click Close", true);
	}
	
	public void clickAddColor(String pos) throws InterruptedException {
		String colorCellXpath = "(//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]"
				+ "//div[contains(@class, 'x-grid3-cell-inner x-grid3-col-2 x-unselectable')])["+pos+"]";
		String colorPaletteXpath = "//div[contains(@class, 'slnmClrPckr')]";
		
		superClick(colorCellXpath, colorPaletteXpath);
		Reporter.log("Click Add Color", true);
	}
	
	//Find and pick color by HEX or RGB color representations
	public void pickColorOnColorPicker(String color) {
		//By HEX
		try {
			McsElement.getElementByPartAttributeValueAndParentElement(driver,
					"div", "@class", "slnmClrPckr", "div", "@data-color", color,
					true, true).click();
			Reporter.log("Select color - " + color, true);
		//By RGB
		} catch (Exception e) {
			McsElement.getElementByPartAttributeValueAndParentElement(driver,
					"div", "@class", "slnmClrPckr", "div", "@data-color", Color.fromString(color).asRgb(),
					true, true).click();
			Reporter.log("Select color - '" + color + "'", true);
		}
	}

	public void setRating(String rating, String pos) {
		//Click on DIV making INPUT to appear or click directly on INPUT
		try {
			McsElement.getElementByXpath(driver, "(//div[contains(@class, 'slnmRatLblId')]"
					+ "//div[contains(@class, 'x-grid3-cell-inner x-grid3-col-1')])["+pos+"]").click();
		} catch (Exception e) {
			McsElement.getElementByXpath(driver, "(//div[contains(@class, 'x-layer x-editor')]"
				+ "//input[contains(@class, 'x-form-text x-form-fiel')])").click();
		}
		
		McsElement.getElementByXpath(driver, "(//div[contains(@class, 'x-layer x-editor')]"
				+ "//input[contains(@class, 'x-form-text x-form-fiel')])").clear();
		McsElement.getElementByXpath(driver, "(//div[contains(@class, 'x-layer x-editor')]"
				+ "//input[contains(@class, 'x-form-text x-form-fiel')])").sendKeys(rating);
		Reporter.log("Set Rating " + pos + " - '" + rating + "'", true);
	}
	
	public String getRating(String pos) {
		return McsElement.getElementByXpath(driver, "(//div[contains(@class, 'slnmRatLblId')]//div[contains(@class, 'x-grid3-cell-inner x-grid3-col-1')])["+pos+"]").getAttribute("innerHTML");
	}
	
	//Get color as HEX in uppercase
	public String getColor(String pos) {
		return Color.fromString
				(McsElement.getElementByXpath(driver, "(//div[contains(@class, 'slnmRatLblId')]"
						+ "//div[contains(@class, 'x-grid3-cell-inner x-grid3-col-2')])["+pos+"]//..")
						.getCssValue("background-color")).asHex().toUpperCase();
	}
	
	public void close(String dialogClass) {
		McsElement.getElementByAttributeValueAndParentElement(driver, "div",
				"@class", dialogClass, "button", "text()",
				"Close", true, true).click();
		Reporter.log("Click Close button", true);
	}
	
	public boolean checkInputDisabled(String elementName){
		
		return McsElement.checkInputDisabled(driver, "@class", XPATH_ADD_FORM_WINDOW, elementName);	
	
	}
	
	public boolean checkTextareaDisabled(String elementName){
		 driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
			try {
				driver.findElement(By.xpath("//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//textarea[@name='" + elementName + "']//..//textarea[contains(@class, 'readonly')]"));
				return true;
			} catch (Exception e) {
				return false;
			}
		 finally{
			 driver.manage().timeouts().implicitlyWait(configuration.getImplicitWait(),TimeUnit.SECONDS);
		 }
			
	}
	
	public boolean checkMandatoryFieldSave(String dialog){
		return driver.findElement(
					By.xpath("//div[contains(@class,'"+dialog+"')]//span[contains(@class,'window-header')]")).getText().contains("Add");
		
	}
	
	//Reopen Weather Station Form being either saved or not
		
	public boolean reopenForm() {

		if (!checkMandatoryFieldSave(XPATH_ADD_FORM_WINDOW)) {

			close(XPATH_ADD_FORM_WINDOW);

			waitForExtJSAjaxComplete(20);

			McsElement.getElementByXpath(driver, "//span[text()='"+ PANEL_NAME +"']//..//..//button[text()='Add']").click();
			
			waitForExtJSAjaxComplete(10);

			return false;
		} else {

			close();

			waitForExtJSAjaxComplete(20);

			clickOnDialogButton("Yes");

			// catch (Exception e) {}

			waitForExtJSAjaxComplete(20);

			McsElement.getElementByXpath(driver, "//span[text()='"+ PANEL_NAME +"']//..//..//button[text()='Add']").click();
			
			waitForExtJSAjaxComplete(10);

			return true;

		}
	}
	
	public String getFieldValue(String fieldName) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//input[@name='"+fieldName+"']//..//input)[last()]"))
				.getAttribute("value");
	}
 
	public String getTextAreaFieldValue(String fieldName) {
		return driver
				.findElement(
						By.xpath("(//div[contains(@class,'"+XPATH_ADD_FORM_WINDOW+"')]//textarea[@name='"+fieldName+"'])[last()]"))
				.getAttribute("value");
	}
 
 
 	public void clearField(String fieldNameTag, String fieldName) {
		WebElement field =  driver.findElement
				(By.xpath("(//div[contains(@class, '"+XPATH_ADD_FORM_WINDOW+"')]//input[contains(@name, '"+fieldNameTag+"')]//..//input)[last()]"));
		field.click();
		field.clear();
		Reporter.log("Clear "+fieldName+" field", true);
	 }

}
