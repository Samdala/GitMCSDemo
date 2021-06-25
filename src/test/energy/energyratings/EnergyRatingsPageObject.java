package test.energy.energyratings;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import test.framework.AbstractMcsTestSuite;
import test.framework.webelement.DropDown;
import test.framework.webelement.McsElement;

public class EnergyRatingsPageObject extends AbstractMcsTestSuite{
	
	protected final String ENERGY_RATINGS_GRID_CLASS = "x-tab-panel x-tab-panel-noborder";
	protected final String ENERGY_RATING_DIALOG = "slnmBldRtnId";
	
	public void expandNavigator() {
		if (McsElement.getElementByXpath(driver, "//div[contains(@class, 'x-panel-noborder')]"
				+ "//span[contains(text(), 'Navigator')]//..//..").getAttribute("class").contains("collapsed")) {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "slnmNrgMenu", 
				"span", "text()", "Navigator", true, true).click();
		Reporter.log("Expand Navigation", true);
		}
	}
	
	public void clickEnergyRatingsTab(){
		/*McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", "x-panel-noborder",
				"span", "text()", "Energy Ratings", true, true).click();*/
		
		WebElement element = driver.findElement(By.xpath("//div[contains(@class, 'x-panel-noborder')]//span[contains(@class, 'x-tab-strip-text') and text()='Energy Ratings']"));
		
		if ("chrome".equals(configuration.getBrowser())) {
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView(true);", element);
            } catch (Exception e) {
            	System.out.println("Exception is G " +e);
            }
        }
		element.click();
		waitForExtJSAjaxComplete(20);
		Reporter.log("Click on Energy Ratings tab", true);
	}

	public void clickAddEnergyRatingButton() {
		/*McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Add", true, true).click();*/
		WebElement ele = driver.findElement(By.xpath("//div[contains(@realid,'location_ratings_overview')]//button[contains(@class,'x-btn-text')and text()='Add']"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			//((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
			ele.click();
		}catch(Exception e){
			e.printStackTrace();
		}
		waitForExtJSAjaxComplete(20);
		Reporter.log("Click Add button on Energy Rating tab", true);
	}
	
	public void clickEditEnergyRatingButton(String gridClass) {
		/*McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Edit", true, true).click();*/
		WebElement ele = driver.findElement(By.xpath("//div[contains(@realid,'location_ratings_overview')]//button[contains(@class,'x-btn-text')and text()='Edit']"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			//((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
			ele.click();
		}catch(Exception e){
			e.printStackTrace();
		}
		waitForExtJSAjaxComplete(20);
		Reporter.log("Click Edit button on Energy Rating tab", true);
	}
	
	public void clickDeleteEnergyRatingButton(String gridClass) {
		/*McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", gridClass, "button", "@class", "x-btn-text",
				"text()", "Delete", true, true).click();*/
		WebElement ele = driver.findElement(By.xpath("//div[contains(@realid,'location_ratings_overview')]//button[contains(@class,'x-btn-text')and text()='Delete']"));
		try{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			//((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
			ele.click();
		}catch(Exception e){
			e.printStackTrace();
		}
		waitForExtJSAjaxComplete(20);
		Reporter.log("Click Delete button on Energy Rating tab", true);
	}
	
	public void setLabel(String dialogId, String comboValue) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+dialogId+"')]//input[@name='label']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, comboValue);
		Reporter.log("Set Label - "+comboValue, true);
	}
	
	public String getLabel() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_RATING_DIALOG, "input", "@name",
				"label", true, true).getAttribute("value");
	}	
	
	public void setRating(String dialogId, String comboValue) {
		String elementId = driver.findElement(By
				.xpath("//div[contains(@class,'"+dialogId+"')]//input[@name='rating']")).getAttribute("Id");
		DropDown.setExtJsComboValue(driver, elementId, comboValue);
		Reporter.log("Set Rating - "+comboValue, true);
	}
	
	public String getRating() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_RATING_DIALOG, "input", "@name",
				"rating", true, true).getAttribute("value");
	}	
	
	public void setScore(String score) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_RATING_DIALOG, "input", "@name",
				"score", true, true);
		field.click();
		field.clear();
		field.sendKeys(score);
		Reporter.log("Set Score - " + score, true);
	}
	
	public String getScore() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_RATING_DIALOG, "input", "@name",
				"score", true, true).getAttribute("value");
	}	
	
	public void setCertificate(String certificate) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_RATING_DIALOG, "input", "@name",
				"certificate", true, true);
		field.click();
		field.clear();
		field.sendKeys(certificate);
		Reporter.log("Set Certificate - " + certificate, true);
	}
	
	public String getCertificate() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_RATING_DIALOG, "input", "@name",
				"certificate", true, true).getAttribute("value");
	}	
	
	public void setInspector(String inspector) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_RATING_DIALOG, "input", "@name",
				"inspector", true, true);
		field.click();
		field.clear();
		field.sendKeys(inspector);
		Reporter.log("Set Inspector - " + inspector, true);
	}
	
	public String getInspector() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_RATING_DIALOG, "input", "@name",
				"inspector", true, true).getAttribute("value");
	}	
	
	public void setRatingDate(String ratingDate) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_RATING_DIALOG, "input", "@name",
				"ratingDate", true, true);
		field.click();
		field.clear();
		field.sendKeys(ratingDate);
		Reporter.log("Set Rating Date - " + ratingDate, true);
	}
	
	public String getRatingDate() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_RATING_DIALOG, "input", "@name",
				"ratingDate", true, true).getAttribute("value");
	}	
	
	public void setValidUntil(String validUntil) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_RATING_DIALOG, "input", "@name",
				"validUntil", true, true);
		field.click();
		field.clear();
		field.sendKeys(validUntil);
		Reporter.log("Set Valid Until - " + validUntil, true);
	}
	
	public String getValidUntil() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_RATING_DIALOG, "input", "@name",
				"validUntil", true, true).getAttribute("value");
	}	
	
	public void setInspectionDate(String inspectionDate) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_RATING_DIALOG, "input", "@name",
				"inspectionDate", true, true);
		field.click();
		field.clear();
		field.sendKeys(inspectionDate);
		Reporter.log("Set Inspection Date - " + inspectionDate, true);
	}
	
	public String getInspectionDate() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_RATING_DIALOG, "input", "@name",
				"inspectionDate", true, true).getAttribute("value");
	}	
	
	public void setNote(String note) {
		WebElement field = McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_RATING_DIALOG, "textarea", "@name",
				"note", true, true);
		field.click();
		field.clear();
		field.sendKeys(note);
		Reporter.log("Set Note - " + note, true);
	}
	
	public String getNote() {
		return McsElement.getElementByAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_RATING_DIALOG, "textarea", "@name",
				"note", true, true).getAttribute("value");
	}	
	
	public void saveClose() {
		McsElement.getElementByPartAttributeValueAndParentElement(driver,
				"div", "@class", ENERGY_RATING_DIALOG, "button",
				"text()", "Save", "text()", "Close", true, true).click();
		waitForExtJSAjaxComplete(25);
		Reporter.log("Save & Close", true);
	}
	
	public void close() {
		driver
		.findElement(
				By.xpath("(//div[contains(@class,'"+ENERGY_RATING_DIALOG+"')]//button[text()='Close'])[last()]")).click();
		Reporter.log("Click Close button", true);
	}
}
